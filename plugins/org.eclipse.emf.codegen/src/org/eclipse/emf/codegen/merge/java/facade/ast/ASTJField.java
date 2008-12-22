/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: ASTJField.java,v 1.11 2008/12/22 14:25:31 emerks Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;

import org.eclipse.emf.codegen.merge.java.facade.JField;


/**
 * Represents a single variable in a field declaration.
 * <p>
 * If multiple variables are declared in the same field declaration, there is a unique
 * <code>ASTJField</code> for each variable. Each <code>ASTJField</code> has reference
 * to {@link FieldDeclaration} and {@link VariableDeclarationFragment}. Multiple <code>ASTJField</code>
 * can share the same {@link FieldDeclaration}.
 * <p>
 * If the field declaration referenced by <code>ASJField</code> has more than 1 variable,
 * calls to some <code>set...()</code> methods on the field and its children (e.g. annotations)
 * will result in a separation of the variable referenced by <code>ASTJField</code> from
 * the field declaration. The new declaration is inserted before the original declaration. 
 * After the separation, calls to <code>get...()</code> methods will no longer return the correct
 * original content.
 * 
 * @since 2.2.0
 */
public class ASTJField extends ASTJMember<FieldDeclaration> implements JField
{
  /**
   * Cached value of initializer of this field.
   * <p>
   * Note that change in initializer should not result in splitting variables in the field declaration.
   * <p>
   * If <code>setInitializer()</code> has been called, and <code>performSplit()</code> is
   * called after, then <code>performSplit()</code> should not overwrite the initializer.
   */  
  protected String initializer = UNITIALIZED_STRING;

  /**
   * Cached type of the field
   * @see #getType()
   * @see #setType(String)
   */
  protected String type = UNITIALIZED_STRING;
  
  /**
   * Original field declaration before the split.
   */
  private FieldDeclaration originalFieldDeclaration = null;  
  
  /**
   * Indicates whether the variable declaration fragment is the only fragment
   * in the field declaration.
   */
  protected boolean splitPerformed = false;
  
  /**
   * Variable declaration fragment that is wrapped by ASTJField and to be used by <code>set...</code>
   * and <code>get...</code> methods. Note that when field is removed, this variable is <b>not</b> changed.
   * On the other hand, wrappedVariableDeclarationFragment is updated to reflect current node in the tree.
   * <p>
   * Since the same {@link FieldDeclaration} can have multiple variables declared in it,
   * but {@link JField} object must be unique for 1 variable, each
   * ASTJField has reference to FieldDeclaration and {@link VariableDeclarationFragment}.
   * 
   * @see org.eclipse.jdt.core.dom.VariableDeclarationFragment
   */
  protected VariableDeclarationFragment variableDeclarationFragment;

  /**
   * Variable declaration fragment that is wrapped by ASTJField and reflects current node in the
   * rewritten tree that is used by this field.
   * <p>
   * Actual wrapped object is FieldDeclaration object, while VariableDeclarationFragment is stored
   * as an attribute of ASTJField and returned by {@link #getWrappedObject()}.
   * 
   * @see org.eclipse.jdt.core.dom.VariableDeclarationFragment
   */
  protected VariableDeclarationFragment wrappedVariableDeclarationFragment;

  /**
   * Sets wrapped object to {@link VariableDeclarationFragment}, and prepares variable
   * separation if required
   * 
   * @param variableDeclarationFragment must have parent of type {@link FieldDeclaration}
   * @param facadeHelper facade helper to use for this field, must not be <code>null</code>
   * @param rewriter to use, must not be <code>null</code>
   * @see #prepareSplit()
   */
  public ASTJField(VariableDeclarationFragment variableDeclarationFragment, ASTFacadeHelper facadeHelper, ASTRewrite rewriter)
  {
    super((FieldDeclaration)variableDeclarationFragment.getParent());
    this.originalFieldDeclaration = (FieldDeclaration)variableDeclarationFragment.getParent();
    this.variableDeclarationFragment = variableDeclarationFragment;
    this.wrappedVariableDeclarationFragment = variableDeclarationFragment;
    setFacadeHelper(facadeHelper);
    this.rewriter = rewriter;
    if (rewriter != null && getFacadeHelper() != null)
    {
      prepareSplit();
    }
  }
  
  @Override
  public void dispose()
  {
    originalFieldDeclaration = null;
    variableDeclarationFragment = null;
    wrappedVariableDeclarationFragment = null;
    initializer = null;
    type = null;    
    
    super.dispose();
  }
  
  public FieldDeclaration getOriginalFieldDeclaration()
  {
    return originalFieldDeclaration;
  }
  
  public VariableDeclarationFragment getWrappedVariableDeclarationFragment()
  {
    return wrappedVariableDeclarationFragment;
  }
  
  public VariableDeclarationFragment getVariableDeclarationFragment()
  {
    return variableDeclarationFragment;
  }  

  @Override
  public boolean addChild(ASTJNode<?> child)
  {
    performSplit();
    return super.addChild(child);
  }

  @Override
  public String getComment()
  {
    if (comment == UNITIALIZED_STRING)
    {
      comment = getFacadeHelper().toString(originalFieldDeclaration.getJavadoc());
    }
    return comment;
  }

  /**
   * May split the declaration same way as {@link ASTJField#setType(String)}.
   * <p>
   * If the declaration has been split, <code>getComment()</code> might not return the correct value.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJMember#setComment(java.lang.String)
   */
  @Override
  public void setComment(String comment)
  {
    // if there are multiple variables in declaration, 
    // separate this variable fragment into a separate declaration    
    performSplit();
    super.setComment(comment);
  }

  /**
   * Return the original declaration contents (including all variable declaration fragments in the
   * declaration).
   * <p>
   * If the declaration has been split, the returned value will not be correct.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#getContents()
   */
  @Override
  public String getContents()
  {
    // return the whole declaration contents (not just variableDeclarationFragment)
    return getFacadeHelper().toString(originalFieldDeclaration);
  }
  
  /**
   * Return original flags of the field declaration.
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJMember#getFlags()
   */
  @Override
  public int getFlags()
  {
    return originalFieldDeclaration.getModifiers();
  }  
  
  /**
   * May split the declaration same way as {@link ASTJField#setType(String)}
   * If the declaration has been split, <code>getFlags()</code> might not return 
   * the correct value.
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJMember#setFlags(int)
   */
  @Override
  public void setFlags(int flags)
  {
    // if there are multiple variables in declaration, 
    // separate this variable fragment into a separate declaration
    performSplit();
    super.setFlags(flags);
  }

  /**
   * Returns original initializer of variable declaration fragment.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JField#getInitializer()
   */
  public String getInitializer()
  {
    if (initializer == UNITIALIZED_STRING)
    {
      initializer = getFacadeHelper().toString(variableDeclarationFragment.getInitializer());
    }
    return initializer;
  }  

  /**
   * Sets initializer of variable declaration fragment.  This operation does 
   * not result in splitting fields.
   * @see org.eclipse.emf.codegen.merge.java.facade.JField#setInitializer(java.lang.String)
   */
  public void setInitializer(String initializer)
  {
    // indicate that initializer has been changed
    // (required to not overwrite initializer if variables are split later)
    this.initializer = initializer;
    
    setTrackedNodeProperty(
      variableDeclarationFragment,
      initializer,
      variableDeclarationFragment.getInitializerProperty(),
      ASTNode.INITIALIZER);
  }

  /**
   * Returns name of variable declaration fragment.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getName()
   */
  public String getName()
  {
    if (name == UNITIALIZED_STRING)
    {
      name = ASTFacadeHelper.toString(variableDeclarationFragment.getName());
    }
    return name;
  }

  /**
   * Sets name of variable declaration fragment.
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#setName(java.lang.String)
   */
  public void setName(String name)
  {
    this.name = name;
    setNodeProperty(variableDeclarationFragment, name, VariableDeclarationFragment.NAME_PROPERTY, ASTNode.SIMPLE_NAME);
  }

  /**
   * Returns original type of {@link FieldDeclaration}.
   * The dimensions declared after variable name are appended to the type.
   * @see org.eclipse.emf.codegen.merge.java.facade.JField#getType()
   */
  public String getType()
  {
    if (type == UNITIALIZED_STRING)
    {
      type = getFacadeHelper().toString(originalFieldDeclaration.getType());
  
      // append extra dimensions since they are not stored in Type object
      for (int i = 0; i < variableDeclarationFragment.getExtraDimensions(); i++)
      {
        type += "[]";
      }
    }    
    return type;
  }
  
  /**
   * Sets the type of {@link FieldDeclaration}.
   * <p>
   * If there is only one {@link VariableDeclarationFragment} in this declaration, 
   * only the type of the FieldDeclaration is changed.
   * <p>
   * If there are multiple {@link VariableDeclarationFragment}s in this declaration,
   * variable declaration fragment of this ASTJField is moved to a new
   * {@link FieldDeclaration}, and the type of new declaration is set. 
   * <p>
   * Note that if field declaration has been split, 
   * <code>getType()</code>, <code>getContents()</code>, <code>getComment()</code>,
   * <code>getInitializer()</code> will not return
   * the original content. 
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JField#setType(String)
   */
  public void setType(String type)
  {
    // if there are multiple variables in declaration, 
    // separate this variable fragment into a separate declaration
    performSplit();
    
    this.type = type;
    setNodeProperty(variableDeclarationFragment, 0, VariableDeclarationFragment.EXTRA_DIMENSIONS_PROPERTY);
    setTrackedNodeProperty(getASTNode(), type, FieldDeclaration.TYPE_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  @Override
  public boolean insertSibling(ASTJNode<?> node, ASTJNode<?> newSibling, boolean before)
  {
    performSplit();
    return super.insertSibling(node, newSibling, before);
  }
  
  @Override
  protected void childToBeChanged(ASTJNode<?> child)
  {
    performSplit();
  }

  /**
   * Ensures that the field wrapped by this ASTJField have only 1 variable in the declaration.
   * <p>
   * If required, this method creates a new field declaration, and sets rewritten AST node
   * to it. The ASTNode of all annotations of the new field is updated to use the original node.
   * <p>
   * Note that the no changes are added to the rewriter or wrapped object until {@link #performSplit()} is called.
   * <p>
   * This method must be called when field is created to ensure that annotations are unique for each <code>ASTJField</code>.
   * 
   * @see #performSplit()
   */
  protected void prepareSplit()
  {
    // check number of fragments
    ListRewrite listRewrite = rewriter.getListRewrite(originalFieldDeclaration, FieldDeclaration.FRAGMENTS_PROPERTY);
    List<?> fragments = listRewrite.getRewrittenList();
    if (splitPerformed || fragments.size() <= 1)
    {
      splitPerformed = true;
      return;
    }
  
    // create a copy of declaration
    FieldDeclaration newDeclaration = (FieldDeclaration)ASTNode.copySubtree(originalFieldDeclaration.getAST(), originalFieldDeclaration);
  
    // set original node of annotations (to allow get methods to work correctly)
    @SuppressWarnings("unchecked")
    Iterator<IExtendedModifier> newModifiersIterator = newDeclaration.modifiers().iterator();
    @SuppressWarnings("unchecked")
    Iterator<IExtendedModifier> originalModifiersIterator = originalFieldDeclaration.modifiers().iterator();
  
    for (; newModifiersIterator.hasNext() && originalModifiersIterator.hasNext();)
    {
      IExtendedModifier modifier = newModifiersIterator.next();
      IExtendedModifier originalModifier = originalModifiersIterator.next();
      if (originalModifier.isAnnotation())
      {
        Annotation annotation = (Annotation)modifier;
        Annotation originalAnnotation = (Annotation)originalModifier;
        ASTJAnnotation astjAnnotation = (ASTJAnnotation)getFacadeHelper().convertToNode(annotation);
        astjAnnotation.setRewriter(getRewriter());
        astjAnnotation.setASTNode(originalAnnotation);
      }
    }
  
    // new declaration will not have fragments until performSplit() is called
    newDeclaration.fragments().clear();
    setASTNode(newDeclaration);
  }

  /**
   * Revert the changes made by <code>prepareSplit()</code>.
   * @see #prepareSplit()
   */
  protected void revertPrepareSplit()
  {
    if (splitPerformed)
    {
      return;
    }
    
    @SuppressWarnings("unchecked")
    Iterator<IExtendedModifier> newModifiersIterator = getASTNode().modifiers().iterator();
    @SuppressWarnings("unchecked")
    Iterator<IExtendedModifier> originalModifiersIterator = originalFieldDeclaration.modifiers().iterator();
    
    for (; newModifiersIterator.hasNext() && originalModifiersIterator.hasNext();)
    {
      IExtendedModifier modifier = newModifiersIterator.next();
      IExtendedModifier originalModifier = originalModifiersIterator.next();
      if (originalModifier.isAnnotation())
      {
        Annotation annotation = (Annotation)modifier;
        Annotation originalAnnotation = (Annotation)originalModifier;
        ASTJAnnotation astjAnnotation = (ASTJAnnotation)getFacadeHelper().convertToNode(annotation);
        astjAnnotation.setRewrittenASTNode(originalAnnotation);
        astjAnnotation.setWrappedObject(originalAnnotation);
        getFacadeHelper().updateObjectToNodeMap(astjAnnotation);
      }
    }
  
    // set rewritten node to be the original
    setASTNode(originalFieldDeclaration);
    splitPerformed = true;
  }

  /**
   * If required, separates the variable declaration fragment into a new {@link FieldDeclaration}
   * object. If this declaration does not need to be split, reverts the changes made by {@link #prepareSplit()}.
   * <p>
   * New field declaration will have only one variable declaration fragment. 
   * New declaration is added to the {@link ASTRewrite}.
   * The attributes of this ASTJField are updated to reference elements of the new declaration.
   * Only the javadoc, variable initializer, and annotations are copied as String, all other attributes are copied
   * using {@link ASTNode#copySubtree(org.eclipse.jdt.core.dom.AST, ASTNode)}. All
   * formatting except for Javadoc, initializer, and annotations is lost.
   * If field declaration wrapped by ASTJField has only one variable declaration
   * fragment left, no changes are made.
   * <p>
   * Note that this method must be called after {@link #prepareSplit()} and before any
   * changes are made to the nodes.
   * 
   * @see #prepareSplit()
   */
  protected void performSplit()
  {
    if (!splitPerformed && getASTNode() != originalFieldDeclaration)
    {
      ListRewrite listRewrite = rewriter.getListRewrite(originalFieldDeclaration, FieldDeclaration.FRAGMENTS_PROPERTY);
      List<?> fragments = listRewrite.getRewrittenList();
      
      // perform split if there is more than 1 fragment
      if (fragments.size() > 1)
      {
        FieldDeclaration newDeclaration = getASTNode();
  
        // set javadoc
        if (originalFieldDeclaration.getJavadoc() != null)
        {
          String javadocString = getFacadeHelper().applyFormatRules(getFacadeHelper().toString(originalFieldDeclaration.getJavadoc()));
          setTrackedNodeProperty(newDeclaration, javadocString, newDeclaration.getJavadocProperty(), ASTNode.JAVADOC);
        }
  
        // set initializer
        if (variableDeclarationFragment.getInitializer() != null)
        {
          if (initializer == UNITIALIZED_STRING)
          {
            initializer = getFacadeHelper().applyFormatRules(getFacadeHelper().toString(variableDeclarationFragment.getInitializer()));
          }
          setTrackedNodeProperty(
            variableDeclarationFragment,
            initializer,
            VariableDeclarationFragment.INITIALIZER_PROPERTY,
            ASTNode.JAVADOC);
        }
  
        // set annotations contents
        @SuppressWarnings("unchecked")
        Iterator<IExtendedModifier> newModifiersIterator = newDeclaration.modifiers().iterator();
        @SuppressWarnings("unchecked")
        Iterator<IExtendedModifier> originalModifiersIterator = originalFieldDeclaration.modifiers().iterator();        
        
        for (; newModifiersIterator.hasNext() && originalModifiersIterator.hasNext();)
        {
          IExtendedModifier modifier = newModifiersIterator.next();
          IExtendedModifier originalModifier = originalModifiersIterator.next();
          if (originalModifier.isAnnotation())
          {
            ASTJAnnotation astjAnnotation = (ASTJAnnotation)getFacadeHelper().convertToNode(modifier);
            astjAnnotation.trackAndReplace(
              astjAnnotation.getRewrittenASTNode(),
              getFacadeHelper().applyFormatRules(getFacadeHelper().toString(originalModifier)));
          }
        }
  
        // insert new declaration before this one
        listRewrite = rewriter.getListRewrite(originalFieldDeclaration.getParent(), (ChildListPropertyDescriptor)originalFieldDeclaration.getLocationInParent());
        listRewrite.insertBefore(newDeclaration, originalFieldDeclaration, null);
  
        // update the wrapped object
        setWrappedObject(newDeclaration);
  
        // delete variable fragment from old declaration
        removeNodeFromListProperty(originalFieldDeclaration, variableDeclarationFragment, FieldDeclaration.FRAGMENTS_PROPERTY);
  
        // add variable fragment to new declaration
        ListRewrite newListRewrite = rewriter.getListRewrite(newDeclaration, FieldDeclaration.FRAGMENTS_PROPERTY);
        newListRewrite.insertFirst(variableDeclarationFragment, null);
      }
      else
      {
        // only 1 fragment left - revert the changes
        revertPrepareSplit();
      }
    }
    // split is performed
    splitPerformed = true;          
  }

  @Override
  public boolean remove(ASTJNode<?> node)
  {
    performSplit();
    return super.remove(node);
  }

  /**
   * Sets wrapped object to be the given FieldDeclaration, 
   * and sets wrappedVariableDeclarationFragment attribute
   * to be the first variable declaration fragment in the given FieldDeclaration.
   * <p>
   * This method is mainly used by {@link #remove(ASTJNode)} to create a move target to allow
   * insertion of the nodes later.
   * 
   * @param node must be of type FieldDeclaration, ignored otherwise
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#setWrappedObject(org.eclipse.jdt.core.dom.ASTNode)
   */
  @Override
  protected void setWrappedObject(ASTNode node)
  {
    if (node instanceof FieldDeclaration)
    {
      super.setWrappedObject(node);
      FieldDeclaration fieldDeclaration = (FieldDeclaration)node;
      if (fieldDeclaration.fragments().size() > 0)
      {
        wrappedVariableDeclarationFragment = (VariableDeclarationFragment)fieldDeclaration.fragments().get(0);
      }
    }
  }

  /**
   * Commenting out a field results in splitting of the fields, and then commenting out
   * only the field that is commented out.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#commentOut()
   */
  @Override
  public void commentOut()
  {
    performSplit();
    super.commentOut();
  }
}
