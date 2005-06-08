/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: SDOUtilProtectedGetTest.java,v 1.3 2005/06/08 06:17:25 nickb Exp $
 */
package org.eclipse.emf.test.sdo.types;


import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.sdo.util.SDOUtil;


/**
 * @author marcelop
 */
public class SDOUtilProtectedGetTest extends TestCase
{
  private static class TypeConverter
  {
    private static Map methodByClass = new HashMap();

    private static Object get(String methodName, Object value) throws Exception
    {
      Method getMethod = (Method)methodByClass.get(methodName);
      if (getMethod == null)
      {
        getMethod = SDOUtil.class.getDeclaredMethod(methodName, new Class []{ Object.class });
        getMethod.setAccessible(true);
        methodByClass.put(methodName, getMethod);
      }
      return getMethod.invoke(null, new Object []{ value });
    }

    public static BigDecimal getBigDecimal(Object value) throws Exception
    {
      return (BigDecimal)get("getBigDecimal", value);
    }

    public static BigInteger getBigInteger(Object value) throws Exception
    {
      return (BigInteger)get("getBigInteger", value);
    }

    public static Date getDate(Object value) throws Exception
    {
      return (Date)get("getDate", value);
    }

    public static String getString(Object value) throws Exception
    {
      return (String)get("getString", value);
    }

    public static byte[] getBytes(Object value) throws Exception
    {
      return (byte[])get("getBytes", value);
    }

    public static boolean getBoolean(Object value) throws Exception
    {
      return ((Boolean)get("getBoolean", value)).booleanValue();
    }

    public static byte getByte(Object value) throws Exception
    {
      return ((Byte)get("getByte", value)).byteValue();
    }

    public static char getChar(Object value) throws Exception
    {
      return ((Character)get("getChar", value)).charValue();
    }

    public static double getDouble(Object value) throws Exception
    {
      return ((Double)get("getDouble", value)).doubleValue();
    }

    public static float getFloat(Object value) throws Exception
    {
      return ((Float)get("getFloat", value)).floatValue();
    }

    public static int getInt(Object value) throws Exception
    {
      return ((Integer)get("getInt", value)).intValue();
    }

    public static long getLong(Object value) throws Exception
    {
      return ((Long)get("getLong", value)).longValue();
    }

    public static short getShort(Object value) throws Exception
    {
      return ((Short)get("getShort", value)).shortValue();
    }
  }

  public static Test suite()
  {
    TestSuite testSuite = new TestSuite("SDOUtilProtectedGetTest");
    testSuite.addTestSuite(SDOUtilProtectedGetTest.class);
    return testSuite;
  }

  public void testGetBoolean() throws Exception
  {
    assertEquals(true, TypeConverter.getBoolean(Boolean.TRUE));
    assertEquals(false, TypeConverter.getBoolean(Boolean.FALSE));

    assertEquals(true, TypeConverter.getBoolean(String.valueOf(true)));
    assertEquals(true, TypeConverter.getBoolean("true"));
    assertEquals(false, TypeConverter.getBoolean(String.valueOf(false)));
    assertEquals(false, TypeConverter.getBoolean("false"));

    assertEquals(false, TypeConverter.getBoolean(null));
  }

  public void testGetByte() throws Exception
  {
    assertEquals(Byte.MAX_VALUE, TypeConverter.getByte(new Byte(Byte.MAX_VALUE)));
    assertEquals(Byte.MIN_VALUE, TypeConverter.getByte(new Byte(Byte.MIN_VALUE)));
    assertEquals((byte)0, TypeConverter.getByte(new Byte((byte)0)));
    double randon = Math.random();
    assertEquals((byte)randon, TypeConverter.getByte(new Byte((byte)randon)));

    assertEquals(Byte.MAX_VALUE, TypeConverter.getByte(new Double(Byte.MAX_VALUE)));
    assertEquals(Byte.MIN_VALUE, TypeConverter.getByte(new Double(Byte.MIN_VALUE)));
    assertEquals((byte)0, TypeConverter.getByte(new Double(0)));
    randon = Math.random();
    assertEquals((byte)randon, TypeConverter.getByte(new Double((byte)randon)));

    assertEquals(Byte.MAX_VALUE, TypeConverter.getByte(new Float(Byte.MAX_VALUE)));
    assertEquals(Byte.MIN_VALUE, TypeConverter.getByte(new Float(Byte.MIN_VALUE)));
    assertEquals((byte)0, TypeConverter.getByte(new Float((byte)0)));
    randon = Math.random();
    assertEquals((byte)randon, TypeConverter.getByte(new Float((byte)randon)));

    assertEquals(Byte.MAX_VALUE, TypeConverter.getByte(new Integer(Byte.MAX_VALUE)));
    assertEquals(Byte.MIN_VALUE, TypeConverter.getByte(new Integer(Byte.MIN_VALUE)));
    assertEquals((byte)0, TypeConverter.getByte(new Integer((byte)0)));
    randon = Math.random();
    assertEquals((byte)randon, TypeConverter.getByte(new Integer((byte)randon)));

    assertEquals(Byte.MAX_VALUE, TypeConverter.getByte(new Long(Byte.MAX_VALUE)));
    assertEquals(Byte.MIN_VALUE, TypeConverter.getByte(new Long(Byte.MIN_VALUE)));
    assertEquals((byte)0, TypeConverter.getByte(new Long((byte)0)));
    randon = Math.random();
    assertEquals((byte)randon, TypeConverter.getByte(new Long((byte)randon)));

    assertEquals(Byte.MAX_VALUE, TypeConverter.getByte(new Short(Byte.MAX_VALUE)));
    assertEquals(Byte.MIN_VALUE, TypeConverter.getByte(new Short(Byte.MIN_VALUE)));
    assertEquals((byte)0, TypeConverter.getByte(new Short((byte)0)));
    randon = Math.random();
    assertEquals((byte)randon, TypeConverter.getByte(new Short((byte)randon)));

    assertEquals(Byte.MAX_VALUE, TypeConverter.getByte(String.valueOf(Byte.MAX_VALUE)));
    assertEquals(Byte.MIN_VALUE, TypeConverter.getByte(String.valueOf(Byte.MIN_VALUE)));
    assertEquals((byte)0, TypeConverter.getByte(String.valueOf((byte)0)));
    randon = Math.random();
    assertEquals((byte)randon, TypeConverter.getByte(String.valueOf((byte)randon)));

    assertEquals(0, TypeConverter.getByte(null));
  }

  public void testGetShort() throws Exception
  {
    assertEquals((byte)Short.MAX_VALUE, TypeConverter.getShort(new Byte((byte)Short.MAX_VALUE)));
    assertEquals((byte)Short.MIN_VALUE, TypeConverter.getShort(new Byte((byte)Short.MIN_VALUE)));
    assertEquals((byte)0, TypeConverter.getShort(new Byte((byte)0)));
    double randon = Math.random();
    assertEquals((short)randon, TypeConverter.getShort(new Byte((byte)randon)));

    assertEquals(Short.MAX_VALUE, TypeConverter.getShort(new Double(Short.MAX_VALUE)));
    assertEquals(Short.MIN_VALUE, TypeConverter.getShort(new Double(Short.MIN_VALUE)));
    assertEquals((short)0, TypeConverter.getShort(new Double(0)));
    randon = Math.random();
    assertEquals((short)randon, TypeConverter.getShort(new Double((short)randon)));

    assertEquals(Short.MAX_VALUE, TypeConverter.getShort(new Float(Short.MAX_VALUE)));
    assertEquals(Short.MIN_VALUE, TypeConverter.getShort(new Float(Short.MIN_VALUE)));
    assertEquals((short)0, TypeConverter.getShort(new Float((short)0)));
    randon = Math.random();
    assertEquals((short)randon, TypeConverter.getShort(new Float((short)randon)));

    assertEquals(Short.MAX_VALUE, TypeConverter.getShort(new Integer(Short.MAX_VALUE)));
    assertEquals(Short.MIN_VALUE, TypeConverter.getShort(new Integer(Short.MIN_VALUE)));
    assertEquals((short)0, TypeConverter.getShort(new Integer((short)0)));
    randon = Math.random();
    assertEquals((short)randon, TypeConverter.getShort(new Integer((short)randon)));

    assertEquals(Short.MAX_VALUE, TypeConverter.getShort(new Long(Short.MAX_VALUE)));
    assertEquals(Short.MIN_VALUE, TypeConverter.getShort(new Long(Short.MIN_VALUE)));
    assertEquals((short)0, TypeConverter.getShort(new Long((short)0)));
    randon = Math.random();
    assertEquals((short)randon, TypeConverter.getShort(new Long((short)randon)));

    assertEquals(Short.MAX_VALUE, TypeConverter.getShort(new Short(Short.MAX_VALUE)));
    assertEquals(Short.MIN_VALUE, TypeConverter.getShort(new Short(Short.MIN_VALUE)));
    assertEquals((short)0, TypeConverter.getShort(new Short((short)0)));
    randon = Math.random();
    assertEquals((short)randon, TypeConverter.getShort(new Short((short)randon)));

    assertEquals(Short.MAX_VALUE, TypeConverter.getShort(String.valueOf(Short.MAX_VALUE)));
    assertEquals(Short.MIN_VALUE, TypeConverter.getShort(String.valueOf(Short.MIN_VALUE)));
    assertEquals((short)0, TypeConverter.getShort(String.valueOf((short)0)));
    randon = Math.random();
    assertEquals((short)randon, TypeConverter.getShort(String.valueOf((short)randon)));

    assertEquals(0, TypeConverter.getShort(null));
  }

  public void testGetChar() throws Exception
  {
    assertEquals(Character.MAX_VALUE, TypeConverter.getChar(new Character(Character.MAX_VALUE)));
    assertEquals(Character.MIN_VALUE, TypeConverter.getChar(new Character(Character.MIN_VALUE)));
    assertEquals((char)0, TypeConverter.getChar(new Character((char)0)));
    double randon = Math.random();
    assertEquals((char)randon, TypeConverter.getChar(new Character((char)randon)));

    assertEquals(Character.MAX_VALUE, TypeConverter.getChar(String.valueOf(Character.MAX_VALUE)));
    assertEquals(Character.MIN_VALUE, TypeConverter.getChar(String.valueOf(Character.MIN_VALUE)));
    assertEquals((char)0, TypeConverter.getChar(String.valueOf((char)0)));
    randon = Math.random();
    assertEquals((char)randon, TypeConverter.getChar(String.valueOf((char)randon)));

    assertEquals(0, TypeConverter.getChar(null));
  }

  public void testGetDouble() throws Exception
  {
    assertEquals((byte)Double.MAX_VALUE, TypeConverter.getDouble(new Byte((byte)Double.MAX_VALUE)), 0);
    assertEquals((byte)Double.MIN_VALUE, TypeConverter.getDouble(new Byte((byte)Double.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getDouble(new Byte((byte)0)), 0);
    double randon = Math.random();
    assertEquals((byte)randon, TypeConverter.getDouble(new Byte((byte)randon)), 0);

    assertEquals(Double.MAX_VALUE, TypeConverter.getDouble(new Double(Double.MAX_VALUE)), 0);
    assertEquals(Double.MIN_VALUE, TypeConverter.getDouble(new Double(Double.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getDouble(new Double(0)), 0);
    randon = Math.random();
    assertEquals(randon, TypeConverter.getDouble(new Double(randon)), 0);

    assertEquals((float)Double.MAX_VALUE, TypeConverter.getDouble(new Float(Double.MAX_VALUE)), 0);
    assertEquals((float)Double.MIN_VALUE, TypeConverter.getDouble(new Float(Double.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getDouble(new Float((double)0)), 0);
    randon = Math.random();
    assertEquals((float)randon, TypeConverter.getDouble(new Float(randon)), 0);

    assertEquals((int)Double.MAX_VALUE, TypeConverter.getDouble(new Integer((int)Double.MAX_VALUE)), 0);
    assertEquals((int)Double.MIN_VALUE, TypeConverter.getDouble(new Integer((int)Double.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getDouble(new Integer(0)), 0);
    randon = Math.random();
    assertEquals((int)randon, TypeConverter.getDouble(new Integer((int)randon)), 0);

    assertEquals((long)Double.MAX_VALUE, TypeConverter.getDouble(new Long((long)Double.MAX_VALUE)), 0);
    assertEquals((long)Double.MIN_VALUE, TypeConverter.getDouble(new Long((long)Double.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getDouble(new Long(0)), 0);
    randon = Math.random();
    assertEquals((long)randon, TypeConverter.getDouble(new Long((long)randon)), 0);

    assertEquals((short)Double.MAX_VALUE, TypeConverter.getDouble(new Short((short)Double.MAX_VALUE)), 0);
    assertEquals((short)Double.MIN_VALUE, TypeConverter.getDouble(new Short((short)Double.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getDouble(new Short((short)0)), 0);
    randon = Math.random();
    assertEquals((short)randon, TypeConverter.getDouble(new Short((short)randon)), 0);

    assertEquals(Double.MAX_VALUE, TypeConverter.getDouble(String.valueOf(Double.MAX_VALUE)), 0);
    assertEquals(Double.MIN_VALUE, TypeConverter.getDouble(String.valueOf(Double.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getDouble(String.valueOf((double)0)), 0);
    randon = Math.random();
    assertEquals(randon, TypeConverter.getDouble(String.valueOf(randon)), 0);

    assertEquals((short)Double.MAX_VALUE, TypeConverter.getDouble(new Short((short)Double.MAX_VALUE)), 0);
    assertEquals((short)Double.MIN_VALUE, TypeConverter.getDouble(new Short((short)Double.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getDouble(new Short((short)0)), 0);
    randon = Math.random();
    assertEquals((short)randon, TypeConverter.getDouble(new Short((short)randon)), 0);

    assertEquals(Double.MAX_VALUE, TypeConverter.getDouble(new BigDecimal(Double.MAX_VALUE)), 0);
    assertEquals(Double.MIN_VALUE, TypeConverter.getDouble(new BigDecimal(Double.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getDouble(new BigDecimal(0)), 0);
    randon = Math.random();
    assertEquals(randon, TypeConverter.getDouble(new BigDecimal(randon)), 0);

    assertEquals(Integer.MAX_VALUE, TypeConverter.getDouble(new BigInteger(String.valueOf(Integer.MAX_VALUE))), 0);
    assertEquals(Integer.MIN_VALUE, TypeConverter.getDouble(new BigInteger(String.valueOf(Integer.MIN_VALUE))), 0);
    assertEquals(0, TypeConverter.getDouble(String.valueOf((double)0)), 0);
    randon = Math.random();
    assertEquals(randon, TypeConverter.getDouble(String.valueOf(randon)), 0);

    assertEquals(0, TypeConverter.getDouble(null), 0);
  }

  public void testGetFloat() throws Exception
  {
    assertEquals((byte)Float.MAX_VALUE, TypeConverter.getFloat(new Byte((byte)Float.MAX_VALUE)), 0);
    assertEquals((byte)Float.MIN_VALUE, TypeConverter.getFloat(new Byte((byte)Float.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getFloat(new Byte((byte)0)), 0);
    double randon = Math.random();
    assertEquals((byte)randon, TypeConverter.getFloat(new Byte((byte)randon)), 0);

    assertEquals(Float.MAX_VALUE, TypeConverter.getFloat(new Double(Float.MAX_VALUE)), 0);
    assertEquals(Float.MIN_VALUE, TypeConverter.getFloat(new Double(Float.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getFloat(new Double(0)), 0);
    randon = Math.random();
    assertEquals((float)randon, TypeConverter.getFloat(new Double(randon)), 0);

    assertEquals(Float.MAX_VALUE, TypeConverter.getFloat(new Float(Float.MAX_VALUE)), 0);
    assertEquals(Float.MIN_VALUE, TypeConverter.getFloat(new Float(Float.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getFloat(new Float(0)), 0);
    randon = Math.random();
    assertEquals((float)randon, TypeConverter.getFloat(new Float(randon)), 0);

    assertEquals((int)Float.MAX_VALUE, TypeConverter.getFloat(new Integer((int)Float.MAX_VALUE)), 0);
    assertEquals((int)Float.MIN_VALUE, TypeConverter.getFloat(new Integer((int)Float.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getFloat(new Integer(0)), 0);
    randon = Math.random();
    assertEquals((int)randon, TypeConverter.getFloat(new Integer((int)randon)), 0);

    assertEquals((long)Float.MAX_VALUE, TypeConverter.getFloat(new Long((long)Float.MAX_VALUE)), 0);
    assertEquals((long)Float.MIN_VALUE, TypeConverter.getFloat(new Long((long)Float.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getFloat(new Long(0)), 0);
    randon = Math.random();
    assertEquals((long)randon, TypeConverter.getFloat(new Long((long)randon)), 0);

    assertEquals((short)Float.MAX_VALUE, TypeConverter.getFloat(new Short((short)Float.MAX_VALUE)), 0);
    assertEquals((short)Float.MIN_VALUE, TypeConverter.getFloat(new Short((short)Float.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getFloat(new Short((short)0)), 0);
    randon = Math.random();
    assertEquals((short)randon, TypeConverter.getFloat(new Short((short)randon)), 0);

    assertEquals(Float.MAX_VALUE, TypeConverter.getFloat(String.valueOf(Float.MAX_VALUE)), 0);
    assertEquals(Float.MIN_VALUE, TypeConverter.getFloat(String.valueOf(Float.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getFloat(String.valueOf((float)0)), 0);
    randon = Math.random();
    assertEquals((float)randon, TypeConverter.getFloat(String.valueOf((float)randon)), 0);

    assertEquals(Float.MAX_VALUE, TypeConverter.getFloat(new BigDecimal(Float.MAX_VALUE)), 0);
    assertEquals(Float.MIN_VALUE, TypeConverter.getFloat(new BigDecimal(Float.MIN_VALUE)), 0);
    assertEquals(0, TypeConverter.getFloat(new BigDecimal(0)), 0);
    randon = Math.random();
    assertEquals((float)randon, TypeConverter.getFloat(new BigDecimal(randon)), 0);

    assertEquals(Integer.MAX_VALUE, TypeConverter.getFloat(new BigInteger(String.valueOf(Integer.MAX_VALUE))), 0);
    assertEquals(Integer.MIN_VALUE, TypeConverter.getFloat(new BigInteger(String.valueOf(Integer.MIN_VALUE))), 0);
    assertEquals(0, TypeConverter.getFloat(new BigInteger(String.valueOf(0))), 0);
    randon = Math.random();
    assertEquals((int)randon, TypeConverter.getFloat(new BigInteger(String.valueOf((int)randon))), 0);

    assertEquals(0, TypeConverter.getFloat(null), 0);
  }

  public void testGetInt() throws Exception
  {
    assertEquals((byte)Integer.MAX_VALUE, TypeConverter.getInt(new Byte((byte)Integer.MAX_VALUE)));
    assertEquals((byte)Integer.MIN_VALUE, TypeConverter.getInt(new Byte((byte)Integer.MIN_VALUE)));
    assertEquals(0, TypeConverter.getInt(new Byte((byte)0)));
    double randon = Math.random();
    assertEquals((byte)randon, TypeConverter.getInt(new Byte((byte)randon)));

    assertEquals(Integer.MAX_VALUE, TypeConverter.getInt(new Double(Integer.MAX_VALUE)));
    assertEquals(Integer.MIN_VALUE, TypeConverter.getInt(new Double(Integer.MIN_VALUE)));
    assertEquals(0, TypeConverter.getInt(new Double(0)));
    randon = Math.random();
    assertEquals((int)randon, TypeConverter.getInt(new Double(randon)));

    assertEquals(Integer.MAX_VALUE, TypeConverter.getInt(new Float(Integer.MAX_VALUE)));
    assertEquals(Integer.MIN_VALUE, TypeConverter.getInt(new Float(Integer.MIN_VALUE)));
    assertEquals(0, TypeConverter.getInt(new Float(0)));
    randon = Math.random();
    assertEquals((int)randon, TypeConverter.getInt(new Float((int)randon)));

    assertEquals(Integer.MAX_VALUE, TypeConverter.getInt(new Integer(Integer.MAX_VALUE)));
    assertEquals(Integer.MIN_VALUE, TypeConverter.getInt(new Integer(Integer.MIN_VALUE)));
    assertEquals(0, TypeConverter.getInt(new Integer(0)));
    randon = Math.random();
    assertEquals((int)randon, TypeConverter.getInt(new Integer((int)randon)));

    assertEquals(Integer.MAX_VALUE, TypeConverter.getInt(new Long(Integer.MAX_VALUE)));
    assertEquals(Integer.MIN_VALUE, TypeConverter.getInt(new Long(Integer.MIN_VALUE)));
    assertEquals(0, TypeConverter.getInt(new Long(0)));
    randon = Math.random();
    assertEquals((int)randon, TypeConverter.getInt(new Long((long)randon)));

    assertEquals((short)Integer.MAX_VALUE, TypeConverter.getInt(new Short((short)Integer.MAX_VALUE)));
    assertEquals((short)Integer.MIN_VALUE, TypeConverter.getInt(new Short((short)Integer.MIN_VALUE)));
    assertEquals(0, TypeConverter.getInt(new Short((short)0)));
    randon = Math.random();
    assertEquals((short)randon, TypeConverter.getInt(new Short((short)randon)));

    assertEquals(Integer.MAX_VALUE, TypeConverter.getInt(String.valueOf(Integer.MAX_VALUE)));
    assertEquals(Integer.MIN_VALUE, TypeConverter.getInt(String.valueOf(Integer.MIN_VALUE)));
    assertEquals(0, TypeConverter.getInt(String.valueOf(0)));
    randon = Math.random();
    assertEquals((int)randon, TypeConverter.getInt(String.valueOf((int)randon)));

    assertEquals(Integer.MAX_VALUE, TypeConverter.getInt(new BigDecimal(Integer.MAX_VALUE)));
    assertEquals(Integer.MIN_VALUE, TypeConverter.getInt(new BigDecimal(Integer.MIN_VALUE)));
    assertEquals(0, TypeConverter.getInt(new BigDecimal(0)));
    randon = Math.random();
    assertEquals((int)randon, TypeConverter.getInt(new BigDecimal(randon)));

    assertEquals(Integer.MAX_VALUE, TypeConverter.getInt(new BigInteger(String.valueOf(Integer.MAX_VALUE))));
    assertEquals(Integer.MIN_VALUE, TypeConverter.getInt(new BigInteger(String.valueOf(Integer.MIN_VALUE))));
    assertEquals(0, TypeConverter.getInt(new BigInteger(String.valueOf(0))));
    randon = Math.random();
    assertEquals((int)randon, TypeConverter.getInt(new BigInteger(String.valueOf((int)randon))));

    assertEquals(0, TypeConverter.getInt(null));
  }

  public void testGetLong() throws Exception
  {
    assertEquals((byte)Long.MAX_VALUE, TypeConverter.getLong(new Byte((byte)Long.MAX_VALUE)));
    assertEquals((byte)Long.MIN_VALUE, TypeConverter.getLong(new Byte((byte)Long.MIN_VALUE)));
    assertEquals(0, TypeConverter.getLong(new Byte((byte)0)));
    double randon = Math.random();
    assertEquals((byte)randon, TypeConverter.getLong(new Byte((byte)randon)));

    assertEquals(Long.MAX_VALUE, TypeConverter.getLong(new Double(Long.MAX_VALUE)));
    assertEquals(Long.MIN_VALUE, TypeConverter.getLong(new Double(Long.MIN_VALUE)));
    assertEquals(0, TypeConverter.getLong(new Double(0)));
    randon = Math.random();
    assertEquals((long)randon, TypeConverter.getLong(new Double(randon)));

    assertEquals(Long.MAX_VALUE, TypeConverter.getLong(new Float(Long.MAX_VALUE)));
    assertEquals(Long.MIN_VALUE, TypeConverter.getLong(new Float(Long.MIN_VALUE)));
    assertEquals(0, TypeConverter.getLong(new Float(0)));
    randon = Math.random();
    assertEquals((long)randon, TypeConverter.getLong(new Float((long)randon)));

    assertEquals((int)Long.MAX_VALUE, TypeConverter.getLong(new Integer((int)Long.MAX_VALUE)));
    assertEquals((int)Long.MIN_VALUE, TypeConverter.getLong(new Integer((int)Long.MIN_VALUE)));
    assertEquals(0, TypeConverter.getLong(new Integer(0)));
    randon = Math.random();
    assertEquals((int)randon, TypeConverter.getLong(new Integer((int)randon)));

    assertEquals(Long.MAX_VALUE, TypeConverter.getLong(new Long(Long.MAX_VALUE)));
    assertEquals(Long.MIN_VALUE, TypeConverter.getLong(new Long(Long.MIN_VALUE)));
    assertEquals(0, TypeConverter.getLong(new Long(0)));
    randon = Math.random();
    assertEquals((long)randon, TypeConverter.getLong(new Long((long)randon)));

    assertEquals((short)Long.MAX_VALUE, TypeConverter.getLong(new Short((short)Long.MAX_VALUE)));
    assertEquals((short)Long.MIN_VALUE, TypeConverter.getLong(new Short((short)Long.MIN_VALUE)));
    assertEquals(0, TypeConverter.getLong(new Short((short)0)));
    randon = Math.random();
    assertEquals((short)randon, TypeConverter.getLong(new Short((short)randon)));

    assertEquals(Long.MAX_VALUE, TypeConverter.getLong(String.valueOf(Long.MAX_VALUE)));
    assertEquals(Long.MIN_VALUE, TypeConverter.getLong(String.valueOf(Long.MIN_VALUE)));
    assertEquals(0, TypeConverter.getLong(String.valueOf((long)0)));
    randon = Math.random();
    assertEquals((long)randon, TypeConverter.getLong(String.valueOf((long)randon)));

    assertEquals(Long.MAX_VALUE, TypeConverter.getLong(new BigDecimal(String.valueOf(Long.MAX_VALUE))));
    assertEquals(Long.MIN_VALUE, TypeConverter.getLong(new BigDecimal(String.valueOf(Long.MIN_VALUE))));
    assertEquals(0, TypeConverter.getLong(new BigDecimal(0)));
    randon = Math.random();
    assertEquals((long)randon, TypeConverter.getLong(new BigDecimal(randon)));

    assertEquals(Long.MAX_VALUE, TypeConverter.getLong(new BigInteger(String.valueOf(Long.MAX_VALUE))));
    assertEquals(Long.MIN_VALUE, TypeConverter.getLong(new BigInteger(String.valueOf(Long.MIN_VALUE))));
    assertEquals(0, TypeConverter.getLong(new BigInteger(String.valueOf((long)0))));
    randon = Math.random();
    assertEquals((long)randon, TypeConverter.getLong(new BigInteger(String.valueOf((long)randon))));

    assertEquals(Long.MAX_VALUE, TypeConverter.getLong(new Date(Long.MAX_VALUE)));
    assertEquals(new Date(0).getTime(), TypeConverter.getLong(new Date(0)));
    randon = Math.random();
    assertEquals((long)randon, TypeConverter.getLong(new BigInteger(String.valueOf((long)randon))));

    assertEquals(0, TypeConverter.getLong(null));
  }

  public void testGetString() throws Exception
  {
    assertEquals(String.valueOf(true), TypeConverter.getString(new Boolean(true)));
    assertEquals(String.valueOf(false), TypeConverter.getString(new Boolean(false)));

    assertEquals(String.valueOf((byte)Integer.MAX_VALUE), TypeConverter.getString(new Byte((byte)Integer.MAX_VALUE)));
    assertEquals(String.valueOf((byte)Integer.MIN_VALUE), TypeConverter.getString(new Byte((byte)Integer.MIN_VALUE)));
    assertEquals(String.valueOf((byte)0), TypeConverter.getString(new Byte((byte)0)));
    double randon = Math.random();
    assertEquals(String.valueOf((byte)randon), TypeConverter.getString(new Byte((byte)randon)));

    assertEquals(String.valueOf((double)Integer.MAX_VALUE), TypeConverter.getString(new Double(Integer.MAX_VALUE)));
    assertEquals(String.valueOf((double)Integer.MIN_VALUE), TypeConverter.getString(new Double(Integer.MIN_VALUE)));
    assertEquals(String.valueOf((double)0), TypeConverter.getString(new Double(0)));
    randon = Math.random();
    assertEquals(String.valueOf(randon), TypeConverter.getString(new Double(randon)));

    assertEquals(String.valueOf((float)Integer.MAX_VALUE), TypeConverter.getString(new Float(Integer.MAX_VALUE)));
    assertEquals(String.valueOf((float)Integer.MIN_VALUE), TypeConverter.getString(new Float(Integer.MIN_VALUE)));
    assertEquals(String.valueOf((float)0), TypeConverter.getString(new Float(0)));
    randon = Math.random();
    assertEquals(String.valueOf((float)randon), TypeConverter.getString(new Float((float)randon)));

    assertEquals(String.valueOf(Integer.MAX_VALUE), TypeConverter.getString(new Integer(Integer.MAX_VALUE)));
    assertEquals(String.valueOf(Integer.MIN_VALUE), TypeConverter.getString(new Integer(Integer.MIN_VALUE)));
    assertEquals(String.valueOf(0), TypeConverter.getString(new Integer(0)));
    randon = Math.random();
    assertEquals(String.valueOf((int)randon), TypeConverter.getString(new Integer((int)randon)));

    assertEquals(String.valueOf((long)Integer.MAX_VALUE), TypeConverter.getString(new Long(Integer.MAX_VALUE)));
    assertEquals(String.valueOf((long)Integer.MIN_VALUE), TypeConverter.getString(new Long(Integer.MIN_VALUE)));
    assertEquals(String.valueOf((long)0), TypeConverter.getString(new Long(0)));
    randon = Math.random();
    assertEquals(String.valueOf((long)randon), TypeConverter.getString(new Long((long)randon)));

    assertEquals(String.valueOf((short)Integer.MAX_VALUE), TypeConverter.getString(new Short((short)Integer.MAX_VALUE)));
    assertEquals(String.valueOf((short)Integer.MIN_VALUE), TypeConverter.getString(new Short((short)Integer.MIN_VALUE)));
    assertEquals(String.valueOf((short)0), TypeConverter.getString(new Short((short)0)));
    randon = Math.random();
    assertEquals(String.valueOf((short)randon), TypeConverter.getString(new Short((short)randon)));

    assertEquals(String.valueOf(Integer.MAX_VALUE), TypeConverter.getString(String.valueOf(Integer.MAX_VALUE)));
    assertEquals(String.valueOf(Integer.MIN_VALUE), TypeConverter.getString(String.valueOf(Integer.MIN_VALUE)));
    assertEquals(String.valueOf(0), TypeConverter.getString(String.valueOf(0)));
    randon = Math.random();
    assertEquals(String.valueOf((int)randon), TypeConverter.getString(String.valueOf((int)randon)));

    assertEquals(String.valueOf(Integer.MAX_VALUE), TypeConverter.getString(new BigDecimal(Integer.MAX_VALUE)));
    assertEquals(String.valueOf(Integer.MIN_VALUE), TypeConverter.getString(new BigDecimal(Integer.MIN_VALUE)));
    assertEquals(String.valueOf(0), TypeConverter.getString(new BigDecimal(0)));
    randon = Math.random();
    assertEquals(String.valueOf((int)randon), TypeConverter.getString(new BigDecimal((int)randon)));

    assertEquals(String.valueOf(Integer.MAX_VALUE), TypeConverter.getString(new BigInteger(String.valueOf(Integer.MAX_VALUE))));
    assertEquals(String.valueOf(Integer.MIN_VALUE), TypeConverter.getString(new BigInteger(String.valueOf(Integer.MIN_VALUE))));
    assertEquals(String.valueOf(0), TypeConverter.getString(new BigInteger(String.valueOf(0))));
    randon = Math.random();
    assertEquals(String.valueOf((int)randon), TypeConverter.getString(new BigInteger(String.valueOf((int)randon))));

    assertEquals("test", TypeConverter.getString("test"));

    assertNull(TypeConverter.getString(null));
  }

  public void testGetBytes() throws Exception
  {
    byte[] array = new byte []{ Byte.MIN_VALUE, 0, Byte.MAX_VALUE };
    assertTrue(Arrays.equals(array, TypeConverter.getBytes(array)));
    assertTrue(Arrays.equals(new byte [0], TypeConverter.getBytes(new byte [0])));

    BigInteger bigInteger = new BigInteger(String.valueOf(Integer.MAX_VALUE));
    array = bigInteger.toByteArray();
    assertTrue(Arrays.equals(array, TypeConverter.getBytes(bigInteger)));
    bigInteger = new BigInteger(String.valueOf(Integer.MIN_VALUE));
    array = bigInteger.toByteArray();
    assertTrue(Arrays.equals(array, TypeConverter.getBytes(bigInteger)));
    bigInteger = new BigInteger(String.valueOf(0));
    array = bigInteger.toByteArray();
    assertTrue(Arrays.equals(array, TypeConverter.getBytes(bigInteger)));
    bigInteger = new BigInteger(String.valueOf((int)Math.random()));
    array = bigInteger.toByteArray();
    assertTrue(Arrays.equals(array, TypeConverter.getBytes(bigInteger)));

    assertNull(TypeConverter.getBytes(null));
  }

  public void testGetBigDecimal() throws Exception
  {
    assertEquals(new BigDecimal(Double.MAX_VALUE), TypeConverter.getBigDecimal(new Double(Double.MAX_VALUE)));
    assertEquals(new BigDecimal(Double.MIN_VALUE), TypeConverter.getBigDecimal(new Double(Double.MIN_VALUE)));
    assertEquals(new BigDecimal(0), TypeConverter.getBigDecimal(new Double(0)));
    double randon = Math.random();
    assertEquals(new BigDecimal(randon), TypeConverter.getBigDecimal(new Double(randon)));

    assertEquals(new BigDecimal(Float.MAX_VALUE), TypeConverter.getBigDecimal(new Float(Float.MAX_VALUE)));
    assertEquals(new BigDecimal(Float.MIN_VALUE), TypeConverter.getBigDecimal(new Float(Float.MIN_VALUE)));
    assertEquals(new BigDecimal(0), TypeConverter.getBigDecimal(new Float(0)));
    randon = Math.random();
    assertEquals(new BigDecimal((float)randon), TypeConverter.getBigDecimal(new Float((float)randon)));

    assertEquals(new BigDecimal(Integer.MAX_VALUE), TypeConverter.getBigDecimal(new Integer(Integer.MAX_VALUE)));
    assertEquals(new BigDecimal(Integer.MIN_VALUE), TypeConverter.getBigDecimal(new Integer(Integer.MIN_VALUE)));
    assertEquals(new BigDecimal(0), TypeConverter.getBigDecimal(new Integer(0)));
    randon = Math.random();
    assertEquals(new BigDecimal((int)randon), TypeConverter.getBigDecimal(new Integer((int)randon)));

    assertEquals(new BigDecimal(Long.MAX_VALUE), TypeConverter.getBigDecimal(new Long(Long.MAX_VALUE)));
    assertEquals(new BigDecimal(Long.MIN_VALUE), TypeConverter.getBigDecimal(new Long(Long.MIN_VALUE)));
    assertEquals(new BigDecimal(0), TypeConverter.getBigDecimal(new Long(0)));
    randon = Math.random();
    assertEquals(new BigDecimal((long)randon), TypeConverter.getBigDecimal(new Long((long)randon)));

    assertEquals(new BigDecimal(String.valueOf(Double.MAX_VALUE)), TypeConverter.getBigDecimal(String.valueOf(Double.MAX_VALUE)));
    assertEquals(new BigDecimal(String.valueOf(Double.MIN_VALUE)), TypeConverter.getBigDecimal(String.valueOf(Double.MIN_VALUE)));
    assertEquals(new BigDecimal("0"), TypeConverter.getBigDecimal("0"));
    randon = Math.random();
    assertEquals(new BigDecimal(String.valueOf(randon)), TypeConverter.getBigDecimal(String.valueOf(randon)));

    assertEquals(new BigDecimal(String.valueOf(Double.MAX_VALUE)), TypeConverter.getBigDecimal(new BigDecimal(
      String.valueOf(Double.MAX_VALUE))));
    assertEquals(new BigDecimal(String.valueOf(Double.MIN_VALUE)), TypeConverter.getBigDecimal(new BigDecimal(
      String.valueOf(Double.MIN_VALUE))));
    assertEquals(new BigDecimal("0"), TypeConverter.getBigDecimal(new BigDecimal("0")));
    randon = Math.random();
    assertEquals(new BigDecimal(randon), TypeConverter.getBigDecimal(new BigDecimal(randon)));

    assertEquals(new BigDecimal(new BigInteger(String.valueOf(Long.MAX_VALUE))), TypeConverter.getBigDecimal(new BigInteger(
      String.valueOf(Long.MAX_VALUE))));
    assertEquals(new BigDecimal(new BigInteger(String.valueOf(Long.MIN_VALUE))), TypeConverter.getBigDecimal(new BigInteger(
      String.valueOf(Long.MIN_VALUE))));
    assertEquals(new BigDecimal(new BigInteger("0")), TypeConverter.getBigDecimal(new BigInteger("0")));
    randon = Math.random();
    assertEquals(new BigDecimal(new BigInteger(String.valueOf((long)randon))), TypeConverter.getBigDecimal(new BigInteger(
      String.valueOf((long)randon))));

    assertNull(TypeConverter.getBigDecimal(null));
  }

  public void testGetBigInteger() throws Exception
  {
    assertEquals(new BigInteger(String.valueOf((long)Double.MAX_VALUE)), TypeConverter.getBigInteger(new Double((long)Double.MAX_VALUE)));
    assertEquals(new BigInteger(String.valueOf((long)Double.MIN_VALUE)), TypeConverter.getBigInteger(new Double(Double.MIN_VALUE)));
    assertEquals(new BigInteger("0"), TypeConverter.getBigInteger(new Double(0)));
    double randon = Math.random();
    assertEquals(new BigInteger(String.valueOf((long)randon)), TypeConverter.getBigInteger(new Double(randon)));

    assertEquals(new BigInteger(String.valueOf((long)Float.MAX_VALUE)), TypeConverter.getBigInteger(new Float((long)Float.MAX_VALUE)));
    assertEquals(new BigInteger(String.valueOf((long)Float.MIN_VALUE)), TypeConverter.getBigInteger(new Float(Float.MIN_VALUE)));
    assertEquals(new BigInteger("0"), TypeConverter.getBigInteger(new Float(0)));
    randon = Math.random();
    assertEquals(new BigInteger(String.valueOf((long)randon)), TypeConverter.getBigInteger(new Float((long)randon)));

    assertEquals(new BigInteger(String.valueOf(Integer.MAX_VALUE)), TypeConverter.getBigInteger(new Integer(Integer.MAX_VALUE)));
    assertEquals(new BigInteger(String.valueOf(Integer.MIN_VALUE)), TypeConverter.getBigInteger(new Integer(Integer.MIN_VALUE)));
    assertEquals(new BigInteger("0"), TypeConverter.getBigInteger(new Integer(0)));
    randon = Math.random();
    assertEquals(new BigInteger(String.valueOf((int)randon)), TypeConverter.getBigInteger(new Integer((int)randon)));

    assertEquals(new BigInteger(String.valueOf(Long.MAX_VALUE)), TypeConverter.getBigInteger(new Long(Long.MAX_VALUE)));
    assertEquals(new BigInteger(String.valueOf(Long.MIN_VALUE)), TypeConverter.getBigInteger(new Long(Long.MIN_VALUE)));
    assertEquals(new BigInteger("0"), TypeConverter.getBigInteger(new Long(0)));
    randon = Math.random();
    assertEquals(new BigInteger(String.valueOf((long)randon)), TypeConverter.getBigInteger(new Long((long)randon)));

    assertEquals(
      new BigInteger(String.valueOf((long)Double.MAX_VALUE)),
      TypeConverter.getBigInteger(String.valueOf((long)Double.MAX_VALUE)));
    assertEquals(
      new BigInteger(String.valueOf((long)Double.MIN_VALUE)),
      TypeConverter.getBigInteger(String.valueOf((long)Double.MIN_VALUE)));
    assertEquals(new BigInteger("0"), TypeConverter.getBigInteger("0"));
    randon = Math.random();
    assertEquals(new BigInteger(String.valueOf((long)randon)), TypeConverter.getBigInteger(String.valueOf((long)randon)));

    assertEquals(new BigInteger(String.valueOf((long)Double.MAX_VALUE)), TypeConverter.getBigInteger(new BigDecimal(
      String.valueOf((long)Double.MAX_VALUE))));
    assertEquals(new BigInteger(String.valueOf((long)Double.MIN_VALUE)), TypeConverter.getBigInteger(new BigDecimal(
      String.valueOf((long)Double.MIN_VALUE))));
    assertEquals(new BigInteger("0"), TypeConverter.getBigInteger(new BigDecimal("0")));
    randon = Math.random();
    assertEquals(new BigInteger(String.valueOf((long)randon)), TypeConverter.getBigInteger(new BigDecimal((long)randon)));

    assertEquals(
      new BigInteger(String.valueOf(Long.MAX_VALUE)),
      TypeConverter.getBigInteger(new BigInteger(String.valueOf(Long.MAX_VALUE))));
    assertEquals(
      new BigInteger(String.valueOf(Long.MIN_VALUE)),
      TypeConverter.getBigInteger(new BigInteger(String.valueOf(Long.MIN_VALUE))));
    assertEquals(new BigInteger("0"), TypeConverter.getBigInteger(new BigInteger("0")));
    randon = Math.random();
    assertEquals(new BigInteger(String.valueOf((long)randon)), TypeConverter.getBigInteger(new BigInteger(String.valueOf((long)randon))));

    assertNull(TypeConverter.getBigInteger(null));
  }

  public void testGetDate() throws Exception
  {
    assertEquals(new Date(Long.MAX_VALUE), TypeConverter.getDate(new Long(Long.MAX_VALUE)));
    assertEquals(new Date(Long.MIN_VALUE), TypeConverter.getDate(new Long(Long.MIN_VALUE)));
    assertEquals(new Date(0), TypeConverter.getDate(new Long(0)));
    double randon = Math.random();
    assertEquals(new Date((long)randon), TypeConverter.getDate(new Long((long)randon)));

    assertEquals(new Date(), TypeConverter.getDate(new Date()));

    assertNull(TypeConverter.getDate(null));
  }
}