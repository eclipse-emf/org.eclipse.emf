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
 * $Id: XSDAttributeDeclarationImpl.java,v 1.20 2007/10/20 16:25:08 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDConstraint;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDFeature;
import org.eclipse.xsd.XSDForm;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeDeclarationImpl#isAttributeDeclarationReference <em>Attribute Declaration Reference</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeDeclarationImpl#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeDeclarationImpl#getAnonymousTypeDefinition <em>Anonymous Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeDeclarationImpl#getTypeDefinition <em>Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeDeclarationImpl#getResolvedAttributeDeclaration <em>Resolved Attribute Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDAttributeDeclarationImpl 
  extends XSDFeatureImpl 
  implements XSDAttributeDeclaration
{
  /**
   * The default value of the '{@link #isAttributeDeclarationReference() <em>Attribute Declaration Reference</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAttributeDeclarationReference()
   * @generated
   * @ordered
   */
  protected static final boolean ATTRIBUTE_DECLARATION_REFERENCE_EDEFAULT = false;

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
   * The cached value of the '{@link #getAnonymousTypeDefinition() <em>Anonymous Type Definition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnonymousTypeDefinition()
   * @generated
   * @ordered
   */
  protected XSDSimpleTypeDefinition anonymousTypeDefinition;

  /**
   * The cached value of the '{@link #getTypeDefinition() <em>Type Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeDefinition()
   * @generated
   * @ordered
   */
  protected XSDSimpleTypeDefinition typeDefinition;

  /**
   * The cached value of the '{@link #getResolvedAttributeDeclaration() <em>Resolved Attribute Declaration</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResolvedAttributeDeclaration()
   * @generated
   * @ordered
   */
  protected XSDAttributeDeclaration resolvedAttributeDeclaration;

  public static XSDAttributeDeclaration createAttributeDeclaration(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.ATTRIBUTE_ELEMENT)
    {
      XSDAttributeDeclaration xsdAttributeDeclaration = XSDFactory.eINSTANCE.createXSDAttributeDeclaration();
      xsdAttributeDeclaration.setElement((Element)node);
      return xsdAttributeDeclaration;
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected XSDAttributeDeclarationImpl()
  {
    super();
    this.resolvedAttributeDeclaration = this;
    // this.setResolvedAttributeDeclaration = true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return XSDPackage.Literals.XSD_ATTRIBUTE_DECLARATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public Boolean getAttributeDeclarationReference() 
  {
    return isAttributeDeclarationReference() ? Boolean.TRUE : Boolean.FALSE;
  }

  @Override
  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.ATTRIBUTE_ELEMENT);
    setElement(newElement);

    XSDSimpleTypeDefinition anonymousTypeDefinition = getAnonymousTypeDefinition();
    if (anonymousTypeDefinition != null)
    {
      Element child = ((XSDConcreteComponentImpl)anonymousTypeDefinition).createElement();
      newElement.appendChild(child);
    }

    return newElement;
  }

  @Override
  protected void patch()
  {
    super.patch();
    XSDAttributeDeclaration theResolvedAttributeDeclaration = getResolvedAttributeDeclaration();
    if (theResolvedAttributeDeclaration == this)
    {
      XSDSimpleTypeDefinition typeDefinition = getTypeDefinition();
      if (typeDefinition != null && (forceResolve && typeDefinition.getName() != null || typeDefinition.getContainer() == null))
      {
        XSDSimpleTypeDefinition newTypeDefinition = 
          resolveSimpleTypeDefinition(typeDefinition.getTargetNamespace(), typeDefinition.getName());
        if (forceResolve || newTypeDefinition.getContainer() != null & newTypeDefinition != typeDefinition)
        {
          setTypeDefinitionGen(newTypeDefinition);
        }
      }
    }
    else
    {
      XSDAttributeDeclaration newResolvedAttributeDeclaration = 
        resolveAttributeDeclaration(theResolvedAttributeDeclaration.getTargetNamespace(), theResolvedAttributeDeclaration.getName());
      if (newResolvedAttributeDeclaration != theResolvedAttributeDeclaration)
      {
        setResolvedAttributeDeclaration(newResolvedAttributeDeclaration);
      }
    }
  }

  @Override
  protected void patchTargetNamespaceAttribute()
  {
    if (!isAttributeDeclarationReference())
    {
      XSDSchema xsdSchema = getSchema();
      if (xsdSchema != null)
      {
        patchTargetNamespaceAttributeHelper(xsdSchema);
      }
    }
  }

  @Override
  protected void patchTargetNamespaceAttribute(XSDSchema xsdSchema)
  {
    if (!isAttributeDeclarationReference())
    {
      patchTargetNamespaceAttributeHelper(xsdSchema);
    }
  }

  protected void patchTargetNamespaceAttributeHelper(XSDSchema xsdSchema)
  {
    if (getScope() instanceof XSDSchema)
    {
      if (!isSetForm() || XSDForm.QUALIFIED_LITERAL != getForm())
      {
        setForm(XSDForm.QUALIFIED_LITERAL);
      }
    }
    if (isSetForm() ? getForm() == XSDForm.UNQUALIFIED_LITERAL : xsdSchema.getAttributeFormDefault() == XSDForm.UNQUALIFIED_LITERAL)
    {
      if (getTargetNamespace() != null)
      {
        setTargetNamespace(null);
      }
    }
    else
    {
      String newTargetNamespace = xsdSchema.getTargetNamespace();
      if (newTargetNamespace == null ? getTargetNamespace() != null : !newTargetNamespace.equals(getTargetNamespace()))
      {
        setTargetNamespace(newTargetNamespace);
      }
    }
  }

  protected boolean isTypeExplicit = false;
  @Override
  protected boolean analyze()
  {
    if (!isAttributeDeclarationReference())
    {
      XSDSimpleTypeDefinition theTypeDefinition = getTypeDefinition();

      if (!isTypeExplicit || theTypeDefinition == null)
      {
        XSDSimpleTypeDefinition newTypeDefinition = getSchema().getSchemaForSchema().resolveSimpleTypeDefinition("anySimpleType");
        if (newTypeDefinition != theTypeDefinition)
        {
          isTypeExplicit = false;
          setTypeDefinitionGen(newTypeDefinition);
        }
      }
    }

    return super.analyze();
  }

  @Override
  public void validate()
  {
    super.validate();

    Element theElement = getElement();
    if (theElement != null)
    {
      if (getContainer() instanceof XSDSchema)
      {
        checkAttributes
          (XSDConstants.PART1,
           "element-attribute",
           theElement,
           new String []
           {
             XSDConstants.DEFAULT_ATTRIBUTE,
             XSDConstants.FIXED_ATTRIBUTE,
             XSDConstants.ID_ATTRIBUTE,
             XSDConstants.NAME_ATTRIBUTE,
             XSDConstants.TYPE_ATTRIBUTE
           });

        checkComplexContent("topLevelAttribute", XSDConstants.PART1, "element-attribute", theElement);
      }
      else
      {
        checkAttributes
          (XSDConstants.PART1,
           "element-attribute",
           theElement,
           new String []
           {
             XSDConstants.DEFAULT_ATTRIBUTE,
             XSDConstants.FIXED_ATTRIBUTE,
             XSDConstants.FORM_ATTRIBUTE,
             XSDConstants.ID_ATTRIBUTE,
             XSDConstants.NAME_ATTRIBUTE,
             XSDConstants.REF_ATTRIBUTE,
             XSDConstants.TYPE_ATTRIBUTE,
             XSDConstants.USE_ATTRIBUTE
           });

        checkComplexContent("attribute", XSDConstants.PART1, "element-attribute", theElement);

        checkAttributeTypeConstraint
          ("attribute",
           "use",
           null,
           XSDConstants.PART1, 
           "element-attribute", 
           theElement, 
           XSDConstants.USE_ATTRIBUTE, 
           false);

        checkBuiltInTypeConstraint
          ("formChoice", 
           null,
           XSDConstants.PART1, 
           "element-attribute", 
           theElement, 
           XSDConstants.FORM_ATTRIBUTE, 
           false);
      }

      if (theElement.hasAttributeNS(null, XSDConstants.FIXED_ATTRIBUTE) &&
            theElement.hasAttributeNS(null, XSDConstants.DEFAULT_ATTRIBUTE))
      {
        createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-attribute.1");
      }

      checkBuiltInTypeConstraint
        ("ID", 
         null,
         XSDConstants.PART1, 
         "element-attribute", 
         theElement, 
         XSDConstants.ID_ATTRIBUTE, 
         false);


      if (theElement.hasAttributeNS(null, XSDConstants.TYPE_ATTRIBUTE))
      {
        for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling())
        {
          if (XSDConstants.nodeType(child) == XSDConstants.SIMPLETYPE_ELEMENT)
          {
            createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-attribute.4");
            break;
          }
        }
      }
    }

    if (isAttributeDeclarationReference())
    {
      XSDAttributeDeclaration theResolvedAttributeDeclaration = getResolvedAttributeDeclaration();
      if (theResolvedAttributeDeclaration.getContainer() == null)
      {
        createDiagnostic
          (XSDDiagnosticSeverity.ERROR_LITERAL, 
           "_UI_UnresolvedAttributeDeclaration_message", 
           theResolvedAttributeDeclaration.getURI());
      }

      if (getName() != null || theElement != null && theElement.hasAttributeNS(null, XSDConstants.NAME_ATTRIBUTE))
      {
        createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-attribute.3.1");
      }
      if (isSetForm() || theElement != null && theElement.hasAttributeNS(null, XSDConstants.FORM_ATTRIBUTE))
      {
        createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-attribute.3.2.1");
      }
      if (getType() != null || theElement != null && theElement.hasAttributeNS(null, XSDConstants.TYPE_ATTRIBUTE))
      {
        createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-attribute.3.2.2");
      }
      else if (theElement != null)
      {
        for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling())
        {
          if (XSDConstants.nodeType(child) == XSDConstants.SIMPLETYPE_ELEMENT)
          {
            createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-attribute.3.2.2");
            break;
          }
        }
      }
    }
    else
    {
      checkBuiltInTypeConstraint
        ("NCName", 
         getName(), 
         XSDConstants.PART1, 
         "element-attribute", 
         theElement, 
         XSDConstants.NAME_ATTRIBUTE, 
         true);

      if ("xmlns".equals(getName()))
      {
        createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "no-xmlns");
      }

      if (XSDConstants.isSchemaInstanceNamespace(getTargetNamespace()))
      {
        createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "no-xsi");
      }

      XSDSimpleTypeDefinition theTypeDefinition = getTypeDefinition();
      if (theTypeDefinition == null || theTypeDefinition.getContainer() == null)
      {
        createDiagnostic
          (XSDDiagnosticSeverity.ERROR_LITERAL, 
           "_UI_UnresolvedTypeDefinition_message", 
           theTypeDefinition == null ? "" :  theTypeDefinition.getURI());
      }
      if (theTypeDefinition != null)
      {
        if (getLexicalValue() != null)
        {
          if (XSDConstants.isOrIsDerivedFromID(theTypeDefinition))
          {
            createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "coss-attribute.3");
          }
          else
          {
            checkSimpleTypeConstraint
              (theTypeDefinition,
               getLexicalValue(), 
               XSDConstants.PART1, 
               "element-attribute", 
               theElement, 
               getConstraint() == XSDConstraint.FIXED_LITERAL ? XSDConstants.FIXED_ATTRIBUTE : XSDConstants.DEFAULT_ATTRIBUTE, 
               false);
          }
        }
      }
    }
  }

  @Override
  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    if (changedElement == getElement())
    {
      XSDAttributeDeclaration newResolvedAttributeDeclaration = this;
      if (changedElement.hasAttributeNS(null, XSDConstants.REF_ATTRIBUTE))
      {
        newResolvedAttributeDeclaration = 
          resolveAttributeDeclarationURI(XSDConstants.lookupQNameForAttribute(changedElement, XSDConstants.REF_ATTRIBUTE));
      }
/*
      else if (!changedElement.hasAttributeNS(null, XSDConstants.NAME_ATTRIBUTE))
      {
        newResolvedAttributeDeclaration = resolveAttributeDeclaration(null, "undefined");
      }
*/

      if (newResolvedAttributeDeclaration != getResolvedAttributeDeclaration())
      {
        setResolvedAttributeDeclaration(newResolvedAttributeDeclaration);
      }

      if (this == newResolvedAttributeDeclaration)
      {
        if (changedElement.hasAttributeNS(null, XSDConstants.TYPE_ATTRIBUTE))
        {
          isTypeExplicit = true;
          XSDSimpleTypeDefinition newTypeDefinition =
            resolveSimpleTypeDefinitionURI(XSDConstants.lookupQNameForAttribute(changedElement, XSDConstants.TYPE_ATTRIBUTE));
          if (newTypeDefinition != getTypeDefinition())
          {
            setTypeDefinition(newTypeDefinition);
          }
        }
        else if (getAnonymousTypeDefinition() != getTypeDefinition())
        {
          isTypeExplicit = false;
          setTypeDefinitionGen(resolveSimpleTypeDefinition(changedElement.getNamespaceURI(), "anySimpleType"));
        }

        if (getScope() instanceof XSDSchema)
        {
          if (getForm() != XSDForm.QUALIFIED_LITERAL)
          {
            setForm(XSDForm.QUALIFIED_LITERAL);
          }
        }
        else
        {
          if (changedElement.hasAttributeNS(null, XSDConstants.FORM_ATTRIBUTE))
          {
            XSDForm newForm = XSDForm.get(changedElement.getAttributeNS(null, XSDConstants.FORM_ATTRIBUTE));
            if (!isSetForm() || newForm != getForm())
            {
              setForm(newForm);
            }
          }
          else if (isSetForm())
          {
            unsetForm();
          }
        }
      }
    }
  }

  @Override
  protected void reconcileNameAttribute()
  {
    if (!isAttributeDeclarationReference())
    {
      super.reconcileNameAttribute();
    }
  }

  @Override
  protected void handleUnreconciledElement(Element child, List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    XSDAnnotation xsdAnnotation = XSDAnnotationImpl.createAnnotation(child);
    if (xsdAnnotation != null)
    {
      if (newContents.isEmpty())
      {
        newContents.add(xsdAnnotation);
      }
    }
    else
    {
      XSDSimpleTypeDefinition xsdSimpleTypeDefinition = XSDSimpleTypeDefinitionImpl.createSimpleTypeDefinition(child);
      if (xsdSimpleTypeDefinition != null)
      {
        newContents.add(xsdSimpleTypeDefinition);
      }
    }
  }

  @Override
  protected void handleReconciliation(List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    handleAnnotationReconciliation(XSDPackage.Literals.XSD_ATTRIBUTE_DECLARATION__ANNOTATION, newContents, remainingContents);
    if (!isAttributeDeclarationReference())
    {
      Element theElement = getElement();
      XSDSimpleTypeDefinition newTypeDefinition = null;
      if (newContents.isEmpty())
      {
        if (!remainingContents.isEmpty())
        {
          setAnonymousTypeDefinition(null);
        }
      }
      else
      {
        setAnonymousTypeDefinition(newTypeDefinition = (XSDSimpleTypeDefinition)newContents.get(0));
      }

      if (theElement.hasAttributeNS(null, XSDConstants.TYPE_ATTRIBUTE))
      {
        isTypeExplicit = true;
        newTypeDefinition = 
          resolveSimpleTypeDefinitionURI(XSDConstants.lookupQNameForAttribute(theElement, XSDConstants.TYPE_ATTRIBUTE));
      }
  
      if (newTypeDefinition == null)
      {
        newTypeDefinition = resolveSimpleTypeDefinition(theElement.getNamespaceURI(), "anySimpleType");
      }
  
      if (newTypeDefinition != getTypeDefinition())
      {
        setTypeDefinitionGen(newTypeDefinition);
      }
    }
  }

  @Override
  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    if (!isAttributeDeclarationReference())
    {
      Element theElement = getElement();
      if (theElement != null)
      {
        if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_FEATURE__FORM)
        {
          if (!(getScope() instanceof XSDSchema))
          {
            niceSetAttribute(theElement, XSDConstants.FORM_ATTRIBUTE, isSetForm() ? getForm().getName() : null);
          }
        }
      }
      if (eAttribute == XSDPackage.Literals.XSD_FEATURE__FORM)
      {
        patchTargetNamespaceAttribute();
      }
    }
  }

  @Override
  protected void changeReference(EReference eReference)
  {
    super.changeReference(eReference);
    Element theElement = getElement();
    if (isAttributeDeclarationReference())
    {
      XSDAttributeDeclaration theResolvedAttributeDeclaration = getResolvedAttributeDeclaration();
      if (eReference == null || eReference == XSDPackage.Literals.XSD_ATTRIBUTE_DECLARATION__RESOLVED_ATTRIBUTE_DECLARATION)
      {
        if (theElement != null)
        {
          niceSetAttributeURIValue(theElement, XSDConstants.REF_ATTRIBUTE, theResolvedAttributeDeclaration.getURI());
        }
        if (eReference != null && getContainer() instanceof XSDAttributeUse)
        {
          ((XSDAttributeUse)getContainer()).setAttributeDeclaration(theResolvedAttributeDeclaration);
        }
      }
    }
    else
    {
      if (theElement != null && eReference == XSDPackage.Literals.XSD_ATTRIBUTE_DECLARATION__RESOLVED_ATTRIBUTE_DECLARATION)
      {
        niceSetAttributeURIValue(theElement, XSDConstants.REF_ATTRIBUTE, null);
      }
      
      if (eReference == null || eReference == XSDPackage.Literals.XSD_ATTRIBUTE_DECLARATION__TYPE_DEFINITION)
      {
        XSDSimpleTypeDefinition theTypeDefinition = getTypeDefinition();
        XSDSimpleTypeDefinition theAnonymousTypeDefinition = getAnonymousTypeDefinition();
        if (!isTypeExplicit ||
              theTypeDefinition == null || 
              theTypeDefinition == theAnonymousTypeDefinition)
        {
          if (theElement != null)
          {
            niceSetAttribute(theElement, XSDConstants.TYPE_ATTRIBUTE, null);
          }
        }
        else 
        {
          if (theElement != null)
          {
            niceSetAttributeURIValue(theElement, XSDConstants.TYPE_ATTRIBUTE, theTypeDefinition.getURI());
          }
          if (eReference != null && theAnonymousTypeDefinition != null)
          {
            setAnonymousTypeDefinition(null);
          }
        }
      }
    }
  }

  @Override
  protected void adoptContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.adoptContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_ATTRIBUTE_DECLARATION__ANONYMOUS_TYPE_DEFINITION)
    {
      setTypeDefinition((XSDSimpleTypeDefinition)xsdConcreteComponent);
      traverseToRootForPatching();
    }
  }

  @Override
  protected void orphanContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.orphanContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_ATTRIBUTE_DECLARATION__ANONYMOUS_TYPE_DEFINITION)
    {
      if (getTypeDefinition() == xsdConcreteComponent)
      {
        setTypeDefinition(null);
      }
    }
  }

  public boolean isAttributeDeclarationReference()
  {
    return this != getResolvedAttributeDeclaration();
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
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANNOTATION, newAnnotation, newAnnotation));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANNOTATION, oldAnnotation, newAnnotation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDSimpleTypeDefinition getAnonymousTypeDefinition()
  {
    return anonymousTypeDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnonymousTypeDefinition(XSDSimpleTypeDefinition newAnonymousTypeDefinition)
  {
    if (newAnonymousTypeDefinition != anonymousTypeDefinition)
    {
      NotificationChain msgs = null;
      if (anonymousTypeDefinition != null)
        msgs = ((InternalEObject)anonymousTypeDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANONYMOUS_TYPE_DEFINITION, null, msgs);
      if (newAnonymousTypeDefinition != null)
        msgs = ((InternalEObject)newAnonymousTypeDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANONYMOUS_TYPE_DEFINITION, null, msgs);
      msgs = basicSetAnonymousTypeDefinition(newAnonymousTypeDefinition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANONYMOUS_TYPE_DEFINITION, newAnonymousTypeDefinition, newAnonymousTypeDefinition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAnonymousTypeDefinition(XSDSimpleTypeDefinition newAnonymousTypeDefinition, NotificationChain msgs)
  {
    XSDSimpleTypeDefinition oldAnonymousTypeDefinition = anonymousTypeDefinition;
    anonymousTypeDefinition = newAnonymousTypeDefinition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANONYMOUS_TYPE_DEFINITION, oldAnonymousTypeDefinition, newAnonymousTypeDefinition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDSimpleTypeDefinition getTypeDefinition()
  {
    return typeDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTypeDefinitionGen(XSDSimpleTypeDefinition newTypeDefinition)
  {
    XSDSimpleTypeDefinition oldTypeDefinition = typeDefinition;
    typeDefinition = newTypeDefinition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_DECLARATION__TYPE_DEFINITION, oldTypeDefinition, typeDefinition));
  }

  public void setTypeDefinition(XSDSimpleTypeDefinition newTypeDefinition)
  {
    isTypeExplicit = newTypeDefinition != null;
    setTypeDefinitionGen(newTypeDefinition);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDAttributeDeclaration getResolvedAttributeDeclaration()
  {
    return resolvedAttributeDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResolvedAttributeDeclaration(XSDAttributeDeclaration newResolvedAttributeDeclaration)
  {
    XSDAttributeDeclaration oldResolvedAttributeDeclaration = resolvedAttributeDeclaration;
    resolvedAttributeDeclaration = newResolvedAttributeDeclaration;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_DECLARATION__RESOLVED_ATTRIBUTE_DECLARATION, oldResolvedAttributeDeclaration, resolvedAttributeDeclaration));
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
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANNOTATION:
        return basicSetAnnotation(null, msgs);
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANONYMOUS_TYPE_DEFINITION:
        return basicSetAnonymousTypeDefinition(null, msgs);
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
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__ATTRIBUTE_DECLARATION_REFERENCE:
        return isAttributeDeclarationReference() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANONYMOUS_TYPE_DEFINITION:
        return getAnonymousTypeDefinition();
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__TYPE_DEFINITION:
        return getTypeDefinition();
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__RESOLVED_ATTRIBUTE_DECLARATION:
        return getResolvedAttributeDeclaration();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANONYMOUS_TYPE_DEFINITION:
        setAnonymousTypeDefinition((XSDSimpleTypeDefinition)newValue);
        return;
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__TYPE_DEFINITION:
        setTypeDefinition((XSDSimpleTypeDefinition)newValue);
        return;
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__RESOLVED_ATTRIBUTE_DECLARATION:
        setResolvedAttributeDeclaration((XSDAttributeDeclaration)newValue);
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
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANONYMOUS_TYPE_DEFINITION:
        setAnonymousTypeDefinition((XSDSimpleTypeDefinition)null);
        return;
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__TYPE_DEFINITION:
        setTypeDefinition((XSDSimpleTypeDefinition)null);
        return;
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__RESOLVED_ATTRIBUTE_DECLARATION:
        setResolvedAttributeDeclaration((XSDAttributeDeclaration)null);
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
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__ATTRIBUTE_DECLARATION_REFERENCE:
        return isAttributeDeclarationReference() != ATTRIBUTE_DECLARATION_REFERENCE_EDEFAULT;
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__ANONYMOUS_TYPE_DEFINITION:
        return anonymousTypeDefinition != null;
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__TYPE_DEFINITION:
        return typeDefinition != null;
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION__RESOLVED_ATTRIBUTE_DECLARATION:
        return resolvedAttributeDeclaration != null;
    }
    return super.eIsSet(featureID);
  }

  @Override
  public String getQName()
  {
    XSDAttributeDeclaration resolvedAttributeDeclaration = getResolvedAttributeDeclaration();
    if (resolvedAttributeDeclaration == this)
    {
      return super.getQName();
    }
    else
    {
      return resolvedAttributeDeclaration.getQName(this);
    }
  }

  @Override
  public boolean isNamedComponentReference()
  {
    return isAttributeDeclarationReference();
  }

  @Override
  public XSDNamedComponent getResolvedNamedComponent()
  {
    return getResolvedAttributeDeclaration();
  }

  @Override
  public boolean isFeatureReference()
  {
    return isAttributeDeclarationReference();
  }

  @Override
  public XSDFeature getResolvedFeature()
  {
    return getResolvedAttributeDeclaration();
  }

  @Override
  public XSDTypeDefinition getType()
  {
    return getTypeDefinition();
  }

  @Override
  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDAttributeDeclarationImpl clonedAttributeDeclaration = 
     (XSDAttributeDeclarationImpl)getXSDFactory().createXSDAttributeDeclaration();
    clonedAttributeDeclaration.isReconciling = true;

    if (isAttributeDeclarationReference())
    {
      XSDAttributeDeclaration theResolvedAttributeDeclaration = getResolvedAttributeDeclaration();
      clonedAttributeDeclaration.setResolvedAttributeDeclaration
        (createUnresolvedAttributeDeclaration
          (theResolvedAttributeDeclaration.getTargetNamespace(), theResolvedAttributeDeclaration.getName()));
    }
    else
    {
      if (getName() != null)
      {
        clonedAttributeDeclaration.setName(getName());
      }
      if (isSetForm())
      {
        clonedAttributeDeclaration.setForm(getForm());
      }
      if (isSetConstraint())
      {
        clonedAttributeDeclaration.setConstraint(getConstraint());
      }
      if (getLexicalValue() != null)
      {
        clonedAttributeDeclaration.setLexicalValue(getLexicalValue());
      }

      if (getTypeDefinition() != null && getTypeDefinition() != getAnonymousTypeDefinition())
      {
        XSDSimpleTypeDefinition theTypeDefinition = getTypeDefinition();
        clonedAttributeDeclaration.setTypeDefinition
          (createUnresolvedSimpleTypeDefinition
            (theTypeDefinition.getTargetNamespace(), theTypeDefinition.getName()));
      }

      if (deep)
      {
        if (getAnonymousTypeDefinition() != null)
        {
          clonedAttributeDeclaration.setAnonymousTypeDefinition
            ((XSDSimpleTypeDefinition)getAnonymousTypeDefinition().cloneConcreteComponent(deep, shareDOM));
        }
      }
    }

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedAttributeDeclaration.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedAttributeDeclaration.setElement(getElement());
    }

    clonedAttributeDeclaration.isReconciling = shareDOM;
    return clonedAttributeDeclaration;
  }
}
