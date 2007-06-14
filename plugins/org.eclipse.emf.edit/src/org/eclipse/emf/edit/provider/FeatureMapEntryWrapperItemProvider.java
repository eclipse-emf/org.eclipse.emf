/**
 * <copyright> 
 *
 * Copyright (c) 2004-2006 IBM Corporation and others.
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
 * $Id: FeatureMapEntryWrapperItemProvider.java,v 1.8 2007/06/14 18:32:42 emerks Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xml.type.ProcessingInstruction;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.SetCommand;
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
   * The resource locator from the owner's item provider.
   */
  protected ResourceLocator resourceLocator;

  /**
   * Creates an instance for the feature map entry. If the entry's feature is a reference, a delegate item provider
   * is created and set up to repeat notifications, decorating them, so that they will update this wrapper, rather
   * than the model object they originate from. If the entry's feature is an attribute or a null reference, no delegate
   * will be created.
   * 
   * @exception IllegalArgumentException If the specified feature map entry is null.
   */
  public FeatureMapEntryWrapperItemProvider(
      FeatureMap.Entry entry,
      EObject owner,
      EAttribute attribute,
      int index,
      AdapterFactory adapterFactory,
      ResourceLocator resourceLocator)
  {
    super(entry, owner, attribute, index, adapterFactory);   
  }

  /**
   * Creates an instance for the feature map entry. If the entry's feature is not a reference, the item property
   * descriptor that will be created for the value should get a resource. So, this constructor has been deprecated.
   * 
   * @exception IllegalArgumentException If the specified feature map entry is null.
   * 
   * @deprecated As of EMF 2.0.1, replaced by {@link #FeatureMapEntryWrapperItemProvider(org.eclipse.emf.ecore.util.FeatureMap.Entry, EObject, EAttribute, int, AdapterFactory, ResourceLocator) this form}.
   */
  @Deprecated
  public FeatureMapEntryWrapperItemProvider(
      FeatureMap.Entry entry,
      EObject owner,
      EAttribute attribute,
      int index,
      AdapterFactory adapterFactory)
  {
    this(entry, owner, attribute, index, adapterFactory, null);
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
  @Override
  protected Object getDelegateValue()
  {
    return isEntryAttribute() ? null : getEntryValue();
  }

  /**
   * Returns the appropriate text for the entry value. If the entry represents XML text, CDATA, or comment, it is
   * appropriately decorated and {@link #encode encoded} to remove non-printable characters. Otherwise, the feature
   * name is prepended to the text returned by the item provider decorator, for a reference value, or the factory
   * method, for an attribute value.
   */
  @Override
  public String getText(Object object)
  {
    String text = null;
    if (getEntryFeature() == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT)
    {
      text = encode(getEntryValue().toString());
    }
    else if (getEntryFeature() == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__CDATA)
    {
      text = "<![CDATA[" + encode(getEntryValue().toString()) + "]]>";
    }
    else if (getEntryFeature() == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__COMMENT)
    {
      text = "<!--" + encode(getEntryValue().toString()) + "-->";
    }
    else if (getEntryFeature() == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__PROCESSING_INSTRUCTION)
    {
      ProcessingInstruction pi = (ProcessingInstruction)getEntryValue();
      text = 
        "<?" + 
          (pi.getTarget() == null ? "" : encode(pi.getTarget())) + 
          (pi.getData() != null && pi.getData().length() > 0 ? " " + encode(pi.getData()) + "?>" : "?>");
    }
    else if (ExtendedMetaData.INSTANCE.getFeatureKind(feature) == ExtendedMetaData.ATTRIBUTE_WILDCARD_FEATURE && isEntryAttribute())
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
  @Override
  public Object getImage(Object object)
  {
    Object image = null;
    
    EStructuralFeature entryFeature = getEntryFeature();
    if (entryFeature == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT ||
          entryFeature == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__CDATA ||
          entryFeature == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__COMMENT ||
          entryFeature == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__PROCESSING_INSTRUCTION)
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
   * Encodes the given string by replacing any occurrences of non-printable characters by the corresponding Java escape
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
        case '\\':
        {
          result.append("\\\\");
          break;
        }
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
          result.append(c);
          break;
        }
      }
    }
    return result.toString();
  }

  /**
   * Decodes the given string by replacing any occurrences Java escape sequences to actual characters.
   */
  protected String decode(String s)
  {
    StringBuffer result = new StringBuffer(s.length());
    for (int i = 0, len = s.length(); i < len; i++)
    {
      char c = s.charAt(i);
      switch (c)
      {
        case '\\':
        {
          if (++i < len)
          {
            c = s.charAt(i);
            switch (c)
            {
              case '\\':
              {
                result.append('\\');
                break;
              }
              case 'b':
              {
                result.append('\b');
                break;
              }
              case 't':
              {
                result.append('\t');
                break;
              }
              case 'n':
              {
                result.append('\n');
                break;
              }
              case 'f':
              {
                result.append('\f');
                break;
              }
              case '\r':
              {
                result.append('\r');
                break;
              }
              default:
              {
                result.append('\\');
                --i;
                break;
              }
            }
          }
          else
          {
            result.append(c);
          }
          break;
        }
        default:
        {
          result.append(c);
          break;
        }
      }
    }
    return result.toString();
  }

  /**
   * Uses the delegate item provider for a reference value or creates a single property descriptor for an attribute
   * value.
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (isEntryAttribute())
    {
      if (propertyDescriptors == null)
      {
        propertyDescriptors = Collections.<IItemPropertyDescriptor>singletonList(
          new WrapperItemPropertyDescriptor(resourceLocator, getEntryFeature())
          {
            @Override
            protected Object getValue(EObject object, EStructuralFeature feature)
            {
              // When the value is changed, the property sheet page doesn't update the property sheet viewer input
              // before refreshing, and this gets called on the obsolete wrapper. So, we need to read directly from the
              // model object.
              //
              //return needsEncoding(feature) ? encode((String)getEntryValue()) : getEntryValue();

              FeatureMap featureMap = (FeatureMap)((EObject)owner).eGet(FeatureMapEntryWrapperItemProvider.this.feature);
              Object result = index >= 0 && index < featureMap.size() ? featureMap.getValue(index) : getEntryValue();
              return needsEncoding(feature) ? encode((String)result) : result;
            }
            
            @Override
            protected void setValue(EObject object, EStructuralFeature feature, Object value)
            {
              if (needsEncoding(feature))
              {
                value = decode((String)value);
              }
              ((FeatureMap)((EObject)owner).eGet(FeatureMapEntryWrapperItemProvider.this.feature)).setValue(index, value);
            }

            @Override
            protected Command createSetCommand(EditingDomain domain, Object owner, Object feature, Object value)
            {
              if (needsEncoding(feature))
              {
                value = decode((String)value);
              }
              return SetCommand.create(domain, getCommandOwner(FeatureMapEntryWrapperItemProvider.this), null, value);
            }

            protected boolean needsEncoding(Object feature)
            {
              return
                feature == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT ||
                  feature == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__CDATA ||
                  feature == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__COMMENT ||
                  feature == XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__PROCESSING_INSTRUCTION;
            }
          });
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
  @Override
  public Object getEditableValue(Object object)
  {
    return isEntryAttribute() ? getEntryValue() : super.getEditableValue(object);
  }

  /**
   * Returns whether the entry attribute is changeable.
   */
  @Override
  protected boolean isPropertySettable()
  {
    return getEntryFeature().isChangeable();
  }

  /**
   * Calls {@link WrapperItemProvider#getPropertyImage(Class) getPropertyImage} to obtain the property image for the
   * entry attribute's type.
   */
  @Override
  protected Object getPropertyImage()
  {
    return getPropertyImage(getEntryFeature().getEType().getInstanceClass());
  }

  /**
   * Uses the delegate item provider or the base wrapper implementation to create a command.
   */
  @Override
  public Command createCommand(Object object, EditingDomain domain, Class<? extends Command> commandClass, CommandParameter commandParameter)
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
  @Override
  protected Command wrapCommand(Command command, Class<? extends Command> commandClass)
  {
    if (commandClass == CopyCommand.class)
    {
      return new WrappingCopyCommand(command)
      {
        @Override
        public IWrapperItemProvider copy()
        {
          Iterator<?> i = getCommand().getResult().iterator();
          return 
            new FeatureMapEntryWrapperItemProvider
              (FeatureMapUtil.createEntry(getEntryFeature(), i.next()), (EObject)owner, (EAttribute)feature, index, adapterFactory, resourceLocator);
        }
      };
    }
    return super.wrapCommand(command, commandClass);
  }

  /**
   * Returns a wrapped set command that returns as its affected object the replacement wrapper for the value.
   * A feature map entry is also created for the value, and used as the value of the set command.
   */
  @Override
  protected Command createSetCommand(EditingDomain domain, Object owner, Object feature, Object value, int index) 
  {
    // Check that the value is type compatible with the entry feature.
    //
    if (getEntryFeature().getEType().isInstance(value))
    {
      FeatureMap.Entry entry = FeatureMapUtil.createEntry(getEntryFeature(), value);
      return new ReplacementAffectedObjectCommand(SetCommand.create(domain, this.owner, this.feature, entry, this.index));
    }
    return UnexecutableCommand.INSTANCE;
  }

  /**
   * This is only called for null or attribute values; it returns a {@link
   * WrapperItemProvider.SimpleCopyCommand} that copies the wrapper.
   */
  @Override
  protected Command createCopyCommand(EditingDomain domain, Object owner, CopyCommand.Helper helper)
  {
    return new SimpleCopyCommand(domain)
    {
      @Override
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
          (EAttribute)feature,
          index,
          adapterFactory);
      }
    };
  }
}
