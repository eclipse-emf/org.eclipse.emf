/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.domain;


import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.mapping.MappingRoot;


//import org.eclipse.emf.mapping.Mapping;
//import org.eclipse.emf.mapping.MappingRoot;



/**
 * This interface extends the editing domain to provide the additional needs for <em>editing</em> in a mapping scenario.
 */
public interface MappingDomain extends EditingDomain 
{
  /**
   * This returns the output meta object corresponding to the given input meta object.
   */
  EObject getOutputMetaObject(EObject inputMetaObject);      

  /**
   * This returns the name of the object.
   */
  String getName(Object object);

  /**
   * This sets the name of the object.
   */
  void setName(Object object, String name);

  /**
   * This returns the corresponding output name for the given input name.
   */
  String getOutputName(String inputName);

  /**
   * This returns the list of words (substrings) in the specified output name.
   */
  List<String> parseOutputName(String outputName);

  /**
   * This returns the list of words (substrings) in the specified input name.
   */
  List<String> parseInputName(String inputName);

  /**
   * This returns the type classifier of the input or output object.
   */
  Object getTypeClassifier(Object mappedObject);

  /**
   * This sets the type classifier of the input or output object.
   */
  void setTypeClassifier(Object mappedObject, Object typeClassifier);

  /**
   * This returns the corresponding type classifier for the given input type classifier.
   */
  Object getOutputTypeClassifier(Object inputTypeClassifier);

  /**
   * This returns the mapping root for this domain.
   */
  MappingRoot getMappingRoot(); 

  /**
   * This sets the mapping root for this domain to use.
   */
  void setMappingRoot(MappingRoot root);    

  /**
   * This returns a set of flags that control enablement of mapping creation and removal commands for this domain.
   */
  int getMappingEnablementFlags();

  static final int ENABLE_MULTIPLE_INPUTS = 0x0001;
  static final int ENABLE_MULTIPLE_OUTPUTS = 0x0002;
  static final int ENABLE_MULTIPLE_INPUT_MAPPINGS = 0x0004;
  static final int ENABLE_MULTIPLE_OUTPUT_MAPPINGS = 0x0008;
  static final int ENABLE_INCOMPATIBLE_METAOBJECTS = 0x0010;
  static final int ENABLE_INCOMPATIBLE_TYPE_CLASSIFIERS = 0x0020;
  static final int ENABLE_EMPTY_INPUTS = 0x0040;
  static final int ENABLE_EMPTY_OUTPUTS = 0x0080;
  static final int ENABLE_UNMAPPED_PARENTS = 0x0100;
  static final int ENABLE_ALL = 0xFFFF;

}
