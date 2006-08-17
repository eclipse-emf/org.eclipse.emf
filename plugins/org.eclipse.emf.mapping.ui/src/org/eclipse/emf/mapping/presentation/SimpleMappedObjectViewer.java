/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: SimpleMappedObjectViewer.java,v 1.3 2006/08/17 21:19:58 emerks Exp $
 */
package org.eclipse.emf.mapping.presentation;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.domain.MappingDomain;


public class SimpleMappedObjectViewer extends TreeViewer 
{
  protected SimpleMappedObjectViewer otherViewer;
  protected MappingDomain mappingDomain;
  protected AdapterFactory adapterFactory;
  protected boolean isTop;

  protected class SelectObjectAction extends Action
  {
    protected Object objectToSelect;

    public SelectObjectAction(String text, ImageDescriptor imageDescriptor)
    {
      super(text, imageDescriptor);
      setEnabled(false);
    }

    public void setObjectToSelect(Object objectToSelect)
    {
      this.objectToSelect = objectToSelect;
      setEnabled(objectToSelect != null);
    }

    public void run()
    {
      setSelection(new StructuredSelection(new Object [] { objectToSelect }), true);
    }
  }

  protected SelectObjectAction selectNextMappedObject;
  protected SelectObjectAction selectPreviousMappedObject;
  protected SelectObjectAction selectNextUnmappedObject;
  protected SelectObjectAction selectPreviousUnmappedObject;
  protected Action filterMappedObjects;
  protected Action selectOtherMappedObjects;

  public SimpleMappedObjectViewer(MappingDomain domain, Tree tree, boolean isTop)
  {
    super(tree);
    this.mappingDomain = domain;
    this.isTop = isTop;
    tree.addMouseListener
      (new MouseAdapter()
       {
         public void mouseDoubleClick(MouseEvent event)
         {
           selectOtherMappedObjects();
         }
       });

    addSelectionChangedListener
      (new ISelectionChangedListener()
       {
         public void selectionChanged(SelectionChangedEvent event) 
         {
           updateActions();
         }
       });
  }

  public void setAdapterFactory(AdapterFactory adapterFactory)
  {
    this.adapterFactory = adapterFactory;
  }

  public Action getFilterMappedObjectsAction()
  {
    return filterMappedObjects;
  }

  public void setOtherViewer(SimpleMappedObjectViewer otherViewer)
  {
    this.otherViewer = otherViewer;
  }

  protected ISelection doGetSelection()
  {
    return super.getSelection();
  }

  public void updateActions()
  {
    Collection selection = ((IStructuredSelection)doGetSelection()).toList();
    boolean isReady = false;
    Object previousMapped = null;
    Object previousUnmapped = null;
    Object nextMapped = null;
    Object nextUnmapped = null;
    boolean selectionHasMappings = false;
    LOOP: for (Iterator i = ((IStructuredItemContentProvider)getInput()).getElements(getInput()).iterator(); i.hasNext(); )
    {
      Object object = i.next();
      for (TreeIterator treeIterator = new AdapterFactoryTreeIterator(adapterFactory, object); treeIterator.hasNext(); )
      {
        Object child = treeIterator.next();
        if (selection.contains(child))
        {
          isReady = true;
          if (!selectionHasMappings)
          {
            for (Iterator mappings = mappingDomain.getMappingRoot().getMappings(child).iterator(); mappings.hasNext(); )
            {
              Mapping mapping = (Mapping)mappings.next();
              if (mapping.getInputs().contains(child) && !mapping.getOutputs().isEmpty() ||
                    mapping.getOutputs().contains(child) && !mapping.getInputs().isEmpty())
              {
                selectionHasMappings = true;
                break;
              }
            }
          }
        }
        else if (mappingDomain.getMappingRoot().getMappings(child).isEmpty())
        {
          if (isReady)
          {
            if (nextUnmapped == null)
            {
              nextUnmapped = child;
              if (nextMapped != null)
              {
                break LOOP;
              }
            }
          }
          else
          {
            previousUnmapped = child;
          }
        }
        else
        {
          if (isReady)
          {
            if (nextMapped == null)
            {
              nextMapped = child;
              if (nextUnmapped != null)
              {
                break LOOP;
              }
            }
          }
          else
          {
            previousMapped = child;
          }
        }
      }
    }
    selectNextMappedObject.setObjectToSelect(nextMapped);
    selectPreviousMappedObject.setObjectToSelect(previousMapped);
    selectNextUnmappedObject.setObjectToSelect(nextUnmapped);
    selectPreviousUnmappedObject.setObjectToSelect(previousUnmapped);
    selectOtherMappedObjects.setEnabled(selectionHasMappings);
  }

  public void selectOtherMappedObjects()
  {
    Collection result = new HashSet();
    for (Iterator i = ((IStructuredSelection)doGetSelection()).iterator(); i.hasNext(); )
    {
      Object object = i.next();
      Collection mappings = mappingDomain.getMappingRoot().getMappings(object);
      if (mappingDomain.getMappingRoot().isInputObject(object))
      {
        for (Iterator j = mappings.iterator(); j.hasNext(); )
        {
          Mapping mapping = (Mapping)j.next();
          result.addAll(mapping.getOutputs());
        }
      }
      else if (mappingDomain.getMappingRoot().isOutputObject(object))
      {
        for (Iterator j = mappings.iterator(); j.hasNext(); )
        {
          Mapping mapping = (Mapping)j.next();
          result.addAll(mapping.getInputs());
        }
      }
    }
    otherViewer.setSelection(new StructuredSelection(result.toArray()), true);
  }

  public ISelection getSelection()
  {
    ISelection basicSelection = doGetSelection();
    if (otherViewer == null)
    {
      return basicSelection;
    }
    else
    {
      return new ComposedSelection(basicSelection, new ISelection [] {basicSelection, otherViewer.doGetSelection()});
    }
  }

  public void makeContributions(IMenuManager menuManager, IToolBarManager toolBarManager, IStatusLineManager statusLineManager)
  {
    String label = isTop ? "Top" : "Bottom";
    String oppositeLabel = isTop ? "Bottom" : "Top";

    selectNextMappedObject =
      new SelectObjectAction
        (MappingUIPlugin.getPlugin().getString("_UI_NextMappedObject_menu_item"), 
         MappingUIPlugin.getPlugin().getImageDescriptor("full/elcl16/SelectNext" + label + "MappedObject"));
    selectNextMappedObject.setToolTipText(MappingUIPlugin.getPlugin().getString("_UI_NextMappedObject_description"));
    selectNextMappedObject.setHoverImageDescriptor
      (MappingUIPlugin.getPlugin().getImageDescriptor("full/clcl16/SelectNext" + label + "MappedObject"));
    selectNextMappedObject.setDisabledImageDescriptor
      (MappingUIPlugin.getPlugin().getImageDescriptor("full/dlcl16/SelectNext" + label + "MappedObject"));
    toolBarManager.add(selectNextMappedObject);
    menuManager.add(selectNextMappedObject);

    selectPreviousMappedObject =
      new SelectObjectAction
        (MappingUIPlugin.getPlugin().getString("_UI_PreviousMappedObject_menu_item"),
         MappingUIPlugin.getPlugin().getImageDescriptor("full/elcl16/SelectPrevious" + label + "MappedObject"));
    selectPreviousMappedObject.setToolTipText(MappingUIPlugin.getPlugin().getString("_UI_PreviousMappedObject_description"));
    selectPreviousMappedObject.setHoverImageDescriptor
      (MappingUIPlugin.getPlugin().getImageDescriptor("full/clcl16/SelectPrevious" + label + "MappedObject"));
    selectPreviousMappedObject.setDisabledImageDescriptor
      (MappingUIPlugin.getPlugin().getImageDescriptor("full/dlcl16/SelectPrevious" + label + "MappedObject"));
    toolBarManager.add(selectPreviousMappedObject);
    menuManager.add(selectPreviousMappedObject);

    menuManager.add(new Separator());

    selectNextUnmappedObject =
      new SelectObjectAction
        (MappingUIPlugin.getPlugin().getString("_UI_NextUnmappedObject_menu_item"),
         MappingUIPlugin.getPlugin().getImageDescriptor("full/elcl16/SelectNext" + label + "UnmappedObject"));
    selectNextUnmappedObject.setToolTipText(MappingUIPlugin.getPlugin().getString("_UI_NextUnmappedObject_description"));
    selectNextUnmappedObject.setHoverImageDescriptor
      (MappingUIPlugin.getPlugin().getImageDescriptor("full/clcl16/SelectNext" + label + "UnmappedObject"));
    selectNextUnmappedObject.setDisabledImageDescriptor
      (MappingUIPlugin.getPlugin().getImageDescriptor("full/dlcl16/SelectNext" + label + "UnmappedObject"));
    toolBarManager.add(selectNextUnmappedObject);
    menuManager.add(selectNextUnmappedObject);

    selectPreviousUnmappedObject =
      new SelectObjectAction
        (MappingUIPlugin.getPlugin().getString("_UI_PreviousUnmappedObject_menu_item"),
         MappingUIPlugin.getPlugin().getImageDescriptor("full/elcl16/SelectPrevious" + label + "UnmappedObject"));
    selectPreviousUnmappedObject.setToolTipText(MappingUIPlugin.getPlugin().getString("_UI_PreviousUnmappedObject_description"));
    selectPreviousUnmappedObject.setHoverImageDescriptor
      (MappingUIPlugin.getPlugin().getImageDescriptor("full/clcl16/SelectPrevious" + label + "UnmappedObject"));
    selectPreviousUnmappedObject.setDisabledImageDescriptor
      (MappingUIPlugin.getPlugin().getImageDescriptor("full/dlcl16/SelectPrevious" + label + "UnmappedObject"));
    toolBarManager.add(selectPreviousUnmappedObject);
    menuManager.add(selectPreviousUnmappedObject);

    menuManager.add(new Separator());

    filterMappedObjects = 
      new Action
        (MappingUIPlugin.getPlugin().getString("_UI_FilterMappedObjects_menu_item"),
         MappingUIPlugin.getPlugin().getImageDescriptor("full/elcl16/HideAllMappedObjects"))
      {
        public void run()
        {
          preserveState();
          Object input = getInput();
          setInput(input);
          restoreState();

          updateActions();
        }
        public void setChecked(boolean checked)
        {
          super.setChecked(checked);
          setToolTipText
            (MappingUIPlugin.getPlugin().getString
              (checked ? "_UI_FilterMappedObjects_checked_description" : "_UI_FilterMappedObjects_unchecked_description"));
        }
      };
    filterMappedObjects.setChecked(false);
    filterMappedObjects.setHoverImageDescriptor
      (MappingUIPlugin.getPlugin().getImageDescriptor("full/clcl16/HideAllMappedObjects"));
    filterMappedObjects.setDisabledImageDescriptor
      (MappingUIPlugin.getPlugin().getImageDescriptor("full/dlcl16/HideAllMappedObjects"));
    toolBarManager.add(filterMappedObjects);
    menuManager.add(filterMappedObjects);

    menuManager.add(new Separator());

    selectOtherMappedObjects =
      new Action
        (MappingUIPlugin.getPlugin().getString("_UI_SelectOtherMappedObjects_menu_item"),
         MappingUIPlugin.getPlugin().getImageDescriptor("full/elcl16/Select" + oppositeLabel + "MappedObjects"))
      {
        public void run()
        {
          selectOtherMappedObjects();
        }
      };
    selectOtherMappedObjects.setEnabled(false);
    selectOtherMappedObjects.setToolTipText(MappingUIPlugin.getPlugin().getString("_UI_SelectOtherMappedObjects_description"));
    selectOtherMappedObjects.setHoverImageDescriptor
      (MappingUIPlugin.getPlugin().getImageDescriptor("full/clcl16/Select" + oppositeLabel + "MappedObjects"));
    selectOtherMappedObjects.setDisabledImageDescriptor
      (MappingUIPlugin.getPlugin().getImageDescriptor("full/dlcl16/Select" + oppositeLabel + "MappedObjects"));
    toolBarManager.add(selectOtherMappedObjects);
    menuManager.add(selectOtherMappedObjects);

    toolBarManager.update(true);
    menuManager.update(true);
  }

  protected Collection expandedObjects = new HashSet();
  protected Collection selectedObjects = new HashSet();

  public void preserveState()
  {
    Collection oldExpandedObjects = expandedObjects;
    expandedObjects = new HashSet(Arrays.asList(getExpandedElements()));
    oldExpandedObjects.removeAll(expandedObjects);
    for (Iterator i = oldExpandedObjects.iterator(); i.hasNext(); )
    {
      Object oldExpandedObject = i.next();
      Widget item = findItem(oldExpandedObject);
      if (item == null)
      {
        expandedObjects.add(oldExpandedObject);
      }
    }  

    Collection oldSelectedObjects = selectedObjects;
    selectedObjects = new HashSet(((IStructuredSelection)getSelection()).toList());
    if (selectedObjects.isEmpty())
    {
      selectedObjects = oldSelectedObjects;
    }
  }

  public void restoreState()
  {
    setExpandedElements(expandedObjects.toArray());
    setSelection(new StructuredSelection(selectedObjects.toArray()), true);
  }
}
