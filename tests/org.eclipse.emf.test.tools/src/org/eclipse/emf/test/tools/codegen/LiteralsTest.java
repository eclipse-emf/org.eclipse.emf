/**
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.tools.codegen;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.codegen.ecore.genmodel.impl.Literals;
import org.junit.Test;


public class LiteralsTest
{
  /*
   * Bugzilla 273829
   */
  @Test
  public void testStringLiteral()
  {
    assertEquals("\"abc\"", Literals.toStringLiteral("abc", null));
    assertEquals("\"abc\\b def\\t ghi\\n jkl\\f mno\\r pqr\\\" stu\\\' vwx\\\\\"", Literals.toStringLiteral("abc\b def\t ghi\n jkl\f mno\r pqr\" stu\' vwx\\", null));
    assertEquals("\"abc \\u0627\\u0644\\u0648\"", Literals.toStringLiteral("abc \u0627\u0644\u0648", null));
  }

  /*
   * Bugzilla 273829
   */
  @Test
  public void testUnsafeStringLiteral()
  {
    assertEquals("\"abc\"", Literals.toUnsafeStringLiteral("abc", null));
    assertEquals("\"abc\\b def\\t ghi\\n jkl\\f mno\\r pqr\\\" stu\\\' vwx\\\\\"", Literals.toUnsafeStringLiteral("abc\b def\t ghi\n jkl\f mno\r pqr\" stu\' vwx\\", null));
    assertEquals("\"abc \u0627\u0644\u0648\"", Literals.toUnsafeStringLiteral("abc \u0627\u0644\u0648", null));
  }

  /*
   * Bugzilla 273829
   */
  @Test
  public void testCharLiteral()
  {
    assertEquals("\'a\'", Literals.toCharLiteral('a', null));
    assertEquals("\'\\b\'", Literals.toCharLiteral('\b', null));
    assertEquals("\'\\u0627\'", Literals.toCharLiteral('\u0627', null));
  }

  /*
   * Bugzilla 273829
   */
  @Test
  public void testUnsafeCharLiteral()
  {
    assertEquals("\'a\'", Literals.toUnsafeCharLiteral('a', null));
    assertEquals("\'\\b\'", Literals.toUnsafeCharLiteral('\b', null));
    assertEquals("\'\u0627\'", Literals.toUnsafeCharLiteral('\u0627', null));
  }
}
