/**
 * <copyright>
 * </copyright>
 *
 * $Id: AThingImpl.java,v 1.1 2004/05/12 22:05:58 marcelop Exp $
 */
package org.eclipse.emf.test.core.sdo.types.model.types.impl;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.test.core.sdo.types.model.types.AThing;
import org.eclipse.emf.test.core.sdo.types.model.types.TypesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AThing</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.core.sdo.types.model.types.impl.AThingImpl#isABoolean <em>ABoolean</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.sdo.types.model.types.impl.AThingImpl#getAByte <em>AByte</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.sdo.types.model.types.impl.AThingImpl#getADecimal <em>ADecimal</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.sdo.types.model.types.impl.AThingImpl#getAFloat <em>AFloat</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.sdo.types.model.types.impl.AThingImpl#getADouble <em>ADouble</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.sdo.types.model.types.impl.AThingImpl#getAInt <em>AInt</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.sdo.types.model.types.impl.AThingImpl#getAInteger <em>AInteger</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.sdo.types.model.types.impl.AThingImpl#getALong <em>ALong</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.sdo.types.model.types.impl.AThingImpl#getAShort <em>AShort</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.sdo.types.model.types.impl.AThingImpl#getAString <em>AString</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.sdo.types.model.types.impl.AThingImpl#getAChar <em>AChar</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.sdo.types.model.types.impl.AThingImpl#getADate <em>ADate</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.sdo.types.model.types.impl.AThingImpl#getABytes <em>ABytes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AThingImpl extends EDataObjectImpl implements AThing
{
  /**
   * The default value of the '{@link #isABoolean() <em>ABoolean</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isABoolean()
   * @generated
   * @ordered
   */
  protected static final boolean ABOOLEAN_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isABoolean() <em>ABoolean</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isABoolean()
   * @generated
   * @ordered
   */
  protected boolean aBoolean = ABOOLEAN_EDEFAULT;

  /**
   * This is true if the ABoolean attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean aBooleanESet = false;

  /**
   * The default value of the '{@link #getAByte() <em>AByte</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAByte()
   * @generated
   * @ordered
   */
  protected static final byte ABYTE_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getAByte() <em>AByte</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAByte()
   * @generated
   * @ordered
   */
  protected byte aByte = ABYTE_EDEFAULT;

  /**
   * This is true if the AByte attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean aByteESet = false;

  /**
   * The default value of the '{@link #getADecimal() <em>ADecimal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getADecimal()
   * @generated
   * @ordered
   */
  protected static final BigDecimal ADECIMAL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getADecimal() <em>ADecimal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getADecimal()
   * @generated
   * @ordered
   */
  protected BigDecimal aDecimal = ADECIMAL_EDEFAULT;

  /**
   * The default value of the '{@link #getAFloat() <em>AFloat</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAFloat()
   * @generated
   * @ordered
   */
  protected static final float AFLOAT_EDEFAULT = 0.0F;

  /**
   * The cached value of the '{@link #getAFloat() <em>AFloat</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAFloat()
   * @generated
   * @ordered
   */
  protected float aFloat = AFLOAT_EDEFAULT;

  /**
   * This is true if the AFloat attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean aFloatESet = false;

  /**
   * The default value of the '{@link #getADouble() <em>ADouble</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getADouble()
   * @generated
   * @ordered
   */
  protected static final double ADOUBLE_EDEFAULT = 0.0;

  /**
   * The cached value of the '{@link #getADouble() <em>ADouble</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getADouble()
   * @generated
   * @ordered
   */
  protected double aDouble = ADOUBLE_EDEFAULT;

  /**
   * This is true if the ADouble attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean aDoubleESet = false;

  /**
   * The default value of the '{@link #getAInt() <em>AInt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAInt()
   * @generated
   * @ordered
   */
  protected static final int AINT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getAInt() <em>AInt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAInt()
   * @generated
   * @ordered
   */
  protected int aInt = AINT_EDEFAULT;

  /**
   * This is true if the AInt attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean aIntESet = false;

  /**
   * The default value of the '{@link #getAInteger() <em>AInteger</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAInteger()
   * @generated
   * @ordered
   */
  protected static final BigInteger AINTEGER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAInteger() <em>AInteger</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAInteger()
   * @generated
   * @ordered
   */
  protected BigInteger aInteger = AINTEGER_EDEFAULT;

  /**
   * The default value of the '{@link #getALong() <em>ALong</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getALong()
   * @generated
   * @ordered
   */
  protected static final long ALONG_EDEFAULT = 0L;

  /**
   * The cached value of the '{@link #getALong() <em>ALong</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getALong()
   * @generated
   * @ordered
   */
  protected long aLong = ALONG_EDEFAULT;

  /**
   * This is true if the ALong attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean aLongESet = false;

  /**
   * The default value of the '{@link #getAShort() <em>AShort</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAShort()
   * @generated
   * @ordered
   */
  protected static final short ASHORT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getAShort() <em>AShort</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAShort()
   * @generated
   * @ordered
   */
  protected short aShort = ASHORT_EDEFAULT;

  /**
   * This is true if the AShort attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean aShortESet = false;

  /**
   * The default value of the '{@link #getAString() <em>AString</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAString()
   * @generated
   * @ordered
   */
  protected static final String ASTRING_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAString() <em>AString</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAString()
   * @generated
   * @ordered
   */
  protected String aString = ASTRING_EDEFAULT;

  /**
   * The default value of the '{@link #getAChar() <em>AChar</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAChar()
   * @generated
   * @ordered
   */
  protected static final char ACHAR_EDEFAULT = '\u0000';

  /**
   * The cached value of the '{@link #getAChar() <em>AChar</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAChar()
   * @generated
   * @ordered
   */
  protected char aChar = ACHAR_EDEFAULT;

  /**
   * This is true if the AChar attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean aCharESet = false;

  /**
   * The default value of the '{@link #getADate() <em>ADate</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getADate()
   * @generated
   * @ordered
   */
  protected static final Date ADATE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getADate() <em>ADate</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getADate()
   * @generated
   * @ordered
   */
  protected Date aDate = ADATE_EDEFAULT;

  /**
   * The default value of the '{@link #getABytes() <em>ABytes</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getABytes()
   * @generated
   * @ordered
   */
  protected static final byte[] ABYTES_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getABytes() <em>ABytes</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getABytes()
   * @generated
   * @ordered
   */
  protected byte[] aBytes = ABYTES_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AThingImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return TypesPackage.eINSTANCE.getAThing();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isABoolean()
  {
    return aBoolean;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setABoolean(boolean newABoolean)
  {
    boolean oldABoolean = aBoolean;
    aBoolean = newABoolean;
    boolean oldABooleanESet = aBooleanESet;
    aBooleanESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ATHING__ABOOLEAN, oldABoolean, aBoolean, !oldABooleanESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetABoolean()
  {
    boolean oldABoolean = aBoolean;
    boolean oldABooleanESet = aBooleanESet;
    aBoolean = ABOOLEAN_EDEFAULT;
    aBooleanESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, TypesPackage.ATHING__ABOOLEAN, oldABoolean, ABOOLEAN_EDEFAULT, oldABooleanESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetABoolean()
  {
    return aBooleanESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public byte getAByte()
  {
    return aByte;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAByte(byte newAByte)
  {
    byte oldAByte = aByte;
    aByte = newAByte;
    boolean oldAByteESet = aByteESet;
    aByteESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ATHING__ABYTE, oldAByte, aByte, !oldAByteESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetAByte()
  {
    byte oldAByte = aByte;
    boolean oldAByteESet = aByteESet;
    aByte = ABYTE_EDEFAULT;
    aByteESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, TypesPackage.ATHING__ABYTE, oldAByte, ABYTE_EDEFAULT, oldAByteESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetAByte()
  {
    return aByteESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigDecimal getADecimal()
  {
    return aDecimal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setADecimal(BigDecimal newADecimal)
  {
    BigDecimal oldADecimal = aDecimal;
    aDecimal = newADecimal;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ATHING__ADECIMAL, oldADecimal, aDecimal));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Date getADate()
  {
    return aDate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setADate(Date newADate)
  {
    Date oldADate = aDate;
    aDate = newADate;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ATHING__ADATE, oldADate, aDate));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public byte[] getABytes()
  {
    return aBytes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setABytes(byte[] newABytes)
  {
    byte[] oldABytes = aBytes;
    aBytes = newABytes;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ATHING__ABYTES, oldABytes, aBytes));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public float getAFloat()
  {
    return aFloat;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAFloat(float newAFloat)
  {
    float oldAFloat = aFloat;
    aFloat = newAFloat;
    boolean oldAFloatESet = aFloatESet;
    aFloatESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ATHING__AFLOAT, oldAFloat, aFloat, !oldAFloatESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetAFloat()
  {
    float oldAFloat = aFloat;
    boolean oldAFloatESet = aFloatESet;
    aFloat = AFLOAT_EDEFAULT;
    aFloatESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, TypesPackage.ATHING__AFLOAT, oldAFloat, AFLOAT_EDEFAULT, oldAFloatESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetAFloat()
  {
    return aFloatESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public double getADouble()
  {
    return aDouble;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setADouble(double newADouble)
  {
    double oldADouble = aDouble;
    aDouble = newADouble;
    boolean oldADoubleESet = aDoubleESet;
    aDoubleESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ATHING__ADOUBLE, oldADouble, aDouble, !oldADoubleESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetADouble()
  {
    double oldADouble = aDouble;
    boolean oldADoubleESet = aDoubleESet;
    aDouble = ADOUBLE_EDEFAULT;
    aDoubleESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, TypesPackage.ATHING__ADOUBLE, oldADouble, ADOUBLE_EDEFAULT, oldADoubleESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetADouble()
  {
    return aDoubleESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getAInt()
  {
    return aInt;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAInt(int newAInt)
  {
    int oldAInt = aInt;
    aInt = newAInt;
    boolean oldAIntESet = aIntESet;
    aIntESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ATHING__AINT, oldAInt, aInt, !oldAIntESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetAInt()
  {
    int oldAInt = aInt;
    boolean oldAIntESet = aIntESet;
    aInt = AINT_EDEFAULT;
    aIntESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, TypesPackage.ATHING__AINT, oldAInt, AINT_EDEFAULT, oldAIntESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetAInt()
  {
    return aIntESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger getAInteger()
  {
    return aInteger;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAInteger(BigInteger newAInteger)
  {
    BigInteger oldAInteger = aInteger;
    aInteger = newAInteger;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ATHING__AINTEGER, oldAInteger, aInteger));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public long getALong()
  {
    return aLong;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setALong(long newALong)
  {
    long oldALong = aLong;
    aLong = newALong;
    boolean oldALongESet = aLongESet;
    aLongESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ATHING__ALONG, oldALong, aLong, !oldALongESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetALong()
  {
    long oldALong = aLong;
    boolean oldALongESet = aLongESet;
    aLong = ALONG_EDEFAULT;
    aLongESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, TypesPackage.ATHING__ALONG, oldALong, ALONG_EDEFAULT, oldALongESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetALong()
  {
    return aLongESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public short getAShort()
  {
    return aShort;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAShort(short newAShort)
  {
    short oldAShort = aShort;
    aShort = newAShort;
    boolean oldAShortESet = aShortESet;
    aShortESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ATHING__ASHORT, oldAShort, aShort, !oldAShortESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetAShort()
  {
    short oldAShort = aShort;
    boolean oldAShortESet = aShortESet;
    aShort = ASHORT_EDEFAULT;
    aShortESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, TypesPackage.ATHING__ASHORT, oldAShort, ASHORT_EDEFAULT, oldAShortESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetAShort()
  {
    return aShortESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAString()
  {
    return aString;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAString(String newAString)
  {
    String oldAString = aString;
    aString = newAString;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ATHING__ASTRING, oldAString, aString));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public char getAChar()
  {
    return aChar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAChar(char newAChar)
  {
    char oldAChar = aChar;
    aChar = newAChar;
    boolean oldACharESet = aCharESet;
    aCharESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ATHING__ACHAR, oldAChar, aChar, !oldACharESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetAChar()
  {
    char oldAChar = aChar;
    boolean oldACharESet = aCharESet;
    aChar = ACHAR_EDEFAULT;
    aCharESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, TypesPackage.ATHING__ACHAR, oldAChar, ACHAR_EDEFAULT, oldACharESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetAChar()
  {
    return aCharESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case TypesPackage.ATHING__ABOOLEAN:
        return isABoolean() ? Boolean.TRUE : Boolean.FALSE;
      case TypesPackage.ATHING__ABYTE:
        return new Byte(getAByte());
      case TypesPackage.ATHING__ADECIMAL:
        return getADecimal();
      case TypesPackage.ATHING__AFLOAT:
        return new Float(getAFloat());
      case TypesPackage.ATHING__ADOUBLE:
        return new Double(getADouble());
      case TypesPackage.ATHING__AINT:
        return new Integer(getAInt());
      case TypesPackage.ATHING__AINTEGER:
        return getAInteger();
      case TypesPackage.ATHING__ALONG:
        return new Long(getALong());
      case TypesPackage.ATHING__ASHORT:
        return new Short(getAShort());
      case TypesPackage.ATHING__ASTRING:
        return getAString();
      case TypesPackage.ATHING__ACHAR:
        return new Character(getAChar());
      case TypesPackage.ATHING__ADATE:
        return getADate();
      case TypesPackage.ATHING__ABYTES:
        return getABytes();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case TypesPackage.ATHING__ABOOLEAN:
        setABoolean(((Boolean)newValue).booleanValue());
        return;
      case TypesPackage.ATHING__ABYTE:
        setAByte(((Byte)newValue).byteValue());
        return;
      case TypesPackage.ATHING__ADECIMAL:
        setADecimal((BigDecimal)newValue);
        return;
      case TypesPackage.ATHING__AFLOAT:
        setAFloat(((Float)newValue).floatValue());
        return;
      case TypesPackage.ATHING__ADOUBLE:
        setADouble(((Double)newValue).doubleValue());
        return;
      case TypesPackage.ATHING__AINT:
        setAInt(((Integer)newValue).intValue());
        return;
      case TypesPackage.ATHING__AINTEGER:
        setAInteger((BigInteger)newValue);
        return;
      case TypesPackage.ATHING__ALONG:
        setALong(((Long)newValue).longValue());
        return;
      case TypesPackage.ATHING__ASHORT:
        setAShort(((Short)newValue).shortValue());
        return;
      case TypesPackage.ATHING__ASTRING:
        setAString((String)newValue);
        return;
      case TypesPackage.ATHING__ACHAR:
        setAChar(((Character)newValue).charValue());
        return;
      case TypesPackage.ATHING__ADATE:
        setADate((Date)newValue);
        return;
      case TypesPackage.ATHING__ABYTES:
        setABytes((byte[])newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case TypesPackage.ATHING__ABOOLEAN:
        unsetABoolean();
        return;
      case TypesPackage.ATHING__ABYTE:
        unsetAByte();
        return;
      case TypesPackage.ATHING__ADECIMAL:
        setADecimal(ADECIMAL_EDEFAULT);
        return;
      case TypesPackage.ATHING__AFLOAT:
        unsetAFloat();
        return;
      case TypesPackage.ATHING__ADOUBLE:
        unsetADouble();
        return;
      case TypesPackage.ATHING__AINT:
        unsetAInt();
        return;
      case TypesPackage.ATHING__AINTEGER:
        setAInteger(AINTEGER_EDEFAULT);
        return;
      case TypesPackage.ATHING__ALONG:
        unsetALong();
        return;
      case TypesPackage.ATHING__ASHORT:
        unsetAShort();
        return;
      case TypesPackage.ATHING__ASTRING:
        setAString(ASTRING_EDEFAULT);
        return;
      case TypesPackage.ATHING__ACHAR:
        unsetAChar();
        return;
      case TypesPackage.ATHING__ADATE:
        setADate(ADATE_EDEFAULT);
        return;
      case TypesPackage.ATHING__ABYTES:
        setABytes(ABYTES_EDEFAULT);
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case TypesPackage.ATHING__ABOOLEAN:
        return isSetABoolean();
      case TypesPackage.ATHING__ABYTE:
        return isSetAByte();
      case TypesPackage.ATHING__ADECIMAL:
        return ADECIMAL_EDEFAULT == null ? aDecimal != null : !ADECIMAL_EDEFAULT.equals(aDecimal);
      case TypesPackage.ATHING__AFLOAT:
        return isSetAFloat();
      case TypesPackage.ATHING__ADOUBLE:
        return isSetADouble();
      case TypesPackage.ATHING__AINT:
        return isSetAInt();
      case TypesPackage.ATHING__AINTEGER:
        return AINTEGER_EDEFAULT == null ? aInteger != null : !AINTEGER_EDEFAULT.equals(aInteger);
      case TypesPackage.ATHING__ALONG:
        return isSetALong();
      case TypesPackage.ATHING__ASHORT:
        return isSetAShort();
      case TypesPackage.ATHING__ASTRING:
        return ASTRING_EDEFAULT == null ? aString != null : !ASTRING_EDEFAULT.equals(aString);
      case TypesPackage.ATHING__ACHAR:
        return isSetAChar();
      case TypesPackage.ATHING__ADATE:
        return ADATE_EDEFAULT == null ? aDate != null : !ADATE_EDEFAULT.equals(aDate);
      case TypesPackage.ATHING__ABYTES:
        return ABYTES_EDEFAULT == null ? aBytes != null : !ABYTES_EDEFAULT.equals(aBytes);
    }
    return eDynamicIsSet(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (aBoolean: ");
    if (aBooleanESet) result.append(aBoolean); else result.append("<unset>");
    result.append(", aByte: ");
    if (aByteESet) result.append(aByte); else result.append("<unset>");
    result.append(", aDecimal: ");
    result.append(aDecimal);
    result.append(", aFloat: ");
    if (aFloatESet) result.append(aFloat); else result.append("<unset>");
    result.append(", aDouble: ");
    if (aDoubleESet) result.append(aDouble); else result.append("<unset>");
    result.append(", aInt: ");
    if (aIntESet) result.append(aInt); else result.append("<unset>");
    result.append(", aInteger: ");
    result.append(aInteger);
    result.append(", aLong: ");
    if (aLongESet) result.append(aLong); else result.append("<unset>");
    result.append(", aShort: ");
    if (aShortESet) result.append(aShort); else result.append("<unset>");
    result.append(", aString: ");
    result.append(aString);
    result.append(", aChar: ");
    if (aCharESet) result.append(aChar); else result.append("<unset>");
    result.append(", aDate: ");
    result.append(aDate);
    result.append(", aBytes: ");
    result.append(aBytes);
    result.append(')');
    return result.toString();
  }

} //AThingImpl
