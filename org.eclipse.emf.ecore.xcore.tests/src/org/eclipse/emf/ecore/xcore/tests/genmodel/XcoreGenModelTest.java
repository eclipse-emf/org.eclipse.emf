package org.eclipse.emf.ecore.xcore.tests.genmodel;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.tests.EObjectFormatter;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.parameterized.ParameterizedXtextRunner;
import org.eclipse.xtext.junit4.parameterized.ResourceURIs;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parsetree.reconstr.impl.NodeIterator;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

@InjectWith(XcoreInjectorProvider.class)
@RunWith(ParameterizedXtextRunner.class)
@ResourceURIs(baseDir = "src/org/eclipse/emf/ecore/xcore/tests/genmodel", fileExtensions = "xcore")
public class XcoreGenModelTest
{

	protected String expected;
	protected INode leaf;

	public XcoreGenModelTest(ILeafNode leaf, String expected)
	{
		super();
		this.leaf = leaf;
		NodeIterator ni = new NodeIterator(leaf);
		while (ni.hasNext())
		{
			INode n = ni.next();
			if (n instanceof ILeafNode && !((ILeafNode) n).isHidden())
			{
				this.leaf = n;
				break;
			}
		}
		this.expected = expected;
	}

	@Inject
	private XcoreMapper mapper = new XcoreMapper();

	@Test
	public String gen()
	{
		EObject obj = NodeModelUtils.findActualSemanticObjectFor(leaf);
		EcoreUtil.resolveAll(obj.eResource());
		EObject gen = mapper.getGen((XNamedElement) obj);
		return new EObjectFormatter().resolveCrossReferences().format(gen);
	}

}
