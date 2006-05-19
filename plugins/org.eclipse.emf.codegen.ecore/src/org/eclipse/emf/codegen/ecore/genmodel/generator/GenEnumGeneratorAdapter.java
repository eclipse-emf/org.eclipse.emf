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
 * $Id: GenEnumGeneratorAdapter.java,v 1.4 2006/05/19 22:33:50 davidms Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.generator;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;

/**
 * @since 2.2.0
 */
public class GenEnumGeneratorAdapter extends GenBaseGeneratorAdapter
{
  protected static final int ENUM_CLASS_ID = 0;

  private static final JETEmitterDescriptor[] JET_EMITTER_DESCRIPTORS =
  {
    new JETEmitterDescriptor("model/EnumClass.javajet", "org.eclipse.emf.codegen.ecore.templates.model.EnumClass")
  };

  protected JETEmitterDescriptor[] getJETEmitterDescriptors()
  {
    return JET_EMITTER_DESCRIPTORS;
  }

  public GenEnumGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory)
  {
    super(generatorAdapterFactory);
  }

  public Object getGenerateParent(Object object, Object projectType)
  {
    return getParent(object);
  }

  protected Diagnostic generateModel(Object object, Monitor monitor)
  {
    monitor.beginTask("", 2);

    GenEnum genEnum = (GenEnum)object;
    message = CodeGenEcorePlugin.INSTANCE.getString("_UI_Generating_message", new Object[] { genEnum.getFormattedName() });
    monitor.subTask(message);

    GenModel genModel = genEnum.getGenModel();
    ensureProjectExists(genModel.getModelDirectory(), genEnum, MODEL_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

    message = CodeGenEcorePlugin.INSTANCE.getString
      ("_UI_GeneratingJavaClass_message", new Object[] { genEnum.getGenPackage().getInterfacePackageName() + "." + genEnum.getName() });

    generateEnumClass(genEnum, monitor);

    return Diagnostic.OK_INSTANCE;
  }

  protected void generateEnumClass(GenEnum genEnum, Monitor monitor)
  {
    monitor.subTask(message);
    generateJava
      (genEnum.getGenModel().getModelDirectory(),
       genEnum.getGenPackage().getInterfacePackageName(),
       genEnum.getName(),
       getJETEmitter(getJETEmitterDescriptors(), ENUM_CLASS_ID),
       null,
       createMonitor(monitor, 1)); 
  }
}
