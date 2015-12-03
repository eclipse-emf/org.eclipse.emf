/**
 * 
 */
package org.eclipse.emf.ecore.xcore.validation;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.annotations.validation.UnresolvedFeatureCallTypeAwareMessageProvider;

/**
 * @author dhuebner
 *
 */
public class XcoreAwareMessageProvider extends UnresolvedFeatureCallTypeAwareMessageProvider {
	
@Override
protected String getTypeName(EClass c, EStructuralFeature referingFeature) {
	String typeName = super.getTypeName(c, referingFeature);
	if(Strings.isEmpty(typeName) && referingFeature == XcorePackage.Literals.XGENERIC_TYPE__TYPE) {
		return " to a type";
	}
	return typeName;
}
}
