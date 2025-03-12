/**
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import org.eclipse.emf.codegen.ecore.genmodel.GenCodeStyle;
import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenDecoration;
import org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenEclipsePlatformVersion;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOSGiStyle;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenProviderKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimePlatform;
import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypedElement;

import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelValidator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GenModelPackageImpl extends EPackageImpl implements GenModelPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass genModelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass genPackageEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass genClassEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass genFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass genBaseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass genEnumEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass genEnumLiteralEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass genClassifierEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass genDataTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass genOperationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass genParameterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass genTypedElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass genAnnotationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass genTypeParameterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum genProviderKindEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum genPropertyKindEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum genResourceKindEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum genDelegationKindEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum genJDKLevelEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum genRuntimeVersionEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum genRuntimePlatformEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum genDecorationEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  private EEnum genEclipsePlatformVersionEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.19
   * @generated
   */
  private EEnum genCodeStyleEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.33
   * @generated
   */
  private EEnum genOSGiStyleEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  private EDataType pathEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  private EDataType propertyEditorFactoryEDataType = null;

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
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private GenModelPackageImpl()
  {
    super(eNS_URI, GenModelFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link GenModelPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static GenModelPackage init()
  {
    if (isInited) return (GenModelPackage)EPackage.Registry.INSTANCE.getEPackage(GenModelPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredGenModelPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    GenModelPackageImpl theGenModelPackage = registeredGenModelPackage instanceof GenModelPackageImpl ? (GenModelPackageImpl)registeredGenModelPackage : new GenModelPackageImpl();

    isInited = true;

    // Initialize simple dependencies
    EcorePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theGenModelPackage.createPackageContents();

    // Initialize created meta-data
    theGenModelPackage.initializePackageContents();

    // Register package validator
    EValidator.Registry.INSTANCE.put
      (theGenModelPackage,
       new EValidator.Descriptor()
       {
         @Override
         public EValidator getEValidator()
         {
           return GenModelValidator.INSTANCE;
         }
       });

    // Mark meta-data to indicate it can't be changed
    theGenModelPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(GenModelPackage.eNS_URI, theGenModelPackage);
    return theGenModelPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGenModel()
  {
    return genModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_CopyrightText()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_ModelDirectory()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_CreationCommands()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_CreationIcons()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_EditDirectory()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_CreationSubmenus()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_EditorDirectory()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_ModelPluginID()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_TemplateDirectory()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_RuntimeJar()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_ForeignModel()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_DynamicTemplates()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_Redirection()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_ForceOverwrite()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_NonExternalizedStringTag()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(14);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_ModelName()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(15);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_ModelPluginClass()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(16);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_EditPluginClass()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(17);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_EditorPluginClass()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(18);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_UpdateClasspath()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(19);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_GenerateSchema()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(20);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_NonNLSMarkers()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(21);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_StaticPackages()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(22);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_ModelPluginVariables()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(23);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_RootExtendsInterface()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(24);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_RootExtendsClass()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(25);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_RootImplementsInterface()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(26);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_SuppressEMFTypes()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(27);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_SuppressEMFMetaData()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(28);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_SuppressEMFModelTags()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(29);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_SuppressInterfaces()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(30);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_FeatureMapWrapperInterface()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(31);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_FeatureMapWrapperInternalInterface()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(32);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_FeatureMapWrapperClass()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(33);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_RuntimeCompatibility()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(34);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_RichClientPlatform()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(35);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_ReflectiveDelegation()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(36);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_CodeFormatting()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(37);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_CommentFormatting()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(38);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_TestsDirectory()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(39);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_TestSuiteClass()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(40);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_BooleanFlagsField()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(41);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_BooleanFlagsReservedBits()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(42);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_ImporterID()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(43);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_BundleManifest()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(44);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_FeatureDelegation()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(45);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_ContainmentProxies()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(46);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_MinimalReflectiveMethods()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(47);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_SuppressContainment()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(48);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_SuppressNotification()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(49);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_ArrayAccessors()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(50);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_SuppressUnsettable()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(51);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_FacadeHelperClass()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(52);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_ComplianceLevel()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(53);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_SuppressGenModelAnnotations()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(54);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_CopyrightFields()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(55);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_BinaryCompatibleReflectiveMethods()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(56);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_PublicConstructors()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(57);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_TemplatePluginVariables()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(58);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_ProviderRootExtendsClass()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(59);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_EditPluginID()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(60);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_EditPluginVariables()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(61);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_EditorPluginID()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(62);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_EditorPluginVariables()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(63);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_TestsPluginID()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(64);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_TestsPluginVariables()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(65);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_OptimizedHasChildren()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(66);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_TableProviders()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(67);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_ColorProviders()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(68);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_FontProviders()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(69);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_RuntimeVersion()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(70);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_Language()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(71);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_PackedEnums()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(72);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenModel_GenPackages()
  {
    return (EReference)genModelEClass.getEStructuralFeatures().get(73);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenModel_UsedGenPackages()
  {
    return (EReference)genModelEClass.getEStructuralFeatures().get(74);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_InterfaceNamePattern()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(75);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_ClassNamePattern()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(76);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_OperationReflection()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(77);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_RichAjaxPlatform()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(78);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_RuntimePlatform()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(79);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_ImportOrganizing()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(80);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_PluginKey()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(81);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_Decoration()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(82);
  }

  /**
   * <!-- begin-user-doc -->
   * @since 2.10
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_StyleProviders()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(83);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_Cleanup()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(84);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenModel_OSGiCompatible()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(85);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenModel_EclipsePlatformVersion()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(86);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenModel_ModelDocumentation()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(87);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenModel_AutoResizeProperties()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(88);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenModel_AutoExpandProperties()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(89);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenModel_FindAction()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(90);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenModel_ExpandAllAction()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(91);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenModel_CollapseAllAction()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(92);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenModel_RevertAction()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(93);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.19
   * @generated
   */
  @Override
  public EAttribute getGenModel_CodeStyle()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(94);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.33
   * @generated
   */
  @Override
  public EAttribute getGenModel_OSGiStyle()
  {
    return (EAttribute)genModelEClass.getEStructuralFeatures().get(95);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGenPackage()
  {
    return genPackageEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_Prefix()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_BasePackage()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_Resource()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_DisposableProviderFactory()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_AdapterFactory()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_LoadInitialization()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_InterfacePackageSuffix()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_MetaDataPackageSuffix()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_ClassPackageSuffix()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_UtilityPackageSuffix()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_ProviderPackageSuffix()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_PresentationPackageSuffix()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_TestsPackageSuffix()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_GenerateExampleClass()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_LiteralsInterface()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(14);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_DataTypeConverters()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(15);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_MultipleEditorPages()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(16);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_GenerateModelWizard()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(17);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_ExtensibleProviderFactory()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(18);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_ChildCreationExtenders()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(19);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_ContentTypeIdentifier()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(20);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_FileExtensions()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(21);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenPackage_EcorePackage()
  {
    return (EReference)genPackageEClass.getEStructuralFeatures().get(22);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenPackage_GenModel()
  {
    return (EReference)genPackageEClass.getEStructuralFeatures().get(23);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenPackage_GenEnums()
  {
    return (EReference)genPackageEClass.getEStructuralFeatures().get(24);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenPackage_GenDataTypes()
  {
    return (EReference)genPackageEClass.getEStructuralFeatures().get(25);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenPackage_GenClasses()
  {
    return (EReference)genPackageEClass.getEStructuralFeatures().get(26);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenPackage_NestedGenPackages()
  {
    return (EReference)genPackageEClass.getEStructuralFeatures().get(27);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenPackage_GenClassifiers()
  {
    return (EReference)genPackageEClass.getEStructuralFeatures().get(28);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenPackage_PublicationLocation()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(29);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenPackage_Documentation()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(30);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.15
   * @generated
   */
  @Override
  public EAttribute getGenPackage_LoadInitializationFileExtension()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(31);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.31
   * @generated
   */
  @Override
  public EAttribute getGenPackage_FamilyTreeInitialization()
  {
    return (EAttribute)genPackageEClass.getEStructuralFeatures().get(32);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGenClass()
  {
    return genClassEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenClass_Provider()
  {
    return (EAttribute)genClassEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenClass_Image()
  {
    return (EAttribute)genClassEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenClass_Dynamic()
  {
    return (EAttribute)genClassEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenClass_EcoreClass()
  {
    return (EReference)genClassEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenClass_GenFeatures()
  {
    return (EReference)genClassEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenClass_GenOperations()
  {
    return (EReference)genClassEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenClass_LabelFeature()
  {
    return (EReference)genClassEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGenFeature()
  {
    return genFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenFeature_Property()
  {
    return (EAttribute)genFeatureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenFeature_Notify()
  {
    return (EAttribute)genFeatureEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenFeature_Children()
  {
    return (EAttribute)genFeatureEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenFeature_CreateChild()
  {
    return (EAttribute)genFeatureEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenFeature_PropertyCategory()
  {
    return (EAttribute)genFeatureEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenFeature_PropertyFilterFlags()
  {
    return (EAttribute)genFeatureEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenFeature_PropertyDescription()
  {
    return (EAttribute)genFeatureEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenFeature_PropertyMultiLine()
  {
    return (EAttribute)genFeatureEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenFeature_PropertySortChoices()
  {
    return (EAttribute)genFeatureEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenFeature_GenClass()
  {
    return (EReference)genFeatureEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenFeature_EcoreFeature()
  {
    return (EReference)genFeatureEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenFeature_SuppressedGetVisibility()
  {
    return (EAttribute)genFeatureEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenFeature_SuppressedSetVisibility()
  {
    return (EAttribute)genFeatureEClass.getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenFeature_SuppressedIsSetVisibility()
  {
    return (EAttribute)genFeatureEClass.getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenFeature_SuppressedUnsetVisibility()
  {
    return (EAttribute)genFeatureEClass.getEStructuralFeatures().get(14);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenFeature_Get()
  {
    return (EAttribute)genFeatureEClass.getEStructuralFeatures().get(15);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenFeature_PropertyEditorFactory()
  {
    return (EAttribute)genFeatureEClass.getEStructuralFeatures().get(16);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGenBase()
  {
    return genBaseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenBase_GenAnnotations()
  {
    return (EReference)genBaseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGenEnum()
  {
    return genEnumEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenEnum_TypeSafeEnumCompatible()
  {
    return (EAttribute)genEnumEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenEnum_EcoreEnum()
  {
    return (EReference)genEnumEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenEnum_GenEnumLiterals()
  {
    return (EReference)genEnumEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGenEnumLiteral()
  {
    return genEnumLiteralEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenEnumLiteral_GenEnum()
  {
    return (EReference)genEnumLiteralEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenEnumLiteral_EcoreEnumLiteral()
  {
    return (EReference)genEnumLiteralEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenEnumLiteral_Documentation()
  {
    return (EAttribute)genEnumLiteralEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGenClassifier()
  {
    return genClassifierEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenClassifier_GenPackage()
  {
    return (EReference)genClassifierEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenClassifier_GenTypeParameters()
  {
    return (EReference)genClassifierEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenClassifier_Documentation()
  {
    return (EAttribute)genClassifierEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGenDataType()
  {
    return genDataTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenDataType_EcoreDataType()
  {
    return (EReference)genDataTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenDataType_Create()
  {
    return (EAttribute)genDataTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenDataType_Convert()
  {
    return (EAttribute)genDataTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenDataType_PropertyEditorFactory()
  {
    return (EAttribute)genDataTypeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGenOperation()
  {
    return genOperationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenOperation_GenClass()
  {
    return (EReference)genOperationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenOperation_EcoreOperation()
  {
    return (EReference)genOperationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenOperation_GenParameters()
  {
    return (EReference)genOperationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenOperation_GenTypeParameters()
  {
    return (EReference)genOperationEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenOperation_SuppressedVisibility()
  {
    return (EAttribute)genOperationEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenOperation_Body()
  {
    return (EAttribute)genOperationEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGenParameter()
  {
    return genParameterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenParameter_GenOperation()
  {
    return (EReference)genParameterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenParameter_EcoreParameter()
  {
    return (EReference)genParameterEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGenTypedElement()
  {
    return genTypedElementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenTypedElement_Documentation()
  {
    return (EAttribute)genTypedElementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGenAnnotation()
  {
    return genAnnotationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getGenAnnotation_Source()
  {
    return (EAttribute)genAnnotationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenAnnotation_Details()
  {
    return (EReference)genAnnotationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenAnnotation_GenBase()
  {
    return (EReference)genAnnotationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenAnnotation_References()
  {
    return (EReference)genAnnotationEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenAnnotation_Contents()
  {
    return (EReference)genAnnotationEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGenTypeParameter()
  {
    return genTypeParameterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGenTypeParameter_EcoreTypeParameter()
  {
    return (EReference)genTypeParameterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EAttribute getGenTypeParameter_Documentation()
  {
    return (EAttribute)genTypeParameterEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EEnum getGenProviderKind()
  {
    return genProviderKindEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EEnum getGenPropertyKind()
  {
    return genPropertyKindEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EEnum getGenResourceKind()
  {
    return genResourceKindEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EEnum getGenDelegationKind()
  {
    return genDelegationKindEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EEnum getGenJDKLevel()
  {
    return genJDKLevelEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EEnum getGenRuntimeVersion()
  {
    return genRuntimeVersionEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EEnum getGenRuntimePlatform()
  {
    return genRuntimePlatformEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EEnum getGenDecoration()
  {
    return genDecorationEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EEnum getGenEclipsePlatformVersion()
  {
    return genEclipsePlatformVersionEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.19
   * @generated
   */
  @Override
  public EEnum getGenCodeStyle()
  {
    return genCodeStyleEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.33
   * @generated
   */
  @Override
  public EEnum getGenOSGiStyle()
  {
    return genOSGiStyleEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EDataType getPath()
  {
    return pathEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  @Override
  public EDataType getPropertyEditorFactory()
  {
    return propertyEditorFactoryEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public GenModelFactory getGenModelFactory()
  {
    return (GenModelFactory)getEFactoryInstance();
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
    genModelEClass = createEClass(GEN_MODEL);
    createEAttribute(genModelEClass, GEN_MODEL__COPYRIGHT_TEXT);
    createEAttribute(genModelEClass, GEN_MODEL__MODEL_DIRECTORY);
    createEAttribute(genModelEClass, GEN_MODEL__CREATION_COMMANDS);
    createEAttribute(genModelEClass, GEN_MODEL__CREATION_ICONS);
    createEAttribute(genModelEClass, GEN_MODEL__CREATION_SUBMENUS);
    createEAttribute(genModelEClass, GEN_MODEL__EDIT_DIRECTORY);
    createEAttribute(genModelEClass, GEN_MODEL__EDITOR_DIRECTORY);
    createEAttribute(genModelEClass, GEN_MODEL__MODEL_PLUGIN_ID);
    createEAttribute(genModelEClass, GEN_MODEL__TEMPLATE_DIRECTORY);
    createEAttribute(genModelEClass, GEN_MODEL__RUNTIME_JAR);
    createEAttribute(genModelEClass, GEN_MODEL__FOREIGN_MODEL);
    createEAttribute(genModelEClass, GEN_MODEL__DYNAMIC_TEMPLATES);
    createEAttribute(genModelEClass, GEN_MODEL__REDIRECTION);
    createEAttribute(genModelEClass, GEN_MODEL__FORCE_OVERWRITE);
    createEAttribute(genModelEClass, GEN_MODEL__NON_EXTERNALIZED_STRING_TAG);
    createEAttribute(genModelEClass, GEN_MODEL__MODEL_NAME);
    createEAttribute(genModelEClass, GEN_MODEL__MODEL_PLUGIN_CLASS);
    createEAttribute(genModelEClass, GEN_MODEL__EDIT_PLUGIN_CLASS);
    createEAttribute(genModelEClass, GEN_MODEL__EDITOR_PLUGIN_CLASS);
    createEAttribute(genModelEClass, GEN_MODEL__UPDATE_CLASSPATH);
    createEAttribute(genModelEClass, GEN_MODEL__GENERATE_SCHEMA);
    createEAttribute(genModelEClass, GEN_MODEL__NON_NLS_MARKERS);
    createEAttribute(genModelEClass, GEN_MODEL__STATIC_PACKAGES);
    createEAttribute(genModelEClass, GEN_MODEL__MODEL_PLUGIN_VARIABLES);
    createEAttribute(genModelEClass, GEN_MODEL__ROOT_EXTENDS_INTERFACE);
    createEAttribute(genModelEClass, GEN_MODEL__ROOT_EXTENDS_CLASS);
    createEAttribute(genModelEClass, GEN_MODEL__ROOT_IMPLEMENTS_INTERFACE);
    createEAttribute(genModelEClass, GEN_MODEL__SUPPRESS_EMF_TYPES);
    createEAttribute(genModelEClass, GEN_MODEL__SUPPRESS_EMF_META_DATA);
    createEAttribute(genModelEClass, GEN_MODEL__SUPPRESS_EMF_MODEL_TAGS);
    createEAttribute(genModelEClass, GEN_MODEL__SUPPRESS_INTERFACES);
    createEAttribute(genModelEClass, GEN_MODEL__FEATURE_MAP_WRAPPER_INTERFACE);
    createEAttribute(genModelEClass, GEN_MODEL__FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE);
    createEAttribute(genModelEClass, GEN_MODEL__FEATURE_MAP_WRAPPER_CLASS);
    createEAttribute(genModelEClass, GEN_MODEL__RUNTIME_COMPATIBILITY);
    createEAttribute(genModelEClass, GEN_MODEL__RICH_CLIENT_PLATFORM);
    createEAttribute(genModelEClass, GEN_MODEL__REFLECTIVE_DELEGATION);
    createEAttribute(genModelEClass, GEN_MODEL__CODE_FORMATTING);
    createEAttribute(genModelEClass, GEN_MODEL__COMMENT_FORMATTING);
    createEAttribute(genModelEClass, GEN_MODEL__TESTS_DIRECTORY);
    createEAttribute(genModelEClass, GEN_MODEL__TEST_SUITE_CLASS);
    createEAttribute(genModelEClass, GEN_MODEL__BOOLEAN_FLAGS_FIELD);
    createEAttribute(genModelEClass, GEN_MODEL__BOOLEAN_FLAGS_RESERVED_BITS);
    createEAttribute(genModelEClass, GEN_MODEL__IMPORTER_ID);
    createEAttribute(genModelEClass, GEN_MODEL__BUNDLE_MANIFEST);
    createEAttribute(genModelEClass, GEN_MODEL__FEATURE_DELEGATION);
    createEAttribute(genModelEClass, GEN_MODEL__CONTAINMENT_PROXIES);
    createEAttribute(genModelEClass, GEN_MODEL__MINIMAL_REFLECTIVE_METHODS);
    createEAttribute(genModelEClass, GEN_MODEL__SUPPRESS_CONTAINMENT);
    createEAttribute(genModelEClass, GEN_MODEL__SUPPRESS_NOTIFICATION);
    createEAttribute(genModelEClass, GEN_MODEL__ARRAY_ACCESSORS);
    createEAttribute(genModelEClass, GEN_MODEL__SUPPRESS_UNSETTABLE);
    createEAttribute(genModelEClass, GEN_MODEL__FACADE_HELPER_CLASS);
    createEAttribute(genModelEClass, GEN_MODEL__COMPLIANCE_LEVEL);
    createEAttribute(genModelEClass, GEN_MODEL__SUPPRESS_GEN_MODEL_ANNOTATIONS);
    createEAttribute(genModelEClass, GEN_MODEL__COPYRIGHT_FIELDS);
    createEAttribute(genModelEClass, GEN_MODEL__BINARY_COMPATIBLE_REFLECTIVE_METHODS);
    createEAttribute(genModelEClass, GEN_MODEL__PUBLIC_CONSTRUCTORS);
    createEAttribute(genModelEClass, GEN_MODEL__TEMPLATE_PLUGIN_VARIABLES);
    createEAttribute(genModelEClass, GEN_MODEL__PROVIDER_ROOT_EXTENDS_CLASS);
    createEAttribute(genModelEClass, GEN_MODEL__EDIT_PLUGIN_ID);
    createEAttribute(genModelEClass, GEN_MODEL__EDIT_PLUGIN_VARIABLES);
    createEAttribute(genModelEClass, GEN_MODEL__EDITOR_PLUGIN_ID);
    createEAttribute(genModelEClass, GEN_MODEL__EDITOR_PLUGIN_VARIABLES);
    createEAttribute(genModelEClass, GEN_MODEL__TESTS_PLUGIN_ID);
    createEAttribute(genModelEClass, GEN_MODEL__TESTS_PLUGIN_VARIABLES);
    createEAttribute(genModelEClass, GEN_MODEL__OPTIMIZED_HAS_CHILDREN);
    createEAttribute(genModelEClass, GEN_MODEL__TABLE_PROVIDERS);
    createEAttribute(genModelEClass, GEN_MODEL__COLOR_PROVIDERS);
    createEAttribute(genModelEClass, GEN_MODEL__FONT_PROVIDERS);
    createEAttribute(genModelEClass, GEN_MODEL__RUNTIME_VERSION);
    createEAttribute(genModelEClass, GEN_MODEL__LANGUAGE);
    createEAttribute(genModelEClass, GEN_MODEL__PACKED_ENUMS);
    createEReference(genModelEClass, GEN_MODEL__GEN_PACKAGES);
    createEReference(genModelEClass, GEN_MODEL__USED_GEN_PACKAGES);
    createEAttribute(genModelEClass, GEN_MODEL__INTERFACE_NAME_PATTERN);
    createEAttribute(genModelEClass, GEN_MODEL__CLASS_NAME_PATTERN);
    createEAttribute(genModelEClass, GEN_MODEL__OPERATION_REFLECTION);
    createEAttribute(genModelEClass, GEN_MODEL__RICH_AJAX_PLATFORM);
    createEAttribute(genModelEClass, GEN_MODEL__RUNTIME_PLATFORM);
    createEAttribute(genModelEClass, GEN_MODEL__IMPORT_ORGANIZING);
    createEAttribute(genModelEClass, GEN_MODEL__PLUGIN_KEY);
    createEAttribute(genModelEClass, GEN_MODEL__DECORATION);
    createEAttribute(genModelEClass, GEN_MODEL__STYLE_PROVIDERS);
    createEAttribute(genModelEClass, GEN_MODEL__CLEANUP);
    createEAttribute(genModelEClass, GEN_MODEL__OS_GI_COMPATIBLE);
    createEAttribute(genModelEClass, GEN_MODEL__ECLIPSE_PLATFORM_VERSION);
    createEAttribute(genModelEClass, GEN_MODEL__MODEL_DOCUMENTATION);
    createEAttribute(genModelEClass, GEN_MODEL__AUTO_RESIZE_PROPERTIES);
    createEAttribute(genModelEClass, GEN_MODEL__AUTO_EXPAND_PROPERTIES);
    createEAttribute(genModelEClass, GEN_MODEL__FIND_ACTION);
    createEAttribute(genModelEClass, GEN_MODEL__EXPAND_ALL_ACTION);
    createEAttribute(genModelEClass, GEN_MODEL__COLLAPSE_ALL_ACTION);
    createEAttribute(genModelEClass, GEN_MODEL__REVERT_ACTION);
    createEAttribute(genModelEClass, GEN_MODEL__CODE_STYLE);
    createEAttribute(genModelEClass, GEN_MODEL__OS_GI_STYLE);

    genPackageEClass = createEClass(GEN_PACKAGE);
    createEAttribute(genPackageEClass, GEN_PACKAGE__PREFIX);
    createEAttribute(genPackageEClass, GEN_PACKAGE__BASE_PACKAGE);
    createEAttribute(genPackageEClass, GEN_PACKAGE__RESOURCE);
    createEAttribute(genPackageEClass, GEN_PACKAGE__DISPOSABLE_PROVIDER_FACTORY);
    createEAttribute(genPackageEClass, GEN_PACKAGE__ADAPTER_FACTORY);
    createEAttribute(genPackageEClass, GEN_PACKAGE__LOAD_INITIALIZATION);
    createEAttribute(genPackageEClass, GEN_PACKAGE__INTERFACE_PACKAGE_SUFFIX);
    createEAttribute(genPackageEClass, GEN_PACKAGE__META_DATA_PACKAGE_SUFFIX);
    createEAttribute(genPackageEClass, GEN_PACKAGE__CLASS_PACKAGE_SUFFIX);
    createEAttribute(genPackageEClass, GEN_PACKAGE__UTILITY_PACKAGE_SUFFIX);
    createEAttribute(genPackageEClass, GEN_PACKAGE__PROVIDER_PACKAGE_SUFFIX);
    createEAttribute(genPackageEClass, GEN_PACKAGE__PRESENTATION_PACKAGE_SUFFIX);
    createEAttribute(genPackageEClass, GEN_PACKAGE__TESTS_PACKAGE_SUFFIX);
    createEAttribute(genPackageEClass, GEN_PACKAGE__GENERATE_EXAMPLE_CLASS);
    createEAttribute(genPackageEClass, GEN_PACKAGE__LITERALS_INTERFACE);
    createEAttribute(genPackageEClass, GEN_PACKAGE__DATA_TYPE_CONVERTERS);
    createEAttribute(genPackageEClass, GEN_PACKAGE__MULTIPLE_EDITOR_PAGES);
    createEAttribute(genPackageEClass, GEN_PACKAGE__GENERATE_MODEL_WIZARD);
    createEAttribute(genPackageEClass, GEN_PACKAGE__EXTENSIBLE_PROVIDER_FACTORY);
    createEAttribute(genPackageEClass, GEN_PACKAGE__CHILD_CREATION_EXTENDERS);
    createEAttribute(genPackageEClass, GEN_PACKAGE__CONTENT_TYPE_IDENTIFIER);
    createEAttribute(genPackageEClass, GEN_PACKAGE__FILE_EXTENSIONS);
    createEReference(genPackageEClass, GEN_PACKAGE__ECORE_PACKAGE);
    createEReference(genPackageEClass, GEN_PACKAGE__GEN_MODEL);
    createEReference(genPackageEClass, GEN_PACKAGE__GEN_ENUMS);
    createEReference(genPackageEClass, GEN_PACKAGE__GEN_DATA_TYPES);
    createEReference(genPackageEClass, GEN_PACKAGE__GEN_CLASSES);
    createEReference(genPackageEClass, GEN_PACKAGE__NESTED_GEN_PACKAGES);
    createEReference(genPackageEClass, GEN_PACKAGE__GEN_CLASSIFIERS);
    createEAttribute(genPackageEClass, GEN_PACKAGE__PUBLICATION_LOCATION);
    createEAttribute(genPackageEClass, GEN_PACKAGE__DOCUMENTATION);
    createEAttribute(genPackageEClass, GEN_PACKAGE__LOAD_INITIALIZATION_FILE_EXTENSION);
    createEAttribute(genPackageEClass, GEN_PACKAGE__FAMILY_TREE_INITIALIZATION);

    genClassEClass = createEClass(GEN_CLASS);
    createEAttribute(genClassEClass, GEN_CLASS__PROVIDER);
    createEAttribute(genClassEClass, GEN_CLASS__IMAGE);
    createEAttribute(genClassEClass, GEN_CLASS__DYNAMIC);
    createEReference(genClassEClass, GEN_CLASS__ECORE_CLASS);
    createEReference(genClassEClass, GEN_CLASS__GEN_FEATURES);
    createEReference(genClassEClass, GEN_CLASS__GEN_OPERATIONS);
    createEReference(genClassEClass, GEN_CLASS__LABEL_FEATURE);

    genFeatureEClass = createEClass(GEN_FEATURE);
    createEAttribute(genFeatureEClass, GEN_FEATURE__PROPERTY);
    createEAttribute(genFeatureEClass, GEN_FEATURE__NOTIFY);
    createEAttribute(genFeatureEClass, GEN_FEATURE__CHILDREN);
    createEAttribute(genFeatureEClass, GEN_FEATURE__CREATE_CHILD);
    createEAttribute(genFeatureEClass, GEN_FEATURE__PROPERTY_CATEGORY);
    createEAttribute(genFeatureEClass, GEN_FEATURE__PROPERTY_FILTER_FLAGS);
    createEAttribute(genFeatureEClass, GEN_FEATURE__PROPERTY_DESCRIPTION);
    createEAttribute(genFeatureEClass, GEN_FEATURE__PROPERTY_MULTI_LINE);
    createEAttribute(genFeatureEClass, GEN_FEATURE__PROPERTY_SORT_CHOICES);
    createEReference(genFeatureEClass, GEN_FEATURE__GEN_CLASS);
    createEReference(genFeatureEClass, GEN_FEATURE__ECORE_FEATURE);
    createEAttribute(genFeatureEClass, GEN_FEATURE__SUPPRESSED_GET_VISIBILITY);
    createEAttribute(genFeatureEClass, GEN_FEATURE__SUPPRESSED_SET_VISIBILITY);
    createEAttribute(genFeatureEClass, GEN_FEATURE__SUPPRESSED_IS_SET_VISIBILITY);
    createEAttribute(genFeatureEClass, GEN_FEATURE__SUPPRESSED_UNSET_VISIBILITY);
    createEAttribute(genFeatureEClass, GEN_FEATURE__GET);
    createEAttribute(genFeatureEClass, GEN_FEATURE__PROPERTY_EDITOR_FACTORY);

    genBaseEClass = createEClass(GEN_BASE);
    createEReference(genBaseEClass, GEN_BASE__GEN_ANNOTATIONS);

    genEnumEClass = createEClass(GEN_ENUM);
    createEAttribute(genEnumEClass, GEN_ENUM__TYPE_SAFE_ENUM_COMPATIBLE);
    createEReference(genEnumEClass, GEN_ENUM__ECORE_ENUM);
    createEReference(genEnumEClass, GEN_ENUM__GEN_ENUM_LITERALS);

    genEnumLiteralEClass = createEClass(GEN_ENUM_LITERAL);
    createEReference(genEnumLiteralEClass, GEN_ENUM_LITERAL__GEN_ENUM);
    createEReference(genEnumLiteralEClass, GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL);
    createEAttribute(genEnumLiteralEClass, GEN_ENUM_LITERAL__DOCUMENTATION);

    genClassifierEClass = createEClass(GEN_CLASSIFIER);
    createEReference(genClassifierEClass, GEN_CLASSIFIER__GEN_PACKAGE);
    createEReference(genClassifierEClass, GEN_CLASSIFIER__GEN_TYPE_PARAMETERS);
    createEAttribute(genClassifierEClass, GEN_CLASSIFIER__DOCUMENTATION);

    genDataTypeEClass = createEClass(GEN_DATA_TYPE);
    createEReference(genDataTypeEClass, GEN_DATA_TYPE__ECORE_DATA_TYPE);
    createEAttribute(genDataTypeEClass, GEN_DATA_TYPE__CREATE);
    createEAttribute(genDataTypeEClass, GEN_DATA_TYPE__CONVERT);
    createEAttribute(genDataTypeEClass, GEN_DATA_TYPE__PROPERTY_EDITOR_FACTORY);

    genOperationEClass = createEClass(GEN_OPERATION);
    createEReference(genOperationEClass, GEN_OPERATION__GEN_CLASS);
    createEReference(genOperationEClass, GEN_OPERATION__ECORE_OPERATION);
    createEReference(genOperationEClass, GEN_OPERATION__GEN_PARAMETERS);
    createEReference(genOperationEClass, GEN_OPERATION__GEN_TYPE_PARAMETERS);
    createEAttribute(genOperationEClass, GEN_OPERATION__SUPPRESSED_VISIBILITY);
    createEAttribute(genOperationEClass, GEN_OPERATION__BODY);

    genParameterEClass = createEClass(GEN_PARAMETER);
    createEReference(genParameterEClass, GEN_PARAMETER__GEN_OPERATION);
    createEReference(genParameterEClass, GEN_PARAMETER__ECORE_PARAMETER);

    genTypedElementEClass = createEClass(GEN_TYPED_ELEMENT);
    createEAttribute(genTypedElementEClass, GEN_TYPED_ELEMENT__DOCUMENTATION);

    genAnnotationEClass = createEClass(GEN_ANNOTATION);
    createEAttribute(genAnnotationEClass, GEN_ANNOTATION__SOURCE);
    createEReference(genAnnotationEClass, GEN_ANNOTATION__DETAILS);
    createEReference(genAnnotationEClass, GEN_ANNOTATION__GEN_BASE);
    createEReference(genAnnotationEClass, GEN_ANNOTATION__REFERENCES);
    createEReference(genAnnotationEClass, GEN_ANNOTATION__CONTENTS);

    genTypeParameterEClass = createEClass(GEN_TYPE_PARAMETER);
    createEReference(genTypeParameterEClass, GEN_TYPE_PARAMETER__ECORE_TYPE_PARAMETER);
    createEAttribute(genTypeParameterEClass, GEN_TYPE_PARAMETER__DOCUMENTATION);

    // Create enums
    genProviderKindEEnum = createEEnum(GEN_PROVIDER_KIND);
    genPropertyKindEEnum = createEEnum(GEN_PROPERTY_KIND);
    genResourceKindEEnum = createEEnum(GEN_RESOURCE_KIND);
    genDelegationKindEEnum = createEEnum(GEN_DELEGATION_KIND);
    genJDKLevelEEnum = createEEnum(GEN_JDK_LEVEL);
    genRuntimeVersionEEnum = createEEnum(GEN_RUNTIME_VERSION);
    genRuntimePlatformEEnum = createEEnum(GEN_RUNTIME_PLATFORM);
    genDecorationEEnum = createEEnum(GEN_DECORATION);
    genEclipsePlatformVersionEEnum = createEEnum(GEN_ECLIPSE_PLATFORM_VERSION);
    genCodeStyleEEnum = createEEnum(GEN_CODE_STYLE);
    genOSGiStyleEEnum = createEEnum(GEN_OS_GI_STYLE);

    // Create data types
    pathEDataType = createEDataType(PATH);
    propertyEditorFactoryEDataType = createEDataType(PROPERTY_EDITOR_FACTORY);
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

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    genModelEClass.getESuperTypes().add(this.getGenBase());
    genPackageEClass.getESuperTypes().add(this.getGenBase());
    genClassEClass.getESuperTypes().add(this.getGenClassifier());
    genFeatureEClass.getESuperTypes().add(this.getGenTypedElement());
    genEnumEClass.getESuperTypes().add(this.getGenDataType());
    genEnumLiteralEClass.getESuperTypes().add(this.getGenBase());
    genClassifierEClass.getESuperTypes().add(this.getGenBase());
    genDataTypeEClass.getESuperTypes().add(this.getGenClassifier());
    genOperationEClass.getESuperTypes().add(this.getGenTypedElement());
    genParameterEClass.getESuperTypes().add(this.getGenTypedElement());
    genTypedElementEClass.getESuperTypes().add(this.getGenBase());
    genAnnotationEClass.getESuperTypes().add(this.getGenBase());
    genTypeParameterEClass.getESuperTypes().add(this.getGenBase());

    // Initialize classes and features; add operations and parameters
    initEClass(genModelEClass, GenModel.class, "GenModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenModel_CopyrightText(), ecorePackage.getEString(), "copyrightText", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ModelDirectory(), this.getPath(), "modelDirectory", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_CreationCommands(), ecorePackage.getEBoolean(), "creationCommands", "true", 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_CreationIcons(), ecorePackage.getEBoolean(), "creationIcons", "true", 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_CreationSubmenus(), ecorePackage.getEBoolean(), "creationSubmenus", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_EditDirectory(), this.getPath(), "editDirectory", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_EditorDirectory(), this.getPath(), "editorDirectory", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ModelPluginID(), ecorePackage.getEString(), "modelPluginID", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_TemplateDirectory(), ecorePackage.getEString(), "templateDirectory", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_RuntimeJar(), ecorePackage.getEBoolean(), "runtimeJar", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ForeignModel(), ecorePackage.getEString(), "foreignModel", null, 0, -1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_DynamicTemplates(), ecorePackage.getEBoolean(), "dynamicTemplates", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_Redirection(), ecorePackage.getEString(), "redirection", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ForceOverwrite(), ecorePackage.getEBoolean(), "forceOverwrite", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_NonExternalizedStringTag(), ecorePackage.getEString(), "nonExternalizedStringTag", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ModelName(), ecorePackage.getEString(), "modelName", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ModelPluginClass(), ecorePackage.getEString(), "modelPluginClass", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_EditPluginClass(), ecorePackage.getEString(), "editPluginClass", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_EditorPluginClass(), ecorePackage.getEString(), "editorPluginClass", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_UpdateClasspath(), ecorePackage.getEBoolean(), "updateClasspath", "true", 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_GenerateSchema(), ecorePackage.getEBoolean(), "generateSchema", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_NonNLSMarkers(), ecorePackage.getEBoolean(), "nonNLSMarkers", "false", 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_StaticPackages(), ecorePackage.getEString(), "staticPackages", null, 0, -1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ModelPluginVariables(), ecorePackage.getEString(), "modelPluginVariables", null, 0, -1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_RootExtendsInterface(), ecorePackage.getEString(), "rootExtendsInterface", "org.eclipse.emf.ecore.EObject", 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_RootExtendsClass(), ecorePackage.getEString(), "rootExtendsClass", "org.eclipse.emf.ecore.impl.EObjectImpl", 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_RootImplementsInterface(), ecorePackage.getEString(), "rootImplementsInterface", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_SuppressEMFTypes(), ecorePackage.getEBoolean(), "suppressEMFTypes", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_SuppressEMFMetaData(), ecorePackage.getEBoolean(), "suppressEMFMetaData", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_SuppressEMFModelTags(), ecorePackage.getEBoolean(), "suppressEMFModelTags", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_SuppressInterfaces(), ecorePackage.getEBoolean(), "suppressInterfaces", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_FeatureMapWrapperInterface(), ecorePackage.getEString(), "featureMapWrapperInterface", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_FeatureMapWrapperInternalInterface(), ecorePackage.getEString(), "featureMapWrapperInternalInterface", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_FeatureMapWrapperClass(), ecorePackage.getEString(), "featureMapWrapperClass", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_RuntimeCompatibility(), ecorePackage.getEBoolean(), "runtimeCompatibility", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_RichClientPlatform(), ecorePackage.getEBoolean(), "richClientPlatform", null, 0, 1, GenModel.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ReflectiveDelegation(), ecorePackage.getEBoolean(), "reflectiveDelegation", null, 0, 1, GenModel.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_CodeFormatting(), ecorePackage.getEBoolean(), "codeFormatting", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_CommentFormatting(), ecorePackage.getEBoolean(), "commentFormatting", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_TestsDirectory(), this.getPath(), "testsDirectory", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_TestSuiteClass(), ecorePackage.getEString(), "testSuiteClass", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_BooleanFlagsField(), ecorePackage.getEString(), "booleanFlagsField", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_BooleanFlagsReservedBits(), ecorePackage.getEInt(), "booleanFlagsReservedBits", "-1", 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ImporterID(), ecorePackage.getEString(), "importerID", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_BundleManifest(), ecorePackage.getEBoolean(), "bundleManifest", "true", 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_FeatureDelegation(), this.getGenDelegationKind(), "featureDelegation", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ContainmentProxies(), ecorePackage.getEBoolean(), "containmentProxies", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_MinimalReflectiveMethods(), ecorePackage.getEBoolean(), "minimalReflectiveMethods", "true", 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_SuppressContainment(), ecorePackage.getEBoolean(), "suppressContainment", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_SuppressNotification(), ecorePackage.getEBoolean(), "suppressNotification", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ArrayAccessors(), ecorePackage.getEBoolean(), "arrayAccessors", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_SuppressUnsettable(), ecorePackage.getEBoolean(), "suppressUnsettable", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_FacadeHelperClass(), ecorePackage.getEString(), "facadeHelperClass", "org.eclipse.emf.codegen.merge.java.facade.ast.ASTFacadeHelper", 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ComplianceLevel(), this.getGenJDKLevel(), "complianceLevel", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_SuppressGenModelAnnotations(), ecorePackage.getEBoolean(), "suppressGenModelAnnotations", "true", 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_CopyrightFields(), ecorePackage.getEBoolean(), "copyrightFields", "true", 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_BinaryCompatibleReflectiveMethods(), ecorePackage.getEBoolean(), "binaryCompatibleReflectiveMethods", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_PublicConstructors(), ecorePackage.getEBoolean(), "publicConstructors", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_TemplatePluginVariables(), ecorePackage.getEString(), "templatePluginVariables", null, 0, -1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ProviderRootExtendsClass(), ecorePackage.getEString(), "providerRootExtendsClass", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_EditPluginID(), ecorePackage.getEString(), "editPluginID", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_EditPluginVariables(), ecorePackage.getEString(), "editPluginVariables", null, 0, -1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_EditorPluginID(), ecorePackage.getEString(), "editorPluginID", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_EditorPluginVariables(), ecorePackage.getEString(), "editorPluginVariables", null, 0, -1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_TestsPluginID(), ecorePackage.getEString(), "testsPluginID", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_TestsPluginVariables(), ecorePackage.getEString(), "testsPluginVariables", null, 0, -1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_OptimizedHasChildren(), ecorePackage.getEBoolean(), "optimizedHasChildren", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_TableProviders(), ecorePackage.getEBoolean(), "tableProviders", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ColorProviders(), ecorePackage.getEBoolean(), "colorProviders", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_FontProviders(), ecorePackage.getEBoolean(), "fontProviders", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_RuntimeVersion(), this.getGenRuntimeVersion(), "runtimeVersion", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_Language(), ecorePackage.getEString(), "language", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_PackedEnums(), ecorePackage.getEBoolean(), "packedEnums", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenModel_GenPackages(), this.getGenPackage(), this.getGenPackage_GenModel(), "genPackages", null, 0, -1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenModel_UsedGenPackages(), this.getGenPackage(), null, "usedGenPackages", null, 0, -1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_InterfaceNamePattern(), ecorePackage.getEString(), "interfaceNamePattern", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ClassNamePattern(), ecorePackage.getEString(), "classNamePattern", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_OperationReflection(), ecorePackage.getEBoolean(), "operationReflection", "false", 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_RichAjaxPlatform(), ecorePackage.getEBoolean(), "richAjaxPlatform", null, 0, 1, GenModel.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_RuntimePlatform(), this.getGenRuntimePlatform(), "runtimePlatform", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ImportOrganizing(), theEcorePackage.getEBoolean(), "importOrganizing", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_PluginKey(), theEcorePackage.getEString(), "pluginKey", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_Decoration(), this.getGenDecoration(), "decoration", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_StyleProviders(), ecorePackage.getEBoolean(), "styleProviders", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_Cleanup(), ecorePackage.getEBoolean(), "cleanup", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_OSGiCompatible(), theEcorePackage.getEBoolean(), "oSGiCompatible", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_EclipsePlatformVersion(), this.getGenEclipsePlatformVersion(), "eclipsePlatformVersion", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ModelDocumentation(), theEcorePackage.getEString(), "modelDocumentation", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_AutoResizeProperties(), ecorePackage.getEBoolean(), "autoResizeProperties", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_AutoExpandProperties(), ecorePackage.getEInt(), "autoExpandProperties", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_FindAction(), ecorePackage.getEBoolean(), "findAction", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_ExpandAllAction(), ecorePackage.getEBoolean(), "expandAllAction", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_CollapseAllAction(), ecorePackage.getEBoolean(), "collapseAllAction", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_RevertAction(), ecorePackage.getEBoolean(), "revertAction", null, 0, 1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_CodeStyle(), this.getGenCodeStyle(), "codeStyle", null, 0, -1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenModel_OSGiStyle(), this.getGenOSGiStyle(), "oSGiStyle", null, 0, -1, GenModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(genPackageEClass, GenPackage.class, "GenPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenPackage_Prefix(), ecorePackage.getEString(), "prefix", null, 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_BasePackage(), ecorePackage.getEString(), "basePackage", null, 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_Resource(), this.getGenResourceKind(), "resource", null, 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_DisposableProviderFactory(), ecorePackage.getEBoolean(), "disposableProviderFactory", null, 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_AdapterFactory(), ecorePackage.getEBoolean(), "adapterFactory", "true", 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_LoadInitialization(), ecorePackage.getEBoolean(), "loadInitialization", null, 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_InterfacePackageSuffix(), ecorePackage.getEString(), "interfacePackageSuffix", "", 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_MetaDataPackageSuffix(), ecorePackage.getEString(), "metaDataPackageSuffix", "", 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_ClassPackageSuffix(), ecorePackage.getEString(), "classPackageSuffix", "impl", 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_UtilityPackageSuffix(), ecorePackage.getEString(), "utilityPackageSuffix", "util", 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_ProviderPackageSuffix(), ecorePackage.getEString(), "providerPackageSuffix", "provider", 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_PresentationPackageSuffix(), ecorePackage.getEString(), "presentationPackageSuffix", "presentation", 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_TestsPackageSuffix(), ecorePackage.getEString(), "testsPackageSuffix", "tests", 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_GenerateExampleClass(), ecorePackage.getEBoolean(), "generateExampleClass", "true", 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_LiteralsInterface(), ecorePackage.getEBoolean(), "literalsInterface", "true", 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_DataTypeConverters(), ecorePackage.getEBoolean(), "dataTypeConverters", null, 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_MultipleEditorPages(), ecorePackage.getEBoolean(), "multipleEditorPages", "true", 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_GenerateModelWizard(), ecorePackage.getEBoolean(), "generateModelWizard", "true", 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_ExtensibleProviderFactory(), ecorePackage.getEBoolean(), "extensibleProviderFactory", null, 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_ChildCreationExtenders(), ecorePackage.getEBoolean(), "childCreationExtenders", null, 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_ContentTypeIdentifier(), ecorePackage.getEString(), "contentTypeIdentifier", null, 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_FileExtensions(), ecorePackage.getEString(), "fileExtensions", null, 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenPackage_EcorePackage(), theEcorePackage.getEPackage(), null, "ecorePackage", null, 1, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenPackage_GenModel(), this.getGenModel(), this.getGenModel_GenPackages(), "genModel", null, 0, 1, GenPackage.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenPackage_GenEnums(), this.getGenEnum(), null, "genEnums", null, 0, -1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenPackage_GenDataTypes(), this.getGenDataType(), null, "genDataTypes", null, 0, -1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenPackage_GenClasses(), this.getGenClass(), null, "genClasses", null, 0, -1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenPackage_NestedGenPackages(), this.getGenPackage(), null, "nestedGenPackages", null, 0, -1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenPackage_GenClassifiers(), this.getGenClassifier(), this.getGenClassifier_GenPackage(), "genClassifiers", null, 0, -1, GenPackage.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_PublicationLocation(), theEcorePackage.getEString(), "publicationLocation", null, 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_Documentation(), theEcorePackage.getEString(), "documentation", null, 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_LoadInitializationFileExtension(), theEcorePackage.getEString(), "loadInitializationFileExtension", "ecore", 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenPackage_FamilyTreeInitialization(), theEcorePackage.getEBoolean(), "familyTreeInitialization", null, 0, 1, GenPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(genClassEClass, GenClass.class, "GenClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenClass_Provider(), this.getGenProviderKind(), "provider", null, 0, 1, GenClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenClass_Image(), ecorePackage.getEBoolean(), "image", "true", 0, 1, GenClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenClass_Dynamic(), ecorePackage.getEBoolean(), "dynamic", null, 0, 1, GenClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenClass_EcoreClass(), theEcorePackage.getEClass(), null, "ecoreClass", null, 1, 1, GenClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenClass_GenFeatures(), this.getGenFeature(), this.getGenFeature_GenClass(), "genFeatures", null, 0, -1, GenClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenClass_GenOperations(), this.getGenOperation(), this.getGenOperation_GenClass(), "genOperations", null, 0, -1, GenClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenClass_LabelFeature(), this.getGenFeature(), null, "labelFeature", null, 0, 1, GenClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(genFeatureEClass, GenFeature.class, "GenFeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenFeature_Property(), this.getGenPropertyKind(), "property", null, 0, 1, GenFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenFeature_Notify(), ecorePackage.getEBoolean(), "notify", "true", 0, 1, GenFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenFeature_Children(), ecorePackage.getEBoolean(), "children", null, 0, 1, GenFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenFeature_CreateChild(), ecorePackage.getEBoolean(), "createChild", null, 0, 1, GenFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenFeature_PropertyCategory(), ecorePackage.getEString(), "propertyCategory", null, 0, 1, GenFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenFeature_PropertyFilterFlags(), ecorePackage.getEString(), "propertyFilterFlags", null, 0, -1, GenFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenFeature_PropertyDescription(), ecorePackage.getEString(), "propertyDescription", null, 0, 1, GenFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenFeature_PropertyMultiLine(), ecorePackage.getEBoolean(), "propertyMultiLine", null, 0, 1, GenFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenFeature_PropertySortChoices(), ecorePackage.getEBoolean(), "propertySortChoices", null, 0, 1, GenFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenFeature_GenClass(), this.getGenClass(), this.getGenClass_GenFeatures(), "genClass", null, 1, 1, GenFeature.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenFeature_EcoreFeature(), theEcorePackage.getEStructuralFeature(), null, "ecoreFeature", null, 1, 1, GenFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenFeature_SuppressedGetVisibility(), theEcorePackage.getEBoolean(), "suppressedGetVisibility", null, 0, 1, GenFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenFeature_SuppressedSetVisibility(), theEcorePackage.getEBoolean(), "suppressedSetVisibility", null, 0, 1, GenFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenFeature_SuppressedIsSetVisibility(), theEcorePackage.getEBoolean(), "suppressedIsSetVisibility", null, 0, 1, GenFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenFeature_SuppressedUnsetVisibility(), theEcorePackage.getEBoolean(), "suppressedUnsetVisibility", null, 0, 1, GenFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenFeature_Get(), ecorePackage.getEString(), "get", null, 0, 1, GenFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenFeature_PropertyEditorFactory(), this.getPropertyEditorFactory(), "propertyEditorFactory", null, 0, 1, GenFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(genBaseEClass, GenBase.class, "GenBase", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getGenBase_GenAnnotations(), this.getGenAnnotation(), this.getGenAnnotation_GenBase(), "genAnnotations", null, 0, -1, GenBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    EOperation op = addEOperation(genBaseEClass, this.getGenAnnotation(), "getGenAnnotation", 0, 1, IS_UNIQUE, IS_ORDERED);
    addEParameter(op, ecorePackage.getEString(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);

    initEClass(genEnumEClass, GenEnum.class, "GenEnum", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenEnum_TypeSafeEnumCompatible(), ecorePackage.getEBoolean(), "typeSafeEnumCompatible", "true", 0, 1, GenEnum.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenEnum_EcoreEnum(), theEcorePackage.getEEnum(), null, "ecoreEnum", null, 1, 1, GenEnum.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenEnum_GenEnumLiterals(), this.getGenEnumLiteral(), this.getGenEnumLiteral_GenEnum(), "genEnumLiterals", null, 0, -1, GenEnum.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(genEnumLiteralEClass, GenEnumLiteral.class, "GenEnumLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getGenEnumLiteral_GenEnum(), this.getGenEnum(), this.getGenEnum_GenEnumLiterals(), "genEnum", null, 1, 1, GenEnumLiteral.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenEnumLiteral_EcoreEnumLiteral(), theEcorePackage.getEEnumLiteral(), null, "ecoreEnumLiteral", null, 1, 1, GenEnumLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenEnumLiteral_Documentation(), theEcorePackage.getEString(), "documentation", null, 0, 1, GenEnumLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(genClassifierEClass, GenClassifier.class, "GenClassifier", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getGenClassifier_GenPackage(), this.getGenPackage(), this.getGenPackage_GenClassifiers(), "genPackage", null, 1, 1, GenClassifier.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenClassifier_GenTypeParameters(), this.getGenTypeParameter(), null, "genTypeParameters", null, 0, -1, GenClassifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenClassifier_Documentation(), theEcorePackage.getEString(), "documentation", null, 0, 1, GenClassifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(genDataTypeEClass, GenDataType.class, "GenDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getGenDataType_EcoreDataType(), theEcorePackage.getEDataType(), null, "ecoreDataType", null, 1, 1, GenDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenDataType_Create(), ecorePackage.getEString(), "create", null, 0, 1, GenDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenDataType_Convert(), ecorePackage.getEString(), "convert", null, 0, 1, GenDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenDataType_PropertyEditorFactory(), this.getPropertyEditorFactory(), "propertyEditorFactory", null, 0, 1, GenDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(genOperationEClass, GenOperation.class, "GenOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getGenOperation_GenClass(), this.getGenClass(), this.getGenClass_GenOperations(), "genClass", null, 1, 1, GenOperation.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenOperation_EcoreOperation(), theEcorePackage.getEOperation(), null, "ecoreOperation", null, 1, 1, GenOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenOperation_GenParameters(), this.getGenParameter(), this.getGenParameter_GenOperation(), "genParameters", null, 0, -1, GenOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenOperation_GenTypeParameters(), this.getGenTypeParameter(), null, "genTypeParameters", null, 0, -1, GenOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenOperation_SuppressedVisibility(), theEcorePackage.getEBoolean(), "suppressedVisibility", null, 0, 1, GenOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenOperation_Body(), ecorePackage.getEString(), "body", null, 0, 1, GenOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(genParameterEClass, GenParameter.class, "GenParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getGenParameter_GenOperation(), this.getGenOperation(), this.getGenOperation_GenParameters(), "genOperation", null, 1, 1, GenParameter.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenParameter_EcoreParameter(), theEcorePackage.getEParameter(), null, "ecoreParameter", null, 1, 1, GenParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(genTypedElementEClass, GenTypedElement.class, "GenTypedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenTypedElement_Documentation(), theEcorePackage.getEString(), "documentation", null, 0, 1, GenTypedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(genAnnotationEClass, GenAnnotation.class, "GenAnnotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenAnnotation_Source(), ecorePackage.getEString(), "source", null, 0, 1, GenAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenAnnotation_Details(), theEcorePackage.getEStringToStringMapEntry(), null, "details", null, 0, -1, GenAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenAnnotation_GenBase(), this.getGenBase(), this.getGenBase_GenAnnotations(), "genBase", null, 0, 1, GenAnnotation.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenAnnotation_References(), theEcorePackage.getEObject(), null, "references", null, 0, -1, GenAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGenAnnotation_Contents(), theEcorePackage.getEObject(), null, "contents", null, 0, -1, GenAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(genTypeParameterEClass, GenTypeParameter.class, "GenTypeParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getGenTypeParameter_EcoreTypeParameter(), theEcorePackage.getETypeParameter(), null, "ecoreTypeParameter", null, 1, 1, GenTypeParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGenTypeParameter_Documentation(), theEcorePackage.getEString(), "documentation", null, 0, 1, GenTypeParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(genProviderKindEEnum, GenProviderKind.class, "GenProviderKind");
    addEEnumLiteral(genProviderKindEEnum, GenProviderKind.SINGLETON_LITERAL);
    addEEnumLiteral(genProviderKindEEnum, GenProviderKind.STATEFUL_LITERAL);
    addEEnumLiteral(genProviderKindEEnum, GenProviderKind.NONE_LITERAL);

    initEEnum(genPropertyKindEEnum, GenPropertyKind.class, "GenPropertyKind");
    addEEnumLiteral(genPropertyKindEEnum, GenPropertyKind.EDITABLE_LITERAL);
    addEEnumLiteral(genPropertyKindEEnum, GenPropertyKind.READONLY_LITERAL);
    addEEnumLiteral(genPropertyKindEEnum, GenPropertyKind.NONE_LITERAL);

    initEEnum(genResourceKindEEnum, GenResourceKind.class, "GenResourceKind");
    addEEnumLiteral(genResourceKindEEnum, GenResourceKind.NONE_LITERAL);
    addEEnumLiteral(genResourceKindEEnum, GenResourceKind.BASIC_LITERAL);
    addEEnumLiteral(genResourceKindEEnum, GenResourceKind.XMI_LITERAL);
    addEEnumLiteral(genResourceKindEEnum, GenResourceKind.XML_LITERAL);

    initEEnum(genDelegationKindEEnum, GenDelegationKind.class, "GenDelegationKind");
    addEEnumLiteral(genDelegationKindEEnum, GenDelegationKind.NONE_LITERAL);
    addEEnumLiteral(genDelegationKindEEnum, GenDelegationKind.REFLECTIVE_LITERAL);
    addEEnumLiteral(genDelegationKindEEnum, GenDelegationKind.VIRTUAL_LITERAL);
    addEEnumLiteral(genDelegationKindEEnum, GenDelegationKind.DYNAMIC_LITERAL);

    initEEnum(genJDKLevelEEnum, GenJDKLevel.class, "GenJDKLevel");
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK14_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK50_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK60_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK70_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK80_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK90_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK100_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK110_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK120_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK130_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK140_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK150_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK160_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK170_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK180_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK190_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK200_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK210_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK220_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK230_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK240_LITERAL);
    addEEnumLiteral(genJDKLevelEEnum, GenJDKLevel.JDK250_LITERAL);

    initEEnum(genRuntimeVersionEEnum, GenRuntimeVersion.class, "GenRuntimeVersion");
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF22);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF23);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF24);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF25);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF26);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF27);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF28);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF29);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF210);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF211);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF212);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF213);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF214);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF215);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF216);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF217);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF218);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF219);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF220);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF221);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF222);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF223);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF224);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF225);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF226);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF227);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF228);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF229);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF230);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF231);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF232);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF233);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF234);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF235);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF236);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF237);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF238);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF239);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF240);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF241);
    addEEnumLiteral(genRuntimeVersionEEnum, GenRuntimeVersion.EMF242);

    initEEnum(genRuntimePlatformEEnum, GenRuntimePlatform.class, "GenRuntimePlatform");
    addEEnumLiteral(genRuntimePlatformEEnum, GenRuntimePlatform.IDE);
    addEEnumLiteral(genRuntimePlatformEEnum, GenRuntimePlatform.RCP);
    addEEnumLiteral(genRuntimePlatformEEnum, GenRuntimePlatform.RAP);
    addEEnumLiteral(genRuntimePlatformEEnum, GenRuntimePlatform.GWT);

    initEEnum(genDecorationEEnum, GenDecoration.class, "GenDecoration");
    addEEnumLiteral(genDecorationEEnum, GenDecoration.NONE);
    addEEnumLiteral(genDecorationEEnum, GenDecoration.MANUAL);
    addEEnumLiteral(genDecorationEEnum, GenDecoration.LIVE);

    initEEnum(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.class, "GenEclipsePlatformVersion");
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.JUNO);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.KEPLER);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.LUNA);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.MARS);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.NEON);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.OXYGEN);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.PHOTON);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2018_09);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2018_12);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2019_03);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2019_06);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2019_09);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2019_12);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2020_03);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2020_06);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2020_09);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2020_12);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2021_03);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2021_06);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2021_09);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2021_12);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2022_03);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2022_06);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2022_09);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2022_12);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2023_03);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2023_06);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2023_09);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2023_12);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2024_03);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2024_06);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2024_09);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2024_12);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2025_03);
    addEEnumLiteral(genEclipsePlatformVersionEEnum, GenEclipsePlatformVersion.ECLIPSE_2025_06);

    initEEnum(genCodeStyleEEnum, GenCodeStyle.class, "GenCodeStyle");
    addEEnumLiteral(genCodeStyleEEnum, GenCodeStyle.UNNECESSARY_ELSE);
    addEEnumLiteral(genCodeStyleEEnum, GenCodeStyle.SWITCH_MISSING_DEFAULT_CASE);
    addEEnumLiteral(genCodeStyleEEnum, GenCodeStyle.UNNECESSARY_ASSIGNMENT_BEFORE_RETURN);
    addEEnumLiteral(genCodeStyleEEnum, GenCodeStyle.UNNECESSARY_DEPRECATED_METHOD);

    initEEnum(genOSGiStyleEEnum, GenOSGiStyle.class, "GenOSGiStyle");
    addEEnumLiteral(genOSGiStyleEEnum, GenOSGiStyle.PROVIDE_CAPABILITY_GENERATED_PACKAGE);

    // Initialize data types
    initEDataType(pathEDataType, String.class, "Path", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(propertyEditorFactoryEDataType, String.class, "PropertyEditorFactory", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http://www.eclipse.org/emf/2002/Ecore
    createEcoreAnnotations();
  }

  /**
   * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void createEcoreAnnotations()
  {
    String source = "http://www.eclipse.org/emf/2002/Ecore";
    addAnnotation
      (genEnumEClass,
       source,
       new String[]
       {
         "constraints", "NoEcoreDataType"
       });
    addAnnotation
      (pathEDataType,
       source,
       new String[]
       {
         "constraints", "WellFormedPath"
       });
  }

} //GenModelPackageImpl
