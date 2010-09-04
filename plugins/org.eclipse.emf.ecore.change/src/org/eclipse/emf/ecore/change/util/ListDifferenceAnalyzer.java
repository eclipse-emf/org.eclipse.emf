/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ListDifferenceAnalyzer.java,v 1.4 2010/09/04 16:57:16 emerks Exp $
 */
package org.eclipse.emf.ecore.change.util;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.change.ChangeFactory;
import org.eclipse.emf.ecore.change.ChangeKind;
import org.eclipse.emf.ecore.change.ListChange;


/**
 * Abstract class implementing the methods required to compute differences between
 * lists.  The differences are described by {@link ListChange} objects.
 * @since 2.3
 */
public class ListDifferenceAnalyzer
{
  /**
   * Analyzes the differences between two lists, returning the {@link ListChange list changes}
   * that describe how the <code>newList</code> could be changed to the contents of 
   * <code>oldList</code>.  The lists are not modified by this method.</p>
   * @param oldList
   * @param newList
   * @return a list of {@link ListChange}
   */
  public EList<ListChange> analyzeLists(EList<?> oldList, EList<?> newList)
  {
    EList<ListChange> listChanges = new BasicEList<ListChange>();
    analyzeLists(new BasicEList<Object>(oldList), newList, listChanges);
    return listChanges;
  }

  /**
   * <p>Analyzes the differences between two lists, adding new {@link ListChange list changes} to the
   * specified <code>listChanges</code>.  The list changes describe how the <code>newList</code>
   * should be manipulated in order to have the same contents of <code>oldList</code></p>
   * 
   * <p>This methods changes the contents of <code>oldList</code></p>
   * 
   * @param oldList
   * @param newList
   * @param listChanges
   */
  public void analyzeLists(EList<Object> oldList, EList<?> newList, EList<ListChange> listChanges)
  {
    createListChanges(new BasicEList<Object>(oldList), newList, listChanges);
  }
  
  /**
   * @see #analyzeLists(EList, EList, EList)
   */
  protected void createListChanges(EList<Object> oldList, EList<?> newList, EList<ListChange> listChanges)
  {
    int index = 0;
    for (Object newObject : newList)
    {
      if (oldList.size() <= index)
      {
        createAddListChange(oldList, listChanges, newObject, index);
      }
      else
      {
        boolean done;
        do
        {
          done = true;
          Object targetObject = oldList.get(index);
          if (targetObject == null ? newObject != null : !targetObject.equals(newObject))
          {
            int position = ECollections.indexOf(oldList, newObject, index);
            if (position != -1)
            {
              int targetIndex = ECollections.indexOf(newList, targetObject, index);
              if (targetIndex == -1)
              {
                createRemoveListChange(oldList, listChanges, targetObject, index);
                done = false;
              }
              else if (targetIndex > position)
              {
                if (oldList.size() <= targetIndex)
                {
                  targetIndex = oldList.size() - 1;
                }
                createMoveListChange(oldList, listChanges, targetObject, index, targetIndex);
                done = false;
              }
              else
              {
                createMoveListChange(oldList, listChanges, newObject, position, index);
              }
            }
            else
            {
              createAddListChange(oldList, listChanges, newObject, index);
            }
          }
        }
        while (!done);
      }
      ++index;
    }
    for (int i = oldList.size(); i > index;)
    {
      createRemoveListChange(oldList, listChanges, oldList.get(--i), i);
    }
  }

  /**
   * Convenience method added to allow subclasses to modify the default implementation 
   * for the scenario in which an element was added to the monitored list.
   * @see #createListChanges(EList, EList, EList) 
   */
  protected void createAddListChange(EList<Object> oldList, EList<ListChange> listChanges, Object newObject, int index)
  {
    ListChange listChange = createListChange(listChanges, ChangeKind.ADD_LITERAL, index);
    listChange.getValues().add(newObject);
    oldList.add(index, newObject);    
  }

  /**
   * Convenience method added to allow subclasses to modify the default implementation 
   * for the scenario in which an element was removed from the monitored list.
   * @see #createListChanges(EList, EList, EList) 
   */
  protected void createRemoveListChange(EList<?> oldList, EList<ListChange> listChanges, Object newObject, int index)
  {
    createListChange(listChanges, ChangeKind.REMOVE_LITERAL, index);
    oldList.remove(index);
  }

  /**
   * Convenience method added to allow subclasses to modify the default implementation 
   * for the scenario in which an element was moved in the monitored list.
   * @see #createListChanges(EList, EList, EList) 
   */
  protected void createMoveListChange(EList<?> oldList, EList<ListChange> listChanges, Object newObject, int index, int toIndex)
  {
    ListChange listChange = createListChange(listChanges, ChangeKind.MOVE_LITERAL, index);
    listChange.setMoveToIndex(toIndex);
    oldList.move(toIndex, index);
   }  

  /**
   * Creates a ListChange, initializes the main attributes, and adds it to the specified listChanges.   
   * @param listChanges
   * @param kind
   * @param index
   * @return ListChange
   */
  protected ListChange createListChange(EList<ListChange> listChanges, ChangeKind kind, int index)
  {
    ListChange listChange = ChangeFactory.eINSTANCE.createListChange();
    listChange.setKind(kind);
    listChange.setIndex(index);
    listChanges.add(listChange);
    return listChange;
  }
}
