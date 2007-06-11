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
  EList<Writer> getWriters();

  /**
   * @model containment="true"
   */
  EList<Book> getBooks();
  
  /**
   * @model keyType="String" valueType="Book"
   */
  EMap<String, Book> getBookByTitleMap();
  
  /**
   * @model
   */
  EMap<String, Writer> getWriterByNameMap();
  
  /**
   * @model kind="attribute"
   */
  Map<String, String> getOptions();
  
  /**
   * Sets the value of the '{@link org.eclipse.example.library.Library#getOptions <em>Options</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Options</em>' attribute.
   * @see #getOptions()
   * @generated
   */
  void setOptions(Map<String, String> value);

  /**
   * @model dataType="MyMapOfIntegersAndStrings"
   */  
  Map<Integer, String> getWriterByIDMap();

  /**
   * Sets the value of the '{@link org.eclipse.example.library.Library#getWriterByIDMap <em>Writer By ID Map</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Writer By ID Map</em>' attribute.
   * @see #getWriterByIDMap()
   * @generated
   */
  void setWriterByIDMap(Map<Integer, String> value);
}