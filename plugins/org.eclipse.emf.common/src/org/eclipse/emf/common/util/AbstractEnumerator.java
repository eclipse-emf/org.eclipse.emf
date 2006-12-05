/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: AbstractEnumerator.java,v 1.5 2006/12/05 20:19:56 emerks Exp $
 */
package org.eclipse.emf.common.util;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * An extensible enumerator implementation.
 */
public abstract class AbstractEnumerator implements Enumerator, Serializable
{
  /**
   * The name of the enumerator.
   */
  private final String name;

  /**
   * The <code>int</code> value of the enumerator.
   */
  private final int value;
  
  /**
   * The literal value of the enumerator.
   */
  private final String literal;

  /**
   * Creates an initialized instance.
   * @param value the <code>int</code> value of the enumerator.
   * @param name the name of the enumerator, which is also used as the literal value.
   */
  protected AbstractEnumerator(int value, String name)
  {
    this.name = literal = name;
    this.value = value;
  }
  
  /**
   * Creates an initialized instance.
   * @param value the <code>int</code> value of the enumerator.
   * @param name the name of the enumerator.
   * @param literal the literal value of the enumerator.
   */
  protected AbstractEnumerator(int value, String name, String literal)
  {
    this.name = name;
    this.value = value;
    this.literal = literal;
  }

  /**
   * Returns the name of the enumerator.
   * @return the name.
   */
  public final String getName()
  {
    return name;
  }

  /**
   * Returns the <code>int</code> value of the enumerator.
   * @return the value.
   */
  public final int getValue()
  {
    return value;
  }
  
  /**
   * Returns the literal value of the enumerator.
   * @return the literal.
   */
  public final String getLiteral()
  {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * @return the literal.
   */
  @Override
  public final String toString()
  {
    return literal;
  }

  private static class AbstractEnumeratorExternalizeable implements Externalizable
  {
    protected AbstractEnumerator enumerator;

    public AbstractEnumeratorExternalizeable()
    {
      super();
    }

    public AbstractEnumeratorExternalizeable(AbstractEnumerator enumerator)
    {
      this.enumerator = enumerator;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException
    {
      objectOutput.writeObject(enumerator.getClass());
      objectOutput.writeUTF(enumerator.getName());
    }

    private static final Class<?> [] SIGNATURE = new Class [] { String.class };

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException
    {
      Class<?> _class = (Class<?>)objectInput.readObject();
      String name = objectInput.readUTF();
      try
      {
        enumerator = (AbstractEnumerator)_class.getMethod("get", SIGNATURE).invoke(null, new Object [] { name });
      }
      catch (Exception exception)
      {
        IOException ioException = new IOException();
        ioException.initCause(exception);
        throw ioException;
      }
    }

    protected Object readResolve()
    {
      return enumerator;
    } 
  }

  protected Object writeReplace() throws ObjectStreamException
  {
    return new AbstractEnumeratorExternalizeable(this);
  }
}
