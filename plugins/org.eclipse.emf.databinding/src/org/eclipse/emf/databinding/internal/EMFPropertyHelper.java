/**
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   Matthew Hall - initial API and implementation (bug 194734)
 *   Martin Frey <martin.frey@logica.com> - bug 256150
 *   Matthew Hall - bug 264307
 *   Tom Schindl <tom.schindl@bestsolution.at> - port to EMF in 262160
 */
package org.eclipse.emf.databinding.internal;

import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @since 2.5
 */
public class EMFPropertyHelper
{

  /**
   * @return String description of property descriptor
   */
  public static String propertyName(EStructuralFeature eStructuralFeature)
  {
    return eStructuralFeature.getEContainingClass().getName() + "." + eStructuralFeature.getName() + ""; //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * @return class name excluding package
   */
  public static String shortClassName(EStructuralFeature eStructuralFeature)
  {
    return eStructuralFeature.getEType().getName();
  }
}
