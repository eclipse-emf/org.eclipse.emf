/**
 * Copyright (c) 2004-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.ecore2ecore.presentation;


import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.InitializeCopyCommand;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.mapping.MappingRoot;
import org.eclipse.emf.mapping.command.InitializeCopyOverrideCommand;
import org.eclipse.emf.mapping.domain.AdapterFactoryMappingDomain;
import org.eclipse.emf.mapping.domain.MappingDomain;
import org.eclipse.emf.mapping.domain.PluginAdapterFactoryMappingDomain;
import org.eclipse.emf.mapping.ecore2ecore.Ecore2EcoreFactory;
import org.eclipse.emf.mapping.ecore2ecore.provider.Ecore2EcoreItemProviderAdapterFactory;
import org.eclipse.emf.mapping.presentation.MappingEditor;
import org.eclipse.emf.mapping.provider.MappingItemProviderAdapterFactory;
import org.eclipse.ui.part.FileEditorInput;


/**
 * This is an example of a Ecore2Ecore model editor.
 * @generated NOT
 */
public class Ecore2EcoreEditor extends MappingEditor
{
  public Ecore2EcoreEditor()
  {
    topLabel = Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_InputEcore_label"); //$NON-NLS-1$
    bottomLabel = Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_OutputEcore_label"); //$NON-NLS-1$
  }

  public static class Ecore2EcoreInitializeCopyOverrideCommand extends InitializeCopyOverrideCommand
  {

    public Ecore2EcoreInitializeCopyOverrideCommand(MappingDomain domain, InitializeCopyCommand initializeCommand)
    {
      super(domain, initializeCommand);
    }

    @Override
    public void execute()
    {
      super.execute();
    }
  }

  public static class Ecore2EcoreMappingDomain extends PluginAdapterFactoryMappingDomain
  {

    public Ecore2EcoreMappingDomain(
      AdapterFactory mappingDomainAdapterFactory,
      AdapterFactory topDomainAdapterFactory,
      AdapterFactory bottomDomainAdapterFactory,
      CommandStack commandStack,
      String mappingDomainKey)
    {
      super(mappingDomainAdapterFactory, topDomainAdapterFactory, bottomDomainAdapterFactory, commandStack, mappingDomainKey);

      setMappingEnablementFlags(MappingDomain.ENABLE_ALL);
    }

    @Override
    protected Command createInitializeCopyOverrideCommand(InitializeCopyCommand initializeCopyCommand)
    {
      if (!isSameEditingDomainAdapterFactory)
      {
        return new Ecore2EcoreInitializeCopyOverrideCommand(this, initializeCopyCommand);
      }

      return null;
    }
  }

  @Override
  protected AdapterFactoryMappingDomain createMappingDomain()
  {
    AdapterFactory mappingAdapterFactory = new ComposedAdapterFactory(new AdapterFactory[]
      {
        new ResourceItemProviderAdapterFactory(),
        new MappingItemProviderAdapterFactory(),
        new Ecore2EcoreItemProviderAdapterFactory()
      });
    AdapterFactory ecoreAdapterFactory = new EcoreItemProviderAdapterFactory();
    
    AdapterFactoryMappingDomain result = new Ecore2EcoreMappingDomain(mappingAdapterFactory, ecoreAdapterFactory, ecoreAdapterFactory, new BasicCommandStack(), null);
    result.getResourceSet().getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(true));
    return result;
  }

  @Override
  protected void handleMissingModelFile()
  {
    try
    {
      IFile mappingModelFile = modelFile.getFile().getParent().getFile(
        new Path(new Path(modelFile.getName()).removeFileExtension().toOSString() + ".mapper")); //$NON-NLS-1$

      MappingRoot originalRootObject = (MappingRoot)(mappingDomain.getResourceSet().getResources().iterator().next()).getContents().get(0);

      modelFile = new FileEditorInput(mappingModelFile);

      Resource mappingModelResource = null;

      if (mappingModelFile.exists())
      {
        mappingRoot = (MappingRoot)mappingDomain.loadResource(getURIFromFile(modelFile.getFile())).getContents().get(0);
      }
      else
      {
        mappingModelResource = mappingDomain.getResourceSet().createResource(
          URI.createFileURI(modelFile.getFile().getFullPath().toString()));

        mappingRoot = Ecore2EcoreFactory.eINSTANCE.createEcore2EcoreMappingRoot();

        mappingModelResource.getContents().add(mappingRoot);

        mappingDomain.getResourceSet().getResources().add(mappingModelResource);

        mappingRoot.getInputs().add(originalRootObject);

        IFile outputModelFile = modelFile.getFile().getParent().getFile(
          new Path(new Path(modelFile.getName()).removeFileExtension().toOSString() + "_mapper_result.classside")); //$NON-NLS-1$

        Resource outputResource = null;

        if (outputModelFile.exists())
        {
          outputResource = mappingDomain.loadResource(getURIFromFile(outputModelFile));
        }
        else
        {
          outputResource = mappingDomain.getResourceSet().createResource(URI.createURI(getURIFromFile(outputModelFile)));

          outputResource.getContents().add(Ecore2EcoreFactory.eINSTANCE.createEcore2EcoreMappingRoot());

          mappingRoot.getOutputs().addAll(outputResource.getContents());
        }

        doSave(null);
      }

      mappingRoot.setDomain(mappingDomain);
      mappingRoot.setTopToBottom(true);

    }
    catch (Exception e)
    {
      e.printStackTrace(System.err);
    }
  }
}