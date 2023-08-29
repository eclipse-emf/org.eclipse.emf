/**
 * Copyright (c) 2017 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.plugin;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAnnotationValidator;


/**
 * A plugin extension reader that populates the
 * {@link org.eclipse.emf.ecore.EAnnotationValidator.Registry#INSTANCE global} annotation validator registry.
 * Clients are not expected to use this class directly.
 */
class AnnotationValidatorRegistryReader extends RegistryReader
{
  static class AnnotationValidatorDescriptor extends PluginClassDescriptor implements EAnnotationValidator.Descriptor
  {
    protected EAnnotationValidator eAnnotationValidator;

    public AnnotationValidatorDescriptor(IConfigurationElement e, String attrName)
    {
      super(e, attrName);
    }

    public EAnnotationValidator getEAnnotationValidator()
    {
      try
      {
        Class<?> javaClass = CommonPlugin.loadClass(element.getDeclaringExtension().getContributor().getName(), element.getAttribute(attributeName));
        try
        {
          // First try to see if this class has an INSTANCE field
          //
          Field field = javaClass.getField("INSTANCE");
          Object result = field.get(null);
          return (EAnnotationValidator)result;
        }
        catch (NoSuchFieldException e)
        {
          // If not, create a new instance.
          return (EAnnotationValidator) javaClass.getDeclaredConstructor().newInstance();
        }
      }
      catch (ClassNotFoundException e)
      {
        throw new WrappedException(e);
      }
      catch (IllegalAccessException e)
      {
        throw new WrappedException(e);
      }
      catch (InstantiationException e)
      {
        throw new WrappedException(e);
      }
      catch (IllegalArgumentException e)
      {
        throw new WrappedException(e);
      }
      catch (InvocationTargetException e)
      {
        throw new WrappedException(e);
      }
      catch (NoSuchMethodException e)
      {
        throw new WrappedException(e);
      }
      catch (SecurityException e)
      {
        throw new WrappedException(e);
      }
    }
  }

  static final String TAG_VALIDATOR = "validator";

  static final String ATT_URI = "uri";

  static final String ATT_CLASS = "class";

  public AnnotationValidatorRegistryReader()
  {
    super(RegistryFactory.getRegistry(), EcorePlugin.INSTANCE.getSymbolicName(), EcorePlugin.ANNOTATION_VALIDATOR_PPID);
  }

  @Override
  protected boolean readElement(IConfigurationElement element, boolean add)
  {
    if (element.getName().equals(TAG_VALIDATOR))
    {
      String uri = element.getAttribute(ATT_URI);
      if (uri == null)
      {
        logMissingAttribute(element, ATT_URI);
      }
      else if (element.getAttribute(ATT_CLASS) == null)
      {
        logMissingAttribute(element, ATT_CLASS);
      }
      else if (add)
      {
        Object previous = EAnnotationValidator.Registry.INSTANCE.put(uri, new AnnotationValidatorDescriptor(element, ATT_CLASS));
        if (previous instanceof AnnotationValidatorDescriptor)
        {
          AnnotationValidatorDescriptor descriptor = (AnnotationValidatorDescriptor)previous;
          EcorePlugin.INSTANCE.log(
            "Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register an annotation validator for '" + uri + "'");
        }
        return true;
      }
      else
      {
        EAnnotationValidator.Registry.INSTANCE.remove(uri);
        return true;
      }
    }

    return false;
  }
}