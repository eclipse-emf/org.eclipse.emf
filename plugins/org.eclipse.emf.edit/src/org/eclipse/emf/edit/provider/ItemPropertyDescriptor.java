/**
 * <copyright> 
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: ItemPropertyDescriptor.java,v 1.34 2011/12/05 15:34:04 emerks Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.UniqueEList;
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
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor.OverrideableCommandOwner;


/**
 * This implementation of an item property descriptor supports delegating of the {@link IItemPropertySource} interface
 * to the {@link IItemPropertyDescriptor} interface.
 */
public class ItemPropertyDescriptor implements IItemPropertyDescriptor, OverrideableCommandOwner
{
  /**
   * Returns the feature's default {@link #getId(Object) identifier}.
   * @param eStructuralFeature the feature to lookup.
   * @return the feature's default identifier.
   */
  public static String getDefaultId(EStructuralFeature eStructuralFeature)
  {
    return eStructuralFeature.getName();
  }

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
   * This is used to locate resources for translated values like enumeration literals.
   */
  protected ResourceLocator resourceLocator;

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
   * Whether the value of this property consists of multi-line text.
   * @since 2.2.0
   */
  protected boolean multiLine;

  /**
   * Whether the choices for this property should be sorted for display.
   * @since 2.2.0
   */
  protected boolean sortChoices;

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
   * If non-null, this object will be the owner of commands created to set the property's value. 
   */
  protected Object commandOwner;

  /**
   * This class uses a static image
   */
  protected class ItemDelegator extends AdapterFactoryItemDelegator
  {
    protected ResourceLocator resourceLocator;

    public ItemDelegator(AdapterFactory adapterFactory)
    {
      super(adapterFactory);
    }

    public ItemDelegator(AdapterFactory adapterFactory, ResourceLocator resourceLocator)
    {
      super(adapterFactory);
      this.resourceLocator = resourceLocator;
    }

    @Override
    public String getText(Object object)
    {
      if (feature instanceof EAttribute)
      {
        EDataType eDataType = ((EAttribute)feature).getEAttributeType();
        if (eDataType.isSerializable() && (eDataType != EcorePackage.Literals.EJAVA_OBJECT || !feature.isTransient()))
        {
          if (isMany(object))
          {
            if (object instanceof List<?>)
            {
              StringBuffer result = new StringBuffer();
              for (Iterator<?> i = ((List<?>)object).iterator(); i.hasNext(); )
              {
                Object value = i.next();
                result.append(convert(eDataType, value));
                if (i.hasNext())
                {
                  result.append(", ");
                }
              }
              return result.toString();
            }
          }
          if (eDataType.isInstance(object))
          {
            return convert(eDataType, object);
          }
        }
      }

      return super.getText(object);
    }

    protected String convert(EDataType eDataType, Object value)
    {
      if (resourceLocator != null)
      {
        if (eDataType instanceof EEnum)
        {
          try
          {
            return 
              resourceLocator.getString
                ("_UI_" + eDataType.getName() + "_" + ((Enumerator)value).getName() + "_literal");
          }
          catch (MissingResourceException exception)
          {
            // Ignore
          }
        }
        else if (value instanceof Boolean)
        {
          try
          {
            return 
              resourceLocator.getString
                (Boolean.TRUE.equals(value) ? "_UI_Boolean_true_literal" : "_UI_Boolean_false_literal");
          }
          catch (MissingResourceException exception)
          {
            // Ignore
          }
        } 
      }
      return crop(EcoreUtil.convertToString(eDataType, value));
    }

    // This is copied from ItemProviderAdapterFactory.
    //
    protected String crop(String text)
    {
      if (text != null)
      {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++)
        {
          if (Character.isISOControl(chars[i]))
          {
            return text.substring(0, i) + "...";
          }
        }
      }
      return text;
    }

    @Override
    public Object getImage(Object object)
    {
      return staticImage == null ? super.getImage(object) : staticImage;
    }
  }

  /**
   * This creates an instance that does not use a resource locator and determines the cell editor from the type of the
   * structural feature.  It assumed that the feature should be settable from this property.
   * 
   * <p>To reduce the number of constructors for this class, this one will soon be deprecated.  For new code, please
   * use {@link #ItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EStructuralFeature, boolean)
   * this} form, instead.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EStructuralFeature feature)
  {
    this(adapterFactory, null, displayName, description, feature, true, false, false, null, null, null);
  }

  /**
   * This creates an instance that uses a resource locator and determines the cell editor from the type of the
   * structural feature.  It assumed that the feature should be settable from this property.
   * 
   * <p>To reduce the number of constructors for this class, this one will soon be deprecated.  For new code, please
   * use {@link #ItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EStructuralFeature, boolean)
   * this} form, instead.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      ResourceLocator resourceLocator,
      String displayName,
      String description,
      EStructuralFeature feature)
  {
    this(adapterFactory, resourceLocator, displayName, description, feature, true, false, false, null, null, null);
  }

  /**
   * This creates an instance that does not use a resource locator and determines the cell editor from the type of the
   * structural feature. 
   * 
   * <p>To reduce the number of constructors for this class, this one may be deprecated in the future.  For new code, please
   * use {@link #ItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EStructuralFeature, boolean)
   * this} form, instead.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable)
  {
    this(adapterFactory, null, displayName, description, feature, isSettable, false, false, null, null, null);
  }

  /**
   * This creates an instance that uses a resource locator and determines the cell editor from the type of the
   * structural feature. 
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      ResourceLocator resourceLocator,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable)
  {
    this(adapterFactory, resourceLocator, displayName, description, feature, isSettable, false, false, null, null, null);
  }

  /**
   * This creates an instance that does not use a resource locator, specifies a static image, and determines the cell
   * editor from the type of the structural feature. 
   * 
   * <p>To reduce the number of constructors for this class, this one may be deprecated in the future.  For new code, please
   * use {@link #ItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EStructuralFeature, boolean, Object)
   * this} form, instead.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable,
      Object staticImage)
  {
    this(adapterFactory, null, displayName, description, feature, isSettable, false, false, staticImage, null, null);
  }

  /**
   * This creates an instance that uses a resource locator, specifies a static image, and determines the cell editor
   * from the type of the structural feature. 
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      ResourceLocator resourceLocator,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable,
      Object staticImage)
  {
    this(adapterFactory, resourceLocator, displayName, description, feature, isSettable, false, false, staticImage, null, null);
  }

  /**
   * This creates an instance that does not use a resource locator, specifies a category, and determines the cell
   * editor from the type of the structural feature. 
   * 
   * <p>To reduce the number of constructors for this class, this one will soon be deprecated.  For new code, please
   * use {@link #ItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EStructuralFeature, boolean, String, String[])
   * this} form, instead.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable,
      String category)
  {
    this(adapterFactory, null, displayName, description, feature, isSettable, false, false, null, category, null);
  }

  /**
   * This creates an instance that uses a resource locator, specifies a category, and determines the cell editor from
   * the type of the structural feature. 
   * 
   * <p>To reduce the number of constructors for this class, this one will soon be deprecated.  For new code, please
   * use {@link #ItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EStructuralFeature, boolean, String, String[])
   * this} form, instead.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      ResourceLocator resourceLocator,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable,
      String category)
  {
    this(adapterFactory, resourceLocator, displayName, description, feature, isSettable, false, false, null, category, null);
  }

  /**
   * This creates an instance that does not use a resource locator, specifies a static image and category, and
   * determines the cell editor from the type of the structural feature. 
   * 
   * <p>To reduce the number of constructors for this class, this one will soon be deprecated.  For new code, please
   * use {@link #ItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EStructuralFeature, boolean, boolean, boolean, Object, String, String[])
   * this} form, instead.
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
    this(adapterFactory, null, displayName, description, feature, isSettable, false, false, staticImage, category, null);
  }

  /**
   * This creates an instance that uses a resource locator, specifies a static image and category, and determines the
   * cell editor from the type of the structural feature. 
   * 
   * <p>To reduce the number of constructors for this class, this one will soon be deprecated.  For new code, please
   * use {@link #ItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EStructuralFeature, boolean, boolean, boolean, Object, String, String[])
   * this} form, instead.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      ResourceLocator resourceLocator,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable,
      Object staticImage,
      String category)
  {
    this(adapterFactory, resourceLocator, displayName, description, feature, isSettable, false, false, staticImage, category, null);
  }

  /**
   * This creates an instance that does not use a resource locator, specifies a category and filter flags, and
   * determines the cell editor from the type of the structural feature. 
   * 
   * <p>To reduce the number of constructors for this class, this one may be deprecated in the future.  For new code, please
   * use {@link #ItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EStructuralFeature, boolean, String, String[])
   * this} form, instead.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable,
      String category,
      String [] filterFlags)
  {
    this(adapterFactory, null, displayName, description, feature, isSettable, false, false, null, category, filterFlags);
  }

  /**
   * This creates an instance that uses a resource locator, specifies a category and filter flags, and determines the
   * cell editor from the type of the structural feature. 
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      ResourceLocator resourceLocator,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable,
      String category,
      String [] filterFlags)
  {
    this(adapterFactory, resourceLocator, displayName, description, feature, isSettable, false, false, null, category, filterFlags);
  }

  /**
   * This creates an instance that does not use a resource locator; specifies a static image, a category, and filter
   * flags; and determines the cell editor from the type of the structural feature. 
   * 
   * <p>To reduce the number of constructors for this class, this one may be deprecated in the future.  For new code, please
   * use {@link #ItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EStructuralFeature, boolean, boolean, boolean, Object, String, String[])
   * this} form, instead.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable,
      Object staticImage,
      String category,
      String [] filterFlags)
  {
    this(adapterFactory, null, displayName, description, feature, isSettable, false, false, staticImage, category, filterFlags);
  }

  /**
   * This creates an instance that uses a resource locator; specifies a static image, a category, and filter flags;
   * and determines the cell editor from the type of the structural feature. 
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      ResourceLocator resourceLocator,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable,
      Object staticImage,
      String category,
      String [] filterFlags)
  {
    this(adapterFactory, resourceLocator, displayName, description, feature, isSettable, false, false, staticImage, category, filterFlags);
    
  }

  /**
   * This creates an instance that uses a resource locator; indicates whether to be multi-line and to sort choices; specifies
   * a  static image, a category, and filter flags; and determines the cell editor from the type of the structural feature. 
   * @since 2.2.0
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      ResourceLocator resourceLocator,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable,
      boolean multiLine,
      boolean sortChoices,
      Object staticImage,
      String category,
      String [] filterFlags)
  {
    this.adapterFactory = adapterFactory;
    this.resourceLocator = resourceLocator;
    this.itemDelegator = new ItemDelegator(adapterFactory, resourceLocator);
    this.displayName = displayName;
    this.description = description;
    this.feature = feature;
    this.isSettable = isSettable;
    this.multiLine = multiLine;
    this.sortChoices = sortChoices;
    this.staticImage = staticImage;
    this.category = category;
    this.filterFlags = filterFlags;
  }

  /**
   * This creates an instance that does not use a resource locator and determines the cell editor from the parent
   * references.  It assumed that the feature should be settable from this property.
   * 
   * <p>To reduce the number of constructors for this class, this one will soon be deprecated.  For new code, please
   * use {@link #ItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EReference[], boolean)
   * this} form, instead.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EReference [] parentReferences)
  {
    this(adapterFactory, null, displayName, description, parentReferences, true, null, null);
  }

  /**
   * This creates an instance that uses a resource locator and determines the cell editor from the parent references. 
   * It assumed that the feature should be settable from this property.
   * 
   * <p>To reduce the number of constructors for this class, this one will soon be deprecated.  For new code, please
   * use {@link #ItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EReference[], boolean)
   * this} form, instead.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      ResourceLocator resourceLocator,
      String displayName,
      String description,
      EReference [] parentReferences)
  {
    this(adapterFactory, resourceLocator, displayName, description, parentReferences, true, null, null);
  }

  /**
   * This creates an instance that does not use a resource locator and determines the cell editor from the parent
   * references. 
   * 
   * <p>To reduce the number of constructors for this class, this one may be deprecated in the future.  For new code, please
   * use {@link #ItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EReference[], boolean)
   * this} form, instead.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EReference [] parentReferences,
      boolean isSettable)
  {
    this(adapterFactory, null, displayName, description, parentReferences, isSettable, null, null);
  }

  /**
   * This creates an instance that uses a resource locator and determines the cell editor from the parent references. 
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      ResourceLocator resourceLocator,
      String displayName,
      String description,
      EReference [] parentReferences,
      boolean isSettable)
  {
    this(adapterFactory, resourceLocator, displayName, description, parentReferences, isSettable, null, null);
  }

  /**
   * This creates an instance that does not use a resource locator, specifies a category, and determines the cell
   * editor from the parent references. 
   * 
   * <p>To reduce the number of constructors for this class, this one will soon be deprecated.  For new code, please
   * use {@link #ItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EReference[], boolean, String, String[])
   * this} form, instead.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EReference [] parentReferences,
      boolean isSettable,
      String category)
  {
    this(adapterFactory, null, displayName, description, parentReferences, isSettable, category, null);
  }

  /**
   * This creates an instance that uses a resource locator, specifies a category, and determines the cell editor from
   * the parent references. 
   * 
   * <p>To reduce the number of constructors for this class, this one will soon be deprecated.  For new code, please
   * use {@link #ItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EReference[], boolean, String, String[])
   * this} form, instead.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      ResourceLocator resourceLocator,
      String displayName,
      String description,
      EReference [] parentReferences,
      boolean isSettable,
      String category)
  {
    this(adapterFactory, resourceLocator, displayName, description, parentReferences, isSettable, category, null);
  }

  /**
   * This creates an instance that does not use a resource locator, specifies a category and filter flags, and
   * determines the cell editor from the parent references. 
   * 
   * <p>To reduce the number of constructors for this class, this one may be deprecated in the future.  For new code, please
   * use {@link #ItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EReference[], boolean, String, String[])
   * this} form, instead.
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      String displayName,
      String description,
      EReference [] parentReferences,
      boolean isSettable,
      String category,
      String [] filterFlags)
  {
    this(adapterFactory, null, displayName, description, parentReferences, isSettable, category, filterFlags);
  }

  /**
   * This creates an instance that uses a resource locator, specifies a category and filter flags, and determines the
   * cell editor from the parent references. 
   */
  public ItemPropertyDescriptor
     (AdapterFactory adapterFactory,
      ResourceLocator resourceLocator,
      String displayName,
      String description,
      EReference [] parentReferences,
      boolean isSettable,
      String category,
      String [] filterFlags)
  {
    this.adapterFactory = adapterFactory;
    this.resourceLocator = resourceLocator;
    this.itemDelegator = new ItemDelegator(adapterFactory, resourceLocator);
    this.displayName = displayName;
    this.description = description;
    this.parentReferences = parentReferences;
    this.isSettable = isSettable;
    this.category = category;
    this.filterFlags = filterFlags;
  }

  /**
   * This returns the group of properties into which this one should be placed.
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
   * This returns the {@link #getDefaultId(EStructuralFeature) default identifier} of the {@link #feature feature}
   * if it's present,
   * or dash-separated concatenation of the default identifier of each {@link #parentReferences parent reference}.
   * This key that must uniquely identify this descriptor 
   * among the other descriptors from the same {@link IItemPropertySource#getPropertyDescriptor(Object, Object) property source}.
   */
  public String getId(Object object) 
  {
    if (feature != null)
    {
      return getDefaultId(feature);
    }
    else if (parentReferences != null && parentReferences.length != 0)
    {
      StringBuffer result = new StringBuffer(getDefaultId(parentReferences[0]));
      for (int i = 1; i < parentReferences.length; ++i)
      {
        result.append('-');
        result.append(getDefaultId(parentReferences[i]));
      }
      return result.toString();
    }
    else
    {
      return displayName;
    }
  }

  public Object getHelpContextIds(Object object)
  {
    return null;
  }

  protected static final EcorePackage ecorePackage = EcorePackage.eINSTANCE;

  /**
   * This will be called to populate a list of choices.
   * The label provider will be used to determine the labels for the objects this returns.
   * This default implementation uses {@link #getReachableObjectsOfType getReachableObjectsOfType}.
   */
  protected Collection<?> getComboBoxObjects(Object object)
  {
    if (object instanceof EObject)
    {
      if (parentReferences != null)
      {
        Collection<Object> result = new UniqueEList<Object>();
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
          Collection<EObject> result = getReachableObjectsOfType((EObject)object, feature.getEType());
          if (!feature.isMany() && !result.contains(null))
          {
            result.add(null);
          }
          return result;
        }
        else if (feature.getEType() instanceof EEnum)
        {
          EEnum eEnum = (EEnum)feature.getEType();
          List<Enumerator> enumerators = new ArrayList<Enumerator>();
          for (EEnumLiteral eEnumLiteral :  eEnum.getELiterals())
          {
            enumerators.add(eEnumLiteral.getInstance());
          }
          return enumerators;
        }
        else 
        {
          EDataType eDataType = (EDataType)feature.getEType();
          List<String> enumeration = ExtendedMetaData.INSTANCE.getEnumerationFacet(eDataType);
          if (!enumeration.isEmpty())
          {
            List<Object> enumerators = new ArrayList<Object>();
            for (String enumerator : enumeration)
            {
              enumerators.add(EcoreUtil.createFromString(eDataType, enumerator));
            }
            return enumerators;
          }
          else
          {
            for (EDataType baseType = ExtendedMetaData.INSTANCE.getBaseType(eDataType);
                 baseType != null;
                 baseType = ExtendedMetaData.INSTANCE.getBaseType(baseType))
            {
              if (baseType instanceof EEnum)
              {
                EEnum eEnum = (EEnum)baseType;
                List<Enumerator> enumerators = new ArrayList<Enumerator>();
                enumerators.add(null);
                for (EEnumLiteral eEnumLiteral :  eEnum.getELiterals())
                {
                  enumerators.add(eEnumLiteral.getInstance());
                }
                return enumerators;
              }
            }
          }
        }
      }
    }

    return null;
  }

  /**
   * This yields all reachable references from object with a meta object which indicates that it is a subtype of type.
   */
  static public Collection<EObject> getReachableObjectsOfType(EObject object, EClassifier type)
  {
    LinkedList<EObject> itemQueue = new LinkedList<EObject>();
    Collection<EObject> visited  = new HashSet<EObject>();
    Collection<EObject> result = new ArrayList<EObject>();
    Resource resource = object.eResource();
    if (resource != null)
    {
      ResourceSet resourceSet = resource.getResourceSet();
      if (resourceSet != null)
      {
        for (TreeIterator<?> i = resourceSet.getAllContents(); i.hasNext(); )
        {
          Object child = i.next();
          if (child instanceof EObject)
          {
            collectReachableObjectsOfType(visited, itemQueue, result, (EObject)child, type);
            i.prune();
          }
        }
      }
      else
      {
        for (EObject eObject : resource.getContents())
        {
          collectReachableObjectsOfType(visited, itemQueue, result, eObject, type);
        }
      }
    }
    else
    {
      collectReachableObjectsOfType(visited, itemQueue, result, EcoreUtil.getRootContainer(object), type);
    }

    while (!itemQueue.isEmpty()) 
    {
      EObject nextItem = itemQueue.removeFirst();
      collectReachableObjectsOfType(visited, itemQueue, result, nextItem, type);
    } 

    return result;
  }
 
  /**
   * This will visit all reachable references from object except those in visited;
   * it updates visited and adds to result any object with a meta object that indicates that it is a subtype of type.
   */
  static public void collectReachableObjectsOfType(Collection<EObject> visited, Collection<EObject> result, EObject object, EClassifier type)
  {
    LinkedList<EObject> itemQueue = new LinkedList<EObject>();
    collectReachableObjectsOfType(visited, itemQueue, result, object, type);
    while (!itemQueue.isEmpty()) 
    {
      EObject nextItem = itemQueue.removeFirst();
      collectReachableObjectsOfType(visited, itemQueue, result, nextItem, type);
    } 
  }

  /**
   * This will visit all reachable references from object except those in visited and add them to the queue.
   * The queue is processed outside this recursive traversal to avoid stack overflows.
   * It updates visited and adds to result any object with a meta object that indicates that it is a subtype of type.
   */
  static private void collectReachableObjectsOfType
    (Collection<EObject> visited,  LinkedList<EObject> itemQueue, Collection<EObject> result,  EObject object,  EClassifier type)
  {
    if (visited.add(object))
    {
      if (type.isInstance(object))
      {
        result.add(object);
      }

      // Don't traverse the structure of the EcorePackage's EObject EClass instance.
      // This avoids pulling in all the EcorePackage's meta data simply because EObject was used.
      //
      if (object != EcorePackage.Literals.EOBJECT)
      {
        EClass eClass = object.eClass();
        for (EStructuralFeature eStructuralFeature : eClass.getEAllStructuralFeatures())
        {
          if (!eStructuralFeature.isDerived())
          {
            if (eStructuralFeature instanceof EReference)
            {
              EReference eReference = (EReference)eStructuralFeature;
              if (eReference.isMany())
              {
                @SuppressWarnings("unchecked")
                List<EObject> list = ((List<EObject>)object.eGet(eReference));
                itemQueue.addAll(list);
              }
              else
              {
                EObject eObject = (EObject)object.eGet(eReference);
  
                // Explicitly exclude walking up the container reference for EClassifiers of the EcorePackage instance
                // except for EClass instances (other than EObject which was excluded above already).
                // This avoids pulling in all the EcorePackage's meta data simply because an EDataType was used.
                //
                if (eObject != null && 
                      (eObject != EcorePackage.eINSTANCE ||
                         eStructuralFeature != EcorePackage.Literals.ECLASSIFIER__EPACKAGE ||
                         object instanceof EClass))
                {
                  itemQueue.addLast(eObject);
                }
              }
            }
            else if (FeatureMapUtil.isFeatureMap(eStructuralFeature))
            {
              for (FeatureMap.Entry entry : (FeatureMap)object.eGet(eStructuralFeature))
              {
                if (entry.getEStructuralFeature() instanceof EReference && entry.getValue() != null)
                {
                  itemQueue.addLast((EObject)entry.getValue());
                }
              }
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

    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object thisObject)
    {
      // This guards the switch.
      //
      List<IItemPropertyDescriptor> list = itemDelegator.getPropertyDescriptors(nestedPropertySource);
      if (list != null)
      {
        List<IItemPropertyDescriptor> result = new ArrayList<IItemPropertyDescriptor>(list.size());
        for (IItemPropertyDescriptor itemPropertyDescriptor : list)
        {
          result.add(createPropertyDescriptorDecorator(nestedPropertySource, itemPropertyDescriptor));
        }
        return result;
      }
  
      return Collections.emptyList();
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
          return Boolean.FALSE;
        }
        case EcorePackage.EBYTE:
        case EcorePackage.EBYTE_OBJECT:
        {
          return (byte)0;
        }
        case EcorePackage.ECHAR:
        case EcorePackage.ECHARACTER_OBJECT:
        {
          return ' ';
        }
        case EcorePackage.EDOUBLE:
        case EcorePackage.EDOUBLE_OBJECT:
        {
          return 0.0;
        }
        case EcorePackage.EFLOAT:
        case EcorePackage.EFLOAT_OBJECT:
        {
          return 0.0F;
        }
        case EcorePackage.EINT:
        case EcorePackage.EINTEGER_OBJECT:
        {
          return 0;
        }
        case EcorePackage.ELONG:
        case EcorePackage.ELONG_OBJECT:
        {
          return 0L;
        }
        case EcorePackage.ESHORT:
        case EcorePackage.ESHORT_OBJECT:
        {
          return (short)0;
        }
        case EcorePackage.ESTRING:
        {
          return "";
        }
      }
    }
    else if (eType instanceof EEnum)
    {
      return (((EEnum)eType).getELiterals().get(0)).getInstance();
    }
    else if (eType instanceof EDataType)
    {
      EDataType eDataType = (EDataType)eType;
      List<String> enumeration = ExtendedMetaData.INSTANCE.getEnumerationFacet(eDataType);
      if (!enumeration.isEmpty())
      {
        return EcoreUtil.createFromString(eDataType, enumeration.get(0));
      }
    }

    return null;
  }

  /**
   * This is called by {@link #getPropertyValue getPropertyValue} to reflectively obtain the value of a feature
   * from an object.  It can be overridden by a subclass to provide additional processing of the value.
   */
  protected Object getValue(EObject object, EStructuralFeature feature)
  {
    try
    {
      return object.eGet(feature);
    }
    catch (Throwable exception)
    {
      return null;
    }
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
      Object result =  getValue(eObject, attribute);

      // We used to use getDefaultValue() when null, but that behaviour isn't correct: the value is already set to
      // its default initially, and we should always show the actual state of the object (bug 102557).
      // 
      if (result == null)
      {
        //return getDefaultValue(attribute.getEType());
        return null;
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
        Object result = getValue(eObject, parentReferences[i]);
        if (result != null)
        {
          return createPropertyValueWrapper(object, result);
        }
      }
      return "";
    }
    else 
    {
      return createPropertyValueWrapper(object, getValue(eObject, feature));
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
      try
      {
        return 
          feature instanceof EAttribute ? 
            feature.isMany() ?
              !((List<?>)eObject.eGet(feature)).isEmpty() : 
              eObject.eIsSet(feature) : 
            eObject.eGet(feature) != null;
      }
      catch (Throwable exception)
      {
        return false;
      }
    }
  }

  /**
   * This determines whether this descriptor's property for the object supports set (and reset).
   */
  public boolean canSetProperty(Object object)
  {
    if (isSettable)
    {
      EditingDomain editingDomain = getEditingDomain(object);
      if (editingDomain != null)
      {
        Resource resource = object instanceof EObject ?
          ((EObject)object).eResource() :
            object instanceof Resource ?
            (Resource)object :
            null;
  
        return resource == null || !editingDomain.isReadOnly(resource);
      }
      else
      {
        return true;
      }
    }
    else
    {
      return false;
    }
  }

  /**
   * Sets the object to use as the owner of commands created to set the property's value.
   */
  public void setCommandOwner(Object commandOwner)
  {
    this.commandOwner = commandOwner;
  }

  /**
   * Returns the override command owner set via {@link #setCommandOwner setCommandOwner}.
   */
  public Object getCommandOwner()
  {
    return commandOwner;
  }

  /**
   * Returns either the override command owner set via {@link #setCommandOwner setCommandOwner} or, if that is null, the
   * fall-back object provided.
   */
  protected Object getCommandOwner(Object fallback)
  {
    return commandOwner != null ? commandOwner : fallback;
  }

  /**
   * This does the delegated job of resetting property value back to it's default value.
   */
  public void resetPropertyValue(Object object)
  {
    EObject eObject = (EObject)object;
    EditingDomain editingDomain = getEditingDomain(object);

    if (parentReferences != null)
    {
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
            editingDomain.getCommandStack().execute
              (SetCommand.create(editingDomain, getCommandOwner(eObject), parentReference, SetCommand.UNSET_VALUE));
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
      else
      {
        editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, getCommandOwner(eObject), feature, SetCommand.UNSET_VALUE));
      }
    }
  }

  public EditingDomain getEditingDomain(Object object)
  {
    EObject eObject = (EObject)object;
    EditingDomain result = AdapterFactoryEditingDomain.getEditingDomainFor(eObject);
    if (result == null)
    {
      if (adapterFactory instanceof IEditingDomainProvider)
      {
        result = ((IEditingDomainProvider)adapterFactory).getEditingDomain();
      }

      if (result == null && adapterFactory instanceof ComposeableAdapterFactory)
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
              editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, getCommandOwner(eObject), parentReference, value));
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
              removeCommand = SetCommand.create(editingDomain, getCommandOwner(eObject), parentReference, null);
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
              compoundCommand.append(SetCommand.create(editingDomain, getCommandOwner(eObject), parentReference, value));
              editingDomain.getCommandStack().execute(compoundCommand);
            }
            else
            {
              editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, getCommandOwner(eObject), parentReference, value));
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
        editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, getCommandOwner(eObject), feature, value));
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

  /**
   * Returns whether this property represents multiple values.  This is true only if we're using a {@link #feature
   * structural feature} to provide the values for this property, and if that feature is multi-valued.
   */
  public boolean isMany(Object object)
  {
    return parentReferences == null && feature != null && feature.isMany();
  }

  public Collection<?> getChoiceOfValues(Object object)
  {
    return getComboBoxObjects(object);
  }
  
  public boolean isMultiLine(Object object)
  {
    return multiLine;
  }

  public boolean isSortChoices(Object object)
  {
    return sortChoices;
  }
}
