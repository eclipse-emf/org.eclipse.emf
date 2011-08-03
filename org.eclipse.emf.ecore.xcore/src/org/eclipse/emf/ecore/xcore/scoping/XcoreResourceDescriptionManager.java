package org.eclipse.emf.ecore.xcore.scoping;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionManager;

import com.google.inject.Singleton;

/**
 * The resource description manager for Xcore resources. It produces an {@link XcoreResourceDescription} 
 * which allows to produce descriptions without iterating the derived contents of the resource.
 * 
 * @author Sebastian Zarnekow
 */
@Singleton
public class XcoreResourceDescriptionManager extends DefaultResourceDescriptionManager
{

	@Override
	protected IResourceDescription internalGetResourceDescription(Resource resource,
	    IDefaultResourceDescriptionStrategy strategy)
	{
		return new XcoreResourceDescription(resource, strategy, getCache());
	}
	
}
