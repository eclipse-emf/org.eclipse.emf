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
 * $Id: GenBaseImpl.java,v 1.14 2004/11/01 21:14:42 davidms Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.core.jdom.DOMFactory;
import org.eclipse.jdt.core.jdom.IDOMCompilationUnit;
import org.eclipse.jdt.core.jdom.IDOMImport;
import org.eclipse.jdt.core.jdom.IDOMNode;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.jet.JETCompiler;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.codegen.jmerge.JMerger;
import org.eclipse.emf.codegen.jmerge.PropertyMerger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Base</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class GenBaseImpl extends EObjectImpl implements GenBase
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  protected GenBaseImpl() 
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return GenModelPackage.eINSTANCE.getGenBase();
  }

  protected static HashSet javaReservedWords;
  
  protected static HashSet javaLangTypes;
  
  public GenModel getGenModel()
  {
    if (this instanceof GenModel)
      return (GenModel)this;
    else
      return ((GenBase)eContainer()).getGenModel();
  }

  public abstract String getName();

  public String capName(String name)
  {
    if (name.length() == 0)
      return name;
    else
      return name.substring(0,1).toUpperCase() + name.substring(1);
  }

  public String uncapName(String name)
  {
    if (name.length() == 0)
      return name;
    else
      return name.substring(0,1).toLowerCase() + name.substring(1);
  }

  public String uncapPrefixedName(String name)
  {
    // lower all except the last upper case character if there are
    // more than one upper case characters in the beginning.
    // e.g. XSDElementContent -> xsdElementContent
    // However if the whole string is uppercase, the whole string
    // is turned into lower case.
    // e.g. CPU -> cpu
    if (name.length() == 0)
    {
      return name;
    }
    else 
    {
      String lowerName = name.toLowerCase();
      int i;
      for (i = 0; i < name.length(); i++) 
      {
        if (name.charAt(i) == lowerName.charAt(i)) 
        {
          break;
        }
      }
      if (i > 1 && i < name.length()) 
      {
        --i;
      }
      return name.substring(0, i).toLowerCase() + name.substring(i);
    }
  }

  public String safeName(String name)
  {
    if (getJavaReservedWords().contains(name)) return name + "_";
    return name;
  }

  protected String getImplClassName(String interfaceName)
  {
    return interfaceName + "Impl";
  }

  public boolean canGenerate()
  {
    return getGenModel() != null && getGenModel().canGenerate() && hasModelContribution();
  }

  protected boolean hasModelContribution()
  {
    return false;
  }

  public void generate(IProgressMonitor progressMonitor)
  {
  }

  protected void generate(IProgressMonitor progressMonitor, int style, List pluginVariables, String outputFilePath, JETEmitter jetEmitter)
  {
    try
    {
      IPath outputPath = new Path(outputFilePath.substring(0, outputFilePath.lastIndexOf("/")));
      progressMonitor.beginTask("", 3);
      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingFile_message", new Object [] { outputFilePath }));
      IContainer container = findOrCreateContainer(new SubProgressMonitor(progressMonitor, 1), style, pluginVariables, outputPath, false);
      if (container != null)
      {
        IFile targetFile = container.getFile(new Path(outputFilePath.substring(outputFilePath.lastIndexOf("/") + 1)));
        if (targetFile.exists() && (outputFilePath.endsWith("/build.properties") || !outputFilePath.endsWith(".properties")))
        {
          return;
        }

        boolean changed = false;
        boolean isUnicodeEscapeEncoded = outputFilePath.endsWith(".properties");
        String emitterResult = jetEmitter.generate(new SubProgressMonitor(progressMonitor, 1), new Object [] { this });
        if (isUnicodeEscapeEncoded)
        {
          emitterResult = unicodeEscapeEncode(emitterResult);
        }

        progressMonitor.worked(1);
        InputStream contents = new ByteArrayInputStream(emitterResult.toString().getBytes(isUnicodeEscapeEncoded ? "ISO-8859-1" : "UTF-8"));
        if (targetFile.exists())
        {
          // Don't overwrite exising file
          PropertyMerger propertyMerger = new PropertyMerger();
          propertyMerger.setSourceProperties(emitterResult);
          progressMonitor.subTask
            (CodeGenEcorePlugin.INSTANCE.getString("_UI_ExaminingOld_message", new Object [] { targetFile.getFullPath() }));
          String oldProperties = propertyMerger.createPropertiesForInputStream(targetFile.getContents(true));
          propertyMerger.setTargetProperties(oldProperties);
          progressMonitor.subTask
            (CodeGenEcorePlugin.INSTANCE.getString("_UI_PreparingNew_message", new Object [] { targetFile.getFullPath() }));
          propertyMerger.merge();
          progressMonitor.worked(1);

          String mergedResult = propertyMerger.getTargetProperties();
          changed = !mergedResult.equals(oldProperties);
          if (changed)
          {
            if (targetFile.isReadOnly() && 
                  targetFile.getWorkspace().validateEdit(new IFile [] { targetFile }, new SubProgressMonitor(progressMonitor, 1)).isOK())
            {
              propertyMerger.setTargetProperties(propertyMerger.createPropertiesForInputStream(targetFile.getContents(true)));
              propertyMerger.merge();
              mergedResult = propertyMerger.getTargetProperties();
            }

            contents = new ByteArrayInputStream(mergedResult.getBytes(isUnicodeEscapeEncoded ? "ISO-8859-1" : "UTF-8"));
          }
        }
        else
        {
          changed = true;
        }

        if (changed)
        {
          String redirection = getGenModel().getRedirection();
          boolean redirect = redirection != null && redirection.indexOf("{0}") != -1;

          // Use an alternate if we can't write to this one.
          //
          if (redirect)
          {
            String baseName = MessageFormat.format(redirection, new Object [] { targetFile.getName() });
            targetFile = container.getFile(new Path(baseName));
            progressMonitor.subTask
              (CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingAlternate_message", new Object [] { targetFile.getFullPath() }));
          }
  
          if (targetFile.isReadOnly())
          {
            if (getGenModel().isForceOverwrite())
            {
              targetFile.setReadOnly(false);
            }
            else
            {
              targetFile = container.getFile(new Path("." + targetFile.getName() + ".new"));
              progressMonitor.subTask
                (CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingDefaultAlternate_message", new Object [] { targetFile.getFullPath() }));
            }
          }
  
          if (targetFile.exists())
          {
            targetFile.setContents(contents, true, true, new SubProgressMonitor(progressMonitor, 1));
          }
          else
          {
            targetFile.create(contents, true, new SubProgressMonitor(progressMonitor, 1));
          }
        }
      }
    }
    catch (Exception exception)
    {
      CodeGenEcorePlugin.INSTANCE.log(exception);
    }
    finally
    {
      progressMonitor.done();
    }
  }

  protected void generate
    (IProgressMonitor progressMonitor, 
     int style, 
     List pluginVariables,
     String outputFilePath, 
     GIFEmitter gifEmitter, 
     String key)
  {
    generate(progressMonitor, style, pluginVariables, outputFilePath, gifEmitter, key, null);
  }

  protected void generate
    (IProgressMonitor progressMonitor, 
     int style, 
     List pluginVariables,
     String outputFilePath, 
     GIFEmitter gifEmitter, 
     String parentKey, 
     String childKey)
  {
    try
    {
      IPath outputPath = new Path(outputFilePath.substring(0, outputFilePath.lastIndexOf("/")));
      progressMonitor.beginTask("", 3);
      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingImage_message", new Object [] { outputFilePath }));
      IContainer container = 
        findOrCreateContainer(new SubProgressMonitor(progressMonitor, 1), style, pluginVariables, outputPath, false);
      if (container != null)
      {
        IFile targetFile = container.getFile(new Path(outputFilePath.substring(outputFilePath.lastIndexOf("/") + 1)));
        if (targetFile.exists())
        {
          // Don't overwrite exising file
        }
        else
        {
          byte[] emitterResult = gifEmitter.generateGIF(parentKey, childKey);
          progressMonitor.worked(1);
          InputStream contents = new ByteArrayInputStream(emitterResult);
          targetFile.create(contents, true, new SubProgressMonitor(progressMonitor, 1));
        }
      }
    }
    catch (Exception exception)
    {
      CodeGenEcorePlugin.INSTANCE.log(exception);
    }
    progressMonitor.done();
  }

  protected void generate
    (IProgressMonitor progressMonitor, 
     int style, 
     List pluginVariables,
     String targetDirectory, 
     String packageName, 
     String className, 
     JETEmitter jetEmitter)
  {
    try
    {
      IPath outputPath = new Path(targetDirectory + "/" + packageName.replace('.','/'));
      progressMonitor.beginTask("", 4);
      IContainer container = findOrCreateContainer(new SubProgressMonitor(progressMonitor, 1), style, pluginVariables, outputPath, false);
      if (container != null)
      {
        // Create an import manager for this compilation unit
        ImportManager importManager = new ImportManager(packageName);
        importManager.addMasterImport(packageName, className);
        setImportManager(importManager);

        // Create a code formatter for this compilation unit, if needed
        CodeFormatter codeFormatter = getGenModel().isCodeFormatting() ? getGenModel().createCodeFormatter() : null;

        String emitterResult = jetEmitter.generate(new SubProgressMonitor(progressMonitor, 1), new Object [] { this });
        progressMonitor.worked(1);

        boolean changed = true;
        IFile targetFile = container.getFile(new Path(className + ".java"));
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString("_UI_Generating_message", new Object [] { targetFile.getFullPath()}));
        JMerger jMerger = new JMerger();
        jMerger.setControlModel(getGenModel().getJControlModel());
        jMerger.setSourceCompilationUnit(jMerger.createCompilationUnitForContents(emitterResult));
        String newContents = null;
        if (targetFile.exists())
        {
          progressMonitor.subTask
            (CodeGenEcorePlugin.INSTANCE.getString("_UI_ExaminingOld_message", new Object [] { targetFile.getFullPath() }));
          jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(targetFile.getContents(true)));
          String oldContents = jMerger.getTargetCompilationUnitContents();

          progressMonitor.subTask
            (CodeGenEcorePlugin.INSTANCE.getString("_UI_PreparingNew_message", new Object [] { targetFile.getFullPath() }));
          jMerger.merge();
          progressMonitor.worked(1);

          newContents = formatCode(jMerger.getTargetCompilationUnitContents(), codeFormatter);
          changed = !oldContents.equals(newContents);
          if (changed)
          {
            if (targetFile.isReadOnly() && 
                  targetFile.getWorkspace().validateEdit(new IFile [] { targetFile }, new SubProgressMonitor(progressMonitor, 1)).isOK())
            {
              jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(targetFile.getContents(true)));
              jMerger.remerge();
              newContents = formatCode(jMerger.getTargetCompilationUnitContents(), codeFormatter);
            }
          }
        }
        else
        {
          changed = true;
          progressMonitor.subTask
            (CodeGenEcorePlugin.INSTANCE.getString("_UI_PreparingNew_message", new Object [] { targetFile.getFullPath() }));
          jMerger.merge();
          progressMonitor.worked(1);
          newContents = formatCode(jMerger.getTargetCompilationUnitContents(), codeFormatter);
        }

        if (changed)
        {
          //purpose: using charset from 'targetFile' to encode in-memory 
          //         'newContents' object into bytes
          //modifer: Wu Zhi Qiang
          //date:    Aug 25, 2004
          //action:  first get the charset from 'targetFile', then use it 
          //         to encode the 'newContents' object into bytes
          String encoding = null;
          try
          {
            encoding = targetFile.getCharset();
          }
          catch (CoreException ce)
          {
            // use no encoding
          }
          byte[] bytes = encoding == null 
            ? newContents.getBytes() 
            : newContents.getBytes(encoding);

          InputStream contents = new ByteArrayInputStream(bytes);

          String redirection = getGenModel().getRedirection();
          boolean redirect = redirection != null && redirection.indexOf("{0}") != -1;

          // Use an alternate if we can't write to this one.
          //
          if (redirect)
          {
            String baseName = MessageFormat.format(redirection, new Object [] { className + ".java" });
            targetFile = container.getFile(new Path(baseName));
            progressMonitor.subTask
              (CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingAlternate_message", new Object [] { targetFile.getFullPath() }));
          } 

          if (targetFile.isReadOnly())
          {
            if (getGenModel().isForceOverwrite())
            {
              targetFile.setReadOnly(false);
            }
            else
            {
              targetFile = container.getFile(new Path("." + className + ".java.new"));
              progressMonitor.subTask
                (CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingDefaultAlternate_message", new Object [] { targetFile.getFullPath() }));
            }
          }

          if (targetFile.exists())
          {
            targetFile.setContents(contents, true, true, new SubProgressMonitor(progressMonitor, 1));
          }
          else
          {
            targetFile.create(contents, true, new SubProgressMonitor(progressMonitor, 1));
          }
        }
      }
    }
    catch (JETException exception)
    {
      CodeGenEcorePlugin.INSTANCE.log(exception);
    }
    catch (Exception exception)
    {
      CodeGenEcorePlugin.INSTANCE.log(exception);
    }

    // Clear the import manager
    setImportManager(null);

    progressMonitor.done();
  }

  protected ImportManager getImportManager()
  {
    return ((GenBaseImpl)getGenModel()).getImportManager();
  }

  protected void setImportManager(ImportManager importManager)
  {
    GenModelImpl genModel = (GenModelImpl)getGenModel();
    if (genModel != null)
    {
      genModel.setImportManager(importManager);

      // EATM Kind of ugly...
      //
      if (ecoreGenPackage != null && ((GenPackageImpl)ecoreGenPackage).getImportManager() != importManager)
      {
        ((GenPackageImpl)ecoreGenPackage).setImportManager(getImportManager());
      }
      if (xmlTypeGenPackage != null && ((GenPackageImpl)xmlTypeGenPackage).getImportManager() != importManager)
      {
        ((GenPackageImpl)xmlTypeGenPackage).setImportManager(getImportManager());
      }
      if (xmlNamespaceGenPackage != null && ((GenPackageImpl)xmlNamespaceGenPackage).getImportManager() != importManager)
      {
        ((GenPackageImpl)xmlNamespaceGenPackage).setImportManager(getImportManager());
      }
    }
  }

  protected IContainer findOrCreateContainer
    (IProgressMonitor progressMonitor, int style, List pluginVariables, IPath outputPath, boolean forceStyle)
  {
    IContainer container = null;
    try
    {
      progressMonitor.beginTask("", outputPath.segmentCount() + 1);
      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_OpeningFolder_message", new Object [] { outputPath }));
      if (outputPath.isAbsolute())
      {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IProject project = workspace.getRoot().getProject(outputPath.segment(0));
        if (forceStyle || !project.exists())
        {
          IPath projectLocation = null;

          List referencedProjects = new UniqueEList();
          if (project.exists())
          {
            referencedProjects.addAll(Arrays.asList(project.getDescription().getReferencedProjects()));
            projectLocation = project.getDescription().getLocation();
          }
          else
          {
            URI genModelURI = getGenModel().eResource().getURI();
            if (genModelURI.toString().startsWith("platform:/resource/"))
            {
              IProject genModelProject = workspace.getRoot().getProject(genModelURI.segments()[1]);
              projectLocation = genModelProject.getDescription().getLocation();
            }
          }

          IProject modelProject = workspace.getRoot().getProject(getGenModel().getModelProjectDirectory());
          IPath javaSource = new Path(getGenModel().getModelDirectory());
          if ((style & Generator.EMF_MODEL_PROJECT_STYLE) == 0 && getGenModel().hasEditSupport())
          {
            IProject editProject = workspace.getRoot().getProject(getGenModel().getEditProjectDirectory());

            if (!getGenModel().sameModelEditProject())
            {
              IPath modelDirectory = javaSource;
              javaSource = new Path(getGenModel().getEditDirectory());
              if (editProject.exists())
              {
                projectLocation = editProject.getDescription().getLocation();
              }

              referencedProjects.add(modelProject);
            }

            for (Iterator i = getGenModel().getUsedGenPackages().iterator(); i.hasNext(); )
            {
              GenModel otherGenModel = ((GenPackage)i.next()).getGenModel();
              if (otherGenModel.hasEditSupport())
              {
                IProject otherEditProject = workspace.getRoot().getProject(otherGenModel.getEditProjectDirectory());
                if (otherEditProject.exists())
                {
                  referencedProjects.add(otherEditProject);
                  for (Iterator j = Arrays.asList(otherEditProject.getDescription().getReferencedProjects()).iterator(); j.hasNext(); )
                  {
                    IProject otherEditReferencedProjects = (IProject)j.next();
                  }
  
                  referencedProjects.addAll(Arrays.asList(otherEditProject.getDescription().getReferencedProjects()));
                }
              }
            }

            if ((style & Generator.EMF_EDIT_PROJECT_STYLE) == 0 && getGenModel().hasEditorSupport())
            {
              IPath editDirectory = javaSource;
              javaSource = new Path(getGenModel().getEditorDirectory());
              if (!getGenModel().sameEditEditorProject())
              {
                referencedProjects.add(editProject);
                referencedProjects.addAll(Arrays.asList(editProject.getDescription().getReferencedProjects()));
              }
            }
          }

          //  Remove any non-Java dependencies from being added.
          //
          for (Iterator i = referencedProjects.iterator(); i.hasNext(); )
          {
            IProject referencedProject = (IProject)i.next();
            IJavaProject referencedJavaProject = JavaCore.create(referencedProject);
            if (!referencedJavaProject.exists())
            {
              i.remove();
            }
          }

          if (projectLocation != null)
          {
            projectLocation = projectLocation.removeLastSegments(1).append(javaSource.segment(0));
          }

          if (getGenModel().hasXMLDependency())
          {
            style |= Generator.EMF_XML_PROJECT_STYLE;
          }

          if ((style & Generator.EMF_MODEL_PROJECT_STYLE) == 0 || getGenModel().hasPluginSupport())
          {
            style |= Generator.EMF_PLUGIN_PROJECT_STYLE;
          }

          Generator.createEMFProject
            (javaSource,
             projectLocation, 
             referencedProjects, 
             progressMonitor, 
             style,
             pluginVariables);
        }
        else
        {
          if (!project.isOpen())
          {
            project.open(new SubProgressMonitor(progressMonitor, 1));
          }
        }

        container = project;
        for (int i = 1, length = outputPath.segmentCount(); i < length; ++ i)
        {
          IFolder folder = container.getFolder(new Path(outputPath.segment(i)));
          if (!folder.exists())
          {
            folder.create(false, true, new SubProgressMonitor(progressMonitor, 1));
          }
          container = folder;
        }
      }
    }
    catch (Exception exception)
    {
      CodeGenEcorePlugin.INSTANCE.log(exception);
    }
    progressMonitor.done();
    return container;
  }

  /**
   * If {@link org.eclipse.emf.codegen.ecore.GenModel#isCodeFormatting code formatting} is enabled for this model, use
   * the specified JDT code formatter to format the given compilation unit contents. If no code formatter is specified,
   * one will be {@link org.eclipse.emf.codegen.ecore.GenModel#createCodeFormatter created}.
   */
  protected String formatCode(String contents, CodeFormatter codeFormatter)
  {
    if (getGenModel().isCodeFormatting())
    {
      if (codeFormatter == null)
      {
        codeFormatter = getGenModel().createCodeFormatter();
      }

      IDocument doc = new Document(contents);
      TextEdit edit = codeFormatter.format(CodeFormatter.K_COMPILATION_UNIT, doc.get(), 0, doc.get().length(), 0, null);

      try
      {
        edit.apply(doc);
        contents = doc.get();
      }
      catch (Exception exception)
      {
        CodeGenEcorePlugin.INSTANCE.log(exception);
      }
    }
    return contents;
  }

  public String format(String name, char separator, String prefix, boolean includePrefix)
  {
    List parsedName = new ArrayList();

    if (prefix != null && name.startsWith(prefix))
    {
      name = name.substring(prefix.length());
      if (includePrefix)
      {
        parsedName = parseName(prefix, '_');
      }
    }

    if (name.length() != 0) parsedName.addAll(parseName(name, '_'));

    StringBuffer result = new StringBuffer();

    for (Iterator nameIter = parsedName.iterator(); nameIter.hasNext(); )
    {
      String nameComponent = (String)nameIter.next();
      result.append(nameComponent);

      if (nameIter.hasNext() && nameComponent.length() > 1)
      {
        result.append(separator);
      }
    }

    return result.length() == 0 && prefix != null ? prefix : result.toString();
  }

  /**
   * This method breaks sourceName into words delimited by sourceSeparator and/or mixed-case naming.
   */
  protected List parseName(String sourceName, char sourceSeparator)
  {
    List result = new ArrayList();
    StringBuffer currentWord = new StringBuffer();

    int length = sourceName.length();
    boolean lastIsLower = false;

    for (int index=0; index<length; index++)
    {
      char curChar = sourceName.charAt(index);
      if (Character.isUpperCase(curChar) || (!lastIsLower && Character.isDigit(curChar)) || curChar == sourceSeparator)
      {
        if (lastIsLower || curChar == sourceSeparator)
        {
          result.add(currentWord.toString());
          currentWord = new StringBuffer();
        }
        lastIsLower = false;
      }
      else
      {
        if (!lastIsLower)
        {
          int currentWordLength = currentWord.length();
          if (currentWordLength > 1)
          {
            char lastChar = currentWord.charAt(--currentWordLength);
            currentWord.setLength(currentWordLength);
            result.add(currentWord.toString());
            currentWord = new StringBuffer();
            currentWord.append(lastChar);
          }
        }
        lastIsLower = true;
      }
      if (curChar != sourceSeparator)
      {
        currentWord.append(curChar);
      }
    }

    result.add(currentWord.toString());
    return result;
  }

  protected List getAllGenPackages()
  {
    List result = new ArrayList();
    result.addAll(getGenModel().getGenPackages());
    result.addAll(getGenModel().getUsedGenPackages());
    result.addAll(getGenModel().getStaticGenPackages());
    return result;
  }

  /**
   * Finds the GenPackage corresponding to the EPackage, taking into account any nested GenPackages.
   */
  protected GenPackage findGenPackageHelper(GenPackage genPackage, EPackage ePackage)
  {
    if (ePackage.getNsURI() == null ? 
          genPackage.getEcorePackage().getNsURI() == null :
          ePackage.getNsURI().equals(genPackage.getEcorePackage().getNsURI())) //FB TBD different objects for ecore model!
    {
      return genPackage;
    }

    for (Iterator nestedGenPackages = genPackage.getNestedGenPackages().iterator(); nestedGenPackages.hasNext(); )
    {
      GenPackage nestedGenPackage = (GenPackage)nestedGenPackages.next();
      GenPackage nestedResult = findGenPackageHelper(nestedGenPackage, ePackage);
      if (nestedResult != null)
      {
        return nestedResult;
      }
    }

    return null;
  }

  protected static GenPackage ecoreGenPackage;
  protected static GenPackage xmlTypeGenPackage;
  protected static GenPackage xmlNamespaceGenPackage;

  public GenPackage findGenPackage(EPackage ePackage)
  {
    if (ePackage == EcorePackage.eINSTANCE)
    {
      if (ecoreGenPackage == null)
      {
        GenModel ecoreGenModel = GenModelFactory.eINSTANCE.createGenModel();
        ecoreGenModel.initialize(Collections.singleton(EcorePackage.eINSTANCE));
        ecoreGenPackage = (GenPackage)ecoreGenModel.getGenPackages().get(0);
        ecoreGenPackage.setPrefix("Ecore");
        ecoreGenPackage.setBasePackage("org.eclipse.emf");
        ((GenPackageImpl)ecoreGenPackage).setImportManager(getImportManager());
      }
      return ecoreGenPackage;
    }

    if (ePackage == XMLTypePackage.eINSTANCE)
    {
      if (xmlTypeGenPackage == null)
      {
        GenModel xmlTypeGenModel = GenModelFactory.eINSTANCE.createGenModel();
        xmlTypeGenModel.initialize(Collections.singleton(XMLTypePackage.eINSTANCE));
        xmlTypeGenPackage = (GenPackage)xmlTypeGenModel.getGenPackages().get(0);
        xmlTypeGenPackage.setPrefix("XMLType");
        xmlTypeGenPackage.setBasePackage("org.eclipse.emf.ecore.xml");
        ((GenPackageImpl)xmlTypeGenPackage).setImportManager(getImportManager());
      }
      return xmlTypeGenPackage;
    }

    if (ePackage == XMLNamespacePackage.eINSTANCE)
    {
      if (xmlNamespaceGenPackage == null)
      {
        GenModel xmlNamespaceGenModel = GenModelFactory.eINSTANCE.createGenModel();
        xmlNamespaceGenModel.initialize(Collections.singleton(XMLNamespacePackage.eINSTANCE));
        xmlNamespaceGenPackage = (GenPackage)xmlNamespaceGenModel.getGenPackages().get(0);
        xmlNamespaceGenPackage.setPrefix("XMLNamespace");
        xmlNamespaceGenPackage.setBasePackage("org.eclipse.emf.ecore.xml");
        ((GenPackageImpl)xmlNamespaceGenPackage).setImportManager(getImportManager());
      }
      return xmlNamespaceGenPackage;
    }

    for (Iterator pIter = getAllGenPackages().iterator(); pIter.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)pIter.next();
      GenPackage resultGenPackage = findGenPackageHelper(genPackage, ePackage);
      if (resultGenPackage != null)
      {
        return resultGenPackage;
      }
    }

    return null;
  }

  protected GenClass findGenClass(EClass eClass)
  {
    GenPackage genPackage = findGenPackage(eClass.getEPackage());
    if (genPackage != null)
    {
      for (Iterator iter = genPackage.getGenClasses().iterator(); iter.hasNext(); )
      {
        GenClass genClass = (GenClass)iter.next();
        if (eClass.getName().equals(genClass.getEcoreClass().getName())) //FB TBD different objects for ecore model!
        {
          return genClass;
        }
      }
    }
    return null;
  }

  protected GenEnum findGenEnum(EEnum eEnum)
  {
    GenPackage genPackage = findGenPackage(eEnum.getEPackage());
    if (genPackage != null)
    {
      for (Iterator iter = genPackage.getGenEnums().iterator(); iter.hasNext(); )
      {
        GenEnum genEnum = (GenEnum)iter.next();
        if (eEnum.getName().equals(genEnum.getEcoreEnum().getName())) //FB TBD different objects for ecore model!
        {
          return genEnum;
        }
      }
    }
    return null;
  }

  protected GenDataType findGenDataType(EDataType eDataType)
  {
    GenPackage genPackage = findGenPackage(eDataType.getEPackage());
    if (genPackage != null)
    {
      for (Iterator iter = genPackage.getGenDataTypes().iterator(); iter.hasNext(); )
      {
        GenDataType genDataType = (GenDataType)iter.next();
        if (eDataType.getName().equals(genDataType.getEcoreDataType().getName())) //FB TBD different objects for ecore model!
        {
          return genDataType;
        }
      }
    }
    return null;
  }

  protected GenClassifier findGenClassifier(EClassifier eClassifier)
  {
    if (eClassifier instanceof EClass)
    {
      return findGenClass((EClass)eClassifier);
    }
    else if (eClassifier instanceof EEnum)
    {
      return findGenEnum((EEnum)eClassifier);
    }
    else if (eClassifier instanceof EDataType)
    {
      return findGenDataType((EDataType)eClassifier);
    }
    else
    {
      return null;
    }
  }

  protected GenFeature findGenFeature(EStructuralFeature eStructuralFeature)
  {
    GenClass genClass = findGenClass(eStructuralFeature.getEContainingClass());
    for (Iterator i = genClass.getGenFeatures().iterator(); i.hasNext(); )
    {
      GenFeature genFeature = (GenFeature)i.next();
      if (eStructuralFeature.getName().equals(genFeature.getEcoreFeature().getName())) //FB TBD different objects for ecore model!
      {
        return genFeature;
      }
    }

    return null;
  }

  protected Class getInstanceClass(EClassifier eType)
  {
    try
    {
      Class instanceClass = eType.getInstanceClass();
      return instanceClass;
    }
    catch (Exception e)
    {
      return null;
    }
  }

  protected boolean isPrimitiveType(EClassifier eType)
  {
    try
    {
      // J9 2.2 has problems assigning null to a Class variable.
      // 
      Object result = eType.getInstanceClass();
      if (result == null)
      {
        return false;
      }
      Class instanceClass = (Class)result;
      return instanceClass.isPrimitive();
    }
    catch (Exception e)
    {
      return false;
    }
  }

  protected String getPrimitiveObjectType(EClassifier eType)
  {
    Class instanceClass = getInstanceClass(eType);
    if (instanceClass == java.lang.Boolean.TYPE)
      return "java.lang.Boolean";
    if (instanceClass == java.lang.Byte.TYPE)
      return "java.lang.Byte";
    if (instanceClass == java.lang.Character.TYPE)
      return "java.lang.Character";
    if (instanceClass == java.lang.Double.TYPE)
      return "java.lang.Double";
    if (instanceClass == java.lang.Float.TYPE)
      return "java.lang.Float";
    if (instanceClass == java.lang.Integer.TYPE)
      return "java.lang.Integer";
    if (instanceClass == java.lang.Long.TYPE)
      return "java.lang.Long";
    if (instanceClass == java.lang.Short.TYPE)
      return "java.lang.Short";
    return null;
  }

  protected String getPrimitiveDefault(EClassifier eType)
  {
    if (isPrimitiveType(eType))
    {
      Class instanceClass = eType.getInstanceClass();
      if (instanceClass == java.lang.Boolean.TYPE)
        return "false";
      if (instanceClass == java.lang.Byte.TYPE    ||
          instanceClass == java.lang.Integer.TYPE ||
          instanceClass == java.lang.Long.TYPE    ||
          instanceClass == java.lang.Short.TYPE)
        return "0";
      if (instanceClass == java.lang.Character.TYPE)
        return "'\\u0000'";
      if (instanceClass == java.lang.Double.TYPE)
        return "0.0";
      if (instanceClass == java.lang.Float.TYPE)
        return "0.0F";
    }
    return null;
  }

  /**
   * Returns the primitive or qualified class name for the given
   * EClassifier.  If primitiveAsObject is true, wrapper object names will
   * be returned instead of primitive names (e.g. java.lang.Integer instead
   * of int).
   */
  protected String getType(EClassifier eType, boolean primitiveAsObject)
  {
    if (eType instanceof EClass)
    {
      return findGenClass((EClass)eType).getQualifiedInterfaceName();
    }

    if (eType instanceof EEnum)
    {
      return findGenEnum((EEnum)eType).getQualifiedName();
    }

    //(eType instanceof EDataType)
    if (primitiveAsObject && isPrimitiveType(eType))
    {
      return getPrimitiveObjectType(eType);
    }

    if ("org.eclipse.emf.common.util.AbstractEnumerator".equals(eType.getInstanceClassName()))
    {
      EDataType baseType = getExtendedMetaData().getBaseType((EDataType)eType);
      if (baseType instanceof EEnum)
      {
        GenEnum genEnum = findGenEnum((EEnum)baseType);
        if (genEnum != null)
        {
          return genEnum.getQualifiedName();
        }
      }
    }

    return eType.getInstanceClassName();
  }

  /**
   * Returns the primitive or class name for the given EClassifier.  Class
   * names will be added as imports to the GenModel's ImportManager, and the
   * imported form will be returned.  If primitiveAsObject is true, wrapper
   * object names will be returned instead of primitive names (e.g. Integer
   * instead of int).
   */
  protected String getImportedType(EClassifier eType, boolean primitiveAsObject)
  {
    String t = getType(eType, primitiveAsObject);
    return !primitiveAsObject && isPrimitiveType(eType) ? t : getGenModel().getImportedName(t);
  }

  /**
   * If eType is an EClass, returns the list of GenClasses representing its
   * non-abstract subclasses, beginning with those from the specified
   * firstGenPackage followed by the others in genPackages, and ordered down
   * the inheritance chains within each package.  Stops searching after
   * max GenClasses are found; -1 for no limit.  If eType corresponds to an
   * anonymous complex type, only that class itself is returned; otherwise,
   * no such anonymous classes are included.
   */
  protected List getTypeGenClasses(EClassifier eType, GenPackage firstGenPackage, List genPackages, int max)
  {
    if (max == 0 || !(eType instanceof EClass)) return Collections.EMPTY_LIST;

    boolean hasMax = max > -1;
    List result = new ArrayList();
    EClass baseClass = (EClass)eType;

    ExtendedMetaData extendedMetaData = getExtendedMetaData();
    if (extendedMetaData.isAnonymous(baseClass))
    {
      result.add(findGenClass(baseClass));
      return result;
    }

    // order genPackages by putting firstGenPackage (if non-null) first
    List orderedGenPackages = genPackages;
    if (firstGenPackage != null)
    {
      orderedGenPackages = new ArrayList(genPackages.size() + 1);
      orderedGenPackages.add(firstGenPackage);
      for (Iterator iter = genPackages.iterator(); iter.hasNext(); )
      {
        GenPackage genPackage = (GenPackage)iter.next();
        if (genPackage != firstGenPackage) orderedGenPackages.add(genPackage);
      }
    }

    for (Iterator pIter = orderedGenPackages.iterator(); pIter.hasNext(); )
    {
      if (baseClass == EcorePackage.eINSTANCE.getEObject())
      {
        for (Iterator cIter = ((GenPackage)pIter.next()).getOrderedGenClasses().iterator(); cIter.hasNext(); )
        {
          GenClass genClass = (GenClass)cIter.next();
          if (!genClass.isAbstract() && !extendedMetaData.isAnonymous(genClass.getEcoreClass()))
          {
            result.add(genClass);
            if (hasMax && result.size() >= max) return result;
          }
        }      
      }
      else
      {
        for (Iterator cIter = ((GenPackage)pIter.next()).getOrderedGenClasses().iterator(); cIter.hasNext(); )
        {
          GenClass genClass = (GenClass)cIter.next();
          if (!genClass.isAbstract() && 
                baseClass.isSuperTypeOf(genClass.getEcoreClass()) && 
                !extendedMetaData.isAnonymous(genClass.getEcoreClass()))
          {
            result.add(genClass);
            if (hasMax && result.size() >= max) return result;
          }
        }      
      }
    }
    return result;
  }
  
  /**
   * Returns a hash of all Java's keywords and textual literals, as of Java
   * 1.4.
   */
  protected static HashSet getJavaReservedWords()
  {
    if (javaReservedWords == null)
    {
      javaReservedWords = new HashSet(100);
      javaReservedWords.add("abstract");
      javaReservedWords.add("assert");
      javaReservedWords.add("boolean");
      javaReservedWords.add("break");
      javaReservedWords.add("byte");
      javaReservedWords.add("case");
      javaReservedWords.add("catch");
      javaReservedWords.add("char");
      javaReservedWords.add("class");
      javaReservedWords.add("const");
      javaReservedWords.add("continue");
      javaReservedWords.add("default");
      javaReservedWords.add("do");
      javaReservedWords.add("double");
      javaReservedWords.add("else");
      javaReservedWords.add("extends");
      javaReservedWords.add("false");
      javaReservedWords.add("final");
      javaReservedWords.add("finally");
      javaReservedWords.add("float");
      javaReservedWords.add("for");
      javaReservedWords.add("goto");
      javaReservedWords.add("if");
      javaReservedWords.add("implements");
      javaReservedWords.add("import");
      javaReservedWords.add("instanceof");
      javaReservedWords.add("int");
      javaReservedWords.add("interface");
      javaReservedWords.add("long");
      javaReservedWords.add("native");
      javaReservedWords.add("new");
      javaReservedWords.add("null");
      javaReservedWords.add("package");
      javaReservedWords.add("private");
      javaReservedWords.add("protected");
      javaReservedWords.add("public");
      javaReservedWords.add("return");
      javaReservedWords.add("short");
      javaReservedWords.add("static");
      javaReservedWords.add("strictfp");
      javaReservedWords.add("super");
      javaReservedWords.add("switch");
      javaReservedWords.add("synchronized");
      javaReservedWords.add("this");
      javaReservedWords.add("throw");
      javaReservedWords.add("throws");
      javaReservedWords.add("transient");
      javaReservedWords.add("true");
      javaReservedWords.add("try");
      javaReservedWords.add("void");
      javaReservedWords.add("volatile");
      javaReservedWords.add("while");
    }
    return javaReservedWords;
  }

  protected static HashSet getJavaLangTypes()
  {
    if (javaLangTypes == null)
    {
      javaLangTypes = new HashSet(100);
      javaLangTypes.add("AbstractMethodError");
      javaLangTypes.add("ArithmeticException");
      javaLangTypes.add("ArrayIndexOutOfBoundsException");
      javaLangTypes.add("ArrayStoreException");
      javaLangTypes.add("Boolean");
      javaLangTypes.add("Byte");
      javaLangTypes.add("Character");
      javaLangTypes.add("Class");
      javaLangTypes.add("ClassCastException");
      javaLangTypes.add("ClassCircularityError");
      javaLangTypes.add("ClassFormatError");
      javaLangTypes.add("ClassLoader");
      javaLangTypes.add("ClassNotFoundException");
      javaLangTypes.add("CloneNotSupportedException");
      javaLangTypes.add("Cloneable");
      javaLangTypes.add("Comparable");
      javaLangTypes.add("Compiler");
      javaLangTypes.add("Double");
      javaLangTypes.add("Error");
      javaLangTypes.add("Exception");
      javaLangTypes.add("ExceptionInInitializerError");
      javaLangTypes.add("Float");
      javaLangTypes.add("FloatingDecimal");
      javaLangTypes.add("IllegalAccessError");
      javaLangTypes.add("IllegalAccessException");
      javaLangTypes.add("IllegalArgumentException");
      javaLangTypes.add("IllegalMonitorStateException");
      javaLangTypes.add("IllegalStateException");
      javaLangTypes.add("IllegalThreadStateException");
      javaLangTypes.add("IncompatibleClassChangeError");
      javaLangTypes.add("IndexOutOfBoundsException");
      javaLangTypes.add("InheritableThreadLocal");
      javaLangTypes.add("InstantiationError");
      javaLangTypes.add("InstantiationException");
      javaLangTypes.add("Integer");
      javaLangTypes.add("InternalError");
      javaLangTypes.add("InterruptedException");
      javaLangTypes.add("LinkageError");
      javaLangTypes.add("Long");
      javaLangTypes.add("Math");
      javaLangTypes.add("NegativeArraySizeException");
      javaLangTypes.add("NoClassDefFoundError");
      javaLangTypes.add("NoSuchFieldError");
      javaLangTypes.add("NoSuchFieldException");
      javaLangTypes.add("NoSuchMethodError");
      javaLangTypes.add("NoSuchMethodException");
      javaLangTypes.add("NullPointerException");
      javaLangTypes.add("Number");
      javaLangTypes.add("NumberFormatException");
      javaLangTypes.add("Object");
      javaLangTypes.add("OutOfMemoryError");
      javaLangTypes.add("Package");
      javaLangTypes.add("Process");
      javaLangTypes.add("Runnable");
      javaLangTypes.add("Runtime");
      javaLangTypes.add("RuntimeException");
      javaLangTypes.add("RuntimePermission");
      javaLangTypes.add("SecurityException");
      javaLangTypes.add("SecurityManager");
      javaLangTypes.add("Short");
      javaLangTypes.add("StackOverflowError");
      javaLangTypes.add("String");
      javaLangTypes.add("StringBuffer");
      javaLangTypes.add("StringIndexOutOfBoundsException");
      javaLangTypes.add("System");
      javaLangTypes.add("Thread");
      javaLangTypes.add("ThreadDeath");
      javaLangTypes.add("ThreadGroup");
      javaLangTypes.add("ThreadLocal");
      javaLangTypes.add("Throwable");
      javaLangTypes.add("UnknownError");
      javaLangTypes.add("UnsatisfiedLinkError");
      javaLangTypes.add("UnsupportedClassVersionError");
      javaLangTypes.add("UnsupportedOperationException");
      javaLangTypes.add("VerifyError");
      javaLangTypes.add("VirtualMachineError");
      javaLangTypes.add("Void");
      javaLangTypes.add("boolean");
      javaLangTypes.add("byte");
      javaLangTypes.add("char");
      javaLangTypes.add("double");
      javaLangTypes.add("float");
      javaLangTypes.add("int");
      javaLangTypes.add("long");
      javaLangTypes.add("short");
    }
    return javaLangTypes;
  }

  protected static class ImportManager
  {
    protected SortedSet imports = new TreeSet();
    protected HashMap shortNameToImportMap = new HashMap();
    protected HashSet javaLangImports = null;
    protected HashSet importedPackages;

    public ImportManager(String compilationUnitPackage)
    {
      importedPackages = new HashSet();
      importedPackages.add(compilationUnitPackage);
    }
    
    public Collection getImports()
    {
      return imports;
    }

    public String getImportedName(String qualifiedName)
    {
      String indices = "";
      int firstBracket = qualifiedName.indexOf("[");
      if (firstBracket != -1) 
      {
        indices = qualifiedName.substring(firstBracket);
        qualifiedName = qualifiedName.substring(0, firstBracket);
      }

      String baseName = qualifiedName.substring(qualifiedName.lastIndexOf(".") + 1);

      String shortName = baseName;
      int firstDollar = shortName.indexOf("$");
      if (firstDollar != -1) 
      {
        shortName = shortName.substring(0, firstDollar);
      }

      String registeredName = (String)shortNameToImportMap.get(shortName);
      if (registeredName == null)
      {
        registeredName = "java.lang." + shortName;
        if (qualifiedName.equals(registeredName))
        {
          if (javaLangImports != null && javaLangImports.contains(shortName))
          {
            imports.add(qualifiedName);
          }
          return shortName + indices;
        }
        else
        {
          return qualifiedName + indices;
        }
      }
      else
      {
        if (qualifiedName.startsWith(registeredName))
        {
          if (qualifiedName.length () == registeredName.length())
          {
            return baseName.replace('$', '.') + indices;
          }
          else
          {
            char character = qualifiedName.charAt(registeredName.length());
            if (character == '.' || character == '$')
            {
              return baseName.replace('$', '.') + indices;
            }
          }
        }
        return qualifiedName.replace('$', '.') + indices;
      }
    }

    public void addImport(String packageName, String shortName)
    {
      int firstBracket = shortName.indexOf("[");
      if (firstBracket != -1) shortName = shortName.substring(0, firstBracket);
      basicAdd(packageName, shortName, packageName + "." + shortName);
    }

    public void addImport(String qualifiedName)
    {
      int firstBracket = qualifiedName.indexOf("[");
      if (firstBracket != -1) qualifiedName = qualifiedName.substring(0, firstBracket);

      int lastDot = qualifiedName.lastIndexOf(".");
      String shortName = qualifiedName.substring(lastDot + 1);
      int firstDollar = shortName.indexOf("$");
      if (firstDollar != -1) 
      {
        shortName = shortName.substring(0, firstDollar);
      }

      String packageName = lastDot == -1 ? null : qualifiedName.substring(0, lastDot);
      basicAdd(packageName, shortName, qualifiedName);
    }

    public void addMasterImport(String packageName, String shortName)
    {
      shortNameToImportMap.put(shortName, packageName + "." + shortName);
    }

    public void addJavaLangImports(List javaLangClassNames)
    {
      if (!javaLangClassNames.isEmpty())
      {
        javaLangImports = new HashSet();
        javaLangImports.addAll(javaLangClassNames);
      }
    }

    public boolean hasImport(String shortName)
    {
      return shortNameToImportMap.containsKey(shortName);
    }

    public void addCompilationUnitImports(String compilationUnitContents)
    {   
      DOMFactory jdomFactory = new DOMFactory();
      IDOMCompilationUnit jdomCompilationUnit = jdomFactory.createCompilationUnit(compilationUnitContents, "Local");
      for (IDOMNode child = jdomCompilationUnit.getFirstChild(); child != null; child = child.getNextNode())
      {
        switch (child.getNodeType())
        {
          case IDOMNode.IMPORT:
          {
            IDOMImport jdomImport = (IDOMImport)child;
            String qualifiedName = jdomImport.getName();
            int lastDot = qualifiedName.lastIndexOf(".");
            String shortName = qualifiedName.substring(lastDot + 1);
            if (shortName.equals("*"))
            {
              String packageName = qualifiedName.substring(0, lastDot);
              importedPackages.add(packageName);
            }
            else
            {
              shortNameToImportMap.put(shortName, qualifiedName);
            }
            break;
          }
        }
      }
    }

    public void addPseudoImport(String qualifiedName)
    {
      int lastDot = qualifiedName.lastIndexOf(".");
      String shortName = qualifiedName.substring(lastDot + 1);
      if (shortName.equals("*"))
      {
        String packageName = qualifiedName.substring(0, lastDot);
        importedPackages.add(packageName);
      }
      else
      {
        shortNameToImportMap.put(shortName, qualifiedName);
      }
    }

    private void basicAdd(String packageName, String shortName, String qualifiedName)
    {
      if (shortName.equals("*"))
      {
        importedPackages.add(packageName);
        imports.add(qualifiedName);
      }
      else if (!shortNameToImportMap.containsKey(shortName) && (!getJavaLangTypes().contains(shortName)))
      {
        shortNameToImportMap.put(shortName, qualifiedName);

        if (!importedPackages.contains(packageName))
        {
          imports.add(qualifiedName);
        }
      }
    }

  }

  protected static interface GenClassFilter
  {
    boolean accept(GenClass genClass);
  }

  protected static interface GenFeatureFilter
  {
    boolean accept(GenFeature genFeature);
  }

  protected static interface GenOperationFilter
  {
    boolean accept(GenOperation genOperation);
  }  

  protected static interface GenConstraintFilter
  {
    boolean accept(String genConstraint);
  }

  /**
   * Return all GenClasses in the specified genClasses list that are
   * accepted by filter; all are accepted if filter is null.
   */
  protected List filterGenClasses(List genClasses, GenClassFilter filter)
  {
    List result = new ArrayList();
    for (Iterator iter = genClasses.iterator(); iter.hasNext(); )
    {
      GenClass genClass = (GenClass)iter.next();
      if (filter == null || filter.accept(genClass))
      {
        result.add(genClass);
      }
    }
    return result;
  }

  /**
   * Iterate over the specified eClasses list, finding the GenClass
   * corresponding to each EClass.  Return all such GenClasses if filter is
   * null, or those accepted by filter, otherwise.
   */
  protected List collectGenClasses(List eClasses, GenClassFilter filter)
  {
    List result = new ArrayList();
    for (Iterator iter = eClasses.iterator(); iter.hasNext(); )
    {
      GenClass genClass = findGenClass((EClass)iter.next());
      if (filter == null || filter.accept(genClass))
      {
        result.add(genClass);
      }
    }
    return result;
  }

  /**
   * Iterate over the lists returned by calling getGenFeatures() on items in
   * the list of genClasses, and then over the list of genFeatures.  Return
   * all such GenFeatures if filter is null, or those accepted by filter,
   * otherwise.  Either list argument is ignored if it is null.
   */
  protected List collectGenFeatures(List genClasses, List genFeatures, GenFeatureFilter filter)
  {
    List result = new ArrayList();

    if (genClasses != null)
    {
      for (Iterator iter = genClasses.iterator(); iter.hasNext(); )
      {
        GenClass genClass = (GenClass)iter.next();
        for (Iterator sIter = genClass.getGenFeatures().iterator(); sIter.hasNext(); )
        {
          GenFeature genFeature = (GenFeature)sIter.next();
          if (filter == null || filter.accept(genFeature))
          {
            result.add(genFeature);
          }
        }
      }
    }

    if (genFeatures != null)
    {
      for (Iterator iter = genFeatures.iterator(); iter.hasNext(); )
      {
        GenFeature genFeature = (GenFeature)iter.next();
        if (filter == null || filter.accept(genFeature))
        {
          result.add(genFeature);
        }
      }
    }
    return result;
  }

  /**
   * Iterate over the lists returned by calling getGenOperations() on items
   * in the list of genClasses, and then over the list of genOperations.
   * Return all such GenOperations if filter is null, or those accepted by
   * filter, otherwise.  Either list argument is ignored if it is null.
   */
  protected List collectGenOperations(List genClasses, List genOperations, GenOperationFilter filter)
  {
    List result = new ArrayList();

    if (genClasses != null)
    {
      for (Iterator iter = genClasses.iterator(); iter.hasNext(); )
      {
        GenClass genClass = (GenClass)iter.next();
        for (Iterator sIter = genClass.getGenOperations().iterator(); sIter.hasNext(); )
        {
          GenOperation genOperation = (GenOperation)sIter.next();
          if (filter == null || filter.accept(genOperation))
          {
            result.add(genOperation);
          }
        }
      }
    }

    if (genOperations != null)
    {
      for (Iterator iter = genOperations.iterator(); iter.hasNext(); )
      {
        GenOperation genOperation = (GenOperation)iter.next();
        if (filter == null || filter.accept(genOperation))
        {
          result.add(genOperation);
        }
      }
    }
    return result;
  }

  /**
   * Iterate over the lists returned by calling getGenConstraints() on items in
   * the list of genClassifiers, and then over the list of genConstraints.  Return
   * all such constraint if filter is null, or those accepted by filter,
   * otherwise.  Either list argument is ignored if it is null.
   */
  protected List collectGenConstraints(List genClassifiers, List genConstraints, GenConstraintFilter filter)
  {
    List result = new UniqueEList();

    if (genClassifiers != null)
    {
      for (Iterator iter = genClassifiers.iterator(); iter.hasNext(); )
      {
        GenClassifier genClassifier = (GenClassifier)iter.next();
        for (Iterator sIter = genClassifier.getGenConstraints().iterator(); sIter.hasNext(); )
        {
          String genConstraint = (String)sIter.next();
          if (filter == null || filter.accept(genConstraint))
          {
            result.add(genConstraint);
          }
        }
      }
    }

    if (genConstraints != null)
    {
      for (Iterator iter = genConstraints.iterator(); iter.hasNext(); )
      {
        String genConstraint = (String)iter.next();
        if (filter == null || filter.accept(genConstraint))
        {
          result.add(genConstraint);
        }
      }
    }
    return result;
  }

  public String getModelInfo()
  {
    return "";
  }

  protected void appendModelSetting(StringBuffer result, String qualifier, String name, String value)
  {
    if (qualifier != null)
    {
      result.append(qualifier);
      result.append(capName(name));
    }
    else
    {
      result.append(name);
    }
    result.append("=\"");
    result.append(value);
    result.append("\" ");
  }

  protected void appendModelSetting(StringBuffer result, String name, String value)
  {
    result.append(name);
    result.append("=\"");
    result.append(value);
    result.append("\" ");
  }

  public GenModelPackage ePackageGenModel()
  {
    return GenModelPackage.eINSTANCE;
  }

  protected static boolean isJavaUtilMapEntry(String name)
  {
    return "java.util.Map.Entry".equals(name) || "java.util.Map$Entry".equals(name);
  }

  protected static boolean isBlank(String string)
  {
    return string == null || string.length() == 0;
  }

  protected abstract class UniqueNameHelper
  {
    private Map nameToObjectMap;
    private Map objectToNameMap;

    protected UniqueNameHelper()
    {
      nameToObjectMap = new HashMap();
      objectToNameMap = new HashMap();
    }

    protected UniqueNameHelper(int initialMapCapacity)
    {
      nameToObjectMap = new HashMap(initialMapCapacity);
      objectToNameMap = new HashMap(initialMapCapacity);
    }

    protected abstract String getName(Object o);

    protected List getAlternateNames(Object o)
    {
      return Collections.EMPTY_LIST;
    }

    public String getUniqueName(Object o)
    {
      if (!contains(o)) add(o);
      return (String)objectToNameMap.get(o);
    }

    protected boolean add(Object o)
    {
      if (contains(o)) return false;

      String name = getName(o);

      if (containsName(name))
      {
        List alternates = getAlternateNames(o);
        for (Iterator i = alternates.iterator(); i.hasNext(); )
        {
          name = (String)i.next();
          if (!containsName(name)) break;
        }
      }

      if (containsName(name))
      {
        for (int i = 1; true; i++)
        {
          String newName = name + "_" + i;
          if (!containsName(newName))
          {
            name = newName;
            break;
          }
          if (i == Integer.MAX_VALUE) throw new IllegalArgumentException(
            "too many objects named " + name);
        }
      }

      nameToObjectMap.put(name, o);
      objectToNameMap.put(o, name);
      return true;
    }

    public boolean addAll(Collection c)
    {
      boolean result = false;
      for (Iterator i = c.iterator(); i.hasNext(); )
      {
        if (add(i.next())) result = true;
      }
      return result;
    }

    public final boolean contains(Object o)
    {
      return objectToNameMap.containsKey(o);
    }

    public final boolean containsName(String name)
    {
      return nameToObjectMap.containsKey(name);
    }
  }

  //
  // EMFEdit generation
  //
  public boolean canGenerateEdit()
  {
    return getGenModel().canGenerateEdit() && hasModelContribution();
  }

  public void generateEdit(IProgressMonitor progressMonitor)
  {
  }

  public boolean canGenerateEditor()
  {
    return getGenModel().canGenerateEditor() && hasModelContribution();
  }

  public void generateEditor(IProgressMonitor progressMonitor)
  {
  }

  public boolean canGenerateSchema()
  {
    return false;
  }

  public void generateSchema(IProgressMonitor progressMonitor)
  {
  }

  protected static class GIFEmitter
  {
    protected String inputFile;

    protected static final int tableOffset1 = 49;
    protected static final int tableOffset2 = 25;

    public GIFEmitter(String inputFile)
    {
      this.inputFile = inputFile;
    }

    protected int code(String code)
    {
      int result = 0;
      for (int i = 0; i < code.length(); ++ i)
      {
        result += code.charAt(i) - 32;
      }
      return result;
    }

    protected byte[] generateGIF(String key1, String key2)
    {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      try
      {
        byte [] content = new byte [5000];
        int result = getContents(content, inputFile);

        // generateColor();
        ColorInformation info1 = ColorInformation.getColor(code(key1));
        ColorInformation info2 = key2 == null ? null : ColorInformation.getColor(code(key2));


        for (int j = 0; j < result; ++j)
        {
          if (j == tableOffset1 || j == tableOffset1 + 3 || j == tableOffset1 + 6 || j == tableOffset1 + 9)
          {
            int index = (j - tableOffset1) / 3;
            if (!info1.rainbow || info1.which == index - 1)
            {
              content[j] = info1.scale(info1.red, info1.factor[index]);
            }
          }
          else if (j == tableOffset1 + 1 || j == tableOffset1 + 4 || j == tableOffset1 + 7 || j == tableOffset1 + 10)
          {
            int index = (j - tableOffset1 - 1) / 3;
            if (!info1.rainbow || info1.which == index - 1)
            {
              content[j] = info1.scale(info1.green, info1.factor[index]);
            }
          }
          else if (j == tableOffset1 + 2 || j == tableOffset1 + 5 || j == tableOffset1 + 8 || j == tableOffset1 + 11)
          {
            int index = (j - tableOffset1 - 2) / 3;
            if (!info1.rainbow || info1.which == index - 1)
            {
              content[j] = info1.scale(info1.blue, info1.factor[index]);
            }
          }

          if (info2 != null)
          {
            if (j == tableOffset2 || j == tableOffset2 + 3 || j == tableOffset2 + 6 || j == tableOffset2 + 9)
            {
              int index = (j - tableOffset2) / 3;
              if (!info2.rainbow || info2.which == index - 1)
              {
                content[j] = info2.scale(info2.red, info2.factor[index]);
              }
            }
            else if (j == tableOffset2 + 1 || j == tableOffset2 + 4 || j == tableOffset2 + 7 || j == tableOffset2 + 10)
            {
              int index = (j - tableOffset2 - 1) / 3;
              if (!info2.rainbow || info2.which == index - 1)
              {
                content[j] = info2.scale(info2.green, info2.factor[index]);
              }
            }
            else if (j == tableOffset2 + 2 || j == tableOffset2 + 5 || j == tableOffset2 + 8 || j == tableOffset2 + 11)
            {
              int index = (j - tableOffset2 - 2) / 3;
              if (!info2.rainbow || info2.which == index - 1)
              {
                content[j] = info2.scale(info2.blue, info2.factor[index]);
              }
            }
          }
        }

        DataOutputStream writer = new DataOutputStream(outputStream);
        writer.write(content, 0, result);
        writer.close();
      }
      catch (Exception exception)
      {
        CodeGenEcorePlugin.INSTANCE.log(exception);
      }

      return outputStream.toByteArray();
    }

    protected int getContents(byte [] content, String gifFile) throws JETException, IOException
    {
      DataInputStream reader = new DataInputStream(JETCompiler.openStream(gifFile));
      int result = reader.read(content, 0, content.length);
      reader.close();
      return result;
    }
  }

  public static class ColorInformation
  {
    public static ColorInformation getColor(int index)
    {
      index = Math.abs(index) % 61;
      while (entries.size() <= index)
      {
        instance.generateColor();

        ColorInformation entry = new ColorInformation();
        entry.red = instance.red;
        entry.green = instance.green;
        entry.blue = instance.blue;
        entry.which = instance.which;
        entry.factor = new double [] { instance.factor[0], instance.factor[1], instance.factor[2], instance.factor[3] };
        entry.rainbow = instance.rainbow;
        entries.add(entry);
        instance.fixFactor();
      }
      return (ColorInformation)entries.get(index);
    }

    protected static ColorInformation instance = new ColorInformation();

    protected static List entries = new ArrayList(1000);

    public int red = 192;
    public int green = 64;
    public int blue = 64;

    public int which  = 2;
    public int change  = 64;

    public double [] factor = { 0.35, 0.1, -0.1, -0.3 };
    public boolean rainbow;

    public byte scale(int value, double factor)
    {
      if (factor > 0.0)
      {
        return (byte)(value + (255 - value) * factor);
      }
      else
      {
        return (byte)(value + value * factor);
      }
    }

    protected void generateColor()
    {
      switch (which)
      {
        case 0:
        {
          red += change;
          if (red <= 64)
          {
            which = 1;
            change = -change;
          }
          else if (red >= 192)
          {
            which = 1;
            change = -change;
          }
          break;
        }
        case 1:
        {
          green += change;
          if (green >= 192)
          {
            which = 2;
            change = -change;
          }
          else if (green <= 64)
          {
            which = 2;
            change = -change;
          }
          break;
        }
        case 2:
        {
          blue += change;
          if (blue >= 192)
          {
            which = 0;
            change = -change;
          }
          else if (blue <=64)
          {
            which = 0;
            change = -change;
          }
          break;
        }
      }
    }

    protected void fixFactor()
    {
      if (red == 192 && green == 64 && blue == 64)
      {
        for (int j = 0; j < factor.length; ++j)
        {
          factor[j] += 0.3;
        }
        if (factor[0] >= 1.0)
        {
          rainbow = true;
          for (int j = 0; j < factor.length; ++j)
          {
            factor[j] -= 0.8;
          }
        }
      }
    }
  }

  public String eURIFragmentSegment(EStructuralFeature eStructuralFeature, EObject eObject)
  {
    if (eObject instanceof GenBaseImpl)
    {
      GenBaseImpl genBaseImpl = (GenBaseImpl)eObject;
      String name = genBaseImpl.getName();
      if (name != null)
      {
        int count = 0;
        for (Iterator i = eContents().iterator(); i.hasNext(); )
        {
          Object otherEObject = i.next();
          if (otherEObject == eObject)
          {
            break;
          }
          if (otherEObject instanceof GenBaseImpl)
          {
            GenBaseImpl otherGenBaseImpl = (GenBaseImpl)otherEObject;
            if (name.equals(otherGenBaseImpl.getName()))
            {
              ++count;
            }
          }
        }
        return 
          count > 0 ?
            name + "." + count : 
            name;
      }
    }
    return super.eURIFragmentSegment(eStructuralFeature, eObject);
  }

  public EObject eObjectForURIFragmentSegment(String uriFragmentSegment)
  {
    if (!uriFragmentSegment.startsWith("@"))
    {
      int index = uriFragmentSegment.indexOf(".");
      String name = index == -1 ? uriFragmentSegment : uriFragmentSegment.substring(0, index);
      int count = 0;
      if (index != -1)
      {
        try
        {
          count = Integer.parseInt(uriFragmentSegment.substring(index + 1));
        }
        catch (NumberFormatException exception)
        {
          throw new WrappedException(exception);
        }
      }

      for (Iterator i = eContents().iterator(); i.hasNext(); )
      {
        Object object = i.next();
        if (object instanceof GenBaseImpl)
        {
          GenBaseImpl genBaseImpl = (GenBaseImpl)object;
          if (name.equals(genBaseImpl.getName()) && count-- == 0)
          {
            return genBaseImpl;
          }
        }
      }

      return null;
    }
    else
    {
      return super.eObjectForURIFragmentSegment(uriFragmentSegment);
    }
  }

  protected static String unicodeEscapeEncode(String unicode)
  {
    StringBuffer result = new StringBuffer(unicode.length());
    for (int i = 0, size = unicode.length(); i < size; ++i)
    {
      char character = unicode.charAt(i);
      if (character > '\u00ff')
      {
        result.append("\\u");
        String hex = Integer.toString(character, 16);
        for (int j = hex.length(); j < 4; ++j)
        {
          result.append("0");
        }
        result.append(hex);
      }
      else
      {
        result.append(character);
      }
    }

    return result.toString();
  }

  public abstract EModelElement getEcoreModelElement();

  protected String getDocumentation()
  {
    return EcoreUtil.getDocumentation(getEcoreModelElement());
  }

  public boolean hasDocumentation()
  {
    return getDocumentation() != null;
  }

  public String getDocumentation(String indentation)
  {
    return indent(getDocumentation(), indentation);
  }

  protected String indent(String text, String indentation)
  {
    if (text == null)
    {
      return null;
    }
    else
    {
      String separator = System.getProperty("line.separator") + indentation;
      int increment = separator.length() - 1;
      StringBuffer stringBuffer = new StringBuffer(text);
      for (int i = 0; i < stringBuffer.length(); ++i)
      {
        switch (stringBuffer.charAt(i))
        {
          case '\n':
          {
            stringBuffer.replace(i, i + (i + 1 < stringBuffer.length() && stringBuffer.charAt(i + 1) == '\r' ? 2 : 1), separator);
            i += increment;
            break;
          }
          case '\r':
          {
            stringBuffer.replace(i, i + (i + 1 < stringBuffer.length() && stringBuffer.charAt(i + 1) == '\n' ? 2 : 1), separator);
            i += increment;
            break;
          }
        }
      }
      return stringBuffer.toString();
    }
  }

  protected ExtendedMetaData getExtendedMetaData()
  {
    return eContainer() == null ? ExtendedMetaData.INSTANCE : ((GenBaseImpl)eContainer()).getExtendedMetaData();
  }

  protected void addNonDuplicates(Collection target, Collection source, Set noDupSet)
  {
    if (noDupSet == null)
    {
      noDupSet = new HashSet(target);
    }

    for (Iterator iter = source.iterator(); iter.hasNext(); )
    {
      Object o = iter.next();
      if (noDupSet.add(o))
      {
        target.add(o);
      }
    }
  }
}
