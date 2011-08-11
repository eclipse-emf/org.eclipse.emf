package org.eclipse.emf.ecore.xcore.conversion;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.conversion.impl.QualifiedNameValueConverter;

public class FixedQualifiedNameValueConverter extends QualifiedNameValueConverter
{
	
	@Override
	protected boolean isDelegateRuleCall(EObject grammarElement)
	{
		if (grammarElement instanceof Keyword) {
			if ("void".equals(((Keyword) grammarElement).getValue()))
				return true;
		}
	  return super.isDelegateRuleCall(grammarElement);
	}
	
}
