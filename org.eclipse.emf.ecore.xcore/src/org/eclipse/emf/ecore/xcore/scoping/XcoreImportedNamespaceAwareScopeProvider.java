package org.eclipse.emf.ecore.xcore.scoping;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;
import org.eclipse.xtext.util.Strings;

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
}
