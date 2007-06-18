/**
 * <copyright>
 *
 * Copyright (c) 2003-2006 IBM Corporation and others.
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
 * $Id: EMOFResourceFactoryImpl.java,v 1.5 2007/06/18 17:26:49 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;


public class EMOFResourceFactoryImpl extends ResourceFactoryImpl
{
  protected EMOFExtendedMetaData extendedMetaData;

  public EMOFResourceFactoryImpl()
  {
    super();

    XMLResource.XMLMap xmlMap = new XMLMapImpl();

    xmlMap.add(EcorePackage.eINSTANCE.getEFactory(), createXMLInfo("Factory"));
    xmlMap.add(EcorePackage.eINSTANCE.getEPackage(), createXMLInfo("Package"));
    xmlMap.add(EcorePackage.eINSTANCE.getEAttribute(), createXMLInfo("Property"));
    xmlMap.add(EcorePackage.eINSTANCE.getEReference(), createXMLInfo("Property"));
    xmlMap.add(EcorePackage.eINSTANCE.getEParameter(), createXMLInfo("Parameter"));
    xmlMap.add(EcorePackage.eINSTANCE.getEOperation(), createXMLInfo("Operation"));
    xmlMap.add(EcorePackage.eINSTANCE.getEEnumLiteral(), createXMLInfo("EnumerationLiteral"));
    xmlMap.add(EcorePackage.eINSTANCE.getEDataType(), createXMLInfo("PrimitiveType"));
    xmlMap.add(EcorePackage.eINSTANCE.getEEnum(), createXMLInfo("Enumeration"));
    xmlMap.add(EcorePackage.eINSTANCE.getEClass(), createXMLInfo("Class"));

    xmlMap.add(EcorePackage.eINSTANCE.getEFactory_EPackage(), createXMLInfo("package"));

    xmlMap.add(EcorePackage.eINSTANCE.getEPackage_NsURI(), createXMLInfo("uri"));
    xmlMap.add(EcorePackage.eINSTANCE.getEPackage_ESubpackages(), createXMLInfo("nestedPackage"));
    xmlMap.add(EcorePackage.eINSTANCE.getEPackage_ESuperPackage(), createXMLInfo("nestingPackage"));
    xmlMap.add(EcorePackage.eINSTANCE.getEPackage_EClassifiers(), createXMLInfo("ownedType"));
    xmlMap.add(EcorePackage.eINSTANCE.getEPackage_NsPrefix(), createXMLInfo()); // no property

    xmlMap.add(EcorePackage.eINSTANCE.getETypedElement_EType(), createXMLInfo("type"));
    xmlMap.add(EcorePackage.eINSTANCE.getETypedElement_Ordered(), createXMLInfo("isOrdered"));
    xmlMap.add(EcorePackage.eINSTANCE.getETypedElement_Unique(), createXMLInfo("isUnique"));
    xmlMap.add(EcorePackage.eINSTANCE.getETypedElement_LowerBound(), createXMLInfo("lower"));
    xmlMap.add(EcorePackage.eINSTANCE.getETypedElement_UpperBound(), createXMLInfo("upper"));

    xmlMap.add(EcorePackage.eINSTANCE.getEStructuralFeature_Changeable(), createXMLInfo("isReadOnly")); // boolean not
    xmlMap.add(EcorePackage.eINSTANCE.getEStructuralFeature_DefaultValueLiteral(), createXMLInfo("default"));
    xmlMap.add(EcorePackage.eINSTANCE.getEStructuralFeature_Volatile(), createXMLInfo()); // no property
    xmlMap.add(EcorePackage.eINSTANCE.getEStructuralFeature_Unsettable(), createXMLInfo()); // no property
    xmlMap.add(EcorePackage.eINSTANCE.getEStructuralFeature_Transient(), createXMLInfo()); // no property
    xmlMap.add(EcorePackage.eINSTANCE.getEStructuralFeature_Derived(), createXMLInfo("isDerived"));

    xmlMap.add(EcorePackage.eINSTANCE.getEAttribute_ID(), createXMLInfo("isID"));

    xmlMap.add(EcorePackage.eINSTANCE.getEReference_Containment(), createXMLInfo("isComposite"));
    xmlMap.add(EcorePackage.eINSTANCE.getEReference_EOpposite(), createXMLInfo("opposite"));
    xmlMap.add(EcorePackage.eINSTANCE.getEReference_ResolveProxies(), createXMLInfo()); // no property

    xmlMap.add(EcorePackage.eINSTANCE.getEEnumLiteral_Value(), createXMLInfo()); // no property

    xmlMap.add(EcorePackage.eINSTANCE.getEOperation_EParameters(), createXMLInfo("ownedParameter"));
    xmlMap.add(EcorePackage.eINSTANCE.getEOperation_EExceptions(), createXMLInfo("raisedException"));

    xmlMap.add(EcorePackage.eINSTANCE.getEClassifier_InstanceClassName(), createXMLInfo()); // no property
    xmlMap.add(EcorePackage.eINSTANCE.getEClassifier_InstanceTypeName(), createXMLInfo()); // no property

    xmlMap.add(EcorePackage.eINSTANCE.getEDataType_Serializable(), createXMLInfo()); // no property

    xmlMap.add(EcorePackage.eINSTANCE.getEEnum_ELiterals(), createXMLInfo("ownedLiteral"));

    xmlMap.add(EcorePackage.eINSTANCE.getEClass_Abstract(), createXMLInfo("isAbstract"));
    xmlMap.add(EcorePackage.eINSTANCE.getEClass_EStructuralFeatures(), createXMLInfo("ownedAttribute")); // ownedProperty???
    xmlMap.add(EcorePackage.eINSTANCE.getEClass_EOperations(), createXMLInfo("ownedOperation"));
    xmlMap.add(EcorePackage.eINSTANCE.getEClass_ESuperTypes(), createXMLInfo("superClass"));
    xmlMap.add(EcorePackage.eINSTANCE.getEClass_Interface(), createXMLInfo()); // no property

    extendedMetaData = new EMOFExtendedMetaData(xmlMap);
  }

  @Override
  public Resource createResource(URI uri)
  {
    EMOFResourceImpl result = new EMOFResourceImpl(uri);

    result.setEncoding("UTF-8");

    result.getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
    result.getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

    result.getDefaultSaveOptions().put(XMLResource.OPTION_LINE_WIDTH, new Integer(80));
    result.getDefaultSaveOptions().put(XMIResource.OPTION_USE_XMI_TYPE, Boolean.TRUE);

    return result;
  }

  protected XMLResource.XMLInfo createXMLInfo(String name)
  {
    XMLResource.XMLInfo info = new XMLInfoImpl();
    info.setName(name);
    return info;
  }

  protected XMLResource.XMLInfo createXMLInfo()
  {
    XMLResource.XMLInfo info = new XMLInfoImpl();
    info.setXMLRepresentation(XMLResource.XMLInfo.ELEMENT); // We will use an xmi:Extension element for these
    return info;
  }
}
