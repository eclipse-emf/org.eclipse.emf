/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.xcore.generator

import com.google.inject.Inject
import com.google.inject.Provider
import java.util.Collections
import java.util.HashSet
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
import org.eclipse.xtext.common.types.JvmFormalParameter
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.xbase.compiler.XbaseCompiler
import org.eclipse.xtext.linking.impl.XtextLinkingDiagnostic
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.xbase.XBlockExpression
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable
import java.util.Set
import org.eclipse.emf.ecore.EModelElement
import com.google.common.collect.Maps
import java.util.Map
import org.eclipse.emf.codegen.util.CodeGenUtil

class XcoreGenerator implements IGenerator {

	@Inject
	extension XcoreMapper mappings

	@Inject
	XbaseCompiler compiler

	@Inject
	Provider<XcoreGeneratorImpl> xcoreGeneratorImplProvider

	def void generateBodyAnnotations(XPackage pack) {
		val errors = getErrors(pack)
		val processed = newHashSet()
		for (xClassifier : pack.classifiers) {
			if (xClassifier instanceof XDataType) {
				val XDataType xDataType = xClassifier
				val eDataType = xDataType.mapping.EDataType
				val createBody = xDataType.createBody
				val creator = xDataType.mapping.creator
				if (createBody !== null && creator !== null) {
					val appendable = createAppendable
					appendable.declareVariable(creator.parameters.get(0), "it")
					compile(eDataType, "create", appendable, errors, createBody, creator.returnType, Collections.emptySet);
				}
				val convertBody = xDataType.convertBody
				val converter = xDataType.mapping.converter
				if (convertBody !== null && converter !== null) {
					val appendable = createAppendable
					appendable.declareVariable(converter.parameters.get(0), "it")
					compile(eDataType, "convert", appendable, errors, convertBody, converter.returnType, Collections.emptySet);
				}
			}
			else {
				val xClass = xClassifier as XClass
				val eClass = xClass.mapping.EClass
				for (eStructuralFeature : eClass.EAllStructuralFeatures) {
					if (processed.add(eStructuralFeature)) {
						val xFeature = mappings.getXFeature(eStructuralFeature)
						if (xFeature !== null) {
							val getBody = xFeature.getBody
							if (getBody !== null) {
								val getter = mappings.getMapping(xFeature).getter
								val appendable = createAppendable
								appendable.declareVariable(getter.declaringType, "this")
								val superType = getter.declaringType.superTypes.head
								if (superType !== null) {
									appendable.declareVariable(superType.type, "super")
								}
								compile(eStructuralFeature, "get", appendable, errors, getBody, getter.returnType, Collections.emptySet);
							}
						}
					}
				}
				for (eOperation : eClass.EAllOperations) {
					if (processed.add(eOperation)) {
						val xOperation = mappings.getXOperation(eOperation)
						if (xOperation !== null) {
							val body = xOperation.body
							if (body !== null) {
								val xOperationMapping = mappings.getMapping(xOperation)
								val jvmOperation = xOperationMapping.jvmOperation
								if (jvmOperation !== null) {
									val appendable = createAppendable
									var declaringType = jvmOperation.declaringType
									if (xOperationMapping.genOperation.genClass.externalInterface) {
										// For an external interface, the synthetic inferred interface should be skipped to use the super type.
										val superTypes = declaringType.superTypes
										val effectiveTypeReference = superTypes.head
										if (effectiveTypeReference !== null) {
											appendable.declareVariable(effectiveTypeReference.type, "this")
										}
									} else {
										appendable.declareVariable(declaringType, "this")
										val superType = declaringType.superTypes.head
										if (superType !== null) {
											appendable.declareVariable(superType.type, "super")
										}
									}
									for (JvmFormalParameter parameter : jvmOperation.parameters) {
										appendable.declareVariable(parameter, parameter.getName())
									}
									compile(eOperation, "body", appendable, errors, body, jvmOperation.returnType, new HashSet<JvmTypeReference>(jvmOperation.exceptions));
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

	def compile(EModelElement target, String key, ITreeAppendable appendable, Map<EObject, String> errors, XBlockExpression body, JvmTypeReference returnType, Set<JvmTypeReference> exceptions) {
		try {
			for (Map.Entry<EObject, String> error : errors.entrySet) {
				if (EcoreUtil.isAncestor(body, error.key)) {
					throw new RuntimeException(error.value);
				}
			}

			compiler.compile(body, appendable, returnType, exceptions)
			EcoreUtil::setAnnotation(target, GenModelPackage::eNS_URI, key, extractBody(appendable.toString))
		}
		catch (Throwable throwable) {
			val message = if (throwable.message === null) "throw new <%java.lang.Error%>(\"Unresolved compilation problem\");" else "throw new <%java.lang.Error%>(\"Unresolved compilation problems: " + CodeGenUtil.unicodeEscapeEncode(throwable.message) + "\");"
			EcoreUtil::setAnnotation(target, GenModelPackage::eNS_URI, key, message)
		}
	}

	def getErrors(XPackage xPackage) {
		val result = Maps.<EObject, String>newLinkedHashMap
		val resource = xPackage.eResource
		for (Resource.Diagnostic diagnostic : resource.errors) {
			if (diagnostic instanceof XtextLinkingDiagnostic) {
				val uri = diagnostic.uriToProblem
				if (uri !== null) {
					val eObject = resource.getEObject(uri.fragment);
					if (eObject !== null) {
						result.put(eObject, diagnostic.message)
					}
				}
			}
		}
		return result
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
		if (genModel.modelDirectory !== null) {
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
