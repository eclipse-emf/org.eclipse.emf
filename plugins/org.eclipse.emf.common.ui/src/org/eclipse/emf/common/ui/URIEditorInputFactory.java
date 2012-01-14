/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.common.ui;


import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;


/**
 * A implementation of an {@link IElementFactory} for creating {@link URIEditorInput} instances.
 */
public class URIEditorInputFactory implements IElementFactory
{
  public static final String ID = URIEditorInputFactory.class.getName();
  
  public URIEditorInputFactory()
  {
    super();
  }

  public IAdaptable createElement(IMemento memento)
  {
    return URIEditorInput.create(memento);
  }
}
