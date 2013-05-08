/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.formatting;


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.formatting.IWhitespaceInformationProvider;
import org.eclipse.xtext.preferences.IPreferenceValues;
import org.eclipse.xtext.preferences.IPreferenceValuesProvider;
import org.eclipse.xtext.preferences.PreferenceKey;
import org.eclipse.xtext.xbase.formatting.BasicFormatterPreferenceKeys;
import org.eclipse.xtext.xbase.formatting.IFormattingPreferenceValuesProvider;

import com.google.inject.Inject;


public class XcoreFormatterPreferenceValuesProvider implements IFormattingPreferenceValuesProvider
{
  @Inject
  private IWhitespaceInformationProvider whitespaceInfo;

  @Inject 
  private IPreferenceValuesProvider valuesProvider;

  public IPreferenceValues getPreferenceValues(Resource resource)
  {
    final IPreferenceValues preferenceValues = internalGetRawPreferenceValues(resource);
    
    URI uri = resource.getURI();
    final String indent = whitespaceInfo.getIndentationInformation(uri).getIndentString();
    final String lineSep = whitespaceInfo.getLineSeparatorInformation(uri).getLineSeparator();

    return 
      new IPreferenceValues()
      {
        public String getPreference(PreferenceKey key)
        {
          if (key == BasicFormatterPreferenceKeys.indentation)
          {
            return indent;
          }
          if (key == BasicFormatterPreferenceKeys.lineSeparator)
          {
            return lineSep;
          }
          return preferenceValues.getPreference(key);
        }
      };
  }

  protected IPreferenceValues internalGetRawPreferenceValues(final Resource resource)
  {
    return valuesProvider.getPreferenceValues(resource);
  }
}
