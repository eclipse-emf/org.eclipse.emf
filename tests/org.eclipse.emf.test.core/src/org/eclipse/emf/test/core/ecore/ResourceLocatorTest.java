/**
 * Copyright (c) 2006-2008 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.ecore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class ResourceLocatorTest extends TestCase
{
  private static final URI TEST_URI = URI.createURI("test.xml") ;

  private static final Resource RESOURCE =
    new ResourceImpl(TEST_URI)
    {
      @Override
      public String toString()
      {
        return uri.toString();
      }
    };

  private static final URI TEST_BOGUS_URI = URI.createURI("test-bogus.xml") ;

  private static final Resource RESOURCE_BOGUS =
    new ResourceImpl(TEST_BOGUS_URI)
    {
      @Override
      public String toString()
      {
        return uri.toString();
      }
    };

  private static final class Event
  {
    private Object target;
    private String method;
    private Object[] arguments;

    public Event(Object target, String method, Object[] arguments)
    {
      super();
      this.target = target;
      this.method = method;
      this.arguments = arguments;
    }

    @Override
    public int hashCode()
    {
      final int prime = 31;
      int result = 1;
      result = prime * result + Arrays.hashCode(arguments);
      result = prime * result + ((method == null) ? 0 : method.hashCode());
      result = prime * result + ((target == null) ? 0 : target.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj)
    {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Event other = (Event)obj;
      if (!Arrays.equals(arguments, other.arguments))
        return false;
      if (method == null)
      {
        if (other.method != null)
          return false;
      }
      else if (!method.equals(other.method))
        return false;
      if (target == null)
      {
        if (other.target != null)
          return false;
      }
      else if (!target.equals(other.target))
        return false;
      return true;
    }

    @Override
    public String toString()
    {
      return "Event [target='" + target + "' method='" + method + "' arguments='" + Arrays.toString(arguments).replace(',', ' ') + "']";
    }
  }

  private static class TestResourceSet extends ResourceSetImpl
  {
    public List<Event> events;

    public TestResourceSet(List<Event> events)
    {
      this.events = events;
    }

    @Override
    public Resource getResource(URI uri, boolean loadOnDemand)
    {
      events.add(new Event(this, "getResource", new Object[] {uri, loadOnDemand }));
      return super.getResource(uri, loadOnDemand);
    }

    @Override
    protected Resource demandCreateResource(URI uri)
    {
      events.add(new Event(this, "demandCreateResource", new Object[] { uri }));
      return super.demandCreateResource(uri);
    }

    @Override
    public Resource createResource(URI uri, String contentType)
    {
      if (uri.equals(TEST_URI))
      {
        getResources().add(RESOURCE);
        return RESOURCE;
      }
      else if (uri.equals(TEST_BOGUS_URI))
      {
        getResources().add(RESOURCE_BOGUS);
        return RESOURCE_BOGUS;
      }

      throw new IllegalStateException("Bogus");
    }

    @Override
    protected void demandLoad(Resource resource) throws IOException
    {
      events.add(new Event(this, "demandLoad", new Object[] { resource }));
      if (resource == RESOURCE_BOGUS)
      {
        throw new IOException("bogus");
      }
      resource.getContents().clear();
    }

    @Override
    protected void demandLoadHelper(Resource resource)
    {
      events.add(new Event(this, "demandLoadHelper", new Object[] { resource }));
      super.demandLoadHelper(resource);
    }

    @Override
    protected void handleDemandLoadException(Resource resource, IOException exception) throws RuntimeException
    {
      events.add(new Event(this, "handleDemandLoadException", new Object[] { resource, exception }));
      super.handleDemandLoadException(resource, exception);
    }

    @Override
    protected Resource delegatedGetResource(URI uri, boolean loadOnDemand)
    {
      events.add(new Event(this, "delegatedGetResource", new Object[] {uri, loadOnDemand }));
      return super.delegatedGetResource(uri, loadOnDemand);
    }

    @Override
    public String toString()
    {
      return "ResourceSet";
    }
  }

  private static class TestResourceLocator extends ResourceSetImpl.ResourceLocator
  {
    private List<Event> events;
    private String name;

    public TestResourceLocator(String name, List<Event> events, ResourceSetImpl resourceSet)
    {
      super(resourceSet);

      this.events = events;
      this.name = name;
    }

    @Override
    protected void dispose()
    {
      super.dispose();
    }

    @Override
    protected Resource basicGetResource(URI uri, boolean loadOnDemand)
    {
      events.add(new Event(this, "basicGetResource", new Object[] {uri, loadOnDemand }));
      return super.basicGetResource(uri, loadOnDemand);
    }

    @Override
    public Resource getResource(URI uri, boolean loadOnDemand)
    {
      events.add(new Event(this, "getResource", new Object[] {uri, loadOnDemand }));
      return basicGetResource(uri, loadOnDemand);
    }

    @Override
    protected Resource demandCreateResource(URI uri)
    {
      events.add(new Event(this, "demandCreateResource", new Object[] { uri }));
      return super.demandCreateResource(uri);
    }

    @Override
    protected void demandLoad(Resource resource) throws IOException
    {
      events.add(new Event(this, "demandLoad", new Object[] { resource }));
      super.demandLoad(resource);
    }

    @Override
    protected void demandLoadHelper(Resource resource)
    {
      events.add(new Event(this, "demandLoadHelper", new Object[] { resource }));
      super.demandLoadHelper(resource);
    }

    @Override
    protected void handleDemandLoadException(Resource resource, IOException exception) throws RuntimeException
    {
      events.add(new Event(this, "handleDemandLoadException", new Object[] { resource, exception }));
      super.handleDemandLoadException(resource, exception);
    }

    @Override
    protected Resource delegatedGetResource(URI uri, boolean loadOnDemand)
    {
      events.add(new Event(this, "delegatedGetResource", new Object[] {uri, loadOnDemand }));
      return super.delegatedGetResource(uri, loadOnDemand);
    }

    @Override
    public String toString()
    {
      return name;
    }
  }

  /**
   * @param name
   */
  public ResourceLocatorTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("ResourceLocatorTest");
    ts.addTest(new ResourceLocatorTest("testResourceSetEvents"));
    ts.addTest(new ResourceLocatorTest("testResourceSetEventsWithDisposedLocator"));
    ts.addTest(new ResourceLocatorTest("testResourceSetEventsWithManyDisposedLocator"));
    ts.addTest(new ResourceLocatorTest("testResourceSetEventsWithLocator"));
    ts.addTest(new ResourceLocatorTest("testResourceSetEventsWithTwoLocators"));
    ts.addTest(new ResourceLocatorTest("testResourceSetEventsWithTwoLocatorsAndDispose"));
    return ts;
  }

  public void testResourceSetEvents() throws Exception
  {
    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);

      assertNull(resourceSet.getResource(TEST_URI, false));

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, false}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_URI, false}),
            }),
         toString(events));
    }

    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);

      assertNotNull(resourceSet.getResource(TEST_URI, true));

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, true}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_URI, true}),
             new Event(resourceSet, "demandCreateResource", new Object[] {TEST_URI}),
             new Event(resourceSet, "demandLoadHelper", new Object[] {TEST_URI}),
             new Event(resourceSet, "demandLoad", new Object[] {TEST_URI}),
            }),
         toString(events));
    }

    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);

      Exception exception = new Exception();
      try
      {
        assertNotNull(resourceSet.getResource(TEST_BOGUS_URI, true));
        fail("Expected an exception");
      }
      catch (RuntimeException ex)
      {
        exception = ex;
      }

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(resourceSet, "demandCreateResource", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "demandLoadHelper", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "demandLoad", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "handleDemandLoadException", new Object[] {RESOURCE_BOGUS, exception.getCause() }),
            }),
         toString(events));
    }
  }

  public void testResourceSetEventsWithDisposedLocator() throws Exception
  {
    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);
      TestResourceLocator locator = new TestResourceLocator("Locator", events, resourceSet);
      locator.dispose();

      assertNull(resourceSet.getResource(TEST_URI, false));

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, false}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_URI, false}),
            }),
         toString(events));
    }

    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);
      TestResourceLocator locator = new TestResourceLocator("Locator", events, resourceSet);
      locator.dispose();

      assertNotNull(resourceSet.getResource(TEST_URI, true));

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, true}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_URI, true}),
             new Event(resourceSet, "demandCreateResource", new Object[] {TEST_URI}),
             new Event(resourceSet, "demandLoadHelper", new Object[] {TEST_URI}),
             new Event(resourceSet, "demandLoad", new Object[] {TEST_URI}),
            }),
         toString(events));
    }

    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);
      TestResourceLocator locator = new TestResourceLocator("Locator", events, resourceSet);
      locator.dispose();

      Exception exception = new Exception();
      try
      {
        assertNotNull(resourceSet.getResource(TEST_BOGUS_URI, true));
        fail("Expected an exception");
      }
      catch (RuntimeException ex)
      {
        exception = ex;
      }

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(resourceSet, "demandCreateResource", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "demandLoadHelper", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "demandLoad", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "handleDemandLoadException", new Object[] {RESOURCE_BOGUS, exception.getCause() }),
            }),
         toString(events));
    }
  }

  public void testResourceSetEventsWithManyDisposedLocator() throws Exception
  {
    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);
      TestResourceLocator locator1 = new TestResourceLocator("Locator", events, resourceSet);
      TestResourceLocator locator2 = new TestResourceLocator("Locator", events, resourceSet);
      locator2.dispose();
      locator1.dispose();

      assertNull(resourceSet.getResource(TEST_URI, false));

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, false}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_URI, false}),
            }),
         toString(events));
    }

    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);
      TestResourceLocator locator1 = new TestResourceLocator("Locator", events, resourceSet);
      TestResourceLocator locator2 = new TestResourceLocator("Locator", events, resourceSet);
      locator1.dispose();
      locator2.dispose();

      assertNotNull(resourceSet.getResource(TEST_URI, true));

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, true}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_URI, true}),
             new Event(resourceSet, "demandCreateResource", new Object[] {TEST_URI}),
             new Event(resourceSet, "demandLoadHelper", new Object[] {TEST_URI}),
             new Event(resourceSet, "demandLoad", new Object[] {TEST_URI}),
            }),
         toString(events));
    }

    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);
      TestResourceLocator locator = new TestResourceLocator("Locator", events, resourceSet);
      locator.dispose();
      TestResourceLocator locator1 = new TestResourceLocator("Locator", events, resourceSet);
      TestResourceLocator locator2 = new TestResourceLocator("Locator", events, resourceSet);
      TestResourceLocator locator3 = new TestResourceLocator("Locator", events, resourceSet);
      locator3.dispose();
      locator1.dispose();
      locator2.dispose();

      Exception exception = new Exception();
      try
      {
        assertNotNull(resourceSet.getResource(TEST_BOGUS_URI, true));
        fail("Expected an exception");
      }
      catch (RuntimeException ex)
      {
        exception = ex;
      }

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(resourceSet, "demandCreateResource", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "demandLoadHelper", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "demandLoad", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "handleDemandLoadException", new Object[] {RESOURCE_BOGUS, exception.getCause() }),
            }),
         toString(events));
    }
  }

  public void testResourceSetEventsWithLocator() throws Exception
  {
    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);
      TestResourceLocator locator = new TestResourceLocator("Locator", events, resourceSet);

      assertNull(resourceSet.getResource(TEST_URI, false));

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, false}),
             new Event(locator, "getResource", new Object[] {TEST_URI, false}),
             new Event(locator, "basicGetResource", new Object[] {TEST_URI, false}),
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, false}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_URI, false}),
            }),
         toString(events));
    }

    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);
      TestResourceLocator locator = new TestResourceLocator("Locator", events, resourceSet);

      assertNotNull(resourceSet.getResource(TEST_URI, true));

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, true}),
             new Event(locator, "getResource", new Object[] {TEST_URI, true}),
             new Event(locator, "basicGetResource", new Object[] {TEST_URI, true}),
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, true}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_URI, true}),
             new Event(resourceSet, "demandCreateResource", new Object[] {TEST_URI}),
             new Event(resourceSet, "demandLoadHelper", new Object[] {TEST_URI}),
             new Event(resourceSet, "demandLoad", new Object[] {TEST_URI}),
            }),
         toString(events));
    }

    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);
      TestResourceLocator locator = new TestResourceLocator("Locator", events, resourceSet);

      Exception exception = new Exception();
      try
      {
        assertNotNull(resourceSet.getResource(TEST_BOGUS_URI, true));
        fail("Expected an exception");
      }
      catch (RuntimeException ex)
      {
        exception = ex;
      }

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(locator, "getResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(locator, "basicGetResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(resourceSet, "getResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(resourceSet, "demandCreateResource", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "demandLoadHelper", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "demandLoad", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "handleDemandLoadException", new Object[] {RESOURCE_BOGUS, exception.getCause() }),
            }),
         toString(events));
    }
  }

  public void testResourceSetEventsWithTwoLocators() throws Exception
  {
    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);
      TestResourceLocator locator1 = new TestResourceLocator("Locator1", events, resourceSet);
      TestResourceLocator locator2 = new TestResourceLocator("Locator2", events, resourceSet);

      assertNull(resourceSet.getResource(TEST_URI, false));

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, false}),
             new Event(locator2, "getResource", new Object[] {TEST_URI, false}),
             new Event(locator2, "basicGetResource", new Object[] {TEST_URI, false}),
             new Event(locator1, "getResource", new Object[] {TEST_URI, false}),
             new Event(locator1, "basicGetResource", new Object[] {TEST_URI, false}),
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, false}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_URI, false}),
            }),
         toString(events));
    }

    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);
      TestResourceLocator locator1 = new TestResourceLocator("Locator1", events, resourceSet);
      TestResourceLocator locator2 = new TestResourceLocator("Locator2", events, resourceSet);

      assertNotNull(resourceSet.getResource(TEST_URI, true));

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, true}),
             new Event(locator2, "getResource", new Object[] {TEST_URI, true}),
             new Event(locator2, "basicGetResource", new Object[] {TEST_URI, true}),
             new Event(locator1, "getResource", new Object[] {TEST_URI, true}),
             new Event(locator1, "basicGetResource", new Object[] {TEST_URI, true}),
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, true}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_URI, true}),
             new Event(resourceSet, "demandCreateResource", new Object[] {TEST_URI}),
             new Event(resourceSet, "demandLoadHelper", new Object[] {TEST_URI}),
             new Event(resourceSet, "demandLoad", new Object[] {TEST_URI}),
            }),
         toString(events));
    }

    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);
      TestResourceLocator locator1 = new TestResourceLocator("Locator1", events, resourceSet);
      TestResourceLocator locator2 = new TestResourceLocator("Locator2", events, resourceSet);

      Exception exception = new Exception();
      try
      {
        assertNotNull(resourceSet.getResource(TEST_BOGUS_URI, true));
        fail("Expected an exception");
      }
      catch (RuntimeException ex)
      {
        exception = ex;
      }

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(locator2, "getResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(locator2, "basicGetResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(locator1, "getResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(locator1, "basicGetResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(resourceSet, "getResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(resourceSet, "demandCreateResource", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "demandLoadHelper", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "demandLoad", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "handleDemandLoadException", new Object[] {RESOURCE_BOGUS, exception.getCause() }),
            }),
         toString(events));
    }
  }

  public void testResourceSetEventsWithTwoLocatorsAndDispose() throws Exception
  {
    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);
      TestResourceLocator locator = new TestResourceLocator("Locator", events, resourceSet);
      TestResourceLocator locator1 = new TestResourceLocator("Locator1", events, resourceSet);
      TestResourceLocator locator2 = new TestResourceLocator("Locator2", events, resourceSet);
      locator.dispose();

      assertNull(resourceSet.getResource(TEST_URI, false));

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, false}),
             new Event(locator2, "getResource", new Object[] {TEST_URI, false}),
             new Event(locator2, "basicGetResource", new Object[] {TEST_URI, false}),
             new Event(locator1, "getResource", new Object[] {TEST_URI, false}),
             new Event(locator1, "basicGetResource", new Object[] {TEST_URI, false}),
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, false}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_URI, false}),
            }),
         toString(events));
    }

    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);
      TestResourceLocator locator1 = new TestResourceLocator("Locator1", events, resourceSet);
      TestResourceLocator locator = new TestResourceLocator("Locator", events, resourceSet);
      TestResourceLocator locator2 = new TestResourceLocator("Locator2", events, resourceSet);
      locator.dispose();

      assertNotNull(resourceSet.getResource(TEST_URI, true));

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, true}),
             new Event(locator2, "getResource", new Object[] {TEST_URI, true}),
             new Event(locator2, "basicGetResource", new Object[] {TEST_URI, true}),
             new Event(locator1, "getResource", new Object[] {TEST_URI, true}),
             new Event(locator1, "basicGetResource", new Object[] {TEST_URI, true}),
             new Event(resourceSet, "getResource", new Object[] {TEST_URI, true}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_URI, true}),
             new Event(resourceSet, "demandCreateResource", new Object[] {TEST_URI}),
             new Event(resourceSet, "demandLoadHelper", new Object[] {TEST_URI}),
             new Event(resourceSet, "demandLoad", new Object[] {TEST_URI}),
            }),
         toString(events));
    }

    {
      List<Event> events = new ArrayList<Event>();
      TestResourceSet resourceSet = new TestResourceSet(events);
      TestResourceLocator locator1 = new TestResourceLocator("Locator1", events, resourceSet);
      TestResourceLocator locator2 = new TestResourceLocator("Locator2", events, resourceSet);
      TestResourceLocator locator = new TestResourceLocator("Locator", events, resourceSet);
      locator.dispose();

      Exception exception = new Exception();
      try
      {
        assertNotNull(resourceSet.getResource(TEST_BOGUS_URI, true));
        fail("Expected an exception");
      }
      catch (RuntimeException ex)
      {
        exception = ex;
      }

      assertEquals
        (toString
           (new Event[]
            {
             new Event(resourceSet, "getResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(locator2, "getResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(locator2, "basicGetResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(locator1, "getResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(locator1, "basicGetResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(resourceSet, "getResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(resourceSet, "delegatedGetResource", new Object[] {TEST_BOGUS_URI, true}),
             new Event(resourceSet, "demandCreateResource", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "demandLoadHelper", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "demandLoad", new Object[] {TEST_BOGUS_URI}),
             new Event(resourceSet, "handleDemandLoadException", new Object[] {RESOURCE_BOGUS, exception.getCause() }),
            }),
         toString(events));
    }
  }

  private String toString(Event[] events)
  {
    return toString(Arrays.asList(events));
  }

  private String toString(List<Event> events)
  {
    StringBuilder result = new StringBuilder();
    for (Event event : events)
    {
      result.append(event);
      result.append('\n');
    }

    return result.substring(0, result.length() - 1);
  }
}
