/**
 * Copyright (c) 2015 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.validation;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.xbase.ui.validation.ProjectAwareUniqueClassNameValidator;

/**
 * Checks the genmodel property modelDir to identify types that are derived
 * from the current resource.
 * 
 * @author Sebastian Zarnekow
 */
public class XcoreUniqueClassNameValidator extends ProjectAwareUniqueClassNameValidator {

	private static final String OUTPUT_DIR = "XcoreUniqueClassNameValidator.OUTPUT_DIR";
	
	/*
	 * Store the configured model directory in the context map
	 * to make it available to #isDerived  
	 */
	@Override
	protected void doCheckUniqueName(JvmDeclaredType type) {
		Map<Object, Object> currentContext = getContext();
		if (currentContext != null && !currentContext.containsKey(OUTPUT_DIR)) {
			Resource resource = type.eResource();
			List<EObject> contents = resource.getContents();
			if (contents.size() > 1) {
				GenModel genmodel = (GenModel) contents.get(1);
				String modelDirectory = genmodel.getModelDirectory();
				currentContext.put(OUTPUT_DIR, new Path(modelDirectory));
			}
		}
		super.doCheckUniqueName(type);
	}
	
	@Override
	protected boolean isDerived(IResource resource) {
		/*
		 * Since Xcore doesn't use an OutputConfigurationProvider but reads from the preferences
		 * directly to store the output directory in the genmodel, we use the path of the
		 * resource the check if it is contained in the output dir
		 */
		Map<Object, Object> currentContext = getContext();
		IPath outputDir = (IPath) currentContext.get(OUTPUT_DIR);
		if (outputDir != null && outputDir.isPrefixOf(resource.getFullPath())) {
			return true;
		}
		return super.isDerived(resource);
	}
	
}
