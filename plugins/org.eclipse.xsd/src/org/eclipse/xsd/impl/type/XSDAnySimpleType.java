/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDAnySimpleType.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.impl.type;


import java.util.Arrays;

import org.eclipse.xsd.XSDPlugin;
import org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl;


public class XSDAnySimpleType 
{
  public static class ByteSequence
  {
    protected final XSDAnySimpleType xsdAnySimpleType;
    protected final byte [] bytes;

    public ByteSequence(XSDAnySimpleType xsdAnySimpleType, byte [] bytes)
    {
      this.xsdAnySimpleType = xsdAnySimpleType;
      this.bytes = bytes;
    }

    public byte [] getBytes()
    {
      return bytes;
    }

    public boolean equals(Object that)
    {
      if (that == this)
      {
        return true;
      }
      else if (that instanceof ByteSequence)
      {
        return Arrays.equals(this.bytes, ((ByteSequence)that).bytes);
      }
      else
      {
        return false;
      }
    }

    public int hashCode()
    {
      int hashCode = 1;
      for (int i = 0; i < bytes.length; ++i)
      {
        byte theByte = bytes[i];
        hashCode = 31 * hashCode + theByte;
      }
      return hashCode;
    }

    public String toString()
    {
      return xsdAnySimpleType.getCanonicalLiteral(this);
    }
  }

  public static class IntSequence
  {
    protected final XSDAnySimpleType xsdAnySimpleType;
    protected final int [] ints;

    public IntSequence(XSDAnySimpleType xsdAnySimpleType, int [] ints)
    {
      this.xsdAnySimpleType = xsdAnySimpleType;
      this.ints = ints;
    }

    public int [] getInts()
    {
      return ints;
    }

    public boolean equals(Object that)
    {
      if (that == this)
      {
        return true;
      }
      else if (that instanceof IntSequence)
      {
        return Arrays.equals(this.ints, ((IntSequence)that).ints);
      }
      else
      {
        return false;
      }
    }

    public int hashCode()
    {
      int hashCode = 1;
      for (int i = 0; i < ints.length; ++i)
      {
        int theInt = ints[i];
        hashCode = 31 * hashCode + theInt;
      }
      return hashCode;
    }

    public String toString()
    {
      return xsdAnySimpleType.getCanonicalLiteral(this);
    }
  }

  public void assess(XSDSimpleTypeDefinitionImpl.AssessmentImpl assessment)
  {
    assessment.xsdAnySimpleType = this;
    if (isValidLiteral(assessment.normalizedLiteral))
    {
      assessment.value = getValue(assessment.normalizedLiteral);
    }
    else
    {
      assessment.reportDatatypeDiagnostic();
    }
  }

  public boolean isValidLiteral(String normalizedLiteral)
  {
    return true;
  }

  public Object getValue(String normalizedLiteral)
  {
    return normalizedLiteral;
  }

  public String getCanonicalLiteral(Object value)
  {
    return value == null ? null : value.toString();
  }

  public int compareValues(Object value1, Object value2)
  {
    throw new RuntimeException(XSDPlugin.INSTANCE.getString("_EXC_CompareNotSupported"));
  }
}
