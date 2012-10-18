/**
 * Copyright (c) 2006-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.generator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.IBufferChangedListener;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.WorkingCopyOwner;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTRequestor;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.rewrite.ImportRewrite;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.osgi.util.ManifestElement;
import org.eclipse.text.edits.TextEdit;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.generator.Generator.Options;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.jet.JETCompiler;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JImport;
import org.eclipse.emf.codegen.merge.java.facade.JNode;
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
import org.eclipse.emf.ecore.resource.impl.ContentHandlerImpl;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.PlatformResourceURIHandlerImpl;
import org.osgi.framework.BundleException;

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
 * <p>Code generation is supported in the Eclipse IDE or stand-alone. However, merging and formatting of Java code are
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
   * @see #canGenerate(Object, Object)
   * @see #doGenerate(Object, Object, Monitor)
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

  protected static final Class<?>[] OBJECT_ARGUMENT = new Class[] { Object.class };

  /**
   * If {@link Generator.Options#dynamicTemplates dynamic templates} are not being used, 
   * attempts to set the emitter to use an existing, precompiled template class
   * that has a method with signature <code>generate(Object)</code>.
   * @see #setStaticTemplateClass(JETEmitter, String, String, Class[])
   */
  protected void setStaticTemplateClass(JETEmitter jetEmitter, String className)
  {
    setStaticTemplateClass(jetEmitter, className, "generate", OBJECT_ARGUMENT);
  }

  /**
   * If {@link Generator.Options#dynamicTemplates dynamic templates} are not being used, 
   * attempts to set the emitter to use an existing, precompiled template class
   * that has the given method name and argument types.
   * @since 2.5
   */
  protected void setStaticTemplateClass(JETEmitter jetEmitter, String className, String methodName, Class<?>[] arguments)
  {
    if (!getGenerator().getOptions().dynamicTemplates)
    {
      try
      {
        Class<?> templateClass = getClass().getClassLoader().loadClass(className);
        Method emitterMethod = templateClass.getDeclaredMethod(methodName, arguments);
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
   * @see org.eclipse.emf.codegen.jet.JETEmitter#addVariable(String, String)
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
   * @param targetPathName the path name of the target file. This should be a workspace path; when running stand-alone,
   *         it will be converted to a platform resource URI that should be mapped to a physical file URI by the
   *         {@link #getURIConverter() URIConverter}.
   * @param jetEmitter the <code>JETEmitter</code> to use for generating the text.
   * @param arguments the argument array to pass to the <code>JETEmitter</code>'s
   *         {@link JETEmitter#generate(Monitor, Object[]) generate(Monitor, Object[])} method. If null, an array will
   *         be constructed containing only the {@link #generatingObject object} for which code is being generated. 
   * @param overwrite whether an existing file should be overwritten.
   * @param encoding an override of the default encoding. If "ISO-8859-1" is specified,
   *         {@link org.eclipse.emf.codegen.util.CodeGenUtil#unicodeEscapeEncode(String) Unicode escape encoding}  
   *         is performed to represent non-Latin characters. The default encoding, when running under Eclipse, is
   *         determined from the workspace. Failing that, or in stand-alone, the platform default is used.
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
          if (changed)
          {
            if (targetPathName.endsWith("/plugin.xml"))
            {
              emitterResult = mergePluginXML(generatingObject instanceof GenBase ? ((GenBase)generatingObject).getGenModel().getPluginKey() : "", oldContents, emitterResult);
              changed = !emitterResult.equals(oldContents);
            }
            else if (targetPathName.endsWith("/MANIFEST.MF"))
            {
              emitterResult = mergeManifest(oldContents, emitterResult);
              changed = !emitterResult.equals(oldContents);
            }
          }
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
   * Information about extensions in a plugin.xml.
   * @since 2.9
   */
  protected static final class ExtensionData
  {
    public String extensionPointID;
    public String identifier;

    public String generated;

    public int start;
    public int end;

    public String content;
    public String lineSeparator;

    @Override
    public boolean equals(Object object)
    {
      if (object == this)
      {
        return true;
      }
      else if (object instanceof ExtensionData)
      {
        ExtensionData extension = (ExtensionData)object;
        return extension.extensionPointID.equals(extensionPointID) && (extension.identifier == null ? identifier == null : extension.identifier.equals(identifier));
      }
      else
      {
        return false;
      }
    }

    @Override
    public int hashCode()
    {
      return extensionPointID.hashCode() ^ (identifier == null ? 0 : identifier.hashCode());
    }

    @Override
    public String toString()
    {
      return extensionPointID + ":" + identifier + ":" + generated;
    }
  }

  /**
   * A pattern for matching extensions.
   * @since 2.9
   */
  protected static final Pattern EXTENSION_POINT_PATTERN = Pattern.compile("[ \t]*<extension[^>]+point\\s*=['\"]([^'\"]+)['\"].*?(?:id|class)\\s*=\\s*['\"]([^'\"]+)['\"].*?</extension>[ \t]*(\n\r|\r\n|\n|\r)", Pattern.DOTALL);

  /**
   * A pattern for matching the <code>@generated</code> comment in an extension.
   * @since 2.9
   */
  protected static final Pattern GENERATED_PATTERN = Pattern.compile("<!-- *@generated *([^ ]+) *-->");

  /**
   * A pattern for matching the closing tag of a plugin.xml.
   * @since 2.9
   */
  protected static final Pattern PLUGIN_END_TAG_PATTERN = Pattern.compile(".*(\n\r|\r\n|\n|\r)?[ \t]*(</plugin>)");

  /**
   * A pattern for matching trailing blank lines.
   * @since 2.9
   */
  protected static final Pattern BLANK_LINES_PATTERN = Pattern.compile("([ \t]*(\n\r|\r\n|\n|\r))+");

  /**
   * Collect information about extensions in a plugin.xml's contents.
   * @since 2.9
   */
  protected List<ExtensionData> getExtensionData(String contents)
  {
    // Collect information about extensions by finding all matches of the pattern.
    //
    List<ExtensionData> extensions = new ArrayList<AbstractGeneratorAdapter.ExtensionData>();
    Matcher matcher = EXTENSION_POINT_PATTERN.matcher(contents);
    while (matcher.find())
    {
      ExtensionData extension = new ExtensionData();
      extension.extensionPointID = matcher.group(1);
      extension.identifier = matcher.group(2);
      extension.start = matcher.start();
      extension.end = matcher.end();
      extension.content = matcher.group();
      extension.lineSeparator = matcher.group(3);
      extensions.add(extension);

      // Look for the @generated comment.
      //
      Matcher generatedMatcher = GENERATED_PATTERN.matcher(extension.content);
      if (generatedMatcher.find())
      {
        extension.generated = generatedMatcher.group(1);
      }
    }
    return extensions;
  }

  /**
   * Merge the contents of two plugin.xmls.
   * @since 2.9
   */
  protected String mergePluginXML(String generated, String oldContents, String newContents)
  {
    // Fetch the information about the extensions.
    //
    List<ExtensionData> oldExtensions = getExtensionData(oldContents);
    List<ExtensionData> newExtensions = getExtensionData(newContents);

    // Find the index of the closing tag and remember whether it was preceded by a line separator.
    //
    int end = -1;
    Matcher matcher = PLUGIN_END_TAG_PATTERN.matcher(oldContents);
    boolean isEmpty = false;
    if (matcher.find())
    {
      isEmpty = matcher.group(1) == null;
      end = matcher.start(2);
    }

    // Pull in the new extension content.
    //
    for (int i = 0, size = oldExtensions.size(); i < size; ++i)
    {
      // Determine the matching new extension.
      //
      ExtensionData oldExtension = oldExtensions.get(i);
      int index = newExtensions.indexOf(oldExtension);
      if (index == -1)
      {
        // If there is no match and this extension was generated...
        //
        if (generated.equals(oldExtension.generated))
        {
          // Set the old extension up to be swept, including trailing blank lines for removal.
          //
          oldExtension.content = "";
          Matcher trailingMatching = BLANK_LINES_PATTERN.matcher(oldContents);
          if (trailingMatching.find(oldExtension.end) && oldExtension.end == trailingMatching.start())
          {
            oldExtension.end = trailingMatching.end();
          }
        }
      }
      else
      {
        // If the new match has the same non-null generation key.
        //
        ExtensionData newExtension = newExtensions.get(index);
        if (oldExtension.generated != null && oldExtension.generated.equals(newExtension.generated))
        {
          // Set up the old extension up to pull the new content.
          //
          oldExtension.content = newExtension.content;
        }

        // Set up the new extension to block it being pushed.
        //
        newExtension.content = null;
      }
    }

    // Push in the new extension content, except for the ones blocked during pull analysis.
    //
    LOOP:
    for (int i = 0, size = newExtensions.size(); i < size; ++i)
    {
      ExtensionData newExtension = newExtensions.get(i);

      // If the new extension isn't blocked and it's marked for merging...
      //
      if (newExtension.content != null && newExtension.generated != null)
      {
        // To find an insertion point, look backward in the new extensions for a matching one in the old extensions after which to insert the new one.
        //
        for (int j = i - 1; j >= 0; --j)
        {
          int index = oldExtensions.indexOf(newExtensions.get(j));
          if (index != -1)
          {
            ExtensionData oldExtension = oldExtensions.get(index);
            oldExtensions.add(index + 1, newExtension);
            newExtension.content = oldExtension.lineSeparator + newExtension.content;
            newExtension.start = newExtension.end = oldExtension.end;
            continue LOOP;
          }
        }
        // Failing that, look forward in the new extensions for a matching one in the old extensions before which to insert the new one.
        //
        for (int j = i + 1; j < size; ++j)
        {
          int index = oldExtensions.indexOf(newExtensions.get(j));
          if (index != -1)
          {
            ExtensionData oldExtension = oldExtensions.get(index);
            oldExtensions.add(index, newExtension);
            newExtension.content = newExtension.content + newExtension.lineSeparator;
            newExtension.start = newExtension.end = oldExtension.start;
            continue LOOP;
          }
        }

        // Failing both those, insert it before the closing tag.
        //
        oldExtensions.add(newExtension);
        newExtension.content = (isEmpty ? newExtension.lineSeparator : "") + newExtension.content + newExtension.lineSeparator;
        newExtension.lineSeparator = "";
        newExtension.start = newExtension.end = end;
      }
    }

    // Build up the result...
    //
    StringBuilder result = new StringBuilder();
    int index = 0;
    for (int i = 0, size = oldExtensions.size(); i < size; ++i)
    {
      ExtensionData oldExtension = oldExtensions.get(i);
      result.append(oldContents.substring(index, oldExtension.start));
      result.append(oldExtension.content);
      index = oldExtension.end;
    }
    result.append(oldContents.substring(index));

    return result.toString();
  }

  /**
   * Information about attributes in a manifest.
   * @since 2.9
   */
  protected static final class AttributeData
  {
    public String name;
    public String value;
    public String lineDelimiter;
    public List<Element> elements;
    public int start;
    public int end;

    @Override
    public String toString()
    {
      if (elements != null)
      {
        StringBuilder result = new StringBuilder(name);
        result.append(": ");
        boolean previous = false;
        for (Element element : elements)
        {
          if (previous)
          {
            result.append(',');
            result.append(lineDelimiter);
            result.append(' ');
          }
          else
          {
            previous = true;
          }
          result.append(element);
        }
        return result.toString();
      }
      else
      {
        return name + ": " + value;
      }
    }

    @Override
    public boolean equals(Object object)
    {
      return object instanceof AttributeData && name.equals(((AttributeData)object).name);
    }

    @Override
    public int hashCode()
    {
      return name.hashCode();
    }

    protected final static class Element
    {
      public Set<String> valueComponents = new LinkedHashSet<String>();
      public List<Directive> directives = new ArrayList<Directive>();
      public List<Attribute> attributes = new ArrayList<Attribute>();

      @Override
      public boolean equals(Object object)
      {
        return object instanceof Element && valueComponents.equals(((Element)object).valueComponents);
      }

      @Override
      public int hashCode()
      {
        return valueComponents.hashCode();
      }

      @Override
      public String toString()
      {
        StringBuilder result = new StringBuilder();
        boolean previous = false;
        for (String valueComponent : valueComponents)
        {
          if (previous)
          {
            result.append(';');
          }
          else
          {
            previous = true;
          }
          result.append(valueComponent);
        }
        for (Directive directive : directives)
        {
          result.append(';');
          result.append(directive);
        }
        for (Attribute attribute : attributes)
        {
          result.append(';');
          result.append(attribute);
        }
        return result.toString();
      }

      protected static final class Directive
      {
        public String key;
        public String value;

        @Override
        public String toString()
        {
          return key + ":=" + quote(value);
        }

        @Override
        public boolean equals(Object object)
        {
          return object instanceof Directive && key.equals(((Directive)object).key);
        }

        @Override
        public int hashCode()
        {
          return key.hashCode();
        }
      }

      protected final static class Attribute
      {
        public String key;
        public String value;

        @Override
        public String toString()
        {
          return key + "=" + quote(value);
        }

        @Override
        public boolean equals(Object object)
        {
          return object instanceof Attribute && key.equals(((Attribute)object).key);
        }

        @Override
        public int hashCode()
        {
          return key.hashCode();
        }
      }
    }

    private static String quote(String value)
    {
      for (int i = 0, length = value.length(); i < length; i++)
      {
        char c = value.charAt(i);
        if (!Character.isLetter(c) && c != '_' && c != '-' && c != '.')
        {
          return '"' + value + '"';
        }
      }

      return value;
    }
  }

  /**
   * A pattern for matching a manifest's version attribute.
   * @since 2.9
   */
  protected static final Pattern VERSION_PATTERN = Pattern.compile("Manifest-Version: *([0-9]+(?:\\.[0-9]+)*) *(\r\n|\n\r|\n|\r)?");

  /**
   * A pattern for matching manifest attribute headers.
   * @since 2.9
   */
  protected static final Pattern HEADER_PATTERN = Pattern.compile("([A-Za-z0-9][-_A-Za-z0-9]*): *([^\n\r]*)(\r\n|\n\r|\n|\r)?");

  /**
   * A pattern for matching manifest attribute continuations.
   * @since 2.9
   */
  protected static final Pattern CONTINUATION_PATTERN = Pattern.compile(" ([^\n\r]*)(\r\n|\n\r|\n|\r)?");

  /**
   * Collect information about attributes in a manifest's contents.
   * If the contents are well formed, there should always be at least one entry for the manifest version.
   * @since 2.9
   */
  protected List<AttributeData> getAttributeData(String contents)
  {
    List<AttributeData> result = new ArrayList<AttributeData>();

    // Look for the initial manifest version attribute.
    //
    Matcher versionMatcher = VERSION_PATTERN.matcher(contents);
    if (versionMatcher.lookingAt())
    {
      // Construct the data for it.
      //
      AttributeData versionAttribute = new AttributeData();
      versionAttribute.name = "Manifest-Version";
      versionAttribute.value = versionMatcher.group(1);
      versionAttribute.lineDelimiter = versionMatcher.group(2);
      versionAttribute.end = versionMatcher.end();
      result.add(versionAttribute);

      // Look each header as well as continuations for that header starting after the version attribute...
      //
      Matcher headerMatcher = HEADER_PATTERN.matcher(contents);
      Matcher continuationMatcher = CONTINUATION_PATTERN.matcher(contents);
      for (int start = versionMatcher.end();;)
      {
        // Find a match, checking that it matches at the expected position...
        //
        if (headerMatcher.find(start) && headerMatcher.start() == start)
        {
          // Constructure data for it.
          //
          AttributeData attribute = new AttributeData();
          attribute.name = headerMatcher.group(1);
          attribute.lineDelimiter = headerMatcher.group(3);
          attribute.start = headerMatcher.start();
          attribute.end = headerMatcher.end();
          result.add(attribute);

          // Build up the value from any continuations that start after the header...
          //
          StringBuilder value = new StringBuilder(headerMatcher.group(2));
          start = headerMatcher.end();
          for (;;)
          {
            // Look for the continuation, checking that it matches at the expected positions.
            //
            if (continuationMatcher.find(start) && continuationMatcher.start() == start)
            {
              // Build up the value, ignoring the line delimiter and the leading space.
              //
              value.append(continuationMatcher.group(1));
              attribute.end = continuationMatcher.end();
              start = continuationMatcher.end();
            }
            else
            {
              break;
            }
          }

          // Set the attributes value from this computation.
          //
          attribute.value = value.toString();

          // If we're running in Eclipse, compute the structured OSGi manifest element information from the value.
          //
          if (EMFPlugin.IS_ECLIPSE_RUNNING)
          {
            attribute.elements = EclipseHelper.getElements(attribute.name, attribute.value);
          }
        }
        else
        {
          // Once we don't find a header, stop processing the rest of the manifest.
          //
          break;
        }
      }
    }
    return result;
  }

  /**
   * Finds the first attribute with the given name.
   */
  private static AttributeData getAttribute(List<AttributeData> attributes, String name)
  {
    for (int i = 0, size = attributes.size(); i < size; ++i)
    {
      AttributeData attribute = attributes.get(i);
      if (name.equals(attribute.name))
      {
        return attribute;
      }
    }
    return null;
  }

  /**
   * Merge the contents of to manifests.
   * @since 2.9
   */
  protected String mergeManifest(String oldContents, String newContents)
  {
    List<AttributeData> oldAttributes = getAttributeData(oldContents);
    List<AttributeData> newAttributes = getAttributeData(newContents);

    // We should merge the translated attributes if the bundle localization changes.
    //
    AttributeData oldBundleLocalization = getAttribute(oldAttributes, "Bundle-Localization");
    AttributeData newBundleLocalization = getAttribute(newAttributes, "Bundle-Localization");
    boolean mergeTranslatedAttributes = oldBundleLocalization == null || (newBundleLocalization != null && !newBundleLocalization.value.equals(oldBundleLocalization.value));

    // Pull in the appropriate new attribute content.
    //
    for (int i = 0, size = oldAttributes.size(); i < size; ++i)
    {
      // Determine the matching new attribute.
      //
      AttributeData oldAttribute = oldAttributes.get(i);
      int index = newAttributes.indexOf(oldAttribute);
      if (index != -1)
      {
        AttributeData newAttribute = newAttributes.get(index);

        // Mark the attribute so it's not pushed in later.
        //
        newAttribute.value = null;

        // Pull in the contents for these specific attributes if the are merging translated attributes...
        //
        if ("Bundle-Name".equals(oldAttribute.name) || "Bundle-Vendor".equals(oldAttribute.name))
        {
          // If we should merge translated attributes...
          //
          if (mergeTranslatedAttributes)
          {
            if (oldAttribute.elements == null)
            {
              oldAttribute.value = newAttribute.value;
            }
            else
            {
              oldAttribute.elements = newAttribute.elements;
            }
          }
        }

        // The bundle localization attribute must be what we generate because we must be able to find the properties in that generated properties file.
        //
        else if ("Bundle-Localization".equals(oldAttribute.name) || "Bundle-SymbolicName".equals(oldAttribute.name))
        {
          if (oldAttribute.elements == null)
          {
            oldAttribute.value = newAttribute.value;
          }
          else
          {
            oldAttribute.elements = newAttribute.elements;
          }
        }

        // These attributes have structured content that we should merge...
        //
        else if ("Export-Package".equals(oldAttribute.name) ||
                   "Require-Bundle".equals(oldAttribute.name) ||
                   "Require-Package".equals(oldAttribute.name))
        {
          // If the new one has structured content...
          //
          if (newAttribute.elements != null)
          {
            // If the old one has no structured content, just pull in the full value.
            //
            if (oldAttribute.elements == null)
            {
              oldAttribute.value = newAttribute.value;
            }
            else
            {
              // Merge the elements...
              //
              LOOP:
              for (int j = 0, elementSize = newAttribute.elements.size(); j < elementSize; ++j)
              {
                AttributeData.Element element = newAttribute.elements.get(j);
                int elementIndex = oldAttribute.elements.indexOf(element);
                if (elementIndex == -1)
                {
                  // Look backward for an appropriate element after which to insert the new one.
                  //
                  for (int k = j - 1; k >= 0; --k)
                  {
                    int targetIndex = oldAttribute.elements.indexOf(newAttribute.elements.get(k));
                    if (targetIndex != -1)
                    {
                      oldAttribute.elements.add(targetIndex + 1, element);
                      continue LOOP;
                    }
                  }

                  // Failing that, look forward for an appropriate element before which to insert the new one.
                  //
                  for (int k = j + 1; k < elementSize; ++k)
                  {
                    int targetIndex = oldAttribute.elements.indexOf(newAttribute.elements.get(k));
                    if (targetIndex != -1)
                    {
                     oldAttribute.elements.add(targetIndex, element);
                     continue LOOP;
                    }
                  }

                  // Failing both of those, add the new element at the end.
                  //
                  oldAttribute.elements.add(element);
                }
              }
            }
          }
          else
          {
            // Pull in just the new value if there is no structured content.
            //
            oldAttribute.value = newAttribute.value;
          }
        }
      }
    }

    // Push in the new attribute content, except for the ones blocked during pull analysis.
    //
    LOOP:
    for (int i = 0, size = newAttributes.size(); i < size; ++i)
    {
      AttributeData newAttribute = newAttributes.get(i);

      // If the new attribute isn't blocked...
      //
      if (newAttribute.value != null)
      {
        // To find an insertion point, look backward in the new attributes for a matching one in the old attributes after which to insert the new one.
        //
        for (int j = i - 1; j >= 0; --j)
        {
          int index = oldAttributes.indexOf(newAttributes.get(j));
          if (index != -1)
          {
            AttributeData oldAttribute = oldAttributes.get(index);
            oldAttributes.add(index + 1, newAttribute);
            newAttribute.start = newAttribute.end = oldAttribute.end;
            continue LOOP;
          }
        }

        // Failing that, look forward in the new attributes for a matching one in the old attributes before which to insert the new one.
        //
        for (int j = i + 1; j < size; ++j)
        {
          int index = oldAttributes.indexOf(newAttributes.get(j));
          if (index != -1)
          {
            AttributeData oldAttribute = oldAttributes.get(index);
            oldAttributes.add(index, newAttribute);
            newAttribute.start = newAttribute.end = oldAttribute.start;
            continue LOOP;
          }
        }

        // Failing both those, insert it after the last old attribute.
        //
        oldAttributes.add(newAttribute);
        newAttribute.start = newAttribute.end = oldAttributes.get(oldAttributes.size() - 1).end;
      }
    }

    // Build up the result...
    //
    StringBuilder result = new StringBuilder();
    int index = 0;
    for (int i = 0, size = oldAttributes.size(); i < size; ++i)
    {
      AttributeData oldAttribute = oldAttributes.get(i);
      result.append(oldContents.substring(index, oldAttribute.start));
      result.append(oldAttribute);
      result.append(oldAttribute.lineDelimiter);
      index = oldAttribute.end;
    }
    result.append(oldContents.substring(index));

    return result.toString();
  }

  /**
   * Generates a properties file using JET, with {@link org.eclipse.emf.codegen.merge.properties.PropertyMerger merging}
   * capability.
   * 
   * <p>The encoding used for the generated file is "ISO-8859-1".
   * {@link org.eclipse.emf.codegen.util.CodeGenUtil#unicodeEscapeEncode(String) Unicode escape encoding} is
   * performed to represent non-Latin characters.
   *
   * @param targetPathName the path name of the target file. This should be a workspace path; when running stand-alone,
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
   * @param targetPathName the path name of the target file. This should be a workspace path; when running stand-alone,
   *         it will be converted to a platform resource URI that should be mapped to a physical file URI by the
   *         {@link #getURIConverter() URIConverter}.
   * @param gifEmitter the <code>GIFEmitter</code> to use for generating the icon.
   * @param parentKey the key used to determine the first colour set.
   * @param childKey the key used to determine the second colour set. If null, this key is ignored.
   * @param overwrite whether an existing file should be overwritten.
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
   * stand-alone, the platform default is used.
   * 
   * @param targetPath the workspace path of the directory in or under which the file will be created, depending on the
   *         specified package name. When running stand-alone, this path will be converted to a platform resource URI
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

      JControlModel jControlModel = getGenerator().getJControlModel();      
      JMerger jMerger = null;
      if (jControlModel.canMerge())
      {
        jMerger = new JMerger(jControlModel);
      }

      createImportManager(packageName, className);
      String targetFileContents = null;
      String targetFileEncoding = getEncoding(targetFile);
      if (exists(targetFile) && jMerger != null)
      {
        // Prime the import manager with the existing imports of the target.
        //
        jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(createInputStream(targetFile), targetFileEncoding));
        JCompilationUnit targetCompilationUnit = jMerger.getTargetCompilationUnit();
        ImportManager importManager = getImportManager();
        for (JNode node : targetCompilationUnit.getChildren())
        {
          if (node instanceof JImport)
          {
            JImport jImport = (JImport)node;
            String qualifiedName = jImport.getQualifiedName();
            importManager.addImport(qualifiedName);
          }
        }
        targetFileContents = jMerger.getTargetCompilationUnitContents();
      }

      setLineDelimiter(getLineDelimiter(targetFile, targetFileEncoding));
      String emitterResult = jetEmitter.generate(createMonitor(monitor, 1), arguments, getLineDelimiter());
      boolean changed = true;
      String newContents = emitterResult;

      Options options = getGenerator().getOptions();

      if (jMerger != null)
      {
        jMerger.setFixInterfaceBrace(jControlModel.getFacadeHelper().fixInterfaceBrace());
        
        try
        {
          jMerger.setSourceCompilationUnit(jMerger.createCompilationUnitForContents(emitterResult));
        }
        catch (RuntimeException runtimeException)
        {
          if (targetFileContents != null)
          {
            throw runtimeException;
          }
          else
          {
            jMerger = null;
          }
        }

        if (jMerger != null)
        {
          // Create a code formatter for this compilation unit, if needed.
          //
          Object codeFormatter = options.codeFormatting ?
            createCodeFormatter(options.codeFormatterOptions, targetFile) : null;
  
          if (targetFileContents != null)
          {
            monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_ExaminingOld_message", new Object[] { targetFile }));
  
            monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_PreparingNew_message", new Object[] { targetFile }));
            jMerger.merge();
  
            newContents = formatCode(jMerger.getTargetCompilationUnitContents(), codeFormatter, options.commentFormatting);
            if (options.importOrganizing)
            {
              newContents = organizeImports(targetFile.toString(), newContents);
            }
            changed = !targetFileContents.equals(newContents);
  
            // If the target is read-only, we can ask the platform to release it, and it may be updated in the process.
            //
            if (changed && isReadOnly(targetFile) && validateEdit(targetFile, createMonitor(monitor, 1)))
            {
              jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(createInputStream(targetFile), targetFileEncoding));
              jMerger.remerge();
              newContents = formatCode(jMerger.getTargetCompilationUnitContents(), codeFormatter, options.commentFormatting);
              if (options.importOrganizing)
              {
                newContents = organizeImports(targetFile.toString(), newContents);
              }
            }
          }
          else
          {
            changed = true;
            monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_PreparingNew_message", new Object[] { targetFile }));

            jMerger.merge();
            newContents = formatCode(jMerger.getTargetCompilationUnitContents(), codeFormatter, options.commentFormatting);
          }
  
          if (jControlModel.getFacadeHelper() != null)
          {
            jControlModel.getFacadeHelper().reset();
          }
        }
      }

      if (jMerger == null)
      {
        newContents = 
          CodeGenUtil.convertFormat(jControlModel.getLeadingTabReplacement(), jControlModel.convertToStandardBraceStyle(), emitterResult);
        if (targetFileContents != null)
        {
          monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_ExaminingOld_message", new Object[] { targetFile }));
          changed = !targetFileContents.equals(newContents);
        }
        else
        {
          changed = true;
        }
      }
      monitor.worked(1);

      if (changed)
      {
        String encoding = targetFileEncoding;
        byte[] bytes = encoding == null ? newContents.getBytes() : newContents.getBytes(encoding);

        // Apply a redirection pattern, if specified.
        //
        String redirection = options.redirectionPattern;
        boolean redirect = redirection != null && redirection.indexOf("{0}") != -1;

        if (redirect)
        {
          String baseName = MessageFormat.format(redirection, new Object[] { className + ".java" });
          targetFile = targetDirectory.appendSegment(baseName);
          monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_UsingAlternate_message", new Object[] { targetFile }));
        } 

        if (isReadOnly(targetFile))
        {
          if (options.forceOverwrite)
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
      throw e instanceof RuntimeException ? (RuntimeException)e : new WrappedException(e);
    }
    finally
    {
      clearImportManager();
      setLineDelimiter(null);
      monitor.done();
    }
  }

  /**
   * Converts the given workspace path to a <code>URI</code>. No encoding is performed, so the URI may contain invalid
   * characters. Such a URI is only used to easily access and manipulate parts of the workspace path. It can then be
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
   * Creates and returns a sub-monitor for the given progress monitor. When running stand-alone, the same monitor is
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
   * Ensures that a project, corresponding to the first segment in the specified workspace path, exists. If the project
   * does not exist, a default project will be created. If it does exist and <code>force</code> is true, it will be
   * reconfigured to match the default configuration. The remainder of the path suggests the folder under which source
   * will be generated.
   *
   * <p>When running stand-alone, this method does nothing, since simply opening a stream via a <code>URIConverter</code>
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
   * Ensures that a container corresponding to the specified relative URI exists. The URI represents a workspace
   * path for which the project must already exist, since this method doesn't have the necessary information to
   * set up a project. This method will create nested folders within the project, if possible.
   *
   * <p>When running stand-alone, this method does nothing, since simply opening a stream via a <code>URIConverter</code>
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
      uriConverter = new ExtensibleURIConverterImpl();
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
      String lineDelimiter = ContentHandlerImpl.getLineDelimiter(inputStream, encoding);
      if (lineDelimiter != null)
      {
        return lineDelimiter;
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
      return PlatformResourceURIHandlerImpl.WorkbenchHelper.getLineDelimiter(workspacePath.toString(), null);
    }
    return System.getProperty(Platform.PREF_LINE_SEPARATOR);
  }

  /**
   * Determines whether a given workspace path URI represents a file that already exists.
   */
  protected boolean exists(URI workspacePath)
  {
    return getURIConverter().exists(toPlatformResourceURI(workspacePath), null);
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
   * Sets the file represented by a workspace path URI to be writable. When running stand-alone, this actually
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
   * providing it the opportunity to prepare the files if required. When running stand-alone, does nothing.
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
    return getURIConverter().createInputStream(toPlatformResourceURI(workspacePath), null);
  }

  /**
   * Creates an <code>OutputStream</code> for the file identified by the given workspace path URI.
   */
  protected OutputStream createOutputStream(URI workspacePath) throws Exception
  {
    return getURIConverter().createOutputStream(toPlatformResourceURI(workspacePath), null);
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
   * the given workspace path URI. When running stand-alone, returns null.
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
   * When running under Eclipse, returns a code formatter; when stand-alone, returns null. If <code>options</code> is
   * non-null, the code formatting options it specifies are used to create the formatter. Otherwise, the project is
   * obtained from the given workspace path URI, and its default formatting options are used.
   *
   * @return the created code formatter. If non-null, this will be an instance of
   *          {@link org.eclipse.jdt.core.formatter.CodeFormatter CodeFormatter}; however, it is not statically typed
   *          as such to avoid failure when running stand-alone.
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
   * Clients overriding this method should change to overrides {@link #formatCode(String, Object, boolean)} instead.
   *
   * @return the formatted version of the contents. If the code formatter is null or when running stand-alone, the
   *          contents are returned unchanged. 
   * @deprecated
   */
  @Deprecated
  protected String formatCode(String contents, Object codeFormatter)
  {
    return EMFPlugin.IS_ECLIPSE_RUNNING ? EclipseHelper.formatCode(contents, codeFormatter, getLineDelimiter(), false) : contents;
  }

  /**
   * If non-null, use the specified code formatter to format the given compilation unit contents.
   *
   * @return the formatted version of the contents. If the code formatter is null or when running stand-alone, the
   *          contents are returned unchanged. 
   * @since 2.8
   */
  protected String formatCode(String contents, Object codeFormatter, boolean formatComments)
  {
    return
      formatComments && EMFPlugin.IS_ECLIPSE_RUNNING ?
        EclipseHelper.formatCode(contents, codeFormatter, getLineDelimiter(), formatComments) :
        formatCode(contents, codeFormatter);
  }

  /**
   * When running under Eclipse, returns the contents with unused imports removed; when stand-alone, returns the original contents.
   * @return the contents with unused imports removed, or when running stand-alone, the unchanged contents.
   * Since 2.9
   */
  protected String organizeImports(String path, String contents)
  {
    return EMFPlugin.IS_ECLIPSE_RUNNING ? EclipseHelper.organizeImports(path, contents) : contents;
  }

  /*
   * All Eclipse-dependent operations are delegated to this class. This pattern avoids any runtime failure due to
   * missing dependencies in the stand-alone case.
   */
  private static class EclipseHelper
  {
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
        CodeGenEcorePlugin.INSTANCE.log(exception);
      }
      finally
      {
        monitor.done();
      }
      return container != null && container.getFullPath().equals(path);
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
      return file.getWorkspace().validateEdit(new IFile [] { file }, IWorkspace.VALIDATE_PROMPT).isOK();
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

    public static String formatCode(String contents, Object codeFormatter, String lineDelimiter, boolean formatComments)
    {
      if (codeFormatter instanceof CodeFormatter)
      {
        IDocument doc = new Document(contents);
        TextEdit edit = ((CodeFormatter)codeFormatter).format(CodeFormatter.K_COMPILATION_UNIT | (formatComments ? CodeFormatter.F_INCLUDE_COMMENTS : 0), doc.get(), 0, doc.get().length(), 0, lineDelimiter);
  
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

    public static String organizeImports(String workspacePath, String contents)
    {
      // Keep the result in an array so we can update if we successfully remove imports.
      //
      final String [] result = new String[] { contents };

      // We must be able to determine this Java project for this target path to proceed...
      //
      IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(workspacePath));
      IProject project = file.getProject();
      if (project != null)
      {
        IJavaProject javaProject = JavaCore.create(project);
        if (javaProject != null)
        {
          // All is good so we can create a compilation for this path.
          //
          ICompilationUnit compilationUnit = JavaCore.createCompilationUnitFrom(file);

          // Create a specialized working copy owner that creates a specialized buffer to access the contents passed to this method.
          //
          WorkingCopyOwner workingCopyOwner =
            new WorkingCopyOwner()
            {
              @Override
              public IBuffer createBuffer(final ICompilationUnit workingCopy)
              {
                return
                  new IBuffer()
                  {
                    public IOpenable getOwner()
                    {
                      return workingCopy;
                    }

                    public String getText(int offset, int length) throws IndexOutOfBoundsException
                    {
                      return result[0].substring(offset, offset + length);
                    }

                    public int getLength()
                    {
                      return result[0].length();
                    }

                    public String getContents()
                    {
                      return result[0];
                    }

                    public char[] getCharacters()
                    {
                      return result[0].toCharArray();
                    }

                    public char getChar(int position)
                    {
                      return result[0].charAt(position);
                    }

                    public boolean isReadOnly()
                    {
                      return true;
                    }

                    public boolean isClosed()
                    {
                      return false;
                    }

                    public boolean hasUnsavedChanges()
                    {
                      return false;
                    }

                    public IResource getUnderlyingResource()
                    {
                      return null;
                    }

                    public void close()
                    {
                      // Ignore
                    }

                    public void save(IProgressMonitor progress, boolean force) throws JavaModelException
                    {
                      throw new UnsupportedOperationException();
                    }

                    public void setContents(String contents)
                    {
                      throw new UnsupportedOperationException();
                    }

                    public void setContents(char[] contents)
                    {
                      throw new UnsupportedOperationException();
                    }

                    public void replace(int position, int length, String text)
                    {
                      throw new UnsupportedOperationException();
                    }

                    public void replace(int position, int length, char[] text)
                    {
                      throw new UnsupportedOperationException();
                    }

                    public void append(String text)
                    {
                      throw new UnsupportedOperationException();
                    }

                    public void append(char[] text)
                    {
                      throw new UnsupportedOperationException();
                    }

                    public void addBufferChangedListener(IBufferChangedListener listener)
                    {
                      // Ignore
                    }

                    public void removeBufferChangedListener(IBufferChangedListener listener)
                    {
                      // Ignore
                    }
                  };
              }
            };
          try
          {
            // Create a working copy that yields the contents passed to this method as the compilation unit's current content.
            //
            ICompilationUnit workingCopy = compilationUnit.getWorkingCopy(workingCopyOwner, null);

            // Create a parser that will produce errors for unused imports.
            //
            ASTParser astParser = CodeGenUtil.EclipseUtil.newASTParser();
            astParser.setCompilerOptions(Collections.singletonMap(JavaCore.COMPILER_PB_UNUSED_IMPORT, JavaCore.ERROR));
            astParser.setResolveBindings(true);
            astParser.setProject(javaProject);

            // Create a visitor that will handle the compiled content.
            //
            ASTRequestor unusedImportRemover =
              new ASTRequestor()
              {
                @Override
                public void acceptAST(ICompilationUnit sourceUnit, CompilationUnit compiledUnit)
                {
                  // Visit all the compiler problems looking for unused imports.
                  //
                  final Set<String> unusedImports = new HashSet<String>();
                  IProblem[] problems = compiledUnit.getProblems();
                  boolean onlyUnusedImportErrors = true;
                  for (IProblem problem : problems)
                  {
                    int id = problem.getID();
                    if (id == IProblem.UnusedImport)
                    {
                      unusedImports.add(problem.getArguments()[0]);
                    }
                    else
                    {
                      // If there are other errors, we can't rely on there being unused import errors because they're optional and aren't produced when non-optional errors are present.
                      //
                      onlyUnusedImportErrors = false;
                      break;
                    }
                  }

                  // If there are other errors, we need to do our own detailed analysis to find unused imports...
                  //
                  if (!onlyUnusedImportErrors)
                  {
                    // Build up the set up all imported names, ignoring static and on-demand imports.
                    //
                    @SuppressWarnings({ "unchecked", "cast" })
                    List<? extends ImportDeclaration> imports = (List<? extends ImportDeclaration>)compiledUnit.imports();
                    for (ImportDeclaration importDeclaration : imports)
                    {
                      if (!importDeclaration.isStatic() && !importDeclaration.isOnDemand())
                      {
                        unusedImports.add(importDeclaration.getName().getFullyQualifiedName());
                      }
                    }

                    // Walk the AST to determine references to the imported names.
                    //
                    compiledUnit.accept
                      (new ASTVisitor(true)
                       {
                         @Override
                         public boolean visit(ImportDeclaration node)
                         {
                           // Ignore the import declarations themselves.
                           //
                           return false;
                         }

                         @Override
                         public boolean visit(QualifiedName node)
                         {
                           // Determine the root qualifier, visit that simple name, and don't visit the children.
                           //
                           Name name = node.getQualifier();
                           while (name.isQualifiedName())
                           {
                             name= ((QualifiedName) name).getQualifier();
                           }
                           visit((SimpleName)name);
                           return false;
                         }

                         @Override
                         public boolean visit(SimpleName node)
                         {
                           // If the binding is a type binding, process it, and don't visit the children.
                           //
                           IBinding binding = node.resolveBinding();
                           if (binding instanceof ITypeBinding)
                           {
                             // If there is a type binding, remove the qualified name of its erasure from the unused imports.
                             //
                             unusedImports.remove(((ITypeBinding)binding).getErasure().getQualifiedName());
                           }
                           else if (binding == null)
                           {
                             // If we can't resolve it at all, better assume it should resolve to an import...
                             //
                             String suffix = "." + node.getIdentifier();
                             for (String unusedImport : unusedImports)
                             {
                               if (unusedImport.endsWith(suffix))
                               {
                                 unusedImports.remove(unusedImport);
                                 break;
                               }
                             }
                           }
                           return false;
                         }
                       });

                    // If there are imports that aren't resolvable, we have to be careful that we don't just remove them as if they are unused because they might be needed somewhere.
                    // So we look for errors about unresolved types and unresolved variables and remove the corresponding unused import, if there is one.
                    //
                    for (IProblem problem : problems)
                    {
                      int id = problem.getID();
                      if (id == IProblem.UndefinedType || id == IProblem.UnresolvedVariable)
                      {
                        String suffix = "." + problem.getArguments()[0];
                        for (String unusedImport : unusedImports)
                        {
                          if (unusedImport.endsWith(suffix))
                          {
                            unusedImports.remove(unusedImport);
                            break;
                          }
                        }
                      }
                    }
                  }

                  // If there are imports that should be removed...
                  //
                  if (!unusedImports.isEmpty())
                  {
                    // Create an import rewriter if possible.
                    //
                    ImportRewrite importRewrite;
                    try
                    {
                      importRewrite = ImportRewrite.create(sourceUnit, true);
                    }
                    catch (JavaModelException e)
                    {
                      // In this case, we can't fix them, so just return.
                      //
                      return;
                    }

                    for (String unusedImport : unusedImports)
                    {
                      // Try to remove the problematic import, and failing that, try to remove the wildcard import.
                      //
                      boolean removed = importRewrite.removeImport(unusedImport);
                      if (!removed)
                      {
                        importRewrite.removeImport(unusedImport + ".*");
                      }
                    }

                    try
                    {
                      // Apply the text edits to the original contents and update the result.
                      //
                      TextEdit textEdits = importRewrite.rewriteImports(null);
                      Document document = new Document(result[0]);
                      textEdits.apply(document);
                      result[0] = document.get();
                    }
                    catch (BadLocationException exception)
                    {
                      // We failed unexpectedly, so just return.
                      //
                      return;
                    }
                    catch (CoreException exception)
                    {
                      // We failed unexpectedly, so just return.
                      //
                      return;
                    }
                  }
                }
              };

            // Compile the working copy source, applying the unused import remover.
            //
            astParser.createASTs(new ICompilationUnit[] { workingCopy }, new String[0], unusedImportRemover, null);
          }
          catch (JavaModelException exception)
          {
            // Ignore all problems and just return the original contents.
          }
        }
      }

      return result[0];
    }

  public static final Set<String> OSGI_ATTRIBUTES = 
    new HashSet<String>
     (Arrays.asList
        (new String[] 
         {
           "Bundle-ManifestVersion",
           "Bundle-SymbolicName",
           "Bundle-Version",
           "Bundle-Localization",
           "Bundle-Name",
           "Bundle-Vendor",
           "Bundle-ClassPath",
           "Bundle-Activator",
           "Bundle-ActivationPolicy",
           "Bundle-RequiredExecutionEnvironment",
           "Require-Bundle",
           "Import-Package",
           "Export-Package"
         }));

    public static List<AttributeData.Element> getElements(String name, String value)
    {
      // If we fail, or it's not appropriate to compute structured content, we just return null.
      //
      ArrayList<AttributeData.Element> elements = null;
      if (OSGI_ATTRIBUTES.contains(name))
      {
        try
        {
          // Parse the manifest elements from the value.
          //
          ManifestElement[] header = ManifestElement.parseHeader(name, value);
          if (header != null)
          {
            // Create a list to hold the result.
            //
            elements = new ArrayList<AttributeData.Element>();

            // Look at all the elements...
            //
            for (ManifestElement manifestElement : header)
            {
              // Create a structure to hold the information.
              //
              AttributeData.Element element = new AttributeData.Element();
              elements.add(element);

              // Record the value components.
              //
              String[] valueComponents = manifestElement.getValueComponents();
              for (String valueComponent : valueComponents)
              {
                element.valueComponents.add(valueComponent);
              }

              // Record the directives.
              //
              Enumeration<String> directiveKeys = manifestElement.getDirectiveKeys();
              if (directiveKeys != null)
              {
                while (directiveKeys.hasMoreElements())
                {
                  AttributeData.Element.Directive directive = new AttributeData.Element.Directive();
                  directive.key = directiveKeys.nextElement();
                  directive.value =  manifestElement.getDirective(directive.key);
                  element.directives.add(directive);
                }
              }

              // Record the attributes.
              //
              Enumeration<String> attributeKeys = manifestElement.getKeys();
              if (attributeKeys != null)
              {
                while (attributeKeys.hasMoreElements())
                {
                  AttributeData.Element.Attribute attribute2 = new AttributeData.Element.Attribute();
                  attribute2.key  = attributeKeys.nextElement();
                  attribute2.value  =  manifestElement.getAttribute(attribute2.key);
                  element.attributes.add(attribute2);
                }
              }
            }
          }
        }
        catch (BundleException e)
        {
          // Ignore
        }
      }
      return elements;
    }
  }
}
