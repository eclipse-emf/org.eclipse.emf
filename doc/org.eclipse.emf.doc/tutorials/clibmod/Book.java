package org.eclipse.example.library;

/**
 * @model
 */
public interface Book
{
  /**
   * @model
   */
  String getTitle();

  /**
   * @model default="100"
   */
  int getPages();

  /**
   * @model oppoosite="books"
   */
  Writer getAuthor();

  /**
   * @model
   */
  BookCategory getCategory();
}
