/**
 * <copyright>
 *
 * Copyright (c) 2007-2011 IBM Corporation and others.
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
 * $Id: ListDifferenceAnalyzer.java,v 1.4.2.1 2011/08/29 19:17:24 khussey Exp $
 */
package org.eclipse.emf.ecore.change.util;

import org.eclipse.emf.common.util.BasicEList;
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


  protected void createListChanges(EList<Object> oldList, EList<?> newList, EList<ListChange> listChanges)
  {
    // Keep track of the list sizes.
    //
    int oldListSize = oldList.size();
    int newListSize = newList.size();

    // Track which value at an index in the old list matches the value at each index in the new list.
    // A zero indicates an unmatched item while an index is offset by one to avoid using the zero index.
    //
    int[] newListSources  = new int [newListSize];

    // Iterate over the old list.
    // We'll remove unmatched items as we proceed.
    // Also keep track of which entry in the sources at which to start,
    // so we can skip over all the already matched values at the start of the list.
    //
    for (int i = 0, start = 0; i < oldListSize; )
    {
      // Get the value at that the index.
      //
      Object oldValue = oldList.get(i);

      // Mark it as one that needs to be removed until we find a match.
      //
      boolean remove = true;

      // Keep track when all slots in the new list have been consumed.
      //
      boolean allSlotsMatched = true;

      // Look for a match for the old value in the new list.
      //
      LOOP:
      for (int j = start; j < newListSize; ++j)
      {
        // If the tracked entry is uninitialized...
        //
        int source = newListSources[j];
        if (source == 0)
        {
          // Get the new value at the index and compare it to the old value.
          //
          Object newValue = newList.get(j);
          if (oldValue == null ? newValue == null : oldValue == newValue || oldValue.equals(newValue))
          {
            // If they're equal, indicate that the new value at the index j matches the old value at index i.
            //
            newListSources[j] = i + 1;

            // If this index was the start, increment the start.
            //
            if (start == j)
            {
              ++start;
            }

            // The value is matched so don't remove it when exiting the loop.
            //
            remove = false;
            break LOOP;
          }
          // If all slots might be matched, but we just hit one that wasn't...
          //
          else if (allSlotsMatched)
          {
            // Make that the starting slot and make sure no subsequent slot is marked as the starting slot.
            //
            start = j;
            allSlotsMatched = false;
          }
        }
      }

      // If we're done the loop without finding a match...
      //
      if (remove)
      {
        // Remove the old value thereby reducing the size of the list.
        //
        createRemoveListChange(oldList, listChanges, oldValue, i);
        --oldListSize;
      }
      else
      {
        // Proceed with the next old value.
        //
        ++i;
      }
    }

    // Create an array where each index represents the target index at which the value at the index in the old list should end up.
    //
    int[] oldListTargets = new int [oldListSize];

    // Keep a count of the number of values that need to be added, because we won't add anything until the rest of the list is in the right order.
    // That way we ensure that we add at exactly the right index.
    //
    int count = 0;

    // Iterate over the new list sources...
    //
    for (int i = 0; i < newListSize; ++i)
    {
      // If the new value is matched.
      //
      int newListSource = newListSources[i];
      if (newListSource != 0)
      {
        // Store in the index for where the matched value is now, the index of where it must end up.
        //
        oldListTargets[newListSource - 1] = count;

        // Only increment the count, and hence the index used for moving the items, for matched items.
        //
        ++count;
      }
    }

    // Loop until the termination condition is reached...
    //
    while (true)
    {
      // The index of the value to be moved.
      // We're looking for the one that needs to be moved the farthest.
      //
      int index = -1;

      // The distance of the value at the index needs to be moved.
      //
      int farthest = 0;

      // Iterate over the old list targets.
      //
      for (int i = 0; i < oldListSize; ++i)
      {
        // Determine the distance between the index and where it should be and ensure it's positive.
        //
        int distance = oldListTargets[i] - i;
        if (distance < 0)
        {
          distance = -distance;
        }

        // If the object at this index needs to be moved farther than the one we've determined so far...
        //
        if (distance > farthest)
        {
          // Record this index and the distance.
          //
          index = i;
          farthest = distance;
        }
      }
      // If no object needs to be moved.
      //
      if (index == -1)
      {
        // Terminate the list.
        //
        break;
      }
      else
      {
        // Keep track of the absolutely correct final target index.
        //
        int actualTargetIndex = oldListTargets[index];

        // Look for an index that might be a better intermediate...
        //
        int targetIndex = actualTargetIndex;
        if (targetIndex < index)
        {
          // In this case, the value ends up before the index.
          // So look to see if the value currently at the target index is before the value we're about to put before it.
          //
          if (oldListTargets[targetIndex] < actualTargetIndex)
          {
            do
            {
              // If so, move it after instead and check again...
              //
              ++targetIndex;
            }
            while (oldListTargets[targetIndex] < actualTargetIndex);
          }
          else
          {
            // Otherwise, check that it wouldn't be better to put the value before the previous target index
            // because the value we're about to put after it should be before.
            //
            while (targetIndex > 0 && oldListTargets[targetIndex - 1] > actualTargetIndex)
            {
              // If so, move it before instead and check again.
              //
              --targetIndex;
            }
          }
        }
        else
        {
          // In this case, the value ends up after the index.
          // So look to see if the value currently at the target index is after the value we're about to put after it.
          //
          if (oldListTargets[targetIndex] > actualTargetIndex)
          {
            do
            {
              // If so, move it before instead and check again...
              //
              --targetIndex;
            }
            while (oldListTargets[targetIndex] > actualTargetIndex);
          }
          else
          {
            // Otherwise, check that it wouldn't be better to put the value after the following target index
            // because the value we're about to put before it should be after.
            //
            while (targetIndex + 1 < oldListSize && oldListTargets[targetIndex + 1] < actualTargetIndex)
            {
              // If so, move it after instead and check again.
              //
              ++targetIndex;
            }
          }
        }

        // Move the old value at the index to the best new target index.
        //
        createMoveListChange(oldList, listChanges, oldList.get(index), index, targetIndex);

        // Update the old list targets array to reflect the move.
        //
        if (targetIndex < index)
        {
          System.arraycopy(oldListTargets, targetIndex, oldListTargets, targetIndex + 1, index - targetIndex);
        }
        else
        {
          System.arraycopy(oldListTargets, index + 1, oldListTargets, index, targetIndex - index);
        }
        oldListTargets[targetIndex] = actualTargetIndex;
      }
    }

    // If there are objects to add.
    //
    if (count != newListSize)
    {
      // Look for them.
      //
      for (int i = 0; i < newListSize; ++i)
      {
        int newListSource = newListSources[i];
        if (newListSource == 0)
        {
          // Add the missing new value.
          //
          createAddListChange(oldList, listChanges, newList.get(i), i);
        }
      }
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
