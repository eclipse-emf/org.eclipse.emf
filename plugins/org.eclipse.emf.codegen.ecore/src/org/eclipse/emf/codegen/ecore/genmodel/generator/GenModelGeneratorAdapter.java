/**
 * <copyright>
 *
 * Copyright (c) 2006-2010 IBM Corporation and others.
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
 * $Id: GenModelGeneratorAdapter.java,v 1.12 2010/09/07 16:57:56 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.generator;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimePlatform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;

/**
 * A {@link GeneratorAdapter} for instances of {@link GenModel}. This contributes the model level artifacts to EMF's
 * default code generation.
 * 
 * <p>This implementation should not be extended merely to augment the default code generation for models. The
 * recommended approach is to implement a new adapter and register the {@link GeneratorAdapterFactory adapter factory}
 * that creates it, so that it is contributed to code generation. Such registration is usually done through the
 * <code>org.eclipse.emf.codegen.ecore.generatorAdapters</code> extension point.
 * 
 * <p>This implementation may be extended, however, in order to remove from or change the default code generation.
 * 
 * @since 2.2.0
 */
public class GenModelGeneratorAdapter extends GenBaseGeneratorAdapter
{
  protected static final int MODEL_PLUGIN_CLASS_ID = 0;
  protected static final int MODEL_MANIFEST_MF_ID = 1;
  protected static final int MODEL_PLUGIN_XML_ID = 2;
  protected static final int MODEL_PLUGIN_PROPERTIES_ID = 3;
  protected static final int MODEL_BUILD_PROPERTIES_ID = 4;
  protected static final int EDIT_PLUGIN_CLASS_ID = 5;
  protected static final int EDIT_MANIFEST_MF_ID = 6;
  protected static final int EDIT_PLUGIN_XML_ID= 7;
  protected static final int EDIT_PLUGIN_PROPERTIES_ID = 8;
  protected static final int EDIT_BUILD_PROPERTIES_ID = 9;
  protected static final int EDITOR_PLUGIN_CLASS_ID = 10;
  protected static final int EDITOR_MANIFEST_MF_ID = 11;
  protected static final int EDITOR_PLUGIN_XML_ID = 12;
  protected static final int EDITOR_PLUGIN_PROPERTIES_ID = 13;
  protected static final int EDITOR_BUILD_PROPERTIES_ID = 14;
  protected static final int ADVISOR_ID = 15;
  protected static final int MODEL_TEST_SUITE_ID = 16;
  protected static final int TESTS_MANIFEST_MF_ID = 17;
  protected static final int TESTS_PLUGIN_XML_ID = 18;
  protected static final int TESTS_PLUGIN_PROPERTIES_ID = 19;
  protected static final int TESTS_BUILD_PROPERTIES_ID = 20;
  protected static final int MODEL_MODULE_GWT_XML_ID = 21;
  protected static final int EDIT_MODULE_GWT_XML_ID = 22;
  protected static final int EDIT_PLUGIN_PROPERTIES_INTERFACE_ID = 23;
  protected static final int EDIT_PLUGIN_IMAGES_INTERFACE_ID = 24;
  protected static final int EDITOR_WEB_XML_ID = 25;
  protected static final int EDITOR_APPENGINE_WEB_XML_ID = 26;
  protected static final int EDITOR_MODULE_GWT_XML_ID = 27;
  protected static final int EDITOR_HOME_HMTL_ID = 28;
  protected static final int EDITOR_ENTRY_POINT_CLASS_ID = 29;

  private static final JETEmitterDescriptor[] JET_EMITTER_DESCRIPTORS =
  {
    new JETEmitterDescriptor("model/Plugin.javajet", "org.eclipse.emf.codegen.ecore.templates.model.Plugin"),
    new JETEmitterDescriptor("model/manifest.mfjet", "org.eclipse.emf.codegen.ecore.templates.model.ManifestMF"),
    new JETEmitterDescriptor("model/plugin.xmljet", "org.eclipse.emf.codegen.ecore.templates.model.PluginXML"),
    new JETEmitterDescriptor("model/plugin.propertiesjet", "org.eclipse.emf.codegen.ecore.templates.model.PluginProperties"),
    new JETEmitterDescriptor("model/build.propertiesjet", "org.eclipse.emf.codegen.ecore.templates.model.BuildProperties"),
    new JETEmitterDescriptor("edit/Plugin.javajet", "org.eclipse.emf.codegen.ecore.templates.edit.Plugin"),
    new JETEmitterDescriptor("edit/manifest.mfjet", "org.eclipse.emf.codegen.ecore.templates.edit.ManifestMF"),
    new JETEmitterDescriptor("edit/plugin.xmljet", "org.eclipse.emf.codegen.ecore.templates.edit.PluginXML"),
    new JETEmitterDescriptor("edit/plugin.propertiesjet", "org.eclipse.emf.codegen.ecore.templates.edit.PluginProperties"),
    new JETEmitterDescriptor("edit/build.propertiesjet", "org.eclipse.emf.codegen.ecore.templates.edit.BuildProperties"),
    new JETEmitterDescriptor("editor/Plugin.javajet", "org.eclipse.emf.codegen.ecore.templates.editor.Plugin"),
    new JETEmitterDescriptor("editor/manifest.mfjet", "org.eclipse.emf.codegen.ecore.templates.editor.ManifestMF"),
    new JETEmitterDescriptor("editor/plugin.xmljet", "org.eclipse.emf.codegen.ecore.templates.editor.PluginXML"),
    new JETEmitterDescriptor("editor/plugin.propertiesjet", "org.eclipse.emf.codegen.ecore.templates.editor.PluginProperties"),
    new JETEmitterDescriptor("editor/build.propertiesjet", "org.eclipse.emf.codegen.ecore.templates.editor.BuildProperties"),
    new JETEmitterDescriptor("editor/Advisor.javajet", "org.eclipse.emf.codegen.ecore.templates.editor.Advisor"),
    new JETEmitterDescriptor("model.tests/ModelTestSuite.javajet", "org.eclipse.emf.codegen.ecore.templates.model.tests.ModelTestSuite"),
    new JETEmitterDescriptor("model.tests/manifest.mfjet", "org.eclipse.emf.codegen.ecore.templates.model.tests.ManifestMF"),
    new JETEmitterDescriptor("model.tests/plugin.xmljet", "org.eclipse.emf.codegen.ecore.templates.model.tests.PluginXML"),
    new JETEmitterDescriptor("model.tests/plugin.propertiesjet", "org.eclipse.emf.codegen.ecore.templates.model.tests.PluginProperties"),
    new JETEmitterDescriptor("model.tests/build.propertiesjet", "org.eclipse.emf.codegen.ecore.templates.model.tests.BuildProperties"),
    new JETEmitterDescriptor("model/module.gwt.xmljet", "org.eclipse.emf.codegen.ecore.templates.model.ModuleGWTXML"),
    new JETEmitterDescriptor("edit/module.gwt.xmljet", "org.eclipse.emf.codegen.ecore.templates.edit.ModuleGWTXML"),
    new JETEmitterDescriptor("edit/Properties.javajet", "org.eclipse.emf.codegen.ecore.templates.edit.Properties"),
    new JETEmitterDescriptor("edit/Images.javajet", "org.eclipse.emf.codegen.ecore.templates.edit.Images"),
    new JETEmitterDescriptor("editor/web.xmljet", "org.eclipse.emf.codegen.ecore.templates.editor.WebXML"),
    new JETEmitterDescriptor("editor/appengine-web.xmljet", "org.eclipse.emf.codegen.ecore.templates.editor.AppEngineWebXML"),
    new JETEmitterDescriptor("editor/module.gwt.xmljet", "org.eclipse.emf.codegen.ecore.templates.editor.ModuleGWTXML"),
    new JETEmitterDescriptor("editor/home.htmljet", "org.eclipse.emf.codegen.ecore.templates.editor.HomeHTML"),
    new JETEmitterDescriptor("editor/EntryPoint.javajet", "org.eclipse.emf.codegen.ecore.templates.editor.EntryPoint"),
  };

  /**
   * Returns the set of <code>JETEmitterDescriptor</code>s used by the adapter. The contents of the returned array
   * should never be changed. Rather, subclasses may override this method to return a different array altogether.
   */
  protected JETEmitterDescriptor[] getJETEmitterDescriptors()
  {
    return JET_EMITTER_DESCRIPTORS;
  }

  public GenModelGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory)
  {
    super(generatorAdapterFactory);
  }

  /**
   * Returns the {@link GenModel}'s packages.
   */
  @Override
  public Collection<?> getGenerateChildren(Object object, Object projectType)
  {
    return new ArrayList<GenPackage>(((GenModel)object).getGenPackages());
  }

  /**
   * Prepares the {@link GenModel} for generation.
   */
  @Override
  protected Diagnostic doPreGenerate(Object object, Object projectType)
  {
    ((GenModel)object).getStaticGenPackages();
    return Diagnostic.OK_INSTANCE;
  }

  @Override
  protected Diagnostic generateModel(Object object, Monitor monitor)
  {
    monitor.beginTask("", 7);

    GenModel genModel = (GenModel)object;
    message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingPackages_message");
    monitor.subTask(message);

    ensureProjectExists
      (genModel.getModelDirectory(), genModel, MODEL_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

    generateModelPluginClass(genModel, monitor);
    generateModelPluginProperties(genModel, monitor);
    generateModelBuildProperties(genModel, monitor);
    generateModelManifest(genModel, monitor);
    generateModelModule(genModel, monitor);

    return Diagnostic.OK_INSTANCE;
  }

  protected void generateModelPluginClass(GenModel genModel, Monitor monitor)
  {
    if (genModel.hasModelPluginClass())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message",new Object[] { genModel.getQualifiedModelPluginClassName() });
      monitor.subTask(message);
      generateJava
        (genModel.getModelDirectory(),
         genModel.getModelPluginPackageName(),
         genModel.getModelPluginClassName(),
         getJETEmitter(getJETEmitterDescriptors(), MODEL_PLUGIN_CLASS_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateModelManifest(GenModel genModel, Monitor monitor)
  {
    if (genModel.hasPluginSupport() && !genModel.sameModelEditProject() && !genModel.sameModelEditorProject())
    {
      if (genModel.isBundleManifest())
      {
        message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingModelManifestMF_message");
        monitor.subTask(message);

        // Do allow an existing MANIFEST.MF to be overwritten, since it may have been created as part of an empty EMF project.
        // Use the existence of a plugin.xml as a guard against overwriting in a project that has already been generated.
        //
        generateText
          (genModel.getModelProjectDirectory() + "/META-INF/MANIFEST.MF",
           getJETEmitter(getJETEmitterDescriptors(), MODEL_MANIFEST_MF_ID),
           null,
           genModel.isUpdateClasspath() && !exists(toURI(genModel.getModelProjectDirectory()).appendSegment("plugin.xml")),
           MANIFEST_ENCODING,
           createMonitor(monitor, 1));
      }
      else
      {
        monitor.worked(1);
      }

      if (genModel.getRuntimePlatform() != GenRuntimePlatform.GWT)
      {
        message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingModelPluginXML_message");
        monitor.subTask(message);
        generateText
          (genModel.getModelProjectDirectory() + "/plugin.xml",
           getJETEmitter(getJETEmitterDescriptors(), MODEL_PLUGIN_XML_ID),
           null,
           false,
           MANIFEST_ENCODING,
           createMonitor(monitor, 1));
      }
      else
      {
        monitor.worked(1);
      }
    }
    else
    {
      monitor.worked(2);
    }
  }

  protected void generateModelModule(GenModel genModel, Monitor monitor)
  {
    if (genModel.getRuntimePlatform() == GenRuntimePlatform.GWT && !genModel.sameModelEditProject() && !genModel.sameModelEditorProject())
    {
      String qualifiedModelModuleName = genModel.getQualifiedModelModuleName();
      message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingModuleGWTXML_message", new Object[] { qualifiedModelModuleName });
      monitor.subTask(message);
      generateText
        (genModel.getModelDirectory() + "/" + qualifiedModelModuleName.replace(".", "/") + ".gwt.xml",
         getJETEmitter(getJETEmitterDescriptors(), MODEL_MODULE_GWT_XML_ID),
         null,
         false,
         MANIFEST_ENCODING,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateModelPluginProperties(GenModel genModel, Monitor monitor)
  {
    if (genModel.hasPluginSupport())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingModelPluginProperties_message");
      monitor.subTask(message);
      generateProperties
        (genModel.getModelProjectDirectory() + "/plugin.properties",
         getJETEmitter(getJETEmitterDescriptors(), MODEL_PLUGIN_PROPERTIES_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateModelBuildProperties(GenModel genModel, Monitor monitor)
  {
    if (genModel.hasPluginSupport())
    {
      monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingModelBuildProperties_message"));
      monitor.subTask(message);

      // Do allow an existing build.properties to be overwritten, since it may have been created as part of an empty EMF project.
      // Use the existence of a plugin.xml as a guard against overwriting in a project that has already been generated.
      //
      generateText
        (genModel.getModelProjectDirectory() + "/build.properties",
         getJETEmitter(getJETEmitterDescriptors(), MODEL_BUILD_PROPERTIES_ID),
         null,
         genModel.isUpdateClasspath() && !exists(toURI(genModel.getModelProjectDirectory()).appendSegment("plugin.xml")),
         PROPERTIES_ENCODING,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  @Override
  protected Diagnostic generateEdit(Object object, Monitor monitor)
  {
    monitor.beginTask("", 9);

    GenModel genModel = (GenModel)object;
    message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditPackages_message");
    monitor.subTask(message);

    ensureProjectExists
      (genModel.getEditDirectory(), genModel, EDIT_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

    generateEditPluginClass(genModel, monitor);
    generateEditPluginProperties(genModel, monitor);
    if (genModel.getRuntimePlatform() == GenRuntimePlatform.GWT)
    {
      generateEditPluginPropertiesInterface(genModel, monitor);
      generateEditPluginImagesInterface(genModel, monitor);
    }
    else
    {
      monitor.worked(2);
    }
    generateEditBuildProperties(genModel, monitor);
    generateEditManifest(genModel, monitor);
    generateEditModule(genModel, monitor);

    return Diagnostic.OK_INSTANCE;
  }

  protected void generateEditPluginClass(GenModel genModel, Monitor monitor)
  {
    if (!genModel.sameEditEditorProject())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genModel.getQualifiedEditPluginClassName() });
      monitor.subTask(message);
      generateJava
        (genModel.getEditPluginDirectory(),
         genModel.getEditPluginPackageName(),
         genModel.getEditPluginClassName(),
         getJETEmitter(getJETEmitterDescriptors(), EDIT_PLUGIN_CLASS_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateEditPluginPropertiesInterface(GenModel genModel, Monitor monitor)
  {
    if (!genModel.sameEditEditorProject())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genModel.getQualifiedEditPluginClassName() + "Properties" });
      monitor.subTask(message);
      generateJava
        (genModel.getEditPluginDirectory(),
         genModel.getEditPluginPackageName(),
         genModel.getEditPluginClassName() + "Properties",
         getJETEmitter(getJETEmitterDescriptors(), EDIT_PLUGIN_PROPERTIES_INTERFACE_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateEditPluginImagesInterface(GenModel genModel, Monitor monitor)
  {
    if (!genModel.sameEditEditorProject())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genModel.getQualifiedEditPluginClassName() + "Images" });
      monitor.subTask(message);
      generateJava
        (genModel.getEditPluginDirectory(),
         genModel.getEditPluginPackageName(),
         genModel.getEditPluginClassName() + "Images",
         getJETEmitter(getJETEmitterDescriptors(), EDIT_PLUGIN_IMAGES_INTERFACE_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateEditManifest(GenModel genModel, Monitor monitor)
  {
    if (!genModel.sameEditEditorProject())
    {
      if (genModel.isBundleManifest())
      {
        message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditManifestMF_message");
        monitor.subTask(message);

        // Do allow an existing MANIFEST.MF to be overwritten, since it may have been created as part of an empty EMF project.
        // Use the existence of a plugin.xml as a guard against overwriting in a project that has already been generated.
        //
        generateText
          (genModel.getEditProjectDirectory() + "/META-INF/MANIFEST.MF",
           getJETEmitter(getJETEmitterDescriptors(), EDIT_MANIFEST_MF_ID),
           null,
           genModel.isUpdateClasspath() && !exists(toURI(genModel.getEditProjectDirectory()).appendSegment("plugin.xml")),
           MANIFEST_ENCODING,
           createMonitor(monitor, 1));
      }
      else
      {
        monitor.worked(1);
      }

      if (genModel.getRuntimePlatform() != GenRuntimePlatform.GWT)
      {
        message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditPluginXML_message");
        monitor.subTask(message);
        generateText
          (genModel.getEditProjectDirectory() + "/plugin.xml",
           getJETEmitter(getJETEmitterDescriptors(), EDIT_PLUGIN_XML_ID),
           null,
           false,
           MANIFEST_ENCODING,
           createMonitor(monitor, 1));
      }
      else
      {
        monitor.worked(1);
      }
    }
    else
    {
      monitor.worked(2);
    }
  }

  protected void generateEditModule(GenModel genModel, Monitor monitor)
  {
    if (genModel.getRuntimePlatform() == GenRuntimePlatform.GWT && !genModel.sameModelEditorProject())
    {
      String qualifiedEditModuleName = genModel.getQualifiedEditModuleName();
      message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingModuleGWTXML_message", new Object[] { qualifiedEditModuleName });
      monitor.subTask(message);
      generateText
        (genModel.getEditDirectory() + "/" + qualifiedEditModuleName.replace(".", "/") + ".gwt.xml",
         getJETEmitter(getJETEmitterDescriptors(), EDIT_MODULE_GWT_XML_ID),
         null,
         false,
         MANIFEST_ENCODING,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateEditPluginProperties(GenModel genModel, Monitor monitor)
  {
    if (true /* && genModel.getRuntimePlatform() != GenRuntimePlatform.GWT */)
    {
      message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditPluginProperties_message");
      monitor.subTask(message);
      generateProperties
        (genModel.getEditProjectDirectory() + "/plugin.properties",
         getJETEmitter(getJETEmitterDescriptors(), EDIT_PLUGIN_PROPERTIES_ID),
         null,
         createMonitor(monitor, 1));
    }
    /*
    else
    {
      monitor.worked(1);
    }
    */
  }

  protected void generateEditBuildProperties(GenModel genModel, Monitor monitor)
  {
    message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditBuildProperties_message");
    monitor.subTask(message);

    // Do allow an existing build.properties to be overwritten, since it may have been created as part of an empty EMF project.
    // Use the existence of a plugin.xml as a guard against overwriting in a project that has already been generated.
    //
    generateText
      (genModel.getEditProjectDirectory() + "/build.properties",
       getJETEmitter(getJETEmitterDescriptors(), EDIT_BUILD_PROPERTIES_ID),
       null,
       genModel.isUpdateClasspath() && !exists(toURI(genModel.getEditProjectDirectory()).appendSegment("plugin.xml")),
       PROPERTIES_ENCODING,
       createMonitor(monitor, 1));
  }

  @Override
  protected Diagnostic generateEditor(Object object, Monitor monitor)
  {
    monitor.beginTask("", 12);

    GenModel genModel = (GenModel)object;
    monitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorPackages_message"));
    monitor.subTask(message);

    ensureProjectExists
      (genModel.getEditorDirectory(), genModel, EDITOR_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

    generateEditorPluginClass(genModel, monitor);
    generateEditorEntryPointClass(genModel, monitor);
    generateEditorPluginProperties(genModel, monitor);
    generateEditorBuildProperties(genModel, monitor);
    generateEditorManifest(genModel, monitor);
    generateEditorModule(genModel, monitor);
    generateEditorWebXML(genModel, monitor);
    generateEditorHomeHTML(genModel, monitor);
    generateEditorAppEngineWebXML(genModel, monitor);
    generateAdvisor(genModel, monitor);

    return Diagnostic.OK_INSTANCE;
  }

  protected void generateEditorPluginClass(GenModel genModel, Monitor monitor)
  {
    message = CodeGenEcorePlugin.INSTANCE.getString
      ("_UI_GeneratingJavaClass_message", new Object[] { genModel.getQualifiedEditorPluginClassName() });
    monitor.subTask(message);
    generateJava
      (genModel.getEditorPluginDirectory(),
       genModel.getEditorPluginPackageName(),
       genModel.getEditorPluginClassName(),
       getJETEmitter(getJETEmitterDescriptors(), EDITOR_PLUGIN_CLASS_ID),
       null,
       createMonitor(monitor, 1));
  }

  protected void generateEditorEntryPointClass(GenModel genModel, Monitor monitor)
  {
    if (genModel.getRuntimePlatform() == GenRuntimePlatform.GWT)
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genModel.getQualifiedEditorPluginClassName() });
      monitor.subTask(message);
      generateJava
        (genModel.getEditorPluginDirectory(),
         genModel.getEditorPluginPackageName(),
         genModel.getEditorEntryPointClassName(),
         getJETEmitter(getJETEmitterDescriptors(), EDITOR_ENTRY_POINT_CLASS_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateEditorManifest(GenModel genModel, Monitor monitor)
  {
    if (genModel.isBundleManifest())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorManifestMF_message");
      monitor.subTask(message);

      // Do allow an existing MANIFEST.MF to be overwritten, since it may have been created as part of an empty EMF project.
      // Use the existence of a plugin.xml as a guard against overwriting in a project that has already been generated.
      //
      generateText
        (genModel.getEditorProjectDirectory() + "/META-INF/MANIFEST.MF",
         getJETEmitter(getJETEmitterDescriptors(), EDITOR_MANIFEST_MF_ID),
         null,
         genModel.isUpdateClasspath() && !exists(toURI(genModel.getEditorProjectDirectory()).appendSegment("plugin.xml")),
         MANIFEST_ENCODING,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }

    if (genModel.getRuntimePlatform() != GenRuntimePlatform.GWT)
    {
      message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorPluginXML_message");
      monitor.subTask(message);
      generateText
        (genModel.getEditorProjectDirectory() + "/plugin.xml",
         getJETEmitter(getJETEmitterDescriptors(), EDITOR_PLUGIN_XML_ID),
         null,
         false,
         MANIFEST_ENCODING,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateEditorModule(GenModel genModel, Monitor monitor)
  {
    if (genModel.getRuntimePlatform() == GenRuntimePlatform.GWT)
    {
      String qualifiedEditorModuleName = genModel.getQualifiedEditorModuleName();
      message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingModuleGWTXML_message", new Object[] { qualifiedEditorModuleName });
      monitor.subTask(message);
      generateText
        (genModel.getEditorDirectory() + "/" + qualifiedEditorModuleName.replace(".", "/") + ".gwt.xml",
         getJETEmitter(getJETEmitterDescriptors(), EDITOR_MODULE_GWT_XML_ID),
         null,
         false,
         MANIFEST_ENCODING,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateEditorPluginProperties(GenModel genModel, Monitor monitor)
  {
    message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorPluginProperties_message");
    monitor.subTask(message);
    generateProperties
      (genModel.getEditorProjectDirectory() + "/plugin.properties",
       getJETEmitter(getJETEmitterDescriptors(), EDITOR_PLUGIN_PROPERTIES_ID),
       null,
       createMonitor(monitor, 1));
  }

  protected void generateEditorBuildProperties(GenModel genModel, Monitor monitor)
  {
    message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorBuildProperties_message");
    monitor.subTask(message);

    // Do allow an existing build.properties to be overwritten, since it may have been created as part of an empty EMF project.
    // Use the existence of a plugin.xml as a guard against overwriting in a project that has already been generated.
    //
    generateText
      (genModel.getEditorProjectDirectory() + "/build.properties",
       getJETEmitter(getJETEmitterDescriptors(), EDITOR_BUILD_PROPERTIES_ID),
       null,
       genModel.isUpdateClasspath() && !exists(toURI(genModel.getEditorProjectDirectory()).appendSegment("plugin.xml")),
       PROPERTIES_ENCODING,
       createMonitor(monitor, 1));
  }

  protected void generateEditorWebXML(GenModel genModel, Monitor monitor)
  {
    if (genModel.getRuntimePlatform() == GenRuntimePlatform.GWT)
    {
      message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorWebXML_message");
      monitor.subTask(message);
      generateText
        (genModel.getEditorProjectDirectory() + "/war/WEB-INF/web.xml",
         getJETEmitter(getJETEmitterDescriptors(), EDITOR_WEB_XML_ID),
         null,
         false,
         MANIFEST_ENCODING,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateEditorAppEngineWebXML(GenModel genModel, Monitor monitor)
  {
    if (genModel.getRuntimePlatform() == GenRuntimePlatform.GWT)
    {
      message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorAppEngineWebXML_message");
      monitor.subTask(message);
      generateText
        (genModel.getEditorProjectDirectory() + "/war/WEB-INF/appengine-web.xml",
         getJETEmitter(getJETEmitterDescriptors(), EDITOR_APPENGINE_WEB_XML_ID),
         null,
         false,
         MANIFEST_ENCODING,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateEditorHomeHTML(GenModel genModel, Monitor monitor)
  {
    if (genModel.getRuntimePlatform() == GenRuntimePlatform.GWT)
    {
      message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorHomePageHTML_message", new Object[] { genModel.getEditorHomePageName() + ".html" });
      monitor.subTask(message);
      generateText
        (genModel.getEditorProjectDirectory() + "/war/" + genModel.getEditorHomePageName() + ".html",
         getJETEmitter(getJETEmitterDescriptors(), EDITOR_HOME_HMTL_ID),
         null,
         false,
         MANIFEST_ENCODING,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateAdvisor(GenModel genModel, Monitor monitor)
  {
    if (genModel.isRichClientPlatform())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genModel.getQualifiedEditorAdvisorClassName() });
      monitor.subTask(message);
      generateJava
        (genModel.getEditorPluginDirectory(),
         genModel.getEditorPluginPackageName(),
         genModel.getEditorAdvisorClassName(),
         getJETEmitter(getJETEmitterDescriptors(), ADVISOR_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  @Override
  protected Diagnostic generateTests(Object object, Monitor monitor)
  {
    monitor.beginTask("", 5);

    GenModel genModel = (GenModel)object;
    message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingTestsPackages_message");
    monitor.subTask(message);

    ensureProjectExists
      (genModel.getTestsDirectory(), genModel, TESTS_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

    generateModelTestSuite(genModel, monitor);
    generateTestsPluginProperties(genModel, monitor);
    generateTestsBuildProperties(genModel, monitor);
    generateTestsManifest(genModel, monitor);

    return Diagnostic.OK_INSTANCE;
  }

  protected void generateModelTestSuite(GenModel genModel, Monitor monitor)
  {
    if (genModel.hasTestSuiteClass())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genModel.getQualifiedTestSuiteClassName() });
      monitor.subTask(message);
      generateJava
        (genModel.getTestsDirectory(),
         genModel.getTestSuitePackageName(),
         genModel.getTestSuiteClassName(),
         getJETEmitter(getJETEmitterDescriptors(), MODEL_TEST_SUITE_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateTestsManifest(GenModel genModel, Monitor monitor)
  {
    if (!genModel.sameModelTestsProject())
    {
      if (genModel.isBundleManifest())
      {
        message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingTestsManifestMF_message");
        monitor.subTask(message);

        // Do not allow an existing MANIFEST.MF to be overwritten, as the tests project is originally generated from scratch.
        //
        generateText
          (genModel.getTestsProjectDirectory() + "/META-INF/MANIFEST.MF",
           getJETEmitter(getJETEmitterDescriptors(), TESTS_MANIFEST_MF_ID),
           null,
           false,
           MANIFEST_ENCODING,
           createMonitor(monitor, 1));
      }
      else if (genModel.getRuntimePlatform() != GenRuntimePlatform.GWT)
      {
        message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingTestsPluginXML_message");
        monitor.subTask(message);
        generateText
          (genModel.getTestsProjectDirectory() + "/plugin.xml",
           getJETEmitter(getJETEmitterDescriptors(), TESTS_PLUGIN_XML_ID),
           null,
           false,
           MANIFEST_ENCODING,
           createMonitor(monitor, 1));
      }
      else
      {
        monitor.worked(1);
      }
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateTestsPluginProperties(GenModel genModel, Monitor monitor)
  {
    if (!genModel.sameModelTestsProject())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingTestsPluginProperties_message");
      monitor.subTask(message);
      generateProperties
        (genModel.getTestsProjectDirectory() + "/plugin.properties",
         getJETEmitter(getJETEmitterDescriptors(), TESTS_PLUGIN_PROPERTIES_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateTestsBuildProperties(GenModel genModel, Monitor monitor)
  {
    if (!genModel.sameModelTestsProject())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingTestsBuildProperties_message");
      monitor.subTask(message);

      // Do not allow an existing build.properties to be overwritten, as the tests project is originally generated from scratch.
      //
      generateText
        (genModel.getTestsProjectDirectory() + "/build.properties",
         getJETEmitter(getJETEmitterDescriptors(), TESTS_BUILD_PROPERTIES_ID),
         null,
         false,
         PROPERTIES_ENCODING,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  @Override
  public void notifyChanged(Notification notification)
  {
    switch (notification.getFeatureID(GenModel.class))
    {
      case GenModelPackage.GEN_MODEL__REDIRECTION:
      case GenModelPackage.GEN_MODEL__FORCE_OVERWRITE:
      case GenModelPackage.GEN_MODEL__DYNAMIC_TEMPLATES:
      case GenModelPackage.GEN_MODEL__TEMPLATE_DIRECTORY:
      case GenModelPackage.GEN_MODEL__FACADE_HELPER_CLASS:
      case GenModelPackage.GEN_MODEL__CODE_FORMATTING:
        getGenerator().requestInitialize();
        break;
    }
    super.notifyChanged(notification);
  }
}
