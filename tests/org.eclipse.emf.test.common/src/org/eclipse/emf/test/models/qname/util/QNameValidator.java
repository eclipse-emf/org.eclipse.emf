/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: QNameValidator.java,v 1.5 2007/06/12 15:08:10 emerks Exp $
 */
package org.eclipse.emf.test.models.qname.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;

import org.eclipse.emf.test.models.qname.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.qname.QNamePackage
 * @generated
 */
public class QNameValidator extends EObjectValidator
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final QNameValidator INSTANCE = new QNameValidator();

  /**
   * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.common.util.Diagnostic#getSource()
   * @see org.eclipse.emf.common.util.Diagnostic#getCode()
   * @generated
   */
  public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.test.models.qname";

  /**
   * A constant with a fixed name that can be used as the base value for additional hand written constants.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

  /**
   * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

  /**
   * The cached base package validator.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XMLTypeValidator xmlTypeValidator;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public QNameValidator()
  {
    super();
    xmlTypeValidator = XMLTypeValidator.INSTANCE;
  }

  /**
   * Returns the package of this validator switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EPackage getEPackage()
  {
    return QNamePackage.eINSTANCE;
  }

  /**
   * Calls <code>validateXXX</code> for the corresponding classifier of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    switch (classifierID)
    {
      case QNamePackage.DOCUMENT_ROOT:
        return validateDocumentRoot((DocumentRoot)value, diagnostics, context);
      case QNamePackage.RESOURCE_TYPE:
        return validateResourceType((ResourceType)value, diagnostics, context);
      case QNamePackage.INT_QNAME_UNION:
        return validateIntQNameUnion(value, diagnostics, context);
      case QNamePackage.LIST_UNION:
        return validateListUnion((List<?>)value, diagnostics, context);
      case QNamePackage.QNAME_LIST:
        return validateQnameList((List<?>)value, diagnostics, context);
      case QNamePackage.UNION:
        return validateUnion(value, diagnostics, context);
      default: 
        return true;
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateDocumentRoot(DocumentRoot documentRoot, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(documentRoot, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateResourceType(ResourceType resourceType, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(resourceType, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateIntQNameUnion(Object intQNameUnion, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result = validateIntQNameUnion_MemberTypes(intQNameUnion, diagnostics, context);
    return result;
  }

  /**
   * Validates the MemberTypes constraint of '<em>Int QName Union</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateIntQNameUnion_MemberTypes(Object intQNameUnion, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    if (diagnostics != null)
    {
      BasicDiagnostic tempDiagnostics = new BasicDiagnostic();
      if (XMLTypePackage.Literals.INT.isInstance(intQNameUnion))
      {
        if (xmlTypeValidator.validateInt(((Integer)intQNameUnion).intValue(), tempDiagnostics, context)) return true;
      }
      if (XMLTypePackage.Literals.QNAME.isInstance(intQNameUnion))
      {
        if (xmlTypeValidator.validateQName((QName)intQNameUnion, tempDiagnostics, context)) return true;
      }
      for (Diagnostic diagnostic : tempDiagnostics.getChildren())
      {
        diagnostics.add(diagnostic);
      }
    }
    else
    {
      if (XMLTypePackage.Literals.INT.isInstance(intQNameUnion))
      {
        if (xmlTypeValidator.validateInt(((Integer)intQNameUnion).intValue(), null, context)) return true;
      }
      if (XMLTypePackage.Literals.QNAME.isInstance(intQNameUnion))
      {
        if (xmlTypeValidator.validateQName((QName)intQNameUnion, null, context)) return true;
      }
    }
    return false;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateListUnion(List<?> listUnion, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result = validateListUnion_ItemType(listUnion, diagnostics, context);
    return result;
  }

  /**
   * Validates the ItemType constraint of '<em>List Union</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateListUnion_ItemType(List<?> listUnion, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result = true;
    for (Iterator<?> i = listUnion.iterator(); i.hasNext() && (result || diagnostics != null); )
    {
      Object item = i.next();
      if (QNamePackage.Literals.UNION.isInstance(item))
      {
        result &= validateUnion(item, diagnostics, context);
      }
      else
      {
        result = false;
        reportDataValueTypeViolation(QNamePackage.Literals.UNION, item, diagnostics, context);
      }
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateQnameList(List<?> qnameList, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result = validateQnameList_ItemType(qnameList, diagnostics, context);
    return result;
  }

  /**
   * Validates the ItemType constraint of '<em>Qname List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateQnameList_ItemType(List<?> qnameList, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result = true;
    for (Iterator<?> i = qnameList.iterator(); i.hasNext() && (result || diagnostics != null); )
    {
      Object item = i.next();
      if (XMLTypePackage.Literals.QNAME.isInstance(item))
      {
        result &= xmlTypeValidator.validateQName((QName)item, diagnostics, context);
      }
      else
      {
        result = false;
        reportDataValueTypeViolation(XMLTypePackage.Literals.QNAME, item, diagnostics, context);
      }
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUnion(Object union, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result = validateUnion_MemberTypes(union, diagnostics, context);
    return result;
  }

  /**
   * Validates the MemberTypes constraint of '<em>Union</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUnion_MemberTypes(Object union, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    if (diagnostics != null)
    {
      BasicDiagnostic tempDiagnostics = new BasicDiagnostic();
      if (XMLTypePackage.Literals.BOOLEAN.isInstance(union))
      {
        if (xmlTypeValidator.validateBoolean(((Boolean)union).booleanValue(), tempDiagnostics, context)) return true;
      }
      if (QNamePackage.Literals.INT_QNAME_UNION.isInstance(union))
      {
        if (validateIntQNameUnion(union, tempDiagnostics, context)) return true;
      }
      for (Diagnostic diagnostic : tempDiagnostics.getChildren())
      {
        diagnostics.add(diagnostic);
      }
    }
    else
    {
      if (XMLTypePackage.Literals.BOOLEAN.isInstance(union))
      {
        if (xmlTypeValidator.validateBoolean(((Boolean)union).booleanValue(), null, context)) return true;
      }
      if (QNamePackage.Literals.INT_QNAME_UNION.isInstance(union))
      {
        if (validateIntQNameUnion(union, null, context)) return true;
      }
    }
    return false;
  }

} //QNameValidator
