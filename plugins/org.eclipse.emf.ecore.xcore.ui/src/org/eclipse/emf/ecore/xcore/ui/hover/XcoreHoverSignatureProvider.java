/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.hover;


import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.common.types.JvmAnyTypeReference;
import org.eclipse.xtext.common.types.JvmConstructor;
import org.eclipse.xtext.common.types.JvmEnumerationType;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.ui.hover.XbaseDeclarativeHoverSignatureProvider;

import com.google.inject.Inject;


public class XcoreHoverSignatureProvider extends XbaseDeclarativeHoverSignatureProvider
{
  @Inject
  private IQualifiedNameProvider nameProvider;

  @Inject
  private IQualifiedNameConverter nameConverter;

  @Inject
  private XcoreMapper mapper;

  protected String _signature(XNamedElement xNamedElement, boolean typeAtEnd)
  {
    QualifiedName qualifiedName = nameProvider.getFullyQualifiedName(xNamedElement);
    String name = nameConverter.toString(qualifiedName);
    if (xNamedElement instanceof XAnnotationDirective)
    {
      return "annotation \"" + ((XAnnotationDirective)xNamedElement).getSourceURI() + "\" as " + name;
    }
    else
    {
      return name;
    }
  }

  protected String _signature(GenBase genBase, boolean typeAtEnd)
  {
    QualifiedName qualifiedName = 
      genBase instanceof GenFeature ?
        nameProvider.getFullyQualifiedName(genBase.eContainer()).append(((GenFeature)genBase).getName()) : 
        nameProvider.getFullyQualifiedName(genBase);
    return nameConverter.toString(qualifiedName);
  }

  protected String _signature(EModelElement eModelElement, boolean typeAtEnd)
  {
    QualifiedName qualifiedName = nameProvider.getFullyQualifiedName(eModelElement);
    return nameConverter.toString(qualifiedName);
  }

  @Override
  protected String _signature(JvmGenericType jvmGenericType, boolean typeAtEnd)
  {
    return jvmGenericType.getQualifiedName() + hoverUiStrings.typeParameters(jvmGenericType.getTypeParameters());
  }

  @Override
  protected String _signature(JvmEnumerationType jvmEnumerationType, boolean typeAtEnd)
  {
    return jvmEnumerationType.getQualifiedName();
  }

  @Override
  protected String _signature(JvmOperation jvmOperation, boolean typeAtEnd)
  {
    JvmTypeReference returnType = jvmOperation.getReturnType();
    String returnTypeString =
        returnType == null ?
           "void" :
           returnType instanceof JvmAnyTypeReference ?
             "Object" :
             returnType.getSimpleName();

    String signature = jvmOperation.getQualifiedName() + hoverUiStrings.parameters(jvmOperation) + getThrowsDeclaration(jvmOperation);
    String typeParameter = uiStrings.typeParameters(jvmOperation.getTypeParameters());
    return
       typeParameter != null && typeParameter.length() > 0 ?
         typeAtEnd ?
           signature + " " + typeParameter + " : " + returnTypeString :
           typeParameter + " " + returnTypeString + " " + signature :
        typeAtEnd ?
          signature + " : " + returnTypeString :
          returnTypeString + " " + signature;
  }

  @Override
  protected String _signature(JvmField jvmField, boolean typeAtEnd)
  {
    JvmTypeReference type = jvmField.getType();
    String fieldName = jvmField.getQualifiedName();
    if (type != null)
    {
      String fieldType = type.getSimpleName();
      return
        typeAtEnd ?
          fieldName + " : " + fieldType :
          fieldType + " " + fieldName;
    }
    return fieldName;
  }

  @Override
  protected String _signature(JvmConstructor contructor, boolean typeAtEnd)
  {
    return super._signature(contructor, typeAtEnd);
  }

  @Override
  protected String _signature(JvmFormalParameter parameter, boolean typeAtEnd)
  {
    return super._signature(parameter, typeAtEnd);
  }

  @Override
  protected String _signature(JvmTypeParameter parameter, boolean typeAtEnd)
  {
    return super._signature(parameter, typeAtEnd);
  }
}
