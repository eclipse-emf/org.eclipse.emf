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
 * $Id: GenModelGeneratorAdapterFactory.java,v 1.2.2.1 2006/11/08 22:05:02 davidms Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.generator;

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
  }

  /**
   * Returns <code>true</code> when the type is <code>GeneratorAdapter.class</code>.
   */
  public boolean isFactoryForType(Object type)
  {
    return type == GeneratorAdapter.class;
  }

  /**
   * Does an {@link org.eclipse.emf.common.notify.impl.AdapterFactoryImpl#adapt(Notifier, Object) adapt(Notifier, Object)},
   * substituting <code>this</code> for the given <code>type</code>. This substitution is necessary because each of many
   * generator adapter factories can have its own generator adapter on a single object.
   */ 
  public Adapter adapt(Notifier target, Object type)
  {
    return super.adapt(target, this);
  }

  /**
   * Returns a singleton {@link GenModelGeneratorAdapter}.
   */
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
    options.mergerFacadeHelperClass = genModel.getFacadeHelperClass();
    options.mergeRulesURI = JETCompiler.find(getTemplatePath(genModel), MERGE_RULES_PATH_NAME);
    options.codeFormatting = genModel.isCodeFormatting();
    options.resourceSet = resource != null ? resource.getResourceSet() : null;
  }

  /**
   * Computes the default template path for the given <code>GenModel</code>.
   */
  protected String[] getTemplatePath(GenModel genModel)
  {
    String[] result = null;
    String staticLocation = CodeGenEcorePlugin.INSTANCE.getBaseURL().toString() + "templates";
    String templateDirectory = genModel.getTemplateDirectory(); 

    if (genModel.isDynamicTemplates() && templateDirectory != null)
    {
      result = new String[2];
      result[0] = templateDirectory.indexOf(':') == -1 ? URI.createPlatformResourceURI(templateDirectory).toString() : templateDirectory;
      result[1] = staticLocation;
    }
    else
    {
      result = new String[1];
      result[0] = staticLocation; 
    }
    return result;
  }

  public void dispose()
  {
    if (genModelGeneratorAdapter != null) genModelGeneratorAdapter.dispose();
    if (genPackageGeneratorAdapter != null) genPackageGeneratorAdapter.dispose();
    if (genClassGeneratorAdapter != null) genClassGeneratorAdapter.dispose();
    if (genEnumGeneratorAdapter != null) genEnumGeneratorAdapter.dispose();
  }
}
