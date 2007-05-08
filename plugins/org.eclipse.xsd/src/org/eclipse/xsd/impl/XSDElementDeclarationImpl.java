/**
 * <copyright>
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: XSDElementDeclarationImpl.java,v 1.25 2007/05/08 19:15:11 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDConstraint;
import org.eclipse.xsd.XSDContentTypeCategory;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDDisallowedSubstitutions;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDFeature;
import org.eclipse.xsd.XSDForm;
import org.eclipse.xsd.XSDIdentityConstraintDefinition;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDProhibitedSubstitutions;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDSubstitutionGroupExclusions;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDElementDeclarationImpl#isNillable <em>Nillable</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDElementDeclarationImpl#getDisallowedSubstitutions <em>Disallowed Substitutions</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDElementDeclarationImpl#getSubstitutionGroupExclusions <em>Substitution Group Exclusions</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDElementDeclarationImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDElementDeclarationImpl#getLexicalFinal <em>Lexical Final</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDElementDeclarationImpl#getBlock <em>Block</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDElementDeclarationImpl#isElementDeclarationReference <em>Element Declaration Reference</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDElementDeclarationImpl#isCircular <em>Circular</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDElementDeclarationImpl#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDElementDeclarationImpl#getAnonymousTypeDefinition <em>Anonymous Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDElementDeclarationImpl#getTypeDefinition <em>Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDElementDeclarationImpl#getIdentityConstraintDefinitions <em>Identity Constraint Definitions</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDElementDeclarationImpl#getResolvedElementDeclaration <em>Resolved Element Declaration</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDElementDeclarationImpl#getSubstitutionGroupAffiliation <em>Substitution Group Affiliation</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDElementDeclarationImpl#getSubstitutionGroup <em>Substitution Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDElementDeclarationImpl 
  extends XSDFeatureImpl 
  implements XSDElementDeclaration
{
  /**
   * The default value of the '{@link #isNillable() <em>Nillable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNillable()
   * @generated
   * @ordered
   */
  protected static final boolean NILLABLE_EDEFAULT = false;

  /**
   * The flag representing the value of the '{@link #isNillable() <em>Nillable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNillable()
   * @generated
   * @ordered
   */
  protected static final int NILLABLE_EFLAG = 1 << 10;

  /**
   * The flag representing whether the Nillable attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int NILLABLE_ESETFLAG = 1 << 11;

  /**
   * The cached value of the '{@link #getDisallowedSubstitutions() <em>Disallowed Substitutions</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDisallowedSubstitutions()
   * @generated
   * @ordered
   */
  protected EList<XSDDisallowedSubstitutions> disallowedSubstitutions;

  /**
   * The cached value of the '{@link #getSubstitutionGroupExclusions() <em>Substitution Group Exclusions</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubstitutionGroupExclusions()
   * @generated
   * @ordered
   */
  protected EList<XSDSubstitutionGroupExclusions> substitutionGroupExclusions;

  /**
   * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAbstract()
   * @generated
   * @ordered
   */
  protected static final boolean ABSTRACT_EDEFAULT = false;

  /**
   * The flag representing the value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAbstract()
   * @generated
   * @ordered
   */
  protected static final int ABSTRACT_EFLAG = 1 << 12;

  /**
   * The flag representing whether the Abstract attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int ABSTRACT_ESETFLAG = 1 << 13;

  /**
   * The cached value of the '{@link #getLexicalFinal() <em>Lexical Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLexicalFinal()
   * @generated
   * @ordered
   */
  protected EList<XSDProhibitedSubstitutions> lexicalFinal;

  /**
   * The cached value of the '{@link #getBlock() <em>Block</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBlock()
   * @generated
   * @ordered
   */
  protected EList<XSDDisallowedSubstitutions> block;

  /**
   * The default value of the '{@link #isElementDeclarationReference() <em>Element Declaration Reference</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isElementDeclarationReference()
   * @generated
   * @ordered
   */
  protected static final boolean ELEMENT_DECLARATION_REFERENCE_EDEFAULT = false;

  /**
   * The default value of the '{@link #isCircular() <em>Circular</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCircular()
   * @generated
   * @ordered
   */
  protected static final boolean CIRCULAR_EDEFAULT = false;

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
  protected XSDTypeDefinition anonymousTypeDefinition;

  /**
   * The cached value of the '{@link #getTypeDefinition() <em>Type Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeDefinition()
   * @generated
   * @ordered
   */
  protected XSDTypeDefinition typeDefinition;

  /**
   * The cached value of the '{@link #getIdentityConstraintDefinitions() <em>Identity Constraint Definitions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdentityConstraintDefinitions()
   * @generated
   * @ordered
   */
  protected EList<XSDIdentityConstraintDefinition> identityConstraintDefinitions;

  /**
   * The cached value of the '{@link #getResolvedElementDeclaration() <em>Resolved Element Declaration</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResolvedElementDeclaration()
   * @generated
   * @ordered
   */
  protected XSDElementDeclaration resolvedElementDeclaration;

  /**
   * The cached value of the '{@link #getSubstitutionGroupAffiliation() <em>Substitution Group Affiliation</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubstitutionGroupAffiliation()
   * @generated
   * @ordered
   */
  protected XSDElementDeclaration substitutionGroupAffiliation;

  /**
   * The cached value of the '{@link #getSubstitutionGroup() <em>Substitution Group</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubstitutionGroup()
   * @generated
   * @ordered
   */
  protected EList<XSDElementDeclaration> substitutionGroup;

  public static XSDElementDeclaration createElementDeclaration(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.ELEMENT_ELEMENT)
    {
      XSDElementDeclaration xsdElementDeclaration = XSDFactory.eINSTANCE.createXSDElementDeclaration();
      xsdElementDeclaration.setElement((Element)node);
      return xsdElementDeclaration;
    }

    return null;
  }

  protected int analysisState;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected XSDElementDeclarationImpl()
  {
    super();
    this.resolvedElementDeclaration = this;
    // this.setResolvedElementDeclaration = true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return XSDPackage.Literals.XSD_ELEMENT_DECLARATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isNillable()
  {
    return (eFlags & NILLABLE_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNillable(boolean newNillable)
  {
    boolean oldNillable = (eFlags & NILLABLE_EFLAG) != 0;
    if (newNillable) eFlags |= NILLABLE_EFLAG; else eFlags &= ~NILLABLE_EFLAG;
    boolean oldNillableESet = (eFlags & NILLABLE_ESETFLAG) != 0;
    eFlags |= NILLABLE_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ELEMENT_DECLARATION__NILLABLE, oldNillable, newNillable, !oldNillableESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetNillable()
  {
    boolean oldNillable = (eFlags & NILLABLE_EFLAG) != 0;
    boolean oldNillableESet = (eFlags & NILLABLE_ESETFLAG) != 0;
    if (NILLABLE_EDEFAULT) eFlags |= NILLABLE_EFLAG; else eFlags &= ~NILLABLE_EFLAG;
    eFlags &= ~NILLABLE_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, XSDPackage.XSD_ELEMENT_DECLARATION__NILLABLE, oldNillable, NILLABLE_EDEFAULT, oldNillableESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetNillable()
  {
    return (eFlags & NILLABLE_ESETFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDDisallowedSubstitutions> getDisallowedSubstitutions()
  {
    if (disallowedSubstitutions == null)
    {
      disallowedSubstitutions = new EDataTypeUniqueEList<XSDDisallowedSubstitutions>(XSDDisallowedSubstitutions.class, this, XSDPackage.XSD_ELEMENT_DECLARATION__DISALLOWED_SUBSTITUTIONS);
    }
    return disallowedSubstitutions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDSubstitutionGroupExclusions> getSubstitutionGroupExclusions()
  {
    if (substitutionGroupExclusions == null)
    {
      substitutionGroupExclusions = new EDataTypeUniqueEList<XSDSubstitutionGroupExclusions>(XSDSubstitutionGroupExclusions.class, this, XSDPackage.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP_EXCLUSIONS);
    }
    return substitutionGroupExclusions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isAbstract()
  {
    return (eFlags & ABSTRACT_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAbstract(boolean newAbstract)
  {
    boolean oldAbstract = (eFlags & ABSTRACT_EFLAG) != 0;
    if (newAbstract) eFlags |= ABSTRACT_EFLAG; else eFlags &= ~ABSTRACT_EFLAG;
    boolean oldAbstractESet = (eFlags & ABSTRACT_ESETFLAG) != 0;
    eFlags |= ABSTRACT_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ELEMENT_DECLARATION__ABSTRACT, oldAbstract, newAbstract, !oldAbstractESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetAbstract()
  {
    boolean oldAbstract = (eFlags & ABSTRACT_EFLAG) != 0;
    boolean oldAbstractESet = (eFlags & ABSTRACT_ESETFLAG) != 0;
    if (ABSTRACT_EDEFAULT) eFlags |= ABSTRACT_EFLAG; else eFlags &= ~ABSTRACT_EFLAG;
    eFlags &= ~ABSTRACT_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, XSDPackage.XSD_ELEMENT_DECLARATION__ABSTRACT, oldAbstract, ABSTRACT_EDEFAULT, oldAbstractESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetAbstract()
  {
    return (eFlags & ABSTRACT_ESETFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDProhibitedSubstitutions> getLexicalFinal()
  {
    if (lexicalFinal == null)
    {
      lexicalFinal = new EDataTypeUniqueEList.Unsettable<XSDProhibitedSubstitutions>(XSDProhibitedSubstitutions.class, this, XSDPackage.XSD_ELEMENT_DECLARATION__LEXICAL_FINAL);
    }
    return lexicalFinal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetLexicalFinal()
  {
    if (lexicalFinal != null) ((InternalEList.Unsettable<?>)lexicalFinal).unset();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetLexicalFinal()
  {
    return lexicalFinal != null && ((InternalEList.Unsettable<?>)lexicalFinal).isSet();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDDisallowedSubstitutions> getBlock()
  {
    if (block == null)
    {
      block = new EDataTypeUniqueEList.Unsettable<XSDDisallowedSubstitutions>(XSDDisallowedSubstitutions.class, this, XSDPackage.XSD_ELEMENT_DECLARATION__BLOCK);
    }
    return block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetBlock()
  {
    if (block != null) ((InternalEList.Unsettable<?>)block).unset();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetBlock()
  {
    return block != null && ((InternalEList.Unsettable<?>)block).isSet();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public Boolean getElementDeclarationReference() 
  {
    return isElementDeclarationReference() ? Boolean.TRUE : Boolean.FALSE;
  }

  @Override
  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.ELEMENT_ELEMENT);
    setElement(newElement);

    XSDTypeDefinition anonymousTypeDefinition = getAnonymousTypeDefinition();
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
    analysisState = UNANALYZED;
    super.patch();
    XSDElementDeclaration theResolvedElementDeclaration = getResolvedElementDeclaration();
    if (theResolvedElementDeclaration == this)
    {
      XSDTypeDefinition typeDefinition = getTypeDefinition();
      if (typeDefinition != null && (forceResolve && typeDefinition.getName() != null || typeDefinition.getContainer() == null))
      {
        XSDTypeDefinition newTypeDefinition = resolveTypeDefinition(typeDefinition.getTargetNamespace(), typeDefinition.getName());
        if (forceResolve || newTypeDefinition.getContainer() != null && newTypeDefinition != typeDefinition)
        {
          setTypeDefinitionGen(newTypeDefinition);
        }
      }

      XSDElementDeclaration theSubstitutionGroupAffiliation = getSubstitutionGroupAffiliation();
      if (theSubstitutionGroupAffiliation != null && theSubstitutionGroupAffiliation.getContainer() == null)
      {
        XSDElementDeclaration newSubstitutionGroupAffiliation = 
          resolveElementDeclaration(theSubstitutionGroupAffiliation.getTargetNamespace(), theSubstitutionGroupAffiliation.getName());
        if (newSubstitutionGroupAffiliation.getContainer() != null && newSubstitutionGroupAffiliation != theSubstitutionGroupAffiliation)
        {
          setSubstitutionGroupAffiliation(newSubstitutionGroupAffiliation);
        }
      }

      if (!getSubstitutionGroup().contains(this))
      {
        getSubstitutionGroup().add(this);
      }

      XSDSchema xsdSchema = getSchema();
      if (xsdSchema != null)
      {
        //  
        //
        List<XSDDisallowedSubstitutions> theBlock = getBlock();
        if (!isSetBlock())
        {
          theBlock = getSchema().getBlockDefault();
        }
        List<XSDDisallowedSubstitutions> newDisallowedSubstitutions = new ArrayList<XSDDisallowedSubstitutions>();
        for (XSDDisallowedSubstitutions value : theBlock)
        {
          if (value.toString().equals("all"))
          {
            newDisallowedSubstitutions.add(XSDDisallowedSubstitutions.EXTENSION_LITERAL);
            newDisallowedSubstitutions.add(XSDDisallowedSubstitutions.RESTRICTION_LITERAL);
            newDisallowedSubstitutions.add(XSDDisallowedSubstitutions.SUBSTITUTION_LITERAL);
            break;
          }
          else
          {
            newDisallowedSubstitutions.add(value);
          }
        }

        Collection<XSDDisallowedSubstitutions> oldDisallowedSubstitutions = new ArrayList<XSDDisallowedSubstitutions>(getDisallowedSubstitutions());
        oldDisallowedSubstitutions.removeAll(newDisallowedSubstitutions);
        if (!oldDisallowedSubstitutions.isEmpty())
        {
          getDisallowedSubstitutions().removeAll(oldDisallowedSubstitutions);
        }
        setListContentAndOrder(getDisallowedSubstitutions(), newDisallowedSubstitutions);

        //  
        //
        List<XSDProhibitedSubstitutions> theLexicalFinal = getLexicalFinal();
        if (!isSetLexicalFinal())
        {
          theLexicalFinal = getSchema().getFinalDefault();
        }
        List<XSDSubstitutionGroupExclusions> newSubstitutionGroupExclusions = new ArrayList<XSDSubstitutionGroupExclusions>();
        for (Object value : theLexicalFinal)
        {
          if (value.toString().equals("all"))
          {
            newSubstitutionGroupExclusions.add(XSDSubstitutionGroupExclusions.EXTENSION_LITERAL);
            newSubstitutionGroupExclusions.add(XSDSubstitutionGroupExclusions.RESTRICTION_LITERAL);
            break;
          }
          else if (value.toString().equals("restriction"))
          {
            newSubstitutionGroupExclusions.add(XSDSubstitutionGroupExclusions.RESTRICTION_LITERAL);
          }
          else
          {
            newSubstitutionGroupExclusions.add(XSDSubstitutionGroupExclusions.EXTENSION_LITERAL);
          }
        }

        Collection<XSDSubstitutionGroupExclusions> oldSubstitutionGroupExclusions = 
          new ArrayList<XSDSubstitutionGroupExclusions>(getSubstitutionGroupExclusions());
        oldSubstitutionGroupExclusions.removeAll(newSubstitutionGroupExclusions);
        if (!oldSubstitutionGroupExclusions.isEmpty())
        {
          getSubstitutionGroupExclusions().removeAll(oldSubstitutionGroupExclusions);
        }
        setListContentAndOrder(getSubstitutionGroupExclusions(), newSubstitutionGroupExclusions);
      }
    }
    else
    {
      XSDElementDeclaration newResolvedElementDeclaration = 
        resolveElementDeclaration(theResolvedElementDeclaration.getTargetNamespace(), theResolvedElementDeclaration.getName());
      if (newResolvedElementDeclaration != theResolvedElementDeclaration)
      {
        setResolvedElementDeclaration(newResolvedElementDeclaration);
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

  protected boolean isTypeExplicit = false;
  protected void handleAnalysis()
  {
    if (!isElementDeclarationReference())
    {
      XSDElementDeclaration theSubstitutionGroupAffiliation = getSubstitutionGroupAffiliation();
      if (theSubstitutionGroupAffiliation != null && theSubstitutionGroupAffiliation.getContainer() != null)
      {
        ((XSDConcreteComponentImpl)theSubstitutionGroupAffiliation).analyze();
      }
  
      XSDTypeDefinition theTypeDefinition = getTypeDefinition();

      if (!isTypeExplicit || theTypeDefinition == null)
      {
        XSDTypeDefinition newTypeDefinition =
          theSubstitutionGroupAffiliation == null || theSubstitutionGroupAffiliation.getTypeDefinition() == null ?
            getSchema().getSchemaForSchema().resolveTypeDefinition("anyType") :
            theSubstitutionGroupAffiliation.getTypeDefinition();
        if (newTypeDefinition != theTypeDefinition)
        {
          isTypeExplicit = false;
          setTypeDefinitionGen(newTypeDefinition);
        }
      }
    }

    super.analyze();
  }

  @Override
  protected void patchTargetNamespaceAttribute()
  {
    if (!isElementDeclarationReference())
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
    if (!isElementDeclarationReference())
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

    if (isSetForm() ? XSDForm.UNQUALIFIED_LITERAL == getForm() : XSDForm.UNQUALIFIED_LITERAL == xsdSchema.getElementFormDefault())
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
           "element-element",
           theElement,
           new String []
           {
             XSDConstants.ABSTRACT_ATTRIBUTE,
             XSDConstants.BLOCK_ATTRIBUTE,
             XSDConstants.DEFAULT_ATTRIBUTE,
             XSDConstants.FINAL_ATTRIBUTE,
             XSDConstants.FIXED_ATTRIBUTE,
             XSDConstants.ID_ATTRIBUTE,
             XSDConstants.NAME_ATTRIBUTE,
             XSDConstants.NILLABLE_ATTRIBUTE,
             XSDConstants.SUBSTITUTIONGROUP_ATTRIBUTE,
             XSDConstants.TYPE_ATTRIBUTE
           });

        checkComplexContent("topLevelElement", XSDConstants.PART1, "element-element", theElement);

        checkBuiltInTypeConstraint
          ("boolean",
           null,
           XSDConstants.PART1,
           "element-element",
           theElement,
           XSDConstants.ABSTRACT_ATTRIBUTE,
           false);

        checkBuiltInTypeConstraint
          ("derivationSet",
           null,
           XSDConstants.PART1,
           "element-element",
           theElement,
           XSDConstants.FINAL_ATTRIBUTE,
           false);
      }
      else
      {
        checkAttributes
          (XSDConstants.PART1,
           "element-element",
           theElement,
           new String []
           {
             XSDConstants.BLOCK_ATTRIBUTE,
             XSDConstants.DEFAULT_ATTRIBUTE,
             XSDConstants.FIXED_ATTRIBUTE,
             XSDConstants.FORM_ATTRIBUTE,
             XSDConstants.ID_ATTRIBUTE,
             XSDConstants.MAXOCCURS_ATTRIBUTE,
             XSDConstants.MINOCCURS_ATTRIBUTE,
             XSDConstants.NAME_ATTRIBUTE,
             XSDConstants.NILLABLE_ATTRIBUTE,
             XSDConstants.REF_ATTRIBUTE,
             XSDConstants.TYPE_ATTRIBUTE
           });

        checkComplexContent("localElement", XSDConstants.PART1, "element-element", theElement);

        checkBuiltInTypeConstraint
          ("formChoice",
           null,
           XSDConstants.PART1,
           "element-element",
           theElement,
           XSDConstants.FORM_ATTRIBUTE,
           false);

        checkBuiltInTypeConstraint
          ("allNNI",
           null,
           XSDConstants.PART1,
           "element-element",
           theElement,
           XSDConstants.MAXOCCURS_ATTRIBUTE,
           false);

        checkBuiltInTypeConstraint
          ("nonNegativeInteger",
           null,
           XSDConstants.PART1,
           "element-element",
           theElement,
           XSDConstants.MINOCCURS_ATTRIBUTE,
           false);
      }

      if (theElement.hasAttributeNS(null, XSDConstants.FIXED_ATTRIBUTE) &&
            theElement.hasAttributeNS(null, XSDConstants.DEFAULT_ATTRIBUTE))
      {
        createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-element.1");
      }

      checkBuiltInTypeConstraint
        ("ID",
         null,
         XSDConstants.PART1,
         "element-element",
         theElement,
         XSDConstants.ID_ATTRIBUTE,
         false);

      checkBuiltInTypeConstraint
        ("boolean",
         null,
         XSDConstants.PART1,
         "element-element",
         theElement,
         XSDConstants.NILLABLE_ATTRIBUTE,
         false);

      checkBuiltInTypeConstraint
        ("blockSet",
         null,
         XSDConstants.PART1,
         "element-element",
         theElement,
         XSDConstants.BLOCK_ATTRIBUTE,
         false);

      if (theElement.hasAttributeNS(null, XSDConstants.TYPE_ATTRIBUTE))
      {
        for (Node child = theElement.getFirstChild(); child != null; child = child.getNextSibling())
        {
          switch (XSDConstants.nodeType(child))
          {
            case XSDConstants.COMPLEXTYPE_ELEMENT:
            case XSDConstants.SIMPLETYPE_ELEMENT:
            {
              XSDDiagnostic xsdDiagnostic = createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-element.3");
              xsdDiagnostic.setNode(child);
              break;
            }
          }
        }
      }
    }

    if (isElementDeclarationReference())
    {
      XSDElementDeclaration theResolvedElementDeclaration = getResolvedElementDeclaration();
      if (theResolvedElementDeclaration.getContainer() == null)
      {
        createDiagnostic
          (XSDDiagnosticSeverity.ERROR_LITERAL, "_UI_UnresolvedElementDeclaration_message", theResolvedElementDeclaration.getURI());
      }

      if (theElement != null)
      {
        if (theElement.hasAttributeNS(null, XSDConstants.NAME_ATTRIBUTE))
        {
          createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-element.2.1");
        }
        if (theElement.hasAttributeNS(null, XSDConstants.NILLABLE_ATTRIBUTE) ||
              theElement.hasAttributeNS(null, XSDConstants.DEFAULT_ATTRIBUTE) ||
              theElement.hasAttributeNS(null, XSDConstants.FIXED_ATTRIBUTE) ||
              theElement.hasAttributeNS(null, XSDConstants.BLOCK_ATTRIBUTE))
        {
          createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-element.2.2.1");
        }
      }

      if (isSetForm() || theElement != null && theElement.hasAttributeNS(null, XSDConstants.FORM_ATTRIBUTE))
      {
        createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-element.2.2.1");
      }
      if (getType() != null || theElement != null && theElement.hasAttributeNS(null, XSDConstants.TYPE_ATTRIBUTE))
      {
        createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-element.2.2.1");
      }
      else if (theElement != null)
      {
        for (Node child = theElement.getFirstChild(); child != null; child = child.getNextSibling())
        {
          switch (XSDConstants.nodeType(child))
          {
            case XSDConstants.COMPLEXTYPE_ELEMENT:
            case XSDConstants.SIMPLETYPE_ELEMENT:
            case XSDConstants.KEY_ELEMENT:
            case XSDConstants.KEYREF_ELEMENT:
            case XSDConstants.UNIQUE_ELEMENT:
            {
              XSDDiagnostic xsdDiagnostic = createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-element.2.2.2");
              xsdDiagnostic.setNode(child);
              break;
            }
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
         "element-element",
         theElement,
         XSDConstants.NAME_ATTRIBUTE,
         true);

      XSDTypeDefinition theTypeDefinition = getTypeDefinition();
      if (theTypeDefinition == null || theTypeDefinition.getContainer() == null)
      {
        createDiagnostic
          (XSDDiagnosticSeverity.ERROR_LITERAL, 
            "_UI_UnresolvedTypeDefinition_message", 
            theTypeDefinition == null ? "" : theTypeDefinition.getURI());
      }

      if (theTypeDefinition != null)
      {
        if (getLexicalValue() != null)
        {
          XSDSimpleTypeDefinition xsdSimpleTypeDefinition;
          if (theTypeDefinition instanceof XSDComplexTypeDefinition)
          {
            XSDComplexTypeDefinition xsdComplexTypeDefinition = (XSDComplexTypeDefinition)theTypeDefinition;
            xsdSimpleTypeDefinition = ((XSDComplexTypeDefinition)theTypeDefinition).getSimpleType();
            if (xsdSimpleTypeDefinition == null)
            {
              if (xsdComplexTypeDefinition.getContentTypeCategory() != XSDContentTypeCategory.MIXED_LITERAL ||
                    !xsdComplexTypeDefinition.getComplexType().isEmptiable())
              {
                createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cos-valid-default.2.2.2");
              }
            }
          }
          else
          {
            xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)theTypeDefinition;
          }
  
          if (xsdSimpleTypeDefinition != null)
          {
            if (XSDConstants.isOrIsDerivedFromID(xsdSimpleTypeDefinition))
            {
              createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "e-props-correct.4");
            }
            else
            {
              checkSimpleTypeConstraint
                (xsdSimpleTypeDefinition,
                 getLexicalValue(),
                 XSDConstants.PART1,
                 "element-element",
                 theElement,
                 getConstraint() == XSDConstraint.FIXED_LITERAL ? XSDConstants.FIXED_ATTRIBUTE : XSDConstants.DEFAULT_ATTRIBUTE,
                 false);
            }
          }
        }
      }

      XSDElementDeclaration substitutionGroupAffiliation = getSubstitutionGroupAffiliation();
      if (substitutionGroupAffiliation != null)
      {
        if (substitutionGroupAffiliation.getResolvedElementDeclaration().getContainer() == null)
        {
          createDiagnostic
            (XSDDiagnosticSeverity.ERROR_LITERAL, "_UI_UnresolvedElementDeclaration_message", substitutionGroupAffiliation.getURI());
        }
        else if (theTypeDefinition != null)
        {
          EList<XSDSubstitutionGroupExclusions> substitutionGroupExclusions = substitutionGroupAffiliation.getSubstitutionGroupExclusions();
          XSDTypeDefinition badTypeDefinition = 
            theTypeDefinition.getBadTypeDerivation
              (substitutionGroupAffiliation.getTypeDefinition(),
               !substitutionGroupExclusions.contains(XSDSubstitutionGroupExclusions.EXTENSION_LITERAL),
               !substitutionGroupExclusions.contains(XSDSubstitutionGroupExclusions.RESTRICTION_LITERAL));
          if (badTypeDefinition != null)
          {
            if (XSDConstants.isURType(badTypeDefinition) && substitutionGroupAffiliation.getTypeDefinition() != null)
            {
              createDiagnostic
                (XSDDiagnosticSeverity.ERROR_LITERAL, "e-props-correct.3", substitutionGroupAffiliation.getTypeDefinition().getURI());
            }
            else
            {
              createDiagnostic
                (XSDDiagnosticSeverity.ERROR_LITERAL, "cos-ct-derived-ok", badTypeDefinition.getURI());
            }
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
      XSDElementDeclaration newResolvedElementDeclaration = this;
      if (changedElement.hasAttributeNS(null, XSDConstants.REF_ATTRIBUTE))
      {
        newResolvedElementDeclaration =
          resolveElementDeclarationURI(XSDConstants.lookupQNameForAttribute(changedElement, XSDConstants.REF_ATTRIBUTE));
      }
/*
      else if (!changedElement.hasAttributeNS(null, XSDConstants.NAME_ATTRIBUTE))
      {
        newResolvedElementDeclaration = resolveElementDeclaration(null, "undefined");
      }
*/
      if (newResolvedElementDeclaration != getResolvedElementDeclaration())
      {
        setResolvedElementDeclaration(newResolvedElementDeclaration);
      }

      if (this == newResolvedElementDeclaration)
      {
        if (changedElement.hasAttributeNS(null, XSDConstants.TYPE_ATTRIBUTE))
        {
          isTypeExplicit = true;
          XSDTypeDefinition newTypeDefinition =
            resolveTypeDefinitionURI(XSDConstants.lookupQNameForAttribute(changedElement, XSDConstants.TYPE_ATTRIBUTE));
          if (newTypeDefinition != getTypeDefinition())
          {
            setTypeDefinition(newTypeDefinition);
          }
        }
        else if (getAnonymousTypeDefinition() != getTypeDefinition())
        {
          isTypeExplicit = false;
          setTypeDefinitionGen(resolveSimpleTypeDefinition(changedElement.getNamespaceURI(), "anyType"));
        }

        if (changedElement.hasAttributeNS(null, XSDConstants.NILLABLE_ATTRIBUTE))
        {
          String newNillable = changedElement.getAttributeNS(null, XSDConstants.NILLABLE_ATTRIBUTE);
          setNillable("true".equals(newNillable));
        }
        else 
        {
          unsetNillable();
        }

        if (changedElement.hasAttributeNS(null, XSDConstants.ABSTRACT_ATTRIBUTE))
        {
          String newAbstract = changedElement.getAttributeNS(null, XSDConstants.ABSTRACT_ATTRIBUTE);
          setAbstract("true".equals(newAbstract));
        }
        else 
        {
          unsetAbstract();
        }

        if (getScope() instanceof XSDSchema)
        {
          if (XSDForm.QUALIFIED_LITERAL != getForm())
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

        if (changedElement.hasAttributeNS(null, XSDConstants.FINAL_ATTRIBUTE))
        {
          setStringLexicalFinal(changedElement.getAttributeNS(null, XSDConstants.FINAL_ATTRIBUTE));
        }
        else if (isSetLexicalFinal())
        {
          unsetLexicalFinal();
        }

        if (changedElement.hasAttributeNS(null, XSDConstants.BLOCK_ATTRIBUTE))
        {
          setStringBlock(changedElement.getAttributeNS(null, XSDConstants.BLOCK_ATTRIBUTE));
        }
        else if (isSetBlock())
        {
          unsetBlock();
        }

        XSDElementDeclaration newSubstituionGroupAffiliation = null;
        if (changedElement.hasAttributeNS(null, XSDConstants.SUBSTITUTIONGROUP_ATTRIBUTE))
        {
          newSubstituionGroupAffiliation = 
            resolveElementDeclarationURI(XSDConstants.lookupQNameForAttribute(changedElement, XSDConstants.SUBSTITUTIONGROUP_ATTRIBUTE));
        }
  
        if (newSubstituionGroupAffiliation != getSubstitutionGroupAffiliation())
        {
          setSubstitutionGroupAffiliation(newSubstituionGroupAffiliation);
        }
      }
    }
  }

  @Override
  protected void reconcileNameAttribute()
  {
    if (!isElementDeclarationReference())
    {
      super.reconcileNameAttribute();
    }
  }

  @Override
  protected void reconcileContents(Element changedElement)
  {
    super.reconcileContents(changedElement);
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
      XSDTypeDefinition xsdTypeDefinition = XSDTypeDefinitionImpl.createTypeDefinition(child);
      if (xsdTypeDefinition != null)
      {
        newContents.add(xsdTypeDefinition);
      }
      else
      {
        XSDIdentityConstraintDefinition xsdIdentityConstraintDefinition = 
          XSDIdentityConstraintDefinitionImpl.createIdentityConstraintDefinition(child);
        if (xsdIdentityConstraintDefinition != null)
        {
          newContents.add(xsdIdentityConstraintDefinition);
        }
      }
    }
  }

  @Override
  protected void handleReconciliation(List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    handleAnnotationReconciliation(XSDPackage.Literals.XSD_ELEMENT_DECLARATION__ANNOTATION, newContents, remainingContents);

    if (!isElementDeclarationReference())
    {
      Element theElement = getElement();
      XSDTypeDefinition newTypeDefinition = null;
      XSDTypeDefinition newAnonymousTypeDefinition = null;
      if (!newContents.isEmpty())
      {
        XSDConcreteComponent newComponent = newContents.get(0);
        if (newComponent instanceof XSDTypeDefinition)
        {
          newTypeDefinition = newAnonymousTypeDefinition = (XSDTypeDefinition)newComponent;
          newContents.remove(0);
        }
      }

      if (newAnonymousTypeDefinition != getAnonymousTypeDefinition())
      {
        remainingContents.remove(getAnonymousTypeDefinition());
        setAnonymousTypeDefinition(newAnonymousTypeDefinition);
      }

      for (Iterator<XSDConcreteComponent> i = newContents.iterator(); i.hasNext(); )
      {
        if (!(i.next() instanceof XSDIdentityConstraintDefinition))
        {
          i.remove();
        }
      }

      if (!remainingContents.isEmpty())
      {
        getIdentityConstraintDefinitions().removeAll(remainingContents);
      }

      if (!newContents.isEmpty())
      {
        @SuppressWarnings("unchecked") List<XSDIdentityConstraintDefinition> list = (List<XSDIdentityConstraintDefinition>)(List<?>)newContents;
        setListContentAndOrder(getIdentityConstraintDefinitions(), list);
      }

      if (theElement.hasAttributeNS(null, XSDConstants.TYPE_ATTRIBUTE))
      {
        isTypeExplicit = true;
        newTypeDefinition = resolveTypeDefinitionURI(XSDConstants.lookupQNameForAttribute(theElement, XSDConstants.TYPE_ATTRIBUTE));
      }

      if (newTypeDefinition != null && newTypeDefinition != getTypeDefinition())
      {
        setTypeDefinitionGen(newTypeDefinition);
      }
    }
  }

  @Override
  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    if (!isElementDeclarationReference())
    {
      if (!isReconciling)
      {
        Element theElement = getElement();
        if (theElement != null)
        {
          if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_ELEMENT_DECLARATION__ABSTRACT)
          {
            niceSetAttribute(theElement, XSDConstants.ABSTRACT_ATTRIBUTE, isSetAbstract() ? isAbstract() ? "true" : "false" : null);
          }
          if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_ELEMENT_DECLARATION__NILLABLE)
          {
            niceSetAttribute(theElement, XSDConstants.NILLABLE_ATTRIBUTE, isSetNillable() ? isNillable() ? "true" : "false" : null);
          }
          if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_FEATURE__FORM)
          {
            if (!(getScope() instanceof XSDSchema))
            {
              niceSetAttribute(theElement, XSDConstants.FORM_ATTRIBUTE, isSetForm() ? getForm().getName() : null);
            }
          }
          if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_ELEMENT_DECLARATION__LEXICAL_FINAL)
          {
            niceSetAttribute
              (theElement,
               XSDConstants.FINAL_ATTRIBUTE,
               getStringLexicalFinal());
            if (eAttribute != null)
            {
              traverseToRootForPatching();
            }
          }

          if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_ELEMENT_DECLARATION__BLOCK)
          {
            niceSetAttribute
              (theElement,
               XSDConstants.BLOCK_ATTRIBUTE,
               getStringBlock());
            if (eAttribute != null)
            {
              traverseToRootForPatching();
            }
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
  public void eNotify(Notification msg)
  {
    int eventType = msg.getEventType();
    Object feature = msg.getFeature();
    Object oldValue = msg.getOldValue();
    Object newValue = msg.getNewValue();

    if (feature == XSDPackage.Literals.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP_AFFILIATION)
    {
      switch (eventType)
      {
        case Notification.SET:
        case Notification.UNSET:
        {
          Collection<XSDElementDeclaration> substitutionGroup = new ArrayList<XSDElementDeclaration>(getSubstitutionGroup());
          substitutionGroup.add(this);

          if (oldValue != null)
          {
            Collection<XSDElementDeclaration> visited = new HashSet<XSDElementDeclaration>();
            for (XSDElementDeclaration oldSubstitutionGroupAffiliation = (XSDElementDeclaration)oldValue;
                 oldSubstitutionGroupAffiliation != null;
                 oldSubstitutionGroupAffiliation = oldSubstitutionGroupAffiliation.getSubstitutionGroupAffiliation())
            {
              if (visited.add(oldSubstitutionGroupAffiliation))
              {
                oldSubstitutionGroupAffiliation.getSubstitutionGroup().removeAll(substitutionGroup);
              }
              else
              {
                break;
              }
            }
          }
          if (newValue != null)
          {
            Collection<XSDElementDeclaration> visited = new HashSet<XSDElementDeclaration>();
            for (XSDElementDeclaration newSubstitutionGroupAffiliation = (XSDElementDeclaration)newValue;
                 newSubstitutionGroupAffiliation != null;
                 newSubstitutionGroupAffiliation = newSubstitutionGroupAffiliation.getSubstitutionGroupAffiliation())
            {
              if (visited.add(newSubstitutionGroupAffiliation))
              {
                newSubstitutionGroupAffiliation.getSubstitutionGroup().addAll(substitutionGroup);
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
    }
    super.eNotify(msg);
  }

  @Override
  protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID)
  {
    if (substitutionGroupAffiliation != null)
    {
      EList<XSDElementDeclaration> substitutionGroup = getSubstitutionGroup();
      Collection<XSDElementDeclaration> visited = new HashSet<XSDElementDeclaration>();
      if (newContainer == null)
      {
        for (XSDElementDeclaration oldSubstitutionGroupAffiliation = substitutionGroupAffiliation;
             oldSubstitutionGroupAffiliation != null;
             oldSubstitutionGroupAffiliation = oldSubstitutionGroupAffiliation.getSubstitutionGroupAffiliation())
        {
          if (visited.add(oldSubstitutionGroupAffiliation))
          {
            oldSubstitutionGroupAffiliation.getSubstitutionGroup().removeAll(substitutionGroup);
          }
          else
          {
            break;
          }
        }
      }
      else
      {
        for (XSDElementDeclaration oldSubstitutionGroupAffiliation = substitutionGroupAffiliation;
             oldSubstitutionGroupAffiliation != null;
             oldSubstitutionGroupAffiliation = oldSubstitutionGroupAffiliation.getSubstitutionGroupAffiliation())
        {
          if (visited.add(oldSubstitutionGroupAffiliation))
          {
            oldSubstitutionGroupAffiliation.getSubstitutionGroup().removeAll(substitutionGroup);
          }
          else
          {
            break;
          }
        }
      }
    }
    super.eBasicSetContainer(newContainer, newContainerFeatureID);
  }

  @Override
  protected void changeReference(EReference eReference)
  {
    super.changeReference(eReference);
    Element theElement = getElement();
    if (isElementDeclarationReference())
    {
      if (eReference == null || eReference == XSDPackage.Literals.XSD_ELEMENT_DECLARATION__RESOLVED_ELEMENT_DECLARATION)
      {
        XSDElementDeclaration theResolvedElementDeclaration = getResolvedElementDeclaration();
        if (theElement != null)
        {
          niceSetAttributeURIValue(theElement, XSDConstants.REF_ATTRIBUTE, theResolvedElementDeclaration.getURI());
        }
        if (eReference != null && getContainer() instanceof XSDParticle)
        {
          ((XSDParticle)getContainer()).setTerm(theResolvedElementDeclaration);
        }
      }
    }
    else
    {
      if (theElement != null && eReference == XSDPackage.Literals.XSD_ELEMENT_DECLARATION__RESOLVED_ELEMENT_DECLARATION)
      {
        niceSetAttributeURIValue(theElement, XSDConstants.REF_ATTRIBUTE, null);
      }
      
      if (eReference == null || eReference == XSDPackage.Literals.XSD_ELEMENT_DECLARATION__TYPE_DEFINITION)
      {
        XSDTypeDefinition theTypeDefinition = getTypeDefinition();
        XSDTypeDefinition theAnonymousTypeDefinition = getAnonymousTypeDefinition();

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
      if (eReference == null || eReference == XSDPackage.Literals.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP_AFFILIATION)
      {
        if (theElement != null)
        {
          XSDElementDeclaration theSubstitutionGroupAffiliation = getSubstitutionGroupAffiliation();
          if (theSubstitutionGroupAffiliation == null)
          {
            niceSetAttribute(theElement, XSDConstants.SUBSTITUTIONGROUP_ATTRIBUTE, null);
          }
          else
          {
            niceSetAttributeURIValue(theElement, XSDConstants.SUBSTITUTIONGROUP_ATTRIBUTE, theSubstitutionGroupAffiliation.getURI());
          }
        }
      }
    }
  }

  @Override
  protected void adoptContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.adoptContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_ELEMENT_DECLARATION__ANONYMOUS_TYPE_DEFINITION)
    {
      setTypeDefinition((XSDTypeDefinition)xsdConcreteComponent);
    }
  }

  @Override
  protected void orphanContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.orphanContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_ELEMENT_DECLARATION__ANONYMOUS_TYPE_DEFINITION)
    {
      if (getTypeDefinition() == xsdConcreteComponent)
      {
        setTypeDefinition(null);
      }
    }
  }

  public boolean isElementDeclarationReference()
  {
    return this != getResolvedElementDeclaration();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public Boolean getCircular() 
  {
    return isCircular() ? Boolean.TRUE : Boolean.FALSE;
  }

  @Override
  public String getQName()
  {
    XSDElementDeclaration resolvedElementDeclaration = getResolvedElementDeclaration();
    if (resolvedElementDeclaration == this)
    {
      return super.getQName();
    }
    else
    {
      return resolvedElementDeclaration.getQName(this);
    }
  }

  @Override
  public boolean isNamedComponentReference()
  {
    return isElementDeclarationReference();
  }

  @Override
  public XSDNamedComponent getResolvedNamedComponent()
  {
    return getResolvedElementDeclaration();
  }

  @Override
  public boolean isFeatureReference()
  {
    return isElementDeclarationReference();
  }

  @Override
  public XSDFeature getResolvedFeature()
  {
    return getResolvedElementDeclaration();
  }

  @Override
  public XSDTypeDefinition getType()
  {
    return getTypeDefinition();
  }

  public String getStringLexicalFinal()
  {
    if (isSetLexicalFinal())
    {
      StringBuffer result = new StringBuffer();
      for (Object literal : getLexicalFinal())
      {
        if (result.length() != 0)
        {
          result.append(' ');
        }
        if (literal.toString().equals("all"))
        {
          result.append("#all");
        }
        else
        {
          result.append(literal);
        }
      }
      return result.toString();
    }
    else
    {
      return null;
    }
  }

  public void setStringLexicalFinal(String finalDefault)
  {
    if (finalDefault == null)
    {
      unsetLexicalFinal();
    }
    else
    {
      List<XSDProhibitedSubstitutions> newLexicalFinal = new ArrayList<XSDProhibitedSubstitutions>();
      for (StringTokenizer stringTokenizer = new StringTokenizer(finalDefault); stringTokenizer.hasMoreTokens(); )
      {
        String token = stringTokenizer.nextToken();
        if (token.equals("#all"))
        {
          token = "all";
        }

        XSDProhibitedSubstitutions literal = XSDProhibitedSubstitutions.get(token);
        if (literal != null)
        {
          newLexicalFinal.add(literal);
        }
      }
      if (!newLexicalFinal.equals(getLexicalFinal()))
      {
        Collection<XSDProhibitedSubstitutions> oldContents = new ArrayList<XSDProhibitedSubstitutions>(getLexicalFinal());
        oldContents.removeAll(newLexicalFinal);
        if (!oldContents.isEmpty())
        {
          getLexicalFinal().removeAll(oldContents);
        }
        setListContentAndOrder(getLexicalFinal(), newLexicalFinal);
      }
      else if (newLexicalFinal.isEmpty() && !isSetLexicalFinal())
      {
        getLexicalFinal().clear();
      }
    }
  }

  public String getStringBlock()
  {
    if (isSetBlock())
    {
      StringBuffer result = new StringBuffer();
      for (Object literal : getBlock())
      {
        if (result.length() != 0)
        {
          result.append(' ');
        }
        if (literal.toString().equals("all"))
        {
          result.append("#all");
        }
        else
        {
          result.append(literal);
        }
      }
      return result.toString();
    }
    else
    {
      return null;
    }
  }

  public void setStringBlock(String block)
  {
    if (block == null)
    {
      unsetBlock();
    }
    else
    {
      List<XSDDisallowedSubstitutions> newBlock = new ArrayList<XSDDisallowedSubstitutions>();
      for (StringTokenizer stringTokenizer = new StringTokenizer(block); stringTokenizer.hasMoreTokens(); )
      {
        String token = stringTokenizer.nextToken();
        if (token.equals("#all"))
        {
          token = "all";
        }
        XSDDisallowedSubstitutions literal = XSDDisallowedSubstitutions.get(token);
        if (literal != null)
        {
          newBlock.add(literal);
        }
      }
      if (!newBlock.equals(getBlock()))
      {
        Collection<XSDDisallowedSubstitutions> oldContents = new ArrayList<XSDDisallowedSubstitutions>(getBlock());
        oldContents.removeAll(newBlock);
        if (!oldContents.isEmpty())
        {
          getBlock().removeAll(oldContents);
        }
        setListContentAndOrder(getBlock(), newBlock);
      }
      else if (newBlock.isEmpty() && !isSetBlock())
      {
        getBlock().clear();
      }
    }
  }

  public String getStringDisallowedSubstitutions()
  {
    StringBuffer result = new StringBuffer();
    for (Object literal : getDisallowedSubstitutions())
    {
      if (result.length() != 0)
      {
        result.append(' ');
      }
      result.append(literal);
    }
    return result.toString();
  }

  public String getStringSubstitutionGroupExclusions()
  { 
    StringBuffer result = new StringBuffer();
    for (Object literal : getSubstitutionGroupExclusions())
    {
      if (result.length() != 0)
      {
        result.append(' ');
      }
      result.append(literal);
    }
    return result.toString();
  }

  public boolean isCircular()
  {
    return analysisState == CIRCULAR;
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
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_ELEMENT_DECLARATION__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_ELEMENT_DECLARATION__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ELEMENT_DECLARATION__ANNOTATION, newAnnotation, newAnnotation));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ELEMENT_DECLARATION__ANNOTATION, oldAnnotation, newAnnotation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDTypeDefinition getAnonymousTypeDefinition()
  {
    return anonymousTypeDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnonymousTypeDefinition(XSDTypeDefinition newAnonymousTypeDefinition)
  {
    if (newAnonymousTypeDefinition != anonymousTypeDefinition)
    {
      NotificationChain msgs = null;
      if (anonymousTypeDefinition != null)
        msgs = ((InternalEObject)anonymousTypeDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_ELEMENT_DECLARATION__ANONYMOUS_TYPE_DEFINITION, null, msgs);
      if (newAnonymousTypeDefinition != null)
        msgs = ((InternalEObject)newAnonymousTypeDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_ELEMENT_DECLARATION__ANONYMOUS_TYPE_DEFINITION, null, msgs);
      msgs = basicSetAnonymousTypeDefinition(newAnonymousTypeDefinition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ELEMENT_DECLARATION__ANONYMOUS_TYPE_DEFINITION, newAnonymousTypeDefinition, newAnonymousTypeDefinition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAnonymousTypeDefinition(XSDTypeDefinition newAnonymousTypeDefinition, NotificationChain msgs)
  {
    XSDTypeDefinition oldAnonymousTypeDefinition = anonymousTypeDefinition;
    anonymousTypeDefinition = newAnonymousTypeDefinition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ELEMENT_DECLARATION__ANONYMOUS_TYPE_DEFINITION, oldAnonymousTypeDefinition, newAnonymousTypeDefinition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDTypeDefinition getTypeDefinition()
  {
    return typeDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTypeDefinitionGen(XSDTypeDefinition newTypeDefinition)
  {
    XSDTypeDefinition oldTypeDefinition = typeDefinition;
    typeDefinition = newTypeDefinition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ELEMENT_DECLARATION__TYPE_DEFINITION, oldTypeDefinition, typeDefinition));
  }

  public void setTypeDefinition(XSDTypeDefinition newTypeDefinition)
  {
    isTypeExplicit = newTypeDefinition != null;
    setTypeDefinitionGen(newTypeDefinition);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDIdentityConstraintDefinition> getIdentityConstraintDefinitions()
  {
    if (identityConstraintDefinitions == null)
    {
      identityConstraintDefinitions = new EObjectContainmentEList<XSDIdentityConstraintDefinition>(XSDIdentityConstraintDefinition.class, this, XSDPackage.XSD_ELEMENT_DECLARATION__IDENTITY_CONSTRAINT_DEFINITIONS);
    }
    return identityConstraintDefinitions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDElementDeclaration getResolvedElementDeclaration()
  {
    return resolvedElementDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResolvedElementDeclaration(XSDElementDeclaration newResolvedElementDeclaration)
  {
    XSDElementDeclaration oldResolvedElementDeclaration = resolvedElementDeclaration;
    resolvedElementDeclaration = newResolvedElementDeclaration;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ELEMENT_DECLARATION__RESOLVED_ELEMENT_DECLARATION, oldResolvedElementDeclaration, resolvedElementDeclaration));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDElementDeclaration getSubstitutionGroupAffiliation()
  {
    return substitutionGroupAffiliation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSubstitutionGroupAffiliation(XSDElementDeclaration newSubstitutionGroupAffiliation)
  {
    XSDElementDeclaration oldSubstitutionGroupAffiliation = substitutionGroupAffiliation;
    substitutionGroupAffiliation = newSubstitutionGroupAffiliation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP_AFFILIATION, oldSubstitutionGroupAffiliation, substitutionGroupAffiliation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDElementDeclaration> getSubstitutionGroup()
  {
    if (substitutionGroup == null)
    {
      substitutionGroup = new EObjectEList<XSDElementDeclaration>(XSDElementDeclaration.class, this, XSDPackage.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP);
    }
    return substitutionGroup;
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
      case XSDPackage.XSD_ELEMENT_DECLARATION__ANNOTATION:
        return basicSetAnnotation(null, msgs);
      case XSDPackage.XSD_ELEMENT_DECLARATION__ANONYMOUS_TYPE_DEFINITION:
        return basicSetAnonymousTypeDefinition(null, msgs);
      case XSDPackage.XSD_ELEMENT_DECLARATION__IDENTITY_CONSTRAINT_DEFINITIONS:
        return ((InternalEList<?>)getIdentityConstraintDefinitions()).basicRemove(otherEnd, msgs);
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
      case XSDPackage.XSD_ELEMENT_DECLARATION__NILLABLE:
        return isNillable() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_ELEMENT_DECLARATION__DISALLOWED_SUBSTITUTIONS:
        return getDisallowedSubstitutions();
      case XSDPackage.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP_EXCLUSIONS:
        return getSubstitutionGroupExclusions();
      case XSDPackage.XSD_ELEMENT_DECLARATION__ABSTRACT:
        return isAbstract() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_ELEMENT_DECLARATION__LEXICAL_FINAL:
        return getLexicalFinal();
      case XSDPackage.XSD_ELEMENT_DECLARATION__BLOCK:
        return getBlock();
      case XSDPackage.XSD_ELEMENT_DECLARATION__ELEMENT_DECLARATION_REFERENCE:
        return isElementDeclarationReference() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_ELEMENT_DECLARATION__CIRCULAR:
        return isCircular() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_ELEMENT_DECLARATION__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_ELEMENT_DECLARATION__ANONYMOUS_TYPE_DEFINITION:
        return getAnonymousTypeDefinition();
      case XSDPackage.XSD_ELEMENT_DECLARATION__TYPE_DEFINITION:
        return getTypeDefinition();
      case XSDPackage.XSD_ELEMENT_DECLARATION__IDENTITY_CONSTRAINT_DEFINITIONS:
        return getIdentityConstraintDefinitions();
      case XSDPackage.XSD_ELEMENT_DECLARATION__RESOLVED_ELEMENT_DECLARATION:
        return getResolvedElementDeclaration();
      case XSDPackage.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP_AFFILIATION:
        return getSubstitutionGroupAffiliation();
      case XSDPackage.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP:
        return getSubstitutionGroup();
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
      case XSDPackage.XSD_ELEMENT_DECLARATION__NILLABLE:
        setNillable(((Boolean)newValue).booleanValue());
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__DISALLOWED_SUBSTITUTIONS:
        getDisallowedSubstitutions().clear();
        getDisallowedSubstitutions().addAll((Collection<? extends XSDDisallowedSubstitutions>)newValue);
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP_EXCLUSIONS:
        getSubstitutionGroupExclusions().clear();
        getSubstitutionGroupExclusions().addAll((Collection<? extends XSDSubstitutionGroupExclusions>)newValue);
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__ABSTRACT:
        setAbstract(((Boolean)newValue).booleanValue());
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__LEXICAL_FINAL:
        getLexicalFinal().clear();
        getLexicalFinal().addAll((Collection<? extends XSDProhibitedSubstitutions>)newValue);
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__BLOCK:
        getBlock().clear();
        getBlock().addAll((Collection<? extends XSDDisallowedSubstitutions>)newValue);
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__ANONYMOUS_TYPE_DEFINITION:
        setAnonymousTypeDefinition((XSDTypeDefinition)newValue);
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__TYPE_DEFINITION:
        setTypeDefinition((XSDTypeDefinition)newValue);
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__IDENTITY_CONSTRAINT_DEFINITIONS:
        getIdentityConstraintDefinitions().clear();
        getIdentityConstraintDefinitions().addAll((Collection<? extends XSDIdentityConstraintDefinition>)newValue);
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__RESOLVED_ELEMENT_DECLARATION:
        setResolvedElementDeclaration((XSDElementDeclaration)newValue);
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP_AFFILIATION:
        setSubstitutionGroupAffiliation((XSDElementDeclaration)newValue);
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP:
        getSubstitutionGroup().clear();
        getSubstitutionGroup().addAll((Collection<? extends XSDElementDeclaration>)newValue);
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
      case XSDPackage.XSD_ELEMENT_DECLARATION__NILLABLE:
        unsetNillable();
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__DISALLOWED_SUBSTITUTIONS:
        getDisallowedSubstitutions().clear();
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP_EXCLUSIONS:
        getSubstitutionGroupExclusions().clear();
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__ABSTRACT:
        unsetAbstract();
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__LEXICAL_FINAL:
        unsetLexicalFinal();
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__BLOCK:
        unsetBlock();
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__ANONYMOUS_TYPE_DEFINITION:
        setAnonymousTypeDefinition((XSDTypeDefinition)null);
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__TYPE_DEFINITION:
        setTypeDefinition((XSDTypeDefinition)null);
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__IDENTITY_CONSTRAINT_DEFINITIONS:
        getIdentityConstraintDefinitions().clear();
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__RESOLVED_ELEMENT_DECLARATION:
        setResolvedElementDeclaration((XSDElementDeclaration)null);
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP_AFFILIATION:
        setSubstitutionGroupAffiliation((XSDElementDeclaration)null);
        return;
      case XSDPackage.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP:
        getSubstitutionGroup().clear();
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
      case XSDPackage.XSD_ELEMENT_DECLARATION__NILLABLE:
        return isSetNillable();
      case XSDPackage.XSD_ELEMENT_DECLARATION__DISALLOWED_SUBSTITUTIONS:
        return disallowedSubstitutions != null && !disallowedSubstitutions.isEmpty();
      case XSDPackage.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP_EXCLUSIONS:
        return substitutionGroupExclusions != null && !substitutionGroupExclusions.isEmpty();
      case XSDPackage.XSD_ELEMENT_DECLARATION__ABSTRACT:
        return isSetAbstract();
      case XSDPackage.XSD_ELEMENT_DECLARATION__LEXICAL_FINAL:
        return isSetLexicalFinal();
      case XSDPackage.XSD_ELEMENT_DECLARATION__BLOCK:
        return isSetBlock();
      case XSDPackage.XSD_ELEMENT_DECLARATION__ELEMENT_DECLARATION_REFERENCE:
        return isElementDeclarationReference() != ELEMENT_DECLARATION_REFERENCE_EDEFAULT;
      case XSDPackage.XSD_ELEMENT_DECLARATION__CIRCULAR:
        return isCircular() != CIRCULAR_EDEFAULT;
      case XSDPackage.XSD_ELEMENT_DECLARATION__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_ELEMENT_DECLARATION__ANONYMOUS_TYPE_DEFINITION:
        return anonymousTypeDefinition != null;
      case XSDPackage.XSD_ELEMENT_DECLARATION__TYPE_DEFINITION:
        return typeDefinition != null;
      case XSDPackage.XSD_ELEMENT_DECLARATION__IDENTITY_CONSTRAINT_DEFINITIONS:
        return identityConstraintDefinitions != null && !identityConstraintDefinitions.isEmpty();
      case XSDPackage.XSD_ELEMENT_DECLARATION__RESOLVED_ELEMENT_DECLARATION:
        return resolvedElementDeclaration != null;
      case XSDPackage.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP_AFFILIATION:
        return substitutionGroupAffiliation != null;
      case XSDPackage.XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP:
        return substitutionGroup != null && !substitutionGroup.isEmpty();
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
    result.append(" (nillable: ");
    if ((eFlags & NILLABLE_ESETFLAG) != 0) result.append((eFlags & NILLABLE_EFLAG) != 0); else result.append("<unset>");
    result.append(", disallowedSubstitutions: ");
    result.append(disallowedSubstitutions);
    result.append(", substitutionGroupExclusions: ");
    result.append(substitutionGroupExclusions);
    result.append(", abstract: ");
    if ((eFlags & ABSTRACT_ESETFLAG) != 0) result.append((eFlags & ABSTRACT_EFLAG) != 0); else result.append("<unset>");
    result.append(", lexicalFinal: ");
    result.append(lexicalFinal);
    result.append(", block: ");
    result.append(block);
    result.append(')');
    return result.toString();
  }

  @Override
  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDElementDeclarationImpl clonedElementDeclaration =
      (XSDElementDeclarationImpl)getXSDFactory().createXSDElementDeclaration();
    clonedElementDeclaration.isReconciling = true;

    if (isElementDeclarationReference())
    {
      XSDElementDeclaration theResolvedElementDeclaration = getResolvedElementDeclaration();
      clonedElementDeclaration.setResolvedElementDeclaration
        (createUnresolvedElementDeclaration
          (theResolvedElementDeclaration.getTargetNamespace(), theResolvedElementDeclaration.getName()));
    }
    else
    {
      if (getName() != null)
      {
        clonedElementDeclaration.setName(getName());
      }
      if (isSetForm())
      {
        clonedElementDeclaration.setForm(getForm());
      }
      if (isSetConstraint())
      {
        clonedElementDeclaration.setConstraint(getConstraint());
      }
      if (getLexicalValue() != null)
      {
        clonedElementDeclaration.setLexicalValue(getLexicalValue());
      }
      if (isSetNillable())
      {
        clonedElementDeclaration.setNillable(isNillable());
      }
      if (isSetAbstract())
      {
        clonedElementDeclaration.setAbstract(isAbstract());
      }
      if (isSetBlock())
      {
        if (!getBlock().isEmpty())
        {
          clonedElementDeclaration.getBlock().addAll(getBlock());
        }
        else
        {
          clonedElementDeclaration.getBlock().clear();
        }
      }
      if (isSetLexicalFinal())
      {
        if (!getLexicalFinal().isEmpty())
        {
          clonedElementDeclaration.getLexicalFinal().addAll(getLexicalFinal());
        }
        else
        {
          clonedElementDeclaration.getLexicalFinal().clear();
        }
      }

      if (getTypeDefinition() != null && getTypeDefinition() != getAnonymousTypeDefinition())
      {
        XSDTypeDefinition theTypeDefinition = getTypeDefinition();
        clonedElementDeclaration.setTypeDefinition
          (createUnresolvedTypeDefinition
            (theTypeDefinition.getTargetNamespace(), theTypeDefinition.getName()));
      }

      if (deep)
      {
        if (getAnonymousTypeDefinition() != null)
        {
          clonedElementDeclaration.setAnonymousTypeDefinition
            ((XSDTypeDefinition)getAnonymousTypeDefinition().cloneConcreteComponent(deep, shareDOM));
        }
        if (!getIdentityConstraintDefinitions().isEmpty())
        {
          clonedElementDeclaration.getIdentityConstraintDefinitions().addAll
            (cloneConcreteComponents(getIdentityConstraintDefinitions(), true, shareDOM));
        }
        XSDElementDeclaration theSubstitutionGroupAffiliation = getSubstitutionGroupAffiliation();
        if (theSubstitutionGroupAffiliation != null)
        {
          clonedElementDeclaration.setSubstitutionGroupAffiliation
            (createUnresolvedElementDeclaration
              (theSubstitutionGroupAffiliation.getTargetNamespace(), theSubstitutionGroupAffiliation.getName()));
        }
      }
    }

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedElementDeclaration.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedElementDeclaration.setElement(getElement());
    }

    clonedElementDeclaration.isReconciling = shareDOM;
    return clonedElementDeclaration;
  }
} 
