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
 * $Id: SDOUtilGetTest.java,v 1.3 2005/06/08 06:17:25 nickb Exp $
 */
package org.eclipse.emf.test.sdo.types;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

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
public class SDOUtilGetTest extends TestCase
{
  private AThing thing;
  public static Test suite()
  {
    TestSuite testSuite = new TestSuite("SDOUtilGetTest");
    testSuite.addTestSuite(SDOUtilGetTest.class);
    return testSuite;
  }
  
  /* (non-Javadoc)
   * @see junit.framework.TestCase#setUp()
   */
  protected void setUp() throws Exception
  {
    thing = TypesFactory.eINSTANCE.createAThing();
  }
    
  public void testGetBigDecimal() throws Exception
  {
    thing.setADecimal(new BigDecimal(Double.MAX_VALUE)); bigDecimalTest();
    thing.setADecimal(new BigDecimal(Double.MIN_VALUE)); bigDecimalTest();
    thing.setADecimal(new BigDecimal(0)); bigDecimalTest();
    thing.setADecimal(new BigDecimal(Math.random())); bigDecimalTest();
    
    thing.setADecimal(null);
    assertNull(SDOUtil.getBigDecimal((EObject)thing, TypesPackage.ATHING__ADECIMAL));
    assertNull(SDOUtil.getBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal")));
    assertNull(SDOUtil.getBigDecimal((EObject)thing, "aDecimal"));    
  }
  
  protected void bigDecimalTest()
  {
    //BigDecimal
    assertEquals(thing.getADecimal(), SDOUtil.getBigDecimal((EObject)thing, TypesPackage.ATHING__ADECIMAL));
    assertEquals(thing.getADecimal(), SDOUtil.getBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal")));
    assertEquals(thing.getADecimal(), SDOUtil.getBigDecimal((EObject)thing, "aDecimal"));
    //BigInteger
    assertEquals(thing.getADecimal().toBigInteger(), SDOUtil.getBigInteger((EObject)thing, TypesPackage.ATHING__ADECIMAL));
    assertEquals(thing.getADecimal().toBigInteger(), SDOUtil.getBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal")));
    assertEquals(thing.getADecimal().toBigInteger(), SDOUtil.getBigInteger((EObject)thing, "aDecimal"));
    //byte
    assertEquals(thing.getADecimal().byteValue(), SDOUtil.getByte((EObject)thing, TypesPackage.ATHING__ADECIMAL));
    assertEquals(thing.getADecimal().byteValue(), SDOUtil.getByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal")));
    assertEquals(thing.getADecimal().byteValue(), SDOUtil.getByte((EObject)thing, "aDecimal"));
    //double
    assertEquals(thing.getADecimal().doubleValue(), SDOUtil.getDouble((EObject)thing, TypesPackage.ATHING__ADECIMAL), 0);
    assertEquals(thing.getADecimal().doubleValue(), SDOUtil.getDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal")), 0);
    assertEquals(thing.getADecimal().doubleValue(), SDOUtil.getDouble((EObject)thing, "aDecimal"), 0);
    //float
    assertEquals(thing.getADecimal().floatValue(), SDOUtil.getFloat((EObject)thing, TypesPackage.ATHING__ADECIMAL), 0);
    assertEquals(thing.getADecimal().floatValue(), SDOUtil.getFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal")), 0);
    assertEquals(thing.getADecimal().floatValue(), SDOUtil.getFloat((EObject)thing, "aDecimal"), 0);
    //int
    assertEquals(thing.getADecimal().intValue(), SDOUtil.getInt((EObject)thing, TypesPackage.ATHING__ADECIMAL));
    assertEquals(thing.getADecimal().intValue(), SDOUtil.getInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal")));
    assertEquals(thing.getADecimal().intValue(), SDOUtil.getInt((EObject)thing, "aDecimal"));
    //long
    assertEquals(thing.getADecimal().longValue(), SDOUtil.getLong((EObject)thing, TypesPackage.ATHING__ADECIMAL));
    assertEquals(thing.getADecimal().longValue(), SDOUtil.getLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal")));
    assertEquals(thing.getADecimal().longValue(), SDOUtil.getLong((EObject)thing, "aDecimal"));
    //short
    assertEquals(thing.getADecimal().shortValue(), SDOUtil.getShort((EObject)thing, TypesPackage.ATHING__ADECIMAL));
    assertEquals(thing.getADecimal().shortValue(), SDOUtil.getShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal")));
    assertEquals(thing.getADecimal().shortValue(), SDOUtil.getShort((EObject)thing, "aDecimal"));
    //String
    assertEquals(thing.getADecimal().toString(), SDOUtil.getString((EObject)thing, TypesPackage.ATHING__ADECIMAL));
    assertEquals(thing.getADecimal().toString(), SDOUtil.getString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDecimal")));
    assertEquals(thing.getADecimal().toString(), SDOUtil.getString((EObject)thing, "aDecimal"));
  }

  public void testGetBigInteger() throws Exception
  {
    thing.setAInteger(BigInteger.valueOf(Long.MAX_VALUE)); bigIntegerTest();
    thing.setAInteger(BigInteger.valueOf(Long.MIN_VALUE)); bigIntegerTest();
    thing.setAInteger(new BigInteger("0")); bigIntegerTest();
    thing.setAInteger(BigInteger.valueOf((long)Math.random())); bigIntegerTest();
    
    thing.setAInteger(null);
    assertNull(SDOUtil.getBigInteger((EObject)thing, TypesPackage.ATHING__AINTEGER));
    assertNull(SDOUtil.getBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger")));
    assertNull(SDOUtil.getBigInteger((EObject)thing, "aInteger"));    
  }
  
  protected void bigIntegerTest()
  {
    //BigDecimal
    assertEquals(BigDecimal.valueOf(thing.getAInteger().longValue()), SDOUtil.getBigDecimal((EObject)thing, TypesPackage.ATHING__AINTEGER));
    assertEquals(BigDecimal.valueOf(thing.getAInteger().longValue()), SDOUtil.getBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger")));
    assertEquals(BigDecimal.valueOf(thing.getAInteger().longValue()), SDOUtil.getBigDecimal((EObject)thing, "aInteger"));
    //BigInteger
    assertEquals(thing.getAInteger(), SDOUtil.getBigInteger((EObject)thing, TypesPackage.ATHING__AINTEGER));
    assertEquals(thing.getAInteger(), SDOUtil.getBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger")));
    assertEquals(thing.getAInteger(), SDOUtil.getBigInteger((EObject)thing, "aInteger"));
    //byte
    assertEquals(thing.getAInteger().byteValue(), SDOUtil.getByte((EObject)thing, TypesPackage.ATHING__AINTEGER));
    assertEquals(thing.getAInteger().byteValue(), SDOUtil.getByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger")));
    assertEquals(thing.getAInteger().byteValue(), SDOUtil.getByte((EObject)thing, "aInteger"));
    //double
    assertEquals(thing.getAInteger().doubleValue(), SDOUtil.getDouble((EObject)thing, TypesPackage.ATHING__AINTEGER), 0);
    assertEquals(thing.getAInteger().doubleValue(), SDOUtil.getDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger")), 0);
    assertEquals(thing.getAInteger().doubleValue(), SDOUtil.getDouble((EObject)thing, "aInteger"), 0);
    //float
    assertEquals(thing.getAInteger().floatValue(), SDOUtil.getFloat((EObject)thing, TypesPackage.ATHING__AINTEGER), 0);
    assertEquals(thing.getAInteger().floatValue(), SDOUtil.getFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger")), 0);
    assertEquals(thing.getAInteger().floatValue(), SDOUtil.getFloat((EObject)thing, "aInteger"), 0);
    //int
    assertEquals(thing.getAInteger().intValue(), SDOUtil.getInt((EObject)thing, TypesPackage.ATHING__AINTEGER));
    assertEquals(thing.getAInteger().intValue(), SDOUtil.getInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger")));
    assertEquals(thing.getAInteger().intValue(), SDOUtil.getInt((EObject)thing, "aInteger"));
    //long
    assertEquals(thing.getAInteger().longValue(), SDOUtil.getLong((EObject)thing, TypesPackage.ATHING__AINTEGER));
    assertEquals(thing.getAInteger().longValue(), SDOUtil.getLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger")));
    assertEquals(thing.getAInteger().longValue(), SDOUtil.getLong((EObject)thing, "aInteger"));
    //short
    assertEquals(thing.getAInteger().shortValue(), SDOUtil.getShort((EObject)thing, TypesPackage.ATHING__AINTEGER));
    assertEquals(thing.getAInteger().shortValue(), SDOUtil.getShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger")));
    assertEquals(thing.getAInteger().shortValue(), SDOUtil.getShort((EObject)thing, "aInteger"));
    //String
    assertEquals(thing.getAInteger().toString(), SDOUtil.getString((EObject)thing, TypesPackage.ATHING__AINTEGER));
    assertEquals(thing.getAInteger().toString(), SDOUtil.getString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger")));
    assertEquals(thing.getAInteger().toString(), SDOUtil.getString((EObject)thing, "aInteger"));
    //byte[]
    assertTrue(Arrays.equals(thing.getAInteger().toByteArray(), SDOUtil.getBytes((EObject)thing, TypesPackage.ATHING__AINTEGER)));
    assertTrue(Arrays.equals(thing.getAInteger().toByteArray(), SDOUtil.getBytes((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInteger"))));
    assertTrue(Arrays.equals(thing.getAInteger().toByteArray(), SDOUtil.getBytes((EObject)thing, "aInteger")));
  }

  public void testGetByte() throws Exception
  {
    thing.setAByte(Byte.MAX_VALUE); byteTest();
    thing.setAByte(Byte.MIN_VALUE); byteTest();
    thing.setAByte((byte)0); byteTest();
    thing.setAByte((byte)Math.random()); byteTest();
  }
  
  protected void byteTest()
  {
    //byte
    assertEquals(thing.getAByte(), SDOUtil.getByte((EObject)thing, TypesPackage.ATHING__ABYTE));
    assertEquals(thing.getAByte(), SDOUtil.getByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aByte")));
    assertEquals(thing.getAByte(), SDOUtil.getByte((EObject)thing, "aByte"));
    //double
    assertEquals(new Byte(thing.getAByte()).doubleValue(), SDOUtil.getDouble((EObject)thing, TypesPackage.ATHING__ABYTE), 0);
    assertEquals(new Byte(thing.getAByte()).doubleValue(), SDOUtil.getDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aByte")), 0);
    assertEquals(new Byte(thing.getAByte()).doubleValue(), SDOUtil.getDouble((EObject)thing, "aByte"), 0);
    //float
    assertEquals(new Byte(thing.getAByte()).floatValue(), SDOUtil.getFloat((EObject)thing, TypesPackage.ATHING__ABYTE), 0);
    assertEquals(new Byte(thing.getAByte()).floatValue(), SDOUtil.getFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aByte")), 0);
    assertEquals(new Byte(thing.getAByte()).floatValue(), SDOUtil.getFloat((EObject)thing, "aByte"), 0);
    //int
    assertEquals(new Byte(thing.getAByte()).intValue(), SDOUtil.getInt((EObject)thing, TypesPackage.ATHING__ABYTE));
    assertEquals(new Byte(thing.getAByte()).intValue(), SDOUtil.getInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aByte")));
    assertEquals(new Byte(thing.getAByte()).intValue(), SDOUtil.getInt((EObject)thing, "aByte"));
    //long
    assertEquals(new Byte(thing.getAByte()).longValue(), SDOUtil.getLong((EObject)thing, TypesPackage.ATHING__ABYTE));
    assertEquals(new Byte(thing.getAByte()).longValue(), SDOUtil.getLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aByte")));
    assertEquals(new Byte(thing.getAByte()).longValue(), SDOUtil.getLong((EObject)thing, "aByte"));
    //short
    assertEquals(new Byte(thing.getAByte()).shortValue(), SDOUtil.getShort((EObject)thing, TypesPackage.ATHING__ABYTE));
    assertEquals(new Byte(thing.getAByte()).shortValue(), SDOUtil.getShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aByte")));
    assertEquals(new Byte(thing.getAByte()).shortValue(), SDOUtil.getShort((EObject)thing, "aByte"));
    //String
    assertEquals(String.valueOf(thing.getAByte()), SDOUtil.getString((EObject)thing, TypesPackage.ATHING__ABYTE));
    assertEquals(String.valueOf(thing.getAByte()), SDOUtil.getString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aByte")));
    assertEquals(String.valueOf(thing.getAByte()), SDOUtil.getString((EObject)thing, "aByte"));       
  }
  
  public void testGetBoolean() throws Exception
  {
    thing.setABoolean(false); booleanTest();
    thing.setABoolean(true); booleanTest();
  } 
  
  protected void booleanTest() throws Exception
  {
    //boolean
    assertEquals(thing.isABoolean(), SDOUtil.getBoolean((EObject)thing, TypesPackage.ATHING__ABOOLEAN));
    assertEquals(thing.isABoolean(), SDOUtil.getBoolean((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aBoolean")));
    assertEquals(thing.isABoolean(), SDOUtil.getBoolean((EObject)thing, "aBoolean"));
    //String
    assertEquals(String.valueOf(thing.isABoolean()), SDOUtil.getString((EObject)thing, TypesPackage.ATHING__ABOOLEAN));
    assertEquals(String.valueOf(thing.isABoolean()), SDOUtil.getString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aBoolean")));
    assertEquals(String.valueOf(thing.isABoolean()), SDOUtil.getString((EObject)thing, "aBoolean"));    
  }

  public void testGetBytes() throws Exception
  {
    byte[] array = new byte []{ Byte.MIN_VALUE, 0, Byte.MAX_VALUE };
    thing.setABytes(array); bytesTest();
    
    thing.setABytes(null);
    assertNull(SDOUtil.getBytes((EObject)thing, TypesPackage.ATHING__ABYTES));
    assertNull(SDOUtil.getBytes((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aBytes")));
    assertNull(SDOUtil.getBytes((EObject)thing, "aBytes"));
  }
  
  protected void bytesTest() throws Exception
  {
    //bytes
    assertTrue(Arrays.equals(thing.getABytes(), SDOUtil.getBytes((EObject)thing, TypesPackage.ATHING__ABYTES)));
    assertTrue(Arrays.equals(thing.getABytes(), SDOUtil.getBytes((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aBytes"))));
    assertTrue(Arrays.equals(thing.getABytes(), SDOUtil.getBytes((EObject)thing, "aBytes")));
    //BigInteger
    assertEquals(new BigInteger(thing.getABytes()), SDOUtil.getBigInteger((EObject)thing, TypesPackage.ATHING__ABYTES));
    assertEquals(new BigInteger(thing.getABytes()), SDOUtil.getBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aBytes")));
    assertEquals(new BigInteger(thing.getABytes()), SDOUtil.getBigInteger((EObject)thing, "aBytes"));    
  }

  public void testGetChar() throws Exception
  {
    thing.setAChar(Character.MAX_VALUE); charTest();
    thing.setAChar(Character.MIN_VALUE); charTest();
    thing.setAChar((char)0); charTest();
    thing.setAChar((char)Math.random()); charTest();
  } 
  
  protected void charTest() throws Exception
  {
    //char
    assertEquals(thing.getAChar(), SDOUtil.getChar((EObject)thing, TypesPackage.ATHING__ACHAR));
    assertEquals(thing.getAChar(), SDOUtil.getChar((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aChar")));
    assertEquals(thing.getAChar(), SDOUtil.getChar((EObject)thing, "aChar"));
    //String
    assertEquals(String.valueOf(thing.getAChar()), SDOUtil.getString((EObject)thing, TypesPackage.ATHING__ACHAR));
    assertEquals(String.valueOf(thing.getAChar()), SDOUtil.getString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aChar")));
    assertEquals(String.valueOf(thing.getAChar()), SDOUtil.getString((EObject)thing, "aChar"));    
  }
  
  public void testGetDate() throws Exception
  {
    thing.setADate(new Date()); dateTest();
    thing.setADate(null); dateTest();
  }
  
  protected void dateTest()
  {
    //Date
    assertEquals(thing.getADate(), SDOUtil.getDate((EObject)thing, TypesPackage.ATHING__ADATE));
    assertEquals(thing.getADate(), SDOUtil.getDate((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDate")));
    assertEquals(thing.getADate(), SDOUtil.getDate((EObject)thing, "aDate"));
    //long
    long expected = thing.getADate() == null ? 0 : thing.getADate().getTime();
    assertEquals(expected, SDOUtil.getLong((EObject)thing, TypesPackage.ATHING__ADATE));
    assertEquals(expected, SDOUtil.getLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDate")));
    assertEquals(expected, SDOUtil.getLong((EObject)thing, "aDate"));    
  }
  
  public void testGetDouble() throws Exception
  {
    thing.setADouble(Double.MAX_VALUE); doubleTest();
    thing.setADouble(Double.MIN_VALUE); doubleTest();
    thing.setADouble(0); doubleTest();
    thing.setADouble(Math.random()); doubleTest();
  }
  
  protected void doubleTest()
  {
    //BigDecimal
    assertEquals(new BigDecimal(thing.getADouble()), SDOUtil.getBigDecimal((EObject)thing, TypesPackage.ATHING__ADOUBLE));
    assertEquals(new BigDecimal(thing.getADouble()), SDOUtil.getBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble")));
    assertEquals(new BigDecimal(thing.getADouble()), SDOUtil.getBigDecimal((EObject)thing, "aDouble"));
    //BigInteger
    assertEquals(BigInteger.valueOf(new Double(thing.getADouble()).longValue()), SDOUtil.getBigInteger((EObject)thing, TypesPackage.ATHING__ADOUBLE));
    assertEquals(BigInteger.valueOf(new Double(thing.getADouble()).longValue()), SDOUtil.getBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble")));
    assertEquals(BigInteger.valueOf(new Double(thing.getADouble()).longValue()), SDOUtil.getBigInteger((EObject)thing, "aDouble"));
    //byte
    assertEquals(new Double(thing.getADouble()).byteValue(), SDOUtil.getByte((EObject)thing, TypesPackage.ATHING__ADOUBLE));
    assertEquals(new Double(thing.getADouble()).byteValue(), SDOUtil.getByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble")));
    assertEquals(new Double(thing.getADouble()).byteValue(), SDOUtil.getByte((EObject)thing, "aDouble"));
    //double
    assertEquals(thing.getADouble(), SDOUtil.getDouble((EObject)thing, TypesPackage.ATHING__ADOUBLE), 0);
    assertEquals(thing.getADouble(), SDOUtil.getDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble")), 0);
    assertEquals(thing.getADouble(), SDOUtil.getDouble((EObject)thing, "aDouble"), 0);
    //float
    assertEquals(new Double(thing.getADouble()).floatValue(), SDOUtil.getFloat((EObject)thing, TypesPackage.ATHING__ADOUBLE), 0);
    assertEquals(new Double(thing.getADouble()).floatValue(), SDOUtil.getFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble")), 0);
    assertEquals(new Double(thing.getADouble()).floatValue(), SDOUtil.getFloat((EObject)thing, "aDouble"), 0);
    //int
    assertEquals(new Double(thing.getADouble()).intValue(), SDOUtil.getInt((EObject)thing, TypesPackage.ATHING__ADOUBLE));
    assertEquals(new Double(thing.getADouble()).intValue(), SDOUtil.getInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble")));
    assertEquals(new Double(thing.getADouble()).intValue(), SDOUtil.getInt((EObject)thing, "aDouble"));
    //long
    assertEquals(new Double(thing.getADouble()).longValue(), SDOUtil.getLong((EObject)thing, TypesPackage.ATHING__ADOUBLE));
    assertEquals(new Double(thing.getADouble()).longValue(), SDOUtil.getLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble")));
    assertEquals(new Double(thing.getADouble()).longValue(), SDOUtil.getLong((EObject)thing, "aDouble"));
    //short
    assertEquals(new Double(thing.getADouble()).shortValue(), SDOUtil.getShort((EObject)thing, TypesPackage.ATHING__ADOUBLE));
    assertEquals(new Double(thing.getADouble()).shortValue(), SDOUtil.getShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble")));
    assertEquals(new Double(thing.getADouble()).shortValue(), SDOUtil.getShort((EObject)thing, "aDouble"));
    //String
    assertEquals(String.valueOf(thing.getADouble()), SDOUtil.getString((EObject)thing, TypesPackage.ATHING__ADOUBLE));
    assertEquals(String.valueOf(thing.getADouble()), SDOUtil.getString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aDouble")));
    assertEquals(String.valueOf(thing.getADouble()), SDOUtil.getString((EObject)thing, "aDouble"));       
  }

  public void testGetFloat() throws Exception
  {
    thing.setAFloat(Float.MAX_VALUE); floatTest();
    thing.setAFloat(Float.MIN_VALUE); floatTest();
    thing.setAFloat(0); floatTest();
    thing.setAFloat((float)Math.random()); floatTest();
  }
  
  protected void floatTest()
  {
    //BigDecimal
    assertEquals(new BigDecimal(thing.getAFloat()), SDOUtil.getBigDecimal((EObject)thing, TypesPackage.ATHING__AFLOAT));
    assertEquals(new BigDecimal(thing.getAFloat()), SDOUtil.getBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat")));
    assertEquals(new BigDecimal(thing.getAFloat()), SDOUtil.getBigDecimal((EObject)thing, "aFloat"));
    //BigInteger
    assertEquals(BigInteger.valueOf(new Float(thing.getAFloat()).longValue()), SDOUtil.getBigInteger((EObject)thing, TypesPackage.ATHING__AFLOAT));
    assertEquals(BigInteger.valueOf(new Float(thing.getAFloat()).longValue()), SDOUtil.getBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat")));
    assertEquals(BigInteger.valueOf(new Float(thing.getAFloat()).longValue()), SDOUtil.getBigInteger((EObject)thing, "aFloat"));
    //byte
    assertEquals(new Float(thing.getAFloat()).byteValue(), SDOUtil.getByte((EObject)thing, TypesPackage.ATHING__AFLOAT));
    assertEquals(new Float(thing.getAFloat()).byteValue(), SDOUtil.getByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat")));
    assertEquals(new Float(thing.getAFloat()).byteValue(), SDOUtil.getByte((EObject)thing, "aFloat"));
    //double
    assertEquals(new Float(thing.getAFloat()).doubleValue(), SDOUtil.getDouble((EObject)thing, TypesPackage.ATHING__AFLOAT), 0);
    assertEquals(new Float(thing.getAFloat()).doubleValue(), SDOUtil.getDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat")), 0);
    assertEquals(new Float(thing.getAFloat()).doubleValue(), SDOUtil.getDouble((EObject)thing, "aFloat"), 0);
    //float
    assertEquals(thing.getAFloat(), SDOUtil.getFloat((EObject)thing, TypesPackage.ATHING__AFLOAT), 0);
    assertEquals(thing.getAFloat(), SDOUtil.getFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat")), 0);
    assertEquals(thing.getAFloat(), SDOUtil.getFloat((EObject)thing, "aFloat"), 0);
    //int
    assertEquals(new Float(thing.getAFloat()).intValue(), SDOUtil.getInt((EObject)thing, TypesPackage.ATHING__AFLOAT));
    assertEquals(new Float(thing.getAFloat()).intValue(), SDOUtil.getInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat")));
    assertEquals(new Float(thing.getAFloat()).intValue(), SDOUtil.getInt((EObject)thing, "aFloat"));
    //long
    assertEquals(new Float(thing.getAFloat()).longValue(), SDOUtil.getLong((EObject)thing, TypesPackage.ATHING__AFLOAT));
    assertEquals(new Float(thing.getAFloat()).longValue(), SDOUtil.getLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat")));
    assertEquals(new Float(thing.getAFloat()).longValue(), SDOUtil.getLong((EObject)thing, "aFloat"));
    //short
    assertEquals(new Float(thing.getAFloat()).shortValue(), SDOUtil.getShort((EObject)thing, TypesPackage.ATHING__AFLOAT));
    assertEquals(new Float(thing.getAFloat()).shortValue(), SDOUtil.getShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat")));
    assertEquals(new Float(thing.getAFloat()).shortValue(), SDOUtil.getShort((EObject)thing, "aFloat"));
    //String
    assertEquals(String.valueOf(thing.getAFloat()), SDOUtil.getString((EObject)thing, TypesPackage.ATHING__AFLOAT));
    assertEquals(String.valueOf(thing.getAFloat()), SDOUtil.getString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aFloat")));
    assertEquals(String.valueOf(thing.getAFloat()), SDOUtil.getString((EObject)thing, "aFloat"));       
  }

  public void testGetInt() throws Exception
  {
    thing.setAInt(Integer.MAX_VALUE); intTest();
    thing.setAInt(Integer.MIN_VALUE); intTest();
    thing.setAInt(0); intTest();
    thing.setAInt((int)Math.random()); intTest();
  }
  
  protected void intTest()
  {
    //BigDecimal
    assertEquals(new BigDecimal(thing.getAInt()), SDOUtil.getBigDecimal((EObject)thing, TypesPackage.ATHING__AINT));
    assertEquals(new BigDecimal(thing.getAInt()), SDOUtil.getBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt")));
    assertEquals(new BigDecimal(thing.getAInt()), SDOUtil.getBigDecimal((EObject)thing, "aInt"));
    //BigInteger
    assertEquals(BigInteger.valueOf(new Integer(thing.getAInt()).longValue()), SDOUtil.getBigInteger((EObject)thing, TypesPackage.ATHING__AINT));
    assertEquals(BigInteger.valueOf(new Integer(thing.getAInt()).longValue()), SDOUtil.getBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt")));
    assertEquals(BigInteger.valueOf(new Integer(thing.getAInt()).longValue()), SDOUtil.getBigInteger((EObject)thing, "aInt"));
    //byte
    assertEquals(new Integer(thing.getAInt()).byteValue(), SDOUtil.getByte((EObject)thing, TypesPackage.ATHING__AINT));
    assertEquals(new Integer(thing.getAInt()).byteValue(), SDOUtil.getByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt")));
    assertEquals(new Integer(thing.getAInt()).byteValue(), SDOUtil.getByte((EObject)thing, "aInt"));
    //double
    assertEquals(new Integer(thing.getAInt()).doubleValue(), SDOUtil.getDouble((EObject)thing, TypesPackage.ATHING__AINT), 0);
    assertEquals(new Integer(thing.getAInt()).doubleValue(), SDOUtil.getDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt")), 0);
    assertEquals(new Integer(thing.getAInt()).doubleValue(), SDOUtil.getDouble((EObject)thing, "aInt"), 0);
    //float
    assertEquals(new Integer(thing.getAInt()).floatValue(), SDOUtil.getFloat((EObject)thing, TypesPackage.ATHING__AINT), 0);
    assertEquals(new Integer(thing.getAInt()).floatValue(), SDOUtil.getFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt")), 0);
    assertEquals(new Integer(thing.getAInt()).floatValue(), SDOUtil.getFloat((EObject)thing, "aInt"), 0);
    //int
    assertEquals(thing.getAInt(), SDOUtil.getInt((EObject)thing, TypesPackage.ATHING__AINT));
    assertEquals(thing.getAInt(), SDOUtil.getInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt")));
    assertEquals(thing.getAInt(), SDOUtil.getInt((EObject)thing, "aInt"));
    //long
    assertEquals(new Integer(thing.getAInt()).longValue(), SDOUtil.getLong((EObject)thing, TypesPackage.ATHING__AINT));
    assertEquals(new Integer(thing.getAInt()).longValue(), SDOUtil.getLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt")));
    assertEquals(new Integer(thing.getAInt()).longValue(), SDOUtil.getLong((EObject)thing, "aInt"));
    //short
    assertEquals(new Integer(thing.getAInt()).shortValue(), SDOUtil.getShort((EObject)thing, TypesPackage.ATHING__AINT));
    assertEquals(new Integer(thing.getAInt()).shortValue(), SDOUtil.getShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt")));
    assertEquals(new Integer(thing.getAInt()).shortValue(), SDOUtil.getShort((EObject)thing, "aInt"));
    //String
    assertEquals(String.valueOf(thing.getAInt()), SDOUtil.getString((EObject)thing, TypesPackage.ATHING__AINT));
    assertEquals(String.valueOf(thing.getAInt()), SDOUtil.getString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aInt")));
    assertEquals(String.valueOf(thing.getAInt()), SDOUtil.getString((EObject)thing, "aInt"));       
  }

  public void testGetLong() throws Exception
  {
    thing.setALong(Long.MAX_VALUE); longTest();
    thing.setALong(Long.MIN_VALUE); longTest();
    thing.setALong(0); longTest();
    thing.setALong((long)Math.random()); longTest();
  }
  
  protected void longTest()
  {
    //BigDecimal
    assertEquals(new BigDecimal(thing.getALong()), SDOUtil.getBigDecimal((EObject)thing, TypesPackage.ATHING__ALONG));
    assertEquals(new BigDecimal(thing.getALong()), SDOUtil.getBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong")));
    assertEquals(new BigDecimal(thing.getALong()), SDOUtil.getBigDecimal((EObject)thing, "aLong"));
    //BigInteger
    assertEquals(BigInteger.valueOf(new Long(thing.getALong()).longValue()), SDOUtil.getBigInteger((EObject)thing, TypesPackage.ATHING__ALONG));
    assertEquals(BigInteger.valueOf(new Long(thing.getALong()).longValue()), SDOUtil.getBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong")));
    assertEquals(BigInteger.valueOf(new Long(thing.getALong()).longValue()), SDOUtil.getBigInteger((EObject)thing, "aLong"));
    //byte
    assertEquals(new Long(thing.getALong()).byteValue(), SDOUtil.getByte((EObject)thing, TypesPackage.ATHING__ALONG));
    assertEquals(new Long(thing.getALong()).byteValue(), SDOUtil.getByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong")));
    assertEquals(new Long(thing.getALong()).byteValue(), SDOUtil.getByte((EObject)thing, "aLong"));
    //double
    assertEquals(new Long(thing.getALong()).doubleValue(), SDOUtil.getDouble((EObject)thing, TypesPackage.ATHING__ALONG), 0);
    assertEquals(new Long(thing.getALong()).doubleValue(), SDOUtil.getDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong")), 0);
    assertEquals(new Long(thing.getALong()).doubleValue(), SDOUtil.getDouble((EObject)thing, "aLong"), 0);
    //float
    assertEquals(new Long(thing.getALong()).floatValue(), SDOUtil.getFloat((EObject)thing, TypesPackage.ATHING__ALONG), 0);
    assertEquals(new Long(thing.getALong()).floatValue(), SDOUtil.getFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong")), 0);
    assertEquals(new Long(thing.getALong()).floatValue(), SDOUtil.getFloat((EObject)thing, "aLong"), 0);
    //int
    assertEquals(new Long(thing.getALong()).intValue(), SDOUtil.getInt((EObject)thing, TypesPackage.ATHING__ALONG));
    assertEquals(new Long(thing.getALong()).intValue(), SDOUtil.getInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong")));
    assertEquals(new Long(thing.getALong()).intValue(), SDOUtil.getInt((EObject)thing, "aLong"));
    //long
    assertEquals(thing.getALong(), SDOUtil.getLong((EObject)thing, TypesPackage.ATHING__ALONG));
    assertEquals(thing.getALong(), SDOUtil.getLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong")));
    assertEquals(thing.getALong(), SDOUtil.getLong((EObject)thing, "aLong"));
    //short
    assertEquals(new Long(thing.getALong()).shortValue(), SDOUtil.getShort((EObject)thing, TypesPackage.ATHING__ALONG));
    assertEquals(new Long(thing.getALong()).shortValue(), SDOUtil.getShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong")));
    assertEquals(new Long(thing.getALong()).shortValue(), SDOUtil.getShort((EObject)thing, "aLong"));
    //String
    assertEquals(String.valueOf(thing.getALong()), SDOUtil.getString((EObject)thing, TypesPackage.ATHING__ALONG));
    assertEquals(String.valueOf(thing.getALong()), SDOUtil.getString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong")));
    assertEquals(String.valueOf(thing.getALong()), SDOUtil.getString((EObject)thing, "aLong"));       
    //Date
    assertEquals(new Date(thing.getALong()), SDOUtil.getDate((EObject)thing, TypesPackage.ATHING__ALONG));
    assertEquals(new Date(thing.getALong()), SDOUtil.getDate((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aLong")));
    assertEquals(new Date(thing.getALong()), SDOUtil.getDate((EObject)thing, "aLong"));       
  }

  public void testGetShort() throws Exception
  {
    thing.setAShort(Short.MAX_VALUE); shortTest();
    thing.setAShort(Short.MIN_VALUE); shortTest();
    thing.setAShort((short)0); shortTest();
    thing.setAShort((short)Math.random()); shortTest();
  }
  
  protected void shortTest()
  {
    //BigDecimal
    assertEquals(new BigDecimal(thing.getAShort()), SDOUtil.getBigDecimal((EObject)thing, TypesPackage.ATHING__ASHORT));
    assertEquals(new BigDecimal(thing.getAShort()), SDOUtil.getBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort")));
    assertEquals(new BigDecimal(thing.getAShort()), SDOUtil.getBigDecimal((EObject)thing, "aShort"));
    //BigInteger
    assertEquals(BigInteger.valueOf(new Short(thing.getAShort()).longValue()), SDOUtil.getBigInteger((EObject)thing, TypesPackage.ATHING__ASHORT));
    assertEquals(BigInteger.valueOf(new Short(thing.getAShort()).longValue()), SDOUtil.getBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort")));
    assertEquals(BigInteger.valueOf(new Short(thing.getAShort()).longValue()), SDOUtil.getBigInteger((EObject)thing, "aShort"));
    //byte
    assertEquals(new Short(thing.getAShort()).byteValue(), SDOUtil.getByte((EObject)thing, TypesPackage.ATHING__ASHORT));
    assertEquals(new Short(thing.getAShort()).byteValue(), SDOUtil.getByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort")));
    assertEquals(new Short(thing.getAShort()).byteValue(), SDOUtil.getByte((EObject)thing, "aShort"));
    //double
    assertEquals(new Short(thing.getAShort()).doubleValue(), SDOUtil.getDouble((EObject)thing, TypesPackage.ATHING__ASHORT), 0);
    assertEquals(new Short(thing.getAShort()).doubleValue(), SDOUtil.getDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort")), 0);
    assertEquals(new Short(thing.getAShort()).doubleValue(), SDOUtil.getDouble((EObject)thing, "aShort"), 0);
    //float
    assertEquals(new Short(thing.getAShort()).floatValue(), SDOUtil.getFloat((EObject)thing, TypesPackage.ATHING__ASHORT), 0);
    assertEquals(new Short(thing.getAShort()).floatValue(), SDOUtil.getFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort")), 0);
    assertEquals(new Short(thing.getAShort()).floatValue(), SDOUtil.getFloat((EObject)thing, "aShort"), 0);
    //int
    assertEquals(new Short(thing.getAShort()).intValue(), SDOUtil.getInt((EObject)thing, TypesPackage.ATHING__ASHORT));
    assertEquals(new Short(thing.getAShort()).intValue(), SDOUtil.getInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort")));
    assertEquals(new Short(thing.getAShort()).intValue(), SDOUtil.getInt((EObject)thing, "aShort"));
    //long
    assertEquals(new Short(thing.getAShort()).longValue(), SDOUtil.getLong((EObject)thing, TypesPackage.ATHING__ASHORT));
    assertEquals(new Short(thing.getAShort()).longValue(), SDOUtil.getLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort")));
    assertEquals(new Short(thing.getAShort()).longValue(), SDOUtil.getLong((EObject)thing, "aShort"));
    //short
    assertEquals(thing.getAShort(), SDOUtil.getShort((EObject)thing, TypesPackage.ATHING__ASHORT));
    assertEquals(thing.getAShort(), SDOUtil.getShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort")));
    assertEquals(thing.getAShort(), SDOUtil.getShort((EObject)thing, "aShort"));
    //String
    assertEquals(String.valueOf(thing.getAShort()), SDOUtil.getString((EObject)thing, TypesPackage.ATHING__ASHORT));
    assertEquals(String.valueOf(thing.getAShort()), SDOUtil.getString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aShort")));
    assertEquals(String.valueOf(thing.getAShort()), SDOUtil.getString((EObject)thing, "aShort"));
  }

  public void testGetString() throws Exception
  {
    thing.setAString(String.valueOf(Byte.MAX_VALUE)); stringNumberTest();
    thing.setAString(String.valueOf(Byte.MIN_VALUE)); stringNumberTest();
    thing.setAString(String.valueOf(0)); stringNumberTest();
    thing.setAString(String.valueOf((byte)Math.random())); stringNumberTest();
    
    thing.setAString(null);
    assertNull(SDOUtil.getString((EObject)thing, TypesPackage.ATHING__ASTRING));
    assertNull(SDOUtil.getString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString")));
    assertNull(SDOUtil.getString((EObject)thing, "aString"));

    thing.setAString("");
    assertEquals("", SDOUtil.getString((EObject)thing, TypesPackage.ATHING__ASTRING));
    assertEquals("", SDOUtil.getString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString")));
    assertEquals("", SDOUtil.getString((EObject)thing, "aString"));
    
    //boolean
    thing.setAString(Boolean.TRUE.toString());
    assertEquals(Boolean.valueOf(thing.getAString()).booleanValue(), SDOUtil.getBoolean((EObject)thing, TypesPackage.ATHING__ASTRING));
    assertEquals(Boolean.valueOf(thing.getAString()).booleanValue(), SDOUtil.getBoolean((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString")));
    assertEquals(Boolean.valueOf(thing.getAString()).booleanValue(), SDOUtil.getBoolean((EObject)thing, "aString"));
    thing.setAString(Boolean.FALSE.toString());
    assertEquals(Boolean.valueOf(thing.getAString()).booleanValue(), SDOUtil.getBoolean((EObject)thing, TypesPackage.ATHING__ASTRING));
    assertEquals(Boolean.valueOf(thing.getAString()).booleanValue(), SDOUtil.getBoolean((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString")));
    assertEquals(Boolean.valueOf(thing.getAString()).booleanValue(), SDOUtil.getBoolean((EObject)thing, "aString"));
    thing.setAString("aTest");
    assertEquals(Boolean.valueOf(thing.getAString()).booleanValue(), SDOUtil.getBoolean((EObject)thing, TypesPackage.ATHING__ASTRING));
    assertEquals(Boolean.valueOf(thing.getAString()).booleanValue(), SDOUtil.getBoolean((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString")));
    assertEquals(Boolean.valueOf(thing.getAString()).booleanValue(), SDOUtil.getBoolean((EObject)thing, "aString"));    
  }
  
  protected void stringNumberTest()
  {
    //BigDecimal
    assertEquals(new BigDecimal(thing.getAString()), SDOUtil.getBigDecimal((EObject)thing, TypesPackage.ATHING__ASTRING));
    assertEquals(new BigDecimal(thing.getAString()), SDOUtil.getBigDecimal((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString")));
    assertEquals(new BigDecimal(thing.getAString()), SDOUtil.getBigDecimal((EObject)thing, "aString"));
    //BigInteger
    assertEquals(new BigInteger(thing.getAString()), SDOUtil.getBigInteger((EObject)thing, TypesPackage.ATHING__ASTRING));
    assertEquals(new BigInteger(thing.getAString()), SDOUtil.getBigInteger((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString")));
    assertEquals(new BigInteger(thing.getAString()), SDOUtil.getBigInteger((EObject)thing, "aString"));
    //byte
    assertEquals(Byte.parseByte(thing.getAString()), SDOUtil.getByte((EObject)thing, TypesPackage.ATHING__ASTRING));
    assertEquals(Byte.parseByte(thing.getAString()), SDOUtil.getByte((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString")));
    assertEquals(Byte.parseByte(thing.getAString()), SDOUtil.getByte((EObject)thing, "aString"));
    //double
    assertEquals(Double.parseDouble(thing.getAString()), SDOUtil.getDouble((EObject)thing, TypesPackage.ATHING__ASTRING), 0);
    assertEquals(Double.parseDouble(thing.getAString()), SDOUtil.getDouble((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString")), 0);
    assertEquals(Double.parseDouble(thing.getAString()), SDOUtil.getDouble((EObject)thing, "aString"), 0);
    //float
    assertEquals(Float.parseFloat(thing.getAString()), SDOUtil.getFloat((EObject)thing, TypesPackage.ATHING__ASTRING), 0);
    assertEquals(Float.parseFloat(thing.getAString()), SDOUtil.getFloat((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString")), 0);
    assertEquals(Float.parseFloat(thing.getAString()), SDOUtil.getFloat((EObject)thing, "aString"), 0);
    //int
    assertEquals(Integer.parseInt(thing.getAString()), SDOUtil.getInt((EObject)thing, TypesPackage.ATHING__ASTRING));
    assertEquals(Integer.parseInt(thing.getAString()), SDOUtil.getInt((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString")));
    assertEquals(Integer.parseInt(thing.getAString()), SDOUtil.getInt((EObject)thing, "aString"));
    //long
    assertEquals(Long.parseLong(thing.getAString()), SDOUtil.getLong((EObject)thing, TypesPackage.ATHING__ASTRING));
    assertEquals(Long.parseLong(thing.getAString()), SDOUtil.getLong((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString")));
    assertEquals(Long.parseLong(thing.getAString()), SDOUtil.getLong((EObject)thing, "aString"));
    //short
    assertEquals(Short.parseShort(thing.getAString()), SDOUtil.getShort((EObject)thing, TypesPackage.ATHING__ASTRING));
    assertEquals(Short.parseShort(thing.getAString()), SDOUtil.getShort((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString")));
    assertEquals(Short.parseShort(thing.getAString()), SDOUtil.getShort((EObject)thing, "aString"));
    //String
    assertEquals(thing.getAString(), SDOUtil.getString((EObject)thing, TypesPackage.ATHING__ASTRING));
    assertEquals(thing.getAString(), SDOUtil.getString((EObject)thing, ((EDataObject)TypesFactory.eINSTANCE.createAThing()).getType().getProperty("aString")));
    assertEquals(thing.getAString(), SDOUtil.getString((EObject)thing, "aString"));       
  }
}
