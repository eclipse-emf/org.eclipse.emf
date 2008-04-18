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
 * $Id: XSDDiagnosticImpl.java,v 1.14 2008/04/18 15:44:13 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.Collection;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectEList;

import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDParser;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagnostic</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDDiagnosticImpl#getSeverity <em>Severity</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDDiagnosticImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDDiagnosticImpl#getLocationURI <em>Location URI</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDDiagnosticImpl#getLine <em>Line</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDDiagnosticImpl#getColumn <em>Column</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDDiagnosticImpl#getNode <em>Node</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDDiagnosticImpl#getAnnotationURI <em>Annotation URI</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDDiagnosticImpl#getKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDDiagnosticImpl#getSubstitutions <em>Substitutions</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDDiagnosticImpl#getComponents <em>Components</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDDiagnosticImpl#getPrimaryComponent <em>Primary Component</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDDiagnosticImpl 
  extends XSDConcreteComponentImpl 
  implements XSDDiagnostic
{
  /**
   * The default value of the '{@link #getSeverity() <em>Severity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSeverity()
   * @generated
   * @ordered
   */
  protected static final XSDDiagnosticSeverity SEVERITY_EDEFAULT = XSDDiagnosticSeverity.FATAL_LITERAL;

  /**
   * The offset of the flags representing the value of the '{@link #getSeverity() <em>Severity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int SEVERITY_EFLAG_OFFSET = 8;

  /**
   * The flags representing the default value of the '{@link #getSeverity() <em>Severity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int SEVERITY_EFLAG_DEFAULT = SEVERITY_EDEFAULT.ordinal() << SEVERITY_EFLAG_OFFSET;

  /**
   * The array of enumeration values for '{@link XSDDiagnosticSeverity Diagnostic Severity}'
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  private static final XSDDiagnosticSeverity[] SEVERITY_EFLAG_VALUES = XSDDiagnosticSeverity.values();

  /**
   * The flags representing the value of the '{@link #getSeverity() <em>Severity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSeverity()
   * @generated
   * @ordered
   */
  protected static final int SEVERITY_EFLAG = 0x3 << SEVERITY_EFLAG_OFFSET;

  /**
   * The default value of the '{@link #getMessage() <em>Message</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessage()
   * @generated
   * @ordered
   */
  protected static final String MESSAGE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMessage() <em>Message</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessage()
   * @generated
   * @ordered
   */
  protected String message = MESSAGE_EDEFAULT;

  /**
   * The default value of the '{@link #getLocationURI() <em>Location URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLocationURI()
   * @generated
   * @ordered
   */
  protected static final String LOCATION_URI_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLocationURI() <em>Location URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLocationURI()
   * @generated
   * @ordered
   */
  protected String locationURI = LOCATION_URI_EDEFAULT;

  /**
   * The default value of the '{@link #getLine() <em>Line</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLine()
   * @generated
   * @ordered
   */
  protected static final int LINE_EDEFAULT = 1;

  /**
   * The cached value of the '{@link #getLine() <em>Line</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLine()
   * @generated
   * @ordered
   */
  protected int line = LINE_EDEFAULT;

  /**
   * The default value of the '{@link #getColumn() <em>Column</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColumn()
   * @generated
   * @ordered
   */
  protected static final int COLUMN_EDEFAULT = 1;

  /**
   * The cached value of the '{@link #getColumn() <em>Column</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColumn()
   * @generated
   * @ordered
   */
  protected int column = COLUMN_EDEFAULT;

  /**
   * The default value of the '{@link #getNode() <em>Node</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNode()
   * @generated
   * @ordered
   */
  protected static final Node NODE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNode() <em>Node</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNode()
   * @generated
   * @ordered
   */
  protected Node node = NODE_EDEFAULT;

  /**
   * The default value of the '{@link #getAnnotationURI() <em>Annotation URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotationURI()
   * @generated
   * @ordered
   */
  protected static final String ANNOTATION_URI_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAnnotationURI() <em>Annotation URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotationURI()
   * @generated
   * @ordered
   */
  protected String annotationURI = ANNOTATION_URI_EDEFAULT;

  /**
   * The default value of the '{@link #getKey() <em>Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKey()
   * @generated
   * @ordered
   */
  protected static final String KEY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getKey() <em>Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKey()
   * @generated
   * @ordered
   */
  protected String key = KEY_EDEFAULT;

  /**
   * The cached value of the '{@link #getSubstitutions() <em>Substitutions</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubstitutions()
   * @generated
   * @ordered
   */
  protected EList<String> substitutions;

  /**
   * The cached value of the '{@link #getComponents() <em>Components</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComponents()
   * @generated
   * @ordered
   */
  protected EList<XSDConcreteComponent> components;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDDiagnosticImpl()
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
    return XSDPackage.Literals.XSD_DIAGNOSTIC;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDDiagnosticSeverity getSeverity()
  {
    return SEVERITY_EFLAG_VALUES[(eFlags & SEVERITY_EFLAG) >>> SEVERITY_EFLAG_OFFSET];
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSeverity(XSDDiagnosticSeverity newSeverity)
  {
    XSDDiagnosticSeverity oldSeverity = SEVERITY_EFLAG_VALUES[(eFlags & SEVERITY_EFLAG) >>> SEVERITY_EFLAG_OFFSET];
    if (newSeverity == null) newSeverity = SEVERITY_EDEFAULT;
    eFlags = eFlags & ~SEVERITY_EFLAG | newSeverity.ordinal() << SEVERITY_EFLAG_OFFSET;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_DIAGNOSTIC__SEVERITY, oldSeverity, newSeverity));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getMessage()
  {
    return message;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMessage(String newMessage)
  {
    String oldMessage = message;
    message = newMessage;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_DIAGNOSTIC__MESSAGE, oldMessage, message));
  }

  public String getLocation() 
  {
    return getLocationURI();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLocationURIGen()
  {
    return locationURI;
  }

  public String getLocationURI() 
  {
    if (getLocationURIGen() == null)
    {
      XSDConcreteComponent thePrimaryComponent = getPrimaryComponent();
      if (thePrimaryComponent != null)
      {
        XSDSchema xsdSchema = thePrimaryComponent.getSchema();
        if (xsdSchema != null)
        {
          return xsdSchema.getSchemaLocation();
        }
      }
    }
    return getLocationURIGen();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLocationURI(String newLocationURI)
  {
    String oldLocationURI = locationURI;
    locationURI = newLocationURI;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_DIAGNOSTIC__LOCATION_URI, oldLocationURI, locationURI));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getLineGen()
  {
    return line;
  }

  public int getLine() 
  {
    Node theNode = getNode();
    if (theNode != null && theNode.getNodeType() == Node.ATTRIBUTE_NODE)
    {
      theNode = ((Attr)theNode).getOwnerElement();
    }
    if (theNode != null && XSDParser.getUserData(theNode) != null)
    {
      return XSDParser.getStartLine(theNode);
    }

    return getLineGen();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLine(int newLine)
  {
    int oldLine = line;
    line = newLine;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_DIAGNOSTIC__LINE, oldLine, line));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getColumnGen()
  {
    return column;
  }

  public int getColumn() 
  {
    Node theNode = getNode();
    if (theNode != null && theNode.getNodeType() == Node.ATTRIBUTE_NODE)
    {
      theNode = ((Attr)theNode).getOwnerElement();
    }
    if (theNode != null && XSDParser.getUserData(theNode) != null)
    {
      return  XSDParser.getStartColumn(theNode);
    }

    return getColumnGen();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setColumn(int newColumn)
  {
    int oldColumn = column;
    column = newColumn;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_DIAGNOSTIC__COLUMN, oldColumn, column));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Node getNode()
  {
    return node;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNode(Node newNode)
  {
    Node oldNode = node;
    node = newNode;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_DIAGNOSTIC__NODE, oldNode, node));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAnnotationURI()
  {
    return annotationURI;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnnotationURI(String newAnnotationURI)
  {
    String oldAnnotationURI = annotationURI;
    annotationURI = newAnnotationURI;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_DIAGNOSTIC__ANNOTATION_URI, oldAnnotationURI, annotationURI));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getKey()
  {
    return key;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKey(String newKey)
  {
    String oldKey = key;
    key = newKey;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_DIAGNOSTIC__KEY, oldKey, key));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getSubstitutions()
  {
    if (substitutions == null)
    {
      substitutions = new EDataTypeUniqueEList<String>(String.class, this, XSDPackage.XSD_DIAGNOSTIC__SUBSTITUTIONS);
    }
    return substitutions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDConcreteComponent> getComponents()
  {
    if (components == null)
    {
      components = new EObjectEList<XSDConcreteComponent>(XSDConcreteComponent.class, this, XSDPackage.XSD_DIAGNOSTIC__COMPONENTS);
    }
    return components;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public XSDConcreteComponent getPrimaryComponent()
  {
    List<XSDConcreteComponent> theComponents = getComponents();
    return 
      theComponents.size() > 0 ?
        theComponents.get(0) :
        null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public void setPrimaryComponent(XSDConcreteComponent primaryComponent)
  {
    List<XSDConcreteComponent> theComponents = getComponents();
    if (primaryComponent == null)
    {
      theComponents.clear();
    }
    else if (theComponents.size() > 0)
    {
      theComponents.set(0, primaryComponent);
    }
    else
    {
      theComponents.add(primaryComponent);
    }
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
      case XSDPackage.XSD_DIAGNOSTIC__SEVERITY:
        return getSeverity();
      case XSDPackage.XSD_DIAGNOSTIC__MESSAGE:
        return getMessage();
      case XSDPackage.XSD_DIAGNOSTIC__LOCATION_URI:
        return getLocationURI();
      case XSDPackage.XSD_DIAGNOSTIC__LINE:
        return new Integer(getLine());
      case XSDPackage.XSD_DIAGNOSTIC__COLUMN:
        return new Integer(getColumn());
      case XSDPackage.XSD_DIAGNOSTIC__NODE:
        return getNode();
      case XSDPackage.XSD_DIAGNOSTIC__ANNOTATION_URI:
        return getAnnotationURI();
      case XSDPackage.XSD_DIAGNOSTIC__KEY:
        return getKey();
      case XSDPackage.XSD_DIAGNOSTIC__SUBSTITUTIONS:
        return getSubstitutions();
      case XSDPackage.XSD_DIAGNOSTIC__COMPONENTS:
        return getComponents();
      case XSDPackage.XSD_DIAGNOSTIC__PRIMARY_COMPONENT:
        return getPrimaryComponent();
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
      case XSDPackage.XSD_DIAGNOSTIC__SEVERITY:
        setSeverity((XSDDiagnosticSeverity)newValue);
        return;
      case XSDPackage.XSD_DIAGNOSTIC__MESSAGE:
        setMessage((String)newValue);
        return;
      case XSDPackage.XSD_DIAGNOSTIC__LOCATION_URI:
        setLocationURI((String)newValue);
        return;
      case XSDPackage.XSD_DIAGNOSTIC__LINE:
        setLine(((Integer)newValue).intValue());
        return;
      case XSDPackage.XSD_DIAGNOSTIC__COLUMN:
        setColumn(((Integer)newValue).intValue());
        return;
      case XSDPackage.XSD_DIAGNOSTIC__NODE:
        setNode((Node)newValue);
        return;
      case XSDPackage.XSD_DIAGNOSTIC__ANNOTATION_URI:
        setAnnotationURI((String)newValue);
        return;
      case XSDPackage.XSD_DIAGNOSTIC__KEY:
        setKey((String)newValue);
        return;
      case XSDPackage.XSD_DIAGNOSTIC__SUBSTITUTIONS:
        getSubstitutions().clear();
        getSubstitutions().addAll((Collection<? extends String>)newValue);
        return;
      case XSDPackage.XSD_DIAGNOSTIC__COMPONENTS:
        getComponents().clear();
        getComponents().addAll((Collection<? extends XSDConcreteComponent>)newValue);
        return;
      case XSDPackage.XSD_DIAGNOSTIC__PRIMARY_COMPONENT:
        setPrimaryComponent((XSDConcreteComponent)newValue);
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
      case XSDPackage.XSD_DIAGNOSTIC__SEVERITY:
        setSeverity(SEVERITY_EDEFAULT);
        return;
      case XSDPackage.XSD_DIAGNOSTIC__MESSAGE:
        setMessage(MESSAGE_EDEFAULT);
        return;
      case XSDPackage.XSD_DIAGNOSTIC__LOCATION_URI:
        setLocationURI(LOCATION_URI_EDEFAULT);
        return;
      case XSDPackage.XSD_DIAGNOSTIC__LINE:
        setLine(LINE_EDEFAULT);
        return;
      case XSDPackage.XSD_DIAGNOSTIC__COLUMN:
        setColumn(COLUMN_EDEFAULT);
        return;
      case XSDPackage.XSD_DIAGNOSTIC__NODE:
        setNode(NODE_EDEFAULT);
        return;
      case XSDPackage.XSD_DIAGNOSTIC__ANNOTATION_URI:
        setAnnotationURI(ANNOTATION_URI_EDEFAULT);
        return;
      case XSDPackage.XSD_DIAGNOSTIC__KEY:
        setKey(KEY_EDEFAULT);
        return;
      case XSDPackage.XSD_DIAGNOSTIC__SUBSTITUTIONS:
        getSubstitutions().clear();
        return;
      case XSDPackage.XSD_DIAGNOSTIC__COMPONENTS:
        getComponents().clear();
        return;
      case XSDPackage.XSD_DIAGNOSTIC__PRIMARY_COMPONENT:
        setPrimaryComponent((XSDConcreteComponent)null);
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
      case XSDPackage.XSD_DIAGNOSTIC__SEVERITY:
        return (eFlags & SEVERITY_EFLAG) != SEVERITY_EFLAG_DEFAULT;
      case XSDPackage.XSD_DIAGNOSTIC__MESSAGE:
        return MESSAGE_EDEFAULT == null ? message != null : !MESSAGE_EDEFAULT.equals(message);
      case XSDPackage.XSD_DIAGNOSTIC__LOCATION_URI:
        return LOCATION_URI_EDEFAULT == null ? locationURI != null : !LOCATION_URI_EDEFAULT.equals(locationURI);
      case XSDPackage.XSD_DIAGNOSTIC__LINE:
        return line != LINE_EDEFAULT;
      case XSDPackage.XSD_DIAGNOSTIC__COLUMN:
        return column != COLUMN_EDEFAULT;
      case XSDPackage.XSD_DIAGNOSTIC__NODE:
        return NODE_EDEFAULT == null ? node != null : !NODE_EDEFAULT.equals(node);
      case XSDPackage.XSD_DIAGNOSTIC__ANNOTATION_URI:
        return ANNOTATION_URI_EDEFAULT == null ? annotationURI != null : !ANNOTATION_URI_EDEFAULT.equals(annotationURI);
      case XSDPackage.XSD_DIAGNOSTIC__KEY:
        return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
      case XSDPackage.XSD_DIAGNOSTIC__SUBSTITUTIONS:
        return substitutions != null && !substitutions.isEmpty();
      case XSDPackage.XSD_DIAGNOSTIC__COMPONENTS:
        return components != null && !components.isEmpty();
      case XSDPackage.XSD_DIAGNOSTIC__PRIMARY_COMPONENT:
        return getPrimaryComponent() != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public void unsetPrimaryComponent() 
  {
    List<XSDConcreteComponent> theComponents = getComponents();
    theComponents.clear();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public boolean isSetPrimaryComponent() 
  {
    List<XSDConcreteComponent> theComponents = getComponents();
    return !theComponents.isEmpty();
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
    result.append(" (severity: ");
    result.append(SEVERITY_EFLAG_VALUES[(eFlags & SEVERITY_EFLAG) >>> SEVERITY_EFLAG_OFFSET]);
    result.append(", message: ");
    result.append(message);
    result.append(", locationURI: ");
    result.append(locationURI);
    result.append(", line: ");
    result.append(line);
    result.append(", column: ");
    result.append(column);
    result.append(", node: ");
    result.append(node);
    result.append(", annotationURI: ");
    result.append(annotationURI);
    result.append(", key: ");
    result.append(key);
    result.append(", substitutions: ");
    result.append(substitutions);
    result.append(')');
    return result.toString();
  }

}
