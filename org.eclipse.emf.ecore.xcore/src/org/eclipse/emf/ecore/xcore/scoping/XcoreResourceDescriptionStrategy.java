package org.eclipse.emf.ecore.xcore.scoping;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XDataType;
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
				createGenModelDescription(eObject.eResource().getURI(), acceptor, qn);
				createEcoreDescription(eObject.eResource().getURI(), acceptor, qn);
				createJvmTypesDescription(eObject.eResource().getURI(), acceptor, qn);
			}
			return false;
		}
		if (eObject instanceof XDataType) {
			QualifiedName qn = nameProvider.getFullyQualifiedName(eObject);
			if (qn != null) {
				GenDataType genDatatype = genFactory.createGenDataType();
				proxyTool.installProxyURI(eObject.eResource().getURI(), genDatatype, qn);
				acceptor.accept(EObjectDescription.create(qn, genDatatype));
			}
			return false;
		}
		return true;
	}

	protected void createJvmTypesDescription(URI resourceURI, IAcceptor<IEObjectDescription> acceptor, QualifiedName qn) {
		JvmGenericType theInterface = typesFactory.createJvmGenericType();
		proxyTool.installProxyURI(resourceURI, theInterface, qn);
		acceptor.accept(EObjectDescription.create(qn, theInterface));
		
		QualifiedName implClassName = QualifiedName.create(qn.toString()+"Impl"); 
		JvmGenericType theImplClass = typesFactory.createJvmGenericType();
		proxyTool.installProxyURI(resourceURI, theImplClass, implClassName);
		acceptor.accept(EObjectDescription.create(implClassName, theImplClass));
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
