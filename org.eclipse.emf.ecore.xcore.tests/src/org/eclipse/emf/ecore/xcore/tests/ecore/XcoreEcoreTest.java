package org.eclipse.emf.ecore.xcore.tests.ecore;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.tests.GenModelFormatter;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.parameterized.AbstractParameterizedXtextTest;
import org.eclipse.xtext.junit4.parameterized.ResourceURIs;
import org.eclipse.xtext.junit4.parameterized.TestAsString;
import org.eclipse.xtext.resource.XtextResource;

import com.google.inject.Inject;

@InjectWith(XcoreInjectorProvider.class)
@ResourceURIs(baseDir = "src/org/eclipse/emf/ecore/xcore/tests/ecore", fileExtensions = "xcore")
public class XcoreEcoreTest extends AbstractParameterizedXtextTest
{

	public XcoreEcoreTest(XtextResource resource, int offset, String[] params)
	{
		super(resource, offset, params);
	}

	@Inject
	private XcoreMapper mapper;

	@TestAsString
	public String eNamedElement()
	{
		EcoreUtil.resolveAll(resource);
		ENamedElement gen = mapper.getEcore((XNamedElement) getEObjectAtOffset());
		return new GenModelFormatter().resolveCrossReferences().format(gen);
	}

}
