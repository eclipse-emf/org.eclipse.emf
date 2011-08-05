package org.eclipse.emf.ecore.xcore.scoping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.util.XcoreEcoreBuilder;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.AbstractEObjectDescription;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractScope;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;
import org.eclipse.xtext.util.Strings;

import com.google.common.base.Predicate;
import com.google.inject.Inject;

public class XcoreImportedNamespaceAwareScopeProvider extends ImportedNamespaceAwareLocalScopeProvider
{
  @Inject
  private IQualifiedNameConverter nameConverter;
  
  @Override
  protected List<ImportNormalizer> internalGetImportedNamespaceResolvers(EObject context, boolean ignoreCase)
  {
    if (!(context instanceof XPackage))
      return Collections.emptyList();
    List<ImportNormalizer> importedNamespaceResolvers = super.internalGetImportedNamespaceResolvers(context, ignoreCase);
    String name = ((XPackage)context).getName();
    if (!Strings.isEmpty(name)) 
    {
      importedNamespaceResolvers.add(new ImportNormalizer(nameConverter.toQualifiedName(name), true, ignoreCase));
    }
    return importedNamespaceResolvers;
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
  
  protected static final EDataType [] IMPLICIT_ALIASES = 
    new EDataType[] 
    {
      EcorePackage.Literals.ESTRING
    };

  @Override
  protected IScope getGlobalScope(Resource context, EReference reference, Predicate<IEObjectDescription> filter)
  {
    final IScope globalScope = super.getGlobalScope(context, reference, filter);
    if (false && reference == XcorePackage.Literals.XGENERIC_TYPE__TYPE)
    {
      return
        new AbstractScope(globalScope, false)
        {
          @Override
          protected Iterable<IEObjectDescription> getAllLocalElements()
          {
            ArrayList<IEObjectDescription> result = new ArrayList<IEObjectDescription>();
            for (EDataType eDataType : IMPLICIT_ALIASES)
            {
              String instanceClassName = eDataType.getInstanceClassName();
              final QualifiedName actualQualifiedName = QualifiedName.create("org", "eclipse", "emf", "ecore", eDataType.getName());
              final QualifiedName qualifiedName = nameConverter.toQualifiedName(instanceClassName);
              result.add
                (new AbstractEObjectDescription()
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
                  	 return globalScope.getSingleElement(actualQualifiedName).getEObjectURI();
                   }
                     
                   public EObject getEObjectOrProxy()
                   {
                  	 return globalScope.getSingleElement(actualQualifiedName).getEObjectOrProxy();
                   }
                     
                   public EClass getEClass()
                   {
                     return EcorePackage.Literals.EDATA_TYPE;
                   }
                 });
             }
             return result;
           }
        };
    }
    else
    {
     return globalScope;
    }
  
  }
}
