package org.eclipse.example.library;

import java.util.List;

/**
 * @model
 */
public interface Library
{
  /**
   * @model type="Book" containment="true"
   */
  List getBooks();

  /**
   * @model type="Writer" containment="true"
   */
  List getWriters();

  /**
   * @model
   */
  String getName();
}
