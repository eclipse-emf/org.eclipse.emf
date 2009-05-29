/**
 * <copyright>
 *
 * Copyright (c) 2009 Tom Schindl and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: NLSMessages.java,v 1.1 2009/05/29 15:06:30 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp;

import org.eclipse.osgi.util.NLS;

/**
 * NLS strings for i18n
 */
public class NLSMessages
{
  /**
   * 
   */
  public static String ProjectAdminViewPart_StartDateNotParseable;
  /**
   * 
   */
  public static String ProjectAdminViewPart_EndDateNotParseable;
  
  static {
    NLS.initializeMessages(NLSMessages.class.getName(), NLSMessages.class);
  }
}
