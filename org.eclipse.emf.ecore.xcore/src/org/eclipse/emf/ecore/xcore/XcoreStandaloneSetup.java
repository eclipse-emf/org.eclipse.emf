
package org.eclipse.emf.ecore.xcore;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class XcoreStandaloneSetup extends XcoreStandaloneSetupGenerated{

	public static void doSetup() {
		new XcoreStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

