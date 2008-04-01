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
 * $Id: XMLCalendar.java,v 1.16 2008/04/01 16:43:34 emerks Exp $
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
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.xml.type.InvalidDatatypeValueException;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;

/**
 * An internal extension of Java's XMLGregorianCalendar to represent
 * <a href="http://www.w3.org/TR/2001/REC-xmlschema-2-20010502/">W3C XML Schema 1.0</a> 
 * dateTime, time, date, gYearMonth,  gYear, gMonthDay, gDay, gMonth data types.
 * <p> 
 * NOTE: this class is for internal use only. 
 */
public final class XMLCalendar extends XMLGregorianCalendar
{
  public final static short DATETIME = 0;
  public final static short TIME = 1;
  public final static short DATE = 2;
  public final static short GYEARMONTH = 3;
  public final static short GYEAR = 4;
  public final static short GMONTHDAY = 5;
  public final static short GDAY = 6;
  public final static short GMONTH = 7;
  
  protected static final String [] XML_SCHEMA_TYPES =
    {
      "dateTime",
      "time",
      "date",
      "gYearMonth",
      "gYear",
      "gMonthDay",
      "gDay",
      "gMonth"
    };
  
  public final static int EQUALS = 0;
  public final static int LESS_THAN = -1;
  public final static int GREATER_THAN = 1;
  public final static int INDETERMINATE = 2;
  
  final short dataType;
  final private XMLGregorianCalendar xmlGregorianCalendar;
  private Date date;
  
  static final DatatypeFactory datatypeFactory;
  static
  {
    try
    {
      datatypeFactory = DatatypeFactory.newInstance();
    }
    catch (DatatypeConfigurationException exception)
    {
      throw new RuntimeException(exception);
    }
  }
  
  protected static final DateFormat [] EDATE_FORMATS =
  {
    new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"),
    new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
    new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'S"), 
    new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'S'Z'"),
    new SafeSimpleDateFormat("yyyy-MM-dd'Z'"),
    new SafeSimpleDateFormat("yyyy-MM-dd")
  };

  static
  {
    EDATE_FORMATS[0].setTimeZone(TimeZone.getTimeZone("GMT"));
    EDATE_FORMATS[3].setTimeZone(TimeZone.getTimeZone("GMT"));    
  }

  private XMLCalendar(XMLGregorianCalendar xmlGregorianCalendar, Date date, short dataType)
  {
    this.xmlGregorianCalendar = xmlGregorianCalendar;
    this.date = date;
    this.dataType = dataType;
  }
  
  public XMLCalendar(String value, short datatype)
  {
    value = XMLTypeUtil.normalize(value, true);
    if (value.length() == 0)
    {
      throw new InvalidDatatypeValueException("Incomplete value");
    }
    if (datatype < 0 || datatype > GMONTH)
    {
      throw new IllegalArgumentException("Illegal datatype value " + datatype);
    }

    this.date = null;
    this.dataType = datatype;
    this.xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(value);
  }

  public XMLCalendar(Date date, short dataType)
  {
    this.xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(XMLCalendar.EDATE_FORMATS[0].format(date));
    this.dataType = dataType;
    this.date = date;
  }
  
  public static int compare(XMLCalendar value1, XMLCalendar value2)
  {
    switch (value1.xmlGregorianCalendar.compare(value2.xmlGregorianCalendar))
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
    return 
      object instanceof XMLCalendar ? 
         xmlGregorianCalendar.equals(((XMLCalendar)object).xmlGregorianCalendar) :
         object instanceof XMLGregorianCalendar && xmlGregorianCalendar.equals(object);
  }

  @Override
  public int hashCode()
  {
    return xmlGregorianCalendar.hashCode();
  }
  
  @Override
  public String toString()
  {
    return xmlGregorianCalendar.toString();
  }

  public Date getDate()
  {
    if (date == null)
    {
      try
      {
        if (dataType == XMLCalendar.DATETIME)
        {
          try
          {
            date = XMLCalendar.EDATE_FORMATS[0].parse(toXMLFormat());
          }
          catch (Exception e)
          {
            try
            {
              date = XMLCalendar.EDATE_FORMATS[1].parse(toXMLFormat());
            }
            catch (Exception e2)
            {
              try
              {
                date = XMLCalendar.EDATE_FORMATS[2].parse(toXMLFormat());
              }
              catch (Exception e3)
              {
                date = XMLCalendar.EDATE_FORMATS[3].parse(toXMLFormat());
              }
            }
          }
        }
        else if (dataType == XMLCalendar.DATE)
        {
          try
          {
            date = XMLCalendar.EDATE_FORMATS[4].parse(toXMLFormat());
          }
          catch (Exception e)
          {
            date = XMLCalendar.EDATE_FORMATS[5].parse(toXMLFormat());
          }
        }
      }
      catch (Exception e)
      {
        throw new WrappedException(e);
      }
    }
    return date;
  }

  private static class SafeSimpleDateFormat extends SimpleDateFormat
  {
    private static final long serialVersionUID = 1L;

    public SafeSimpleDateFormat(String pattern)
    {
      super(pattern, Locale.ENGLISH);
    }

    @Override
    public synchronized Date parse(String source) throws ParseException
    {
      return super.parse(source);
    }
  }

  @Override
  public void add(Duration duration)
  {
    xmlGregorianCalendar.add(duration);
    date = null;
  }

  @Override
  public void clear()
  {
    xmlGregorianCalendar.clear();
    date = null;
  }

  @Override
  public Object clone()
  {
    return new XMLCalendar(xmlGregorianCalendar, date, dataType);
  }

  @Override
  public int compare(XMLGregorianCalendar xmlGregorianCalendar)
  {
    return
      this.xmlGregorianCalendar.compare
       (xmlGregorianCalendar instanceof XMLCalendar ? ((XMLCalendar)xmlGregorianCalendar).xmlGregorianCalendar : xmlGregorianCalendar);
  }

  @Override
  public int getDay()
  {
    return xmlGregorianCalendar.getDay();
  }

  @Override
  public BigInteger getEon()
  {
    return xmlGregorianCalendar.getEon();
  }

  @Override
  public BigInteger getEonAndYear()
  {
    return xmlGregorianCalendar.getEonAndYear();
  }

  @Override
  public BigDecimal getFractionalSecond()
  {
    return xmlGregorianCalendar.getFractionalSecond();
  }

  @Override
  public int getHour()
  {
    return xmlGregorianCalendar.getHour();
  }

  @Override
  public int getMinute()
  {
    return xmlGregorianCalendar.getMinute();
  }

  @Override
  public int getMonth()
  {
    return xmlGregorianCalendar.getMonth();
  }

  @Override
  public int getSecond()
  {
    return xmlGregorianCalendar.getSecond();
  }

  @Override
  public TimeZone getTimeZone(int defaultTimeZone)
  {
    return xmlGregorianCalendar.getTimeZone(defaultTimeZone);
  }

  @Override
  public int getTimezone()
  {
    return xmlGregorianCalendar.getTimezone();
  }

  @Override
  public QName getXMLSchemaType()
  {
    return xmlGregorianCalendar.getXMLSchemaType();
  }

  @Override
  public int getYear()
  {
    return xmlGregorianCalendar.getYear();
  }

  @Override
  public boolean isValid()
  {
    return xmlGregorianCalendar.isValid();
  }

  @Override
  public XMLGregorianCalendar normalize()
  {
    return xmlGregorianCalendar.normalize();
  }

  @Override
  public void reset()
  {
    date = null;
    xmlGregorianCalendar.reset();
  }

  @Override
  public void setDay(int day)
  {
    xmlGregorianCalendar.setDay(day);
  }

  @Override
  public void setFractionalSecond(BigDecimal fractionalSecond)
  {
    xmlGregorianCalendar.setFractionalSecond(fractionalSecond);
  }

  @Override
  public void setHour(int hour)
  {
    xmlGregorianCalendar.setHour(hour);
  }

  @Override
  public void setMillisecond(int millisecond)
  {
    xmlGregorianCalendar.setMillisecond(millisecond);
  }

  @Override
  public void setMinute(int minute)
  {
    xmlGregorianCalendar.setMinute(minute);
  }

  @Override
  public void setMonth(int month)
  {
    xmlGregorianCalendar.setMonth(month);
  }

  @Override
  public void setSecond(int second)
  {
    xmlGregorianCalendar.setSecond(second);
  }

  @Override
  public void setTimezone(int offset)
  {
    xmlGregorianCalendar.setTimezone(offset);
  }

  @Override
  public void setYear(BigInteger year)
  {
    xmlGregorianCalendar.setYear(year);
  }

  @Override
  public void setYear(int year)
  {
    xmlGregorianCalendar.setYear(year);
  }

  @Override
  public GregorianCalendar toGregorianCalendar()
  {
    return xmlGregorianCalendar.toGregorianCalendar();
  }

  @Override
  public GregorianCalendar toGregorianCalendar(TimeZone timeZone, Locale locale, XMLGregorianCalendar defaults)
  {
    return xmlGregorianCalendar.toGregorianCalendar(timeZone, locale, defaults);
  }

  @Override
  public String toXMLFormat()
  {
    return xmlGregorianCalendar.toXMLFormat();
  }
}
