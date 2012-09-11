/**
 * Copyright (c) 2011 BestSolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - initial API and implementation
 */
package org.eclipse.emf.databinding.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.IProperty;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.SimplePropertyEvent;
import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.core.databinding.property.value.SimpleValueProperty;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.databinding.IEMFListProperty.ListElementAccess;
import org.eclipse.emf.databinding.IEMFListProperty.ListElementAccess.WriteData;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 */
public class EMFListValueProperty extends SimpleValueProperty
{
  private final EStructuralFeature eStructuralFeature;
  @SuppressWarnings("rawtypes")
  private final ListElementAccess elementAccess;
  private final IListProperty delegate;

  /**
   * @param delegate 
   * @param eStructuralFeature
   * @param elementAccess
   */
  public EMFListValueProperty(IListProperty delegate, EStructuralFeature eStructuralFeature, ListElementAccess<?> elementAccess)
  {
    this.delegate = delegate;
    this.eStructuralFeature = eStructuralFeature;
    this.elementAccess = elementAccess;
  }

  public Object getValueType()
  {
    return eStructuralFeature;
  }

  @Override
  protected Object doGetValue(Object source)
  {
    List< ? > list = listFromDelegate(source);
    @SuppressWarnings("unchecked")
    int idx = elementAccess.getReadValueIndex(list);
    if (idx != WriteData.NO_INDEX)
    {
      return list.get(idx);
    }
    return null;
  }

	@SuppressWarnings("unchecked")
	private List<?> listFromDelegate(Object source) {
		List<?> list = Collections.EMPTY_LIST;
		if (source != null) {
		    // we can't use IListProperty#getList(Object) because our lower bound is DB 1.2 
		    if( delegate instanceof EMFListProperty ) {
		      list = ((EMFListProperty)delegate).doGetList(source);
		    } else {
		      IObservableList observable = delegate.observe(source);
	            try {
	                list = new ArrayList<Object>(observable);
	            } finally {
	                observable.dispose();
	            }
		    }
		}
		return list;
	}

  @SuppressWarnings("unchecked")
  @Override
  protected void doSetValue(Object source, Object value)
  {
    IObservableList list = delegate.observe(source);
    try {
      WriteData data = elementAccess.getWriteValueData(list);
      if (data != null)
      {
        doSetListValue((EObject)source, list, data, value);
      }      
    } finally {
      list.dispose();  
    }
  }

  /**
   * @param source
   * @param targetList
   * @param data
   * @param value
   */
  protected void doSetListValue(EObject source, List<Object> targetList, WriteData data, Object value)
  {
    if (data.insert)
    {
      if (data.index == WriteData.NO_INDEX)
      {
        targetList.add(value);
      }
      else
      {
        targetList.add(data.index, value);
      }
    }
    else
    {
      targetList.set(data.index, value);
    }
  }

  @Override
  public INativePropertyListener adaptListener(final ISimplePropertyListener listener)
  {
    return new EMFPropertyListener()
      {

        @Override
        public void notifyChanged(Notification msg)
        {
          if (msg.getFeature() == getFeature() && !msg.isTouch())
          {

            Object newValue = doGetValue(msg.getNotifier());

            //TODO Can we be smarter here and find out if the value really changed instead of passing null
            getListener().handleEvent(
              new SimplePropertyEvent(SimplePropertyEvent.CHANGE, msg.getNotifier(), getOwner(), Diffs.createValueDiff(null, newValue)));
          }
        }

        @Override
        protected IProperty getOwner()
        {
          return EMFListValueProperty.this;
        }

        @Override
        protected ISimplePropertyListener getListener()
        {
          return listener;
        }

        @Override
        protected EStructuralFeature getFeature()
        {
          return eStructuralFeature;
        }
      };
  }
}
