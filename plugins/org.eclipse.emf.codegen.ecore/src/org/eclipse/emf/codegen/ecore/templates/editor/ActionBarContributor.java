package org.eclipse.emf.codegen.ecore.templates.editor;

import org.eclipse.emf.codegen.ecore.genmodel.*;

public class ActionBarContributor
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL + "" + NL + "" + NL + "import java.util.Collection;" + NL + "import java.util.Iterator;" + NL + "import java.util.LinkedList;" + NL + "" + NL + "import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;";
  protected final String TEXT_6 = NL + "import org.eclipse.emf.edit.ui.action.CreateChildAction;" + NL + "import org.eclipse.emf.edit.ui.action.CreateSiblingAction;" + NL + "" + NL + "import org.eclipse.emf.edit.domain.EditingDomain;" + NL + "import org.eclipse.emf.edit.domain.IEditingDomainProvider;";
  protected final String TEXT_7 = NL + NL + "import org.eclipse.jface.action.ActionContributionItem;" + NL + "import org.eclipse.jface.action.IAction;" + NL + "import org.eclipse.jface.action.IContributionItem;" + NL + "import org.eclipse.jface.action.IContributionManager;" + NL + "import org.eclipse.jface.action.IMenuManager;" + NL + "import org.eclipse.jface.action.IToolBarManager;" + NL + "import org.eclipse.jface.action.MenuManager;" + NL + "import org.eclipse.jface.action.Separator;" + NL + "import org.eclipse.jface.action.SubContributionItem;" + NL + "" + NL + "import org.eclipse.jface.viewers.ISelection;" + NL + "import org.eclipse.jface.viewers.ISelectionChangedListener;" + NL + "import org.eclipse.jface.viewers.ISelectionProvider;" + NL + "import org.eclipse.jface.viewers.IStructuredSelection;" + NL + "import org.eclipse.jface.viewers.SelectionChangedEvent;" + NL + "" + NL + "import org.eclipse.ui.IEditorPart;" + NL;
  protected final String TEXT_8 = NL + NL + NL + "/**" + NL + " * This is the action bar contributor for the ";
  protected final String TEXT_9 = " model editor." + NL + " * <!-- begin-user-doc -->" + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_10 = NL + "\textends EditingDomainActionBarContributor" + NL + "\timplements ISelectionChangedListener" + NL + "{";
  protected final String TEXT_11 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_12 = " copyright = \"";
  protected final String TEXT_13 = "\";";
  protected final String TEXT_14 = NL;
  protected final String TEXT_15 = NL + "\t/**" + NL + "\t * This keeps track of the active editor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected IEditorPart activeEditorPart;" + NL + "" + NL + "\t/**" + NL + "\t * This keeps track of the current selection provider." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ISelectionProvider selectionProvider;" + NL;
  protected final String TEXT_16 = NL + "\t/**" + NL + "\t * This will contain one {@link CreateChildAction} corresponding to each descriptor" + NL + "\t * generated for the current selection by the item provider." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected Collection createChildActions;" + NL + "" + NL + "\t/**" + NL + "\t * This is the menu manager into which menu contribution items should be added for CreateChild actions." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected IMenuManager createChildMenuManager;" + NL + "" + NL + "\t/**" + NL + "\t * This will contain one {@link CreateSiblingAction} corresponding to each descriptor" + NL + "\t * generated for the current selection by the item provider." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected Collection createSiblingActions;" + NL + "" + NL + "\t/**" + NL + "\t * This is the menu manager into which menu contribution items should be added for CreateSibling actions." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected IMenuManager createSiblingMenuManager;" + NL;
  protected final String TEXT_17 = NL + "\t/**" + NL + "\t * This creates an instance of the contributor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_18 = "()" + NL + "\t{" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This adds Separators for editor additions to the tool bar." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void contributeToToolBar(IToolBarManager toolBarManager)" + NL + "\t{" + NL + "\t\ttoolBarManager.add(new Separator(\"";
  protected final String TEXT_19 = "-settings\"));";
  protected final String TEXT_20 = NL + "\t\ttoolBarManager.add(new Separator(\"";
  protected final String TEXT_21 = "-additions\"));";
  protected final String TEXT_22 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This adds to the menu bar a menu and some separators for editor additions," + NL + "\t * as well as the sub-menus for object creation items." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void contributeToMenu(IMenuManager menuManager)" + NL + "\t{" + NL + "\t\tsuper.contributeToMenu(menuManager);" + NL + "" + NL + "\t\tIMenuManager submenuManager = new MenuManager(";
  protected final String TEXT_23 = ".INSTANCE.getString(\"_UI_";
  protected final String TEXT_24 = "_menu\"), \"";
  protected final String TEXT_25 = "MenuID\");";
  protected final String TEXT_26 = NL + "\t\tmenuManager.insertAfter(\"additions\", submenuManager);";
  protected final String TEXT_27 = NL + "\t\tsubmenuManager.add(new Separator(\"settings\"));";
  protected final String TEXT_28 = NL + "\t\tsubmenuManager.add(new Separator(\"actions\"));";
  protected final String TEXT_29 = NL + "\t\tsubmenuManager.add(new Separator(\"additions\"));";
  protected final String TEXT_30 = NL + "\t\tsubmenuManager.add(new Separator(\"additions-end\"));";
  protected final String TEXT_31 = NL;
  protected final String TEXT_32 = NL + "\t\t// Prepare for CreateChild item addition or removal." + NL + "\t\t//" + NL + "\t\tcreateChildMenuManager = new MenuManager(";
  protected final String TEXT_33 = ".INSTANCE.getString(\"_UI_CreateChild_menu_item\"));";
  protected final String TEXT_34 = NL + "\t\tsubmenuManager.insertBefore(\"additions\", createChildMenuManager);";
  protected final String TEXT_35 = NL + NL + "\t\t// Prepare for CreateSibling item addition or removal." + NL + "\t\t//" + NL + "\t\tcreateSiblingMenuManager = new MenuManager(";
  protected final String TEXT_36 = ".INSTANCE.getString(\"_UI_CreateSibling_menu_item\"));";
  protected final String TEXT_37 = NL + "\t\tsubmenuManager.insertBefore(\"additions\", createSiblingMenuManager);";
  protected final String TEXT_38 = NL + "\t\t// Add your contributions." + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_39 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * When the active editor changes," + NL + "\t * this remembers the change," + NL + "\t * and registers with it as a selection provider." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setActiveEditor(IEditorPart part)" + NL + "\t{" + NL + "\t\tsuper.setActiveEditor(part);" + NL + "\t\tactiveEditorPart = part;" + NL + "" + NL + "\t\t// Switch to the new selection provider." + NL + "\t\t//" + NL + "\t\tif (selectionProvider != null)" + NL + "\t\t{" + NL + "\t\t\tselectionProvider.removeSelectionChangedListener(this);" + NL + "\t\t}" + NL + "\t\tif (part == null)" + NL + "\t\t{" + NL + "\t\t\tselectionProvider = null;" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{" + NL + "\t\t\tselectionProvider = part.getSite().getSelectionProvider();" + NL + "\t\t\tselectionProvider.addSelectionChangedListener(this);" + NL + "" + NL + "\t\t\t// Fake a selection changed event to update the menus." + NL + "\t\t\t//" + NL + "\t\t\tif (selectionProvider.getSelection() != null)" + NL + "\t\t\t{" + NL + "\t\t\t\tselectionChanged(new SelectionChangedEvent(selectionProvider, selectionProvider.getSelection()));" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This implements {@link ISelectionChangedListener}," + NL + "\t * handling {@link SelectionChangedEvents} by querying for the children and siblings" + NL + "\t * that can be added to the selected object and updating the menus accordingly." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void selectionChanged(SelectionChangedEvent event)" + NL + "\t{";
  protected final String TEXT_40 = NL + "\t\t// Remove any menu items for old selection." + NL + "\t\t//" + NL + "\t\tif (createChildMenuManager != null)" + NL + "\t\t{" + NL + "\t\t\tdepopulateManager(createChildMenuManager, createChildActions);" + NL + "\t\t}" + NL + "\t\tif (createSiblingMenuManager != null)" + NL + "\t\t{" + NL + "\t\t\tdepopulateManager(createSiblingMenuManager, createSiblingActions);" + NL + "\t\t}" + NL + "" + NL + "\t\t// Query the new selection for appropriate new child/sibling descriptors" + NL + "\t\t//" + NL + "\t\tCollection newChildDescriptors = null;" + NL + "\t\tCollection newSiblingDescriptors = null;" + NL + "" + NL + "\t\tISelection selection = event.getSelection();" + NL + "\t\tif (selection instanceof IStructuredSelection && ((IStructuredSelection)selection).size() == 1)" + NL + "\t\t{" + NL + "\t\t\tObject object = ((IStructuredSelection)selection).getFirstElement();" + NL + "" + NL + "\t\t\tEditingDomain domain =" + NL + "\t\t\t\t((IEditingDomainProvider) activeEditorPart).getEditingDomain();" + NL + "" + NL + "\t\t\tnewChildDescriptors = domain.getNewChildDescriptors(object, null);" + NL + "\t\t\tnewSiblingDescriptors = domain.getNewChildDescriptors(null, object);" + NL + "\t\t}" + NL + "" + NL + "\t\t// Generate actions for selection; populate and redraw the menus." + NL + "\t\t//" + NL + "\t\tcreateChildActions = generateCreateChildActions(newChildDescriptors, selection);" + NL + "\t\tcreateSiblingActions = generateCreateSiblingActions(newSiblingDescriptors, selection);" + NL + "" + NL + "\t\tif (createChildMenuManager != null)" + NL + "\t\t{" + NL + "\t\t\tpopulateManager(createChildMenuManager, createChildActions, null);" + NL + "\t\t\tcreateChildMenuManager.update(true);" + NL + "\t\t}" + NL + "\t\tif (createSiblingMenuManager != null)" + NL + "\t\t{" + NL + "\t\t\tpopulateManager(createSiblingMenuManager, createSiblingActions, null);" + NL + "\t\t\tcreateSiblingMenuManager.update(true);" + NL + "\t\t}";
  protected final String TEXT_41 = NL + "\t\t// Add your contributions." + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_42 = NL + "\t}" + NL;
  protected final String TEXT_43 = NL + "\t/**" + NL + "\t * This generates a {@link CreateChildAction} for each object in <code>descriptors</code>," + NL + "\t * and returns the collection of these actions." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected Collection generateCreateChildActions(Collection descriptors, ISelection selection)" + NL + "\t{" + NL + "\t\tCollection actions = new LinkedList();" + NL + "\t\tif (descriptors != null)" + NL + "\t\t{" + NL + "\t\t\tfor (Iterator i = descriptors.iterator(); i.hasNext(); )" + NL + "\t\t\t{" + NL + "\t\t\t\tactions.add(new CreateChildAction(activeEditorPart, selection, i.next()));" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn actions;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This generates a {@link CreateSiblingAction} for each object in <code>descriptors</code>," + NL + "\t * and returns the collection of these actions." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected Collection generateCreateSiblingActions(Collection descriptors, ISelection selection)" + NL + "\t{" + NL + "\t\tCollection actions = new LinkedList();" + NL + "\t\tif (descriptors != null)" + NL + "\t\t{" + NL + "\t\t\tfor (Iterator i = descriptors.iterator(); i.hasNext(); )" + NL + "\t\t\t{" + NL + "\t\t\t\tactions.add(new CreateSiblingAction(activeEditorPart, selection, i.next()));" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn actions;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This populates the specified <code>manager</code> with {@link ActionContributionItem}s" + NL + "\t * based on the {@link IAction}s contained in the <code>actions</code> collection," + NL + "\t * by inserting them before the specified contribution item <code>contributionID</code>." + NL + "\t * If <code>ID</code> is <code>null</code>, they are simply added." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void populateManager(IContributionManager manager, Collection actions, String contributionID)" + NL + "\t{" + NL + "\t\tif (actions != null)" + NL + "\t\t{" + NL + "\t\t\tfor (Iterator i = actions.iterator(); i.hasNext(); )" + NL + "\t\t\t{" + NL + "\t\t\t\tIAction action = (IAction) i.next();" + NL + "\t\t\t\tif (contributionID != null)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\tmanager.insertBefore(contributionID, action);" + NL + "\t\t\t\t}" + NL + "\t\t\t\telse" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\tmanager.add(action);" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t}" + NL + "\t\t" + NL + "\t/**" + NL + "\t * This removes from the specified <code>manager</code> all {@link ActionContributionItem}s" + NL + "\t * based on the {@link IAction}s contained in the <code>actions</code> collection." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void depopulateManager(IContributionManager manager, Collection actions)" + NL + "\t{" + NL + "\t\tif (actions != null)" + NL + "\t\t{" + NL + "\t\t\tIContributionItem[] items = manager.getItems();" + NL + "\t\t\tfor (int i = 0; i < items.length; i++)" + NL + "\t\t\t{" + NL + "\t\t\t\t// Look into SubContributionItems" + NL + "\t\t\t\t//" + NL + "\t\t\t\tIContributionItem contributionItem = items[i];" + NL + "\t\t\t\twhile (contributionItem instanceof SubContributionItem)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\tcontributionItem = ((SubContributionItem)contributionItem).getInnerItem();" + NL + "\t\t\t\t}" + NL + "" + NL + "\t\t\t\t// Delete the ActionContributionItems with matching action." + NL + "\t\t\t\t//" + NL + "\t\t\t\tif (contributionItem instanceof ActionContributionItem)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\tIAction action = ((ActionContributionItem) contributionItem).getAction();" + NL + "\t\t\t\t\tif (actions.contains(action))" + NL + "\t\t\t\t\t{" + NL + "\t\t\t\t\t\tmanager.remove(contributionItem);" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This populates the pop-up menu before it appears." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void menuAboutToShow(IMenuManager menuManager)" + NL + "\t{" + NL + "\t\tsuper.menuAboutToShow(menuManager);" + NL + "\t\tMenuManager submenuManager = null;" + NL + "" + NL + "\t\tsubmenuManager = new MenuManager(";
  protected final String TEXT_44 = ".INSTANCE.getString(\"_UI_CreateChild_menu_item\"));";
  protected final String TEXT_45 = NL + "\t\tpopulateManager(submenuManager, createChildActions, null);" + NL + "\t\tmenuManager.insertBefore(\"additions\", submenuManager);";
  protected final String TEXT_46 = NL + NL + "\t\tsubmenuManager = new MenuManager(";
  protected final String TEXT_47 = ".INSTANCE.getString(\"_UI_CreateSibling_menu_item\"));";
  protected final String TEXT_48 = NL + "\t\tpopulateManager(submenuManager, createSiblingActions, null);" + NL + "\t\tmenuManager.insertBefore(\"additions\", submenuManager);";
  protected final String TEXT_49 = NL + "\t}";
  protected final String TEXT_50 = NL + "}";
  protected final String TEXT_51 = NL;

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 */

    GenPackage genPackage = (GenPackage)argument; GenModel genModel=genPackage.getGenModel();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genPackage.getPresentationPackageName());
    stringBuffer.append(TEXT_5);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_7);
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genPackage.getActionBarContributorClassName());
    stringBuffer.append(TEXT_10);
    if (genModel.getCopyrightText() != null) {
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genModel.getCopyrightText());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_14);
    }
    stringBuffer.append(TEXT_15);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getActionBarContributorClassName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getPrefix().toLowerCase());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genPackage.getPrefix().toLowerCase());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genPackage.getImportedEditorPluginClassName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getEditorClassName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_31);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genPackage.getImportedEditorPluginClassName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genPackage.getImportedEditorPluginClassName());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_38);
    }
    stringBuffer.append(TEXT_39);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_40);
    } else {
    stringBuffer.append(TEXT_41);
    }
    stringBuffer.append(TEXT_42);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genPackage.getImportedEditorPluginClassName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genPackage.getImportedEditorPluginClassName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_49);
    }
    stringBuffer.append(TEXT_50);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_51);
    return stringBuffer.toString();
  }
}
