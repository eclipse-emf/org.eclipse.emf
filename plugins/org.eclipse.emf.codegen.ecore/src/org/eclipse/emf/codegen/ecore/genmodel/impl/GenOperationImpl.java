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
 * $Id: GenOperationImpl.java,v 1.27 2006/12/05 20:29:53 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
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
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenOperationImpl#getGenTypeParameters <em>Gen Type Parameters</em>}</li>
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
   * The cached value of the '{@link #getGenTypeParameters() <em>Gen Type Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenTypeParameters()
   * @generated
   * @ordered
   */
  protected EList genTypeParameters = null;

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
    return GenModelPackage.Literals.GEN_OPERATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenClass getGenClass()
  {
    if (eContainerFeatureID != GenModelPackage.GEN_OPERATION__GEN_CLASS) return null;
    return (GenClass)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetGenClass(GenClass newGenClass, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newGenClass, GenModelPackage.GEN_OPERATION__GEN_CLASS, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGenClass(GenClass newGenClass)
  {
    if (newGenClass != eInternalContainer() || (eContainerFeatureID != GenModelPackage.GEN_OPERATION__GEN_CLASS && newGenClass != null))
    {
      if (EcoreUtil.isAncestor(this, newGenClass))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newGenClass != null)
        msgs = ((InternalEObject)newGenClass).eInverseAdd(this, GenModelPackage.GEN_CLASS__GEN_OPERATIONS, GenClass.class, msgs);
      msgs = basicSetGenClass(newGenClass, msgs);
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
      InternalEObject oldEcoreOperation = (InternalEObject)ecoreOperation;
      ecoreOperation = (EOperation)eResolveProxy(oldEcoreOperation);
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
  public EList getGenTypeParameters()
  {
    if (genTypeParameters == null)
    {
      genTypeParameters = new EObjectContainmentEList(GenTypeParameter.class, this, GenModelPackage.GEN_OPERATION__GEN_TYPE_PARAMETERS);
    }
    return genTypeParameters;
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
      case GenModelPackage.GEN_OPERATION__GEN_CLASS:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetGenClass((GenClass)otherEnd, msgs);
      case GenModelPackage.GEN_OPERATION__GEN_PARAMETERS:
        return ((InternalEList)getGenParameters()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
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
      case GenModelPackage.GEN_OPERATION__GEN_CLASS:
        return basicSetGenClass(null, msgs);
      case GenModelPackage.GEN_OPERATION__GEN_PARAMETERS:
        return ((InternalEList)getGenParameters()).basicRemove(otherEnd, msgs);
      case GenModelPackage.GEN_OPERATION__GEN_TYPE_PARAMETERS:
        return ((InternalEList)getGenTypeParameters()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID)
    {
      case GenModelPackage.GEN_OPERATION__GEN_CLASS:
        return eInternalContainer().eInverseRemove(this, GenModelPackage.GEN_CLASS__GEN_OPERATIONS, GenClass.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
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
      case GenModelPackage.GEN_OPERATION__GEN_CLASS:
        return getGenClass();
      case GenModelPackage.GEN_OPERATION__ECORE_OPERATION:
        if (resolve) return getEcoreOperation();
        return basicGetEcoreOperation();
      case GenModelPackage.GEN_OPERATION__GEN_PARAMETERS:
        return getGenParameters();
      case GenModelPackage.GEN_OPERATION__GEN_TYPE_PARAMETERS:
        return getGenTypeParameters();
    }
    return super.eGet(featureID, resolve, coreType);
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
      case GenModelPackage.GEN_OPERATION__GEN_TYPE_PARAMETERS:
        getGenTypeParameters().clear();
        getGenTypeParameters().addAll((Collection)newValue);
        return;
    }
    super.eSet(featureID, newValue);
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
      case GenModelPackage.GEN_OPERATION__GEN_CLASS:
        setGenClass((GenClass)null);
        return;
      case GenModelPackage.GEN_OPERATION__ECORE_OPERATION:
        setEcoreOperation((EOperation)null);
        return;
      case GenModelPackage.GEN_OPERATION__GEN_PARAMETERS:
        getGenParameters().clear();
        return;
      case GenModelPackage.GEN_OPERATION__GEN_TYPE_PARAMETERS:
        getGenTypeParameters().clear();
        return;
    }
    super.eUnset(featureID);
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
      case GenModelPackage.GEN_OPERATION__GEN_CLASS:
        return getGenClass() != null;
      case GenModelPackage.GEN_OPERATION__ECORE_OPERATION:
        return ecoreOperation != null;
      case GenModelPackage.GEN_OPERATION__GEN_PARAMETERS:
        return genParameters != null && !genParameters.isEmpty();
      case GenModelPackage.GEN_OPERATION__GEN_TYPE_PARAMETERS:
        return genTypeParameters != null && !genTypeParameters.isEmpty();
    }
    return super.eIsSet(featureID);
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
    return format(getCapName(), ' ', null, false, false);
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
      String type = genParameter.getRawType();

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

      for (int j = 0; j < getGenParameters().size(); ++j)
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

    List typeParameters = eOperation.getETypeParameters();
    LOOP:
    for (int i = 0; i < typeParameters.size(); ++i) 
    {
      ETypeParameter typeParameter = (ETypeParameter)typeParameters.get(i);

      for (int j = 0; j < getGenTypeParameters().size(); ++j)
      {
        GenTypeParameter genTypeParameter = (GenTypeParameter)getGenTypeParameters().get(j);
        if (genTypeParameter.getEcoreTypeParameter() == typeParameter)
        {
          genTypeParameter.initialize(typeParameter);
          if (i != j)
          {
            getGenTypeParameters().move(i, j);
          }

          continue LOOP;
        }
      }

      GenTypeParameter genTypeParameter = getGenModel().createGenTypeParameter();
      getGenTypeParameters().add(genTypeParameter);
      genTypeParameter.initialize(typeParameter);
    }
  }

  public String getModelInfo()
  {
    EOperation eOperation = getEcoreOperation();
    StringBuffer result = new StringBuffer();

    // If this looks like a feature getter, a kind property will allow it to be recognized properly.
    //
    if (getGenParameters().isEmpty() &&
        ((getName().startsWith("get") && getName().length() > 3 && Character.isUpperCase(getName().charAt(3))) ||
         (getName().startsWith("is") && getName().length() > 2 && Character.isUpperCase(getName().charAt(2)))))
    {
      appendModelSetting(result, "kind", "operation");
    }

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
    
    List genExceptions = getGenExceptions();
    if (!genExceptions.isEmpty())
    {
      StringBuffer exceptions = new StringBuffer();
      for (Iterator i = genExceptions.iterator(); i.hasNext(); )
      {
        GenClassifier genClassifier = (GenClassifier)i.next();
        GenPackage genPackage = genClassifier.getGenPackage();
        if (genPackage != null)
        {
          if (exceptions.length() > 0)
          {
            exceptions.append(' ');
          }
          exceptions.append(genPackage.getInterfacePackageName() + "." + genClassifier.getName());
        }
      }
      appendModelSetting(result, "exceptions", exceptions.toString());
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
    if (getEcoreOperation().getName().equals(oldGenOperationVersion.getEcoreOperation().getName()) &&
          getGenParameters().size() == oldGenOperationVersion.getGenParameters().size())
    {
      for (Iterator i = getGenParameters().iterator(), j = oldGenOperationVersion.getGenParameters().iterator(); i.hasNext(); )
      {
        GenParameter genParameter = (GenParameter)i.next();
        GenParameter oldGenParameterVersion = (GenParameter)j.next();
        if (!genParameter.getType().equals(oldGenParameterVersion.getType()))
        {
          return false;
        }
      }
      for (Iterator i = getGenParameters().iterator(), j = oldGenOperationVersion.getGenParameters().iterator(); i.hasNext(); )
      {
        GenParameter genParameter = (GenParameter)i.next();
        GenParameter oldGenParameterVersion = (GenParameter)j.next();
        genParameter.reconcile(oldGenParameterVersion);
      }
      for (Iterator i = getGenTypeParameters().iterator(), j = oldGenOperationVersion.getGenTypeParameters().iterator(); i.hasNext() && j.hasNext(); )
      {
        GenTypeParameter genTypeParameter = (GenTypeParameter)i.next();
        GenTypeParameter oldGenTypeParameterVersion = (GenTypeParameter)j.next();
        genTypeParameter.reconcile(oldGenTypeParameterVersion);
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
    reconcileGenAnnotations(oldGenOperationVersion);
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

      for (Iterator i = getGenTypeParameters().iterator(); i.hasNext(); )
      {
        GenTypeParameter genTypeParameter = (GenTypeParameter)i.next();
        if (!genTypeParameter.reconcile())
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
    List<EGenericType> exceptions = getEcoreOperation().getEGenericExceptions();
    if (exceptions.isEmpty())
    {
      return "";
    }
    else
    {
      StringBuilder result = new StringBuilder(" throws ");
      for (Iterator<EGenericType> i = exceptions.iterator(); i.hasNext(); )
      {
        EGenericType exception = i.next();
        result.append(getImportedType(exception, false));
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

  public boolean isOverrideOf(GenOperation genOperation)
  {
    if (genOperation.getName().equals(getName()))
    {
      List parameters = getGenParameters();
      List otherParameters = genOperation.getGenParameters();
      if (parameters.size() == otherParameters.size())
      {
        for (Iterator i = parameters.iterator(), j = otherParameters.iterator(); i.hasNext(); )
        {
          GenParameter genParameter = (GenParameter)i.next();
          GenParameter otherGenParameter = (GenParameter)j.next();
          if (!genParameter.getType().equals(otherGenParameter.getType()))
          {
            return false;
          }
        }
        return true;
      }
    }
    return false;
  }
  
  public String getTypeParameters()
  {
    if (!getGenTypeParameters().isEmpty() && getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      StringBuilder result = new StringBuilder("<");
      for (Iterator i = getGenTypeParameters().iterator(); i.hasNext(); )
      {
        GenTypeParameter genTypeParameter = (GenTypeParameter)i.next();
        result.append(genTypeParameter.getName());
        List<EGenericType> eBounds = genTypeParameter.getEcoreTypeParameter().getEBounds();
        if (!eBounds.isEmpty())
        {
          result.append(" extends ");
          for (Iterator j = genTypeParameter.getEcoreTypeParameter().getEBounds().iterator(); j.hasNext(); )
          {
            EGenericType eBound = (EGenericType)j.next();
            result.append(getTypeArgument(eBound, true));
            if (j.hasNext())
            {
              result.append(" & ");
            }
          }
        }
        if (i.hasNext())
        {
          result.append(", ");
        }
      }

      result.append("> ");
      return result.toString();
    }
    else
    {
      return "";
    }
  }
  
  public boolean hasGenericExceptions()
  {
    if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      for (EGenericType eGenericType : getEcoreOperation().getEGenericExceptions())
      {
        if (eGenericType.getETypeParameter() != null || !eGenericType.getETypeArguments().isEmpty())
        {
          return true;
        }
      }
    }
    return false;
  }
}
