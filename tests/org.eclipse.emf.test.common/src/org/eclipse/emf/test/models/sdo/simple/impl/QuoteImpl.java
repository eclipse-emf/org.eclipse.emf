/**
 * <copyright>
 * </copyright>
 *
 * $Id: QuoteImpl.java,v 1.1 2007/01/18 15:50:26 marcelop Exp $
 */
package org.eclipse.emf.test.models.sdo.simple.impl;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.sdo.simple.Quote;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Quote</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.impl.QuoteImpl#getSymbol <em>Symbol</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.impl.QuoteImpl#getCompanyName <em>Company Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.impl.QuoteImpl#getPrice <em>Price</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.impl.QuoteImpl#getOpen1 <em>Open1</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.impl.QuoteImpl#getHigh <em>High</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.impl.QuoteImpl#getLow <em>Low</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.impl.QuoteImpl#getVolume <em>Volume</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.impl.QuoteImpl#getChange1 <em>Change1</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.impl.QuoteImpl#getQuotes <em>Quotes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QuoteImpl extends EDataObjectImpl implements Quote
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final long serialVersionUID = 1L;

  /**
   * The default value of the '{@link #getSymbol() <em>Symbol</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSymbol()
   * @generated
   * @ordered
   */
  protected static final String SYMBOL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSymbol() <em>Symbol</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSymbol()
   * @generated
   * @ordered
   */
  protected String symbol = SYMBOL_EDEFAULT;

  /**
   * The default value of the '{@link #getCompanyName() <em>Company Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCompanyName()
   * @generated
   * @ordered
   */
  protected static final String COMPANY_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCompanyName() <em>Company Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCompanyName()
   * @generated
   * @ordered
   */
  protected String companyName = COMPANY_NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getPrice() <em>Price</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrice()
   * @generated
   * @ordered
   */
  protected static final BigDecimal PRICE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPrice() <em>Price</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrice()
   * @generated
   * @ordered
   */
  protected BigDecimal price = PRICE_EDEFAULT;

  /**
   * The default value of the '{@link #getOpen1() <em>Open1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOpen1()
   * @generated
   * @ordered
   */
  protected static final BigDecimal OPEN1_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOpen1() <em>Open1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOpen1()
   * @generated
   * @ordered
   */
  protected BigDecimal open1 = OPEN1_EDEFAULT;

  /**
   * The default value of the '{@link #getHigh() <em>High</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHigh()
   * @generated
   * @ordered
   */
  protected static final BigDecimal HIGH_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getHigh() <em>High</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHigh()
   * @generated
   * @ordered
   */
  protected BigDecimal high = HIGH_EDEFAULT;

  /**
   * The default value of the '{@link #getLow() <em>Low</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLow()
   * @generated
   * @ordered
   */
  protected static final BigDecimal LOW_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLow() <em>Low</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLow()
   * @generated
   * @ordered
   */
  protected BigDecimal low = LOW_EDEFAULT;

  /**
   * The default value of the '{@link #getVolume() <em>Volume</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVolume()
   * @generated
   * @ordered
   */
  protected static final double VOLUME_EDEFAULT = 0.0;

  /**
   * The cached value of the '{@link #getVolume() <em>Volume</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVolume()
   * @generated
   * @ordered
   */
  protected double volume = VOLUME_EDEFAULT;

  /**
   * This is true if the Volume attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean volumeESet = false;

  /**
   * The default value of the '{@link #getChange1() <em>Change1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getChange1()
   * @generated
   * @ordered
   */
  protected static final double CHANGE1_EDEFAULT = 0.0;

  /**
   * The cached value of the '{@link #getChange1() <em>Change1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getChange1()
   * @generated
   * @ordered
   */
  protected double change1 = CHANGE1_EDEFAULT;

  /**
   * This is true if the Change1 attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean change1ESet = false;

  /**
   * The cached value of the '{@link #getQuotes() <em>Quotes</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQuotes()
   * @generated
   * @ordered
   */
  protected EList<Quote> quotes = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected QuoteImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return SimplePackageImpl.Literals.QUOTE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSymbol()
  {
    return symbol;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSymbol(String newSymbol)
  {
    String oldSymbol = symbol;
    symbol = newSymbol;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SimplePackageImpl.QUOTE__SYMBOL, oldSymbol, symbol));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCompanyName()
  {
    return companyName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCompanyName(String newCompanyName)
  {
    String oldCompanyName = companyName;
    companyName = newCompanyName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SimplePackageImpl.QUOTE__COMPANY_NAME, oldCompanyName, companyName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigDecimal getPrice()
  {
    return price;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPrice(BigDecimal newPrice)
  {
    BigDecimal oldPrice = price;
    price = newPrice;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SimplePackageImpl.QUOTE__PRICE, oldPrice, price));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigDecimal getOpen1()
  {
    return open1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOpen1(BigDecimal newOpen1)
  {
    BigDecimal oldOpen1 = open1;
    open1 = newOpen1;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SimplePackageImpl.QUOTE__OPEN1, oldOpen1, open1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigDecimal getHigh()
  {
    return high;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHigh(BigDecimal newHigh)
  {
    BigDecimal oldHigh = high;
    high = newHigh;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SimplePackageImpl.QUOTE__HIGH, oldHigh, high));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigDecimal getLow()
  {
    return low;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLow(BigDecimal newLow)
  {
    BigDecimal oldLow = low;
    low = newLow;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SimplePackageImpl.QUOTE__LOW, oldLow, low));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public double getVolume()
  {
    return volume;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVolume(double newVolume)
  {
    double oldVolume = volume;
    volume = newVolume;
    boolean oldVolumeESet = volumeESet;
    volumeESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SimplePackageImpl.QUOTE__VOLUME, oldVolume, volume, !oldVolumeESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetVolume()
  {
    double oldVolume = volume;
    boolean oldVolumeESet = volumeESet;
    volume = VOLUME_EDEFAULT;
    volumeESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, SimplePackageImpl.QUOTE__VOLUME, oldVolume, VOLUME_EDEFAULT, oldVolumeESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetVolume()
  {
    return volumeESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public double getChange1()
  {
    return change1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setChange1(double newChange1)
  {
    double oldChange1 = change1;
    change1 = newChange1;
    boolean oldChange1ESet = change1ESet;
    change1ESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SimplePackageImpl.QUOTE__CHANGE1, oldChange1, change1, !oldChange1ESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetChange1()
  {
    double oldChange1 = change1;
    boolean oldChange1ESet = change1ESet;
    change1 = CHANGE1_EDEFAULT;
    change1ESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, SimplePackageImpl.QUOTE__CHANGE1, oldChange1, CHANGE1_EDEFAULT, oldChange1ESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetChange1()
  {
    return change1ESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<Quote> getQuotes()
  {
    if (quotes == null)
    {
      quotes = new EObjectContainmentEList<Quote>(Quote.class, this, SimplePackageImpl.QUOTE__QUOTES);
    }
    return quotes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case SimplePackageImpl.QUOTE__QUOTES:
        return ((InternalEList<?>)getQuotes()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case SimplePackageImpl.QUOTE__SYMBOL:
        return getSymbol();
      case SimplePackageImpl.QUOTE__COMPANY_NAME:
        return getCompanyName();
      case SimplePackageImpl.QUOTE__PRICE:
        return getPrice();
      case SimplePackageImpl.QUOTE__OPEN1:
        return getOpen1();
      case SimplePackageImpl.QUOTE__HIGH:
        return getHigh();
      case SimplePackageImpl.QUOTE__LOW:
        return getLow();
      case SimplePackageImpl.QUOTE__VOLUME:
        return new Double(getVolume());
      case SimplePackageImpl.QUOTE__CHANGE1:
        return new Double(getChange1());
      case SimplePackageImpl.QUOTE__QUOTES:
        return getQuotes();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SimplePackageImpl.QUOTE__SYMBOL:
        setSymbol((String)newValue);
        return;
      case SimplePackageImpl.QUOTE__COMPANY_NAME:
        setCompanyName((String)newValue);
        return;
      case SimplePackageImpl.QUOTE__PRICE:
        setPrice((BigDecimal)newValue);
        return;
      case SimplePackageImpl.QUOTE__OPEN1:
        setOpen1((BigDecimal)newValue);
        return;
      case SimplePackageImpl.QUOTE__HIGH:
        setHigh((BigDecimal)newValue);
        return;
      case SimplePackageImpl.QUOTE__LOW:
        setLow((BigDecimal)newValue);
        return;
      case SimplePackageImpl.QUOTE__VOLUME:
        setVolume(((Double)newValue).doubleValue());
        return;
      case SimplePackageImpl.QUOTE__CHANGE1:
        setChange1(((Double)newValue).doubleValue());
        return;
      case SimplePackageImpl.QUOTE__QUOTES:
        getQuotes().clear();
        getQuotes().addAll((Collection<? extends Quote>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case SimplePackageImpl.QUOTE__SYMBOL:
        setSymbol(SYMBOL_EDEFAULT);
        return;
      case SimplePackageImpl.QUOTE__COMPANY_NAME:
        setCompanyName(COMPANY_NAME_EDEFAULT);
        return;
      case SimplePackageImpl.QUOTE__PRICE:
        setPrice(PRICE_EDEFAULT);
        return;
      case SimplePackageImpl.QUOTE__OPEN1:
        setOpen1(OPEN1_EDEFAULT);
        return;
      case SimplePackageImpl.QUOTE__HIGH:
        setHigh(HIGH_EDEFAULT);
        return;
      case SimplePackageImpl.QUOTE__LOW:
        setLow(LOW_EDEFAULT);
        return;
      case SimplePackageImpl.QUOTE__VOLUME:
        unsetVolume();
        return;
      case SimplePackageImpl.QUOTE__CHANGE1:
        unsetChange1();
        return;
      case SimplePackageImpl.QUOTE__QUOTES:
        getQuotes().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case SimplePackageImpl.QUOTE__SYMBOL:
        return SYMBOL_EDEFAULT == null ? symbol != null : !SYMBOL_EDEFAULT.equals(symbol);
      case SimplePackageImpl.QUOTE__COMPANY_NAME:
        return COMPANY_NAME_EDEFAULT == null ? companyName != null : !COMPANY_NAME_EDEFAULT.equals(companyName);
      case SimplePackageImpl.QUOTE__PRICE:
        return PRICE_EDEFAULT == null ? price != null : !PRICE_EDEFAULT.equals(price);
      case SimplePackageImpl.QUOTE__OPEN1:
        return OPEN1_EDEFAULT == null ? open1 != null : !OPEN1_EDEFAULT.equals(open1);
      case SimplePackageImpl.QUOTE__HIGH:
        return HIGH_EDEFAULT == null ? high != null : !HIGH_EDEFAULT.equals(high);
      case SimplePackageImpl.QUOTE__LOW:
        return LOW_EDEFAULT == null ? low != null : !LOW_EDEFAULT.equals(low);
      case SimplePackageImpl.QUOTE__VOLUME:
        return isSetVolume();
      case SimplePackageImpl.QUOTE__CHANGE1:
        return isSetChange1();
      case SimplePackageImpl.QUOTE__QUOTES:
        return quotes != null && !quotes.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (symbol: ");
    result.append(symbol);
    result.append(", companyName: ");
    result.append(companyName);
    result.append(", price: ");
    result.append(price);
    result.append(", open1: ");
    result.append(open1);
    result.append(", high: ");
    result.append(high);
    result.append(", low: ");
    result.append(low);
    result.append(", volume: ");
    if (volumeESet) result.append(volume); else result.append("<unset>");
    result.append(", change1: ");
    if (change1ESet) result.append(change1); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

} //QuoteImpl
