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
 * $Id: XSDSimpleTypeDefinitionImpl.java,v 1.36 2010/03/12 16:12:45 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
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
import org.eclipse.xsd.XSDComponent;
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
   * The offset of the flags representing the value of the '{@link #getVariety() <em>Variety</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int VARIETY_EFLAG_OFFSET = 8;

  /**
   * The flags representing the default value of the '{@link #getVariety() <em>Variety</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int VARIETY_EFLAG_DEFAULT = VARIETY_EDEFAULT.ordinal() << VARIETY_EFLAG_OFFSET;

  /**
   * The array of enumeration values for '{@link XSDVariety Variety}'
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  private static final XSDVariety[] VARIETY_EFLAG_VALUES = XSDVariety.values();

  /**
   * The flags representing the value of the '{@link #getVariety() <em>Variety</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariety()
   * @generated
   * @ordered
   */
  protected static final int VARIETY_EFLAG = 0x3 << VARIETY_EFLAG_OFFSET;

  /**
   * The flag representing whether the Variety attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int VARIETY_ESETFLAG = 1 << 10;

  /**
   * The cached value of the '{@link #getFinal() <em>Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFinal()
   * @generated
   * @ordered
   */
  protected EList<XSDSimpleFinal> final_;

  /**
   * The cached value of the '{@link #getLexicalFinal() <em>Lexical Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLexicalFinal()
   * @generated
   * @ordered
   */
  protected EList<XSDSimpleFinal> lexicalFinal;

  /**
   * The cached value of the '{@link #getValidFacets() <em>Valid Facets</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValidFacets()
   * @generated
   * @ordered
   */
  protected EList<String> validFacets;

  /**
   * The cached value of the '{@link #getContents() <em>Contents</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContents()
   * @generated
   * @ordered
   */
  protected EList<XSDSimpleTypeDefinition> contents;

  /**
   * The cached value of the '{@link #getFacetContents() <em>Facet Contents</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFacetContents()
   * @generated NOT
   * @ordered
   */
  protected EcoreEList<XSDConstrainingFacet> facetContents = null;

  /**
   * The cached value of the '{@link #getFacets() <em>Facets</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFacets()
   * @generated NOT
   * @ordered
   */
  protected EcoreEList<XSDConstrainingFacet> facets = null;

  /**
   * The cached value of the '{@link #getMemberTypeDefinitions() <em>Member Type Definitions</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMemberTypeDefinitions()
   * @generated
   * @ordered
   */
  protected EList<XSDSimpleTypeDefinition> memberTypeDefinitions;

  /**
   * The cached value of the '{@link #getFundamentalFacets() <em>Fundamental Facets</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFundamentalFacets()
   * @generated
   * @ordered
   */
  protected EList<XSDFundamentalFacet> fundamentalFacets;

  /**
   * The cached value of the '{@link #getBaseTypeDefinition() <em>Base Type Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBaseTypeDefinition()
   * @generated
   * @ordered
   */
  protected XSDSimpleTypeDefinition baseTypeDefinition;

  /**
   * The cached value of the '{@link #getPrimitiveTypeDefinition() <em>Primitive Type Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrimitiveTypeDefinition()
   * @generated
   * @ordered
   */
  protected XSDSimpleTypeDefinition primitiveTypeDefinition;

  /**
   * The cached value of the '{@link #getItemTypeDefinition() <em>Item Type Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getItemTypeDefinition()
   * @generated
   * @ordered
   */
  protected XSDSimpleTypeDefinition itemTypeDefinition;

  /**
   * The cached value of the '{@link #getSyntheticFacets() <em>Synthetic Facets</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSyntheticFacets()
   * @generated
   * @ordered
   */
  protected EList<XSDFacet> syntheticFacets;

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
  @Override
  protected EClass eStaticClass()
  {
    return XSDPackage.Literals.XSD_SIMPLE_TYPE_DEFINITION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDVariety getVariety()
  {
    return VARIETY_EFLAG_VALUES[(eFlags & VARIETY_EFLAG) >>> VARIETY_EFLAG_OFFSET];
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVariety(XSDVariety newVariety)
  {
    XSDVariety oldVariety = VARIETY_EFLAG_VALUES[(eFlags & VARIETY_EFLAG) >>> VARIETY_EFLAG_OFFSET];
    if (newVariety == null) newVariety = VARIETY_EDEFAULT;
    eFlags = eFlags & ~VARIETY_EFLAG | newVariety.ordinal() << VARIETY_EFLAG_OFFSET;
    boolean oldVarietyESet = (eFlags & VARIETY_ESETFLAG) != 0;
    eFlags |= VARIETY_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__VARIETY, oldVariety, newVariety, !oldVarietyESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetVariety()
  {
    XSDVariety oldVariety = VARIETY_EFLAG_VALUES[(eFlags & VARIETY_EFLAG) >>> VARIETY_EFLAG_OFFSET];
    boolean oldVarietyESet = (eFlags & VARIETY_ESETFLAG) != 0;
    eFlags = eFlags & ~VARIETY_EFLAG | VARIETY_EFLAG_DEFAULT;
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
  public EList<XSDSimpleFinal> getFinal()
  {
    if (final_ == null)
    {
      final_ = new EDataTypeUniqueEList<XSDSimpleFinal>(XSDSimpleFinal.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FINAL);
    }
    return final_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDSimpleFinal> getLexicalFinal()
  {
    if (lexicalFinal == null)
    {
      lexicalFinal = new EDataTypeUniqueEList.Unsettable<XSDSimpleFinal>(XSDSimpleFinal.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__LEXICAL_FINAL);
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
  public EList<String> getValidFacets()
  {
    if (validFacets == null)
    {
      validFacets = new EDataTypeUniqueEList<String>(String.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__VALID_FACETS);
    }
    return validFacets;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDSimpleTypeDefinition> getContents()
  {
    if (contents == null)
    {
      contents = new EObjectContainmentEList<XSDSimpleTypeDefinition>(XSDSimpleTypeDefinition.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__CONTENTS);
    }
    return contents;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDConstrainingFacet> getFacetContents()
  {
    if (facetContents == null)
    {
      facetContents = new EObjectContainmentEList<XSDConstrainingFacet>(XSDConstrainingFacet.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FACET_CONTENTS);
    }
    return facetContents;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDConstrainingFacet> getFacets()
  {
    if (facets == null)
    {
      facets = new EObjectEList<XSDConstrainingFacet>(XSDConstrainingFacet.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FACETS);
    }
    return facets;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDSimpleTypeDefinition> getMemberTypeDefinitions()
  {
    if (memberTypeDefinitions == null)
    {
      memberTypeDefinitions = new EObjectEList<XSDSimpleTypeDefinition>(XSDSimpleTypeDefinition.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MEMBER_TYPE_DEFINITIONS);
    }
    return memberTypeDefinitions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDFundamentalFacet> getFundamentalFacets()
  {
    if (fundamentalFacets == null)
    {
      fundamentalFacets = new EObjectContainmentEList<XSDFundamentalFacet>(XSDFundamentalFacet.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FUNDAMENTAL_FACETS);
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

  @Override
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

  @Override
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
            if ((forceResolve || newItemTypeDefinition.getContainer() != null) && newItemTypeDefinition != theItemTypeDefinition)
            {
              setItemTypeDefinition(newItemTypeDefinition);
            }
          }
        }
        else
        {
          for (ListIterator<XSDSimpleTypeDefinition> theMemberTypeDefinitions = getMemberTypeDefinitions().listIterator(); 
               theMemberTypeDefinitions.hasNext(); )
          {
            XSDSimpleTypeDefinition theMemberTypeDefinition = theMemberTypeDefinitions.next();
            if (forceResolve || theMemberTypeDefinition.getContainer() == null)
            {
              XSDSimpleTypeDefinition newMemberTypeDefinition = 
                resolveSimpleTypeDefinition(theMemberTypeDefinition.getTargetNamespace(), theMemberTypeDefinition.getName());
              if ((forceResolve || newMemberTypeDefinition.getContainer() != null) && newMemberTypeDefinition != theMemberTypeDefinition)
              {
                theMemberTypeDefinitions.set(newMemberTypeDefinition);
              }
            }
          }
        }
      }
    }

    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      List<XSDSimpleFinal> newFinal = new ArrayList<XSDSimpleFinal>();
      if (!isSetLexicalFinal())
      {
        for (XSDProhibitedSubstitutions value : xsdSchema.getFinalDefault())
        {
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
        for (XSDSimpleFinal value : getLexicalFinal())
        {
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

      Collection<XSDSimpleFinal> oldFinal = new ArrayList<XSDSimpleFinal>(getFinal());
      oldFinal.removeAll(newFinal);
      if (!oldFinal.isEmpty())
      {
        getFinal().removeAll(oldFinal);
      }
      setListContentAndOrder(getFinal(), newFinal);
    }
  }

  protected XSDWhiteSpaceFacet effectiveWhiteSpaceFacet;
  @Override
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

    List<XSDConstrainingFacet> newFacets = null;
    boolean newBounded = false;
    XSDCardinality newCardinality = XSDCardinality.FINITE_LITERAL;
    boolean newNumeric = false;
    XSDOrdered newOrdered = XSDOrdered.FALSE_LITERAL;
    XSDSimpleTypeDefinition newPrimitiveTypeDefinition = null;
    XSDVariety newVariety = null;
    EList<String> newValidFacets = null;

    if (theBaseTypeDefinition != null && theBaseTypeDefinition.getContainer() != null)
    {
      if (!((XSDConcreteComponentImpl)theBaseTypeDefinition).analyze() && !XSDConstants.isURType(theBaseTypeDefinition) && theBaseTypeDefinition.isCircular())
      {
        analysisState = CIRCULAR;
      }
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
            List<XSDSimpleTypeDefinition> newMemberTypeDefinitions = theBaseTypeDefinition.getMemberTypeDefinitions();
            List<XSDSimpleTypeDefinition> remainingMemberTypeDefinitions = new ArrayList<XSDSimpleTypeDefinition>(getMemberTypeDefinitions());
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
          newFacets = new ArrayList<XSDConstrainingFacet>(getFacetContents());
        }
      }
      else
      {
        newFacets = new ArrayList<XSDConstrainingFacet>(getFacetContents());
      }
    }
    else
    {
      newFacets = new ArrayList<XSDConstrainingFacet>(getFacetContents());
    }

    if (theBaseTypeDefinition == null || XSDConstants.isURType(theBaseTypeDefinition))
    {
      XSDSimpleTypeDefinition theItemTypeDefinition = getItemTypeDefinition();
      if (theItemTypeDefinition != null && theItemTypeDefinition.getContainer() != null)
      {
        newVariety = XSDVariety.LIST_LITERAL;
        newPrimitiveTypeDefinition = null;
        ((XSDTypeDefinitionImpl)theItemTypeDefinition).analyze();
        if (!((XSDConcreteComponentImpl)theItemTypeDefinition).analyze() && !XSDConstants.isURType(theItemTypeDefinition) && theItemTypeDefinition.isCircular())
        {
          analysisState = CIRCULAR;
        }
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
        for (XSDSimpleTypeDefinition theMemberTypeDefinition : getMemberTypeDefinitions())
        {
          if (theMemberTypeDefinition.getContainer() != null)
          {
            if (!((XSDConcreteComponentImpl)theMemberTypeDefinition).analyze() && !XSDConstants.isURType(theMemberTypeDefinition) && theMemberTypeDefinition.isCircular())
            {
              analysisState = CIRCULAR;
            }
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

    XSDAnnotation theAnnotation = getAnnotation();
    if (theAnnotation != null)
    {
      for (Element applicationInformationElement :  theAnnotation.getApplicationInformation())
      {
        for (Node child = applicationInformationElement.getFirstChild(); child != null; child = child.getNextSibling())
        {
          switch (XSDConstants.hfpNodeType(child))
          {
            case XSDConstants.HFP_HASFACET_ELEMENT:
            {
              Element childElement = (Element)child;
              String facetName = childElement.getAttributeNS(null, XSDConstants.HFP_NAME_ATTRIBUTE);
              if (newValidFacets == null)
              {
                newValidFacets = new BasicEList<String>();
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
      List<XSDFacet> remainingFacets = new ArrayList<XSDFacet>(getFacets());
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
      List<String> remainingValidFacets = new ArrayList<String>(getValidFacets());
      remainingValidFacets.removeAll(newValidFacets);
      getValidFacets().removeAll(remainingValidFacets);
      if (!newValidFacets.isEmpty())
      {
        setListContentAndOrder(getValidFacets(), newValidFacets);
      }
    }
  }

  @Override
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

              // This is allowed https://bugs.eclipse.org/bugs/show_bug.cgi?id=177035
              //
              // if (childElement.hasAttributeNS(null, XSDConstants.BASE_ATTRIBUTE) && !getContents().isEmpty())
              // {
              //   createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-restriction-base-or-simpleType");
              // }

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

              if (childElement.hasAttributeNS(null, XSDConstants.BASE_ATTRIBUTE)) 
              {
                if (!getContents().isEmpty())
                {
                  createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-restriction-base-or-simpleType");
                }
              }
              else if (getContents().isEmpty())
              {
                createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-restriction-base-or-simpleType.0");
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

              if (childElement.hasAttributeNS(null, XSDConstants.ITEMTYPE_ATTRIBUTE))
              {
                if (!getContents().isEmpty())
                {
                  createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-list-itemType-or-simpleType");
                }
              }
              else
              {
                if (getContents().isEmpty())
                {
                  createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-list-itemType-or-simpleType.0");
                }
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
            for (XSDSimpleTypeDefinition theMemberTypeDefinition : getMemberTypeDefinitions())
            {
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
              // Apparently this is allowed now.
              // else if (!XSDConstants.isSchemaForSchemaNamespace(getTargetNamespace()) &&
              //           XSDConstants.isAnySimpleType(theMemberTypeDefinition))
              // {
              //   createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cos-st-retricts.0.2", theMemberTypeDefinition.getURI());
              // }
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

  @Override
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
        List<XSDSimpleTypeDefinition> newMemberTypeDefinitions = new ArrayList<XSDSimpleTypeDefinition>();
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

        List<XSDSimpleTypeDefinition> remainingMemberTypeDefinitions = new ArrayList<XSDSimpleTypeDefinition>(getMemberTypeDefinitions());
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
      XSDComplexTypeDefinitionImpl xsdComplexTypeDefinition = (XSDComplexTypeDefinitionImpl)getContainer();
      boolean oldIsReconciling = xsdComplexTypeDefinition.isReconciling;
      xsdComplexTypeDefinition.isReconciling = isReconciling;
      xsdComplexTypeDefinition.handleNewBaseTypeDefinition(newComplexBaseTypeDefinition);
      xsdComplexTypeDefinition.isReconciling = oldIsReconciling;
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

    if (eContainer instanceof XSDRedefine)
    {
      XSDSchema redefinedSchema = ((XSDRedefine)eContainer).getIncorporatedSchema();
      if (redefinedSchema != null)
      {
        Map<XSDComponent, XSDComponent> redefinitionMap = ((XSDSchemaImpl)redefinedSchema).getRedefinitionMap();
        if (redefinitionMap.containsKey(newBaseTypeDefinition))
        {
          XSDComponent replacement = redefinitionMap.get(this);
          if (replacement != null)
          {
            newBaseTypeDefinition = (XSDSimpleTypeDefinition)replacement;
          }
        }
      }
    }

    if (newBaseTypeDefinition != getBaseTypeDefinition())
    {
      setBaseTypeDefinition(newBaseTypeDefinition);
    }

    return newBaseTypeDefinition;
  }

  @Override
  protected Node getAdoptionParentNode(EReference eReference)
  {
    if (eReference == XSDPackage.Literals.XSD_SIMPLE_TYPE_DEFINITION__FACET_CONTENTS ||
          eReference == XSDPackage.Literals.XSD_SIMPLE_TYPE_DEFINITION__CONTENTS ||
          eReference == XSDPackage.Literals.XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION) 
    {
      Element element = getElement();
      if (element == null)
      {
        return null;
      }
      else
      {
        for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling())
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
    }

    return super.getAdoptionParentNode(eReference);
  }

  @Override
  protected Collection<Element> getContentNodes(Element changedElement)
  {
    Collection<Element> result = new ArrayList<Element>();
    for (Node child = getElement().getFirstChild(); child != null; child = child.getNextSibling())
    {
      switch (XSDConstants.nodeType(child))
      {
        case XSDConstants.ANNOTATION_ELEMENT:
        {
          result.add((Element)child);
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
              result.add((Element)grandChild);
            }
          }
          break;
        }
      }
    }
    return result;
  }

  @Override
  protected void handleUnreconciledElement(Element child, List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
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

  @Override
  protected void handleReconciliation(List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    if (!newContents.isEmpty() && newContents.get(0).getElement().getParentNode() == getElement())
    {
      handleAnnotationReconciliation(XSDPackage.Literals.XSD_TYPE_DEFINITION__ANNOTATION, newContents, remainingContents);
    }
    handleAnnotationReconciliation(XSDPackage.Literals.XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION, newContents, remainingContents);

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
            XSDConcreteComponent xsdConcreteComponent = newContents.get(0);
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
          List<XSDSimpleTypeDefinition> newMemberTypeDefinitions = new ArrayList<XSDSimpleTypeDefinition>();
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

          List<XSDSimpleTypeDefinition> newTypeContents = new ArrayList<XSDSimpleTypeDefinition>();
          for (ListIterator<XSDConcreteComponent> i = newContents.listIterator(); i.hasNext(); )
          {
            XSDConcreteComponent xsdConcreteComponent = i.next();
            if (xsdConcreteComponent instanceof XSDSimpleTypeDefinition)
            {
              XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)xsdConcreteComponent;
              newTypeContents.add(xsdSimpleTypeDefinition);
              newMemberTypeDefinitions.add(xsdSimpleTypeDefinition);
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

          List<XSDSimpleTypeDefinition> remainingMemberTypeDefinitions = new ArrayList<XSDSimpleTypeDefinition>(getMemberTypeDefinitions());
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
            XSDConcreteComponent xsdConcreteComponent = newContents.get(0);
            if (xsdConcreteComponent instanceof XSDSimpleTypeDefinition)
            {
              XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)xsdConcreteComponent;
              List<XSDSimpleTypeDefinition> theContents = getContents();
              if (theContents.size() != 1 || theContents.get(0) != xsdConcreteComponent)
              {
                if (!theContents.isEmpty())
                {
                  remainingContents.removeAll(theContents);
                  theContents.clear();
                }
                theContents.add(xsdSimpleTypeDefinition);
              }
              newBaseTypeDefinition = xsdSimpleTypeDefinition;
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
      for (Iterator<?> i = newContents.iterator(); i.hasNext(); )
      {
        if (!(i.next() instanceof XSDFacet))
        {
          i.remove();
        }
      }
      @SuppressWarnings("unchecked") List<XSDConstrainingFacet> list = (List<XSDConstrainingFacet>)(List<?>)newContents;
      setListContentAndOrder(getFacetContents(), list);
    }

    handleNewBaseTypeDefinition(newBaseTypeDefinition);
    handleNewComplexBaseTypeDefinition(newComplexBaseTypeDefinition);
  }

  @Override
  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    Element theElement = getElement();
    if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_SIMPLE_TYPE_DEFINITION__LEXICAL_FINAL)
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
    if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_SIMPLE_TYPE_DEFINITION__VARIETY)
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

  @Override
  protected void changeReference(EReference eReference)
  {
    super.changeReference(eReference);
    Element theElement = getElement();
    if (theElement != null)
    {
      if (eReference == null || eReference == XSDPackage.Literals.XSD_SIMPLE_TYPE_DEFINITION__BASE_TYPE_DEFINITION)
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
      if (eReference == null || eReference == XSDPackage.Literals.XSD_SIMPLE_TYPE_DEFINITION__ITEM_TYPE_DEFINITION)
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
      if (eReference == null || eReference == XSDPackage.Literals.XSD_SIMPLE_TYPE_DEFINITION__MEMBER_TYPE_DEFINITIONS)
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
                for (XSDSimpleTypeDefinition theMemberTypeDefinition : getMemberTypeDefinitions())
                {
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

  @Override
  protected void adoptContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.adoptContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_SIMPLE_TYPE_DEFINITION__CONTENTS ||
          eReference == XSDPackage.Literals.XSD_SIMPLE_TYPE_DEFINITION__FACET_CONTENTS)
    {
      traverseToRootForPatching();
    }
    else if (eReference == XSDPackage.Literals.XSD_TYPE_DEFINITION__ANNOTATION)
    {
      getAnnotations().add(0, (XSDAnnotation)xsdConcreteComponent);
    }
    else if (eReference == XSDPackage.Literals.XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION)
    {
      getAnnotations().add((XSDAnnotation)xsdConcreteComponent);
    }
  }

  @Override
  protected void orphanContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.orphanContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_SIMPLE_TYPE_DEFINITION__CONTENTS || 
          eReference == XSDPackage.Literals.XSD_SIMPLE_TYPE_DEFINITION__FACET_CONTENTS)
    {
      traverseToRootForPatching();
    }
    else if (eReference == XSDPackage.Literals.XSD_TYPE_DEFINITION__ANNOTATION ||
               eReference == XSDPackage.Literals.XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION)
    {
      getAnnotations().remove(xsdConcreteComponent);
    }
  }

  public XSDTotalDigitsFacet getTotalDigitsFacet()
  {
    if (facetContents != null)
    {
      Object [] facets = facetContents.data();
      for (int i = 0, size = facetContents.size(); i < size; ++i)
      {
        Object facet = facets[i];
        if (facet instanceof XSDTotalDigitsFacet)
        {
          return (XSDTotalDigitsFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDTotalDigitsFacet getEffectiveTotalDigitsFacet()
  {
    if (facets != null)
    {
      Object [] facetData = facets.data();
      for (int i = 0, size = facets.size(); i < size; ++i)
      {
        Object facet = facetData[i];
        if (facet instanceof XSDTotalDigitsFacet)
        {
          return (XSDTotalDigitsFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDFractionDigitsFacet getFractionDigitsFacet()
  {
    if (facetContents != null)
    {
      Object [] facets = facetContents.data();
      for (int i = 0, size = facetContents.size(); i < size; ++i)
      {
        Object facet = facets[i];
        if (facet instanceof XSDFractionDigitsFacet)
        {
          return (XSDFractionDigitsFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDFractionDigitsFacet getEffectiveFractionDigitsFacet()
  {
    if (facets != null)
    {
      Object [] facetData = facets.data();
      for (int i = 0, size = facets.size(); i < size; ++i)
      {
        Object facet = facetData[i];
        if (facet instanceof XSDFractionDigitsFacet)
        {
          return (XSDFractionDigitsFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDLengthFacet getLengthFacet()
  {
    if (facetContents != null)
    {
      Object [] facets = facetContents.data();
      for (int i = 0, size = facetContents.size(); i < size; ++i)
      {
        Object facet = facets[i];
        if (facet instanceof XSDLengthFacet)
        {
          return (XSDLengthFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDLengthFacet getEffectiveLengthFacet()
  {
    if (facets != null)
    {
      Object [] facetData = facets.data();
      for (int i = 0, size = facets.size(); i < size; ++i)
      {
        Object facet = facetData[i];
        if (facet instanceof XSDLengthFacet)
        {
          return (XSDLengthFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDMaxLengthFacet getMaxLengthFacet()
  {
    if (facetContents != null)
    {
      Object [] facets = facetContents.data();
      for (int i = 0, size = facetContents.size(); i < size; ++i)
      {
        Object facet = facets[i];
        if (facet instanceof XSDMaxLengthFacet)
        {
          return (XSDMaxLengthFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDMaxLengthFacet getEffectiveMaxLengthFacet()
  {
    if (facets != null)
    {
      Object [] facetData = facets.data();
      for (int i = 0, size = facets.size(); i < size; ++i)
      {
        Object facet = facetData[i];
        if (facet instanceof XSDMaxLengthFacet)
        {
          return (XSDMaxLengthFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDMinLengthFacet getMinLengthFacet()
  {
    if (facetContents != null)
    {
      Object [] facets = facetContents.data();
      for (int i = 0, size = facetContents.size(); i < size; ++i)
      {
        Object facet = facets[i];
        if (facet instanceof XSDMinLengthFacet)
        {
          return (XSDMinLengthFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDMinLengthFacet getEffectiveMinLengthFacet()
  {
    if (facets != null)
    {
      Object [] facetData = facets.data();
      for (int i = 0, size = facets.size(); i < size; ++i)
      {
        Object facet = facetData[i];
        if (facet instanceof XSDMinLengthFacet)
        {
          return (XSDMinLengthFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDMaxExclusiveFacet getMaxExclusiveFacet()
  {
    if (facetContents != null)
    {
      Object [] facets = facetContents.data();
      for (int i = 0, size = facetContents.size(); i < size; ++i)
      {
        Object facet = facets[i];
        if (facet instanceof XSDMaxExclusiveFacet)
        {
          return (XSDMaxExclusiveFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDMaxInclusiveFacet getMaxInclusiveFacet()
  {
    if (facetContents != null)
    {
      Object [] facets = facetContents.data();
      for (int i = 0, size = facetContents.size(); i < size; ++i)
      {
        Object facet = facets[i];
        if (facet instanceof XSDMaxInclusiveFacet)
        {
          return (XSDMaxInclusiveFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDMaxFacet getMaxFacet()
  {
    if (facetContents != null)
    {
      Object [] facets = facetContents.data();
      for (int i = 0, size = facetContents.size(); i < size; ++i)
      {
        Object facet = facets[i];
        if (facet instanceof XSDMaxFacet)
        {
          return (XSDMaxFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDMaxFacet getEffectiveMaxFacet()
  {
    if (facets != null)
    {
      Object [] facetData = facets.data();
      for (int i = 0, size = facets.size(); i < size; ++i)
      {
        Object facet = facetData[i];
        if (facet instanceof XSDMaxFacet)
        {
          return (XSDMaxFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDMinExclusiveFacet getMinExclusiveFacet()
  {
    if (facetContents != null)
    {
      Object [] facets = facetContents.data();
      for (int i = 0, size = facetContents.size(); i < size; ++i)
      {
        Object facet = facets[i];
        if (facet instanceof XSDMinExclusiveFacet)
        {
          return (XSDMinExclusiveFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDMinInclusiveFacet getMinInclusiveFacet()
  {
    if (facetContents != null)
    {
      Object [] facets = facetContents.data();
      for (int i = 0, size = facetContents.size(); i < size; ++i)
      {
        Object facet = facets[i];
        if (facet instanceof XSDMinInclusiveFacet)
        {
          return (XSDMinInclusiveFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDMinFacet getMinFacet()
  {
    if (facetContents != null)
    {
      Object [] facets = facetContents.data();
      for (int i = 0, size = facetContents.size(); i < size; ++i)
      {
        Object facet = facets[i];
        if (facet instanceof XSDMinFacet)
        {
          return (XSDMinFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDMinFacet getEffectiveMinFacet()
  {
    if (facets != null)
    {
      Object [] facetData = facets.data();
      for (int i = 0, size = facets.size(); i < size; ++i)
      {
        Object facet = facetData[i];
        if (facet instanceof XSDMinFacet)
        {
          return (XSDMinFacet)facet;
        }
      }
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDFacet> getSyntheticFacets()
  {
    if (syntheticFacets == null)
    {
      syntheticFacets = new EObjectContainmentEList<XSDFacet>(XSDFacet.class, this, XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__SYNTHETIC_FACETS);
    }
    return syntheticFacets;
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
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__CONTENTS:
        return ((InternalEList<?>)getContents()).basicRemove(otherEnd, msgs);
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FACET_CONTENTS:
        return ((InternalEList<?>)getFacetContents()).basicRemove(otherEnd, msgs);
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FUNDAMENTAL_FACETS:
        return ((InternalEList<?>)getFundamentalFacets()).basicRemove(otherEnd, msgs);
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__SYNTHETIC_FACETS:
        return ((InternalEList<?>)getSyntheticFacets()).basicRemove(otherEnd, msgs);
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
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__VARIETY:
        setVariety((XSDVariety)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FINAL:
        getFinal().clear();
        getFinal().addAll((Collection<? extends XSDSimpleFinal>)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__LEXICAL_FINAL:
        getLexicalFinal().clear();
        getLexicalFinal().addAll((Collection<? extends XSDSimpleFinal>)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__VALID_FACETS:
        getValidFacets().clear();
        getValidFacets().addAll((Collection<? extends String>)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__CONTENTS:
        getContents().clear();
        getContents().addAll((Collection<? extends XSDSimpleTypeDefinition>)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FACET_CONTENTS:
        getFacetContents().clear();
        getFacetContents().addAll((Collection<? extends XSDConstrainingFacet>)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FACETS:
        getFacets().clear();
        getFacets().addAll((Collection<? extends XSDConstrainingFacet>)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__MEMBER_TYPE_DEFINITIONS:
        getMemberTypeDefinitions().clear();
        getMemberTypeDefinitions().addAll((Collection<? extends XSDSimpleTypeDefinition>)newValue);
        return;
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION__FUNDAMENTAL_FACETS:
        getFundamentalFacets().clear();
        getFundamentalFacets().addAll((Collection<? extends XSDFundamentalFacet>)newValue);
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
        getSyntheticFacets().addAll((Collection<? extends XSDFacet>)newValue);
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
    result.append(" (variety: ");
    if ((eFlags & VARIETY_ESETFLAG) != 0) result.append(VARIETY_EFLAG_VALUES[(eFlags & VARIETY_EFLAG) >>> VARIETY_EFLAG_OFFSET]); else result.append("<unset>");
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
    if (facetContents != null)
    {
      Object [] facets = facetContents.data();
      for (int i = 0, size = facetContents.size(); i < size; ++i)
      {
        Object facet = facets[i];
        if (facet instanceof XSDWhiteSpaceFacet)
        {
          return (XSDWhiteSpaceFacet)facet;
        }
      }
    }
    return null;
  }

  public XSDWhiteSpaceFacet getEffectiveWhiteSpaceFacet()
  {
    if (facets != null)
    {
      Object [] facetData = facets.data();
      for (int i = 0, size = facets.size(); i < size; ++i)
      {
        Object facet = facetData[i];
        if (facet instanceof XSDWhiteSpaceFacet)
        {
          return (XSDWhiteSpaceFacet)facet;
        }
      }
    }
    return null;
  }

  public EList<XSDEnumerationFacet> getEnumerationFacets()
  {
    EList<XSDEnumerationFacet> result = new BasicEList<XSDEnumerationFacet>();
    if (facetContents != null)
    {
      Object [] facets = facetContents.data();
      for (int i = 0, size = facetContents.size(); i < size; ++i)
      {
        Object facet = facets[i];
        if (facet instanceof XSDEnumerationFacet)
        {
          result.add((XSDEnumerationFacet)facet);
        }
      }
    }
    return 
      new EcoreEList.UnmodifiableEList.FastCompare<XSDEnumerationFacet>
        (this, XSDPackage.Literals.XSD_SIMPLE_TYPE_DEFINITION__ENUMERATION_FACETS, result.size(), result.toArray());
  }

  public XSDEnumerationFacet getEffectiveEnumerationFacet()
  {
    if (facets != null)
    {
      Object [] facetData = facets.data();
      for (int i = 0, size = facets.size(); i < size; ++i)
      {
        Object facet = facetData[i];
        if (facet instanceof XSDEnumerationFacet)
        {
          return (XSDEnumerationFacet)facet;
        }
      }
    }
    return null;
  }

  public EList<XSDPatternFacet> getPatternFacets()
  {
    EList<XSDPatternFacet> result = new BasicEList<XSDPatternFacet>();
    if (facetContents != null)
    {
      Object [] facets = facetContents.data();
      for (int i = 0, size = facetContents.size(); i < size; ++i)
      {
        Object facet = facets[i];
        if (facet instanceof XSDPatternFacet)
        {
          result.add((XSDPatternFacet)facet);
        }
      }
    }
    return 
      new EcoreEList.UnmodifiableEList.FastCompare<XSDPatternFacet>
        (this, XSDPackage.Literals.XSD_SIMPLE_TYPE_DEFINITION__PATTERN_FACETS, result.size(), result.toArray());
  }

  public XSDPatternFacet getEffectivePatternFacet()
  {
    if (facets != null)
    {
      Object [] facetData = facets.data();
      for (int i = 0, size = facets.size(); i < size; ++i)
      {
        Object facet = facetData[i];
        if (facet instanceof XSDPatternFacet)
        {
          return (XSDPatternFacet)facet;
        }
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
    List<XSDFundamentalFacet> theFundamentalFacets = getFundamentalFacets();
    boundedFacet = getXSDFactory().createXSDBoundedFacet();
    cardinalityFacet = getXSDFactory().createXSDCardinalityFacet();
    numericFacet = getXSDFactory().createXSDNumericFacet();
    orderedFacet = getXSDFactory().createXSDOrderedFacet();
    List<XSDFundamentalFacet> list = new ArrayList<XSDFundamentalFacet>(4);
    list.add(boundedFacet);
    list.add(cardinalityFacet);
    list.add(numericFacet);
    list.add(orderedFacet);
    theFundamentalFacets.addAll(list);
  }

  protected static EList<String> validFacetsForList;
  public EList<String> getValidFacetsForList()
  {
    if (validFacetsForList == null)
    {
      validFacetsForList = new BasicEList<String>();
      validFacetsForList.add("length");
      validFacetsForList.add("maxLength");
      validFacetsForList.add("minLength");
      validFacetsForList.add("whiteSpace");
      validFacetsForList.add("enumeration");
      validFacetsForList.add("pattern");
    }
    return validFacetsForList;
  }

  protected static EList<String> validFacetsForUnion;
  public EList<String> getValidFacetsForUnion()
  {
    if (validFacetsForUnion == null)
    {
      validFacetsForUnion = new BasicEList<String>();
      validFacetsForUnion.add("enumeration");
      validFacetsForUnion.add("pattern");
    }
    return validFacetsForUnion;
  }

  @Override
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

  @Override
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

  @Override
  public XSDSimpleTypeDefinition getSimpleType()
  {
    return this;
  }

  @Override
  public XSDParticle getComplexType()
  {
    return null;
  }

  protected XSDEnumerationFacetImpl effectiveEnumerationFacet;
  protected XSDPatternFacetImpl effectivePatternFacet;

  protected List<XSDConstrainingFacet> mergeFacets()
  {
    List<XSDConstrainingFacet> result = new ArrayList<XSDConstrainingFacet>();

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

    List<XSDEnumerationFacet> enumerationFacets = getEnumerationFacets();
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
      List<Object> newValue = new UniqueEList<Object>();
      List<XSDAnnotation> newAnnotations = new ArrayList<XSDAnnotation>();

      for (XSDEnumerationFacet enumerationFacet : enumerationFacets)
      {
        XSDAnnotation xsdAnnotation = enumerationFacet.getAnnotation();
        if (xsdAnnotation != null)
        {
          newAnnotations.add(xsdAnnotation);
        }

        if (newValue.addAll(enumerationFacet.getValue()))
        {
          if (newLexicalValue.length() != 0)
          {
            newLexicalValue.append(", ");
          }
          newLexicalValue.append(enumerationFacet.getLexicalValue());
        }
      }
      String newLexicalValueString = newLexicalValue.toString();

      List<Object> remainingValues = new ArrayList<Object>(effectiveEnumerationFacet.getValue());
      remainingValues.removeAll(newValue);
      if (!remainingValues.isEmpty())
      {
        effectiveEnumerationFacet.getValue().removeAll(remainingValues);
      }
      if (!newValue.isEmpty())
      {
        setListContentAndOrder(effectiveEnumerationFacet.getValue(), newValue);
      }

      List<XSDAnnotation> remainingAnnotations = new ArrayList<XSDAnnotation>(effectiveEnumerationFacet.getAnnotations());
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

    List<XSDPatternFacet> patternFacets = getPatternFacets();
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

      List<String> newValue = new ArrayList<String>();
      List<XSDAnnotation> newAnnotations = new ArrayList<XSDAnnotation>();
      XSDPatternFacet effectiveBasePatternFacet = theBaseTypeDefinition.getEffectivePatternFacet();
      if (effectiveBasePatternFacet != null)
      {
        newValue.addAll(effectiveBasePatternFacet.getValue());
      }

      StringBuffer combinedPattern = new StringBuffer();
      if (patternFacets.size() == 1)
      {
        XSDPatternFacet xsdPatternFacet = patternFacets.get(0);
        combinedPattern.append(xsdPatternFacet.getLexicalValue());
        XSDAnnotation xsdAnnotation = xsdPatternFacet.getAnnotation();
        if (xsdAnnotation != null)
        {
          newAnnotations.add(xsdAnnotation);
        }
      }
      else
      {
        for (XSDPatternFacet xsdPatternFacet : patternFacets)
        {
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

      List<String> remainingValues = new ArrayList<String>(effectivePatternFacet.getValue());
      remainingValues.removeAll(newValue);
      if (!remainingValues.isEmpty())
      {
        effectivePatternFacet.getValue().removeAll(remainingValues);
      }
      if (!newValue.isEmpty())
      {
        setListContentAndOrder(effectivePatternFacet.getValue(), newValue);
      }

      List<XSDAnnotation> remainingAnnotations = new ArrayList<XSDAnnotation>(effectivePatternFacet.getAnnotations());
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
        for (String string : newValue)
        {
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
      List<XSDSimpleFinal> newLexicalFinal = new ArrayList<XSDSimpleFinal>();
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
        Collection<XSDSimpleFinal> oldContents = new ArrayList<XSDSimpleFinal>(getLexicalFinal());
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
    for (Object literal : getFinal())
    {
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
     * This is the context in which the prefix of QNames are resolved.
     */
    public Element context;

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
    public Collection<XSDDiagnostic> diagnostics;

    /**
     * This records any nested assessments that were performed.
     */
    public Collection<Assessment> assessments;

    /**
     * This creates an empty instance;
     * minimally, the node or literal would need to be set before using this.
     */
    public AssessmentImpl()
    {
      super();
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
     * This creates an instance to assess the given node and to yield the specified results.
     */
    public AssessmentImpl(Element context, Node node, int yield)
    {
      this.context = context;
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

    /**
     * This creates an instance to assess the given literal and to yield the specified results.
     */
    public AssessmentImpl(Element context, String literal, int yield)
    {
      this.context = context;
      this.yield = yield;
      this.literal = literal;
    }

    public XSDSimpleTypeDefinition getTypeDefinition()
    {
      return xsdSimpleTypeDefinition;
    }

    public Element getContext()
    {
      return context;
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

    public Collection<XSDDiagnostic> getLocalDiagnostics()
    {
      return diagnostics == null ? Collections.<XSDDiagnostic>emptyList() : diagnostics;
    }

    public Collection<Assessment> getAssessments()
    {
      return assessments;
    }

    public Collection<XSDDiagnostic> getDiagnostics()
    {
      Collection<XSDDiagnostic> result = new ArrayList<XSDDiagnostic>();
      getAllDiagnostics(result);
      return result;
    }

    protected void getAllDiagnostics(Collection<XSDDiagnostic> result)
    {
      if (diagnostics != null)
      {
        result.addAll(diagnostics);
      }
      if (assessments != null)
      {
        for (Assessment assessment : assessments)
        {
          ((AssessmentImpl)assessment).getAllDiagnostics(result);
        }
      }
    }

    public void format(String noun, String name)
    {
      if (diagnostics != null)
      {
        for (XSDDiagnostic xsdDiagnostic : diagnostics)
        {
          xsdDiagnostic.setMessage(MessageFormat.format(xsdDiagnostic.getMessage(), new Object [] { noun, name }));
        }
      }
      if (assessments != null)
      {
        for (Assessment assessment :  assessments)
        {
          assessment.format(noun, name);
        }
      }
    }

    public void assignDiagnostics(XSDConcreteComponent xsdConcreteComponent, Element element, String attributeName)
    {
      Collection<XSDDiagnostic> allDiagnostics = getDiagnostics();
      if (!allDiagnostics.isEmpty())
      {
        Node theNode = element;
        if (element != null && element.hasAttributeNS(null, attributeName))
        {
          theNode = element.getAttributeNodeNS(null, attributeName);
        }

        Object [] substitutions = new Object [] { XSDPlugin.INSTANCE.getString("attribute_noun"), attributeName };
        for (XSDDiagnostic xsdDiagnostic : allDiagnostics)
        {
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

        String message = populateDiagnostic(result, "cvc-" + xsdConstrainingFacet.getFacetName() + "-valid", substitutions);
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
          diagnostics = new ArrayList<XSDDiagnostic>();
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
        populateDiagnostic
          (result,
           primitiveTypeDefinition == null ? 
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
        diagnostics = new ArrayList<XSDDiagnostic>();
      }
      diagnostics.add(result);
    }
  }

  public XSDSimpleTypeDefinition.Assessment assess(Node node)
  {
    return assess(null, node);
  }

  public XSDSimpleTypeDefinition.Assessment assess(Element context, Node node)
  {
    AssessmentImpl assessment = new AssessmentImpl(context, node, 0);
    return assess(assessment);
  }

  public XSDSimpleTypeDefinition.Assessment assess(String literal)
  {
    return assess(null, literal);
  }

  public XSDSimpleTypeDefinition.Assessment assess(Element context, String literal)
  {
    AssessmentImpl assessment = new AssessmentImpl(context, literal, 0);
    return assess(assessment);
  }

  public AssessmentImpl assess(AssessmentImpl assessment)
  {
    return assess(assessment, true);
  }

  public AssessmentImpl assess(AssessmentImpl assessment, boolean validate)
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
          assessment.literal = assessment.node.getNodeValue();
          break;
        }
        case Node.ELEMENT_NODE:
        {
          Element element = (Element)assessment.node;
          StringBuffer text = new StringBuffer();
          for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling())
          {
            switch (child.getNodeType())
            {
              case Node.TEXT_NODE:
              case Node.CDATA_SECTION_NODE:
              {
                text.append(child.getNodeValue());
                break;
              }
            }
          }
          assessment.literal = text.toString();
          break;
        }
        case Node.TEXT_NODE:
        case Node.CDATA_SECTION_NODE:
        {
          assessment.literal = assessment.node.getNodeValue();
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
      if (validate)
      {
        assessment.validate(getEffectivePatternFacet());
      }

      switch (getVariety().getValue())
      {
        case XSDVariety.ATOMIC:
        {
          XSDSimpleTypeDefinition thePrimitiveTypeDefinition = getPrimitiveTypeDefinition();
          if (thePrimitiveTypeDefinition != null)
          {
            XSDAnySimpleType xsdAnySimpleType = XSDTypeRegister.getTypeImplementer(thePrimitiveTypeDefinition.getName());
            xsdAnySimpleType.assess(assessment);
            if (validate && assessment.diagnostics == null)
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
          for (XSDSimpleTypeDefinition memberTypeDefinition : getMemberTypeDefinitions())
          {
            AssessmentImpl nestedAssessment = new AssessmentImpl(assessment.context, assessment.node, assessment.yield);
            nestedAssessment.literal = nestedAssessment.normalizedLiteral = assessment.normalizedLiteral;

            ((XSDSimpleTypeDefinitionImpl)memberTypeDefinition).assess(nestedAssessment);

            if (nestedAssessment.diagnostics == null)
            {
              if (assessment.assessments == null)
              {
                assessment.assessments = new ArrayList<Assessment>();
              }
              assessment.assessments.add(nestedAssessment);
              assessment.value = nestedAssessment.value;
              good = true;
              break;
            }
          }

          if (validate && !good)
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
            List<Object> list = new ArrayList<Object>();
            assessment.value = list;
            int length = 0;
            for (StringTokenizer tokens = new StringTokenizer(assessment.normalizedLiteral, " "); 
                 tokens.hasMoreTokens(); 
                 ++length)
            {
              AssessmentImpl nestedAssessment = new AssessmentImpl(assessment.context, assessment.node, assessment.yield);
              nestedAssessment.literal = nestedAssessment.normalizedLiteral = tokens.nextToken();
              ((XSDSimpleTypeDefinitionImpl)getItemTypeDefinition()).assess(nestedAssessment, validate);
              if (nestedAssessment.value != null)
              {
                list.add(nestedAssessment.value);
              }
              if (assessment.assessments == null)
              {
                assessment.assessments = new ArrayList<Assessment>();
              }
              assessment.assessments.add(nestedAssessment);
            }

            if (validate)
            {
              assessment.validate(getEffectiveLengthFacet());
              assessment.validate(getEffectiveMinLengthFacet());
              assessment.validate(getEffectiveMaxLengthFacet());
            }
          }
          break;
        }
      }

      if (validate)
      {
        assessment.validate(getEffectiveEnumerationFacet());
      }
    }

    return assessment;
  }

  public boolean isValidLiteral(String literal)
  {
    return isValidLiteral(null, literal);
  }

  public boolean isValidLiteral(Element context, String literal)
  {
    AssessmentImpl assessment = new AssessmentImpl(context, literal, 0);
    assess(assessment);
    return assessment.diagnostics == null;
  }

  public Object getValue(String literal)
  {
    return getValue(null, literal);
    
  }

  public Object getValue(Element context, String literal)
  {
    AssessmentImpl assessment = new AssessmentImpl(context, literal, 0);
    assess(assessment, false);
    return assessment.value;
  }

  public String getCanonicalLiteral(String literal)
  {
    AssessmentImpl assessment = new AssessmentImpl(null, literal, 0);
    assess(assessment, false);
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
    return equalLiterals(null, literal1, null, literal2);
  }

  public boolean equalLiterals(Element context1, String literal1, Element context2, String literal2)
  {
    return equalValues(getValue(context1, literal1), getValue(context2, literal2));
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
    return compareLiterals(null, literal1, null, literal2);
  }

  public int compareLiterals(Element context1, String literal1, Element context2, String literal2)
  {
    return compareValues(getValue(context1, literal1), getValue(context2, literal2));
  }

  @Override
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
          for (XSDSimpleTypeDefinition memberTypeDefinition : xsdSimpleTypeDefinition.getMemberTypeDefinitions())
          {
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

  @Override
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
        List<XSDSimpleTypeDefinition> unresolvedMembers = new ArrayList<XSDSimpleTypeDefinition>(getMemberTypeDefinitions().size());
        for (XSDSimpleTypeDefinition memberTypeDefinition : getMemberTypeDefinitions())
        {
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
          clonedSimpleTypeDefinition.setItemTypeDefinition(clonedSimpleTypeDefinition.getContents().get(0));
        }
        else if (!getMemberTypeDefinitions().isEmpty() && (theBaseTypeDefinition == null || XSDConstants.isURType(theBaseTypeDefinition)))
        {
          clonedSimpleTypeDefinition.getMemberTypeDefinitions().addAll(clonedSimpleTypeDefinition.getContents());
        }
        else
        {
          clonedSimpleTypeDefinition.setBaseTypeDefinition(clonedSimpleTypeDefinition.getContents().get(0));
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
