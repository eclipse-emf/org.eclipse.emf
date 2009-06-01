/**
 * <copyright>
 *
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: PersonFilterDialog.java,v 1.2 2009/06/01 17:19:27 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.dialogs;

import java.util.Comparator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

import org.eclipse.emf.example.databinding.project.ui.rcp.Activator;
import org.eclipse.emf.examples.databinding.project.core.IModelResource;
import org.eclipse.emf.examples.databinding.project.core.model.project.Person;


/**
 * Dialog to find persons
 */
public class PersonFilterDialog extends FilteredItemsSelectionDialog
{
  private final IModelResource resource;

  /**
   * Create a new dialog
   * @param shell the parent shell
   * @param resource the model resource
   */
  public PersonFilterDialog(Shell shell, IModelResource resource)
  {
    super(shell);
    this.resource = resource;

    setListLabelProvider(new LabelProvider()
      {
        @Override
        public String getText(Object element)
        {
          if (element == null)
          {
            return "";
          }
          return PersonFilterDialog.this.getText((Person)element);
        }
      });

    setDetailsLabelProvider(new LabelProvider()
      {
        @Override
        public String getText(Object element)
        {
          if (element == null)
          {
            return "";
          }
          return PersonFilterDialog.this.getText((Person)element);
        }
      });
  }

  private String getText(Person p)
  {
    return p.getLastname() + " " + p.getFirstname();
  }

  @Override
  protected IStatus validateItem(Object item)
  {
    return Status.OK_STATUS;
  }

  @Override
  protected Comparator< ? > getItemsComparator()
  {
    return new Comparator<Person>()
      {

        public int compare(Person o1, Person o2)
        {
          return getText(o1).compareTo(getText(o2));
        }
      };
  }

  @Override
  public String getElementName(Object item)
  {
    Person p = (Person)item;
    return getText(p);
  }

  @Override
  protected IDialogSettings getDialogSettings()
  {
    IDialogSettings settings = Activator.getDefault().getDialogSettings().getSection("committerdialog");
    if (settings == null)
    {
      settings = Activator.getDefault().getDialogSettings().addNewSection("committerdialog");
    }
    return settings;
  }

  @Override
  protected void fillContentProvider(AbstractContentProvider contentProvider, ItemsFilter itemsFilter, IProgressMonitor progressMonitor) throws CoreException
  {
    for (Person p : resource.getFoundation().getPersons())
    {
      if (progressMonitor.isCanceled())
      {
        return;
      }

      contentProvider.add(p, itemsFilter);
    }
  }

  @Override
  protected ItemsFilter createFilter()
  {
    return new ItemsFilter()
      {

        @Override
        public boolean isConsistentItem(Object item)
        {
          return true;
        }

        @Override
        public boolean matchItem(Object item)
        {
          Person p = (Person)item;
          return matches(p.getLastname() + " " + p.getFirstname());
        }

      };
  }

  @Override
  protected Control createExtendedContentArea(Composite parent)
  {
    return null;
  }
}