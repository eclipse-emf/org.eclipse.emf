/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: XMLNamespaceValidator.java,v 1.1 2005/09/23 17:46:43 emerks Exp $
 */
package org.eclipse.emf.ecore.xml.namespace.util;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.emf.ecore.xml.namespace.*;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage
 * @generated
 */
public class XMLNamespaceValidator extends EObjectValidator
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final XMLNamespaceValidator INSTANCE = new XMLNamespaceValidator();

  /**
   * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.common.util.Diagnostic#getSource()
   * @see org.eclipse.emf.common.util.Diagnostic#getCode()
   * @generated
   */
  public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.ecore.xml.namespace";

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
  public XMLNamespaceValidator()
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
  protected EPackage getEPackage()
  {
    return XMLNamespacePackage.eINSTANCE;
  }

  /**
   * Calls <code>validateXXX</code> for the corresonding classifier of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map context)
  {
    switch (classifierID)
    {
      case XMLNamespacePackage.XML_NAMESPACE_DOCUMENT_ROOT:
        return validateXMLNamespaceDocumentRoot((XMLNamespaceDocumentRoot)value, diagnostics, context);
      case XMLNamespacePackage.SPACE_TYPE:
        return validateSpaceType((Object)value, diagnostics, context);
      case XMLNamespacePackage.LANG_TYPE:
        return validateLangType((String)value, diagnostics, context);
      case XMLNamespacePackage.LANG_TYPE_NULL:
        return validateLangTypeNull((String)value, diagnostics, context);
      case XMLNamespacePackage.SPACE_TYPE_OBJECT:
        return validateSpaceTypeObject((SpaceType)value, diagnostics, context);
      default: 
        return true;
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateXMLNamespaceDocumentRoot(XMLNamespaceDocumentRoot xmlNamespaceDocumentRoot, DiagnosticChain diagnostics, Map context)
  {
    return validate_EveryDefaultConstraint(xmlNamespaceDocumentRoot, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateSpaceType(Object spaceType, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateLangType(String langType, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateLangType_MemberTypes(langType, diagnostics, context);
    return result;
  }

  /**
   * Validates the MemberTypes constraint of '<em>Lang Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateLangType_MemberTypes(String langType, DiagnosticChain diagnostics, Map context)
  {
    if (diagnostics != null)
    {
      BasicDiagnostic tempDiagnostics = new BasicDiagnostic();
      if (XMLTypePackage.eINSTANCE.getLanguage().isInstance(langType))
      {
        if (xmlTypeValidator.validateLanguage((String)langType, tempDiagnostics, context)) return true;
      }
      if (XMLNamespacePackage.eINSTANCE.getLangTypeNull().isInstance(langType))
      {
        if (validateLangTypeNull((String)langType, tempDiagnostics, context)) return true;
      }
      List children = tempDiagnostics.getChildren();
      for (int i = 0; i < children.size(); i++)
      {
        diagnostics.add((Diagnostic)children.get(i));
      }
    }
    else
    {
      if (XMLTypePackage.eINSTANCE.getLanguage().isInstance(langType))
      {
        if (xmlTypeValidator.validateLanguage((String)langType, null, context)) return true;
      }
      if (XMLNamespacePackage.eINSTANCE.getLangTypeNull().isInstance(langType))
      {
        if (validateLangTypeNull((String)langType, null, context)) return true;
      }
    }
    return false;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateLangTypeNull(String langTypeNull, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateSpaceTypeObject(SpaceType spaceTypeObject, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

} //XMLNamespaceValidator
