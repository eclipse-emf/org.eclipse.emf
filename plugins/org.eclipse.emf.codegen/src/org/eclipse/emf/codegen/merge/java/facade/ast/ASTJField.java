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
 * $Id: ASTJField.java,v 1.2 2006/11/01 21:31:43 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;


import java.util.List;

import org.eclipse.emf.codegen.merge.java.facade.JField;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;


/**
 * Represents a single variable in a field declaration.
 * <p>
 * If multiple variables are declared in the same field declaration, there is a unique
 * <code>ASTJField</code> for each variable. Each <code>ASTJField</code> has reference
 * to {@link FieldDeclaration} and {@link VariableDeclarationFragment}. Multiple <code>ASTJField</code>
 * can share the same {@link FieldDeclaration}.
 * <p>
 * If the field declaration referenced by <code>ASJField</code> has more than 1 variable,
 * calls to some <code>set...()</code> methods will result in a separation of the variable referenced
 * by <code>ASTJField</code> from
 * the field declaration. The new declaration is inserted before the original declaration. 
 * After the separation, calls to <code>get...()</code> methods will no longer return the correct
 * original content. 
 * <p>
 * Methods that will result in a separation of the variable are 
 * <code>setComment()</code>, <code>setType()</code>, and <code>setFlags()</code>.
 * 
 * @since 2.2.0
 */
public class ASTJField extends ASTJMember implements JField
{
  /**
   * Variable declaration fragment that is wrapped by ASTJField.
   * <p>
   * Since the same {@link FieldDeclaration} can have multiple variables declared in it,
   * but {@link JField} object must be unique for 1 variable, each
   * ASTJField has reference to FieldDeclaration and {@link VariableDeclarationFragment}.
   * <p>
   * Wrapped object is FieldDeclaration object, while VariableDeclarationFragment is stored
   * as an attribute of ASTJField.
   * 
   * @see org.eclipse.jdt.core.dom.VariableDeclarationFragment
   */
  protected VariableDeclarationFragment variableDeclarationFragment;
  
  /**
   * Sets wrapped object to {@link VariableDeclarationFragment}
   * 
   * @param variableDeclarationFragment must have parent of type {@link FieldDeclaration}
   */
  public ASTJField(VariableDeclarationFragment variableDeclarationFragment)
  {
    super((FieldDeclaration)variableDeclarationFragment.getParent());
    this.variableDeclarationFragment = variableDeclarationFragment;
  }

  /**
   * Returns {@link VariableDeclarationFragment} associated with this ASTJField
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#getASTNode()
   */
  @Override
  protected ASTNode getASTNode()
  {
    return variableDeclarationFragment;
  }
  
  /**
   * Sets wrapped object to be the given FieldDeclaration, 
   * and sets variableDeclarationFragment attribute
   * to be the first variable declaration fragment in the given FieldDeclaration.
   * 
   * @param node must be of type FieldDeclaration, ignored otherwise
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#setASTNode(org.eclipse.jdt.core.dom.ASTNode)
   */
  @Override
  protected void setASTNode(ASTNode node)
  {
    if (node instanceof FieldDeclaration)
    {
      super.setASTNode(node);
      FieldDeclaration fieldDeclaration = (FieldDeclaration)node;
      if (fieldDeclaration.fragments().size() > 0)
      {
        variableDeclarationFragment = (VariableDeclarationFragment)fieldDeclaration.fragments().get(0);
      }
    }
  }
  
  /**
   * Returns wrapped object as BodyDeclaration object.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJMember#getASTBodyDeclaration()
   */
  @Override
  protected BodyDeclaration getASTBodyDeclaration()
  {
    return (BodyDeclaration)super.getASTNode();
  }

  /**
   * @return field declaration wrapped by this node
   */
  protected FieldDeclaration getASTFieldDeclaration()
  {
    return (FieldDeclaration)getASTBodyDeclaration();
  }

  /**
   * Returns name of variable declaration fragment.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getName()
   */
  public String getName()
  {
    return ASTFacadeHelper.toString(variableDeclarationFragment.getName());
  }  

  /**
   * Returns original initializer of variable declaration fragment.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JField#getInitializer()
   */
  public String getInitializer()
  {
    return facadeHelper.toString(variableDeclarationFragment.getInitializer());
  }

  /**
   * Sets initializer of variable declaration fragment.
   * <p>
   * Note that <code>getInitializer()</code> will not return the new value.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JField#setInitializer(java.lang.String)
   */
  public void setInitializer(String initializer)
  {
    setTrackedNodeProperty(variableDeclarationFragment, initializer, variableDeclarationFragment.getInitializerProperty(), ASTNode.INITIALIZER);
  }

  /**
   * Returns original type of {@link FieldDeclaration}.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JField#getType()
   */
  public String getType()
  {
    String type = facadeHelper.toString(getASTFieldDeclaration().getType());
    
    // append extra dimensions since they are not stored in Type object
    for (int i = 0; i < variableDeclarationFragment.getExtraDimensions(); i++)
    {
      type += "[]";
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
   * @see org.eclipse.emf.codegen.merge.java.facade.JField#setType(java.lang.Object)
   */
  public void setType(String type)
  {
    // if there are multiple variables in declaration, 
    // separate this variable fragment into a separate declaration
    separateVariableDeclarationFragment();
    
    FieldDeclaration fd = getASTFieldDeclaration();    
    
    setNodeProperty(variableDeclarationFragment, new Integer(0), VariableDeclarationFragment.EXTRA_DIMENSIONS_PROPERTY);
    setTrackedNodeProperty(fd, type, FieldDeclaration.TYPE_PROPERTY, ASTNode.SIMPLE_TYPE);
  }
  
  /**
   * Separates the variable declaration fragment into a new {@link FieldDeclaration}
   * object.
   * <p>
   * New field declaration will have only one variable declaration fragment. 
   * New declaration is added to the {@link ASTRewrite}.
   * The attributes of this ASTJField are updated to reference elements of the new declaration.
   * Only the {@link Javadoc} attribute is copied as String, all other attributes are copied
   * using {@link ASTNode#copySubtree(org.eclipse.jdt.core.dom.AST, ASTNode)} and all
   * formatting except for Javadoc is lost.
   * If field declaration wrapped by ASTJField has only one variable declaration
   * fragment, no changes are made.
   * <p>
   * Note that if field declaration has been split, 
   * <code>getType()</code>, <code>getContents()</code>, <code>getComment()</code>,
   * <code>getInitializer()</code> will not return
   * the original content. 
   */
  protected void separateVariableDeclarationFragment()
  {
    FieldDeclaration fd = getASTFieldDeclaration();
    
    // check number of fragments
    ListRewrite listRewrite = rewriter.getListRewrite(fd, FieldDeclaration.FRAGMENTS_PROPERTY);    
    List<?> fragments = listRewrite.getRewrittenList();
    if (fragments.size() <= 1)
    {
      return;
    }
    
    // create a copy of declaration
    FieldDeclaration newDeclaration = (FieldDeclaration)ASTNode.copySubtree(fd.getAST(), fd);
    if (fd.getJavadoc() != null)
    {
      String javadocString = facadeHelper.applyFormatRules(facadeHelper.toString(fd.getJavadoc()));
      setNodeProperty(newDeclaration, javadocString, newDeclaration.getJavadocProperty(), ASTNode.JAVADOC);
    }
    
    newDeclaration.fragments().clear();
    
    // insert new declaration before this one
    insert(newDeclaration, fd, true); 

    // delete variable from old declaration
    removeNodeFromListProperty(fd, variableDeclarationFragment, FieldDeclaration.FRAGMENTS_PROPERTY);

    // add fragment to new declaration
    ListRewrite newListRewrite = rewriter.getListRewrite(newDeclaration, FieldDeclaration.FRAGMENTS_PROPERTY);
    newListRewrite.insertFirst(variableDeclarationFragment, null);
    
    // update declaration reference
    wrappedObject = newDeclaration;
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
    return facadeHelper.toString(getASTFieldDeclaration());
  }
  
  /**
   * May split the declaration same way as {@link ASTJField#setType(Object)}
   * <p>
   * If the declaration has been split, <code>getFlags()</code> might not return the correct value.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJMember#setFlags(int)
   */
  @Override
  public void setFlags(int flags)
  {
    // if there are multiple variables in declaration, 
    // separate this variable fragment into a separate declaration
    separateVariableDeclarationFragment();
    super.setFlags(flags);
  }
  
  /**
   * May split the declaration same way as {@link ASTJField#setType(Object)}.
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
    separateVariableDeclarationFragment();
    super.setComment(comment);
  }
}
