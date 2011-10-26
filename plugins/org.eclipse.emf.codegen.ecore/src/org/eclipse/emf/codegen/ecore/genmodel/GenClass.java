/**
 * <copyright> 
 *
 * Copyright (c) 2002-2009 IBM Corporation and others.
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
 * $Id: GenClass.java,v 1.37 2011/10/26 11:30:36 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getProvider <em>Provider</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#isImage <em>Image</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#isDynamic <em>Dynamic</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getEcoreClass <em>Ecore Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getGenFeatures <em>Gen Features</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getGenOperations <em>Gen Operations</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getLabelFeature <em>Label Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenClass()
 * @model
 * @generated
 */
public interface GenClass extends GenClassifier
{
  /**
   * Returns the value of the '<em><b>Provider</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.emf.codegen.ecore.genmodel.GenProviderKind}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Provider</em>' attribute.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenProviderKind
   * @see #setProvider(GenProviderKind)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenClass_Provider()
   * @model
   * @generated
   */
  GenProviderKind getProvider();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getProvider <em>Provider</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Provider</em>' attribute.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenProviderKind
   * @see #getProvider()
   * @generated
   */
  void setProvider(GenProviderKind value);

  /**
   * Returns the value of the '<em><b>Image</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Image</em>' attribute.
   * @see #setImage(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenClass_Image()
   * @model default="true"
   * @generated
   */
  boolean isImage();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#isImage <em>Image</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Image</em>' attribute.
   * @see #isImage()
   * @generated
   */
  void setImage(boolean value);

  /**
   * Returns the value of the '<em><b>Dynamic</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dynamic</em>' attribute.
   * @see #setDynamic(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenClass_Dynamic()
   * @model
   * @generated
   */
  boolean isDynamic();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#isDynamic <em>Dynamic</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Dynamic</em>' attribute.
   * @see #isDynamic()
   * @generated
   */
  void setDynamic(boolean value);

  /**
   * Returns the value of the '<em><b>Ecore Class</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ecore Class</em>' reference.
   * @see #setEcoreClass(EClass)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenClass_EcoreClass()
   * @model required="true"
   * @generated
   */
  EClass getEcoreClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getEcoreClass <em>Ecore Class</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ecore Class</em>' reference.
   * @see #getEcoreClass()
   * @generated
   */
  void setEcoreClass(EClass value);

  /**
   * Returns the value of the '<em><b>Gen Features</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getGenClass <em>Gen Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Features</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenClass_GenFeatures()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getGenClass
   * @model opposite="genClass" containment="true"
   * @generated
   */
  EList<GenFeature> getGenFeatures();

  /**
   * Returns the value of the '<em><b>Gen Operations</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenOperation}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getGenClass <em>Gen Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Operations</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenClass_GenOperations()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getGenClass
   * @model opposite="genClass" containment="true"
   * @generated
   */
  EList<GenOperation> getGenOperations();

  /**
   * Returns the value of the '<em><b>Label Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Label Feature</em>' reference.
   * @see #setLabelFeature(GenFeature)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenClass_LabelFeature()
   * @model
   * @generated
   */
  GenFeature getLabelFeature();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getLabelFeature <em>Label Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Label Feature</em>' reference.
   * @see #getLabelFeature()
   * @generated
   */
  void setLabelFeature(GenFeature value);

  /**
   * This should only be called when there is actually an interface being
   * generated (i.e. when, for certain, this is not an external interface).
   * Otherwise, getImportedInterfaceName() should be used.
   */
  String getInterfaceName();

  String getQualifiedInterfaceName();
  String getImportedInterfaceName();
  /**
   * @since 2.3
   */
  String getRawImportedInterfaceName();

  String getClassName();
  String getQualifiedClassName();
  String getImportedClassName();

  /**
   * This returns the name uncapitalized. Callers that plan to use this name
   * without appending a suffix should call getSafeUncapInterfaceName() to
   * make sure it's not a reserved word.
   */  
  String getUncapName();

  List<GenClass> getBaseGenClasses();
  List<GenClass> getAllBaseGenClasses();

  /**
   * Like getAllBaseGenClasses(), this returns the GenClasses for all of the
   * supertypes, but the result is ordered by a breadth-first search, making
   * it appropriate for use in the switch class.
   */
  List<GenClass> getSwitchGenClasses();

  /**
   * This returns the GenClass for the first immediate supertype.
   */
  GenClass getBaseGenClass();

  /**
   * This walks up the chain of GenClasses defined by getBaseGenClass() and
   * returns the first that does not represent an abstract class or an
   * interface; that is, the instantiable class that the implementation
   * class should extended.
   */
  GenClass getClassExtendsGenClass();

  String getClassExtends();
  String getClassImplements();
  String getInterfaceExtends();
  boolean needsRootExtendsInterfaceExtendsTag();
  
  /**
   * @since 2.5
   */
  void addClassPsuedoImports();
  
  /**
   * @since 2.3
   */
  boolean hasGenericSuperTypes();

  /**
   * @since 2.3
   */
  String getTypeParameters();

  /**
   * @since 2.3
   */
  String getInterfaceTypeArguments();

  /**
   * @since 2.3
   */
  String getInterfaceWildTypeArguments();

  /**
   * @since 2.3
   */
  String getClassTypeArguments();

  List<GenFeature> getAllGenFeatures();
  List<GenFeature> getInheritedGenFeatures();
  List<GenOperation> getAllGenOperations();

  /**
   * @since 2.6
   */
  List<GenOperation> getAllGenOperations(boolean excludeOverrides);

  String getFeatureID(GenFeature genFeature);
  String getQualifiedFeatureID(GenFeature genFeature);
  String getOperationID(GenOperation genOperation);
  String getFeatureValue(GenFeature genFeature);
  String getLocalFeatureIndex(GenFeature genFeature);
  String getFlagsField(GenFeature genFeature);
  int getFlagIndex(GenFeature genFeature);

  /**
   * @since 2.6
   */
  String getUniqueName(GenOperation genOperation);

  /**
   * @since 2.6
   */
  String getOperationID(GenOperation genOperation, boolean diagnosticCode);

  /**
   * @since 2.6
   */
  String getQualifiedOperationID(GenOperation genOperation);

  /**
   * @since 2.6
   */
  String getOperationValue(GenOperation genOperation);

  /**
   * @since 2.6
   */
  String getLocalOperationIndex(GenOperation genOperation);

  /**
   * @since 2.4
   */
  int getFlagSize(GenFeature genFeature);

  /**
   * @since 2.4
   */
  String getFlagMask(GenFeature genFeature);
  String getESetFlagsField(GenFeature genFeature);
  int getESetFlagIndex(GenFeature genFeature);

  String getFeatureCountID();
  String getQualifiedFeatureCountID();
  String getFeatureCountValue();
  int getFeatureCount();

  /**
   * @since 2.6
   */
  String getOperationCountID();

  /**
   * @since 2.6
   */
  String getQualifiedOperationCountID();

  /**
   * @since 2.6
   */
  String getOperationCountValue();

  /**
   * @since 2.6
   */
  int getOperationCount();

  /**
   * @since 2.6
   */
  GenOperation getOverrideGenOperation(GenOperation genOperation);

  /**
   * @since 2.6
   */
  List<GenOperation> getOverrideGenOperations(List<GenOperation> baseGenOperations, List<GenOperation> derivedGenOperations);

  boolean isEObject();
  boolean isEObjectExtension();
  String getCastFromEObject();
  boolean isAbstract();
  String getAbstractFlag();
  boolean isInterface();
  String getInterfaceFlag();

  /**
   * This indicates that there is already an existing interface for this
   * class or interface, so none will be generated.  Any other generated
   * that wishes to refer to its interface should use the value from 
   * getQualifiedInterfaceName() or getImportedInterfaceName().
   */
  boolean isExternalInterface();

  boolean isMapEntry();
  GenFeature getMapEntryKeyFeature();
  GenFeature getMapEntryValueFeature();

  List<GenClass> getImplementedGenClasses();
  List<GenFeature> getImplementedGenFeatures();
  List<GenOperation> getImplementedGenOperations();
  
  /**
   * @since 2.4
   */
  boolean hasImplementedToStringGenOperation();

  GenModel getImplementingGenModel(GenFeature genFeature);

  // Returns whether this class implements any of the given features.
  public boolean implementsAny(Collection<GenFeature> genFeatures);

  List<GenClass> getExtendedGenClasses();
  List<GenFeature> getExtendedGenFeatures();
  List<GenOperation> getExtendedGenOperations();

  List<GenFeature> getDeclaredFieldGenFeatures();

  List<GenFeature> getDeclaredGenFeatures();
  List<GenOperation> getDeclaredGenOperations();

  List<GenFeature> getFlagGenFeatures();
  /**
   * @deprecated use {@link #getFlagGenFeaturesWithDefault()}
   */
  @Deprecated
  List<GenFeature> getFlagGenFeatures(String staticDefaultValue);
  /**
   * @since 2.4
   */
  List<GenFeature> getFlagGenFeaturesWithDefault();

  List<GenFeature> getEGetGenFeatures();
  List<GenFeature> getEIsSetGenFeatures();
  List<GenFeature> getESetGenFeatures();
  List<GenFeature> getEUnsetGenFeatures();
  List<GenFeature> getEInverseAddGenFeatures();
  List<GenFeature> getEInverseRemoveGenFeatures();
  List<GenFeature> getEBasicRemoveFromContainerGenFeatures();
  List<GenFeature> getToStringGenFeatures();

  List<GenClass> getMixinGenClasses();
  List<GenFeature> getMixinGenFeatures();
  List<GenOperation> getMixinGenOperations();

  void initialize(EClass eClass);
  //
  // EMFEdit generation
  //

  String getProviderClassName();
  String getQualifiedProviderClassName();
  String getImportedProviderClassName();
  boolean isProviderSingleton();

  String getItemIconFileName();
  /**
   * @since 2.4
   */
  String getCreateChildIconFileName(GenModel genModel, GenFeature feature, GenClass childClass);
  String getCreateChildIconFileName(GenFeature feature, GenClass childClass);
  /**
   * @since 2.7
   */
  String getItemIconAccessorName();

  /**
   * @since 2.4
   */
  GenClass getProviderExtendsGenClass();
  String getProviderBaseClassName();
  List<GenClass> getProviderImplementedGenClasses();

  List<GenFeature> getLabelFeatureCandidates();
  List<GenFeature> getPropertyFeatures();

  List<GenFeature> getNotifyFeatures();
  List<GenFeature> getLabelNotifyFeatures();
  List<GenFeature> getContentNotifyFeatures();
  List<GenFeature> getLabelAndContentNotifyFeatures();

  List<GenFeature> getChildrenFeatures();
  List<GenFeature> getAllChildrenFeatures();

  List<GenFeature> getCreateChildFeatures();
  List<GenFeature> getAllCreateChildFeatures();
  /**
   * @since 2.4
   */
  List<GenFeature> getCreateChildFeaturesIncludingDelegation();
  List<GenFeature> getAllCreateChildFeaturesIncludingDelegation();
  List<GenFeature> getCrossPackageCreateChildFeatures();
  List<GenFeature> getSharedClassCreateChildFeatures();
  boolean hasFeatureMapCreateChildFeatures();

  /**
   * @since 2.4
   */
  public final class ChildCreationData
  {
    public final GenFeature createFeature;
    public final GenFeature delegatedFeature;
    public final GenClassifier createClassifier;
    
    public ChildCreationData(GenFeature createFeature, GenFeature delegateFeature,  GenClassifier createClassifier)
    {
      this.createFeature = createFeature;
      this.delegatedFeature = delegateFeature;
      this.createClassifier = createClassifier;
    }

    @Override
    public boolean equals(Object object)
    {
      if (object instanceof ChildCreationData)
      {
        ChildCreationData that = (ChildCreationData)object;
        return 
          createFeature == that.createFeature &&
          delegatedFeature == that.delegatedFeature &&
          createClassifier == that.createClassifier;
      }
      else
      {
        return false;
      }
    }

    @Override
    public int hashCode()
    {
      return 
        (createFeature == null ? 0 : createFeature.hashCode()) ^
          (delegatedFeature == null ? 0 : delegatedFeature.hashCode()) ^
          (createClassifier == null ? 0 : createClassifier.hashCode());
    }
  }

  /**
   * @since 2.4
   */
  List<ChildCreationData> getChildCreationData(GenModel context);

  /**
   * @since 2.4
   */
  List<ChildCreationData> getChildCreationData();

  List<GenClass> getChildrenClasses(GenFeature genFeature);
  List<GenClass> getCrossPackageChildrenClasses(GenFeature genFeature);

  String getItemProviderAdapterFactoryClassName();

  String getTestCaseClassName();
  String getQualifiedTestCaseClassName();
  String getImportedTestCaseClassName();
  
  String getModelInfo();

  boolean reconcile(GenClass oldGenClassVersion);

  List<GenOperation> getInvariantOperations();
  GenOperation getInvariantOperation(String constraint);

  boolean isDocumentRoot();
  GenFeature getMixedGenFeature();
  
  String getListConstructor(GenFeature genFeature);

  boolean isModelRoot();

  boolean isFlag(GenFeature genFeature);
  boolean isESetFlag(GenFeature genFeature);

  boolean isField(GenFeature genFeature);
  boolean isESetField(GenFeature genFeature);  

  boolean hasTests();

  String getEVirtualValuesField();
  List<String> getEVirtualIndexBitFields(List<String> eVirtualIndexBitFields);
  List<String> getAllEVirtualIndexBitFields(List<String> allEVirtualIndexBitFields);
  
  boolean isJavaIOSerializable();
  boolean hasFactoryInterfaceCreateMethod();

  /**
   * @since 2.3
   */
  boolean hasOffsetCorrection();
  
  /**
   * @since 2.3
   */
  String getOffsetCorrectionField(GenFeature genFeature);

  /**
   * @since 2.4
   */
  boolean needsHasChildrenMethodOverride();

  /**
   * @since 2.4
   */
  GenOperation getGetAccessorOperation(GenFeature genFeature);

  /**
   * @since 2.4
   */
  GenOperation getIsSetAccessorOperation(GenFeature genFeature);
  
  /**
   * @since 2.4
   */
  GenOperation getSetAccessorOperation(GenFeature genFeature);

  /**
   * @since 2.4
   */
  GenOperation getUnsetAccessorOperation(GenFeature genFeature);

  /**
   * @since 2.4
   */
  boolean hasCollidingGetAccessorOperation(GenFeature genFeature);

  /**
   * @since 2.4
   */
  boolean hasCollidingIsSetAccessorOperation(GenFeature genFeature);
  
  /**
   * @since 2.4
   */
  boolean hasCollidingSetAccessorOperation(GenFeature genFeature);

  /**
   * @since 2.4
   */
  boolean hasCollidingUnsetAccessorOperation(GenFeature genFeature);

  /**
   * Returns a list that corresponds to {@link #getInterfaceExtends()}.
   * @since 2.8
   */
  List<String> getQualifiedInterfaceExtendsList();
  
}
