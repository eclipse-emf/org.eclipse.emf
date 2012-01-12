package org.eclipse.emf.test.ecore.xcore.ecore;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.test.ecore.xcore.GenModelFormatter;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.parameterized.InjectParameter;
import org.eclipse.xtext.junit4.parameterized.Offset;
import org.eclipse.xtext.junit4.parameterized.ParameterizedXtextRunner;
import org.eclipse.xtext.junit4.parameterized.ResourceURIs;
import org.eclipse.xtext.junit4.parameterized.XpectString;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

@SuppressWarnings("restriction")
@InjectWith(XcoreInjectorProvider.class)
@RunWith(ParameterizedXtextRunner.class)
@ResourceURIs(baseDir = "src/org/eclipse/emf/test/ecore/xcore/ecore", fileExtensions = "xcore")
public class XcoreEcoreTest
{

	@Inject
	private XcoreMapper mapper;

	@InjectParameter
	private Offset offset;

	@InjectParameter
	private XtextResource resource;

	@Inject
	private ValidationTestHelper validationHelper;

	@Test
	public void noValidationIssues()
	{
		validationHelper.assertNoIssues(resource.getContents().get(0));
	}

	@XpectString
	public String eNamedElement()
	{
		EcoreUtil.resolveAll(resource);
		ENamedElement gen = mapper.getEcore((XNamedElement) offset.getEObject());
		return new GenModelFormatter().resolveCrossReferences().format(gen);
	}

}
