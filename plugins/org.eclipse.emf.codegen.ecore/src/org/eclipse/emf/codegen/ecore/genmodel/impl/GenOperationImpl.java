/**
 * <copyright> 
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id: GenOperationImpl.java,v 1.7 2005/04/04 19:23:46 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenOperationImpl#getGenClass <em>Gen Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenOperationImpl#getEcoreOperation <em>Ecore Operation</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenOperationImpl#getGenParameters <em>Gen Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenOperationImpl extends GenBaseImpl implements GenOperation
{
  /**
   * The cached value of the '{@link #getEcoreOperation() <em>Ecore Operation</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEcoreOperation()
   * @generated
   * @ordered
   */
  protected EOperation ecoreOperation = null;

  /**
   * The cached value of the '{@link #getGenParameters() <em>Gen Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenParameters()
   * @generated
   * @ordered
   */
  protected EList genParameters = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  protected GenOperationImpl() 
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return GenModelPackage.eINSTANCE.getGenOperation();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenClass getGenClass()
  {
    if (eContainerFeatureID != GenModelPackage.GEN_OPERATION__GEN_CLASS) return null;
    return (GenClass)eContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGenClass(GenClass newGenClass)
  {
    if (newGenClass != eContainer || (eContainerFeatureID != GenModelPackage.GEN_OPERATION__GEN_CLASS && newGenClass != null))
    {
      if (EcoreUtil.isAncestor(this, newGenClass))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eContainer != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newGenClass != null)
        msgs = ((InternalEObject)newGenClass).eInverseAdd(this, GenModelPackage.GEN_CLASS__GEN_OPERATIONS, GenClass.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newGenClass, GenModelPackage.GEN_OPERATION__GEN_CLASS, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_OPERATION__GEN_CLASS, newGenClass, newGenClass));
  }

  public  EModelElement getEcoreModelElement()
  {
    return getEcoreOperation();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getEcoreOperation()
  {
    if (ecoreOperation != null && ecoreOperation.eIsProxy())
    {
      EOperation oldEcoreOperation = ecoreOperation;
      ecoreOperation = (EOperation)eResolveProxy((InternalEObject)ecoreOperation);
      if (ecoreOperation != oldEcoreOperation)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenModelPackage.GEN_OPERATION__ECORE_OPERATION, oldEcoreOperation, ecoreOperation));
      }
    }
    return ecoreOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation basicGetEcoreOperation()
  {
    return ecoreOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEcoreOperation(EOperation newEcoreOperation)
  {
    EOperation oldEcoreOperation = ecoreOperation;
    ecoreOperation = newEcoreOperation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_OPERATION__ECORE_OPERATION, oldEcoreOperation, ecoreOperation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getGenParameters()
  {
    if (genParameters == null)
    {
      genParameters = new EObjectContainmentWithInverseEList(GenParameter.class, this, GenModelPackage.GEN_OPERATION__GEN_PARAMETERS, GenModelPackage.GEN_PARAMETER__GEN_OPERATION);
    }
    return genParameters;
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
        case GenModelPackage.GEN_OPERATION__GEN_CLASS:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, GenModelPackage.GEN_OPERATION__GEN_CLASS, msgs);
        case GenModelPackage.GEN_OPERATION__GEN_PARAMETERS:
          return ((InternalEList)getGenParameters()).basicAdd(otherEnd, msgs);
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
        case GenModelPackage.GEN_OPERATION__GEN_CLASS:
          return eBasicSetContainer(null, GenModelPackage.GEN_OPERATION__GEN_CLASS, msgs);
        case GenModelPackage.GEN_OPERATION__GEN_PARAMETERS:
          return ((InternalEList)getGenParameters()).basicRemove(otherEnd, msgs);
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
        case GenModelPackage.GEN_OPERATION__GEN_CLASS:
          return eContainer.eInverseRemove(this, GenModelPackage.GEN_CLASS__GEN_OPERATIONS, GenClass.class, msgs);
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
      case GenModelPackage.GEN_OPERATION__GEN_CLASS:
        return getGenClass();
      case GenModelPackage.GEN_OPERATION__ECORE_OPERATION:
        if (resolve) return getEcoreOperation();
        return basicGetEcoreOperation();
      case GenModelPackage.GEN_OPERATION__GEN_PARAMETERS:
        return getGenParameters();
    }
    return eDynamicGet(eFeature, resolve);
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
      case GenModelPackage.GEN_OPERATION__GEN_CLASS:
        return getGenClass() != null;
      case GenModelPackage.GEN_OPERATION__ECORE_OPERATION:
        return ecoreOperation != null;
      case GenModelPackage.GEN_OPERATION__GEN_PARAMETERS:
        return genParameters != null && !genParameters.isEmpty();
    }
    return eDynamicIsSet(eFeature);
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
      case GenModelPackage.GEN_OPERATION__GEN_CLASS:
        setGenClass((GenClass)newValue);
        return;
      case GenModelPackage.GEN_OPERATION__ECORE_OPERATION:
        setEcoreOperation((EOperation)newValue);
        return;
      case GenModelPackage.GEN_OPERATION__GEN_PARAMETERS:
        getGenParameters().clear();
        getGenParameters().addAll((Collection)newValue);
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
      case GenModelPackage.GEN_OPERATION__GEN_CLASS:
        setGenClass((GenClass)null);
        return;
      case GenModelPackage.GEN_OPERATION__ECORE_OPERATION:
        setEcoreOperation((EOperation)null);
        return;
      case GenModelPackage.GEN_OPERATION__GEN_PARAMETERS:
        getGenParameters().clear();
        return;
    }
    eDynamicUnset(eFeature);
  }

  public String getName()
  {
    return getEcoreOperation().getName();
  }

  public String getCapName()
  {
    return capName(getName());
  }

  public String getFormattedName()
  {
    return format(getCapName(), ' ', null, false);
  }

  public boolean isVoid()
  {
    return getEcoreOperation().getEType() == null;
  }

  protected EClassifier getReturn()
  {
    return getEcoreOperation().getEType();
  }

  public String getReturnType()
  {
    return isVoid() ? "void" : getType(getReturn(), false);
  }

  public String getImportedReturnType()
  {
    return isVoid() ? "void" : getImportedType(getReturn(), false);
  }

  public String getReturnTypeClassifier()
  {
    return isVoid() ? null : findGenClassifier(getReturn()).getClassifierAccessorName();
  }

  public GenPackage getReturnTypeGenPackage()
  {
    EClassifier eType = getReturn();
    if (eType != null)
      return findGenPackage(eType.getEPackage());
    else
      return null;
  }

  public String getParameters()
  {
    return getParameters(true);
  }

  protected String getParameters(boolean formal)
  {
    StringBuffer result = new StringBuffer();
    for (Iterator iter = getGenParameters().iterator(); iter.hasNext(); )
    {
      GenParameter genParameter = (GenParameter)iter.next();
      if (formal)
      {
        result.append(genParameter.getImportedType());
        result.append(' ');
      }
      result.append(genParameter.getName());
      
      if (iter.hasNext()) result.append(", ");
    }
    return result.toString();
  }

  public String getParameterTypes(String separator)
  {
    StringBuffer parameterTypes = new StringBuffer();
    for (Iterator genParameters = getGenParameters().iterator(); genParameters.hasNext();)
    {
      GenParameter genParameter = (GenParameter)genParameters.next();
      parameterTypes.append(genParameter.getImportedType());
      if (genParameters.hasNext())
      {
        parameterTypes.append(separator);
      }
    }
    return parameterTypes.toString();
  }

  public String getImportedMetaType()
  {
    return getGenModel().getImportedName("org.eclipse.emf.ecore.EOperation");
  }

  public GenPackage getGenPackage()
  {
    return getGenClass().getGenPackage();
  }

  public void initialize(EOperation eOperation)
  {
    setEcoreOperation(eOperation);

    List parameters = eOperation.getEParameters();
    LOOP:
    for (int i = 0; i < parameters.size(); ++i) 
    {
      EParameter parameter = (EParameter)parameters.get(i);

      for (int j = 0; i < getGenParameters().size(); ++j)
      {
        GenParameter genParameter = (GenParameter)getGenParameters().get(j);
        if (genParameter.getEcoreParameter() == parameter)
        {
          genParameter.initialize(parameter);
          if (i != j)
          {
            getGenParameters().move(i, j);
          }

          continue LOOP;
        }
      }

      GenParameter genParameter = getGenModel().createGenParameter();
      getGenParameters().add(genParameter);
      genParameter.initialize(parameter);
    }
  }

  public String getModelInfo()
  {
    StringBuffer result = new StringBuffer();

    EClassifier returnTypeEClassifier = getEcoreOperation().getEType();
    if (returnTypeEClassifier != null)
    {
      GenPackage returnTypeGenPackage = findGenPackage(returnTypeEClassifier.getEPackage());
      if (returnTypeGenPackage != null && 
            !returnTypeGenPackage.isEcorePackage() && 
            returnTypeEClassifier instanceof EDataType && 
            !(returnTypeEClassifier instanceof EEnum))
      {
        result.append("dataType=\"");
        result.append(returnTypeGenPackage == null ? "org.eclipse.emf.ecore" : returnTypeGenPackage.getInterfacePackageName());
        result.append('.');
        result.append(returnTypeEClassifier.getName());
        result.append("\" ");
      }
    } 

    StringBuffer parameterResult = new StringBuffer("parameters=\"");
    boolean required = 
      getGenParameters().isEmpty() && 
        (getName().startsWith("get") && 
         getName().length() > 3 && 
         Character.isUpperCase(getName().charAt(3)) ||
          (getName().startsWith("get") && 
           getName().length() > 2 && 
           Character.isUpperCase(getName().charAt(2))));
    for (Iterator i = getGenParameters().iterator(); i.hasNext(); )
    {
      GenParameter genParameter = (GenParameter)i.next();
      EClassifier eClassifier = genParameter.getEcoreParameter().getEType();
      GenPackage genPackage = findGenPackage(eClassifier.getEPackage());
      required |= 
        genPackage != null && 
          !genPackage.isEcorePackage() && 
          eClassifier instanceof EDataType && 
          !(eClassifier instanceof EEnum);
      parameterResult.append(genPackage == null ? "org.eclipse.emf.ecore" : genPackage.getInterfacePackageName());
      parameterResult.append('.');
      parameterResult.append(eClassifier.getName());
      if (i.hasNext())
      {
        parameterResult.append(" ");
      }
    }

    if (required)
    {
      result.append(parameterResult);
      result.append('"');
    }

    appendAnnotationInfo(result, getEcoreOperation());
  
    return result.toString();
  }

  public boolean reconcile(GenOperation oldGenOperationVersion)
  {
    if (getEcoreOperation().getName().equals(oldGenOperationVersion.getEcoreOperation().getName()))
    {
      for (Iterator i = getGenParameters().iterator(); i.hasNext(); )
      {
        GenParameter genParameter = (GenParameter)i.next();
        for (Iterator j = oldGenOperationVersion.getGenParameters().iterator(); j.hasNext(); )
        {
          GenParameter oldGenParameterVersion = (GenParameter)j.next();
          if (genParameter.reconcile(oldGenParameterVersion))
          {
            break;
          }
        }
      }
      reconcileSettings(oldGenOperationVersion);
      return true;
    }
    else
    {
      return false;
    }
  }

  protected void reconcileSettings(GenOperation oldGenOperationVersion)
  {
  }

  public boolean reconcile()
  {
    EOperation eOperation = getEcoreOperation();
    if (eOperation == null || eOperation.eIsProxy() || eOperation.eResource() == null)
    {
      return false;
    }
    else
    {
      for (Iterator i = getGenParameters().iterator(); i.hasNext(); )
      {
        GenParameter genParameter = (GenParameter)i.next();
        if (!genParameter.reconcile())
        {
          i.remove();
        }
      }

      return true;
    }
  }


  protected String getBody()
  {
    EOperation eOperation = getEcoreOperation();
    EAnnotation eAnnotation = eOperation.getEAnnotation(GenModelPackage.eNS_URI);
    return eAnnotation == null ? null : (String)eAnnotation.getDetails().get("body");
  }

  public boolean hasBody()
  {
    return getBody() != null;
  }

  public String getBody(String indentation)
  {
    String body = getBody();
    if (body != null)
    {
      StringBuffer stringBuffer = new StringBuffer(indent(body, indentation));

      for (int i = 0; i < stringBuffer.length(); )
      {
        // EATM in JDK 1.4 there will be an indexOf on StringBuffer.
        //
        String string = stringBuffer.toString();
        int start = string.indexOf("<%", i);
        if (start == -1)
        {
          break;
        }
        else
        {
          int end = string.indexOf("%>", start + 2);
          if (end == -1)
          {
            break;
          }
          else
          {
            String qualifiedName = stringBuffer.substring(start + 2, end);
            String importedName = getGenModel().getImportedName(qualifiedName);
            stringBuffer.replace(start, end + 2, importedName);
            i += importedName.length();
          }
        }
      }

      return stringBuffer.toString();
    }
    else
    {
      return null;
    }
  }

  public List getGenExceptions()
  {
    List result = new ArrayList();
    for (Iterator i = getEcoreOperation().getEExceptions().iterator(); i.hasNext(); )
    {
      result.add(findGenClassifier((EClassifier)i.next()));
    }
    return result;
  }

  public String getThrows()
  {
    List genExceptions = getGenExceptions();
    if (genExceptions.isEmpty())
    {
      return "";
    }
    else
    {
      StringBuffer result = new StringBuffer(" throws ");
      for (Iterator i = genExceptions.iterator(); i.hasNext(); )
      {
        GenClassifier genClassifier = (GenClassifier)i.next();
        result.append(genClassifier.getImportedInstanceClassName());
        if (i.hasNext())
        {
          result.append(", ");
        }
      }
      return result.toString();
    }
  }

  public boolean isInvariant()
  {
    return
      getReturnType() != null && 
        "boolean".equals(getReturnType()) &&
        getGenParameters().size() == 2 &&
        "org.eclipse.emf.common.util.DiagnosticChain".equals
          (((GenParameter)getGenParameters().get(0)).getEcoreParameter().getEType().getInstanceClassName()) &&
        "java.util.Map".equals
          (((GenParameter)getGenParameters().get(1)).getEcoreParameter().getEType().getInstanceClassName());
  }
}
