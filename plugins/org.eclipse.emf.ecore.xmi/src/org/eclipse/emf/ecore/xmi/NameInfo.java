/**
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xmi;


/**
 * This interface represents a qualified name, as specified in "Namespaces in XML" specification.
 * @since 2.1.0
 */
public interface NameInfo
{
  /**
   * @return the local part of qualified name
   */
  public String getLocalPart();

  /**
   * @param localPart the local part of qualified name
   */
  public void setLocalPart(String localPart);

  /**
   * @return the qualified name
   */
  public String getQualifiedName();

  /**
   * @param name the qualified name
   */
  public void setQualifiedName(String name);

  /**
   * @return the namespace URI
   */
  public String getNamespaceURI();

  /**
   * @param uri the namespace URI
   */
  public void setNamespaceURI(String uri);
}
