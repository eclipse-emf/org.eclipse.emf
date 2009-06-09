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
 * $Id: ProjectExplorerPart.java,v 1.5 2009/06/09 07:35:17 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.views;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.databinding.ObservablesManager;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IMapChangeListener;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.map.MapChangeEvent;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.databinding.viewers.TreeStructureAdvisor;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.services.ISourceProviderService;

import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.IEMFListProperty;
import org.eclipse.emf.example.databinding.project.ui.rcp.Activator;
import org.eclipse.emf.example.databinding.project.ui.rcp.ResourceProvider;
import org.eclipse.emf.example.databinding.project.ui.rcp.databinding.Base64ToImageConverter;
import org.eclipse.emf.example.databinding.project.ui.rcp.handlers.CreateSublevelProjectHandler;
import org.eclipse.emf.example.databinding.project.ui.rcp.handlers.CreateToplevelProjectHandler;
import org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip;
import org.eclipse.emf.examples.databinding.project.core.model.project.Foundation;
import org.eclipse.emf.examples.databinding.project.core.model.project.Person;
import org.eclipse.emf.examples.databinding.project.core.model.project.Project;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage;


/**
 * Part responsible for rendering the project tree
 */
public class ProjectExplorerPart
{
  private final TreeViewer viewer;
  private FormToolkit toolkit;

  private final Image projectImage;
  private final Image committerImage;
  private final IObservableValue master = new WritableValue();
  private final IExecutionListener paramListener;
  private final IViewSite site;

  /**
   * Create a new project explorer
   * @param site the view site
   * @param parent the parent control to renderer on
   * @param toolkit the toolkit
   * @param foundation the foundation instance
   * @param manager manager for observables to avoid leaks
   */
  public ProjectExplorerPart(IViewSite site, Composite parent, FormToolkit toolkit, Foundation foundation, ObservablesManager manager)
  {
    this.toolkit = toolkit;
    this.site = site;

    ImageDescriptor desc = Activator.imageDescriptorFromPlugin("org.eclipse.ui", "icons/full/obj16/generic_elements.gif");
    if (desc != null)
    {
      projectImage = desc.createImage();
    }
    else
    {
      projectImage = null;
    }

    desc = Activator.imageDescriptorFromPlugin("org.eclipse.ui", "icons/full/obj16/signed_yes_tbl.gif");
    if (desc != null)
    {
      committerImage = desc.createImage();
    }
    else
    {
      committerImage = null;
    }

    viewer = init(parent, foundation);

    paramListener = new CommandListener();
    ICommandService service = (ICommandService)site.getService(ICommandService.class);
    service.addExecutionListener(paramListener);
  }

  private TreeViewer init(Composite parent, Foundation foundation)
  {
    TreeViewer viewer = new TreeViewer(parent);
    
    ObservableListTreeContentProvider cp = new ObservableListTreeContentProvider(new TreeFactoryImpl(), new TreeStructureAdvisorImpl());
    viewer.setContentProvider(cp);
    
    IObservableSet set = cp.getKnownElements();

    IObservableMap[] map = new IObservableMap [4];
    map[0] = EMFProperties.value(ProjectPackage.Literals.PROJECT__SHORTNAME).observeDetail(set);
    map[1] = EMFProperties.value(ProjectPackage.Literals.PROJECT__COMMITTERS).observeDetail(set);
    map[2] = EMFProperties.value(
      FeaturePath.fromList(ProjectPackage.Literals.COMMITTER_SHIP__PERSON, ProjectPackage.Literals.PERSON__FIRSTNAME)).observeDetail(set);
    map[3] = EMFProperties.value(
      FeaturePath.fromList(ProjectPackage.Literals.COMMITTER_SHIP__PERSON, ProjectPackage.Literals.PERSON__LASTNAME)).observeDetail(set);

    viewer.setLabelProvider(new TreeLabelProviderImpl(map));
    
    IEMFListProperty projects = EMFProperties.list(ProjectPackage.Literals.FOUNDATION__PROJECTS);
    viewer.setInput(projects.observe(foundation));

    MenuManager mgr = new MenuManager();
    mgr.add(new GroupMarker("additions"));
    viewer.getControl().setMenu(mgr.createContextMenu(viewer.getControl()));

    site.registerContextMenu(Activator.PLUGIN_ID + ".projectexp", mgr, viewer);
    site.setSelectionProvider(viewer);

    ColumnViewerToolTipSupportImpl.enableFor(viewer, toolkit);

    IObservableValue treeObs = ViewerProperties.singleSelection().observe(viewer);
    treeObs.addValueChangeListener(new IValueChangeListener()
      {

        public void handleValueChange(ValueChangeEvent event)
        {
          ISourceProviderService s = (ISourceProviderService)site.getService(ISourceProviderService.class);
          ResourceProvider p = (ResourceProvider)s.getSourceProvider(ResourceProvider.MODEL_RESOURCE_NAME);

          if (event.diff.getNewValue() instanceof Project)
          {
            Project pr = (Project)event.diff.getNewValue();
            master.setValue(pr);
            p.setCommitter(null);
            p.setProject(pr);
          }
          else if (event.diff.getNewValue() != null)
          {
            CommitterShip cm = (CommitterShip)event.diff.getNewValue();
            master.setValue(cm.getProject());
            p.setCommitter(cm);
            p.setProject(cm.getProject());
          }
          else
          {
            p.setCommitter(null);
            p.setProject(null);
          }
        }
      });

    return viewer;
  }

  /**
   * @return observable of the current active project in the tree
   */
  public IObservableValue getProjectObservable()
  {
    return master;
  }

  private static class TreeFactoryImpl implements IObservableFactory
  {
    private IEMFListProperty multi = EMFProperties.multiList(
      ProjectPackage.Literals.PROJECT__SUBPROJECTS,
      ProjectPackage.Literals.PROJECT__COMMITTERS);

    public IObservable createObservable(final Object target)
    {
      if (target instanceof IObservableList)
      {
        return (IObservable)target;
      }
      else if (target instanceof Project)
      {
        return multi.observe(target);
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
      if (element instanceof Project && (((Project)element).getCommitters().size() > 0 || ((Project)element).getSubprojects().size() > 0))
      {
        return Boolean.TRUE;
      }
      return super.hasChildren(element);
    }
  }

  private class TreeLabelProviderImpl extends StyledCellLabelProvider
  {

    private IMapChangeListener mapChangeListener = new IMapChangeListener()
      {
        public void handleMapChange(MapChangeEvent event)
        {
          Set< ? > affectedElements = event.diff.getChangedKeys();
          if( !affectedElements.isEmpty() ) {
            LabelProviderChangedEvent newEvent = new LabelProviderChangedEvent(TreeLabelProviderImpl.this, affectedElements.toArray());
            fireLabelProviderChanged(newEvent);            
          }
        }
      };

    public TreeLabelProviderImpl(IObservableMap... attributeMaps)
    {
      for (int i = 0; i < attributeMaps.length; i++)
      {
        attributeMaps[i].addMapChangeListener(mapChangeListener);
      }
    }

    @Override
    public String getToolTipText(Object element)
    {
      return "#dummy#";
    }

    @Override
    public void update(ViewerCell cell)
    {
      if (cell.getElement() instanceof Project)
      {
        Project p = (Project)cell.getElement();

        StyledString styledString = new StyledString(p.getShortname() != null ? p.getShortname() : "*noname*", null);
        String decoration = " (" + p.getCommitters().size() + " Committers)";
        styledString.append(decoration, StyledString.COUNTER_STYLER);
        cell.setText(styledString.getString());
        cell.setImage(projectImage);
        cell.setStyleRanges(styledString.getStyleRanges());
      }
      else if (cell.getElement() instanceof CommitterShip)
      {
        Person p = ((CommitterShip)cell.getElement()).getPerson();
        String value = "*noname*";
        if (p != null)
        {
          value = p.getLastname() + ", " + p.getFirstname();
        }
        StyledString styledString = new StyledString(value, null);
        cell.setText(styledString.getString());
        cell.setForeground(cell.getControl().getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY));
        cell.setImage(committerImage);
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
      setHideOnMouseDown(false);
      this.toolkit = toolkit;
    }

    @Override
    protected Composite createViewerToolTipContentArea(Event event, ViewerCell cell, Composite parent)
    {
      if (cell.getElement() instanceof Project)
      {
        final Project p = (Project)cell.getElement();

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

        toolkit.createLabel(body, "Mailing-List:").setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
        if (p.getDevmail() != null)
        {
          toolkit.createHyperlink(body, p.getDevmail(), SWT.NONE).addHyperlinkListener(new IHyperlinkListener()
            {

              public void linkExited(HyperlinkEvent e)
              {
              }

              public void linkEntered(HyperlinkEvent e)
              {
              }

              public void linkActivated(HyperlinkEvent e)
              {
                Program.launch(p.getDevmail());
              }
            });
        }
        else
        {
          toolkit.createLabel(body, "-");
        }

        toolkit.createLabel(body, "Homepage:").setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
        if (p.getHomepage() != null)
        {
          toolkit.createHyperlink(body, p.getHomepage(), SWT.NONE).addHyperlinkListener(new IHyperlinkListener()
            {

              public void linkExited(HyperlinkEvent e)
              {
              }

              public void linkEntered(HyperlinkEvent e)
              {
              }

              public void linkActivated(HyperlinkEvent e)
              {
                Program.launch(p.getHomepage());
              }
            });
        }
        else
        {
          toolkit.createLabel(body, "-");
        }

        return form;
      }
      else
      {
        final CommitterShip committership = (CommitterShip)cell.getElement();
        Form form = toolkit.createForm(parent);
        toolkit.decorateFormHeading(form);
        form.setText(committership.getPerson().getFirstname() + ", " + committership.getPerson().getLastname());

        Composite formBody = form.getBody();
        formBody.setLayout(new GridLayout(2, false));

        GridData gd = new GridData();

        final Label l = toolkit.createLabel(formBody, "");
        l.setLayoutData(gd);

        Base64ToImageConverter c = new Base64ToImageConverter(form.getDisplay());
        l.setImage((Image)c.convert(committership.getPerson().getImage()));
        l.addDisposeListener(new DisposeListener()
          {

            public void widgetDisposed(DisposeEvent e)
            {
              if (l.getImage() != null)
                l.getImage().dispose();
            }
          });

        Composite body = toolkit.createComposite(formBody);
        body.setLayout(new GridLayout(2, false));

        toolkit.createLabel(body, "Start:").setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
        toolkit.createLabel(body, committership.getStart() != null ? DateFormat.getDateInstance().format(committership.getStart()) : "");

        toolkit.createLabel(body, "End:").setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
        toolkit.createLabel(body, committership.getEnd() != null ? DateFormat.getDateInstance().format(committership.getEnd()) : "");

        toolkit.createLabel(body, "E-Mail:").setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
        if (committership.getPerson().getEmail() != null)
        {
          toolkit.createHyperlink(body, committership.getPerson().getEmail(), SWT.NONE).addHyperlinkListener(new IHyperlinkListener()
            {

              public void linkExited(HyperlinkEvent e)
              {
              }

              public void linkEntered(HyperlinkEvent e)
              {
              }

              public void linkActivated(HyperlinkEvent e)
              {
                Program.launch(committership.getPerson().getEmail());
              }
            });
        }
        else
        {
          toolkit.createLabel(body, "");
        }

        return form;
      }
    }

    static void enableFor(ColumnViewer viewer, FormToolkit toolkit)
    {
      new ColumnViewerToolTipSupportImpl(toolkit, viewer, ToolTip.NO_RECREATE);
    }
  }

  /**
   * Set the focus
   */
  public void setFocus()
  {
    viewer.getControl().setFocus();
    if (viewer.getTree().getItemCount() > 0)
    {
      viewer.setSelection(new StructuredSelection(viewer.getTree().getItem(0).getData()));
    }
  }

  /**
   * Dispose all resources and remove all listeners
   */
  public void dispose()
  {
    ICommandService service = (ICommandService)site.getService(ICommandService.class);
    service.removeExecutionListener(paramListener);

    if (projectImage != null)
    {
      projectImage.dispose();
    }

    if (committerImage != null)
    {
      committerImage.dispose();
    }
  }

  /**
   * @return the current selected committer
   */
  public CommitterShip getCommitter()
  {
    Object obj = ((IStructuredSelection)viewer.getSelection()).getFirstElement();
    return (CommitterShip)((obj instanceof CommitterShip) ? obj : null);
  }

  private class CommandListener implements IExecutionListener
  {

    public void preExecute(String commandId, ExecutionEvent event)
    {
    }

    public void postExecuteSuccess(String commandId, Object returnValue)
    {
      if (site.getPage() != null && site.getPage().getActivePart() != null && site.getPage().getActivePart().getSite() == site)
      {
        if (commandId.equals(CreateToplevelProjectHandler.ID) && returnValue != null)
        {
          viewer.setSelection(new StructuredSelection(returnValue));
        }
        else if (commandId.equals(CreateSublevelProjectHandler.ID) && returnValue != null)
        {
          Project project = (Project)returnValue;
          List<Project> path = new ArrayList<Project>();
          path.add(project);
          while ((project = project.getParent()) != null)
          {
            path.add(0, project);
          }

          viewer.expandToLevel(new TreePath(path.toArray()), 0);
          viewer.setSelection(new TreeSelection(new TreePath(path.toArray())));
        }
      }
    }

    public void postExecuteFailure(String commandId, ExecutionException exception)
    {
    }

    public void notHandled(String commandId, NotHandledException exception)
    {
    }
  }
}
