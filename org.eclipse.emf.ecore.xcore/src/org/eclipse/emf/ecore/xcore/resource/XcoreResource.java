package org.eclipse.emf.ecore.xcore.resource;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.scoping.LazyCreationProxyUriConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.xbase.resource.XbaseResource;

import com.google.inject.Inject;

public class XcoreResource extends XbaseResource {
	
	@Inject
	private LazyCreationProxyUriConverter proxyConverter;
	
	@Inject
	private IQualifiedNameProvider nameProvider; 
	
	private boolean fullyInitialized = false;
	
	@Override
	public EList<EObject> getContents() {
		if (!isLoading && !fullyInitialized) {
			try {
				eSetDeliver(false);
				lateInitialize();
				fullyInitialized = true;
			} finally {
				eSetDeliver(true);
			}
		}
		return super.getContents();
	}
	
	@Override
	protected void updateInternalState(IParseResult parseResult) {
		fullyInitialized = false;
		super.updateInternalState(parseResult);
	}
	
	protected void lateInitialize() {
		
	}
	
	@Override
	public synchronized EObject getEObject(String uriFragment) {
		Pair<EClass,QualifiedName> fragmentInfo = proxyConverter.decodeFragment(uriFragment);
		if (fragmentInfo != null) {
			return findEObject(fragmentInfo.getFirst(), fragmentInfo.getSecond());
		} else {
			return super.getEObject(uriFragment);
		}
	}

	/**
	 * finds the EObject of the given type and the given {@link QualifiedName}.
	 * 
	 * TODO optimize
	 */
	protected EObject findEObject(EClass clazz, QualifiedName name) {
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
