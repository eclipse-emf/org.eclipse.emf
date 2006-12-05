/**
 * <copyright>
 * </copyright>
 *
 * $Id: ETypeParameterItemProvider.java,v 1.1 2006/12/05 20:26:32 emerks Exp $
 */
package org.eclipse.emf.ecore.provider;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.ecore.ETypeParameter} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ETypeParameterItemProvider
  extends ENamedElementItemProvider
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
  public ETypeParameterItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

    }
    return itemPropertyDescriptors;
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Collection getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(EcorePackage.Literals.ETYPE_PARAMETER__EBOUNDS);
    }
    return childrenFeatures;
  }
  
  @Override
  public String getCreateChildText(Object owner, Object feature, Object child, Collection selection)
  {
    return 
      feature == EcorePackage.Literals.ETYPE_PARAMETER__EBOUNDS ?
      getString("_UI_EGenericBoundType_label") :
      super.getCreateChildText(owner, feature, child, selection);
  }

  /**
   * This returns ETypeParameter.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getImage(Object object)
  {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/ETypeParameter"));
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String getText(Object object)
  {
    return getText((ETypeParameter)object);
  }
  
  static String getText(ETypeParameter eTypeParameter)
  {
    if (eTypeParameter.getEBounds().isEmpty())
    {
      String name = eTypeParameter.getName();
      return name == null ? "null" : name;
    }
    else
    {
      StringBuilder result = new StringBuilder();
      result.append(eTypeParameter.getName());
      result.append(" extends ");
      for (Iterator<EGenericType> i = eTypeParameter.getEBounds().iterator(); i.hasNext(); )
      {
        result.append(EGenericTypeItemProvider.getText(i.next()));
        if (i.hasNext())
        {
          result.append(" & ");
        }
      }
      return result.toString();
    }
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(ETypeParameter.class))
    {
      case EcorePackage.ENAMED_ELEMENT__NAME:
      {
        fireNotifyChanged(new ViewerNotification(notification, ((EObject)notification.getNotifier()).eContainer(), true, true));
        break;
      }
      case EcorePackage.ETYPE_PARAMETER__EBOUNDS:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, true));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
   * describing all of the children that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);

    newChildDescriptors.add
      (createChildParameter
        (EcorePackage.Literals.ETYPE_PARAMETER__EBOUNDS,
         EcoreFactory.eINSTANCE.createEGenericType()));
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceLocator getResourceLocator()
  {
    return EcoreEditPlugin.INSTANCE;
  }

}
