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
 * $Id: ProjectFormAreaPart.java,v 1.6 2009/06/07 17:45:50 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.databinding.AggregateValidationStatus;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.IWidgetValueProperty;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.IEMFListProperty;
import org.eclipse.emf.databinding.IEMFValueProperty;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.example.databinding.project.ui.rcp.Activator;
import org.eclipse.emf.example.databinding.project.ui.rcp.NLSMessages;
import org.eclipse.emf.example.databinding.project.ui.rcp.databinding.FormTextProperty;
import org.eclipse.emf.example.databinding.project.ui.rcp.databinding.UpdateStrategyFactory;
import org.eclipse.emf.example.databinding.project.ui.rcp.databinding.Util;
import org.eclipse.emf.example.databinding.project.ui.rcp.dialogs.PersonFilterDialog;
import org.eclipse.emf.examples.databinding.project.core.IModelResource;
import org.eclipse.emf.examples.databinding.project.core.model.project.Person;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage;


/**
 * Part creating the form area
 */
public class ProjectFormAreaPart
{
  private DataBindingContext ctx;
  private Form form;
  private ProjectCommittersPart committerPart;
  private Image projectImage;
  private AggregateValidationStatus aggregateStatus;

  /**
   * Create a new part instance
   * @param site the site
   * @param parent the parent composite
   * @param toolkit the form toolkit to use
   * @param resource the resource
   * @param master the master observable
   */
  public ProjectFormAreaPart(IViewSite site, Composite parent, FormToolkit toolkit, IModelResource resource, IObservableValue master)
  {
    ImageDescriptor desc = Activator.imageDescriptorFromPlugin("org.eclipse.ui", "icons/full/obj16/generic_elements.gif");
    if (desc != null)
    {
      projectImage = desc.createImage();
    }
    else
    {
      projectImage = null;
    }
    createFormArea(site, parent, toolkit, resource, master);
  }

  private void createFormArea(
    IViewSite site,
    final Composite parent,
    FormToolkit toolkit,
    final IModelResource resource,
    final IObservableValue master)
  {

    final EditingDomain editingDomain = resource.getEditingDomain();

    ctx = new EMFDataBindingContext();
    // Fix for bug 278301
    Util.masterDetailFixup(ctx, master);

    addStatusSupport(ctx);

    form = toolkit.createForm(parent);
    toolkit.decorateFormHeading(form);
    form.setText(" ");
    form.setImage(projectImage);

    Composite body = form.getBody();
    body.setLayout(new GridLayout(3, false));

    IWidgetValueProperty prop = WidgetProperties.text(SWT.Modify);

    {
      final IEMFValueProperty shortProp = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.PROJECT__SHORTNAME);;
      toolkit.createLabel(body, "&Short name");
      Text t = toolkit.createText(body, "");
      t.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 2, 1));
      ctx.bindValue(prop.observeDelayed(400, t), shortProp.observeDetail(master));

      final IEMFValueProperty longProp = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.PROJECT__LONGNAME);
      toolkit.createLabel(body, "&Long name");
      t = toolkit.createText(body, "");
      t.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 2, 1));
      ctx.bindValue(prop.observeDelayed(400, t), longProp.observeDetail(master));

      ctx.bindValue(FormTextProperty.create().observe(form), new ComputedValue()
        {
          private IObservableValue shortname = shortProp.observeDetail(master);
          private IObservableValue longname = longProp.observeDetail(master);

          @Override
          protected Object calculate()
          {
            return shortname.getValue() + " - " + longname.getValue();
          }
        });
    }

    {
      IEMFValueProperty mProp = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.PROJECT__START);
      toolkit.createLabel(body, "Start Date");
      Text t = toolkit.createText(body, "");
      t.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 2, 1));
      ctx.bindValue(prop.observeDelayed(400, t), mProp.observeDetail(master), UpdateStrategyFactory.stringToDateNotNull(
        NLSMessages.ProjectAdminViewPart_StartDateNotParseable,
        "Start date must not be null"), UpdateStrategyFactory.dateToString());
    }

    {
      IEMFValueProperty mProp = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.PROJECT__END);
      toolkit.createLabel(body, "End Date");
      Text t = toolkit.createText(body, "");
      t.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 2, 1));
      ctx.bindValue(
        prop.observeDelayed(400, t),
        mProp.observeDetail(master),
        UpdateStrategyFactory.stringToDate(NLSMessages.ProjectAdminViewPart_EndDateNotParseable),
        UpdateStrategyFactory.dateToString());
    }

    {
      IEMFValueProperty mProp = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.PROJECT__HOMEPAGE);
      toolkit.createLabel(body, "Homepage");
      Text t = toolkit.createText(body, "");
      t.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 2, 1));
      ctx.bindValue(prop.observeDelayed(400, t), mProp.observeDetail(master));
    }

    {
      IEMFValueProperty mProp = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.PROJECT__DEVMAIL);
      toolkit.createLabel(body, "Dev-Mail");
      Text t = toolkit.createText(body, "");
      t.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 2, 1));
      ctx.bindValue(prop.observeDelayed(400, t), mProp.observeDetail(master));
    }

    {
      IEMFListProperty mProp = EMFEditProperties.list(editingDomain, ProjectPackage.Literals.PROJECT__PROJECTLEADS);
      toolkit.createLabel(body, "Project Leads").setLayoutData(new GridData(SWT.TOP, SWT.DEFAULT, false, false));

      Table c = toolkit.createTable(body, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
      final TableViewer tv = new TableViewer(c);
      tv.setLabelProvider(new ColumnLabelProvider()
        {
          @Override
          public String getText(Object element)
          {
            Person p = (Person)element;
            return p.getLastname() + ", " + p.getFirstname();
          }
        });
      tv.setContentProvider(new ObservableListContentProvider());
      tv.setInput(mProp.observeDetail(master));

      GridData gd = new GridData(GridData.FILL_HORIZONTAL);
      gd.heightHint = 100;
      c.setLayoutData(gd);

      Composite buttonContainer = toolkit.createComposite(body);
      buttonContainer.setLayoutData(new GridData(SWT.DEFAULT, SWT.BOTTOM, false, false));
      buttonContainer.setLayout(new GridLayout());

      Button b = toolkit.createButton(buttonContainer, "Add ...", SWT.FLAT);
      gd = new GridData(SWT.DEFAULT, SWT.DEFAULT);
      gd.horizontalAlignment = SWT.FILL;
      b.setLayoutData(gd);
      b.addSelectionListener(new SelectionAdapter()
        {
          @Override
          public void widgetSelected(SelectionEvent e)
          {
            PersonFilterDialog dialog = new PersonFilterDialog(parent.getShell(), resource);
            if (dialog.open() == IDialogConstants.OK_ID)
            {
              Command cmd = AddCommand.create(
                editingDomain,
                master.getValue(),
                ProjectPackage.Literals.PROJECT__PROJECTLEADS,
                dialog.getFirstResult());
              if (cmd.canExecute())
              {
                resource.executeCmd(cmd);
              }
            }
          }
        });
      b = toolkit.createButton(buttonContainer, "Remove", SWT.FLAT);
      gd = new GridData(SWT.DEFAULT, SWT.DEFAULT);
      gd.horizontalAlignment = SWT.FILL;
      b.setLayoutData(gd);
      b.addSelectionListener(new SelectionAdapter()
        {
          @Override
          public void widgetSelected(SelectionEvent e)
          {
            IStructuredSelection s = (IStructuredSelection)tv.getSelection();
            if (!s.isEmpty())
            {
              Command cmd = RemoveCommand.create(
                editingDomain,
                master.getValue(),
                ProjectPackage.Literals.PROJECT__PROJECTLEADS,
                s.toList());
              if (cmd.canExecute())
              {
                resource.executeCmd(cmd);
              }
            }
          }
        });
    }

    addTabArea(site, body, toolkit, ctx, editingDomain, master);
    body.setBackgroundMode(SWT.INHERIT_DEFAULT);
  }

  private void addTabArea(
    IViewSite site,
    Composite parent,
    FormToolkit toolkit,
    DataBindingContext ctx,
    EditingDomain editingDomain,
    IObservableValue master)
  {
    CTabFolder folder = new CTabFolder(parent, SWT.BORDER);
    folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
    folder.setSelectionBackground(new Color []{
      toolkit.getColors().getColor(IFormColors.H_GRADIENT_END),
      toolkit.getColors().getColor(IFormColors.H_GRADIENT_START) }, new int []{ 25 }, true);
    folder.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
    folder.setSelectionForeground(toolkit.getColors().getColor(IFormColors.TITLE));
    folder.setSimple(false);

    committerPart = new ProjectCommittersPart(site, folder, ctx, editingDomain, master);
  }

  private void addStatusSupport(final DataBindingContext ctx)
  {
    aggregateStatus = new AggregateValidationStatus(ctx.getValidationStatusProviders(), AggregateValidationStatus.MAX_SEVERITY);

    aggregateStatus.addValueChangeListener(new IValueChangeListener()
      {
        public void handleValueChange(ValueChangeEvent event)
        {
          handleStateChange((IStatus)event.diff.getNewValue(), ctx);
        }
      });
  }

  private void handleStateChange(IStatus currentStatus, DataBindingContext ctx)
  {
    if (form.isDisposed() || form.getHead().isDisposed())
    {
      return;
    }

    if (currentStatus != null && currentStatus.getSeverity() != IStatus.OK)
    {
      int type = convertType(currentStatus.getSeverity());

      List<IMessage> list = new ArrayList<IMessage>();
      for (Iterator< ? > it = ctx.getValidationStatusProviders().iterator(); it.hasNext();)
      {
        ValidationStatusProvider validationStatusProvider = (ValidationStatusProvider)it.next();
        final IStatus status = (IStatus)validationStatusProvider.getValidationStatus().getValue();
        if (!status.isOK())
        {
          list.add(new IMessage()
            {

              public Control getControl()
              {
                return null;
              }

              public Object getData()
              {
                return null;
              }

              public Object getKey()
              {
                return null;
              }

              public String getPrefix()
              {
                return null;
              }

              public String getMessage()
              {
                return status.getMessage();
              }

              public int getMessageType()
              {
                return convertType(status.getSeverity());
              }

            });
        }
      }

      form.setMessage("Data invalid", type, list.toArray(new IMessage [0]));
    }
    else
    {
      form.setMessage(null);
    }
  }

  private int convertType(int severity)
  {
    switch (severity)
    {
      case IStatus.OK:
        return IMessageProvider.NONE;
      case IStatus.CANCEL:
        return IMessageProvider.NONE;
      case IStatus.INFO:
        return IMessageProvider.INFORMATION;
      case IStatus.WARNING:
        return IMessageProvider.WARNING;
      case IStatus.ERROR:
        return IMessageProvider.ERROR;
      default:
        return IMessageProvider.NONE;
    }
  }

  /**
   * release all sources allocated
   */
  public void dispose()
  {
    if (aggregateStatus != null)
    {
      aggregateStatus.dispose();
    }

    if (projectImage != null)
    {
      projectImage.dispose();
    }

    committerPart.dispose();

    if (ctx != null)
    {
      ctx.dispose();
    }
  }
}
