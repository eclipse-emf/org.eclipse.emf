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
package org.eclipse.emf.example.databinding.project.ui.rcp.views;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ObservablesManager;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.ui.IViewSite;

import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.example.databinding.project.ui.rcp.Activator;
import org.eclipse.emf.example.databinding.project.ui.rcp.databinding.GenericMapCellLabelProvider;
import org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage;


/**
 * Part showing the current committers
 */
public class ProjectCommittersPart
{
  private static final String END_DATE_PROPERTY = "enddate";

  /**
   * Create a new part
   * @param site the site
   * @param folder the folder where shown
   * @param ctx the databinding context
   * @param editingDomain the editing domain to make changes
   * @param manager manager for observables to avoid leaks
   * @param master the master observable
   */
  public ProjectCommittersPart(
    IViewSite site,
    CTabFolder folder,
    DataBindingContext ctx,
    EditingDomain editingDomain,
    ObservablesManager manager,
    IObservableValue master)
  {
    init(site, folder, ctx, editingDomain, master);
  }

  private void init(IViewSite site, CTabFolder folder, DataBindingContext ctx, EditingDomain editingDomain, IObservableValue master)
  {
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
      column.setLabelProvider(new GenericMapCellLabelProvider("{0}, {1}", attributeMap));
    }

    {
      IObservableMap attributeMap = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.COMMITTER_SHIP__START).observeDetail(
        cp.getKnownElements());
      TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
      column.getColumn().setText("Start");
      column.getColumn().setWidth(100);
      column.setLabelProvider(new GenericMapCellLabelProvider("{0,date,short}", attributeMap));
    }

    {
      IObservableMap attributeMap = EMFEditProperties.value(editingDomain, ProjectPackage.Literals.COMMITTER_SHIP__END).observeDetail(
        cp.getKnownElements());
      TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
      column.getColumn().setText("End");
      column.getColumn().setWidth(100);
      column.setLabelProvider(new GenericMapCellLabelProvider("{0,date,short}", attributeMap));
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

    site.registerContextMenu(Activator.PLUGIN_ID + ".committers", mgr, viewer);
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

  /**
   * Release all allocated resources
   */
  public void dispose()
  {
    // Ignore
  }
}
