package org.eclipse.emf.ecore.xcore.resource;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
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

public class XcoreResource extends XbaseResource {
	
	@Inject
	private LazyCreationProxyUriConverter proxyConverter;
	
	@Inject
	private IQualifiedNameProvider nameProvider; 
	
  @Inject
  private IScopeProvider scopeProvider;

	
	@Override
	public synchronized EObject getEObject(String uriFragment) {
		Pair<EClass,QualifiedName> fragmentInfo = proxyConverter.decodeFragment(uriFragment);
		if (fragmentInfo != null) {
			return findEObject(fragmentInfo.getFirst(), fragmentInfo.getSecond(), uriFragment);
		} else {
			return super.getEObject(uriFragment);
		}
	}

	/**
	 * finds the EObject of the given type and the given {@link QualifiedName}.
	 * 
	 * TODO optimize
	 */
	protected EObject findEObject(EClass clazz, QualifiedName name, String uriFragment) {
		if (clazz == TypesPackage.Literals.JVM_GENERIC_TYPE) {
			IScope scope = scopeProvider.getScope(getContents().get(0), TypesPackage.Literals.JVM_PARAMETERIZED_TYPE_REFERENCE__TYPE);
			final IEObjectDescription desc = scope.getSingleElement(name);
			if (desc != null && !uriFragment.equals(desc.getEObjectURI().fragment()))
				return desc.getEObjectOrProxy();
		}
		TreeIterator<EObject> iterator = EcoreUtil.getAllContents(this, false);
		while (iterator.hasNext()) {
			EObject candidate = iterator.next();
			if (clazz.isInstance(candidate)) {
				QualifiedName qualifiedName = nameProvider.getFullyQualifiedName(candidate);
				if (name.equals(qualifiedName))
					return candidate;
			}
		}
		return null;
	}
	
}
