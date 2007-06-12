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
 * $Id: XSDConcreteComponent.java,v 1.9 2007/06/12 15:06:42 emerks Exp $
 */
package org.eclipse.xsd;


import java.util.Collection;

import org.w3c.dom.Element;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concrete Component</b></em>'.
 * It is the root of all concrete components.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDConcreteComponent#getElement <em>Element</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDConcreteComponent#getContainer <em>Container</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDConcreteComponent#getRootContainer <em>Root Container</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDConcreteComponent#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDConcreteComponent#getDiagnostics <em>Diagnostics</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDConcreteComponent()
 * @model abstract="true"
 * @generated
 */
public interface XSDConcreteComponent extends EObject
{
  /**
   * Returns the value of the '<em><b>Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This is the underlying DOM element associated with this component, 
   * i.e., the {@link #updateElement() serialization} of this component.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Element</em>' attribute.
   * @see #setElement(Element)
   * @see org.eclipse.xsd.XSDPackage#getXSDConcreteComponent_Element()
   * @model dataType="org.eclipse.xsd.DOMElement"
   * @generated
   */
  Element getElement();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDConcreteComponent#getElement <em>Element</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Element</em>' attribute.
   * @see #getElement()
   * @generated
   */
  void setElement(Element value);

  /**
   * Ensures that the underlying DOM {@link #getElement element} both exists and is up-to-date with respect to the model,
   * i.e., it serializes the component and maintains an association with that serialization.
   * @see #updateElement(boolean)
   * @see #getElement
   */
  void updateElement();

  /**
   * Ensures that the underlying DOM {@link #getElement element} both exists and is up-to-date with respect to the model,
   * i.e., it serializes the component and maintains an association with that serialization.
   * For <code>deep == true</code>, 
   * or when <code>{@link #getElement getElement()} == null</code>, 
   * this does the same thing as {@link #updateElement()};
   * otherwise, it updates just the DOM element associated with this component to reflect the current state.
   * @see #updateElement()
   * @see #getElement
   */
  void updateElement(boolean deep);

  /**
   * Returns the value of the '<em><b>Container</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the concrete container of this component, 
   * i.e., the inverse of the black diamond relations in the UML model.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Container</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDConcreteComponent_Container()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDConcreteComponent getContainer();

  /**
   * Returns the value of the '<em><b>Root Container</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This walks the {@link #getContainer() containers}
   * until it hits one that has no container.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Root Container</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDConcreteComponent_RootContainer()
   * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDConcreteComponent getRootContainer();

  /**
   * Returns the value of the '<em><b>Schema</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This walks the {@link #getContainer() containers}
   * until it reaches a schema.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Schema</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDConcreteComponent_Schema()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDSchema getSchema();

  /**
   * Returns the value of the '<em><b>Diagnostics</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDDiagnostic}.
   * <!-- begin-user-doc -->
   * <p>
   * These diagnostics are produced by {@link #validate() validation}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Diagnostics</em>' containment reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDConcreteComponent_Diagnostics()
   * @model containment="true" transient="true"
   * @generated
   */
  EList<XSDDiagnostic> getDiagnostics();

  /**
   * Called to indicate that the given element has changed;
   * the element should typically be the same as the one returned {@link #getElement}.
   * It is expected that clients will not call this themselves 
   * since the DOM event listeners attached to the underlying DOM will invoke these automatically.
   */
  void elementChanged(Element changedElement);

  /**
   * Called to indicate that the given element has changed attributes;
   * the element should typically be the same as the one returned {@link #getElement}.
   * It is expected that clients will not call this themselves 
   * since the DOM event listeners attached to the underlying DOM will invoke these automatically.
   */
  void elementAttributesChanged(Element changedElement);

  /**
   * Called to indicate that the given element has changed contents;
   * the element should typically be the same as the one returned {@link #getElement}.
   * It is expected that clients will not call this themselves 
   * since the DOM event listeners attached to the underlying DOM will invoke these automatically.
   */
  void elementContentsChanged(Element changedElement);

  /**
   * Returns the resolved type definition of the given {@link org.eclipse.xsd.XSDNamedComponent#getURI() URI}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveTypeDefinition(java.lang.String, java.lang.String)
   * @see #resolveTypeDefinition(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getURI()
   * @param uri the URI to resolve.
   * @return the resolved type definition.
   */
  XSDTypeDefinition resolveTypeDefinitionURI(String uri);

  /**
   * Returns the resolved type definition of the given  
   * {@link org.eclipse.xsd.XSDNamedComponent#getTargetNamespace() namespace} and {@link org.eclipse.xsd.XSDNamedComponent#getName() name}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveTypeDefinitionURI(java.lang.String)
   * @see #resolveTypeDefinition(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getTargetNamespace()
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param namespace the namespace to resolve.
   * @param localName the name to resolve.
   * @return the resolved type definition.
   */
  XSDTypeDefinition resolveTypeDefinition(String namespace, String localName);

  /**
   * Returns the resolved type definition of the given {@link org.eclipse.xsd.XSDNamedComponent#getName() name} 
   * within the {@link #getSchema() schema}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #getSchema()
   * @see org.eclipse.xsd.XSDSchema#getTargetNamespace()
   * @see #resolveTypeDefinitionURI(java.lang.String)
   * @see #resolveTypeDefinition(java.lang.String, java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param localName the name to resolve.
   * @return the resolved type definition.
   */
  XSDTypeDefinition resolveTypeDefinition(String localName);
  
  /**
   * Returns the resolved simple type definition of the given {@link org.eclipse.xsd.XSDNamedComponent#getURI() URI}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveSimpleTypeDefinition(java.lang.String, java.lang.String)
   * @see #resolveSimpleTypeDefinition(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getURI()
   * @param uri the URI to resolve.
   * @return the resolved simple type definition.
   */
  XSDSimpleTypeDefinition resolveSimpleTypeDefinitionURI(String uri);

  /**
   * Returns the resolved simple type definition of the given  
   * {@link org.eclipse.xsd.XSDNamedComponent#getTargetNamespace() namespace} and {@link org.eclipse.xsd.XSDNamedComponent#getName() name}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveSimpleTypeDefinitionURI(java.lang.String)
   * @see #resolveSimpleTypeDefinition(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getTargetNamespace()
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param namespace the namespace to resolve.
   * @param localName the name to resolve.
   * @return the resolved simple type definition.
   */
  XSDSimpleTypeDefinition resolveSimpleTypeDefinition(String namespace, String localName);

  /**
   * Returns the resolved simple type definition of the given {@link org.eclipse.xsd.XSDNamedComponent#getName() name} 
   * within the {@link #getSchema() schema}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #getSchema()
   * @see org.eclipse.xsd.XSDSchema#getTargetNamespace()
   * @see #resolveSimpleTypeDefinitionURI(java.lang.String)
   * @see #resolveSimpleTypeDefinition(java.lang.String, java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param localName the name to resolve.
   * @return the resolved simple type definition.
   */
  XSDSimpleTypeDefinition resolveSimpleTypeDefinition(String localName);
  
  /**
   * Returns the resolved complex type definition of the given {@link org.eclipse.xsd.XSDNamedComponent#getURI() URI}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveComplexTypeDefinition(java.lang.String, java.lang.String)
   * @see #resolveComplexTypeDefinition(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getURI()
   * @param uri the URI to resolve.
   * @return the resolved complex type definition.
   */
  XSDComplexTypeDefinition resolveComplexTypeDefinitionURI(String uri);

  /**
   * Returns the resolved complex type definition of the given  
   * {@link org.eclipse.xsd.XSDNamedComponent#getTargetNamespace() namespace} and {@link org.eclipse.xsd.XSDNamedComponent#getName() name}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveComplexTypeDefinitionURI(java.lang.String)
   * @see #resolveComplexTypeDefinition(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getTargetNamespace()
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param namespace the namespace to resolve.
   * @param localName the name to resolve.
   * @return the resolved complex type definition.
   */
  XSDComplexTypeDefinition resolveComplexTypeDefinition(String namespace, String localName);

  /**
   * Returns the resolved complex type definition of the given {@link org.eclipse.xsd.XSDNamedComponent#getName() name} 
   * within the {@link #getSchema() schema}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #getSchema()
   * @see org.eclipse.xsd.XSDSchema#getTargetNamespace()
   * @see #resolveComplexTypeDefinitionURI(java.lang.String)
   * @see #resolveComplexTypeDefinition(java.lang.String, java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param localName the name to resolve.
   * @return the resolved complex type definition.
   */
  XSDComplexTypeDefinition resolveComplexTypeDefinition(String localName);

  /**
   * Returns the resolved attribute declaration of the given {@link org.eclipse.xsd.XSDNamedComponent#getURI() URI}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveAttributeDeclaration(java.lang.String, java.lang.String)
   * @see #resolveAttributeDeclaration(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getURI()
   * @param uri the URI to resolve.
   * @return the resolved attribute declaration.
   */
  XSDAttributeDeclaration resolveAttributeDeclarationURI(String uri);

  /**
   * Returns the resolved attribute declaration of the given  
   * {@link org.eclipse.xsd.XSDNamedComponent#getTargetNamespace() namespace} and {@link org.eclipse.xsd.XSDNamedComponent#getName() name}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveAttributeDeclarationURI(java.lang.String)
   * @see #resolveAttributeDeclaration(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getTargetNamespace()
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param namespace the namespace to resolve.
   * @param localName the name to resolve.
   * @return the resolved attribute declaration.
   */
  XSDAttributeDeclaration resolveAttributeDeclaration(String namespace, String localName);

  /**
   * Returns the resolved attribute declaration of the given {@link org.eclipse.xsd.XSDNamedComponent#getName() name} 
   * within the {@link #getSchema() schema}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #getSchema()
   * @see org.eclipse.xsd.XSDSchema#getTargetNamespace()
   * @see #resolveAttributeDeclarationURI(java.lang.String)
   * @see #resolveAttributeDeclaration(java.lang.String, java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param localName the name to resolve.
   * @return the resolved attribute declaration.
   */
  XSDAttributeDeclaration resolveAttributeDeclaration(String localName);

  /**
   * Returns the resolved element declaration of the given {@link org.eclipse.xsd.XSDNamedComponent#getURI() URI}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveElementDeclaration(java.lang.String, java.lang.String)
   * @see #resolveElementDeclaration(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getURI()
   * @param uri the URI to resolve.
   * @return the resolved element declaration.
   */
  XSDElementDeclaration resolveElementDeclarationURI(String uri);

  /**
   * Returns the resolved element declaration of the given  
   * {@link org.eclipse.xsd.XSDNamedComponent#getTargetNamespace() namespace} and {@link org.eclipse.xsd.XSDNamedComponent#getName() name}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveElementDeclarationURI(java.lang.String)
   * @see #resolveElementDeclaration(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getTargetNamespace()
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param namespace the namespace to resolve.
   * @param localName the name to resolve.
   * @return the resolved element declaration.
   */
  XSDElementDeclaration resolveElementDeclaration(String namespace, String localName);

  /**
   * Returns the resolved element declaration of the given {@link org.eclipse.xsd.XSDNamedComponent#getName() name} 
   * within the {@link #getSchema() schema}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #getSchema()
   * @see org.eclipse.xsd.XSDSchema#getTargetNamespace()
   * @see #resolveElementDeclarationURI(java.lang.String)
   * @see #resolveElementDeclaration(java.lang.String, java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param localName the name to resolve.
   * @return the resolved element declaration.
   */
  XSDElementDeclaration resolveElementDeclaration(String localName);
  
  /**
   * Returns the resolved attribute group definition of the given {@link org.eclipse.xsd.XSDNamedComponent#getURI() URI}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveAttributeGroupDefinition(java.lang.String, java.lang.String)
   * @see #resolveAttributeGroupDefinition(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getURI()
   * @param uri the URI to resolve.
   * @return the resolved attribute group definition.
   */
  XSDAttributeGroupDefinition resolveAttributeGroupDefinitionURI(String uri);

  /**
   * Returns the resolved attribute group definition the given  
   * {@link org.eclipse.xsd.XSDNamedComponent#getTargetNamespace() namespace} and {@link org.eclipse.xsd.XSDNamedComponent#getName() name}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveAttributeGroupDefinitionURI(java.lang.String)
   * @see #resolveAttributeGroupDefinition(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getTargetNamespace()
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param namespace the namespace to resolve.
   * @param localName the name to resolve.
   * @return the resolved attribute group definition
   */
  XSDAttributeGroupDefinition resolveAttributeGroupDefinition(String namespace, String localName);

  /**
   * Returns the resolved attribute group definition of the given {@link org.eclipse.xsd.XSDNamedComponent#getName() name} 
   * within the {@link #getSchema() schema}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #getSchema()
   * @see org.eclipse.xsd.XSDSchema#getTargetNamespace()
   * @see #resolveAttributeGroupDefinitionURI(java.lang.String)
   * @see #resolveAttributeGroupDefinition(java.lang.String, java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param localName the name to resolve.
   * @return the resolved attribute group definition.
   */
  XSDAttributeGroupDefinition resolveAttributeGroupDefinition(String localName);
  
  /**
   * Returns the resolved model group definition of the given {@link org.eclipse.xsd.XSDNamedComponent#getURI() URI}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveModelGroupDefinition(java.lang.String, java.lang.String)
   * @see #resolveModelGroupDefinition(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getURI()
   * @param uri the URI to resolve.
   * @return the resolved model group definition.
   */
  XSDModelGroupDefinition resolveModelGroupDefinitionURI(String uri);

  /**
   * Returns the resolved model group definition of the given  
   * {@link org.eclipse.xsd.XSDNamedComponent#getTargetNamespace() namespace} and {@link org.eclipse.xsd.XSDNamedComponent#getName() name}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveModelGroupDefinitionURI(java.lang.String)
   * @see #resolveModelGroupDefinition(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getTargetNamespace()
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param namespace the namespace to resolve.
   * @param localName the name to resolve.
   * @return the resolved model group definition.
   */
  XSDModelGroupDefinition resolveModelGroupDefinition(String namespace, String localName);

  /**
   * Returns the resolved model group definition of the given {@link org.eclipse.xsd.XSDNamedComponent#getName() name} 
   * within the {@link #getSchema() schema}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #getSchema()
   * @see org.eclipse.xsd.XSDSchema#getTargetNamespace()
   * @see #resolveModelGroupDefinitionURI(java.lang.String)
   * @see #resolveModelGroupDefinition(java.lang.String, java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param localName the name to resolve.
   * @return the resolved model group definition.
   */
  XSDModelGroupDefinition resolveModelGroupDefinition(String localName);

  /**
   * Returns the resolved identity constraint definition of the given {@link org.eclipse.xsd.XSDNamedComponent#getURI() URI}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveIdentityConstraintDefinition(java.lang.String, java.lang.String)
   * @see #resolveIdentityConstraintDefinition(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getURI()
   * @param uri the URI to resolve.
   * @return the resolved identity constraint definition.
   */
  XSDIdentityConstraintDefinition resolveIdentityConstraintDefinitionURI(String uri);

  /**
   * Returns the resolved identity constraint definition of the given  
   * {@link org.eclipse.xsd.XSDNamedComponent#getTargetNamespace() namespace} and {@link org.eclipse.xsd.XSDNamedComponent#getName() name}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveIdentityConstraintDefinitionURI(java.lang.String)
   * @see #resolveIdentityConstraintDefinition(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getTargetNamespace()
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param namespace the namespace to resolve.
   * @param localName the name to resolve.
   * @return the resolved identity constraint definition.
   */
  XSDIdentityConstraintDefinition resolveIdentityConstraintDefinition(String namespace, String localName);

  /**
   * Returns the resolved identity constraint definition of the given {@link org.eclipse.xsd.XSDNamedComponent#getName() name} 
   * within the {@link #getSchema() schema}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #getSchema()
   * @see org.eclipse.xsd.XSDSchema#getTargetNamespace()
   * @see #resolveIdentityConstraintDefinitionURI(java.lang.String)
   * @see #resolveIdentityConstraintDefinition(java.lang.String, java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param localName the name to resolve.
   * @return the resolved identity constraint definition.
   */
  XSDIdentityConstraintDefinition resolveIdentityConstraintDefinition(String localName);

  /**
   * Returns the resolved notation declaration of the given {@link org.eclipse.xsd.XSDNamedComponent#getURI() URI}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveNotationDeclaration(java.lang.String, java.lang.String)
   * @see #resolveNotationDeclaration(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getURI()
   * @param uri the URI to resolve.
   * @return the resolved notation declaration.
   */
  XSDNotationDeclaration resolveNotationDeclarationURI(String uri);

  /**
   * Returns the resolved notation declaration of the given  
   * {@link org.eclipse.xsd.XSDNamedComponent#getTargetNamespace() namespace} and {@link org.eclipse.xsd.XSDNamedComponent#getName() name}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #resolveNotationDeclarationURI(java.lang.String)
   * @see #resolveNotationDeclaration(java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getTargetNamespace()
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param namespace the namespace to resolve.
   * @param localName the name to resolve.
   * @return the resolved notation declaration.
   */
  XSDNotationDeclaration resolveNotationDeclaration(String namespace, String localName);

  /**
   * Returns the resolved notation declaration the given {@link org.eclipse.xsd.XSDNamedComponent#getName() name} 
   * within the {@link #getSchema() schema}.
   * An unresolved instance is synthesized to hold the namespace and name if an existing instance cannot be found.
   * @see #getSchema()
   * @see org.eclipse.xsd.XSDSchema#getTargetNamespace()
   * @see #resolveNotationDeclarationURI(java.lang.String)
   * @see #resolveNotationDeclaration(java.lang.String, java.lang.String)
   * @see org.eclipse.xsd.XSDNamedComponent#getName()
   * @param localName the name to resolve.
   * @return the resolved notation declaration
   */
  XSDNotationDeclaration resolveNotationDeclaration(String localName);

  /**
   * Returns the collection of components that have an {@link org.eclipse.xsd.XSDAnnotation annotation} 
   * containing an {@link org.eclipse.xsd.XSDAnnotation#getApplicationInformation() appinfo} with given source URI.
   * @see org.eclipse.xsd.XSDAnnotation#getApplicationInformationSources()
   * @param sourceURI the source URI to match.
   * @return the collection of matching components.
   */
  Collection<XSDConcreteComponent> getComponentsWithApplicationInformation(String sourceURI);

  /**
   * Returns the collection of components that have an {@link org.eclipse.xsd.XSDAnnotation annotation} 
   * containing a {@link org.eclipse.xsd.XSDAnnotation#getUserInformation() documentation} with given source URI.
   * @see org.eclipse.xsd.XSDAnnotation#getApplicationInformationSources()
   * @param sourceURI the source URI to match.
   * @return the collection of matching components.
   */
  Collection<XSDConcreteComponent> getComponentsWithUserInformation(String sourceURI);

  /**
   * Validates whether this component conforms to the constraints defined in the XML Schema standard.
   * {@link #getDiagnostics() Diagnostics} are produced to report any problems.
   * The effect of calling this for a component not directly or indirectly contained by a {@link org.eclipse.xsd.XSDSchema schema}
   * is undefined, and is likely to result in null pointer exceptions.
   * @see #getDiagnostics()
   * @see #clearDiagnostics()
   */
  void validate();

  /**
   * Clears any {@link #getDiagnostics() diagnostics} associated with the component and all those it contains.
   * @see #validate()
   * @see #getDiagnostics()
   */
  void clearDiagnostics();

  /**
   * Creates a clone of the component.
   * The clone may be deep and may share the DOM of this component.
   * @param deep whether the clone should be deep.
   * @param shareDOM whether the clone should share the original DOM
   * @return a clone of the component.
   */
  XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM);

  /**
   * Returns whether the given component is contained by this component.
   * @param xsdConcreteComponent a component.
   * @return whether the component is contained by this component.
   */
  boolean contains(XSDConcreteComponent xsdConcreteComponent);
}
