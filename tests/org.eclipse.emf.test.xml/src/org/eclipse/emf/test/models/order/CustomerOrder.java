/**
 * <copyright>
 * </copyright>
 *
 * $Id: CustomerOrder.java,v 1.1.2.1 2005/01/14 22:56:17 nickb Exp $
 */
package org.eclipse.emf.test.models.order;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Customer Order</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.order.CustomerOrder#getMoviesToSee <em>Movies To See</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.order.CustomerOrder#getMoviesSeen <em>Movies Seen</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.order.CustomerOrder#getAny <em>Any</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.order.CustomerOrder#getCustomerID <em>Customer ID</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.order.OrderPackage#getCustomerOrder()
 * @model 
 * @generated
 */
public interface CustomerOrder extends EObject
{
  /**
   * Returns the value of the '<em><b>Movies To See</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Movies To See</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Movies To See</em>' containment reference.
   * @see #setMoviesToSee(Order)
   * @see org.eclipse.emf.test.models.order.OrderPackage#getCustomerOrder_MoviesToSee()
   * @model containment="true" resolveProxies="false" required="true"
   * @generated
   */
  Order getMoviesToSee();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.order.CustomerOrder#getMoviesToSee <em>Movies To See</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Movies To See</em>' containment reference.
   * @see #getMoviesToSee()
   * @generated
   */
  void setMoviesToSee(Order value);

  /**
   * Returns the value of the '<em><b>Movies Seen</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Movies Seen</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Movies Seen</em>' containment reference.
   * @see #setMoviesSeen(Order)
   * @see org.eclipse.emf.test.models.order.OrderPackage#getCustomerOrder_MoviesSeen()
   * @model containment="true" resolveProxies="false"
   * @generated
   */
  Order getMoviesSeen();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.order.CustomerOrder#getMoviesSeen <em>Movies Seen</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Movies Seen</em>' containment reference.
   * @see #getMoviesSeen()
   * @generated
   */
  void setMoviesSeen(Order value);

  /**
   * Returns the value of the '<em><b>Any</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Any</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Any</em>' attribute list.
   * @see org.eclipse.emf.test.models.order.OrderPackage#getCustomerOrder_Any()
   * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" required="true" many="true"
   * @generated
   */
  FeatureMap getAny();

  /**
   * Returns the value of the '<em><b>Customer ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Customer ID</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Customer ID</em>' attribute.
   * @see #setCustomerID(String)
   * @see org.eclipse.emf.test.models.order.OrderPackage#getCustomerOrder_CustomerID()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.IDREF"
   * @generated
   */
  String getCustomerID();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.order.CustomerOrder#getCustomerID <em>Customer ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Customer ID</em>' attribute.
   * @see #getCustomerID()
   * @generated
   */
  void setCustomerID(String value);

} // CustomerOrder
