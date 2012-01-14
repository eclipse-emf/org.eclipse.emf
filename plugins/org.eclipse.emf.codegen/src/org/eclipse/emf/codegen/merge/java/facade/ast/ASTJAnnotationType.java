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

import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;

import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.codegen.merge.java.facade.JAnnotationType;


/**
 * Wraps {@link AnnotationTypeDeclaration} object.
 * <p>
 * This class does not have any more functionality then {@link ASTJAbstractType}, but
 * it allows specify merge rules on annotation types in {@link JMerger}.
 */
public class ASTJAnnotationType extends ASTJAbstractType<AnnotationTypeDeclaration> implements JAnnotationType
{
  /**
   * @param annotationTypeDeclaration
   */
  public ASTJAnnotationType(AnnotationTypeDeclaration annotationTypeDeclaration)
  {
    super(annotationTypeDeclaration);
  }
}
