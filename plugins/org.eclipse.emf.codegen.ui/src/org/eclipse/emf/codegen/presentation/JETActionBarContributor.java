/**
 * Copyright (c) 2019 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.presentation;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.eclipse.emf.codegen.presentation.JETEditor.JavaEditor;
import org.eclipse.jdt.ui.actions.IJavaEditorActionDefinitionIds;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.editors.text.TextEditorActionContributor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.RetargetTextEditorAction;


/**
 * @since 2.19
 */
public class JETActionBarContributor extends TextEditorActionContributor
{

  private final Map<String, RetargetTextEditorAction> selectionActions = new LinkedHashMap<String, RetargetTextEditorAction>();

  private final RetargetTextEditorAction toggleInsertModeAction;

  private final RetargetTextEditorAction gotoMatchingBracketAction;

  private final RetargetTextEditorAction selectEncodingJETElement;

  private final RetargetTextEditorAction extractLocalVariableAction;

  private final RetargetTextEditorAction renameLocalVariableAction;

  private final RetargetTextEditorAction formatAction;

  public JETActionBarContributor()
  {
    ResourceBundle bundle = CodeGenUIPlugin.getResourceBundle();

    for (Map.Entry<String, String> entry : JETEditor.JavaEditor.SELECTION_ACTIONS.entrySet())
    {
      RetargetTextEditorAction retargetTextEditorAction = new RetargetTextEditorAction(bundle, entry.getKey() + ".");
      retargetTextEditorAction.setActionDefinitionId(entry.getValue());
      selectionActions.put(entry.getKey(), retargetTextEditorAction);
    }

    toggleInsertModeAction = new RetargetTextEditorAction(bundle, "ToggleInsertMode.", IAction.AS_CHECK_BOX);
    toggleInsertModeAction.setActionDefinitionId(ITextEditorActionDefinitionIds.TOGGLE_INSERT_MODE);

    gotoMatchingBracketAction = new RetargetTextEditorAction(bundle, "GotoMatchingBracket.");
    gotoMatchingBracketAction.setActionDefinitionId(JETEditor.GOTO_BRACKET_COMMAND_ID);

    selectEncodingJETElement = new RetargetTextEditorAction(bundle, "SelectEnclosingJETElement.");
    selectEncodingJETElement.setActionDefinitionId(JETEditor.SELECT_ENCLOSING_JET_ELEMENT_COMMAND_ID);

    extractLocalVariableAction = new RetargetTextEditorAction(bundle, "ExtractLocalVariable.");
    extractLocalVariableAction.setActionDefinitionId(IJavaEditorActionDefinitionIds.EXTRACT_LOCAL_VARIABLE);

    renameLocalVariableAction = new RetargetTextEditorAction(bundle, JETEditor.RENAME_ACTION_ID + ".");
    renameLocalVariableAction.setActionDefinitionId(JETEditor.RENAME_COMMAND_ID);

    formatAction = new RetargetTextEditorAction(bundle, JETEditor.FORMAT_ACTION_ID + ".");
    formatAction.setActionDefinitionId(JETEditor.FORMAT_COMMAND_ID);
  }

  @Override
  public void contributeToMenu(IMenuManager menu)
  {
    super.contributeToMenu(menu);

    IMenuManager editMenu = menu.findMenuUsingPath(IWorkbenchActionConstants.M_EDIT);
    if (editMenu != null)
    {
      editMenu.appendToGroup("additions", toggleInsertModeAction);
      editMenu.appendToGroup("additions", gotoMatchingBracketAction);

      MenuManager expandSelectionToMenu = new MenuManager("Expand Selection to", "expandSelection"); //$NON-NLS-1$
      editMenu.insertAfter(ITextEditorActionConstants.SELECT_ALL, expandSelectionToMenu);

      expandSelectionToMenu.add(selectEncodingJETElement);

      for (Map.Entry<String, String> entry : JETEditor.JavaEditor.SELECTION_ACTIONS.entrySet())
      {
        expandSelectionToMenu.add(selectionActions.get(entry.getKey()));
      }
    }
  }

  @Override
  public void setActiveEditor(IEditorPart part)
  {
    super.setActiveEditor(part);

    if (part instanceof ITextEditor)
    {
      ITextEditor textEditor = (ITextEditor)part;
      IAction toggleInsertAction = textEditor.getAction(ITextEditorActionConstants.TOGGLE_INSERT_MODE);
      toggleInsertModeAction.setAction(toggleInsertAction);
    }

    if (part instanceof JETEditor)
    {
      JETEditor jetEditor = (JETEditor)part;

      JavaEditor javaEditor = jetEditor.getJavaEditor();
      IActionBars actionBars = getActionBars();

      for (Map.Entry<String, String> entry : JETEditor.JavaEditor.SELECTION_ACTIONS.entrySet())
      {
        String actionID = entry.getKey();
        IAction action = javaEditor.getAction(actionID);
        RetargetTextEditorAction retargetTextEditorSelectionAction = selectionActions.get(actionID);
        retargetTextEditorSelectionAction.setAction(action);
        actionBars.setGlobalActionHandler(entry.getValue(), retargetTextEditorSelectionAction);
      }

      IAction selectEncosingJETElementAction = jetEditor.getAction(JETEditor.SELECT_ENCLOSING_JET_ELEMENT_ACTION_ID);
      selectEncosingJETElementAction.setActionDefinitionId(JETEditor.SELECT_ENCLOSING_JET_ELEMENT_COMMAND_ID);
      actionBars.setGlobalActionHandler(JETEditor.SELECT_ENCLOSING_JET_ELEMENT_COMMAND_ID, selectEncosingJETElementAction);

      IAction openDeclarationAction = javaEditor.getOpenDeclarationAction();
      openDeclarationAction.setActionDefinitionId(IJavaEditorActionDefinitionIds.OPEN_EDITOR);
      actionBars.setGlobalActionHandler(IJavaEditorActionDefinitionIds.OPEN_EDITOR, openDeclarationAction);

      IAction gotoMatchingBracketAction = javaEditor.getGotoMatchingBracketAction();
      gotoMatchingBracketAction.setActionDefinitionId(JETEditor.GOTO_BRACKET_COMMAND_ID);
      this.gotoMatchingBracketAction.setAction(gotoMatchingBracketAction);
      actionBars.setGlobalActionHandler(JETEditor.GOTO_BRACKET_COMMAND_ID, this.gotoMatchingBracketAction);

      IAction extractLocalVariableAction = javaEditor.getAction(JETEditor.EXTRACT_LOCAL_VARIABLE_ACTION_ID);
      this.extractLocalVariableAction.setAction(extractLocalVariableAction);
      actionBars.setGlobalActionHandler(IJavaEditorActionDefinitionIds.EXTRACT_LOCAL_VARIABLE, this.extractLocalVariableAction);

      IAction renameLocalVariableAction = jetEditor.getAction(JETEditor.RENAME_ACTION_ID);
      this.renameLocalVariableAction.setAction(renameLocalVariableAction);
      actionBars.setGlobalActionHandler(JETEditor.RENAME_ACTION_ID, this.renameLocalVariableAction);

      IAction formatAction = jetEditor.getAction(JETEditor.FORMAT_ACTION_ID);
      this.formatAction.setAction(formatAction);
      actionBars.setGlobalActionHandler(JETEditor.FORMAT_ACTION_ID, this.formatAction);
    }
  }
}
