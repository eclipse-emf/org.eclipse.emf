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
 * $Id: XSDSimpleTypeDefinitionImpl.java,v 1.13 2005/11/23 13:56:55 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDBoundedFacet;
import org.eclipse.xsd.XSDCardinality;
import org.eclipse.xsd.XSDCardinalityFacet;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDConstrainingFacet;
import org.eclipse.xsd.XSDDerivationMethod;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDEnumerationFacet;
import org.eclipse.xsd.XSDFacet;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDFeature;
import org.eclipse.xsd.XSDFractionDigitsFacet;
import org.eclipse.xsd.XSDFundamentalFacet;
import org.eclipse.xsd.XSDLengthFacet;
import org.eclipse.xsd.XSDMaxExclusiveFacet;
import org.eclipse.xsd.XSDMaxFacet;
import org.eclipse.xsd.XSDMaxInclusiveFacet;
import org.eclipse.xsd.XSDMaxLengthFacet;
import org.eclipse.xsd.XSDMinExclusiveFacet;
import org.eclipse.xsd.XSDMinFacet;
import org.eclipse.xsd.XSDMinInclusiveFacet;
import org.eclipse.xsd.XSDMinLengthFacet;
import org.eclipse.xsd.XSDNumericFacet;
import org.eclipse.xsd.XSDOrdered;
import org.eclipse.xsd.XSDOrderedFacet;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDPatternFacet;
import org.eclipse.xsd.XSDPlugin;
import org.eclipse.xsd.XSDProhibitedSubstitutions;
import org.eclipse.xsd.XSDRedefine;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleFinal;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTotalDigitsFacet;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.XSDVariety;
import org.eclipse.xsd.XSDWhiteSpaceFacet;
import org.eclipse.xsd.impl.type.XSDAnySimpleType;
import org.eclipse.xsd.impl.type.XSDTypeRegister;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simple Type Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getVariety <em>Variety</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getLexicalFinal <em>Lexical Final</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getValidFacets <em>Valid Facets</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getContents <em>Contents</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getFacetContents <em>Facet Contents</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getFacets <em>Facets</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getMemberTypeDefinitions <em>Member Type Definitions</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getFundamentalFacets <em>Fundamental Facets</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getBaseTypeDefinition <em>Base Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getPrimitiveTypeDefinition <em>Primitive Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getItemTypeDefinition <em>Item Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getRootTypeDefinition <em>Root Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getMinFacet <em>Min Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getMaxFacet <em>Max Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getMaxInclusiveFacet <em>Max Inclusive Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getMinInclusiveFacet <em>Min Inclusive Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getMinExclusiveFacet <em>Min Exclusive Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getMaxExclusiveFacet <em>Max Exclusive Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getLengthFacet <em>Length Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getWhiteSpaceFacet <em>White Space Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getEnumerationFacets <em>Enumeration Facets</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getPatternFacets <em>Pattern Facets</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getCardinalityFacet <em>Cardinality Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getNumericFacet <em>Numeric Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getMaxLengthFacet <em>Max Length Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getMinLengthFacet <em>Min Length Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getTotalDigitsFacet <em>Total Digits Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getFractionDigitsFacet <em>Fraction Digits Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getOrderedFacet <em>Ordered Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getBoundedFacet <em>Bounded Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getEffectiveMaxFacet <em>Effective Max Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getEffectiveWhiteSpaceFacet <em>Effective White Space Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getEffectiveMaxLengthFacet <em>Effective Max Length Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getEffectiveFractionDigitsFacet <em>Effective Fraction Digits Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getEffectivePatternFacet <em>Effective Pattern Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getEffectiveEnumerationFacet <em>Effective Enumeration Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getEffectiveTotalDigitsFacet <em>Effective Total Digits Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getEffectiveMinLengthFacet <em>Effective Min Length Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getEffectiveLengthFacet <em>Effective Length Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getEffectiveMinFacet <em>Effective Min Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl#getSyntheticFacets <em>Synthetic Facets</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDSimpleTypeDefinitionImpl 
  extends XSDTypeDefinitionImpl 
  implements XSDSimpleTypeDefinition
{
  /**
   * The default value of the '{@link #getVariety() <em>Variety</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariety()
   * @generated
   * @ordered
   */
  protected static final XSDVariety VARIETY_EDEFAULT = XSDVariety.ATOMIC_LITERAL;

  /**
   * The cached value of the '{@link #getVariety() <em>Variety</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariety()
   * @generated
   * @ordered
   */
  protected XSDVariety variety = VARIETY_EDEFAULT;

  /**
   * The flag representing whether the Variety attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int VARIETY_ESETFLAG = 1 << 8;

  /**
   * The cached value of the '{@link #getFinal() <em>Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFinal()
   * @generated
   * @ordered
   */
  protected EList final_ = null;

  /**
   * The cached value of the '{@link #getLexicalFinal() <em>Lexical Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLexicalFinal()
   * @generated
   * @ordered
   */
  protected EList lexicalFinal = null;

  /**
   * The cached value of the '{@link #getValidFacets() <em>Valid Facets</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValidFacets()
   * @generated
   * @ordered
   */
  protected EList validFacets = null;

  /**
   * The cached value of the '{@link #getContents() <em>Contents</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContents()
   * @generated
   * @ordered
   */
  protected EList contents = null;

  /**
   * The cached value of the '{@link #getFacetContents() <em>Facet Contents</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFacetContents()
   * @generated
   * @ordered
   */
  protected EList facetContents = null;

  /**
   * The cached value of the '{@link #getFacets() <em>Facets</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFacets()
   * @generated
   * @ordered
   */
  protected EList facets = null;

  /**
   * The cached value of the '{@link #getMemberTypeDefinitions() <em>Member Type Definitions</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMemberTypeDefinitions()
   * @generated
   * @ordered
   */
  protected EList memberTypeDefinitions = null;

  /**
   * The cached value of the '{@link #getFundamentalFacets() <em>Fundamental Facets</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFundamentalFacets()
   * @generated
   * @ordered
   */
  protected EList fundamentalFacets = null;

  /**
   * The cached value of the '{@link #getBaseTypeDefinition() <em>Base Type Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBaseTypeDefinition()
   * @generated
   * @ordered
   */
  protected XSDSimpleTypeDefinition baseTypeDefinition = null;

  /**
   * The cached value of the '{@link #getPrimitiveTypeDefinition() <em>Primitive Type Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrimitiveTypeDefinition()
   * @generated
   * @ordered
   */
  protected XSDSimpleTypeDefinition primitiveTypeDefinition = null;

  /**
   * The cached value of the '{@link #getItemTypeDefinition() <em>Item Type Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getItemTypeDefinition()
   * @generated
   * @ordered
   */
  protected XSDSimpleTypeDefinition itemTypeDefinition = null;

  /**
   * The cached value of the '{@link #getSyntheticFacets() <em>Synthetic Facets</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSyntheticFacets()
   * @generated
   * @ordered
   */
  protected EList syntheticFacets = null;

  public static XSDSimpleTypeDefinition createSimpleTypeDefinition(Node node)
  {
    switch (XSDConstants.nodeType(node))
    {
      case XSDConstants.SIMPLETYPE_ELEMENT:
      case XSDConstants.SIMPLECONTENT_ELEMENT:
      {
        XSDSimpleTypeDefinition xsdSimpleTypeDefinition = XSDFactory.eINSTANCE.createXSDSimpleTypeDefinition();
        xsdSimpleTypeDefinition.setElement((Element)node);
        return xsdSimpleTypeDefinition;
      }
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDSimpleTypeDefinitionImpl()
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
    return XSDPackage.eINSTANCE.getXSDSimpleTypeDefinition();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDVariety getVariety()
  {
    return variety;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVariety(XSDVariety newVariety)
  {
    XSDVariety oldVariety = variety;
    variety = newVariety == null ? VARIETY_EDEFAULT : newVariety;
    boolean oldVarietyESet = (eFlags & VARIETY_ESETFLAG) != 0;
    eFlags |= VARIETY_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__VARIETY, oldVariety, variety, !oldVarietyESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetVariety()
  {
    XSDVariety oldVariety = variety;
    boolean oldVarietyESet = (eFlags & VARIETY_ESETFLAG) != 0;
    variety = VARIETY_EDEFAULT;
    eFlags &= ~VARIETY_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__VARIETY, oldVariety, VARIETY_EDEFAULT, oldVarietyESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetVariety()
  {
    return (eFlags & VARIETY_ESETFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getFinal()
  {
    if (final_ == null)
    {
      final_ = new EDataTypeUniqueEList(XSDSimpleFinal.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FINAL);
    }
    return final_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getLexicalFinal()
  {
    if (lexicalFinal == null)
    {
      lexicalFinal = new EDataTypeUniqueEList.Unsettable(XSDSimpleFinal.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__LEXICAL_FINAL);
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
    ((InternalEList.Unsettable)getLexicalFinal()).unset();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetLexicalFinal()
  {
    return lexicalFinal != null && ((InternalEList.Unsettable)lexicalFinal).isSet();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getValidFacets()
  {
    if (validFacets == null)
    {
      validFacets = new EDataTypeUniqueEList(String.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__VALID_FACETS);
    }
    return validFacets;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getContents()
  {
    if (contents == null)
    {
      contents = new EObjectContainmentEList(XSDSimpleTypeDefinition.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__CONTENTS);
    }
    return contents;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getFacetContents()
  {
    if (facetContents == null)
    {
      facetContents = new EObjectContainmentEList(XSDConstrainingFacet.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FACET_CONTENTS);
    }
    return facetContents;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getFacets()
  {
    if (facets == null)
    {
      facets = new EObjectEList(XSDConstrainingFacet.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FACETS);
    }
    return facets;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getMemberTypeDefinitions()
  {
    if (memberTypeDefinitions == null)
    {
      memberTypeDefinitions = new EObjectEList(XSDSimpleTypeDefinition.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MEMBER_TYPE_DEFINITIONS);
    }
    return memberTypeDefinitions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getFundamentalFacets()
  {
    if (fundamentalFacets == null)
    {
      fundamentalFacets = new EObjectContainmentEList(XSDFundamentalFacet.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FUNDAMENTAL_FACETS);
    }
    return fundamentalFacets;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDSimpleTypeDefinition getBaseTypeDefinition()
  {
    return baseTypeDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBaseTypeDefinition(XSDSimpleTypeDefinition newBaseTypeDefinition)
  {
    XSDSimpleTypeDefinition oldBaseTypeDefinition = baseTypeDefinition;
    baseTypeDefinition = newBaseTypeDefinition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__BASE_TYPE_DEFINITION, oldBaseTypeDefinition, baseTypeDefinition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDSimpleTypeDefinition getPrimitiveTypeDefinition()
  {
    return primitiveTypeDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPrimitiveTypeDefinition(XSDSimpleTypeDefinition newPrimitiveTypeDefinition)
  {
    XSDSimpleTypeDefinition oldPrimitiveTypeDefinition = primitiveTypeDefinition;
    primitiveTypeDefinition = newPrimitiveTypeDefinition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__PRIMITIVE_TYPE_DEFINITION, oldPrimitiveTypeDefinition, primitiveTypeDefinition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDSimpleTypeDefinition getItemTypeDefinition()
  {
    return itemTypeDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setItemTypeDefinition(XSDSimpleTypeDefinition newItemTypeDefinition)
  {
    XSDSimpleTypeDefinition oldItemTypeDefinition = itemTypeDefinition;
    itemTypeDefinition = newItemTypeDefinition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ITEM_TYPE_DEFINITION, oldItemTypeDefinition, itemTypeDefinition));
  }

  public Element createElement()
  {
    XSDComplexTypeDefinition xsdComplexTypeDefinition =
      getContainer() instanceof XSDComplexTypeDefinition ? 
        (XSDComplexTypeDefinition)getContainer() :
        null;

    Element newElement = 
      createElement
        (xsdComplexTypeDefinition != null ?
           XSDConstants.SIMPLECONTENT_ELEMENT : 
           XSDConstants.SIMPLETYPE_ELEMENT);
    setElement(newElement);

    Element childElement = createChildElement();
    newElement.appendChild(childElement);
    return newElement;
  }

  protected Element createChildElement()
  {
    return createElement(getRequiredChildElementNodeType());
  }

  protected int getRequiredChildElementNodeType()
  {
    XSDComplexTypeDefinition xsdComplexTypeDefinition =
      getContainer() instanceof XSDComplexTypeDefinition ? 
        (XSDComplexTypeDefinition)getContainer() :
        null;

    XSDSimpleTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();
    if (getItemTypeDefinition() != null && (theBaseTypeDefinition == null || XSDConstants.isURType(theBaseTypeDefinition)))
    {
      return XSDConstants.LIST_ELEMENT;
    }
    else if (!getMemberTypeDefinitions().isEmpty() && (theBaseTypeDefinition == null || XSDConstants.isURType(theBaseTypeDefinition)))
    {
      return XSDConstants.UNION_ELEMENT;
    }
    else
    {
      return
        xsdComplexTypeDefinition == null || XSDDerivationMethod.EXTENSION_LITERAL != xsdComplexTypeDefinition.getDerivationMethod() ? 
          XSDConstants.RESTRICTION_ELEMENT :
          XSDConstants.EXTENSION_ELEMENT;
    }
  }

  protected void patch()
  {
    super.patch();

    if (!(getContainer() instanceof XSDComplexTypeDefinition))
    {
      XSDSimpleTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();
      if (theBaseTypeDefinition != null && (forceResolve || theBaseTypeDefinition.getContainer() == null))
      {
        theBaseTypeDefinition = resolveSimpleTypeDefinition(theBaseTypeDefinition.getTargetNamespace(), theBaseTypeDefinition.getName());
      }
      theBaseTypeDefinition = handleNewBaseTypeDefinition(theBaseTypeDefinition);
      if (theBaseTypeDefinition == null || !theBaseTypeDefinition.isSetVariety() || theBaseTypeDefinition.getVariety() != getVariety())
      {
        XSDSimpleTypeDefinition theItemTypeDefinition = getItemTypeDefinition();
        if (theItemTypeDefinition != null)
        {
          if (forceResolve || theItemTypeDefinition.getContainer() == null)
          {
            XSDSimpleTypeDefinition newItemTypeDefinition = 
              resolveSimpleTypeDefinition(theItemTypeDefinition.getTargetNamespace(), theItemTypeDefinition.getName());
            if (newItemTypeDefinition.getContainer() != null && newItemTypeDefinition != theItemTypeDefinition)
            {
              setItemTypeDefinition(newItemTypeDefinition);
            }
          }
        }
        else
        {
          for (ListIterator theMemberTypeDefinitions = getMemberTypeDefinitions().listIterator(); theMemberTypeDefinitions.hasNext(); )
          {
            XSDSimpleTypeDefinition theMemberTypeDefinition = (XSDSimpleTypeDefinition)theMemberTypeDefinitions.next();
            if (forceResolve || theMemberTypeDefinition.getContainer() == null)
            {
              XSDSimpleTypeDefinition newMemberTypeDefinition = 
                resolveSimpleTypeDefinition(theMemberTypeDefinition.getTargetNamespace(), theMemberTypeDefinition.getName());
              if (newMemberTypeDefinition.getContainer() != null && newMemberTypeDefinition != theMemberTypeDefinition)
              {
                if (getMemberTypeDefinitions().contains(newMemberTypeDefinition))
                {
                  theMemberTypeDefinitions.remove();
                }
                else
                {
                  theMemberTypeDefinitions.set(newMemberTypeDefinition);
                }
              }
            }
          }
        }
      }
    }

    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      List newFinal = new ArrayList();
      if (!isSetLexicalFinal())
      {
        for (Iterator values = xsdSchema.getFinalDefault().iterator(); values.hasNext(); )
        {
          XSDProhibitedSubstitutions value = (XSDProhibitedSubstitutions)values.next();
          switch (value.getValue())
          {
            case XSDProhibitedSubstitutions.ALL:
            {
              newFinal.add(XSDSimpleFinal.RESTRICTION_LITERAL);
              newFinal.add(XSDSimpleFinal.LIST_LITERAL);
              newFinal.add(XSDSimpleFinal.UNION_LITERAL);
              break;
            }
            case XSDProhibitedSubstitutions.RESTRICTION:
            {
              newFinal.add(XSDSimpleFinal.RESTRICTION_LITERAL);
              break;
            }
          }
        }
      }
      else
      {
        for (Iterator values = getLexicalFinal().iterator(); values.hasNext(); )
        {
          XSDSimpleFinal value = (XSDSimpleFinal)values.next();
          switch (value.getValue())
          {
            case XSDSimpleFinal.ALL:
            {
              newFinal.add(XSDSimpleFinal.RESTRICTION_LITERAL);
              newFinal.add(XSDSimpleFinal.LIST_LITERAL);
              newFinal.add(XSDSimpleFinal.UNION_LITERAL);
              break;
            }
            default:
            {
              newFinal.add(value);
              break;
            }
          }
        }
      }

      Collection oldFinal = new ArrayList(getFinal());
      oldFinal.removeAll(newFinal);
      if (!oldFinal.isEmpty())
      {
        getFinal().removeAll(oldFinal);
      }
      setListContentAndOrder(getFinal(), newFinal);
    }
  }

  protected XSDWhiteSpaceFacet effectiveWhiteSpaceFacet;
  protected void handleAnalysis()
  {
    XSDSimpleTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();

    XSDConcreteComponent container = getContainer();
    if (container instanceof XSDComplexTypeDefinition)
    {
      if (!getContents().contains(theBaseTypeDefinition))
      {
        XSDComplexTypeDefinition xsdComplexTypeDefinition = (XSDComplexTypeDefinitionImpl)container;
        XSDTypeDefinition complexBaseTypeDefinition = xsdComplexTypeDefinition.getBaseTypeDefinition();
        if (complexBaseTypeDefinition != null)
        {
          ((XSDConcreteComponentImpl)complexBaseTypeDefinition).analyze();
          theBaseTypeDefinition = handleNewBaseTypeDefinition(complexBaseTypeDefinition.getSimpleType());
        }
      }
    }

    if (getFundamentalFacets().isEmpty())
    {
      createFundamentalFacets();
    }

    List newFacets = null;
    boolean newBounded = false;
    XSDCardinality newCardinality = XSDCardinality.FINITE_LITERAL;
    boolean newNumeric = false;
    XSDOrdered newOrdered = XSDOrdered.FALSE_LITERAL;
    XSDSimpleTypeDefinition newPrimitiveTypeDefinition = null;
    XSDVariety newVariety = null;
    EList newValidFacets = null;

    if (theBaseTypeDefinition != null && theBaseTypeDefinition.getContainer() != null)
    {
      ((XSDTypeDefinitionImpl)theBaseTypeDefinition).analyze();
      if (theBaseTypeDefinition != this)
      {
        if (!XSDConstants.isURType(this))
        {
          newVariety = XSDVariety.ATOMIC_LITERAL;
          newPrimitiveTypeDefinition = this;
        }

        if (!XSDConstants.isURType(theBaseTypeDefinition))
        {
          newVariety = theBaseTypeDefinition.getVariety();
          newPrimitiveTypeDefinition = theBaseTypeDefinition.getPrimitiveTypeDefinition();
          newBounded = theBaseTypeDefinition.getBoundedFacet().isValue();
          newCardinality = theBaseTypeDefinition.getCardinalityFacet().getValue();
          newNumeric = theBaseTypeDefinition.getNumericFacet().isValue();
          newOrdered = theBaseTypeDefinition.getOrderedFacet().getValue();
          newValidFacets = theBaseTypeDefinition.getValidFacets();

          if (XSDVariety.LIST_LITERAL == theBaseTypeDefinition.getVariety())
          {
            XSDSimpleTypeDefinition newItemTypeDefinition = theBaseTypeDefinition.getItemTypeDefinition();
            if (newItemTypeDefinition != getItemTypeDefinition())
            {
              setItemTypeDefinition(newItemTypeDefinition);
            }
          }
          else if (XSDVariety.UNION_LITERAL == theBaseTypeDefinition.getVariety())
          {
            List newMemberTypeDefinitions = theBaseTypeDefinition.getMemberTypeDefinitions();
            List remainingMemberTypeDefinitions = new ArrayList(getMemberTypeDefinitions());
            remainingMemberTypeDefinitions.removeAll(newMemberTypeDefinitions);
            if (!remainingMemberTypeDefinitions.isEmpty())
            {
              getMemberTypeDefinitions().removeAll(remainingMemberTypeDefinitions);
            }
            if (!newMemberTypeDefinitions.isEmpty())
            {
              setListContentAndOrder(getMemberTypeDefinitions(), newMemberTypeDefinitions);
            }
          }
        }
        else
        {
          newFacets = new ArrayList(getFacetContents());
        }
      }
      else
      {
        newFacets = new ArrayList(getFacetContents());
      }
    }
    else
    {
      newFacets = new ArrayList(getFacetContents());
    }

    if (theBaseTypeDefinition == null || XSDConstants.isURType(theBaseTypeDefinition))
    {
      XSDSimpleTypeDefinition theItemTypeDefinition = getItemTypeDefinition();
      if (theItemTypeDefinition != null && theItemTypeDefinition.getContainer() != null)
      {
        newVariety = XSDVariety.LIST_LITERAL;
        newPrimitiveTypeDefinition = null;
        ((XSDTypeDefinitionImpl)theItemTypeDefinition).analyze();
        newValidFacets = getValidFacetsForList();
        if (effectiveWhiteSpaceFacet == null)
        {
          effectiveWhiteSpaceFacet = getXSDFactory().createXSDWhiteSpaceFacet();
          effectiveWhiteSpaceFacet.setLexicalValue("collapse");
          effectiveWhiteSpaceFacet.setFixed(true);
          getSyntheticFacets().add(effectiveWhiteSpaceFacet);
        }
        if (newFacets != null)
        {
          newFacets.add(effectiveWhiteSpaceFacet);
        }
      }
      else if (!getMemberTypeDefinitions().isEmpty())
      {
        newVariety = XSDVariety.UNION_LITERAL;
        newPrimitiveTypeDefinition = null;
        newNumeric = true;
        for (ListIterator theMemberTypeDefinitions = getMemberTypeDefinitions().listIterator(); theMemberTypeDefinitions.hasNext(); )
        {
          XSDSimpleTypeDefinition theMemberTypeDefinition = (XSDSimpleTypeDefinition)theMemberTypeDefinitions.next();
          if (theMemberTypeDefinition.getContainer() != null)
          {
            ((XSDTypeDefinitionImpl)theMemberTypeDefinition).analyze();
            if (!theMemberTypeDefinition.getNumericFacet().isValue())
            {
              newNumeric = false;
            }
          }
        }

        XSDTypeDefinition lowestCommonAncestor = getLowestCommonAncestor(getMemberTypeDefinitions());
        if (lowestCommonAncestor instanceof XSDSimpleTypeDefinition && lowestCommonAncestor.getContainer() != null)
        {
          newOrdered = ((XSDSimpleTypeDefinition)lowestCommonAncestor).getOrderedFacet().getValue();
        }
        newValidFacets = getValidFacetsForUnion();
      }
      else if (getElement() != null)
      {
        LOOP:
        for (Node child = getElement().getFirstChild(); child != null; child = child.getNextSibling())
        {
          switch (XSDConstants.nodeType(child))
          {
            case XSDConstants.UNION_ELEMENT:
            {
              newVariety = XSDVariety.UNION_LITERAL;
              newPrimitiveTypeDefinition = null;
              newValidFacets = getValidFacetsForUnion();
              break LOOP;
            }
            case XSDConstants.LIST_ELEMENT:
            {
              newVariety = XSDVariety.LIST_LITERAL;
              newPrimitiveTypeDefinition = null;
              newValidFacets = getValidFacetsForList();
              break LOOP;
            }
          }
        }
      }
    }

    if (newVariety != null)
    {
      if (!isSetVariety() || newVariety != getVariety())
      {
        setVariety(newVariety);
      }
    }
    else if (isSetVariety())
    {
      unsetVariety();
    }

    if (newPrimitiveTypeDefinition != getPrimitiveTypeDefinition())
    {
      setPrimitiveTypeDefinition(newPrimitiveTypeDefinition);
    }

    // This ensures the the facets are analyzed when the containing type is relatively well-analyzed.
    //
    super.handleAnalysis();

    if (newFacets == null)
    {
      newFacets = mergeFacets();
    }

    boolean firstHFP = true;
    XSDAnnotation theAnnotation = getAnnotation();
    if (theAnnotation != null)
    {
      for (Iterator applicationInformationElements = theAnnotation.getApplicationInformation().iterator(); 
           applicationInformationElements.hasNext(); )
      {
        Element applicationInformationElement = (Element)applicationInformationElements.next();

        for (Node child = applicationInformationElement.getFirstChild(); child != null; child = child.getNextSibling())
        {
          switch (XSDConstants.hfpNodeType(child))
          {
            case XSDConstants.HFP_HASFACET_ELEMENT:
            {
              Element childElement = (Element)child;
              String facetName = childElement.getAttributeNS(null, XSDConstants.HFP_NAME_ATTRIBUTE);
              if (firstHFP )
              {
                firstHFP = false;
                newValidFacets = new BasicEList();
              }

              newValidFacets.add(facetName);

              break;
            }
            case XSDConstants.HFP_HASPROPERTY_ELEMENT:
            {
              Element childElement = (Element)child;
              String propertyName = childElement.getAttributeNS(null, XSDConstants.HFP_NAME_ATTRIBUTE);
              String propertyValue = childElement.getAttributeNS(null, XSDConstants.HFP_VALUE_ATTRIBUTE);
              if ("bounded".equals(propertyName))
              {
                newBounded = "true".equals(propertyValue);
              }
              else if ("cardinality".equals(propertyName))
              {
                newCardinality = 
                  "countably infinite".equals(propertyValue) ? 
                    XSDCardinality.COUNTABLY_INFINITE_LITERAL : 
                    XSDCardinality.get(propertyValue);
              }
              else if ("numeric".equals(propertyName))
              {
                newNumeric = "true".equals(propertyValue);
              }
              else if ("ordered".equals(propertyName))
              {
                newOrdered = XSDOrdered.get(propertyValue);
              }
              break;
            }
          }
        }
      }
    }


    if (!XSDConstants.isURType(this))
    {
      if (newBounded != getBoundedFacet().isValue() /* || !getBoundedFacet().isSetValue()*/)
      {
        getBoundedFacet().setValue(newBounded);
      }
      if (newCardinality != getCardinalityFacet().getValue() /* || !getCardinalityFacet().isSetValue()*/)
      {
        getCardinalityFacet().setValue(newCardinality);
      }
      if (newNumeric != getNumericFacet().isValue() /* || !getNumericFacet().isSetValue()*/)
      {
        getNumericFacet().setValue(newNumeric);
      }
      if (newOrdered == null)
      {
        newOrdered = XSDOrdered.FALSE_LITERAL;
      }
      if (newOrdered != getOrderedFacet().getValue() /* || !getOrderedFacet().isSetValue()*/)
      {
        getOrderedFacet().setValue(newOrdered);
      }
    }

    if (newFacets == null)
    {
      if (!getFacets().isEmpty())
      {
        getFacets().clear();
      }
    }
    else
    {
      List remainingFacets = new ArrayList(getFacets());
      remainingFacets.removeAll(newFacets);
      getFacets().removeAll(remainingFacets);
      if (!newFacets.isEmpty())
      {
        setListContentAndOrder(getFacets(), newFacets);
      }
    }

    if (newValidFacets == null)
    {
      if (!getValidFacets().isEmpty())
      {
        getValidFacets().clear();
      }
    }
    else
    {
      List remainingValidFacets = new ArrayList(getValidFacets());
      remainingValidFacets.removeAll(newValidFacets);
      getValidFacets().removeAll(remainingValidFacets);
      if (!newValidFacets.isEmpty())
      {
        setListContentAndOrder(getValidFacets(), newValidFacets);
      }
    }
  }

  public void validate()
  {
    super.validate();

    Element theElement = getElement();
    Element theRestrictionElement = null;
    if (theElement != null)
    {
      String anchor;
      if (getContainer() instanceof XSDComplexTypeDefinition)
      {
        checkAttributes
          (XSDConstants.PART2,
           anchor = "element-simpleContent",
           theElement,
           new String []
           {
             XSDConstants.ID_ATTRIBUTE
           });

        XSDComplexTypeDefinition xsdComplexTypeDefinition =
           (XSDComplexTypeDefinition)getSchema().getSchemaForSchema().resolveElementDeclaration("simpleContent").getTypeDefinition();

        checkComplexContent(xsdComplexTypeDefinition, XSDConstants.PART2, "element-simpleContent", theElement);
        for (Node child = theElement.getFirstChild(); child != null; child = child.getNextSibling())
        {
          switch (XSDConstants.nodeType(child))
          {
            case XSDConstants.RESTRICTION_ELEMENT:
            {
              Element childElement = (Element)child;
              theRestrictionElement = childElement;
  
              checkAttributes
                (XSDConstants.PART2,
                 "element-simpleContent::restriction",
                 childElement,
                 new String []
                 {
                   XSDConstants.ID_ATTRIBUTE,
                   XSDConstants.BASE_ATTRIBUTE
                 });

              checkBuiltInTypeConstraint
                ("ID",
                 null,
                 XSDConstants.PART2,
                 "element-simpleContent::restriction",
                 childElement,
                 XSDConstants.ID_ATTRIBUTE,
                 false);

              checkComplexContent("simpleRestrictionType", XSDConstants.PART2, "element-simpleContent::restriction", childElement);

              if (childElement.hasAttributeNS(null, XSDConstants.BASE_ATTRIBUTE) && !getContents().isEmpty())
              {
                createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-restriction-base-or-simpleType");
              }

              break;
            }
            case XSDConstants.EXTENSION_ELEMENT:
            {
              Element childElement = (Element)child;
  
              checkAttributes
                (XSDConstants.PART2,
                 "element-simpleContent::extension",
                 childElement,
                 new String []
                 {
                   XSDConstants.ID_ATTRIBUTE,
                   XSDConstants.BASE_ATTRIBUTE
                 });

              checkBuiltInTypeConstraint
                ("ID",
                 null,
                 XSDConstants.PART2,
                 "element-simpleContent::extension",
                 childElement,
                 XSDConstants.ID_ATTRIBUTE,
                 false);

              checkComplexContent("simpleExtensionType", XSDConstants.PART2, "element-simpleContent::extension", childElement);

              if (childElement.hasAttributeNS(null, XSDConstants.BASE_ATTRIBUTE) && !getContents().isEmpty())
              {
                createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-restriction-base-or-simpleType");
              }

              break;
            }
          }
        }
      }
      else
      {
        if (getContainer() instanceof XSDFeature || getContainer() instanceof XSDSimpleTypeDefinition)
        {
          checkAttributes
            (XSDConstants.PART2,
             anchor = "element-simpleType",
             theElement,
             new String []
             {
               XSDConstants.ID_ATTRIBUTE,
             });
        }
        else
        {
          checkAttributes
            (XSDConstants.PART2,
             anchor = "element-simpleType",
             theElement,
             new String []
             {
               XSDConstants.FINAL_ATTRIBUTE,
               XSDConstants.ID_ATTRIBUTE,
               XSDConstants.NAME_ATTRIBUTE
             });

          // EATM this is an error in the spec, I believe.
          //
          if (theElement.hasAttributeNS(null, XSDConstants.FINAL_ATTRIBUTE))
          {
            String value = theElement.getAttributeNS(null, XSDConstants.FINAL_ATTRIBUTE);
            for (StringTokenizer values = new StringTokenizer(value, " "); values.hasMoreTokens(); )
            {
              String token = values.nextToken();

              checkBuiltInTypeConstraint
                ("simpleDerivationSet",
                 token,
                 XSDConstants.PART2,
                 anchor,
                 theElement,
                 XSDConstants.FINAL_ATTRIBUTE,
                 false);
            }
          }
        }

        checkComplexContent("simpleType", XSDConstants.PART2, "element-simpleType", theElement);

        for (Node child = theElement.getFirstChild(); child != null; child = child.getNextSibling())
        {
          switch (XSDConstants.nodeType(child))
          {
            case XSDConstants.RESTRICTION_ELEMENT:
            {
              Element childElement = (Element)child;
              theRestrictionElement = childElement;
  
              checkAttributes
                (XSDConstants.PART2,
                 "element-restriction",
                 childElement,
                 new String []
                 {
                   XSDConstants.ID_ATTRIBUTE,
                   XSDConstants.BASE_ATTRIBUTE
                 });

              checkBuiltInTypeConstraint
                ("ID",
                 null,
                 XSDConstants.PART2,
                 "element-restriction",
                 childElement,
                 XSDConstants.ID_ATTRIBUTE,
                 false);

              XSDComplexTypeDefinition xsdComplexTypeDefinition =
                 (XSDComplexTypeDefinition)getSchema().getSchemaForSchema().resolveElementDeclaration("restriction").getTypeDefinition();
      
              checkComplexContent(xsdComplexTypeDefinition, XSDConstants.PART2, "element-restriction", childElement);

              if (childElement.hasAttributeNS(null, XSDConstants.BASE_ATTRIBUTE) && !getContents().isEmpty())
              {
                createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-restriction-base-or-simpleType");
              }

              break;
            }
            case XSDConstants.UNION_ELEMENT:
            {
              Element childElement = (Element)child;
  
              checkAttributes
                (XSDConstants.PART2,
                 "element-union",
                 childElement,
                 new String []
                 {
                   XSDConstants.ID_ATTRIBUTE,
                   XSDConstants.MEMBERTYPES_ATTRIBUTE
                 });

              checkBuiltInTypeConstraint
                ("ID",
                 null,
                 XSDConstants.PART2,
                 "element-union",
                 childElement,
                 XSDConstants.ID_ATTRIBUTE,
                 false);

              XSDComplexTypeDefinition xsdComplexTypeDefinition =
                 (XSDComplexTypeDefinition)getSchema().getSchemaForSchema().resolveElementDeclaration("union").getTypeDefinition();
      
              checkComplexContent(xsdComplexTypeDefinition, XSDConstants.PART2, "element-union", childElement);

              if (getMemberTypeDefinitions().isEmpty())
              {
                createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-union-memberTypes-or-simpleTypes");
              }

              break;
            }
            case XSDConstants.LIST_ELEMENT:
            {
              Element childElement = (Element)child;
  
              checkAttributes
                (XSDConstants.PART2,
                 "element-list",
                 childElement,
                 new String []
                 {
                   XSDConstants.ID_ATTRIBUTE,
                   XSDConstants.ITEMTYPE_ATTRIBUTE
                 });

              checkBuiltInTypeConstraint
                ("ID",
                 null,
                 XSDConstants.PART2,
                 "element-list",
                 childElement,
                 XSDConstants.ID_ATTRIBUTE,
                 false);

              XSDComplexTypeDefinition xsdComplexTypeDefinition =
                 (XSDComplexTypeDefinition)getSchema().getSchemaForSchema().resolveElementDeclaration("list").getTypeDefinition();
      
              checkComplexContent(xsdComplexTypeDefinition, XSDConstants.PART2, "element-list", childElement);

              if (childElement.hasAttributeNS(null, XSDConstants.ITEMTYPE_ATTRIBUTE) && !getContents().isEmpty())
              {
                createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-list-itemType-or-simpleType");
              }

              break;
            }
          }
        }
      }

      checkBuiltInTypeConstraint
        ("ID",
         null,
         XSDConstants.PART2,
         anchor,
         theElement,
         XSDConstants.ID_ATTRIBUTE,
         false);
    }

    if (!(getContainer() instanceof XSDFeature) && !(getContainer() instanceof XSDTypeDefinition))
    {
      checkBuiltInTypeConstraint
        ("NCName",
         getName(),
         XSDConstants.PART2,
         "element-simpleType",
         theElement,
         XSDConstants.NAME_ATTRIBUTE,
         true);
    }

    XSDSimpleTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();
    if (theBaseTypeDefinition == null)
    {
      createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "_UI_UnresolvedTypeDefinition_message", "");
    }
    else if (theBaseTypeDefinition.getContainer() == null)
    {
      createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "_UI_UnresolvedTypeDefinition_message", theBaseTypeDefinition.getURI());
    }
    else if (isSetVariety())
    {
      switch (getVariety().getValue())
      {
        case XSDVariety.ATOMIC:
        {
          if (theBaseTypeDefinition.getFinal().contains(XSDSimpleFinal.RESTRICTION_LITERAL))
          {
            createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cos-st-restricts.1.2", theBaseTypeDefinition.getURI());
          }
          if (isCircular())
          {
            reportConstraintViolation
              (XSDConstants.PART2,
               "st-props-correct.2",
               getElement(),
               XSDConstants.BASE_ATTRIBUTE,
               new Object [] {});
          }
          if (!XSDConstants.isSchemaForSchemaNamespace(getTargetNamespace()) &&
                XSDConstants.isAnySimpleType(theBaseTypeDefinition) &&
                (theElement == null || 
                   theRestrictionElement != null && theRestrictionElement.hasAttributeNS(null, XSDConstants.BASE_ATTRIBUTE)))
          {
            createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cos-st-retricts.0.0", theBaseTypeDefinition.getURI());
          }
          break;
        }
        case XSDVariety.LIST:
        {
          if (!XSDConstants.isURType(theBaseTypeDefinition))
          {
            if (theBaseTypeDefinition.getFinal().contains(XSDSimpleFinal.RESTRICTION_LITERAL))
            {
              createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cos-st-restricts.1.2", theBaseTypeDefinition.getURI());
            }
          }
          else
          {
            XSDSimpleTypeDefinition theItemTypeDefinition = getItemTypeDefinition();
            if (theItemTypeDefinition == null)
            {
              createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "_UI_UnresolvedTypeDefinition_message", "");
            }
            else if (theItemTypeDefinition.getContainer() == null)
            {
              createDiagnostic
                (XSDDiagnosticSeverity.ERROR_LITERAL, "_UI_UnresolvedTypeDefinition_message", theItemTypeDefinition.getURI());
            }
            else if (XSDVariety.ATOMIC_LITERAL != theItemTypeDefinition.getVariety() && 
                       XSDVariety.UNION_LITERAL != theItemTypeDefinition.getVariety())
            {
              reportConstraintViolation
                (XSDConstants.PART2,
                 "cos-list-of-atomic",
                 getElement(),
                 XSDConstants.ITEMTYPE_ATTRIBUTE,
                 new Object [] {});
            }
            else if (theItemTypeDefinition.getFinal().contains(XSDSimpleFinal.LIST_LITERAL))
            {
              createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cos-st-restricts.2.0", theBaseTypeDefinition.getURI());
            }
            else if (!XSDConstants.isSchemaForSchemaNamespace(getTargetNamespace()) &&
                      XSDConstants.isAnySimpleType(theItemTypeDefinition))
            {
              createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cos-st-retricts.0.1", theItemTypeDefinition.getURI());
            }
          }
          if (isCircular())
          {
            reportConstraintViolation
              (XSDConstants.PART2,
               "st-props-correct.2",
               getElement(),
               XSDConstants.ITEMTYPE_ATTRIBUTE,
               new Object [] {});
          }
          break;
        }
        case XSDVariety.UNION:
        {
          if (!XSDConstants.isURType(theBaseTypeDefinition))
          {
            if (theBaseTypeDefinition.getFinal().contains(XSDSimpleFinal.RESTRICTION_LITERAL))
            {
              createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cos-st-restricts.1.2", theBaseTypeDefinition.getURI());
            }
          }
          else
          {
            for (Iterator memberTypeDefinitions = getMemberTypeDefinitions().iterator(); memberTypeDefinitions.hasNext(); )
            {
              XSDSimpleTypeDefinition theMemberTypeDefinition = (XSDSimpleTypeDefinition)memberTypeDefinitions.next();
              if (theMemberTypeDefinition == null)
              {
                createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "_UI_UnresolvedTypeDefinition_message", "");
              }
              else if (theMemberTypeDefinition.getContainer() == null)
              {
                createDiagnostic
                  (XSDDiagnosticSeverity.ERROR_LITERAL, "_UI_UnresolvedTypeDefinition_message", theMemberTypeDefinition.getURI());
              }
              else if (theMemberTypeDefinition.getFinal().contains(XSDSimpleFinal.UNION_LITERAL))
              {
                createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cos-st-restricts.3.0", theBaseTypeDefinition.getURI());
              }
              else if (!XSDConstants.isSchemaForSchemaNamespace(getTargetNamespace()) &&
                        XSDConstants.isAnySimpleType(theMemberTypeDefinition))
              {
                createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cos-st-retricts.0.2", theMemberTypeDefinition.getURI());
              }
            }
          }
          if (isCircular())
          {
            reportConstraintViolation
              (XSDConstants.PART2,
               "cos-no-circular-unions",
               getElement(),
               XSDConstants.MEMBERTYPES_ATTRIBUTE,
               new Object [] {});
          }
          break;
        }
      }
    }
    else if (isCircular())
    {
      reportConstraintViolation
        (XSDConstants.PART2,
         "st-props-correct.2",
         getElement(),
         XSDConstants.BASE_ATTRIBUTE,
         new Object [] {});
    }
  }

  protected void reconcileAttributes(Element changedElement)
  {
    switch (XSDConstants.nodeType(changedElement))
    {
      case XSDConstants.EXTENSION_ELEMENT:
      case XSDConstants.RESTRICTION_ELEMENT:
      {
        if (!(getContainer() instanceof XSDComplexTypeDefinition))
        {
          XSDSimpleTypeDefinition newBaseTypeDefinition = null;
          if (changedElement.hasAttributeNS(null, XSDConstants.BASE_ATTRIBUTE))
          {
            newBaseTypeDefinition =
              resolveSimpleTypeDefinitionURI(XSDConstants.lookupQNameForAttribute(changedElement, XSDConstants.BASE_ATTRIBUTE));
          }
          else if (getContents().contains(getBaseTypeDefinition()))
          {
            newBaseTypeDefinition = getBaseTypeDefinition();
          }

          handleNewBaseTypeDefinition(newBaseTypeDefinition);
        }
        else
        {
          XSDTypeDefinition newBaseTypeDefinition = null;
          if (changedElement.hasAttributeNS(null, XSDConstants.BASE_ATTRIBUTE))
          {
            newBaseTypeDefinition =
              resolveTypeDefinitionURI(XSDConstants.lookupQNameForAttribute(changedElement, XSDConstants.BASE_ATTRIBUTE));
          }
          else if (getContents().contains(getBaseTypeDefinition()))
          {
            newBaseTypeDefinition = getBaseTypeDefinition();
          }

          handleNewComplexBaseTypeDefinition(newBaseTypeDefinition);
        }

        break;
      }
      case XSDConstants.LIST_ELEMENT:
      {
        XSDSimpleTypeDefinition newItemTypeDefinition = getItemTypeDefinition();
        if (changedElement.hasAttributeNS(null, XSDConstants.ITEMTYPE_ATTRIBUTE))
        {
          newItemTypeDefinition =
            resolveSimpleTypeDefinitionURI(XSDConstants.lookupQNameForAttribute(changedElement, XSDConstants.ITEMTYPE_ATTRIBUTE));
        }
        else if (!getContents().contains(newItemTypeDefinition))
        {
          newItemTypeDefinition = resolveSimpleTypeDefinition(null, "undefined");
        }

        if (newItemTypeDefinition != getItemTypeDefinition())
        {
          setItemTypeDefinition(newItemTypeDefinition);
        }

        handleNewBaseTypeDefinition(null);
        break;
      }
      case XSDConstants.UNION_ELEMENT:
      {
        List newMemberTypeDefinitions = new ArrayList();
        if (changedElement.hasAttributeNS(null, XSDConstants.MEMBERTYPES_ATTRIBUTE))
        {
          String memberTypes = changedElement.getAttributeNS(null, XSDConstants.MEMBERTYPES_ATTRIBUTE);
          if (memberTypes != null)
          {
            for (StringTokenizer tokens = new StringTokenizer(memberTypes, " "); tokens.hasMoreTokens(); )
            {
              String memberTypeQName = tokens.nextToken();
              XSDSimpleTypeDefinition newMemberTypeDefinition =
                resolveSimpleTypeDefinitionURI(XSDConstants.lookupQName(changedElement, memberTypeQName));
              newMemberTypeDefinitions.add(newMemberTypeDefinition);
            }
          }
        }

        newMemberTypeDefinitions.addAll(getContents());

        List remainingMemberTypeDefinitions = new ArrayList(getMemberTypeDefinitions());
        remainingMemberTypeDefinitions.removeAll(newMemberTypeDefinitions);
        getMemberTypeDefinitions().removeAll(remainingMemberTypeDefinitions);
        if (!newMemberTypeDefinitions.isEmpty())
        {
          setListContentAndOrder(getMemberTypeDefinitions(), newMemberTypeDefinitions);
        }

        handleNewBaseTypeDefinition(null);
        break;
      }
      case XSDConstants.SIMPLETYPE_ELEMENT:
      case XSDConstants.SIMPLECONTENT_ELEMENT:
      {
        super.reconcileAttributes(changedElement);

        if (changedElement.hasAttributeNS(null, XSDConstants.FINAL_ATTRIBUTE))
        {
          setStringLexicalFinal(changedElement.getAttributeNS(null, XSDConstants.FINAL_ATTRIBUTE));
        }
        else if (isSetLexicalFinal())
        {
          unsetLexicalFinal();
        }

        for (Node child = changedElement.getFirstChild(); child != null; child = child.getNextSibling())
        {
          if (child.getNodeType() == Node.ELEMENT_NODE)
          {
            reconcileAttributes((Element)child);
          }
        }
        break;
      }
    }
  }

  protected void handleNewComplexBaseTypeDefinition(XSDTypeDefinition newComplexBaseTypeDefinition)
  {
    if (getContainer() instanceof XSDComplexTypeDefinition)
    {
      ((XSDComplexTypeDefinitionImpl)getContainer()).handleNewBaseTypeDefinition(newComplexBaseTypeDefinition);
    }
  }

  protected XSDSimpleTypeDefinition handleNewBaseTypeDefinition(XSDSimpleTypeDefinition newBaseTypeDefinition)
  {
    if (newBaseTypeDefinition == null)
    {
      XSDSchema xsdSchema = getSchema();
      if (xsdSchema == null)
      {
        Element theElement = getElement();
        if (theElement != null)
        {
          newBaseTypeDefinition = 
            XSDSchemaImpl.getSchemaForSchema(theElement.getNamespaceURI()).resolveSimpleTypeDefinition("anySimpleType");
        }
        else
        {
          newBaseTypeDefinition = this;
        }
      }
      else
      {
        newBaseTypeDefinition =  xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("anySimpleType");
      }
    }

    if (newBaseTypeDefinition == this && getContainer() instanceof XSDRedefine)
    {
      XSDSchema redefinedSchema = ((XSDRedefine)getContainer()).getIncorporatedSchema();
      if (redefinedSchema != null)
      {
        XSDSimpleTypeDefinition redefinedTypeDefinition = 
          (XSDSimpleTypeDefinition)((XSDSchemaImpl)redefinedSchema).getRedefinitionMap().get(this);
        if (redefinedTypeDefinition != null)
        {
          newBaseTypeDefinition = redefinedTypeDefinition;
        }
      }
    }

    if (newBaseTypeDefinition != getBaseTypeDefinition())
    {
      setBaseTypeDefinition(newBaseTypeDefinition);
    }

    return newBaseTypeDefinition;
  }

  protected Node getAdoptionParentNode(EReference eReference)
  {
    if (eReference == XSDPackage.eINSTANCE.getXSDSimpleTypeDefinition_FacetContents() ||
          eReference == XSDPackage.eINSTANCE.getXSDSimpleTypeDefinition_Contents() ||
          eReference == XSDPackage.eINSTANCE.getXSDTypeDefinition_DerivationAnnotation()) 
    {
      for (Node child = getElement().getFirstChild(); child != null; child = child.getNextSibling())
      {
        switch (XSDConstants.nodeType(child))
        {
          case XSDConstants.EXTENSION_ELEMENT:
          case XSDConstants.RESTRICTION_ELEMENT:
          case XSDConstants.LIST_ELEMENT:
          case XSDConstants.UNION_ELEMENT:
          {
            return child;
          }
        }
      }
    }

    return super.getAdoptionParentNode(eReference);
  }

  protected Collection getContentNodes(Element changedElement)
  {
    Collection result = new ArrayList();
    for (Node child = getElement().getFirstChild(); child != null; child = child.getNextSibling())
    {
      switch (XSDConstants.nodeType(child))
      {
        case XSDConstants.ANNOTATION_ELEMENT:
        {
          result.add(child);
          break;
        }
        case XSDConstants.EXTENSION_ELEMENT:
        case XSDConstants.RESTRICTION_ELEMENT:
        case XSDConstants.LIST_ELEMENT:
        case XSDConstants.UNION_ELEMENT:
        {
          for (Node grandChild = child.getFirstChild(); grandChild != null; grandChild = grandChild.getNextSibling())
          {
            if (grandChild.getNodeType() == Node.ELEMENT_NODE)
            {
              result.add(grandChild);
            }
          }
          break;
        }
      }
    }
    return result;
  }

  protected void handleUnreconciledElement(Element child, List newContents, List remainingContents)
  {
    switch (XSDConstants.nodeType(child))
    {
      case XSDConstants.ANNOTATION_ELEMENT:
      {
        XSDAnnotation xsdAnnotation = XSDAnnotationImpl.createAnnotation(child);
        newContents.add(xsdAnnotation);
        break;
      }
      case XSDConstants.SIMPLETYPE_ELEMENT:
      {
        XSDSimpleTypeDefinition xsdSimpleTypeDefinition = XSDSimpleTypeDefinitionImpl.createSimpleTypeDefinition(child);
        if (xsdSimpleTypeDefinition != null)
        {
          newContents.add(xsdSimpleTypeDefinition);
        }
        break;
      }
      default:
      {
        XSDConstrainingFacet xsdConstrainingFacet = XSDConstrainingFacetImpl.createConstrainingFacet(child);
        if (xsdConstrainingFacet != null)
        {
          newContents.add(xsdConstrainingFacet);
        }
      }
    }
  }

  protected void handleReconciliation(List newContents, List remainingContents)
  {
    if (!newContents.isEmpty() && ((XSDConcreteComponent)newContents.get(0)).getElement().getParentNode() == getElement())
    {
      handleAnnotationReconciliation(XSDPackage.eINSTANCE.getXSDTypeDefinition_Annotation(), newContents, remainingContents);
    }
    handleAnnotationReconciliation(XSDPackage.eINSTANCE.getXSDTypeDefinition_DerivationAnnotation(), newContents, remainingContents);

    XSDSimpleTypeDefinition newBaseTypeDefinition = null;
    XSDTypeDefinition newComplexBaseTypeDefinition = null;

    boolean didContents = false;
    Element theElement = getElement();
    LOOP:
    for (Node child = theElement.getFirstChild(); child != null; child = child.getNextSibling())
    {
      switch (XSDConstants.nodeType(child))
      {
        case XSDConstants.LIST_ELEMENT:
        {
          Element elementChild = (Element)child;
          XSDSimpleTypeDefinition newTypeContent = null;
          XSDSimpleTypeDefinition newItemTypeDefinition = null;
          if (!newContents.isEmpty())
          {
            XSDConcreteComponent xsdConcreteComponent = (XSDConcreteComponent)newContents.get(0);
            if (xsdConcreteComponent instanceof XSDSimpleTypeDefinition)
            {
              newItemTypeDefinition = (XSDSimpleTypeDefinition)xsdConcreteComponent;
              newTypeContent = newItemTypeDefinition;
              newContents.remove(0);
            }
          }
          if (elementChild.hasAttributeNS(null, XSDConstants.ITEMTYPE_ATTRIBUTE) && newItemTypeDefinition == null)
          {
            newItemTypeDefinition = 
              resolveSimpleTypeDefinitionURI(XSDConstants.lookupQNameForAttribute((Element)child, XSDConstants.ITEMTYPE_ATTRIBUTE));
          }
          if (newItemTypeDefinition == null)
          {
            newItemTypeDefinition = resolveSimpleTypeDefinition(null, "undefined");
          }

          getContents().removeAll(remainingContents);
          if (newTypeContent != null)
          {
            getContents().add(newTypeContent);
          }

          if (newItemTypeDefinition != getItemTypeDefinition())
          {
            setItemTypeDefinition(newItemTypeDefinition);
          }
          
          didContents = true;
          break LOOP;
        }
        case XSDConstants.UNION_ELEMENT:
        {
          Element elementChild = (Element)child;
          List newMemberTypeDefinitions = new ArrayList();
          if (elementChild.hasAttributeNS(null, XSDConstants.MEMBERTYPES_ATTRIBUTE))
          {
            String memberTypes = elementChild.getAttributeNS(null, XSDConstants.MEMBERTYPES_ATTRIBUTE);
            if (memberTypes != null)
            {
              for (StringTokenizer tokens = new StringTokenizer(memberTypes, " "); tokens.hasMoreTokens(); )
              {
                String memberTypeQName = tokens.nextToken();
                XSDSimpleTypeDefinition newMemberTypeDefinition =
                  resolveSimpleTypeDefinitionURI(XSDConstants.lookupQName(elementChild, memberTypeQName));
                newMemberTypeDefinitions.add(newMemberTypeDefinition);
              }
            }
          }

          List newTypeContents = new ArrayList();
          for (ListIterator i = newContents.listIterator(); i.hasNext(); )
          {
            XSDConcreteComponent xsdConcreteComponent = (XSDConcreteComponent)i.next();
            if (xsdConcreteComponent instanceof XSDSimpleTypeDefinition)
            {
              newTypeContents.add(xsdConcreteComponent);
              newMemberTypeDefinitions.add(xsdConcreteComponent);
              i.remove();
            }
            else
            {
              break;
            }
          }

          getContents().removeAll(remainingContents);
          if (!newTypeContents.isEmpty())
          {
            setListContentAndOrder(getContents(), newTypeContents);
          }

          List remainingMemberTypeDefinitions = new ArrayList(getMemberTypeDefinitions());
          remainingMemberTypeDefinitions.removeAll(newMemberTypeDefinitions);
          if (!remainingMemberTypeDefinitions.isEmpty())
          {
            getMemberTypeDefinitions().removeAll(remainingMemberTypeDefinitions);
          }
          if (!newMemberTypeDefinitions.isEmpty())
          {
            setListContentAndOrder(getMemberTypeDefinitions(), newMemberTypeDefinitions);
          }

          didContents = true;
          break LOOP;
        }
        case XSDConstants.EXTENSION_ELEMENT:
        case XSDConstants.RESTRICTION_ELEMENT:
        {
          Element elementChild = (Element)child;
          if (!newContents.isEmpty())
          {
            XSDConcreteComponent xsdConcreteComponent = (XSDConcreteComponent)newContents.get(0);
            if (xsdConcreteComponent instanceof XSDSimpleTypeDefinition)
            {
              List theContents = getContents();
              if (theContents.size() != 1 || theContents.get(0) != xsdConcreteComponent)
              {
                if (!theContents.isEmpty())
                {
                  remainingContents.removeAll(theContents);
                  theContents.clear();
                }
                theContents.add(xsdConcreteComponent);
              }
              newBaseTypeDefinition = (XSDSimpleTypeDefinition)xsdConcreteComponent;
              newContents.remove(0);
            }
          }

          if (!(getContainer() instanceof XSDComplexTypeDefinition))
          {
            if (elementChild.hasAttributeNS(null, XSDConstants.BASE_ATTRIBUTE) && newBaseTypeDefinition == null)
            {
              newBaseTypeDefinition = 
                resolveSimpleTypeDefinitionURI(XSDConstants.lookupQNameForAttribute(elementChild, XSDConstants.BASE_ATTRIBUTE));
            }
          }
          else
          {
            if (elementChild.hasAttributeNS(null, XSDConstants.BASE_ATTRIBUTE))
            {
              newComplexBaseTypeDefinition = 
                resolveTypeDefinitionURI(XSDConstants.lookupQNameForAttribute(elementChild, XSDConstants.BASE_ATTRIBUTE));
            }
          }

          didContents = true;
          break LOOP;
        }
      }
    }

    if (!didContents && !getContents().isEmpty())
    {
      getContents().clear();
    }

    if (!remainingContents.isEmpty())
    {
      getFacetContents().removeAll(remainingContents);
    }

    if (!newContents.isEmpty())
    {
      for (Iterator i = newContents.iterator(); i.hasNext(); )
      {
        if (!(i.next() instanceof XSDFacet))
        {
          i.remove();
        }
      }
      setListContentAndOrder(getFacetContents(), newContents);
    }

    handleNewBaseTypeDefinition(newBaseTypeDefinition);
    handleNewComplexBaseTypeDefinition(newComplexBaseTypeDefinition);
  }

  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    Element theElement = getElement();
    if (eAttribute == null || eAttribute == XSDPackage.eINSTANCE.getXSDSimpleTypeDefinition_LexicalFinal())
    {
      if (theElement != null)
      {
        niceSetAttribute
          (theElement,
           XSDConstants.FINAL_ATTRIBUTE,
           getStringLexicalFinal());
      }
      if (eAttribute != null)
      {
        traverseToRootForPatching();
      }
    }
    if (eAttribute == null || eAttribute == XSDPackage.eINSTANCE.getXSDSimpleTypeDefinition_Variety())
    {
      if (theElement != null && eAttribute != null) 
      {
        if (analysisState != ANALYZING) 
        {
          LOOP:
          for (Node child = theElement.getFirstChild(); child != null; child = child.getNextSibling())
          {
            int childNodeType = XSDConstants.nodeType(child);
            switch (childNodeType)
            {
              case XSDConstants.RESTRICTION_ELEMENT:
              case XSDConstants.EXTENSION_ELEMENT:
              case XSDConstants.UNION_ELEMENT:
              case XSDConstants.LIST_ELEMENT:
              {
                if (childNodeType != getRequiredChildElementNodeType())
                {
                  Element newElement = createChildElement();
                  forceReplace(newElement, (Element)child);
                }
                break LOOP;
              }
            }
          }
          traverseToRootForAnalysis();
        }
      }
    }
  }

  protected void changeReference(EReference eReference)
  {
    super.changeReference(eReference);
    Element theElement = getElement();
    if (theElement != null)
    {
      if (eReference == null || eReference == XSDPackage.eINSTANCE.getXSDSimpleTypeDefinition_BaseTypeDefinition())
      {
        if (!isReconciling && eReference != null)
        {
          traverseToRootForPatching();
        }

        if (!isReconciling && !(getContainer() instanceof XSDComplexTypeDefinition))
        {
          XSDSimpleTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();
          if (theBaseTypeDefinition != null && !getContents().contains(theBaseTypeDefinition))
          {
            LOOP:
            for (Node child = theElement.getFirstChild(); child != null; child = child.getNextSibling())
            {
              switch (XSDConstants.nodeType(child))
              {
                case XSDConstants.EXTENSION_ELEMENT:
                case XSDConstants.RESTRICTION_ELEMENT:
                {
                  niceSetAttributeURIValue((Element)child, XSDConstants.BASE_ATTRIBUTE, theBaseTypeDefinition.getURI());
                  break LOOP;
                }
              }
            }
          }
        }
      }
      if (eReference == null || eReference == XSDPackage.eINSTANCE.getXSDSimpleTypeDefinition_ItemTypeDefinition())
      {
        if (!isReconciling && eReference != null)
        {
          traverseToRootForPatching();
        }

        XSDSimpleTypeDefinition theItemTypeDefinition = getItemTypeDefinition();
        if (!isReconciling && theItemTypeDefinition != null && !getContents().contains(theItemTypeDefinition))
        {
          LOOP:
          for (Node child = theElement.getFirstChild(); child != null; child = child.getNextSibling())
          {
            switch (XSDConstants.nodeType(child))
            {
              case XSDConstants.LIST_ELEMENT:
              {
                niceSetAttributeURIValue((Element)child, XSDConstants.ITEMTYPE_ATTRIBUTE, theItemTypeDefinition.getURI());
                break LOOP;
              }
            }
          }
        }
      }
      if (eReference == null || eReference == XSDPackage.eINSTANCE.getXSDSimpleTypeDefinition_MemberTypeDefinitions())
      {
        if (!isReconciling && eReference != null)
        {
          traverseToRootForPatching();
        }

        if (!isReconciling)
        {
          LOOP:
          for (Node child = theElement.getFirstChild(); child != null; child = child.getNextSibling())
          {
            switch (XSDConstants.nodeType(child))
            {
              case XSDConstants.UNION_ELEMENT:
              {
                StringBuffer newMemberTypeDefinitions = null;
                for (ListIterator theMemberTypeDefinitions = getMemberTypeDefinitions().listIterator(); theMemberTypeDefinitions.hasNext(); )
                {
                  XSDSimpleTypeDefinition theMemberTypeDefinition = (XSDSimpleTypeDefinition)theMemberTypeDefinitions.next();
                  if (getContents().contains(theMemberTypeDefinition))
                  {
                    break;
                  }
                  if (newMemberTypeDefinitions == null)
                  {
                    newMemberTypeDefinitions = new StringBuffer();
                  }
                  else 
                  {
                    newMemberTypeDefinitions.append(' ');
                  }
                  newMemberTypeDefinitions.append(theMemberTypeDefinition.getURI());
                }
                
                niceSetAttributeURIValue
                  ((Element)child, 
                   XSDConstants.MEMBERTYPES_ATTRIBUTE, 
                   newMemberTypeDefinitions == null ? null : newMemberTypeDefinitions.toString());
                break LOOP;
              }
            }
          }
        }
      }
    }
  }

  protected void adoptContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.adoptContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.eINSTANCE.getXSDSimpleTypeDefinition_Contents() ||
          eReference == XSDPackage.eINSTANCE.getXSDSimpleTypeDefinition_FacetContents())
    {
      traverseToRootForPatching();
    }
    else if (eReference == XSDPackage.eINSTANCE.getXSDTypeDefinition_Annotation())
    {
      getAnnotations().add(0, xsdConcreteComponent);
    }
    else if (eReference == XSDPackage.eINSTANCE.getXSDTypeDefinition_DerivationAnnotation())
    {
      getAnnotations().add(xsdConcreteComponent);
    }
  }

  protected void orphanContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.orphanContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.eINSTANCE.getXSDSimpleTypeDefinition_Contents() || 
          eReference == XSDPackage.eINSTANCE.getXSDSimpleTypeDefinition_FacetContents())
    {
      traverseToRootForPatching();
    }
    else if (eReference == XSDPackage.eINSTANCE.getXSDTypeDefinition_Annotation() ||
               eReference == XSDPackage.eINSTANCE.getXSDTypeDefinition_DerivationAnnotation())
    {
      getAnnotations().remove(xsdConcreteComponent);
    }
  }

  public XSDTotalDigitsFacet getTotalDigitsFacet()
  {
    for (Iterator facets = getFacetContents().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDTotalDigitsFacet)
      {
        return (XSDTotalDigitsFacet)facet;
      }
    }
    return null;
  }

  public XSDTotalDigitsFacet getEffectiveTotalDigitsFacet()
  {
    for (Iterator facets = getFacets().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDTotalDigitsFacet)
      {
        return (XSDTotalDigitsFacet)facet;
      }
    }
    return null;
  }

  public XSDFractionDigitsFacet getFractionDigitsFacet()
  {
    for (Iterator facets = getFacetContents().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDFractionDigitsFacet)
      {
        return (XSDFractionDigitsFacet)facet;
      }
    }
    return null;
  }

  public XSDFractionDigitsFacet getEffectiveFractionDigitsFacet()
  {
    for (Iterator facets = getFacets().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDFractionDigitsFacet)
      {
        return (XSDFractionDigitsFacet)facet;
      }
    }
    return null;
  }

  public XSDLengthFacet getLengthFacet()
  {
    for (Iterator facets = getFacetContents().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDLengthFacet)
      {
        return (XSDLengthFacet)facet;
      }
    }
    return null;
  }

  public XSDLengthFacet getEffectiveLengthFacet()
  {
    for (Iterator facets = getFacets().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDLengthFacet)
      {
        return (XSDLengthFacet)facet;
      }
    }
    return null;
  }

  public XSDMaxLengthFacet getMaxLengthFacet()
  {
    for (Iterator facets = getFacetContents().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDMaxLengthFacet)
      {
        return (XSDMaxLengthFacet)facet;
      }
    }
    return null;
  }

  public XSDMaxLengthFacet getEffectiveMaxLengthFacet()
  {
    for (Iterator facets = getFacets().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDMaxLengthFacet)
      {
        return (XSDMaxLengthFacet)facet;
      }
    }
    return null;
  }

  public XSDMinLengthFacet getMinLengthFacet()
  {
    for (Iterator facets = getFacetContents().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDMinLengthFacet)
      {
        return (XSDMinLengthFacet)facet;
      }
    }
    return null;
  }

  public XSDMinLengthFacet getEffectiveMinLengthFacet()
  {
    for (Iterator facets = getFacets().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDMinLengthFacet)
      {
        return (XSDMinLengthFacet)facet;
      }
    }
    return null;
  }

  public XSDMaxExclusiveFacet getMaxExclusiveFacet()
  {
    for (Iterator facets = getFacetContents().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDMaxExclusiveFacet)
      {
        return (XSDMaxExclusiveFacet)facet;
      }
    }
    return null;
  }

  public XSDMaxInclusiveFacet getMaxInclusiveFacet()
  {
    for (Iterator facets = getFacetContents().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDMaxInclusiveFacet)
      {
        return (XSDMaxInclusiveFacet)facet;
      }
    }
    return null;
  }

  public XSDMaxFacet getMaxFacet()
  {
    for (Iterator facets = getFacetContents().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDMaxFacet)
      {
        return (XSDMaxFacet)facet;
      }
    }
    return null;
  }

  public XSDMaxFacet getEffectiveMaxFacet()
  {
    for (Iterator facets = getFacets().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDMaxFacet)
      {
        return (XSDMaxFacet)facet;
      }
    }
    return null;
  }

  public XSDMinExclusiveFacet getMinExclusiveFacet()
  {
    for (Iterator facets = getFacetContents().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDMinExclusiveFacet)
      {
        return (XSDMinExclusiveFacet)facet;
      }
    }
    return null;
  }

  public XSDMinInclusiveFacet getMinInclusiveFacet()
  {
    for (Iterator facets = getFacetContents().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDMinInclusiveFacet)
      {
        return (XSDMinInclusiveFacet)facet;
      }
    }
    return null;
  }

  public XSDMinFacet getMinFacet()
  {
    for (Iterator facets = getFacetContents().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDMinFacet)
      {
        return (XSDMinFacet)facet;
      }
    }
    return null;
  }

  public XSDMinFacet getEffectiveMinFacet()
  {
    for (Iterator facets = getFacets().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDMinFacet)
      {
        return (XSDMinFacet)facet;
      }
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getSyntheticFacets()
  {
    if (syntheticFacets == null)
    {
      syntheticFacets = new EObjectContainmentEList(XSDFacet.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__SYNTHETIC_FACETS);
    }
    return syntheticFacets;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ANNOTATION:
          return basicSetAnnotation(null, msgs);
        case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__DERIVATION_ANNOTATION:
          return basicSetDerivationAnnotation(null, msgs);
        case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__CONTENTS:
          return ((InternalEList)getContents()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FACET_CONTENTS:
          return ((InternalEList)getFacetContents()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FUNDAMENTAL_FACETS:
          return ((InternalEList)getFundamentalFacets()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__SYNTHETIC_FACETS:
          return ((InternalEList)getSyntheticFacets()).basicRemove(otherEnd, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
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
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ELEMENT:
        return getElement();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__NAME:
        return getName();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__TARGET_NAMESPACE:
        return getTargetNamespace();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ALIAS_NAME:
        return getAliasName();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__URI:
        return getURI();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ALIAS_URI:
        return getAliasURI();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__QNAME:
        return getQName();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__CIRCULAR:
        return isCircular() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__DERIVATION_ANNOTATION:
        return getDerivationAnnotation();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ANNOTATIONS:
        return getAnnotations();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ROOT_TYPE:
        return getRootType();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__BASE_TYPE:
        return getBaseType();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__SIMPLE_TYPE:
        return getSimpleType();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__COMPLEX_TYPE:
        return getComplexType();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__VARIETY:
        return getVariety();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FINAL:
        return getFinal();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__LEXICAL_FINAL:
        return getLexicalFinal();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__VALID_FACETS:
        return getValidFacets();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__CONTENTS:
        return getContents();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FACET_CONTENTS:
        return getFacetContents();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FACETS:
        return getFacets();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MEMBER_TYPE_DEFINITIONS:
        return getMemberTypeDefinitions();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FUNDAMENTAL_FACETS:
        return getFundamentalFacets();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__BASE_TYPE_DEFINITION:
        return getBaseTypeDefinition();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__PRIMITIVE_TYPE_DEFINITION:
        return getPrimitiveTypeDefinition();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ITEM_TYPE_DEFINITION:
        return getItemTypeDefinition();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ROOT_TYPE_DEFINITION:
        return getRootTypeDefinition();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MIN_FACET:
        return getMinFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MAX_FACET:
        return getMaxFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MAX_INCLUSIVE_FACET:
        return getMaxInclusiveFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MIN_INCLUSIVE_FACET:
        return getMinInclusiveFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MIN_EXCLUSIVE_FACET:
        return getMinExclusiveFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MAX_EXCLUSIVE_FACET:
        return getMaxExclusiveFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__LENGTH_FACET:
        return getLengthFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__WHITE_SPACE_FACET:
        return getWhiteSpaceFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ENUMERATION_FACETS:
        return getEnumerationFacets();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__PATTERN_FACETS:
        return getPatternFacets();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__CARDINALITY_FACET:
        return getCardinalityFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__NUMERIC_FACET:
        return getNumericFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MAX_LENGTH_FACET:
        return getMaxLengthFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MIN_LENGTH_FACET:
        return getMinLengthFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__TOTAL_DIGITS_FACET:
        return getTotalDigitsFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FRACTION_DIGITS_FACET:
        return getFractionDigitsFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ORDERED_FACET:
        return getOrderedFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__BOUNDED_FACET:
        return getBoundedFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_MAX_FACET:
        return getEffectiveMaxFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_WHITE_SPACE_FACET:
        return getEffectiveWhiteSpaceFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_MAX_LENGTH_FACET:
        return getEffectiveMaxLengthFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_FRACTION_DIGITS_FACET:
        return getEffectiveFractionDigitsFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_PATTERN_FACET:
        return getEffectivePatternFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_ENUMERATION_FACET:
        return getEffectiveEnumerationFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_TOTAL_DIGITS_FACET:
        return getEffectiveTotalDigitsFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_MIN_LENGTH_FACET:
        return getEffectiveMinLengthFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_LENGTH_FACET:
        return getEffectiveLengthFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_MIN_FACET:
        return getEffectiveMinFacet();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__SYNTHETIC_FACETS:
        return getSyntheticFacets();
    }
    return eDynamicGet(featureID, resolve, coreType);
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
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__NAME:
        setName((String)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__TARGET_NAMESPACE:
        setTargetNamespace((String)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__DERIVATION_ANNOTATION:
        setDerivationAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ANNOTATIONS:
        getAnnotations().clear();
        getAnnotations().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__VARIETY:
        setVariety((XSDVariety)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FINAL:
        getFinal().clear();
        getFinal().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__LEXICAL_FINAL:
        getLexicalFinal().clear();
        getLexicalFinal().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__VALID_FACETS:
        getValidFacets().clear();
        getValidFacets().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__CONTENTS:
        getContents().clear();
        getContents().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FACET_CONTENTS:
        getFacetContents().clear();
        getFacetContents().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FACETS:
        getFacets().clear();
        getFacets().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MEMBER_TYPE_DEFINITIONS:
        getMemberTypeDefinitions().clear();
        getMemberTypeDefinitions().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FUNDAMENTAL_FACETS:
        getFundamentalFacets().clear();
        getFundamentalFacets().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__BASE_TYPE_DEFINITION:
        setBaseTypeDefinition((XSDSimpleTypeDefinition)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__PRIMITIVE_TYPE_DEFINITION:
        setPrimitiveTypeDefinition((XSDSimpleTypeDefinition)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ITEM_TYPE_DEFINITION:
        setItemTypeDefinition((XSDSimpleTypeDefinition)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__SYNTHETIC_FACETS:
        getSyntheticFacets().clear();
        getSyntheticFacets().addAll((Collection)newValue);
        return;
    }
    eDynamicSet(featureID, newValue);
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
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__TARGET_NAMESPACE:
        setTargetNamespace(TARGET_NAMESPACE_EDEFAULT);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__DERIVATION_ANNOTATION:
        setDerivationAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ANNOTATIONS:
        getAnnotations().clear();
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__VARIETY:
        unsetVariety();
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FINAL:
        getFinal().clear();
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__LEXICAL_FINAL:
        unsetLexicalFinal();
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__VALID_FACETS:
        getValidFacets().clear();
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__CONTENTS:
        getContents().clear();
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FACET_CONTENTS:
        getFacetContents().clear();
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FACETS:
        getFacets().clear();
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MEMBER_TYPE_DEFINITIONS:
        getMemberTypeDefinitions().clear();
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FUNDAMENTAL_FACETS:
        getFundamentalFacets().clear();
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__BASE_TYPE_DEFINITION:
        setBaseTypeDefinition((XSDSimpleTypeDefinition)null);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__PRIMITIVE_TYPE_DEFINITION:
        setPrimitiveTypeDefinition((XSDSimpleTypeDefinition)null);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ITEM_TYPE_DEFINITION:
        setItemTypeDefinition((XSDSimpleTypeDefinition)null);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__SYNTHETIC_FACETS:
        getSyntheticFacets().clear();
        return;
    }
    eDynamicUnset(featureID);
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
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__TARGET_NAMESPACE:
        return TARGET_NAMESPACE_EDEFAULT == null ? targetNamespace != null : !TARGET_NAMESPACE_EDEFAULT.equals(targetNamespace);
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ALIAS_NAME:
        return ALIAS_NAME_EDEFAULT == null ? getAliasName() != null : !ALIAS_NAME_EDEFAULT.equals(getAliasName());
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__URI:
        return URI_EDEFAULT == null ? getURI() != null : !URI_EDEFAULT.equals(getURI());
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ALIAS_URI:
        return ALIAS_URI_EDEFAULT == null ? getAliasURI() != null : !ALIAS_URI_EDEFAULT.equals(getAliasURI());
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__QNAME:
        return QNAME_EDEFAULT == null ? getQName() != null : !QNAME_EDEFAULT.equals(getQName());
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__CIRCULAR:
        return isCircular() != CIRCULAR_EDEFAULT;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__DERIVATION_ANNOTATION:
        return derivationAnnotation != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ROOT_TYPE:
        return getRootType() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__BASE_TYPE:
        return getBaseType() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__SIMPLE_TYPE:
        return getSimpleType() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__COMPLEX_TYPE:
        return getComplexType() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__VARIETY:
        return isSetVariety();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FINAL:
        return final_ != null && !final_.isEmpty();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__LEXICAL_FINAL:
        return isSetLexicalFinal();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__VALID_FACETS:
        return validFacets != null && !validFacets.isEmpty();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__CONTENTS:
        return contents != null && !contents.isEmpty();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FACET_CONTENTS:
        return facetContents != null && !facetContents.isEmpty();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FACETS:
        return facets != null && !facets.isEmpty();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MEMBER_TYPE_DEFINITIONS:
        return memberTypeDefinitions != null && !memberTypeDefinitions.isEmpty();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FUNDAMENTAL_FACETS:
        return fundamentalFacets != null && !fundamentalFacets.isEmpty();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__BASE_TYPE_DEFINITION:
        return baseTypeDefinition != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__PRIMITIVE_TYPE_DEFINITION:
        return primitiveTypeDefinition != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ITEM_TYPE_DEFINITION:
        return itemTypeDefinition != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ROOT_TYPE_DEFINITION:
        return getRootTypeDefinition() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MIN_FACET:
        return getMinFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MAX_FACET:
        return getMaxFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MAX_INCLUSIVE_FACET:
        return getMaxInclusiveFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MIN_INCLUSIVE_FACET:
        return getMinInclusiveFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MIN_EXCLUSIVE_FACET:
        return getMinExclusiveFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MAX_EXCLUSIVE_FACET:
        return getMaxExclusiveFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__LENGTH_FACET:
        return getLengthFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__WHITE_SPACE_FACET:
        return getWhiteSpaceFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ENUMERATION_FACETS:
        return !getEnumerationFacets().isEmpty();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__PATTERN_FACETS:
        return !getPatternFacets().isEmpty();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__CARDINALITY_FACET:
        return getCardinalityFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__NUMERIC_FACET:
        return getNumericFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MAX_LENGTH_FACET:
        return getMaxLengthFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MIN_LENGTH_FACET:
        return getMinLengthFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__TOTAL_DIGITS_FACET:
        return getTotalDigitsFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FRACTION_DIGITS_FACET:
        return getFractionDigitsFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__ORDERED_FACET:
        return getOrderedFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__BOUNDED_FACET:
        return getBoundedFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_MAX_FACET:
        return getEffectiveMaxFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_WHITE_SPACE_FACET:
        return getEffectiveWhiteSpaceFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_MAX_LENGTH_FACET:
        return getEffectiveMaxLengthFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_FRACTION_DIGITS_FACET:
        return getEffectiveFractionDigitsFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_PATTERN_FACET:
        return getEffectivePatternFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_ENUMERATION_FACET:
        return getEffectiveEnumerationFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_TOTAL_DIGITS_FACET:
        return getEffectiveTotalDigitsFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_MIN_LENGTH_FACET:
        return getEffectiveMinLengthFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_LENGTH_FACET:
        return getEffectiveLengthFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_MIN_FACET:
        return getEffectiveMinFacet() != null;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__SYNTHETIC_FACETS:
        return syntheticFacets != null && !syntheticFacets.isEmpty();
    }
    return eDynamicIsSet(featureID);
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
    result.append(" (variety: ");
    if ((eFlags & VARIETY_ESETFLAG) != 0) result.append(variety); else result.append("<unset>");
    result.append(", final: ");
    result.append(final_);
    result.append(", lexicalFinal: ");
    result.append(lexicalFinal);
    result.append(", validFacets: ");
    result.append(validFacets);
    result.append(')');
    return result.toString();
  }

  public XSDWhiteSpaceFacet getWhiteSpaceFacet()
  {
    for (Iterator facets = getFacetContents().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDWhiteSpaceFacet)
      {
        return (XSDWhiteSpaceFacet)facet;
      }
    }
    return null;
  }

  public XSDWhiteSpaceFacet getEffectiveWhiteSpaceFacet()
  {
    for (Iterator facets = getFacets().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDWhiteSpaceFacet)
      {
        return (XSDWhiteSpaceFacet)facet;
      }
    }
    return null;
  }

  public EList getEnumerationFacets()
  {
    EList result = new BasicEList();
    for (Iterator facets = getFacetContents().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDEnumerationFacet)
      {
        result.add(facet);
      }
    }
    return 
      new EcoreEList.UnmodifiableEList.FastCompare
        (this, XSDPackage.eINSTANCE.getXSDSimpleTypeDefinition_EnumerationFacets(), result.size(), result.toArray());
  }

  public XSDEnumerationFacet getEffectiveEnumerationFacet()
  {
    for (Iterator facets = getFacets().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDEnumerationFacet)
      {
        return (XSDEnumerationFacet)facet;
      }
    }
    return null;
  }

  public EList getPatternFacets()
  {
    EList result = new BasicEList();
    for (Iterator facets = getFacetContents().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDPatternFacet)
      {
        result.add(facet);
      }
    }
    return 
      new EcoreEList.UnmodifiableEList.FastCompare
        (this, XSDPackage.eINSTANCE.getXSDSimpleTypeDefinition_PatternFacets(), result.size(), result.toArray());
  }

  public XSDPatternFacet getEffectivePatternFacet()
  {
    for (Iterator facets = getFacets().iterator(); facets.hasNext(); )
    {
      Object facet = facets.next();
      if (facet instanceof XSDPatternFacet)
      {
        return (XSDPatternFacet)facet;
      }
    }
    return null;
  }

  protected XSDBoundedFacet boundedFacet;
  public XSDBoundedFacet getBoundedFacet()
  {
    if (boundedFacet == null)
    {
      createFundamentalFacets();
    }
    return boundedFacet;
  }

  protected XSDCardinalityFacet cardinalityFacet;
  public XSDCardinalityFacet getCardinalityFacet()
  {
    if (cardinalityFacet == null)
    {
      createFundamentalFacets();
    }
    return cardinalityFacet;
  }

  protected XSDNumericFacet numericFacet;
  public XSDNumericFacet getNumericFacet()
  {
    if (numericFacet == null)
    {
      createFundamentalFacets();
    }
    return numericFacet;
  }

  protected XSDOrderedFacet orderedFacet;
  public XSDOrderedFacet getOrderedFacet()
  {
    if (orderedFacet == null)
    {
      createFundamentalFacets();
    }
    return orderedFacet;
  }

  protected void createFundamentalFacets()
  {
    List theFundamentalFacets = getFundamentalFacets();
    boundedFacet = getXSDFactory().createXSDBoundedFacet();
    cardinalityFacet = getXSDFactory().createXSDCardinalityFacet();
    numericFacet = getXSDFactory().createXSDNumericFacet();
    orderedFacet = getXSDFactory().createXSDOrderedFacet();
    List list = new ArrayList(4);
    list.add(boundedFacet);
    list.add(cardinalityFacet);
    list.add(numericFacet);
    list.add(orderedFacet);
    theFundamentalFacets.addAll(list);
  }

  protected static EList validFacetsForList;
  public EList getValidFacetsForList()
  {
    if (validFacetsForList == null)
    {
      validFacetsForList = new BasicEList();
      validFacetsForList.add("length");
      validFacetsForList.add("maxLength");
      validFacetsForList.add("minLength");
      validFacetsForList.add("whiteSpace");
      validFacetsForList.add("enumeration");
      validFacetsForList.add("pattern");
    }
    return validFacetsForList;
  }

  protected static EList validFacetsForUnion;
  public EList getValidFacetsForUnion()
  {
    if (validFacetsForUnion == null)
    {
      validFacetsForUnion = new BasicEList();
      validFacetsForUnion.add("enumeration");
      validFacetsForUnion.add("pattern");
    }
    return validFacetsForUnion;
  }

  public XSDTypeDefinition getBaseType()
  {
    XSDTypeDefinition result = getBaseTypeDefinition();
    if (result == this && XSDConstants.isAnyType(this))
    {
      XSDSchema xsdSchema = getSchema();
      if (xsdSchema != null)
      {
        String schemaForSchemaNamespace = xsdSchema.getSchemaForSchemaNamespace();
        XSDSchemaImpl.getSchemaForSchema(schemaForSchemaNamespace).resolveComplexTypeDefinition("anyType");
      }
    }
    return result;
  }

  public XSDTypeDefinition getRootType()
  {
    return getRootTypeDefinition();
  }

  public XSDSimpleTypeDefinition getRootTypeDefinition()
  {
    XSDSimpleTypeDefinition result = this;
    if (!isCircular())
    {
      for (XSDSimpleTypeDefinition theBaseTypeDefinition = result.getBaseTypeDefinition();
           theBaseTypeDefinition != null && !XSDConstants.isURType(theBaseTypeDefinition); 
           theBaseTypeDefinition = theBaseTypeDefinition.getBaseTypeDefinition())
      {
        result = theBaseTypeDefinition;
      }
    }
    return result;
  }

  public XSDSimpleTypeDefinition getSimpleType()
  {
    return this;
  }

  public XSDParticle getComplexType()
  {
    return null;
  }

  protected XSDEnumerationFacetImpl effectiveEnumerationFacet;
  protected XSDPatternFacetImpl effectivePatternFacet;

  protected List mergeFacets()
  {
    List result = new ArrayList();

    XSDSimpleTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();

    XSDWhiteSpaceFacet xsdWhiteSpaceFacet = getWhiteSpaceFacet();
    if (xsdWhiteSpaceFacet == null)
    {
      xsdWhiteSpaceFacet = theBaseTypeDefinition.getEffectiveWhiteSpaceFacet();
    }
    if (xsdWhiteSpaceFacet != null)
    {
      result.add(xsdWhiteSpaceFacet);
    }

    XSDTotalDigitsFacet xsdTotalDigitsFacet = getTotalDigitsFacet();
    if (xsdTotalDigitsFacet == null)
    {
      xsdTotalDigitsFacet = theBaseTypeDefinition.getEffectiveTotalDigitsFacet();
    }
    if (xsdTotalDigitsFacet != null)
    {
      result.add(xsdTotalDigitsFacet);
    }

    XSDFractionDigitsFacet xsdFractionDigitsFacet = getFractionDigitsFacet();
    if (xsdFractionDigitsFacet == null)
    {
      xsdFractionDigitsFacet = theBaseTypeDefinition.getEffectiveFractionDigitsFacet();
    }
    if (xsdFractionDigitsFacet != null)
    {
      result.add(xsdFractionDigitsFacet);
    }

    XSDLengthFacet xsdLengthFacet = getLengthFacet();
    if (xsdLengthFacet == null)
    {
      xsdLengthFacet = theBaseTypeDefinition.getEffectiveLengthFacet();
    }
    if (xsdLengthFacet != null)
    {
      result.add(xsdLengthFacet);
    }

    XSDMinLengthFacet xsdMinLengthFacet = getMinLengthFacet();
    if (xsdMinLengthFacet == null)
    {
      xsdMinLengthFacet = theBaseTypeDefinition.getEffectiveMinLengthFacet();
    }
    if (xsdMinLengthFacet != null)
    {
      result.add(xsdMinLengthFacet);
    }

    XSDMaxLengthFacet xsdMaxLengthFacet = getMaxLengthFacet();
    if (xsdMaxLengthFacet == null)
    {
      xsdMaxLengthFacet = theBaseTypeDefinition.getEffectiveMaxLengthFacet();
    }
    if (xsdMaxLengthFacet != null)
    {
      result.add(xsdMaxLengthFacet);
    }

    XSDConstrainingFacet maxFacet = getMaxFacet();
    if (maxFacet == null)
    {
      maxFacet = theBaseTypeDefinition.getEffectiveMaxFacet();
    }
    if (maxFacet != null)
    {
      result.add(maxFacet);
    }

    XSDConstrainingFacet minFacet = getMinFacet();
    if (minFacet == null)
    {
      minFacet = theBaseTypeDefinition.getEffectiveMinFacet();
    }
    if (minFacet != null)
    {
      result.add(minFacet);
    }

    List enumerationFacets = getEnumerationFacets();
    if (enumerationFacets.isEmpty())
    {
      XSDEnumerationFacet baseEnumerationFacet = theBaseTypeDefinition.getEffectiveEnumerationFacet();
      if (baseEnumerationFacet != null)
      {
        result.add(baseEnumerationFacet);
      }
    }
    else
    {
      if (effectiveEnumerationFacet == null)
      {
        effectiveEnumerationFacet = (XSDEnumerationFacetImpl)getXSDFactory().createXSDEnumerationFacet();
        getSyntheticFacets().add(effectiveEnumerationFacet);
      }

      StringBuffer newLexicalValue = new StringBuffer();
      List newValue = new ArrayList();
      List newAnnotations = new ArrayList();

      for (Iterator facets = enumerationFacets.iterator(); facets.hasNext(); )
      {
        XSDEnumerationFacet enumerationFacet = (XSDEnumerationFacet)facets.next();
        newValue.addAll(enumerationFacet.getValue());

        XSDAnnotation xsdAnnotation = enumerationFacet.getAnnotation();
        if (xsdAnnotation != null)
        {
          newAnnotations.add(xsdAnnotation);
        }

        if (newLexicalValue.length() != 0)
        {
          newLexicalValue.append(", ");
        }
        newLexicalValue.append(enumerationFacet.getLexicalValue());
      }
      String newLexicalValueString = newLexicalValue.toString();

      List remainingValues = new ArrayList(effectiveEnumerationFacet.getValue());
      remainingValues.removeAll(newValue);
      if (!remainingValues.isEmpty())
      {
        effectiveEnumerationFacet.getValue().removeAll(remainingValues);
      }
      if (!newValue.isEmpty())
      {
        setListContentAndOrder(effectiveEnumerationFacet.getValue(), newValue);
      }

      List remainingAnnotations = new ArrayList(effectiveEnumerationFacet.getAnnotations());
      remainingAnnotations.removeAll(newAnnotations);
      if (!remainingAnnotations.isEmpty())
      {
        effectiveEnumerationFacet.getAnnotations().removeAll(remainingAnnotations);
      }
      if (!newAnnotations.isEmpty())
      {
        setListContentAndOrder(effectiveEnumerationFacet.getAnnotations(), newAnnotations);
      }

      if (!newLexicalValueString.equals(effectiveEnumerationFacet.getLexicalValue()))
      {
        effectiveEnumerationFacet.isReconciling = true;
        effectiveEnumerationFacet.setLexicalValue(newLexicalValueString);
        effectiveEnumerationFacet.isReconciling = false;
      }

      result.add(effectiveEnumerationFacet);
    }

    List patternFacets = getPatternFacets();
    if (patternFacets.isEmpty())
    {
      XSDPatternFacet basePatternFacet = theBaseTypeDefinition.getEffectivePatternFacet();
      if (basePatternFacet != null)
      {
        result.add(basePatternFacet);
      }
    }
    else
    {
      if (effectivePatternFacet == null)
      {
        effectivePatternFacet = (XSDPatternFacetImpl)getXSDFactory().createXSDPatternFacet();
        getSyntheticFacets().add(effectivePatternFacet);
      }

      List newValue = new ArrayList();
      List newAnnotations = new ArrayList();
      XSDPatternFacet effectiveBasePatternFacet = theBaseTypeDefinition.getEffectivePatternFacet();
      if (effectiveBasePatternFacet != null)
      {
        newValue.addAll(effectiveBasePatternFacet.getValue());
      }

      StringBuffer combinedPattern = new StringBuffer();
      if (patternFacets.size() == 1)
      {
        XSDPatternFacet xsdPatternFacet = (XSDPatternFacet)patternFacets.get(0);
        combinedPattern.append(xsdPatternFacet.getLexicalValue());
        XSDAnnotation xsdAnnotation = xsdPatternFacet.getAnnotation();
        if (xsdAnnotation != null)
        {
          newAnnotations.add(xsdAnnotation);
        }
      }
      else
      {
        for (Iterator facets = patternFacets.iterator(); facets.hasNext(); )
        {
          XSDPatternFacet xsdPatternFacet = (XSDPatternFacet)facets.next();
          if (combinedPattern.length() != 0)
          {
            combinedPattern.append("|");
          }
          combinedPattern.append("(");
          combinedPattern.append(xsdPatternFacet.getLexicalValue());
          combinedPattern.append(")");
          XSDAnnotation xsdAnnotation = xsdPatternFacet.getAnnotation();
          if (xsdAnnotation != null)
          {
            newAnnotations.add(xsdAnnotation);
          }
        }
      }
      newValue.add(combinedPattern.toString());

      List remainingValues = new ArrayList(effectivePatternFacet.getValue());
      remainingValues.removeAll(newValue);
      if (!remainingValues.isEmpty())
      {
        effectivePatternFacet.getValue().removeAll(remainingValues);
      }
      if (!newValue.isEmpty())
      {
        setListContentAndOrder(effectivePatternFacet.getValue(), newValue);
      }

      List remainingAnnotations = new ArrayList(effectivePatternFacet.getAnnotations());
      remainingAnnotations.removeAll(newAnnotations);
      if (!remainingAnnotations.isEmpty())
      {
        effectivePatternFacet.getAnnotations().removeAll(remainingAnnotations);
      }
      if (!newAnnotations.isEmpty())
      {
        setListContentAndOrder(effectivePatternFacet.getAnnotations(), newAnnotations);
      }

      StringBuffer newLexicalValue = new StringBuffer();
      if (newValue.size() == 1)
      {
        newLexicalValue.append(newValue.get(0));
      }
      else
      {
        for (Iterator strings = newValue.iterator(); strings.hasNext(); )
        {
          String string = (String)strings.next();
          if (newLexicalValue.length() != 0)
          {
            newLexicalValue.append(" & ");
          }
          newLexicalValue.append("(");
          newLexicalValue.append(string);
          newLexicalValue.append(")");
        }
      }
      String newLexicalValueString = newLexicalValue.toString();
      if (!newLexicalValueString.equals(effectivePatternFacet.getLexicalValue()))
      {
        effectivePatternFacet.isReconciling = true;
        effectivePatternFacet.setLexicalValue(newLexicalValueString);
        effectivePatternFacet.isReconciling = false;
      }

      result.add(effectivePatternFacet);
    }

    return result;
  }


  public String getStringLexicalFinal()
  {
    if (isSetLexicalFinal())
    {
      StringBuffer result = new StringBuffer();
      for (Iterator literals = getLexicalFinal().iterator(); literals.hasNext(); )
      {
        Object literal = literals.next();
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
      List newLexicalFinal = new ArrayList();
      for (StringTokenizer stringTokenizer = new StringTokenizer(finalDefault); stringTokenizer.hasMoreTokens(); )
      {
        String token = stringTokenizer.nextToken();
        if (token.equals("#all"))
        {
          token = "all";
        }
        XSDSimpleFinal literal = XSDSimpleFinal.get(token);
        if (literal != null)
        {
          newLexicalFinal.add(literal);
        }
      }
      if (!newLexicalFinal.equals(getLexicalFinal()))
      {
        Collection oldContents = new ArrayList(getLexicalFinal());
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

  public String getStringFinal()
  {
    StringBuffer result = new StringBuffer();
    for (Iterator literals = getFinal().iterator(); literals.hasNext(); )
    {
      Object literal = literals.next();
      if (result.length() != 0)
      {
        result.append(' ');
      }
      result.append(literal);
    }
    return result.toString();
  }

  /**
   * This is used to gather selectively 
   * the information determined when assessing a literal string 
   * with respect to a {@link XSDSimpleTypeDefinition}.
   * It is intended to be very light-weight and flexible.
   *
   * You may begin assessment with a {@link #node}, which handles three cases:
   * if the node is an attribute, it's value is used to determine the literal;
   * if the node is a text node, it's value is used to determine the literal;
   * if the node is an element, it's (one and only) child text node is used to determine the literal in the preceeding case.
   * You may begin assessment with a {@link #literal} by not setting a node.
   * And you may begin assessment with a {@link #normalizedLiteral} by setting neither the node nor the literal.
   *
   * Unless additional {@link #yield} flags are set,
   * assessment will perform but one function:
   * it will set the {@link #diagnostics} to Collections.EMPTY_LIST when the literal fails to assess as valid.
   * Hence, this most-light-weight invocation will only determine validity.
   *
   * You may choose for assessment to yield additional results, 
   * i.e., the {@link #value}, the {@link #canonicalLiteral}, and the {@link #diagnostics},
   * using the flags {@link #YIELD_VALUE}, {@link #YIELD_CANONICAL_LITERAL}, {@link #YIELD_DIAGNOSTICS}.
   */
  public static class AssessmentImpl implements XSDSimpleTypeDefinition.Assessment
  { 
    public static final int YIELD_VALUE             = 0x01;
    public static final int YIELD_CANONICAL_LITERAL = 0x02;
    public static final int YIELD_DIAGNOSTICS       = 0x04;

    /**
     * This is the simple type definition doing the assessment.
     */
    public XSDSimpleTypeDefinitionImpl xsdSimpleTypeDefinition;

    /**
     * This is the simple type definition doing the assessment for an atomic type.
     */
    public XSDAnySimpleType xsdAnySimpleType;

    /**
     * This is the node whose literal is being assessed.
     */
    public Node node;

    /**
     * This is the literal that is being assessed.
     */
    public String literal;

    /**
     * This is the normalized value of the literal being assessed.
     */
    public String normalizedLiteral;

    /**
     *  This determines the results that will be yielded during assessment.
     */
    public int yield;

    /**
     * This is the value of the normalized literal in the Java representation of the value space.
     */
    public Object value;

    /**
     * This is the canonical literal representation of the literal.
     */
    public String canonicalLiteral;

    /**
     * These are the diagnostics that are collected.
     */
    public Collection diagnostics;

    /**
     * This records any nested assessments that were performed.
     */
    public Collection assessments;

    /**
     * This creates an empty instance;
     * minimally, the node or literal would need to be set before using this.
     */
    public AssessmentImpl()
    {
    }

    /**
     * This creates an instance to assess the given node and to yield the specified results.
     */
    public AssessmentImpl(Node node, int yield)
    {
      this.yield = yield;
      this.node = node;
    }

    /**
     * This creates an instance to assess the given literal and to yield the specified results.
     */
    public AssessmentImpl(String literal, int yield)
    {
      this.yield = yield;
      this.literal = literal;
    }

    public XSDSimpleTypeDefinition getTypeDefinition()
    {
      return xsdSimpleTypeDefinition;
    }

    public Node getNode()
    {
      return node;
    }

    public String getLiteral()
    {
      return literal;
    }

    public String getNormalizedLiteral()
    {
      return normalizedLiteral;
    }

    public Object getValue()
    {
      return value;
    }

    public String getCanonicalLiteral()
    {
      return normalizedLiteral;
    }

    public XSDSimpleTypeDefinition getMemberTypeDefinition()
    {
      return null;
    }

    public Collection getLocalDiagnostics()
    {
      return diagnostics == null ? Collections.EMPTY_LIST : diagnostics;
    }

    public Collection getAssessments()
    {
      return assessments;
    }

    public Collection getDiagnostics()
    {
      Collection result = new ArrayList();
      getAllDiagnostics(result);
      return result;
    }

    protected void getAllDiagnostics(Collection result)
    {
      if (diagnostics != null)
      {
        result.addAll(diagnostics);
      }
      if (assessments != null)
      {
        for (Iterator i = assessments.iterator(); i.hasNext(); )
        {
          AssessmentImpl assessment = (AssessmentImpl)i.next();
          assessment.getAllDiagnostics(result);
        }
      }
    }

    public void format(String noun, String name)
    {
      if (diagnostics != null)
      {
        for (Iterator i = diagnostics.iterator(); i.hasNext(); )
        {
          XSDDiagnostic xsdDiagnostic = (XSDDiagnostic)i.next();
          xsdDiagnostic.setMessage(MessageFormat.format(xsdDiagnostic.getMessage(), new Object [] { noun, name }));
        }
      }
      if (assessments != null)
      {
        for (Iterator i = assessments.iterator(); i.hasNext(); )
        {
          Assessment assessment = (Assessment)i.next();
          assessment.format(noun, name);
        }
      }
    }

    public void assignDiagnostics(XSDConcreteComponent xsdConcreteComponent, Element element, String attributeName)
    {
      Collection allDiagnostics = getDiagnostics();
      if (!allDiagnostics.isEmpty())
      {
        Node theNode = element;
        if (element != null && element.hasAttributeNS(null, attributeName))
        {
          theNode = element.getAttributeNodeNS(null, attributeName);
        }

        Object [] substitutions = new Object [] { XSDPlugin.INSTANCE.getString("attribute_noun"), attributeName };
        for (Iterator i = allDiagnostics.iterator(); i.hasNext(); )
        {
          XSDDiagnostic xsdDiagnostic = (XSDDiagnostic)i.next();
          xsdDiagnostic.setMessage(MessageFormat.format(xsdDiagnostic.getMessage(), substitutions));
          xsdDiagnostic.getComponents().add(0, xsdConcreteComponent);
          xsdDiagnostic.setNode(theNode);
        }
      }
    }

    public void validate(XSDConstrainingFacet xsdConstrainingFacet)
    {
      if (xsdConstrainingFacet != null &&
            !(xsdConstrainingFacet instanceof XSDPatternFacet ?
                ((XSDPatternFacet)xsdConstrainingFacet).isConstraintSatisfied(normalizedLiteral) :
                xsdConstrainingFacet.isConstraintSatisfied(value)))
      {
        XSDDiagnostic result = ((XSDConcreteComponentImpl)xsdConstrainingFacet).getXSDFactory().createXSDDiagnostic();
        result.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
        Object [] substitutions =  createSubstitutions(5);
        substitutions[3] = xsdConstrainingFacet.getLexicalValue();
        substitutions[4] = xsdConstrainingFacet.getSimpleTypeDefinition().getURI();

        String message = XSDPlugin.INSTANCE.getString("cvc-" + xsdConstrainingFacet.getFacetName() + "-valid", substitutions);
        if (node == null || node.getNodeType() == Node.TEXT_NODE)
        {
          message = requote(message);
        }
        result.setMessage(XSDPlugin.INSTANCE.getString("_UI_XSDError_message", new Object [] { message}));
        result.setAnnotationURI(XSDConstants.PART2 + "#" + xsdConstrainingFacet.getFacetName());
        result.setPrimaryComponent(xsdConstrainingFacet);
        result.setNode(node);

        if (diagnostics == null)
        {
          diagnostics = new ArrayList();
        }
        diagnostics.add(result);
      }
    }

    protected Object [] createSubstitutions(int length)
    {
      Object [] substitutions =  new Object [length];
      if (normalizedLiteral != null)
      {
        substitutions[0] = normalizedLiteral;
      }
      else if (literal != null)
      {
        substitutions[0] = literal;
      }
      else 
      {
        substitutions[0] = value;
      }

      if (node == null || node.getNodeType() == Node.TEXT_NODE)
      {
        substitutions[1] = "\000";
        substitutions[2] = "\001";
      }
      else if (node.getNodeType() == Node.ATTRIBUTE_NODE)
      {
        substitutions[1] =  XSDPlugin.INSTANCE.getString("attribute_noun");
        substitutions[2] = node.getLocalName();
      }
      else if (node.getNodeType() == Node.ELEMENT_NODE)
      {
        substitutions[1] =  XSDPlugin.INSTANCE.getString("element_noun");
        substitutions[2] = node.getLocalName();
      }

      return substitutions;
    }

    protected static String requote(String message)
    {
      StringBuffer stringBuffer = new StringBuffer(message);
      for (int i = stringBuffer.length() - 1;  i > 0; --i)
      {
        switch (stringBuffer.charAt(i))
        {
          case 0:
          {
            stringBuffer.replace(i, i + 1, "{0}");
            break;
          }
          case 1:
          {
            stringBuffer.replace(i, i + 1, "{1}");
            break;
          }
          case '\'':
          {
            stringBuffer.insert(i, '\'');
            break;
          }
          case '{': // }
          {
            stringBuffer.replace(i, i + 1, "'{'"); // }
            break;
          }
        }
      }
      return stringBuffer.toString();
    }

    public void reportDatatypeDiagnostic()
    {
      XSDDiagnostic result = xsdSimpleTypeDefinition.getXSDFactory().createXSDDiagnostic();
      result.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
      Object [] substitutions =  createSubstitutions(4);
      substitutions[3] = xsdSimpleTypeDefinition.getURI();
      XSDSimpleTypeDefinition primitiveTypeDefinition = xsdSimpleTypeDefinition.getPrimitiveTypeDefinition();
      String message = 
        XSDPlugin.INSTANCE.getString
          (primitiveTypeDefinition == null ? 
             "cvc-datatype-valid.1.2.3" : 
             "cvc-datatype-valid.1.2.1", 
           substitutions);
      if (node == null || node.getNodeType() == Node.TEXT_NODE)
      {
        message = requote(message);
      }
      result.setMessage(XSDPlugin.INSTANCE.getString("_UI_XSDError_message", new Object [] { message }));
      if (primitiveTypeDefinition != null)
      {
        result.setAnnotationURI(XSDConstants.PART2 + "#" + primitiveTypeDefinition.getName());
      }
      result.setPrimaryComponent(primitiveTypeDefinition);
      result.setNode(node);

      if (diagnostics == null)
      {
        diagnostics = new ArrayList();
      }
      diagnostics.add(result);
    }
  }

  public XSDSimpleTypeDefinition.Assessment assess(Node node)
  {
    AssessmentImpl assessment = new AssessmentImpl(node, 0);
    return assess(assessment);
  }

  public XSDSimpleTypeDefinition.Assessment assess(String literal)
  {
    AssessmentImpl assessment = new AssessmentImpl(literal, 0);
    return assess(assessment);
  }

  public AssessmentImpl assess(AssessmentImpl assessment)
  {
    assessment.xsdSimpleTypeDefinition = this;

    // Determine the literal from the node.
    //
    if (assessment.literal == null && assessment.node != null)
    {
      switch (assessment.node.getNodeType())
      {
        case Node.ATTRIBUTE_NODE:
        {
          assessment.literal = ((Attr)assessment.node).getValue();
          break;
        }
        case Node.ELEMENT_NODE:
        {
          Element element = (Element)assessment.node;
          for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling())
          {
            if (child.getNodeType() == Node.TEXT_NODE)
            {
              assessment.literal = ((Text)child).getData();
              break;
            }
          }
          break;
        }
        case Node.TEXT_NODE:
        {
          assessment.literal = ((Text)assessment.node).getData();
          break;
        }
      }
    }

    // Determine the normalized literal from the literal.
    //
    if (assessment.normalizedLiteral == null && assessment.literal != null)
    {
      assessment.normalizedLiteral = assessment.literal;
      XSDWhiteSpaceFacet effectiveWhiteSpaceFacet = getEffectiveWhiteSpaceFacet();
      if (effectiveWhiteSpaceFacet != null)
      {
        assessment.normalizedLiteral = effectiveWhiteSpaceFacet.getNormalizedLiteral(assessment.literal);
      }
    }

    if (!isCircular())
    {
      assessment.validate(getEffectivePatternFacet());

      switch (getVariety().getValue())
      {
        case XSDVariety.ATOMIC:
        {
          XSDSimpleTypeDefinition thePrimitiveTypeDefinition = getPrimitiveTypeDefinition();
          if (thePrimitiveTypeDefinition != null)
          {
            XSDAnySimpleType xsdAnySimpleType = XSDTypeRegister.getTypeImplementer(thePrimitiveTypeDefinition.getName());
            xsdAnySimpleType.assess(assessment);
            if (assessment.diagnostics == null)
            {
              assessment.validate(getEffectiveLengthFacet());
              assessment.validate(getEffectiveMinLengthFacet());
              assessment.validate(getEffectiveMaxLengthFacet());

              assessment.validate(getEffectiveTotalDigitsFacet());
              assessment.validate(getEffectiveFractionDigitsFacet());

              if (XSDOrdered.FALSE_LITERAL != getOrderedFacet().getValue())
              {
                assessment.validate(getEffectiveMinFacet());
                assessment.validate(getEffectiveMaxFacet());
              }
            }
          }
          break;
        }
        case XSDVariety.UNION:
        {
          boolean good = false;
          for (Iterator memberTypeDefinitions = getMemberTypeDefinitions().iterator(); memberTypeDefinitions.hasNext(); )
          {
            XSDSimpleTypeDefinition memberTypeDefinition = (XSDSimpleTypeDefinition)memberTypeDefinitions.next();
            AssessmentImpl nestedAssessment = new AssessmentImpl(assessment.node, assessment.yield);
            nestedAssessment.literal = nestedAssessment.normalizedLiteral = assessment.normalizedLiteral;

            ((XSDSimpleTypeDefinitionImpl)memberTypeDefinition).assess(nestedAssessment);

            if (nestedAssessment.diagnostics == null)
            {
              if (assessment.assessments == null)
              {
                assessment.assessments = new ArrayList();
              }
              assessment.assessments.add(nestedAssessment);
              assessment.value = nestedAssessment.value;
              good = true;
              break;
            }
          }

          if (!good)
          {
            assessment.reportDatatypeDiagnostic();
          }

          break;
        }
        case XSDVariety.LIST:
        {
          XSDSimpleTypeDefinition theItemTypeDefinition = getItemTypeDefinition();
          if (theItemTypeDefinition != null)
          {
            List list = new ArrayList();
            assessment.value = list;
            int length = 0;
            for (StringTokenizer tokens = new StringTokenizer(assessment.normalizedLiteral, " "); 
                 tokens.hasMoreTokens(); 
                 ++length)
            {
              AssessmentImpl nestedAssessment = new AssessmentImpl(assessment.node, assessment.yield);
              nestedAssessment.literal = nestedAssessment.normalizedLiteral = tokens.nextToken();
              ((XSDSimpleTypeDefinitionImpl)getItemTypeDefinition()).assess(nestedAssessment);
              if (nestedAssessment.value != null)
              {
                list.add(nestedAssessment.value);
              }
              if (assessment.assessments == null)
              {
                assessment.assessments = new ArrayList();
              }
              assessment.assessments.add(nestedAssessment);
            }

            assessment.validate(getEffectiveLengthFacet());
            assessment.validate(getEffectiveMinLengthFacet());
            assessment.validate(getEffectiveMaxLengthFacet());
          }
          break;
        }
      }

      assessment.validate(getEffectiveEnumerationFacet());
    }

    return assessment;
  }

  public boolean isValidLiteral(String literal)
  {
    AssessmentImpl assessment = new AssessmentImpl(literal, 0);
    assess(assessment);
    return assessment.diagnostics == null;
  }

  public Object getValue(String literal)
  {
    AssessmentImpl assessment = new AssessmentImpl(literal, 0);
    assess(assessment);
    return assessment.value;
  }

  public String getCanonicalLiteral(String literal)
  {
    AssessmentImpl assessment = new AssessmentImpl(literal, 0);
    assess(assessment);
    return assessment.normalizedLiteral;
  }

  public String getNormalizedLiteral(String literal)
  {
    String result = literal;
    XSDWhiteSpaceFacet effectiveWhiteSpaceFacet = getEffectiveWhiteSpaceFacet();
    if (effectiveWhiteSpaceFacet != null)
    {
      result = effectiveWhiteSpaceFacet.getNormalizedLiteral(result);
    }
    return result;
  }

  public boolean equalValues(Object value1, Object value2)
  {
    return value1 == null ? value2 == null : value1.equals(value2);
  }

  public boolean equalLiterals(String literal1, String literal2)
  {
    return equalValues(getValue(literal1), getValue(literal2));
  }

  public int compareValues(Object value1, Object value2)
  {
    if (XSDOrdered.FALSE_LITERAL != getOrderedFacet().getValue())
    {
      XSDSimpleTypeDefinition thePrimitiveTypeDefinition = getPrimitiveTypeDefinition();
      if (thePrimitiveTypeDefinition != null)
      {
        XSDAnySimpleType xsdAnySimpleType = 
          XSDTypeRegister.getTypeImplementer(thePrimitiveTypeDefinition.getName());
        return 
          value1 == null ?
             value2 == null ? 
               0 : 
               -1 :
             value2 == null ? 
               1 :
               xsdAnySimpleType.compareValues(value1, value2);
      }
    }
    return 0;
  }

  public int compareLiterals(String literal1, String literal2)
  {
    return compareValues(getValue(literal1), getValue(literal2));
  }

  public XSDTypeDefinition getBadTypeDerivation(XSDTypeDefinition xsdTypeDefinition, boolean extension, boolean restriction)
  {
    if (xsdTypeDefinition == this)
    {
      return null;
    }
    else if (!restriction || 
               getBaseTypeDefinition() == null || 
               getBaseTypeDefinition().getFinal().contains(XSDSimpleFinal.RESTRICTION_LITERAL))
    {
      return this;
    }
    else
    {
      XSDTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();
      XSDTypeDefinition result = theBaseTypeDefinition;
      if (theBaseTypeDefinition == xsdTypeDefinition)
      {
        return null;
      }
      else if (!XSDConstants.isURType(theBaseTypeDefinition))
      {
        result = theBaseTypeDefinition.getBadTypeDerivation(xsdTypeDefinition, extension, restriction);
        if (result == null)
        {
          return null;
        }
      }

      switch (getVariety().getValue())
      {
        case XSDVariety.LIST:
        case XSDVariety.UNION:
        {
          if (XSDConstants.isURType(xsdTypeDefinition))
          {
            return null;
          }
        }
      }

      if (xsdTypeDefinition instanceof XSDSimpleTypeDefinition)
      {
        XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)xsdTypeDefinition;
        if (xsdSimpleTypeDefinition.getVariety() == XSDVariety.UNION_LITERAL)
        {
          for (Iterator i = xsdSimpleTypeDefinition.getMemberTypeDefinitions().iterator(); i.hasNext(); )
          {
            XSDSimpleTypeDefinition memberTypeDefinition = (XSDSimpleTypeDefinition)i.next();
            XSDTypeDefinition memberResult = getBadTypeDerivation(memberTypeDefinition, extension, restriction);
            if (memberResult == null)
            {
              return null;
            }
          }
        }
      }
      else if (XSDConstants.isAnyType(xsdTypeDefinition))
      {
        return null;
      }

      return result;
    }
  }

  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDSimpleTypeDefinitionImpl clonedSimpleTypeDefinition =
      (XSDSimpleTypeDefinitionImpl)getXSDFactory().createXSDSimpleTypeDefinition();
    clonedSimpleTypeDefinition.isReconciling = true;

    XSDSimpleTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();

    if (getName() != null)
    {
      clonedSimpleTypeDefinition.setName(getName());
    }
    if (isSetLexicalFinal())
    {
      if (!getLexicalFinal().isEmpty())
      {
        clonedSimpleTypeDefinition.getLexicalFinal().addAll(getLexicalFinal());
      }
      else
      {
        clonedSimpleTypeDefinition.getLexicalFinal().clear();
      }
    }

    if (!(getContainer() instanceof XSDComplexTypeDefinition))
    {
      if (getItemTypeDefinition() != null && (theBaseTypeDefinition == null || XSDConstants.isURType(theBaseTypeDefinition)))
      {
        if (!getContents().contains(getItemTypeDefinition()))
        {
          XSDSimpleTypeDefinition theItemTypeDefinition = getItemTypeDefinition();
          clonedSimpleTypeDefinition.setItemTypeDefinition
            (createUnresolvedSimpleTypeDefinition
              (theItemTypeDefinition.getTargetNamespace(), theItemTypeDefinition.getName()));
        }
      }
      else if (!getMemberTypeDefinitions().isEmpty() && (theBaseTypeDefinition == null || XSDConstants.isURType(theBaseTypeDefinition)))
      {
        List unresolvedMembers = new ArrayList(getMemberTypeDefinitions().size());
        for (Iterator members = getMemberTypeDefinitions().iterator(); members.hasNext(); )
        {
          XSDSimpleTypeDefinition memberTypeDefinition = (XSDSimpleTypeDefinition)members.next();
          if (getContents().contains(memberTypeDefinition))
          {
            break;
          }
          else
          {
            unresolvedMembers.add
              (createUnresolvedSimpleTypeDefinition
                (memberTypeDefinition.getTargetNamespace(), memberTypeDefinition.getName()));
          }
        }
        if (!unresolvedMembers.isEmpty())
        {
          clonedSimpleTypeDefinition.getMemberTypeDefinitions().addAll(unresolvedMembers);
        }
      }
      else
      {
        if (getBaseTypeDefinition() != null && !getContents().contains(getBaseTypeDefinition()))
        {
          clonedSimpleTypeDefinition.setBaseTypeDefinition
            (createUnresolvedSimpleTypeDefinition
              (theBaseTypeDefinition.getTargetNamespace(), theBaseTypeDefinition.getName()));
        }
      }
    }

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedSimpleTypeDefinition.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
      if (getDerivationAnnotation() != null)
      {
        clonedSimpleTypeDefinition.setDerivationAnnotation((XSDAnnotation)getDerivationAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
      if (!getContents().isEmpty())
      {
        clonedSimpleTypeDefinition.getContents().addAll(cloneConcreteComponents(getContents(), deep, shareDOM));
        if (getItemTypeDefinition() != null && (theBaseTypeDefinition == null || XSDConstants.isURType(theBaseTypeDefinition)))
        {
          clonedSimpleTypeDefinition.setItemTypeDefinition((XSDSimpleTypeDefinition)clonedSimpleTypeDefinition.getContents().get(0));
        }
        else if (!getMemberTypeDefinitions().isEmpty() && (theBaseTypeDefinition == null || XSDConstants.isURType(theBaseTypeDefinition)))
        {
          clonedSimpleTypeDefinition.getMemberTypeDefinitions().addAll(clonedSimpleTypeDefinition.getContents());
        }
        else
        {
          clonedSimpleTypeDefinition.setBaseTypeDefinition((XSDSimpleTypeDefinition)clonedSimpleTypeDefinition.getContents().get(0));
        }
      }
      if (!getFacetContents().isEmpty())
      {
        clonedSimpleTypeDefinition.getFacetContents().addAll(cloneConcreteComponents(getFacetContents(), deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedSimpleTypeDefinition.setElement(getElement());
    }

    clonedSimpleTypeDefinition.isReconciling = shareDOM;
    return clonedSimpleTypeDefinition;
  }
}
