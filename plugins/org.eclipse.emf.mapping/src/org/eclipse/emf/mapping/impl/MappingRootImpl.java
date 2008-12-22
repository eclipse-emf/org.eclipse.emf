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
 * $Id: MappingRootImpl.java,v 1.11 2008/12/22 14:26:20 emerks Exp $
 */
package org.eclipse.emf.mapping.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.notify.impl.NotifyingListImpl;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.Disposable;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.mapping.MappedObjectState;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingFactory;
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
  @Override
  protected EClass eStaticClass()
  {
    return MappingPackage.Literals.MAPPING_ROOT;
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
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case MappingPackage.MAPPING_ROOT__OUTPUT_READ_ONLY:
        return isOutputReadOnly();
      case MappingPackage.MAPPING_ROOT__TOP_TO_BOTTOM:
        return isTopToBottom();
      case MappingPackage.MAPPING_ROOT__COMMAND_STACK:
        return getCommandStack();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case MappingPackage.MAPPING_ROOT__OUTPUT_READ_ONLY:
        setOutputReadOnly((Boolean)newValue);
        return;
      case MappingPackage.MAPPING_ROOT__TOP_TO_BOTTOM:
        setTopToBottom((Boolean)newValue);
        return;
      case MappingPackage.MAPPING_ROOT__COMMAND_STACK:
        setCommandStack((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
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
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case MappingPackage.MAPPING_ROOT__OUTPUT_READ_ONLY:
        return outputReadOnly != OUTPUT_READ_ONLY_EDEFAULT;
      case MappingPackage.MAPPING_ROOT__TOP_TO_BOTTOM:
        return topToBottom != TOP_TO_BOTTOM_EDEFAULT;
      case MappingPackage.MAPPING_ROOT__COMMAND_STACK:
        return COMMAND_STACK_EDEFAULT == null ? commandStack != null : !COMMAND_STACK_EDEFAULT.equals(commandStack);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
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
          @Override
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
    for (Object input : subtree.getInputs())
    {
      for (Iterator<?> objects = domain.treeIterator(input); objects.hasNext(); )
      {
        Object object = objects.next();
        MappedObjectState mappedObjectState = getMappedObjectState(object);
        if (mappedObjectState != null)
        {
          mappedObjectState.setInput();
        }
      }
    }

    for (Object output : subtree.getOutputs())
    {
      for (Iterator<?> objects = domain.treeIterator(output); objects.hasNext(); )
      {
        MappedObjectState mappedObjectState = getMappedObjectState(objects.next());
        if (mappedObjectState != null)
        {
          mappedObjectState.setOutput();
        }
      }
    }

    for (Iterator<Mapping> mappings = subtree.treeIterator(); mappings.hasNext(); )
    {
      Mapping mapping = mappings.next();
      for (Object input : mapping.getInputs())
      {
        MappedObjectState mappedObjectState = getMappedObjectState(input);
        if (mappedObjectState != null)
        {
          mappedObjectState.getMappings().add(mapping);
        }
      }

      for (Object output : mapping.getOutputs())
      {
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

  public Mapping getParentMapping(Collection<?> collection)
  {
    // Barring a better result, this will be the result.
    //
    Mapping result = this;

    // Cache the tree path for each object.
    //
    final Collection<List<?>> allTreePaths = new ArrayList<List<?>>();
    for (Object object : collection)
    {
      allTreePaths.add(domain.getTreePath(object));
    }

    // Iterate over the mappings in the tree.
    //
    OuterLoop: for (TreeIterator<Mapping> mappings = treeIterator(); mappings.hasNext(); )
    {
      Mapping mapping = mappings.next();

      // Check to make sure that every object in the collection has an ancestor that is contained in this mapping.
      //
      for (Iterator<List<?>> treePaths = allTreePaths.iterator(); treePaths.hasNext(); )
      {
        List<?> treePath = treePaths.next();
        Collection<?> mappedObjects = mapping.getMappedObjects();
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
      Collection<?> mappedObjects = mapping.getMappedObjects();
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
    for (Object input : mapping.getInputs())
    {
      MappedObjectState mappedObjectState = getMappedObjectState(input);
      if (mappedObjectState != null)
      {
        mappedObjectState.getMappings().add(mapping);
      }
    }

    for (Object output : mapping.getOutputs())
    {
      MappedObjectState mappedObjectState = getMappedObjectState(output);
      if (mappedObjectState != null)
      {
        mappedObjectState.getMappings().add(mapping);
      }
    }
  }

  public void deregister(Mapping mapping)
  {
    for (Object input : mapping.getInputs())
    {
      MappedObjectState mappedObjectState = getMappedObjectState(input);
      if (mappedObjectState != null)
      {
        mappedObjectState.getMappings().remove(mapping);
      }
    }

    for (Object output : mapping.getOutputs())
    {
      MappedObjectState mappedObjectState = getMappedObjectState(output);
      if (mappedObjectState != null)
      {
        mappedObjectState.getMappings().remove(mapping);
      }
    }
  }

  public boolean canCreateMapping(Collection<?> inputs, Collection<?> outputs, Mapping mapping)
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

    for (Object input : inputs)
    {
      if (!isAttachedObject(input))
      {
        return false;
      }
    }

    for (Object output :  outputs)
    {
      if (!isAttachedObject(output))
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

  protected boolean hasMappedParents(Collection<?> inputs, Collection<?> outputs) 
  {
    Collection<Object> parents = new HashSet<Object>();
    for (Object input : inputs)
    {
      parents.add(domain.getParent(input));
    }
    for (Object output : outputs)
    {
      parents.add(domain.getParent(output));
    }
    return !getAllMappings(parents).isEmpty();
  }

  protected boolean isMapped(Collection<?> collection, Mapping mapping) 
  {
    for (Object object : collection)
    {
      Collection<? extends Mapping> mappings = getMappings(object);
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

  protected boolean hasCompatibleMetaObjects(Collection<?> inputs, Collection<?> outputs)
  {
    for (Object input : inputs)
    {
      EObject inputType = ((EObject)input).eClass();
      EObject convertedInputType = domain.getOutputMetaObject(inputType);
      for (Object output : outputs)
      {
        EObject outputType = ((EObject)output).eClass();
        if (convertedInputType != outputType)
        {
          return false;
        }
      }
    }
    return true;
  }

  protected boolean hasCompatibleTypes(Collection<?> inputs, Collection<?> outputs)
  {
    MappingRoot typeMappingRoot = getTypeMappingRoot();
    if (typeMappingRoot != null)
    {
      Collection<Object> inputTypes = getTypeClassifiers(inputs);
      Collection<Object> outputTypes = getTypeClassifiers(outputs);

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

  protected Collection<?> getTypeMappings(Collection<?> inputTypes, Collection<?> outputTypes)
  {
    Collection<?> typeMappings;
    if (outputTypes.isEmpty())
    {
      typeMappings = getTypeMappingRoot().getAllMappings(inputTypes);
    }
    else
    {
      Collection<Object> allTypes = new ArrayList<Object>(inputTypes);
      allTypes.addAll(outputTypes);
      typeMappings = getTypeMappingRoot().getExactMappings(allTypes);
    }
    return typeMappings;
  }

  protected boolean hasTypeMappings(Collection<?> types)
  {
    return !getTypeMappingRoot().getAllMappings(types).isEmpty();
  }

  protected Collection<Object> getTypeClassifiers(Collection<?> collection)
  {
    Collection<Object> types = new ArrayList<Object>();
    for (Object object : collection)
    {
      Object type = domain.getTypeClassifier(object);
      if (type != null)
      {
        types.add(type);
      }
    }
    return types;
  }

  public Mapping createMapping(Collection<?> inputs, Collection<?> outputs)
  {
    Mapping newMapping = createMapping();
    initializeNewMapping(newMapping, inputs, outputs);
    return newMapping;
  }

  protected Mapping createMapping()
  {
    return MappingFactory.eINSTANCE.createMapping();
  }

  protected void initializeNewMapping(Mapping newMapping, Collection<?> inputs, Collection<?> outputs)
  {
    @SuppressWarnings("unchecked")
    Collection<EObject> eObjectInputs = (Collection<EObject>)inputs;
    @SuppressWarnings("unchecked")
    Collection<EObject> eObjectOutputs = (Collection<EObject>)outputs;
    newMapping.getInputs().addAll(eObjectInputs);
    newMapping.getOutputs().addAll(eObjectOutputs);

    if (getTypeMappingRoot() != null)
    {
      Collection<?> inputTypes = getTypeClassifiers(inputs);
      if (!inputTypes.isEmpty())
      {
        Collection<?> outputTypes = getTypeClassifiers(outputs);

        Collection<?> typeMappings = getTypeMappings(inputTypes, outputTypes);
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

  public Collection<? extends Mapping> getMappings(Object object) 
  {
    MappedObjectState mappedObjectState = getMappedObjectState(object);
    if (mappedObjectState == null)
    {
      return Collections.emptySet();
    }
    else
    {
      return  mappedObjectState.getMappings();
    }
  }

  public Collection<? extends Mapping> getAllMappings(Collection<?> collection) 
  {
    Iterator<?> objects = collection.iterator(); 
    if (objects.hasNext())
    {
      Collection<Mapping> result = new ArrayList<Mapping>(getMappings(objects.next()));
      while (objects.hasNext() && !result.isEmpty())
      {
        result.retainAll(getMappings(objects.next()));
      }
      return result;
    }
    else
    {
      return Collections.emptySet();
    }
  }

  public Collection<? extends Mapping> getExactMappings(Collection<?> collection)
  {
    Collection<Mapping> result = new ArrayList<Mapping>();
    for (Mapping mapping : getAllMappings(collection))
    {
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
    protected Collection<Mapping> mappings = 
      new NotifyingListImpl<Mapping>()
      {
        private static final long serialVersionUID = 1L;

        @Override
        public Object getNotifier()
        {
          return getTarget();
        }

        @Override
        protected boolean isNotificationRequired()
        {
          return true;
        }

        @Override
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

        @Override
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
    @Override
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

    public Collection<Mapping> getMappings()
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
      super();
    }

    @Override
    public Adapter createAdapter(Notifier target) 
    {
      return createMappedObjectStateAdapter(target);
    }

    @Override
    public boolean isFactoryForType(Object type)
    {
      return super.isFactoryForType(type) || type == MappedObjectState.class;
    }

    @Override
    public Adapter adapt(Notifier notifier, Object type)
    {
      return super.adapt(notifier, this);
    }

    @Override
    public Object adapt(Object object, Object type)
    {
      Object result = super.adapt(object, type);
      return 
        result instanceof MappedObjectState ? result : null;
    }

    @Override
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
    for (TreeIterator<Mapping> mappings = treeIterator(); mappings.hasNext(); )
    {
      for (EObject eObject : mappings.next().getMappedObjects())
      {
        walk(eObject);
      }
    }
  }

  protected void walk(EObject object)
  {
    for (EObject child : object.eContents())
    {
      Collection<Adapter> adapters = child.eAdapters();
      if (adapters != null)
      {
        boolean once = false;
        for (Object adapter : adapters)
        {
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
