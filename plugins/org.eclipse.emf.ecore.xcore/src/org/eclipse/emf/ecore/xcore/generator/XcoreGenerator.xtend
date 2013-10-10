/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.generator

import com.google.inject.Inject
import com.google.inject.Provider
import org.eclipse.emf.codegen.ecore.genmodel.GenModel
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter
import org.eclipse.emf.common.util.BasicMonitor
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.xcore.XClass
import org.eclipse.emf.ecore.xcore.XDataType
import org.eclipse.emf.ecore.xcore.XPackage
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.xbase.compiler.XbaseCompiler

import java.util.Collections
import java.util.HashSet
import org.eclipse.xtext.common.types.JvmFormalParameter
import org.eclipse.xtext.common.types.JvmTypeReference

class XcoreGenerator implements IGenerator {

	@Inject
	extension XcoreMapper mappings

	@Inject
	XbaseCompiler compiler

	@Inject
	Provider<XcoreGeneratorImpl> xcoreGeneratorImplProvider

	def void generateBodyAnnotations(XPackage pack) {
		val processed = newHashSet()
		for (xClassifier : pack.classifiers) {
			if (xClassifier instanceof XDataType) {
				val xDataType = xClassifier as XDataType
				val eDataType = xDataType.mapping.EDataType
				val createBody = xDataType.createBody
				val creator = xDataType.mapping.creator
				if (createBody != null && creator != null) {
					val appendable = createAppendable
					appendable.declareVariable(creator.parameters.get(0), "it")
					compiler.compile(createBody, appendable, creator.returnType, Collections::emptySet)
					EcoreUtil::setAnnotation(eDataType, GenModelPackage::eNS_URI, "create", extractBody(appendable.toString))
				}
				val convertBody = xDataType.convertBody
				val converter = xDataType.mapping.converter
				if (convertBody != null && converter != null) {
					val appendable = createAppendable
					appendable.declareVariable(converter.parameters.get(0), "it")
					compiler.compile(convertBody, appendable, converter.returnType, Collections::emptySet)
					EcoreUtil::setAnnotation(eDataType, GenModelPackage::eNS_URI, "convert", extractBody(appendable.toString))
				}
			}
			else {
				val xClass = xClassifier as XClass
				val eClass = xClass.mapping.EClass
				for (eStructuralFeature : eClass.EAllStructuralFeatures) {
					if (processed.add(eStructuralFeature)) {
						val xFeature = mappings.getXFeature(eStructuralFeature)
						if (xFeature != null) {
							val getBody = xFeature.getBody
							if (getBody != null) {
								val getter = mappings.getMapping(xFeature).getter
								val appendable = createAppendable
								appendable.declareVariable(getter.declaringType, "this")
								val superType = getter.declaringType.superTypes.head
								if (superType != null) {
									appendable.declareVariable(superType.type, "super")
								}
								compiler.compile(getBody, appendable, getter.returnType, Collections::emptySet)
								EcoreUtil::setAnnotation(eStructuralFeature, GenModelPackage::eNS_URI, "get", extractBody(appendable.toString)) }
						}
					}
				}
				for (eOperation : eClass.EAllOperations) {
					if (processed.add(eOperation)) {
						val xOperation = mappings.getXOperation(eOperation)
						if (xOperation != null) {
							val body = xOperation.body
							if (body != null) {
								val jvmOperation = mappings.getMapping(xOperation).jvmOperation
								if (jvmOperation != null) {
									val appendable = createAppendable
									appendable.declareVariable(jvmOperation.declaringType, "this")
									val superType = jvmOperation.declaringType.superTypes.head
									if (superType != null) {
										appendable.declareVariable(superType.type, "super")
									}
									for (JvmFormalParameter parameter : jvmOperation.parameters) {
										appendable.declareVariable(parameter, parameter.getName())
									}
									compiler.compile(body, appendable, jvmOperation.returnType, new HashSet<JvmTypeReference>(jvmOperation.exceptions))
									EcoreUtil::setAnnotation(eOperation, GenModelPackage::eNS_URI, "body", extractBody(appendable.toString))
								}
							}
						}
					}
				}
			}
		}
	}

	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		generateBodyAnnotations(resource.contents.head as XPackage)
		generateGenModel(resource.contents.filter(typeof(GenModel)).head, fsa)
	}

	def createAppendable() {
		new XcoreAppendable()
	}

	def extractBody(String body) {
		var result = if (body.startsWith("\n")) body.substring(1) else body
		if (result.startsWith("{\n")) {
			result = result.replace("\n\t", "\n")
			result.substring(1, result.length - 2)
		} else {
			result
		}
	}

	def generateGenModel(GenModel genModel, IFileSystemAccess fsa) {
		if (genModel.modelDirectory != null) {
			genModel.canGenerate = true
			val generator = xcoreGeneratorImplProvider.get
			generator.input = genModel
			generator.fileSystemAccess = fsa
			generator.modelDirectory = genModel.modelDirectory
			generator.generate(genModel, GenBaseGeneratorAdapter::MODEL_PROJECT_TYPE, new BasicMonitor())
			generator.generate(genModel, GenBaseGeneratorAdapter::EDIT_PROJECT_TYPE, new BasicMonitor())
			generator.generate(genModel, GenBaseGeneratorAdapter::EDITOR_PROJECT_TYPE, new BasicMonitor())
			generator.generate(genModel, GenBaseGeneratorAdapter::TESTS_PROJECT_TYPE, new BasicMonitor())
		}
	}
}
