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
 * $Id: MappedObjectState.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.mapping;


import java.util.Collection;

import org.eclipse.emf.edit.provider.IChangeNotifier;


/**
 * This interface represents that state information that is available for each mapped object,
 * i.e., each input or output object in the domain.
 */
public interface MappedObjectState extends IChangeNotifier
{
  /**
   * This returns whether the mapped object is an input.
   */
  boolean isInput();

  /**
   * This is called to indicate that the mapped object is an input.
   */
  void setInput();

  /**
   * This returns whether the mapped object is an output.
   */
  boolean isOutput();

  /**
   * This is called to indicate that the mapped object is an output.
   */
  void setOutput();

  /**
   * This returns, if appropriate, the originating input object of the output object.
   */
  Object getOriginatingInput();

  /**
   * This sets the originating input object of the output object.
   */
  void setOriginatingInput(Object originatingInput);

  /**
   * This returns all the mappings the refer to the mapped object.
   */
  Collection getMappings();
}


