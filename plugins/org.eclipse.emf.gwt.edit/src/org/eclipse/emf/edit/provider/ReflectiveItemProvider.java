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
 * $Id: ReflectiveItemProvider.java,v 1.1 2010/04/28 14:48:42 emerks Exp $
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
import org.eclipse.emf.common.util.EMap;
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
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) 
  {
    // if (itemPropertyDescriptors == null)
    {
      itemPropertyDescriptors = new ArrayList<IItemPropertyDescriptor>();

      for (EStructuralFeature eFeature : ((EObject)object).eClass().getEAllStructuralFeatures())
      {
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

  protected List<EObject> allRoots;
  protected List<EPackage> allEPackages;
  protected List<EClass> allEClasses;

  protected void gatherAllMetaData(EObject eObject)
  {
    EObject container = eObject.eContainer();
    if (container == null)
    {
      ExtendedMetaData extendedMetaData =
          eObject.eResource() == null || eObject.eResource().getResourceSet() == null ?
            ExtendedMetaData.INSTANCE :
            new BasicExtendedMetaData(eObject.eResource().getResourceSet().getPackageRegistry());
      EStructuralFeature xmlnsPrefixMapFeature = extendedMetaData.getXMLNSPrefixMapFeature(eObject.eClass());
      if (xmlnsPrefixMapFeature != null)
      {
        @SuppressWarnings("unchecked")
        EMap<String, String> map = ((EMap<String, String>)eObject.eGet(xmlnsPrefixMapFeature));
        for (Map.Entry<String, String> entry : map)
        {
          EPackage ePackage = extendedMetaData.getPackage(entry.getValue());
          if (ePackage != null)
          {
            gatherMetaData((EModelElement)EcoreUtil.getRootContainer(ePackage));
          }
        }
      }
    }
    else
    {
      gatherAllMetaData(container);
    }
    
    gatherMetaData(eObject.eClass());
  }
  
  protected List<EClass> getAllEClasses(EClass eClass)
  {
    gatherMetaData(eClass);
    return allEClasses;
  }
  
  protected List<EPackage> getAllEPackages(EClass eClass)
  {
    gatherMetaData(eClass);
    return allEPackages;
  }
    
  protected void gatherMetaData(EModelElement eModelElement)
  {
    if (allRoots == null)
    {
      allRoots = new ArrayList<EObject>();
      allEClasses = new ArrayList<EClass>();
      allEPackages = new ArrayList<EPackage>();
    }
    
    Set<EObject> roots = new HashSet<EObject>();
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
          allEPackages.add((EPackage)root);
        }
        for (Iterator<EObject> i = root.eAllContents(); i.hasNext(); )
        {
          EObject eObject = i.next();
          
          if (eObject instanceof EClassifier)
          {
            extendedMetaData.getName((EClassifier)eObject);
            if (eObject instanceof EClass)
            {
              allEClasses.add((EClass)eObject);
            }
          }
          else if (eObject instanceof EStructuralFeature)
          {
            extendedMetaData.getName((EStructuralFeature)eObject);
          }
          else if (eObject instanceof EPackage)
          {
            allEPackages.add((EPackage)eObject);
          }
          
          for (EReference eReference : eObject.eClass().getEAllReferences())
          {
            if (!eReference.isDerived() && !eReference.isContainer() && !eReference.isContainment())
            {
              if (eReference.isMany())
              {
                @SuppressWarnings("unchecked")
                List<EObject> eObjects = ((List<EObject>)eObject.eGet(eReference));
                for (EObject crossReference : eObjects)
                {
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
        Iterator<EObject> i = roots.iterator();
        root = i.next();
        i.remove();
      }
    }
  }

  protected List<EClass> getAllChildConcreteSubclasses(EReference eReference)
  {
    EClass eClass = eReference.getEReferenceType();
    List<EClass> result = getAllConcreteSubclasses(eClass);
    if (eClass == EcorePackage.Literals.EOBJECT && eReference.getEAnnotation(ExtendedMetaData.ANNOTATION_URI) != null)
    {
      if (!result.contains(XMLTypePackage.Literals.ANY_TYPE))
      {
        result.add(XMLTypePackage.Literals.ANY_TYPE);
      }
    }
    return result;
  }

  protected List<EClass> getAllConcreteSubclasses(EClass eClass)
  {
    List<EClass> result = new ArrayList<EClass>();
    if (eClass == EcorePackage.Literals.EOBJECT)
    {
      for (EClass otherEClass : getAllEClasses(eClass))
      {
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
      for (EClass otherEClass : getAllEClasses(eClass))
      {
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
  @Override
  protected Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    // if (childrenFeatures == null)
    {
      childrenFeatures = new ArrayList<EStructuralFeature>();
      EObject eObject = (EObject)object;
      EClass eClass = eObject.eClass();
      if (ExtendedMetaData.INSTANCE.getContentKind(eClass) != ExtendedMetaData.MIXED_CONTENT)
      {
        for (EReference eReference : eClass.getEAllReferences())
        {
          if (eReference.isContainment() && ExtendedMetaData.INSTANCE.getGroup(eReference) == null)
          {
            childrenFeatures.add(eReference);
          }
        }
      }
      for (EAttribute eAttribute : eClass.getEAllAttributes())
      {
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
  @Override
  public Object getImage(Object object)
  {
    EObject eObject = (EObject)object;
    EClass eClass = eObject.eClass();
    return URI.createURI(getResourceLocator().getImage("full/obj16/Item").toString() + "#" + eClass.getName());
  }

  /**
   */
  @Override
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
    for (EAttribute eAttribute : eClass.getEAllAttributes())
    {
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

    for (Iterator<String> i = parseName(name, '_').iterator(); i.hasNext(); )
    {
      String component = i.next();
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
  protected List<String> parseName(String sourceName, char sourceSeparator)
  {
    List<String> result = new ArrayList<String>();
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
  protected List<? extends EStructuralFeature> getAllDelegatedFeatures(EStructuralFeature feature)
  {
    if (!FeatureMapUtil.isFeatureMap(feature)) return Collections.emptyList();

    EClass eClass = feature.getEContainingClass();
    List<EStructuralFeature> delegated = new ArrayList<EStructuralFeature>();

    if (ExtendedMetaData.INSTANCE.getMixedFeature(eClass) == feature)
    {
       delegated.add(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__COMMENT);
       delegated.add(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT);
       delegated.add(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__PROCESSING_INSTRUCTION);

       if (ExtendedMetaData.INSTANCE.getDocumentRoot(eClass.getEPackage()) != eClass)
       {
         delegated.add(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__CDATA);
       }

       for (EStructuralFeature otherFeature : eClass.getEAllStructuralFeatures())
       {
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
          Set<EStructuralFeature> allDelegated = new HashSet<EStructuralFeature>();
          Set<String> qNames = new HashSet<String>();
          for (EStructuralFeature otherFeature : eClass.getEStructuralFeatures())
          {
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
          
          for (EPackage ePackage : getAllEPackages(eClass))
          {
            EClass documentRootEClass = ExtendedMetaData.INSTANCE.getDocumentRoot(ePackage);
            if (documentRootEClass != null)
            {
              for (EStructuralFeature otherFeature : documentRootEClass.getEAllStructuralFeatures())
              {
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
          for (EPackage ePackage : getAllEPackages(eClass))
          {
            EClass documentRootEClass = ExtendedMetaData.INSTANCE.getDocumentRoot(ePackage);
            if (documentRootEClass != null)
            {
              for (EStructuralFeature otherFeature : documentRootEClass.getEAllStructuralFeatures())
              {
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

    List<EStructuralFeature> result = new ArrayList<EStructuralFeature>();
    for (EStructuralFeature delegatedFeature : delegated)
    {
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
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)    
  {
    // This ensure that this package itself is traversed even if the reference type is EObject...
    //
    gatherAllMetaData((EObject)object);

    for (EStructuralFeature feature : getChildrenFeatures(object))
    {
      if (FeatureMapUtil.isFeatureMap(feature))
      {
        for (EStructuralFeature delegatedFeature : getAllDelegatedFeatures(feature))
        {
          if (delegatedFeature instanceof EAttribute)
          {
            EDataType type = ((EAttribute)delegatedFeature).getEAttributeType();
            Object value = delegatedFeature.getDefaultValue();

            if (value == null)
            {
              Class<?> instanceClass = type.getInstanceClass();

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
                value = '\u0000';
              }
              else
              {
                /*
                String literal = instanceClass != null && Number.class.isAssignableFrom(instanceClass) ? "0" : ""; 
                try
                {
                  value = EcoreUtil.createFromString(type, literal);
                }
                catch (Exception e) 
                {
                  // Ignore
                }
                */
              }
            }

            if (value != null)
            {
              newChildDescriptors.add(createChildParameter(feature, FeatureMapUtil.createEntry(delegatedFeature, value)));
            }
          }
          else if (delegatedFeature instanceof EReference)
          {
            for (EClass eClass : getAllChildConcreteSubclasses((EReference)delegatedFeature))
            {
              FeatureMap.Entry entry = FeatureMapUtil.createEntry(delegatedFeature, EcoreUtil.create(eClass));
              newChildDescriptors.add(createChildParameter(feature, entry));
            }
          }
        }
      }
      else if (feature instanceof EReference && feature.isChangeable())
      {
        for (EClass eClass : getAllChildConcreteSubclasses((EReference)feature))
        {
          newChildDescriptors.add(createChildParameter(feature, EcoreUtil.create(eClass)));
        }
      }
    }
  }

  @Override
  public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection)
  {
    if (feature instanceof EStructuralFeature && FeatureMapUtil.isFeatureMap((EStructuralFeature)feature))
    {
      FeatureMap.Entry entry = (FeatureMap.Entry)child;
      feature = entry.getEStructuralFeature();
      child = entry.getValue();
    }

    String childType = feature instanceof EAttribute ? getTypeText((EAttribute)feature) : getTypeText(child);

    return 
      getResourceLocator().getString
        ("_UI_CreateChild_text2",
         new Object[] { childType, getFeatureText(feature), getTypeText(owner) });
  }

  /**
   */
  @Override
  public Object getCreateChildImage(Object owner, Object feature, Object child, Collection<?> selection)
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
  @Override
  protected String getTypeText(Object object)
  {
    String text = object instanceof EObject ? ((EObject)object).eClass().getName() : getString("_UI_Unknown_type");
    return format(capName(text), ' ');
  }

  /**
   */
  @Override
  protected String getTypeText(EAttribute attribute)
  {
    return format(capName(attribute.getEAttributeType().getName()), ' ');
  }

  /**
   */
  @Override
  protected String getFeatureText(Object feature)
  {
    String text = feature instanceof EStructuralFeature ?
      ((EStructuralFeature)feature).getName() : getResourceLocator().getString("_UI_Unknown_feature");
    return format(capName(text), ' ');
  }

  /**
   */
  @Override
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
  
  @Override
  protected boolean isWrappingNeeded(Object object)
  {
    wrappingNeeded = null;
    return super.isWrappingNeeded(object);
  }
}
