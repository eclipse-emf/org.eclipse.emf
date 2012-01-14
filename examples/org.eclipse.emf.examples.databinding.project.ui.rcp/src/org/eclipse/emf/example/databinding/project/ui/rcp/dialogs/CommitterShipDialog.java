/**
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.dialogs;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ObservablesManager;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.jface.databinding.dialog.TitleAreaDialogSupport;
import org.eclipse.jface.databinding.swt.IWidgetValueProperty;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditValueProperty;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.example.databinding.project.ui.rcp.databinding.EMFObservablesManager;
import org.eclipse.emf.example.databinding.project.ui.rcp.databinding.UpdateStrategyFactory;
import org.eclipse.emf.examples.databinding.project.core.IModelResource;
import org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip;
import org.eclipse.emf.examples.databinding.project.core.model.project.Person;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage;


/**
 * Dialog to edit the committer ship
 */
public class CommitterShipDialog extends TitleAreaDialog
{
  private final DataBindingContext ctx;
  private final CommitterShip committership;
  final IModelResource resource;
  private final boolean isnew;

  /**
   * Create new dialog
   * @param parentShell the parent shell
   * @param resource the resource
   * @param committership the committership instance
   * @param isnew flag to indicate if the committer ship is new
   */
  public CommitterShipDialog(Shell parentShell, IModelResource resource, CommitterShip committership, boolean isnew)
  {
    super(parentShell);
    this.committership = committership;
    this.resource = resource;
    this.isnew = isnew;
    this.ctx = new EMFDataBindingContext();
  }

  @Override
  protected Control createDialogArea(Composite parent)
  {
    getShell().setText("Edit Committership");
    setTitle("Edit Committership");
    setMessage("Edit the committership of a person in the project");

    final Composite comp = (Composite)super.createDialogArea(parent);

    ObservablesManager mgr = new EMFObservablesManager();
    mgr.runAndCollect(new Runnable()
      {

        public void run()
        {
          createForm(comp).setLayoutData(new GridData(GridData.FILL_BOTH));
        }

      });

    return comp;
  }

  private Control createForm(final Composite comp)
  {

    Composite container = new Composite(comp, SWT.NONE);
    container.setLayout(new GridLayout(3, false));

    {
      Label l = new Label(container, SWT.NONE);
      l.setText("Committer");

      IEMFEditValueProperty mprop = EMFEditProperties.value(resource.getEditingDomain(), ProjectPackage.Literals.COMMITTER_SHIP__PERSON);

      ViewForm form = new ViewForm(container, SWT.BORDER | SWT.FLAT);
      form.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      CLabel label = new CLabel(form, SWT.NONE);
      form.setContent(label);

      ctx.bindValue(
        WidgetProperties.text().observe(label),
        mprop.observe(committership),
        null,
        new EMFUpdateValueStrategy().setConverter(new PersonToStringConverter()));

      Button b = new Button(container, SWT.PUSH);
      b.setText("...");
      b.setEnabled(isnew);
      b.addSelectionListener(new SelectionAdapter()
        {
          @Override
          public void widgetSelected(SelectionEvent e)
          {
            FilteredItemsSelectionDialog dialog = new PersonFilterDialog(comp.getShell(), resource);
            dialog.open();
            Person p = (Person)dialog.getFirstResult();

            if (p != null)
            {
              Command cmd = SetCommand.create(resource.getEditingDomain(), committership, ProjectPackage.Literals.COMMITTER_SHIP__PERSON, p);
              if (cmd.canExecute())
              {
                resource.executeCmd(cmd);
              }
            }
          }
        });
    }

    IWidgetValueProperty textProp = WidgetProperties.text(SWT.Modify);

    {
      Label l = new Label(container, SWT.NONE);
      l.setText("Startdate");

      IEMFEditValueProperty mprop = EMFEditProperties.value(resource.getEditingDomain(), ProjectPackage.Literals.COMMITTER_SHIP__START);

      Text t = new Text(container, SWT.BORDER);
      t.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 2, 1));

      ctx.bindValue(textProp.observe(t), mprop.observe(committership), UpdateStrategyFactory.stringToDateNotNull(
        "Can't convert start date value into a valid date",
        "The start date can not be null"), UpdateStrategyFactory.dateToString());
    }

    {
      Label l = new Label(container, SWT.NONE);
      l.setText("Enddate");

      IEMFEditValueProperty mprop = EMFEditProperties.value(resource.getEditingDomain(), ProjectPackage.Literals.COMMITTER_SHIP__END);

      Text t = new Text(container, SWT.BORDER);
      t.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 2, 1));

      ctx.bindValue(
        textProp.observe(t),
        mprop.observe(committership),
        UpdateStrategyFactory.stringToDate("Can't convert end date value into a valid date"),
        UpdateStrategyFactory.dateToString());
    }

    TitleAreaDialogSupport.create(this, ctx);

    return container;
  }

  private class PersonToStringConverter extends Converter
  {
    public PersonToStringConverter()
    {
      super(Person.class, String.class);
    }

    public Object convert(Object fromObject)
    {
      if (fromObject != null)
      {
        Person p = (Person)fromObject;
        return p.getLastname() + ", " + p.getFirstname();
      }

      return null;
    }
  };
}