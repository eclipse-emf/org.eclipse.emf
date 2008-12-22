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
 * $Id: XSDAttributeGroupDefinitionImpl.java,v 1.19 2008/12/22 14:25:48 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeGroupContent;
import org.eclipse.xsd.XSDAttributeGroupDefinition;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDAttributeUseCategory;
import org.eclipse.xsd.XSDComponent;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDNamespaceConstraintCategory;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDRedefine;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDWildcard;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Group Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeGroupDefinitionImpl#isAttributeGroupDefinitionReference <em>Attribute Group Definition Reference</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeGroupDefinitionImpl#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeGroupDefinitionImpl#getContents <em>Contents</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeGroupDefinitionImpl#getAttributeUses <em>Attribute Uses</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeGroupDefinitionImpl#getAttributeWildcardContent <em>Attribute Wildcard Content</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeGroupDefinitionImpl#getAttributeWildcard <em>Attribute Wildcard</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeGroupDefinitionImpl#getResolvedAttributeGroupDefinition <em>Resolved Attribute Group Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeGroupDefinitionImpl#getSyntheticWildcard <em>Synthetic Wildcard</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDAttributeGroupDefinitionImpl 
  extends XSDRedefinableComponentImpl 
  implements XSDAttributeGroupDefinition
{
  /**
   * The default value of the '{@link #isAttributeGroupDefinitionReference() <em>Attribute Group Definition Reference</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAttributeGroupDefinitionReference()
   * @generated
   * @ordered
   */
  protected static final boolean ATTRIBUTE_GROUP_DEFINITION_REFERENCE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #getAnnotation() <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotation()
   * @generated
   * @ordered
   */
  protected XSDAnnotation annotation;

  /**
   * The cached value of the '{@link #getContents() <em>Contents</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContents()
   * @generated
   * @ordered
   */
  protected EList<XSDAttributeGroupContent> contents;

  /**
   * The cached value of the '{@link #getAttributeUses() <em>Attribute Uses</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttributeUses()
   * @generated
   * @ordered
   */
  protected EList<XSDAttributeUse> attributeUses;

  /**
   * The cached value of the '{@link #getAttributeWildcardContent() <em>Attribute Wildcard Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttributeWildcardContent()
   * @generated
   * @ordered
   */
  protected XSDWildcard attributeWildcardContent;

  /**
   * The cached value of the '{@link #getAttributeWildcard() <em>Attribute Wildcard</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttributeWildcard()
   * @generated
   * @ordered
   */
  protected XSDWildcard attributeWildcard;

  /**
   * The cached value of the '{@link #getResolvedAttributeGroupDefinition() <em>Resolved Attribute Group Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResolvedAttributeGroupDefinition()
   * @generated
   * @ordered
   */
  protected XSDAttributeGroupDefinition resolvedAttributeGroupDefinition;

  /**
   * The cached value of the '{@link #getSyntheticWildcard() <em>Synthetic Wildcard</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSyntheticWildcard()
   * @generated
   * @ordered
   */
  protected XSDWildcard syntheticWildcard;

  public static XSDAttributeGroupDefinition createAttributeGroupDefinition(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.ATTRIBUTEGROUP_ELEMENT)
    {
      XSDAttributeGroupDefinition xsdAttributeGroupDefinition = XSDFactory.eINSTANCE.createXSDAttributeGroupDefinition();
      xsdAttributeGroupDefinition.setElement((Element)node);
      return xsdAttributeGroupDefinition;
    }

    return null;
  }

  protected int analysisState;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected XSDAttributeGroupDefinitionImpl()
  {
    super();
    this.resolvedAttributeGroupDefinition = this;
    // this.setResolvedAttributeGroupDefinition = true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return XSDPackage.Literals.XSD_ATTRIBUTE_GROUP_DEFINITION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public Boolean getAttributeGroupDefinitionReference() 
  {
    return isAttributeGroupDefinitionReference() ? Boolean.TRUE : Boolean.FALSE;
  }

  @Override
  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.ATTRIBUTEGROUP_ELEMENT);
    setElement(newElement);

    for (XSDAttributeGroupContent xsdAttributeGroupContent : getContents())
    {
      if (xsdAttributeGroupContent instanceof XSDAttributeGroupDefinition)
      {
        XSDAttributeGroupDefinition xsdAttributeGroupDefinition = (XSDAttributeGroupDefinition)xsdAttributeGroupContent;
        Element attributeGroupDefinitionElement = ((XSDConcreteComponentImpl)xsdAttributeGroupDefinition).createElement();
        newElement.appendChild(attributeGroupDefinitionElement);
      }
      else
      {
        XSDAttributeUse xsdAttributeUse = (XSDAttributeUse)xsdAttributeGroupContent;
        Element attributeUseElement = ((XSDConcreteComponentImpl)xsdAttributeUse).createElement();
        newElement.appendChild(attributeUseElement);
      }
    }

    return newElement;
  }

  @Override
  protected void patch()
  {
    analysisState = UNANALYZED;
    super.patch();
    XSDAttributeGroupDefinition theResolvedAttributeGroupDefinition = getResolvedAttributeGroupDefinition();
    if (theResolvedAttributeGroupDefinition != this && 
          (forceResolve || theResolvedAttributeGroupDefinition.getContainer() == null))
    {
      XSDAttributeGroupDefinition newResolvedAttributeGroupDefinition =
        resolveAttributeGroupDefinition
          (theResolvedAttributeGroupDefinition.getTargetNamespace(), theResolvedAttributeGroupDefinition.getName());
      if (forceResolve || newResolvedAttributeGroupDefinition.getContainer() != null)
      {
        handleNewResolvedAttributeGroupDefinition(newResolvedAttributeGroupDefinition);
      }
    }
  }

  @Override
  protected boolean analyze()
  {
    switch (analysisState)
    {
      case UNANALYZED:
      {
        analysisState = ANALYZING;
        super.analyze();
        handleAnalysis();
        if (analysisState == ANALYZING)
        {
          analysisState = ANALYZED;
          return true;
        }
        else
        {
          return false;
        }
      }
      case ANALYZED:
      {
        return true;
      }
      case ANALYZING:
      case CIRCULAR:
      default:
      {
        analysisState = CIRCULAR;
        return false;
      }
    }
  }

  protected XSDWildcardImpl effectiveWildcard;
  protected void handleAnalysis()
  {
    List<XSDAttributeUse> newAttributeUses = getAttributeUses(getContents(), Collections.<XSDAttributeUse>emptyList());
    List<XSDAttributeUse> remainingAttributeUses = new ArrayList<XSDAttributeUse>(getAttributeUses());
    remainingAttributeUses.removeAll(newAttributeUses);
    if (!remainingAttributeUses.isEmpty())
    {
      getAttributeUses().removeAll(remainingAttributeUses);
    }
    setListContentAndOrder(getAttributeUses(), newAttributeUses);

    XSDWildcard newAttributeWildcard = getAttributeWildcard(null, getAttributeWildcardContent(), getContents());

    if (newAttributeWildcard != null && newAttributeWildcard.getContainer() == null)
    {
      if (effectiveWildcard == null)
      {
        effectiveWildcard = (XSDWildcardImpl)getXSDFactory().createXSDWildcard();
        // effectiveWildcard.setOwner(this);
        setSyntheticWildcard(effectiveWildcard);
      }
      effectiveWildcard.setLike(newAttributeWildcard);
      newAttributeWildcard = effectiveWildcard;
    }

    if (newAttributeWildcard != getAttributeWildcard())
    {
      setAttributeWildcard(newAttributeWildcard);
    }
  }

  @Override
  public void validate()
  {
    super.validate();

    Element theElement = getElement();
    if (theElement != null)
    {
      if (getContainer() instanceof XSDSchema || getContainer() instanceof XSDRedefine)
      { 
        checkAttributes
          (XSDConstants.PART1,
           "element-attributeGroup",
           theElement,
           new String []
           {
             XSDConstants.ID_ATTRIBUTE,
             XSDConstants.NAME_ATTRIBUTE
           });

        checkComplexContent("namedAttributeGroup", XSDConstants.PART1, "element-attributeGroup", theElement);
      }
      else
      {
        checkAttributes
          (XSDConstants.PART1,
           "element-attributeGroup",
           theElement,
           new String []
           {
             XSDConstants.ID_ATTRIBUTE,
             XSDConstants.REF_ATTRIBUTE
           });

        checkComplexContent("attributeGroupRef", XSDConstants.PART1, "element-attributeGroup", theElement);
      }

      checkBuiltInTypeConstraint
        ("ID",
         null,
         XSDConstants.PART1,
         "element-attributeGroup",
         theElement,
         XSDConstants.ID_ATTRIBUTE,
         false);
    }

    if (getContainer() instanceof XSDSchema || getContainer() instanceof XSDRedefine)
    {  
      if (isAttributeGroupDefinitionReference())
      {
        if (theElement == null)
        {
          createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cvc-complex-type.3", "ref");
        }
      }
      else
      {
        checkBuiltInTypeConstraint
          ("NCName",
           getName(),
           XSDConstants.PART1,
           "element-attributeGroup",
           theElement,
           XSDConstants.NAME_ATTRIBUTE,
           true);

        XSDWildcard theAttributeWildcard = getAttributeWildcard();
        if (theAttributeWildcard != null &&
              theAttributeWildcard.getNamespaceConstraint().isEmpty() &&
              theAttributeWildcard.getNamespaceConstraintCategory() == XSDNamespaceConstraintCategory.NOT_LITERAL)
        {
           createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-attribute_group.2");
        }
    
        if (isCircular())
        {
          createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-attribute_group.3");
        }
    
        XSDAttributeDeclaration idAttribute = null;
        Set<String> uris = new HashSet<String>();
        for (XSDAttributeUse xsdAttributeUse : getAttributeUses())
        {
          XSDAttributeDeclaration xsdAttributeDeclaration = xsdAttributeUse.getAttributeDeclaration();
          if (!uris.add(xsdAttributeDeclaration.getURI()))
          {
            getDiagnosticTarget(xsdAttributeUse).createDiagnostic
              (XSDDiagnosticSeverity.ERROR_LITERAL, "coss-attrGroup.2", xsdAttributeDeclaration.getURI());
          }
          if (XSDConstants.isOrIsDerivedFromID(xsdAttributeDeclaration.getTypeDefinition()))
          {
            if (idAttribute != null)
            {
              getDiagnosticTarget(xsdAttributeUse).createDiagnostic
                (XSDDiagnosticSeverity.ERROR_LITERAL, "coss-attrGroup.3", xsdAttributeDeclaration.getURI(), idAttribute.getURI());
            }
            else
            {
              idAttribute = xsdAttributeDeclaration;
            }
          }
        }
      }
    }
    else
    {
      if (isAttributeGroupDefinitionReference())
      {
        XSDAttributeGroupDefinition theResolvedAttributeGroupDefinition = getResolvedAttributeGroupDefinition();
        if (theResolvedAttributeGroupDefinition.getContainer() == null)
        {
          createDiagnostic
            (XSDDiagnosticSeverity.ERROR_LITERAL, 
             "_UI_UnresolvedAttributeGroupDefinition_message", 
             theResolvedAttributeGroupDefinition.getURI());
        }
      }
      else
      {
        if (theElement == null)
        {
          createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cvc-complex-type.3", "name");
        }
      }
    }
  }

  public static List<XSDAttributeUse> getAttributeUses
    (Collection<XSDAttributeGroupContent> xsdAttributeGroupContents, Collection<XSDAttributeUse> xsdAttributeUses)
  {
    List<XSDAttributeUse> result = new ArrayList<XSDAttributeUse>();
    Set<String> localAttributeUses = new HashSet<String>();
    
    for (XSDAttributeGroupContent xsdAttributeGroupContent : xsdAttributeGroupContents)
    {
      ((XSDConcreteComponentImpl)xsdAttributeGroupContent).analyze();
      if (xsdAttributeGroupContent instanceof XSDAttributeUse)
      {
        XSDAttributeUse xsdAttributeUse = (XSDAttributeUse)xsdAttributeGroupContent;
        if (xsdAttributeUse.getUse() != XSDAttributeUseCategory.PROHIBITED_LITERAL)
        {
          result.add(xsdAttributeUse);
        }
        localAttributeUses.add(xsdAttributeUse.getAttributeDeclaration().getURI());
      }
      else
      {
        XSDAttributeGroupDefinition xsdAttributeGroupDefinition =  
            ((XSDAttributeGroupDefinition)xsdAttributeGroupContent).getResolvedAttributeGroupDefinition();
        ((XSDConcreteComponentImpl)xsdAttributeGroupDefinition).analyze();
        result.addAll(xsdAttributeGroupDefinition.getAttributeUses());
      }
    }

    for (XSDAttributeUse xsdAttributeUse : xsdAttributeUses)
    {
      if (!localAttributeUses.contains(xsdAttributeUse.getAttributeDeclaration().getURI()))
      {
        result.add(xsdAttributeUse);
      }
    }

    return XSDAttributeUseImpl.sortAttributeUses(result);
  }

  public static XSDWildcard getAttributeWildcard
    (XSDWildcard baseWildcard, XSDWildcard localWildcard, Collection<XSDAttributeGroupContent> xsdAttributeGroupContents)
  {
    for (XSDAttributeGroupContent xsdAttributeGroupContent  : xsdAttributeGroupContents)
    {
      ((XSDConcreteComponentImpl)xsdAttributeGroupContent).analyze();
      if (xsdAttributeGroupContent instanceof XSDAttributeGroupDefinition)
      {
        XSDAttributeGroupDefinition xsdAttributeGroupDefinition =  
            ((XSDAttributeGroupDefinition)xsdAttributeGroupContent).getResolvedAttributeGroupDefinition();
        ((XSDConcreteComponentImpl)xsdAttributeGroupDefinition).analyze();
        XSDWildcard attributeGroupWildcard = xsdAttributeGroupDefinition.getAttributeWildcard();
        if (attributeGroupWildcard != null)
        {
          if (localWildcard == null)
          {
            localWildcard = attributeGroupWildcard;
          }
          else
          {
            localWildcard = localWildcard.attributeWildcardIntersection(attributeGroupWildcard);
            if (localWildcard == null)
            {
              return null;
            }
            else if (localWildcard.getNamespaceConstraint().isEmpty() &&
                       localWildcard.getNamespaceConstraintCategory() == XSDNamespaceConstraintCategory.NOT_LITERAL)
            {
              // Failure
              //
              return localWildcard;
            }
          }
        }
      }
    }

    if (localWildcard == null)
    {
      localWildcard = baseWildcard;
    }
    else if (baseWildcard != null)
    {
      localWildcard = localWildcard.attributeWildcardUnion(baseWildcard);
    }
    return localWildcard;
  }

  @Override
  protected void patchTargetNamespaceAttribute(XSDSchema xsdSchema)
  {
    if (!isAttributeGroupDefinitionReference())
    {
      super.patchTargetNamespaceAttribute(xsdSchema);
    }
  }

  @Override
  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    XSDAttributeGroupDefinition newResolvedAttributeGroupDefinition = this;
    if (changedElement.hasAttributeNS(null, XSDConstants.REF_ATTRIBUTE))
    {
      newResolvedAttributeGroupDefinition =
        resolveAttributeGroupDefinitionURI(XSDConstants.lookupQNameForAttribute(changedElement, XSDConstants.REF_ATTRIBUTE));
    }

    handleNewResolvedAttributeGroupDefinition(newResolvedAttributeGroupDefinition);
  }

  protected void handleNewResolvedAttributeGroupDefinition(XSDAttributeGroupDefinition newResolvedAttributeGroupDefinition)
  {
    if (eContainer != null)
    {
      EObject parent = eContainer.eContainer();
      if (parent instanceof XSDRedefine)
      {
        XSDSchema redefinedSchema = ((XSDRedefine)parent).getIncorporatedSchema();
        if (redefinedSchema != null)
        {
          Map<XSDComponent, XSDComponent> redefinitionMap = ((XSDSchemaImpl)redefinedSchema).getRedefinitionMap();
          if (redefinitionMap.containsKey(newResolvedAttributeGroupDefinition))
          {
            XSDComponent replacement = redefinitionMap.get(newResolvedAttributeGroupDefinition);
            if (replacement != null)
            {
              newResolvedAttributeGroupDefinition = (XSDAttributeGroupDefinition)replacement;
            }
          }
        }
      }
    }
    if (newResolvedAttributeGroupDefinition != getResolvedAttributeGroupDefinition())
    {
      setResolvedAttributeGroupDefinition(newResolvedAttributeGroupDefinition);
    }
  }

  @Override
  protected void handleUnreconciledElement(Element child, List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    XSDAnnotation xsdAnnotation = XSDAnnotationImpl.createAnnotation(child);
    if (xsdAnnotation != null)
    {
      newContents.add(xsdAnnotation);
    }
    else
    {
      XSDAttributeGroupContent xsdAttributeGroupContent = XSDAttributeGroupContentImpl.createAttributeGroupContent(child);
      if (xsdAttributeGroupContent != null)
      {
        newContents.add(xsdAttributeGroupContent);
      }
      else
      {
        XSDWildcard xsdWildcard = XSDWildcardImpl.createWildcard(child);
        if (xsdWildcard != null)
        {
          newContents.add(xsdWildcard);
        }
      }
    }
  }

  @Override
  protected void handleReconciliation(List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    handleAnnotationReconciliation(XSDPackage.Literals.XSD_ATTRIBUTE_GROUP_DEFINITION__ANNOTATION, newContents, remainingContents);
  
    XSDWildcard newAttributeWildcard = null;
    if (!newContents.isEmpty())
    {
      int lastIndex = newContents.size() - 1;
      Object last = newContents.get(lastIndex);
      if (last instanceof XSDWildcard)
      {
        newAttributeWildcard = (XSDWildcard)last;
        if (XSDConstants.nodeType(newAttributeWildcard.getElement()) == XSDConstants.ANYATTRIBUTE_ELEMENT)
        {
          newContents.remove(lastIndex);
        }
        else
        {
          newAttributeWildcard = null;
        }
      }
    }

    if (newAttributeWildcard != getAttributeWildcardContent())
    {
      remainingContents.remove(getAttributeWildcardContent());
      setAttributeWildcardContent(newAttributeWildcard);
    }


    if (!remainingContents.isEmpty())
    {
      getContents().removeAll(remainingContents);
    }

    if (!newContents.isEmpty())
    {
      for (Iterator<XSDConcreteComponent> i = newContents.iterator(); i.hasNext(); )
      {
        if (!(i.next() instanceof XSDAttributeGroupContent))
        {
          i.remove();
        }
      }
      @SuppressWarnings("unchecked") List<XSDAttributeGroupContent> list = (List<XSDAttributeGroupContent>)(List<?>)newContents;
      setListContentAndOrder(getContents(), list);
    }
  }

  @Override
  protected void changeReference(EReference eReference)
  {
    if (isReconciling)
    {
      return;
    }

    super.changeReference(eReference);
    Element theElement = getElement();
    if (theElement != null)
    {
      if (eReference == null || eReference == XSDPackage.Literals.XSD_ATTRIBUTE_GROUP_DEFINITION__RESOLVED_ATTRIBUTE_GROUP_DEFINITION)
      {
        XSDAttributeGroupDefinition theResolvedAttributeGroupDefinition = getResolvedAttributeGroupDefinition();
        if (theResolvedAttributeGroupDefinition != this)
        {
          niceSetAttributeURIValue(theElement, XSDConstants.REF_ATTRIBUTE, theResolvedAttributeGroupDefinition.getURI());
        }
      }
    }
  }

  @Override
  protected void adoptContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.adoptContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_ATTRIBUTE_GROUP_DEFINITION__CONTENTS)
    {
      traverseToRootForPatching();
    }
  }

  @Override
  protected void orphanContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.orphanContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_ATTRIBUTE_GROUP_DEFINITION__CONTENTS)
    {
      traverseToRootForPatching();
    }
  }

  public boolean isAttributeGroupDefinitionReference()
  {
    return this != getResolvedAttributeGroupDefinition();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDAnnotation getAnnotation()
  {
    return annotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnnotation(XSDAnnotation newAnnotation)
  {
    if (newAnnotation != annotation)
    {
      NotificationChain msgs = null;
      if (annotation != null)
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ANNOTATION, newAnnotation, newAnnotation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAnnotation(XSDAnnotation newAnnotation, NotificationChain msgs)
  {
    XSDAnnotation oldAnnotation = annotation;
    annotation = newAnnotation;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ANNOTATION, oldAnnotation, newAnnotation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDAttributeGroupContent> getContents()
  {
    if (contents == null)
    {
      contents = new EObjectContainmentEList<XSDAttributeGroupContent>(XSDAttributeGroupContent.class, this, XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__CONTENTS);
    }
    return contents;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDAttributeUse> getAttributeUses()
  {
    if (attributeUses == null)
    {
      attributeUses = new EObjectEList<XSDAttributeUse>(XSDAttributeUse.class, this, XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_USES);
    }
    return attributeUses;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDWildcard getAttributeWildcardContent()
  {
    return attributeWildcardContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAttributeWildcardContent(XSDWildcard newAttributeWildcardContent)
  {
    if (newAttributeWildcardContent != attributeWildcardContent)
    {
      NotificationChain msgs = null;
      if (attributeWildcardContent != null)
        msgs = ((InternalEObject)attributeWildcardContent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT, null, msgs);
      if (newAttributeWildcardContent != null)
        msgs = ((InternalEObject)newAttributeWildcardContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT, null, msgs);
      msgs = basicSetAttributeWildcardContent(newAttributeWildcardContent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT, newAttributeWildcardContent, newAttributeWildcardContent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAttributeWildcardContent(XSDWildcard newAttributeWildcardContent, NotificationChain msgs)
  {
    XSDWildcard oldAttributeWildcardContent = attributeWildcardContent;
    attributeWildcardContent = newAttributeWildcardContent;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT, oldAttributeWildcardContent, newAttributeWildcardContent);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDWildcard getAttributeWildcard()
  {
    return attributeWildcard;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAttributeWildcard(XSDWildcard newAttributeWildcard)
  {
    XSDWildcard oldAttributeWildcard = attributeWildcard;
    attributeWildcard = newAttributeWildcard;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_WILDCARD, oldAttributeWildcard, attributeWildcard));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDAttributeGroupDefinition getResolvedAttributeGroupDefinition()
  {
    return resolvedAttributeGroupDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResolvedAttributeGroupDefinition(XSDAttributeGroupDefinition newResolvedAttributeGroupDefinition)
  {
    XSDAttributeGroupDefinition oldResolvedAttributeGroupDefinition = resolvedAttributeGroupDefinition;
    resolvedAttributeGroupDefinition = newResolvedAttributeGroupDefinition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__RESOLVED_ATTRIBUTE_GROUP_DEFINITION, oldResolvedAttributeGroupDefinition, resolvedAttributeGroupDefinition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDWildcard getSyntheticWildcard()
  {
    return syntheticWildcard;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSyntheticWildcard(XSDWildcard newSyntheticWildcard, NotificationChain msgs)
  {
    XSDWildcard oldSyntheticWildcard = syntheticWildcard;
    syntheticWildcard = newSyntheticWildcard;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__SYNTHETIC_WILDCARD, oldSyntheticWildcard, newSyntheticWildcard);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSyntheticWildcard(XSDWildcard newSyntheticWildcard)
  {
    if (newSyntheticWildcard != syntheticWildcard)
    {
      NotificationChain msgs = null;
      if (syntheticWildcard != null)
        msgs = ((InternalEObject)syntheticWildcard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__SYNTHETIC_WILDCARD, null, msgs);
      if (newSyntheticWildcard != null)
        msgs = ((InternalEObject)newSyntheticWildcard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__SYNTHETIC_WILDCARD, null, msgs);
      msgs = basicSetSyntheticWildcard(newSyntheticWildcard, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__SYNTHETIC_WILDCARD, newSyntheticWildcard, newSyntheticWildcard));
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
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ANNOTATION:
        return basicSetAnnotation(null, msgs);
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__CONTENTS:
        return ((InternalEList<?>)getContents()).basicRemove(otherEnd, msgs);
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT:
        return basicSetAttributeWildcardContent(null, msgs);
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__SYNTHETIC_WILDCARD:
        return basicSetSyntheticWildcard(null, msgs);
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
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_GROUP_DEFINITION_REFERENCE:
        return isAttributeGroupDefinitionReference();
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__CONTENTS:
        return getContents();
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_USES:
        return getAttributeUses();
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT:
        return getAttributeWildcardContent();
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_WILDCARD:
        return getAttributeWildcard();
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__RESOLVED_ATTRIBUTE_GROUP_DEFINITION:
        return getResolvedAttributeGroupDefinition();
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__SYNTHETIC_WILDCARD:
        return getSyntheticWildcard();
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
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__CONTENTS:
        getContents().clear();
        getContents().addAll((Collection<? extends XSDAttributeGroupContent>)newValue);
        return;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_USES:
        getAttributeUses().clear();
        getAttributeUses().addAll((Collection<? extends XSDAttributeUse>)newValue);
        return;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT:
        setAttributeWildcardContent((XSDWildcard)newValue);
        return;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_WILDCARD:
        setAttributeWildcard((XSDWildcard)newValue);
        return;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__RESOLVED_ATTRIBUTE_GROUP_DEFINITION:
        setResolvedAttributeGroupDefinition((XSDAttributeGroupDefinition)newValue);
        return;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__SYNTHETIC_WILDCARD:
        setSyntheticWildcard((XSDWildcard)newValue);
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
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__CONTENTS:
        getContents().clear();
        return;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_USES:
        getAttributeUses().clear();
        return;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT:
        setAttributeWildcardContent((XSDWildcard)null);
        return;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_WILDCARD:
        setAttributeWildcard((XSDWildcard)null);
        return;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__RESOLVED_ATTRIBUTE_GROUP_DEFINITION:
        setResolvedAttributeGroupDefinition((XSDAttributeGroupDefinition)null);
        return;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__SYNTHETIC_WILDCARD:
        setSyntheticWildcard((XSDWildcard)null);
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
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_GROUP_DEFINITION_REFERENCE:
        return isAttributeGroupDefinitionReference() != ATTRIBUTE_GROUP_DEFINITION_REFERENCE_EDEFAULT;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__CONTENTS:
        return contents != null && !contents.isEmpty();
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_USES:
        return attributeUses != null && !attributeUses.isEmpty();
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT:
        return attributeWildcardContent != null;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_WILDCARD:
        return attributeWildcard != null;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__RESOLVED_ATTRIBUTE_GROUP_DEFINITION:
        return resolvedAttributeGroupDefinition != null;
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION__SYNTHETIC_WILDCARD:
        return syntheticWildcard != null;
    }
    return super.eIsSet(featureID);
  }

  @Override
  public String getQName()
  {
    XSDAttributeGroupDefinition resolvedAttributeGroupDefinition = getResolvedAttributeGroupDefinition();
    if (resolvedAttributeGroupDefinition == this)
    {
      return super.getQName();
    }
    else
    {
      return resolvedAttributeGroupDefinition.getQName(this);
    }
  }

  @Override
  public boolean isNamedComponentReference()
  {
    return isAttributeGroupDefinitionReference();
  }

  @Override
  public XSDNamedComponent getResolvedNamedComponent()
  {
    return getResolvedAttributeGroupDefinition();
  }

  @Override
  public boolean isCircular()
  {
    return analysisState == CIRCULAR;
  }

  @Override
  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDAttributeGroupDefinitionImpl clonedAttributeGroupDefinition = 
      (XSDAttributeGroupDefinitionImpl)getXSDFactory().createXSDAttributeGroupDefinition();
    clonedAttributeGroupDefinition.isReconciling = true;

    if (isAttributeGroupDefinitionReference())
    {
      XSDAttributeGroupDefinition theResolvedAttributeGroupDefinition = getResolvedAttributeGroupDefinition();
      clonedAttributeGroupDefinition.setResolvedAttributeGroupDefinition
        (createUnresolvedAttributeGroupDefinition
          (theResolvedAttributeGroupDefinition.getTargetNamespace(), theResolvedAttributeGroupDefinition.getName()));
    }
    else
    {
      if (getName() != null)
      {
        clonedAttributeGroupDefinition.setName(getName());
      }
    }

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedAttributeGroupDefinition.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(true, shareDOM));
      }

      if (!getContents().isEmpty())
      {
        clonedAttributeGroupDefinition.getContents().addAll(cloneConcreteComponents(getContents(), true, shareDOM));
      }

      if (getAttributeWildcardContent() != null)
      {
        clonedAttributeGroupDefinition.setAttributeWildcardContent
          ((XSDWildcard)getAttributeWildcardContent().cloneConcreteComponent(true, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedAttributeGroupDefinition.setElement(getElement());
    }

    clonedAttributeGroupDefinition.isReconciling = shareDOM;
    return clonedAttributeGroupDefinition;
  }
}
