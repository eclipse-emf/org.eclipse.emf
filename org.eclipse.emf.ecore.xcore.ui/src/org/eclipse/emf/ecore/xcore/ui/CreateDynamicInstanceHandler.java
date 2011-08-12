package org.eclipse.emf.ecore.xcore.ui;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.presentation.DynamicModelWizard;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.inject.Inject;

public class CreateDynamicInstanceHandler extends AbstractHandler
{
  protected static final URI PLATFORM_RESOURCE = URI.createPlatformResourceURI("/", false);
  
  @Inject
  protected EObjectAtOffsetHelper eObjectAtOffsetHelper;
  
  @Inject
  private XcoreMapper mapper;
  
  public Object execute(ExecutionEvent event) throws ExecutionException
  {
    XtextEditor xtextEditor = EditorUtils.getActiveXtextEditor(event);
    final ITextSelection selection = (ITextSelection) xtextEditor.getSelectionProvider().getSelection();
    // EObject element = eObjectAtOffsetHelper.resolveElementAt(localResource, selection.getOffset());
    xtextEditor.getDocument().readOnly
      (new IUnitOfWork<Object, XtextResource>() 
       {
         public Object exec(XtextResource localResource) throws Exception 
         {
           Object element = eObjectAtOffsetHelper.resolveElementAt(localResource, selection.getOffset());
           if (element instanceof XClass)
           {
             EClass eClass = (EClass)mapper.getEcore((XClass)element);
             if (eClass != null)
             {
               URI uri = eClass.eResource().getURI();
               IStructuredSelection selection = StructuredSelection.EMPTY;
               if (uri != null && uri.isHierarchical())
               {
                 if (uri.isRelative() || (uri = uri.deresolve(PLATFORM_RESOURCE)).isRelative())
                 {
                   IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toString()));
                   if (file.exists())
                   {
                     selection = new StructuredSelection(file);
                   }
                 }
               }
    
               DynamicModelWizard dynamicModelWizard = new DynamicModelWizard(eClass);
               dynamicModelWizard.init(PlatformUI.getWorkbench(), selection);
               WizardDialog wizardDialog = 
                 new WizardDialog
                   (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),  
                    dynamicModelWizard);
    
               wizardDialog.open();
             }
           } 
           return null;
         }
       });
    return null;
  }
}
