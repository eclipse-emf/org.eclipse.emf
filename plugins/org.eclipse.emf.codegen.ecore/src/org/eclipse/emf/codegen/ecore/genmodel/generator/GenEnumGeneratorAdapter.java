/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.generator;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;

/**
 * A {@link GeneratorAdapter} for instances of {@link GenEnum}. This contributes the artifacts for every enum to EMF's
 * default code generation.
 * 
 * <p>This implementation should not be extended merely to augment the default code generation for enums. The
 * recommended approach is to implement a new adapter and register the {@link GeneratorAdapterFactory adapter factory}
 * that creates it, so that it is contributed to code generation. Such registration is usually done through the
 * <code>org.eclipse.emf.codegen.ecore.generatorAdapters</code> extension point.
 * 
 * <p>This implementation may be extended, however, in order to remove from or change the default code generation.
 * 
 * @since 2.2.0
 */
public class GenEnumGeneratorAdapter extends GenBaseGeneratorAdapter
{
  protected static final int ENUM_CLASS_ID = 0;

  private static final JETEmitterDescriptor[] JET_EMITTER_DESCRIPTORS =
  {
    new JETEmitterDescriptor("model/EnumClass.javajet", "org.eclipse.emf.codegen.ecore.templates.model.EnumClass")
  };

  /**
   * Returns the set of <code>JETEmitterDescriptor</code>s used by the adapter. The contents of the returned array
   * should never be changed. Rather, subclasses may override this method to return a different array altogether.
   */
  protected JETEmitterDescriptor[] getJETEmitterDescriptors()
  {
    return JET_EMITTER_DESCRIPTORS;
  }

  public GenEnumGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory)
  {
    super(generatorAdapterFactory);
  }

  /**
   * Returns the {@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage} that contains the given {@link GenEnum}.
   */
  @Override
  public Object getGenerateParent(Object object, Object projectType)
  {
    return getParent(object);
  }

  @Override
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
