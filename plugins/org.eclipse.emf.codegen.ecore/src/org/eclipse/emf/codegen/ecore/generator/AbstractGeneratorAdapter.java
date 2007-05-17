/**
 * <copyright>
 *
 * Copyright (c) 2006-2007 IBM Corporation and others.
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
 * $Id: AbstractGeneratorAdapter.java,v 1.14 2007/05/17 13:38:21 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.generator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
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
 * A base <code>GeneratorAdapter</code> implementation. This base provides support for
 * {@link org.eclipse.emf.codegen.jet.JETEmitter JET-based} generation of Java and other text artifacts, as well as
 * {@link org.eclipse.emf.codegen.util.GIFEmitter GIFEmitter-based} colourization of icons, via the following methods:
 * <ul>
 * <li>{@link #generateJava(String, String, String, JETEmitter, Object[], Monitor)} for generating Java code, with
 *     {@link org.eclipse.emf.codegen.util.ImportManager import management},
 *     {@link org.eclipse.emf.codegen.merge.java.JMerger merging}, and
 *     {@link org.eclipse.jdt.core.formatter.CodeFormatter code formatting} capabilities.
 * <li>{@link #generateProperties(String, JETEmitter, Object[], Monitor)} for generating property files, with
 *     {@link org.eclipse.emf.codegen.merge.properties.PropertyMerger merging} capability.
 * <li>{@link #generateText(String, JETEmitter, Object[], boolean, String, Monitor)} for generating other text
 *     artifacts.
 * <li>{@link #generateGIF(String, GIFEmitter, String, String, boolean, Monitor) generateGIF()} for generating icons by
 *     colourizing grey-scale images.
 * </ul>
 * 
 * <p>Code generation is supported in the Eclipse IDE or standalone. However, merging and formatting of Java code are
 * not available in the latter scenario.
 * 
 * <p>At a minimum, subclasses must implement {@link #canGenerate(Object, Object)}, to determine whether code can be
 * generated for an object, and {@link #doGenerate(Object, Object, Monitor) doGenerate()}, to actually generate code
 * for it.
 * 
 * <p>They may also override {@link #getCanGenerateChildren(Object, Object)},
 * {@link #getCanGenerateParent(Object, Object)}, {@link #getGenerateChildren(Object, Object)}, and
 * {@link #getGenerateParent(Object, Object)}, to involve more objects these operations, as well as
 * {@link #doPreGenerate(Object, Object)} and {@link #doPostGenerate(Object, Object)}, to perform setup and cleanup
 * before and after code generation.
 * 
 * <p>This class is designed to support singleton generator adapters, where a single adapter instance can be attached
 * to multiple objects of the same type. State relevant to a single object is only cached during a call to
 * {@link #preGenerate(Object, Object)}, {@link #generate(Object, Object, Monitor)}, or
 * {@link #postGenerate(Object, Object)}.
 * 
 * @since 2.2.0
 */
public abstract class AbstractGeneratorAdapter extends SingletonAdapterImpl implements GeneratorAdapter
{
  protected final static String MANIFEST_ENCODING = "UTF-8";
  protected final static String PROPERTIES_ENCODING = "ISO-8859-1";

  protected GeneratorAdapterFactory adapterFactory;

  /**
   * The object for which this adapter is currently being used to generate code. This will only be set during
   * {@link #preGenerate(Object, Object)}, {@link #generate(Object, Object, Monitor)}, and
   * {@link #postGenerate(Object, Object)}.
   */
  protected Object generatingObject;

  /**
   * The message describing what is being done. This can be set during {@link #doGenerate(Object, Object, Monitor)}
   * and will be used in the diagnostic message if an exception is caught.
   */
  protected String message;

  /**
   * All the <code>JETEmitter</code>s used by this adapter. This are cached so that they can be reused at least for
   * different objects with the same adapter. When {@link Generator.Options#dynamicTemplates dynamic templateS} are
   * not being used, they can actually be reused for multiple code generation invocations.
   */
  protected JETEmitter[] jetEmitters;

  /**
   * All the <code>GIFEmitter</code>s used by this adapter. This are cached so that they can be reused at least for
   * different objects with the same adapter. When {@link Generator.Options#dynamicTemplates dynamic templateS} are
   * not being used, they can actually be reused for multiple code generation invocations. 
   */
  protected GIFEmitter[] gifEmitters;

  /**
   * The <code>ImportManager</code> currently being used in generating Java code. This should only be created and
   * cleared via {@link #createImportManager(String, String)} and {@link #clearImportManager()}, so that subclasses may
   * respond to such changes.
   */
  protected ImportManager importManager;

  /**
   * The line delimiter currently being used in generating textual results. This should only be set
   * via {@link #setLineDelimiter(String)} so that subclasses may respond to such changes.
   * @since 2.3
   */
  protected String lineDelimiter;

  /** 
   * An appropriate <code>URIConverter</code> for use during code generation. This is usually applicable to the whole
   * set of objects for which code is being generated, so it can be cached long-term.
   */
  protected URIConverter uriConverter;

  /**
   * If this default constructor is used, the {@link #setAdapterFactory(GeneratorAdapterFactory) setAdapterFactory()}
   * method must be called immediately to set the generator adapter factory that created this adapter.
   */
  public AbstractGeneratorAdapter()
  {
    super();
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

  /**
   * Returns <code>true</code> when the type is this adapter's {@link #getAdapterFactory() factory}.
   * This allows generator adapters from different factories to be attached to the same objects.
   */
  @Override
  public boolean isAdapterForType(Object type)
  {
    return type == adapterFactory;
  }

  /**
   * Returns an empty collection, indicating that by default no children are involved in determining whether code can be
   * generated for an object.
   */
  public Collection<?> getCanGenerateChildren(Object object, Object projectType)
  {
    return Collections.EMPTY_LIST;
  }

  /**
   * Returns null, indicating that by default no parent is involved in determining whether code can be generated for an
   * object.
   */
  public Object getCanGenerateParent(Object object, Object projectType)
  {
    return null;
  }

  public abstract boolean canGenerate(Object object, Object projectType);

  /**
   * Returns an empty collection, indicating that by default there are no children of an object for which code should
   * be generated.
   */
  public Collection<?> getGenerateChildren(Object object, Object projectType)
  {
    return Collections.EMPTY_LIST;
  }

  /**
   * Returns null, indicating that by default there is no parent of an object for which could should be generated.
   */
  public Object getGenerateParent(Object object, Object projectType)
  {
    return null;
  }

  /**
   * Caches the object as {@link #generatingObject}, calls {@link #doPreGenerate(Object, Object)}, and clears it again.
   * If {@link Generator.Options#dynamicTemplates dynamic templates} are enabled on the generator, any cached
   * {@link #jetEmitters JETEmitter}s and {@link #gifEmitters GIFEmitter}s are also removed, so that templates
   * will be recompiled during {@link #generate(Object, Object, Monitor)}.
   */
  public final Diagnostic preGenerate(Object object, Object projectType)
  {
    try
    {
      generatingObject = object;
      if (getGenerator().getOptions().dynamicTemplates)
      {
        jetEmitters = null;
        gifEmitters = null;
      }
      return doPreGenerate(object, projectType);
    }
    finally
    {
      generatingObject = null;
    }
  }

  /**
   * Does nothing and returns {@link org.eclipse.emf.common.util.Diagnostic#OK_INSTANCE OK}. Override this to perform
   * setup for code generation.
   */
  protected Diagnostic doPreGenerate(Object object, Object projectType)
  {
    return Diagnostic.OK_INSTANCE;
  }

  /**
   * If code can be generated for the object, as determined by {@link #canGenerate(Object, Object)}, delegates code
   * generation to {@link #doGenerate(Object, Object, Monitor)}. Otherwise, simply returns
   * {@link Diagnostic#OK_INSTANCE OK}. The object is cached as {@link #generatingObject} and the {@link #message} is
   * cleared before calling {@link #doGenerate(Object, Object, Monitor)}; both are cleared again afterwards.
   * 
   * @link #canGenerate(Object, Object)
   * @link #doGenerate(Object, Object, Monitor)
   */
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

  /**
   * Implement this to perform code generation of the given project type for the specified object. Use the monitor
   * to update progress for this long-running operation. Any exceptions thrown will be converted into a diagnostic
   * and returned by {@link #generate(Object, Object, Monitor) generate()}. If a {@link #message} is set, it will
   * be used in this diagnostic.
   */
  protected abstract Diagnostic doGenerate(Object object, Object projectType, Monitor monitor) throws Exception;

  /**
   * Caches the object as {@link #generatingObject}, calls {@link #doPostGenerate(Object, Object)}, and clears it again.
   * If {@link Generator.Options#dynamicTemplates dynamic templates} are enabled on the generator, any cached
   * {@link #jetEmitters JETEmitter} and {@link #gifEmitters GIFEmitters} are also removed, so that templates will be
   * recompiled during the next {@link #generate(Object, Object, Monitor)}.
   */
  public final Diagnostic postGenerate(Object object, Object projectType)
  {
    try
    {
      generatingObject = object;
      if (getGenerator().getOptions().dynamicTemplates)
      {
        jetEmitters = null;
        gifEmitters = null;
      }
      return doPostGenerate(object, projectType);
    }
    finally
    {
      generatingObject = null;
    }
  }

  /**
   * Does nothing and returns {@link org.eclipse.emf.common.util.Diagnostic#OK_INSTANCE OK}. Override this to perform
   * cleanup from code generation.
   */
  protected Diagnostic doPostGenerate(Object object, Object projectType)
  {
    return Diagnostic.OK_INSTANCE;
  }

  /**
   * Converts the given exception to a <code>Diagnostic</code>. The <code>currentMessage</code>, if non-null, should
   * describe what was being done when the exception occurred, and will be used in forming the diagnostic's message.
   */
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

  /**
   * Returns the generator for this adapter's factory.
   */
  protected Generator getGenerator()
  {
    return getAdapterFactory().getGenerator();
  }

  /**
   * The information required to construct and initialize a {@link org.eclipse.emf.codegen.jet.JETEmitter JETEmitter},
   * namely the file name of the template (relative to the template path) and the qualified name of the template class.
   */
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

  /**
   * Returns the <code>JETEmitter</code> for the <code>JETEmitterDescriptor</code> at the index specified by
   * <code>id</code> in the given array. If the <code>JETEmitter</code> has not yet been created, it will be created,
   * initialized, and cached at the same index in {@link #jetEmitters}. 
   * 
   * @param jetEmitterDescriptors an array of descriptors for all of the <code>JETEmitter</code>s used by this
   *         generator adapter.
   * @param id the identifier for the desired <code>JETEmitter</code>, also the index of the descriptor in the array.
   */
  protected JETEmitter getJETEmitter(JETEmitterDescriptor[] jetEmitterDescriptors, int id)
  {
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

  /**
   * Creates and initializes a <code>JETEmitter</code> according to the given descriptor.
   */
  protected JETEmitter createJETEmitter(JETEmitterDescriptor jetEmitterDescriptor)
  {
    JETEmitter jetEmitter = new JETEmitter(getTemplatePath(), jetEmitterDescriptor.templatePathName, getClass().getClassLoader());

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

  /*
   * Returns the dynamic template path, an ordered list of URIs corresponding to locations under which to find
   * templates.
   */
  private String[] getTemplatePath()
  {
    // Consult the generator option for backwards compatibility.
    //
    @SuppressWarnings("deprecation")
    String[] legacyPath = getGenerator().getOptions().templatePath;
    if (legacyPath != null)
    {
      return legacyPath;
    }

    List<String> result = new ArrayList<String>(getUserTemplatePath());
    result.addAll(getBaseTemplatePath());

    return result.toArray(new String[result.size()]);
  }

  /**
   * Returns the user-specified portion of the dynamic template path, an ordered list of URIs corresponding to locations
   * under which to find templates. This implementation returns an empty list.
   * 
   * <p>This method is only consulted if the generator's {@link Generator.Options#templatePath templatePath} option is
   * set to null.
   * 
   * @see Generator.Options#templatePath
   * @see org.eclipse.emf.codegen.jet.JETEmitter#JETEmitter(String[], String)
   * @see org.eclipse.emf.codegen.jet.JETCompiler#find(String[], String)
   * @since org.eclipse.emf.codegen.ecore 2.2.2
   */
  protected List<String> getUserTemplatePath()
  {
    return Collections.emptyList();
  }

  /*
   * Returns the base portion of the dynamic template path.
   */
  private List<String> getBaseTemplatePath()
  {
    List<String> result = new ArrayList<String>();
    addBaseTemplatePathEntries(result);
    return result;
  }

  /**
   * Adds template locations to the base portion of the dynamic template path, an ordered list of URIs corresponding to
   * locations under which to find templates. Order matters, so the pattern is to add local entries first, and then
   * invoke the superclass implementation. This implementation does nothing.
   * 
   * <p>This method is only consulted if the generator's {@link Generator.Options#templatePath templatePath} option is
   * set to null.
   * 
   * @see Generator.Options#templatePath
   * @see org.eclipse.emf.codegen.jet.JETEmitter#JETEmitter(String[], String)
   * @see org.eclipse.emf.codegen.jet.JETCompiler#find(String[], String)
   * @since org.eclipse.emf.codegen.ecore 2.2.2
   */
  protected void addBaseTemplatePathEntries(List<String> templatePath)
  {
    // Subclasses may override
  }

  protected static final Class<?>[] OBJECT_ARGUMENT = new Class[]{ Object.class };

  /**
   * If {@link Generator.Options#dynamicTemplates dynamic templates} are not being used, attempts to set the emitter to
   * use an existing, pre-compiled template class.
   */
  protected void setStaticTemplateClass(JETEmitter jetEmitter, String className)
  {
    if (!getGenerator().getOptions().dynamicTemplates)
    {
      try
      {
        Class<?> templateClass = getClass().getClassLoader().loadClass(className);
        Method emitterMethod = templateClass.getDeclaredMethod("generate", OBJECT_ARGUMENT);
        jetEmitter.setMethod(emitterMethod);
      }
      catch (Exception exception)
      {
        // It's okay for there not be a precompiled template, so fail quietly.
      }
    }
  }

  /**
   * Override this to {@link org.eclipse.emf.codegen.jet.JETEmitter#addVariable(String, String) add classpath variables}
   * to the JETEmitter. These will be used to build and execute dynamic templates.
   * 
   * @link org.eclipse.emf.codegen.jet.JETEmitter#addVariable(String, String)
   */
  protected void addClasspathEntries(JETEmitter jetEmitter) throws JETException
  {
    if (getGenerator().getOptions().templateClasspath!= null)
    {
      for (String additionalClasspathEntry : getGenerator().getOptions().templateClasspath)
      {
        int index = additionalClasspathEntry.indexOf('=');
        if (index == -1)
        {
          jetEmitter.addVariable(additionalClasspathEntry, additionalClasspathEntry);
        }
        else
        {
          jetEmitter.addVariable(additionalClasspathEntry.substring(0, index), additionalClasspathEntry.substring(index + 1));
        }
      }
    }
  }

  /**
   * Returns the <code>GIFEmitter</code> for the input path name at the index specified by <code>id</code> in the given
   * array. If the <code>GIFEmitter</code> has not yet been created, it will be created and cached at the same index in
   * {@link #gifEmitters}. 
   * 
   * @param inputPathNames an array of input path names for all the <code>GIFEmitter</code>s used by this generator
   *         adapter. These are the file names, relative to the template path, of grey-scale images to be colourized.
   * @param id the identifier for the desired <code>GIFEmitter</code>, also the index of the input path name in the
   *         array.
   */
  protected GIFEmitter getGIFEmitter(String[] inputPathNames, int id)
  {
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

  /**
   * Creates a <code>GIFEmitter</code> based on the image at the give template-path-relative file name.
   */
  protected GIFEmitter createGIFEmitter(String inputPathName)
  {
    return new GIFEmitter(JETCompiler.find(getTemplatePath(), inputPathName));
  }

  /**
   * Generates an arbitrary text artifact using JET.
   * 
   * @param targetPathName the path name of the target file. This should be a workspace path; when running standalone,
   *         it will be converted to a platform resource URI that should be mapped to a physical file URI by the
   *         {@link #getURIConverter() URIConverter}.
   * @param jetEmitter the <code>JETEmitter</code> to use for generating the text.
   * @param arguments the argument array to pass to the <code>JETEmitter</code>'s
   *         {@link JETEmitter#generate(Monitor, Object[]) generate(Monitor, Object[])} method. If null, an array will
   *         be constructed containing only the {@link #generatingObject object} for which code is being generated. 
   * @param overwrite whether an existing file should be overritten.
   * @param encoding an override of the default encoding. If "ISO-8859-1" is specified,
   *         {@link org.eclipse.emf.codegen.util.CodeGenUtil#unicodeEscapeEncode(String) Unicode escape encoding}  
   *         is performed to represent non-Latin characters. The default encoding, when running under Eclipse, is
   *         determined from the workspace. Failing that, or in standalone, the platform default is used.
   * @param monitor the <code>Monitor</code> through which to report progress.
   * 
   * <p>This method also consults the following {@link Generator#getOptions() generator options}:
   * <ul>
   * <li>{@link Generator.Options#redirectionPattern redirectionPattern}
   * <li>{@link Generator.Options#forceOverwrite forceOverwrite}
   * <li>{@link Generator.Options#dynamicTemplates dynamicTemplates}
   * <li>{@link Generator.Options#resourceSet resourceSet}
   * </ul>
   */
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
        setLineDelimiter(getLineDelimiter(targetFile, encoding));
        String emitterResult = jetEmitter.generate(createMonitor(monitor, 1), arguments, getLineDelimiter());

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
      setLineDelimiter(null);
      monitor.done();
    }
  }

  /**
   * Generates a properties file using JET, with {@link org.eclipse.emf.codegen.merge.properties.PropertyMerger merging}
   * capability.
   * 
   * <p>The encoding used for the generated file is "ISO-8859-1".
   * {@link org.eclipse.emf.codegen.util.CodeGenUtil#unicodeEscapeEncode(String) Unicode escape encoding} is
   * performed to represent non-Latin characters.
   *
   * @param targetPathName the path name of the target file. This should be a workspace path; when running standalone,
   *         it will be converted to a platform resource URI that should be mapped to a physical file URI by the
   *         {@link #getURIConverter() URIConverter}.
   * @param jetEmitter the <code>JETEmitter</code> to use for generating the text.
   * @param arguments the argument array to pass to the <code>JETEmitter</code>'s
   *         {@link JETEmitter#generate(Monitor, Object[]) generate(Monitor, Object[])} method. If null, an array will
   *         be constructed containing only the {@link #generatingObject object} for which code is being generated. 
   * @param monitor the <code>Monitor</code> through which to report progress.
   * 
   * <p>This method also consults the following {@link Generator#getOptions() generator options}:
   * <ul>
   * <li>{@link Generator.Options#redirectionPattern redirectionPattern}
   * <li>{@link Generator.Options#forceOverwrite forceOverwrite}
   * <li>{@link Generator.Options#dynamicTemplates dynamicTemplates}
   * <li>{@link Generator.Options#resourceSet resourceSet}
   * </ul>
   */
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
      setLineDelimiter(getLineDelimiter(targetFile, PROPERTIES_ENCODING));
      String emitterResult = CodeGenUtil.unicodeEscapeEncode(jetEmitter.generate(createMonitor(monitor, 1), arguments, getLineDelimiter()));
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
      setLineDelimiter(null);
      monitor.done();
    }
  }

  /**
   * Generates an icon using a {@link org.eclipse.emf.codegen.util.GIFEmitter GIFEmitter} to colourize a grey-scale GIF
   * image. The colours to use are calculated from one or, optionally, two text keys. 
   * 
   * @param targetPathName the path name of the target file. This should be a workspace path; when running standalone,
   *         it will be converted to a platform resource URI that should be mapped to a physical file URI by the
   *         {@link #getURIConverter() URIConverter}.
   * @param gifEmitter the <code>GIFEmitter</code> to use for generating the icon.
   * @param parentKey the key used to determine the first colour set.
   * @param childKey the key used to determine the second colour set. If null, this key is ignored.
   * @param overwrite whether an existing file should be overritten.
   * @param monitor the <code>Monitor</code> through which to report progress.
   * 
   * <p>This method also consults the following {@link Generator#getOptions() generator options}:
   * <ul>
   * <li>{@link Generator.Options#redirectionPattern redirectionPattern}
   * <li>{@link Generator.Options#forceOverwrite forceOverwrite}
   * <li>{@link Generator.Options#resourceSet resourceSet}
   * </ul>
   */
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

  /**
   * Generates a Java source file using JET, with {@link org.eclipse.emf.codegen.util.ImportManager import management}
   * and, when running under Eclipse, {@link org.eclipse.emf.codegen.merge.java.JMerger merging} and
   * {@link org.eclipse.jdt.core.formatter.CodeFormatter code formatting} capabilities.
   * 
   * <p>When running under Eclipse, the encoding for the file is determined from the workspace. Failing that, or in
   * standalone, the platform default is used.
   * 
   * @param targetPath the workspace path of the directory in or under which the file will be created, depending on the
   *         specified package name. When running standalone, this path will be converted to a platform resource URI
   *         that should be mapped to a physical file URI by the {@link #getURIConverter() URIConverter}.
   * @param packageName the package name for the generated compilation unit.
   * @param className the name of the public class in the generated compilation unit.
   * @param jetEmitter the <code>JETEmitter</code> to use for generating the code.
   * @param arguments the argument array to pass to the <code>JETEmitter</code>'s
   *         {@link JETEmitter#generate(Monitor, Object[]) generate(Monitor, Object[])} method. If null, an array will
   *         be constructed containing only the {@link #generatingObject object} for which code is being generated. 
   * @param monitor the <code>Monitor</code> through which to report progress.
   * 
   * <p>This method also consults the following {@link Generator#getOptions() generator options}:
   * <ul>
   * 
   * <li>{@link Generator.Options#redirectionPattern redirectionPattern}
   * <li>{@link Generator.Options#forceOverwrite forceOverwrite}
   * <li>{@link Generator.Options#dynamicTemplates dynamicTemplates}
   * <li>{@link Generator.Options#mergerFacadeHelperClass mergerFacadeHelperClass}
   * <li>{@link Generator.Options#mergeRulesURI mergeRulesURI}
   * <li>{@link Generator.Options#codeFormatting codeFormatting} 
   * <li>{@link Generator.Options#codeFormatterOptions codeFormatterOptions} 
   * <li>{@link Generator.Options#resourceSet resourceSet}
   * </ul>
   */
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
      setLineDelimiter(getLineDelimiter(targetFile, getEncoding(targetFile)));
      String emitterResult = jetEmitter.generate(createMonitor(monitor, 1), arguments, getLineDelimiter());

      boolean changed = true;
      String newContents = emitterResult;
      boolean targetExists = exists(targetFile);

      JControlModel jControlModel = getGenerator().getJControlModel();      
      boolean mergeSuccessful = jControlModel.canMerge();
      
      //DMS This is not right. It replaced "if (EMFPlugin.IS_ECLIPSE_RUNNING)" but can also be false if an invalid facade has been specified.
      if (mergeSuccessful)
      {
        JMerger jMerger = new JMerger(jControlModel);
        jMerger.setFixInterfaceBrace(jControlModel.getFacadeHelper().fixInterfaceBrace());
        
        try
        {
          jMerger.setSourceCompilationUnit(jMerger.createCompilationUnitForContents(emitterResult));
        }
        catch(RuntimeException runtimeException)
        {
          if (targetExists)
          {
            throw runtimeException;
          }
          else
          {
            mergeSuccessful = false;
          }
        }

        if (mergeSuccessful)
        {
          // Create a code formatter for this compilation unit, if needed.
          //
          Object codeFormatter = getGenerator().getOptions().codeFormatting ?
            createCodeFormatter(getGenerator().getOptions().codeFormatterOptions, targetFile) : null;
  
          if (targetExists)
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
      }
      
      if (!mergeSuccessful)
      {
        //DMS What if Eclipse is running, but an invalid facade has been specified?  We still should format code, use encoding,...
        newContents = 
          CodeGenUtil.convertFormat(jControlModel.getLeadingTabReplacement(), jControlModel.convertToStandardBraceStyle(), emitterResult);
        if (targetExists)
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
      setLineDelimiter(null);
      monitor.done();
    }
  }

  /**
   * Converts the given workspace path to a <code>URI</code>. No encoding is performed, so the URI may contain invalid
   * characters. Such a URI is only used to easily acess and manipulate parts of the workspace path. It can then be
   * converted back to a string and an {@link org.eclipse.core.runtime.IPath IPath} for use in the workspace, or to an 
   * encoded {@link #toPlatformResourceURI(URI) platform resource URI} for direct use with the EMF persistence
   * framework.
   */
  protected URI toURI(String pathName)
  {
    return URI.createURI(pathName);
  }

  /**
   * Converts the given workspace path URI to an absolute, platform resource URI, with encoding to eliminate any
   * invalid characters.
   */
  protected URI toPlatformResourceURI(URI uri)
  {
    return URI.createPlatformResourceURI(uri.toString(), true);
  }

  /**
   * Creates and returns a sub-monitor for the given progress monitor. When running standalone, the same monitor is
   * actually returned.
   * 
   * @param monitor the parent monitor
   * @param ticks the number of work ticks allocated from the parent monitor
   */
  protected Monitor createMonitor(Monitor monitor, int ticks)
  {
    return CodeGenUtil.createMonitor(monitor, ticks);
  }

  /**
   * Creates and caches an {@link org.eclipse.emf.codegen.util.ImportManager ImportManager} for use in generating Java
   * code.
   */
  protected void createImportManager(String packageName, String className)
  {
    importManager = new ImportManager(packageName);
    importManager.addMasterImport(packageName, className);    
  }

  /**
   * Clears the cached {@link org.eclipse.emf.codegen.util.ImportManager ImportManager}.
   */
  protected void clearImportManager()
  {
    importManager = null;
  }

  /**
   * Returns the {@link org.eclipse.emf.codegen.util.ImportManager ImportManager} that is currently in use for
   * generating Java code, or null if there is none.
   */
  protected ImportManager getImportManager()
  {
    return importManager;
  }

  /**
   * Sets the current line delimiter used for generating textual results.
   * @param lineDelimiter
   * @since 2.3
   */
  protected void setLineDelimiter(String lineDelimiter)
  {
    this.lineDelimiter = lineDelimiter;
  }

  /**
   * Returns the current line delimiter used for generating textual results.
   * @since 2.3
   */
  protected String getLineDelimiter()
  {
    return lineDelimiter;
  }

  /**
   * Ensures that a project, corresponding to the first segment in the specifed workspace path, exists. If the project
   * does not exist, a default project will be created. If it does exist and <code>force</code> is true, it will be
   * reconfigured to match the default configuration. The remainder of the path suggests the folder under which source
   * will be generated.
   *
   * <p>When running standalone, this method does nothing, since simply opening a stream via a <code>URIConverter</code>
   * will automatically create the necessary directories.
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
   * <p>When running standalone, this method does nothing, since simply opening a stream via a <code>URIConverter</code>
   * will automatically create the necessary directories.
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

  /**
   * Returns an appropriate <code>URIConverter</code> for use during code generation.
   */
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

  /**
   * @since 2.3
   */
  public String getLineDelimiter(URI workspacePath, String encoding)
  {
    InputStream inputStream = null;
    try
    {
      inputStream = createInputStream(workspacePath);
      Reader reader = encoding == null ? new InputStreamReader(inputStream) : new InputStreamReader(inputStream, encoding);
      char [] text = new char [4048];
      char target = 0;
      for (int count = reader.read(text); count > -1; count = reader.read(text))
      {
        for (int i = 0; i < count; ++i)
        {
          char character = text[i];
          if (character == '\n')
          {
            if (target == '\n')
            {
              return "\n";
            }
            else if (target == '\r')
            {
              return "\r\n";
            }
            else
            {
              target = '\n';
            }
          }
          else if (character == '\r')
          {
            if (target == '\n')
            {
              return "\n\r";
            }
            else if (target == '\r')
            {
              return "\r";
            }
            else
            {
              target = '\r';
            }
          }
        }
      }
    }
    catch (Exception exception)
    {
      // If we can't determine it by reading the file,
      // look at the preferences instead.
    }
    finally
    {
      if (inputStream != null)
      {
        try
        {
          inputStream.close();
        }
        catch (IOException exception)
        {
          CodeGenEcorePlugin.INSTANCE.log(exception);
        }
      }
    }

    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      return EclipseHelper.getLineDelimiter(workspacePath.toString());
    }
    return System.getProperty(Platform.PREF_LINE_SEPARATOR);
  }

  /**
   * Determines whether a given workspace path URI represents a file that already exists.
   */
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
        InputStream inputStream = getURIConverter().createInputStream(uri);
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
   * Determines whether a given workspace path URI represents a read-only file.
   */
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

  /**
   * Sets the file represented by a workspace path URI to be writable. When running standalone, this actually
   * <em>deletes</em> the file, since there is no Java platform API for making a file writable.
   */
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

  /**
   * When running under Eclipse, performs an
   * {@link org.eclipse.core.resources.IWorkspace#validateEdit(IFile[], Object) IWorkspace.validateEdit(IFile[], Object)}
   * for the file identified by the given workspace path URI. This notifies the workspace that the file will be edited,
   * providing it the opportunity to prepare the files if required. When running standalone, does nothing.
   */
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

  /**
   * Creates an <code>InputStream</code> for the file identified by the given workspace path URI.
   */
  protected InputStream createInputStream(URI workspacePath) throws Exception
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      return EclipseHelper.createInputStream(workspacePath.toString());
    }
    return getURIConverter().createInputStream(toPlatformResourceURI(workspacePath));
  }

  /**
   * Creates an <code>OutputStream</code> for the file identified by the given workspace path URI.
   */
  protected OutputStream createOutputStream(URI workspacePath) throws Exception
  {
    return getURIConverter().createOutputStream(toPlatformResourceURI(workspacePath));
  }

  /**
   * Returns the contents of the file identified by the given workspace path URI, as read using the specified encoding.
   */
  protected String getContents(URI workspacePath, String encoding) throws Exception
  {
    BufferedInputStream bufferedInputStream = new BufferedInputStream(createInputStream(workspacePath));
    byte[] input = new byte[bufferedInputStream.available()];
    bufferedInputStream.read(input);
    bufferedInputStream.close();
    return encoding == null ? new String(input) : new String(input, encoding);
  }

  /**
   * When running under Eclipse, queries the workspace to determine the correct encoding for the file identified by
   * the given workspace path URI. When running standalone, returns null.
   */
  protected String getEncoding(URI workspacePath)
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      return EclipseHelper.getEncoding(workspacePath.toString());
    }
    return null;
  }

  /**
   * When running under Eclipse, returns a code formatter; when standalone, returns null. If <code>options</code> is
   * non-null, the code formatting options it specifies are used to create the formatter. Otherwose, the project is
   * obtained from the given workspace path URI, and its default formatting options are used.
   *
   * @return the created code formatter. If non-null, this will be an instance of
   *          {@link org.eclipse.jdt.core.formatter.CodeFormatter CodeFormatter}; however, it is not statically typed
   *          as such to avoid failure when running standalone.
   */
  protected Object createCodeFormatter(Map<?, ?> options, URI workspacePath)
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      return EclipseHelper.createCodeFormatter(options, workspacePath.toString());
    }
    return null;
  }

  /**
   * If non-null, use the specified code formatter to format the given compilation unit contents.
   *
   * @return the formatted version of the contents. If the code formatter is null or when running standalone, the
   *          contents are returned unchanged. 
   */
  protected String formatCode(String contents, Object codeFormatter)
  {
    return EMFPlugin.IS_ECLIPSE_RUNNING ? EclipseHelper.formatCode(contents, codeFormatter) : contents;
  }

  /*
   * All Eclipse-dependent operations are delegated to this class. This pattern avoids any runtime failure due to
   * missing dependencies in the standalone case.
   */
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
               Collections.<IProject>emptyList(),
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

    /**
     * @since 2.3
     */
    public static String getLineDelimiter(String workspacePath)
    {
      return 
        Platform.getPreferencesService().getString
          (Platform.PI_RUNTIME, 
           Platform.PREF_LINE_SEPARATOR, 
           System.getProperty(Platform.PREF_LINE_SEPARATOR), 
           new IScopeContext[] { new ProjectScope(ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(workspacePath)).getProject()), new InstanceScope() });
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

    public static Object createCodeFormatter(Map<?, ?> options, String workspacePath)
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

    public static String formatCode(String contents, Object codeFormatter)
    {
      if (codeFormatter instanceof CodeFormatter)
      {
        IDocument doc = new Document(contents);
        TextEdit edit = ((CodeFormatter)codeFormatter).format(CodeFormatter.K_COMPILATION_UNIT, doc.get(), 0, doc.get().length(), 0, null);
  
        if (edit != null)
        {
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
      }
      return contents;
    }
  }
}
