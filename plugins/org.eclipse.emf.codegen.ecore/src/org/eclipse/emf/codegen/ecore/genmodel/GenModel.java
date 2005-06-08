/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: GenModel.java,v 1.8.2.1 2005/06/08 18:27:45 nickb Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;

import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jmerge.JControlModel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>GenModel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getCopyrightText <em>Copyright Text</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelDirectory <em>Model Directory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCreationCommands <em>Creation Commands</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditDirectory <em>Edit Directory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorDirectory <em>Editor Directory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelPluginID <em>Model Plugin ID</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTemplateDirectory <em>Template Directory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRuntimeJar <em>Runtime Jar</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getForeignModel <em>Foreign Model</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isDynamicTemplates <em>Dynamic Templates</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRedirection <em>Redirection</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isForceOverwrite <em>Force Overwrite</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getNonExternalizedStringTag <em>Non Externalized String Tag</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestDirectory <em>Test Directory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelName <em>Model Name</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelPluginClass <em>Model Plugin Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditPluginClass <em>Edit Plugin Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorPluginClass <em>Editor Plugin Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isUpdateClasspath <em>Update Classpath</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isGenerateSchema <em>Generate Schema</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isNonNLSMarkers <em>Non NLS Markers</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getStaticPackages <em>Static Packages</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelPluginVariables <em>Model Plugin Variables</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootExtendsInterface <em>Root Extends Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootExtendsClass <em>Root Extends Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootImplementsInterface <em>Root Implements Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressEMFTypes <em>Suppress EMF Types</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperInterface <em>Feature Map Wrapper Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperInternalInterface <em>Feature Map Wrapper Internal Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperClass <em>Feature Map Wrapper Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRuntimeCompatibility <em>Runtime Compatibility</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRichClientPlatform <em>Rich Client Platform</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isReflectiveDelegation <em>Reflective Delegation</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getGenPackages <em>Gen Packages</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getUsedGenPackages <em>Used Gen Packages</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel()
 * @model 
 * @generated
 */
public interface GenModel extends GenBase{
  /**
   * Returns the value of the '<em><b>Copyright Text</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Copyright Text</em>' attribute isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Copyright Text</em>' attribute.
   * @see #setCopyrightText(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_CopyrightText()
   * @model 
   * @generated
   */
  String getCopyrightText();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getCopyrightText <em>Copyright Text</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Copyright Text</em>' attribute.
   * @see #getCopyrightText()
   * @generated
   */
  void setCopyrightText(String value);

  /**
   * Returns the value of the '<em><b>Model Directory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Model Directory</em>' attribute isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Directory</em>' attribute.
   * @see #setModelDirectory(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ModelDirectory()
   * @model 
   * @generated
   */
  String getModelDirectory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelDirectory <em>Model Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Model Directory</em>' attribute.
   * @see #getModelDirectory()
   * @generated
   */
  void setModelDirectory(String value);

  /**
   * Returns the value of the '<em><b>Creation Commands</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Creation Commands</em>' attribute.
   * @see #setCreationCommands(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_CreationCommands()
   * @model default="true"
   * @generated
   */
  boolean isCreationCommands();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCreationCommands <em>Creation Commands</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Creation Commands</em>' attribute.
   * @see #isCreationCommands()
   * @generated
   */
  void setCreationCommands(boolean value);

  /**
   * Returns the value of the '<em><b>Edit Directory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Edit Directory</em>' attribute isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Edit Directory</em>' attribute.
   * @see #setEditDirectory(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_EditDirectory()
   * @model 
   * @generated
   */
  String getEditDirectory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditDirectory <em>Edit Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Edit Directory</em>' attribute.
   * @see #getEditDirectory()
   * @generated
   */
  void setEditDirectory(String value);

  /**
   * Returns the value of the '<em><b>Editor Directory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Editor Directory</em>' attribute isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Editor Directory</em>' attribute.
   * @see #setEditorDirectory(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_EditorDirectory()
   * @model 
   * @generated
   */
  String getEditorDirectory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorDirectory <em>Editor Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Editor Directory</em>' attribute.
   * @see #getEditorDirectory()
   * @generated
   */
  void setEditorDirectory(String value);

  /**
   * Returns the value of the '<em><b>Model Plugin ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Model Plugin ID</em>' attribute isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Plugin ID</em>' attribute.
   * @see #setModelPluginID(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ModelPluginID()
   * @model 
   * @generated
   */
  String getModelPluginID();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelPluginID <em>Model Plugin ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Model Plugin ID</em>' attribute.
   * @see #getModelPluginID()
   * @generated
   */
  void setModelPluginID(String value);

  /**
   * Returns the value of the '<em><b>Template Directory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Template Directory</em>' attribute isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Template Directory</em>' attribute.
   * @see #setTemplateDirectory(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_TemplateDirectory()
   * @model 
   * @generated
   */
  String getTemplateDirectory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTemplateDirectory <em>Template Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Template Directory</em>' attribute.
   * @see #getTemplateDirectory()
   * @generated
   */
  void setTemplateDirectory(String value);

  /**
   * Returns the value of the '<em><b>Runtime Jar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Runtime Jar</em>' attribute.
   * @see #setRuntimeJar(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_RuntimeJar()
   * @model 
   * @generated
   */
  boolean isRuntimeJar();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRuntimeJar <em>Runtime Jar</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Runtime Jar</em>' attribute.
   * @see #isRuntimeJar()
   * @generated
   */
  void setRuntimeJar(boolean value);

  /**
   * Returns the value of the '<em><b>Foreign Model</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Foreign Model</em>' attribute list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Foreign Model</em>' attribute list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ForeignModel()
   * @model type="java.lang.String"
   * @generated
   */
  EList getForeignModel();

  /**
   * Returns the value of the '<em><b>Dynamic Templates</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dynamic Templates</em>' attribute.
   * @see #setDynamicTemplates(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_DynamicTemplates()
   * @model 
   * @generated
   */
  boolean isDynamicTemplates();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isDynamicTemplates <em>Dynamic Templates</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Dynamic Templates</em>' attribute.
   * @see #isDynamicTemplates()
   * @generated
   */
  void setDynamicTemplates(boolean value);

  /**
   * Returns the value of the '<em><b>Redirection</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Redirection</em>' attribute isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Redirection</em>' attribute.
   * @see #setRedirection(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_Redirection()
   * @model 
   * @generated
   */
  String getRedirection();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRedirection <em>Redirection</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Redirection</em>' attribute.
   * @see #getRedirection()
   * @generated
   */
  void setRedirection(String value);

  /**
   * Returns the value of the '<em><b>Force Overwrite</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Force Overwrite</em>' attribute.
   * @see #setForceOverwrite(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ForceOverwrite()
   * @model 
   * @generated
   */
  boolean isForceOverwrite();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isForceOverwrite <em>Force Overwrite</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Force Overwrite</em>' attribute.
   * @see #isForceOverwrite()
   * @generated
   */
  void setForceOverwrite(boolean value);

  /**
   * Returns the value of the '<em><b>Non Externalized String Tag</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>This attribute has been replaced by {@link #isNonNLSMarkers
   * nonNLSMarkers}.  Even after the attribute has been set to a non-null
   * value, this method will continue to return <code>null</code>, though
   * <code>isNonNLSMarkers()</code> will return <code>true</code>.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Non Externalized String Tag</em>' attribute.
   * @see #setNonExternalizedStringTag(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_NonExternalizedStringTag()
   * @model 
   * @generated
   */
  String getNonExternalizedStringTag();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getNonExternalizedStringTag <em>Non Externalized String Tag</em>}' attribute.
   * <!-- begin-user-doc -->
   * <p>This attribute has been replaced by {@link #setNonNLSMarkers
   * nonNLSMarkers}.  To automate the transition, this sets the attribute
   * <code>null</code>, and calls <code>setNonNLSMarkers(value != null)</code>.
   * </p>
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Non Externalized String Tag</em>' attribute.
   * @see #getNonExternalizedStringTag()
   * @generated
   */
  void setNonExternalizedStringTag(String value);

  /**
   * Returns the value of the '<em><b>Test Directory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Test Directory</em>' attribute isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Test Directory</em>' attribute.
   * @see #setTestDirectory(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_TestDirectory()
   * @model 
   * @generated
   */
  String getTestDirectory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestDirectory <em>Test Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Test Directory</em>' attribute.
   * @see #getTestDirectory()
   * @generated
   */
  void setTestDirectory(String value);

  /**
   * Returns the value of the '<em><b>Model Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Model Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Name</em>' attribute.
   * @see #setModelName(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ModelName()
   * @model 
   * @generated
   */
  String getModelName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelName <em>Model Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Model Name</em>' attribute.
   * @see #getModelName()
   * @generated
   */
  void setModelName(String value);

  /**
   * Returns the value of the '<em><b>Model Plugin Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Model Plugin Class</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Plugin Class</em>' attribute.
   * @see #setModelPluginClass(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ModelPluginClass()
   * @model 
   * @generated
   */
  String getModelPluginClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelPluginClass <em>Model Plugin Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Model Plugin Class</em>' attribute.
   * @see #getModelPluginClass()
   * @generated
   */
  void setModelPluginClass(String value);

  /**
   * Returns the value of the '<em><b>Edit Plugin Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Edit Plugin Class</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Edit Plugin Class</em>' attribute.
   * @see #setEditPluginClass(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_EditPluginClass()
   * @model 
   * @generated
   */
  String getEditPluginClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditPluginClass <em>Edit Plugin Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Edit Plugin Class</em>' attribute.
   * @see #getEditPluginClass()
   * @generated
   */
  void setEditPluginClass(String value);

  /**
   * Returns the value of the '<em><b>Editor Plugin Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Editor Plugin Class</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Editor Plugin Class</em>' attribute.
   * @see #setEditorPluginClass(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_EditorPluginClass()
   * @model 
   * @generated
   */
  String getEditorPluginClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorPluginClass <em>Editor Plugin Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Editor Plugin Class</em>' attribute.
   * @see #getEditorPluginClass()
   * @generated
   */
  void setEditorPluginClass(String value);

  /**
   * Returns the value of the '<em><b>Update Classpath</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Update Classpath</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Update Classpath</em>' attribute.
   * @see #setUpdateClasspath(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_UpdateClasspath()
   * @model default="true"
   * @generated
   */
  boolean isUpdateClasspath();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isUpdateClasspath <em>Update Classpath</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Update Classpath</em>' attribute.
   * @see #isUpdateClasspath()
   * @generated
   */
  void setUpdateClasspath(boolean value);

  /**
   * Returns the value of the '<em><b>Generate Schema</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Generate Schema</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Generate Schema</em>' attribute.
   * @see #setGenerateSchema(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_GenerateSchema()
   * @model 
   * @generated
   */
  boolean isGenerateSchema();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isGenerateSchema <em>Generate Schema</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Generate Schema</em>' attribute.
   * @see #isGenerateSchema()
   * @generated
   */
  void setGenerateSchema(boolean value);

  /**
   * Returns the value of the '<em><b>Non NLS Markers</b></em>' attribute.
   * The default value is <code>"false"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Non NLS Markers</em>' attribute.
   * @see #setNonNLSMarkers(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_NonNLSMarkers()
   * @model default="false"
   * @generated
   */
  boolean isNonNLSMarkers();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isNonNLSMarkers <em>Non NLS Markers</em>}' attribute.
   * <!-- begin-user-doc -->
   * <p>As a side effect, this method sets {@link #setNonExternalizedStringTag
   * nonExternalizedStringTag} to <code>null</code>.
   * </p>
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Non NLS Markers</em>' attribute.
   * @see #isNonNLSMarkers()
   * @generated
   */
  void setNonNLSMarkers(boolean value);

  /**
   * Returns the value of the '<em><b>Static Packages</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Static Packages</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Static Packages</em>' attribute list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_StaticPackages()
   * @model type="java.lang.String"
   * @generated
   */
  EList getStaticPackages();

  /**
   * Returns the value of the '<em><b>Model Plugin Variables</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Model Plugin Variables</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Plugin Variables</em>' attribute list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ModelPluginVariables()
   * @model type="java.lang.String"
   * @generated
   */
  EList getModelPluginVariables();

  /**
   * Returns the value of the '<em><b>Root Extends Interface</b></em>' attribute.
   * The default value is <code>"org.eclipse.emf.ecore.EObject"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Root Extends Interface</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Root Extends Interface</em>' attribute.
   * @see #setRootExtendsInterface(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_RootExtendsInterface()
   * @model default="org.eclipse.emf.ecore.EObject"
   * @generated
   */
  String getRootExtendsInterface();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootExtendsInterface <em>Root Extends Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Root Extends Interface</em>' attribute.
   * @see #getRootExtendsInterface()
   * @generated
   */
  void setRootExtendsInterface(String value);

  /**
   * Returns the value of the '<em><b>Root Extends Class</b></em>' attribute.
   * The default value is <code>"org.eclipse.emf.ecore.impl.EObjectImpl"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Root Extends Class</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Root Extends Class</em>' attribute.
   * @see #setRootExtendsClass(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_RootExtendsClass()
   * @model default="org.eclipse.emf.ecore.impl.EObjectImpl"
   * @generated
   */
  String getRootExtendsClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootExtendsClass <em>Root Extends Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Root Extends Class</em>' attribute.
   * @see #getRootExtendsClass()
   * @generated
   */
  void setRootExtendsClass(String value);

  /**
   * Returns the value of the '<em><b>Root Implements Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Root Implements Interface</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Root Implements Interface</em>' attribute.
   * @see #setRootImplementsInterface(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_RootImplementsInterface()
   * @model 
   * @generated
   */
  String getRootImplementsInterface();

  GenClass getRootImplementsInterfaceGenClass();


  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootImplementsInterface <em>Root Implements Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Root Implements Interface</em>' attribute.
   * @see #getRootImplementsInterface()
   * @generated
   */
  void setRootImplementsInterface(String value);

  /**
   * Returns the value of the '<em><b>Suppress EMF Types</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Suppress EMF Types</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Suppress EMF Types</em>' attribute.
   * @see #setSuppressEMFTypes(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_SuppressEMFTypes()
   * @model 
   * @generated
   */
  boolean isSuppressEMFTypes();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressEMFTypes <em>Suppress EMF Types</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Suppress EMF Types</em>' attribute.
   * @see #isSuppressEMFTypes()
   * @generated
   */
  void setSuppressEMFTypes(boolean value);

  /**
   * Returns the value of the '<em><b>Feature Map Wrapper Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature Map Wrapper Interface</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature Map Wrapper Interface</em>' attribute.
   * @see #setFeatureMapWrapperInterface(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_FeatureMapWrapperInterface()
   * @model 
   * @generated
   */
  String getFeatureMapWrapperInterface();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperInterface <em>Feature Map Wrapper Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature Map Wrapper Interface</em>' attribute.
   * @see #getFeatureMapWrapperInterface()
   * @generated
   */
  void setFeatureMapWrapperInterface(String value);

  /**
   * Returns the value of the '<em><b>Feature Map Wrapper Internal Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature Map Wrapper Internal Interface</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature Map Wrapper Internal Interface</em>' attribute.
   * @see #setFeatureMapWrapperInternalInterface(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_FeatureMapWrapperInternalInterface()
   * @model 
   * @generated
   */
  String getFeatureMapWrapperInternalInterface();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperInternalInterface <em>Feature Map Wrapper Internal Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature Map Wrapper Internal Interface</em>' attribute.
   * @see #getFeatureMapWrapperInternalInterface()
   * @generated
   */
  void setFeatureMapWrapperInternalInterface(String value);

  /**
   * Returns the value of the '<em><b>Feature Map Wrapper Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature Map Wrapper Class</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature Map Wrapper Class</em>' attribute.
   * @see #setFeatureMapWrapperClass(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_FeatureMapWrapperClass()
   * @model 
   * @generated
   */
  String getFeatureMapWrapperClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperClass <em>Feature Map Wrapper Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature Map Wrapper Class</em>' attribute.
   * @see #getFeatureMapWrapperClass()
   * @generated
   */
  void setFeatureMapWrapperClass(String value);

  /**
   * Returns the value of the '<em><b>Runtime Compatibility</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Runtime Compatibility</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Runtime Compatibility</em>' attribute.
   * @see #setRuntimeCompatibility(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_RuntimeCompatibility()
   * @model default="true"
   * @generated
   */
  boolean isRuntimeCompatibility();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRuntimeCompatibility <em>Runtime Compatibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Runtime Compatibility</em>' attribute.
   * @see #isRuntimeCompatibility()
   * @generated
   */
  void setRuntimeCompatibility(boolean value);

  /**
   * Returns the value of the '<em><b>Rich Client Platform</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rich Client Platform</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rich Client Platform</em>' attribute.
   * @see #setRichClientPlatform(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_RichClientPlatform()
   * @model 
   * @generated
   */
  boolean isRichClientPlatform();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRichClientPlatform <em>Rich Client Platform</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rich Client Platform</em>' attribute.
   * @see #isRichClientPlatform()
   * @generated
   */
  void setRichClientPlatform(boolean value);

  /**
   * Returns the value of the '<em><b>Reflective Delegation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reflective Delegation</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reflective Delegation</em>' attribute.
   * @see #setReflectiveDelegation(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ReflectiveDelegation()
   * @model 
   * @generated
   */
  boolean isReflectiveDelegation();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isReflectiveDelegation <em>Reflective Delegation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reflective Delegation</em>' attribute.
   * @see #isReflectiveDelegation()
   * @generated
   */
  void setReflectiveDelegation(boolean value);

  /**
   * Returns the value of the '<em><b>Gen Packages</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenModel <em>Gen Model</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Gen Packages</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Packages</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_GenPackages()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenModel
   * @model type="org.eclipse.emf.codegen.ecore.genmodel.GenPackage" opposite="genModel" containment="true"
   * @generated
   */
  EList getGenPackages();

  /**
   * Returns the value of the '<em><b>Used Gen Packages</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Used Gen Packages</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Used Gen Packages</em>' reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_UsedGenPackages()
   * @model type="org.eclipse.emf.codegen.ecore.genmodel.GenPackage"
   * @generated
   */
  EList getUsedGenPackages();

  EList getStaticGenPackages();

  void initialize(Collection ePackages);

  String getDriverNumber();
  String getDate();

  String getNonNLS();
  String getNonNLS(int i);
  String getNonNLS(String s);
  String getNonNLS(String s, int i);

  void markImportLocation(StringBuffer stringBuffer);
  void markImportLocation(StringBuffer stringBuffer, GenPackage genPackage);
  void emitSortedImports();
  String getIndentation(StringBuffer stringBuffer);

  String getImportedName(String qualifiedName);
  void addImport(String qualifiedName);
  void addPseudoImport(String qualifiedName);

  JControlModel getJControlModel();

  JETEmitter getInterfaceEmitter();
  JETEmitter getClassEmitter();
  JETEmitter getEnumClassEmitter();
  JETEmitter getFactoryInterfaceEmitter();
  JETEmitter getFactoryClassEmitter();
  JETEmitter getPackageInterfaceEmitter();
  JETEmitter getPackageClassEmitter();
  JETEmitter getAdapterFactoryClassEmitter();
  JETEmitter getSwitchClassEmitter();
  JETEmitter getValidatorClassEmitter();
  JETEmitter getPluginXMLEmitter();
  JETEmitter getModelPluginClassEmitter();
  JETEmitter getResourceClassEmitter();
  JETEmitter getResourceFactoryClassEmitter();
  JETEmitter getBuildPropertiesEmitter();

  //
  // EMF.Edit generation
  //
  boolean hasEditSupport();

  JETEmitter getItemProviderEmitter();
  JETEmitter getItemProviderAdapterFactoryEmitter();
  JETEmitter getEditPluginClassEmitter();
  JETEmitter getEditPluginXMLEmitter();
  JETEmitter getEditPluginPropertiesEmitter();
  JETEmitter getEditBuildPropertiesEmitter();

  //
  // EMF.Editor generation
  //
  boolean hasEditorSupport();

  JETEmitter getEditorEmitter();
  JETEmitter getActionBarContributorEmitter();
  JETEmitter getModelWizardEmitter();
  JETEmitter getEditorAdvisorEmitter();
  JETEmitter getEditorPluginClassEmitter();
  JETEmitter getEditorPluginXMLEmitter();
  JETEmitter getEditorPluginPropertiesEmitter();
  JETEmitter getEditorBuildPropertiesEmitter();

  // boolean isGenerateEditPlugin();
  // boolean isGenerateEditorPlugin();

  String getModelProjectDirectory();
  String getEditProjectDirectory();
  String getEditorProjectDirectory();

  boolean sameModelEditProject();
  boolean sameEditEditorProject();
  boolean sameModelEditorProject();

  String getEditIconsDirectory();
  String getEditorIconsDirectory();

  void setCanGenerate(boolean canGenerate);

  GenPackage findGenPackage(EPackage ePackage);

  List getAllGenPackagesWithClassifiers();
  List getAllUsedGenPackagesWithClassifiers();
  List getAllGenAndUsedGenPackagesWithClassifiers();
  List getAllGenUsedAndStaticGenPackagesWithClassifiers();

  boolean hasModelPluginClass();
  boolean hasPluginSupport();

  String getModelPluginClassName();
  String getModelPluginPackageName();
  String getQualifiedModelPluginClassName();

  String getEditPluginClassName();
  String getEditPluginPackageName();
  String getQualifiedEditPluginClassName();

  String getEditorPluginClassName();
  String getEditorPluginPackageName();
  String getEditorAdvisorClassName();
  String getQualifiedEditorPluginClassName();
  String getQualifiedEditorAdvisorClassName();

  String getEditPluginID();
  String getEditorPluginID();

  List getModelRequiredPlugins();
  List getEditRequiredPlugins();
  List getEditorRequiredPlugins();

  List getEditResourceDelegateImportedPluginClassNames();

  /**
   * Restore all the corresponding settings of the old version into this version.
   */
  boolean reconcile(GenModel oldGenModelVersion);

  List getMissingPackages();

  boolean hasXMLDependency();

  IStatus validate();

  String getXMLEncodingChoices();

  List getEffectiveModelPluginVariables();

  boolean needsRuntimeCompatibility();
}
