/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.test.codegen.ecore.xtext;

import java.util.Collections;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.xtext.GenModelRuntimeModule;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.generic.GenericResourceDescriptionManager;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Sven Efftinge - Initial contribution and API
 * @author Jan Koehnlein
 */
public class GenModelResourceDescriptionManagerTest extends TestCase {
	
	private GenericResourceDescriptionManager testMe;
	
	private IQualifiedNameConverter converter;

	private Resource ecoreGenModel;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Injector injector = Guice.createInjector(new GenModelRuntimeModule());
		testMe = injector.getInstance(GenericResourceDescriptionManager.class);
		converter = injector.getInstance(IQualifiedNameConverter.class);
		URI genmodelURI = URI.createURI(EcorePlugin.INSTANCE.getBaseURL().toExternalForm() + "model/Ecore.genmodel");
		GenModelPackage.eINSTANCE.eClass();
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new EcoreResourceFactoryImpl());
		ecoreGenModel = resourceSet.getResource(genmodelURI, true);
		assertNotNull(ecoreGenModel);
	}
	
	@Override
	protected void tearDown() throws Exception {
		testMe = null;
		converter = null;
		ecoreGenModel = null;
		super.tearDown();
	}
	
	public void testEcoreGenModel() throws Exception {
		Map<QualifiedName, IEObjectDescription> index = createIndex(ecoreGenModel);
		getAndCheckEntry(index, GenModelPackage.Literals.GEN_PACKAGE, "org.eclipse.emf.ecore");
		IEObjectDescription description = getAndCheckEntry(index, GenModelPackage.Literals.GEN_CLASS, "org.eclipse.emf.ecore.EClass");
		GenClass genClass = (GenClass) description.getEObjectOrProxy();
		assertEquals(EcorePackage.Literals.ECLASS.getName(), genClass.getEcoreClass().getName());
		// abstract types are available, too
		getAndCheckEntry(index, GenModelPackage.Literals.GEN_CLASS, "org.eclipse.emf.ecore.EClassifier");
		
		// GenStructuralFeatures are not indexed 
		assertNoEntry(index, "org.eclipse.emf.ecore.EClassifier.defaultValue");
		assertNoEntry(index, "org.eclipse.emf.ecore.EClassifier.getDefaultValue");
		assertNoEntry(index, "org.eclipse.emf.ecore.EClassifier.DefaultValue");
	}
	
	public void testNestedPackage() throws Exception {
		Resource ecoreResource = new XMIResourceImpl();
		Resource genmodelResource = new XMIResourceImpl();
		EPackage parent = EcoreFactory.eINSTANCE.createEPackage();
		parent.setName("parent");
		parent.setNsURI("http://parent");
		EPackage child = EcoreFactory.eINSTANCE.createEPackage();
		child.setName("child");
		child.setNsURI("http://child");
		EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		eClass.setName("Test");
		child.getEClassifiers().add(eClass);
		parent.getESubpackages().add(child);
		ecoreResource.getContents().add(parent);
		GenModel genModel = GenModelFactory.eINSTANCE.createGenModel();
		genModel.initialize(Collections.singleton(parent));
		genmodelResource.getContents().add(genModel);
		genModel.initialize();
		genModel.getGenPackages().get(0).setBasePackage("org.foo.bar");
		genModel.getGenPackages().get(0).getNestedGenPackages().get(0).setBasePackage("org.foo.zonk");
		Map<QualifiedName, IEObjectDescription> index = createIndex(genmodelResource);
		getAndCheckEntry(index, GenModelPackage.Literals.GEN_PACKAGE, "org.foo.bar.parent");
		getAndCheckEntry(index, GenModelPackage.Literals.GEN_PACKAGE, "org.foo.zonk.child");
		getAndCheckEntry(index, GenModelPackage.Literals.GEN_CLASS, "org.foo.zonk.child.Test");
		assertEquals(3, index.size());
	}

	protected IEObjectDescription getAndCheckEntry(Map<QualifiedName, IEObjectDescription> index, EClass expectedType, String name) {
		IEObjectDescription element = index.get(converter.toQualifiedName(name));
		assertNotNull(element);
		assertSame(expectedType, element.getEClass());
		return element;
	}
	
	protected void assertNoEntry(Map<QualifiedName, IEObjectDescription> index, String name) {
		IEObjectDescription element = index.get(converter.toQualifiedName(name));
		assertNull(element);
	}

	protected Map<QualifiedName, IEObjectDescription> createIndex(Resource ecoreResoure) {
		IResourceDescription description = testMe.getResourceDescription(ecoreResoure);
		Map<QualifiedName,IEObjectDescription> index = Maps.newHashMap();
		for (IEObjectDescription ieObjectDescription : description.getExportedObjects()) {
			index.put(ieObjectDescription.getName(), ieObjectDescription);
		}
		return index;
	}

}
