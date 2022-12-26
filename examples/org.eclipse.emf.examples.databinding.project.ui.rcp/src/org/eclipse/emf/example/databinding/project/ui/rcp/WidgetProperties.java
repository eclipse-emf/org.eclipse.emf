/*******************************************************************************
 * Copyright (c) 2008, 2015 Matthew Hall and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 194734)
 *     Matthew Hall - bugs 256543, 213893, 262320, 262946, 264286, 266563, 169876, 306203
 *     Eugen Neufeld - bug 461560
 *     Lars Vogel <Lars.Vogel@vogella.com> - Bug 482486
 ******************************************************************************/
package org.eclipse.emf.example.databinding.project.ui.rcp;


import org.eclipse.jface.databinding.swt.IWidgetListProperty;
import org.eclipse.jface.databinding.swt.IWidgetValueProperty;
import org.eclipse.jface.internal.databinding.swt.ControlBackgroundProperty;
import org.eclipse.jface.internal.databinding.swt.ControlBoundsProperty;
import org.eclipse.jface.internal.databinding.swt.ControlFocusedProperty;
import org.eclipse.jface.internal.databinding.swt.ControlFontProperty;
import org.eclipse.jface.internal.databinding.swt.ControlForegroundProperty;
import org.eclipse.jface.internal.databinding.swt.ControlLocationProperty;
import org.eclipse.jface.internal.databinding.swt.ControlSizeProperty;
import org.eclipse.jface.internal.databinding.swt.ControlVisibleProperty;
import org.eclipse.jface.internal.databinding.swt.WidgetEditableProperty;
import org.eclipse.jface.internal.databinding.swt.WidgetImageProperty;
import org.eclipse.jface.internal.databinding.swt.WidgetItemsProperty;
import org.eclipse.jface.internal.databinding.swt.WidgetMaximumProperty;
import org.eclipse.jface.internal.databinding.swt.WidgetMessageProperty;
import org.eclipse.jface.internal.databinding.swt.WidgetMinimumProperty;
import org.eclipse.jface.internal.databinding.swt.WidgetSelectionProperty;
import org.eclipse.jface.internal.databinding.swt.WidgetSingleSelectionIndexProperty;
import org.eclipse.jface.internal.databinding.swt.WidgetTextProperty;
import org.eclipse.jface.internal.databinding.swt.WidgetTextWithEventsProperty;
import org.eclipse.jface.internal.databinding.swt.WidgetTooltipTextProperty;


/**
 * This class is a bogus copy of an old pre-generics version superseded by <code>org.eclipse.jface.databinding.swt.typed.WidgetProperties</code>.
 * Better to update sample not to use this, but this would be the only reason why we can no longer build with Helios.
 */
@SuppressWarnings("all")
public class WidgetProperties
{
  public static IWidgetValueProperty background()
  {
    return new ControlBackgroundProperty();
  }

  public static IWidgetValueProperty bounds()
  {
    return new ControlBoundsProperty();
  }

  public static IWidgetValueProperty editable()
  {
    return new WidgetEditableProperty();
  }

  public static IWidgetValueProperty focused()
  {
    return new ControlFocusedProperty();
  }

  public static IWidgetValueProperty font()
  {
    return new ControlFontProperty();
  }

  public static IWidgetValueProperty foreground()
  {
    return new ControlForegroundProperty();
  }

  public static IWidgetValueProperty image()
  {
    return new WidgetImageProperty();
  }

  public static IWidgetListProperty items()
  {
    return new WidgetItemsProperty();
  }

  public static IWidgetValueProperty location()
  {
    return new ControlLocationProperty();
  }

  public static IWidgetValueProperty maximum()
  {
    return new WidgetMaximumProperty();
  }

  public static IWidgetValueProperty message()
  {
    return new WidgetMessageProperty();
  }

  public static IWidgetValueProperty minimum()
  {
    return new WidgetMinimumProperty();
  }

  public static IWidgetValueProperty selection()
  {
    return new WidgetSelectionProperty();
  }

  public static IWidgetValueProperty singleSelectionIndex()
  {
    return new WidgetSingleSelectionIndexProperty();
  }

  public static IWidgetValueProperty size()
  {
    return new ControlSizeProperty();
  }

  public static IWidgetValueProperty text()
  {
    return new WidgetTextProperty();
  }

  public static IWidgetValueProperty text(final int event)
  {
    return text(new int []{ event });
  }

  public static IWidgetValueProperty text(int... events)
  {
    return new WidgetTextWithEventsProperty(events.clone());
  }

  public static IWidgetValueProperty tooltipText()
  {
    return new WidgetTooltipTextProperty();
  }

  public static IWidgetValueProperty visible()
  {
    return new ControlVisibleProperty();
  }
}
