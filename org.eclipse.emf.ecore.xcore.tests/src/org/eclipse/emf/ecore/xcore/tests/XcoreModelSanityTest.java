package org.eclipse.emf.ecore.xcore.tests;

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xcore.XcoreStandaloneSetup;
import org.eclipse.emf.ecore.xcore.tests.util.AbstractModelSanityTest;
import org.eclipse.emf.ecore.xcore.tests.util.LabelledParameterized.LabelledParameters;

public class XcoreModelSanityTest extends AbstractModelSanityTest
{

	static
	{
		XcoreStandaloneSetup.doSetup();
	}

	public XcoreModelSanityTest(String label, Resource res) throws Exception
	{
		super(label, res);
	}

	@LabelledParameters
	public static Collection<Object[]> generateData()
	{
		return collectTestFiles("xcore-src", "xcore");
	}

}
