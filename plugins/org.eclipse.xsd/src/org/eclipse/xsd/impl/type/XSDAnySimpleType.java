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
 * $Id: XSDAnySimpleType.java,v 1.2 2004/05/22 19:05:58 marcelop Exp $
 */
package org.eclipse.xsd.impl.type;


import java.util.Arrays;

import org.eclipse.emf.ecore.xml.type.internal.DataValue.Base64;
import org.eclipse.emf.ecore.xml.type.internal.DataValue.HexBin;
import org.eclipse.xsd.XSDPlugin;
import org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl;


public class XSDAnySimpleType 
{
  public static class ByteSequence
  {
    protected final XSDAnySimpleType xsdAnySimpleType;
    protected final byte [] bytes;
    protected String canonical;

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
      if (canonical == null)
      {
        canonical = Base64.encode(bytes);
      }
      return canonical;
    }   
  }
  
  public static class HexSequence extends ByteSequence
  {
    public HexSequence(XSDAnySimpleType xsdAnySimpleType, byte [] bytes)
    {
      super(xsdAnySimpleType, bytes);
    }
    public String toString()
    {
      if (canonical == null)
      {
        canonical = HexBin.encode(bytes);
      }
      return canonical;
    } 
  }

  public void assess(XSDSimpleTypeDefinitionImpl.AssessmentImpl assessment)
  {
    assessment.xsdAnySimpleType = this;
    Object value = getValue(assessment.normalizedLiteral);    
    if (value != null)
    {
      assessment.value = value;
    }
    else
    {
      assessment.reportDatatypeDiagnostic();
    }
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
