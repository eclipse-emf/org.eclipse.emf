/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
                }
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: PropertyDescriptor.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.edit.ui.provider;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
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
        public String getText(Object object)
        {
          return itemLabelProvider.getText(object);
        }
        public Image getImage(Object object)
        {
          return ExtendedImageRegistry.getInstance().getImage(itemLabelProvider.getImage(object));
        }
      };
  }

  public boolean isCompatibleWith(IPropertyDescriptor anotherProperty) 
  {
    return false;
  }

  /**
   * This cell editor ensures that only Integer values are supported
   */
  public static class EDataTypeCellEditor extends TextCellEditor
  {
    protected EDataType eDataType; 

    public EDataTypeCellEditor(EDataType eDataType, Composite composite)
    {
      super(composite);
      this.eDataType = eDataType;
      setValidator
        (new ICellEditorValidator()
         {
           public String isValid(Object object)
           {
             if (EDataTypeCellEditor.this.eDataType.isInstance(object))
             {
               return null;
             }
             else
             {
               String string = (String)object;
               try
               {
                 EDataTypeCellEditor.this.eDataType.getEPackage().getEFactoryInstance().createFromString
                   (EDataTypeCellEditor.this.eDataType, string);
                 return null;
               }
               catch (Exception exception)
               {
                 return exception.getMessage();
               }
             }
           }
         });
    }

    public Object doGetValue()
    {
      return eDataType.getEPackage().getEFactoryInstance().createFromString(eDataType, (String)super.doGetValue());
    }

    public void doSetValue(Object value)
    {
      String stringValue = eDataType.getEPackage().getEFactoryInstance().convertToString(eDataType, value);
      super.doSetValue(stringValue == null ? "" : stringValue);
    }
  }

  /**
   * This cell editor ensures that only Integer values are supported
   * @deprecated
   */
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

    public Object doGetValue()
    {
      return new Integer(Integer.parseInt((String)super.doGetValue()));
    }

    public void doSetValue(Object value)
    {
      super.doSetValue(value.toString());
    }
  }

  /**
   * This cell editor ensures that only Float values are supported
   * @deprecated
   */
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

    public Object doGetValue()
    {
      return new Float(Float.parseFloat((String)super.doGetValue()));
    }

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
    if (genericFeature instanceof EReference [])
    {
      result = 
        new ExtendedComboBoxCellEditor
          (composite, new ArrayList(itemPropertyDescriptor.getChoiceOfValues(object)), getLabelProvider(), true);
    }
    else if (genericFeature instanceof EStructuralFeature)
    {
      final EStructuralFeature feature = (EStructuralFeature)genericFeature;
      final EClassifier eType = ((EStructuralFeature)feature).getEType();
      final Collection choiceOfValues = itemPropertyDescriptor.getChoiceOfValues(object);
      if (choiceOfValues != null)
      {
        if (feature.isMany())
        {
          boolean valid = true;
          for (Iterator i = choiceOfValues.iterator(); i.hasNext(); )
          {
            Object choice = i.next();
            if (!eType.isInstance(choice))
            {
              valid = false;
              break;
            }
          }

          if (valid)
          {
            result = 
              new ExtendedDialogCellEditor(composite, getLabelProvider())
              {
                protected Object openDialogBox(Control cellEditorWindow)
                {
                  FeatureEditorDialog dialog = 
                    new FeatureEditorDialog
                      (cellEditorWindow.getShell(), 
                       getLabelProvider(),
                       object, 
                       feature.getEType(), 
                       (List)((IItemPropertySource)itemPropertyDescriptor.getPropertyValue(object)).getEditableValue(object), 
                       getDisplayName(),
                       new ArrayList(choiceOfValues));
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
              (composite, new ArrayList(choiceOfValues), getLabelProvider(), true);
        }
      }
      else if (eType instanceof EDataType)
      {
        EDataType eDataType = (EDataType)eType;
        if (eDataType.isSerializable())
        {
          if (feature.isMany()) 
          {
            result = 
              new ExtendedDialogCellEditor(composite, getLabelProvider())
              {
                protected Object openDialogBox(Control cellEditorWindow)
                {
                  FeatureEditorDialog dialog = 
                    new FeatureEditorDialog
                      (cellEditorWindow.getShell(), 
                       getLabelProvider(),
                       object, 
                       feature.getEType(), 
                       (List)((IItemPropertySource)itemPropertyDescriptor.getPropertyValue(object)).getEditableValue(object), 
                       getDisplayName(),
                       null);
                  dialog.open();
                  return dialog.getResult();
                }
              };
          }
          else if (eDataType == EcorePackage.eINSTANCE.getEBoolean() || eDataType == EcorePackage.eINSTANCE.getEBooleanObject())
          {
            result = 
              new ExtendedComboBoxCellEditor
                (composite, Arrays.asList(new Object [] { new Boolean(false), new Boolean(true) }), getLabelProvider(), true);
          }
          else
          {
            result = new EDataTypeCellEditor(eDataType, composite);
          }
        }
      }
    }

    return result;
  }
}
