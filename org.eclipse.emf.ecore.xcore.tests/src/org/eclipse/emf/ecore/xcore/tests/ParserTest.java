package org.eclipse.emf.ecore.xcore.tests;

import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

import static org.junit.Assert.*;

@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
public class ParserTest {
	
	@Inject
	private ParseHelper<XPackage> parser;

	@Test
	public void parseSimpleFile() throws Exception {
		XPackage parse = parser.parse("package foo");
		assertEquals("foo", parse.getName());
	}

}
