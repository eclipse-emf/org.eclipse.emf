/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore

import org.eclipse.emf.codegen.ecore.genmodel.GenClass
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter

class XcoreExtensions {
	def getGenClass(XGenericType type) {
		switch t : type.type {
			GenClass : t
			default : null
		}
	}
	
	def getGenTypeParameter(XGenericType type) {
		switch t : type.type {
			GenTypeParameter : t
			default : null
		}
	}
}