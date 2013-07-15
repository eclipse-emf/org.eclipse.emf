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
package org.eclipse.emf.edit.ui.provider;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * An extended {@link org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider DelegatingStyledCellLabelProvider} that can
 * be used as a {@link ILabelProvider standard label provider} and {@link ITableLabelProvider table label provider} by 
 * delegating to the wrapped {@link IStyledLabelProvider}. The methods of these interfaces will return null if the wrapped 
 * {@link IStyledLabelProvider} does not implement itself the proper interfaces.
 * 
 * @author <a href="mailto:mikael.barbero@obeo.fr">Mikael Barbero</a>
 * @since 2.10
 */
public class DelegatingStyledCellLabelProvider extends org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider implements
    ILabelProvider, ITableLabelProvider
{

  /**
   * An extended version of the delegating styled cell label provider that also provides for fonts if the wrapped
   * {@link IStyledLabelProvider} itself provides for fonts.
   */
  public static class FontProvider extends DelegatingStyledCellLabelProvider implements IFontProvider, ITableFontProvider
  {
    /**
     * Construct an instance that wraps the given styled label provider. The constructed instance will 
     * return null when asked to provide for {@link ILabelProvider label} and for {@link ITableLabelProvider table label}
     * if the given {@code styledLabelProvider} does not implement itself the proper interfaces.
     * @param styledLabelProvider the styled label provider to be wrapped
     */
    public FontProvider(IStyledLabelProvider styledLabelProvider)
    {
      super(styledLabelProvider);
    }
  }
  
  /**
   * An extended version of the delegating styled cell label provider that also provides for colors if the wrapped
   * {@link IStyledLabelProvider} itself provides for colors.
   */
  public static class ColorProvider extends DelegatingStyledCellLabelProvider implements IColorProvider, ITableColorProvider
  {
    /**
     * Construct an instance that wraps the given styled label provider. The constructed instance will 
     * return null when asked to provide for {@link ILabelProvider label} and for {@link ITableLabelProvider table label}
     * if the given {@code styledLabelProvider} does not implement itself the proper interfaces.
     * @param styledLabelProvider the styled label provider to be wrapped
     */
    public ColorProvider(IStyledLabelProvider styledLabelProvider)
    {
      super(styledLabelProvider);
    }
  }
  
  /**
   * An extended version of the delegating styled cell label provider that also provides for fonts and colors if the wrapped
   * {@link IStyledLabelProvider} itself provides for fonts and colors.
   */
  public static class FontAndColorProvider extends DelegatingStyledCellLabelProvider implements IFontProvider, ITableFontProvider, IColorProvider, ITableColorProvider
  {
    /**
     * Construct an instance that wraps the given styled label provider. The constructed instance will 
     * return null when asked to provide for {@link ILabelProvider label} and for {@link ITableLabelProvider table label}
     * if the given {@code styledLabelProvider} does not implement itself the proper interfaces.
     * @param styledLabelProvider the styled label provider to be wrapped
     */
    public FontAndColorProvider(IStyledLabelProvider styledLabelProvider)
    {
      super(styledLabelProvider);
    }
  }

  /**
   * Construct an instance that wraps the given styled label provider. The constructed instance will 
   * return null when asked to provide for {@link ILabelProvider label} and for {@link ITableLabelProvider table label}
   * if the given {@code styledLabelProvider} does not implement itself the proper interfaces.
   * @param styledLabelProvider the styled label provider to be wrapped
   */
  public DelegatingStyledCellLabelProvider(IStyledLabelProvider styledLabelProvider)
  {
    super(styledLabelProvider);
  }

  /**
   * This implements {@link ILabelProvider#getText(Object)} by forwarding it to the wrapped styled label provider if 
   * it implements {@link ILabelProvider}. Returns null otherwise.
   */
  public String getText(Object element)
  {
    if (getStyledStringProvider() instanceof ILabelProvider) 
    {
      return ((ILabelProvider) getStyledStringProvider()).getText(element);
    }
    return null;
  }
  
  /**
   * This implements {@link ITableFontProvider#getFont(Object, int)} by forwarding it to the wrapped styled label provider if 
   * it implements {@link ITableFontProvider}. Returns null otherwise.
   */
  public Font getFont(Object element, int columnIndex)
  {
    if (getStyledStringProvider() instanceof ITableFontProvider) 
    {
      return ((ITableFontProvider) getStyledStringProvider()).getFont(element, columnIndex);
    }
    return null;
  }

  /**
   * This implements {@link ITableColorProvider#getForeground(Object, int)} by forwarding it to the wrapped styled label provider if 
   * it implements {@link ITableColorProvider}. Returns null otherwise.
   */
  public Color getForeground(Object element, int columnIndex)
  {
    if (getStyledStringProvider() instanceof ITableColorProvider) 
    {
      return ((ITableColorProvider) getStyledStringProvider()).getForeground(element, columnIndex);
    }
    return null;
  }

  /**
   * This implements {@link ITableColorProvider#getBackground(Object, int)} by forwarding it to the wrapped styled label provider if 
   * it implements {@link ITableColorProvider}. Returns null otherwise.
   */
  public Color getBackground(Object element, int columnIndex)
  {
    if (getStyledStringProvider() instanceof ITableColorProvider) 
    {
      return ((ITableColorProvider) getStyledStringProvider()).getBackground(element, columnIndex);
    }
    return null;
  }

  /**
   * This implements {@link ITableLabelProvider#getColumnImage(Object, int)} by forwarding it to the wrapped styled label provider if 
   * it implements {@link ITableLabelProvider}. Returns null otherwise.
   */
  public Image getColumnImage(Object element, int columnIndex)
  {
    if (getStyledStringProvider() instanceof ITableLabelProvider) 
    {
      return ((ITableLabelProvider) getStyledStringProvider()).getColumnImage(element, columnIndex);
    }
    return null;
  }

  /**
   * This implements {@link ITableLabelProvider#getColumnText(Object, int)} by forwarding it to the wrapped styled label provider if 
   * it implements {@link ITableLabelProvider}. Returns null otherwise.
   */
  public String getColumnText(Object element, int columnIndex)
  {
    if (getStyledStringProvider() instanceof ITableLabelProvider) 
    {
      return ((ITableLabelProvider) getStyledStringProvider()).getColumnText(element, columnIndex);
    }
    return null;
  }
}