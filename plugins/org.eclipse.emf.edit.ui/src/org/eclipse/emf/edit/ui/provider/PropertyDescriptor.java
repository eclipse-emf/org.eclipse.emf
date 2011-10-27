/**
 * <copyright> 
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: PropertyDescriptor.java,v 1.20 2011/10/27 08:57:01 emerks Exp $
 */
package org.eclipse.emf.edit.ui.provider;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;


/**
 * This is used to encapsulate an {@link IItemPropertyDescriptor} along with the object for which it is an item property source
 * and make it behave like an {@link org.eclipse.ui.views.properties.IPropertyDescriptor}.
 */
public class PropertyDescriptor implements IPropertyDescriptor
{
  /**
   * This is the object for which this class is a property source.
   */
  protected Object object;

  /**
   * This is the descriptor to which we will delegate all the {@link org.eclipse.ui.views.properties.IPropertyDescriptor} methods.
   */
  protected IItemPropertyDescriptor itemPropertyDescriptor;

  /**
   * An instance is constructed from an object and its item property source.
   */
  public PropertyDescriptor(Object object, IItemPropertyDescriptor itemPropertyDescriptor)
  {
    this.object = object;
    this.itemPropertyDescriptor = itemPropertyDescriptor;
  }

  public String getCategory() 
  {
    return itemPropertyDescriptor.getCategory(object);
  }

  public String getDescription() 
  {
    return itemPropertyDescriptor.getDescription(object);
  }

  public String getDisplayName() 
  {
    return itemPropertyDescriptor.getDisplayName(object);
  }

  public String[] getFilterFlags() 
  {
    return itemPropertyDescriptor.getFilterFlags(object);
  }

  public Object getHelpContextIds() 
  {
    return itemPropertyDescriptor.getHelpContextIds(object);
  }

  public Object getId() 
  {
    return itemPropertyDescriptor.getId(object);
  }

  public ILabelProvider getLabelProvider() 
  {
    final IItemLabelProvider itemLabelProvider = itemPropertyDescriptor.getLabelProvider(object);
    return 
      new LabelProvider()
      {
        @Override
        public String getText(Object object)
        {
          return itemLabelProvider.getText(object);
        }
        @Override
        public Image getImage(Object object)
        {
          return ExtendedImageRegistry.getInstance().getImage(itemLabelProvider.getImage(object));
        }
      };
  }

  protected ILabelProvider getEditLabelProvider()
  {
    return getLabelProvider();
  }

  public boolean isCompatibleWith(IPropertyDescriptor anotherProperty) 
  {
    return false;
  }

  /**
   * A delegate for handling validation and conversion for data type values.
   */
  protected static class EDataTypeValueHandler implements ICellEditorValidator, IInputValidator
  {
    protected EDataType eDataType;

    public EDataTypeValueHandler(EDataType eDataType)
    {
      this.eDataType = eDataType;
    }

    public String isValid(Object object)
    {
      Object value;
      try
      {
        value = eDataType.getEPackage().getEFactoryInstance().createFromString(eDataType, (String)object);
      }
      catch (Exception exception)
      {
        String message = exception.getClass().getName();
        int index = message.lastIndexOf('.');
        if (index >= 0)
        {
          message = message.substring(index + 1);
        }
        if (exception.getLocalizedMessage() != null)
        {
          message = message + ": " + exception.getLocalizedMessage();
        }
        return message;
      }
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eDataType, value);
      if (diagnostic.getSeverity() == Diagnostic.OK)
      {
        return null;
      }
      else
      {
        return (diagnostic.getChildren().get(0)).getMessage().replaceAll("'","''").replaceAll("\\{", "'{'"); // }}
      }
    }

    public String isValid(String text)
    {
      return isValid((Object)text);
    }

    public Object toValue(String string)
    {
      return EcoreUtil.createFromString(eDataType, string);
    }

    public String toString(Object value)
    {
      String result = EcoreUtil.convertToString(eDataType, value);
      return result == null ? "" : result;
    }
    
  }

  public static class EDataTypeCellEditor extends TextCellEditor
  {
    protected EDataType eDataType;
    protected EDataTypeValueHandler valueHandler;  

    public EDataTypeCellEditor(EDataType eDataType, Composite parent)
    {
      super(parent);
      this.eDataType = eDataType;
      valueHandler = new EDataTypeValueHandler(eDataType);
      setValidator(valueHandler);
    }

    @Override
    public Object doGetValue()
    {
      return valueHandler.toValue((String)super.doGetValue());
    }

    @Override
    public void doSetValue(Object value)
    {
      value = valueHandler.toString(value);
      super.doSetValue(value);
    }

    // CellEditor.setValue() calls isCorrect() to validate the value that is about to be set. We are doing conversion
    // between the value and a corresponding string, and we would usually like to validate the string. Because
    // setValue() is final, we cannot do that conversion there, so we need to record what we're trying to validate and
    // work around the problem in isCorrect().
    // 
    protected boolean validateAsValue = true;

    @Override
    protected void editOccured(ModifyEvent e)
    {
      validateAsValue = false;
      super.editOccured(e);
      validateAsValue = true;
    }

    @Override
    protected boolean isCorrect(Object value)
    {
      if (validateAsValue)
      {
        value = valueHandler.toString(value);
      }
      return super.isCorrect(value);
    }
  }

  private static class MultiLineInputDialog extends InputDialog
  {
    public MultiLineInputDialog(Shell parentShell, String title, String message, String initialValue, IInputValidator validator)
    {
      super(parentShell, title, message, initialValue, validator);
      setShellStyle(getShellStyle() | SWT.RESIZE);
    }

    @Override
    protected Text createText(Composite composite)
    {
      Text text = new Text(composite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
      GridData data = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
      data.heightHint = 5 * text.getLineHeight();
      data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.ENTRY_FIELD_WIDTH);
      text.setLayoutData(data);
      return text;
    }
  }

  protected CellEditor createEDataTypeCellEditor(final EDataType eDataType, Composite composite)
  {
    if (itemPropertyDescriptor.isMultiLine(object))
    {
      return new ExtendedDialogCellEditor(composite, getEditLabelProvider())
      {
        protected EDataTypeValueHandler valueHandler = new EDataTypeValueHandler(eDataType);

        @Override
        protected Object openDialogBox(Control cellEditorWindow)
        {
          InputDialog dialog = new MultiLineInputDialog
            (cellEditorWindow.getShell(),
             EMFEditUIPlugin.INSTANCE.getString
               ("_UI_FeatureEditorDialog_title", new Object [] { getDisplayName(), getEditLabelProvider().getText(object) }),
             EMFEditUIPlugin.INSTANCE.getString("_UI_MultiLineInputDialog_message"),
             valueHandler.toString(getValue()),
             valueHandler);
          return dialog.open() == Window.OK ? valueHandler.toValue(dialog.getValue()) : null;
        }
      };
    }
    return new EDataTypeCellEditor(eDataType, composite);
  }

  /**
   * This cell editor ensures that only Integer values are supported
   * @deprecated
   */
  @Deprecated
  public static class IntegerCellEditor extends TextCellEditor
  {
    public IntegerCellEditor(Composite composite)
    {
      super(composite);
      setValidator
        (new ICellEditorValidator()
         {
           public String isValid(Object object)
           {
             if (object instanceof Integer)
             {
               return null;
             }
             else
             {
               String string = (String)object;
               try
               {
                 Integer.parseInt(string);
                 return null;
               }
               catch (NumberFormatException exception)
               {
                 return exception.getMessage();
               }
             }
           }
         });
    }

    @Override
    public Object doGetValue()
    {
      return Integer.parseInt((String)super.doGetValue());
    }

    @Override
    public void doSetValue(Object value)
    {
      super.doSetValue(value.toString());
    }
  }

  /**
   * This cell editor ensures that only Float values are supported
   * @deprecated
   */
  @Deprecated
  public static class FloatCellEditor extends TextCellEditor
  {
    public FloatCellEditor(Composite composite)
    {
      super(composite);
      setValidator
        (new ICellEditorValidator()
         {
           public String isValid(Object object)
           {
             if (object instanceof Float)
             {
               return null;
             }
             else
             {
               String string = (String)object;
               try
               {
                 Float.parseFloat(string);
                 return null;
               }
               catch (NumberFormatException exception)
               {
                 return exception.getMessage();
               }
             }
           }
         });
    }

    @Override
    public Object doGetValue()
    {
      return new Float(Float.parseFloat((String)super.doGetValue()));
    }

    @Override
    public void doSetValue(Object value)
    {
      super.doSetValue(value.toString());
    }
  }

  protected static final EcorePackage ecorePackage = EcorePackage.eINSTANCE;

  /**
   * This returns the cell editor that will be used to edit the value of this property.
   * This default implementation determines the type of cell editor from the nature of the structural feature.
   */
  public CellEditor createPropertyEditor(Composite composite) 
  {
    if (!itemPropertyDescriptor.canSetProperty(object))
    {
      return null;
    }

    CellEditor result = null;

    Object genericFeature = itemPropertyDescriptor.getFeature(object);
    if (genericFeature instanceof EReference[])
    {
      result = new ExtendedComboBoxCellEditor(
        composite,
        new ArrayList<Object>(itemPropertyDescriptor.getChoiceOfValues(object)),
        getEditLabelProvider(),
        itemPropertyDescriptor.isSortChoices(object));
    }
    else if (genericFeature instanceof EStructuralFeature)
    {
      final EStructuralFeature feature = (EStructuralFeature)genericFeature;
      final EClassifier eType = feature.getEType();
      final Collection<?> choiceOfValues = itemPropertyDescriptor.getChoiceOfValues(object);
      if (choiceOfValues != null)
      {
        if (itemPropertyDescriptor.isMany(object))
        {
          boolean valid = true;
          for (Object choice : choiceOfValues)
          {
            if (!eType.isInstance(choice))
            {
              valid = false;
              break;
            }
          }

          if (valid)
          {
            final ILabelProvider editLabelProvider = getEditLabelProvider();
            result =
              new ExtendedDialogCellEditor(composite, editLabelProvider)
              {
                @Override
                protected Object openDialogBox(Control cellEditorWindow)
                {
                  FeatureEditorDialog dialog = new FeatureEditorDialog(
                    cellEditorWindow.getShell(),
                    editLabelProvider,
                    object,
                    feature.getEType(),
                    (List<?>)doGetValue(),
                    getDisplayName(),
                    new ArrayList<Object>(choiceOfValues),
                    false,
                    itemPropertyDescriptor.isSortChoices(object),
                    feature.isUnique());
                  dialog.open();
                  return dialog.getResult();
                }
              };
          }
        }

        if (result == null)
        {
          result = 
            new ExtendedComboBoxCellEditor
              (composite, new ArrayList<Object>(choiceOfValues), getEditLabelProvider(), itemPropertyDescriptor.isSortChoices(object));
        }
      }
      else if (eType instanceof EDataType)
      {
        EDataType eDataType = (EDataType)eType;
        if (eDataType.isSerializable())
        {
          if (itemPropertyDescriptor.isMany(object))
          {
            final ILabelProvider editLabelProvider = getEditLabelProvider();
            result = 
              new ExtendedDialogCellEditor(composite, editLabelProvider)
              {
                @Override
                protected Object openDialogBox(Control cellEditorWindow)
                {
                  FeatureEditorDialog dialog = new FeatureEditorDialog(
                    cellEditorWindow.getShell(),
                    editLabelProvider,
                    object,
                    feature.getEType(),
                    (List<?>)doGetValue(),
                    getDisplayName(),
                    null,
                    itemPropertyDescriptor.isMultiLine(object),
                    false,
                    feature.isUnique());
                  dialog.open();
                  return dialog.getResult();
                }
              };
          }
          else if (eDataType.getInstanceClass() == Boolean.class) 
          {
            result = new ExtendedComboBoxCellEditor(
              composite,
              Arrays.asList(new Object [] { null, Boolean.FALSE, Boolean.TRUE }),
              getEditLabelProvider(),
              itemPropertyDescriptor.isSortChoices(object));
          }
          else if (eDataType.getInstanceClass() == Boolean.TYPE)
          {
            result = new ExtendedComboBoxCellEditor(
              composite,
              Arrays.asList(new Object [] { Boolean.FALSE, Boolean.TRUE }),
              getEditLabelProvider(),
              itemPropertyDescriptor.isSortChoices(object));
          }
          else
          {
            result = createEDataTypeCellEditor(eDataType, composite);
          }
        }
      }
    }

    return result;
  }
}
