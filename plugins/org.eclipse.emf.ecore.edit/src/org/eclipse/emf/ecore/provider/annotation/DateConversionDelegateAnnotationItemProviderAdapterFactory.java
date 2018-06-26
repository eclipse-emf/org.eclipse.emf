/**
 * Copyright (c) Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.provider.annotation;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAnnotationValidator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.ecore.util.BasicEAnnotationValidator;
import org.eclipse.emf.ecore.util.DateConversionDelegateFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;


/**
 * An extension for annotations with source <code>http://www.eclipse.org/emf/2002/Ecore</code>.
 *
 * @see EcorePackage#eNS_URI
 *
 * @since 2.14
 */
public final class DateConversionDelegateAnnotationItemProviderAdapterFactory extends EAnnotationItemProviderAdapterFactory
{
  protected static final BasicEAnnotationValidator.Assistant ASSISTANT = ((BasicEAnnotationValidator)EAnnotationValidator.Registry.INSTANCE.getEAnnotationValidator(
    DateConversionDelegateFactory.ANNOTATION_URI)).getAssistant();

  /**
   * Creates an instance.
   */
  public DateConversionDelegateAnnotationItemProviderAdapterFactory()
  {
    super(EcoreEditPlugin.INSTANCE, ASSISTANT);
  }

  @Override
  public IItemPropertyDescriptor getPropertyDescriptor(EObject eObject, String key, EStructuralFeature eStructuralFeature, EAnnotation eAnnotation, ResourceLocator resourceLocator)
  {
    EAttribute eAttribute = (EAttribute)eStructuralFeature;
    String messageKey = eAttribute.getEContainingClass().getName() + "_" + eAttribute.getName();
    return new ItemPropertyDescriptor(
      getRootAdapterFactory(),
      resourceLocator.getString("_UI_" + messageKey + "_feature"),
      resourceLocator.getString("_UI_" + messageKey + "_description"),
      eAttribute,
      eAttribute.isChangeable(),
      ItemPropertyDescriptor.GENERIC_VALUE_IMAGE)
      {
        @Override
        public Collection<?> getChoiceOfValues(Object object)
        {
          List<String> result = new ArrayList<String>();
          String name = feature.getName();
          if (name.equals("format"))
          {
            String value = (String)((EObject)object).eGet(feature);
            if (value != null)
            {
              result.add(value);
            }

            result.add("//Long");

            result.add("//SimpleDateFormat/yyyy-MM-dd");
            result.add("//SimpleDateFormat/yyyy%2FMM%2Fdd");
            result.add("//SimpleDateFormat/yyyy-MM-dd'T'HH:mm");
            result.add("//SimpleDateFormat/yyyy-MM-dd'T'HH:mm:ss");
            result.add("//SimpleDateFormat/yyyy-MM-dd'T'HH:mm:ss'.'SSS");
            result.add("//SimpleDateFormat/yyyy-MM-dd'T'HH:mm:ss'.'SSSZ");

            result.add("//DateFormat/LONG/en/US");
            result.add("//DateFormat/LONG/ja/JP/JP");
            result.add("//DateTimeFormat/LONG/LONG/en/US");
            result.add("//TimeFormat/LONG/en/US");
          }
          return result;
        }

        @Override
        public boolean isChoiceArbitrary(Object object)
        {
          return true;
        }
      };
  }
}
