/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.conversion;


import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.impl.KeywordAlternativeConverter;
import org.eclipse.xtext.conversion.impl.QualifiedNameValueConverter;
import org.eclipse.xtext.xbase.conversion.XbaseValueConverterService;

import com.google.inject.Inject;
import com.google.inject.Singleton;


@Singleton
public class XcoreValueConverterService extends XbaseValueConverterService
{
  @Inject
  private QualifiedNameValueConverter xQualifiedNameValueConverter;

  @Inject
  private QualifiedNameValueConverter xQualifiedNameWithWildcardValueConverter;

  @Inject
  private KeywordAlternativeConverter xIDConverter;

  @Inject
  private KeywordAlternativeConverter validIDConverter;

  @ValueConverter(rule = "XQualifiedName")
  public IValueConverter<String> getXQualifiedNameValueConverter()
  {
    return xQualifiedNameValueConverter;
  }

  @ValueConverter(rule = "QualifiedNameWithWildcard")
  public IValueConverter<String> getXQualifiedNameWithWildcardValueConverter()
  {
    return xQualifiedNameWithWildcardValueConverter;
  }

  @ValueConverter(rule = "XID")
  public IValueConverter<String> getXID()
  {
    return xIDConverter;
  }

  @ValueConverter(rule = "ValidID")
  public IValueConverter<String> getValidID()
  {
    return validIDConverter;
  }

}
