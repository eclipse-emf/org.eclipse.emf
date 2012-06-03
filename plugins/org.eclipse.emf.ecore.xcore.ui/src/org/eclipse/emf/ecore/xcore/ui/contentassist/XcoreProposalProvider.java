/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.contentassist;


import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xcore.XAttribute;
import org.eclipse.emf.ecore.xcore.XReference;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.services.XcoreGrammarAccess;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.common.types.xtext.ui.JdtVariableCompletions;
import org.eclipse.xtext.common.types.xtext.ui.JdtVariableCompletions.VariableType;
import org.eclipse.xtext.conversion.impl.QualifiedNameValueConverter;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal.IReplacementTextApplier;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;


/**
 */
public class XcoreProposalProvider extends AbstractXcoreProposalProvider
{
  @Inject
  IScopeProvider xcoreScopeProvider;

  @Inject
  private IQualifiedNameConverter qualifiedNameConverter;

  @Inject
  QualifiedNameValueConverter qualifiedNameValueConverter;

  @Inject
  private XcoreMapper mapper;

  @Inject
  XcoreGrammarAccess xcoreGrammarAccess;

  @Inject
  private JdtVariableCompletions completions;

  @Override
  public void completeXReference_Opposite(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor)
  {
    final IReplacementTextApplier textApplier =
      new OppositeReplacementTextApplier
        ((XReference)model,
         context.getViewer(),
         xcoreScopeProvider.getScope(model, XcorePackage.Literals.XREFERENCE__OPPOSITE),
         mapper,
         qualifiedNameConverter,
         qualifiedNameValueConverter);
    ICompletionProposalAcceptor oppositeAware =
      new ICompletionProposalAcceptor.Delegate(acceptor)
      {
        @Override
        public void accept(ICompletionProposal proposal)
        {
          if (proposal instanceof ConfigurableCompletionProposal && textApplier != null)
          {
            ((ConfigurableCompletionProposal)proposal).setTextApplier(textApplier);
          }
          super.accept(proposal);
        }
      };
    super.completeXReference_Opposite(model, assignment, context, oppositeAware);
  }

  @Override
  public void completeXGenericType_Type(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor)
  {
    IScope scope = xcoreScopeProvider.getScope(model, XcorePackage.Literals.XGENERIC_TYPE__TYPE);
    final IReplacementTextApplier textApplier =
      new ImportingTypesProposalProvider.FQNImporter
        (context.getResource(),
         context.getViewer(),
         scope,
         qualifiedNameConverter,
         qualifiedNameValueConverter,
         qualifiedNameValueConverter);
    ICompletionProposalAcceptor scopeAware =
      new ICompletionProposalAcceptor.Delegate(acceptor)
      {
        @Override
        public void accept(ICompletionProposal proposal)
        {
          if (proposal instanceof ConfigurableCompletionProposal && textApplier != null)
          {
            ((ConfigurableCompletionProposal)proposal).setTextApplier(textApplier);
          }
          super.accept(proposal);
        }
      };
    super.completeXGenericType_Type(model, assignment, context, scopeAware);
  }

  @Override
  public void completeXReference_Name(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor)
  {
    completeXStructuralFeature_Name(model, assignment, context, acceptor);
    super.completeXReference_Name(model, assignment, context, acceptor);
  }

  @Override
  public void completeXAttribute_Name(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor)
  {
    completeXStructuralFeature_Name(model, assignment, context, acceptor);
    super.completeXAttribute_Name(model, assignment, context, acceptor);
  }

  protected Set<String> getAllKeywords()
  {
    return GrammarUtil.getAllKeywords(xcoreGrammarAccess.getGrammar());
  }

  public void completeXStructuralFeature_Name(EObject model, Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor)
  {
    if (model instanceof XStructuralFeature)
    {
      XStructuralFeature xStructuralFeature = (XStructuralFeature)model;
      EStructuralFeature eStructuralFeature = (EStructuralFeature)mapper.getEcore(xStructuralFeature);
      Set<String> alreadyTaken = Sets.newHashSet(getAllKeywords());
      if (eStructuralFeature != null)
      {
        EClass eContainingClass = eStructuralFeature.getEContainingClass();
        alreadyTaken.addAll
          (Lists.transform
            (eContainingClass.getEAllStructuralFeatures(),
             new Function<EStructuralFeature, String>()
             {
               public String apply(EStructuralFeature input)
               {
                 String name = input.getName();
                 return name == null ? "_" : name;
               }
             }));
      }
      completions.getVariableProposals
        (model,
         XcorePackage.Literals.XTYPED_ELEMENT__TYPE,
         VariableType.INSTANCE_FIELD,
         alreadyTaken,
         new JdtVariableCompletions.CompletionDataAcceptor()
         {
           public void accept(String replaceText, StyledString label, Image image)
           {
             acceptor.accept(createCompletionProposal(replaceText, label, image, context));
          }
        });
    }
  }

  @Override
  protected void lookupCrossReference(CrossReference crossReference, EReference reference, ContentAssistContext contentAssistContext, ICompletionProposalAcceptor acceptor, Predicate<IEObjectDescription> filter)
  {
    if (reference == XcorePackage.Literals.XREFERENCE__OPPOSITE)
    {
      XReference xReference = (XReference)contentAssistContext.getCurrentModel();
      final EStructuralFeature eReference = mapper.getMapping(xReference).getEStructuralFeature();
      final EClass eClass = eReference.getEContainingClass();
      final Predicate<IEObjectDescription> baseFilter = filter;
      filter = new Predicate<IEObjectDescription>()
        {
          public boolean apply(IEObjectDescription input)
          {
            // Filter out features that aren't of the correct type to be a valid opposite.
            //
            GenFeature genFeature = (GenFeature)input.getEObjectOrProxy();
            EStructuralFeature eStructuralFeature = genFeature.getEcoreFeature();
            return eStructuralFeature.getEType() == eClass && eStructuralFeature != eReference && baseFilter.apply(input);
          }
        };
    }
    else if (reference == XcorePackage.Literals.XGENERIC_TYPE__TYPE)
    {
      EObject eObject = contentAssistContext.getCurrentModel();
      ImmutableList<AbstractElement> firstSetGrammarElements = contentAssistContext.getFirstSetGrammarElements();
      if (firstSetGrammarElements.contains(xcoreGrammarAccess.getXAttributeAccess().getTypeAssignment_3_0_0()) ||
            firstSetGrammarElements.contains(xcoreGrammarAccess.getXAttributeAccess().getNameAssignment_4()) ||
            firstSetGrammarElements.contains(xcoreGrammarAccess.getXGenericTypeAccess().getTypeAssignment_0()) && eObject instanceof XAttribute)
      {
        final Predicate<IEObjectDescription> baseFilter = filter;
        filter = new Predicate<IEObjectDescription>()
          {
            public boolean apply(IEObjectDescription input)
            {
              // Filter out types that aren't data types.
              //
              EObject eObjectOrProxy = input.getEObjectOrProxy();
              return (eObjectOrProxy instanceof GenDataType || eObjectOrProxy instanceof GenTypeParameter) && baseFilter.apply(input);
            }
          };
      }
      else if (firstSetGrammarElements.contains(xcoreGrammarAccess.getXReferenceAccess().getTypeAssignment_4()) ||
                firstSetGrammarElements.contains(xcoreGrammarAccess.getXGenericTypeAccess().getTypeAssignment_0()) && eObject instanceof XReference ||
                firstSetGrammarElements.contains(xcoreGrammarAccess.getXClassAccess().getSuperTypesAssignment_5_1()) ||
                firstSetGrammarElements.contains(xcoreGrammarAccess.getXClassAccess().getSuperTypesAssignment_5_2_1()))
      {
        final Predicate<IEObjectDescription> baseFilter = filter;
        filter = new Predicate<IEObjectDescription>()
          {
            public boolean apply(IEObjectDescription input)
            {
              // Filter out types that aren't classes.
              //
              EObject eObjectOrProxy = input.getEObjectOrProxy();
              return (eObjectOrProxy instanceof GenClass || eObjectOrProxy instanceof GenTypeParameter) && baseFilter.apply(input);
            }
          };
      }
    }
    super.lookupCrossReference(crossReference, reference, contentAssistContext, acceptor, filter);
  }
}
