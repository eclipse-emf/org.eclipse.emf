/**
 * <copyright>
 * </copyright>
 *
 * $Id: PersonnelType.java,v 1.1 2007/01/18 15:50:25 marcelop Exp $
 */
package org.eclipse.emf.test.models.personal;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Personnel Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.PersonnelType#getPerson <em>Person</em>}</li>
 * </ul>
 * </p>
 *
 * @model extendedMetaData="name='personnel_._type' kind='elementOnly'"
 * @generated
 */
public interface PersonnelType
{
  /**
   * Returns the value of the '<em><b>Person</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.personal.PersonType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Person</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Person</em>' containment reference list.
   * @model type="org.eclipse.emf.test.models.personal.PersonType" containment="true" required="true"
   *        extendedMetaData="kind='element' name='person' namespace='##targetNamespace'"
   * @generated
   */
  List<PersonType> getPerson();

} // PersonnelType
