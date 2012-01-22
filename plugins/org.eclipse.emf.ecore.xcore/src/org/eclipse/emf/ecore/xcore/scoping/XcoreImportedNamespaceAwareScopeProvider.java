/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.EMFPlugin;
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
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.ISelectable;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractScope;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.scoping.impl.ImportScope;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;
import org.eclipse.xtext.util.Strings;

import com.google.common.collect.Iterables;
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
        new ImportScope
          (getImplicitImports(false),
           new EClassifierScope(resourceScope, context.eResource().getResourceSet(), nameConverter, ecoreXcoreBuilderProvider, jvmInferrer),
           null,
           GenModelPackage.Literals.GEN_BASE,
           false);
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
            if (result == null)
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
      List<ImportNormalizer> importedNamespaceResolvers = super.internalGetImportedNamespaceResolvers(context, ignoreCase);
      String name = ((XPackage)context).getName();
      if (!Strings.isEmpty(name))
      {
        importedNamespaceResolvers.add(new ImportNormalizer(nameConverter.toQualifiedName(name), true, ignoreCase));
      }
      return importedNamespaceResolvers;
    }
  }

  @Override
  protected boolean isRelativeImport()
  {
    return false;
  }

  @Override
  protected List<ImportNormalizer> getImplicitImports(boolean ignoreCase)
  {
    return Collections.singletonList(new ImportNormalizer(nameConverter.toQualifiedName("java.lang"), true, ignoreCase));
  }

  @Override
  protected ISelectable internalGetAllDescriptions(Resource resource)
  {
    IResourceDescription description = manager.getResourceDescription(resource);
    return description;
  }

  protected static class EClassifierScope extends AbstractScope
  {
    private static final URI ECORE_XCORE_URI = URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/Ecore.xcore");

    private static final URI ECORE_GEN_MODEL_URI =
       EMFPlugin.IS_ECLIPSE_RUNNING ?
         URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/Ecore.genmodel") :
         URI.createURI(EcorePlugin.INSTANCE.getBaseURL().toString() + "model/Ecore.genmodel");

    protected static final EDataType[] IMPLICIT_ALIASES =
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
          new AbstractEObjectDescription()
          {
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
                InternalEObject genDataType = (InternalEObject)GenModelFactory.eINSTANCE.createGenDataType();
                genDataType.eSetProxyURI(getSyntheticEObjectURI());
                return genDataType;
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
                Resource ecoreXcoreResource = resourceSet.getResource(ECORE_XCORE_URI, false);
                if (ecoreXcoreResource == null)
                {
                  Resource genModelResource = resourceSet.getResource(ECORE_GEN_MODEL_URI, true);
                  GenModel genModel = (GenModel)genModelResource.getContents().get(0);
                  ecoreXcoreResource = resourceSet.getResource(ECORE_XCORE_URI, false);
                  EPackage ePackage = genModel.getGenPackages().get(0).getEcorePackage();
                  Resource ecoreResource = ePackage.eResource();
                  EcoreXcoreBuilder ecoreXcoreBuilder = ecoreXcoreBuilderProvider.get();
                  ecoreXcoreBuilder.initialize(genModel);
                  EcoreUtil.resolveAll(genModel);
                  XPackage xPackage = ecoreXcoreBuilder.getXPackage(ePackage);
                  ecoreXcoreResource = resourceSet.createResource(ECORE_XCORE_URI);
                  ecoreXcoreResource.getContents().add(xPackage);
                  ecoreXcoreResource.getContents().add(genModel);
                  ecoreXcoreResource.getContents().add(ePackage);
                  ecoreXcoreBuilder.link();
                  ecoreXcoreResource.getContents().addAll(jvmInferrer.getDeclaredTypes(xPackage));
                  resourceSet.getResources().remove(genModelResource);
                  resourceSet.getResources().remove(ecoreResource);
                }
              }
              return element;
            }
          };
        result.add(eObjectDescription);
      }
      return result;
    }
  }

  protected static class XAnnotationDirectiveScope extends AbstractScope
  {
    private static final URI LOGICAL_XCORE_LANG_URI = URI.createURI("platform:/plugin/org.eclipse.emf.ecore.xcore/model/XcoreLang.xcore");

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
        final String sourceURI = IMPLICIT_ANNOTATION_DIRECTIVES[i + 1];
        final int index = i;
        final QualifiedName actualQualifiedName = QualifiedName.create("xcore", "lang",name);
        final QualifiedName qualifiedName = QualifiedName.create(name);
        AbstractEObjectDescription eObjectDescription =
          new AbstractEObjectDescription()
          {
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
              return LOGICAL_XCORE_LANG_URI.appendFragment("//@annotationDirectives." + index/2);
            }

            protected IEObjectDescription getElement()
            {
              IEObjectDescription element = getParent().getSingleElement(actualQualifiedName);
              if (element == null)
              {
                Resource xcoreLangResource = resourceSet.getResource(LOGICAL_XCORE_LANG_URI, false);
                if (xcoreLangResource == null)
                {
                  xcoreLangResource = resourceSet.getResource(PHYSICAL_XCORE_LANG_URI, true);
                  xcoreLangResource.setURI(LOGICAL_XCORE_LANG_URI);
                }
              }
              return element;
            }
          };
        result.add(eObjectDescription);
      }
      return result;
    }
  }
}
