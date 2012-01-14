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
package org.eclipse.xsd.util;


import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.xsd.*;


/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage
 * @generated
 */
public class XSDAdapterFactory extends AdapterFactoryImpl
{

  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static XSDPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = XSDPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDSwitch<Adapter> modelSwitch =
    new XSDSwitch<Adapter>()
    {
      @Override
      public Adapter caseXSDAnnotation(XSDAnnotation object)
      {
        return createXSDAnnotationAdapter();
      }
      @Override
      public Adapter caseXSDAttributeDeclaration(XSDAttributeDeclaration object)
      {
        return createXSDAttributeDeclarationAdapter();
      }
      @Override
      public Adapter caseXSDAttributeGroupContent(XSDAttributeGroupContent object)
      {
        return createXSDAttributeGroupContentAdapter();
      }
      @Override
      public Adapter caseXSDAttributeGroupDefinition(XSDAttributeGroupDefinition object)
      {
        return createXSDAttributeGroupDefinitionAdapter();
      }
      @Override
      public Adapter caseXSDAttributeUse(XSDAttributeUse object)
      {
        return createXSDAttributeUseAdapter();
      }
      @Override
      public Adapter caseXSDBoundedFacet(XSDBoundedFacet object)
      {
        return createXSDBoundedFacetAdapter();
      }
      @Override
      public Adapter caseXSDCardinalityFacet(XSDCardinalityFacet object)
      {
        return createXSDCardinalityFacetAdapter();
      }
      @Override
      public Adapter caseXSDComplexTypeContent(XSDComplexTypeContent object)
      {
        return createXSDComplexTypeContentAdapter();
      }
      @Override
      public Adapter caseXSDComplexTypeDefinition(XSDComplexTypeDefinition object)
      {
        return createXSDComplexTypeDefinitionAdapter();
      }
      @Override
      public Adapter caseXSDComponent(XSDComponent object)
      {
        return createXSDComponentAdapter();
      }
      @Override
      public Adapter caseXSDConcreteComponent(XSDConcreteComponent object)
      {
        return createXSDConcreteComponentAdapter();
      }
      @Override
      public Adapter caseXSDConstrainingFacet(XSDConstrainingFacet object)
      {
        return createXSDConstrainingFacetAdapter();
      }
      @Override
      public Adapter caseXSDDiagnostic(XSDDiagnostic object)
      {
        return createXSDDiagnosticAdapter();
      }
      @Override
      public Adapter caseXSDElementDeclaration(XSDElementDeclaration object)
      {
        return createXSDElementDeclarationAdapter();
      }
      @Override
      public Adapter caseXSDEnumerationFacet(XSDEnumerationFacet object)
      {
        return createXSDEnumerationFacetAdapter();
      }
      @Override
      public Adapter caseXSDFacet(XSDFacet object)
      {
        return createXSDFacetAdapter();
      }
      @Override
      public Adapter caseXSDFeature(XSDFeature object)
      {
        return createXSDFeatureAdapter();
      }
      @Override
      public Adapter caseXSDFixedFacet(XSDFixedFacet object)
      {
        return createXSDFixedFacetAdapter();
      }
      @Override
      public Adapter caseXSDFractionDigitsFacet(XSDFractionDigitsFacet object)
      {
        return createXSDFractionDigitsFacetAdapter();
      }
      @Override
      public Adapter caseXSDFundamentalFacet(XSDFundamentalFacet object)
      {
        return createXSDFundamentalFacetAdapter();
      }
      @Override
      public Adapter caseXSDIdentityConstraintDefinition(XSDIdentityConstraintDefinition object)
      {
        return createXSDIdentityConstraintDefinitionAdapter();
      }
      @Override
      public Adapter caseXSDImport(XSDImport object)
      {
        return createXSDImportAdapter();
      }
      @Override
      public Adapter caseXSDInclude(XSDInclude object)
      {
        return createXSDIncludeAdapter();
      }
      @Override
      public Adapter caseXSDLengthFacet(XSDLengthFacet object)
      {
        return createXSDLengthFacetAdapter();
      }
      @Override
      public Adapter caseXSDMaxExclusiveFacet(XSDMaxExclusiveFacet object)
      {
        return createXSDMaxExclusiveFacetAdapter();
      }
      @Override
      public Adapter caseXSDMaxFacet(XSDMaxFacet object)
      {
        return createXSDMaxFacetAdapter();
      }
      @Override
      public Adapter caseXSDMaxInclusiveFacet(XSDMaxInclusiveFacet object)
      {
        return createXSDMaxInclusiveFacetAdapter();
      }
      @Override
      public Adapter caseXSDMaxLengthFacet(XSDMaxLengthFacet object)
      {
        return createXSDMaxLengthFacetAdapter();
      }
      @Override
      public Adapter caseXSDMinExclusiveFacet(XSDMinExclusiveFacet object)
      {
        return createXSDMinExclusiveFacetAdapter();
      }
      @Override
      public Adapter caseXSDMinFacet(XSDMinFacet object)
      {
        return createXSDMinFacetAdapter();
      }
      @Override
      public Adapter caseXSDMinInclusiveFacet(XSDMinInclusiveFacet object)
      {
        return createXSDMinInclusiveFacetAdapter();
      }
      @Override
      public Adapter caseXSDMinLengthFacet(XSDMinLengthFacet object)
      {
        return createXSDMinLengthFacetAdapter();
      }
      @Override
      public Adapter caseXSDModelGroup(XSDModelGroup object)
      {
        return createXSDModelGroupAdapter();
      }
      @Override
      public Adapter caseXSDModelGroupDefinition(XSDModelGroupDefinition object)
      {
        return createXSDModelGroupDefinitionAdapter();
      }
      @Override
      public Adapter caseXSDNamedComponent(XSDNamedComponent object)
      {
        return createXSDNamedComponentAdapter();
      }
      @Override
      public Adapter caseXSDNotationDeclaration(XSDNotationDeclaration object)
      {
        return createXSDNotationDeclarationAdapter();
      }
      @Override
      public Adapter caseXSDNumericFacet(XSDNumericFacet object)
      {
        return createXSDNumericFacetAdapter();
      }
      @Override
      public Adapter caseXSDOrderedFacet(XSDOrderedFacet object)
      {
        return createXSDOrderedFacetAdapter();
      }
      @Override
      public Adapter caseXSDParticle(XSDParticle object)
      {
        return createXSDParticleAdapter();
      }
      @Override
      public Adapter caseXSDParticleContent(XSDParticleContent object)
      {
        return createXSDParticleContentAdapter();
      }
      @Override
      public Adapter caseXSDPatternFacet(XSDPatternFacet object)
      {
        return createXSDPatternFacetAdapter();
      }
      @Override
      public Adapter caseXSDRedefinableComponent(XSDRedefinableComponent object)
      {
        return createXSDRedefinableComponentAdapter();
      }
      @Override
      public Adapter caseXSDRedefineContent(XSDRedefineContent object)
      {
        return createXSDRedefineContentAdapter();
      }
      @Override
      public Adapter caseXSDRedefine(XSDRedefine object)
      {
        return createXSDRedefineAdapter();
      }
      @Override
      public Adapter caseXSDRepeatableFacet(XSDRepeatableFacet object)
      {
        return createXSDRepeatableFacetAdapter();
      }
      @Override
      public Adapter caseXSDSchema(XSDSchema object)
      {
        return createXSDSchemaAdapter();
      }
      @Override
      public Adapter caseXSDSchemaCompositor(XSDSchemaCompositor object)
      {
        return createXSDSchemaCompositorAdapter();
      }
      @Override
      public Adapter caseXSDSchemaContent(XSDSchemaContent object)
      {
        return createXSDSchemaContentAdapter();
      }
      @Override
      public Adapter caseXSDSchemaDirective(XSDSchemaDirective object)
      {
        return createXSDSchemaDirectiveAdapter();
      }
      @Override
      public Adapter caseXSDScope(XSDScope object)
      {
        return createXSDScopeAdapter();
      }
      @Override
      public Adapter caseXSDSimpleTypeDefinition(XSDSimpleTypeDefinition object)
      {
        return createXSDSimpleTypeDefinitionAdapter();
      }
      @Override
      public Adapter caseXSDTerm(XSDTerm object)
      {
        return createXSDTermAdapter();
      }
      @Override
      public Adapter caseXSDTotalDigitsFacet(XSDTotalDigitsFacet object)
      {
        return createXSDTotalDigitsFacetAdapter();
      }
      @Override
      public Adapter caseXSDTypeDefinition(XSDTypeDefinition object)
      {
        return createXSDTypeDefinitionAdapter();
      }
      @Override
      public Adapter caseXSDWhiteSpaceFacet(XSDWhiteSpaceFacet object)
      {
        return createXSDWhiteSpaceFacetAdapter();
      }
      @Override
      public Adapter caseXSDWildcard(XSDWildcard object)
      {
        return createXSDWildcardAdapter();
      }
      @Override
      public Adapter caseXSDXPathDefinition(XSDXPathDefinition object)
      {
        return createXSDXPathDefinitionAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDAnnotation <em>Annotation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDAnnotation
   * @generated
   */
  public Adapter createXSDAnnotationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDAttributeDeclaration <em>Attribute Declaration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDAttributeDeclaration
   * @generated
   */
  public Adapter createXSDAttributeDeclarationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDAttributeGroupContent <em>Attribute Group Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDAttributeGroupContent
   * @generated
   */
  public Adapter createXSDAttributeGroupContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDAttributeGroupDefinition <em>Attribute Group Definition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDAttributeGroupDefinition
   * @generated
   */
  public Adapter createXSDAttributeGroupDefinitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDAttributeUse <em>Attribute Use</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDAttributeUse
   * @generated
   */
  public Adapter createXSDAttributeUseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDBoundedFacet <em>Bounded Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDBoundedFacet
   * @generated
   */
  public Adapter createXSDBoundedFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDCardinalityFacet <em>Cardinality Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDCardinalityFacet
   * @generated
   */
  public Adapter createXSDCardinalityFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDComplexTypeContent <em>Complex Type Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDComplexTypeContent
   * @generated
   */
  public Adapter createXSDComplexTypeContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDComplexTypeDefinition <em>Complex Type Definition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDComplexTypeDefinition
   * @generated
   */
  public Adapter createXSDComplexTypeDefinitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDComponent <em>Component</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDComponent
   * @generated
   */
  public Adapter createXSDComponentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDConcreteComponent <em>Concrete Component</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDConcreteComponent
   * @generated
   */
  public Adapter createXSDConcreteComponentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDConstrainingFacet <em>Constraining Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDConstrainingFacet
   * @generated
   */
  public Adapter createXSDConstrainingFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDDiagnostic <em>Diagnostic</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDDiagnostic
   * @generated
   */
  public Adapter createXSDDiagnosticAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDElementDeclaration <em>Element Declaration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDElementDeclaration
   * @generated
   */
  public Adapter createXSDElementDeclarationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDEnumerationFacet <em>Enumeration Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDEnumerationFacet
   * @generated
   */
  public Adapter createXSDEnumerationFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDFacet <em>Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDFacet
   * @generated
   */
  public Adapter createXSDFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDFeature
   * @generated
   */
  public Adapter createXSDFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDFixedFacet <em>Fixed Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDFixedFacet
   * @generated
   */
  public Adapter createXSDFixedFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDFractionDigitsFacet <em>Fraction Digits Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDFractionDigitsFacet
   * @generated
   */
  public Adapter createXSDFractionDigitsFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDFundamentalFacet <em>Fundamental Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDFundamentalFacet
   * @generated
   */
  public Adapter createXSDFundamentalFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDIdentityConstraintDefinition <em>Identity Constraint Definition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDIdentityConstraintDefinition
   * @generated
   */
  public Adapter createXSDIdentityConstraintDefinitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDImport <em>Import</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDImport
   * @generated
   */
  public Adapter createXSDImportAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDInclude <em>Include</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDInclude
   * @generated
   */
  public Adapter createXSDIncludeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDLengthFacet <em>Length Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDLengthFacet
   * @generated
   */
  public Adapter createXSDLengthFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDMaxExclusiveFacet <em>Max Exclusive Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDMaxExclusiveFacet
   * @generated
   */
  public Adapter createXSDMaxExclusiveFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDMaxFacet <em>Max Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDMaxFacet
   * @generated
   */
  public Adapter createXSDMaxFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDMaxInclusiveFacet <em>Max Inclusive Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDMaxInclusiveFacet
   * @generated
   */
  public Adapter createXSDMaxInclusiveFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDMaxLengthFacet <em>Max Length Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDMaxLengthFacet
   * @generated
   */
  public Adapter createXSDMaxLengthFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDMinExclusiveFacet <em>Min Exclusive Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDMinExclusiveFacet
   * @generated
   */
  public Adapter createXSDMinExclusiveFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDMinFacet <em>Min Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDMinFacet
   * @generated
   */
  public Adapter createXSDMinFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDMinInclusiveFacet <em>Min Inclusive Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDMinInclusiveFacet
   * @generated
   */
  public Adapter createXSDMinInclusiveFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDMinLengthFacet <em>Min Length Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDMinLengthFacet
   * @generated
   */
  public Adapter createXSDMinLengthFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDModelGroup <em>Model Group</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDModelGroup
   * @generated
   */
  public Adapter createXSDModelGroupAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDModelGroupDefinition <em>Model Group Definition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDModelGroupDefinition
   * @generated
   */
  public Adapter createXSDModelGroupDefinitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDNamedComponent <em>Named Component</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDNamedComponent
   * @generated
   */
  public Adapter createXSDNamedComponentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDNotationDeclaration <em>Notation Declaration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDNotationDeclaration
   * @generated
   */
  public Adapter createXSDNotationDeclarationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDNumericFacet <em>Numeric Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDNumericFacet
   * @generated
   */
  public Adapter createXSDNumericFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDOrderedFacet <em>Ordered Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDOrderedFacet
   * @generated
   */
  public Adapter createXSDOrderedFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDParticle <em>Particle</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDParticle
   * @generated
   */
  public Adapter createXSDParticleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDParticleContent <em>Particle Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDParticleContent
   * @generated
   */
  public Adapter createXSDParticleContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDPatternFacet <em>Pattern Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDPatternFacet
   * @generated
   */
  public Adapter createXSDPatternFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDRedefinableComponent <em>Redefinable Component</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDRedefinableComponent
   * @generated
   */
  public Adapter createXSDRedefinableComponentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDRedefineContent <em>Redefine Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDRedefineContent
   * @generated
   */
  public Adapter createXSDRedefineContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDRedefine <em>Redefine</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDRedefine
   * @generated
   */
  public Adapter createXSDRedefineAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDRepeatableFacet <em>Repeatable Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDRepeatableFacet
   * @generated
   */
  public Adapter createXSDRepeatableFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDSchema <em>Schema</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDSchema
   * @generated
   */
  public Adapter createXSDSchemaAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDSchemaCompositor <em>Schema Compositor</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDSchemaCompositor
   * @generated
   */
  public Adapter createXSDSchemaCompositorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDSchemaContent <em>Schema Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDSchemaContent
   * @generated
   */
  public Adapter createXSDSchemaContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDSchemaDirective <em>Schema Directive</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDSchemaDirective
   * @generated
   */
  public Adapter createXSDSchemaDirectiveAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDScope <em>Scope</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDScope
   * @generated
   */
  public Adapter createXSDScopeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDSimpleTypeDefinition <em>Simple Type Definition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDSimpleTypeDefinition
   * @generated
   */
  public Adapter createXSDSimpleTypeDefinitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDTerm <em>Term</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDTerm
   * @generated
   */
  public Adapter createXSDTermAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDTotalDigitsFacet <em>Total Digits Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDTotalDigitsFacet
   * @generated
   */
  public Adapter createXSDTotalDigitsFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDTypeDefinition <em>Type Definition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDTypeDefinition
   * @generated
   */
  public Adapter createXSDTypeDefinitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDWhiteSpaceFacet <em>White Space Facet</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDWhiteSpaceFacet
   * @generated
   */
  public Adapter createXSDWhiteSpaceFacetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDWildcard <em>Wildcard</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDWildcard
   * @generated
   */
  public Adapter createXSDWildcardAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDXPathDefinition <em>XPath Definition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xsd.XSDXPathDefinition
   * @generated
   */
  public Adapter createXSDXPathDefinitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //XSDAdapterFactory

