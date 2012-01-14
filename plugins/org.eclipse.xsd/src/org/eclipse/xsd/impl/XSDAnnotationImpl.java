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
package org.eclipse.xsd.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
  protected EList<Element> applicationInformation;

  /**
   * The cached value of the '{@link #getUserInformation() <em>User Information</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUserInformation()
   * @generated
   * @ordered
   */
  protected EList<Element> userInformation;

  /**
   * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttributes()
   * @generated
   * @ordered
   */
  protected EList<Attr> attributes;

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
  @Override
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
  @Override
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
  @SuppressWarnings("unchecked")
  @Override
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
  @Override
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
  @Override
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
  @Override
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

  @Override
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

  @Override
  public void validate()
  {
    super.validate();

    Element theElement = getElement();
    if (theElement != null)
    {
      checkElementComplexContent("annotation", XSDConstants.PART1, "element-annotation", theElement);
    }
  }

  @Override
  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    Element theElement = getElement();
    if (changedElement == theElement || changedElement.getParentNode() == theElement)
    {
      XSDConcreteComponent container = getContainer();
      List<Attr> newAttributes = new ArrayList<Attr>();
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

      EList<Attr> theAttributes = getAttributes();
      List<Attr> remainingAttributes = new ArrayList<Attr>(theAttributes);
      remainingAttributes.removeAll(newAttributes);
      if (!remainingAttributes.isEmpty())
      {
        theAttributes.removeAll(remainingAttributes);
      }
      setListContentAndOrder(theAttributes, newAttributes);
    }
  }

  @Override
  protected void reconcileContents(Element changedElement)
  {
    Element theElement = getElement();
    if (changedElement == theElement || changedElement.getParentNode() == theElement)
    {
      List<Element> newApplicationInformation = new ArrayList<Element>();
      List<Element> newUserInformation = new ArrayList<Element>();
      for (Node child = theElement.getFirstChild(); child != null; child = child.getNextSibling())
      {
        switch (XSDConstants.nodeType(child))
        {
          case XSDConstants.APPINFO_ELEMENT:
          {
            newApplicationInformation.add((Element)child);
            break;
          }
          case XSDConstants.DOCUMENTATION_ELEMENT:
          {
            newUserInformation.add((Element)child);
            break;
          }
        }
      }

      EList<Element> theApplicationInformation = getApplicationInformation();
      List<Element> remainingApplicationInformation = new ArrayList<Element>(theApplicationInformation);
      remainingApplicationInformation.removeAll(newApplicationInformation);
      if (!remainingApplicationInformation.isEmpty())
      {
        theApplicationInformation.removeAll(remainingApplicationInformation);
      }
      setListContentAndOrder(theApplicationInformation, newApplicationInformation);

      EList<Element> theUserInformation = getUserInformation();
      List<Element> remainingUserInformation = new ArrayList<Element>(theUserInformation);
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

  public EList<Element> getApplicationInformation(String sourceURI)
  {
    EList<Element> result = new BasicEList<Element>();
    for (Element appinfo : getApplicationInformation())
    {
      if (sourceURI == null ? 
            !appinfo.hasAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE) : 
            appinfo.getAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE).equals(sourceURI))
      {
        result.add(appinfo);
      }
    }
    return result;
  }

  public EList<Element> getUserInformation(String sourceURI)
  {
    EList<Element> result = new BasicEList<Element>();
    for (Element documentation : getUserInformation())
    {
      if (sourceURI == null ? 
            !documentation.hasAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE) : 
            documentation.getAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE).equals(sourceURI))
      {
        result.add(documentation);
      }
    }
    return result;
  }

  public Set<String> getApplicationInformationSources()
  {
    Set<String> result = new HashSet<String>();
    for (Element appinfo : getApplicationInformation())
    {
      result.add
        (appinfo.hasAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE) ? appinfo.getAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE) : null);
    }
    return result;
  }

  public Set<String> getUserInformationSources()
  {
    Set<String> result = new HashSet<String>();
    for (Element documentation : getUserInformation())
    {
      result.add
        (documentation.hasAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE) ? 
          documentation.getAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE) : null);
    }
    return result;
  }

  @Override
  protected void getComponentsWithInformation(Collection<XSDConcreteComponent> result, int nodeType, String sourceURI)
  {
    for (Element childElement : nodeType == XSDConstants.APPINFO_ELEMENT ? getApplicationInformation() : getUserInformation())
    {
      if (sourceURI == null ? 
            !childElement.hasAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE) : 
            childElement.getAttributeNS(null, XSDConstants.SOURCE_ATTRIBUTE).equals(sourceURI))
      {
        result.add(getContainer());
        break;
      }
    }
  }

  @Override
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
