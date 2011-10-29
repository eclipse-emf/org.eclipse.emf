package org.eclipse.emf.ecore.xcore.tests.genmodel;

import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.tests.GenModelFormatter;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.parameterized.OffsetHelper;
import org.eclipse.xtext.junit4.parameterized.Parameter;
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
@ResourceURIs(baseDir = "src/org/eclipse/emf/ecore/xcore/tests/genmodel", fileExtensions = "xcore")
public class XcoreGenModelTest
{

	@Inject
	private XcoreMapper mapper;
	@Inject
	private OffsetHelper offsetHelper;
	private Map<String, String> params;
	private XtextResource resource;
	@Inject
	private ValidationTestHelper validationHelper;

	public XcoreGenModelTest(XtextResource resource, Map<String, String> params)
	{
		this.resource = resource;
		this.params = params;
	}

	@Test
	public void noValidationIssues()
	{
		validationHelper.assertNoIssues(resource.getContents().get(0));
	}

	@XpectString
	@Parameter(syntax = "'at' offset=OFFSET")
	public String genBase()
	{
		EcoreUtil.resolveAll(resource);
		XNamedElement namedElement = (XNamedElement) offsetHelper.at(resource, params.get("offset")).getEObject();
		GenBase gen = mapper.getGen(namedElement);
		return new GenModelFormatter().resolveCrossReferences().format(gen);
	}

}
