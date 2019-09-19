/**
 * Copyright (c) 2005-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.ui.view;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.ui.viewer.ColumnResizer;
import org.eclipse.emf.common.ui.viewer.ColumnResizer.Handler;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.provider.DiagnosticDecorator;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManagerOverrides;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.text.IFindReplaceTarget;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetEntry;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySource2;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetEntry;
import org.eclipse.ui.views.properties.PropertySheetPage;

/**
 * This property sheet page has an additional button in its local toolbar that locates the value of the
 * selected property in the editor.
 * It also supports {@link #ExtendedPropertySheetPage(AdapterFactoryEditingDomain, Decoration) decorating} the property values to indicate bad values.
 */
public class ExtendedPropertySheetPage extends PropertySheetPage
{
  /**
   * @since 2.9
   */
  public static enum Decoration
  {
    NONE, MANUAL, LIVE
  }

  protected List<Object> objectsToSelect = new ArrayList<Object>();

  protected AdapterFactoryEditingDomain editingDomain;

  /**
   * @since 2.9
   */
  protected DiagnosticDecorator diagnosticDecorator;

  /**
   * @since 2.9
   */
  protected IDialogSettings dialogSettings;

  /**
   * @since 2.9
   */
  protected List<?> input = Collections.emptyList();

  /**
   * @since 2.9
   */
  protected IPropertySourceProvider propertySourceProvider;

  protected IAction locateValueAction = new LocateValueAction();

  /**
   * @since 2.14
   */
  protected IWorkbenchPart workbenchPart;

  protected class LocateValueAction extends Action
  {
    public LocateValueAction()
    {
      setText(EMFEditUIPlugin.INSTANCE.getString("_UI_LocateValue_action"));
      setToolTipText(EMFEditUIPlugin.INSTANCE.getString("_UI_LocateValue_action_tool_tip"));
      setImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(EMFEditUIPlugin.INSTANCE.getImage("full/elcl16/LocateValue")));
      setDisabledImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(EMFEditUIPlugin.INSTANCE.getImage("full/dlcl16/LocateValue")));
    }

    @Override
    public void run()
    {
      List<Object> selection = new ArrayList<Object>();
      for (Object object : objectsToSelect)
      {
        selection.add(editingDomain.getWrapper(object));
      }
      setSelectionToViewer(selection);
    }
  }

  /**
   * @since 2.14
   */
  protected static class SetValueAction extends Action
  {
    protected ExtendedPropertySheetEntry entry;

    public SetValueAction()
    {
      setText(EMFEditUIPlugin.INSTANCE.getString("_UI_SetValue_action"));
      setToolTipText(EMFEditUIPlugin.INSTANCE.getString("_UI_SetValue_action_tool_tip"));
      setImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(EMFEditUIPlugin.INSTANCE.getImage("full/elcl16/SetProperty.png")));
      setDisabledImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(EMFEditUIPlugin.INSTANCE.getImage("full/dlcl16/SetProperty.png")));
    }

    @Override
    public void run()
    {
      entry.setPropertyValue();
    }

    public void setEntry(ExtendedPropertySheetEntry entry)
    {
      this.entry = entry;
      setEnabled(entry != null && entry.isSetValueEnabled());
    }
  }

  /**
   * @since 2.14
   */
  protected SetValueAction setValueAction = new SetValueAction();

  /**
   * @since 2.14
   */
  protected  IAction restoreValueAction;

  /**
   * @since 2.14
   */
  protected int autoExpandLevel;

  /**
   * @since 2.14
   */
  protected boolean autoResizeColumns;

  /**
   * @since 2.14
   */
  protected Handler columnResizer;

  /**
   * @since 2.14
   */
  protected Clipboard clipboard = new Clipboard(PlatformUI.getWorkbench().getDisplay());

  /**
   * @since 2.14
   */
  protected CopyValuePropertyAction copyPropertyValueAction = new CopyValuePropertyAction(clipboard);

  private ExtendedPropertySheetEntry rootEntry;

  private IAction filterAction;

  private IAction categoriesAction;

  public ExtendedPropertySheetPage(AdapterFactoryEditingDomain editingDomain)
  {
    this(editingDomain, Decoration.NONE, null, 0, false);
  }

  /**
   * @since 2.9
   */
  public ExtendedPropertySheetPage(AdapterFactoryEditingDomain editingDomain, Decoration decoration)
  {
    this(editingDomain, decoration, null, 0, false);
  }

  /**
   * @since 2.9
   */
  public ExtendedPropertySheetPage(AdapterFactoryEditingDomain editingDomain, Decoration decoration, IDialogSettings dialogSettings)
  {
    this(editingDomain, decoration, dialogSettings, 0, false);
  }

  /**
   * @since 2.14
   */
  public ExtendedPropertySheetPage(AdapterFactoryEditingDomain editingDomain, Decoration decoration, IDialogSettings dialogSettings, int autoExpandLevel, boolean autoResizeColumns)
  {
    this.editingDomain = editingDomain;
    this.dialogSettings = dialogSettings;
    this.autoExpandLevel = autoExpandLevel;
    this.autoResizeColumns = autoResizeColumns;
    this.autoResizeColumns = autoResizeColumns;
    diagnosticDecorator =  createDiagnosticDecorator(decoration);
  }

  /**
   * @since 2.14
   */
  public IAction getFilterAction()
  {
    return filterAction;
  }

  /**
   * @since 2.14
   */
  public IAction getCategoriesAction()
  {
    return categoriesAction;
  }

  /**
   * @since 2.9
   */
  protected DiagnosticDecorator createDiagnosticDecorator(Decoration decoration)
  {
    switch (decoration)
    {
      case MANUAL:
      {
        return new DiagnosticDecorator(editingDomain.getResourceSet(), this);
      }
      case LIVE:
      {
        return new DiagnosticDecorator(editingDomain, this, dialogSettings);
      }
      default:
      {
        return null;
      }
    }
  }

  @Override
  public void setPropertySourceProvider(IPropertySourceProvider newProvider)
  {
    propertySourceProvider = newProvider;
    super.setPropertySourceProvider(newProvider);
  }

  @Override
  public void createControl(Composite parent)
  {
    super.createControl(parent);

    // Always create our extension of a property sheet entry.
    //
    ExtendedPropertySheetEntry decoratingPropertySheetEntry = new ExtendedPropertySheetEntry(diagnosticDecorator);
    decoratingPropertySheetEntry.setPropertySourceProvider(propertySourceProvider);
    rootEntry =  decoratingPropertySheetEntry;
    setRootEntry(decoratingPropertySheetEntry);

    // The property sheet page's viewer does not respond to widget selected events in a way that updates the current entry selection.
    // This ensures that we can update the tool bar actions when the selection is changed via the keyboard.
    //
    final Tree tree = (Tree)getControl();
    tree.addSelectionListener(new SelectionListener()
      {
        public void widgetSelected(SelectionEvent e)
        {
          Object data = e.item.getData();
          handleEntrySelection(new StructuredSelection(data));
        }

        public void widgetDefaultSelected(SelectionEvent e)
        {
        }
      });

    if (autoResizeColumns)
    {
      columnResizer = ColumnResizer.addColumnResizer(tree);
    }

    Menu menu = getControl().getMenu();
    IMenuManager menuManager = (IMenuManager)menu.getData("org.eclipse.jface.action.MenuManager.managerKey");
    if (menuManager != null)
    {
      menuManager.insertAfter("copy", copyPropertyValueAction);
      menuManager.insertAfter("defaults", setValueAction);
    }
  }

  /**
   * This method should be overridden to set the selection.
   */
  protected void setSelectionToViewer(List<?> selection)
  {
    // Do nothing.
  }

  @Override
  public void makeContributions(IMenuManager menuManager, final IToolBarManager toolBarManager, IStatusLineManager statusLineManager)
  {
    class DelegatingToolBarManager implements IToolBarManager
    {
      public void add(IAction action)
      {
        if ("defaults".equals(action.getId()))
        {
          restoreValueAction = action;
          if (setValueAction != null)
          {
            toolBarManager.add(setValueAction);
          }
        }
        toolBarManager.add(action);
      }

      public void add(IContributionItem item)
      {
        toolBarManager.add(item);
      }

      public void appendToGroup(String groupName, IAction action)
      {
        toolBarManager.appendToGroup(groupName, action);
      }

      public void appendToGroup(String groupName, IContributionItem item)
      {
        toolBarManager.appendToGroup(groupName, item);
      }

      public IContributionItem find(String id)
      {
        return toolBarManager.find(id);
      }

      public IContributionItem[] getItems()
      {
        return toolBarManager.getItems();
      }

      public IContributionManagerOverrides getOverrides()
      {
        return toolBarManager.getOverrides();
      }

      public void insertAfter(String id, IAction action)
      {
        toolBarManager.insertAfter(id, action);
      }

      public void insertAfter(String id, IContributionItem item)
      {
        toolBarManager.insertAfter(id, item);
      }

      public void insertBefore(String id, IAction action)
      {
        toolBarManager.insertBefore(id, action);
      }

      public void insertBefore(String id, IContributionItem item)
      {
        toolBarManager.insertBefore(id, item);
      }

      public boolean isDirty()
      {
        return toolBarManager.isDirty();
      }

      public boolean isEmpty()
      {
        return toolBarManager.isEmpty();
      }

      public void markDirty()
      {
        toolBarManager.markDirty();
      }

      public void prependToGroup(String groupName, IAction action)
      {
        toolBarManager.prependToGroup(groupName, action);
      }

      public void prependToGroup(String groupName, IContributionItem item)
      {
        toolBarManager.prependToGroup(groupName, item);
      }

      public IContributionItem remove(String id)
      {
        return toolBarManager.remove(id);
      }

      public IContributionItem remove(IContributionItem item)
      {
        return toolBarManager.remove(item);
      }

      public void removeAll()
      {
        toolBarManager.removeAll();
      }

      public void update(boolean force)
      {
        toolBarManager.update(force);
      }
    }

    super.makeContributions(menuManager, new DelegatingToolBarManager(), statusLineManager);
    toolBarManager.add(locateValueAction);
    for (IContributionItem contributionItem : toolBarManager.getItems())
    {
      if ("filter".equals(contributionItem.getId()) && contributionItem instanceof ActionContributionItem)
      {
        filterAction = ((ActionContributionItem)contributionItem).getAction();
      }
      else if ("categories".equals(contributionItem.getId()) && contributionItem instanceof ActionContributionItem)
      {
        categoriesAction = ((ActionContributionItem)contributionItem).getAction();
      }
    }
  }

  @Override
  public void handleEntrySelection(ISelection selection)
  {
    ExtendedPropertySheetEntry entry = null;
    objectsToSelect.clear();
    if (!selection.isEmpty() && selection instanceof IStructuredSelection)
    {
      IStructuredSelection structuredSelection = (IStructuredSelection)selection;
      if (structuredSelection.size() == 1)
      {
        Object object = structuredSelection.getFirstElement();
        if (object instanceof ExtendedPropertySheetEntry)
        {
          entry = (ExtendedPropertySheetEntry)object;

          Object [] values = entry.getValues();
          if (values != null)
          {
            for (int i = 0; i < values.length; ++i)
            {
              Object value = values[i];
              if (value instanceof IItemPropertySource)
              {
                Object realValue = ((IItemPropertySource)value).getEditableValue(null);
                if (realValue instanceof Collection<?>)
                {
                  for (Object o : (Collection<?>)realValue)
                  {
                    addObjectToSelect(o);
                  }
                }
                else
                {
                  addObjectToSelect(realValue);
                }
              }
            }
          }
        }
      }
    }

    locateValueAction.setEnabled(!objectsToSelect.isEmpty());

    if (restoreValueAction == null || entry == null)
    {
      super.handleEntrySelection(selection);
    }
    else
    {
      restoreValueAction.setEnabled(entry.isRestoreValueEnabled());
    }

    copyPropertyValueAction.selectionChanged(selection);

    if (setValueAction != null)
    {
      setValueAction.setEntry(entry);
    }
  }

  protected void addObjectToSelect(Object object)
  {
    if (object instanceof EObject)
    {
      objectsToSelect.add(object);
    }
  }

  @Override
  public void selectionChanged(IWorkbenchPart part, ISelection selection)
  {
    workbenchPart = part;
    if (selection instanceof IStructuredSelection)
    {
      input = ((IStructuredSelection)selection).toList();
    }
    else
    {
      input = Collections.emptyList();
    }

    Tree tree = (Tree)getControl();
    if (tree != null)
    {
      try
      {
        tree.setRedraw(false);
        super.selectionChanged(part, selection);
      }
      finally
      {
        if (columnResizer != null)
        {
          columnResizer.resizeColumns();
        }
        tree.setRedraw(true);
      }
  
      if (autoExpandLevel != 0)
      {
        try
        {
          tree.setRedraw(false);
          expand(tree, tree.getItems(), autoExpandLevel);
        }
        finally
        {
          tree.setRedraw(true);
        }
      }
    }
  }

  private void expand(Tree tree, TreeItem[] items, int level)
  {
    if (level != 0)
    {
      for (TreeItem treeItem : items)
      {
        Event event = new Event();
        event.item = treeItem;
        if (!treeItem.getExpanded())
        {
          tree.notifyListeners(SWT.Expand, event);
          treeItem.setExpanded(true);
        }
        expand(tree, treeItem.getItems(), level - 1);
      }
    }
  }

  /**
   * Provides access to the current input to the page.
   * 
   * @since 2.9
   */
  public List<?> getInput()
  {
    return input;
  }

  @Override
  public void dispose()
  {
    if (diagnosticDecorator != null)
    {
      diagnosticDecorator.dispose();
    }
    clipboard.dispose();
    super.dispose();
  }

  /**
   * Refreshes only the labels of the property sheet viewer.
   * It does not dismiss the cell editor, if there is one.
   *
   * @since 2.12
   */
  public void refreshLabels()
  {
    try
    {
      Field viewerField = PropertySheetPage.class.getDeclaredField("viewer");
      viewerField.setAccessible(true);
      Viewer viewer = (Viewer)viewerField.get(this);
      viewer.refresh();
      if (columnResizer != null)
      {
        columnResizer.resizeColumns();
      }
      return;
    }
    catch (Throwable throwable)
    {
      // Ignore.
    }

    refresh();
  }

  @Override
  public void refresh()
  {
    Control control = getControl();
    if (control == null)
    {
      return;
    }

    // Check that there isn't currently a cell editor active.
    // If there is a focus control that isn't the control of the property sheet page...
    Control focusControl = control.getDisplay().getFocusControl();
    if (focusControl != null && focusControl != control)
    {
      // Check if that focus control is contained by the property sheet page's control.
      for (Control parent = focusControl.getParent(); parent != null; parent = parent.getParent())
      {
        if (parent == control)
        {
          if (columnResizer != null)
          {
            columnResizer.resizeColumns();
          }

          // If it is, then don't refresh the property sheet page
          // because that will make the cell editor deactivate.
          return;
        }
      }
    }

    // If it's a CCombo and the list (Shell) is active, the above logic won't find it.
    if (setValueAction.entry != null && setValueAction.entry.editor != null && setValueAction.entry.editor.getControl() != null
      && !setValueAction.entry.editor.getControl().isDisposed() && setValueAction.entry.editor.getControl().isFocusControl())
    {
      if (columnResizer != null)
      {
        columnResizer.resizeColumns();
      }
      return;
    }

    Tree tree = (Tree)control;
    try
    {
      tree.setRedraw(false);
      int expectedModCount = rootEntry.modCount;
      super.refresh();
      if (expectedModCount != rootEntry.modCount && autoExpandLevel != 0)
      {
        expand(tree, tree.getItems(), autoExpandLevel);
      }

      if (columnResizer != null)
      {
        columnResizer.resizeColumns();
      }
    }
    finally
    {
      tree.setRedraw(true);
    }
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public  Object getAdapter(Class adapter)
  {
    if (workbenchPart != null && adapter == IFindReplaceTarget.class)
    {
      return workbenchPart.getAdapter(adapter);
    }
    else
    {
      return super.getAdapter(adapter);
    }
  }

  /**
   * @since 2.14
   */
  public static class ExtendedPropertySheetEntry extends PropertySheetEntry
  {
    private int modCount;
    
    protected DiagnosticDecorator diagnosticDecorator;
    
    protected CellEditor editor;

    public ExtendedPropertySheetEntry(DiagnosticDecorator diagnosticDecorator)
    {
      this.diagnosticDecorator = diagnosticDecorator;
    }

    @Override
    public IPropertyDescriptor getDescriptor()
    {
      return super.getDescriptor();
    }

    @Override
    protected PropertySheetEntry createChildEntry()
    {
      ++modCount;
      for (PropertySheetEntry parent = getParent(); parent instanceof ExtendedPropertySheetEntry; parent = ((ExtendedPropertySheetEntry)parent).getParent())
      {
        ++((ExtendedPropertySheetEntry)parent).modCount;
      }

      return new ExtendedPropertySheetEntry(diagnosticDecorator);
    }

    public boolean isRestoreValueEnabled()
    {
      PropertySheetEntry parent = getParent();
      if (parent != null)
      {
        Object[] objects = parent.getValues();
        IPropertyDescriptor descriptor = getDescriptor();
        Object id = descriptor.getId();
        for (Object object : objects)
        {
          IPropertySource source = getPropertySource(object);
          for (IPropertyDescriptor propertyDescriptor : source.getPropertyDescriptors())
          {
            if (id.equals(propertyDescriptor.getId()))
            {
              if (source.isPropertySet(id) && (!(source instanceof IPropertySource2) || ((IPropertySource2)source).isPropertyResettable(id)))
              {
               return true;
              }
              else
              {
                break;
              }
            }
          }
        }
      }
      return false;
    }

    public boolean isSetValueEnabled()
    {
      PropertySheetEntry parent = getParent();
      if (parent != null)
      {
        Object[] objects = parent.getValues();
        IPropertyDescriptor descriptor = getDescriptor();
        Object id = descriptor.getId();
        for (Object object : objects)
        {
          IPropertySource source = getPropertySource(object);
          for (IPropertyDescriptor propertyDescriptor : source.getPropertyDescriptors())
          {
            if (id.equals(propertyDescriptor.getId()))
            {
              if (!source.isPropertySet(id) && source instanceof IUnsettablePropertySource && ((IUnsettablePropertySource)source).isPropertyUnsettable(id))
              {
                return true;
              }
              else
              {
                break;
              }
            }
          }
        }
      }
      return false;
    }

    public void setPropertyValue()
    {
      PropertySheetEntry parent = getParent();
      if (parent != null)
      {
        boolean change = false;
        Object[] objects = parent.getValues();
        IPropertyDescriptor descriptor = getDescriptor();
        Object id = descriptor.getId();
        for (int i = 0; i < objects.length; ++i)
        {
          Object object = objects[i];
          IPropertySource source = getPropertySource(object);
          Object value = getEditValue(i);
          source.setPropertyValue(id, value);
          change = true;
        }
        if (change)
        {
          refreshFromRoot();
        }
      }
    }

    @Override
    public Image getImage()
    {
      Image image = super.getImage();
      if (image == null)
      {
        image = ExtendedImageRegistry.INSTANCE.getImage(ItemPropertyDescriptor.GENERIC_VALUE_IMAGE);
      }
      Diagnostic featureDiagnostic = findDiagnostic();
      return featureDiagnostic != null ? diagnosticDecorator.decorate(image, featureDiagnostic) : image;
    }

    protected Diagnostic findDiagnostic()
    {
      if (diagnosticDecorator != null)
      {
        IPropertyDescriptor descriptor = getDescriptor();
        if (descriptor instanceof PropertyDescriptor)
        {
          Object feature = ((PropertyDescriptor)descriptor).getFeature();
          Map<Object, ? extends Diagnostic> decorations = diagnosticDecorator.getDecorations();
          if (!decorations.isEmpty() && feature != null)
          {
            for (Diagnostic diagnostic : decorations.values())
            {
              Diagnostic featureDiagnostic = find(diagnostic, feature);
              if (featureDiagnostic != null)
              {
                return featureDiagnostic;
              }
            }
          }
        }
      }
      return null;
    }

    protected Diagnostic find(Diagnostic diagnostic, Object feature)
    {
      // Gather them all...
      //
      if (diagnostic.getData().contains(feature))
      {
        return diagnostic;
      }
      for (Diagnostic child : diagnostic.getChildren())
      {
        Diagnostic result = find(child, feature);
        if (result != null)
        {
          return result;
        }
      }
      return null;
    }

    @Override
    public String getDescription()
    {
      String description = super.getDescription();
      Diagnostic featureDiagnostic = findDiagnostic();
      if (featureDiagnostic != null)
      {
        List<Diagnostic> children = featureDiagnostic.getChildren();
        if (!children.isEmpty())
        {
          return description + " - " + DiagnosticDecorator.strip(children.get(0).getMessage());
        }
        else
        {
          return description + " - " + DiagnosticDecorator.strip(featureDiagnostic.getMessage());
        }
      }
      else
      {
        return description;
      }
    }

    /**
     * This override guards the creation so it's only done if the current selection in the tree is the tree item for this property sheet entry.
     * The framework listens for and handles the mouse down event using the point of that event to determine the tree item,
     * but if the tree scrolls during an expansion, that point may not  be the tree item that was actually expanded but instead a tree item that's just been revealed.
     * We really only want to create a cell editor for the actual selected tree item.
     */
    @Override
    public CellEditor getEditor(Composite parent)
    {
      editor = null;
      Tree tree = (Tree)parent;
      TreeItem[] selection = tree.getSelection();
      for (TreeItem treeItem : selection)
      {
        if (treeItem.getData() == this)
        {
          editor = super.getEditor(parent);
          break;
        }
      }
      return editor;
    }

    @Override
    public void setValues(Object[] objects)
    {
      editor = null;
      super.setValues(objects);
    }

    @Override
    public void applyEditorValue()
    {
      if (editor != null && editor.isDirty())
      {
        super.applyEditorValue();
      }
    }
  }

  /**
   *  An extension of the standard <code>{@link IPropertySource2}</code> interface.
   * <p>
   * This interface provides an extended API to allow a property to indicate whether or not it supports the concept of being in the <em>unset</em> state,
   * i.e., to indicate that the value state of the property includes one additional state, the unset state, that is distinct from the set of values the property can have.
   * For a property with {@link IPropertyDescriptor#getId() ID} <code>x</code> that returns <code>true</code> for <code>{@link #isPropertyUnsettable(Object) isPropertyUnsettable}(x)</code>
   * and that returns <code>false</code> for <code>{@link #isPropertySet(Object) isPropertySet}(x)</code>,
   * calling <code>{@link #setPropertyValue(Object, Object) setPropertyValue}(x, {@link #getEditableValue getEditableValue}(x)</code>
   * will result in <code>isPropertySet(true)</code> becoming <code>true</code>, even though in both states the property has the same value.
   * Such a property <code>x</code> will always return <code>false</code> for <code>isPropertySet(x)</code> after a call to  <code>{@link #resetPropertyValue(Object) resetPropertyValue}(x)</code>.
   * A property <code>x</code> that is read-only will always return <code>false</code> for <code>isPropertyUnsettable(x)</code>.
   * </p>
   *
   * @since 2.14
   */
  public interface IUnsettablePropertySource extends IPropertySource2
  {
    /**
     * Returns whether the property supports the concept of being in the <em>{@link IUnsettablePropertySource unset}</em> state.
     * @param id the ID of the property.
     * @return whether the property supports the concept of being in the unset.
     * @see IUnsettablePropertySource
     */
    boolean isPropertyUnsettable(Object id);
  }

  /**
   * @since 2.14
   */
  protected static class CopyValuePropertyAction extends Action
  {
    private Clipboard clipboard;

    private IStructuredSelection selection;

    public CopyValuePropertyAction(Clipboard clipboard)
    {
      super(EMFEditUIPlugin.INSTANCE.getString("_UI_CopyValue_menu_item"));
      this.clipboard = clipboard;
      setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
    }

    @Override
    public void run()
    {
      if (selection != null && !selection.isEmpty())
      {
        IPropertySheetEntry entry = (IPropertySheetEntry)selection.getFirstElement();
        String value = entry.getValueAsString();
        if (value != null)
        {
          setClipboard(value);
        }
      }
    }

    public void selectionChanged(ISelection selection)
    {
      if (selection instanceof IStructuredSelection)
      {
        this.selection = (IStructuredSelection)selection;
        setEnabled(!selection.isEmpty());
      }
      else
      {
        this.selection = null;
        setEnabled(false);
      }
    }

    private void setClipboard(String text)
    {
      try
      {
        Object[] data = new Object []{ text };
        Transfer[] transferTypes = new Transfer []{ TextTransfer.getInstance() };
        clipboard.setContents(data, transferTypes);
      }
      catch (SWTError exception)
      {
        EMFEditUIPlugin.INSTANCE.log(exception);
      }
    }
  }
}
