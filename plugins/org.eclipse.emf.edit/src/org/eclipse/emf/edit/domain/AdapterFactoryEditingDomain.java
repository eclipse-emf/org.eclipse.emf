/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: AdapterFactoryEditingDomain.java,v 1.2 2004/04/30 14:34:09 emerks Exp $
 */
package org.eclipse.emf.edit.domain;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyToClipboardCommand;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.CutToClipboardCommand;
import org.eclipse.emf.edit.command.OverrideableCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.ReplaceCommand;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;



/**
 * This class implements an editing domain by delegating to adapters that implement 
 * {@link org.eclipse.emf.edit.provider.IEditingDomainItemProvider}.
 */
public class AdapterFactoryEditingDomain implements EditingDomain
{
  /**
   * This class is an adapter than implements {@link org.eclipse.emf.edit.provider.IEditingDomainProvider}.
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

    public boolean isAdapterForType(Object type)
    {
      return type == IEditingDomainProvider.class;
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
        else
        {
          editingDomainProvider = (IEditingDomainProvider)EcoreUtil.getExistingAdapter(resourceSet, IEditingDomainProvider.class);
          if (editingDomainProvider != null)
          {
            return editingDomainProvider.getEditingDomain();
          }
        }
      }
    }

    return null;
  }

  /**
   * This returns the editing domain for the given aribtrary object, or null, if it can't be determined.
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
  protected Collection clipboard;

  /**
   * This controls whether or not copy command optimizations are safe in this domain.
   */
  protected boolean optimizeCopy = true;

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
   * Create an instance from the adapter factory, the specialized command stack, and the specialized resource set.
   * If the resource set's context is null, one will be created here;
   * otherwize, the existing context should implement {@link org.eclipse.emf.edit.domain.IEditingDomainProvider}.
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
      exception.printStackTrace();
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
  public Command createCommand(Class commandClass, CommandParameter commandParameter)
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

        List objects = new ArrayList(commandParameter.getCollection());
        while (!objects.isEmpty())
        {
          // We will iterate over the whole collection, removing some as we go.
          //
          ListIterator remainingObjects = objects.listIterator();

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
            List siblings = new ArrayList();
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
          else
          {
            // The parent is null, which implies a top-leve removal, so create a self-removing command.
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
        return createCommand(ReplaceCommand.class, new CommandParameter(parent, null, commandParameter.getCollection()));
      }
      else if (commandClass == CreateChildCommand.class)
      {
        // For CreateChildCommand, we will find the owner by calling EditingDomain.getParent() on the first selected object
        Collection sel = commandParameter.getCollection();
        Object parent = sel == null ? null : getParent(sel.iterator().next());
        if (parent == null)
        {
          return UnexecutableCommand.INSTANCE;
        }
        return createCommand(CreateChildCommand.class, new CommandParameter(parent, commandParameter.getFeature(), commandParameter.getValue(), commandParameter.getCollection(), commandParameter.getIndex()));
      }
    }

    try
    {
      Constructor constructor = commandClass.getConstructor(new Class [] { EditingDomain.class, CommandParameter.class });
      Object command = constructor.newInstance(new Object [] { this, commandParameter });
      return (Command)command;
    }
    catch (IllegalAccessException exception)
    {
    }
    catch (InstantiationException exception)
    {
    }
    catch (NoSuchMethodException exception)
    {
    }
    catch (InvocationTargetException exception)
    {
    }

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
  public Collection getChildren(Object object)
  {
    // If there is an adapter of the correct type...
    //
    IEditingDomainItemProvider editingDomainItemProvider = 
      (IEditingDomainItemProvider)
        adapterFactory.adapt(object, IEditingDomainItemProvider.class);

    return
      editingDomainItemProvider != null ?
        editingDomainItemProvider.getChildren(object) :
        Collections.EMPTY_LIST;
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
   * This delegates to 
   * {@link org.eclipse.emf.edit.provider.IEditingDomainItemProvider#getNewChildDescriptors IEditingDomainItemProvider.getNewChildDescriptors}.
   */
  public Collection getNewChildDescriptors(Object object, Object sibling)
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
        Collections.EMPTY_LIST;
  }

  /**
   * This returns the clipboard of the editing domain.
   */
  public Collection getClipboard()
  {
    return clipboard;
  }

  /**
   * This sets the clipboard of the editing domain.
   */
  public void setClipboard(Collection clipboard)
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
   * This implements an tree iterator that iterates over an object, it's domain children, their domain children, and so on.
   */
  public static class DomainTreeIterator extends AbstractTreeIterator
  {
    /**
     * This is the domain that defines the tree structured.
     */
    protected EditingDomain domain;

    /**
     * This constructs tree iterator that iterates over an object, it's domain children, their domain children, and so on.
     */
    public DomainTreeIterator(EditingDomain domain, Object object)
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

    protected Iterator getChildren(Object o)
    {
      return domain.getChildren(o).iterator();
    }
  }

  /**
   * This returns a tree iterator that will yield the object, the children of the object, their children, and so on.
   */
  public TreeIterator treeIterator(Object object)
  {
    return new DomainTreeIterator(this, object);
  }

  /**
   * This returns a path list from the root object to the given object in the tree.
   */
  public List getTreePath(Object object)
  {
    LinkedList result = new LinkedList();
    result.addFirst(object);
    while ((object = getParent(object)) != null)
    {
      result.addFirst(object);
    }

    return result;
  }
}

/**
 * This implementation of {@link org.eclipse.emf.ecore.resource.ContextResourceFactoryRegister} 
 * behaves so that it extends, 
 * and potentially overrides,
 * the behaviour you would get without setting this into a {@link org.eclipse.emf.ecore.resource.Context},
 * In other words, 
 * this provides for registrations in addition to the factories registered with {@link org.eclipse.emf.ecore.resource.ResourceFactoryRegister}.
 * Since the are considered first, they can override default registrations.
 * EATM We should move this.
 */
/*
class ExtensibleContextResourceFactoryRegister extends ContextResourceFactoryRegister 
{
  public ExtensibleContextResourceFactoryRegister() 
  {
  }

  public ResourceFactory getFactory(String uri) 
  {
    ResourceFactory resourceFactory = super.getFactory(uri);
    if (resourceFactory == null)
    {
      resourceFactory = ResourceFactoryRegister.getFactory(uri);
    }

    return resourceFactory;
  }

  public ResourceFactory getFileFactory(String fileName) 
  {
    ResourceFactory resourceFactory = super.getFileFactory(fileName);
    if (resourceFactory == null)
    {
      resourceFactory = ResourceFactoryRegister.getFileFactory(fileName);
    }

    return resourceFactory;
  }
}
*/
