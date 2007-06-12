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
 * $Id: Generator.java,v 1.12 2007/06/12 20:56:35 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.generator;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;


/**
 * An extensible code generator that delegates its work to model-specific {@link GeneratorAdapter adapters} created
 * provided by an {@link GeneratorAdapterFactory adapter factory}.
 *
 * <p>Adapter factories are obtained for model objects from a particular EMF or Java package by consulting a 
 * {@link GeneratorAdapterFactory.Descriptor.Registry registry}, which can be local to the generator or globally
 * shared. Local registries typically {@link GeneratorAdapterFactory.Descriptor.DelegatingRegistry delegate} to the
 * global registry, overriding the adapter factories returned for certain packages. Global registration is usually
 * done via the <code>org.eclipse.emf.codegen.ecore.generatorAdapters</code> extension point.
 * 
 * <p>Here is a typical usage example, where we generate code for a standard GenModel-decorated Ecore model:
 * 
 * <pre>
 *   // Globally register the default generator adapter factory for GenModel
 *   // elements (only needed in stand-alone).
 *   // 
 *   GeneratorAdapterFactory.Descriptor.Registry.INSTANCE.addDescriptor
 *     (GenModelPackage.eNS_URI, GenModelGeneratorAdapterFactory.DESCRIPTOR);
 * 
 *   // Create the generator and set the model-level input object.
 *   // 
 *   Generator generator = new Generator();
 *   generator.setInput(genModel);
 * 
 *   // Generator model code.
 *   //
 *   generator.generate
 *     (genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE,
 *      new BasicMonitor.Printing(System.out));
 * </pre>
 * 
 * <p>
 * The adapter factories for the input object handle initializing the generator's {@link #getOptions() options}, and the
 * generator walks the tree of objects defined by the relevant adapters, invoking code generation for each one.
 *
 * @since 2.2.0
 */
public class Generator
{
  private static final boolean SYSOUT_BEGIN_END = false;
  
  /**
   * A set of code generation options that should be shared among the generator, adapter factories and adapters.
   * Additional options may be added to this class in the future.
   */
  public static class Options
  {
    /**
     * A filename redirection pattern for generated files.
     */
    public String redirectionPattern;

    /**
     * Whether to overwrite read-only files.
     */
    public boolean forceOverwrite;

    /**
     * Whether to try to use dynamically compiled templates, in place of supplied static templates.
     */
    public boolean dynamicTemplates;

    /**
     * A path for dynamic templates: ordered list of URIs corresponding to base locations under which to find templates.
     * A single path for all code generation is actually insufficient. This path needs to specified and extended on a
     * per-adapter basis, so this field should not be used.
     * @deprecated org.eclipse.emf.codegen.ecore 2.2.2 Override {@link AbstractGeneratorAdapter#addBaseTemplatePathEntries(List)} instead. 
     * @see AbstractGeneratorAdapter#addBaseTemplatePathEntries(List)
     */
    @Deprecated
    public String[] templatePath;

    /**
     * The name of the {@link org.eclipse.emf.codegen.merge.java.facade.FacadeHelper facade helper} class to be used in
     * Java merging.
     * @see org.eclipse.emf.codegen.merge.java.facade.FacadeHelper
     */
    public String mergerFacadeHelperClass;

    /**
     * The URI of the JMerge rules file.
     */
    public String mergeRulesURI;

    /**
     * Whether Eclipse JDT code formatting should be applied to generated Java code.
     */
    public boolean codeFormatting;

    /**
     * Code formatter options to be used instead of the defaults for Java code formatting.
     */
    public Map<?, ?> codeFormatterOptions;

    /**
     * The resource set containing the input, from which a URI converter, package registry, resource factory registry,
     * etc can be obtained.
     */  
    public ResourceSet resourceSet;

    /**
     * A list of strings of the form [&lt;variable-name>=]&lt;plugin-id>
     * that will be used by {@link AbstractGeneratorAdapter#addClasspathEntries(org.eclipse.emf.codegen.jet.JETEmitter)}
     * to add classpath entries to each JETEmitter.
     * @see AbstractGeneratorAdapter#addClasspathEntries(org.eclipse.emf.codegen.jet.JETEmitter)
     */
    public List<String> templateClasspath;

    /**
     * Arbitrary data for extensibility.
     */
    public Object[] data;

    public Options()
    {
      super();
    }
  }

  /**
   * The local registry from which generator adapter factories are created.
   */
  protected GeneratorAdapterFactory.Descriptor.Registry adapterFactoryDescriptorRegistry;

  /**
   * The cached set of generator adapter factories for this generator, keyed by package ID.
   */
  protected Map<String, Collection<GeneratorAdapterFactory>> packageIDToAdapterFactories;

  protected Object input;
  protected Options options;

  /**
   * Whether the adapter factories for the input need to be called to initialize before generating code.
   * 
   * @see #requestInitialize()
   */
  protected boolean initializeNeeded = true;

  protected JControlModel jControlModel;

  /**
   * Creates a generator that delegates directly to the {@link GeneratorAdapterFactory.Descriptor.Registry#INSTANCE global}
   * adapter factory descriptor registry.
   * 
   * @see GeneratorAdapterFactory.Descriptor.Registry#INSTANCE
   */
  public Generator()
  {
    super();
  }

  /**
   * Creates a generator that delegates to the given adapter factory descriptor registry.
   */
  public Generator(GeneratorAdapterFactory.Descriptor.Registry adapterFactoryDescriptorRegistry)
  {
    this.adapterFactoryDescriptorRegistry = adapterFactoryDescriptorRegistry;
  }

  /**
   * Returns the current model-level input object.
   * 
   * @see #setInput(Object)
   */
  public Object getInput()
  {
    return input;
  }

  /**
   * Sets the model-level input object, and invokes {@link GeneratorAdapterFactory#initialize(Object) initialize(Object)}
   * on any of its adapter factories. This initialization will also be repeated in the next
   * {@link #generate(Object, Object, Monitor)} or {@link #generate(Object, Object, String, Monitor)}, when the full
   * tree of objects and adapters has been discovered.
   * 
   * @see #getInput()
   * @see #generate(Object, Object, Monitor)
   * @see #generate(Object, Object, String, Monitor)
   * @see GeneratorAdapterFactory#initialize(Object)
   */
  public void setInput(Object input)
  {
    this.input = input;
    initialize();
    initializeNeeded = true;
  }

  /**
   * Signals that {@link GeneratorAdapterFactory#initialize(Object) initialize(object)} should be called on any adapter
   * factories for the {@link #getInput() input} object during the next {@link #generate(Object, Object, Monitor)} or
   * {@link #generate(Object, Object, String, Monitor)}, when the full tree of objects and adapters has been discovered.
   * 
   * @see #getInput()
   * @see #generate(Object, Object, Monitor)
   * @see #generate(Object, Object, String, Monitor)
   * @see GeneratorAdapterFactory#initialize(Object)
   */
  public void requestInitialize()
  {
    initializeNeeded = true;
  }

  /**
   * Invokes {@link GeneratorAdapterFactory#initialize(Object) initialize(object)} on each adapter factory for the
   * {@link #getInput() input} object. Note that adapter factory order is non-deterministic, so if more than
   * one tries to set the same options, the result is undefined.
   * 
   * @see #getInput()
   * @see GeneratorAdapterFactory#initialize(Object)
   */
  protected void initialize()
  {
    for (GeneratorAdapterFactory adapterFactory : getAdapterFactories(input))
    {
      adapterFactory.initialize(input);
    }
  }

  /**
   * Returns the {@link Options} for this generator. Note that these are accessible to (and can be set by) all of the
   * adapter factories and adapters supporting this generator and by clients of the generator itself.
   */
  public Options getOptions()
  {
    if (options == null)
    {
      options = new Options();
    }
    return options;
  }

  protected Set<String> badFacadeHelperClasses = new HashSet<String>();

  /**
   * Returns a {@link org.eclipse.emf.codegen.merge.java.JControlModel JControlModel} that the generator's adapters can
   * use for merging. It is initialized with the {@link Options#mergerFacadeHelperClass facade helper class} and
   * {@link Options#mergeRulesURI merge rules URI} specified in the {@link #getOptions() options}.
   * 
   * @see #getOptions()
   * @see Options#mergerFacadeHelperClass
   * @see Options#mergeRulesURI
   */
  public JControlModel getJControlModel()
  {
    if (jControlModel == null)
    {
      jControlModel = new JControlModel();
    }

    String facadeHelperClass = options.mergerFacadeHelperClass;
    if (!badFacadeHelperClasses.contains(facadeHelperClass) &&
           (jControlModel.getFacadeHelper() == null || !jControlModel.getFacadeHelper().getClass().getName().equals(facadeHelperClass)))
    {
      FacadeHelper facadeHelper = CodeGenUtil.instantiateFacadeHelper(facadeHelperClass); 
      if (facadeHelper == null)
      {
        badFacadeHelperClasses.add(facadeHelperClass);
      }
      jControlModel.initialize(facadeHelper, options.mergeRulesURI);
    }
    return jControlModel;
  }

  /**
   * Returns the generator's adapter factory descriptor registry.
   */
  public GeneratorAdapterFactory.Descriptor.Registry getAdapterFactoryDescriptorRegistry()
  {
    if (adapterFactoryDescriptorRegistry == null)
    {
      adapterFactoryDescriptorRegistry =
        new GeneratorAdapterFactory.Descriptor.DelegatingRegistry(GeneratorAdapterFactory.Descriptor.Registry.INSTANCE);
    }
    return adapterFactoryDescriptorRegistry;
  }

  /**
   * Returns the generator adapter factories for a given object. The {@link #getAdapterFactoryDescriptorRegistry() registry}
   * for the generator is used to obtain the {@link GeneratorAdapterFactory.Descriptor descriptors} for the object's
   * {@link #getPackageID(Object) package ID}, and those descriptors are used to create the adapter factories, which are
   * cached.
   * 
   * @see #getAdapterFactoryDescriptorRegistry()
   * @see #getPackageID(Object)
   * @see GeneratorAdapterFactory.Descriptor
   */
  protected Collection<GeneratorAdapterFactory> getAdapterFactories(Object object)
  {
    if (packageIDToAdapterFactories == null)
    {
      packageIDToAdapterFactories = new HashMap<String, Collection<GeneratorAdapterFactory>>();
    }

    String packageID = getPackageID(object);
    Collection<GeneratorAdapterFactory> result = packageIDToAdapterFactories.get(packageID);
    if (result == null)
    {
      Collection<GeneratorAdapterFactory.Descriptor> descriptors = getAdapterFactoryDescriptorRegistry().getDescriptors(packageID);
      result = new ArrayList<GeneratorAdapterFactory>(descriptors.size());
      for (GeneratorAdapterFactory.Descriptor descriptor : descriptors)
      {
        GeneratorAdapterFactory adapterFactory = descriptor.createAdapterFactory();
        adapterFactory.setGenerator(this);
        result.add(adapterFactory);
      }
      packageIDToAdapterFactories.put(packageID, result);
    }
    return result;
  }

  /**
   * Returns the package ID for the given object.
   *
   * <p>This implementation returns the {@link org.eclipse.emf.ecore.EPackage#getNsURI() EPackage nsURI} for an
   * {@link org.eclipse.emf.ecore.EObject}, and the Java {@link java.lang.Package#getName() package name} for any other
   * object.
   * 
   * @see org.eclipse.emf.ecore.EPackage#getNsURI()
   * @see java.lang.Package#getName()
   */
  protected String getPackageID(Object object)
  {
    return object instanceof EObject ? ((EObject)object).eClass().getEPackage().getNsURI() : object.getClass().getPackage().getName();
  }

  /**
   * Returns the generator adapters for the given object. The adapter factories are obtained from
   * {@link #getAdapterFactories(Object)}, and each may provide one adapter of type
   * {@link GeneratorAdapter GeneratorAdapter.class}.
   * 
   * @see #getAdapterFactories(Object)
   * @see GeneratorAdapter
   */
  protected Collection<GeneratorAdapter> getAdapters(Object object)
  {
    Collection<GeneratorAdapterFactory> adapterFactories = getAdapterFactories(object);
    List<GeneratorAdapter> result = new ArrayList<GeneratorAdapter>(adapterFactories.size());

    for (AdapterFactory adapterFactory : adapterFactories)
    {
      if (adapterFactory.isFactoryForType(GeneratorAdapter.class))
      {
        Object adapter = adapterFactory.adapt(object, GeneratorAdapter.class);
        if (adapter != null)
        {
          result.add((GeneratorAdapter)adapter);
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
    // Since we're invoking plugged-in code, we must be defensive against cycles.
    //
    Set<Object> objects = new HashSet<Object>();

    // Compute the GeneratorData for the given object and its children, then for the parents of the given object.
    //

    List<GeneratorData> childrenData = getGeneratorData(object, projectType, forGenerate, true, false, objects);
    List<GeneratorData> parentsData = getGeneratorData(object, projectType, forGenerate, false, true, objects);

    // Combine the two lists.
    //
    List<GeneratorData> result = new ArrayList<GeneratorData>(parentsData.size() + childrenData.size());
    Collections.reverse(parentsData);
    result.addAll(parentsData);
    result.addAll(childrenData);
    return result.toArray(new GeneratorData[result.size()]);
  }

  private List<GeneratorData> getGeneratorData(Object object, Object projectType, boolean forGenerate, boolean forChildren, boolean skipFirst, Set<Object> objects)
  {
    List<Object> result  = new ArrayList<Object>();
    result.add(object);

    for (int i = 0; i < result.size(); skipFirst = false)
    {
      Object o = result.get(i);

      Collection<GeneratorAdapter> adapters = getAdapters(o);
      result.remove(i);
      if (!adapters.isEmpty())
      {
        for (GeneratorAdapter adapter : adapters)
        {
          if (forChildren)
          {
            Collection<?> children = forGenerate ? adapter.getGenerateChildren(o, projectType) : adapter.getCanGenerateChildren(o, projectType);
            for (Object child : children)
            {
              if (objects.add(child))
              {
                result.add(child);
              }
            }
          }
          else
          {
            Object parent = forGenerate ? adapter.getGenerateParent(o, projectType) : adapter.getCanGenerateParent(o, projectType);
            if (parent != null && objects.add(parent))
            {
              result.add(parent);
            }
          }

          if (!skipFirst)
          {
            result.add(i++, new GeneratorData(o, adapter));
          }
        }
      }
    }
    
    @SuppressWarnings({"cast","unchecked"})
    List<GeneratorData> list = (List<GeneratorData>)(List)result;
    return list;
  }

  /**
   * Returns whether code can be generated for the given object and project type.
   * A project type is represented by an arbitrary object that is meaningful to the generator adapters for the relevant
   * objects.
   *
   * <p>This result is obtained as follows:
   * 
   * <ol>
   * <li>The {@link GeneratorAdapter adapters} for the object are obtained.
   * <li>A complete collection of objects to be considered is formed by iteratively invoking the
   *     {@link GeneratorAdapter#getCanGenerateParent(Object, Object) getCanGenerateParent(Object, Object)} and
   *     {@link GeneratorAdapter#getCanGenerateChildren(Object, Object) getCanGenerateChildren(Object, Object)} methods
   *     on the adapters for the object, the adapters for the object's parent and children, and so on. It is the
   *     adapters' responsibility to determine the relevant objects through their implementations of these methods.
   * <li>The {@link GeneratorAdapter#canGenerate(Object, Object) canGenerate(Object, Object)} method is invoked on all
   *     the adapters for every object in the set formed in step 2. If any adapter returns <code>true</code> for any
   *     object, this method returns <code>true</code>.
   * <li>Otherwise, <code>false</code> is returned.
   * </ol>
   * 
   * @see GeneratorAdapter#getCanGenerateParent(Object, Object)
   * @see GeneratorAdapter#getCanGenerateChildren(Object, Object)
   * @see GeneratorAdapter#canGenerate(Object, Object)
   */
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

  /**
   * Performs code generation for the given object and project type. A project type is represented by an arbitrary object
   * that is meaningful to the generator adapters for the relevant objects. Since this is a long-running operation, it
   * reports progress using the given <code>Monitor</code>. Its final status is returned as a <code>Diagnostic</code>.
   * 
   * <p>This method operates exactly as {@link #generate(Object, Object, String, Monitor)} does when null is specified
   * as the <code>projectTypeName</code>.
   * 
   * <p>It is not necessary to call {@link #canGenerate(Object, Object)} before this method. If that method would 
   * return <code>false</code>, this method should generate nothing when invoked with the same arguments.
   * 
   * @see #generate(Object, Object, String, Monitor)
   * @see #canGenerate(Object, Object)
   */
  public Diagnostic generate(Object object, Object projectType, Monitor monitor)
  {
    return generate(object, projectType, null, monitor);
  }

  /**
   * Performs code generation for the given object and project type. A project type is represented by an arbitrary object
   * that is meaningful to the generator adapters for the relevant objects. Since this is a long-running operation, it
   * reports progress using the given <code>Monitor</code>. Its final status is returned as a <code>Diagnostic</code>.
   * The <code>projectTypeName</code>, if non-null, is used only to provide to the <code>Monitor</code> a more specific
   * message for the task.
   * 
   * <p>It is not necessary to call {@link #canGenerate(Object, Object)} before this method. If that method would 
   * return <code>false</code>, this method should generate nothing when invoked with the same arguments.
   * 
   * <p>Code generation is performed as follows:
   * 
   * <ol>
   * <li>The {@link GeneratorAdapter adapters} for the object are obtained.
   * <li>A complete collection of objects to be considered is formed by iteratively invoking the
   *     {@link GeneratorAdapter#getGenerateParent(Object, Object) getGenerateParent(Object, Object)} and
   *     {@link GeneratorAdapter#getGenerateChildren(Object, Object) getGenerateChildren(Object, Object)} methods on the
   *     adapters for the object, the adapters for the object's parent and children, and so on. It is the adapters'
   *     responsibility to determine the relevant objects through their implementations of these methods.
   * <li>If this is the first invocation of {@link #generate(Object, Object, String, Monitor)} or initialization has
   *     been {@link #requestInitialize() requested}, {@link GeneratorAdapterFactory#initialize(Object) initialize(Object)}
   *     is invoked on each adapter factory for the {@link #getInput() input} object.
   * <li>The {@link GeneratorAdapter#preGenerate(Object, Object) preGenerate(Object, Object)} method is invoked on all
   *     the adapters for every object in the set formed in step 2, giving the adapters a chance to perform setup
   *     before any code is generated.
   * <li>The {@link GeneratorAdapter#generate(Object, Object, Monitor) generate(Object, Object, Monitor)} method is
   *     invoked on all the adapters for every object in the set formed in step 2. This is where code generation
   *     actually occurs.
   * <li>The {@link GeneratorAdapter#postGenerate(Object, Object) postGenerate(Object, Object)} method is invoked on all
   *     the adapters for every object in the set formed in step 2, giving adapters a chance to clean up from code
   *     generation.
   * </ol>
   * 
   * <p>The operation may be canceled during step 4 or 5, either based on the <code>Monitor</code> or the
   * <code>Diagnostic</code> returned by any generator adapter invocation. By default, only a <code>CANCEL</code>
   * {@link org.eclipse.emf.common.util.Diagnostic#getSeverity severity} will cause code generation to stop; however,
   * this can be customized by overriding {@link #canContinue(Diagnostic) canContinue(Diagnostic)}. Even if code
   * generation is canceled, {@link GeneratorAdapter#postGenerate(Object, Object) postGenerate(Object, Object)} will
   * still be called on all the adapters on which {@link GeneratorAdapter#preGenerate(Object, Object) preGenerate(Object, Object)}
   * has been called.
   * 
   * @see #canGenerate(Object, Object)
   * @see #requestInitialize()
   * @see GeneratorAdapter#getGenerateParent(Object, Object)
   * @see GeneratorAdapter#getGenerateChildren(Object, Object)
   * @see GeneratorAdapter#preGenerate(Object, Object)
   * @see GeneratorAdapter#generate(Object, Object, Monitor)
   * @see GeneratorAdapter#postGenerate(Object, Object)
   * @see GeneratorAdapterFactory#initialize(Object)
   */
  public Diagnostic generate(Object object, Object projectType, String projectTypeName, Monitor monitor)
  {
    if (SYSOUT_BEGIN_END) System.out.println("******* Begin: " + new java.util.Date());
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

      // Give all generator adapters the chance to do tear down.
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
      if (SYSOUT_BEGIN_END) System.out.println("******* End: " + new java.util.Date());
    }
  }

  /**
   * Determines whether code generation can continue, based on the given <code>Diagnostic</code>. This implementation
   * returns true unless the {@link org.eclipse.emf.common.util.Diagnostic#getSeverity severity} is
   * <code>CANCEL</code>.
   * 
   * @see org.eclipse.emf.common.util.Diagnostic#getSeverity()
   */
  protected boolean canContinue(Diagnostic diagnostic)
  {
    return diagnostic.getSeverity() != Diagnostic.CANCEL;
  }

  /**
   * Disposes all of the generator's adapter factories, by calling {@link GeneratorAdapterFactory#dispose() dispose()}
   * on each.
   * 
   * @see GeneratorAdapterFactory#dispose()
   */
  public void dispose()
  {
    if (packageIDToAdapterFactories != null)
    {
      for (Collection<GeneratorAdapterFactory> adapterFactories : packageIDToAdapterFactories.values())
      {
        for (GeneratorAdapterFactory adapterFactory : adapterFactories)
        {
          adapterFactory.dispose();
        }
      }
    }
  }
}
