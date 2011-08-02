
package org.eclipse.emf.ecore.xcore;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;

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
		super.register(injector);
	}
}

