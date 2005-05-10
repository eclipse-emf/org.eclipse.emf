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
 * $Id: RoseVisitor.java,v 1.1 2005/05/10 17:40:33 davidms Exp $
 */
package org.eclipse.emf.importer.rose.builder;

import org.eclipse.emf.importer.rose.parser.RoseNode;


/**
 *
 */
public interface RoseVisitor
{
  public void visitObject(RoseNode tree);

  public void visitList(RoseNode tree);
}
