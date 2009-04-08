/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * ImporterUtil.java,v 1.9 2005/12/14 07:48:49 marcelop Exp
 */
package org.eclipse.emf.importer.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;


/**
 * Utility methods and classes.  This class cannot import UI code because it is used on headless
 * scenarios. 
 * 
 * @since 2.1.0
 */
public class ImporterUtil
{
  public static String validPluginID(String base)
  {
    StringBuffer sb = new StringBuffer(base);
    for (int i = sb.length() - 1; i >= 0; i--)
    {
      char c = sb.charAt(i);
      if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') || c == '_' || c == '-' || c == '.')
      {
        //do nothing
      }
      else if (c == ' ')
      {
        sb.setCharAt(i, '_');
      }
      else
      {
        sb.deleteCharAt(i);
      }
    }
    return sb.length() == 0 ? "_": sb.toString();
  }
  
  /**
   * <p>Removes any GenPackage from <tt>genPackages</tt> that has the same NSURI of a
   * genPackage in <tt>genPackagesToAdd</tt>.</p>
   * 
   * <p>After dealing with the NSURI, this method calls <tt>genPackages.addAll()</tt> which is expected to 
   * perform a "contains" check to ensure the uniqueness of the list's elements.</p> 
   * 
   * @param genPackages
   * @param genPackagesToAdd
   */
  public static void addUniqueGenPackages(List<GenPackage> genPackages, List<GenPackage> genPackagesToAdd)
  {
    if (!genPackagesToAdd.isEmpty())
    {      
      if (!genPackages.isEmpty())
      {
        Set<String> nsURIs = new HashSet<String>();
        for (GenPackage genPackage : genPackagesToAdd)
        {
          String nsURI = genPackage.getNSURI();
          nsURIs.add(nsURI);
        }
        
        for (Iterator<GenPackage> i = genPackages.iterator(); i.hasNext();)
        {
          GenPackage genPackage = i.next();
          if (nsURIs.contains(genPackage.getNSURI()) && !genPackagesToAdd.contains(genPackage))
          {
            i.remove();
          }
        }
      }
      
      // This will not add duplicates.
      //
      genPackages.addAll(genPackagesToAdd);
    }
  }
}
