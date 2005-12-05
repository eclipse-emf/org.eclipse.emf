/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: GenModelUtil.java,v 1.4 2005/12/05 20:26:34 marcelop Exp $
 */

package org.eclipse.emf.codegen.ecore.genmodel.util;

import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;

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
}
