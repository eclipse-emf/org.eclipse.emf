/**
 * <copyright>
 *
 * Copyright (c) 2006`-2007 IBM Corporation and others.
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
 * $Id: ProblemEditorPart.java,v 1.5 2007/01/06 22:06:38 marcelop Exp $
 */
package org.eclipse.emf.common.ui.editor;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import org.eclipse.emf.common.ui.CommonUIPlugin;
import org.eclipse.emf.common.ui.MarkerHelper;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;

/**
 * @since 2.2.0
 */
public class ProblemEditorPart extends EditorPart
{
  /**
   * Since 2.3.0
   */
  public static class TextProvider
  {
    /**
     * Returns the message to be displayed next to the icon, at the top 
     * of the editor.
     * @return a not null String
     */    
    public String getMessageText(Diagnostic rootDiagnostic)
    {
      return rootDiagnostic.getSeverity() == Diagnostic.OK
      ? CommonUIPlugin.getPlugin().getString("_UI_NoProblems_message") : 
        rootDiagnostic.getMessage() != null
        ? rootDiagnostic.getMessage() : CommonUIPlugin.getPlugin().getString("_UI_DefaultProblem_message");      
    }
    
    /**
     * Returns the text associated to be displayed in the details text
     * when a a dignostic is selected in the tree.
     * @param diagnostic
     * @return a not null String
     */
    public String getDetailsText(Diagnostic diagnostic)
    {
      Throwable throwable = diagnostic.getException();
      if (throwable != null)
      {
        StringWriter in = new StringWriter();
        PrintWriter ps = new PrintWriter(in);
        throwable.printStackTrace(ps);
        return in.getBuffer().toString();
      }
      return "";      
    }
  }
  
  protected Diagnostic diagnostic;

  protected String editorToOpen;

  protected Label imageLabel;
  protected Text messageText;
  protected Button detailsButton;
  protected Composite detailsComposite;
  protected Control detailsControl;
  protected TreeViewer detailsTreeViewer;
  protected Text detailsText;
  
  protected TextProvider textProvider = new TextProvider();
  protected MarkerHelper markerUtil;  
  
  /**
   * Deprecated in 2.3.0.  Use detailsText instead.  This field will be
   * removed in futture versions
   */
  @Deprecated
  protected Text exceptionText;

  @Override
  public void dispose()
  {
    diagnostic = null;
    imageLabel = null;
    messageText = null;
    detailsButton = null;
    detailsComposite = null;
    detailsControl = null;
    detailsTreeViewer = null;
    detailsText = null;
    textProvider = null;
    markerUtil = null;
    exceptionText = null;

    super.dispose();
  }

  public void setMarkerHelper(MarkerHelper markerHelper)
  {
    this.markerUtil = markerHelper;
  }

  public MarkerHelper getMarkerHelper()
  {
    return markerUtil;
  }
  
  public void setTextProvider(TextProvider textProvider)
  {
    this.textProvider = textProvider;
  }
  
  public TextProvider getTextProvider()
  {
    return textProvider;
  }

  @Override
  public void init(IEditorSite site, IEditorInput input) throws PartInitException
  {
    setSite(site);
    setInput(input);
    setPartName(CommonUIPlugin.getPlugin().getString("_UI_Problems_label"));
  }

  @Override
  public void createPartControl(Composite parent)
  {
    {
      GridLayout layout = new GridLayout();
      layout.numColumns = 3;
      int spacing = 8;
      int margins = 8;
      layout.marginBottom = margins;
      layout.marginTop = margins;
      layout.marginLeft = margins;
      layout.marginRight = margins;
      layout.horizontalSpacing = spacing;
      layout.verticalSpacing = spacing;
      parent.setLayout(layout);
    }

    imageLabel = new Label(parent, SWT.NONE);

    messageText = new Text(parent, SWT.MULTI | SWT.READ_ONLY | SWT.WRAP | SWT.NO_FOCUS);
    messageText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
    messageText.setBackground(messageText.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));

    Composite buttonComposite = new Composite(parent, SWT.NONE);
    buttonComposite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_END));
    buttonComposite.setLayout(new GridLayout());
    {
      GridLayout layout = new GridLayout();
      int spacing = 3;
      layout.marginTop = -5;
      layout.marginRight = -5;
      layout.horizontalSpacing = spacing;
      layout.verticalSpacing = spacing;
      buttonComposite.setLayout(layout);
    }

    editorToOpen = computeEditorToOpen();
    if (editorToOpen != null)
    {
      Button openButton = new Button(buttonComposite, SWT.PUSH);
      openButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL));
      openButton.setText(CommonUIPlugin.getPlugin().getString("_UI_ErrorEditor_OpenEditor_label"));
      openButton.addSelectionListener(new SelectionAdapter()
        {
          @Override
          public void widgetSelected(SelectionEvent e)
          {
            openEditor();
          }
        });
    }

    if (markerUtil != null)
    {
      Button createMarkersButton = new Button(buttonComposite, SWT.PUSH);
      createMarkersButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL));
      createMarkersButton.setText(CommonUIPlugin.getPlugin().getString("_UI_ErrorEditor_CreateMarkers_label"));
      createMarkersButton.addSelectionListener(new SelectionAdapter()
        {
          @Override
          public void widgetSelected(SelectionEvent e)
          {
            createMarkers();
          }
        });
    }

    detailsButton = new Button(buttonComposite, SWT.PUSH);
    detailsButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL));
    detailsButton.setData(Boolean.FALSE);
    detailsButton.addSelectionListener(new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent e)
        {
          toggleDetails();
        }
      });
    updateDetails();

    detailsComposite = new Composite(parent, SWT.NONE);
    GridData data = new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL);
    data.horizontalSpan = 3;
    data.verticalSpan = 2;
    detailsComposite.setLayoutData(data);
    {
      GridLayout layout = new GridLayout();
      int margin = -5;
      int spacing = 3;
      layout.marginTop = margin;
      layout.marginLeft = margin;
      layout.marginRight = margin;
      layout.marginBottom = margin;
      layout.horizontalSpacing = spacing;
      layout.verticalSpacing = spacing;
      detailsComposite.setLayout(layout);
    }

    refresh();
    parent.layout(true);
  }

  public Diagnostic getDiagnostic()
  {
    return diagnostic;
  }

  public void setDiagnostic(Diagnostic diagnostic)
  {
    this.diagnostic = diagnostic;
    refresh();
  }

  protected void refresh()
  {
    if (diagnostic != null && messageText != null)
    {
      Image image = getImage();
      if (image != null)
      {
        image.setBackground(imageLabel.getBackground());
        imageLabel.setImage(image);
        imageLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER | GridData.VERTICAL_ALIGN_BEGINNING));
      }

      messageText.setText(getMessage());

      if (detailsTreeViewer != null && detailsTreeViewer.getInput() != diagnostic)
      {
        detailsTreeViewer.setInput(diagnostic);
        detailsText.setText("");
      }
    }
  }

  protected Image getImage()
  {
    Display display = Display.getCurrent();
    switch (diagnostic.getSeverity())
    {
      case Diagnostic.ERROR:
        return display.getSystemImage(SWT.ICON_ERROR);
      case Diagnostic.WARNING:
        return display.getSystemImage(SWT.ICON_WARNING);
      default:
        return display.getSystemImage(SWT.ICON_INFORMATION);
    }
  }

  protected String getMessage()
  {
    return getTextProvider().getMessageText(getDiagnostic());
  }
  
  protected String getDetailsText(Diagnostic diagnostic)
  {
    return getTextProvider().getDetailsText(diagnostic);
  }

  protected void updateDetails()
  {
    if (detailsButton.getData() == Boolean.TRUE)
    {
      if (detailsControl == null)
      {
        detailsControl = createDetailsControl(detailsComposite);
        detailsComposite.layout(true);
      }
      else
      {
        detailsControl.setVisible(true);
      }
      detailsButton.setText(IDialogConstants.HIDE_DETAILS_LABEL);
    }
    else
    {
      if (detailsControl != null)
      {
        detailsControl.setVisible(false);
      }
      detailsButton.setText(IDialogConstants.SHOW_DETAILS_LABEL);
    }
  }

  protected Control createDetailsControl(Composite parent)
  {
    SashForm detailsComposite = new SashForm(parent, SWT.VERTICAL);
    detailsComposite.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL));

    detailsTreeViewer = new TreeViewer(detailsComposite, SWT.BORDER);
    detailsTreeViewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_BEGINNING));

    detailsText = new Text(detailsComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI | SWT.READ_ONLY);
    exceptionText = detailsText;
    GridData gridData = new GridData(GridData.FILL_BOTH);
    gridData.heightHint = 20;
    gridData.grabExcessVerticalSpace = true;
    detailsText.setLayoutData(gridData);
    detailsText.setBackground(detailsText.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));

    detailsComposite.setWeights(new int []{ 70, 30 });

    detailsTreeViewer.setContentProvider(new ITreeContentProvider()
      {
        private boolean isRootElement = true;

        public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
        {
          // Empty block
        }

        public void dispose()
        {
          // Empty block
        }

        public Object getParent(Object element)
        {
          return null;
        }

        public Object[] getElements(Object inputElement)
        {
          if (isRootElement)
          {
            isRootElement = false;
            Diagnostic diagnostic = (Diagnostic)inputElement;
            if (diagnostic.getMessage() != null || diagnostic.getException() != null)
            {
              return new Object []{ diagnostic };
            }
          }
          return getChildren(inputElement);
        }

        public boolean hasChildren(Object element)
        {
          return !((Diagnostic)element).getChildren().isEmpty();
        }

        public Object[] getChildren(Object parentElement)
        {
          return ((Diagnostic)parentElement).getChildren().toArray();
        }
      });

    detailsTreeViewer.setLabelProvider(new LabelProvider()
      {
        @Override
        public String getText(Object element)
        {
          Diagnostic diagnostic = (Diagnostic)element;
          String message = diagnostic.getMessage();
          if (message == null)
          {
            switch (diagnostic.getSeverity())
            {
              case Diagnostic.ERROR:
                message = CommonUIPlugin.getPlugin().getString("_UI_DiagnosticError_label");
                break;
              case Diagnostic.WARNING:
                message = CommonUIPlugin.getPlugin().getString("_UI_DiagnosticWarning_label");
                break;
              default:
                message = CommonUIPlugin.getPlugin().getString("_UI_Diagnostic_label");
                break;
            }
          }
          return message;
        }
        
        @Override
        public Image getImage(Object element)
        {
          Diagnostic diagnostic = (Diagnostic)element;
          ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
          switch (diagnostic.getSeverity())
          {
            case Diagnostic.ERROR:
              return sharedImages.getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
            case Diagnostic.WARNING:
              return sharedImages.getImage(ISharedImages.IMG_OBJS_WARN_TSK);
          }
          return null;
        }        
      });

    detailsTreeViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
        public void selectionChanged(SelectionChangedEvent event)
        {
          if (!event.getSelection().isEmpty())
          {
            Diagnostic diagnostic = (Diagnostic)((IStructuredSelection)event.getSelection()).getFirstElement();
            detailsText.setText(getDetailsText(diagnostic));
          }
          else
          {
            detailsText.setText("");
          }
        }
      });

    detailsTreeViewer.setInput(diagnostic);
    detailsTreeViewer.expandToLevel(2);
    return detailsComposite;
  }

  protected void toggleDetails()
  {
    detailsButton.setData(detailsButton.getData() == Boolean.TRUE ? Boolean.FALSE : Boolean.TRUE);
    updateDetails();
  }

  protected String computeEditorToOpen()
  {
    IEditorDescriptor editorDescriptor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor("foo.txt");
    return editorDescriptor != null ? editorDescriptor.getId() : null;
  }

  protected void openEditor()
  {
    if (editorToOpen != null)
    {
      try
      {
        IWorkbenchPage workbenchPage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IEditorInput editorInput = getEditorInput();
        if (markerUtil != null && detailsTreeViewer != null && detailsControl.isVisible())
        {
          Diagnostic diagnostic = (Diagnostic)((IStructuredSelection)detailsTreeViewer.getSelection()).getFirstElement();
          IEditorInput diagnosticEditorInput = markerUtil.getEditorInput(diagnostic);
          if (diagnosticEditorInput != null)
          {
            editorInput = diagnosticEditorInput;
          }
        }
        workbenchPage.openEditor(editorInput, editorToOpen, true, IWorkbenchPage.MATCH_INPUT | IWorkbenchPage.MATCH_ID);
      }
      catch (Exception exception)
      {
        openErrorDialog(CommonUIPlugin.getPlugin().getString("_UI_OpenEditorError_message"), exception);
      }
    }
  }

  protected void createMarkers()
  {
    if (markerUtil != null)
    {
      markerUtil.deleteMarkers(diagnostic);
      if (diagnostic.getSeverity() != Diagnostic.OK)
      {
        try
        {
          markerUtil.createMarkers(diagnostic);
        }
        catch (CoreException exception)
        {
          openErrorDialog(CommonUIPlugin.getPlugin().getString("_UI_CreateMarkerError_message"), exception);
        }
      }
    }
  }

  protected void openErrorDialog(String message, Exception exception)
  {
    ErrorDialog.openError(
      Display.getCurrent().getActiveShell(),
      CommonUIPlugin.getPlugin().getString("_UI_Error_label"),
      message,
      exception instanceof CoreException
        ? ((CoreException)exception).getStatus() : BasicDiagnostic.toIStatus(BasicDiagnostic.toDiagnostic(exception)));
  }

  @Override
  public void doSave(IProgressMonitor monitor)
  {
    // Empty block
  }

  @Override
  public void doSaveAs()
  {
    // Empty block
  }

  @Override
  public boolean isDirty()
  {
    return false;
  }

  @Override
  public boolean isSaveAsAllowed()
  {
    return false;
  }

  @Override
  public void setFocus()
  {
    // Empty block
  }
}
