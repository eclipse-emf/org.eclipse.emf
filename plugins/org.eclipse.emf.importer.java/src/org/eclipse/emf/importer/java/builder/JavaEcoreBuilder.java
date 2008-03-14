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
 * $Id: JavaEcoreBuilder.java,v 1.47 2008/03/14 15:13:56 emerks Exp $
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
import java.util.LinkedHashSet;
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
import org.eclipse.core.runtime.Path;
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
import org.eclipse.emf.ecore.ETypeParameter;
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
   * The factory used to create JNodes.
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
   * The map from a model element to the corresponding JNode.
   * These are populated during traversal as each model element is created.
   */
  protected Map<EModelElement, JNode> eModelElementToJNodeMap = new LinkedHashMap<EModelElement, JNode>();

  /**
   * The features handled during the traversal.
   * These are populated during traversal as each feature is created.
   * They must be patched after traversal is completed to ensure they have resolved to the right type of classifier.
   */
  protected Set<EStructuralFeature> eStructuralFeatures = new LinkedHashSet<EStructuralFeature>();

  /**
   * The attributes that must be treated as attributes because the are explicitly declared to be of that kind.
   */
  protected Set<EAttribute> eAttributes = new LinkedHashSet<EAttribute>();

  /**
   * A map from a generic type to the possibly null generic type that represents its proper Java type.
   */
  protected Map<EGenericType, EGenericType> ecoreEGenericTypeToJavaEGenericTypeMap = new LinkedHashMap<EGenericType, EGenericType>();

  /**
   * The set of classifiers that were demand created.
   */
  protected Set<EClassifier> demandCreatedEClassifiers = new LinkedHashSet<EClassifier>();

  /**
   * The map from a reference to the name of its opposite.
   * These are populated during traversal as each reference with an opposite is created.
   * They must be patched after traversal and patching of typed elements is completed.
   * The opposite found as a feature of the type.
   */
  protected Map<EReference, String> eReferenceToOppositeNameMap = new LinkedHashMap<EReference, String>();

  /**
   * The set of references that have opposites and have been explicitly marked as being transient.
   */
  protected Set<EReference> transientEReferenceWithOpposite = new HashSet<EReference>();

  /**
   * The map from a reference to the names of its keys.
   * These are populated during traversal as each reference with keys is created.
   * They must be patched after traversal and patching of typed elements is completed.
   * The key is found as an attribute of the type.
   */
  protected Map<EReference, List<String>> eReferenceToKeyNamesMap = new LinkedHashMap<EReference, List<String>>();

  /**
   * All the external GenModels from all required projects.
   */
  protected Collection<GenModel> externalGenModels = new UniqueEList<GenModel>();

  /**
   * All the used GenPackages.
   */
  protected Collection<GenPackage> usedGenPackages = new ArrayList<GenPackage>();

  /**
   * A collection of the problems encountered during the processing.
   */
  protected BasicDiagnostic basicDiagnostic;

  /**
   * Set to true when a compilation unit is processed and hence indicates if anything to process has been found.
   */
  protected boolean foundJava;

  /**
   * The old version to against which to reconcile.
   */
  protected GenModel oldGenModelVersion;

  /**
   * The utility used to convert a generate type to a fully qualified Java instance type.
   */
  protected EcoreUtil.EGenericTypeConverter eGenericTypeConverter =
     new EcoreUtil.EGenericTypeConverter()
     {
       @Override
       protected String getInstanceTypeName(EClassifier eClassifier)
       {
         String result = super.getInstanceTypeName(eClassifier);
         if (result == null)
         {
           EPackage ePackage = eClassifier.getEPackage();
           for (Map.Entry<String, EPackage> entry : packageNameToEPackageMap.entrySet())
           {
             if (entry.getValue() == ePackage)
             {
               return entry.getKey() + '.' + eClassifier.getName();
             }
           }
           for (Map.Entry<String, EPackage> entry : externalPackageNameToEPackageMap.entrySet())
           {
             if (entry.getValue() == ePackage)
             {
               return entry.getKey() + '.' + eClassifier.getName();
             }
           }
         }
         return result;
       }
     };

  protected static final String MAP_ENTRY_CLASS_CONTAINER_ANNOTATION_SOURCE = EcorePackage.eNS_URI + "#MapEntryClassContainer";
  
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

    for (Map.Entry<EGenericType, EGenericType> entry : ecoreEGenericTypeToJavaEGenericTypeMap.entrySet())
    {
      EGenericType ecoreEGenericType = entry.getKey();
      EGenericType javaEGenericType = entry.getValue();
      EModelElement eModelElement = null;
      for (EObject eObject = ecoreEGenericType.eContainer(); eObject != null; eObject = eObject.eContainer())
      {
        if (eObject instanceof EModelElement)
        {
          eModelElement = (EModelElement)eObject;
          break;
        }
      }
      RequiredClassifierType requiredClassifierType =
         ecoreEGenericType.eContainer() instanceof EClass || ecoreEGenericType.eContainer() instanceof EReference ?
           RequiredClassifierType.CLASS :
           eAttributes.contains(ecoreEGenericType.eContainer()) ?
             RequiredClassifierType.DATA_TYPE :
             RequiredClassifierType.NONE;
      resolve(eModelElement, ecoreEGenericType, requiredClassifierType);
      if (javaEGenericType != null)
      {
        resolve(eModelElement, javaEGenericType, requiredClassifierType);
      }

      if (javaEGenericType != null)
      {
        resolve(eModelElement, ecoreEGenericType, javaEGenericType);
      }
    }

    for (Map.Entry<EGenericType, EGenericType> entry : ecoreEGenericTypeToJavaEGenericTypeMap.entrySet())
    {
      EGenericType ecoreEGenericType = entry.getKey();
      EGenericType javaEGenericType = entry.getValue();
      EModelElement eModelElement = null;
      for (EObject eObject = ecoreEGenericType.eContainer(); eObject != null; eObject = eObject.eContainer())
      {
        if (eObject instanceof EModelElement)
        {
          eModelElement = (EModelElement)eObject;
          break;
        }
      }

      if (javaEGenericType == null)
      {
        resolve(eModelElement, ecoreEGenericType, javaEGenericType);
      }
      used(ecoreEGenericType);
    }

    for (EStructuralFeature eStructuralFeature : eStructuralFeatures)
    {
      EGenericType eGenericType = eStructuralFeature.getEGenericType();
      EClassifier eClassifier = eGenericType.getERawType();

      // If we have resolved to an EClass but we have an EAttribute, we can change it to be an EReference.
      //
      if (eClassifier instanceof EClass && eStructuralFeature instanceof EAttribute)
      {
        EAttribute eAttribute = (EAttribute)eStructuralFeature;
        EClass container = eAttribute.getEContainingClass();
        EReference eReference = EcoreFactory.eINSTANCE.createEReference();
        eReference.setChangeable(eAttribute.isChangeable());
        eReference.setVolatile(eAttribute.isVolatile());
        eReference.setTransient(eAttribute.isTransient());
        eReference.setDerived(eAttribute.isDerived());
        eReference.setUnsettable(eAttribute.isUnsettable());
        eReference.setLowerBound(eAttribute.getLowerBound());
        eReference.setUpperBound(eAttribute.getUpperBound());
        eReference.setName(eStructuralFeature.getName());
        eReference.setEGenericType(eGenericType);
        eReference.getEAnnotations().addAll(eStructuralFeature.getEAnnotations());
        container.getEStructuralFeatures().add(container.getEStructuralFeatures().indexOf(eStructuralFeature), eReference);
        container.getEStructuralFeatures().remove(eStructuralFeature);
        eStructuralFeature = eReference;
      }
      else if (eClassifier instanceof EDataType && eStructuralFeature instanceof EReference)
      {
        EReference eReference = (EReference)eStructuralFeature;
        EClass container = eReference.getEContainingClass();
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setChangeable(eReference.isChangeable());
        eAttribute.setVolatile(eReference.isVolatile());
        eAttribute.setTransient(eReference.isTransient());
        eAttribute.setDerived(eReference.isDerived());
        eAttribute.setUnsettable(eReference.isUnsettable());
        eAttribute.setLowerBound(eReference.getLowerBound());
        eAttribute.setUpperBound(eReference.getUpperBound());
        eAttribute.setName(eStructuralFeature.getName());
        eAttribute.setEGenericType(eGenericType);
        eAttribute.getEAnnotations().addAll(eStructuralFeature.getEAnnotations());
        container.getEStructuralFeatures().remove(eStructuralFeature);
        eStructuralFeature = eAttribute;
        eReferenceToOppositeNameMap.remove(eReference);
      }

      if (eClassifier.getEPackage() == null)
      {
        error(CodeGenEcorePlugin.INSTANCE.getString("_UI_TheTypeDoesNotResolveCorrectly_message", new Object []{ eClassifier.getInstanceTypeName() }));

        eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericType.setEClassifier(eClassifier instanceof EClass ? EcorePackage.Literals.EOBJECT : EcorePackage.Literals.EJAVA_OBJECT);
      }
    }

    // Now we need to hook up opposites by finding the named feature in the type.
    //
    for (Map.Entry<EReference, String> entry : eReferenceToOppositeNameMap.entrySet())
    {
      EReference eReference = entry.getKey();
      String oppositeName = entry.getValue();
      EClass eClass = (EClass)eReference.getEType();
      // TODO handle class cast exception better.
      EReference eOpposite = (EReference)eClass.getEStructuralFeature(oppositeName);
      if (eOpposite == null)
      {
        error(CodeGenEcorePlugin.INSTANCE.getString("_UI_TheAttributeIsNotAMemberOf_message", new Object []{ oppositeName, eClass.getName() }));
      }
      else if (eOpposite.getEOpposite() != eReference && eOpposite.getEOpposite() != null)
      {
        error
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_TheOppositeAlreadyHasOpposite_message",
              new Object []{ oppositeName, eOpposite.getEOpposite().getName(), eOpposite.getEOpposite().getEContainingClass().getName() }));
      }
      else
      {
        eReference.setEOpposite(eOpposite);
        eOpposite.setEOpposite(eReference);

        used(eOpposite);

        // Containers are transient by default unless explicitly annotated otherwise.
        //
        if (eOpposite.isContainment() && !transientEReferenceWithOpposite.contains(eReference))
        {
          eReference.setTransient(true);
        }
      }
    }

    // Now we need to hook up keys by finding the named feature in the type.
    //
    for (Map.Entry<EReference, List<String>> entry : eReferenceToKeyNamesMap.entrySet())
    {
      EReference eReference = entry.getKey();
      EClass eClass = (EClass)eReference.getEType();
      for (String keyName : entry.getValue())
      {
        EStructuralFeature eKey = eClass.getEStructuralFeature(keyName);
        if (eKey == null)
        {
          error(CodeGenEcorePlugin.INSTANCE.getString("_UI_TheAttributeIsNotAMemberOf_message", new Object []{ keyName, eClass.getName() }));
        }
        else if (!(eKey instanceof EAttribute))
        {
          // TODO Ignore for now.
        }
        else
        {
          eReference.getEKeys().add((EAttribute)eKey);
          used(eKey);
        }
      }
    }

    // Clean up the temporary container annotations for holding map entry classes until they are for sure needed.
    //
    for (EPackage ePackage : packageNameToEPackageMap.values())
    {
      EAnnotation eAnnotation = ePackage.getEAnnotation(MAP_ENTRY_CLASS_CONTAINER_ANNOTATION_SOURCE);
      if (eAnnotation != null)
      { 
        EcoreUtil.remove(eAnnotation);
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
    if (!project.hasNature(JavaCore.NATURE_ID) && oldGenModelVersion.getModelDirectory() != null)
    {
      project = project.getWorkspace().getRoot().getFolder(new Path(oldGenModelVersion.getModelDirectory())).getProject();
    }

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

    // Iterate over all the plugin IDs to determine the dependency closure.
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

  public void used(EGenericType eGenericType)
  {
    if (eGenericType != null)
    {
      EClassifier eClassifier = eGenericType.getEClassifier();
      if (eClassifier != null)
      {
        used(eClassifier);
      }
      used(eGenericType.getEUpperBound());
      used(eGenericType.getELowerBound());
      for (EGenericType eTypeArgument : eGenericType.getETypeArguments())
      {
        used(eTypeArgument);
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
    String modelAnnotation = getModelAnnotation(enumeration.getComment());
    if (modelAnnotation != null)
    {
      EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
      eModelElementToJNodeMap.put(eEnum, enumeration);
      eEnum.setName(enumeration.getName());
      getEPackage(enumeration).getEClassifiers().add(eEnum);
      eEnum.getEAnnotations().addAll(extractEAnnotations(modelAnnotation));
      EcoreUtil.setDocumentation(eEnum, getModelDocumentation(enumeration.getComment()));

      // Walk the fields.
      //
      boolean hasEnumLiteral = false;
      for (JEnumConstant enumConstant : facadeHelper.getChildren(enumeration, JEnumConstant.class))
      {
        hasEnumLiteral |= analyzeEnumLiteral(eEnum, enumConstant);
      }

      if (!hasEnumLiteral)
      {
        for (JField field : facadeHelper.getChildren(enumeration, JField.class))
        {
          hasEnumLiteral |= analyzeEnumLiteral(eEnum, field);
        }
      }
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

        analyzeTypeParameters(eClass.getETypeParameters(), type.getTypeParameters(), modelAnnotation);

        String[] superInterfaces = type.getSuperInterfaces();
        String extend = getExtendsAnnotation(type.getComment());
        if (extend != null)
        {
          List<String> superInterfaceList = new ArrayList<String>(Arrays.asList(superInterfaces));
          for (StringTokenizer stringTokenizer = new StringTokenizer(extend, " ,\t\n\r\f"); stringTokenizer.hasMoreTokens();)
          {
            superInterfaceList.remove(stringTokenizer.nextToken());
          }
          superInterfaces = new String [superInterfaceList.size()];
          superInterfaceList.toArray(superInterfaces);
        }

        // Create a generic super type with an EClass as the classifier for each super interface in the Java representation.
        //
        List<EGenericType> javaEGenericSuperTypes = new ArrayList<EGenericType>();
        for (String superType : superInterfaces)
        {
          Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(superType);
          if (diagnostic.getSeverity() == Diagnostic.OK)
          {
            EGenericType eGenericSuperType = (EGenericType)diagnostic.getData().get(0);
            EClass eSuperClass = EcoreFactory.eINSTANCE.createEClass();
            eSuperClass.setAbstract(true);
            eSuperClass.setInterface(true);
            eSuperClass.setInstanceTypeName(eGenericSuperType.getEClassifier().getInstanceTypeName());
            eGenericSuperType.setEClassifier(eSuperClass);
            javaEGenericSuperTypes.add(eGenericSuperType);
          }
        }

        // Create a generic super type with an EClass as the classifier for each super interface in the @model representation
        //
        List<EGenericType> ecoreEGenericSuperTypes = null;
        String superTypes = getModelAnnotationAttribute(modelAnnotation, "superTypes");
        if (superTypes != null)
        {
          ecoreEGenericSuperTypes = new ArrayList<EGenericType>();
          for (EGenericType ecoreEGenericSuperType : analyzeEGenericTypes(superTypes))
          {
            EClass eSuperClass = EcoreFactory.eINSTANCE.createEClass();
            eSuperClass.setAbstract(true);
            eSuperClass.setInterface(true);
            eSuperClass.setInstanceTypeName(ecoreEGenericSuperType.getEClassifier().getInstanceTypeName());
            ecoreEGenericSuperType.setEClassifier(eSuperClass);
            ecoreEGenericSuperTypes.add(ecoreEGenericSuperType);
          }
        }

        // Filter out explicit EObject from super types, except in the Ecore package itself, or if it appears in the @model superTypes.
        //
        if (!javaEGenericSuperTypes.isEmpty() &&
              javaEGenericSuperTypes.get(0).getERawType().getInstanceTypeName().endsWith("EObject") &&
              (ecoreEGenericSuperTypes == null ||
                ecoreEGenericSuperTypes.size() > 1 && !ecoreEGenericSuperTypes.get(0).getERawType().getInstanceTypeName().endsWith("EObject")) &&
              !EcorePackage.eNS_URI.equals(ePackage.getNsURI()))
        {
           EGenericType superType = resolve(eClass, javaEGenericSuperTypes.get(0).getERawType().getInstanceTypeName(), RequiredClassifierType.CLASS, false);
           EClassifier superClass = superType.getEClassifier();
           if (superClass.getEPackage() != null &&
                 EcorePackage.eNS_URI.equals(superClass.getEPackage().getNsURI()) &&
                 "EObject".equals(superClass.getName()))
           {
             javaEGenericSuperTypes.remove(0);
           }
        }

        // Match them and accumulate the appropriate result.
        //
        match(eClass.getEGenericSuperTypes(), javaEGenericSuperTypes, ecoreEGenericSuperTypes);

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
      String qualifiedPackageName = jPackage != null ? jPackage.getQualifiedName() : null;

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

                  String typeParameterNames = getModelAnnotationAttribute(methodAnnotation, "typeParameters");
                  if (typeParameterNames != null)
                  {
                    for (StringTokenizer stringTokenizer = new StringTokenizer(typeParameterNames, " "); stringTokenizer.hasMoreTokens();)
                    {
                      String typeParameterName = stringTokenizer.nextToken();
                      ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
                      eTypeParameter.setName(typeParameterName);
                      String typeParameterModelAnnotation = getFilteredModelAnnotations(methodAnnotation, typeParameterName);
                      String bounds = getModelAnnotationAttribute(typeParameterModelAnnotation, "bounds");
                      if (bounds != null)
                      {
                        eTypeParameter.getEBounds().addAll(analyzeEGenericTypes(bounds));
                        for (EGenericType eBound : eTypeParameter.getEBounds())
                        {
                          ecoreEGenericTypeToJavaEGenericTypeMap.put(eBound, null);
                        }
                      }

                      eDataType.getETypeParameters().add(eTypeParameter);
                    }
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
                           EMPTY_STRING_ARRAY,
                           EMPTY_STRING_ARRAY);
                      }
                    }
                    else
                    {
                      analyzeMethod
                        (eClass,
                         getFilteredModelAnnotations(methodAnnotation, "key"),
                         "getKey",
                         "java.lang.Object",
                         EMPTY_STRING_ARRAY,
                         EMPTY_STRING_ARRAY,
                         EMPTY_STRING_ARRAY,
                         EMPTY_STRING_ARRAY);

                      analyzeMethod
                        (eClass,
                         getFilteredModelAnnotations(methodAnnotation, "value"),
                         "getValue",
                         "java.lang.Object",
                         EMPTY_STRING_ARRAY,
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
            ePackage.getEAnnotations().addAll(existingEPackage.getEAnnotations());
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
      String[] parameterTypes = method.getFullParameterTypes();
      String[] exceptionTypes = method.getExceptions();
      String[] typeParameters = method.getTypeParameters();

      ETypedElement eTypedElement = analyzeMethod(eClass, modelAnnotation, methodName, returnType, parameterNames, parameterTypes, exceptionTypes, typeParameters);
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
    String[] exceptionTypes,
    String[] typeParameters)
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
    boolean declaredEOperation =
        "operation".equals(kind) ||
          (parameters != null && !"attribute".equals(kind) && !"reference".equals(kind));

    // Check whether there are parameters; the special attribute and reference rules only apply for the case of no arguments.
    //
    if (parameterNames.length == 0 &&
         !declaredEOperation &&
         methodName.startsWith("get") &&
         methodName.length() > 3 &&
         Character.isUpperCase(methodName.charAt(3)) &&
         !"boolean".equals(returnType) &&
         !"void".equals(returnType))
    {
      // The feature name is extracted lower cased.
      //
      featureName = CodeGenUtil.uncapName(methodName.substring(3));
    }
    else if (parameterNames.length == 0 &&
              !declaredEOperation &&
              methodName.startsWith("is") &&
              methodName.length() > 2 &&
              Character.isUpperCase(methodName.charAt(2)) &&
              "boolean".equals(returnType))
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

      analyzeTypeParameters(eOperation.getETypeParameters(), typeParameters, modelAnnotation);

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
        List<EGenericType> ecoreEGenericExceptions = new ArrayList<EGenericType>();
        String exceptions = getModelAnnotationAttribute(modelAnnotation, "exceptions");
        if (exceptions != null)
        {
          ecoreEGenericExceptions.addAll(analyzeEGenericTypes(exceptions));
        }
        List<EGenericType> javaEGenericExceptions = new ArrayList<EGenericType>();
        for (String exception : exceptionTypes)
        {
          Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(exception);
          if (diagnostic.getSeverity() == Diagnostic.OK)
          {
            javaEGenericExceptions.add((EGenericType)diagnostic.getData().get(0));
          }
        }
        match(eOperation.getEGenericExceptions(), javaEGenericExceptions, ecoreEGenericExceptions);
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
      String keys = getModelAnnotationAttribute(modelAnnotation, "keys");
      String containment = getModelAnnotationAttribute(modelAnnotation, "containment");
      String resolveProxies = getModelAnnotationAttribute(modelAnnotation, "resolveProxies");
      String mapType = getModelAnnotationAttribute(modelAnnotation, "mapType");
      String keyType = getModelAnnotationAttribute(modelAnnotation, "keyType");
      String valueType = getModelAnnotationAttribute(modelAnnotation, "valueType");

      if ("reference".equals(kind) ||
            opposite != null ||
            keys != null ||
            containment != null ||
            resolveProxies != null ||
            mapType != null ||
            keyType != null && valueType != null)
      {
        EReference eReference = EcoreFactory.eINSTANCE.createEReference();
        eTypedElement = eStructuralFeature = eReference;
        eClass.getEStructuralFeatures().add(eReference);

        // Set the EReference attributes.
        //
        eReference.setContainment
          ("true".equals(containment) ||
              mapType != null && !stripTypeArguments(returnType).endsWith("Entry") ||
              keyType != null && valueType != null);
        eReference.setResolveProxies(eReference.isContainment() ? "true".equals(resolveProxies) : !"false".equals(resolveProxies));
        eReference.setUnsettable("true".equals(getModelAnnotationAttribute(modelAnnotation, "unsettable")));

        // Defer the handling of the opposite.
        //
        if (opposite != null)
        {
          eReferenceToOppositeNameMap.put(eReference, opposite);
          if ("false".equals(getModelAnnotationAttribute(modelAnnotation, "transient")))
          {
            transientEReferenceWithOpposite.add(eReference);
          }
        }
        
        if (keys != null)
        {
          List<String> keyNames = new ArrayList<String>();
          for (StringTokenizer stringTokenizer = new StringTokenizer(keys, " ,\t\n\r\f"); stringTokenizer.hasMoreTokens();)
          {
            keyNames.add(stringTokenizer.nextToken());
          }
          eReferenceToKeyNamesMap.put(eReference, keyNames);
        }
      }
      else
      {
        // Assume that it's an attribute for now.
        // It will/could become a reference if the type resolves to an EClass.
        //
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        if ("attribute".equals(kind))
        {
          eAttributes.add(eAttribute);
        }
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
      if (eTypedElement instanceof EStructuralFeature)
      {
        eStructuralFeatures.add((EStructuralFeature)eTypedElement);
      }
    }

    return eTypedElement;
  }

  protected List<EGenericType> analyzeEGenericTypes(String genericTypes)
  {
    List<EGenericType> result = new ArrayList<EGenericType>();
    int depth = 0;
    int start = -1;
    int length = genericTypes.length();
    for (int i = 0; i < length; i = Character.offsetByCodePoints(genericTypes, i, 1))
    {
      int codePoint = genericTypes.codePointAt(i);
      if (codePoint == '<')
      {
        ++depth;
      }
      else if (codePoint == '>')
      {
        --depth;
      }
      else if (Character.isWhitespace(codePoint))
      {
        if (depth == 0 && start != -1)
        {
          Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(genericTypes.substring(start, i));
          result.add((EGenericType)diagnostic.getData().get(0));
          start = -1;
        }
      }
      else if (start == -1)
      {
        start = i;
      }
    }
    if (start != -1)
    {
      Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(genericTypes.substring(start));
      result.add((EGenericType)diagnostic.getData().get(0));
    }
    return result;
  }

  void analyzeTypeParameters(List<ETypeParameter> eTypeParameters, String[] typeParameters, String modelAnnotation)
  {
    if (typeParameters.length > 0)
    {
      for (String typeParameter : typeParameters)
      {
        Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseTypeParameter(typeParameter);
        if (diagnostic.getSeverity() == Diagnostic.OK)
        {
          ETypeParameter eTypeParameter = (ETypeParameter)diagnostic.getData().get(0);
          List<EGenericType> javaEBounds = new ArrayList<EGenericType>(eTypeParameter.getEBounds());
          eTypeParameter.getEBounds().clear();
          List<EGenericType> ecoreEBounds =  null;
          String bounds = getModelAnnotationAttribute(getFilteredModelAnnotations(modelAnnotation, eTypeParameter.getName()), "bounds");
          if (bounds != null)
          {
            ecoreEBounds = analyzeEGenericTypes(bounds);
          }
          match(eTypeParameter.getEBounds(), javaEBounds, ecoreEBounds);
          eTypeParameters.add(eTypeParameter);
        }
      }
    }
  }

  protected boolean isListType(String type)
  {
    type = stripTypeArguments(type);
    return "EList".equals(type) || "org.eclipse.emf.common.util.EList".equals(type) || "List".equals(type) || "java.util.List".equals(type);
  }

  protected boolean isMapType(String type)
  {
    type = stripTypeArguments(type);
    return "EMap".equals(type) || "org.eclipse.emf.common.util.EMap".equals(type) || "Map".equals(type) || "java.util.Map".equals(type);
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

    // For lists, maps, and feature maps, the default is many-valued, which can be overridden by an upper-bound declaration.
    //
    if (isListType(type) && (dataType == null || modelType != null && !isListType(modelType)))
    {
      eTypedElement.setUpperBound(-1);
      Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(type);
      if (diagnostic.getSeverity() == Diagnostic.OK)
      {
        EGenericType eGenericType = (EGenericType)diagnostic.getData().get(0);
        if (eGenericType.getETypeArguments().size() == 1)
        {
          type = eGenericTypeConverter.toJavaInstanceTypeName(eGenericType.getETypeArguments().get(0));
          if (modelType == null)
          {
            modelType = type;
          }
        }
      }
      if (modelType == null && !"false".equals(many))
      {
        error(CodeGenEcorePlugin.INSTANCE.getString("_UI_TheTypeMustBeSpecifiedFor_message", new Object [] { identifierName }));
        modelType = "java.lang.Object";
      }
    }
    else if (mapType != null ||
              keyType != null && valueType != null ||
              "FeatureMap".equals(type) ||
              "org.eclipse.emf.common.util.FeatureMap".equals(type))
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

    EGenericType ecoreEGenericType = null;
    EGenericType javaEGenericType = null;

    // The type can be augmented by specifying the it explicitly in the annotation.
    // This mostly makes sense only for many-valued typed elements, where the Java
    // type is a list and the item type needs to be specified.
    //
    if (modelType != null)
    {
      Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(modelType);
      if (diagnostic.getSeverity() == Diagnostic.OK)
      {
        javaEGenericType = (EGenericType)diagnostic.getData().get(0);
      }
      type = modelType;
    }

    if (mapType != null || keyType != null && valueType != null)
    {
      EPackage ePackage = (EPackage)EcoreUtil.getRootContainer(eTypedElement);
      EGenericType ecoreKeyEGenericType = null;
      EGenericType javaKeyEGenericType = null;
      EGenericType ecoreValueEGenericType = null;
      EGenericType javaValueEGenericType = null;
      if (mapType != null)
      {
        Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(mapType);
        if (diagnostic.getSeverity() == Diagnostic.OK)
        {
          ecoreEGenericType = (EGenericType)diagnostic.getData().get(0);
          resolve(eTypedElement, ecoreEGenericType, RequiredClassifierType.CLASS);
          List<EGenericType> mapETypeArguments = ecoreEGenericType.getETypeArguments();
          if (mapETypeArguments.size() == 2)
          {
            ecoreKeyEGenericType = mapETypeArguments.get(0);
            ecoreValueEGenericType = mapETypeArguments.get(1);
          }
          mapETypeArguments.clear();
        }
        else
        {
          ecoreEGenericType = resolve(eTypedElement, mapType, RequiredClassifierType.CLASS, false);
        }
        if (ecoreEGenericType.getERawType().getEPackage() == null)
        {
          if (keyType == null || valueType == null && isMapType(type))
          {
            diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(type);
            if (diagnostic.getSeverity() == Diagnostic.OK)
            {
              EGenericType eGenericType = (EGenericType)diagnostic.getData().get(0);
              if (eGenericType.getETypeArguments().size() == 2)
              {
                javaKeyEGenericType = eGenericType.getETypeArguments().get(0);
                javaValueEGenericType = eGenericType.getETypeArguments().get(1);
              }
            }
          }
          if (javaKeyEGenericType == null)
          {
            javaKeyEGenericType = resolve(eTypedElement, keyType == null ? "java.lang.Object" : keyType, RequiredClassifierType.NONE, false);
          }
          if (javaValueEGenericType == null)
          {
            javaValueEGenericType = resolve(eTypedElement, valueType == null ? "java.lang.Object" : valueType, RequiredClassifierType.NONE, false);
          }
        }
      }
      else
      {
        javaKeyEGenericType = resolve(eTypedElement, keyType, RequiredClassifierType.NONE, false);
        javaValueEGenericType = resolve(eTypedElement, valueType, RequiredClassifierType.NONE, false);
        ecoreEGenericType = resolveMapEntry(ePackage, javaKeyEGenericType, javaValueEGenericType);
        if (ecoreEGenericType == null)
        {
          ecoreEGenericType = 
            resolve
              (eTypedElement, 
               javaKeyEGenericType.getERawType().getName() + "To" + javaValueEGenericType.getERawType().getName() + "MapEntry", 
               RequiredClassifierType.CLASS, 
               false);
        }
      }
      
      EClass mapEClass = (EClass)ecoreEGenericType.getEClassifier();
      if (mapEClass.getEPackage() == null && mapEClass.getEStructuralFeatures().isEmpty())
      {
        eModelElementToJNodeMap.put(mapEClass, getJCompilationUnit(eTypedElement));
        mapEClass.setInstanceTypeName("java.util.Map$Entry");
        mapEClass.setAbstract(false);
        mapEClass.setInterface(false);
        if (ecoreKeyEGenericType == null)
        {
          createFeature(mapEClass, "key", javaKeyEGenericType);
          ecoreEGenericTypeToJavaEGenericTypeMap.put(javaKeyEGenericType, null);
        }
        else
        {
          createFeature(mapEClass, "key", ecoreKeyEGenericType);
          ecoreEGenericTypeToJavaEGenericTypeMap.put(ecoreKeyEGenericType, javaKeyEGenericType);
        }
        if (ecoreValueEGenericType == null)
        {
          createFeature(mapEClass, "value", javaValueEGenericType);
          ecoreEGenericTypeToJavaEGenericTypeMap.put(javaValueEGenericType, null);
        }
        else
        {
          createFeature(mapEClass, "value", ecoreValueEGenericType);
          ecoreEGenericTypeToJavaEGenericTypeMap.put(ecoreValueEGenericType, javaValueEGenericType);
        }
        EAnnotation container = ePackage.getEAnnotation(MAP_ENTRY_CLASS_CONTAINER_ANNOTATION_SOURCE);
        if (container == null)
        {
           container = EcoreFactory.eINSTANCE.createEAnnotation();
           container.setSource(MAP_ENTRY_CLASS_CONTAINER_ANNOTATION_SOURCE);
           ePackage.getEAnnotations().add(container);
        }
        container.getContents().add(mapEClass);
      }
    }
    else if (dataType != null)
    {
      Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(dataType);
      if (diagnostic.getSeverity() == Diagnostic.OK)
      {
        ecoreEGenericType = (EGenericType)diagnostic.getData().get(0);
        diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(type);
        if (diagnostic.getSeverity() == Diagnostic.OK)
        {
          javaEGenericType = (EGenericType)diagnostic.getData().get(0);
        }
      }
      else
      {
        ecoreEGenericType = EcoreFactory.eINSTANCE.createEGenericType();
        ecoreEGenericType.setEClassifier(EcorePackage.Literals.EJAVA_OBJECT);
        basicDiagnostic.add(diagnostic);
      }
    }
    else
    {
      Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(type);
      if (diagnostic.getSeverity() == Diagnostic.OK)
      {
        javaEGenericType = (EGenericType)diagnostic.getData().get(0);
      }
      else
      {
        ecoreEGenericType = EcoreFactory.eINSTANCE.createEGenericType();
        ecoreEGenericType.setEClassifier(EcorePackage.Literals.EJAVA_OBJECT);
        basicDiagnostic.add(diagnostic);
      }
    }

    if (ecoreEGenericType == null)
    {
      eTypedElement.setEGenericType(javaEGenericType);
      ecoreEGenericTypeToJavaEGenericTypeMap.put(javaEGenericType, null);
    }
    else
    {
      eTypedElement.setEGenericType(ecoreEGenericType);
      ecoreEGenericTypeToJavaEGenericTypeMap.put(ecoreEGenericType, javaEGenericType);
    }

    eTypedElement.setUnique(!"false".equals(getModelAnnotationAttribute(modelAnnotation, "unique")));
    eTypedElement.setOrdered(!"false".equals(getModelAnnotationAttribute(modelAnnotation, "ordered")));
  }

  protected JCompilationUnit getJCompilationUnit(EModelElement eModelElement)
  {
    for (; eModelElement != null; eModelElement = (EModelElement)eModelElement.eContainer())
    {
      JCompilationUnit compilationUnit = facadeHelper.getCompilationUnit(eModelElementToJNodeMap.get(eModelElement));
      if (compilationUnit != null)
      {
        return compilationUnit;
      }
    }
    return null;
  }

  /**
   * Strips the type arguments from the type, if it has any.
   */
  protected String stripTypeArguments(String type)
  {
    int start = type.indexOf('<');
    if (start > 0)
    {
      int end = type.lastIndexOf('>');
      if (end > start+1)
      {
        return type.substring(0, start).trim();
      }
    }
    return type;
  }

  protected EStructuralFeature createFeature(EClass eClass, String name, EGenericType eGenericType)
  {
    EClassifier eClassifier = eGenericType.getERawType();
    if (eClassifier instanceof EClass)
    {
      EReference eReference = EcoreFactory.eINSTANCE.createEReference();
      eReference.setName(name);
      eReference.setEGenericType(eGenericType);
      eClass.getEStructuralFeatures().add(eReference);
      eStructuralFeatures.add(eReference);
      return eReference;
    }
    else
    {
      EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      eAttribute.setName(name);
      eAttribute.setEGenericType(eGenericType);
      eClass.getEStructuralFeatures().add(eAttribute);
      eStructuralFeatures.add(eAttribute);
      return eAttribute;
    }
  }

  /**
   * Creates EEnumLiteral as appropriate.
   * @return <code>true</code> when the method has created an EnumLiteral after
   * analyzing the JMember
   */
  protected boolean analyzeEnumLiteral(EEnum eEnum, JMember member)
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

      if (fieldName.endsWith("_VALUE") && (name == null || !name.toLowerCase().endsWith("value")))
      {
        fieldName = fieldName.substring(0, fieldName.length()-"_VALUE".length());
      }

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

      return eEnum.getELiterals().add(eEnumLiteral);
    }
    return false;
  }

  /**
   * The pattern for extracting the model documentation.
   */
  protected static Pattern modelDocExpression =
    Pattern.compile
      ("<!--\\s*begin-model-doc\\s*-->[ \\f\\n\\r\\t]*\\*\\s?(.*?)<!--\\s*end-model-doc\\s*-->",
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
  protected static Pattern modelAnnotationExpression =
    Pattern.compile
      ("@[ \\f\\n\\r\\t*]*model[ \\f\\n\\r\\t*]*((\\w*\\s*=\\s*(['\"])(?>\\\\.|.)*?\\3[ \\f\\n\\r\\t*]*)*)",
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
    Pattern modelAnnotationAttributeExpressionDoubleQuote =
      Pattern.compile
        ("\\b" + attributeName + "\\s*=\\s*([\"'])((?>\\\\.|.)*?)\\1",
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
    Pattern modelAnnotationAttributeExpressionDoubleQuote =
      Pattern.compile
        ("\\b" + attributeName + "\\s*=\\s*([\"'])((?>\\\\.|.)*?)\\1",
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
    Pattern modelAnnotationAttributeExpressionDoubleQuote =
      Pattern.compile
        ("\\b" + filter + "([A-Z]\\w*\\s*=\\s*([\"'])((?>\\\\.|.)*?)\\2)",
         Pattern.MULTILINE);
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

  protected void match(List<EGenericType> target, List<EGenericType> javaEGenericTypes, List<EGenericType> ecoreEGenericTypes)
  {
    for (Iterator<EGenericType> i = javaEGenericTypes.iterator(), j = ecoreEGenericTypes == null ? null : ecoreEGenericTypes.iterator(); i.hasNext(); )
    {
      EGenericType javaEGenericType = i.next();
      EGenericType ecoreEGenericType = j != null && j.hasNext() ? j.next() : null;

      // If there is no Ecore representation or it's invalid, e.g., when - is used to omit a specification, then just use the Java representation.
      //
      if (ecoreEGenericType == null || ecoreEGenericType.getEClassifier() == null)
      {
        ecoreEGenericTypeToJavaEGenericTypeMap.put(javaEGenericType, null);
        target.add(javaEGenericType);
      }
      else
      {
        ecoreEGenericTypeToJavaEGenericTypeMap.put(ecoreEGenericType, javaEGenericType);
        target.add(ecoreEGenericType);
      }
    }
  }

  protected void resolve(EModelElement eModelElement, EGenericType ecoreEGenericType, EGenericType javaEGenericType)
  {
    EClassifier eClassifier = ecoreEGenericType.getERawType();
    if (eClassifier.getEPackage() == null)
    {
      if (javaEGenericType != null)
      {
        ETypeParameter eTypeParameter = javaEGenericType.getETypeParameter();
        if (eTypeParameter != null)
        {
          eClassifier = null;
          ecoreEGenericType.setEClassifier(null);
          ecoreEGenericType.setETypeParameter(eTypeParameter);
        }
        else
        {
          eClassifier.setInstanceTypeName
            (ecoreEGenericType.getETypeArguments().isEmpty() ?
               eGenericTypeConverter.toJavaInstanceTypeName(javaEGenericType) :
               javaEGenericType.getEClassifier().getInstanceTypeName());
        }
      }

      if (eClassifier != null)
      {
        EPackage ePackage = (EPackage)EcoreUtil.getRootContainer(eModelElement);
        addToPackage(ePackage, eClassifier);
        if (eClassifier instanceof EClass)
        {
          for (EStructuralFeature eStructuralFeature : ((EClass)eClassifier).getEStructuralFeatures())
          {
            resolve(eStructuralFeature, eStructuralFeature.getEGenericType(), (EGenericType)null);
          }
        }
      }
    }
    EGenericType ecoreEUpperBound = ecoreEGenericType.getEUpperBound();
    if (ecoreEUpperBound != null)
    {
      resolve(eModelElement, ecoreEUpperBound, javaEGenericType == null ? null : javaEGenericType.getEUpperBound());
    }
    else
    {
      EGenericType ecoreELowerBound = ecoreEGenericType.getELowerBound();
      if (ecoreELowerBound != null)
      {
        resolve(eModelElement, ecoreELowerBound, javaEGenericType == null ? null : javaEGenericType.getELowerBound());
      }
    }

    if (eClassifier != null)
    {
      int index = 0;
      for (Iterator<EGenericType>
             i = ecoreEGenericType.getETypeArguments().iterator(),
             j = javaEGenericType == null ? null : javaEGenericType.getETypeArguments().iterator();
           i.hasNext();
           ++index)
      {
        EGenericType eTypeArgument = i.next();
        EGenericType eTypeArgumentInstance = j != null && j.hasNext() ? j.next() : null;
        if (demandCreatedEClassifiers.contains(eClassifier) && eClassifier.getETypeParameters().size() <= index)
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName(index == 0? "T"  : "T" + index);
          eClassifier.getETypeParameters().add(eTypeParameter);
        }
        resolve(eModelElement, eTypeArgument, eTypeArgumentInstance);
      }
    }
  }

  protected void resolve(EModelElement eModelElement, EGenericType eGenericType, RequiredClassifierType requiredClassifierType)
  {
    EClassifier eClassifier = eGenericType.getEClassifier();
    if (eClassifier != null)
    {
      if (eClassifier.eContainer() == null)
      {
        EGenericType resolvedEGenericType = resolve(eModelElement, eClassifier.getInstanceTypeName(), requiredClassifierType, false);
        eGenericType.setETypeParameter(resolvedEGenericType.getETypeParameter());
        eGenericType.setEClassifier(resolvedEGenericType.getEClassifier());
      }
      else if (eClassifier.getEPackage() == null)
      {
        EGenericType resolvedEGenericType = resolve(eModelElement, eClassifier.getName(), requiredClassifierType, false);
        if (resolvedEGenericType.getERawType().getEPackage() == null)
        {
          EPackage ePackage = (EPackage)EcoreUtil.getRootContainer(eClassifier);
          addToPackage(ePackage, eClassifier);
        }
        else
        {
          eGenericType.setETypeParameter(null);
          eGenericType.setEClassifier(resolvedEGenericType.getEClassifier());
        }
      }
      for (EGenericType eTypeArgument : eGenericType.getETypeArguments())
      {
        resolve(eModelElement, eTypeArgument, RequiredClassifierType.NONE);
      }
    }
    else
    {
      EGenericType eUpperBound = eGenericType.getEUpperBound();
      if (eUpperBound != null)
      {
        resolve(eModelElement, eUpperBound, RequiredClassifierType.NONE);
      }
      else
      {
        EGenericType eLowerBound = eGenericType.getELowerBound();
        if (eLowerBound != null)
        {
          resolve(eModelElement, eLowerBound, RequiredClassifierType.NONE);
        }
      }
    }
  }

  protected enum RequiredClassifierType
  {
    NONE
    {
      @Override
      boolean isValid(EClassifier eClassifier)
      {
        return eClassifier != null;
      }
    },
    CLASS
    {
      @Override
      boolean isValid(EClassifier eClassifier)
      {
        return eClassifier instanceof EClass;
      }
    },
    DATA_TYPE
    {
      @Override
      boolean isValid(EClassifier eClassifier)
      {
        return eClassifier instanceof EDataType;
      }
    };

    abstract boolean isValid(EClassifier eClassifier);
  }

  protected EGenericType resolve(EModelElement eModelElement, String typeName, RequiredClassifierType requiredClassifierType, boolean recordDemandCreatedEClassifier)
  {
    EPackage ePackage = (EPackage)EcoreUtil.getRootContainer(eModelElement);

    // We want to resolve to this.
    //
    EGenericType eGenericType = null;

    // Check if it is exactly the name of a type parameter that's in scope.
    //
    for (EObject eObject = eModelElement; eObject != null; eObject = eObject.eContainer())
    {
      EList<ETypeParameter> eTypeParameters = null;
      if (eObject instanceof EOperation)
      {
        eTypeParameters = ((EOperation)eObject).getETypeParameters();
      }
      else if (eObject instanceof EClass)
      {
        eTypeParameters = ((EClass)eObject).getETypeParameters();
      }
      // If we've found thing with type parameters, search them for a match.
      //
      if (eTypeParameters != null)
      {
        for (ETypeParameter localETypeParameter : eTypeParameters)
        {
          if (typeName.equals(localETypeParameter.getName()))
          {
            // If we find a match, make this generic type be for this type parameter,
            // and then stop the whole process.
            eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericType.setETypeParameter(localETypeParameter);
            return eGenericType;
          }
        }
      }
    }

    // Check if the name is qualified
    //
    String baseName = typeName;
    String packageName = "";
    int index = baseName.lastIndexOf(".");
    if (index == -1)
    {
      // Look through the imports of the containing compilation unit.
      //
      JCompilationUnit compilationUnit = getJCompilationUnit(eModelElement);
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
            typeName = packageName + "." + baseName;
            break;
          }
          else if (firstWildcard && importName.endsWith(".*"))
          {
            int importIndex = importName.lastIndexOf(".");
            packageName = importName.substring(0, importIndex);
            typeName = packageName + "." + baseName;
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
          EClassifier eClassifier = otherEPackage.getEClassifier(baseName);
          if (requiredClassifierType.isValid(eClassifier))
          {
            eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericType.setEClassifier(eClassifier);
          }
        }
      }

      // If we can't find it, try the simple name in the package...
      //
      if (eGenericType == null && "".equals(packageName))
      {
        EClassifier eClassifier = ePackage.getEClassifier(baseName);
        if (requiredClassifierType.isValid(eClassifier))
        {
          eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setEClassifier(eClassifier);
        }
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
        EClassifier eClassifier = otherEPackage.getEClassifier(baseName);
        if (eClassifier != null)
        {
          eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setEClassifier(eClassifier);
        }
      }
    }

    // If we still don't have one, we'll have to settle for an EDataType or EClass with an instance class name.
    //
    if (eGenericType == null)
    {
      // See if we already have the EDataType.
      //
      String modifiedName = typeName.replace('$', '.');
      for (EClassifier ePackageClassifier : ePackage.getEClassifiers())
      {
        String name = ePackageClassifier.getInstanceTypeName();
        if (name != null &&
              name.replace('$', '.').equals(modifiedName) &&
              requiredClassifierType.isValid(ePackageClassifier))
        {
          eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setEClassifier(ePackageClassifier);
          break;
        }
      }
    }

    if (eGenericType == null &&
          EcorePackage.Literals.EOBJECT.getInstanceTypeName().equals(typeName) &&
          requiredClassifierType != RequiredClassifierType.DATA_TYPE)
    {
      eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
      eGenericType.setEClassifier(EcorePackage.Literals.EOBJECT);
    }

    // Just to be helpful, we'll recognize a type of org.eclipse.emf.ecore.util.FeatureMap and convert it to EFeatureMapEntry.
    // This way a dataType need not be specified. But, we won't do this if recordDemandCreateEDataType is false, so we don't
    // change the instanceClass of a new EDataType that's implicitly being defined for FeatureMap.
    //
    if (eGenericType == null &&
          EcorePackage.Literals.EFEATURE_MAP.getInstanceTypeName().equals(typeName) &&
          requiredClassifierType != RequiredClassifierType.CLASS)
    {
      eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
      eGenericType.setEClassifier(EcorePackage.Literals.EFEATURE_MAP_ENTRY);
    }

    // If we don't have one yet, maybe it's one of the special types...
    //
    if (eGenericType == null &&
          requiredClassifierType != RequiredClassifierType.CLASS &&
          (packageName.length() == 0 ||
             packageName.equals("java.lang") ||
             packageName.equals("java.math") ||
             packageName.equals("java.util")))
    {
      for (EClassifier ecoreEClassifier : EcorePackage.eINSTANCE.getEClassifiers())
      {
        if (ecoreEClassifier instanceof EDataType)
        {
          String instanceClassName = ecoreEClassifier.getInstanceClassName();
          if ((instanceClassName.equals(typeName) || instanceClassName.equals("java.lang." + typeName)) && ecoreEClassifier.getETypeParameters().isEmpty())
          {
            eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericType.setEClassifier(ecoreEClassifier);
            break;
          }
        }
      }
    }

    // If we still don't have one, we'll have to settle for a demand created EDataType or EClass,
    // so create a new EClassifier with a nice unique name.
    //
    if (eGenericType == null)
    {
      // If the name isn't qualified, it might be a primitive or from java.lang. Otherwise, assume it's in the current
      // package and use the nsPrefix for the qualified package name.
      //
      boolean primitive = false;
      String instanceTypeName = typeName;
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
          instanceTypeName = packageName + "." + instanceTypeName;
        }
        else
        {
          packageName = ePackage.getNsPrefix();
          instanceTypeName = packageName + '.' + instanceTypeName;
        }
      }

      EClassifier eClassifier = null;
      if (!"java.util.Map$Entry".equals(instanceTypeName))
      {
        for (EClassifier demandCreatedEClassifier : demandCreatedEClassifiers)
        {
          if (requiredClassifierType.isValid(demandCreatedEClassifier) && 
                instanceTypeName.equals(demandCreatedEClassifier.getInstanceTypeName()))
          {
            eClassifier = demandCreatedEClassifier;
          }
        }
      }

      if (eClassifier == null)
      {
        if (requiredClassifierType == RequiredClassifierType.CLASS)
        {
          EClass eClass = EcoreFactory.eINSTANCE.createEClass();
          eClass.setAbstract(true);
          eClass.setInterface(true);
          eClassifier = eClass;
        }
        else
        {
          eClassifier = EcoreFactory.eINSTANCE.createEDataType();
        }
        demandCreatedEClassifiers.add(eClassifier);
  
        eClassifier.setInstanceTypeName(instanceTypeName);
  
        // Even primitives should be represented by a data type with a conventional (i.e. capitalized) name.
        //
        String name = baseName;
        if (primitive && name.length() > 0)
        {
          name = CodeGenUtil.capName(name);
        }
  
        // Make array names legal.
        //
        while (name.endsWith("[]"))
        {
          name = name.substring(0, name.length() - 2) + "Array";
        }
        
        eClassifier.setName(name);

        if (recordDemandCreatedEClassifier)
        {
          addToPackage(ePackage, eClassifier);
        }
      }

      eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
      eGenericType.setEClassifier(eClassifier);
    }

    return eGenericType;
  }
  
  protected void addToPackage(EPackage ePackage, EClassifier eClassifier)
  {
    String baseName = eClassifier.getName();
    String name = baseName;
    
    // Avoid classifier name collisions.
    //
    for (int j = 1; ePackage.getEClassifier(name) != null; ++j)
    {
      name = baseName + "_" + j;
    }
    
    eClassifier.setName(name);
    ePackage.getEClassifiers().add(eClassifier);
  }

  protected EGenericType resolveMapEntry(EPackage ePackage, EGenericType keyEGenericType, EGenericType valueEGenericType)
  {
    for (EClassifier ePackageClassifier : ePackage.getEClassifiers())
    {
      if (ePackageClassifier instanceof EClass &&
            ("java.util.Map.Entry".equals(ePackageClassifier.getInstanceClassName()) || "java.util.Map$Entry".equals(ePackageClassifier.getInstanceClassName())))
      {
        EClass mapEntryInterface = (EClass)ePackageClassifier;
        EStructuralFeature keyFeature = mapEntryInterface.getEStructuralFeature("key");
        if (keyFeature != null && EcoreUtil.equals(keyFeature.getEGenericType(), keyEGenericType) && !keyFeature.isMany())
        {
          EStructuralFeature valueFeature = mapEntryInterface.getEStructuralFeature("value");
          if (valueFeature != null && EcoreUtil.equals(valueFeature.getEGenericType(), valueEGenericType) && !valueFeature.isMany())
          {
            EGenericType result = EcoreFactory.eINSTANCE.createEGenericType();
            result.setEClassifier(mapEntryInterface);
            return result;
          }
        }
      }
    }

    return null;
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

  protected <T extends ENamedElement> T getNamedElement(List<T> namedElements, String name)
  {
    if (name != null)
    {
      for (T namedElement : namedElements)
      {
        if (name.equals(namedElement.getName()))
        {
          return namedElement;
        }
      }
    }
    return null;
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
