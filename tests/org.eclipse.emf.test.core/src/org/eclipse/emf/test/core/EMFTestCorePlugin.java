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
 * $Id: EMFTestCorePlugin.java,v 1.8 2004/08/20 22:58:27 marcelop Exp $
 */
package org.eclipse.emf.test.core;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;

public class EMFTestCorePlugin 
extends Plugin
{
    private static EMFTestCorePlugin instance;
    
    public EMFTestCorePlugin()
    {
        super();
        instance = this;
    }

    public static EMFTestCorePlugin getPlugin()
    {
        return instance;
    }

    public String getPluginDirectory()
    {
      try
      {
        return new java.io.File(Platform.asLocalURL(getBundle().getEntry("/")).getFile()).toString();
      }
      catch (IOException e)
      {
      }
      return "";
    }
    
    public static boolean areEqual(List list1, List list2)
    {
      if (list1 == null) return list2 == null;
      if (list2 == null) return false; 
 
      int size = list1.size(); 
      if (size != list2.size())
      {
        return false;
      }
      
      for(int i=0; i<size; i++)
      {
        if (list1.get(i) != list2.get(i))
        {
          if (list1.get(i) == null || list2.get(i) == null)
          {
            return false;
          }
          else if (!list1.get(i).equals(list2.get(i)))
          {
            return false; 
          }
        }
      }
      
      return true;  
    }
}
