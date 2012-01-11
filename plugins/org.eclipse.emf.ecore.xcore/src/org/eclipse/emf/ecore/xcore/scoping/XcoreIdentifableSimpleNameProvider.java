package org.eclipse.emf.ecore.xcore.scoping;

import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.xbase.featurecalls.IdentifiableSimpleNameProvider;

public class XcoreIdentifableSimpleNameProvider extends IdentifiableSimpleNameProvider
{
	@Override
	public String getSimpleName(JvmIdentifiableElement element)
	{
		if (element instanceof JvmDeclaredType) {
			return "this";
		}
	  return super.getSimpleName(element);
	}
}
