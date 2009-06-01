/**
 * <copyright>
 *
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ResourceProvider.java,v 1.2 2009/06/01 17:19:26 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;

import org.eclipse.emf.examples.databinding.project.core.IModelResource;
import org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip;
import org.eclipse.emf.examples.databinding.project.core.model.project.Project;


/**
 * Adds special variables to the application context
 */
public class ResourceProvider extends AbstractSourceProvider
{
  /**
   * The model resource variable
   */
  public static final String MODEL_RESOURCE_NAME = "modelresource";
  /**
   * The committer variable
   */
  public static final String COMMITTER_NAME = "committer";
  /**
   * The project variable
   */
  public static final String PROJECT_NAME = "project";

  private HashMap<String, Object> map = new HashMap<String, Object>();

  public void dispose()
  {
    map = null;
  }

  public Map< ? , ? > getCurrentState()
  {
    return map;
  }

  public String[] getProvidedSourceNames()
  {
    return new String []{ MODEL_RESOURCE_NAME, COMMITTER_NAME, PROJECT_NAME };
  }

  /**
   * Set the current model resource
   * @param resource the resource
   */
  public void setModelResource(IModelResource resource)
  {
    map.put(MODEL_RESOURCE_NAME, resource);
    fireSourceChanged(ISources.ACTIVE_PART, MODEL_RESOURCE_NAME, resource);
  }

  /**
   * Set the current committer
   * @param committer the committer
   */
  public void setCommitter(CommitterShip committer)
  {
    map.put(COMMITTER_NAME, committer);
    fireSourceChanged(ISources.ACTIVE_PART, COMMITTER_NAME, committer);
  }

  /**
   * Set the current project
   * @param project the project
   */
  public void setProject(Project project)
  {
    map.put(PROJECT_NAME, project);
    fireSourceChanged(ISources.ACTIVE_PART, PROJECT_NAME, project);
  }
}