package org.eclipse.example.library;
import java.util.List;

/**
 * @model
 */
public interface Library
{
  /**
   * @model
   */
  String getName();

  /**
   * @model containment="true"
   */
  List<Writer> getWriters();

  /**
   * @model containment="true"
   */
  List<Book> getBooks();
}
