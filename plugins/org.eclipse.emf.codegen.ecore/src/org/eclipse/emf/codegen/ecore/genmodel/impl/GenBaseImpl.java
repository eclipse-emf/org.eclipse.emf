/**
 * <copyright> 
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: GenBaseImpl.java,v 1.63 2008/01/12 11:01:59 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.jet.JETCompiler;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.properties.PropertyMerger;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Base</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenBaseImpl#getGenAnnotations <em>Gen Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class GenBaseImpl extends EObjectImpl implements GenBase
{
  /**
   * The cached value of the '{@link #getGenAnnotations() <em>Gen Annotations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenAnnotations()
   * @generated
   * @ordered
   */
  protected EList<GenAnnotation> genAnnotations;

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
  @Override
  protected EClass eStaticClass()
  {
    return GenModelPackage.Literals.GEN_BASE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GenAnnotation> getGenAnnotations()
  {
    if (genAnnotations == null)
    {
      genAnnotations = new EObjectContainmentWithInverseEList<GenAnnotation>(GenAnnotation.class, this, GenModelPackage.GEN_BASE__GEN_ANNOTATIONS, GenModelPackage.GEN_ANNOTATION__GEN_BASE);
    }
    return genAnnotations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public GenAnnotation getGenAnnotation(String source)
  {
    if (source == null)
    {
      for (GenAnnotation genAnnotation : getGenAnnotations())
      {
        if (genAnnotation.getSource() == null)
        {
          return genAnnotation;
        }
      }
    }
    else
    {
      for (GenAnnotation genAnnotation : getGenAnnotations())
      {
        if (source.equals(genAnnotation.getSource()))
        {
          return genAnnotation;
        }
      }
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_BASE__GEN_ANNOTATIONS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getGenAnnotations()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_BASE__GEN_ANNOTATIONS:
        return ((InternalEList<?>)getGenAnnotations()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_BASE__GEN_ANNOTATIONS:
        return getGenAnnotations();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_BASE__GEN_ANNOTATIONS:
        getGenAnnotations().clear();
        getGenAnnotations().addAll((Collection<? extends GenAnnotation>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_BASE__GEN_ANNOTATIONS:
        getGenAnnotations().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_BASE__GEN_ANNOTATIONS:
        return genAnnotations != null && !genAnnotations.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  public GenModel getGenModel()
  {
    return ((GenBase)eInternalContainer()).getGenModel();
  }

  public abstract String getName();

  public String capName(String name)
  {
    return CodeGenUtil.capName(name);
  }

  public String uncapName(String name)
  {
    return CodeGenUtil.uncapName(name);
  }

  public String uncapPrefixedName(String name)
  {
    return CodeGenUtil.uncapPrefixedName(name, false);
    
  }
  
  public String uncapPrefixedName(String name, boolean forceDifferent)
  {
    return CodeGenUtil.uncapPrefixedName(name, forceDifferent);
  }

  public String safeName(String name)
  {
    return CodeGenUtil.safeName(name);
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

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  public final void generate(IProgressMonitor progressMonitor)
  {
    generate(BasicMonitor.toMonitor(progressMonitor));
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  public final void gen(Monitor progressMonitor)
  {
    generate(progressMonitor);
  }
  
  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  public void generate(Monitor progressMonitor)
  {
    // Does nothing
  }
  
  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @SuppressWarnings("unchecked")
  @Deprecated
  protected void generate(Monitor progressMonitor, int style, List pluginVariables, String outputFilePath, JETEmitter jetEmitter)
  {
    try
    {
      URI outputURI = URI.createPlatformResourceURI(outputFilePath.substring(0, outputFilePath.lastIndexOf("/")));
      progressMonitor.beginTask("", 3);
      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingFile_message", new Object [] { outputFilePath }));
      if (findOrCreateContainer(createMonitor(progressMonitor, 1), style, pluginVariables, outputURI, false))
      {
        URI targetFile = outputURI.appendSegment(outputFilePath.substring(outputFilePath.lastIndexOf("/") + 1));
        if (exists(targetFile) && (outputFilePath.endsWith("/build.properties") || !outputFilePath.endsWith(".properties")))
        {
          return;
        }
          
        //We are not generating the manifest file if the plugin.xml exists.
        //
        if (outputFilePath.endsWith("/META-INF/MANIFEST.MF") && exists(targetFile.trimSegments(2).appendSegment("plugin.xml")))
        {
          return;
        }
  
        boolean changed = false;
        boolean isUnicodeEscapeEncoded = outputFilePath.endsWith(".properties");
        String emitterResult = jetEmitter.generate(createMonitor(progressMonitor, 1), new Object [] { this });
        if (isUnicodeEscapeEncoded)
        {
          emitterResult = unicodeEscapeEncode(emitterResult);
        }
  
        progressMonitor.worked(1);
        byte [] bytes = emitterResult.toString().getBytes(isUnicodeEscapeEncoded ? "ISO-8859-1" : "UTF-8");
        if (exists(targetFile))
        {
          // Don't overwrite exising file
          PropertyMerger propertyMerger = new PropertyMerger();
          propertyMerger.setSourceProperties(emitterResult);
          progressMonitor.subTask
            (CodeGenEcorePlugin.INSTANCE.getString("_UI_ExaminingOld_message", new Object [] { targetFile }));
          String oldProperties = propertyMerger.createPropertiesForInputStream(createInputStream(targetFile));
          propertyMerger.setTargetProperties(oldProperties);
          progressMonitor.subTask
            (CodeGenEcorePlugin.INSTANCE.getString("_UI_PreparingNew_message", new Object [] { targetFile }));
          propertyMerger.merge();
          progressMonitor.worked(1);
  
          String mergedResult = propertyMerger.getTargetProperties();
          changed = !mergedResult.equals(oldProperties);
          if (changed)
          {
            if (isReadOnly(targetFile) && EclipseUtil.validateEdit(targetFile.toString(), createMonitor(progressMonitor, 1)))
            {
              propertyMerger.setTargetProperties(propertyMerger.createPropertiesForInputStream(createInputStream(targetFile)));
              propertyMerger.merge();
              mergedResult = propertyMerger.getTargetProperties();
            }
  
            bytes = mergedResult.getBytes(isUnicodeEscapeEncoded ? "ISO-8859-1" : "UTF-8");
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
            String baseName = MessageFormat.format(redirection, new Object [] { targetFile.lastSegment() });
            targetFile = outputURI.appendSegment(baseName);
            progressMonitor.subTask
              (CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingAlternate_message", new Object [] { targetFile }));
          }
    
          if (isReadOnly(targetFile))
          {
            if (getGenModel().isForceOverwrite())
            {
              setOverwriteable(targetFile);
            }
            else
            {
              targetFile = outputURI.appendSegment("." + targetFile.lastSegment() + ".new");
              progressMonitor.subTask
                (CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingDefaultAlternate_message", new Object [] { targetFile }));
            }
          }
          
          OutputStream outputStream = createOutputStream(targetFile);
          outputStream.write(bytes);
          outputStream.close();
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

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  protected Monitor createMonitor(Monitor monitor, int ticks)
  {
    return CodeGenUtil.createMonitor(monitor, ticks);
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @SuppressWarnings("unchecked")
  @Deprecated
  protected void generate
    (Monitor progressMonitor, 
     int style, 
     List pluginVariables,
     String outputFilePath, 
     GIFEmitter gifEmitter, 
     String key)
  {
    generate(progressMonitor, style, pluginVariables, outputFilePath, gifEmitter, key, null);
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @SuppressWarnings("unchecked")
  @Deprecated
  protected void generate
    (Monitor progressMonitor,
     int style, 
     List pluginVariables,
     String outputFilePath, 
     GIFEmitter gifEmitter, 
     String parentKey, 
     String childKey)
  {
    try
    {
      URI outputURI = URI.createPlatformResourceURI(outputFilePath.substring(0, outputFilePath.lastIndexOf("/")));
      progressMonitor.beginTask("", 3);
      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingImage_message", new Object [] { outputFilePath }));
      if (findOrCreateContainer(createMonitor(progressMonitor, 1), style, pluginVariables, outputURI, false))
      {
        URI targetFile = outputURI.appendSegment(outputFilePath.substring(outputFilePath.lastIndexOf("/") + 1));
        if (exists(targetFile))
        {
          // Don't overwrite exising file
        }
        else
        {
          byte[] emitterResult = gifEmitter.generateGIF(parentKey, childKey);
          progressMonitor.worked(1);
          OutputStream outputStream = createOutputStream(targetFile);
          outputStream.write(emitterResult);
          outputStream.close();
        }
      }
    }
    catch (Exception exception)
    {
      CodeGenEcorePlugin.INSTANCE.log(exception);
    }
    progressMonitor.done();
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @SuppressWarnings("unchecked")
  @Deprecated
  public void gen
    (Monitor progressMonitor, 
     int style, 
     List pluginVariables,
     String targetDirectory, 
     String packageName, 
     String className, 
     JETEmitter jetEmitter)
  {
    generate(progressMonitor, style, pluginVariables, targetDirectory, packageName, className, jetEmitter, new Object [] { this });
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @SuppressWarnings("unchecked")
  @Deprecated
  protected void generate
    (Monitor progressMonitor, 
     int style, 
     List pluginVariables,
     String targetDirectory, 
     String packageName, 
     String className, 
     JETEmitter jetEmitter)
  {
    generate(progressMonitor, style, pluginVariables, targetDirectory, packageName, className, jetEmitter, new Object [] { this });
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @SuppressWarnings("unchecked")
  @Deprecated
  protected void generate
    (Monitor progressMonitor, 
     int style, 
     List pluginVariables,
     String targetDirectory, 
     String packageName, 
     String className, 
     JETEmitter jetEmitter,
     Object [] arguments)
  {
    try
    {
      URI outputURI = URI.createPlatformResourceURI(targetDirectory).appendSegments(packageName.split("\\."));
        
      progressMonitor.beginTask("", 4);
        
      if (findOrCreateContainer(createMonitor(progressMonitor, 1), style, pluginVariables, outputURI, false))
      {
        // Create an import manager for this compilation unit
        ImportManager importManager = new ImportManager(packageName);
        importManager.addMasterImport(packageName, className);
        setImportManager(importManager);
    
        String emitterResult = jetEmitter.generate(createMonitor(progressMonitor, 1), arguments );
        progressMonitor.worked(1);
    
        boolean changed = true;
        URI targetFile = outputURI.appendSegment(className + ".java");
        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_Generating_message", new Object [] { targetFile }));
        
        String newContents = emitterResult;
        JControlModel jControlModel = getGenModel().getJControlModel();
        
        if (getGenModel().getFacadeHelperClass() != null && (jControlModel.getFacadeHelper() == null || !jControlModel.getFacadeHelper().getClass().getName().equals(getGenModel().getFacadeHelperClass())))
        {
          FacadeHelper facadeHelper = CodeGenUtil.instantiateFacadeHelper(getGenModel().getFacadeHelperClass()); 
          jControlModel.initialize(facadeHelper, getGenModel().getMergeRulesLocation());
        }
        
        if (jControlModel.canMerge())
        {
          JMerger jMerger = new JMerger(jControlModel);
          jMerger.setFixInterfaceBrace(jControlModel.getFacadeHelper().fixInterfaceBrace());
          jMerger.setSourceCompilationUnit(jMerger.createCompilationUnitForContents(emitterResult));
          // Create a code formatter for this compilation unit, if needed
          CodeFormatter codeFormatter = getGenModel().isCodeFormatting() ? getGenModel().createCodeFormatter() : null;
      
          if (exists(targetFile))
          {
            progressMonitor.subTask
              (CodeGenEcorePlugin.INSTANCE.getString("_UI_ExaminingOld_message", new Object [] { targetFile }));
            jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(createInputStream(targetFile), getEncoding(targetFile)));
            String oldContents = jMerger.getTargetCompilationUnitContents();
      
            progressMonitor.subTask
              (CodeGenEcorePlugin.INSTANCE.getString("_UI_PreparingNew_message", new Object [] { targetFile }));
            jMerger.merge();
            progressMonitor.worked(1);
      
            newContents = formatCode(jMerger.getTargetCompilationUnitContents(), codeFormatter);
            changed = !oldContents.equals(newContents);
            if (changed)
            {
              if (isReadOnly(targetFile) &&  EclipseUtil.validateEdit(targetFile.toString(), createMonitor(progressMonitor, 1)))
              {
                jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(createInputStream(targetFile), getEncoding(targetFile)));
                jMerger.remerge();
                newContents = formatCode(jMerger.getTargetCompilationUnitContents(), codeFormatter);
              }
            }
          }
          else
          {
            changed = true;
            progressMonitor.subTask
              (CodeGenEcorePlugin.INSTANCE.getString("_UI_PreparingNew_message", new Object [] { targetFile }));
            
            jMerger.merge();
            newContents = formatCode(jMerger.getTargetCompilationUnitContents(), codeFormatter);
          }
          
          if (jControlModel.getFacadeHelper() != null)
          {
            jControlModel.getFacadeHelper().reset();
          }
        }
        else
        {
          newContents = 
            CodeGenUtil.convertFormat(jControlModel.getLeadingTabReplacement(), jControlModel.convertToStandardBraceStyle(), emitterResult);
          if (exists(targetFile))
          {
            String oldContents = getContents(targetFile); 
            changed = !oldContents.equals(newContents);
          }
          else
          {
            changed = true;
          }
        }
    
        if (changed)
        {
          //purpose: using charset from 'targetFile' to encode in-memory 
          //         'newContents' object into bytes
          //modifer: Wu Zhi Qiang
          //date:    Aug 25, 2004
          //action:  first get the charset from 'targetFile', then use it 
          //         to encode the 'newContents' object into bytes
          String encoding = getEncoding(targetFile);
          byte[] bytes = encoding == null 
            ? newContents.getBytes() 
            : newContents.getBytes(encoding);
    
          // InputStream contents = new ByteArrayInputStream(bytes);
    
          String redirection = getGenModel().getRedirection();
          boolean redirect = redirection != null && redirection.indexOf("{0}") != -1;
    
          // Use an alternate if we can't write to this one.
          //
          if (redirect)
          {
            String baseName = MessageFormat.format(redirection, new Object [] { className + ".java" });
            targetFile = outputURI.appendSegment(baseName);
            progressMonitor.subTask
              (CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingAlternate_message", new Object [] { targetFile }));
          } 
    
          if (isReadOnly(targetFile))
          {
            if (getGenModel().isForceOverwrite())
            {
              setOverwriteable(targetFile);
            }
            else
            {
              targetFile = outputURI.appendSegment("." + className + ".java.new");
              progressMonitor.subTask
                (CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingDefaultAlternate_message", new Object [] { targetFile }));
            }
          }
    
          OutputStream outputStream = createOutputStream(targetFile);
          outputStream.write(bytes);
          outputStream.close();
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
    return getGenModel().getImportManager();
  }

  protected void setImportManager(ImportManager importManager)
  {
    getGenModel().setImportManager(importManager);
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  protected String formatCode(String contents, CodeFormatter codeFormatter)
  {
    return 
      EMFPlugin.IS_ECLIPSE_RUNNING && getGenModel().isCodeFormatting() ?
        EclipseUtil.formatCode(contents, codeFormatter) : 
        contents;
  }

  /**
   * @deprecated in 2.2. Please use {@link #format(String, char, String, boolean, boolean)} instead.
   */
  @Deprecated
  public String format(String name, char separator, String prefix, boolean includePrefix)
  {
    return CodeGenUtil.format(name, separator, prefix, includePrefix);
  }

  /**
   * @since 2.2.
   */
  protected String format(String name, char separator, String prefix, boolean includePrefix, boolean includeLeadingSeparator)
  {
    return CodeGenUtil.format(name, separator, prefix, includePrefix, includeLeadingSeparator);
  }

  /**
   * This method was used to break sourceName into words delimited by sourceSeparator and/or mixed-case naming.
   * Now, it simply returns an empty list.
   * @deprecated in 2.1.0.  Use {@link CodeGenUtil#parseName(String, char)} instead.
   */
  @SuppressWarnings("unchecked")
  @Deprecated
  protected final List parseName(String sourceName, char sourceSeparator)
  {
    return Collections.EMPTY_LIST;
  }

  protected List<GenPackage> getAllGenPackages()
  {
    GenModel genModel = getGenModel();
    List<GenPackage> genPackages =  genModel.getGenPackages();
    List<GenPackage> usedGenPackages =  genModel.getUsedGenPackages();
    List<GenPackage> staticGenPackages =  genModel.getStaticGenPackages();
    List<GenPackage> result = new ArrayList<GenPackage>(genPackages.size() + usedGenPackages.size() + staticGenPackages.size());
    result.addAll(genPackages);
    result.addAll(usedGenPackages);
    result.addAll(staticGenPackages);
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

    for (GenPackage nestedGenPackage : genPackage.getNestedGenPackages())
    {
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
    return getGenModel().findGenPackage(ePackage);
  }

  protected GenClass findGenClass(EClass eClass)
  {
    return ((GenModelImpl)getGenModel()).findGenClass(eClass);
  }

  protected GenEnum findGenEnum(EEnum eEnum)
  {
    return ((GenModelImpl)getGenModel()).findGenEnum(eEnum);
  }

  protected GenDataType findGenDataType(EDataType eDataType)
  {
    return ((GenModelImpl)getGenModel()).findGenDataType(eDataType);
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
    for (GenFeature genFeature : genClass.getGenFeatures())
    {
      if (eStructuralFeature.getName().equals(genFeature.getEcoreFeature().getName())) //FB TBD different objects for ecore model!
      {
        return genFeature;
      }
    }

    return null;
  }

  protected GenOperation findGenOperation(EOperation eOperation)
  {
    GenClass genClass = findGenClass(eOperation.getEContainingClass());
    genOperationsLoop: for (GenOperation genOperation : genClass.getGenOperations())
    {
      EOperation ecoreOperation = genOperation.getEcoreOperation();
      if (eOperation.getName().equals(ecoreOperation.getName())
        && eOperation.getEParameters().size() == ecoreOperation.getEParameters().size())
      {
        for (int j = 0; j < eOperation.getEParameters().size(); j++)
        {
          EParameter ecoreParameter = eOperation.getEParameters().get(j);

          if (!ecoreParameter.getEType().getName().equals((ecoreOperation.getEParameters().get(j)).getEType().getName()))
          {
            continue genOperationsLoop;
          }
        }

        return genOperation;
      }
    }

    return null;
  }

  protected GenJDKLevel getEffectiveComplianceLevel()
  {
    return getGenModel().getComplianceLevel();
  }

  protected boolean isEffectiveSuppressEMFTypes()
  {
    return getGenModel().isSuppressEMFTypes();
  }

  protected String getEffectiveMapType(GenClass context, EGenericType eGenericType, GenClass genClass)
  {
    if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50 && eGenericType.getETypeArguments().size() == 2)
    {
      String mapType = getEffectiveMapType();  
      String keyType = getTypeArgument(context, eGenericType.getETypeArguments().get(0), true, false);
      String valueType = getTypeArgument(context, eGenericType.getETypeArguments().get(1), true, false);
      mapType += "<" + keyType + ", " + valueType + ">";
      return mapType;
    }
    else
    {
      return getEffectiveMapType(context, genClass);
    }
  }

  protected String getEffectiveMapType(GenClass context, GenClass genClass)
  {
    String mapType = getEffectiveMapType();  
    if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      String keyType = genClass.getMapEntryKeyFeature().getType(context);
      String valueType = genClass.getMapEntryValueFeature().getType(context);
      mapType += "<" + keyType + ", " + valueType + ">";
    }
    return mapType;
  }

  protected String getEffectiveMapType()
  {
    return isEffectiveSuppressEMFTypes() ? "java.util.Map" : "org.eclipse.emf.common.util.EMap";
  }

  protected String getEffectiveMapEntryType(GenClass context, EGenericType eGenericType, GenClass genClass)
  {
    if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50 && eGenericType.getETypeArguments().size() == 2)
    {
      String mapType = getEffectiveMapEntryType();  
      String keyType = getTypeArgument(context, eGenericType.getETypeArguments().get(0), true, false);
      String valueType = getTypeArgument(context, eGenericType.getETypeArguments().get(1), true, false);
      mapType += "<" + keyType + ", " + valueType + ">";
      return mapType;
    }
    else
    {
      return getEffectiveMapEntryType(context, genClass);
    }
  }

  protected String getEffectiveMapEntryType(GenClass context, GenClass genClass)
  {
    String mapType = getEffectiveMapEntryType();  
    if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      String keyType = genClass.getMapEntryKeyFeature().getType(context);
      String valueType = genClass.getMapEntryValueFeature().getType(context);
      mapType += "<" + keyType + ", " + valueType + ">";
    }
    return mapType;
  }

  protected String getEffectiveMapEntryType()
  {
    return "java.util.Map$Entry";
  }

  protected String getEffectiveListType(GenClass context, EGenericType eGenericType)
  {
    String listType = getEffectiveListType();  
    if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      String itemType = getType(context, eGenericType, true);
      listType += "<" + itemType + ">";
    }
    return listType;
  }

  protected String getEffectiveListType(GenClass context, EClassifier eType)
  {
    String listType = getEffectiveListType();  
    if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      String itemType = getType(context, eType, true);
      listType += "<" + itemType + ">";
    }
    return listType;
  }

  protected String getEffectiveListType()
  {
    return isEffectiveSuppressEMFTypes() ? "java.util.List" : "org.eclipse.emf.common.util.EList";
  }

  protected String getEffectiveEObjectType()
  {
    return isEffectiveSuppressEMFTypes() ? "java.lang.Object" : "org.eclipse.emf.ecore.EObject";
  }

  protected String getEffectiveFeatureMapWrapperInterface()
  {
    String result = getGenModel().getFeatureMapWrapperInterface();
    return isBlank(result) ? "org.eclipse.emf.ecore.util.FeatureMap" : result;
  }

  protected String getImportedEffectiveFeatureMapWrapperInternalInterface()
  {
    String result = getGenModel().getFeatureMapWrapperInternalInterface();
    return getGenModel().getImportedName(isBlank(result) ? "org.eclipse.emf.ecore.util.FeatureMap" : result);
  }

  protected String getImportedEffectiveFeatureMapWrapperClass()
  {
    String result = getGenModel().getFeatureMapWrapperClass();
    return getGenModel().getImportedName(isBlank(result) ? "org.eclipse.emf.ecore.util.FeatureMap" : result);
  }

  protected Class<?> getInstanceClass(EClassifier eType)
  {
    try
    {
      Class<?> instanceClass = eType.getInstanceClass();
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
      Class<?> instanceClass = (Class<?>)result;
      return instanceClass.isPrimitive();
    }
    catch (Exception e)
    {
      return false;
    }
  }

  protected String getPrimitiveObjectType(EClassifier eType)
  {
    Class<?> instanceClass = getInstanceClass(eType);
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
      Class<?> instanceClass = eType.getInstanceClass();
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

  protected boolean hasReferenceToClassifierWithInstanceTypeName(List<? extends EGenericType> eGenericTypes)
  {
    for (EGenericType eGenericType : eGenericTypes)
    {
      if (hasReferenceToClassifierWithInstanceTypeName(eGenericType))
      {
        return true;
      }
    }
    return false;
  }

  protected boolean hasReferenceToClassifierWithInstanceTypeName(EGenericType eGenericType)
  {
    EClassifier eClassifier = eGenericType.getEClassifier();
    if (eClassifier != null)
    {
      return 
        eClassifier.eIsSet(EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME) ||
          eClassifier.eIsSet(EcorePackage.Literals.ECLASSIFIER__INSTANCE_TYPE_NAME) ||
          hasReferenceToClassifierWithInstanceTypeName(eGenericType.getETypeArguments());
    }
    else
    {
      ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
      if (eTypeParameter != null)
      {
        return false;
      }
      else
      {
        EGenericType eUpperBound = eGenericType.getEUpperBound();
        if (eUpperBound != null)
        {
          return hasReferenceToClassifierWithInstanceTypeName(eUpperBound);
        }
        else
        {
          EGenericType eLowerBound = eGenericType.getELowerBound();
          if (eLowerBound != null)
          {
            return hasReferenceToClassifierWithInstanceTypeName(eLowerBound);
          }
        }
      }
    }
    return false;
  }

  protected String getEcoreType(EGenericType eGenericType)
  {
    StringBuilder result = new StringBuilder();
    boolean useGenerics = getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    EClassifier eClassifier = useGenerics ? eGenericType.getEClassifier() : eGenericType.getERawType();
    if (eClassifier != null)
    {
      GenClassifier genClassifier = findGenClassifier(eClassifier);
      if (genClassifier != null)
      {
        result.append(genClassifier.getGenPackage().getInterfacePackageName());
        result.append('.');
        result.append(genClassifier.getName());
      }
      if (useGenerics)
      {
        EList<EGenericType> eTypeArguments = eGenericType.getETypeArguments();
        if (!eTypeArguments.isEmpty())
        {
          result.append('<');
          for (int i = 0, size = eTypeArguments.size(); i < size; ++i)
          {
            if (i != 0)
            {
              result.append(", ");
            }
            result.append(getEcoreType(eTypeArguments.get(i)));
          }
          result.append('>');
        }
      }
    }
    else
    {
      ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
      if (eTypeParameter != null)
      {
        result.append(eTypeParameter.getName());
      }
      else
      {
        result.append('?');
        EGenericType eUpperBound = eGenericType.getEUpperBound();
        if (eUpperBound != null)
        {
          result.append(" extends ");
          result.append(getEcoreType(eUpperBound));
        }
        else
        {
          EGenericType eLowerBound = eGenericType.getELowerBound();
          if (eLowerBound != null)
          {
            result.append(" super ");
            result.append(getEcoreType(eLowerBound));
          }
        }
      }
    }
    return result.toString();
  }

  /**
   * Returns the primitive or qualified class name for the given
   * EClassifier.  If primitiveAsObject is true, wrapper object names will
   * be returned instead of primitive names (e.g. java.lang.Integer instead
   * of int).
   */
  protected String getType(GenClass context, EClassifier eType, boolean primitiveAsObject)
  {
    return getType(context, eType, primitiveAsObject, false);
  }

  protected String getType(GenClass context, EClassifier eType, boolean primitiveAsObject, boolean erased)
  {
    if (eType instanceof EClass)
    {
      return (context == null ? this : (GenBaseImpl)context).findGenClass((EClass)eType).getQualifiedInterfaceName();
    }

    if (eType instanceof EEnum)
    {
      return (context == null ? this : (GenBaseImpl)context).findGenEnum((EEnum)eType).getQualifiedName();
    }

    //(eType instanceof EDataType)
    if (primitiveAsObject && isPrimitiveType(eType))
    {
      return getPrimitiveObjectType(eType);
    }

    if ("org.eclipse.emf.common.util.Enumerator".equals(eType.getInstanceClassName()))
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

    if (getEffectiveComplianceLevel().getValue() < GenJDKLevel.JDK50  || isPrimitiveType(eType) || erased && !eType.getInstanceTypeName().contains("."))
    {
      return eType.getInstanceClassName();
    }
    else
    {
      Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(eType.getInstanceTypeName());
      EGenericType eGenericType = (EGenericType)diagnostic.getData().get(0);
      return getTypeArgument(context, eGenericType, false, erased);
    }
  }

  /**
   * Returns the primitive or class name for the given EClassifier.  Class
   * names will be added as imports to the GenModel's ImportManager, and the
   * imported form will be returned.  If primitiveAsObject is true, wrapper
   * object names will be returned instead of primitive names (e.g. Integer
   * instead of int).
   */
  protected String getImportedType(GenClass context, EGenericType eGenericType, boolean primitiveAsObject)
  {
    String t = getType(context, eGenericType, primitiveAsObject);
    return !primitiveAsObject && isPrimitiveType(eGenericType.getERawType()) ? t : getGenModel().getImportedName(t);
  }

  /**
   * Returns the primitive or qualified class name for the given
   * EClassifier.  If primitiveAsObject is true, wrapper object names will
   * be returned instead of primitive names (e.g. java.lang.Integer instead
   * of int).
   */
  protected String getType(GenClass context, EGenericType eGenericType, boolean primitiveAsObject)
  {
    if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      return
        primitiveAsObject && isPrimitiveType(eGenericType.getERawType()) ?
          getPrimitiveObjectType(eGenericType.getERawType()) :
          getTypeArgument(context, eGenericType, false, false);
    }
    else
    {
      return getType(context, eGenericType.getERawType(), primitiveAsObject);
    }
  }

  /**
   * Returns the primitive or class name for the given EClassifier.  Class
   * names will be added as imports to the GenModel's ImportManager, and the
   * imported form will be returned.  If primitiveAsObject is true, wrapper
   * object names will be returned instead of primitive names (e.g. Integer
   * instead of int).
   */
  protected String getImportedType(GenClass context, EClassifier eType, boolean primitiveAsObject)
  {
    return getImportedType(context, eType, primitiveAsObject, false);
  }

  protected String getImportedType(GenClass context, EClassifier eType, boolean primitiveAsObject, boolean erased)
  {
    String t = getType(context, eType, primitiveAsObject, erased);
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
  protected List<GenClass> getTypeGenClasses(EClassifier eType, GenPackage firstGenPackage, List<GenPackage> genPackages, int max)
  {
    if (max == 0 || !(eType instanceof EClass)) return Collections.emptyList();

    boolean hasMax = max > -1;
    List<GenClass> result = new ArrayList<GenClass>();
    EClass baseClass = (EClass)eType;

    ExtendedMetaData extendedMetaData = getExtendedMetaData();
    if (extendedMetaData.isAnonymous(baseClass))
    {
      result.add(findGenClass(baseClass));
      return result;
    }

    // order genPackages by putting firstGenPackage (if non-null) first
    List<GenPackage> orderedGenPackages = genPackages;
    if (firstGenPackage != null)
    {
      orderedGenPackages = new ArrayList<GenPackage>(genPackages.size() + 1);
      orderedGenPackages.add(firstGenPackage);
      for (GenPackage genPackage : genPackages)
      {
        if (genPackage != firstGenPackage) orderedGenPackages.add(genPackage);
      }
    }

    for (GenPackage genPackage : orderedGenPackages)
    {
      if (baseClass == EcorePackage.eINSTANCE.getEObject())
      {
        for (GenClass genClass : genPackage.getOrderedGenClasses())
        {
          if (!genClass.isAbstract() && !extendedMetaData.isAnonymous(genClass.getEcoreClass()))
          {
            result.add(genClass);
            if (hasMax && result.size() >= max) return result;
          }
        }      
      }
      else
      {
        for (GenClass genClass : genPackage.getOrderedGenClasses())
        {
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
   * @deprecated
   */
  @SuppressWarnings("unchecked")
  @Deprecated
  protected static Set getJavaReservedWords()
  {
    return CodeGenUtil.getJavaReservedWords();
  }

  /**
   * @deprecated
   */
  @Deprecated
  @SuppressWarnings("unchecked")
  protected static Set getJavaLangTypes()
  {
    return CodeGenUtil.getJavaDefaultTypes();
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
  protected List<GenClass> filterGenClasses(List<GenClass> genClasses, GenClassFilter filter)
  {
    List<GenClass> result = new ArrayList<GenClass>();
    for (GenClass genClass : genClasses)
    {
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
  protected List<GenClass> collectGenClasses(List<EClass> eClasses, GenClassFilter filter)
  {
    GenModelImpl genModelImpl = (GenModelImpl)getGenModel();
    List<GenClass> result = new ArrayList<GenClass>();
    for (int i = 0, size = eClasses.size(); i < size; ++i)
    {
      GenClass genClass = genModelImpl.findGenClass(eClasses.get(i));
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
  protected List<GenFeature> collectGenFeatures(List<GenClass> genClasses, List<GenFeature> genFeatures, GenFeatureFilter filter)
  {
    List<GenFeature> result = new ArrayList<GenFeature>();

    if (genClasses != null)
    {
      for (int i = 0, iSize = genClasses.size(); i < iSize; ++i)
      {
        GenClass genClass = genClasses.get(i);
        List<GenFeature> features = genClass.getGenFeatures();
        if (filter == null)
        {
          result.addAll(features);
        }
        else
        {
          for (int j = 0, jSize = features.size(); j < jSize; ++j)
          {
            GenFeature genFeature = features.get(j);
            if (filter.accept(genFeature))
            {
              result.add(genFeature);
            }
          }
        }
      }
    }

    if (genFeatures != null)
    {
      if (filter == null)
      {
        result.addAll(genFeatures);
      }
      else
      {
        for (int i = 0, size = genFeatures.size(); i < size; ++i)
        {
          GenFeature genFeature = genFeatures.get(i);
          if (filter.accept(genFeature))
          {
            result.add(genFeature);
          }
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
  protected List<GenOperation> collectGenOperations(GenClass context, List<GenClass> genClasses, List<GenOperation> genOperations, GenOperationFilter filter)
  {
    List<GenOperation> result = new ArrayList<GenOperation>();

    if (genClasses != null)
    {
      for (GenClass genClass : genClasses)
      {
        LOOP:
        for (GenOperation genOperation : genClass.getGenOperations())
        {
          if (filter == null || filter.accept(genOperation))
          {
            for (GenOperation otherGenOperation : result)
            {
              if (otherGenOperation.isOverrideOf(context, genOperation))
              {
                continue LOOP;
              }
            }
            result.add(genOperation);
          }
        }
      }
    }

    if (genOperations != null)
    {
      LOOP:
      for (GenOperation genOperation : genOperations)
      {
        if (filter == null || filter.accept(genOperation))
        {
          for (GenOperation otherGenOperation : result)
          {
            if (otherGenOperation.isOverrideOf(context, genOperation))
            {
              continue LOOP;
            }
          }
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
  protected List<String> collectGenConstraints(List<? extends GenClassifier> genClassifiers, List<String> genConstraints, GenConstraintFilter filter)
  {
    List<String> result = new UniqueEList<String>();

    if (genClassifiers != null)
    {
      for (GenClassifier genClassifier : genClassifiers)
      {
        for (String genConstraint : genClassifier.getGenConstraints())
        {
          if (filter == null || filter.accept(genConstraint))
          {
            result.add(genConstraint);
          }
        }
      }
    }

    if (genConstraints != null)
    {
      for (String genConstraint : genConstraints)
      {
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

  protected static interface AnnotationFilter
  {
    boolean accept(EModelElement eModelElement, String source, String key, String value);
  }
  
  protected static class AnnotationFilterImpl implements AnnotationFilter
  {
    public AnnotationFilterImpl()
    {
      super();
    }
    
    public boolean accept(EModelElement eModelElement, String source, String key, String value)
    {
      return !(GenModelPackage.eNS_URI.equals(source) && ("documentation".equals(key) || "copyright".equals(key)));
    }
  }
  
  protected static final AnnotationFilter DEFAULT_ANNOTATION_FILTER = new AnnotationFilterImpl();
  
  protected List<String> getAnnotationInfo(EModelElement eModelElement)
  {
    return getAnnotationInfo(eModelElement, DEFAULT_ANNOTATION_FILTER);
  }
  
  protected List<String> getAnnotationInfo(EModelElement eModelElement, AnnotationFilter annotationFilter)
  {
    List<String> result = Collections.emptyList();
    for (EAnnotation eAnnotation : eModelElement.getEAnnotations())
    {
      String source = eAnnotation.getSource();
      if (source != null)
      {
        StringBuffer stringBuffer = null;
        for (Map.Entry<String, String> entry : eAnnotation.getDetails())
        {
          String key = entry.getKey();
          String value = entry.getValue();
          if (annotationFilter.accept(eModelElement, source, key, value))
          {
            if (stringBuffer == null)
            {
              stringBuffer = new StringBuffer(escapeString(source, " ="));
            }
            stringBuffer.append(' ');
            stringBuffer.append(escapeString(key, " ="));
            stringBuffer.append("=\'");
            stringBuffer.append(escapeString(value, ""));
            stringBuffer.append('\'');
          }
        }
        if (stringBuffer != null)
        {
          if (result.size() == 0)
          {
            result = new ArrayList<String>();
          }
          result.add(stringBuffer.toString());
        } 
      }
    }
    return result;
  }

  protected void appendAnnotationInfo(StringBuffer result, EModelElement eModelElement)
  {
    appendAnnotationInfo(result, eModelElement, DEFAULT_ANNOTATION_FILTER);
  }

  protected void appendAnnotationInfo(StringBuffer result, EModelElement eModelElement, AnnotationFilter annotationFilter)
  {
    appendAnnotationInfo(result, false, eModelElement, annotationFilter);
  }

  protected void appendAnnotationInfo(StringBuffer result, boolean qualified, EModelElement eModelElement, AnnotationFilter annotationFilter)
  {
    for (String annotationInfo : getAnnotationInfo(eModelElement, annotationFilter))
    {
      appendLineBreak(result);
      String qualifier = qualified && eModelElement instanceof ENamedElement ? ((ENamedElement)eModelElement).getName() : null;
      if (annotationInfo.startsWith(ExtendedMetaData.ANNOTATION_URI))
      {
        appendModelSetting(result, qualifier, "extendedMetaData", annotationInfo.substring(ExtendedMetaData.ANNOTATION_URI.length() + 1));
      }
      else
      {
        appendModelSetting(result, qualifier, "annotation", annotationInfo);
      } 
    }
  }
  
  protected static String escapeString(String s, String additionalCharactersToEscape)
  {
    if (s == null) return null;
    
    int len = s.length();
    StringBuffer result = new StringBuffer(len + 16);
    for (int i = 0; i < len; i++)
    {
      char c = s.charAt(i);
      if (c == '\b') result.append("\\b");
      else if (c == '\t') result.append("\\t");
      else if (c == '\n') result.append("\\n");
      else if (c == '\f') result.append("\\f");
      else if (c == '\r') result.append("\\r");
      else if (c == '\"') result.append("\\\"");
      else if (c == '\'') result.append("\\\'");
      else if (c == '\\') result.append("\\\\");
      else if (additionalCharactersToEscape.indexOf(c) == -1 && c >= 32 && c < 127) 
      {
        result.append(c);
      }
      else if (c < 256)
      {
        String num = Integer.toOctalString(c);
        switch(num.length()) 
        {
          case 1: result.append("\\00"); break;
          case 2: result.append("\\0"); break;
          default: result.append("\\"); break;
        }
        result.append(num);
      }
      else
      {
        String num = Integer.toHexString(c);
        switch(num.length()) 
        {
          case 1: result.append("\\u000"); break;
          case 2: result.append("\\u00"); break;
          case 3: result.append("\\u0"); break;
          default: result.append("\\u"); break;
        }
        result.append(num);
      }
    }

    // Escape a string that will terminate the comment in which this will all be nested.
    //
    for (int index = result.indexOf("*/"); index != -1; index = result.indexOf("*/", index))
    {
      result.replace(index, index + 1, "\\052");
    }

    return result.toString();
  }

  protected void appendLineBreak(StringBuffer result)
  {
    for (int i = result.length(); --i >= 0; )
    {
      if (Character.isWhitespace(result.charAt(i)))
      {
        result.deleteCharAt(i);
      }
      else
      {
        break;
      }
    }
    result.append(getGenModel().getLineDelimiter());
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
    private Map<String, Object> nameToObjectMap;
    private Map<Object, String> objectToNameMap;

    protected UniqueNameHelper()
    {
      nameToObjectMap = new HashMap<String, Object>();
      objectToNameMap = new HashMap<Object, String>();
    }

    protected UniqueNameHelper(int initialMapCapacity)
    {
      nameToObjectMap = new HashMap<String, Object>(initialMapCapacity);
      objectToNameMap = new HashMap<Object, String>(initialMapCapacity);
    }

    protected abstract String getName(Object o);

    protected List<String> getAlternateNames(Object o)
    {
      return Collections.emptyList();
    }

    public String getUniqueName(Object o)
    {
      if (!contains(o)) add(o);
      return objectToNameMap.get(o);
    }

    protected boolean add(Object o)
    {
      if (contains(o)) return false;

      String name = getName(o);

      if (containsName(name))
      {
        List<String> alternates = getAlternateNames(o);
        for (String aName : alternates)
        {
          if (!containsName(name)) 
          {
            name = aName;
            break;
          }
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

    public boolean addAll(Collection<?> c)
    {
      boolean result = false;
      for (Object o : c)
      {
        if (add(o)) result = true;
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

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  public final void generateEdit(IProgressMonitor progressMonitor)
  {
    generateEdit(BasicMonitor.toMonitor(progressMonitor));
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  public final void genEdit(Monitor progressMonitor)
  {
    generateEdit(progressMonitor);
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  public void generateEdit(Monitor progressMonitor)
  {
    // Do nothing
  }

  public boolean canGenerateEditor()
  {
    return getGenModel().canGenerateEditor() && hasModelContribution();
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  public final void generateEditor(IProgressMonitor progressMonitor)
  {
    generateEditor(BasicMonitor.toMonitor(progressMonitor));
  }
  
  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  public final void genEditor(Monitor progressMonitor)
  {
    generateEditor(progressMonitor);
  }
  
  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  public void generateEditor(Monitor progressMonitor)
  {
    // Do nothing
  }

  /**
   * @deprecated In EMF 2.2, schema generation is properly done via a model exporter. This method will be removed after 2.2.
   */
  @Deprecated
  public boolean canGenerateSchema()
  {
    return false;
  }

  /**
   * @deprecated In EMF 2.2, schema generation is properly done via a model exporter. This method will be removed after 2.2.
   */
  @Deprecated
  public final void generateSchema(IProgressMonitor progressMonitor)
  {
    generateSchema(BasicMonitor.toMonitor(progressMonitor));
  }
  
  /**
   * @deprecated In EMF 2.2, schema generation is properly done via a model exporter. This method will be removed after 2.2.
   */
  @Deprecated
  public final void genSchema(Monitor progressMonitor)
  {
    generateSchema(progressMonitor);
  }
  
  /**
   * @deprecated In EMF 2.2, schema generation is properly done via a model exporter. This method will be removed after 2.2.
   */
  @Deprecated
  public void generateSchema(Monitor progressMonitor)
  {
    // Do nothing
  }

  public boolean canGenerateTests()
  {
    return false;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  public final void generateTests(IProgressMonitor progressMonitor)
  {
    generateTests(BasicMonitor.toMonitor(progressMonitor));
  }
  
  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  public final void genTests(Monitor progressMonitor)
  {
    generateTests(progressMonitor);
  }
  
  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  public void generateTests(Monitor progressMonitor)
  {
    // Do nothing
  }

  /**
   * @deprecated In EMF 2.2, this moved to {@link org.eclipse.emf.codegen.util.GIFEmitter GIFEmitter}.
   * This copy will be removed after 2.2.
   */
  @Deprecated
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

  /**
   * @deprecated In EMF 2.2, this moved to {@link org.eclipse.emf.codegen.util.GIFEmitter GIFEmitter}.
   * This copy will be removed after 2.2.
   */
  @SuppressWarnings("unchecked")
  @Deprecated
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

  @Override
  public String eURIFragmentSegment(EStructuralFeature eStructuralFeature, EObject eObject)
  {
    if (eObject instanceof GenBaseImpl)
    {
      GenBaseImpl genBaseImpl = (GenBaseImpl)eObject;
      String name = genBaseImpl.getName();
      if (name != null)
      {
        int count = 0;
        for (EObject otherEObject: eContents())
        {
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

  @Override
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

      for (Object object : eContents())
      {
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

  /**
   * @deprecated In EMF 2.2, this moved to {@link org.eclipse.emf.codegen.util.CodeGenUtil#unicodeEscapeEncode CodeGenUtil}.
   * This copy will be removed after 2.2.
   */
  @Deprecated
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

  protected String getDocumentation()
  {
    EModelElement modelElement = getEcoreModelElement();
    return modelElement != null ? EcoreUtil.getDocumentation(modelElement) : null;
  }

  public boolean hasDocumentation()
  {
    return getDocumentation() != null;
  }

  public String getDocumentation(String indentation)
  {
    return indent(getDocumentation(), indentation);
  }

  protected String getCopyright(boolean includeGenModelCopyrightTextAsDefault)
  {
    for (EModelElement modelElement = getEcoreModelElement(); 
         modelElement != null; 
         modelElement = modelElement.eContainer() instanceof EModelElement ? (EModelElement)modelElement.eContainer() : null)
    {
      String copyright = EcoreUtil.getAnnotation(modelElement, GenModelPackage.eNS_URI, "copyright");
      if (copyright != null)
      {
        return copyright;
      }
    }
    if (includeGenModelCopyrightTextAsDefault)
    {
      String copyright = getGenModel().getCopyrightText();
      return isBlank(copyright) ? null : copyright;
    }
    else
    {
      return null;
    }
  }

  public boolean hasCopyright()
  {
    return getCopyright(true) != null;
  }

  public String getCopyright(String indentation)
  {
    return indent(getCopyright(true), indentation);
  }

  protected String indent(String text, String indentation)
  {
    if (text == null)
    {
      return null;
    }
    else
    {
      String separator = getGenModel().getLineDelimiter() + indentation;
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

  protected <T> void addNonDuplicates(Collection<T> target, Collection<? extends T> source, Set<T> noDupSet)
  {
    if (noDupSet == null)
    {
      noDupSet = new HashSet<T>(target);
    }

    for (T o : source)
    {
      if (noDupSet.add(o))
      {
        target.add(o);
      }
    }
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalents to the methods in this class. This class will be removed after 2.2.
   */
  @SuppressWarnings("unchecked")
  @Deprecated
  protected static class EclipseUtil
  {
    protected static String formatCode(String contents, CodeFormatter codeFormatter)
    {
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
      
      return contents;
    }

    // Unused?
    protected static IContainer findOrCreateContainer
      (GenBaseImpl genBase, Monitor progressMonitor, int style, List pluginVariables, String outputPath, boolean forceStyle)
    {
      return findOrCreateContainer(genBase, progressMonitor, style, pluginVariables, new Path(outputPath), forceStyle);
    }
    
    // Unused?
    protected static IContainer findOrCreateContainer
      (GenBaseImpl genBase, Monitor progressMonitor, int style, List pluginVariables, IPath outputPath, boolean forceStyle)
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
              URI genModelURI = genBase.getGenModel().eResource().getURI();
              if (genModelURI.isPlatformResource())
              {
                IProject genModelProject = workspace.getRoot().getProject(genModelURI.segments()[1]);
                projectLocation = genModelProject.getDescription().getLocation();
              }
            }
  
            IProject modelProject = workspace.getRoot().getProject(genBase.getGenModel().getModelProjectDirectory());
            IPath javaSource = new Path(genBase.getGenModel().getModelDirectory());
  
            if ((style & Generator.EMF_TESTS_PROJECT_STYLE) != 0)
            {
              IProject testsProject = workspace.getRoot().getProject(genBase.getGenModel().getTestsProjectDirectory());
  
              if (!genBase.getGenModel().sameModelTestsProject()) 
              {
                javaSource = new Path(genBase.getGenModel().getTestsDirectory());
  
                if (testsProject.exists())
                {
                  projectLocation = testsProject.getDescription().getLocation();
                }
  
                referencedProjects.add(modelProject);
                referencedProjects.addAll(Arrays.asList(modelProject.getDescription().getReferencedProjects()));
              }
            }
            else if ((style & Generator.EMF_MODEL_PROJECT_STYLE) == 0 && genBase.getGenModel().hasEditSupport())
            {
              IProject editProject = workspace.getRoot().getProject(genBase.getGenModel().getEditProjectDirectory());
  
              if (!genBase.getGenModel().sameModelEditProject())
              {
                javaSource = new Path(genBase.getGenModel().getEditDirectory());
                if (editProject.exists())
                {
                  projectLocation = editProject.getDescription().getLocation();
                }
  
                referencedProjects.add(modelProject);
              }
  
              for (Iterator i = genBase.getGenModel().getUsedGenPackages().iterator(); i.hasNext(); )
              {
                GenModel otherGenModel = ((GenPackage)i.next()).getGenModel();
                if (otherGenModel.hasEditSupport())
                {
                  IProject otherEditProject = workspace.getRoot().getProject(otherGenModel.getEditProjectDirectory());
                  if (otherEditProject.exists())
                  {
                    referencedProjects.add(otherEditProject);
                    referencedProjects.addAll(Arrays.asList(otherEditProject.getDescription().getReferencedProjects()));
                  }
                }
              }
  
              if ((style & Generator.EMF_EDIT_PROJECT_STYLE) == 0 && genBase.getGenModel().hasEditorSupport())
              {
                javaSource = new Path(genBase.getGenModel().getEditorDirectory());
                if (!genBase.getGenModel().sameEditEditorProject())
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
  
            if (genBase.getGenModel().hasXMLDependency())
            {
              style |= Generator.EMF_XML_PROJECT_STYLE;
            }
  
            if ((style & Generator.EMF_MODEL_PROJECT_STYLE) == 0 || genBase.getGenModel().hasPluginSupport())
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
              project.open(BasicMonitor.toIProgressMonitor(genBase.createMonitor(progressMonitor, 1)));
            }
          }
  
          container = project;
          for (int i = 1, length = outputPath.segmentCount(); i < length; ++ i)
          {
            IFolder folder = container.getFolder(new Path(outputPath.segment(i)));
            if (!folder.exists())
            {
              folder.create(false, true, BasicMonitor.toIProgressMonitor(genBase.createMonitor(progressMonitor, 1)));
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
    
    protected static boolean findOrCreateContainer
      (GenModel genModel, Monitor progressMonitor, int style, List pluginVariables, String output, boolean forceStyle)
    {
      IPath outputPath = new Path(output);
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
              URI genModelURI = genModel.eResource().getURI();
              if (genModelURI.isPlatformResource())
              {
                IProject genModelProject = workspace.getRoot().getProject(genModelURI.segments()[1]);
                projectLocation = genModelProject.getDescription().getLocation();
              }
            }
  
            IProject modelProject = workspace.getRoot().getProject(genModel.getModelProjectDirectory());
            IPath javaSource = new Path(genModel.getModelDirectory());
  
            if ((style & Generator.EMF_TESTS_PROJECT_STYLE) != 0)
            {
              IProject testsProject = workspace.getRoot().getProject(genModel.getTestsProjectDirectory());
  
              if (!genModel.sameModelTestsProject()) 
              {
                javaSource = new Path(genModel.getTestsDirectory());
  
                if (testsProject.exists())
                {
                  projectLocation = testsProject.getDescription().getLocation();
                }
  
                referencedProjects.add(modelProject);
                referencedProjects.addAll(Arrays.asList(modelProject.getDescription().getReferencedProjects()));
              }
            }
            else if ((style & Generator.EMF_MODEL_PROJECT_STYLE) == 0 && genModel.hasEditSupport())
            {
              IProject editProject = workspace.getRoot().getProject(genModel.getEditProjectDirectory());
  
              if (!genModel.sameModelEditProject())
              {
                javaSource = new Path(genModel.getEditDirectory());
                if (editProject.exists())
                {
                  projectLocation = editProject.getDescription().getLocation();
                }
  
                referencedProjects.add(modelProject);
              }
  
              for (Iterator i = genModel.getUsedGenPackages().iterator(); i.hasNext(); )
              {
                GenModel otherGenModel = ((GenPackage)i.next()).getGenModel();
                if (otherGenModel.hasEditSupport())
                {
                  IProject otherEditProject = workspace.getRoot().getProject(otherGenModel.getEditProjectDirectory());
                  if (otherEditProject.exists())
                  {
                    referencedProjects.add(otherEditProject);
                    referencedProjects.addAll(Arrays.asList(otherEditProject.getDescription().getReferencedProjects()));
                  }
                }
              }
  
              if ((style & Generator.EMF_EDIT_PROJECT_STYLE) == 0 && genModel.hasEditorSupport())
              {
                javaSource = new Path(genModel.getEditorDirectory());
                if (!genModel.sameEditEditorProject())
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
  
            if (genModel.hasXMLDependency())
            {
              style |= Generator.EMF_XML_PROJECT_STYLE;
            }
  
            if ((style & Generator.EMF_MODEL_PROJECT_STYLE) == 0 || genModel.hasPluginSupport())
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
              project.open(BasicMonitor.toIProgressMonitor(CodeGenUtil.createMonitor(progressMonitor, 1)));
            }
          }
  
          container = project;
          for (int i = 1, length = outputPath.segmentCount(); i < length; ++ i)
          {
            IFolder folder = container.getFolder(new Path(outputPath.segment(i)));
            if (!folder.exists())
            {
              folder.create(false, true, BasicMonitor.toIProgressMonitor(CodeGenUtil.createMonitor(progressMonitor, 1)));
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
      return container != null;
    }
    
    public static boolean validateEdit(String path, Monitor progressMonitor)
    {
      IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path));
      return file.getWorkspace().validateEdit(new IFile [] { file }, progressMonitor).isOK();
    }
    
    public static boolean exists(String path)
    {
      return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path)).exists();
    }
    
    public static boolean isReadOnly(String path)
    {
      return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path)).isReadOnly();
    }
    
    public static InputStream createInputStream(String path) throws Exception
    {
      return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path)).getContents(true);
    }
    
    public static String getEncoding(String path)
    {
      try
      {
        return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path)).getCharset();
      }
      catch (CoreException exception)
      {
        return null;
      }
    }
    
    public static void setWriteable(String path) throws Exception
    {
      IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path));
      ResourceAttributes resourceAttributes = file.getResourceAttributes();
      if (resourceAttributes != null)
      {
        resourceAttributes.setReadOnly(false);
        file.setResourceAttributes(resourceAttributes);
      }
    }
  }

  /**
   * @deprecated in EMF 2.2
   */
  @Deprecated
  protected static final URI PLATFORM_RESOURCE_URI = URI.createURI("platform:/resource/");

  /**
   * @deprecated in EMF 2.2
   */
  @Deprecated
  protected static final URI EMPTY_URI = URI.createURI("/");

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @SuppressWarnings("unchecked")
  @Deprecated
  public boolean findOrCreateContainer
    (Monitor progressMonitor, int style, List pluginVariables, URI outputURI, boolean forceStyle)
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      URI workspacePath = outputURI.replacePrefix(PLATFORM_RESOURCE_URI, EMPTY_URI);
      if (workspacePath != null)
      {
        return 
          EclipseUtil.findOrCreateContainer
            (getGenModel(), progressMonitor, style, pluginVariables, workspacePath.toString(), forceStyle);
      }
    }

    progressMonitor.done();
    return true;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public boolean exists(URI uri)
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      URI workspacePath = uri.replacePrefix(PLATFORM_RESOURCE_URI, EMPTY_URI);
      if (workspacePath != null)
      {
        return  EclipseUtil.exists(workspacePath.toString());
      }
    }

    URIConverter uriConverter = eResource().getResourceSet().getURIConverter();
    uri = uriConverter.normalize(uri);
    if ("file".equalsIgnoreCase(uri.scheme()))
    {
      return new File(uri.toFileString()).exists();
    }
    else
    {
      try
      {
        InputStream inputStream = uriConverter.createInputStream(uri);
        inputStream.close();
        return true;
      }
      catch (IOException exception)
      { 
        return false;
      }
    }
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public boolean isReadOnly(URI uri)
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      URI workspacePath = uri.replacePrefix(PLATFORM_RESOURCE_URI, EMPTY_URI);
      if (workspacePath != null)
      {
        return  EclipseUtil.isReadOnly(workspacePath.toString());
      }
    }

    URIConverter uriConverter = eResource().getResourceSet().getURIConverter();
    uri = uriConverter.normalize(uri);
    if ("file".equalsIgnoreCase(uri.scheme()))
    {
      File file = new File(uri.toFileString());
      return file.exists() && !file.canWrite();
    }
    else
    {
      return false;
    }
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public InputStream createInputStream(URI uri) throws Exception
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      URI workspacePath = uri.replacePrefix(PLATFORM_RESOURCE_URI, EMPTY_URI);
      if (workspacePath != null)
      {
        return EclipseUtil.createInputStream(workspacePath.toString());
      }
    }

    URIConverter uriConverter = eResource().getResourceSet().getURIConverter();
    return uriConverter.createInputStream(uri);
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public OutputStream createOutputStream(URI uri) throws Exception
  {
    URIConverter uriConverter = eResource().getResourceSet().getURIConverter();
    return uriConverter.createOutputStream(uri);
  } 

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public String getContents(URI uri) throws Exception
  {
    BufferedInputStream bufferedInputStream = new BufferedInputStream(createInputStream(uri));
    byte [] input = new byte [bufferedInputStream.available()];
    bufferedInputStream.read(input);
    bufferedInputStream.close();
    return new String(input);
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public String getEncoding(URI uri)
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      URI workspacePath = uri.replacePrefix(PLATFORM_RESOURCE_URI, EMPTY_URI);
      if (workspacePath != null)
      {
        return EclipseUtil.getEncoding(workspacePath.toString());
      }
    }
    return null;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public void setOverwriteable(URI uri) throws Exception
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      EclipseUtil.setWriteable(uri.toString());
    }
    else
    {
      URIConverter uriConverter = eResource().getResourceSet().getURIConverter();
      uri = uriConverter.normalize(uri);
      if ("file".equalsIgnoreCase(uri.scheme()))
      {
        new File(uri.toFileString()).delete();
      }
    }
  }

  public EModelElement getEcoreModelElement()
  {
    return null;
  }
  
  protected static class GenAnnotationCopier extends EcoreUtil.Copier
  {
    private static final long serialVersionUID = 1L;

    protected ResourceSet newContext;
    protected EObject oldContext;
    
    public GenAnnotationCopier(ResourceSet newContext, EObject oldContext)
    {
      this.newContext = newContext;
      this.oldContext = oldContext;
    }
    
    public void dispose()
    {
      newContext = null;
      oldContext = null;
    }
    
    // If the value is null, the key is a referenced object
    @Override
    public EObject get(Object key)
    {
      EObject value = super.get(key);
      if (value == null)
      {
        if (key instanceof EObject)
        {
          EObject referencedEObject = (EObject)key;
          if (EcoreUtil.isAncestor(oldContext, referencedEObject))
          {
            URI uri = EcoreUtil.getURI(referencedEObject);
            referencedEObject = newContext.getEObject(uri, false);
            if (referencedEObject != null)
            {
              value = referencedEObject;
            }
          }
        }
      }
      return value;
    }
  }
  
  public void reconcileGenAnnotations(GenBase oldGenBase)
  {
    if (!oldGenBase.getGenAnnotations().isEmpty() && eResource() != null && eResource().getResourceSet() != null)
    {
      GenAnnotationCopier copier = new GenAnnotationCopier(eResource().getResourceSet(), oldGenBase);
      Collection<GenAnnotation> genAnnotationsCopy = copier.copyAll(oldGenBase.getGenAnnotations());
      copier.copyReferences();

      getGenAnnotations().clear();
      getGenAnnotations().addAll(genAnnotationsCopy);
    }
  }

  protected String getTypeArguments(GenClass context, List<EGenericType> typeArguments, boolean isImported)
  {
    if (typeArguments.isEmpty())
    {
      return "";
    }
    else
    {
      StringBuilder result = new StringBuilder();
      result.append('<');
      for (Iterator<EGenericType> i = typeArguments.iterator(); i.hasNext(); )
      {
        result.append(getTypeArgument(context, i.next(), isImported, false));
        if (i.hasNext())
        {
          result.append(", ");
        }
      }
      result.append('>');
      return result.toString();
    }
  }

  protected EClassifier getBoundType(EGenericType eGenericType)
  {
    ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
    if (eTypeParameter != null)
    {
      if (eTypeParameter.getEBounds().isEmpty())
      {
        return EcorePackage.Literals.EJAVA_OBJECT;
      }
      else
      {
        return getBoundType(eTypeParameter.getEBounds().get(0));
      }
    }
    else
    {
      return eGenericType.getEClassifier();
    }
  }

  protected String getTypeArgument(GenClass context, EGenericType eGenericType, boolean isImported, boolean isErased)
  {
    ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
    if (eTypeParameter != null)
    {
      if (context != null)
      {
        for (EGenericType eGenericSuperType : context.getEcoreClass().getEAllGenericSuperTypes())
        {
          EList<ETypeParameter> eTypeParameters = eGenericSuperType.getEClassifier().getETypeParameters();
          int index = eTypeParameters.indexOf(eTypeParameter);
          if (index != -1 && eGenericSuperType.getETypeArguments().size() > index)
          {
            return getTypeArgument(context, eGenericSuperType.getETypeArguments().get(index), isImported, isErased);
          }
        }
        return eTypeParameter.getName();
      }
      else
      {
        return isImported ? getImportedType(context, getBoundType(eGenericType), false) : getType(context, getBoundType(eGenericType), false);
      }
    }
    else
    {
      StringBuilder result = new StringBuilder();
      EClassifier eClassifier = eGenericType.getEClassifier();
      if (eClassifier != null)
      {
        if (eClassifier.getName() == null)
        {
          // If it is an unqualified name, we will assume it refers to a type parameter, since we don't support default package.
          //
          String instanceTypeName = eClassifier.getInstanceTypeName();
          if (instanceTypeName.indexOf('.') == -1)
          {
            // Strip off the array indices if any.
            //
            String arrayIndices = null;
            int index = instanceTypeName.indexOf('[');
            if (index != -1)
            {
              arrayIndices = instanceTypeName.substring(index);
              instanceTypeName = instanceTypeName.substring(0, index);
            }
            
            if (CodeGenUtil.isJavaPrimitiveType(instanceTypeName))
            {
              result.append(instanceTypeName);
            }
            else
            {
              // Search the local scope for a resolution of the type parameter name.
              //
              LOOP:
              for (EObject eObject = getEcoreModelElement(); eObject != null; eObject = eObject.eContainer())
              {
                EList<ETypeParameter> eTypeParameters = null;
                if (eObject instanceof EOperation)
                {
                  eTypeParameters = ((EOperation)eObject).getETypeParameters();
                }
                else if (eObject instanceof EClass)
                {
                  eTypeParameters = ((EClass)eObject).getETypeParameters();
                }
                // If we've found thing with type parameters, search them for a match.
                if (eTypeParameters != null)
                {
                  for (ETypeParameter localETypeParameter : eTypeParameters)
                  {
                    if (instanceTypeName.equals(localETypeParameter.getName()))
                    {
                      // If we find a match, make this generic type be for this type parameter,
                      // and then stop the whole process.
                      eGenericType.setETypeParameter(localETypeParameter);
                      break LOOP;
                    }
                  }
                }
              }
              
              // Check if we found a resolution for it.
              //
              if (eGenericType.getETypeParameter() == null)
              {
                // If not we'll erase it to java.lang.Object.
                //
                result.append(isImported ? getGenModel().getImportedName("java.lang.Object") : "java.lang.Object");
              }
              else
              {
                // Otherwise, recursively call ourselves to substitute it in context if necessary.
                //
                result.append(getTypeArgument(context, eGenericType, isImported, isErased));
              }
            }
            // Add back in the array indices if there are any.
            //
            if (arrayIndices != null)
            {
              result.append(arrayIndices);
            }
          }
          else
          {
            // Just import the name or use it directly.
            //
            result.append(isImported ? getGenModel().getImportedName(instanceTypeName) : instanceTypeName);
          }
        }
        else
        {
          result.append(isImported ? getImportedType(context, eClassifier, false) : getType(context, eClassifier, false));
        }
        if (!isErased)
        {
          result.append(getTypeArguments(context, eGenericType.getETypeArguments(), isImported));
        }
      }
      else
      {
        result.append('?');
        EGenericType eUpperBound = eGenericType.getEUpperBound();
        if (eUpperBound != null)
        {
          result.append(" extends ");
          result.append(getTypeArgument(context, eUpperBound, isImported, isErased));
        }
        else
        {
          EGenericType eLowerBound = eGenericType.getELowerBound();
          if (eLowerBound != null)
          {
            result.append(" super ");
            result.append(getTypeArgument(context, eLowerBound, isImported, isErased));
          }
        }
      }
      return result.toString();
    }
  }
}
