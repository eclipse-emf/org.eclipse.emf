/*
 * Copyright (c) 2018 Eclipse contributors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.edit.ui.util;


import org.eclipse.emf.edit.ui.action.RevertAction;
import org.eclipse.ui.ISaveablePart;


/**
 * A saveable part that can be reverted back to its saved state, discarding any {@link #isDirty() state}.
 * 
 * @see RevertAction
 * @since 2.14
 */
public interface IRevertablePart extends ISaveablePart
{
  /**
   * Reverts the part to its saved state, discarding any {@link #isDirty() dirty} state.
   */
  void doRevert();
}
