/**
 * Copyright (c) 2005-2009 IBM Corporation and others.
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

import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.junit.Test;

public class CodeGenUtilTest
{
  @Test
  public void testValidPluginID()
  {
    assertEquals("ab.c.12", CodeGenUtil.validPluginID("ab.c.12"));
    assertEquals("AB-c_DE", CodeGenUtil.validPluginID("AB-c_DE"));
    assertEquals("a_b._c._1_2", CodeGenUtil.validPluginID("a b. c. 1 2"));
    assertEquals("a-b_.c.-1_2", CodeGenUtil.validPluginID("a-b .c.-1 2"));
    assertEquals("a-b.c.-12", CodeGenUtil.validPluginID("a-b�.c.-1�2"));
    assertEquals("_", CodeGenUtil.validPluginID("� �"));
  }
}
