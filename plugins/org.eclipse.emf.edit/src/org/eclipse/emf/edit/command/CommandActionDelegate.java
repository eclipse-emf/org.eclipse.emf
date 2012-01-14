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
package org.eclipse.emf.edit.command;


/**
 * This is the interface used by a CommandAction to delegate all of the set methods of an IAction to a {@link org.eclipse.emf.common.command.Command}.
 */
public interface CommandActionDelegate
{
  /**
   * This returns whether the action should be enabled.
   */
  public boolean canExecute();

  /**
   * This returns the decoration, if any, of the action.
   */
  public Object getImage();

  /**
   * This returns the menu text, if any, of the action.
   */
  public String getText();

  /**
   * This returns the description, if any, of the action.
   */
  public String getDescription();

  /**
   * This returns the tool tip text, if any, of the action.
   */
  public String getToolTipText();
}
