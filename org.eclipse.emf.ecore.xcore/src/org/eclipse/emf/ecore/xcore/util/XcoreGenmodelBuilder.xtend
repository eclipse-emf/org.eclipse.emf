package org.eclipse.emf.ecore.xcore.util

import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper
import com.google.inject.Inject
import org.eclipse.emf.ecore.xcore.XPackage
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory
import java.util.Collections
import static extension org.eclipse.xtext.xtend2.lib.EObjectExtensions.*
import org.eclipse.emf.codegen.ecore.genmodel.GenClass
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.ecore.xcore.XClass
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature
import org.eclipse.emf.ecore.xcore.XStructuralFeature
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation
import org.eclipse.emf.ecore.xcore.XOperation
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType
import org.eclipse.emf.ecore.xcore.XDataType

class XcoreGenmodelBuilder {
	
	@Inject extension XcoreMapper mapper
	
	def getGenModel(XPackage pack) {
		val ePackage = pack.mapping.getEPackage
		val genModel =  GenModelFactory::eINSTANCE.createGenModel();
      	genModel.initialize(Collections::singleton(ePackage));
      	pack.eResource.getContents().add(1, genModel);
      	genModel.initialize();
      for (genElement : genModel.allContentsIterable)
      {
      	switch genElement {
      		GenPackage : 
      		{
      			val xPack = genElement.ecorePackage.toXcoreMapping.xcoreElement as XPackage
				xPack.mapping.genPackage = genElement
				genElement.toXcoreMapping.xcoreElement = xPack    			
      		}
      		GenClass :
      		{
      			val xClass = genElement.ecoreClass.toXcoreMapping.xcoreElement as XClass
				xClass.mapping.genClass = genElement
				genElement.toXcoreMapping.xcoreElement = xClass    			
      		}
      		GenDataType :
      		{
      			val xDataType = genElement.ecoreDataType.toXcoreMapping.xcoreElement as XDataType
				xDataType.mapping.genDataType = genElement
				genElement.toXcoreMapping.xcoreElement = xDataType    			
      		}
      		GenFeature :
      		{
      			val xFeature = genElement.ecoreFeature.toXcoreMapping.xcoreElement as XStructuralFeature
				xFeature.mapping.genFeature = genElement
				genElement.toXcoreMapping.xcoreElement = xFeature    			
      		}
      		GenOperation :
      		{
      			val xOperation = genElement.ecoreOperation.toXcoreMapping.xcoreElement as XOperation
				xOperation.mapping.genOperation = genElement
				genElement.toXcoreMapping.xcoreElement = xOperation    			
      		}
      	}
      }
	}
}