/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.editor;

import java.util.EventObject;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelItemProvider;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelItemProviderAdapterFactory;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.mappings.ToXcoreMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptorDecorator;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.editors.text.TextEditorActionContributor;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.inject.Inject;

public class XcoreEditor extends XtextEditor
{
  protected PropertySheetPage propertySheetPage;

  @Inject
  private XcoreMapper mapper;

  @Override
  @SuppressWarnings("rawtypes")
  public Object getAdapter(Class type)
  {
    if (type.equals(IPropertySheetPage.class))
    {
      return true ? null : getPropertySheetPage();
    }
    else
    {
      return super.getAdapter(type);
    }
  }

  public IPropertySheetPage getPropertySheetPage()
  {
    if (propertySheetPage == null)
    {
      final ISourceViewer sourceViewer = getSourceViewer();
      final ITextListener textListener =
       new ITextListener()
       {
         public void textChanged(TextEvent event)
         {
           propertySheetPage.selectionChanged(XcoreEditor.this, sourceViewer.getSelectionProvider().getSelection());
         }
       };
      sourceViewer.addTextListener(textListener);
      ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
      final AdapterFactoryItemDelegator itemDelegator = new AdapterFactoryItemDelegator(adapterFactory);
      adapterFactory.addAdapterFactory
        (new GenModelItemProviderAdapterFactory()
         {
           @Override
           public Adapter createGenModelAdapter()
           {
             return
               new GenModelItemProvider(this)
               {
                 @Override
                 public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
                 {
                   if (itemPropertyDescriptors == null)
                   {
                     super.getPropertyDescriptors(object);

                     GenModel genModel = (GenModel)object;
                     EList<GenPackage> genPackages = genModel.getGenPackages();
                     if (!genPackages.isEmpty())
                     {
                       GenPackage genPackage = genPackages.get(0);
                      List<IItemPropertyDescriptor> genPackagePropertyDescriptors = itemDelegator.getPropertyDescriptors(genPackage);
                       for (IItemPropertyDescriptor genPackagePropertyDescriptor : genPackagePropertyDescriptors)
                       {
                         itemPropertyDescriptors.add(new ItemPropertyDescriptorDecorator(genPackage, genPackagePropertyDescriptor));
                       }
                     }
                   }
                   return itemPropertyDescriptors;
                 }
               };
             } 
           });
      BasicCommandStack commandStack =
        new BasicCommandStack()
        {
          @Override
          public void execute(Command command)
          {
            /*
            GenModel genModel = (GenModel)EcoreUtil.getObjectByType(xtextResource.getContents(), GenModelPackage.Literals.GEN_MODEL);
            EContentAdapter eContentAdatper =
              new EContentAdapter()
              {
                @Override
                public void notifyChanged(final Notification notification)
                {
                  super.notifyChanged(notification);
                  if (!notification.isTouch() &&  notification.getNotifier() instanceof EObject)
                  {
                    document.modify
                      (new IUnitOfWork.Void<XtextResource>()
                       {
                         @Override
                         public void process(XtextResource state) throws Exception
                         {
                           EObject eObject = (EObject)notification.getNotifier();
                           ToXcoreMapping xcoreMapping = mapper.getToXcoreMapping(eObject);
                           XNamedElement xNamedElement = xcoreMapping.getXcoreElement();
                           if (xNamedElement == null && eObject instanceof GenModel)
                           {
                             xNamedElement = (XPackage)xtextResource.getContents().get(0);
                           }
                           if (xNamedElement != null)
                           {
                             ICompositeNode node = NodeModelUtils.getNode(xNamedElement);
                             if (node != null)
                             {
                               int offset = node.getOffset();
                               sourceViewer.removeTextListener(textListener);
                               // document.replace(offset, 0, "xxx");
                               sourceViewer.addTextListener(textListener);
                             }
                           }
                           System.err.println("###" + xcoreMapping);
                         }
                       });
                  }
                }
              };
            */
            super.execute(command);
          }
        };
      // Add a listener to set the most recent command's affected objects to be the selection of the viewer with focus.
      //
      commandStack.addCommandStackListener
        (new CommandStackListener()
         {
           public void commandStackChanged(final EventObject event)
           {
             Shell shell= getSite().getShell();
             Display display= shell.getDisplay();
             display.asyncExec
               (new Runnable()
                {
                  public void run()
                  {
                    firePropertyChange(IEditorPart.PROP_DIRTY);

                    // Try to select the affected objects.
                    //
                    Command mostRecentCommand = ((CommandStack)event.getSource()).getMostRecentCommand();
                    if (mostRecentCommand != null)
                    {
                      System.err.println("###" + mostRecentCommand.getAffectedObjects());
                      // setSelectionToViewer(mostRecentCommand.getAffectedObjects());
                    }

                    if (propertySheetPage != null && !propertySheetPage.getControl().isDisposed())
                    {
                      propertySheetPage.refresh();
                    }
                  }
                });
           }
         });

      final IXtextDocument document = getDocument();
      ResourceSet resourceSet =
        document.readOnly
          (new IUnitOfWork<ResourceSet, XtextResource>()
           {
             public ResourceSet exec(final XtextResource xtextResource) throws Exception
             {
               return xtextResource.getResourceSet();
             }
           });

      // Create the editing domain with a special command stack.
      //
      final AdapterFactoryEditingDomain editingDomain =
        new AdapterFactoryEditingDomain(adapterFactory, commandStack, resourceSet)
        {
          @Override
          public boolean isReadOnly(Resource resource)
          {
            return super.isReadOnly(resource) || getResourceSet().getResources().indexOf(resource) != 0;
          }
        };

      class EditingDomainProvider extends AdapterImpl implements IEditingDomainProvider
      {
        public EditingDomain getEditingDomain()
        {
          return editingDomain;
        }
        @Override
        public boolean isAdapterForType(Object type)
        {
          return IEditingDomainProvider.class.equals(type);
        }
      }
      resourceSet.eAdapters().add(new EditingDomainProvider());

      propertySheetPage =
        new ExtendedPropertySheetPage(editingDomain)
        {
          @Override
          public void setSelectionToViewer(List<?> selection)
          {
            XcoreEditor.this.setFocus();
          }

          @Override
          public void setActionBars(IActionBars actionBars)
          {
            super.setActionBars(actionBars);
            TextEditorActionContributor actionBarContributor = (TextEditorActionContributor)getEditorSite().getActionBarContributor();
            IActionBars editorActionBars = actionBarContributor.getActionBars();
            actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), editorActionBars.getGlobalActionHandler((ActionFactory.UNDO.getId())));
            actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), editorActionBars.getGlobalActionHandler((ActionFactory.REDO.getId())));
          }

          @Override
          public void selectionChanged(final IWorkbenchPart part, ISelection selection)
          {
            if (selection instanceof IStructuredSelection)
            {
              Object element = ((IStructuredSelection)selection).getFirstElement();
              if (element instanceof EObjectNode)
              {
                final EObjectNode eObjectNode = (EObjectNode)element;
                IXtextDocument document = getDocument();
                Boolean handled =
                  document.readOnly
                    (new IUnitOfWork<Boolean, XtextResource>()
                     {
                       public Boolean exec(XtextResource xtextResource) throws Exception
                       {
                         EObject eObject = eObjectNode.getEObject(xtextResource);
                         if (eObject instanceof XNamedElement)
                         {
                           GenBase genBase = mapper.getGen((XNamedElement)eObject);
                           if (genBase instanceof GenPackage)
                           {
                             genBase = ((GenPackage)genBase).getGenModel();
                           }
                           selectionChanged(part, new StructuredSelection(genBase));
                           return Boolean.TRUE;
                         }
                         else
                         {
                           return Boolean.FALSE;
                         }
                       }
                     });
                if (handled)
                {
                  return;
                }
              }
            }
            else if (selection instanceof ITextSelection)
            {
              final ITextSelection textSelection = (ITextSelection)selection;
              IXtextDocument document = getDocument();
              Boolean handled =
                document.readOnly
                  (new IUnitOfWork<Boolean, XtextResource>()
                   {
                     public Boolean exec(XtextResource xtextResource) throws Exception
                     {
                       IParseResult parseResult = xtextResource.getParseResult();
                       if (parseResult != null)
                       {
                         ICompositeNode rootNode = parseResult.getRootNode();
                         if (rootNode != null)
                         {
                           ILeafNode node = NodeModelUtils.findLeafNodeAtOffset(rootNode, textSelection.getOffset());
                           if (node != null)
                           {
                             EObject eObject = NodeModelUtils.findActualSemanticObjectFor(node);
                             if (eObject instanceof XNamedElement)
                             {
                               GenBase genBase = mapper.getGen((XNamedElement)eObject);
                               if (genBase instanceof GenPackage)
                               {
                                 genBase = ((GenPackage)genBase).getGenModel();
                               }
                               if (genBase != null)
                               {
                                 selectionChanged(part, new StructuredSelection(genBase));
                                 return Boolean.TRUE;
                               }
                             }
                           }
                         }
                       }
                       return Boolean.FALSE;
                     }
                   });
              
              if (handled)
              {
                return;
              }
              selection = new StructuredSelection();
            }
            super.selectionChanged(part, selection);
          }
        };
      final AdapterFactoryContentProvider contentProvider = new AdapterFactoryContentProvider(adapterFactory);
      propertySheetPage.setPropertySourceProvider(contentProvider);
    }

    return propertySheetPage;
  }
  
  // TODO Dispose.
}
