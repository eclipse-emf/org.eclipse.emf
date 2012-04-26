/**
 * Copyright (c) Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.core.ecore;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ResourceSetMappedResourceLocatorTest extends TestCase
{
  public ResourceSetMappedResourceLocatorTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite testSuite = new TestSuite("ResourceLocatorTests");
    testSuite.addTest(new ResourceSetMappedResourceLocatorTest("test"));
    return testSuite;
  }

  protected ResourceSet createResourceSet(boolean tracked)
  {
    ResourceSetImpl resourceSet = new ResourceSetImpl();
    if (tracked)
    {
      new ResourceSetImpl.MappedResourceLocator(resourceSet);
    }
    return resourceSet;
  }

  public void test() throws Exception
  {
    test1(createResourceSet(false));
    test1(createResourceSet(true));
  }

  public void test1(ResourceSet resourceSet)
  {
    // Create a resource with a given URI 1 and confirm that we can find it with that URI.
    //
    URI uri1 = URI.createURI("http://www.eclipse.org/emf/test/core/ecore/Resource1");
    Resource resource = resourceSet.createResource(uri1);
    assertSame(resource, resourceSet.getResource(uri1, false));

    // Change the resource's URI to 2 and confirm that we can't find it with the old URI but can find it with in the new URI.
    //
    URI uri2 = URI.createURI("http://www.eclipse.org/emf/test/core/ecore/Resource2");
    resource.setURI(uri2);
    assertNull(resourceSet.getResource(uri1, false));
    assertSame(resource, resourceSet.getResource(uri2, false));

    // Define a mapping from URI 3 to URI 2 and confirm that we can find it with URI 3 and with URI 2..
    //
    URI remappedURI =  URI.createURI("http://www.eclipse.org/emf/test/core/ecore/Resource3");
    resourceSet.getURIConverter().getURIMap().put(remappedURI, uri2);
    assertSame(resource, resourceSet.getResource(uri2, false));
    assertSame(resource, resourceSet.getResource(remappedURI, false));

    // Remove the resource and confirm that we can't find it anymore.
    //
    resourceSet.getResources().remove(resource);
    assertNull(resourceSet.getResource(remappedURI, false));

    // Add the resource back and confirm that we can find it again with both URI 3 and URI 2.
    //
    resourceSet.getResources().add(resource);
    assertSame(resource, resourceSet.getResource(uri2, false));
    assertSame(resource, resourceSet.getResource(remappedURI, false));

    // Create another resource with URI 3 and confirm that it's shadowed.
    //
    Resource resource2 = resourceSet.createResource(remappedURI);
    assertSame(resource, resourceSet.getResource(remappedURI, false));

    // Move that other resource to the beginning and confirm it's now visible and the other one is shadowed.
    //
    resourceSet.getResources().move(0, 1);
    assertSame(resource2, resourceSet.getResource(remappedURI, false));
  }
}
