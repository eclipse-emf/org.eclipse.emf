/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Enum</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumImpl#isTypeSafeEnumCompatible <em>Type Safe Enum Compatible</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumImpl#getEcoreEnum <em>Ecore Enum</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumImpl#getGenEnumLiterals <em>Gen Enum Literals</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenEnumImpl extends GenDataTypeImpl implements GenEnum
{
  /**
   * The default value of the '{@link #isTypeSafeEnumCompatible() <em>Type Safe Enum Compatible</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTypeSafeEnumCompatible()
   * @generated
   * @ordered
   */
  protected static final boolean TYPE_SAFE_ENUM_COMPATIBLE_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isTypeSafeEnumCompatible() <em>Type Safe Enum Compatible</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTypeSafeEnumCompatible()
   * @generated
   * @ordered
   */
  protected boolean typeSafeEnumCompatible = TYPE_SAFE_ENUM_COMPATIBLE_EDEFAULT;

  /**
   * The cached value of the '{@link #getEcoreEnum() <em>Ecore Enum</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEcoreEnum()
   * @generated
   * @ordered
   */
  protected EEnum ecoreEnum;

  /**
   * The cached value of the '{@link #getGenEnumLiterals() <em>Gen Enum Literals</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenEnumLiterals()
   * @generated
   * @ordered
   */
  protected EList<GenEnumLiteral> genEnumLiterals;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  protected GenEnumImpl() 
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return GenModelPackage.Literals.GEN_ENUM;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isTypeSafeEnumCompatible()
  {
    return typeSafeEnumCompatible;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTypeSafeEnumCompatible(boolean newTypeSafeEnumCompatible)
  {
    boolean oldTypeSafeEnumCompatible = typeSafeEnumCompatible;
    typeSafeEnumCompatible = newTypeSafeEnumCompatible;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_ENUM__TYPE_SAFE_ENUM_COMPATIBLE, oldTypeSafeEnumCompatible, typeSafeEnumCompatible));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getEcoreEnum()
  {
    if (ecoreEnum != null && ecoreEnum.eIsProxy())
    {
      InternalEObject oldEcoreEnum = (InternalEObject)ecoreEnum;
      ecoreEnum = (EEnum)eResolveProxy(oldEcoreEnum);
      if (ecoreEnum != oldEcoreEnum)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenModelPackage.GEN_ENUM__ECORE_ENUM, oldEcoreEnum, ecoreEnum));
      }
    }
    return ecoreEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum basicGetEcoreEnum()
  {
    return ecoreEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEcoreEnum(EEnum newEcoreEnum)
  {
    EEnum oldEcoreEnum = ecoreEnum;
    ecoreEnum = newEcoreEnum;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_ENUM__ECORE_ENUM, oldEcoreEnum, ecoreEnum));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GenEnumLiteral> getGenEnumLiterals()
  {
    if (genEnumLiterals == null)
    {
      genEnumLiterals = new EObjectContainmentWithInverseEList<GenEnumLiteral>(GenEnumLiteral.class, this, GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS, GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM);
    }
    return genEnumLiterals;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getGenEnumLiterals()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS:
        return ((InternalEList<?>)getGenEnumLiterals()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case GenModelPackage.GEN_ENUM__TYPE_SAFE_ENUM_COMPATIBLE:
        return isTypeSafeEnumCompatible();
      case GenModelPackage.GEN_ENUM__ECORE_ENUM:
        if (resolve) return getEcoreEnum();
        return basicGetEcoreEnum();
      case GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS:
        return getGenEnumLiterals();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_ENUM__TYPE_SAFE_ENUM_COMPATIBLE:
        setTypeSafeEnumCompatible((Boolean)newValue);
        return;
      case GenModelPackage.GEN_ENUM__ECORE_ENUM:
        setEcoreEnum((EEnum)newValue);
        return;
      case GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS:
        getGenEnumLiterals().clear();
        getGenEnumLiterals().addAll((Collection<? extends GenEnumLiteral>)newValue);
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
      case GenModelPackage.GEN_ENUM__TYPE_SAFE_ENUM_COMPATIBLE:
        setTypeSafeEnumCompatible(TYPE_SAFE_ENUM_COMPATIBLE_EDEFAULT);
        return;
      case GenModelPackage.GEN_ENUM__ECORE_ENUM:
        setEcoreEnum((EEnum)null);
        return;
      case GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS:
        getGenEnumLiterals().clear();
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
      case GenModelPackage.GEN_ENUM__TYPE_SAFE_ENUM_COMPATIBLE:
        return typeSafeEnumCompatible != TYPE_SAFE_ENUM_COMPATIBLE_EDEFAULT;
      case GenModelPackage.GEN_ENUM__ECORE_ENUM:
        return ecoreEnum != null;
      case GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS:
        return genEnumLiterals != null && !genEnumLiterals.isEmpty();
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
    result.append(" (typeSafeEnumCompatible: ");
    result.append(typeSafeEnumCompatible);
    result.append(')');
    return result.toString();
  }

  @Override
  public EClassifier getEcoreClassifier()
  {
    return getEcoreEnum();
  }

  @Override
  protected EModelElement basicGetEcoreModelElement()
  {
    return ecoreEnum;
  }

  @Override
  public EDataType getEcoreDataType()
  {
    EDataType result = super.getEcoreDataType();
    return result != null ? result : getEcoreEnum();
  }

  @Override
  public String getImportedMetaType()
  {
    return getGenModel().getImportedName("org.eclipse.emf.ecore.EEnum");
  }

  public String getQualifiedName()
  {
    return getGenPackage().getInterfacePackageName() + "." + getName();
  }

  public String getImportedName()
  {
    return getGenModel().getImportedName(getQualifiedName());
  }

  @Override
  public String getRawQualifiedInstanceClassName()
  {
    return getGenPackage().getInterfacePackageName() + "." + getName();
  }

/*
  public String getImportedInstanceClassName()
  {
    return getGenModel().getImportedName("org.eclipse.emf.ecore.EEnumLiteral"); 
  }
*/

  public String getEnumLiteralID(GenEnumLiteral genEnumLiteral)
  {
    return getClassifierID() + "__" + genEnumLiteral.getEnumLiteralID();
  }

  public int getEnumLiteralValue(GenEnumLiteral genEnumLiteral)
  {
    return getGenEnumLiterals().indexOf(genEnumLiteral);
  }

  public GenEnumLiteral getGenEnumLiteral(String literal)
  {
    for (GenEnumLiteral genEnumLiteral : getGenEnumLiterals())
    {
      if (genEnumLiteral.getLiteral().equals(literal))
      {
        return genEnumLiteral;
      }
    }
    return getGenEnumLiterals().isEmpty() ? null : (GenEnumLiteral)getGenEnumLiterals().get(0);
  }

  public List<GenEnumLiteral> getUniqueValuedGenEnumLiterals()
  {
    List<GenEnumLiteral> result = new ArrayList<GenEnumLiteral>();
    Set<Integer> resultSet = new HashSet<Integer>();

    for (GenEnumLiteral genEnumLiteral : getGenEnumLiterals())
    {
      if (resultSet.add(genEnumLiteral.getValue()))
      {
        result.add(genEnumLiteral);
      }      
    }
    return result;
  }

  @Override
  public boolean isSerializable()
  {
    return true;
  }

  public void initialize(EEnum eEnum)
  {
    if (eEnum != getEcoreEnum())
    {
      setTypeSafeEnumCompatible(false);
      setEcoreEnum(eEnum);
    }

    int localEnumLiteralIndex = 0;
    LOOP:
    for (EEnumLiteral eEnumLiteral : eEnum.getELiterals())
    {
      for (GenEnumLiteral genEnumLiteral : getGenEnumLiterals())
      {
        if (genEnumLiteral.getEcoreEnumLiteral() == eEnumLiteral)
        {
          genEnumLiteral.initialize(eEnumLiteral);
          getGenEnumLiterals().move(localEnumLiteralIndex++, genEnumLiteral);
          continue LOOP;
        }
      }

      GenEnumLiteral genEnumLiteral = getGenModel().createGenEnumLiteral();
      getGenEnumLiterals().add(localEnumLiteralIndex++, genEnumLiteral);
      genEnumLiteral.initialize(eEnumLiteral);
    }
  }

  @Override
  protected boolean hasModelContribution()
  {
    return true;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  @Override
  public void generate(Monitor progressMonitor)
  {
    try
    {
      if (!canGenerate()) return;

      progressMonitor.beginTask("", 1);
      progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString
           ("_UI_Generating_message", new Object [] { getFormattedName() }));
      progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString
           ("_UI_GeneratingJavaClass_message", new Object [] { getGenPackage().getInterfacePackageName() + "." + getName() }));
      generate
        (createMonitor(progressMonitor, 1), 
         Generator.EMF_MODEL_PROJECT_STYLE, 
         getGenModel().getEffectiveModelPluginVariables(),
         getGenModel().getModelDirectory(), 
         getGenPackage().getInterfacePackageName(), 
         getName(), 
         getGenModel().getEnumClassEmitter());
    }
    finally
    {
      progressMonitor.done();
    }
  }

  @Override
  public boolean canGenerateEdit()
  {
    return false;
  }

  @Override
  public boolean canGenerateEditor()
  {
    return false;
  }

  public boolean reconcile(GenEnum oldGenEnumVersion)
  {
    if (getEcoreEnum().getName().equals(oldGenEnumVersion.getEcoreEnum().getName()))
    {
      for (GenEnumLiteral genEnumLiteral : getGenEnumLiterals())
      {
        for (GenEnumLiteral oldGenEnumLiteralVersion : oldGenEnumVersion.getGenEnumLiterals())
        {
          if (genEnumLiteral.reconcile(oldGenEnumLiteralVersion))
          {
            break;
          }
        }
      }
      reconcileSettings(oldGenEnumVersion);
      return true;
    }
    else
    {
      return false;
    }
  }

  protected void reconcileSettings(GenEnum oldGenEnumVersion)
  {
    setTypeSafeEnumCompatible(oldGenEnumVersion.isTypeSafeEnumCompatible());
    reconcileGenAnnotations(oldGenEnumVersion);
  }

  @Override
  public boolean reconcile()
  {
    try
    {
      EEnum eEnum = getEcoreEnum();
      if (eEnum == null || eEnum.eIsProxy() || eEnum.eResource() == null)
      {
        return false;
      }
      else
      {
        for (Iterator<GenEnumLiteral> i = getGenEnumLiterals().iterator(); i.hasNext(); )
        {
          GenEnumLiteral genEnumLiteral = i.next();
          if (!genEnumLiteral.reconcile())
          {
            i.remove();
          }
        }
  
        return true;
      }
    }
    catch (RuntimeException exception)
    {
      return false;
    }
  }

  @Override
  public String getStaticValue(String literal)
  {
    GenEnumLiteral genEnumLiteral = getGenEnumLiteral(literal);
    return genEnumLiteral == null ? "null" : getImportedName() + "." + genEnumLiteral.getEnumLiteralInstanceConstantName();
  }

} //GenEnumImpl
