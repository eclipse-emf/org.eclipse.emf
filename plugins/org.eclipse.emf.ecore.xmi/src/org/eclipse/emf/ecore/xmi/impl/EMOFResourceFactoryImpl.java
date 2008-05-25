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
 * $Id: EMOFResourceFactoryImpl.java,v 1.7 2008/05/25 17:11:32 emerks Exp $
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

    xmlMap.add(EcorePackage.Literals.EFACTORY, createXMLInfo("Factory"));
    xmlMap.add(EcorePackage.Literals.EPACKAGE, createXMLInfo("Package"));
    xmlMap.add(EcorePackage.Literals.EATTRIBUTE, createXMLInfo("Property"));
    xmlMap.add(EcorePackage.Literals.EREFERENCE, createXMLInfo("Property"));
    xmlMap.add(EcorePackage.Literals.EPARAMETER, createXMLInfo("Parameter"));
    xmlMap.add(EcorePackage.Literals.EOPERATION, createXMLInfo("Operation"));
    xmlMap.add(EcorePackage.Literals.EENUM_LITERAL, createXMLInfo("EnumerationLiteral"));
    xmlMap.add(EcorePackage.Literals.EDATA_TYPE, createXMLInfo("PrimitiveType"));
    xmlMap.add(EcorePackage.Literals.EENUM, createXMLInfo("Enumeration"));
    xmlMap.add(EcorePackage.Literals.ECLASS, createXMLInfo("Class"));

    xmlMap.add(EcorePackage.Literals.EFACTORY__EPACKAGE, createXMLInfo("package"));

    xmlMap.add(EcorePackage.Literals.EPACKAGE__NS_URI, createXMLInfo("uri"));
    xmlMap.add(EcorePackage.Literals.EPACKAGE__ESUBPACKAGES, createXMLInfo("nestedPackage"));
    xmlMap.add(EcorePackage.Literals.EPACKAGE__ESUPER_PACKAGE, createXMLInfo("nestingPackage"));
    xmlMap.add(EcorePackage.Literals.EPACKAGE__ECLASSIFIERS, createXMLInfo("ownedType"));
    xmlMap.add(EcorePackage.Literals.EPACKAGE__NS_PREFIX, createXMLInfo()); // no property

    xmlMap.add(EcorePackage.Literals.ETYPED_ELEMENT__ETYPE, createXMLInfo("type"));
    xmlMap.add(EcorePackage.Literals.ETYPED_ELEMENT__ORDERED, createXMLInfo("isOrdered"));
    xmlMap.add(EcorePackage.Literals.ETYPED_ELEMENT__UNIQUE, createXMLInfo("isUnique"));
    xmlMap.add(EcorePackage.Literals.ETYPED_ELEMENT__LOWER_BOUND, createXMLInfo("lower"));
    xmlMap.add(EcorePackage.Literals.ETYPED_ELEMENT__UPPER_BOUND, createXMLInfo("upper"));

    xmlMap.add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__CHANGEABLE, createXMLInfo("isReadOnly")); // boolean not
    xmlMap.add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__DEFAULT_VALUE_LITERAL, createXMLInfo("default"));
    xmlMap.add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__VOLATILE, createXMLInfo()); // no property
    xmlMap.add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__UNSETTABLE, createXMLInfo()); // no property
    xmlMap.add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__TRANSIENT, createXMLInfo()); // no property
    xmlMap.add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__DERIVED, createXMLInfo("isDerived"));

    xmlMap.add(EcorePackage.Literals.EATTRIBUTE__ID, createXMLInfo("isID"));

    xmlMap.add(EcorePackage.Literals.EREFERENCE__CONTAINMENT, createXMLInfo("isComposite"));
    xmlMap.add(EcorePackage.Literals.EREFERENCE__EOPPOSITE, createXMLInfo("opposite"));
    xmlMap.add(EcorePackage.Literals.EREFERENCE__RESOLVE_PROXIES, createXMLInfo()); // no property

    xmlMap.add(EcorePackage.Literals.EENUM_LITERAL__VALUE, createXMLInfo()); // no property

    xmlMap.add(EcorePackage.Literals.EOPERATION__EPARAMETERS, createXMLInfo("ownedParameter"));
    xmlMap.add(EcorePackage.Literals.EOPERATION__EEXCEPTIONS, createXMLInfo("raisedException"));

    xmlMap.add(EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME, createXMLInfo()); // no property
    xmlMap.add(EcorePackage.Literals.ECLASSIFIER__INSTANCE_TYPE_NAME, createXMLInfo()); // no property

    xmlMap.add(EcorePackage.Literals.EDATA_TYPE__SERIALIZABLE, createXMLInfo()); // no property

    xmlMap.add(EcorePackage.Literals.EENUM__ELITERALS, createXMLInfo("ownedLiteral"));

    xmlMap.add(EcorePackage.Literals.ECLASS__ABSTRACT, createXMLInfo("isAbstract"));
    xmlMap.add(EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES, createXMLInfo("ownedAttribute")); // ownedProperty???
    xmlMap.add(EcorePackage.Literals.ECLASS__EOPERATIONS, createXMLInfo("ownedOperation"));
    xmlMap.add(EcorePackage.Literals.ECLASS__ESUPER_TYPES, createXMLInfo("superClass"));
    xmlMap.add(EcorePackage.Literals.ECLASS__INTERFACE, createXMLInfo()); // no property

    extendedMetaData = new EMOFExtendedMetaData(xmlMap);
  }

  @Override
  public Resource createResource(URI uri)
  {
    EMOFResourceImpl result = new EMOFResourceImpl(uri);

    result.setEncoding("UTF-8");

    result.getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
    result.getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

    result.getDefaultSaveOptions().put(XMLResource.OPTION_LINE_WIDTH, 80);
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
