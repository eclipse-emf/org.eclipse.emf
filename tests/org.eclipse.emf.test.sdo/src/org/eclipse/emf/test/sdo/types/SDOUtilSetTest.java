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
 * $Id: SDOUtilSetTest.java,v 1.4 2006/12/30 03:44:08 marcelop Exp $
 */
package org.eclipse.emf.test.sdo.types;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.sdo.EDataObject;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;
import org.eclipse.emf.test.sdo.types.model.types.AThing;
import org.eclipse.emf.test.sdo.types.model.types.TypesFactory;
import org.eclipse.emf.test.sdo.types.model.types.TypesPackage;

/**
 * @author marcelop
 */
public class SDOUtilSetTest extends TestCase
{
  private AThing thing;
  public static Test suite()
  {
    TestSuite testSuite = new TestSuite("SDOUtilSetTest");
    testSuite.addTestSuite(SDOUtilSetTest.class);
    return testSuite;
  }
  
  /* (non-Javadoc)
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception
  {
    thing = TypesFactory.eINSTANCE.createAThing();
  }
  
  /*
   * [69029]
   */
  public void testSetManyBoolean() throws Exception
  {
    List<Boolean> list = thing.getManyBoolean();
    String pathWithNoIndex = "manyBoolean";

    int size = (int)(Math.random()*50)+1;
    int index = (int)(Math.random()*size);
    for (int i=0; i<size; i++)
    {
      list.add(Boolean.FALSE);
    }

    String path = pathWithNoIndex + "." + index;
    
    SDOUtil.set((EObject)thing, path, Boolean.TRUE);
    assertEquals(size, list.size());
    assertEquals("size=" + size + " index=" + index, true, list.get(index).booleanValue());   

    SDOUtil.setBoolean((EObject)thing, path, false);
    assertEquals(size, list.size());
    assertEquals("size=" + size + " index=" + index, false, list.get(index).booleanValue());   
  }
  
  /*
   * [69029]
   */
  public void testSetManyByte() throws Exception
  {
    List<Byte> list = thing.getManyByte();
    String pathWithNoIndex = "manyByte";

    int size = (int)(Math.random()*50)+1;
    int index = (int)(Math.random()*size);
    for (int i=0; i<size; i++)
    {
      list.add(new Byte((byte)i));
    }
    
    String path = pathWithNoIndex + "." + index;
    
    SDOUtil.set((EObject)thing, path, new Byte(Byte.MAX_VALUE));
    assertEquals(size, list.size());
    assertEquals("size=" + size + " index=" + index, Byte.MAX_VALUE, ((Number)list.get(index)).byteValue());   

    SDOUtil.setInt((EObject)thing, path, Byte.MIN_VALUE);
    assertEquals(size, list.size());
    assertEquals("size=" + size + " index=" + index, Byte.MIN_VALUE, ((Number)list.get(index)).byteValue());   
  }
  
  /*
   * [69029]
   */
  public void testSetManyBytes() throws Exception
  {
    List<byte[]> list = thing.getManyBytes();
    String pathWithNoIndex = "manyBytes";

    int size = (int)(Math.random()*50)+1;
    int index = (int)(Math.random()*size);
    for (int i=0; i<size; i++)
    {
      list.add(new byte[]{(byte)i});
    }
    
    String path = pathWithNoIndex + "." + index;
    
    SDOUtil.set((EObject)thing, path, "emf & sdo".getBytes());
    assertEquals(size, list.size());
    assertTrue("size=" + size + " index=" + index, Arrays.equals("emf & sdo".getBytes(), list.get(index)));   

    SDOUtil.setBytes((EObject)thing, path, "FEDMNEB".getBytes());
    assertEquals(size, list.size());
    assertTrue("size=" + size + " index=" + index, Arrays.equals("FEDMNEB".getBytes(), list.get(index)));   
  }

  /*
   * [69029]
   */
  public void testSetManyDecimal() throws Exception
  {
    List<BigDecimal> list = thing.getManyDecimal();
    String pathWithNoIndex = "manyDecimal";

    int size = (int)(Math.random()*50)+1;
    int index = (int)(Math.random()*size);
    for (int i=0; i<size; i++)
    {
      list.add(new BigDecimal(Integer.toString(i)));
    }
    
    String path = pathWithNoIndex + "." + index;
    
    SDOUtil.set((EObject)thing, path, new BigDecimal(Long.MAX_VALUE));
    assertEquals(size, list.size());
    assertEquals("size=" + size + " index=" + index, new BigDecimal(Long.MAX_VALUE), list.get(index));   

    SDOUtil.setBigDecimal((EObject)thing, path, new BigDecimal(Integer.MIN_VALUE));
    assertEquals(size, list.size());
    assertEquals("size=" + size + " index=" + index, new BigDecimal(Integer.MIN_VALUE), list.get(index));   

    SDOUtil.setBigDecimal((EObject)thing, path, null);
    assertEquals(size, list.size());
    assertEquals("size=" + size + " index=" + index, null, list.get(index));   
  }

  /*
   * [69029]
   */
  public void testSetManyInt() throws Exception
  {
    List<Integer> list = thing.getManyInt();
    String pathWithNoIndex = "manyInt";

    int size = (int)(Math.random()*50)+1;
    int index = (int)(Math.random()*size);
    for (int i=0; i<size; i++)
    {
      list.add(new Integer(i));
    }

    String path = pathWithNoIndex + "." + index;
    
    SDOUtil.set((EObject)thing, path, new Integer(Integer.MAX_VALUE));
    assertEquals(size, list.size());
    assertEquals("size=" + size + " index=" + index, Integer.MAX_VALUE, ((Number)list.get(index)).intValue());   

    SDOUtil.setInt((EObject)thing, path, Integer.MIN_VALUE);
    assertEquals(size, list.size());
    assertEquals("size=" + size + " index=" + index, Integer.MIN_VALUE, ((Number)list.get(index)).intValue());   
  }

  /*
   * [69029]
   */
  public void testSetManyLong() throws Exception
  {
    List<Long> list = thing.getManyLong();
    String pathWithNoIndex = "manyLong";

    int size = (int)(Math.random()*50)+1;
    int index = (int)(Math.random()*size);
    for (int i=0; i<size; i++)
    {
      list.add(new Long(i));
    }

    String path = pathWithNoIndex + "." + index;
    
    SDOUtil.set((EObject)thing, path, new Long(Long.MAX_VALUE));
    assertEquals(size, list.size());
    assertEquals("size=" + size + " index=" + index, Long.MAX_VALUE, ((Number)list.get(index)).longValue());   

    SDOUtil.setLong((EObject)thing, path, Long.MIN_VALUE);
    assertEquals(size, list.size());
    assertEquals("size=" + size + " index=" + index, Long.MIN_VALUE, ((Number)list.get(index)).longValue());   
  }

  /*
   * [69029]
   */
  public void testSetManyString() throws Exception
  {
    List<String> list = thing.getManyString();
    String pathWithNoIndex = "manyString";

    int size = (int)(Math.random()*50)+1;
    int index = (int)(Math.random()*size);
    for (int i=0; i<size; i++)
    {
      list.add(Integer.toString(i));
    }

    String path = pathWithNoIndex + "." + index;
    
    SDOUtil.set((EObject)thing, path, "emf & sdo");
    assertEquals(size, list.size());
    assertEquals("size=" + size + " index=" + index, "emf & sdo", list.get(index));   

    SDOUtil.setString((EObject)thing, path, "FEDMNEB");
    assertEquals(size, list.size());
    assertEquals("size=" + size + " index=" + index, "FEDMNEB", list.get(index));   
  }

  public void testSetBigDecimal() throws Exception
  {
    bigDecimalTest(new BigDecimal(Double.MAX_VALUE));
    bigDecimalTest(new BigDecimal(Double.MIN_VALUE));
    bigDecimalTest(new BigDecimal(0));
    bigDecimalTest(new BigDecimal(Math.random()));
    
    SDOUtil.setBigDecimal((EObject)thing, TypesPackage.ATHING__ADECIMAL, null);
    assertNull(thing.getADecimal());
    thing.setADecimal(new BigDecimal(0));
    SDOUtil.setBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal"), null);
    assertNull(thing.getADecimal());
    thing.setADecimal(new BigDecimal(0));
    SDOUtil.setBigDecimal((EObject)thing, "aDecimal", null);
    assertNull(thing.getADecimal());
    thing.setADecimal(new BigDecimal(0));
    //
    SDOUtil.setBigDecimal((EObject)thing, TypesPackage.ATHING__ALONG, null);
    assertEquals(0, thing.getALong());
    thing.setALong(1);
    SDOUtil.setBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong"), null);
    assertEquals(0, thing.getALong());
    thing.setALong(1);
    SDOUtil.setBigDecimal((EObject)thing, "aLong", null);
    assertEquals(0, thing.getALong());
    thing.setALong(1);
  }
  
  protected void bigDecimalTest(BigDecimal value)
  {
    //BigDecimal
    SDOUtil.setBigDecimal((EObject)thing, TypesPackage.ATHING__ADECIMAL, value);
    assertEquals(value, thing.getADecimal());
    thing.setADecimal(null);
    SDOUtil.setBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal"), value);
    assertEquals(value, thing.getADecimal());
    thing.setADecimal(null);
    SDOUtil.setBigDecimal((EObject)thing, "aDecimal", value);
    assertEquals(value, thing.getADecimal());
    thing.setADecimal(null);
    //BigInteger
    SDOUtil.setBigDecimal((EObject)thing, TypesPackage.ATHING__AINTEGER, value);
    assertEquals(value.toBigInteger(), thing.getAInteger());
    thing.setAInteger(null);
    SDOUtil.setBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger"), value);
    assertEquals(value.toBigInteger(), thing.getAInteger());
    thing.setAInteger(null);
    SDOUtil.setBigDecimal((EObject)thing, "aInteger", value);
    assertEquals(value.toBigInteger(), thing.getAInteger());
    thing.setAInteger(null);
    //byte
    SDOUtil.setBigDecimal((EObject)thing, TypesPackage.ATHING__ABYTE, value);
    assertEquals(value.byteValue(), thing.getAByte());
    thing.setAByte((byte)(value.byteValue()+1));
    SDOUtil.setBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aByte"), value);
    assertEquals(value.byteValue(), thing.getAByte());
    thing.setAByte((byte)(value.byteValue()+1));
    SDOUtil.setBigDecimal((EObject)thing, "aByte", value);
    assertEquals(value.byteValue(), thing.getAByte());
    thing.setAByte((byte)(value.byteValue()+1));
    //double
    SDOUtil.setBigDecimal((EObject)thing, TypesPackage.ATHING__ADOUBLE, value);
    assertEquals(value.doubleValue(), thing.getADouble(), 0);
    thing.setADouble(value.doubleValue()+1);
    SDOUtil.setBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble"), value);
    assertEquals(value.doubleValue(), thing.getADouble(), 0);
    thing.setADouble(value.doubleValue()+1);
    SDOUtil.setBigDecimal((EObject)thing, "aDouble", value);
    assertEquals(value.doubleValue(), thing.getADouble(), 0);
    thing.setADouble(value.doubleValue()+1);
    //float
    SDOUtil.setBigDecimal((EObject)thing, TypesPackage.ATHING__AFLOAT, value);
    assertEquals(value.floatValue(), thing.getAFloat(), 0);
    thing.setAFloat(value.floatValue()+1);
    SDOUtil.setBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat"), value);
    assertEquals(value.floatValue(), thing.getAFloat(), 0);
    thing.setAFloat(value.floatValue()+1);
    SDOUtil.setBigDecimal((EObject)thing, "aFloat", value);
    assertEquals(value.floatValue(), thing.getAFloat(), 0);
    thing.setAFloat(value.floatValue()+1);
    //int
    SDOUtil.setBigDecimal((EObject)thing, TypesPackage.ATHING__AINT, value);
    assertEquals(value.intValue(), thing.getAInt());
    thing.setAInt(value.intValue()+1);
    SDOUtil.setBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt"), value);
    assertEquals(value.intValue(), thing.getAInt());
    thing.setAInt(value.intValue()+1);
    SDOUtil.setBigDecimal((EObject)thing, "aInt", value);
    assertEquals(value.intValue(), thing.getAInt());
    thing.setAInt(value.intValue()+1);
    //long
    SDOUtil.setBigDecimal((EObject)thing, TypesPackage.ATHING__ALONG, value);
    assertEquals(value.longValue(), thing.getALong());
    thing.setALong(value.longValue()+1);
    SDOUtil.setBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong"), value);
    assertEquals(value.longValue(), thing.getALong());
    thing.setALong(value.longValue()+1);
    SDOUtil.setBigDecimal((EObject)thing, "aLong", value);
    assertEquals(value.longValue(), thing.getALong());
    thing.setALong(value.longValue()+1);
    //short
    SDOUtil.setBigDecimal((EObject)thing, TypesPackage.ATHING__ASHORT, value);
    assertEquals(value.shortValue(), thing.getAShort());
    thing.setAShort((short)(value.shortValue()+1));
    SDOUtil.setBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort"), value);
    assertEquals(value.shortValue(), thing.getAShort());
    thing.setAShort((short)(value.shortValue()+1));
    SDOUtil.setBigDecimal((EObject)thing, "aShort", value);
    assertEquals(value.shortValue(), thing.getAShort());
    thing.setAShort((short)(value.shortValue()+1));
    //String
    SDOUtil.setBigDecimal((EObject)thing, TypesPackage.ATHING__ASTRING, value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString"), value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setBigDecimal((EObject)thing, "aString", value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    //Object
    SDOUtil.setBigDecimal((EObject)thing, TypesPackage.ATHING__AOBJECT, value);
    assertEquals(value, thing.getAObject());
    thing.setAObject("");
    SDOUtil.setBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aObject"), value);
    assertEquals(value, thing.getAObject());
    thing.setAObject("");
    SDOUtil.setBigDecimal((EObject)thing, "aObject", value);
    assertEquals(value, thing.getAObject());
    thing.setAObject("");
    //Number
    SDOUtil.setBigDecimal((EObject)thing, TypesPackage.ATHING__ANUMBER, value);
    assertEquals(value, thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aNumber"), value);
    assertEquals(value, thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setBigDecimal((EObject)thing, "aNumber", value);
    assertEquals(value, thing.getANumber());
    thing.setANumber(null);
    //Thread
    Thread thread = new Thread();
    boolean classCastExceptionHappened = false;
    thing.setAThread(thread);
    try
    {
      SDOUtil.setBigDecimal((EObject)thing, TypesPackage.ATHING__ATHREAD, value);
      classCastExceptionHappened = false;
    }
    catch(ClassCastException e)
    {
      classCastExceptionHappened = true;
    }
    assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		  SDOUtil.setBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aThread"), value);
		  classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		    SDOUtil.setBigDecimal((EObject)thing, "aThread", value);
		    classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
  }

  public void testSetBigInteger() throws Exception
  {
    bigIntegerTest(BigInteger.valueOf(Long.MAX_VALUE));
    bigIntegerTest(BigInteger.valueOf(Long.MIN_VALUE));
    bigIntegerTest(new BigInteger("0"));
    bigIntegerTest(BigInteger.valueOf((long)Math.random()));
    
    SDOUtil.setBigInteger((EObject)thing, TypesPackage.ATHING__AINTEGER, null);
    assertNull(thing.getADecimal());
    thing.setAInteger(new BigInteger("0"));
    SDOUtil.setBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger"), null);
    assertNull(thing.getADecimal());
    thing.setAInteger(new BigInteger("0"));
    SDOUtil.setBigInteger((EObject)thing, "aInteger", null);
    assertNull(thing.getADecimal());
    thing.setAInteger(new BigInteger("0"));
    //
    SDOUtil.setBigInteger((EObject)thing, TypesPackage.ATHING__ASHORT, null);
    assertEquals((short)0, thing.getAShort());
    thing.setAShort((short)1);
    SDOUtil.setBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort"), null);
    assertEquals((short)0, thing.getAShort());
    thing.setAShort((short)1);
    SDOUtil.setBigInteger((EObject)thing, "aShort", null);
    assertEquals((short)0, thing.getAShort());
    thing.setAShort((short)1);
  }
  
  protected void bigIntegerTest(BigInteger value)
  {
    //BigDecimal
    SDOUtil.setBigInteger((EObject)thing, TypesPackage.ATHING__ADECIMAL, value);
    assertEquals(value, thing.getADecimal().toBigInteger());
    thing.setADecimal(null);
    SDOUtil.setBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal"), value);
    assertEquals(value, thing.getADecimal().toBigInteger());
    thing.setADecimal(null);
    SDOUtil.setBigInteger((EObject)thing, "aDecimal", value);
    assertEquals(value, thing.getADecimal().toBigInteger());
    thing.setADecimal(null);
    //BigInteger
    SDOUtil.setBigInteger((EObject)thing, TypesPackage.ATHING__AINTEGER, value);
    assertEquals(value, thing.getAInteger());
    thing.setAInteger(null);
    SDOUtil.setBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger"), value);
    assertEquals(value, thing.getAInteger());
    thing.setAInteger(null);
    SDOUtil.setBigInteger((EObject)thing, "aInteger", value);
    assertEquals(value, thing.getAInteger());
    thing.setAInteger(null);
    //byte
    SDOUtil.setBigInteger((EObject)thing, TypesPackage.ATHING__ABYTE, value);
    assertEquals(value.byteValue(), thing.getAByte());
    thing.setAByte((byte)(value.byteValue()+1));
    SDOUtil.setBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aByte"), value);
    assertEquals(value.byteValue(), thing.getAByte());
    thing.setAByte((byte)(value.byteValue()+1));
    SDOUtil.setBigInteger((EObject)thing, "aByte", value);
    assertEquals(value.byteValue(), thing.getAByte());
    thing.setAByte((byte)(value.byteValue()+1));
    //double
    SDOUtil.setBigInteger((EObject)thing, TypesPackage.ATHING__ADOUBLE, value);
    assertEquals(value.doubleValue(), thing.getADouble(), 0);
    thing.setADouble(value.doubleValue()+1);
    SDOUtil.setBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble"), value);
    assertEquals(value.doubleValue(), thing.getADouble(), 0);
    thing.setADouble(value.doubleValue()+1);
    SDOUtil.setBigInteger((EObject)thing, "aDouble", value);
    assertEquals(value.doubleValue(), thing.getADouble(), 0);
    thing.setADouble(value.doubleValue()+1);
    //float
    SDOUtil.setBigInteger((EObject)thing, TypesPackage.ATHING__AFLOAT, value);
    assertEquals(value.floatValue(), thing.getAFloat(), 0);
    thing.setAFloat(value.floatValue()+1);
    SDOUtil.setBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat"), value);
    assertEquals(value.floatValue(), thing.getAFloat(), 0);
    thing.setAFloat(value.floatValue()+1);
    SDOUtil.setBigInteger((EObject)thing, "aFloat", value);
    assertEquals(value.floatValue(), thing.getAFloat(), 0);
    thing.setAFloat(value.floatValue()+1);
    //int
    SDOUtil.setBigInteger((EObject)thing, TypesPackage.ATHING__AINT, value);
    assertEquals(value.intValue(), thing.getAInt());
    thing.setAInt(value.intValue()+1);
    SDOUtil.setBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt"), value);
    assertEquals(value.intValue(), thing.getAInt());
    thing.setAInt(value.intValue()+1);
    SDOUtil.setBigInteger((EObject)thing, "aInt", value);
    assertEquals(value.intValue(), thing.getAInt());
    thing.setAInt(value.intValue()+1);
    //long
    SDOUtil.setBigInteger((EObject)thing, TypesPackage.ATHING__ALONG, value);
    assertEquals(value.longValue(), thing.getALong());
    thing.setALong(value.longValue()+1);
    SDOUtil.setBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong"), value);
    assertEquals(value.longValue(), thing.getALong());
    thing.setALong(value.longValue()+1);
    SDOUtil.setBigInteger((EObject)thing, "aLong", value);
    assertEquals(value.longValue(), thing.getALong());
    thing.setALong(value.longValue()+1);
    //short
    SDOUtil.setBigInteger((EObject)thing, TypesPackage.ATHING__ASHORT, value);
    assertEquals(value.shortValue(), thing.getAShort());
    thing.setAShort((short)(value.shortValue()+1));
    SDOUtil.setBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort"), value);
    assertEquals(value.shortValue(), thing.getAShort());
    thing.setAShort((short)(value.shortValue()+1));
    SDOUtil.setBigInteger((EObject)thing, "aShort", value);
    assertEquals(value.shortValue(), thing.getAShort());
    thing.setAShort((short)(value.shortValue()+1));
    //String
    SDOUtil.setBigInteger((EObject)thing, TypesPackage.ATHING__ASTRING, value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString"), value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setBigInteger((EObject)thing, "aString", value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    //Object
    SDOUtil.setBigInteger((EObject)thing, TypesPackage.ATHING__AOBJECT, value);
    assertEquals(value, thing.getAObject());
    thing.setAObject("");
    SDOUtil.setBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aObject"), value);
    assertEquals(value, thing.getAObject());
    thing.setAObject("");
    SDOUtil.setBigInteger((EObject)thing, "aObject", value);
    assertEquals(value, thing.getAObject());
    thing.setAObject("");
    //Number
    SDOUtil.setBigInteger((EObject)thing, TypesPackage.ATHING__ANUMBER, value);
    assertEquals(value, thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aNumber"), value);
    assertEquals(value, thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setBigInteger((EObject)thing, "aNumber", value);
    assertEquals(value, thing.getANumber());
    thing.setANumber(null);
    //Thread
    Thread thread = new Thread();
    boolean classCastExceptionHappened = false;
    thing.setAThread(thread);
    try
    {
      SDOUtil.setBigInteger((EObject)thing, TypesPackage.ATHING__ATHREAD, value);
      classCastExceptionHappened = false;
    }
    catch(ClassCastException e)
    {
      classCastExceptionHappened = true;
    }
    assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		  SDOUtil.setBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aThread"), value);
		  classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		    SDOUtil.setBigInteger((EObject)thing, "aThread", value);
		    classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
  }

  public void testSetByte() throws Exception
  {
    byteTest(Byte.MAX_VALUE);
    byteTest(Byte.MIN_VALUE);
    byteTest((byte)0);
    byteTest((byte)Math.random());
  }
  
  protected void byteTest(byte value)
  {
    //byte
    SDOUtil.setByte((EObject)thing, TypesPackage.ATHING__ABYTE, value);
    assertEquals(value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    SDOUtil.setByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aByte"), value);
    assertEquals(value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    SDOUtil.setByte((EObject)thing, "aByte", value);
    assertEquals(value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    //double
    SDOUtil.setByte((EObject)thing, TypesPackage.ATHING__ADOUBLE, value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    SDOUtil.setByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble"), value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    SDOUtil.setByte((EObject)thing, "aDouble", value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    //float
    SDOUtil.setByte((EObject)thing, TypesPackage.ATHING__AFLOAT, value);
    assertEquals(value, thing.getAFloat(), 0);
    thing.setAFloat(value+1);
    SDOUtil.setByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat"), value);
    assertEquals(value, thing.getAFloat(), 0);
    thing.setAFloat(value+1);
    SDOUtil.setByte((EObject)thing, "aFloat", value);
    assertEquals(value, thing.getAFloat(), 0);
    thing.setAFloat(value+1);
    //int
    SDOUtil.setByte((EObject)thing, TypesPackage.ATHING__AINT, value);
    assertEquals(value, thing.getAInt());
    thing.setAInt(value+1);
    SDOUtil.setByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt"), value);
    assertEquals(value, thing.getAInt());
    thing.setAInt(value+1);
    SDOUtil.setByte((EObject)thing, "aInt", value);
    assertEquals(value, thing.getAInt());
    thing.setAInt(value+1);
    //long
    SDOUtil.setByte((EObject)thing, TypesPackage.ATHING__ALONG, value);
    assertEquals(value, thing.getALong());
    thing.setALong(value+1);
    SDOUtil.setByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong"), value);
    assertEquals(value, thing.getALong());
    thing.setALong(value+1);
    SDOUtil.setByte((EObject)thing, "aLong", value);
    assertEquals(value, thing.getALong());
    thing.setALong(value+1);
    //short
    SDOUtil.setByte((EObject)thing, TypesPackage.ATHING__ASHORT, value);
    assertEquals(value, thing.getAShort());
    thing.setAShort((short)(value+1));
    SDOUtil.setByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort"), value);
    assertEquals(value, thing.getAShort());
    thing.setAShort((short)(value+1));
    SDOUtil.setByte((EObject)thing, "aShort", value);
    assertEquals(value, thing.getAShort());
    thing.setAShort((short)(value+1));
    //String
    SDOUtil.setByte((EObject)thing, TypesPackage.ATHING__ASTRING, value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString"), value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setByte((EObject)thing, "aString", value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    //Object
    SDOUtil.setByte((EObject)thing, TypesPackage.ATHING__AOBJECT, value);
    assertEquals(new Byte(value), thing.getAObject());
    thing.setAObject("");
    SDOUtil.setByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aObject"), value);
    assertEquals(new Byte(value), thing.getAObject());
    thing.setAObject("");
    SDOUtil.setByte((EObject)thing, "aObject", value);
    assertEquals(new Byte(value), thing.getAObject());
    thing.setAObject("");
    //Number
    SDOUtil.setByte((EObject)thing, TypesPackage.ATHING__ANUMBER, value);
    assertEquals(new Byte(value), thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aNumber"), value);
    assertEquals(new Byte(value), thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setByte((EObject)thing, "aNumber", value);
    assertEquals(new Byte(value), thing.getANumber());
    thing.setANumber(null);
    //Thread
    Thread thread = new Thread();
    boolean classCastExceptionHappened = false;
    thing.setAThread(thread);
    try
    {
      SDOUtil.setByte((EObject)thing, TypesPackage.ATHING__ATHREAD, value);
      classCastExceptionHappened = false;
    }
    catch(ClassCastException e)
    {
      classCastExceptionHappened = true;
    }
    assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		  SDOUtil.setByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aThread"), value);
		  classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		    SDOUtil.setByte((EObject)thing, "aThread", value);
		    classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
  }
  
  public void testSetBoolean() throws Exception
  {
    booleanTest(true);
    booleanTest(false);
  } 
  
  protected void booleanTest(boolean value) throws Exception
  {
    //boolean
    SDOUtil.setBoolean((EObject)thing, TypesPackage.ATHING__ABOOLEAN, value);
    assertEquals(value, thing.isABoolean());
    thing.setABoolean(!value);
    SDOUtil.setBoolean((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aBoolean"), value);
    assertEquals(value, thing.isABoolean());
    thing.setABoolean(!value);
    SDOUtil.setBoolean((EObject)thing, "aBoolean", value);
    assertEquals(value, thing.isABoolean());
    thing.setABoolean(!value);
    //String
    SDOUtil.setBoolean((EObject)thing, TypesPackage.ATHING__ASTRING, value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setBoolean((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString"), value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setBoolean((EObject)thing, "aString", value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
  }

  public void testSetBytes() throws Exception
  {
    bytesTest(new byte []{ Byte.MIN_VALUE, 0, Byte.MAX_VALUE });
    bytesTest(null);
  }
  
  protected void bytesTest(byte[] value) throws Exception
  {
    //bytes
    SDOUtil.setBytes((EObject)thing, TypesPackage.ATHING__ABYTES, value);
    assertTrue(Arrays.equals(value, thing.getABytes()));
    thing.setABytes(new byte[0]);
    SDOUtil.setBytes((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aBytes"), value);
    assertTrue(Arrays.equals(value, thing.getABytes()));
    thing.setABytes(new byte[0]);
    SDOUtil.setBytes((EObject)thing, "aBytes", value);
    assertTrue(Arrays.equals(value, thing.getABytes()));
    thing.setABytes(new byte[0]);
    //BigInteger
    BigInteger expected = value == null ? null : new BigInteger(value);
    SDOUtil.setBytes((EObject)thing, TypesPackage.ATHING__AINTEGER, value);
    assertEquals(expected, thing.getAInteger());
    thing.setAInteger(BigInteger.valueOf(1));
    SDOUtil.setBytes((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger"), value);
    assertEquals(expected, thing.getAInteger());
    thing.setAInteger(BigInteger.valueOf(1));
    SDOUtil.setBytes((EObject)thing, "aInteger", value);
    assertEquals(expected, thing.getAInteger());
    thing.setAInteger(BigInteger.valueOf(1));
  }

  public void testSetChar() throws Exception
  {
    charTest(Character.MAX_VALUE);
    charTest(Character.MIN_VALUE);
    charTest((char)0);
    charTest((char)Math.random());
  } 
  
  protected void charTest(char value) throws Exception
  {
    //char
    SDOUtil.setChar((EObject)thing, TypesPackage.ATHING__ACHAR, value);
    assertEquals(value, thing.getAChar());
    thing.setAChar((char)(value+1));
    SDOUtil.setChar((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aChar"), value);
    assertEquals(value, thing.getAChar());
    thing.setAChar((char)(value+1));
    SDOUtil.setChar((EObject)thing, "aChar", value);
    assertEquals(value, thing.getAChar());
    thing.setAChar((char)(value+1));
    //String
    SDOUtil.setChar((EObject)thing, TypesPackage.ATHING__ASTRING, value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString(null);
    SDOUtil.setChar((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString"), value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString(null);
    SDOUtil.setChar((EObject)thing, "aString", value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString(null);
    //Object
    SDOUtil.setChar((EObject)thing, TypesPackage.ATHING__AOBJECT, value);
    assertEquals(new Character(value), thing.getAObject());
    thing.setAObject("");
    SDOUtil.setChar((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aObject"), value);
    assertEquals(new Character(value), thing.getAObject());
    thing.setAObject("");
    SDOUtil.setChar((EObject)thing, "aObject", value);
    assertEquals(new Character(value), thing.getAObject());
    thing.setAObject("");
    //Thread
    Thread thread = new Thread();
    boolean classCastExceptionHappened = false;
    thing.setAThread(thread);
    try
    {
      SDOUtil.setChar((EObject)thing, TypesPackage.ATHING__ATHREAD, value);
      classCastExceptionHappened = false;
    }
    catch(ClassCastException e)
    {
      classCastExceptionHappened = true;
    }
    assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		  SDOUtil.setChar((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aThread"), value);
		  classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		    SDOUtil.setChar((EObject)thing, "aThread", value);
		    classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
  }
  
  public void testSetDate() throws Exception
  {
    dateTest(new Date());
    dateTest(null);
  }
  
  protected void dateTest(Date value)
  {
    //Date
    SDOUtil.setDate((EObject)thing, TypesPackage.ATHING__ADATE, value);
    assertEquals(value, thing.getADate());
    thing.setADate(null);
    SDOUtil.setDate((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDate"), value);
    assertEquals(value, thing.getADate());
    thing.setADate(null);
    SDOUtil.setDate((EObject)thing, "aDate", value);
    assertEquals(value, thing.getADate());
    thing.setADate(null);
    //long
    long expected = value == null ? 0 : value.getTime();
    SDOUtil.setDate((EObject)thing, TypesPackage.ATHING__ALONG, value);
    assertEquals(expected, thing.getALong());
    thing.setALong(10);
    SDOUtil.setDate((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong"), value);
    assertEquals(expected, thing.getALong());
    thing.setALong(10);
    SDOUtil.setDate((EObject)thing, "aLong", value);
    assertEquals(expected, thing.getALong());
    thing.setALong(10);
  }
  
  public void testSetDouble() throws Exception
  {
    doubleTest(Double.MAX_VALUE);
    doubleTest(Double.MIN_VALUE);
    doubleTest(0);
    doubleTest(Math.random());
  }
  
  protected void doubleTest(double value)
  {
    //BigDecimal
    SDOUtil.setDouble((EObject)thing, TypesPackage.ATHING__ADECIMAL, value);
    assertEquals(new BigDecimal(value), thing.getADecimal());
    thing.setADecimal(null);
    SDOUtil.setDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal"), value);
    assertEquals(new BigDecimal(value), thing.getADecimal());
    thing.setADecimal(null);
    SDOUtil.setDouble((EObject)thing, "aDecimal", value);
    assertEquals(new BigDecimal(value), thing.getADecimal());
    thing.setADecimal(null);
    //BigInteger
    SDOUtil.setDouble((EObject)thing, TypesPackage.ATHING__AINTEGER, value);
    assertEquals(BigInteger.valueOf(new Double(value).longValue()), thing.getAInteger());
    thing.setAInteger(null);
    SDOUtil.setDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger"), value);
    assertEquals(BigInteger.valueOf(new Double(value).longValue()), thing.getAInteger());
    thing.setAInteger(null);
    SDOUtil.setDouble((EObject)thing, "aInteger", value);
    assertEquals(BigInteger.valueOf(new Double(value).longValue()), thing.getAInteger());
    thing.setAInteger(null);
    //byte
    SDOUtil.setDouble((EObject)thing, TypesPackage.ATHING__ABYTE, value);
    assertEquals((byte)value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    SDOUtil.setDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aByte"), value);
    assertEquals((byte)value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    SDOUtil.setDouble((EObject)thing, "aByte", value);
    assertEquals((byte)value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    //double
    SDOUtil.setDouble((EObject)thing, TypesPackage.ATHING__ADOUBLE, value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    SDOUtil.setDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble"), value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    SDOUtil.setDouble((EObject)thing, "aDouble", value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    //float
    SDOUtil.setDouble((EObject)thing, TypesPackage.ATHING__AFLOAT, value);
    assertEquals((float)value, thing.getAFloat(), 0);
    thing.setAFloat((float)(value+1));
    SDOUtil.setDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat"), value);
    assertEquals((float)value, thing.getAFloat(), 0);
    thing.setAFloat((float)(value+1));
    SDOUtil.setDouble((EObject)thing, "aFloat", value);
    assertEquals((float)value, thing.getAFloat(), 0);
    thing.setAFloat((float)(value+1));
    //int
    SDOUtil.setDouble((EObject)thing, TypesPackage.ATHING__AINT, value);
    assertEquals((int)value, thing.getAInt());
    thing.setAInt((int)(value+1));
    SDOUtil.setDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt"), value);
    assertEquals((int)value, thing.getAInt());
    thing.setAInt((int)(value+1));
    SDOUtil.setDouble((EObject)thing, "aInt", value);
    assertEquals((int)value, thing.getAInt());
    thing.setAInt((int)(value+1));
    //long
    SDOUtil.setDouble((EObject)thing, TypesPackage.ATHING__ALONG, value);
    assertEquals((long)value, thing.getALong());
    thing.setALong((long)(value+1));
    SDOUtil.setDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong"), value);
    assertEquals((long)value, thing.getALong());
    thing.setALong((long)(value+1));
    SDOUtil.setDouble((EObject)thing, "aLong", value);
    assertEquals((long)value, thing.getALong());
    thing.setALong((long)(value+1));
    //short
    SDOUtil.setDouble((EObject)thing, TypesPackage.ATHING__ASHORT, value);
    assertEquals((short)value, thing.getAShort());
    thing.setAShort((short)(value+1));
    SDOUtil.setDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort"), value);
    assertEquals((short)value, thing.getAShort());
    thing.setAShort((short)(value+1));
    SDOUtil.setDouble((EObject)thing, "aShort", value);
    assertEquals((short)value, thing.getAShort());
    thing.setAShort((short)(value+1));
    //String
    SDOUtil.setDouble((EObject)thing, TypesPackage.ATHING__ASTRING, value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString"), value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setDouble((EObject)thing, "aString", value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    //Object
    SDOUtil.setDouble((EObject)thing, TypesPackage.ATHING__AOBJECT, value);
    assertEquals(new Double(value), thing.getAObject());
    thing.setAObject("");
    SDOUtil.setDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aObject"), value);
    assertEquals(new Double(value), thing.getAObject());
    thing.setAObject("");
    SDOUtil.setDouble((EObject)thing, "aObject", value);
    assertEquals(new Double(value), thing.getAObject());
    thing.setAObject("");
    //Number
    SDOUtil.setDouble((EObject)thing, TypesPackage.ATHING__ANUMBER, value);
    assertEquals(new Double(value), thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aNumber"), value);
    assertEquals(new Double(value), thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setDouble((EObject)thing, "aNumber", value);
    assertEquals(new Double(value), thing.getANumber());
    thing.setANumber(null);
    //Thread
    Thread thread = new Thread();
    boolean classCastExceptionHappened = false;
    thing.setAThread(thread);
    try
    {
      SDOUtil.setDouble((EObject)thing, TypesPackage.ATHING__ATHREAD, value);
      classCastExceptionHappened = false;
    }
    catch(ClassCastException e)
    {
      classCastExceptionHappened = true;
    }
    assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		  SDOUtil.setDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aThread"), value);
		  classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		    SDOUtil.setDouble((EObject)thing, "aThread", value);
		    classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
  }

  public void testSetFloat() throws Exception
  {
    floatTest(Float.MAX_VALUE);
    floatTest(Float.MIN_VALUE);
    floatTest(0);
    floatTest((float)Math.random());
  }
  
  protected void floatTest(float value)
  {
    //BigDecimal
    SDOUtil.setFloat((EObject)thing, TypesPackage.ATHING__ADECIMAL, value);
    assertEquals(new BigDecimal(value), thing.getADecimal());
    thing.setADecimal(null);
    SDOUtil.setFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal"), value);
    assertEquals(new BigDecimal(value), thing.getADecimal());
    thing.setADecimal(null);
    SDOUtil.setFloat((EObject)thing, "aDecimal", value);
    assertEquals(new BigDecimal(value), thing.getADecimal());
    thing.setADecimal(null);
    //BigInteger
    SDOUtil.setFloat((EObject)thing, TypesPackage.ATHING__AINTEGER, value);
    assertEquals(BigInteger.valueOf(new Float(value).longValue()), thing.getAInteger());
    thing.setAInteger(null);
    SDOUtil.setFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger"), value);
    assertEquals(BigInteger.valueOf(new Float(value).longValue()), thing.getAInteger());
    thing.setAInteger(null);
    SDOUtil.setFloat((EObject)thing, "aInteger", value);
    assertEquals(BigInteger.valueOf(new Float(value).longValue()), thing.getAInteger());
    thing.setAInteger(null);
    //byte
    SDOUtil.setFloat((EObject)thing, TypesPackage.ATHING__ABYTE, value);
    assertEquals((byte)value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    SDOUtil.setFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aByte"), value);
    assertEquals((byte)value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    SDOUtil.setFloat((EObject)thing, "aByte", value);
    assertEquals((byte)value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    //double
    SDOUtil.setFloat((EObject)thing, TypesPackage.ATHING__ADOUBLE, value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    SDOUtil.setFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble"), value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    SDOUtil.setFloat((EObject)thing, "aDouble", value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    //float
    SDOUtil.setFloat((EObject)thing, TypesPackage.ATHING__AFLOAT, value);
    assertEquals(value, thing.getAFloat(), 0);
    thing.setAFloat(value+1);
    SDOUtil.setFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat"), value);
    assertEquals(value, thing.getAFloat(), 0);
    thing.setAFloat(value+1);
    SDOUtil.setFloat((EObject)thing, "aFloat", value);
    assertEquals(value, thing.getAFloat(), 0);
    thing.setAFloat(value+1);
    //int
    SDOUtil.setFloat((EObject)thing, TypesPackage.ATHING__AINT, value);
    assertEquals((int)value, thing.getAInt());
    thing.setAInt((int)(value+1));
    SDOUtil.setFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt"), value);
    assertEquals((int)value, thing.getAInt());
    thing.setAInt((int)(value+1));
    SDOUtil.setFloat((EObject)thing, "aInt", value);
    assertEquals((int)value, thing.getAInt());
    thing.setAInt((int)(value+1));
    //long
    SDOUtil.setFloat((EObject)thing, TypesPackage.ATHING__ALONG, value);
    assertEquals((long)value, thing.getALong());
    thing.setALong((long)(value+1));
    SDOUtil.setFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong"), value);
    assertEquals((long)value, thing.getALong());
    thing.setALong((long)(value+1));
    SDOUtil.setFloat((EObject)thing, "aLong", value);
    assertEquals((long)value, thing.getALong());
    thing.setALong((long)(value+1));
    //short
    SDOUtil.setFloat((EObject)thing, TypesPackage.ATHING__ASHORT, value);
    assertEquals((short)value, thing.getAShort());
    thing.setAShort((short)(value+1));
    SDOUtil.setFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort"), value);
    assertEquals((short)value, thing.getAShort());
    thing.setAShort((short)(value+1));
    SDOUtil.setFloat((EObject)thing, "aShort", value);
    assertEquals((short)value, thing.getAShort());
    thing.setAShort((short)(value+1));
    //String
    SDOUtil.setFloat((EObject)thing, TypesPackage.ATHING__ASTRING, value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString"), value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setFloat((EObject)thing, "aString", value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    //Object
    SDOUtil.setFloat((EObject)thing, TypesPackage.ATHING__AOBJECT, value);
    assertEquals(new Float(value), thing.getAObject());
    thing.setAObject("");
    SDOUtil.setFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aObject"), value);
    assertEquals(new Float(value), thing.getAObject());
    thing.setAObject("");
    SDOUtil.setFloat((EObject)thing, "aObject", value);
    assertEquals(new Float(value), thing.getAObject());
    thing.setAObject("");
    //Number
    SDOUtil.setFloat((EObject)thing, TypesPackage.ATHING__ANUMBER, value);
    assertEquals(new Float(value), thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aNumber"), value);
    assertEquals(new Float(value), thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setFloat((EObject)thing, "aNumber", value);
    assertEquals(new Float(value), thing.getANumber());
    thing.setANumber(null);
    //Thread
    Thread thread = new Thread();
    boolean classCastExceptionHappened = false;
    thing.setAThread(thread);
    try
    {
      SDOUtil.setFloat((EObject)thing, TypesPackage.ATHING__ATHREAD, value);
      classCastExceptionHappened = false;
    }
    catch(ClassCastException e)
    {
      classCastExceptionHappened = true;
    }
    assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		  SDOUtil.setFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aThread"), value);
		  classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		    SDOUtil.setFloat((EObject)thing, "aThread", value);
		    classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
  }

  public void testSetInt() throws Exception
  {
    intTest(Integer.MAX_VALUE);
    intTest(Integer.MIN_VALUE);
    intTest(0);
    intTest((int)Math.random());
  }
  
  protected void intTest(int value)
  {
    //BigDecimal
    SDOUtil.setInt((EObject)thing, TypesPackage.ATHING__ADECIMAL, value);
    assertEquals(new BigDecimal(value), thing.getADecimal());
    thing.setADecimal(null);
    SDOUtil.setInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal"), value);
    assertEquals(new BigDecimal(value), thing.getADecimal());
    thing.setADecimal(null);
    SDOUtil.setInt((EObject)thing, "aDecimal", value);
    assertEquals(new BigDecimal(value), thing.getADecimal());
    thing.setADecimal(null);
    //BigInteger
    SDOUtil.setInt((EObject)thing, TypesPackage.ATHING__AINTEGER, value);
    assertEquals(BigInteger.valueOf(new Double(value).longValue()), thing.getAInteger());
    thing.setAInteger(null);
    SDOUtil.setInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger"), value);
    assertEquals(BigInteger.valueOf(new Double(value).longValue()), thing.getAInteger());
    thing.setAInteger(null);
    SDOUtil.setInt((EObject)thing, "aInteger", value);
    assertEquals(BigInteger.valueOf(new Double(value).longValue()), thing.getAInteger());
    thing.setAInteger(null);
    //byte
    SDOUtil.setInt((EObject)thing, TypesPackage.ATHING__ABYTE, value);
    assertEquals((byte)value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    SDOUtil.setInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aByte"), value);
    assertEquals((byte)value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    SDOUtil.setInt((EObject)thing, "aByte", value);
    assertEquals((byte)value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    //double
    SDOUtil.setInt((EObject)thing, TypesPackage.ATHING__ADOUBLE, value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    SDOUtil.setInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble"), value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    SDOUtil.setInt((EObject)thing, "aDouble", value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    //float
    SDOUtil.setInt((EObject)thing, TypesPackage.ATHING__AFLOAT, value);
    assertEquals(value, thing.getAFloat(), 0);
    thing.setAFloat(value+1);
    SDOUtil.setInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat"), value);
    assertEquals(value, thing.getAFloat(), 0);
    thing.setAFloat(value+1);
    SDOUtil.setInt((EObject)thing, "aFloat", value);
    assertEquals(value, thing.getAFloat(), 0);
    thing.setAFloat(value+1);
    //int
    SDOUtil.setInt((EObject)thing, TypesPackage.ATHING__AINT, value);
    assertEquals(value, thing.getAInt());
    thing.setAInt(value+1);
    SDOUtil.setInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt"), value);
    assertEquals(value, thing.getAInt());
    thing.setAInt(value+1);
    SDOUtil.setInt((EObject)thing, "aInt", value);
    assertEquals(value, thing.getAInt());
    thing.setAInt(value+1);
    //long
    SDOUtil.setInt((EObject)thing, TypesPackage.ATHING__ALONG, value);
    assertEquals(value, thing.getALong());
    thing.setALong(value+1);
    SDOUtil.setInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong"), value);
    assertEquals(value, thing.getALong());
    thing.setALong(value+1);
    SDOUtil.setInt((EObject)thing, "aLong", value);
    assertEquals(value, thing.getALong());
    thing.setALong(value+1);
    //short
    SDOUtil.setInt((EObject)thing, TypesPackage.ATHING__ASHORT, value);
    assertEquals((short)value, thing.getAShort());
    thing.setAShort((short)(value+1));
    SDOUtil.setInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort"), value);
    assertEquals((short)value, thing.getAShort());
    thing.setAShort((short)(value+1));
    SDOUtil.setInt((EObject)thing, "aShort", value);
    assertEquals((short)value, thing.getAShort());
    thing.setAShort((short)(value+1));
    //String
    SDOUtil.setInt((EObject)thing, TypesPackage.ATHING__ASTRING, value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString"), value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setInt((EObject)thing, "aString", value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    //Object
    SDOUtil.setInt((EObject)thing, TypesPackage.ATHING__AOBJECT, value);
    assertEquals(new Integer(value), thing.getAObject());
    thing.setAObject("");
    SDOUtil.setInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aObject"), value);
    assertEquals(new Integer(value), thing.getAObject());
    thing.setAObject("");
    SDOUtil.setInt((EObject)thing, "aObject", value);
    assertEquals(new Integer(value), thing.getAObject());
    thing.setAObject("");
    //Number
    SDOUtil.setInt((EObject)thing, TypesPackage.ATHING__ANUMBER, value);
    assertEquals(new Integer(value), thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aNumber"), value);
    assertEquals(new Integer(value), thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setInt((EObject)thing, "aNumber", value);
    assertEquals(new Integer(value), thing.getANumber());
    thing.setANumber(null);
    //Thread
    Thread thread = new Thread();
    boolean classCastExceptionHappened = false;
    thing.setAThread(thread);
    try
    {
      SDOUtil.setInt((EObject)thing, TypesPackage.ATHING__ATHREAD, value);
      classCastExceptionHappened = false;
    }
    catch(ClassCastException e)
    {
      classCastExceptionHappened = true;
    }
    assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		  SDOUtil.setInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aThread"), value);
		  classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		    SDOUtil.setInt((EObject)thing, "aThread", value);
		    classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
  }

  public void testSetLong() throws Exception
  {
    longTest(Long.MAX_VALUE);
    longTest(Long.MIN_VALUE);
    longTest(0);
    longTest((long)Math.random());

    //String
    Date date = new Date();
    SDOUtil.setLong((EObject)thing, TypesPackage.ATHING__ADATE, date.getTime());
    assertEquals(date, thing.getADate());
    thing.setADate(null);
    SDOUtil.setLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDate"), date.getTime());
    assertEquals(date, thing.getADate());
    thing.setADate(null);
    SDOUtil.setLong((EObject)thing, "aDate", date.getTime());
    assertEquals(date, thing.getADate());
    thing.setADate(null);
  }
  
  protected void longTest(long value)
  {
    //BigDecimal
    SDOUtil.setLong((EObject)thing, TypesPackage.ATHING__ADECIMAL, value);
    assertEquals(new BigDecimal(value), thing.getADecimal());
    thing.setADecimal(null);
    SDOUtil.setLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal"), value);
    assertEquals(new BigDecimal(value), thing.getADecimal());
    thing.setADecimal(null);
    SDOUtil.setLong((EObject)thing, "aDecimal", value);
    assertEquals(new BigDecimal(value), thing.getADecimal());
    thing.setADecimal(null);
    //BigInteger
    SDOUtil.setLong((EObject)thing, TypesPackage.ATHING__AINTEGER, value);
    assertEquals(BigInteger.valueOf(new Double(value).longValue()), thing.getAInteger());
    thing.setAInteger(null);
    SDOUtil.setLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger"), value);
    assertEquals(BigInteger.valueOf(new Double(value).longValue()), thing.getAInteger());
    thing.setAInteger(null);
    SDOUtil.setLong((EObject)thing, "aInteger", value);
    assertEquals(BigInteger.valueOf(new Double(value).longValue()), thing.getAInteger());
    thing.setAInteger(null);
    //byte
    SDOUtil.setLong((EObject)thing, TypesPackage.ATHING__ABYTE, value);
    assertEquals((byte)value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    SDOUtil.setLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aByte"), value);
    assertEquals((byte)value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    SDOUtil.setLong((EObject)thing, "aByte", value);
    assertEquals((byte)value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    //double
    SDOUtil.setLong((EObject)thing, TypesPackage.ATHING__ADOUBLE, value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    SDOUtil.setLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble"), value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    SDOUtil.setLong((EObject)thing, "aDouble", value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    //float
    SDOUtil.setLong((EObject)thing, TypesPackage.ATHING__AFLOAT, value);
    assertEquals(value, thing.getAFloat(), 0);
    thing.setAFloat(value+1);
    SDOUtil.setLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat"), value);
    assertEquals(value, thing.getAFloat(), 0);
    thing.setAFloat(value+1);
    SDOUtil.setLong((EObject)thing, "aFloat", value);
    assertEquals(value, thing.getAFloat(), 0);
    thing.setAFloat(value+1);
    //int
    SDOUtil.setLong((EObject)thing, TypesPackage.ATHING__AINT, value);
    assertEquals((int)value, thing.getAInt());
    thing.setAInt(((int)value+1));
    SDOUtil.setLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt"), value);
    assertEquals((int)value, thing.getAInt());
    thing.setAInt(((int)value+1));
    SDOUtil.setLong((EObject)thing, "aInt", value);
    assertEquals((int)value, thing.getAInt());
    thing.setAInt(((int)value+1));
    //long
    SDOUtil.setLong((EObject)thing, TypesPackage.ATHING__ALONG, value);
    assertEquals(value, thing.getALong());
    thing.setALong(value+1);
    SDOUtil.setLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong"), value);
    assertEquals(value, thing.getALong());
    thing.setALong(value+1);
    SDOUtil.setLong((EObject)thing, "aLong", value);
    assertEquals(value, thing.getALong());
    thing.setALong(value+1);
    //short
    SDOUtil.setLong((EObject)thing, TypesPackage.ATHING__ASHORT, value);
    assertEquals((short)value, thing.getAShort());
    thing.setAShort((short)(value+1));
    SDOUtil.setLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort"), value);
    assertEquals((short)value, thing.getAShort());
    thing.setAShort((short)(value+1));
    SDOUtil.setLong((EObject)thing, "aShort", value);
    assertEquals((short)value, thing.getAShort());
    thing.setAShort((short)(value+1));
    //String
    SDOUtil.setLong((EObject)thing, TypesPackage.ATHING__ASTRING, value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString"), value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setLong((EObject)thing, "aString", value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    //Object
    SDOUtil.setLong((EObject)thing, TypesPackage.ATHING__AOBJECT, value);
    assertEquals(new Long(value), thing.getAObject());
    thing.setAObject("");
    SDOUtil.setLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aObject"), value);
    assertEquals(new Long(value), thing.getAObject());
    thing.setAObject("");
    SDOUtil.setLong((EObject)thing, "aObject", value);
    assertEquals(new Long(value), thing.getAObject());
    thing.setAObject("");
    //Number
    SDOUtil.setLong((EObject)thing, TypesPackage.ATHING__ANUMBER, value);
    assertEquals(new Long(value), thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aNumber"), value);
    assertEquals(new Long(value), thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setLong((EObject)thing, "aNumber", value);
    assertEquals(new Long(value), thing.getANumber());
    thing.setANumber(null);
    //Thread
    Thread thread = new Thread();
    boolean classCastExceptionHappened = false;
    thing.setAThread(thread);
    try
    {
      SDOUtil.setLong((EObject)thing, TypesPackage.ATHING__ATHREAD, value);
      classCastExceptionHappened = false;
    }
    catch(ClassCastException e)
    {
      classCastExceptionHappened = true;
    }
    assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		  SDOUtil.setLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aThread"), value);
		  classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		    SDOUtil.setLong((EObject)thing, "aThread", value);
		    classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
  }

  public void testSetShort() throws Exception
  {
    shortTest(Short.MAX_VALUE);
    shortTest(Short.MIN_VALUE);
    shortTest((short)0);
    shortTest((short)Math.random());
  }
  
  protected void shortTest(short value)
  {
    //byte
    SDOUtil.setShort((EObject)thing, TypesPackage.ATHING__ABYTE, value);
    assertEquals((byte)value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    SDOUtil.setShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aByte"), value);
    assertEquals((byte)value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    SDOUtil.setShort((EObject)thing, "aByte", value);
    assertEquals((byte)value, thing.getAByte());
    thing.setAByte((byte)(value+1));
    //double
    SDOUtil.setShort((EObject)thing, TypesPackage.ATHING__ADOUBLE, value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    SDOUtil.setShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble"), value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    SDOUtil.setShort((EObject)thing, "aDouble", value);
    assertEquals(value, thing.getADouble(), 0);
    thing.setADouble(value+1);
    //float
    SDOUtil.setShort((EObject)thing, TypesPackage.ATHING__AFLOAT, value);
    assertEquals(value, thing.getAFloat(), 0);
    thing.setAFloat(value+1);
    SDOUtil.setShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat"), value);
    assertEquals(value, thing.getAFloat(), 0);
    thing.setAFloat(value+1);
    SDOUtil.setShort((EObject)thing, "aFloat", value);
    assertEquals(value, thing.getAFloat(), 0);
    thing.setAFloat(value+1);
    //int
    SDOUtil.setShort((EObject)thing, TypesPackage.ATHING__AINT, value);
    assertEquals(value, thing.getAInt());
    thing.setAInt(value+1);
    SDOUtil.setShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt"), value);
    assertEquals(value, thing.getAInt());
    thing.setAInt(value+1);
    SDOUtil.setShort((EObject)thing, "aInt", value);
    assertEquals(value, thing.getAInt());
    thing.setAInt(value+1);
    //long
    SDOUtil.setShort((EObject)thing, TypesPackage.ATHING__ALONG, value);
    assertEquals(value, thing.getALong());
    thing.setALong(value+1);
    SDOUtil.setShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong"), value);
    assertEquals(value, thing.getALong());
    thing.setALong(value+1);
    SDOUtil.setShort((EObject)thing, "aLong", value);
    assertEquals(value, thing.getALong());
    thing.setALong(value+1);
    //short
    SDOUtil.setShort((EObject)thing, TypesPackage.ATHING__ASHORT, value);
    assertEquals(value, thing.getAShort());
    thing.setAShort((short)(value+1));
    SDOUtil.setShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort"), value);
    assertEquals(value, thing.getAShort());
    thing.setAShort((short)(value+1));
    SDOUtil.setShort((EObject)thing, "aShort", value);
    assertEquals(value, thing.getAShort());
    thing.setAShort((short)(value+1));
    //String
    SDOUtil.setShort((EObject)thing, TypesPackage.ATHING__ASTRING, value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString"), value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    SDOUtil.setShort((EObject)thing, "aString", value);
    assertEquals(String.valueOf(value), thing.getAString());
    thing.setAString("");
    //Object
    SDOUtil.setShort((EObject)thing, TypesPackage.ATHING__AOBJECT, value);
    assertEquals(new Short(value), thing.getAObject());
    thing.setAObject("");
    SDOUtil.setShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aObject"), value);
    assertEquals(new Short(value), thing.getAObject());
    thing.setAObject("");
    SDOUtil.setShort((EObject)thing, "aObject", value);
    assertEquals(new Short(value), thing.getAObject());
    thing.setAObject("");
    //Number
    SDOUtil.setShort((EObject)thing, TypesPackage.ATHING__ANUMBER, value);
    assertEquals(new Short(value), thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aNumber"), value);
    assertEquals(new Short(value), thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setShort((EObject)thing, "aNumber", value);
    assertEquals(new Short(value), thing.getANumber());
    thing.setANumber(null);
    //Thread
    Thread thread = new Thread();
    boolean classCastExceptionHappened = false;
    thing.setAThread(thread);
    try
    {
      SDOUtil.setShort((EObject)thing, TypesPackage.ATHING__ATHREAD, value);
      classCastExceptionHappened = false;
    }
    catch(ClassCastException e)
    {
      classCastExceptionHappened = true;
    }
    assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		  SDOUtil.setShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aThread"), value);
		  classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		    SDOUtil.setShort((EObject)thing, "aThread", value);
		    classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
  }

  public void testSetString() throws Exception
  {
    stringNumberTest(String.valueOf(Byte.MAX_VALUE));
    stringNumberTest(String.valueOf(Byte.MIN_VALUE));
    stringNumberTest(String.valueOf(0));
    stringNumberTest(String.valueOf((byte)Math.random()));
        
    SDOUtil.setString((EObject)thing, TypesPackage.ATHING__ASTRING, null);
    assertNull(thing.getAString());
    thing.setAString("");
    SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString"), null);
    assertNull(thing.getAString());
    thing.setAString("");
    SDOUtil.setString((EObject)thing, "aString", null);
    assertNull(thing.getAString());
    thing.setAString("");
    //
    SDOUtil.setString((EObject)thing, TypesPackage.ATHING__AINT, null);
    assertEquals(0, thing.getAInt());
    thing.setAInt(1);
    SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt"), null);
    assertEquals(0, thing.getAInt());
    thing.setAInt(1);
    SDOUtil.setString((EObject)thing, "aInt", null);
    assertEquals(0, thing.getAInt());
    thing.setAInt(1);
    
    SDOUtil.setString((EObject)thing, TypesPackage.ATHING__ASTRING, "");
    assertEquals("", thing.getAString());
    thing.setAString(null);
    SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString"), "");
    assertEquals("", thing.getAString());
    thing.setAString(null);
    SDOUtil.setString((EObject)thing, "aString", "");
    assertEquals("", thing.getAString());
    thing.setAString(null);
    
    //boolean

    SDOUtil.setString((EObject)thing, TypesPackage.ATHING__ABOOLEAN, Boolean.FALSE.toString());
    assertEquals(false, thing.isABoolean());
    thing.setABoolean(!thing.isABoolean());
    SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aBoolean"), Boolean.FALSE.toString());
    assertEquals(false, thing.isABoolean());
    thing.setABoolean(!thing.isABoolean());
    SDOUtil.setString((EObject)thing, "aBoolean", Boolean.FALSE.toString());
    assertEquals(false, thing.isABoolean());
    thing.setABoolean(!thing.isABoolean());
    //
    SDOUtil.setString((EObject)thing, TypesPackage.ATHING__ABOOLEAN, Boolean.TRUE.toString());
    assertEquals(true, thing.isABoolean());
    thing.setABoolean(!thing.isABoolean());
    SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aBoolean"), Boolean.TRUE.toString());
    assertEquals(true, thing.isABoolean());
    thing.setABoolean(!thing.isABoolean());
    SDOUtil.setString((EObject)thing, "aBoolean", Boolean.TRUE.toString());
    assertEquals(true, thing.isABoolean());
    thing.setABoolean(!thing.isABoolean());    
  }
  
  protected void stringNumberTest(String value)
  {
    //BigDecimal
    SDOUtil.setString((EObject)thing, TypesPackage.ATHING__ADECIMAL, value);
    assertEquals(new BigDecimal(value), thing.getADecimal());
    thing.setADecimal(null);
    SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal"), value);
    assertEquals(new BigDecimal(value), thing.getADecimal());
    thing.setADecimal(null);
    SDOUtil.setString((EObject)thing, "aDecimal", value);
    assertEquals(new BigDecimal(value), thing.getADecimal());
    thing.setADecimal(null);
    //BigInteger
    SDOUtil.setString((EObject)thing, TypesPackage.ATHING__AINTEGER, value);
    assertEquals(BigInteger.valueOf(new Double(value).longValue()), thing.getAInteger());
    thing.setAInteger(null);
    SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger"), value);
    assertEquals(BigInteger.valueOf(new Double(value).longValue()), thing.getAInteger());
    thing.setAInteger(null);
    SDOUtil.setString((EObject)thing, "aInteger", value);
    assertEquals(BigInteger.valueOf(new Double(value).longValue()), thing.getAInteger());
    thing.setAInteger(null);
    //byte
    SDOUtil.setString((EObject)thing, TypesPackage.ATHING__ABYTE, value);
    assertEquals(Byte.parseByte(value), thing.getAByte());
    thing.setAByte((byte)(Byte.parseByte(value)+1));
    SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aByte"), value);
    assertEquals(Byte.parseByte(value), thing.getAByte());
    thing.setAByte((byte)(Byte.parseByte(value)+1));
    SDOUtil.setString((EObject)thing, "aByte", value);
    assertEquals(Byte.parseByte(value), thing.getAByte());
    thing.setAByte((byte)(Byte.parseByte(value)+1));
    //double
    SDOUtil.setString((EObject)thing, TypesPackage.ATHING__ADOUBLE, value);
    assertEquals(Double.parseDouble(value), thing.getADouble(), 0);
    thing.setADouble(Double.parseDouble(value)+1);
    SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble"), value);
    assertEquals(Double.parseDouble(value), thing.getADouble(), 0);
    thing.setADouble(Double.parseDouble(value)+1);
    SDOUtil.setString((EObject)thing, "aDouble", value);
    assertEquals(Double.parseDouble(value), thing.getADouble(), 0);
    thing.setADouble(Double.parseDouble(value)+1);
    //float
    SDOUtil.setString((EObject)thing, TypesPackage.ATHING__AFLOAT, value);
    assertEquals(Float.parseFloat(value), thing.getAFloat(), 0);
    thing.setAFloat(Float.parseFloat(value)+1);
    SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat"), value);
    assertEquals(Float.parseFloat(value), thing.getAFloat(), 0);
    thing.setAFloat(Float.parseFloat(value)+1);
    SDOUtil.setString((EObject)thing, "aFloat", value);
    assertEquals(Float.parseFloat(value), thing.getAFloat(), 0);
    thing.setAFloat(Float.parseFloat(value)+1);
    //int
    SDOUtil.setString((EObject)thing, TypesPackage.ATHING__AINT, value);
    assertEquals(Integer.parseInt(value), thing.getAInt());
    thing.setAInt(Integer.parseInt(value)+1);
    SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt"), value);
    assertEquals(Integer.parseInt(value), thing.getAInt());
    thing.setAInt(Integer.parseInt(value)+1);
    SDOUtil.setString((EObject)thing, "aInt", value);
    assertEquals(Integer.parseInt(value), thing.getAInt());
    thing.setAInt(Integer.parseInt(value)+1);
    //long
    SDOUtil.setString((EObject)thing, TypesPackage.ATHING__ALONG, value);
    assertEquals(Long.parseLong(value), thing.getALong());
    thing.setALong(Long.parseLong(value)+1);
    SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong"), value);
    assertEquals(Long.parseLong(value), thing.getALong());
    thing.setALong(Long.parseLong(value)+1);
    SDOUtil.setString((EObject)thing, "aLong", value);
    assertEquals(Long.parseLong(value), thing.getALong());
    thing.setALong(Long.parseLong(value)+1);
    //short
    SDOUtil.setString((EObject)thing, TypesPackage.ATHING__ASHORT, value);
    assertEquals(Short.parseShort(value), thing.getAShort());
    thing.setAShort((short)(Short.parseShort(value)+1));
    SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort"), value);
    assertEquals(Short.parseShort(value), thing.getAShort());
    thing.setAShort((short)(Short.parseShort(value)+1));
    SDOUtil.setString((EObject)thing, "aShort", value);
    assertEquals(Short.parseShort(value), thing.getAShort());
    thing.setAShort((short)(Short.parseShort(value)+1));
    //String
    SDOUtil.setString((EObject)thing, TypesPackage.ATHING__ASTRING, value);
    assertEquals(value, thing.getAString());
    thing.setAString("");
    SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString"), value);
    assertEquals(value, thing.getAString());
    thing.setAString("");
    SDOUtil.setString((EObject)thing, "aString", value);
    assertEquals(value, thing.getAString());
    thing.setAString("");
    //Object
    SDOUtil.setString((EObject)thing, TypesPackage.ATHING__AOBJECT, value);
    assertEquals(value, thing.getAObject());
    thing.setAObject("");
    SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aObject"), value);
    assertEquals(value, thing.getAObject());
    thing.setAObject("");
    SDOUtil.setString((EObject)thing, "aObject", value);
    assertEquals(value, thing.getAObject());
    thing.setAObject("");
    //Number
    SDOUtil.setString((EObject)thing, TypesPackage.ATHING__ANUMBER, value);
    assertEquals(new Double(value), thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aNumber"), value);
    assertEquals(new Double(value), thing.getANumber());
    thing.setANumber(null);
    SDOUtil.setString((EObject)thing, "aNumber", value);
    assertEquals(new Double(value), thing.getANumber());
    thing.setANumber(null);
    //Thread
    Thread thread = new Thread();
    boolean classCastExceptionHappened = false;
    thing.setAThread(thread);
    try
    {
      SDOUtil.setString((EObject)thing, TypesPackage.ATHING__ATHREAD, value);
      classCastExceptionHappened = false;
    }
    catch(ClassCastException e)
    {
      classCastExceptionHappened = true;
    }
    assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		  SDOUtil.setString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aThread"), value);
		  classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
    try
    {
		    SDOUtil.setString((EObject)thing, "aThread", value);
		    classCastExceptionHappened = false;
		}
		catch(ClassCastException e)
		{
		  classCastExceptionHappened = true;
		}
		assertTrue(classCastExceptionHappened);
    assertEquals(thread, thing.getAThread());
  }
}
