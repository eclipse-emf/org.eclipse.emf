package org.eclipse.emf.ecore.xcore.resource;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.scoping.LazyCreationProxyUriConverter;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.xbase.resource.XbaseResource;

import com.google.inject.Inject;

public class XcoreResource extends XbaseResource
{
	@Inject
	private LazyCreationProxyUriConverter proxyConverter;

	@Inject
	private IQualifiedNameProvider nameProvider;

	@Inject
	private IScopeProvider scopeProvider;

	@Override
	public synchronized EObject getEObject(String uriFragment)
	{
		Pair<EClass, QualifiedName> fragmentInfo = proxyConverter.decodeFragment(uriFragment);
		if (fragmentInfo != null)
		{
			return findEObject(fragmentInfo.getFirst(), fragmentInfo.getSecond(), uriFragment);
		} 
		else
		{
			return super.getEObject(uriFragment);
		}
	}

	/**
	 * finds the EObject of the given type and the given {@link QualifiedName}.
	 * 
	 * TODO optimize
	 */
	protected EObject findEObject(EClass eClass, QualifiedName name, String uriFragment)
	{
		if (eClass == TypesPackage.Literals.JVM_ENUMERATION_TYPE || eClass == GenModelPackage.Literals.GEN_CLASS
		    || eClass == GenModelPackage.Literals.GEN_DATA_TYPE || eClass == GenModelPackage.Literals.GEN_ENUM
		    || eClass == TypesPackage.Literals.JVM_GENERIC_TYPE)
		{
			IScope scope = scopeProvider.getScope(getContents().get(0),
			    eClass.getEPackage() == GenModelPackage.eINSTANCE ? XcorePackage.Literals.XGENERIC_TYPE__TYPE : TypesPackage.Literals.JVM_PARAMETERIZED_TYPE_REFERENCE__TYPE);
			final IEObjectDescription desc = scope.getSingleElement(name);
			if (desc != null)
			{
				URI uri = desc.getEObjectURI();
				if (!uriFragment.equals(uri.fragment()) || !uri.trimFragment().equals(getURI()))
					return desc.getEObjectOrProxy();
			}
		}
		TreeIterator<EObject> iterator = EcoreUtil.getAllContents(this, false);
		while (iterator.hasNext())
		{
			EObject candidate = iterator.next();
			if (eClass.isInstance(candidate))
			{
				QualifiedName qualifiedName = nameProvider.getFullyQualifiedName(candidate);
				if (name.equals(qualifiedName))
					return candidate;
			}
		}
		return null;
	}

	@Override
	public String getURIFragment(EObject object)
	{
		EClass eClass = object.eClass();
		if (eClass == TypesPackage.Literals.JVM_ENUMERATION_TYPE || eClass == GenModelPackage.Literals.GEN_CLASS
		    || eClass == GenModelPackage.Literals.GEN_DATA_TYPE || eClass == GenModelPackage.Literals.GEN_ENUM
		    || eClass == TypesPackage.Literals.JVM_GENERIC_TYPE)
		{
			QualifiedName qualifiedName = nameProvider.getFullyQualifiedName(object);
      if (qualifiedName != null)
      {
			  return proxyConverter.encodeFragment(eClass, qualifiedName);
      }
		} 
    return super.getURIFragment(object);
	}

}
