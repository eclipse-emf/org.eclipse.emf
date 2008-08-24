/**
 * <copyright>
 *
 * Copyright (c) 2006-2007 IBM Corporation and others.
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
 * $Id: EGenericTypeItemProvider.java,v 1.10 2008/08/24 14:33:07 emerks Exp $
 */
package org.eclipse.emf.ecore.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.ecore.EGenericType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EGenericTypeItemProvider
  extends EObjectItemProvider
  implements	
    IEditingDomainItemProvider,	
    IStructuredItemContentProvider,	
    ITreeItemContentProvider,	
    IItemLabelProvider,	
    IItemPropertySource		
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EGenericTypeItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addERawTypePropertyDescriptor(object);
      addETypeParameterPropertyDescriptor(object);
      addEClassifierPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the ERaw Type feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addERawTypePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EGenericType_eRawType_feature"),
         getString("_UI_EGenericType_eRawType_description"),
         EcorePackage.Literals.EGENERIC_TYPE__ERAW_TYPE,
         false,
         false,
         true,
         null,
         null,
         new String[] {
          "org.eclipse.ui.views.properties.expert"
         }));
  }

  /**
   * This adds a property descriptor for the EType Parameter feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addETypeParameterPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new EModelElementItemProvider.ItemPropertyDescriptorWithUniqueChoiceOfValueLabels
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EGenericType_eTypeParameter_feature"),
         getString("_UI_EGenericType_eTypeParameter_description"),
         EcorePackage.Literals.EGENERIC_TYPE__ETYPE_PARAMETER,
         true,
         false,
         true,
         null,
         null,
         null)
       {
         @Override
         public boolean canSetProperty(Object object)
         {
           return super.canSetProperty(object) && !(((EGenericType)object).eContainer() instanceof EClass);
         }
         
         @Override
         public Collection<?> getChoiceOfValues(Object object)
         {
           Collection<Object> result = new ArrayList<Object>();
           result.add(null);
           for (EObject eObject = (EObject)object; eObject != null; eObject = eObject.eContainer())
           {
             if (eObject instanceof EClassifier)
             {
               result.addAll(((EClassifier)eObject).getETypeParameters());
             }
             else if (eObject instanceof EOperation)
             {
               result.addAll(((EOperation)eObject).getETypeParameters());
             }
             result.remove(eObject);
           }
           uniqueNameMap = computeUniqueLabels(object, result);

           return result;
         }
         
         @Override
         public void setPropertyValue(final Object object, Object value)
         {
           EditingDomain editingDomain = getEditingDomain(object);
           if (editingDomain == null)
           {
             super.setPropertyValue(object, value);
           }
           else
           {
             CompoundCommand command = 
               new CompoundCommand()
               {
                 @Override
                public Collection<?> getAffectedObjects()
                {
                  return Collections.singleton(object);
                }
               };
             command.appendIfCanExecute
               (SetCommand.create(editingDomain, object, EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS, Collections.EMPTY_LIST));
             command.appendIfCanExecute
               (SetCommand.create(editingDomain, object, EcorePackage.Literals.EGENERIC_TYPE__ECLASSIFIER, null));
             command.appendIfCanExecute
               (SetCommand.create(editingDomain, object, EcorePackage.Literals.EGENERIC_TYPE__EUPPER_BOUND, null));
             command.appendIfCanExecute
               (SetCommand.create(editingDomain, object, EcorePackage.Literals.EGENERIC_TYPE__ELOWER_BOUND, null));
             command.append
               (SetCommand.create(editingDomain, object, EcorePackage.Literals.EGENERIC_TYPE__ETYPE_PARAMETER, value));
             editingDomain.getCommandStack().execute(command);
           }
         }
       });
  }

  /**
   * This adds a property descriptor for the EClassifier feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addEClassifierPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new EModelElementItemProvider.ItemPropertyDescriptorWithUniqueChoiceOfValueLabels
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EGenericType_eClassifier_feature"),
         getString("_UI_EGenericType_eClassifier_description"),
         EcorePackage.Literals.EGENERIC_TYPE__ECLASSIFIER,
         true,
         false,
         true,
         null,
         null,
         null)
       {
         @Override
         public Collection<?> getChoiceOfValues(Object object)
         {
           EGenericType eGenericType = (EGenericType)object;
           // Filter out types that aren't permitted.
           //
           Collection<Object> result = new ArrayList<Object>(super.getChoiceOfValues(object));

           EObject container = eGenericType.eContainer();
           if (!(container instanceof EReference))
           {
             for (Object classifier :  EcorePackage.eINSTANCE.getEClassifiers())
             {
               if (!result.contains(classifier))
               {
                 result.add(classifier);
               }
             }
           }
           if (!result.contains(EcorePackage.Literals.EOBJECT))
           {
             result.add(EcorePackage.Literals.EOBJECT);
           }

           if (container instanceof EAttribute)
           {
             for (Iterator<Object> i = result.iterator(); i.hasNext(); )
             {
               if (i.next() instanceof EClass)
               {
                 i.remove();
               }
             }
           }
           else if (container instanceof EReference)
           {
             for (Iterator<Object> i = result.iterator(); i.hasNext(); )
             {
               if (i.next() instanceof EDataType)
               {
                 i.remove();
               }
             }
           }
           else if (container instanceof EClass)
           {
             for (Iterator<Object> i = result.iterator(); i.hasNext(); )
             {
               Object choice = i.next();
               if (choice instanceof EDataType ||  choice == container || choice != null && ((EClass)choice).getEAllSuperTypes().contains(container))
               {
                 i.remove();
               }
             }
             // Avoid allowing choices that will lead to duplicates.
             //
             for (EGenericType eGenericSuperType : ((EClass)container).getEGenericSuperTypes())
             {
               EClassifier eClassifier = eGenericSuperType.getERawType();
                if (eClassifier != null)
                {
                  result.remove(eClassifier);
                }
             }
           }
           else if (container instanceof EOperation)
           {
             //  Avoid allowing a choice that will lead to duplicates.
             //
             if (eGenericType.eContainmentFeature() == EcorePackage.Literals.EOPERATION__EGENERIC_EXCEPTIONS)
             {
               for (EGenericType eGenericException : ((EOperation)container).getEGenericExceptions())
               {
                 EClassifier eClassifier = eGenericException.getERawType();
                 if (eClassifier != null)
                 {
                   result.remove(eClassifier);
                 }
               }
             }
           }
           
           uniqueNameMap = computeUniqueLabels(object, result);

           return result;
         }

         @Override
         public void setPropertyValue(final Object object, Object value)
         {
           EditingDomain editingDomain = getEditingDomain(object);
           if (editingDomain == null)
           {
             super.setPropertyValue(object, value);
           }
           else
           {
             CompoundCommand command = 
               new CompoundCommand()
               {
                 @Override
                public Collection<?> getAffectedObjects()
                {
                  return Collections.singleton(object);
                }
               };
             command.appendIfCanExecute
               (SetCommand.create(editingDomain, object, EcorePackage.Literals.EGENERIC_TYPE__ETYPE_PARAMETER, null));
             
             // Ensure that there are enough type arguments to match the number of type parameters.
             //
             int typeParameterCount = value == null ? 0 : ((EClassifier)value).getETypeParameters().size();
             List<EGenericType> typeArguments = new ArrayList<EGenericType>(((EGenericType)object).getETypeArguments());
             while (typeArguments.size() > typeParameterCount)
             {
               typeArguments.remove(typeParameterCount);
             }
             while (typeArguments.size() < typeParameterCount)
             {
               typeArguments.add(EcoreFactory.eINSTANCE.createEGenericType());
             }

             command.appendIfCanExecute
               (SetCommand.create(editingDomain, object, EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS, typeArguments));
             command.appendIfCanExecute
               (SetCommand.create(editingDomain, object, EcorePackage.Literals.EGENERIC_TYPE__EUPPER_BOUND, null));
             command.appendIfCanExecute
               (SetCommand.create(editingDomain, object, EcorePackage.Literals.EGENERIC_TYPE__ELOWER_BOUND, null));
             command.append
               (SetCommand.create(editingDomain, object, EcorePackage.Literals.EGENERIC_TYPE__ECLASSIFIER, value));
             editingDomain.getCommandStack().execute(command);
           }
         }
       });
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(EcorePackage.Literals.EGENERIC_TYPE__EUPPER_BOUND);
      childrenFeatures.add(EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS);
      childrenFeatures.add(EcorePackage.Literals.EGENERIC_TYPE__ELOWER_BOUND);
    }
    return childrenFeatures;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  protected EStructuralFeature getChildFeature(Object object, Object child)
  {
    EGenericType eGenericType = (EGenericType)object;
    if (child instanceof EObject)
    {
      EObject eObject = (EObject)child;
      if (eObject.eContainer() == eGenericType)
      {
        // If it's really a contained child, return the feature for it.
        //
        return eObject.eContainingFeature();
      }
    }
    if (eGenericType.getEClassifier() != null)
    {
      // You can only add type arguments if there are type parameters not yet used up.
      //
      return 
        eGenericType.getETypeArguments().size() < eGenericType.getEClassifier().getETypeParameters().size() ?
          EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS :
          null;
    }
    else if (eGenericType.getETypeParameter() != null)
    {
      // You cannot add any children for a type parameter.
      //
      return null;
    }
    else if (eGenericType.eContainer() instanceof EGenericType)
    {
      // Only if you are contained by a generic type can you have bounds,
      // and only if there is no classifier or type parameter
      // and in that case the lower bound is returned only if that's the one that's set.
      //
      return 
        eGenericType.getELowerBound() != null ? 
           EcorePackage.Literals.EGENERIC_TYPE__EUPPER_BOUND : 
           EcorePackage.Literals.EGENERIC_TYPE__EUPPER_BOUND;
    }
    else
    {
      // Otherwise you can't make it a child.
      //
      return null;
    }
  }

  /**
   * This returns EGenericType.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public Object getImage(Object object)
  {
    EGenericType eGenericType = (EGenericType)object;
    EReference eContainmentFeature = eGenericType.eContainmentFeature();
    return 
      overlayImage
        (object, 
         getResourceLocator().getImage
          (eContainmentFeature == EcorePackage.Literals.ECLASS__EGENERIC_SUPER_TYPES ?
            "full/obj16/EGenericSuperType" :
            eContainmentFeature == EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE ?
              "full/obj16/EGenericElementType" :
              eContainmentFeature == EcorePackage.Literals.EOPERATION__EGENERIC_EXCEPTIONS ?
                "full/obj16/EGenericException" :
                eContainmentFeature == EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS ?
                  "full/obj16/EGenericTypeArgument" :
                  eContainmentFeature == EcorePackage.Literals.EGENERIC_TYPE__ELOWER_BOUND ||
                  eContainmentFeature == EcorePackage.Literals.EGENERIC_TYPE__EUPPER_BOUND ?
                    "full/obj16/EGenericWildcard" :
                    "full/obj16/EGenericType"));
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public String getText(Object object)
  {
    EGenericType eGenericType = (EGenericType)object;
    return getText(eGenericType);
  }
  
  static String getText(EGenericType eGenericType)
  {
    ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
    if (eTypeParameter != null)
    {
      String name = eTypeParameter.getName();
      return name == null ? "null" : name;
    }
    else
    {
      EClassifier eClassifier = eGenericType.getEClassifier();
      if (eClassifier != null)
      {
        List<EGenericType> eTypeArguments = eGenericType.getETypeArguments();
        if (eTypeArguments.isEmpty())
        {
          String name = eClassifier.getName();
          return name == null ? "null" : name;
        }
        else
        {
          StringBuilder result = new StringBuilder();
          result.append(eClassifier.getName());
          result.append('<');
          for (Iterator<EGenericType> i = eTypeArguments.iterator(); ; )
          {
            result.append(getText(i.next()));
            if (i.hasNext())
            {
              result.append(", ");
            }
            else
            {
              break;
            }
          }
          result.append('>');
          return result.toString();
        }
      }
      else
      {
        EGenericType eUpperBound = eGenericType.getEUpperBound();
        if (eUpperBound != null)
        {
          return "? extends " + getText(eUpperBound);
        }
        else
        {
          EGenericType eLowerBound = eGenericType.getELowerBound();
          if (eLowerBound != null)
          {
            return "? super " + getText(eLowerBound);
          }
          else
          {
            return "?";
          }
        }
      }
    }
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(EGenericType.class))
    {
      case EcorePackage.EGENERIC_TYPE__ECLASSIFIER:
      case EcorePackage.EGENERIC_TYPE__ETYPE_PARAMETER:
      {
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        for (EObject container = ((EObject)notification.getNotifier()).eContainer(); container != null; container = container.eContainer())
        {
          fireNotifyChanged(new ViewerNotification(notification, container, false, true));
          if (container instanceof EOperation || container instanceof EStructuralFeature || container instanceof EClassifier)
          { 
            break;
          }
        }
        return;
      } 
      case EcorePackage.EGENERIC_TYPE__EUPPER_BOUND:
      case EcorePackage.EGENERIC_TYPE__ETYPE_ARGUMENTS:
      case EcorePackage.EGENERIC_TYPE__ELOWER_BOUND:
      {
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, true));
        for (EObject container = ((EObject)notification.getNotifier()).eContainer(); container != null; container = container.eContainer())
        {
          fireNotifyChanged(new ViewerNotification(notification, container, false, true));
          if (container instanceof EOperation || container instanceof EStructuralFeature || container instanceof EClassifier)
          { 
            break;
          }
        }
        return;
      }
    }
    
    super.notifyChanged(notification);
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);

    EGenericType eGenericType = (EGenericType)object;

    if (eGenericType.eContainmentFeature() == EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS &&
          eGenericType.getEClassifier() == null && 
          eGenericType.getETypeParameter() == null)
    {
      newChildDescriptors.add
        (createChildParameter
          (EcorePackage.Literals.EGENERIC_TYPE__EUPPER_BOUND,
           EcoreFactory.eINSTANCE.createEGenericType()));
      newChildDescriptors.add
        (createChildParameter
          (EcorePackage.Literals.EGENERIC_TYPE__ELOWER_BOUND,
           EcoreFactory.eINSTANCE.createEGenericType()));
    }

    if (eGenericType.getEClassifier() != null && !eGenericType.getEClassifier().getETypeParameters().isEmpty())
    {
      newChildDescriptors.add
        (createChildParameter
          (EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS,
           EcoreFactory.eINSTANCE.createEGenericType()));
    }
  }

  /**
   * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection)
  {
    return
      feature == EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS ? 
        getString("_UI_EGenericTypeArgument_label") :
        feature == EcorePackage.Literals.EGENERIC_TYPE__EUPPER_BOUND ?
          getString("_UI_EGenericUpperBoundType_label") :
          feature == EcorePackage.Literals.EGENERIC_TYPE__ELOWER_BOUND ?
            getString("_UI_EGenericLowerBoundType_label"):
            super.getCreateChildText(owner, feature, child, selection);
  }
  
  @Override
  protected Command createCreateChildCommand(
    EditingDomain domain,
    EObject owner,
    EStructuralFeature feature,
    Object value,
    int index,
    Collection<?> collection)
  {
    return 
      new CreateChildCommand(domain, owner, feature, value, index, collection, this)
      {
        @Override
        protected Command createCommand()
        {
          if ((feature == EcorePackage.Literals.EGENERIC_TYPE__ELOWER_BOUND || feature == EcorePackage.Literals.EGENERIC_TYPE__EUPPER_BOUND))
          {
            EGenericType eGenericType = (EGenericType)owner;
            if (eGenericType.getELowerBound() != null || eGenericType.getEUpperBound() != null)
            {
              return UnexecutableCommand.INSTANCE;
            }
          }
          return super.createCommand();
        }
        
      };
  }

}
