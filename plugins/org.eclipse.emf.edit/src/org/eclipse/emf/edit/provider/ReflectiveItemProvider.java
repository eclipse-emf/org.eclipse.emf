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
 * $Id: ReflectiveItemProvider.java,v 1.3 2004/05/06 18:25:35 emerks Exp $
 */
package org.eclipse.emf.edit.provider;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;


/**
 * This adapter implementation provides reflective support 
 * that emulates the behaviour of a default generated item provider.
 */
public class ReflectiveItemProvider
  extends ItemProviderAdapter
  implements
    IEditingDomainItemProvider,
    IStructuredItemContentProvider,
    ITreeItemContentProvider,
    IItemLabelProvider,
    IItemPropertySource
{
  /**
   */
  public ReflectiveItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   */
  public List getPropertyDescriptors(Object object) 
  {
    // if (itemPropertyDescriptors == null)
    {
      itemPropertyDescriptors = new ArrayList();

      for (Iterator i = ((EObject)object).eClass().getEAllStructuralFeatures().iterator(); i.hasNext(); )
      {
        EStructuralFeature eFeature = (EStructuralFeature)i.next();
        if (!(eFeature instanceof EReference) || !((EReference)eFeature).isContainment())
        {
          itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
              (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
               getFeatureText(eFeature),
               getResourceLocator().getString
                 ("_UI_Property_description", new Object [] { getFeatureText(eFeature), eFeature.getEType().getName() }),
               eFeature,
               !eFeature.isMany(),
               ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
        }
      }
    }
    return itemPropertyDescriptors;
  }

  protected List allRoots;
  protected List allEClasses;

  protected List getAllEClasses(EClass eClass)
  {
    if (allRoots == null)
    {
      allRoots = new ArrayList();
      allEClasses = new ArrayList();
    }

    Set roots = new HashSet();
    EObject root = EcoreUtil.getRootContainer(eClass);
    for (;;)
    {
      if (!allRoots.contains(root))
      {
        allRoots.add(root);
        for (Iterator i = root.eAllContents(); i.hasNext(); )
        {
          EObject eObject = (EObject)i.next();
          if (eObject instanceof EClass)
          {
            allEClasses.add(eObject);
          }
          for (Iterator j = eObject.eCrossReferences().iterator(); j.hasNext(); )
          {
            EObject crossReference = (EObject)j.next();
            EObject otherRoot = EcoreUtil.getRootContainer(crossReference);
            if (!allRoots.contains(otherRoot))
            {
              roots.add(otherRoot);
            }
          }
        }
      }

      if (roots.isEmpty())
      {
        break;
      }
      else
      {
        Iterator i = roots.iterator();
        root = (EObject)i.next();
        i.remove();
      }
    }

    return allEClasses;
  }

  protected List getAllConcreteSubclasses(EClass eClass)
  {
    List result = new ArrayList();
    if (eClass == EcorePackage.eINSTANCE.getEObject())
    {
      for (Iterator i = getAllEClasses(eClass).iterator(); i.hasNext(); )
      {
        EClass otherEClass = (EClass)i.next();
        if (!otherEClass.isAbstract())
        {
          result.add(otherEClass);
        }
      }
    }
    else
    {
      for (Iterator i = getAllEClasses(eClass).iterator(); i.hasNext(); )
      {
        EClass otherEClass = (EClass)i.next();
        if (!otherEClass.isAbstract() && eClass.isSuperTypeOf(otherEClass))
        {
          result.add(otherEClass);
        }
      }
    }
    return  result;
  }

  /**
   */
  protected Collection getChildrenFeatures(Object object)
  {
    // if (childrenFeatures == null)
    {
      childrenFeatures = new ArrayList();
      EObject eObject = (EObject)object;
      EClass eClass = eObject.eClass();
      if (ExtendedMetaData.INSTANCE.getContentKind(eClass) != ExtendedMetaData.MIXED_CONTENT)
      {
        for (Iterator i = eClass.getEAllReferences().iterator(); i.hasNext(); )
        {
          EReference eReference = (EReference)i.next();
          if (eReference.isContainment() && ExtendedMetaData.INSTANCE.getGroup(eReference) == null)
          {
            childrenFeatures.add(eReference);
          }
        }
      }
      for (Iterator i = eClass.getEAllAttributes().iterator(); i.hasNext(); )
      {
        EAttribute eAttribute = (EAttribute)i.next();
        if (ExtendedMetaData.INSTANCE.getGroup(eAttribute) == null && 
              eAttribute.getEType().getInstanceClass() == FeatureMap.Entry.class &&
              !eAttribute.isDerived())
        {
          childrenFeatures.add(eAttribute);
        }
      }
    }
    return childrenFeatures;
  }

  /**
   */
  public Object getImage(Object object)
  {
    EObject eObject = (EObject)object;
    EClass eClass = eObject.eClass();
    try
    {
      return new URL(getResourceLocator().getImage("full/obj16/Item").toString() + "#" + eClass.getName());
    }
    catch (MalformedURLException exception)
    {
      return getResourceLocator().getImage("full/obj16/Item");
    }
  }

  /**
   */
  public String getText(Object object)
  {
    EObject eObject = (EObject)object;
    EClass eClass = eObject.eClass();
    EStructuralFeature feature = getLabelFeature(eClass);
    Object label = feature == null ? feature : eObject.eGet(feature);
    return label == null ? eClass.getName() : eClass.getName() + " " + label.toString();
  }

  protected EStructuralFeature getLabelFeature(EClass eClass)
  {
    EAttribute result = null;
    for (Iterator i = eClass.getEAllAttributes().iterator(); i.hasNext(); )
    {
      EAttribute eAttribute = (EAttribute)i.next();
      if (!eAttribute.isMany() && eAttribute.getEType().getInstanceClass() != FeatureMap.Entry.class)
      {
        if ("name".equalsIgnoreCase(eAttribute.getName()))
        {
          result = eAttribute;
          break;
        }
        else if (result == null)
        {
          result = eAttribute;
        }
        else if (eAttribute.getEAttributeType().getInstanceClass() == String.class && 
                 result.getEAttributeType().getInstanceClass() != String.class)
        {
          result = eAttribute;
        }
      }
    }
    return result;
  }

  /**
   */
  protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object)    
  {
    EObject eObject = (EObject)object;
    EClass eClass = eObject.eClass();

    // This ensure that this package itself is traversed even if the reference type is EObject...
    //
    getAllEClasses(eClass);

    for (Iterator i = eClass.getEAllReferences().iterator(); i.hasNext(); )
    {
      EReference eReference = (EReference)i.next();
      if (eReference.isContainment())
      {
        EClass referenceType = eReference.getEReferenceType();

        for (Iterator j = getAllConcreteSubclasses(referenceType).iterator(); j.hasNext(); )
        {
          EClass concreteType = (EClass)j.next();
          newChildDescriptors.add
            (createChildParameter
              (eReference,
               EcoreUtil.create(concreteType)));
        }
      }
    }
  }

  /**
   */
  public Object getCreateChildImage(Object owner, Object feature, Object child, Collection selection)
  {
    if (feature instanceof EReference && child instanceof EObject)
    {
      EReference reference = (EReference)feature;
      EClass parentClass = reference.getEContainingClass();
      EClass childClass = ((EObject)child).eClass();
      try
      {
        return 
          new URL
            (getResourceLocator().getImage("full/ctool16/CreateChild").toString() + 
               "#" + parentClass.getName() + "/" + childClass.getName());
      }
      catch (MalformedURLException exception)
      {
      }
    }

    return getResourceLocator().getImage("full/ctool16/CreateChild");
  }

  /**
   */
  protected String getTypeText(Object object)
  {
    return object instanceof EObject ?
      ((EObject)object).eClass().getName() :
      getString("_UI_Unknown_type");
  }

  /**
   */
  protected String getFeatureText(Object feature)
  {
    return feature instanceof EStructuralFeature ?
      ((EStructuralFeature)feature).getName() :
      getResourceLocator().getString("_UI_Unknown_feature");
  }

  /**
   */
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    EObject object = (EObject)notification.getNotifier();
    EClass eClass = (EClass)object.eClass();
    EStructuralFeature feature = (EStructuralFeature)notification.getFeature();

    boolean label = feature == getLabelFeature(eClass);
    boolean content =
      (ExtendedMetaData.INSTANCE.getContentKind(eClass) == ExtendedMetaData.MIXED_CONTENT &&
       feature.getEType().getInstanceClass() == FeatureMap.Entry.class &&
       ExtendedMetaData.INSTANCE.getGroup(feature) == null) ||
      (feature instanceof EReference &&
       ((EReference)feature).isContainment() &&
       ExtendedMetaData.INSTANCE.getGroup(feature) == null);

    if (content || label)
    {
      fireNotifyChanged(new ViewerNotification(notification, object, content, label));
    }
  }
}
