package org.eclipse.emf.examples.jet.article2.model;


/**
 * Class modelling an attribute of an enumeration instance. An attribute is a
 * member variable of the enumeration instance that may or may not be used to
 * uniquely identify an instance depending on whether is is marked as a key
 * attribute or not.
 * 
 * @author Remko Popma
 * @version $Revision: 1.1 $ ($Date: 2005/05/25 13:37:49 $)
 */
public class Attribute
{

  private boolean mKey = false;

  private String mName = "";

  private String mType = "";

  /**
   * Constructs an uninitialized <code>Attribute</code>.
   */
  public Attribute()
  {
    super();
  }

  /**
   * Constructs an <code>Attribute</code> initialized with the specified
   * values.
   * 
   * @param type
   *          the type of this attribute
   * @param name
   *          the name of this attribute
   * @param isKey
   *          whether this attribute is one of the key attributes that uniquely
   *          identifies an instance
   */
  public Attribute(String type, String name, boolean isKey)
  {
    super();
    mType = type;
    mName = name;
    mKey = isKey;
  }

  /**
   * Returns the name of the get method for this attribute. The returned string
   * does not contain the type of this attribute and does not end in brackets.
   * For example <code>isInitialized</code> or <code>getAddress</code>.
   * 
   * @return the name of the get method for this attribute
   */
  public String toGetMethod()
  {
    return ("boolean".equals(getType())) ? "is" + getCappedName() : "get" + getCappedName();
  }

  /**
   * Returns the name of this attribute with the first character converted to
   * upper case.
   * 
   * @return the name of this attribute with the first character converted to
   *         upper case
   */
  public String getCappedName()
  {
    return NameUtil.capName(getName());
  }

  /**
   * Returns the name of this attribute with the first character converted to
   * lower case.
   * 
   * @return the name of this attribute with the first character converted to
   *         lower case
   */
  public String getUncappedName()
  {
    return NameUtil.uncapName(getName());
  }

  public boolean isBoolean()
  {
    return "boolean".equals(getType());
  }

  public boolean isInt()
  {
    return "int".equals(getType());
  }

  public boolean isChar()
  {
    return "char".equals(getType());
  }

  public boolean isByte()
  {
    return "byte".equals(getType());
  }

  public boolean isShort()
  {
    return "short".equals(getType());
  }

  public boolean isLong()
  {
    return "long".equals(getType());
  }

  public boolean isDouble()
  {
    return "double".equals(getType());
  }

  public boolean isFloat()
  {
    return "float".equals(getType());
  }

  /**
   * Returns the name of this attribute.
   * 
   * @return the name of this attribute
   */
  public String getName()
  {
    return mName;
  }

  /**
   * Returns the type of this attribute.
   * 
   * @return the type of this attribute
   */
  public String getType()
  {
    return mType;
  }

  /**
   * Sets the name of this attribute.
   * 
   * @param name
   *          the name of this attribute
   */
  public void setName(String name)
  {
    mName = name;
  }

  /**
   * Sets the type of this attribute.
   * 
   * @param type
   *          the type of this attribute
   */
  public void setType(String type)
  {
    mType = type;
  }

  /**
   * Returns whether this attribute is one of the attributes that uniquely
   * identify an instance.
   * 
   * @return whether this attribute is one of the attributes that uniquely
   *         identify an instance
   */
  public boolean isKey()
  {
    return mKey;
  }

  /**
   * Sets whether this attribute is one of the attributes that uniquely identify
   * an instance.
   * 
   * @param isKey
   *          whether this attribute is one of the attributes that uniquely
   *          identify an instance
   */
  public void setKey(boolean isKey)
  {
    mKey = isKey;
  }
}