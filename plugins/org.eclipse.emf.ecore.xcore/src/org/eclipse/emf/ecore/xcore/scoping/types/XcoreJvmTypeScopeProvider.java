package org.eclipse.emf.ecore.xcore.scoping.types;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xcore.XImportDirective;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.access.IJvmTypeProvider;
import org.eclipse.xtext.common.types.xtext.AbstractTypeScope;
import org.eclipse.xtext.common.types.xtext.AbstractTypeScopeProvider;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.util.IResourceScopeCache;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.imports.IImportsConfiguration;
import org.eclipse.xtext.xbase.scoping.AbstractNestedTypeAwareImportNormalizer;
import org.eclipse.xtext.xbase.scoping.XImportSectionNamespaceScopeProvider;
import org.eclipse.xtext.xtype.XImportDeclaration;
import org.eclipse.xtext.xtype.XImportSection;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class XcoreJvmTypeScopeProvider extends XImportSectionNamespaceScopeProvider
{
  private static final ImportNormalizer JAVA_LANG_IMPORT_NORMALIZER = AbstractNestedTypeAwareImportNormalizer.createNestedTypeAwareImportNormalizer(JAVA_LANG, true, false);

  @Inject
  private IResourceScopeCache cache;

  @Inject
  private AbstractTypeScopeProvider typeScopeProvider;

  @Inject
  private IQualifiedNameConverter qualifiedNameConverter;

  @Inject
  private IImportsConfiguration importsConfiguration;

  @Override
  public IScope getScope(final EObject context, EReference reference)
  {
    if (context instanceof XImportDirective)
    {
      return cache.get("import.type.scope", context.eResource(), new Provider<IScope>()
      {
        public IScope get()
        {
          Resource resource = context.eResource();
          IJvmTypeProvider typeProvider = typeScopeProvider.getTypeProvider(resource.getResourceSet());
          return typeScopeProvider.createTypeScope(typeProvider, null);
        }

      });
    }

    final XPackage xPackage = (XPackage) context;
    return cache.get("type.scope", context.eResource(), new Provider<IScope>()
    {
      public IScope get()
      {
        IJvmTypeProvider typeProvider = typeScopeProvider.getTypeProvider(xPackage.eResource().getResourceSet());
        AbstractTypeScope typeScope = typeScopeProvider.createTypeScope(typeProvider, null);
        AbstractXcoreScope rootTypeScope = getRootTypeScope(xPackage, typeScope);
        AbstractXcoreScope importScope = getImportScope(xPackage, rootTypeScope, typeScope);
        AbstractXcoreScope localTypes = getResourceTypeScope(xPackage.eResource(), xPackage.getName(), importScope);
        AbstractXcoreScope primitiveAware = new PrimitiveAwareScope(localTypes, typeScope);
        AbstractXcoreScope caching = new CachingTypeScope(primitiveAware);
        return caching;
      }
    });
  }

  protected AbstractXcoreScope getRootTypeScope(XPackage rootContainer, AbstractTypeScope typeScope)
  {
    String packageName = rootContainer.getName();
    ImportNormalizer[] alwaysImported =
      {
        JAVA_LANG_IMPORT_NORMALIZER
     };
    final ImportNormalizer[][] implicitImports;
    if (Strings.isEmpty(packageName))
    {
      implicitImports = new ImportNormalizer[][]
      {
        alwaysImported
      };
    }
    else
    {
      QualifiedName packageQualifiedName = qualifiedNameConverter.toQualifiedName(packageName);
      implicitImports = new ImportNormalizer[][]
      {
        {
          AbstractNestedTypeAwareImportNormalizer.createNestedTypeAwareImportNormalizer(packageQualifiedName, true, false),
        },
        alwaysImported
      };
    }
    return new TypeScopeWithWildcardImports(implicitImports, typeScope);
  }

  private AbstractXcoreScope getImportScope(XPackage xPackage, AbstractXcoreScope parent, AbstractTypeScope typeScope)
  {
    XImportSection importSection = importsConfiguration.getImportSection((XtextResource) xPackage.eResource());
    EList<XImportDeclaration> importDeclarations = importSection.getImportDeclarations();
    if (importDeclarations.isEmpty())
    {
      return parent;
    }
    List<ImportNormalizer> wildcardImports = Lists.newArrayList();
    List<JvmType> concreteImports = Lists.newArrayList();
    for (XImportDeclaration importDeclaration : importDeclarations)
    {
      if (importDeclaration.getImportedNamespace().endsWith(getWildcard()))
      {
        String importedNamespace = importDeclaration.getImportedNamespace();
        importedNamespace = importedNamespace.substring(0, importedNamespace.length() - 2);
        QualifiedName qualifiedImportedNamespace = qualifiedNameConverter.toQualifiedName(importedNamespace);
        wildcardImports.add(AbstractNestedTypeAwareImportNormalizer.createNestedTypeAwareImportNormalizer(qualifiedImportedNamespace, true, false));
      }
      else
      {
        JvmDeclaredType importedType = importDeclaration.getImportedType();
        if (importedType != null && !importedType.eIsProxy())
        {
          concreteImports.add(importedType);
        }
      }
    }
    return getImportScope(wildcardImports, concreteImports, parent, typeScope);
  }

  private AbstractXcoreScope getImportScope(List<ImportNormalizer> wildcardImports, List<JvmType> concreteImports, AbstractXcoreScope parent, AbstractTypeScope typeScope)
  {
    AbstractXcoreScope result = parent;
    if (!wildcardImports.isEmpty())
    {
      result = new TypeScopeWithWildcardImports(wildcardImports, typeScope, result);
    }
    if (!concreteImports.isEmpty())
    {
      result = new KnownTypesScope(concreteImports, result);
    }
    return result;
  }

  private AbstractXcoreScope getResourceTypeScope(Resource resource, String packageName, AbstractXcoreScope parent)
  {
    List<JvmDeclaredType> knownTypes = Lists.newArrayList(importsConfiguration.getLocallyDefinedTypes((XtextResource) resource));
    return knownTypes.isEmpty() ? parent : new KnownTypesScope(knownTypes, parent);
  }
}
