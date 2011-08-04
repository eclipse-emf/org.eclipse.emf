package org.eclipse.emf.ecore.xcore.util

import org.eclipse.emf.ecore.xcore.XOperation
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation
import org.eclipse.xtext.common.types.JvmOperation

class MappingFacade {
	
	def getEOperation(XOperation op) {
		XcoreEcoreBuilder::get(op) as EOperation
	}
	
	def getGenOperation(XOperation op) {
		XcoreEcoreBuilder::get(op) as GenOperation
	}
	
	def getXOperation(GenOperation op) {
		XcoreEcoreBuilder::get(op) as GenOperation
	}
	
	def JvmOperation getJvmOperation(XOperation op) {
		null //TODO
	}
	
	
}