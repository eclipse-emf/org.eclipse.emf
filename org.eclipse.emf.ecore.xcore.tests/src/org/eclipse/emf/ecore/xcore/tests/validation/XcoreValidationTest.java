package org.eclipse.emf.ecore.xcore.tests.validation;

import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.parameterized.AbstractParameterizedXtextTest;
import org.eclipse.xtext.junit4.parameterized.ResourceURIs;
import org.eclipse.xtext.resource.XtextResource;

/**
 * There are cases not covered because the grammar doesn't allow it, i.e.,
 * {@link EcoreValidator#CONSISTENT_BOUNDS_NOT_ALLOWED},
 * {@link EcoreValidator#CONSISTENT_BOUNDS_NO_BOUNDS_WITH_TYPE_PARAMETER_OR_CLASSIFIER},
 * {@link EcoreValidator#CONSISTENT_BOUNDS_NO_LOWER_AND_UPPER},
 * {@link EcoreValidator#CONSISTENT_TYPE_NO_TYPE_PARAMETER_AND_CLASSIFIER},
 * {@link EcoreValidator#CONSISTENT_TYPE_WILDCARD_NOT_PERMITTED},
 * {@link EcoreValidator#INTERFACE_IS_ABSTRACT},
 * {@link EcoreValidator#NO_REPEATING_VOID},
 * {@link EcoreValidator#UNIQUE_NS_URIS},
 * {@link EcoreValidator#UNIQUE_SUBPACKAGE_NAMES},
 * {@link EcoreValidator#VALID_LOWER_BOUND},
 * {@link EcoreValidator#VALID_TYPE},
 * {@link EcoreValidator#VALID_UPPER_BOUND},
 * {@link EcoreValidator#WELL_FORMED_INSTANCE_TYPE_NAME},
 * or the scoping rules won't resolve that way, i.e.,
 * {@link EcoreValidator#CONSISTENT_KEYS},
 * {@link EcoreValidator#CONSISTENT_OPPOSITE_NOT_FROM_TYPE},
 * {@link EcoreValidator#CONSISTENT_TYPE_TYPE_PARAMETER_NOT_IN_SCOPE},
 * {@link EcoreValidator#WELL_FORMED_NAME},
 * or we don't set the flags yet, i.e., 
 * {@link EcoreValidator#CONSISTENT_TRANSIENT},
 * or we can't get into that state, i.e.,
 * {@link EcoreValidator#VALID_DEFAULT_VALUE_LITERAL},
 * {@link EcoreValidator#CONSISTENT_UNIQUE},
 * or are TODO items
 * {@link EcoreValidator#WELL_FORMED_NS_PREFIX},
 * {@link EcoreValidator#WELL_FORMED_NS_URI},
 * {@link EcoreValidator#WELL_FORMED_SOURCE_URI},
 * 
 */
@InjectWith(XcoreInjectorProvider.class)
@ResourceURIs(baseDir = "src/org/eclipse/emf/ecore/xcore/tests/validation", fileExtensions = "xcore")
public class XcoreValidationTest extends AbstractParameterizedXtextTest
{
	public XcoreValidationTest(XtextResource resource, int offset, String[] params)
	{
		super(resource, offset, params);
	}
}
