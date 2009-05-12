/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: JavaPackageImpl.java,v 1.9 2009/05/12 15:54:42 davidms Exp $
 */
package org.eclipse.emf.java.impl;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.java.JClass;
import org.eclipse.emf.java.JCompilationUnit;
import org.eclipse.emf.java.JField;
import org.eclipse.emf.java.JInitializer;
import org.eclipse.emf.java.JMember;
import org.eclipse.emf.java.JMethod;
import org.eclipse.emf.java.JModelElement;
import org.eclipse.emf.java.JPackage;
import org.eclipse.emf.java.JParameter;
import org.eclipse.emf.java.JVisibility;
import org.eclipse.emf.java.JavaFactory;
import org.eclipse.emf.java.JavaPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JavaPackageImpl extends EPackageImpl implements JavaPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jClassEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jCompilationUnitEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jFieldEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jInitializerEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jMemberEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jMethodEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jModelElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jParameterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jPackageEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum jVisibilityEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType jNodeEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType javaClassEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType javaMethodEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType javaConstructorEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType javaFieldEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType javaPackageEDataType = null;

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
   * @see org.eclipse.emf.java.JavaPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private JavaPackageImpl()
  {
    super(eNS_URI, JavaFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link JavaPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static JavaPackage init()
  {
    if (isInited) return (JavaPackage)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI);

    // Obtain or create and register package
    JavaPackageImpl theJavaPackage = (JavaPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof JavaPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new JavaPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theJavaPackage.createPackageContents();

    // Initialize created meta-data
    theJavaPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theJavaPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(JavaPackage.eNS_URI, theJavaPackage);
    return theJavaPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJClass()
  {
    return jClassEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJClass_Abstract()
  {
    return (EAttribute)jClassEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJClass_Final()
  {
    return (EAttribute)jClassEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJClass_Interface()
  {
    return (EAttribute)jClassEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJClass_Throwable()
  {
    return (EAttribute)jClassEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJClass_JavaClass()
  {
    return (EAttribute)jClassEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJClass_Fields()
  {
    return (EReference)jClassEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJClass_Methods()
  {
    return (EReference)jClassEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJClass_SuperTypes()
  {
    return (EReference)jClassEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJClass_AllSuperTypes()
  {
    return (EReference)jClassEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJClass_Members()
  {
    return (EReference)jClassEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJClass_ComponentType()
  {
    return (EReference)jClassEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJClass_ArrayType()
  {
    return (EReference)jClassEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJClass_Unit()
  {
    return (EReference)jClassEClass.getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJClass_AllMethods()
  {
    return (EReference)jClassEClass.getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJClass_AllFields()
  {
    return (EReference)jClassEClass.getEStructuralFeatures().get(14);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJClass_Package()
  {
    return (EReference)jClassEClass.getEStructuralFeatures().get(15);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJClass_Types()
  {
    return (EReference)jClassEClass.getEStructuralFeatures().get(16);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJClass_AllTypes()
  {
    return (EReference)jClassEClass.getEStructuralFeatures().get(17);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJCompilationUnit()
  {
    return jCompilationUnitEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJCompilationUnit_Package()
  {
    return (EReference)jCompilationUnitEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJCompilationUnit_Imports()
  {
    return (EAttribute)jCompilationUnitEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJCompilationUnit_Comment()
  {
    return (EAttribute)jCompilationUnitEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJCompilationUnit_Types()
  {
    return (EReference)jCompilationUnitEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJCompilationUnit_ImportedPackages()
  {
    return (EReference)jCompilationUnitEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJCompilationUnit_ImportedTypes()
  {
    return (EReference)jCompilationUnitEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJField()
  {
    return jFieldEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJField_Final()
  {
    return (EAttribute)jFieldEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJField_Transient()
  {
    return (EAttribute)jFieldEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJField_Volatile()
  {
    return (EAttribute)jFieldEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJField_JavaField()
  {
    return (EAttribute)jFieldEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJField_Initializer()
  {
    return (EAttribute)jFieldEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJField_Type()
  {
    return (EReference)jFieldEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJInitializer()
  {
    return jInitializerEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJInitializer_Body()
  {
    return (EAttribute)jInitializerEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJMember()
  {
    return jMemberEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJMember_Static()
  {
    return (EAttribute)jMemberEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJMember_Visibility()
  {
    return (EAttribute)jMemberEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJMember_Comment()
  {
    return (EAttribute)jMemberEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJMember_ContainingType()
  {
    return (EReference)jMemberEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJMethod()
  {
    return jMethodEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJMethod_Abstract()
  {
    return (EAttribute)jMethodEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJMethod_Final()
  {
    return (EAttribute)jMethodEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJMethod_Native()
  {
    return (EAttribute)jMethodEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJMethod_Synchronized()
  {
    return (EAttribute)jMethodEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJMethod_JavaMethod()
  {
    return (EAttribute)jMethodEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJMethod_Constructor()
  {
    return (EAttribute)jMethodEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJMethod_JavaConstructor()
  {
    return (EAttribute)jMethodEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJMethod_Body()
  {
    return (EAttribute)jMethodEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJMethod_Parameters()
  {
    return (EReference)jMethodEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJMethod_Exceptions()
  {
    return (EReference)jMethodEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJMethod_ReturnType()
  {
    return (EReference)jMethodEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJModelElement()
  {
    return jModelElementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJModelElement_Name()
  {
    return (EAttribute)jModelElementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJModelElement_JNode()
  {
    return (EAttribute)jModelElementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJParameter()
  {
    return jParameterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJParameter_Final()
  {
    return (EAttribute)jParameterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJParameter_Method()
  {
    return (EReference)jParameterEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJParameter_Type()
  {
    return (EReference)jParameterEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJPackage()
  {
    return jPackageEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJPackage_JavaPackage()
  {
    return (EAttribute)jPackageEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJPackage_Types()
  {
    return (EReference)jPackageEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getJVisibility()
  {
    return jVisibilityEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getJNode()
  {
    return jNodeEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getJavaClass()
  {
    return javaClassEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getJavaMethod()
  {
    return javaMethodEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getJavaConstructor()
  {
    return javaConstructorEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getJavaField()
  {
    return javaFieldEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getJavaPackage()
  {
    return javaPackageEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JavaFactory getJavaFactory()
  {
    return (JavaFactory)getEFactoryInstance();
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
    jClassEClass = createEClass(JCLASS);
    createEAttribute(jClassEClass, JCLASS__ABSTRACT);
    createEAttribute(jClassEClass, JCLASS__FINAL);
    createEAttribute(jClassEClass, JCLASS__INTERFACE);
    createEAttribute(jClassEClass, JCLASS__THROWABLE);
    createEAttribute(jClassEClass, JCLASS__JAVA_CLASS);
    createEReference(jClassEClass, JCLASS__FIELDS);
    createEReference(jClassEClass, JCLASS__METHODS);
    createEReference(jClassEClass, JCLASS__SUPER_TYPES);
    createEReference(jClassEClass, JCLASS__ALL_SUPER_TYPES);
    createEReference(jClassEClass, JCLASS__MEMBERS);
    createEReference(jClassEClass, JCLASS__COMPONENT_TYPE);
    createEReference(jClassEClass, JCLASS__ARRAY_TYPE);
    createEReference(jClassEClass, JCLASS__UNIT);
    createEReference(jClassEClass, JCLASS__ALL_METHODS);
    createEReference(jClassEClass, JCLASS__ALL_FIELDS);
    createEReference(jClassEClass, JCLASS__PACKAGE);
    createEReference(jClassEClass, JCLASS__TYPES);
    createEReference(jClassEClass, JCLASS__ALL_TYPES);

    jCompilationUnitEClass = createEClass(JCOMPILATION_UNIT);
    createEAttribute(jCompilationUnitEClass, JCOMPILATION_UNIT__IMPORTS);
    createEAttribute(jCompilationUnitEClass, JCOMPILATION_UNIT__COMMENT);
    createEReference(jCompilationUnitEClass, JCOMPILATION_UNIT__TYPES);
    createEReference(jCompilationUnitEClass, JCOMPILATION_UNIT__IMPORTED_PACKAGES);
    createEReference(jCompilationUnitEClass, JCOMPILATION_UNIT__IMPORTED_TYPES);
    createEReference(jCompilationUnitEClass, JCOMPILATION_UNIT__PACKAGE);

    jFieldEClass = createEClass(JFIELD);
    createEAttribute(jFieldEClass, JFIELD__FINAL);
    createEAttribute(jFieldEClass, JFIELD__TRANSIENT);
    createEAttribute(jFieldEClass, JFIELD__VOLATILE);
    createEAttribute(jFieldEClass, JFIELD__JAVA_FIELD);
    createEAttribute(jFieldEClass, JFIELD__INITIALIZER);
    createEReference(jFieldEClass, JFIELD__TYPE);

    jInitializerEClass = createEClass(JINITIALIZER);
    createEAttribute(jInitializerEClass, JINITIALIZER__BODY);

    jMemberEClass = createEClass(JMEMBER);
    createEAttribute(jMemberEClass, JMEMBER__STATIC);
    createEAttribute(jMemberEClass, JMEMBER__VISIBILITY);
    createEAttribute(jMemberEClass, JMEMBER__COMMENT);
    createEReference(jMemberEClass, JMEMBER__CONTAINING_TYPE);

    jMethodEClass = createEClass(JMETHOD);
    createEAttribute(jMethodEClass, JMETHOD__ABSTRACT);
    createEAttribute(jMethodEClass, JMETHOD__FINAL);
    createEAttribute(jMethodEClass, JMETHOD__NATIVE);
    createEAttribute(jMethodEClass, JMETHOD__SYNCHRONIZED);
    createEAttribute(jMethodEClass, JMETHOD__JAVA_METHOD);
    createEAttribute(jMethodEClass, JMETHOD__CONSTRUCTOR);
    createEAttribute(jMethodEClass, JMETHOD__JAVA_CONSTRUCTOR);
    createEAttribute(jMethodEClass, JMETHOD__BODY);
    createEReference(jMethodEClass, JMETHOD__PARAMETERS);
    createEReference(jMethodEClass, JMETHOD__EXCEPTIONS);
    createEReference(jMethodEClass, JMETHOD__RETURN_TYPE);

    jModelElementEClass = createEClass(JMODEL_ELEMENT);
    createEAttribute(jModelElementEClass, JMODEL_ELEMENT__NAME);
    createEAttribute(jModelElementEClass, JMODEL_ELEMENT__JNODE);

    jPackageEClass = createEClass(JPACKAGE);
    createEAttribute(jPackageEClass, JPACKAGE__JAVA_PACKAGE);
    createEReference(jPackageEClass, JPACKAGE__TYPES);

    jParameterEClass = createEClass(JPARAMETER);
    createEAttribute(jParameterEClass, JPARAMETER__FINAL);
    createEReference(jParameterEClass, JPARAMETER__METHOD);
    createEReference(jParameterEClass, JPARAMETER__TYPE);

    // Create enums
    jVisibilityEEnum = createEEnum(JVISIBILITY);

    // Create data types
    javaClassEDataType = createEDataType(JAVA_CLASS);
    javaConstructorEDataType = createEDataType(JAVA_CONSTRUCTOR);
    javaFieldEDataType = createEDataType(JAVA_FIELD);
    javaMethodEDataType = createEDataType(JAVA_METHOD);
    javaPackageEDataType = createEDataType(JAVA_PACKAGE);
    jNodeEDataType = createEDataType(JNODE);
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

    // Create type parameters
    addETypeParameter(javaClassEDataType, "T");
    addETypeParameter(javaConstructorEDataType, "T");

    // Set bounds for type parameters

    // Add supertypes to classes
    jClassEClass.getESuperTypes().add(this.getJMember());
    jCompilationUnitEClass.getESuperTypes().add(this.getJModelElement());
    jFieldEClass.getESuperTypes().add(this.getJMember());
    jInitializerEClass.getESuperTypes().add(this.getJMember());
    jMemberEClass.getESuperTypes().add(this.getJModelElement());
    jMethodEClass.getESuperTypes().add(this.getJMember());
    jPackageEClass.getESuperTypes().add(this.getJModelElement());
    jParameterEClass.getESuperTypes().add(this.getJModelElement());

    // Initialize classes and features; add operations and parameters
    initEClass(jClassEClass, JClass.class, "JClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getJClass_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 0, 1, JClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJClass_Final(), ecorePackage.getEBoolean(), "final", null, 0, 1, JClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJClass_Interface(), ecorePackage.getEBoolean(), "interface", null, 0, 1, JClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJClass_Throwable(), ecorePackage.getEBoolean(), "throwable", null, 0, 1, JClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    EGenericType g1 = createEGenericType(this.getJavaClass());
    EGenericType g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEAttribute(getJClass_JavaClass(), g1, "javaClass", null, 0, 1, JClass.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJClass_Fields(), this.getJField(), null, "fields", null, 0, -1, JClass.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJClass_Methods(), this.getJMethod(), null, "methods", null, 0, -1, JClass.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJClass_SuperTypes(), this.getJClass(), null, "superTypes", null, 0, -1, JClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJClass_AllSuperTypes(), this.getJClass(), null, "allSuperTypes", null, 0, -1, JClass.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJClass_Members(), this.getJMember(), this.getJMember_ContainingType(), "members", null, 0, -1, JClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJClass_ComponentType(), this.getJClass(), this.getJClass_ArrayType(), "componentType", null, 0, 1, JClass.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJClass_ArrayType(), this.getJClass(), this.getJClass_ComponentType(), "arrayType", null, 0, 1, JClass.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJClass_Unit(), this.getJCompilationUnit(), this.getJCompilationUnit_Types(), "unit", null, 0, 1, JClass.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJClass_AllMethods(), this.getJMethod(), null, "allMethods", null, 0, -1, JClass.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJClass_AllFields(), this.getJField(), null, "allFields", null, 0, -1, JClass.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJClass_Package(), this.getJPackage(), this.getJPackage_Types(), "package", null, 0, 1, JClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJClass_Types(), this.getJClass(), null, "types", null, 0, -1, JClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJClass_AllTypes(), this.getJClass(), null, "allTypes", null, 0, -1, JClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(jCompilationUnitEClass, JCompilationUnit.class, "JCompilationUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getJCompilationUnit_Imports(), ecorePackage.getEString(), "imports", null, 0, -1, JCompilationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJCompilationUnit_Comment(), ecorePackage.getEString(), "comment", null, 0, 1, JCompilationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJCompilationUnit_Types(), this.getJClass(), this.getJClass_Unit(), "types", null, 0, -1, JCompilationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJCompilationUnit_ImportedPackages(), this.getJPackage(), null, "importedPackages", null, 0, -1, JCompilationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJCompilationUnit_ImportedTypes(), this.getJClass(), null, "importedTypes", null, 0, -1, JCompilationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJCompilationUnit_Package(), this.getJPackage(), null, "package", null, 0, 1, JCompilationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(jFieldEClass, JField.class, "JField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getJField_Final(), ecorePackage.getEBoolean(), "final", null, 0, 1, JField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJField_Transient(), ecorePackage.getEBoolean(), "transient", null, 0, 1, JField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJField_Volatile(), ecorePackage.getEBoolean(), "volatile", null, 0, 1, JField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJField_JavaField(), this.getJavaField(), "javaField", null, 0, 1, JField.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJField_Initializer(), ecorePackage.getEString(), "initializer", null, 0, 1, JField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJField_Type(), this.getJClass(), null, "type", null, 0, 1, JField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(jInitializerEClass, JInitializer.class, "JInitializer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getJInitializer_Body(), ecorePackage.getEString(), "body", null, 0, 1, JInitializer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(jMemberEClass, JMember.class, "JMember", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getJMember_Static(), ecorePackage.getEBoolean(), "static", null, 0, 1, JMember.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJMember_Visibility(), this.getJVisibility(), "visibility", null, 0, 1, JMember.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJMember_Comment(), ecorePackage.getEString(), "comment", null, 0, 1, JMember.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJMember_ContainingType(), this.getJClass(), this.getJClass_Members(), "containingType", null, 0, 1, JMember.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(jMethodEClass, JMethod.class, "JMethod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getJMethod_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 0, 1, JMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJMethod_Final(), ecorePackage.getEBoolean(), "final", null, 0, 1, JMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJMethod_Native(), ecorePackage.getEBoolean(), "native", null, 0, 1, JMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJMethod_Synchronized(), ecorePackage.getEBoolean(), "synchronized", null, 0, 1, JMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJMethod_JavaMethod(), this.getJavaMethod(), "javaMethod", null, 0, 1, JMethod.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJMethod_Constructor(), ecorePackage.getEBoolean(), "constructor", null, 0, 1, JMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getJavaConstructor());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEAttribute(getJMethod_JavaConstructor(), g1, "javaConstructor", null, 0, 1, JMethod.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJMethod_Body(), ecorePackage.getEString(), "body", null, 0, 1, JMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJMethod_Parameters(), this.getJParameter(), this.getJParameter_Method(), "parameters", null, 0, -1, JMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJMethod_Exceptions(), this.getJClass(), null, "exceptions", null, 0, -1, JMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJMethod_ReturnType(), this.getJClass(), null, "returnType", null, 0, 1, JMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(jModelElementEClass, JModelElement.class, "JModelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getJModelElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, JModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJModelElement_JNode(), this.getJNode(), "jNode", null, 0, 1, JModelElement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(jPackageEClass, JPackage.class, "JPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getJPackage_JavaPackage(), this.getJavaPackage(), "javaPackage", null, 0, 1, JPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJPackage_Types(), this.getJClass(), this.getJClass_Package(), "types", null, 0, -1, JPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(jParameterEClass, JParameter.class, "JParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getJParameter_Final(), ecorePackage.getEBoolean(), "final", null, 0, 1, JParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJParameter_Method(), this.getJMethod(), this.getJMethod_Parameters(), "method", null, 0, 1, JParameter.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJParameter_Type(), this.getJClass(), null, "type", null, 0, 1, JParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(jVisibilityEEnum, JVisibility.class, "JVisibility");
    addEEnumLiteral(jVisibilityEEnum, JVisibility.PUBLIC_LITERAL);
    addEEnumLiteral(jVisibilityEEnum, JVisibility.PROTECTED_LITERAL);
    addEEnumLiteral(jVisibilityEEnum, JVisibility.PRIVATE_LITERAL);
    addEEnumLiteral(jVisibilityEEnum, JVisibility.PACKAGE_LITERAL);

    // Initialize data types
    initEDataType(javaClassEDataType, Class.class, "JavaClass", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(javaConstructorEDataType, Constructor.class, "JavaConstructor", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(javaFieldEDataType, Field.class, "JavaField", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(javaMethodEDataType, Method.class, "JavaMethod", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(javaPackageEDataType, Package.class, "JavaPackage", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(jNodeEDataType, Object.class, "JNode", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

} //JavaPackageImpl
