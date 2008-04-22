package org.eclipse.emf.examples.jet.article2.model;


import java.util.Iterator;
import java.util.Properties;


/**
 * Class modelling an Singleton instance of an enumeration. An instance has a
 * name, a set of attribute values, and knows whether it is the default instance
 * of the enumeration.
 * 
 * @author Remko Popma
 * @version $Revision: 1.3 $ ($Date: 2008/04/22 13:35:56 $)
 */
public class Instance
{

  private TypesafeEnum mType = null;

  private String mName = "";

  private Properties mValues = new Properties();

  /**
   * Creates an uninitialized <code>Instance</code>.
   */
  public Instance()
  {
    super();
  }

  /**
   * Creates an <code>Instance</code> with the specified name.
   * 
   * @param name
   *          the name of this instance
   */
  public Instance(String name)
  {
    mName = name;
  }

  /**
   * Creates an <code>Instance</code> with the specified name and attribute
   * values.
   * 
   * @param name
   *          the name of this instance
   * @param values
   *          a <code>Properties</code> object mapping attribute names to the
   *          attribute values for this instance
   */
  public Instance(String name, Properties values)
  {
    super();
    mName = name;
    mValues = values;
  }

  /**
   * Convenience method that returns a description of the key attribute names
   * and values.
   * 
   * @return a comma-separated list of key attribute names and values, formatted
   *         like
   *         <code>attrib1-name = attrib1-value, attrib2-name = attrib2-value (, ...)</code>
   * @throws IllegalStateException
   *           if this instance has not been added to a type
   */
  public String keyDescription()
  {
    assertTypeNotNull();

    StringBuffer result = new StringBuffer();
    for (Iterator<Attribute> i = mType.keyAttributes(); i.hasNext();)
    {
      Attribute attribute = i.next();
      result.append(attribute.getCappedName());
      result.append(" = ");
      result.append(getAttributeValue(attribute));
      if (i.hasNext())
      {
        result.append(", ");
      }
    }
    return result.toString();
  }

  /**
   * Convenience method that returns the attribute values of this instance, in
   * the order expected by the constructor of this instance.
   * 
   * @return a comma-separated list of all attribute values of this instance,
   *         formatted like <code>attrib1-value, attrib2-value (, ...)</code>
   * @throws IllegalStateException
   *           if this instance has not been added to a type
   */
  public String constructorValues()
  {
    assertTypeNotNull(); // check we've been added to a TypesafeEnum

    StringBuffer result = new StringBuffer();
    for (Iterator<Attribute> i = getType().attributes(); i.hasNext();)
    {
      Attribute attribute = i.next();
      result.append(getAttributeValue(attribute));
      if (i.hasNext())
      {
        result.append(", ");
      }
    }
    return result.toString();
  }

  /**
   * Returns the name of this instance converted to a java class name. For
   * example, a constant name like <code>THIS_IS_A_CONSTANT</code> is
   * converted to <code>ThisIsAConstant</code>.
   * 
   * @return the name of this instance converted to a java class name
   */
  public String getCappedName()
  {
    return NameUtil.constantToJavaClassName(getName());
  }

  /**
   * Returns the name of this instance.
   * 
   * @return the name of this instance
   */
  public String getName()
  {
    return mName;
  }

  /**
   * Returns the <code>Properties</code> object mapping attribute names to the
   * attribute values for this instance
   * 
   * @return the <code>Properties</code> object mapping attribute names to the
   *         attribute values for this instance
   */
  public Properties getValues()
  {
    return mValues;
  }

  /**
   * Returns the value of the specified attribute for this instance.
   * 
   * @param attribute
   *          the attribute whose value to return
   * @return the value of the specified attribute for this instance
   */
  public String getAttributeValue(Attribute attribute)
  {
    return getAttributeValue(attribute.getName());
  }

  /**
   * Returns the value of the specified attribute for this instance.
   * 
   * @param attributeName
   *          the name of the attribute whose value to return
   * @return the value of the specified attribute for this instance
   */
  public String getAttributeValue(String attributeName)
  {
    return mValues.getProperty(attributeName);
  }

  /**
   * Sets the name of this instance.
   * 
   * @param name
   *          the name of this instance
   */
  public void setName(String name)
  {
    mName = name;
  }

  /**
   * Sets the <code>Properties</code> object mapping attribute names to the
   * attribute values for this instance.
   * 
   * @param values
   *          the <code>Properties</code> object mapping attribute names to
   *          the attribute values for this instance
   */
  public void setValues(Properties values)
  {
    mValues = values;
  }

  /**
   * Returns the <code>TypesafeEnum</code> parent for this instance.
   * 
   * @return the <code>TypesafeEnum</code> parent for this instance
   */
  public TypesafeEnum getType()
  {
    return mType;
  }

  /**
   * Sets the <code>TypesafeEnum</code> parent for this instance.
   * 
   * @param type
   *          the <code>TypesafeEnum</code> parent for this instance
   */
  /* package */void setType(TypesafeEnum type)
  {
    mType = type;
  }

  /**
   * Returns whether this instance is the default instance of the parent
   * <code>TypesafeEnum</code>.
   * 
   * @return whether this instance is the default instance of the parent
   *         <code>TypesafeEnum</code>
   * @throws IllegalStateException
   *           if this instance has not been added to a type
   */
  public boolean isDefault()
  {
    assertTypeNotNull();
    return getType().getDefaultInstance() == this;
  }

  /**
   * Sets whether this instance is the default instance of the parent
   * <code>TypesafeEnum</code>.
   * 
   * @throws IllegalStateException
   *           if this instance has not been added to a type
   */
  public void setDefault()
  {
    assertTypeNotNull();
    getType().setDefaultInstance(this);
  }

  private void assertTypeNotNull()
  {
    if (getType() == null)
    {
      throw new IllegalStateException("Type is null. This instance has not been added to a type yet.");
    }
  }
}