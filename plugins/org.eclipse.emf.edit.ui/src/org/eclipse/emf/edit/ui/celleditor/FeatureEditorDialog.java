/**
 *
 * Copyright (c) 2002-2009 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.ui.celleditor;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor.ValueHandler;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;


public class FeatureEditorDialog extends Dialog
{
  protected ILabelProvider labelProvider;
  protected IContentProvider contentProvider;
  protected Object object;
  protected EClassifier eClassifier;
  protected String displayName;
  protected ItemProvider values;
  protected List<?> choiceOfValues;
  protected EList<?> result;
  protected boolean multiLine;
  protected boolean unique;

  /**
   * @since 2.14
   */
  protected ValueHandler valueHandler;

  /**
   * @since 2.14
   */
  public FeatureEditorDialog
    (Shell parent,
     ILabelProvider labelProvider,
     Object object,
     EClassifier eClassifier,
     List<?> currentValues,
     String displayName,
     List<?> choiceOfValues,
     boolean multiLine,
     boolean sortChoices,
     boolean unique,
     IItemPropertyDescriptor.ValueHandler valueHandler)
  {
    super(parent);
    this.valueHandler = valueHandler;
    setShellStyle(getShellStyle() | SWT.RESIZE | SWT.MAX);
    this.labelProvider = labelProvider;
    this.object = object;
    this.eClassifier = eClassifier;
    this.displayName = displayName;
    this.choiceOfValues = choiceOfValues;
    this.multiLine = multiLine;
    this.unique = unique;

    AdapterFactory adapterFactory = new ComposedAdapterFactory(Collections.<AdapterFactory>emptyList());
    values = new ItemProvider(adapterFactory, currentValues);
    contentProvider = new AdapterFactoryContentProvider(adapterFactory);
    if (sortChoices && choiceOfValues != null)
    {
      this.choiceOfValues = new ArrayList<Object>(choiceOfValues);
      ExtendedComboBoxCellEditor.createItems(this.choiceOfValues, labelProvider, true);
    }
  }

  /**
   * @since 2.6
   */
  public FeatureEditorDialog
    (Shell parent,
     ILabelProvider labelProvider,
     Object object,
     EClassifier eClassifier,
     List<?> currentValues,
     String displayName,
     List<?> choiceOfValues,
     boolean multiLine,
     boolean sortChoices,
     boolean unique)
  {
    this(parent, labelProvider, object, eClassifier, currentValues, displayName, choiceOfValues, multiLine, sortChoices, unique, null);
  }

  /**
   * @deprecated Use {@link #FeatureEditorDialog(Shell, ILabelProvider, Object, EClassifier, List, String, List, boolean, boolean, boolean)},
   * which provides proper behaviour for unique and non-unique features. This form retains the old behaviour, where
   * specifying a list of choices enforces uniqueness.
   */
  @Deprecated
  public FeatureEditorDialog
  (Shell parent,
   ILabelProvider labelProvider,
   Object object,
   EClassifier eClassifier,
   List<?> currentValues,
   String displayName,
   List<?> choiceOfValues,
   boolean multiLine,
   boolean sortChoices)
  {
    this(parent, labelProvider, object, eClassifier, currentValues, displayName, choiceOfValues, multiLine, sortChoices, choiceOfValues != null);
  }

  /**
   * @deprecated Use {@link #FeatureEditorDialog(Shell, ILabelProvider, Object, EClassifier, List, String, List, boolean, boolean, boolean)},
   * which provides proper behaviour for unique and non-unique features. This form retains the old behaviour, where
   * specifying a list of choices enforces uniqueness.
   */
  @Deprecated
  public FeatureEditorDialog
    (Shell parent,
     ILabelProvider labelProvider,
     Object object,
     EClassifier eClassifier,
     List<?> currentValues,
     String displayName,
     List<?> choiceOfValues)
  {
    this(parent, labelProvider, object, eClassifier, currentValues, displayName, choiceOfValues, false, false, choiceOfValues != null);
  }

  public FeatureEditorDialog
    (Shell parent,
     ILabelProvider labelProvider,
     EObject eObject,
     EStructuralFeature eStructuralFeature,
     String displayName,
     List<?> choiceOfValues)
  {
    this(parent,
         labelProvider,
         eObject,
         eStructuralFeature.getEType(),
         (List<?>)eObject.eGet(eStructuralFeature),
         displayName,
         choiceOfValues,
         false,
         false,
         eStructuralFeature.isUnique() || eStructuralFeature instanceof EReference);
  }

  @Override
  protected void configureShell(Shell shell)
  {
    super.configureShell(shell);
    shell.setText
      (EMFEditUIPlugin.INSTANCE.getString
         ("_UI_FeatureEditorDialog_title", new Object [] { displayName, labelProvider.getText(object) }));
    shell.setImage(labelProvider.getImage(object));
  }

  @Override
  protected Control createDialogArea(Composite parent)
  {
    Composite contents = (Composite)super.createDialogArea(parent);

    GridLayout contentsGridLayout = (GridLayout)contents.getLayout();
    contentsGridLayout.numColumns = 3;

    GridData contentsGridData = (GridData)contents.getLayoutData();
    contentsGridData.horizontalAlignment = SWT.FILL;
    contentsGridData.verticalAlignment = SWT.FILL;

    Text patternText = null;

    if (choiceOfValues != null)
    {
      Group filterGroupComposite = new Group(contents, SWT.NONE);
      filterGroupComposite.setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Choices_pattern_group"));
      filterGroupComposite.setLayout(new GridLayout(2, false));
      filterGroupComposite.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 3, 1));

      Label label = new Label(filterGroupComposite, SWT.NONE);
      label.setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Choices_pattern_label"));

      patternText = new Text(filterGroupComposite, SWT.BORDER);
      patternText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    }

    Composite choiceComposite = new Composite(contents, SWT.NONE);
    {
      GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
      data.horizontalAlignment = SWT.END;
      choiceComposite.setLayoutData(data);

      GridLayout layout = new GridLayout();
      data.horizontalAlignment = SWT.FILL;
      layout.marginHeight = 0;
      layout.marginWidth = 0;
      layout.numColumns = 1;
      choiceComposite.setLayout(layout);
    }

    Label choiceLabel = new Label(choiceComposite, SWT.NONE);
    choiceLabel.setText
       (EMFEditUIPlugin.INSTANCE.getString
           (choiceOfValues == null ?
               "_UI_Value_label" :
                  valueHandler == null ?
                    "_UI_Choices_label" :
                    "_UI_ValueAndChoices_label"));
    GridData choiceLabelGridData = new GridData();
    choiceLabelGridData.verticalAlignment = SWT.FILL;
    choiceLabelGridData.horizontalAlignment = SWT.FILL;
    choiceLabel.setLayoutData(choiceLabelGridData);

    // We use multi even for a single line because we want to respond to the enter key.
    //
    int style = multiLine ?
      SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER :
      SWT.MULTI | SWT.BORDER;
    final Text choiceText = choiceOfValues == null || valueHandler != null ? new Text(choiceComposite, style) : null;
    if (choiceText != null)
    {
      GridData choiceTextGridData = new GridData();
      choiceTextGridData.widthHint = Display.getCurrent().getBounds().width / 5;
      choiceTextGridData.verticalAlignment = SWT.BEGINNING;
      choiceTextGridData.horizontalAlignment = SWT.FILL;
      choiceTextGridData.grabExcessHorizontalSpace = true;
      if (multiLine)
      {
        choiceTextGridData.verticalAlignment = SWT.FILL;
        choiceTextGridData.grabExcessVerticalSpace = true;
      }
      choiceText.setLayoutData(choiceTextGridData);
      if (valueHandler == null)
      {
        valueHandler = new ItemPropertyDescriptor.DataTypeValueHandler((EDataType)eClassifier);
      }
    }

    final Table choiceTable = choiceOfValues == null ? null : new Table(choiceComposite, SWT.MULTI | SWT.BORDER);
    if (choiceTable != null)
    {
      GridData choiceTableGridData = new GridData();
      choiceTableGridData.widthHint = Display.getCurrent().getBounds().width / 5;
      choiceTableGridData.heightHint = Display.getCurrent().getBounds().height / 3;
      choiceTableGridData.verticalAlignment = SWT.FILL;
      choiceTableGridData.horizontalAlignment = SWT.FILL;
      choiceTableGridData.grabExcessHorizontalSpace= true;
      choiceTableGridData.grabExcessVerticalSpace= true;
      choiceTable.setLayoutData(choiceTableGridData);
    }

    final TableViewer choiceTableViewer = choiceOfValues == null ? null : new TableViewer(choiceTable);
    if (choiceTableViewer != null)
    {
      choiceTableViewer.setContentProvider(new AdapterFactoryContentProvider(new AdapterFactoryImpl()));
      choiceTableViewer.setLabelProvider(labelProvider);
      final PatternFilter filter =
        new PatternFilter()
        {
          @Override
          protected boolean isParentMatch(Viewer viewer, Object element)
          {
            return viewer instanceof AbstractTreeViewer && super.isParentMatch(viewer, element);
          }
        };
      choiceTableViewer.addFilter(filter);
      if (patternText != null)
      {
        patternText.addModifyListener
          (new ModifyListener()
           {
             public void modifyText(ModifyEvent e)
             {
               filter.setPattern(((Text)e.widget).getText());
               choiceTableViewer.refresh();
             }
           });
      }
      if (unique)
      {
        choiceTableViewer.addFilter
          (new ViewerFilter()
           {
             @Override
             public boolean select(Viewer viewer, Object parentElement, Object element)
             {
               return !values.getChildren().contains(element);
             }
           });
      }
      choiceTableViewer.setInput(new ItemProvider(choiceOfValues));
    }

    Composite controlButtons = new Composite(contents, SWT.NONE);
    GridData controlButtonsGridData = new GridData();
    controlButtonsGridData.verticalAlignment = SWT.FILL;
    controlButtonsGridData.horizontalAlignment = SWT.FILL;
    controlButtons.setLayoutData(controlButtonsGridData);

    GridLayout controlsButtonGridLayout = new GridLayout();
    controlButtons.setLayout(controlsButtonGridLayout);

    new Label(controlButtons, SWT.NONE);

    final Button addButton = new Button(controlButtons, SWT.PUSH);
    addButton.setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Add_label"));
    GridData addButtonGridData = new GridData();
    addButtonGridData.verticalAlignment = SWT.FILL;
    addButtonGridData.horizontalAlignment = SWT.FILL;
    addButton.setLayoutData(addButtonGridData);
    addButton.setEnabled(valueHandler != null && valueHandler.isValid("") == null);

    final Button removeButton = new Button(controlButtons, SWT.PUSH);
    removeButton.setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Remove_label"));
    GridData removeButtonGridData = new GridData();
    removeButtonGridData.verticalAlignment = SWT.FILL;
    removeButtonGridData.horizontalAlignment = SWT.FILL;
    removeButton.setLayoutData(removeButtonGridData);
    removeButton.setEnabled(false);

    Label spaceLabel = new Label(controlButtons, SWT.NONE);
    GridData spaceLabelGridData = new GridData();
    spaceLabelGridData.verticalSpan = 2;
    spaceLabel.setLayoutData(spaceLabelGridData);

    final Button upButton = new Button(controlButtons, SWT.PUSH);
    upButton.setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Up_label"));
    GridData upButtonGridData = new GridData();
    upButtonGridData.verticalAlignment = SWT.FILL;
    upButtonGridData.horizontalAlignment = SWT.FILL;
    upButton.setLayoutData(upButtonGridData);
    upButton.setEnabled(false);

    final Button downButton = new Button(controlButtons, SWT.PUSH);
    downButton.setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Down_label"));
    GridData downButtonGridData = new GridData();
    downButtonGridData.verticalAlignment = SWT.FILL;
    downButtonGridData.horizontalAlignment = SWT.FILL;
    downButton.setLayoutData(downButtonGridData);
    downButton.setEnabled(false);

    Composite featureComposite = new Composite(contents, SWT.NONE);
    {
      GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
      data.horizontalAlignment = SWT.END;
      featureComposite.setLayoutData(data);

      GridLayout layout = new GridLayout();
      data.horizontalAlignment = SWT.FILL;
      layout.marginHeight = 0;
      layout.marginWidth = 0;
      layout.numColumns = 1;
      featureComposite.setLayout(layout);
    }

    Label featureLabel = new Label(featureComposite, SWT.NONE);
    featureLabel.setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Feature_label"));
    GridData featureLabelGridData = new GridData();
    featureLabelGridData.horizontalSpan = 2;
    featureLabelGridData.horizontalAlignment = SWT.FILL;
    featureLabelGridData.verticalAlignment = SWT.FILL;
    featureLabel.setLayoutData(featureLabelGridData);

    final Table featureTable = new Table(featureComposite, SWT.MULTI | SWT.BORDER);
    GridData featureTableGridData = new GridData();
    featureTableGridData.widthHint = Display.getCurrent().getBounds().width / 5;
    featureTableGridData.heightHint = Display.getCurrent().getBounds().height / 3;
    featureTableGridData.verticalAlignment = SWT.FILL;
    featureTableGridData.horizontalAlignment = SWT.FILL;
    featureTableGridData.grabExcessHorizontalSpace= true;
    featureTableGridData.grabExcessVerticalSpace= true;
    featureTable.setLayoutData(featureTableGridData);

    // We carefully manage the selection because for non-unique features,
    // because the value selected by a selection-preserving refresh will be the first of the duplicates.
    //
    final TableViewer featureTableViewer = new TableViewer(featureTable)
      {
        @Override
        public void refresh(Object element, boolean updateLabels)
        {
          internalRefresh(element, updateLabels);
        }

        @Override
        public void refresh(Object element)
        {
          internalRefresh(element);
        }
      };
    featureTableViewer.setContentProvider(contentProvider);
    featureTableViewer.setLabelProvider(labelProvider);
    featureTableViewer.setInput(values);

    featureTableViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
        public void selectionChanged(SelectionChangedEvent event)
        {
          // Remove is enabled only if something is selected.
          //
          removeButton.setEnabled(!event.getSelection().isEmpty());

          int[] selectionIndices = getSelectionIndices(featureTable);

          // Up is enabled only if something will actually be moved up.
          //
          boolean upEnabled = false;
          if (selectionIndices.length > 0)
          {
            int upIndex = Math.max(selectionIndices[0] - 1, 0);
            for (int i = 0; i < selectionIndices.length; ++i)
            {
              if (upIndex++ != selectionIndices[i])
              {
                upEnabled = true;
              }
            }
          }
          upButton.setEnabled(upEnabled);

          // Down is enabled only if something will actually be moved up.
          //
          boolean downEnabled = false;
          if (selectionIndices.length > 0)
          {
            int downIndex = Math.min(selectionIndices[selectionIndices.length - 1] + 1, featureTable.getItemCount() - 1);
            for (int i = selectionIndices.length - 1; i >= 0; --i)
            {
              if (downIndex-- !=  selectionIndices[i])
              {
                downEnabled = true;
              }
            }
          }
          downButton.setEnabled(downEnabled);
        }
      });

    // Manage the active control used by the Add button.
    //
    class ActiveControl
    {
      private Control control = choiceText == null ? choiceTable : choiceText;

      public Control get()
      {
        return control;
      }

      public void set(Control control)
      {
        this.control = control;
        if (control == choiceText)
        {
          choiceText.notifyListeners(SWT.Modify, null);
        }
        else
        {
          choiceTable.notifyListeners(SWT.Selection, null);
          setErrorMessage(null);
        }
      }
    }
    final ActiveControl activeControl = new ActiveControl();

    final EList<Object> children = values.getChildren();
    if (!children.isEmpty())
    {
      featureTableViewer.setSelection(new StructuredSelection(children.get(0)));
    }

    if (choiceTableViewer != null)
    {
      choiceTableViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
          public void selectionChanged(SelectionChangedEvent event)
          {
            if (activeControl.get() == choiceTable)
            {
              addButton.setEnabled(!event.getSelection().isEmpty());
            }
          }
        });
      choiceTableViewer.addDoubleClickListener(new IDoubleClickListener()
        {
          public void doubleClick(DoubleClickEvent event)
          {
            if (addButton.isEnabled())
            {
              addButton.notifyListeners(SWT.Selection, null);
            }
          }
        });
      choiceTable.addFocusListener(new FocusAdapter()
        {
          @Override
          public void focusGained(FocusEvent e)
          {
            activeControl.set(choiceTable);
          }
        });
    }

    featureTableViewer.addDoubleClickListener(new IDoubleClickListener()
    {
      public void doubleClick(DoubleClickEvent event)
      {
        if (removeButton.isEnabled())
        {
          removeButton.notifyListeners(SWT.Selection, null);
        }
      }
    });

    if (choiceText != null)
    {
      choiceText.addKeyListener(
        new KeyAdapter()
        {
          @Override
          public void keyPressed(KeyEvent event)
          {
            if (!multiLine && (event.character == '\r' || event.character == '\n'))
            {
              event.doit = false;
              if (addButton.isEnabled())
              {
                addButton.notifyListeners(SWT.Selection, null);
              }
            }
          }
        });
      choiceText.addModifyListener(
        new ModifyListener()
        {
          public void modifyText(ModifyEvent e)
          {
            if (activeControl.get() == choiceText)
            {
              String text = choiceText.getText();
              String errorMessage = valueHandler.isValid(text);
              if (errorMessage == null && unique && children.contains(valueHandler.toValue(text)))
              {
                errorMessage = EMFEditUIPlugin.INSTANCE.getString("_UI_DuplicateValue_message");
              }
              if (choiceText.isFocusControl() || !"".equals(text))
              {
                setErrorMessage(errorMessage);
              }
              addButton.setEnabled(errorMessage == null);
            }
          }
        });
      choiceText.addFocusListener(new FocusAdapter()
        {
          @Override
          public void focusGained(FocusEvent e)
          {
            activeControl.set(choiceText);
          }
        });
    }

    upButton.addSelectionListener(
      new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent event)
        {
          // We use indices to properly handle non-unique values.
          //
          int[] selectionIndices = getSelectionIndices(featureTable);
          int index = Math.max(selectionIndices[0] - 1, 0);
          int start = index;
          for (int i = 0; i < selectionIndices.length; ++i)
          {
            children.move(index++, selectionIndices[i]);
          }

          // We manage the selection to select exactly the appropriate indices.
          //
          featureTableViewer.refresh();
          featureTable.setSelection(start, start + selectionIndices.length - 1);
          featureTable.notifyListeners(SWT.Selection, null);
        }
      });

    downButton.addSelectionListener(
      new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent event)
        {
          // We use indices to properly handle non-unique values.
          //
          int[] selectionIndices = getSelectionIndices(featureTable);
          int index = Math.min(selectionIndices[selectionIndices.length - 1] + 1, children.size() - 1);
          for (int i = selectionIndices.length - 1; i >= 0; --i)
          {
            children.move(index--, selectionIndices[i]);
          }

          // We manage the selection to select exactly the appropriate indices.
          //
          featureTableViewer.refresh();
          featureTable.setSelection(index + 1, index + selectionIndices.length);
          featureTable.notifyListeners(SWT.Selection, null);
        }
      });

    addButton.addSelectionListener(
      new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent event)
        {
          // If there is a choice table and there is no choice text or the choice table is the active control...
          //
          if (choiceTableViewer != null && (choiceText == null || activeControl.get() == choiceTable))
          {
            // Remember the selection of the choices table.
            //
            int[] selectionIndices = getSelectionIndices(choiceTable);

            // Process the selected values...
            //
            IStructuredSelection selection = (IStructuredSelection)choiceTableViewer.getSelection();
            int addCount = 0;
            for (Object value : selection.toArray())
            {
              // If duplicates are allowed or the value isn't already present...
              //
              if (!unique || !children.contains(value))
              {
                // Add the value and remember how many we added.
                children.add(value);
                ++addCount;

                // If there is a choice text, and its value is equal to the text value we added, clear that value.
                //
                if (choiceText != null && choiceText.getText().equals(valueHandler.toString(value)))
                {
                  choiceText.setText("");
                }
              }
            }

            // Select exactly the expected indices.
            //
            featureTableViewer.refresh();
            featureTable.setSelection(children.size() - addCount, children.size() - 1);
            featureTable.notifyListeners(SWT.Selection, null);

            // Refresh the choice table based on the filters and if the table isn't empty and there is no selection.
            //
            choiceTableViewer.refresh();
            if (choiceTable.getItemCount() > 0 && getSelectionIndices(choiceTable).length == 0)
            {
              // Select the index before the previously selected index.
              //
              choiceTable.setSelection(selectionIndices.length == 0 || selectionIndices[0] == 0 ? 0 : selectionIndices[0] - 1);
              choiceTableViewer.setSelection(choiceTableViewer.getSelection());
            }
          }
          else if (choiceText != null)
          {
            // Convert the value, add it, and clear the value from the choice text.
            //
            Object value = valueHandler.toValue(choiceText.getText());
            children.add(value);
            choiceText.setText("");

            // Select the new value at the end of the table.
            //
            featureTableViewer.refresh();
            featureTable.setSelection(featureTable.getItemCount() - 1);
            featureTable.notifyListeners(SWT.Selection, null);

            // If there is a choice table...
            //
            if (choiceTableViewer != null)
            {
              // Refresh it and if there is no longer a selection and the table isn't empty...
              //
              choiceTableViewer.refresh();
              if (choiceTableViewer.getSelection().isEmpty() && choiceTable.getItemCount() > 0)
              {
                // Select the first item in the table.
                //
                choiceTable.setSelection(0);
                choiceTable.notifyListeners(SWT.Selection, null);
              }
            }
          }
        }
      });

    removeButton.addSelectionListener(
      new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent event)
        {
          // Remember the selection in the feature table...
          //
          IStructuredSelection selection = (IStructuredSelection)featureTableViewer.getSelection();

          // Process the selection to remove the appropriate values.
          // And remember the value we might put into the choice text.
          //
          int[] selectionIndices = getSelectionIndices(featureTable);
          Object firstValue = children.get(selectionIndices[0]);
          for (int i = selectionIndices.length -1; i >= 0; --i)
          {
            children.remove(selectionIndices[i]);
          }

          // Refresh the feature table and select the index before the previous selection.
          //
          featureTableViewer.refresh();
          if (!children.isEmpty())
          {
            featureTable.setSelection(selectionIndices[0] == 0 ? 0 : selectionIndices[0] - 1);
          }
          featureTable.notifyListeners(SWT.Selection, null);

          // If there is a choice table...
          //
          if (choiceTableViewer != null)
          {
            // Refresh it and select the values we just removed.
            //
            choiceTableViewer.refresh();
            choiceTableViewer.setSelection(selection);

            // Check which values are actually selected, them from the list of things we tried to select...
            //
            IStructuredSelection choiceSelection = (IStructuredSelection)choiceTableViewer.getSelection();
            @SuppressWarnings("unchecked")
            List<?> values = new ArrayList<Object>(selection.toList());
            values.removeAll(choiceSelection.toList());

            // If there is a value that was removed but isn't selected in the choice table...
            //
            if (!values.isEmpty())
            {
              // Use the first such value as the value to set into the choice text.
              //
              firstValue = values.get(0);
            }
          }

          // If there is a choice text, and we have a value...
          //
          if (choiceText != null && firstValue != null)
          {
            // Convert the value to text, and set it into the choice.
            //
            String value = valueHandler.toString(firstValue);
            choiceText.setText(value);

            // If the choice table is the active control and it doesn't have a selection...
            //
            if (activeControl.get() == choiceTable && choiceTableViewer.getSelection().isEmpty())
            {
              // Make the choice text the active control.
              // This ensures that when you do a remove, that the Add button is enabled and you can add the value back.
              //
              activeControl.set(choiceText);
            }
          }
        }
      });

    // If there is a choice text, clear the error message to ensure that the dialog does not come up initially in an error state.
    // The add button will be disabled if there is an error message.
    //
    if (choiceText != null)
    {
      choiceText.getDisplay().asyncExec(new Runnable()
        {
          public void run()
          {
            if (!choiceText.isDisposed())
            {
              if (choiceTable != null && choiceTable.getItemCount() == 0)
              {
                choiceText.setFocus();
              }
              setErrorMessage(null);
            }
          }
        });
    }

    return contents;
  }

  @Override
  protected Control createButtonBar(Composite parent)
  {
    // Specialize the button area so that we can provide a label control that displays error messages.
    //
    Composite composite = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    composite.setLayout(layout);
    GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_CENTER);
    composite.setLayoutData(data);
    composite.setFont(parent.getFont());

    Label label = new Label(composite, SWT.NONE);
    label.setForeground(JFaceColors.getErrorText(label.getDisplay()));
    GridData labelData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_CENTER | GridData.GRAB_HORIZONTAL);
    labelData.horizontalIndent = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
    label.setLayoutData(labelData);

    Control buttonBar = super.createButtonBar(composite);
    GridData buttonBarData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_CENTER);
    buttonBar.setLayoutData(buttonBarData);
    return buttonBar;
  }

  /**
   * @since 2.14
   */
  protected void setErrorMessage(String message)
  {
    Label label = (Label)getButtonBar().getParent().getChildren()[0];
    label.setText(message == null ? "" : message);
    label.setToolTipText(message == null ? "" : message);
  }

  @Override
  protected void okPressed()
  {
    result = new BasicEList<Object>(values.getChildren());
    super.okPressed();
  }

  @Override
  public boolean close()
  {
    contentProvider.dispose();
    return super.close();
  }

  public EList<?> getResult()
  {
    return result;
  }

  private static int[] getSelectionIndices(Table table)
  {
    int[] selectionIndices = table.getSelectionIndices();
    Arrays.sort(selectionIndices);
    return selectionIndices;
  }
}
