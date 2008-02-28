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
 * $Id: XSDComplexTypeDefinitionImpl.java,v 1.27 2008/02/28 21:03:37 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeGroupContent;
import org.eclipse.xsd.XSDAttributeGroupDefinition;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDAttributeUseCategory;
import org.eclipse.xsd.XSDComplexFinal;
import org.eclipse.xsd.XSDComplexTypeContent;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDComponent;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDContentTypeCategory;
import org.eclipse.xsd.XSDDerivationMethod;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDDisallowedSubstitutions;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDNamespaceConstraintCategory;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDParticleContent;
import org.eclipse.xsd.XSDProhibitedSubstitutions;
import org.eclipse.xsd.XSDRedefine;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.XSDWildcard;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Complex Type Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getDerivationMethod <em>Derivation Method</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getContentTypeCategory <em>Content Type Category</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getProhibitedSubstitutions <em>Prohibited Substitutions</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getLexicalFinal <em>Lexical Final</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getBlock <em>Block</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#isMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getContentAnnotation <em>Content Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getBaseTypeDefinition <em>Base Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getContent <em>Content</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getContentType <em>Content Type</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getAttributeUses <em>Attribute Uses</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getAttributeContents <em>Attribute Contents</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getAttributeWildcard <em>Attribute Wildcard</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getAttributeWildcardContent <em>Attribute Wildcard Content</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getRootTypeDefinition <em>Root Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getSyntheticParticle <em>Synthetic Particle</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDComplexTypeDefinitionImpl#getSyntheticWildcard <em>Synthetic Wildcard</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDComplexTypeDefinitionImpl 
  extends XSDTypeDefinitionImpl 
  implements XSDComplexTypeDefinition
{
  /**
   * The default value of the '{@link #getDerivationMethod() <em>Derivation Method</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDerivationMethod()
   * @generated
   * @ordered
   */
  protected static final XSDDerivationMethod DERIVATION_METHOD_EDEFAULT = XSDDerivationMethod.RESTRICTION_LITERAL;

  /**
   * The cached value of the '{@link #getDerivationMethod() <em>Derivation Method</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDerivationMethod()
   * @generated
   * @ordered
   */
  protected XSDDerivationMethod derivationMethod = DERIVATION_METHOD_EDEFAULT;

  /**
   * The flag representing whether the Derivation Method attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int DERIVATION_METHOD_ESETFLAG = 1 << 8;

  /**
   * The cached value of the '{@link #getFinal() <em>Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFinal()
   * @generated
   * @ordered
   */
  protected EList<XSDComplexFinal> final_;

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
  protected static final int ABSTRACT_EFLAG = 1 << 9;

  /**
   * The flag representing whether the Abstract attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int ABSTRACT_ESETFLAG = 1 << 10;

  /**
   * The default value of the '{@link #getContentTypeCategory() <em>Content Type Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContentTypeCategory()
   * @generated
   * @ordered
   */
  protected static final XSDContentTypeCategory CONTENT_TYPE_CATEGORY_EDEFAULT = XSDContentTypeCategory.EMPTY_LITERAL;

  /**
   * The cached value of the '{@link #getContentTypeCategory() <em>Content Type Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContentTypeCategory()
   * @generated
   * @ordered
   */
  protected XSDContentTypeCategory contentTypeCategory = CONTENT_TYPE_CATEGORY_EDEFAULT;

  /**
   * The cached value of the '{@link #getProhibitedSubstitutions() <em>Prohibited Substitutions</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProhibitedSubstitutions()
   * @generated
   * @ordered
   */
  protected EList<XSDProhibitedSubstitutions> prohibitedSubstitutions;

  /**
   * The cached value of the '{@link #getLexicalFinal() <em>Lexical Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLexicalFinal()
   * @generated
   * @ordered
   */
  protected EList<XSDComplexFinal> lexicalFinal;

  /**
   * The cached value of the '{@link #getBlock() <em>Block</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBlock()
   * @generated
   * @ordered
   */
  protected EList<XSDProhibitedSubstitutions> block;

  /**
   * The default value of the '{@link #isMixed() <em>Mixed</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isMixed()
   * @generated
   * @ordered
   */
  protected static final boolean MIXED_EDEFAULT = false;

  /**
   * The flag representing the value of the '{@link #isMixed() <em>Mixed</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isMixed()
   * @generated
   * @ordered
   */
  protected static final int MIXED_EFLAG = 1 << 11;

  /**
   * The flag representing whether the Mixed attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int MIXED_ESETFLAG = 1 << 12;

  /**
   * The cached value of the '{@link #getContentAnnotation() <em>Content Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContentAnnotation()
   * @generated
   * @ordered
   */
  protected XSDAnnotation contentAnnotation;

  /**
   * The cached value of the '{@link #getBaseTypeDefinition() <em>Base Type Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBaseTypeDefinition()
   * @generated
   * @ordered
   */
  protected XSDTypeDefinition baseTypeDefinition;

  /**
   * The cached value of the '{@link #getContent() <em>Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContent()
   * @generated
   * @ordered
   */
  protected XSDComplexTypeContent content;

  /**
   * The cached value of the '{@link #getContentType() <em>Content Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContentType()
   * @generated
   * @ordered
   */
  protected XSDComplexTypeContent contentType;

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
   * The cached value of the '{@link #getAttributeContents() <em>Attribute Contents</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttributeContents()
   * @generated
   * @ordered
   */
  protected EList<XSDAttributeGroupContent> attributeContents;

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
   * The cached value of the '{@link #getAttributeWildcardContent() <em>Attribute Wildcard Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttributeWildcardContent()
   * @generated
   * @ordered
   */
  protected XSDWildcard attributeWildcardContent;

  /**
   * The cached value of the '{@link #getSyntheticParticle() <em>Synthetic Particle</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSyntheticParticle()
   * @generated
   * @ordered
   */
  protected XSDParticle syntheticParticle;

  /**
   * The cached value of the '{@link #getSyntheticWildcard() <em>Synthetic Wildcard</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSyntheticWildcard()
   * @generated
   * @ordered
   */
  protected XSDWildcard syntheticWildcard;

  public static XSDComplexTypeDefinition createComplexTypeDefinition(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.COMPLEXTYPE_ELEMENT)
    {
      XSDComplexTypeDefinition xsdComplexTypeDefinition = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
      xsdComplexTypeDefinition.setElement((Element)node);
      return xsdComplexTypeDefinition;
    }

   return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDComplexTypeDefinitionImpl()
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
    return XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDDerivationMethod getDerivationMethod()
  {
    return derivationMethod;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDerivationMethod(XSDDerivationMethod newDerivationMethod)
  {
    XSDDerivationMethod oldDerivationMethod = derivationMethod;
    derivationMethod = newDerivationMethod == null ? DERIVATION_METHOD_EDEFAULT : newDerivationMethod;
    boolean oldDerivationMethodESet = (eFlags & DERIVATION_METHOD_ESETFLAG) != 0;
    eFlags |= DERIVATION_METHOD_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__DERIVATION_METHOD, oldDerivationMethod, derivationMethod, !oldDerivationMethodESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetDerivationMethod()
  {
    XSDDerivationMethod oldDerivationMethod = derivationMethod;
    boolean oldDerivationMethodESet = (eFlags & DERIVATION_METHOD_ESETFLAG) != 0;
    derivationMethod = DERIVATION_METHOD_EDEFAULT;
    eFlags &= ~DERIVATION_METHOD_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__DERIVATION_METHOD, oldDerivationMethod, DERIVATION_METHOD_EDEFAULT, oldDerivationMethodESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetDerivationMethod()
  {
    return (eFlags & DERIVATION_METHOD_ESETFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDComplexFinal> getFinal()
  {
    if (final_ == null)
    {
      final_ = new EDataTypeUniqueEList<XSDComplexFinal>(XSDComplexFinal.class, this, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__FINAL);
    }
    return final_;
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
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ABSTRACT, oldAbstract, newAbstract, !oldAbstractESet));
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
      eNotify(new ENotificationImpl(this, Notification.UNSET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ABSTRACT, oldAbstract, ABSTRACT_EDEFAULT, oldAbstractESet));
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
  public XSDContentTypeCategory getContentTypeCategory()
  {
    return contentTypeCategory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContentTypeCategory(XSDContentTypeCategory newContentTypeCategory)
  {
    XSDContentTypeCategory oldContentTypeCategory = contentTypeCategory;
    contentTypeCategory = newContentTypeCategory == null ? CONTENT_TYPE_CATEGORY_EDEFAULT : newContentTypeCategory;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_TYPE_CATEGORY, oldContentTypeCategory, contentTypeCategory));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDProhibitedSubstitutions> getProhibitedSubstitutions()
  {
    if (prohibitedSubstitutions == null)
    {
      prohibitedSubstitutions = new EDataTypeUniqueEList<XSDProhibitedSubstitutions>(XSDProhibitedSubstitutions.class, this, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__PROHIBITED_SUBSTITUTIONS);
    }
    return prohibitedSubstitutions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDComplexFinal> getLexicalFinal()
  {
    if (lexicalFinal == null)
    {
      lexicalFinal = new EDataTypeUniqueEList.Unsettable<XSDComplexFinal>(XSDComplexFinal.class, this, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__LEXICAL_FINAL);
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
  public EList<XSDProhibitedSubstitutions> getBlock()
  {
    if (block == null)
    {
      block = new EDataTypeUniqueEList.Unsettable<XSDProhibitedSubstitutions>(XSDProhibitedSubstitutions.class, this, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__BLOCK);
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
   * @generated
   */
  public boolean isMixed()
  {
    return (eFlags & MIXED_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMixed(boolean newMixed)
  {
    boolean oldMixed = (eFlags & MIXED_EFLAG) != 0;
    if (newMixed) eFlags |= MIXED_EFLAG; else eFlags &= ~MIXED_EFLAG;
    boolean oldMixedESet = (eFlags & MIXED_ESETFLAG) != 0;
    eFlags |= MIXED_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__MIXED, oldMixed, newMixed, !oldMixedESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetMixed()
  {
    boolean oldMixed = (eFlags & MIXED_EFLAG) != 0;
    boolean oldMixedESet = (eFlags & MIXED_ESETFLAG) != 0;
    if (MIXED_EDEFAULT) eFlags |= MIXED_EFLAG; else eFlags &= ~MIXED_EFLAG;
    eFlags &= ~MIXED_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__MIXED, oldMixed, MIXED_EDEFAULT, oldMixedESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetMixed()
  {
    return (eFlags & MIXED_ESETFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDAnnotation getContentAnnotation()
  {
    return contentAnnotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContentAnnotation(XSDAnnotation newContentAnnotation)
  {
    if (newContentAnnotation != contentAnnotation)
    {
      NotificationChain msgs = null;
      if (contentAnnotation != null)
        msgs = ((InternalEObject)contentAnnotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_ANNOTATION, null, msgs);
      if (newContentAnnotation != null)
        msgs = ((InternalEObject)newContentAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_ANNOTATION, null, msgs);
      msgs = basicSetContentAnnotation(newContentAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_ANNOTATION, newContentAnnotation, newContentAnnotation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetContentAnnotation(XSDAnnotation newContentAnnotation, NotificationChain msgs)
  {
    XSDAnnotation oldContentAnnotation = contentAnnotation;
    contentAnnotation = newContentAnnotation;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_ANNOTATION, oldContentAnnotation, newContentAnnotation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDTypeDefinition getBaseTypeDefinition()
  {
    return baseTypeDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBaseTypeDefinition(XSDTypeDefinition newBaseTypeDefinition)
  {
    XSDTypeDefinition oldBaseTypeDefinition = baseTypeDefinition;
    baseTypeDefinition = newBaseTypeDefinition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__BASE_TYPE_DEFINITION, oldBaseTypeDefinition, baseTypeDefinition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDComplexTypeContent getContent()
  {
    return content;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContent(XSDComplexTypeContent newContent)
  {
    if (newContent != content)
    {
      NotificationChain msgs = null;
      if (content != null)
        msgs = ((InternalEObject)content).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT, null, msgs);
      if (newContent != null)
        msgs = ((InternalEObject)newContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT, null, msgs);
      msgs = basicSetContent(newContent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT, newContent, newContent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetContent(XSDComplexTypeContent newContent, NotificationChain msgs)
  {
    XSDComplexTypeContent oldContent = content;
    content = newContent;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT, oldContent, newContent);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDComplexTypeContent getContentType()
  {
    return contentType;
  }

  @Override
  public XSDParticle getComplexType()
  {
    return getContentType() instanceof XSDParticle ? (XSDParticle)getContentType() : null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContentType(XSDComplexTypeContent newContentType)
  {
    XSDComplexTypeContent oldContentType = contentType;
    contentType = newContentType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_TYPE, oldContentType, contentType));
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
      attributeUses = new EObjectEList<XSDAttributeUse>(XSDAttributeUse.class, this, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_USES);
    }
    return attributeUses;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDAttributeGroupContent> getAttributeContents()
  {
    if (attributeContents == null)
    {
      attributeContents = new EObjectContainmentEList<XSDAttributeGroupContent>(XSDAttributeGroupContent.class, this, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_CONTENTS);
    }
    return attributeContents;
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
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD, oldAttributeWildcard, attributeWildcard));
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
        msgs = ((InternalEObject)attributeWildcardContent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT, null, msgs);
      if (newAttributeWildcardContent != null)
        msgs = ((InternalEObject)newAttributeWildcardContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT, null, msgs);
      msgs = basicSetAttributeWildcardContent(newAttributeWildcardContent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT, newAttributeWildcardContent, newAttributeWildcardContent));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT, oldAttributeWildcardContent, newAttributeWildcardContent);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  @Override
  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.COMPLEXTYPE_ELEMENT);
    setElement(newElement);

    Element attributeParentElement = newElement;

    XSDComplexTypeContent theContent = getContent();
    if (theContent == null || theContent instanceof XSDParticle)
    {
      XSDTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();
      if (theBaseTypeDefinition != null && 
            (!XSDConstants.isURType(theBaseTypeDefinition) || 
               XSDDerivationMethod.EXTENSION_LITERAL == getDerivationMethod()))
      {
        Element [] contentElementPair = createContentElementPair();
        attributeParentElement = contentElementPair[1];

        newElement.appendChild(contentElementPair[0]);
      }

      if (theContent != null)
      {
        Element simpleContentElement = ((XSDConcreteComponentImpl)theContent).createElement(); 
        attributeParentElement.appendChild(simpleContentElement);
      }
    }
    else 
    {
      Element simpleContentElement = ((XSDConcreteComponentImpl)theContent).createElement();
      newElement.appendChild(simpleContentElement);

      LOOP:
      for (Node child = simpleContentElement.getFirstChild(); child != null; child = child.getNextSibling())
      {
        switch (XSDConstants.nodeType(child))
        {
          case XSDConstants.EXTENSION_ELEMENT:
          case XSDConstants.RESTRICTION_ELEMENT:
          {
            attributeParentElement = (Element)child;
            break LOOP;
          }
        }
      }
    }

    for (XSDAttributeGroupContent xsdAttributeGroupContent : getAttributeContents())
    {
      Element attributeElement = ((XSDConcreteComponentImpl)xsdAttributeGroupContent).createElement();
      attributeParentElement.appendChild(attributeElement);
    }

    return newElement;
  }

  protected Element [] createContentElementPair()
  {
    Element [] contentElementPair = new Element [2];
    contentElementPair[0] = createElement(XSDConstants.COMPLEXCONTENT_ELEMENT);
    contentElementPair[1] =
      createElement
       (XSDDerivationMethod.EXTENSION_LITERAL == getDerivationMethod() ?
           XSDConstants.EXTENSION_ELEMENT :
           XSDConstants.RESTRICTION_ELEMENT);
    contentElementPair[0].appendChild(contentElementPair[1]);
    return contentElementPair;
  }

  protected Element createDerivationElement()
  {
    return
      createElement
       (XSDDerivationMethod.EXTENSION_LITERAL == getDerivationMethod() ?
           XSDConstants.EXTENSION_ELEMENT :
           XSDConstants.RESTRICTION_ELEMENT);
  }

  @Override
  protected boolean considerAllContainsForBestConcreteComponent()
  {
    return true;
  }

  @Override
  protected void patch()
  {
    super.patch();
    XSDTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();
    if (theBaseTypeDefinition != null && (forceResolve || theBaseTypeDefinition.getContainer() == null))
    {
      theBaseTypeDefinition = resolveTypeDefinition(theBaseTypeDefinition.getTargetNamespace(), theBaseTypeDefinition.getName());
    }
    theBaseTypeDefinition = handleNewBaseTypeDefinition(theBaseTypeDefinition);

    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      List<XSDComplexFinal> newFinal = new ArrayList<XSDComplexFinal>();
      if (!isSetLexicalFinal())
      {
        for (XSDProhibitedSubstitutions value : getSchema().getFinalDefault())
        {
          switch (value.getValue())
          {
            case XSDProhibitedSubstitutions.ALL:
            {
              newFinal.add(XSDComplexFinal.EXTENSION_LITERAL);
              newFinal.add(XSDComplexFinal.RESTRICTION_LITERAL);
              break;
            }
            case XSDProhibitedSubstitutions.EXTENSION:
            {
              newFinal.add(XSDComplexFinal.EXTENSION_LITERAL);
              break;
            }
            case XSDProhibitedSubstitutions.RESTRICTION:
            {
              newFinal.add(XSDComplexFinal.RESTRICTION_LITERAL);
              break;
            }
          }
        }
      }
      else
      {
        for (XSDComplexFinal value :  getLexicalFinal())
        {
          switch (value.getValue())
          {
            case XSDComplexFinal.ALL:
            {
              newFinal.add(XSDComplexFinal.EXTENSION_LITERAL);
              newFinal.add(XSDComplexFinal.RESTRICTION_LITERAL);
              break;
            }
            case XSDComplexFinal.EXTENSION:
            case XSDComplexFinal.RESTRICTION:
            {
              newFinal.add(value);
              break;
            }
          }
        }
      }

      Collection<XSDComplexFinal> oldFinal = new ArrayList<XSDComplexFinal>(getFinal());
      oldFinal.removeAll(newFinal);
      if (!oldFinal.isEmpty())
      {
        getFinal().removeAll(oldFinal);
      }
      setListContentAndOrder(getFinal(), newFinal);

      List<XSDProhibitedSubstitutions> newProhibitedSubstitutions = new ArrayList<XSDProhibitedSubstitutions>();
      if (!isSetBlock())
      {
        for (XSDDisallowedSubstitutions value :  getSchema().getBlockDefault())
        {
          switch (value.getValue())
          {
            case XSDDisallowedSubstitutions.ALL:
            {
              newProhibitedSubstitutions.add(XSDProhibitedSubstitutions.EXTENSION_LITERAL);
              newProhibitedSubstitutions.add(XSDProhibitedSubstitutions.RESTRICTION_LITERAL);
              break;
            }
            case XSDDisallowedSubstitutions.RESTRICTION:
            {
              newProhibitedSubstitutions.add(XSDProhibitedSubstitutions.RESTRICTION_LITERAL);
              break;
            }
            case XSDDisallowedSubstitutions.EXTENSION:
            {
              newProhibitedSubstitutions.add(XSDProhibitedSubstitutions.EXTENSION_LITERAL);
              break;
            }
          }
        }
      }
      else
      {
        for (XSDProhibitedSubstitutions value : getBlock())
        {
          switch (value.getValue())
          {
            case XSDProhibitedSubstitutions.ALL:
            {
              newProhibitedSubstitutions.add(XSDProhibitedSubstitutions.EXTENSION_LITERAL);
              newProhibitedSubstitutions.add(XSDProhibitedSubstitutions.RESTRICTION_LITERAL);
              break;
            }
            case XSDProhibitedSubstitutions.EXTENSION:
            case XSDProhibitedSubstitutions.RESTRICTION:
            {
              newProhibitedSubstitutions.add(value);
              break;
            }
          }
        }
      }

      Collection<XSDProhibitedSubstitutions> oldProhibitedSubstitutions = new ArrayList<XSDProhibitedSubstitutions>(getProhibitedSubstitutions());
      oldProhibitedSubstitutions.removeAll(newProhibitedSubstitutions);
      if (!oldProhibitedSubstitutions.isEmpty())
      {
        getProhibitedSubstitutions().removeAll(oldProhibitedSubstitutions);
      }
      setListContentAndOrder(getProhibitedSubstitutions(), newProhibitedSubstitutions);
    }
  }

  protected XSDParticle extensionParticle;
  protected XSDWildcardImpl effectiveWildcard;
  @Override
  protected void handleAnalysis()
  {
    XSDTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();
    XSDComplexTypeContent newContentType = null;
    XSDContentTypeCategory newContentTypeCategory = XSDContentTypeCategory.EMPTY_LITERAL;
    XSDWildcard localWildcard = getAttributeWildcardContent();
    XSDWildcard baseWildcard = null;
    if (theBaseTypeDefinition != null && theBaseTypeDefinition.getContainer() != null)
    {
      if (!((XSDConcreteComponentImpl)theBaseTypeDefinition).analyze() && !XSDConstants.isURType(theBaseTypeDefinition) && theBaseTypeDefinition.isCircular())
      {
        analysisState = CIRCULAR;
      }
      if (theBaseTypeDefinition != this)
      {
        List<XSDAttributeUse> baseAttributeUses = Collections.emptyList();
        if (theBaseTypeDefinition instanceof XSDComplexTypeDefinition)
        {
          baseAttributeUses = ((XSDComplexTypeDefinition)theBaseTypeDefinition).getAttributeUses();
        }
        EList<XSDAttributeUse> theAttributeUses = getAttributeUses();
        List<XSDAttributeUse> newAttributeUses = XSDAttributeGroupDefinitionImpl.getAttributeUses(getAttributeContents(), baseAttributeUses);
        List<XSDAttributeUse> remainingAttributeUses = new ArrayList<XSDAttributeUse>(theAttributeUses);
        remainingAttributeUses.removeAll(newAttributeUses);
        theAttributeUses.removeAll(remainingAttributeUses);
        setListContentAndOrder(theAttributeUses, newAttributeUses);

        if (XSDDerivationMethod.EXTENSION_LITERAL == getDerivationMethod() && 
              theBaseTypeDefinition instanceof XSDComplexTypeDefinition)
        {
          baseWildcard = ((XSDComplexTypeDefinition)theBaseTypeDefinition).getAttributeWildcard();
        }
      }
    }

    if (localWildcard != null)
    {
      ((XSDConcreteComponentImpl)localWildcard).analyze();
    }
    XSDWildcard newAttributeWildcard = 
      XSDAttributeGroupDefinitionImpl.getAttributeWildcard(baseWildcard, localWildcard, getAttributeContents());

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

    newContentType = getContent();
    if (newContentType instanceof XSDSimpleTypeDefinition)
    {
      ((XSDConcreteComponentImpl)newContentType).analyze();
      newContentTypeCategory = XSDContentTypeCategory.SIMPLE_LITERAL;
      if (XSDDerivationMethod.EXTENSION_LITERAL == getDerivationMethod())
      {
        newContentType = ((XSDSimpleTypeDefinition)newContentType).getBaseTypeDefinition();
      }
    }
    else 
    {
      newContentTypeCategory = isMixed() ? XSDContentTypeCategory.MIXED_LITERAL : XSDContentTypeCategory.ELEMENT_ONLY_LITERAL;
      boolean isEmptyContent = isEmptyContent((XSDParticle)newContentType);
      if (XSDDerivationMethod.EXTENSION_LITERAL == getDerivationMethod())
      {
        if (theBaseTypeDefinition instanceof XSDComplexTypeDefinition)
        {
          XSDComplexTypeDefinition complexBaseTypeDefinition = (XSDComplexTypeDefinition)theBaseTypeDefinition;

          if (isEmptyContent)
          {
            newContentTypeCategory = complexBaseTypeDefinition.getContentTypeCategory();
            newContentType = complexBaseTypeDefinition.getContentType();
          }
          else
          {
            XSDComplexTypeContent baseContentType = complexBaseTypeDefinition.getContentType();
            if (baseContentType instanceof XSDParticle)
            {
              if (extensionParticle == null)
              {
                extensionParticle = getXSDFactory().createXSDParticle();
                setSyntheticParticle(extensionParticle);
                extensionParticle.setMinOccurs(1);
                extensionParticle.setMaxOccurs(1);
                XSDModelGroup xsdModelGroup = getXSDFactory().createXSDModelGroup();
                xsdModelGroup.setCompositor(XSDCompositor.SEQUENCE_LITERAL);
                extensionParticle.setContent(xsdModelGroup);
              }

              XSDModelGroup extensionGroup = (XSDModelGroup)extensionParticle.getTerm();
              List<XSDParticle> newParticles = new ArrayList<XSDParticle>();
              newParticles.add((XSDParticle)baseContentType);
              if (getContent() != null)
              {
                newParticles.add((XSDParticle)getContent());
              }
              List<XSDParticle> remainingParticles = new ArrayList<XSDParticle>(extensionGroup.getParticles());
              remainingParticles.removeAll(newParticles);
              if (!remainingParticles.isEmpty())
              {
                extensionGroup.getParticles().removeAll(remainingParticles);
              }
              setListContentAndOrder(extensionGroup.getParticles(), newParticles);
    
              if (isEmptyContent((XSDParticle)baseContentType))
              {
                newContentType = getContent();
              }
              else
              {
                newContentType = extensionParticle;
              }
            }
          }
        }
        else if (isEmptyContent)
        {
          newContentTypeCategory = XSDContentTypeCategory.EMPTY_LITERAL;
        }
      }
      else
      {
        if (isEmptyContent)
        {
          if (isMixed())
          {
            if (extensionParticle == null)
            {
              extensionParticle = getXSDFactory().createXSDParticle();
              setSyntheticParticle(extensionParticle);
              extensionParticle.setMinOccurs(1);
              extensionParticle.setMaxOccurs(1);
              XSDModelGroup xsdModelGroup = getXSDFactory().createXSDModelGroup();
              xsdModelGroup.setCompositor(XSDCompositor.SEQUENCE_LITERAL);
              extensionParticle.setContent(xsdModelGroup);
            }
            newContentType = extensionParticle;
          }
          else
          {
            newContentTypeCategory = XSDContentTypeCategory.EMPTY_LITERAL;
          }
        }
      }
    }

    if (newContentTypeCategory != getContentTypeCategory())
    {
      setContentTypeCategory(newContentTypeCategory);
    }

    if (newContentType != getContentType())
    {
      setContentType(newContentType);
    }

    if (newAttributeWildcard != getAttributeWildcard())
    {
      setAttributeWildcard(newAttributeWildcard);
    }

    if (analysisState == ANALYZING)
    {
      analysisState = ANALYZED;
    }
    super.handleAnalysis();
  }

  protected boolean isEmptyContent(XSDParticle xsdParticle)
  {
    if (xsdParticle == null)
    {
      return true;
    }
    else
    {
      XSDParticleContent xsdParticleContent = xsdParticle.getContent();
      if (xsdParticleContent instanceof XSDModelGroup)
      {
        XSDModelGroup xsdModelGroup = (XSDModelGroup)xsdParticleContent;
        switch (xsdModelGroup.getCompositor().getValue())
        {
          case XSDCompositor.SEQUENCE:
          case XSDCompositor.ALL:
          {
            return xsdModelGroup.getParticles().isEmpty();
          }
          case XSDCompositor.CHOICE:
          {
            return xsdModelGroup.getParticles().isEmpty() && xsdParticle.getMinOccurs() == 0;
          }
          default:
          {
            return false;
          }
        }
      }
      else
      {
        return false;
      }
    }
  }

  @Override
  public void validate()
  {
    super.validate();

    if (getComplexType() != null && getComplexType().getTerm() instanceof XSDModelGroupImpl)
    {
      ((XSDModelGroupImpl)getComplexType().getTerm()).validateRoot();
    }

    Element theElement = getElement();
    if (theElement != null)
    {
      if (getContainer() instanceof XSDElementDeclaration)
      {
        checkAttributes
          (XSDConstants.PART1,
           "element-complexType",
           theElement,
           new String []
           {
             XSDConstants.ID_ATTRIBUTE,
             XSDConstants.MIXED_ATTRIBUTE
           });

         checkComplexContent("localComplexType", XSDConstants.PART1, "element-complexType", theElement);
      }
      else
      {
        checkAttributes
          (XSDConstants.PART1,
           "element-complexType",
           theElement,
           new String []
           {
             XSDConstants.ABSTRACT_ATTRIBUTE,
             XSDConstants.BLOCK_ATTRIBUTE,
             XSDConstants.FINAL_ATTRIBUTE,
             XSDConstants.ID_ATTRIBUTE,
             XSDConstants.NAME_ATTRIBUTE,
             XSDConstants.MIXED_ATTRIBUTE
           });

         checkComplexContent("topLevelComplexType", XSDConstants.PART1, "element-complexType", theElement);

         checkBuiltInTypeConstraint
           ("boolean",
            null,
            XSDConstants.PART1,
            "element-complexType",
            theElement,
            XSDConstants.ABSTRACT_ATTRIBUTE,
            false);

         checkBuiltInTypeConstraint
           ("derivationSet",
            null,
            XSDConstants.PART1,
            "element-complexType",
            theElement,
            XSDConstants.BLOCK_ATTRIBUTE,
            false);
         
         checkBuiltInTypeConstraint
           ("derivationSet",
            null,
            XSDConstants.PART1,
            "element-complexType",
            theElement,
            XSDConstants.FINAL_ATTRIBUTE,
            false);

      }

      checkBuiltInTypeConstraint
        ("ID",
         null,
         XSDConstants.PART1,
         "element-complexType",
         theElement,
         XSDConstants.ID_ATTRIBUTE,
         false);

      checkBuiltInTypeConstraint
        ("boolean",
         null,
         XSDConstants.PART1,
         "element-complexType",
         theElement,
         XSDConstants.MIXED_ATTRIBUTE,
         false);

      for (Node child = theElement.getFirstChild(); child != null; child = child.getNextSibling())
      {
        switch (XSDConstants.nodeType(child))
        {
          case XSDConstants.COMPLEXCONTENT_ELEMENT:
          {
            Element childElement = (Element)child;

            checkAttributes
              (XSDConstants.PART1,
               "element-complexContent",
               childElement,
               new String []
               {
                 XSDConstants.ID_ATTRIBUTE,
                 XSDConstants.MIXED_ATTRIBUTE
               });

            checkElementComplexContent("complexContent", XSDConstants.PART1, "element-complexContent", childElement);

            checkBuiltInTypeConstraint
              ("ID",
               null,
               XSDConstants.PART1,
               "element-complexContent",
               childElement,
               XSDConstants.ID_ATTRIBUTE,
               false);

            checkBuiltInTypeConstraint
              ("boolean",
               null,
               XSDConstants.PART1,
               "element-complexContent",
               childElement,
               XSDConstants.MIXED_ATTRIBUTE,
               false);
            
            for (Node grandChild = childElement.getFirstChild(); grandChild != null; grandChild = grandChild.getNextSibling())
            {
              switch (XSDConstants.nodeType(grandChild))
              {
                case XSDConstants.EXTENSION_ELEMENT:
                {
                  Element grandChildElement = (Element)grandChild;
                  checkAttributes
                    (XSDConstants.PART1,
                     "element-complexContent::extension",
                     grandChildElement,
                     new String []
                     {
                       XSDConstants.ID_ATTRIBUTE,
                       XSDConstants.BASE_ATTRIBUTE
                     });

                  checkBuiltInTypeConstraint
                    ("ID",
                     null,
                     XSDConstants.PART1,
                     "element-complexContent::extension",
                     theElement,
                     XSDConstants.ID_ATTRIBUTE,
                     false);

                  checkComplexContent("extensionType", XSDConstants.PART1, "element-complexContent::extension", grandChildElement);

                  break;
                }
                case XSDConstants.RESTRICTION_ELEMENT:
                {
                  Element grandChildElement = (Element)grandChild;
                  checkAttributes
                    (XSDConstants.PART1,
                     "element-complexContent::restriction",
                     grandChildElement,
                     new String []
                     {
                       XSDConstants.ID_ATTRIBUTE,
                       XSDConstants.BASE_ATTRIBUTE
                     });

                  checkBuiltInTypeConstraint
                    ("ID",
                     null,
                     XSDConstants.PART1,
                     "element-complexContent::restriction",
                     theElement,
                     XSDConstants.ID_ATTRIBUTE,
                     false);

                  checkComplexContent
                    ("complexRestrictionType", XSDConstants.PART1, "element-complexContent::restriction", grandChildElement);

                  break;
                }
              }
            }

            break;
          }
        }
      }
    }

    if (!(getContainer() instanceof XSDElementDeclaration))
    {
      checkBuiltInTypeConstraint
        ("NCName",
         getName(),
         XSDConstants.PART1,
         "element-complexType",
         theElement,
         XSDConstants.NAME_ATTRIBUTE,
         true);
    }

    Map<String, XSDAttributeDeclaration> prohibitedAttributeURIs = null;

    XSDTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();
    if (theBaseTypeDefinition == null)
    {
      createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "_UI_UnresolvedTypeDefinition_message", "");
    }
    else if (theBaseTypeDefinition.getContainer() == null)
    {
      createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "_UI_UnresolvedTypeDefinition_message", theBaseTypeDefinition.getURI());
    }
    else
    {
      XSDComplexTypeDefinition xsdComplexTypeDefinition = null;
      XSDSimpleTypeDefinition xsdSimpleTypeDefinition = null;
      switch (getContentTypeCategory().getValue())
      {
        case XSDContentTypeCategory.SIMPLE:
        {
          if (theBaseTypeDefinition instanceof XSDComplexTypeDefinition)
          {
            xsdComplexTypeDefinition = (XSDComplexTypeDefinition)theBaseTypeDefinition;
            xsdSimpleTypeDefinition = xsdComplexTypeDefinition.getSimpleType();

            // EATM this contradicts the more relaxed constraint of derivation-ok-restriction.5.1.2
            // if (xsdSimpleTypeDefinition == null)
            // {
            //  createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-ct.2.1", theBaseTypeDefinition.getURI());
            // }
          }
          else
          {
            xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)theBaseTypeDefinition;
            if (getDerivationMethod() == XSDDerivationMethod.RESTRICTION_LITERAL)
            {
              createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-ct.2.2", theBaseTypeDefinition.getURI());
            }
            // else if (!XSDConstants.isSchemaForSchemaNamespace(getTargetNamespace()) && 
            //            XSDConstants.isAnySimpleType(xsdSimpleTypeDefinition))
            // {
            //   createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cos-ct-retricts.0", theBaseTypeDefinition.getURI());
            // }
          }
          break;
        }
        case XSDContentTypeCategory.EMPTY:
        case XSDContentTypeCategory.MIXED:
        case XSDContentTypeCategory.ELEMENT_ONLY:
        {
          if (theBaseTypeDefinition instanceof XSDComplexTypeDefinition)
          {
            xsdComplexTypeDefinition = (XSDComplexTypeDefinition)theBaseTypeDefinition;
          }
          else
          {
            xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)theBaseTypeDefinition;
            createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-ct.1", theBaseTypeDefinition.getURI());
          }
          break;
        }
      }

      if (getDerivationMethod() == XSDDerivationMethod.EXTENSION_LITERAL)
      {
        if (xsdComplexTypeDefinition != null)
        {
          prohibitedAttributeURIs = getProhibitedAttributeURIs(xsdComplexTypeDefinition);
          if (xsdComplexTypeDefinition.getFinal().contains(XSDComplexFinal.EXTENSION_LITERAL))
          {
            createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cos-ct-extends.1.1", theBaseTypeDefinition.getURI());
          }
          else
          {
            for (XSDAttributeUse baseXSDAttributeUse : xsdComplexTypeDefinition.getAttributeUses())
            {
              boolean matched = false;
              XSDAttributeDeclaration baseXSDAttributeDeclaration = baseXSDAttributeUse.getAttributeDeclaration();
              for (XSDAttributeUse xsdAttributeUse : getAttributeUses())
              {
                XSDAttributeDeclaration xsdAttributeDeclaration = xsdAttributeUse.getAttributeDeclaration();
                if (xsdAttributeDeclaration.hasSameNameAndTargetNamespace(baseXSDAttributeDeclaration))
                {
                  if (xsdAttributeDeclaration.getTypeDefinition() != baseXSDAttributeDeclaration.getTypeDefinition())
                  {
                    getDiagnosticTarget(xsdAttributeUse).createDiagnostic
                      (XSDDiagnosticSeverity.ERROR_LITERAL, 
                       "cos-ct-extends.1.2.2", 
                       xsdAttributeDeclaration.getURI(),
                       baseXSDAttributeDeclaration.getTypeDefinition().getURI());
                  }

                  matched = true;
                  break;
                }
              }

              if (!matched)
              {
                createDiagnostic
                  (XSDDiagnosticSeverity.ERROR_LITERAL, 
                   "cos-ct-extends.1.2.1", 
                   baseXSDAttributeDeclaration.getURI());
              }
            }

            // EATM this is impossible to hit by virtue of how the wildcard is constructed.
            //
            XSDWildcard baseXSDWildcard = xsdComplexTypeDefinition.getAttributeWildcard();
            if (baseXSDWildcard != null)
            {
              XSDWildcard xsdWildcard = getAttributeWildcard();
              if (xsdWildcard == null || !baseXSDWildcard.isWildcardSubset(xsdWildcard))
              {
                createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cos-ct-extends.1.3");
              }
            }

            switch (xsdComplexTypeDefinition.getContentTypeCategory().getValue())
            {
              case XSDContentTypeCategory.SIMPLE:
              {
                if (getContentType() != xsdComplexTypeDefinition.getContentType())
                {
                  createDiagnostic
                    (XSDDiagnosticSeverity.ERROR_LITERAL, 
                     "cos-ct-extends.1.4.1", 
                     xsdComplexTypeDefinition.getSimpleType().getURI());
                }
                break;
              }
              case XSDContentTypeCategory.EMPTY:
              {
                break;
              }
              case XSDContentTypeCategory.MIXED:
              case XSDContentTypeCategory.ELEMENT_ONLY:
              {
                //  EATM problems in spec since definition ignores "mixed" attribute we can't validate it here.
                //
                if (getContentTypeCategory() != xsdComplexTypeDefinition.getContentTypeCategory() ||
                     isSetMixed() && isMixed() != xsdComplexTypeDefinition.isMixed())
                {
                  createDiagnostic
                    (XSDDiagnosticSeverity.ERROR_LITERAL, 
                     "cos-ct-extends.1.4.2.2.2.1", 
                     xsdComplexTypeDefinition.getContentTypeCategory().toString());
                }
                break;
              }
            }
          }
        }
        else
        {
          // EATM can this happen?
          //
          if (theBaseTypeDefinition != getContentType())
          {
            createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cos-ct-extends.2.1");
          }
          // else if the base isn't final for extension, EATM but we can't test for that because it's not in the spec...
        }
      }
      else
      {
        // The null cases are already diagnosed; EATM except for errors above
        //
        if (xsdComplexTypeDefinition != null)
        {
          if (xsdComplexTypeDefinition.getFinal().contains(XSDComplexFinal.RESTRICTION_LITERAL))
          {
            createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "derivation-ok-restriction.1", xsdComplexTypeDefinition.getURI());
          }

          XSDComplexTypeDefinitionImpl.validateAttributeGroup
            (this,
             getAttributeWildcardContent(),
             xsdComplexTypeDefinition.getAttributeUses(),
             getAttributeUses(),
             xsdComplexTypeDefinition.getAttributeWildcard(),
             getAttributeWildcard());

          if (getSimpleType() != null)
          {
            if (xsdSimpleTypeDefinition != null)
            {
              XSDTypeDefinition badType = getSimpleType().getBadTypeDerivation(xsdSimpleTypeDefinition, true, true);
              if (badType != null)
              {
                createDiagnostic
                  (XSDDiagnosticSeverity.ERROR_LITERAL, 
                   "derivation-ok-restriction.5.1.1", 
                   getSimpleType().getURI(),
                   xsdSimpleTypeDefinition.getURI());
              }
            }
            else if (xsdComplexTypeDefinition.getContentTypeCategory() != XSDContentTypeCategory.MIXED_LITERAL ||
                       !xsdComplexTypeDefinition.getComplexType().isEmptiable())
            {
              createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "derivation-ok-restriction.5.1.2");
            }
          }
          else if (getComplexType() == null || getContentTypeCategory() == XSDContentTypeCategory.EMPTY_LITERAL)
          {
            if (xsdComplexTypeDefinition.getContentTypeCategory() == XSDContentTypeCategory.SIMPLE_LITERAL ||
                  xsdComplexTypeDefinition.getContentTypeCategory() != XSDContentTypeCategory.EMPTY_LITERAL &&
                  !xsdComplexTypeDefinition.getComplexType().isEmptiable())
            {
              createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "derivation-ok-restriction.5.2");
            }
          }
          else 
          {
            // EATM the spec kind of trails off here without adequate diagnostics for explanation.
            //
            if (xsdComplexTypeDefinition.getComplexType() == null)
            {
              createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "derivation-ok-restriction.5.3.1");
            }
            else if (getContentTypeCategory() == XSDContentTypeCategory.MIXED_LITERAL &&
                       xsdComplexTypeDefinition.getContentTypeCategory() != XSDContentTypeCategory.MIXED_LITERAL)
            {
              createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "derivation-ok-restriction.5.3.2");
            }
            else if (!((XSDParticleImpl)getComplexType()).isSubset(xsdComplexTypeDefinition.getComplexType(), true))
            {
              createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "derivation-ok-restriction.5.3.3");
            }
          }
        }
      }
    }

    XSDWildcard theAttributeWildcard = getAttributeWildcard();
    if (theAttributeWildcard != null &&
          theAttributeWildcard.getNamespaceConstraint().isEmpty() &&
          theAttributeWildcard.getNamespaceConstraintCategory() == XSDNamespaceConstraintCategory.NOT_LITERAL)
    {
       createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-ct.4");
    }

    if (isCircular() && !XSDConstants.isURType(this))
    {
      createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "ct-props-correct.3");
    }

    XSDAttributeDeclaration idAttribute = null;

    Set<String> uris = new HashSet<String>();
    for (XSDAttributeUse xsdAttributeUse : getAttributeUses())
    {
      XSDAttributeDeclaration xsdAttributeDeclaration = xsdAttributeUse.getAttributeDeclaration();
      String uri = xsdAttributeDeclaration.getURI();
      if (prohibitedAttributeURIs != null && prohibitedAttributeURIs.containsKey(uri))
      {
        getDiagnosticTarget(xsdAttributeUse).createDiagnostic
          (XSDDiagnosticSeverity.ERROR_LITERAL, "cos-ct-extends.1.5.1", uri);
      }
      if (!uris.add(uri))
      {
        getDiagnosticTarget(xsdAttributeUse).createDiagnostic
          (XSDDiagnosticSeverity.ERROR_LITERAL, "ct-props-correct.4", uri);
      }
      if (XSDConstants.isOrIsDerivedFromID(xsdAttributeDeclaration.getTypeDefinition()))
      {
        if (idAttribute != null)
        {
          getDiagnosticTarget(xsdAttributeUse).createDiagnostic
            (XSDDiagnosticSeverity.ERROR_LITERAL, "ct-props-correct.5", xsdAttributeDeclaration.getURI(), idAttribute.getURI());
        }
        else
        {
          idAttribute = xsdAttributeDeclaration;
        }
      }
    }
  }

  protected static Map<String, XSDAttributeDeclaration> getProhibitedAttributeURIs(XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {
    Map<String, XSDAttributeDeclaration> result = getProhibitedAttributes(xsdComplexTypeDefinition.getAttributeContents());
    if (xsdComplexTypeDefinition.getBaseTypeDefinition() instanceof XSDComplexTypeDefinition && 
          !xsdComplexTypeDefinition.isCircular())
    {
      result.putAll
        (getProhibitedAttributes
          (((XSDComplexTypeDefinition)xsdComplexTypeDefinition.getBaseTypeDefinition()).getAttributeContents()));
    }
    return result;
  }

  protected static Map<String, XSDAttributeDeclaration> getProhibitedAttributes(Collection<XSDAttributeGroupContent> xsdAttributeContents)
  {
    Map<String, XSDAttributeDeclaration> result = new HashMap<String, XSDAttributeDeclaration>();
    for (XSDAttributeGroupContent xsdAttributeGroupContent :  xsdAttributeContents)
    {
      if (xsdAttributeGroupContent instanceof XSDAttributeGroupDefinition)
      {
        XSDAttributeGroupDefinition xsdAttributeGroupDefinition = (XSDAttributeGroupDefinition)xsdAttributeGroupContent;
        result.putAll(getProhibitedAttributes(xsdAttributeGroupDefinition.getContents()));
      }
      else
      {
        XSDAttributeUse xsdAttributeUse = (XSDAttributeUse)xsdAttributeGroupContent;
        if (xsdAttributeUse.getUse() == XSDAttributeUseCategory.PROHIBITED_LITERAL)
        {
          XSDAttributeDeclaration xsdAttributeDeclaration = xsdAttributeUse.getAttributeDeclaration();
          result.put(xsdAttributeDeclaration.getURI(), xsdAttributeDeclaration);
        }
      }
    }
    return result;
  }

  public static void validateAttributeGroup
    (XSDConcreteComponentImpl xsdConcreteComponent, 
     XSDWildcard containedWildcard,
     EList<XSDAttributeUse> baseXSDAttributeUses, 
     EList<XSDAttributeUse> xsdAttributeUses, 
     XSDWildcard baseXSDWildcard, 
     XSDWildcard xsdWildcard)
  {
    List<XSDAttributeUse> unmatchedBaseAttributeUses = new ArrayList<XSDAttributeUse>(baseXSDAttributeUses);
    for (XSDAttributeUse xsdAttributeUse : xsdAttributeUses)
    {
      XSDAttributeDeclaration xsdAttributeDeclaration = xsdAttributeUse.getAttributeDeclaration();
      boolean matched = false;
      for (XSDAttributeUse baseXSDAttributeUse :  baseXSDAttributeUses)
      {
        XSDAttributeDeclaration baseXSDAttributeDeclaration = baseXSDAttributeUse.getAttributeDeclaration();
        if (xsdAttributeDeclaration.hasSameNameAndTargetNamespace(baseXSDAttributeDeclaration))
        {
          if (xsdAttributeDeclaration.getTypeDefinition() != null)
          {
            XSDTypeDefinition badType = 
              xsdAttributeDeclaration.getTypeDefinition().getBadTypeDerivation
                (baseXSDAttributeDeclaration.getTypeDefinition(), true, true);
            if (badType != null)
            {
              xsdConcreteComponent.getDiagnosticTarget(xsdAttributeUse).createDiagnostic
                (XSDDiagnosticSeverity.ERROR_LITERAL, 
                 "derivation-ok-restriction.2.1.2", 
                 xsdAttributeDeclaration.getURI(),
                 baseXSDAttributeDeclaration.getTypeDefinition().getURI());
            }
          }

          if (baseXSDAttributeUse.isRequired() && !xsdAttributeUse.isRequired())
          {
            xsdConcreteComponent.getDiagnosticTarget(xsdAttributeUse).createDiagnostic
              (XSDDiagnosticSeverity.ERROR_LITERAL, "derivation-ok-restriction.2.1.1", xsdAttributeDeclaration.getURI());
          }

          unmatchedBaseAttributeUses.remove(baseXSDAttributeUse);
          matched = true;
          break;
        }
      }

      if (!matched)
      {
        if (baseXSDWildcard == null || !baseXSDWildcard.allows(xsdAttributeDeclaration.getTargetNamespace()))
        {
          xsdConcreteComponent.getDiagnosticTarget(xsdAttributeUse).createDiagnostic
            (XSDDiagnosticSeverity.ERROR_LITERAL, "derivation-ok-restriction.2.2", xsdAttributeDeclaration.getURI());
        }
      }
    }

    for (XSDAttributeUse baseXSDAttributeUse : unmatchedBaseAttributeUses)
    {
      if (baseXSDAttributeUse.isRequired())
      {
        xsdConcreteComponent.createDiagnostic
          (XSDDiagnosticSeverity.ERROR_LITERAL, 
           "derivation-ok-restriction.3", 
           baseXSDAttributeUse.getAttributeDeclaration().getURI());
      }
    }

    if (xsdWildcard != null)
    {
      if (baseXSDWildcard == null || !xsdWildcard.isWildcardSubset(baseXSDWildcard))
      {
        XSDConcreteComponentImpl target = xsdConcreteComponent;
        if (containedWildcard != null)
        {
          target = (XSDConcreteComponentImpl)containedWildcard;
        }
        target.createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "derivation-ok-restriction.4");
      }
    }
  }

  @Override
  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    switch (XSDConstants.nodeType(changedElement))
    {
      case XSDConstants.COMPLEXTYPE_ELEMENT:
      {
        if (changedElement.hasAttributeNS(null, XSDConstants.ABSTRACT_ATTRIBUTE))
        {
          String newAbstract = changedElement.getAttributeNS(null, XSDConstants.ABSTRACT_ATTRIBUTE);
          setAbstract(convertToBoolean(newAbstract));
        }
        else
        {
          unsetAbstract();
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

        if (changedElement.hasAttributeNS(null, XSDConstants.MIXED_ATTRIBUTE))
        {
          String newAbstract = changedElement.getAttributeNS(null, XSDConstants.MIXED_ATTRIBUTE);
          setMixed(convertToBoolean(newAbstract));
        }
        else
        {
          Element [] contentElementPair = getContentElementPair();
          if (contentElementPair != null && 
                contentElementPair[0] != null &&
                contentElementPair[0].hasAttributeNS(null, XSDConstants.MIXED_ATTRIBUTE))
          {
            String newAbstract = contentElementPair[0].getAttributeNS(null, XSDConstants.MIXED_ATTRIBUTE);
            setMixed(convertToBoolean(newAbstract));
          }
          else
          {
            unsetMixed();
          }
        }

        break;
      }
      case XSDConstants.COMPLEXCONTENT_ELEMENT:
      {
        if (changedElement.hasAttributeNS(null, XSDConstants.MIXED_ATTRIBUTE))
        {
          String newMixed = changedElement.getAttributeNS(null, XSDConstants.MIXED_ATTRIBUTE);
          setMixed(convertToBoolean(newMixed));
        }
        else
        {
          Element theElement = getElement();
          if (!theElement.hasAttributeNS(null, XSDConstants.MIXED_ATTRIBUTE))
          {
            unsetMixed();
          }
        }
        break;
      }
      case XSDConstants.EXTENSION_ELEMENT:
      case XSDConstants.RESTRICTION_ELEMENT:
      {

        XSDTypeDefinition newBaseTypeDefinition = getBaseTypeDefinition();
        if (changedElement.hasAttributeNS(null, XSDConstants.BASE_ATTRIBUTE))
        {
          newBaseTypeDefinition =
            resolveSimpleTypeDefinitionURI(XSDConstants.lookupQNameForAttribute(changedElement, XSDConstants.BASE_ATTRIBUTE));
        }
        handleNewBaseTypeDefinition(newBaseTypeDefinition);
        break;
      }
    }
  }

  protected XSDTypeDefinition handleNewBaseTypeDefinition(XSDTypeDefinition newBaseTypeDefinition)
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
            XSDSchemaImpl.getSchemaForSchema(theElement.getNamespaceURI()).resolveComplexTypeDefinition("anyType");
        }
        else
        {
          newBaseTypeDefinition = this;
        }
      }
      else
      {
        newBaseTypeDefinition = xsdSchema.getSchemaForSchema().resolveComplexTypeDefinition("anyType");
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
            newBaseTypeDefinition = (XSDTypeDefinition)replacement;
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
    if (eReference == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__CONTENT ||
          eReference == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_CONTENTS ||
          eReference == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT)
    {
      Element [] contentElementPair = getContentElementPair();
      if (contentElementPair[1] != null)
      {
        return contentElementPair[1];
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
        case XSDConstants.SIMPLECONTENT_ELEMENT:
        {
          result.add((Element)child);

          // DROP to case
        }
        case XSDConstants.COMPLEXCONTENT_ELEMENT:
        {
          for (Node grandChild = child.getFirstChild(); grandChild != null; grandChild = grandChild.getNextSibling())
          {
            switch (XSDConstants.nodeType(grandChild))
            {
              case XSDConstants.ANNOTATION_ELEMENT:
              {
                result.add((Element)grandChild);
                break;
              }
              case XSDConstants.EXTENSION_ELEMENT:
              case XSDConstants.RESTRICTION_ELEMENT:
              {
                for (Node greatGrandChild = grandChild.getFirstChild(); 
                     greatGrandChild != null; 
                     greatGrandChild = greatGrandChild.getNextSibling())
                {
                  switch (XSDConstants.nodeType(greatGrandChild))
                  {
                    case XSDConstants.GROUP_ELEMENT:
                    case XSDConstants.ALL_ELEMENT:
                    case XSDConstants.CHOICE_ELEMENT:
                    case XSDConstants.SEQUENCE_ELEMENT:
                    case XSDConstants.ANYATTRIBUTE_ELEMENT:
                    case XSDConstants.ATTRIBUTE_ELEMENT:
                    case XSDConstants.ATTRIBUTEGROUP_ELEMENT:
                    case XSDConstants.ANNOTATION_ELEMENT:
                    {
                      result.add((Element)greatGrandChild);
                      break;
                    }
                  }
                }
                break;
              }
            }
          }
          break;
        }
        case XSDConstants.GROUP_ELEMENT:
        case XSDConstants.ALL_ELEMENT:
        case XSDConstants.CHOICE_ELEMENT:
        case XSDConstants.SEQUENCE_ELEMENT:
        case XSDConstants.ANYATTRIBUTE_ELEMENT:
        case XSDConstants.ATTRIBUTE_ELEMENT:
        case XSDConstants.ATTRIBUTEGROUP_ELEMENT:
        case XSDConstants.ANNOTATION_ELEMENT:
        {
          result.add((Element)child);
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
      case XSDConstants.GROUP_ELEMENT:
      case XSDConstants.ALL_ELEMENT:
      case XSDConstants.CHOICE_ELEMENT:
      case XSDConstants.SEQUENCE_ELEMENT:
      {
        XSDParticle xsdParticle = XSDParticleImpl.createParticle(child);
        if (xsdParticle != null)
        {
          newContents.add(xsdParticle);
        }
        break;
      }
      case XSDConstants.SIMPLECONTENT_ELEMENT:
      {
        XSDSimpleTypeDefinition xsdSimpleTypeDefinition = XSDSimpleTypeDefinitionImpl.createSimpleTypeDefinition(child);
        if (xsdSimpleTypeDefinition != null)
        {
          newContents.add(xsdSimpleTypeDefinition);
        }
        break;
      }
      case XSDConstants.ATTRIBUTE_ELEMENT:
      {
        XSDAttributeUse xsdAttributeUse = XSDAttributeUseImpl.createAttributeUse(child);
        if (xsdAttributeUse != null)
        {
          newContents.add(xsdAttributeUse);
        }
        break;
      }
      case XSDConstants.ATTRIBUTEGROUP_ELEMENT:
      {
        XSDAttributeGroupDefinition xsdAttributeGroupDefinition = XSDAttributeGroupDefinitionImpl.createAttributeGroupDefinition(child);
        if (xsdAttributeGroupDefinition != null)
        {
          newContents.add(xsdAttributeGroupDefinition);
        }
        break;
      }
      case XSDConstants.ANYATTRIBUTE_ELEMENT:
      {
        XSDWildcard xsdWildcard = XSDWildcardImpl.createWildcard(child);
        if (xsdWildcard != null)
        {
          newContents.add(xsdWildcard);
        }
        break;
      }
      case XSDConstants.ANNOTATION_ELEMENT:
      {
        XSDAnnotation xsdAnnotation = XSDAnnotationImpl.createAnnotation(child);
        if (xsdAnnotation != null)
        {
          newContents.add(xsdAnnotation);
        }
        break;
      }
    }
  }

  @Override
  protected void handleReconciliation(List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    handleAnnotationReconciliation(XSDPackage.Literals.XSD_TYPE_DEFINITION__ANNOTATION, newContents, remainingContents);
    handleAnnotationReconciliation(XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_ANNOTATION, newContents, remainingContents);
    handleAnnotationReconciliation(XSDPackage.Literals.XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION, newContents, remainingContents);

    XSDTypeDefinition newBaseTypeDefinition = null;
    XSDComplexTypeContent newContent =  null;

    if (!newContents.isEmpty() && newContents.get(0) instanceof XSDComplexTypeContent)
    {
      newContent = (XSDComplexTypeContent)newContents.get(0);
      newContents.remove(0);
    }

    if (newContent != getContent())
    {
      remainingContents.remove(getContent());
      setContent(newContent);
    }

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
      getAttributeContents().removeAll(remainingContents);
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
      setListContentAndOrder(getAttributeContents(), list);
    }

    XSDDerivationMethod newDerivationMethod = null;
    Element theElement = getElement();
    LOOP:
    for (Node child = theElement.getFirstChild(); child != null; child = child.getNextSibling())
    {
      int childNodeType = XSDConstants.nodeType(child);
      switch (childNodeType)
      {
        case XSDConstants.SIMPLECONTENT_ELEMENT:
        case XSDConstants.COMPLEXCONTENT_ELEMENT:
        {
          for (Node grandChild = child.getFirstChild(); grandChild != null; grandChild = grandChild.getNextSibling())
          {
            int grandChildNodeType = XSDConstants.nodeType(grandChild);
            switch (grandChildNodeType)
            {
              case XSDConstants.EXTENSION_ELEMENT:
              case XSDConstants.RESTRICTION_ELEMENT:
              {
                newDerivationMethod = 
                  grandChildNodeType == XSDConstants.EXTENSION_ELEMENT ?
                    XSDDerivationMethod.EXTENSION_LITERAL :
                    XSDDerivationMethod.RESTRICTION_LITERAL;

                Element elementGrandChild = (Element)grandChild;
                if (elementGrandChild.hasAttributeNS(null, XSDConstants.BASE_ATTRIBUTE))
                {
                  newBaseTypeDefinition = 
                    resolveTypeDefinitionURI(XSDConstants.lookupQNameForAttribute(elementGrandChild, XSDConstants.BASE_ATTRIBUTE));
                }
                break LOOP;
              }
            }
          }
          break LOOP;
        }
        case XSDConstants.GROUP_ELEMENT:
        case XSDConstants.ALL_ELEMENT:
        case XSDConstants.CHOICE_ELEMENT:
        case XSDConstants.SEQUENCE_ELEMENT:
        {
          break LOOP;
        }
      }
    }

    handleNewBaseTypeDefinition(newBaseTypeDefinition);

    if (newDerivationMethod == null)
    {
      unsetDerivationMethod();
    }
    else if (!isSetDerivationMethod() || newDerivationMethod != getDerivationMethod())
    {
      setDerivationMethod(newDerivationMethod);
    }
  }

  @Override
  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    Element theElement = getElement();
    if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__ABSTRACT)
    {
      if (theElement != null)
      {
        niceSetAttribute(theElement, XSDConstants.ABSTRACT_ATTRIBUTE, isSetAbstract() ? isAbstract() ? "true" : "false" : null);
      }
    }
    if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__MIXED)
    {
      if (theElement != null)
      {
        Element targetElement = theElement;
        if (!theElement.hasAttributeNS(null, XSDConstants.MIXED_ATTRIBUTE))
        {
          Element [] contentElementPair = getContentElementPair();
          if (contentElementPair != null && 
                contentElementPair[0] != null && 
                contentElementPair[0].hasAttributeNS(null, XSDConstants.MIXED_ATTRIBUTE))
          {
            targetElement = contentElementPair[0];
          }
        }
        niceSetAttribute(targetElement, XSDConstants.MIXED_ATTRIBUTE, isSetMixed() ? isMixed() ? "true" : "false" : null);
      }
    }
    if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__LEXICAL_FINAL)
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
    if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__BLOCK)
    {
      if (theElement != null)
      {
        niceSetAttribute
          (theElement,
           XSDConstants.BLOCK_ATTRIBUTE,
           getStringBlock());
      }
      if (eAttribute != null)
      {
        traverseToRootForPatching();
      }
    }
    if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__DERIVATION_METHOD)
    {
      if (theElement != null && !isReconciling && (eAttribute == null || !abbreviate()))
      {
        Element [] contentElementPair = findOrCreateContentElementPair();
        if (contentElementPair[1] != null && 
              isSetDerivationMethod() && 
              !contentElementPair[1].getLocalName().equals(getDerivationMethod().getName()))
        {
          Element newElement = createDerivationElement();
          forceReplace(newElement, contentElementPair[1]);
        }
      }
    }
  }

  @Override
  protected void changeReference(EReference eReference)
  {
    super.changeReference(eReference);
    Element theElement = getElement();
    if (eReference == null || eReference == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__BASE_TYPE_DEFINITION)
    {
      XSDTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();
      if (theElement != null && !isReconciling && (eReference == null || !abbreviate()))
      {
        Element [] contentElementPair = findOrCreateContentElementPair();
        if (contentElementPair[1] != null)
        {
          XSDSimpleTypeDefinitionImpl theSimpleType = (XSDSimpleTypeDefinitionImpl)getSimpleType();
          if (theSimpleType != null)
          {
            theSimpleType.updatingDOM = true;
          }
          niceSetAttributeURIValue
            (contentElementPair[1], 
             XSDConstants.BASE_ATTRIBUTE, 
             theBaseTypeDefinition == null ?
               theElement.getNamespaceURI() + "#anyType" :
               theBaseTypeDefinition.getURI());
          if (theSimpleType != null)
          {
            theSimpleType.updatingDOM = false;
          }
        }
      }
    }
  }

  protected boolean abbreviate()
  {
    Element theElement = getElement();
    XSDTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();
    if ((theBaseTypeDefinition == null || XSDConstants.isURType(theBaseTypeDefinition)) && 
          XSDDerivationMethod.EXTENSION_LITERAL != getDerivationMethod() &&
          !(getContent() instanceof XSDSimpleTypeDefinition) &&
          getDerivationAnnotation() == null &&
          getContentAnnotation() == null)
    {
      Element [] contentElementPair = getContentElementPair();
      if (contentElementPair[1] != null)
      {
        for (Node child = contentElementPair[1].getFirstChild(); child != null; child = child.getNextSibling())
        {
          switch (XSDConstants.nodeType(child))
          {
            case XSDConstants.GROUP_ELEMENT:
            case XSDConstants.ALL_ELEMENT:
            case XSDConstants.CHOICE_ELEMENT:
            case XSDConstants.SEQUENCE_ELEMENT:
            case XSDConstants.ANYATTRIBUTE_ELEMENT:
            case XSDConstants.ATTRIBUTE_ELEMENT:
            case XSDConstants.ATTRIBUTEGROUP_ELEMENT:
            {
              forceEngulf(child, theElement, null);
              niceRemoveChild(theElement, contentElementPair[0]);
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  protected Element [] findOrCreateContentElementPair()
  {
    Element theElement = getElement();
    if (theElement != null && !isReconciling)
    {
      Element [] contentElementPair = getContentElementPair();
      if (contentElementPair[0] == null || contentElementPair[1] == null)
      {
        XSDTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();
        if (((theBaseTypeDefinition != null && !XSDConstants.isURType(theBaseTypeDefinition)) || 
               XSDDerivationMethod.EXTENSION_LITERAL == getDerivationMethod()) && 
              !(getContent() instanceof XSDSimpleTypeDefinition))
        {
          contentElementPair = createContentElementPair();
          Node startingNode = theElement.getFirstChild();
          for (Node child = startingNode; child != null; child = child.getNextSibling())
          {
            if (XSDConstants.nodeType(child) == XSDConstants.ANNOTATION_ELEMENT)
            {
              startingNode = child.getNextSibling();
            }
          }

          forceEngulf(contentElementPair[0], contentElementPair[1], theElement, startingNode);

          changeReference(XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__BASE_TYPE_DEFINITION);
        }
      }

      return contentElementPair;
    }
    else
    {
      return null;
    }
  }

  protected Element [] getContentElementPair()
  {
    Element theElement = getElement();
    if (theElement != null)
    {
      Element [] result = new Element [2];
      LOOP:
      for (Node child = theElement.getFirstChild(); child != null; child = child.getNextSibling())
      {
        switch (XSDConstants.nodeType(child))
        {
          case XSDConstants.SIMPLECONTENT_ELEMENT:
          case XSDConstants.COMPLEXCONTENT_ELEMENT:
          {
            result[0] = (Element)child;
            for (Node grandChild = child.getFirstChild(); grandChild != null; grandChild = grandChild.getNextSibling())
            {
              switch (XSDConstants.nodeType(grandChild))
              {
                case XSDConstants.EXTENSION_ELEMENT:
                case XSDConstants.RESTRICTION_ELEMENT:
                {
                  result[1] = (Element)grandChild;
                  break LOOP;
                }
              }
            }
            break LOOP;
          }
        }
      }
      return result;
    }
    else
    {
      return null;
    }
  }

  @Override
  protected void adoptContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    Node firstAdoptee = null;

    if (eReference == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__CONTENT)
    {
      if (xsdConcreteComponent instanceof XSDSimpleTypeDefinition)
      {
        Element [] contentElementPair = getContentElementPair();
        if (contentElementPair != null)
        {
          if (contentElementPair[0] != null)
          {
            if (XSDConstants.nodeType(contentElementPair[0]) == XSDConstants.COMPLEXCONTENT_ELEMENT)
            {
              niceRemoveChild(getElement(), contentElementPair[0]);
            }
            if (contentElementPair[1] != null)
            {
              for (Node child = contentElementPair[1].getFirstChild(); child != null; child = child.getNextSibling())
              {
                switch (XSDConstants.nodeType(child))
                {
                  case XSDConstants.GROUP_ELEMENT:
                  case XSDConstants.ALL_ELEMENT:
                  case XSDConstants.CHOICE_ELEMENT:
                  case XSDConstants.SEQUENCE_ELEMENT:
                  case XSDConstants.ANYATTRIBUTE_ELEMENT:
                  case XSDConstants.ATTRIBUTE_ELEMENT:
                  case XSDConstants.ATTRIBUTEGROUP_ELEMENT:
                  {
                    firstAdoptee = child;
                    break;
                  }
                }
              }
            }
          }
        }
      }
    }

    super.adoptContent(eReference, xsdConcreteComponent);

    if (eReference == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__CONTENT)
    {
      Element theElement = getElement();
      if (theElement != null && !isReconciling)
      {
        if (xsdConcreteComponent instanceof XSDSimpleTypeDefinition)
        {
          changeReference(XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__BASE_TYPE_DEFINITION);
        }

        Element [] contentElementPair = getContentElementPair();
        if (contentElementPair != null && contentElementPair[1] != null)
        {
          if (firstAdoptee == null)
          {
            for (Node child = getElement().getFirstChild(); child != null; child = child.getNextSibling())
            {
              switch (XSDConstants.nodeType(child))
              {
                case XSDConstants.GROUP_ELEMENT:
                case XSDConstants.ALL_ELEMENT:
                case XSDConstants.CHOICE_ELEMENT:
                case XSDConstants.SEQUENCE_ELEMENT:
                case XSDConstants.ANYATTRIBUTE_ELEMENT:
                case XSDConstants.ATTRIBUTE_ELEMENT:
                case XSDConstants.ATTRIBUTEGROUP_ELEMENT:
                {
                  firstAdoptee = child;
                  break;
                }
              }
            }
          }

          if (firstAdoptee != null)
          {
            forceEngulf(firstAdoptee, contentElementPair[1], null);
          }
        }
      }

      traverseToRootForPatching();
    }
    else if (eReference == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_CONTENTS)
    {
      traverseToRootForPatching();
    }
    else if (eReference == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT)
    {
      traverseToRootForPatching();
    }
    else if (eReference == XSDPackage.Literals.XSD_TYPE_DEFINITION__ANNOTATION)
    {
      getAnnotations().add(0, (XSDAnnotation)xsdConcreteComponent);
    }
    else if (eReference == XSDPackage.Literals.XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION)
    {
      getAnnotations().add(getAnnotation() == null ? 0 : 1, (XSDAnnotation)xsdConcreteComponent);
    }
    else if (eReference == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_ANNOTATION)
    {
      getAnnotations().add((XSDAnnotation)xsdConcreteComponent);
    }
  }

  @Override
  protected void orphanContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    Node firstAdoptee = null;
    if (eReference == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__CONTENT)
    {
      if (xsdConcreteComponent instanceof XSDSimpleTypeDefinition)
      {
        Element [] contentElementPair = getContentElementPair();
        if (contentElementPair != null)
        {
          if (contentElementPair[1] != null)
          {
            for (Node child = contentElementPair[1].getFirstChild(); child != null; child = child.getNextSibling())
            {
              switch (XSDConstants.nodeType(child))
              {
                case XSDConstants.GROUP_ELEMENT:
                case XSDConstants.ALL_ELEMENT:
                case XSDConstants.CHOICE_ELEMENT:
                case XSDConstants.SEQUENCE_ELEMENT:
                case XSDConstants.ANYATTRIBUTE_ELEMENT:
                case XSDConstants.ATTRIBUTE_ELEMENT:
                case XSDConstants.ATTRIBUTEGROUP_ELEMENT:
                {
                  firstAdoptee = child;
                  break;
                }
              }
            }
          }
        }
      }
    }

    super.orphanContent(eReference, xsdConcreteComponent);

    if (eReference == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__CONTENT)
    {
      if (firstAdoptee != null)
      {
        Element theElement = getElement();
        if (theElement != null && !isReconciling)
        {
          Element [] contentElementPair = findOrCreateContentElementPair();
          if (contentElementPair != null && contentElementPair[1] != null)
          {
            forceEngulf(firstAdoptee, contentElementPair[1], null);
          }
          else
          {
            forceEngulf(firstAdoptee, theElement, null);
          }
        }
      }

      traverseToRootForPatching();
    }
    else if (eReference == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_CONTENTS)
    {
      traverseToRootForPatching();
    }
    else if (eReference == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT)
    {
      traverseToRootForPatching();
    }
    else if (eReference == XSDPackage.Literals.XSD_TYPE_DEFINITION__ANNOTATION ||
               eReference == XSDPackage.Literals.XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION ||
               eReference == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_ANNOTATION)
    {
      getAnnotations().remove(xsdConcreteComponent);
    }
  }

  @Override
  public XSDTypeDefinition getBaseType()
  {
    return getBaseTypeDefinition();
  }

  @Override
  public XSDTypeDefinition getRootType()
  {
    return getRootTypeDefinition();
  }

  public XSDTypeDefinition getRootTypeDefinition()
  {
    XSDTypeDefinition result = this;
    if (!isCircular())
    {
      for (XSDTypeDefinition theBaseType= result.getBaseType();
           theBaseType!= null && !XSDConstants.isURType(theBaseType);
           theBaseType = theBaseType.getBaseType())
      {
        result = theBaseType;
      }
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDParticle getSyntheticParticle()
  {
    return syntheticParticle;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSyntheticParticle(XSDParticle newSyntheticParticle, NotificationChain msgs)
  {
    XSDParticle oldSyntheticParticle = syntheticParticle;
    syntheticParticle = newSyntheticParticle;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_PARTICLE, oldSyntheticParticle, newSyntheticParticle);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSyntheticParticle(XSDParticle newSyntheticParticle)
  {
    if (newSyntheticParticle != syntheticParticle)
    {
      NotificationChain msgs = null;
      if (syntheticParticle != null)
        msgs = ((InternalEObject)syntheticParticle).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_PARTICLE, null, msgs);
      if (newSyntheticParticle != null)
        msgs = ((InternalEObject)newSyntheticParticle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_PARTICLE, null, msgs);
      msgs = basicSetSyntheticParticle(newSyntheticParticle, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_PARTICLE, newSyntheticParticle, newSyntheticParticle));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_WILDCARD, oldSyntheticWildcard, newSyntheticWildcard);
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
        msgs = ((InternalEObject)syntheticWildcard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_WILDCARD, null, msgs);
      if (newSyntheticWildcard != null)
        msgs = ((InternalEObject)newSyntheticWildcard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_WILDCARD, null, msgs);
      msgs = basicSetSyntheticWildcard(newSyntheticWildcard, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_WILDCARD, newSyntheticWildcard, newSyntheticWildcard));
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
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_ANNOTATION:
        return basicSetContentAnnotation(null, msgs);
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT:
        return basicSetContent(null, msgs);
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_CONTENTS:
        return ((InternalEList<?>)getAttributeContents()).basicRemove(otherEnd, msgs);
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT:
        return basicSetAttributeWildcardContent(null, msgs);
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_PARTICLE:
        return basicSetSyntheticParticle(null, msgs);
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_WILDCARD:
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
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__DERIVATION_METHOD:
        return getDerivationMethod();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__FINAL:
        return getFinal();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ABSTRACT:
        return isAbstract() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_TYPE_CATEGORY:
        return getContentTypeCategory();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__PROHIBITED_SUBSTITUTIONS:
        return getProhibitedSubstitutions();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__LEXICAL_FINAL:
        return getLexicalFinal();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__BLOCK:
        return getBlock();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__MIXED:
        return isMixed() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_ANNOTATION:
        return getContentAnnotation();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__BASE_TYPE_DEFINITION:
        return getBaseTypeDefinition();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT:
        return getContent();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_TYPE:
        return getContentType();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_USES:
        return getAttributeUses();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_CONTENTS:
        return getAttributeContents();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD:
        return getAttributeWildcard();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT:
        return getAttributeWildcardContent();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ROOT_TYPE_DEFINITION:
        return getRootTypeDefinition();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_PARTICLE:
        return getSyntheticParticle();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_WILDCARD:
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
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__DERIVATION_METHOD:
        setDerivationMethod((XSDDerivationMethod)newValue);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__FINAL:
        getFinal().clear();
        getFinal().addAll((Collection<? extends XSDComplexFinal>)newValue);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ABSTRACT:
        setAbstract(((Boolean)newValue).booleanValue());
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_TYPE_CATEGORY:
        setContentTypeCategory((XSDContentTypeCategory)newValue);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__PROHIBITED_SUBSTITUTIONS:
        getProhibitedSubstitutions().clear();
        getProhibitedSubstitutions().addAll((Collection<? extends XSDProhibitedSubstitutions>)newValue);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__LEXICAL_FINAL:
        getLexicalFinal().clear();
        getLexicalFinal().addAll((Collection<? extends XSDComplexFinal>)newValue);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__BLOCK:
        getBlock().clear();
        getBlock().addAll((Collection<? extends XSDProhibitedSubstitutions>)newValue);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__MIXED:
        setMixed(((Boolean)newValue).booleanValue());
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_ANNOTATION:
        setContentAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__BASE_TYPE_DEFINITION:
        setBaseTypeDefinition((XSDTypeDefinition)newValue);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT:
        setContent((XSDComplexTypeContent)newValue);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_TYPE:
        setContentType((XSDComplexTypeContent)newValue);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_USES:
        getAttributeUses().clear();
        getAttributeUses().addAll((Collection<? extends XSDAttributeUse>)newValue);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_CONTENTS:
        getAttributeContents().clear();
        getAttributeContents().addAll((Collection<? extends XSDAttributeGroupContent>)newValue);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD:
        setAttributeWildcard((XSDWildcard)newValue);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT:
        setAttributeWildcardContent((XSDWildcard)newValue);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_PARTICLE:
        setSyntheticParticle((XSDParticle)newValue);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_WILDCARD:
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
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__DERIVATION_METHOD:
        unsetDerivationMethod();
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__FINAL:
        getFinal().clear();
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ABSTRACT:
        unsetAbstract();
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_TYPE_CATEGORY:
        setContentTypeCategory(CONTENT_TYPE_CATEGORY_EDEFAULT);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__PROHIBITED_SUBSTITUTIONS:
        getProhibitedSubstitutions().clear();
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__LEXICAL_FINAL:
        unsetLexicalFinal();
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__BLOCK:
        unsetBlock();
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__MIXED:
        unsetMixed();
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_ANNOTATION:
        setContentAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__BASE_TYPE_DEFINITION:
        setBaseTypeDefinition((XSDTypeDefinition)null);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT:
        setContent((XSDComplexTypeContent)null);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_TYPE:
        setContentType((XSDComplexTypeContent)null);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_USES:
        getAttributeUses().clear();
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_CONTENTS:
        getAttributeContents().clear();
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD:
        setAttributeWildcard((XSDWildcard)null);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT:
        setAttributeWildcardContent((XSDWildcard)null);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_PARTICLE:
        setSyntheticParticle((XSDParticle)null);
        return;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_WILDCARD:
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
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__DERIVATION_METHOD:
        return isSetDerivationMethod();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__FINAL:
        return final_ != null && !final_.isEmpty();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ABSTRACT:
        return isSetAbstract();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_TYPE_CATEGORY:
        return contentTypeCategory != CONTENT_TYPE_CATEGORY_EDEFAULT;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__PROHIBITED_SUBSTITUTIONS:
        return prohibitedSubstitutions != null && !prohibitedSubstitutions.isEmpty();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__LEXICAL_FINAL:
        return isSetLexicalFinal();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__BLOCK:
        return isSetBlock();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__MIXED:
        return isSetMixed();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_ANNOTATION:
        return contentAnnotation != null;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__BASE_TYPE_DEFINITION:
        return baseTypeDefinition != null;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT:
        return content != null;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__CONTENT_TYPE:
        return contentType != null;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_USES:
        return attributeUses != null && !attributeUses.isEmpty();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_CONTENTS:
        return attributeContents != null && !attributeContents.isEmpty();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD:
        return attributeWildcard != null;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT:
        return attributeWildcardContent != null;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__ROOT_TYPE_DEFINITION:
        return getRootTypeDefinition() != null;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_PARTICLE:
        return syntheticParticle != null;
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_WILDCARD:
        return syntheticWildcard != null;
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
    result.append(" (derivationMethod: ");
    if ((eFlags & DERIVATION_METHOD_ESETFLAG) != 0) result.append(derivationMethod); else result.append("<unset>");
    result.append(", final: ");
    result.append(final_);
    result.append(", abstract: ");
    if ((eFlags & ABSTRACT_ESETFLAG) != 0) result.append((eFlags & ABSTRACT_EFLAG) != 0); else result.append("<unset>");
    result.append(", contentTypeCategory: ");
    result.append(contentTypeCategory);
    result.append(", prohibitedSubstitutions: ");
    result.append(prohibitedSubstitutions);
    result.append(", lexicalFinal: ");
    result.append(lexicalFinal);
    result.append(", block: ");
    result.append(block);
    result.append(", mixed: ");
    if ((eFlags & MIXED_ESETFLAG) != 0) result.append((eFlags & MIXED_EFLAG) != 0); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

  @Override
  public XSDSimpleTypeDefinition getSimpleType()
  {
    return 
      getContentType() instanceof XSDSimpleTypeDefinition ?
        (XSDSimpleTypeDefinition)getContentType() :
        null;
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
      List<XSDComplexFinal> newLexicalFinal = new ArrayList<XSDComplexFinal>();
      for (StringTokenizer stringTokenizer = new StringTokenizer(finalDefault); stringTokenizer.hasMoreTokens(); )
      {
        String token = stringTokenizer.nextToken();
        if (token.equals("#all"))
        {
          token = "all";
        }
        XSDComplexFinal literal = XSDComplexFinal.get(token);
        if (literal != null)
        {
          newLexicalFinal.add(literal);
        }
      }
      if (!newLexicalFinal.equals(getLexicalFinal()))
      {
        Collection<XSDComplexFinal> oldContents = new ArrayList<XSDComplexFinal>(getLexicalFinal());
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
      List<XSDProhibitedSubstitutions> newBlock = new ArrayList<XSDProhibitedSubstitutions>();
      for (StringTokenizer stringTokenizer = new StringTokenizer(block); stringTokenizer.hasMoreTokens(); )
      {
        String token = stringTokenizer.nextToken();
        if (token.equals("#all"))
        {
          token = "all";
        }
        XSDProhibitedSubstitutions literal = XSDProhibitedSubstitutions.get(token);
        if (literal != null)
        {
          newBlock.add(literal);
        }
      }
      if (!newBlock.equals(getBlock()))
      {
        Collection<XSDProhibitedSubstitutions> oldContents = new ArrayList<XSDProhibitedSubstitutions>(getBlock());
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

  public String getStringProhibitedSubstitutions()
  {
    StringBuffer result = new StringBuffer();
    for (Object literal :  getProhibitedSubstitutions())
    {
      if (result.length() != 0)
      {
        result.append(' ');
      }
      result.append(literal);
    }
    return result.toString();
  }

  @Override
  public XSDTypeDefinition getBadTypeDerivation(XSDTypeDefinition xsdTypeDefinition, boolean extension, boolean restriction)
  {
    if (xsdTypeDefinition == this || isCircular())
    {
      return null;
    }
    else if (getDerivationMethod() == XSDDerivationMethod.EXTENSION_LITERAL ? !extension : !restriction)
    {
      return this;
    }
    else 
    {
      XSDTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();
      if (xsdTypeDefinition == theBaseTypeDefinition)
      {
        return null;
      }
      else if (theBaseTypeDefinition == null)
      {
        return this;
      }
      else if (XSDConstants.isURType(theBaseTypeDefinition))
      {
        return theBaseTypeDefinition;
      }
      else
      {
        return theBaseTypeDefinition.getBadTypeDerivation(xsdTypeDefinition, extension, restriction);
      }
    }
  }

  @Override
  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDComplexTypeDefinitionImpl clonedComplexTypeDefinition =
      (XSDComplexTypeDefinitionImpl)getXSDFactory().createXSDComplexTypeDefinition();
    clonedComplexTypeDefinition.isReconciling = true;

    if (getName() != null)
    {
      clonedComplexTypeDefinition.setName(getName());
    }
    if (isSetMixed())
    {
      clonedComplexTypeDefinition.setMixed(isMixed());
    }
    if (isSetDerivationMethod())
    {
      clonedComplexTypeDefinition.setDerivationMethod(getDerivationMethod());
    }
    if (isSetAbstract())
    {
      clonedComplexTypeDefinition.setAbstract(isAbstract());
    }
    if (isSetBlock())
    {
      if (!getBlock().isEmpty())
      {
        clonedComplexTypeDefinition.getBlock().addAll(getBlock());
      }
      else
      {
        clonedComplexTypeDefinition.getBlock().clear();
      }
    }
    if (isSetLexicalFinal())
    {
      if (!getLexicalFinal().isEmpty())
      {
        clonedComplexTypeDefinition.getLexicalFinal().addAll(getLexicalFinal());
      }
      else
      {
        clonedComplexTypeDefinition.getLexicalFinal().clear();
      }
    }

    if (getBaseTypeDefinition() != null) 
    {
      XSDTypeDefinition theBaseTypeDefinition = getBaseTypeDefinition();
      clonedComplexTypeDefinition.setBaseTypeDefinition
        (createUnresolvedTypeDefinition
          (theBaseTypeDefinition.getTargetNamespace(), theBaseTypeDefinition.getName()));
    }

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedComplexTypeDefinition.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
      if (getDerivationAnnotation() != null)
      {
        clonedComplexTypeDefinition.setDerivationAnnotation
          ((XSDAnnotation)getDerivationAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
      if (getContentAnnotation() != null)
      {
        clonedComplexTypeDefinition.setContentAnnotation((XSDAnnotation)getContentAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
      if (getContent() != null)
      {
        clonedComplexTypeDefinition.setContent((XSDComplexTypeContent)getContent().cloneConcreteComponent(deep, shareDOM));
      }
      if (!getAttributeContents().isEmpty())
      {
        clonedComplexTypeDefinition.getAttributeContents().addAll(cloneConcreteComponents(getAttributeContents(), deep, shareDOM));
      }
      if (getAttributeWildcardContent() != null)
      {
        clonedComplexTypeDefinition.setAttributeWildcardContent
          ((XSDWildcard)getAttributeWildcardContent().cloneConcreteComponent(deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedComplexTypeDefinition.setElement(getElement());
    }

    clonedComplexTypeDefinition.isReconciling = shareDOM;
    return clonedComplexTypeDefinition;
  }
}

