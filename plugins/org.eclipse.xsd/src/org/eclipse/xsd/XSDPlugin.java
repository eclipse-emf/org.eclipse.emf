/**
 * <copyright>
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDPlugin.java,v 1.8 2008/04/22 13:36:03 emerks Exp $
 */
package org.eclipse.xsd;


import java.util.Comparator;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;


/**
 * The <b>Plugin</b> for the model.
 * The XML Schema model needs to be able to run 
 * within an Eclipse workbench,
 * within a headless Eclipse workspace,
 * or just stand-alone as part of some other application.
 * To support this, all access is directed to the static methods,
 * which can redirect the service as appopriate to the runtime.
 * During stand-alone invocation no plugin initialization takes place.
 * In this case you will need the resources jar on the class path.
 * @see #getBaseURL()
 */
public final class XSDPlugin extends EMFPlugin 
{
  /**
   * The singleton instance of the plugin.
   */
  public static final XSDPlugin INSTANCE = new XSDPlugin();

  /**
   * The one instance of this class.
   */
  static private Implementation plugin;

  /**
   * Creates the singleton instance.
   */
  private XSDPlugin()
  {
    super(new ResourceLocator[] {});
  }
  
  /**
   * A comparator for strings which does collation specific to the current locale by default.
   */
  public static class StringComparator implements Comparator<String>
  {
    private Comparator<String> comparator = CommonPlugin.INSTANCE.getComparator();
  
    public int compare(String s1, String s2)
    {
      return comparator.compare(s1, s2);
    }
  }

  /**
   * A comparator for strings which does collation based simply on unicode values.
   */
  public static class UnicodeStringComparator extends StringComparator
  {
    @Override
    public int compare(String s1, String s2)
    {
      return s1.compareTo(s2);
    }
  }

  /**
   * The current comparator.
   */
  private StringComparator comparator = new StringComparator();

  /**
   * Returns the comparator that will be used to sort namespaces and names.
   * The default comparator is specific to the current locale at startup.
   * Changes to the comparator after it's already in use will be ignored.
   * @return the comparator that will be used to sort namespaces and names.
   */
  public StringComparator getComparator()
  {
    return comparator;
  }

  /**
   * Sets the comparator that will be used to sort namespaces and names.
   * Changes to the comparator after it's already in use will be ignored.
   * @param comparator the comparator that will be used to sort namespaces and names.
   */
  public void setComparator(StringComparator comparator)
  {
    this.comparator = comparator;
  }

  /*
   * Javadoc copied from base class.
   */
  @Override
  public ResourceLocator getPluginResourceLocator()
  {
    return plugin;
  }

  /**
   * Returns the singleton instance of the Eclipse plugin.
   * @return the singleton instance.
   */
  public static Implementation getPlugin()
  {
    return plugin;
  }

  /**
   * The actual implementation of the Eclipse <b>Plugin</b>.
   */
  public static class Implementation extends EMFPlugin.EclipsePlugin
  {
    /**
     * Creates an instance.
     */
    public Implementation()
    {
      super();

      // Remember the static instance.
      //
      plugin = this;
    }
  }
}
