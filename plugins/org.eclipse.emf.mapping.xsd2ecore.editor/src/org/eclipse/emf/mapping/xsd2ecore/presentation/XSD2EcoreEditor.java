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
package org.eclipse.emf.mapping.xsd2ecore.presentation;


import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.part.FileEditorInput;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
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
import org.eclipse.emf.mapping.presentation.MappingEditor;
import org.eclipse.emf.mapping.provider.MappingItemProviderAdapterFactory;
import org.eclipse.emf.mapping.xsd2ecore.XSD2EcoreFactory;
import org.eclipse.emf.mapping.xsd2ecore.provider.XSD2EcoreItemProviderAdapterFactory;

import org.eclipse.xsd.provider.XSDItemProviderAdapterFactory;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;


/**
 * This is an example of a mapper model editor.
 */
public class XSD2EcoreEditor extends MappingEditor 
{
  public XSD2EcoreEditor()
  {
    topLabel = XSD2EcoreEditorPlugin.INSTANCE.getString("_UI_XMLSchema_label");
    bottomLabel = XSD2EcoreEditorPlugin.INSTANCE.getString("_UI_Ecore_label");
  }

  public static class MyInitializeCopyOverrideCommand extends InitializeCopyOverrideCommand 
  {
    /**
     * Constructor for MyInitializeCopyOverrideCommand.
     * @param domain
     * @param initializeCommand
     */
    public MyInitializeCopyOverrideCommand(MappingDomain domain, InitializeCopyCommand initializeCommand) 
    {
      super(domain, initializeCommand);
    }
      
    /**
     * @see org.eclipse.emf.common.command.Command#execute()
     */
    @Override
    public void execute() 
    {
      super.execute();
    }
  }
   
  public static class MyMappingDomain extends PluginAdapterFactoryMappingDomain 
  {
    /**
     * Constructor for MyMappingDomain.
     * @param mappingDomainAdapterFactory
     * @param topDomainAdapterFactory
     * @param bottomDomainAdapterFactory
     * @param commandStack
     * @param mappingDomainKey
     */
    public MyMappingDomain
      (AdapterFactory mappingDomainAdapterFactory,
       AdapterFactory topDomainAdapterFactory,
       AdapterFactory bottomDomainAdapterFactory,
       CommandStack commandStack,
       String mappingDomainKey) 
    {
     super
       (mappingDomainAdapterFactory,
        topDomainAdapterFactory,
        bottomDomainAdapterFactory,
        commandStack,
        mappingDomainKey);
    }

    @Override
    protected Command createInitializeCopyOverrideCommand(InitializeCopyCommand initializeCopyCommand) 
    {
      // If the owner and the copy are of the same type, we can proceed with the command, i.e. no override.
      // If not, we will skip this command by returning a do-nothing override.
      //
      if (!isSameEditingDomainAdapterFactory) 
      {
        return new MyInitializeCopyOverrideCommand(this, initializeCopyCommand);
      }

      return null;
    }
  }

  @Override
  protected AdapterFactoryMappingDomain createMappingDomain() 
  {
    AdapterFactory mappingAdapterFactory =
      new ComposedAdapterFactory(
        new AdapterFactory[] 
        {
          new ResourceItemProviderAdapterFactory(),
          new MappingItemProviderAdapterFactory(),
          new XSD2EcoreItemProviderAdapterFactory()
        });

    // This is a test case for the persistent command stack implementation.
    //
    CommandStack commandStack = new BasicCommandStack();

    // This is a test case for cross domain code.
    // It creates two instances of the factory.
    //
    AdapterFactoryMappingDomain mappingDomain;
    mappingDomain =
      new MyMappingDomain
        (mappingAdapterFactory,
         new XSDItemProviderAdapterFactory(),
         new EcoreItemProviderAdapterFactory(),
         commandStack,
         null);

/*
    mappingDomain.setMappingEnablementFlags
      (MappingDomain.ENABLE_MULTIPLE_INPUTS |
        MappingDomain.ENABLE_MULTIPLE_INPUT_MAPPINGS |
        MappingDomain.ENABLE_EMPTY_INPUTS);
*/

    mappingDomain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put("wsdl", new XSDResourceFactoryImpl());

    return mappingDomain;
  }

  /**
   * @see org.eclipse.emf.mapping.presentation.MappingEditor#handleMissingModelFile()
   */
  @Override
  protected void handleMissingModelFile() 
  {
    try 
    {
      IFile mappingModelFile =
        modelFile.getFile().getParent().getFile
          (new Path(new Path(modelFile.getName()).removeFileExtension().toOSString() + ".xsd2ecore"));

      EObject originalRootObject = (mappingDomain.getResourceSet().getResources().iterator().next()).getContents().get(0);

      // Switch over.
      //
      modelFile = new FileEditorInput(mappingModelFile);

      Resource mappingModelResource = null;
      if (mappingModelFile.exists()) 
      {
        Resource resource = mappingDomain.loadResource(getURIFromFile(modelFile.getFile()));
        mappingRoot = (MappingRoot) resource.getContents().get(0);
      } 
      else 
      {
        // Get the resource factory for this type of file name.
        //
        mappingModelResource = 
          mappingDomain.getResourceSet().createResource(URI.createFileURI(modelFile.getFile().getFullPath().toString()));

        // Add the initial model object to the extent.
        //
        mappingRoot = XSD2EcoreFactory.eINSTANCE.createXSD2EcoreMappingRoot();

        mappingModelResource.getContents().add(mappingRoot);

        mappingDomain.getResourceSet().getResources().add(mappingModelResource);

        mappingRoot.getInputs().add(originalRootObject);

        IFile outputModelFile =
          modelFile.getFile().getParent().getFile
            (new Path(new Path(modelFile.getName()).removeFileExtension().toOSString() + ".ecore"));

        Resource outputResource = null;

        if (outputModelFile.exists()) 
        {
          outputResource = mappingDomain.loadResource(getURIFromFile(outputModelFile));
        } 
        else 
        {
          // Get the resource factory for this type of file name.
          //
          outputResource = mappingDomain.getResourceSet().createResource(URI.createURI(getURIFromFile(outputModelFile)));

          EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();

          outputResource.getContents().add(ePackage);

          mappingRoot.getOutputs().addAll(outputResource.getContents());
        }

        doSave(null);
      }
      mappingRoot.setDomain(mappingDomain);
      mappingRoot.setTopToBottom(true);
    } 
    catch (Exception exception) 
    {
      exception.printStackTrace();
    }
  }
}
