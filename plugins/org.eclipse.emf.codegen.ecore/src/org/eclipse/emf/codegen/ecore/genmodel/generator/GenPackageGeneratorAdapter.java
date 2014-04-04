/**
 * Copyright (c) 2006-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimePlatform;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;

/**
 * A {@link GeneratorAdapter} for instances of {@link GenPackage}. This contributes the package level artifacts to EMF's
 * default code generation.
 * 
 * <p>This implementation should not be extended merely to augment the default code generation for packages. The
 * recommended approach is to implement a new adapter and register the {@link GeneratorAdapterFactory adapter factory}
 * that creates it, so that it is contributed to code generation. Such registration is usually done through the
 * <code>org.eclipse.emf.codegen.ecore.generatorAdapters</code> extension point.
 * 
 * <p>This implementation may be extended, however, in order to remove from or change the default code generation.
 * 
 * @since 2.2.0
 */
public class GenPackageGeneratorAdapter extends GenBaseGeneratorAdapter
{
  protected static final int PACKAGE_CLASS_ID = 0;
  protected static final int FACTORY_CLASS_ID = 1;
  protected static final int XML_PROCESSOR_CLASS_ID = 2;
  protected static final int VALIDATOR_CLASS_ID = 3;
  protected static final int SWITCH_CLASS_ID = 4;
  protected static final int ADAPTER_FACTORY_CLASS_ID = 5;
  protected static final int RESOURCE_FACTORY_CLASS_ID = 6;
  protected static final int RESOURCE_CLASS_ID = 7;
  protected static final int ITEM_PROVIDER_ADAPTER_FACTORY_ID = 8;
  protected static final int EDITOR_ID = 9;
  protected static final int MODEL_WIZARD_ID = 10;
  protected static final int ACTION_BAR_CONTRIBUTOR_ID = 11;
  protected static final int PACKAGE_TEST_SUITE_ID = 12;
  protected static final int PACKAGE_EXAMPLE_ID = 13;

  private static final JETEmitterDescriptor[] JET_EMITTER_DESCRIPTORS =
  {
    new JETEmitterDescriptor("model/PackageClass.javajet", "org.eclipse.emf.codegen.ecore.templates.model.PackageClass"),
    new JETEmitterDescriptor("model/FactoryClass.javajet", "org.eclipse.emf.codegen.ecore.templates.model.FactoryClass"),
    new JETEmitterDescriptor("model/XMLProcessorClass.javajet", "org.eclipse.emf.codegen.ecore.templates.model.XMLProcessorClass"),
    new JETEmitterDescriptor("model/ValidatorClass.javajet", "org.eclipse.emf.codegen.ecore.templates.model.ValidatorClass"),
    new JETEmitterDescriptor("model/SwitchClass.javajet", "org.eclipse.emf.codegen.ecore.templates.model.SwitchClass"),
    new JETEmitterDescriptor("model/AdapterFactoryClass.javajet", "org.eclipse.emf.codegen.ecore.templates.model.AdapterFactoryClass"),
    new JETEmitterDescriptor("model/ResourceFactoryClass.javajet", "org.eclipse.emf.codegen.ecore.templates.model.ResourceFactoryClass"),
    new JETEmitterDescriptor("model/ResourceClass.javajet", "org.eclipse.emf.codegen.ecore.templates.model.ResourceClass"),
    new JETEmitterDescriptor("edit/ItemProviderAdapterFactory.javajet", "org.eclipse.emf.codegen.ecore.templates.edit.ItemProviderAdapterFactory"),
    new JETEmitterDescriptor("editor/Editor.javajet", "org.eclipse.emf.codegen.ecore.templates.editor.Editor"),
    new JETEmitterDescriptor("editor/ModelWizard.javajet", "org.eclipse.emf.codegen.ecore.templates.editor.ModelWizard"),
    new JETEmitterDescriptor("editor/ActionBarContributor.javajet", "org.eclipse.emf.codegen.ecore.templates.editor.ActionBarContributor"),
    new JETEmitterDescriptor("model.tests/PackageTestSuite.javajet", "org.eclipse.emf.codegen.ecore.templates.model.tests.PackageTestSuite"),
    new JETEmitterDescriptor("model.tests/PackageExample.javajet", "org.eclipse.emf.codegen.ecore.templates.model.tests.PackageExample")
  };

  /**
   * Returns the set of <code>JETEmitterDescriptor</code>s used by the adapter. The contents of the returned array
   * should never be changed. Rather, subclasses may override this method to return a different array altogether.
   */
  protected JETEmitterDescriptor[] getJETEmitterDescriptors()
  {
    return JET_EMITTER_DESCRIPTORS;
  }

  protected static final int MODEL_ICON_ID = 0;
  protected static final int MODEL_WIZARD_ICON_ID = 1;
  protected static final int CREATE_CHILD_ICON_ID = 2;

  private static final String[] INPUT_PATH_NAMES = { "editor/ModelFile.gif", "editor/NewModel.gif", "edit/CreateChild.gif" };

  /**
   * Returns the set of {@link org.eclipse.emf.codegen.util.GIFEmitter} input paths used by the adapter. The contents
   * of the returned array should never be changed. Rather, subclasses may override this method to return a different
   * array altogether.
   */
  protected String[] getInputPathNames()
  {
    return INPUT_PATH_NAMES;
  }

  public GenPackageGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory)
  {
    super(generatorAdapterFactory);
  }

  @Override
  protected Collection<?> getGenerateModelChildren(Object object)
  {
    GenPackage genPackage = (GenPackage)object;
    List<GenBase> result = new ArrayList<GenBase>(genPackage.getGenClasses());
    result.addAll(genPackage.getGenEnums());
    result.addAll(genPackage.getNestedGenPackages());
    return result;
  }

  @Override
  protected Collection<?> getGenerateEditChildren(Object object)
  {
    GenPackage genPackage = (GenPackage)object;
    List<GenBase> result = new ArrayList<GenBase>(genPackage.getGenClasses());
    result.addAll(genPackage.getNestedGenPackages());
    return result;
  }

  @Override
  protected Collection<?> getGenerateEditorChildren(Object object)
  {
    return new ArrayList<GenPackage>(((GenPackage)object).getNestedGenPackages());
  }

  @Override
  protected Collection<?> getGenerateTestsChildren(Object object)
  {
    GenPackage genPackage = (GenPackage)object;
    List<GenBase> result = new ArrayList<GenBase>(genPackage.getGenClasses());
    result.addAll(genPackage.getNestedGenPackages());
    return result;
  }

  /**
   * Returns the {@link GenModel} or {@link GenPackage} that contains the given {@link GenPackage}.
   */
  @Override
  public Object getGenerateParent(Object object, Object projectType)
  {
    return getParent(object);
  }

  /**
   * Prepares the {@link GenPackage} for generation.
   */
  @Override
  protected Diagnostic doPreGenerate(Object object, Object projectType)
  {
    if (MODEL_PROJECT_TYPE.equals(projectType))
    {
      ((GenPackage)object).prepareCache();
      return Diagnostic.OK_INSTANCE;
    }
    return super.doPreGenerate(object, projectType);
  }

  /**
   * Cleans up the {@link GenPackage} after generation.
   */
  @Override
  protected Diagnostic doPostGenerate(Object object, Object projectType)
  {
    if (MODEL_PROJECT_TYPE.equals(projectType))
    {
      ((GenPackage)object).clearCache();
      return Diagnostic.OK_INSTANCE;
    }
    return super.doPostGenerate(object, projectType);
  }

  @Override
  protected Diagnostic generateModel(Object object, Monitor monitor)
  {
    monitor.beginTask("", 13);

    GenPackage genPackage = (GenPackage)object;
    message = CodeGenEcorePlugin.INSTANCE.getString
      ("_UI_GeneratingPackage_message", new Object[] { genPackage.getBasicPackageName() });
    monitor.subTask(message);

    GenModel genModel = genPackage.getGenModel();
    ensureProjectExists
      (genModel.getModelDirectory(), genPackage, MODEL_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

    generateSchema(genPackage, monitor);
    generatePackagePublication(genPackage, monitor);
    generatePackageSerialization(genPackage, monitor);
    generatePackageInterface(genPackage, monitor);
    generatePackageClass(genPackage, monitor);
    generateFactoryInterface(genPackage, monitor);
    generateFactoryClass(genPackage, monitor);
    generateXMLProcessorClass(genPackage, monitor);
    generateValidatorClass(genPackage, monitor);
    generateSwitchClass(genPackage, monitor);
    generateAdapterFactoryClass(genPackage, monitor);
    generateResourceFactoryClass(genPackage, monitor);
    generateResourceClass(genPackage, monitor);

    return Diagnostic.OK_INSTANCE;
  }

  @SuppressWarnings("deprecation")
  protected void generateSchema(GenPackage genPackage, Monitor monitor)
  {
    if (genPackage.hasClassifiers() && genPackage.getGenModel().isGenerateSchema())
    {
      // This functionality should eventually go away, as it is replaced by model exporters.
      // We'll just delegate to the deprecated GenPackage method. The monitor isn't used, so we won't pass it.
      //
      genPackage.generateSchema();
    }
    monitor.worked(1);      
  }

  /**
   * @since 2.10
   */
  protected void generatePackagePublication(GenPackage genPackage, Monitor monitor)
  {
    String publicationLocation = genPackage.getPublicationLocation();
    if (publicationLocation != null && !"".equals(publicationLocation))
    {
      monitor = createMonitor(monitor, 1);

      try
      {
        monitor.beginTask("", 2);

        final GenModel genModel = genPackage.getGenModel();
        message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingPackageSerialization_message", new Object[] { publicationLocation });
        monitor.subTask(message);

        URI targetFile = toURI(publicationLocation);
        ensureContainerExists(targetFile.trimSegments(1), createMonitor(monitor, 1));

        final ResourceSet originalSet = genModel.eResource().getResourceSet();
        EPackage originalPackage = genPackage.getEcorePackage();

        ResourceSet outputSet = new ResourceSetImpl();
        outputSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new EcoreResourceFactoryImpl());
        URI targetURI = toPlatformResourceURI(targetFile);
        Resource outputResource = outputSet.createResource(targetURI);

        // Copy the package.
        EPackage outputPackage = EcoreUtil.copy(originalPackage);
        outputResource.getContents().add(outputPackage);

        // This URI handler redirects cross-document references to correct schema-location-based or namespace-based values.
        //
        XMLResource.URIHandler uriHandler = 
          new URIHandlerImpl.PlatformSchemeAware()
          {
            private EPackage getRootContainingPackage(EObject object)
            {
              EPackage result = null;
              while (object != null)
              {
                if (object instanceof EPackage)
                {
                  result = (EPackage)object;
                }
                object = object.eContainer();
              }
              return result;
            }
            
            private EPackage getContainingPackage(EObject object)
            {
              while (object != null)
              {
                if (object instanceof EPackage)
                {
                  return (EPackage)object;
                }
                object = object.eContainer();
              }
              return null;
            }

            private URI redirect(URI uri)
            {
              if (!uri.isCurrentDocumentReference() && uri.hasFragment())
              {
                EObject object = originalSet.getEObject(uri, false);
                if (object != null)
                {
                  EPackage ePackage = getRootContainingPackage(object);
                  if (ePackage != null)
                  {
                    String schemaLocation = EcoreUtil.getAnnotation(ePackage, EcorePackage.eNS_URI, "schemaLocation");
                    if (schemaLocation != null)
                    {
                      return URI.createURI(schemaLocation).appendFragment(uri.fragment());
                    }
                    else
                    {
                      String relativeURIFragmentPath = EcoreUtil.getRelativeURIFragmentPath(getContainingPackage(object), object);
                      return URI.createURI(ePackage.getNsURI()).appendFragment("//" + relativeURIFragmentPath);
                    }
                  }
                }
              }
              return uri;
            }

            @Override
            public URI deresolve(URI uri)
            {
              return redirect(uri);
            }

            @Override
            public URI resolve(URI uri)
            {
              throw new UnsupportedOperationException("There should be no resolving while serializing");
            }

            @Override
            public void setBaseURI(URI uri)
            {
              baseURI = uri;
            }
          };
        Map<Object, Object> options = new HashMap<Object, Object>();
        options.put(XMLResource.OPTION_URI_HANDLER, uriHandler);
        options.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
        options.put(Resource.OPTION_LINE_DELIMITER, Resource.OPTION_LINE_DELIMITER_UNSPECIFIED);

        try
        {
          outputResource.save(options);
        }
        catch (IOException exception)
        {
          CodeGenEcorePlugin.INSTANCE.log(exception);
        }
      }
      finally
      {
        monitor.done();
      }
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generatePackageSerialization(GenPackage genPackage, Monitor monitor)
  {
    if (genPackage.hasClassifiers() && genPackage.isLoadingInitialization())
    {
      monitor = createMonitor(monitor, 1);

      try
      {
        monitor.beginTask("", 2);

        final GenModel genModel = genPackage.getGenModel();
        String targetPathName = genModel.getModelDirectory() + "/" + genPackage.getClassPackageName().replace('.', '/') + "/" +
          genPackage.getSerializedPackageFilename();
        message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingPackageSerialization_message", new Object[] { targetPathName });
        monitor.subTask(message);

        URI targetFile = toURI(targetPathName);
        ensureContainerExists(targetFile.trimSegments(1), createMonitor(monitor, 1));

        final ResourceSet originalSet = genModel.eResource().getResourceSet();
        EPackage originalPackage = genPackage.getEcorePackage();

        ResourceSet outputSet = new ResourceSetImpl();
        outputSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new EcoreResourceFactoryImpl());
        URI targetURI = toPlatformResourceURI(targetFile);        
        Resource outputResource = outputSet.createResource(targetURI);

        // Copy the package, excluding unwanted annotations.
        //
        EcoreUtil.Copier copier =
          new EcoreUtil.Copier()
          {
            private static final long serialVersionUID = 1L;

            @Override
            protected void copyContainment(EReference reference, EObject object, EObject copyEObject)
            {
              if (reference == EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS)
              {
                List<EAnnotation> result = ((EModelElement)copyEObject).getEAnnotations();
                result.clear();

                for (EAnnotation eAnnotation : ((EModelElement)object).getEAnnotations())
                {
                  if (!genModel.isSuppressedAnnotation(eAnnotation.getSource()))
                  {
                    result.add((EAnnotation)copy(eAnnotation));
                  }
                }
                return;
              }
              super.copyContainment(reference, object, copyEObject);
            }
          };
        EPackage outputPackage = (EPackage)copier.copy(originalPackage);
        copier.copyReferences();
        outputResource.getContents().add(outputPackage);
        collapseEmptyPackages(outputPackage);

        // This URI handler redirects cross-document references to correct namespace-based values.
        //
        XMLResource.URIHandler uriHandler = 
          new URIHandlerImpl.PlatformSchemeAware()
          {
            private EPackage getContainingPackage(EObject object)
            {
              while (object != null)
              {
                if (object instanceof EPackage)
                {
                  return (EPackage)object;
                }
                object = object.eContainer();
              }
              return null;
            }

            private String getRelativeFragmentPath(Resource resource, EObject base, String path)
            {
              String basePath = resource.getURIFragment(base);
              if (basePath != null && path.startsWith(basePath))
              {
                int i = basePath.length();
                if (path.length() == i)
                {
                  return "";
                }
                else if (path.charAt(i) == '/')
                {
                  return path.substring(i);
                }
              }
              return null;
            }

            private EPackage getNonEmptySuperPackage(EPackage ePackage)
            {
              EPackage result = ePackage.getESuperPackage();
              while (result != null && result.getEClassifiers().isEmpty())
              {
                result = result.getESuperPackage();
              }
              return result;
            }

            private URI redirect(URI uri)
            {
              if (!uri.isCurrentDocumentReference() && uri.hasFragment())
              {
                URI base = uri.trimFragment();
                String fragment = uri.fragment();
                Resource resource = originalSet.getResource(base, false);
                if (resource != null)
                {
                  EObject object = resource.getEObject(fragment);
                  if (object != null)
                  {
                    EPackage ePackage = getContainingPackage(object);
                    if (ePackage != null)
                    {
                      String relativePath = getRelativeFragmentPath(resource, ePackage, fragment);
                      if (relativePath != null)
                      {
                        StringBuilder path = new StringBuilder();
                        EPackage superPackage = getNonEmptySuperPackage(ePackage);
                        while (superPackage != null)
                        {
                          path.insert(0, '/');
                          path.insert(1, ePackage.getName());
                          ePackage = superPackage;
                          superPackage = getNonEmptySuperPackage(ePackage);
                        }
                        path.insert(0, '/');
                        path.append(relativePath);
                        return URI.createURI(ePackage.getNsURI()).appendFragment(path.toString());
                      }
                    }
                  }
                }
              }
              return uri;
            }

            @Override
            public URI deresolve(URI uri)
            {
              return super.deresolve(redirect(uri));
            }

            @Override
            public URI resolve(URI uri)
            {
              return super.resolve(redirect(uri));
            }

            @Override
            public void setBaseURI(URI uri)
            {
              super.setBaseURI(redirect(uri));
            }
          };
        Map<Object, Object> options = new HashMap<Object, Object>();
        options.put(XMLResource.OPTION_URI_HANDLER, uriHandler);
        options.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
        options.put(Resource.OPTION_LINE_DELIMITER, Resource.OPTION_LINE_DELIMITER_UNSPECIFIED);

        try
        {
          outputResource.save(options);
        }
        catch (IOException exception)
        {
          //DMS handle this well.
          CodeGenEcorePlugin.INSTANCE.log(exception);
        }
      }
      finally
      {
        monitor.done();
      }
    }
    else
    {
      monitor.worked(1);
    }
  }    

  // Returns the change in number of immediate child packages.
  //
  protected int collapseEmptyPackages(EPackage ePackage)
  {
    EList<EPackage> subpackages = ePackage.getESubpackages();
    for (int i = 0; i < subpackages.size(); i++)
    {
      EPackage subpackage = subpackages.get(i);
      i += collapseEmptyPackages(subpackage);
      subpackages = ePackage.getESubpackages();
    }

    EPackage superPackage = ePackage.getESuperPackage();
    if (superPackage != null && ePackage.getEClassifiers().isEmpty())
    {
      int result = subpackages.size() - 1;

      EList<EPackage> siblingPackages = superPackage.getESubpackages();
      int i = siblingPackages.indexOf(ePackage);
      siblingPackages.remove(i);
      siblingPackages.addAll(i, subpackages);
      return result;
    }
    return 0;
  }

  protected void generatePackageInterface(GenPackage genPackage, Monitor monitor)
  {
    GenModel genModel = genPackage.getGenModel();

    if (genPackage.hasClassifiers() && !genModel.isSuppressEMFMetaData() && !genModel.isSuppressInterfaces())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaInterface_message", new Object[] { genPackage.getQualifiedPackageInterfaceName() });
      monitor.subTask(message);
      generateJava
        (genModel.getModelDirectory(),
         genPackage.getReflectionPackageName(),
         genPackage.getPackageInterfaceName(),
         getJETEmitter(getJETEmitterDescriptors(), PACKAGE_CLASS_ID),
         new Object[] { new Object[] { genPackage, Boolean.TRUE, Boolean.FALSE }},
         createMonitor(monitor, 1)); 
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generatePackageClass(GenPackage genPackage, Monitor monitor)
  {
    GenModel genModel = genPackage.getGenModel();

    if (genPackage.hasClassifiers())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genPackage.getQualifiedPackageClassName() });
      monitor.subTask(message);
      generateJava
        (genModel.getModelDirectory(),
         genPackage.getReflectionClassPackageName(),
         genPackage.getPackageClassName(),
         getJETEmitter(getJETEmitterDescriptors(), PACKAGE_CLASS_ID),
         new Object[]
         {
           new Object[] 
           {
             genPackage, 
             genModel.isSuppressEMFMetaData() || genModel.isSuppressInterfaces() ? Boolean.TRUE: Boolean.FALSE, 
             Boolean.TRUE 
           }
         },
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateFactoryInterface(GenPackage genPackage, Monitor monitor)
  {
    GenModel genModel = genPackage.getGenModel();

    if (genPackage.hasClassifiers() && !genModel.isSuppressInterfaces())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaInterface_message", new Object[] { genPackage.getQualifiedFactoryInterfaceName() });
      monitor.subTask(message);
      generateJava
        (genModel.getModelDirectory(),
         genPackage.getReflectionPackageName(), 
         genPackage.getFactoryInterfaceName(),
         getJETEmitter(getJETEmitterDescriptors(), FACTORY_CLASS_ID),
         new Object[] { new Object[] { genPackage, Boolean.TRUE, Boolean.FALSE }},
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateFactoryClass(GenPackage genPackage, Monitor monitor)
  {
    GenModel genModel = genPackage.getGenModel();

    if (genPackage.hasClassifiers())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genPackage.getQualifiedFactoryClassName() });
      monitor.subTask(message);
      generateJava
      (genModel.getModelDirectory(),
       genPackage.getReflectionClassPackageName(),
       genPackage.getFactoryClassName(),
       getJETEmitter(getJETEmitterDescriptors(), FACTORY_CLASS_ID),
       new Object[] { new Object[] { genPackage, genModel.isSuppressInterfaces() ? Boolean.TRUE : Boolean.FALSE, Boolean.TRUE }},
       createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateXMLProcessorClass(GenPackage genPackage, Monitor monitor)
  {
    if (genPackage.hasClassifiers() && genPackage.getResource().getValue() == GenResourceKind.XML)
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] {  genPackage.getQualifiedXMLProcessorClassName() });
      monitor.subTask(message);
      generateJava
        (genPackage.getGenModel().getModelDirectory(),
         genPackage.getUtilitiesPackageName(),
         genPackage.getXMLProcessorClassName(),
         getJETEmitter(getJETEmitterDescriptors(), XML_PROCESSOR_CLASS_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateValidatorClass(GenPackage genPackage, Monitor monitor)
  {
    if (genPackage.hasClassifiers() && genPackage.hasConstraints())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genPackage.getQualifiedValidatorClassName() });
      monitor.subTask(message);
      generateJava
        (genPackage.getGenModel().getModelDirectory(),
         genPackage.getUtilitiesPackageName(),
         genPackage.getValidatorClassName(),
         getJETEmitter(getJETEmitterDescriptors(), VALIDATOR_CLASS_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateSwitchClass(GenPackage genPackage, Monitor monitor)
  {
    if (genPackage.hasClassifiers() && genPackage.isAdapterFactory() && !genPackage.getGenClasses().isEmpty())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genPackage.getQualifiedSwitchClassName() });
      monitor.subTask(message);
      generateJava
        (genPackage.getGenModel().getModelDirectory(),
         genPackage.getUtilitiesPackageName(),
         genPackage.getSwitchClassName(),
         getJETEmitter(getJETEmitterDescriptors(), SWITCH_CLASS_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateAdapterFactoryClass(GenPackage genPackage, Monitor monitor)
  {
    if (genPackage.hasClassifiers() && genPackage.isAdapterFactory() && !genPackage.getGenClasses().isEmpty())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genPackage.getQualifiedAdapterFactoryClassName() });
      monitor.subTask(message);
      generateJava
        (genPackage.getGenModel().getModelDirectory(),
         genPackage.getUtilitiesPackageName(),
         genPackage.getAdapterFactoryClassName(),
         getJETEmitter(getJETEmitterDescriptors(), ADAPTER_FACTORY_CLASS_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateResourceFactoryClass(GenPackage genPackage, Monitor monitor)
  {
    if (genPackage.getResource() != GenResourceKind.NONE_LITERAL)
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genPackage.getQualifiedResourceFactoryClassName() });
      monitor.subTask(message);
      generateJava
        (genPackage.getGenModel().getModelDirectory(),
         genPackage.getUtilitiesPackageName(),
         genPackage.getResourceFactoryClassName(),
         getJETEmitter(getJETEmitterDescriptors(), RESOURCE_FACTORY_CLASS_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateResourceClass(GenPackage genPackage, Monitor monitor)
  {
    if (genPackage.getResource() != GenResourceKind.NONE_LITERAL)
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genPackage.getQualifiedResourceClassName() });
      monitor.subTask(message);
      generateJava
        (genPackage.getGenModel().getModelDirectory(),
         genPackage.getUtilitiesPackageName(),
         genPackage.getResourceClassName(),
         getJETEmitter(getJETEmitterDescriptors(), RESOURCE_CLASS_ID),
         null,
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
    GenPackage genPackage = (GenPackage)object;
    monitor.beginTask("", 2 + countCreateChildIcons(genPackage));

    message = CodeGenEcorePlugin.INSTANCE.getString
      ("_UI_GeneratingItemProvidersForPackage_message", new Object[] { genPackage.getBasicPackageName() });
    monitor.subTask(message);

    GenModel genModel = genPackage.getGenModel();
    ensureProjectExists
      (genModel.getEditDirectory(), genPackage, EDIT_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

    generateItemProviderAdapterFactory(genPackage, monitor);
    generateCreateChildIcons(genPackage, monitor);

    return Diagnostic.OK_INSTANCE;
  }

  protected void generateItemProviderAdapterFactory(GenPackage genPackage, Monitor monitor)
  {
    if (!genPackage.getGenClasses().isEmpty())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genPackage.getQualifiedItemProviderAdapterFactoryClassName() });
      monitor.subTask(message);
      generateJava
        (genPackage.getGenModel().getEditDirectory(),
         genPackage.getProviderPackageName(),
         genPackage.getItemProviderAdapterFactoryClassName(),
         getJETEmitter(getJETEmitterDescriptors(), ITEM_PROVIDER_ADAPTER_FACTORY_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected int countCreateChildIcons(GenPackage genPackage)
  {
    int result = 0;
    GenModel genModel = genPackage.getGenModel();
    if (genModel.isCreationCommands() && genModel.isCreationIcons() && genPackage.isChildCreationExtenders())
    {
      for (Map.Entry<GenPackage, Map<GenClass, List<GenClass.ChildCreationData>>> packageEntry : genPackage.getExtendedChildCreationData().entrySet())
      {
        for (Map.Entry<GenClass, List<GenClass.ChildCreationData>> classEntry : packageEntry.getValue().entrySet())
        {
          for (GenClass.ChildCreationData childCreationData : classEntry.getValue())
          {
            if (childCreationData.createClassifier instanceof GenClass &&
                  (childCreationData.delegatedFeature == null ||
                     classEntry.getKey().getAllGenFeatures().contains(childCreationData.delegatedFeature)))
            {
              ++result;
            }
          }
        }
      }
    }
    return result;
  }

  protected void generateCreateChildIcons(GenPackage genPackage, Monitor monitor)
  {
    GenModel genModel = genPackage.getGenModel();
    if (genModel.isCreationCommands() && genModel.isCreationIcons() && genPackage.isChildCreationExtenders())
    {
      for (Map.Entry<GenPackage, Map<GenClass, List<GenClass.ChildCreationData>>> packageEntry : genPackage.getExtendedChildCreationData().entrySet())
      {
        for (Map.Entry<GenClass, List<GenClass.ChildCreationData>> classEntry : packageEntry.getValue().entrySet())
        {
          GenClass genClass = classEntry.getKey();
          for (GenClass.ChildCreationData childCreationData : classEntry.getValue())
          {
            if (childCreationData.createClassifier instanceof GenClass &&
                  (childCreationData.delegatedFeature == null ||
                     classEntry.getKey().getAllGenFeatures().contains(childCreationData.delegatedFeature)))
            {
              GenClass childClass = (GenClass)childCreationData.createClassifier;
              GenFeature feature = childCreationData.createFeature;
              message = CodeGenEcorePlugin.INSTANCE.getString
                ("_UI_GeneratingCreateChildIcon_message", new Object[] { classEntry.getKey().getCreateChildIconFileName(feature, childClass) });
              monitor.subTask(message);
              generateGIF
                (genClass.getCreateChildIconFileName(genModel, feature, childClass),
                 getGIFEmitter(getInputPathNames(), CREATE_CHILD_ICON_ID),
                 genClass.getName(),
                 childClass.getName(),
                 false,
                 createMonitor(monitor, 1));
            }
          }
        }
      }
    }
  }

  @Override
  protected Diagnostic generateEditor(Object object, Monitor monitor)
  {
    monitor.beginTask("", 6);

    GenPackage genPackage = (GenPackage)object;
    message = CodeGenEcorePlugin.INSTANCE.getString
      ("_UI_GeneratingEditorForPackage_message", new Object[] { genPackage.getBasicPackageName() });
    monitor.subTask(message);

    GenModel genModel = genPackage.getGenModel();
    ensureProjectExists
      (genModel.getEditorDirectory(), genPackage, EDITOR_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

    generateEditor(genPackage, monitor);
    generateModelWizard(genPackage, monitor);
    generateActionBarContributor(genPackage, monitor);
    generateModelIcon(genPackage, monitor);
    generateModelWizardIcon(genPackage, monitor);

    return Diagnostic.OK_INSTANCE;
  }

  protected void generateEditor(GenPackage genPackage, Monitor monitor)
  {
    if (genPackage.hasConcreteClasses() && genPackage.getGenModel().getRuntimePlatform() != GenRuntimePlatform.GWT)
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genPackage.getQualifiedEditorClassName() });
      monitor.subTask(message);
      generateJava
        (genPackage.getGenModel().getEditorDirectory(),
         genPackage.getPresentationPackageName(),
         genPackage.getEditorClassName(),
         getJETEmitter(getJETEmitterDescriptors(), EDITOR_ID),
         null,
         createMonitor(monitor, 1)); 
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateModelWizard(GenPackage genPackage, Monitor monitor)
  {
    if (genPackage.hasConcreteClasses() && genPackage.isGenerateModelWizard() && genPackage.getGenModel().getRuntimePlatform() != GenRuntimePlatform.GWT)
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genPackage.getQualifiedModelWizardClassName() });
      monitor.subTask(message);
      generateJava
        (genPackage.getGenModel().getEditorDirectory(),
         genPackage.getPresentationPackageName(),
         genPackage.getModelWizardClassName(),
         getJETEmitter(getJETEmitterDescriptors(), MODEL_WIZARD_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateActionBarContributor(GenPackage genPackage, Monitor monitor)
  {
    if (genPackage.hasConcreteClasses() && genPackage.getGenModel().getRuntimePlatform() != GenRuntimePlatform.GWT)
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genPackage.getQualifiedActionBarContributorClassName() });
      monitor.subTask(message);
      generateJava
        (genPackage.getGenModel().getEditorDirectory(),
         genPackage.getPresentationPackageName(),
         genPackage.getActionBarContributorClassName(),
         getJETEmitter(getJETEmitterDescriptors(), ACTION_BAR_CONTRIBUTOR_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateModelIcon(GenPackage genPackage, Monitor monitor)
  {
    if (genPackage.hasConcreteClasses())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingModelIcon_message", new Object[] { genPackage.getModelIconFileName() });
      monitor.subTask(message);
      generateGIF
        (genPackage.getModelIconFileName(),
         getGIFEmitter(getInputPathNames(), MODEL_ICON_ID),
         genPackage.getPrefix(),
         null,
         false,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generateModelWizardIcon(GenPackage genPackage, Monitor monitor)
  {
    if (genPackage.hasConcreteClasses() && genPackage.isGenerateModelWizard())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingModelWizardIcon_message", new Object[] { genPackage.getModelWizardIconFileName() });
      monitor.subTask(message);
      generateGIF
        (genPackage.getModelWizardIconFileName(),
         getGIFEmitter(getInputPathNames(), MODEL_WIZARD_ICON_ID),
         genPackage.getPrefix(),
         null,
         false,
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
    monitor.beginTask("", 3);

    GenPackage genPackage = (GenPackage)object;
    message = CodeGenEcorePlugin.INSTANCE.getString
      ("_UI_GeneratingTestsForPackage_message", new Object[] { genPackage.getBasicPackageName() });
    monitor.subTask(message);

    GenModel genModel = genPackage.getGenModel();
    ensureProjectExists
      (genModel.getTestsDirectory(), genPackage, TESTS_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

    generatePackageTestSuite(genPackage, monitor);
    generatePackageExample(genPackage, monitor);

    return Diagnostic.OK_INSTANCE;
  }

  protected void generatePackageTestSuite(GenPackage genPackage, Monitor monitor)
  {
    if (genPackage.hasClassifiers())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genPackage.getQualifiedTestSuiteClassName() });
      monitor.subTask(message);
      generateJava
        (genPackage.getGenModel().getTestsDirectory(),
         genPackage.getTestsPackageName(),
         genPackage.getTestSuiteClassName(),
         getJETEmitter(getJETEmitterDescriptors(), PACKAGE_TEST_SUITE_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }

  protected void generatePackageExample(GenPackage genPackage, Monitor monitor)
  {
    if (genPackage.hasClassifiers() && genPackage.isGenerateExampleClass())
    {
      message = CodeGenEcorePlugin.INSTANCE.getString
        ("_UI_GeneratingJavaClass_message", new Object[] { genPackage.getQualifiedExampleClassName() });
      monitor.subTask(message);
      generateJava
        (genPackage.getGenModel().getTestsDirectory(),
         genPackage.getTestsPackageName(),
         genPackage.getExampleClassName(),
         getJETEmitter(getJETEmitterDescriptors(), PACKAGE_EXAMPLE_ID),
         null,
         createMonitor(monitor, 1));
    }
    else
    {
      monitor.worked(1);
    }
  }
}
