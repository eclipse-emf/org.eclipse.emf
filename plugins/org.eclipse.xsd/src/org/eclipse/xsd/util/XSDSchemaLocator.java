/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
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
 * $Id: XSDSchemaLocator.java,v 1.3 2008/05/29 14:56:36 marcelop Exp $
 */
package org.eclipse.xsd.util;


import org.eclipse.xsd.XSDSchema;


/**
 * An adapter interface used by {@link org.eclipse.xsd.XSDSchemaDirective} to find referenced schemas.
 * When the schema referenced by a schema directive needs to be determined, 
 * the containing {@link org.eclipse.emf.ecore.resource.Resource}
 * will be {@link org.eclipse.emf.ecore.util.EcoreUtil#getRegisteredAdapter(org.eclipse.emf.ecore.resource.Resource,Object) inspected} 
 * for an adapter that implements this interface.
 * As such, you can register an adapter factory like this to tailor the algorithm used to locate a schema:
 *<pre>
 *  ResourceSet resourceSet = new ResourceSetImpl();
 *  resourceSet.getAdapterFactories().add
 *    (new AdapterFactoryImpl()
 *     {
 *       class SchemaLocator extends AdapterImpl implements XSDSchemaLocator
 *       {
 *         public XSDSchema locateSchema(XSDSchema xsdSchema, String namespaceURI,  String rawSchemaLocationURI, String resolvedSchemaLocation)
 *         {
 *           return null;  // Additional logic...
 *         }
 *
 *         public boolean isAdatperForType(Object type)
 *         {
 *           return type == XSDSchemaLocator.class;
 *         }
 *       }
 *
 *       protected SchemaLocator schemaLocator = new SchemaLocator();
 *
 *       public boolean isFactoryForType(Object type)
 *       {
 *         return type == XSDSchemaLocator.class;
 *       }
 *
 *       public Adapter adaptNew(Notifier target, Object type)
 *       {
 *         return schemaLocator;
 *       }
 *     });
 *</pre>
 * @see org.eclipse.emf.ecore.util.EcoreUtil#getRegisteredAdapter(org.eclipse.emf.ecore.resource.Resource,Object)
 */
public interface XSDSchemaLocator
{
  /**
   * Locate the schema for the given namespace.
   * @param xsdSchema the schema containing the namespace reference.
   * @param namespaceURI the namespace being resolved, i.e., {@link org.eclipse.xsd.XSDImport#getNamespace}.
   * @param rawSchemaLocationURI the suggested location of the namespace being resolved, i.e., {@link org.eclipse.xsd.XSDSchemaDirective#getSchemaLocation}.
   * @param resolvedSchemaLocationURI the {@link XSDSchemaLocationResolver#resolveSchemaLocation resolved} suggested location of the namespace.
   * @return the resolved schema.
   * @see org.eclipse.xsd.util.XSDSchemaLocationResolver#resolveSchemaLocation(XSDSchema, String, String)
   * @see org.eclipse.xsd.util.XSDConstants#resolveSchemaLocation(String,String,String)
   */
  XSDSchema locateSchema(XSDSchema xsdSchema, String namespaceURI,  String rawSchemaLocationURI, String resolvedSchemaLocationURI);
}
