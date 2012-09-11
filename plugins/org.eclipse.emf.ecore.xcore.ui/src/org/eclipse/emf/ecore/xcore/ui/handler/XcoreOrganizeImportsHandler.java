/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.handler;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.xcore.formatting.XcoreImportOrganizer;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Region;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.TextRegion;
import org.eclipse.xtext.util.Tuples;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.inject.Inject;


public class XcoreOrganizeImportsHandler extends AbstractHandler
{
  @Inject
  private XcoreImportOrganizer xcoreImportOrganizer;

  public Object execute(ExecutionEvent event) throws ExecutionException
  {
    XtextEditor editor = EditorUtils.getActiveXtextEditor(event);
    if (editor != null)
    {
      IXtextDocument document = editor.getDocument();
      doOrganizeImports(document);
    }
    return null;
  }

  public void doOrganizeImports(final IXtextDocument document)
  {
    Pair<Region, String> result =
      document.readOnly
        (new IUnitOfWork<Pair<Region, String>, XtextResource>()
         {
           public Pair<Region, String> exec(XtextResource xtextResource) throws Exception
           {
             TextRegion importRegion = xcoreImportOrganizer.getImportRegion(xtextResource);
             if (importRegion != null)
             {
               final String organizedImportSection = xcoreImportOrganizer.getOrganizedImportSection(xtextResource);
               if (organizedImportSection != null)
               {
                return Tuples.create(new Region(importRegion.getOffset(), importRegion.getLength()), organizedImportSection);
               }
             }
             return null;
           }
         });
    if (result != null)
    {
      try
      {
        Region region = result.getFirst();
        String importSection = result.getSecond();
        int offset = region.getOffset();
        int length = region.getLength();
        String string = document.get(offset, length);
        if (!string.equals(importSection))
        {
          document.replace(offset, length, importSection);
        }
      }
      catch (BadLocationException e)
      {
        // Ignore.
      }
    }
  }
}
