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
 * $Id: PersonDialog.java,v 1.2 2009/06/01 17:19:27 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.dialogs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ObservablesManager;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.dialog.TitleAreaDialogSupport;
import org.eclipse.jface.databinding.swt.IWidgetValueProperty;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditValueProperty;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.example.databinding.project.ui.rcp.Activator;
import org.eclipse.emf.example.databinding.project.ui.rcp.databinding.Base64ToImageConverter;
import org.eclipse.emf.example.databinding.project.ui.rcp.databinding.EmptyStringValidator;
import org.eclipse.emf.examples.databinding.project.core.model.project.Person;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage;


/**
 * Dialog to edit person data
 */
public class PersonDialog extends TitleAreaDialog
{
  private final Person person;
  private final EditingDomain editingDomain;
  private final DataBindingContext ctx;

  /**
   * Create a new dialog
   * @param parentShell
   * @param editingDomain
   * @param person
   */
  public PersonDialog(Shell parentShell, EditingDomain editingDomain, Person person)
  {
    super(parentShell);
    this.person = person;
    this.editingDomain = editingDomain;
    this.ctx = new EMFDataBindingContext();
  }

  @Override
  protected Control createDialogArea(Composite parent)
  {
    getShell().setText("Edit Person");
    setTitle("Edit Person");
    setMessage("Edit the data of the person and add an image (Max. 200KB and 200x200px)");

    final Composite comp = (Composite)super.createDialogArea(parent);

    ObservablesManager mgr = new ObservablesManager();
    mgr.runAndCollect(new Runnable()
      {

        public void run()
        {
          createForm(comp).setLayoutData(new GridData(GridData.FILL_BOTH));
        }
      });

    return comp;
  }

  private Composite createForm(Composite parent)
  {
    final Composite container = new Composite(parent, SWT.NONE);
    container.setLayout(new GridLayout(2, false));

    final Composite imgContainer = new Composite(container, SWT.NONE);
    
    {
      imgContainer.setLayout(new GridLayout());
      GridData gd = new GridData(200, SWT.DEFAULT);
      gd.verticalAlignment=SWT.TOP;
      imgContainer.setLayoutData(gd);

      final Label l = new Label(imgContainer, SWT.BORDER);
      gd = new GridData(SWT.CENTER, SWT.FILL, false, false);
      l.setLayoutData(gd);

      IEMFEditValueProperty mprop = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.PERSON__IMAGE);

      ctx.bindValue(
        WidgetProperties.image().observe(l),
        mprop.observe(person),
        null,
        new EMFUpdateValueStrategy().setConverter(new Base64ToImageConverter(container.getDisplay())));

      l.addDisposeListener(new DisposeListener()
        {

          public void widgetDisposed(DisposeEvent e)
          {
            if (l.getImage() != null)
            {
              l.getImage().dispose();
            }
          }
        });

      final Button button = new Button(imgContainer, SWT.PUSH);
      button.setText("Select Image ...");
      button.addSelectionListener(new SelectionAdapter()
        {
          @Override
          public void widgetSelected(SelectionEvent e)
          {
            showImageSelectionDialog(container.getShell(),container);
          }
        });
    }

    Composite formContainer = new Composite(container, SWT.NONE);
    formContainer.setLayout(new GridLayout(2, false));
    formContainer.setLayoutData(new GridData(GridData.FILL_BOTH));

    IWidgetValueProperty textProp = WidgetProperties.text(SWT.Modify);
    {
      Label l = new Label(formContainer, SWT.NONE);
      l.setText("Firstname");

      IEMFEditValueProperty mprop = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.PERSON__FIRSTNAME);

      Text t = new Text(formContainer, SWT.BORDER);
      t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      ctx.bindValue(
        textProp.observeDelayed(400, t),
        mprop.observe(person),
        new EMFUpdateValueStrategy().setBeforeSetValidator(new EmptyStringValidator("Firstname must not be empty")),
        null);
    }

    {
      Label l = new Label(formContainer, SWT.NONE);
      l.setText("Lastname");

      IEMFEditValueProperty mprop = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.PERSON__LASTNAME);

      Text t = new Text(formContainer, SWT.BORDER);
      t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      ctx.bindValue(
        textProp.observeDelayed(400, t),
        mprop.observe(person),
        new EMFUpdateValueStrategy().setBeforeSetValidator(new EmptyStringValidator("Lastname must not be empty")),
        null);
    }

    {
      Label l = new Label(formContainer, SWT.NONE);
      l.setText("E-Mail");

      IEMFEditValueProperty mprop = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.PERSON__EMAIL);

      Text t = new Text(formContainer, SWT.BORDER);
      t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      ctx.bindValue(
        textProp.observeDelayed(400, t),
        mprop.observe(person),
        new EMFUpdateValueStrategy().setBeforeSetValidator(new EmptyStringValidator("E-Mail must not be empty")),
        null);
    }

    TitleAreaDialogSupport.create(this, ctx);

    return container;
  }

  private void showImageSelectionDialog(Shell parent, Composite imgContainer)
  {
    FileDialog dialog = new FileDialog(parent);
    dialog.setFilterExtensions(new String []{ "*.png", "*.jpg" });
    String file = dialog.open();

    if (file != null)
    {
      try
      {
        File f = new File(file);
        if (f.length() < 1024 * 200) // 200KB
        {
          FileInputStream in = new FileInputStream(f);
          byte[] buf = new byte [1024];
          int len;
          ByteArrayOutputStream out = new ByteArrayOutputStream();

          while ((len = in.read(buf)) != -1)
          {
            out.write(buf, 0, len);
          }
          in.close();
          String enc = XMLTypeFactory.eINSTANCE.convertBase64Binary(out.toByteArray());
          out.close();
          Command cmd = SetCommand.create(editingDomain, person, ProjectPackage.Literals.PERSON__IMAGE, enc);
          if (cmd.canExecute())
          {
            editingDomain.getCommandStack().execute(cmd);
          }

          imgContainer.layout(true);
        }
        else
        {
          ErrorDialog.openError(parent, "Image too big", "The image selected is too big. The maximum file size is 200KB", new Status(
            IStatus.ERROR,
            Activator.PLUGIN_ID,
            ""), SWT.SHEET);
        }
      }
      catch (FileNotFoundException e)
      {
        e.printStackTrace();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  @Override
  public boolean close()
  {
    ctx.dispose();
    return super.close();
  }
}
