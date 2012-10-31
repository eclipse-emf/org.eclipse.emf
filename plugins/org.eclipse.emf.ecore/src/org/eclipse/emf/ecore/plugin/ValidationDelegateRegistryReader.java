/**
 * Copyright (c) 2009 Kenn Hussey and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Kenn Hussey - Initial API and implementation
 */
package org.eclipse.emf.ecore.plugin;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;

import org.eclipse.emf.ecore.EValidator;


/**
 * A plugin extension reader that populates the
 * {@link org.eclipse.emf.ecore.EValidator.ValidationDelegate.Registry#INSTANCE global} validation delegate registry.
 * Clients are not expected to use this class directly.
 */
class ValidationDelegateRegistryReader extends RegistryReader
{
  static class ValidationDelegateDescriptor extends PluginClassDescriptor implements EValidator.ValidationDelegate.Descriptor
  {
    protected EValidator.ValidationDelegate validationDelegate;

    public ValidationDelegateDescriptor(IConfigurationElement e, String attrName)
    {
      super(e, attrName);
    }

    public EValidator.ValidationDelegate getValidationDelegate()
    {
      if (validationDelegate == null)
      {
        validationDelegate = (EValidator.ValidationDelegate)createInstance();
      }
      return validationDelegate;
    }
  }

  static final String TAG_DELEGATE = "delegate";
  static final String ATT_URI = "uri";
  static final String ATT_CLASS = "class";

  public ValidationDelegateRegistryReader()
  {
    super(RegistryFactory.getRegistry(), EcorePlugin.INSTANCE.getSymbolicName(), EcorePlugin.VALIDATION_DELEGATE_PPID);
  }

  @Override
  protected boolean readElement(IConfigurationElement element, boolean add)
  {
    if (element.getName().equals(TAG_DELEGATE))
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
        Object previous = EValidator.ValidationDelegate.Registry.INSTANCE.put(uri, new ValidationDelegateDescriptor(element, ATT_CLASS));
        if (previous instanceof ValidationDelegateDescriptor)
        {
          ValidationDelegateDescriptor descriptor = (ValidationDelegateDescriptor)previous;
          EcorePlugin.INSTANCE.log("Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a validation delegate for '" + uri + "'");
        }
        return true;
      }
      else
      {
        EValidator.ValidationDelegate.Registry.INSTANCE.remove(uri);
        return true;
      }
    }

    return false;
  }
}
