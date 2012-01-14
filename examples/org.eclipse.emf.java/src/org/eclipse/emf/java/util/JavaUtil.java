/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.java.util;


import java.lang.reflect.Modifier;

import org.eclipse.jdt.core.Flags;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.java.JClass;
import org.eclipse.emf.java.JMethod;
import org.eclipse.emf.java.JPackage;
import org.eclipse.emf.java.JVisibility;
import org.eclipse.emf.java.JavaFactory;


/**
 * This class contains some convenient static functions for working with Java objects.
 */
public class JavaUtil
{
  public static final String JAVA_PACKAGE_RESOURCE = "http:///org/eclipse/emf/java/.packages";
  public static final URI JAVA_PACKAGE_RESOURCE_URI = URI.createURI(JAVA_PACKAGE_RESOURCE);

  public static URI createJPackageProxyURI(String packageName)
  {
    return JAVA_PACKAGE_RESOURCE_URI.appendFragment("/" + packageName);
  }

  public static JPackage createJPackageProxy(String packageName)
  {
    JPackage jPackage = JavaFactory.eINSTANCE.createJPackage();
    ((InternalEObject)jPackage).eSetProxyURI(createJPackageProxyURI(packageName));
    return jPackage;
  }

  public static URI createJClassProxyURI(Class<?> javaClass)
  {
    String suffix = "";
    while (javaClass.getComponentType() != null)
    {
      suffix += "/[]";
      javaClass = javaClass.getComponentType();
    }
    String className = javaClass.getName();
    String packageName = "";
    int index = className.lastIndexOf(".");
    if (index != -1)
    {
      packageName = className.substring(0, index);
      className = className.substring(index + 1);
    }
    if (javaClass.isPrimitive())
    {
      packageName = "java.lang";
    }

    return JAVA_PACKAGE_RESOURCE_URI.appendFragment("/" + packageName + "/" + className.replace('$','/') + suffix);
  }

  public static JClass createJClassProxy(Class<?> javaClass)
  {
    JClass jClass = JavaFactory.eINSTANCE.createJClass();
    ((InternalEObject)jClass).eSetProxyURI(createJClassProxyURI(javaClass));
    return jClass;
  }

  public static URI createJClassProxyURI(String className)
  {
    StringBuffer result = new StringBuffer("/");
    result.append(className);
    int dotIndex = className.lastIndexOf(".");
    if (dotIndex != -1)
    {
      result.setCharAt(dotIndex + 1, '/');
    }

    for (int i = dotIndex + 2; i < result.length(); ++i)
    {
      switch (result.charAt(i))
      {
        case '$':
        {
          result.setCharAt(i, '/');
          break;
        }
        case '[':
        {
          result.insert(i++, '/');
          break;
        }
      }
    }

    if (dotIndex == -1)
    {
      result.insert(0, '/');
    }

    return JAVA_PACKAGE_RESOURCE_URI.appendFragment(result.toString());
  }

  public static JClass createJClassProxy(String className)
  {
    JClass jClass = JavaFactory.eINSTANCE.createJClass();
    ((InternalEObject)jClass).eSetProxyURI(createJClassProxyURI(className));
    return jClass;
  }

  public static URI createJClassProxyURI(URI javaSourceURI)
  {
    return javaSourceURI.appendFragment("//@types.0");
  }

  public static JClass createJClassProxy(URI javaSourceURI)
  {
    JClass jClass = JavaFactory.eINSTANCE.createJClass();
    ((InternalEObject)jClass).eSetProxyURI(createJClassProxyURI(javaSourceURI));
    return jClass;
  }

  public static JVisibility getFlagVisibility(int flags)
  {
    if ((flags & Flags.AccPublic) != 0)
    {
      return JVisibility.PUBLIC_LITERAL;
    }
    else if ((flags & Flags.AccProtected) != 0)
    {
      return JVisibility.PROTECTED_LITERAL;
    }
    else if ((flags & Flags.AccPrivate) != 0)
    {
      return JVisibility.PRIVATE_LITERAL;
    }
    else
    {
      return JVisibility.PACKAGE_LITERAL;
    }
  }

  public static JVisibility getModifierVisibility(int modifiers)
  {
    if (Modifier.isPublic(modifiers))
    {
      return JVisibility.PUBLIC_LITERAL;
    }
    else if (Modifier.isProtected(modifiers))
    {
      return JVisibility.PROTECTED_LITERAL;
    }
    else if (Modifier.isPrivate(modifiers))
    {
      return JVisibility.PRIVATE_LITERAL;
    }
    else
    {
      return JVisibility.PACKAGE_LITERAL;
    }
  }

  /**
   * Returns whether the <code>name</code> is a prefix of the <code>fullName</code>.
   * @return whether the <code>name</code> is a prefix of the <code>fullName</code>.
   */
  public static boolean isPrefixOf(String name, String fullName)
  {
    if (fullName.startsWith(name))
    {
      int nameLength = name.length();
      int fullNameLength = fullName.length();
      if (nameLength == fullNameLength)
      {
        return true;
      }
      else if (nameLength > fullNameLength)
      {
        return fullName.charAt(nameLength) == '.';
      }
      else
      {
        return false;
      }
    }
    else
    {
      return false;
    }
  }
  
  /**
   * Separates the type argument from the type.  The first position of the
   * returned array is always the raw type and the second is either the type argument
   * without the outermost '&lt;' and '&gt;' or <code>null</null>.
   * @param typeName
   * @return a String array with length == 2
   */
  public static String[] separateTypeArgument(String typeName)
  {
    String typeArgument = null;
    int ltIndex = typeName.indexOf('<');
    if (ltIndex > 0)
    {
      int gtIndex = typeName.lastIndexOf('>');
      if (gtIndex > ltIndex+1)
      {
        typeArgument = typeName.substring(ltIndex+1, gtIndex).trim();
        typeName = typeName.substring(0, ltIndex).trim();
      }
    }
    return new String[]{typeName, typeArgument};
  }   

  public static class StandAloneTest
  {
    /**
     * Executes a stand-alone test.
     * @param arguments an array of Strings from the command line.
     */
    public static void main(String arguments[])
    {
      ResourceSet resourceSet = new ResourceSetImpl();
      resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("packages", new JavaPackageResourceFactoryImpl());

      JavaPackageResourceImpl javaPackageResource = (JavaPackageResourceImpl)resourceSet.getResource(JAVA_PACKAGE_RESOURCE_URI, true);
      javaPackageResource.setClassLoader(StandAloneTest.class.getClassLoader());

      JClass thisClass = 
        (JClass)resourceSet.getEObject
          (JAVA_PACKAGE_RESOURCE_URI.appendFragment("/org.eclipse.emf.java.util/JavaUtil/StandAloneTest"), true);


      System.out.println("All Methods of " + thisClass.getQualifiedName());
      for (JMethod jMethod : thisClass.getAllMethods())
      {
        System.out.println("  " + jMethod.getQualifiedName());
      }

      System.exit(1);
    }
  }
}
