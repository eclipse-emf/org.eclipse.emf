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
 * $Id: InitializeCopyCommand.java,v 1.3 2004/05/14 19:39:11 emerks Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * The initialize copy command is implementated to set the values of an object copy based on those
 * of the original (copied) object. It is a helper command used by the CopyCommand.
 *
 * <p>
 * An initialize copy command is an {@link OverrideableCommand}.
 */
public class InitializeCopyCommand extends AbstractOverrideableCommand
{
  public static Command create(EditingDomain domain, Object owner, CopyCommand.Helper copyHelper) 
  {
    return domain.createCommand(InitializeCopyCommand.class, new CommandParameter(owner, null, copyHelper));
  }

  /**
   * This caches the label.
   */
  protected static final String LABEL = EMFEditPlugin.INSTANCE.getString("_UI_InitializeCopyCommand_label");

  /**
   * This caches the description.
   */
  protected static final String DESCRIPTION = EMFEditPlugin.INSTANCE.getString("_UI_InitializeCopyCommand_description");

  /**
   * This is the object being copied.
   */
  protected EObject owner;

  /**
   * This is the object (copy) being initialized.
   */
  protected EObject copy;

  /**
   * This is a map of objects to their copies
   */
  protected CopyCommand.Helper copyHelper;

  /**
   * This constructs an instance that will copy the attribute values of value to those of owner.
   */
  public InitializeCopyCommand(EditingDomain domain, EObject owner, CopyCommand.Helper copyHelper) 
  {
    super(domain, LABEL, DESCRIPTION);

    this.owner = owner;
    this.copy = copyHelper.getCopy(owner);
    this.copyHelper = copyHelper;
  }

  /**
   * This is the object being copied.
   */
  public EObject getOwner()
  {
    return owner;
  }

  /**
   * This is the object (copy) being initialized.
   */
  public EObject getCopy()
  {
    return copy;
  }

  /**
   * This is the map of objects to their copies.
   */
  public CopyCommand.Helper getCopyHelper()
  {
    return copyHelper;
  }

  protected boolean prepare()
  {
    return owner.eClass().isInstance(copy);
  }

  public void doExecute()
  {
    copyAttributes();
    copyReferences();
  }

  protected Collection getAttributesToCopy()
  {
    return owner.eClass().getEAllAttributes();
  }

  /**
   * This method will iterate over the attributes of the owner object and set them
   * accordingly in the copy.
   */
  protected void copyAttributes()
    {
    for (Iterator attributes = getAttributesToCopy().iterator(); attributes.hasNext(); )
    {
      EAttribute attribute = (EAttribute) attributes.next();
      if (attribute.isChangeable() && !attribute.isDerived() && (attribute.isMany() || owner.eIsSet(attribute)))
      {
        Object value = owner.eGet(attribute);
        if (!attribute.isMany())
        {
          copy.eSet(attribute, value);
        }
        else
        {
          List list = (List)copy.eGet(attribute);
          if (FeatureMapUtil.isFeatureMap(attribute))
          {
            FeatureMap featureMap = (FeatureMap)list;
            LOOP:
            for (Iterator i = ((List)value).iterator(); i.hasNext(); )
            {
              FeatureMap.Entry entry = (FeatureMap.Entry)i.next();
              EStructuralFeature entryFeature = entry.getEStructuralFeature();
              if (entryFeature instanceof EAttribute)
              {
                featureMap.add(entry);
              }
              else
              {
                EReference reference = (EReference)entryFeature;
                EReference reverseReference = reference.getEOpposite();
                Object entryValue = entry.getValue();
                boolean copiedTargetRequired = reverseReference != null || reference.isContainment();
                EObject target = copyHelper.getCopyTarget((EObject)entryValue, copiedTargetRequired);
                if (target != null)
                {
                  if (reverseReference != null)
                  {
                    for (Iterator j = featureMap.iterator(); j.hasNext(); )
                    {
                      FeatureMap.Entry copyEntry = (FeatureMap.Entry)j.next();
                      if (copyEntry.getEStructuralFeature() == reference && copyEntry.getValue() == target)
                      {
                        featureMap.move(featureMap.size() - 1, copyEntry);
                        continue LOOP;
                      }
                    }
                  }
                  featureMap.add(reference, target);
                }
              }
            }
          }
          else
          {
            list.addAll((List)value);
          }
        }
      }
    }
  }


  protected Collection getReferencesToCopy()
  {
    return owner.eClass().getEAllReferences();
  }

  /**
   * This method will iterate over the references of the owner object and sets them.
   * accordingly in the copy.
   */
  protected void copyReferences()
  {
    for (Iterator references = getReferencesToCopy().iterator(); references.hasNext(); ) 
    {
      EReference reference = (EReference) references.next();
      if (!reference.isChangeable() || reference.isDerived())
      {
        continue;
      }

      EReference reverseReference = reference.getEOpposite();

      Object value = owner.eGet(reference);
      if (value == null)
      {
//      copy.eSet(reference, null); // is it possible to not be null already?
        continue;
      }

      boolean copiedTargetRequired = reverseReference != null || reference.isContainment();
      if (reference.isMany())
      {
        List valueList = (List) value;
        if (!valueList.isEmpty())
        {
          EList copyList = (EList) copy.eGet(reference);
          int index = 0;
          for (Iterator valueIter = valueList.iterator(); valueIter.hasNext(); ++index)
          {
            EObject target = copyHelper.getCopyTarget((EObject) valueIter.next(), copiedTargetRequired);
            if (target == null) break; // if one is null, they'll all be null
            if (reverseReference != null)
            {
              int position = copyList.indexOf(target);
              if (position == -1)
              {
                copyList.add(index, target);
              }
              else
              {
                copyList.move(index, target);
              }
            }
            else
            {
              copyList.add(target);
            }
          }
        }
      }
      else
      {
        EObject target = copyHelper.getCopyTarget((EObject) value, copiedTargetRequired);
        if (target != null)
        {
          copy.eSet(reference, target);
        }
      }
    }
  }

  public void doUndo() 
  {
    // no-op
  }

  public void doRedo()
  {
    // no-op
  }

  public Collection doGetResult()
  {
    return Collections.singleton(copy);
  }

  public Collection doGetAffectedObjects()
  {
    return Collections.singleton(copy);
  }

  /**
   * This gives an abbreviated name using this object's own class' name, without package qualification,
   * followed by a space separated list of <tt>field:value</tt> pairs.
   */
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString() + ")");
    result.append(" (domain: " + domain + ")");
    result.append(" (owner: " + owner + ")");
    result.append(" (copy: " + copy + ")");
    result.append(" (copyHelper: " + copyHelper + ")");

    return result.toString();
  }
}
