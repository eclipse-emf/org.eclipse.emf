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
 * $Id: MappingRootImpl.java,v 1.4 2004/12/16 21:55:06 emerks Exp $
 */
package org.eclipse.emf.mapping.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.notify.impl.NotifyingListImpl;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.Disposable;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.mapping.MappedObjectState;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingFactory;
import org.eclipse.emf.mapping.MappingHelper;
import org.eclipse.emf.mapping.MappingPackage;
import org.eclipse.emf.mapping.MappingRoot;
import org.eclipse.emf.mapping.domain.MappingDomain;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.mapping.impl.MappingRootImpl#isOutputReadOnly <em>Output Read Only</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.impl.MappingRootImpl#isTopToBottom <em>Top To Bottom</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.impl.MappingRootImpl#getCommandStack <em>Command Stack</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MappingRootImpl extends MappingImpl implements MappingRoot
{
  /**
   * The default value of the '{@link #isOutputReadOnly() <em>Output Read Only</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOutputReadOnly()
   * @generated
   * @ordered
   */
  protected static final boolean OUTPUT_READ_ONLY_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isOutputReadOnly() <em>Output Read Only</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOutputReadOnly()
   * @generated
   * @ordered
   */
  protected boolean outputReadOnly = OUTPUT_READ_ONLY_EDEFAULT;

  /**
   * The default value of the '{@link #isTopToBottom() <em>Top To Bottom</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTopToBottom()
   * @generated
   * @ordered
   */
  protected static final boolean TOP_TO_BOTTOM_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isTopToBottom() <em>Top To Bottom</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTopToBottom()
   * @generated
   * @ordered
   */
  protected boolean topToBottom = TOP_TO_BOTTOM_EDEFAULT;

  /**
   * The default value of the '{@link #getCommandStack() <em>Command Stack</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCommandStack()
   * @generated
   * @ordered
   */
  protected static final String COMMAND_STACK_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCommandStack() <em>Command Stack</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCommandStack()
   * @generated
   * @ordered
   */
  protected String commandStack = COMMAND_STACK_EDEFAULT;
  
  /**
   * This keeps track of the mapping domain that uses this mapping root.
   */
  protected MappingDomain domain;

  /**
   * This keeps track of whether the output has been modified.
   */
  protected boolean outputDirty = false;

  /**
   * This allows this listen for changes to inputs or outputs.
   */
  protected AdapterImpl mappedObjectListener;

  /**
   * This keeps track of the factory for creating the {@link org.eclipse.emf.mapping.MappedObjectState}.
   */
  protected AdapterFactory mappedObjectStateAdapterFactory;


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   */
  protected MappingRootImpl() 
  {
    super();
    this.mappedObjectStateAdapterFactory = createMappedObjectStateAdapterFactory();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return MappingPackage.eINSTANCE.getMappingRoot();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isOutputReadOnly()
  {
    return outputReadOnly;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOutputReadOnly(boolean newOutputReadOnly)
  {
    boolean oldOutputReadOnly = outputReadOnly;
    outputReadOnly = newOutputReadOnly;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.MAPPING_ROOT__OUTPUT_READ_ONLY, oldOutputReadOnly, outputReadOnly));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isTopToBottom()
  {
    return topToBottom;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTopToBottom(boolean newTopToBottom)
  {
    boolean oldTopToBottom = topToBottom;
    topToBottom = newTopToBottom;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.MAPPING_ROOT__TOP_TO_BOTTOM, oldTopToBottom, topToBottom));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCommandStack()
  {
    return commandStack;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCommandStack(String newCommandStack)
  {
    String oldCommandStack = commandStack;
    commandStack = newCommandStack;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.MAPPING_ROOT__COMMAND_STACK, oldCommandStack, commandStack));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case MappingPackage.MAPPING_ROOT__HELPER:
          if (helper != null)
            msgs = ((InternalEObject)helper).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MappingPackage.MAPPING_ROOT__HELPER, null, msgs);
          return basicSetHelper((MappingHelper)otherEnd, msgs);
        case MappingPackage.MAPPING_ROOT__NESTED:
          return ((InternalEList)getNested()).basicAdd(otherEnd, msgs);
        case MappingPackage.MAPPING_ROOT__NESTED_IN:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, MappingPackage.MAPPING_ROOT__NESTED_IN, msgs);
        default:
          return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
      }
    }
    if (eContainer != null)
      msgs = eBasicRemoveFromContainer(msgs);
    return eBasicSetContainer(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case MappingPackage.MAPPING_ROOT__HELPER:
          return basicSetHelper(null, msgs);
        case MappingPackage.MAPPING_ROOT__NESTED:
          return ((InternalEList)getNested()).basicRemove(otherEnd, msgs);
        case MappingPackage.MAPPING_ROOT__NESTED_IN:
          return eBasicSetContainer(null, MappingPackage.MAPPING_ROOT__NESTED_IN, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs)
  {
    if (eContainerFeatureID >= 0)
    {
      switch (eContainerFeatureID)
      {
        case MappingPackage.MAPPING_ROOT__NESTED_IN:
          return eContainer.eInverseRemove(this, MappingPackage.MAPPING__NESTED, Mapping.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case MappingPackage.MAPPING_ROOT__HELPER:
        return getHelper();
      case MappingPackage.MAPPING_ROOT__NESTED:
        return getNested();
      case MappingPackage.MAPPING_ROOT__NESTED_IN:
        return getNestedIn();
      case MappingPackage.MAPPING_ROOT__INPUTS:
        return getInputs();
      case MappingPackage.MAPPING_ROOT__OUTPUTS:
        return getOutputs();
      case MappingPackage.MAPPING_ROOT__TYPE_MAPPING:
        if (resolve) return getTypeMapping();
        return basicGetTypeMapping();
      case MappingPackage.MAPPING_ROOT__OUTPUT_READ_ONLY:
        return isOutputReadOnly() ? Boolean.TRUE : Boolean.FALSE;
      case MappingPackage.MAPPING_ROOT__TOP_TO_BOTTOM:
        return isTopToBottom() ? Boolean.TRUE : Boolean.FALSE;
      case MappingPackage.MAPPING_ROOT__COMMAND_STACK:
        return getCommandStack();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case MappingPackage.MAPPING_ROOT__HELPER:
        setHelper((MappingHelper)newValue);
        return;
      case MappingPackage.MAPPING_ROOT__NESTED:
        getNested().clear();
        getNested().addAll((Collection)newValue);
        return;
      case MappingPackage.MAPPING_ROOT__NESTED_IN:
        setNestedIn((Mapping)newValue);
        return;
      case MappingPackage.MAPPING_ROOT__INPUTS:
        getInputs().clear();
        getInputs().addAll((Collection)newValue);
        return;
      case MappingPackage.MAPPING_ROOT__OUTPUTS:
        getOutputs().clear();
        getOutputs().addAll((Collection)newValue);
        return;
      case MappingPackage.MAPPING_ROOT__TYPE_MAPPING:
        setTypeMapping((Mapping)newValue);
        return;
      case MappingPackage.MAPPING_ROOT__OUTPUT_READ_ONLY:
        setOutputReadOnly(((Boolean)newValue).booleanValue());
        return;
      case MappingPackage.MAPPING_ROOT__TOP_TO_BOTTOM:
        setTopToBottom(((Boolean)newValue).booleanValue());
        return;
      case MappingPackage.MAPPING_ROOT__COMMAND_STACK:
        setCommandStack((String)newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case MappingPackage.MAPPING_ROOT__HELPER:
        setHelper((MappingHelper)null);
        return;
      case MappingPackage.MAPPING_ROOT__NESTED:
        getNested().clear();
        return;
      case MappingPackage.MAPPING_ROOT__NESTED_IN:
        setNestedIn((Mapping)null);
        return;
      case MappingPackage.MAPPING_ROOT__INPUTS:
        getInputs().clear();
        return;
      case MappingPackage.MAPPING_ROOT__OUTPUTS:
        getOutputs().clear();
        return;
      case MappingPackage.MAPPING_ROOT__TYPE_MAPPING:
        setTypeMapping((Mapping)null);
        return;
      case MappingPackage.MAPPING_ROOT__OUTPUT_READ_ONLY:
        setOutputReadOnly(OUTPUT_READ_ONLY_EDEFAULT);
        return;
      case MappingPackage.MAPPING_ROOT__TOP_TO_BOTTOM:
        setTopToBottom(TOP_TO_BOTTOM_EDEFAULT);
        return;
      case MappingPackage.MAPPING_ROOT__COMMAND_STACK:
        setCommandStack(COMMAND_STACK_EDEFAULT);
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case MappingPackage.MAPPING_ROOT__HELPER:
        return helper != null;
      case MappingPackage.MAPPING_ROOT__NESTED:
        return nested != null && !nested.isEmpty();
      case MappingPackage.MAPPING_ROOT__NESTED_IN:
        return getNestedIn() != null;
      case MappingPackage.MAPPING_ROOT__INPUTS:
        return inputs != null && !inputs.isEmpty();
      case MappingPackage.MAPPING_ROOT__OUTPUTS:
        return outputs != null && !outputs.isEmpty();
      case MappingPackage.MAPPING_ROOT__TYPE_MAPPING:
        return typeMapping != null;
      case MappingPackage.MAPPING_ROOT__OUTPUT_READ_ONLY:
        return outputReadOnly != OUTPUT_READ_ONLY_EDEFAULT;
      case MappingPackage.MAPPING_ROOT__TOP_TO_BOTTOM:
        return topToBottom != TOP_TO_BOTTOM_EDEFAULT;
      case MappingPackage.MAPPING_ROOT__COMMAND_STACK:
        return COMMAND_STACK_EDEFAULT == null ? commandStack != null : !COMMAND_STACK_EDEFAULT.equals(commandStack);
    }
    return eDynamicIsSet(eFeature);
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
    result.append(" (outputReadOnly: ");
    result.append(outputReadOnly);
    result.append(", topToBottom: ");
    result.append(topToBottom);
    result.append(", commandStack: ");
    result.append(commandStack);
    result.append(')');
    return result.toString();
  }

  public MappingDomain getDomain() 
  {
    return domain;
  }

  public void setDomain(MappingDomain domain) 
  {
    if (this.domain != domain)
    {
      if (this.domain != null)
      {
        eAdapters.remove(mappedObjectListener);
      }

      this.domain = domain;
      domain.setMappingRoot(this);

      mappedObjectListener = 
        new AdapterImpl()
        {
          public void notifyChanged(Notification notification)
          {
            Object feature = notification.getFeature();
            if (feature == MappingPackage.eINSTANCE.getMapping_Inputs() || feature == MappingPackage.eINSTANCE.getMapping_Outputs())
            {
              initializeMappedObjectStates();
            }
          }
        };
        eAdapters().add(mappedObjectListener);

      initializeMappedObjectStates();
    }
  }

  public void refreshMappedObjectStates(Mapping subtree)
  {
    for (Iterator inputs = subtree.getInputs().iterator(); inputs.hasNext(); )
    {
      for (Iterator objects = domain.treeIterator(inputs.next()); objects.hasNext(); )
      {
        Object object = objects.next();
        MappedObjectState mappedObjectState = getMappedObjectState(object);
        if (mappedObjectState != null)
        {
          mappedObjectState.setInput();
        }
      }
    }

    for (Iterator outputs = subtree.getOutputs().iterator(); outputs.hasNext(); )
    {
      for (Iterator objects = domain.treeIterator(outputs.next()); objects.hasNext(); )
      {
        MappedObjectState mappedObjectState = getMappedObjectState(objects.next());
        if (mappedObjectState != null)
        {
          mappedObjectState.setOutput();
        }
      }
    }

    for (Iterator mappings = subtree.treeIterator(); mappings.hasNext(); )
    {
      Mapping mapping = (Mapping)mappings.next();
      for (Iterator inputs = mapping.getInputs().iterator(); inputs.hasNext(); )
      {
        Object input = inputs.next();
        MappedObjectState mappedObjectState = getMappedObjectState(input);
        if (mappedObjectState != null)
        {
          mappedObjectState.getMappings().add(mapping);
        }
      }

      for (Iterator outputs = mapping.getOutputs().iterator(); outputs.hasNext(); )
      {
        Object output = outputs.next();
        MappedObjectState mappedObjectState = getMappedObjectState(output);
        if (mappedObjectState != null)
        {
          mappedObjectState.getMappings().add(mapping);
        }
      }
    }
  }

  protected void initializeMappedObjectStates()
  {
    refreshMappedObjectStates(this);

    if (getTypeMapping() instanceof MappingRootImpl)
    {
      ((MappingRootImpl)getTypeMapping()).initializeMappedObjectStates();
    }
  }

  public Mapping getParentMapping(Collection collection)
  {
    // Barring a better result, this will be the result.
    //
    Mapping result = this;

    // Cache the tree path for each object.
    //
    final Collection allTreePaths = new ArrayList();
    for (Iterator objects = collection.iterator(); objects.hasNext(); )
    {
      allTreePaths.add(domain.getTreePath(objects.next()));
    }

    // Iterate over the mappings in the tree.
    //
    OuterLoop: for (TreeIterator mappings = treeIterator(); mappings.hasNext(); )
    {
      Mapping mapping = (Mapping)mappings.next();

      // Check to make sure that every object in the collection has an ancestor that is contained in this mapping.
      //
      for (Iterator treePaths = allTreePaths.iterator(); treePaths.hasNext(); )
      {
        Collection treePath = (Collection)treePaths.next();
        Collection mappedObjects = mapping.getMappedObjects();
        mappedObjects.retainAll(treePath);

        // If the intersection is empty, i.e., no ancestor is in the mapping...
        //
        if (mappedObjects.isEmpty())
        {
          // If this mapping isn't a parent, it's children definitely won't be either.
          //
          mappings.prune();
          continue OuterLoop;
        }
      }

      // Make sure the collections aren't identical...
      //
      Collection mappedObjects = mapping.getMappedObjects();
      if (!collection.containsAll(mappedObjects) || !mappedObjects.containsAll(collection))
      {
        result = mapping;
      }
    }

    return result;
  }

  public boolean isDirty() 
  {
    /**
    if (getRuleList() != null) 
    {
      return getRuleList().isDirty();
    }
    **/
    return false;
  }

  public boolean isOutputDirty() 
  {
    return outputDirty;
  }

  public void register(Mapping mapping)
  {
    for (Iterator inputs = mapping.getInputs().iterator(); inputs.hasNext(); )
    {
      Object input = inputs.next();
      MappedObjectState mappedObjectState = getMappedObjectState(input);
      if (mappedObjectState != null)
      {
        mappedObjectState.getMappings().add(mapping);
      }
    }

    for (Iterator outputs = mapping.getOutputs().iterator(); outputs.hasNext(); )
    {
      Object output = outputs.next();
      MappedObjectState mappedObjectState = getMappedObjectState(output);
      if (mappedObjectState != null)
      {
        mappedObjectState.getMappings().add(mapping);
      }
    }
  }

  public void deregister(Mapping mapping)
  {
    for (Iterator inputs = mapping.getInputs().iterator(); inputs.hasNext(); )
    {
      Object input = inputs.next();
      MappedObjectState mappedObjectState = getMappedObjectState(input);
      if (mappedObjectState != null)
      {
        mappedObjectState.getMappings().remove(mapping);
      }
    }

    for (Iterator outputs = mapping.getOutputs().iterator(); outputs.hasNext(); )
    {
      Object output = outputs.next();
      MappedObjectState mappedObjectState = getMappedObjectState(output);
      if (mappedObjectState != null)
      {
        mappedObjectState.getMappings().remove(mapping);
      }
    }
  }

  public boolean canCreateMapping(Collection inputs, Collection outputs, Mapping mapping)
  {
    int enablementFlags = domain.getMappingEnablementFlags();
    if ((enablementFlags & MappingDomain.ENABLE_EMPTY_INPUTS) == 0 && inputs.size() == 0 ||
        (enablementFlags & MappingDomain.ENABLE_EMPTY_OUTPUTS) == 0 && outputs.size() == 0 ||
        inputs.size() == 0 && outputs.size() == 0 ||
        (enablementFlags & MappingDomain.ENABLE_MULTIPLE_INPUTS) == 0 && inputs.size() > 1 ||
        (enablementFlags & MappingDomain.ENABLE_MULTIPLE_OUTPUTS) == 0 && outputs.size() > 1 ||
        (enablementFlags & MappingDomain.ENABLE_MULTIPLE_INPUT_MAPPINGS) == 0 && isMapped(inputs, mapping) ||
        (enablementFlags & MappingDomain.ENABLE_MULTIPLE_OUTPUT_MAPPINGS) == 0 && isMapped(outputs, mapping) ||
        (enablementFlags & MappingDomain.ENABLE_UNMAPPED_PARENTS) == 0 && !hasMappedParents(inputs, outputs) ||
        (enablementFlags & MappingDomain.ENABLE_INCOMPATIBLE_METAOBJECTS) == 0 && !hasCompatibleMetaObjects(inputs, outputs) ||
        (enablementFlags & MappingDomain.ENABLE_INCOMPATIBLE_TYPE_CLASSIFIERS) == 0 && !hasCompatibleTypes(inputs, outputs))
    {
      return false;
    }

    for (Iterator i = inputs.iterator(); i.hasNext(); )
    {
      if (!isAttachedObject(i.next()))
      {
        return false;
      }
    }

    for (Iterator i = outputs.iterator(); i.hasNext(); )
    {
      if (!isAttachedObject(i.next()))
      {
        return false;
      }
    }

    return true;
  }

  public boolean canRemoveMapping(Mapping mapping)
  {
    int enablementFlags = domain.getMappingEnablementFlags();
    if (mapping.getNestedIn() == null ||
        (enablementFlags & MappingDomain.ENABLE_UNMAPPED_PARENTS) == 0 && hasMappedChildren(mapping))
    {
      return false;
    }

    return true;
  }

  protected boolean hasMappedChildren(Mapping mapping) 
  {
    return !mapping.getNested().isEmpty();
  }

  protected boolean hasMappedParents(Collection inputs, Collection outputs) 
  {
    Collection parents = new HashSet();
    for (Iterator inputIter = inputs.iterator(); inputIter.hasNext(); )
    {
      parents.add(domain.getParent(inputIter.next()));
    }
    for (Iterator outputIter = outputs.iterator(); outputIter.hasNext(); )
    {
      parents.add(domain.getParent(outputIter.next()));
    }
    return !getAllMappings(parents).isEmpty();
  }

  protected boolean isMapped(Collection collection, Mapping mapping) 
  {
    for (Iterator objects = collection.iterator(); objects.hasNext(); )
    {
      Collection mappings = getMappings(objects.next());
      if (!mappings.isEmpty())
      {
        if (mapping == null || mappings.size() > 1 || !mappings.contains(mapping))
        {
          return true;
        }
      }
    }
    return false;
  }

  protected boolean hasCompatibleMetaObjects(Collection inputs, Collection outputs)
  {
    for (Iterator inputIter = inputs.iterator(); inputIter.hasNext(); )
    {
      EObject inputType = ((EObject)inputIter.next()).eClass();
      EObject convertedInputType = domain.getOutputMetaObject(inputType);
      for (Iterator outputIter = outputs.iterator(); outputIter.hasNext(); )
      {
        EObject outputType = ((EObject)outputIter.next()).eClass();
        if (convertedInputType != outputType)
        {
          return false;
        }
      }
    }
    return true;
  }

  protected boolean hasCompatibleTypes(Collection inputs, Collection outputs)
  {
    MappingRoot typeMappingRoot = getTypeMappingRoot();
    if (typeMappingRoot != null)
    {
      Collection inputTypes = getTypeClassifiers(inputs);
      Collection outputTypes = getTypeClassifiers(outputs);

      if (inputTypes.equals(outputTypes))
        return true;

      if (inputTypes.size() != inputs.size() || outputTypes.size() != outputs.size())
        return false;

      if (getTypeMappings(inputTypes, outputTypes).isEmpty() &&
          hasTypeMappings(inputTypes) && hasTypeMappings(outputTypes))
      {
        return false;
      }
    }
    return true;
  }

  protected Collection getTypeMappings(Collection inputTypes, Collection outputTypes)
  {
    Collection typeMappings;
    if (outputTypes.isEmpty())
    {
      typeMappings = getTypeMappingRoot().getAllMappings(inputTypes);
    }
    else
    {
      Collection allTypes = new ArrayList(inputTypes);
      allTypes.addAll(outputTypes);
      typeMappings = getTypeMappingRoot().getExactMappings(allTypes);
    }
    return typeMappings;
  }

  protected boolean hasTypeMappings(Collection types)
  {
    return !getTypeMappingRoot().getAllMappings(types).isEmpty();
  }

  protected Collection getTypeClassifiers(Collection collection)
  {
    Collection types = new ArrayList();
    for (Iterator iter = collection.iterator(); iter.hasNext(); )
    {
      Object type = domain.getTypeClassifier(iter.next());
      if (type != null)
      {
        types.add(type);
      }
    }
    return types;
  }

  public Mapping createMapping(Collection inputs, Collection outputs)
  {
    Mapping newMapping = createMapping();
    initializeNewMapping(newMapping, inputs, outputs);
    return newMapping;
  }

  protected Mapping createMapping()
  {
    return MappingFactory.eINSTANCE.createMapping();
  }

  protected void initializeNewMapping(Mapping newMapping, Collection inputs, Collection outputs)
  {
    newMapping.getInputs().addAll(inputs);
    newMapping.getOutputs().addAll(outputs);

    if (getTypeMappingRoot() != null)
    {
      Collection inputTypes = getTypeClassifiers(inputs);
      if (!inputTypes.isEmpty())
      {
        Collection outputTypes = getTypeClassifiers(outputs);

        Collection typeMappings = getTypeMappings(inputTypes, outputTypes);
        if (!typeMappings.isEmpty())
        {
          newMapping.setTypeMapping((Mapping)typeMappings.iterator().next());
        }
      }
    }
  }

  public void resetDirty() 
  {
    this.setOutputDirty(false);
  }

  public void setOutputDirty(boolean dirty) 
  {
    outputDirty = dirty;
  }

  public boolean isInputObject(Object object)
  {
    MappedObjectState mappedObjectState = (MappedObjectState)mappedObjectStateAdapterFactory.adapt(object, MappedObjectState.class);
    return 
      mappedObjectState != null  && mappedObjectState.isInput();
  }

  public boolean isOutputObject(Object object)
  {
    MappedObjectState mappedObjectState = (MappedObjectState)mappedObjectStateAdapterFactory.adapt(object, MappedObjectState.class);
    return 
      mappedObjectState != null  && mappedObjectState.isOutput();
  }

  public boolean isTopObject(Object object)
  {
    MappedObjectState mappedObjectState = (MappedObjectState)mappedObjectStateAdapterFactory.adapt(object, MappedObjectState.class);
    if (mappedObjectState != null)
      return isTopToBottom() ? mappedObjectState.isInput() : mappedObjectState.isOutput();
    return false;
  }

  public boolean isBottomObject(Object object)
  {
    MappedObjectState mappedObjectState = (MappedObjectState)mappedObjectStateAdapterFactory.adapt(object, MappedObjectState.class);
    if (mappedObjectState != null)
      return !isTopToBottom() ? mappedObjectState.isInput() : mappedObjectState.isOutput();
    return false;
  }

  public boolean isAttachedObject(Object object)
  {
    Object root = object;
    //FB used to check for parent != null.
    // Need to find the top most model object not including the resource.
    // parent instanceof Eobject only checks for trees in model object space.
    for (Object parent = domain.getParent(object); parent instanceof EObject; parent = domain.getParent(parent))
    {
      root = parent;
    }

    return getInputs().contains(root) || getOutputs().contains(root);
  }

  public Collection getMappings(Object object) 
  {
    MappedObjectState mappedObjectState = getMappedObjectState(object);
    return 
      mappedObjectState == null ?
        Collections.EMPTY_SET :
        mappedObjectState.getMappings();
  }

  public Collection getAllMappings(Collection collection) 
  {
    Iterator objects = collection.iterator(); 
    if (objects.hasNext())
    {
      Collection result = new ArrayList(getMappings(objects.next()));
      while (objects.hasNext() && !result.isEmpty())
      {
        result.retainAll(getMappings(objects.next()));
      }
      return result;
    }
    else
    {
      return Collections.EMPTY_SET;
    }
  }

  public Collection getExactMappings(Collection collection)
  {
    Collection result = new ArrayList();
    for (Iterator mappings = getAllMappings(collection).iterator(); mappings.hasNext(); )
    {
      Mapping mapping = (Mapping)mappings.next();
      if (collection.containsAll(mapping.getMappedObjects()))
      {
        result.add(mapping);
      }
    }

    return result;
  }

  /**
   * This is a simple implementation of the basic information that needs to be maintained for any mapped object.
   */
  protected class MappedObjectStateAdapter extends AdapterImpl implements MappedObjectState, IDisposable
  {
    /**
     * This indicates whether the mapped object is an input.
     */
    protected boolean isInput;

    /**
     * This indicates whether the object is an output.
     */
    protected boolean isOutput;

    /**
     * This keeps track of the originating input of the mapped object, if any.
     */
    protected Object originatingInput;

    /**
     * This keeps track of all the mappings that involve the mapped object.
     */
    protected Collection mappings = 
      new NotifyingListImpl()
      {
        public Object getNotifier()
        {
          return getTarget();
        }

        protected boolean isNotificationRequired()
        {
          return true;
        }

        protected NotificationImpl createNotification(int eventType, Object oldObject, Object newObject, int index, boolean wasSet)
        {
          Object object = oldObject == null ? newObject : oldObject;
          // Ensure that this is a touch notification so to resource aren't marked as dirty.
          //
          ENotificationImpl notification = 
            new ENotificationImpl
              ((InternalEObject)getTarget(),
               Notification.SET, 
               Notification.NO_FEATURE_ID - 1,
               object,
               object,
               Notification.NO_INDEX,
               wasSet);
          MappedObjectStateAdapter.this.fireNotifyChanged(notification);
          return notification;
        }

        protected boolean isUnique()
        {
          return true;
        }
      };

    /**
     * This is where {@link org.eclipse.emf.edit.provider.IChangeNotifier} is delegated.
     */
    protected ChangeNotifier changeNotifier = new ChangeNotifier(); 
   
    /**
     * This returns when type is the {@link #mappedObjectStateAdapterFactory}.
     */
    public boolean isAdapterForType(Object type)
    {
      return type == mappedObjectStateAdapterFactory;
    }

    public boolean isInput()
    {
      return isInput;
    }

    public void setInput()
    {
      isInput = true;
    }

    public boolean isOutput()
    {
      return isOutput;
    }

    public void setOutput()
    {
      isOutput = true;
      ENotificationImpl note = 
         new ENotificationImpl
           ((InternalEObject)getTarget(),
            Notification.SET, 
            Notification.NO_FEATURE_ID - 2,
            Boolean.TRUE,
            Boolean.TRUE,
            Notification.NO_INDEX);
      changeNotifier.fireNotifyChanged(note);
    }

    public Object getOriginatingInput()
    {
      return originatingInput;
    }

    public void setOriginatingInput(Object originatingInput)
    {
      setOutput();
      this.originatingInput = originatingInput;
    }

    public Collection getMappings()
    {
      return mappings;
    }

    public void addListener(INotifyChangedListener notifyChangedListener)
    {
      changeNotifier.addListener(notifyChangedListener);
    }
  
    public void removeListener(INotifyChangedListener notifyChangedListener)
    {
      changeNotifier.removeListener(notifyChangedListener);
    }

    public void fireNotifyChanged(Notification notification)
    {
      changeNotifier.fireNotifyChanged(notification);
    }

    public void dispose()
    {
      if (target != null)
      {
        target.eAdapters().remove(this);
      }
    }
  }

  /**
   * This uses the {@link #mappedObjectStateAdapterFactory} to get an adapter that implements this interface.
   */
  public MappedObjectState getMappedObjectState(Object object)
  {
    return (MappedObjectState)mappedObjectStateAdapterFactory.adapt(object, MappedObjectState.class);
  }

  public MappingRoot getTypeMappingRoot()
  {
    return (MappingRoot)getTypeMapping();
  }

  /**
   * By default, this creates an adapter factory that delegates {@link AdapterFactoryImpl#createAdapter(Notifier) createAdapter}
   * to {@link #createMappedObjectStateAdapter createMappedObjectStateAdapter}.
   */
  protected AdapterFactory createMappedObjectStateAdapterFactory()
  {
    return new MappedObjectStateAdapterFactory();
  }

  /**
   * This is the factory that creates adapters for the objects being mapped.
   * It must be disposed if the lifetime of the mapped objects is longer than the lifetime of the mapping root.
   */
  protected class MappedObjectStateAdapterFactory extends AdapterFactoryImpl implements IDisposable
  {
    protected Disposable disposable = new Disposable();

    public MappedObjectStateAdapterFactory()
    {
    }

    public Adapter createAdapter(Notifier target) 
    {
      return createMappedObjectStateAdapter(target);
    }

    public boolean isFactoryForType(Object type)
    {
      return super.isFactoryForType(type) || type == MappedObjectState.class;
    }

    public Adapter adapt(Notifier notifier, Object type)
    {
      return super.adapt(notifier, this);
    }

    public Object adapt(Object object, Object type)
    {
      Object result = super.adapt(object, type);
      return 
        result instanceof MappedObjectState ? result : null;
    }

    public Adapter adaptNew(Notifier object, Object type)
    {
      Adapter result = super.adaptNew(object, type);
      disposable.add(result);
      return result;
    }

    public void dispose()
    {
      disposable.dispose();
    }
  }

  /**
   * By default, this creates a new instance of {@link MappedObjectStateAdapter}.
   */
  protected Adapter createMappedObjectStateAdapter(Notifier target)
  {
    return new MappedObjectStateAdapter();
  }

  public void dispose()
  {
    if (mappedObjectStateAdapterFactory instanceof IDisposable)
    {
      ((IDisposable)mappedObjectStateAdapterFactory).dispose();
    }

    MappingRoot typeMappingRoot = getTypeMappingRoot();
    if (typeMappingRoot != null)
    {
      typeMappingRoot.dispose();
    }

    // printAdapters();
  }

  protected void printAdapters()
  {
    walk(this);
    for (TreeIterator mappings = treeIterator(); mappings.hasNext(); )
    {
      for (Iterator objects = ((Mapping)mappings.next()).getMappedObjects().iterator(); objects.hasNext(); )
      {
        walk((EObject)objects.next());
      }
    }
  }

  protected void walk(EObject object)
  {

    for (Iterator iterator = object.eContents().iterator(); iterator.hasNext(); )
    {
      EObject child = (EObject)iterator.next();
      Collection adapters = child.eAdapters();
      if (adapters != null)
      {
        boolean once = false;
        for (Iterator i = adapters.iterator();  i.hasNext(); )
        {
          Object adapter = i.next();
          if (adapter != null)
          {
            if (!once)
            {
              System.out.println("*** " + child);
              once = true;
            }
            System.out.println("  * " + adapter);
          }
        }
      }
      walk(child);
    }
  }

} //MappingRootImpl
