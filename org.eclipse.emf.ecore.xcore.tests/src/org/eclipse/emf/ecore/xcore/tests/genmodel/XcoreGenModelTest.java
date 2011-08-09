package org.eclipse.emf.ecore.xcore.tests.genmodel;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.tests.GenModelFormatter;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.parameterized.AbstractParameterizedXtextTest;
import org.eclipse.xtext.junit4.parameterized.ResourceURIs;
import org.eclipse.xtext.resource.XtextResource;
import org.junit.Test;

import com.google.inject.Inject;

@InjectWith(XcoreInjectorProvider.class)
@ResourceURIs(baseDir = "src/org/eclipse/emf/ecore/xcore/tests/genmodel", fileExtensions = "xcore")
public class XcoreGenModelTest extends AbstractParameterizedXtextTest
{

	public XcoreGenModelTest(XtextResource resource, int offset, String[] params)
	{
		super(resource, offset, params);
	}

	@Inject
	private XcoreMapper mapper;

	@Test
	public String genBase()
	{
		EcoreUtil.resolveAll(resource);
		GenBase gen = mapper.getGen((XNamedElement) getEObjectAtOffset());
		return new GenModelFormatter().resolveCrossReferences().format(gen);
	}

}
