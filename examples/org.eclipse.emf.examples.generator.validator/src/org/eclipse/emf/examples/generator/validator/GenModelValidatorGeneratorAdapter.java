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
package org.eclipse.emf.examples.generator.validator;

import java.util.List;

import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;

public class GenModelValidatorGeneratorAdapter extends GenBaseGeneratorAdapter
{
  protected static final int MODEL_DESCRIPTION_ID = 0;

  protected static final JETEmitterDescriptor[] JET_EMITTER_DESCRIPTORS =
  {
    new JETEmitterDescriptor("model/ModelDescription.txtjet", "org.eclipse.emf.examples.generator.validator.templates.model.ModelDescription")
  };

  protected JETEmitterDescriptor[] getJETEmitterDescriptors()
  {
    return JET_EMITTER_DESCRIPTORS;
  }

  public GenModelValidatorGeneratorAdapter()
  {
    super();
  }

  public GenModelValidatorGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory)
  {
    super(generatorAdapterFactory);
  }

  @Override
  public boolean canGenerate(Object object, Object projectType)
  {
    return MODEL_PROJECT_TYPE.equals(projectType) ? super.canGenerate(object, projectType) : false;
  }

  @Override
  protected Diagnostic generateModel(Object object, Monitor monitor)
  {
    GenModel genModel = (GenModel)object;

    monitor.beginTask("", 2);
    message = ValidatorGeneratorPlugin.INSTANCE.getString("GeneratingModelDescription.message");    
    monitor.subTask(message);

    ensureProjectExists
      (genModel.getModelDirectory(), genModel, MODEL_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

    String targetFile = genModel.getModelProjectDirectory() + "/text/description.txt";
    generateText
      (targetFile,
       getJETEmitter(getJETEmitterDescriptors(), MODEL_DESCRIPTION_ID),
       null,
       true,
       getEncoding(URI.createURI(targetFile)),
       createMonitor(monitor, 1));

    return Diagnostic.OK_INSTANCE;
  }

  @Override
  protected void addBaseTemplatePathEntries(List<String> templatePath)
  {
    templatePath.add(ValidatorGeneratorUtil.TEMPLATE_LOCATION);
    super.addBaseTemplatePathEntries(templatePath);
  }

  @Override
  protected void addClasspathEntries(JETEmitter jetEmitter) throws JETException
  {
    super.addClasspathEntries(jetEmitter);
    jetEmitter.addVariable(ValidatorGeneratorUtil.CLASSPATH_VARIABLE_NAME, ValidatorGeneratorPlugin.ID);
  }
}