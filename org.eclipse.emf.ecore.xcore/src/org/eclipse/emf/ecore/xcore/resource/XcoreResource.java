package org.eclipse.emf.ecore.xcore.resource;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.scoping.LazyCreationProxyUriConverter;
import org.eclipse.emf.ecore.xcore.util.XcoreEcoreBuilder;
import org.eclipse.emf.ecore.xcore.util.XcoreJvmInferrer;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.antlr.IReferableElementsUnloader;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.xbase.resource.XbaseResource;

import com.google.inject.Inject;

public class XcoreResource extends XbaseResource {
	
	@Inject
	private LazyCreationProxyUriConverter proxyConverter;
	
	@Inject
	private IQualifiedNameProvider nameProvider; 
	
  @Inject
  private IReferableElementsUnloader.GenericUnloader unloader;
	
	protected boolean fullyInitialized = false;
	
	@Override
	public EList<EObject> getContents() {
		if (!isLoading && !fullyInitialized) {
			try {
				eSetDeliver(false);
				isLoading = true;
				lateInitialize();
				fullyInitialized = true;
			} finally {
				isLoading = false;
				eSetDeliver(true);
			}
		}
		return super.getContents();
	}
	
	@Override
	protected void updateInternalState(IParseResult parseResult) {
    for (Iterator<EObject> i = getContents().iterator(); i.hasNext(); )
    {
      EObject eObject = i.next();
      if (eObject instanceof EPackage || eObject instanceof GenModel || eObject instanceof JvmGenericType)
      {
        unloader.unloadRoot(eObject);
        i.remove();
      }
    }
		fullyInitialized = false;
		super.updateInternalState(parseResult);
	}
	
	protected void lateInitialize() {
		if (getParseResult() != null && getParseResult().getRootASTElement() instanceof XPackage)
    {
			XPackage model = (XPackage) getParseResult().getRootASTElement();
      XcoreEcoreBuilder xcoreEcoreBuilder = new XcoreEcoreBuilder();
      EPackage ePackage = xcoreEcoreBuilder.getEPackage(model);
      super.getContents().add(ePackage);
      GenModel genModel =  GenModelFactory.eINSTANCE.createGenModel();
      genModel.initialize(Collections.singleton(ePackage));
      super.getContents().add(genModel);
      genModel.initialize();
      for (Iterator<EObject> i = genModel.eAllContents(); i.hasNext(); )
      {
        EObject eObject = i.next();
        if (eObject instanceof GenBase)
        {
          GenBase genBase = (GenBase)eObject;
          EModelElement eModelElement = genBase.getEcoreModelElement();
          if (eModelElement != null)
          {
            XcoreEcoreBuilder.map(eModelElement, (GenBase)eObject);
          }
        }
      }
      xcoreEcoreBuilder.link(); 
      super.getContents().addAll(new XcoreJvmInferrer().getDeclaredTypes(model));
    }
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
