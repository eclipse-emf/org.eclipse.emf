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
 *
 * $Id: ItemPropertyDescriptor.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;


/**
 * This implementation of an item property descriptor supports delegating of the {@link IItemPropertySource} interface
 * to the {@link IItemPropertyDescriptor} interface.
 */
public class ItemPropertyDescriptor implements IItemPropertyDescriptor
{
  public static final Object BOOLEAN_VALUE_IMAGE = EMFEditPlugin.INSTANCE.getImage("full/obj16/BooleanValue");
  public static final Object GENERIC_VALUE_IMAGE = EMFEditPlugin.INSTANCE.getImage("full/obj16/GenericValue");
  public static final Object INTEGRAL_VALUE_IMAGE = EMFEditPlugin.INSTANCE.getImage("full/obj16/IntegralValue");
  public static final Object REAL_VALUE_IMAGE = EMFEditPlugin.INSTANCE.getImage("full/obj16/RealValue");
  public static final Object TEXT_VALUE_IMAGE = EMFEditPlugin.INSTANCE.getImage("full/obj16/TextValue");

  /**
   * For now we need to keep track of the adapter factory, because we need it to provide a correct label provider.
   */
  protected AdapterFactory adapterFactory;

  /**
   * This is a convenient wrapper of the {@link #adapterFactory}.
   */
  protected AdapterFactoryItemDelegator itemDelegator;

  /**
   * This is returned by {@link #canSetProperty}.
   */
  protected boolean isSettable;

  /**
   * This is the name that is displayed in the property sheet.
   */
  protected String displayName;

  /**
   * This is the description shown in the property sheet when this property is selected.
   */
  protected String description;

  /**
   * This is the structural feature that provides the values for this property.
   * This is mutually exclusive with {@link #parentReferences}.
   */
  protected EStructuralFeature feature;

  /**
   * This is the set of single-valued references that act as a parent, only one can have a non null value at a time.
   * This is mutually exclusive with {@link #feature}.
   */
  protected EReference [] parentReferences;

  /**
   * This represents the group of properties into which this one should be placed.
   */
  protected String category;

  /**
   * These are the flags used as filters in the property sheet.
   */
  protected String [] filterFlags;

  /**
   * This is the label provider used to render property values.
   */
  // protected Object labelProvider;

  /**
   * This is the image that will be used with the value no matter what type of object it is.
   */
  protected Object staticImage;

  /**
   * This class uses a static image
   */
  protected class ItemDelegator extends AdapterFactoryItemDelegator
  {
    public ItemDelegator(AdapterFactory adapterFactory)
    {
      super(adapterFactory);
    }

    public String getText(Object object)
    {
      if (feature instanceof EAttribute)
      {
        EDataType eDataType = ((EAttribute)feature).getEAttributeType();
        if (eDataType.isSerializable())
        {
          if (feature.isMany())
          {
            if (object instanceof List)
            {
              StringBuffer result = new StringBuffer();
              for (Iterator i = ((List)object).iterator(); i.hasNext(); )
              {
                Object value = i.next();
                result.append(EcoreUtil.convertToString(eDataType, value));
                if (i.hasNext())
                {
                  result.append(", ");
                }
              }
              return result.toString();
            }
          }
          else if (eDataType.isInstance(object))
          {
            return EcoreUtil.convertToString(eDataType, object);
          }
        }
      }

      return super.getText(object);
    }

    public Object getImage(Object object)
    {
      return staticImage == null ? super.getImage(object) : staticImage;
    }
  }

  /**
   * This creates an instance where the category and filter flags are empty 
   * and the cell editor is determined from the type of the structural feature.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EStructuralFeature feature)
  {
    this(adapterFactory, displayName, description, feature, true);
  }

  /**
   * This creates an instance where the category and filter flags are empty 
   * and the cell editor is determined from the type of the structural feature.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable)
  {
    this.adapterFactory = adapterFactory;
    this.itemDelegator = new ItemDelegator(adapterFactory);
    this.displayName = displayName;
    this.description = description;
    this.feature = feature;
    this.isSettable = isSettable;
  }

  /**
   * This creates an instance where the filter flags are empty, there is a
   * a specifed category, and the cell editor is determined from the type of
   * the structural feature.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable,
      String category)
  {
    this.adapterFactory = adapterFactory;
    this.itemDelegator = new ItemDelegator(adapterFactory);
    this.displayName = displayName;
    this.description = description;
    this.feature = feature;
    this.isSettable = isSettable;
    this.category = category;
  }

  /**
   * This creates an instance where the category and filter flags are empty 
   * and the cell editor is determined for the references.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EReference [] parentReferences)
  {
    this(adapterFactory, displayName, description, parentReferences, true);
  }

  /**
   * This creates an instance where the category and filter flags are empty 
   * and the cell editor is determined for the references.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EReference [] parentReferences,
      boolean isSettable)
  {
    this.adapterFactory = adapterFactory;
    this.itemDelegator = new ItemDelegator(adapterFactory);
    this.displayName = displayName;
    this.description = description;
    this.parentReferences = parentReferences;
    this.isSettable = isSettable;
  }

  /**
   * This creates an instance where the category and filter flags are empty,
   * there is a static image used, and the cell editor is determined from
   * the type of the structural feature.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable,
      Object staticImage)
  {
    this.adapterFactory = adapterFactory;
    this.itemDelegator = new ItemDelegator(adapterFactory);
    this.displayName = displayName;
    this.description = description;
    this.feature = feature;
    this.isSettable = isSettable;
    this.staticImage = staticImage;
  }

  /**
   * This creates an instance where the filter flags are empty, there is a
   * static image used and a specifed category, and the cell editor is
   * determined from the type of the structural feature.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable,
      Object staticImage,
      String category)
  {
    this.adapterFactory = adapterFactory;
    this.itemDelegator = new ItemDelegator(adapterFactory);
    this.displayName = displayName;
    this.description = description;
    this.feature = feature;
    this.isSettable = isSettable;
    this.staticImage = staticImage;
    this.category = category;
  }

  /**
   * This returns the group of propertiesk into which this one should be placed.
   */
  public String getCategory(Object object) 
  {
    return category;
  }

  /**
   * This returns the description to be displayed in the property sheet when this property is selected.
   */
  public String getDescription(Object object) 
  {
    return description;
  }

  /**
   * This returns the name of the property to be displayed in the property sheet.
   */
  public String getDisplayName(Object object) 
  {
    return displayName;
  }

  /**
   * This returns the flags used as filters in the property sheet.
   */
  public String[] getFilterFlags(Object object) 
  {
    return filterFlags;
  }

  /**
   * This returns the interface name of this property.
   * This is the key that is passed around and must uniquely identifiy this descriptor.
   */
  public String getId(Object object) 
  {
    // System.out.println("getName " + feature.eClass().getEID());
    // return feature.eClass().getEID().toString();

    return displayName;
  }

  public Object getHelpContextIds(Object object)
  {
    return new String [] {};
  }

  protected static final EcorePackage ecorePackage = EcorePackage.eINSTANCE;

  /**
   * This will be called to populate a list of choices.
   * The label provider will be used to determine the labels for the objects this returns.
   * This default implementation uses {@link #getReachableObjectsOfType getReachableObjectsOfType}.
   */
  protected Collection getComboBoxObjects(Object object)
  {
    if (object instanceof EObject)
    {
      if (parentReferences != null)
      {
        Collection result = new HashSet();
        for (int i = 0; i < parentReferences.length; ++i)
        {
          result.addAll(getReachableObjectsOfType((EObject)object, parentReferences[i].getEType()));
        }
        return result;
      }
      else if (feature != null)
      {
        if (feature instanceof EReference)
        {
          return getReachableObjectsOfType((EObject)object, feature.getEType());
        }
        else if (feature.getEType() instanceof EEnum)
        {
          EEnum enum = (EEnum)feature.getEType();
          List enumerators = new ArrayList();
          for (Iterator iter = enum.getELiterals().iterator(); iter.hasNext(); )
          {
            enumerators.add(((EEnumLiteral)iter.next()).getInstance());
          }
          return enumerators;
        }
      }
    }

    return null;
  }

  /**
   * This yields all reachable references from object with a meta object which indicates that it is a subtype of type.
   */
  static public Collection getReachableObjectsOfType(EObject object, EClassifier type)
  {
    Collection visited  = new HashSet();
    Collection result = new HashSet();
    Resource resource = object.eResource();
    if (resource != null)
    {
      ResourceSet resourceSet = resource.getResourceSet();
      if (resourceSet != null)
      {
        for (TreeIterator i = resourceSet.getAllContents(); i.hasNext(); )
        {
          Object child = i.next();
          if (child instanceof EObject)
          {
            collectReachableObjectsOfType(visited, result, (EObject)child, type);
            i.prune();
          }
        }
      }
      else
      {
        for (Iterator i = resource.getContents().iterator(); i.hasNext(); )
        {
          collectReachableObjectsOfType(visited, result, (EObject)i.next(), type);
        }
      }
    }
    else
    {
      collectReachableObjectsOfType(visited, result, EcoreUtil.getRootContainer(object), type);
    }
    return result;
  }

  /**
   * This will visit all reachable references from object except those in visited;
   * it updates visited and adds to result any object with a meta object that indicates that it is a subtype of type.
   */
  static public void collectReachableObjectsOfType(Collection visited, Collection result, EObject object, EClassifier type)
  {
    if (visited.add(object))
    {
      if (type.isInstance(object))
      {
        result.add(object);
      }

      EClass metaObject = object.eClass();
      Collection eReferenceList = metaObject.getEAllReferences();
      if (eReferenceList != null)
      {
        for (Iterator eReferences = eReferenceList.iterator(); eReferences.hasNext(); )
        {
          EReference eReference = (EReference)eReferences.next();
          if (eReference.isMany())
          {
            for (Iterator referencedObjects = ((List)object.eGet(eReference)).iterator(); referencedObjects.hasNext(); )
            {
              Object referencedObject = referencedObjects.next();
              if (referencedObject instanceof EObject)
              {
                collectReachableObjectsOfType(visited, result, (EObject)referencedObject, type);
              }
            }
          }
          else
          {
            Object referencedObject = object.eGet(eReference);
            if (referencedObject instanceof EObject)
            {
              collectReachableObjectsOfType(visited, result, (EObject)referencedObject, type);
            }
          }
        }
      }
    }
  }

  /**
   * This returns the label provider that will be used to render the value of this property.
   * The implementation here just creates an {@link AdapterFactoryItemDelegator}.
   */
  public IItemLabelProvider getLabelProvider(Object object) 
  {
    return itemDelegator;
  }

  /**
   * This indicates whether these two property descriptors are equal.
   * It's not really clear to me how this is meant to be used, 
   * but it's a little bit like an equals test.
   */
  public boolean isCompatibleWith(Object object, Object anotherObject, IItemPropertyDescriptor anotherItemPropertyDescriptor) 
  {
/*
    if (propertyDescriptor == this)
    {
      return true;
    }
    else if (propertyDescriptor instanceof ItemPropertyDescriptor)
    {
      ItemPropertyDescriptor itemPropertyDescriptor = (ItemPropertyDescriptor)propertyDescriptor;
      if (adapterFactory == itemPropertyDescriptor.adapterFactory &&
            displayName.equals(itemPropertyDescriptor.displayName) &&
            (category == null && itemPropertyDescriptor.category == null || category.equals(itemPropertyDescriptor.category)))
      {
        return true;
      }
    }
*/

    return false;
  }

  static public class PropertyValueWrapper implements IItemLabelProvider, IItemPropertySource
  {
    protected Object object;
    protected Object propertyValue;
    protected Object nestedPropertySource;
    protected AdapterFactoryItemDelegator itemDelegator;

    public PropertyValueWrapper(AdapterFactory adapterFactory, Object object, Object propertyValue, Object nestedPropertySource)
    {
      this.object = object;
      this.propertyValue = propertyValue;
      this.nestedPropertySource = nestedPropertySource;
      this.itemDelegator = new AdapterFactoryItemDelegator(adapterFactory);
    }

    public String getText(Object thisObject)
    {
      return itemDelegator.getText(propertyValue);
    }

    public Object getImage(Object thisObject)
    {
      return itemDelegator.getImage(propertyValue);
    }

    public List getPropertyDescriptors(Object thisObject)
    {
      // This guards the switch.
      //
      List list = itemDelegator.getPropertyDescriptors(nestedPropertySource);
      if (list != null)
      {
        List result = new ArrayList(list.size());
        for (Iterator i = list.iterator(); i.hasNext(); )
        {
          IItemPropertyDescriptor itemPropertyDescriptor = (IItemPropertyDescriptor)i.next();
          result.add(createPropertyDescriptorDecorator(nestedPropertySource, itemPropertyDescriptor));
        }
        return result;
      }
  
      return Collections.EMPTY_LIST;
    }

    public IItemPropertyDescriptor getPropertyDescriptor(Object thisObject, Object propertyId)
    {
      return 
        createPropertyDescriptorDecorator(nestedPropertySource, itemDelegator.getPropertyDescriptor(nestedPropertySource, propertyId));
    }

    public Object getEditableValue(Object thisObject)
    {
      return propertyValue;
    }

    protected IItemPropertyDescriptor createPropertyDescriptorDecorator(Object object, IItemPropertyDescriptor itemPropertyDescriptor)
    {
      return new ItemPropertyDescriptorDecorator(object, itemPropertyDescriptor);
    }
  }

  protected Object createPropertyValueWrapper(Object object, Object propertyValue)
  {
    return new PropertyValueWrapper(adapterFactory, object, propertyValue, null);
  }

  public static Object getDefaultValue(EClassifier eType)
  {
    if (eType.getEPackage() == EcorePackage.eINSTANCE)
    {
      switch (eType.getClassifierID())
      {
        case EcorePackage.EBOOLEAN:
        case EcorePackage.EBOOLEAN_OBJECT:
        {
          return new Boolean(false);
        }
        case EcorePackage.EBYTE:
        case EcorePackage.EBYTE_OBJECT:
        {
          return new Byte((byte)0);
        }
        case EcorePackage.ECHAR:
        case EcorePackage.ECHARACTER_OBJECT:
        {
          return new Character(' ');
        }
        case EcorePackage.EDOUBLE:
        case EcorePackage.EDOUBLE_OBJECT:
        {
          return new Double(0.0);
        }
        case EcorePackage.EFLOAT:
        case EcorePackage.EFLOAT_OBJECT:
        {
          return new Float(0.0);
        }
        case EcorePackage.EINT:
        case EcorePackage.EINTEGER_OBJECT:
        {
          return new Integer(0);
        }
        case EcorePackage.ELONG:
        case EcorePackage.ELONG_OBJECT:
        {
          return new Long(0);
        }
        case EcorePackage.ESHORT:
        case EcorePackage.ESHORT_OBJECT:
        {
          return new Short((short)0);
        }
        case EcorePackage.ESTRING:
        {
          return "";
        }
      }
    }
    else if (eType instanceof EEnum)
    {
      return ((EEnumLiteral)((EEnum)eType).getELiterals().get(0)).getInstance();
    }

    return null;
  }

  /**
   * This does the delegated job of getting the property value from the given object; 
   * and it sets object, which is necessary if {@link #getComboBoxObjects getComboBoxObjects} is called.
   * It is implemented in a generic way using the structural feature or parent references.
   */
  public Object getPropertyValue(Object object)
  {
    EObject eObject = (EObject)object;
    if (feature instanceof EAttribute)
    {
      EAttribute attribute = (EAttribute)feature;
      Object result = 
        attribute.isMany() || eObject.eIsSet(attribute) || !attribute.isChangeable() ? 
          eObject.eGet(attribute) : 
          attribute.getDefaultValue();
      if (result == null)
      {
        return getDefaultValue(attribute.getEType());
      }
      else
      {
        return createPropertyValueWrapper(object, result);
      }
    }
    else if (parentReferences != null)
    {
      for (int i = 0; i < parentReferences.length; ++i)
      {
        Object result = eObject.eGet(parentReferences[i]);
        if (result != null)
        {
          return createPropertyValueWrapper(object, result);
        }
      }
      return "";
    }
    else 
    {
      return createPropertyValueWrapper(object, eObject.eGet(feature));
    }
  }

  /**
   * This does the delegated job of determine whether the property value from the given object is set.
   * It is implemented in a generic way using the structural feature.
   */
  public boolean isPropertySet(Object object)
  {
    // System.out.println("isPropertySet " + object);
    EObject eObject = (EObject)object;

    if (parentReferences != null)
    {
      for (int i = 0; i < parentReferences.length; ++i)
      {
        if (eObject.eGet(parentReferences[i]) != null)
        {
          return true;
        }
      }
      return false;
    }
    else
    {
      return 
        feature instanceof EAttribute ? 
          feature.isMany() ?
            !((List)eObject.eGet(feature)).isEmpty() : 
            eObject.eIsSet((EAttribute)feature) : 
          eObject.eGet(feature) != null;
    }
  }

  /**
   * This determines whether this descriptor's property for the object supports set (and reset).
   */
  public boolean canSetProperty(Object object)
  {
    return isSettable;
  }

  /**
   * This does the delegated job of resetting property value back to it's default value.
   * This does nothing by default. I need to think more about what it should do.
   */
  public void resetPropertyValue(Object object)
  {
    EObject eObject = (EObject)object;
    EditingDomain editingDomain = getEditingDomain(object);

    if (parentReferences != null)
    {
      Command removeCommand = null;
      for (int i = 0; i < parentReferences.length; ++i)
      {
        final EReference parentReference = parentReferences[i];
        if (eObject.eIsSet(parentReference))
        {
          if (editingDomain == null)
          {
            eObject.eUnset(parentReferences[i]);
          }
          else
          {
            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, eObject, parentReference, null));
          }
          break;
        }
      }
    }
    else
    {
      if (editingDomain == null)
      {
        eObject.eUnset(feature);
      }
      else if (feature.isMany())
      {
        editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, eObject, feature, Collections.EMPTY_LIST));
      }
      else
      {
        editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, eObject, feature, null));
      }
    }
  }

  public EditingDomain getEditingDomain(Object object)
  {
    EObject eObject = (EObject)object;
    EditingDomain result = AdapterFactoryEditingDomain.getEditingDomainFor(eObject);
    if (result == null)
    {
      if (adapterFactory instanceof ComposeableAdapterFactory)
      {
        AdapterFactory rootAdapterFactory = ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory();
        if (rootAdapterFactory instanceof IEditingDomainProvider)
        {
          result = ((IEditingDomainProvider)rootAdapterFactory).getEditingDomain();
        }
      }
    }
    return result;
  }

  /**
   * This does the delegated job of setting the property to the given value.
   * It is implemented in a generic way using the structural feature.
   */
  public void setPropertyValue(Object object, Object value)
  {
    EObject eObject = (EObject)object;
    EditingDomain editingDomain = getEditingDomain(object);

    if (parentReferences != null)
    {
      Command removeCommand = null;
      for (int i = 0; i < parentReferences.length; ++i)
      {
        Object oldValue = eObject.eGet(parentReferences[i]);
        if (oldValue != null)
        {
          final EReference parentReference = parentReferences[i];
          if (oldValue == value)
          {
            return;
          }
          else if (parentReference.getEType().isInstance(value))
          {
            if (editingDomain == null)
            {
              eObject.eSet(parentReference, value);
            }
            else
            {
              editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, eObject, parentReference, value));
            }
            return;
          }
          else
          {
            if (editingDomain == null)
            {
              eObject.eSet(parentReference, null);
            }
            else
            {
              removeCommand = SetCommand.create(editingDomain, eObject, parentReference, null);
            }
            break;
          }
        }
      }

      for (int i = 0; i < parentReferences.length; ++i)
      {
        final EReference parentReference = parentReferences[i];
        if (parentReference.getEType().isInstance(value))
        {
          if (editingDomain == null)
          {
            eObject.eSet(parentReferences[i], value);
          }
          else
          {
            if (removeCommand != null)
            {
              final CompoundCommand compoundCommand = new CompoundCommand(CompoundCommand.LAST_COMMAND_ALL);
              compoundCommand.append(removeCommand);
              compoundCommand.append(SetCommand.create(editingDomain, eObject, parentReference, value));
              editingDomain.getCommandStack().execute(compoundCommand);
            }
            else
            {
              editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, eObject, parentReference, value));
            }
          }
          break;
        }
      }
    }
    else
    {
      if (editingDomain == null)
      {
        eObject.eSet(feature, value);
      }
      else
      {
        editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, eObject, feature, value));
      }
    }
  }

  public Object getFeature(Object object)
  {
    if (feature != null)
    {
      return feature;
    }
    else if (parentReferences != null)
    {
      return parentReferences;
    }
    else
    {
      return null;
    }
  }

  public Collection getChoiceOfValues(Object object)
  {
    return getComboBoxObjects(object);
  }
}
