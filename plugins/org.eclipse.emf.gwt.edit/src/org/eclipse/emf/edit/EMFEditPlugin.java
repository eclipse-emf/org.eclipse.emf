/**
 * <copyright> 
 *
 * Copyright (c) 2002-2010 IBM Corporation and others.
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
 * $Id: EMFEditPlugin.java,v 1.3 2010/12/12 20:29:46 emerks Exp $
 */
package org.eclipse.emf.edit;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChildCreationExtender;

import com.google.gwt.core.client.GWT;


/**
 * The <b>Plugin</b> for the model EMF.Edit library.
 * EMF must run 
 * within an Eclipse workbench,
 * within a headless Eclipse workspace,
 * or just stand-alone as part of some other application.
 * To support this, all resource access should be directed to the resource locator,
 * which can redirect the service as appropriate to the runtime.
 * During stand-alone invocation no plugin initialization takes place.
 * In this case, emf.edit.resources.jar must be on the CLASSPATH.
 * @see #INSTANCE
 */
public final class EMFEditPlugin extends EMFPlugin 
{
  /**
   * The singleton instance of the plugin.
   */
  public static final EMFEditPlugin INSTANCE = new EMFEditPlugin();


  /**
   * Creates the singleton instance.
   */
  private EMFEditPlugin()
  {
    super(new ResourceLocator[] {});
  }

  /*
   * Javadoc copied from base class.
   */
  @Override
  public ResourceLocator getPluginResourceLocator()
  {
    return null;
  }
  
  /**
   * The singleton instance of an {@link ComposedAdapterFactory.Descriptor.Registry item provider adapter factory registry}.
   */
  private static ComposedAdapterFactory.Descriptor.Registry.Impl composedAdapterFactoryDescriptorRegistry;

  /**
   * Returns a populated instance of an {@link ComposedAdapterFactory.Descriptor.Registry item provider adapter factory registry}.
   * @return a populated instance of an item provider adapter factory registry.
   */
  public static ComposedAdapterFactory.Descriptor.Registry getComposedAdapterFactoryDescriptorRegistry()
  {
    if (composedAdapterFactoryDescriptorRegistry == null)
    {
      composedAdapterFactoryDescriptorRegistry = 
        new ComposedAdapterFactory.Descriptor.Registry.Impl(null)
        {
          private static final long serialVersionUID = 1L;
  
          @Override
          public ComposedAdapterFactory.Descriptor delegatedGetDescriptor(Collection<?> types)
          {
            List<Object> stringTypes = new ArrayList<Object>(types.size());
            for (Object key : types)
            {
              if (key instanceof EPackage)
              {
                stringTypes.add(((EPackage)key).getNsURI());
              }
              else if (key instanceof Class<?>)
              {
                stringTypes.add(((Class<?>)key).getName());
              }
              else
              {
                return null;
              }
            }
            ComposedAdapterFactory.Descriptor descriptor = (ComposedAdapterFactory.Descriptor)get(stringTypes);
            if (descriptor != null)
            {
              put(types, descriptor);
              return descriptor;
            }
            
            return super.delegatedGetDescriptor(types);
          }
        };
    }
    return composedAdapterFactoryDescriptorRegistry;
  }

  /**
   * The singleton instance of a {@link IChildCreationExtender.Descriptor.Registry child creation extender registry}.
   */
  private static IChildCreationExtender.Descriptor.Registry.Impl childCreationExtenderDescriptorRegistry;

  /**
   * Returns a populated instance of a {@link IChildCreationExtender.Descriptor.Registry child creation extender registry}.
   * @return a populated instance of child creation extender registry.
   */
  public static IChildCreationExtender.Descriptor.Registry getChildCreationExtenderDescriptorRegistry()
  {
    if (childCreationExtenderDescriptorRegistry == null)
    {
      childCreationExtenderDescriptorRegistry = 
        new IChildCreationExtender.Descriptor.Registry.Impl(null)
        {
          private static final long serialVersionUID = 1L;
  
          @Override
          public Collection<IChildCreationExtender.Descriptor> delegatedGetDescriptors(String namespace)
          {
            Collection<IChildCreationExtender.Descriptor> descriptors = get(namespace);
            return descriptors != null ? descriptors : super.delegatedGetDescriptors(namespace);
          }
        };
    }
    return childCreationExtenderDescriptorRegistry;
  }

  protected static final EMFEditPluginProperties PROPERTIES = GWT.create(EMFEditPluginProperties.class);

  @Override
  public String getString(String key, boolean translate)
  {
    if ("_UI_AddCommand_label".equals(key)) return PROPERTIES.addCommandLabel();
    else if ("_UI_AddCommand_description".equals(key)) return PROPERTIES.addCommandDescription();
    else if ("_UI_AddCommand_description_for_list".equals(key)) return PROPERTIES.addCommandDescriptionForList();
    else if ("_UI_CopyCommand_label".equals(key)) return PROPERTIES.copyCommandLabel();
    else if ("_UI_CopyCommand_description".equals(key)) return PROPERTIES.copyCommandDescription();
    else if ("_UI_CopyToClipboardCommand_label".equals(key)) return PROPERTIES.copyToClipboardCommandLabel();
    else if ("_UI_CopyToClipboardCommand_description".equals(key)) return PROPERTIES.copyToClipboardCommandDescription();
    else if ("_UI_CreateCopyCommand_label".equals(key)) return PROPERTIES.createCopyCommandLabel();
    else if ("_UI_CreateCopyCommand_description".equals(key)) return PROPERTIES.createCopyCommandDescription();
    else if ("_UI_CutToClipboardCommand_label".equals(key)) return PROPERTIES.cutToClipboardCommandLabel();
    else if ("_UI_CutToClipboardCommand_description".equals(key)) return PROPERTIES.cutToClipboardCommandDescription();
    else if ("_UI_DragAndDropCommand_label".equals(key)) return PROPERTIES.dragAndDropCommandLabel();
    else if ("_UI_DragAndDropCommand_description".equals(key)) return PROPERTIES.dragAndDropCommandDescription();
    else if ("_UI_InitializeCopyCommand_label".equals(key)) return PROPERTIES.initializeCopyCommandLabel();
    else if ("_UI_InitializeCopyCommand_description".equals(key)) return PROPERTIES.initializeCopyCommandDescription();
    else if ("_UI_MoveCommand_label".equals(key)) return PROPERTIES.moveCommandLabel();
    else if ("_UI_MoveCommand_description".equals(key)) return PROPERTIES.moveCommandDescription();
    else if ("_UI_MoveCommand_description_for_list".equals(key)) return PROPERTIES.moveCommandDescriptionForList();
    else if ("_UI_PasteFromClipboardCommand_label".equals(key)) return PROPERTIES.pasteFromClipboardCommandLabel();
    else if ("_UI_PasteFromClipboardCommand_description".equals(key)) return PROPERTIES.pasteFromClipboardCommandDescription();
    else if ("_UI_RemoveCommand_label".equals(key)) return PROPERTIES.removeCommandLabel();
    else if ("_UI_RemoveCommand_description".equals(key)) return PROPERTIES.removeCommandDescription();
    else if ("_UI_RemoveCommand_description_for_list".equals(key)) return PROPERTIES.removeCommandDescriptionForList();
    else if ("_UI_ReplaceCommand_label".equals(key)) return PROPERTIES.replaceCommandLabel();
    else if ("_UI_ReplaceCommand_description".equals(key)) return PROPERTIES.replaceCommandDescription();
    else if ("_UI_SetCommand_label".equals(key)) return PROPERTIES.setCommandLabel();
    else if ("_UI_SetCommand_description".equals(key)) return PROPERTIES.setCommandDescription();
    else if ("_UI_CreateChildCommand_description".equals(key)) return PROPERTIES.createChildCommandDescription();
    else if ("_UI_Unknown_type".equals(key)) return PROPERTIES.unknownType();
    else if ("_UI_Unknown_feature".equals(key)) return PROPERTIES.unknownFeature();
    else if ("_UI_ResourceSet_label".equals(key)) return PROPERTIES.resourceSetLabel();
    else if ("_UI_ValueProperty_name".equals(key)) return PROPERTIES.valuePropertyName();
    else if ("_UI_ValueProperty_description".equals(key)) return PROPERTIES.valuePropertyDescription();
    else if ("_UI_Unknown_datatype".equals(key)) return PROPERTIES.unknownDatatype();
    else if ("_UI_ItemProviderAdapterFactory_extensionpoint".equals(key)) return PROPERTIES.itemProviderAdapterFactoryExtensionpoint();
    else if ("_UI_DeleteCommand_label".equals(key)) return PROPERTIES.deleteCommandLabel();
    else if ("_UI_DeleteCommand_description".equals(key)) return PROPERTIES.deleteCommandDescription();
    else if ("_UI_ChildCreationExtender_extensionpoint".equals(key)) return PROPERTIES.childCreationExtenderExtensionpoint();
    else return key;
  }

  @Override
  public String getString(String key, Object [] substitutions, boolean translate)
  {
    if ("_EXC_Method_not_implemented".equals(key)) return PROPERTIES.excMethodNotImplemented(substitutions[0]);
    else if ("_UI_CreateChild_text".equals(key)) return PROPERTIES.createChildText(substitutions[0]);
    else if ("_UI_CreateChild_tooltip".equals(key)) return PROPERTIES.createChildTooltip(substitutions[0], substitutions[1]);
    else if ("_UI_CreateChild_description".equals(key)) return PROPERTIES.createChildDescription(substitutions[0], substitutions[1], substitutions[2]);
    else if ("_UI_CreateSibling_description".equals(key)) return PROPERTIES.createSiblingDescription(substitutions[0], substitutions[1], substitutions[2]);
    else if ("_UI_CreateChildCommand_label".equals(key)) return PROPERTIES.createChildCommandLabel(substitutions[0]);
    else if ("_UI_Property_description".equals(key)) return PROPERTIES.propertyDescription(substitutions[0], substitutions[1]);
    else if ("_UI_CreateChild_text2".equals(key)) return PROPERTIES.createChildText2(substitutions[0], substitutions[1]);
    else if ("_UI_CreateChild_text3".equals(key)) return PROPERTIES.createChildText3(substitutions[1]);
    else return key;
  }
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static final EMFEditPluginImages IMAGES = GWT.create(EMFEditPluginImages.class);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(String key)
  {
    if ("full/obj16/Resource".equals(key)) return IMAGES.resource();
    else if ("full/obj16/ResourceSet".equals(key)) return IMAGES.resourceSet();
    else return key;
  }
}
