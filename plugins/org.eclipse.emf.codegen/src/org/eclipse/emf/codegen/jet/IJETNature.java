/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: IJETNature.java,v 1.2 2004/07/29 13:32:47 marcelop Exp $
 */
package org.eclipse.emf.codegen.jet;


import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProjectNature;


public interface IJETNature extends IProjectNature 
{
  /**
   * The unique identifier for the JET Nature.
   */
  public static final String NATURE_ID = "org.eclipse.emf.codegen.jet.IJETNature";

  /**
   * Returns the containers where the templates are located.
   * @return the containers where the templates are located.
   */
  public List getTemplateContainers();

  /**
   * Sets the containers where the templates are located.
   * @param templateContainers the new locations.
   */
  public void setTemplateContainers(List templateContainers);
  
  /**
   * Returns the container where the generated Java sources go.
   * @return the container where the generated Java sources go.
   */
  public IContainer getJavaSourceContainer();
  
  /**
   * Sets the container where the generated Java sources go.
   * @param javaSourceContainer the new location.
   */
  public void setJavaSourceContainer(IContainer javaSourceContainer);
}
