/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EMOFSaveImpl.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLHelper;


public class EMOFSaveImpl extends XMISaveImpl 
{
  public EMOFSaveImpl(XMLHelper helper)
  {
    super(helper);
    idAttributeName = XMI_ID_NS;
  }

  protected void saveTypeAttribute(EClass eClass)
  {
    if (eClass != EcorePackage.eINSTANCE.getEAttribute() && eClass != EcorePackage.eINSTANCE.getEReference())
    {
      super.saveTypeAttribute(eClass);
    }
  }

  protected void saveDataTypeElementSingle(EObject o, EStructuralFeature f)
  {
    if (f == EcorePackage.eINSTANCE.getEPackage_NsPrefix() ||
        f == EcorePackage.eINSTANCE.getEStructuralFeature_Unsettable() ||
        f == EcorePackage.eINSTANCE.getEStructuralFeature_Transient() ||
        f == EcorePackage.eINSTANCE.getEStructuralFeature_Volatile() ||
        f == EcorePackage.eINSTANCE.getEEnumLiteral_Value() ||
        f == EcorePackage.eINSTANCE.getEReference_ResolveProxies() ||
        f == EcorePackage.eINSTANCE.getEClassifier_InstanceClassName() ||
        f == EcorePackage.eINSTANCE.getEDataType_Serializable() ||
        f == EcorePackage.eINSTANCE.getEClass_Interface())
    {
      if (f == EcorePackage.eINSTANCE.getEStructuralFeature_Unsettable() && 
          o.eIsSet(EcorePackage.eINSTANCE.getEStructuralFeature_Volatile())) return;
      if (f == EcorePackage.eINSTANCE.getEStructuralFeature_Transient() && 
          (o.eIsSet(EcorePackage.eINSTANCE.getEStructuralFeature_Unsettable()) ||
           o.eIsSet(EcorePackage.eINSTANCE.getEStructuralFeature_Volatile()))) return;
      if (f == EcorePackage.eINSTANCE.getEReference_ResolveProxies() && 
          (o.eIsSet(EcorePackage.eINSTANCE.getEStructuralFeature_Transient()) || 
           o.eIsSet(EcorePackage.eINSTANCE.getEStructuralFeature_Unsettable()) ||
           o.eIsSet(EcorePackage.eINSTANCE.getEStructuralFeature_Volatile()))) return;
      if (f == EcorePackage.eINSTANCE.getEDataType_Serializable() && 
          o.eIsSet(EcorePackage.eINSTANCE.getEClassifier_InstanceClassName())) return;
      if (f == EcorePackage.eINSTANCE.getEClass_Interface() && 
          o.eIsSet(EcorePackage.eINSTANCE.getEClassifier_InstanceClassName())) return;

      doc.startElement(EMOFExtendedMetaData.XMI_EXTENSION_ELEMENT);
      doc.addAttribute(EMOFExtendedMetaData.XMI_EXTENDER_ATTRIBUTE, EcorePackage.eNS_URI);
      saveExtensionFeature(o, f);
      if (f == EcorePackage.eINSTANCE.getEClassifier_InstanceClassName())
      {
        if (o instanceof EDataType)
        {
          if (o.eIsSet(EcorePackage.eINSTANCE.getEDataType_Serializable()))
          {
            saveExtensionFeature(o, EcorePackage.eINSTANCE.getEDataType_Serializable());
          }
        }
        else if (o instanceof EClass)
        {
          if (o.eIsSet(EcorePackage.eINSTANCE.getEClass_Interface()))
          {
            saveExtensionFeature(o, EcorePackage.eINSTANCE.getEClass_Interface());
          }
        }
      }
      else if (f == EcorePackage.eINSTANCE.getEStructuralFeature_Transient())
      {
        if (o instanceof EReference && o.eIsSet(EcorePackage.eINSTANCE.getEReference_ResolveProxies()))
        {
          saveExtensionFeature(o, EcorePackage.eINSTANCE.getEReference_ResolveProxies());
        }
      }
      else if (f == EcorePackage.eINSTANCE.getEStructuralFeature_Unsettable())
      { 
        if (o.eIsSet(EcorePackage.eINSTANCE.getEStructuralFeature_Transient()))
        {
          saveExtensionFeature(o, EcorePackage.eINSTANCE.getEStructuralFeature_Transient());
        }
        if (o instanceof EReference && o.eIsSet(EcorePackage.eINSTANCE.getEReference_ResolveProxies()))
        {
          saveExtensionFeature(o, EcorePackage.eINSTANCE.getEReference_ResolveProxies());
        }
      }
      else if (f == EcorePackage.eINSTANCE.getEStructuralFeature_Volatile())
      { 
        if (f == EcorePackage.eINSTANCE.getEStructuralFeature_Unsettable())
        {
          saveExtensionFeature(o, EcorePackage.eINSTANCE.getEStructuralFeature_Unsettable());
        }
        if (o.eIsSet(EcorePackage.eINSTANCE.getEStructuralFeature_Transient()))
        {
          saveExtensionFeature(o, EcorePackage.eINSTANCE.getEStructuralFeature_Transient());
        }
        if (o instanceof EReference && o.eIsSet(EcorePackage.eINSTANCE.getEReference_ResolveProxies()))
        {
          saveExtensionFeature(o, EcorePackage.eINSTANCE.getEReference_ResolveProxies());
        }
      }
      doc.endElement();
    }
    else
    {
      super.saveDataTypeElementSingle(o, f);
    }
  }

  protected void saveExtensionFeature(EObject o, EStructuralFeature f)
  {
    doc.startElement(f.getName());
    EDataType eDataType = (EDataType)f.getEType();
    doc.endContentElement(EcoreFactory.eINSTANCE.convertToString(eDataType, o.eGet(f)));
  }

  protected void saveContainedMany(EObject o, EStructuralFeature f)
  {
    if (f == EcorePackage.eINSTANCE.getEModelElement_EAnnotations())
    {
      doc.startElement(EMOFExtendedMetaData.XMI_EXTENSION_ELEMENT);
      doc.addAttribute(EMOFExtendedMetaData.XMI_EXTENDER_ATTRIBUTE, EcorePackage.eNS_URI);
      super.saveContainedMany(o, f);
      doc.endElement();
    }
    else
    {
      super.saveContainedMany(o, f);
    }
  }

  public Object writeTopObjects(List contents)
  {
    doc.startElement(XMI_TAG_NS);
    Object mark = doc.mark();

    for (int i = 0, size = contents.size(); i < size; i++)
    {
      EObject top = (EObject)contents.get(i);
      EClass eClass = top.eClass();
      if (eClass == EcorePackage.eINSTANCE.getEAnnotation())
      {
        EAnnotation annotation = (EAnnotation)top;
        if (!annotation.getSource().equals(EMOFExtendedMetaData.EMOF_PACKAGE_NS_URI)) continue;

        doc.startElement(EMOFExtendedMetaData.EMOF_TAG);
        doc.addAttribute(idAttributeName, helper.getID(annotation));
        doc.addAttribute(EMOFExtendedMetaData.EMOF_TAG_NAME, (String)annotation.getDetails().get(EMOFExtendedMetaData.EMOF_TAG_NAME));
        doc.addAttribute(EMOFExtendedMetaData.EMOF_TAG_VALUE, (String)annotation.getDetails().get(EMOFExtendedMetaData.EMOF_TAG_VALUE));

        InternalEList values = (InternalEList)annotation.getReferences();
        if (!values.isEmpty())
        {
          if (sameDocMany(annotation, EcorePackage.eINSTANCE.getEAnnotation_References()) == CROSS_DOC)
          {
            for (Iterator iter = values.basicIterator(); iter.hasNext(); )
            {
              EObject value = (EObject)iter.next();
              doc.startElement(EMOFExtendedMetaData.EMOF_TAG_ELEMENT);
              doc.addAttribute(XMIResource.HREF, helper.getHREF(value));
              doc.endEmptyElement();
            }
          }
          else
          {
            StringBuffer ids = new StringBuffer();
            for (Iterator iter = values.basicIterator();; )
            {
              EObject value = (EObject)iter.next();
              ids.append(helper.getIDREF(value));
              if (!iter.hasNext()) break;
              ids.append(" ");
            }
            doc.addAttribute(EMOFExtendedMetaData.EMOF_TAG_ELEMENT, ids.toString());
          }
        }
        doc.endElement();
      }
      else
      {
        String name = helper.getQName(eClass);
        doc.startElement(name);
        saveElementID(top);
      }
    }

    doc.endElement();
    return mark;
  }
}
