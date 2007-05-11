package org.eclipse.example.library;

import java.util.List;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * @model
 */
public interface Library extends EObject
{
  /**
   * @model
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.example.library.Library#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * @model type="Writer" containment="true"
   */
  EList getWriters();

  /**
   * @model type="Book" containment="true"
   */
  EList getBooks();
  
  /**
   * @model type="Book"
   */
  EList getSpecialBooks();
  
  /**
   * @model keyType="String" valueType="Book"
   */
  EMap getBookByTitleMap(); 

  /**
   * @model keyType="String" valueType="Writer"
   */
  EMap getWriterByNameMap();
  
  /**
   * @model
   */
  Map getOptions();
  
  /**
   * Sets the value of the '{@link org.eclipse.example.library.Library#getOptions <em>Options</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Options</em>' attribute.
   * @see #getOptions()
   * @generated
   */
  void setOptions(Map value);

  /**
   * @model dataType="MyMapOfIntegersAndStrings"
   */  
  Map getWriterByIDMap();

  /**
   * Sets the value of the '{@link org.eclipse.example.library.Library#getWriterByIDMap <em>Writer By ID Map</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Writer By ID Map</em>' attribute.
   * @see #getWriterByIDMap()
   * @generated
   */
  void setWriterByIDMap(Map value);  
}