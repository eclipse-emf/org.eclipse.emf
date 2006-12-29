package org.eclipse.emf.examples.jet.article2.ui;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import org.eclipse.emf.examples.jet.article2.model.Attribute;
import org.eclipse.emf.examples.jet.article2.model.Instance;
import org.eclipse.emf.examples.jet.article2.model.NameUtil;
import org.eclipse.emf.examples.jet.article2.model.TypesafeEnum;



/**
 * Wizard page where the user can specify the instances of the typesafe
 * enumeration class, and the attribute values for each instance.
 * 
 * @author Remko Popma
 * @version $Revision: 1.3 $ ($Date: 2006/12/29 18:36:19 $)
 */
public class NewTypesafeEnumCreationWizardPageInstances extends WizardPage
{

  private final static String PAGE_NAME = "InstancesPage"; //$NON-NLS-1$

  private String[] mColumnNames = new String []{
    WizardMessages.getString("NewEnumWizPageInst.col.Default"),
    WizardMessages.getString("NewEnumWizPageInst.col.Name"), };

  private static final int COL_DEFAULT = 0;

  private static final int COL_NAME = 1;

  private int[] mColumnWidths = new int []{ 30, 400, };

  private static final int TABLE_ROWS = 15; // show 15 rows

  private boolean mIgnoreCheckEvents = false;

  private CheckboxTableViewer mTableViewer = null;

  private Table mTableWidget = null;

  private Button mButtonAdd = null;

  private Button mButtonEdit = null;

  private Button mButtonRemove = null;

  /**
   * Helper class that provides content for the table widget.
   */
  protected class InstanceContentProvider implements IStructuredContentProvider
  {
    public Object[] getElements(Object inputElement)
    {
      List<Instance> result = new ArrayList<Instance>();
      for (Iterator<Instance> i = ((TypesafeEnum)inputElement).instances(); i.hasNext();)
      {
        result.add(i.next());
      }
      return result.toArray();
    }

    public void dispose()
    {
      // Ignore
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
    {
      // Ignore
    }
  }

  /**
   * Helper class that knows how to display a table widget row.
   */
  protected class InstanceLabelProvider extends LabelProvider implements ITableLabelProvider
  {

    public Image getColumnImage(Object element, int columnIndex)
    {
      return null;
    }

    public String getColumnText(Object element, int columnIndex)
    {
      if (element instanceof Instance)
      {
        Instance instance = (Instance)element;

        switch (columnIndex)
        {
          case COL_DEFAULT:
            mTableViewer.setChecked(instance, instance.isDefault());
            return "";

          case COL_NAME:
            return instance.getName() == null ? "" : instance.getName();

          default:
            String property = mColumnNames[columnIndex];
            String result = instance.getAttributeValue(property);
            return (result == null) ? "" : result;
        }
      }
      return "";
    }
  }

  /**
   * Constructs a new <code>NewTypesafeEnumCreationWizardPageInstances</code>.
   */
  public NewTypesafeEnumCreationWizardPageInstances()
  {
    super(PAGE_NAME);
    setTitle(WizardMessages.getString("NewEnumWizPageInst.title")); //$NON-NLS-1$
    setDescription(WizardMessages.getString("NewEnumWizPageInst.description")); //$NON-NLS-1$
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent)
  {
    initializeDialogUnits(parent);
    Composite container = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    container.setLayout(layout);

    container.setLayoutData(new GridData(GridData.FILL_BOTH));

    createTableWidget(container);
    createButtonsPanel(container);

    hookListeners();
    updateEnabledState();
    mButtonAdd.forceFocus();

    setControl(container);
    Dialog.applyDialogFont(container);
    setPageComplete(false);
  }

  /**
   * Registers listeners with the widgets.
   */
  private void hookListeners()
  {
    mTableViewer.addCheckStateListener(new ICheckStateListener()
      {
        public void checkStateChanged(CheckStateChangedEvent event)
        {

          if (mIgnoreCheckEvents)
          {
            return;
          }

          Object element = event.getElement();
          if (element instanceof TableItem)
          { // workaround for bug in older versions of Eclipse
            element = ((TableItem)element).getData();
          }
          if (element instanceof Instance)
          {
            Instance instance = (Instance)element;
            if (event.getChecked())
            {
              instance.setDefault();
            }
            else
            {
              instance.getType().setDefaultInstance(null);
            }
            syncTableCheckWithModel();
            validatePage();
          }
        }
      });
    mTableViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
        public void selectionChanged(SelectionChangedEvent event)
        {
          updateEnabledState();
        }
      });
    mTableViewer.addDoubleClickListener(new IDoubleClickListener()
      {
        public void doubleClick(DoubleClickEvent event)
        {
          handleEditPressed();

        }
      });

    mButtonAdd.addSelectionListener(new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent e)
        {
          handleAddPressed();
        }
      });
    mButtonEdit.addSelectionListener(new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent e)
        {
          handleEditPressed();
        }
      });
    mButtonRemove.addSelectionListener(new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent e)
        {
          handleRemovePressed();
        }
      });
  }

  private void syncTableCheckWithModel()
  {
    mIgnoreCheckEvents = true;
    for (Iterator<Instance> i = getTypesafeEnumModel().instances(); i.hasNext();)
    {
      Instance inst = i.next();
      mTableViewer.setChecked(inst, inst.isDefault());
    }
    mIgnoreCheckEvents = false;
  }

  private void updateEnabledState()
  {

    mTableWidget.setEnabled(mTableWidget.getItemCount() > 0);

    boolean enabled = (mTableWidget.getSelectionCount() > 0);
    mButtonEdit.setEnabled(enabled);
    mButtonRemove.setEnabled(enabled);
  }

  private void handleAddPressed()
  {
    EditInstanceDialog dialog = new EditInstanceDialog(getShell());
    Instance instance = new Instance();
    getTypesafeEnumModel().addInstance(instance); // or cannot setDefault...

    dialog.setInstance(instance);
    dialog.setAttributes(getTypesafeEnumModel().attributeArray());
    dialog.setTitle(WizardMessages.getString("NewEnumWizPageInst.Add_Instance"));

    int reply = dialog.open();
    if (reply == Window.OK)
    {
      mTableViewer.refresh();
      mTableViewer.setSelection(new StructuredSelection(dialog.getInstance()));
      syncTableCheckWithModel();
    }
    else
    {
      getTypesafeEnumModel().removeInstance(dialog.getInstance());
      mTableViewer.refresh();
    }
    updateEnabledState();
    validatePage();
    mButtonAdd.forceFocus();
  }

  private void handleEditPressed()
  {
    Instance instance = getSelectedInstance();
    if (instance == null)
    {
      return;
    }

    EditInstanceDialog dialog = new EditInstanceDialog(getShell());
    dialog.setInstance(instance);
    dialog.setAttributes(getTypesafeEnumModel().attributeArray());
    dialog.setTitle(WizardMessages.getString("NewEnumWizPageInst.Edit_Instance"));

    dialog.open();
    mTableViewer.refresh();
    mTableViewer.setSelection(new StructuredSelection(dialog.getInstance()));
    syncTableCheckWithModel();
    updateEnabledState();
    validatePage();
  }

  private void handleRemovePressed()
  {
    Instance instance = getSelectedInstance();
    if (instance == null)
    {
      return;
    }
    getTypesafeEnumModel().removeInstance(instance);
    mTableViewer.refresh();
    updateEnabledState();
    validatePage();
  }

  private Instance getSelectedInstance()
  {
    IStructuredSelection selection = (IStructuredSelection)mTableViewer.getSelection();
    if (selection.isEmpty())
    {
      return null;
    }

    Object element = selection.getFirstElement();
    if (element instanceof TableItem)
    { // workaround for bug in older versions of Eclipse
      element = ((TableItem)element).getData();
    }
    Instance result = (Instance)element;
    return result;
  }

  /**
   * Create the button panel.
   */
  private void createButtonsPanel(Composite parent)
  {
    Composite panel = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.numColumns = 3;
    panel.setLayout(layout);

    mButtonAdd = new Button(panel, SWT.PUSH);
    mButtonEdit = new Button(panel, SWT.PUSH);
    mButtonRemove = new Button(panel, SWT.PUSH);

    mButtonAdd.setText(WizardMessages.getString("NewEnumWizPageAttr.button.Add"));
    mButtonEdit.setText(WizardMessages.getString("NewEnumWizPageAttr.button.Edit"));
    mButtonRemove.setText(WizardMessages.getString("NewEnumWizPageAttr.button.Remove"));
  }

  /**
   * Create the table widget.
   */
  private void createTableWidget(Composite container)
  {
    mTableWidget = new Table(container, SWT.CHECK | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
    mTableWidget.setHeaderVisible(true);
    mTableWidget.setLinesVisible(true);

    initTableLayoutData(TABLE_ROWS);
    initTableColumns();

    mTableWidget.pack();

    mTableViewer = new CheckboxTableViewer(mTableWidget);
    mTableViewer.setColumnProperties(mColumnNames); // must be same as
                                                    // TableColumn text

    mTableViewer.setLabelProvider(new InstanceLabelProvider());
    mTableViewer.setContentProvider(new InstanceContentProvider());
    mTableViewer.setInput(getTypesafeEnumModel());
  }

  /**
   * Recalculate the table height needed for the specified number of rows.
   */
  private void initTableLayoutData(int rows)
  {
    int listHeight = mTableWidget.getItemHeight() * rows;
    Rectangle trim = mTableWidget.computeTrim(0, 0, 0, listHeight);
    GridData tblGD = new GridData(GridData.FILL_BOTH);
    tblGD.heightHint = trim.height;
    tblGD.horizontalSpan = 2; // use both columns of grid
    mTableWidget.setLayoutData(tblGD);
  }

  private void initTableColumns()
  {
    TableColumn[] existing = mTableWidget.getColumns();

    // erase existing table column headers
    for (int i = 0; i < existing.length; i++)
    {
      existing[i].setText("");
    }

    // add table columns if necessary
    for (int i = existing.length; i < mColumnNames.length; i++)
    {
      new TableColumn(mTableWidget, SWT.NONE);
    }

    // set table column headers
    for (int i = 0; i < mColumnNames.length; i++)
    {
      TableColumn column = mTableWidget.getColumn(i);
      column.setText(mColumnNames[i]);
    }

    // set table column widths
    TableLayout tableLayout = new TableLayout();
    for (int i = 0; i < mColumnWidths.length; i++)
    {
      tableLayout.addColumnData(new ColumnWeightData(1, mColumnWidths[i], true));
    }
    mTableWidget.setLayout(tableLayout);
  }

  /**
   * Validates the attributes of the <code>TypesafeEnum</code>.
   */
  /* package */void validatePage()
  {
    TypesafeEnum type = getTypesafeEnumModel();
    if (type.instanceCount() == 0)
    {
      setErrorMessage(WizardMessages.getString("NewEnumWizPageInst.Must_have_at_least_one_instance"));
      setPageComplete(false);
      return;
    }
    for (Iterator<Instance> i = type.instances(); i.hasNext();)
    {
      Instance instance = i.next();
      if (instance.getName().length() == 0)
      {
        setErrorMessage(WizardMessages.getString("NewEnumWizPageInst.Every_instance_must_have_a_name"));
        setPageComplete(false);
        return;
      }
      if (!NameUtil.isValidIdentifier(instance.getName()))
      {
        setErrorMessage(WizardMessages.getFormattedString("NewEnumWizPageInst.invalid_instance_name", instance.getName()));
        setPageComplete(false);
        return;
      }
      for (Iterator<Attribute> attributes = type.attributes(); attributes.hasNext();)
      {
        Attribute attribute = attributes.next();
        String value = instance.getAttributeValue(attribute);
        if (value == null || value.trim().length() == 0)
        {
          setErrorMessage(WizardMessages.getFormattedString(
            "NewEnumWizPageInst.Instance_does_not_have_a_value_for_attribute",
            new String []{ instance.getName(), attribute.getName() }));
          setPageComplete(false);
          return;
        }
      }
    }
    setErrorMessage(null);
    setPageComplete(true);
  }

  /*
   * Attributes may have changed. Re-initialize the table widget.
   */
  @Override
  public void setVisible(boolean visible)
  {
    if (visible)
    {
      mapAttributesToTableModel();
      mTableViewer.setInput(getTypesafeEnumModel());

      getControl().pack(true); // redo layout
      mTableViewer.refresh();
    }
    super.setVisible(visible);
  }

  /**
   * Re-initializes the member variables containing the column names, column
   * widths, and finally re-initializes the table widget.
   */
  private void mapAttributesToTableModel()
  {
    reinitColumnNames();
    reinitColumnWidths();

    initTableLayoutData(TABLE_ROWS);
    initTableColumns();
  }

  private void reinitColumnNames()
  {
    mColumnNames = new String [2 + getTypesafeEnumModel().attributeCount()];
    //System.out.println("Init Column names, length = " + mColumnNames.length);
    int col = 0;
    mColumnNames[col++] = WizardMessages.getString("NewEnumWizPageInst.col.Default");
    mColumnNames[col++] = WizardMessages.getString("NewEnumWizPageInst.col.Name");
    for (Iterator<Attribute> i = getTypesafeEnumModel().attributes(); i.hasNext();)
    {
      Attribute attribute = i.next();
      mColumnNames[col++] = attribute.getName();
      //System.out.println("colname[" + (col-1) + "] = " +
      // attribute.getName());
    }
  }

  private void reinitColumnWidths()
  {
    mColumnWidths = new int [2 + getTypesafeEnumModel().attributeCount()];
    //System.out.println("Init Column widths, length = " +
    // mColumnWidths.length);
    mColumnWidths[0] = 30;
    mColumnWidths[1] = 120;
    for (int i = 2; i < mColumnWidths.length; i++)
    {
      mColumnWidths[i] = 250 / (mColumnWidths.length - 2);
      //System.out.println("colwidth[" + i + "] = " + mColumnWidths[i]);
    }
  }

  /**
   * Returns the <code>TypesafeEnum</code> to edit.
   * 
   * @return the <code>TypesafeEnum</code> to edit
   */
  public TypesafeEnum getTypesafeEnumModel()
  {
    return ((NewTypesafeEnumCreationWizardPageAttributes)getPreviousPage()).getTypesafeEnumModel();
  }
}