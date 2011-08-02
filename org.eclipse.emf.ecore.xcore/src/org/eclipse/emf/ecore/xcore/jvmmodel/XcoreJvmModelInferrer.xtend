package org.eclipse.emf.ecore.xcore.jvmmodel
 
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociator
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.emf.ecore.EObject
import java.util.List
import com.google.inject.Inject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EGenericType
import org.eclipse.emf.ecore.xcore.XPackage
import org.eclipse.emf.ecore.xcore.util.XcoreJvmInferrer

/**
 * <p>Infers a JVM model from the source model.</p> 
 *
 * <p>The JVM model should contain all elements that would appear in the Java code 
 * which is generated from the source model. 
 * Other Xtend models link against the JVM model rather than the source model. The JVM
 * model elements should be associated with their source element by means of the
 * {@link IJvmModelAssociator}.</p>     
 */
class XcoreJvmModelInferrer implements IJvmModelInferrer {

	@Inject 
	IJvmModelAssociator jvmModelAssociator

/*
   	override List<? extends JvmDeclaredType> inferJvmModel(EObject sourceObject) {
   		return newArrayList();
   	}
*/
   	
   	def dispatch List<? extends JvmDeclaredType> inferJvmModel(EObject sourceObject) {
   		return newArrayList();
   	}
   	def dispatch List<? extends JvmDeclaredType> inferJvmModel(XPackage xPackage) {
   		return new XcoreJvmInferrer().getDeclaredTypes(xPackage);
   	}
}
