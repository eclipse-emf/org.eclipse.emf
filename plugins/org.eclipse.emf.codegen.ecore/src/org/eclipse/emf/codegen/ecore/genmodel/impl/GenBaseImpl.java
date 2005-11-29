/**
 * <copyright> 
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id: GenBaseImpl.java,v 1.37 2005/11/29 15:07:02 emerks Exp $
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
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.jet.JETCompiler;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.codegen.jmerge.JControlModel;
import org.eclipse.emf.codegen.jmerge.JMerger;
import org.eclipse.emf.codegen.jmerge.PropertyMerger;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.URIConverter;
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
    return GenModelPackage.Literals.GEN_BASE;
  }

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

  public final void generate(IProgressMonitor progressMonitor)
  {
    generate(BasicMonitor.toMonitor(progressMonitor));
  }
  
  public final void gen(Monitor progressMonitor)
  {
    generate(progressMonitor);
  }
  
  public void generate(Monitor progressMonitor)
  {
  }
  
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
  
  protected Monitor createMonitor(Monitor monitor, int ticks)
  {
    return CodeGenUtil.createMonitor(monitor, ticks);
  }
  
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
        
      if (progressMonitor != null) progressMonitor.beginTask("", 4);
        
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
        if (progressMonitor != null)
        {
          progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_Generating_message", new Object [] { targetFile }));
        }
        
        String newContents = emitterResult;
        JControlModel jControlModel = getGenModel().getJControlModel();
        if (EMFPlugin.IS_ECLIPSE_RUNNING)
        {
          JMerger jMerger = new JMerger();
          jMerger.setControlModel(jControlModel);
          jMerger.setSourceCompilationUnit(jMerger.createCompilationUnitForContents(emitterResult));
          // Create a code formatter for this compilation unit, if needed
          CodeFormatter codeFormatter = getGenModel().isCodeFormatting() ? getGenModel().createCodeFormatter() : null;
      
          if (exists(targetFile))
          {
            progressMonitor.subTask
              (CodeGenEcorePlugin.INSTANCE.getString("_UI_ExaminingOld_message", new Object [] { targetFile }));
            jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(createInputStream(targetFile)));
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
                jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(createInputStream(targetFile)));
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

  /**
   * If {@link org.eclipse.emf.codegen.ecore.GenModel#isCodeFormatting code formatting} is enabled for this model, use
   * the specified JDT code formatter to format the given compilation unit contents. If no code formatter is specified,
   * one will be {@link org.eclipse.emf.codegen.ecore.GenModel#createCodeFormatter created}.
   */
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
  protected final List parseName(String sourceName, char sourceSeparator)
  {
    return Collections.EMPTY_LIST;
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
        GenModel ecoreGenModel = getGenModel().createGenModel();
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
        GenModel xmlTypeGenModel = getGenModel().createGenModel();
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
        GenModel xmlNamespaceGenModel = getGenModel().createGenModel();
        xmlNamespaceGenModel.initialize(Collections.singleton(XMLNamespacePackage.eINSTANCE));
        xmlNamespaceGenPackage = (GenPackage)xmlNamespaceGenModel.getGenPackages().get(0);
        xmlNamespaceGenPackage.setPrefix("XMLNamespace");
        xmlNamespaceGenPackage.setBasePackage("org.eclipse.emf.ecore.xml");
        ((GenPackageImpl)xmlNamespaceGenPackage).setImportManager(getImportManager());
      }
      return xmlNamespaceGenPackage;
    }

    if (ePackage != null)
    {
      for (Iterator pIter = getAllGenPackages().iterator(); pIter.hasNext(); )
      {
        GenPackage genPackage = (GenPackage)pIter.next();
        GenPackage resultGenPackage = findGenPackageHelper(genPackage, ePackage);
        if (resultGenPackage != null)
        {
          return resultGenPackage;
        }
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

  protected GenOperation findGenOperation(EOperation eOperation)
  {
    GenClass genClass = findGenClass(eOperation.getEContainingClass());
    genOperationsLoop: for (Iterator i = genClass.getGenOperations().iterator(); i.hasNext();)
    {
      GenOperation genOperation = (GenOperation)i.next();
      EOperation ecoreOperation = genOperation.getEcoreOperation();
      if (eOperation.getName().equals(ecoreOperation.getName())
        && eOperation.getEParameters().size() == ecoreOperation.getEParameters().size())
      {
        for (int j = 0; j < eOperation.getEParameters().size(); j++)
        {
          EParameter ecoreParameter = (EParameter)eOperation.getEParameters().get(j);

          if (!ecoreParameter.getEType().getName().equals(((EParameter)ecoreOperation.getEParameters().get(j)).getEType().getName()))
          {
            continue genOperationsLoop;
          }
        }

        return genOperation;
      }
    }

    return null;
  }

  protected boolean isEffectiveSuppressEMFTypes()
  {
    return getGenModel().isSuppressEMFTypes();
  }

  protected String getEffectiveMapType()
  {
    return isEffectiveSuppressEMFTypes() ? "java.util.Map" : "org.eclipse.emf.common.util.EMap";
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
   * @deprecated
   */
  protected static Set getJavaReservedWords()
  {
    return CodeGenUtil.getJavaReservedWords();
  }

  /**
   * @deprecated
   */
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

  protected static interface AnnotationFilter
  {
    boolean accept(EModelElement eModelElement, String source, String key, String value);
  }
  
  protected static class AnnotationFilterImpl implements AnnotationFilter
  {
    public AnnotationFilterImpl()
    {
    }
    
    public boolean accept(EModelElement eModelElement, String source, String key, String value)
    {
      return !(GenModelPackage.eNS_URI.equals(source) && "documentation".equals(key));
    }
  }
  
  protected static final AnnotationFilter DEFAULT_ANNOTATION_FILTER = new AnnotationFilterImpl();
  
  protected List getAnnotationInfo(EModelElement eModelElement)
  {
    return getAnnotationInfo(eModelElement, DEFAULT_ANNOTATION_FILTER);
  }
  
  protected List getAnnotationInfo(EModelElement eModelElement, AnnotationFilter annotationFilter)
  {
    List result = Collections.EMPTY_LIST;
    for (Iterator i = eModelElement.getEAnnotations().iterator(); i.hasNext(); )
    {
      EAnnotation eAnnotation = (EAnnotation)i.next();
      String source = eAnnotation.getSource();
      if (source != null)
      {
        StringBuffer stringBuffer = null;
        for (Iterator j = eAnnotation.getDetails().iterator(); j.hasNext(); )
        {
          Map.Entry mapEntry = (Map.Entry)j.next();
          String key = (String)mapEntry.getKey();
          String value = (String)mapEntry.getValue();
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
            result = new ArrayList();
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
    for (Iterator i = getAnnotationInfo(eModelElement, annotationFilter).iterator(); i.hasNext(); )
    {
      String annotationInfo = (String)i.next();
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
    result.append(System.getProperty("line.separator"));
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

  public final void generateEdit(IProgressMonitor progressMonitor)
  {
    generateEdit(BasicMonitor.toMonitor(progressMonitor));
  }
  
  public final void genEdit(Monitor progressMonitor)
  {
    generateEdit(progressMonitor);
  }
  
  public void generateEdit(Monitor progressMonitor)
  {
  }

  public boolean canGenerateEditor()
  {
    return getGenModel().canGenerateEditor() && hasModelContribution();
  }

  public final void generateEditor(IProgressMonitor progressMonitor)
  {
    generateEditor(BasicMonitor.toMonitor(progressMonitor));
  }
  
  public final void genEditor(Monitor progressMonitor)
  {
    generateEditor(progressMonitor);
  }
  
  public void generateEditor(Monitor progressMonitor)
  {
  }

  public boolean canGenerateSchema()
  {
    return false;
  }

  public final void generateSchema(IProgressMonitor progressMonitor)
  {
    generateSchema(BasicMonitor.toMonitor(progressMonitor));
  }
  
  public final void genSchema(Monitor progressMonitor)
  {
    generateSchema(progressMonitor);
  }
  
  public void generateSchema(Monitor progressMonitor)
  {
  }

  public boolean canGenerateTests()
  {
    return false;
  }

  public final void generateTests(IProgressMonitor progressMonitor)
  {
    generateTests(BasicMonitor.toMonitor(progressMonitor));
  }
  
  public final void genTests(Monitor progressMonitor)
  {
    generateTests(progressMonitor);
  }
  
  public void generateTests(Monitor progressMonitor)
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
    
    protected static IContainer findOrCreateContainer
      (GenBaseImpl genBase, Monitor progressMonitor, int style, List pluginVariables, String outputPath, boolean forceStyle)
    {
      return findOrCreateContainer(genBase, progressMonitor, style, pluginVariables, new Path(outputPath), forceStyle);
    }
    
    protected static IContainer findOrCreateContainer
      (GenBaseImpl genBase, Monitor progressMonitor, int style, List pluginVariables, IPath outputPath, boolean forceStyle)
    {
      IContainer container = null;
      try
      {
        if (progressMonitor != null)
        {
          progressMonitor.beginTask("", outputPath.segmentCount() + 1);
          progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_OpeningFolder_message", new Object [] { outputPath }));
        }
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
              if (genModelURI.toString().startsWith("platform:/resource/"))
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
        if (progressMonitor != null)
        {
          progressMonitor.beginTask("", outputPath.segmentCount() + 1);
          progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_OpeningFolder_message", new Object [] { outputPath }));
        }
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
              if (genModelURI.toString().startsWith("platform:/resource/"))
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
  
  protected static final URI PLATFORM_RESOURCE_URI = URI.createURI("platform:/resource/");
  protected static final URI EMPTY_URI = URI.createURI("/");
    
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
  
  public OutputStream createOutputStream(URI uri) throws Exception
  {
    URIConverter uriConverter = eResource().getResourceSet().getURIConverter();
    return uriConverter.createOutputStream(uri);
  } 
  
  public String getContents(URI uri) throws Exception
  {
    BufferedInputStream bufferedInputStream = new BufferedInputStream(createInputStream(uri));
    byte [] input = new byte [bufferedInputStream.available()];
    bufferedInputStream.read(input);
    bufferedInputStream.close();
    return new String(input);
  }
  
  public String getEncoding(URI uri)
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      return EclipseUtil.getEncoding(uri.toString());
    }
    else
    {
      return null;
    }
  }
  
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
} 
