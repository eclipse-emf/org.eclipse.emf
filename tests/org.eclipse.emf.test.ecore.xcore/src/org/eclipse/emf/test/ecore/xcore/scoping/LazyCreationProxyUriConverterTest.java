package org.eclipse.emf.test.ecore.xcore.scoping;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.emf.ecore.xcore.scoping.LazyCreationProxyUriConverter;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.util.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

import static junit.framework.Assert.*;

@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
public class LazyCreationProxyUriConverterTest
{
	@Inject LazyCreationProxyUriConverter converter;

	@Test
	public void testUriConversion_1() throws Exception
	{
		LazyCreationProxyUriConverter converter = getProxyUriConverter();

		GenClass genClass = GenModelFactory.eINSTANCE.createGenClass();
		final QualifiedName name = QualifiedName.create("foo.bar", "Baz");
		converter.installProxyURI(URI.createFileURI("foo.test"), genClass, name);

		Pair<EClass, QualifiedName> proxyInfo = converter.decodeProxy(genClass);
		assertSame(genClass.eClass(), proxyInfo.getFirst());
		assertEquals(name.toString(), proxyInfo.getSecond().toString());
	}

	protected LazyCreationProxyUriConverter getProxyUriConverter() {
		return converter;
	}

	@Test
	public void testUriConversion_2() throws Exception
	{
		LazyCreationProxyUriConverter converter = getProxyUriConverter();

		EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		final QualifiedName name = QualifiedName.create("foo.bar", "Baz");
		converter.installProxyURI(URI.createFileURI("foo.test"), eClass, name);

		Pair<EClass, QualifiedName> proxyInfo = converter.decodeProxy(eClass);
		assertSame(eClass.eClass(), proxyInfo.getFirst());
		assertEquals(name.toString(), proxyInfo.getSecond().toString());
	}

	@Test
	public void testUriConversion_3() throws Exception
	{
		LazyCreationProxyUriConverter converter = getProxyUriConverter();

		JvmGenericType genericType = TypesFactory.eINSTANCE.createJvmGenericType();
		final QualifiedName name = QualifiedName.create("foo.bar", "Baz");
		converter.installProxyURI(URI.createFileURI("foo.test"), genericType, name);

		Pair<EClass, QualifiedName> proxyInfo = converter.decodeProxy(genericType);
		assertSame(genericType.eClass(), proxyInfo.getFirst());
		assertEquals(name.toString(), proxyInfo.getSecond().toString());
	}

	@Test
	public void testUriConversion_4() throws Exception
	{
		LazyCreationProxyUriConverter converter = getProxyUriConverter();

		EOperation op = EcoreFactory.eINSTANCE.createEOperation();
		final QualifiedName name = QualifiedName.create("foo.bar", "Baz");
		try
		{
			converter.installProxyURI(URI.createFileURI("foo.test"), op, name);
			fail();
		} catch (IllegalArgumentException e)
		{

		}
	}

	@Test
	public void testUriConversion_5() throws Exception
	{
		LazyCreationProxyUriConverter converter = getProxyUriConverter();

		JvmGenericType genericType = TypesFactory.eINSTANCE.createJvmGenericType();
		try
		{
			converter.decodeProxy(genericType);
			fail();
		} catch (IllegalArgumentException e)
		{

		}
	}
}
