/**
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
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
