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
 * $Id: GenModelGeneratorAdapterFactory.java,v 1.2 2006/05/02 17:33:51 davidms Exp $
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
 * @since 2.2.0
 */
public class GenModelGeneratorAdapterFactory extends GenModelAdapterFactory implements GeneratorAdapterFactory
{
  public static final GeneratorAdapterFactory.Descriptor DESCRIPTOR = new GeneratorAdapterFactory.Descriptor()
  {
    public GeneratorAdapterFactory createAdapterFactory()
    {
      return new GenModelGeneratorAdapterFactory();
    }
  };

  protected static final String MERGE_RULES_PATH_NAME = "emf-merge.xml";

  protected Generator generator;
  protected GenBaseGeneratorAdapter genModelGeneratorAdapter;
  protected GenBaseGeneratorAdapter genPackageGeneratorAdapter;
  protected GenBaseGeneratorAdapter genClassGeneratorAdapter;
  protected GenBaseGeneratorAdapter genEnumGeneratorAdapter;

  public GenModelGeneratorAdapterFactory()
  {
  }

  public boolean isFactoryForType(Object type)
  {
    return type == GeneratorAdapter.class;
  }

  // Because each of many generator adapter factories can have its own generator adapter on a single object, we need 
  // to use the adapter factory instance as the type seen by the adapter.
  //
  public Adapter adapt(Notifier target, Object type)
  {
    return super.adapt(target, this);
  }

  public Adapter createGenModelAdapter()
  {
    if (genModelGeneratorAdapter == null)
    {
      genModelGeneratorAdapter = new GenModelGeneratorAdapter(this);
    }
    return genModelGeneratorAdapter;
  }

  public Adapter createGenPackageAdapter()
  {
    if (genPackageGeneratorAdapter == null)
    {
      genPackageGeneratorAdapter = new GenPackageGeneratorAdapter(this);
    }
    return genPackageGeneratorAdapter;
  }

  public Adapter createGenClassAdapter()
  {
    if (genClassGeneratorAdapter == null)
    {
      genClassGeneratorAdapter = new GenClassGeneratorAdapter(this);
    }
    return genClassGeneratorAdapter;
  }

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

  public void initialize(Object input)
  {
    Generator.Options options = generator.getOptions();
    GenModel genModel = (GenModel)input;
    Resource resource = genModel.eResource();

    options.redirectionPattern = genModel.getRedirection();
    options.forceOverwrite = genModel.isForceOverwrite();
    options.dynamicTemplates = genModel.isDynamicTemplates();
    options.templatePath = getTemplatePath(genModel);
    options.mergerFacadeHelperClass = genModel.getFacadeHelperClass();
    options.mergeRulesURI = JETCompiler.find(options.templatePath, MERGE_RULES_PATH_NAME);
    options.codeFormatting = genModel.isCodeFormatting();
    options.resourceSet = resource != null ? resource.getResourceSet() : null;
  }

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
