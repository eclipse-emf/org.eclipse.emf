/**
 * Copyright (c) 2003-2011 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.change;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;


/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.ecore.change.ChangeFactory
 * @model kind="package"
 * @generated
 */
public interface ChangePackage extends EPackage{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "change";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/emf/2003/Change";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "change";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ChangePackage eINSTANCE = org.eclipse.emf.ecore.change.impl.ChangePackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.change.impl.ChangeDescriptionImpl <em>Description</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.change.impl.ChangeDescriptionImpl
   * @see org.eclipse.emf.ecore.change.impl.ChangePackageImpl#getChangeDescription()
   * @generated
   */
  int CHANGE_DESCRIPTION = 0;

  /**
   * The feature id for the '<em><b>Object Changes</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHANGE_DESCRIPTION__OBJECT_CHANGES = 0;

  /**
   * The feature id for the '<em><b>Objects To Detach</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHANGE_DESCRIPTION__OBJECTS_TO_DETACH = 1;

  /**
   * The feature id for the '<em><b>Objects To Attach</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHANGE_DESCRIPTION__OBJECTS_TO_ATTACH = 2;

  /**
   * The feature id for the '<em><b>Resource Changes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHANGE_DESCRIPTION__RESOURCE_CHANGES = 3;

  /**
   * The number of structural features of the '<em>Description</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHANGE_DESCRIPTION_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.change.impl.EObjectToChangesMapEntryImpl <em>EObject To Changes Map Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.change.impl.EObjectToChangesMapEntryImpl
   * @see org.eclipse.emf.ecore.change.impl.ChangePackageImpl#getEObjectToChangesMapEntry()
   * @generated
   */
  int EOBJECT_TO_CHANGES_MAP_ENTRY = 1;

  /**
   * The feature id for the '<em><b>Key</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EOBJECT_TO_CHANGES_MAP_ENTRY__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EOBJECT_TO_CHANGES_MAP_ENTRY__VALUE = 1;

  /**
   * The number of structural features of the '<em>EObject To Changes Map Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EOBJECT_TO_CHANGES_MAP_ENTRY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.change.impl.FeatureChangeImpl <em>Feature Change</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.change.impl.FeatureChangeImpl
   * @see org.eclipse.emf.ecore.change.impl.ChangePackageImpl#getFeatureChange()
   * @generated
   */
  int FEATURE_CHANGE = 2;

  /**
   * The feature id for the '<em><b>Feature Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CHANGE__FEATURE_NAME = 0;

  /**
   * The feature id for the '<em><b>Data Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CHANGE__DATA_VALUE = 1;

  /**
   * The feature id for the '<em><b>Set</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CHANGE__SET = 2;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CHANGE__VALUE = 3;

  /**
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CHANGE__FEATURE = 4;

  /**
   * The feature id for the '<em><b>Reference Value</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CHANGE__REFERENCE_VALUE = 5;

  /**
   * The feature id for the '<em><b>List Changes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CHANGE__LIST_CHANGES = 6;

  /**
   * The number of structural features of the '<em>Feature Change</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CHANGE_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.change.impl.ListChangeImpl <em>List Change</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.change.impl.ListChangeImpl
   * @see org.eclipse.emf.ecore.change.impl.ChangePackageImpl#getListChange()
   * @generated
   */
  int LIST_CHANGE = 3;

  /**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_CHANGE__KIND = 0;

  /**
   * The feature id for the '<em><b>Data Values</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_CHANGE__DATA_VALUES = 1;

  /**
   * The feature id for the '<em><b>Index</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_CHANGE__INDEX = 2;

  /**
   * The feature id for the '<em><b>Move To Index</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_CHANGE__MOVE_TO_INDEX = 3;

  /**
   * The feature id for the '<em><b>Values</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_CHANGE__VALUES = 4;

  /**
   * The feature id for the '<em><b>Reference Values</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_CHANGE__REFERENCE_VALUES = 5;

  /**
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_CHANGE__FEATURE = 6;

  /**
   * The feature id for the '<em><b>Feature Map Entry Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_CHANGE__FEATURE_MAP_ENTRY_VALUES = 7;

  /**
   * The number of structural features of the '<em>List Change</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_CHANGE_FEATURE_COUNT = 8;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.change.impl.ResourceChangeImpl <em>Resource Change</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.change.impl.ResourceChangeImpl
   * @see org.eclipse.emf.ecore.change.impl.ChangePackageImpl#getResourceChange()
   * @generated
   */
  int RESOURCE_CHANGE = 4;

  /**
   * The feature id for the '<em><b>Resource URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOURCE_CHANGE__RESOURCE_URI = 0;

  /**
   * The feature id for the '<em><b>Resource</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOURCE_CHANGE__RESOURCE = 1;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOURCE_CHANGE__VALUE = 2;

  /**
   * The feature id for the '<em><b>List Changes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOURCE_CHANGE__LIST_CHANGES = 3;

  /**
   * The number of structural features of the '<em>Resource Change</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOURCE_CHANGE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.change.impl.FeatureMapEntryImpl <em>Feature Map Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.change.impl.FeatureMapEntryImpl
   * @see org.eclipse.emf.ecore.change.impl.ChangePackageImpl#getFeatureMapEntry()
   * @generated
   */
  int FEATURE_MAP_ENTRY = 5;

  /**
   * The feature id for the '<em><b>Feature Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MAP_ENTRY__FEATURE_NAME = 0;

  /**
   * The feature id for the '<em><b>Data Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MAP_ENTRY__DATA_VALUE = 1;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MAP_ENTRY__VALUE = 2;

  /**
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MAP_ENTRY__FEATURE = 3;

  /**
   * The feature id for the '<em><b>Reference Value</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MAP_ENTRY__REFERENCE_VALUE = 4;

  /**
   * The number of structural features of the '<em>Feature Map Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MAP_ENTRY_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.change.ChangeKind <em>Kind</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.change.ChangeKind
   * @see org.eclipse.emf.ecore.change.impl.ChangePackageImpl#getChangeKind()
   * @generated
   */
  int CHANGE_KIND = 6;

  /**
   * The meta object id for the '<em>EObject To URI Map</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.Map
   * @see org.eclipse.emf.ecore.change.impl.ChangePackageImpl#getEObjectToURIMap()
   * @generated
   */
  int EOBJECT_TO_URI_MAP = 7;

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.change.ChangeDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Description</em>'.
   * @see org.eclipse.emf.ecore.change.ChangeDescription
   * @generated
   */
  EClass getChangeDescription();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.ecore.change.ChangeDescription#getObjectChanges <em>Object Changes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Object Changes</em>'.
   * @see org.eclipse.emf.ecore.change.ChangeDescription#getObjectChanges()
   * @see #getChangeDescription()
   * @generated
   */
  EReference getChangeDescription_ObjectChanges();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.ecore.change.ChangeDescription#getObjectsToDetach <em>Objects To Detach</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Objects To Detach</em>'.
   * @see org.eclipse.emf.ecore.change.ChangeDescription#getObjectsToDetach()
   * @see #getChangeDescription()
   * @generated
   */
  EReference getChangeDescription_ObjectsToDetach();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.change.ChangeDescription#getObjectsToAttach <em>Objects To Attach</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Objects To Attach</em>'.
   * @see org.eclipse.emf.ecore.change.ChangeDescription#getObjectsToAttach()
   * @see #getChangeDescription()
   * @generated
   */
  EReference getChangeDescription_ObjectsToAttach();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.change.ChangeDescription#getResourceChanges <em>Resource Changes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Resource Changes</em>'.
   * @see org.eclipse.emf.ecore.change.ChangeDescription#getResourceChanges()
   * @see #getChangeDescription()
   * @generated
   */
  EReference getChangeDescription_ResourceChanges();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>EObject To Changes Map Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EObject To Changes Map Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyType="org.eclipse.emf.ecore.EObject" keyRequired="true"
   *        valueType="org.eclipse.emf.ecore.change.FeatureChange" valueContainment="true" valueMany="true"
   * @generated
   */
  EClass getEObjectToChangesMapEntry();

  /**
   * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getEObjectToChangesMapEntry()
   * @generated
   */
  EReference getEObjectToChangesMapEntry_Key();

  /**
   * Returns the meta object for the containment reference list '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getEObjectToChangesMapEntry()
   * @generated
   */
  EReference getEObjectToChangesMapEntry_Value();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.change.FeatureChange <em>Feature Change</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Change</em>'.
   * @see org.eclipse.emf.ecore.change.FeatureChange
   * @generated
   */
  EClass getFeatureChange();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.change.FeatureChange#getFeatureName <em>Feature Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Feature Name</em>'.
   * @see org.eclipse.emf.ecore.change.FeatureChange#getFeatureName()
   * @see #getFeatureChange()
   * @generated
   */
  EAttribute getFeatureChange_FeatureName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.change.FeatureChange#getDataValue <em>Data Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Value</em>'.
   * @see org.eclipse.emf.ecore.change.FeatureChange#getDataValue()
   * @see #getFeatureChange()
   * @generated
   */
  EAttribute getFeatureChange_DataValue();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.change.FeatureChange#isSet <em>Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Set</em>'.
   * @see org.eclipse.emf.ecore.change.FeatureChange#isSet()
   * @see #getFeatureChange()
   * @generated
   */
  EAttribute getFeatureChange_Set();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.change.FeatureChange#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.eclipse.emf.ecore.change.FeatureChange#getValue()
   * @see #getFeatureChange()
   * @generated
   */
  EAttribute getFeatureChange_Value();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.ecore.change.FeatureChange#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Feature</em>'.
   * @see org.eclipse.emf.ecore.change.FeatureChange#getFeature()
   * @see #getFeatureChange()
   * @generated
   */
  EReference getFeatureChange_Feature();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.ecore.change.FeatureChange#getReferenceValue <em>Reference Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Reference Value</em>'.
   * @see org.eclipse.emf.ecore.change.FeatureChange#getReferenceValue()
   * @see #getFeatureChange()
   * @generated
   */
  EReference getFeatureChange_ReferenceValue();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.change.FeatureChange#getListChanges <em>List Changes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>List Changes</em>'.
   * @see org.eclipse.emf.ecore.change.FeatureChange#getListChanges()
   * @see #getFeatureChange()
   * @generated
   */
  EReference getFeatureChange_ListChanges();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.change.ListChange <em>List Change</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>List Change</em>'.
   * @see org.eclipse.emf.ecore.change.ListChange
   * @generated
   */
  EClass getListChange();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.change.ListChange#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.eclipse.emf.ecore.change.ListChange#getKind()
   * @see #getListChange()
   * @generated
   */
  EAttribute getListChange_Kind();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.ecore.change.ListChange#getDataValues <em>Data Values</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Data Values</em>'.
   * @see org.eclipse.emf.ecore.change.ListChange#getDataValues()
   * @see #getListChange()
   * @generated
   */
  EAttribute getListChange_DataValues();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.change.ListChange#getIndex <em>Index</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Index</em>'.
   * @see org.eclipse.emf.ecore.change.ListChange#getIndex()
   * @see #getListChange()
   * @generated
   */
  EAttribute getListChange_Index();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.change.ListChange#getMoveToIndex <em>Move To Index</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Move To Index</em>'.
   * @see org.eclipse.emf.ecore.change.ListChange#getMoveToIndex()
   * @see #getListChange()
   * @generated
   */
  EAttribute getListChange_MoveToIndex();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.ecore.change.ListChange#getValues <em>Values</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Values</em>'.
   * @see org.eclipse.emf.ecore.change.ListChange#getValues()
   * @see #getListChange()
   * @generated
   */
  EAttribute getListChange_Values();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.ecore.change.ListChange#getReferenceValues <em>Reference Values</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Reference Values</em>'.
   * @see org.eclipse.emf.ecore.change.ListChange#getReferenceValues()
   * @see #getListChange()
   * @generated
   */
  EReference getListChange_ReferenceValues();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.ecore.change.ListChange#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Feature</em>'.
   * @see org.eclipse.emf.ecore.change.ListChange#getFeature()
   * @see #getListChange()
   * @generated
   */
  EReference getListChange_Feature();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.change.ListChange#getFeatureMapEntryValues <em>Feature Map Entry Values</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Feature Map Entry Values</em>'.
   * @see org.eclipse.emf.ecore.change.ListChange#getFeatureMapEntryValues()
   * @see #getListChange()
   * @generated
   */
  EReference getListChange_FeatureMapEntryValues();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.change.ResourceChange <em>Resource Change</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Resource Change</em>'.
   * @see org.eclipse.emf.ecore.change.ResourceChange
   * @generated
   */
  EClass getResourceChange();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.change.ResourceChange#getResourceURI <em>Resource URI</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Resource URI</em>'.
   * @see org.eclipse.emf.ecore.change.ResourceChange#getResourceURI()
   * @see #getResourceChange()
   * @generated
   */
  EAttribute getResourceChange_ResourceURI();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.change.ResourceChange#getResource <em>Resource</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Resource</em>'.
   * @see org.eclipse.emf.ecore.change.ResourceChange#getResource()
   * @see #getResourceChange()
   * @generated
   */
  EAttribute getResourceChange_Resource();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.change.ResourceChange#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.eclipse.emf.ecore.change.ResourceChange#getValue()
   * @see #getResourceChange()
   * @generated
   */
  EAttribute getResourceChange_Value();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.change.ResourceChange#getListChanges <em>List Changes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>List Changes</em>'.
   * @see org.eclipse.emf.ecore.change.ResourceChange#getListChanges()
   * @see #getResourceChange()
   * @generated
   */
  EReference getResourceChange_ListChanges();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.change.FeatureMapEntry <em>Feature Map Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Map Entry</em>'.
   * @see org.eclipse.emf.ecore.change.FeatureMapEntry
   * @generated
   */
  EClass getFeatureMapEntry();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getFeatureName <em>Feature Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Feature Name</em>'.
   * @see org.eclipse.emf.ecore.change.FeatureMapEntry#getFeatureName()
   * @see #getFeatureMapEntry()
   * @generated
   */
  EAttribute getFeatureMapEntry_FeatureName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getDataValue <em>Data Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Value</em>'.
   * @see org.eclipse.emf.ecore.change.FeatureMapEntry#getDataValue()
   * @see #getFeatureMapEntry()
   * @generated
   */
  EAttribute getFeatureMapEntry_DataValue();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.eclipse.emf.ecore.change.FeatureMapEntry#getValue()
   * @see #getFeatureMapEntry()
   * @generated
   */
  EAttribute getFeatureMapEntry_Value();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Feature</em>'.
   * @see org.eclipse.emf.ecore.change.FeatureMapEntry#getFeature()
   * @see #getFeatureMapEntry()
   * @generated
   */
  EReference getFeatureMapEntry_Feature();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getReferenceValue <em>Reference Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Reference Value</em>'.
   * @see org.eclipse.emf.ecore.change.FeatureMapEntry#getReferenceValue()
   * @see #getFeatureMapEntry()
   * @generated
   */
  EReference getFeatureMapEntry_ReferenceValue();

  /**
   * Returns the meta object for enum '{@link org.eclipse.emf.ecore.change.ChangeKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Kind</em>'.
   * @see org.eclipse.emf.ecore.change.ChangeKind
   * @generated
   */
  EEnum getChangeKind();

  /**
   * Returns the meta object for data type '{@link java.util.Map <em>EObject To URI Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>EObject To URI Map</em>'.
   * @see java.util.Map
   * @model instanceClass="java.util.Map<org.eclipse.emf.ecore.EObject, org.eclipse.emf.common.util.URI>" serializeable="false"
   * @generated
   */
  EDataType getEObjectToURIMap();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ChangeFactory getChangeFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.change.impl.ChangeDescriptionImpl <em>Description</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.change.impl.ChangeDescriptionImpl
     * @see org.eclipse.emf.ecore.change.impl.ChangePackageImpl#getChangeDescription()
     * @generated
     */
    EClass CHANGE_DESCRIPTION = eINSTANCE.getChangeDescription();

    /**
     * The meta object literal for the '<em><b>Object Changes</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CHANGE_DESCRIPTION__OBJECT_CHANGES = eINSTANCE.getChangeDescription_ObjectChanges();

    /**
     * The meta object literal for the '<em><b>Objects To Detach</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CHANGE_DESCRIPTION__OBJECTS_TO_DETACH = eINSTANCE.getChangeDescription_ObjectsToDetach();

    /**
     * The meta object literal for the '<em><b>Objects To Attach</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CHANGE_DESCRIPTION__OBJECTS_TO_ATTACH = eINSTANCE.getChangeDescription_ObjectsToAttach();

    /**
     * The meta object literal for the '<em><b>Resource Changes</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CHANGE_DESCRIPTION__RESOURCE_CHANGES = eINSTANCE.getChangeDescription_ResourceChanges();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.change.impl.EObjectToChangesMapEntryImpl <em>EObject To Changes Map Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.change.impl.EObjectToChangesMapEntryImpl
     * @see org.eclipse.emf.ecore.change.impl.ChangePackageImpl#getEObjectToChangesMapEntry()
     * @generated
     */
    EClass EOBJECT_TO_CHANGES_MAP_ENTRY = eINSTANCE.getEObjectToChangesMapEntry();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EOBJECT_TO_CHANGES_MAP_ENTRY__KEY = eINSTANCE.getEObjectToChangesMapEntry_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EOBJECT_TO_CHANGES_MAP_ENTRY__VALUE = eINSTANCE.getEObjectToChangesMapEntry_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.change.impl.FeatureChangeImpl <em>Feature Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.change.impl.FeatureChangeImpl
     * @see org.eclipse.emf.ecore.change.impl.ChangePackageImpl#getFeatureChange()
     * @generated
     */
    EClass FEATURE_CHANGE = eINSTANCE.getFeatureChange();

    /**
     * The meta object literal for the '<em><b>Feature Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE_CHANGE__FEATURE_NAME = eINSTANCE.getFeatureChange_FeatureName();

    /**
     * The meta object literal for the '<em><b>Data Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE_CHANGE__DATA_VALUE = eINSTANCE.getFeatureChange_DataValue();

    /**
     * The meta object literal for the '<em><b>Set</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE_CHANGE__SET = eINSTANCE.getFeatureChange_Set();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE_CHANGE__VALUE = eINSTANCE.getFeatureChange_Value();

    /**
     * The meta object literal for the '<em><b>Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE_CHANGE__FEATURE = eINSTANCE.getFeatureChange_Feature();

    /**
     * The meta object literal for the '<em><b>Reference Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE_CHANGE__REFERENCE_VALUE = eINSTANCE.getFeatureChange_ReferenceValue();

    /**
     * The meta object literal for the '<em><b>List Changes</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE_CHANGE__LIST_CHANGES = eINSTANCE.getFeatureChange_ListChanges();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.change.impl.ListChangeImpl <em>List Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.change.impl.ListChangeImpl
     * @see org.eclipse.emf.ecore.change.impl.ChangePackageImpl#getListChange()
     * @generated
     */
    EClass LIST_CHANGE = eINSTANCE.getListChange();

    /**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIST_CHANGE__KIND = eINSTANCE.getListChange_Kind();

    /**
     * The meta object literal for the '<em><b>Data Values</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIST_CHANGE__DATA_VALUES = eINSTANCE.getListChange_DataValues();

    /**
     * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIST_CHANGE__INDEX = eINSTANCE.getListChange_Index();

    /**
     * The meta object literal for the '<em><b>Move To Index</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIST_CHANGE__MOVE_TO_INDEX = eINSTANCE.getListChange_MoveToIndex();

    /**
     * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIST_CHANGE__VALUES = eINSTANCE.getListChange_Values();

    /**
     * The meta object literal for the '<em><b>Reference Values</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIST_CHANGE__REFERENCE_VALUES = eINSTANCE.getListChange_ReferenceValues();

    /**
     * The meta object literal for the '<em><b>Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIST_CHANGE__FEATURE = eINSTANCE.getListChange_Feature();

    /**
     * The meta object literal for the '<em><b>Feature Map Entry Values</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIST_CHANGE__FEATURE_MAP_ENTRY_VALUES = eINSTANCE.getListChange_FeatureMapEntryValues();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.change.impl.ResourceChangeImpl <em>Resource Change</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.change.impl.ResourceChangeImpl
     * @see org.eclipse.emf.ecore.change.impl.ChangePackageImpl#getResourceChange()
     * @generated
     */
    EClass RESOURCE_CHANGE = eINSTANCE.getResourceChange();

    /**
     * The meta object literal for the '<em><b>Resource URI</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RESOURCE_CHANGE__RESOURCE_URI = eINSTANCE.getResourceChange_ResourceURI();

    /**
     * The meta object literal for the '<em><b>Resource</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RESOURCE_CHANGE__RESOURCE = eINSTANCE.getResourceChange_Resource();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RESOURCE_CHANGE__VALUE = eINSTANCE.getResourceChange_Value();

    /**
     * The meta object literal for the '<em><b>List Changes</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RESOURCE_CHANGE__LIST_CHANGES = eINSTANCE.getResourceChange_ListChanges();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.change.impl.FeatureMapEntryImpl <em>Feature Map Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.change.impl.FeatureMapEntryImpl
     * @see org.eclipse.emf.ecore.change.impl.ChangePackageImpl#getFeatureMapEntry()
     * @generated
     */
    EClass FEATURE_MAP_ENTRY = eINSTANCE.getFeatureMapEntry();

    /**
     * The meta object literal for the '<em><b>Feature Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE_MAP_ENTRY__FEATURE_NAME = eINSTANCE.getFeatureMapEntry_FeatureName();

    /**
     * The meta object literal for the '<em><b>Data Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE_MAP_ENTRY__DATA_VALUE = eINSTANCE.getFeatureMapEntry_DataValue();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE_MAP_ENTRY__VALUE = eINSTANCE.getFeatureMapEntry_Value();

    /**
     * The meta object literal for the '<em><b>Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE_MAP_ENTRY__FEATURE = eINSTANCE.getFeatureMapEntry_Feature();

    /**
     * The meta object literal for the '<em><b>Reference Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE_MAP_ENTRY__REFERENCE_VALUE = eINSTANCE.getFeatureMapEntry_ReferenceValue();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.change.ChangeKind <em>Kind</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.change.ChangeKind
     * @see org.eclipse.emf.ecore.change.impl.ChangePackageImpl#getChangeKind()
     * @generated
     */
    EEnum CHANGE_KIND = eINSTANCE.getChangeKind();

    /**
     * The meta object literal for the '<em>EObject To URI Map</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.Map
     * @see org.eclipse.emf.ecore.change.impl.ChangePackageImpl#getEObjectToURIMap()
     * @generated
     */
    EDataType EOBJECT_TO_URI_MAP = eINSTANCE.getEObjectToURIMap();

  }

} //ChangePackage
