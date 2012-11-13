/**
 * Copyright (c) 2005-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.ui.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.provider.DiagnosticDecorator;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetEntry;
import org.eclipse.ui.views.properties.PropertySheetPage;

/**
 * This property sheet page has an additional button in its local toolbar that locates the value of the
 * selected property in the editor.
 * It also supports {@link #ExtendedPropertySheetPage(AdapterFactoryEditingDomain, Decoration) decorating} the property values to indicate bad values.
 */
public class ExtendedPropertySheetPage extends PropertySheetPage
{
  /**
   * @since 2.9
   */
  public static enum Decoration
  {
    NONE, MANUAL, LIVE
  }

  protected List<Object> objectsToSelect = new ArrayList<Object>();

  protected AdapterFactoryEditingDomain editingDomain;

  /**
   * @since 2.9
   */
  protected DiagnosticDecorator diagnosticDecorator;

  /**
   * @since 2.9
   */
  protected List<?> input = Collections.emptyList();

  /**
   * @since 2.9
   */
  protected IPropertySourceProvider propertySourceProvider;

  protected IAction locateValueAction = new LocateValueAction();

  protected class LocateValueAction extends Action
  {
    public LocateValueAction()
    {
      setText(EMFEditUIPlugin.INSTANCE.getString("_UI_LocateValue_action"));
      setToolTipText(EMFEditUIPlugin.INSTANCE.getString("_UI_LocateValue_action_tool_tip"));
      setImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(EMFEditUIPlugin.INSTANCE.getImage("full/elcl16/LocateValue")));
      setDisabledImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(EMFEditUIPlugin.INSTANCE.getImage("full/dlcl16/LocateValue")));
    }

    @Override
    public void run()
    {
      List<Object> selection = new ArrayList<Object>();
      for (Object object : objectsToSelect)
      {
        selection.add(editingDomain.getWrapper(object));
      }
      setSelectionToViewer(selection);
    }
  }

  public ExtendedPropertySheetPage(AdapterFactoryEditingDomain editingDomain)
  {
    super();
    this.editingDomain = editingDomain;
  }

  /**
   * @since 2.9
   */
  public ExtendedPropertySheetPage(AdapterFactoryEditingDomain editingDomain, Decoration decoration)
  {
    this(editingDomain);
    diagnosticDecorator =  createDiagnosticDecorator(decoration);
  }

  /**
   * @since 2.9
   */
  protected DiagnosticDecorator createDiagnosticDecorator(Decoration decoration)
  {
    switch (decoration)
    {
      case MANUAL:
      {
        return new DiagnosticDecorator(editingDomain.getResourceSet(), this);
      }
      case LIVE:
      {
        return new DiagnosticDecorator(editingDomain, this);
      }
      default:
      {
        return null;
      }
    }
  }

  @Override
  public void setPropertySourceProvider(IPropertySourceProvider newProvider)
  {
    propertySourceProvider = newProvider;
    super.setPropertySourceProvider(newProvider);
  }

  @Override
  public void createControl(Composite parent)
  {
    super.createControl(parent);
    if (diagnosticDecorator != null)
    {
      class DecoratingPropertySheetEntry extends PropertySheetEntry
      {
        @Override
        protected PropertySheetEntry createChildEntry()
        {
          return new DecoratingPropertySheetEntry();
        }
    
        @Override
        public Image getImage()
        {
          Image image = super.getImage();
          if (image == null)
          {
            image = ExtendedImageRegistry.INSTANCE.getImage(ItemPropertyDescriptor.GENERIC_VALUE_IMAGE);
          }
          Diagnostic featureDiagnostic = findDiagnostic();
          return featureDiagnostic != null ? diagnosticDecorator.decorate(image, featureDiagnostic) : image;
        }
        
        protected Diagnostic findDiagnostic()
        {
          IPropertyDescriptor descriptor = getDescriptor();
          if (descriptor instanceof PropertyDescriptor)
          {
            Object feature = ((PropertyDescriptor)descriptor).getFeature();
            Map<Object, ? extends Diagnostic> decorations = diagnosticDecorator.getDecorations();
            if (!decorations.isEmpty() && feature != null)
            {
              for (Diagnostic diagnostic : decorations.values())
              {
                Diagnostic featureDiagnostic = find(diagnostic, feature);
                if (featureDiagnostic != null)
                {
                  return featureDiagnostic;
                }
              }
            }
          }
          return null;
        }

        protected Diagnostic find(Diagnostic diagnostic, Object feature)
        {
          // Gather them all...
          //
          if (diagnostic.getData().contains(feature))
          {
            return diagnostic;
          }
          for (Diagnostic child : diagnostic.getChildren())
          {
            Diagnostic result = find(child, feature);
            if (result != null)
            {
              return result;
            }
          }
          return null;
        }

        @Override
        public String getDescription()
        {
          String description = super.getDescription();
          Diagnostic featureDiagnostic = findDiagnostic();
          if (featureDiagnostic != null)
          {
            return description + " - " + DiagnosticDecorator.strip(featureDiagnostic.getMessage());
          }
          else
          {
            return description;
          }
        }
      }

      DecoratingPropertySheetEntry decoratingPropertySheetEntry = new DecoratingPropertySheetEntry();
      decoratingPropertySheetEntry.setPropertySourceProvider(propertySourceProvider);
      setRootEntry(decoratingPropertySheetEntry);
    }
  }

  /**
   * This method should be overridden to set the selection.
   */
  protected void setSelectionToViewer(List<?> selection)
  {
    // Do nothing.
  }

  @Override
  public void makeContributions(IMenuManager menuManager, IToolBarManager toolBarManager, IStatusLineManager statusLineManager)
  {
    super.makeContributions(menuManager, toolBarManager, statusLineManager);
    toolBarManager.add(locateValueAction);
  }

  @Override
  public void handleEntrySelection(ISelection selection)
  {
    super.handleEntrySelection(selection);
    objectsToSelect.clear();
    if (!selection.isEmpty() && selection instanceof IStructuredSelection)
    {
      IStructuredSelection structuredSelection = (IStructuredSelection)selection;
      if (structuredSelection.size() == 1)
      {
        Object object = structuredSelection.getFirstElement();
        if (object instanceof PropertySheetEntry)
        {
          PropertySheetEntry entry = (PropertySheetEntry)object;
          Object [] values = entry.getValues();
          if (values != null)
          {
            for (int i = 0; i < values.length; ++i)
            {
              Object value = values[i];
              if (value instanceof IItemPropertySource)
              {
                Object realValue = ((IItemPropertySource)value).getEditableValue(null);
                if (realValue instanceof Collection<?>)
                {
                  for (Object o : (Collection<?>)realValue)
                  {
                    addObjectToSelect(o);
                  }
                }
                else
                {
                  addObjectToSelect(realValue);
                }
              }
            }
          }
        }
      }
    }
    locateValueAction.setEnabled(!objectsToSelect.isEmpty());
  }

  protected void addObjectToSelect(Object object)
  {
    if (object instanceof EObject)
    {
      objectsToSelect.add(object);
    }
  }

  @Override
  public void selectionChanged(IWorkbenchPart part, ISelection selection)
  {
    if (selection instanceof IStructuredSelection)
    {
      input = ((IStructuredSelection)selection).toList();
    }
    else
    {
      input = Collections.emptyList();
    }
    super.selectionChanged(part, selection);
  }

  /**
   * Provides access to the current input to the page.
   * 
   * @since 2.9
   */
  public List<?> getInput()
  {
    return input;
  }

  @Override
  public void dispose()
  {
    if (diagnosticDecorator != null)
    {
      diagnosticDecorator.dispose();
    }
    super.dispose();
  }
}
