/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.formatting

import org.eclipse.xtext.xbase.formatting.XbaseFormatter2
import org.eclipse.xtext.xbase.formatting.FormattableDocument
import org.eclipse.emf.ecore.xcore.XPackage
import com.google.inject.Inject
import org.eclipse.xtext.xbase.formatting.NodeModelAccess
import org.eclipse.xtext.xbase.formatting.FormattingDataFactory
import static org.eclipse.emf.ecore.xcore.XcorePackage$Literals.*
import org.eclipse.emf.ecore.xcore.XAnnotation
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.xbase.formatting.BlankLineKey
import org.eclipse.emf.ecore.xcore.XClass
import org.eclipse.emf.ecore.xcore.XReference
import org.eclipse.emf.ecore.xcore.XAttribute
import org.eclipse.emf.ecore.xcore.XOperation
import org.eclipse.emf.ecore.xcore.XParameter
import org.eclipse.emf.ecore.xcore.XEnum
import org.eclipse.emf.ecore.xcore.XEnumLiteral
import org.eclipse.emf.ecore.xcore.XTypeParameter
import org.eclipse.emf.ecore.xcore.XDataType

class XcoreFormatter extends XbaseFormatter2
{
  @Inject
  extension NodeModelAccess

  @Inject
  extension FormattingDataFactory

  val blankLines = new BlankLineKey("foo", 1)

  def protected dispatch void format(XAnnotation xAnnotation, FormattableDocument format)
  {
    format += xAnnotation.nodeForKeyword('@').append[noSpace]

    val leftParenthesis = xAnnotation.nodeForKeyword('(')
    format += leftParenthesis.prepend[noSpace]
    format += leftParenthesis.append[noSpace]

    val rightParenthesis = xAnnotation.nodeForKeyword(')')
    format += rightParenthesis.prepend[noSpace]
    format += rightParenthesis.append[newLine]

    for (entry : xAnnotation.details)
    {
      val detail = entry as EObject
      val equals = detail.nodeForKeyword('=')
      format += equals.prepend[noSpace]
      format += equals.append[noSpace]

      val comma = detail.nodeForEObject.immediatelyFollowingKeyword(',')
      if (comma != null)
      {
        format += comma.prepend[noSpace]
      }
    }
  }

  def protected dispatch void format(XPackage xPackage, FormattableDocument format)
  {
    formatAnnotations(xPackage.annotations, format)

    format += xPackage.nodeForEObject.prepend[noSpace]
    format += xPackage.nodeForFeature(XNAMED_ELEMENT__NAME).prepend[oneSpace]

    val xImportDirectives = xPackage.importDirectives
    if (!xImportDirectives.empty)
    {
      val first = xImportDirectives.head
      val last = xImportDirectives.last
      for (xImportDirective : xImportDirectives)
      {
        val node = xImportDirective.nodeForEObject
        if (xImportDirective == first)
        {
          format += node.prepend[cfg(blankLines)]
        }
        xImportDirective.format(format)
        if (xImportDirective == last)
        {
          format += node.append[cfg(blankLines)]
        }
        else
        {
          format += node.append[newLine]
        }
      }
    }
    else
    {
      format += xPackage.nodeForFeature(XNAMED_ELEMENT__NAME).append[cfg(blankLines)]
    }

    val xClassifiers = xPackage.classifiers
    val last = xClassifiers.last
    for (xClassifier : xClassifiers)
    {
      xClassifier.format(format)
      if (last != xClassifier)
      {
        format += xClassifier.nodeForEObject.append[cfg(blankLines)]
      }
    }
  }

  def protected dispatch void format(XEnum xEnum, FormattableDocument format)
  {
    formatAnnotations(xEnum.annotations, format)

    val leftBrace = xEnum.nodeForKeyword('{')
    format += leftBrace.append[increaseIndentation]
    format += leftBrace.append[newLine]

    for (xEnumLiteral : xEnum.literals)
    {
      xEnumLiteral.format(format)
      format += xEnumLiteral.nodeForEObject.append[newLine]
    }

    val rightBrace = xEnum.nodeForKeyword('}')
    format += rightBrace.prepend[decreaseIndentation]
  }

  def protected dispatch void format(XEnumLiteral xEnumLiteral, FormattableDocument format)
  {
    formatAnnotations(xEnumLiteral.annotations, format)
  }

  def protected dispatch void format(XDataType xDataType, FormattableDocument format)
  {
    val leftAngleBracket = xDataType.nodeForKeyword('<')
    if (leftAngleBracket != null)
    {
      format += leftAngleBracket.prepend[noSpace]
      format += leftAngleBracket.append[noSpace]
      formatTypeParameters(xDataType.typeParameters, format)
      format += xDataType.nodeForKeyword('>').prepend[noSpace];
    }
  }

  def protected dispatch void format(XClass xClass, FormattableDocument format)
  {
    formatAnnotations(xClass.annotations, format)

    val leftAngleBracket = xClass.nodeForKeyword('<')
    if (leftAngleBracket != null)
    {
      format += leftAngleBracket.prepend[noSpace]
      format += leftAngleBracket.append[noSpace]
      formatTypeParameters(xClass.typeParameters, format)
      format += xClass.nodeForKeyword('>').prepend[noSpace];
    }

    val leftBrace = xClass.nodeForKeyword('{')
    format += leftBrace.append[increaseIndentation]
    format += leftBrace.append[newLine]

    for (xMember : xClass.members)
    {
      xMember.format(format)
      format += xMember.nodeForEObject.append[newLine]
    }

    val rightBrace = xClass.nodeForKeyword('}')
    format += rightBrace.prepend[decreaseIndentation]
  }

  def protected dispatch void format(XReference xReference, FormattableDocument format)
  {
    formatAnnotations(xReference.annotations, format)

    val multiplicity = xReference.nodeForFeature(XTYPED_ELEMENT__MULTIPLICITY)
    if (multiplicity != null)
    {
      format += multiplicity.prepend[noSpace]
    }

    val get = xReference.getBody
    if (get != null)
    {
      val leftBrace = get.nodeForKeyword('{')
      format += leftBrace.prepend[oneSpace]
      get.format(format)
    }
  }

  def protected dispatch void format(XAttribute xAttribute, FormattableDocument format)
  {
    formatAnnotations(xAttribute.annotations, format)

    val multiplicity = xAttribute.nodeForFeature(XTYPED_ELEMENT__MULTIPLICITY)
    if (multiplicity != null)
    {
      format += multiplicity.prepend[noSpace]
    }

    val get = xAttribute.getBody
    if (get != null)
    {
      val leftBrace = get.nodeForKeyword('{')
      format += leftBrace.prepend[oneSpace]
      get.format(format)
    }
  }

  def protected dispatch void format(XOperation xOperation, FormattableDocument format)
  {
    formatAnnotations(xOperation.annotations, format)

    val multiplicity = xOperation.nodeForFeature(XTYPED_ELEMENT__MULTIPLICITY)
    if (multiplicity != null)
    {
      format += multiplicity.prepend[noSpace]
    }

    val leftAngleBracket = xOperation.nodeForKeyword('<')
    if (leftAngleBracket != null)
    {
      format += leftAngleBracket.prepend[noSpace]
      format += leftAngleBracket.append[noSpace]
      formatTypeParameters(xOperation.typeParameters, format)
      format += xOperation.nodeForKeyword('>').prepend[noSpace];
    }

    val leftParenthesis = xOperation.nodeForKeyword('(')
    format += leftParenthesis.prepend[noSpace]
    format += leftParenthesis.append[noSpace]

    val xParameters = xOperation.parameters
    if (!xParameters.empty)
    {
      val rightParenthesis = xOperation.nodeForKeyword(')')
      format += rightParenthesis.prepend[noSpace]

      for (xParameter : xParameters)
      {
        xParameter.format(format)
      }
    }

    val body = xOperation.body
    if (body != null)
    {
      val leftBrace = body.nodeForKeyword('{')
      format += leftBrace.prepend[oneSpace]
      body.format(format)
    }
  }

  def protected dispatch void format(XParameter xParameter, FormattableDocument format)
  {
    formatAnnotations(xParameter.annotations, format)

    val multiplicity = xParameter.nodeForFeature(XTYPED_ELEMENT__MULTIPLICITY)
    if (multiplicity != null)
    {
      format += multiplicity.prepend[noSpace]
    }
  }

  def protected void formatAnnotations(List<XAnnotation> xAnnotations, FormattableDocument format)
  {
    for (xAnnotation : xAnnotations)
    {
      format(xAnnotation, format)
    }
  }

  def protected void formatTypeParameters(List<XTypeParameter> xTypeParameters, FormattableDocument format)
  {
    for (xTypeParameter : xTypeParameters)
    {
      format(xTypeParameter, format)
    }
  }
}