/**
 * <copyright>
 *
 * Copyright (c) 2004-2006 IBM Corporation and others.
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
 * $Id: XMLDuration.java,v 1.7 2007/06/14 18:32:46 emerks Exp $
 *
 * ---------------------------------------------------------------------
 *
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 1999-2004 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Xerces" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation and was
 * originally based on software copyright (c) 1999-2003, International
 * Business Machines, Inc., http://www.apache.org.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package org.eclipse.emf.ecore.xml.type.internal;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.Duration;
import javax.xml.datatype.DatatypeConstants.Field;

import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;


/**
 * An internal extension of Java's Duration to represent
 * the <a href="http://www.w3.org/TR/2001/REC-xmlschema-2-20010502/">W3C XML Schema 1.0</a>
 * duration data type.
 * <p> 
 * NOTE: this class is for internal use only. 
 */
public final class XMLDuration extends Duration
{
  final Duration duration;

  public XMLDuration(String value)
  {
    duration = XMLCalendar.datatypeFactory.newDuration(XMLTypeUtil.normalize(value, true));
  }

  public static int compare(XMLDuration value1, XMLDuration value2)
  {
    switch (value1.duration.compare(value2.duration))
    {
      case DatatypeConstants.EQUAL:
      {
        return XMLCalendar.EQUALS;
      }
      case DatatypeConstants.LESSER:
      {
        return XMLCalendar.LESS_THAN;
      }
      case DatatypeConstants.GREATER:
      {
        return XMLCalendar.GREATER_THAN;
      }
      default:
      {
        return XMLCalendar.INDETERMINATE;
      }
    }
  }

  @Override
  public boolean equals(Object object)
  {
    return object != null && duration.equals(object);
  }
    
  @Override
  public int hashCode()
  {
    return duration.hashCode();
  }
    
  @Override
  public String toString() 
  {
    return duration.toString();
  }

  @Override
  public Duration add(Duration rhs)
  {
    return duration.add(rhs);
  }

  @Override
  public void addTo(Calendar calendar)
  {
    duration.addTo(calendar);
  }

  @Override
  public int compare(Duration duration)
  {
    return this.duration.compare(duration);
  }

  @Override
  public Number getField(Field field)
  {
    return duration.getField(field);
  }

  @Override
  public int getSign()
  {
    return duration.getSign();
  }

  @Override
  public boolean isSet(Field field)
  {
    return duration.isSet(field);
  }

  @Override
  public Duration multiply(BigDecimal factor)
  {
    return duration.multiply(factor);
  }

  @Override
  public Duration negate()
  {
    return duration.negate();
  }

  @Override
  public Duration normalizeWith(Calendar startTimeInstant)
  {
    return duration.normalizeWith(startTimeInstant);
  }
}
