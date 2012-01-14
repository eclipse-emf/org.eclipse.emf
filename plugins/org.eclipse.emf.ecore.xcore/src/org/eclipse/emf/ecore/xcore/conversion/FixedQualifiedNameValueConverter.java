/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.conversion;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.conversion.impl.QualifiedNameValueConverter;
import org.eclipse.xtext.nodemodel.INode;

public class FixedQualifiedNameValueConverter extends QualifiedNameValueConverter
{
  @Override
  public String toValue(String string, INode node) throws ValueConverterException
  {
    return node == null && string.startsWith("^") ? string.substring(1) : super.toValue(string, node);
  }
	
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
