package org.eclipse.emf.examples.jet.article2.model;


/**
 * Utility class for formatting names of java model objects.
 * 
 * @author Remko Popma
 * @version $Revision: 1.2 $ ($Date: 2006/12/29 18:36:19 $)
 */
public class NameUtil
{

  /**
   * Returns the specified string with the first character converted to lower
   * case.
   * 
   * @param name
   *          the string to convert
   * @return the specified string with the first character converted to lower
   *         case
   */
  public static String uncapName(String name)
  {
    if (name.length() == 0)
    {
      return name;
    }
    return Character.toLowerCase(name.charAt(0)) + name.substring(1);
  }

  /**
   * Returns the specified string with the first character converted to upper
   * case.
   * 
   * @param name
   *          the string to convert
   * @return the specified string with the first character converted to upper
   *         case
   */
  public static String capName(String name)
  {
    if (name.length() == 0)
    {
      return name;
    }
    return Character.toUpperCase(name.charAt(0)) + name.substring(1);
  }

  /**
   * Converts the specified constant name to a Java class name: the returned
   * string will have an initial upper case character, followed by all lower
   * case characters, except for characters following an underscore '_' or a
   * whitespace character: characters following a character that will be removed
   * from the identifier are converted to upper case.
   * 
   * @param code
   *          the name to convert to a Java class name
   * @return a Java class name for the specified name
   */
  public static String constantToJavaClassName(String code)
  {
    StringBuffer result = new StringBuffer();

    boolean upperNext = true;
    char[] chars = code.toCharArray();
    for (int i = 0; i < chars.length; i++)
    {
      char c = chars[i];

      boolean valid = (i == 0) ? Character.isJavaIdentifierStart(c) : Character.isJavaIdentifierPart(c);

      if (!valid)
      {
        upperNext = true;
        continue;
      }
      if (c == '_')
      {
        upperNext = true;
        continue;
      }

      if (upperNext)
      {
        result.append(Character.toUpperCase(c));
        upperNext = false;
      }
      else
      {
        result.append(Character.toLowerCase(c));
      }
    }
    return result.toString();
  }

  /**
   * Returns whether the specified string is a valid Java identifier.
   * 
   * @param name
   *          the string to check
   * @return whether the specified string is a valid Java identifier
   */
  public static boolean isValidIdentifier(String name)
  {
    char[] chars = name.toCharArray();
    for (int i = 0; i < chars.length; i++)
    {
      char c = chars[i];

      boolean valid = (i == 0) ? Character.isJavaIdentifierStart(c) : Character.isJavaIdentifierPart(c);

      if (!valid)
      {
        return false;
      }
    }
    return true;
  }

  /**
   * Private constructor: this class is not intended to be instantiated.
   */
  private NameUtil()
  {
    super();
  }
}