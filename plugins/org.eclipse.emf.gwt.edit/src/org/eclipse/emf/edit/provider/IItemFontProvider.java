/**
 * Copyright (c) 2008-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.provider;

import org.eclipse.emf.common.util.URI;


/**
 * This is the interface implemented to provide a color for an item;
 * it receives delegated calls from IFontProvider.
 * Fonts are expressed in a platform-independent way as a URI of the form:
 *<pre>
 *  font://&lt;font-family>/&lt;size>/&lt;style>
 *</pre>
 * If the authority is omitted, the viewer's current font family is specified.
 * If the height, which is specified in points, is omitted, the viewer's current font height is specified;
 * a size delta can be expressed as using + or - with the size,
 * e.g., +2 specifies a point height two points larger than the viewer's current font height.
 * The style can specify <code>"normal"</code>, <code>"bold"</code>, <code>"italic"</code>, or <code>"bold+italic"</code> variations;
 * when omitted, the viewer's current font style is specified.
 */
public interface IItemFontProvider
{
  /**
   * An instance object used to specify a normal version of the viewer's font:
   *<pre>
   *  font:////normal
   *</pre>
   */
  URI NORMAL_FONT = URI.createURI("font:////normal");

  /**
   * An instance object used to specify a bold version of the viewer's font:
   *<pre>
   *  font:////bold
   *</pre>
   */
  URI BOLD_FONT = URI.createURI("font:////bold");

  /**
   * An instance object used to specify an italic version of the viwer's font:
   *<pre>
   *  font:////italic
   *</pre>
   */
  URI ITALIC_FONT = URI.createURI("font:////italic");

  /**
   * An instance object used to specify an bold italic version of the viwer's font:
   *<pre>
   *  font:////bold+italic
   *</pre>
   */
  URI BOLD_ITALIC_FONT = URI.createURI("font:////bold+italic");

  /**
   * This does the same thing as IFontProvider.getFont, 
   * it fetches the font specific to this object instance.
   */
  public Object getFont(Object object);
}
