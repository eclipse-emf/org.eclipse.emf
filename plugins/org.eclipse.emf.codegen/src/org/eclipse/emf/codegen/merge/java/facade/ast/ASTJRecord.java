/**
 * Copyright (c) 2006-2025 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.codegen.merge.java.facade.JRecord;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.RecordDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;


/**
 * Wraps a {@link RecordDeclaration} object.
 *
 * @since 2.27.0
 */
public class ASTJRecord extends ASTJAbstractType<RecordDeclaration> implements JRecord
{
  /**
   * Array of cached super interfaces. All currently set super interfaces is a combination of
   * this array and {@link #addedSuperInterfaces}.
   */
  protected String[] superInterfaces = EMPTY_STRING_ARRAY;

  /**
   * List of added interfaces by calling {@link #addSuperInterface(String)}.
   * This list does not include existing interfaces, nor interfaces set by {@link #setSuperInterfaces(String[])}
   */
  protected List<String> addedSuperInterfaces;

  /**
   * Cached type parameters
   * @see #getTypeParameters()
   * @see #setTypeParameters(String[])
   */
  protected String[] typeParameters = EMPTY_STRING_ARRAY;

  protected String[] recordComponentNames = EMPTY_STRING_ARRAY;

  protected String[] recordComponents = EMPTY_STRING_ARRAY;

  protected ASTJRecord(RecordDeclaration abstractTypeDeclaration)
  {
    super(abstractTypeDeclaration);
  }

  @Override
  public void dispose()
  {
    superInterfaces = null;
    typeParameters = null;
    recordComponentNames = null;
    recordComponents = null;
    if (addedSuperInterfaces != null)
    {
      addedSuperInterfaces.clear();
      addedSuperInterfaces = null;
    }

    super.dispose();
  }

  @SuppressWarnings("unchecked")
  public String[] getSuperInterfaces()
  {
    if (superInterfaces == EMPTY_STRING_ARRAY)
    {
      superInterfaces = convertASTNodeListToStringArray(getASTNode().superInterfaceTypes());
    }

    // add added super interfaces to the array
    superInterfaces = combineArrayAndList(superInterfaces, addedSuperInterfaces);
    return superInterfaces;
  }

  public void setSuperInterfaces(String[] interfaceNames)
  {
    this.superInterfaces = interfaceNames;
    this.addedSuperInterfaces = null;
    setListNodeProperty(getASTNode(), interfaceNames, RecordDeclaration.SUPER_INTERFACE_TYPES_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  public void addSuperInterface(String superInterface)
  {
    if (addedSuperInterfaces == null)
    {
      addedSuperInterfaces = new ArrayList<String>();
    }
    addedSuperInterfaces.add(superInterface);
    addValueToListProperty(getASTNode(), superInterface, RecordDeclaration.SUPER_INTERFACE_TYPES_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  @SuppressWarnings("unchecked")
  public String[] getTypeParameters()
  {
    if (typeParameters == EMPTY_STRING_ARRAY)
    {
      typeParameters = convertASTNodeListToStringArray(getASTNode().typeParameters());
    }
    return typeParameters;
  }

  public void setTypeParameters(String[] typeParameters)
  {
    this.typeParameters = typeParameters;
    setListNodeProperty(getASTNode(), typeParameters, RecordDeclaration.TYPE_PARAMETERS_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  /**
   * Returned recordComponents will not include new names set by
   * {@link #setRecordComponentNames(String[])}.
   *
   * @see org.eclipse.emf.codegen.merge.java.facade.JRecord#getRecordComponents()
   */
  @Override
  public String[] getRecordComponents()
  {
    if (recordComponents == EMPTY_STRING_ARRAY)
    {
      @SuppressWarnings("unchecked")
      List<SingleVariableDeclaration> recordComponentsList = getASTNode().recordComponents();
      recordComponents = convertASTNodeListToStringArray(recordComponentsList);
    }
    return recordComponents;
  }

  @Override
  public void setRecordComponents(String[] recordComponents)
  {
    this.recordComponents = recordComponents;
    setListNodeProperty(getASTNode(), recordComponents, RecordDeclaration.RECORD_COMPONENTS_PROPERTY, ASTNode.SINGLE_VARIABLE_DECLARATION);
  }

  /**
   * Returns recordComponent names.
   * <p>{@link #setRecordComponents(String[])}
   * method, this method will <strong>not</strong> par
   * Note that if recordComponents have been changed by se recordComponents, and will
   * <strong>not</strong> return the new recordComponent names. This method will return either
   * recordComponent names set by {@link #setRecordComponentNames(String[])} method or original
   * recordComponent names.
   *
   * @see org.eclipse.emf.codegen.merge.java.facade.JRecord#getRecordComponentNames()
   */
  @Override
  public String[] getRecordComponentNames()
  {
    if (recordComponentNames == EMPTY_STRING_ARRAY)
    {
      @SuppressWarnings("unchecked")
      List<SingleVariableDeclaration> recordComponents = getASTNode().recordComponents();

      recordComponentNames = new String [recordComponents.size()];
      int i = 0;
      for (SingleVariableDeclaration recordComponent : recordComponents)
      {
        recordComponentNames[i++] = ASTFacadeHelper.toString(recordComponent.getName());
      }
    }
    return recordComponentNames;
  }

  /**
   * Returns type erasure of all the types of formal recordComponents of the original method
   * declaration.
   * <p>
   * This method is used to create a method signature, and match methods by signature.
   * <p>
   * Note that this implementation does not expand types into fully qualified names, nor does it
   * replace type recordComponents defined for a class or a method.
   *
   * @see ASTFacadeHelper#getTypeErasure(org.eclipse.jdt.core.dom.Type)
   * @see org.eclipse.emf.codegen.merge.java.facade.JRecord#getRecordComponentTypes()
   */
  @Override
  public String[] getRecordComponentTypes()
  {
    @SuppressWarnings("unchecked")
    List<SingleVariableDeclaration> recordComponents = getASTNode().recordComponents();

    String[] ret = new String [recordComponents.size()];
    int j = 0;
    for (SingleVariableDeclaration recordComponent : recordComponents)
    {
      String type = ASTFacadeHelper.getTypeErasure(recordComponent.getType());
      // append extra dimensions if any
      for (int i = 0; i < recordComponent.getExtraDimensions(); i++)
      {
        type += "[]";
      }

      // append [] if it is variable arity recordComponent (@see JLS3 8.4.1,
      // http://java.sun.com/docs/books/jls/third_edition/html/classes.html#300870)
      if (recordComponent.isVarargs())
      {
        type += "[]";
      }
      ret[j++] = type;
    }
    return ret;
  }

  /**
   * Returns all the types of formal recordComponents of the original method declaration. Note
   * that this implementation does not expand types into fully qualified names, nor does it
   * replace type recordComponents defined for a class or a method.
   *
   * @see org.eclipse.emf.codegen.merge.java.facade.JRecord#getFullRecordComponentTypes()
   */
  @Override
  public String[] getFullRecordComponentTypes()
  {
    @SuppressWarnings("unchecked")
    List<SingleVariableDeclaration> recordComponents = getASTNode().recordComponents();

    String[] ret = new String [recordComponents.size()];
    int j = 0;
    for (SingleVariableDeclaration recordComponent : recordComponents)
    {
      String type = getFacadeHelper().toString(recordComponent.getType());
      // append extra dimensions if any
      for (int i = 0; i < recordComponent.getExtraDimensions(); i++)
      {
        type += "[]";
      }

      // append [] if it is variable arity recordComponent (@see JLS3 8.4.1,
      // http://java.sun.com/docs/books/jls/third_edition/html/classes.html#300870)
      if (recordComponent.isVarargs())
      {
        type += "[]";
      }
      ret[j++] = type;
    }
    return ret;
  }

  /**
   * Sets recordComponent names. New recordComponent names will not be returned by
   * {@link #getRecordComponents()}, but will be returned by {@link #getRecordComponentNames()}.
   *
   * @see #getRecordComponentNames()
   * @see #getRecordComponents()
   * @see org.eclipse.emf.codegen.merge.java.facade.JRecord#setRecordComponentNames(java.lang.String[])
   */
  @Override
  public void setRecordComponentNames(String[] names)
  {

    @SuppressWarnings("unchecked")
    List<SingleVariableDeclaration> recordComponents = getASTNode().recordComponents();

    if (recordComponents.size() != names.length)
    {
      throw new IllegalArgumentException("Length of names must match number of existing recordComponents.");
    }

    recordComponentNames = names;
    int i = 0;
    for (SingleVariableDeclaration recordComponent : recordComponents)
    {
      setNodeProperty(recordComponent, names[i++], SingleVariableDeclaration.NAME_PROPERTY, ASTNode.SIMPLE_NAME);
    }
  }
}
