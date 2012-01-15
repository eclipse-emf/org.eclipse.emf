/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.refactoring;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.ui.jvmmodel.refactoring.DefaultJvmModelRenameStrategy;


/**
 * Encapsulates the model changes of a rename refactoring.  
 */
public class XcoreRenameStrategy extends DefaultJvmModelRenameStrategy
{

  @Override
  protected void setInferredJvmElementName(String name, EObject renamedSourceElement)
  {
    /*
     * TODO: rename inferred elements as you would in IJvmModelInferrer 
     */
  }
}
