/**
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.handler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.presentation.GeneratorUIUtil;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelEditPlugin;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;

/**
 * PROVISIONAL
 * This API is subject to arbitrary change, including renaming or removal.
 * 
 * @since 2.5
 */
public class GenerateHandler extends AbstractHandler
{
  protected static List<URI> getGenModelURIs(IProgressMonitor progressMonitor, Object[] objects)
  {
    if (objects.length > 0)
    {
      progressMonitor.beginTask(GenModelEditPlugin.INSTANCE.getString("_UI_AnalyzingObjects_message"), objects.length);
      List<URI> uris = new ArrayList<URI>(objects.length);
      for (Object object : objects)
      {
        IFile file = null;
        if (object instanceof IFile)
        {
          file = (IFile)object;
        }
        else if (object instanceof IAdaptable)
        {
          file = (IFile)((IAdaptable)object).getAdapter(IFile.class);
        }

        if (file != null && "genmodel".equals(file.getFileExtension()))
        {
          progressMonitor.subTask(file.getName());
          URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
          uris.add(uri);
        }
        progressMonitor.worked(1);
      }
      return uris;
    }
    return new ArrayList<URI>();
  }
  
  protected static class GenModelSelectionDialog extends FilteredResourcesSelectionDialog
  { 
    protected class GenModelResourceFilter extends FilteredResourcesSelectionDialog.ResourceFilter
    {
      public GenModelResourceFilter(IContainer container)
      {
        super(container, false, IResource.FILE);
      }
     
      @Override
      public boolean matchItem(Object item)
      {
        return 
          item instanceof IFile && 
          "genmodel".equals(((IFile)item).getFileExtension()) && 
          super.matchItem(item);
      }
    }
    
    protected IContainer container;
    protected IStatus status;
    protected StatusLineManager statusLineManager;
    
    protected List<GenModel> genModels;
    protected Point size;
    protected Point location;
    
    public GenModelSelectionDialog(Shell shell, boolean multi, IContainer container, int typesMask)
    {
      super(shell, multi, container, typesMask);
      this.container = container;
    }
    
    public void initialize(Point size, Point location)
    {
      this.size = size;
      this.location = location;
    }

    public List<GenModel> getGenModels()
    {
      if (genModels == null)
      {
        genModels = new ArrayList<GenModel>();
      }
      return genModels;
    }
    
    public Point getSize()
    {
      return size;
    }
    
    public Point getLocation()
    {
      return location;
    }
    
    @Override
    protected void configureShell(Shell newShell)
    {
      super.configureShell(newShell);
      if (size != null)
      {
        newShell.setSize(size);
      }
      if (location != null)
      {
        newShell.setLocation(location);
      }
    }
    
    @Override
    public void create()
    {
      super.create();

      if (size != null)
      {
        Point shellSize = getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT);
        getShell().setSize(Math.max(shellSize.x, size.x), size.y);
      }
      if (location != null)
      {
        getShell().setLocation(location);
      }
    }
        
    @Override
    protected Control createDialogArea(Composite parent)
    {
      Composite composite = (Composite)super.createDialogArea(parent);
      
      statusLineManager = new StatusLineManager();
      statusLineManager.createControl(composite);
      statusLineManager.setCancelEnabled(true);
      GridData gridData = new GridData();
      gridData.horizontalAlignment = GridData.FILL;
      gridData.grabExcessHorizontalSpace = true;
      gridData.heightHint = convertVerticalDLUsToPixels(9);
      statusLineManager.getControl().setLayoutData(gridData);
            
      return composite;
    }
    
    @Override
    protected void createButtonsForButtonBar(Composite parent)
    {
      super.createButtonsForButtonBar(parent);
      
      Button okButton = getOkButton();
      okButton.setText(IDialogConstants.NEXT_LABEL);
      
      Button button = createButton(parent, IDialogConstants.BACK_ID, IDialogConstants.BACK_LABEL, false);
      button.moveAbove(okButton);
      button.setEnabled(false);
      button = createButton(parent, IDialogConstants.FINISH_ID, IDialogConstants.FINISH_LABEL, false);
      button.moveBelow(okButton);
      button.setEnabled(false);
    }
    
    @Override
    protected ItemsFilter createFilter()
    {
      return new GenModelResourceFilter(container);
    }
    
    @Override
    protected void updateStatus(IStatus status)
    {
      this.status = status;
      super.updateStatus(status);
    }
    
    @Override
    protected void cancelPressed()
    {
      super.cancelPressed();
    }
    
    @Override
    public boolean close()
    {
      location = getShell().getLocation();
      size = getShell().getSize();
      
      return super.close();
    }
    
    @Override
    protected void okPressed()
    {      
      if (status != null && (status.isOK() || status.getCode() == IStatus.INFO))
      {
        computeResult();
        
        IProgressMonitor progressMonitor = statusLineManager.getProgressMonitor();
        progressMonitor.beginTask("", 10);
        List<URI> uris = getGenModelURIs(new SubProgressMonitor(progressMonitor, 3, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK), getResult());
        List<GenModel> genModels = GeneratorUIUtil.loadGenModels(new SubProgressMonitor(progressMonitor, 7), uris, getShell());
        progressMonitor.done();
        
        if (!genModels.isEmpty())
        {
          getGenModels().addAll(genModels);
          close();
        }
      }
    }
  }
    
  protected static class GenModelGenerationDialog extends TrayDialog
  {
    protected enum ProjectType
    {
      MODEL
      {
        @Override
        public String getID()
        {
          return GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE;
        }
        
        @Override
        public String getLabel()
        {
          return GenModelEditPlugin.INSTANCE.getString("_UI_Model_title");
        }
        
        @Override
        public boolean canGenerate(GenModel genModel)
        {
          return genModel.canGenerate();
        }
      },
      
      EDIT
      {
        @Override
        public String getID()
        {
          return GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE;
        }
        
        
        @Override
        public String getLabel()
        {
          return GenModelEditPlugin.INSTANCE.getString("_UI_Edit_title");
        }
        
        @Override
        public boolean canGenerate(GenModel genModel)
        {
          return genModel.canGenerateEdit();
        }
      },
      
      EDITOR
      {
        @Override
        public String getID()
        {
          return GenBaseGeneratorAdapter.EDITOR_PROJECT_TYPE;
        }
        
        @Override
        public String getLabel()
        {
          return GenModelEditPlugin.INSTANCE.getString("_UI_Editor_title");
        }
        
        @Override
        public boolean canGenerate(GenModel genModel)
        {
          return genModel.canGenerateEditor();
        }
      },
      
      TESTS
      {
        @Override
        public String getID()
        {
          return GenBaseGeneratorAdapter.TESTS_PROJECT_TYPE;
        }
        
        @Override
        public String getLabel()
        {
          return GenModelEditPlugin.INSTANCE.getString("_UI_Tests_title");
        }
        
        @Override
        public boolean canGenerate(GenModel genModel)
        {
          return genModel.canGenerateTests();
        }
      };
      
      public abstract boolean canGenerate(GenModel genModel); 
      public abstract String getID();
      public abstract String getLabel();
    }
    
    protected List<GenModel> genModels;
    protected List<Object[]> generatorAndArgumentsList;

    protected boolean startWithProjectSelected = true;
    protected Point size;
    protected Point location;
    protected Table genModelTable;
    protected CLabel genModelLabel;
    protected boolean showBackButton = false;    
    
    public GenModelGenerationDialog(Shell shell, List<GenModel> genModels)
    {
      super(shell);
      this.genModels = genModels;
      setShellStyle(getShellStyle() | SWT.RESIZE  | SWT.MAX);
    }
    
    public boolean isShowBackButton()
    {
      return showBackButton;
    }

    public void setShowBackButton(boolean showBackButton)
    {
      this.showBackButton = showBackButton;
    }

    public List<Object[]> getGeneratorAndArgumentsList()
    {
      if (generatorAndArgumentsList == null)
      {
        generatorAndArgumentsList = new ArrayList<Object[]>();
      }
      return generatorAndArgumentsList;
    }
    
    public void initialize(Point size, Point location)
    {
      this.size = size;
      this.location = location;
    }
    
    public Point getSize()
    {
      return size;
    }
    
    public Point getLocation()
    {
      return location;
    }

    @Override
    protected void configureShell(Shell newShell)
    {
      super.configureShell(newShell);
      newShell.setText(GenModelEditPlugin.INSTANCE.getString("_UI_GenModelGenerationDialog_title"));
    }
    
    @Override
    public void create()
    {
      super.create();

      if (size != null)
      {
        Point shellSize = getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT);
        getShell().setSize(Math.max(shellSize.x, size.x), size.y);
      }
      if (location != null)
      {
        getShell().setLocation(location);
      }
      
      if (genModelTable.getSelectionCount() == 0)
      {
        genModelTable.select(0);
        selectionChanged((GenModel)genModelTable.getItem(0).getData());
      }      
    }
    
    @Override
    protected Control createDialogArea(Composite parent)
    {
      Composite composite = (Composite)super.createDialogArea(parent);
      createControls(composite);
      refresh();
      return composite;
    }
    
    protected void createControls(Composite parent)
    {
      createTable(parent);
      createGenModelDetailControl(parent);
    }
    
    @Override
    protected void createButtonsForButtonBar(Composite parent)
    {
      if (isShowBackButton())
      {
        Button backButton = createButton(parent, IDialogConstants.BACK_ID, IDialogConstants.BACK_LABEL, false);
        Listener backButtonListener = new Listener()
        {
          public void handleEvent(Event event)
          {
            setReturnCode(IDialogConstants.BACK_ID);
            close();
          }
        };
        backButton.addListener(SWT.Selection, backButtonListener);

        Button button = createButton(parent, IDialogConstants.NEXT_ID, IDialogConstants.NEXT_LABEL, false);
        button.setEnabled(false);
      }
      
      super.createButtonsForButtonBar(parent);
      
      if (isShowBackButton())
      {
        getButton(IDialogConstants.OK_ID).setText(IDialogConstants.FINISH_LABEL);
      }
      
      updateButtons(false);
    }
    
    protected void createTable(Composite parent)
    {
      CLabel label = new CLabel(parent, SWT.NONE);
      label.setText(GenModelEditPlugin.INSTANCE.getString("_UI_GenModelGenerationDialog_message"));
      
      genModelTable = new Table(parent, SWT.BORDER);
      genModelTable.setLinesVisible(true);
      genModelTable.setHeaderVisible(true);
      GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
      genModelTable.setLayoutData(gridData);
      
      Listener tableListener = new Listener()
      {
        public void handleEvent(Event event)
        {
          if (genModelTable.getSelectionCount() == 1)
          {
            if (event.type == SWT.MouseDoubleClick)
            {
              Boolean select = null;
              TableColumn[] tableColumns = genModelTable.getColumns();
              for (int i=1; i < tableColumns.length; i++)
              {
                select = checkBoxColumnHeaderSelected(tableColumns[i], genModelTable.getSelectionIndex(), select);
              }
              updateButtons(select);
            }
            TableItem tableItem = genModelTable.getSelection()[0];
            selectionChanged((GenModel)tableItem.getData());
          }
        }
      };
      genModelTable.addListener(SWT.Selection, tableListener);
      genModelTable.addListener(SWT.MouseDoubleClick, tableListener);
      
      TableColumn genModelColumn = new TableColumn(genModelTable, SWT.NONE);
      genModelColumn.setText(GenModelEditPlugin.INSTANCE.getString("_UI_GenModelFile_title"));
      
      Listener genModelColumnListener = new Listener()
      {
        public void handleEvent(Event event)
        {
          Boolean select = null;
          TableColumn[] tableColumns = genModelTable.getColumns();
          for (int i=1; i < tableColumns.length; i++)
          {
            select = checkBoxColumnHeaderSelected(tableColumns[i], -1, select);
          }
          updateButtons(select);
        }
      };
      genModelColumn.addListener(SWT.Selection, genModelColumnListener);
      
      Listener checkBoxColumnListener = new Listener()
      {
        public void handleEvent(Event event)
        {
          TableColumn tableColumn = (TableColumn)event.widget;
          Boolean select = checkBoxColumnHeaderSelected(tableColumn, -1, null);
          updateButtons(select);
        }
      };
      
      int maxWidth = 0;
      for (ProjectType projectType : ProjectType.values())
      {
        maxWidth = Math.max(maxWidth, createCheckBoxColumn(projectType, checkBoxColumnListener));
      }
      
      TableColumn[] tableColumns = genModelTable.getColumns();
      for (int i=1; i < tableColumns.length; i++)
      {
        tableColumns[i].setWidth(maxWidth);
      }
    }
    
    protected int createCheckBoxColumn(ProjectType projectType, Listener listener)
    {
      TableColumn tableColumn = new TableColumn(genModelTable, SWT.NONE);
      tableColumn.setText("  " + projectType.getLabel() + "  ");
      tableColumn.setData("type", projectType);
      tableColumn.setResizable(false);
      tableColumn.pack();
      tableColumn.addListener(SWT.Selection, listener);
      return tableColumn.getWidth();
    }
    
    protected Boolean checkBoxColumnHeaderSelected(TableColumn tableColumn, int itemIndex, Boolean select)
    {
      @SuppressWarnings("unchecked")
      List<Button> buttons = (List<Button>)tableColumn.getData("buttons");
      if (buttons != null && !buttons.isEmpty())
      {
        if (itemIndex > -1)
        {
          buttons = buttons.subList(itemIndex, itemIndex+1);
        }
        
        for (Button button : buttons)
        {
          if (button.isEnabled())
          {
            if (select == null)
            {
              select = !button.getSelection();
            }
            button.setSelection(select);
          }
        }
      }
      return select;
    }
    
    protected void selectionChanged(GenModel genModel)
    {
      StringBuilder text = new StringBuilder();
      for (GenPackage genPackage : genModel.getGenPackages())
      {
        text.append(", ").append(genPackage.getEcorePackage().getNsURI());
      }
      Image image = ExtendedImageRegistry.INSTANCE.getImage(URI.createPlatformPluginURI("/org.eclipse.emf.ecore.edit/icons/full/obj16/EPackage.gif", false));
      genModelLabel.setImage(image);
      genModelLabel.setText(text.substring(", ".length()));
    }
    
    protected void refresh()
    {
      genModelLabel.setText("");
      genModelTable.clearAll();

      TableColumn[] tableColumns = genModelTable.getColumns();
      for (int i=1; i < tableColumns.length; i++)
      {
        @SuppressWarnings("unchecked")
        List<Button> buttons = (List<Button>)tableColumns[i].getData("buttons");
        if (buttons != null)
        {
          for (Button button : buttons)
          {
            button.dispose();
          }
          buttons.clear();
        }
        else
        {
          tableColumns[i].setData("buttons", new ArrayList<Button>());
        }
      }
      
      for (GenModel genModel : genModels)
      {
        TableItem tableItem = new TableItem(genModelTable, SWT.NONE);
        tableItem.setText(0, genModel.eResource().getURI().toPlatformString(true) + "   ");
        tableItem.setImage(ExtendedImageRegistry.INSTANCE.getImage(URI.createPlatformPluginURI("/org.eclipse.emf.codegen.ecore.ui/icons/full/obj16/GenModel.gif", false)));
        tableItem.setData(genModel);
        
        for (int i=1; i < tableColumns.length; i++)
        {
          TableColumn tableColumn = tableColumns[i];
          ProjectType projectType = (ProjectType)tableColumn.getData("type");
          
          Button button = new Button(genModelTable, SWT.CHECK);
          if (projectType.canGenerate(genModel))
          {
            button.setSelection(startWithProjectSelected);
          }
          else
          {
            button.setEnabled(false);            
          }
          button.pack();
          
          Listener buttonListener = new Listener()
          {
            public void handleEvent(Event event)
            {
              updateButtons(((Button)event.widget).getSelection());
            }
          };
          button.addListener(SWT.Selection, buttonListener);
          
          @SuppressWarnings("unchecked")
          List<Button> buttons = (List<Button>)tableColumn.getData("buttons");
          buttons.add(button);
          
          TableEditor editor = new TableEditor(genModelTable);
          editor.minimumWidth = button.getSize().x;
          editor.horizontalAlignment = SWT.CENTER;
          editor.setEditor(button, tableItem, i);
        }
      }
      tableColumns[0].pack();
      updateButtons(false);
    }
    
    protected void createGenModelDetailControl(Composite parent)
    {
      ViewForm viewForm = new ViewForm(parent, SWT.BORDER | SWT.FLAT);
      viewForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));      
      
      genModelLabel = new CLabel(viewForm, SWT.FLAT);
      genModelLabel.setFont(parent.getFont());
      genModelLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
      genModelLabel.setToolTipText(GenModelEditPlugin.INSTANCE.getString("_UI_GenModelGenerationDetail_message"));
      genModelLabel.pack();
      
      viewForm.setContent(genModelLabel);
    }
    
    @Override
    protected void okPressed()
    {
      getGeneratorAndArgumentsList().addAll(prepareGenerators());
      super.okPressed();
    }
    
    protected void updateButtons(Boolean hasSelection)
    {
      Button okButton = getButton(IDialogConstants.OK_ID);
      if (okButton != null)
      {
        if (hasSelection == null)
        {
          hasSelection = false;
        }
        else if (!hasSelection)
        {
          TableColumn[] tableColumns = genModelTable.getColumns();
          LOOP:
          for (int column=1; column < tableColumns.length; column++)
          {
            @SuppressWarnings("unchecked")
            List<Button> buttons = (List<Button>)tableColumns[column].getData("buttons");
            for (Button button : buttons)
            {
              hasSelection |= button.isEnabled() && button.getSelection();
              if (hasSelection)
              {
                break LOOP;
              }
            }        
          }
        }
        okButton.setEnabled(hasSelection);
      }
    }
    
    @Override
    public boolean close()
    {
      location = getShell().getLocation();
      size = getShell().getSize();
      
      return super.close();
    }    
    
    protected List<Object[]> prepareGenerators()
    {
      TableColumn[] tableColumns = genModelTable.getColumns();
      TableItem[] tableItems = genModelTable.getItems();
      List<Object[]> generatorAndArgumentsList = new ArrayList<Object[]>(tableItems.length*(tableColumns.length-1));      
      for (int line=0; line < tableItems.length; line++)
      {
        GenModel genModel = (GenModel)tableItems[line].getData();
        for (int column=1; column < tableColumns.length; column++)
        {
          @SuppressWarnings("unchecked")
          Button button = ((List<Button>)tableColumns[column].getData("buttons")).get(line);
          if (button.isEnabled() && button.getSelection())
          {            
            ProjectType projectType = (ProjectType)tableColumns[column].getData("type");
            
            Generator generator = GenModelUtil.createGenerator(genModel);
            
            Object[] generatorAndArguments = new Object[4];
            generatorAndArguments[0] = generator;
            generatorAndArguments[1] = genModel;
            generatorAndArguments[2] = projectType.getID();
            generatorAndArguments[3] = projectType.getLabel();
            generatorAndArgumentsList.add(generatorAndArguments);
          }
        }
      }
      return generatorAndArgumentsList;
    }
  }
   
  protected Point dialogSize = null;
  protected Point dialogLocation = null;
    
  /* (non-Javadoc)
   * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
   */
  public Object execute(ExecutionEvent event) throws ExecutionException
  {
    Shell shell = HandlerUtil.getActiveShell(event);
    List<GenModel> genModels = null;

    boolean usingSelection = false;
    ISelection selection = HandlerUtil.getCurrentSelection(event);
    if (selection instanceof IStructuredSelection)
    {
      Object[] selectedObjects = ((IStructuredSelection)selection).toArray();
      if (selectedObjects.length >= 1)
      {
        IProgressMonitor progressMonitor = new NullProgressMonitor();
        List<URI> uris = getGenModelURIs(progressMonitor, selectedObjects);
        if (!uris.isEmpty())
        {
          genModels = GeneratorUIUtil.loadGenModels(progressMonitor, uris, shell);
          if (genModels.isEmpty())
            return null;
          usingSelection = true;
        }
      }
    }
    
    if (!usingSelection)
    {
      GenModelSelectionDialog selectionDialog = 
        new GenModelSelectionDialog(shell, true, ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
      selectionDialog.initialize(dialogSize, dialogLocation);
      selectionDialog.setTitle(GenModelEditPlugin.INSTANCE.getString("_UI_GenModelSelectionDialog_title"));
      selectionDialog.setInitialPattern("*.genmodel");
      
      if (selectionDialog.open() == Window.OK)
      {
        dialogSize = selectionDialog.getSize();
        dialogLocation = selectionDialog.getLocation();
        genModels = selectionDialog.getGenModels();
      }
    }
    
    if (genModels != null && !genModels.isEmpty())
    {      
      GenModelGenerationDialog generationDialog = new GenModelGenerationDialog(shell, genModels);
      generationDialog.setShowBackButton(!usingSelection);
      generationDialog.initialize(dialogSize, dialogLocation);
      int ret = generationDialog.open(); 
      if (ret == Window.OK)
      {
        try
        {
          new ProgressMonitorDialog(shell).run(true, true, new GeneratorUIUtil.GeneratorOperation(shell, generationDialog.getGeneratorAndArgumentsList()));
        }
        catch (Exception exception)
        {
          GenModelEditPlugin.INSTANCE.log(exception);
        }        
      }
      else if (ret == IDialogConstants.BACK_ID)
      {
        dialogSize = generationDialog.getSize();
        dialogLocation = generationDialog.getLocation();
        execute(event);
      }     
    }
    
    return null;
  }
}
