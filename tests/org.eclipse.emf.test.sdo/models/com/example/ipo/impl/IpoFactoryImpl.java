/**
 * <copyright>
 * </copyright>
 *
 * $Id: IpoFactoryImpl.java,v 1.1 2005/06/08 20:47:30 bportier Exp $
 */
package com.example.ipo.impl;

import com.example.ipo.*;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IpoFactoryImpl extends EFactoryImpl implements IpoFactory {
	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IpoFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case IpoPackage.ADDRESS: return (EObject)createAddress();
			case IpoPackage.DOCUMENT_ROOT: return (EObject)createDocumentRoot();
			case IpoPackage.ITEMS: return (EObject)createItems();
			case IpoPackage.ITEM_TYPE: return (EObject)createItemType();
			case IpoPackage.PURCHASE_ORDER_TYPE: return (EObject)createPurchaseOrderType();
			case IpoPackage.UK_ADDRESS: return (EObject)createUKAddress();
			case IpoPackage.US_ADDRESS: return (EObject)createUSAddress();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case IpoPackage.US_STATE: {
				USState result = USState.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case IpoPackage.POSTCODE:
				return createPostcodeFromString(eDataType, initialValue);
			case IpoPackage.QUANTITY_TYPE:
				return createQuantityTypeFromString(eDataType, initialValue);
			case IpoPackage.SKU:
				return createSKUFromString(eDataType, initialValue);
			case IpoPackage.UK_POSTCODE:
				return createUKPostcodeFromString(eDataType, initialValue);
			case IpoPackage.US_STATE_OBJECT:
				return createUSStateObjectFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case IpoPackage.US_STATE:
				return instanceValue == null ? null : instanceValue.toString();
			case IpoPackage.POSTCODE:
				return convertPostcodeToString(eDataType, instanceValue);
			case IpoPackage.QUANTITY_TYPE:
				return convertQuantityTypeToString(eDataType, instanceValue);
			case IpoPackage.SKU:
				return convertSKUToString(eDataType, instanceValue);
			case IpoPackage.UK_POSTCODE:
				return convertUKPostcodeToString(eDataType, instanceValue);
			case IpoPackage.US_STATE_OBJECT:
				return convertUSStateObjectToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Address createAddress() {
		AddressImpl address = new AddressImpl();
		return address;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Items createItems() {
		ItemsImpl items = new ItemsImpl();
		return items;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ItemType createItemType() {
		ItemTypeImpl itemType = new ItemTypeImpl();
		return itemType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PurchaseOrderType createPurchaseOrderType() {
		PurchaseOrderTypeImpl purchaseOrderType = new PurchaseOrderTypeImpl();
		return purchaseOrderType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UKAddress createUKAddress() {
		UKAddressImpl ukAddress = new UKAddressImpl();
		return ukAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public USAddress createUSAddress() {
		USAddressImpl usAddress = new USAddressImpl();
		return usAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createPostcodeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPostcodeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createQuantityTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getPositiveInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertQuantityTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getPositiveInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createSKUFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSKUToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createUKPostcodeFromString(EDataType eDataType, String initialValue) {
		return (String)IpoFactory.eINSTANCE.createFromString(IpoPackage.eINSTANCE.getPostcode(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUKPostcodeToString(EDataType eDataType, Object instanceValue) {
		return IpoFactory.eINSTANCE.convertToString(IpoPackage.eINSTANCE.getPostcode(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public USState createUSStateObjectFromString(EDataType eDataType, String initialValue) {
		return (USState)IpoFactory.eINSTANCE.createFromString(IpoPackage.eINSTANCE.getUSState(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUSStateObjectToString(EDataType eDataType, Object instanceValue) {
		return IpoFactory.eINSTANCE.convertToString(IpoPackage.eINSTANCE.getUSState(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IpoPackage getIpoPackage() {
		return (IpoPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static IpoPackage getPackage() {
		return IpoPackage.eINSTANCE;
	}

} //IpoFactoryImpl
