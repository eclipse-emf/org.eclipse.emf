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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAnnotationValidator;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.BasicEAnnotationValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaDataAnnotationValidator;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptorDecorator;


/**
 * An extension for annotations with source <code>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</code>.
 *
 * @see ExtendedMetaData#ANNOTATION_URI
 *
 * @since 2.14
 */
public final class ExtendedMetaDataAnnotationItemProviderAdapterFactory extends EAnnotationItemProviderAdapterFactory
{
  protected static final BasicEAnnotationValidator.Assistant ASSISTANT = ((BasicEAnnotationValidator)EAnnotationValidator.Registry.INSTANCE.getEAnnotationValidator(
    ExtendedMetaData.ANNOTATION_URI)).getAssistant();

  /**
   * Creates an instance.
   */
  public ExtendedMetaDataAnnotationItemProviderAdapterFactory()
  {
    super(EcoreEditPlugin.INSTANCE, ASSISTANT);
  }

  @Override
  public IItemPropertyDescriptor getPropertyDescriptor(EObject eObject, String key, EStructuralFeature eStructuralFeature, EAnnotation eAnnotation, ResourceLocator resourceLocator)
  {
    final EObject context = eObject;
    String messageKey = eStructuralFeature.getEContainingClass().getName() + "_" + eStructuralFeature.getName();
    return new ItemPropertyDescriptor(
      getRootAdapterFactory(),
      resourceLocator,
      resourceLocator.getString("_UI_" + messageKey + "_feature"),
      resourceLocator.getString("_UI_" + messageKey + "_description"),
      eStructuralFeature,
      eStructuralFeature.isChangeable(),
      false,
      true,
      ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
      null,
      null)
      {
        @Override
        public Collection<?> getChoiceOfValues(Object object)
        {
          EObject eObject = (EObject)object;
          if (feature instanceof EReference)
          {
            EClassifier eType = feature.getEType();
            if (eType == null)
            {
              return null;
            }
            else
            {
              Collection<EObject> result = getReachableObjectsOfType(context, eType);
              if (!feature.isMany())
              {
                result.add(null);
              }
              return ExtendedMetaDataAnnotationValidator.INSTANCE.filterChoiceOfValues(eObject, feature, result);
            }
          }
          else if ("namespace".equals(feature.getName()))
          {
            Collection<EObject> result = getReachableObjectsOfType(context, EcorePackage.Literals.EPACKAGE);
            List<EObject> eObjects = new LinkedList<EObject>(result);
            Resource resource = context.eResource();
            if (resource != null)
            {
              ResourceSet resourceSet = resource.getResourceSet();
              if (resourceSet != null)
              {
                Collection<EObject> visited = new HashSet<EObject>(eObjects);
                EPackage.Registry packageRegistry = resourceSet.getPackageRegistry();
                for (String nsURI : packageRegistry.keySet())
                {
                  collectReachableObjectsOfType(visited, eObjects, packageRegistry.getEPackage(nsURI), EcorePackage.Literals.EPACKAGE);
                }
              }
            }
            return ExtendedMetaDataAnnotationValidator.INSTANCE.filterChoiceOfValues(eObject, feature, eObjects);
          }
          else if ("wildcards".equals(feature.getName()))
          {
            Collection<EObject> ePackages = getReachableObjectsOfType(context, EcorePackage.Literals.EPACKAGE);
            List<EObject> eObjects = new LinkedList<EObject>(ePackages);
            Resource resource = context.eResource();
            if (resource != null)
            {
              ResourceSet resourceSet = resource.getResourceSet();
              if (resourceSet != null)
              {
                Collection<EObject> visited = new HashSet<EObject>(eObjects);
                EPackage.Registry packageRegistry = resourceSet.getPackageRegistry();
                for (String nsURI : packageRegistry.keySet())
                {
                  collectReachableObjectsOfType(visited, eObjects, packageRegistry.getEPackage(nsURI), EcorePackage.Literals.EPACKAGE);
                }
              }
            }
            return ExtendedMetaDataAnnotationValidator.INSTANCE.filterChoiceOfValues(eObject, feature, eObjects);
          }
          else
          {
            return ExtendedMetaDataAnnotationValidator.INSTANCE.filterChoiceOfValues(eObject, feature, super.getChoiceOfValues(object));
          }
        }

        @Override
        public boolean isChoiceArbitrary(Object object)
        {
          String name = feature.getName();
          return "namespace".equals(name) || "wildcards".equals(name);
        }
      };
  }

  @Override
  public IItemPropertyDescriptor createPropertyDescriptorDecorator(
    IItemPropertyDescriptor propertyDescriptor,
    EObject eObject,
    String key,
    EStructuralFeature eStructuralFeature,
    EAnnotation eAnnotation,
    ResourceLocator resourceLocator,
    EditingDomain domain)
  {
    return new ValuePropertyDescriptorDecorator(domain, eObject, propertyDescriptor, eAnnotation);
  }

  private static class ValuePropertyDescriptorDecorator extends ItemPropertyDescriptorDecorator
  {
    private final EAnnotation eAnnotation;

    private EditingDomain domain;

    private ValuePropertyDescriptorDecorator(EditingDomain domain, Object object, IItemPropertyDescriptor itemPropertyDescriptor, EAnnotation eAnnotation)
    {
      super(object, itemPropertyDescriptor);
      this.domain = domain;
      this.eAnnotation = eAnnotation;
    }

    @Override
    public boolean isPropertyUnsettable(Object object)
    {
      return "name".equals(itemPropertyDescriptor.getId(object));
    }

    public void basicSetPropertyValue(Object object, Object value)
    {
      super.setPropertyValue(object, value);
    }

    public void basicResetPropertyValue(Object object)
    {
      super.resetPropertyValue(object);
    }

    @Override
    public void resetPropertyValue(Object object)
    {
      Command command = new ValueChangeCommand(EcoreUtil.getRootContainer(eAnnotation), object, null);
      domain.getCommandStack().execute(command);
    }

    @Override
    public void setPropertyValue(Object object, Object value)
    {
      if ("".equals(value))
      {
        String id = itemPropertyDescriptor.getId(object);
        if ("minInclusive".equals(id) || "maxInclusive".equals(id) || "minExclusive".equals(id) || "maxExclusive".equals(id))
        {
          return;
        }
      }

      Command command = new ValueChangeCommand(EcoreUtil.getRootContainer(eAnnotation), object, value);
      domain.getCommandStack().execute(command);
    }

    protected class ValueChangeCommand extends ChangeCommand
    {
      private final Object object;

      private final Object value;

      private ValueChangeCommand(Notifier notifier, Object object, Object value)
      {
        super(notifier);
        this.object = object;
        this.value = value;
      }

      @Override
      protected void doExecute()
      {
        List<Map.Entry<String, String>> originalPrecedingDetails = null;
        EMap<String, String> details = eAnnotation.getDetails();
        if (object instanceof Map.Entry<?, ?>)
        {
          originalPrecedingDetails = new ArrayList<Map.Entry<String, String>>();
          for (Map.Entry<String, String> entry : details)
          {
            if (entry == object)
            {
              break;
            }
            else
            {
              originalPrecedingDetails.add(entry);
            }
          }
        }

        if (value == null)
        {
          basicResetPropertyValue(object);
        }
        else
        {
          basicSetPropertyValue(object, value);
        }

        if (originalPrecedingDetails != null && !details.contains(object))
        {
          @SuppressWarnings("unchecked")
          Map.Entry<String, String> entry = (Map.Entry<String, String>)object;
          boolean added = false;
          for (int i = 0, size = details.size(); i < size; i++)
          {
            if (!originalPrecedingDetails.contains(details.get(i)))
            {
              details.add(i, entry);
              added = true;
              break;
            }
          }
          if (!added)
          {
            details.add(entry);
          }
          entry.setValue(value == null ? null : value.toString());
        }
      }

      @Override
      public String getLabel()
      {
        return EcoreEditPlugin.INSTANCE.getString("_UI_ChangeExtendedMetaData_text");
      }

      @Override
      public String getDescription()
      {
        return EcoreEditPlugin.INSTANCE.getString("_UI_ChangeExtendedMetaData_description");
      }

      @Override
      public Collection<?> getAffectedObjects()
      {
        if (changeDescription != null)
        {
          EList<EObject> objectsToDetach = changeDescription.getObjectsToDetach();
          if (!objectsToDetach.isEmpty())
          {
            return objectsToDetach;
          }
          return changeDescription.getObjectChanges().keySet();
        }
        else
        {
          return super.getAffectedObjects();
        }
      }
    }
  }
}
