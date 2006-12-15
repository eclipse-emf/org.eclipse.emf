/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: XSDNotationType.java,v 1.5 2006/12/15 18:59:56 emerks Exp $
 */
package org.eclipse.xsd.impl.type;

import org.eclipse.emf.ecore.xml.type.internal.QName;

public class XSDNotationType extends XSDAnySimpleType
{
  @Override
  public Object getValue (String literal)
  {
    try
    {
      return new QName(literal);
    }
    catch (RuntimeException exception)
    {
      return null;
    }
  }
}
