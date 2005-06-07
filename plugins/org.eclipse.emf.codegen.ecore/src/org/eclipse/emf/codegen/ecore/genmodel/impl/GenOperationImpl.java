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
 * $Id: GenOperationImpl.java,v 1.12 2005/06/07 21:20:24 davidms Exp $
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
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
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
public class GenOperationImpl extends GenTypedElementImpl implements GenOperation
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

  public ETypedElement getEcoreTypedElement()
  {
    return getEcoreOperation();
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

  // Many inherited methods must be overridden to handle this case.
  //
  public boolean isVoid()
  {
    return getEcoreOperation().getEType() == null;
  }

  /**
   * @deprecated As of EMF 2.1, simply call <code>getEcoreTypedElement().getEType()</code>.
   */
  protected EClassifier getReturn()
  {
    return getEcoreOperation().getEType();
  }

  public String getTypeClassifierAccessorName()
  {
    return isVoid() ? "null" : super.getTypeClassifierAccessorName();
  }

  public GenPackage getTypeGenPackage()
  {
    return isVoid() ? null : super.getTypeGenPackage();
  }

  public String getType()
  {
    return isVoid() ? "void" : super.getType();
  }

  public String getImportedType()
  {
    return isVoid() ? "void" : super.getImportedType();
  }

  public String getObjectType()
  {
    return isVoid() ? "void" : super.getObjectType();
  }

  public String getImportedInternalType()
  {
    return isVoid() ? "void" : super.getImportedInternalType();
  }

  public boolean isFeatureMapType()
  {
    return !isVoid() && super.isFeatureMapType();
  }

  public String getListItemType()
  {
    return isVoid() ? "void" : super.getListItemType();
  }

  public String getQualifiedListItemType()
  {
    return isVoid() ? "void" : super.getQualifiedListItemType();
  }

  public boolean isPrimitiveType()
  {
    return !isVoid() && super.isPrimitiveType();
  }

  public String getPrimitiveValueFunction()
  {
    return isVoid() ? null : super.getPrimitiveValueFunction();
  }

  public boolean isStringType()
  {
    return !isVoid() && super.isStringType();
  }

  public boolean isStringBasedType()
  {
    return !isVoid() && super.isStringBasedType();
  }

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElementImpl#getTypeClassifierAccessorName() getTypeClassifierAccessorName} instead.
   */
  public String getReturnTypeClassifier()
  {
    return getTypeClassifierAccessorName();
  }

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElementImpl#getTypeGenPackage getTypeGenPackage} instead.
   */
  public GenPackage getReturnTypeGenPackage()
  {
    return getTypeGenPackage();
  }

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElementImpl#getType getType} instead.
   */
  public String getReturnType()
  {
    return getType();
  }

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElementImpl#getImporterType getImportedType} instead.
   */
  public String getImportedReturnType()
  {
    return getImportedType();
  }

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElementImpl#getObjectType getObjectType} instead.
   */
  public String getObjectReturnType()
  {
    return getObjectType();
  }

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElementImpl#isPrimitiveType isPrimitiveType} instead.
   */
  public boolean isPrimitiveReturnType()
  {
    return isPrimitiveType();
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
    return getParameterTypes(separator, true);
  }

  public String getParameterTypes(String separator, boolean qualified)
  {
    StringBuffer parameterTypes = new StringBuffer();

    for (Iterator genParameters = getGenParameters().iterator(); genParameters.hasNext();)
    {
      GenParameter genParameter = (GenParameter)genParameters.next();
      String type = genParameter.getType();

      if (!qualified)
      {
        int firstBracket = type.indexOf("[");
        if (firstBracket != -1)
        {
          type = type.substring(0, firstBracket);
        }

        type = type.substring(type.lastIndexOf(".") + 1);

        int firstDollar = type.indexOf("$");
        if (firstDollar != -1)
        {
          type = type.substring(0, firstDollar);
        }
      }

      parameterTypes.append(type);

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
    EOperation eOperation = getEcoreOperation();
    StringBuffer result = new StringBuffer();

    if (!isVoid())
    {
      // Since we only use this right on an operation, we always want to include the keyType and valueType, and we never
      // want to qualify the property names.
      //
      String mapModelInfo = getMapModelInfo(false, true);
      if (mapModelInfo != null)
      {
        result.append(mapModelInfo);
      }
      else
      {
        if (eOperation.isMany() && !isFeatureMapType())
        {
          appendModelSetting(result, "type", getType(eOperation.getEType(), false));
        }

        EClassifier type = eOperation.getEType();
        if (type instanceof EDataType && !(type instanceof EEnum))
        {
          GenPackage genPackage = findGenPackage(type.getEPackage());
          if (genPackage != null && (isFeatureMapType() || !genPackage.isEcorePackage()))
          {
            appendModelSetting(result, "dataType", genPackage.getInterfacePackageName() + '.' + type.getName());
          }
        }
        
        if (!eOperation.isUnique())
        {
          appendModelSetting(result, "unique", "false");
        }

        result.append(getMultiplicityModelInfo(false));
      }

      if (!eOperation.isOrdered())
      {
        appendModelSetting(result, "ordered", "false");
      }
    }

    // If this looks like a feature getter, a dummy parameters property will allow it to be recognized properly.
    //
    if (getGenParameters().isEmpty() &&
        ((getName().startsWith("get") && getName().length() > 3 && Character.isUpperCase(getName().charAt(3))) ||
         (getName().startsWith("is") && getName().length() > 2 && Character.isUpperCase(getName().charAt(2)))))
    {
      appendModelSetting(result, "parameters", "");
    }

    for (Iterator i = getGenParameters().iterator(); i.hasNext(); )
    {
      String parameterResult = ((GenParameter)i.next()).getQualifiedModelInfo();
      if (parameterResult.length() > 0)
      {
        result.append(parameterResult);
        result.append(' ');
      }
    }

    appendAnnotationInfo(result, eOperation);  
    return result.toString().trim();
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

  public String getParameterNames(String separator)
  {
    StringBuffer parameterNames = new StringBuffer();
    for (Iterator genParameters = getGenParameters().iterator(); genParameters.hasNext();)
    {
      parameterNames.append(((GenParameter)genParameters.next()).getName());
      if (genParameters.hasNext())
      {
        parameterNames.append(separator);
      }
    }

    return parameterNames.toString();
  }
}
