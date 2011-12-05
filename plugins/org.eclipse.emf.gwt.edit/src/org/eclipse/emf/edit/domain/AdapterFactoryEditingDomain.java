/**
 * <copyright> 
 *
 * Copyright (c) 2002-2010 IBM Corporation and others.
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
 * $Id: AdapterFactoryEditingDomain.java,v 1.4 2011/12/05 14:40:30 emerks Exp $
 */
package org.eclipse.emf.edit.domain;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyToClipboardCommand;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.CutToClipboardCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.OverrideableCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.ReplaceCommand;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;



/**
 * This class implements an editing domain by delegating to adapters that implement 
 * {@link org.eclipse.emf.edit.provider.IEditingDomainItemProvider}.
 */
public class AdapterFactoryEditingDomain implements EditingDomain
{
  /**
   * This class is an adapter than implements {@link IEditingDomainProvider}.
   */
  public static class EditingDomainProvider extends AdapterImpl implements IEditingDomainProvider
  {
    /**
     * Keeps track of the editing domain.
     */
    protected EditingDomain editingDomain;

    public EditingDomainProvider(EditingDomain editingDomain)
    {
      this.editingDomain = editingDomain;
    }

    public EditingDomain getEditingDomain()
    {
      return editingDomain;
    }

    @Override
    public boolean isAdapterForType(Object type)
    {
      return type == IEditingDomainProvider.class;
    }
  }

  /**
   * Returns whether the object is, contains, or wraps something that likely represents 
   * a stale {@link Resource#unload() unloaded} {@link EObject#eIsProxy() object}.
   * It's best to stop using unloaded objects entirely because they ought to be garbage collected
   * and should be replaced by their {@link AdapterFactoryEditingDomain#resolve(Collection) resolved} result.
   * @since 2.4
   */
  static public boolean isStale(Object object)
  {
    if (object instanceof IWrapperItemProvider)
    {
      IWrapperItemProvider wrapper = (IWrapperItemProvider)object;
      return isStale(wrapper.getValue()) || isStale(wrapper.getOwner());
    }
    else if (object instanceof Collection<?>)
    {
      for (Object item : (Collection<?>)object)
      {
        if (isStale(item))
        {
          return true;
        }
      }
      return false;
    }
    else if (object instanceof Object[])
    {
      for (Object item : (Object[])object)
      {
        if (isStale(item))
        {
          return true;
        }
      }
      return false;
    }
    else if (object instanceof EObject)
    {
      EObject eObject = (EObject)object;
      return eObject.eIsProxy() && eObject.eAdapters().isEmpty();
    }
    else if (object instanceof FeatureMap.Entry)
    {
      return isStale(((FeatureMap.Entry)object).getValue());
    }
    else if (object == null)
    {
      return false;
    }
    else
    {
      return false;
    }
  }

  /**
   * This returns the editing domain of the given EMF object, or null, if it can't be determined.
   * This is implemented by checking whether the {@link org.eclipse.emf.ecore.resource.ResourceSet} of the object 
   * implements {@link IEditingDomainProvider}
   * and returns that result or null.
   *
   * <p>
   * Just as for {@link #getEditingDomainFor(java.lang.Object) getEditingDomainFor(Object)},
   * it is recommended that you always keep an editing domain instance available through some other means.
   */
  static public EditingDomain getEditingDomainFor(EObject object)
  {
    if (object != null)
    {
      Resource resource = object.eResource();
      if (resource != null)
      {
        IEditingDomainProvider editingDomainProvider =
          (IEditingDomainProvider)EcoreUtil.getExistingAdapter(resource, IEditingDomainProvider.class);
        if (editingDomainProvider != null)
        {
          return editingDomainProvider.getEditingDomain();
        }
        else
        {
          ResourceSet resourceSet = resource.getResourceSet();
          if (resourceSet instanceof IEditingDomainProvider)
          {
            EditingDomain editingDomain = ((IEditingDomainProvider)resourceSet).getEditingDomain();
            return editingDomain;
          }
          else if (resourceSet != null)
          {
            editingDomainProvider = (IEditingDomainProvider)EcoreUtil.getExistingAdapter(resourceSet, IEditingDomainProvider.class);
            if (editingDomainProvider != null)
            {
              return editingDomainProvider.getEditingDomain();
            }
          }
        }
      }
    }

    return null;
  }

  /**
   * This returns the editing domain for the given arbitrary object, or null, if it can't be determined.
   * It is recommended that you always work directly with an EditingDomain instance whenever possible.
   * This is implemented to checks if the object itself implements {@link org.eclipse.emf.edit.domain.IEditingDomainProvider}
   * and returns that result.
   * Otherwise it checks if it is valid to call 
   * {@link #getEditingDomainFor(org.eclipse.emf.ecore.EObject) getEditingDomainFor(EObject)} 
   * and returns that result or null.
   *
   * <p>
   * It is recommended that you always keep an editing domain instance available through some other means;
   * this should only be used to implement things such as a global popup action for some object;
   * in such a cases such as that the editing domain returned here
   * may well be one that belongs to some editor you know nothing about,
   * which is what you want.
   */
  static public EditingDomain getEditingDomainFor(Object object)
  {
    if (object instanceof IEditingDomainProvider)
    {
      EditingDomain editingDomain = ((IEditingDomainProvider)object).getEditingDomain();
      return editingDomain;
    }
    else if (object instanceof EObject)
    {
      EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor((EObject)object);
      return editingDomain;
    }
    else if (object instanceof FeatureMap.Entry)
    {
      return getEditingDomainFor(((FeatureMap.Entry)object).getValue());
    }
    else if (object instanceof IWrapperItemProvider)
    {
      return getEditingDomainFor(((IWrapperItemProvider)object).getValue());
    }
    else
    {
      return null;
    }
  }

  /**
   * This is a convenient way to determine the adapter to which editing domain methods are delegated for a given object,
   * by providing nothing more than the object itself.
   * It is recommended that you always work directly with an EditingDomain instance whenever possible.
   * If the object is an EMF object, 
   * this is implemented to call {@link #getEditingDomainFor(java.lang.Object) getEditingDomainFor(Object)}
   * and to return the adapter returned from the domain's adapter factory.
   * If the object itself is an IEditingDomainItemProvider, it returns the object.
   * Otherwise, it returns null.
   */
  static public IEditingDomainItemProvider getEditingDomainItemProviderFor(Object object)
  {
    if (object instanceof EObject)
    {
      EObject eObject = (EObject)object;
      EditingDomain editingDomain = getEditingDomainFor(eObject);
      if (editingDomain instanceof AdapterFactoryEditingDomain)
      {
        object = ((AdapterFactoryEditingDomain)editingDomain).getAdapterFactory().adapt(eObject, IEditingDomainItemProvider.class);
      }
    }

    return 
      object instanceof IEditingDomainItemProvider ? 
        (IEditingDomainItemProvider)object : 
        object instanceof IWrapperItemProvider ? 
          getEditingDomainItemProviderFor(((IWrapperItemProvider)object).getValue()) : 
          object instanceof FeatureMap.Entry ? 
            getEditingDomainItemProviderFor(((FeatureMap.Entry)object).getValue()) : 
            null;
  }

  /**
   * This is an implementation of a context that knows about this editing domain.
   * It is used to help implement 
   * {@link #getEditingDomainFor(java.lang.Object) getEditingDomainFor(Object)}
   * and {@link #getEditingDomainFor(org.eclipse.emf.ecore.EObject) getEditingDomainFor(EObject)}
   * An instance of this is created if needed in the constructor.
   * 
   */
  protected class AdapterFactoryEditingDomainResourceSet extends ResourceSetImpl implements IEditingDomainProvider
  {
    public AdapterFactoryEditingDomainResourceSet()
    {
      super();
      // setResourceFactoryRegister(new ExtensibleContextResourceFactoryRegister());
      //EATM setResourceFactoryRegister(new ContextResourceFactoryRegister());
    }

    public EditingDomain getEditingDomain()
    {
      return AdapterFactoryEditingDomain.this;
    }
  }

  /**
   * This is the adapter factory used to create the adapter to which calls are delegated.
   */
  protected AdapterFactory adapterFactory;

  /**
   * This is the command stack that was passed into the constructor.
   */
  protected CommandStack commandStack;

  /**
   * This is the resource set used to contain all created and loaded resources.
   */
  protected ResourceSet resourceSet;

  /**
   * This is the current clipboard.
   */
  protected Collection<Object> clipboard;

  /**
   * This controls whether or not copy command optimizations are safe in this domain.
   */
  protected boolean optimizeCopy = true;

  /**
   * This controls whether the domain is read only.
   */
  protected Map<Resource, Boolean> resourceToReadOnlyMap;

  /**
   * Create an instance from the adapter factory, and the specialized command stack.
   */
  public AdapterFactoryEditingDomain(AdapterFactory adapterFactory, CommandStack commandStack)
  {
    this.adapterFactory = adapterFactory;
    this.commandStack = commandStack;
    this.resourceSet = new AdapterFactoryEditingDomainResourceSet();
  }

  /**
   * Create an instance from the adapter factory, the specialized command stack, and the map used to maintain read only state.
   */
  public AdapterFactoryEditingDomain(AdapterFactory adapterFactory, CommandStack commandStack, Map<Resource, Boolean> resourceToReadOnlyMap)
  {
    this.adapterFactory = adapterFactory;
    this.commandStack = commandStack;
    this.resourceSet = new AdapterFactoryEditingDomainResourceSet();
    this.resourceToReadOnlyMap = resourceToReadOnlyMap;
  }

  /**
   * Create an instance from the adapter factory, the specialized command stack, and the specialized resource set.
   * If the resource set's context is null, one will be created here;
   * otherwise, the existing context should implement {@link org.eclipse.emf.edit.domain.IEditingDomainProvider}.
   */
  public AdapterFactoryEditingDomain(AdapterFactory adapterFactory, CommandStack commandStack, ResourceSet resourceSet)
  {
    this.adapterFactory = adapterFactory;
    this.commandStack = commandStack;
    this.resourceSet = resourceSet;

/*
    if (resourceSet.getContext() == null)
    {
      Context context = new AdapterFactoryEditingDomainContext();
      resourceSet.setContext(context);
    }
*/
  }

  /**
   * This returns the adapter factory used by this domain.
   */
  public AdapterFactory getAdapterFactory()
  {
    return adapterFactory;
  }

  /**
   * This sets the adapter factory after the domain is already created.
   */
  public void setAdapterFactory(AdapterFactory adapterFactory)
  {
    this.adapterFactory = adapterFactory;
  }

  /**
   * This is a convenience method to create a resource, you could use the resource set returned by {@link #getResourceSet} directly.
   */
  public Resource createResource(String fileNameURI)
  {
    URI uri = URI.createURI(fileNameURI);
    Resource resource = resourceSet.createResource(uri);
    return resource;
  }

  /**
   * This is a convenience method to load a resource, you could use the resource set returned by {@link #getResourceSet} directly.
   */
  public Resource loadResource(String fileNameURI)
  {
    try 
    {
      Resource resource = resourceSet.getResource(URI.createURI(fileNameURI), true);
      return resource;
    } 
    catch (Exception exception) 
    {
      EMFEditPlugin.INSTANCE.log(exception);
    }

    return null;
  }

  /**
   * This returns the resource set used to contain all created and loaded resources.
   */
  public ResourceSet getResourceSet()
  {
    return resourceSet;
  }

  /**
   * This delegates to 
   * {@link org.eclipse.emf.edit.provider.IEditingDomainItemProvider#createCommand IEditingDomainItemProvider.createCommand}.
   */
  public Command createCommand(Class<? extends Command> commandClass, CommandParameter commandParameter)
  {
    // If the owner parameter is set, we delegate to the owner's adapter
    //
    Object owner = commandParameter.getOwner();
    if (commandClass == CopyToClipboardCommand.class)
    {
      return new CopyToClipboardCommand(this, commandParameter.getCollection());
    }
    else if (commandClass == PasteFromClipboardCommand.class)
    {
      return new PasteFromClipboardCommand
        (this, commandParameter.getOwner(), commandParameter.getFeature(), commandParameter.getIndex(), getOptimizeCopy());
    }
    else if (commandClass == CutToClipboardCommand.class)
    {
      return new CutToClipboardCommand
        (this, RemoveCommand.create(this, commandParameter.getOwner(), commandParameter.getFeature(), commandParameter.getCollection()));
    }
    else if (commandClass == DeleteCommand.class)
    {
      return new DeleteCommand(this, commandParameter.getCollection());
    }
    else if (owner != null)
    {
      // If there is an adapter of the correct type...
      //
      IEditingDomainItemProvider editingDomainItemProvider = 
        (IEditingDomainItemProvider)
          adapterFactory.adapt(owner, IEditingDomainItemProvider.class);

      return
        editingDomainItemProvider != null ?
          editingDomainItemProvider.createCommand(owner, this, commandClass, commandParameter) :
        new ItemProviderAdapter(null).createCommand(owner, this, commandClass, commandParameter);
    }
    else 
    {
      // If command has no owner specified
      //
      if (commandClass == RemoveCommand.class)
      {
        // For RemoveCommand, we will find the owner by calling EditingDomain.getParent() on the object(s) being removed.
        //
        CompoundCommand removeCommand = new CompoundCommand(CompoundCommand.MERGE_COMMAND_ALL);

        List<Object> objects = new ArrayList<Object>(commandParameter.getCollection());
        while (!objects.isEmpty())
        {
          // We will iterate over the whole collection, removing some as we go.
          //
          ListIterator<Object> remainingObjects = objects.listIterator();

          // Take the first object, and remove it.
          //
          Object object = remainingObjects.next();
          remainingObjects.remove();

          // Determine the object's parent.
          //
          Object parent = getParent(object);

          if (parent != null)
          {
            // Now we want to find all the other objects with this same parent.
            // So we can collection siblings together and give the parent control over their removal.
            //
            List<Object> siblings = new ArrayList<Object>();
            siblings.add(object);

            while (remainingObjects.hasNext())
            {
              // Get the next object and check if it has the same parent.
              //
              Object otherObject = remainingObjects.next();
              Object otherParent = getParent(otherObject);
              if (otherParent == parent)
              {
                // Remove the object and add it as a sibling.
                //
                remainingObjects.remove();
                siblings.add(otherObject);
              }
            }

            // We will now create a command with this implied parent
            //
            removeCommand.append(createCommand(RemoveCommand.class, new CommandParameter(parent, null, siblings)));
          }
          else if (object != null)
          {
            // The parent is null, which implies a top-level removal, so create a self-removing command.
            //
            removeCommand.append(createCommand(RemoveCommand.class, new CommandParameter(object, null, Collections.singleton(object))));
          }
        } 

        return removeCommand.unwrap();
      } 
      else if (commandClass == ReplaceCommand.class)
      {
        Object obj = commandParameter.getValue();
        Object parent = (obj == null) ? null : getParent(obj);
        if (parent == null) parent = obj;
        return createCommand(ReplaceCommand.class, new CommandParameter(parent, null, obj, commandParameter.getCollection()));
      }
      else if (commandClass == CreateChildCommand.class)
      {
        // For CreateChildCommand, we will find the owner by calling EditingDomain.getParent() on the first selected object
        Collection<?> sel = commandParameter.getCollection();
        Object parent = sel == null ? null : getParent(sel.iterator().next());
        if (parent == null)
        {
          return UnexecutableCommand.INSTANCE;
        }
        return createCommand(CreateChildCommand.class, new CommandParameter(parent, commandParameter.getFeature(), commandParameter.getValue(), commandParameter.getCollection(), commandParameter.getIndex()));
      }
    }

    /*
    try
    {
      Constructor<? extends Command> constructor = commandClass.getConstructor(EditingDomain.class, CommandParameter.class);
      Command command = constructor.newInstance(new Object [] { this, commandParameter });
      return command;
    }
    catch (IllegalAccessException exception)
    {
      // Ignore.
    }
    catch (InstantiationException exception)
    {
      // Ignore.
    }
    catch (NoSuchMethodException exception)
    {
      // Ignore.
    }
    catch (InvocationTargetException exception)
    {
      // Ignore.
    }
    */

    return UnexecutableCommand.INSTANCE;
  }

  /**
   * This just returns null, since this is an optional feature that we don't support here.
   */
  public Command createOverrideCommand(OverrideableCommand command)
  {
    return null;
  }

  /**
   * This returns the command stack provided in the constructor.
   */
  public CommandStack getCommandStack() 
  {
    return commandStack;
  }

  /**
   * This delegates to 
   * {@link org.eclipse.emf.edit.provider.IEditingDomainItemProvider#getChildren IEditingDomainItemProvider.getChildren}.
   */
  public Collection<?> getChildren(Object object)
  {
    // If there is an adapter of the correct type...
    //
    IEditingDomainItemProvider editingDomainItemProvider = 
      (IEditingDomainItemProvider)
        adapterFactory.adapt(object, IEditingDomainItemProvider.class);

    return
      editingDomainItemProvider != null ?
        editingDomainItemProvider.getChildren(object) :
        Collections.emptyList();
  }

  /**
   * This delegates to 
   * {@link org.eclipse.emf.edit.provider.IEditingDomainItemProvider#getParent IEditingDomainItemProvider.getParent}.
   */
  public Object getParent(Object object)
  {
    // If there is an adapter of the correct type...
    //
    IEditingDomainItemProvider editingDomainItemProvider = 
      (IEditingDomainItemProvider)
        adapterFactory.adapt(object, IEditingDomainItemProvider.class);

    return
      editingDomainItemProvider != null ?
        editingDomainItemProvider.getParent(object) :
        null;
  }

  public Object getRoot(Object object)
  {
    Object result = object;
    for (Object parent = getParent(object); parent != null; parent = getParent(parent))
    {
      result = parent;
    }
    return result;
  }

  /**
   * Each {@link #isStale(Object)} in the list is unwrapped,
   * {@link EcoreUtil#resolve(EObject, ResourceSet) resolved}, 
   * and rewrapped before it's added to the result;
   * Other objects are passed through unchecked.
   * @since 2.4
   */
  public List<?> resolve(Collection<?> objects)
  {
    List<Object> result = new UniqueEList<Object>();
    for (Object object : objects)
    {
      if (isStale(object))
      {
        Object unwrappedObject = unwrap(object);
        if (unwrappedObject instanceof EObject)
        {
          EObject resolvedEObject = EcoreUtil.resolve((EObject)unwrappedObject, resourceSet);
          if (resolvedEObject != unwrappedObject)
          {
            result.add(object instanceof IWrapperItemProvider ? getWrapper(resolvedEObject) : resolvedEObject);
          }
        }
      }
      else
      {
        result.add(object);
      }
    }
    return result;
  }

  public Object getWrapper(Object object)
  {
    return getWrapper(object, this);
  }

  /**
   * Determine a wrapper associated with the given object in the given editing domain.
   * @param object the object for which to determine a wrapper.
   * @param domain the domain in which to find the wrapper.
   * @return a wrapper or the object itself.
   * @since 2.5
   */
  public static Object getWrapper(Object object, EditingDomain domain)
  {
    if (object != null)
    {
      for (Iterator<?> i = domain.treeIterator(domain.getRoot(object)); i.hasNext(); )
      {
        Object element = i.next();
        Object elementValue = element; 
        while (elementValue instanceof IWrapperItemProvider)
        {
          elementValue = ((IWrapperItemProvider)elementValue).getValue();
        }
        if (elementValue == object)
        {
          return element;
        }
        else if (elementValue instanceof FeatureMap.Entry)
        {
          Object entryValue = ((FeatureMap.Entry)elementValue).getValue();
          if (entryValue == object)
          {
            return element;
          }
        }
      }
    }
    return object;
  }

  public static Object unwrap(Object object)
  {
    while (object instanceof IWrapperItemProvider)
    {
      object = ((IWrapperItemProvider)object).getValue();
    }
    if (object instanceof FeatureMap.Entry)
    {
      object = ((FeatureMap.Entry)object).getValue();
    }
    return object;
  }

  /**
   * This delegates to 
   * {@link org.eclipse.emf.edit.provider.IEditingDomainItemProvider#getNewChildDescriptors IEditingDomainItemProvider.getNewChildDescriptors}.
   */
  public Collection<?> getNewChildDescriptors(Object object, Object sibling)
  {
    // If no object is specified, but an existing sibling is, the object is
    // its parent.
    //
    if (object == null)
    {
      object = getParent(sibling);
    }

    // If there is an adapter of the correct type...
    //
    IEditingDomainItemProvider editingDomainItemProvider = 
      (IEditingDomainItemProvider)
        adapterFactory.adapt(object, IEditingDomainItemProvider.class);

    return
      editingDomainItemProvider != null ?
        editingDomainItemProvider.getNewChildDescriptors(object, this, sibling) :
        Collections.emptyList();
  }

  /**
   * This returns the clipboard of the editing domain.
   */
  public Collection<Object> getClipboard()
  {
    return clipboard;
  }

  /**
   * This sets the clipboard of the editing domain.
   */
  public void setClipboard(Collection<Object> clipboard)
  {
    this.clipboard = clipboard;
  }

  /**
   * This returns whether or not copy command optimizations are safe in this domain.
   */
  public boolean getOptimizeCopy()
  {
    return optimizeCopy;
  }

  /**
   * This sets whether or not copy command optimizations are safe in this domain.
   */
  public void setOptimizeCopy(boolean optimizeCopy)
  {
    this.optimizeCopy = optimizeCopy;
  }

  /**
   * Returns the map of resource to a Boolean value indicating whether the resource is read only.
   */
  public Map<Resource, Boolean> getResourceToReadOnlyMap()
  {
    return resourceToReadOnlyMap;
  }

  /**
   * Set the map of resource to a Boolean value indicating whether the resource is read only.
   */
  public void setResourceToReadOnlyMap(Map<Resource, Boolean> resourceToReadOnlyMap)
  {
    this.resourceToReadOnlyMap = resourceToReadOnlyMap;
  }

  /**
   * This returns whether the resource is read only.
   */
  public boolean isReadOnly(Resource resource)
  {
    if (resourceToReadOnlyMap == null)
    {
      return false;
    }
    else
    {
      Boolean result = resourceToReadOnlyMap.get(resource);
      if (result == null && resource != null)
      {
        Map<?, ?> options = Collections.singletonMap(URIConverter.OPTION_REQUESTED_ATTRIBUTES, Collections.singleton(URIConverter.ATTRIBUTE_READ_ONLY));
        Map<String, ?> attributes = (resource.getResourceSet() == null ? resourceSet : resource.getResourceSet()).getURIConverter().getAttributes(resource.getURI(), options);
        result = Boolean.TRUE.equals(attributes.get(URIConverter.ATTRIBUTE_READ_ONLY));
        resourceToReadOnlyMap.put(resource, result);
      }
      return Boolean.TRUE.equals(result);
    }
  }

  /**
   * Returns whether to expect that the resource corresponding to the given URI form will be read only.
   * @deprecated this method is no longer called by {@link #isReadOnly(Resource)}
   */
  @Deprecated
  protected boolean isReadOnlyURI(URI uri)
  {
    if (uri.isArchive())
    {
      return isReadOnlyURI(URI.createURI(uri.authority()));
    }

    return !uri.isPlatformResource() && (uri.isRelative() || !uri.isFile());
  }

  /**
   * This implements an tree iterator that iterates over an object, it's domain children, their domain children, and so on.
   */
  public static class DomainTreeIterator<E> extends AbstractTreeIterator<E>
  {
    private static final long serialVersionUID = 1L;

    /**
     * This is the domain that defines the tree structured.
     */
    protected EditingDomain domain;

    /**
     * This constructs tree iterator that iterates over an object, it's domain children, their domain children, and so on.
     */
    public DomainTreeIterator(EditingDomain domain, E object)
    {
      super(object);
      this.domain = domain;
    }

    /**
     * This constructs tree iterator that iterates over an object (but only if includeRoot is true), 
     * it's domain children, their domain children, and so on.
     */
    public DomainTreeIterator(EditingDomain domain, Object object, boolean includeRoot)
    {
      super(object, includeRoot);
      this.domain = domain;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Iterator<E> getChildren(Object o)
    {
      return (Iterator<E>)domain.getChildren(o).iterator();
    }
  }

  /**
   * This returns a tree iterator that will yield the object, the children of the object, their children, and so on.
   */
  public TreeIterator<?> treeIterator(Object object)
  {
    return new DomainTreeIterator<Object>(this, object);
  }

  /**
   * This returns a path list from the root object to the given object in the tree.
   */
  public List<?> getTreePath(Object object)
  {
    LinkedList<Object> result = new LinkedList<Object>();
    result.addFirst(object);
    while ((object = getParent(object)) != null)
    {
      result.addFirst(object);
    }

    return result;
  }

  /**
   * This returns whether or not the domain allows the given object to be moved to
   * a different resource from its container.
   * In this implementation, an EObject is controllable if it has a container,
   * it is contained via a feature that allows proxy resolution, and neither it
   * nor its container is in a read-only resource.
   */
  public boolean isControllable(Object object)
  {
    if (!(object instanceof EObject)) return false;
    EObject eObject = (EObject)object;
    EObject container = eObject.eContainer();
    return container != null && eObject.eContainmentFeature().isResolveProxies() &&
      !isReadOnly(eObject.eResource()) && !isReadOnly(container.eResource());
  }

  /**
   * This returns whether or not an object has been moved to a different resource from
   * its container. It is a simple convenience method that compares the two resource
   * of the two objects.
   */
  public static boolean isControlled(Object object)
  {
    if (!(object instanceof EObject)) return false;
    EObject eObject = (EObject)object;
    EObject container = eObject.eContainer();
    Resource resource = eObject.eResource();
    return resource != null && container != null && resource != container.eResource();
  }
}