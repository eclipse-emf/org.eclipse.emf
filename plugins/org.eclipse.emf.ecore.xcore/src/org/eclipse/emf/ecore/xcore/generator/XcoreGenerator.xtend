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
import org.eclipse.emf.ecore.xcore.XDataType
import org.eclipse.emf.ecore.xcore.XOperation
import org.eclipse.emf.ecore.xcore.XPackage
import org.eclipse.emf.ecore.xcore.XStructuralFeature
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.xbase.compiler.XbaseCompiler

import static extension org.eclipse.emf.ecore.xcore.XcoreExtensions.*

class XcoreGenerator implements IGenerator {
	
	@Inject
	extension XcoreMapper mappings
	
	@Inject
	XbaseCompiler compiler
	
	@Inject
	Provider<XcoreGeneratorImpl> xcoreGeneratorImplProvider
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		val pack = resource.contents.head as XPackage
		// install operation bodies
		for (op : pack.allContentsIterable.filter(typeof(XOperation))) {
			val eOperation = op.mapping.EOperation
			val body = op.body
			if (body != null)
			{
				val appendable = createAppendable
				appendable.declareVariable(mappings.getMapping(op).jvmOperation.declaringType, "this");
				compiler.compile(body, appendable, null)
				EcoreUtil::setAnnotation(eOperation, GenModelPackage::eNS_URI, "body", extractBody(appendable.toString));
			}
		}
		// install feature accessors
		for (feature : pack.allContentsIterable.filter(typeof(XStructuralFeature))) {
			val eStructuralFeature = feature.mapping.EStructuralFeature
			val getBody = feature.getBody
			if (getBody != null) {
				val getter = mappings.getMapping(feature).getter
				val appendable = createAppendable
				appendable.declareVariable(getter.declaringType, "this");
				compiler.compile(getBody, appendable, null)
				EcoreUtil::setAnnotation(eStructuralFeature, GenModelPackage::eNS_URI, "get", extractBody(appendable.toString));
			}
		}
		// install data type converters
		for (dataType : pack.allContentsIterable.filter(typeof(XDataType))) {
			val eDataType = dataType.mapping.EDataType
			val createBody = dataType.createBody
			val creator = dataType.mapping.creator
			if (createBody != null && creator != null) {
				val appendable = createAppendable
				appendable.declareVariable(creator.parameters.get(0), "it");
				compiler.compile(createBody, appendable, null)
				EcoreUtil::setAnnotation(eDataType, GenModelPackage::eNS_URI, "create", extractBody(appendable.toString));
			}
			val convertBody = dataType.convertBody
			val converter = dataType.mapping.converter
			if (convertBody != null && converter != null) {
				val appendable = createAppendable
				appendable.declareVariable(converter.parameters.get(0), "it");
				compiler.compile(convertBody, appendable, null)
				EcoreUtil::setAnnotation(eDataType, GenModelPackage::eNS_URI, "convert", extractBody(appendable.toString));
			}
		}

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
		genModel.canGenerate = true
		val generator = xcoreGeneratorImplProvider.get
		generator.input = genModel
		generator.fileSystemAccess = fsa
		generator.generate(genModel, GenBaseGeneratorAdapter::MODEL_PROJECT_TYPE,
				new BasicMonitor());
	}
}
