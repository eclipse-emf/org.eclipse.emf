package org.eclipse.emf.ecore.xcore.scoping;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XEnum;
import org.eclipse.xtext.common.types.JvmEnumerationType;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy;
import org.eclipse.xtext.util.IAcceptor;

import com.google.inject.Inject;

public class XcoreResourceDescriptionStrategy extends DefaultResourceDescriptionStrategy {
	
	@Inject(optional=true)
	private TypesFactory typesFactory = TypesFactory.eINSTANCE;
	
	@Inject(optional=true)
	private EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
	
	@Inject(optional=true)
	private GenModelFactory genFactory = GenModelFactory.eINSTANCE;
	
	@Inject
	private LazyCreationProxyUriConverter proxyTool;
	
	@Inject
	private IQualifiedNameProvider nameProvider;
	
	@Override
	public boolean createEObjectDescriptions(EObject eObject, IAcceptor<IEObjectDescription> acceptor) {
		if (eObject instanceof XClass) {
			QualifiedName qn = nameProvider.getFullyQualifiedName(eObject);
			if (qn != null) {
		    URI uri = eObject.eResource().getURI();
				createGenModelDescription(uri, acceptor, qn);
				createEcoreDescription(uri, acceptor, qn);
				createJvmTypesDescription(uri, acceptor, qn);
			}
			return false;
		}
		if (eObject instanceof XDataType) {
			QualifiedName qn = nameProvider.getFullyQualifiedName(eObject);
			if (qn != null) {
		    URI uri = eObject.eResource().getURI();
				GenDataType genDatatype = genFactory.createGenDataType();
				proxyTool.installProxyURI(uri, genDatatype, qn);
				acceptor.accept(EObjectDescription.create(qn, genDatatype));
			  if (eObject instanceof XEnum)
			  {
				  createJvmEnumDescription(uri, acceptor, qn);
			  }
			}
			return false;
		}
		if (eObject instanceof XAnnotationDirective)
		{
			QualifiedName qn = nameProvider.getFullyQualifiedName(eObject);
			if (qn != null) {
				acceptor.accept(EObjectDescription.create(qn, eObject));
			}
		}
		return true;
	}

	protected void createJvmTypesDescription(URI resourceURI, IAcceptor<IEObjectDescription> acceptor, QualifiedName qn) {
		JvmGenericType theInterface = typesFactory.createJvmGenericType();
		proxyTool.installProxyURI(resourceURI, theInterface, qn);
		acceptor.accept(EObjectDescription.create(qn, theInterface));
		
		// TODO This isn't right.
		// It's bad new have logic like this in multiple places.
		// We need to know the proper package name which only the GenPackage knows.
		// It's even possible that separate interface and implement classes is suppressed...
		//
		QualifiedName implClassName = QualifiedName.create(qn.toString()+"Impl"); 
		JvmGenericType theImplClass = typesFactory.createJvmGenericType();
		proxyTool.installProxyURI(resourceURI, theImplClass, implClassName);
		acceptor.accept(EObjectDescription.create(implClassName, theImplClass));
	}
	
	protected void createJvmEnumDescription(URI resourceURI, IAcceptor<IEObjectDescription> acceptor, QualifiedName qn) {
		JvmEnumerationType theEnum = typesFactory.createJvmEnumerationType();
		proxyTool.installProxyURI(resourceURI, theEnum, qn);
		acceptor.accept(EObjectDescription.create(qn, theEnum));
	}

	protected void createEcoreDescription(URI resourceURI, IAcceptor<IEObjectDescription> acceptor, QualifiedName qn) {
		EClass eclass = ecoreFactory.createEClass();
		proxyTool.installProxyURI(resourceURI, eclass, qn);
		acceptor.accept(EObjectDescription.create(qn, eclass));
	}

	protected void createGenModelDescription(URI resourceURI, IAcceptor<IEObjectDescription> acceptor, QualifiedName qn) {
		GenClass genClass = genFactory.createGenClass();
		proxyTool.installProxyURI(resourceURI, genClass, qn);
		acceptor.accept(EObjectDescription.create(qn, genClass));
	}

}
