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
 * $Id: XSDConcreteComponentImpl.java,v 1.23 2007/04/30 15:30:29 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeGroupDefinition;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDIdentityConstraintCategory;
import org.eclipse.xsd.XSDIdentityConstraintDefinition;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDNotationDeclaration;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDPlugin;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTerm;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.XSDVariety;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Concrete Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDConcreteComponentImpl#getElement <em>Element</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDConcreteComponentImpl#getContainer <em>Container</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDConcreteComponentImpl#getRootContainer <em>Root Container</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDConcreteComponentImpl#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDConcreteComponentImpl#getDiagnostics <em>Diagnostics</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class XSDConcreteComponentImpl 
  extends EObjectImpl 
  implements XSDConcreteComponent
{
  /**
   * The default value of the '{@link #getElement() <em>Element</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElement()
   * @generated
   * @ordered
   */
  protected static final Element ELEMENT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getElement() <em>Element</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElement()
   * @generated
   * @ordered
   */
  protected Element element = ELEMENT_EDEFAULT;

  /**
   * The cached value of the '{@link #getDiagnostics() <em>Diagnostics</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDiagnostics()
   * @generated
   * @ordered
   */
  protected EList<XSDDiagnostic> diagnostics;

  protected static final int FATAL=0;
  protected static final int ERROR=1;
  protected static final int WARNING=2;
  protected static final int INFORMATION=3;

  protected boolean updatingDOM; 
  protected boolean isReconciling; 
  protected boolean forceResolve; 
  protected EList<XSDConcreteComponent> xsdContents;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDConcreteComponentImpl()
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
    return XSDPackage.Literals.XSD_CONCRETE_COMPONENT;
  }

  protected XSDFactory getXSDFactory()
  {
    return getXSDPackage().getXSDFactory();
  }

  protected XSDPackage getXSDPackage()
  {
    return (XSDPackage)eClass().getEPackage();
  }

  public static class XSDContentsEList extends EContentsEList<XSDConcreteComponent>
  {
    XSDContentsEList(EObject eObject, EStructuralFeature [] eStructuralFeatures)
    {
      super(eObject, eStructuralFeatures);
    }

    @Override
    protected ListIterator<XSDConcreteComponent> newListIterator()
    {
      return new FeatureIteratorImpl(eObject, eStructuralFeatures);
    }

    @Override
    protected boolean isIncluded(EStructuralFeature eStructuralFeature)
    {
      EReference eReference = (EReference)eStructuralFeature;
      return !eReference.isTransient();
    }

    @Override
    public List<XSDConcreteComponent> basicList()
    {
      return new XSDContentsEList(eObject, eStructuralFeatures);
    }

    @Override
    public Iterator<XSDConcreteComponent> basicIterator()
    {
      return new FeatureIteratorImpl(eObject, eStructuralFeatures);
    }

    public static class FeatureIteratorImpl extends EContentsEList.FeatureIteratorImpl<XSDConcreteComponent>
    {
      public FeatureIteratorImpl(EObject eObject)
      {
        super(eObject, (EStructuralFeature [])((BasicEList<?>)eObject.eClass().getEAllReferences()).data());
      }

      public FeatureIteratorImpl(EObject eObject, EStructuralFeature [] eStructuralFeatures)
      {
        super(eObject, eStructuralFeatures);
      }

      @Override
      protected boolean isIncluded(EStructuralFeature eStructuralFeature)
      {
        EReference eReference = (EReference)eStructuralFeature;
        return !eReference.isTransient();
      }
    }
  }

  public EList<XSDConcreteComponent> getXSDContents()
  {
    if (xsdContents == null)
    {
      xsdContents =
        new XSDContentsEList
          (this,
           (EStructuralFeature [])((BasicEList<?>)eClass().getEAllContainments()).data());
    }

    return xsdContents;
  }

  @SuppressWarnings("unchecked")
  protected EList<XSDConcreteComponentImpl> getXSDConcreteComponentImpls()
  {
    return (EList<XSDConcreteComponentImpl>)(EList<?>)getXSDContents();
  }

  public void reset()
  {
    for (XSDConcreteComponentImpl content : getXSDConcreteComponentImpls())
    {
      content.reset();
    }
  }

  protected final Element createElement(int nodeType)
  {
    XSDSchema xsdSchema = getSchema();

    if (xsdSchema == null)
    {
       return null;
    }

    Document document = null;
    Element element = null;

    LOOP:
    for (XSDConcreteComponent container = getContainer(); container != null; container = container.getContainer())
    {
      element = container.getElement();
      if (element != null)
      {
        for (Node root = element.getParentNode(); root != null; root = root.getParentNode())
        {
          if (root.getNodeType() == Node.DOCUMENT_NODE)
          {
            document = (Document)root;
            break LOOP;
          }
        }
      }
    }
    if (document == null)
    {
      document = xsdSchema.getDocument();
      if (document == null)
      {
        document = xsdSchema.updateDocument();
      }
    }

    String qualifier = xsdSchema.getSchemaForSchemaQNamePrefix();
    String schemaForSchemaNamespace = xsdSchema.getSchemaForSchemaNamespace();

    Element newElement = 
      document.createElementNS
        (schemaForSchemaNamespace, (qualifier == null ? "" : qualifier + ":") + XSDConstants.ELEMENT_TAGS[nodeType]);

    if (document.getDocumentElement() == null)
    {
      document.appendChild(newElement);
    }

    if (nodeType == XSDConstants.SCHEMA_ELEMENT)
    {
      for (Map.Entry<String, String> entry : xsdSchema.getQNamePrefixToNamespaceMap().entrySet())
      {
        String key = entry.getKey();
        newElement.setAttributeNS(XSDConstants.XMLNS_URI_2000, key == null ? "xmlns" : "xmlns:" + key, entry.getValue());
      }

      String xmlnsAttribute = "xmlns";
      if (qualifier != null)
      {
        xmlnsAttribute += ":" + qualifier;
      }
      newElement.setAttributeNS(XSDConstants.XMLNS_URI_2000, xmlnsAttribute,  schemaForSchemaNamespace);
    }

    return newElement;
  }

  public Element createElement()
  {
    return null;
  }

  public void updateElement(boolean deep)
  {
    if (deep || getElement() == null)
    {
      updateElement();
    }
    else
    {
      changeAttribute(null);
      changeReference(null);
    }
  }

  public void updateElement()
  {
    if (getElement() == null)
    {
      XSDConcreteComponent container = getContainer();
      if (container == null)
      {
        if (!(this instanceof XSDSchema))
        {
          return;
        }
      }
      else if (container.getElement() == null)
      {
        container.updateElement();
        return;
      }

      isReconciling = true;
      createElement();
      isReconciling = false;
    }

    changeAttribute(null);
    changeReference(null);

    for (EReference eReference : eClass().getEAllContainments())
    {
      if (!eReference.isTransient())
      {
        if (eReference.isMany())
        {
          @SuppressWarnings("unchecked") 
          Collection<XSDConcreteComponent> xsdConcreteComponents = ((Collection<XSDConcreteComponent>)eGet(eReference));
          for (XSDConcreteComponent xsdConcreteComponent : xsdConcreteComponents)
          {
            handleElementForAdopt(eReference, xsdConcreteComponent);
            xsdConcreteComponent.updateElement();
          }
        }
        else
        {
          XSDConcreteComponent xsdConcreteComponent = (XSDConcreteComponent)eGet(eReference);
          if (xsdConcreteComponent != null)
          {
            handleElementForAdopt(eReference, xsdConcreteComponent);
            xsdConcreteComponent.updateElement();
          }
        }
      }
    }
  }

  protected void updatePrefix(Node node, String namespace, String oldPrefix, String newPrefix)
  {
    if ((namespace == null ? node.getNamespaceURI() == null : namespace.equals(node.getNamespaceURI())) &&
          (oldPrefix == null ? node.getPrefix() == null : oldPrefix.equals(node.getPrefix())))
    {
      node.setPrefix(newPrefix);
    }

    for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling())
    {
      updatePrefix(child, namespace, oldPrefix, newPrefix);
    }
  }

  protected boolean isUpdatingDOM()
  {
    return updatingDOM;
  }

  public void elementChanged(Element changedElement)
  {
    if (!isUpdatingDOM())
    {
      if (!isReconciling)
      {
        // System.out.println("**** changeFor " + eClass().getName());
    
        isReconciling = true;
        reconcile(changedElement);

        XSDConcreteComponent theContainer = getContainer();
        if (theContainer != null && theContainer.getElement() == changedElement)
        {
          theContainer.elementChanged(changedElement);
        }

        isReconciling = false;
        traverseToRootForPatching();
      }
      else
      {
        // System.out.println("**** cyclic internal reconcile avoided " + eClass().getName());
      }
    }
    else
    {
      // System.out.println("**** cyclic DOM reconcile avoided " + eClass().getName());
    }
  }

  public void elementAttributesChanged(Element changedElement)
  {
    if (!isUpdatingDOM())
    {
      if (!isReconciling)
      {
        // System.out.println("**** changeFor " + eClass().getName());
    
        isReconciling = true;
        reconcileAttributes(changedElement);

        XSDConcreteComponent theContainer = getContainer();
        if (theContainer != null && theContainer.getElement() == changedElement)
        {
          theContainer.elementAttributesChanged(changedElement);
        }

        isReconciling = false;
        traverseToRootForPatching();
      }
      else
      {
        // System.out.println("**** cyclic internal reconcile avoided " + eClass().getName());
      }
    }
    else
    {
      // System.out.println("**** cyclic DOM reconcile avoided " + eClass().getName());
    }
  }

  public void elementContentsChanged(Element changedElement)
  {
    if (!isUpdatingDOM())
    {
      if (!isReconciling)
      {
        // System.out.println("**** changeFor " + eClass().getName());
    
        isReconciling = true;
        reconcileContents(changedElement);

        XSDConcreteComponent theContainer = getContainer();
        if (theContainer != null && theContainer.getElement() == changedElement)
        {
          theContainer.elementContentsChanged(changedElement);
        }

        isReconciling = false;
        traverseToRootForPatching();
      }
      else
      {
        // System.out.println("**** cyclic internal reconcile avoided " + eClass().getName());
      }
    }
    else
    {
      // System.out.println("**** cyclic DOM reconcile avoided " + eClass().getName());
    }
  }

  protected void traverseToRootForPatching()
  {
    if (!isReconciling)
    {
      XSDConcreteComponentImpl container = (XSDConcreteComponentImpl)getContainer();
      if (container != null)
      {
        container.traverseToRootForPatching();
      }
    }
  }

  protected void traverseToRootForAnalysis()
  {
    if (!isReconciling)
    {
      XSDConcreteComponentImpl container = (XSDConcreteComponentImpl)getContainer();
      if (container != null)
      {
        container.traverseToRootForAnalysis();
      }
    }
  }

  protected void patch()
  {
    for (XSDConcreteComponentImpl content : getXSDConcreteComponentImpls())
    {
      content.forceResolve = forceResolve;
      content.isReconciling = true;
      content.patch();
      content.isReconciling = false;
      content.forceResolve = false;
    }
  }

  protected final int UNANALYZED = 0;
  protected final int ANALYZING = 1;
  protected final int ANALYZED = 2;
  protected final int CIRCULAR = 3;

  protected boolean analyze()
  {
    boolean result = true;
    for (XSDConcreteComponentImpl content : getXSDConcreteComponentImpls())
    {
      content.isReconciling = true;
      if (!content.analyze())
      {
        result = false;
      }
      content.isReconciling = false;
    }
    return result;
  }

  public void validate()
  {
    for (XSDConcreteComponentImpl content : getXSDConcreteComponentImpls())
    {
      content.validate();
    }
  }

  public void clearDiagnostics()
  {
    Collection<XSDDiagnostic> theDiagnostics = getDiagnostics();
    if (!theDiagnostics.isEmpty())
    {
      theDiagnostics.clear();
    }
    for (XSDConcreteComponent content : getXSDContents())
    {
      content.clearDiagnostics();
    }
  }

  protected XSDDiagnostic reportConstraintViolation
    (String part, String anchor, Element element, String attributeName, Object [] substitutions)
  {
    XSDDiagnostic xsdDiagnostic = getXSDFactory().createXSDDiagnostic();
    xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
    xsdDiagnostic.setMessage
      (XSDPlugin.INSTANCE.getString("_UI_XSDError_message", new Object [] { populateDiagnostic(xsdDiagnostic, anchor, substitutions) }));
    xsdDiagnostic.setPrimaryComponent(this);
    Node node = element;
    if (attributeName != null)
    {
      Attr attr = element.getAttributeNodeNS(null, attributeName);
      if (attr != null)
      {
        node = attr;
      }
    }
    xsdDiagnostic.setNode(node);

    getDiagnostics().add(xsdDiagnostic);
    return xsdDiagnostic;
  }

  protected XSDDiagnostic checkElementComplexContent
    (String elementName, 
     String part, 
     String anchor, 
     Element element)
  {
    XSDElementDeclaration xsdElementDeclaration = getSchema().getSchemaForSchema().resolveElementDeclaration(elementName);
    return checkComplexContent((XSDComplexTypeDefinition)xsdElementDeclaration.getTypeDefinition(), part, anchor, element);
  }

  protected void printDFA(XSDParticle.DFA dfa)
  {
    ((XSDParticleImpl.XSDNFA)dfa).dump(System.err);
  }

  protected XSDDiagnostic checkComplexContent
    (String builtInTypeName, 
     String part, 
     String anchor, 
     Element element)
  {
    XSDComplexTypeDefinition xsdComplexTypeDefinition = getSchema().getSchemaForSchema().resolveComplexTypeDefinition(builtInTypeName);
    return checkComplexContent(xsdComplexTypeDefinition, part, anchor, element);
  }

  protected XSDDiagnostic checkComplexContent
    (XSDComplexTypeDefinition xsdComplexTypeDefinition,
     String part, 
     String anchor, 
     Element element)
  {
    XSDParticle complexType = xsdComplexTypeDefinition.getComplexType();
    XSDParticle.DFA dfa = complexType.getDFA();
    XSDParticle.DFA.State state = dfa.getInitialState();
    for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling())
    {
      if (child.getNodeType() == Node.ELEMENT_NODE)
      {
        XSDParticle.DFA.Transition transition = state.accept(child.getNamespaceURI(), child.getLocalName());
        if (transition != null)
        {
          state = transition.getState();
        }
        else
        {
          XSDDiagnostic xsdDiagnostic = getXSDFactory().createXSDDiagnostic();
          xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
          xsdDiagnostic.setMessage
            (XSDPlugin.INSTANCE.getString
               ("_UI_XSDError_message", 
                new Object [] 
                {
                  populateDiagnostic
                    (xsdDiagnostic,
                     "content-valid.1", 
                     new Object [] { XSDConstants.uri(child), xsdComplexTypeDefinition.getURI(), getExpected(state) })
                }));
          xsdDiagnostic.setAnnotationURI(part + "#" + anchor);
          xsdDiagnostic.setPrimaryComponent(this);
          xsdDiagnostic.setNode(child);
          getDiagnostics().add(xsdDiagnostic);

          return xsdDiagnostic;
        }
      }
      else
      {
        // EATM should be whitespace
      }
    }

    if (!state.isAccepting())
    {
      XSDDiagnostic xsdDiagnostic = getXSDFactory().createXSDDiagnostic();
      xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
      xsdDiagnostic.setMessage
        (XSDPlugin.INSTANCE.getString
           ("_UI_XSDError_message", 
            new Object [] 
            { 
              populateDiagnostic
                (xsdDiagnostic,
                 "content-valid.2", 
                 new Object [] { xsdComplexTypeDefinition.getURI(), getExpected(state) }) 
            }));
      xsdDiagnostic.setAnnotationURI(part + "#" + anchor);
      xsdDiagnostic.setPrimaryComponent(this);
      xsdDiagnostic.setNode(element);
      getDiagnostics().add(xsdDiagnostic);

      return xsdDiagnostic;
    }

    return null;
  }

  private static String getExpected(XSDParticle.DFA.State state)
  {
    StringBuffer result = new StringBuffer();
    for (XSDParticle.DFA.Transition transition : state.getTransitions())
    {
      XSDParticle xsdParticle = transition.getParticle();
      XSDTerm xsdTerm = xsdParticle.getTerm();
      if (xsdTerm instanceof XSDElementDeclaration)
      {
        XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)xsdTerm;
        if (result.length() != 0)
        {
          result.append(" | ");
        }
        result.append(xsdElementDeclaration.getName());
      }
    }

    if (state.isAccepting()) 
    {
      if (result.length() != 0)
      {
        result.append(" | ");
      }

      result.append(XSDPlugin.INSTANCE.getString("expecting_nothing"));
    }

    return result.length() == 0 ? XSDPlugin.INSTANCE.getString("expecting_nothing") : result.toString();
  }

  protected XSDDiagnostic checkAttributeTypeConstraint
    (String builtInTypeName, 
     String builtInAttributeName, 
     String value, 
     String part, 
     String anchor, 
     Element element, 
     String attributeName, 
     boolean isRequired)
  {
    XSDComplexTypeDefinition xsdComplexTypeDefinition = getSchema().getSchemaForSchema().resolveComplexTypeDefinition(builtInTypeName);
    return checkAttributeTypeConstraint
      (xsdComplexTypeDefinition,
       builtInAttributeName,
       value,
       part,
       anchor,
       element,
       attributeName,
       isRequired);
  }

  protected XSDDiagnostic checkAttributeTypeConstraint
    (XSDComplexTypeDefinition xsdComplexTypeDefinition,
     String builtInAttributeName, 
     String value, 
     String part, 
     String anchor, 
     Element element, 
     String attributeName, 
     boolean isRequired)
  {
    for (XSDAttributeUse xsdAttributeUse : xsdComplexTypeDefinition.getAttributeUses())
    {
      XSDAttributeDeclaration xsdAttributeDeclaration = xsdAttributeUse.getAttributeDeclaration();
      if (builtInAttributeName.equals(xsdAttributeDeclaration.getName()))
      {
        XSDDiagnostic result =
          checkSimpleTypeConstraint
            (xsdAttributeDeclaration.getTypeDefinition(), value, part, anchor, element, attributeName, isRequired);
        return result;
      }
    }

    return null;
  }

  protected XSDDiagnostic checkBuiltInTypeConstraint
    (String builtInTypeName, String value, String part, String anchor, Element element, String attributeName, boolean isRequired)
  {
    XSDSimpleTypeDefinition builtInType = getSchema().getSchemaForSchema().resolveSimpleTypeDefinition(builtInTypeName);
    return checkSimpleTypeConstraint(builtInType, value, part, anchor, element, attributeName, isRequired);
  }

  protected XSDDiagnostic checkSimpleTypeConstraint
    (XSDSimpleTypeDefinition xsdTypeDefinition, 
     String value, 
     String part, 
     String anchor, 
     Element element, 
     String attributeName, 
     boolean isRequired)
  {
    XSDDiagnostic result = null;
    if (element != null && value == null && element.hasAttributeNS(null, attributeName))
    {
      value = element.getAttributeNS(null, attributeName);
    }

    if (value == null)
    {
      if (isRequired)
      {
        result = createRequiredAttributeDiagnostic(part, anchor, element, attributeName);
      }
    }
    else
    {
      XSDSimpleTypeDefinition.Assessment assessment = xsdTypeDefinition.assess(value);
      Collection<XSDDiagnostic> allDiagnostics = assessment.getDiagnostics();
      if (!allDiagnostics.isEmpty())
      {
        ((XSDSimpleTypeDefinitionImpl.AssessmentImpl)assessment).assignDiagnostics(this, element, attributeName);
        getDiagnostics().addAll(allDiagnostics);
        result = allDiagnostics.iterator().next();
        result.setAnnotationURI(part + "#" + anchor);
      }
    }

    return result;
  }

  protected Collection<XSDDiagnostic> checkAttributes(String part, String anchor, Element element, String [] allowedAttributeNames)
  {
    Collection<XSDDiagnostic> result = null;
    if (element != null)
    {
      NamedNodeMap attributes = element.getAttributes();
      LOOP:
      for (int i = 0, length = attributes.getLength(); i < length; ++i)
      {
        Attr attr = (Attr)attributes.item(i);
        if (attr.getNamespaceURI() == null)
        {
          String localName = attr.getLocalName();

          for (int j = 0; j < allowedAttributeNames.length; ++j)
          {
            if (allowedAttributeNames[j].equals(localName))
            {
              continue LOOP;
            }
          }

          XSDDiagnostic xsdDiagnostic = getXSDFactory().createXSDDiagnostic();
          xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
          xsdDiagnostic.setMessage
            (XSDPlugin.INSTANCE.getString
              ("_UI_XSDError_message", 
               new Object [] 
               { 
                 populateDiagnostic(xsdDiagnostic, "cvc-complex-type.3", new Object [] { localName }) 
               }));
          xsdDiagnostic.setAnnotationURI(part + "#" + anchor);
          xsdDiagnostic.setPrimaryComponent(this);
          xsdDiagnostic.setNode(attr);
          getDiagnostics().add(xsdDiagnostic);
          if (result == null)
          {
            result = new ArrayList<XSDDiagnostic>();
          }
          result.add(xsdDiagnostic);
        }
      }
    }

    return result;
  }

  protected XSDDiagnostic createRequiredAttributeDiagnostic(String part, String anchor, Element element, String attributeName)
  {
    XSDDiagnostic result = getXSDFactory().createXSDDiagnostic();
    result.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
    result.setMessage
      (XSDPlugin.INSTANCE.getString
         ("_UI_XSDError_message", 
          new Object [] 
          { 
            populateDiagnostic(result, "cvc-complex-type.4", new Object [] { attributeName }) 
          }));
    result.setAnnotationURI(part + "#" + anchor);
    result.setPrimaryComponent(this);
    result.setNode(element);
    getDiagnostics().add(result);
    return result;
  }

  protected XSDConcreteComponentImpl getDiagnosticTarget(XSDConcreteComponent xsdConcreteComponent)
  {
    return
      EcoreUtil.isAncestor(this, xsdConcreteComponent) && 
        !xsdConcreteComponent.eContainmentFeature().isTransient() ?
          (XSDConcreteComponentImpl)xsdConcreteComponent :
          this;
  }

  protected XSDDiagnostic createDiagnostic(XSDDiagnosticSeverity severity, String key)
  {
    XSDDiagnostic xsdDiagnostic = getXSDFactory().createXSDDiagnostic();
    xsdDiagnostic.setSeverity(severity);
    xsdDiagnostic.setMessage
      (XSDPlugin.INSTANCE.getString
         ("_UI_XSDError_message", new Object [] { populateDiagnostic(xsdDiagnostic, key, null) }));
    xsdDiagnostic.setPrimaryComponent(this);
    xsdDiagnostic.setNode(getElement());

    Element theElement = getElement();
    xsdDiagnostic.setNode(theElement);

    getDiagnostics().add(xsdDiagnostic);
    return xsdDiagnostic;
  }

  protected XSDDiagnostic createDiagnostic(XSDDiagnosticSeverity severity, String key, Object s1)
  {
    XSDDiagnostic xsdDiagnostic = getXSDFactory().createXSDDiagnostic();
    xsdDiagnostic.setSeverity(severity);
    xsdDiagnostic.setMessage
      (XSDPlugin.INSTANCE.getString
         ("_UI_XSDError_message", new Object [] { populateDiagnostic(xsdDiagnostic, key, new Object [] { s1 }) }));
    xsdDiagnostic.setPrimaryComponent(this);
    xsdDiagnostic.setNode(getElement());

    Element theElement = getElement();
    xsdDiagnostic.setNode(theElement);

    getDiagnostics().add(xsdDiagnostic);
    return xsdDiagnostic;
  }

  protected XSDDiagnostic createDiagnostic(XSDDiagnosticSeverity severity, String key, Object s1, Object s2)
  {
    XSDDiagnostic xsdDiagnostic = getXSDFactory().createXSDDiagnostic();
    xsdDiagnostic.setSeverity(severity);
    xsdDiagnostic.setMessage
      (XSDPlugin.INSTANCE.getString
         ("_UI_XSDError_message", new Object [] { populateDiagnostic(xsdDiagnostic, key, new Object [] { s1, s2 }) }));
    xsdDiagnostic.setPrimaryComponent(this);
    xsdDiagnostic.setNode(getElement());

    Element theElement = getElement();
    xsdDiagnostic.setNode(theElement);

    getDiagnostics().add(xsdDiagnostic);
    return xsdDiagnostic;
  }

  protected XSDDiagnostic createDiagnostic(XSDDiagnosticSeverity severity, String key, Object s1, Object s2, Object s3)
  {
    XSDDiagnostic xsdDiagnostic = getXSDFactory().createXSDDiagnostic();
    xsdDiagnostic.setSeverity(severity);
    xsdDiagnostic.setMessage
      (XSDPlugin.INSTANCE.getString
         ("_UI_XSDError_message", new Object [] { populateDiagnostic(xsdDiagnostic, key, new Object [] { s1, s2, s3 }) }));
    xsdDiagnostic.setPrimaryComponent(this);
    xsdDiagnostic.setNode(getElement());

    Element theElement = getElement();
    xsdDiagnostic.setNode(theElement);

    getDiagnostics().add(xsdDiagnostic);
    return xsdDiagnostic;
  }
  
  protected static String populateDiagnostic(XSDDiagnostic xsdDiagnostic, String key, Object [] substitutions)
  {
    xsdDiagnostic.setKey(key);
    if (substitutions != null)
    {
      List<String> values = xsdDiagnostic.getSubstitutions();
      for (int i = 0; i < substitutions.length; ++i)
      {
        Object value = substitutions[i];
        values.add(value == null ? null : value.toString());
      }
      return XSDPlugin.INSTANCE.getString(key, substitutions);
    }
    else
    {
      return XSDPlugin.INSTANCE.getString(key);
    }
  }

  protected void reconcile(Element changedElement)
  {
    reconcileAttributes(changedElement);
    reconcileContents(changedElement);
  }

  protected void reconcileAttributes(Element changedElement)
  {
    // Do nothing.
  }

  protected Collection<Element> getContentNodes(Element changedElement)
  {
    Collection<Element> result = new ArrayList<Element>();
    for (Node child = getElement().getFirstChild(); child != null; child = child.getNextSibling())
    {
      if (child.getNodeType() == Node.ELEMENT_NODE)
      {
        result.add((Element)child);
      }
    }
    return result;
  }

  protected void reconcileContents(Element changedElement)
  {
    XSDSchemaImpl xsdSchema = (XSDSchemaImpl)getSchema();
    List<XSDConcreteComponent> newContents = new ArrayList<XSDConcreteComponent>();
    List<XSDConcreteComponent> remainingContents = new ArrayList<XSDConcreteComponent>(getXSDContents());
    Collection<Element> contentNodes = getContentNodes(changedElement);
    LOOP: 
    for (Node child : contentNodes)
    {
      if (child.getNodeType() == Node.ELEMENT_NODE && (xsdSchema == null || child != xsdSchema.getDeletionNode()))
      {
        for (Iterator<XSDConcreteComponent> contents = remainingContents.iterator(); contents.hasNext(); )
        {
          XSDConcreteComponent remainingConcreteComponent = contents.next();
          if (remainingConcreteComponent.getElement() == child)
          {
            newContents.add(remainingConcreteComponent);
            contents.remove();
            continue LOOP;
          }
        }

        if (!remainingContents.isEmpty())
        {
          XSDConcreteComponent potentialReplacement = remainingContents.get(0);
          Element potentialReplacedElement = potentialReplacement.getElement();
          if (potentialReplacedElement != null &&
                potentialReplacedElement.getParentNode() != changedElement &&
                potentialReplacedElement.getLocalName().equals(child.getLocalName()))
          {
            remainingContents.remove(0);
            potentialReplacement.setElement((Element)child);
            newContents.add(potentialReplacement);
            continue;
          }
        }

        handleUnreconciledElement((Element)child, newContents, remainingContents);
      }
    }

    handleReconciliation(newContents, remainingContents);
  }

  protected void handleUnreconciledElement(Element child, List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    // Do nothing.
  }

  protected void handleReconciliation(List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    // Do nothing.
  }

  protected void handleAnnotationReconciliation
    (EReference eReference, List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    XSDAnnotation newAnnotation = null;
    XSDAnnotation oldAnnotation = (XSDAnnotation)eGet(eReference);
    if (!newContents.isEmpty())
    {
      XSDConcreteComponent xsdConcreteComponent = newContents.get(0);
      if (xsdConcreteComponent instanceof XSDAnnotation)
      {
        newAnnotation = (XSDAnnotation)xsdConcreteComponent;
        newContents.remove(0);
      }
    }

    if (newAnnotation != oldAnnotation)
    {
      eSet(eReference, newAnnotation);
    }

    if (oldAnnotation != null)
    {
      remainingContents.remove(oldAnnotation);
    }
  }

  @Override
  public void eNotify(Notification msg)
  {
    int eventType = msg.getEventType();
    Object feature = msg.getFeature();
    Object oldValue = msg.getOldValue();
    Object newValue = msg.getNewValue();
    if (eClass().getEAllReferences().contains(feature))
    {
      EReference eReference = (EReference)feature;
      if (feature == XSDPackage.Literals.XSD_CONCRETE_COMPONENT__DIAGNOSTICS)
      {
        switch (eventType)
        {
          case Notification.ADD:
          {
            adoptDiagnostic((XSDDiagnostic)newValue);
            break;
          }
          case Notification.ADD_MANY:
          {
            @SuppressWarnings("unchecked") Collection<XSDDiagnostic> xsdDiagnostics = ((Collection<XSDDiagnostic>)newValue);
            for (XSDDiagnostic xsdDiagnostic : xsdDiagnostics)
            {
              adoptDiagnostic(xsdDiagnostic);
            }
            break;
          }
          case Notification.REMOVE:
          {
            if (oldValue != null)
            {
              orphanDiagnostic((XSDDiagnostic)oldValue);
            }
            break;
          }
          case Notification.REMOVE_MANY:
          {
            @SuppressWarnings("unchecked") Collection<XSDDiagnostic> xsdDiagnostics = ((Collection<XSDDiagnostic>)oldValue);
            for (XSDDiagnostic xsdDiagnostic : xsdDiagnostics)
            {
              orphanDiagnostic(xsdDiagnostic);
            }
            break;
          }
          case Notification.MOVE:
          {
            // Don't care.
            //
            break;
          }
          case Notification.SET:
          case Notification.UNSET:
          {
            if (oldValue != null)
            {
              orphanDiagnostic((XSDDiagnostic)oldValue);
            }
            if (newValue != null)
            {
              adoptDiagnostic((XSDDiagnostic)newValue);
            }
            break;
          }
        }
      }
      else if (eReference.isContainment() && !eReference.isTransient())
      {
        switch (eventType)
        {
          case Notification.ADD:
          {
            adoptContent(eReference, (XSDConcreteComponent)newValue);
            break;
          }
          case Notification.ADD_MANY:
          {
            @SuppressWarnings("unchecked") Collection<XSDConcreteComponent> xsdConcreteComponents = ((Collection<XSDConcreteComponent>)newValue);
            for (XSDConcreteComponent xsdConcreteComponent : xsdConcreteComponents)
            {
              adoptContent(eReference, xsdConcreteComponent);
            }
            break;
          }
          case Notification.REMOVE:
          {
            if (oldValue != null)
            {
              orphanContent(eReference, (XSDConcreteComponent)oldValue);
            }
            break;
          }
          case Notification.REMOVE_MANY:
          {
            @SuppressWarnings("unchecked") Collection<XSDConcreteComponent> xsdConcreteComponents = ((Collection<XSDConcreteComponent>)oldValue);
            for (XSDConcreteComponent xsdConcreteComponent : xsdConcreteComponents)
            {
              orphanContent(eReference, xsdConcreteComponent);
            }
            break;
          }
          case Notification.MOVE:
          {
            moveContent(eReference, (XSDConcreteComponent)newValue);
            break;
          }
          case Notification.SET:
          case Notification.UNSET:
          {
            if (oldValue != null)
            {
              orphanContent(eReference, (XSDConcreteComponent)oldValue);
            }
            if (newValue != null)
            {
              adoptContent(eReference, (XSDConcreteComponent)newValue);
            }
            break;
          }
        }
      }
      else
      {
        switch (eventType)
        {
          case Notification.ADD:
          case Notification.ADD_MANY:
          case Notification.REMOVE:
          case Notification.REMOVE_MANY:
          case Notification.MOVE:
          case Notification.SET:
          case Notification.UNSET:
          {
            changeReference(eReference);
            break;
          }
        }
      }
    }
    else if (eClass().getEAllAttributes().contains(feature))
    {
      EAttribute eAttribute = (EAttribute)feature;
      switch (eventType)
      {
        case Notification.ADD:
        case Notification.ADD_MANY:
        case Notification.REMOVE:
        case Notification.REMOVE_MANY:
        case Notification.MOVE:
        case Notification.SET:
        case Notification.UNSET:
        {
          changeAttribute(eAttribute);
          break;
        }
      }
    }
    super.eNotify(msg);
  }

  protected void changeAttribute(EAttribute eAttribute)
  {
    if (eAttribute == XSDPackage.Literals.XSD_CONCRETE_COMPONENT__ELEMENT)
    {
      Element theElement = getElement();
      if (theElement != null && !isReconciling)
      {
        for (Node rootDocument = theElement.getParentNode(); rootDocument != null; rootDocument = rootDocument.getParentNode())
        {
          if (rootDocument.getNodeType() == Node.DOCUMENT_NODE)
          {
            isReconciling = true;
            if (getSchema() == null)
            {
              reconcileAttributes(getElement());
              isReconciling = false;
            }
            else
            {
              reconcile(getElement());
              isReconciling = false;
              traverseToRootForPatching();
            }
            break;
          }
        }
      }
    }
  }

  protected void changeReference(EReference eReference)
  {
    // Do nothing.
  }

  protected Node getAdoptionParentNode(EReference eReference)
  {
    return getElement();
  }

  protected void handleElementForAdopt(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    Node adoptionParent = getAdoptionParentNode(eReference);
    Element childElement = xsdConcreteComponent.getElement();
    Element referencedElement = null;
    for (Iterator<XSDConcreteComponent> contents = getXSDContents().iterator(); contents.hasNext(); )
    {
      if (contents.next() == xsdConcreteComponent)
      {
        if (contents.hasNext())
        {
          referencedElement = contents.next().getElement();
          while (referencedElement != null)
          {
            Node parent = referencedElement.getParentNode(); 
            if (parent == null )
            {
              referencedElement = null;
              break;
            }
            else if (parent == adoptionParent)
            {
              break;
            }
            else if (parent.getNodeType() == Node.ELEMENT_NODE)
            {
              referencedElement = (Element)parent;
            }
            else
            {
              break;
            }
          }
        }
        break;
      }
    }

    if (childElement == null)
    {
      ((XSDConcreteComponentImpl)xsdConcreteComponent).isReconciling = true;
      childElement = ((XSDConcreteComponentImpl)xsdConcreteComponent).createElement();
      ((XSDConcreteComponentImpl)xsdConcreteComponent).isReconciling = false;
      if (childElement == null)
      {
         System.out.println("not created! " + xsdConcreteComponent);
      }
    }

    boolean isAttached = false;
    for (Node rootDocument = childElement; rootDocument != null; rootDocument = rootDocument.getParentNode())
    {
      if (XSDConstants.nodeType(rootDocument) == XSDConstants.SCHEMA_ELEMENT)
      {
        isAttached = true;
        break;
      }
    }

    if (!isAttached)
    {
      // Special rule for annotations that must appear first.
      //
      if (referencedElement == null && 
            XSDConstants.nodeType(childElement) == XSDConstants.ANNOTATION_ELEMENT &&
            !eReference.isMany())
      {
        for (Node child = adoptionParent.getFirstChild(); child != null; child = child.getNextSibling())
        {
          if (child.getNodeType() == Node.ELEMENT_NODE)
          {
            referencedElement = (Element)child;
            break;
          }
        }
      }

      niceInsertBefore(adoptionParent, childElement, referencedElement);
    }
    else
    {
      // System.out.println("bad adoption " + xsdConcreteComponent);
    }
  }

  protected void adoptContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    if (isReconciling)
    {
      if (xsdConcreteComponent.getElement() != null)
      { 
        xsdConcreteComponent.elementChanged(xsdConcreteComponent.getElement());
      }
    }
    else
    {
      Element childElement = xsdConcreteComponent.getElement();
      if (getElement() != null && (childElement == null || childElement.getParentNode() == null))
      {
        if (childElement != null && childElement.getOwnerDocument() != getElement().getOwnerDocument())
        {
          xsdConcreteComponent.setElement(null);
          childElement = null;
        }

        handleElementForAdopt(eReference, xsdConcreteComponent);
        xsdConcreteComponent.updateElement();
      }
    }

    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      ((XSDConcreteComponentImpl)xsdConcreteComponent).adoptBy(xsdSchema);
    }
  }

  protected void adoptBy(XSDSchema xsdSchema)
  {
    List<XSDDiagnostic> theDiagnostics = getDiagnostics();
    if (!theDiagnostics.isEmpty())
    {
      xsdSchema.getDiagnostics().addAll(theDiagnostics);
    }

    for (XSDConcreteComponentImpl childXSDConcreteComponent : getXSDConcreteComponentImpls())
    {
      childXSDConcreteComponent.adoptBy(xsdSchema);
    }
  }

  protected void adoptDiagnostic(XSDDiagnostic xsdDiagnostic)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      xsdSchema.getAllDiagnostics().add(xsdDiagnostic);
    }
  }


  protected void orphanContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    if (!isReconciling)
    {
      Element parent = getElement();
      if (parent != null)
      {
        Element contentElement = xsdConcreteComponent.getElement();
        if (contentElement != null)
        {
          if (parent == contentElement)
          {
            XSDConcreteComponentImpl parentComponent = (XSDConcreteComponentImpl)getContainer();
            boolean oldParentUpdatingDOM = parentComponent.updatingDOM;
            parentComponent.updatingDOM = true;
            niceRemoveChild(contentElement.getParentNode(), contentElement);
            parentComponent.updatingDOM = oldParentUpdatingDOM;
          }
          else
          {
            niceRemoveChild(contentElement.getParentNode(), contentElement);
          }
        }
      }
    }

    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      ((XSDConcreteComponentImpl)xsdConcreteComponent).orphanBy(xsdSchema);
    }
  }

  protected void orphanBy(XSDSchema xsdSchema)
  {
    List<XSDDiagnostic> theDiagnostics = getDiagnostics();
    if (!theDiagnostics.isEmpty())
    {
      xsdSchema.getAllDiagnostics().removeAll(theDiagnostics);
    }
    for (XSDConcreteComponentImpl childXSDConcreteComponent : getXSDConcreteComponentImpls())
    {
      childXSDConcreteComponent.orphanBy(xsdSchema);
    }
  }

  protected void orphanDiagnostic(XSDDiagnostic xsdDiagnostic)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      xsdSchema.getAllDiagnostics().remove(xsdDiagnostic);
    }
  }


  public void moveContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    if (isReconciling)
    {
      // System.out.println("**** cyclic dom writeback avoided " + eClass().getName());
      return;
    }
    //System.out.println("moving " + xsdConcreteComponent);
    Node parent = getAdoptionParentNode(eReference);
    if (parent != null)
    {
      Element child = xsdConcreteComponent.getElement();
      if (child != null)
      {
        List<XSDConcreteComponent> contents = getXSDContents();
        int index = contents.indexOf(xsdConcreteComponent);
        niceRemoveChild(parent, child);
        niceInsertBefore
          (parent, 
            child, 
            ++index == contents.size() ?  
              null : 
              contents.get(index).getElement());
      }
    }
    //System.out.println("moved " + xsdConcreteComponent);
  }

  public void niceInsertBefore(Node parent, Node newChild, Node referenceChild)
  {
    if (isReconciling || parent == newChild)
    {
      // System.out.println("**** cyclic dom writeback avoided " + eClass().getName());
      return;
    }

    forceNiceInsertBefore(parent, newChild, referenceChild);
  }

  public void forceNiceInsertBefore(Node parent, Node newChild, Node referenceChild)
  {
    updatingDOM = true;

    LOOP:
    for (Node child = referenceChild == null ? parent.getLastChild() : referenceChild.getPreviousSibling(); 
         child != null; 
         child = child.getPreviousSibling())
    {
      switch (child.getNodeType())
      {
        case Node.TEXT_NODE:
        {
          Text text = (Text)child;
          String data = text.getData();

/*
          System.out.print("xx " + data.length() + ":: ");
          for (int i = 0; i < data.length(); ++i)
          {
            System.out.print(" " + Integer.toHexString((int)data.charAt(i)));
          }
          System.out.println();
*/

          int index = data.lastIndexOf('\n');
          if (index != -1)
          {
// System.out.println("In here");

            StringBuffer indent = new StringBuffer();
            for (Node ancestor = parent.getParentNode(); 
                 ancestor != null && ancestor.getNodeType() != Node.DOCUMENT_NODE; 
                 ancestor = ancestor.getParentNode())
            {
              indent.append("    ");
            }

            if (index + 1 < data.length() && data.charAt(index + 1) == '\r')
            {
              ++index;
            }
            if (index + 1 == data.length())
            {
              text.appendData(indent + "    ");
            }
            else
            {
              text.replaceData(index + 1, data.length() - index - 1, indent + "    ");
            }

            // setCorrectIndentation(indent, newText);
            if (referenceChild != null)
            {
              indent.append("    ");
            }
            Text newText = parent.getOwnerDocument().createTextNode("\n" + indent);
// System.out.println("Inserted..." + newText);
            parent.insertBefore(newText, referenceChild);
            referenceChild = newText;
            break LOOP;
          }

          break;
        } 
        case Node.ELEMENT_NODE:
        {
          break LOOP;
        }
      }
    }

// System.out.println("Inserted..." + newChild);
    parent.insertBefore(newChild, referenceChild);

    updatingDOM = false;
  }

  public void niceRemoveChild(Node parent, Node child)
  {
    if (isReconciling)
    {
      // System.out.println("**** cyclic dom writeback avoided " + eClass().getName());
      return;
    }
    forceNiceRemoveChild(parent, child);
  }

  public void forceNiceRemoveChild(Node parent, Node child)
  {
    updatingDOM = true;

    boolean done = false;

// System.out.println("?*");
    Node previous = child.getPreviousSibling();
    if (previous != null && previous.getNodeType() == Node.TEXT_NODE)
    {
      Text text = (Text)previous;
      String data = text.getData();
      int index = data.lastIndexOf('\n');
      if (index != -1)
      {
        if (index - 1 > 0 && data.charAt(index - 1) == '\r')
        {
//System.out.println("1*");
          text.deleteData(index - 1, data.length() - index + 1);
        }
        else
        {
//System.out.println("2*");
          text.deleteData(index, data.length() - index);
        }
        done = true;
      }
      else
      {
        //System.out.println("No return before!");
      }
    }
    else
    {
      // System.out.println("No text node before!");
    }


    if (!done)
    {
      for (Node next = child.getNextSibling(); next != null; next = next.getNextSibling())
      {
        if (next.getNodeType() == Node.TEXT_NODE)
        {
          Text text = (Text)next;
          String data = text.getData();
/*
           System.out.print("xx " + data.length() + ":: ");
          for (int i = 0; i < data.length(); ++i)
          {
             System.out.print(" " + Integer.toHexString((int)data.charAt(i)));
          }
          System.out.println();
*/

          int index = data.indexOf('\n');
          if (index != -1)
          {
            if (index + 1 < data.length() && data.charAt(index + 1) == '\r')
            {
    // System.out.println("3*");
              text.deleteData(0, index + 2);
            }
            else
            {
    //System.out.println("4*");
              text.deleteData(0, index + 1);
            }
            break;
          }
          else
          {
            // System.out.println("No return after!");
          }
        }
        else if (next.getNodeType() == Node.ELEMENT_NODE)
        {
          break;
        }
      }
    }

// System.out.println("Removing from--------\n     " + parent);
//System.out.println("Removing--------\n     " + child);
    parent.removeChild(child);

    updatingDOM=false;
  }

  public void forceReplace(Element replacementElement, Element referenceElement)
  {
    isReconciling = true;
    updatingDOM = true;

    XSDConcreteComponentImpl container = (XSDConcreteComponentImpl)getContainer();
    container.isReconciling = true;
    container.updatingDOM = true;

    Node parent = referenceElement.getParentNode();
    XSDConcreteComponentImpl containerOfContainer =
      parent != container.getElement() ?
        (XSDConcreteComponentImpl)container.getContainer() :
        null;
    if (containerOfContainer != null)
    {
      containerOfContainer.isReconciling = true;
      containerOfContainer.updatingDOM = true;
    }

    for (Node child = referenceElement.getFirstChild(); child != null; )
    {
      Node node = child;
      child = child.getNextSibling();
      referenceElement.removeChild(node);
      replacementElement.appendChild(node);
    }

    org.w3c.dom.NamedNodeMap attributes = referenceElement.getAttributes();
    for (int i = 0, length = attributes.getLength(); i < length; ++i)
    {
      replacementElement.setAttributeNode((org.w3c.dom.Attr)attributes.item(i).cloneNode(true));
    }

    forceNiceInsertBefore(parent, replacementElement, referenceElement);
    forceNiceRemoveChild(parent, referenceElement);

    updatingDOM = false;
    isReconciling = false;

    container.updatingDOM = false;
    container.isReconciling = false;

    if (containerOfContainer != null)
    {
      containerOfContainer.updatingDOM = false;
      containerOfContainer.isReconciling = false;
    }
  }

  public void forceEngulf(Element newElement, Element engulfingElement, Element referenceElement, Node referenceChild)
  {
    isReconciling = true;
    updatingDOM = true;

    forceNiceInsertBefore(referenceElement, newElement, referenceChild);

    for (Node child = referenceChild; child != null; )
    {
      Node node = child;
      child = child.getNextSibling();
      forceNiceRemoveChild(referenceElement, node);
      forceNiceInsertBefore(engulfingElement, node, null);
    }

    updatingDOM = false;
    isReconciling = false;
  }

  public void forceEngulf(Node firstAdoptee, Element engulfingElement, Node referenceChild)
  {
    isReconciling = true;
    updatingDOM = true;

    Node parent = firstAdoptee.getParentNode();
    for (Node child = firstAdoptee; child != null; )
    {
      Node node = child;
      child = child.getNextSibling();
      forceNiceRemoveChild(parent, node);
      forceNiceInsertBefore(engulfingElement, node, referenceChild);
    }

    updatingDOM = false;
    isReconciling = false;
  }

  protected void niceSetAttribute(Element element, String attribute, String value)
  {
    if (isReconciling)
    {
      // System.out.println("**** cyclic dom attribute writeback avoided " + eClass().getName());
      return;
    }

    if (value == null)
    {
      if (element.hasAttributeNS(null, attribute))
      {
        updatingDOM = true;
        element.removeAttribute(attribute);
        updatingDOM=false;
      }
    }
    else if (!element.hasAttributeNS(null, attribute) || !element.getAttributeNS(null, attribute).equals(value))
    {
      updatingDOM = true;
      element.setAttributeNS(null, attribute, value);
      updatingDOM=false;
    }
  }

  protected String niceCreateNamespaceAttribute(String namespace)
  {
    XSDSchema xsdSchema = getSchema();
    if (namespace == null)
    {
      namespace = "";
    }
    if (xsdSchema != null && xsdSchema.getElement() != null)
    {
      Element schemaElement = xsdSchema.getElement();
      if (XSDConstants.isSchemaForSchemaNamespace(namespace))
      {
        if (!schemaElement.hasAttributeNS(XSDConstants.XMLNS_URI_2000, "xsd"))
        {
          schemaElement.setAttributeNS(XSDConstants.XMLNS_URI_2000, "xmlns:xsd", namespace);
          return "xsd";
        }
      }

      String qualifier = "Q";
      int count = 1;
      while (schemaElement.hasAttributeNS(XSDConstants.XMLNS_URI_2000, qualifier + count))
      {
        ++count;
      }
      schemaElement.setAttributeNS(XSDConstants.XMLNS_URI_2000, "xmlns:" + qualifier + count, namespace);
      return qualifier + count;
    }
    else
    {
      return null;
    }
  }

  protected void niceSetAttributeURIValue(Element element, String attribute, String uriList)
  {
    if (isReconciling)
    {
      // System.out.println("**** cyclic dom attribute writeback avoided " + eClass().getName());
      return;
    }

    if (uriList == null)
    {
      updatingDOM = true;
      element.removeAttribute(attribute);
      updatingDOM=false;
    }
    else
    {
      StringBuffer value = new StringBuffer();
      for (StringTokenizer stringTokenizer = new StringTokenizer(uriList, " "); stringTokenizer.hasMoreElements(); )
      {
        String uri =  stringTokenizer.nextToken();
        String namespace = null;
        String localName = uri;
        int index = uri.lastIndexOf("#");
        if (index != -1)
        {
          if (index > 0)
          {
            namespace = uri.substring(0, index);
          }
          localName =  uri.substring(index + 1);
        }
        String qualifier = XSDConstants.lookupQualifier(element, namespace);
        if (qualifier == null)
        {
          qualifier = niceCreateNamespaceAttribute(namespace);
        }

        String qName = qualifier == null || qualifier.length() == 0 ? localName : qualifier + ":" + localName;

        if (value.length() != 0)
        {
          value.append(' ');
        }
        value.append(qName);
      }

      if (!element.hasAttributeNS(null, attribute) || !element.getAttributeNS(null, attribute).equals(value.toString()))
      {
        updatingDOM = true;
        element.setAttributeNS(null, attribute, value.toString());
        updatingDOM=false;
      }
    }
  }

  public XSDConcreteComponent getContainer()
  {
    return 
      eContainer instanceof XSDConcreteComponent ?
        (XSDConcreteComponent)eContainer :
         null;
  }

  public XSDConcreteComponent getRootContainer()
  {
    for (XSDConcreteComponent root = this;;)
    {
      XSDConcreteComponent parent = root.getContainer();
      if (parent == null)
      {
        return root;
      }
      root = parent;
    }
  }

  public boolean contains(XSDConcreteComponent xsdConcreteComponent)
  {
    while (xsdConcreteComponent != null)
    {
      if (xsdConcreteComponent == this)
      {
        return true;
      }
      xsdConcreteComponent = xsdConcreteComponent.getContainer();
    }
    return false;
  }

  public XSDSchema getSchema()
  {
    for (XSDConcreteComponent container = this; container != null; container = container.getContainer())
    {
      if (container instanceof XSDSchema)
      {
        return (XSDSchema)container;
      }
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDDiagnostic> getDiagnostics()
  {
    if (diagnostics == null)
    {
      diagnostics = new EObjectContainmentEList<XSDDiagnostic>(XSDDiagnostic.class, this, XSDPackage.XSD_CONCRETE_COMPONENT__DIAGNOSTICS);
    }
    return diagnostics;
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
      case XSDPackage.XSD_CONCRETE_COMPONENT__DIAGNOSTICS:
        return ((InternalEList<?>)getDiagnostics()).basicRemove(otherEnd, msgs);
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
      case XSDPackage.XSD_CONCRETE_COMPONENT__ELEMENT:
        return getElement();
      case XSDPackage.XSD_CONCRETE_COMPONENT__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_CONCRETE_COMPONENT__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_CONCRETE_COMPONENT__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_CONCRETE_COMPONENT__DIAGNOSTICS:
        return getDiagnostics();
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
      case XSDPackage.XSD_CONCRETE_COMPONENT__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_CONCRETE_COMPONENT__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection<? extends XSDDiagnostic>)newValue);
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
      case XSDPackage.XSD_CONCRETE_COMPONENT__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_CONCRETE_COMPONENT__DIAGNOSTICS:
        getDiagnostics().clear();
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
      case XSDPackage.XSD_CONCRETE_COMPONENT__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_CONCRETE_COMPONENT__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_CONCRETE_COMPONENT__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_CONCRETE_COMPONENT__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_CONCRETE_COMPONENT__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
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
    result.append(" (element: ");
    result.append(element);
    result.append(')');
    return result.toString();
  }

  public XSDConcreteComponent getBestConcreteComponent(List<Element> elementPath)
  {
    XSDConcreteComponent result = this;
    for (XSDConcreteComponentImpl childXSDConcreteComponent : getXSDConcreteComponentImpls())
    {
      if (elementPath.contains(childXSDConcreteComponent.getElement()))
      {
        result = childXSDConcreteComponent;
        XSDConcreteComponent betterXSDConcreteComponent = childXSDConcreteComponent.getBestConcreteComponent(elementPath);
        if (betterXSDConcreteComponent != null)
        {
          result = betterXSDConcreteComponent;
        }

        if (!considerAllContainsForBestConcreteComponent())
        {
          break;
        }
      }
    }

    return result;
  }

  protected boolean considerAllContainsForBestConcreteComponent()
  {
    return false;
  }

  public static <T> void setListContentAndOrder(EList<T> targetList, List<? extends T> prototypeList)
  {
    for (int index = 0, size = prototypeList.size(); index < size; ++index)
    {
      T prototypeObject = prototypeList.get(index);
      if (targetList.size() <= index)
      {
        targetList.add(prototypeObject);
      }
      else
      {
        Object targetObject = targetList.get(index);
        if (targetObject == null ? prototypeObject != null : !targetObject.equals(prototypeObject))
        {
          if (targetList.contains(prototypeObject))
          {
            targetList.move(index, prototypeObject);
          }
          else
          {
            targetList.add(index, prototypeObject);
          }
        }
      }
    }
  }

  protected String getURIReferenceLabel()
  {
    return null;
  }

  @Override
  public String eURIFragmentSegment(EStructuralFeature eStructuralFeature, EObject eObject)
  {
    StringBuffer result = new StringBuffer();
    XSDConcreteComponent xsdConcreteComponent = (XSDConcreteComponent)eObject;
    String sort = xsdConcreteComponent.eClass().getName();
    int sortCount = 0;
    int labelCount = 0;
    String uriReferenceLabel = ((XSDConcreteComponentImpl)xsdConcreteComponent).getURIReferenceLabel();
    @SuppressWarnings("unchecked") EList<XSDConcreteComponentImpl> eContents = (EList<XSDConcreteComponentImpl>)(EList<?>)eContents();
    for (XSDConcreteComponentImpl sibling : eContents)
    {
      if (sibling == eObject)
      {
        break;
      }
      if (sibling.eClass().getName().equals(sort))
      {
        ++sortCount;
        if (uriReferenceLabel != null && 
              uriReferenceLabel.equals(sibling.getURIReferenceLabel()))
        {
          ++labelCount;
        }
      }
    }

    if (uriReferenceLabel != null)
    {
      result.append(uriReferenceLabel);
      if (labelCount != 0)
      {
        result.append("=");
        result.append(labelCount);
      }
      result.append(";");
    }
    result.append(sort);
    if (sortCount != 0)
    {
      result.append("=");
      result.append(sortCount);
    }

    return result.toString();
  }

  @Override
  public EObject eObjectForURIFragmentSegment(String uriFragmentSegment)
  {
    EObject  result = null;
    if (uriFragmentSegment.startsWith("@"))
    {
      result = super.eObjectForURIFragmentSegment(uriFragmentSegment);
    }
    else
    {
      StringTokenizer stringTokenizer = new StringTokenizer(uriFragmentSegment, ";=,", true); 

      String uriReferenceLabel = null;
      String sort = stringTokenizer.nextToken();
      if (Character.isDigit(sort.charAt(0)))
      {
        int diagnosticIndex = 0;
        try
        {
          diagnosticIndex = Integer.parseInt(sort);
        }
        catch (NumberFormatException exception)
        {
          // Ignore.
        }
        List<XSDDiagnostic> theDiagnostics = getDiagnostics();
        if (theDiagnostics.size() > diagnosticIndex)
        {
          result = theDiagnostics.get(diagnosticIndex);
        }
      }
      else
      {
        EReference eReference = null;
        int labelCount = 0;
        int sortCount = 0;
        String prefix = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "";
        if (prefix.equals(","))
        {
          String relationName = sort;
          eReference = (EReference)eClass().getEStructuralFeature(relationName);
          sort = stringTokenizer.nextToken();
          prefix = stringTokenizer.nextToken();
        }

        if (prefix.equals("="))
        {
          String sortCountString = stringTokenizer.nextToken();
          try
          {
            sortCount = Integer.parseInt(sortCountString);
          }
          catch (NumberFormatException exception)
          {
            // Ignore.
          }
          prefix = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "";
        }
        if (prefix.equals(";"))
        {
          uriReferenceLabel = sort;
          labelCount = sortCount;
          sort = stringTokenizer.nextToken();
          sortCount = 0;
          if (stringTokenizer.hasMoreTokens())
          {
            prefix = stringTokenizer.nextToken();
            if (prefix.equals("="))
            {
              String sortCountString = stringTokenizer.nextToken();
              try
              {
                sortCount = Integer.parseInt(sortCountString);
              }
              catch (NumberFormatException exception)
              {
                // Ignore.
              }
            }
          }
        }

        @SuppressWarnings("unchecked")
        Collection<XSDConcreteComponentImpl> collection =
          eReference == null ?
            (Collection<XSDConcreteComponentImpl>)(Collection<?>)eContents() :
            eReference.isMany() ?
              (Collection<XSDConcreteComponentImpl>)eGet(eReference) :
              Collections.singleton((XSDConcreteComponentImpl)eGet(eReference));

        EObject candidate = null;
        for (XSDConcreteComponentImpl child : collection)
        {
          if (sortCount >= 0 && child.eClass().getName().equals(sort))
          {
            --sortCount;
            candidate = child;
            if (sortCount == -1 && uriReferenceLabel == null)
            {
              break;
            }
          }
          if (uriReferenceLabel != null && 
                uriReferenceLabel.equals(child.getURIReferenceLabel()) &&
                child.eClass().getName().equals(sort))
          {
            if (labelCount-- == 0)
            {
              candidate = child;
              break;
            }
          }
        }

        result = candidate;
      }
    }

    return result;
  }

  public XSDTypeDefinition resolveTypeDefinitionURI(String uri)
  {
    int index = uri.lastIndexOf("#");
    if (index == -1)
    {
      return resolveTypeDefinition(null, uri);
    }
    else
    {
      return resolveTypeDefinition(uri.substring(0, index), uri.substring(index + 1));
    }
  }

  public XSDTypeDefinition resolveTypeDefinition(String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveTypeDefinition(xsdSchema.getTargetNamespace(), localName);
    }
    else
    {
      return createUnresolvedTypeDefinition(null, localName);
    }
  }

  public XSDTypeDefinition resolveTypeDefinition(String namespace, String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveTypeDefinition(namespace, localName);
    }
    else
    {
      return createUnresolvedTypeDefinition(namespace, localName);
    }
  }

  protected XSDTypeDefinition createUnresolvedTypeDefinition(String namespace, String localName)
  {
    return createUnresolvedSimpleTypeDefinition(namespace, localName);
  }



  public XSDSimpleTypeDefinition resolveSimpleTypeDefinitionURI(String uri)
  {
    int index = uri.lastIndexOf("#");
    if (index == -1)
    {
      return resolveSimpleTypeDefinition(null, uri);
    }
    else
    {
      return resolveSimpleTypeDefinition(uri.substring(0, index), uri.substring(index + 1));
    }
  }

  public XSDSimpleTypeDefinition resolveSimpleTypeDefinition(String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveSimpleTypeDefinition(xsdSchema.getTargetNamespace(), localName);
    }
    else
    {
      return createUnresolvedSimpleTypeDefinition(null, localName);
    }
  }

  public XSDSimpleTypeDefinition resolveSimpleTypeDefinition(String namespace, String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveSimpleTypeDefinition(namespace, localName);
    }
    else
    {
      return createUnresolvedSimpleTypeDefinition(namespace, localName);
    }
  }

  protected XSDSimpleTypeDefinition createUnresolvedSimpleTypeDefinition(String namespace, String localName)
  {
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = XSDFactory.eINSTANCE.createXSDSimpleTypeDefinition();
    if (localName != null)
    {
      xsdSimpleTypeDefinition.setName(localName);
    }
    if (namespace != null)
    {
      xsdSimpleTypeDefinition.setTargetNamespace(namespace);
    }
    xsdSimpleTypeDefinition.setVariety(XSDVariety.ATOMIC_LITERAL);
    return xsdSimpleTypeDefinition;
  }

  public XSDComplexTypeDefinition resolveComplexTypeDefinitionURI(String uri)
  {
    int index = uri.lastIndexOf("#");
    if (index == -1)
    {
      return resolveComplexTypeDefinition(null, uri);
    }
    else
    {
      return resolveComplexTypeDefinition(uri.substring(0, index), uri.substring(index + 1));
    }
  }

  public XSDComplexTypeDefinition resolveComplexTypeDefinition(String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveComplexTypeDefinition(xsdSchema.getTargetNamespace(), localName);
    }
    else
    {
      return createUnresolvedComplexTypeDefinition(null, localName);
    }
  }

  public XSDComplexTypeDefinition resolveComplexTypeDefinition(String namespace, String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveComplexTypeDefinition(namespace, localName);
    }
    else
    {
      return createUnresolvedComplexTypeDefinition(namespace, localName);
    }
  }

  protected XSDComplexTypeDefinition createUnresolvedComplexTypeDefinition(String namespace, String localName)
  {
    XSDComplexTypeDefinition xsdComplexTypeDefinition = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
    if (localName != null)
    {
      xsdComplexTypeDefinition.setName(localName);
    }
    if (namespace != null)
    {
      xsdComplexTypeDefinition.setTargetNamespace(namespace);
    }
    return xsdComplexTypeDefinition;
  }




  public XSDAttributeDeclaration resolveAttributeDeclarationURI(String uri)
  {
    int index = uri.lastIndexOf("#");
    if (index == -1)
    {
      return resolveAttributeDeclaration(null, uri);
    }
    else
    {
      return resolveAttributeDeclaration(uri.substring(0, index), uri.substring(index + 1));
    }
  }

  public XSDAttributeDeclaration resolveAttributeDeclaration(String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveAttributeDeclaration(xsdSchema.getTargetNamespace(), localName);
    }
    else
    {
      return createUnresolvedAttributeDeclaration(null, localName);
    }
  }

  public XSDAttributeDeclaration resolveAttributeDeclaration(String namespace, String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveAttributeDeclaration(namespace, localName);
    }
    else
    {
      return createUnresolvedAttributeDeclaration(namespace, localName);
    }
  }

  protected XSDAttributeDeclaration createUnresolvedAttributeDeclaration(String namespace, String localName)
  {
    XSDAttributeDeclaration xsdAttributeDeclaration = XSDFactory.eINSTANCE.createXSDAttributeDeclaration();
    if (localName != null)
    {
      xsdAttributeDeclaration.setName(localName);
    }
    if (namespace != null)
    {
      xsdAttributeDeclaration.setTargetNamespace(namespace);
    }
    xsdAttributeDeclaration.setResolvedAttributeDeclaration(xsdAttributeDeclaration);
    return xsdAttributeDeclaration;
  }



  public XSDElementDeclaration resolveElementDeclarationURI(String uri)
  {
    int index = uri.lastIndexOf("#");
    if (index == -1)
    {
      return resolveElementDeclaration(null, uri);
    }
    else
    {
      return resolveElementDeclaration(uri.substring(0, index), uri.substring(index + 1));
    }
  }

  public XSDElementDeclaration resolveElementDeclaration(String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveElementDeclaration(xsdSchema.getTargetNamespace(), localName);
    }
    else
    {
      return createUnresolvedElementDeclaration(null, localName);
    }
  }

  public XSDElementDeclaration resolveElementDeclaration(String namespace, String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveElementDeclaration(namespace, localName);
    }
    else
    {
      return createUnresolvedElementDeclaration(namespace, localName);
    }
  }

  protected XSDElementDeclaration createUnresolvedElementDeclaration(String namespace, String localName)
  {
    XSDElementDeclaration xsdElementDeclaration = XSDFactory.eINSTANCE.createXSDElementDeclaration();
    if (localName != null)
    {
      xsdElementDeclaration.setName(localName);
    }
    if (namespace != null)
    {
      xsdElementDeclaration.setTargetNamespace(namespace);
    }
    xsdElementDeclaration.setResolvedElementDeclaration(xsdElementDeclaration);
    return xsdElementDeclaration;
  }





  public XSDAttributeGroupDefinition resolveAttributeGroupDefinitionURI(String uri)
  {
    int index = uri.lastIndexOf("#");
    if (index == -1)
    {
      return resolveAttributeGroupDefinition(null, uri);
    }
    else
    {
      return resolveAttributeGroupDefinition(uri.substring(0, index), uri.substring(index + 1));
    }
  }

  public XSDAttributeGroupDefinition resolveAttributeGroupDefinition(String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveAttributeGroupDefinition(xsdSchema.getTargetNamespace(), localName);
    }
    else
    {
      return createUnresolvedAttributeGroupDefinition(null, localName);
    }
  }

  public XSDAttributeGroupDefinition resolveAttributeGroupDefinition(String namespace, String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveAttributeGroupDefinition(namespace, localName);
    }
    else
    {
      return createUnresolvedAttributeGroupDefinition(namespace, localName);
    }
  }

  protected XSDAttributeGroupDefinition createUnresolvedAttributeGroupDefinition(String namespace, String localName)
  {
    XSDAttributeGroupDefinition xsdAttributeGroupDefinition = XSDFactory.eINSTANCE.createXSDAttributeGroupDefinition();
    if (localName != null)
    {
      xsdAttributeGroupDefinition.setName(localName);
    }
    if (namespace != null)
    {
      xsdAttributeGroupDefinition.setTargetNamespace(namespace);
    }
    xsdAttributeGroupDefinition.setResolvedAttributeGroupDefinition(xsdAttributeGroupDefinition);
    return xsdAttributeGroupDefinition;
  }



  public XSDModelGroupDefinition resolveModelGroupDefinitionURI(String uri)
  {
    int index = uri.lastIndexOf("#");
    if (index == -1)
    {
      return resolveModelGroupDefinition(null, uri);
    }
    else
    {
      return resolveModelGroupDefinition(uri.substring(0, index), uri.substring(index + 1));
    }
  }

  public XSDModelGroupDefinition resolveModelGroupDefinition(String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveModelGroupDefinition(xsdSchema.getTargetNamespace(), localName);
    }
    else
    {
      return createUnresolvedModelGroupDefinition(null, localName);
    }
  }

  public XSDModelGroupDefinition resolveModelGroupDefinition(String namespace, String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveModelGroupDefinition(namespace, localName);
    }
    else
    {
      return createUnresolvedModelGroupDefinition(namespace, localName);
    }
  }

  protected XSDModelGroupDefinition createUnresolvedModelGroupDefinition(String namespace, String localName)
  {
    XSDModelGroupDefinition xsdModelGroupDefinition = XSDFactory.eINSTANCE.createXSDModelGroupDefinition();
    if (localName != null)
    {
      xsdModelGroupDefinition.setName(localName);
    }
    if (namespace != null)
    {
      xsdModelGroupDefinition.setTargetNamespace(namespace);
    }
    xsdModelGroupDefinition.setResolvedModelGroupDefinition(xsdModelGroupDefinition);
    XSDModelGroup xsdModelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
    xsdModelGroup.setCompositor(XSDCompositor.SEQUENCE_LITERAL);
    xsdModelGroupDefinition.setModelGroup(xsdModelGroup);
    return xsdModelGroupDefinition;
  }

  public XSDIdentityConstraintDefinition resolveIdentityConstraintDefinitionURI(String uri)
  {
    int index = uri.lastIndexOf("#");
    if (index == -1)
    {
      return resolveIdentityConstraintDefinition(null, uri);
    }
    else
    {
      return resolveIdentityConstraintDefinition(uri.substring(0, index), uri.substring(index + 1));
    }
  }

  public XSDIdentityConstraintDefinition resolveIdentityConstraintDefinition(String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveIdentityConstraintDefinition(xsdSchema.getTargetNamespace(), localName);
    }
    else
    {
      return createUnresolvedIdentityConstraintDefinition(null, localName);
    }
  }

  public XSDIdentityConstraintDefinition resolveIdentityConstraintDefinition(String namespace, String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveIdentityConstraintDefinition(namespace, localName);
    }
    else
    {
      return createUnresolvedIdentityConstraintDefinition(namespace, localName);
    }
  }

  protected XSDIdentityConstraintDefinition createUnresolvedIdentityConstraintDefinition(String namespace, String localName)
  {
    XSDIdentityConstraintDefinition xsdIdentityConstraintDefinition = 
      XSDFactory.eINSTANCE.createXSDIdentityConstraintDefinition();
    xsdIdentityConstraintDefinition.setIdentityConstraintCategory(XSDIdentityConstraintCategory.KEY_LITERAL);
    if (localName != null)
    {
      xsdIdentityConstraintDefinition.setName(localName);
    }
    if (namespace != null)
    {
      xsdIdentityConstraintDefinition.setTargetNamespace(namespace);
    }
    return xsdIdentityConstraintDefinition;
  }

  public XSDNotationDeclaration resolveNotationDeclarationURI(String uri)
  {
    int index = uri.lastIndexOf("#");
    if (index == -1)
    {
      return resolveNotationDeclaration(null, uri);
    }
    else
    {
      return resolveNotationDeclaration(uri.substring(0, index), uri.substring(index + 1));
    }
  }

  public XSDNotationDeclaration resolveNotationDeclaration(String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveNotationDeclaration(xsdSchema.getTargetNamespace(), localName);
    }
    else
    {
      return createUnresolvedNotationDeclaration(null, localName);
    }
  }

  public XSDNotationDeclaration resolveNotationDeclaration(String namespace, String localName)
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      return xsdSchema.resolveNotationDeclaration(namespace, localName);
    }
    else
    {
      return createUnresolvedNotationDeclaration(namespace, localName);
    }
  }

  protected XSDNotationDeclaration createUnresolvedNotationDeclaration(String namespace, String localName)
  {
    XSDNotationDeclaration xsdNotationDeclaration = 
      XSDFactory.eINSTANCE.createXSDNotationDeclaration();
    if (localName != null)
    {
      xsdNotationDeclaration.setName(localName);
    }
    if (namespace != null)
    {
      xsdNotationDeclaration.setTargetNamespace(namespace);
    }
    return xsdNotationDeclaration;
  }

  public Collection<XSDConcreteComponent> getComponentsWithApplicationInformation(String sourceURI)
  {
    Collection<XSDConcreteComponent> result = new HashSet<XSDConcreteComponent>();
    getComponentsWithInformation(result, XSDConstants.APPINFO_ELEMENT, sourceURI);
    return result;
  }

  public Collection<XSDConcreteComponent> getComponentsWithUserInformation(String sourceURI)
  {
    Collection<XSDConcreteComponent> result = new HashSet<XSDConcreteComponent>();
    getComponentsWithInformation(result, XSDConstants.DOCUMENTATION_ELEMENT, sourceURI);
    return result;
  }

  protected void getComponentsWithInformation(Collection<XSDConcreteComponent> result, int nodeType, String sourceURI)
  {
    for (XSDConcreteComponentImpl xsdConcreteComponent : getXSDConcreteComponentImpls())
    {
      xsdConcreteComponent.getComponentsWithInformation(result, nodeType, sourceURI);
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Element getElement()
  {
    return element;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElementGen(Element newElement)
  {
    Element oldElement = element;
    element = newElement;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_CONCRETE_COMPONENT__ELEMENT, oldElement, element));
  }

/*
  public void unsetElement()
  {
    isReconciling = true;
    unsetElementGen();
    for (Iterator contents = getXSDContents().iterator(); contents.hasNext(); )
    {
      XSDConcreteComponentImpl content = (XSDConcreteComponentImpl)contents.next();
      content.unsetElement();
    }
    isReconciling = false;
  }
*/

  public void setElement(Element element)
  {
    if (element == null)
    {
      isReconciling = true;
      setElementGen(null);
      for (XSDConcreteComponentImpl content : getXSDConcreteComponentImpls())
      {
        content.setElement(null);
      }
      isReconciling = false;
    }
    else
    {
      setElementGen(element);
    }
  }

  /**
   */
  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    throw new RuntimeException("Unimplemented: XSDConcreteComponent.cloneConcreteComponent(boolean deep, boolean shareDOM)");
  }

  protected static <T extends XSDConcreteComponent> List<T> cloneConcreteComponents(List<T> xsdConcreteComponents, boolean deep, boolean shareDOM)
  {
    List<T> result = new ArrayList<T>(xsdConcreteComponents.size());
    for (XSDConcreteComponent xsdConcreteComponent : xsdConcreteComponents)
    {
      try
      {
        @SuppressWarnings("unchecked") 
        T clonedConcreteComponent = (T)xsdConcreteComponent.cloneConcreteComponent(deep, shareDOM);
        result.add(clonedConcreteComponent);
      }
      catch (Exception exception)
      {
        exception.printStackTrace();
      }
    }
    return result;
  }

  @Override
  public boolean eNotificationRequired()
  {
    return true;
  }
}
