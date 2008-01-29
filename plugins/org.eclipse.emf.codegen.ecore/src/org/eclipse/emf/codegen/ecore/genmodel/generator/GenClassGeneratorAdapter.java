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
 * $Id: GenClassGeneratorAdapter.java,v 1.9 2008/01/29 21:12:08 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.generator;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;

/**
 * A {@link GeneratorAdapter} for instances of {@link GenClass}. This contributes the artifacts for every class to EMF's
 * default code generation.
 * 
 * <p>This implementation should not be extended merely to augment the default code generation for classes. The
 * recommended approach is to implement a new adapter and register the {@link GeneratorAdapterFactory adapter factory}
 * that creates it, so that it is contributed to code generation. Such registration is usually done through the
 * <code>org.eclipse.emf.codegen.ecore.generatorAdapters</code> extension point.
 * 
 * <p>This implementation may be extended, however, in order to remove from or change the default code generation.
 * 
 * @since 2.2.0
 */
public class GenClassGeneratorAdapter extends GenBaseGeneratorAdapter
{
  protected static final int CLASS_ID = 0;
  protected static final int ITEM_PROVIDER_ID = 1;
  protected static final int TEST_CASE_ID = 2;

  private static final JETEmitterDescriptor[] JET_EMITTER_DESCRIPTORS =
  {
    new JETEmitterDescriptor("model/Class.javajet", "org.eclipse.emf.codegen.ecore.templates.model.Class"),
    new JETEmitterDescriptor("edit/ItemProvider.javajet", "org.eclipse.emf.codegen.ecore.templates.edit.ItemProvider"),
    new JETEmitterDescriptor("model.tests/TestCase.javajet", "org.eclipse.emf.codegen.ecore.templates.model.tests.TestCase")
  };

  /**
   * Returns the set of <code>JETEmitterDescriptor</code>s used by the adapter. The contents of the returned array
   * should never be changed. Rather, subclasses may override this method to return a different array altogether.
   */
  protected JETEmitterDescriptor[] getJETEmitterDescriptors()
  {
    return JET_EMITTER_DESCRIPTORS;
  }

  protected static final int ITEM_ICON_ID = 0;
  protected static final int CREATE_CHILD_ICON_ID = 1;

  private static final String[] INPUT_PATH_NAMES = { "edit/Item.gif", "edit/CreateChild.gif" };

  /**
   * Returns the set of {@link org.eclipse.emf.codegen.util.GIFEmitter} input paths used by the adapter. The contents
   * of the returned array should never be changed. Rather, subclasses may override this method to return a different
   * array altogether.
   */
  protected String[] getInputPathNames()
  {
    return INPUT_PATH_NAMES;
  }

  public GenClassGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory)
  {
    super(generatorAdapterFactory);
  }

  /**
   * Returns the {@link GenPackage} that contains the given {@link GenClass}.
   */
  @Override
  public Object getGenerateParent(Object object, Object projectType)
  {
    return getParent(object);
  }

  @Override
  protected Diagnostic generateModel(Object object, Monitor monitor)
  {
    monitor.beginTask("", 3);

    GenClass genClass = (GenClass)object;
    message = CodeGenEcorePlugin.INSTANCE.getString("_UI_Generating_message", new Object[] { genClass.getFormattedName() });
    monitor.subTask(message);

    GenModel genModel = genClass.getGenModel();
    ensureProjectExists
      (genModel.getModelDirectory(), genClass, MODEL_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

    generateInterface(genClass, monitor);
    generateClass(genClass, monitor);
    
    return Diagnostic.OK_INSTANCE;
  }

  protected void generateInterface(GenClass genClass, Monitor monitor)
  {
    GenModel genModel = genClass.getGenModel();
    GenPackage genPackage = genClass.getGenPackage();

    if (!genClass.isExternalInterface() && (!genModel.isSuppressInterfaces() || genClass.isInterface()))
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaInterface_message",
         new Object[] { genPackage.getInterfacePackageName() + "." + genClass.getInterfaceName() });
      monitor.subTask(message);
      generateJava
        (genModel.getModelDirectory(),
         genPackage.getInterfacePackageName(),
         genClass.getInterfaceName(),
         getJETEmitter(getJETEmitterDescriptors(), CLASS_ID),
         new Object[] { new Object[] { genClass, Boolean.TRUE, Boolean.FALSE }},
         createMonitor(monitor, 1));           
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateClass(GenClass genClass, Monitor monitor)
  {
    GenModel genModel = genClass.getGenModel();
    GenPackage genPackage = genClass.getGenPackage();

    if (!genClass.isInterface())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
         ("_UI_GeneratingJavaClass_message", new Object[] { genClass.getQualifiedClassName() });
      monitor.subTask(message);
      generateJava
        (genModel.getModelDirectory(),
         genPackage.getClassPackageName(),
         genClass.getClassName(),
         getJETEmitter(getJETEmitterDescriptors(), CLASS_ID),
         new Object[] { new Object[] { genClass, genModel.isSuppressInterfaces() ? Boolean.TRUE : Boolean.FALSE, Boolean.TRUE }},
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
    GenClass genClass = (GenClass)object;
    monitor.beginTask("", 3 + countCreateChildIcons(genClass));

    message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingProvider_message", new Object[] { genClass.getFormattedName() });
    monitor.subTask(message);

    GenModel genModel = genClass.getGenModel();
    ensureProjectExists
      (genModel.getEditDirectory(), genClass, EDIT_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

    generateItemProvider(genClass, monitor);
    generateItemIcon(genClass, monitor);
    generateCreateChildIcons(genClass, monitor);

    return Diagnostic.OK_INSTANCE;
  }

  protected void generateItemProvider(GenClass genClass, Monitor monitor)
  {
    message =  CodeGenEcorePlugin.INSTANCE.getString
      ("_UI_GeneratingJavaClass_message", new Object[] { genClass.getQualifiedProviderClassName() });
    monitor.subTask(message);
    generateJava
      (genClass.getGenModel().getEditDirectory(),
       genClass.getGenPackage().getProviderPackageName(),
       genClass.getProviderClassName(),
       getJETEmitter(getJETEmitterDescriptors(), ITEM_PROVIDER_ID),
       null,
       createMonitor(monitor, 1)); 
  }

  protected void generateItemIcon(GenClass genClass, Monitor monitor)
  {
    if (genClass.isImage())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingItemIcon_message", new Object[] { genClass.getItemIconFileName() });
      monitor.subTask(message);
      generateGIF
        (genClass.getItemIconFileName(),
         getGIFEmitter(getInputPathNames(), ITEM_ICON_ID),
         genClass.getName(),
         null,
         false,
         createMonitor(monitor, 1)); 
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected int countCreateChildIcons(GenClass genClass)
  {
    int result = 0;
    GenModel genModel = genClass.getGenModel();
    if (genModel.isCreationCommands() && genModel.isCreationIcons())
    {
      for (GenFeature feature : genClass.getCreateChildFeaturesIncludingDelegation())
      {
        result += genClass.getChildrenClasses(feature).size();
      }
    }
    return result;
  }

  protected void generateCreateChildIcons(GenClass genClass, Monitor monitor)
  {
    GenModel genModel = genClass.getGenModel();
    if (genModel.isCreationCommands() && genModel.isCreationIcons())
    {
      for (GenFeature feature : genClass.getCreateChildFeaturesIncludingDelegation())
      {
        for (GenClass childClass : genClass.getChildrenClasses(feature))
        {
          message = CodeGenEcorePlugin.INSTANCE.getString
            ("_UI_GeneratingCreateChildIcon_message", new Object[] { genClass.getCreateChildIconFileName(feature, childClass) });
          monitor.subTask(message);
          generateGIF
            (genClass.getCreateChildIconFileName(feature, childClass),
             getGIFEmitter(getInputPathNames(), CREATE_CHILD_ICON_ID),
             genClass.getName(),
             childClass.getName(),
             false,
             createMonitor(monitor, 1));
        }
      }
    }
  }

  @Override
  protected Diagnostic generateTests(Object object, Monitor monitor)
  {
    monitor.beginTask("", 2);

    GenClass genClass = (GenClass)object;
    message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingTestCase_message", new Object[] { genClass.getFormattedName() });
    monitor.subTask(message);

    GenModel genModel = genClass.getGenModel();
    ensureProjectExists
      (genModel.getTestsDirectory(), genClass, TESTS_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

    generateTestCase(genClass, monitor);

    return Diagnostic.OK_INSTANCE;
  }

  protected void generateTestCase(GenClass genClass, Monitor monitor)
  {
    message = CodeGenEcorePlugin.INSTANCE.getString
      ("_UI_GeneratingJavaClass_message", new Object[] { genClass.getQualifiedTestCaseClassName() });
    monitor.subTask(message);
    generateJava
      (genClass.getGenModel().getTestsDirectory(),
       genClass.getGenPackage().getTestsPackageName(),
       genClass.getTestCaseClassName(),
       getJETEmitter(getJETEmitterDescriptors(), TEST_CASE_ID),
       null,
       createMonitor(monitor, 1));
  }
}
