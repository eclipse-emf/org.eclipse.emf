package org.eclipse.emf.ecore.xcore.tests.validation;

import java.util.List;

import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.parameterized.ParameterizedXtextRunner;
import org.eclipse.xtext.junit4.parameterized.ResourceURIs;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

@InjectWith(XcoreInjectorProvider.class)
@RunWith(ParameterizedXtextRunner.class)
@ResourceURIs(baseDir = "src/org/eclipse/emf/ecore/xcore/tests/validation", fileExtensions = "xcore")
public class XcoreValidationTest
{

	protected String expected;
	protected ILeafNode leaf;

	public XcoreValidationTest(ILeafNode leaf, String expected)
	{
		super();
		this.leaf = leaf;
		this.expected = expected;
	}

	protected String formatIssue(Issue issue)
	{
		StringBuilder result = new StringBuilder();
		result.append(issue.getSeverity().name().toLowerCase());
		if (issue.getOffset() != null && issue.getLength() != null)
		{
			result.append(" for '");
			result.append(leaf.getRootNode().getText().substring(issue.getOffset(), issue.getOffset() + issue.getLength()));
		} else
			result.append(" ");
		result.append("' message '");
		result.append(issue.getMessage());
		result.append("'");
		return result.toString();
	}

	@Test
	public String issues()
	{
		XtextResource resource = (XtextResource) NodeModelUtils.findActualSemanticObjectFor(leaf).eResource();
		IResourceValidator validator = resource.getResourceServiceProvider().get(IResourceValidator.class);
		List<Issue> issues = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
		List<String> result = Lists.newArrayList();
		for (Issue issue : issues)
			result.add(formatIssue(issue));
		return Joiner.on('\n').join(result);
	}

}
