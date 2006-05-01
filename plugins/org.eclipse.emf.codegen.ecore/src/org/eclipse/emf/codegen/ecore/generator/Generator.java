/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: Generator.java,v 1.1 2006/05/01 10:25:19 davidms Exp $
 */
package org.eclipse.emf.codegen.ecore.generator;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;


/**
 * @since 2.2.0
 */
public class Generator
{
  public static class Options
  {
    // Text, Java
    public String redirectionPattern;
    public boolean forceOverwrite;

    // Java
    public boolean dynamicTemplates;
    public String[] templatePath;
    public String mergerFacadeHelperClass;
    public String mergeRulesURI;
    public boolean codeFormatting;
    public Map codeFormatterOptions;
    //DMS Instead of URIConverter? We can use this for package lookup, e.g. in CodeGenEcorePlugin.GenericDescriptor.AdapterFactory.  
    public ResourceSet resourceSet;

    //DMS data collection for extensibility?
    public Object[] data;

    public Options()
    {
    }
  }

  protected GeneratorAdapterFactory.Descriptor.Registry adapterFactoryDescriptorRegistry;
  protected Map packageIDToAdapterFactories;

  protected Object input;
  protected Options options;
  protected boolean initializeNeeded = true;

  protected JControlModel jControlModel;

  public Generator()
  {
  }

  public Generator(GeneratorAdapterFactory.Descriptor.Registry adapterFactoryDescriptorRegistry)
  {
    this.adapterFactoryDescriptorRegistry = adapterFactoryDescriptorRegistry;
  }

  public Object getInput()
  {
    return input;
  }

  public void setInput(Object input)
  {
    this.input = input;
    initialize();
    initializeNeeded = true;
  }

  public void requestInitialize()
  {
    initializeNeeded = true;
  }

  protected void initialize()
  {
    for (Iterator i = getAdapterFactories(input).iterator(); i.hasNext(); )
    {
      ((GeneratorAdapterFactory)i.next()).initialize(input);
    }
  }

  public Options getOptions()
  {
    if (options == null)
    {
      options = new Options();
    }
    return options;
  }

  public JControlModel getJControlModel()
  {
    if (jControlModel == null)
    {
      jControlModel = new JControlModel();
    }

    String facadeHelperClass = options.mergerFacadeHelperClass;
    if (jControlModel.getFacadeHelper() == null || !jControlModel.getFacadeHelper().getClass().getName().equals(facadeHelperClass))
    {
      FacadeHelper facadeHelper = CodeGenUtil.instantiateFacadeHelper(facadeHelperClass); 
      jControlModel.initialize(facadeHelper, options.mergeRulesURI);
    }
    return jControlModel;
  }

  public GeneratorAdapterFactory.Descriptor.Registry getAdapterFactoryDescriptorRegistry()
  {
    if (adapterFactoryDescriptorRegistry == null)
    {
      adapterFactoryDescriptorRegistry =
        new GeneratorAdapterFactory.Descriptor.DelegatingRegistry(GeneratorAdapterFactory.Descriptor.Registry.INSTANCE);
    }
    return adapterFactoryDescriptorRegistry;
  }

  protected Collection getAdapterFactories(Object object)
  {
    if (packageIDToAdapterFactories == null)
    {
      packageIDToAdapterFactories = new HashMap();
    }

    Collection result = new ArrayList();

    for (Iterator i = getPackageIDs(object).iterator(); i.hasNext(); )
    {
      String packageID = (String)i.next();
      Collection adapterFactories = (Collection)packageIDToAdapterFactories.get(packageID);
      if (adapterFactories == null)
      {
        Collection descriptors = getAdapterFactoryDescriptorRegistry().getDescriptors(packageID);
        adapterFactories = new ArrayList(descriptors.size());
        for (Iterator j = descriptors.iterator(); j.hasNext(); )
        {
          GeneratorAdapterFactory adapterFactory = ((GeneratorAdapterFactory.Descriptor)j.next()).createAdapterFactory();
          adapterFactory.setGenerator(this);
          adapterFactories.add(adapterFactory);
        }
        packageIDToAdapterFactories.put(packageID, adapterFactories);
      }
      result.addAll(adapterFactories);
    }
    return result;
  }

  protected Collection getPackageIDs(Object object)
  {
    List result = new UniqueEList();

    if (object instanceof EObject)
    {
      EClass eClass = ((EObject)object).eClass();
      for (Iterator i = eClass.getEAllSuperTypes().iterator(); i.hasNext(); )
      {
        result.add(((EClass)i.next()).getEPackage().getNsURI());
      }
      result.add(eClass.getEPackage().getNsURI());
    }
    else
    {
      Class javaClass = object.getClass();

      for (Iterator i = getAllSuperTypes(javaClass).iterator(); i.hasNext(); )
      {
        result.add(((Class)i.next()).getPackage().getName());
      }
      result.add(javaClass.getPackage().getName());
    }
    return result;
  }

  protected Collection getAllSuperTypes(Class javaClass)
  {
    List result = new ArrayList();
    if (!javaClass.isInterface())
    {
      Class superclass = javaClass.getSuperclass();
      result.add(getAllSuperTypes(superclass));
      result.add(superclass);
    }
    Class[] interfaces = javaClass.getInterfaces();
    for (int i = 0; i < interfaces.length; i++)
    {
      result.add(getAllSuperTypes(interfaces[i]));
      result.add(interfaces[i]);
    }
    return result;
  }

  protected Collection getAdapters(Object object)
  {
    Collection adapterFactories = getAdapterFactories(object);
    List result = new ArrayList(adapterFactories.size());

    for (Iterator i = adapterFactories.iterator(); i.hasNext(); )
    {
      AdapterFactory adapterFactory = (AdapterFactory)i.next();
      {
        if (adapterFactory.isFactoryForType(GeneratorAdapter.class))
        {
          Object adapter = adapterFactory.adapt(object, GeneratorAdapter.class);
          if (adapter != null)
          {
            result.add(adapter);
          }
        }
      }
    }
    return result;
  }

  private static class GeneratorData
  {
    public Object object;
    public GeneratorAdapter adapter;

    public GeneratorData(Object object, GeneratorAdapter adapter)
    {
      this.object = object;
      this.adapter = adapter;
    }
  }

  private GeneratorData[] getGeneratorData(Object object, Object projectType, boolean forGenerate)
  {
    List result = new ArrayList();
    result.add(object);

    // Since we're invoking plugged-in code, we must be defensive against cycles.
    //
    Set objects = new HashSet();

    for (int i = 0; i < result.size(); )
    {
      Object o = result.get(i);

      Collection adapters = getAdapters(o);
      result.remove(i);
      if (!adapters.isEmpty())
      {
        for (Iterator adaptersIter = adapters.iterator(); adaptersIter.hasNext(); )
        {
          GeneratorAdapter adapter = (GeneratorAdapter)adaptersIter.next();
          Collection children = forGenerate ?
            adapter.getGenerateChildren(o, projectType) : 
            adapter.getCanGenerateChildren(o, projectType);
  
          for (Iterator childrenIter = children.iterator(); childrenIter.hasNext(); )
          {
            Object child = childrenIter.next();
            if (objects.add(child))
            {
              result.add(child);
            }
          }
          result.add(i++, new GeneratorData(o, adapter));
        }
      }
    }
    return (GeneratorData[])result.toArray(new GeneratorData[result.size()]);
  }

  public boolean canGenerate(Object object, Object projectType)
  {
    GeneratorData[] data = getGeneratorData(object, projectType, false);
    for (int i = 0; i < data.length; i++)
    {
      if (data[i].adapter.canGenerate(data[i].object, projectType))
      {
        return true;
      }
    }
    return false;
  }

  public Diagnostic generate(Object object, Object projectType, Monitor monitor)
  {
    return generate(object, projectType, null, monitor);
  }

  //DMS order of last two args?
  public Diagnostic generate(Object object, Object projectType, String projectTypeName, Monitor monitor)
  {
    try
    {
      String message = projectTypeName != null ?
        CodeGenEcorePlugin.INSTANCE.getString("_UI_Generating_message", new Object[] { projectTypeName }) :
        CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingCode_message");
      BasicDiagnostic result = new BasicDiagnostic(CodeGenEcorePlugin.ID, 0, message, null);

      GeneratorData[] data = getGeneratorData(object, projectType, true);
      monitor.beginTask("", data.length + 2);
      monitor.subTask(message);

      // Initialization is deferred until adapters are attached to all the objects of interest and we're
      // about to ask them to generate.
      //
      if (initializeNeeded)
      {
        initializeNeeded = false;
        initialize();
      }

      // Give all generator adapters the chance to do setup work.
      //
      int preIndex = 0;
      for (; preIndex < data.length && canContinue(result); preIndex++)
      {
        result.add(data[preIndex].adapter.preGenerate(data[preIndex].object, projectType));
      }
      monitor.worked(1);

      // Invoke generator adapters for each object.
      //
      for (int i = 0; i < data.length && canContinue(result); i++)
      {
        result.add(data[i].adapter.generate(data[i].object, projectType, CodeGenUtil.createMonitor(monitor, 1)));
        if (monitor.isCanceled())
        {
          result.add(Diagnostic.CANCEL_INSTANCE);
        }
      }

      // Give all generator adapters the chance to do teardown.
      //
      for (int i = 0; i < preIndex; i++)
      {
        result.add(data[i].adapter.postGenerate(data[i].object, projectType));
      }
      return result;
    }
    finally
    {
      monitor.done();
    }
  }

  // Stop only on cancel.
  //
  protected boolean canContinue(Diagnostic diagnostic)
  {
    return diagnostic.getSeverity() != Diagnostic.CANCEL;
  }

  public void dispose()
  {
    if (packageIDToAdapterFactories != null)
    {
      for (Iterator i = packageIDToAdapterFactories.values().iterator(); i.hasNext(); )
      {
        for (Iterator j = ((Collection)i.next()).iterator(); j.hasNext(); )
        {
          ((GeneratorAdapterFactory)j.next()).dispose();
        }
      }
    }
  }
}
