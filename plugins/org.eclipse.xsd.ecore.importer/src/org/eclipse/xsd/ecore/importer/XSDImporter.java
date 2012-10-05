/**
 * Copyright (c) 2005-2012 IBM Corporation and others
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd.ecore.importer;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.converter.ConverterPlugin;
import org.eclipse.emf.converter.util.ConverterUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.importer.ModelImporter;

import org.eclipse.xsd.ecore.XSDEcoreBuilder;


public class XSDImporter extends ModelImporter
{
  /**
   * @since 2.9
   */
  protected static final String SORT_ATTRIBUTES_KEY = "sortAttributes";

  /**
   * @since 2.9
   */
  protected static final String CREATE_MAP_KEY = "createMap";

  public static class MapHelper
  {
    public void setNewMapper(XSDEcoreBuilder ecoreBuilder)
    {
      try
      {
        org.eclipse.emf.mapping.xsd2ecore.XSD2EcoreMapper mapper = new org.eclipse.emf.mapping.xsd2ecore.XSD2EcoreMapper();
        ecoreBuilder.setMapper(mapper);
      }
      catch (Exception e)
      {
        XSDImporterPlugin.INSTANCE.log(e);
      }
    }
  }

  protected boolean createEcoreMap;

  protected EObject mappingRoot;

  /**
   * @since 2.9
   */
  protected boolean sortAttributes = true;

  @Override
  public void dispose()
  {
    mappingRoot = null;
    super.dispose();
  }

  @Override
  public String getID()
  {
    return "org.eclipse.xsd.ecore.importer";
  }

  public boolean canCreateEcoreMap()
  {
    return Platform.getBundle("org.eclipse.emf.mapping.xsd2ecore") != null;
  }

  public void setCreateEcoreMap(boolean createEcoreMap)
  {
    this.createEcoreMap = createEcoreMap;
  }

  public boolean createEcoreMap()
  {
    return createEcoreMap && canCreateEcoreMap();
  }

  /**
   * @since 2.9
   */
  public void setSortAttributes(boolean sortAttributes)
  {
    this.sortAttributes = sortAttributes;
  }

  /**
   * @since 2.9
   */
  public boolean sortAttributes()
  {
    return sortAttributes;
  }

  public void setMappingRoot(EObject mappingRoot)
  {
    this.mappingRoot = mappingRoot;
  }

  public EObject getMappingRoot()
  {
    return mappingRoot;
  }

  @Override
  protected Diagnostic doComputeEPackages(Monitor monitor) throws Exception
  {
    BasicDiagnostic basicDiagnostic = null;

    List<URI> locationURIs = getModelLocationURIs();
    if (locationURIs.isEmpty())
    {
      basicDiagnostic = new BasicDiagnostic(
        Diagnostic.ERROR,
        ConverterPlugin.ID,
        ConverterUtil.ACTION_DIALOG_NONE | ConverterUtil.ACTION_MESSAGE_SET_ERROR,
        XSDImporterPlugin.INSTANCE.getString("_UI_SpecifyAValidXMLSchema_message"),
        null);
    }
    else
    {
      monitor.beginTask("", 2);
      monitor.subTask(XSDImporterPlugin.INSTANCE.getString("_UI_Loading_message", new Object []{ locationURIs }));

      setMappingRoot(null);

      XSDEcoreBuilder ecoreBuilder =
        sortAttributes() ?
          new XSDEcoreBuilder() :
          new XSDEcoreBuilder()
          {
            @Override
            protected boolean useSortedAttributes()
            {
              return false;
            }
          };

      if (createEcoreMap())
      {
        new MapHelper().setNewMapper(ecoreBuilder);
      }

      @SuppressWarnings("unchecked")
      List<Object> result = (List<Object>)(List<?>)(Collection<?>)ecoreBuilder.generate(locationURIs);

      Object lastElement = removeNonEPackageFromTheEnd(result);
      if (lastElement instanceof List<?>)
      {
        @SuppressWarnings("unchecked")
        List<List<?>> diagnostics = (List<List<?>>)(List<?>)lastElement;
        if (!diagnostics.isEmpty())
        {
          BasicDiagnostic diagnostic = new BasicDiagnostic(
            ConverterPlugin.ID,
            ConverterUtil.ACTION_MESSAGE_NONE,
            XSDImporterPlugin.INSTANCE.getString("_UI_ErrorsWereDetectedXMLSchema_message"),
            null);

          for (List<?> information : diagnostics)
          {
            diagnostic.add(new BasicDiagnostic(
              "fatal".equals(information.get(0)) || "error".equals(information.get(0)) ? Diagnostic.ERROR : "warning".equals(information.get(0)) ? Diagnostic.WARNING : Diagnostic.INFO,
              XSDImporterPlugin.getPlugin().getBundle().getSymbolicName(),
              0,
              (String)information.get(1),
              null));
          }
          basicDiagnostic = diagnostic;
        }

        lastElement = removeNonEPackageFromTheEnd(result);
      }

      if (lastElement instanceof EObject)
      {
        setMappingRoot((EObject)lastElement);
      }

      @SuppressWarnings("unchecked")
      List<EPackage> ePackages = (List<EPackage>)(List<?>)result;
      getEPackages().addAll(ePackages);
    }

    if (basicDiagnostic == null)
    {
      return Diagnostic.OK_INSTANCE;
    }
    else
    {
      return basicDiagnostic;
    }
  }

  protected Object removeNonEPackageFromTheEnd(List<Object> list)
  {
    int lastIndex = list.size() - 1;
    if (lastIndex >= 0 && !(list.get(lastIndex) instanceof EPackage))
    {
      return list.remove(lastIndex);
    }
    else
    {
      return null;
    }
  }

  @Override
  protected void adjustGenPackageDuringTraverse(GenPackage genPackage)
  {
    genPackage.setResource(GenResourceKind.XML_LITERAL);
  }

  @Override
  protected void adjustGenModel(Monitor monitor)
  {
    super.adjustGenModel(monitor);

    IPath genModelFileFullPath = getGenModelPath();
    URI genModelURI = createFileURI(genModelFileFullPath.toString());

    GenModel genModel = getGenModel();
    EList<String> foreignModel = genModel.getForeignModel();
    for (URI uri : getModelLocationURIs())
    {
      foreignModel.add(makeRelative(uri, genModelURI).toString());
    }

    if (getMappingRoot() != null)
    {
      IPath mappingPath = genModelFileFullPath.removeFileExtension().addFileExtension("xsd2ecore");
      URI mappingModelURI = createFileURI(mappingPath.toString());
      Resource mappingModelResource = getGenModelResourceSet().createResource(mappingModelURI);
      mappingModelResource.getContents().add(getMappingRoot());
    }
  }

  @Override
  public void prepareGenModelAndEPackages(Monitor monitor)
  {
    super.prepareGenModelAndEPackages(monitor);

    GenModel genModel = getGenModel();

    GenAnnotation annotation = genModel.getGenAnnotation(getConverterGenAnnotationSource());
    if (!sortAttributes())
    {
      if (annotation == null)
      {
        annotation = genModel.createGenAnnotation();
        annotation.setSource(getConverterGenAnnotationSource());
      }
      annotation.getDetails().put(SORT_ATTRIBUTES_KEY, "false");
      genModel.getGenAnnotations().add(annotation);
    }
    else if (annotation != null)
    {
      annotation.getDetails().remove(SORT_ATTRIBUTES_KEY);
    }

    if (getMappingRoot() != null)
    {
      if (annotation == null)
      {
        annotation = genModel.createGenAnnotation();
        annotation.setSource(getConverterGenAnnotationSource());
        genModel.getGenAnnotations().add(annotation);
      }
      annotation.getDetails().put(CREATE_MAP_KEY, "true");
    }
    else if (annotation != null)
    {
      annotation.getDetails().remove(CREATE_MAP_KEY);
    }

    if (annotation != null && annotation.getDetails().isEmpty())
    {
      genModel.getGenAnnotations().remove(annotation);
    }
  }

  @Override
  protected List<Resource> computeResourcesToBeSaved()
  {
    List<Resource> resources = super.computeResourcesToBeSaved();
    if (getMappingRoot() != null)
    {
      resources.add(getMappingRoot().eResource());
    }
    return resources;
  }

  @Override
  protected void handleOriginalGenModel() throws DiagnosticException
  {
    GenModel originalGenModel = getOriginalGenModel();
    URI genModelURI = originalGenModel.eResource().getURI();
    StringBuffer text = new StringBuffer();
    for (String value : originalGenModel.getForeignModel())
    {
      if (value.endsWith(".xsd") || value.endsWith(".wsdl"))
      {
        text.append(makeAbsolute(URI.createURI(value), genModelURI).toString());
        text.append(" ");
      }
    }
    GenAnnotation annotation = originalGenModel.getGenAnnotation(getConverterGenAnnotationSource());
    if (annotation != null)
    {
      setSortAttributes(!"false".equals(annotation.getDetails().get(SORT_ATTRIBUTES_KEY)));
      setCreateEcoreMap("true".equals(annotation.getDetails().get(CREATE_MAP_KEY)));
    }
    setModelLocation(text.toString().trim());
  }
}