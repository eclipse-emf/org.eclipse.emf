/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: XSDAnnotationImpl.java,v 1.9 2006/12/05 20:32:13 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Annotation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDAnnotationImpl#getApplicationInformation <em>Application Information</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAnnotationImpl#getUserInformation <em>User Information</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAnnotationImpl#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDAnnotationImpl 
  extends XSDComponentImpl 
  implements XSDAnnotation 
{
  /**
   * The cached value of the '{@link #getApplicationInformation() <em>Application Information</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getApplicationInformation()
   * @generated
   * @ordered
   */
  protected EList<Element> applicationInformation = null;

  /**
   * The cached value of the '{@link #getUserInformation() <em>User Information</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUserInformation()
   * @generated
   * @ordered
   */
  protected EList<Element> userInformation = null;

  /**
   * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttributes()
   * @generated
   * @ordered
   */
  protected EList<Attr> attributes = null;

  public static XSDAnnotation createAnnotation(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.ANNOTATION_ELEMENT)
    {
      XSDAnnotation xsdAnnotation = XSDFactory.eINSTANCE.createXSDAnnotation();
      xsdAnnotation.setElement((Element)node);
      return xsdAnnotation;
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDAnnotationImpl()
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
    return XSDPackage.Literals.XSD_ANNOTATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Element> getApplicationInformation()
  {
    if (applicationInformation == null)
    {
      applicationInformation = new EDataTypeUniqueEList<Element>(Element.class, this, XSDPackage.XSD_ANNOTATION__APPLICATION_INFORMATION);
    }
    return applicationInformation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Element> getUserInformation()
  {
    if (userInformation == null)
    {
      userInformation = new EDataTypeUniqueEList<Element>(Element.class, this, XSDPackage.XSD_ANNOTATION__USER_INFORMATION);
    }
    return userInformation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Attr> getAttributes()
  {
    if (attributes == null)
    {
      attributes = new EDataTypeUniqueEList<Attr>(Attr.class, this, XSDPackage.XSD_ANNOTATION__ATTRIBUTES);
    }
    return attributes;
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
      case XSDPackage.XSD_ANNOTATION__APPLICATION_INFORMATION:
        return getApplicationInformation();
      case XSDPackage.XSD_ANNOTATION__USER_INFORMATION:
        return getUserInformation();
      case XSDPackage.XSD_ANNOTATION__ATTRIBUTES:
        return getAttributes();
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
      case XSDPackage.XSD_ANNOTATION__APPLICATION_INFORMATION:
        getApplicationInformation().clear();
        getApplicationInformation().addAll((Collection<? extends Element>)newValue);
        return;
      case XSDPackage.XSD_ANNOTATION__USER_INFORMATION:
        getUserInformation().clear();
        getUserInformation().addAll((Collection<? extends Element>)newValue);
        return;
      case XSDPackage.XSD_ANNOTATION__ATTRIBUTES:
        getAttributes().clear();
        getAttributes().addAll((Collection<? extends Attr>)newValue);
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
      case XSDPackage.XSD_ANNOTATION__APPLICATION_INFORMATION:
        getApplicationInformation().clear();
        return;
      case XSDPackage.XSD_ANNOTATION__USER_INFORMATION:
        getUserInformation().clear();
        return;
      case XSDPackage.XSD_ANNOTATION__ATTRIBUTES:
        getAttributes().clear();
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
      case XSDPackage.XSD_ANNOTATION__APPLICATION_INFORMATION:
        return applicationInformation != null && !applicationInformation.isEmpty();
      case XSDPackage.XSD_ANNOTATION__USER_INFORMATION:
        return userInformation != null && !userInformation.isEmpty();
      case XSDPackage.XSD_ANNOTATION__ATTRIBUTES:
        return attributes != null && !attributes.isEmpty();
    }
    return super.eIsSet(featureID);
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
    result.append(" (applicationInformation: ");
    result.append(applicationInformation);
    result.append(", userInformation: ");
    result.append(userInformation);
    result.append(", attributes: ");
    result.append(attributes);
    result.append(')');
    return result.toString();
  }

  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.ANNOTATION_ELEMENT);
    setElement(newElement);
    return newElement;
  }

/*
  protected void patch()
  {
    super.patch();
  }
*/

  public void validate()
  {
    super.validate();

    Element theElement = getElement();
    if (theElement != null)
    {
      checkElementComplexContent("annotation", XSDConstants.PART1, "element-annotation", theElement);
    }
  }

  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    Element theElement = getElement();
    if (changedElement == theElement || changedElement.getParentNode() == theElement)
    {
      XSDConcreteComponent container = getContainer();
      List newAttributes = new ArrayList();
      for (Node theNode = theElement; theNode != null; theNode = theNode.getParentNode())
      {
        NamedNodeMap attributes = theNode.getAttributes();
        for (int i = 0, size = attributes.getLength(); i < size; ++i)
        {
          Attr attr = (Attr)attributes.item(i);
          String attrName = attr.getNodeName();
          int index = attrName.lastIndexOf(":");
          if (index != -1)
          {
            newAttributes.add(attr);
          }
        }
        if (container == null || container.getElement() == theNode)
        {
          break;
        }
      }

      EList theAttributes = getAttributes();
      List remainingAttributes = new ArrayList(theAttributes);
      remainingAttributes.removeAll(newAttributes);
      if (!remainingAttributes.isEmpty())
      {
        theAttributes.removeAll(remainingAttributes);
      }
      setListContentAndOrder(theAttributes, newAttributes);
    }
  }

  protected void reconcileContents(Element changedElement)
  {
    Element theElement = getElement();
    if (changedElement == theElement || changedElement.getParentNode() == theElement)
    {
      List newApplicationInformation = new ArrayList();
      List newUserInformation = new ArrayList();
      for (Node child = theElement.getFirstChild(); child != null; child = child.getNextSibling())
      {
        switch (XSDConstants.nodeType(child))
        {
          case XSDConstants.APPINFO_ELEMENT:
          {
            newApplicationInformation.add(child);
            break;
          }
          case XSDConstants.DOCUMENTATION_ELEMENT:
          {
            newUserInformation.add(child);
            break;
          }
        }
      }

      EList theApplicationInformation = getApplicationInformation();
      List remainingApplicationInformation = new ArrayList(theApplicationInformation);
      remainingApplicationInformation.removeAll(newApplicationInformation);
      if (!remainingApplicationInformation.isEmpty())
      {
        theApplicationInformation.removeAll(remainingApplicationInformation);
      }
      setListContentAndOrder(theApplicationInformation, newApplicationInformation);

      EList theUserInformation = getUserInformation();
      List remainingUserInformation = new ArrayList(theUserInformation);
      remainingUserInformation.removeAll(newUserInformation);
      if (!remainingUserInformation.isEmpty())
      {
        remainingUserInformation.removeAll(newUserInformation);
      }
      theUserInformation.removeAll(remainingUserInformation);
      setListContentAndOrder(theUserInformation, newUserInformation);
    }
  }

  public Element createApplicationInformation(String sourceURI)
  {
    if (getElement() == null)
    {
      updateElement();
    }

    Element result = createElement(XSDConstants.APPINFO_ELEMENT);
    if (sourceURI != null && result != null)
    {
      result.setAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE, sourceURI);
    }
    return result;
  }

  public Element createUserInformation(String sourceURI)
  {
    if (getElement() == null)
    {
      updateElement();
    }

    Element result = createElement(XSDConstants.DOCUMENTATION_ELEMENT);
    if (sourceURI != null && result != null)
    {
      result.setAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE, sourceURI);
    }
    return result;
  }

  public EList getApplicationInformation(String sourceURI)
  {
    EList result = new BasicEList();
    for (Iterator applicationInformation = getApplicationInformation().iterator(); applicationInformation.hasNext(); )
    {
      Element appinfo = (Element)applicationInformation.next();
      if (sourceURI == null ? 
            !appinfo.hasAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE) : 
            appinfo.getAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE).equals(sourceURI))
      {
        result.add(appinfo);
      }
    }
    return result;
  }

  public EList getUserInformation(String sourceURI)
  {
    EList result = new BasicEList();
    for (Iterator userInformation = getUserInformation().iterator(); userInformation.hasNext(); )
    {
      Element documentation = (Element)userInformation.next();
      if (sourceURI == null ? 
            !documentation.hasAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE) : 
            documentation.getAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE).equals(sourceURI))
      {
        result.add(documentation);
      }
    }
    return result;
  }

  public Set getApplicationInformationSources()
  {
    Set result = new HashSet();
    for (Iterator applicationInformation = getApplicationInformation().iterator(); applicationInformation.hasNext(); )
    {
      Element appinfo = (Element)applicationInformation.next();
      result.add
        (appinfo.hasAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE) ? appinfo.getAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE) : null);
    }
    return result;
  }

  public Set getUserInformationSources()
  {
    Set result = new HashSet();
    for (Iterator userInformation = getUserInformation().iterator(); userInformation.hasNext(); )
    {
      Element documentation = (Element)userInformation.next();
      result.add
        (documentation.hasAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE) ? 
          documentation.getAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE) : null);
    }
    return result;
  }

  protected void getComponentsWithInformation(Collection result, int nodeType, String sourceURI)
  {
    for (Iterator elements = (nodeType == XSDConstants.APPINFO_ELEMENT ? getApplicationInformation() : getUserInformation()).iterator(); 
         elements.hasNext(); )
    {
      Element childElement = (Element)elements.next();
      if (sourceURI == null ? 
            !childElement.hasAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE) : 
            childElement.getAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE).equals(sourceURI))
      {
        result.add(getContainer());
        break;
      }
    }
  }

  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDAnnotationImpl clonedAnnotation = (XSDAnnotationImpl)getXSDFactory().createXSDAnnotation();
    clonedAnnotation.isReconciling = true;

    if (shareDOM)
    {
      Element element = getElement();
      if (element != null)
      {
        clonedAnnotation.setElement(element);
      }

      clonedAnnotation.getApplicationInformation().addAll(getApplicationInformation());
      clonedAnnotation.getUserInformation().addAll(getUserInformation());
      clonedAnnotation.getAttributes().addAll(getAttributes());
    }

    clonedAnnotation.isReconciling = shareDOM;
    return clonedAnnotation;
  }
}
