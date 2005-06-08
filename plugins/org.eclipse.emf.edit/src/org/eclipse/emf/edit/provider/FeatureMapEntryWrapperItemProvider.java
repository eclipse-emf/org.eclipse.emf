/**
 * <copyright> 
 *
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: FeatureMapEntryWrapperItemProvider.java,v 1.2.2.1 2005/06/08 18:27:42 nickb Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * A wrapper for {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}s. Feature map entry values can be either simple
 * attribute values or model objects, so this wrapper provides behaviours appropriate for both, depending on the type
 * of the entry feature. If it is a reference with a non-null value, an item provider will be obtained for that value,
 * and most methods will delegate to that item provider. Otherwise, simple attribute-value-like implementations
 * generally suffice. This wrapper's text and image values reflect the primary use of feature maps: to represent XML
 * mixed content and choice model groups.
 */
public class FeatureMapEntryWrapperItemProvider extends DelegatingWrapperItemProvider
{
  /**
   * The attribute through which the feature map entry can be set and retreived.
   */
  protected EAttribute attribute;

  /**
   * The index within the attribute at which the feature map entry is located.
   */
  protected int index;
  
  /**
   * Creates an instance for the feature map entry. If the entry's feature is a reference, a delegate item provider
   * is created and set up to repeat notifications, decorating them, so that they will update this wrapper, rather
   * than the model object they originate from. If the entry's feature is an attribute or a null reference, no delegate
   * will be created.
   * @exception IllegalArgumentException If the specified feature map entry is null.
   */
  public FeatureMapEntryWrapperItemProvider(
      FeatureMap.Entry entry,
      EObject owner,
      EAttribute attribute,
      int index,
      AdapterFactory adapterFactory)
  {
    super(entry, owner, adapterFactory);   
    this.attribute = attribute;
    this.index = index;
  }

  /**
   * Returns the value of the wrapped feature map entry.
   */
  protected Object getEntryValue()
  {
    return ((FeatureMap.Entry)value).getValue();
  }

  /**
   * Returns the feature of the wrapped feature map entry.
   */
  protected EStructuralFeature getEntryFeature()
  {
    return ((FeatureMap.Entry)value).getEStructuralFeature();
  }

  /**
   * Returns whether the feature of the wrapped feature map entry is an attribute.
   */
  protected boolean isEntryAttribute()
  {
    return getEntryFeature() instanceof EAttribute;
  }

  /**
   * If the entry's feature is a reference, returns its value as the value from which to obtain and which to pass to a
   * delegate item provider. If the entry's feature is an attribute, null is returned.
   */
  protected Object getDelegateValue()
  {
    return isEntryAttribute() ? null : getEntryValue();
  }

  /**
   * Returns the index within the attribute at which the feature map entry is located.
   */
  public int getIndex()
  {
    return index;
  }

  /**
   * Sets the index.
   */
  public void setIndex(int index)
  {
    this.index = index;
  }

  /**
   * Returns the appropriate text for the entry value. If the entry represents XML text, CDATA, or comment, it is
   * appropriately decorated and {@link #encode encoded} to remove non-printable characters. Otherwise, the feature
   * name is prepended to the text returned by the item provider decorator, for a reference value, or the factory
   * method, for an attribute value.
   */
  public String getText(Object object)
  {
    String text = null;
    XMLTypePackage xmlPackage = XMLTypePackage.eINSTANCE;

    if (getEntryFeature() == xmlPackage.getXMLTypeDocumentRoot_Text())
    {
      text = encode(getEntryValue().toString());
    }
    else if (getEntryFeature() == xmlPackage.getXMLTypeDocumentRoot_CDATA())
    {
      text = "<![CDATA[" + encode(getEntryValue().toString()) + "]]>";
    }
    else if (getEntryFeature() == xmlPackage.getXMLTypeDocumentRoot_Comment())
    {
      text = "<!--" + encode(getEntryValue().toString()) + "-->";
    }
    else if (ExtendedMetaData.INSTANCE.getFeatureKind(attribute) == ExtendedMetaData.ATTRIBUTE_WILDCARD_FEATURE)
    {
      text = getEntryFeature().getName() + "='" +
        EcoreUtil.convertToString((EDataType)getEntryFeature().getEType(), getEntryValue()) + "'";
    }
    else if (getEntryValue() == null)
    {
      text = "<" + getEntryFeature().getName() + " xsi:nil=\"true\"/>";
    }
    else if (isEntryAttribute())
    {
      text = addEntryFeature(EcoreUtil.convertToString((EDataType)getEntryFeature().getEType(), getEntryValue()));
    }
    else
    {
      text = addEntryFeature(super.getText(object));
    }
    return text;
  }

  /**
   * Prepends the entry feature name to the given text and returns the result.
   */
  protected String addEntryFeature(String text)
  {
    return "<" + getEntryFeature().getName() + "> " + text;
  }

  /**
   * Returns the appropriate image for the entry value: the text property icon for text, CDATA, or comment; the generic
   * property icon for an attribute value; the generic item icon for a null reference value; or the icon returned by
   * the delegate item provider for a non-null reference value.
   */
  public Object getImage(Object object)
  {
    Object image = null;
    XMLTypePackage xmlPackage = XMLTypePackage.eINSTANCE;

    if (getEntryFeature() == xmlPackage.getXMLTypeDocumentRoot_Text() ||
        getEntryFeature() == xmlPackage.getXMLTypeDocumentRoot_CDATA() ||
        getEntryFeature() == xmlPackage.getXMLTypeDocumentRoot_Comment())
    {
      image = EMFEditPlugin.INSTANCE.getImage("full/obj16/TextValue");
    }
    else if (isEntryAttribute())
    {
      image = EMFEditPlugin.INSTANCE.getImage("full/obj16/GenericValue");      
    }
    else if (getDelegateValue() == null)
    {
      image = EMFEditPlugin.INSTANCE.getImage("full/obj16/Item");
    }
    else
    {
      image = super.getImage(object);
    }
    return image;
  }

  /**
   * Encodes the given string by replacing any occurences of non-printable characters by the corresponding Java escape
   * sequence.
   */
  protected String encode(String s)
  {
    StringBuffer result = new StringBuffer(s.length());
    for (int i = 0, len = s.length(); i < len; i++)
    {
      char c = s.charAt(i);
      switch (c)
      {
        case '\b':
        {
          result.append("\\b");
          break;
        }
        case '\t':
        {
          result.append("\\t");
          break;
        }
        case '\n':
        {
          result.append("\\n");
          break;
        }
        case '\f':
        {
          result.append("\\f");
          break;
        }
        case '\r':
        {
          result.append("\\r");
          break;
        }
        default:
        {
          if (c < ' ')
          {
            result.append('\\');
            result.append((int)c);
          }
          else
          {
            result.append(c);
          }
          break;
        }
      }
    }
    return result.toString();
  }

  /**
   * Uses the delegate item provider for a reference value or creates a single property descriptor for an attribute
   * value, calling out to {@link #getPropertyName getPropertyName}, {@link #getPropertyDescription
   * getPropertyDescription}, {@link #isPropertySettable isPropertySettable}, and {@link #getPropertyImage
   * getPropertyImage}.
   */
  public List getPropertyDescriptors(Object object)
  {
    if (isEntryAttribute())
    {
      if (propertyDescriptors == null)
      {
        IItemPropertyDescriptor descriptor = new ItemPropertyDescriptor(
          getRootAdapterFactory(),
          getPropertyName(),
          getPropertyDescription(),
          getEntryFeature(),
          isPropertySettable(),
          getPropertyImage());
        descriptor = new ItemPropertyDescriptorDecorator(owner, descriptor);
        propertyDescriptors = Collections.singletonList(descriptor);
      }

      return propertyDescriptors;
    }
    else
    {
      return super.getPropertyDescriptors(object);
    }
  }

  /**
   * Uses the delegate item provider for a reference value or returns the attribute value itself.
   */
  public Object getEditableValue(Object object)
  {
    return isEntryAttribute() ? getEntryValue() : super.getEditableValue(object);
  }

  /**
   * Returns false, since the property descriptor decorator cannot properly set the feature map entry value.
   */
  protected boolean isPropertySettable()
  {
    return false;
  }

  /**
   * Calls {@link WrapperItemProvider#getPropertyImage(Class) getPropertyImage} to obtain the property image for the
   * entry attribute's type.
   */
  protected Object getPropertyImage()
  {
    return getPropertyImage(getEntryFeature().getEType().getInstanceClass());
  }

  /**
   * Uses the delegate item provider or the base wrapper implementation to create a command.
   */
  public Command createCommand(Object object, EditingDomain domain, Class commandClass, CommandParameter commandParameter)
  {
    if (getDelegateValue() == null)
    {
      return baseCreateCommand(object, domain, commandClass, commandParameter);
    }
    return super.createCommand(object, domain, commandClass, commandParameter);
  }

  /**
   * For a copy command, creates a {@link WrapperItemProvider.WrappingCopyCommand}, which copies the feature map entry
   * and wrapper along with the entry value; for other commands, the wrapper-substituting command wrapper supplied by
   * the base implementation is used. This method is only called for non-null reference values to wrap a command
   * returned by the delegate item provider.
   */
  protected Command wrapCommand(Command command, Class commandClass)
  {
    if (commandClass == CopyCommand.class)
    {
      return new WrappingCopyCommand(command)
      {
        public IWrapperItemProvider copy()
        {
          Iterator i = getCommand().getResult().iterator();
          return new FeatureMapEntryWrapperItemProvider(
            FeatureMapUtil.createEntry(getEntryFeature(), i.next()), (EObject)owner, attribute, index, adapterFactory);
        }
      };
    }
    return super.wrapCommand(command, commandClass);
  }

  /**
   * This is only called for null or attribute values; it returns a {@link
   * WrapperItemProvider.SimpleCopyCommand} that copies the wrapper.
   */
  protected Command createCopyCommand(EditingDomain domain, Object owner, CopyCommand.Helper helper)
  {
    return new SimpleCopyCommand(domain)
    {
      public IWrapperItemProvider copy()
      {
        Object entryValueCopy = null;
        Object entryValue = getEntryValue();

        if (entryValue != null)
        {
          EDataType dataType = (EDataType)getEntryFeature().getEType();
          String serialization = EcoreUtil.convertToString(dataType, entryValue);
          entryValueCopy = EcoreUtil.createFromString(dataType, serialization);
          if (serialization == entryValue && serialization == entryValueCopy)
          {
            entryValueCopy = new String((String)entryValue);
          }
        }

        return new FeatureMapEntryWrapperItemProvider(
          FeatureMapUtil.createEntry(getEntryFeature(), entryValueCopy),
          (EObject)FeatureMapEntryWrapperItemProvider.this.owner,
          attribute,
          index,
          adapterFactory);
      }
    };
  }
}