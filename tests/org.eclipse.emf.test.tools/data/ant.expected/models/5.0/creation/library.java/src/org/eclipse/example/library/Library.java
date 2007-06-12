package org.eclipse.example.library;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
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
   * @model containment="true"
   */
  EList<Writer> getWriters();

  /**
   * @model containment="true"
   */
  EList<Book> getBooks();
  
  /**
   * @model
   */
  EList<Book> getSpecialBooks();

  /**
   * @model
   */
  Map<String, Book> getBookByTitleMap();
  
  /**
   * Sets the value of the '{@link org.eclipse.example.library.Library#getBookByTitleMap <em>Book By Title Map</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Book By Title Map</em>' attribute.
   * @see #getBookByTitleMap()
   * @generated
   */
  void setBookByTitleMap(Map<String, Book> value);

  /**
   * @model mapType="WriterNameMap"
   */
  EMap<String, Writer> getWriterByNameMap();
    
  /**
   * @model mapType="MapOfDataTypes<WriterNumber, WriterID>"
   */  
  EMap<Integer, String> getWriterByIDMap();
  
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
   * @model
   */
  Map<EObject, List<URI>> getMap1();
  
  /**
   * Sets the value of the '{@link org.eclipse.example.library.Library#getMap1 <em>Map1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Map1</em>' attribute.
   * @see #getMap1()
   * @generated
   */
  void setMap1(Map<EObject, List<URI>> value);

  /**
   * @model dataType="ManyURIs"
   */
  List<URI> getURIs_1();

  /**
   * Sets the value of the '{@link org.eclipse.example.library.Library#getURIs_1 <em>UR Is 1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>UR Is 1</em>' attribute.
   * @see #getURIs_1()
   * @generated
   */
  void setURIs_1(List<URI> value);  
}