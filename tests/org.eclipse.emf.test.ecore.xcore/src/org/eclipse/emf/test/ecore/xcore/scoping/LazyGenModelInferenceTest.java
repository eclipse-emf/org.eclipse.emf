package org.eclipse.emf.test.ecore.xcore.scoping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.emf.ecore.xcore.XcoreStandaloneSetup;
import org.eclipse.emf.ecore.xcore.resource.XcoreResource;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Manager;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.StringInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

@RunWith(XtextRunner.class)
@InjectWith(LazyGenModelInferenceTest.MyXcoreInjectorProvider.class)
public class LazyGenModelInferenceTest {

	@Inject
	private Provider<XtextResourceSet> resourceSetProvider;

	public static class MyXcoreInjectorProvider extends XcoreInjectorProvider {

		private Injector injector;

		@Override
		public Injector getInjector() {
			if (injector == null) {
				this.injector = new XcoreStandaloneSetup() {
					@Override
					public Injector createInjector() {
						return Guice.createInjector(new org.eclipse.emf.ecore.xcore.XcoreRuntimeModule() {
							public Class<? extends XtextResource> bindXtextResource() {
								return InspectableXcoreResource.class;
							}
						});
					}
				}.createInjectorAndDoEMFRegistration();
			}
			return injector;
		}

		@Override
		public void setupRegistry() {
			super.setupRegistry();
			if (injector != null) {
				new XcoreStandaloneSetup().register(injector);
			}
		}
	}

	public static class InspectableXcoreResource extends XcoreResource {

		public EList<EObject> getContentsUnsafe() {
			return contents;
		}
		
		public boolean isFullyInitialized() {
			return fullyInitialized;
		}

	}

	@Test
	public void testSetup() {
		XtextResourceSet resourceSet = resourceSetProvider.get();
		Resource resource = resourceSet.createResource(URI.createURI("foo.xcore"));
		assertTrue(resource.toString(), resource instanceof InspectableXcoreResource);
		assertNull(((InspectableXcoreResource) resource).getContentsUnsafe());
		assertFalse(((InspectableXcoreResource) resource).isFullyInitialized());
	}

	@Test
	public void testContentsWithoutDerived() throws IOException {
		XtextResourceSet resourceSet = resourceSetProvider.get();
		Resource resource = resourceSet.createResource(URI.createURI("foo.xcore"));
		resource.load(new StringInputStream("package foo.bar class Baz {}"), null);
		assertEquals(1, ((InspectableXcoreResource) resource).getContentsUnsafe().size());
		assertFalse(((InspectableXcoreResource) resource).isFullyInitialized());
	}

	@Test
	public void testContentsWithDerived() throws IOException {
		XtextResourceSet resourceSet = resourceSetProvider.get();
		Resource resource = resourceSet.createResource(URI.createURI("foo.xcore"));
		resource.load(new StringInputStream("package foo.bar class Baz {}"), null);
		assertTrue(1 < resource.getContents().size());
		assertTrue(((InspectableXcoreResource) resource).isFullyInitialized());
	}

	public void testResourceDescriptionManagerDoesNotResolve() throws IOException {
		XtextResourceSet resourceSet = resourceSetProvider.get();
		InspectableXcoreResource resource = (InspectableXcoreResource) resourceSet.createResource(URI.createURI("foo.xcore"));
		resource.load(new StringInputStream("package foo.bar class Baz {}"), null);
		Manager manager = resource.getResourceServiceProvider().getResourceDescriptionManager();
		IResourceDescription resourceDescription = manager.getResourceDescription(resource);

		Iterator<IEObjectDescription> eclass = resourceDescription.getExportedObjectsByType(EcorePackage.Literals.ECLASS).iterator();
		Iterator<IEObjectDescription> genclass = resourceDescription.getExportedObjectsByType(GenModelPackage.Literals.GEN_CLASS)
				.iterator();
		Iterator<IEObjectDescription> jvmTypes = resourceDescription.getExportedObjectsByType(TypesPackage.Literals.JVM_GENERIC_TYPE)
				.iterator();
		final String expected = "foo.bar.Baz";
		assertEquals(expected, eclass.next().getName().toString());
		assertFalse(eclass.hasNext());
		assertEquals(expected, genclass.next().getName().toString());
		assertFalse(genclass.hasNext());
		assertEquals(expected, jvmTypes.next().getName().toString());
		assertEquals(expected + "Impl", jvmTypes.next().getName().toString());
		assertFalse(genclass.hasNext());

		assertEquals(1, resource.getContentsUnsafe().size());
		assertFalse(((InspectableXcoreResource) resource).isFullyInitialized());
	}

}
