/**
 * Copyright (c) 2013 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 */
package org.eclipse.emf.edit.provider;

/**
 * This is the interface implemented to provide a styled label text for an item;
 * it receives delegated calls from IStyledLabelProvider.
 * 
 * @author <a href="mailto:mikael.barbero@obeo.fr">Mikael Barbero</a>
 * @since 2.10
 */
public interface IItemStyledLabelProvider
{

  /**
   * This does the same thing as IStyledLabelProvider.getStyledText, it fetches
   * the styled label text specific to this object instance.
   * 
   * @param object
   *          the object to evaluate the styled string for.
   * @return the styled string.
   */
  Object getStyledText(Object object);
}
