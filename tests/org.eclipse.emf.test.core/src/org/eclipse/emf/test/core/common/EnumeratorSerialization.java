/**
 * Copyright (c) 2015 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.core.common;

import static org.junit.Assert.assertSame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eclipse.emf.common.util.AbstractEnumerator;
import org.junit.Test;

public class EnumeratorSerialization
{
  public static final class Enumerator extends AbstractEnumerator
  {
    public static final Enumerator VALUE = new Enumerator(0, "foo", "bar");

    private Enumerator(int value, String name, String literal)
    {
      super(value, name, literal);
    }

    public static AbstractEnumerator get(String name)
    {
      return VALUE;
    }
  }

  @Test
  public void test() throws Exception
  {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
    objectOutputStream.writeObject(Enumerator.VALUE);
    objectOutputStream.close();
    
    ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
    ObjectInputStream objectInputStream = new ObjectInputStream(in);
    Object object = objectInputStream.readObject();
    assertSame(Enumerator.VALUE, object);
  }
}
