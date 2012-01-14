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


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.java.JClass;
import org.eclipse.emf.java.JCompilationUnit;
import org.eclipse.emf.java.JPackage;
import org.eclipse.emf.java.JavaFactory;


/**
 */
public class JavaPackageResourceImpl extends ResourceImpl 
{
  public JavaPackageResourceImpl(URI uri)
  {
    super(uri);

    // The no-name compilation unit
    //
    JCompilationUnit jCompilationUnit = JavaFactory.eINSTANCE.createJCompilationUnit();
    jCompilationUnit.setName(".class");
    getContents().add(jCompilationUnit);
  }

  @Override
  protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException
  {
    // Ignore
  }

  @Override
  protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public EObject getEObject(String uriFragment)
  {
    return super.getEObject(uriFragment);
  }

  @Override
  protected EObject getEObjectForURIFragmentRootSegment(String uriFragmentRootSegment)
  {
    for (Object object : getContents())
    {
      if (object instanceof JPackage)
      {
        JPackage jPackage = (JPackage)object;
        if (uriFragmentRootSegment.equals(jPackage.getName()))
        {
          return jPackage;
        }
      }
    }

    return null;
  }

  @Override
  protected EObject getEObject(List<String> uriFragmentPath)
  {
    Iterator<String> fragments = uriFragmentPath.iterator(); 
    String packageName = !fragments.hasNext() ? "" : (String)fragments.next();

    EObject eObject = getEObjectForURIFragmentRootSegment(packageName);
    if (fragments.hasNext())
    {
      JPackage jPackage = (JPackage)eObject;
      String typeName = fragments.next();
      if (eObject == null || (eObject = ((InternalEObject)eObject).eObjectForURIFragmentSegment(typeName)) == null)
      {
        JClass jClass = demandLoad(packageName, typeName);
        if (jClass != null)
        {
          if (jClass.getUnit() == null)
          {
            ((JCompilationUnit)getContents().get(0)).getTypes().add(jClass);
          }

          if (jPackage == null)
          {
            jPackage = JavaFactory.eINSTANCE.createJPackage();
            jPackage.setName(packageName);
            getContents().add(jPackage);
          }
          jPackage.getTypes().add(jClass);
          eObject = jClass;
        }
      }

      while (fragments.hasNext() && eObject != null)
      {
        eObject = ((InternalEObject)eObject).eObjectForURIFragmentSegment(fragments.next());
      }
    }
    else if (eObject == null)
    {
      JPackage jPackage = JavaFactory.eINSTANCE.createJPackage();
      jPackage.setName(packageName);
      getContents().add(jPackage);
      eObject = jPackage;
    }

    return eObject;
  }

  protected JClass demandLoad(String packageName, String typeName)
  {
    int index = typeName.indexOf("["); 
    String className;
    if (index == -1)
    {
      className = packageName.length() == 0 ? typeName : packageName + "." + typeName;
    }
    else
    {
      int dimensions = (typeName.length() - index) / 2;
      typeName = typeName.substring(0, index);
      if ("java.lang".equals(packageName))
      {
        if (typeName.equals("int"))
        {
          className = "I";
        }
        else if (typeName.equals("boolean"))
        {
          className = "B";
        }
        else if (typeName.equals("char"))
        {
          className = "C";
        }
        else if (typeName.equals("double"))
        {
          className = "D";
        }
        else if (typeName.equals("float"))
        {
          className = "F";
        }
        else if (typeName.equals("byte"))
        {
          className = "B";
        }
        else if (typeName.equals("short"))
        {
          className = "S";
        }
        else if (typeName.equals("long"))
        {
          className = "J";
        }
        else
        {
          className = "Ljava.lang." + typeName + ";";
        }
      }
      else
      {
        className = "L" + (packageName.length() == 0 ? typeName + ";" :  packageName + "." + typeName + ";");
      }

      while (--dimensions >= 0)
      {
        className = "[" + className;
      }
    }
    try
    {
      Class<?> theClass = null;
      if ("java.lang".equals(packageName) && index == -1)
      {
        if (typeName.equals("int"))
        {
          theClass = Integer.TYPE;
        }
        else if (typeName.equals("boolean"))
        {
          theClass = Boolean.TYPE;
        }
        else if (typeName.equals("char"))
        {
          theClass = Character.TYPE;
        }
        else if (typeName.equals("double"))
        {
          theClass = Double.TYPE;
        }
        else if (typeName.equals("float"))
        {
          theClass = Float.TYPE;
        }
        else if (typeName.equals("byte"))
        {
          theClass = Byte.TYPE;
        }
        else if (typeName.equals("short"))
        {
          theClass = Short.TYPE;
        }
        else if (typeName.equals("long"))
        {
          theClass = Long.TYPE;
        }
        else if (typeName.equals("void"))
        {
          theClass = Void.TYPE;
        }
        else
        {
          theClass = getClassLoader().loadClass(className);
        }
      }
      else
      {
        theClass = getClassLoader().loadClass(className);
      }

      JClass jClass = JavaFactory.eINSTANCE.createJClass();
      jClass.setJavaClass(theClass);
      return jClass;
    }
    catch (ClassNotFoundException exception)
    {
      // Ignore
    }

    for (String sourceURI : getSourceURIs())
    {
      URI resolvedSourceURI = 
        URI.createURI
          (sourceURI.toString() + (packageName.length() == 0 ? "" : packageName.replace('.','/') + '/')  + typeName + ".java");

      try
      {
        InputStream inputStream = resourceSet.getURIConverter().createInputStream(resolvedSourceURI);
        inputStream.close();

        JClass jClass = (JClass)resourceSet.getEObject(JavaUtil.createJClassProxyURI(resolvedSourceURI), true);
        return jClass;
      }
      catch (IOException exception)
      {
        // Ignore
      }
    }

    return null;
  }

  protected ClassLoader classLoader;

  public ClassLoader getClassLoader()
  {
    if (classLoader ==  null)
    {
      try
      {
        classLoader = 
          new java.net.URLClassLoader
            (new java.net.URL [] 
             { 
             }, 
             null);
      }
      catch (Exception exception)
      {
        exception.printStackTrace();
      }
    }
    return classLoader;
  }

  public void setClassLoader(ClassLoader classLoader)
  {
    this.classLoader = classLoader;
  }

  protected List<String> sourceURIs = new ArrayList<String>();

  public List<String> getSourceURIs()
  {
    return sourceURIs;
  }
}
