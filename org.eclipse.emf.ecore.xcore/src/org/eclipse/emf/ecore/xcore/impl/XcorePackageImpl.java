/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.ecore.xcore.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xcore.XAnnotation;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XEnum;
import org.eclipse.emf.ecore.xcore.XEnumLiteral;
import org.eclipse.emf.ecore.xcore.XGenericType;
import org.eclipse.emf.ecore.xcore.XImportDirective;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XModelElement;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XParameter;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.XTypeParameter;
import org.eclipse.emf.ecore.xcore.XTypedElement;
import org.eclipse.emf.ecore.xcore.XcoreFactory;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.xtext.xbase.XbasePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XcorePackageImpl extends EPackageImpl implements XcorePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xAnnotationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xAnnotationDirectiveEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xClassEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xClassifierEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xDataTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xEnumEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xEnumLiteralEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xModelElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xNamedElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xOperationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xPackageEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xParameterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xStructuralFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xTypedElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xStringToStringMapEntryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xGenericTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xImportDirectiveEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xMemberEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xTypeParameterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType xMultiplicityEDataType = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private XcorePackageImpl()
  {
    super(eNS_URI, XcoreFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link XcorePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static XcorePackage init()
  {
    if (isInited) return (XcorePackage)EPackage.Registry.INSTANCE.getEPackage(XcorePackage.eNS_URI);

    // Obtain or create and register package
    XcorePackageImpl theXcorePackage = (XcorePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof XcorePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new XcorePackageImpl());

    isInited = true;

    // Initialize simple dependencies
    EcorePackage.eINSTANCE.eClass();
    XbasePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theXcorePackage.createPackageContents();

    // Initialize created meta-data
    theXcorePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theXcorePackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(XcorePackage.eNS_URI, theXcorePackage);
    return theXcorePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXAnnotation()
  {
    return xAnnotationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXAnnotation_Source()
  {
    return (EReference)xAnnotationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXAnnotation_Details()
  {
    return (EReference)xAnnotationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXAnnotation_ModelElement()
  {
    return (EReference)xAnnotationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXAnnotationDirective()
  {
    return xAnnotationDirectiveEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXAnnotationDirective_SourceURI()
  {
    return (EAttribute)xAnnotationDirectiveEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXClass()
  {
    return xClassEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXClass_Abstract()
  {
    return (EAttribute)xClassEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXClass_Interface()
  {
    return (EAttribute)xClassEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXClass_Members()
  {
    return (EReference)xClassEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXClass_SuperTypes()
  {
    return (EReference)xClassEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXClassifier()
  {
    return xClassifierEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXClassifier_InstanceTypeName()
  {
    return (EAttribute)xClassifierEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXClassifier_Package()
  {
    return (EReference)xClassifierEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXClassifier_TypeParameters()
  {
    return (EReference)xClassifierEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXDataType()
  {
    return xDataTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXDataType_Serializable()
  {
    return (EAttribute)xDataTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXDataType_CreateBody()
  {
    return (EReference)xDataTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXDataType_ConvertBody()
  {
    return (EReference)xDataTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXEnum()
  {
    return xEnumEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXEnum_Literals()
  {
    return (EReference)xEnumEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXEnumLiteral()
  {
    return xEnumLiteralEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXEnumLiteral_Value()
  {
    return (EAttribute)xEnumLiteralEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXEnumLiteral_Literal()
  {
    return (EAttribute)xEnumLiteralEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXEnumLiteral_Enum()
  {
    return (EReference)xEnumLiteralEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXModelElement()
  {
    return xModelElementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXModelElement_Annotations()
  {
    return (EReference)xModelElementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXNamedElement()
  {
    return xNamedElementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXNamedElement_Name()
  {
    return (EAttribute)xNamedElementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXOperation()
  {
    return xOperationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXOperation_TypeParameters()
  {
    return (EReference)xOperationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXOperation_Parameters()
  {
    return (EReference)xOperationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXOperation_Exceptions()
  {
    return (EReference)xOperationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXOperation_Body()
  {
    return (EReference)xOperationEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXPackage()
  {
    return xPackageEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXPackage_NsURI()
  {
    return (EAttribute)xPackageEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXPackage_NsPrefix()
  {
    return (EAttribute)xPackageEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXPackage_ImportDirectives()
  {
    return (EReference)xPackageEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXPackage_AnnotationDirectives()
  {
    return (EReference)xPackageEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXPackage_Classifiers()
  {
    return (EReference)xPackageEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXParameter()
  {
    return xParameterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXParameter_Operation()
  {
    return (EReference)xParameterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXStructuralFeature()
  {
    return xStructuralFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXStructuralFeature_Readonly()
  {
    return (EAttribute)xStructuralFeatureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXStructuralFeature_Volatile()
  {
    return (EAttribute)xStructuralFeatureEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXStructuralFeature_Transient()
  {
    return (EAttribute)xStructuralFeatureEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXStructuralFeature_DefaultValueLiteral()
  {
    return (EAttribute)xStructuralFeatureEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXStructuralFeature_Unsettable()
  {
    return (EAttribute)xStructuralFeatureEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXStructuralFeature_Derived()
  {
    return (EAttribute)xStructuralFeatureEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXStructuralFeature_ID()
  {
    return (EAttribute)xStructuralFeatureEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXStructuralFeature_Containment()
  {
    return (EAttribute)xStructuralFeatureEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXStructuralFeature_ResolveProxies()
  {
    return (EAttribute)xStructuralFeatureEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXStructuralFeature_Local()
  {
    return (EAttribute)xStructuralFeatureEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXStructuralFeature_Opposite()
  {
    return (EReference)xStructuralFeatureEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXStructuralFeature_Keys()
  {
    return (EReference)xStructuralFeatureEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXStructuralFeature_GetBody()
  {
    return (EReference)xStructuralFeatureEClass.getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXStructuralFeature_SetBody()
  {
    return (EReference)xStructuralFeatureEClass.getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXStructuralFeature_IsSetBody()
  {
    return (EReference)xStructuralFeatureEClass.getEStructuralFeatures().get(14);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXStructuralFeature_UnsetBody()
  {
    return (EReference)xStructuralFeatureEClass.getEStructuralFeatures().get(15);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXStructuralFeature_Reference()
  {
    return (EAttribute)xStructuralFeatureEClass.getEStructuralFeatures().get(16);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXTypedElement()
  {
    return xTypedElementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXTypedElement_Unordered()
  {
    return (EAttribute)xTypedElementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXTypedElement_Unique()
  {
    return (EAttribute)xTypedElementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXTypedElement_Type()
  {
    return (EReference)xTypedElementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXTypedElement_Multiplicity()
  {
    return (EAttribute)xTypedElementEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXStringToStringMapEntry()
  {
    return xStringToStringMapEntryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXStringToStringMapEntry_Key()
  {
    return (EAttribute)xStringToStringMapEntryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXStringToStringMapEntry_Value()
  {
    return (EAttribute)xStringToStringMapEntryEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXGenericType()
  {
    return xGenericTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXGenericType_UpperBound()
  {
    return (EReference)xGenericTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXGenericType_TypeArguments()
  {
    return (EReference)xGenericTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXGenericType_LowerBound()
  {
    return (EReference)xGenericTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXGenericType_Type()
  {
    return (EReference)xGenericTypeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXImportDirective()
  {
    return xImportDirectiveEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXImportDirective_ImportedNamespace()
  {
    return (EAttribute)xImportDirectiveEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXMember()
  {
    return xMemberEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXMember_ContainingClass()
  {
    return (EReference)xMemberEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXTypeParameter()
  {
    return xTypeParameterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXTypeParameter_Bounds()
  {
    return (EReference)xTypeParameterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getXMultiplicity()
  {
    return xMultiplicityEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XcoreFactory getXcoreFactory()
  {
    return (XcoreFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    xAnnotationEClass = createEClass(XANNOTATION);
    createEReference(xAnnotationEClass, XANNOTATION__SOURCE);
    createEReference(xAnnotationEClass, XANNOTATION__DETAILS);
    createEReference(xAnnotationEClass, XANNOTATION__MODEL_ELEMENT);

    xAnnotationDirectiveEClass = createEClass(XANNOTATION_DIRECTIVE);
    createEAttribute(xAnnotationDirectiveEClass, XANNOTATION_DIRECTIVE__SOURCE_URI);

    xClassEClass = createEClass(XCLASS);
    createEAttribute(xClassEClass, XCLASS__ABSTRACT);
    createEAttribute(xClassEClass, XCLASS__INTERFACE);
    createEReference(xClassEClass, XCLASS__MEMBERS);
    createEReference(xClassEClass, XCLASS__SUPER_TYPES);

    xClassifierEClass = createEClass(XCLASSIFIER);
    createEAttribute(xClassifierEClass, XCLASSIFIER__INSTANCE_TYPE_NAME);
    createEReference(xClassifierEClass, XCLASSIFIER__PACKAGE);
    createEReference(xClassifierEClass, XCLASSIFIER__TYPE_PARAMETERS);

    xDataTypeEClass = createEClass(XDATA_TYPE);
    createEAttribute(xDataTypeEClass, XDATA_TYPE__SERIALIZABLE);
    createEReference(xDataTypeEClass, XDATA_TYPE__CREATE_BODY);
    createEReference(xDataTypeEClass, XDATA_TYPE__CONVERT_BODY);

    xEnumEClass = createEClass(XENUM);
    createEReference(xEnumEClass, XENUM__LITERALS);

    xEnumLiteralEClass = createEClass(XENUM_LITERAL);
    createEAttribute(xEnumLiteralEClass, XENUM_LITERAL__VALUE);
    createEAttribute(xEnumLiteralEClass, XENUM_LITERAL__LITERAL);
    createEReference(xEnumLiteralEClass, XENUM_LITERAL__ENUM);

    xGenericTypeEClass = createEClass(XGENERIC_TYPE);
    createEReference(xGenericTypeEClass, XGENERIC_TYPE__UPPER_BOUND);
    createEReference(xGenericTypeEClass, XGENERIC_TYPE__TYPE_ARGUMENTS);
    createEReference(xGenericTypeEClass, XGENERIC_TYPE__LOWER_BOUND);
    createEReference(xGenericTypeEClass, XGENERIC_TYPE__TYPE);

    xImportDirectiveEClass = createEClass(XIMPORT_DIRECTIVE);
    createEAttribute(xImportDirectiveEClass, XIMPORT_DIRECTIVE__IMPORTED_NAMESPACE);

    xMemberEClass = createEClass(XMEMBER);
    createEReference(xMemberEClass, XMEMBER__CONTAINING_CLASS);

    xModelElementEClass = createEClass(XMODEL_ELEMENT);
    createEReference(xModelElementEClass, XMODEL_ELEMENT__ANNOTATIONS);

    xNamedElementEClass = createEClass(XNAMED_ELEMENT);
    createEAttribute(xNamedElementEClass, XNAMED_ELEMENT__NAME);

    xOperationEClass = createEClass(XOPERATION);
    createEReference(xOperationEClass, XOPERATION__TYPE_PARAMETERS);
    createEReference(xOperationEClass, XOPERATION__PARAMETERS);
    createEReference(xOperationEClass, XOPERATION__EXCEPTIONS);
    createEReference(xOperationEClass, XOPERATION__BODY);

    xPackageEClass = createEClass(XPACKAGE);
    createEAttribute(xPackageEClass, XPACKAGE__NS_URI);
    createEAttribute(xPackageEClass, XPACKAGE__NS_PREFIX);
    createEReference(xPackageEClass, XPACKAGE__IMPORT_DIRECTIVES);
    createEReference(xPackageEClass, XPACKAGE__ANNOTATION_DIRECTIVES);
    createEReference(xPackageEClass, XPACKAGE__CLASSIFIERS);

    xParameterEClass = createEClass(XPARAMETER);
    createEReference(xParameterEClass, XPARAMETER__OPERATION);

    xStringToStringMapEntryEClass = createEClass(XSTRING_TO_STRING_MAP_ENTRY);
    createEAttribute(xStringToStringMapEntryEClass, XSTRING_TO_STRING_MAP_ENTRY__KEY);
    createEAttribute(xStringToStringMapEntryEClass, XSTRING_TO_STRING_MAP_ENTRY__VALUE);

    xStructuralFeatureEClass = createEClass(XSTRUCTURAL_FEATURE);
    createEAttribute(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__READONLY);
    createEAttribute(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__VOLATILE);
    createEAttribute(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__TRANSIENT);
    createEAttribute(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__DEFAULT_VALUE_LITERAL);
    createEAttribute(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__UNSETTABLE);
    createEAttribute(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__DERIVED);
    createEAttribute(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__ID);
    createEAttribute(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__CONTAINMENT);
    createEAttribute(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__RESOLVE_PROXIES);
    createEAttribute(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__LOCAL);
    createEReference(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__OPPOSITE);
    createEReference(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__KEYS);
    createEReference(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__GET_BODY);
    createEReference(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__SET_BODY);
    createEReference(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__IS_SET_BODY);
    createEReference(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__UNSET_BODY);
    createEAttribute(xStructuralFeatureEClass, XSTRUCTURAL_FEATURE__REFERENCE);

    xTypedElementEClass = createEClass(XTYPED_ELEMENT);
    createEAttribute(xTypedElementEClass, XTYPED_ELEMENT__UNORDERED);
    createEAttribute(xTypedElementEClass, XTYPED_ELEMENT__UNIQUE);
    createEReference(xTypedElementEClass, XTYPED_ELEMENT__TYPE);
    createEAttribute(xTypedElementEClass, XTYPED_ELEMENT__MULTIPLICITY);

    xTypeParameterEClass = createEClass(XTYPE_PARAMETER);
    createEReference(xTypeParameterEClass, XTYPE_PARAMETER__BOUNDS);

    // Create data types
    xMultiplicityEDataType = createEDataType(XMULTIPLICITY);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
    XbasePackage theXbasePackage = (XbasePackage)EPackage.Registry.INSTANCE.getEPackage(XbasePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    xAnnotationEClass.getESuperTypes().add(this.getXModelElement());
    xAnnotationDirectiveEClass.getESuperTypes().add(this.getXNamedElement());
    xClassEClass.getESuperTypes().add(this.getXClassifier());
    xClassifierEClass.getESuperTypes().add(this.getXNamedElement());
    xDataTypeEClass.getESuperTypes().add(this.getXClassifier());
    xEnumEClass.getESuperTypes().add(this.getXDataType());
    xEnumLiteralEClass.getESuperTypes().add(this.getXNamedElement());
    xImportDirectiveEClass.getESuperTypes().add(this.getXModelElement());
    xMemberEClass.getESuperTypes().add(this.getXTypedElement());
    xNamedElementEClass.getESuperTypes().add(this.getXModelElement());
    xOperationEClass.getESuperTypes().add(this.getXMember());
    xPackageEClass.getESuperTypes().add(this.getXNamedElement());
    xParameterEClass.getESuperTypes().add(this.getXTypedElement());
    xStructuralFeatureEClass.getESuperTypes().add(this.getXMember());
    xTypedElementEClass.getESuperTypes().add(this.getXNamedElement());
    xTypeParameterEClass.getESuperTypes().add(this.getXNamedElement());

    // Initialize classes and features; add operations and parameters
    initEClass(xAnnotationEClass, XAnnotation.class, "XAnnotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getXAnnotation_Source(), this.getXAnnotationDirective(), null, "source", null, 0, 1, XAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXAnnotation_Details(), this.getXStringToStringMapEntry(), null, "details", null, 0, -1, XAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXAnnotation_ModelElement(), this.getXModelElement(), this.getXModelElement_Annotations(), "modelElement", null, 0, 1, XAnnotation.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xAnnotationDirectiveEClass, XAnnotationDirective.class, "XAnnotationDirective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXAnnotationDirective_SourceURI(), theEcorePackage.getEString(), "sourceURI", null, 0, 1, XAnnotationDirective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xClassEClass, XClass.class, "XClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXClass_Abstract(), theEcorePackage.getEBoolean(), "abstract", null, 0, 1, XClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXClass_Interface(), theEcorePackage.getEBoolean(), "interface", null, 0, 1, XClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXClass_Members(), this.getXMember(), this.getXMember_ContainingClass(), "members", null, 0, -1, XClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXClass_SuperTypes(), this.getXGenericType(), null, "superTypes", null, 0, -1, XClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xClassifierEClass, XClassifier.class, "XClassifier", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXClassifier_InstanceTypeName(), theEcorePackage.getEString(), "instanceTypeName", null, 0, 1, XClassifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXClassifier_Package(), this.getXPackage(), this.getXPackage_Classifiers(), "package", null, 0, 1, XClassifier.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXClassifier_TypeParameters(), this.getXTypeParameter(), null, "typeParameters", null, 0, -1, XClassifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xDataTypeEClass, XDataType.class, "XDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXDataType_Serializable(), theEcorePackage.getEBoolean(), "serializable", "true", 0, 1, XDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXDataType_CreateBody(), theXbasePackage.getXBlockExpression(), null, "createBody", null, 0, 1, XDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXDataType_ConvertBody(), theXbasePackage.getXBlockExpression(), null, "convertBody", null, 0, 1, XDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xEnumEClass, XEnum.class, "XEnum", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getXEnum_Literals(), this.getXEnumLiteral(), this.getXEnumLiteral_Enum(), "literals", null, 0, -1, XEnum.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xEnumLiteralEClass, XEnumLiteral.class, "XEnumLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXEnumLiteral_Value(), theEcorePackage.getEInt(), "value", null, 0, 1, XEnumLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXEnumLiteral_Literal(), theEcorePackage.getEString(), "literal", null, 0, 1, XEnumLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXEnumLiteral_Enum(), this.getXEnum(), this.getXEnum_Literals(), "enum", null, 0, 1, XEnumLiteral.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xGenericTypeEClass, XGenericType.class, "XGenericType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getXGenericType_UpperBound(), this.getXGenericType(), null, "upperBound", null, 0, 1, XGenericType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXGenericType_TypeArguments(), this.getXGenericType(), null, "typeArguments", null, 0, -1, XGenericType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXGenericType_LowerBound(), this.getXGenericType(), null, "lowerBound", null, 0, 1, XGenericType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXGenericType_Type(), theEcorePackage.getENamedElement(), null, "type", null, 0, 1, XGenericType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xImportDirectiveEClass, XImportDirective.class, "XImportDirective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXImportDirective_ImportedNamespace(), theEcorePackage.getEString(), "importedNamespace", null, 1, 1, XImportDirective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xMemberEClass, XMember.class, "XMember", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getXMember_ContainingClass(), this.getXClass(), this.getXClass_Members(), "containingClass", null, 0, 1, XMember.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xModelElementEClass, XModelElement.class, "XModelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getXModelElement_Annotations(), this.getXAnnotation(), this.getXAnnotation_ModelElement(), "annotations", null, 0, -1, XModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xNamedElementEClass, XNamedElement.class, "XNamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXNamedElement_Name(), theEcorePackage.getEString(), "name", null, 0, 1, XNamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xOperationEClass, XOperation.class, "XOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getXOperation_TypeParameters(), this.getXTypeParameter(), null, "typeParameters", null, 0, -1, XOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXOperation_Parameters(), this.getXParameter(), this.getXParameter_Operation(), "parameters", null, 0, -1, XOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXOperation_Exceptions(), this.getXGenericType(), null, "exceptions", null, 0, -1, XOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXOperation_Body(), theXbasePackage.getXBlockExpression(), null, "body", null, 0, 1, XOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xPackageEClass, XPackage.class, "XPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXPackage_NsURI(), theEcorePackage.getEString(), "nsURI", null, 0, 1, XPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXPackage_NsPrefix(), theEcorePackage.getEString(), "nsPrefix", null, 0, 1, XPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXPackage_ImportDirectives(), this.getXImportDirective(), null, "importDirectives", null, 0, -1, XPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXPackage_AnnotationDirectives(), this.getXAnnotationDirective(), null, "annotationDirectives", null, 0, -1, XPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXPackage_Classifiers(), this.getXClassifier(), this.getXClassifier_Package(), "classifiers", null, 0, -1, XPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xParameterEClass, XParameter.class, "XParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getXParameter_Operation(), this.getXOperation(), this.getXOperation_Parameters(), "operation", null, 0, 1, XParameter.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xStringToStringMapEntryEClass, Map.Entry.class, "XStringToStringMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXStringToStringMapEntry_Key(), theEcorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXStringToStringMapEntry_Value(), theEcorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xStructuralFeatureEClass, XStructuralFeature.class, "XStructuralFeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXStructuralFeature_Readonly(), theEcorePackage.getEBoolean(), "readonly", null, 0, 1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXStructuralFeature_Volatile(), theEcorePackage.getEBoolean(), "volatile", null, 0, 1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXStructuralFeature_Transient(), theEcorePackage.getEBoolean(), "transient", null, 0, 1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXStructuralFeature_DefaultValueLiteral(), theEcorePackage.getEString(), "defaultValueLiteral", null, 0, 1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXStructuralFeature_Unsettable(), theEcorePackage.getEBoolean(), "unsettable", null, 0, 1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXStructuralFeature_Derived(), theEcorePackage.getEBoolean(), "derived", null, 0, 1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXStructuralFeature_ID(), theEcorePackage.getEBoolean(), "iD", null, 0, 1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXStructuralFeature_Containment(), theEcorePackage.getEBoolean(), "containment", null, 0, 1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXStructuralFeature_ResolveProxies(), theEcorePackage.getEBoolean(), "resolveProxies", null, 0, 1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXStructuralFeature_Local(), theEcorePackage.getEBoolean(), "local", null, 0, 1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXStructuralFeature_Opposite(), theEcorePackage.getEReference(), null, "opposite", null, 0, 1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXStructuralFeature_Keys(), theEcorePackage.getEAttribute(), null, "keys", null, 0, -1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXStructuralFeature_GetBody(), theXbasePackage.getXBlockExpression(), null, "getBody", null, 0, 1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXStructuralFeature_SetBody(), theXbasePackage.getXBlockExpression(), null, "setBody", null, 0, 1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXStructuralFeature_IsSetBody(), theXbasePackage.getXBlockExpression(), null, "isSetBody", null, 0, 1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXStructuralFeature_UnsetBody(), theXbasePackage.getXBlockExpression(), null, "unsetBody", null, 0, 1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXStructuralFeature_Reference(), theEcorePackage.getEBoolean(), "reference", null, 0, 1, XStructuralFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xTypedElementEClass, XTypedElement.class, "XTypedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXTypedElement_Unordered(), theEcorePackage.getEBoolean(), "unordered", null, 0, 1, XTypedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXTypedElement_Unique(), theEcorePackage.getEBoolean(), "unique", null, 0, 1, XTypedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXTypedElement_Type(), this.getXGenericType(), null, "type", null, 0, 1, XTypedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXTypedElement_Multiplicity(), this.getXMultiplicity(), "multiplicity", null, 0, 1, XTypedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xTypeParameterEClass, XTypeParameter.class, "XTypeParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getXTypeParameter_Bounds(), this.getXGenericType(), null, "bounds", null, 0, -1, XTypeParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize data types
    initEDataType(xMultiplicityEDataType, int[].class, "XMultiplicity", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

} //XcorePackageImpl

/*
package xcore 
*/
