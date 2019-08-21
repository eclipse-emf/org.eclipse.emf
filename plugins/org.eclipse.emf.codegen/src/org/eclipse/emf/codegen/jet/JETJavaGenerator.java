/**
 * Copyright (c) Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.jet;


/**
 * The interface that generators which generate Java snippets implement.
 *
 * @since 2.19
 */
public interface JETJavaGenerator extends JETGenerator
{
  /**
   * Returns the start mark for the source of this generator.
   */
  JETMark getStart();

  /**
   * Returns the stop mark for the source of this generator.
   */
  JETMark getStop();

  /**
   * Returns the offset within the {@link #generate() generated} result at which the source of the template's {@link #getStart() start} mark starts.
   */
  int getRelativeJavaOffset();

  /**
   * Returns the length of the actual {@link #generate() generated} result at which the source of the template's {@link #getStop() stop} mark ends.
   */
  int getJavaLength();

  /**
   * Returns the associated item.
   */
  JETJavaItem getJavaItem();
}
