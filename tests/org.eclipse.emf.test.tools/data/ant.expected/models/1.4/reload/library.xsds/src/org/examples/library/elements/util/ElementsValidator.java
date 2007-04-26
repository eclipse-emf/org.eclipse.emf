/**
 * This is my code.
 *
 * $Id: ElementsValidator.java,v 1.2 2007/04/26 20:57:15 emerks Exp $
 */
package org.examples.library.elements.util;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;

import org.examples.library.elements.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.examples.library.elements.ElementsPackage
 * @generated
 */
public class ElementsValidator extends EObjectValidator
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final ElementsValidator INSTANCE = new ElementsValidator();

  /**
   * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.common.util.Diagnostic#getSource()
   * @see org.eclipse.emf.common.util.Diagnostic#getCode()
   * @generated
   */
  public static final String DIAGNOSTIC_SOURCE = "org.examples.library.elements"; //$NON-NLS-1$

  /**
   * A constant with a fixed name that can be used as the base value for additional hand written constants.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

  /**
   * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

  /**
   * The cached base package validator.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XMLTypeValidator xmlTypeValidator;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ElementsValidator()
  {
    super();
    xmlTypeValidator = XMLTypeValidator.INSTANCE;
  }

  /**
   * Returns the package of this validator switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EPackage getEPackage()
  {
    return ElementsPackage.eINSTANCE;
  }

  /**
   * Calls <code>validateXXX</code> for the corresonding classifier of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map context)
  {
    switch (classifierID)
    {
      case ElementsPackage.BOOK:
        return validateBook((Book)value, diagnostics, context);
      case ElementsPackage.WRITER:
        return validateWriter((Writer)value, diagnostics, context);
      case ElementsPackage.BOOK_CATEGORY:
        return validateBookCategory((BookCategory)value, diagnostics, context);
      case ElementsPackage.BOOK_CATEGORY_OBJECT:
        return validateBookCategoryObject((BookCategory)value, diagnostics, context);
      case ElementsPackage.UUID:
        return validateUUID((byte[])value, diagnostics, context);
      default: 
        return true;
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateBook(Book book, DiagnosticChain diagnostics, Map context)
  {
    return validate_EveryDefaultConstraint(book, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateWriter(Writer writer, DiagnosticChain diagnostics, Map context)
  {
    return validate_EveryDefaultConstraint(writer, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateBookCategory(BookCategory bookCategory, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateBookCategoryObject(BookCategory bookCategoryObject, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUUID(byte[] uuid, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateUUID_MinLength(uuid, diagnostics, context);
    if (result || diagnostics != null) result &= validateUUID_MaxLength(uuid, diagnostics, context);
    return result;
  }

  /**
   * Validates the MinLength constraint of '<em>UUID</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUUID_MinLength(byte[] uuid, DiagnosticChain diagnostics, Map context)
  {
    int length = uuid.length;  
    boolean result = length >= 16;
    if (!result && diagnostics != null) 
      reportMinLengthViolation(ElementsPackage.Literals.UUID, uuid, length, 16, diagnostics, context);
    return result;
  }

  /**
   * Validates the MaxLength constraint of '<em>UUID</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUUID_MaxLength(byte[] uuid, DiagnosticChain diagnostics, Map context)
  {
    int length = uuid.length;  
    boolean result = length <= 16;
    if (!result && diagnostics != null) 
      reportMaxLengthViolation(ElementsPackage.Literals.UUID, uuid, length, 16, diagnostics, context);
    return result;
  }

} //ElementsValidator
