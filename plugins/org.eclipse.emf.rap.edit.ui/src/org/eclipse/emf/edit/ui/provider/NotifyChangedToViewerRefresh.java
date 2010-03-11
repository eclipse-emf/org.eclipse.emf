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
 * $Id: NotifyChangedToViewerRefresh.java,v 1.1 2010/03/11 02:30:14 khussey Exp $
 */
package org.eclipse.emf.edit.ui.provider;


import java.util.Collection;

import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;


/**
 * This class calls optimized refresh APIs for all the standard Viewer subclasses.
 */
public class NotifyChangedToViewerRefresh
{
  public static void handleNotifyChanged
    (final Viewer viewer, 
     final Object object, 
     final int eventType, 
     final Object feature, 
     final Object oldValue, 
     final Object newValue, 
     final int index)
  {
    if (viewer.getControl() == null || viewer.getControl().isDisposed()) return;
    Display d = viewer.getControl().getDisplay();
    if (d != Display.getCurrent())
    {
      d.asyncExec
        (new Runnable() 
         {
           public void run()
           {
             if (viewer.getControl() != null && !viewer.getControl().isDisposed())
             {
               new NotifyChangedToViewerRefresh().refresh(viewer, object, eventType, feature, oldValue, newValue, index);
             }
           }
         });
    }
    else
    {
      new NotifyChangedToViewerRefresh().refresh(viewer, object, eventType, feature, oldValue, newValue, index);
    }
  }

  public void refresh
    (Viewer viewer, 
     Object object, 
     int eventType, 
     Object feature, 
     Object oldValue, 
     Object newValue, 
     int index)
  {
    if (viewer instanceof TreeViewer)
    {
      refreshTreeViewer((TreeViewer)viewer, object, eventType, feature, oldValue, newValue, index);
    }
    else if (viewer instanceof TableViewer)
    {
      refreshTableViewer((TableViewer)viewer, object, eventType, feature, oldValue, newValue, index);
    }
    else if (viewer instanceof ListViewer)
    {
      refreshListViewer((ListViewer)viewer, object, eventType, feature, oldValue, newValue, index);
    }
    else
    {
      refreshViewer(viewer, object, eventType, feature, oldValue, newValue, index);
    }
  }

  public void refreshTreeViewer
    (TreeViewer viewer, 
     Object object, 
     int eventType, 
     Object feature, 
     Object oldValue, 
     Object newValue, 
     int index)
  {
    switch (eventType) 
    {
      case Notification.ADD:
      case Notification.ADD_MANY:
      case Notification.REMOVE:
      case Notification.REMOVE_MANY:
      case Notification.MOVE:
      case Notification.UNSET:
      case Notification.SET:
      default:
      {
        refreshAbstractTreeViewer(viewer, object, eventType, feature, oldValue, newValue, index);
        break;
      }
    }
  }

  public void refreshListViewer
    (ListViewer viewer, 
     Object object, 
     int eventType, 
     Object feature, 
     Object oldValue, 
     Object newValue, 
     int index)
  {
    switch (eventType) 
    {
      case Notification.ADD:
      {
        viewer.add(newValue);
        break;
      }
      case Notification.ADD_MANY:
      {
        viewer.add(((Collection<?>)newValue).toArray());
        break;
      }
      case Notification.REMOVE:
      {
        viewer.remove(oldValue);
        break;
      }
      case Notification.REMOVE_MANY:
      {
        viewer.remove(((Collection<?>)oldValue).toArray());
        break;
      }
      case Notification.MOVE:
      {
        viewer.refresh(); // ???
      }
      case Notification.UNSET:
      case Notification.SET:
      default:
      {
        refreshStructuredViewer(viewer, object, eventType, feature, oldValue, newValue, index);
        break;
      }
    }
  }

  public void refreshTableViewer
    (TableViewer viewer, 
     Object object, 
     int eventType, 
     Object feature, 
     Object oldValue, 
     Object newValue, 
     int index)
  {
    switch (eventType) 
    {
      case Notification.ADD:
      {
        viewer.insert(newValue, index);
        break;
      }
      case Notification.ADD_MANY:
      {
        if (index == -1)
        {
          viewer.add(((Collection<?>)newValue).toArray());
        }
        else
        {
          for (Object value : (Collection<?>)newValue)
          {
            viewer.insert(value, index++);
          }
        }
        break;
      }
      case Notification.REMOVE:
      {
        viewer.remove(oldValue);
        break;
      }
      case Notification.REMOVE_MANY:
      {
        viewer.remove(((Collection<?>)oldValue).toArray());
        break;
      }
      case Notification.MOVE:
      case Notification.UNSET:
      case Notification.SET:
      default:
      {
        refreshStructuredViewer(viewer, object, eventType, feature, oldValue, newValue, index);
        break;
      }
    }
  }

  public void refreshAbstractTreeViewer
    (AbstractTreeViewer viewer, 
     Object object, 
     int eventType, 
     Object feature, 
     Object oldValue, 
     Object newValue, 
     int index)
  {
    switch (eventType) 
    {
      case Notification.ADD:
      {
        if (newValue == null)
        {
          viewer.refresh(object);
        }
        else
        {
          viewer.add(object, newValue);
        }
        break;
      }
      case Notification.ADD_MANY:
      {
        viewer.add(object, ((Collection<?>)newValue).toArray());
        break;
      }
      case Notification.REMOVE:
      {
        if (oldValue == null)
        {
          viewer.refresh(object);
        }
        else
        {
          viewer.remove(oldValue);
        }
        break;
      }
      case Notification.REMOVE_MANY:
      {
        viewer.remove(((Collection<?>)oldValue).toArray());
        break;
      }
      case Notification.MOVE:
      case Notification.UNSET:
      case Notification.SET:
      default:
      {
        refreshStructuredViewer(viewer, object, eventType, feature, oldValue, newValue, index);
        break;
      }
    }
  }

  public void refreshStructuredViewer
    (StructuredViewer viewer, 
     Object object, 
     int eventType, 
     Object feature, 
     Object oldValue, 
     Object newValue, 
     int index)
  {
    switch (eventType) 
    {
      case Notification.ADD:
      case Notification.ADD_MANY:
      case Notification.REMOVE:
      case Notification.REMOVE_MANY:
      case Notification.MOVE:
      {
        viewer.refresh(object);
        break;
      }
      case Notification.UNSET:
      case Notification.SET:
      {
        if (feature instanceof EReference)
        {
          viewer.refresh(object);
        }
        else
        {
          viewer.update(object, feature instanceof ENamedElement ? new String[] { ((ENamedElement)feature).getName() } : null);
        }
        break;
      }
//    case Notification.TOUCH:
      default:
      {
        refreshViewer(viewer, object, eventType, feature, oldValue, newValue, index);
        break;
      }
    }
  }

  public void refreshViewer
    (Viewer viewer, 
     Object object, 
     int eventType, 
     Object feature, 
     Object oldValue, 
     Object newValue, 
     int index)
  {
    switch (eventType) 
    {
      case Notification.RESOLVE:
      {
        // We ignore non-changes for now.
        //
        break;
      }
      case Notification.ADD:
      case Notification.ADD_MANY:
      case Notification.REMOVE:
      case Notification.REMOVE_MANY:
      case Notification.MOVE:
      case Notification.UNSET:
      case Notification.SET:
      default:
      {
        viewer.refresh();
        break;
      }
    }
  }
}
