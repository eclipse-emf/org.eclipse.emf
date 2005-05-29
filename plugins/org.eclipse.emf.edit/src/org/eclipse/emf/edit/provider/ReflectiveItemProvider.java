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
 * $Id: ReflectiveItemProvider.java,v 1.13 2005/05/29 11:44:35 emerks Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;


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
               eFeature.isChangeable(),
               ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
        }
      }
    }
    return itemPropertyDescriptors;
  }

  protected List allRoots;
  protected List allEPackages;
  protected List allEClasses;

  protected void gatherAllMetaData(EObject eObject)
  {
    EObject root = EcoreUtil.getRootContainer(eObject);
    ExtendedMetaData extendedMetaData =
        root.eResource() == null || root.eResource().getResourceSet() == null ?
          ExtendedMetaData.INSTANCE :
          new BasicExtendedMetaData(root.eResource().getResourceSet().getPackageRegistry());
    EStructuralFeature xmlnsPrefixMapFeature = extendedMetaData.getXMLNSPrefixMapFeature(root.eClass());
    if (xmlnsPrefixMapFeature != null)
    {
      for (Iterator i = ((List)root.eGet(xmlnsPrefixMapFeature)).iterator(); i.hasNext(); )
      {
        Map.Entry entry = (Map.Entry)i.next();
        EPackage ePackage = extendedMetaData.getPackage((String)entry.getValue());
        if (ePackage != null)
        {
          gatherMetaData((EModelElement)EcoreUtil.getRootContainer(ePackage));
        }
      }
    }
    
    gatherMetaData(eObject.eClass());
  }
  
  protected List getAllEClasses(EClass eClass)
  {
    gatherMetaData(eClass);
    return allEClasses;
  }
  
  protected List getAllEPackages(EClass eClass)
  {
    gatherMetaData(eClass);
    return allEPackages;
  }
    
  protected void gatherMetaData(EModelElement eModelElement)
  {
    if (allRoots == null)
    {
      allRoots = new ArrayList();
      allEClasses = new ArrayList();
      allEPackages = new ArrayList();
    }
    
    Set roots = new HashSet();
    EObject root = EcoreUtil.getRootContainer(eModelElement);
    for (;;)
    {
      if (!allRoots.contains(root))
      {
        allRoots.add(root);
        ExtendedMetaData extendedMetaData = 
            root.eResource() == null || root.eResource().getResourceSet() == null ?
              ExtendedMetaData.INSTANCE :
              new BasicExtendedMetaData(root.eResource().getResourceSet().getPackageRegistry());
        if (root instanceof EPackage)
        {
          allEPackages.add(root);
        }
        for (Iterator i = root.eAllContents(); i.hasNext(); )
        {
          EObject eObject = (EObject)i.next();
          
          if (eObject instanceof EClassifier)
          {
            extendedMetaData.getName((EClassifier)eObject);
            if (eObject instanceof EClass)
            {
              allEClasses.add(eObject);
            }
          }
          else if (eObject instanceof EStructuralFeature)
          {
            extendedMetaData.getName((EStructuralFeature)eObject);
          }
          else if (eObject instanceof EPackage)
          {
            allEPackages.add(eObject);
          }
          
          for (Iterator j = eObject.eClass().getEAllReferences().iterator(); j.hasNext(); )
          {
            EReference eReference = (EReference)j.next();
            if (!eReference.isDerived() && !eReference.isContainer() && !eReference.isContainment())
            {
              if (eReference.isMany())
              {
                for (Iterator k = ((List)eObject.eGet(eReference)).iterator(); k.hasNext(); )
                {
                  EObject crossReference = (EObject)k.next();
                  if (crossReference != null)
                  {
                    EObject otherRoot = EcoreUtil.getRootContainer(crossReference);
                    if (!allRoots.contains(otherRoot))
                    {
                      roots.add(otherRoot);
                    }
                  } 
                }
              }
              else
              {
                EObject crossReference = (EObject)eObject.eGet(eReference);
                if (crossReference != null)
                {
                  EObject otherRoot = EcoreUtil.getRootContainer(crossReference);
                  if (!allRoots.contains(otherRoot))
                  {
                    roots.add(otherRoot);
                  }
                }
              }
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
  }

  protected List getAllConcreteSubclasses(EClass eClass)
  {
    List result = new ArrayList();
    if (eClass == EcorePackage.eINSTANCE.getEObject())
    {
      for (Iterator i = getAllEClasses(eClass).iterator(); i.hasNext(); )
      {
        EClass otherEClass = (EClass)i.next();
        if (!otherEClass.isAbstract() && !ExtendedMetaData.INSTANCE.isAnonymous(otherEClass))
        {
          result.add(otherEClass);
        }
      }
    }
    else if (ExtendedMetaData.INSTANCE.isAnonymous(eClass))
    {
      result.add(eClass);
    }
    else
    {
      for (Iterator i = getAllEClasses(eClass).iterator(); i.hasNext(); )
      {
        EClass otherEClass = (EClass)i.next();
        if (!otherEClass.isAbstract() && eClass.isSuperTypeOf(otherEClass) && !ExtendedMetaData.INSTANCE.isAnonymous(otherEClass))
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
    return URI.createURI(getResourceLocator().getImage("full/obj16/Item").toString() + "#" + eClass.getName());
  }

  /**
   */
  public String getText(Object object)
  {
    EObject eObject = (EObject)object;
    EClass eClass = eObject.eClass();
    String label = format(capName(eClass.getName()), ' ');

    EStructuralFeature feature = getLabelFeature(eClass);
    if (feature != null)
    {
      Object value = eObject.eGet(feature);
      if (value != null)
      {
        return label + " " + value.toString();
      }
    }
    return label;
  }

  /**
   */  
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
  protected String capName(String name)
  {
    return name.length() == 0 ? name : name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  /**
   */
  public String format(String name, char separator)
  {
    StringBuffer result = new StringBuffer();

    for (Iterator i = parseName(name, '_').iterator(); i.hasNext(); )
    {
      String component = (String)i.next();
      result.append(component);
      if (i.hasNext() && component.length() > 1)
      {
        result.append(separator);
      }
    }
    return result.toString();
  }

  /**
   */
  protected List parseName(String sourceName, char sourceSeparator)
  {
    List result = new ArrayList();
    StringBuffer currentWord = new StringBuffer();

    int length = sourceName.length();
    boolean lastIsLower = false;

    for (int index = 0; index < length; index++)
    {
      char curChar = sourceName.charAt(index);
      if (Character.isUpperCase(curChar) || (!lastIsLower && Character.isDigit(curChar)) || curChar == sourceSeparator)
      {
        if (lastIsLower || curChar == sourceSeparator)
        {
          result.add(currentWord.toString());
          currentWord = new StringBuffer();
        }
        lastIsLower = false;
      }
      else
      {
        if (!lastIsLower)
        {
          int currentWordLength = currentWord.length();
          if (currentWordLength > 1)
          {
            char lastChar = currentWord.charAt(--currentWordLength);
            currentWord.setLength(currentWordLength);
            result.add(currentWord.toString());
            currentWord = new StringBuffer();
            currentWord.append(lastChar);
          }
        }
        lastIsLower = true;
      }
      if (curChar != sourceSeparator)
      {
        currentWord.append(curChar);
      }
    }

    result.add(currentWord.toString());
    return result;
  }

  /**
   */  
  protected List getAllDelegatedFeatures(EStructuralFeature feature)
  {
    if (!FeatureMapUtil.isFeatureMap(feature)) return Collections.EMPTY_LIST;

    EClass eClass = feature.getEContainingClass();
    List delegated = new ArrayList();

    if (ExtendedMetaData.INSTANCE.getMixedFeature(eClass) == feature)
    {
       delegated.add(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Comment());
       delegated.add(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text());

       if (ExtendedMetaData.INSTANCE.getDocumentRoot(eClass.getEPackage()) != eClass)
       {
         delegated.add(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_CDATA());
       }

       for (Iterator i = eClass.getEAllStructuralFeatures().iterator(); i.hasNext(); )
       {
         EStructuralFeature otherFeature = (EStructuralFeature)i.next();
         if (otherFeature != feature && otherFeature.isDerived() && otherFeature.isChangeable() &&
             ExtendedMetaData.INSTANCE.getGroup(otherFeature) == null)
         {
           delegated.add(otherFeature);
         }
       }
    }
    else
    {
      switch (ExtendedMetaData.INSTANCE.getFeatureKind(feature))
      {
        case ExtendedMetaData.GROUP_FEATURE:
        {
          Set allDelegated = new HashSet();
          Set qNames = new HashSet();
          for (Iterator i = eClass.getEStructuralFeatures().iterator(); i.hasNext(); )
          {
            EStructuralFeature otherFeature = (EStructuralFeature)i.next();
            if (otherFeature != feature && otherFeature.isDerived() && 
                ExtendedMetaData.INSTANCE.getGroup(otherFeature) == feature)
            { 
              if (otherFeature.isChangeable())
              {
                delegated.add(otherFeature);
                qNames.add
                  (ExtendedMetaData.INSTANCE.getNamespace(otherFeature) + "#" + 
                     ExtendedMetaData.INSTANCE.getName(otherFeature));
              }
              allDelegated.add(otherFeature);
            }
          }
          
          for (Iterator i = getAllEPackages(eClass).iterator(); i.hasNext(); )
          {
            EPackage ePackage = (EPackage)i.next();
            EClass documentRootEClass = ExtendedMetaData.INSTANCE.getDocumentRoot(ePackage);
            if (documentRootEClass != null)
            {
              for (Iterator j = documentRootEClass.getEAllStructuralFeatures().iterator(); j.hasNext(); )
              {
                EStructuralFeature otherFeature = (EStructuralFeature)j.next();
                if (otherFeature != feature && 
                      otherFeature.isChangeable() &&
                      otherFeature.isDerived() &&
                      allDelegated.contains(ExtendedMetaData.INSTANCE.getAffiliation(eClass, otherFeature))  &&
                      qNames.add
                        (ExtendedMetaData.INSTANCE.getNamespace(otherFeature) + "#" + 
                           ExtendedMetaData.INSTANCE.getName(otherFeature)))
                {
                  delegated.add(otherFeature);
                }
              }
            }
          }
            
          break;
        }
        case ExtendedMetaData.ATTRIBUTE_WILDCARD_FEATURE:
        case ExtendedMetaData.ELEMENT_WILDCARD_FEATURE:
        {
          for (Iterator i = getAllEPackages(eClass).iterator(); i.hasNext(); )
          {
            EPackage ePackage = (EPackage)i.next();
            EClass documentRootEClass = ExtendedMetaData.INSTANCE.getDocumentRoot(ePackage);
            if (documentRootEClass != null)
            {
              for (Iterator j = documentRootEClass.getEAllStructuralFeatures().iterator(); j.hasNext(); )
              {
                EStructuralFeature otherFeature = (EStructuralFeature)j.next();
                if (otherFeature != feature && 
                      otherFeature.isDerived() && 
                      otherFeature.isChangeable() &&
                      ExtendedMetaData.INSTANCE.getAffiliation(eClass, otherFeature) == feature)
                {
                  delegated.add(otherFeature);
                }
              }
            }
          }
          break;
        }
      }
    }

    List result = new ArrayList();
    for (Iterator iter = delegated.iterator(); iter.hasNext(); )
    {
      EStructuralFeature delegatedFeature = (EStructuralFeature)iter.next();
      if (FeatureMapUtil.isFeatureMap(delegatedFeature))
      {
        result.addAll(getAllDelegatedFeatures(delegatedFeature));
      }
      else
      {
        result.add(delegatedFeature);
      }
    }
    return result;
  }

  /**
   */
  protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object)    
  {
    // This ensure that this package itself is traversed even if the reference type is EObject...
    //
    gatherAllMetaData((EObject)object);

    for (Iterator i = getChildrenFeatures(object).iterator(); i.hasNext(); )
    {
      EStructuralFeature feature = (EStructuralFeature)i.next();

      if (FeatureMapUtil.isFeatureMap(feature))
      {
        for (Iterator j = getAllDelegatedFeatures(feature).iterator(); j.hasNext(); )
        {
          EStructuralFeature delegatedFeature = (EStructuralFeature)j.next();

          if (delegatedFeature instanceof EAttribute)
          {
            EDataType type = ((EAttribute)delegatedFeature).getEAttributeType();
            Object value = delegatedFeature.getDefaultValue();

            if (value == null)
            {
              Class instanceClass = type.getInstanceClass();

              if (instanceClass == String.class)
              {
                value = "";
              }
              else if (instanceClass == Boolean.class)
              {
                value = Boolean.FALSE;
              }
              else if (instanceClass == Character.class)
              {
                value = new Character('\u0000');
              }
              else
              {
                String literal = instanceClass != null && Number.class.isAssignableFrom(instanceClass) ? "0" : ""; 
                try
                {
                  value = EcoreUtil.createFromString(type, literal);
                }
                catch (Exception e) {}
              }
            }

            if (value != null)
            {
              newChildDescriptors.add(createChildParameter(feature, FeatureMapUtil.createEntry(delegatedFeature, value)));
            }
          }
          else if (delegatedFeature instanceof EReference)
          {
            EReference delegatedReference = (EReference)delegatedFeature;

            for (Iterator k = getAllConcreteSubclasses((EClass)delegatedFeature.getEType()).iterator(); k.hasNext(); )
            {
              FeatureMap.Entry entry = FeatureMapUtil.createEntry(delegatedFeature, EcoreUtil.create((EClass)k.next()));
              newChildDescriptors.add(createChildParameter(feature, entry));
            }
          }
        }
      }
      else if (feature instanceof EReference && feature.isChangeable())
      {
        for (Iterator j = getAllConcreteSubclasses((EClass)feature.getEType()).iterator(); j.hasNext(); )
        {
          newChildDescriptors.add(createChildParameter(feature, EcoreUtil.create((EClass)j.next())));
        }        
      }
    }
  }

  /**
   */
  public Object getCreateChildImage(Object owner, Object feature, Object child, Collection selection)
  {
    if (feature instanceof EStructuralFeature && FeatureMapUtil.isFeatureMap((EStructuralFeature)feature))
    {
      FeatureMap.Entry entry = (FeatureMap.Entry)child;
      feature = entry.getEStructuralFeature();
      child = entry.getValue();        
    }

    if (feature instanceof EReference && child instanceof EObject)
    {
      EReference reference = (EReference)feature;
      EClass parentClass = reference.getEContainingClass();
      EClass childClass = ((EObject)child).eClass();
      return  
        URI.createURI
          (getResourceLocator().getImage("full/ctool16/CreateChild").toString() + 
             "#" + parentClass.getName() + "/" + childClass.getName());
    }

    return getResourceLocator().getImage("full/ctool16/CreateChild");
  }

  /**
   */
  protected String getTypeText(Object object)
  {
    String text = object instanceof EObject ? ((EObject)object).eClass().getName() : getString("_UI_Unknown_type");
    return format(capName(text), ' ');
  }

  /**
   */
  protected String getTypeText(EAttribute attribute)
  {
    return format(capName(attribute.getEAttributeType().getName()), ' ');
  }

  /**
   */
  protected String getFeatureText(Object feature)
  {
    String text = feature instanceof EStructuralFeature ?
      ((EStructuralFeature)feature).getName() : getResourceLocator().getString("_UI_Unknown_feature");
    return format(capName(text), ' ');
  }

  /**
   */
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    EObject object = (EObject)notification.getNotifier();
    EClass eClass = object.eClass();
    EStructuralFeature feature = (EStructuralFeature)notification.getFeature();

    // Is this a containment reference child?
    //
    boolean child = 
      ExtendedMetaData.INSTANCE.getContentKind(eClass) != ExtendedMetaData.MIXED_CONTENT &&
      feature instanceof EReference && ((EReference)feature).isContainment() &&
      ExtendedMetaData.INSTANCE.getGroup(feature) == null;

    // Or a feature map child?
    //
    child |=
      feature instanceof EAttribute &&
      ExtendedMetaData.INSTANCE.getGroup(feature) == null &&
      feature.getEType().getInstanceClass() == FeatureMap.Entry.class &&
      !feature.isDerived();

    // Is this the label feature?
    //
    boolean label = feature == getLabelFeature(eClass);

    fireNotifyChanged(new ViewerNotification(notification, object, child, !child || (child && label)));
  }
  
  protected boolean isWrappingNeeded(Object object)
  {
    wrappingNeeded = null;
    return super.isWrappingNeeded(object);
  }
}
