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
 * $Id: EClassImpl.java,v 1.26 2006/05/07 12:01:41 emerks Exp $
 */

package org.eclipse.emf.ecore.impl;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EClass</b></em>'.
 * @extends ESuperAdapter.Holder
 * @ignore
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassImpl#isInterface <em>Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassImpl#getESuperTypes <em>ESuper Types</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassImpl#getEOperations <em>EOperations</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassImpl#getEAllAttributes <em>EAll Attributes</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassImpl#getEAllReferences <em>EAll References</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassImpl#getEReferences <em>EReferences</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassImpl#getEAttributes <em>EAttributes</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassImpl#getEAllContainments <em>EAll Containments</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassImpl#getEAllOperations <em>EAll Operations</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassImpl#getEAllStructuralFeatures <em>EAll Structural Features</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassImpl#getEAllSuperTypes <em>EAll Super Types</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassImpl#getEIDAttribute <em>EID Attribute</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassImpl#getEStructuralFeatures <em>EStructural Features</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EClassImpl extends EClassifierImpl implements EClass, ESuperAdapter.Holder
{
  public interface FeatureSubsetSupplier
  {
    EStructuralFeature [] containments();
    EStructuralFeature [] crossReferences();
    EStructuralFeature [] features();
  }

  protected EAttribute eIDAttribute;
  protected BasicEList eAllAttributes;
  protected BasicEList eAllReferences;
  protected BasicEList eAllStructuralFeatures;
  protected EStructuralFeature[] eAllStructuralFeaturesData;
  protected BasicEList eAllContainments;  
  protected BasicEList eAllOperations;
  protected BasicEList eAllSuperTypes;
  protected Map eNameToFeatureMap;
  protected ESuperAdapter eSuperAdapter;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClassImpl()
  {
    super();
  }

  protected void freeze()
  {
    getEAllAttributes();
    getEAllReferences();
    getEAllContainments();
    getEAllOperations();
    getEAllStructuralFeatures();
    getEAllSuperTypes();

    getESuperAdapter().getSubclasses().clear();
    
    if (eStructuralFeatures != null)
    {
      for (int i = 0, size = eStructuralFeatures.size(); i < size; ++i)
      {
        freeze(eStructuralFeatures.get(0));
      }
    }
    if (eOperations != null)
    {
      for (int i = 0, size = eOperations.size(); i < size; ++i)
      {
        freeze(eOperations.get(0));
      }
    }
    super.freeze();
  }
  
  public boolean isFrozen()
  {
    return super.isFrozen();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return EcorePackage.Literals.ECLASS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EAttribute getEIDAttribute()
  {
    getEAllAttributes();
    return eIDAttribute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getEStructuralFeatures()
  {
    if (eStructuralFeatures == null)
    {
      eStructuralFeatures = new EObjectContainmentWithInverseEList(EStructuralFeature.class, this, EcorePackage.ECLASS__ESTRUCTURAL_FEATURES, EcorePackage.ESTRUCTURAL_FEATURE__ECONTAINING_CLASS);
    }
    return eStructuralFeatures;
  }

  public EList getEAllAttributes()
  {
    if (eAllAttributes == null)
    {
      eIDAttribute = null;

      BasicEList result =
        new UniqueEList()
        {
          protected Object [] newData(int capacity)
          {
            return new EAttribute [capacity];
          }

          protected boolean useEquals()
          {
            return false;
          }

          protected void didAdd(int index, Object object)
          {
            EAttribute eAttribute = (EAttribute)object;
            if (eAttribute.isID() && eIDAttribute == null) 
            {
              eIDAttribute = eAttribute;
            }
          }
        };

      BasicEList attributes = 
        new UniqueEList()
        {
          protected Object [] newData(int capacity)
          {
            return new EAttribute [capacity];
          }

          protected boolean useEquals()
          {
            return false;
          }
        };

      for (Iterator i = getESuperTypes().iterator(); i.hasNext(); )
      {
        EClass eSuperType = (EClass)i.next();
        result.addAll(eSuperType.getEAllAttributes());
      }
      for (Iterator i = getEStructuralFeatures().iterator(); i.hasNext(); )
      {
        EStructuralFeature eStructuralFeature = (EStructuralFeature)i.next();
        if (eStructuralFeature instanceof EAttribute)
        {
          attributes.add(eStructuralFeature);
        }
      }

      attributes.shrink();
      eAttributes = 
        new EcoreEList.UnmodifiableEList.FastCompare
          (this, EcorePackage.eINSTANCE.getEClass_EAttributes(), attributes.size(), attributes.data())
        {
          public void addUnique(Object object)
          {
            ((InternalEList)getEStructuralFeatures()).addUnique(object);
          }

          public boolean add(Object object)
          {
            System.err.println("Please fix your code to add using EClass.getEStructuralFeatures() instead of EClass.getEAttributes()");
            return ((InternalEList)getEStructuralFeatures()).add(object);
          }
        };

      result.addAll(eAttributes);
      result.shrink();
      eAllAttributes = 
        new EcoreEList.UnmodifiableEList.FastCompare
          (this, EcorePackage.eINSTANCE.getEClass_EAllAttributes(), result.size(), result.data());
      getESuperAdapter().setAllAttributesCollectionModified(false);
    }

    return eAllAttributes;
  }

  public EList getEAllReferences()
  {
    if (eAllReferences == null)
    {
      class ReferenceList extends UniqueEList
      {
        public ReferenceList()
        {
        }

        protected Object [] newData(int capacity)
        {
          return new EReference [capacity];
        }

        protected boolean useEquals()
        {
          return false;
        }
      }
      BasicEList result = new ReferenceList();
      BasicEList references = new ReferenceList();

      for (Iterator i = getESuperTypes().iterator(); i.hasNext(); )
      {
        EClass eSuperType = (EClass)i.next();
        result.addAll(eSuperType.getEAllReferences());
      }
      for (Iterator i = getEStructuralFeatures().iterator(); i.hasNext(); )
      {
        EStructuralFeature eStructuralFeature = (EStructuralFeature)i.next();
        if (eStructuralFeature instanceof EReference)
        {
          references.add(eStructuralFeature);
        }
      }

      references.shrink();
      eReferences = 
        new EcoreEList.UnmodifiableEList.FastCompare
          (this, EcorePackage.eINSTANCE.getEClass_EReferences(), references.size(), references.data())
        {
          public void addUnique(Object object)
          {
            ((InternalEList)getEStructuralFeatures()).addUnique(object);
          }

          public boolean add(Object object)
          {
            System.err.println("Please fix your code to add using EClass.getEStructuralFeatures() instead of EClass.getEReferences()");
            return ((InternalEList)getEStructuralFeatures()).add(object);
          }
        };

      result.addAll(eReferences);
      result.shrink();
      eAllReferences = 
        new EcoreEList.UnmodifiableEList.FastCompare
          (this, EcorePackage.eINSTANCE.getEClass_EAllReferences(), result.size(), result.data());
      getESuperAdapter().setAllReferencesCollectionModified(false);
    }

    return eAllReferences;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList getEReferences()
  {
    getEAllReferences();
    return eReferences;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList getEAttributes()
  {
    getEAllAttributes();
    return eAttributes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public EList getEAllStructuralFeatures() 
  {
    // The algorithm for the order of the features in this list should never change.
    // Also, the fact that a new list is created whenever the contents change 
    // is something else that should never change.
    // There are clients who rely on both these behaviors, 
    // and they will need to agree to any change,
    // so that they can adjust their own code.
    //
    if (eAllStructuralFeatures == null)
    {
      class EStructuralFeatureUniqueEList extends UniqueEList
      {
        protected Object [] newData(int capacity)
        {
          return new EStructuralFeature [capacity];
        }

        protected boolean useEquals()
        {
          return false;
        }
      }

      BasicEList result = new EStructuralFeatureUniqueEList();

      for (Iterator i = getESuperTypes().iterator(); i.hasNext(); )
      {
        EClass eSuperType = (EClass)i.next();
        result.addAll(eSuperType.getEAllStructuralFeatures());
      }
      int featureID = result.size();
      for (Iterator i = getEStructuralFeatures().iterator(); i.hasNext(); ++featureID)
      {
        ((EStructuralFeatureImpl)i.next()).setFeatureID(featureID);
      }
      result.addAll(getEStructuralFeatures());

      class EAllStructuralFeaturesList extends EcoreEList.UnmodifiableEList.FastCompare implements FeatureSubsetSupplier
      {
        protected EStructuralFeature [] containments;
        protected EStructuralFeature [] crossReferences;

        public EAllStructuralFeaturesList(BasicEList eAllStructuralFeatures)
        {
          super
            (EClassImpl.this, 
             EcorePackage.eINSTANCE.getEClass_EAllStructuralFeatures(), 
             eAllStructuralFeatures.size(), 
             eAllStructuralFeatures.data()) ;

          BasicEList containmentsList = new EStructuralFeatureUniqueEList();
          BasicEList crossReferencesList = new EStructuralFeatureUniqueEList();
          boolean isMixed = "mixed".equals(EcoreUtil.getAnnotation(EClassImpl.this, ExtendedMetaData.ANNOTATION_URI, "kind"));
          for (int i = 0;  i < size; ++i)
          {
            // Skip derived features.
            //
            EStructuralFeature eStructuralFeature = (EStructuralFeature)data[i];
            if (eStructuralFeature instanceof EReference)
            {
              EReference eReference = (EReference)eStructuralFeature;
              if (eReference.isContainment())
              {
                if (!eReference.isDerived())
                {
                  containmentsList.add(eReference);
                }
              }
              else if (!eReference.isContainer())
              {
                // Include derived relations only if they won't also come from mixed or a group.
                //
                if (!eReference.isDerived() || 
                      !isMixed && EcoreUtil.getAnnotation(eReference, ExtendedMetaData.ANNOTATION_URI, "group") == null)
                {
                  crossReferencesList.add(eReference);
                }
              }
            }
            else if (FeatureMapUtil.isFeatureMap(eStructuralFeature))
            {
              if (!eStructuralFeature.isDerived())
              {
                containmentsList.add(eStructuralFeature);
                crossReferencesList.add(eStructuralFeature);
              }
            }
          }
          containmentsList.shrink();
          crossReferencesList.shrink();
          containments = (EStructuralFeature [])containmentsList.data();
          crossReferences = (EStructuralFeature [])crossReferencesList.data();
        }

        public EStructuralFeature [] containments()
        {
          return containments;
        }

        public EStructuralFeature [] crossReferences()
        {
          return crossReferences;
        }

        public EStructuralFeature [] features()
        {
          return (EStructuralFeature [])data;
        }

        public int indexOf(Object object)
        {
          if (object instanceof EStructuralFeature)
          {
            EStructuralFeature eStructuralFeature = (EStructuralFeature)object;
            int index = eStructuralFeature.getFeatureID();
            if (index != -1)
            {
              for (int last = this.size; index < last; ++index)
              {
                if (data[index] == object)
                {
                  return index;
                }
              }
            }
          }
          return -1;
        }
      }

      result.shrink();
      eAllStructuralFeatures = new EAllStructuralFeaturesList(result);
      eAllStructuralFeaturesData = (EStructuralFeature[])result.data();
      if (eAllStructuralFeaturesData == null)
      {
        eAllStructuralFeaturesData = NO_EALL_STRUCTURE_FEATURES_DATA;
      }

      eNameToFeatureMap = null; 
      
      getESuperAdapter().setAllStructuralFeaturesCollectionModified(false);
    }

    return eAllStructuralFeatures;
  }
  
  private static final EStructuralFeature[] NO_EALL_STRUCTURE_FEATURES_DATA = {};

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public EList getEAllOperations()
  {
    if (eAllOperations == null)
    {
      BasicEList result = 
        new UniqueEList()
        {
          protected Object [] newData(int capacity)
          {
            return new EOperation [capacity];
          }

          protected boolean useEquals()
          {
            return false;
          }
        };

      for (Iterator i = getESuperTypes().iterator(); i.hasNext(); )
      {
        result.addAll(((EClass)i.next()).getEAllOperations());
      }
      result.addAll(getEOperations());
      result.shrink();
      eAllOperations = 
        new EcoreEList.UnmodifiableEList.FastCompare
          (this, EcorePackage.eINSTANCE.getEClass_EAllOperations(), result.size(), result.data());
      getESuperAdapter().setAllOperationsCollectionModified(false);
    }

    return eAllOperations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case EcorePackage.ECLASS__EANNOTATIONS:
        return getEAnnotations();
      case EcorePackage.ECLASS__NAME:
        return getName();
      case EcorePackage.ECLASS__INSTANCE_CLASS_NAME:
        return getInstanceClassName();
      case EcorePackage.ECLASS__INSTANCE_CLASS:
        return getInstanceClass();
      case EcorePackage.ECLASS__DEFAULT_VALUE:
        return getDefaultValue();
      case EcorePackage.ECLASS__EPACKAGE:
        if (resolve) return getEPackage();
        return basicGetEPackage();
      case EcorePackage.ECLASS__ABSTRACT:
        return isAbstract() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.ECLASS__INTERFACE:
        return isInterface() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.ECLASS__ESUPER_TYPES:
        return getESuperTypes();
      case EcorePackage.ECLASS__EOPERATIONS:
        return getEOperations();
      case EcorePackage.ECLASS__EALL_ATTRIBUTES:
        return getEAllAttributes();
      case EcorePackage.ECLASS__EALL_REFERENCES:
        return getEAllReferences();
      case EcorePackage.ECLASS__EREFERENCES:
        return getEReferences();
      case EcorePackage.ECLASS__EATTRIBUTES:
        return getEAttributes();
      case EcorePackage.ECLASS__EALL_CONTAINMENTS:
        return getEAllContainments();
      case EcorePackage.ECLASS__EALL_OPERATIONS:
        return getEAllOperations();
      case EcorePackage.ECLASS__EALL_STRUCTURAL_FEATURES:
        return getEAllStructuralFeatures();
      case EcorePackage.ECLASS__EALL_SUPER_TYPES:
        return getEAllSuperTypes();
      case EcorePackage.ECLASS__EID_ATTRIBUTE:
        return getEIDAttribute();
      case EcorePackage.ECLASS__ESTRUCTURAL_FEATURES:
        return getEStructuralFeatures();
    }
    return eDynamicGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case EcorePackage.ECLASS__EANNOTATIONS:
        getEAnnotations().clear();
        getEAnnotations().addAll((Collection)newValue);
        return;
      case EcorePackage.ECLASS__NAME:
        setName((String)newValue);
        return;
      case EcorePackage.ECLASS__INSTANCE_CLASS_NAME:
        setInstanceClassName((String)newValue);
        return;
      case EcorePackage.ECLASS__ABSTRACT:
        setAbstract(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.ECLASS__INTERFACE:
        setInterface(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.ECLASS__ESUPER_TYPES:
        getESuperTypes().clear();
        getESuperTypes().addAll((Collection)newValue);
        return;
      case EcorePackage.ECLASS__EOPERATIONS:
        getEOperations().clear();
        getEOperations().addAll((Collection)newValue);
        return;
      case EcorePackage.ECLASS__ESTRUCTURAL_FEATURES:
        getEStructuralFeatures().clear();
        getEStructuralFeatures().addAll((Collection)newValue);
        return;
    }
    eDynamicSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case EcorePackage.ECLASS__EANNOTATIONS:
        getEAnnotations().clear();
        return;
      case EcorePackage.ECLASS__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EcorePackage.ECLASS__INSTANCE_CLASS_NAME:
        setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
        return;
      case EcorePackage.ECLASS__ABSTRACT:
        setAbstract(ABSTRACT_EDEFAULT);
        return;
      case EcorePackage.ECLASS__INTERFACE:
        setInterface(INTERFACE_EDEFAULT);
        return;
      case EcorePackage.ECLASS__ESUPER_TYPES:
        getESuperTypes().clear();
        return;
      case EcorePackage.ECLASS__EOPERATIONS:
        getEOperations().clear();
        return;
      case EcorePackage.ECLASS__ESTRUCTURAL_FEATURES:
        getEStructuralFeatures().clear();
        return;
    }
    eDynamicUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case EcorePackage.ECLASS__EANNOTATIONS:
        return eAnnotations != null && !eAnnotations.isEmpty();
      case EcorePackage.ECLASS__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EcorePackage.ECLASS__INSTANCE_CLASS_NAME:
        return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
      case EcorePackage.ECLASS__INSTANCE_CLASS:
        return INSTANCE_CLASS_EDEFAULT == null ? getInstanceClass() != null : !INSTANCE_CLASS_EDEFAULT.equals(getInstanceClass());
      case EcorePackage.ECLASS__DEFAULT_VALUE:
        return DEFAULT_VALUE_EDEFAULT == null ? getDefaultValue() != null : !DEFAULT_VALUE_EDEFAULT.equals(getDefaultValue());
      case EcorePackage.ECLASS__EPACKAGE:
        return basicGetEPackage() != null;
      case EcorePackage.ECLASS__ABSTRACT:
        return ((eFlags & ABSTRACT_EFLAG) != 0) != ABSTRACT_EDEFAULT;
      case EcorePackage.ECLASS__INTERFACE:
        return ((eFlags & INTERFACE_EFLAG) != 0) != INTERFACE_EDEFAULT;
      case EcorePackage.ECLASS__ESUPER_TYPES:
        return eSuperTypes != null && !eSuperTypes.isEmpty();
      case EcorePackage.ECLASS__EOPERATIONS:
        return eOperations != null && !eOperations.isEmpty();
      case EcorePackage.ECLASS__EALL_ATTRIBUTES:
        return !getEAllAttributes().isEmpty();
      case EcorePackage.ECLASS__EALL_REFERENCES:
        return !getEAllReferences().isEmpty();
      case EcorePackage.ECLASS__EREFERENCES:
        return !getEReferences().isEmpty();
      case EcorePackage.ECLASS__EATTRIBUTES:
        return !getEAttributes().isEmpty();
      case EcorePackage.ECLASS__EALL_CONTAINMENTS:
        return !getEAllContainments().isEmpty();
      case EcorePackage.ECLASS__EALL_OPERATIONS:
        return !getEAllOperations().isEmpty();
      case EcorePackage.ECLASS__EALL_STRUCTURAL_FEATURES:
        return !getEAllStructuralFeatures().isEmpty();
      case EcorePackage.ECLASS__EALL_SUPER_TYPES:
        return !getEAllSuperTypes().isEmpty();
      case EcorePackage.ECLASS__EID_ATTRIBUTE:
        return getEIDAttribute() != null;
      case EcorePackage.ECLASS__ESTRUCTURAL_FEATURES:
        return eStructuralFeatures != null && !eStructuralFeatures.isEmpty();
    }
    return eDynamicIsSet(featureID);
  }

  public EList getEAllContainments()
  {
    if (eAllContainments == null)
    {
      BasicEList result = 
        new UniqueEList()
        {
          protected Object [] newData(int capacity)
          {
            return new EReference [capacity];
          }

          protected boolean useEquals()
          {
            return false;
          }
        };

      for (Iterator i = getEAllReferences().iterator(); i.hasNext(); )
      {
        EReference eReference = (EReference)i.next();
        if (eReference.isContainment())
        {
          result.add(eReference);
        }
      }

      result.shrink();
      eAllContainments = 
        new EcoreEList.UnmodifiableEList.FastCompare
          (this, EcorePackage.eINSTANCE.getEClass_EAllContainments(), result.size(), result.data()); 
      getESuperAdapter().setAllContainmentsCollectionModified(false);
    }

    return eAllContainments;
  }

  public EStructuralFeature getEStructuralFeature(String name)
  {
    getFeatureCount();
    if (eNameToFeatureMap == null)
    {
      Map result = new HashMap(3 * eAllStructuralFeatures.size() / 2 + 1);
      for (Iterator i = eAllStructuralFeatures.iterator(); i.hasNext(); )
      {
        EStructuralFeature eStructuralFeature = (EStructuralFeature)i.next();
        result.put(eStructuralFeature.getName(), eStructuralFeature);
      }
      eNameToFeatureMap = result;
    }
    return (EStructuralFeatureImpl)eNameToFeatureMap.get(name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case EcorePackage.ECLASS__EANNOTATIONS:
        return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
      case EcorePackage.ECLASS__EPACKAGE:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return eBasicSetContainer(otherEnd, EcorePackage.ECLASS__EPACKAGE, msgs);
      case EcorePackage.ECLASS__EOPERATIONS:
        return ((InternalEList)getEOperations()).basicAdd(otherEnd, msgs);
      case EcorePackage.ECLASS__ESTRUCTURAL_FEATURES:
        return ((InternalEList)getEStructuralFeatures()).basicAdd(otherEnd, msgs);
    }
    return eDynamicInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case EcorePackage.ECLASS__EANNOTATIONS:
        return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
      case EcorePackage.ECLASS__EPACKAGE:
        return eBasicSetContainer(null, EcorePackage.ECLASS__EPACKAGE, msgs);
      case EcorePackage.ECLASS__EOPERATIONS:
        return ((InternalEList)getEOperations()).basicRemove(otherEnd, msgs);
      case EcorePackage.ECLASS__ESTRUCTURAL_FEATURES:
        return ((InternalEList)getEStructuralFeatures()).basicRemove(otherEnd, msgs);
    }
    return eDynamicInverseRemove(otherEnd, featureID, msgs);
  }

  protected EStructuralFeature[] getEAllStructuralFeaturesData()
  {
    if (eAllStructuralFeaturesData == null)
    {
      getEAllStructuralFeatures();
    }
    return eAllStructuralFeaturesData;
  }
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public int getFeatureCount()
  {
    return getEAllStructuralFeaturesData().length;
  }
  
  public EStructuralFeature getEStructuralFeature(int featureID) 
  {
    EStructuralFeature [] eAllStructuralFeaturesData  = getEAllStructuralFeaturesData();
    return 
      featureID >= 0 && featureID < eAllStructuralFeaturesData.length ? 
        eAllStructuralFeaturesData[featureID] : 
        null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public int getFeatureID(EStructuralFeature feature)
  {
    EStructuralFeature [] eAllStructuralFeaturesData  = getEAllStructuralFeaturesData();
    int index = feature.getFeatureID();
    if (index != -1)
    {
      for (int last = eAllStructuralFeaturesData.length; index < last; ++index)
      {
        if (eAllStructuralFeaturesData[index] == feature)
        {
          return index;
        }
      }
    }
    return -1;
  }

  /**
   * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAbstract()
   * @generated
   * @ordered
   */
  protected static final boolean ABSTRACT_EDEFAULT = false;

  /**
   * The flag representing the value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAbstract()
   * @generated
   * @ordered
   */
  protected static final int ABSTRACT_EFLAG = 1 << 8;

  /**
   * The default value of the '{@link #isInterface() <em>Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isInterface()
   * @generated
   * @ordered
   */
  protected static final boolean INTERFACE_EDEFAULT = false;

  /**
   * The flag representing the value of the '{@link #isInterface() <em>Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isInterface()
   * @generated
   * @ordered
   */
  protected static final int INTERFACE_EFLAG = 1 << 9;

  /**
   * The cached value of the '{@link #getESuperTypes() <em>ESuper Types</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getESuperTypes()
   * @generated
   * @ordered
   */
  protected EList eSuperTypes = null;

  /**
   * The cached value of the '{@link #getEOperations() <em>EOperations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEOperations()
   * @generated
   * @ordered
   */
  protected EList eOperations = null;

  /**
   * The cached value of the '{@link #getEReferences() <em>EReferences</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEReferences()
   * @generated NOT
   * @ordered
   */
  protected BasicEList eReferences = null;

  /**
   * The cached value of the '{@link #getEAttributes() <em>EAttributes</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttributes()
   * @generated NOT
   * @ordered
   */
  protected BasicEList eAttributes = null;

  /**
   * The cached value of the '{@link #getEStructuralFeatures() <em>EStructural Features</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEStructuralFeatures()
   * @generated
   * @ordered
   */
  protected EList eStructuralFeatures = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isAbstract()
  {
    return (eFlags & ABSTRACT_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAbstract(boolean newAbstract)
  {
    boolean oldAbstract = (eFlags & ABSTRACT_EFLAG) != 0;
    if (newAbstract) eFlags |= ABSTRACT_EFLAG; else eFlags &= ~ABSTRACT_EFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.ECLASS__ABSTRACT, oldAbstract, newAbstract));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isInterface()
  {
    return (eFlags & INTERFACE_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInterface(boolean newInterface)
  {
    boolean oldInterface = (eFlags & INTERFACE_EFLAG) != 0;
    if (newInterface) eFlags |= INTERFACE_EFLAG; else eFlags &= ~INTERFACE_EFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.ECLASS__INTERFACE, oldInterface, newInterface));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (abstract: ");
    result.append((eFlags & ABSTRACT_EFLAG) != 0);
    result.append(", interface: ");
    result.append((eFlags & INTERFACE_EFLAG) != 0);
    result.append(')');
    return result.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getESuperTypesGen()
  {
    if (eSuperTypes == null)
    {
      eSuperTypes = new EObjectResolvingEList(EClass.class, this, EcorePackage.ECLASS__ESUPER_TYPES);
    }
    return eSuperTypes;
  }

  public EList getESuperTypes()
  {
    getESuperAdapter();
    return getESuperTypesGen();
  }
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getEOperations()
  {
    if (eOperations == null)
    {
      eOperations = new EObjectContainmentWithInverseEList(EOperation.class, this, EcorePackage.ECLASS__EOPERATIONS, EcorePackage.EOPERATION__ECONTAINING_CLASS);
    }
    return eOperations;
  }

  /**
   * Determines if the class or interface represented by this Class object is either
   * the same as, or is a superclass or superinterface of, the class or interface
   * represented by the specified someClass parameter.  Semantics are the same as
   * java.lang.Class#isAssignableFrom
   */
  public boolean isSuperTypeOf(EClass someClass)
  {
    return someClass == this || someClass.getEAllSuperTypes().contains(this);
  }

  /**
   * Returns all the supertypes in the hierarchy.
   */
  public EList getEAllSuperTypes()
  {
    if (eAllSuperTypes == null)
    {
      BasicEList result = 
        new UniqueEList()
        {
          protected Object [] newData(int capacity)
          {
            return new EClassifier [capacity];
          }

          protected boolean useEquals()
          {
            return false;
          }
        };

      EList immediateSupers = getESuperTypes();
      Iterator i = immediateSupers.iterator();
      while (i.hasNext())
      {
        EClass supertype = (EClass)i.next();
        EList higherSupers = supertype.getEAllSuperTypes();
        result.addAll(higherSupers);
        result.add(supertype);
      }

      result.shrink();
      eAllSuperTypes = 
        new EcoreEList.UnmodifiableEList.FastCompare
          (this, EcorePackage.eINSTANCE.getEClass_EAllSuperTypes(), result.size(), result.data());
      getESuperAdapter().setAllSuperCollectionModified(false);
    }

    return eAllSuperTypes;
  }

  protected boolean dynamicIsInstance(EObject eObject)
  {
    return isSuperTypeOf(eObject.eClass());
  }

  public ESuperAdapter getESuperAdapter()
  {
    if (eSuperAdapter == null)
    {
      eSuperAdapter = 
        new ESuperAdapter()
        {
          void setFlags(int featureId)
          {
            super.setFlags(featureId);
            
            if (isAllAttributesCollectionModified())
            {
              eAllAttributes = null;
            }
            if (isAllReferencesCollectionModified())
            {
              eAllReferences = null;
            }
            if (isAllStructuralFeaturesCollectionModified())
            {
              eAllStructuralFeatures = null;
              eAllStructuralFeaturesData = null;
            }
            if (isAllOperationsCollectionModified())
            {
              eAllOperations = null;
            }
            if (isAllContainmentsCollectionModified())
            {
              eAllContainments = null;
            }
            if (isAllSuperCollectionModified())
            {
              eAllSuperTypes = null;
            }
          }
        };
      eAdapters().add(0, eSuperAdapter);
    }
    return eSuperAdapter;
  }

  public void eSetDeliver(boolean deliver)
  {
    super.eSetDeliver(deliver);

    if (deliver)
    {
      for (Iterator eSuperTypes =  getESuperTypes().iterator(); eSuperTypes.hasNext(); )
      {
        EClass eSuperType = (EClass)eSuperTypes.next();
        ESuperAdapter eSuperAdapter = ((ESuperAdapter.Holder)eSuperType).getESuperAdapter();
        eSuperAdapter.getSubclasses().add(this);
      }
    }
  }

  public EObject eObjectForURIFragmentSegment(String uriFragmentSegment)
  {
    EObject result = eAllStructuralFeaturesData == null ? null : getEStructuralFeature(uriFragmentSegment);
    return result != null ? result : super.eObjectForURIFragmentSegment(uriFragmentSegment);
  }
}
