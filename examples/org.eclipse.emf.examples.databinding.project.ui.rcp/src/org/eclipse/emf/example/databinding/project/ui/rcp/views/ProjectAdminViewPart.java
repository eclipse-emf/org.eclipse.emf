/**
 * <copyright>
 *
 * Copyright (c) 2009 Tom Schindl and others.
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
 * $Id: ProjectAdminViewPart.java,v 1.1 2009/05/29 15:06:30 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.views;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.databinding.AggregateValidationStatus;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ObservablesManager;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IMapChangeListener;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.map.MapChangeEvent;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.databinding.swt.IWidgetValueProperty;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapCellLabelProvider;
import org.eclipse.jface.databinding.viewers.TreeStructureAdvisor;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.IEMFListProperty;
import org.eclipse.emf.databinding.IEMFValueProperty;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip;
import org.eclipse.emf.examples.databinding.project.core.model.project.Person;
import org.eclipse.emf.examples.databinding.project.core.model.project.Project;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage;
import org.eclipse.emf.example.databinding.project.ui.rcp.Activator;
import org.eclipse.emf.example.databinding.project.ui.rcp.NLSMessages;
import org.eclipse.emf.example.databinding.project.ui.rcp.databinding.DateToStringConverter;
import org.eclipse.emf.example.databinding.project.ui.rcp.databinding.FormTextProperty;
import org.eclipse.emf.example.databinding.project.ui.rcp.databinding.StringToDateConverter;


/**
 * @author Tom Schindl
 */
public class ProjectAdminViewPart extends ViewPart
{
  /**
   * Id of the view
   */
  public static final String ID = "org.eclipse.emf.example.project.ui.rcp.views.ProjectAdminViewPart";

  private TreeViewer viewer;
  private SashForm sashForm;

  private FormToolkit toolkit;
  private Form form;

  private float divider = 0.2f;
  private static final String DIVIDER_KEY = Activator.PLUGIN_ID + ".divider";

  private Resource resource;
  private EditingDomain editingDomain;

  private DataBindingContext ctx;
  private ObservablesManager mgr;
  private static String END_DATE_PROPERTY = "enddate";

  @Override
  public void init(IViewSite site, IMemento memento) throws PartInitException
  {
    super.init(site, memento);
    if (memento != null && memento.getFloat(DIVIDER_KEY) != null)
    {
      divider = memento.getFloat(DIVIDER_KEY);
    }
  }

  @Override
  public void saveState(IMemento memento)
  {
    super.saveState(memento);
    int total = sashForm.getWeights()[0] + sashForm.getWeights()[1];
    memento.putFloat(DIVIDER_KEY, sashForm.getWeights()[0] * 1.f / total);
  }

  @Override
  public void createPartControl(Composite parent)
  {
    toolkit = new FormToolkit(parent.getDisplay());
    loadModel();
    sashForm = new SashForm(parent, SWT.HORIZONTAL);

    mgr = new ObservablesManager();
    mgr.runAndCollect(new Runnable()
      {

        public void run()
        {
          createTreeArea(sashForm);
          createFormArea(sashForm);
        }
      });

    int left = (int)(100 * divider);

    sashForm.setWeights(new int []{ left, 100 - left });
  }

  private void createTreeArea(Composite parent)
  {

    ObservableListTreeContentProvider cp = new ObservableListTreeContentProvider(new TreeFactoryImpl(), new TreeStructureAdvisorImpl());
    IObservableSet set = cp.getKnownElements();

    IObservableMap[] map = new IObservableMap [2];
    map[0] = EMFProperties.value(ProjectPackage.Literals.PROJECT__SHORTNAME).observeDetail(set);
    map[1] = EMFProperties.value(ProjectPackage.Literals.PROJECT__COMMITTERS).observeDetail(set);

    IEMFListProperty projects = EMFProperties.list(ProjectPackage.Literals.FOUNDATION__PROJECTS);

    viewer = new TreeViewer(parent);
    viewer.setContentProvider(cp);
    viewer.setLabelProvider(new LabelProviderImpl(map));
    viewer.setInput(projects.observe(resource.getContents().get(0)));

    ColumnViewerToolTipSupportImpl.enableFor(viewer, toolkit);
  }

  private void createFormArea(Composite parent)
  {
    final IObservableValue master = ViewerProperties.singleSelection().observe(viewer);
    ctx = new EMFDataBindingContext();
    addStatusSupport(ctx);

    form = toolkit.createForm(parent);
    toolkit.decorateFormHeading(form);
    form.setText(" ");

    Composite body = form.getBody();
    body.setLayout(new GridLayout(2, false));

    IWidgetValueProperty prop = WidgetProperties.text(SWT.Modify);

    {
      final IEMFValueProperty shortProp = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.PROJECT__SHORTNAME);;
      toolkit.createLabel(body, "&Short name");
      Text t = toolkit.createText(body, "");
      t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      ctx.bindValue(prop.observeDelayed(400, t), shortProp.observeDetail(master));

      final IEMFValueProperty longProp = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.PROJECT__LONGNAME);
      toolkit.createLabel(body, "&Long name");
      t = toolkit.createText(body, "");
      t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
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
      t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      ctx.bindValue(
        prop.observeDelayed(400, t),
        mProp.observeDetail(master),
        new EMFUpdateValueStrategy().setConverter(new StringToDateConverter(NLSMessages.ProjectAdminViewPart_StartDateNotParseable)),
        new EMFUpdateValueStrategy().setConverter(new DateToStringConverter()));
    }

    {
      IEMFValueProperty mProp = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.PROJECT__END);
      toolkit.createLabel(body, "End Date");
      Text t = toolkit.createText(body, "");
      t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      ctx.bindValue(
        prop.observeDelayed(400, t),
        mProp.observeDetail(master),
        new EMFUpdateValueStrategy().setConverter(new StringToDateConverter(NLSMessages.ProjectAdminViewPart_EndDateNotParseable)),
        new EMFUpdateValueStrategy().setConverter(new DateToStringConverter()));
    }

    addTabArea(ctx, master, body);
    body.setBackgroundMode(SWT.INHERIT_DEFAULT);
  }

  private void addTabArea(DataBindingContext ctx, IObservableValue master, Composite parent)
  {
    CTabFolder folder = new CTabFolder(parent, SWT.BORDER);
    folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
    folder.setSelectionBackground(new Color []{
      toolkit.getColors().getColor(IFormColors.H_GRADIENT_END),
      toolkit.getColors().getColor(IFormColors.H_GRADIENT_START) }, new int []{ 25 }, true);
    folder.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
    folder.setSelectionForeground(toolkit.getColors().getColor(IFormColors.TITLE));
    folder.setSimple(false);

    final TableViewer viewer = new TableViewer(folder, SWT.FULL_SELECTION);

    {
      IValueProperty prop = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.PROJECT__COMMITTERS);
      CTabItem item = new CTabItem(folder, SWT.NONE);
      item.setControl(viewer.getControl());

      ctx.bindValue(
        WidgetProperties.text().observe(item),
        prop.observeDetail(master),
        new EMFUpdateValueStrategy(),
        new EMFUpdateValueStrategy().setConverter(new LengthConverter()));
    }

    folder.setSelection(0);

    viewer.getTable().setHeaderVisible(true);
    ObservableListContentProvider cp = new ObservableListContentProvider();

    {
      IObservableMap[] attributeMap = new IObservableMap [2];
      attributeMap[0] = EMFEditProperties.value(
        editingDomain,
        FeaturePath.fromList(ProjectPackage.Literals.COMMITTER_SHIP__PERSON, ProjectPackage.Literals.PERSON__LASTNAME)).observeDetail(
        cp.getKnownElements());
      attributeMap[1] = EMFEditProperties.value(
        editingDomain,
        FeaturePath.fromList(ProjectPackage.Literals.COMMITTER_SHIP__PERSON, ProjectPackage.Literals.PERSON__FIRSTNAME)).observeDetail(
        cp.getKnownElements());

      TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
      column.getColumn().setText("Name");
      column.getColumn().setWidth(150);
      column.setLabelProvider(new ObservableMapCellLabelProviderImpl("{0}, {1}", attributeMap));
    }

    {
      IObservableMap attributeMap = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.COMMITTER_SHIP__START).observeDetail(
        cp.getKnownElements());
      TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
      column.getColumn().setText("Start");
      column.getColumn().setWidth(100);
      column.setLabelProvider(new ObservableMapCellLabelProviderImpl("{0,date,short}", attributeMap));
    }

    {
      IObservableMap attributeMap = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.COMMITTER_SHIP__END).observeDetail(
        cp.getKnownElements());
      TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
      column.getColumn().setText("End");
      column.getColumn().setWidth(100);
      column.setLabelProvider(new ObservableMapCellLabelProviderImpl("{0,date,short}", attributeMap));
    }

    IListProperty prop = EMFEditProperties.list(editingDomain, ProjectPackage.Literals.PROJECT__COMMITTERS);
    viewer.setContentProvider(cp);
    viewer.setInput(prop.observeDetail(master));

    MenuManager mgr = new MenuManager();
    mgr.add(new Action("Hide historic committers", IAction.AS_CHECK_BOX)
      {
        @Override
        public void run()
        {
          if (isChecked())
          {
            viewer.addFilter(new ViewerFilterImpl());
          }
          else
          {
            viewer.setFilters(new ViewerFilter [0]);
          }
        }
      });

    viewer.getControl().setMenu(mgr.createContextMenu(viewer.getControl()));

    getSite().registerContextMenu(Activator.PLUGIN_ID + ".committers", mgr, viewer);
  }

  private void addStatusSupport(final DataBindingContext ctx)
  {
    final AggregateValidationStatus aggregateStatus = new AggregateValidationStatus(
      ctx.getValidationStatusProviders(),
      AggregateValidationStatus.MAX_SEVERITY);

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
    if (form.isDisposed())
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

  @Override
  public void setFocus()
  {
    viewer.getControl().setFocus();
  }

  @Override
  public void dispose()
  {
    if (toolkit != null)
    {
      toolkit.dispose();
    }

    if (ctx != null)
    {
      ctx.dispose();
    }

    if (mgr != null)
    {
      mgr.dispose();
    }

    super.dispose();
  }

  private void loadModel()
  {
    ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

    ResourceSet resourceSet = new ResourceSetImpl();

    BasicCommandStack commandStack = new BasicCommandStack();
    //commandStack.addCommandStackListener(new CommandStackListenerImpl());
    editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, resourceSet);

    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
      Resource.Factory.Registry.DEFAULT_EXTENSION,
      new XMIResourceFactoryImpl());
    resource = resourceSet.getResource(URI.createPlatformPluginURI(Activator.PLUGIN_ID + "/Foundation.xmi", true), true);
  }

  private static class TreeFactoryImpl implements IObservableFactory
  {
    private IEMFListProperty subproject = EMFProperties.list(ProjectPackage.Literals.PROJECT__SUBPROJECTS);

    public IObservable createObservable(final Object target)
    {
      if (target instanceof IObservableList)
      {
        return (IObservable)target;
      }
      else if (target instanceof Project)
      {
        return subproject.observe(target);
      }

      return null;
    }
  }

  private static class TreeStructureAdvisorImpl extends TreeStructureAdvisor
  {
    @Override
    public Object getParent(Object element)
    {
      if (element instanceof Project)
      {
        return ((Project)element).getParent();
      }

      return null;
    }

    @Override
    public Boolean hasChildren(Object element)
    {
      if (element instanceof Project && ((Project)element).getCommitters().size() > 0)
      {
        return Boolean.TRUE;
      }
      return super.hasChildren(element);
    }
  }

  private static class LabelProviderImpl extends StyledCellLabelProvider
  {

    private IMapChangeListener mapChangeListener = new IMapChangeListener()
      {
        public void handleMapChange(MapChangeEvent event)
        {
          Set< ? > affectedElements = event.diff.getChangedKeys();
          LabelProviderChangedEvent newEvent = new LabelProviderChangedEvent(LabelProviderImpl.this, affectedElements.toArray());
          fireLabelProviderChanged(newEvent);
        }
      };

    public LabelProviderImpl(IObservableMap... attributeMaps)
    {
      for (int i = 0; i < attributeMaps.length; i++)
      {
        attributeMaps[i].addMapChangeListener(mapChangeListener);
      }
    }

    @Override
    public String getToolTipText(Object element)
    {
      return "a";
    }

    @Override
    public void update(ViewerCell cell)
    {
      if (cell.getElement() instanceof Project)
      {
        Project p = (Project)cell.getElement();

        StyledString styledString = new StyledString(p.getShortname(), null);
        String decoration = " (" + p.getCommitters().size() + " Committers)";
        styledString.append(decoration, StyledString.COUNTER_STYLER);
        cell.setText(styledString.getString());
        cell.setStyleRanges(styledString.getStyleRanges());
      }
      else if (cell.getElement() instanceof CommitterShip)
      {
        Person p = ((CommitterShip)cell.getElement()).getPerson();
        StyledString styledString = new StyledString(p.getLastname() + ", " + p.getFirstname(), null);
        cell.setText(styledString.getString());
        cell.setStyleRanges(styledString.getStyleRanges());
      }
    }
  }

  private static class ColumnViewerToolTipSupportImpl extends ColumnViewerToolTipSupport
  {
    private FormToolkit toolkit;

    protected ColumnViewerToolTipSupportImpl(FormToolkit toolkit, ColumnViewer viewer, int style)
    {
      super(viewer, style, false);
      this.toolkit = toolkit;
    }

    @Override
    protected Composite createViewerToolTipContentArea(Event event, ViewerCell cell, Composite parent)
    {
      Project p = (Project)cell.getElement();

      StringBuilder b = new StringBuilder();
      for (Person lead : p.getProjectleads())
      {
        if (b.length() > 0)
        {
          b.append(", ");
        }
        b.append(lead.getFirstname() + " " + lead.getLastname());
      }

      Form form = toolkit.createForm(parent);
      toolkit.decorateFormHeading(form);
      form.setText(p.getShortname() + " - " + p.getLongname());
      Composite body = form.getBody();
      body.setLayout(new GridLayout(2, false));

      toolkit.createLabel(body, "Project creation:").setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
      toolkit.createLabel(body, p.getStart() != null ? DateFormat.getDateInstance().format(p.getStart()) : "");

      toolkit.createLabel(body, "Project lead:").setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
      toolkit.createLabel(body, b.toString());

      toolkit.createLabel(body, "Committers:").setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
      toolkit.createLabel(body, p.getCommitters().size() + "");

      return form;
    }

    static void enableFor(ColumnViewer viewer, FormToolkit toolkit)
    {
      new ColumnViewerToolTipSupportImpl(toolkit, viewer, ToolTip.NO_RECREATE);
    }
  }

  private class ObservableMapCellLabelProviderImpl extends ObservableMapCellLabelProvider
  {
    private IObservableMap[] attributeMaps;
    private String messagePattern;

    public ObservableMapCellLabelProviderImpl(String messagePattern, IObservableMap... attributeMaps)
    {
      super(attributeMaps);
      this.messagePattern = messagePattern;
      this.attributeMaps = attributeMaps;
    }

    @Override
    public void update(ViewerCell cell)
    {
      Object element = cell.getElement();
      Object[] values = new Object [attributeMaps.length];
      int i = 0;
      for (IObservableMap m : attributeMaps)
      {
        values[i++] = m.get(element);
        if (values[i - 1] == null)
        {
          cell.setText("");
          return;
        }
      }
      cell.setText(MessageFormat.format(messagePattern, values)); 
    }
  }

  private class LengthConverter extends Converter
  {

    public LengthConverter()
    {
      super(Collection.class, String.class);
    }

    public Object convert(Object fromObject)
    {
      return "Committers (" + (fromObject != null ? ((Collection< ? >)fromObject).size() : "0") + ")";
    }
  }

  private class ViewerFilterImpl extends ViewerFilter
  {
    @Override
    public boolean isFilterProperty(Object element, String property)
    {
      if (property != null && property == END_DATE_PROPERTY)
      {
        return true;
      }
      return super.isFilterProperty(element, property);
    }

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element)
    {
      Date enddate = ((CommitterShip)element).getEnd();
      if (enddate == null || enddate.getTime() > Calendar.getInstance().getTimeInMillis())
      {
        return true;
      }
      return false;
    }

  }
}
