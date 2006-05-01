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
 * $Id: AbstractGeneratorAdapter.java,v 1.1 2006/05/01 10:23:59 davidms Exp $
 */
package org.eclipse.emf.codegen.ecore.generator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.jet.JETCompiler;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.codegen.merge.properties.PropertyMerger;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.codegen.util.GIFEmitter;
import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.notify.impl.SingletonAdapterImpl;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.URIConverterImpl;

/**
 * @since 2.2.0
 */
public abstract class AbstractGeneratorAdapter extends SingletonAdapterImpl implements GeneratorAdapter
{
  protected final static String MANIFEST_ENCODING = "UTF-8";
  protected final static String PROPERTIES_ENCODING = "ISO-8859-1";

  protected GeneratorAdapterFactory adapterFactory;

  // The object for which this adapter is currently being used to generate code. This will only be set during
  // preGenerate(), generate(), and postGenerate().
  //
  protected Object generatingObject;

  // The message describing what is being done. This can be set during doExecute() and will be used in the
  // diagnostic message if an exception is caught.
  //
  protected String message;

  protected JETEmitter[] jetEmitters;
  protected ImportManager importManager;
  protected URIConverter uriConverter;

  public AbstractGeneratorAdapter()
  {
  }

  public AbstractGeneratorAdapter(GeneratorAdapterFactory adapterFactory)
  {
    this.adapterFactory = adapterFactory;
  }

  public GeneratorAdapterFactory getAdapterFactory()
  {
    return adapterFactory;
  }

  public void setAdapterFactory(GeneratorAdapterFactory adapterFactory)
  {
    this.adapterFactory = adapterFactory;
  }

  public boolean isAdapterForType(Object type)
  {
    return type == adapterFactory;
  }

  public Collection getCanGenerateChildren(Object object, Object projectType)
  {
    return Collections.EMPTY_LIST;
  }

  public abstract boolean canGenerate(Object object, Object projectType);
  
  public Collection getGenerateChildren(Object object, Object projectType)
  {
    return Collections.EMPTY_LIST;
  }

//public void preGenerate(Object object, Object projectType)
//{
//}

  //DMS Is this pattern overkill for preGenerate()?  Still pass object?
  //
  public final Diagnostic preGenerate(Object object, Object projectType)
  {
    try
    {
      generatingObject = object;
      return doPreGenerate(object, projectType);
    }
    finally
    {
      generatingObject = null;
    }
  }

  protected Diagnostic doPreGenerate(Object object, Object projectType)
  {
    return Diagnostic.OK_INSTANCE;
  }

  //DMS Still pass object, even though available as generatingObject?
  //
  public final Diagnostic generate(Object object, Object projectType, Monitor monitor)
  {
    try
    {
      if (canGenerate(object, projectType))
      {
        generatingObject = object;
        message = null;
        return doGenerate(object, projectType, monitor);
      }
      return Diagnostic.OK_INSTANCE;
    }
    catch (Exception exception)
    {      
      return toDiagnostic(exception, message);
    }
    finally
    {
      generatingObject = null;
      message = null;
      monitor.done();
    }
  }

  protected abstract Diagnostic doGenerate(Object object, Object projectType, Monitor monitor) throws Exception;

//  public void postGenerate(Object object, Object projectType)
//  {
//  }
  
  //DMS Is this pattern overkill for preGenerate()?  Still pass object?
  //
  public final Diagnostic postGenerate(Object object, Object projectType)
  {
    try
    {
      generatingObject = object;
      return doPostGenerate(object, projectType);
    }
    finally
    {
      generatingObject = null;
    }
  }

  protected Diagnostic doPostGenerate(Object object, Object projectType)
  {
    return Diagnostic.OK_INSTANCE;
  }

  protected Diagnostic toDiagnostic(Exception exception, String currentMessage)
  {
    CodeGenEcorePlugin.INSTANCE.log(exception);

    currentMessage = currentMessage != null ?
      CodeGenEcorePlugin.INSTANCE.getString("_UI_GenerateException_diagnostic", new Object[] { currentMessage }) :
      CodeGenEcorePlugin.INSTANCE.getString("_UI_GenericGenerateException_diagnostic");
    BasicDiagnostic diagnostic = new BasicDiagnostic(CodeGenEcorePlugin.ID, 0, currentMessage, null);

    //DMS this doesn't really produce nice output.
    //
    diagnostic.add(BasicDiagnostic.toDiagnostic(exception));
    return diagnostic;
  }

  protected Generator getGenerator()
  {
    return getAdapterFactory().getGenerator();
  }

  protected static class JETEmitterDescriptor
  {
    public String templatePathName;
    public String className;

    public JETEmitterDescriptor(String templatePathName, String className)
    {
      this.templatePathName = templatePathName;
      this.className = className;
    }
  }

  protected JETEmitter getJETEmitter(JETEmitterDescriptor[] jetEmitterDescriptors, int id)
  {
    // For dynamic templates, we shouldn't cache JETEmitters.
    //
    if (getGenerator().getOptions().dynamicTemplates)
    {
      jetEmitters = null;
      return createJETEmitter(jetEmitterDescriptors[id]);
    }

    if (jetEmitters == null)
    {
      jetEmitters = new JETEmitter[jetEmitterDescriptors.length];
    }

    JETEmitter jetEmitter = jetEmitters[id];
    if (jetEmitter == null)
    {
      jetEmitter = createJETEmitter(jetEmitterDescriptors[id]);
      jetEmitters[id] = jetEmitter;
    }
    return jetEmitter;
  }

  protected JETEmitter createJETEmitter(JETEmitterDescriptor jetEmitterDescriptor)
  {
    JETEmitter jetEmitter = new JETEmitter
      (getGenerator().getOptions().templatePath, jetEmitterDescriptor.templatePathName, getClass().getClassLoader());

    try
    {
      setStaticTemplateClass(jetEmitter, jetEmitterDescriptor.className);
      addClasspathEntries(jetEmitter);
    }
    catch (JETException exception)
    {
      CodeGenEcorePlugin.INSTANCE.log(exception);
    }

    return jetEmitter;
  }

  protected static final Class[] OBJECT_ARGUMENT = new Class [ ] { Object.class };

  protected void setStaticTemplateClass(JETEmitter jetEmitter, String className)
  {
    if (!getGenerator().getOptions().dynamicTemplates)
    {
      try
      {
        Class templateClass = getClass().getClassLoader().loadClass(className);
        Method emitterMethod = templateClass.getDeclaredMethod("generate", OBJECT_ARGUMENT);
        jetEmitter.setMethod(emitterMethod);
      }
      catch (Exception exception)
      {
        // It's okay for there not be a precompiled template, so fail quietly.
      }
    }
  }

  protected void addClasspathEntries(JETEmitter jetEmitter) throws JETException
  {
  }

  protected GIFEmitter[] gifEmitters;

  protected GIFEmitter getGIFEmitter(String[] inputPathNames, int id)
  {
    // For dynamic templates, we shouldn't cache GIFEmitters.
    //
    if (getGenerator().getOptions().dynamicTemplates)
    {
      gifEmitters = null;
      return createGIFEmitter(inputPathNames[id]);
    }

    if (gifEmitters == null)
    {
      gifEmitters = new GIFEmitter[inputPathNames.length];
    }

    GIFEmitter gifEmitter = gifEmitters[id];
    if (gifEmitter == null)
    {
      gifEmitter = createGIFEmitter(inputPathNames[id]);
      gifEmitters[id] = gifEmitter;
    }
    return gifEmitter;
  }

  protected GIFEmitter createGIFEmitter(String inputPathName)
  {
    return new GIFEmitter(JETCompiler.find(getGenerator().getOptions().templatePath, inputPathName));
  }

  protected void generateText
    (String targetPathName,
     JETEmitter jetEmitter,
     Object[] arguments,
     boolean overwrite,
     String encoding,
     Monitor monitor)
  {
    try
    {
      monitor.beginTask("", 3);

      URI targetFile = toURI(targetPathName);
      monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingFile_message", new Object[] { targetFile }));

      URI targetDirectory = targetFile.trimSegments(1);
      ensureContainerExists(targetDirectory, createMonitor(monitor, 1));

      boolean exists = exists(targetFile);
      if (!exists || overwrite)
      {
        if (arguments == null)
        {
          arguments = new Object[] { generatingObject };
        }
        String emitterResult = jetEmitter.generate(createMonitor(monitor, 1), arguments);

        //DMS Should I add the option back in?  Make a more general/specifc test?  Get encoding from file before the test?
        if (PROPERTIES_ENCODING.equals(encoding))
        {
          emitterResult = CodeGenUtil.unicodeEscapeEncode(emitterResult);
        }

        if (encoding == null)
        {
          encoding = getEncoding(targetFile);
        }

        boolean changed = true;
        if (exists)
        {
          monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_ExaminingOld_message", new Object[] { targetFile }));
          String oldContents = getContents(targetFile, encoding);
          changed = !emitterResult.equals(oldContents);
        }

        if (changed)
        {
          byte[] bytes = encoding == null ? emitterResult.toString().getBytes() : emitterResult.toString().getBytes(encoding);

          // Apply a redirection pattern, if specified.
          //
          String redirection = getGenerator().getOptions().redirectionPattern;
          boolean redirect = redirection != null && redirection.indexOf("{0}") != -1;

          if (redirect)
          {
            String baseName = MessageFormat.format(redirection, new Object[] { targetFile.lastSegment() });
            targetFile = targetDirectory.appendSegment(baseName);
            monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingAlternate_message", new Object[] { targetFile }));
          }

          if (isReadOnly(targetFile))
          {
            if (getGenerator().getOptions().forceOverwrite)
            {
              // If the target is read-only, we can ask the platform to release it.
              //
              validateEdit(targetFile, createMonitor(monitor, 1));              
              setWriteable(targetFile);
            }
            else
            {
              targetFile = targetDirectory.appendSegment("." + targetFile.lastSegment() + ".new");
              monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingDefaultAlternate_message", new Object[] { targetFile }));
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
      monitor.done();
    }
  }

  protected void generateProperties(String targetPathName, JETEmitter jetEmitter, Object[] arguments, Monitor monitor)
  {
    try
    {
      monitor.beginTask("", 3);

      URI targetFile = toURI(targetPathName);
      monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingFile_message", new Object[] { targetFile }));

      URI targetDirectory = targetFile.trimSegments(1);
      ensureContainerExists(targetDirectory, createMonitor(monitor, 1));

      boolean changed = false;
      if (arguments == null)
      {
        arguments = new Object[] { generatingObject };
      }
      String emitterResult = CodeGenUtil.unicodeEscapeEncode(jetEmitter.generate(createMonitor(monitor, 1), arguments));
      byte[] bytes = emitterResult.toString().getBytes(PROPERTIES_ENCODING);

      if (exists(targetFile))
      {
        // Merge with an existing file.
        //
        PropertyMerger propertyMerger = new PropertyMerger();
        propertyMerger.setSourceProperties(emitterResult);
        monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_ExaminingOld_message", new Object[] { targetFile }));
        String oldProperties = propertyMerger.createPropertiesForInputStream(createInputStream(targetFile));
        propertyMerger.setTargetProperties(oldProperties);
        monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_PreparingNew_message", new Object[] { targetFile }));
        propertyMerger.merge();

        String mergedResult = propertyMerger.getTargetProperties();
        changed = !mergedResult.equals(oldProperties);
        if (changed)
        {
          // If the target is read-only, we can ask the platform to release it, and it may be updated in the process.
          //
          if (isReadOnly(targetFile) && validateEdit(targetFile, createMonitor(monitor, 1)))
          {
            propertyMerger.setTargetProperties(propertyMerger.createPropertiesForInputStream(createInputStream(targetFile)));
            propertyMerger.merge();
            mergedResult = propertyMerger.getTargetProperties();
          }
          bytes = mergedResult.getBytes(PROPERTIES_ENCODING);
        }
      }
      else
      {
        changed = true;
        monitor.worked(1);
      }

      if (changed)
      {
        // Apply a redirection pattern, if specified.
        //
        String redirection = getGenerator().getOptions().redirectionPattern;
        boolean redirect = redirection != null && redirection.indexOf("{0}") != -1;

        if (redirect)
        {
          String baseName = MessageFormat.format(redirection, new Object[] { targetFile.lastSegment() });
          targetFile = targetDirectory.appendSegment(baseName);
          monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingAlternate_message", new Object[] { targetFile }));
        }

        if (isReadOnly(targetFile))
        {
          if (getGenerator().getOptions().forceOverwrite)
          {
            setWriteable(targetFile);
          }
          else
          {
            targetFile = targetDirectory.appendSegment("." + targetFile.lastSegment() + ".new");
            monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingDefaultAlternate_message", new Object[] { targetFile }));
          }
        }

        OutputStream outputStream = createOutputStream(targetFile);
        outputStream.write(bytes);
        outputStream.close();
      }
    }
    catch (Exception exception)
    {
      CodeGenEcorePlugin.INSTANCE.log(exception);
    }
    finally
    {
      monitor.done();
    }
  }
  
  protected void generateGIF
    (String targetPathName,
     GIFEmitter gifEmitter,
     String parentKey,
     String childKey,
     boolean overwrite,
     Monitor monitor)
  {
    try
    {
      monitor.beginTask("", 3);

      URI targetFile = toURI(targetPathName);
      monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingImage_message", new Object[] { targetFile }));

      URI targetDirectory = targetFile.trimSegments(1);
      ensureContainerExists(targetDirectory, createMonitor(monitor, 1));

      boolean exists = exists(targetFile);
      if (!exists || overwrite)
      {
        byte[] emitterResult = gifEmitter.generateGIF(parentKey, childKey);
        monitor.worked(1);

        // Apply a redirection pattern, if specified.
        //
        String redirection = getGenerator().getOptions().redirectionPattern;
        boolean redirect = redirection != null && redirection.indexOf("{0}") != -1;

        if (redirect)
        {
          String baseName = MessageFormat.format(redirection, new Object[] { targetFile.lastSegment() });
          targetFile = targetDirectory.appendSegment(baseName);
          monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingAlternate_message", new Object[] { targetFile }));
        }

        if (isReadOnly(targetFile))
        {
          if (getGenerator().getOptions().forceOverwrite)
          {
            // If the target is read-only, we can ask the platform to release it.
            //
            validateEdit(targetFile, createMonitor(monitor, 1));              
            setWriteable(targetFile);
          }
          else
          {
            targetFile = targetDirectory.appendSegment("." + targetFile.lastSegment() + ".new");
            monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingDefaultAlternate_message", new Object[] { targetFile }));
          }
        }

        OutputStream outputStream = createOutputStream(targetFile);
        outputStream.write(emitterResult);
        outputStream.close();
      }
    }
    catch (Exception exception)
    {
      CodeGenEcorePlugin.INSTANCE.log(exception);
    }
    finally
    {
      monitor.done();
    }
  }

  protected void generateJava
    (String targetPath,
     String packageName,
     String className,
     JETEmitter jetEmitter,
     Object[] arguments,
     Monitor monitor)
  {
    try
    {
      monitor.beginTask("", 4);

      URI targetDirectory = toURI(targetPath).appendSegments(packageName.split("\\."));
      URI targetFile = targetDirectory.appendSegment(className + ".java");
      monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_Generating_message", new Object[] { targetFile }));

      ensureContainerExists(targetDirectory, createMonitor(monitor, 1));

      if (arguments == null)
      {
        arguments = new Object[] { generatingObject };
      }
      createImportManager(packageName, className);
      String emitterResult = jetEmitter.generate(createMonitor(monitor, 1), arguments);

      boolean changed = true;
      String newContents = emitterResult;

      JControlModel jControlModel = getGenerator().getJControlModel();
      //DMS This is not right. It replaced "if (EMFPlugin.IS_ECLIPSE_RUNNING)" but can also be false if an invalid facade has been specified.
      if (jControlModel.canMerge())
      {
        JMerger jMerger = new JMerger(jControlModel);
        jMerger.setFixInterfaceBrace(true);
        jMerger.setSourceCompilationUnit(jMerger.createCompilationUnitForContents(emitterResult));

        // Create a code formatter for this compilation unit, if needed.
        //
        CodeFormatter codeFormatter = getGenerator().getOptions().codeFormatting ?
          createCodeFormatter(getGenerator().getOptions().codeFormatterOptions, targetFile) : null;

        if (exists(targetFile))
        {
          monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_ExaminingOld_message", new Object[] { targetFile }));
          jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(createInputStream(targetFile), getEncoding(targetFile)));
          String oldContents = jMerger.getTargetCompilationUnitContents();

          monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_PreparingNew_message", new Object[] { targetFile }));
          jMerger.merge();

          newContents = formatCode(jMerger.getTargetCompilationUnitContents(), codeFormatter);
          changed = !oldContents.equals(newContents);

          // If the target is read-only, we can ask the platform to release it, and it may be updated in the process.
          //
          if (changed && isReadOnly(targetFile) && validateEdit(targetFile, createMonitor(monitor, 1)))
          {
            jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(createInputStream(targetFile), getEncoding(targetFile)));
            jMerger.remerge();
            newContents = formatCode(jMerger.getTargetCompilationUnitContents(), codeFormatter);
          }
        }
        else
        {
          changed = true;
          monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_PreparingNew_message", new Object[] { targetFile }));
          
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
        //DMS What if Eclipse is running, but an invalid facade has been specified?  We still should format code, use encoding,...
        newContents = 
          CodeGenUtil.convertFormat(jControlModel.getLeadingTabReplacement(), jControlModel.convertToStandardBraceStyle(), emitterResult);
        if (exists(targetFile))
        {
          monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_ExaminingOld_message", new Object[] { targetFile }));
          String oldContents = getContents(targetFile, null); 
          changed = !oldContents.equals(newContents);
        }
        else
        {
          changed = true;
        }
      }
      monitor.worked(1);

      if (changed)
      {
        String encoding = getEncoding(targetFile);
        byte[] bytes = encoding == null ? newContents.getBytes() : newContents.getBytes(encoding);

        // Apply a redirection pattern, if specified.
        //
        String redirection = getGenerator().getOptions().redirectionPattern;
        boolean redirect = redirection != null && redirection.indexOf("{0}") != -1;

        if (redirect)
        {
          String baseName = MessageFormat.format(redirection, new Object[] { className + ".java" });
          targetFile = targetDirectory.appendSegment(baseName);
          monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingAlternate_message", new Object[] { targetFile }));
        } 

        if (isReadOnly(targetFile))
        {
          if (getGenerator().getOptions().forceOverwrite)
          {
            setWriteable(targetFile);
          }
          else
          {
            targetFile = targetDirectory.appendSegment("." + className + ".java.new");
            monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingDefaultAlternate_message", new Object[] { targetFile }));
          }
        }

        OutputStream outputStream = createOutputStream(targetFile);
        outputStream.write(bytes);
        outputStream.close();
      }
    }
    catch (Exception e)
    {
      //DMS  Do a better job with specific exceptions? Just use chained RuntimeExceptions?
      throw e instanceof RuntimeException ? (RuntimeException)e : new WrappedException(e);
    }
//    catch (JETException exception)
//    {
//      CodeGenEcorePlugin.INSTANCE.log(exception);
//    }
//    catch (Exception exception)
//    {
//      CodeGenEcorePlugin.INSTANCE.log(exception);
//    }
    finally
    {
      clearImportManager();
      monitor.done();
    }
  }

  protected URI toURI(String pathName)
  {
    return URI.createURI(pathName);
  }

  protected URI toPlatformResourceURI(URI uri)
  {
    return URI.createPlatformResourceURI(uri.toString(), true);
  }

  protected Monitor createMonitor(Monitor monitor, int ticks)
  {
    return CodeGenUtil.createMonitor(monitor, ticks);
  }

  protected void createImportManager(String packageName, String className)
  {
    importManager = new ImportManager(packageName);
    importManager.addMasterImport(packageName, className);    
  }

  protected void clearImportManager()
  {
    importManager = null;
  }

  protected ImportManager getImportManager()
  {
    return importManager;
  }

  /**
   * Ensures that a project, corresponding to the first segment in the specifed workspace path, exists. If the project
   * does not exist, a default project will be created. If it does exist and force is true, it will be reconfigured to
   * match the default configuration. The remainder of the path suggests the folder under which source will be generated.
   *
   * <p>In a standalone scenario, this method does nothing, since simply opening a stream via a URIConverter will
   * automatically create the necessary directories.
   */
  protected void ensureProjectExists(String workspacePath, Object object, Object projectType, boolean force, Monitor monitor)
  {
    try
    {
      if (EMFPlugin.IS_ECLIPSE_RUNNING)
      {
        EclipseHelper.ensureProjectExists(workspacePath, object, projectType, force, monitor);
      }
    }
    finally
    {
      monitor.done();
    }
  }

  /**
   * Ensures that a container coresponding to the specified relative URI exists. The URI represents a workspace
   * path for which the project must already exist, since this method doesn't have the necessary information to
   * set up a project. This method will create nested folders within the project, if possible.
   *
   * <p>In a standalone scenario, this method does nothing, since simply opening a stream via a URIConverter will
   * automatically create the necessary directories.  
   */
  protected void ensureContainerExists(URI workspacePath, Monitor monitor)
  {
    try
    {
      if (EMFPlugin.IS_ECLIPSE_RUNNING)
      {
        EclipseHelper.ensureContainerExists(workspacePath.toString(), monitor);
      }
    }
    finally
    {
      monitor.done();
    }
  }

  protected URIConverter getURIConverter()
  {
    ResourceSet resourceSet = getGenerator().getOptions().resourceSet;
    URIConverter result = resourceSet != null ? resourceSet.getURIConverter() : null;
    if (result != null)
    {
      return result;
    }

    if (uriConverter == null)
    {
      uriConverter = new URIConverterImpl();
    }
    return uriConverter;
  }

  protected boolean exists(URI workspacePath)
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      return EclipseHelper.exists(workspacePath.toString());
    }

    URI uri = getURIConverter().normalize(toPlatformResourceURI(workspacePath));
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

  protected boolean isReadOnly(URI workspacePath)
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      return EclipseHelper.isReadOnly(workspacePath.toString());
    }
  
    URI uri = getURIConverter().normalize(toPlatformResourceURI(workspacePath));
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

  protected void setWriteable(URI workspacePath) throws Exception
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      EclipseHelper.setWriteable(workspacePath.toString());
      return;
    }

    URI uri = getURIConverter().normalize(toPlatformResourceURI(workspacePath));
    if ("file".equalsIgnoreCase(uri.scheme()))
    {
      new File(uri.toFileString()).delete();
    }
  }

  protected boolean validateEdit(URI workspacePath, Monitor monitor)
  {
    try
    {
      if (EMFPlugin.IS_ECLIPSE_RUNNING)
      {
        return EclipseHelper.validateEdit(workspacePath.toString(), monitor);
      }
      return false;      
    }
    finally
    {
      monitor.done();
    }
  }

  protected InputStream createInputStream(URI workspacePath) throws Exception
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      return EclipseHelper.createInputStream(workspacePath.toString());
    }
    return getURIConverter().createInputStream(toPlatformResourceURI(workspacePath));
  }

  protected OutputStream createOutputStream(URI workspacePath) throws Exception
  {
    return getURIConverter().createOutputStream(toPlatformResourceURI(workspacePath));
  }

  protected String getContents(URI workspacePath, String encoding) throws Exception
  {
    BufferedInputStream bufferedInputStream = new BufferedInputStream(createInputStream(workspacePath));
    byte[] input = new byte[bufferedInputStream.available()];
    bufferedInputStream.read(input);
    bufferedInputStream.close();
    return encoding == null ? new String(input) : new String(input, encoding);
  }

  protected String getEncoding(URI workspacePath)
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      return EclipseHelper.getEncoding(workspacePath.toString());
    }
    return null;
  }

  protected CodeFormatter createCodeFormatter(Map options, URI workspacePath)
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      return EclipseHelper.createCodeFormatter(options, workspacePath.toString());
    }
    return null;
  }

  /**
   * If non-null, use the specified JDT code formatter to format the given compilation unit contents.
   */
  protected String formatCode(String contents, CodeFormatter codeFormatter)
  {
    return EMFPlugin.IS_ECLIPSE_RUNNING ? EclipseHelper.formatCode(contents, codeFormatter) : contents;
  }

  private static class EclipseHelper
  {
    //DMS this is totally untested.
    public static boolean ensureProjectExists(String workspacePath, Object object, Object projectType, boolean force, Monitor monitor)
    {
      try
      {
        IPath path = new Path(workspacePath);

        if (path.isAbsolute())
        {
          IWorkspace workspace = ResourcesPlugin.getWorkspace();
          IProject project = workspace.getRoot().getProject(path.segment(0));

          if (!project.exists() || force)
          {
            IPath javaSource = path.uptoSegment(1).append("src");
            org.eclipse.emf.codegen.ecore.Generator.createEMFProject
              (javaSource,
               null,
               Collections.EMPTY_LIST,
               monitor,
               org.eclipse.emf.codegen.ecore.Generator.EMF_PLUGIN_PROJECT_STYLE);
          }
          return workspace.getRoot().getProject(path.segment(0)).exists();
        }
      }
      catch (Exception exception)
      {
        //DMS should we let this exception out?
        CodeGenEcorePlugin.INSTANCE.log(exception);
      }
      return false;
    }

    public static boolean ensureContainerExists(String workspacePath, Monitor monitor)
    {
      IPath path = new Path(workspacePath);
      IContainer container = null;
      try
      {
        monitor.beginTask("", path.segmentCount() + 1);
        monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_OpeningFolder_message", new Object[] { path }));

        if (path.isAbsolute())
        {
          IWorkspace workspace = ResourcesPlugin.getWorkspace();
          IProject project = workspace.getRoot().getProject(path.segment(0));
          if (project.exists())
          {
            if (!project.isOpen())
            {
              project.open(BasicMonitor.toIProgressMonitor(CodeGenUtil.createMonitor(monitor, 1)));
            }
            else
            {
              monitor.worked(1);
            }

            container = project;
            for (int i = 1, length = path.segmentCount(); i < length; i++)
            {
              IFolder folder = container.getFolder(new Path(path.segment(i)));
              if (!folder.exists())
              {
                folder.create(false, true, BasicMonitor.toIProgressMonitor(CodeGenUtil.createMonitor(monitor, 1)));
              }
              container = folder;
            }
          }
        }
      }
      catch (Exception exception)
      { 
        //DMS should we let this exception out?
        CodeGenEcorePlugin.INSTANCE.log(exception);
      }
      finally
      {
        monitor.done();
      }
      return container != null && container.getFullPath().equals(path);
    }

    public static boolean exists(String workspacePath)
    {
      return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(workspacePath)).exists();
    }

    public static boolean isReadOnly(String workspacePath)
    {
      return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(workspacePath)).isReadOnly();
    }

    public static void setWriteable(String workspacePath) throws Exception
    {
      IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(workspacePath));
      ResourceAttributes resourceAttributes = file.getResourceAttributes();
      if (resourceAttributes != null)
      {
        resourceAttributes.setReadOnly(false);
        file.setResourceAttributes(resourceAttributes);
      }
    }

    public static boolean validateEdit(String workspacePath, Monitor monitor)
    {
      IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(workspacePath));
      return file.getWorkspace().validateEdit(new IFile [] { file }, monitor).isOK();
    }

    public static InputStream createInputStream(String workspacePath) throws Exception
    {
      return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(workspacePath)).getContents(true);
    }

    public static String getEncoding(String workspacePath)
    {
      try
      {
        return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(workspacePath)).getCharset();
      }
      catch (CoreException exception)
      {
        return null;
      }
    }

    public static CodeFormatter createCodeFormatter(Map options, String workspacePath)
    {
      if (options == null)
      {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(new Path(workspacePath).segment(0));
        if (project != null)
        {
          IJavaProject javaProject = JavaCore.create(project);
          if (javaProject != null)
          {
            options = javaProject.getOptions(true);
          }
        }
      }
      return ToolFactory.createCodeFormatter(options);
    }

    public static String formatCode(String contents, CodeFormatter codeFormatter)
    {
      if (codeFormatter != null)
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
      }
      return contents;
    }
  }
}
