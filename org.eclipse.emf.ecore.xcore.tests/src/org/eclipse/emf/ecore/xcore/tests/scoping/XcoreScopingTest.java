package org.eclipse.emf.ecore.xcore.tests.scoping;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.parameterized.OffsetHelper;
import org.eclipse.xtext.junit4.parameterized.Parameter;
import org.eclipse.xtext.junit4.parameterized.ParameterizedXtextRunner;
import org.eclipse.xtext.junit4.parameterized.ResourceURIs;
import org.eclipse.xtext.junit4.parameterized.XpectCommaSeparatedValues;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

@SuppressWarnings("restriction")
@InjectWith(XcoreInjectorProvider.class)
@RunWith(ParameterizedXtextRunner.class)
@ResourceURIs(baseDir = "src/org/eclipse/emf/ecore/xcore/tests/scoping", fileExtensions = "xcore")
public class XcoreScopingTest
{

	private XtextResource resource;
	private Map<String, String> params;
	@Inject
	private OffsetHelper offsetHelper;
	@Inject
	private ValidationTestHelper validationHelper;
	@Inject
	private IScopeProvider scopeProvider;

	public XcoreScopingTest(XtextResource resource, Map<String, String> params)
	{
		super();
		this.resource = resource;
		this.params = params;
	}

	@Test
	public void noValidationIssues()
	{
		validationHelper.assertNoIssues(resource.getContents().get(0));
	}

	@XpectCommaSeparatedValues()
	@Parameter(syntax = "'at' offset=OFFSET")
	public List<String> scopeAllElements()
	{
		Pair<EObject, EStructuralFeature> objAndFeature = offsetHelper.at(resource, params.get("offset"))
		    .getEStructuralFeatureByParent();
		Assert.assertTrue(objAndFeature.getSecond() instanceof EReference);
		Assert.assertFalse(((EReference) objAndFeature.getSecond()).isContainment());
		IScope scope = scopeProvider.getScope(objAndFeature.getFirst(), (EReference) objAndFeature.getSecond());
		List<String> result = Lists.newArrayList();
		for (IEObjectDescription desc : scope.getAllElements())
			result.add(desc.getName().toString());
		return result;
	}

}
