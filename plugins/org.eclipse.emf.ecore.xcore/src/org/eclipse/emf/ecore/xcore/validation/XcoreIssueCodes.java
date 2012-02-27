/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.validation;

public final class XcoreIssueCodes
{
  protected static final String ISSUE_CODE_PREFIX = "org.eclipse.emf.ecore.xcore.validation.";

  public static final String DUPLICATE_IMPORT = ISSUE_CODE_PREFIX + "duplicate_import";
  public static final String UNUSED_IMPORT = ISSUE_CODE_PREFIX + "unused_import";
  public static final String COLLIDING_IMPORT = ISSUE_CODE_PREFIX + "colliding_import";
  public static final String WILDCARD_IMPORT = ISSUE_CODE_PREFIX + "wildcard_import";

  public static final String CONTAINER_WITHOUT_OPPOSITE = ISSUE_CODE_PREFIX + "container_without_opposite";

  private XcoreIssueCodes()
  {
  }
}
