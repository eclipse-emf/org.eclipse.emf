package org.eclipse.emf.ecore.xcore.tests.validation;

import java.util.List;

import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.parameterized.ParameterizedXtextRunner;
import org.eclipse.xtext.junit4.parameterized.ResourceURIs;
import org.eclipse.xtext.junit4.parameterized.XpectLines;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.junit.runner.RunWith;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * There are cases not covered because the grammar doesn't allow it, i.e.,
 * {@link EcoreValidator#CONSISTENT_BOUNDS_NOT_ALLOWED},
 * {@link EcoreValidator#CONSISTENT_BOUNDS_NO_BOUNDS_WITH_TYPE_PARAMETER_OR_CLASSIFIER}
 * , {@link EcoreValidator#CONSISTENT_BOUNDS_NO_LOWER_AND_UPPER},
 * {@link EcoreValidator#CONSISTENT_TYPE_NO_TYPE_PARAMETER_AND_CLASSIFIER},
 * {@link EcoreValidator#CONSISTENT_TYPE_WILDCARD_NOT_PERMITTED},
 * {@link EcoreValidator#INTERFACE_IS_ABSTRACT},
 * {@link EcoreValidator#NO_REPEATING_VOID},
 * {@link EcoreValidator#UNIQUE_NS_URIS},
 * {@link EcoreValidator#UNIQUE_SUBPACKAGE_NAMES},
 * {@link EcoreValidator#VALID_LOWER_BOUND}, {@link EcoreValidator#VALID_TYPE},
 * {@link EcoreValidator#VALID_UPPER_BOUND},
 * {@link EcoreValidator#WELL_FORMED_INSTANCE_TYPE_NAME}, or the scoping rules
 * won't resolve that way, i.e., {@link EcoreValidator#CONSISTENT_KEYS},
 * {@link EcoreValidator#CONSISTENT_OPPOSITE_NOT_FROM_TYPE},
 * {@link EcoreValidator#CONSISTENT_TYPE_TYPE_PARAMETER_NOT_IN_SCOPE},
 * {@link EcoreValidator#WELL_FORMED_NAME}, or we don't set the flags yet, i.e.,
 * {@link EcoreValidator#CONSISTENT_TRANSIENT}, or we can't get into that state,
 * i.e., {@link EcoreValidator#VALID_DEFAULT_VALUE_LITERAL},
 * {@link EcoreValidator#CONSISTENT_UNIQUE}, or are TODO items
 * {@link EcoreValidator#WELL_FORMED_NS_PREFIX},
 * {@link EcoreValidator#WELL_FORMED_NS_URI},
 * {@link EcoreValidator#WELL_FORMED_SOURCE_URI},
 * 
 */
@SuppressWarnings("restriction")
@InjectWith(XcoreInjectorProvider.class)
@RunWith(ParameterizedXtextRunner.class)
@ResourceURIs(baseDir = "src/org/eclipse/emf/ecore/xcore/tests/validation", fileExtensions = "xcore")
public class XcoreValidationTest
{

	private XtextResource resource;

	@Inject
	private IResourceValidator validator;

	public XcoreValidationTest(XtextResource resource)
	{
		super();
		this.resource = resource;
	}

	protected String formatIssue(Issue issue)
	{
		StringBuilder result = new StringBuilder();
		result.append(issue.getSeverity().name().toLowerCase());
		if (issue.getOffset() != null && issue.getLength() != null)
		{
			result.append(" at '");
			result.append(resource.getParseResult().getRootNode().getText()
			    .substring(issue.getOffset(), issue.getOffset() + issue.getLength()));
			result.append("' ");
		} else
			result.append(" ");
		result.append("message '");
		result.append(issue.getMessage());
		result.append("'");
		return result.toString();
	}

	@XpectLines()
	public List<String> validationIssues()
	{
		List<String> result = Lists.newArrayList();
		for (Issue issue : validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl))
			result.add(formatIssue(issue));
		return result;
	}
}
