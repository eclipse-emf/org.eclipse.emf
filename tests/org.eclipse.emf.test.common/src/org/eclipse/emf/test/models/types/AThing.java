/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: AThing.java,v 1.5 2007/06/15 21:22:18 emerks Exp $
 */
package org.eclipse.emf.test.models.types;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Date;
import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AThing</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#isABoolean <em>ABoolean</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getAByte <em>AByte</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getADecimal <em>ADecimal</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getAFloat <em>AFloat</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getADouble <em>ADouble</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getAInt <em>AInt</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getAInteger <em>AInteger</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getALong <em>ALong</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getAShort <em>AShort</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getAString <em>AString</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getAChar <em>AChar</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getADate <em>ADate</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getABytes <em>ABytes</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getANumber <em>ANumber</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getAObject <em>AObject</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getAThread <em>AThread</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getManyBoolean <em>Many Boolean</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getManyByte <em>Many Byte</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getManyDecimal <em>Many Decimal</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getManyFloat <em>Many Float</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getManyDouble <em>Many Double</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getManyInt <em>Many Int</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getManyInteger <em>Many Integer</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getManyLong <em>Many Long</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getManyShort <em>Many Short</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getManyString <em>Many String</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getManyChar <em>Many Char</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getManyDate <em>Many Date</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getManyBytes <em>Many Bytes</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getManyNumber <em>Many Number</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getManyObject <em>Many Object</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.types.AThing#getManyThread <em>Many Thread</em>}</li>
 * </ul>
 * </p>
 *
 * @model extendedMetaData="name='AThing' kind='elementOnly'"
 * @generated
 */
public interface AThing
{
  /**
   * Returns the value of the '<em><b>ABoolean</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>ABoolean</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>ABoolean</em>' attribute.
   * @see #isSetABoolean()
   * @see #unsetABoolean()
   * @see #setABoolean(boolean)
   * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean" required="true"
   *        extendedMetaData="kind='element' name='aBoolean'"
   * @generated
   */
  boolean isABoolean();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.types.AThing#isABoolean <em>ABoolean</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>ABoolean</em>' attribute.
   * @see #isSetABoolean()
   * @see #unsetABoolean()
   * @see #isABoolean()
   * @generated
   */
  void setABoolean(boolean value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.types.AThing#isABoolean <em>ABoolean</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetABoolean()
   * @see #isABoolean()
   * @see #setABoolean(boolean)
   * @generated
   */
  void unsetABoolean();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.types.AThing#isABoolean <em>ABoolean</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>ABoolean</em>' attribute is set.
   * @see #unsetABoolean()
   * @see #isABoolean()
   * @see #setABoolean(boolean)
   * @generated
   */
  boolean isSetABoolean();

  /**
   * Returns the value of the '<em><b>AByte</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>AByte</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>AByte</em>' attribute.
   * @see #isSetAByte()
   * @see #unsetAByte()
   * @see #setAByte(byte)
   * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Byte" required="true"
   *        extendedMetaData="kind='element' name='aByte'"
   * @generated
   */
  byte getAByte();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAByte <em>AByte</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>AByte</em>' attribute.
   * @see #isSetAByte()
   * @see #unsetAByte()
   * @see #getAByte()
   * @generated
   */
  void setAByte(byte value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAByte <em>AByte</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetAByte()
   * @see #getAByte()
   * @see #setAByte(byte)
   * @generated
   */
  void unsetAByte();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAByte <em>AByte</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>AByte</em>' attribute is set.
   * @see #unsetAByte()
   * @see #getAByte()
   * @see #setAByte(byte)
   * @generated
   */
  boolean isSetAByte();

  /**
   * Returns the value of the '<em><b>ADecimal</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>ADecimal</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>ADecimal</em>' attribute.
   * @see #setADecimal(BigDecimal)
   * @model dataType="org.eclipse.emf.ecore.xml.type.Decimal" required="true"
   *        extendedMetaData="kind='element' name='aDecimal'"
   * @generated
   */
  BigDecimal getADecimal();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getADecimal <em>ADecimal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>ADecimal</em>' attribute.
   * @see #getADecimal()
   * @generated
   */
  void setADecimal(BigDecimal value);

  /**
   * Returns the value of the '<em><b>AFloat</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>AFloat</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>AFloat</em>' attribute.
   * @see #isSetAFloat()
   * @see #unsetAFloat()
   * @see #setAFloat(float)
   * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float" required="true"
   *        extendedMetaData="kind='element' name='aFloat'"
   * @generated
   */
  float getAFloat();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAFloat <em>AFloat</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>AFloat</em>' attribute.
   * @see #isSetAFloat()
   * @see #unsetAFloat()
   * @see #getAFloat()
   * @generated
   */
  void setAFloat(float value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAFloat <em>AFloat</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetAFloat()
   * @see #getAFloat()
   * @see #setAFloat(float)
   * @generated
   */
  void unsetAFloat();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAFloat <em>AFloat</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>AFloat</em>' attribute is set.
   * @see #unsetAFloat()
   * @see #getAFloat()
   * @see #setAFloat(float)
   * @generated
   */
  boolean isSetAFloat();

  /**
   * Returns the value of the '<em><b>ADouble</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>ADouble</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>ADouble</em>' attribute.
   * @see #isSetADouble()
   * @see #unsetADouble()
   * @see #setADouble(double)
   * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
   *        extendedMetaData="kind='element' name='aDouble'"
   * @generated
   */
  double getADouble();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getADouble <em>ADouble</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>ADouble</em>' attribute.
   * @see #isSetADouble()
   * @see #unsetADouble()
   * @see #getADouble()
   * @generated
   */
  void setADouble(double value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getADouble <em>ADouble</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetADouble()
   * @see #getADouble()
   * @see #setADouble(double)
   * @generated
   */
  void unsetADouble();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.types.AThing#getADouble <em>ADouble</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>ADouble</em>' attribute is set.
   * @see #unsetADouble()
   * @see #getADouble()
   * @see #setADouble(double)
   * @generated
   */
  boolean isSetADouble();

  /**
   * Returns the value of the '<em><b>AInt</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>AInt</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>AInt</em>' attribute.
   * @see #isSetAInt()
   * @see #unsetAInt()
   * @see #setAInt(int)
   * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
   *        extendedMetaData="kind='element' name='aInt'"
   * @generated
   */
  int getAInt();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAInt <em>AInt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>AInt</em>' attribute.
   * @see #isSetAInt()
   * @see #unsetAInt()
   * @see #getAInt()
   * @generated
   */
  void setAInt(int value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAInt <em>AInt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetAInt()
   * @see #getAInt()
   * @see #setAInt(int)
   * @generated
   */
  void unsetAInt();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAInt <em>AInt</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>AInt</em>' attribute is set.
   * @see #unsetAInt()
   * @see #getAInt()
   * @see #setAInt(int)
   * @generated
   */
  boolean isSetAInt();

  /**
   * Returns the value of the '<em><b>AInteger</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>AInteger</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>AInteger</em>' attribute.
   * @see #setAInteger(BigInteger)
   * @model dataType="org.eclipse.emf.ecore.xml.type.Integer" required="true"
   *        extendedMetaData="kind='element' name='aInteger'"
   * @generated
   */
  BigInteger getAInteger();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAInteger <em>AInteger</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>AInteger</em>' attribute.
   * @see #getAInteger()
   * @generated
   */
  void setAInteger(BigInteger value);

  /**
   * Returns the value of the '<em><b>ALong</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>ALong</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>ALong</em>' attribute.
   * @see #isSetALong()
   * @see #unsetALong()
   * @see #setALong(long)
   * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Long" required="true"
   *        extendedMetaData="kind='element' name='aLong'"
   * @generated
   */
  long getALong();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getALong <em>ALong</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>ALong</em>' attribute.
   * @see #isSetALong()
   * @see #unsetALong()
   * @see #getALong()
   * @generated
   */
  void setALong(long value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getALong <em>ALong</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetALong()
   * @see #getALong()
   * @see #setALong(long)
   * @generated
   */
  void unsetALong();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.types.AThing#getALong <em>ALong</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>ALong</em>' attribute is set.
   * @see #unsetALong()
   * @see #getALong()
   * @see #setALong(long)
   * @generated
   */
  boolean isSetALong();

  /**
   * Returns the value of the '<em><b>AShort</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>AShort</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>AShort</em>' attribute.
   * @see #isSetAShort()
   * @see #unsetAShort()
   * @see #setAShort(short)
   * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Short" required="true"
   *        extendedMetaData="kind='element' name='aShort'"
   * @generated
   */
  short getAShort();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAShort <em>AShort</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>AShort</em>' attribute.
   * @see #isSetAShort()
   * @see #unsetAShort()
   * @see #getAShort()
   * @generated
   */
  void setAShort(short value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAShort <em>AShort</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetAShort()
   * @see #getAShort()
   * @see #setAShort(short)
   * @generated
   */
  void unsetAShort();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAShort <em>AShort</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>AShort</em>' attribute is set.
   * @see #unsetAShort()
   * @see #getAShort()
   * @see #setAShort(short)
   * @generated
   */
  boolean isSetAShort();

  /**
   * Returns the value of the '<em><b>AString</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>AString</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>AString</em>' attribute.
   * @see #setAString(String)
   * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='aString'"
   * @generated
   */
  String getAString();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAString <em>AString</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>AString</em>' attribute.
   * @see #getAString()
   * @generated
   */
  void setAString(String value);

  /**
   * Returns the value of the '<em><b>AChar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>AChar</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>AChar</em>' attribute.
   * @see #isSetAChar()
   * @see #unsetAChar()
   * @see #setAChar(char)
   * @model unsettable="true" dataType="org.eclipse.emf.test.models.types.MyChar" required="true"
   *        extendedMetaData="kind='element' name='aChar'"
   * @generated
   */
  char getAChar();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAChar <em>AChar</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>AChar</em>' attribute.
   * @see #isSetAChar()
   * @see #unsetAChar()
   * @see #getAChar()
   * @generated
   */
  void setAChar(char value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAChar <em>AChar</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetAChar()
   * @see #getAChar()
   * @see #setAChar(char)
   * @generated
   */
  void unsetAChar();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAChar <em>AChar</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>AChar</em>' attribute is set.
   * @see #unsetAChar()
   * @see #getAChar()
   * @see #setAChar(char)
   * @generated
   */
  boolean isSetAChar();

  /**
   * Returns the value of the '<em><b>ADate</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>ADate</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>ADate</em>' attribute.
   * @see #setADate(Date)
   * @model dataType="org.eclipse.emf.test.models.types.MyDate" required="true"
   *        extendedMetaData="kind='element' name='aDate'"
   * @generated
   */
  Date getADate();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getADate <em>ADate</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>ADate</em>' attribute.
   * @see #getADate()
   * @generated
   */
  void setADate(Date value);

  /**
   * Returns the value of the '<em><b>ABytes</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>ABytes</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>ABytes</em>' attribute.
   * @see #setABytes(byte[])
   * @model dataType="org.eclipse.emf.test.models.types.MyBytes" required="true"
   *        extendedMetaData="kind='element' name='aBytes'"
   * @generated
   */
  byte[] getABytes();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getABytes <em>ABytes</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>ABytes</em>' attribute.
   * @see #getABytes()
   * @generated
   */
  void setABytes(byte[] value);

  /**
   * Returns the value of the '<em><b>ANumber</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>ANumber</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>ANumber</em>' attribute.
   * @see #setANumber(Number)
   * @model dataType="org.eclipse.emf.test.models.types.MyNumber" required="true"
   *        extendedMetaData="kind='element' name='aNumber'"
   * @generated
   */
  Number getANumber();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getANumber <em>ANumber</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>ANumber</em>' attribute.
   * @see #getANumber()
   * @generated
   */
  void setANumber(Number value);

  /**
   * Returns the value of the '<em><b>AObject</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>AObject</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>AObject</em>' attribute.
   * @see #setAObject(Object)
   * @model dataType="org.eclipse.emf.test.models.types.MyObject" required="true"
   *        extendedMetaData="kind='element' name='aObject'"
   * @generated
   */
  Object getAObject();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAObject <em>AObject</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>AObject</em>' attribute.
   * @see #getAObject()
   * @generated
   */
  void setAObject(Object value);

  /**
   * Returns the value of the '<em><b>AThread</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>AThread</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>AThread</em>' attribute.
   * @see #setAThread(Thread)
   * @model dataType="org.eclipse.emf.test.models.types.MyThread" required="true"
   *        extendedMetaData="kind='element' name='aThread'"
   * @generated
   */
  Thread getAThread();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.types.AThing#getAThread <em>AThread</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>AThread</em>' attribute.
   * @see #getAThread()
   * @generated
   */
  void setAThread(Thread value);

  /**
   * Returns the value of the '<em><b>Many Boolean</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Boolean}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many Boolean</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many Boolean</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Boolean" required="true"
   *        extendedMetaData="kind='element' name='manyBoolean'"
   * @generated
   */
  List<Boolean> getManyBoolean();

  /**
   * Returns the value of the '<em><b>Many Byte</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Byte}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many Byte</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many Byte</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Byte" required="true"
   *        extendedMetaData="kind='element' name='manyByte'"
   * @generated
   */
  List<Byte> getManyByte();

  /**
   * Returns the value of the '<em><b>Many Decimal</b></em>' attribute list.
   * The list contents are of type {@link java.math.BigDecimal}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many Decimal</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many Decimal</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal" required="true"
   *        extendedMetaData="kind='element' name='manyDecimal'"
   * @generated
   */
  List<BigDecimal> getManyDecimal();

  /**
   * Returns the value of the '<em><b>Many Float</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Float}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many Float</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many Float</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Float" required="true"
   *        extendedMetaData="kind='element' name='manyFloat'"
   * @generated
   */
  List<Float> getManyFloat();

  /**
   * Returns the value of the '<em><b>Many Double</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Double}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many Double</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many Double</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
   *        extendedMetaData="kind='element' name='manyDouble'"
   * @generated
   */
  List<Double> getManyDouble();

  /**
   * Returns the value of the '<em><b>Many Int</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Integer}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many Int</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many Int</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
   *        extendedMetaData="kind='element' name='manyInt'"
   * @generated
   */
  List<Integer> getManyInt();

  /**
   * Returns the value of the '<em><b>Many Integer</b></em>' attribute list.
   * The list contents are of type {@link java.math.BigInteger}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many Integer</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many Integer</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer" required="true"
   *        extendedMetaData="kind='element' name='manyInteger'"
   * @generated
   */
  List<BigInteger> getManyInteger();

  /**
   * Returns the value of the '<em><b>Many Long</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Long}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many Long</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many Long</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Long" required="true"
   *        extendedMetaData="kind='element' name='manyLong'"
   * @generated
   */
  List<Long> getManyLong();

  /**
   * Returns the value of the '<em><b>Many Short</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Short}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many Short</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many Short</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Short" required="true"
   *        extendedMetaData="kind='element' name='manyShort'"
   * @generated
   */
  List<Short> getManyShort();

  /**
   * Returns the value of the '<em><b>Many String</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many String</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many String</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='manyString'"
   * @generated
   */
  List<String> getManyString();

  /**
   * Returns the value of the '<em><b>Many Char</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Character}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many Char</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many Char</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.test.models.types.MyChar" required="true"
   *        extendedMetaData="kind='element' name='manyChar'"
   * @generated
   */
  List<Character> getManyChar();

  /**
   * Returns the value of the '<em><b>Many Date</b></em>' attribute list.
   * The list contents are of type {@link java.util.Date}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many Date</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many Date</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.test.models.types.MyDate" required="true"
   *        extendedMetaData="kind='element' name='manyDate'"
   * @generated
   */
  List<Date> getManyDate();

  /**
   * Returns the value of the '<em><b>Many Bytes</b></em>' attribute list.
   * The list contents are of type {@link byte}[].
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many Bytes</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many Bytes</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.test.models.types.MyBytes" required="true"
   *        extendedMetaData="kind='element' name='manyBytes'"
   * @generated
   */
  List<byte[]> getManyBytes();

  /**
   * Returns the value of the '<em><b>Many Number</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Number}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many Number</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many Number</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.test.models.types.MyNumber" required="true"
   *        extendedMetaData="kind='element' name='manyNumber'"
   * @generated
   */
  List<Number> getManyNumber();

  /**
   * Returns the value of the '<em><b>Many Object</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Object}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many Object</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many Object</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.test.models.types.MyObject" required="true"
   *        extendedMetaData="kind='element' name='manyObject'"
   * @generated
   */
  List<Object> getManyObject();

  /**
   * Returns the value of the '<em><b>Many Thread</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Thread}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many Thread</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many Thread</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.test.models.types.MyThread" required="true"
   *        extendedMetaData="kind='element' name='manyThread'"
   * @generated
   */
  List<Thread> getManyThread();

} // AThing
