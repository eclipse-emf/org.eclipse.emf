/**
 * <copyright> 
 *
 * Copyright (c) 6 IBM Corporation and others.
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
 * $Id: GenModelGeneratorAdapterFactory.java,v 1.8 2007/05/11 15:12:39 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.generator;

import java.util.Arrays;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelAdapterFactory;
import org.eclipse.emf.codegen.jet.JETCompiler;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * A generator adapter factory for the {@link org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage GenModel package}.
 * This implementation creates the adapters that perform default EMF code generation. It can also be subclassed to
 * create derived adapters that remove from or change the default code generation, or to create separate adapters
 * that augment the default code generation.
 * 
 * <p>The factory implements a singleton adapter pattern, where one adapter is cached and reused for all objects of a
 * given type.
 * 
 * <p>This implementation also initializes its generator's options based on the {@link GenModel}.
 * 
 * @since 2.2.0
 */
public class GenModelGeneratorAdapterFactory extends GenModelAdapterFactory implements GeneratorAdapterFactory
{
  /**
   * A descriptor for this adapter factory, which can be used to programatically
   * {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor.Registry#addDescriptor(String, org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor) register}
   * it.
   * @see org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor.Registry
   */
  public static final GeneratorAdapterFactory.Descriptor DESCRIPTOR = new GeneratorAdapterFactory.Descriptor()
  {
    public GeneratorAdapterFactory createAdapterFactory()
    {
      return new GenModelGeneratorAdapterFactory();
    }
  };

  /**
   * The default JMerge rules file for EMF.
   */
  protected static final String MERGE_RULES_PATH_NAME = "emf-merge.xml";

  protected Generator generator;
  protected GenBaseGeneratorAdapter genModelGeneratorAdapter;
  protected GenBaseGeneratorAdapter genPackageGeneratorAdapter;
  protected GenBaseGeneratorAdapter genClassGeneratorAdapter;
  protected GenBaseGeneratorAdapter genEnumGeneratorAdapter;

  public GenModelGeneratorAdapterFactory()
  {
    super();
  }

  /**
   * Returns <code>true</code> when the type is <code>GeneratorAdapter.class</code>.
   */
  @Override
  public boolean isFactoryForType(Object type)
  {
    return type == GeneratorAdapter.class;
  }

  /**
   * Does an {@link org.eclipse.emf.common.notify.impl.AdapterFactoryImpl#adapt(Notifier, Object) adapt(Notifier, Object)},
   * substituting <code>this</code> for the given <code>type</code>. This substitution is necessary because each of many
   * generator adapter factories can have its own generator adapter on a single object.
   */ 
  @Override
  public Adapter adapt(Notifier target, Object type)
  {
    return super.adapt(target, this);
  }

  /**
   * Returns a singleton {@link GenModelGeneratorAdapter}.
   */
  @Override
  public Adapter createGenModelAdapter()
  {
    if (genModelGeneratorAdapter == null)
    {
      genModelGeneratorAdapter = new GenModelGeneratorAdapter(this);
    }
    return genModelGeneratorAdapter;
  }

  /**
   * Returns a singleton {@link GenPackageGeneratorAdapter}.
   */
  @Override
  public Adapter createGenPackageAdapter()
  {
    if (genPackageGeneratorAdapter == null)
    {
      genPackageGeneratorAdapter = new GenPackageGeneratorAdapter(this);
    }
    return genPackageGeneratorAdapter;
  }

  /**
   * Returns a singleton {@link GenClassGeneratorAdapter}.
   */
  @Override
  public Adapter createGenClassAdapter()
  {
    if (genClassGeneratorAdapter == null)
    {
      genClassGeneratorAdapter = new GenClassGeneratorAdapter(this);
    }
    return genClassGeneratorAdapter;
  }

  /**
   * Returns a singleton {@link GenEnumGeneratorAdapter}.
   */
  @Override
  public Adapter createGenEnumAdapter()
  {
    if (genEnumGeneratorAdapter == null)
    {
      genEnumGeneratorAdapter = new GenEnumGeneratorAdapter(this);
    }
    return genEnumGeneratorAdapter;
  }

  public Generator getGenerator()
  {
    return generator;
  }

  public void setGenerator(Generator generator)
  {
    this.generator = generator;
  }

  /**
   * Performs initialization for the given input {@link GenModel}. It is used as the basis for setting
   * {@link Generator#getOptions() options} on the associated {@link Generator}.
   */
  public void initialize(Object input)
  {
    Generator.Options options = generator.getOptions();
    GenModel genModel = (GenModel)input;
    Resource resource = genModel.eResource();

    options.redirectionPattern = genModel.getRedirection();
    options.forceOverwrite = genModel.isForceOverwrite();
    options.dynamicTemplates = genModel.isDynamicTemplates();

    initializeMergeRulesURI(options, genModel);

    options.mergerFacadeHelperClass = genModel.getFacadeHelperClass();
    options.codeFormatting = genModel.isCodeFormatting();
    options.resourceSet = resource != null ? resource.getResourceSet() : null;
    options.templateClasspath = genModel.getTemplatePluginVariables();
  }
  
  /**
   * For backwards compatibility, we must check whether getTemplatePath() has been overridden to return something
   * other than the default, and, if so, behave as before.
   */
  @SuppressWarnings("deprecation")
  private void initializeMergeRulesURI(Generator.Options options, GenModel genModel)
  {
    String[] defaultTemplatePath = getDefaultTemplatePath(genModel);
    String[] templatePath = getTemplatePath(genModel);
    if (!Arrays.equals(templatePath, defaultTemplatePath))
    {
      options.templatePath = templatePath;
      options.mergeRulesURI = JETCompiler.find(templatePath, MERGE_RULES_PATH_NAME);
    }
    else
    {
      options.mergeRulesURI = getMergeRulesURI(genModel);
    }    
  }

  /**
   * Computes the template path for the given <code>GenModel</code>. The result of this method was intended to be used
   * in setting the generator's {@link org.eclipse.emf.codegen.ecore.generator.Generator.Options#templatePath templatePath}
   * option. However, a single path for all code generation is actually insufficient. The path needs to be specified and
   * extended on a per-adapter basis.
   * 
   * <p>If this implementation is not overridden, the generator's <code>templatePath</code> will no longer be set to
   * the default value. Instead, it will be left null, and template paths will be computed on a per-adapter basis using
   * {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter#addBaseTemplatePathEntries(java.util.List)}.
   * 
   * <p>In order to preserve backwards compatibility, if this implementation is overridden to return something other
   * than the default, the generator's <code>templatePath</code> will be set to this result.
   * 
   * <p>Previously, this path was also searched to obtain the value to set as the generator's
   * {@link org.eclipse.emf.codegen.ecore.generator.Generator.Options#mergeRulesURI mergeRulesURI} option. Now, if this
   * method is not overridden, the new {@link #getMergeRulesURI(GenModel)} method will be invoked to compute that value. 
   * 
   * @deprecated org.eclipse.emf.codegen.ecore 2.2.2 Override
   *              {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter#addBaseTemplatePathEntries(java.util.List)}
   *              and, if needed, {@link #getMergeRulesURI(GenModel)}, instead.
   */
  @Deprecated
  protected String[] getTemplatePath(GenModel genModel)
  {
    return getDefaultTemplatePath(genModel);
  }

  /*
   * Computes the default path for the given GenModel. This was previously the implementation of getTemplatePath().
   */
  private String[] getDefaultTemplatePath(GenModel genModel)
  {
    String[] result = null;
    String staticLocation = CodeGenEcorePlugin.INSTANCE.getBaseURL().toString() + "templates";
    String templateDirectory = genModel.getTemplateDirectory(); 

    if (genModel.isDynamicTemplates() && templateDirectory != null)
    {
      result = new String[2];
      result[0] = templateDirectory.indexOf(':') == -1 ? URI.createPlatformResourceURI(templateDirectory, true).toString() : templateDirectory;
      result[1] = staticLocation;
    }
    else
    {
      result = new String[1];
      result[0] = staticLocation; 
    }
    return result;
  }

  /**
   * Returns the URI of the merge rules file for the given <code>GenModel</code>.
   * 
   * <p>The default implementation of this method is to search the default path that would be returned by
   * {@link #getTemplatePath(GenModel)}, if not overridden, for a file called "emf-merge.xml", and return the URI of
   * the first such file encontered. Since that method has been deprecated, this method can now be overridden to search
   * a different path, or indeed, obtain the merge rules URI in some other way.
   * 
   * <p>This method is only invoked if {@link #getTemplatePath(GenModel)} has not been overridden.
   * 
   * @since org.eclipse.emf.codegen.ecore 2.2.2
   */
  protected String getMergeRulesURI(GenModel genModel)
  {
    return JETCompiler.find(getDefaultTemplatePath(genModel), MERGE_RULES_PATH_NAME);
  }

  public void dispose()
  {
    if (genModelGeneratorAdapter != null) genModelGeneratorAdapter.dispose();
    if (genPackageGeneratorAdapter != null) genPackageGeneratorAdapter.dispose();
    if (genClassGeneratorAdapter != null) genClassGeneratorAdapter.dispose();
    if (genEnumGeneratorAdapter != null) genEnumGeneratorAdapter.dispose();
  }
}
