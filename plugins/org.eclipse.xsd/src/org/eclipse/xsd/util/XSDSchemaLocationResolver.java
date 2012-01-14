/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd.util;


import org.eclipse.xsd.XSDSchema;


/**
 * An adapter interface used to resolve {@link org.eclipse.xsd.XSDSchemaDirective#getSchemaLocation schema locations}.
 * When the schema location of an schema directive needs to be resolved, 
 * each containing {@link org.eclipse.xsd.XSDSchema schema} 
 * will be {@link org.eclipse.emf.ecore.util.EcoreUtil#getRegisteredAdapter(org.eclipse.emf.ecore.EObject,Object) inspected} 
 * for an adapter that implements this interface.
 * As such, you can register an adapter factory like this to tailor the algorithm used to resolve a schema location:
 *<pre>
 *  ResourceSet resourceSet = new ResourceSetImpl();
 *  resourceSet.getAdapterFactories().add
 *    (new AdapterFactoryImpl()
 *     {
 *       class Resolver extends AdapterImpl implements XSDSchemaLocationResolver
 *       {
 *         public String resolveSchemaLocation(XSDSchema xsdSchema, String namespaceURI,  String schemaLocationURI)
 *         {
 *           return XSDConstants.resolveSchemaLocation(xsdSchema.getSchemaLocation(), namespaceURI, schemaLocationURI);
 *         }
 *
 *         public boolean isAdapterForType(Object type)
 *         {
 *           return type == XSDSchemaLocationResolver.class;
 *         }
 *       }
 *
 *       protected Resolver resolver = new Resolver();
 *
 *       public boolean isFactoryForType(Object type)
 *       {
 *         return type == XSDSchemaLocationResolver.class;
 *       }
 *
 *       public Adapter adaptNew(Notifier target, Object type)
 *       {
 *         return resolver;
 *       }
 *     });
 *</pre>
 * @see org.eclipse.emf.ecore.util.EcoreUtil#getRegisteredAdapter(org.eclipse.emf.ecore.EObject,Object)
 */
public interface XSDSchemaLocationResolver
{
  /**
   * Resolve the namespace and schema location relative to the given base schema's location.
   * For example, the expression
   *<pre>
   *  resolveSchemaLocation
   *    (xsdSchema, // xsdSchema.getSchemaLocation() == "http://www.example.com/A/a.xsd" 
   *     "http://www.example.com/B", 
   *     "../B/b.xsd");
   *</pre>
   * would normally yield
   *<pre>
   *  "http://www.example.com/B/b.xsd"
   *</pre>
   * When no namespace schema location is provided, the namespace itself will be used.
   * @param xsdSchema the schema containing the reference
   * @param namespaceURI the namespace being resolved, i.e., {@link org.eclipse.xsd.XSDImport#getNamespace}.
   * @param schemaLocationURI the suggested location of the namespace being resolved, i.e., {@link org.eclipse.xsd.XSDSchemaDirective#getSchemaLocation}.
   * @return the resolved schema location.
   * @see org.eclipse.xsd.util.XSDConstants#resolveSchemaLocation(String,String,String)
   */
  String resolveSchemaLocation(XSDSchema xsdSchema, String namespaceURI,  String schemaLocationURI);
}
