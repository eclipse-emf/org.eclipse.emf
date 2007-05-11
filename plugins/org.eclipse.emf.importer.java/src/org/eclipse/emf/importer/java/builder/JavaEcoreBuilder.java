/**
 * <copyright> 
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: JavaEcoreBuilder.java,v 1.36 2007/05/11 10:04:26 marcelop Exp $
 */
package org.eclipse.emf.importer.java.builder;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.jar.Manifest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.osgi.util.ManifestElement;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.JAbstractType;
import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JEnum;
import org.eclipse.emf.codegen.merge.java.facade.JEnumConstant;
import org.eclipse.emf.codegen.merge.java.facade.JField;
import org.eclipse.emf.codegen.merge.java.facade.JImport;
import org.eclipse.emf.codegen.merge.java.facade.JMember;
import org.eclipse.emf.codegen.merge.java.facade.JMethod;
import org.eclipse.emf.codegen.merge.java.facade.JNode;
import org.eclipse.emf.codegen.merge.java.facade.JPackage;
import org.eclipse.emf.codegen.merge.java.facade.JType;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.java.JavaImporterPlugin;


public class JavaEcoreBuilder
{
  /**
   * The factory used to create Node.
   */
  protected static FacadeHelper facadeHelper = CodeGenUtil.instantiateFacadeHelper(JMerger.DEFAULT_FACADE_HELPER_CLASS);

  /**
   * The file being generated.
   */
  protected IFile genModelFile;

  /**
   * The GenModel being generated.
   */
  protected GenModel genModel;

  /**
   * The map from a package name to the corresponding package.
   * These are populated from the GenModels of required projects.
   */
  protected Map<String, EPackage> externalPackageNameToEPackageMap = new LinkedHashMap<String, EPackage>();

  /**
   * The map from a package name to the corresponding package.
   * These are populated on demand during traversal as modeled class and enums are discovered.
   */
  protected Map<String, EPackage> packageNameToEPackageMap = new LinkedHashMap<String, EPackage>();

  /**
   * The map from package to the map of ordering constants.
   * These are populated as the package interface is traversed.
   */
  protected Map<EPackage, Map<Object, Integer>> ePackageToOrderingMap = new LinkedHashMap<EPackage, Map<Object, Integer>>();

  /**
   * The map from package to the it's prefix.
   * These are populated as the package interfaces are traversed.
   */
  protected Map<EPackage, String> ePackageToPrefixMap = new LinkedHashMap<EPackage, String>();

  /**
   * The map from a model element to the corresponding JNode node.
   * These are populated during traversal as each model element is created.
   */
  protected Map<EModelElement, JNode> eModelElementToJNodeMap = new LinkedHashMap<EModelElement, JNode>();

  /**
   * The map from a typed element to its type name.
   * These are populated during traversal as each typed element is created.
   * They must be patched after traversal is completed.
   */
  protected Map<ETypedElement, String> eTypedElementToTypeNameMap = new LinkedHashMap<ETypedElement, String>();

  /**
   * The map from a typed element to its datatype's instance type name.
   * These are populated during traversal as each typed element is created.
   * They must be patched after traversal is completed.
   * They are only needed if the instance must be determined bottom up.
   */
  protected Map<ETypedElement, String> eTypedElementToInstanceTypeNameMap = new LinkedHashMap<ETypedElement, String>();

  /**
   * The set of data types that were created without setting the instance class.
   */
  protected Set<EDataType> demandCreatedEDataTypes = new HashSet<EDataType>();

  /**
   * The map from a class to its base class names.
   * These are populated during traversal as each class is created.
   * They must be patched after traversal is completed.
   */
  protected Map<EClass, String[]> eClassToSuperTypeNamesMap = new LinkedHashMap<EClass, String[]>();

  /**
   * The map from an operation to its exception type names.
   * These are populated during traversal as each operation is created.
   * They must be patched after traversal is completed.
   */
  protected Map<EOperation, String[]> eOperationToExceptionTypeNamesMap = new LinkedHashMap<EOperation, String[]>();

  /**
   * The map from a reference to the name it's opposite.
   * These are populated during traversal as each reference with an opposite is created.
   * They must be patched after traversal and patching of typed elements is completed.
   * The opposite found as a feature of the type.
   */
  protected Map<EReference, String> eReferenceToOppositeNameMap = new LinkedHashMap<EReference, String>();

  /**
   * All the external GenModels from all required projects.
   */
  protected Collection<GenModel> externalGenModels = new UniqueEList<GenModel>();

  /**
   * All the used GenPackages.
   */
  protected Collection<GenPackage> usedGenPackages = new ArrayList<GenPackage>();

  protected BasicDiagnostic basicDiagnostic;

  protected boolean foundJava = false;

  /**
   * The old version to against which to reconcile.
   */
  protected GenModel oldGenModelVersion;
  
  /**
   * Creates a builder for the given file.
   */
  public JavaEcoreBuilder(IFile genModelFile)
  {
    this.genModelFile = genModelFile;

    basicDiagnostic = new BasicDiagnostic(
      JavaImporterPlugin.getPlugin().getBundle().getSymbolicName(),
      0,
      CodeGenEcorePlugin.INSTANCE.getString("_UI_ErrorsWereDetectedJava_message"),
      null);
  }

  public JavaEcoreBuilder(IFile genModelFile, GenModel oldGenModelVersion)
  {
    this(genModelFile);
    this.oldGenModelVersion = oldGenModelVersion;
  }

  public JavaEcoreBuilder(IFile genModelFile, GenModel oldGenModelVersion, GenModel genModel)
  {
    this(genModelFile, oldGenModelVersion);
    this.genModel = genModel;
  }

  protected ResourceSet createResourceSet()
  {
    ResourceSet result = new ResourceSetImpl();
    result.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap());
    return result;
  }
    
  protected IPath analyzeProject(IProject project) throws Exception
  {
    // Walk the project looking for .java files to analyze.
    //
    IJavaProject javaProject = JavaCore.create(project);
    IPackageFragmentRoot[] packageFragmentRoots = javaProject.getPackageFragmentRoots();
    Set<IResource> visited = new HashSet<IResource>();
    for (int i = 0; i < packageFragmentRoots.length; ++i)
    {
      if (packageFragmentRoots[i].getKind() == IPackageFragmentRoot.K_SOURCE)
      {
        traverse((IContainer)packageFragmentRoots[i].getUnderlyingResource(), visited);
      }
    }

    // Fix all the typed elements that have names which need resolving.
    //
    for (Map.Entry<ETypedElement, String> entry : new ArrayList<Map.Entry<ETypedElement, String>>(eTypedElementToTypeNameMap.entrySet()))
    {
      ETypedElement eTypedElement = entry.getKey();
      String typeName = entry.getValue();
      EClassifier eClassifier = resolve(eTypedElement, typeName);

      // If we have resolved to an EClass but we have an EAttribute, we can change it to be an EReference.
      //
      if (eClassifier instanceof EClass && eTypedElement instanceof EAttribute)
      {
        EAttribute eAttribute = (EAttribute)eTypedElement;
        EClass container = eAttribute.getEContainingClass();
        EReference eReference = EcoreFactory.eINSTANCE.createEReference();
        eReference.setChangeable(eAttribute.isChangeable());
        eReference.setVolatile(eAttribute.isVolatile());
        eReference.setTransient(eAttribute.isTransient());
        eReference.setLowerBound(eAttribute.getLowerBound());
        eReference.setUpperBound(eAttribute.getUpperBound());
        eReference.setName(eTypedElement.getName());
        eReference.getEAnnotations().addAll(eTypedElement.getEAnnotations());
        container.getEStructuralFeatures().add(container.getEStructuralFeatures().indexOf(eTypedElement), eReference);
        container.getEStructuralFeatures().remove(eTypedElement);
        eTypedElement = eReference;
      }
      else if (eClassifier instanceof EDataType && eTypedElement instanceof EReference)
      {
        EReference eReference = (EReference)eTypedElement;
        EClass container = eReference.getEContainingClass();
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setChangeable(eReference.isChangeable());
        eAttribute.setVolatile(eReference.isVolatile());
        eAttribute.setTransient(eReference.isTransient());
        eAttribute.setLowerBound(eReference.getLowerBound());
        eAttribute.setUpperBound(eReference.getUpperBound());
        eAttribute.setName(eTypedElement.getName());
        eAttribute.getEAnnotations().addAll(eTypedElement.getEAnnotations());
        container.getEStructuralFeatures().remove(eTypedElement);
        eTypedElement = eAttribute;
        eReferenceToOppositeNameMap.remove(eReference);
      }

      String instanceClassName = eTypedElementToInstanceTypeNameMap.get(eTypedElement);
      if (instanceClassName != null && demandCreatedEDataTypes.contains(eClassifier))
      {
        demandCreatedEDataTypes.remove(eClassifier);
        EClassifier resolvedInstanceClassName = resolve(eTypedElement, instanceClassName, false);
        ((EDataType)eClassifier).setInstanceTypeName(resolvedInstanceClassName.getInstanceTypeName());
      }

      if (eClassifier == null)
      {
        error(CodeGenEcorePlugin.INSTANCE.getString("_UI_TheTypeDoesNotResolveCorrectly_message", new Object []{ typeName }));
        eClassifier = EcorePackage.Literals.EOBJECT;
      }

      // Finally set the type if the GenericType is not already set 
      //
      if (!eTypedElement.eIsSet(EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE))
      {
        eTypedElement.setEType(eClassifier);
      }

      used(eClassifier);
    }

    // Fix all the classes that have supers that need resolving.
    //
    for (Map.Entry<EClass, String[]> entry : eClassToSuperTypeNamesMap.entrySet())
    {
      EClass eClass = entry.getKey();
      String[] superTypeNames = entry.getValue();
      if (superTypeNames != null)
      {
        for (int j = 0; j < superTypeNames.length; ++j)
        {
          EClassifier eClassifier = resolve(eClass, superTypeNames[j], false);
          if (eClassifier.getEPackage() == null)
          {
            EPackage ePackage = (EPackage)EcoreUtil.getRootContainer(eClass);
            EClass superEClass = EcoreFactory.eINSTANCE.createEClass();
            superEClass.setInstanceTypeName(eClassifier.getInstanceTypeName());
            superEClass.setName(eClassifier.getName());
            superEClass.setAbstract(true);
            superEClass.setInterface(true);
            ePackage.getEClassifiers().add(superEClass);
            eClassifier = superEClass;
          }

          if (eClassifier instanceof EClass)
          {
            if (eClassifier != EcorePackage.Literals.EOBJECT)
            {
              eClass.getESuperTypes().add((EClass)eClassifier);
              used(eClassifier);
            }
          }
          else
          {
            error(CodeGenEcorePlugin.INSTANCE.getString(
              "_UI_TheSuperTypeDoesNotResolveCorrectly_message",
              new Object []{ superTypeNames[j] }));
          }
        }
      }
    }

    // Fix all the operations that have exceptions that need resolving.
    //
    for (Map.Entry<EOperation, String[]> entry : eOperationToExceptionTypeNamesMap.entrySet())
    {
      EOperation eOperation = entry.getKey();
      String[] exceptionTypeNames = entry.getValue();
      if (exceptionTypeNames != null)
      {
        for (int j = 0; j < exceptionTypeNames.length; ++j)
        {
          String compositeName = exceptionTypeNames[j];
          int index = compositeName.indexOf(":");
          String typeName = index == -1 ? compositeName : compositeName.substring(0, index);
          EClassifier eClassifier = resolve(eOperation, typeName, true);
          if (index != -1 && demandCreatedEDataTypes.contains(eClassifier)) 
          {
            demandCreatedEDataTypes.remove(eClassifier);
            EClassifier resolvedInstanceClassName = resolve(eOperation, compositeName.substring(index + 1), false);
            ((EDataType)eClassifier).setInstanceTypeName(resolvedInstanceClassName.getInstanceTypeName());
          }
          if (eClassifier != null)
          {
            eOperation.getEExceptions().add(eClassifier);
            used(eClassifier);
          }
          else
          {
            error(CodeGenEcorePlugin.INSTANCE.getString(
              "_UI_TheTypeDoesNotResolveCorrectly_message",
              new Object []{ exceptionTypeNames[j] }));
          }
        }
      }
    }

    // Now we need to hook up opposites by finding the named feature in the type.
    //
    for (Map.Entry<EReference, String> entry : eReferenceToOppositeNameMap.entrySet())
    {
      EReference eReference = entry.getKey();
      String oppositeName = entry.getValue();
      EClass eClass = (EClass)eReference.getEType();
      EReference eOpposite = (EReference)eClass.getEStructuralFeature(oppositeName);
      if (eOpposite == null)
      {
        error(CodeGenEcorePlugin.INSTANCE.getString("_UI_TheAttributeIsNotAMemberOf_message", new Object []{
          oppositeName,
          eClass.getName() }));
      }
      else if (eOpposite.getEOpposite() != eReference && eOpposite.getEOpposite() != null)
      {
        error(CodeGenEcorePlugin.INSTANCE.getString("_UI_TheOppositeAlreadyHasOpposite_message", new Object []{
          oppositeName,
          eOpposite.getEOpposite().getName(),
          eOpposite.getEOpposite().getEContainingClass().getName() }));
      }
      else
      {
        eReference.setEOpposite(eOpposite);
        eOpposite.setEOpposite(eReference);

        used(eOpposite);

        // Containers must be transient.
        //
        if (eOpposite.isContainment())
        {
          eReference.setTransient(true);
        }
      }
    }

    // Now we should sort.
    //
    for (Map.Entry<EPackage, Map<Object, Integer>> entry : ePackageToOrderingMap.entrySet())
    {
      EPackage ePackage = entry.getKey();
      Map<Object, Integer> nameToIDMap = entry.getValue();

      sort(ePackage.getEClassifiers(), nameToIDMap);
      for (EClassifier eClassifier : ePackage.getEClassifiers())
      {
        if (eClassifier instanceof EClass)
        {
          EClass eClass = (EClass)eClassifier;
          sort(eClass.getEStructuralFeatures(), nameToIDMap);
        }
      }
    }

    // Find the fragment root so that we can generate to the right location (by default). 
    //
    IPath targetFragmentRoot = project.getFullPath();
    for (int i = 0; i < packageFragmentRoots.length; ++i)
    {
      if (packageFragmentRoots[i].getKind() == IPackageFragmentRoot.K_SOURCE)
      {
        IPath path = packageFragmentRoots[i].getUnderlyingResource().getFullPath();
        if (targetFragmentRoot.isPrefixOf(path))
        {
          targetFragmentRoot = path;
          break;
        }
      }
    }
    
    facadeHelper.reset();
    return targetFragmentRoot;
  }

  public void computeEPackages(Monitor monitor, ModelImporter modelImporter) throws Exception
  {
    IProject project = genModelFile.getProject();
    project.open(BasicMonitor.toIProgressMonitor(monitor));
    
    Collection<IFile> allGenModelFiles = new ArrayList<IFile>();
    Collection<IProject> allReferencedProjects = new ArrayList<IProject>();
    getAllReferencedProjects(allReferencedProjects, project.getDescription().getReferencedProjects());
    getAllReferencedProjects(allReferencedProjects, project.getDescription().getDynamicReferences());
    for (IProject referencedProject : allReferencedProjects)
    {
      getAllGenModelFiles(allGenModelFiles, referencedProject);
    }
    
    ResourceSet resourceSet = modelImporter.createResourceSet();
    for (IFile file : allGenModelFiles)
    {
      Resource resource = resourceSet.getResource(modelImporter.createFileURI(file.getFullPath().toString()), true);
      GenModel genModel = (GenModel)resource.getContents().get(0);
      externalGenModels.add(genModel);
      for (GenPackage genPackage : genModel.getGenPackages())
      {
        determineExternalPackages(genPackage, modelImporter);
      }
    }
    
    // Iterate over all projects to look at the manifests.
    //
    List<String> allReferencedPluginIDs = new UniqueEList<String>();
    allReferencedProjects.add(project);
    for (IProject referencedProject : allReferencedProjects)
    {
      try
      {
        // Determine the required plugins.
        //
        URI manifestURI = URI.createURI(referencedProject.getFullPath() + "/META-INF/MANIFEST.MF");
        Manifest manifest = new Manifest(resourceSet.getURIConverter().createInputStream(manifestURI));
        String requires =  manifest.getMainAttributes().getValue("Require-Bundle");
        if (requires != null)
        {
          ManifestElement[] elements = ManifestElement.parseHeader(Constants.REQUIRE_BUNDLE, requires);
          for (int j = 0; j < elements.length; ++j)
          {
            // Include each required plugin of the starting project and the exported ones of all the required projects.
            //
            ManifestElement element = elements[j];
            if (project == referencedProject || "reexport".equals(element.getDirective("visibility")))
            {
              String pluginID = element.getValue();
              allReferencedPluginIDs.add(pluginID);
            }
          }
        }
      }
      catch (IOException exception)
      {
        // Ignore
      }
    }

    // Iterate over all the plugin IDs to determinethe dependency closure.
    // The list grows as the plugins are visited.
    //
    for (int i = 0; i < allReferencedPluginIDs.size(); ++i)
    {
      // Determine the required plugins.
      //
      String pluginID = allReferencedPluginIDs.get(i);
      Bundle bundle = Platform.getBundle(pluginID);
      if (bundle != null)
      {
        String requires = (String)bundle.getHeaders().get(Constants.REQUIRE_BUNDLE);
        if (requires != null)
        {
          ManifestElement[] elements = ManifestElement.parseHeader(Constants.REQUIRE_BUNDLE, requires);
          for (int j = 0; j < elements.length; ++j)
          {
            // Also add each required plugin for consideration in the loop.
            //
            ManifestElement element = elements[j];
            String value = element.getValue();
            if ("reexport".equals(element.getDirective("visibility")))
            {
              allReferencedPluginIDs.add(value);
            }
          }
        }
      }
    }

    // Determine the inverse map from plugin IDs to their registered GenModel locations.
    //
    Map<String, List<URI>> allPluginsWithGenModels = new HashMap<String, List<URI>>();
    for (Map.Entry<String, URI> entry : EcorePlugin.getEPackageNsURIToGenModelLocationMap().entrySet())
    {
      // If it's a platform plugin URI, include it in the map.
      //
      URI genModelLocation = entry.getValue();
      if (genModelLocation.isPlatformPlugin())
      {
        List<URI> uris = allPluginsWithGenModels.get(genModelLocation.segment(1));
        if (uris == null)
        {
          uris = new UniqueEList<URI>();
        }
        uris.add(genModelLocation);
        allPluginsWithGenModels.put(genModelLocation.segment(1), uris);
      }
    }

    // Keep only the plugins that have GenModels for consideration.
    //
    allReferencedPluginIDs.retainAll(allPluginsWithGenModels.keySet());
    for (String pluginID : allReferencedPluginIDs)
    {
      // Consider each GenModel location URI for each required plugin.
      //
      for (URI uri : allPluginsWithGenModels.get(pluginID))
      {
        // Load the model and if it's not one already considered, e.g., a local version in the workspace, process its GenPackages.
        //
        Resource resource = resourceSet.getResource(uri, true);
        GenModel genModel = (GenModel)resource.getContents().get(0);
        if (externalGenModels.add(genModel))
        {
          for (GenPackage genPackage : genModel.getGenPackages())
          {
            determineExternalPackages(genPackage, modelImporter);
          }
        }
      }
    }

    IPath targetFragmentRoot = analyzeProject(project);  
    modelImporter.setModelPluginDirectory(targetFragmentRoot.toString());    

    if (packageNameToEPackageMap.isEmpty())
    {
      error(JavaImporterPlugin.INSTANCE.getString(foundJava ? "_UI_NoModelElementsInJava_message" : "_UI_NoModelElements_message"));
    }

    for (EPackage ePackage : packageNameToEPackageMap.values())
    {
      modelImporter.getEPackages().add(ePackage);

      ModelImporter.EPackageImportInfo ePackageInfo = modelImporter.getEPackageImportInfo(ePackage);
      ePackageInfo.setPrefix(ePackageToPrefixMap.get(ePackage));
      for (Map.Entry<String, EPackage> entry : packageNameToEPackageMap.entrySet())
      {
        if (entry.getValue() == ePackage)
        {
          String qualifiedPackageName = entry.getKey();
          if (qualifiedPackageName != null)
          {
            int index = qualifiedPackageName.lastIndexOf(".");
            if (index != -1)
            {
              ePackageInfo.setBasePackage(qualifiedPackageName.substring(0, index));
            }
          }
          break;
        }
      }      
    }
  }
  
  public void used(EModelElement modelElement)
  {
    EPackage ePackage = (EPackage)EcoreUtil.getRootContainer(modelElement);
    if (ePackage != EcorePackage.eINSTANCE)
    {
      for (GenModel genModel : externalGenModels)
      {
        GenPackage genPackage = genModel.findGenPackage(ePackage);
        if (genPackage != null)
        {
          if (!getUsedGenPackages().contains(genPackage) && genPackage.eResource() != null)
          {
            getUsedGenPackages().add(genPackage);

            // Compute the closure.
            //
            for (Iterator<EObject> j = ePackage.eAllContents(); j.hasNext();)
            {
              for (Iterator<EObject> k = j.next().eCrossReferences().iterator(); k.hasNext();)
              {
                EObject o = k.next();
                if (o instanceof EModelElement)
                {
                  used((EModelElement)o);
                }
              }
            }
          }
          break;
        }
      }
    }
  }

  public void determineExternalPackages(GenPackage genPackage)
  {
    determineExternalPackages(genPackage, null);
  }
  
  protected void determineExternalPackages(GenPackage genPackage, ModelImporter modelImporter)
  {
    if (modelImporter != null)
    {
      modelImporter.getReferencedGenPackages().add(genPackage);
    }
    EPackage ePackage = genPackage.getEcorePackage();
    externalPackageNameToEPackageMap.put(genPackage.getInterfacePackageName(), ePackage);
    for (GenPackage nestedGenPackage : genPackage.getNestedGenPackages())
    {
      determineExternalPackages(nestedGenPackage);
    }
  }

  /**
   * Walks the projects recursively.
   */
  public void getAllReferencedProjects(Collection<IProject> result, IProject[] projects) throws CoreException
  {
    for (int i = 0; i < projects.length; ++i)
    {
      IProject project = projects[i];
      if (!result.contains(project) && project.exists() && project.isOpen())
      {
        result.add(project);
        getAllReferencedProjects(result, project.getDescription().getReferencedProjects());
        getAllReferencedProjects(result, project.getDescription().getDynamicReferences());
      }
    }
  }

  /**
   * Walks the container recursively.
   */
  public void getAllGenModelFiles(Collection<IFile> result, IContainer container) throws CoreException
  {
    IResource[] contents = container.members();
    for (int i = 0; i < contents.length; ++i)
    {
      IResource resource = contents[i];
      if (resource.getType() == IResource.FILE)
      {
        getAllGenModelFiles(result, (IFile)resource);
      }
      else
      {
        getAllGenModelFiles(result, (IContainer)resource);
      }
    }
  }

  /**
   * Walks the container recursively.
   */
  public void getAllGenModelFiles(Collection<IFile> result, IFile file) throws CoreException
  {
    if (file.getName().endsWith(".genmodel"))
    {
      IProject project = file.getProject();
      IJavaProject javaProject = JavaCore.create(project);
      try
      {
        IPath outputLocation = javaProject.getOutputLocation();
        if (project == project.getWorkspace().getRoot().findMember(javaProject.getOutputLocation())
          || !outputLocation.isPrefixOf(file.getFullPath()))
        {
          result.add(file);
        }
      }
      catch (JavaModelException exception)
      {
        JavaImporterPlugin.INSTANCE.log(exception);
      }
    }
  }

  /**
   * Walks the container recursively.
   */
  public void traverse(IContainer container, Set<IResource> visited) throws CoreException
  {
    IResource[] contents = container.members();
    for (int i = 0; i < contents.length; ++i)
    {
      IResource resource = contents[i];
      if (visited.add(resource))
      {
        if (resource.getType() == IResource.FILE)
        {
          traverse((IFile)resource);
        }
        else
        {
          traverse((IContainer)resource, visited);
        }
      }
    }
  }

  /**
   * Analyzes .java files as compilation units.
   */
  public void traverse(IFile file) throws CoreException
  {
    if ("java".equalsIgnoreCase(file.getProjectRelativePath().getFileExtension()))
    {
      try
      {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(file.getContents(true));
        byte[] input = new byte [bufferedInputStream.available()];
        bufferedInputStream.read(input);
        bufferedInputStream.close();

        //purpose: using charset from 'file' to decode bytes into in-memory 
        //         String object
        //modifer: Wu Zhi Qiang
        //date:    Aug 25, 2004
        //action:  first get the charset from 'file', then use it 
        //         to decode the 'input' bytes object
        String encoding = null;
        try
        {
          encoding = file.getCharset();
        }
        catch (CoreException ce)
        {
          // use no encoding
        }
        String contents = encoding == null ? new String(input) : new String(input, encoding);
        JCompilationUnit compilationUnit = facadeHelper.createCompilationUnit("NAME", contents);
        analyzeCompilationUnit(compilationUnit);
      }
      catch (IOException exception)
      {
        JavaImporterPlugin.INSTANCE.log(exception);
      }
    }
  }
  
  /**
   * Walks the compilation unit to analyze the type.
   */
  protected void analyzeCompilationUnit(JCompilationUnit compilationUnit)
  {
    foundJava = true;
    for (JAbstractType abstractType : facadeHelper.getChildren(compilationUnit, JAbstractType.class))
    {
      if (abstractType instanceof JEnum)
      {
        analyzeEnum((JEnum)abstractType);
      }
      else
      {
        analyzeType((JType)abstractType);        
      }
      break;
    }
  }
  
  protected void analyzeEnum(JEnum enumeration)
  {
    EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
    eModelElementToJNodeMap.put(eEnum, enumeration);
    eEnum.setName(enumeration.getName());
    getEPackage(enumeration).getEClassifiers().add(eEnum);
    eEnum.getEAnnotations().addAll(extractEAnnotations(getModelAnnotation(enumeration.getComment())));
    EcoreUtil.setDocumentation(eEnum, getModelDocumentation(enumeration.getComment()));

    // Walk the fields.
    //
    for (JEnumConstant enumConstant : facadeHelper.getChildren(enumeration, JEnumConstant.class))
    {
      analyzeEnumLiteral(eEnum, enumConstant);
    }    
  }
  
  protected EPackage getEPackage(JNode node)
  {
    JPackage jPackage = facadeHelper.getPackage(node);  
    String qualifiedPackageName = jPackage != null ?
      jPackage.getQualifiedName()
      : null;
    
    EPackage ePackage = packageNameToEPackageMap.get(qualifiedPackageName);
    if (ePackage == null)
    {
      // Create the EPackage on demand.
      //
      ePackage = EcoreFactory.eINSTANCE.createEPackage();
      int index = qualifiedPackageName == null ? -1 : qualifiedPackageName.lastIndexOf(".");
      @SuppressWarnings("null")
      String packageName = index == -1 ? qualifiedPackageName : qualifiedPackageName.substring(index + 1);
      ePackage.setName(packageName);
      ePackage.setNsURI("http:///" + (qualifiedPackageName == null ? "null" : qualifiedPackageName.replace('.', '/')) + ".ecore");
      ePackage.setNsPrefix(qualifiedPackageName == null ? "null" : qualifiedPackageName);
      packageNameToEPackageMap.put(qualifiedPackageName, ePackage);

      if (packageName != null)
      {
        String prefix = Character.toUpperCase(packageName.charAt(0)) + packageName.substring(1);
        ePackageToPrefixMap.put(ePackage, prefix);
      }
    }
      
    return ePackage;
  }

  private static final String[] EMPTY_STRING_ARRAY = new String[0];

  /**
   * Walks the type either as an EClass or an ENum to analyze either the methods or the fields.
   */
  protected void analyzeType(JType type)
  {
    // Check whether this has @model annotation contents.
    // If not, it might be a package interface, for backwards compatibility.
    //
    String modelAnnotation = getModelAnnotation(type.getComment());
    boolean isEClassifier = false;
    String kind = null;

    if (modelAnnotation != null)
    {
      kind = getModelAnnotationAttribute(modelAnnotation, "kind");
      isEClassifier = !"package".equals(kind);
    }
    
    if (isEClassifier)
    {
      // Get the package name and see if there's an EPackage for it.
      //
      EPackage ePackage = getEPackage(type);

      // If it's an interface, then it will be treated as an EClass
      // 
      if ((type.getFlags() & Flags.AccInterface) != 0 || "class".equals(kind))
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eModelElementToJNodeMap.put(eClass, type);
        eClass.setName(type.getName());
        ePackage.getEClassifiers().add(eClass);
        eClass.getEAnnotations().addAll(extractEAnnotations(modelAnnotation));
        EcoreUtil.setDocumentation(eClass, getModelDocumentation(type.getComment()));

        String[] superInterfaces = type.getSuperInterfaces();
        String extend = getExtendsAnnotation(type.getComment());
        if (extend != null && superInterfaces != null)
        {
          List<String> superInterfaceList = new ArrayList<String>(Arrays.asList(superInterfaces));
          for (StringTokenizer stringTokenizer = new StringTokenizer(extend, " ,\t\n\r\f"); stringTokenizer.hasMoreTokens();)
          {
            superInterfaceList.remove(stringTokenizer.nextToken());
          }
          superInterfaces = new String [superInterfaceList.size()];
          superInterfaceList.toArray(superInterfaces);
        }
        eClassToSuperTypeNamesMap.put(eClass, superInterfaces);

        String isInterface = getModelAnnotationAttribute(modelAnnotation, "interface");
        eClass.setInterface("true".equals(isInterface));

        String isAbstract = getModelAnnotationAttribute(modelAnnotation, "abstract");
        eClass.setAbstract("true".equals(isAbstract) || isAbstract == null && eClass.isInterface());

        // Walk the methods.
        //
        for (JMethod method : facadeHelper.getChildren(type, JMethod.class))
        {
          analyzeMethod(eClass, method);
        }

        // Additional attributes and references may be defined directly on the interface in order to allow the
        // get accessor method to have suppressed visibility.
        //
        String features = getModelAnnotationAttribute(modelAnnotation, "features");
        if (features != null)
        {
          for (StringTokenizer stringTokenizer = new StringTokenizer(features, " "); stringTokenizer.hasMoreTokens();)
          {
            String feature = stringTokenizer.nextToken();
            if (eClass.getEStructuralFeature(feature) == null)
            {
              analyzeMethod
                (eClass, 
                 getFilteredModelAnnotations(modelAnnotation, feature), 
                 "get" + Character.toUpperCase(feature.charAt(0)) + feature.substring(1), 
                 "java.lang.Object", 
                 EMPTY_STRING_ARRAY, 
                 EMPTY_STRING_ARRAY,
                 EMPTY_STRING_ARRAY);
            }
            else
            {
              warning(CodeGenEcorePlugin.INSTANCE.getString("_UI_DuplicateFeature_message", new Object []{ feature, eClass.getName() }));
            }
          }
        }
      }
      // Otherwise it's treated as an EEnum
      //
      else
      {
        EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
        eModelElementToJNodeMap.put(eEnum, type);
        eEnum.setName(type.getName());
        ePackage.getEClassifiers().add(eEnum);
        eEnum.getEAnnotations().addAll(extractEAnnotations(modelAnnotation));
        EcoreUtil.setDocumentation(eEnum, getModelDocumentation(type.getComment()));

        // Walk the fields.
        //
        for (JField field : facadeHelper.getChildren(type, JField.class))
        {
          analyzeEnumLiteral(eEnum, field);
        }
      }
    }
    // Find Packages and Factories
    else
    {
      JPackage jPackage = facadeHelper.getPackage(type);  
      String qualifiedPackageName = jPackage != null ?
        jPackage.getQualifiedName()
        : null;

      String typeName = type.getName();
      boolean isEPackage = false;
      if (typeName.endsWith("Package") && typeName.length() > 7)
      {
        String packagePrefix = typeName.substring(0, typeName.length() - 7);

        // It's definitely a package if it was declared as such.
        //
        if ("package".equals(kind))
        {
          isEPackage = true;
        }

        int index = qualifiedPackageName == null ? -1 : qualifiedPackageName.lastIndexOf(".");
        @SuppressWarnings("null")
        String name = index == -1 ? qualifiedPackageName : qualifiedPackageName.substring(index + 1);
        String nsURI = "http:///" + (qualifiedPackageName == null ? "null" : qualifiedPackageName.replace('.', '/')) + ".ecore";
        String nsPrefix = qualifiedPackageName == null ? "null" : qualifiedPackageName;

        List<EClass> eClasses = new ArrayList<EClass>();
        List<EDataType> eDataTypes = new ArrayList<EDataType>();
        Map<Object, Integer> ordering = new HashMap<Object, Integer>();

        // It's definitely a package if expected constants eNAME, eNS_PREFIX, or eNS_URI appear.
        //
        for (JNode node : type.getChildren())
        {
          if (node instanceof JField)
          {
            JField field = (JField)node;
            String fieldName = field.getName();
            String fieldType = field.getType();
            if ("eNAME".equals(fieldName))
            {
              isEPackage = true;
              name = field.getInitializer();
              name = name.substring(1, name.length() - 1);
            }
            else if ("eNS_URI".equals(fieldName))
            {
              isEPackage = true;
              nsURI = field.getInitializer();
              nsURI = nsURI.substring(1, nsURI.length() - 1);
            }
            else if ("eNS_PREFIX".equals(fieldName))
            {
              isEPackage = true;
              nsPrefix = field.getInitializer();
              nsPrefix = nsPrefix.substring(1, nsPrefix.length() - 1);
            }
            else if ("int".equals(fieldType) && !fieldName.endsWith("FEATURE_COUNT"))
            {
              try
              {
                String initializer = field.getInitializer();
                int plusIndex = initializer.lastIndexOf("+");
                if (plusIndex != -1)
                {
                  initializer = initializer.substring(plusIndex + 1);
                }
                initializer = initializer.trim();
                int value = Integer.parseInt(initializer);
                ordering.put(fieldName, value);
              }
              catch (NumberFormatException exception)
              {
                // This will catch inherited features, or additional things we don't want to worry about.
              }
            }
          }
          else if (node instanceof JMethod)
          {
            JMethod method = (JMethod)node;
            String methodAnnotation = getModelAnnotation(method.getComment());
            if (methodAnnotation != null)
            {
              String returnType = method.getReturnType();
              if (returnType != null)
              {
                if (returnType.endsWith("EDataType"))
                {
                  EDataType eDataType = EcoreFactory.eINSTANCE.createEDataType();
                  eDataType.setInstanceTypeName(getModelAnnotationAttribute(methodAnnotation, "instanceClass"));
                  eDataType.setName(method.getName().substring(3));
                  String isSerializable = getModelAnnotationAttribute(methodAnnotation, "serializable");
                  if ("false".equals(isSerializable))
                  {
                    eDataType.setSerializable(false);
                  }
                  eDataTypes.add(eDataType);
                  eDataType.getEAnnotations().addAll(extractEAnnotations(methodAnnotation));
                  EcoreUtil.setDocumentation(eDataType, getModelDocumentation(method.getComment()));
                }
                else if (returnType.endsWith("EClass"))
                {
                  EClass eClass = EcoreFactory.eINSTANCE.createEClass();
                  String instanceClass = getModelAnnotationAttribute(methodAnnotation, "instanceClass");
                  if (instanceClass != null)
                  {
                    eClass.setInterface(true);
                    eClass.setAbstract(true);
                    eClass.setInstanceTypeName(instanceClass);
                    eClass.setName(method.getName().substring(3));
                    eClasses.add(eClass);
                  }
                  else
                  {
                    eClass.setInstanceTypeName("java.util.Map$Entry");
                    eClass.setName(method.getName().substring(3));
                    eClasses.add(eClass);

                    String features = getModelAnnotationAttribute(methodAnnotation, "features");
                    if (features != null)
                    {
                      for (StringTokenizer stringTokenizer = new StringTokenizer(features, " "); stringTokenizer.hasMoreTokens();)
                      {
                        String feature = stringTokenizer.nextToken();
                        analyzeMethod
                          (eClass, 
                           getFilteredModelAnnotations(methodAnnotation, feature), 
                           "get" + Character.toUpperCase(feature.charAt(0)) + feature.substring(1), 
                           "java.lang.Object", 
                           EMPTY_STRING_ARRAY, 
                           EMPTY_STRING_ARRAY,
                           EMPTY_STRING_ARRAY);
                      }
                    }
                    else
                    {
                      analyzeMethod(eClass, getFilteredModelAnnotations(methodAnnotation, "key"), "getKey", "java.lang.Object", EMPTY_STRING_ARRAY, EMPTY_STRING_ARRAY, EMPTY_STRING_ARRAY);

                      analyzeMethod(
                        eClass,
                        getFilteredModelAnnotations(methodAnnotation, "value"),
                        "getValue",
                        "java.lang.Object",
                        EMPTY_STRING_ARRAY,
                        EMPTY_STRING_ARRAY,
                        EMPTY_STRING_ARRAY);
                    }
                  }
                  eClass.getEAnnotations().addAll(extractEAnnotations(methodAnnotation));
                  EcoreUtil.setDocumentation(eClass, getModelDocumentation(method.getComment()));
                }
              }
            }
          }
        }

        if (isEPackage || !eClasses.isEmpty() || !eDataTypes.isEmpty())
        {
          EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
          ePackageToOrderingMap.put(ePackage, ordering);
          eModelElementToJNodeMap.put(ePackage, type);
          ePackage.setNsURI(nsURI);
          ePackage.setNsPrefix(nsPrefix);
          ePackage.setName(name);
          ePackage.getEClassifiers().addAll(eClasses);
          ePackage.getEClassifiers().addAll(eDataTypes);
          if (modelAnnotation != null)
          {
            ePackage.getEAnnotations().addAll(extractEAnnotations(modelAnnotation));
          }
          EcoreUtil.setDocumentation(ePackage, getModelDocumentation(type.getComment()));

          ePackageToPrefixMap.put(ePackage, packagePrefix);

          EPackage existingEPackage = packageNameToEPackageMap.get(qualifiedPackageName);
          if (existingEPackage != null)
          {
            ePackage.getEClassifiers().addAll(existingEPackage.getEClassifiers());
          }

          packageNameToEPackageMap.put(qualifiedPackageName, ePackage);
        }
      }
    }
  }

  /**
   * Creates an EOperation, EAttribute, or EReference as appropriate.
   */
  protected void analyzeMethod(EClass eClass, JMethod method)
  {
    // Check whether this has @model annotation contents.
    //
    String modelAnnotation = getModelAnnotation(method.getComment());
    if (modelAnnotation != null && method.getName() != null)
    {
      String methodName = method.getName();
      String returnType = method.getReturnType();
      String[] parameterNames = method.getParameterNames();
      String[] parameterTypes = method.getParameterTypes();
      String[] exceptionTypes = method.getExceptions();

      ETypedElement eTypedElement = analyzeMethod(eClass, modelAnnotation, methodName, returnType, parameterNames, parameterTypes, exceptionTypes);
      if (eTypedElement != null)
      {
        EcoreUtil.setDocumentation(eTypedElement, getModelDocumentation(method.getComment()));
      }

      eModelElementToJNodeMap.put(eTypedElement, method);
      if (eTypedElement instanceof EOperation)
      {
        EOperation eOperation = (EOperation)eTypedElement;
        for (EParameter eParameter : eOperation.getEParameters())
        {
          eModelElementToJNodeMap.put(eParameter, method);
        }
      }
    }
  }

  protected ETypedElement analyzeMethod(
    EClass eClass,
    String modelAnnotation,
    String methodName,
    String returnType,
    String[] parameterNames,
    String[] parameterTypes,
    String[] exceptionTypes)
  {
    // We will create an EAttribute, EReference, or an EOperation.
    //
    ETypedElement eTypedElement = null;
    String featureName = methodName;
    String parameters = getModelAnnotationAttribute(modelAnnotation, "parameters");
    String kind = getModelAnnotationAttribute(modelAnnotation, "kind");

    // An operation is declared via the kind property or, for backwards compatibility, by specifying parameters
    // (though attribute or reference kind takes precedence).
    //
    boolean declaredEOperation = "operation".equals(kind) ||
      (parameters != null && !"attribute".equals(kind) && !"reference".equals(kind));

    // Check whether there are parameters; the special attribute and reference rules only apply for the case of no arguments.
    //
    if (parameterNames.length == 0 && !declaredEOperation && methodName.startsWith("get") && methodName.length() > 3 &&
        Character.isUpperCase(methodName.charAt(3)) && !"boolean".equals(returnType) && !"void".equals(returnType))
    {
      // The feature name is extracted lower cased.
      //
      featureName = CodeGenUtil.uncapName(methodName.substring(3));
    }
    else if (parameterNames.length == 0 && !declaredEOperation && methodName.startsWith("is") && methodName.length() > 2 &&
             Character.isUpperCase(methodName.charAt(2)) && "boolean".equals(returnType))
    {
      // The name is extracted and lower cased.
      //
      featureName = CodeGenUtil.uncapName(methodName.substring(2));
    }
    else
    {
      // The method is not a structural feature, so it's modeled as an operation.
      //
      EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
      eTypedElement = eOperation;
      eClass.getEOperations().add(eOperation);

      handleETypedElement(eOperation, methodName, returnType, modelAnnotation, eClass.getName() + "." + methodName);
      
      if (parameterTypes.length > 0)
      {
        // Each token in parameters will specify a dataType for the corresponding parameter, but can be overridden by a
        // parameter-name-prefixed dataType property.
        //
        StringTokenizer stringTokenizer = new StringTokenizer(parameters == null ? "" : parameters);
        for (int i = 0; i < parameterNames.length; ++i)
        {
          EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
          eOperation.getEParameters().add(eParameter);
          String parameterName = parameterNames[i];
          String parameterType = parameterTypes[i];
          String parameterModelAnnotation = getFilteredModelAnnotations(modelAnnotation, parameterName);

          if (stringTokenizer.hasMoreTokens())
          {
            String dataType = stringTokenizer.nextToken();
            if (!"-".equals(dataType))
            {
              StringBuilder sb = new StringBuilder(parameterModelAnnotation);
              sb.append("dataType=\"");
              sb.append(dataType);
              sb.append("\" ");
              parameterModelAnnotation = sb.toString();
            }
          }

          StringBuilder identifierName = new StringBuilder(eClass.getName());
          identifierName.append('.');
          identifierName.append(methodName);
          identifierName.append('(');
          identifierName.append(parameterName);
          identifierName.append(')');
          handleETypedElement(eParameter, parameterName, parameterType, parameterModelAnnotation, identifierName.toString());
          eParameter.getEAnnotations().addAll(extractEAnnotations(parameterModelAnnotation));
        }
      }

      if (exceptionTypes.length > 0)
      {
        // Each token in exceptions will specify a data type for the corresponding exception.
        //
        String exceptions = getModelAnnotationAttribute(modelAnnotation, "exceptions");
        StringTokenizer stringTokenizer = new StringTokenizer(exceptions == null ? "" : exceptions);
        String[] exceptionTypeNames = new String[exceptionTypes.length];
        for (int i = 0; i < exceptionTypes.length; ++i)
        {
          exceptionTypeNames[i] = exceptionTypes[i];
          if (stringTokenizer.hasMoreTokens())
          {
            String dataType = stringTokenizer.nextToken();
            if (!"-".equals(dataType))
            {
              exceptionTypeNames[i] = dataType + ":" + exceptionTypeNames[i];
            }
          }

          eOperationToExceptionTypeNamesMap.put(eOperation, exceptionTypeNames); 
        }
      }
    }

    // If we aren't doing an operation...
    //
    if (eTypedElement == null)
    {
      // We'll create one of these.
      //
      EStructuralFeature eStructuralFeature = null;

      // If any of these attributes appear, this must be a reference.
      //
      String opposite = getModelAnnotationAttribute(modelAnnotation, "opposite");
      String containment = getModelAnnotationAttribute(modelAnnotation, "containment");
      String resolveProxies = getModelAnnotationAttribute(modelAnnotation, "resolveProxies");
      String mapType = getModelAnnotationAttribute(modelAnnotation, "mapType");
      String keyType = getModelAnnotationAttribute(modelAnnotation, "keyType");
      String valueType = getModelAnnotationAttribute(modelAnnotation, "valueType");
      
      String dataType = getModelAnnotationAttribute(modelAnnotation, "dataType");
      String modelType = getModelAnnotationAttribute(modelAnnotation, "type");      
      
      if (dataType == null)
      {
        Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(returnType);
        if (diagnostic.getSeverity() == Diagnostic.OK)
        {
          EGenericType genericType = (EGenericType)diagnostic.getData().get(0);          
          switch(genericType.getETypeArguments().size())
          {
            case 2:
            {
              if (!"attribute".equals(kind) && isMapType(returnType))
              {
                if (keyType == null)
                {
                  EGenericType typeArgument = genericType.getETypeArguments().get(0);
                  if (typeArgument.getETypeArguments().isEmpty())
                  {
                    keyType = typeArgument.getEClassifier().getInstanceTypeName();
                    modelAnnotation = modelAnnotation + " keyType=\"" + keyType + "\"";
                  }
                }
  
                if (valueType == null)
                {
                  EGenericType typeArgument = genericType.getETypeArguments().get(1);
                  if (typeArgument.getETypeArguments().isEmpty())
                  {
                    valueType = typeArgument.getEClassifier().getInstanceTypeName();
                    modelAnnotation = modelAnnotation + " valueType=\"" + valueType + "\"";
                  }
                }
              }
              break;
            }
            
            case 1:
            {
              if (modelType == null && isListType(returnType))
              {
                EGenericType typeArgument = genericType.getETypeArguments().get(0);
                if (typeArgument.getETypeArguments().isEmpty())
                {
                  modelType = typeArgument.getEClassifier().getInstanceTypeName();
                  modelAnnotation = modelAnnotation + " type=\"" + modelType + "\"";
                }
              }
              break;
            }
          }
        }
      }
      
      if ("reference".equals(kind) || opposite != null || containment != null || resolveProxies != null || mapType != null || (keyType != null && valueType != null))
      {
        EReference eReference = EcoreFactory.eINSTANCE.createEReference();
        eTypedElement = eStructuralFeature = eReference;
        eClass.getEStructuralFeatures().add(eReference);

        // Set the EReference attributes.
        //
        eReference.setContainment("true".equals(containment) || mapType != null && !separateTypeArgument(returnType)[0].endsWith("Entry") || keyType != null
          && valueType != null);
        eReference.setResolveProxies(eReference.isContainment() ? "true".equals(resolveProxies) : !"false".equals(resolveProxies));
        eReference.setUnsettable("true".equals(getModelAnnotationAttribute(modelAnnotation, "unsettable")));

        // Defer the handling of the opposite.
        //
        if (opposite != null)
        {
          eReferenceToOppositeNameMap.put(eReference, opposite);
        }
      }
      else
      {
        // Assume that it's an atttribute for now.
        // It will/could become a reference if the type resolves to an EClass.
        //
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eTypedElement = eStructuralFeature = eAttribute;
        eClass.getEStructuralFeatures().add(eAttribute);

        // Set the EAttribute attributes.
        //
        eAttribute.setUnsettable("true".equals(getModelAnnotationAttribute(modelAnnotation, "unsettable")));
        eAttribute.setID("true".equals(getModelAnnotationAttribute(modelAnnotation, "id")));
        String defaultValueLiteral = getModelAnnotationAttribute(modelAnnotation, "defaultValue");
        if (defaultValueLiteral == null)
        {
          defaultValueLiteral = getModelAnnotationAttribute(modelAnnotation, "default");
        }
        eStructuralFeature.setDefaultValueLiteral(defaultValueLiteral);
      }

      // Handle the type, multiplicity and other ETypedElement attributes. 
      //
      handleETypedElement(eStructuralFeature, featureName, returnType, modelAnnotation, eClass.getName() + "." + methodName);
      
      // Set the EStructuralFeature attributes.
      //
      eStructuralFeature.setChangeable(!"false".equals(getModelAnnotationAttribute(modelAnnotation, "changeable")));
      eStructuralFeature.setDerived("true".equals(getModelAnnotationAttribute(modelAnnotation, "derived")));
      eStructuralFeature.setVolatile("true".equals(getModelAnnotationAttribute(modelAnnotation, "volatile")));
      eStructuralFeature.setTransient("true".equals(getModelAnnotationAttribute(modelAnnotation, "transient")));

      // Set the visibility annotations for the EstructuralFeature.
      //
      EcoreUtil.setSuppressedVisibility
        (eStructuralFeature, EcoreUtil.GET, "true".equals(getModelAnnotationAttribute(modelAnnotation, "suppressedGetVisibility")));
      EcoreUtil.setSuppressedVisibility
        (eStructuralFeature, EcoreUtil.SET, "true".equals(getModelAnnotationAttribute(modelAnnotation, "suppressedSetVisibility")));
      EcoreUtil.setSuppressedVisibility
        (eStructuralFeature, EcoreUtil.IS_SET, "true".equals(getModelAnnotationAttribute(modelAnnotation, "suppressedIsSetVisibility")));
      EcoreUtil.setSuppressedVisibility
        (eStructuralFeature, EcoreUtil.UNSET, "true".equals(getModelAnnotationAttribute(modelAnnotation, "suppressedUnsetVisibility")));
    }

    if (eTypedElement != null)
    {
      // Process the annotations.
      //
      eTypedElement.getEAnnotations().addAll(extractEAnnotations(modelAnnotation));
    }

    return eTypedElement;
  }

  protected boolean isListType(String type)
  {
    type = separateTypeArgument(type)[0];
    return "EList".equals(type) || "org.eclipse.emf.common.util.EList".equals(type)|| "List".equals(type) || "java.util.List".equals(type);
  }

  protected boolean isMapType(String type)
  {
    type = separateTypeArgument(type)[0];
    return "EMap".equals(type) || "org.eclipse.emf.common.util.EMap".equals(type)|| "Map".equals(type) || "java.util.Map".equals(type);
  }

  protected void handleETypedElement(ETypedElement eTypedElement, String name, String type, String modelAnnotation, String identifierName)
  {
    eTypedElement.setName(name);
    if ("void".equals(type)) return;
    
    String mapType = getModelAnnotationAttribute(modelAnnotation, "mapType");
    String dataType = getModelAnnotationAttribute(modelAnnotation, "dataType");
    String modelType = getModelAnnotationAttribute(modelAnnotation, "type");
    String keyType = getModelAnnotationAttribute(modelAnnotation, "keyType");
    String valueType = getModelAnnotationAttribute(modelAnnotation, "valueType");
    String many = getModelAnnotationAttribute(modelAnnotation, "many");
    
    // For lists, maps, and feature maps, the default is many-valued, which can be overriden by an upper-bound declaration.
    //
    if (isListType(type) && (dataType == null || (modelType != null && !isListType(modelType))))
    {
      eTypedElement.setUpperBound(-1);
      if (modelType == null && !"false".equals(many))
      {
        error(CodeGenEcorePlugin.INSTANCE.getString("_UI_TheTypeMustBeSpecifiedFor_message", new Object [] { identifierName }));
        modelType = "java.lang.Object";
      }
    }
    else if (mapType != null || (keyType != null && valueType != null) || 
              "FeatureMap".equals(type) || "org.eclipse.emf.common.util.FeatureMap".equals(type))
    {
      eTypedElement.setUpperBound(-1);
    }

    if (many != null)
    {
      eTypedElement.setUpperBound("true".equals(many) ? -1 : 1);
    }

    eTypedElement.setLowerBound("true".equals(getModelAnnotationAttribute(modelAnnotation, "required")) ? 1 : 0);

    String lowerBound = getModelAnnotationAttribute(modelAnnotation, "lowerBound");
    if (lowerBound == null)
    {
      lowerBound = getModelAnnotationAttribute(modelAnnotation, "lower");
    }
    if (lowerBound != null)
    {
      try
      {
        eTypedElement.setLowerBound(Integer.parseInt(lowerBound));
      }
      catch (NumberFormatException exception)
      {
        warning(JavaImporterPlugin.INSTANCE.getString("_UI_ValueOfPropertyIsBad_message", new Object [] { lowerBound, "lower", identifierName }));
      }
    }
    String upperBound = getModelAnnotationAttribute(modelAnnotation, "upperBound");
    if (upperBound == null)
    {
      upperBound = getModelAnnotationAttribute(modelAnnotation, "upper");
    }
    if (upperBound != null)
    {
      try
      {
        eTypedElement.setUpperBound(Integer.parseInt(upperBound));
      }
      catch (NumberFormatException exception)
      {
        warning(JavaImporterPlugin.INSTANCE.getString("_UI_ValueOfPropertyIsBad_message", new Object [] { upperBound, "upper", identifierName }));
      }
    }

    // The type can be augmented by specifying the it explicitly in the annotation.
    // This mostly makes sense only for many-valued typed elements, where the Java
    // type is a list and the item type needs to be specified.
    //
    if (modelType != null)
    {
      type = modelType;
    }

    if (mapType != null)
    {
      if (keyType != null && valueType != null)
      {
        type = mapType + "@" + keyType + "/" + valueType;
      }
      else
      {
        type = mapType;
      }
    }
    else if (dataType != null)
    {
      eTypedElementToInstanceTypeNameMap.put(eTypedElement, type);
      type = dataType;
    }
    else if (keyType != null && valueType != null)
    {
      // Special paired return type.
      //
      type = keyType + "/" + valueType;
    }
    eTypedElementToTypeNameMap.put(eTypedElement, type);

    eTypedElement.setUnique(!"false".equals(getModelAnnotationAttribute(modelAnnotation, "unique")));
    eTypedElement.setOrdered(!"false".equals(getModelAnnotationAttribute(modelAnnotation, "ordered")));
  }
  
  /**
   * Separates the type argument from the type.  The first position of the
   * returned array is always the raw type and the second is either the type argument
   * without the outmost '&lt;' and '&gt;' or <code>null</null>.
   * @param type
   * @return a String array with length == 2
   */
  protected String[] separateTypeArgument(String type)
  {
    String typeArgument = null;
    int ltIndex = type.indexOf('<');
    if (ltIndex > 0)
    {
      int gtIndex = type.lastIndexOf('>');
      if (gtIndex > ltIndex+1)
      {
        typeArgument = type.substring(ltIndex+1, gtIndex).trim();
        type = type.substring(0, ltIndex).trim();
      }
    }
    return new String[]{type, typeArgument};
  }

  protected EStructuralFeature createFeature(EClass eClass, String name, EClassifier eType)
  {
    if (eType instanceof EClass)
    {
      EReference eReference = EcoreFactory.eINSTANCE.createEReference();
      eReference.setName(name);
      eReference.setEType(eType);
      eClass.getEStructuralFeatures().add(eReference);
      return eReference;
    }
    else
    {
      EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      eAttribute.setName(name);
      eAttribute.setEType(eType);
      eClass.getEStructuralFeatures().add(eAttribute);
      return eAttribute;
    }
  }

  /**
   * Creates EEnumLiteral as appropriate.
   */
  protected void analyzeEnumLiteral(EEnum eEnum, JMember member)
  {
    String modelAnnotation = getModelAnnotation(member.getComment());
    if (modelAnnotation != null)
    {
      String fieldName = member.getName();

      EEnumLiteral eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
      eModelElementToJNodeMap.put(eEnumLiteral, member);
      eEnumLiteral.getEAnnotations().addAll(extractEAnnotations(modelAnnotation));
      EcoreUtil.setDocumentation(eEnumLiteral, getModelDocumentation(member.getComment()));
      
      // Allow the value to be defined by an annotation or by the field's initializer
      //      
      String value = getModelAnnotationAttribute(modelAnnotation, "value");
      if (value == null)
      {
        if (member instanceof JField)
        {
          value = ((JField)member).getInitializer().trim();
        }
      }
      
      if (value != null)
      {
        try
        {
          int intValue = Integer.parseInt(value);
          eEnumLiteral.setValue(intValue);
        }
        catch (NumberFormatException exception)
        {
          warning
            (JavaImporterPlugin.INSTANCE.getString
                ("_UI_InvalidLiteralValueForField", 
                 new Object [] { value, eEnum.getName() + "." + fieldName }));
          eEnumLiteral.setValue(eEnum.getELiterals().size());
        }        
      }
      else
      {
        eEnumLiteral.setValue(eEnum.getELiterals().size());
      }
      
      // Allow a mixed case version of the name to be provided.
      //
      String name = getModelAnnotationAttribute(modelAnnotation, "name");

      // But, if name doesn't expand into field name, ignore it.
      //
      if (name == null || !CodeGenUtil.format(name, '_', null, false, true).toUpperCase().equals(fieldName))
      {
          if (name != null)
          {
            warning
              (JavaImporterPlugin.INSTANCE.getString
                ("_UI_InvalidLiteralNameForField", new Object[] { name, eEnum.getName() + "." + fieldName }));
          }
          name = fieldName;
      }
      eEnumLiteral.setName(name);

      // Allow a distinct literal value to be provided, too.
      //
      String literal = getModelAnnotationAttribute(modelAnnotation, "literal");
      if (literal != null)
      {
        eEnumLiteral.setLiteral(literal);
      }

      eEnum.getELiterals().add(eEnumLiteral);
    }
  }

  /**
   * The pattern for extracting the model documentation.
   */
  protected static Pattern modelDocExpression = Pattern.compile(
    "<!--\\s*begin-model-doc\\s*-->[ \\f\\n\\r\\t]*\\*\\s?(.*?)<!--\\s*end-model-doc\\s*-->",
    Pattern.MULTILINE | Pattern.DOTALL);

  /**
   * Returns the model documentation, or null.
   */
  protected String getModelDocumentation(String comment)
  {
    if (comment != null)
    {
      Matcher matcher = modelDocExpression.matcher(comment);
      if (matcher.find())
      {
        return comment.substring(matcher.start(1), matcher.end(1)).replaceAll("[\\n\\r]*\\s*\\*[\\s]?", "\n").replaceAll("\\s*$", "");
      }
    }

    return null;
  }

  /**
   * The pattern for extracting the @model annotations.
   */
  protected static Pattern modelAnnotationExpression = Pattern.compile(
    "@[ \\f\\n\\r\\t*]*model[ \\f\\n\\r\\t*]*((\\w*\\s*=\\s*(['\"])(?>\\\\.|.)*?\\3[ \\f\\n\\r\\t*]*)*)",
    Pattern.MULTILINE);

  /**
   * Returns the @model annotation contents, or null.
   */
  protected String getModelAnnotation(String comment)
  {
    if (comment != null)
    {
      Matcher matcher = modelAnnotationExpression.matcher(comment);
      if (matcher.find())
      {
        return comment.substring(matcher.start(1), matcher.end(1));
      }
    }

    return null;
  }

  /**
   * The pattern for extracting the @extends annotations.
   */
  protected static Pattern extendsAnnotationExpression = Pattern.compile("@\\s*extends\\s*(([.\\w]*\\s*,*\\s*)+)", Pattern.MULTILINE);

  /**
   * The pattern for extracting the @implements annotations.
   */
  protected static Pattern implementsAnnotationExpression = Pattern.compile("@\\s*implements\\s*(([.\\w]*\\s*,*\\s*)+)", Pattern.MULTILINE);

  /**
   * Returns the @extends/@implements annotation contents, or null.
   */
  protected String getExtendsAnnotation(String comment)
  {
    if (comment != null)
    {
      StringBuilder result = new StringBuilder();
      Matcher extendsMatcher = extendsAnnotationExpression.matcher(comment);
      while (extendsMatcher.find())
      {
        result.append(comment.substring(extendsMatcher.start(1), extendsMatcher.end(1)));
        result.append(' ');
      }

      Matcher implementsMatcher = implementsAnnotationExpression.matcher(comment);
      while (implementsMatcher.find())
      {
        result.append(comment.substring(implementsMatcher.start(1), implementsMatcher.end(1)));
        result.append(' ');
      }

      return result.length() == 0 ? null : result.toString();
    }

    return null;
  }

  /**
   * Returns the unquoted value of attribute-name="value" or of attribute-name='value', or null.
   */
  protected String getModelAnnotationAttribute(String modelAnnotation, String attributeName)
  {
    Pattern modelAnnotationAttributeExpressionDoubleQuote = Pattern.compile(
      "\\b" + attributeName + "\\s*=\\s*([\"'])((?>\\\\.|.)*?)\\1",
      Pattern.MULTILINE);
    Matcher matcher = modelAnnotationAttributeExpressionDoubleQuote.matcher(modelAnnotation);
    if (matcher.find())
    {
      return modelAnnotation.substring(matcher.start(2), matcher.end(2));
    }
    else
    {
      return null;
    }
  }

  /**
   * Returns the space separated concatenation of the unquoted value of each attribute-name="value" or attribute-name='value' repeat, or null.
   */
  protected String getModelAnnotationAttributes(String modelAnnotation, String attributeName)
  {
    StringBuilder result = null;
    Pattern modelAnnotationAttributeExpressionDoubleQuote = Pattern.compile(
      "\\b" + attributeName + "\\s*=\\s*([\"'])((?>\\\\.|.)*?)\\1",
      Pattern.MULTILINE);
    for (Matcher matcher = modelAnnotationAttributeExpressionDoubleQuote.matcher(modelAnnotation); matcher.find();)
    {
      if (result == null)
      {
        result = new StringBuilder();
      }
      else
      {
        result.append(' ');
      }
      result.append(modelAnnotation.subSequence(matcher.start(2), matcher.end(2)));
    }

    return result == null ? null : result.toString();
  }

  protected static Pattern eAnnotationExpression = Pattern.compile("\\G\\s*((?>\\\\.|\\S)+)((?:\\s+(?>\\\\.|\\S)+\\s*+=\\s*(['\"])((?>\\\\.|.)*?)\\3)*)");

  protected static Pattern eAnnotationDetailExpression = Pattern.compile("\\s+((?>\\\\.|\\S)+)\\s*+=\\s*((['\"])((?>\\\\.|.)*?)\\3)");

  protected List<EAnnotation> extractEAnnotations(String modelAnnotation)
  {
    List<EAnnotation> result = Collections.emptyList();
    String annotations = getModelAnnotationAttributes(modelAnnotation, "annotation");
    if (annotations != null)
    {
      for (Matcher matcher = eAnnotationExpression.matcher(annotations); matcher.find();)
      {
        if (result == Collections.EMPTY_LIST)
        {
          result = new ArrayList<EAnnotation>();
        }
        EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
        result.add(eAnnotation);
        eAnnotation.setSource(parseString(matcher.group(1)));
        EMap<String, String> details = eAnnotation.getDetails();
        for (Matcher detailMatcher = eAnnotationDetailExpression.matcher(matcher.group(2)); detailMatcher.find();)
        {
          details.put(parseString(detailMatcher.group(1)), parseString(detailMatcher.group(4)));
        }
      }
    }

    String extendedMetaDataAnnotations = getModelAnnotationAttributes(modelAnnotation, "extendedMetaData");
    if (extendedMetaDataAnnotations != null)
    {
      if (result == Collections.EMPTY_LIST)
      {
        result = new ArrayList<EAnnotation>();
      }
      EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
      result.add(eAnnotation);
      eAnnotation.setSource(ExtendedMetaData.ANNOTATION_URI);
      EMap<String, String> details = eAnnotation.getDetails();
      for (Matcher detailMatcher = eAnnotationDetailExpression.matcher(" " + extendedMetaDataAnnotations); detailMatcher.find();)
      {
        details.put(parseString(detailMatcher.group(1)), parseString(detailMatcher.group(4)));
      }
    }

    return result;
  }

  private static String parseString(String stringLiteralBody)
  {
    return CodeGenUtil.parseString(stringLiteralBody);
  }

  /**
   * Returns the filtered matches xyzAttribute-name="value" or of xyxAttribute-name='value', or null.
   */
  protected String getFilteredModelAnnotations(String modelAnnotation, String filter)
  {
    StringBuilder result = new StringBuilder();
    Pattern modelAnnotationAttributeExpressionDoubleQuote = Pattern.compile("\\b" + filter
      + "([A-Z]\\w*\\s*=\\s*([\"'])((?>\\\\.|.)*?)\\2)", Pattern.MULTILINE);
    int start = 0;
    int end = modelAnnotation.length();
    Matcher matcher;
    while ((matcher = modelAnnotationAttributeExpressionDoubleQuote.matcher(modelAnnotation.subSequence(start, end))).find())
    {
      result.append(modelAnnotation.substring(start + matcher.start(1), start + matcher.start(1) + 1).toLowerCase());
      result.append(modelAnnotation.substring(start + matcher.start(1) + 1, start + matcher.end(1)));
      result.append(' ');
      start += matcher.end(0);
    }
    return result.toString();
  }

  protected EClassifier resolve(EModelElement eModelElement, String typeName)
  {
    return resolve(eModelElement, typeName, true);
  }

  protected EClassifier resolve(EModelElement eModelElement, String typeName, boolean recordDemandCreatedEDataType)
  {
    EPackage ePackage = (EPackage)EcoreUtil.getRootContainer(eModelElement);

    // We want to resolve to this.
    //
    EClassifier eClassifier = null;

    // Is this is a map entry name consisting of /-separated pair of type names.
    // It may start with the name of the map entry followed by @.
    //
    int indexOfSlash = typeName.indexOf("/");
    if (indexOfSlash != -1)
    {
      String mapType = null;
      String keyType = typeName.substring(0, indexOfSlash);
      int indexOfAt = keyType.indexOf("@");
      if (indexOfAt != -1)
      {
        mapType = keyType.substring(0, indexOfAt);
        keyType = keyType.substring(indexOfAt + 1);
      }

      // Resolve using the container so that EDataTypes will be created, if necessary, 
      // which won't happen for EReferences, which typically must resolve to an EClass, but that's the the case here.
      //
      EModelElement container = (EModelElement)eModelElement.eContainer();

      EClassifier keyEClassifier = resolve(container, keyType);

      String valueType = typeName.substring(indexOfSlash + 1);
      EClassifier valueEClassifier = resolve(container, valueType);

      if (mapType == null)
      {
        eClassifier = resolveMapEntry(ePackage, keyEClassifier, valueEClassifier);
        if (eClassifier == null)
        {
          eClassifier = resolveMapEntry(EcorePackage.eINSTANCE, keyEClassifier, valueEClassifier);
        }
      }
      else
      {
        eClassifier = resolve(eModelElement, mapType, false);
      }

      if (eClassifier == null)
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setInstanceTypeName("java.util.Map$Entry");

        String baseName = mapType != null ? mapType : keyEClassifier.getName() + "To" + valueEClassifier.getName() + "MapEntry";
        String name = baseName;
        for (int j = 1; ePackage.getEClassifier(name) != null; ++j)
        {
          name = baseName + "_" + j;
        }
        eClass.setName(name);
        createFeature(eClass, "key", keyEClassifier);
        createFeature(eClass, "value", valueEClassifier);

        ePackage.getEClassifiers().add(eClass);

        eClassifier = eClass;
      }

      return eClassifier;
    }

    // Check if the name is qualified
    //
    String[] ret = separateTypeArgument(typeName);
    String baseName = ret[0];
    String typeArgument = ret[1] == null ? "" : "<" + ret[1] + ">";
    String packageName = "";
    int index = baseName.lastIndexOf(".");
    if (index == -1)
    {
      // Look through the imports of the containing compilation unit.
      //
      JCompilationUnit compilationUnit = facadeHelper.getCompilationUnit(eModelElementToJNodeMap.get(eModelElement));
      if (compilationUnit != null)
      {
        // Find an explicit import or the first wildcard import.
        //
        boolean firstWildcard = true;
        for (JImport jImport : facadeHelper.getChildren(compilationUnit, JImport.class))
        {
          String importName = jImport.getName();
          if (importName.endsWith("." + baseName))
          {
            int importIndex = importName.lastIndexOf(".");
            packageName = importName.substring(0, importIndex);
            typeName = packageName + "." + baseName + typeArgument;
            break;
          }
          else if (firstWildcard && importName.endsWith(".*"))
          {
            int importIndex = importName.lastIndexOf(".");
            packageName = importName.substring(0, importIndex);
            typeName = packageName + "." + baseName + typeArgument;
            firstWildcard = false;
          }
        }

        // Find the modeled package for the import and look up the name there.
        //
        EPackage otherEPackage = packageNameToEPackageMap.get(packageName);
        if (otherEPackage == null)
        {
          otherEPackage = externalPackageNameToEPackageMap.get(packageName);
        }
        if (otherEPackage != null)
        {
          eClassifier = otherEPackage.getEClassifier(baseName);
        }
      }
      
      // If we can't find it, try the simple name in the package...
      //
      if (eClassifier == null && "".equals(packageName))
      {
        eClassifier = ePackage.getEClassifier(baseName);
      }
    }
    else
    {
      // Find the modeled package for the name and look up the name there.
      //
      packageName = baseName.substring(0, index);
      baseName = baseName.substring(index + 1);
      EPackage otherEPackage = packageNameToEPackageMap.get(packageName);
      if (otherEPackage == null)
      {
        otherEPackage = externalPackageNameToEPackageMap.get(packageName);
        if (otherEPackage == null)
        {
          if ("org.eclipse.emf.ecore".equals(packageName))
          {
            otherEPackage = EcorePackage.eINSTANCE;
          }
          else if ("org.eclipse.emf.ecore.xml.type".equals(packageName))
          {
            otherEPackage = XMLTypePackage.eINSTANCE;
          }
          else if ("org.eclipse.emf.ecore.xml.namespace".equals(packageName))
          {
            otherEPackage = XMLNamespacePackage.eINSTANCE;
          }
        }
      }
      if (otherEPackage != null)
      {
        eClassifier = otherEPackage.getEClassifier(baseName);
      }
    }

    // If we still don't have one, we'll have to settle for an EDataType or EClass with an instance class name.
    //
    if (eClassifier == null)
    {
      // See if we already have the EDataType.
      //
      String modifiedName = typeName.replace('$', '.');
      for (EClassifier ePackageClassifier : ePackage.getEClassifiers())
      {
        String name = ePackageClassifier.getInstanceTypeName();
        if (name != null && name.replace('$', '.').equals(modifiedName))
        {
          eClassifier = ePackageClassifier;
          break;
        }
      }
    }

    if (EcorePackage.Literals.EOBJECT.getInstanceTypeName().equals(typeName))
    {
      eClassifier = EcorePackage.Literals.EOBJECT;
    }

    // Just to be helpful, we'll recognize a type of org.eclipse.emf.ecore.util.FeatureMap and convert it to EFeatureMapEntry.
    // This way a dataType need not be specified. But, we won't do this if recordDemandCreateEDataType is false, so we don't
    // change the instanceClass of a new EDataType that's implicitly being defined for FeatureMap.
    //
    if (eClassifier == null && recordDemandCreatedEDataType
      && EcorePackage.Literals.EFEATURE_MAP.getInstanceTypeName().equals(typeName))
    {
      eClassifier = EcorePackage.Literals.EFEATURE_MAP_ENTRY;
    }

    // If we don't have one yet, maybe it's one of the special types...
    //
    if (eClassifier == null
      && (packageName.length() == 0 || packageName.equals("java.lang") || packageName.equals("java.math") || packageName.equals("java.util")))
    {
      for (EClassifier ecoreEClassifier : EcorePackage.eINSTANCE.getEClassifiers())
      {
        if (ecoreEClassifier instanceof EDataType)
        {
          String instanceTypeName = ecoreEClassifier.getInstanceTypeName();
          if (instanceTypeName.equals(typeName) || instanceTypeName.equals("java.lang." + typeName))
          {
            eClassifier = ecoreEClassifier;
            break;
          }
          else if (recordDemandCreatedEDataType && 
                   (instanceTypeName.equals(packageName + "." + baseName) || instanceTypeName.equals("java.lang." + baseName)))
          {
            if (typeArgument.length() > 0 && !ecoreEClassifier.getETypeParameters().isEmpty())
            {
              Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(typeName);
              if (diagnostic.getSeverity() == Diagnostic.OK)
              {
                EGenericType genericType = (EGenericType)diagnostic.getData().get(0);
                if (adjustGenericType(ecoreEClassifier, eModelElement, genericType) && eModelElement instanceof ETypedElement)
                {
                  ((ETypedElement)eModelElement).setEGenericType(genericType);                  
                  eClassifier = ecoreEClassifier;
                  break;
                }
              }
            }
          }
        }
      }
    }

    // If we still don't have one, we'll have to settle for an EDataType not an EClass,
    // so create a new EDataType with a nice unique name.
    //
    if (eClassifier == null && !(eModelElement instanceof EReference))
    {
      EDataType eDataType = EcoreFactory.eINSTANCE.createEDataType();

      // If the name isn't qualified, it might be a primitive or from java.lang. Otherwise, assume it's in the current
      // package and use the nsPrefix for the qualified package name.
      //
      boolean primitive = false;
      if (packageName.length() == 0)
      {
        // For arrays, consider the element type.
        //
        int i = baseName.indexOf('[');
        String elementTypeName = i == -1 ? baseName : baseName.substring(0, i);

        if (CodeGenUtil.isJavaPrimitiveType(elementTypeName))
        {
          primitive = true;
        }
        else if (CodeGenUtil.isJavaLangType(elementTypeName))
        {
          packageName = "java.lang";
          typeName = packageName + "." + typeName;
        }
        else
        {
          packageName = ePackage.getNsPrefix();
          typeName = packageName + '.' + typeName;
        }
      }
      eDataType.setInstanceTypeName(typeName);

      // Even primitives should be represented by a data type with a conventional (i.e. capitalized) name.
      //
      String name = baseName;
      if (primitive && name.length() > 0)
      {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
      }

      // Make array names legal.
      //
      while (name.endsWith("[]"))
      {
        name = name.substring(0, name.length() - 2) + "Array";
      }

      // Avoid classifier name collisions.
      //
      for (int j = 1; ePackage.getEClassifier(name) != null; ++j)
      {
        name = baseName + "_" + j;
      }
      eDataType.setName(name);

      if (recordDemandCreatedEDataType)
      {
        demandCreatedEDataTypes.add(eDataType);
        ePackage.getEClassifiers().add(eDataType);
      }
      eClassifier = eDataType;
    }

    return eClassifier;
  }
  
  /**
   * Adjusts the generic type created by {@link #genericTypeBuilder} so that it 
   * can be used in a model.
   * 
   * @param eClassifier, the EClassifier that the adjusted generic type should refer to
   * @param eModelElement, the EModelElement that the adjusted generic type will be added to
   * @param genericType, a generic type created by {@link #genericTypeBuilder}
   * @return whether it was possible to adjust the generic type
   */
  protected boolean adjustGenericType(EClassifier eClassifier, EModelElement eModelElement, EGenericType genericType)
  {
    int size = genericType.getETypeArguments().size();
    if (size == eClassifier.getETypeParameters().size())
    {
      if (size > 0)
      {
        EPackage ePackage = (EPackage)EcoreUtil.getRootContainer(eModelElement);
        EClass tempEClass = EcoreFactory.eINSTANCE.createEClass();
        ePackage.getEClassifiers().add(tempEClass);
        tempEClass.setName("temp_EClass__MP_" + ePackage.getEClassifiers().size());
        int count = 0;
        for (EGenericType eTypeArgument : genericType.getETypeArguments())
        {
          String typeName = EcoreUtil.toJavaInstanceTypeName(eTypeArgument);

          ETypedElement typedElement = analyzeMethod(
            tempEClass, "@model", "getTempMethod" + (count++), typeName, EMPTY_STRING_ARRAY, EMPTY_STRING_ARRAY, EMPTY_STRING_ARRAY);
          EClassifier typeArgumentClassifier = resolve(typedElement, typeName);
          eTypeArgument.setEClassifier(typeArgumentClassifier);
        }
        EcoreUtil.delete(tempEClass);
      }
      genericType.setEClassifier(eClassifier);
      return true;
    }
    return false;
  }
  
  protected EClass resolveMapEntry(EPackage ePackage, EClassifier keyEClassifier, EClassifier valueEClassifier)
  {
    for (EClassifier ePackageClassifier : ePackage.getEClassifiers())
    {
      if (ePackageClassifier instanceof EClass
        && ("java.util.Map.Entry".equals(ePackageClassifier.getInstanceClassName()) || "java.util.Map$Entry".equals(ePackageClassifier.getInstanceClassName())))
      {
        EClass mapEntryInterface = (EClass)ePackageClassifier;
        EStructuralFeature keyFeature = mapEntryInterface.getEStructuralFeature("key");
        if (keyFeature != null && resolveType(keyFeature) == keyEClassifier && !keyFeature.isMany())
        {
          EStructuralFeature valueFeature = mapEntryInterface.getEStructuralFeature("value");
          if (valueFeature != null && resolveType(valueFeature) == valueEClassifier && !valueFeature.isMany())
          {
            return mapEntryInterface;
          }
        }
      }
    }

    return null;
  }

  protected EClassifier resolveType(ETypedElement eTypedElement)
  {
    EClassifier type = eTypedElement.getEType();
    if (type == null)
    {
      String typeName = eTypedElementToTypeNameMap.get(eTypedElement);
      if (typeName != null)
      {
        type = resolve(eTypedElement, typeName);
      }
    }
    return type;
  }

  protected <T extends ENamedElement> void sort(EList<T> namedElements, final Map<Object, Integer> nameToIDMap)
  {
    Collection<T> ordered = new TreeSet<T>(new Comparator<T>()
      {
        @Override
        public boolean equals(Object object)
        {
          return object == this;
        }

        public int compare(T firstObject, T secondObject)
        {
          int firstValue = getOrderingValue(firstObject, nameToIDMap);
          int secondValue = getOrderingValue(secondObject, nameToIDMap);
          return firstValue - secondValue;
        }
      });
    ordered.addAll(namedElements);
    int index = 0;
    for (T eNamedElement : ordered)
    {
      namedElements.move(index++, eNamedElement);
    }
  }

  protected int getOrderingValue(ENamedElement eNamedElement, Map<Object, Integer> nameToIDMap)
  {
    Integer result = nameToIDMap.get(eNamedElement);
    if (result == null)
    {
      if (eNamedElement instanceof EClassifier)
      {
        String prefix = ePackageToPrefixMap.get(eNamedElement.eContainer());
        String name = eNamedElement.getName();
        String id = CodeGenUtil.format(name, '_', prefix, true, true).toUpperCase();
        result = nameToIDMap.get(id);
      }
      else
      {
        String prefix = ePackageToPrefixMap.get(eNamedElement.eContainer().eContainer());
        String eClassName = ((ENamedElement)eNamedElement.eContainer()).getName();
        String eFeatureName = eNamedElement.getName();
        String id = CodeGenUtil.format(eClassName, '_', prefix, true, true).toUpperCase() + "__"
          + CodeGenUtil.format(eFeatureName, '_', prefix, true, false).toUpperCase();
        result = nameToIDMap.get(id);
      }
      if (result != null)
      {
        nameToIDMap.put(eNamedElement, result);
      }
    }

    if (result != null)
    {
      return result.intValue();
    }

    return Integer.MAX_VALUE;
  }

  /**
   * Returns the diagnostic.
   * @return the status.
   */
  public Diagnostic getDiagnostic()
  {
    return basicDiagnostic;
  }

  /**
   * Returns the generator model.
   * @return the generator model.
   */
  public GenModel getGenModel()
  {
    return genModel;
  }
  
  /**
   * Returns the list of used GenPackages.
   * @return the list of used GenPackages.
   */
  public Collection<GenPackage> getUsedGenPackages()
  {
    return usedGenPackages;
  }

  /**
   * Produces another IStatus in the MultiStatus.
   * @param message a description of the error.
   */
  protected void error(String message)
  {
    System.err.println("-->Error: " + message);
    basicDiagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, JavaImporterPlugin.getPlugin().getBundle().getSymbolicName(), 0, message, null));
  }

  /**
   * Produces another IStatus in the MultiStatus, with warning severity.
   * @param message a description of the error.
   */
  protected void warning(String message)
  {
    System.err.println("-->Warning: " + message);
    basicDiagnostic.add(new BasicDiagnostic(Diagnostic.WARNING, JavaImporterPlugin.getPlugin().getBundle().getSymbolicName(), 0, message, null));
  }
}
