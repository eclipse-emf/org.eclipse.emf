/**
 * <copyright>
 * 
 * Copyright (c) 2004-2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: Ecore2EcoreMappingRoot.java,v 1.3 2005/05/06 15:03:21 khussey Exp $
 */
package org.eclipse.emf.mapping.ecore2ecore;


import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.mapping.MappingRoot;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mapping Root</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.emf.mapping.ecore2ecore.Ecore2EcorePackage#getEcore2EcoreMappingRoot()
 * @model
 * @generated
 */
public interface Ecore2EcoreMappingRoot extends MappingRoot
{
  EPackage getInputEPackage();

  EPackage getOutputEPackage();
  
} // Ecore2EcoreMappingRoot
