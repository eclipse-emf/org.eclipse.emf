/**
 * Copyright (c) 2005-2013 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */

package org.eclipse.emf.codegen.ecore.genmodel.util;

import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaProject;

/**
 * This class contains convenient static methods for working with GenModel objects.
 * @since 2.2.0
 */
public class GenModelUtil
{
  public static String getAnnotation(GenBase genBase, String sourceURI, String key)
  {
    GenAnnotation genAnnotation = genBase.getGenAnnotation(sourceURI);
    return genAnnotation == null ? null : (String)genAnnotation.getDetails().get(key);
  }

  public static void setAnnotation(GenBase genBase, String sourceURI, String key, String value)
  {
    GenAnnotation genAnnotation = genBase.getGenAnnotation(sourceURI);
    if (genAnnotation == null)
    {
      genAnnotation = GenModelFactory.eINSTANCE.createGenAnnotation();
      genAnnotation.setSource(sourceURI);
      genBase.getGenAnnotations().add(genAnnotation);
    }
    genAnnotation.getDetails().put(key, value);
  }

  private static IJavaProject getJavaProject(GenModel genModel)
  {
    // Try to get the Java project for the target model directory...
    //
    String modelDirectory = genModel.getModelDirectory();
    try
    {
      URI uri = URI.createURI(modelDirectory);
      IJavaProject javaProject = getJavaProject(uri);
      if (javaProject != null)
      {
        return javaProject;
      }
    }
    catch (Throwable throwable)
    {
      // Ignore.
    }

    // Failing that, try to get the Java project from the GenModel's resource's project.
    //
    Resource resource = genModel.eResource();
    if (resource != null)
    {
      return getJavaProject(resource.getURI());
    }
    return null;
  }

  private static IJavaProject getJavaProject(URI uri)
  {
    String projectName = null;
    if (uri != null)
    {
      if (uri.isPlatformResource())
      {
        projectName = uri.segment(1);
      }
      else if (uri.isHierarchical() && uri.segmentCount() > 1)
      {
        projectName = uri.segment(0);
      }
    }

    if (projectName != null)
    {
      IWorkspaceRoot workspaceRoot = EcorePlugin.getWorkspaceRoot();
      if (workspaceRoot != null)
      {
        IProject project = workspaceRoot.getProject(projectName);
        try
        {
          if (project.isAccessible() && project.hasNature(JavaCore.NATURE_ID))
          {
            return JavaCore.create(project);
          }
        }
        catch (CoreException exception)
        {
          // Ignore.
        }
      }
    }
    return null;
  }

  /**
   * @since 2.9,
   */
  public static Map<String, String> getJavaOptions(GenModel genModel)
  {
    IJavaProject javaProject = getJavaProject(genModel);
    @SuppressWarnings("unchecked")
    Map<String, String> options = javaProject != null ? javaProject.getOptions(true) : JavaCore.getOptions();
    return options;
  }

  /**
   * @since 2.5
   */
  public static Generator createGenerator(GenModel genModel)
  {
    Generator generator = new Generator();
    generator.setInput(genModel);
    JControlModel jControlModel = generator.getJControlModel();

    if (genModel.isCodeFormatting())
    {
      jControlModel.setLeadingTabReplacement(null);
      jControlModel.setConvertToStandardBraceStyle(false);
    }
    else
    {
      Map<String, String> options = getJavaOptions(genModel);
      String tabSize = options.get(DefaultCodeFormatterConstants.FORMATTER_TAB_SIZE);
      String braceStyle = options.get(DefaultCodeFormatterConstants.FORMATTER_BRACE_POSITION_FOR_TYPE_DECLARATION);
      String tabCharacter = options.get(DefaultCodeFormatterConstants.FORMATTER_TAB_CHAR);
      if (JavaCore.TAB.equals(tabCharacter))
      {
        jControlModel.setLeadingTabReplacement("\t");
      }
      else
      {
        String spaces = "";
        for (int i = Integer.parseInt(tabSize); i > 0; --i)
        {
          spaces += " ";
        }
        jControlModel.setLeadingTabReplacement(spaces);
      }
      jControlModel.setConvertToStandardBraceStyle(DefaultCodeFormatterConstants.END_OF_LINE.equals(braceStyle));
    }
    return generator;
  }  
}
