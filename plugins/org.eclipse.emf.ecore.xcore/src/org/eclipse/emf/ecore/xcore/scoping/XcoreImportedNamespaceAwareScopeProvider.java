/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.singletonList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XImportDirective;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.mappings.XClassMapping;
import org.eclipse.emf.ecore.xcore.mappings.XOperationMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.scoping.types.XcoreJvmTypeScopeProvider;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.AbstractEObjectDescription;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.AbstractScope;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.scoping.impl.ImportScope;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;
import org.eclipse.xtext.scoping.impl.ScopeBasedSelectable;
import org.eclipse.xtext.scoping.impl.SimpleScope;
import org.eclipse.xtext.util.IResourceScopeCache;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.lib.Pair;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class XcoreImportedNamespaceAwareScopeProvider extends ImportedNamespaceAwareLocalScopeProvider
{
  @Inject
  private IQualifiedNameConverter nameConverter;

  @Inject
  private IQualifiedNameProvider nameProvider;

  @Inject
  private IResourceScopeCache cache;

  @Inject
  private XcoreJvmTypeScopeProvider typeScopeProvider;

  @Inject
  protected XcoreMapper mapper;

  @Override
  public IScope getScope(final EObject context, EReference reference)
  {
    if (reference == XcorePackage.Literals.XIMPORT_DIRECTIVE__IMPORTED_OBJECT)
    {
      final IScope jvmTypeScope = typeScopeProvider.getScope(context, reference);
      final IScope annotationScope = getAnnotationScope(context);
      final IScope classifierScope = getClassifierScope(context);
      return new ImportableObjectsScope(classifierScope, annotationScope, jvmTypeScope);
    }
    else if (reference == XcorePackage.Literals.XGENERIC_TYPE__TYPE)
    {
      return getClassifierScope(context);
    }
    else if (reference == XcorePackage.Literals.XANNOTATION__SOURCE)
    {
      return getAnnotationScope(context);
    }
    else if (TypesPackage.Literals.JVM_TYPE.isSuperTypeOf(reference.getEReferenceType()))
    {
      return getJvmTypeScope(context, reference);
    }
    else
    {
      throw new IllegalArgumentException("Scope for unknown reference " + reference + " requested");
    }
  }

  private IScope getJvmTypeScope(EObject context, EReference reference)
  {
    EObject eContainer = context.eContainer();
    if (eContainer instanceof XOperation)
    {
      XOperation xOperation = (XOperation)eContainer;
      XOperationMapping mapping = mapper.getMapping(xOperation);
      JvmOperation jvmOperation = mapping.getJvmOperation();
      EList<JvmTypeParameter> typeParameters = jvmOperation.getTypeParameters();
      return getJvmTypeScope(context, reference, typeParameters);
    }
    else if (eContainer instanceof XClass)
    {
      XClass xClass = (XClass)eContainer;
      XClassMapping mapping = mapper.getMapping(xClass);
      JvmGenericType jvmGenericType = mapping.getInterfaceType();
      EList<JvmTypeParameter> typeParameters = jvmGenericType.getTypeParameters();
      return getJvmTypeScope(context, reference, typeParameters);
    }
    else if (eContainer == null)
    {
      return typeScopeProvider.getScope(context, reference);
    }
    else
    {
      return getJvmTypeScope(eContainer, reference);
    }
  }

  private IScope getJvmTypeScope(final EObject context, final EReference reference, final EList<JvmTypeParameter> typeParameters)
  {
    final IScope parentScope = getJvmTypeScope(context.eContainer(), reference);
    if (typeParameters.isEmpty())
    {
      return parentScope;
    }
    else
    {
      final List<IEObjectDescription> descriptions = new ArrayList<IEObjectDescription>();
      for (JvmTypeParameter jvmTypeParameter : typeParameters)
      {
        descriptions.add(EObjectDescription.create(jvmTypeParameter.getName(), jvmTypeParameter));
      }

      return cache.get(new Pair<String, EObject>("typeParameters", context), context.eResource(), new Provider<IScope>()
      {
        public IScope get()
        {
          IScope typeParameterScope =
            new AbstractScope(parentScope, isIgnoreCase(reference))
            {
              @Override
              protected Iterable<IEObjectDescription> getAllLocalElements()
              {
                return descriptions;
              }
            };
          return new CachingScope(typeParameterScope);
        }
      });
    }
  }

  private IScope getAnnotationScope(final EObject context)
  {
    final boolean isImport = context instanceof XImportDirective;
    String cacheKey = isImport ? "import.annotation.scope" : "annotation.scope";
    final Resource resource = context.eResource();
    return cache.get(cacheKey, resource, new Provider<IScope>()
    {
      public IScope get()
      {
        IScope globalScope = getGlobalScope(resource, XcorePackage.Literals.XANNOTATION__SOURCE);
        ImportNormalizer xcoreLang = new ImportNormalizer(nameConverter.toQualifiedName("xcore.lang"), true, false);
        ImportScope xcoreLangScope = createImportScope(globalScope, singletonList(xcoreLang), null, XcorePackage.Literals.XANNOTATION_DIRECTIVE, false);
        List<ImportNormalizer> importedNamespaceResolvers = isImport ? Collections.<ImportNormalizer>emptyList() : getImportedNamespaceResolvers(EcoreUtil.getRootContainer(context), false);
        ImportScope importScope = createImportScope(xcoreLangScope, importedNamespaceResolvers, null, XcorePackage.Literals.XANNOTATION_DIRECTIVE, false);
        IScope resourceScope = getLocalAnnotationsScope(resource, importScope);
        return new CachingScope(resourceScope);
      }
    });
  }

  private IScope getClassifierScope(final EObject context)
  {
    final boolean isImport = context instanceof XImportDirective;
    String cacheKey = isImport ? "import.classifier.scope" : "classifier.scope";
    final Resource resource = context.eResource();
    return cache.get(cacheKey, resource, new Provider<IScope>()
    {
      public IScope get()
      {
        IScope globalScope = getGlobalScope(resource, XcorePackage.Literals.XGENERIC_TYPE__TYPE, new Predicate<IEObjectDescription>()
        {
          public boolean apply(IEObjectDescription input)
          {
            EClass eClass = input.getEClass();
            return GenModelPackage.Literals.GEN_CLASSIFIER.isSuperTypeOf(eClass) || eClass == GenModelPackage.Literals.GEN_ANNOTATION || eClass == GenModelPackage.Literals.GEN_TYPE_PARAMETER;
          }
        });
        EcoreDataTypeAliasingScope aliasingScope = new EcoreDataTypeAliasingScope(globalScope, nameConverter);

        List<ImportNormalizer> importedNamespaceResolvers = Collections.emptyList();
        if (!isImport)
        {
          importedNamespaceResolvers = Lists.newArrayList(getImportedNamespaceResolvers(EcoreUtil.getRootContainer(context), false));
          Set<String> names = new HashSet<String>();
          for (ImportNormalizer importNormalizer : importedNamespaceResolvers)
          {
            if (!importNormalizer.hasWildCard())
            {
              names.add(importNormalizer.getImportedNamespacePrefix().getLastSegment());
            }
          }
  
          for (String implicitImport : IMPLICIT_IMPORTS)
          {
            ImportNormalizer importedNamespaceResolver = createImportedNamespaceResolver(implicitImport, false);
            if (!names.contains(importedNamespaceResolver.getImportedNamespacePrefix().getLastSegment()))
            {
              importedNamespaceResolvers.add(importedNamespaceResolver);
            }
          }
        }

        ImportScope importScope = createImportScope(aliasingScope, importedNamespaceResolvers, null, GenModelPackage.Literals.GEN_BASE, false);

        IScope resourceScope = getLocalClassifiersScope(resource, importScope);
        return new CachingScope(resourceScope);
      }
    });
  }

  /*
   * This is much faster than creating resource descriptions for all elements and then filtering out the interesting ones.
   */
  private IScope getLocalAnnotationsScope(Resource resource, IScope parent)
  {
    XPackage xPackage = (XPackage)resource.getContents().get(0);
    List<XAnnotationDirective> directives = xPackage.getAnnotationDirectives();
    return directives.isEmpty() ? parent : createLocalElementsScope(parent, directives, xPackage, GenModelPackage.Literals.GEN_BASE);
  }

  /*
   * This is much faster than creating resource descriptions for all elements and then filtering out the interesting ones.
   */
  private IScope getLocalClassifiersScope(Resource resource, IScope parent)
  {
    EList<EObject> contents = resource.getContents();
    XPackage xPackage = (XPackage)contents.get(0);
    List<GenClassifier> classifiers = newArrayList();
    EList<GenPackage> genPackages = ((GenModel)contents.get(1)).getGenPackages();
    for (GenPackage genPackage : genPackages)
    {
      classifiers.addAll(genPackage.getGenClassifiers());
    }
    return classifiers.isEmpty() ? parent : createLocalElementsScope(parent, classifiers, xPackage, GenModelPackage.Literals.GEN_BASE);
  }

  private IScope createLocalElementsScope(IScope parent, List<? extends EObject> elements, XPackage xPackage, EClass type)
  {
    List<IEObjectDescription> eagerlyResolvedDescriptions = newArrayList(Scopes.scopedElementsFor(elements, nameProvider));
    IScope localScope = new SimpleScope(eagerlyResolvedDescriptions);
    String name = xPackage.getName();
    if (Strings.isEmpty(name))
    {
      return localScope;
    }
    ImportNormalizer localNormalizer = doCreateImportNormalizer(nameConverter.toQualifiedName(name), true, false);
    return createImportScope(parent, singletonList(localNormalizer), new ScopeBasedSelectable(localScope), type, false);
  }

  /*
   * This creates the same result as the super implementation,
   * but much faster since it knows where to look for imports instead of iterating all contents
   */
  @Override
  protected List<ImportNormalizer> internalGetImportedNamespaceResolvers(EObject context, boolean ignoreCase)
  {
    if (context instanceof XPackage)
    {
      List<ImportNormalizer> importedNamespaceResolvers = Lists.newArrayList();
      XPackage xPackage = (XPackage)context;
      for (XImportDirective xImportDirective : xPackage.getImportDirectives())
      {
        ImportNormalizer normalizer = createImportedNamespaceResolver(xImportDirective.getImportedNamespace(), ignoreCase);
        if (normalizer != null)
        {
          importedNamespaceResolvers.add(normalizer);
        }
      }
      return importedNamespaceResolvers;
    }
    return Collections.emptyList();
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

  private static final String[] IMPLICIT_IMPORTS;

  static
  {
    List<String> implicitImports = Lists.newArrayList();
    for (EDataType eDataType : IMPLICIT_ALIASES)
    {
      String instanceClassName = eDataType.getInstanceClassName();
      if (instanceClassName.indexOf('.') != -1 && !instanceClassName.startsWith("java.lang."))
      {
        implicitImports.add(instanceClassName);
      }
    }

    implicitImports.add("java.lang.*");

    IMPLICIT_IMPORTS = implicitImports.toArray(new String[implicitImports.size()]);
  }

  private static final class ImportableObjectsScope implements IScope
  {
    private final IScope classifierScope;
    private final IScope jvmTypeScope;
    private final IScope annotationScope;

    private ImportableObjectsScope(IScope classifierScope, IScope annotationScope, IScope jvmTypeScope)
    {
      this.classifierScope = classifierScope;
      this.jvmTypeScope = jvmTypeScope;
      this.annotationScope = annotationScope;
    }

    public IEObjectDescription getSingleElement(QualifiedName name)
    {
      IEObjectDescription result = classifierScope.getSingleElement(name);
      if (result == null || result instanceof EcoreDataTypeAliasingScope.EcoreDataTypeAliasEObjectDescription)
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
  }

  private static class EcoreDataTypeAliasingScope extends AbstractScope
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
        return ECORE_GEN_MODEL_URI.appendFragment("//ecore/" + eDataType.getName());
      }

      protected IEObjectDescription getElement()
      {
        IEObjectDescription element = getParent().getSingleElement(actualQualifiedName);
        return element;
      }
    }

    private static final URI ECORE_GEN_MODEL_URI = URI.createURI("platform:/resource/org.eclipse.emf.ecore/model/Ecore.genmodel");

    private IQualifiedNameConverter nameConverter;

    public EcoreDataTypeAliasingScope(IScope parent, IQualifiedNameConverter nameConverter)
    {
      super(parent, false);
      this.nameConverter = nameConverter;
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
        AbstractEObjectDescription qualifiedObjectDescription = new EcoreDataTypeAliasEObjectDescription(qualifiedName, actualQualifiedName, eDataType);
        result.add(qualifiedObjectDescription);
      }
      return result;
    }
  }

  private static final URI LOGICAL_XCORE_LANG_URI = URI.createURI("platform:/resource/org.eclipse.emf.ecore.xcore.lib/model/XcoreLang.xcore");
  private static final URI PHYSICAL_XCORE_LANG_URI = URI.createURI("platform:/plugin/org.eclipse.emf.ecore.xcore.lib/model/XcoreLang.xcore");

  public static Resource getXcoreLangResource(ResourceSet resourceSet)
  {
    Resource xcoreLangResource = resourceSet.getResource(LOGICAL_XCORE_LANG_URI, false);
    if (xcoreLangResource == null)
    {
      xcoreLangResource = resourceSet.getResource(PHYSICAL_XCORE_LANG_URI, true);
      xcoreLangResource.setURI(LOGICAL_XCORE_LANG_URI);
    }
    return xcoreLangResource;
  }

  private static class CachingScope extends AbstractScope
  {
    private final Map<QualifiedName, IEObjectDescription> cache;

    public CachingScope(IScope parent)
    {
      super(parent, true);
      this.cache = Maps.newHashMapWithExpectedSize(50);
    }

    @Override
    public IEObjectDescription getSingleElement(QualifiedName name)
    {
      IEObjectDescription cached = cache.get(name);
      if (cached == null)
      {
        if (cache.containsKey(name))
        {
          return null;
        }
        cached = getParent().getSingleElement(name);
        cache.put(name, cached);
      }
      return cached;
    }

    @Override
    public Iterable<IEObjectDescription> getElements(QualifiedName name)
    {
      IEObjectDescription element = getSingleElement(name);
      return element == null ? Collections.<IEObjectDescription>emptyList() : Collections.singletonList(element);
    }

    @Override
    protected Iterable<IEObjectDescription> getAllLocalElements()
    {
      return Collections.emptyList();
    }
  }
}
