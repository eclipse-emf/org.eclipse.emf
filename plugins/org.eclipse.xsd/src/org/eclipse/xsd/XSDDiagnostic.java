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
package org.eclipse.xsd;


import org.w3c.dom.Node;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagnostic</b></em>'.
 * @extends Resource.Diagnostic
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDDiagnostic#getSeverity <em>Severity</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDDiagnostic#getMessage <em>Message</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDDiagnostic#getLocationURI <em>Location URI</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDDiagnostic#getLine <em>Line</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDDiagnostic#getColumn <em>Column</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDDiagnostic#getNode <em>Node</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDDiagnostic#getAnnotationURI <em>Annotation URI</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDDiagnostic#getKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDDiagnostic#getSubstitutions <em>Substitutions</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDDiagnostic#getComponents <em>Components</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDDiagnostic#getPrimaryComponent <em>Primary Component</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDDiagnostic()
 * @model
 * @generated
 */
public interface XSDDiagnostic extends XSDConcreteComponent, Resource.Diagnostic
{
  /**
   * This is the ID used for Eclipse markers that are based on diagnostics.
   */
  public static final String MARKER = "org.eclipse.xsd.diagnostic";

  /**
   * This is the string for encoding a {@link Resource#getURIFragment URI fragment} for a diagnostic.
   * @see Resource#getURIFragment
   */
  public static final String URI_FRAGMENT_ATTRIBUTE = "diagnostic";

  /**
   * Returns the value of the '<em><b>Severity</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDDiagnosticSeverity}.
   * <!-- begin-user-doc -->
   * This indicates the how bad the problem is.
   * <!-- end-user-doc -->
   * @return the value of the '<em>Severity</em>' attribute.
   * @see org.eclipse.xsd.XSDDiagnosticSeverity
   * @see #setSeverity(XSDDiagnosticSeverity)
   * @see org.eclipse.xsd.XSDPackage#getXSDDiagnostic_Severity()
   * @model
   * @generated
   */
  XSDDiagnosticSeverity getSeverity();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDDiagnostic#getSeverity <em>Severity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Severity</em>' attribute.
   * @see org.eclipse.xsd.XSDDiagnosticSeverity
   * @see #getSeverity()
   * @generated
   */
  void setSeverity(XSDDiagnosticSeverity value);

  /**
   * Returns the value of the '<em><b>Message</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This describes the problem.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Message</em>' attribute.
   * @see #setMessage(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDDiagnostic_Message()
   * @model
   * @generated
   */
  String getMessage();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDDiagnostic#getMessage <em>Message</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Message</em>' attribute.
   * @see #getMessage()
   * @generated
   */
  void setMessage(String value);

  /**
   * Returns the value of the '<em><b>Location URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This indicates the URI of the resource containing the problem.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Location URI</em>' attribute.
   * @see #setLocationURI(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDDiagnostic_LocationURI()
   * @model
   * @generated
   */
  String getLocationURI();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDDiagnostic#getLocationURI <em>Location URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Location URI</em>' attribute.
   * @see #getLocationURI()
   * @generated
   */
  void setLocationURI(String value);

  /**
   * Returns the value of the '<em><b>Line</b></em>' attribute.
   * The default value is <code>"1"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * This indicates the line number within the resource containing the problem.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Line</em>' attribute.
   * @see #setLine(int)
   * @see org.eclipse.xsd.XSDPackage#getXSDDiagnostic_Line()
   * @model default="1"
   * @generated
   */
  int getLine();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDDiagnostic#getLine <em>Line</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Line</em>' attribute.
   * @see #getLine()
   * @generated
   */
  void setLine(int value);

  /**
   * Returns the value of the '<em><b>Column</b></em>' attribute.
   * The default value is <code>"1"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * This indicates the column number within the line of the resource containing the problem.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Column</em>' attribute.
   * @see #setColumn(int)
   * @see org.eclipse.xsd.XSDPackage#getXSDDiagnostic_Column()
   * @model default="1"
   * @generated
   */
  int getColumn();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDDiagnostic#getColumn <em>Column</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Column</em>' attribute.
   * @see #getColumn()
   * @generated
   */
  void setColumn(int value);

  /**
   * Returns the value of the '<em><b>Node</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This indicates the DOM node at which the problem occurs.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Node</em>' attribute.
   * @see #setNode(Node)
   * @see org.eclipse.xsd.XSDPackage#getXSDDiagnostic_Node()
   * @model dataType="org.eclipse.xsd.DOMNode"
   * @generated
   */
  Node getNode();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDDiagnostic#getNode <em>Node</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Node</em>' attribute.
   * @see #getNode()
   * @generated
   */
  void setNode(Node value);

  /**
   * Returns the value of the '<em><b>Annotation URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This provides a URI that can be used to locate more detailed documentation of the general type of problem encountered.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation URI</em>' attribute.
   * @see #setAnnotationURI(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDDiagnostic_AnnotationURI()
   * @model
   * @generated
   */
  String getAnnotationURI();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDDiagnostic#getAnnotationURI <em>Annotation URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation URI</em>' attribute.
   * @see #getAnnotationURI()
   * @generated
   */
  void setAnnotationURI(String value);

  /**
   * Returns the value of the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This represents a unique key used to load the message.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Key</em>' attribute.
   * @see #setKey(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDDiagnostic_Key()
   * @model
   * @generated
   */
  String getKey();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDDiagnostic#getKey <em>Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Key</em>' attribute.
   * @see #getKey()
   * @generated
   */
  void setKey(String value);

  /**
   * Returns the value of the '<em><b>Substitutions</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the substitutions within the message.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Substitutions</em>' attribute list.
   * @see org.eclipse.xsd.XSDPackage#getXSDDiagnostic_Substitutions()
   * @model
   * @generated
   */
  EList<String> getSubstitutions();

  /**
   * Returns the value of the '<em><b>Components</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDConcreteComponent}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the components associated with the problem;
   * the first component is al the {@link #getPrimaryComponent() primary} component.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Components</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDDiagnostic_Components()
   * @model resolveProxies="false" required="true"
   * @generated
   */
  EList<XSDConcreteComponent> getComponents();

  /**
   * Returns the value of the '<em><b>Primary Component</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the component most directly associated with the problem.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Primary Component</em>' reference.
   * @see #setPrimaryComponent(XSDConcreteComponent)
   * @see org.eclipse.xsd.XSDPackage#getXSDDiagnostic_PrimaryComponent()
   * @model resolveProxies="false" required="true" transient="true" volatile="true"
   * @generated
   */
  XSDConcreteComponent getPrimaryComponent();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDDiagnostic#getPrimaryComponent <em>Primary Component</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Primary Component</em>' reference.
   * @see #getPrimaryComponent()
   * @generated
   */
  void setPrimaryComponent(XSDConcreteComponent value);

}
