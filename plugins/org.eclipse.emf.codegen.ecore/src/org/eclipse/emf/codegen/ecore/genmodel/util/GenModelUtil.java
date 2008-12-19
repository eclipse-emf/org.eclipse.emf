/**
 * <copyright>
 *
 * Copyright (c) 2005-2008 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: GenModelUtil.java,v 1.5 2008/12/19 00:16:23 marcelop Exp $
 */

package org.eclipse.emf.codegen.ecore.genmodel.util;

import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;

import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.merge.java.JControlModel;

/**
 * This class contains convenient static methods for working with GenModel objects.
 * @since 2.2.0
 */
public class GenModelUtil
{
  public static String getAnnotation(GenBase genBase, String sourceURI, String key)
  {
    GenAnnotation genAnnotation = genBase.getGenAnnotation(sourceURI);
    return genAnnotation == null ? null : (String)genAnnotation.getDetails().get(key);
  }

  public static void setAnnotation(GenBase genBase, String sourceURI, String key, String value)
  {
    GenAnnotation genAnnotation = genBase.getGenAnnotation(sourceURI);
    if (genAnnotation == null)
    {
      genAnnotation = GenModelFactory.eINSTANCE.createGenAnnotation();
      genAnnotation.setSource(sourceURI);
      genBase.getGenAnnotations().add(genAnnotation);
    }
    genAnnotation.getDetails().put(key, value);
  }
  
  /**
   * @since 2.5
   */
  public static Generator createGenerator(GenModel genModel)
  {
    Generator generator = new Generator();
    generator.setInput(genModel);
    JControlModel jControlModel = generator.getJControlModel();

    if (genModel.isCodeFormatting())
    {
      jControlModel.setLeadingTabReplacement(null);
      jControlModel.setConvertToStandardBraceStyle(false);
    }
    else
    {
      Map<?, ?> options = JavaCore.getOptions();
      String tabSize = (String)options.get(DefaultCodeFormatterConstants.FORMATTER_TAB_SIZE);
      String braceStyle = (String)options.get(DefaultCodeFormatterConstants.FORMATTER_BRACE_POSITION_FOR_TYPE_DECLARATION);
      String tabCharacter = (String)options.get(DefaultCodeFormatterConstants.FORMATTER_TAB_CHAR);
      if (JavaCore.TAB.equals(tabCharacter))
      {
        jControlModel.setLeadingTabReplacement("\t");
      }
      else
      {
        String spaces = "";
        for (int i = Integer.parseInt(tabSize); i > 0; --i)
        {
          spaces += " ";
        }
        jControlModel.setLeadingTabReplacement(spaces);
      }
      jControlModel.setConvertToStandardBraceStyle(DefaultCodeFormatterConstants.END_OF_LINE.equals(braceStyle));
    }
    return generator;
  }  
}
