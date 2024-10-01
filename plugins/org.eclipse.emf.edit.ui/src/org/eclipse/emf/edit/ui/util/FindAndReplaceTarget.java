/*
 * Copyright (c) Eclipse contributors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.edit.ui.util;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.ui.viewer.IStyledLabelDecorator;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptorDecorator;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.action.FindAction;
import org.eclipse.emf.edit.ui.provider.DecoratingColumLabelProvider;
import org.eclipse.emf.edit.ui.provider.DelegatingStyledCellLabelProvider;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage.ExtendedPropertySheetEntry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.text.IFindReplaceTarget;
import org.eclipse.jface.text.IFindReplaceTargetExtension;
import org.eclipse.jface.text.IFindReplaceTargetExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.WorkbenchPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetSorter;


/**
 * An implementation of a find and replace target specialized to work with EMF Editors and EMF's {@link ExtendedPropertySheetPage}.
 * Because platform internals are used, which are subject to arbitrary change, this class is final and the constructor is private.
 * The only public entry point for creating an instance is {@link #getAdapter(Class, IWorkbenchPart, AbstractUIPlugin)}.
 * <p>
 * This find and replace target expects to be used by the platform's {@code org.eclipse.ui.texteditor.FindReplaceDialog},
 * i.e., it reuses the same find and replace dialog that is used by the platform's text editors,
 * specializing that dialog with addition controls, i.e., support for a {@link SearchType search type} control.
 * </p>
 * <p>
 * To exploit this implementation, {@link FindAction create} a find action the constructor of a derived {@link EditingDomainActionBarContributor}
 * and specialize the {@link WorkbenchPart#getAdapter(Class)} method in the editor implementation class to call the {@link #getAdapter(Class, IWorkbenchPart, AbstractUIPlugin) getAdapter} method of this class.
 * This will result in the {@code Find/Replace...} appearing in the context menu and {@code Edit} menu, bound to the search hot key, generally {@code Ctrl-F},
 * and will result in an instance of this class being created, bound to the viewers in your editor.
 * But take note, this implementation's behavior is <b>invasive</b>, i.e., it replaces the label provider of the structure viewer with a wrapper label provider
 * that is able to show boxes around the matching search text.
 * <p>
 * <p>
 * If your content trees are potentially infinite, or you otherwise wish to control which parts of the tree are searched, you can specialize each structured viewer's {@link StructuredViewerTreeIterator}.
 * If you have specialized property sheet's {@link PropertySheetPage#setSorter(PropertySheetSorter) sorter}, you must {@link #setCollator(Collator) set the collator} used by this implementation to sort properties and property categories.
 * </p>
 *
 * @since 2.14
 */
public final class FindAndReplaceTarget implements IFindReplaceTarget, IFindReplaceTargetExtension, IFindReplaceTargetExtension3
{
  /**
   * A map for managing the association between a workbench part and it associated find and replace target.
   */
  private static final Map<IWorkbenchPart, FindAndReplaceTarget> FIND_AND_REPLACE_TARGETS = new HashMap<IWorkbenchPart, FindAndReplaceTarget>();

  /**
   * A styler for drawing a box around matching text.
   */
  private static final Styler MATCH_STYLER = new Styler()
    {
      @Override
      public void applyStyles(TextStyle textStyle)
      {
        textStyle.borderStyle = SWT.BORDER_SOLID;
      }
    };

  /**
   * Provides access to the {@code fIsRegExCheckBox} of a {@code org.eclipse.ui.texteditor.FindReplaceDialog}.
   */
  private static final Field F_IS_REGEX_CHECK_BOX_FIELD;

  /**
   * Provides access to the {@code fSelectedRangeRadioButton} of a {@code org.eclipse.ui.texteditor.FindReplaceDialog}.
   */
  private static final Field F_SELECTED_RANGE_RADIO_BUTTON_FIELD;

  /**
   * Provides access to the {@code fTarget} of a {@code org.eclipse.ui.texteditor.FindReplaceDialog}.
   */
  private static final Field F_TARGET_FIELD;

  /**
   * Provides access to the {@code findReplaceLogic} of a {@code org.eclipse.ui.texteditor.FindReplaceDialog}.
   */
  private static final Field FIND_REPLACE_LOGIC_FIELD;

  static
  {
    Field fIsRegExCheckBoxField = null;
    Field fSelectedRangeRadioButtonField = null;
    Field fTarget = null;
    Field findReplaceLogic = null;
    try
    {
      Class<?> findReplaceDialogClass = CommonPlugin.loadClass("org.eclipse.ui.workbench.texteditor", "org.eclipse.ui.texteditor.FindReplaceDialog");
      fIsRegExCheckBoxField = findReplaceDialogClass.getDeclaredField("fIsRegExCheckBox");
      fIsRegExCheckBoxField.setAccessible(true);
      fSelectedRangeRadioButtonField = findReplaceDialogClass.getDeclaredField("fSelectedRangeRadioButton");
      fSelectedRangeRadioButtonField.setAccessible(true);
      try
      {
        fTarget = findReplaceDialogClass.getDeclaredField("fTarget");
        fTarget.setAccessible(true);
      }
      catch (Throwable throwable)
      {
        findReplaceLogic = findReplaceDialogClass.getDeclaredField("findReplaceLogic");
        findReplaceLogic.setAccessible(true);
      }
    }
    catch (Throwable throwable)
    {
      // Ignore.
    }
    F_IS_REGEX_CHECK_BOX_FIELD = fIsRegExCheckBoxField;
    F_SELECTED_RANGE_RADIO_BUTTON_FIELD = fSelectedRangeRadioButtonField;
    F_TARGET_FIELD = fTarget;
    FIND_REPLACE_LOGIC_FIELD = findReplaceLogic;
  }

  /**
   * We need a tiny font to produce a bit of white space in the labels.
   * @see #hookLabelProvider()
   */
  private static final Map<Font, Font> TINY_FONT = new HashMap<Font, Font>();

  /**
   * A listener for when parts are closed to clean up the {@link #FIND_AND_REPLACE_TARGETS}.
   * @see #getAdapter(Class, IWorkbenchPart, AbstractUIPlugin)
   */
  private static final IPartListener PART_LISTENER = new IPartListener()
    {
      public void partOpened(IWorkbenchPart part)
      {
      }

      public void partDeactivated(IWorkbenchPart part)
      {
      }

      public void partClosed(IWorkbenchPart part)
      {
        synchronized (FIND_AND_REPLACE_TARGETS)
        {
          FindAndReplaceTarget findAndReplaceTarget = FIND_AND_REPLACE_TARGETS.remove(part);
          if (findAndReplaceTarget != null)
          {
            findAndReplaceTarget.dispose();
          }
        }
      }

      public void partBroughtToTop(IWorkbenchPart part)
      {
      }

      public void partActivated(IWorkbenchPart part)
      {
      }
    };

  /**
   * The workbench part of this find and replace target.
   */
  private final IWorkbenchPart workbenchPart;

  /**
   * The plugin used for saving and fetching the dialog settings of the find/replace dialog.
   */
  private final AbstractUIPlugin plugin;

  /**
   * The collator used to {@link org.eclipse.emf.edit.ui.util.FindAndReplaceTarget.TextData#sort(List, Object) sort properties}.
   */
  Collator collator = Collator.getInstance();

  /**
   * A cleanup action for removing the paint lister from the properties view and for refreshing its labels.
   *
   * @see FindAndReplaceTarget#propertiesCleanup()
   * @see #setSelection(boolean, StructuredViewer, org.eclipse.emf.edit.ui.util.FindAndReplaceTarget.Data.Item, int, Pattern)
   */
  private Runnable propertiesCleanup;

  /**
   * A cleanup action for when the session ends.
   *
   * @see #addSearchTypeControl()
   * @see #endSession()
   */
  private Runnable sessionCleanup;

  /**
   * A cleanup action for when the workbench part is disposed.
   *
   * @see #addSearchTypeControl()
   * @see #dispose()
   */
  private Runnable disposeCleanup;

  /**
   * If an advanced property needs to be displayed for search results and that action is currently not checked,
   * it will be checked and this cleanup action will be set to restore the state when the session ends.
   *
   * @see #setSelection(boolean, StructuredViewer, org.eclipse.emf.edit.ui.util.FindAndReplaceTarget.Data.Item, int, Pattern)
   */
  private Runnable filterChangeCleanup;

  /**
   * Properties are always search assuming the are categorized.
   * The properties view state will be changed to match, and if it was changed, this cleanup action will be set to restore the state when the session ends.
   *
   * @see #setSelection(boolean, StructuredViewer, org.eclipse.emf.edit.ui.util.FindAndReplaceTarget.Data.Item, int, Pattern)
   */
  private Runnable categoriesChangeCleanup;

  /**
   * Set to the objects in the selection of the viewer by {@link #getLineSelection()} and used by {@link #setScope(IRegion)}.
   */
  private List<?> selectionScope;

  /**
   * Use to control the scope of all objects that will be searched.
   */
  private Set<Object> selectionScopeObjects;

  /**
   * Initialized with the initial text to search based on the text of whatever is select in whatever viewer is active.
   */
  private String selectionText;

  /**
   * The current selected item.
   * @see #setSelection(boolean, StructuredViewer, org.eclipse.emf.edit.ui.util.FindAndReplaceTarget.Data.Item, int, Pattern)
   */
  private Data.Item selectedItem;

  /**
   * The current selection start.
   * @see #setSelection(boolean, StructuredViewer, org.eclipse.emf.edit.ui.util.FindAndReplaceTarget.Data.Item, int, Pattern)
   */
  private int selectedItemStart;

  /**
   * The current pattern being selected.
   * @see #setSelection(boolean, StructuredViewer, org.eclipse.emf.edit.ui.util.FindAndReplaceTarget.Data.Item, int, Pattern)
   */
  private Pattern selectedItemPattern;

  /**
   * The command for doing replace-all.
   * @see #replaceSelectionAll(String, boolean)
   * @see #setReplaceAllMode(boolean)
   */
  private CompoundCommand replaceAllCommand;

  /**
   * Used for processing all replacements.
   */
  private int pendingReplacements = -1;

  /**
   * Used to control whether replace-all is enabled. Of course you can only modify editable properties.
   */
  private boolean findReplaceable;

  /**
   * The types of things to search.
   */
  private FindAndReplaceTarget.SearchType searchType;

  /**
   * A tree item in the properties view that's currently being processed for search/replace.
   */
  private TreeItem specialTreeItem;

  /**
   * If the special tree item can't show the matching text, e.g., because there are control characters, this handles the display of the matching text.
   */
  private int specialStart;

  /**
   * This avoids changes in {@link #setScope(IRegion)} while the properties view is being made visible.
   */
  private boolean suspendScopeChanges;

  /**
   * Adapter the workbench part to an {@link IFindReplaceTarget}.
   * @param adapter the adapter type; only {@code IFindReplaceTarget.class} will return a non-null result.
   * @param workbenchPart the workbench part to adapt.
   * @param plugin the plugin used for {@link AbstractUIPlugin#getDialogSettings() dialog settings}.
   * @return a {@link FindAndReplaceTarget} or {@code null}.
   */
  public static <T> T getAdapter(Class<T> adapter, IWorkbenchPart workbenchPart, AbstractUIPlugin plugin)
  {
    if (adapter == IFindReplaceTarget.class)
    {
      synchronized (FindAndReplaceTarget.FIND_AND_REPLACE_TARGETS)
      {
        FindAndReplaceTarget findAndReplaceTarget = FindAndReplaceTarget.FIND_AND_REPLACE_TARGETS.get(workbenchPart);
        if (findAndReplaceTarget == null)
        {
          workbenchPart.getSite().getPage().addPartListener(PART_LISTENER);
          findAndReplaceTarget = new FindAndReplaceTarget(workbenchPart, plugin);
          FindAndReplaceTarget.FIND_AND_REPLACE_TARGETS.put(workbenchPart, findAndReplaceTarget);
        }
        return adapter.cast(findAndReplaceTarget);
      }
    }
    return null;
  }

  /**
   * Creates an instance for the given workbench part and the plugin used for {@link AbstractUIPlugin#getDialogSettings() dialog settings}.
   * @param workbenchPart the workbench part.
   * @param plugin the plugin used for dialog settings.
   */
  private FindAndReplaceTarget(IWorkbenchPart workbenchPart, AbstractUIPlugin plugin)
  {
    this.workbenchPart = workbenchPart;
    this.plugin = plugin;
  }

  /**
   * Returns the collator used to sort properties and property categories.
   * @return the collator used to sort properties and property categories.
   */
  public Collator getCollator()
  {
    return collator;
  }

  /**
   * Sets the collator used to sort properties and property categories.
   * @param collator the collator used to sort properties and property categories.
   */
  public void setCollator(Collator collator)
  {
    this.collator = collator;
  }

  /**
   * Extracts the viewer from the workbench part.
   */
  protected StructuredViewer getViewer()
  {
    if (workbenchPart instanceof IViewerProvider)
    {
      IViewerProvider viewerProvider = (IViewerProvider)workbenchPart;
      Viewer viewer = viewerProvider.getViewer();
      if (viewer instanceof StructuredViewer)
      {
        return (StructuredViewer)viewer;
      }
    }

    return null;
  }

  /**
   * Returns the property sheet page of the workbench page's active property sheet.
   */
  protected ExtendedPropertySheetPage getActivePropertySheetPage()
  {
    IWorkbenchPart activePart = workbenchPart.getSite().getPage().getActivePart();
    if (activePart instanceof PropertySheet)
    {
      PropertySheet propertySheet = (PropertySheet)activePart;
      IPage currentPage = propertySheet.getCurrentPage();
      if (currentPage instanceof ExtendedPropertySheetPage)
      {
        ExtendedPropertySheetPage propertySheetPage = (ExtendedPropertySheetPage)currentPage;
        return propertySheetPage;
      }
    }

    return null;
  }

  /**
   * Returns the tree of the active property sheet page.
   */
  protected Tree getActivePropertySheetTree()
  {
    ExtendedPropertySheetPage propertySheetPage = getActivePropertySheetPage();
    if (propertySheetPage != null)
    {
      Control control = propertySheetPage.getControl();
      if (control instanceof Tree)
      {
        Tree tree = (Tree)control;
        return tree;
      }
    }

    return null;
  }

  public boolean isEditable()
  {
    // Editing is always supported.
    // Replace is selectively disabled when a particular selection is not editable.
    return true;
  }

  public boolean canPerformFind()
  {
    // As long as there is a viewer that has appropriate label and content providers, we can support find.
    StructuredViewer viewer = getViewer();
    return viewer != null && viewer.getLabelProvider() instanceof ILabelProvider && viewer.getContentProvider() instanceof IStructuredContentProvider;
  }

  /**
   * Called by {@link #beginSession()} to populate the initial {@link #selectionText}.
   */
  protected void initialize(IWorkbenchPart workbenchPart)
  {
    // This method is called by the find and replace action before it opens the find and replace dialog.
    // It has the opportunity to see the state before the find and replace dialog takes focus away.
    // In particular, it can see the selected text in an active cell editor in the properties view.
    StructuredViewer viewer = getViewer();
    Tree propertySheetTree = getActivePropertySheetTree();
    if (propertySheetTree != null)
    {
      // If there is an active property sheet with a tree, iterate over the selected tree items.
      for (TreeItem treeItem : propertySheetTree.getSelection())
      {
        // Determine if there is an EMF property descriptor associated with it.
        Collection<? extends PropertyDescriptor> propertyDescriptors = getPropertyDescriptors(treeItem).keySet();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors)
        {
          // If so, extract the object and look for it in the data.
          Object object = propertyDescriptor.getObject();
          for (FindAndReplaceTarget.Data data : new TextData(viewer, collator))
          {
            if (data.object == object)
            {
              // Look for the feature in the data items.
              Object feature = getFeature(propertyDescriptor);
              for (Data.Item item : data.items)
              {
                Object itemFeature = item.getFeature();
                if (itemFeature == feature)
                {
                  // If there is a focus text control, it must be the cell editor of this property.
                  Control control = workbenchPart.getSite().getShell().getDisplay().getFocusControl();
                  if (control instanceof Text)
                  {
                    // Extract the selected text, if any...
                    Text text = (Text)control;
                    selectionText = text.getSelectionText();
                    if (selectionText.length() > 0)
                    {
                      // Use this item's selected text as our initial selection.
                      Point selection = text.getSelection();
                      setSelection(true, viewer, item, selection.x, Pattern.compile(Pattern.quote(selectionText)));

                      // Then we're done.
                      return;
                    }
                  }

                  // Use this overall item as our initial selection.
                  setSelection(true, viewer, item, 0, Pattern.compile(Pattern.quote(item.value)));
                  return;
                }
              }

              // Once we've passed the object of interest, there is nothing left to do.
              break;
            }
          }
        }
      }
    }
    else
    {
      // Otherwise, use the first item selected in the viewer as our initial selection.
      List<?> list = ((IStructuredSelection)viewer.getSelection()).toList();
      for (FindAndReplaceTarget.Data data : new TextData(viewer, collator))
      {
        if (list.contains(data.object))
        {
          Data.Item item = data.items.get(0);
          setSelection(true, viewer, item, 0, Pattern.compile(Pattern.quote(item.value)));
          return;
        }
      }
    }
  }

  public void beginSession()
  {
    initialize(workbenchPart);

    // When the session start, we add our search-type control.
    addSearchTypeControl();
  }

  /**
   * Used to access the private fields of {@org.eclipse.ui.texteditor.FindReplaceDialog}.
   */
  private static Object getFieldValue(Dialog dialog, Field field)
  {
    try
    {
      return field.get(dialog);
    }
    catch (Exception exception)
    {
      return null;
    }
  }

  /**
   * Modifies the  {@org.eclipse.ui.texteditor.FindReplaceDialog} to add our {@link SearchType search type controls}.
   */
  protected void addSearchTypeControl()
  {
    // Look through all child shells...
    Shell workbenchShell = workbenchPart.getSite().getShell();
    Shell[] shells = workbenchShell.getShells();
    for (final Shell shell : shells)
    {
      // If this is a shell for the find and replace dialog...
      Object data = shell.getData();
      if (data instanceof Dialog && "org.eclipse.ui.texteditor.FindReplaceDialog".equals(data.getClass().getName()))
      {
        // Find the last checkbox in the dialog.
        final Dialog dialog = (Dialog)data;
        Object checkBox = getFieldValue(dialog, F_IS_REGEX_CHECK_BOX_FIELD);
        if (checkBox instanceof Button)
        {
          // It should have grid data.
          Button checkBoxButton = (Button)checkBox;
          Object layoutData = checkBoxButton.getLayoutData();
          if (layoutData instanceof GridData)
          {
            // Change it's span and alignment to make room for our additional control.
            final GridData checkBoxGridData = (GridData)layoutData;
            if (checkBoxGridData.horizontalSpan == 2)
            {
              checkBoxGridData.verticalAlignment = SWT.TOP;
              checkBoxGridData.horizontalSpan = 1;
            }

            // Keep state in a section of our plugin's dialog settings.
            IDialogSettings pluginDialogSettings = plugin.getDialogSettings();
            final IDialogSettings dialogSettings = pluginDialogSettings.getSection("org.eclipse.ui.texteditor.FindReplaceDialog") == null
              ? pluginDialogSettings.addNewSection("org.eclipse.ui.texteditor.FindReplaceDialog") : pluginDialogSettings.getSection("org.eclipse.ui.texteditor.FindReplaceDialog");

            // Create a search-type combo in the same group as the checkbox.
            final Composite group = checkBoxButton.getParent();
            final Combo combo = new Combo(group, SWT.READ_ONLY | SWT.DROP_DOWN);
            combo.setItems(SearchType.getLabels());
            GridData gridData = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
            combo.setLayoutData(gridData);

            // The initial choice is remembered from the dialog settings.
            searchType = SearchType.getSearchType(dialogSettings.get("search-type"));
            combo.select(searchType.ordinal());

            // Listen for changes in the choice.
            combo.addSelectionListener(new SelectionAdapter()
              {
                @Override
                public void widgetSelected(SelectionEvent e)
                {
                  // Not only remember the choice, but record it in the dialog settings.
                  searchType = SearchType.values()[combo.getSelectionIndex()];
                  dialogSettings.put("search-type", searchType.key());
                }
              });

            String oldLabel = null;
            Button selectedRangeButton = null;
            Object selectedRange = getFieldValue(dialog, F_SELECTED_RANGE_RADIO_BUTTON_FIELD);
            if (selectedRange instanceof Button)
            {
              selectedRangeButton = (Button)selectedRange;
              String label = selectedRangeButton.getText();
              if (label.endsWith("lines"))
              {
                oldLabel = label;
                selectedRangeButton.setText(label.substring(0, label.length() - 5) + " elements");
              }
            }

            // Relayout the shell.
            shell.layout(true, true);

            // If we've never checked that the default size of the dialog is big enough to fit our controls,
            // do it this time.
            if (!dialogSettings.getBoolean("resized"))
            {
              // Determine the bottom right corner location of the combo in absolute coordinates.
              Rectangle comboBounds = combo.getBounds();
              Point comboBottomRightLocation = group.toDisplay(comboBounds.x + comboBounds.width, comboBounds.y + comboBounds.height);

              // Determine the bottom right corner location of the containing group in absolute coordinates.
              Rectangle groupBounds = group.getBounds();
              Point groupBottomRightLocation = group.getParent().toDisplay(groupBounds.x + groupBounds.width, groupBounds.y + groupBounds.height);

              // Determine how much too small each dimension might be.
              // If there is less that 8 pixes of padding in either direction...
              int widthDelta = groupBottomRightLocation.x - comboBottomRightLocation.x;
              int heightDelta = groupBottomRightLocation.y - comboBottomRightLocation.y;
              if (widthDelta < 8 || heightDelta < 8)
              {
                // Increase the shell size so that the search-type combo fits nicely.
                Point shellSize = shell.getSize();

                if (widthDelta < 8)
                {
                  shellSize.x -= widthDelta - 8;
                }

                if (heightDelta < 8)
                {
                  shellSize.y -= heightDelta - 8;
                }

                shell.setSize(shellSize);
              }

              // Only do this once in this workspace.
              dialogSettings.put("resized", true);
            }

            // Setup the task that needs to be done to undo what we've done here.
            final String finalOldLabel = oldLabel;
            final Button finalSelectRangeButton = selectedRangeButton;
            sessionCleanup = new Runnable()
              {
                public void run()
                {
                  // Restore the grid data and dispose our control.
                  checkBoxGridData.horizontalSpan = 2;
                  checkBoxGridData.verticalAlignment = SWT.CENTER;
                  combo.dispose();

                  // Clean up the label change that we did.
                  if (finalOldLabel != null)
                  {
                    finalSelectRangeButton.setText(finalOldLabel);
                  }

                  group.getDisplay().asyncExec(new Runnable()
                    {
                      public void run()
                      {
                        // Defer the layout so that when the editor is switched to another EMF editor that supports find and replace,
                        // we don't see a lot of flickering.
                        if (!group.isDisposed() && !shell.isDisposed())
                        {
                          shell.layout(true, true);
                        }
                      }
                    });

                  if (filterChangeCleanup != null)
                  {
                    filterChangeCleanup.run();
                  }

                  if (categoriesChangeCleanup != null)
                  {
                    categoriesChangeCleanup.run();
                  }

                  sessionCleanup = null;
                }
              };

            disposeCleanup = new Runnable()
              {
                public void run()
                {
                  // It may be the case that the properties view has focus and the editor being closed is the last one.
                  // The find-and-replace target is not updated in this case.
                  // So set it to null to ensure that the target is updated.
                  //
                  if (F_TARGET_FIELD != null)
                  {
                    Object fieldValue = getFieldValue(dialog, F_TARGET_FIELD);
                    if (fieldValue == FindAndReplaceTarget.this)
                    {
                      try
                      {
                        Method method = dialog.getClass().getMethod("updateTarget", IFindReplaceTarget.class, boolean.class, boolean.class);
                        method.setAccessible(true);
                        method.invoke(dialog, null, false, false);
                      }
                      catch (Exception exception)
                      {
                        // Ignore.
                      }
                    }
                  }
                  else
                  {
                      Object fieldValue = getFieldValue(dialog, FIND_REPLACE_LOGIC_FIELD);
                      try
                      {
                         Object target = FIND_REPLACE_LOGIC_FIELD.getType().getMethod("getTarget").invoke(fieldValue);
                         if (target == FindAndReplaceTarget.this)
                         {
                           fieldValue.getClass().getMethod("updateTarget", IFindReplaceTarget.class, boolean.class).invoke(fieldValue, null, false);
                         }
                      }
                      catch (Exception exception)
                      {
                        // Ignore.
                      }
                    
                  }
                  disposeCleanup = null;
                }
              };
          }
        }
      }
    }
  }

  private void dispose()
  {
    if (disposeCleanup != null)
    {
      disposeCleanup.run();
    }
  }

  public void endSession()
  {
    // When the session ends, we clean up or search-type control.
    if (sessionCleanup != null)
    {
      sessionCleanup.run();
    }

    // Also do the clean that's done when the find and replace dialog loses focus.
    setScope(null);
  }

  public Point getLineSelection()
  {
    // This method is used only to compute region to pass to setScope.
    // So instead of computing something useless we use this opportunity to remember the viewer's selection.
    StructuredViewer viewer = getViewer();
    selectionScope = ((IStructuredSelection)viewer.getSelection()).toList();
    return new Point(0, 0);
  }

  public IRegion getScope()
  {
    // This method is kind of useless, we remember our scope when the getLineSelection is called.
    return new Region(0, 0);
  }

  public void setScope(IRegion scope)
  {
    // When the properties view is activated, it's sometimes given focus.
    // In that case we restore the focus to the dialog, but we need to given the scope changes that are caused by the transient focus changes.
    if (suspendScopeChanges)
    {
      return;
    }

    StructuredViewer viewer = getViewer();
    if (scope == null)
    {
      // Remember the objects that need label updating.
      Object[] selectionScopeObjectsToUpdate = selectionScopeObjects == null ? null : selectionScopeObjects.toArray();
      Object selectionToUpdate = selectedItem != null && selectedItem.itemPropertyDescriptor == null ? selectedItem.data.object : null;

      // The scope is set to null when the dialog loses focus.
      // We should forget about most of our state at this point.
      selectionText = null;
      selectedItem = null;
      findReplaceable = false;
      selectionScopeObjects = null;

      propertiesCleanup();

      // Update update the selection scope objects.
      if (selectionScopeObjectsToUpdate != null)
      {
        viewer.update(selectionScopeObjectsToUpdate, null);
      }

      // Update the selection object.
      if (selectionToUpdate != null)
      {
        viewer.update(selectionToUpdate, null);
      }
    }
    else
    {
      // Record the objects in the selection scope.
      // These will be painted in a special way in the first to highlight them.
      selectionScopeObjects = new HashSet<Object>();

      int depth = -1;
      for (FindAndReplaceTarget.Data data : new TextData(viewer, collator))
      {
        // If we hit the next object at the same depend, reset the depth.
        if (data.depth == depth)
        {
          depth = -1;
        }

        // If the object is directly in scope.
        if (selectionScope.contains(data.object))
        {
          // Remember the depth if we haven't remember one already.
          if (depth == -1)
          {
            depth = data.depth;
          }
        }

        // If the object is a selected object or nested under a selected object, include it.
        if (depth != -1)
        {
          selectionScopeObjects.add(data.object);
        }
      }

      // Hook up our decorating label provider for the current viewer.
      hookLabelProvider();

      // Eliminate the selection.
      viewer.setSelection(StructuredSelection.EMPTY);
    }
  }

  /**
   * Handles the cleanup of the properties view.
   * @see #propertiesCleanup
   */
  protected void propertiesCleanup()
  {
    // Clean up the stuff we did to the properties view.
    if (propertiesCleanup != null)
    {
      propertiesCleanup.run();
    }
  }

  public void setScopeHighlightColor(Color color)
  {
    // This is never called by the find and replace dialog.
  }

  public String getSelectionText()
  {
    // This method is called for three different reasons.
    // 1 - For the initial text in the find field.
    // 2 - For enabling the replace button.
    // 3 - For recording a "selection" history; goodness knows what that's for though!
    //
    // If we're updating the button state, the selection text is used to enable/disable the replace button state.
    // We want it enabled only if we've selected an editable property.
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    if ("updateButtonState".equals(stackTrace[2].getMethodName())
      && (selectedItem == null || selectedItem.itemPropertyDescriptor == null || !selectedItem.itemPropertyDescriptor.canSetProperty(selectedItem.data.object)))
    {
      return "";
    }

    // We use this only to initialize the text selection from a cell editor.
    // Once we've done a find, we clean this field.
    if (selectionText != null)
    {
      return selectionText;
    }

    // If there is a selected item, return its value.
    if (selectedItem != null)
    {
      return selectedItem.value;
    }

    // Otherwise we have no selection; we must return a non-null value.
    return "";
  }

  public Point getSelection()
  {
    if (pendingReplacements >= 0)
    {
      return new Point(pendingReplacements, 0);
    }

    // This method is super important for determining the point from which the next find processing will proceed.
    // If there is a selected item, we should proceed from the end of the current match.
    if (selectedItem != null)
    {
      Matcher matcher = selectedItemPattern.matcher(selectedItem.value);
      int size = 0;
      if (selectedItem.value.length() >= selectedItemStart && matcher.find(selectedItemStart))
      {
        size = matcher.group().length();
      }

      Point point = new Point(selectedItem.index + selectedItemStart, size);
      return point;
    }

    // If there is an active property sheet tree.
    StructuredViewer viewer = getViewer();
    Tree propertySheetTree = getActivePropertySheetTree();
    if (propertySheetTree != null)
    {
      // Look through the selection.
      for (final TreeItem treeItem : propertySheetTree.getSelection())
      {
        // If it has and EMF property descriptor
        Collection<? extends PropertyDescriptor> propertyDescriptors = getPropertyDescriptors(treeItem).keySet();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors)
        {
          // Determine it's wrapped object and look for it in the induced text data.
          Object object = propertyDescriptor.getObject();
          for (FindAndReplaceTarget.Data data : new TextData(viewer, collator))
          {
            // If we find it...
            if (data.object == object)
            {
              // Determine which feature is the selected feature.
              Object feature = getFeature(propertyDescriptor);

              // Collection all features before the selected feature.
              List<Object> features = new ArrayList<Object>();
              LOOP: for (final TreeItem otherTreeIem : propertySheetTree.getItems())
              {
                Collection<? extends PropertyDescriptor> otherPropertyDescriptors = getPropertyDescriptors(otherTreeIem).keySet();
                for (PropertyDescriptor otherPropertyDescriptor : otherPropertyDescriptors)
                {
                  Object otherFeature = getFeature(otherPropertyDescriptor);
                  features.add(otherFeature);
                  if (otherFeature == feature)
                  {
                    break LOOP;
                  }
                }
              }

              // Consider all the items.
              Data.Item candidate = null;
              for (Data.Item item : data.items)
              {
                // If we find an exact match, return the information for it immediately, otherwise consider it a candidate.
                Object itemFeature = item.getFeature();
                if (itemFeature == feature)
                {
                  return new Point(item.index, 0);
                }
                else if (features.contains(itemFeature))
                {
                  candidate = item;
                }
              }

              // If there is a candidate, return the information for it.
              if (candidate != null)
              {
                return new Point(candidate.index, 0);
              }

              // If we find nothing, there's no point in looking anywhere else.
              break;
            }
          }
        }
      }
    }

    // Otherwise find the first item of an object in the selection.
    List<?> list = ((IStructuredSelection)viewer.getSelection()).toList();
    for (FindAndReplaceTarget.Data data : new TextData(viewer, collator))
    {
      if (list.contains(data.object))
      {
        return new Point(data.items.get(0).index, 0);
      }
    }

    // Start at the beginning.
    return new Point(0, 0);
  }

  public void setSelection(int offset, int length)
  {
    // This method is always called right before setScope.
    // The information it provides is not useful and can be ignored.
  }

  public int findAndSelect(int offset, String findString, boolean searchForward, boolean caseSensitive, boolean wholeWord)
  {
    // This is never called, but we forward it sensibly anyway.
    return findAndSelect(offset, findString, searchForward, caseSensitive, wholeWord, false);
  }

  public int findAndSelect(int offset, String findString, boolean searchForward, boolean caseSensitive, boolean wholeWord, boolean regExSearch)
  {
    // Clear out the text used to populate the search text field once we've done a search.
    selectionText = null;

    // Compile the raw pattern early so it can throw an exception if it's not well formed.
    // The information in that exception is displayed to the user.
    if (regExSearch)
    {
      Pattern.compile(findString);
    }

    // A pattern will be constructed depending on the search parameters.
    String impliedPattern = findString;

    // If we're not doing a regular expression search, quote the pattern.
    if (!regExSearch)
    {
      impliedPattern = Pattern.quote(impliedPattern);
    }

    // If we want case-insensitive matching, encode that in the pattern.
    if (!caseSensitive)
    {
      impliedPattern = "(?i)" + impliedPattern;
    }

    // If we want whole word matching, add the word break delimiters to the pattern.
    if (wholeWord)
    {
      impliedPattern = "\\b" + impliedPattern + "\\b";
    }

    // This should always compile correctly.
    Pattern pattern = Pattern.compile(impliedPattern);

    // If there are pending replacements, don't bother searching.
    if (pendingReplacements >= 0)
    {
      selectedItemPattern = pattern;
      return --pendingReplacements;
    }

    StructuredViewer viewer = getViewer();

    // Iterate over the induced text, keeping track of a candidates for the case of backward searching.
    Data.Item candidate = null;
    int candidateStart = -1;
    LOOP: for (FindAndReplaceTarget.Data data : new TextData(viewer, collator))
    {
      // If we have no restricted scope or the we and the object is in that scope...
      if (selectionScopeObjects == null || selectionScopeObjects.contains(data.object))
      {
        // Iterate over the items.
        for (Data.Item item : data.items)
        {
          // If we're searching forward and the item is at or above the offset or the end of the string is above the offset...
          // Otherwise if were's searching backward and the offset is -1, because we're wrapped, or the item is before the offset...
          if (searchForward ? item.index >= offset || item.index + item.value.length() > offset : offset == -1 || item.index < offset)
          {
            // If we've just done a replace, we make sure that the next find will find something that can be replaced, i.e., a modifiable attribute.
            // Otherwise we make sure that the item is included by the search type.
            if (findReplaceable ? !SearchType.MODIFIABLE_ATTRIBUTE.isIncluded(item) : searchType != null && !searchType.isIncluded(item))
            {
              continue;
            }

            // When searching forward, we need to make sure that we begin the pattern match where it skips the stuff before the current offset.
            int begin = 0;
            if (searchForward && item.index < offset)
            {
              begin = offset - item.index;
            }

            // Look for a match from the starting point.
            Matcher matcher = pattern.matcher(item.value);
            if (matcher.find(begin))
            {
              // Determine the offset of the match.
              int start = matcher.start();

              if (searchForward)
              {
                // If we're searching forward, so record and mark this selection point.
                setSelection(false, viewer, item, start, pattern);

                // Mark the fact that we've done a find but not a replace yet.
                findReplaceable = false;

                // Return the appropriate absolute index where we matched.
                return item.index + start;
              }
              // Otherwise, if we're searching backward all the way to the end, or the match is not past the target offset...
              else if (offset == -1 || item.index + start <= offset)
              {
                // This is definitely a candidate.
                candidate = item;
                candidateStart = start;

                // But keep searching for a better candidate later in the string.
                while (matcher.find())
                {
                  // Repeat the same logic.
                  start = matcher.start();
                  if (offset == -1 || item.index + start <= offset)
                  {
                    candidate = item;
                    candidateStart = start;
                  }
                }
              }
            }
          }
          // If we're searching backward and we've gone as far as we need to go...
          else if (!searchForward && item.index > offset)
          {
            // Break from the loop.
            break LOOP;
          }
        }
      }
    }

    // If there is a candidate (which is only possible if we're searching backwards...
    if (candidate != null)
    {
      // Record and mark this selection point.
      setSelection(false, viewer, candidate, candidateStart, pattern);

      // Mark the fact that we've done a find but not a replace yet.
      findReplaceable = false;

      // Return the appropriate absolute index where we matched.
      return candidate.index + candidateStart;
    }

    // There is no match.
    return -1;
  }

  /**
   * This records a match either initially or as a result of a find.
   */
  protected void setSelection(boolean preserve, StructuredViewer viewer, Data.Item item, final int start, Pattern pattern)
  {
    Object selectedObjectToUpdate = selectedItem != null && selectedItem.itemPropertyDescriptor == null ? selectedItem.data.object : null;

    // Remember the information about the item, pattern, and offset within the item of the match.
    selectedItem = item;
    selectedItemPattern = pattern;
    selectedItemStart = start;

    if (selectedObjectToUpdate != null)
    {
      viewer.update(selectedObjectToUpdate, null);
    }

    // There is no special tree item anymore.
    specialTreeItem = null;

    // If we haven't already done so, hook up the special label provider for providing selection feedback.
    hookLabelProvider();

    // Clean any previous stuff we did to decorate the properties view.
    propertiesCleanup();

    // In replace all mode, we don't want to provide any further feedback.
    if (replaceAllCommand == null)
    {
      // Select the item in the viewer, unless we're preserving the selection, i.e., during the initial feedback.
      ISelection selection = new StructuredSelection(new TreePath(item.data.getPath()));
      if (!preserve)
      {
        viewer.setSelection(selection, true);
        selection = viewer.getSelection();

        // If there is an active property page, update its selection immediately.
        ExtendedPropertySheetPage activePropertySheetPage = getActivePropertySheetPage();
        if (activePropertySheetPage != null)
        {
          activePropertySheetPage.selectionChanged(workbenchPart, selection);
        }
      }

      // Make the properties view visible, creating it if necessary.
      IWorkbenchPartSite site = workbenchPart.getSite();
      IWorkbenchPage page = site.getPage();
      IViewPart viewPart = page.findView("org.eclipse.ui.views.PropertySheet");

      // Sometimes showing the properties view gives it focus, e.g., when the editor is maximized.
      Display display = site.getShell().getDisplay();
      Control oldFocusControl = display.getFocusControl();
      try
      {
        // Ignore scope changes while showing the properties view.
        suspendScopeChanges = true;
        if (item.itemPropertyDescriptor != null)
        {
          viewPart = page.showView("org.eclipse.ui.views.PropertySheet", null, IWorkbenchPage.VIEW_VISIBLE);
          if (viewPart == null)
          {
            viewPart = page.showView("org.eclipse.ui.views.PropertySheet", null, IWorkbenchPage.VIEW_CREATE);
          }
        }

        // Restore the focus.
        Control newFocusControl = display.getFocusControl();
        if (oldFocusControl != newFocusControl)
        {
          oldFocusControl.setFocus();
        }
      }
      catch (PartInitException exception)
      {
        plugin.getLog().log(new Status(IStatus.WARNING, plugin.getBundle().getSymbolicName(), 0, exception.getLocalizedMessage(), null));
      }
      finally
      {
        suspendScopeChanges = false;
      }

      // If it is a property sheet, as expected...
      if (viewPart instanceof PropertySheet)
      {
        // And the current page is a property sheet page as expected...
        final PropertySheet propertySheet = (PropertySheet)viewPart;
        IPage currentPage = propertySheet.getCurrentPage();
        if (currentPage instanceof ExtendedPropertySheetPage)
        {
          // And the control is a tree...
          Control control = currentPage.getControl();
          if (control instanceof Tree)
          {
            final Tree tree = (Tree)control;

            if (item.itemPropertyDescriptor != null)
            {
              // If the property has filter flags...
              String[] filterFlags = item.itemPropertyDescriptor.getFilterFlags(item.data.object);
              if (filterFlags != null)
              {
                for (String filterFlag : filterFlags)
                {
                  // If the filter is one for expert property...
                  if ("org.eclipse.ui.views.properties.expert".equals(filterFlag))
                  {
                    final IAction action = ((ExtendedPropertySheetPage)currentPage).getFilterAction();
                    if (!action.isChecked())
                    {
                      // Run the action to show advanced properties, and remember that.
                      action.setChecked(true);
                      action.run();
                      filterChangeCleanup = new Runnable()
                        {
                          public void run()
                          {
                            action.setChecked(false);
                            action.run();
                            filterChangeCleanup = null;
                          }
                        };
                    }
                  }
                }
              }

              // Remember the categories action that we need to ensure is checked because we search in an order that assumes this.
              final IAction categoriesAction = ((ExtendedPropertySheetPage)currentPage).getCategoriesAction();
              if (!categoriesAction.isChecked())
              {
                categoriesAction.setChecked(true);
                categoriesAction.run();
                categoriesChangeCleanup = new Runnable()
                  {
                    public void run()
                    {
                      categoriesAction.setChecked(false);
                      categoriesAction.run();
                      categoriesChangeCleanup = null;
                    }
                  };
              }

              // Walk the tree items.
              for (TreeItem rootTreeItem : tree.getItems())
              {
                // If there is an EMF property descriptor with a feature for the selected item...
                Map<? extends PropertyDescriptor, TreeItem> propertyDescriptors = getPropertyDescriptors(rootTreeItem);
                for (Map.Entry<? extends PropertyDescriptor, TreeItem> entry : propertyDescriptors.entrySet())
                {
                  PropertyDescriptor propertyDescriptor = entry.getKey();
                  final TreeItem propertyDescriptorTreeItem = entry.getValue();
                  if (propertyDescriptor.getFeature() == item.getFeature())
                  {
                    // Consider the label shown in the tree versus the value of the selected item...
                    String treeItemText = propertyDescriptorTreeItem.getText(1);
                    String itemValue = item.value;

                    // We might need to replace the tree item's text with a special representation...
                    specialStart = -1;

                    // If they are're identical....
                    if (!treeItemText.equals(itemValue))
                    {
                      // Find the match, which really must be there, do we can determine the length of the match.
                      Matcher matcher = pattern.matcher(itemValue);
                      if (matcher.find(start))
                      {
                        // Remember this special item, because we'll want to update it after we do a replace to show the replaced text.
                        specialTreeItem = propertyDescriptorTreeItem;

                        // If the end of the match is after the end of the tree item's text, or the strings up until the end of the match are not
                        // identical...
                        int end = matcher.end();
                        if (treeItemText.length() < end || !treeItemText.substring(0, end).equals(itemValue.substring(0, end)))
                        {
                          // Consider the starting point of the match, and work our way backward for 20 characters or until the preceding control
                          // character.
                          int begin = matcher.start();
                          specialStart = 2;
                          while (begin >= 0 && specialStart < 20 && !Character.isISOControl(itemValue.charAt(begin)))
                          {
                            ++specialStart;
                            --begin;
                          }

                          // Work our way forward until the end of the string or until we hit a control character.
                          int itemValueLength = itemValue.length();
                          while (end < itemValueLength && !Character.isISOControl(itemValue.charAt(end)))
                          {
                            ++end;
                          }

                          // Create a special string with ellipses at both ends.
                          String specialText = "..." + itemValue.substring(begin + 1, end) + "...";

                          // But that back into the item.
                          propertyDescriptorTreeItem.setText(1, specialText);

                          // Get the tree to redraw itself.
                          tree.redraw();
                        }
                      }
                    }

                    // Create a paint listener to select the match.
                    final Listener paintItemListener = new Listener()
                      {
                        private void paintItem(Event event, TreeItem item, int matchStart)
                        {
                          String text = item.getText(1);
                          Matcher matcher = selectedItemPattern.matcher(text);
                          if (matchStart < text.length() && matcher.find(matchStart))
                          {
                            // Compute the offset of the start of the matching, relative to the start of the text.
                            int start = matcher.start();
                            int x = event.gc.textExtent(text.substring(0, start)).x + item.getTextBounds(1).x - propertyDescriptorTreeItem.getBounds(1).x;

                            // Compute the offset at the end of the match, taking into account the width of the matching text.
                            int width = event.gc.textExtent(matcher.group()).x;
                            event.gc.drawRectangle(event.x + x + 1, event.y, width + 1, event.height - 1);
                          }
                          else if (text.endsWith("..."))
                          {
                            int x = event.gc.textExtent(text.substring(0, text.length() - 3)).x + propertyDescriptorTreeItem.getTextBounds(1).x
                              - propertyDescriptorTreeItem.getBounds(1).x;
                            int width = event.gc.textExtent("...").x;
                            event.gc.drawRectangle(event.x + x + 1, event.y, width + 1, event.height - 1);
                          }
                        }

                        public void handleEvent(Event event)
                        {
                          // If we're painting or special item...
                          TreeItem item = (TreeItem)event.item;
                          if (item == propertyDescriptorTreeItem && event.index == 1)
                          {
                            paintItem(event, item, specialStart == -1 ? start : specialStart);
                          }
                        }
                      };

                    // Add the listener.
                    tree.addListener(SWT.PaintItem, paintItemListener);

                    // Set up the runnable to clean up what we've done here.
                    final ExtendedPropertySheetPage propertySheetPage = (ExtendedPropertySheetPage)currentPage;
                    propertiesCleanup = new Runnable()
                      {
                        public void run()
                        {
                          // Remove the listener.
                          tree.removeListener(SWT.PaintItem, paintItemListener);

                          // Refresh the view.
                          propertySheetPage.refreshLabels();

                          propertiesCleanup = null;
                        }
                      };

                    // Select the item, and force a repaint.
                    tree.setSelection(propertyDescriptorTreeItem);
                    tree.redraw();

                    // We're done.
                    return;
                  }
                }
              }
            }

            // If we didn't find it at all, clear out the selection.
            tree.setSelection(new TreeItem [0]);
          }
        }
      }
    }
  }

  /**
   * The styled text decorator used to show highlight box around the matching text in structured viewer's label.
   */
  protected final class StyledLabelDecorator implements IStyledLabelDecorator
  {
    private final ILabelProvider labelProvider;

    // Use the color from the theme that the editor uses to highlight the scope.
    final Color color = workbenchPart.getSite().getWorkbenchWindow().getWorkbench().getThemeManager().getCurrentTheme().getColorRegistry().get("org.eclipse.ui.editors.findScope");

    final Styler scopeStyler = new Styler()
      {
        @Override
        public void applyStyles(TextStyle textStyle)
        {
          textStyle.background = color;
        }
      };

    private final Styler tinyFontStyler;

    private StyledLabelDecorator(ILabelProvider labelProvider, Styler tinyFontStyler)
    {
      this.labelProvider = labelProvider;
      this.tinyFontStyler = tinyFontStyler;
    }

    public void removeListener(ILabelProviderListener listener)
    {
      labelProvider.removeListener(listener);
    }

    public boolean isLabelProperty(Object element, String property)
    {
      return labelProvider.isLabelProperty(element, property);
    }

    public void dispose()
    {
      labelProvider.dispose();
    }

    public void addListener(ILabelProviderListener listener)
    {
      labelProvider.addListener(listener);
    }

    public String decorateText(String text, Object element)
    {
      if (labelProvider instanceof ILabelDecorator)
      {
        ILabelDecorator labelDecorator = (ILabelDecorator)labelProvider;
        return labelDecorator.decorateText(text, element);
      }

      return text;
    }

    public Image decorateImage(Image image, Object element)
    {
      if (labelProvider instanceof ILabelDecorator)
      {
        ILabelDecorator labelDecorator = (ILabelDecorator)labelProvider;
        return labelDecorator.decorateImage(image, element);
      }

      return image;
    }

    public StyledString decorateStyledText(StyledString styledString, Object element)
    {
      if (labelProvider instanceof IStyledLabelDecorator)
      {
        IStyledLabelDecorator styledLabelDecorator = (IStyledLabelDecorator)labelProvider;
        styledString = styledLabelDecorator.decorateStyledText(styledString, element);
      }

      // If we have a selected item, it's the item for the label, and this element is that selected element's object...
      if (selectedItem != null && selectedItem.itemPropertyDescriptor == null && element == selectedItem.data.object)
      {
        // Convert the styled string to just a string.
        String string = styledString.getString();

        // Find the pattern match within that string.
        Matcher matcher = selectedItemPattern.matcher(string);
        if (matcher.find(selectedItemStart))
        {
          // Create a new styles string.
          StyledString result = new StyledString();

          // Recompose the string with the match styled to show a selection box.
          String group = matcher.group();
          int start = matcher.start();
          int end = matcher.end();
          result.append(string.substring(0, start));
          Styler tinyPadding = new Styler()
            {
              @Override
              public void applyStyles(TextStyle textStyle)
              {
                MATCH_STYLER.applyStyles(textStyle);
                tinyFontStyler.applyStyles(textStyle);
              }
            };
          result.append(" ", tinyPadding);
          result.append(group, MATCH_STYLER);
          result.append(" ", tinyPadding);
          result.append(string.substring(end));
          result.append(" ", tinyFontStyler);
          return result;
        }
      }

      // If we have scope objects and the element is one of those...
      if (selectionScopeObjects != null && selectionScopeObjects.contains(element))
      {
        // Mark the entire string with the scope styling.
        StyledString result = new StyledString();
        result.append(styledString.getString(), scopeStyler);
        result.append(" ", tinyFontStyler);
        return result;
      }

      // Otherwise just pass through the string with a tiny bit of extra white space at the end.
      styledString.append(" ", tinyFontStyler);
      return styledString;
    }
  }

  /**
   * We use this special class so we can detect if the label provider is already hooked up.
   */
  protected static class DecoratingLabelProvider extends DelegatingStyledCellLabelProvider.FontAndColorProvider implements IStyledLabelProvider
  {
    public DecoratingLabelProvider(IStyledLabelProvider styledLabelProvider)
    {
      super(styledLabelProvider);
    }

    @Override
    public StyledString getStyledText(Object element)
    {
      return super.getStyledText(element);
    }
  }

  /**
   * This sets up a special label provider in the viewer to be able to highlight the scope and show the selected match.
   */
  protected void hookLabelProvider()
  {
    final StructuredViewer viewer = getViewer();

    // Create a very tiny font that can be used to make very tiny spaces.
    Font font = viewer.getControl().getFont();
    Font tinyFont = TINY_FONT.get(font);
    if (tinyFont == null)
    {
      FontData[] fontData = font.getFontData();
      for (FontData data : fontData)
      {
        data.setHeight(data.getHeight() / 4);
      }
      tinyFont = new Font(font.getDevice(), fontData);
      TINY_FONT.put(font, tinyFont);
    }

    // If the label provider is already hooked up...
    final ILabelProvider labelProvider = (ILabelProvider)viewer.getLabelProvider();
    if (labelProvider instanceof DecoratingLabelProvider)
    {
      // Update the selection scope objects.
      if (selectionScopeObjects != null)
      {
        viewer.update(selectionScopeObjects.toArray(), null);
      }

      // Update the selected object if it's for a label.
      if (selectedItem != null && selectedItem.itemPropertyDescriptor == null)
      {
        viewer.update(selectedItem.data.object, null);
      }
    }
    else
    {
      // Create a styler for creating very small spaces.
      final Font finalTinyFont = tinyFont;
      Styler tinyFontStyler = new Styler()
        {
          @Override
          public void applyStyles(TextStyle textStyle)
          {
            textStyle.font = finalTinyFont;
          }
        };
      // Create a styled label provider that can decorate the text.
      IStyledLabelProvider styledProvider = new DecoratingColumLabelProvider.StyledLabelProvider(labelProvider, new StyledLabelDecorator(labelProvider, tinyFontStyler))
        {
          {
            if (labelProvider instanceof CellLabelProvider)
            {
              cellLabelProvider = (CellLabelProvider)labelProvider;
            }
          }
        };

      // Hook up the label provider to be the one used by the view.
      ILabelProvider delegatingLabelProvider = new DecoratingLabelProvider(styledProvider);
      viewer.setLabelProvider(delegatingLabelProvider);
    }
  }

  public void replaceSelection(String text)
  {
    // This is never called, but delegate it appropriately nevertheless.
    replaceSelection(text, false);
  }

  public void replaceSelection(String text, boolean regExReplace)
  {
    // If we're in replace all mode.
    if (replaceAllCommand != null)
    {
      // And this is the first call to replace selection...
      if (pendingReplacements == Integer.MAX_VALUE - 1)
      {
        // Determine all the replacements that are applicable.
        pendingReplacements = replaceSelectionAll(text, regExReplace) - 1;
      }

      // Both find and replace will ignore the next pendingReplacements number of calls.
      return;
    }

    // If the selected item can't be modified...
    if (!SearchType.MODIFIABLE_ATTRIBUTE.isIncluded(selectedItem))
    {
      return;
    }

    // If the pattern doesn't match...
    Matcher matcher = selectedItemPattern.matcher(selectedItem.value);
    if (!matcher.find(selectedItemStart))
    {
      return;
    }

    // Build up the replacement.
    StringBuffer result = new StringBuffer();

    // Remember the start of the match...
    int start = matcher.start();

    // Escape the $ if we're not doing a regular expression replacement.
    String replacement = regExReplace ? text : text.replace("$", "\\$");

    // Append the replacement
    matcher.appendReplacement(result, replacement);

    // Remember exactly what we've replaced the pattern with.
    String actualReplacement = result.substring(start);

    // Complete the composition.
    matcher.appendTail(result);

    // We must have an editing domain and the selected item's feature must be a modifiable attribute.
    EditingDomain domain = ((IEditingDomainProvider)workbenchPart).getEditingDomain();
    EAttribute eAttribute = (EAttribute)selectedItem.getFeature();

    // Try to convert the value modified string to a value; this can fail.
    Object value;
    try
    {
      value = EcoreUtil.createFromString(eAttribute.getEAttributeType(), result.toString());
    }
    catch (RuntimeException exception)
    {
      return;
    }

    // If there is a special item in the properties view, we want to update the text to show the replacement.
    String replacementSpecialText = null;
    if (specialTreeItem != null && !specialTreeItem.isDisposed())
    {
      String specialText = specialTreeItem.getText(1);
      Matcher specialMatcher = selectedItemPattern.matcher(specialText);
      if (specialMatcher.find(specialStart))
      {
        StringBuffer specialResult = new StringBuffer();
        specialMatcher.appendReplacement(specialResult, replacement);
        specialMatcher.appendTail(specialResult);
        replacementSpecialText = specialResult.toString();
      }
    }

    // Remember the replacement as the pattern so that views showing the matching selection continue to show the matching replacement.
    selectedItemPattern = Pattern.compile(Pattern.quote(actualReplacement));

    Command setCommand;
    if (eAttribute.isMany())
    {
      Object propertyValue = selectedItem.itemPropertyDescriptor.getPropertyValue(selectedItem.data.object);
      if (propertyValue instanceof IItemPropertySource)
      {
        propertyValue = ((IItemPropertySource)propertyValue).getEditableValue(selectedItem.data.object);
      }

      // Compute the new overall value for the list.
      List<Object> values = new ArrayList<Object>((List<?>)propertyValue);
      values.set(selectedItem.itemIndex, value);

      // Create a command to set the overall list value.
      setCommand = SetCommand.create(domain, selectedItem.data.object, eAttribute, values);
    }
    else
    {
      // Create a command to set the value.
      setCommand = SetCommand.create(domain, selectedItem.data.object, eAttribute, value);
    }

    // If this is not in replace all mode, we need to be careful that command execution will cause notification that will try to select the affected
    // objects.
    // This messes up or attempts to track the selection progress in the case of replace/find.
    CompoundCommand wrapper = new CompoundCommand(CompoundCommand.MERGE_COMMAND_ALL)
      {
        boolean isFirst = true;

        @Override
        public Collection<?> getAffectedObjects()
        {
          // The first time this is called, it returns the empty list, so that no selection takes place.
          if (isFirst)
          {
            isFirst = false;
            return Collections.emptyList();
          }

          return super.getAffectedObjects();
        }
      };

    // Put the set command in the wrapper and execute the wrapper.
    wrapper.append(setCommand);
    domain.getCommandStack().execute(wrapper);

    // We need to wait for async executed updates to finish.
    Display display = getViewer().getControl().getShell().getDisplay();
    final boolean[] run = new boolean []{ true };
    display.asyncExec(new Runnable()
      {
        public void run()
        {
          run[0] = false;
        }
      });

    // Process the event queue until our runnable has run, at which point other runnables queued before ours will also have been completed.
    while (run[0] && display.readAndDispatch())
    {
      display.sleep();
    }

    // If we have a special item.
    if (specialTreeItem != null)
    {
      // And it's not disposed yet.
      if (!specialTreeItem.isDisposed() && replacementSpecialText != null)
      {
        // Update it to the replacement text, and force it to repaint to highlight the replacement.
        specialTreeItem.setText(1, replacementSpecialText);
        specialTreeItem.getParent().redraw();
      }

      // We can forget about it now.
      specialTreeItem = null;
    }

    // It's important to relocate our selected item, because the replacement could have updated text that appears earlier in our induced text view.
    // This would mess up the current selected item's index.
    for (FindAndReplaceTarget.Data data : new TextData(getViewer(), collator))
    {
      if (data.object == selectedItem.data.object)
      {
        for (Data.Item item : data.items)
        {
          if (selectedItem.itemPropertyDescriptor == item.itemPropertyDescriptor && selectedItem.itemIndex == item.itemIndex)
          {
            selectedItem = item;
          }
        }
      }
    }

    // We want the next find (especially for find/replace) to find only modifiable selections.
    findReplaceable = true;
  }

  /**
   * Use to help process all matches to be replaced.
   * @see #replaceSelection(String, boolean)
   */
  protected int replaceSelectionAll(String text, boolean regExReplace)
  {
    // Escape the $ if we're not doing a regular expression replacement.
    String replacement = regExReplace ? text : text.replace("$", "\\$");

    // We must have an editing domain.
    EditingDomain domain = ((IEditingDomainProvider)workbenchPart).getEditingDomain();

    int total = 0;
    for (Data data : new TextData(getViewer(), collator))
    {
      // We must defer creation of the set command for a multi-valued feature until after all its items are processed.
      EAttribute currentListAttribute = null;
      List<Object> currentListValue = null;

      for (Data.Item item : data.items)
      {
        // If the selected item can't be modified...
        if (!SearchType.MODIFIABLE_ATTRIBUTE.isIncluded(item))
        {
          continue;
        }

        // If the pattern doesn't match...
        Matcher matcher = selectedItemPattern.matcher(item.value);
        if (!matcher.find())
        {
          continue;
        }

        // Build up the replacement.
        StringBuffer result = new StringBuffer();

        // Keep track of the number of matches.
        int count = 0;
        do
        {
          // Append the replacement
          matcher.appendReplacement(result, replacement);
          ++count;
        }
        while (matcher.find());

        // Complete the composition.
        matcher.appendTail(result);

        // The feature must be an attribute.
        EAttribute eAttribute = (EAttribute)item.getFeature();

        // If we've deferred a multi-valued feature change, and this is a different feature
        if (currentListAttribute != null && eAttribute != currentListAttribute)
        {
          Command setCommand = SetCommand.create(domain, data.object, currentListAttribute, currentListValue);
          replaceAllCommand.append(setCommand);

          currentListAttribute = null;
          currentListValue = null;
        }

        // Try to convert the modified string to a value; this can fail.
        Object value;
        try
        {
          value = EcoreUtil.createFromString(eAttribute.getEAttributeType(), result.toString());
        }
        catch (RuntimeException exception)
        {
          continue;
        }

        total += count;

        if (eAttribute.isMany())
        {
          if (currentListValue == null)
          {
            currentListAttribute = eAttribute;

            Object propertyValue = item.itemPropertyDescriptor.getPropertyValue(data.object);
            if (propertyValue instanceof IItemPropertySource)
            {
              propertyValue = ((IItemPropertySource)propertyValue).getEditableValue(data.object);
            }

            // Compute the new overall value for the list.
            currentListValue = new ArrayList<Object>((List<?>)propertyValue);
          }

          currentListValue.set(item.itemIndex, value);
        }
        else
        {
          // Create a command to set the value.
          Command setCommand = SetCommand.create(domain, data.object, eAttribute, value);
          replaceAllCommand.append(setCommand);
        }
      }

      // If we've deferred a multi-valued feature change and it's not been processed in the above loop.
      if (currentListAttribute != null)
      {
        Command setCommand = SetCommand.create(domain, data.object, currentListAttribute, currentListValue);
        replaceAllCommand.append(setCommand);
      }
    }

    return total;
  }

  public void setReplaceAllMode(boolean replaceAll)
  {
    if (replaceAll)
    {
      // We want the next find to find only modifiable selections.
      findReplaceable = true;

      // We want all the commands to be batched into a single undoable command.
      replaceAllCommand = new CompoundCommand(CompoundCommand.MERGE_COMMAND_ALL, "Replace All")
        {
        };
      pendingReplacements = Integer.MAX_VALUE;
    }
    else
    {
      try
      {
        // We must have an editing domain.
        EditingDomain domain = ((IEditingDomainProvider)workbenchPart).getEditingDomain();
        domain.getCommandStack().execute(replaceAllCommand);
      }
      finally
      {
        // We're done now.
        findReplaceable = false;
        replaceAllCommand = null;
        pendingReplacements = -1;
      }
    }
  }

  /**
   * Exacts the textual representation of the attribute-based property descriptor.
   * Returns <code>null</code> if the property is not attribute-based, or if the value is not representable as text.
   */
  protected static List<String> getText(IItemPropertyDescriptor itemPropertyDescriptor, Object object)
  {
    if (itemPropertyDescriptor instanceof ItemPropertyDescriptorDecorator)
    {
      ItemPropertyDescriptorDecorator itemPropertyDescriptorDecorator = (ItemPropertyDescriptorDecorator)itemPropertyDescriptor;
      return getText(itemPropertyDescriptorDecorator.getDecoratedItemPropertyDescriptor(), itemPropertyDescriptorDecorator.getDecoratedObject());
    }

    List<String> result = new ArrayList<String>();

    // Extract the property value and unwrap it if necessary.
    Object propertyValue = itemPropertyDescriptor.getPropertyValue(object);
    if (propertyValue instanceof IItemPropertySource)
    {
      propertyValue = ((IItemPropertySource)propertyValue).getEditableValue(object);
    }

    // If the feature is an attribute...
    Object feature = itemPropertyDescriptor.getFeature(object);
    if (feature instanceof EAttribute)
    {
      // If the attribute's type is serializeable...
      EAttribute eAttribute = (EAttribute)feature;
      EDataType eDataType = eAttribute.getEAttributeType();
      if (eDataType.isSerializable())
      {
        // If there is a value.
        if (propertyValue != null)
        {
          boolean useLabel = false;
          // Always create a list.
          if (eAttribute.isMany())
          {
            // Add the textual representation of each value.
            for (Object item : (List<?>)propertyValue)
            {
              try
              {
                result.add(EcoreUtil.convertToString(eDataType, item));
              }
              catch (Exception exception)
              {
                useLabel = true;
                break;
              }
            }
          }
          else
          {
            // Add the textual representation of the one value.
            try
            {
              result.add(EcoreUtil.convertToString(eDataType, propertyValue));
            }
            catch (RuntimeException exception)
            {
              useLabel = true;
            }
          }

          if (!useLabel)
          {
            // return result;
          }
        }
      }
    }

    // Use the label as displayed in the properties view's value column.
    IItemLabelProvider labelProvider = itemPropertyDescriptor.getLabelProvider(object);
    if (labelProvider != null && propertyValue != null)
    {
      // Make sure it's not duplicated text.
      String text = labelProvider.getText(propertyValue);
      for (String otherText : result)
      {
        if (otherText.contains(text))
        {
          return result;
        }
      }
      result.add(text);
    }

    return result;
  }

  /**
   * Returns the EMF property descriptors, mapped to the corresponding the tree item.
   * It will walked nested properties and property categories.
   */
  protected static Map<? extends PropertyDescriptor, TreeItem> getPropertyDescriptors(TreeItem treeItem)
  {
    Map<PropertyDescriptor, TreeItem> result = new LinkedHashMap<PropertyDescriptor, TreeItem>();
    Object data = treeItem.getData();
    if (data instanceof ExtendedPropertySheetEntry)
    {
      ExtendedPropertySheetEntry propertySheetEntry = (ExtendedPropertySheetEntry)data;
      IPropertyDescriptor descriptor = propertySheetEntry.getDescriptor();
      if (descriptor instanceof PropertyDescriptor)
      {
        PropertyDescriptor propertyDescriptor = (PropertyDescriptor)descriptor;
        result.put(propertyDescriptor, treeItem);
        if (treeItem.getItemCount() != 0)
        {
          // Force the content provider to create the child items without actually expanding the tree.
          Event event = new Event();
          event.item = treeItem;
          treeItem.getParent().notifyListeners(SWT.Expand, event);

          for (TreeItem childItem : treeItem.getItems())
          {
            result.putAll(getPropertyDescriptors(childItem));
          }
        }
      }
    }
    else
    {
      for (TreeItem childTreeItem : treeItem.getItems())
      {
        result.putAll(getPropertyDescriptors(childTreeItem));
      }
    }
    return result;
  }

  /**
   * Returns the feature of item property descriptor of the EMF property descriptor.
   */
  protected static Object getFeature(PropertyDescriptor propertyDescriptor)
  {
    Object object = propertyDescriptor.getObject();
    IItemPropertyDescriptor itemPropertyDescriptor = propertyDescriptor.getItemPropertyDescriptor();
    return itemPropertyDescriptor.getFeature(object);
  }

  /**
   * This represents an element associated with each object in a tree.
   * @see TextData
   */
  protected static class Data
  {
    /**
     * The object in the tree.
     */
    public Object object;

    /**
     * The depth of the object in the tree.
     */
    public int depth;

    /**
     * The parent data in the tree.
     */
    public Data parent;

    /**
     * The items associated with the object.
     */
    public List<Data.Item> items;

    public Data(int depth, Object object, List<Data.Item> items)
    {
      this.depth = depth;
      this.object = object;
      this.items = items;
    }

    public Object[] getPath()
    {
      List<Object> path = new ArrayList<Object>();
      for (Data data = this; data != null; data = data.parent)
      {
        path.add(0, data.object);
      }

      return path.toArray();
    }

    /**
     * This represents an item associated with each data item.
     */
    protected static class Item
    {
      /**
       * The containing data of this item.
       */
      public FindAndReplaceTarget.Data data;

      /**
       * The index of this item in the overall {@link TextData induced} textual representation.
       */
      public int index;

      /**
       * The property descriptor associated with this item.
       * The <code>null</value> represents the label value of the data object.
       */
      public IItemPropertyDescriptor itemPropertyDescriptor;

      /**
       * Each value in a multi-valued feature is represented as an item.
       * This is the index of that item in its list.
       */
      public int itemIndex;

      /**
       * The textual value of the item.
       */
      public String value;

      public Item(FindAndReplaceTarget.Data data, int index, IItemPropertyDescriptor itemPropertyDescriptor, int itemIndex, String value)
      {
        this.data = data;
        this.index = index;
        this.itemPropertyDescriptor = itemPropertyDescriptor;
        this.itemIndex = itemIndex;
        this.value = value;
      }

      public Object getFeature()
      {
        if (itemPropertyDescriptor != null)
        {
          return itemPropertyDescriptor.getFeature(data.object);
        }

        return null;
      }
    }
  }

  /**
   * This supports iterating over an induced textual representation of a structured viewer's structure.
   */
  protected static class TextData implements Iterable<FindAndReplaceTarget.Data>
  {
    private final StructuredViewer viewer;

    private final Collator collator;

    private final ILabelProvider labelProvider;

    private final IPropertySourceProvider propertySourceProvider;

    public TextData(StructuredViewer viewer, Collator collator)
    {
      this.viewer = viewer;
      this.collator = collator;
      labelProvider = (ILabelProvider)viewer.getLabelProvider();
      IContentProvider contentProvider = viewer.getContentProvider();
      propertySourceProvider = contentProvider instanceof IPropertySourceProvider ? (IPropertySourceProvider)contentProvider : null;
    }

    public Iterator<FindAndReplaceTarget.Data> iterator()
    {
      // This is an iterator that delegates to an iterator that walks the structure of the viewer.
      final StructuredViewerTreeIterator structuredViewerTreeIterator = StructuredViewerTreeIterator.create(viewer);
      return new Iterator<FindAndReplaceTarget.Data>()
        {
          private List<Data> parents = new ArrayList<Data>();

          // This keeps track of the textual index as we iterate.
          private int index;

          public boolean hasNext()
          {
            return structuredViewerTreeIterator.hasNext();
          }

          public FindAndReplaceTarget.Data next()
          {
            // Keep track of the depth before calling next.
            int depth = structuredViewerTreeIterator.size();
            Object object = structuredViewerTreeIterator.next();

            // Create a list of items for this object and use that to create a new data representation.
            List<Data.Item> items = new ArrayList<Data.Item>();
            FindAndReplaceTarget.Data data = new Data(depth - 1, object, items);

            if (parents.size() < depth)
            {
              parents.add(data);
            }
            else
            {
              parents.set(depth - 1, data);
            }

            if (depth > 1)
            {
              data.parent = parents.get(depth - 2);
            }

            // Add an item for the label.
            String label = labelProvider.getText(object);
            items.add(new Data.Item(data, index, null, 0, label));
            index += label.length();

            // If we have a source provider...
            if (propertySourceProvider != null)
            {
              // And we have an EMF property source for the object...
              IPropertySource propertySource = propertySourceProvider.getPropertySource(object);
              if (propertySource instanceof PropertySource)
              {
                // Extract the EMF property source so we can iterate directly over the EMF property descriptors.
                PropertySource emfPropertySource = (PropertySource)propertySource;
                IItemPropertySource itemPropertySource = emfPropertySource.getItemPropertySource();
                visit(items, data, itemPropertySource, object);
              }
            }

            return data;
          }

          private void visit(List<Data.Item> items, FindAndReplaceTarget.Data data, IItemPropertySource itemPropertySource, Object object)
          {
            for (IItemPropertyDescriptor itemPropertyDescriptor : sort(itemPropertySource.getPropertyDescriptors(object), object))
            {
              // Extract the textual values of the property, if there are any.
              List<String> text = getText(itemPropertyDescriptor, object);
              if (text != null)
              {
                // Create an item for each value.
                for (int i = 0, size = text.size(); i < size; ++i)
                {
                  String value = text.get(i);
                  items.add(new Data.Item(data, index, itemPropertyDescriptor, i, value));
                  index += value.length();
                }
              }

              Object propertyValue = itemPropertyDescriptor.getPropertyValue(object);
              if (propertyValue instanceof IItemPropertySource)
              {
                IItemPropertySource childPropertySource = (IItemPropertySource)propertyValue;
                visit(items, data, childPropertySource, propertyValue);
              }
            }
          }

          public void remove()
          {
            throw new UnsupportedOperationException("remove");
          }
        };
    }

    protected List<? extends IItemPropertyDescriptor> sort(List<IItemPropertyDescriptor> propertyDescriptors, Object object)
    {
      Map<String, Map<String, IItemPropertyDescriptor>> categorizedPropertyDescriptors = collator == null
        ? new LinkedHashMap<String, Map<String, IItemPropertyDescriptor>>() : new TreeMap<String, Map<String, IItemPropertyDescriptor>>(collator);
      for (IItemPropertyDescriptor itemPropertyDescriptor : propertyDescriptors)
      {
        String category = itemPropertyDescriptor.getCategory(object);
        if (category == null)
        {
          category = EMFEditUIPlugin.INSTANCE.getString("_UI_Misc_property_category");
        }
        Map<String, IItemPropertyDescriptor> sortedItemPropertyDescriptors = categorizedPropertyDescriptors.get(category);
        if (sortedItemPropertyDescriptors == null)
        {
          sortedItemPropertyDescriptors = collator == null
            ? new LinkedHashMap<String, IItemPropertyDescriptor>() : new TreeMap<String, IItemPropertyDescriptor>(Collator.getInstance());
          categorizedPropertyDescriptors.put(category, sortedItemPropertyDescriptors);
        }
        sortedItemPropertyDescriptors.put(itemPropertyDescriptor.getDisplayName(object), itemPropertyDescriptor);
      }

      List<IItemPropertyDescriptor> result = new ArrayList<IItemPropertyDescriptor>(propertyDescriptors.size());
      for (Map<String, IItemPropertyDescriptor> sortedItemPropertyDescriptors : categorizedPropertyDescriptors.values())
      {
        result.addAll(sortedItemPropertyDescriptors.values());
      }

      return result;
    }
  }

  /**
   * This enumerates the types of searches that are possible for an EMF-based structured editor.
   * <ul>
   *   <li>Labels and properties - Searches the labels in the structure viewer as well as the values of all properties in the properties view.</li>
   *   <li>Labels - Searches the only the labels in the structure viewer.</li>
   *   <li>Properties - Searches the only the properties in the properties view.</li>
   *   <li>Modifiable attributes - Searches the only the properties in the properties view that correspond to modified attributes of the model.</li>
   * </ul>
   */
  protected enum SearchType
  {
    LABEL_AND_PROPERTY()
    {
      @Override
      public boolean isIncluded(Data.Item item)
      {
        return item != null;
      }

      @Override
      public String key()
      {
        return "label+property";
      }

      @Override
      public String label()
      {
        return EMFEditUIPlugin.INSTANCE.getString("_UI_LabelAndProperties_label");
      }
    },
    LABEL()
    {
      @Override
      public boolean isIncluded(Data.Item item)
      {
        return item != null && item.itemPropertyDescriptor == null;
      }

      @Override
      public String key()
      {
        return "label";
      }

      @Override
      public String label()
      {
        return EMFEditUIPlugin.INSTANCE.getString("_UI_Labels_label");
      }
    },
    PROPERTY()
    {
      @Override
      public boolean isIncluded(Data.Item item)
      {
        return item != null && item.itemPropertyDescriptor != null;
      }

      @Override
      public String key()
      {
        return "property";
      }

      @Override
      public String label()
      {
        return EMFEditUIPlugin.INSTANCE.getString("_UI_Properties_label");
      }
    },
    MODIFIABLE_ATTRIBUTE
    {
      @Override
      public boolean isIncluded(Data.Item item)
      {
        return item != null && item.itemPropertyDescriptor != null && item.getFeature() instanceof EAttribute && item.itemPropertyDescriptor.canSetProperty(item.data.object);
      }

      @Override
      public String key()
      {
        return "modifiable-attribute";
      }

      @Override
      public String label()
      {
        return EMFEditUIPlugin.INSTANCE.getString("_UI_ModifiableAttributes_label");
      }
    };

    public abstract boolean isIncluded(Data.Item item);

    public abstract String key();

    public abstract String label();

    public static FindAndReplaceTarget.SearchType getSearchType(String key)
    {
      for (FindAndReplaceTarget.SearchType searchType : SearchType.values())
      {
        if (searchType.key().equals(key))
        {
          return searchType;
        }
      }

      return LABEL_AND_PROPERTY;
    }

    public static String[] getLabels()
    {
      FindAndReplaceTarget.SearchType[] values = SearchType.values();
      String[] labels = new String [values.length];
      for (int i = 0; i < values.length; ++i)
      {
        labels[i] = values[i].label();
      }

      return labels;
    }
  }
}
