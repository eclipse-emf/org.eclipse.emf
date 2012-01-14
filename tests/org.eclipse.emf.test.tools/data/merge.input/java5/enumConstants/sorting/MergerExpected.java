/**
 */
package org.eclipse.example.library;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Book Category</b></em>',
 * and utility methods for working with them.
 * @implements EnumeratorTarget 
 * <!-- end-user-doc -->
 * @see org.eclipse.example.library.LibraryPackage#getBookCategory()
 * @model
 * @generated
 */
public enum BookCategory implements Enumerator, Enumerator1, EnumeratorTarget
{
  /**
   * The '<em><b>Short Stories</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SHORT_STORIES
   * @generated
   * @ordered
   */
  SHORT_STORIES_LITERAL(3, "ShortStories", "ShortStories") //$NON-NLS-1$ //$NON-NLS-2$
  , // line comment after ScienceFiction after comma

  // line comment between ScienceFiction and Biography
  
  // line comment right before Biography
  /**
   * The '<em><b>Biography</b></em>' literal object.
   * <!-- begin-user-doc -->
   * target comment Biography
   * <!-- end-user-doc -->
   * @see #BIOGRAPHY
   * @generated
   * @ordered
   */
  BIOGRAPHY_LITERAL(2, "Biography", "Biography") //$NON-NLS-1$ //$NON-NLS-2$ // Biography
  // line comment after Biography before semicolon
  
  // hanging comment after Biography before semicolon  
  , /**
   * The '<em><b>Long Stories</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #LONG_STORIES
   * @generated
   * @ordered
   */
  LONG_STORIES_LITERAL(4, "LongStories", "LongStories") //$NON-NLS-1$ //$NON-NLS-2$  
  , // line comment after MysteryToBeRemoved after comma

  // line comment between MysteryToBeRemoved and ScienceFiction
  
  // line comment right before ScienceFiction
  /**
   * The '<em><b>Science Fiction</b></em>' literal object.
   * target comment
   * <!-- begin-user-doc -->
   * target comment ScienceFiction
   * <!-- end-user-doc -->
   * @see #SCIENCE_FICTION
   * @generated NOT
   * @ordered
   */
  SCIENCE_FICTION_LITERAL(1, "Target ScienceFiction", "ScienceFiction") //$NON-NLS-1$ //$NON-NLS-2$ //ScienceFiction 
  // line comment after ScienceFiction before comma
  
  // hanging comment after ScienceFiction before comma  
  , // line comment after Mystery after comma

  // line comment between Mystery and Mystery2
  
  // line comment right before Mystery2
  /**
   * The '<em><b>Mystery2</b></em>' literal object.
   * target comment
   * <!-- begin-user-doc -->
   * target comment Mystery2
   * <!-- end-user-doc -->
   * @see #MYSTERY
   * @generated NOT
   * @ordered
   */
  MYSTERY2_LITERAL(0, "Target Mystery2", "Mystery") //$NON-NLS-1$ //$NON-NLS-2$ // Mystery2 
  // line comment after Mystery2 before comma
  
  // hanging comment after Mystery2 before comma  
  , // line comment hanging before Mystery
  
  // line comment right before Mystery 
  /**
   * The '<em><b>Mystery</b></em>' literal object.
   * target comment
   * <!-- begin-user-doc -->
   * target comment Mystery
   * <!-- end-user-doc -->
   * @see #MYSTERY
   * @generated NOT
   * @ordered
   */
  MYSTERY_LITERAL(0, "Target Mystery", "Mystery") //$NON-NLS-1$ //$NON-NLS-2$  // Mystery
  // line comment after Mystery before comma
  
  // hanging comment after Mystery before comma
  ; // line comment at the end of enum consts
  
  /*
   * Bugzilla 165525: Adding new field after last field in enum should keep
   * the line comment of existing field with the existing field 
   */  
  String s1 = "target"; //$NON-NLS-1$
  private String s2 = "source"; //$NON-NLS-1$    
}
