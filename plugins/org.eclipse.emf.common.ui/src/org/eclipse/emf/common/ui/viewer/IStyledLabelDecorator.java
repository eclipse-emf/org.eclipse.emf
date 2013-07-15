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
package org.eclipse.emf.common.ui.viewer;

import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.StyledString;

/**
 * An extended {@link ILabelDecorator} to decorate a {@link StyledString}.
 * 
 * @author <a href="mailto:mikael.barbero@obeo.fr">Mikael Barbero</a>
 * @since 2.10
 */
public interface IStyledLabelDecorator extends ILabelDecorator
{
  /**
   * Returns a decorated {@link StyledString}. It may return the given {@code styledString} itself if no 
   * decoration has been done.
   * 
   * @param styledString the {@link StyledString} to decorate. Must not be null.
   * @param element the object being described by the given {@code styledString}. Must not be null. 
   * @return a decorated styled string or {@code styledString}. It never returns null.
   */
  public StyledString decorateStyledText(StyledString styledString, Object element);
}
