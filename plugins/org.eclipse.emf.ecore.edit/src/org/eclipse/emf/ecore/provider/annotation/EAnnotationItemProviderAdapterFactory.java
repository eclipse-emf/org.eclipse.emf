/**
 * Copyright (c) 2017 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.provider.annotation;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAnnotationValidator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.provider.EAnnotationItemProvider;
import org.eclipse.emf.ecore.provider.EStringToStringMapEntryItemProvider;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.BasicEAnnotationValidator;
import org.eclipse.emf.ecore.util.EcoreSwitch;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.Disposable;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptorDecorator;
import org.eclipse.emf.edit.provider.ReflectiveItemProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;


/**
 * A base class for supporting the specialization of {@link EAnnotationItemProvider} and {@link EStringToStringMapEntryItemProvider}.
 * <p>
 * The {@link EcoreItemProviderAdapterFactory} has a specialized {@link EcoreItemProviderAdapterFactory#adapt(Notifier, Object) adapt} method
 * that uses this class' {@link Registry} to {@link Registry#createEAnnotationItemProviderAdapterFactory(String) create} specialized implementations of this factory
 * depending on the {@link EAnnotation#getSource() annotation's source}.
 * Before {@link EcoreItemProviderAdapterFactory#createEAnnotationAdapter() creating} an EAnnotationItemProvider 
 * or {@link EcoreItemProviderAdapterFactory#createEStringToStringMapEntryAdapter() creating} an EStringToStringMapEntryItemProvider,
 * EcoreItemProviderAdapterFactory will call {@link #createEAnnotationAdapter()} or {@link #createEStringToStringMapEntryAdapter()} on this factory.
 * If this factory returns one, that one will be used.
 * When EcoreItemProviderAdapterFactory creates an instance of this class,
 * it calls {@link #setParentAdapterFactory(EcoreItemProviderAdapterFactory)} so that this factory has a path back to the {@link #getRootAdapterFactory()}.
 * When EcoreItemProviderAdapterFactory is {@link EcoreItemProviderAdapterFactory#dispose() disposed} it will {@link #dispose() dispose} this factory.
 * </p>
 * By default,
 * the {@link #createEAnnotationAdapter()} and the {@link #createEStringToStringMapEntryAdapter()} simply create an EAnnotationItemProvider or an EStringToStringMapEntryItemProvider,
 * passing in this factory to the constructors.
 * <p>
 * All methods are implemented by this class, 
 * but the factory is abstract because it's expected that a derived class will specialize some of the methods.
 * A derived class could override {@link #doCreateEAnnotationAdapter()}, {@link #doCreateEStringToStringMapEntryAdapter()}, or both in order to support specialized stateless adapters.
 * A derived class could instead override {@link #createEAnnotationAdapter()}, {@link #createEStringToStringMapEntryAdapter()}, or both in order to support specialized stateful adapters.
 * However, based on {@link EAnnotationItemProvider#getEAnnotationItemProviderAdapterFactory()} and {@link EStringToStringMapEntryItemProvider#getEAnnotationItemProviderAdapterFactory()},
 * both existing implementations are specialized to delegate back to this factory's 
 * {@link #getPropertyDescriptor(EObject, String, EStructuralFeature, EAnnotation, ResourceLocator) getPropertyDescriptor}, 
 * {@link #getPropertyDescriptors(EObject, EAnnotation, ResourceLocator) getPropertyDescriptors},
 * and {@link #createPropertyDescriptorDecorator(IItemPropertyDescriptor, EObject, String, EStructuralFeature, EAnnotation, ResourceLocator, EditingDomain) createPropertyDescriptorDecorator} methods,
 * so those are the methods most likely to be specialized.
 * </p>
 * <p>
 * It's generally expected that there will be a corresponding {@link EAnnotationValidator} also associated with the annotation source  which which this factory provides specialized adapters.
 * That annotation validator's {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant assistant} is used to drive much of the specialized behavior.
 * </p>
 *
 * @since 2.14
 */
public abstract class EAnnotationItemProviderAdapterFactory extends AdapterFactoryImpl implements ComposeableAdapterFactory, IChangeNotifier, IDisposable
{
  /**
   * An <code>EAnnotationItemProviderAdapterFactory</code> factory that is used by the {@link EAnnotationItemProviderAdapterFactory.Registry}.
   */
  public interface Factory
  {
    /**
     * Creates an annotation item provider adapter factory.
     * @return an annotation item provider adapter factory.
     */
    EAnnotationItemProviderAdapterFactory createEAnnotationItemProviderAdapterFactory();
  }

  /**
   * A map from {@link EAnnotation#getSource() annotation source} to {@link EAnnotationItemProviderAdapterFactory.Factory}.
   */
  public interface Registry extends Map<String, Factory>
  {
    /**
     * Creates an annotation item provider adapter factory for the given {@link EAnnotation#getSource() annotation source}.
     * @param annotationSoure the annotation source.
     * @return an annotation item provider adapter factory or <code>null</code> if there is no registered factory.
     */
    EAnnotationItemProviderAdapterFactory createEAnnotationItemProviderAdapterFactory(String annotationSoure);

    /**
     * The global instance of an annotation item provider adapter factory registry.
     */
    Registry INSTANCE = new Impl();

    /**
     * A trivial registry implementation.
     *
     */
    class Impl extends HashMap<String, EAnnotationItemProviderAdapterFactory.Factory> implements Registry
    {
      private static final long serialVersionUID = 1L;

      public EAnnotationItemProviderAdapterFactory createEAnnotationItemProviderAdapterFactory(String annotationSource)
      {
        EAnnotationItemProviderAdapterFactory.Factory factory = get(annotationSource);
        return factory == null ? null : factory.createEAnnotationItemProviderAdapterFactory();
      }
    }
  }

  /**
   * A reflective implementation of an annotation item provider adapter factory.
   */
  public static class Reflective extends EAnnotationItemProviderAdapterFactory
  {
    private static BasicEAnnotationValidator.Assistant getAssistant(String annotationSource)
    {
      EAnnotationValidator eAnnotationValidator = EAnnotationValidator.Registry.INSTANCE.getEAnnotationValidator(annotationSource);
      if (eAnnotationValidator instanceof BasicEAnnotationValidator)
      {
        return ((BasicEAnnotationValidator)eAnnotationValidator).getAssistant();
      }
      else
      {
        throw new RuntimeException("There is no BasicEAnnotationValidator validator registered for " + annotationSource);
      }
    }

    /**
     * Constructs an instance that uses {@link EcoreEditPlugin#INSTANCE} and the assistant associated with the registered annotation validator of the annotation source.
     * @param annotationSource the annotation source of a {@link org.eclipse.emf.ecore.EAnnotationValidator.Registry#getEAnnotationValidator(String) registered} annotation validator.
     */
    public Reflective(String annotationSource)
    {
      super(EcoreEditPlugin.INSTANCE, getAssistant(annotationSource));
    }

    /**
     * {@inheritDoc}
     * <p>
     * </p>
     */
    @Override
    public String getGroupName(EObject instance)
    {
      return NameHelper.INSTANCE.getTypeText(instance);
    }

    private static class NameHelper extends ReflectiveItemProvider
    {
      private static final NameHelper INSTANCE = new NameHelper();

      private NameHelper()
      {
        super(null);
      }

      @Override
      public String getTypeText(Object object)
      {
        return super.getTypeText(object);
      }
    }
  }

  /**
   * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
   */
  protected final IChangeNotifier changeNotifier = new ChangeNotifier();

  /**
   * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
   */
  protected final Collection<Object> supportedTypes = new ArrayList<Object>();

  /**
   * This is used to implement {@link org.eclipse.emf.edit.provider.IDisposable}.
   */
  protected final Disposable disposable = new Disposable();

  /**
   * The switch that delegates to {@link #createEAnnotationAdapter()} or {@link #createEStringToStringMapEntryAdapter()} as appropriate.
   */
  protected final EcoreSwitch<Adapter> modelSwitch = new EcoreSwitch<Adapter>()
    {
      @Override
      public Adapter caseEAnnotation(EAnnotation object)
      {
        return createEAnnotationAdapter();
      }

      @Override
      public Adapter caseEStringToStringMapEntry(Map.Entry<String, String> object)
      {
        return createEStringToStringMapEntryAdapter();
      }
    };

  /**
   * The assistant provided in the constructor and returned by {@link #getAssistant()}.
   */
  protected final BasicEAnnotationValidator.Assistant assistant;

  /**
   * The resource locator provided in the constructor and returned by {@link #getResourceLocator()}.
   */
  protected final ResourceLocator resourceLocator;

  /**
   * This keeps track of the Ecore item provider adapter factory that delegates to this adapter factory.
   */
  protected EcoreItemProviderAdapterFactory ecoreItemProviderAdapterFactory;

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.emf.ecore.EAnnotation} instances.
   */
  protected Adapter eAnnotationItemProvider;

  /**
   * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
   */
  protected Adapter eStringToStringMapEntryItemProvider;

  /**
   * This constructs an instance given a resource locator and an annotation assistant.
   */
  public EAnnotationItemProviderAdapterFactory(ResourceLocator resourceLocator, BasicEAnnotationValidator.Assistant assistant)
  {
    this.resourceLocator = resourceLocator;
    this.assistant = assistant;

    supportedTypes.add(IEditingDomainItemProvider.class);
    supportedTypes.add(IStructuredItemContentProvider.class);
    supportedTypes.add(ITreeItemContentProvider.class);
    supportedTypes.add(IItemLabelProvider.class);
    supportedTypes.add(IItemPropertySource.class);
  }

  /**
   * Returns the assistant.
   * <p>
   * The assistant is used by the implementations of {@link EAnnotationItemProvider} and {@link EStringToStringMapEntryItemProvider}.
   * </p>
   * @return the assistant.
   * @see EAnnotationItemProvider.SourcePropertyDescriptor#getAssistant()
   * @see EAnnotationItemProvider#addReferencesPropertyDescriptor(Object)
   * @see EStringToStringMapEntryItemProvider#createValuePropertyDescriptor(Map.Entry)
   * @see EStringToStringMapEntryItemProvider#getKeyChoiceOfValues(Map.Entry)
   */
  public BasicEAnnotationValidator.Assistant getAssistant()
  {
    return assistant;
  }

  /**
   * Returns whether {@link EAnnotationItemProvider.SourcePropertyDescriptor#createPropertyValueWrapper(Object, Object)} 
   * should show the {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#createInstance(EClass, EAnnotation) modeled object} in the property tree.
   * <p>
   * This implementation always returns <code>true</code>
   * if {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#getPropertyClasses(EModelElement)} returns more than one class.
   * 
   * @return whether the modeled objects should be explicitly shown in the property tree or should be omitted.
   * @see EAnnotationItemProvider.SourcePropertyDescriptor#createPropertyValueWrapper(Object, Object)
   */
  public boolean isShowInstances(EAnnotation eAnnotation)
  {
    return assistant.getPropertyClasses(eAnnotation.getEModelElement()).size() > 1;
  }

  /**
   * This creates an adapter for a {@link org.eclipse.emf.ecore.EAnnotation} by calling {@link #doCreateEAnnotationAdapter()}.
   * Override this to create stateful adapters.
   * @return the adapter or <code>null</code>.
   */
  public Adapter createEAnnotationAdapter()
  {
    if (eAnnotationItemProvider == null)
    {
      eAnnotationItemProvider = doCreateEAnnotationAdapter();
    }
    return eAnnotationItemProvider;
  }

  /**
   * Creates the adapter for an {@link EAnnotation}.
   * Typically this will be a subclass of {@link EAnnotationItemProvider}.
   * Override this to create stateless adapters.
   * This implementation always simply creates an EAnnotationItemProvider.
   * @return the adapter or <code>null</code>.
   */
  protected Adapter doCreateEAnnotationAdapter()
  {
    return new EAnnotationItemProvider(this);
  }

  /**
   * This creates an adapter for a {@link java.util.Map.Entry} by calling {@link #doCreateEStringToStringMapEntryAdapter()}.
   * Override this to create stateful adapters.
   * @return the adapter or <code>null</code>.
   */
  public Adapter createEStringToStringMapEntryAdapter()
  {
    if (eStringToStringMapEntryItemProvider == null)
    {
      eStringToStringMapEntryItemProvider = doCreateEStringToStringMapEntryAdapter();
    }
    return eStringToStringMapEntryItemProvider;
  }

  /**
   * Creates the adapter for an annotation's {@link EAnnotation#getDetails() details}.
   * Typically this will be a subclass of {@link EStringToStringMapEntryItemProvider}.
   * Override this to create stateless adapters.
   * This implementation always simply creates an EStringToStringMapEntryItemProvider.
   * @return the adapter or <code>null</code>.
   */
  protected Adapter doCreateEStringToStringMapEntryAdapter()
  {
    return new EStringToStringMapEntryItemProvider(this);
  }

  /**
   * This returns the root adapter factory that contains this factory.
   */
  public ComposeableAdapterFactory getRootAdapterFactory()
  {
    return ecoreItemProviderAdapterFactory.getRootAdapterFactory();
  }

  /**
   * This operation is not supported.
   * A EAnnotationItemProviderAdapterFactory should not be directly composed.
   * Instead {@link #setParentAdapterFactory(EcoreItemProviderAdapterFactory)} should be called to set the Ecore item provider adapter factory that uses this factory.
   */
  public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory)
  {
    throw new UnsupportedOperationException("This factory must not be directly a child in a composed adapter factory");
  }

  /**
   * Sets the Ecore item provider adapter factory that uses this factory.
   * @param ecoreItemProviderAdapterFactory the Ecore item provider adapter factory that uses this factory.
   */
  public void setParentAdapterFactory(EcoreItemProviderAdapterFactory ecoreItemProviderAdapterFactory)
  {
    this.ecoreItemProviderAdapterFactory = ecoreItemProviderAdapterFactory;
  }

  /**
   * Returns the resource locator uses by this annotation item provider adapter factory.
   * @return the resource locator.
   */
  public ResourceLocator getResourceLocator()
  {
    return resourceLocator;
  }

  @Override
  public boolean isFactoryForType(Object type)
  {
    return supportedTypes.contains(type) || type == EcorePackage.eINSTANCE;
  }

  @Override
  protected Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }

  /**
   * {@inheritDoc}
   * <p>
   * This implementation substitutes the factory itself as the key for the adapter.
   * </p>
   */
  @Override
  public Adapter adapt(Notifier notifier, Object type)
  {
    return super.adapt(notifier, this);
  }

  @Override
  public Object adapt(Object object, Object type)
  {
    if (isFactoryForType(type))
    {
      Object adapter = super.adapt(object, type);
      if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter)))
      {
        return adapter;
      }
    }

    return null;
  }

  /**
   * {@inheritDoc}
   * <p>
   * This implementation also adds the adapter to the {@link #disposable}, if it's an {@link IDisposable}.
   * </p>
   */
  @Override
  protected void associate(Adapter adapter, Notifier target)
  {
    super.associate(adapter, target);
    if (adapter instanceof IDisposable)
    {
      disposable.add(adapter);
    }
  }

  /**
   * This adds a listener.
   */
  public void addListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.addListener(notifyChangedListener);
  }

  /**
   * This removes a listener.
   */
  public void removeListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.removeListener(notifyChangedListener);
  }

  /**
   * This delegates to {@link #changeNotifier} and to {@link #ecoreItemProviderAdapterFactory}.
   */
  public void fireNotifyChanged(Notification notification)
  {
    changeNotifier.fireNotifyChanged(notification);
    ecoreItemProviderAdapterFactory.fireNotifyChanged(notification);
  }

  /**
   * This disposes all of the item providers created by this factory.
   */
  public void dispose()
  {
    disposable.dispose();
  }

  /**
   * Returns a list of property descriptors for the {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#createInstance(EClass, EAnnotation) modeled object}
   * representing the given annotation.
   * <p>
   * This implementation uses the {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#getApplicableProperties(EObject, EAnnotation) applicable properties} of the modeled object,
   * calling {@link #getPropertyDescriptor(EObject, String, EStructuralFeature, EAnnotation, ResourceLocator) getPropertyDescriptor} to create ach property descriptor.
   * If that method returns one,
   * it calls {@link #createPropertyDescriptorDecorator(IItemPropertyDescriptor, EObject, String, EStructuralFeature, EAnnotation, ResourceLocator, EditingDomain) createPropertyDescriptorDecorator} to decorate it,
   * and then wraps that in a {@link DecategorizingItemPropertyDescritorDecorator}.
   * This implementation is likely sufficient for most purposes.
   * </p>
   * @param eObject the modeled object.
   * @param eAnnotation the annotation that it models.
   * @param resourceLocator the resource locator from which to acquire resources.
   * @return
   */
  public List<IItemPropertyDescriptor> getPropertyDescriptors(EObject eObject, EAnnotation eAnnotation, ResourceLocator resourceLocator)
  {
    List<IItemPropertyDescriptor> result = new ArrayList<IItemPropertyDescriptor>();
    EClass eClass = eObject.eClass();
    Map<String, EStructuralFeature> properties = assistant.getApplicableProperties(eObject, eAnnotation);
    EList<EStructuralFeature> eAllStructuralFeatures = eClass.getEAllStructuralFeatures();
    EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(eAnnotation);
    for (Map.Entry<String, EStructuralFeature> entry : properties.entrySet())
    {
      String key = entry.getKey();
      EStructuralFeature eStructuralFeature = entry.getValue();
      if (eAllStructuralFeatures.contains(eStructuralFeature))
      {
        IItemPropertyDescriptor propertyDescriptor = getPropertyDescriptor(eObject, key, eStructuralFeature, eAnnotation, resourceLocator);
        if (propertyDescriptor != null)
        {
          IItemPropertyDescriptor decoratedPropertyDescriptor = createPropertyDescriptorDecorator(
            propertyDescriptor,
            eObject,
            key,
            eStructuralFeature,
            eAnnotation,
            resourceLocator,
            domain);
          result.add(new DecategorizingItemPropertyDescritorDecorator(eObject, decoratedPropertyDescriptor));
        }
      }
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * Creates a property descriptor for a {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#getApplicableProperties(EObject, EAnnotation) property} 
   * of the {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#createInstance(EClass, EAnnotation) modeled object}
   * corresponding to the given key and feature for the given annotation.
   * <p>
   * This method adapts the object to an {@link IItemPropertySource}, {@link IItemPropertySource#getPropertyDescriptor(Object, Object) fetching} the property descriptor from that, if available.
   * This implementation is sufficient if the model is generated or if the {@link ReflectiveItemProviderAdapterFactory} is available and provides sufficient support.
   * </p>
   * @param eObject the modeled object.
   * @param key the key of the property.
   * @param eStructuralFeature the structure feature of the property.
   * @param eAnnotation the annotation that the modeled object models.
   * @param resourceLocator the resource locator from which to acquire resources.
   * @return a property descriptor or <code>null</code>.
   */
  public IItemPropertyDescriptor getPropertyDescriptor(EObject eObject, String key, EStructuralFeature eStructuralFeature, EAnnotation eAnnotation, ResourceLocator resourceLocator)
  {
    IItemPropertySource itemPropertySource = (IItemPropertySource)getRootAdapterFactory().adapt(eObject, IItemPropertySource.class);
    return itemPropertySource == null ? null : itemPropertySource.getPropertyDescriptor(eObject, eStructuralFeature);
  }

  /**
   * Creates a property descriptor decorator for the property descriptor of a {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#getApplicableProperties(EObject, EAnnotation) property} 
   * of the {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#createInstance(EClass, EAnnotation) modeled object}
   * corresponding to the given key and feature for the given annotation.
   * <p>
   * This implementation creates an instance of {@link ModeledItemPropertyDescriptorDecorator} which uses the assistant to {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#convertPropertyValueToLiteral(EObject, EStructuralFeature, Object) convert}
   * the model object's value to a literal.
   * </p>
   * @param propertyDescriptor the property descriptor to be decorated.
   * @param eObject the modeled object.
   * @param key the key of the property.
   * @param eStructuralFeature the structure feature of the property.
   * @param eAnnotation the annotation that the modeled object models.
   * @param resourceLocator the resource locator from which to acquire resources.
   * @param domain the editing domain to be used to modify the annotation.
   * @return a property descriptor or <code>null</code>.
   */
  public IItemPropertyDescriptor createPropertyDescriptorDecorator(
    IItemPropertyDescriptor propertyDescriptor,
    EObject eObject,
    String key,
    EStructuralFeature eStructuralFeature,
    EAnnotation eAnnotation,
    ResourceLocator resourceLocator,
    EditingDomain domain)
  {
    return new EAnnotationItemProviderAdapterFactory.ModeledItemPropertyDescriptorDecorator(
      propertyDescriptor,
      eObject,
      key,
      eStructuralFeature,
      eAnnotation,
      resourceLocator,
      domain,
      getAssistant());
  }

  /**
   * Returns the group name associated with the given instance object.
   * This will typically be the name of the {@link EObject#eClass() instances's class}.
   * If instances are {@link #isShowInstances(EAnnotation) shown},
   * this name will be shown for the {@link org.eclipse.emf.ecore.provider.EAnnotationItemProvider.SourcePropertyDescriptor#createPropertyValueWrapper(Object, Object) group of properties} associated with that instance.
   * 
   * @param instance
   * @return the group name associated with the given instance object.
   * 
   * @see #isShowInstances(EAnnotation)
   * @see org.eclipse.emf.ecore.provider.EAnnotationItemProvider.SourcePropertyDescriptor#createPropertyValueWrapper(Object, Object)
   */
  public String getGroupName(EObject instance)
  {
    String groupName = getResourceLocator().getString("_UI_" + instance.eClass().getName() + "_type");
    return groupName;
  }

  /**
   * An item property descriptor decorator implementation used by 
   * {@link EAnnotationItemProviderAdapterFactory#createPropertyDescriptorDecorator(IItemPropertyDescriptor, EObject, String, EStructuralFeature, EAnnotation, ResourceLocator, EditingDomain) EAnnotationItemProviderAdapterFactory.createPropertyDescriptorDecorator}
   * to create an {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant assistant}-aware decorator.
   * It is reusable in overrides of EAnnotationItemProviderAdapterFactory.createPropertyDescriptorDecorator.
   */
  public static class ModeledItemPropertyDescriptorDecorator extends ItemPropertyDescriptorDecorator
  {
    /**
     * The {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#getApplicableProperties(EObject, EAnnotation) key} of a property of the modeled object.
     */
    protected final String key;

    /**
     * The {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#getApplicableProperties(EObject, EAnnotation) feature} of a property of the modeled object.
     */
    protected final EStructuralFeature eStructuralFeature;

    /**
     * The annotation modeled by the modeled object.
     */
    protected final EAnnotation eAnnotation;

    /**
     * The editing domain of the annotation.
     */
    protected final EditingDomain domain;

    /**
     * The assistant used for {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#convertPropertyValueToLiteral(EObject, EStructuralFeature, Object) value conversion}.
     */
    protected final BasicEAnnotationValidator.Assistant assistant;

    public ModeledItemPropertyDescriptorDecorator(
      IItemPropertyDescriptor propertyDescriptor,
      EObject eObject,
      String key,
      EStructuralFeature eStructuralFeature,
      EAnnotation eAnnotation,
      ResourceLocator resourceLocator,
      EditingDomain domain,
      BasicEAnnotationValidator.Assistant assistant)
    {
      super(eObject, propertyDescriptor);
      this.key = key;
      this.eStructuralFeature = eStructuralFeature;
      this.eAnnotation = eAnnotation;
      this.domain = domain;
      this.assistant = assistant;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation checks whether the resource of the {@link #eAnnotation annotation} is {@link EditingDomain#isReadOnly(Resource) read-only} in {@link #domain}.
     * </p>
     */
    @Override
    public boolean canSetProperty(Object object)
    {
      Resource resource = eAnnotation.eResource();
      return resource != null && !domain.isReadOnly(resource);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation checks if the key has a non-null value in the {@link #eAnnotation annotation} details.
     * </p>
     */
    @Override
    public boolean isPropertySet(Object thisObject)
    {
      EMap<String, String> details = eAnnotation.getDetails();
      return details.get(key) != null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation checks if the key has a <code>null</code> value in the {@link #eAnnotation annotation} details and the {@link #getPropertyValue(Object) value of the property} is non-null.
     * Returning <code>true</code> for this allows the properties view to set the implicit value explicitly via the tool bar button.
     * </p>
     */
    @Override
    public boolean isPropertyUnsettable(Object object)
    {
      EMap<String, String> details = eAnnotation.getDetails();
      return details.get(key) == null && getPropertyValue(object) != null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation removes the detail corresponding to the {@link #key key} from the {@link #eAnnotation annotation}.
     * </p>
     */
    @Override
    public void resetPropertyValue(Object thisObject)
    {
      EMap<String, String> details = eAnnotation.getDetails();
      for (Map.Entry<String, String> entry : details)
      {
        if (key.equals(entry.getKey()))
        {
          domain.getCommandStack().execute(RemoveCommand.create(domain, eAnnotation, EcorePackage.Literals.EANNOTATION__DETAILS, entry));
          return;
        }
      }
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#convertPropertyValueToLiteral(EObject, EStructuralFeature, Object) converts} the value to a literal
     * and either updates the key's existing entry's value or create a new entry for the key and value.
     * </p>
     */
    @Override
    public void setPropertyValue(Object object, Object value)
    {
      String literal = assistant.convertPropertyValueToLiteral((EObject)this.object, eStructuralFeature, value);
      EMap<String, String> details = eAnnotation.getDetails();
      for (Map.Entry<String, String> entry : details)
      {
        if (key.equals(entry.getKey()))
        {
          // Don't set the literal value if it is the same as the current value.
          String entryValue = entry.getValue();
          if (entryValue == null ? literal != null : !entryValue.equals(literal))
          {
            domain.getCommandStack().execute(SetCommand.create(domain, entry, EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__VALUE, literal));
          }
          return;
        }
      }

      EObject entry = EcoreUtil.create(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY);
      entry.eSet(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__KEY, key);
      entry.eSet(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__VALUE, literal);
      domain.getCommandStack().execute(AddCommand.create(domain, eAnnotation, EcorePackage.Literals.EANNOTATION__DETAILS, entry));
    }
  }

  /**
   * A property descriptor created by {@link EAnnotationItemProviderAdapterFactory#getPropertyDescriptors(EObject, EAnnotation, ResourceLocator) EAnnotationItemProviderAdapterFactory#getPropertyDescriptors}
   * and used by {@link EAnnotationItemProvider.SourcePropertyDescriptor#createPropertyValueWrapper(Object, Object)}.
   * It specialized {@link #getCategory(Object)} to return null but provides {@link #getCategory()} to give access to the original category.
   */
  public final static class DecategorizingItemPropertyDescritorDecorator extends ItemPropertyDescriptorDecorator
  {
    /**
     * Creates an instance.
     * @param object the object of the property descriptor being decorated.
     * @param itemPropertyDescriptor the property descriptor to be decorated.
     */
    public DecategorizingItemPropertyDescritorDecorator(Object object, IItemPropertyDescriptor itemPropertyDescriptor)
    {
      super(object, itemPropertyDescriptor);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation always returns <code>null</code>.
     */
    @Override
    public String getCategory(Object thisObject)
    {
      return null;
    }

    /**
     * Returns the group of properties into which this one should be placed based on the underlying property descriptor being decorated.
     * @return the group of properties into which this one should be placed.
     */
    public String getCategory()
    {
      return super.getCategory(this.object);
    }
  }

  /**
   * A convenient implementation of a property source with an explicit list of property descriptors that can be {@link #add(IItemPropertyDescriptor) populated} after construction.
   */
  public static final class Group implements IItemPropertySource
  {
    /**
     * The {@link #getEditableValue(Object) value} for this property source.
     */
    protected Object propertyValue;

    /**
     * The list of property descriptors as returned by {@link #getPropertyDescriptors()}.
     */
    protected final List<IItemPropertyDescriptor> itemPropertyDescriptors = new ArrayList<IItemPropertyDescriptor>();;

    /**
     * Creates an instance that wraps the given property value.
     * @param propertyValue the {@link #getEditableValue(Object) value} for this property source.
     */
    public Group(Object propertyValue)
    {
      this.propertyValue = propertyValue;
    }

    /**
     * Adds the given property descriptor to the {@link #getPropertyDescriptors() property descriptors}.
     * @param propertyDescriptor the property descriptor to add.
     */
    public void add(IItemPropertyDescriptor propertyDescriptor)
    {
      itemPropertyDescriptors.add(propertyDescriptor);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation simply delegates to {@link #getPropertyDescriptors()}.
     * </p>
     */
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
    {
      return itemPropertyDescriptors;
    }

    /**
     * Returns the underlying list of property descriptors.
     * This list can be directly modified by the caller.
     * @return the underlying list of property descriptors.
     */
    public List<IItemPropertyDescriptor> getPropertyDescriptors()
    {
      return itemPropertyDescriptors;
    }

    /**
     * {@inheritDoc}
     */
    public IItemPropertyDescriptor getPropertyDescriptor(Object object, Object propertyID)
    {
      for (IItemPropertyDescriptor itemPropertyDescriptor : getPropertyDescriptors(object))
      {
        if (propertyID.equals(itemPropertyDescriptor.getId(object)) || propertyID.equals(itemPropertyDescriptor.getFeature(object)))
        {
          return itemPropertyDescriptor;
        }
      }
      return null;
    }

    /**
     * {@inheritDoc}
     */
    public Object getEditableValue(Object object)
    {
      return propertyValue;
    }

    @Override
    public String toString()
    {
      return propertyValue == null ? "null" : propertyValue.toString();
    }
  }
}
