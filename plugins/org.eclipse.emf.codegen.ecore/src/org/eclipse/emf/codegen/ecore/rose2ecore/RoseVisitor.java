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
 * $Id: RoseVisitor.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.rose2ecore;


import org.eclipse.emf.codegen.ecore.rose2ecore.parser.RoseNode;


/**
 *
 */
public interface RoseVisitor
{
  public void visitObject(RoseNode tree);

  public void visitList(RoseNode tree);
}
