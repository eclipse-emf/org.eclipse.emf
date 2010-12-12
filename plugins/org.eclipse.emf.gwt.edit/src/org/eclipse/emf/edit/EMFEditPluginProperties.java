/**
 * <copyright> 
 *
 * Copyright (c) 2010 Ed Merks and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Ed Merks - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EMFEditPluginProperties.java,v 1.1 2010/12/12 20:29:46 emerks Exp $
 */
package org.eclipse.emf.edit;

import com.google.gwt.i18n.client.Messages;

public interface EMFEditPluginProperties extends Messages
{
  @Key("_EXC_Method_not_implemented")
  @DefaultMessage("The method {0} is not implemented")
  String excMethodNotImplemented(Object substitution);

  @Key("_UI_AddCommand_label")
  @DefaultMessage("Add")
  String addCommandLabel();

  @Key("_UI_AddCommand_description")
  @DefaultMessage("Insert the objects into the feature of the owner")
  String addCommandDescription();

  @Key("_UI_AddCommand_description_for_list")
  @DefaultMessage("Insert the objects into the list")
  String addCommandDescriptionForList();

  @Key("_UI_CopyCommand_label")
  @DefaultMessage("Copy")
  String copyCommandLabel();

  @Key("_UI_CopyCommand_description")
  @DefaultMessage("Create a recursive copy of the objects and their children")
  String copyCommandDescription();

  @Key("_UI_CopyToClipboardCommand_label")
  @DefaultMessage("Copy to Clipboard")
  String copyToClipboardCommandLabel();

  @Key("_UI_CopyToClipboardCommand_description")
  @DefaultMessage("Copy the objects to the clipboard")
  String copyToClipboardCommandDescription();

  @Key("_UI_CreateCopyCommand_label")
  @DefaultMessage("Create Copy")
  String createCopyCommandLabel();

  @Key("_UI_CreateCopyCommand_description")
  @DefaultMessage("Create a copy of the object")
  String createCopyCommandDescription();

  @Key("_UI_CutToClipboardCommand_label")
  @DefaultMessage("Cut")
  String cutToClipboardCommandLabel();

  @Key("_UI_CutToClipboardCommand_description")
  @DefaultMessage("Delete the objects and place them in the clipboard")
  String cutToClipboardCommandDescription();

  @Key("_UI_DragAndDropCommand_label")
  @DefaultMessage("Drag and Drop")
  String dragAndDropCommandLabel();

  @Key("_UI_DragAndDropCommand_description")
  @DefaultMessage("Drag the objects and drop them on some other object")
  String dragAndDropCommandDescription();

  @Key("_UI_InitializeCopyCommand_label")
  @DefaultMessage("Initialize Copy")
  String initializeCopyCommandLabel();

  @Key("_UI_InitializeCopyCommand_description")
  @DefaultMessage("Initialize the copy of the other object")
  String initializeCopyCommandDescription();

  @Key("_UI_MoveCommand_label")
  @DefaultMessage("Move")
  String moveCommandLabel();

  @Key("_UI_MoveCommand_description")
  @DefaultMessage("Move the value to a new index within the feature of the object")
  String moveCommandDescription();

  @Key("_UI_MoveCommand_description_for_list")
  @DefaultMessage("Move the value to a new index within the list")
  String moveCommandDescriptionForList();

  @Key("_UI_PasteFromClipboardCommand_label")
  @DefaultMessage("Paste from Clipboard")
  String pasteFromClipboardCommandLabel();

  @Key("_UI_PasteFromClipboardCommand_description")
  @DefaultMessage("Paste the contents of the clipboard into the object")
  String pasteFromClipboardCommandDescription();

  @Key("_UI_RemoveCommand_label")
  @DefaultMessage("Remove")
  String removeCommandLabel();

  @Key("_UI_RemoveCommand_description")
  @DefaultMessage("Remove the objects from the feature of the owner")
  String removeCommandDescription();

  @Key("_UI_RemoveCommand_description_for_list")
  @DefaultMessage("Remove the objects from the list")
  String removeCommandDescriptionForList();

  @Key("_UI_ReplaceCommand_label")
  @DefaultMessage("Replace")
  String replaceCommandLabel();

  @Key("_UI_ReplaceCommand_description")
  @DefaultMessage("Replace the object from the feature of the owner with the collection of objects")
  String replaceCommandDescription();

  @Key("_UI_SetCommand_label")
  @DefaultMessage("Set")
  String setCommandLabel();

  @Key("_UI_SetCommand_description")
  @DefaultMessage("Set the value to the feature of the owner")
  String setCommandDescription();

  @Key("_UI_CreateChild_text")
  @DefaultMessage("{0}")
  String createChildText(Object substitution);

  @Key("_UI_CreateChild_tooltip")
  @DefaultMessage("Create New {0} Under {1} Feature")
  String createChildTooltip(Object substitution0, Object substitution1);

  @Key("_UI_CreateChild_description")
  @DefaultMessage("Create a new child of type {0} for the {1} feature of the selected {2}")
  String createChildDescription(Object substitution0, Object substitution1, Object substitution2);

  @Key("_UI_CreateSibling_description")
  @DefaultMessage("Create a new sibling of type {0} for the selected {2}, under the {1} feature of their parent")
  String createSiblingDescription(Object substitution0, Object substitution1, Object substitution2);

  @Key("_UI_CreateChildCommand_label")
  @DefaultMessage("New {0}")
  String createChildCommandLabel(Object substitution);

  @Key("_UI_CreateChildCommand_description")
  @DefaultMessage("Create a new object")
  String createChildCommandDescription();

  @Key("_UI_Unknown_type")
  @DefaultMessage("Object")
  String unknownType();

  @Key("_UI_Unknown_feature")
  @DefaultMessage("Unspecified")
  String unknownFeature();

  @Key("_UI_Property_description")
  @DefaultMessage("The {0} feature of type {1}")
  String propertyDescription(Object substitution0, Object substitution1);

  @Key("_UI_ResourceSet_label")
  @DefaultMessage("Resource Set")
  String resourceSetLabel();

  @Key("_UI_ValueProperty_name")
  @DefaultMessage("Value")
  String valuePropertyName();

  @Key("_UI_ValueProperty_description")
  @DefaultMessage("The value")
  String valuePropertyDescription();

  @Key("_UI_CreateChild_text2")
  @DefaultMessage("{1} {0}")
  String createChildText2(Object substitution0, Object substitution1);

  @Key("_UI_CreateChild_text3")
  @DefaultMessage("{0}")
  String createChildText3(Object Substitution1);

  @Key("_UI_Unknown_datatype")
  @DefaultMessage("Value")
  String unknownDatatype();

  @Key("_UI_ItemProviderAdapterFactory_extensionpoint")
  @DefaultMessage("Registered Item Provider Adapter Factories")
  String itemProviderAdapterFactoryExtensionpoint();

  @Key("_UI_DeleteCommand_label")
  @DefaultMessage("Delete")
  String deleteCommandLabel();

  @Key("_UI_DeleteCommand_description")
  @DefaultMessage("Remove the objects and clean up any other references to them from within the editing domain")
  String deleteCommandDescription();

  @Key("_UI_ChildCreationExtender_extensionpoint")
  @DefaultMessage("Child Creation Extender")
  String childCreationExtenderExtensionpoint();
}
