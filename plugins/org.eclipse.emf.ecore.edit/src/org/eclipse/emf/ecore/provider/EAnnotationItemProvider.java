/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.provider;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAnnotationValidator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.provider.annotation.EAnnotationItemProviderAdapterFactory;
import org.eclipse.emf.ecore.provider.annotation.EAnnotationItemProviderAdapterFactory.Group;
import org.eclipse.emf.ecore.provider.annotation.EcoreAnnotationItemProviderAdapterFactory;
import org.eclipse.emf.ecore.util.BasicEAnnotationValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;


/**
 * This is the item provider adapter for a {@link org.eclipse.emf.ecore.EAnnotation} object.
 * <!-- begin-user-doc -->
 * <p>
 * This implementation is {@link EAnnotationItemProviderAdapterFactory}-aware using {@link #getEAnnotationItemProviderAdapterFactory()} to drive specialized behavior.
 * I.e., it behaves differently when created by {@link EcoreItemProviderAdapterFactory} versus when created by {@link EAnnotationItemProviderAdapterFactory}.
 * </p>
 * <!-- end-user-doc -->
 * @generated
 */
public class EAnnotationItemProvider extends EModelElementItemProvider
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   */
  public EAnnotationItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * Gets the root factory, which must be a {@link ComposeableAdapterFactory}.
   *
   * @since 2.14
   */
  @Override
  protected ComposeableAdapterFactory getRootAdapterFactory()
  {
    return ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory();
  }

  /**
   * Returns the {@link EAnnotationItemProviderAdapterFactory} if the adapter factory is of that type, <code>null</code> otherwise.
   * @return the annotation item provider adapter factory or <code>null</code>
   * @since 2.14
   */
  protected EAnnotationItemProviderAdapterFactory getEAnnotationItemProviderAdapterFactory()
  {
    return adapterFactory instanceof EAnnotationItemProviderAdapterFactory ? (EAnnotationItemProviderAdapterFactory)adapterFactory : null;
  }

  /**
   * Returns the {@link EAnnotationItemProviderAdapterFactory#getAssistant() assistant} of the {@link #getEAnnotationItemProviderAdapterFactory() annotation item provider adapter factory}, if available.
   * @return the assistant or <code>null</code>.
   * @since 2.14
   */
  protected BasicEAnnotationValidator.Assistant getAssistant()
  {
    EAnnotationItemProviderAdapterFactory eAnnotationItemProviderAdapterFactory = getEAnnotationItemProviderAdapterFactory();
    return eAnnotationItemProviderAdapterFactory == null ? null : eAnnotationItemProviderAdapterFactory.getAssistant();
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * @since 2.14
   * <!-- end-user-doc -->
   * @generated
   */
  protected List<IItemPropertyDescriptor> getPropertyDescriptorsGen(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addSourcePropertyDescriptor(object);
      addReferencesPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * {@inheritDoc}
   * <p>
   * This implementation is specialized to clear the cached {@link #itemPropertyDescriptors descriptors} if this adapter was created by an {@link EAnnotationItemProviderAdapterFactory}.
   * <p>
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (getEAnnotationItemProviderAdapterFactory() != null)
    {
      itemPropertyDescriptors = null;
    }

    List<IItemPropertyDescriptor> propertyDescriptors = getPropertyDescriptorsGen(object);
    return propertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Source feature.
   * <!-- begin-user-doc -->
   * This implementation is specialized to create a {@link SourcePropertyDescriptor}.
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addSourcePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(new SourcePropertyDescriptor());
  }

  /**
   * A specialized property descriptor for the {@link EAnnotation#getSource() annotation source} feature.
   * <p>
   * This implementation that is {@link EAnnotationItemProviderAdapterFactory}-aware,
   * using its {@link EAnnotationItemProviderAdapterFactory#getAssistant() assistant} when available.
   * It specializes {@link #createPropertyValueWrapper(Object, Object) property value wrapper creation} to provide assistant-driven nested property descriptors.
   * </p>
   *
   * @since 2.14
   */
  protected class SourcePropertyDescriptor extends ItemPropertyDescriptor
  {
    public SourcePropertyDescriptor()
    {
      super(
        getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_EAnnotation_source_feature"),
        getString("_UI_EAnnotation_source_description"),
        EcorePackage.Literals.EANNOTATION__SOURCE,
        true,
        false,
        true,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null);
    }

    /**
     * Creates a property value wrapper for the given object's property value.
     * <p>
     * This implementation creates a hierarchy of property descriptors if the {@link #getAssistant() assistant} is available,
     * if the assistant considers the annotation {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#isValidLocation(EAnnotation) valid at this location},
     * and if the assistant returns {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#getPropertyClasses(org.eclipse.emf.ecore.EModelElement) one or more modeled annotation classes}.
     * In that case, it {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#createInstance(EClass, EAnnotation) creates} the modeled object of each class,
     * {@link EAnnotationItemProviderAdapterFactory#isShowInstances(EAnnotation) optionally including} it in the tree,
     * and if the property descriptors are {@link org.eclipse.emf.ecore.provider.annotation.EAnnotationItemProviderAdapterFactory.DecategorizingItemPropertyDescritorDecorator#getCategory() categorized},
     * it creates an additional tree nesting per category.
     * Otherwise it simply creates simply delegates to <code>super</code>.
     * <p>
     * @param object the object.
     * @param propertyValue the property value of that object.
     */
    @Override
    protected Object createPropertyValueWrapper(Object object, Object propertyValue)
    {
      BasicEAnnotationValidator.Assistant assistant = getAssistant();
      if (assistant != null)
      {
        final EAnnotation eAnnotation = (EAnnotation)object;
        if (assistant.isValidLocation(eAnnotation))
        {
          List<EClass> propertyClasses = assistant.getPropertyClasses(eAnnotation.getEModelElement());
          if (!propertyClasses.isEmpty())
          {
            EcoreAnnotationItemProviderAdapterFactory.Group group = new EcoreAnnotationItemProviderAdapterFactory.Group(propertyValue);
            Map<String, Group> categories = new HashMap<String, Group>();
            EAnnotationItemProviderAdapterFactory eAnnotationItemProviderAdapterFactory = getEAnnotationItemProviderAdapterFactory();
            boolean showInstances = eAnnotationItemProviderAdapterFactory.isShowInstances(eAnnotation);
            boolean onlyMisc = true;
            for (EClass propertyClass : propertyClasses)
            {
              EObject instance = assistant.createInstance(propertyClass, eAnnotation);
              Group targetGroup = group;
              if (showInstances)
              {
                Group classGroup = new Group(instance);
                String groupName = eAnnotationItemProviderAdapterFactory.getGroupName(instance);
                GroupPropertyDescriptor groupPropertyDescriptor = new GroupPropertyDescriptor(groupName, groupName, classGroup);
                group.add(groupPropertyDescriptor);
                targetGroup = classGroup;
                categories.clear();
                onlyMisc = true;
              }

              List<IItemPropertyDescriptor> propertyDescriptors = eAnnotationItemProviderAdapterFactory.getPropertyDescriptors(
                instance,
                eAnnotation,
                eAnnotationItemProviderAdapterFactory.getResourceLocator());
              for (IItemPropertyDescriptor propertyDescriptor : propertyDescriptors)
              {
                String category = null;
                if (propertyDescriptor instanceof EAnnotationItemProviderAdapterFactory.DecategorizingItemPropertyDescritorDecorator)
                {
                  category = ((EAnnotationItemProviderAdapterFactory.DecategorizingItemPropertyDescritorDecorator)propertyDescriptor).getCategory();
                }

                Group categoryGroup = categories.get(category);
                if (categoryGroup == null)
                {
                  categoryGroup = new Group(category);
                  categories.put(category, categoryGroup);
                  if (category == null)
                  {
                    category = EcoreEditPlugin.INSTANCE.getString("_UI_Misc_property_category");
                  }
                  else
                  {
                    onlyMisc = false;
                  }

                  targetGroup.add(new GroupPropertyDescriptor(category, category, categoryGroup));
                }

                categoryGroup.add(propertyDescriptor);
              }

              if (showInstances && onlyMisc)
              {
                List<IItemPropertyDescriptor> groupPropertyDescriptors = targetGroup.getPropertyDescriptors();
                groupPropertyDescriptors.clear();
                if (!categories.isEmpty())
                {
                  groupPropertyDescriptors.addAll(categories.values().iterator().next().getPropertyDescriptors());
                }
              }
            }

            if (!showInstances && onlyMisc)
            {
              List<IItemPropertyDescriptor> groupPropertyDescriptors = group.getPropertyDescriptors();
              groupPropertyDescriptors.clear();
              if (!categories.isEmpty())
              {
                groupPropertyDescriptors.addAll(categories.values().iterator().next().getPropertyDescriptors());
              }
            }

            return group;
          }
        }
      }

      return super.createPropertyValueWrapper(object, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(Object object, Object value)
    {
      EAnnotation eAnnotation = (EAnnotation)object;
      String source = eAnnotation.getSource();
      String strippedValue = stripToNull((String)value);
      if (strippedValue == null ? source != null : !strippedValue.equals(source))
      {
        super.setPropertyValue(object, strippedValue);
      }
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation collects all the existing {@link EAnnotation#getSource() annotation sources} in the containing model,
     * adds to that the annotation source of any {@link org.eclipse.emf.ecore.EAnnotationValidator.Registry registered} annotation validator
     * that returns <code>true</code> for {@link EAnnotationValidator#isValidLocation(EAnnotation)},
     * removing the annotation source for any annotation validator that returns <code>false</code>.
     * </p>
     */
    @Override
    public Collection<?> getChoiceOfValues(Object object)
    {
      EAnnotation eAnnotation = (EAnnotation)object;
      List<Object> result = new UniqueEList<Object>();

      // Gather up all well-formed annotation sources already present in the tree on other annotations.
      for (Iterator<EObject> i = EcoreUtil.getRootContainer(eAnnotation).eAllContents(); i.hasNext();)
      {
        EObject eObject = i.next();
        if (eObject instanceof EAnnotation)
        {
          EAnnotation otherEAnnotation = (EAnnotation)eObject;
          String otherSource = otherEAnnotation.getSource();
          if (otherSource != null && EcoreValidator.INSTANCE.validateEAnnotation_WellFormedSourceURI(otherEAnnotation, null, null))
          {
            result.add(otherSource);
          }
        }
      }

      EObject eContainer = eAnnotation.eContainer();
      for (EObject eObject = eContainer; eObject != null; eObject = eObject.eContainer())
      {
        if (eObject instanceof EPackage)
        {
          EPackage ePackage = (EPackage)eObject;
          if (eContainer instanceof EDataType)
          {
            for (String conversionDelegate : EcoreUtil.getConversionDelegates(ePackage))
            {
              result.add(conversionDelegate);
            }
          }
          else if (eContainer instanceof EOperation)
          {
            for (String invocationDelegate : EcoreUtil.getInvocationDelegates(ePackage))
            {
              result.add(invocationDelegate);
            }
          }
          else if (eContainer instanceof EStructuralFeature)
          {
            for (String settingDelegate : EcoreUtil.getSettingDelegates(ePackage))
            {
              result.add(settingDelegate);
            }
          }
        }
      }

      for (String annotationSource : EAnnotationValidator.Registry.INSTANCE.keySet().toArray(new String[0]))
      {
        EAnnotationValidator eAnnotationValidator = EAnnotationValidator.Registry.INSTANCE.getEAnnotationValidator(annotationSource);
        if (eAnnotationValidator.isValidLocation(eAnnotation))
        {
          result.add(annotationSource);
        }
        else
        {
          result.remove(annotationSource);
        }
      }

      String source = eAnnotation.getSource();
      if (source != null)
      {
        result.add(source);
      }

      return result;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation is specialized to always return <code>true</code>.
     * </p>
     */
    @Override
    public boolean isChoiceArbitrary(Object object)
    {
      return true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This is specialized to do additional validation
     * checking to ensure that the source URI is {@link EcoreValidator#validateEAnnotation_WellFormedSourceURI(EAnnotation, org.eclipse.emf.common.util.DiagnosticChain, Map) well-formed}.
     * </p>
     */
    @Override
    public ValueHandler getValueHandler(Object object)
    {
      return new DataTypeValueHandler((EDataType)feature.getEType())
        {
          @Override
          protected Diagnostic validate(EDataType eDataType, Object instance)
          {
            EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
            eAnnotation.setSource((String)instance);
            BasicDiagnostic diagnostic = new BasicDiagnostic();
            EcoreValidator.INSTANCE.validateEAnnotation_WellFormedSourceURI(eAnnotation, diagnostic, null);
            return diagnostic;
          }
        };
    }
  }

  /**
   * This adds a property descriptor for the References feature.
   * <!-- begin-user-doc -->
   * This implementation is specialized to use the {@link #getAssistant() assistant} when available.
   * If the assistant indicates that {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#isReferencesSupported(EAnnotation) references aren't supported}
   * and the {@link EAnnotation#getReferences() references} are empty,
   * the property descriptor is not added.
   * Otherwise an instance of {@link ReferencesPropertyDescriptor} is created.
   * @see ReferencesPropertyDescriptor
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addReferencesPropertyDescriptor(Object object)
  {
    BasicEAnnotationValidator.Assistant assistant = getAssistant();
    if (assistant != null)
    {
      EAnnotation eAnnotation = (EAnnotation)object;
      if (!assistant.isReferencesSupported(eAnnotation) && eAnnotation.getReferences().isEmpty())
      {
        return;
      }
    }
    itemPropertyDescriptors.add(new ReferencesPropertyDescriptor());
  }

  /**
   * A specialized property descriptor for the {@link EAnnotation#getReferences() annotation references} feature.
   * Specialized {@link EAnnotationItemProvider annotation item providers}
   * created by an {@link EAnnotationItemProviderAdapterFactory annotation item provider adapter factory}
   * can override the {@link #getChoiceOfValues(Object) value choices} to provide more restricted choices.
   * But they won't generally need to do that because this implementation delegates to the {@link EAnnotationItemProvider#getAssistant() assistant}.
   *
   * @since 2.14
   */
  protected class ReferencesPropertyDescriptor extends ItemPropertyDescriptor
  {
    public ReferencesPropertyDescriptor()
    {
      super(
        getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_EAnnotation_references_feature"),
        getString("_UI_EAnnotation_references_description"),
        EcorePackage.Literals.EANNOTATION__REFERENCES,
        true,
        false,
        true,
        null,
        null,
        null);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation is specialized to delegate to the {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#getValidReferences(EAnnotation, Collection) assistant}.
     * </p>
     */
    @Override
    public Collection<?> getChoiceOfValues(Object object)
    {
      Collection<?> result = super.getChoiceOfValues(object);
      BasicEAnnotationValidator.Assistant assistant = getAssistant();
      if (assistant != null)
      {
        result = assistant.getValidReferences((EAnnotation)object, result);
      }
      return result;
    }
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @SuppressWarnings("unchecked")
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    List<EStructuralFeature> childrenFeatures = getChildrenFeatures();
    if (childrenFeatures == null)
    {
      childrenFeatures = (List<EStructuralFeature>)super.getChildrenFeatures(object);
      childrenFeatures.add(EcorePackage.Literals.EANNOTATION__DETAILS);
      childrenFeatures.add(EcorePackage.Literals.EANNOTATION__CONTENTS);
    }
    return childrenFeatures;
  }

  /**
   * This implementation is specialized to use the {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant assistant} if available
   * to avoid adding the child if it would produce an invalid annotations.
   * @see org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#getValidAnnotations(EAnnotation, Collection) Assistant.getValidAnnotations(EAnnotation, Collection)
   * @see org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#getValidContents(EAnnotation, Collection) Assistant.getValidContents(EAnnotation, Collection)
   * @generated NOT
   */
  @Override
  protected EStructuralFeature getChildFeature(Object object, Object child)
  {
    EStructuralFeature childFeature = super.getChildFeature(object, child);
    BasicEAnnotationValidator.Assistant assistant = getAssistant();
    if (assistant != null)
    {
      EAnnotation eAnnotation = (EAnnotation)object;
      if (child instanceof EAnnotation)
      {
        EAnnotation nestedEAnnotation = (EAnnotation)child;
        if (assistant.getValidAnnotations(eAnnotation, Collections.singletonList(nestedEAnnotation)).contains(nestedEAnnotation))
        {
          return EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS;
        }
        else if (childFeature == EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS)
        {
          return null;
        }
      }
      if (child instanceof EObject)
      {
        if (assistant.getValidContents(eAnnotation, Collections.singletonList((EObject)child)).contains(child))
        {
          return EcorePackage.Literals.EANNOTATION__CONTENTS;
        }
        else if (childFeature == EcorePackage.Literals.EANNOTATION__CONTENTS)
        {
          return null;
        }
      }
    }
    return childFeature;
  }

  /**
   * This returns EAnnotation.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object)
  {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/EAnnotation"));
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
    EAnnotation eAnnotation = (EAnnotation)object;
    StringBuilder result = new StringBuilder();
    String source = eAnnotation.getSource();
    if (source != null)
    {
      int index = getParent(eAnnotation) instanceof EAnnotation ? -1 : source.lastIndexOf("/");
      if (index == -1)
      {
        result.append(source);
      }
      else
      {
        result.append(source.substring(index + 1));
      }
    }
    return result.toString();
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(EAnnotation.class))
    {
      case EcorePackage.EANNOTATION__SOURCE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case EcorePackage.EANNOTATION__DETAILS:
      case EcorePackage.EANNOTATION__CONTENTS:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
        return;
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
    BasicEAnnotationValidator.Assistant assistant = getAssistant();
    if (assistant != null)
    {
      EAnnotation eAnnotation = (EAnnotation)object;
      Collection<? extends EObject> validContents = assistant.getValidContents(eAnnotation, Collections.<EObject> emptyList());
      for (EObject validContent : validContents)
      {
        newChildDescriptors.add(createChildParameter(EcorePackage.Literals.EANNOTATION__CONTENTS, validContent));
      }
      Collection<? extends EAnnotation> validAnnotations = assistant.getValidAnnotations(eAnnotation, Collections.<EAnnotation> emptyList());
      for (EAnnotation validAnnotation : validAnnotations)
      {
        newChildDescriptors.add(createChildParameter(EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS, validAnnotation));
      }
    }

    newChildDescriptors.add(createChildParameter(EcorePackage.Literals.EANNOTATION__DETAILS, EcoreFactory.eINSTANCE.create(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY)));
  }

  @Override
  public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection)
  {
    String result = super.getCreateChildText(owner, feature, child, selection);
    if (feature == EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS)
    {
      EAnnotation nestedEAnnotation = (EAnnotation)child;
      String text = getText(nestedEAnnotation);
      if (text.length() != 0)
      {
        result += " - " + text;
      }
    }
    return result;
  }

  private static final class GroupPropertyDescriptor implements IItemPropertyDescriptor
  {
    private String label;

    private String description;

    private Object itemPropertySource;

    public GroupPropertyDescriptor(String label, String description, IItemPropertySource itemPropertySource)
    {
      this.label = label;
      this.description = description;
      this.itemPropertySource = itemPropertySource;
    }

    public void setPropertyValue(Object object, Object value)
    {
    }

    public void resetPropertyValue(Object object)
    {
    }

    public boolean isSortChoices(Object object)
    {
      return false;
    }

    public boolean isPropertySet(Object object)
    {
      return false;
    }

    public boolean isMultiLine(Object object)
    {
      return false;
    }

    public boolean isMany(Object object)
    {
      return false;
    }

    public boolean isCompatibleWith(Object object, Object anotherObject, IItemPropertyDescriptor anotherPropertyDescriptor)
    {
      return false;
    }

    public Object getPropertyValue(Object object)
    {
      return itemPropertySource;
    }

    public IItemLabelProvider getLabelProvider(Object object)
    {
      return new IItemLabelProvider()
        {
          public String getText(Object object)
          {
            return "";
          }

          public Object getImage(Object object)
          {
            return ItemPropertyDescriptor.NO_VALUE_IMAGE;
          }
        };
    }

    public String getId(Object object)
    {
      return label;
    }

    public Object getHelpContextIds(Object object)
    {
      return null;
    }

    public String[] getFilterFlags(Object object)
    {
      return null;
    }

    public Object getFeature(Object object)
    {
      return label;
    }

    public String getDisplayName(Object object)
    {
      return label;
    }

    public String getDescription(Object object)
    {
      return description;
    }

    public Collection<?> getChoiceOfValues(Object object)
    {
      return null;
    }

    public String getCategory(Object object)
    {
      return null;
    }

    public boolean canSetProperty(Object object)
    {
      return false;
    }
  }
}
