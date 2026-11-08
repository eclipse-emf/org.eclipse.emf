/**
 * Copyright (c) 2026 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.core.common.util;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

import org.eclipse.emf.common.util.URI;
import org.junit.Test;


/**
 * Tests that the "EMF Reference Cleaner" thread does not keep the class loader that loaded EMF reachable.
 */
public class ReferenceClearingQueueTest
{
  private static final String REFERENCE_CLEARING_QUEUE_PROPERTY = "org.eclipse.emf.common.util.ReferenceClearingQueue";

  private static final String IDLE_TIMEOUT_PROPERTY = "org.eclipse.emf.common.util.ReferenceClearingQueue.idleTimeout";

  private static final String THREAD_NAME = "EMF Reference Cleaner";

  /**
   * Loads the classes of <code>org.eclipse.emf.common</code> in a class loader of their own,
   * like a plugin class loader in an application that discards class loaders.
   */
  private static final class IsolatingClassLoader extends ClassLoader
  {
    private final ClassLoader source;

    IsolatingClassLoader(ClassLoader source)
    {
      super(null);
      this.source = source;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException
    {
      if (!name.startsWith("org.eclipse.emf.common."))
      {
        throw new ClassNotFoundException(name);
      }

      InputStream inputStream = source.getResourceAsStream(name.replace('.', '/') + ".class");
      if (inputStream == null)
      {
        throw new ClassNotFoundException(name);
      }

      try
      {
        try
        {
          ByteArrayOutputStream bytes = new ByteArrayOutputStream();
          byte[] buffer = new byte [8192];
          for (int length; (length = inputStream.read(buffer)) != -1;)
          {
            bytes.write(buffer, 0, length);
          }
          return defineClass(name, bytes.toByteArray(), 0, bytes.size());
        }
        finally
        {
          inputStream.close();
        }
      }
      catch (IOException exception)
      {
        throw new ClassNotFoundException(name, exception);
      }
    }
  }

  @Test
  public void testPollingThreadDoesNotRetainClassLoader() throws Exception
  {
    String referenceClearingQueue = System.getProperty(REFERENCE_CLEARING_QUEUE_PROPERTY);
    String idleTimeout = System.getProperty(IDLE_TIMEOUT_PROPERTY);
    System.setProperty(REFERENCE_CLEARING_QUEUE_PROPERTY, "true");
    System.setProperty(IDLE_TIMEOUT_PROPERTY, "1000");
    try
    {
      WeakReference<ClassLoader> classLoaderReference = useEMFInIsolatedClassLoader();
      for (int i = 0; i < 100 && classLoaderReference.get() != null; ++i)
      {
        System.gc();
        Thread.sleep(100);
      }
      assertNull("The class loader that loaded EMF should be garbage collected", classLoaderReference.get());
    }
    finally
    {
      restoreProperty(REFERENCE_CLEARING_QUEUE_PROPERTY, referenceClearingQueue);
      restoreProperty(IDLE_TIMEOUT_PROPERTY, idleTimeout);
    }
  }

  private static WeakReference<ClassLoader> useEMFInIsolatedClassLoader() throws Exception
  {
    ClassLoader classLoader = new IsolatingClassLoader(URI.class.getClassLoader());
    Method createURI = classLoader.loadClass(URI.class.getName()).getMethod("createURI", String.class);

    // Creating a URI creates weak references managed by the reference clearing queue, so the polling thread is running.
    //
    Object uri = createURI.invoke(null, "http://www.example.org/" + System.nanoTime() + "/a");
    assertNotNull(uri.toString());
    Thread thread = getPollingThread(classLoader);
    assertNotNull("The polling thread should be running", thread);
    assertNull("The polling thread should not have a context class loader", thread.getContextClassLoader());

    // The polling thread terminates when it is idle.
    //
    thread.join(10000);
    assertFalse("The polling thread should terminate when it is idle", thread.isAlive());

    // Creating a new weak reference starts a new polling thread.
    //
    createURI.invoke(null, "http://www.example.org/" + System.nanoTime() + "/b");
    Thread restartedThread = getPollingThread(classLoader);
    assertNotNull("The polling thread should be restarted", restartedThread);
    assertNotSame(thread, restartedThread);
    restartedThread.join(10000);
    assertFalse("The restarted polling thread should terminate when it is idle", restartedThread.isAlive());

    return new WeakReference<ClassLoader>(classLoader);
  }

  private static Thread getPollingThread(ClassLoader classLoader)
  {
    for (Thread thread : Thread.getAllStackTraces().keySet())
    {
      if (THREAD_NAME.equals(thread.getName()) && thread.getClass().getClassLoader() == classLoader && thread.isAlive())
      {
        return thread;
      }
    }
    return null;
  }

  private static void restoreProperty(String key, String value)
  {
    if (value == null)
    {
      System.clearProperty(key);
    }
    else
    {
      System.setProperty(key, value);
    }
  }
}
