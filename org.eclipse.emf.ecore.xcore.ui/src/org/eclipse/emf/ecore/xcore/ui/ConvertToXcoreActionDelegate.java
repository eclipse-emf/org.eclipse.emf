/**
 * <copyright>
 *
 * Copyright (c) 2004-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: MapToEcoreActionDelegate.java,v 1.8 2008/05/04 10:59:02 emerks Exp $
 */
package org.eclipse.emf.ecore.xcore.ui;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.xbase.XBlockExpression;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XAnnotation;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XEnum;
import org.eclipse.emf.ecore.xcore.XEnumLiteral;
import org.eclipse.emf.ecore.xcore.XGenericType;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XModelElement;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XParameter;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.XTypeParameter;
import org.eclipse.emf.ecore.xcore.XTypedElement;
import org.eclipse.emf.ecore.xcore.XcoreFactory;
import org.eclipse.emf.ecore.xcore.ui.internal.XcoreActivator;
import org.eclipse.emf.ecore.xcore.util.EcoreXcoreBuilder;


/**
 *
 */
public class ConvertToXcoreActionDelegate extends ActionDelegate
{
  protected EPackage getInputEPackage(IStructuredSelection structuredSelection)
  {
    Object element = structuredSelection.getFirstElement();
    if (element instanceof IFile)
    {
      IFile file = (IFile)element;
      if ("ecore".equals(file.getFullPath().getFileExtension()))
      {
        return
          (EPackage)EcoreUtil.getObjectByType
            (new ResourceSetImpl().getResource(URI.createPlatformResourceURI(file.getFullPath().toString(), true), true).getContents(),
             EcorePackage.Literals.EPACKAGE);
      }
      else if ("genmodel".equals(file.getFullPath().getFileExtension()))
      {
        GenModel genModel = 
          (GenModel)EcoreUtil.getObjectByType
            (new ResourceSetImpl().getResource(URI.createPlatformResourceURI(file.getFullPath().toString(), true), true).getContents(),
             GenModelPackage.Literals.GEN_MODEL);
        if (genModel.getGenPackages().size() == 1)
        {
          return genModel.getGenPackages().get(0).getEcorePackage();
        }
      }
    }

    return null;
  }

  protected IFile getFile(Resource resource)
  {
      URI uri = resource.getURI();
      uri = resource.getResourceSet().getURIConverter().normalize(uri);
      String platformResourceString = uri.toPlatformString(true);
      return platformResourceString != null ?
        ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformResourceString)) :
        null;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  @Override
  public void run(IAction action)
  {
    final IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

    final EPackage inputEPackage = getInputEPackage((IStructuredSelection)workbenchWindow.getSelectionService().getSelection());
    final Resource inputResource = inputEPackage.eResource();

    URI outputResourceURI = inputResource.getURI().trimFileExtension().appendFileExtension("xcore");
    final Resource outputResource = inputResource.getResourceSet().createResource(outputResourceURI);

    ProgressMonitorDialog dialog = new ProgressMonitorDialog(workbenchWindow.getShell());
    try
    {
      dialog.run(false, true,
        new WorkspaceModifyOperation()
        {
          @Override
          protected void execute(IProgressMonitor progressMonitor)
          {
            try
            {
              progressMonitor.beginTask("", 1);

              EPackage copy = EcoreUtil.copy(inputEPackage);
              
              Resource genModelResource = inputResource.getResourceSet().getResources().get(0);
              if (genModelResource != inputResource)
              {
                GenModel inputGenModel = (GenModel)genModelResource.getContents().get(0);
                inputGenModel.reconcile();
                final GenModel genModel =  GenModelFactory.eINSTANCE.createGenModel();
                genModel.initialize(Collections.singleton(copy));
                genModelResource.getContents().add(genModel);
                genModel.initialize();
                new Object() 
                {
                  void visit(GenBase genBase1, GenBase genBase2)
                  {
                    if (genBase1.eClass() == genBase2.eClass())
                    {
                      for (EAttribute eAttribute : genBase1.eClass().getEAllAttributes())
                      {
                        if (genBase1.eIsSet(eAttribute))
                        {
                          Object value1 = genBase1.eGet(eAttribute);
                          Object value2 = genBase2.eGet(eAttribute);
                          if (value1 == null ? value2 != null : !value1.equals(value2))
                          {
                            EModelElement eModelElement = genBase2.getEcoreModelElement();
                            if (eModelElement == null)
                            {
                              eModelElement = genModel.getGenPackages().get(0).getEcorePackage();
                            }
                            EcoreUtil.setAnnotation
                              (eModelElement, 
                               GenModelPackage.eNS_URI, 
                               eAttribute.getName(), 
                               EcoreUtil.convertToString(eAttribute.getEAttributeType(), value1));
                          }
                          for (Iterator<EObject> i = genBase1.eContents().iterator(), j = genBase2.eContents().iterator(); i.hasNext() && j.hasNext(); )
                          {
                            EObject content1 = i.next();
                            EObject content2 = j.next();
                            if (content1 instanceof GenBase && content2 instanceof GenBase)
                            {
                              visit((GenBase)content1, (GenBase)content2);
                            }
                          }
                        }
                      }
                    }
                  }
                }.visit(inputGenModel, genModel);
              }
              
              outputResource.getContents().add(new EcoreXcoreBuilder().getXPackage(copy));
              outputResource.getContents().add(copy);

              try
              {
                Map<Object, Object> options  = new HashMap<Object, Object>();
                SaveOptions.newBuilder().format().noValidation().getOptions().addTo(options);
                outputResource.save(options);

                IFile file = getFile(outputResource);
                IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();

                final IWorkbenchPart activePart = workbenchPage.getActivePart();
                if (activePart instanceof ISetSelectionTarget)
                {
                  final ISelection targetSelection = new StructuredSelection(file);
                  workbenchWindow.getShell().getDisplay().asyncExec(new Runnable()
                    {
                      @Override
                      public void run()
                      {
                        ((ISetSelectionTarget)activePart).selectReveal(targetSelection);
                      }
                    });
                }

                try
                {
                  workbenchPage.openEditor(new FileEditorInput(file), workbenchWindow.getWorkbench().getEditorRegistry().getDefaultEditor(file.getFullPath().toString(), Platform.getContentTypeManager().getContentType("org.eclipse.emf.ecore")).getId());
                }
                catch (PartInitException pie)
                {
                  XcoreActivator.getInstance().getLog().log(new Status(IStatus.WARNING, "org.eclipse.emf.ecore.xcore.ui", 0, pie.getLocalizedMessage(), pie));
                }
              }
              catch (IOException ioe)
              {
                XcoreActivator.getInstance().getLog().log(new Status(IStatus.WARNING, "org.eclipse.emf.ecore.xcore.ui", 0, ioe.getLocalizedMessage(), ioe));
              }
            }
            finally
            {
              progressMonitor.done();
            }
          }
        });
    }
    catch (InterruptedException ie)
    {
      // ignore
    }
    catch (InvocationTargetException ite)
    {
      XcoreActivator.getInstance().getLog().log(new Status(IStatus.WARNING, "org.eclipse.emf.ecore.xcore.ui", 0, ite.getLocalizedMessage(), ite));
    }
  }

  /*
  static class EcoreXcoreBuilder
  {
    List<Runnable> runnables = new ArrayList<Runnable>();

    XPackage getXPackage(EPackage ePackage)
    {
      XPackage xPackage = XcoreFactory.eINSTANCE.createXPackage();
      handleAnnotations(ePackage, xPackage);
      String name = ePackage.getName();
      xPackage.setName(name);
      // TODO
      // xPackage.setNsURI(ePackage.getNsURI());
      // xPackage.setNsPrefix(ePackage.getNsPrefix());

      for (EClassifier eClassifier : ePackage.getEClassifiers())
      {
        XClassifier xClassifier = getXClassifier(eClassifier);
        xPackage.getClassifiers().add(xClassifier);
      }
      
      // Hook up local references.
      //
      for (Runnable runnable : runnables)
      {
        runnable.run();
      }
      runnables.clear();

      return xPackage;
    }

    void handleAnnotations(final EModelElement eModelElement, final XModelElement xModelElement)
    {
      runnables.add
        (new Runnable()
         {
           @Override
           public void run()
           {
             for (EAnnotation eAnnotation : eModelElement.getEAnnotations())
             {
               XAnnotation xAnnotation = XcoreFactory.eINSTANCE.createXAnnotation();
               String source = eAnnotation.getSource();
               xAnnotation.setSource(getXAnnotationDirective(xModelElement, source));
               for (Map.Entry<String, String> detail : eAnnotation.getDetails())
               {
                 xAnnotation.getDetails().put(detail.getKey(), detail.getValue());
               }
               xModelElement.getAnnotations().add(xAnnotation);
             }
           }
         });
    }

    XAnnotationDirective getXAnnotationDirective(XModelElement xModelElement, String source)
    {
      XPackage xPackage = (XPackage)EcoreUtil.getRootContainer(xModelElement);
      for (XAnnotationDirective xAnnotationDirective : xPackage.getAnnotationDirectives())
      {
        if (source.equals(xAnnotationDirective.getSourceURI()))
        {
          return xAnnotationDirective;
          
        }
      }
      XAnnotationDirective xAnnotationDirective = XcoreFactory.eINSTANCE.createXAnnotationDirective();
      xAnnotationDirective.setSourceURI(source);
      // TODO
      URI sourceURI = URI.createURI(source);
      xAnnotationDirective.setName(sourceURI.lastSegment());
      xPackage.getAnnotationDirectives().add(xAnnotationDirective);
      return xAnnotationDirective;
    }

    XClassifier getXClassifier(EClassifier eClassifier)
    {
      XClassifier xClassifier;
      if (eClassifier instanceof EClass)
      {
        xClassifier = getXClass((EClass)eClassifier);
      }
      else if (eClassifier instanceof EEnum)
      {
        xClassifier = getXEnum((EEnum)eClassifier);
      }
      else
      {
        xClassifier = getXDataType((EDataType)eClassifier);
      }
      handleAnnotations(eClassifier, xClassifier);
      xClassifier.setName(eClassifier.getName());
      String instanceTypeName = eClassifier.getInstanceTypeName();
      if (instanceTypeName != null)
      {
        xClassifier.setInstanceTypeName(instanceTypeName);
      }
      for (ETypeParameter eTypeParameter : eClassifier.getETypeParameters())
      {
        XTypeParameter xTypeParameter = getXTypeParameter(eTypeParameter);
        xClassifier.getTypeParameters().add(xTypeParameter);
      }
      return xClassifier;
    }

    XClass getXClass(EClass eClass)
    {
      XClass xClass = XcoreFactory.eINSTANCE.createXClass();
      if (eClass.isInterface())
      {
        xClass.setInterface(true);
      }
      else if (eClass.isAbstract())
      {
        xClass.setAbstract(true);
      }
      
      for (EGenericType eGenericSuperType : eClass.getEGenericSuperTypes())
      {
        xClass.getSuperTypes().add(getXGenericType(eGenericSuperType));
      }
      
      for (EStructuralFeature eStructuralFeature : eClass.getEStructuralFeatures())
      {
        XStructuralFeature xStructuralFeature = getXStructuralFeature(eStructuralFeature);
        xClass.getMembers().add(xStructuralFeature);
      }
      
      for (EOperation eOperation : eClass.getEOperations())
      {
        XOperation xOperation = getXOperation(eOperation);
        xClass.getMembers().add(xOperation);
      }
      return xClass;
    }

    XOperation getXOperation(EOperation eOperation)
    {
      XOperation xOperation = XcoreFactory.eINSTANCE.createXOperation();
      handleXTypedElement(xOperation, eOperation);
      for (EParameter eParameter : eOperation.getEParameters())
      {
        XParameter xParameter = getXParameter(eParameter);
        xOperation.getParameters().add(xParameter);
      }
      for (ETypeParameter eTypeParameter : eOperation.getETypeParameters())
      {
        XTypeParameter xTypeParameter = getXTypeParameter(eTypeParameter);
        xOperation.getTypeParameters().add(xTypeParameter);
      }
      for (EGenericType eException : eOperation.getEGenericExceptions())
      {
        XGenericType xException = getXGenericType(eException);
        xOperation.getExceptions().add(xException);
      }
      EAnnotation ecoreAnnotation = eOperation.getEAnnotation(EcorePackage.eNS_URI);
      if (ecoreAnnotation != null && !ecoreAnnotation.getContents().isEmpty())
      {
        EObject body = ecoreAnnotation.getContents().get(0);
        if (body instanceof XBlockExpression)
        {
          xOperation.setBody((XBlockExpression)body);
        }
      }
      return xOperation;
    }
    
    XParameter getXParameter(EParameter eParameter)
    {
      XParameter xParameter = XcoreFactory.eINSTANCE.createXParameter();
      handleXTypedElement(xParameter, eParameter);
      return xParameter;
    }

    XTypeParameter getXTypeParameter(ETypeParameter eTypeParameter)
    {
      XTypeParameter xTypeParameter = XcoreFactory.eINSTANCE.createXTypeParameter();
      xTypeParameter.setName(eTypeParameter.getName());
      for (EGenericType eGenericType : eTypeParameter.getEBounds())
      {
        xTypeParameter.getBounds().add(getXGenericType(eGenericType));
      }
      return xTypeParameter;
    }
    
    void handleXTypedElement(XTypedElement xTypedElement, ETypedElement eTypedElement)
    {
      
      handleAnnotations(eTypedElement, xTypedElement);
      xTypedElement.setName(eTypedElement.getName());
      xTypedElement.setType(getXGenericType(eTypedElement.getEGenericType()));
      if (eTypedElement.isUnique() && !(eTypedElement instanceof EReference))
      {
        xTypedElement.setUnique(true);
      }
      if (!eTypedElement.isOrdered())
      {
        xTypedElement.setUnordered(true);
      }
      int lowerBound = eTypedElement.getLowerBound();
      int upperBound = eTypedElement.getUpperBound();
      if (lowerBound == 0)
      {
        if (upperBound == EStructuralFeature.UNBOUNDED_MULTIPLICITY)
        {
          xTypedElement.setMultiplicity(new int [] {});
        }
        else if (upperBound == 1)
        {
          xTypedElement.setMultiplicity(new int [] {-3});
        }
        else
        {
          xTypedElement.setMultiplicity(new int [] {0, upperBound});
        }
      }
      else if (lowerBound == 1)
      {
        if (upperBound == EStructuralFeature.UNBOUNDED_MULTIPLICITY)
        {
          xTypedElement.setMultiplicity(new int [] {-2});
        }
        else if (lowerBound != 1)
        {
          xTypedElement.setMultiplicity(new int [] {1, upperBound});
        }
      }
      else if (lowerBound == upperBound)
      {
        xTypedElement.setMultiplicity(new int [] {lowerBound});
      }
      else
      {
        xTypedElement.setMultiplicity(new int [] {lowerBound, upperBound});
      }
    }

    XGenericType getXGenericType(EGenericType eGenericType)
    {
      final XGenericType xGenericType = XcoreFactory.eINSTANCE.createXGenericType();
      EGenericType lowerBound = eGenericType.getELowerBound();
      if (lowerBound != null)
      {
        xGenericType.setLowerBound(getXGenericType(lowerBound));
      }
      EGenericType upperBound = eGenericType.getEUpperBound();
      if (upperBound != null)
      {
        xGenericType.setUpperBound(getXGenericType(upperBound));
      }
      for (EGenericType typeArgument : eGenericType.getETypeArguments())
      {
        xGenericType.getTypeArguments().add(getXGenericType(typeArgument));
      }
      
      EClassifier eClassifier = eGenericType.getEClassifier();
      if (eClassifier != null)
      {
        xGenericType.setType(eClassifier);
      }
      else
      {
        xGenericType.setType(eGenericType.getETypeParameter());
      }
      
      return xGenericType;
    }
    
    XClassifier getClassifier(XPackage xPackage, String name)
    {
      for (XClassifier xClassifier : xPackage.getClassifiers())
      {
        if (name.equals(xClassifier.getName()))
        {
          return xClassifier;
          
        }
      }
      return null;
    }

    XStructuralFeature getStructuralFeature(XClass xClass, String name)
    {
      for (XMember xMember : xClass.getMembers())
      {
        if (xMember instanceof XStructuralFeature && name.equals(xMember.getName()))
        {
          return (XStructuralFeature)xMember;
        }
      }
      return null;
    }

    XStructuralFeature getXStructuralFeature(EStructuralFeature eStructuralFeature)
    {
      final XStructuralFeature xStructuralFeature = XcoreFactory.eINSTANCE.createXStructuralFeature();
      if (eStructuralFeature instanceof EReference)
      {
        final EReference eReference = (EReference)eStructuralFeature;
        if (eReference.isContainment())
        {
          xStructuralFeature.setContainment(true);
          if (eReference.isResolveProxies())
          {
            xStructuralFeature.setResolveProxies(true);
          }
        }
        else if (!eReference.isResolveProxies())
        {
          xStructuralFeature.setLocal(true);
        }
        EReference opposite = eReference.getEOpposite();
        if (opposite != null)
        {
          xStructuralFeature.setOpposite(opposite);
        }
        xStructuralFeature.getKeys().addAll(eReference.getEKeys());
      }
      else
      {
        EAttribute eAttribute = (EAttribute)eStructuralFeature;
        if (eAttribute.isID())
        {
          xStructuralFeature.setID(true);
        }
      }
      if (!eStructuralFeature.isChangeable())
      {
        xStructuralFeature.setReadonly(true);
      }
      if (eStructuralFeature.isTransient())
      {
        xStructuralFeature.setTransient(true);
      }
      if (eStructuralFeature.isVolatile())
      {
        xStructuralFeature.setVolatile(true);
      }
      if (eStructuralFeature.isDerived())
      {
        xStructuralFeature.setDerived(true);
      }
      if (eStructuralFeature.isUnsettable())
      {
        xStructuralFeature.setUnsettable(true);
      }
      handleXTypedElement(xStructuralFeature, eStructuralFeature);
      xStructuralFeature.setName(eStructuralFeature.getName());
      return xStructuralFeature;
    }

    XDataType getXDataType(EDataType eDataType)
    {
      XDataType xDataType = XcoreFactory.eINSTANCE.createXDataType();
      return xDataType;
    }

    XEnum getXEnum(EEnum eEnum)
    {
      XEnum xEnum = XcoreFactory.eINSTANCE.createXEnum();
      for (EEnumLiteral eEnumLiteral : eEnum.getELiterals())
      {
        xEnum.getLiterals().add(getXEnumLiteral(eEnumLiteral));
      }
      return xEnum;
    }

    XEnumLiteral getXEnumLiteral(EEnumLiteral eEnumLiteral)
    {
      XEnumLiteral xEnumLiteral = XcoreFactory.eINSTANCE.createXEnumLiteral();
      handleAnnotations(eEnumLiteral, xEnumLiteral);
      xEnumLiteral.setName(eEnumLiteral.getName());
      if (!eEnumLiteral.getName().equals(eEnumLiteral.getLiteral()))
      {
        xEnumLiteral.setLiteral(eEnumLiteral.getLiteral());
      }
      xEnumLiteral.setValue(eEnumLiteral.getValue());
      return xEnumLiteral;
    }
  }
  */

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
   *      org.eclipse.jface.viewers.ISelection)
   */
  @Override
  public void selectionChanged(IAction action, ISelection selection)
  {
    action.setEnabled(IStructuredSelection.class.isInstance(selection) && getInputEPackage((IStructuredSelection)selection) != null);
  }
}