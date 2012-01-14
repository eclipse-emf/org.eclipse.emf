/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
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
