
package org.eclipse.emf.ecore.xcore;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import com.google.inject.Injector;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class XcoreStandaloneSetup extends XcoreStandaloneSetupGenerated{

	public static void doSetup() {
		new XcoreStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
	
	@Override
	public void register(Injector injector) {
		Registry instance = injector.getInstance(EPackage.Registry.class);
		instance.put(XcorePackage.eNS_URI, XcorePackage.eINSTANCE);
		instance.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		instance.put(GenModelPackage.eNS_URI, GenModelPackage.eINSTANCE);
		instance.put(XMLTypePackage.eNS_URI, XMLTypePackage.eINSTANCE);
		instance.put(XMLNamespacePackage.eNS_URI, XMLNamespacePackage.eINSTANCE);
		super.register(injector);
	}
}

