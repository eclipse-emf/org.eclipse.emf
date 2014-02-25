/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelItemProvider;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelItemProviderAdapterFactory;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XAnnotation;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.mappings.ToXcoreMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.services.XcoreGrammarAccess;
import org.eclipse.emf.ecore.xcore.ui.quickfix.XcoreQuickfixProvider;
import org.eclipse.emf.ecore.xcore.util.XcoreGenModelInitializer;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptorDecorator;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.editors.text.TextEditorActionContributor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.inject.Inject;

/**
 * A derived {@link XtextEditor} that supports a properties view for the Xcore resource's {@link GenModel}.
 */
public class XcoreEditor extends XtextEditor
{
  @Inject
  private XcoreMapper mapper;

  @Inject
  private IValueConverterService valueConverterService;

  @Inject XcoreGrammarAccess xcoreGrammarAccess;

  @Inject XcoreGenModelInitializer genModelInitializer;

  protected List<PropertySheetPage> propertySheetPages = new ArrayList<PropertySheetPage>();

  protected ComposedAdapterFactory adapterFactory;

  @Override
  @SuppressWarnings("rawtypes")
  public Object getAdapter(Class type)
  {
    if (type.equals(IPropertySheetPage.class))
    {
      return getPropertySheetPage();
    }
    else
    {
      return super.getAdapter(type);
    }
  }

  public IPropertySheetPage getPropertySheetPage()
  {
    // Create an adapter factory that uses registered item provider adapter factories, but specializes the one for the GenModel
    //
    adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
    final AdapterFactoryItemDelegator itemDelegator = new AdapterFactoryItemDelegator(adapterFactory);
    adapterFactory.addAdapterFactory
      (new GenModelItemProviderAdapterFactory()
       {
         @Override
         public Adapter createGenModelAdapter()
         {
           // Create a new adapter each time.
           //
           return
             new GenModelItemProvider(this)
             {
               @Override
               public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
               {
                 if (itemPropertyDescriptors == null)
                 {
                   super.getPropertyDescriptors(object);

                   // Merge in the decorated property descriptors for the one GenPackage in the model.
                   //
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

    // Cache the editor's document and resource set.
    //
    final IXtextDocument document = getDocument();
    final ResourceSet resourceSet =
      document.readOnly
        (new IUnitOfWork<ResourceSet, XtextResource>()
         {
           public ResourceSet exec(final XtextResource xtextResource) throws Exception
           {
             return xtextResource.getResourceSet();
           }
         });

    // Create a specialized command stack.
    //
    BasicCommandStack commandStack =
      new BasicCommandStack()
      {
        protected boolean isDefault(final GenModel genModel, EObject eObject, EStructuralFeature eStructuralFeature)
        {
          // Create a freshly initialized GenModel for the original GenModel's EPackage.
          //
          Resource fakeResource = new ResourceImpl(genModel.eResource().getURI());
          final GenModel clonedGenModel = GenModelFactory.eINSTANCE.createGenModel();
          EList<GenPackage> genPackages = genModel.getGenPackages();
          if (!genPackages.isEmpty())
          {
            clonedGenModel.initialize(Collections.singleton(genPackages.get(0).getEcorePackage()));
          }
          genModelInitializer.initialize(clonedGenModel, false);
          fakeResource.getContents().add(clonedGenModel);

          // Traverse the EObject to find its clone in the cloned GenModel.
          //
          EObject clonedEObject =
            new Object()
            {
              EObject traverse(EObject eObject)
              {
                EObject eContainer = eObject.eContainer();
                if (eContainer == null)
                {
                  return clonedGenModel;
                }
                else
                {
                  EReference eContainmentFeature = eObject.eContainmentFeature();
                  EObject clonedEObject = traverse(eContainer);
                  Object value = clonedEObject.eGet(eContainmentFeature);
                  if (eContainmentFeature.isMany())
                  {
                    @SuppressWarnings("unchecked")
                    List<EObject> values = (List<EObject>)value;
                    List<?> originalValues = (List<?>)eContainer.eGet(eContainmentFeature);
                    return values.get(originalValues.indexOf(eObject));
                  }
                  else
                  {
                    return (EObject)value;
                  }
                }
              }
            }.traverse(eObject);

          // Test whether the feature's value is the same as the default.
          //
          Object newValue = eObject.eGet(eStructuralFeature);
          Object defaultValue = clonedEObject.eGet(eStructuralFeature);
          return
            newValue == null ?
              defaultValue == null :
              newValue instanceof GenFeature  && defaultValue instanceof GenFeature ?
                ((GenFeature)newValue).getEcoreFeature() == ((GenFeature)defaultValue).getEcoreFeature() :
                newValue.equals(defaultValue);
        }

        protected String getValue(EObject eObject, EStructuralFeature eStructuralFeature)
        {
          EClassifier eType = eStructuralFeature.getEType();
          Object value = eObject.eGet(eStructuralFeature);
          @SuppressWarnings("unchecked")
          String literal =
            eType instanceof EDataType ?
              eStructuralFeature.isMany() ?
                XMLTypeFactory.eINSTANCE.convertENTITIESBase((List<String>)value) :
                EcoreUtil.convertToString((EDataType)eType, value) :
              ((GenFeature)value).getName();
          return valueConverterService.toString(literal, "STRING");
        }

        @Override
        public void execute(Command command)
        {
          // Before executing the command, add a content adapter to the GenModel to be notified of whatever feature changes.
          //
          final Resource resource = resourceSet.getResources().get(0);
          final GenModel genModel = (GenModel)EcoreUtil.getObjectByType(resource.getContents(), GenModelPackage.Literals.GEN_MODEL);
          final List<Notification> notifications = new ArrayList<Notification>();
          final EContentAdapter eContentAdatper =
            new EContentAdapter()
            {
              @Override
              public void notifyChanged(final Notification notification)
              {
                super.notifyChanged(notification);
                int eventType = notification.getEventType();
                if (eventType == Notification.REMOVING_ADAPTER)
                {
                  // If we are removing the adapters from the GenModel, because it's unloaded when a new GenModel is inferred...
                  //
                  if (notification.getNotifier() instanceof GenModel)
                  {
                    // Defer producing a new selection changed event to update the properties view for the the new inferred selection.
                    //
                    getEditorSite().getShell().getDisplay().asyncExec
                      (new Runnable()
                       {
                         public void run()
                         {
                           document.readOnly
                             (new IUnitOfWork.Void<XtextResource>()
                              {
                                @Override
                                public void process(final XtextResource xtextResource) throws Exception
                                {
                                  ISelection selection = getSourceViewer().getSelectionProvider().getSelection();
                                  for (Iterator<PropertySheetPage> i = propertySheetPages.iterator(); i.hasNext(); )
                                  {
                                    PropertySheetPage propertySheetPage = i.next();
                                    if (propertySheetPage.getControl().isDisposed())
                                    {
                                      i.remove();
                                    }
                                    else
                                    {
                                      propertySheetPage.selectionChanged(XcoreEditor.this, selection);
                                    }
                                  }
                                }
                              });
                         }
                       });
                  }
                }
                else if (!notification.isTouch() && notification.getNotifier() instanceof EObject)
                {
                  // Record the notifications.
                  //
                  notifications.add(0, notification);
                }
              }
            };
          genModel.eAdapters().add(eContentAdatper);

          // Execute the command, recording notifications so they can be processed after the command is complete.
          //
          super.execute(command);

          // Process the deferred notifications.
          //
          if (!notifications.isEmpty())
          {
            final Notification notification = notifications.get(0);

            // For the feature of the object that's changed, process the new contents of the feature.
            //
            document.modify
              (new IUnitOfWork.Void<XtextResource>()
               {
                 @Override
                 public void process(XtextResource state) throws Exception
                 {
                   // Determine the object and feature that are changed.
                   //
                   EObject eObject = (EObject)notification.getNotifier();
                   EStructuralFeature eStructuralFeature = (EStructuralFeature)notification.getFeature();
                   String name = eStructuralFeature.getName();

                   // Determine the affected Xcore element.
                   //
                   ToXcoreMapping xcoreMapping = mapper.getToXcoreMapping(eObject);
                   XNamedElement xNamedElement = xcoreMapping.getXcoreElement();
                   if (xNamedElement == null && eObject instanceof GenModel)
                   {
                     xNamedElement = (XPackage)resource.getContents().get(0);
                   }
                   if (xNamedElement != null)
                   {
                     // Determine the nodes affected for the element,  i.e.,
                     // the node for the element as a whole,
                     // the node for the annotation,
                     // the node for the detail entry,
                     // and the node for the value in the detail entry.
                     //
                     ICompositeNode elementNode = NodeModelUtils.getNode(xNamedElement);
                     ICompositeNode annotationNode = null;
                     ICompositeNode detailNode = null;
                     List<INode> valueNodes = null;

                     // Determine if there is already an annotation for the GenModel's annotation URI.
                     //
                     XAnnotation xAnnotation = xNamedElement.getAnnotation(GenModelPackage.eNS_URI);
                     if (xAnnotation != null)
                     {
                       // If there is, get the node for that.
                       //
                       annotationNode = NodeModelUtils.getNode(xAnnotation);

                       // Determine if there is a detail entry for the affected feature.
                       //
                       for (Map.Entry<String, String> detail : xAnnotation.getDetails())
                       {
                         if (name.equals(detail.getKey()))
                         {
                           // If there is a matching key, determine the overall node for it and the node for the value.
                           //
                           detailNode = NodeModelUtils.findActualNodeFor((EObject)detail);
                           valueNodes = NodeModelUtils.findNodesForFeature((EObject)detail, XcorePackage.Literals.XSTRING_TO_STRING_MAP_ENTRY__VALUE);
                           break;
                         }
                       }
                     }
                     // If we found a node for the element...
                     //
                     if (elementNode != null)
                     {
                       // If there doesn't yet exist an annotation node.
                       //
                       if (annotationNode == null)
                       {
                         // Insert a new annotation with the key/value mapping on a new line before the element node.
                         //
                         int offset = elementNode.getOffset();

                         // Match the indentation of the element.
                         //
                         int line = document.getLineOfOffset(offset);
                         String lineDelimiter = document.getLineDelimiter(line);
                         int lineOffset = document.getLineOffset(line);
                         String indentation = document.get(lineOffset, offset - lineOffset);
                         int length = indentation.length();
                         StringBuilder newIndentation = new StringBuilder(length);
                         for (int i = 0; i < length; ++i)
                         {
                           int codePoint = indentation.codePointAt(i);
                           newIndentation.appendCodePoint(Character.isSpaceChar(codePoint) ? codePoint : ' ');
                         }

                         document.replace(lineOffset, 0, newIndentation + "@GenModel(" + name + "=" + getValue(eObject, eStructuralFeature) + ")" + lineDelimiter);
                       }
                       // If there is a node for the old value...
                       //
                       else if (valueNodes != null)
                       {
                         // If the feature isn't set to the default, and there is a node for the detail entry, we want to remove the node...
                         //
                         if (detailNode != null && isDefault(genModel, eObject, eStructuralFeature))
                         {
                           // Cache the grammar rules we'll need to match.
                           //
                           Keyword comma = xcoreGrammarAccess.getXAnnotationAccess().getCommaKeyword_2_2_0();
                           Keyword leftParenthesis = xcoreGrammarAccess.getXAnnotationAccess().getLeftParenthesisKeyword_2_0();
                           Keyword rightParenthesis = xcoreGrammarAccess.getXAnnotationAccess().getRightParenthesisKeyword_2_3();

                           // Compute the locations of the surrounding mark-up, i.e.,
                           // the left parenthesis of the annotation,
                           // the comma before the detail entry,
                           // the start of the next entry,
                           // and the right parenthesis.
                           //
                           int leftParenthesisOffset = -1;
                           int commaOffset = -1;
                           int nextDetailNodeOffset = -1;
                           int rightParenthesisOffset = -1;

                           // This is set to true once we've iterated past the detail entry.
                           //
                           boolean matched = false;
                           for (INode child : detailNode.getParent().getChildren())
                           {
                             EObject grammarElement = child.getGrammarElement();
                             if (matched)
                             {
                               if (grammarElement == rightParenthesis)
                               {
                                 rightParenthesisOffset = child.getOffset();
                                 break;
                               }
                               else if (NodeModelUtils.findActualSemanticObjectFor(child) instanceof Map.Entry)
                               {
                                 nextDetailNodeOffset = child.getOffset();
                                 break;
                               }
                             }
                             else if (child == detailNode)
                             {
                               matched = true;
                             }
                             else if (grammarElement == leftParenthesis)
                             {
                               leftParenthesisOffset = child.getOffset();
                             }
                             else if (grammarElement == comma)
                             {
                               commaOffset = child.getOffset();
                             }
                           }

                           if (commaOffset != -1)
                           {
                             if (rightParenthesisOffset != -1)
                             {
                               // @GenModel(a="x", b="y", c="z")
                               //                       ^      ^
                               //
                               document.replace(commaOffset, rightParenthesisOffset - commaOffset, "");
                             }
                             else // if (nextDetailNodeOffset != -1)
                             {
                               // @GenModel(a="x", b="y", c="z")
                               //                 ^       ^
                               //
                               document.replace(commaOffset + 1, nextDetailNodeOffset - commaOffset - 1, " ");
                             }
                           }
                           else  // if (leftParenthesisOffset != -1)
                           {
                             if (rightParenthesisOffset != -1)
                             {
                               // @GenModel(a="x")
                               //          ^     ^
                               //
                               // document.replace(leftParenthesisOffset, rightParenthesisOffset - leftParenthesisOffset + 1, "");
                               XcoreQuickfixProvider.RemovalRegion removalRegion = new XcoreQuickfixProvider.RemovalRegion(document, xAnnotation);
                               document.replace(removalRegion.getDeleteBegin(), removalRegion.getDeleteEnd() - removalRegion.getDeleteBegin(), "");
                             }
                             else // if (nextDetailNodeOffset != -1)
                             {
                               // @GenModel(a="x", b="y", c="z")
                               //           ^      ^
                               //
                               document.replace(leftParenthesisOffset + 1, nextDetailNodeOffset - leftParenthesisOffset - 1, "");
                             }
                           }
                         }
                         else
                         {
                           // Replace the old value with the new value.
                           // @GenModel(a="x")
                           //             ^ ^
                           //
                           INode valueNode = valueNodes.get(0);
                           document.replace(valueNode.getOffset(), valueNode.getLength(), getValue(eObject, eStructuralFeature));
                         }
                       }
                       // If this is the first detail entry...
                       //
                       else if (xAnnotation != null && xAnnotation.getDetails().isEmpty())
                       {
                         // Add the key/value mapping with new parentheses.
                         // @GenModel
                         //          ^
                         //
                         int offset = annotationNode.getOffset() + annotationNode.getLength();
                         document.replace(offset, 0, "(" + name + "=" + getValue(eObject, eStructuralFeature) + ")");
                       }
                       // Otherwise, we just need to add a new key/value mapping to the end of the list.
                       // @GenModel(a="x")
                       //                ^
                       //
                       else
                       {
                         int offset = annotationNode.getOffset() + annotationNode.getLength() - 1;
                         document.replace(offset, 0, ", " + name + "=" + getValue(eObject, eStructuralFeature));
                       }
                     }
                   }
                 }
               });
          }
        }
      };

    // Create the editing domain with a special command stack.
    // Be sure that only objects in the main resource are modifiable.
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

    // Ensure that the editing domain for the resource set can be determined.
    //
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

    // Create a specialized property sheet page.
    //
    final PropertySheetPage propertySheetPage =
      new ExtendedPropertySheetPage(editingDomain)
      {
        @Override
        public void setSelectionToViewer(List<?> selection)
        {
          // TODO
          XcoreEditor.this.setFocus();
        }

        @Override
        public void setActionBars(IActionBars actionBars)
        {
          // Ensure that the undo/redo actions are hooked up.
          //
          super.setActionBars(actionBars);
          TextEditorActionContributor actionBarContributor = (TextEditorActionContributor)getEditorSite().getActionBarContributor();
          IActionBars editorActionBars = actionBarContributor.getActionBars();
          actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), editorActionBars.getGlobalActionHandler((ActionFactory.UNDO.getId())));
          actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), editorActionBars.getGlobalActionHandler((ActionFactory.REDO.getId())));
        }

        /**
         * A helper utility for processing an EObject to determine the appropriate GenModel element to select.
         * Returns whether candidate was successfully determined.
         */
        protected boolean selectionChanged(IWorkbenchPart part, EObject eObject)
        {
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
              return true;
            }
          }
          else if (eObject != null)
          {
            return selectionChanged(part, eObject.eContainer());
          }
          return false;
        }

        @Override
        public void selectionChanged(final IWorkbenchPart part, ISelection selection)
        {
          if (selection instanceof IStructuredSelection)
          {
            // If the first element is an EObjectNode from the outline view...
            //
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
                       // Determine the EObject and process that instead.
                       //
                       EObject eObject = eObjectNode.getEObject(xtextResource);
                       return selectionChanged(part, eObject);
                     }
                   });

              // Don't continue with default processing if we've already successfully processed the selection.
              //
              if (handled)
              {
                return;
              }
            }
          }
          else if (selection instanceof ITextSelection)
          {
            // Map the selection to a model object...
            //
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
                           // Determine the EObject and process that instead.
                           //
                           EObject eObject = NodeModelUtils.findActualSemanticObjectFor(node);
                           return selectionChanged(part, eObject);
                         }
                       }
                     }
                     return Boolean.FALSE;
                   }
                 });

            // Don't continue with default processing if we've already successfully processed the selection.
            //
            if (handled)
            {
              return;
            }

            selection = new StructuredSelection();
          }
          super.selectionChanged(part, selection);
        }
      };

    // Set the content provider.
    //
    final AdapterFactoryContentProvider contentProvider = new AdapterFactoryContentProvider(adapterFactory)
    {
      @Override
      protected IPropertySource createPropertySource(Object object, IItemPropertySource itemPropertySource)
      {
        return 
          new PropertySource(object, itemPropertySource)
          {
            @Override
            protected IPropertyDescriptor createPropertyDescriptor(IItemPropertyDescriptor itemPropertyDescriptor)
            {
              return 
                new PropertyDescriptor(object, itemPropertyDescriptor)
                {
                  @Override
                  public CellEditor createPropertyEditor(Composite composite)
                  {
                    if (object instanceof IItemPropertySource)
                    {
                      Object value = ((IItemPropertySource) object).getEditableValue(object);
                      if (value instanceof EObject && ((EObject)value).eClass().getEPackage() == EcorePackage.eINSTANCE)
                      {
                        // Ensure that Ecore properties are read only.
                        //
                        return null;
                      }
                    }
                    
                    return super.createPropertyEditor(composite);
                  }
                };
            }
          };
      }
    };
    propertySheetPage.setPropertySourceProvider(contentProvider);

    // Set the initial selection.
    //
    getEditorSite().getShell().getDisplay().asyncExec
      (new Runnable()
       {
         public void run()
         {
           propertySheetPage.selectionChanged(XcoreEditor.this, getSourceViewer().getSelectionProvider().getSelection());
         }
       });

    propertySheetPages.add(propertySheetPage);

    return propertySheetPage;
  }

  @Override
  public void dispose()
  {
    super.dispose();

    if (adapterFactory != null)
    {
      adapterFactory.dispose();
    }

    for (PropertySheetPage propertySheetPage : propertySheetPages)
    {
      propertySheetPage.dispose();
    }
  }
}
