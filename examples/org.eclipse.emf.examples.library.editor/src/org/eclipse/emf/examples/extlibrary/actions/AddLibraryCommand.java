/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.examples.extlibrary.actions;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandActionDelegate;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;


/**
 * The extended library example supports multi-rooted resources
 * and therefore this command will add a library to the resource.
 */
public class AddLibraryCommand extends AddCommand implements CommandActionDelegate
{
  /* Attribute for the adapter factory */
  private IItemLabelProvider labelProvider = null;

  /**
   * Constructor
   * 
   * @param domain
   *            the editing domain
   * @param list
   *            the list to add to
   * @param value
   *            the value to add
   */
  public AddLibraryCommand(EditingDomain domain, EList<?> list, Object value)
  {
    super(domain, list, value);

    if (domain instanceof AdapterFactoryEditingDomain)
    {
      labelProvider = (IItemLabelProvider)((AdapterFactoryEditingDomain)domain).getAdapterFactory().adapt(value, IItemLabelProvider.class);
    }

    setLabel(EMFEditPlugin.INSTANCE.getString("_UI_CreateChildCommand_label", new Object []{ getText() })); //$NON-NLS-1$
    setDescription(EMFEditPlugin.INSTANCE.getString("_UI_CreateChildCommand_description")); //$NON-NLS-1$
  }

  /*
   * @see org.eclipse.emf.edit.command.CommandActionDelegate#getImage()
   */
  public Object getImage()
  {
    return this.labelProvider != null ? this.labelProvider.getImage(getCollection().toArray()[0]) : null;
  }

  /*
   * @see org.eclipse.emf.edit.command.CommandActionDelegate#getText()
   */
  public String getText()
  {
    return this.labelProvider != null ? this.labelProvider.getText(getCollection().toArray()[0]) : null;
  }

  /*
   * @see org.eclipse.emf.edit.command.CommandActionDelegate#getToolTipText()
   */
  public String getToolTipText()
  {
    return getText();
  }
}