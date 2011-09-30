package org.eclipse.emf.ecore.xcore.scoping;

import static com.google.common.collect.Lists.newArrayList;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.impl.DefaultResourceDescription;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.util.IResourceScopeCache;

/**
 * This implementation ensures that the contents of the resource is not queried
 * to compute the exported elements.
 * @author Sebastian Zarnekow
 */
public class XcoreResourceDescription extends DefaultResourceDescription
{

	private final static Logger log = Logger.getLogger(XcoreResourceDescription.class);
	private final IDefaultResourceDescriptionStrategy strategy;

	public XcoreResourceDescription(Resource resource, IDefaultResourceDescriptionStrategy strategy,
	    IResourceScopeCache cache)
	{
		super(resource, strategy, cache);
		this.strategy = strategy;
	}

	@Override
	protected List<IEObjectDescription> computeExportedObjects()
	{
		Resource resource = getResource();
		if (resource instanceof XtextResource)
		{
			if (!resource.isLoaded())
			{
				try
				{
					resource.load(null);
				} 
				catch (IOException e)
				{
					log.error(e.getMessage(), e);
					return Collections.<IEObjectDescription> emptyList();
				}
			}
			IParseResult parseResult = ((XtextResource) resource).getParseResult();
			if (parseResult != null && parseResult.getRootASTElement() != null)
			{
				final List<IEObjectDescription> result = newArrayList();
				IAcceptor<IEObjectDescription> acceptor = new IAcceptor<IEObjectDescription>()
				{
					public void accept(IEObjectDescription description)
					{
						result.add(description);
					}
				};
				TreeIterator<EObject> allProperContents = EcoreUtil2.eAll(parseResult.getRootASTElement());
				while (allProperContents.hasNext())
				{
					EObject content = allProperContents.next();
					if (!strategy.createEObjectDescriptions(content, acceptor))
					{
						allProperContents.prune();
					}
				}
				return result;
			}
		}
		return super.computeExportedObjects();
	}

}
