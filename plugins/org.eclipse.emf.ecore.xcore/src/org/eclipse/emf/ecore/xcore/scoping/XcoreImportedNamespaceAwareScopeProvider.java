/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;


import static java.util.Collections.singletonList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xcore.XImportDirective;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcoreFactory;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.XcorePlugin;
import org.eclipse.emf.ecore.xcore.util.EcoreXcoreBuilder;
import org.eclipse.emf.ecore.xcore.util.XcoreJvmInferrer;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.AbstractEObjectDescription;
import org.eclipse.xtext.resource.DerivedStateAwareResource;
import org.eclipse.xtext.resource.IDerivedStateComputer;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.ISelectable;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractScope;
import org.eclipse.xtext.scoping.impl.FilteringScope;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.scoping.impl.ImportScope;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;
import org.eclipse.xtext.scoping.impl.ScopeBasedSelectable;
import org.eclipse.xtext.util.Strings;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;


public class XcoreImportedNamespaceAwareScopeProvider extends ImportedNamespaceAwareLocalScopeProvider
{
  @Inject
  private IQualifiedNameConverter nameConverter;

  @Inject
  private Provider<EcoreXcoreBuilder> ecoreXcoreBuilderProvider;

  @Inject
  private XcoreJvmInferrer jvmInferrer;

  @Inject
  private IResourceDescription.Manager manager;

  @Override
  protected IScope getResourceScope(IScope parent, EObject context, EReference reference)
  {
    IScope resourceScope = super.getResourceScope(parent, context, reference);
    if (reference == XcorePackage.Literals.XGENERIC_TYPE__TYPE)
    {
      return
        new FilteringScope
          (new ImportScope
            (getImplicitImports(false),
             new EClassifierScope(resourceScope, context.eResource().getResourceSet(), nameConverter, ecoreXcoreBuilderProvider, jvmInferrer),
             null,
             GenModelPackage.Literals.GEN_BASE,
             false),
           new Predicate<IEObjectDescription>()
           {
             public boolean apply(IEObjectDescription input)
             {
               EObject eObjectOrProxy = input.getEObjectOrProxy();
               return !(eObjectOrProxy instanceof GenPackage);
             }
           });
    }
    else if (reference == XcorePackage.Literals.XANNOTATION__SOURCE)
    {
      return
        new ImportScope
          (Collections.singletonList(new ImportNormalizer(nameConverter.toQualifiedName("xcore.lang"), true, false)),
           new XAnnotationDirectiveScope(resourceScope, context.eResource().getResourceSet()),
           null,
           GenModelPackage.Literals.GEN_BASE,
           false);
    }
    else
    {
      return resourceScope;
    }
  }

  @Override
  public IScope getScope(EObject context, EReference reference)
  {
    if (reference == XcorePackage.Literals.XIMPORT_DIRECTIVE__IMPORTED_OBJECT)
    {
      final IScope classifierScope = getResourceScope(context.eResource(), XcorePackage.Literals.XGENERIC_TYPE__TYPE);
      final IScope annotationScope = getResourceScope(context.eResource(), XcorePackage.Literals.XANNOTATION__SOURCE);
      final IScope jvmTypeScope = getResourceScope(context.eResource(), TypesPackage.Literals.JVM_PARAMETERIZED_TYPE_REFERENCE__TYPE);
      return
        new IScope()
        {
          public IEObjectDescription getSingleElement(QualifiedName name)
          {
            IEObjectDescription result = classifierScope.getSingleElement(name);

            // Ignore also any incidental matches to aliased Ecore data types.
            //
            if (result == null || result instanceof EClassifierScope.EcoreDataTypeAliasEObjectDescription)
            {
              result = annotationScope.getSingleElement(name);
            }
            if (result == null)
            {
              result = jvmTypeScope.getSingleElement(name);
            }
            return result;
          }

          public Iterable<IEObjectDescription> getElements(QualifiedName name)
          {
            return Iterables.concat(classifierScope.getElements(name), annotationScope.getElements(name), jvmTypeScope.getElements(name));
          }

          public IEObjectDescription getSingleElement(EObject object)
          {
            IEObjectDescription result = classifierScope.getSingleElement(object);
            if (result == null)
            {
              result = annotationScope.getSingleElement(object);
            }
            if (result == null)
            {
              result = jvmTypeScope.getSingleElement(object);
            }
            return result;
          }

          public Iterable<IEObjectDescription> getElements(EObject object)
          {
            return Iterables.concat(classifierScope.getElements(object), annotationScope.getElements(object), jvmTypeScope.getElements(object));
          }

          public Iterable<IEObjectDescription> getAllElements()
          {
            return Iterables.concat(classifierScope.getAllElements(), annotationScope.getAllElements(), jvmTypeScope.getAllElements());
          }
        };
    }
    else
    {
      return super.getScope(context, reference);
    }
  }

  @Override
  protected List<ImportNormalizer> internalGetImportedNamespaceResolvers(EObject context, boolean ignoreCase)
  {
    if (!(context instanceof XPackage))
    {
      return Collections.emptyList();
    }
    else
    {
      List<ImportNormalizer> importedNamespaceResolvers = Lists.newArrayList();
      XPackage xPackage = (XPackage)context;
      for (XImportDirective xImportDirective : xPackage.getImportDirectives())
      {
        importedNamespaceResolvers.add(createImportedNamespaceResolver(xImportDirective.getImportedNamespace(), ignoreCase));
      }
      String name = xPackage.getName();
      if (!Strings.isEmpty(name))
      {
        importedNamespaceResolvers.add(new ImportNormalizer(nameConverter.toQualifiedName(name), true, ignoreCase));
      }
      return importedNamespaceResolvers;
    }
  }

  @Override
  protected ImportNormalizer createImportedNamespaceResolver(String namespace, boolean ignoreCase)
  {
    if (!Strings.isEmpty(namespace))
    {
      QualifiedName importedNamespace = nameConverter.toQualifiedName(namespace);
      if (importedNamespace != null && importedNamespace.getSegmentCount() >= 1)
      {
        if (importedNamespace.getLastSegment().equals(getWildCard()))
        {
          if (importedNamespace.getSegmentCount() > 1)
          {
            return createImportNormalizer(importedNamespace.skipLast(1), true, ignoreCase);
          }
        }
        else
        {
          return createImportNormalizer(importedNamespace, false, ignoreCase);
        }
      }
    }
    return null;
  }

  @Override
  protected IScope getLocalElementsScope(IScope parent, EObject context, EReference reference)
  {
    IScope result = parent;
    ISelectable allDescriptions = getAllDescriptions(context.eResource());
    ScopeBasedSelectable parentSelectable = new ScopeBasedSelectable(parent);
    QualifiedName name = getQualifiedNameOfLocalElement(context);
    boolean ignoreCase = isIgnoreCase(reference);
    if (context instanceof XPackage)
    {
      result = createImportScope(result, singletonList(createImportNormalizer(QualifiedName.create("java", "lang"), true, false)), parentSelectable, reference.getEReferenceType(), isIgnoreCase(reference));
    }
    List<ImportNormalizer> namespaceResolvers = getImportedNamespaceResolvers(context, ignoreCase);
    if (!namespaceResolvers.isEmpty())
    {
      result = createImportScope(result, namespaceResolvers, parentSelectable, reference.getEReferenceType(), ignoreCase);
    }
    if (name != null)
    {
      ImportNormalizer localNormalizer = createImportNormalizer(name, true, ignoreCase);
      result = createImportScope(result, singletonList(localNormalizer), allDescriptions, reference.getEReferenceType(), ignoreCase);
    }
    return result;
  }

  protected ImportNormalizer createImportNormalizer(QualifiedName importedNamespace, boolean wildCard, boolean ignoreCase)
  {
    return
      new ImportNormalizer(importedNamespace, wildCard, ignoreCase)
      {
        @Override
        public QualifiedName deresolve(QualifiedName fullyQualifiedName)
        {
          QualifiedName importedNamespacePrefix = getImportedNamespacePrefix();
          boolean ignoreCase = isIgnoreCase();
          if (hasWildCard())
          {
            if (fullyQualifiedName.getSegmentCount() != importedNamespacePrefix.getSegmentCount() &&
                  (ignoreCase ? fullyQualifiedName.startsWithIgnoreCase(importedNamespacePrefix) : fullyQualifiedName.startsWith(importedNamespacePrefix)))
            {
              return fullyQualifiedName.skipFirst(importedNamespacePrefix.getSegmentCount());
            }
          }
          else
          {
            if (fullyQualifiedName.equals(importedNamespacePrefix))
            {
              String lastSegment = fullyQualifiedName.getLastSegment();
              int dollar = lastSegment.lastIndexOf('$');
              return QualifiedName.create(dollar >= 0 ? lastSegment.substring(dollar + 1) : fullyQualifiedName.getLastSegment());
            }
            QualifiedName fullyQualifiedNameBase = fullyQualifiedName.skipLast(1);
            QualifiedName importedNamespacePrefixBase = importedNamespacePrefix.skipLast(1);
            if (ignoreCase ? fullyQualifiedNameBase.equalsIgnoreCase(importedNamespacePrefixBase) : fullyQualifiedNameBase.equals(importedNamespacePrefixBase))
            {
              String lastImportedSegment = importedNamespacePrefix.getLastSegment();
              String lastSegment = fullyQualifiedName.getLastSegment();
              int lastImportedSegmentLength = lastImportedSegment.length();
              if (lastSegment.length() > lastImportedSegmentLength &&
                    lastSegment.charAt(lastImportedSegmentLength) == '$' &&
                    (ignoreCase ?
                       lastSegment.substring(0, lastImportedSegmentLength).equalsIgnoreCase(lastImportedSegment) :
                       lastSegment.startsWith(lastImportedSegment)))
              {
                int dollar = lastImportedSegment.lastIndexOf('$');
                return
                  dollar == -1 ?
                    fullyQualifiedName.skipFirst(importedNamespacePrefix.getSegmentCount() - 1) :
                    QualifiedName.create(lastSegment.substring(dollar + 1));
              }
            }
          }
          return null;
        }

        @Override
        public QualifiedName resolve(QualifiedName relativeName)
        {
          QualifiedName importedNamespacePrefix = getImportedNamespacePrefix();
          if (hasWildCard())
          {
            return importedNamespacePrefix.append(relativeName);
          }
          else
          {
            if (relativeName.getSegmentCount() == 1)
            {
              String lastSegment = relativeName.getLastSegment();
              String lastImportedSegment = importedNamespacePrefix.getLastSegment();
              boolean ignoreCase = isIgnoreCase();
              if (ignoreCase ? lastSegment.equalsIgnoreCase(lastImportedSegment) : lastSegment.equals(lastImportedSegment))
              {
                return importedNamespacePrefix;
              }
              else
              {
                int dollar = lastSegment.indexOf('$');
                if (dollar >= 0)
                {
                  int lastImportedSegmentLength = lastImportedSegment.length();
                  if (dollar == lastImportedSegmentLength &&
                        (ignoreCase ?
                           (lastSegment.length() > lastImportedSegmentLength && lastSegment.substring(0, lastImportedSegmentLength).equalsIgnoreCase(lastImportedSegment)) :
                           lastSegment.startsWith(lastImportedSegment)))
                  {
                    return importedNamespacePrefix.skipLast(1).append(lastSegment);
                  }
                }
                int importedDollar = lastImportedSegment.lastIndexOf('$');
                if (importedDollar >= 0)
                {
                  String nestedTypeName = lastImportedSegment.substring(importedDollar + 1);
                  int nestedTypeNameLength = nestedTypeName.length();
                  if (ignoreCase ?
                        (lastSegment.length() > nestedTypeNameLength && lastSegment.substring(0, nestedTypeNameLength).equalsIgnoreCase(nestedTypeName)) :
                        lastSegment.startsWith(nestedTypeName))
                  {
                    if (nestedTypeNameLength == lastSegment.length())
                    {
                      return importedNamespacePrefix;
                    }
                    else if (lastSegment.charAt(nestedTypeNameLength) == '$')
                    {
                      return importedNamespacePrefix.skipLast(1).append(lastImportedSegment + lastSegment.substring(nestedTypeNameLength));
                    }
                  }
                }
              }
            }
          }
          return null;
        }
      };
  }

  @Override
  protected ImportScope createImportScope(IScope parent, List<ImportNormalizer> namespaceResolvers, ISelectable importFrom, final EClass type, boolean ignoreCase)
  {
    // Ensure that qualified names with more than one component don't resolve against wildcard imports.
    //
    return
      new ImportScope(namespaceResolvers, parent, importFrom, type, ignoreCase)
      {
        @Override
        protected IEObjectDescription getSingleLocalElementByName(QualifiedName name)
        {
          return name.getSegmentCount() > 1 && type == GenModelPackage.Literals.GEN_BASE ? null : super.getSingleLocalElementByName(name);
        }

        @Override
        protected Iterable<IEObjectDescription> getLocalElementsByName(QualifiedName name)
        {
          return name.getSegmentCount() > 1 && type == GenModelPackage.Literals.GEN_BASE ? Collections.<IEObjectDescription>emptyList() : super.getLocalElementsByName(name);
        }
      };
  }

  @Override
  protected boolean isRelativeImport()
  {
    return false;
  }

  @Override
  protected ISelectable internalGetAllDescriptions(Resource resource)
  {
    IResourceDescription description = manager.getResourceDescription(resource);
    return description;
  }

  public static final EDataType[] IMPLICIT_ALIASES =
    {
      EcorePackage.Literals.EBIG_DECIMAL,
      EcorePackage.Literals.EBIG_INTEGER,
      EcorePackage.Literals.EBOOLEAN,
      EcorePackage.Literals.EBOOLEAN_OBJECT,
      EcorePackage.Literals.EBYTE,
      EcorePackage.Literals.EBYTE_OBJECT,
      EcorePackage.Literals.ECHAR,
      EcorePackage.Literals.ECHARACTER_OBJECT,
      EcorePackage.Literals.EDATE,
      EcorePackage.Literals.EDOUBLE,
      EcorePackage.Literals.EDOUBLE_OBJECT,
      EcorePackage.Literals.EFLOAT,
      EcorePackage.Literals.EFLOAT_OBJECT,
      EcorePackage.Literals.EINT,
      EcorePackage.Literals.EINTEGER_OBJECT,
      EcorePackage.Literals.EJAVA_CLASS,
      EcorePackage.Literals.EJAVA_OBJECT,
      EcorePackage.Literals.ELONG,
      EcorePackage.Literals.ELONG_OBJECT,
      EcorePackage.Literals.ESHORT,
      EcorePackage.Literals.ESHORT_OBJECT,
      EcorePackage.Literals.ESTRING
    };

  protected static class EClassifierScope extends AbstractScope
  {
    private class EcoreDataTypeAliasEObjectDescription extends AbstractEObjectDescription
    {
      private final QualifiedName qualifiedName;
      private final QualifiedName actualQualifiedName;
      private final EDataType eDataType;
      private EObject eObject;

      private EcoreDataTypeAliasEObjectDescription(QualifiedName qualifiedName, QualifiedName actualQualifiedName, EDataType eDataType)
      {
        this.qualifiedName = qualifiedName;
        this.actualQualifiedName = actualQualifiedName;
        this.eDataType = eDataType;
      }

      public QualifiedName getQualifiedName()
      {
        return qualifiedName;
      }

      public QualifiedName getName()
      {
        return qualifiedName;
      }

      public URI getEObjectURI()
      {
        IEObjectDescription element = getElement();
        return element == null ? getSyntheticEObjectURI() : element.getEObjectURI();
      }

      public EObject getEObjectOrProxy()
      {
        IEObjectDescription element = getElement();
        if (element == null)
        {
          if (eObject == null)
          {
            InternalEObject genDataType = (InternalEObject)GenModelFactory.eINSTANCE.createGenDataType();
            genDataType.eSetProxyURI(getSyntheticEObjectURI());
            eObject = genDataType;
          }
          return eObject;
        }
        else
        {
          return element.getEObjectOrProxy();
        }
      }

      public EClass getEClass()
      {
        return GenModelPackage.Literals.GEN_DATA_TYPE;
      }

      protected URI getSyntheticEObjectURI()
      {
        return ECORE_XCORE_URI.appendFragment("/1/ecore/" + eDataType.getName());
      }

      protected IEObjectDescription getElement()
      {
        IEObjectDescription element = getParent().getSingleElement(actualQualifiedName);
        if (element == null)
        {
          DerivedStateAwareResource ecoreXcoreResource = (DerivedStateAwareResource)resourceSet.getResource(ECORE_XCORE_URI, false);
          if (ecoreXcoreResource == null)
          {
            Resource genModelResource = resourceSet.getResource(ECORE_GEN_MODEL_URI, true);
            final GenModel genModel = (GenModel)genModelResource.getContents().get(0);
            final EPackage ePackage = genModel.getGenPackages().get(0).getEcorePackage();
            Resource ecoreResource = ePackage.eResource();
            final EcoreXcoreBuilder ecoreXcoreBuilder = ecoreXcoreBuilderProvider.get();
            ecoreXcoreBuilder.initialize(genModel);
            EcoreUtil.resolveAll(genModel);
            final XPackage xPackage = ecoreXcoreBuilder.getXPackage(ePackage);

            // Make the population happen as if it were part of creating the derived state so that no client can see empty resource contents.
            // This avoids problems with Ecore Tools transactional editing domain seeing this as a model modification without a write transaction.
            //
            ecoreXcoreResource = (DerivedStateAwareResource)resourceSet.createResource(ECORE_XCORE_URI);
            ecoreXcoreResource.getContents().clear();
            ecoreXcoreResource.setDerivedStateComputer
              (new IDerivedStateComputer()
               {
                 public void installDerivedState(DerivedStateAwareResource resource, boolean preLinkingPhase)
                 {
                   EList<EObject> contents = resource.getContents();
                   contents.add(xPackage);
                   contents.add(genModel);
                   contents.add(ePackage);
                   ecoreXcoreBuilder.link();
                   contents.addAll(jvmInferrer.inferElements(genModel));
                   jvmInferrer.inferDeepStructure(genModel);
                 }

                 public void discardDerivedState(DerivedStateAwareResource resource)
                 {
                   throw new UnsupportedOperationException();
                 }
               });

            resourceSet.getResources().remove(genModelResource);
            resourceSet.getResources().remove(ecoreResource);
          }
          eObject = ecoreXcoreResource.getEObject("/1/ecore/" + eDataType.getName());
        }
        return element;
      }
    }

    private static final URI ECORE_XCORE_URI = URI.createURI("platform:/resource/org.eclipse.emf.ecore/model/Ecore.xcore");

    private static final URI ECORE_GEN_MODEL_URI =
       EMFPlugin.IS_ECLIPSE_RUNNING ?
         URI.createURI("platform:/resource/org.eclipse.emf.ecore/model/Ecore.genmodel") :
         URI.createURI(EcorePlugin.INSTANCE.getBaseURL().toString() + "model/Ecore.genmodel");


    private ResourceSet resourceSet;

    private IQualifiedNameConverter nameConverter;

    private Provider<EcoreXcoreBuilder> ecoreXcoreBuilderProvider;

    private XcoreJvmInferrer jvmInferrer;

    public EClassifierScope(IScope parent, ResourceSet resourceSet, IQualifiedNameConverter nameConverter, Provider<EcoreXcoreBuilder> ecoreXcoreBuilderProvider, XcoreJvmInferrer jvmInferrer)
    {
      super(parent, false);
      this.resourceSet = resourceSet;
      this.nameConverter = nameConverter;
      this.ecoreXcoreBuilderProvider = ecoreXcoreBuilderProvider;
      this.jvmInferrer = jvmInferrer;
    }

    @Override
    protected Iterable<IEObjectDescription> getAllLocalElements()
    {
      ArrayList<IEObjectDescription> result = new ArrayList<IEObjectDescription>();
      for (final EDataType eDataType : IMPLICIT_ALIASES)
      {
        String instanceClassName = eDataType.getInstanceClassName();
        final QualifiedName actualQualifiedName = QualifiedName.create("org", "eclipse", "emf", "ecore", eDataType.getName());
        final QualifiedName qualifiedName = nameConverter.toQualifiedName(instanceClassName);
        AbstractEObjectDescription eObjectDescription =
          new EcoreDataTypeAliasEObjectDescription(qualifiedName, actualQualifiedName, eDataType);
        result.add(eObjectDescription);
      }
      return result;
    }
  }

  public static Resource getXcoreLangResource(ResourceSet resourceSet)
  {
    Resource xcoreLangResource = resourceSet.getResource(XAnnotationDirectiveScope.LOGICAL_XCORE_LANG_URI, false);
    if (xcoreLangResource == null)
    {
      xcoreLangResource = resourceSet.getResource(XAnnotationDirectiveScope.PHYSICAL_XCORE_LANG_URI, true);
      xcoreLangResource.setURI(XAnnotationDirectiveScope.LOGICAL_XCORE_LANG_URI);
    }
    return xcoreLangResource;
  }

  protected static class XAnnotationDirectiveScope extends AbstractScope
  {
    private static final URI LOGICAL_XCORE_LANG_URI = URI.createURI("platform:/resource/org.eclipse.emf.ecore.xcore/model/XcoreLang.xcore");

    private static final URI PHYSICAL_XCORE_LANG_URI =
       EMFPlugin.IS_ECLIPSE_RUNNING ?
         LOGICAL_XCORE_LANG_URI :
         URI.createURI(XcorePlugin.INSTANCE.getBaseURL().toString() + "model/XcoreLang.xcore");

    protected static final String[] IMPLICIT_ANNOTATION_DIRECTIVES =
      {
        "Ecore", EcorePackage.eNS_URI,
        "ExtendedMetaData", ExtendedMetaData.ANNOTATION_URI,
        "GenModel", GenModelPackage.eNS_URI
      };

    private ResourceSet resourceSet;

    public XAnnotationDirectiveScope(IScope parent, ResourceSet resourceSet)
    {
      super(parent, false);
      this.resourceSet = resourceSet;
    }

    @Override
    protected Iterable<IEObjectDescription> getAllLocalElements()
    {
      ArrayList<IEObjectDescription> result = new ArrayList<IEObjectDescription>();
      for (int i = 0; i < IMPLICIT_ANNOTATION_DIRECTIVES.length; i += 2)
      {
        final String name = IMPLICIT_ANNOTATION_DIRECTIVES[i];
        final int index = i;
        final QualifiedName actualQualifiedName = QualifiedName.create("xcore", "lang",name);
        final QualifiedName qualifiedName = QualifiedName.create(name);
        class AnnotationDescription extends AbstractEObjectDescription
        {
          protected QualifiedName name;
          
          public AnnotationDescription(QualifiedName name)
          {
            this.name = name;
          }

          public QualifiedName getQualifiedName()
          {
            return actualQualifiedName;
          }

          public QualifiedName getName()
          {
            return name;
          }

          public URI getEObjectURI()
          {
            IEObjectDescription element = getElement();
            return element == null ? getSyntheticEObjectURI() : element.getEObjectURI();
          }

          public EObject getEObjectOrProxy()
          {
            IEObjectDescription element = getElement();
            if (element == null)
            {
              InternalEObject xAnnotationDirective = (InternalEObject)XcoreFactory.eINSTANCE.createXAnnotationDirective();
              xAnnotationDirective.eSetProxyURI(getSyntheticEObjectURI());
              return xAnnotationDirective;
            }
            else
            {
              return element.getEObjectOrProxy();
            }
          }

          public EClass getEClass()
          {
            return XcorePackage.Literals.XANNOTATION_DIRECTIVE;
          }

          protected URI getSyntheticEObjectURI()
          {
            // TODO
            return LOGICAL_XCORE_LANG_URI.appendFragment("/0/@annotationDirectives." + index/2);
          }

          protected IEObjectDescription getElement()
          {
            IEObjectDescription element = getParent().getSingleElement(actualQualifiedName);
            if (element == null)
            {
              getXcoreLangResource(resourceSet);
            }
            return element;
          }
        }
        result.add(new AnnotationDescription(qualifiedName));
        result.add(new AnnotationDescription(actualQualifiedName));
      }
      return result;
    }
  }
}
