/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.formatting;


import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.formatter.IFormattingStrategy;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.ReplaceEdit;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.preferences.IPreferenceValues;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.formatting.IContentFormatterFactory;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.eclipse.xtext.xbase.formatting.FormattingPreferenceValues;
import org.eclipse.xtext.xbase.formatting.IBasicFormatter;
import org.eclipse.xtext.xbase.formatting.IFormattingPreferenceValuesProvider;
import org.eclipse.xtext.xbase.formatting.TextReplacement;

import com.google.inject.Inject;


public class XcoreFormatterFactory implements IContentFormatterFactory
{
  @Inject
  private XcoreContentFormatter formatter;

  public static class XcoreContentFormatter implements IContentFormatter
  {
    @Inject
    protected IBasicFormatter formatter;

    @Inject
    private IFormattingPreferenceValuesProvider preferencesProvider;

    public void format(IDocument document, final IRegion region)
    {
      IXtextDocument xtextDocument = (IXtextDocument)document;

      TextEdit textEdit = xtextDocument.readOnly
        (new IUnitOfWork<TextEdit, XtextResource>()
         {
           public TextEdit exec(XtextResource xtextResource) throws Exception
           {
             IParseResult parseResult = xtextResource.getParseResult();
             if (parseResult == null)
             {
               return null;
             }
             else
             {
               IPreferenceValues configuration = preferencesProvider.getPreferenceValues(xtextResource);
               final MultiTextEdit multiTextEdit = new MultiTextEdit();
               try
               {
                 List<TextReplacement> edits = formatter.format(xtextResource, region.getOffset(), region.getLength(), new FormattingPreferenceValues(configuration));
                 for (TextReplacement replacement : edits)
                   multiTextEdit.addChild(new ReplaceEdit(replacement.getOffset(), replacement.getLength(), replacement.getText()));
               }
               catch (Throwable throwable)
               {
                 throw new RuntimeException(throwable);
               }
               return multiTextEdit;
             }
           }
         });

      try
      {
        if (textEdit != null)
        {
          textEdit.apply(document);
        }
      }
      catch (BadLocationException exception)
      {
        throw new RuntimeException(exception);
      }
    }

    public IFormattingStrategy getFormattingStrategy(String contentType)
    {
      return null;
    }
  }

  public IContentFormatter createConfiguredFormatter(SourceViewerConfiguration configuration, ISourceViewer sourceViewer)
  {
    return formatter;
  }
}
