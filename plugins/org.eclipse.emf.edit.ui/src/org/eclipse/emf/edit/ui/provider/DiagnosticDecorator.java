/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.edit.ui.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.ui.ImageURIRegistry;
import org.eclipse.emf.common.ui.MarkerHelper;
import org.eclipse.emf.common.ui.viewer.ColumnViewerInformationControlToolTipSupport;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.emf.edit.ui.util.EditUIMarkerHelper;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * A {@link ILabelDecorator label decorator} for associating {@link Diagnostic diagnostic} decorations with a {@link StructuredViewer structured viewer}'s content labels.
 *
 * @since 2.9
 */
public class DiagnosticDecorator extends CellLabelProvider implements ILabelDecorator
{
  /**
   * Converts special characters to entities except those in {@link #enquote(String) enquoted ranges}.
   */
  public static String escapeContent(String content)
  {
    StringBuilder result = new StringBuilder();
    boolean escape = true;
    for (int i = 0, length = content.length(); i < length; ++i)
    {
      char character = content.charAt(i);
      if (escape)
      {
        switch (character)
        {
          case '\002':
          {
            escape = false;
            break;
          }
          case '\003':
          {
            escape = true;
            break;
          }
          case '&':
          {
            result.append("&amp;");
            break;
          }
          case '<':
          {
            result.append("&lt;");
            break;
          }
          default:
          {
            result.append(character);
            break;
          }
        }
      }
      else
      {
        result.append(character);
      }
    }
    return result.toString();
  }

  /**
   * Marks the string such that the content will not be subsequently {@link #escapeContent(String) escaped}.
   */
  public static String enquote(String content)
  {
    return '\002' + content + '\003';
  }

  private static final Pattern ENQUOTED_SEGMENT_PATTERN = Pattern.compile("\002<img src='[^']*'/> (?:<i>)?([^\003]*)(?:</i>)?\003");

  /**
   * Cleans up the escaping and HTML tags inserted by the {@link LiveValidator live validator}.
   * It can be safely called on text not produces by the live validator because it only transformed {@link #enquote(String) enquoted} content.
   */
  public static String strip(String content)
  {
    Matcher matcher = ENQUOTED_SEGMENT_PATTERN.matcher(content);
    if (matcher.find())
    {
      StringBuilder result = new StringBuilder();
      int start = 0;
      do
      {
        result.append(content.substring(start, matcher.start()));
        String label = matcher.group(1);
        result.append(label.replace("&lt;", "<").replace("&amp;", "&"));
        start = matcher.end();
      }
      while (matcher.find());
      result.append(content.substring(start));
      return result.toString();
    }
    else
    {
      return content;
    }
  }

  /**
   * A content adapter for monitoring a {@link ResourceSet resource set}'s resources.
   */
  public static abstract class DiagnosticAdapter extends EContentAdapter
  {
    /**
     * Inform all diagnostic adapters associated with the notifier of new diagnostic information.
     */
    public static void update(Notifier notifier, Diagnostic diagnostic)
    {
      for (Adapter adapter : notifier.eAdapters())
      {
        if (adapter instanceof DiagnosticAdapter)
        {
          ((DiagnosticAdapter)adapter).updateDiagnostic(diagnostic);
        }
      }
    }

    protected abstract void updateDiagnostic(Diagnostic diagnostic);

    @Override
    public void notifyChanged(Notification notification)
    {
      if (notification.getNotifier() instanceof Resource)
      {
        switch (notification.getFeatureID(Resource.class))
        {
          case Resource.RESOURCE__IS_LOADED:
          case Resource.RESOURCE__ERRORS:
          case Resource.RESOURCE__WARNINGS:
          {
            final Resource resource = (Resource)notification.getNotifier();
            Display.getDefault().asyncExec
              (new Runnable()
               {
                 public void run()
                 {
                   handleResourceDiagnostics(resource);
                 }
               });
            break;
          }
        }
      }
      else
      {
        super.notifyChanged(notification);
      }
    }

    protected abstract void handleResourceDiagnostics(Resource resource);

    @Override
    protected void setTarget(ResourceSet target)
    {
      super.setTarget(target);
      for (Resource resource : target.getResources())
      {
        handleResourceDiagnostics(resource);
      }
    }

    @Override
    protected void setTarget(Resource target)
    {
      basicSetTarget(target);
    }

    @Override
    protected void unsetTarget(Resource target)
    {
      basicUnsetTarget(target);
    }
  }

  /**
   * A listener that interprets links as navigable references to objects in the resource set.
   */
  public static class EditingDomainLocationListener extends ColumnViewerInformationControlToolTipSupport.PathLocationListener
  {
    protected EditingDomain editingDomain;

    public EditingDomainLocationListener(EditingDomain editingDomain, StructuredViewer viewer)
    {
      super(viewer);
      this.editingDomain = editingDomain;
    }

    @Override
    public void changing(LocationEvent event)
    {
      EObject eObject = null;
      try
      {
        URI uri = URI.createURI(event.location);
        if (uri.hasFragment())
        {
          eObject = editingDomain.getResourceSet().getEObject(uri, false);
        }
      }
      catch (Throwable throwable)
      {
        // Ignore when we can't find the object.
      }
      if (eObject != null)
      {
        event.doit = false;
        setSelection(eObject);
      }
      else
      {
        super.changing(event);
      }
    }
  }

  public static class LiveValidator
  {
    private final List<DiagnosticDecorator> diagnosticDecorators = new ArrayList<DiagnosticDecorator>();

    protected EditingDomain editingDomain;
    protected AdapterFactory adapterFactory;
    protected ILabelProvider labelProvider;
    protected Job validationJob;
    protected List<Resource> scheduledResources = Collections.synchronizedList(new UniqueEList<Resource>());

    public LiveValidator(final EditingDomain editingDomain)
    {
      this.editingDomain = editingDomain;
      adapterFactory = editingDomain instanceof AdapterFactoryEditingDomain ? ((AdapterFactoryEditingDomain)editingDomain).getAdapterFactory() : null;
      labelProvider = adapterFactory == null ? null : new AdapterFactoryLabelProvider(adapterFactory);

      editingDomain.getCommandStack().addCommandStackListener
        (new CommandStackListener()
         {
           public void commandStackChanged(EventObject event)
           {
             Command mostRecentCommand = ((CommandStack)event.getSource()).getMostRecentCommand();
             if (!(mostRecentCommand instanceof AbstractCommand.NonDirtying))
             {
               scheduledResources.addAll(editingDomain.getResourceSet().getResources());
               schedule();
             }
           }
         });
    }

    public void schedule(Resource resource)
    {
      scheduledResources.add(resource);
      schedule();
    }

    public void schedule()
    {
      if (validationJob == null)
      {
        validationJob =
          new Job("Validation Job")
          {
            @Override
            protected IStatus run(final IProgressMonitor monitor)
            {
              Diagnostician diagnostician =
                new Diagnostician()
                {
                  @Override
                  public String getObjectLabel(EObject eObject)
                  {
                    String text = labelProvider != null && eObject.eIsProxy() ? ((InternalEObject)eObject).eProxyURI().toString() : labelProvider.getText(eObject);
                    if (text == null || text.length() == 0)
                    {
                      text = "<i>null</i>";
                    }
                    else
                    {
                      text = escapeContent(text);
                    }
                    Image image = labelProvider != null ? labelProvider.getImage(eObject) : null;
                    if (image != null)
                    {
                      URI imageURI = ImageURIRegistry.INSTANCE.getImageURI(image);
                      return enquote("<img src='" + imageURI + "'/> " + text);
                    }
                    else
                    {
                      return escapeContent(text);
                    }
                  }

                  @Override
                  public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context)
                  {
                    monitor.worked(1);
                    return super.validate(eClass, eObject, diagnostics, context);
                  }
                };

              List<Resource> resources = Arrays.asList(scheduledResources.toArray(new Resource[0]));
              scheduledResources.removeAll(resources);

              int count = 0;
              for (Iterator<?> i = EcoreUtil.getAllContents(resources); i.hasNext(); i.next())
              {
                ++count;
              }

              final ResourceSet resourceSet = editingDomain.getResourceSet();

              monitor.beginTask("", count);
              final BasicDiagnostic diagnostic =
                new BasicDiagnostic
                  (EObjectValidator.DIAGNOSTIC_SOURCE,
                   0,
                   EMFEditUIPlugin.INSTANCE.getString("_UI_DiagnosisOfNObjects_message", new String[] { "" + resources.size() }),
                   new Object [] { resourceSet } );
              Map<Object, Object> context = diagnostician.createDefaultContext();
              for (int i = 0; i < resources.size(); ++i)
              {
                Resource resource = resources.get(i);
                monitor.setTaskName(EMFEditUIPlugin.INSTANCE.getString("_UI_Validating_message", new Object [] { resource.getURI() }));

                final BasicDiagnostic resourceDiagnostic =
                  new BasicDiagnostic
                    (EObjectValidator.DIAGNOSTIC_SOURCE,
                     0,
                     EMFEditUIPlugin.INSTANCE.getString("_UI_DiagnosisOfNObjects_message", new String[] { "1" }),
                     new Object [] { resource } );

                for (EObject eObject : resource.getContents())
                {
                  diagnostician.validate(eObject, resourceDiagnostic, context);
                }

                for (Resource.Diagnostic warning : resource.getWarnings())
                {
                  resourceDiagnostic.add(new BasicDiagnostic(Diagnostic.WARNING, null, 0, warning.getMessage(),  new Object [] { resource }));
                }

                for (Resource.Diagnostic error : resource.getErrors())
                {
                  resourceDiagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, null, 0, error.getMessage(),  new Object[] { resource}));
                }

                diagnostic.add(resourceDiagnostic);

                if (monitor.isCanceled())
                {
                  validationJob = null;
                  return Status.CANCEL_STATUS;
                }
                monitor.worked(1);
              }

              Display.getDefault().asyncExec
                (new Runnable()
                 {
                   public void run()
                   {
                     DiagnosticAdapter.update(resourceSet, diagnostic);
                     validationJob = null;
                     if (!scheduledResources.isEmpty())
                     {
                       LiveValidator.this.schedule();
                     }
                   }
                 });

              return Status.OK_STATUS;
            }
          };
        validationJob.schedule();
      }
    }

    public void register(DiagnosticDecorator diagnosticDecorator)
    {
      diagnosticDecorators.add(diagnosticDecorator);
    }

    public void deregister(DiagnosticDecorator diagnosticDecorator)
    {
      if (diagnosticDecorators.remove(diagnosticDecorator) && diagnosticDecorators.isEmpty())
      {
        dispose();
      }
    }

    public void dispose()
    {
      LIVE_VALIDATORS.remove(editingDomain);
      if (validationJob != null)
      {
        validationJob.cancel();
      }
    }
  }

  private class DiagnosticDecoratorAdapter extends DiagnosticAdapter
  {
    @Override
    public void updateDiagnostic(Diagnostic diagnostic)
    {
      handleDiagnostic(diagnostic);
    }

    @Override
    protected void handleResourceDiagnostics(Resource resource)
    {
      handleDiagnostic(markerHelper.getMarkerDiagnostics(resource, null));
    }
  }

  private static final Map<EditingDomain, LiveValidator> LIVE_VALIDATORS = new HashMap<EditingDomain, LiveValidator>();

  protected DiagnosticAdapter diagnosticAdapter;
  protected EditingDomain editingDomain;
  protected LiveValidator liveValidator;
  protected ResourceSet resourceSet;
  protected StructuredViewer viewer;
  protected ExtendedPropertySheetPage propertySheetPage;
  protected Map<Object, BasicDiagnostic> decorations = new HashMap<Object, BasicDiagnostic>();
  protected MarkerHelper markerHelper = new EditUIMarkerHelper();
  protected Object input;
  protected IContentProvider contentProvider;
  protected Set<Diagnostic> diagnostics = new HashSet<Diagnostic>();
  protected boolean isDecorating = true;

  /**
   * Creates an instance that doesn't support {@link LiveValidator live validation}.
   * Only decorations explicitly produced from {@link ValidateAction} or those {@link EditUIMarkerHelper#getMarkerDiagnostics(Object, org.eclipse.core.resources.IFile) derived from markers} are displayed.
   */
  public DiagnosticDecorator(ResourceSet resourceSet, StructuredViewer viewer)
  {
    this.viewer = viewer;
    this.resourceSet = resourceSet;

    diagnosticAdapter = new DiagnosticDecoratorAdapter();
    resourceSet.eAdapters().add(diagnosticAdapter);

    input = viewer.getInput();
    contentProvider = viewer.getContentProvider();
  }

  /**
   * Creates an instance that supports {@link LiveValidator live validation}.
   */
  public DiagnosticDecorator(EditingDomain editingDomain, StructuredViewer viewer)
  {
    this.editingDomain = editingDomain;
    this.viewer = viewer;
    this.resourceSet = editingDomain.getResourceSet();

    diagnosticAdapter = new DiagnosticDecoratorAdapter();
    resourceSet.eAdapters().add(diagnosticAdapter);

    input = viewer.getInput();
    contentProvider = viewer.getContentProvider();
  }

  /**
   * Creates an instance that doesn't support {@link LiveValidator live validation}.
   * Only decorations explicitly produced from {@link ValidateAction} or those {@link EditUIMarkerHelper#getMarkerDiagnostics(Object, org.eclipse.core.resources.IFile) derived from markers} are displayed.
   */
  public DiagnosticDecorator(ResourceSet resourceSet, ExtendedPropertySheetPage propertySheetPage)
  {
    this.resourceSet = resourceSet;
    this.propertySheetPage = propertySheetPage;

    diagnosticAdapter = new DiagnosticDecoratorAdapter();
    resourceSet.eAdapters().add(diagnosticAdapter);

    input = propertySheetPage.getInput();
  }

  /**
   * Creates an instance that supports {@link LiveValidator live validation}.
   */
  public DiagnosticDecorator(EditingDomain editingDomain, ExtendedPropertySheetPage propertySheetPage)
  {
    this.editingDomain = editingDomain;
    this.resourceSet = editingDomain.getResourceSet();
    this.propertySheetPage = propertySheetPage;

    diagnosticAdapter = new DiagnosticDecoratorAdapter();
    resourceSet.eAdapters().add(diagnosticAdapter);

    input = propertySheetPage.getInput();
  }

  protected LiveValidator getLiveValidator()
  {
    if (liveValidator == null && editingDomain != null)
    {
      liveValidator = LIVE_VALIDATORS.get(editingDomain);
      if (liveValidator == null)
      {
        liveValidator = new LiveValidator(editingDomain);
        LIVE_VALIDATORS.put(editingDomain, liveValidator);
      }
      liveValidator.register(this);
    }
    return liveValidator;
  }

  public String decorateText(String text, Object object)
  {
    return text;
  }

  public Image decorateImage(Image image, Object object)
  {
    if (isDecorating)
    {
      Diagnostic diagnostic = getDecorations().get(object);
      if (diagnostic != null && diagnostic.getSeverity() >= Diagnostic.WARNING)
      {
        return decorate(image, diagnostic);
      }
    }
    return image;
  }

  public Image decorate(Image image, Diagnostic diagnostic)
  {
    if (image == null)
    {
      return ExtendedImageRegistry.INSTANCE.getImage(EMFEditUIPlugin.INSTANCE.getImage(diagnostic.getSeverity() == Diagnostic.WARNING ? "full/ovr16/warning_ovr.gif" : "full/ovr16/error_ovr.gif"));
    }
    else
    {
      List<Object> images = new ArrayList<Object>(2);
      images.add(image);
      images.add(EMFEditUIPlugin.INSTANCE.getImage(diagnostic.getSeverity() == Diagnostic.WARNING ? "full/ovr16/warning_ovr.gif" : "full/ovr16/error_ovr.gif"));
      ComposedImage composedImage =
        new ComposedImage(images)
        {
          @Override
          public List<Point> getDrawPoints(Size size)
          {
            List<Point> result = new ArrayList<Point>();
            result.add(new Point());
            Point overlay = new Point();
            overlay.y = 7;
            result.add(overlay);
            return result;
          }
        };
      return ExtendedImageRegistry.INSTANCE.getImage(composedImage);
    }
  }

  public Map<Object, ? extends Diagnostic> getDecorations()
  {
    // Check that the current decorations are up-to-date with respect to the viewer input and content provider.
    //
    if (propertySheetPage != null)
    {
      List<?> input = propertySheetPage.getInput();
      if (!input.equals(this.input))
      {
        redecorate();
      }
      this.input = input;
    }
    else
    {
      Object input = viewer.getInput();
      IContentProvider contentProvider = viewer.getContentProvider();
      if (input != null && !input.equals(this.input) || contentProvider != null && !contentProvider.equals(this.contentProvider))
      {
        redecorate();
      }
      this.input = input;
      this.contentProvider = contentProvider;
    }

    return decorations;
  }

  protected void updateDiagnotics(Diagnostic diagnostic)
  {
    String markerSource = markerHelper.getDiagnosticSource();
    String source = diagnostic.getSource();
    if (markerSource.equals(source))
    {
      // Diagnostics produced from markers are expected to have a root diagnostic with the resource as the data,
      // so we clean up old diagnostics that the same source and for that resource.
      //
      for (Iterator<Diagnostic> i = diagnostics.iterator(); i.hasNext(); )
      {
        Diagnostic oldDiagnostic = i.next();
        if (markerSource.equals(oldDiagnostic.getSource()) && diagnostic.getData().equals(oldDiagnostic.getData()))
        {
          i.remove();
        }
      }
      diagnostics.add(diagnostic);

      // Schedule live validation of the resource to produce more detailed information than what was recorded in the marker.
      //
      LiveValidator liveValidator = getLiveValidator();
      if (liveValidator != null)
      {
        liveValidator.schedule((Resource)diagnostic.getData().get(0));
      }
    }
    else
    {
      // If the root diagnostic is for a resource set, we expect it to hold per-resource children.
      //
      Object object = diagnostic.getData().get(0);
      if (object instanceof ResourceSet)
      {
        for (Diagnostic child : diagnostic.getChildren())
        {
          updateDiagnotics(child);
        }
      }
      else
      {
        // Generally ehse will be per resource diagnostics.
        // We clean up old diagnostics with a marker source or the same source and with the same data.
        //
        for (Iterator<Diagnostic> i = diagnostics.iterator(); i.hasNext(); )
        {
          Diagnostic oldDiagnostic = i.next();
          String oldSource = oldDiagnostic.getSource();
          if ((source.equals(oldSource) || markerSource.equals(oldSource)) && diagnostic.getData().equals(oldDiagnostic.getData()))
          {
            i.remove();
          }
        }
        diagnostics.add(diagnostic);
      }
    }
  }

  protected void handleDiagnostic(Diagnostic rootDiagnostic)
  {
    // Update the decorative diagnostics.
    //
    updateDiagnotics(rootDiagnostic);

    // Redecorate with the current diagnostics.
    //
    redecorate();
  }

  protected void redecorate()
  {
    // If the viewer has input and is ready to receive decorations...
    //
    if (propertySheetPage != null ?  propertySheetPage.getInput() != null : viewer.getInput() != null && viewer.getContentProvider() != null)
    {
      // Build up the map for root decorations.
      //
      Map<Object, BasicDiagnostic> objects = new HashMap<Object, BasicDiagnostic>();
      for (Diagnostic diagnostic : diagnostics)
      {
        for (Diagnostic child : diagnostic.getChildren())
        {
          List<?> data = child.getData();
          if (!data.isEmpty())
          {
            decorate(objects, data.get(0), child, null);
          }
        }
      }

      // Keep track of the old decorations and start building fresh new ones.
      //
      Map<Object, BasicDiagnostic> oldDecorations = decorations;
      decorations = new HashMap<Object, BasicDiagnostic>();

      // Decorate based on the root decorations.
      //
      decorate(objects);

      if (propertySheetPage != null)
      {
        // If this is for the property sheet and there are decorations that need cleaning or there are new decorations to show, refresh the view.
        //
        if (!decorations.isEmpty() || !oldDecorations.isEmpty())
        {
          final Control control = propertySheetPage.getControl();
          control.getDisplay().asyncExec
            (new Runnable()
             {
               public void run()
               {
                 if (!control.isDisposed())
                 {
                   propertySheetPage.refresh();
                 }
               }
             });
        }
      }
      else
      {
        // Compute the objects that need to be refreshed.
        //
        final Set<Object> objectsToRefresh = new HashSet<Object>();
        for (Map.Entry<Object, BasicDiagnostic> entry : decorations.entrySet())
        {
          Object decoratedObject = entry.getKey();
          BasicDiagnostic oldDiagnostic = oldDecorations.get(decoratedObject);
          if (oldDiagnostic == null || entry.getValue().getSeverity() != oldDiagnostic.getSeverity())
          {
            // Each object that's newly decorated or has a different decoration needs to be refreshed.
            //
            objectsToRefresh.add(decoratedObject);
          }

          // Forget about this old decoration; either the object is already added for refreshing or it doesn't need refreshing because the severity is the same.
          //
          if (oldDiagnostic != null)
          {
            oldDecorations.remove(decoratedObject);
          }
        }

        // Also refresh all the object with old decorations that no longer have decorations.
        //
        objectsToRefresh.addAll(oldDecorations.keySet());

        if (!objectsToRefresh.isEmpty())
        {
          // Be careful to refresh asynchronously if the viewer is busy.
          //
          if (!(viewer instanceof ColumnViewer) || !((ColumnViewer)viewer).isBusy())
          {
            viewer.update(objectsToRefresh.toArray(), null);
          }
          else
          {
            final Control control = viewer.getControl();
            control.getDisplay().asyncExec
              (new Runnable()
               {
                 public void run()
                 {
                   if (!control.isDisposed())
                   {
                     viewer.update(objectsToRefresh.toArray(), null);
                   }
                 }
               });
          }
        }
      }
    }
  }

  protected BasicDiagnostic decorate(Map<Object, BasicDiagnostic> decorations, Object object, Diagnostic diagnostic, List<Integer> path)
  {
    BasicDiagnostic oldDiagnostic = decorations.get(object);
    if (diagnostic != null)
    {
      if (oldDiagnostic == null)
      {
        oldDiagnostic = new BasicDiagnostic(null, 0, null, path == null ? new Object [] { object } : new Object [] { object, path.toArray(new Integer[path.size()]) });
        decorations.put(object, oldDiagnostic);
      }
      oldDiagnostic.add(diagnostic);
    }
    return oldDiagnostic;
  }

  protected void decorate(Map<Object, BasicDiagnostic> objects)
  {
    if (propertySheetPage != null)
    {
      for (Object object : propertySheetPage.getInput())
      {
        decorate(decorations, object, objects.get(object), null);
      }
    }
    else
    {
      Object input = viewer.getInput();
      IContentProvider contentProvider = viewer.getContentProvider();
      if (contentProvider instanceof IStructuredContentProvider)
      {
        ITreeContentProvider treeContentProvider = contentProvider instanceof ITreeContentProvider ? (ITreeContentProvider)contentProvider : null;

        int index = 0;
        List<Integer> path = new ArrayList<Integer>();
        path.add(-1);
        for (Object object : ((IStructuredContentProvider)contentProvider).getElements(input))
        {
          path.set(0, index++);
          Diagnostic objectDiagnostic = decorate(decorations, object, objects.get(AdapterFactoryEditingDomain.unwrap(object)), path);
          if (treeContentProvider != null)
          {
            Set<Object> visited = new HashSet<Object>();
            objectDiagnostic = decorate(objects, treeContentProvider, visited, object, path);
          }
          decorate(decorations, input, objectDiagnostic, null);
        }
      }
    }
  }

  protected BasicDiagnostic decorate(Map<Object, BasicDiagnostic> objects, ITreeContentProvider treeContentProvider, Set<Object> visited, Object object, List<Integer> path)
  {
    BasicDiagnostic result = decorations.get(object);
    if (visited.add(object))
    {
      int index = 0;
      int last = path.size();
      for (Object child : treeContentProvider.getChildren(object))
      {
        path.add(index++);
        BasicDiagnostic childResult = decorate(decorations, child, objects.get(AdapterFactoryEditingDomain.unwrap(child)), path);
        childResult = decorate(objects, treeContentProvider, visited, child, path);
        path.remove(last);
        result = decorate(decorations, object, childResult, path);
      }
    }
    return result;
  }

  @Override
  public String getToolTipText(Object object)
  {
    BasicDiagnostic diagnostic = decorations.get(object);
    if (diagnostic != null)
    {
      ILabelProvider labelProvider = (ILabelProvider)viewer.getLabelProvider();
      StringBuilder result = new StringBuilder();
      isDecorating = false;
      buildToolTipText(result, labelProvider, diagnostic, object);
      isDecorating = true;
      return result.length() == 0 ? null : result.toString();
    }
    else
    {
      return null;
    }
  }

  protected void buildToolTipText(StringBuilder result, ILabelProvider labelProvider, Diagnostic diagnostic, Object object)
  {
    List<Diagnostic> children = diagnostic.getChildren();
    Diagnostic child = children.get(0);
    int index = 0;
    if (child.getData().contains(object))
    {
      ++index;
      for (Diagnostic grandChild : child.getChildren())
      {
        buildToolTipMessage(result, labelProvider, object, grandChild, 0);
      }
    }

    StringBuilder moreResults = new StringBuilder();
    for (int size = children.size(); index < size; ++index)
    {
      child = children.get(index);
      buildMoreToolTipText(moreResults, labelProvider, child);
    }
    if (moreResults.length() != 0)
    {
      result.append("<h1>Problems on Children</h1>\n");
      result.append(moreResults);
    }
  }

  protected void buildToolTipMessage(StringBuilder result, ILabelProvider labelProvider, Object object, Diagnostic diagnostic, int indentation)
  {
    String message = diagnostic.getMessage();
    ImageDescriptor imageDescriptor =
      ExtendedImageRegistry.INSTANCE.getImageDescriptor
        (EMFEditUIPlugin.INSTANCE.getImage(diagnostic.getSeverity() == Diagnostic.WARNING ? "full/ovr16/warning_ovr.gif" : "full/ovr16/error_ovr.gif"));
    URI severityURI = ImageURIRegistry.INSTANCE.getImageURI(imageDescriptor);
    result.append("<div>");
    for (int i = 0; i < indentation; ++i)
    {
      result.append("&#160;&#160;&#160;");
    }
    result.append("<img src='");
    result.append(severityURI);
    result.append("'/> ");
    result.append(escapeContent(message));
    result.append("</div>\n");
    List<?> excludedObjects = object instanceof EObject ? ((EObject)object).eClass().getEAllStructuralFeatures() : Collections.emptyList();
    for (Object data : diagnostic.getData())
    {
      if (data != object && !excludedObjects.contains(data) && data instanceof EObject)
      {
        EObject eObject = (EObject)data;
        if (eObject.eResource() != null && eObject.eResource().getResourceSet() == resourceSet)
        {
          result.append("<div>");
          for (int i = 0; i <= indentation; ++i)
          {
            result.append("&#160;&#160;&#160;");
          }
          String text = escapeContent(labelProvider.getText(data));
          Image image = labelProvider.getImage(data);
          result.append("<img src='");
          result.append(ImageURIRegistry.INSTANCE.getImageURI(image));
          result.append("'/> <a href='");
          result.append(EcoreUtil.getURI((EObject)data));
          result.append("'>");
          result.append(text);
          result.append("</a></div>\n");
        }
      }
    }

    for (Diagnostic child : diagnostic.getChildren())
    {
      buildToolTipMessage(result, labelProvider, child.getData().get(0), child, indentation + 1);
    }
  }

  protected void buildMoreToolTipText(StringBuilder result, ILabelProvider labelProvider, Diagnostic diagnostic)
  {
    List<?> data = diagnostic.getData();
    Object object = data.get(0);
    Integer[] path = (Integer[])data.get(1);

    List<Diagnostic> children = diagnostic.getChildren();
    Diagnostic child = children.get(0);
    int index = 0;
    if (child.getData().contains(object))
    {
      ++index;
      Image image = labelProvider.getImage(object);
      if (image != null)
      {
        URI imageURI = ImageURIRegistry.INSTANCE.getImageURI(image);
        result.append("<div><img src='");
        result.append(imageURI);
        result.append("'/> ");
      }
      result.append("<a href='");
      result.append("path:");
      for (Integer segment : path)
      {
        result.append('/');
        result.append(segment);
      }
      String text = escapeContent(labelProvider.getText(object));
      if (text == null || text.length() == 0)
      {
        text = "<i>null</i>";
      }
      result.append("'>");
      result.append(text);
      result.append("</a></div>\n");
      for (Diagnostic grandChild : child.getChildren())
      {
        buildToolTipMessage(result, labelProvider, object, grandChild, 1);
      }
    }

    for (int size = children.size(); index < size; ++index)
    {
      child = children.get(index);
      buildMoreToolTipText(result, labelProvider, child);
    }
  }

  @Override
  public void update(ViewerCell cell)
  {
    // Do nothing.
  }

  @Override
  public void dispose()
  {
    if (liveValidator != null)
    {
      liveValidator.deregister(this);
    }

    if (diagnosticAdapter != null)
    {
      resourceSet.eAdapters().remove(diagnosticAdapter);
    }

    super.dispose();
  }
}
