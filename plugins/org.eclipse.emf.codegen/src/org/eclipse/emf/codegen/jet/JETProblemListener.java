/**
 * Copyright (c) 2019 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.jet;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.codegen.CodeGenPlugin;
import org.eclipse.emf.common.util.Diagnostic;


/**
 * @since 2.19
 */
public class JETProblemListener
{
  public static final String UNKNOWN_PROBLEM = "jet.error.unknown";
  
  public static final String FILE_NOT_ON_TEMPLATE_SOURCE_PATH = "jet.error.not.on.source.path";

  public static final String FILE_NOT_A_TEMPLATE = "jet.error.not.a.template";
  
  public static final String MISSING_JET_DIRECTIVE = "jet.error.missing.jet.directive";

  public static final String UNTERMINATED = "jet.error.unterminated";

  public static final String BAD_DIRECTIVE = "jet.error.bad.directive";

  public static final String BAD_ATTRIBUTE_VALUE = "jet.error.bad.attribute.value";

  public static final String FILE_CANNOT_READ = "jet.error.file.cannot.read";

  public static final String SECTION_NO_INCLUDE = "jet.error.section.noinclude";

  public static final String UNMATCHED_DIRECTIVE = "jet.error.unmatched.directive";

  public static final String MISSING_ATTRIBUTE = "jet.error.missing.attribute";

  public static final String BAD_SKELETON = "jet.error.jet.skeleton";

  public static final String BAD_BUILDER = "jet.error.jet.builder";

  public static final String BAD_ATTRIBUTE = "jet.error.bad.attribute";

  public static final String BAD_ATTRIBUTE_DUPLICATE = "jet.error.bad.attribute.duplicate";

  public static final String BAD_ATTRIBUTE_NO_VALUE = "jet.error.attr.novalue";

  public static final String QUOTE_UNTERMINATED = "jet.error.quotes.unterminated";

  public static final String BAD_ATTRIBUTE_UNQUOTED = "jet.error.attr.quoted";

  public static final String BAD_ATTRIBUTE_UNTERMINATED = "jet.error.tag.attr.unterminated";

  private final List<JETException> problems = new ArrayList<JETException>();

  public JETProblemListener()
  {
  }

  public List<JETException> getProblems()
  {
    return problems;
  }

  public void handleProblem(JETMark start, JETMark stop, int severity, Throwable throwable, String messageKey, Object... messageSubstitutions) throws JETException
  {
    if (severity < Diagnostic.CANCEL)
    {
      JETException newJETException = newJETException(start, stop, severity, throwable, messageKey, messageSubstitutions);
      newJETException.fillInStackTrace();
      problems.add(newJETException);
    }
    else
    {
      throwJETException(start, stop, severity, throwable, messageKey, messageSubstitutions);
    }
  }

  public void throwJETException(JETMark start, JETMark stop, int severity, Throwable throwable, String messageKey, Object... messageSubstitutions) throws JETException
  {
    throw newJETException(start, stop, severity, throwable, messageKey, messageSubstitutions);
  }

  public JETException newJETException(JETMark start, JETMark stop, int severity, Throwable throwable, String messageKey, Object... messageSubstitutions)
  {
    return new JETException(messageKey, getMessage(messageKey, messageSubstitutions), null, start, stop, severity);
  }

  public String getMessage(String messageKey, Object... messageSubstitutions)
  {
    return CodeGenPlugin.getPlugin().getString(messageKey, messageSubstitutions);
  }
}
