package org.eclipse.emf.ecore.xcore.scoping

import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier
import org.eclipse.emf.codegen.ecore.genmodel.GenModel
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.xcore.XAnnotationDirective
import org.eclipse.emf.ecore.xcore.XPackage
import org.eclipse.emf.ecore.xcore.XcorePackage
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.common.types.TypesPackage
import org.eclipse.xtext.ecore.EcoreResourceDescriptionStrategy
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.util.IAcceptor
import org.eclipse.xtext.xbase.resource.XbaseResourceDescriptionStrategy

@Singleton
class XcoreResourceDescriptionStrategy extends XbaseResourceDescriptionStrategy {
	@Inject EcoreResourceDescriptionStrategy ecoreResourceDescriptionStrategy

	override createEObjectDescriptions(EObject it, IAcceptor<IEObjectDescription> acceptor) {
		val ePackage = eClass.EPackage

		switch ePackage {
			case EcorePackage.eINSTANCE: {
				ecoreResourceDescriptionStrategy.createEObjectDescriptions(it, acceptor)
				switch it {
					EPackage: {
						EClassifiers.forEach[createEObjectDescriptions(acceptor)]
					}
					EClass: {
						(EOperations + EStructuralFeatures).forEach[createEObjectDescriptions(acceptor)]
					}
				}
			}
			case GenModelPackage.eINSTANCE: {
				switch it {
					GenModel: {
						genPackages.forEach[createEObjectDescriptions(acceptor)]
					}
					GenPackage: {
						genClassifiers.forEach[createEObjectDescriptions(acceptor)]
					}
					GenClassifier: {
						super.createEObjectDescriptions(it, acceptor)
					}
				}
			}
			case XcorePackage.eINSTANCE: {
				switch it {
					XPackage: {
						annotationDirectives.forEach[createEObjectDescriptions(acceptor)]
					}
					XAnnotationDirective: {
						super.createEObjectDescriptions(it, acceptor)
					}
				}
			}
			case TypesPackage.eINSTANCE: {
				if (it instanceof JvmDeclaredType) {
					super.createEObjectDescriptions(it, acceptor)
					members.filter(JvmDeclaredType).forEach[createEObjectDescriptions(acceptor)]
				}
			}
		}
		false
	}

}
