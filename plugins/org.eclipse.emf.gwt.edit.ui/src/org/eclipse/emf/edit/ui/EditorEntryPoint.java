/**
 * Copyright (c) 2010 Ed Merks and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Ed Merks - Initial API and implementation
 */
package org.eclipse.emf.edit.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.Callback;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIService;
import org.eclipse.emf.ecore.resource.URIServiceAsync;
import org.eclipse.emf.ecore.resource.URIServiceCallback;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CopyToClipboardCommand;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.CutToClipboardCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

public abstract class EditorEntryPoint implements EntryPoint
{
  public static abstract class CommandHandler implements ClickHandler
  {
    protected Command command;
    protected EditingDomain editingDomain;
    protected Button button;

    public CommandHandler(EditingDomain editingDomain, Button button)
    {
      this.editingDomain = editingDomain;
      this.button = button;
      button.addClickHandler(this);
      button.setEnabled(false);
    }

    public void setSelection(Object value)
    {
      command = createCommand(value);
      button.setEnabled(command.canExecute());
    }

    public abstract Command createCommand(Object value);

    public void onClick(ClickEvent event)
    {
      editingDomain.getCommandStack().execute(command);
    }
  }

  public void onModuleLoad()
  {
    ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory();
    composedAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
    configureItemProviderAdapterFactories(composedAdapterFactory);
    composedAdapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

    final BasicCommandStack commandStack = new BasicCommandStack();
    final AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(composedAdapterFactory, commandStack);

    final ResourceSet resourceSet = editingDomain.getResourceSet();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
      (Resource.Factory.Registry.DEFAULT_EXTENSION,
       new ResourceFactoryImpl()
       {
         @Override
         public Resource createResource(URI uri)
         {
           return new BinaryResourceImpl(uri);
         }
       });

    final URIServiceAsync uriService = GWT.create(URIService.class);
    URIServiceCallback uriServiceCallback = new URIServiceCallback(uriService);
    resourceSet.getURIConverter().getURIHandlers().add(uriServiceCallback);

    final List<Resource> originalResources = new ArrayList<Resource>();

    Resource listingResource =
      resourceSet.getResource
        (URI.createURI("datastore:/"),
         new Callback<Resource>()
         {
           public void onFailure(Throwable caught)
           {
             System.err.println("Failed");
             caught.printStackTrace();
           }

           public void onSuccess(Resource result)
           {
             EAnnotation eAnnotation = (EAnnotation)result.getContents().get(0);
             for (Map.Entry<String, String> entry : eAnnotation.getDetails())
             {
               resourceSet.createResource(URI.createURI(entry.getKey()));
             }
             originalResources.clear();
             originalResources.addAll(resourceSet.getResources());
           }
         });
    resourceSet.getResources().remove(listingResource);

    registerPackages(resourceSet.getPackageRegistry());

    final AdapterFactoryItemDelegator itemDelegator = new AdapterFactoryItemDelegator(composedAdapterFactory);

    DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.PX);
    mainPanel.setStyleName("Main", true);
    mainPanel.setHeight("100%");

    FlowPanel headerPanel = new FlowPanel();
    headerPanel.setStyleName("Header", true);

    final Label header = new Label(getApplicationTitle());
    header.setStyleName("HeaderLabel", true);

    headerPanel.add(header);

    mainPanel.addNorth(headerPanel, 56.0);

    DockLayoutPanel bodyPanel = new DockLayoutPanel(Unit.PX);
    bodyPanel.setStyleName("Body", true);
    bodyPanel.setHeight("100%");

    mainPanel.add(bodyPanel);

    FlowPanel toolBar = new FlowPanel();
    toolBar.setStyleName("ToolBar", true);

    SplitLayoutPanel contentPanel = new SplitLayoutPanel();
    contentPanel.setStyleName("Content", true);

    DockLayoutPanel propertiesPanel = new DockLayoutPanel(Unit.PX);
    propertiesPanel.setStyleName("Properties", true);
    propertiesPanel.setHeight("100%");

    FlowPanel propertiesTitlePanel = new FlowPanel();
    propertiesTitlePanel.setStyleName("PropertiesTitle", true);

    final Label title = new Label("Properties");
    title.setStyleName("PropertiesTitleLabel", true);

    propertiesTitlePanel.add(title);
    
    propertiesPanel.addNorth(propertiesTitlePanel, 28.0);
    
    final Grid properties = new Grid();
    properties.setWidth("100%");
    ScrollPanel propertiesScrollPanel = new ScrollPanel();
    propertiesScrollPanel.add(properties);

    propertiesPanel.add(propertiesScrollPanel);

    final Button createButton = new Button();
    createButton.setText("Create...");
    createButton.setStyleName("ToolBarButtonLeftMargin");

    toolBar.add(createButton);

    final Button deleteButton = new Button();
    deleteButton.setText("Delete");
    final CommandHandler deleteCommandHandler =
      new CommandHandler(editingDomain, deleteButton)
      {
        @Override
        public Command createCommand(Object value)
        {
          if (value instanceof Resource)
          {
            final Resource resource = (Resource)value;
            final Command deleteContentsCommand = DeleteCommand.create(editingDomain, resource.getContents());
            final RemoveCommand removeResourceCommand = new RemoveCommand(editingDomain, resourceSet.getResources(), resource);
            return
              new CompoundCommand()
              {
                @Override
                protected boolean prepare()
                {
                  return true;
                }

                @Override
                public void execute()
                {
                  appendAndExecute(deleteContentsCommand);
                  appendAndExecute(removeResourceCommand);
                }
              };
          }
          else
          {
            return DeleteCommand.create(editingDomain, value);
          }
        }
      };
    deleteButton.setStyleName("ToolBarButton");

    toolBar.add(deleteButton);

    final Button validateButton = new Button();
    validateButton.setText("Validate");
    validateButton.setStyleName("ToolBarButtonLeftMargin");

    toolBar.add(validateButton);

    final Button saveButton = new Button();
    saveButton.setText("Save");
    saveButton.setStyleName("ToolBarButton");

    toolBar.add(saveButton);

    final Button undoButton = new Button();
    undoButton.setText("Undo");
    undoButton.setStyleName("ToolBarButtonLeftMargin");

    toolBar.add(undoButton);

    final Button redoButton = new Button();
    redoButton.setText("Redo");
    redoButton.setStyleName("ToolBarButton");

    toolBar.add(redoButton);

    final Button cutButton = new Button();
    cutButton.setText("Cut");
    final CommandHandler cutCommandHandler =
      new CommandHandler(editingDomain, cutButton)
      {
        @Override
        public Command createCommand(Object value)
        {
          return CutToClipboardCommand.create(editingDomain, value);
        }
      };
    cutButton.setStyleName("ToolBarButtonLeftMargin");

    toolBar.add(cutButton);

    final Button copyButton = new Button();
    copyButton.setText("Copy");
    final CommandHandler copyCommandHandler =
      new CommandHandler(editingDomain, copyButton)
      {
        @Override
        public Command createCommand(Object value)
        {
          return value instanceof EObject ? CopyToClipboardCommand.create(editingDomain, value) : UnexecutableCommand.INSTANCE;
        }
      };
    copyButton.setStyleName("ToolBarButton");

    toolBar.add(copyButton);

    final Button pasteButton = new Button();
    pasteButton.setText("Paste");
    final CommandHandler pasteCommandHandler =
      new CommandHandler(editingDomain, pasteButton)
      {
        @Override
        public Command createCommand(Object value)
        {
          return PasteFromClipboardCommand.create(editingDomain, value, null);
        }
      };
    pasteButton.setStyleName("ToolBarButton");

    toolBar.add(pasteButton);

    final Button upButton = new Button();
    upButton.setText("Up ^");
    final CommandHandler upCommandHandler =
      new CommandHandler(editingDomain, upButton)
      {
        @Override
        public Command createCommand(Object value)
        {
          Object owner = editingDomain.getParent(value);
          if (owner != null)
          {
            List<?> children = new ArrayList<Object>(editingDomain.getChildren(owner));
            int index = children.indexOf(value);
            if (index != -1)
            {
              return MoveCommand.create(editingDomain, owner, null, value, index - 1);
            }
          }
          return UnexecutableCommand.INSTANCE;
        }
      };
    upButton.setStyleName("ToolBarButtonLeftMargin");

    toolBar.add(upButton);

    final Button downButton = new Button();
    downButton.setText("Down v");
    final CommandHandler downCommandHandler =
      new CommandHandler(editingDomain, downButton)
      {
        @Override
        public Command createCommand(Object value)
        {
          Object owner = editingDomain.getParent(value);
          if (owner != null)
          {
            List<?> children = new ArrayList<Object>(editingDomain.getChildren(owner));
            int index = children.indexOf(value);
            if (index != -1)
            {
              return MoveCommand.create(editingDomain, owner, null, value, index + 1);
            }
          }
          return UnexecutableCommand.INSTANCE;
        }
      };
    downButton.setStyleName("ToolBarButton");

    toolBar.add(downButton);

    final Button navigateButton = new Button();
    navigateButton.setText("Navigate");
    navigateButton.setEnabled(false);
    navigateButton.setStyleName("ToolBarButtonLeftMargin");

    toolBar.add(navigateButton);

    final SingleSelectionModel<Object> treeSelection = new SingleSelectionModel<Object>();
    final Collection<Runnable> propertyUpdater = new HashSet<Runnable>();

    TreeViewModel treeViewModel =
      new TreeViewModel()
      {
        public <T> NodeInfo<?> getNodeInfo(final T value)
        {
          final ListDataProvider<Object> abstractListViewAdapter =
            new ListDataProvider<Object>()
            {
              int size;
              class NodeAdapter extends AdapterImpl implements INotifyChangedListener
              {
                Set<Object> children = new HashSet<Object>();

                @Override
                public void notifyChanged(Notification msg)
                {
                  if (msg.getEventType() != Notification.REMOVING_ADAPTER)
                  {
                    update();
                    if (!(msg.getNotifier() instanceof EObject))
                    {
                      for (Runnable runnable : propertyUpdater)
                      {
                        runnable.run();
                      }
                    }
                  }
                }

                public void listenTo(Object target)
                {
                  if (target instanceof Notifier)
                  {
                    ((Notifier)target).eAdapters().add(this);
                  }
                  else if (target instanceof IChangeNotifier)
                  {
                    ((IChangeNotifier)target).addListener(this);
                  }
                }
                public void stopListeningTo(Object target)
                {
                  if (target instanceof Notifier)
                  {
                    ((Notifier)target).eAdapters().remove(this);
                  }
                  else if (target instanceof IChangeNotifier)
                  {
                    ((IChangeNotifier)target).removeListener(this);
                  }
                }

                public void listenTo(List<Object> children)
                {
                  // TODO
                  // I don't know how to update just one child. :-(

                  boolean isListeningToValue = !this.children.isEmpty();
                  Set<Object> oldChildren = new HashSet<Object>(this.children);
                  for (Object child : children)
                  {
                    if (this.children.contains(child))
                    {
                      oldChildren.remove(child);
                    }
                    else
                    {
                      this.children.add(child);
                      listenTo(child);
                    }
                  }
                  for (Object child : oldChildren)
                  {
                    stopListeningTo(child);
                    this.children.remove(child);
                  }
                  if (isListeningToValue)
                  {
                    if (children.isEmpty())
                    {
                      stopListeningTo(value);
                    }
                  }
                  else if (!children.isEmpty())
                  {
                    listenTo(value);
                  }
                }
              }

              protected NodeAdapter nodeAdapter = new NodeAdapter();

              @Override
              protected void onRangeChanged(HasData<Object> display)
              {
                if (value instanceof Resource)
                {
                  Resource resource = (Resource)value;
                  if (!resource.isLoaded())
                  {
                    try
                    {
                      resource.load(resourceSet.getLoadOptions());
                    }
                    catch (IOException e)
                    {
                      // Ignore.
                    }
                  }
                }
                update();
              }

              protected void update()
              {
                Collection<?> children = itemDelegator.getChildren(value);
                ArrayList<Object> childrenList = new ArrayList<Object>(children);
                nodeAdapter.listenTo(childrenList);
                int size = children.size();
                if (this.size < size)
                {
                  updateRowCount(size, true);
                  this.size = size;
                }
                else
                {
                  // Pad with dummy objects.
                  for (int i = size; i < this.size; ++i)
                  {
                    childrenList.add(new Object());
                  }
                }
                updateRowData(0, childrenList);
                if (this.size > size)
                {
                  updateRowCount(size, true);
                  this.size = size;
                }
              }
            };

          Cell<Object> cell =
            new AbstractCell<Object>()
            {
              @Override
              public void render(Context context, Object value, SafeHtmlBuilder safeHtmlBuilder)
              {
                StringBuilder sb = new StringBuilder();
                Object image = itemDelegator.getImage(value);
                if (image instanceof ImageResource)
                {
                  ImageResource imageResource = (ImageResource)image;
                  sb.append("<div style='position:relative;padding-left:");
                  sb.append(imageResource.getWidth() + 6);
                  sb.append("px;'>");
                  sb.append("<div style=\"position:absolute;left:0px;top:0px;height:100%;");
                  sb.append("width:").append(imageResource.getWidth()).append("px;");
                  sb.append("background:url('").append(imageResource.getURL()).append("') ");
                  sb.append("no-repeat scroll center center transparent;");
                  sb.append("\"></div>");
                  sb.append(itemDelegator.getText(value));
                  sb.append("</div>");
                }
                else if (image instanceof ComposedImage)
                {
                  ComposedImage composedImage = (ComposedImage)image;
                  List<ComposedImage.Size> sizes = new ArrayList<ComposedImage.Size>();
                  List<Object> images = new ArrayList<Object>(composedImage.getImages());
                  List<ImageData> nestedImagesData = new ArrayList<ImageData>();
                  for (Object nestedImage : images)
                  {
                    ImageData nestedImageData = getImageData(nestedImage);
                    ComposedImage.Size size = new ComposedImage.Size();
                    size.height = nestedImageData.height;
                    size.width = nestedImageData.width;
                    sizes.add(size);
                    nestedImagesData.add(nestedImageData);
                  }
                  ComposedImage.Size size = composedImage.getSize(sizes);
                  sb.append("<div style='position:relative;padding-left:");
                  sb.append(size.width + 6);
                  sb.append("px;'>");
                  List<ComposedImage.Point> drawPoints = composedImage.getDrawPoints(size);
                  int i = 0;
                  for (ComposedImage.Point drawPoint : drawPoints)
                  {
                    ImageResource nestedImage = (ImageResource)images.get(i++);
                    sb.append("<div style=\"position:absolute;left:").append(drawPoint.x).append("px;top:").append(drawPoint.y).append("px;height:100%;");
                    sb.append("width:").append(nestedImage.getWidth()).append("px;");
                    sb.append("background:url('").append(nestedImage.getURL()).append("') ");
                    sb.append("no-repeat scroll center center transparent;");
                    sb.append("\"></div>");
                  }
                  sb.append(itemDelegator.getText(value));
                  sb.append("</div>");
                }
                else
                {
                  sb.append(itemDelegator.getText(value));
                }
                safeHtmlBuilder.appendHtmlConstant(sb.toString());
              }
            };
          return new DefaultNodeInfo<Object>(abstractListViewAdapter, cell, treeSelection, null);
        }

        public boolean isLeaf(Object value)
        {
          return !itemDelegator.hasChildren(value);
        }
      };

    treeSelection.setSelected(resourceSet, true);
    final MyCellTree cellTree = new MyCellTree(treeViewModel, new ItemProvider(Collections.singleton(resourceSet)));
    ScrollPanel treeScrollPanel = new ScrollPanel();
    treeScrollPanel.add(cellTree);

    class NavigationListener
    {
      Object selection;

      {
        navigateButton.addClickHandler
         (new ClickHandler()
          {
            public void onClick(ClickEvent event)
            {
              final Object oldSelection = treeSelection.getSelectedObject();
              Object newSelection = selection;
              cellTree.expand(editingDomain.getTreePath(editingDomain.getParent(newSelection)));
              treeSelection.setSelected(newSelection, true);
              Scheduler.get().scheduleDeferred
                (new Scheduler.ScheduledCommand()
                  {
                    public void execute()
                    {
                      setSelection(oldSelection);
                    }
                  });
            }
          });
      }

      public void listenTo(final ListBox listBox, final List<?> values)
      {
        listBox.addFocusHandler
          (new FocusHandler()
           {
             protected HandlerRegistration changeHandlerRegistration;

             {
               listBox.addBlurHandler
                 (new BlurHandler()
                  {
                    public void onBlur(BlurEvent event)
                    {
                      if (changeHandlerRegistration != null)
                      {
                        changeHandlerRegistration.removeHandler();
                        changeHandlerRegistration = null;
                      }
                    }
                  });
             }

             public void onFocus(FocusEvent event)
             {
               updateSelection();
               changeHandlerRegistration =
                 listBox.addChangeHandler
                   (new ChangeHandler()
                    {
                      public void onChange(ChangeEvent event)
                      {
                        updateSelection();
                      }
                    });
             }

             void updateSelection()
             {
               int selectedIndex = listBox.getSelectedIndex();
               setSelection(selectedIndex == -1 || selectedIndex >= values.size() ? null : values.get(selectedIndex));
             }
           });
      }

      protected void setSelection(Object object)
      {
        if (object != selection)
        {
          selection = object;
          navigateButton.setEnabled(selection != null);
        }
      }
    }
    final NavigationListener navigationListener = new NavigationListener();

    treeSelection.addSelectionChangeHandler
      (new SelectionChangeEvent.Handler()
       {
         protected Object oldSelection;

         public void onSelectionChange(SelectionChangeEvent event)
         {
           final Object object = treeSelection.getSelectedObject();

           deleteCommandHandler.setSelection(object);
           cutCommandHandler.setSelection(object);
           pasteCommandHandler.setSelection(object);
           copyCommandHandler.setSelection(object);
           upCommandHandler.setSelection(object);
           downCommandHandler.setSelection(object);
           createButton.setEnabled
             (object instanceof Resource ||
                object instanceof ResourceSet ||
                !editingDomain.getNewChildDescriptors(object, null).isEmpty());

           if (oldSelection == object)
           {
             for (Runnable runnable : propertyUpdater)
             {
               runnable.run();
             }
           }
           else
           {
             navigationListener.setSelection(null);

             oldSelection = object;

             List<IItemPropertyDescriptor> propertyDescriptors = itemDelegator.getPropertyDescriptors(object);
             if (propertyDescriptors != null)
             {
               properties.clear();
               propertyUpdater.clear();
               int size = propertyDescriptors.size();
               properties.resize(size, 2);
               properties.getColumnFormatter().setWidth(0, "10%");
               properties.getColumnFormatter().setWidth(1, "90%");
               for (int i = 0; i < size; ++i)
               {
                 final IItemPropertyDescriptor propertyDescriptor = propertyDescriptors.get(i);
                 properties.setText(i, 0, propertyDescriptor.getDisplayName(object));
                 Widget widget = null;
                 final IItemLabelProvider itemLabelProvider = propertyDescriptor.getLabelProvider(object);
                 final Object feature = propertyDescriptor.getFeature(object);
                 if (feature instanceof EReference[])
                 {
                   final ItemPropertyDescriptorFeatureEditor dialog =
                     new ItemPropertyDescriptorFeatureEditor(object, true, propertyDescriptor)
                     {
                       @Override
                       protected void hook(ListBox listBox, List<?> values)
                       {
                         navigationListener.listenTo(listBox, values);
                       }
                     };
                   dialog.update();
                   widget = dialog;
                   propertyUpdater.add
                     (new Runnable()
                      {
                        public void run()
                        {
                          dialog.update();
                        }
                      });
                 }
                 else if (feature instanceof EStructuralFeature)
                 {
                   final EStructuralFeature eStructureFeature = (EStructuralFeature)feature;
                   final EClassifier eType = eStructureFeature.getEType();
                   final Collection<?> choiceOfValues = propertyDescriptor.getChoiceOfValues(object);
                   if (choiceOfValues != null)
                   {
                     final List<?> listOfValues = new ArrayList<Object>(propertyDescriptor.getChoiceOfValues(object));
                     if (propertyDescriptor.isMany(object))
                     {
                       boolean valid = true;
                       for (Object choice : choiceOfValues)
                       {
                         if (!eType.isInstance(choice))
                         {
                           valid = false;
                           break;
                         }
                       }

                       if (valid)
                       {
                         final ItemPropertyDescriptorFeatureEditor dialog =
                           new ItemPropertyDescriptorFeatureEditor(object, true, propertyDescriptor)
                           {
                             @Override
                             protected void hook(ListBox listBox, List<?> values)
                             {
                               navigationListener.listenTo(listBox, values);
                             }
                           };
                         dialog.update();
                         widget = dialog;
                         propertyUpdater.add
                           (new Runnable()
                            {
                              public void run()
                              {
                                dialog.update();
                              }
                            });
                       }
                     }
                     if (widget == null)
                     {
                       final ListBox listBox = new ListBox();
                       widget = listBox;
                       listBox.setVisibleItemCount(1);
                       Runnable runnable =
                         new Runnable()
                         {
                           public void run()
                           {
                             listBox.clear();
                             navigationListener.listenTo(listBox, listOfValues);
                             Object propertyValue = itemDelegator.getEditableValue(propertyDescriptor.getPropertyValue(object));
                             for (Object value : choiceOfValues)
                             {
                               listBox.addItem(itemLabelProvider.getText(value));
                               if (value == null ? propertyValue == null : value.equals(propertyValue))
                               {
                                 listBox.setSelectedIndex(listBox.getItemCount() - 1);
                               }
                             }
                           }
                         };
                       listBox.addChangeHandler
                         (new ChangeHandler()
                          {
                            public void onChange(ChangeEvent event)
                            {
                              Object value = listOfValues.get(listBox.getSelectedIndex());
                              propertyDescriptor.setPropertyValue(object, value);
                            }
                          });
                       runnable.run();
                       propertyUpdater.add(runnable);
                     }
                   }
                   else if (eType instanceof EDataType)
                   {
                     final EDataType eDataType = (EDataType)eType;
                     if (eDataType.isSerializable())
                     {
                       if (propertyDescriptor.isMany(object))
                       {
                         final ItemPropertyDescriptorFeatureEditor dialog  =
                           new ItemPropertyDescriptorFeatureEditor(object, propertyDescriptor)
                           {
                             @Override
                             protected void hook(ListBox listBox, List<?> values)
                             {
                               navigationListener.listenTo(listBox, values);
                             }
                           };
                         dialog.update();
                         widget = dialog;
                         propertyUpdater.add
                           (new Runnable()
                            {
                              public void run()
                              {
                                dialog.update();
                              }
                            });
                       }
                       else
                       {
                         if (eDataType.getInstanceClass() == Date.class)
                         {
                           final DateBox dateBox = new DateBox();
                           widget = dateBox;
                           Runnable runnable =
                             new Runnable()
                             {
                               public void run()
                               {
                                 Object propertyValue = itemDelegator.getEditableValue(propertyDescriptor.getPropertyValue(object));
                                 dateBox.setValue((Date)propertyValue);
                               }
                             };
                           dateBox.addValueChangeHandler
                             (new ValueChangeHandler<Date>()
                              {
                                public void onValueChange(ValueChangeEvent<Date> event)
                                {
                                  propertyDescriptor.setPropertyValue(object, event.getValue());
                                }
                              });
                           runnable.run();
                           propertyUpdater.add(runnable);

                         }
                         else if (eDataType.getInstanceClass() == Boolean.class || eDataType.getInstanceClass() == boolean.class)
                         {
                           final CheckBox checkBox = new CheckBox();
                           widget = checkBox;
                           Runnable runnable =
                             new Runnable()
                             {
                               public void run()
                               {
                                 Object propertyValue = itemDelegator.getEditableValue(propertyDescriptor.getPropertyValue(object));
                                 checkBox.setValue(Boolean.TRUE.equals(propertyValue));
                               }
                             };
                           checkBox.addValueChangeHandler
                             (new ValueChangeHandler<Boolean>()
                              {
                                public void onValueChange(ValueChangeEvent<Boolean> event)
                                {
                                  propertyDescriptor.setPropertyValue(object, event.getValue());
                                }
                              });
                           runnable.run();
                           propertyUpdater.add(runnable);
                         }
                         else if (propertyDescriptor.isMultiLine(object))
                         {
                           final TextArea textArea = new TextArea();
                           widget = textArea;
                           Runnable runnable =
                             new Runnable()
                             {
                               public void run()
                               {
                                 Object propertyValue = itemDelegator.getEditableValue(propertyDescriptor.getPropertyValue(object));
                                 textArea.setText(EcoreUtil.convertToString(eDataType, propertyValue));
                               }
                             };
                           textArea.addValueChangeHandler
                             (new ValueChangeHandler<String>()
                              {
                                public void onValueChange(ValueChangeEvent<String> event)
                                {
                                  propertyDescriptor.setPropertyValue(object, EcoreUtil.createFromString(eDataType, event.getValue()));
                                }
                              });
                           runnable.run();
                           propertyUpdater.add(runnable);
                         }
                         else
                         {
                           final TextBox textBox = new TextBox();
                           widget = textBox;
                           textBox.addValueChangeHandler
                             (new ValueChangeHandler<String>()
                              {
                                public void onValueChange(ValueChangeEvent<String> event)
                                {
                                  String value = event.getValue();
                                  propertyDescriptor.setPropertyValue(object, EcoreUtil.createFromString(eDataType, value));
                                  // TOD0
                                  // commandStack.execute(SetCommand.create(editingDomain, object, feature, EcoreUtil.createFromString(eDataType, value)));
                                }
                              });
                           Runnable runnable =
                             new Runnable()
                             {
                               public void run()
                               {
                                 Object propertyValue = itemDelegator.getEditableValue(propertyDescriptor.getPropertyValue(object));
                                 String stringValue = EcoreUtil.convertToString(eDataType, propertyValue);
                                 if (!textBox.getText().equals(stringValue))
                                 {
                                   textBox.setText(stringValue);
                                 }
                               }
                             };
                           runnable.run();
                           propertyUpdater.add(runnable);
                         }
                       }
                     }
                   }
                 }
                 else
                 {
                   final TextBox textBox = new TextBox();
                   widget = textBox;
                   textBox.addValueChangeHandler
                     (new ValueChangeHandler<String>()
                      {
                        public void onValueChange(ValueChangeEvent<String> event)
                        {
                          String value = event.getValue();
                          propertyDescriptor.setPropertyValue(object, value);
                        }
                      });
                   Runnable runnable =
                     new Runnable()
                     {
                       public void run()
                       {
                         Object propertyValue = itemDelegator.getEditableValue(propertyDescriptor.getPropertyValue(object));
                         String stringValue =
                           propertyValue == null ?
                             null :
                             propertyDescriptor.getLabelProvider(object).getText(propertyValue);
                         if (!textBox.getText().equals(stringValue))
                         {
                           textBox.setText(stringValue);
                         }
                       }
                     };
                   runnable.run();
                   propertyUpdater.add(runnable);
                 }
                 if (widget != null)
                 {
                   widget.setWidth("95%");
                   properties.setWidget(i, 1, widget);
                   if (!propertyDescriptor.canSetProperty(object) && widget instanceof FocusWidget)
                   {
                     ((FocusWidget)widget).setEnabled(false);

                   }
                 }
               }
             }
           }
         }
       });

    contentPanel.addWest(treeScrollPanel, 400);
    contentPanel.add(propertiesPanel);

    bodyPanel.addNorth(toolBar, 28.0);
    bodyPanel.add(contentPanel);

    RootPanel.get("main").add(mainPanel);

    createButton.addClickHandler
     (new ClickHandler()
      {
        public void onClick(ClickEvent event)
        {
          Object selection= treeSelection.getSelectedObject();
          if (selection instanceof ResourceSet)
          {
            final DialogBox dialogBox = new DialogBox();
            dialogBox.setText("Create Resource");
            final Button okButton = new Button("OK");
            final Button cancelButton = new Button("Cancel");
            VerticalPanel verticalPanel = new VerticalPanel();
            Grid grid = new Grid();
            grid.setWidth("50em");
            grid.resize(3, 2);
            grid.getColumnFormatter().setWidth(0, "15%");
            grid.getColumnFormatter().setWidth(1, "85%");
            Label uriLabel = new Label();
            uriLabel.setText("URI");
            final TextBox uriText = new TextBox();
            uriText.setWidth("90%");
            int count = 1;
            while (resourceSet.getResource(URI.createURI("datastore:/resource" + count), false) != null)
            {
              ++count;
            }
            uriText.setValue("datastore:/resource" + count);
            uriText.selectAll();
            final Label message = new Label();
            uriText.addValueChangeHandler
              (new ValueChangeHandler<String>()
                {
                  public void onValueChange(ValueChangeEvent<String> event)
                  {
                    String value = event.getValue();
                    try
                    {
                      if (resourceSet.getResource(URI.createURI(value), false) != null)
                      {
                        message.setText("This URI a duplicate");
                        okButton.setEnabled(false);
                      }
                      else
                      {
                        message.setText("");
                        okButton.setEnabled(true);
                      }
                    }
                    catch (RuntimeException exception)
                    {
                      message.setText("This URI is not a well formed");
                      okButton.setEnabled(false);
                    }
                  }
                });
            grid.setWidget(0, 0, uriLabel);
            grid.setWidget(0, 1, uriText);
            verticalPanel.add(grid);
            grid.setWidget(1, 1, message);
            grid.setWidget(2, 0, okButton);
            grid.setWidget(2, 1, cancelButton);
            dialogBox.setWidget(verticalPanel);
            dialogBox.show();
            uriText.setFocus(true);
            // dialogBox.setWidth("50em");
            okButton.addClickHandler
              (new ClickHandler()
               {
                 public void onClick(ClickEvent event)
                 {
                   dialogBox.hide();
                   commandStack.execute
                     (new AbstractCommand()
                      {
                        Collection<?> affectedObjects;
                        Resource resource;

                        @Override
                        protected boolean prepare()
                        {
                          return true;
                        }

                        public void redo()
                        {
                          resourceSet.getResources().add(resource);
                          affectedObjects = Collections.singleton(resource);
                        }

                        @Override
                        public void undo()
                        {
                          resourceSet.getResources().remove(resource);
                          affectedObjects = Collections.singleton(resourceSet);
                        }

                        public void execute()
                        {
                          resource = resourceSet.createResource(URI.createURI(uriText.getValue()));
                          resource.getContents().clear();
                          affectedObjects = Collections.singleton(resource);
                        }

                        @Override
                        public Collection<?> getAffectedObjects()
                        {
                          return affectedObjects;
                        }
                      });
                 }
               });
            cancelButton.addClickHandler
              (new ClickHandler()
               {
                 public void onClick(ClickEvent event)
                 {
                   dialogBox.hide();
                 }
               });
          }
          else if (selection instanceof Resource)
          {
            final Resource resource = (Resource)selection;
            final DialogBox dialogBox = new DialogBox();
            dialogBox.setText("Create Object");
            final ListBox listBox = new ListBox();
            listBox.setVisibleItemCount(1);
            Registry packageRegistry = resourceSet.getPackageRegistry();
            for (String nsURI : packageRegistry.keySet())
            {
              EPackage ePackage = packageRegistry.getEPackage(nsURI);
              for (EClassifier eClassifier : ePackage.getEClassifiers())
              {
                if (eClassifier instanceof EClass)
                {
                  EClass eClass = (EClass)eClassifier;
                  if (!eClass.isAbstract())
                  {
                    EObject eObject = EcoreUtil.create(eClass);
                    listBox.addItem("New " + itemDelegator.getText(eObject), EcoreUtil.getURI(eClass).toString());
                  }
                }
              }
            }
            final Button okButton = new Button("OK");
            final Button cancelButton = new Button("Cancel");
            VerticalPanel verticalPanel = new VerticalPanel();
            Grid grid = new Grid();
            grid.setWidth("50em");
            grid.resize(2, 2);
            grid.getColumnFormatter().setWidth(0, "15%");
            grid.getColumnFormatter().setWidth(1, "85%");
            Label classLabel = new Label();
            classLabel.setText("Class");
            grid.setWidget(0, 0, classLabel);
            grid.setWidget(0, 1, listBox);
            verticalPanel.add(grid);
            // verticalPanel.add(okButton);
            grid.setWidget(1, 0, okButton);
            grid.setWidget(1, 1, cancelButton);
            dialogBox.setWidget(verticalPanel);
            dialogBox.show();
            listBox.setFocus(true);
            // dialogBox.setWidth("50em");
            okButton.addClickHandler
              (new ClickHandler()
               {
                 public void onClick(ClickEvent event)
                 {
                   dialogBox.hide();
                   commandStack.execute
                     (new AbstractCommand()
                      {
                        EObject eObject;
                        Collection<?> affectedObjects;

                        @Override
                        protected boolean prepare()
                        {
                          return true;
                        }

                        public void redo()
                        {
                          resource.getContents().add(eObject);
                          affectedObjects = Collections.singleton(eObject);
                        }

                        @Override
                        public void undo()
                        {
                          resource.getContents().remove(eObject);
                          affectedObjects = Collections.singleton(resource);
                        }

                        public void execute()
                        {
                          eObject = EcoreUtil.create((EClass)resourceSet.getEObject(URI.createURI(listBox.getValue(listBox.getSelectedIndex())), false));
                          resource.getContents().add(eObject);
                          affectedObjects = Collections.singleton(eObject);
                        }

                        @Override
                        public Collection<?> getAffectedObjects()
                        {
                          return affectedObjects;
                        }
                      });
                 }
               });
            cancelButton.addClickHandler
              (new ClickHandler()
               {
                 public void onClick(ClickEvent event)
                 {
                   dialogBox.hide();
                 }
               });

          }
          else
          {
            Collection<?> newChildDescriptors = editingDomain.getNewChildDescriptors(selection, null);
            final DialogBox dialogBox = new DialogBox();
            dialogBox.setText("Create Object");
            final ListBox listBox = new ListBox();
            final List<Command> commands = new ArrayList<Command>();
            listBox.setVisibleItemCount(1);
            for (Object descriptor : newChildDescriptors)
            {
              Command command = CreateChildCommand.create(editingDomain, selection, descriptor, Collections.singleton(selection));
              commands.add(command);
              listBox.addItem(command.getLabel());
            }
            final Button okButton = new Button("OK");
            final Button cancelButton = new Button("Cancel");
            VerticalPanel verticalPanel = new VerticalPanel();
            Grid grid = new Grid();
            grid.setWidth("50em");
            grid.resize(2, 2);
            grid.getColumnFormatter().setWidth(0, "15%");
            grid.getColumnFormatter().setWidth(1, "85%");
            Label classLabel = new Label();
            classLabel.setText("Class");
            grid.setWidget(0, 0, classLabel);
            grid.setWidget(0, 1, listBox);
            verticalPanel.add(grid);
            // verticalPanel.add(okButton);
            grid.setWidget(1, 0, okButton);
            grid.setWidget(1, 1, cancelButton);
            dialogBox.setWidget(verticalPanel);
            dialogBox.show();
            listBox.setFocus(true);
            // dialogBox.setWidth("50em");
            okButton.addClickHandler
              (new ClickHandler()
               {
                 public void onClick(ClickEvent event)
                 {
                   dialogBox.hide();
                   commandStack.execute(commands.get(listBox.getSelectedIndex()));
                 }
               });
            cancelButton.addClickHandler
              (new ClickHandler()
               {
                 public void onClick(ClickEvent event)
                 {
                   dialogBox.hide();
                 }
               });
          }
        }
      });

    saveButton.addClickHandler
     (new ClickHandler()
      {
        public void onClick(ClickEvent event)
        {
          EList<Resource> resources = resourceSet.getResources();
          for (Resource resource : resources)
          {
            try
            {
              if (resource.isLoaded())
              {
                Map<String, Object> options = null;
                if (originalResources.contains(resource))
                {
                  options = new HashMap<String, Object>();
                  options.put(URIConverter.OPTION_UPDATE_ONLY_IF_TIME_STAMP_MATCHES, resource.getTimeStamp());
                }
                resource.save
                  (options,
                   new Callback<Resource>()
                   {
                     public void onFailure(Throwable caught)
                     {
                       final DialogBox dialogBox = new DialogBox();
                       dialogBox.setText("Save conflict");
                       final Button okButton = new Button("OK");
                       final Tree tree = new Tree();
                       tree.addItem(caught.getLocalizedMessage());
                       Grid grid = new Grid();
                       grid.setWidth("150em");
                       grid.resize(2, 1);
                       grid.setWidget(0, 0, tree);
                       grid.setWidget(1, 0, okButton);
                       dialogBox.setWidget(grid);
                       dialogBox.show();
                       okButton.addClickHandler
                         (new ClickHandler()
                          {
                            public void onClick(ClickEvent event)
                            {
                              dialogBox.hide();
                            }
                          });
                     }

                     public void onSuccess(Resource result)
                     {
                       // TODO Auto-generated method stub
                     }
                   });
              }
            }
            catch (IOException exception)
            {
              EMFEditUIPlugin.INSTANCE.log(exception);
            }
          }
          for (Resource resource : originalResources)
          {
            if (!resources.contains(resource))
            {
              Map<String, Object> options = null;
              if (resource.isLoaded())
              {
                options = new HashMap<String, Object>();
                options.put(URIConverter.OPTION_UPDATE_ONLY_IF_TIME_STAMP_MATCHES, resource.getTimeStamp());
              }
              resourceSet.getURIConverter().delete
                (resource.getURI(),
                  options,
                  new Callback<Map<?, ?>>()
                  {
                    public void onFailure(Throwable caught)
                    {
                       final DialogBox dialogBox = new DialogBox();
                       dialogBox.setText("Delete conflict");
                       final Button okButton = new Button("OK");
                       final Tree tree = new Tree();
                       tree.addItem(caught.getLocalizedMessage());
                       Grid grid = new Grid();
                       grid.setWidth("150em");
                       grid.resize(2, 1);
                       grid.setWidget(0, 0, tree);
                       grid.setWidget(1, 0, okButton);
                       dialogBox.setWidget(grid);
                       dialogBox.show();
                       okButton.addClickHandler
                         (new ClickHandler()
                          {
                            public void onClick(ClickEvent event)
                            {
                              dialogBox.hide();
                            }
                          });
                    }

                    public void onSuccess(Map<?, ?> result)
                    {
                      // TODO Auto-generated method stub
                    }
                  });
            }
          }
          originalResources.clear();
          originalResources.addAll(resources);
          commandStack.saveIsDone();
          saveButton.setEnabled(false);
        }
      });

    validateButton.addClickHandler
     (new ClickHandler()
      {
        protected TreeItem createTreeItems(Diagnostic diagnostic)
        {
          TreeItem treeItem = new TreeItem(diagnostic.getMessage());
          List<?> data = diagnostic.getData();
          if (!data.isEmpty())
          {
            treeItem.setUserObject(data.get(0));
          }
          for (Diagnostic child : diagnostic.getChildren())
          {
            treeItem.addItem(createTreeItems(child));
          }
          return treeItem;
        }

        public void onClick(ClickEvent event)
        {
          Diagnostician diagnostician =
            new Diagnostician()
            {
              @Override
              public String getObjectLabel(EObject eObject)
              {
                return !eObject.eIsProxy() ? itemDelegator.getText(eObject) : super.getObjectLabel(eObject);
              }
            };
          Map<Object, Object> context = diagnostician.createDefaultContext();
          EList<Resource> resources = resourceSet.getResources();
          BasicDiagnostic diagnostics = new BasicDiagnostic();
          for (Resource resource : resources)
          {
            if (resource.isLoaded())
            {
              for (EObject eObject : resource.getContents())
              {
                diagnostician.validate(eObject, diagnostics, context);
              }
            }
          }
          final DialogBox dialogBox = new DialogBox();
          dialogBox.setText("Problems");
          final Button okButton = new Button("OK");
          final Tree tree = new Tree();
          if (diagnostics.getSeverity() == Diagnostic.OK)
          {
            tree.addItem("No problems detected");
          }
          else
          {
            for (Diagnostic child : diagnostics.getChildren())
            {
              tree.addItem(createTreeItems(child));
            }
          }
          Grid grid = new Grid();
          grid.setWidth("150em");
          grid.resize(2, 1);
          grid.setWidget(0, 0, tree);
          grid.setWidget(1, 0, okButton);
          dialogBox.setWidget(grid);
          dialogBox.show();
          okButton.addClickHandler
            (new ClickHandler()
             {
               public void onClick(ClickEvent event)
               {
                 TreeItem treeItem = tree.getSelectedItem();
                 if (treeItem != null)
                 {
                   Object newSelection = treeItem.getUserObject();
                   if (newSelection != null)
                   {
                     cellTree.expand(editingDomain.getTreePath(editingDomain.getParent(newSelection)));
                     treeSelection.setSelected(newSelection, true);
                   }
                 }
                 dialogBox.hide();
               }
             });
        }
      });
    saveButton.setEnabled(false);
    undoButton.setEnabled(false);
    redoButton.setEnabled(false);

    commandStack.addCommandStackListener
      (new CommandStackListener()
       {
         public void commandStackChanged(EventObject event)
         {
           saveButton.setEnabled(commandStack.isSaveNeeded());
           undoButton.setEnabled(commandStack.canUndo());
           redoButton.setEnabled(commandStack.canRedo());

           Command mostRecentCommand = ((CommandStack)event.getSource()).getMostRecentCommand();
           if (mostRecentCommand != null)
           {
             Collection<?> affectedObjects = mostRecentCommand.getAffectedObjects();
             if (!affectedObjects.isEmpty())
             {
               Object newSelection = affectedObjects.iterator().next();
               cellTree.expand(editingDomain.getTreePath(editingDomain.getParent(newSelection)));
               if (treeSelection.getSelectedObject() == newSelection)
               {
                 for (Runnable runnable : propertyUpdater)
                 {
                   runnable.run();
                 }
               }
               else
               {
                 treeSelection.setSelected(newSelection, true);
               }
             }
           }
         }
       });

    undoButton.addClickHandler
     (new ClickHandler()
      {
        public void onClick(ClickEvent event)
        {
          commandStack.undo();
        }
      });

    redoButton.addClickHandler
     (new ClickHandler()
      {
        public void onClick(ClickEvent event)
        {
          commandStack.redo();
        }
      });
  }

  protected abstract void registerPackages(EPackage.Registry ePackageRegistry);

  protected void configureItemProviderAdapterFactories(ComposedAdapterFactory adapterFactory)
  {
    // Do nothing.
  }

  protected abstract String getApplicationTitle();

  public static class ImageData
  {
    public int width;
    public int height;
  }

  protected ImageData getImageData(Object image)
  {
    ImageData imageData = new ImageData();
    if (image instanceof ImageResource)
    {
      ImageResource imageResource = (ImageResource)image;
      imageData.height = imageResource.getHeight();
      imageData.width = imageResource.getWidth();
    }
    else if (image instanceof ComposedImage)
    {
      ComposedImage composedImage = (ComposedImage)image;
      List<ComposedImage.Size> sizes = new ArrayList<ComposedImage.Size>();
      List<Object> images = new ArrayList<Object>(composedImage.getImages());
      List<ImageData> nestedImagesData = new ArrayList<ImageData>();
      for (Object nestedImage : images)
      {
        ImageData nestedImageData = getImageData(nestedImage);
        ComposedImage.Size size = new ComposedImage.Size();
        size.height = nestedImageData.height;
        size.width = nestedImageData.width;
        sizes.add(size);
        nestedImagesData.add(nestedImageData);
      }
    }
    return imageData;
  }

  public abstract static class FeatureEditor extends Composite
  {
    protected Object object;
    protected boolean isSortChoices;
    protected List<?> values;
    protected List<?> choiceOfValues;
    protected ListBox valueBox;
    protected ListBox choiceBox;
    protected EDataType eDataType;
    protected TextBox textBox;

    protected FeatureEditor(Object object, boolean isSortChoices, final EDataType eDataType)
    {
      this.object = object;
      this.isSortChoices = isSortChoices;
      this.eDataType = eDataType;

      Grid grid = new Grid();
      grid.resize(1, 3);

      grid.getColumnFormatter().setWidth(0, "40%");
      grid.getColumnFormatter().setWidth(1, "20%");
      grid.getColumnFormatter().setWidth(2, "40%");


      valueBox = new ListBox(true);
      valueBox.setVisibleItemCount(4);
      valueBox.setWidth("100%");
      grid.setWidget(0, 0, valueBox);
      
      Grid buttonGrid = new Grid();
      buttonGrid.resize(2, 2);
      Button upButton = new Button("Up ^");
      buttonGrid.setWidget(0, 0, upButton);
      Button downButton = new Button("Down v");
      buttonGrid.setWidget(1, 0, downButton);
      Button addButton = new Button("< Add");
      buttonGrid.setWidget(0, 1, addButton);
      Button removeButton = new Button("Remove >");
      buttonGrid.setWidget(1, 1, removeButton);
      grid.setWidget(0, 1, buttonGrid);

      if (eDataType != null)
      {
        textBox = new TextBox();
        textBox.setWidth("100%");
        grid.setWidget(0, 2, textBox);

        /*
        textBox.addValueChangeHandler
          (new ValueChangeHandler<String>()
           {
             public void onValueChange(ValueChangeEvent<String> event)
             {
               List<Object> result =  isUnique() ? new UniqueEList<Object>(values) : new ArrayList<Object>(values);
               try
               {
                 Object value = EcoreUtil.createFromString(eDataType, event.getValue());
                 result.add(value);
                 setValue(result);
               }
               catch (RuntimeException exception)
               {
                 // Ignore for now
               }
             }
           });
        */
      }
      else
      {
        choiceBox = new ListBox(true);
        choiceBox.setVisibleItemCount(4);
        choiceBox.setWidth("100%");
        grid.setWidget(0, 2, choiceBox);
      }

      addButton.addClickHandler
       (new ClickHandler()
        {
          public void onClick(ClickEvent event)
          {
            List<Object> result =  isUnique() ? new UniqueEList<Object>(values) : new ArrayList<Object>(values);
            if (eDataType != null)
            {
              try
              {
                Object value = EcoreUtil.createFromString(eDataType, textBox.getValue());
                result.add(value);
              }
              catch (RuntimeException exception)
              {
                // Ignore for now
              }
            }
            else
            {
              for (int i = 0, size = choiceBox.getItemCount(); i < size; ++i)
              {
                if (choiceBox.isItemSelected(i))
                {
                  result.add(choiceOfValues.get(i));
                }
              }
            }
            setValue(result);
          }
        });

      removeButton.addClickHandler
       (new ClickHandler()
        {
          public void onClick(ClickEvent event)
          {
            List<Object> result = isUnique() ? new UniqueEList<Object>(values) : new ArrayList<Object>(values);
            for (int i = 0, size = valueBox.getItemCount(); i < size; ++i)
            {
              if (valueBox.isItemSelected(i))
              {
                result.remove(values.get(i));
              }
            }
            setValue(result);
          }
        });
      upButton.addClickHandler
       (new ClickHandler()
        {
          public void onClick(ClickEvent event)
          {
            EList<Object> result = new BasicEList<Object>(values);
            for (int i = 0, size = valueBox.getItemCount(); i < size; ++i)
            {
              if (valueBox.isItemSelected(i))
              {
                if (i != 0)
                {
                  result.move(i, i - 1);
                }
              }
            }
            setValue(result);
          }
        });
      downButton.addClickHandler
       (new ClickHandler()
        {
          public void onClick(ClickEvent event)
          {
            EList<Object> result = new BasicEList<Object>(values);
            for (int i = 0, size = valueBox.getItemCount(); i < size; ++i)
            {
              if (valueBox.isItemSelected(i))
              {
                if (i + 1 < size)
                {
                  result.move(i, i + 1);
                }
              }
            }
            setValue(result);
          }
        });
      initWidget(grid);
    }

    protected void hook(ListBox listBox, List<?> values)
    {
      // Do nothing.

    }

    public void update()
    {
      IItemLabelProvider labelProvider = getLabelProvider();
      List<?> oldValues = values;
      values = new ArrayList<Object>(getValues());

      List<Object> oldValueSelection = new ArrayList<Object>();
      int valueBoxItemCount = valueBox.getItemCount();
      if (valueBoxItemCount != 0)
      {
        for (int i = 0; i < valueBoxItemCount; ++i)
        {
          if (valueBox.isItemSelected(i))
          {
            oldValueSelection.add(oldValues.get(i));
          }
        }
        valueBox.clear();
      }

      for (Object value : values)
      {
        valueBox.addItem(labelProvider.getText(value));
        if (oldValueSelection.contains(value) || oldValues != null && !oldValues.contains(value))
        {
          valueBox.setItemSelected(valueBox.getItemCount() - 1, true);
        }
      }

      hook(valueBox, values);

      if (eDataType != null)
      {
        textBox.setValue("");
      }
      else
      {
        List<Object> oldChoiceSelection = new ArrayList<Object>();
        int choiceBoxItemCount = choiceBox.getItemCount();
        if (choiceBoxItemCount != 0)
        {
          for (int i = 0; i < choiceBoxItemCount; ++i)
          {
            if (choiceBox.isItemSelected(i))
            {
              oldChoiceSelection.add(choiceOfValues.get(i));
            }
          }
          choiceBox.clear();
        }

        Collection<?> basicChoices = getChoiceOfValues();
        if (basicChoices == null)
        {
          choiceOfValues = null;
        }
        else
        {
          choiceOfValues = new ArrayList<Object>(basicChoices);
          if (isSortChoices)
          {
            createItems(choiceOfValues, labelProvider, null, true);
          }
          for (Object value : choiceOfValues)
          {
            choiceBox.addItem(labelProvider.getText(value));
            if (oldChoiceSelection.contains(value) || oldValues != null && oldValues.contains(value) && !values.contains(value))
            {
              choiceBox.setItemSelected(choiceBox.getItemCount() - 1, true);
            }
          }
          hook(choiceBox, choiceOfValues);
        }
      }
    }

    protected abstract IItemLabelProvider getLabelProvider();
    protected abstract List<?> getValues();
    protected abstract Collection<?> getChoiceOfValues();
    protected abstract boolean isMultiLine();
    protected abstract boolean isUnique();
    protected abstract void setValue(Object value);
  }

  public static class ItemPropertyDescriptorFeatureEditor extends FeatureEditor
  {
    protected IItemPropertyDescriptor propertyDescriptor;

    public ItemPropertyDescriptorFeatureEditor(Object object, IItemPropertyDescriptor propertyDescriptor)
    {
      super(object, false, ((EAttribute)propertyDescriptor.getFeature(object)).getEAttributeType());
      this.propertyDescriptor = propertyDescriptor;
    }

    public ItemPropertyDescriptorFeatureEditor(Object object, boolean isSortChoices, IItemPropertyDescriptor propertyDescriptor)
    {
      super(object, isSortChoices, null);
      this.propertyDescriptor = propertyDescriptor;
    }

    @Override
    protected IItemLabelProvider getLabelProvider()
    {
      return propertyDescriptor.getLabelProvider(object);
    }

    @Override
    protected List<?> getValues()
    {
      Object propertyValue = propertyDescriptor.getPropertyValue(object);
      if (propertyValue instanceof IItemPropertySource)
      {
        propertyValue = ((IItemPropertySource)propertyValue).getEditableValue(propertyValue);
      }
      return (List<?>)propertyValue;
    }

    @Override
    protected Collection<?> getChoiceOfValues()
    {
      return propertyDescriptor.getChoiceOfValues(object);
    }

    @Override
    protected boolean isMultiLine()
    {
      return propertyDescriptor.isMultiLine(object);
    }

    @Override
    protected boolean isUnique()
    {
      Object feature = propertyDescriptor.getFeature(object);
      return !(feature instanceof EStructuralFeature) || ((EStructuralFeature)feature).isUnique();
    }

    @Override
    protected void setValue(Object value)
    {
      propertyDescriptor.setPropertyValue(object, value);
    }
  }

  private static class StringPositionPair implements Comparable<StringPositionPair>
  {
    public String key;
    public int position;

    StringPositionPair(String key, int position)
    {
      this.key = key;
      this.position = position;
    }

    public int compareTo(StringPositionPair object)
    {
      if (object == this || key == object.key)
      {
        return 0;
      }
      else if (key == null)
      {
        return -1;
      }
      else
      {
        return key.compareTo(object.key);
      }
    }
  }

  public static <T> String[] createItems(List<T> list, IItemLabelProvider labelProvider, String filter, boolean sorted)
  {
    String[] result;

    if (filter != null && filter.length() > 0)
    {
      sorted = true;
    }

    // If there are objects to populate...
    //
    if (list != null && list.size() > 0)
    {
      if (sorted)
      {
        List<T> unsortedList = new ArrayList<T>(list.size());
        if (filter != null && filter.length() > 0)
        {
          for (int i = 0; i < list.size(); i++)
          {
            if (select(filter, labelProvider.getText(list.get(i))))
            {
              unsortedList.add(list.get(i));
            }
          }
        }
        else
        {
          unsortedList.addAll(list);
        }
        list.clear();

        StringPositionPair[] pairs = new StringPositionPair [unsortedList.size()];

        for (int i = 0, size = unsortedList.size(); i < size; ++i)
        {
          Object object = unsortedList.get(i);
          pairs[i] = new StringPositionPair(labelProvider.getText(object), i);
        }

        Arrays.sort(pairs);

        // Create a new array.
        //
        result = new String [unsortedList.size()];
        // Fill in the result array with labels and re-populate the original list in order.
        //
        for (int i = 0, size = unsortedList.size(); i < size; ++i)
        {
          result[i] = pairs[i].key;
          list.add(unsortedList.get(pairs[i].position));
        }
      }
      else
      {
        // Create a new array.
        //
        result = new String [list.size()];
        // Fill in the array with labels.
        //
        for (int i = 0, size = list.size(); i < size; ++i)
        {
          Object object = list.get(i);
          result[i] = labelProvider.getText(object);
        }
      }
    }
    else
    {
      result = new String [] { "" };
    }

    return result;
  }

  public static boolean select(String filter, String labelValue)
  {
    if (filter != null && filter.length() > 0)
    {
      if (filter.length() > labelValue.length())
      {
        return false;
      }
      for (int i = 0; i < filter.length(); i++)
      {
        if (Character.toLowerCase(filter.charAt(i)) != Character.toLowerCase(labelValue.charAt(i)))
        {
          return false;
        }
      }
    }
    return true;
  }

  static class MyCellTree extends CellTree
  {
    public <T> MyCellTree(TreeViewModel viewModel, T rootValue)
    {
      super(viewModel, rootValue, (Resources)GWT.create(CellTree.BasicResources.class));
    }
 
    public void expand(List<?> path)
    {
      TreeNode node = getRootTreeNode();
      if (node != null)
      {
        LOOP:
        for (int i = 0, size = path.size(); i < size; ++i)
        {
          Object segment = path.get(i);
          for (int j = 0, count = node.getChildCount(); j < count; ++j)
          {
            Object childValue = node.getChildValue(j);
            if (childValue.equals(segment))
            {
              node = node.setChildOpen(j, true);
              continue LOOP;
            }
          }
          break;
        }
      }
    }
  }
  
}
