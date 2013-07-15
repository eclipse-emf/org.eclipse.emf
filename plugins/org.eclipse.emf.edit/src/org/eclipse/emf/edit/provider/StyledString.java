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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.provider.StyledString.Fragment;

/**
 * A mutable string with styled ranges similar to JFaces's SyledString. All
 * {@link Fragment fragments} mark substrings of the string and do not overlap.
 * Styles are applied using instances of {@link StyledString.Style}. It can be iterated to
 * get all composing fragments.
 * 
 * @author <a href="mailto:mikael.barbero@obeo.fr">Mikael Barbero</a>
 * @since 2.10
 */
public final class StyledString implements Iterable<Fragment>
{
  private final List<Fragment> content = new ArrayList<Fragment>();

  /**
   * Constructs an empty styled string.
   */
  public StyledString()
  {
    // do nothing;
  }
  
  /**
   * Constructs a new styled string with the given text with no associated style.
   */
  public StyledString(String text)
  {
    this(text, StyledString.Style.NO_STYLE);
  }

  /**
   * Constructs a new styled string with the given text associated with the given style.
   */
  public StyledString(String text, StyledString.Style style)
  {
    append(text, style);
  }

  /**
  * Returns the string of this {@link StyledString}.
  * 
  * @return the current string of this {@link StyledString}.
  */
  public String getString()
  {
    StringBuilder result = new StringBuilder();
    for (Fragment styledString : this)
    {
      result.append(styledString.getString());
    }
    return result.toString();
  }

  /**
   * Appends a string with a style to the {@link StyledString}. The appended
   * string will be not be styled.
   * 
   * @param string
   *          the string to append
   * @return returns a reference to this object
   */
  public StyledString append(String string)
  {
    content.add(new Fragment(string, StyledString.Style.NO_STYLE));
    return this;
  }

  /**
   * Appends a string with a style to the {@link StyledString}. The appended
   * string will be styled with the given {@code style}.
   * 
   * @param string
   *          the string to append
   * @param style
   *          the style to use for styling the string to append or
   *          <code>null</code> if no style should be associated with the
   *          appended string.
   * @return returns a reference to this object
   */
  public StyledString append(String string, StyledString.Style style)
  {
    content.add(new Fragment(string, style));
    return this;
  }

  /**
   * Appends a string with styles to the {@link StyledString}.
   * 
   * @param styledString
   *          the string to append
   * @return returns a reference to this object
   */
  public StyledString append(StyledString styledString)
  {
    content.addAll(styledString.content);
    return this;
  }
  
  public Iterator<Fragment> iterator()
  {
    return content.iterator();
  }

  /**
   * An immutable string styled with a given {@link StyledString.Style}.
   * 
   * @since 2.10
   */
  public static final class Fragment
  {
    private final String string;

    private final StyledString.Style style;

    private Fragment(String string, StyledString.Style style)
    {
      this.string = string;
      this.style = style;
    }

    /**
     * Returns the string of this {@link Fragment}.
     * 
     * @return the current string of this {@link Fragment}.
     */
    public String getString()
    {
      return string;
    }

    /**
     * Returns the style of this {@link Fragment}.
     * 
     * @return the current style of this {@link Fragment}.
     */
    public StyledString.Style getStyle()
    {
      return style;
    }
  }

  /**
   * An immutable object describing how a {@link StyledString} should be style.
   * <p>
   * It must be constructed with the {@link Style#newBuilder() built-in builder
   * pattern}.
   * 
   * @since 2.10
   */
  public static final class Style
  {

    /**
     * Constants describing that a String with the given style should not be
     * styled (null object).
     */
    public static final Style NO_STYLE = new Style(null, null, null, false,
        null, null, null, null, null, "NO_STYLE");

    /**
     * A built-in style that will eventually be mapped to
     * org.eclipse.jface.viewers.StyledString.QUALIFIER_STYLER.
     */
    public static final Style QUALIFIER_STYLER = new Style(null, null, null,
        false, null, null, null, null, null, "QUALIFIER_STYLER");

    /**
     * A built-in style that will eventually be mapped to
     * org.eclipse.jface.viewers.StyledString.COUNTER_STYLER.
     */
    public static final Style COUNTER_STYLER = new Style(null, null, null,
        false, null, null, null, null, null, "COUNTER_STYLER");

    /**
     * A built-in style that will eventually be mapped to
     * org.eclipse.jface.viewers.StyledString.DECORATIONS_STYLER.
     */
    public static final Style DECORATIONS_STYLER = new Style(null, null, null,
        false, null, null, null, null, null, "DECORATIONS_STYLER");

    private final URI font;

    private final URI backgroundColor;

    private final URI foregroundColor;

    private final boolean isStrikedout;

    private final URI strikeoutColor;

    private final UnderLineStyle underlineStyle;

    private final URI underlineColor;

    private final BorderStyle borderStyle;

    private final URI borderColor;

    private String toString;

    private Style(URI font, URI backgroundColor, URI foregroundColor,
        boolean isStrikedout, URI strikeoutColor,
        UnderLineStyle underlineStyle, URI underlineColor,
        BorderStyle borderStyle, URI borderColor, String toString)
    {
      this(font, backgroundColor, foregroundColor, isStrikedout, strikeoutColor, 
          underlineStyle, underlineColor, borderStyle, borderColor);
      this.toString = toString;
    }
   
    private Style(URI font, URI backgroundColor, URI foregroundColor,
        boolean isStrikedout, URI strikeoutColor,
        UnderLineStyle underlineStyle, URI underlineColor,
        BorderStyle borderStyle, URI borderColor)
    {
      this.font = font;
      this.backgroundColor = backgroundColor;
      this.foregroundColor = foregroundColor;
      this.isStrikedout = isStrikedout;
      this.strikeoutColor = strikeoutColor;
      this.underlineStyle = underlineStyle;
      this.underlineColor = underlineColor;
      this.borderStyle = borderStyle;
      this.borderColor = borderColor;
    }
  
    public URI getFont()
    {
      return font;
    }
  
    public URI getBackgoundColor()
    {
      return backgroundColor;
    }
  
    public URI getForegroundColor()
    {
      return foregroundColor;
    }
  
    public boolean isStrikedout()
    {
      return isStrikedout;
    }
  
    public URI getStrikeoutColor()
    {
      return strikeoutColor;
    }
  
    public UnderLineStyle getUnderlineStyle()
    {
      return underlineStyle;
    }
  
    public URI getUnderlineColor()
    {
      return underlineColor;
    }
  
    public BorderStyle getBorderStyle()
    {
      return borderStyle;
    }
  
    public URI getBorderColor()
    {
      return borderColor;
    }
  
    /**
     * All the supported underline styles.
     */
    public static enum UnderLineStyle
    {
      NONE, SINGLE, DOUBLE, SQUIGGLE, ERROR, LINK;
    }
  
    /**
     * All the supported border styles.
     */
    public static enum BorderStyle
    {
      NONE, SOLID, DOT, DASH;
    }
  
    @Override
    public String toString()
    {
      if (toString == null) 
      {
        StringBuilder sb = new StringBuilder(Style.class.getSimpleName()).append('(');
        sb.append("font: ").append(font);
        sb.append(", backgroundColor: ").append(backgroundColor);
        sb.append(", foregroundColor: ").append(foregroundColor);
        if (isStrikedout)
        {
          sb.append(", strikedout(color: ").append(strikeoutColor).append(')');
        }
        if (underlineStyle != UnderLineStyle.NONE)
        {
          sb.append(", underlined(style: ").
            append(underlineStyle).
            append(", color: ").
            append(underlineColor).
            append(')');
        }
        if (borderStyle != BorderStyle.NONE)
        {
          sb.append(", border(style: ").
            append(borderStyle).
            append(", color: ").
            append(borderColor).
            append(')');
        }
        
        toString = sb.append(')').toString();
      }
      return toString;
    }
    
    /**
     * Returns a new builder instance to create a custom style.
     * 
     * @return a new builder instance to create a custom style.
     */
    public static Builder newBuilder()
    {
      return new Builder();
    }
  
    /**
     * Builder pattern to create a custom class. Methods calls can be chained as
     * all methods returned its instance.
     */
    public static final class Builder
    {
      private static final URI BLACK = URI.createURI("color://rgb/0/0/0"); //$NON-NLS-1$
  
      private URI font;
  
      private URI backgroundColor;
  
      private URI foregroundColor;
  
      private boolean isStrikedout;
  
      private URI strikeoutColor;
  
      private UnderLineStyle underlineStyle = UnderLineStyle.NONE;
  
      private URI underlineColor;
  
      private BorderStyle borderStyle = BorderStyle.NONE;
  
      private URI borderColor;
  
      private Builder() 
      {
        // prevents direct instantiation
      }
      
      /**
       * @param font
       *          the font to set
       * @return
       */
      public Builder setFont(URI font)
      {
        this.font = font;
        return this;
      }
  
      /**
       * @param backgroundColor
       *          the backgroundColor to set
       * @return
       */
      public Builder setBackgroundColor(URI backgroundColor)
      {
        this.backgroundColor = backgroundColor;
        return this;
      }
  
      /**
       * @param foregroundColor
       *          the forregroundColor to set
       * @return
       */
      public Builder setForegroundColor(URI foregroundColor)
      {
        this.foregroundColor = foregroundColor;
        return this;
      }
  
      /**
       * @param isStrikedout
       *          the isStrikedout to set
       * @return
       */
      public Builder setStrikedout(boolean isStrikedout)
      {
        this.isStrikedout = isStrikedout;
        if (strikeoutColor == null)
        {
          strikeoutColor = BLACK;
        }
        return this;
      }
  
      /**
       * @param strikeoutColor
       *          the strikeoutColor to set
       * @return
       */
      public Builder setStrikeoutColor(URI strikeoutColor)
      {
        this.strikeoutColor = strikeoutColor;
        isStrikedout = true;
        return this;
      }
  
      /**
       * @param borderColor
       *          the borderColor to set
       * @return
       */
      public Builder setBorderColor(URI borderColor)
      {
        this.borderColor = borderColor;
        if (borderStyle == BorderStyle.NONE)
        {
          borderStyle = BorderStyle.SOLID;
        }
        return this;
      }
  
      /**
       * @param borderStyle
       *          the borderStyle to set
       * @return
       */
      public Builder setBorderStyle(BorderStyle borderStyle)
      {
        this.borderStyle = borderStyle;
        if (borderColor == null)
        {
          borderColor = BLACK;
        }
        return this;
      }
  
      /**
       * @param underlineColor
       *          the underlineColor to set
       * @return
       */
      public Builder setUnderlineColor(URI underlineColor)
      {
        this.underlineColor = underlineColor;
        if (underlineStyle == UnderLineStyle.NONE)
        {
          underlineStyle = UnderLineStyle.SINGLE;
        }
        return this;
      }
  
      /**
       * @param underlineStyle
       *          the underlineStyle to set
       * @return
       */
      public Builder setUnderlineStyle(UnderLineStyle underlineStyle)
      {
        this.underlineStyle = underlineStyle;
        if (underlineColor == null)
        {
          underlineColor = BLACK;
        }
        return this;
      }
  
      /**
       * Creates and return a new Style object as configured with the builder.
       * @return
       */
      public Style toStyle()
      {
        return new Style(font, backgroundColor, foregroundColor, isStrikedout,
            strikeoutColor, underlineStyle, underlineColor, borderStyle,
            borderColor);
      }
    }
  }
}
