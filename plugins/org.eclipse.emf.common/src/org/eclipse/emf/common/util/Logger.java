/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: Logger.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.common.util;



/**
 * A logger of log entries.
 * It can be implemented by different logging facilities depending on the runtime.
 * It is plastic and intended to support any underlying logging facility.
 */
public interface Logger
{
  /**
   * Logs an entry.
   * @param logEntry a plastic entry to log.
   */
  void log(Object logEntry);
}
