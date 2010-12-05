/**
 * <copyright>
 *
 * Copyright (c) 2010 Kenn Hussey and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Kenn Hussey - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EcoreDataSetWizardPage.java,v 1.1 2010/12/05 01:42:01 khussey Exp $
 */
package org.eclipse.emf.oda.ecore.ui.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSetParameters;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.ParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.ParameterMode;
import org.eclipse.datatools.connectivity.oda.design.ResultSetColumns;
import org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;
import org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.QueryDelegate;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.oda.ecore.impl.Connection;
import org.eclipse.emf.oda.ecore.impl.Driver;
import org.eclipse.emf.oda.ecore.impl.ParameterMetaData;
import org.eclipse.emf.oda.ecore.impl.Query;
import org.eclipse.emf.oda.ecore.ui.ODAEcoreUIPlugin;
import org.eclipse.emf.oda.ecore.util.StringUtil;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;


/**
 * Implementation of an EMF ODA data set designer page for a user to create or edit a data set
 * design instance.
 */
public class EcoreDataSetWizardPage extends DataSetWizardPage
{
  protected static String DEFAULT_MESSAGE = ODAEcoreUIPlugin.INSTANCE.getString("_UI_DefaultDataSet_message"); //$NON-NLS-1$

  protected Combo queryDelegateCombo = null;

  protected Text queryTextField = null;

  protected TableViewer variablesViewer = null;
  protected List<Variable> variables = new UniqueEList<Variable>();

  protected Text contextTypeField = null;
  protected EClassifier contextType = null;

  protected Text resultTypeField = null;
  protected EClassifier resultType = null;

  public EcoreDataSetWizardPage(String pageName)
  {
    super(pageName);

    setTitle(pageName);
    setMessage(DEFAULT_MESSAGE);
    setImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(ODAEcoreUIPlugin.INSTANCE.getImage("full/wizban/NewEcore"))); //$NON-NLS-1$
  }

  public EcoreDataSetWizardPage(String pageName, String title, ImageDescriptor titleImage)
  {
    super(pageName, title, titleImage);
    setMessage(DEFAULT_MESSAGE);
  }

  @Override
  public void createPageCustomControl(Composite parent)
  {
    setControl(createPageControl(parent));
    initializeControl();
  }

  /**
   * Creates custom page control for user-defined queries.
   */
  protected Control createPageControl(Composite parent)
  {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(3, false));

    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(composite);

    createQueryDelegateControl(composite);
    createContextTypeControl(composite);
    createVariablesControl(composite);
    createQueryTextControl(composite);
    createResultTypeControl(composite);

    setPageComplete(false);

    return composite;
  }

  /**
   * Creates control for query delegate property.
   */
  protected void createQueryDelegateControl(Composite parent)
  {
    Label queryDelegateLabel = new Label(parent, SWT.NONE);
    queryDelegateLabel.setText(ODAEcoreUIPlugin.INSTANCE.getString("_UI_QueryDelegate_label")); //$NON-NLS-1$

    queryDelegateCombo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
    queryDelegateCombo.addSelectionListener(new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent ee)
        {
          validateData();
        }
      });

    for (String uri : QueryDelegate.Factory.Registry.INSTANCE.keySet())
    {
      queryDelegateCombo.add(uri);
    }

    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(2, 1).applyTo(queryDelegateCombo);
  }

  /**
   * A provider of formatted labels for types.
   */
  protected class TypeLabelProvider extends LabelProvider
  {
    @Override
    public String getText(Object element)
    {
      return StringUtil.getTypeText((EClassifier)element);
    }

    @Override
    public Image getImage(Object element)
    {
      String typeName = element instanceof EClass ? "EClass" : (element instanceof EEnum ? "EEnum" : "EDataType"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      return ExtendedImageRegistry.getInstance().getImage(EcoreEditPlugin.INSTANCE.getImage("full/obj16/" + typeName)); //$NON-NLS-1$
    }
  }

  /**
   * Opens a dialog prompting the user to select a context type.
   */
  protected void promptForContextTypeSelection()
  {
    ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), new TypeLabelProvider());

    dialog.setMessage(ODAEcoreUIPlugin.INSTANCE.getString("_UI_SelectContextType")); //$NON-NLS-1$          
    dialog.setFilter("*"); //$NON-NLS-1$
    dialog.setTitle(ODAEcoreUIPlugin.INSTANCE.getString("_UI_ContextTypeSelection_title")); //$NON-NLS-1$
    dialog.setElements(getClassChoices());

    if (contextType != null)
    {
      dialog.setInitialElementSelections(Collections.singletonList(contextType));
    }

    if (dialog.open() == Window.OK)
    {
      setContextType((EClass)dialog.getFirstResult());
      validateData();
    }
  }

  /**
   * Creates control for context type property.
   */
  protected void createContextTypeControl(Composite parent)
  {
    Label contextTypeLabel = new Label(parent, SWT.NONE);
    contextTypeLabel.setText(ODAEcoreUIPlugin.INSTANCE.getString("_UI_ContextType_label")); //$NON-NLS-1$

    contextTypeField = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
    contextTypeField.addMouseListener(new MouseAdapter()
      {
        @Override
        public void mouseDoubleClick(MouseEvent me)
        {
          promptForContextTypeSelection();
        }
      });

    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(contextTypeField);

    Button contextTypeButton = new Button(parent, SWT.PUSH);
    contextTypeButton.setText(ODAEcoreUIPlugin.INSTANCE.getString("_UI_Select_label")); //$NON-NLS-1$
    contextTypeButton.addSelectionListener(new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent se)
        {
          promptForContextTypeSelection();
        }
      });
  }

  /**
   * Representation of an expression variable.
   */
  protected static class Variable
  {
    protected final String name;
    protected final EClassifier type;

    protected Variable(String name, EClassifier type)
    {
      super();

      this.name = name;
      this.type = type;
    }

    /**
     * Returns the name of this expression variable.
     * @return the name
     */
    protected String getName()
    {
      return name;
    }

    /**
     * Returns the type of this expression variable.
     * @return the type
     */
    protected EClassifier getType()
    {
      return type;
    }
  }

  /**
   * A dialog which prompts the user to specify a name and select a type for an
   * expression variable.
   */
  protected class VariableSpecificationDialog extends ElementListSelectionDialog
  {
    protected Text nameField = null;
    protected String initialName = null;

    protected VariableSpecificationDialog(Shell shell)
    {
      super(shell, new TypeLabelProvider());

      setMessage(ODAEcoreUIPlugin.INSTANCE.getString("_UI_SpecifyNameSelectType")); //$NON-NLS-1$
      setFilter("*"); //$NON-NLS-1$
      setElements(getClassifierChoices());
    }

    @Override
    protected void computeResult()
    {
      Object[] selectedElements = getSelectedElements();
      setResult(Collections.singletonList(new Variable(nameField.getText(), (EClassifier)selectedElements[0])));
    }

    @Override
    protected Text createFilterText(Composite parent)
    {
      nameField = new Text(parent, SWT.BORDER);
      nameField.setText(initialName == null ? ODAEcoreUIPlugin.INSTANCE.getString("_UI_VariableName_label") : initialName); //$NON-NLS-1$

      GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(nameField);

      return super.createFilterText(parent);
    }

    protected void setInitialName(String initialName)
    {
      this.initialName = initialName;
    }
  }

  /**
   * Creates control for expression variables property.
   */
  protected void createVariablesControl(Composite parent)
  {
    Label variablesLabel = new Label(parent, SWT.NONE);
    variablesLabel.setText(ODAEcoreUIPlugin.INSTANCE.getString("_UI_Variables_label")); //$NON-NLS-1$

    final Table variablesTable = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);

    TableLayout tableLayout = new TableLayout();
    tableLayout.addColumnData(new ColumnWeightData(2));
    tableLayout.addColumnData(new ColumnWeightData(3));

    variablesTable.setLayout(tableLayout);

    GridDataFactory.fillDefaults().grab(true, true).applyTo(variablesTable);

    variablesTable.setHeaderVisible(true);
    variablesTable.setLinesVisible(true);

    TableColumn nameColumn = new TableColumn(variablesTable, SWT.NONE);
    nameColumn.setText(ODAEcoreUIPlugin.INSTANCE.getString("_UI_VariableName_label")); //$NON-NLS-1$

    TableColumn typeColumn = new TableColumn(variablesTable, SWT.NONE);
    typeColumn.setText(ODAEcoreUIPlugin.INSTANCE.getString("_UI_VariableType_label")); //$NON-NLS-1$

    variablesViewer = new TableViewer(variablesTable);
    variablesViewer.setContentProvider(new IStructuredContentProvider()
      {
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
        {
        }

        public void dispose()
        {
        }

        @SuppressWarnings("unchecked")
        public Object[] getElements(Object inputElement)
        {
          return ((List<Variable>)inputElement).toArray();
        }
      });
    variablesViewer.setLabelProvider(new ITableLabelProvider()
      {
        public void removeListener(ILabelProviderListener listener)
        {
        }

        public boolean isLabelProperty(Object element, String property)
        {
          return false;
        }

        public void dispose()
        {
        }

        public void addListener(ILabelProviderListener listener)
        {
        }

        public String getColumnText(Object element, int columnIndex)
        {
          Variable variable = (Variable)element;
          return columnIndex == 0 ? variable.getName() : StringUtil.getTypeText(variable.getType());
        }

        public Image getColumnImage(Object element, int columnIndex)
        {
          return null;
        }
      });

    Composite variablesButtons = new Composite(parent, SWT.NONE);
    GridLayout gridLayout = new GridLayout(1, true);
    gridLayout.marginWidth = 0;
    variablesButtons.setLayout(gridLayout);

    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).applyTo(variablesButtons);

    Button newButton = new Button(variablesButtons, SWT.PUSH);
    newButton.setText(ODAEcoreUIPlugin.INSTANCE.getString("_UI_VariableNew_label")); //$NON-NLS-1$
    newButton.addSelectionListener(new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent se)
        {
          VariableSpecificationDialog dialog = new VariableSpecificationDialog(getShell());
          dialog.setTitle(ODAEcoreUIPlugin.INSTANCE.getString("_UI_VariableNew_title")); //$NON-NLS-1$

          if (dialog.open() == Window.OK)
          {
            List<Variable> variables = getVariables();
            variables.add((Variable)dialog.getFirstResult());

            variablesViewer.setInput(variables);

            validateData();
          }
        }
      });

    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).applyTo(newButton);

    final Button editButton = new Button(variablesButtons, SWT.PUSH);
    editButton.setText(ODAEcoreUIPlugin.INSTANCE.getString("_UI_VariableEdit_label")); //$NON-NLS-1$
    editButton.addSelectionListener(new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent se)
        {
          VariableSpecificationDialog dialog = new VariableSpecificationDialog(getShell());
          dialog.setTitle(ODAEcoreUIPlugin.INSTANCE.getString("_UI_VariableEdit_title")); //$NON-NLS-1$

          Variable variable = (Variable)((IStructuredSelection)variablesViewer.getSelection()).getFirstElement();
          dialog.setInitialName(variable.getName());
          dialog.setInitialElementSelections(Collections.singletonList(variable.getType()));

          if (dialog.open() == Window.OK)
          {
            List<Variable> variables = getVariables();
            variables.set(variables.indexOf(variable), (Variable)dialog.getFirstResult());

            variablesViewer.setInput(variables);

            validateData();
          }
        }
      });
    editButton.setEnabled(false);

    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).applyTo(editButton);

    final Button removeButton = new Button(variablesButtons, SWT.PUSH);
    removeButton.setText(ODAEcoreUIPlugin.INSTANCE.getString("_UI_VariableRemove_label")); //$NON-NLS-1$
    removeButton.addSelectionListener(new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent se)
        {
          List<Variable> variables = getVariables();
          ISelection selection = variablesViewer.getSelection();

          for (Iterator< ? > selections = ((IStructuredSelection)selection).iterator(); selections.hasNext();)
          {
            variables.remove(selections.next());
          }

          variablesViewer.setInput(variables);

          validateData();
        }
      });
    removeButton.setEnabled(false);

    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).applyTo(removeButton);

    variablesViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
        public void selectionChanged(SelectionChangedEvent sce)
        {
          IStructuredSelection selection = (IStructuredSelection)sce.getSelection();

          editButton.setEnabled(selection.size() == 1);
          removeButton.setEnabled(selection.size() > 0);
        }
      });
  }

  /**
   * Creates control for query text.
   */
  protected void createQueryTextControl(Composite parent)
  {
    Label queryTextLabel = new Label(parent, SWT.NONE);
    queryTextLabel.setText(ODAEcoreUIPlugin.INSTANCE.getString("_UI_QueryText_label")); //$NON-NLS-1$

    queryTextField = new Text(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
    queryTextField.addModifyListener(new ModifyListener()
      {
        public void modifyText(ModifyEvent me)
        {
          validateData();
        }
      });

    GridDataFactory.fillDefaults().grab(true, true).span(2, 1).applyTo(queryTextField);
  }

  /**
   * Opens a dialog prompting the user to select a result type.
   */
  protected void promptForResultTypeSelection()
  {
    ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), new TypeLabelProvider());

    dialog.setMessage(ODAEcoreUIPlugin.INSTANCE.getString("_UI_SelectResultType")); //$NON-NLS-1$
    dialog.setFilter("*"); //$NON-NLS-1$
    dialog.setTitle(ODAEcoreUIPlugin.INSTANCE.getString("_UI_ResultTypeSelection_title")); //$NON-NLS-1$
    dialog.setElements(getClassifierChoices());

    if (resultType != null)
    {
      dialog.setInitialElementSelections(Collections.singletonList(resultType));
    }

    if (dialog.open() == Window.OK)
    {
      setResultType((EClassifier)dialog.getFirstResult());
      validateData();
    }
  }

  /**
   * Creates control for result type property.
   */
  protected void createResultTypeControl(Composite parent)
  {
    Label resultTypeLabel = new Label(parent, SWT.NONE);
    resultTypeLabel.setText(ODAEcoreUIPlugin.INSTANCE.getString("_UI_ResultType_label")); //$NON-NLS-1$

    resultTypeField = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
    resultTypeField.addMouseListener(new MouseAdapter()
      {
        @Override
        public void mouseDoubleClick(MouseEvent me)
        {
          promptForResultTypeSelection();
        }
      });

    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(resultTypeField);

    Button resultTypeButton = new Button(parent, SWT.PUSH);
    resultTypeButton.setText(ODAEcoreUIPlugin.INSTANCE.getString("_UI_Select_label")); //$NON-NLS-1$
    resultTypeButton.addSelectionListener(new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent se)
        {
          promptForResultTypeSelection();
        }
      });
  }

  /**
   * Initializes the page control with the last edited data set design.
   */
  protected void initializeControl()
  {
    // restore the last saved data set design
    DataSetDesign dataSetDesign = getEditingDesign();

    if (dataSetDesign == null)
    {
      return; // nothing to initialize
    }

    String queryText = dataSetDesign.getQueryText();

    if (queryText != null)
    {
      // initialize query text
      queryTextField.setText(queryText);
    }

    Properties properties = DesignUtil.convertDesignProperties(dataSetDesign.getPublicProperties());

    String queryDelegate = properties.getProperty(Query.DELEGATE_PROPERTY_NAME);

    if (queryDelegate != null)
    {
      // initialize query delegate
      queryDelegateCombo.setText(queryDelegate);
    }

    String context = properties.getProperty(Query.CONTEXT_PROPERTY_NAME);

    if (!StringUtil.isEmpty(context))
    {
      // initialize context type
      try
      {
        setContextType(getTypeFromString(context));
      }
      catch (Exception e)
      {
        // ignore
      }
    }

    String string = properties.getProperty(Query.VARIABLES_PROPERTY_NAME);

    if (!StringUtil.isEmpty(string))
    {
      // initialize expression variables
      try
      {
        List<Variable> variables = getVariables();
        variables.addAll(getVariablesFromString(string));

        variablesViewer.setInput(variables);
      }
      catch (Exception e)
      {
        // ignore
      }
    }

    String type = properties.getProperty(Query.TYPE_PROPERTY_NAME);

    if (!StringUtil.isEmpty(type))
    {
      // initialize result type
      try
      {
        setResultType(getTypeFromString(type));
      }
      catch (Exception e)
      {
        // ignore
      }
    }

    validateData();

    setMessage(DEFAULT_MESSAGE);
  }

  protected Object[] classChoices = null;

  /**
   * Returns an array of classes applicable for the data source.
   * @return the applicable classes
   */
  protected Object[] getClassChoices()
  {
    if (classChoices == null)
    {
      EList<Object> choices = new UniqueEList.FastCompare<Object>();
      choices.addAll(EcoreUtil.getObjectsByType(Arrays.asList(getClassifierChoices()), EcorePackage.Literals.ECLASS));
      classChoices = choices.toArray();
    }

    return classChoices;
  }

  protected Object[] classifierChoices = null;

  /**
   * Returns an array of classes and data types applicable for the data source.
   * @return the applicable classes and data types
   */
  protected Object[] getClassifierChoices()
  {
    if (classifierChoices == null)
    {
      EList<Object> choices = new UniqueEList.FastCompare<Object>();

      Connection connection = null;

      try
      {
        IDriver driver = new Driver();
        connection = (Connection)driver.getConnection(null);

        java.util.Properties properties = DesignSessionUtil.getEffectiveDataSourceProperties(getEditingDesign().getDataSourceDesign());
        connection.open(properties);

        for (TreeIterator<Object> allContents = EcoreUtil.getAllContents(connection.getResourceSet(), true); allContents.hasNext();)
        {
          Object next = allContents.next();

          if (next instanceof EObject)
          {
            EClass eClass = ((EObject)next).eClass();

            if (!choices.contains(eClass))
            {
              choices.add(eClass);

              for (EAttribute eAttribute : eClass.getEAllAttributes())
              {
                EDataType eType = eAttribute.getEAttributeType();

                if (eType != null)
                {
                  choices.add(eType);
                }
              }
            }
          }
        }
      }
      catch (OdaException oe)
      {
        oe.printStackTrace();
      }
      finally
      {
        closeConnection(connection);
      }

      classifierChoices = choices.toArray();
    }

    return classifierChoices;
  }

  /**
   * Converts the specified type to its string representation.
   * @param type the type
   * @return the string representation
   */
  protected String convertTypeToString(EClassifier type)
  {
    return EcoreUtil.getURI(type).toString();
  }

  /**
   * Retrieves the type corresponding to the specified string representation.
   * @param string the string representation
   * @return the type
   */
  protected EClassifier getTypeFromString(String string)
  {
    return (EClassifier)new ResourceSetImpl().getEObject(URI.createURI(string), true);
  }

  /**
   * Converts the specified expression variables to a string representation.
   * @param variables the expression variables
   * @return the string representation
   */
  protected String convertVariablesToString(List<Variable> variables)
  {
    StringBuilder sb = new StringBuilder();

    for (Variable variable : variables)
    {
      sb.append(variable.getName());
      sb.append(' ');
      sb.append(convertTypeToString(variable.getType()));
      sb.append(' ');
    }

    return sb.toString().trim();
  }

  /**
   * Converts the specified expression variables to a map representation.
   * @param variables the expression variables
   * @return the map representation
   */
  protected Map<String, EClassifier> convertVariablesToMap(List<Variable> variables)
  {
    Map<String, EClassifier> map = new HashMap<String, EClassifier>();

    for (Variable variable : variables)
    {
      map.put(variable.getName(), variable.getType());
    }

    return map;
  }

  /**
   * Retrieves the expression variables corresponding to the specified string representation.
   * @param string the string representation
   * @return the variables
   */
  protected List<Variable> getVariablesFromString(String string)
  {
    List<Variable> variables = new UniqueEList<Variable>();
    String[] strings = string.split(Pattern.quote(" ")); //$NON-NLS-1$

    for (int i = 0; i < strings.length; i += 2)
    {
      variables.add(new Variable(strings[i], getTypeFromString(strings[i + 1])));
    }

    return variables;
  }

  /**
   * Returns the value of the query delegate property.
   * @return the query delegate
   */
  protected String getQueryDelegate()
  {
    return queryDelegateCombo == null ? null : queryDelegateCombo.getText();
  }

  /**
   * Returns the value of the expression variables property.
   * @return the expression variables
   */
  protected List<Variable> getVariables()
  {
    return variables;
  }

  /**
   * Returns the value of the query text.
   * @return the query text
   */
  protected String getQueryText()
  {
    return queryTextField == null ? null : queryTextField.getText();
  }

  /**
   * Returns the value of the context type property.
   * @return the context type
   */
  protected EClassifier getContextType()
  {
    return contextType;
  }

  /**
   * Sets the value of the context type property.
   * @param contextType the value
   */
  protected void setContextType(EClassifier contextType)
  {
    this.contextType = contextType;

    if (contextTypeField != null)
    {
      contextTypeField.setText(StringUtil.getTypeText(contextType));
    }
  }

  /**
   * Returns the value of the result type property.
   * @return the result type
   */
  protected EClassifier getResultType()
  {
    return resultType;
  }

  /**
   * Sets the value of the result type property.
   * @param resultType the value
   */
  protected void setResultType(EClassifier resultType)
  {
    this.resultType = resultType;

    if (resultTypeField != null)
    {
      resultTypeField.setText(StringUtil.getTypeText(resultType));
    }
  }

  @Override
  protected DataSetDesign collectDataSetDesign(DataSetDesign design)
  {
    if (getControl() == null) // page control was never created
    {
      return design; // no editing was done
    }

    if (!hasValidData())
    {
      return null; // trigger a design session error status
    }

    savePage(design);

    return design;
  }

  @Override
  protected boolean canLeave()
  {
    return isPageComplete();
  }

  /**
   * Validates that the user has specified values for the properties in the page control
   * and sets page message accordingly.
   */
  protected void validateData()
  {
    QueryDelegate.Factory factory = (QueryDelegate.Factory)QueryDelegate.Factory.Registry.INSTANCE.get(getQueryDelegate());
    boolean isValid = factory != null;

    if (isValid)
    {
      EClassifier context = getContextType();
      isValid = context != null;

      if (isValid)
      {
        String queryText = getQueryText();
        isValid = !StringUtil.isEmpty(queryText);

        if (isValid)
        {
          isValid = getResultType() != null;

          if (isValid)
          {
            try
            {
              List<Variable> variables = getVariables();
              Map<String, EClassifier> map = variables.isEmpty() ? null : convertVariablesToMap(variables);
              factory.createQueryDelegate(context, map, queryText).prepare();

              setMessage(DEFAULT_MESSAGE);
            }
            catch (Exception e)
            {
              isValid = false;

              Throwable cause = e.getCause();
              setMessage(cause == null ? e.getLocalizedMessage() : cause.getLocalizedMessage(), ERROR);
            }
          }
          else
          {
            setMessage(ODAEcoreUIPlugin.INSTANCE.getString("_UI_ResultTypeCannotBeEmpty_message"), ERROR); //$NON-NLS-1$                              
          }
        }
        else
        {
          setMessage(ODAEcoreUIPlugin.INSTANCE.getString("_UI_QueryTextCannotBeEmpty_message"), ERROR); //$NON-NLS-1$        
        }
      }
      else
      {
        setMessage(ODAEcoreUIPlugin.INSTANCE.getString("_UI_ContextTypeCannotBeEmpty_message"), ERROR); //$NON-NLS-1$                  
      }
    }
    else
    {
      setMessage(ODAEcoreUIPlugin.INSTANCE.getString("_UI_QueryDelegateCannotBeEmpty_message"), ERROR); //$NON-NLS-1$
    }

    setPageComplete(isValid);
  }

  /**
   * Indicates whether the custom page has valid data to proceed 
   * with defining a data set.
   */
  protected boolean hasValidData()
  {
    validateData();

    return canLeave();
  }

  /**
   * Saves the user-defined values in this page, and updates the specified 
   * data set design with the latest design definition.
   */
  protected void savePage(DataSetDesign dataSetDesign)
  {
    // save user-defined query text
    String queryText = getQueryText();
    dataSetDesign.setQueryText(queryText);

    // obtain query's current runtime metadata, and maps it to the dataSetDesign
    IConnection connection = null;

    try
    {
      // instantiate runtime driver class
      IDriver driver = new Driver();

      // obtain and open a live connection
      connection = driver.getConnection(null);
      java.util.Properties properties = DesignSessionUtil.getEffectiveDataSourceProperties(getEditingDesign().getDataSourceDesign());
      connection.open(properties);

      // update the data set design with the 
      // query's current runtime metadata
      updateDesign(dataSetDesign, connection, queryText);
    }
    catch (OdaException oe)
    {
      // not able to get current metadata, reset previous derived metadata
      dataSetDesign.setResultSets(null);
      dataSetDesign.setParameters(null);

      oe.printStackTrace();
    }
    finally
    {
      closeConnection(connection);
    }
  }

  /**
   * Updates the given data set design with the proeprties and derived metadata
   * obtained from the ODA runtime connection.
   */
  protected void updateDesign(DataSetDesign dataSetDesign, IConnection connection, String queryText) throws OdaException
  {
    IQuery query = connection.newQuery(null);

    QuerySpecificationHelper helper = new QuerySpecificationHelper((String)null);
    QuerySpecification specification = helper.createQuerySpecification();

    specification.setProperty(Query.DELEGATE_PROPERTY_NAME, getQueryDelegate());
    specification.setProperty(Query.CONTEXT_PROPERTY_NAME, convertTypeToString(getContextType()));
    specification.setProperty(Query.TYPE_PROPERTY_NAME, convertTypeToString(getResultType()));

    specification.setParameterValue(specification.new ParameterIdentifier(ParameterMetaData.TARGET_PARAMETER_NAME, 1), null);

    List<Variable> variables = getVariables();

    specification.setProperty(Query.VARIABLES_PROPERTY_NAME, convertVariablesToString(variables));

    for (int i = 0; i < variables.size(); i++)
    {
      specification.setParameterValue(specification.new ParameterIdentifier(variables.get(i).getName(), i + 2), null);
    }

    query.setSpecification(specification);

    Properties properties = new Properties();
    properties.putAll(specification.getProperties());

    dataSetDesign.setPublicProperties(DesignUtil.convertToDesignProperties(properties));

    query.prepare(queryText);

    try
    {
      updateResultSetDesign(query.getMetaData(), dataSetDesign);
    }
    catch (OdaException oe)
    {
      // no result set definition available, reset previous derived metadata
      dataSetDesign.setResultSets(null);
      oe.printStackTrace();
    }

    // proceed to get parameter design definition
    try
    {
      updateParameterDesign(query.getParameterMetaData(), dataSetDesign);
    }
    catch (OdaException oe)
    {
      // no parameter definition available, reset previous derived metadata
      dataSetDesign.setParameters(null);
      oe.printStackTrace();
    }
  }

  /**
   * Updates the specified data set design's result set definition based on the
   * specified runtime metadata.
   * @param resultSetMetaData runtime result set metadata instance
   * @param dataSetDesign data set design instance to update
   * @throws OdaException
   */
  protected void updateResultSetDesign(IResultSetMetaData resultSetMetaData, DataSetDesign dataSetDesign) throws OdaException
  {
    ResultSetColumns columns = DesignSessionUtil.toResultSetColumnsDesign(resultSetMetaData);

    ResultSetDefinition definition = DesignFactory.eINSTANCE.createResultSetDefinition();
    definition.setResultSetColumns(columns);

    // no exception in conversion; go ahead and assign to specified data set design
    dataSetDesign.setPrimaryResultSet(definition);
    dataSetDesign.getResultSets().setDerivedMetaData(true);
  }

  /**
   * Updates the specified data set design's parameter definition based on the
   * specified runtime metadata.
   * @param parameterMetaData runtime parameter metadata instance
   * @param dataSetDesign data set design instance to update
   * @throws OdaException
   */
  protected void updateParameterDesign(IParameterMetaData parameterMetaData, DataSetDesign dataSetDesign) throws OdaException
  {
    ParameterMode mode = DesignSessionUtil.toParameterModeDesign(IParameterMetaData.parameterModeIn);
    DataSetParameters parameters = DesignSessionUtil.toDataSetParametersDesign(parameterMetaData, mode);

    // no exception in conversion; go ahead and assign to specified data set design
    dataSetDesign.setParameters(parameters);

    if (parameters == null)
    {
      return; // no parameter definitions; done with update
    }

    ParameterMetaData metaData = (ParameterMetaData)parameterMetaData;

    for (ParameterDefinition parameterDefinition : parameters.getParameterDefinitions())
    {
      DataElementAttributes attributes = parameterDefinition.getAttributes();

      if (attributes != null && attributes.isSetNativeDataTypeCode())
      {
        parameterDefinition.addDefaultValue(metaData.getParameterDefaultValue(attributes.getName()));
      }
    }

    parameters.setDerivedMetaData(true);
  }

  /**
   * Attempts to close the given ODA connection.
   */
  protected void closeConnection(IConnection connection)
  {
    try
    {
      if (connection != null && connection.isOpen())
      {
        connection.close();
      }
    }
    catch (OdaException e)
    {
      // ignore
      e.printStackTrace();
    }
  }

}
