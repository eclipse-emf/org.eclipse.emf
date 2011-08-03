package org.eclipse.emf.ecore.xcore.scoping;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.TypesFactory;
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
	
	@Override
	public boolean createEObjectDescriptions(EObject eObject, IAcceptor<IEObjectDescription> acceptor) {
		if (eObject instanceof XClass) {
			final XClass clazz = (XClass) eObject;
			String name = clazz.getName();
			String packageName = clazz.getPackage().getName();
			QualifiedName qn = QualifiedName.create(packageName,name);
			createGenModelDescription(eObject, acceptor, qn);
			createEcoreDescription(eObject, acceptor, qn);
			createJvmTypesDescription(eObject, acceptor, qn);
			return false;
		}
		return true;
	}

	protected void createJvmTypesDescription(EObject eObject, IAcceptor<IEObjectDescription> acceptor, QualifiedName qn) {
		JvmGenericType theInterface = typesFactory.createJvmGenericType();
		proxyTool.installProxyURI(eObject.eResource().getURI(), theInterface, qn);
		acceptor.accept(EObjectDescription.create(qn, theInterface));
		
		QualifiedName implClassName = QualifiedName.create(qn.toString()+"Impl"); 
		JvmGenericType theImplClass = typesFactory.createJvmGenericType();
		proxyTool.installProxyURI(eObject.eResource().getURI(), theImplClass, implClassName);
		acceptor.accept(EObjectDescription.create(implClassName, theImplClass));
	}

	protected void createEcoreDescription(EObject eObject, IAcceptor<IEObjectDescription> acceptor, QualifiedName qn) {
		EClass eclass = ecoreFactory.createEClass();
		proxyTool.installProxyURI(eObject.eResource().getURI(), eclass, qn);
		acceptor.accept(EObjectDescription.create(qn, eclass));
	}

	protected void createGenModelDescription(EObject eObject, IAcceptor<IEObjectDescription> acceptor, QualifiedName qn) {
		GenClass genClass = genFactory.createGenClass();
		proxyTool.installProxyURI(eObject.eResource().getURI(), genClass, qn);
		acceptor.accept(EObjectDescription.create(qn, genClass));
	}

}
