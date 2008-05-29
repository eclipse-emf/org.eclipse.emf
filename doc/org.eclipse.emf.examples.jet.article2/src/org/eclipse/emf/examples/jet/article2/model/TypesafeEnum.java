package org.eclipse.emf.examples.jet.article2.model;


import java.util.ArrayList;
import java.util.Iterator;


/**
 * Class modelling a Java typesafe enumeration class.
 * 
 * @author Remko Popma
 * @version $Revision: 1.3 $ ($Date: 2008/05/29 14:56:38 $)
 */
public class TypesafeEnum
{

  private String mPackageName = "";

  private String mClassName = "";

  private String mAuthor = "";

  private String mVersion = "";

  private ArrayList<Instance> mInstances = new ArrayList<Instance>();

  private ArrayList<Attribute> mAttributes = new ArrayList<Attribute>();

  private Instance mDefaultInstance = null;

  /**
   * Constructs an uninitialized <code>TypesafeEnum</code>.
   */
  public TypesafeEnum()
  {
    super();
  }

  /**
   * Convenience method that returns a comma-separated list of attribute types
   * and names. The result of this method can be used as the parameter list for
   * the constructor of an instance.
   * 
   * @return a comma-separated list of attributes, formatted like
   *         <code>attrib1-type attrib1-name, attrib2-type attrib2-name (, ...)</code>
   */
  public String constructorParameterDescription()
  {
    return parameterDescription(attributes());
  }

  /**
   * Convenience method that returns a comma-separated list of the key attribute
   * types and names. The result of this method can be used as the parameter
   * list for the lookup method.
   * 
   * @return a comma-separated list of key attributes, formatted like
   *         <code>key-attrib1-type key-attrib1-name, key-attrib2-type key-attrib2-name (, ...)</code>
   */
  public String keyParameterDescription()
  {
    return parameterDescription(keyAttributes());
  }

  private String parameterDescription(Iterator<Attribute> attributes)
  {
    StringBuffer result = new StringBuffer();

    for (Iterator<Attribute> i = attributes; i.hasNext();)
    {
      Attribute attrib = i.next();
      result.append(attrib.getType()).append(' ');
      result.append(attrib.getUncappedName());

      if (i.hasNext())
      {
        result.append(", ");
      }
    }
    return result.toString();
  }

  /**
   * Returns whether this <code>TypesafeEnum</code> has a default instance.
   * 
   * @return whether this <code>TypesafeEnum</code> has a default instance
   */
  public boolean hasDefaultInstance()
  {
    return getDefaultInstance() != null;
  }

  /**
   * Returns the default instance of this <code>TypesafeEnum</code>, or
   * <code>null</code> if this type does not have a default instance.
   * 
   * @return the default instance or <code>null</code>
   */
  public Instance getDefaultInstance()
  {
    return mDefaultInstance;
  }

  /**
   * Sets the default instance of this <code>TypesafeEnum</code>.
   * 
   * @param newDefault
   *          the new default instance
   */
  public void setDefaultInstance(Instance newDefault)
  {
    mDefaultInstance = newDefault;
  }

  /**
   * Returns the author to use in the type comment of this type
   * 
   * @return the author to use in the type comment of this type
   */
  public String getAuthor()
  {
    return mAuthor;
  }

  /**
   * Returns the class name of this type.
   * 
   * @return the class name of this type
   */
  public String getClassName()
  {
    return mClassName;
  }

  /**
   * Returns the package name of this type.
   * 
   * @return the package name of this type
   */
  public String getPackageName()
  {
    return mPackageName;
  }

  /**
   * Returns the version string to use in the type comment of this type.
   * 
   * @return the version string to use in the type comment of this type
   */
  public String getVersion()
  {
    return mVersion;
  }

  /**
   * Sets the author to use in the type comment of this type.
   * 
   * @param author
   *          the author to use in the type comment of this type
   */
  public void setAuthor(String author)
  {
    mAuthor = author;
  }

  /**
   * Sets the class name of this type.
   * 
   * @param className
   *          the class name of this type
   */
  public void setClassName(String className)
  {
    mClassName = className;
  }

  /**
   * Sets the package name of this type.
   * 
   * @param packageName
   *          the package name of this type
   */
  public void setPackageName(String packageName)
  {
    mPackageName = packageName;
  }

  /**
   * Sets the version string to use in the type comment of this type.
   * 
   * @param version
   *          the version string to use in the type comment of this type
   */
  public void setVersion(String version)
  {
    mVersion = version;
  }

  /**
   * Returns an <code>Iterator</code> over the instances of this type.
   * 
   * @return an <code>Iterator</code> over the instances of this type
   */
  public Iterator<Instance> instances()
  {
    return mInstances.iterator();
  }

  /**
   * Returns an <code>Iterator</code> over the attributes of this type.
   * 
   * @return an <code>Iterator</code> over the attributes of this type
   */
  public Iterator<Attribute> attributes()
  {
    return mAttributes.iterator();
  }

  /**
   * Returns an <code>Iterator</code> over the attributes of this type that
   * have been marked as key attributes (uniquely identifying instances).
   * 
   * @return an <code>Iterator</code> over the key attributes
   */
  public Iterator<Attribute> keyAttributes()
  {
    ArrayList<Attribute> result = new ArrayList<Attribute>();
    for (Iterator<Attribute> i = attributes(); i.hasNext();)
    {
      Attribute attrib = i.next();
      if (attrib.isKey())
      {
        result.add(attrib);
      }
    }
    return result.iterator();
  }

  /**
   * Adds an instance to this <code>TypesafeEnum</code>, and sets the type of
   * the specified <code>Instance</code> to this object.
   * 
   * @param instance
   *          the instance to add
   */
  public void addInstance(Instance instance)
  {
    mInstances.add(instance);
    instance.setType(this);
  }

  /**
   * Removes the specified <code>Instance</code> from this
   * <code>TypesafeEnum</code>, and sets the type of the specified instance
   * to <code>null</code>.
   * 
   * @param instance
   *          the instance to remove
   * @return whether the instance was in this type
   */
  public boolean removeInstance(Instance instance)
  {
    boolean result = mInstances.remove(instance);
    if (result)
    {
      instance.setType(null);
    }
    return result;
  }

  /**
   * Returns the number of instances in this type.
   * 
   * @return the number of instances in this type
   */
  public int instanceCount()
  {
    return mInstances.size();
  }

  /**
   * Adds the specified <code>Attribute</code> to this
   * <code>TypesafeEnum</code>.
   * 
   * @param attribute
   *          the attribute to add
   */
  public void addAttribute(Attribute attribute)
  {
    mAttributes.add(attribute);
  }

  /**
   * Removes the specified <code>Attribute</code> from this
   * <code>TypesafeEnum</code>.
   * 
   * @param attribute
   *          the attribute to remove
   * @return whether the specified attribute existed in this type
   */
  public boolean removeAttribute(Attribute attribute)
  {
    return mAttributes.remove(attribute);
  }

  /**
   * Returns the number of attributes in this type.
   * 
   * @return the number of attributes in this type
   */
  public int attributeCount()
  {
    return mAttributes.size();
  }

  /**
   * Returns whether this is the last attribute in the list.
   * 
   * @param attribute
   * @return whether this is the last attribute in the list
   */
  public boolean isLast(Attribute attribute)
  {
    return mAttributes.indexOf(attribute) == attributeCount() - 1;
  }

  /**
   * Returns whether this is the first attribute in the list.
   * 
   * @param attribute
   * @return whether this is the first attribute in the list
   */
  public boolean isFirst(Attribute attribute)
  {
    return mAttributes.indexOf(attribute) == 0;
  }

  /**
   * Returns whether this is the last instance in the list.
   * 
   * @param instance
   * @return whether this is the last instance in the list
   */
  public boolean isLast(Instance instance)
  {
    return mInstances.indexOf(instance) == instanceCount() - 1;
  }

  /**
   * Returns whether this is the first instance in the list.
   * 
   * @param instance
   * @return whether this is the first instance in the list
   */
  public boolean isFirst(Instance instance)
  {
    return mInstances.indexOf(instance) == 0;
  }

  /**
   * @param attributeName
   * @return whether there is an attribute with a specific name
   */
  public boolean hasAttribute(String attributeName)
  {
    for (Iterator<Attribute> i = attributes(); i.hasNext();)
    {
      if (i.next().getName().equals(attributeName))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the attributes in this typesafe enum as an array.
   * 
   * @return the attributes in this typesafe enum as an array
   */
  public Attribute[] attributeArray()
  {
    return mAttributes.toArray(new Attribute [mAttributes.size()]);
  }
}