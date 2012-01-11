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