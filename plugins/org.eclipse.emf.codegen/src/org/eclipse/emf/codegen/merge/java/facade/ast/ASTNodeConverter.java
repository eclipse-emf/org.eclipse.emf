/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.JAbstractType;
import org.eclipse.emf.codegen.merge.java.facade.JNode;
import org.eclipse.emf.codegen.merge.java.facade.NodeConverter;


public class ASTNodeConverter implements NodeConverter
{
  protected ASTFacadeHelper facadeHelper;

  /**
   * @param facadeHelper must be not <code>null</code>
   */
  public ASTNodeConverter(ASTFacadeHelper facadeHelper)
  {
    assert facadeHelper != null;

    this.facadeHelper = facadeHelper;
  }

  public ASTFacadeHelper getFacadeHelper()
  {
    return facadeHelper;
  }
  
  public JAbstractType convert(JAbstractType abstractType, Class<? extends JAbstractType> cls)
  {
    try
    {
      Converter converter = null; 
      
      // if the given class is subclass of enum and node is of subclass of type
      if (ASTJEnum.class.isAssignableFrom(cls) && abstractType instanceof ASTJType)
      {
        converter = new TypeToEnumConverter((ASTJType)abstractType);
      }
      else if (ASTJType.class.isAssignableFrom(cls) && abstractType instanceof ASTJEnum)
      {
        converter = new EnumToTypeConverter((ASTJEnum)abstractType);
      }
      
      return converter != null ? (JAbstractType)converter.convert() : null;
    }
    catch (Exception e)
    {
      if (ASTFacadeHelper.DEBUG)
      {
        getFacadeHelper().logError("Error converting " + abstractType.getClass().getSimpleName() + " to " + cls.getSimpleName(), e);
      }
    }
    return null;
  }  

  /**
   * Base class for all converters.
   * 
   * @see #convert()
   */
  protected abstract class Converter
  {
    /**
     * @return converted node
     */
    public abstract JNode convert();

    /**
     * Removes children from the source node and adds them to the target node.
     * 
     * @param source
     * @param target
     */
    protected void moveChildren(JNode source, JNode target)
    {
      for (JNode child : source.getChildren())
      {
        getFacadeHelper().remove(child);
        getFacadeHelper().addChild(target, child);
      }
    }
    
    /**
     * Replaces existing node by new node.
     * 
     * @param existingNode
     * @param newNode
     */
    protected void replaceNode(JNode existingNode, JNode newNode)
    {
      getFacadeHelper().insertSibling(existingNode, newNode, false);
      getFacadeHelper().remove(existingNode);      
    }
  }

  /**
   * Converter of type to enum.
   *
   * @see #convert()
   */  
  protected class TypeToEnumConverter extends Converter
  {
    /**
     * Type that will be converted
     */
    protected ASTJType type;
    
    /**
     * AST of the source and converted node
     */
    protected AST ast;
    
    /**
     * List of children of the type
     */
    protected List<JNode> typeChildren;
    
    /**
     * Map of field names to fields
     */
    protected Map<String, ASTJField> fieldNamesMap = new HashMap<String, ASTJField>();

    /**
     * @param type to convert to enum
     */
    public TypeToEnumConverter(ASTJType type)
    {
      this.type = type;
      
      ast = type.getASTNode().getAST();
      
      typeChildren = type.getChildren();

      // create map of field names to fields
      for (JNode child : typeChildren)
      {
        if (child instanceof ASTJField)
        {
          fieldNamesMap.put(child.getName(), (ASTJField)child);
        }
      }
    }

    /**
     * Converts type to the enum.
     * <p>
     * Name, flags, comment, super interfaces of the enum are set from the type.
     * <p>
     * All children of the type is added to the enum.
     * <p>
     * Fields that are <code>public static final </code>and have the same type are converted to
     * enum constants. Arguments of the enum constants are set from arguments of the initializer.
     * Arguments that match the name of any other field are replaced by initializer value of that field.
     * <p>
     * Conversion must be done only once for the same type.
     * 
     * @return converted type, or <code>null</code> if conversion not possible
     */
    @Override
    public ASTJEnum convert()
    {
      EnumDeclaration enumDeclaration = ast.newEnumDeclaration();

      ASTJEnum astjEnum = (ASTJEnum)getFacadeHelper().convertToNode(enumDeclaration);
      astjEnum.setRewriter(type.getRewriter());

      astjEnum.setName(type.getName());      
      astjEnum.setFlags(type.getFlags());
      astjEnum.setComment(type.getComment());

      // move all children to the enum, converting some fields to constants
      for (JNode child : typeChildren)
      {
        ASTJNode<?> nodeToRemove = (ASTJNode<?>)child;
        ASTJNode<?> nodeToInsert = nodeToRemove;

        if (nodeToRemove instanceof ASTJField)
        {
          ASTJField field = (ASTJField)nodeToRemove;
          nodeToInsert = convertFieldToEnumConst(field);
        }

        type.remove(nodeToRemove);
        astjEnum.addChild(nodeToInsert);
      }

      // remove type, insert enum
      replaceNode(type, astjEnum);

      return astjEnum;
    }

    /**
     * Converts given field to enum constant if possible.
     * <p>
     * Field must be declared as <code>public static final</code> field, and field must be of the same type
     * as its parent (e.g. type).
     * <p>
     * Enum constant arguments and body is set from the original initializer of the field.
     * 
     * @param field to convert
     * @return original field if conversion not possible, or converted field otherwise
     * 
     * @see #setEnumConstantArgumentsAndBody(ASTJEnumConstant, ASTJField)
     */
    protected ASTJNode<?> convertFieldToEnumConst(ASTJField field)
    {
      // convert only public static final fields
      int flags = field.getFlags();
      if ((flags & (FacadeFlags.PUBLIC | FacadeFlags.STATIC | FacadeFlags.FINAL)) == 0)
      {
        return field;
      }

      // convert only fields of the same type as parent
      ASTJNode<?> parent = field.getParent();
      String parentType = (parent == null ? null : parent.getName());
      if (parentType == null || !parentType.equals(field.getType()))
      {
        return field;
      }

      EnumConstantDeclaration enumConstantDeclaration = ast.newEnumConstantDeclaration();

      ASTJEnumConstant enumConstant = (ASTJEnumConstant)getFacadeHelper().convertToNode(enumConstantDeclaration);
      enumConstant.setRewriter(field.getRewriter());

      enumConstant.setName(field.getName());
      enumConstant.setComment(field.getComment());

      // set arguments and body
      setEnumConstantArgumentsAndBody(enumConstant, field);

      // move annotations
      moveChildren(field, enumConstant);

      return enumConstant;
    }

    /**
     * Sets arguments and body of the enum constant from the initializer of the given field.
     * <p>
     * Note that the original field's initializer node structure is used (e.g. if {@link ASTJField#setInitializer(String)}
     * has been called, this method will use the original initializer of the field).
     * 
     * @param enumConstant
     * @param field
     */
    protected void setEnumConstantArgumentsAndBody(ASTJEnumConstant enumConstant, ASTJField field)
    {
      Expression initializer = field.getVariableDeclarationFragment().getInitializer();
      if (initializer == null || initializer.getNodeType() != ASTNode.CLASS_INSTANCE_CREATION)
      {
        // unable to set arguments or body
        return;
      }

      ClassInstanceCreation classInstanceCreation = (ClassInstanceCreation)initializer;

      // replace arguments that match the name of the fields with the initializer value of the field
      //
      @SuppressWarnings("unchecked")
      String[] arguments = field.convertASTNodeListToStringArray(classInstanceCreation.arguments());

      for (int i = 0; i < arguments.length; i++)
      {
        ASTJField existingField = fieldNamesMap.get(arguments[i]);
        if (existingField != null)
        {
          String fieldInitializer = existingField.getInitializer();
          if (fieldInitializer != null && fieldInitializer.length() > 0)
          {
            arguments[i] = fieldInitializer;
          }
        }
      }

      enumConstant.setArguments(arguments);
      enumConstant.setBody(getFacadeHelper().toString(classInstanceCreation.getAnonymousClassDeclaration()));
    }
  }

  /**
   * Converter of enum to class.
   *
   * @see #convert()
   */
  protected class EnumToTypeConverter extends Converter
  {
    /**
     * Enum to convert to type
     */
    protected ASTJEnum astjEnum;
    
    /**
     * AST of the source and converted node
     */
    protected AST ast;    
    
    /**
     * List of children of the enum
     */
    protected List<JNode> enumChildren;

    /**
     * Map of initializer values of the fields to fields
     */
    protected Map<String, ASTJField> fieldInitializersMap = new HashMap<String, ASTJField>();

    /**
     * Map of fields to their index in the children list
     */
    protected Map<ASTJField, Integer> fieldIndexesMap = new HashMap<ASTJField, Integer>();
    
    /**
     * Last field used in the initializer string of the fields converted from enum constants. 
     */
    protected ASTJNode<?> lastFinalFieldUsed = null;

    /**
     * @param astjEnum to convert
     */
    public EnumToTypeConverter(ASTJEnum astjEnum)
    {
      this.astjEnum = astjEnum;

      ast = astjEnum.getASTNode().getAST();
      
      enumChildren = astjEnum.getChildren();

      // create map of public static final field initializers to fields
      int i = 0;
      for (JNode child : enumChildren)
      {
        if (child instanceof ASTJField)
        {
          ASTJField field = (ASTJField)child;
          if ((field.getFlags() & (FacadeFlags.PUBLIC | FacadeFlags.STATIC | FacadeFlags.FINAL)) != 0)
          {
            fieldInitializersMap.put(field.getInitializer(), field);
            fieldIndexesMap.put(field, i++);
          }
        }
      }
    }

    /**
     * Converts enum to the class declaration.
     * <p>
     * Name, flags, comment, super interfaces of the class declaration are set from the enum.
     * <p>
     * All children of the enum are added to the class declaration. Enum constants are converted
     * to public static final fields with initializer value created from arguments and body of enum constant.
     * 
     * @return converted type, or <code>null</code> if conversion not possible
     * 
     * @see #setFieldInitializer(ASTJField, ASTJEnumConstant) 
     */
    @Override
    public ASTJType convert()
    {
      TypeDeclaration typeDeclaration = ast.newTypeDeclaration();

      ASTJType type = (ASTJType)getFacadeHelper().convertToNode(typeDeclaration);
      type.setRewriter(astjEnum.getRewriter());

      type.setName(astjEnum.getName());
      type.setFlags(astjEnum.getFlags());
      type.setComment(astjEnum.getComment());

      // move all children to the type, converting enum constants to fields
      //
      // fields that are created by conversion from enum constants must be inserted after
      // the last final field that is used in constructors for initializers
      List<ASTJNode<?>> convertedEnumConstants = new ArrayList<ASTJNode<?>>(enumChildren.size());

      for (JNode child : enumChildren)
      {
        ASTJNode<?> originalNode = (ASTJNode<?>)child;

        // convert enum constants and add to list of converted constants
        if (originalNode instanceof ASTJEnumConstant)
        {
          ASTJEnumConstant constant = (ASTJEnumConstant)originalNode;
          convertedEnumConstants.add(convertEnumConstToField(constant));
        }
        else
        {
          // move the node
          astjEnum.remove(originalNode);
          type.addChild(originalNode);
        }
      }

      // add all converted fields after the last final field 
      // that is used in initializers of the converted fields
      for (ASTJNode<?> nodeToInsert : convertedEnumConstants)
      {
        if (lastFinalFieldUsed == null)
        {
          type.addChild(nodeToInsert);
        }
        else
        {
          // insert nodeToInsert after targetNode
          type.insertSibling(lastFinalFieldUsed, nodeToInsert, false);
          lastFinalFieldUsed = nodeToInsert;
        }
      }

      // replace type by enum
      replaceNode(astjEnum, type);

      return type;
    }

    /**
     * Converts enum constant to field.
     * <p>
     * Resulting field is <code>public static final</code>. The type of the field is the name of the
     * enum. Comment of the field is copied from the enum constant.
     * Initializer value is a call to the constructor of the enum (or converted type) with arguments
     * taken from enum constant.
     * 
     * @see #setFieldInitializer(ASTJField, ASTJEnumConstant)
     * 
     * @param enumConstant to convert
     * @return converted field
     */
    protected ASTJNode<?> convertEnumConstToField(ASTJEnumConstant enumConstant)
    {
      // create field declaration with 1 variable declaration fragment
      VariableDeclarationFragment variableDeclarationFragment = ast.newVariableDeclarationFragment();
      ast.newFieldDeclaration(variableDeclarationFragment);

      ASTJField field = (ASTJField)getFacadeHelper().convertToNode(variableDeclarationFragment);
      field.setRewriter(enumConstant.getRewriter());

      field.setName(enumConstant.getName());
      field.setComment(enumConstant.getComment());
      field.setType(enumConstant.getParent().getName());
      field.setFlags(FacadeFlags.PUBLIC | FacadeFlags.STATIC | FacadeFlags.FINAL);

      // create field initializer from enum constant's arguments and body
      setFieldInitializer(field, enumConstant);

      // move annotations
      moveChildren(enumConstant, field);

      return field;
    }

    /**
     * Sets field initializer based on arguments and body of enum constant.
     * <p>
     * Initializer string is in the form <pre><code> <b>new</b> Type ( ArgumentsList ) AnonymousClassDeclaration</code></pre> where
     * <ul>
     * <li><code>Type</code> is the the type of the class
     * <li><code>ArgumentsList</code> is the arguments of the enum constant with some arguments replaced by the names of static final fields.
     * Arguments that match the initializer value of any public static final field are replaced by the name of that field.
     * <li><code>AnonymousClassDeclaration is the body of enum constant
     * </ul>
     * <p>
     * 
     * @param field
     * @param enumConstant
     */
    protected void setFieldInitializer(ASTJField field, ASTJEnumConstant enumConstant)
    {
      String[] arguments = enumConstant.getArguments();
      String body = enumConstant.getBody();

      ClassInstanceCreation classInstanceCreation = ast.newClassInstanceCreation();

      // set the type to create
      field.setNodeProperty(
        classInstanceCreation,
        enumConstant.getParent().getName(),
        ClassInstanceCreation.TYPE_PROPERTY,
        ASTNode.SIMPLE_TYPE);

      // set arguments
      if (arguments != null)
      {
        // replace some arguments by another field names (constants)
        for (int i = 0; i < arguments.length; i++)
        {
          if (arguments[i] != null && arguments[i].length() > 0)
          {
            ASTJField constantField = fieldInitializersMap.get(arguments[i]);
            if (constantField != null)
            {
              arguments[i] = constantField.getName();
              
              // update last field that is used
              if (lastFinalFieldUsed != null)
              {
                int constantFieldUsedIndex = fieldIndexesMap.get(constantField);
                int lastFinalFieldUsedIndex = fieldIndexesMap.get(lastFinalFieldUsed);
                if (constantFieldUsedIndex > lastFinalFieldUsedIndex)
                {
                  lastFinalFieldUsed = constantField;
                }
              }
              else
              {
                lastFinalFieldUsed = constantField;
              }
            }
          }
        }

        // set the arguments to the constructor
        field.setListNodeProperty(classInstanceCreation, arguments, ClassInstanceCreation.ARGUMENTS_PROPERTY, ASTNode.SIMPLE_NAME);
      }

      // set the body
      if (body != null && body.length() > 0)
      {
        field.setTrackedNodeProperty(
          classInstanceCreation,
          body,
          ClassInstanceCreation.ANONYMOUS_CLASS_DECLARATION_PROPERTY,
          ASTNode.ANONYMOUS_CLASS_DECLARATION);
      }

      // set the whole initializer
      field.setNodeProperty(field.getVariableDeclarationFragment(), classInstanceCreation, VariableDeclarationFragment.INITIALIZER_PROPERTY);
    }
  }
}
