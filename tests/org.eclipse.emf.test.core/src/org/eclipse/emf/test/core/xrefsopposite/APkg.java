/**
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   Thales - initial API and implementation
 */
package org.eclipse.emf.test.core.xrefsopposite;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>APkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.APkg#getOwnedAs <em>Owned As</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage#getAPkg()
 * @model
 * @generated
 */
public interface APkg extends AbstractA
{
  /**
   * Returns the value of the '<em><b>Owned As</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.core.xrefsopposite.A}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.core.xrefsopposite.A#getOwningAPkg <em>Owning APkg</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Owned As</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Owned As</em>' containment reference list.
   * @see org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage#getAPkg_OwnedAs()
   * @see org.eclipse.emf.test.core.xrefsopposite.A#getOwningAPkg
   * @model opposite="owningAPkg" containment="true"
   * @generated
   */
  EList<A> getOwnedAs();

} // APkg
