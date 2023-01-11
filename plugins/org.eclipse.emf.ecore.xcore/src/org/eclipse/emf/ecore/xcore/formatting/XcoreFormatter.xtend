/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.xcore.formatting

import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.xcore.XAnnotation
import org.eclipse.emf.ecore.xcore.XAttribute
import org.eclipse.emf.ecore.xcore.XClass
import org.eclipse.emf.ecore.xcore.XDataType
import org.eclipse.emf.ecore.xcore.XEnum
import org.eclipse.emf.ecore.xcore.XEnumLiteral
import org.eclipse.emf.ecore.xcore.XGenericType
import org.eclipse.emf.ecore.xcore.XOperation
import org.eclipse.emf.ecore.xcore.XPackage
import org.eclipse.emf.ecore.xcore.XParameter
import org.eclipse.emf.ecore.xcore.XReference
import org.eclipse.emf.ecore.xcore.XTypeParameter
import org.eclipse.xtext.formatting2.IFormattableDocument
import org.eclipse.xtext.formatting2.regionaccess.IEObjectRegion
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegionFinder
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegionsFinder
import org.eclipse.xtext.xbase.formatting2.XbaseFormatter

import static org.eclipse.emf.ecore.xcore.XcorePackage.Literals.*

class XcoreFormatter extends XbaseFormatter
{
  def dispatch void format(XAnnotation xAnnotation, extension IFormattableDocument format)
  {
    xAnnotation.regionFor2.keyword('@').append[noSpace]

    val parentheses = xAnnotation.regionFor2.keywordPairs('(', ')').head
    if (parentheses === null)
    {
      xAnnotation.regionFor2.feature(XANNOTATION__SOURCE).append[newLine]
    }
    else
    {
      parentheses.interior[indent]
      xAnnotation.regionFor2.keyword('(').prepend[noSpace].append[noSpace]
      xAnnotation.regionFor2.keyword(')').prepend[noSpace].append[newLine]

      for (entry : xAnnotation.details)
      {
        val detail = entry as EObject
        detail.regionFor2.keyword('=').prepend[noSpace].append[noSpace]

        val comma = detail.immediatelyFollowing2.keyword(',')
        if (comma !== null)
        {
          comma.prepend[noSpace].append[oneSpace].append[autowrap]
        }
      }
    }
  }

  def protected dispatch void format(XPackage xPackage, extension IFormattableDocument format)
  {
    formatAnnotations(xPackage.annotations, format)

    xPackage.regionFor2.keyword('package').prepend[noSpace]
    xPackage.regionFor2.feature(XNAMED_ELEMENT__NAME).prepend[oneSpace]

    val xImportDirectives = xPackage.importDirectives
    if (!xImportDirectives.empty)
    {
      val first = xImportDirectives.head
      val last = xImportDirectives.last
      for (xImportDirective : xImportDirectives)
      {
        xImportDirective.regionFor2.keyword('import').prepend [
          newLines = if (xImportDirective == first) 2 else 1
        ]
        xImportDirective.format(format)
        if (xImportDirective == last)
        {
          xImportDirective.regionForEObject2.allSemanticRegions.last.append[newLines = 2]
        }
      }
    }
    else
    {
      xPackage.regionFor2.feature(XNAMED_ELEMENT__NAME).append[newLines = 2]
    }

    val xClassifiers = xPackage.classifiers
    for (xClassifier : xClassifiers)
    {
      xClassifier.format(format)
      xClassifier.regionForEObject2.allSemanticRegions.head.prepend[newLines = 2]
    }
  }

  def protected dispatch void format(XEnum xEnum, extension IFormattableDocument format)
  {
    formatAnnotations(xEnum.annotations, format)

    xEnum.regionFor2.keywordPairs('{', '}').head.interior[indent]
    xEnum.regionFor2.keyword('{').append[newLine] // }
    for (xEnumLiteral : xEnum.literals)
    {
      xEnumLiteral.format(format)
      xEnumLiteral.regionForEObject2.allSemanticRegions.last.append[newLine]
    }
  }

  def protected dispatch void format(XEnumLiteral xEnumLiteral, IFormattableDocument format)
  {
    formatAnnotations(xEnumLiteral.annotations, format)
  }

  def protected dispatch void format(XDataType xDataType, extension IFormattableDocument format)
  {
    formatAnnotations(xDataType.annotations, format)

    val leftAngleBracket = xDataType.regionFor2.keyword('<')
    if (leftAngleBracket !== null)
    {
      leftAngleBracket.prepend[noSpace].append[noSpace]
      formatTypeParameters(xDataType.typeParameters, format)
      xDataType.regionFor2.keyword('>').prepend[noSpace].append[oneSpace];
    }
  }

  def protected dispatch void format(XClass xClass, extension IFormattableDocument format)
  {
    formatAnnotations(xClass.annotations, format)

    val leftAngleBracket = xClass.regionFor2.keyword('<')
    if (leftAngleBracket !== null)
    {
      leftAngleBracket.prepend[noSpace].append[noSpace]
      formatTypeParameters(xClass.typeParameters, format)
      xClass.regionFor2.keyword('>').prepend[noSpace].append[oneSpace];
    }

    xClass.regionFor2.keywordPairs('{', '}').head.interior[indent]
    xClass.regionFor2.keyword('{').append[newLine] // }
    for (xMember : xClass.members)
    {
      xMember.format(format)
      xMember.regionForEObject2.allSemanticRegions.last.append[newLine]
    }
  }

  def protected dispatch void format(XReference xReference, extension IFormattableDocument format)
  {
    formatAnnotations(xReference.annotations, format)

    xReference.type.format

    val multiplicity = xReference.regionFor2.feature(XTYPED_ELEMENT__MULTIPLICITY)
    if (multiplicity !== null)
    {
      multiplicity.prepend[noSpace]
    }

    val get = xReference.getBody
    if (get !== null)
    {
      get.regionFor2.keyword('{').prepend[oneSpace] // }
      get.format(format)
    }
  }

  def protected dispatch void format(XAttribute xAttribute, extension IFormattableDocument format)
  {
    formatAnnotations(xAttribute.annotations, format)

    xAttribute.type.format

    val multiplicity = xAttribute.regionFor2.feature(XTYPED_ELEMENT__MULTIPLICITY)
    if (multiplicity !== null)
    {
      multiplicity.prepend[noSpace]
    }

    val get = xAttribute.getBody
    if (get !== null)
    {
      get.regionFor2.keyword('{').prepend[oneSpace] // }
      get.format(format)
    }
  }

  def protected dispatch void format(XOperation xOperation, extension IFormattableDocument format)
  {
    formatAnnotations(xOperation.annotations, format)

    xOperation.type.format

    val multiplicity = xOperation.regionFor2.feature(XTYPED_ELEMENT__MULTIPLICITY)
    if (multiplicity !== null)
    {
      multiplicity.prepend[noSpace]
    }

    val leftAngleBracket = xOperation.regionFor2.keyword('<')
    if (leftAngleBracket !== null)
    {
      leftAngleBracket.prepend[oneSpace].append[noSpace]
      formatTypeParameters(xOperation.typeParameters, format)
      xOperation.regionFor2.keyword('>').prepend[noSpace].append[oneSpace];
    }

    xOperation.regionFor2.keyword('(').prepend[noSpace].append[noSpace]

    val xParameters = xOperation.parameters
    if (!xParameters.empty)
    {
      xOperation.regionFor2.keyword(')').prepend[noSpace]

      for (xParameter : xParameters)
      {
        xParameter.format(format)
      }
    }

    val body = xOperation.body
    if (body !== null)
    {
      body.regionFor2.keyword('{').prepend[oneSpace] // }
      body.format(format)
    }
  }

  def protected dispatch void format(XParameter xParameter, extension IFormattableDocument format)
  {
    formatAnnotations(xParameter.annotations, format)

    val multiplicity = xParameter.regionFor2.feature(XTYPED_ELEMENT__MULTIPLICITY)
    if (multiplicity !== null)
    {
      multiplicity.prepend[noSpace]
    }

    xParameter.type.format
  }

  def protected dispatch void format(XGenericType xGenericType, extension IFormattableDocument format)
  {
    xGenericType.type.format
    val leftAngleBracket = xGenericType.regionFor2.keyword('<')
    if (leftAngleBracket !== null)
    {
      leftAngleBracket.prepend[noSpace].append[noSpace]
      for (XGenericType typeArgument : xGenericType.typeArguments)
      {
        typeArgument.format
        val comma = typeArgument.immediatelyFollowing2.keyword(',')
        if (comma !== null)
        {
          comma.prepend[noSpace].append[oneSpace]
        }
      }

      xGenericType.regionFor2.keyword('>').prepend[noSpace].append[noSpace]
    }

    val upperBound = xGenericType.upperBound
    if (upperBound !== null)
    {
      upperBound.format
      xGenericType.regionFor2.keyword('extends').prepend[oneSpace].append[oneSpace]
    }

    val lowerBound = xGenericType.lowerBound
    if (lowerBound !== null)
    {
      lowerBound.format
      xGenericType.regionFor2.keyword('super').prepend[oneSpace].append[oneSpace]
    }
  }

  def protected dispatch void format(XTypeParameter xTypeParameter, extension IFormattableDocument format)
  {
    formatAnnotations(xTypeParameter.annotations, format)
    for (XGenericType bound : xTypeParameter.bounds)
    {
      bound.format
      val ampersand = bound.immediatelyFollowing2.keyword('&')
      if (ampersand !== null)
      {
        ampersand.prepend[oneSpace].append[oneSpace]
      }
    }
  }

  def protected void formatAnnotations(List<XAnnotation> xAnnotations, IFormattableDocument format)
  {
    for (xAnnotation : xAnnotations)
    {
      format(xAnnotation, format)
    }
  }

  def protected void formatTypeParameters(List<XTypeParameter> xTypeParameters, IFormattableDocument format)
  {
    for (xTypeParameter : xTypeParameters)
    {
      format(xTypeParameter, format)
    }
  }

  def private ISemanticRegionFinder immediatelyFollowing2(EObject semanticElement)
  {
    return textRegionExtensions.immediatelyFollowing(semanticElement);
  }

  def private ISemanticRegionsFinder regionFor2(EObject semanticElement)
  {
    return textRegionExtensions.regionFor(semanticElement);
  }

  def private IEObjectRegion regionForEObject2(EObject semanticElement)
  {
    return textRegionExtensions.regionForEObject(semanticElement);
  }
}
