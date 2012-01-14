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
  public List<Object> getTemplateContainers();

  /**
   * Sets the containers where the templates are located.
   * @param templateContainers the new locations.
   */
  public void setTemplateContainers(List<Object> templateContainers);
  
  /**
   * Returns the containers where the template sources are located.
   * @return the containers where the template sources are located.
   * @since 2.1.0
   */
  public List<Object> getTemplateSourceContainers();
  
  /**
   * Sets the containers where the template sources are located.
   * @param templateContainers the new locations.
   * @param templateSourceContainers the new source locations.
   * @since 2.1.0
   */
  public void setTemplateContainers(List<Object> templateContainers, List<Object> templateSourceContainers);
  
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
