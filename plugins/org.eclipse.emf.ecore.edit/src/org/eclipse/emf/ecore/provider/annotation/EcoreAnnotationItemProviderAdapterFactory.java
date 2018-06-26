/**
 * Copyright (c) Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.provider.annotation;


import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAnnotationValidator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.ecore.util.BasicEAnnotationValidator;
import org.eclipse.emf.ecore.util.QueryDelegate;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;


/**
 * An extension for annotations with source <code>http://www.eclipse.org/emf/2002/Ecore</code>.
 *
 * @see EcorePackage#eNS_URI
 *
 * @since 2.14
 */
public final class EcoreAnnotationItemProviderAdapterFactory extends EAnnotationItemProviderAdapterFactory
{
  protected static final BasicEAnnotationValidator.Assistant ASSISTANT = ((BasicEAnnotationValidator)EAnnotationValidator.Registry.INSTANCE.getEAnnotationValidator(
    EcorePackage.eNS_URI)).getAssistant();

  /**
   * Creates an instance.
   */
  public EcoreAnnotationItemProviderAdapterFactory()
  {
    super(EcoreEditPlugin.INSTANCE, ASSISTANT);

    //    // This can be used to test a registered validator designed to validate annotations on annotations.
    //    int xxx;
    //    final BasicEAnnotationValidator.Assistant assistant = ((BasicEAnnotationValidator)EAnnotationValidator.Registry.INSTANCE.getEAnnotationValidator("Testing")).getAssistant();
    //    EAnnotationItemProviderAdapterFactory.Registry.INSTANCE.put("Testing", new EAnnotationItemProviderAdapterFactory.Factory()
    //      {
    //        public EAnnotationItemProviderAdapterFactory createEAnnotationItemProviderAdapterFactory()
    //        {
    //          return new EAnnotationItemProviderAdapterFactory(EcoreEditPlugin.INSTANCE, assistant)
    //            {
    //            };
    //        }
    //      });
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
          String name = feature.getName();
          if (name.equals("conversionDelegates"))
          {
            return mergeValueChoices(object, EDataType.Internal.ConversionDelegate.Factory.Registry.INSTANCE.getTargetPlatformFactories());
          }
          else if (name.equals("invocationDelegates"))
          {
            return mergeValueChoices(object, EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE.getTargetPlatformFactories());
          }
          else if (name.equals("validationDelegates"))
          {
            return mergeValueChoices(object, EValidator.ValidationDelegate.Registry.INSTANCE.getTargetPlatformFactories());
          }
          else if (name.equals("settingDelegates"))
          {
            return mergeValueChoices(object, EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE.getTargetPlatformFactories());
          }
          else if (name.equals("queryDelegates"))
          {
            return mergeValueChoices(object, QueryDelegate.Factory.Registry.INSTANCE.getTargetPlatformFactories());
          }
          else
          {
            return super.getChoiceOfValues(object);
          }
        }

        @Override
        public boolean isChoiceArbitrary(Object object)
        {
          String name = feature.getName();
          return super.isChoiceArbitrary(object) || name.equals("conversionDelegates") || name.equals("invocationDelegates") || name.equals("validationDelegates")
            || name.equals("settingDelegates");
        }

        private Collection<String> mergeValueChoices(Object object, Set<String> keys)
        {
          EObject eObject = (EObject)object;
          @SuppressWarnings("unchecked")
          List<String> values = (List<String>)eObject.eGet(feature);
          List<String> result = new UniqueEList<String>(values);
          result.addAll(keys);
          return result;
        }
      };
  }
}
