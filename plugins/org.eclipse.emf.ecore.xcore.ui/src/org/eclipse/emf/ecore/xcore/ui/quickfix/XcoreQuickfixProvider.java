/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.quickfix;


import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.emf.ecore.xcore.XAttribute;
import org.eclipse.emf.ecore.xcore.XGenericType;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModification;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.eclipse.xtext.validation.Issue;


public class XcoreQuickfixProvider extends DefaultQuickfixProvider
{

  @Fix(EcoreValidator.DIAGNOSTIC_SOURCE + '.' + EcoreValidator.CONSISTENT_TYPE_CLASS_NOT_PERMITTED)
  public void convertToReference(final Issue issue, final IssueResolutionAcceptor acceptor) 
  {
    IModificationContext modificationContext = getModificationContextFactory().createModificationContext(issue);
    final IXtextDocument xtextDocument = modificationContext.getXtextDocument();
    xtextDocument.readOnly
      (new IUnitOfWork.Void<XtextResource>()
       {
         @Override
         public void process(XtextResource xtextResource) throws Exception
         {
           EObject cause = xtextResource.getResourceSet().getEObject(issue.getUriToProblem(), false);
           if (cause instanceof XGenericType)
           {
             XGenericType xGenericType = (XGenericType)cause;
             if (xGenericType.eContainer() instanceof XAttribute && xGenericType.getType() instanceof GenClass)
             {
               ICompositeNode node = NodeModelUtils.getNode(xGenericType.eContainer());
               String range = node == null ? xtextDocument.get(issue.getOffset(), issue.getLength()) : xtextDocument.get(node.getOffset(), node.getLength());
               quickFix(issue, "Convert to cross reference", "refers ", acceptor, range);
               quickFix(issue, "Convert to containment reference", "contains ", acceptor, range);
               quickFix(issue, "Convert to container reference", "container ", acceptor, range);
             }
           }
         }

         private void quickFix(final Issue issue, String description, final String replacement, final IssueResolutionAcceptor acceptor, String range)
         {
           acceptor.accept
              (issue, 
               description,
               replacement + range,
               "full/obj16/correction_change.gif",
               new IModification()
               {
                 public void apply(IModificationContext context) throws BadLocationException
                 {
                   IXtextDocument xtextDocument = context.getXtextDocument();
                   xtextDocument.replace(issue.getOffset(), 0, replacement);
                 }
               });
         }
       });
  }

}
