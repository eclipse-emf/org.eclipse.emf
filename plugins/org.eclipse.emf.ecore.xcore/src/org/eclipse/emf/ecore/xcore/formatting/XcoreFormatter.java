/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.formatting;


import org.eclipse.emf.ecore.xcore.services.XcoreGrammarAccess;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.xbase.formatting.XbaseFormatter;
import org.eclipse.xtext.xbase.services.XbaseGrammarAccess;

import com.google.inject.Inject;


/**
 * This class contains custom formatting description.
 *
 * see : http://www.eclipse.org/Xtext/documentation/latest/xtext.html#formatting
 * on how and when to use it
 *
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an example
 */
public class XcoreFormatter extends XbaseFormatter
{
  @Inject
  XbaseGrammarAccess xbaseGrammarAccess;

  @Override
  protected void configureFormatting(FormattingConfig config)
  {
    // configure(config, xbaseGrammarAccess);
    config.setAutoLinewrap(140);
    configureFormatting(config, (XcoreGrammarAccess)getGrammarAccess());
  }

  public void configureFormatting(FormattingConfig config, XcoreGrammarAccess grammarAccess)
  {
    configureXPackage(config, grammarAccess.getXPackageAccess());
    configureXAnnotation(config, grammarAccess.getXAnnotationAccess());
    configureXStringToStringMapEntry(config, grammarAccess.getXStringToStringMapEntryAccess());
    configureXImportDirective(config, grammarAccess.getXImportDirectiveAccess());
    configureQualifiedNameWithWildcard(config, grammarAccess.getQualifiedNameWithWildcardAccess());
    configureXAnnotationDirective(config, grammarAccess.getXAnnotationDirectiveAccess());
    configureXClassifier(config, grammarAccess.getXClassifierAccess());
    configureXDataType(config, grammarAccess.getXDataTypeAccess());
    configureXEnum(config, grammarAccess.getXEnumAccess());
    configureXEnumLiteral(config, grammarAccess.getXEnumLiteralAccess());
    configureXClass(config, grammarAccess.getXClassAccess());
    configureXMember(config, grammarAccess.getXMemberAccess());
    configureXAttribute(config, grammarAccess.getXAttributeAccess());
    configureXReference(config, grammarAccess.getXReferenceAccess());
    configureXOperation(config, grammarAccess.getXOperationAccess());
    configureXParameter(config, grammarAccess.getXParameterAccess());
    configureXTypeParameter(config, grammarAccess.getXTypeParameterAccess());
    configureXMultiplicity(config, grammarAccess.getXMultiplicityAccess());
    configureXBlockExpression(config, grammarAccess.getXBlockExpressionAccess());
    configureXGenericType(config, grammarAccess.getXGenericTypeAccess());
    configureXGenericTypeArgument(config, grammarAccess.getXGenericTypeArgumentAccess());
    configureXGenericWildcardTypeArgument(config, grammarAccess.getXGenericWildcardTypeArgumentAccess());
    configureXQualifiedName(config, grammarAccess.getXQualifiedNameAccess());
    configureXID(config, grammarAccess.getXIDAccess());
    configureValidID(config, grammarAccess.getValidIDAccess());
    /*
    for (Pair<Keyword, Keyword> pair : f.findKeywordPairs("{", "}"))
    {
      c.setIndentation(pair.getFirst(), pair.getSecond());
      c.setLinewrap(1).before(pair.getFirst());
      c.setLinewrap(1).after(pair.getFirst());
      c.setLinewrap(1).before(pair.getSecond());
      c.setLinewrap(1).after(pair.getSecond());
    }
    for (Keyword comma : f.findKeywords(","))
    {
      c.setNoLinewrap().before(comma);
      c.setNoSpace().before(comma);
      c.setLinewrap().after(comma);
    }
    for (Keyword dot : f.findKeywords("."))
    {
      c.setNoSpace().around(dot);
    }
    for (Pair<Keyword, Keyword> pair : f.findKeywordPairs("<", ">"))
    {
      c.setNoSpace().after(pair.getFirst());
      c.setNoSpace().before(pair.getSecond());
    }
    for (Pair<Keyword, Keyword> pair : f.findKeywordPairs("(", ")"))
    {
      c.setNoSpace().after(pair.getFirst());
      c.setNoSpace().before(pair.getSecond());
    }
    c.setLinewrap(1).after(f.getXImportDirectiveRule());
    c.setLinewrap(2).after(f.getXPackageAccess().getImportDirectivesXImportDirectiveParserRuleCall_4_0());

    c.setNoSpace().before(f.getXGenericTypeAccess().getLessThanSignKeyword_1_0());

    c.setNoSpace().after(f.getXAnnotationAccess().getCommercialAtKeyword_0());
    c.setNoSpace().before(f.getXAnnotationAccess().getLeftParenthesisKeyword_2_0());
    c.setLinewrap(1).before(f.getXAnnotationRule());
    c.setLinewrap(1).after(f.getXAnnotationRule());
    c.setNoSpace().before(f.getXStringToStringMapEntryAccess().getEqualsSignKeyword_1());
    c.setNoSpace().after(f.getXStringToStringMapEntryAccess().getEqualsSignKeyword_1());

    c.setLinewrap(1).after(f.getXAnnotationDirectiveRule());
    c.setLinewrap(2).after(f.getXPackageAccess().getAnnotationDirectivesXAnnotationDirectiveParserRuleCall_5_0());

    c.setNoSpace().before(f.getXOperationAccess().getLeftParenthesisKeyword_8());
    c.setLinewrap(2).after(f.getXPackageAccess().getNameAssignment_3());
    c.setLinewrap(2).after(f.getXClassRule());
    c.setLinewrap(2).after(f.getXDataTypeRule());
    c.setLinewrap(2).after(f.getXEnumRule());
    c.setLinewrap(1).before(f.getXOperationRule());
    c.setLinewrap(1).after(f.getXOperationRule());
    c.setLinewrap(1).after(f.getXAttributeRule());
    c.setLinewrap(1).after(f.getXReferenceRule());
    c.setLinewrap(1).after(f.getXEnumLiteralRule());
    c.setNoSpace().before(f.getXClassAccess().getLessThanSignKeyword_4_0());
    c.setNoSpace().before(f.getXDataTypeAccess().getLessThanSignKeyword_3_0());

    c.setLinewrap(0, 1, 2).before(f.getSL_COMMENTRule());
    c.setLinewrap(0, 1, 2).before(f.getML_COMMENTRule());
    c.setLinewrap(0, 1, 1).after(f.getML_COMMENTRule());
    */
  }

  public void configureXPackage(FormattingConfig config, XcoreGrammarAccess.XPackageElements elements)
  {
    config.setLinewrap().around(elements.getAnnotationsAssignment_1());
    config.setLinewrap(2).after(elements.getNameAssignment_3());
    config.setLinewrap().around(elements.getImportDirectivesAssignment_4());
    config.setLinewrap(2).before(elements.getAnnotationDirectivesXAnnotationDirectiveParserRuleCall_5_0());
    config.setLinewrap().around(elements.getAnnotationDirectivesAssignment_5());
  }

  public void configureXAnnotation(FormattingConfig config, XcoreGrammarAccess.XAnnotationElements elements)
  {
    config.setNoSpace().after(elements.getCommercialAtKeyword_0());
    config.setNoSpace().around(elements.getLeftParenthesisKeyword_2_0());
    config.setNoSpace().before(elements.getRightParenthesisKeyword_2_3());
    config.setNoSpace().before(elements.getCommaKeyword_2_2_0());
    config.setLinewrap().after(elements.getRightParenthesisKeyword_2_3());
  }

  public void configureXStringToStringMapEntry(FormattingConfig config, XcoreGrammarAccess.XStringToStringMapEntryElements elements)
  {
    config.setNoSpace().around(elements.getEqualsSignKeyword_1());
  }

  public void configureXImportDirective(FormattingConfig config, XcoreGrammarAccess.XImportDirectiveElements elements)
  {
    // No configuration required.
  }

  public void configureQualifiedNameWithWildcard(FormattingConfig config, XcoreGrammarAccess.QualifiedNameWithWildcardElements elements)
  {
    // No configuration required.
  }

  public void configureXAnnotationDirective(FormattingConfig config, XcoreGrammarAccess.XAnnotationDirectiveElements elements)
  {
    // No configuration required.
  }

  public void configureXClassifier(FormattingConfig config, XcoreGrammarAccess.XClassifierElements elements)
  {
    // No configuration required.
  }

  public void configureXDataType(FormattingConfig config, XcoreGrammarAccess.XDataTypeElements elements)
  {
    config.setLinewrap().around(elements.getAnnotationsAssignment_0());
    config.setLinewrap(2).around(elements.getRule());
  }

  public void configureXEnum(FormattingConfig config, XcoreGrammarAccess.XEnumElements elements)
  {
    config.setLinewrap().around(elements.getAnnotationsAssignment_0());
    config.setLinewrap(2).around(elements.getRule());
    config.setIndentationIncrement().after(elements.getLeftCurlyBracketKeyword_3());
    config.setIndentationDecrement().before(elements.getRightCurlyBracketKeyword_5());
    config.setLinewrap().around(elements.getLeftCurlyBracketKeyword_3());
    config.setLinewrap().around(elements.getRightCurlyBracketKeyword_5());
  }

  public void configureXEnumLiteral(FormattingConfig config, XcoreGrammarAccess.XEnumLiteralElements elements)
  {
    config.setLinewrap().around(elements.getRule());
  }

  public void configureXClass(FormattingConfig config, XcoreGrammarAccess.XClassElements elements)
  {
    config.setLinewrap(2).around(elements.getRule());
    config.setLinewrap().around(elements.getAnnotationsAssignment_1());
    config.setLinewrap().around(elements.getLeftCurlyBracketKeyword_7());
    config.setNoSpace().around(elements.getLessThanSignKeyword_4_0());
    config.setNoSpace().before(elements.getGreaterThanSignKeyword_4_3());
    config.setNoSpace().before(elements.getCommaKeyword_4_2_0());
    config.setNoSpace().before(elements.getCommaKeyword_5_2_0());
    config.setIndentationIncrement().after(elements.getLeftCurlyBracketKeyword_7());
    config.setIndentationDecrement().before(elements.getRightCurlyBracketKeyword_9());
    config.setLinewrap(2).after(elements.getRightCurlyBracketKeyword_9());
  }

  public void configureXMember(FormattingConfig config, XcoreGrammarAccess.XMemberElements elements)
  {
    // No configuration required.
  }

  public void configureXAttribute(FormattingConfig config, XcoreGrammarAccess.XAttributeElements elements)
  {
    config.setLinewrap().around(elements.getRule());
    config.setLinewrap().around(elements.getAnnotationsAssignment_1());
    config.setLinewrap(1).around(elements.getRule());
  }

  public void configureXReference(FormattingConfig config, XcoreGrammarAccess.XReferenceElements elements)
  {
    config.setLinewrap().around(elements.getRule());
    config.setLinewrap().around(elements.getAnnotationsAssignment_1());
    config.setLinewrap(1).around(elements.getRule());
  }

  public void configureXOperation(FormattingConfig config, XcoreGrammarAccess.XOperationElements elements)
  {
    config.setLinewrap().around(elements.getRule());
    config.setLinewrap().around(elements.getAnnotationsAssignment_1());
    config.setNoSpace().around(elements.getLeftParenthesisKeyword_8());
    config.setNoSpace().before(elements.getRightParenthesisKeyword_10());
    config.setNoSpace().before(elements.getCommaKeyword_4_2_0());
    config.setNoSpace().before(elements.getCommaKeyword_9_1_0());
    config.setNoSpace().before(elements.getCommaKeyword_11_2_0());
    config.setLinewrap(1).around(elements.getRule());
  }

  public void configureXParameter(FormattingConfig config, XcoreGrammarAccess.XParameterElements elements)
  {
  }

  public void configureXTypeParameter(FormattingConfig config, XcoreGrammarAccess.XTypeParameterElements elements)
  {
  }

  public void configureXMultiplicity(FormattingConfig config, XcoreGrammarAccess.XMultiplicityElements elements)
  {
  }

  public void configureXBlockExpression(FormattingConfig config, XcoreGrammarAccess.XBlockExpressionElements elements)
  {
    config.setLinewrap().around(elements.getRule());
  }

  public void configureXGenericType(FormattingConfig config, XcoreGrammarAccess.XGenericTypeElements elements)
  {
    config.setNoSpace().around(elements.getLessThanSignKeyword_1_0());
    config.setNoSpace().before(elements.getCommaKeyword_1_2_0());
    config.setNoSpace().before(elements.getGreaterThanSignKeyword_1_3());
  }

  public void configureXGenericTypeArgument(FormattingConfig config, XcoreGrammarAccess.XGenericTypeArgumentElements elements)
  {
  }

  public void configureXGenericWildcardTypeArgument(FormattingConfig config, XcoreGrammarAccess.XGenericWildcardTypeArgumentElements elements)
  {
  }

  public void configureXQualifiedName(FormattingConfig config, XcoreGrammarAccess.XQualifiedNameElements elements)
  {
    // No configuration required.
  }

  public void configureXID(FormattingConfig config, XcoreGrammarAccess.XIDElements elements)
  {
    // No configuration required.
  }

  public void configureValidID(FormattingConfig config, XcoreGrammarAccess.ValidIDElements elements)
  {
    // No configuration required.
  }

}
