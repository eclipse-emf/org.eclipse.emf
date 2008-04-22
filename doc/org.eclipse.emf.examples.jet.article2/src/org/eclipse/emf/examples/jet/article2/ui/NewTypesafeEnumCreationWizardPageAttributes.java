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
import org.eclipse.emf.examples.jet.article2.model.NameUtil;
import org.eclipse.emf.examples.jet.article2.model.TypesafeEnum;



/**
 * Wizard page where the user can specify the attributes (member variables) of
 * the typesafe enumeration class.
 * 
 * @author Remko Popma
 * @version $Revision: 1.4 $ ($Date: 2008/04/22 13:35:56 $)
 */
public class NewTypesafeEnumCreationWizardPageAttributes extends WizardPage
{

  private final static String PAGE_NAME = "AttributesPage"; //$NON-NLS-1$

  private/* static */final String[] COLUMN_NAMES = new String []{ WizardMessages.getString("NewEnumWizPageAttr.col.Key"), //$NON-NLS-1$
    WizardMessages.getString("NewEnumWizPageAttr.col.Name"), //$NON-NLS-1$
    WizardMessages.getString("NewEnumWizPageAttr.col.Type"), //$NON-NLS-1$
  };

  private static final int COL_KEY = 0;

  private static final int COL_NAME = 1;

  private static final int COL_TYPE = 2;

  private static final int[] COLUMN_WIDTHS = new int []{ 40, 200, 200, };

  private CheckboxTableViewer mTableViewer = null;

  private Table mTableWidget = null;

  private Button mButtonAdd = null;

  private Button mButtonEdit = null;

  private Button mButtonRemove = null;

  /**
   * Helper class that provides content for the table widget.
   */
  protected class AttributeContentProvider implements IStructuredContentProvider
  {
    public Object[] getElements(Object inputElement)
    {
      List<Attribute> result = new ArrayList<Attribute>();
      for (Iterator<Attribute> i = ((TypesafeEnum)inputElement).attributes(); i.hasNext();)
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
  protected class AttributeLabelProvider extends LabelProvider implements ITableLabelProvider
  {

    public Image getColumnImage(Object element, int columnIndex)
    {
      return null;
    }

    public String getColumnText(Object element, int columnIndex)
    {
      if (element instanceof Attribute)
      {
        Attribute attribute = (Attribute)element;

        switch (columnIndex)
        {
          case COL_NAME:
            return attribute.getName() == null ? "" : attribute.getName();

          case COL_TYPE:
            return attribute.getType();

          case COL_KEY:
            mTableViewer.setChecked(attribute, attribute.isKey());
            return "";
        }
      }
      return null;
    }
  }

  /**
   * Constructs a <code>NewTypesafeEnumCreationWizardPageAttributes</code>.
   */
  public NewTypesafeEnumCreationWizardPageAttributes()
  {
    super(PAGE_NAME);
    setTitle(WizardMessages.getString("NewEnumWizPageAttr.title")); //$NON-NLS-1$
    setDescription(WizardMessages.getString("NewEnumWizPageAttr.description")); //$NON-NLS-1$
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
          Object element = event.getElement();
          if (element instanceof TableItem)
          { // workaround for bug in older versions of Eclipse
            element = ((TableItem)element).getData();
          }
          if (element instanceof Attribute)
          {
            Attribute attribute = (Attribute)element;
            attribute.setKey(event.getChecked());
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

  private void updateEnabledState()
  {

    mTableWidget.setEnabled(mTableWidget.getItemCount() > 0);

    boolean enabled = (mTableWidget.getSelectionCount() > 0);
    mButtonEdit.setEnabled(enabled);
    mButtonRemove.setEnabled(enabled);
  }

  private void handleAddPressed()
  {
    EditAttributeDialog dialog = new EditAttributeDialog(getShell());
    dialog.setAttribute(new Attribute());
    dialog.setTitle(WizardMessages.getString("NewEnumWizPageAttr.Add_Attribute"));
    int reply = dialog.open();
    if (reply == Window.OK)
    {
      getTypesafeEnumModel().addAttribute(dialog.getAttribute());
      mTableViewer.refresh();
      mTableViewer.setSelection(new StructuredSelection(dialog.getAttribute()));
      mTableViewer.setChecked(dialog.getAttribute(), dialog.getAttribute().isKey());
    }
    updateEnabledState();
    ((NewTypesafeEnumCreationWizardPageInstances)getNextPage()).setPageComplete(false);
    validatePage();
    mButtonAdd.forceFocus();
  }

  private void handleEditPressed()
  {
    Attribute attribute = getSelectedAttribute();
    if (attribute == null)
    {
      return;
    }

    EditAttributeDialog dialog = new EditAttributeDialog(getShell());
    dialog.setAttribute(attribute);
    dialog.setTitle(WizardMessages.getString("NewEnumWizPageAttr.Edit_Attribute"));
    dialog.open();
    mTableViewer.refresh();
    mTableViewer.setSelection(new StructuredSelection(dialog.getAttribute()));
    mTableViewer.setChecked(dialog.getAttribute(), dialog.getAttribute().isKey());
    updateEnabledState();
    validatePage();
  }

  private void handleRemovePressed()
  {
    Attribute attribute = getSelectedAttribute();
    if (attribute == null)
    {
      return;
    }
    getTypesafeEnumModel().removeAttribute(attribute);
    mTableViewer.refresh();
    updateEnabledState();
    validatePage();
  }

  private Attribute getSelectedAttribute()
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
    Attribute attribute = (Attribute)element;
    return attribute;
  }

  /**
   * @param parent
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

  private void createTableWidget(Composite container)
  {
    mTableWidget = new Table(container, SWT.CHECK | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
    mTableWidget.setHeaderVisible(true);
    mTableWidget.setLinesVisible(true);

    int listHeight = mTableWidget.getItemHeight() * 10; // show 10 rows
    Rectangle trim = mTableWidget.computeTrim(0, 0, 0, listHeight);
    GridData tblGD = new GridData(GridData.FILL_BOTH);
    tblGD.heightHint = trim.height;
    tblGD.horizontalSpan = 2; // use both columns of grid
    mTableWidget.setLayoutData(tblGD);

    TableLayout tableLayout = new TableLayout();
    for (int i = 0; i < COLUMN_WIDTHS.length; i++)
    {
      tableLayout.addColumnData(new ColumnWeightData(1, COLUMN_WIDTHS[i], true));
    }
    mTableWidget.setLayout(tableLayout);

    for (int i = 0; i < COLUMN_NAMES.length; i++)
    {
      new TableColumn(mTableWidget, SWT.NONE).setText(COLUMN_NAMES[i]);
    }
    mTableWidget.pack();

    mTableViewer = new CheckboxTableViewer(mTableWidget);
    mTableViewer.setColumnProperties(COLUMN_NAMES); // must be same as
                                                    // TableColumn text

    mTableViewer.setLabelProvider(new AttributeLabelProvider());
    mTableViewer.setContentProvider(new AttributeContentProvider());
    mTableViewer.setInput(getTypesafeEnumModel());
  }

  /**
   * Validates the attributes of the <code>TypesafeEnum</code>.
   */
  private void validatePage()
  {
    TypesafeEnum type = getTypesafeEnumModel();
    if (type.attributeCount() == 0)
    {
      setErrorMessage(WizardMessages.getString("NewEnumWizPageAttr.Must_have_at_least_one_attribute"));
      setPageComplete(false);
      return;
    }
    for (Iterator<Attribute> i = type.attributes(); i.hasNext();)
    {
      Attribute attribute = i.next();
      if (attribute.getName().length() == 0)
      {
        setErrorMessage(WizardMessages.getString("NewEnumWizPageAttr.attribute_must_have_a_name"));
        setPageComplete(false);
        return;
      }
      if (!NameUtil.isValidIdentifier(attribute.getName()))
      {
        setErrorMessage(WizardMessages.getFormattedString("NewEnumWizPageAttr.invalid_attribute_name", attribute.getName()));
        setPageComplete(false);
        return;
      }
      if (attribute.getType().length() == 0)
      {
        setErrorMessage(WizardMessages.getFormattedString("NewEnumWizPageAttr.attribute_must_have_a_type", attribute.getName()));
        setPageComplete(false);
        return;
      }
      if (!NameUtil.isValidIdentifier(attribute.getType()))
      {
        setErrorMessage(WizardMessages.getFormattedString("NewEnumWizPageAttr.invalid_attribute_type", attribute.getType()));
        setPageComplete(false);
        return;
      }
    }
    if (type.keyAttributes().hasNext() == false)
    {
      setErrorMessage(WizardMessages.getString("NewEnumWizPageAttr.missing_key_attribute"));
      setPageComplete(false);
      return;
    }
    setErrorMessage(null);
    setPageComplete(true);
  }

  /**
   * Returns the <code>TypesafeEnum</code> object shared by all pages.
   * 
   * @return the <code>TypesafeEnum</code> object to edit
   */
  public TypesafeEnum getTypesafeEnumModel()
  {
    return ((NewTypesafeEnumCreationWizardPage)getPreviousPage()).getTypesafeEnumModel();
  }
}