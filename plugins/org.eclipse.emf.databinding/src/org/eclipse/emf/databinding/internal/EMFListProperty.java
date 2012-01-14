/**
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Matthew Hall - initial API and implementation (bug 194734)
 *   Matthew Hall - bugs 195222, 264307, 265561
 *   Tom Schindl <tom.schindl@bestsolution.at> - port to EMF in 262160
 */
package org.eclipse.emf.databinding.internal;

import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.IProperty;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.list.SimpleListProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMapUtil;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @since 2.5
 */
public class EMFListProperty extends SimpleListProperty
{
  private EStructuralFeature eStructuralFeature;

  /**
   * @param eStructuralFeature
   */
  public EMFListProperty(EStructuralFeature eStructuralFeature)
  {
    this.eStructuralFeature = eStructuralFeature;
  }

  /**
   * @return the feature
   */
  protected EStructuralFeature getFeature()
  {
    return eStructuralFeature;
  }

  public Object getElementType()
  {
    return eStructuralFeature;
  }

  @Override
  protected List< ? > doGetList(Object source)
  {
    final EObject eObject = (EObject)source;
    if (FeatureMapUtil.isMany(eObject, eStructuralFeature))
    {
      return (List< ? >)eObject.eGet(eStructuralFeature);
    }
    else
    {
      return 
        new AbstractSequentialList<Object>()
        {
          @Override
          public ListIterator<Object> listIterator(int index)
          {
            ListIterator<Object> result =
              new ListIterator<Object>()
              {
                protected int position = 0;
                protected boolean setOrRemoveAllowed;
                public void add(Object o)
                {
                  if (position != 0)
                  {
                    throw new IllegalStateException();
                  }
                  eObject.eSet(eStructuralFeature, o);
                  position = 1;
                  setOrRemoveAllowed = false;
                }

                public boolean hasNext()
                {
                  return position == 0 && size() == 1;
                }

                public boolean hasPrevious()
                {
                  return position == 1;
                }

                public Object next()
                {
                  if (!hasNext())
                  {
                    throw new IllegalStateException();
                  }
                  ++position;
                  setOrRemoveAllowed = true;
                  return eObject.eGet(eStructuralFeature);
                }

                public int nextIndex()
                {
                  return position;
                }

                public Object previous()
                {
                  if (!hasPrevious())
                  {
                    throw new IllegalStateException();
                  }
                  else
                  {
                    --position;
                    setOrRemoveAllowed = true;
                    return eObject.eGet(eStructuralFeature);
                  }
                }

                public int previousIndex()
                {
                  return position - 1;
                }

                public void remove()
                {
                  if (!setOrRemoveAllowed)
                  {
                    throw new IllegalStateException();
                  }
                  else
                  {
                    setOrRemoveAllowed = false;
                    eObject.eUnset(eStructuralFeature);
                  }
                }

                public void set(Object o)
                {
                  if (!setOrRemoveAllowed)
                  {
                    throw new IllegalStateException();
                  }
                  else
                  {
                    setOrRemoveAllowed = false;
                    eObject.eSet(eStructuralFeature, o);
                  }
                }
              };
            for (int i = 0; i < index; ++i)
            {
              result.next();
            }
            return result;
          }

          @Override
          public int size()
          {
            return eStructuralFeature.isUnsettable() ? eObject.eIsSet(eStructuralFeature) ? 1 : 0 : eObject.eGet(eStructuralFeature, false) == null ? 0 : 1;
          }
        };
    }
  }

  @Override
  @SuppressWarnings("rawtypes")
  protected void doSetList(Object source, List list, ListDiff diff)
  {
    List< ? > currentList = doGetList(source);
    diff.applyTo(currentList);
  }

  @Override
  public INativePropertyListener adaptListener(final ISimplePropertyListener listener)
  {
    return new EMFPropertyListener.EMFListPropertyListener()
      {

        @Override
        protected EStructuralFeature getFeature()
        {
          return eStructuralFeature;
        }

        @Override
        protected ISimplePropertyListener getListener()
        {
          return listener;
        }

        @Override
        protected IProperty getOwner()
        {
          return EMFListProperty.this;
        }
      };
  }

  @Override
  public String toString()
  {
    String s = EMFPropertyHelper.propertyName(eStructuralFeature) + "[]"; //$NON-NLS-1$
    s += "<" + EMFPropertyHelper.shortClassName(eStructuralFeature) + ">"; //$NON-NLS-1$//$NON-NLS-2$
    return s;
  }

}
