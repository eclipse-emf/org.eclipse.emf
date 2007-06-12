/**
 * <copyright>
 *
 * Copyright (c) 2005-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ModelConverterURIPage.java,v 1.4 2007/06/12 19:51:17 marcelop Exp $
 */
package org.eclipse.emf.converter.ui.contribution.base;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import org.eclipse.emf.converter.ConverterPlugin;
import org.eclipse.emf.converter.ModelConverter;


/**
 * @since 2.2.0
 */
public class ModelConverterURIPage extends ModelConverterPage
{
  protected Text uriText;
  protected Button browseFileSystemButton;
  protected Button browseWorkspaceButton;

  public ModelConverterURIPage(ModelConverter modelConverter, String pageName)
  {
    super(modelConverter, pageName);
  }
  
  @Override
  public void dispose()
  {
    if (uriText != null)
    {
      uriText.removeListener(SWT.Modify, this);
      uriText = null;
    }
    if (browseFileSystemButton != null)
    {
      browseFileSystemButton.removeListener(SWT.Selection, this);
      browseFileSystemButton = null;
    }
    if (browseWorkspaceButton != null)
    {
      browseWorkspaceButton.removeListener(SWT.Selection, this);
      browseWorkspaceButton = null;
    }

    super.dispose();
  }

  public void createControl(Composite parent)
  {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL));

    GridLayout layout = new GridLayout();
    layout.verticalSpacing = 8;
    composite.setLayout(layout);

    createURIControl(composite);
    addControl(composite);
    setControl(composite);
  }

  protected void createURIControl(Composite parent)
  {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));
    {
      FormLayout layout = new FormLayout();
      layout.marginTop = 10;
      layout.spacing = 10;
      composite.setLayout(layout);
    }
    
    Label uriLabel = new Label(composite, SWT.LEFT);
    {
      FormData data = new FormData();
      data.left = new FormAttachment(0);
      uriLabel.setLayoutData(data);      
    }
    uriLabel.setText(getURITextLabel());

    Composite uriComposite = new Composite(composite, SWT.NONE);
    {
      FormData data = new FormData();
      data.top = new FormAttachment(uriLabel, 5);
      data.left = new FormAttachment(0);
      data.right = new FormAttachment(100);
      uriComposite.setLayoutData(data);

      GridLayout layout = new GridLayout(2, false);
      layout.marginTop = -5;
      layout.marginLeft = -5;
      layout.marginRight = -5;
      uriComposite.setLayout(layout);
    }
    
    Composite buttonComposite = new Composite(composite, SWT.NONE);
    {
      FormData data = new FormData();
      data.top = new FormAttachment(uriLabel, 0, SWT.CENTER);
      data.left = new FormAttachment(uriLabel, 0);
      data.right = new FormAttachment(100);
      buttonComposite.setLayoutData(data);      

      FormLayout layout = new FormLayout();
      layout.marginTop = 0;
      layout.marginBottom = 0;
      layout.marginLeft = 0;
      layout.marginRight = 0;
      layout.spacing = 5;
      buttonComposite.setLayout(layout);
    }

    browseFileSystemButton = new Button(buttonComposite, SWT.PUSH);
    browseFileSystemButton.setText(getBrowseFileSystemButtonLabel());
    browseFileSystemButton.addListener(SWT.Selection, this);

    browseWorkspaceButton = new Button(buttonComposite, SWT.PUSH);
    browseWorkspaceButton.setText(getBrowseWorkspaceButtonLabel());
    browseWorkspaceButton.addListener(SWT.Selection, this);

    {
      FormData data = new FormData();
      data.right = new FormAttachment(browseWorkspaceButton);
      browseFileSystemButton.setLayoutData(data);      
    }

    {
      FormData data = new FormData();
      data.right = new FormAttachment(100);
      browseWorkspaceButton.setLayoutData(data);      
    }
     
    uriText = new Text(uriComposite, SWT.SINGLE | SWT.BORDER);
    setURIText(getURITextInitialValue());
    if (uriText.getText().length() > 0)
    {
      uriText.selectAll();
    }
    uriText.addListener(SWT.Modify, this);
    
    addURIControl(uriComposite);

    {
      GridData gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
      if (uriComposite.getChildren().length == 1)
      {
        gridData.horizontalSpan = 2;
      }
      uriText.setLayoutData(gridData);      
    }
  }
  
  protected String getURITextLabel()
  {
    return "";
  }

  protected String getBrowseFileSystemButtonLabel()
  {
    return ConverterPlugin.INSTANCE.getString("_UI_BrowseFileSystemFile_label");
  }

  protected String getBrowseWorkspaceButtonLabel()
  {
    return ConverterPlugin.INSTANCE.getString("_UI_BrowseWorkspace_label");
  }

  protected String getURITextInitialValue()
  {
    return "";
  }

  protected void addURIControl(Composite parent)
  {
    // Subclasses may override
  }

  protected void addControl(Composite parent)
  {
    // Subclasses may override
  }
  
  @Override
  protected void doHandleEvent(Event event)
  {
    if (event.type == SWT.Modify && event.widget == uriText)
    {
      uriTextModified(uriText.getText().trim());
    }
    else if (event.type == SWT.Selection && event.widget == browseFileSystemButton)
    {
      browseFileSystem();
    }
    else if (event.type == SWT.Selection && event.widget == browseWorkspaceButton)
    {
      browseWorkspace();
    }
    else
    {
      super.doHandleEvent(event);
    }
    
    getContainer().updateButtons();
  }
  
  protected void uriTextModified(String text)
  {
    setErrorMessage(null);
    setMessage(null);
  }
  
  protected boolean supportMultipleURIs()
  {
    return false;
  }

  protected void setURIText(String uri)
  {
    uri = uri.trim();
    StringBuffer text = new StringBuffer(uriText.getText());
    if (!uri.equals(text))
    {
      if (supportMultipleURIs())
      {
        Point textSelection = uriText.getSelection();
        text.delete(textSelection.x, textSelection.y);
        uri = text.append(" ").append(uri).toString();
      }
      uriText.setText(uri.trim());
    }
  }

  protected boolean browseFileSystem()
  {
    return true;
  }

  protected boolean browseWorkspace()
  {
    return true;
  }
}
