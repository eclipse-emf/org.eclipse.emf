/**
 * <copyright>
 * </copyright>
 *
 * $Id: NameType.java,v 1.1 2005/06/01 22:28:12 elena Exp $
 */
package org.eclipse.emf.test.models.personal;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Name Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.NameType#getFamily <em>Family</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.NameType#getGiven <em>Given</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.personal.PersonalPackage#getNameType()
 * @model extendedMetaData="name='nameType' kind='elementOnly'"
 * @generated
 */
public interface NameType
{
  /**
   * Returns the value of the '<em><b>Family</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Family</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Family</em>' attribute.
   * @see #setFamily(String)
   * @see org.eclipse.emf.test.models.personal.PersonalPackage#getNameType_Family()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='family' namespace='##targetNamespace'"
   * @generated
   */
  String getFamily();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.NameType#getFamily <em>Family</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Family</em>' attribute.
   * @see #getFamily()
   * @generated
   */
  void setFamily(String value);

  /**
   * Returns the value of the '<em><b>Given</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Given</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Given</em>' attribute.
   * @see #setGiven(String)
   * @see org.eclipse.emf.test.models.personal.PersonalPackage#getNameType_Given()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='given' namespace='##targetNamespace'"
   * @generated
   */
  String getGiven();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.NameType#getGiven <em>Given</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Given</em>' attribute.
   * @see #getGiven()
   * @generated
   */
  void setGiven(String value);

} // NameType
