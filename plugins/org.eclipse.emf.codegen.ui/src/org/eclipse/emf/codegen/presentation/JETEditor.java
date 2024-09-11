/**
 * Copyright (c) 2019 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.presentation;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.core.filebuffers.IStateValidationSupport;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IEncodedStorage;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.codegen.CodeGenPlugin;
import org.eclipse.emf.codegen.jet.JETAttributeItem;
import org.eclipse.emf.codegen.jet.JETAttributeListItem;
import org.eclipse.emf.codegen.jet.JETCommentItem;
import org.eclipse.emf.codegen.jet.JETCompilationUnit;
import org.eclipse.emf.codegen.jet.JETCompilationUnit.JETJavaRange;
import org.eclipse.emf.codegen.jet.JETCompileTemplateOperation;
import org.eclipse.emf.codegen.jet.JETDirectiveItem;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.codegen.jet.JETExpressionItem;
import org.eclipse.emf.codegen.jet.JETItem;
import org.eclipse.emf.codegen.jet.JETJavaItem;
import org.eclipse.emf.codegen.jet.JETLiteralItem;
import org.eclipse.emf.codegen.jet.JETMark;
import org.eclipse.emf.codegen.jet.JETNature;
import org.eclipse.emf.codegen.jet.JETParser;
import org.eclipse.emf.codegen.jet.JETProblemListener;
import org.eclipse.emf.codegen.jet.JETRootItem;
import org.eclipse.emf.codegen.jet.JETScriptletItem;
import org.eclipse.emf.codegen.jet.JETSkeleton;
import org.eclipse.emf.codegen.jet.JETSubItem;
import org.eclipse.emf.codegen.jet.JETTokenItem;
import org.eclipse.emf.codegen.jet.JETValueElementItem;
import org.eclipse.emf.codegen.jet.JETValueItem;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.CommonUtil;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.SegmentSequence;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.IBufferChangedListener;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.ILocalVariable;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.WorkingCopyOwner;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTRequestor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;
import org.eclipse.jdt.ui.JavaElementImageDescriptor;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jdt.ui.PreferenceConstants;
import org.eclipse.jdt.ui.actions.IJavaEditorActionDefinitionIds;
import org.eclipse.jdt.ui.actions.OpenAction;
import org.eclipse.jdt.ui.refactoring.RenameSupport;
import org.eclipse.jdt.ui.text.IJavaPartitions;
import org.eclipse.jdt.ui.text.java.hover.IJavaEditorTextHover;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.DefaultPositionUpdater;
import org.eclipse.jface.text.DefaultTextHover;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.DocumentRewriteSessionType;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension4;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ISelectionValidator;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextHoverExtension;
import org.eclipse.jface.text.ITextHoverExtension2;
import org.eclipse.jface.text.ITextInputListener;
import org.eclipse.jface.text.ITextPresentationListener;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.text.ITextViewerExtension4;
import org.eclipse.jface.text.ITextViewerExtension5;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.IUndoManager;
import org.eclipse.jface.text.IUndoManagerExtension;
import org.eclipse.jface.text.IViewportListener;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.contentassist.ContentAssistEvent;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.ICompletionListener;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension2;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension3;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension4;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension5;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension6;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.quickassist.IQuickAssistAssistant;
import org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext;
import org.eclipse.jface.text.quickassist.IQuickAssistProcessor;
import org.eclipse.jface.text.quickassist.QuickAssistAssistant;
import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.AnnotationModel;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IAnnotationPresentation;
import org.eclipse.jface.text.source.ICharacterPairMatcher;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.ImageUtilities;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.TextInvocationContext;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.IPostSelectionProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CaretEvent;
import org.eclipse.swt.custom.CaretListener;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.ReplaceEdit;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.text.undo.DocumentUndoEvent;
import org.eclipse.text.undo.DocumentUndoManagerRegistry;
import org.eclipse.text.undo.IDocumentUndoListener;
import org.eclipse.text.undo.IDocumentUndoManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.INavigationLocation;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.ISaveablesLifecycleListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.NavigationLocation;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.SaveablesLifecycleEvent;
import org.eclipse.ui.SubActionBars;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.editors.text.ForwardingDocumentProvider;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.ui.handlers.CollapseAllHandler;
import org.eclipse.ui.handlers.ExpandAllHandler;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IShowInSource;
import org.eclipse.ui.part.ShowInContext;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.ChainedPreferenceStore;
import org.eclipse.ui.texteditor.ContentAssistAction;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.IDocumentProviderExtension;
import org.eclipse.ui.texteditor.IElementStateListener;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.MarkerAnnotation;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.eclipse.ui.texteditor.spelling.SpellingService;
import org.eclipse.ui.themes.ColorUtil;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;


/**
 * @since 2.19
 */
public final class JETEditor extends AbstractDecoratedTextEditor
{
  public static final String ID = "org.eclipse.emf.codegen.presentation.JETEditorID";

  static final String REFACTOR_QUICKMENU_COMMAND_ID = "org.eclipse.codegen.ui.jet.refactor.quickmenu";

  private static final String REFACTOR_QUICKMENU_MENU_ID = "org.eclipse.codegen.ui.jet.refactor";

  static final String SOURCE_QUICKMENU_COMMAND_ID = "org.eclipse.codegen.ui.jet.source.quickmenu";

  static final String SOURCE_QUICKMENU_MENU_ID = "org.eclipse.codegen.ui.jet.source";

  static final String SELECT_ENCLOSING_JET_ELEMENT_ACTION_ID = "SelectEnclosingJETElement";

  static final String EXTRACT_LOCAL_VARIABLE_ACTION_ID = "ExtractLocalVariable";

  static final String GOTO_BRACKET_COMMAND_ID = "org.eclipse.emf.codegen.ui.jet.goto.matching.bracket";

  static final String SELECT_ENCLOSING_JET_ELEMENT_COMMAND_ID = "org.eclipse.emf.codegen.ui.jet.select.enclosing";

  static final String RENAME_COMMAND_ID = "org.eclipse.codegen.ui.jet.rename";

  static final String RENAME_ACTION_ID = "Rename";

  static final String FORMAT_COMMAND_ID = "org.eclipse.codegen.ui.jet.format";

  static final String FORMAT_ACTION_ID = "Format";

  private static boolean trace;

  private final BracketInserter bracketInserter = new BracketInserter(this);

  private final DelegatingTextHover delegatingTextHover = new DelegatingTextHover(this);

  private final JETDocumentManager jetDocumentManager = new JETDocumentManager(this);

  private final JETBracketMatcher jetBracketMatcher = new JETBracketMatcher(this);

  private final List<Runnable> disposables = new ArrayList<Runnable>();

  private final AtomicReference<JETCompilerResult> pendingJETCompilerResult = new AtomicReference<JETCompilerResult>();;

  private JETCompilerResult jetCompilerResult;

  private JavaEditor javaEditor;

  private long expectedModificationStamp;

  private JETContentOutlinePage contentOutlinePage;

  private JETBreadcrumbViewer breadcrumbViewer;

  private Runnable pendingUntilDocumentsAvailable;

  private Runnable dismissLandingPage;

  private boolean isSaving;

  private boolean isShowingContentAssist;

  private TrackedPosition jetPosition = new TrackedPosition(TrackedPosition.Type.JET);

  private TrackedPosition javaPosition = new TrackedPosition(TrackedPosition.Type.JAVA);

  private int[] styleRangeOffsets;

  private JETToken[] jetTokens;

  private SelectionSynchronizer selectionSynchronizer;

  private Runnable deferredInvalidatePresentation;

  private boolean editorContextMenuAboutToShow;

  public JETEditor()
  {
    jetPosition.setOpposite(javaPosition);
  }

  JETNature getJETNature()
  {
    IFile file = getEditorInputFile();
    return file == null ? null : JETNature.getRuntime(file.getProject());
  }

  @Override
  public IEditorInput getEditorInput()
  {
    if (editorContextMenuAboutToShow)
    {
      try
      {
        // Prevent potential stack overflow.
        editorContextMenuAboutToShow = false;
        return jetDocumentManager.getEditorInput();
      }
      finally
      {
        editorContextMenuAboutToShow = true;
      }
    }
    else
    {
      return super.getEditorInput();
    }
  }

  SourceViewer getJETSourceViewer()
  {
    return (SourceViewer)getSourceViewer();
  }

  JavaEditor getJavaEditor()
  {
    return javaEditor;
  }

  SourceViewer getJavaSourceViewer()
  {
    return getJavaEditor().getJavaSourceViewer();
  }

  TrackedPosition getJavaPosition()
  {
    return javaPosition;
  }

  TrackedPosition getJETPosition()
  {
    return jetPosition;
  }

  boolean isJavaEditorInSync()
  {
    return expectedModificationStamp == getModificationStamp();
  }

  long getModificationStamp()
  {
    IDocument document = getSourceViewer().getDocument();
    return document == null ? 0 : ((IDocumentExtension4)document).getModificationStamp();
  }

  void resetExpectedModificationStamp()
  {
    expectedModificationStamp = 0;
  }

  void setExpectedModificationStamp()
  {
    expectedModificationStamp = getModificationStamp();
  }

  JETDocumentManager getDocumentManager()
  {
    return jetDocumentManager;
  }

  int getFileID()
  {
    return jetDocumentManager.getFileID();
  }

  IFile getEditorInputFile()
  {
    return toFile(getEditorInput());
  }

  JETBreadcrumbViewer getBreadcrumbViewer()
  {
    return breadcrumbViewer;
  }

  JETCompilerResult getCompilerResult()
  {
    return jetCompilerResult;
  }

  JETCompilationUnit getCompilationUnit()
  {
    return jetCompilerResult == null ? null : jetCompilerResult.getCompilationUnit();
  }

  void setShowingContentAssist(boolean isShowingContentAssist)
  {
    this.isShowingContentAssist = isShowingContentAssist;
    if (!isShowingContentAssist)
    {
      jetDocumentManager.setEnableJavaSynchronization(true);
    }
  }

  void handleCompilerResult(final JETCompilerResult jetCompilerResult)
  {
    // This is called from the JETDynamicCompiler's thread.
    JETCompilerResult oldJETCompilerResult = pendingJETCompilerResult.getAndSet(jetCompilerResult);

    // If there is not a value that was already pending consumption, dispatch a new runnable to the UI thread.
    if (oldJETCompilerResult == null)
    {
      getSite().getShell().getDisplay().asyncExec(new Runnable()
        {
          public void run()
          {
            // If the pending result is still not consumed by waitForCompilerResult, consume it now.
            JETCompilerResult newJETCompilerResult = pendingJETCompilerResult.getAndSet(null);
            if (newJETCompilerResult != null)
            {
              setCompilerResult(newJETCompilerResult);
            }
          }
        });
    }
  }

  void waitForCompilerResult(long timeInMillis)
  {
    long count = timeInMillis / 100;
    for (int i = 0; i < count; ++i)
    {
      JETCompilerResult jetCompilerResult = pendingJETCompilerResult.getAndSet(null);
      if (jetCompilerResult == null)
      {
        try
        {
          Thread.sleep(100);
        }
        catch (InterruptedException exception)
        {
          Thread.interrupted();
        }
      }
      else
      {
        setCompilerResult(jetCompilerResult);
        break;
      }
    }
  }

  void setCompilerResult(JETCompilerResult jetCompilerResult)
  {
    if (!isShowingContentAssist)
    {
      this.jetCompilerResult = jetCompilerResult;

      jetDocumentManager.setCompilerResult(jetCompilerResult);

      handleProblems(getFileID());

      IFile javaFile = jetCompilerResult.getJavaFile();
      JETCompilationUnit jetCompilationUnit = jetCompilerResult.getCompilationUnit();
      if (javaFile != null)
      {
        final ISourceViewer jetSourceViewer = getSourceViewer();
        setExpectedModificationStamp();

        ISourceViewer javaSourceViewer = getJavaSourceViewer();
        StyledText javaTextWidget = javaSourceViewer.getTextWidget();
        try
        {
          javaTextWidget.setRedraw(false);

          String compilationUnit = jetCompilationUnit.getJavaCompilationUnit();
          javaEditor.setInput(javaFile, compilationUnit);

          IDocument document = javaSourceViewer.getDocument();
          if (document != null)
          {
            String oldDocument = document.get();
            if (!compilationUnit.equals(oldDocument))
            {
              document.set(compilationUnit);
            }
            else
            {
              invalidateVisibleTextPresentation(true);
            }

            // Synchronize the cursor/selection.
            jetSourceViewer.getTextWidget().notifyListeners(SWT.Selection, new Event());
          }
        }
        finally
        {
          javaTextWidget.setRedraw(true);
        }
      }

      if (jetCompilationUnit != null && pendingUntilDocumentsAvailable != null)
      {
        try
        {
          pendingUntilDocumentsAvailable.run();
        }
        finally
        {
          pendingUntilDocumentsAvailable = null;
        }
      }

      if (dismissLandingPage != null)
      {
        dismissLandingPage.run();
      }

      selectionSynchronizer.sync(getSourceViewer());
    }
  }

  List<JETJavaRange> getRanges()
  {
    JETCompilationUnit compilerResult = getCompilationUnit();
    return compilerResult == null ? Collections.<JETJavaRange> emptyList() : compilerResult.getRanges();
  }

  JETItem getJETItem(int templateOffset, boolean leaf)
  {
    JETCompilationUnit compilerResult = getCompilationUnit();
    return compilerResult == null ? null : compilerResult.getJETItem(getFileID(), templateOffset, leaf);
  }

  Position getCorrespondingJavaPosition(int templateOffset, int templateLength)
  {
    JETCompilationUnit compilerResult = getCompilationUnit();
    if (compilerResult != null)
    {
      int[] correspondingJavaPoint = compilerResult.getCorrespondingJavaPoint(getFileID(), templateOffset, templateLength);
      if (correspondingJavaPoint != null)
      {
        return new Position(correspondingJavaPoint[0], correspondingJavaPoint[1]);
      }
    }

    return null;
  }

  int getCorrespondingTemplateFileID(int javaOffset)
  {
    JETCompilationUnit compilerResult = getCompilationUnit();
    return compilerResult == null ? -1 : compilerResult.getCorrespondingTemplateFileID(javaOffset);
  }

  Position getCorrespondingTemplatePosition(int javaOffset, int javaLength)
  {
    JETCompilationUnit compilerResult = getCompilationUnit();
    if (compilerResult != null)
    {
      int[] correspondingTemplatePoint = compilerResult.getCorrespondingTemplatePoint(getFileID(), javaOffset, javaLength);
      if (correspondingTemplatePoint != null)
      {
        return new Position(correspondingTemplatePoint[0], correspondingTemplatePoint[1]);
      }
    }
    return null;
  }

  void handleFileIDChanged(int fileID)
  {
    firePropertyChange(PROP_DIRTY);
    handleProblems(fileID);
  }

  void handleProblems(int fileID)
  {
    if (jetCompilerResult != null)
    {
      applyProblemAnnotations(jetCompilerResult.getProblems(fileID));
    }
  }

  void applyProblemAnnotations(List<JETProblemAnnotation> jetProblemAnnotations)
  {
    SourceViewer sourceViewer = getJETSourceViewer();
    IAnnotationModel annotationModel = sourceViewer.getAnnotationModel();
    List<Annotation> annotationsToRemove = new ArrayList<Annotation>();
    for (Iterator<Annotation> i = annotationModel.getAnnotationIterator(); i.hasNext();)
    {
      Annotation annotation = i.next();
      if (annotation instanceof JETProblemAnnotation)
      {
        annotationsToRemove.add(annotation);
      }
      else if (annotation instanceof MarkerAnnotation && isDirty())
      {
        Position position = annotationModel.getPosition(annotation);
        annotationModel.addAnnotation(new JETProblemAnnotation((MarkerAnnotation)annotation, position), position);
      }
    }

    for (Annotation annotation : annotationsToRemove)
    {
      annotationModel.removeAnnotation(annotation);
    }

    for (JETProblemAnnotation jetProblemAnnotation : jetProblemAnnotations)
    {
      Position position = jetProblemAnnotation.getPosition();
      annotationModel.addAnnotation(jetProblemAnnotation, position);
    }
  }

  @Override
  protected void initializeEditor()
  {
    super.initializeEditor();

    configureInsertMode(SMART_INSERT, true);
    configureInsertMode(INSERT, false);
    configureInsertMode(INSERT, true);
    setInsertMode(SMART_INSERT);

    IPreferenceStore preferenceStore = getPreferenceStore();
    IPreferenceStore jetPreferenceStore = CodeGenUIPlugin.getPlugin().getPreferenceStore();
    ChainedPreferenceStore chainedPreferenceStore = new ChainedPreferenceStore(new IPreferenceStore []{ jetPreferenceStore, preferenceStore })
      {
        @Override
        public boolean getBoolean(String name)
        {
          if ("matchingBrackets".equals(name))
          {
            return true;
          }

          return !SpellingService.PREFERENCE_SPELLING_ENABLED.equals(name) && super.getBoolean(name);
        }
      };
    setPreferenceStore(chainedPreferenceStore);
    setSourceViewerConfiguration(new JETSourceViewerConfiguration(chainedPreferenceStore, this));
  }

  @Override
  public void createPartControl(Composite parent)
  {
    final SashForm sashForm = new SashForm(parent, SWT.VERTICAL);

    final Composite composite = new Composite(sashForm, SWT.NONE);
    GridLayout gridLayout = new GridLayout();
    gridLayout.marginHeight = 0;
    gridLayout.marginWidth = 0;
    gridLayout.verticalSpacing = 0;
    gridLayout.horizontalSpacing = 0;
    composite.setLayout(gridLayout);

    breadcrumbViewer = new JETBreadcrumbViewer(composite, SWT.HORIZONTAL)
      {
        @Override
        public void configureDropDownViewer(TreeViewer viewer, Object input)
        {
          viewer.setLabelProvider(contentOutlinePage.newLabelProvider());
          viewer.setContentProvider(contentOutlinePage.getContentProvider());
        }
      };
    GridData breadcrumbLayoutData = new GridData(SWT.FILL, SWT.FILL, true, false);
    breadcrumbViewer.getControl().setLayoutData(breadcrumbLayoutData);

    Composite jetSourceViewerComposite = new Composite(composite, SWT.NONE);
    jetSourceViewerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    jetSourceViewerComposite.setLayout(new FillLayout());

    super.createPartControl(jetSourceViewerComposite);

    ToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT);

    final ImageDescriptor up = CodeGenUIPlugin.getPlugin().getImage("full/up.png");
    final ImageDescriptor down = CodeGenUIPlugin.getPlugin().getImage("full/down.png");
    final Action toggleAction = new Action("", IAction.AS_PUSH_BUTTON)
      {
        @Override
        public void run()
        {
          if (sashForm.getMaximizedControl() == null)
          {
            sashForm.setMaximizedControl(composite);
            setImageDescriptor(up);
          }
          else
          {
            sashForm.setMaximizedControl(null);
            setImageDescriptor(down);
          }
        }
      };
    toggleAction.setImageDescriptor(down);
    toolBarManager.add(toggleAction);

    ToolBar toolBar = toolBarManager.createControl(composite);

    GridData toolBarLayoutData = new GridData(SWT.CENTER, SWT.BOTTOM, true, false);
    toolBarLayoutData.heightHint = 11;
    toolBar.setLayoutData(toolBarLayoutData);

    StyledText sourceViewerStyledText = getSourceViewer().getTextWidget();
    Color sourceViewerBackground = sourceViewerStyledText.getBackground();
    breadcrumbViewer.getControl().setBackground(sourceViewerBackground);
    composite.setBackground(sourceViewerStyledText.getBackground());
    IMenuManager contextMenu = (IMenuManager)sourceViewerStyledText.getMenu().getData("org.eclipse.jface.action.MenuManager.managerKey");
    contextMenu.addMenuListener(new IMenuListener()
      {
        public void menuAboutToShow(IMenuManager manager)
        {
          editorContextMenuAboutToShow = false;
        }
      });

    JETContentOutlinePage contentOutlinePage = getContentOutlinePage();
    breadcrumbViewer.setLabelProvider(contentOutlinePage.newLabelProvider());
    breadcrumbViewer.setContentProvider(contentOutlinePage.getContentProvider());
    breadcrumbViewer.setInput(contentOutlinePage.itemManager.getRootItem());

    jetDocumentManager.initializeDocumentManager();

    try
    {
      javaEditor = new JavaEditor(this);
      javaEditor.init(getEditorSite(), getEditorInput());

      final Composite javaComposite = new Composite(sashForm, SWT.NONE);
      final StackLayout stackLayout = new StackLayout();
      javaComposite.setLayout(stackLayout);

      javaEditor.createPartControl(javaComposite);

      final Control[] javaEditorChildren = javaComposite.getChildren();

      final ScrolledComposite scrolledComposite = new ScrolledComposite(javaComposite, SWT.V_SCROLL | SWT.H_SCROLL);
      scrolledComposite.setExpandHorizontal(true);
      scrolledComposite.setExpandVertical(true);
      scrolledComposite.setAlwaysShowScrollBars(false);

      final StyledText landingPage = new StyledText(scrolledComposite, SWT.READ_ONLY | SWT.NO_FOCUS);
      landingPage.setBackground(sourceViewerStyledText.getBackground());
      scrolledComposite.setContent(landingPage);

      final Runnable updateLandingPageInfo = new Runnable()
        {
          public void run()
          {
            StyledString landingPageInfo = generateLandingPageInfo(landingPage.getLineDelimiter());
            landingPage.setText(landingPageInfo.toString());
            landingPage.setStyleRanges(landingPageInfo.getStyleRanges());
            landingPage.redraw();

            landingPage.pack(true);

            Point landingPageSize = landingPage.getSize();
            scrolledComposite.setMinWidth(landingPageSize.x);
            scrolledComposite.setMinHeight(landingPageSize.y);
          }
        };
      updateLandingPageInfo.run();

      stackLayout.topControl = scrolledComposite;

      final ImageDescriptor star = CodeGenUIPlugin.getPlugin().getImage("full/star.png");
      final ImageDescriptor blank = CodeGenUIPlugin.getPlugin().getImage("full/blank.png");
      final Action showInfoAction = new Action("", IAction.AS_PUSH_BUTTON)
        {
          @Override
          public void run()
          {
            if (stackLayout.topControl == scrolledComposite)
            {
              stackLayout.topControl = javaEditorChildren[0];
            }
            else
            {
              stackLayout.topControl = scrolledComposite;
              updateLandingPageInfo.run();
            }

            if (sashForm.getMaximizedControl() == composite)
            {
              sashForm.setMaximizedControl(null);
              toggleAction.setImageDescriptor(down);
            }

            javaComposite.layout();
          }
        };
      showInfoAction.setImageDescriptor(star);
      showInfoAction.setId("show-info");
      toolBarManager.add(showInfoAction);

      toolBarManager.update(true);

      class HyperlinkListener implements MouseTrackListener, MouseMoveListener, MouseListener
      {
        final Cursor defaultCursor = landingPage.getCursor();

        final Cursor handCursor = landingPage.getDisplay().getSystemCursor(SWT.CURSOR_HAND);

        final Runnable infoActionAnimator = new Runnable()
          {
            public void run()
            {
              if (hyperLinkRange != null && hyperLinkRange.length == 1)
              {
                showInfoAction.setImageDescriptor(showInfoAction.getImageDescriptor() == blank ? star : blank);
                landingPage.getDisplay().timerExec(300, this);
              }
              else
              {
                showInfoAction.setImageDescriptor(star);
              }
            }
          };

        StyleRange hyperLinkRange;

        public void mouseDoubleClick(MouseEvent event)
        {
          clearHyperlink();
        }

        public void mouseDown(MouseEvent event)
        {
          if (hyperLinkRange != null)
          {
            if (hyperLinkRange.length == 1)
            {
              showInfoAction.run();
            }
            else
            {
              landingPage.getDisplay().asyncExec(new Runnable()
                {
                  final URI documentURI = URI.createPlatformResourceURI(landingPage.getText(hyperLinkRange.start, hyperLinkRange.start + hyperLinkRange.length - 1), true);

                  public void run()
                  {
                    IEditorPart editorPart = open(getEditorSite(), documentURI.toString());
                    if (editorPart instanceof JETEditor)
                    {
                      ((JETEditor)editorPart).selectAndReveal(getEditorInput(), 0, 0);
                    }
                  }
                });
            }
          }

          clearHyperlink();
        }

        public void mouseUp(MouseEvent event)
        {
          clearHyperlink();
        }

        public void mouseEnter(MouseEvent event)
        {
          clearHyperlink();
        }

        public void mouseExit(MouseEvent event)
        {
          clearHyperlink();
        }

        public void mouseHover(MouseEvent event)
        {
        }

        public void mouseMove(MouseEvent event)
        {
          updateStyleRange(event);
        }

        private void clearHyperlink()
        {
          if (hyperLinkRange != null)
          {
            hyperLinkRange.fontStyle = SWT.NORMAL;
            hyperLinkRange.underline = false;
            landingPage.setStyleRange(hyperLinkRange);
            hyperLinkRange = null;
            landingPage.setCursor(defaultCursor);
          }
        }

        @SuppressWarnings("deprecation")
        private int getOffset(MouseEvent event)
        {
          try
          {
            try
            {
              return (Integer)landingPage.getClass().getMethod("getOffsetAtPoint", Point.class).invoke(landingPage, new Point(event.x, event.y));
            }
            catch (Exception exception)
            {
            }

            return landingPage.getOffsetAtLocation(new Point(event.x, event.y));
          }
          catch (IllegalArgumentException exception)
          {
            return -1;
          }
        }

        private void updateStyleRange(MouseEvent event)
        {
          StyleRange oldHyperLinkRange = hyperLinkRange;
          hyperLinkRange = null;
          int offsetAtPoint = getOffset(event);
          if (offsetAtPoint != -1)
          {
            StyleRange styleRange = landingPage.getStyleRangeAtOffset(offsetAtPoint);
            if (styleRange != null)
            {
              if (styleRange.data instanceof StyleRange)
              {
                hyperLinkRange = (StyleRange)styleRange.data;
                hyperLinkRange.underline = true;
                hyperLinkRange.fontStyle = SWT.BOLD;
                landingPage.setStyleRange(hyperLinkRange);
                landingPage.setCursor(handCursor);
                infoActionAnimator.run();
              }
            }
          }

          if (oldHyperLinkRange != null && oldHyperLinkRange != hyperLinkRange)
          {
            oldHyperLinkRange.fontStyle = SWT.NORMAL;
            oldHyperLinkRange.underline = false;
            landingPage.setStyleRange(oldHyperLinkRange);
            if (hyperLinkRange == null)
            {
              landingPage.setCursor(defaultCursor);
            }
          }
        }
      }

      HyperlinkListener hyperlinkListener = new HyperlinkListener();
      landingPage.addMouseTrackListener(hyperlinkListener);
      landingPage.addMouseListener(hyperlinkListener);
      landingPage.addMouseMoveListener(hyperlinkListener);
      dismissLandingPage = new Runnable()
        {
          public void run()
          {
            dismissLandingPage = null;

            JETCompilationUnit compilationUnit = getCompilationUnit();
            if (compilationUnit != null)
            {
              for (JETException problem : compilationUnit.getProblems())
              {
                String problemKey = problem.getProblemKey();
                if (JETProblemListener.FILE_NOT_A_TEMPLATE.equals(problemKey) || JETProblemListener.FILE_NOT_ON_TEMPLATE_SOURCE_PATH.equals(problemKey))
                {
                  return;
                }
              }

              stackLayout.topControl = javaEditorChildren[0];
              toggleAction.run();
              javaComposite.layout();
            }
          }
        };

      selectionSynchronizer = new SelectionSynchronizer(this, javaEditor);

      delegatingTextHover.setEditor(javaEditor);

      new VisibleCaretHandler(sourceViewerStyledText, javaEditor.getJavaSourceViewer().getTextWidget());
    }
    catch (PartInitException exception)
    {
      throw new RuntimeException(exception);
    }

    if (fSourceViewerDecorationSupport != null)
    {
      fSourceViewerDecorationSupport.install(getPreferenceStore());
    }

    new ViewPortHandler(this);

    setAction(SELECT_ENCLOSING_JET_ELEMENT_ACTION_ID, new SelectEnclosingJETElementAction(this));
    setAction(RENAME_ACTION_ID, new JETRenameLocalVariableAction(this));
    setAction(FORMAT_ACTION_ID, new JETFormatAction(this));
  }

  private StyledString generateLandingPageInfo(String lineDelimiter)
  {
    StyledString styledString = new StyledString(lineDelimiter);
    IEditorInput editorInput = getEditorInput();
    IFile file = toFile(editorInput);
    String indent = "      ";
    if (file == null)
    {
      URI uri = toURI(editorInput);
      String name = uri == null ? editorInput.getName() : uri.toString();
      styledString.append(indent);
      styledString.append("The file ");
      styledString.append(name, StyledString.DECORATIONS_STYLER);
      styledString.append(" cannot be compiled because it is not located in the workspace.");
    }
    else if (getJETNature() == null)
    {
      styledString.append(indent);
      styledString.append("The file ");
      styledString.append(file.getFullPath().toString(), StyledString.DECORATIONS_STYLER);
      styledString.append(" cannot be compiled because it is not in a project with a JET nature.");
      styledString.append(lineDelimiter);
      styledString.append(lineDelimiter);

      IJavaProject javaProject = JavaCore.create(file.getProject());
      boolean isWellFormedJavaProject = false;
      try
      {
        isWellFormedJavaProject = javaProject.isStructureKnown();
      }
      catch (JavaModelException e)
      {
      }

      if (!isWellFormedJavaProject)
      {
        styledString.append(indent);
        styledString.append("Furthermore, its containing project ");
        styledString.append(file.getParent().getFullPath().toString(), StyledString.DECORATIONS_STYLER);
        styledString.append(" is not in a project with a properly configured Java nature.");
        styledString.append(lineDelimiter);
        styledString.append(lineDelimiter);
      }

      styledString.append(indent);
      styledString.append("Use ");
      styledString.append(
        "File \u2192 New \u2192 Other... \u2192 Java Emitter Templates \u2192 Convert Projects to JET Projects",
        JETContentOutlinePage.Item.getFontStyler(SWT.ITALIC));
      styledString.append(" to convert a Java project into a JET project.");
      styledString.append(lineDelimiter);
      styledString.append(lineDelimiter);
      styledString.append(indent);
      styledString.append("You will need to reopen this editor after having done so.");
    }
    else if (getJETNature().getJavaSourceContainer() == null)
    {
      styledString.append(indent);
      styledString.append("The file ");
      styledString.append(file.getFullPath().toString(), StyledString.DECORATIONS_STYLER);
      styledString.append(" cannot be compiled because the JET nature is not properly configured.");
      styledString.append(lineDelimiter);
    }
    else
    {
      JETCompilationUnit compilationUnit = getCompilationUnit();
      String className = null;
      boolean notOnSourcePath = false;
      if (compilationUnit != null)
      {
        className = compilationUnit.getClassName();
        for (JETException problem : compilationUnit.getProblems())
        {
          if (JETProblemListener.FILE_NOT_ON_TEMPLATE_SOURCE_PATH.equals(problem.getProblemKey()))
          {
            notOnSourcePath = true;
            break;
          }
        }
      }

      styledString.append(indent);
      styledString.append("The file ");
      styledString.append(file.getFullPath().toString(), StyledString.DECORATIONS_STYLER);
      styledString.append(" is being compiled.");
      styledString.append(lineDelimiter);
      styledString.append(lineDelimiter);

      Styler problemStyler = new Styler()
        {
          @Override
          public void applyStyles(TextStyle textStyle)
          {
            textStyle.foreground = ColorManager.INSTANCE.getForeground("directive");
          }
        };

      String fileExtension = file.getFileExtension();
      boolean isNotTemplate = fileExtension == null || !fileExtension.endsWith("jet");
      if (isNotTemplate)
      {
        styledString.append(indent);
        styledString.append("However, the file is not a root JET template.", problemStyler);
        styledString.append(lineDelimiter);
        styledString.append(lineDelimiter);

        styledString.append(indent);
        styledString.append("A root JET template must have have a file extension that ends with ");
        styledString.append("jet", StyledString.DECORATIONS_STYLER);
        styledString.append(", e.g, ");
        styledString.append("MyTemplate.jet", StyledString.DECORATIONS_STYLER);
        styledString.append(" or ");
        styledString.append("MyTemplate.xmljet", StyledString.DECORATIONS_STYLER);
        styledString.append(".");
        styledString.append(lineDelimiter);
        styledString.append(lineDelimiter);
      }
      else
      {
        if (notOnSourcePath)
        {
          styledString.append(indent);
          styledString.append("The class ");
          if (className != null)
          {
            styledString.append(className, StyledString.DECORATIONS_STYLER);
            styledString.append(' ');
          }
          styledString.append("would be generated in ");
          styledString.append(getJETNature().getJavaSourceContainer().getFullPath().toString(), StyledString.DECORATIONS_STYLER);
          styledString.append(", but the template is not on the template path and it will not be built when the project builds.");
          styledString.append(lineDelimiter);
          styledString.append(lineDelimiter);
        }
        else
        {
          styledString.append(indent);
          styledString.append("The class ");
          if (className != null)
          {
            styledString.append(className, StyledString.DECORATIONS_STYLER);
            styledString.append(' ');
          }
          styledString.append("will be generated in ");
          styledString.append(getJETNature().getJavaSourceContainer().getFullPath().toString(), StyledString.DECORATIONS_STYLER);
          styledString.append(".");
          styledString.append(lineDelimiter);
          styledString.append(lineDelimiter);
        }
      }

      styledString.append(indent);
      styledString.append("The following template path is used:");
      styledString.append(lineDelimiter);
      styledString.append(lineDelimiter);
      List<Object> templateSourceContainers = getJETNature().getTemplateSourceContainers();
      for (Object container : getJETNature().getTemplateContainers())
      {
        String containerName = container instanceof IContainer ? ((IContainer)container).getFullPath().toString() : container.toString();
        styledString.append(indent);
        styledString.append("    ");
        styledString.append("@ ", templateSourceContainers.contains(container) ? new Styler()
          {
            @Override
            public void applyStyles(TextStyle textStyle)
            {
              textStyle.foreground = getSourceViewer().getTextWidget().getBackground();
            }
          } : StyledString.COUNTER_STYLER);
        styledString.append(containerName, StyledString.DECORATIONS_STYLER);
        styledString.append(lineDelimiter);
      }

      styledString.append(lineDelimiter);
      styledString.append(indent);
      styledString.append("Click the tiny ", StyledString.COUNTER_STYLER);
      styledString.append("\u2605", new Styler()
        {
          @Override
          public void applyStyles(TextStyle textStyle)
          {
            textStyle.data = textStyle;
            textStyle.foreground = ColorManager.INSTANCE.getColor(230, 179, 60);
          }
        });
      styledString.append(" icon on the divider above to preview the Java results.", StyledString.COUNTER_STYLER);
      if (isNotTemplate)
      {
        styledString.append("  Unresolved names will not be reported.", problemStyler);
      }

      styledString.append(lineDelimiter);
      styledString.append(lineDelimiter);

      Set<URI> includingTemplates = JETNature.getIncludingTemplates(toURI(file));
      if (!includingTemplates.isEmpty())
      {
        Set<String> values = new TreeSet<String>();
        for (URI includingTemplate : includingTemplates)
        {
          values.add(includingTemplate.isPlatformResource() ? includingTemplate.toPlatformString(true) : includingTemplate.toString());
        }

        styledString.append(indent);
        styledString.append("This template is included by the following templates. Click any of these links to open this template in the including context:");
        styledString.append(lineDelimiter);
        styledString.append(lineDelimiter);
        for (String includingTemplate : values)
        {
          styledString.append(indent);
          styledString.append(indent);
          Styler linkStyler = new Styler()
            {
              @Override
              public void applyStyles(TextStyle textStyle)
              {
                StyledString.DECORATIONS_STYLER.applyStyles(textStyle);
                textStyle.data = textStyle;
              }
            };

          styledString.append(includingTemplate, linkStyler);
          styledString.append(lineDelimiter);
        }
      }
    }

    return styledString;
  }

  void invalidateVisibleTextPresentation(boolean defer)
  {
    ISourceViewer jetSourceViewer = getSourceViewer();
    if (defer)
    {
      final StyledText textWidget = jetSourceViewer.getTextWidget();
      deferredInvalidatePresentation = new Runnable()
        {
          public void run()
          {
            if (deferredInvalidatePresentation == this && !textWidget.isDisposed())
            {
              invalidateVisibleTextPresentation(false);
            }
          }
        };
      textWidget.getDisplay().timerExec(200, deferredInvalidatePresentation);
    }
    else
    {
      deferredInvalidatePresentation = null;
      int start = jetSourceViewer.getTopIndexStartOffset();
      int end = jetSourceViewer.getBottomIndexEndOffset();
      ((ITextViewerExtension2)jetSourceViewer).invalidateTextPresentation(start, end - start);
    }
  }

  @Override
  protected void doSetInput(IEditorInput input) throws CoreException
  {
    super.doSetInput(input);

    jetDocumentManager.handleInputChanged(input);
  }

  @Override
  protected ISourceViewer createSourceViewer(Composite parent, IVerticalRuler ruler, int styles)
  {
    ISourceViewer sourceViewer = super.createSourceViewer(parent, ruler, styles);

    ColorManager.INSTANCE.register(sourceViewer);

    if (sourceViewer instanceof ITextViewerExtension)
    {
      ((ITextViewerExtension)sourceViewer).prependVerifyKeyListener(bracketInserter);
    }

    return sourceViewer;
  }

  @SuppressWarnings("restriction")
  @Override
  protected void createActions()
  {
    super.createActions();

    Action action = new ContentAssistAction(CodeGenUIPlugin.getResourceBundle(), "_UI_ContentAssistProposal_", this);
    String id = ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS;
    action.setActionDefinitionId(id);
    setAction("ContentAssistProposal", action);
    markAsStateDependentAction("ContentAssistProposal", true);

    final IHandlerService service = (IHandlerService)getSite().getService(IHandlerService.class);

    org.eclipse.jdt.internal.ui.actions.JDTQuickMenuCreator refactorMenuCreator = new org.eclipse.jdt.internal.ui.actions.JDTQuickMenuCreator(getJavaEditor())
      {
        @Override
        protected void fillMenu(IMenuManager menu)
        {
          addRefactorActions(menu);
        }
      };

    final IHandlerActivation refactorHandlerActivation = service.activateHandler(REFACTOR_QUICKMENU_COMMAND_ID, refactorMenuCreator.createHandler());

    org.eclipse.jdt.internal.ui.actions.JDTQuickMenuCreator sourceMenuCreator = new org.eclipse.jdt.internal.ui.actions.JDTQuickMenuCreator(getJavaEditor())
      {
        @Override
        protected void fillMenu(IMenuManager menu)
        {
          addSourceActions(menu);
        }
      };

    final IHandlerActivation sourceHandlerActivation = service.activateHandler(SOURCE_QUICKMENU_COMMAND_ID, sourceMenuCreator.createHandler());

    disposables.add(new Runnable()
      {
        public void run()
        {
          service.deactivateHandler(refactorHandlerActivation);
          service.deactivateHandler(sourceHandlerActivation);
        }
      });
  }

  @Override
  protected void editorContextMenuAboutToShow(IMenuManager menu)
  {
    editorContextMenuAboutToShow = true;
    super.editorContextMenuAboutToShow(menu);

    Action colorPreferencesAction = new Action(CodeGenUIPlugin.getPlugin().getString("_UI_JETColorPreferences_label"), CodeGenUIPlugin.getPlugin().getImage("full/obj16/JETFile"))
      {
        @Override
        public void run()
        {
          JETToken token = getTokenAtSelection(0);
          String matchingProperty = "selectColor:" + ColorManager.INSTANCE.getProperty(token);
          PreferenceDialog preferenceDialog = PreferencesUtil.createPreferenceDialogOn(
            getSite().getShell(),
            "org.eclipse.ui.preferencePages.ColorsAndFonts",
            null,
            matchingProperty);
          preferenceDialog.open();
        }
      };

    menu.insertAfter(ITextEditorActionConstants.CONTEXT_PREFERENCES, colorPreferencesAction);

    IContributionItem quickAssist = menu.find(ITextEditorActionConstants.QUICK_ASSIST);
    if (quickAssist != null)
    {
      MenuManager refactorSubmenu = new MenuManager("Refac&tor", REFACTOR_QUICKMENU_MENU_ID);
      refactorSubmenu.setActionDefinitionId(REFACTOR_QUICKMENU_COMMAND_ID);

      addRefactorActions(refactorSubmenu);
      menu.insertAfter(ITextEditorActionConstants.QUICK_ASSIST, refactorSubmenu);

      MenuManager sourceSubmenu = new MenuManager("&Source", SOURCE_QUICKMENU_MENU_ID);
      sourceSubmenu.setActionDefinitionId(SOURCE_QUICKMENU_COMMAND_ID);

      addSourceActions(sourceSubmenu);
      menu.insertAfter(ITextEditorActionConstants.QUICK_ASSIST, sourceSubmenu);
    }
  }

  void addRefactorActions(IMenuManager refactorSubmenu)
  {
    IAction action = getAction(RENAME_ACTION_ID);
    refactorSubmenu.add(action);

    action = javaEditor.getAction(EXTRACT_LOCAL_VARIABLE_ACTION_ID);
    refactorSubmenu.add(action);
  }

  void addSourceActions(IMenuManager sourceSubmenu)
  {
    IAction action = getAction(FORMAT_ACTION_ID);
    sourceSubmenu.add(action);
  }

  @Override
  public boolean isSaveAsAllowed()
  {
    return false;
  }

  @Override
  public void dispose()
  {
    jetDocumentManager.dispose();

    super.dispose();

    if (javaEditor != null)
    {
      ((IWorkbenchPart)javaEditor).dispose();
    }

    if (contentOutlinePage != null)
    {
      contentOutlinePage.dispose();
    }

    javaPosition.dispose();
    jetPosition.dispose();

    for (Runnable runnable : disposables)
    {
      runnable.run();
    }
    disposables.clear();
  }

  @Override
  public void saveState(IMemento memento)
  {
    super.saveState(memento);
    memento.putString("document-id", getDocumentManager().getDocumentID());
  }

  @Override
  protected void doRestoreState(IMemento memento)
  {
    String documentID = memento.getString("document-id");
    if (documentID != null)
    {
      getDocumentManager().setDocumentID(documentID);
    }
    super.doRestoreState(memento);
  }

  @Override
  public INavigationLocation createEmptyNavigationLocation()
  {
    return new JETTextSelectionNavigationLocation(this, false);
  }

  @Override
  public INavigationLocation createNavigationLocation()
  {
    return new JETTextSelectionNavigationLocation(this, true);
  }

  @Override
  protected void editorSaved()
  {
    INavigationLocation[] locations = getSite().getPage().getNavigationHistory().getLocations();
    IEditorInput input = getEditorInput();
    for (int i = 0; i < locations.length; i++)
    {
      if (locations[i] instanceof JETTextSelectionNavigationLocation)
      {
        if (input.equals(locations[i].getInput()))
        {
          JETTextSelectionNavigationLocation location = (JETTextSelectionNavigationLocation)locations[i];
          location.partSaved(this);
        }
      }
    }
  }

  @Override
  protected void performSave(boolean overwrite, IProgressMonitor progressMonitor)
  {
    isSaving = true;
    try
    {
      jetDocumentManager.performSave(overwrite, progressMonitor);
    }
    finally
    {
      isSaving = false;
    }
  }

  @Override
  protected void safelySanityCheckState(IEditorInput input)
  {
    if (!isSaving)
    {
      super.safelySanityCheckState(input);
    }
  }

  @Override
  protected void performRevert()
  {
    jetDocumentManager.performRevert();
  }

  void selectAndReveal(IDocument document, int selectionStart, int selectionLength)
  {
    jetDocumentManager.selectAndReveal(document, selectionStart, selectionLength);
  }

  void selectAndReveal(final JETTextSelectionNavigationLocation navigationLocation, final int selectionStart, final int selectionLength)
  {
    IDocument document = jetDocumentManager.setDocumentID(navigationLocation.getDocumentID());
    if (document != null)
    {
      navigationLocation.restoreDocument(this, document);
      selectAndReveal(selectionStart, selectionLength);
      selectionSynchronizer.sync(getSourceViewer());
    }
    else
    {
      pendingUntilDocumentsAvailable = new Runnable()
        {
          public void run()
          {
            selectAndReveal(navigationLocation, selectionStart, selectionLength);
          }
        };
    }
  }

  void selectAndReveal(final IEditorInput editorInput, final int selectionStart, final int selectionLength)
  {
    String documentID = jetDocumentManager.getDocumentID(editorInput);
    if (documentID != null)
    {
      jetDocumentManager.setDocumentID(documentID);
      selectAndReveal(selectionStart, selectionLength);
      selectionSynchronizer.sync(getSourceViewer());
    }
    else
    {
      pendingUntilDocumentsAvailable = new Runnable()
        {
          public void run()
          {
            selectAndReveal(editorInput, selectionStart, selectionLength);
          }
        };
    }
  }

  @Override
  protected void selectAndReveal(int selectionStart, int selectionLength, int revealStart, int revealLength)
  {
    super.selectAndReveal(selectionStart, selectionLength, revealStart, revealLength);
    invalidateVisibleTextPresentation(false);
  }

  @Override
  public Saveable[] getSaveables()
  {
    return jetDocumentManager.getSaveables();
  }

  @Override
  public boolean isDirty()
  {
    return jetDocumentManager != null && jetDocumentManager.isDirty();
  }

  @Override
  public boolean isEditable()
  {
    return jetDocumentManager.isEditable();
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public Object getAdapter(Class adapter)
  {
    if (adapter.equals(IContentOutlinePage.class))
    {
      return getContentOutlinePage();
    }
    else if (adapter.equals(IShowInSource.class))
    {
      return new IShowInSource()
        {
          public ShowInContext getShowInContext()
          {
            return new ShowInContext(jetDocumentManager.getEditorInput(), getSelectionProvider().getSelection());
          }
        };
    }
    else
    {
      return super.getAdapter(adapter);
    }
  }

  JETContentOutlinePage getContentOutlinePage()
  {
    if (contentOutlinePage == null)
    {
      contentOutlinePage = new JETContentOutlinePage(this);
    }
    return contentOutlinePage;
  }

  JETBracketMatcher getJetBracketMatcher()
  {
    return jetBracketMatcher;
  }

  @Override
  protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support)
  {
    support.setCharacterPairMatcher(jetBracketMatcher);
    support.setMatchingCharacterPainterPreferenceKeys("matchingBrackets", "matchingBracketColor");
    super.configureSourceViewerDecorationSupport(support);
  }

  @Override
  protected void initializeKeyBindingScopes()
  {
    setKeyBindingScopes(new String []{ "org.eclipse.emf.codegen.ui.jetEditorScope" }); //$NON-NLS-1$
  }

  IHyperlink[] detectHyperlinks(ITextViewer textViewer, IRegion region, boolean canShowMultipleHyperlinks)
  {
    List<IHyperlink> hyperlinks = new ArrayList<IHyperlink>();
    JETCompilationUnit compilerResult = getCompilationUnit();
    if (compilerResult != null)
    {
      URI templateURI = toPlatformResourceURI(getEditorInput());
      if (templateURI != null)
      {
        JETItem leafJETItem = compilerResult.getJETItem(getFileID(), region.getOffset(), true);
        if (leafJETItem != null)
        {
          JETItem rootJETItem = leafJETItem.getRoot();
          if (rootJETItem instanceof JETDirectiveItem)
          {
            JETDirectiveItem jetDirectiveItem = (JETDirectiveItem)rootJETItem;
            String directiveName = jetDirectiveItem.getNameItem().getText();
            if ("include".equals(directiveName))
            {
              Object data = jetDirectiveItem.getData(JETDirectiveItem.RESOLVED_INCLUDE);
              if (data != null && Boolean.TRUE.equals(jetDirectiveItem.getData(JETDirectiveItem.RESOLVED_INCLUDE_SUCCESS)))
              {
                JETAttributeItem fileAttribute = jetDirectiveItem.getAttribute("file");
                JETItem valueItem = fileAttribute.getValueToken().getValueItem();
                hyperlinks.add(new JETHyperLink(this, toRegion(valueItem), (Integer)jetDirectiveItem.getData(JETDirectiveItem.RESOLVED_INCLUDE_FILE_ID), (String)data));
              }
            }
            else if ("jet".equals(directiveName))
            {
              JETAttributeItem skeletonAttribute = jetDirectiveItem.getAttribute("skeleton");
              if (skeletonAttribute != null && skeletonAttribute.isAncestor(leafJETItem))
              {
                Object data = jetDirectiveItem.getData(JETDirectiveItem.RESOLVED_SKELETON);
                if (data != null)
                {
                  JETItem valueItem = skeletonAttribute.getValueToken().getValueItem();
                  hyperlinks.add(new JETHyperLink(this, toRegion(valueItem), -1, (String)data));
                }
              }
              JETAttributeItem importsAttribute = jetDirectiveItem.getAttribute("imports");
              if (importsAttribute != null && importsAttribute.isAncestor(leafJETItem) && leafJETItem instanceof JETValueElementItem)
              {
                detectedJavaHyperlinks(hyperlinks, compilerResult, region, canShowMultipleHyperlinks);
              }
            }
            else if ("start".equals(directiveName) || "end".equals(directiveName))
            {
              JETDirectiveItem otherEndItem = (JETDirectiveItem)jetDirectiveItem.getData(JETDirectiveItem.SECTION_OTHER_END);
              if (otherEndItem != null)
              {
                hyperlinks.add(new JETNavigationHyperLink(this, toRegion(jetDirectiveItem.getNameItem()), toRegion(((JETDirectiveItem)otherEndItem).getNameItem())));
              }
            }
          }
          else if (rootJETItem instanceof JETScriptletItem || rootJETItem instanceof JETExpressionItem)
          {
            detectedJavaHyperlinks(hyperlinks, compilerResult, region, canShowMultipleHyperlinks);
          }
        }
      }
    }

    return hyperlinks.isEmpty() ? null : hyperlinks.toArray(new IHyperlink [hyperlinks.size()]);
  }

  void detectedJavaHyperlinks(List<IHyperlink> hyperlinks, JETCompilationUnit compilerResult, IRegion region, boolean canShowMultipleHyperlinks)
  {
    int fileID = getFileID();
    int[] correspondingJavaPoint = compilerResult.getCorrespondingJavaPoint(fileID, region.getOffset(), 0);
    if (correspondingJavaPoint != null)
    {
      javaEditor.selectAndReveal(correspondingJavaPoint[0], 0);
      ISourceViewer javaSourceViewer = javaEditor.getJavaSourceViewer();
      StyledText textWidget = javaSourceViewer.getTextWidget();
      Rectangle bounds = textWidget.getCaret().getBounds();
      textWidget.redraw(bounds.x, bounds.y, bounds.width, bounds.height, false);
      IHyperlinkDetector[] hyperlinkDetectors = javaEditor.getHyperlinkDetectors();
      if (hyperlinkDetectors != null)
      {
        for (IHyperlinkDetector javaHyperlinkDetector : hyperlinkDetectors)
        {
          IHyperlink[] javaHyperlinks = javaHyperlinkDetector.detectHyperlinks(javaSourceViewer, new Region(correspondingJavaPoint[0], 0), canShowMultipleHyperlinks);
          if (javaHyperlinks != null)
          {
            for (IHyperlink javaHyperlink : javaHyperlinks)
            {
              if (!(javaHyperlink instanceof JETHyperLink))
              {
                IRegion hyperlinkRegion = javaHyperlink.getHyperlinkRegion();
                int[] correspondingTemplatePoint = compilerResult.getCorrespondingTemplatePoint(fileID, hyperlinkRegion.getOffset(), hyperlinkRegion.getLength());
                if (correspondingTemplatePoint != null)
                {
                  hyperlinks.add(new JavaHyperLink(this, new Region(correspondingTemplatePoint[0], correspondingTemplatePoint[1]), javaHyperlink));
                }
              }
            }
          }
        }
      }
    }
  }

  void setTokenData(int[] styleRangeOffsets, JETToken[] jetTokens)
  {
    this.styleRangeOffsets = styleRangeOffsets;
    this.jetTokens = jetTokens;
  }

  JETToken getTokenAtSelection(int relativeOffsetFromSelection)
  {
    Point selectedRange = getSourceViewer().getSelectedRange();
    return getTokenAt(selectedRange.x + relativeOffsetFromSelection);
  }

  JETToken getTokenAt(int offset)
  {
    ISourceViewer sourceViewer = getSourceViewer();
    StyledText textWidget = sourceViewer.getTextWidget();
    int charCount = textWidget.getCharCount();
    if (charCount != 0)
    {
      JETTokenRegion tokenRegion = getTokenRegionAt(offset);
      if (tokenRegion != null)
      {
        IToken token = tokenRegion.getToken();
        if (token instanceof JETToken)
        {
          return (JETToken)token;
        }
      }
    }

    return JETContentRule.TOKEN;
  }

  Point getTokenRangeAt(int offset)
  {
    JETTokenRegion tokenRegion = getTokenRegionAt(offset);
    if (tokenRegion != null)
    {
      return new Point(tokenRegion.getOffset(), tokenRegion.getLength());
    }
    return new Point(offset, 0);
  }

  JETTokenRegion getTokenRegionAt(int offset)
  {
    ISourceViewer sourceViewer = getSourceViewer();
    StyledText textWidget = sourceViewer.getTextWidget();
    int charCount = textWidget.getCharCount();
    if (charCount > 0)
    {
      int index = Arrays.binarySearch(styleRangeOffsets, offset);
      if (index < 0)
      {
        index = -index - 2;
      }

      if (index + 1 < styleRangeOffsets.length)
      {
        JETTokenRegion jetTokenRegion = new JETTokenRegion(styleRangeOffsets[index], styleRangeOffsets[index + 1] - styleRangeOffsets[index], jetTokens[index]);
        return jetTokenRegion;
      }
    }

    return null;
  }

  void openDialog(String title, IStatus status)
  {
    if (status != null && !status.isOK())
    {
      String message = status.getMessage();
      if (message.length() == 0)
      {
        Throwable exception = status.getException();
        if (exception != null)
        {
          message = exception.getClass().getName();
        }
      }

      switch (status.getSeverity())
      {
        case IStatus.INFO:
        {
          MessageDialog.openInformation(getSite().getShell(), title, message);
          break;
        }
        case IStatus.WARNING:
        {
          MessageDialog.openWarning(getSite().getShell(), title, message);
          break;
        }
        case IStatus.ERROR:
        default:
        {
          MessageDialog.openError(getSite().getShell(), title, message);
          break;
        }
      }
    }
  }

  static class TrackedPosition extends Position
  {
    private static final String CATEGORY = "__jet_" + TrackedPosition.class.hashCode();

    private static final IPositionUpdater POSITION_UPDATER = new PositionUpdater();

    enum Type
    {
      JET, JAVA
    }

    private IDocument document;

    private final Type type;

    private TrackedPosition opposite;

    public TrackedPosition(Type type)
    {
      this.type = type;
    }

    public void setOpposite(TrackedPosition opposite)
    {
      this.opposite = opposite;
      opposite.opposite = this;
    }

    public TrackedPosition getOpposite()
    {
      return opposite;
    }

    public void addToDocument(IDocument document)
    {
      if (this.document != null)
      {
        uninstallFromDocument(document, this, CATEGORY, POSITION_UPDATER);
      }

      this.document = document;

      setOffset(0);
      setLength(0);
      undelete();

      installOnDocument(document, this, CATEGORY, POSITION_UPDATER);
    }

    public void update(JETJavaItem jetJavaItem)
    {
      if (type == Type.JAVA)
      {
        int javaOffset = jetJavaItem.getJavaOffset();
        int javaLength = jetJavaItem.getJavaLength();
        update(javaOffset, javaLength);
      }
      else
      {
        int jetOffset = jetJavaItem.getStartOffset();
        int jetLength = jetJavaItem.getLength();
        update(jetOffset, jetLength);
      }
    }

    public void update(int offset, int length)
    {
      undelete();
      setOffset(offset);
      setLength(length);
    }

    public String getText()
    {
      if (document != null && !isDeleted())
      {
        try
        {
          return document.get(offset, length);
        }
        catch (BadLocationException exception)
        {
        }
      }
      return null;
    }

    public void dispose()
    {
      if (document != null)
      {
        uninstallFromDocument(document, this, CATEGORY, POSITION_UPDATER);
        document = null;
      }

      delete();
    }

    @Override
    public String toString()
    {
      String toString = super.toString();
      return isDeleted() ? toString : toString + " " + JETItem.toString(getText());
    }

    public static class PositionUpdater implements IPositionUpdater
    {
      protected Position position;

      protected int originalPositionLength;

      protected int originalPositionOffset;

      protected int offset;

      protected int length;

      protected String text;

      protected int replaceLength;

      protected IDocument document;

      public PositionUpdater()
      {
      }

      protected String getCategory()
      {
        return CATEGORY;
      }

      protected void adaptToInsert()
      {
        int myStart = position.offset;
        int myEnd2 = position.offset + position.length;
        int myEnd = position.offset + position.length - 1;
        myEnd = Math.max(myStart, myEnd);

        int yourStart = offset;
        int yourEnd = offset + length;

        if (myStart == yourEnd)
        {
          position.offset = offset;
          position.length = myEnd2 - offset + replaceLength;
        }
        else if (myEnd2 == yourStart)
        {
          position.length += replaceLength;
        }
        else if (myEnd >= yourStart)
        {
          if (myStart < yourStart)
          {
            position.length += replaceLength;
          }
          else
          {
            position.offset += replaceLength;
          }
        }
      }

      protected void adaptToRemove()
      {
        int myStart = position.offset;
        int myEnd = position.offset + position.length - 1;
        myEnd = Math.max(myStart, myEnd);

        int yoursStart = offset;
        int yoursEnd = offset + length - 1;
        yoursEnd = Math.max(yoursStart, yoursEnd);

        if (myEnd >= yoursStart)
        {
          if (myStart <= yoursStart)
          {
            if (yoursEnd <= myEnd)
            {
              position.length -= length;
            }
            else
            {
              position.length -= (myEnd - yoursStart + 1);
            }
          }
          else if (yoursStart < myStart)
          {
            if (yoursEnd < myStart)
            {
              position.offset -= length;
            }
            else
            {
              position.offset -= (myStart - yoursStart);
              position.length -= (yoursEnd - myStart + 1);
            }
          }

          // validate position to allowed values
          if (position.offset < 0)
          {
            position.offset = 0;
          }

          if (position.length < 0)
          {
            position.length = 0;
          }
        }
      }

      protected void adaptToReplace()
      {
        if (length > 0 && position.offset <= offset && offset + length <= position.offset + position.length)
        {
          position.length += replaceLength - length;
        }
        else
        {
          if (length > 0)
          {
            adaptToRemove();
          }

          if (replaceLength > 0)
          {
            adaptToInsert();
          }
        }
      }

      protected boolean notDeleted()
      {
        if (offset < position.offset && position.offset + position.length < offset + length)
        {
          position.delete();
          return false;
        }

        return true;
      }

      public void update(DocumentEvent event)
      {
        try
        {
          document = event.getDocument();
          Position[] category = document.getPositions(CATEGORY);
          if (category.length > 0)
          {
            offset = event.getOffset();
            length = event.getLength();
            text = event.getText();
            if (text == null)
            {
              replaceLength = 0;
              text = "";
            }
            else
            {
              replaceLength = text.length();
            }

            for (Position element : category)
            {
              position = element;
              originalPositionOffset = position.offset;
              originalPositionLength = position.length;

              if (notDeleted())
              {
                adaptToReplace();
              }
            }
          }
        }
        catch (BadPositionCategoryException x)
        {
        }
        finally
        {
          document = null;
          text = null;
        }
      }
    }
  }

  static class JETEditorSaveable extends Saveable
  {
    private JETEditor jetEditor;

    private IEditorInput editorInput;

    private IDocument fDocument;

    public JETEditorSaveable(JETEditor jetEditor, IEditorInput editorInput)
    {
      this.jetEditor = jetEditor;
      this.editorInput = editorInput;
    }

    public IEditorInput getEditorInput()
    {
      return editorInput;
    }

    public void disconnectEditor()
    {
      getAdapter(IDocument.class);
      jetEditor = null;
    }

    @Override
    public String getName()
    {
      return editorInput.getName();
    }

    @Override
    public String getToolTipText()
    {
      return editorInput.getToolTipText();
    }

    @Override
    public ImageDescriptor getImageDescriptor()
    {
      return editorInput.getImageDescriptor();
    }

    @Override
    public void doSave(IProgressMonitor monitor) throws CoreException
    {
      jetEditor.doSave(monitor);
    }

    @Override
    public boolean isDirty()
    {
      return jetEditor.getDocumentProvider().canSaveDocument(editorInput);
    }

    @Override
    public int hashCode()
    {
      Object document = getAdapter(IDocument.class);
      return document == null ? 0 : document.hashCode();
    }

    @Override
    public boolean equals(Object that)
    {
      if (this == that)
      {
        return true;
      }

      if (!(that instanceof Saveable))
      {
        return false;
      }

      Object thisDocument = getAdapter(IDocument.class);
      Object thatDocument = ((Saveable)that).getAdapter(IDocument.class);
      return thisDocument == null ? thatDocument == null : thisDocument.equals(thatDocument);
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Object getAdapter(Class adapter)
    {
      if (adapter == IDocument.class)
      {
        if (fDocument == null)
        {
          IDocumentProvider documentProvider = jetEditor.getDocumentProvider();
          if (documentProvider != null)
          {
            fDocument = documentProvider.getDocument(editorInput);
          }
        }
        return fDocument;
      }

      return super.getAdapter(adapter);
    }
  }

  static class JETDocumentManager
  {
    private final JETEditor jetEditor;

    private final Map<IEditorInput, IDocument> managedDocuments = new LinkedHashMap<IEditorInput, IDocument>();

    private final Map<Integer, IDocument> fileIDs = new LinkedHashMap<Integer, IDocument>();

    private final Map<Integer, String> documentIDs = new LinkedHashMap<Integer, String>();

    private final Set<JETEditorSaveable> saveables = new LinkedHashSet<JETEditorSaveable>();

    private final JavaSynchronizer javaSynchronizer;

    private JETDynamicCompilerJob jetDynamicCompilerJob;

    private int currentFileID;

    private IEditorInput currentEditorInput;

    public JETDocumentManager(JETEditor jetEditor)
    {
      this.jetEditor = jetEditor;
      javaSynchronizer = new JavaSynchronizer(jetEditor);
    }

    public void setEnableJavaSynchronization(boolean enable)
    {
      javaSynchronizer.setEnableJavaSynchronization(enable);
      if (jetDynamicCompilerJob != null)
      {
        jetDynamicCompilerJob.setDisabled(!enable);
      }
    }

    public void initializeDocumentManager()
    {
      jetEditor.getDocumentProvider().addElementStateListener(new IElementStateListener()
        {
          public void elementMoved(Object originalElement, Object movedElement)
          {
          }

          public void elementDirtyStateChanged(Object element, boolean isDirty)
          {
            JETDocumentManager.this.jetEditor.firePropertyChange(PROP_DIRTY);
          }

          public void elementDeleted(Object element)
          {
          }

          public void elementContentReplaced(Object element)
          {
            JETDocumentManager.this.jetEditor.firePropertyChange(PROP_DIRTY);
          }

          public void elementContentAboutToBeReplaced(Object element)
          {
          }
        });

      registerOutlineHandler();
      registerBreadcrumbHandler();

      ISourceViewer jetSourceViewer = jetEditor.getSourceViewer();
      IDocument document = jetSourceViewer.getDocument();
      if (document != null)
      {
        registerDocument(currentEditorInput, document, 0, "~root");
      }

      updateDynamicCompilerJob(currentEditorInput);

      registerSaveable(currentEditorInput);
    }

    protected void registerBreadcrumbHandler()
    {
      jetEditor.getBreadcrumbViewer().addOpenListener(new IOpenListener()
        {
          public void open(OpenEvent event)
          {
            handleItemSelection(event.getSelection());
            jetEditor.setFocus();
          }
        });
    }

    protected void registerOutlineHandler()
    {
      jetEditor.getContentOutlinePage().addSelectionChangedListener(new ISelectionChangedListener()
        {
          public void selectionChanged(SelectionChangedEvent event)
          {
            if (jetEditor.getContentOutlinePage().getControl().isFocusControl())
            {
              handleItemSelection(event.getSelection());
            }
          }
        });
    }

    public void performSave(boolean overwrite, IProgressMonitor progressMonitor)
    {
      List<IEditorInput> changedEditorInputs = new ArrayList<IEditorInput>();
      IDocumentProvider provider = jetEditor.getDocumentProvider();
      try
      {
        for (Map.Entry<IEditorInput, IDocument> entry : managedDocuments.entrySet())
        {
          IEditorInput editorInput = entry.getKey();
          IDocument document = entry.getValue();
          if (provider.canSaveDocument(editorInput))
          {
            provider.aboutToChange(editorInput);
            provider.saveDocument(progressMonitor, editorInput, document, overwrite);
            changedEditorInputs.add(editorInput);
          }
        }

        jetEditor.editorSaved();
      }
      catch (CoreException exception)
      {
        IStatus status = exception.getStatus();
        if (status == null || status.getSeverity() != IStatus.CANCEL)
        {
          jetEditor.handleExceptionOnSave(exception, progressMonitor);
        }
      }
      finally
      {
        for (IEditorInput editorInput : changedEditorInputs)
        {
          provider.changed(editorInput);
        }

        refreshBreadcrumbs();
      }

      if (ResourcesPlugin.getWorkspace().isAutoBuilding() && jetDynamicCompilerJob != null)
      {
        Job job = new Job("Wait for build")
          {
            @Override
            protected IStatus run(IProgressMonitor monitor)
            {
              boolean wasInterrupted = false;
              do
              {
                try
                {
                  getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, monitor);
                  jetDynamicCompilerJob.documentChanged(null);
                  break;
                }
                catch (OperationCanceledException exception)
                {
                  break;
                }
                catch (InterruptedException exception)
                {
                  wasInterrupted = true;
                }
              }
              while (wasInterrupted);
              return Status.OK_STATUS;
            }
          };

        job.setSystem(true);
        job.schedule();
      }
    }

    public void performRevert()
    {
      List<IEditorInput> changedEditorInputs = new ArrayList<IEditorInput>();
      IDocumentProvider provider = jetEditor.getDocumentProvider();
      try
      {
        for (IEditorInput editorInput : managedDocuments.keySet())
        {
          if (provider.canSaveDocument(editorInput))
          {
            provider.aboutToChange(editorInput);
            provider.resetDocument(editorInput);
            changedEditorInputs.add(editorInput);
          }
        }
        jetEditor.editorSaved();
      }
      catch (CoreException exception)
      {
        IStatus status = exception.getStatus();
        if (status == null || status.getSeverity() != IStatus.CANCEL)
        {
          Shell shell = jetEditor.getSite().getShell();
          String title = "Problems while reverting to saved state";
          String msg = "Could not revert to saved state.";
          ErrorDialog.openError(shell, title, msg, exception.getStatus());
        }
      }
      finally
      {
        for (IEditorInput editorInput : changedEditorInputs)
        {
          provider.changed(editorInput);
        }

        refreshBreadcrumbs();
      }
    }

    public Saveable[] getSaveables()
    {
      return saveables.toArray(new Saveable [saveables.size()]);
    }

    public int getFileID()
    {
      return currentFileID;
    }

    public String getDocumentID()
    {
      return getDocumentID(currentFileID);
    }

    private String getDocumentID(int fileID)
    {
      return documentIDs.get(fileID);
    }

    public IDocument getDocument(int fileID)
    {
      return fileIDs.get(fileID);
    }

    String getDocumentID(IEditorInput editorInput)
    {
      for (Map.Entry<Integer, String> entry : this.documentIDs.entrySet())
      {
        String documentID = entry.getValue();
        if (editorInput.equals(getEditorInput(documentID)))
        {
          return documentID;
        }
      }
      return null;
    }

    public int getFileID(IDocument document)
    {
      for (Map.Entry<Integer, IDocument> entry : fileIDs.entrySet())
      {
        if (entry.getValue() == document)
        {
          return entry.getKey();
        }
      }
      return -1;
    }

    public IDocument setDocumentID(String documentID)
    {
      for (Map.Entry<Integer, String> entry : documentIDs.entrySet())
      {
        if (entry.getValue().equals(documentID))
        {
          return setFileID(entry.getKey());
        }
      }

      if (documentID != null)
      {
        IEditorInput editorInput = getEditorInput(documentID);
        int fileID = -1;
        while (fileIDs.containsKey(fileID))
        {
          --fileID;
        }
        getRegisteredDocument(editorInput, fileID, documentID);
        return setFileID(fileID);
      }

      return null;
    }

    public void selectAndReveal(IDocument document, int offset, int length)
    {
      ISourceViewer jetSourceViewer = jetEditor.getSourceViewer();
      if (jetSourceViewer.getDocument() != document)
      {
        for (Map.Entry<Integer, IDocument> entry : fileIDs.entrySet())
        {
          if (entry.getValue() == document)
          {
            basicSetFileID(entry.getKey());
            break;
          }
        }
      }

      if (!jetSourceViewer.overlapsWithVisibleRegion(offset, length))
      {
        jetSourceViewer.resetVisibleRegion();
      }

      jetSourceViewer.setSelectedRange(offset, length);
      jetSourceViewer.revealRange(offset, length);
    }

    public IDocument setFileID(int fileID)
    {
      IDocument document = basicSetFileID(fileID);

      JETContentOutlinePage.Item selectionForFileID = jetEditor.getContentOutlinePage().getSelectionForDocumentID(getDocumentID(fileID));
      if (selectionForFileID != null)
      {
        jetEditor.getBreadcrumbViewer().setInput(selectionForFileID);
        jetEditor.getContentOutlinePage().setSelection(new StructuredSelection(selectionForFileID), true);
      }

      return document;
    }

    public IEditorInput getEditorInput()
    {
      IEditorInput editorInput = getEditorInput(getDocument(currentFileID));
      return editorInput == null ? jetEditor.getEditorInput() : editorInput;
    }

    protected IEditorInput getEditorInput(IDocument document)
    {
      for (Map.Entry<IEditorInput, IDocument> entry : managedDocuments.entrySet())
      {
        if (entry.getValue() == document)
        {
          return entry.getKey();
        }
      }
      return null;
    }

    protected IEditorInput getEditorInput(int fileID)
    {
      if (fileID == 0)
      {
        return jetEditor.getEditorInput();
      }
      else
      {
        String documentID = documentIDs.get(fileID);
        return getEditorInput(documentID);
      }
    }

    private IEditorInput getEditorInput(String documentID)
    {
      String templateURI = documentID.replaceAll("~[0-9]+$", "");
      return toEditorInput(URI.createURI(templateURI));
    }

    protected IDocument basicSetFileID(int fileID)
    {
      IEditorInput editorInput = getEditorInput(fileID);
      IDocument registeredDocument = getRegisteredDocument(editorInput, fileID, documentIDs.get(fileID));

      ISourceViewer jetSourceViewer = jetEditor.getSourceViewer();
      if (registeredDocument != null && registeredDocument != jetSourceViewer.getDocument())
      {
        IAnnotationModel model = jetEditor.getDocumentProvider().getAnnotationModel(editorInput);
        if (model == null)
        {
          model = new AnnotationModel();
        }

        boolean javaEditorInSync = jetEditor.isJavaEditorInSync();
        setDocument(editorInput, registeredDocument, model);
        if (javaEditorInSync)
        {
          jetEditor.setExpectedModificationStamp();
        }

        this.currentFileID = fileID;
        jetEditor.handleFileIDChanged(fileID);

        registerSaveable(editorInput);
        hookDynamicCompilerJob(registeredDocument);
      }
      else if (this.currentFileID != fileID)
      {
        this.currentFileID = fileID;
        jetEditor.handleFileIDChanged(fileID);
      }

      return registeredDocument;
    }

    protected void setDocument(IEditorInput editorInput, IDocument document, IAnnotationModel annotationModel)
    {
      ISourceViewer jetSourceViewer = jetEditor.getSourceViewer();
      jetSourceViewer.setDocument(document, annotationModel);
      IDocumentProvider documentProvider = jetEditor.getDocumentProvider();
      if (documentProvider instanceof IDocumentProviderExtension)
      {
        jetSourceViewer.setEditable(!((IDocumentProviderExtension)documentProvider).isReadOnly(editorInput));
      }
    }

    public boolean isEditable()
    {
      IDocumentProviderExtension documentProvider = (IDocumentProviderExtension)jetEditor.getDocumentProvider();
      IDocument document = fileIDs.get(currentFileID);
      IEditorInput editorInput = getEditorInput(document);
      return editorInput != null && !documentProvider.isReadOnly(editorInput);
    }

    public boolean isDirty(int fileID)
    {
      IDocument document = fileIDs.get(fileID);
      IEditorInput editorInput = getEditorInput(document);
      return editorInput != null && jetEditor.getDocumentProvider().canSaveDocument(editorInput);
    }

    public boolean isDirty()
    {
      IDocumentProvider documentProvider = jetEditor.getDocumentProvider();
      if (documentProvider != null)
      {
        for (IEditorInput editorInput : managedDocuments.keySet())
        {
          if (documentProvider.canSaveDocument(editorInput))
          {
            return true;
          }
        }
      }
      return false;
    }

    protected void registerSaveable(IEditorInput editorInput)
    {
      // Equality checking will prevent duplicates.
      // The first one added will not send a life cycle event because that happens automatically when the part is opened.
      JETEditorSaveable saveable = new JETEditorSaveable(jetEditor, editorInput);
      if (saveables.add(saveable) && saveables.size() > 1)
      {
        ISaveablesLifecycleListener listener = (ISaveablesLifecycleListener)jetEditor.getSite().getService(ISaveablesLifecycleListener.class);
        if (listener != null)
        {
          listener.handleLifecycleEvent(new SaveablesLifecycleEvent(jetEditor, SaveablesLifecycleEvent.POST_OPEN, new Saveable []{ saveable }, false));
        }
      }
    }

    public void handleInputChanged(IEditorInput editorInput)
    {
      ISaveablesLifecycleListener listener = (ISaveablesLifecycleListener)jetEditor.getSite().getService(ISaveablesLifecycleListener.class);
      if (listener != null)
      {
        if (!saveables.isEmpty())
        {
          listener.handleLifecycleEvent(new SaveablesLifecycleEvent(jetEditor, SaveablesLifecycleEvent.POST_CLOSE, getSaveables(), false));
          discardSaveables();
        }

        registerSaveable(editorInput);
      }

      this.currentEditorInput = editorInput;

      updateDynamicCompilerJob(editorInput);
    }

    public void dispose()
    {
      if (jetDynamicCompilerJob != null)
      {
        jetDynamicCompilerJob.cancel();
        for (IDocument document : managedDocuments.values())
        {
          unhookDynamicCompilerJob(document);
        }
      }

      IDocumentProvider documentProvider = jetEditor.getDocumentProvider();
      for (IEditorInput editorInput : managedDocuments.keySet())
      {
        // The source viewer will disconnect itself from the editor's current input so skip that one.
        if (!editorInput.equals(this.currentEditorInput))
        {
          documentProvider.disconnect(editorInput);
        }
      }

      discardSaveables();

      for (IDocument document : managedDocuments.values())
      {
        document.removeDocumentListener(javaSynchronizer);
      }

      managedDocuments.clear();
      fileIDs.clear();
      documentIDs.clear();
    }

    protected void discardSaveables()
    {
      for (JETEditorSaveable saveable : saveables)
      {
        saveable.disconnectEditor();
      }

      saveables.clear();
    }

    protected void updateDynamicCompilerJob(IEditorInput input)
    {
      if (jetDynamicCompilerJob != null)
      {
        jetDynamicCompilerJob.cancel();

        for (IDocument document : managedDocuments.values())
        {
          unhookDynamicCompilerJob(document);
        }

        jetDynamicCompilerJob = null;
      }

      IFile file = toFile(input);
      if (file != null)
      {
        jetDynamicCompilerJob = new JETDynamicCompilerJob(jetEditor, file);
        jetDynamicCompilerJob.schedule();

        for (IDocument document : managedDocuments.values())
        {
          hookDynamicCompilerJob(document);
        }
      }
    }

    protected void hookDynamicCompilerJob(IDocument document)
    {
      document.addDocumentListener(jetDynamicCompilerJob);
    }

    protected void unhookDynamicCompilerJob(IDocument document)
    {
      document.removeDocumentListener(jetDynamicCompilerJob);
    }

    protected void handleItemSelection(ISelection selection)
    {
      if (!selection.isEmpty())
      {
        JETContentOutlinePage contentOutlinePage = jetEditor.getContentOutlinePage();
        int fileID = contentOutlinePage.getFileID(selection);
        basicSetFileID(fileID);

        Region region = contentOutlinePage.getRegion(selection);
        if (region != null)
        {
          jetEditor.selectAndReveal(region.getOffset(), region.getLength());
        }

        JETContentOutlinePage.Item selectedItem = contentOutlinePage.getSelectionForDocumentID(getDocumentID(fileID));
        if (selectedItem != null)
        {
          jetEditor.getBreadcrumbViewer().setInput(selectedItem);
          contentOutlinePage.setSelection(new StructuredSelection(selectedItem), true);
        }
      }
    }

    protected IDocument getRegisteredDocument(IEditorInput editorInput, int fileID, String documentID)
    {
      IDocument document = fileIDs.get(fileID);
      if (document == null && editorInput != null)
      {
        IDocumentProvider documentProvider = jetEditor.getDocumentProvider();
        if (!managedDocuments.containsKey(editorInput))
        {
          try
          {
            documentProvider.connect(editorInput);
          }
          catch (CoreException exception)
          {
            CodeGenUIPlugin.getPlugin().log(exception);
          }
        }

        document = documentProvider.getDocument(editorInput);
        if (document != null)
        {
          registerDocument(editorInput, document, fileID, documentID);
        }
      }

      return document;
    }

    protected void registerDocument(IEditorInput editorInput, IDocument document, int fileID, String documentID)
    {
      managedDocuments.put(editorInput, document);
      fileIDs.put(fileID, document);
      documentIDs.put(fileID, documentID);
      document.addDocumentListener(javaSynchronizer);
      jetEditor.getJETPosition().addToDocument(document);
    }

    public void setCompilerResult(JETCompilerResult jetCompilerResult)
    {
      String currentDocumentID = getDocumentID();

      Set<IDocument> orphanDocuments = new LinkedHashSet<IDocument>(fileIDs.values());

      JETCompilationUnit compilationUnit = jetCompilerResult.getCompilationUnit();
      if (compilationUnit != null)
      {
        fileIDs.clear();
        documentIDs.clear();
        int fileID = 0;
        for (String templateURI : compilationUnit.getTemplateURIs())
        {
          String documentID;
          if (fileID == 0)
          {
            documentID = "~root";
            documentIDs.put(0, documentID);
          }
          else
          {
            documentID = templateURI;
            String collision = documentIDs.put(fileID, templateURI);
            for (int count = 1; collision != null; ++count)
            {
              documentIDs.put(fileID, collision);
              documentID = templateURI + "~" + count;
              collision = documentIDs.put(fileID, documentID);
            }
          }

          IDocument document = managedDocuments.get(toEditorInput(URI.createURI(templateURI)));
          if (document != null)
          {
            fileIDs.put(fileID, document);
            if (documentID.equals(currentDocumentID))
            {
              this.currentFileID = fileID;
            }
          }

          ++fileID;
        }

        // Assign orphaned documents negative file IDs.
        orphanDocuments.removeAll(fileIDs.values());
        if (!orphanDocuments.isEmpty())
        {
          fileID = -1;
          for (IDocument orphanDocument : orphanDocuments)
          {
            fileIDs.put(fileID, orphanDocument);

            for (Map.Entry<IEditorInput, IDocument> entry : managedDocuments.entrySet())
            {
              if (entry.getValue() == orphanDocument)
              {
                URI templateURI = toURI(entry.getKey());
                documentIDs.put(fileID, templateURI.toString());
                break;
              }
            }

            --fileID;
          }
        }
      }

      JETContentOutlinePage contentOutlinePage = jetEditor.getContentOutlinePage();
      contentOutlinePage.setCompilerResult(jetCompilerResult, documentIDs);

      refreshBreadcrumbs();
    }

    private void refreshBreadcrumbs()
    {
      JETBreadcrumbViewer breadcrumbViewer = jetEditor.getBreadcrumbViewer();
      JETContentOutlinePage.Item currentInput = (JETContentOutlinePage.Item)breadcrumbViewer.getInput();
      JETContentOutlinePage.Item newCurrentInput = jetEditor.getContentOutlinePage().getSelectionForDocumentID(getDocumentID(currentFileID));
      if (currentInput != newCurrentInput && newCurrentInput != null)
      {
        breadcrumbViewer.setInput(newCurrentInput);
        jetEditor.getContentOutlinePage().setSelection(new StructuredSelection(newCurrentInput), true);
      }
      else
      {
        breadcrumbViewer.refresh(true);
        for (JETContentOutlinePage.Item item = currentInput; item != null; item = item.getParent())
        {
          breadcrumbViewer.update(item, null);
        }
      }
    }

    static class JavaSynchronizer implements IDocumentListener
    {
      private final JETEditor jetEditor;

      private boolean disabled;

      public JavaSynchronizer(JETEditor jetEditor)
      {
        this.jetEditor = jetEditor;
      }

      public void setEnableJavaSynchronization(boolean enable)
      {
        this.disabled = !enable;
      }

      public void documentChanged(DocumentEvent event)
      {
        if (!disabled)
        {
          int eventOffset = event.getOffset();
          JETTokenRegion tokenRegion = jetEditor.getTokenRegionAt(eventOffset);
          if (tokenRegion != null)
          {
            JETToken token = tokenRegion.getToken();
            if (token == JETScriptletRule.TOKEN || token == JETExpressionRule.TOKEN)
            {
              // Compute the location relative to the start of the Java in the region
              // because as we add characters, we might add beyond the currently corresponding region.
              tokenRegion.shrinkToJava(event.getDocument());
              int tokenJavaStart = tokenRegion.getOffset();
              Position correspondingJavaPosition = jetEditor.getCorrespondingJavaPosition(tokenJavaStart, 0);
              if (correspondingJavaPosition != null)
              {
                try
                {
                  ISourceViewer javaSourceViewer = jetEditor.getJavaSourceViewer();
                  int javaOffset = correspondingJavaPosition.getOffset() + eventOffset - tokenJavaStart;
                  int eventLength = event.getLength();
                  javaSourceViewer.getDocument().replace(javaOffset, eventLength, event.getText());
                  javaSourceViewer.setSelectedRange(javaOffset + 1 - eventLength, 0);
                }
                catch (BadLocationException exception)
                {
                }
              }
            }
          }
        }
      }

      public void documentAboutToBeChanged(DocumentEvent event)
      {
      }
    }
  }

  static class JETContentOutlinePage extends ContentOutlinePage
  {
    private final ItemManager itemManager = new ItemManager();

    private final LabelProvider labelProvider = new LabelProvider();

    private final ContentProvider contentProvider = new ContentProvider();

    private final ISelectionChangedListener jetSourceViewerSynchronizer = new ISelectionChangedListener()
      {
        public void selectionChanged(SelectionChangedEvent event)
        {
          if (treeViewer != null && !treeViewer.getControl().isFocusControl())
          {
            ITextSelection selection = (ITextSelection)event.getSelection();
            int offset = selection.getOffset();
            JETItem jetItem = jetEditor.getJETItem(offset, false);
            if (jetItem != null)
            {
              Item correspondingItem = itemManager.getRootItem().getCorrespondingItem(jetItem);
              if (correspondingItem != null)
              {
                setSelection(new StructuredSelection(correspondingItem), true);
              }
            }
          }
        }
      };

    private final JETEditor jetEditor;

    private TreeViewer treeViewer;

    public JETContentOutlinePage(JETEditor jetEditor)
    {
      this.jetEditor = jetEditor;
    }

    public StyledCellLabelProvider newLabelProvider()
    {
      return new LabelProvider()
        {
          @Override
          protected StyledString getStyledText(Object element)
          {
            StyledString styledText = super.getStyledText(element);
            if (element instanceof Item)
            {
              Item item = (Item)element;
              if (item.isBreadcumbSurrogate())
              {
                int fileID = itemManager.getFileID(item);
                if (jetEditor.getDocumentManager().isDirty(fileID))
                {
                  StyledString newStyledString = new StyledString();
                  newStyledString.append('*');
                  newStyledString.append(styledText);
                  styledText = newStyledString;
                }

                if (fileID == jetEditor.getFileID())
                {
                  styledText.setStyle(0, styledText.length(), Item.getFontStyler(SWT.BOLD));
                }
              }
            }
            return styledText;
          }
        };
    }

    public ITreeContentProvider getContentProvider()
    {
      return contentProvider;
    }

    public Item getSelectionForDocumentID(String documentID)
    {
      return itemManager.getSelectionForDocumentID(documentID);
    }

    public void setSelection(ISelection selection, boolean reveal)
    {
      if (treeViewer != null && !treeViewer.getControl().isFocusControl())
      {
        treeViewer.setSelection(selection, reveal);
      }
    }

    @Override
    public void createControl(Composite parent)
    {
      super.createControl(parent);
      treeViewer = getTreeViewer();
      treeViewer.setLabelProvider(labelProvider);
      treeViewer.setContentProvider(contentProvider);
      treeViewer.setInput(itemManager.getRootItem());
      jetEditor.getSelectionProvider().addSelectionChangedListener(jetSourceViewerSynchronizer);
      registerToolBarActions();
    }

    @SuppressWarnings("restriction")
    private void registerToolBarActions()
    {
      class ExpandAllAction extends Action
      {
        public ExpandAllAction()
        {
          super("", CodeGenUIPlugin.getPlugin().getImage("full/ctool16/ExpandAll.png"));
          setToolTipText("Expand all");
          setActionDefinitionId(ExpandAllHandler.COMMAND_ID);
        }

        @Override
        public void run()
        {
          try
          {
            treeViewer.getControl().setRedraw(false);
            treeViewer.expandAll();
          }
          finally
          {
            treeViewer.getControl().setRedraw(true);
          }
        }
      }

      IToolBarManager toolBarManager = getSite().getActionBars().getToolBarManager();
      toolBarManager.add(new ExpandAllAction());

      org.eclipse.jdt.internal.ui.actions.CollapseAllAction collapseAllAction = new org.eclipse.jdt.internal.ui.actions.CollapseAllAction(treeViewer);
      collapseAllAction.setActionDefinitionId(CollapseAllHandler.COMMAND_ID);
      toolBarManager.add(collapseAllAction);

    }

    @Override
    public void dispose()
    {
      treeViewer = null;
      ISelectionProvider selectionProvider = jetEditor.getSelectionProvider();
      if (selectionProvider != null)
      {
        selectionProvider.removeSelectionChangedListener(jetSourceViewerSynchronizer);
      }
      super.dispose();
    }

    public void setCompilerResult(JETCompilerResult jetCompilerResult, Map<Integer, String> documentIDs)
    {
      itemManager.setCompilerResult(jetCompilerResult, documentIDs);
      if (treeViewer != null)
      {
        treeViewer.refresh();
      }
    }

    public int getFileID(ISelection selection)
    {
      Item item = getSelectedItem(selection);
      return item == null ? 0 : itemManager.getFileID(item);
    }

    public Item getSelectedItem(ISelection selection)
    {
      IStructuredSelection structuredSelection = (IStructuredSelection)selection;
      return (Item)structuredSelection.getFirstElement();
    }

    protected JETItem getSelectedJETItem(ISelection selection)
    {
      Item selectedItem = getSelectedItem(selection);
      return selectedItem == null ? null : selectedItem.getJETItem();
    }

    public Region getRegion(ISelection selection)
    {
      JETItem selectedJETItem = getSelectedJETItem(selection);
      if (selectedJETItem != null)
      {
        int start = selectedJETItem.getStartOffset();
        int stop = selectedJETItem.getStopOffset();
        return new Region(start, stop - start);
      }
      else
      {
        return null;
      }
    }

    private static class LabelProvider extends StyledCellLabelProvider implements ILabelProvider
    {
      private static final Map<Font, Font[]> FONTS = new HashMap<Font, Font[]>();

      @Override
      public void update(ViewerCell cell)
      {
        Object element = cell.getElement();

        StyledString styledString = getStyledText(element);
        String newText = styledString.toString();

        StyleRange[] oldStyleRanges = cell.getStyleRanges();
        StyleRange[] newStyleRanges = isOwnerDrawEnabled() ? styledString.getStyleRanges() : null;

        if (!Arrays.equals(oldStyleRanges, newStyleRanges))
        {
          cell.setStyleRanges(newStyleRanges);
          if (cell.getText().equals(newText))
          {
            cell.setText("");
          }
        }

        cell.setText(newText);
        cell.setImage(getImage(element));

        super.update(cell);
      }

      private FontData[] getFontData(int style)
      {
        FontData[] fontDatas = getViewer().getControl().getFont().getFontData();
        for (int i = 0; i < fontDatas.length; i++)
        {
          fontDatas[i].setStyle(style);
        }
        return fontDatas;
      }

      private Font getFont(int fontStyle)
      {
        if (fontStyle > 0 && fontStyle <= (SWT.BOLD | SWT.ITALIC))
        {
          Font baseFont = getViewer().getControl().getFont();
          Font[] fonts = FONTS.get(baseFont);
          if (fonts == null)
          {
            fonts = new Font [SWT.BOLD | SWT.ITALIC];
            FONTS.put(baseFont, fonts);
          }

          Font font = fonts[fontStyle - 1];
          if (font == null)
          {
            font = new Font(baseFont.getDevice(), getFontData(fontStyle));
            fonts[fontStyle - 1] = font;
          }

          return font;
        }
        else
        {
          return null;
        }
      }

      @Override
      protected StyleRange prepareStyleRange(StyleRange styleRange, boolean applyColors)
      {
        StyleRange preparedStyledRange = super.prepareStyleRange(styleRange, applyColors);
        preparedStyledRange.font = getFont(styleRange.fontStyle);
        return preparedStyledRange;
      }

      public Image getImage(Object element)
      {
        if (element instanceof Item)
        {
          return ((Item)element).getImage();
        }
        return null;
      }

      protected StyledString getStyledText(Object element)
      {
        if (element instanceof Item)
        {
          return ((Item)element).getStyledText();
        }
        if (element instanceof JETItem)
        {
          return new StyledString(((JETItem)element).getText());
        }

        return new StyledString("Root");
      }

      public String getText(Object element)
      {
        return getStyledText(element).toString();
      }
    }

    private static class ContentProvider implements ITreeContentProvider
    {
      public Object[] getElements(Object inputElement)
      {
        return getChildren(inputElement);
      }

      public Object[] getChildren(Object parentElement)
      {
        return ((Item)parentElement).getChildren().toArray();
      }

      public Object getParent(Object element)
      {
        return ((Item)element).getParent();
      }

      public boolean hasChildren(Object element)
      {
        return getChildren(element).length > 0;
      }

      public void dispose()
      {
      }

      public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
      {
      }
    }

    private static class ItemManager extends Item
    {
      private static final Pattern COMMENT_PATTERN = Pattern.compile("^[^\n\r]*\\[([^\\]\n\r]*)\\]");

      private final HashMap<String, Item> managedItems = new HashMap<String, Item>();

      private final HashMap<String, Integer> documementIDsToFileIDs = new HashMap<String, Integer>();

      public ItemManager()
      {
        super("~top", null, "top");
        Item rootItem = createItem(managedItems, "~root", null, "root");
        rootItem.setParent(this);
        documementIDsToFileIDs.put("~root", 0);
      }

      public Item getRootItem()
      {
        return getChildren().get(0);
      }

      public int getFileID(Item item)
      {
        if (item.jetItem != null)
        {
          return item.jetItem.getFileID();
        }
        else if ("orphan".equals(item.type))
        {
          return documementIDsToFileIDs.get(item.signature.substring(0, item.signature.lastIndexOf("~orphan")));
        }
        else
        {
          return 0;
        }
      }

      public Item getSelectionForDocumentID(String documentID)
      {
        Item result = managedItems.get(documentID + "~content");
        if (result == null)
        {
          result = managedItems.get(documentID + "~orphan");
          if (result == null)
          {
            result = managedItems.get(documentID);
          }
        }
        return result;
      }

      public void setCompilerResult(JETCompilerResult comilerResult, Map<Integer, String> documentIDs)
      {
        JETCompilationUnit compilerResult = comilerResult.getCompilationUnit();
        if (compilerResult != null)
        {
          Item newTopItem = createItems(compilerResult, documentIDs);
          reconcile(newTopItem);
          handleProblems(comilerResult);
        }
      }

      @Override
      protected void reconcile(Item newItem)
      {
        super.reconcile(newItem);
        managedItems.clear();
        rebuildMap(managedItems);
      }

      private Item createTopItem(Map<String, Item> managedItems)
      {
        Item topItem = createItem(managedItems, "~top", null, "top");
        Item rootItem = createItem(managedItems, "~root", null, "root");
        rootItem.setParent(topItem);
        return topItem;
      }

      protected Item createItems(JETCompilationUnit compilerResult, Map<Integer, String> documentIDs)
      {
        documementIDsToFileIDs.clear();

        Map<String, Item> newManagedItems = new HashMap<String, Item>();
        Item newTopItem = createTopItem(newManagedItems);
        Item newRootItem = newTopItem.getChildren().get(0);
        List<Item> items = new ArrayList<Item>();
        List<JETItem> jetItems = compilerResult.getItems();
        if (!jetItems.isEmpty())
        {
          newRootItem.setJetItem(jetItems.get(0));
          List<Item> resolvedIncludeSuccessItems = new ArrayList<Item>();
          List<Item> flatContentItems = new ArrayList<Item>();
          for (JETItem jetItem : jetItems)
          {
            if (jetItem instanceof JETDirectiveItem)
            {
              JETDirectiveItem jetDirectiveItem = (JETDirectiveItem)jetItem;
              JETSkeleton skeleton = (JETSkeleton)jetDirectiveItem.getData(JETDirectiveItem.SKELETON);
              if (skeleton != null)
              {
                if (jetDirectiveItem.getLength() != 0)
                {
                  String qualifiedClassName = skeleton.getQualifiedClassName();
                  Item directiveItem = createItem(newManagedItems, qualifiedClassName, jetItem, "jet");
                  directiveItem.setParent(newRootItem);
                  items.add(directiveItem);
                }
              }
              else
              {
                String resolvedInclude = (String)jetDirectiveItem.getData(JETDirectiveItem.RESOLVED_INCLUDE);
                if (resolvedInclude != null)
                {
                  Item includeItem = createItem(newManagedItems, resolvedInclude, jetItem, "include");
                  items.add(includeItem);
                  includeItem.setParent(newRootItem);
                  if (Boolean.TRUE.equals(jetDirectiveItem.getData(JETDirectiveItem.RESOLVED_INCLUDE_SUCCESS)))
                  {
                    resolvedIncludeSuccessItems.add(includeItem);

                    JETItem contentStartItem = (JETItem)jetDirectiveItem.getData(JETDirectiveItem.RESOLVED_INCLUDE_START);
                    Item item = createItem(newManagedItems, documentIDs.get(contentStartItem.getFileID()) + "~content", contentStartItem, "content");
                    item.setParent(includeItem);
                    flatContentItems.add(item);
                  }
                }
              }
            }
            else if (jetItem instanceof JETCommentItem)
            {
              Matcher matcher = COMMENT_PATTERN.matcher(jetItem.getText());
              if (matcher.find())
              {
                String signature = matcher.group(1);
                Item commentItem = createItem(newManagedItems, signature + "~comment", jetItem, "comment");
                items.add(commentItem);
                commentItem.setParent(newRootItem);
              }
            }
          }

          List<Item> originalItems = new ArrayList<Item>(items);
          List<Item> rootItems = refactorIncludes(items, jetItems, newManagedItems);
          refactorInclusion(resolvedIncludeSuccessItems, originalItems, rootItems);

          for (Item item : flatContentItems)
          {
            int fileID = item.getJETItem().getFileID();
            Item indexItem = createItem(newManagedItems, documentIDs.get(fileID) + "~root", item.getJETItem(), "root");
            indexItem.setParent(newRootItem.getParent());
          }

          for (Map.Entry<Integer, String> entry : documentIDs.entrySet())
          {
            int fileID = entry.getKey();
            String documentID = entry.getValue();
            documementIDsToFileIDs.put(documentID, fileID);
            if (fileID < 0)
            {
              Item indexItem = createItem(newManagedItems, documentID + "~orphan", null, "orphan");
              indexItem.setParent(newRootItem.getParent());
            }
          }
        }

        return newTopItem;
      }

      private void refactorInclusion(List<Item> resolvedIncludeSuccessItems, List<Item> items, List<Item> rootItems)
      {
        for (Item item : items)
        {
          JETItem jetItem = item.getJETItem();
          int fileID = jetItem.getFileID();
          if (fileID != 0)
          {
            if (item.getParent().getJETItem().getFileID() != fileID)
            {
              Item inclusionItem = resolvedIncludeSuccessItems.get(fileID - 1);
              item.setParent(inclusionItem);
              rootItems.remove(item);
            }
          }
        }
      }

      private List<Item> refactorIncludes(List<Item> items, List<JETItem> jetItems, Map<String, Item> managedItems)
      {
        List<Item> result = new ArrayList<Item>();
        while (!items.isEmpty())
        {
          Item item = items.remove(0);
          captureChildren(item, items, jetItems, managedItems);
          result.add(item);
        }
        return result;
      }

      private void captureChildren(Item item, List<Item> remainingItems, List<JETItem> jetItems, Map<String, Item> managedItems)
      {
        JETItem jetItem = item.getJETItem();
        if (jetItem instanceof JETDirectiveItem)
        {
          JETDirectiveItem parentJETItem = (JETDirectiveItem)jetItem;
          JETDirectiveItem start = (JETDirectiveItem)parentJETItem.getData(JETDirectiveItem.SECTION_START);
          JETDirectiveItem end = (JETDirectiveItem)parentJETItem.getData(JETDirectiveItem.SECTION_END);
          if (start != null && end != null)
          {
            String signature = item.getSignature();
            createItem(managedItems, signature + "~start", start, "start").setParent(item);

            int startIndex = jetItems.indexOf(start) + 1;
            int endIndex = jetItems.indexOf(end) - 1;
            for (int i = startIndex; i <= endIndex; ++i)
            {
              JETItem jetChildItem = jetItems.get(i);
              if (jetChildItem instanceof JETDirectiveItem || jetChildItem instanceof JETCommentItem)
              {
                for (Item managedItem : managedItems.values())
                {
                  if (managedItem.getJETItem() == jetChildItem && remainingItems.remove(managedItem))
                  {
                    // Recursively capture the children of nested includes.
                    managedItem.setParent(item);
                    captureChildren(managedItem, remainingItems, jetItems, managedItems);
                    break;
                  }
                }
              }
            }

            createItem(managedItems, signature + "~end", end, "end").setParent(item);
          }
        }
      }

      private Item createItem(Map<String, Item> managedItems, String signature, JETItem jetItem, String type)
      {
        String uniqueSignature = signature;
        for (Item item = managedItems.get(uniqueSignature); item != null; item = managedItems.get(uniqueSignature))
        {
          uniqueSignature += "_";
        }

        Item item = new Item(uniqueSignature, jetItem, type);
        managedItems.put(uniqueSignature, item);
        return item;
      }
    }

    private static class Item
    {
      private static final Map<String, Image> IMAGES = new HashMap<String, Image>();

      private static final Map<String, Image> ERROR_IMAGES = new HashMap<String, Image>();

      private static final Map<String, Image> WARNING_IMAGES = new HashMap<String, Image>();

      private static final Map<String, Image> INFO_IMAGES = new HashMap<String, Image>();

      private static final Map<Integer, Map<String, Image>> ALL_IMAGES = new HashMap<Integer, Map<String, Image>>();

      static
      {
        CodeGenUIPlugin plugin = CodeGenUIPlugin.getPlugin();
        IMAGES.put("start", plugin.getActualImage("full/obj16/DirectiveStart"));
        IMAGES.put("end", plugin.getActualImage("full/obj16/DirectiveEnd"));
        IMAGES.put("include", plugin.getActualImage("full/obj16/DirectiveInclude"));
        IMAGES.put("jet", plugin.getActualImage("full/obj16/JET"));
        IMAGES.put("skeleton", plugin.getActualImage("full/obj16/Skeleton"));
        IMAGES.put("nlstring", plugin.getActualImage("full/obj16/NLString"));
        IMAGES.put("comment", plugin.getActualImage("full/obj16/Comment.png"));

        Image content = plugin.getActualImage("full/obj16/IncludeContent");
        IMAGES.put("content", content);
        IMAGES.put("root", content);
        IMAGES.put("orphan", content);

        org.eclipse.jdt.ui.ISharedImages sharedImages = JavaUI.getSharedImages();
        IMAGES.put("package", sharedImages.getImage(org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_PACKAGE));
        IMAGES.put("class", sharedImages.getImage(org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_CLASS));
        IMAGES.put("import", sharedImages.getImage(org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_IMPDECL));

        Point size = new Point(16, 16);
        for (Map.Entry<String, Image> entry : IMAGES.entrySet())
        {
          // It should use JavaElementImageDescriptor.WARNING but that's not available in Helios.
          int info = JavaElementImageDescriptor.WARNING;
          try
          {
            info = (Integer)JavaElementImageDescriptor.class.getField("INFO").get(null);
          }
          catch (Exception exception)
          {
            // Ignore;
          }

          Image image = new JavaElementImageDescriptor(ImageDescriptor.createFromImage(entry.getValue()), info, size).createImage();
          INFO_IMAGES.put(entry.getKey(), image);
        }

        for (Map.Entry<String, Image> entry : IMAGES.entrySet())
        {
          Image image = new JavaElementImageDescriptor(ImageDescriptor.createFromImage(entry.getValue()), JavaElementImageDescriptor.WARNING, size).createImage();
          WARNING_IMAGES.put(entry.getKey(), image);
        }

        for (Map.Entry<String, Image> entry : IMAGES.entrySet())
        {
          Image image = new JavaElementImageDescriptor(ImageDescriptor.createFromImage(entry.getValue()), JavaElementImageDescriptor.ERROR, size).createImage();
          ERROR_IMAGES.put(entry.getKey(), image);
        }

        ALL_IMAGES.put(-1, IMAGES);
        ALL_IMAGES.put(IMarker.SEVERITY_INFO, INFO_IMAGES);
        ALL_IMAGES.put(IMarker.SEVERITY_WARNING, WARNING_IMAGES);
        ALL_IMAGES.put(IMarker.SEVERITY_ERROR, ERROR_IMAGES);
      }

      private static final Styler STRIKE_OUT_STYLER = new Styler()
        {
          @Override
          public void applyStyles(TextStyle textStyle)
          {
            textStyle.strikeout = true;
          }
        };

      private static final Styler[] FONT_STYLERS = new Styler [SWT.BOLD | SWT.ITALIC];

      private static final Styler getFontStyler(final int fontStyle)
      {
        if (fontStyle > 0 && fontStyle < (SWT.BOLD | SWT.ITALIC))
        {
          Styler styler = FONT_STYLERS[fontStyle];
          if (styler == null)
          {
            styler = new Styler()
              {
                @Override
                public void applyStyles(TextStyle textStyle)
                {
                  ((StyleRange)textStyle).fontStyle = fontStyle;
                }
              };
            FONT_STYLERS[fontStyle] = styler;
          }
          return styler;
        }
        else
        {
          return null;
        }
      }

      private final List<Item> children = new ArrayList<Item>();

      private final String type;

      private final String signature;

      private Item parent;

      private JETItem jetItem;

      private int problemSeverity = -1;

      public Item(String signature, JETItem jetItem, String type)
      {
        this.signature = signature;
        this.jetItem = jetItem;
        this.type = type == null ? getDirectiveType(jetItem) : type;

        if ("jet".equals(type))
        {
          createJETChildren();
        }
      }

      public Item getCorrespondingItem(JETItem jetItem)
      {
        for (Item child : children)
        {
          Item result = child.getCorrespondingItem(jetItem);
          if (result != null)
          {
            return result;
          }
        }

        return this.jetItem == jetItem ? this : null;
      }

      public boolean isBreadcumbSurrogate()
      {
        return "root".equals(type) || "orphan".equals(type);
      }

      public int handleProblems(JETCompilerResult compilerResult)
      {
        int maxSeverity = -1;
        for (Item child : children)
        {
          maxSeverity = Math.max(maxSeverity, child.handleProblems(compilerResult));
        }

        if ("class".equals(type))
        {
          int overallSeverity = getJavaProblemSeverity(compilerResult.getJavaProblems());
          maxSeverity = Math.max(maxSeverity, overallSeverity);
        }
        else if ("content".equals(type) || "root".equals(type))
        {
          int overallSeverity = getProblemSeverity(compilerResult.getProblems(jetItem.getFileID()));
          maxSeverity = Math.max(maxSeverity, overallSeverity);
        }
        else if ("jet".equals(type) && Boolean.TRUE.equals(((JETDirectiveItem)jetItem).getData(JETDirectiveItem.MISSING_JET_DIRECTIVE)))
        {
          maxSeverity = IMarker.SEVERITY_ERROR;
        }
        else if ("orphan".equals(type))
        {
          maxSeverity = IMarker.SEVERITY_WARNING;
        }
        else if (jetItem != null)
        {
          int localSeverity = getProblemSeverity(compilerResult.getProblem(jetItem));
          maxSeverity = Math.max(maxSeverity, localSeverity);
        }

        this.problemSeverity = maxSeverity;

        return maxSeverity;
      }

      private static final Method INFO_METHOD;

      static
      {
        Method infoMethod = null;
        try
        {
          infoMethod = IProblem.class.getMethod("isInfo");
        }
        catch (Exception exception)
        {
          // Ignore.
        }
        INFO_METHOD = infoMethod;
      }

      private static boolean isInfo(IProblem problem)
      {
        try
        {
          return (Boolean)INFO_METHOD.invoke(problem);
        }
        catch (Exception exception)
        {
          return false;
        }
      }

      private int getJavaProblemSeverity(List<IProblem> problems)
      {
        int result = -1;
        for (IProblem problem : problems)
        {
          int severity = isInfo(problem) ? IMarker.SEVERITY_INFO : problem.isWarning() ? IMarker.SEVERITY_WARNING : problem.isError() ? IMarker.SEVERITY_ERROR : -1;
          result = Math.max(result, severity);
          if (result == IMarker.SEVERITY_ERROR)
          {
            return IMarker.SEVERITY_ERROR;
          }
        }
        return result;
      }

      private int getProblemSeverity(List<JETProblemAnnotation> problems)
      {
        int result = -1;
        for (JETProblemAnnotation problem : problems)
        {
          result = Math.max(result, problem.getSeverity());
          if (result == IMarker.SEVERITY_ERROR)
          {
            return IMarker.SEVERITY_ERROR;
          }
        }
        return result;
      }

      protected void rebuildMap(HashMap<String, Item> managedItems)
      {
        managedItems.put(signature, this);
        for (Item child : children)
        {
          child.rebuildMap(managedItems);
        }
      }

      protected void reconcile(Item newItem)
      {
        // Remove all the existing children.
        Item[] existingChildren = children.toArray(new Item [children.size()]);
        Map<String, Item> signatures = new HashMap<String, Item>();
        for (Item child : existingChildren)
        {
          child.setParent(null);
          signatures.put(child.getSignature(), child);
        }

        Item[] newChildren = newItem.children.toArray(new Item [newItem.children.size()]);
        for (Item newChild : newChildren)
        {
          Item correspondingOldChild = signatures.get(newChild.getSignature());
          if (correspondingOldChild == null)
          {
            // Add the new child.
            newChild.setParent(this);
          }
          else
          {
            // Add the old child back in, transfer the new JET item and recursively reconcile its children.
            correspondingOldChild.setParent(this);
            correspondingOldChild.jetItem = newChild.jetItem;
            correspondingOldChild.reconcile(newChild);
          }
        }
      }

      private void createJETChildren()
      {
        JETDirectiveItem jetDirectiveItem = (JETDirectiveItem)jetItem;
        if (!Boolean.TRUE.equals(jetDirectiveItem.getData(JETDirectiveItem.DUPLICATE_JET_DIRECTIVE)))
        {
          JETAttributeItem packageAttributeItem = jetDirectiveItem.getAttribute("package");
          Item rootItem = this;
          if (packageAttributeItem != null)
          {
            JETValueItem packageValueItem = packageAttributeItem.getValueToken().getValueItem();
            Item packageItem = new Item(signature + "~package", packageValueItem, "package");
            packageItem.setParent(this);
            rootItem = packageItem;
          }

          JETAttributeItem classAttributeItem = jetDirectiveItem.getAttribute("class");
          if (classAttributeItem != null)
          {
            JETValueItem classValueItem = classAttributeItem.getValueToken().getValueItem();
            Item classItem = new Item(rootItem.getSignature() + "~class", classValueItem, "class");
            classItem.setParent(rootItem);
            rootItem = classItem;
          }

          JETAttributeItem importsAttribute = jetDirectiveItem.getAttribute("imports");
          if (importsAttribute != null)
          {
            for (JETValueElementItem jetImportItem : importsAttribute.getValueToken().getValueItem().getElements())
            {
              String importValue = jetImportItem.getValue();
              Item importItem = new Item(rootItem.getSignature() + importValue + "~import", jetImportItem, "import");
              importItem.setParent(rootItem);
            }
          }

          JETAttributeItem skeletonAttribute = jetDirectiveItem.getAttribute("skeleton");
          if (skeletonAttribute != null)
          {
            JETValueItem skeletonValueItem = skeletonAttribute.getValueToken().getValueItem();
            Item skeletonItem = new Item(signature + "~skeleton", skeletonValueItem, "skeleton");
            skeletonItem.setParent(this);
          }

          JETAttributeItem nlString = jetDirectiveItem.getAttribute("nlString");
          if (nlString != null)
          {
            JETValueItem nlStringValue = nlString.getValueToken().getValueItem();
            Item skeletonItem = new Item(signature + "~nlstring", nlStringValue, "nlstring");
            skeletonItem.setParent(this);
          }
        }
      }

      protected void setJetItem(JETItem jetItem)
      {
        this.jetItem = jetItem;
      }

      public Item getParent()
      {
        return parent;
      }

      protected void setParent(Item parent)
      {
        if (this.parent != null)
        {
          this.parent.children.remove(this);
        }

        this.parent = parent;
        if (parent != null)
        {
          parent.children.add(this);
        }
      }

      public String getSignature()
      {
        return signature;
      }

      public JETItem getJETItem()
      {
        return jetItem;
      }

      public Image getImage()
      {
        return ALL_IMAGES.get(problemSeverity).get(type);
      }

      public List<Item> getChildren()
      {
        return children;
      }

      public StyledString getStyledText()
      {
        if ("jet".equals(type))
        {
          JETDirectiveItem jetDirectiveItem = (JETDirectiveItem)jetItem;
          JETSkeleton skeleton = (JETSkeleton)jetDirectiveItem.getData(JETDirectiveItem.SKELETON);
          String className = skeleton.getClassName();
          String packageName = skeleton.getPackageName();
          StyledString result = new StyledString(className);
          if (packageName.length() != 0)
          {
            result.append(" - " + packageName, StyledString.DECORATIONS_STYLER);
          }
          if (Boolean.TRUE.equals(jetDirectiveItem.getData(JETDirectiveItem.DUPLICATE_JET_DIRECTIVE)))
          {
            result.setStyle(0, result.length(), STRIKE_OUT_STYLER);
          }
          return result;
        }
        else if ("include".equals(type))
        {
          JETDirectiveItem jetDirectiveItem = (JETDirectiveItem)jetItem;
          boolean resolvedIncludeSuccess = Boolean.TRUE.equals(jetDirectiveItem.getData(JETDirectiveItem.RESOLVED_INCLUDE_SUCCESS));
          return new StyledString(
            jetDirectiveItem.getAttribute("file").getValueToken().getValueItem().getText(),
            resolvedIncludeSuccess
              ? jetDirectiveItem.isSkipped() ? STRIKE_OUT_STYLER : getFontStyler(SWT.BOLD) : getChildren().isEmpty() ? STRIKE_OUT_STYLER : getFontStyler(SWT.ITALIC));
        }
        else if ("content".equals(type) || "root".equals(type) || "orphan".equals(type))
        {
          if (jetItem == null && !"orphan".equals(type))
          {
            return new StyledString("Analyzing...");
          }
          else
          {
            JETMark start = jetItem == null ? null : jetItem.getStart();
            URI uri = URI.createURI(start == null ? signature.substring(0, signature.lastIndexOf("~orphan")) : start.getResolvedURI());
            String fileName = uri.lastSegment();
            StyledString result = new StyledString(fileName);

            URI baseURI = uri.trimSegments(1);
            String relativeLocation = baseURI.isPlatformResource() ? baseURI.toPlatformString(true) : baseURI.toString();

            if (start != null)
            {
              while (start.getParentMark() != null)
              {
                start = start.getParentMark();
              }
            }

            URI rootURI = URI.createURI(start == null ? parent.getChildren().get(0).jetItem.getStart().getResolvedURI() : start.getResolvedURI());
            if (rootURI.isPlatformResource() && uri.isPlatformResource())
            {
              URI deresolvedURI = uri.deresolve(rootURI, true, true, false);
              if (deresolvedURI.segmentCount() > 1 && !"..".equals(deresolvedURI.segment(0)))
              {
                relativeLocation = deresolvedURI.trimSegments(1).toString();
              }
            }

            result.append(" - " + relativeLocation, StyledString.DECORATIONS_STYLER);
            if ("content".equals(type) && ((JETDirectiveItem)parent.jetItem).isSkipped() || "orphan".equals(type))
            {
              result.setStyle(0, result.length(), STRIKE_OUT_STYLER);
            }

            return result;
          }
        }
        else if ("top".equals(type))
        {
          return new StyledString("top");
        }
        else if ("start".equals(type) || "end".equals(type))
        {
          JETDirectiveItem jetDirectiveItem = (JETDirectiveItem)jetItem;
          return new StyledString(jetItem.getText(), jetDirectiveItem.isSkipped() ? STRIKE_OUT_STYLER : null);
        }
        else if ("comment".equals(type))
        {
          return new StyledString(signature.substring(0, signature.lastIndexOf("~comment")));
        }

        if (jetItem instanceof JETValueItem)
        {
          return new StyledString(((JETValueItem)jetItem).getValue());
        }

        if (jetItem instanceof JETValueElementItem)
        {
          return new StyledString(((JETValueElementItem)jetItem).getValue());
        }

        return new StyledString(jetItem == null ? "" : jetItem.getText());
      }

      private static String getDirectiveType(JETItem jetItem)
      {
        if (jetItem instanceof JETDirectiveItem)
        {
          return ((JETDirectiveItem)jetItem).getNameItem().getText();
        }
        else
        {
          return null;
        }
      }
    }
  }

  static class ViewPortHandler implements IViewportListener, Runnable
  {
    private final JETEditor jetEditor;

    private final StyledText jetStyledText;

    private int topIndex;

    public ViewPortHandler(JETEditor jetEditor)
    {
      this.jetEditor = jetEditor;
      ISourceViewer jetSourceViewer = jetEditor.getSourceViewer();
      jetSourceViewer.addViewportListener(this);
      jetStyledText = jetSourceViewer.getTextWidget();
    }

    public void viewportChanged(int verticalOffset)
    {
      topIndex = jetStyledText.getTopIndex();
      jetStyledText.getDisplay().timerExec(200, this);
    }

    public void run()
    {
      if (!jetStyledText.isDisposed() && topIndex == jetStyledText.getTopIndex())
      {
        jetEditor.invalidateVisibleTextPresentation(false);
      }
    }
  }

  static class JavaHyperLink implements IHyperlink
  {
    private final JETEditor jetEditor;

    private final IRegion region;

    private final IHyperlink javaHyperlink;

    public JavaHyperLink(JETEditor jetEditor, IRegion region, IHyperlink javaHyperlink)
    {
      this.jetEditor = jetEditor;
      this.region = region;
      this.javaHyperlink = javaHyperlink;
    }

    public IRegion getHyperlinkRegion()
    {
      return region;
    }

    public String getTypeLabel()
    {
      return javaHyperlink.getTypeLabel();
    }

    public String getHyperlinkText()
    {
      return javaHyperlink.getHyperlinkText();
    }

    public void open()
    {
      jetEditor.markInNavigationHistory();
      javaHyperlink.open();
    }
  }

  static class JETHyperLink implements IHyperlink
  {
    private final IRegion region;

    private JETEditor jetEditor;

    private final int fileID;

    private final String locationURI;

    public JETHyperLink(JETEditor jetEditor, IRegion region, int fileID, String locationURI)
    {
      this.jetEditor = jetEditor;
      this.region = region;
      this.fileID = fileID;
      this.locationURI = locationURI;
    }

    public IRegion getHyperlinkRegion()
    {
      return region;
    }

    public String getTypeLabel()
    {
      return "Open";
    }

    public String getHyperlinkText()
    {
      URI uri = URI.createURI(locationURI);
      return "Open JET " + (uri.isPlatformResource() ? uri.toPlatformString(true) : uri.toString());
    }

    public void open()
    {
      jetEditor.markInNavigationHistory();
      if (fileID == -1)
      {
        JETEditor.open(jetEditor.getEditorSite(), locationURI);
      }
      else
      {
        jetEditor.getDocumentManager().setFileID(fileID);
        Position correspondingTemplatePoint = jetEditor.getCorrespondingTemplatePosition(region.getOffset(), region.getLength());
        if (correspondingTemplatePoint != null)
        {
          jetEditor.selectAndReveal(correspondingTemplatePoint.offset, correspondingTemplatePoint.length);
        }
        else
        {
          jetEditor.markInNavigationHistory();
        }
      }
    }
  }

  static class JETNavigationHyperLink implements IHyperlink
  {
    protected final JETEditor jetEditor;

    private final IRegion region;

    protected final IRegion targetRegion;

    public JETNavigationHyperLink(JETEditor jetEditor, IRegion region, IRegion targetRegion)
    {
      this.jetEditor = jetEditor;
      this.region = region;
      this.targetRegion = targetRegion;
    }

    public IRegion getHyperlinkRegion()
    {
      return region;
    }

    public String getTypeLabel()
    {
      return "Navigate";
    }

    public String getHyperlinkText()
    {
      return "Navigate in Template";
    }

    public void open()
    {
      jetEditor.markInNavigationHistory();
      jetEditor.selectAndReveal(targetRegion.getOffset(), targetRegion.getLength());
    }
  }

  static class SelectionSynchronizer
  {
    private final JETEditor jetEditor;

    private final JavaEditor javaEditor;

    private final ISourceViewer jetUnderlyingSourceViewer;

    private final ISourceViewer javaUnderlyingSourceViewer;

    private CaretAndSelectionListener jetToJava;

    private CaretAndSelectionListener javaToJET;

    public SelectionSynchronizer(JETEditor jetEditor, final JavaEditor javaEditor)
    {
      this.jetEditor = jetEditor;
      this.javaEditor = javaEditor;

      this.jetUnderlyingSourceViewer = jetEditor.getSourceViewer();
      this.javaUnderlyingSourceViewer = javaEditor.getJavaSourceViewer();

      jetToJava = new CaretAndSelectionListener(jetUnderlyingSourceViewer, javaUnderlyingSourceViewer);
      javaToJET = new CaretAndSelectionListener(javaUnderlyingSourceViewer, jetUnderlyingSourceViewer);
    }

    public void sync(ISourceViewer viewer)
    {
      (viewer == jetUnderlyingSourceViewer ? jetToJava : javaToJET).handle();
    }

    class CaretAndSelectionListener implements CaretListener, SelectionListener
    {
      private final ISourceViewer source;

      private final ISourceViewer target;

      public CaretAndSelectionListener(ISourceViewer source, ISourceViewer target)
      {
        this.source = source;
        this.target = target;
        StyledText textWidget = source.getTextWidget();
        textWidget.addCaretListener(this);
        textWidget.addSelectionListener(this);
      }

      public void widgetSelected(SelectionEvent event)
      {
        if (isEnabled(event))
        {
          if (event.widget == javaUnderlyingSourceViewer.getTextWidget() && javaEditor.isNavigating())
          {
            Point javaSelectedRange = source.getSelectedRange();
            int javaOffset = javaSelectedRange.x;
            int correspondingTemplateFileID = jetEditor.getCorrespondingTemplateFileID(javaOffset);
            if (correspondingTemplateFileID != -1 && correspondingTemplateFileID != jetEditor.getFileID())
            {
              jetEditor.getDocumentManager().setFileID(correspondingTemplateFileID);
            }
          }

          handle();
        }
      }

      public void widgetDefaultSelected(SelectionEvent event)
      {
        if (isEnabled(event))
        {
          handle();
        }
      }

      public void caretMoved(CaretEvent event)
      {
        if (isEnabled(event))
        {
          // A caret move event doesn't generate a selection event if the selection was empty and is also now empty.
          // Furthermore, the queried selection range is stale during the notification, so we must post an event check the range again.
          final StyledText styledText = (StyledText)event.widget;
          if (styledText.getSelectionRange().y == 0)
          {
            event.widget.getDisplay().asyncExec(new Runnable()
              {
                public void run()
                {
                  // If the range is still empty, we need to generate a notification so that the cursors can stay in sync.
                  Point selection = styledText.getSelectionRange();
                  if (selection.y == 0)
                  {
                    Event fakeEvent = new Event();
                    fakeEvent.x = fakeEvent.y = selection.x;
                    styledText.notifyListeners(SWT.Selection, fakeEvent);
                  }
                }
              });
          }
        }
      }

      protected boolean isEnabled(TypedEvent event)
      {
        if (((Control)event.widget).isFocusControl())
        {
          // Don't synchronize anything if the document contents are out of sync with the Java contents.
          return jetEditor.isJavaEditorInSync();
        }
        else
        {
          return event.widget == javaUnderlyingSourceViewer.getTextWidget() && javaEditor.isNavigating();
        }
      }

      protected void handle()
      {
        Point selectedRange = source.getSelectedRange();

        if (source == jetUnderlyingSourceViewer)
        {
          JETItem startItem = jetEditor.getJETItem(selectedRange.x, false);
          JETItem endItem = jetEditor.getJETItem(selectedRange.x + selectedRange.y, false);

          TrackedPosition jetPosition = jetEditor.getJETPosition();
          if (startItem == endItem && startItem instanceof JETJavaItem && !(startItem instanceof JETLiteralItem))
          {
            JETJavaItem jetJavaItem = (JETJavaItem)startItem;
            jetPosition.update(jetJavaItem);
            jetEditor.getJavaPosition().update(jetJavaItem);
          }
          else
          {
            int start = startItem == null ? selectedRange.x : startItem.getStartOffset();
            int end = endItem == null ? startItem == null ? selectedRange.x + selectedRange.y : startItem.getStopOffset() : endItem.getStopOffset();
            int length = end - start;

            if (start != 0 && startItem instanceof JETLiteralItem)
            {
              JETToken token = jetEditor.getTokenAt(start - 1);
              if (token == JETDirectiveRule.TOKEN || token == JETCommentRule.TOKEN || token == JETScriptletRule.TOKEN)
              {
                // These items all consume the line delimiter that immediately follows.
                IDocument document = jetUnderlyingSourceViewer.getDocument();
                try
                {
                  char character = document.getChar(start);
                  if (character == '\n' || character == '\r')
                  {
                    ++start;
                    --length;
                    if (character == '\r' && document.getChar(start) == '\n')
                    {
                      ++start;
                      --length;
                    }
                  }
                }
                catch (BadLocationException exception)
                {
                }
              }
            }

            if (endItem instanceof JETLiteralItem)
            {
              // Always exclude the final line delimiter.
              IDocument document = jetUnderlyingSourceViewer.getDocument();
              try
              {
                char character = document.getChar(start + length);
                if (character == '\n' || character == '\r')
                {
                  --length;
                  if (character == '\n' && document.getChar(start) == '\r')
                  {
                    --length;
                  }
                }
              }
              catch (BadLocationException exception)
              {
              }
            }

            jetPosition.update(start, length);
          }

          jetUnderlyingSourceViewer.setRangeIndication(jetPosition.getOffset(), jetPosition.getLength(), false);
        }

        Position correspondingPoint = source == jetUnderlyingSourceViewer
          ? jetEditor.getCorrespondingJavaPosition(selectedRange.x, selectedRange.y) : jetEditor.getCorrespondingTemplatePosition(selectedRange.x, selectedRange.y);
        if (correspondingPoint != null)
        {
          Position correspondingPointEnd = null;
          for (int i = 0; i < 100 && correspondingPointEnd == null && selectedRange.y > 0; ++i, --selectedRange.y)
          {
            int endOffset = selectedRange.x + selectedRange.y;
            correspondingPointEnd = source == jetUnderlyingSourceViewer
              ? jetEditor.getCorrespondingJavaPosition(endOffset, 0) : jetEditor.getCorrespondingTemplatePosition(endOffset, 0);
          }

          int length = correspondingPointEnd == null ? correspondingPoint.length : correspondingPointEnd.offset + correspondingPointEnd.length - correspondingPoint.offset;
          (target == jetUnderlyingSourceViewer ? jetEditor : javaEditor).selectAndReveal(correspondingPoint.offset, length);

          StyledText textWidget = target.getTextWidget();
          Rectangle bounds = textWidget.getCaret().getBounds();
          textWidget.redraw(bounds.x, bounds.y, bounds.width, bounds.height, false);
        }
      }
    }
  }

  @SuppressWarnings("restriction")
  static class DelegatingTextHover implements IJavaEditorTextHover, ITextHoverExtension, ITextHoverExtension2
  {
    private final org.eclipse.jdt.internal.ui.text.java.hover.JavadocHover javadocHover = new org.eclipse.jdt.internal.ui.text.java.hover.JavadocHover();

    private final JETEditor jetEditor;

    private DefaultTextHover defaultTextHover;

    private ISourceViewer javaTextViewer;

    public DelegatingTextHover(JETEditor jetEditor)
    {
      this.jetEditor = jetEditor;
    }

    public void setEditor(IEditorPart editor)
    {
      javadocHover.setEditor(editor);
      javaTextViewer = ((JavaEditor)editor).getJavaSourceViewer();
      defaultTextHover = new DefaultTextHover(jetEditor.getSourceViewer());
    }

    public IRegion getHoverRegion(ITextViewer textViewer, int offset)
    {
      return defaultTextHover.getHoverRegion(textViewer, offset);
    }

    @SuppressWarnings("deprecation")
    public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion)
    {
      IRegion javaRegion = toJava(hoverRegion);
      return javaRegion == null ? defaultTextHover.getHoverInfo(textViewer, hoverRegion) : javadocHover.getHoverInfo(javaTextViewer, javaRegion);
    }

    public Object getHoverInfo2(ITextViewer textViewer, IRegion hoverRegion)
    {
      IRegion javaRegion = toJava(hoverRegion);
      return javaRegion == null ? null : javadocHover.getHoverInfo2(javaTextViewer, javaRegion);
    }

    public IInformationControlCreator getHoverControlCreator()
    {
      return javadocHover.getHoverControlCreator();
    }

    private IRegion toJava(IRegion templateRegion)
    {
      Position correspondingJavaPoint = jetEditor.getCorrespondingJavaPosition(templateRegion.getOffset(), templateRegion.getLength());
      if (correspondingJavaPoint != null)
      {
        return new Region(correspondingJavaPoint.getOffset(), correspondingJavaPoint.getLength());
      }
      return null;
    }
  }

  static class CompletionProposalDelegate
    implements
      ICompletionProposal,
      ICompletionProposalExtension2,
      ICompletionProposalExtension3,
      ICompletionProposalExtension4,
      ICompletionProposalExtension5,
      ICompletionProposalExtension6
  {
    private final JETEditor jetEditor;

    private final ICompletionProposal delegate;

    private final ITextViewer delegateViewer;

    final IDocument delegateDocument;

    final TrackedPosition javaPosition;

    final TrackedPosition jetPosition;

    public CompletionProposalDelegate(ICompletionProposal delelgate, JETEditor jetEditor)
    {
      this.delegate = delelgate;
      this.jetEditor = jetEditor;
      this.delegateViewer = jetEditor.getJavaSourceViewer();
      this.delegateDocument = delegateViewer.getDocument();
      this.jetPosition = jetEditor.getJETPosition();
      this.javaPosition = jetEditor.getJavaPosition();
    }

    public void apply(IDocument document)
    {
      JavaDocumentTransaction javaDocumentTransaction = new CompletionTransaction(jetEditor);
      javaDocumentTransaction.modify(delegateDocument, new SneakyRunnable()
        {
          @Override
          public void execute() throws Exception
          {
            Method performChangeMethod = null;
            // We do this ugliness because directly applying the proposal will often want to open the Java editor and we definitely don't want that.
            for (Class<?> delegateClass = delegate.getClass(); delegateClass != null; delegateClass = delegateClass.getSuperclass())
            {
              try
              {
                performChangeMethod = delegateClass.getDeclaredMethod("performChange", IEditorPart.class, IDocument.class);
                performChangeMethod.setAccessible(true);
                break;
              }
              catch (Exception exception)
              {
              }
            }

            if (performChangeMethod != null)
            {
              performChangeMethod.invoke(delegate, jetEditor.getJavaEditor(), delegateDocument);
            }
            else
            {
              delegate.apply(delegateDocument);
            }
          }
        });

      jetEditor.openDialog("Proposal Failed", javaDocumentTransaction.getStatus());
    }

    public void apply(ITextViewer viewer, final char trigger, final int stateMask, int offset)
    {
      if (delegate instanceof ICompletionProposalExtension2)
      {
        JavaDocumentTransaction javaDocumentTransaction = new CompletionTransaction(jetEditor);
        javaDocumentTransaction.modify(delegateDocument, new Runnable()
          {
            public void run()
            {
              ((ICompletionProposalExtension2)delegate).apply(delegateViewer, trigger, stateMask, delegateViewer.getSelectedRange().x);
            }
          });

        jetEditor.openDialog("Proposal Failed", javaDocumentTransaction.getStatus());
      }
      else
      {
        apply(delegateDocument);
      }
    }

    public Point getSelection(IDocument document)
    {
      return null;
    }

    public String getAdditionalProposalInfo()
    {
      return delegate.getAdditionalProposalInfo();
    }

    public String getDisplayString()
    {
      return delegate.getDisplayString();
    }

    public Image getImage()
    {
      return delegate.getImage();
    }

    public IContextInformation getContextInformation()
    {
      return delegate.getContextInformation();
    }

    public StyledString getStyledDisplayString()
    {
      return delegate instanceof ICompletionProposalExtension6 ? ((ICompletionProposalExtension6)delegate).getStyledDisplayString() : new StyledString(getDisplayString());
    }

    public Object getAdditionalProposalInfo(IProgressMonitor monitor)
    {
      return delegate instanceof ICompletionProposalExtension5 ? ((ICompletionProposalExtension5)delegate).getAdditionalProposalInfo(monitor) : null;
    }

    public boolean isAutoInsertable()
    {
      return delegate instanceof ICompletionProposalExtension4 && ((ICompletionProposalExtension4)delegate).isAutoInsertable();
    }

    public IInformationControlCreator getInformationControlCreator()
    {
      return delegate instanceof ICompletionProposalExtension3 ? ((ICompletionProposalExtension3)delegate).getInformationControlCreator() : null;
    }

    private int jetToJava(int templateOffset)
    {
      return javaPosition.getOffset() + templateOffset - jetPosition.getOffset();
    }

    public CharSequence getPrefixCompletionText(IDocument document, int completionOffset)
    {
      return delegate instanceof ICompletionProposalExtension3
        ? ((ICompletionProposalExtension3)delegate).getPrefixCompletionText(delegateDocument, jetToJava(completionOffset)) : null;
    }

    public int getPrefixCompletionStart(IDocument document, int completionOffset)
    {
      return delegate instanceof ICompletionProposalExtension3
        ? ((ICompletionProposalExtension3)delegate).getPrefixCompletionStart(delegateDocument, jetToJava(completionOffset)) : null;
    }

    public void selected(ITextViewer viewer, boolean smartToggle)
    {
      if (delegate instanceof ICompletionProposalExtension2)
      {
        ((ICompletionProposalExtension2)delegate).selected(delegateViewer, smartToggle);
      }
    }

    public void unselected(ITextViewer viewer)
    {
      if (delegate instanceof ICompletionProposalExtension2)
      {
        ((ICompletionProposalExtension2)delegate).unselected(delegateViewer);
      }
    }

    public boolean validate(IDocument document, int offset, DocumentEvent event)
    {
      return delegate instanceof ICompletionProposalExtension2 && ((ICompletionProposalExtension2)delegate).validate(delegateDocument, jetToJava(offset), event);
    }

    class CompletionTransaction extends JavaDocumentTransaction
    {
      public CompletionTransaction(JETEditor jetEditor)
      {
        super(jetEditor, Collections.singletonList(jetEditor.getJETPosition()), true, false);
      }

      @Override
      protected void postCommit(List<TrackedPosition> jetPositions)
      {
        Point finalJavaSelectionRange = delegate.getSelection(delegateDocument);
        if (finalJavaSelectionRange != null)
        {
          int offset = finalJavaSelectionRange.x - javaPosition.getOffset();
          if (offset > 0)
          {
            jetEditor.getSourceViewer().setSelectedRange(jetPosition.getOffset() + offset, finalJavaSelectionRange.y);
          }
        }

        // Ensure that pop-up in Java view, if any, is dismissed.
        Event event = new Event();
        event.character = SWT.ESC;
        event.keyCode = SWT.ESC;
        jetEditor.getJavaSourceViewer().getTextWidget().notifyListeners(SWT.KeyDown, event);
      }
    }
  }

  static class JavaDocumentTransaction implements IDocumentListener
  {
    private static final Pattern IMPORT_PATTERN = Pattern.compile("import ([^;]+);");

    protected final JETEditor jetEditor;

    protected final List<TrackedPosition> jetPositions;

    private final boolean transformBraces;

    private final boolean ignoreOutOfScopeChanges;

    public JavaDocumentTransaction(JETEditor jetEditor, List<TrackedPosition> jetPositions, boolean transformBraces, boolean ignoreOutOfScopeChanges)
    {
      this.jetEditor = jetEditor;
      this.jetPositions = jetPositions;
      this.transformBraces = transformBraces;
      this.ignoreOutOfScopeChanges = ignoreOutOfScopeChanges;
    }

    public void modify(IDocument document, Runnable javaModifier)
    {
      start();
      jetEditor.getDocumentManager().setEnableJavaSynchronization(false);
      document.addDocumentListener(this);
      try
      {
        javaModifier.run();
        if (status == null)
        {
          commit(document);
        }
      }
      catch (Exception exception)
      {
        status = CodeGenUIPlugin.toStatus(IStatus.ERROR, exception);
      }
      finally
      {
        document.removeDocumentListener(this);
        jetEditor.getDocumentManager().setEnableJavaSynchronization(true);
        stop();
      }
    }

    protected void startDocumentRewriteSession(ISourceViewer sourceViewer, DocumentRewriteSessionType type, boolean rememberSelection)
    {
      if (rememberSelection)
      {
        try
        {
          Method rememberSelectionMethod = SourceViewer.class.getDeclaredMethod("rememberSelection");
          rememberSelectionMethod.setAccessible(true);
          rememberSelectionMethod.invoke(sourceViewer);
        }
        catch (Exception exception)
        {
          throw new RuntimeException(exception);
        }
      }

      ((IDocumentExtension4)sourceViewer.getDocument()).startRewriteSession(type);
    }

    protected void stopDocumentRewriteSession(ISourceViewer sourceViewer, boolean restoreSelection)
    {
      IDocumentExtension4 extension = (IDocumentExtension4)sourceViewer.getDocument();
      extension.stopRewriteSession(extension.getActiveRewriteSession());

      if (restoreSelection)
      {
        try
        {
          Method rememberSelectionMethod = SourceViewer.class.getDeclaredMethod("restoreSelection");
          rememberSelectionMethod.setAccessible(true);
          rememberSelectionMethod.invoke(sourceViewer);
        }
        catch (Exception exception)
        {
          throw new RuntimeException(exception);
        }
      }
    }

    protected void start()
    {
    }

    protected void stop()
    {
    }

    private List<String> imports = new ArrayList<String>();

    private IStatus status;

    public IStatus getStatus()
    {
      return status == null ? Status.OK_STATUS : status;
    }

    public void documentAboutToBeChanged(DocumentEvent event)
    {
    }

    public void documentChanged(DocumentEvent event)
    {
      if (status == null)
      {
        for (TrackedPosition jetPosition : jetPositions)
        {
          TrackedPosition javaPosition = jetPosition.getOpposite();
          if (javaPosition.overlapsWith(event.getOffset(), event.getLength()))
          {
            return;
          }
        }

        String replacement = event.getText();
        Matcher matcher = IMPORT_PATTERN.matcher(replacement);
        if (matcher.matches())
        {
          String newImport = matcher.group(1);
          imports.add(newImport);
        }
        else if (!ignoreOutOfScopeChanges && replacement.trim().length() != 0)
        {
          status = new Status(IStatus.ERROR, CodeGenUIPlugin.getPlugin().getBundle().getSymbolicName(), "Changes outside the scope of the current document occurred.");
        }
      }
    }

    protected void commit(IDocument delegateDocument) throws Exception
    {
      SourceViewer jetSourceViewer = jetEditor.getJETSourceViewer();
      IUndoManager undoManager = jetSourceViewer.getUndoManager();
      undoManager.beginCompoundChange();
      try
      {
        MultiTextEdit textEdit = new MultiTextEdit();
        for (TrackedPosition jetPosition : jetPositions)
        {
          TrackedPosition javaPosition = jetPosition.getOpposite();
          if (!javaPosition.isDeleted())
          {
            String finalJavaText = javaPosition.getText();

            String originalTemplateJavaText = jetPosition.getText();

            if (!originalTemplateJavaText.equals(finalJavaText))
            {
              int orginalTemplateJavaTextLength = jetPosition.getLength();
              int finalJavaTextLength = javaPosition.getLength();
              int start = 0;
              while (start < finalJavaTextLength && start < orginalTemplateJavaTextLength && finalJavaText.charAt(start) == originalTemplateJavaText.charAt(start))
              {
                ++start;
              }

              if (start != orginalTemplateJavaTextLength - 1 || orginalTemplateJavaTextLength != finalJavaTextLength)
              {
                int end = 0;
                while (finalJavaTextLength - end > 0 && orginalTemplateJavaTextLength - end > 0
                    && finalJavaText.charAt(finalJavaTextLength - end - 1) == originalTemplateJavaText.charAt(orginalTemplateJavaTextLength - end - 1))
                {
                  ++end;
                }

                int replacementOffset = jetPosition.getOffset() + start;
                int replacementLength = orginalTemplateJavaTextLength - end - start;
                while (replacementLength < 0)
                {
                  // The replacement length could be negative because the tail end of the actual Java replacement text could exactly match the text in the original string.
                  // And that part will have been trimmed from the replacement text we've computed for application to the template.
                  ++replacementLength;
                  --end;
                }

                int finalJavaTextEnd = finalJavaTextLength - end;
                String replacementText = finalJavaTextEnd < start ? "" : handleReplacement(finalJavaText.substring(start, finalJavaTextEnd));
                ReplaceEdit replaceEdit = new ReplaceEdit(replacementOffset, replacementLength, replacementText);
                textEdit.addChild(replaceEdit);
              }
            }
          }
        }

        int jetDirectiveFileID = -1;
        int importsOffset = -1;
        boolean needsImportsAttribute = false;
        StringBuilder newImports = new StringBuilder();
        if (!imports.isEmpty())
        {
          JETDirectiveItem jetJETDirectiveItem = jetEditor.getCompilationUnit().getJETJETDirectiveItem();
          jetDirectiveFileID = jetJETDirectiveItem.getFileID();
          JETAttributeItem imports = jetJETDirectiveItem.getAttribute("imports");
          for (String newImport : this.imports)
          {
            if (imports == null)
            {
              importsOffset = jetJETDirectiveItem.getAttributes().getStopOffset();
              needsImportsAttribute = true;
              if (newImports.length() != 0)
              {
                newImports.append(' ');
              }
            }
            else
            {
              JETTokenItem importValue = imports.getValueToken();
              if (importsOffset == -1)
              {
                JETValueItem valueItem = importValue.getValueItem();
                importsOffset = valueItem.getStopOffset();
                if (!valueItem.getElements().isEmpty())
                {
                  newImports.append(' ');
                }
              }
              else
              {
                newImports.append(' ');
              }
            }

            newImports.append(newImport);
          }
        }

        if (importsOffset != -1)
        {
          String insertion = needsImportsAttribute ? " imports=\"" + newImports + "\"" : newImports.toString();
          ReplaceEdit insertImportsEdit = new ReplaceEdit(importsOffset, 0, insertion);
          if (jetDirectiveFileID != jetEditor.getFileID())
          {
            IDocument jetDirectiveDocument = jetEditor.getDocumentManager().getDocument(jetDirectiveFileID);
            if (jetDirectiveDocument != null)
            {
              try
              {
                insertImportsEdit.apply(jetDirectiveDocument);
              }
              catch (Exception exception)
              {
                CodeGenUIPlugin.getPlugin().log(exception);
              }
            }
          }
          else
          {
            textEdit.addChild(insertImportsEdit);
          }
        }

        try
        {
          TextEdit[] children = textEdit.getChildren();
          if (children.length > 0)
          {
            startDocumentRewriteSession(jetSourceViewer, children.length > 50 ? DocumentRewriteSessionType.UNRESTRICTED : DocumentRewriteSessionType.UNRESTRICTED_SMALL, false);
            IDocument document = jetSourceViewer.getDocument();
            textEdit.apply(document);
          }

          postCommit(jetPositions);
        }
        finally
        {
          stopDocumentRewriteSession(jetSourceViewer, false);
        }
      }
      finally
      {
        undoManager.endCompoundChange();
      }
    }

    private static Pattern BRACE_COLLAPSE_PATTERN = Pattern.compile("\\s\\s+\\{");

    private static Pattern TAB_REPLACEMENT_PATTERN = Pattern.compile("\t");

    private static Pattern INDENT_FIX_PATTERN_ = Pattern.compile("(\r?\n)  ");

    protected String handleReplacement(String replacement)
    {
      if (transformBraces)
      {
        String result = BRACE_COLLAPSE_PATTERN.matcher(replacement).replaceAll(" {");
        result = TAB_REPLACEMENT_PATTERN.matcher(result).replaceAll("  ");
        result = INDENT_FIX_PATTERN_.matcher(result).replaceAll("$1");
        return result;
      }

      return replacement;
    }

    protected void postCommit(List<TrackedPosition> jetPositions)
    {
    }
  }

  static class DelegatingContentAssistProcessor implements IContentAssistProcessor
  {
    private static final Pattern IGNORED_PROPOSAL_PATTERN = Pattern.compile("^(((TEXT)?_([0-9]+))|nl|(NL(_[0-9]+)?)) : String");

    private final JETEditor jetEditor;

    private final JETContentAssistProcessor jetContentAssistProcessor;

    private IContentAssistProcessor javaContentAssistProcessor;

    private IContentAssistProcessor delegate;

    public DelegatingContentAssistProcessor(JETEditor jetEditor)
    {
      this.jetEditor = jetEditor;
      jetContentAssistProcessor = new JETContentAssistProcessor(jetEditor);
    }

    protected IContentAssistProcessor getJavaAssistProcessor()
    {
      if (javaContentAssistProcessor == null)
      {
        IContentAssistant contentAssist = jetEditor.getJavaEditor().getContentAssist();
        javaContentAssistProcessor = contentAssist.getContentAssistProcessor(IDocument.DEFAULT_CONTENT_TYPE);
      }
      return javaContentAssistProcessor;
    }

    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset)
    {
      final JETToken token = jetEditor.getTokenAt(offset);
      if (token == JETContentRule.TOKEN || token == JETTrailingWhitespaceContentRule.TOKEN || token == JETDirectiveRule.TOKEN || token == null)
      {
        delegate = jetContentAssistProcessor;
        return jetContentAssistProcessor.computeCompletionProposals(viewer, offset);
      }
      else if (token == JETScriptletRule.TOKEN || token == JETExpressionRule.TOKEN)
      {
        delegate = getJavaAssistProcessor();

        final ISourceViewer javaSourceViewer = jetEditor.getJavaEditor().getJavaSourceViewer();
        final Point javaSelectionRange = javaSourceViewer.getSelectedRange();
        final int javaCaretOffset = javaSelectionRange.x + javaSelectionRange.y;

        ICompletionProposal[] proposals = delegate.computeCompletionProposals(javaSourceViewer, javaCaretOffset);
        List<ICompletionProposal> filteredProposals = new ArrayList<ICompletionProposal>();
        Set<String> duplicates = new HashSet<String>();

        for (final ICompletionProposal completionProposal : proposals)
        {
          String displayString = completionProposal.getDisplayString();
          if (duplicates.add(displayString) && !IGNORED_PROPOSAL_PATTERN.matcher(displayString).find())
          {
            filteredProposals.add(new CompletionProposalDelegate(completionProposal, jetEditor));
          }
        }

        return filteredProposals.toArray(new ICompletionProposal [filteredProposals.size()]);
      }
      else
      {
        return null;
      }
    }

    public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset)
    {
      return null;
    }

    public char[] getCompletionProposalAutoActivationCharacters()
    {
      JETToken token = jetEditor.getTokenAtSelection(0);
      return token == JETScriptletRule.TOKEN || token == JETExpressionRule.TOKEN ? getJavaAssistProcessor().getCompletionProposalAutoActivationCharacters() : null;
    }

    public char[] getContextInformationAutoActivationCharacters()
    {
      JETToken token = jetEditor.getTokenAtSelection(0);
      return token == JETScriptletRule.TOKEN || token == JETExpressionRule.TOKEN ? getJavaAssistProcessor().getContextInformationAutoActivationCharacters() : null;
    }

    public String getErrorMessage()
    {
      return delegate != null ? delegate.getErrorMessage() : null;
    }

    public IContextInformationValidator getContextInformationValidator()
    {
      return null;
    }
  }

  static class DelegatingQuickAssistProcessor implements IQuickAssistProcessor
  {
    private JETEditor jetEditor;

    public DelegatingQuickAssistProcessor(JETEditor jetEditor)
    {
      this.jetEditor = jetEditor;
    }

    @SuppressWarnings("restriction")
    protected IQuickAssistProcessor getJavaAssistProcessor(IQuickAssistInvocationContext invocationContext)
    {
      JavaEditor javaEditor = jetEditor.getJavaEditor();
      IQuickAssistAssistant quickAssistAssistant = javaEditor.getQuickAssistAssistant();
      if (invocationContext != null)
      {
        try
        {
          ArrayList<Annotation> resultingAnnotations = new ArrayList<Annotation>();
          org.eclipse.jdt.internal.ui.text.correction.JavaCorrectionAssistant.collectQuickFixableAnnotations(
            javaEditor,
            invocationContext.getOffset(),
            invocationContext.getLength() != 0,
            resultingAnnotations);
          Field fCurrentAnnotationsField = quickAssistAssistant.getClass().getDeclaredField("fCurrentAnnotations");
          fCurrentAnnotationsField.setAccessible(true);
          fCurrentAnnotationsField.set(quickAssistAssistant, resultingAnnotations.toArray(new Annotation [resultingAnnotations.size()]));
        }
        catch (Exception exception)
        {
        }
      }

      return quickAssistAssistant.getQuickAssistProcessor();
    }

    protected IQuickAssistInvocationContext getJavaInvocationContext(IQuickAssistInvocationContext invocationContext)
    {
      final JETToken token = jetEditor.getTokenAt(invocationContext.getOffset());
      if (token == JETScriptletRule.TOKEN || token == JETExpressionRule.TOKEN)
      {
        final ISourceViewer javaSourceViewer = jetEditor.getJavaEditor().getJavaSourceViewer();
        final Point javaSelectionRange = javaSourceViewer.getSelectedRange();
        return new TextInvocationContext(javaSourceViewer, javaSelectionRange.x, javaSelectionRange.y);
      }
      else
      {
        return null;
      }
    }

    public boolean canAssist(IQuickAssistInvocationContext invocationContext)
    {
      IQuickAssistInvocationContext javaInvocationContext = getJavaInvocationContext(invocationContext);
      return javaInvocationContext != null && getJavaAssistProcessor(javaInvocationContext).canAssist(javaInvocationContext);
    }

    public ICompletionProposal[] computeQuickAssistProposals(IQuickAssistInvocationContext invocationContext)
    {
      IQuickAssistInvocationContext javaInvocationContext = getJavaInvocationContext(invocationContext);
      if (javaInvocationContext != null)
      {
        ICompletionProposal[] quickAssistProposals = getJavaAssistProcessor(javaInvocationContext).computeQuickAssistProposals(javaInvocationContext);
        if (quickAssistProposals != null)
        {
          List<ICompletionProposal> delegatingProposals = new ArrayList<ICompletionProposal>(quickAssistProposals.length);
          for (ICompletionProposal completionProposal : quickAssistProposals)
          {
            delegatingProposals.add(new CompletionProposalDelegate(completionProposal, jetEditor));
          }
          return delegatingProposals.toArray(new ICompletionProposal [delegatingProposals.size()]);
        }
      }
      return null;
    }

    public String getErrorMessage()
    {
      return getJavaAssistProcessor(null).getErrorMessage();
    }

    public boolean canFix(Annotation annotation)
    {
      return getJavaAssistProcessor(null).canFix(annotation);
    }
  }

  static class JETContentAssistProcessor extends TemplateCompletionProcessor
  {
    private static final Map<String, JETTemplateContextType> JET_CONTEXT_TYPES = new HashMap<String, JETTemplateContextType>();

    private static final Map<JETToken, JETTemplateContextType> JET_TOKEN_CONTEXT_TYPES = new HashMap<JETToken, JETTemplateContextType>();

    private static Image JET_IMAGE = CodeGenUIPlugin.getPlugin().getActualImage("full/obj16/JET");

    private final JETEditor jetEditor;

    static
    {
      new JETContentContextType();
      new JETDirectiveContextType();
    }

    public JETContentAssistProcessor(JETEditor jetEditor)
    {
      this.jetEditor = jetEditor;
    }

    @Override
    protected Template[] getTemplates(String contextTypeId)
    {
      JETTemplateContextType jetTemplateContextType = JET_CONTEXT_TYPES.get(contextTypeId);
      return jetTemplateContextType == null ? null : jetTemplateContextType.getTemplates(jetEditor.getEditorInput());
    }

    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset)
    {
      try
      {
        return super.computeCompletionProposals(viewer, offset);
      }
      finally
      {
        for (JETTemplateContextType jetTemplateContextType : JET_CONTEXT_TYPES.values())
        {
          jetTemplateContextType.resetContext();
        }
      }
    }

    @Override
    protected String extractPrefix(ITextViewer viewer, int offset)
    {
      return "";
    }

    @Override
    protected ICompletionProposal createProposal(Template template, TemplateContext context, IRegion region, int relevance)
    {
      return new JETTemplateProposal(template, context, region, getImage(template), relevance);
    }

    @Override
    protected TemplateContextType getContextType(ITextViewer viewer, IRegion region)
    {
      JETTokenRegion tokenRegion = jetEditor.getTokenRegionAt(region.getOffset());

      // If we are right at the start, even in an empty file, treat it like we are in content.
      JETToken token = tokenRegion == null || tokenRegion.getOffset() == region.getOffset() ? JETContentRule.TOKEN : tokenRegion.getToken();
      JETTemplateContextType jetTemplateContextType = JET_TOKEN_CONTEXT_TYPES.get(token.getBaseToken());
      if (jetTemplateContextType != null)
      {
        jetTemplateContextType.setContext(jetEditor, region);
      }
      return jetTemplateContextType;
    }

    @Override
    protected Image getImage(Template template)
    {
      return JET_IMAGE;
    }

    private static class JETTemplateProposal extends TemplateProposal implements ICompletionProposalExtension6
    {
      private StyledString displayString;

      public JETTemplateProposal(Template template, TemplateContext context, IRegion region, Image image, int relevance)
      {
        super(template, context, region, image, relevance);
      }

      @Override
      public String getDisplayString()
      {
        return getStyledDisplayString().toString();
      }

      public StyledString getStyledDisplayString()
      {
        if (displayString == null)
        {
          String name = getTemplate().getName();
          String description = getTemplate().getDescription();
          displayString = new StyledString(name, StyledString.COUNTER_STYLER);
          displayString.append(" - ");
          displayString.append(description, StyledString.QUALIFIER_STYLER);
        }
        return displayString;
      }
    }

    static class JETTemplateContextType extends TemplateContextType
    {
      private JETEditor jetEditor;

      private IRegion region;

      public JETTemplateContextType(String id, JETToken token)
      {
        super(id);
        JET_CONTEXT_TYPES.put(id, this);
        JET_TOKEN_CONTEXT_TYPES.put(token, this);
      }

      public JETEditor getEditor()
      {
        return jetEditor;
      }

      public IRegion getRegion()
      {
        return region;
      }

      public void setContext(JETEditor jetEditor, IRegion region)
      {
        this.jetEditor = jetEditor;
        this.region = region;
      }

      public void resetContext()
      {
        jetEditor = null;
        region = null;
      }

      public Template[] getTemplates(IEditorInput input)
      {
        return null;
      }
    }

    static class JETContentContextType extends JETTemplateContextType
    {
      private static String ID = "org.eclipse.code.ui.jet.content";

      private static Pattern JET_DIRECTIVE_PATTERN = Pattern.compile("\\s*<%@\\s*jet\\s+.*%>", Pattern.DOTALL);

      private static final Template JET_DIRECTIVE_TEMPLATE = new Template(
        "<%@jet%>",
        "@jet directive to minimally specify the Java class name",
        ID,
        "<%@jet package=\"${java_package_name}\" class=\"${java_class_name}\" imports=\"${java_imports}\"%>\n",
        true);

      private static final Template JET_SCRIPTLET_TEMPLATE = new Template("<%%>", "scriptlet for specifying Java statement logic", JETContentContextType.ID, "<%${}%>", true);

      private static final Template JET_EXPRESSION_TEMPLATE = new Template("<%=%>", "expression for specifying Java expression logic", JETContentContextType.ID, "<%=${}%>", true);

      private static final Template JET_SIMPLE_INCLUDE_TEMPLATE = new Template(
        "<%@include%>",
        "@include directive for including a 'jetinc' file",
        ID,
        "<%@include file=\"${file}\"%>",
        true);

      private static final Template JET_OPTIONAL_INCLUDE_TEMPLATE = new Template(
        "<%@include fail=\"silent\"%>",
        "@include directive for including a 'jetinc' file, ignoring when it doesn't exist",
        ID,
        "<%@include file=\"${file}\" fail=\"silent\"%>",
        true);

      private static final Template JET_ALTERNATIVE_INCLUDE_TEMPLATE = new Template(
        "<%@include fail=\"alternative\"%>",
        "@include directive for including a 'jetinc' file, using the @start/@end block when it doesn't exist",
        ID,
        "<%@include file=\"${file}\" fail=\"alternative\"%>\n<%@start%>\n${}<%@end%>\n",
        true);

      private static final Template JET_COPYRIGHT_COMMENT_TEMPLATE = new Template(
        "<%--copyright--%>",
        "a comment at the start of the file for specifying the copyright",
        ID,
        "<%--\nCopyright " + System.getProperty("user.name") + " " + Calendar.getInstance().get(Calendar.YEAR) + "\n--%>\n",
        true);

      private static final Template JET_COMMENT_TEMPLATE = new Template("<%--%>", "comment for specifying excluded content", JETContentContextType.ID, "<%-${}-%>", true);

      private static final Template JET_COMMENT_TEMPLATE_WITH_TAG = new Template(
        "<%-[]-%>",
        "comment for specifying a tag in the content outline",
        JETContentContextType.ID,
        "<%-[${Tag}]-%>",
        true);

      public JETContentContextType()
      {
        super(ID, JETContentRule.TOKEN);
      }

      private Template convert(Template template, String lineDelimeter)
      {
        return new Template(
          template.getName(),
          template.getDescription(),
          template.getContextTypeId(),
          template.getPattern().replace("\n", lineDelimeter),
          template.isAutoInsertable());
      }

      @Override
      public Template[] getTemplates(IEditorInput input)
      {
        ITextViewer viewer = getEditor().getSourceViewer();
        IDocument document = viewer.getDocument();
        String lineDelimiter = TextUtilities.getDefaultLineDelimiter(document);

        List<Template> result = new ArrayList<Template>();
        if (viewer.getSelectedRange().x == 0)
        {
          result.add(convert(JET_COPYRIGHT_COMMENT_TEMPLATE, lineDelimiter));
        }

        boolean isJET = input == null || input.getName() == null || input.getName().endsWith("jet");
        if (isJET)
        {
          try
          {
            IRegion lineInformation = document.getLineInformation(0);
            String firstLine = document.get(lineInformation.getOffset(), lineInformation.getLength());
            if (!JET_DIRECTIVE_PATTERN.matcher(firstLine).matches())
            {
              JETCompilationUnit compilationUnit = getEditor().getCompilationUnit();
              if (compilationUnit == null || compilationUnit.getJETJETDirectiveItem() == null || compilationUnit.getJETJETDirectiveItem().getLength() == 0)
              {
                result.add(convert(JET_DIRECTIVE_TEMPLATE, lineDelimiter));
              }
            }
          }
          catch (BadLocationException exception)
          {
          }
        }

        result.add(convert(JET_SCRIPTLET_TEMPLATE, lineDelimiter));
        result.add(convert(JET_EXPRESSION_TEMPLATE, lineDelimiter));
        result.add(convert(JET_SIMPLE_INCLUDE_TEMPLATE, lineDelimiter));
        result.add(convert(JET_OPTIONAL_INCLUDE_TEMPLATE, lineDelimiter));
        result.add(convert(JET_ALTERNATIVE_INCLUDE_TEMPLATE, lineDelimiter));
        result.add(convert(JET_COMMENT_TEMPLATE, lineDelimiter));
        result.add(convert(JET_COMMENT_TEMPLATE_WITH_TAG, lineDelimiter));

        return result.toArray(new Template [result.size()]);
      }
    }

    private static class JETDirectiveContextType extends JETTemplateContextType
    {
      private static String ID = "org.eclipse.code.ui.jet.directive";

      private static final Map<String, List<Template>> ATTRIBUTE_TEMPLATES = new HashMap<String, List<Template>>();

      static
      {
        List<Template> jetTemplates = new ArrayList<Template>();
        jetTemplates.add(new Template("class", "specify the Java class name of the class", ID, "class=\"${java_class_name}\"", false));
        jetTemplates.add(new Template("package", "specify the Java package name of the class", ID, "package=\"${java_package_name}\"", false));
        jetTemplates.add(new Template("imports", "specify the Java imports for the class", ID, "imports=\"${java_imports}\"", false));
        jetTemplates.add(
          new Template("builder", "specify the builder to be used in the generate method of the class", ID, "builder=\"StringBuilder ${builder} = new StringBuilder()\"", false));
        jetTemplates.add(new Template("minimize", "whether to mimize the footprint of the literal constants of the class", ID, "minimize=\"${true}\"", false));
        jetTemplates.add(
          new Template("nlString", "specify the Java expression used to extract the default line delimiter of the class", ID, "nlString=\"${global_expression}\"", false));
        jetTemplates.add(new Template("skeleton", "specify a Java class to be used as the skeleton of the class", ID, "skeleton=\"${skeleton_java_file}\"", false));
        jetTemplates.add(new Template("version", "specify and arbtirary used version for the templates", ID, "version=\"${version}\"", false));
        ATTRIBUTE_TEMPLATES.put("jet", jetTemplates);

        List<Template> includeTemplates = new ArrayList<Template>();
        includeTemplates.add(new Template("file", "specify file to include in the template", ID, "file=\"${file}\"", false));
        includeTemplates.add(new Template("fail", "specify to ignore a missing file", ID, "fail=\"${silent}\"", false));
        ATTRIBUTE_TEMPLATES.put("include", includeTemplates);
      }

      public JETDirectiveContextType()
      {
        super(ID, JETDirectiveRule.TOKEN);
      }

      private Template convert(Template template, String prefix, String suffix)
      {
        return new Template(template.getName(), template.getDescription(), template.getContextTypeId(), prefix + template.getPattern() + suffix, template.isAutoInsertable());
      }

      @Override
      public Template[] getTemplates(IEditorInput input)
      {
        JETEditor jetEditor = getEditor();

        // Ignore the prefix.
        IRegion region = getRegion();
        int offset = region.getOffset() + region.getLength();
        JETTokenRegion tokenRegion = jetEditor.getTokenRegionAt(offset);
        try
        {
          List<Template> result = new ArrayList<Template>();
          int tokenRegionOffset = tokenRegion.getOffset();
          int tokenRegionLength = tokenRegion.getLength();
          String directive = jetEditor.getSourceViewer().getDocument().get(tokenRegionOffset, tokenRegionLength);
          JETDirectiveItem jetDirectiveItem = JETParser.parseDirective(directive);
          JETAttributeListItem attributes = jetDirectiveItem.getAttributes();
          int relativeOffset = offset - tokenRegionOffset;
          JETItem nameItem = jetDirectiveItem.getNameItem();
          String directiveName = nameItem.getText();
          List<Template> templates = ATTRIBUTE_TEMPLATES.get(directiveName);
          if (templates != null)
          {
            boolean valid = false;
            JETItem previousItem = nameItem;
            for (JETAttributeItem jetAttributeItem : attributes.getAttributes())
            {
              int previousStopOffset = previousItem.getStopOffset();
              int nextStartOffset = jetAttributeItem.getStartOffset();
              if (relativeOffset >= previousStopOffset && relativeOffset <= nextStartOffset)
              {
                valid = true;
                break;
              }
              else
              {
                previousItem = jetAttributeItem;
              }
            }

            if (!valid && relativeOffset >= attributes.getStopOffset() && relativeOffset <= tokenRegionLength - 2)
            {
              valid = true;
            }

            if (valid)
            {
              char characterBeforeInsertion = directive.charAt(relativeOffset - 1);
              char characterAfterInsertion = directive.charAt(relativeOffset);
              String prefix = Character.isWhitespace(characterBeforeInsertion) ? "" : " ";
              String suffix = Character.isWhitespace(characterAfterInsertion) || characterAfterInsertion == '%' ? "" : " ";
              for (Template template : templates)
              {
                if (jetDirectiveItem.getAttribute(template.getName()) == null)
                {
                  result.add(convert(template, prefix, suffix));
                }
              }
            }
          }

          return result.toArray(new Template [result.size()]);
        }
        catch (Exception exception)
        {
          return null;
        }
      }
    }
  }

  static class JETProblemAnnotation extends Annotation implements IAnnotationPresentation
  {
    private static final Image[] SEVERITY_IMAGES;

    private static final Image[] DISABLED_SEVERITY_IMAGES;

    static
    {
      ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
      SEVERITY_IMAGES = new Image []{
        sharedImages.getImage(ISharedImages.IMG_OBJS_INFO_TSK),
        sharedImages.getImage(ISharedImages.IMG_OBJS_WARN_TSK),
        sharedImages.getImage(ISharedImages.IMG_OBJS_ERROR_TSK) };
      DISABLED_SEVERITY_IMAGES = new Image []{
        new Image(SEVERITY_IMAGES[0].getDevice(), SEVERITY_IMAGES[0], SWT.IMAGE_GRAY),
        new Image(SEVERITY_IMAGES[1].getDevice(), SEVERITY_IMAGES[1], SWT.IMAGE_GRAY),
        new Image(SEVERITY_IMAGES[2].getDevice(), SEVERITY_IMAGES[2], SWT.IMAGE_GRAY) };
    }

    private static final String[] SEVERITY_TYPES = new String []{
      "org.eclipse.ui.workbench.texteditor.info",
      "org.eclipse.ui.workbench.texteditor.warning",
      "org.eclipse.ui.workbench.texteditor.error" };

    private final boolean disabled;

    private final int severity;

    private final Position position;

    public JETProblemAnnotation(int severity, String message, Position position)
    {
      super(SEVERITY_TYPES[severity], false, message);
      this.severity = severity;
      this.position = position;
      this.disabled = false;
    }

    public JETProblemAnnotation(MarkerAnnotation annotation, Position position)
    {
      super(SEVERITY_TYPES[getSeverity(annotation)], false, annotation.getText());
      severity = getSeverity(annotation);
      this.position = position;
      this.disabled = true;
    }

    public Position getPosition()
    {
      return position;
    }

    public int getSeverity()
    {
      return severity;
    }

    public int getLayer()
    {
      return 6 + severity + (disabled ? 1 : 0);
    }

    private Image getImage()
    {
      return (disabled ? DISABLED_SEVERITY_IMAGES : SEVERITY_IMAGES)[severity];
    }

    public void paint(GC gc, Canvas canvas, Rectangle bounds)
    {
      ImageUtilities.drawImage(getImage(), gc, canvas, bounds, SWT.CENTER, SWT.TOP);
    }

    private static int getSeverity(MarkerAnnotation markerAnnotation)
    {
      try
      {
        IMarker marker = markerAnnotation.getMarker();
        if (marker != null)
        {
          Object severity = marker.getAttribute(IMarker.SEVERITY);
          return severity instanceof Integer ? (Integer)severity : IMarker.SEVERITY_INFO;
        }
      }
      catch (CoreException exception)
      {
      }
      return IMarker.SEVERITY_ERROR;
    }
  }

  static class ColorManager implements IPropertyChangeListener
  {
    private static ColorManager INSTANCE = new ColorManager();

    private static final String PROPERTY_PREFIX = "org.eclipse.emf.codegen.ui.jet.";

    private final Set<JETToken> tokens = new LinkedHashSet<JETEditor.JETToken>();

    private final Map<Control, ISourceViewer> sourceViewers = new HashMap<Control, ISourceViewer>();

    private final Map<Color, Map<Color, Color>> blendedColors = new HashMap<Color, Map<Color, Color>>();

    private final Map<RGB, Color> colors = new HashMap<RGB, Color>();

    private final DisposeListener disposeListener = new DisposeListener()
      {
        public void widgetDisposed(DisposeEvent event)
        {
          sourceViewers.remove(event.widget);
          if (sourceViewers.isEmpty())
          {
            if (trace)
            {
              System.out.println("unhook color");
            }
            JFaceResources.getColorRegistry().removeListener(ColorManager.this);
          }
        }
      };

    private ColorManager()
    {
    }

    public Color getColor(int red, int green, int blue)
    {
      RGB rgb = new RGB(red, green, blue);
      Color color = colors.get(rgb);
      if (color == null)
      {
        color = new Color(Display.getDefault(), rgb);
        colors.put(rgb, color);
      }
      return color;
    }

    public Color getBlendedColor(Color foreground, Color background)
    {
      Map<Color, Color> blendedBackgroundColors = blendedColors.get(foreground);
      if (blendedBackgroundColors == null)
      {
        blendedBackgroundColors = new HashMap<Color, Color>();
        blendedColors.put(foreground, blendedBackgroundColors);
      }
      Color blendedColor = blendedBackgroundColors.get(background);
      if (blendedColor == null)
      {
        blendedColor = new Color(foreground.getDevice(), ColorUtil.blend(foreground.getRGB(), background.getRGB(), 50));
        blendedBackgroundColors.put(background, blendedColor);
      }
      return blendedColor;
    }

    public String getProperty(JETToken token)
    {
      return "org.eclipse.emf.codegen.ui.jet." + (token == null ? JETContentRule.TOKEN : token.getBaseToken()).getType() + "Background";
    }

    public void register(ISourceViewer sourceViewer)
    {
      if (sourceViewers.isEmpty())
      {
        JFaceResources.getColorRegistry().addListener(this);
      }

      StyledText control = sourceViewer.getTextWidget();
      sourceViewers.put(control, sourceViewer);
      control.addDisposeListener(disposeListener);
    }

    public void register(JETToken token)
    {
      tokens.add(token);
      update(token);
    }

    private void update(JETToken token)
    {
      String type = token.getBaseToken().getType();
      token.setData(new JETTextAttribute(getForeground(type), getBackground(type), token.getTextStyle(), token.getBorderStyle(), null, token));
    }

    private Color getForeground(String type)
    {
      return JFaceResources.getColorRegistry().get("org.eclipse.emf.codegen.ui.jet." + type + "Foreground");
    }

    private Color getBackground(String type)
    {
      return JFaceResources.getColorRegistry().get("org.eclipse.emf.codegen.ui.jet." + type + "Background");
    }

    public void propertyChange(PropertyChangeEvent event)
    {
      if (event.getProperty().startsWith(PROPERTY_PREFIX))
      {
        Display.getDefault().asyncExec(new Runnable()
          {
            public void run()
            {
              for (JETToken token : tokens)
              {
                update(token);
              }

              for (ISourceViewer sourceViewer : sourceViewers.values())
              {
                sourceViewer.invalidateTextPresentation();
              }
            }
          });
      }
    }

    private static class JETTextAttribute extends TextAttribute
    {
      private final JETToken token;

      private final int borderStyle;

      public JETTextAttribute(Color foreground, Color background, int textStyle, int borderStyle, Font font, JETToken token)
      {
        super(foreground, background, textStyle, font);
        this.token = token;
        this.borderStyle = borderStyle;
      }

      public JETToken getToken()
      {
        return token;
      }

      public int getBorderStyle()
      {
        return borderStyle;
      }

      @Override
      public boolean equals(Object object)
      {
        return object == this;
      }
    }
  }

  static class JETBracketMatcher extends DefaultCharacterPairMatcher
  {
    private final static char[] BRACKETS = { //
      '{',
      '}', //
      '(',
      ')', //
      '[',
      ']', //
      '<',
      '>', //
      '%',
      '%', //
      '=',
      '%', //
      '@',
      '%' //
    };

    private final JETEditor jetEditor;

    private int customAnchor;

    public JETBracketMatcher(JETEditor jetEditor)
    {
      super(BRACKETS);
      this.jetEditor = jetEditor;
    }

    private JETToken getToken(int offset)
    {
      return jetEditor.getTokenAt(offset);
    }

    private Point getTokenRange(int offset)
    {
      return jetEditor.getTokenRangeAt(offset);
    }

    @Override
    public IRegion match(IDocument document, int offset)
    {
      JETToken token = getToken(offset);
      if (token == null)
      {
        return null;
      }

      Point tokenRange;
      if (token.getBaseToken() == JETContentRule.TOKEN)
      {
        if (offset > 1)
        {
          token = getToken(offset - 1);
          if (token == null || token.getBaseToken() == JETContentRule.TOKEN)
          {
            return null;
          }

          tokenRange = getTokenRange(offset - 1);
        }
        else
        {
          return null;
        }
      }
      else
      {
        tokenRange = getTokenRange(offset);
      }

      for (;;)
      {
        try
        {
          // Trim of the preceding white space that's included in comments, scriptlets, and directives.
          if (document.getChar(tokenRange.x) == ' ')
          {
            ++tokenRange.x;
            --tokenRange.y;
          }
          else
          {
            break;
          }
        }
        catch (BadLocationException exception)
        {
          break;
        }
      }

      int effectiveOffset = offset;
      boolean isCloseBrace = false;
      if (token == JETScriptletRule.TOKEN && tokenRange.y == 5)
      {
        try
        {
          isCloseBrace = "<%}%>".equals(document.get(tokenRange.x, tokenRange.y));
          if (isCloseBrace)
          {
            effectiveOffset = tokenRange.x + 3;
          }
        }
        catch (BadLocationException exception)
        {
        }
      }

      if (!isCloseBrace)
      {
        IRegion jetMatch = token.match(tokenRange, offset);
        if (jetMatch != null)
        {
          customAnchor = offset > jetMatch.getOffset() + jetMatch.getLength() - 2 ? RIGHT : LEFT;
          return jetMatch;
        }
      }

      if (token == JETScriptletRule.TOKEN || token == JETExpressionRule.TOKEN)
      {
        JavaEditor javaEditor = jetEditor.getJavaEditor();
        if (javaEditor != null)
        {
          ISourceViewer javaSourceViewer = javaEditor.getJavaSourceViewer();
          IDocument javaDocument = javaSourceViewer.getDocument();
          DefaultCharacterPairMatcher javaBracketMatcher = javaEditor.getJavaBracketMatcher();
          if (javaBracketMatcher != null)
          {
            Position correspondingJavaPoint = jetEditor.getCorrespondingJavaPosition(effectiveOffset, 0);
            if (correspondingJavaPoint == null)
            {
              return null;
            }

            IRegion javaRegion = javaBracketMatcher.match(javaDocument, correspondingJavaPoint.offset);
            if (javaRegion == null)
            {
              return null;
            }

            Position correspondingTemplatePointStart = jetEditor.getCorrespondingTemplatePosition(javaRegion.getOffset(), 0);
            if (correspondingTemplatePointStart == null)
            {
              return null;
            }

            Position correspondingTemplatePositionEnd = jetEditor.getCorrespondingTemplatePosition(javaRegion.getOffset() + javaRegion.getLength() - 1, 0);
            if (correspondingTemplatePositionEnd == null)
            {
              return null;
            }

            customAnchor = javaBracketMatcher.getAnchor();
            if (customAnchor != -1)
            {
              return new Region(correspondingTemplatePointStart.offset, correspondingTemplatePositionEnd.offset - correspondingTemplatePointStart.offset + 1);
            }
          }
        }
      }

      customAnchor = -1;
      return null;
    }

    @Override
    public int getAnchor()
    {
      return customAnchor == -1 ? super.getAnchor() : customAnchor;
    }
  }

  static class JETSourceViewerConfiguration extends TextSourceViewerConfiguration
  {
    private final DelegatingContentAssistProcessor delegatingContentAssistProcessor;

    private final DelegatingQuickAssistProcessor delegatingQuickAssistProcessor;

    private final BufferedRuleBasedScanner scanner = new BufferedRuleBasedScanner(1000);

    private final CompletionListener completionListener;

    private final JETEditor jetEditor;

    public JETSourceViewerConfiguration(IPreferenceStore preferenceStore, JETEditor jetEditor)
    {
      super(preferenceStore);

      this.jetEditor = jetEditor;

      delegatingContentAssistProcessor = new DelegatingContentAssistProcessor(jetEditor);
      delegatingQuickAssistProcessor = new DelegatingQuickAssistProcessor(jetEditor);
      completionListener = new CompletionListener(jetEditor);

      scanner.setRules(
        new IRule []{
          new JETCommentRule(),
          new JETExpressionRule(),
          new JETDirectiveRule(),
          new JETScriptletRule(),
          new JETContentRule(),
          new JETTrailingWhitespaceContentRule(),
          new JETCommentErrorRule(),
          new JETExpressionErrorRule(),
          new JETDirectiveErrorRule(),
          new JETScriptletErrorRule() });
    }

    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer)
    {
      PresentationReconciler reconciler = createPresentationReconciler();
      reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));

      DefaultDamagerRepairer damageRepairer = new JETDamageRepairer(jetEditor, scanner);
      reconciler.setDamager(damageRepairer, IDocument.DEFAULT_CONTENT_TYPE);
      reconciler.setRepairer(damageRepairer, IDocument.DEFAULT_CONTENT_TYPE);

      return reconciler;
    }

    protected PresentationReconciler createPresentationReconciler()
    {
      return new JETPresentationReconciler(jetEditor);
    }

    @Override
    @SuppressWarnings("restriction")
    public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer)
    {
      return new org.eclipse.jdt.internal.ui.text.HTMLAnnotationHover(false)
        {
          @Override
          protected boolean isIncluded(Annotation annotation)
          {
            return isShowInVerticalRuler(annotation);
          }
        };
    }

    @Override
    public IUndoManager getUndoManager(ISourceViewer sourceViewer)
    {
      int undoLevel = fPreferenceStore == null || !fPreferenceStore.contains(AbstractDecoratedTextEditorPreferenceConstants.EDITOR_UNDO_HISTORY_SIZE)
        ? 25 : fPreferenceStore.getInt(AbstractDecoratedTextEditorPreferenceConstants.EDITOR_UNDO_HISTORY_SIZE);
      return new CompositeUndoManager(jetEditor, undoLevel);
    }

    @SuppressWarnings("restriction")
    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer)
    {
      ContentAssistant contentAssistant = new ContentAssistant();
      contentAssistant.setInformationControlCreator(getInformationControlCreator(sourceViewer));
      contentAssistant.setContentAssistProcessor(delegatingContentAssistProcessor, IDocument.DEFAULT_CONTENT_TYPE);
      contentAssistant.addCompletionListener(completionListener);

      org.eclipse.jdt.internal.ui.text.ContentAssistPreference.configure(contentAssistant, org.eclipse.jdt.internal.ui.JavaPlugin.getDefault().getPreferenceStore());

      contentAssistant.enableColoredLabels(true);
      return contentAssistant;
    }

    @SuppressWarnings("restriction")
    @Override
    public IQuickAssistAssistant getQuickAssistAssistant(ISourceViewer sourceViewer)
    {
      QuickAssistAssistant assistant = new QuickAssistAssistant();
      assistant.setQuickAssistProcessor(delegatingQuickAssistProcessor);

      try
      {
        // Not available until 3.7, i.e., Helios.
        assistant.getClass().getMethod("setRestoreCompletionProposalSize", IDialogSettings.class).invoke(
          assistant,
          CodeGenUIPlugin.getPlugin().getDialogSettingsSection("quick_assist_proposal_size"));
      }
      catch (Exception exception)
      {
        // Ignore.
      }

      assistant.setInformationControlCreator(new IInformationControlCreator()
        {
          public IInformationControl createInformationControl(Shell parent)
          {
            return new DefaultInformationControl(parent, org.eclipse.ui.internal.editors.text.EditorsPlugin.getAdditionalInfoAffordanceString());
          }
        });
      assistant.addCompletionListener(completionListener);
      return assistant;
    }

    @Override
    public IInformationControlCreator getInformationControlCreator(ISourceViewer sourceViewer)
    {
      return new IInformationControlCreator()
        {
          public IInformationControl createInformationControl(Shell parent)
          {
            return new DefaultInformationControl(parent, false);
          }
        };
    }

    @Override
    public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType)
    {
      return jetEditor.delegatingTextHover;
    }

    @Override
    protected Map<String, IAdaptable> getHyperlinkDetectorTargets(ISourceViewer sourceViewer)
    {
      Map<String, IAdaptable> targets = super.getHyperlinkDetectorTargets(sourceViewer);
      targets.put("org.eclipse.emf.codegen.ui.jetTemplate", jetEditor);
      return targets;
    }

    static class CompletionListener implements ICompletionListener
    {
      private final JETEditor jetEditor;

      public CompletionListener(JETEditor jetEditor)
      {
        this.jetEditor = jetEditor;
      }

      public void assistSessionStarted(ContentAssistEvent event)
      {
        jetEditor.setShowingContentAssist(true);
      }

      public void assistSessionEnded(ContentAssistEvent event)
      {
        jetEditor.setShowingContentAssist(false);
      }

      public void selectionChanged(ICompletionProposal proposal, boolean smartToggle)
      {
      }
    }
  }

  static final class CompositeUndoManager implements IUndoManager, IUndoManagerExtension, ITextInputListener
  {
    private final JETEditor jetEditor;

    private final int undoLevel;

    private int compoundChange;

    private final Map<IDocument, SharedTextViewerUndoManager> managers = new HashMap<IDocument, SharedTextViewerUndoManager>();

    private final IUndoContext undoContext = new IUndoContext()
      {
        public boolean matches(IUndoContext context)
        {
          for (SharedTextViewerUndoManager undoManager : managers.values())
          {
            IUndoContext undoContext = undoManager.getUndoContext();
            if (undoContext.matches(context))
            {
              return true;
            }
          }
          return false;
        }

        public String getLabel()
        {
          return "Shared Undo Context";
        }
      };

    private ITextViewer textViewer;

    private IDocument document;

    public CompositeUndoManager(JETEditor jetEditor, int undoLevel)
    {
      this.jetEditor = jetEditor;
      this.undoLevel = undoLevel;
    }

    protected SharedTextViewerUndoManager getUndoManager()
    {
      return managers.get(document);
    }

    public IUndoContext getUndoContext()
    {
      return undoContext;
    }

    public void connect(ITextViewer textViewer)
    {
      if (this.textViewer == null)
      {
        textViewer.addTextInputListener(this);
      }

      this.textViewer = textViewer;

      inputDocumentAboutToBeChanged(this.document, textViewer.getDocument());
    }

    public void disconnect()
    {
      textViewer.removeTextInputListener(this);
      textViewer = null;
      document = null;

      for (SharedTextViewerUndoManager undoManager : managers.values())
      {
        undoManager.disconnect();
      }

      managers.clear();
    }

    public void reset()
    {
      // This is called when switching documents and we don't want to lose our undo history for each document so don't do anything.
    }

    public void inputDocumentAboutToBeChanged(IDocument oldInput, IDocument newInput)
    {
      SharedTextViewerUndoManager undoManager = getUndoManager();
      if (undoManager != null)
      {
        undoManager.removeListeners();
      }
    }

    public void inputDocumentChanged(IDocument oldInput, IDocument newInput)
    {
      this.document = newInput;
      SharedTextViewerUndoManager undoManager = getUndoManager();
      if (undoManager == null)
      {
        undoManager = new SharedTextViewerUndoManager(undoLevel, jetEditor, textViewer, document);
        managers.put(document, undoManager);
      }
      undoManager.addListeners();
    }

    public void beginCompoundChange()
    {
      if (compoundChange++ == 0)
      {
        for (SharedTextViewerUndoManager sharedTextViewerUndoManager : managers.values())
        {
          sharedTextViewerUndoManager.beginCompoundChange();
        }
      }
    }

    public void endCompoundChange()
    {
      if (--compoundChange == 0)
      {
        for (SharedTextViewerUndoManager sharedTextViewerUndoManager : managers.values())
        {
          sharedTextViewerUndoManager.endCompoundChange();
        }
      }
    }

    public void setMaximalUndoLevel(int undoLevel)
    {
      getUndoManager().setMaximalUndoLevel(undoLevel);
    }

    public boolean undoable()
    {
      return getUndoManager().undoable();
    }

    public boolean redoable()
    {
      return getUndoManager().redoable();
    }

    public void undo()
    {
      getUndoManager().undo();
    }

    public void redo()
    {
      getUndoManager().redo();
    }
  }

  static class SharedTextViewerUndoManager implements IUndoManager, IUndoManagerExtension, IDocumentUndoListener, KeyListener, MouseListener
  {
    private final JETEditor jetEditor;

    private final ITextViewer textViewer;

    private final IDocumentUndoManager documentUndoManager;

    private final IDocument document;

    private int undoLevel;

    public SharedTextViewerUndoManager(int undoLevel, JETEditor jetEditor, ITextViewer textViewer, IDocument document)
    {
      this.undoLevel = undoLevel;
      this.jetEditor = jetEditor;
      this.textViewer = textViewer;
      this.document = document;

      DocumentUndoManagerRegistry.connect(document);
      documentUndoManager = DocumentUndoManagerRegistry.getDocumentUndoManager(document);
      documentUndoManager.connect(this);

      setMaximalUndoLevel(undoLevel);

      documentUndoManager.addDocumentUndoListener(this);
    }

    public void beginCompoundChange()
    {
      documentUndoManager.beginCompoundChange();
    }

    public void endCompoundChange()
    {
      documentUndoManager.endCompoundChange();
    }

    public void addListeners()
    {
      StyledText text = textViewer.getTextWidget();
      if (text != null)
      {
        text.addMouseListener(this);
        text.addKeyListener(this);
      }
    }

    public void removeListeners()
    {
      StyledText text = textViewer.getTextWidget();
      if (text != null)
      {
        text.removeMouseListener(this);
        text.removeKeyListener(this);
      }
    }

    public void setMaximalUndoLevel(int newUndoLevel)
    {
      undoLevel = Math.max(0, newUndoLevel);
      documentUndoManager.setMaximalUndoLevel(undoLevel);
    }

    public void connect(ITextViewer newTextViewer)
    {
      // This is never called because we connect during construction.
    }

    public void disconnect()
    {
      removeListeners();
      documentUndoManager.disconnect(this);
      DocumentUndoManagerRegistry.disconnect(document);
      documentUndoManager.removeDocumentUndoListener(this);
    }

    public void reset()
    {
      documentUndoManager.reset();
    }

    public boolean redoable()
    {
      return documentUndoManager.redoable();
    }

    public boolean undoable()
    {
      return documentUndoManager.undoable();
    }

    public void redo()
    {
      try
      {
        documentUndoManager.redo();
      }
      catch (ExecutionException ex)
      {
        openErrorDialog("Redo Failed", ex);
      }
    }

    public void undo()
    {
      try
      {
        documentUndoManager.undo();
      }
      catch (ExecutionException ex)
      {
        openErrorDialog("Undo Failed", ex);
      }
    }

    private void selectAndReveal(int offset, int length)
    {
      jetEditor.selectAndReveal(document, offset, length);
    }

    public IUndoContext getUndoContext()
    {
      return documentUndoManager.getUndoContext();
    }

    private void openErrorDialog(final String title, final Exception ex)
    {
      Shell shell = null;
      StyledText st = textViewer.getTextWidget();
      if (st != null && !st.isDisposed())
      {
        shell = st.getShell();
      }

      if (Display.getCurrent() != null)
      {
        MessageDialog.openError(shell, title, ex.getLocalizedMessage());
      }
      else
      {
        Display display;
        final Shell finalShell = shell;
        if (finalShell != null)
        {
          display = finalShell.getDisplay();
        }
        else
        {
          display = Display.getDefault();
        }
        display.syncExec(new Runnable()
          {
            public void run()
            {
              MessageDialog.openError(finalShell, title, ex.getLocalizedMessage());
            }
          });
      }
    }

    public void mouseDoubleClick(MouseEvent event)
    {
    }

    public void mouseUp(MouseEvent event)
    {
    }

    public void mouseDown(MouseEvent event)
    {
      if (event.button == 1)
      {
        documentUndoManager.commit();
      }
    }

    public void keyReleased(KeyEvent event)
    {
    }

    public void keyPressed(KeyEvent event)
    {
      switch (event.keyCode)
      {
        case SWT.ARROW_UP:
        case SWT.ARROW_DOWN:
        case SWT.ARROW_LEFT:
        case SWT.ARROW_RIGHT:
        {
          documentUndoManager.commit();
          break;
        }
      }
    }

    public void documentUndoNotification(DocumentUndoEvent event)
    {
      int eventType = event.getEventType();
      if ((eventType & DocumentUndoEvent.ABOUT_TO_UNDO) != 0 || (eventType & DocumentUndoEvent.ABOUT_TO_REDO) != 0)
      {
        if (event.isCompound())
        {
          ((ITextViewerExtension)textViewer).setRedraw(false);
        }
      }
      else if (((eventType & DocumentUndoEvent.UNDONE) != 0) || ((eventType & DocumentUndoEvent.REDONE) != 0))
      {
        if (event.isCompound())
        {
          ((ITextViewerExtension)textViewer).setRedraw(true);
        }

        StyledText widget = textViewer.getTextWidget();
        if (widget != null && !widget.isDisposed() && widget.isFocusControl())
        {
          selectAndReveal(event.getOffset(), event.getText() == null ? 0 : event.getText().length());
        }
      }
    }
  }

  static class JETDamageRepairer extends DefaultDamagerRepairer
  {
    private final JETEditor jetEditor;

    private int capacity = 8;

    private int size = 0;

    private int[] offsets = new int [capacity];

    private JETToken[] tokens = new JETToken [capacity];

    public JETDamageRepairer(JETEditor jetEditor, ITokenScanner scanner)
    {
      super(scanner);
      this.jetEditor = jetEditor;
    }

    private void ensureCapacity(int capacity)
    {
      if (this.capacity < capacity)
      {
        int newCapacity = capacity << 1;
        int[] newOffsets = new int [newCapacity];
        JETToken[] newTokens = new JETToken [newCapacity];
        System.arraycopy(offsets, 0, newOffsets, 0, this.capacity);
        System.arraycopy(tokens, 0, newTokens, 0, this.capacity);

        this.offsets = newOffsets;
        this.tokens = newTokens;
        this.capacity = newCapacity;
      }
    }

    private void add(int offset, int length, JETToken token)
    {
      ensureCapacity(size + 2);
      tokens[size] = token;
      offsets[size] = offset;
      tokens[++size] = null;
      offsets[size] = offset + length;
    }

    @Override
    public void createPresentation(TextPresentation presentation, ITypedRegion region)
    {
      try
      {
        super.createPresentation(presentation, region);
      }
      finally
      {
        int[] offsets = new int [size + 1];
        JETToken[] tokens = new JETToken [size + 1];

        System.arraycopy(this.offsets, 0, offsets, 0, size + 1);
        System.arraycopy(this.tokens, 0, tokens, 0, size + 1);

        jetEditor.setTokenData(offsets, tokens);

        size = 0;
        this.offsets[0] = 0;
        this.offsets[1] = 0;
      }
    }

    @Override
    protected void addRange(TextPresentation presentation, int offset, int length, TextAttribute textAttribute)
    {
      if (textAttribute instanceof ColorManager.JETTextAttribute)
      {
        JETEditor.ColorManager.JETTextAttribute jetTextAttribute = (ColorManager.JETTextAttribute)textAttribute;
        JETToken token = jetTextAttribute.getToken();
        add(offset, length, token);

        int style = textAttribute.getStyle();
        int fontStyle = style & (SWT.ITALIC | SWT.BOLD | SWT.NORMAL);
        StyleRange styleRange = new StyleRange(offset, length, textAttribute.getForeground(), textAttribute.getBackground(), fontStyle);
        styleRange.strikeout = (style & TextAttribute.STRIKETHROUGH) != 0;
        styleRange.underline = (style & TextAttribute.UNDERLINE) != 0;
        styleRange.font = textAttribute.getFont();
        styleRange.data = token;
        styleRange.borderStyle = jetTextAttribute.getBorderStyle();
        if (styleRange.borderStyle != SWT.NONE)
        {
          styleRange.borderColor = styleRange.foreground;
        }

        presentation.addStyleRange(styleRange);
      }
    }
  }

  static class JETTokenRegion
  {
    private final JETToken token;

    private int offset;

    private int length;

    public JETTokenRegion(int offset, int length, JETToken token)
    {
      this.offset = offset;
      this.length = length;
      this.token = token;
    }

    public JETToken getToken()
    {
      return token;
    }

    public void shrinkToJava(IDocument document)
    {
      if (token == JETScriptletRule.TOKEN)
      {
        int delta = 0;

        try
        {
          while (Character.isWhitespace(document.getChar(offset + delta)))
          {
            ++delta;
          }
        }
        catch (BadLocationException exception)
        {
          // Should not happen.
        }

        offset += delta + 2;
        length -= delta + 2;
      }
      else if (token == JETExpressionRule.TOKEN)
      {
        offset += 3;
        length -= 5;
      }
    }

    public void expand()
    {
      --offset;
      length += 2;
    }

    public int getOffset()
    {
      return offset;
    }

    public void setOffset(int offset)
    {
      this.offset = offset;
    }

    public int getLength()
    {
      return length;
    }

    public void setLength(int length)
    {
      this.length = length;
    }
  }

  static class JETPresentationReconciler extends PresentationReconciler
  {
    private final JETEditor jetEditor;

    private IDocument document;

    public JETPresentationReconciler(JETEditor jetEditor)
    {
      this.jetEditor = jetEditor;
    }

    @Override
    public TextPresentation createPresentation(IRegion damage, IDocument document)
    {
      if (document != this.document)
      {
        setDocument(document);
      }

      List<JETJavaItem> javaItems = null;
      List<JETRootItem> skippedItems = null;
      ITextViewerExtension5 javaViewer = null;
      StyledText javaStyledText = null;
      if (jetEditor.isJavaEditorInSync())
      {
        JETCompilationUnit compilerResult = jetEditor.getCompilationUnit();
        if (compilerResult != null)
        {
          ISourceViewer underlyingSourceViewer = jetEditor.getJavaEditor().getJavaSourceViewer();
          if (compilerResult.getJavaCompilationUnit().equals(underlyingSourceViewer.getDocument().get()))
          {
            int fileID = jetEditor.getFileID();
            int templateOffset = damage.getOffset();
            int templateLength = damage.getLength();
            javaItems = compilerResult.getJavaItems(fileID, templateOffset, templateLength);
            skippedItems = compilerResult.getSkippedItems(fileID, templateOffset, templateLength);
            javaStyledText = underlyingSourceViewer.getTextWidget();
            javaViewer = (ITextViewerExtension5)underlyingSourceViewer;
          }
        }
      }

      // Create the full presentation because we can only find tokens if we start from the start.
      // 
      Region fullRegion = new Region(0, document.getLength());
      TextPresentation fullTextPresentation = super.createPresentation(fullRegion, document);

      if (javaItems == null && damage.equals(fullRegion))
      {
        return fullTextPresentation;
      }

      // Filter these down to the ones within the actual damaged region because updating so many takes long.
      //
      TextPresentation textPresentation = new TextPresentation(damage, 1000);
      int offset = damage.getOffset();
      int end = offset + damage.getLength();
      for (Iterator<StyleRange> i = fullTextPresentation.getAllStyleRangeIterator(); i.hasNext();)
      {
        StyleRange styleRange = i.next();
        if (styleRange.start + styleRange.length >= offset && styleRange.start <= end)
        {
          if (javaItems == null)
          {
            textPresentation.addStyleRange(styleRange);
          }
          else
          {
            applySemanticStyle(textPresentation, styleRange, javaItems, skippedItems, javaStyledText, javaViewer);
          }
        }
      }

      return textPresentation;
    }

    private void applySemanticStyle(
      TextPresentation textPresentation,
      StyleRange styleRange,
      List<JETJavaItem> javaItems,
      List<JETRootItem> skippedItems,
      StyledText textWidget,
      ITextViewerExtension5 javaModelViewer)
    {
      IToken token = (IToken)styleRange.data;
      int start = styleRange.start;
      int end = styleRange.start + styleRange.length;
      for (JETRootItem jetRootItem : skippedItems)
      {
        if (jetRootItem.getStartOffset() < end && jetRootItem.getStopOffset() > start)
        {
          styleRange.strikeout = true;
          Color blendedColor = ColorManager.INSTANCE.getBlendedColor(styleRange.foreground, styleRange.background);
          styleRange.strikeoutColor = blendedColor;
          styleRange.foreground = blendedColor;
          break;
        }
      }

      Color defaultForeground = textWidget.getForeground();

      // Only do this work for scriptlets and expressions.
      if (token == JETScriptletRule.TOKEN || token == JETExpressionRule.TOKEN)
      {
        for (JETJavaItem javaItem : javaItems)
        {
          int delta = javaItem.getStartOffset() - start;
          if (delta >= 0 && javaItem.getStopOffset() <= end)
          {
            int javaOffset = javaItem.getJavaOffset();
            int javaLength = javaItem.getJavaLength();
            int javaWidgetOffset = javaModelViewer.modelOffset2WidgetOffset(javaOffset);

            StyleRange[] styleRanges = textWidget.getStyleRanges(javaWidgetOffset, javaLength);

            if (trace)
            {
              String text = javaItem.getText();
              String javaText = textWidget.getTextRange(javaWidgetOffset, javaLength);
              if (!text.equals(javaText))
              {
                if (trace)
                {
                  System.out.println("mismatched text when applying styles");
                }
                break;
              }
            }

            List<StyleRange> newStyleRanges = new ArrayList<StyleRange>();

            // Whatever text range appears before the start of the Java style range keeps the existing style.
            // This will definitely include the leading <%= or the leading <% and any pure whitespace before it.
            //
            StyleRange leadingNewStyleRanage = (StyleRange)styleRange.clone();
            leadingNewStyleRanage.length = delta;
            newStyleRanges.add(leadingNewStyleRanage);
            start += delta;

            int consumedLength = 0;
            for (StyleRange javaStyleRange : styleRanges)
            {
              StyleRange newStyleRange = (StyleRange)styleRange.clone();
              newStyleRange.start = start + consumedLength;
              newStyleRange.length = javaStyleRange.length;
              if (javaStyleRange.foreground == null || !javaStyleRange.foreground.equals(defaultForeground))
              {
                newStyleRange.foreground = javaStyleRange.foreground;
              }
              newStyleRange.foreground = newStyleRange.strikeout
                ? ColorManager.INSTANCE.getBlendedColor(newStyleRange.foreground, newStyleRange.background) : newStyleRange.foreground;
              newStyleRange.fontStyle = javaStyleRange.fontStyle;
              consumedLength += newStyleRange.length;
              newStyleRanges.add(newStyleRange);
            }

            // This will include the style range for the %>
            //
            StyleRange trailingNewStyleRange = (StyleRange)styleRange.clone();
            trailingNewStyleRange.start = start + consumedLength;
            trailingNewStyleRange.length = end - trailingNewStyleRange.start;
            newStyleRanges.add(trailingNewStyleRange);

            for (StyleRange newStyleRange : newStyleRanges)
            {
              textPresentation.addStyleRange(newStyleRange);
            }

            return;
          }
        }
      }

      // If we don't find a corresponding Java item, just handle it like normal.
      textPresentation.addStyleRange(styleRange);
    }

    protected void setDocument(IDocument document)
    {
      setDocumentToDamagers(document);
      setDocumentToRepairers(document);
      this.document = document;
    }
  }

  static class BracketInserter implements VerifyKeyListener
  {
    private final JETEditor jetEditor;

    private final ISelectionProvider jetSelectionProvider;

    public BracketInserter(JETEditor jetEditor)
    {
      this.jetEditor = jetEditor;
      jetSelectionProvider = jetEditor.getSelectionProvider();
    }

    private boolean edit(ITextViewer jetSourceViewer, int offset, int length, String replacement, int selectionOffset)
    {
      try
      {
        IDocument document = jetSourceViewer.getDocument();
        document.replace(offset, length, replacement);
        jetSourceViewer.setSelectedRange(selectionOffset, 0);
        return false;
      }
      catch (BadLocationException exception)
      {
        return true;
      }
    }

    public void verifyKey(VerifyEvent event)
    {
      if (event.doit && jetEditor.getInsertMode() == SMART_INSERT && !isMultilineSelection())
      {
        ISourceViewer jetSourceViewer = jetEditor.getSourceViewer();
        final Point selection = jetSourceViewer.getSelectedRange();
        final int offset = selection.x;
        final int length = selection.y;
        if (length == 0 && offset > 0)
        {
          if (event.character == '%')
          {
            JETToken tokenAtSelection = jetEditor.getTokenAtSelection(0);
            JETToken tokenBeforeSelection = jetEditor.getTokenAtSelection(-1);
            if (tokenAtSelection != null && tokenAtSelection.getBaseToken() == JETContentRule.TOKEN
                || tokenBeforeSelection != null && tokenBeforeSelection.getBaseToken() == JETContentRule.TOKEN)
            {
              String text = jetSourceViewer.getTextWidget().getTextRange(offset - 1, 1);
              if ("<".equals(text))
              {
                event.doit = edit(jetSourceViewer, offset, 0, "%%>", offset + 1);
              }
            }
          }
          else if (event.character == '-')
          {
            JETToken token = jetEditor.getTokenAt(offset);
            if (token == JETScriptletRule.TOKEN)
            {
              IDocument document = jetSourceViewer.getDocument();
              try
              {
                if ("<%%>".equals(document.get(offset - 2, 4)))
                {
                  event.doit = edit(jetSourceViewer, offset, 0, "--", offset + 1);
                }
              }
              catch (BadLocationException e)
              {
              }
            }
          }
          else if (event.character == '>')
          {
            JETToken tokenAtSelection = jetEditor.getTokenAtSelection(0);
            if (tokenAtSelection == JETScriptletRule.TOKEN)
            {
              IDocument document = jetSourceViewer.getDocument();
              try
              {
                char character = document.getChar(offset - 1);
                if (character == '%')
                {
                  int documentLength = document.getLength();
                  StringBuilder additionalWhiteSpace = new StringBuilder();
                  for (int i = offset; i < documentLength; ++i)
                  {
                    character = document.getChar(i);
                    if (!Character.isWhitespace(character))
                    {
                      // {
                      if (character != '}')
                      {
                        additionalWhiteSpace.setLength(0);
                      }
                      break;
                    }
                    additionalWhiteSpace.append(character);
                  }

                  event.doit = edit(jetSourceViewer, offset, additionalWhiteSpace.length(), ">" + additionalWhiteSpace + "<%", offset + 1);
                }
              }
              catch (BadLocationException e)
              {
              }
            }
          }
          else if (event.keyCode == 0x8)
          {
            JETTokenRegion tokenRegion = jetEditor.getTokenRegionAt(offset);
            if (tokenRegion != null)
            {
              JETToken token = tokenRegion.getToken();
              if (token == JETCommentRule.TOKEN)
              {
                if (offset - tokenRegion.getOffset() == 3 && tokenRegion.getLength() == 6)
                {
                  event.doit = edit(jetSourceViewer, offset - 1, 2, "", offset - 1);
                }
              }
              else if (token == JETScriptletRule.TOKEN)
              {
                if (offset - tokenRegion.getOffset() == 2 && tokenRegion.getLength() == 4)
                {
                  event.doit = edit(jetSourceViewer, offset - 1, 3, "", offset - 1);
                }
              }
            }
          }
        }
      }
    }

    private boolean isMultilineSelection()
    {
      ISelection selection = jetSelectionProvider.getSelection();
      if (selection instanceof ITextSelection)
      {
        ITextSelection ts = (ITextSelection)selection;
        return ts.getStartLine() != ts.getEndLine();
      }
      return false;
    }
  }

  static final class JETToken extends Token
  {
    private final String type;

    private final int textStyle;

    private final int borderStyle;

    private final JETToken baseToken;

    public JETToken(String type)
    {
      this(type, SWT.NONE, SWT.NONE);
    }

    public JETToken(String type, int textStyle, int borderStyle)
    {
      super(null);
      this.type = type;
      this.textStyle = textStyle;
      this.borderStyle = borderStyle;
      this.baseToken = this;
      ColorManager.INSTANCE.register(this);
    }

    public JETToken(JETToken baseToken, String type, int textStyle)
    {
      this(baseToken, type, textStyle, SWT.NONE);
    }

    public JETToken(JETToken baseToken, String type, int textStyle, int borderStyle)
    {
      super(null);
      this.type = type + '.' + baseToken.getType();
      this.textStyle = textStyle;
      this.baseToken = baseToken;
      this.borderStyle = borderStyle;
      ColorManager.INSTANCE.register(this);
    }

    public final String getType()
    {
      return type;
    }

    public final JETToken getBaseToken()
    {
      return baseToken;
    }

    public final int getTextStyle()
    {
      return textStyle;
    }

    public final int getBorderStyle()
    {
      return borderStyle;
    }

    public final void setData(TextAttribute textAttribute)
    {
      super.setData(textAttribute);
    }

    @Override
    public final void setData(Object data)
    {
      super.setData((TextAttribute)data);
    }

    @Override
    public final String toString()
    {
      return "JETToken:" + type;
    }

    public final IRegion match(Point tokenRange, int offset)
    {
      int prefixLength = -1;
      if (type.equals("scriptlet"))
      {
        prefixLength = 2;
      }
      else if (type.equals("expression") || type.equals("directive") || type.equals("comment"))
      {
        prefixLength = 3;
      }

      if (prefixLength != -1)
      {
        if (offset == tokenRange.x + tokenRange.y || offset == tokenRange.x + 1)
        {
          return new Region(tokenRange.x, tokenRange.y);
        }

        if (offset == tokenRange.x + tokenRange.y - 1 || offset == tokenRange.x + 2)
        {
          return new Region(tokenRange.x + 1, tokenRange.y - 2);
        }

        if (prefixLength == 3 && (offset == tokenRange.x + tokenRange.y - 1 || offset == tokenRange.x + 3))
        {
          return new Region(tokenRange.x + 2, tokenRange.y - 3);
        }
      }

      return null;
    }
  }

  static abstract class JETRule
  {
    private final JETToken successToken;

    private final char[] prefix;

    private int count;

    public JETRule(JETToken successToken, char[] prefix)
    {
      this.successToken = successToken;
      this.prefix = prefix;
    }

    protected int getCount()
    {
      return count;
    }

    public final JETToken getSuccessToken()
    {
      return successToken;
    }

    protected boolean scanForStart(ICharacterScanner scanner)
    {
      for (int i = 0; i < prefix.length; ++i)
      {
        if (prefix[i] != read(scanner))
        {
          return false;
        }
      }
      return true;
    }

    protected boolean scanForEnd(ICharacterScanner scanner)
    {
      for (int character = read(scanner); character != ICharacterScanner.EOF; character = read(scanner))
      {
        if (character == '%')
        {
          if (read(scanner) == '>')
          {
            return true;
          }

          unread(scanner);
        }
      }

      return false;
    }

    protected int read(ICharacterScanner scanner)
    {
      ++count;
      int read = scanner.read();

      if (trace)
      {
        if (read == ICharacterScanner.EOF)
        {
          System.out.print("'eof'");
        }
        else if (read == ' ')
        {
          System.out.print("~");
        }
        else if (read == '\n')
        {
          System.out.print("\\n");
        }
        else if (read == '\r')
        {
          System.out.print("\\r");
        }
        else
        {
          System.out.print((char)read);
        }
      }

      return read;
    }

    protected void unread(ICharacterScanner scanner)
    {
      --count;
      scanner.unread();
    }

    protected void unreadAll(ICharacterScanner scanner)
    {
      while (count-- > 0)
      {
        scanner.unread();
      }
      count = 0;
    }

    protected boolean isGreedy()
    {
      return true;
    }

    protected void skipLeading(ICharacterScanner scanner)
    {
      if (trace)
      {
        System.out.println("-----------");
      }

      if (isGreedy())
      {
        for (int read = read(scanner); read == ' ' || read == '\t'; read = read(scanner))
        {
        }

        unread(scanner);
      }
    }

    public final IToken evaluate(ICharacterScanner scanner)
    {
      try
      {
        return doEvaluate(scanner);
      }
      finally
      {
        count = 0;
      }
    }

    protected IToken doEvaluate(ICharacterScanner scanner)
    {
      skipLeading(scanner);
      if (scanForStart(scanner))
      {
        if (scanForEnd(scanner))
        {
          return getSuccessToken();
        }
      }

      unreadAll(scanner);

      return Token.UNDEFINED;
    }
  }

  static class JETDirectiveRule extends JETRule implements IRule
  {
    private static final char[] PREFIX = new char []{ '<', '%', '@' };

    public static final JETToken TOKEN = new JETToken("directive");

    @Override
    protected boolean isGreedy()
    {
      return false;
    }

    public JETDirectiveRule()
    {
      super(TOKEN, PREFIX);
    }
  }

  static class JETScriptletRule extends JETRule implements IRule
  {
    private static final char[] PREFIX = new char []{ '<', '%' };

    public static final JETToken TOKEN = new JETToken("scriptlet");

    public JETScriptletRule()
    {
      super(TOKEN, PREFIX);
    }

    @Override
    protected boolean scanForStart(ICharacterScanner scanner)
    {
      if (super.scanForStart(scanner))
      {
        int read = read(scanner);
        unread(scanner);
        if (read == '@' || read == '=' || read == '-')
        {
          return false;
        }
        else
        {
          return true;
        }
      }
      else
      {
        return false;
      }
    }
  }

  static class JETCommentRule extends JETRule implements IRule
  {
    private static final char[] PREFIX = new char []{ '<', '%', '-' };

    public static final JETToken TOKEN = new JETToken("comment");

    public JETCommentRule()
    {
      super(TOKEN, PREFIX);
    }

    @Override
    protected boolean scanForEnd(ICharacterScanner scanner)
    {
      for (int character = read(scanner); character != ICharacterScanner.EOF; character = read(scanner))
      {
        if (character == '-')
        {
          if (read(scanner) == '%')
          {
            if (read(scanner) == '>')
            {
              return true;
            }

            unread(scanner);
          }
          else
          {
            unread(scanner);
          }
        }
      }

      return false;
    }
  }

  static class JETExpressionRule extends JETRule implements IRule
  {
    private static final char[] PREFIX = new char []{ '<', '%', '=' };

    public static final JETToken TOKEN = new JETToken("expression");

    public JETExpressionRule()
    {
      super(TOKEN, PREFIX);
    }

    @Override
    protected boolean isGreedy()
    {
      return false;
    }
  }

  static class JETContentRule extends JETRule implements IRule
  {
    private static final char[] PREFIX = new char [0];

    public static final JETToken TOKEN = new JETToken("content");

    public JETContentRule()
    {
      super(TOKEN, PREFIX);
    }

    @Override
    protected IToken doEvaluate(ICharacterScanner scanner)
    {
      int trailingWhitespaceCount = 0;
      for (int read = read(scanner); read != ICharacterScanner.EOF; read = read(scanner))
      {
        if (read == '<')
        {
          if (read(scanner) == '%')
          {
            unread(scanner);
            unread(scanner);
            if (getCount() == 0)
            {
              return Token.UNDEFINED;
            }
            else
            {
              return getSuccessToken();
            }
          }
          else
          {
            unread(scanner);
          }
        }
        else if (read == '\n')
        {
          if (trailingWhitespaceCount != 0)
          {
            for (int i = 0; i <= trailingWhitespaceCount; ++i)
            {
              unread(scanner);
            }

            return getCount() == 0 ? Token.UNDEFINED : getSuccessToken();
          }
          else
          {
            return getSuccessToken();
          }
        }
        else if (read == '\r')
        {
          if (trailingWhitespaceCount != 0)
          {
            ++trailingWhitespaceCount;
          }
        }
        else if (read == ' ' || read == '\t')
        {
          ++trailingWhitespaceCount;
        }
        else
        {
          trailingWhitespaceCount = 0;
        }
      }

      if (getCount() == 1)
      {
        return Token.EOF;
      }
      else
      {
        if (trailingWhitespaceCount != 0)
        {
          for (int i = 0; i <= trailingWhitespaceCount; ++i)
          {
            unread(scanner);
          }
        }
        return getCount() == 0 ? Token.UNDEFINED : getSuccessToken();
      }
    }
  }

  static class JETTrailingWhitespaceContentRule extends JETRule implements IRule
  {
    private static final char[] PREFIX = new char [0];

    public static final JETToken TOKEN = new JETToken(JETContentRule.TOKEN, "trailing-whitespace-content", SWT.NONE, SWT.BORDER_SOLID);

    public JETTrailingWhitespaceContentRule()
    {
      super(TOKEN, PREFIX);
    }

    @Override
    protected IToken doEvaluate(ICharacterScanner scanner)
    {
      for (int read = read(scanner); read != ICharacterScanner.EOF; read = read(scanner))
      {
        if (read == '\n')
        {
          return getSuccessToken();
        }
        else if (read != ' ' && read != '\t' && read != '\r')
        {
          unreadAll(scanner);
          return Token.UNDEFINED;
        }
      }

      return getCount() == 1 ? Token.EOF : getSuccessToken();
    }
  }

  static class JETBaseErrorRule extends JETRule implements IRule
  {
    public JETBaseErrorRule(JETToken successToken, char[] prefix)
    {
      super(successToken, prefix);
    }

    @Override
    protected IToken doEvaluate(ICharacterScanner scanner)
    {
      if (scanForStart(scanner))
      {
        for (int read = read(scanner); read != ICharacterScanner.EOF; read = read(scanner))
        {
        }

        return getSuccessToken();
      }

      if (read(scanner) == ICharacterScanner.EOF)
      {
        return Token.EOF;
      }
      else
      {
        unreadAll(scanner);
        return Token.UNDEFINED;
      }
    }
  }

  static class JETScriptletErrorRule extends JETBaseErrorRule
  {
    public static final JETToken TOKEN = new JETToken(JETScriptletRule.TOKEN, "error", TextAttribute.STRIKETHROUGH);

    public JETScriptletErrorRule()
    {
      super(TOKEN, JETScriptletRule.PREFIX);
    }
  }

  static class JETExpressionErrorRule extends JETBaseErrorRule
  {
    public static final JETToken TOKEN = new JETToken(JETExpressionRule.TOKEN, "error", TextAttribute.STRIKETHROUGH);

    public JETExpressionErrorRule()
    {
      super(TOKEN, JETExpressionRule.PREFIX);
    }
  }

  static class JETCommentErrorRule extends JETBaseErrorRule
  {
    public static final JETToken TOKEN = new JETToken(JETCommentRule.TOKEN, "error", TextAttribute.STRIKETHROUGH);

    public JETCommentErrorRule()
    {
      super(TOKEN, JETCommentRule.PREFIX);
    }
  }

  static class JETDirectiveErrorRule extends JETBaseErrorRule
  {
    public static final JETToken TOKEN = new JETToken(JETDirectiveRule.TOKEN, "error", TextAttribute.STRIKETHROUGH);

    public JETDirectiveErrorRule()
    {
      super(TOKEN, JETDirectiveRule.PREFIX);
    }
  }

  @SuppressWarnings("restriction")
  static class JETBreadcrumbViewer extends org.eclipse.jdt.internal.ui.javaeditor.breadcrumb.BreadcrumbViewer
  {
    public JETBreadcrumbViewer(Composite parent, int style)
    {
      super(parent, style);

      addDoubleClickListener(new IDoubleClickListener()
        {
          public void doubleClick(DoubleClickEvent event)
          {
            Object element = ((IStructuredSelection)event.getSelection()).getFirstElement();
            Widget item = findItem(((ITreeContentProvider)getContentProvider()).getParent(element));
            if (item != null)
            {
              try
              {
                Method openDropDownMenuMethod = item.getClass().getDeclaredMethod("openDropDownMenu");
                openDropDownMenuMethod.setAccessible(true);
                openDropDownMenuMethod.invoke(item);
              }
              catch (Exception ex)
              {
                // Ignore.
              }
            }
          }
        });
    }

    @Override
    protected void configureDropDownViewer(TreeViewer viewer, Object input)
    {
    }

    @Override
    public Control getControl()
    {
      // Ensure the callers don't get warnings about restricted access.
      return super.getControl();
    }
  }

  static class JETTextSelectionNavigationLocation extends NavigationLocation
  {
    private static final String TAG_X = "x";

    private static final String TAG_Y = "y";

    private static final String TAG_DOCUMENT_ID = "document_id";

    private static final String TAG_INFO = "info";

    private static final String INFO_DELETED = "deleted";

    private static final String INFO_NOT_DELETED = "not_deleted";

    private static final String CATEGORY = "__navigation_" + JETTextSelectionNavigationLocation.class.hashCode();

    private static final IPositionUpdater POSITION_UPDATER = new DefaultPositionUpdater(CATEGORY);

    private Position position;

    private String documentID;

    private IDocument document;

    private Position savedPosition;

    public JETTextSelectionNavigationLocation(JETEditor jetEditor, boolean initialize)
    {
      super(jetEditor);

      if (initialize)
      {
        ITextSelection selection = (ITextSelection)jetEditor.getSelectionProvider().getSelection();
        IDocument document = getDocument(jetEditor);
        Position position = new Position(selection.getOffset(), selection.getLength());
        if (installOnDocument(document, position, CATEGORY, POSITION_UPDATER))
        {
          this.document = document;
          this.documentID = getDocumentID(jetEditor);
          Assert.isNotNull(this.documentID, "The document ID must be specified");
          this.position = position;
          if (!jetEditor.isDirty())
          {
            savedPosition = new Position(position.offset, position.length);
          }
        }
      }
    }

    public String getDocumentID()
    {
      return documentID;
    }

    private String getDocumentID(JETEditor jetEditor)
    {
      return jetEditor.getDocumentManager().getDocumentID();
    }

    private IDocument getDocument(JETEditor jetEditor)
    {
      return jetEditor.getSourceViewer().getDocument();
    }

    @Override
    public void dispose()
    {
      free();
      super.dispose();
    }

    private void free()
    {
      uninstallFromDocument(document, position, CATEGORY, POSITION_UPDATER);
      document = null;
      position = null;
      savedPosition = null;
      documentID = null;

    }

    @Override
    public void releaseState()
    {
      free();
      super.releaseState();
    }

    public boolean mergeInto(INavigationLocation location)
    {
      if (location == null || location.getClass() != getClass())
      {
        return false;
      }

      JETTextSelectionNavigationLocation jetLocation = (JETTextSelectionNavigationLocation)location;
      if (!jetLocation.documentID.equals(documentID))
      {
        return false;
      }

      if (position == null || position.isDeleted || document == null)
      {
        return true;
      }

      if (jetLocation.position == null || jetLocation.position.isDeleted)
      {
        uninstallFromDocument(document, position, CATEGORY, POSITION_UPDATER);
        jetLocation.document = document;
        jetLocation.position = position;
        jetLocation.savedPosition = savedPosition;
        jetLocation.documentID = documentID;
        return true;
      }

      if (jetLocation.document == document)
      {
        if (jetLocation.position.overlapsWith(position.offset, position.length) || position.offset + position.length == jetLocation.position.offset
            || jetLocation.position.offset + jetLocation.position.length == position.offset)
        {
          jetLocation.position.offset = position.offset;
          jetLocation.position.length = position.length;
          return true;
        }
      }

      return false;
    }

    public void restoreLocation()
    {
      if (position != null && !position.isDeleted)
      {
        IEditorPart part = getEditorPart();
        if (part instanceof JETEditor)
        {
          JETEditor editor = (JETEditor)getEditorPart();
          editor.selectAndReveal(this, position.offset, position.length);
        }
      }
    }

    public void saveState(IMemento memento)
    {
      if (savedPosition != null)
      {
        memento.putInteger(TAG_X, savedPosition.offset);
        memento.putInteger(TAG_Y, savedPosition.length);
        memento.putString(TAG_INFO, (savedPosition.isDeleted ? INFO_DELETED : INFO_NOT_DELETED));
        memento.putString(TAG_DOCUMENT_ID, documentID);
      }
    }

    public void restoreState(IMemento memento)
    {
      IEditorPart part = getEditorPart();
      if (part instanceof JETEditor)
      {
        documentID = memento.getString(TAG_DOCUMENT_ID);

        Integer offset = memento.getInteger(TAG_X);
        Integer length = memento.getInteger(TAG_Y);
        String deleted = memento.getString(TAG_INFO);

        if (offset != null && length != null)
        {
          Position position = new Position(offset.intValue(), length.intValue());
          this.position = position;
          if (deleted != null)
          {
            position.isDeleted = INFO_DELETED.equals(deleted) ? true : false;
          }
        }
      }
    }

    public void restoreDocument(JETEditor jetEditor, IDocument document)
    {
      this.document = document;
      if (installOnDocument(document, position, CATEGORY, POSITION_UPDATER))
      {
        if (!jetEditor.isDirty())
        {
          savedPosition = new Position(position.offset, position.length);
        }
      }
    }

    public void partSaved(IEditorPart part)
    {
      savedPosition = position == null || position.isDeleted() ? null : new Position(position.offset, position.length);
    }

    private boolean samePosition(JETEditor jetEditor)
    {
      if (position == null)
      {
        return true;
      }

      if (position.isDeleted)
      {
        return false;
      }

      if (!getDocumentID(jetEditor).equals(documentID))
      {
        return false;
      }

      ITextSelection textSelection = (ITextSelection)jetEditor.getSelectionProvider().getSelection();
      if (textSelection.getOffset() == position.offset && textSelection.getLength() == position.length)
      {
        String text = textSelection.getText();
        if (text != null)
        {
          try
          {
            return text.equals(document.get(position.offset, position.length));
          }
          catch (BadLocationException exception)
          {
          }
        }
      }

      return false;
    }

    public void update()
    {
      IEditorPart part = getEditorPart();
      if (part instanceof JETEditor)
      {
        JETEditor textEditor = (JETEditor)part;

        if (!samePosition(textEditor))
        {
          ITextSelection selection = (ITextSelection)textEditor.getSelectionProvider().getSelection();
          if (selection.getOffset() != 0 || selection.getLength() != 0)
          {
            position.offset = selection.getOffset();
            position.length = selection.getLength();
            position.isDeleted = false;

            if (!part.isDirty())
            {
              savedPosition = new Position(position.offset, position.length);
            }
          }
        }
      }
    }

    @Override
    public String toString()
    {
      return "Selection<" + position + ">";
    }
  }

  static class JETDynamicCompilerJob extends Job implements IDocumentListener
  {
    private final JETEditor jetEditor;

    private final IFile file;

    private volatile boolean disabled;

    public JETDynamicCompilerJob(JETEditor jetEditor, IFile file)
    {
      super("JET Dynamic Compiler");
      setSystem(true);
      this.jetEditor = jetEditor;
      this.file = file;
    }

    public void setDisabled(boolean disabled)
    {
      this.disabled = disabled;
      if (disabled)
      {
        cancel();
      }
      else
      {
        documentChanged(null);
      }
    }

    @Override
    protected IStatus run(IProgressMonitor monitor)
    {
      if (!disabled)
      {
        try
        {
          final WorkingCopyCompilationMonitor compilationMonitor = new WorkingCopyCompilationMonitor();
          JETCompileTemplateOperation jetCompileTemplateOperation = new JETCompileTemplateOperation(file, compilationMonitor)
            {
              @Override
              public void run(IProgressMonitor progressMonitor) throws CoreException
              {
                if (files.isEmpty())
                {
                  files.add(file);
                }

                super.run(progressMonitor);
              }
            };

          long startTime = System.currentTimeMillis();
          jetCompileTemplateOperation.run(monitor);
          if (!monitor.isCanceled())
          {
            final JETCompilationUnit compilerResult = compilationMonitor.getResult();
            if (compilerResult != null)
            {
              JETNature nature = jetEditor.getJETNature();

              IContainer sourceContainer = nature.getJavaSourceContainer();
              IProject project = sourceContainer.getProject();
              IJavaProject javaProject = JavaCore.create(project);

              String className = compilerResult.getClassName();
              final IFile javaFile = sourceContainer.getFile(new Path(className.replace('.', '/') + ".java"));

              ICompilationUnit compilationUnit = JavaCore.createCompilationUnitFrom(javaFile);
              LocalWorkingCopy localWorkingCopy = new LocalWorkingCopy(compilerResult.getJavaCompilationUnit());
              ICompilationUnit workingCopy = compilationUnit.getWorkingCopy(localWorkingCopy, null);

              ASTParser astParser = CodeGenUtil.EclipseUtil.newASTParser(true);
              String fileExtension = file.getFileExtension();
              if (fileExtension != null && fileExtension.endsWith("jet"))
              {
                astParser.setResolveBindings(true);
              }
              astParser.setProject(javaProject);

              ASTRequestor astRequestor = new ASTRequestor()
                {
                  @Override
                  public void acceptAST(ICompilationUnit sourceUnit, CompilationUnit compiledUnit)
                  {
                    IProblem[] problems = compiledUnit.getProblems();
                    JETCompilerResult jetCompilerResult = new JETCompilerResult(javaFile, compilerResult, Arrays.asList(problems), compilationMonitor.getException());
                    handleJavaResult(jetCompilerResult);
                  }
                };

              astParser.createASTs(new ICompilationUnit []{ workingCopy }, new String [0], astRequestor, null);
            }
            else if (compilationMonitor.getException() != null)
            {
              JETCompilerResult jetCompilerResult = new JETCompilerResult(compilationMonitor.getException());
              handleJavaResult(jetCompilerResult);
            }
          }

          long stop = System.currentTimeMillis();
          if (trace)
          {
            System.out.println("elapsed=" + (stop - startTime));
          }
        }
        catch (CoreException exception)
        {
          CodeGenUIPlugin.getPlugin().log(exception);
        }
      }

      return Status.OK_STATUS;
    }

    private void handleJavaResult(final JETCompilerResult jetCompilerResult)
    {
      if (!disabled)
      {
        jetEditor.handleCompilerResult(jetCompilerResult);
      }
    }

    public void documentAboutToBeChanged(DocumentEvent event)
    {
      if (!disabled)
      {
        cancel();
      }
    }

    public void documentChanged(DocumentEvent event)
    {
      if (!disabled)
      {
        jetEditor.resetExpectedModificationStamp();
        schedule(100);
      }
    }
  }

  static class JETCompilerResult
  {
    private final IFile javaFile;

    private final JETCompilationUnit compilationUnit;

    private final List<IProblem> javaProblems;

    private final JETException jetException;

    private final Map<Integer, List<JETProblemAnnotation>> problemAnnotations = new HashMap<Integer, List<JETProblemAnnotation>>();

    public JETCompilerResult(IFile javaFile, JETCompilationUnit compilationUnit, List<IProblem> javaProblems, JETException jetException)
    {
      this.javaFile = javaFile;
      this.compilationUnit = compilationUnit;
      this.javaProblems = javaProblems;
      this.jetException = jetException;
    }

    public JETCompilerResult(JETException jetException)
    {
      this.javaFile = null;
      this.compilationUnit = null;
      this.javaProblems = Collections.emptyList();
      this.jetException = jetException;
    }

    public IFile getJavaFile()
    {
      return javaFile;
    }

    public JETException getJETException()
    {
      return jetException;
    }

    public List<IProblem> getJavaProblems()
    {
      return javaProblems;
    }

    public JETCompilationUnit getCompilationUnit()
    {
      return compilationUnit;
    }

    public List<JETProblemAnnotation> getProblem(JETItem jetItem)
    {
      int fileID = jetItem.getFileID();
      List<JETProblemAnnotation> allProblems = getProblems(fileID);
      if (!allProblems.isEmpty())
      {
        List<JETProblemAnnotation> result = new ArrayList<JETProblemAnnotation>();
        int startOffset = jetItem.getStartOffset();
        int stopOffset = jetItem.getStopOffset();
        for (JETProblemAnnotation problem : allProblems)
        {
          Position position = problem.getPosition();
          if (stopOffset > position.offset && startOffset < position.offset + position.length)
          {
            result.add(problem);
          }
        }
        return result;
      }
      else
      {
        return Collections.emptyList();
      }
    }

    public List<JETProblemAnnotation> getProblems(int fileID)
    {
      if (fileID < 0)
      {
        JETProblemAnnotation jetOrphanProblemAnnotation = new JETProblemAnnotation(IMarker.SEVERITY_WARNING, "This file is no longer included by the template", new Position(0, 0));
        return Collections.singletonList(jetOrphanProblemAnnotation);
      }
      else
      {
        List<JETProblemAnnotation> jetProblemAnnotations = problemAnnotations.get(fileID);
        if (jetProblemAnnotations == null)
        {
          jetProblemAnnotations = new ArrayList<JETProblemAnnotation>();
          JETException jetException = getJETException();
          if (jetException != null)
          {
            JETProblemAnnotation jetExceptionProblemAnnotation = getExceptionProblem(fileID, jetException);
            if (jetExceptionProblemAnnotation != null)
            {
              jetProblemAnnotations.add(jetExceptionProblemAnnotation);
            }
          }

          JETCompilationUnit result = getCompilationUnit();
          if (compilationUnit != null)
          {
            List<JETException> jetProblems = result.getProblems();
            for (JETException jetProblem : jetProblems)
            {
              JETProblemAnnotation jetExceptionProblemAnnotation = getExceptionProblem(fileID, jetProblem);
              if (jetExceptionProblemAnnotation != null)
              {
                jetProblemAnnotations.add(jetExceptionProblemAnnotation);
              }
            }

            List<IProblem> javaProblems = getJavaProblems();
            for (IProblem problem : javaProblems)
            {
              int start = problem.getSourceStart();
              int end = problem.getSourceEnd() + 1;
              int[] correspondingTemplatePoint = result.getClosestCorrespondingTemplatePoint(fileID, start, end == 0 ? 0 : end - start);
              if (correspondingTemplatePoint != null)
              {
                String message = problem.getMessage();
                JETProblemAnnotation jetProblemAnnotation = new JETProblemAnnotation(
                  problem.isError() ? IMarker.SEVERITY_ERROR : problem.isWarning() ? IMarker.SEVERITY_WARNING : IMarker.SEVERITY_INFO,
                  message,
                  new Position(correspondingTemplatePoint[0], correspondingTemplatePoint[1]));
                jetProblemAnnotations.add(jetProblemAnnotation);
              }
            }
          }
        }

        problemAnnotations.put(fileID, jetProblemAnnotations);

        return jetProblemAnnotations;
      }
    }

    protected JETProblemAnnotation getExceptionProblem(int fileID, JETException exception)
    {
      int offset = -1;
      int length = 0;
      boolean locatedInThisFile = false;
      for (JETMark start = exception.getStart(); start != null; start = start.getParentMark())
      {
        if (start.getFileId() == fileID)
        {
          offset = start.getCursor();
          JETMark stop = exception.getStop();
          locatedInThisFile = stop.getFileId() == fileID;
          if (locatedInThisFile)
          {
            length = stop.getCursor() - offset;
          }

          break;
        }
      }

      if (offset == -1)
      {
        String message = exception.getLocalizedMessage();
        int severity = exception.getDiagnostic().getSeverity();
        int problemSeverity = severity == Diagnostic.INFO ? IMarker.SEVERITY_INFO : severity == Diagnostic.WARNING ? IMarker.SEVERITY_WARNING : IMarker.SEVERITY_ERROR;
        JETProblemAnnotation jetProblemAnnotation = new JETProblemAnnotation(problemSeverity, message, new Position(0, length));
        return jetProblemAnnotation;
      }
      else
      {
        String message = exception.getLocalizedMessage();
        if (locatedInThisFile)
        {
          int index = message.indexOf(" in ");
          if (index != -1)
          {
            message = message.substring(0, index);
          }
        }

        int severity = exception.getDiagnostic().getSeverity();
        int problemSeverity = severity == Diagnostic.INFO ? IMarker.SEVERITY_INFO : severity == Diagnostic.WARNING ? IMarker.SEVERITY_WARNING : IMarker.SEVERITY_ERROR;
        JETProblemAnnotation jetProblemAnnotation = new JETProblemAnnotation(problemSeverity, message, new Position(offset, length));
        return jetProblemAnnotation;
      }
    }
  }

  static class VisibleCaretHandler extends FocusAdapter implements PaintListener
  {
    private final StyledText styledText;

    private final StyledText otherStyledText;

    public VisibleCaretHandler(StyledText styledText, StyledText otherStyledText)
    {
      this.styledText = styledText;
      this.otherStyledText = otherStyledText;
      styledText.addFocusListener(this);
      otherStyledText.addFocusListener(this);
      styledText.addPaintListener(this);
    }

    @Override
    public void focusGained(FocusEvent event)
    {
      Rectangle caretBounds = getCaretBounds();
      styledText.redraw(caretBounds.x, caretBounds.y, caretBounds.width, caretBounds.height, false);
    }

    @Override
    public void focusLost(FocusEvent event)
    {
      Rectangle caretBounds = getCaretBounds();
      styledText.redraw(caretBounds.x, caretBounds.y, caretBounds.width, caretBounds.height, false);
    }

    public void paintControl(PaintEvent event)
    {
      if (!styledText.isFocusControl() && otherStyledText.isFocusControl())
      {
        Color background = event.gc.getBackground();
        event.gc.setBackground(ColorManager.INSTANCE.getBlendedColor(ColorManager.INSTANCE.getForeground(JETDirectiveRule.TOKEN.getType()), background));
        Rectangle caretBounds = getCaretBounds();
        event.gc.fillRectangle(caretBounds);
        event.gc.setBackground(background);
      }
    }

    private Rectangle getCaretBounds()
    {
      return styledText.getCaret().getBounds();
    }
  }

  static class SelectEnclosingJETElementAction extends Action
  {
    private JETEditor jetEditor;

    public SelectEnclosingJETElementAction(JETEditor jetEditor)
    {
      this.jetEditor = jetEditor;
    }

    @Override
    public void run()
    {
      ISourceViewer sourceViewer = jetEditor.getSourceViewer();
      Point selectedRange = sourceViewer.getSelectedRange();
      JETItem startItem = jetEditor.getJETItem(selectedRange.x, false);
      JETItem stopItem = jetEditor.getJETItem(selectedRange.x + selectedRange.y, false);

      if (startItem != null)
      {
        Point startRange = getRange(startItem);
        int offset = startRange.x;
        int end = startRange.x + startRange.y;
        if (stopItem != null)
        {
          Point stopRange = getRange(stopItem);
          end = stopRange.x + stopRange.y;
        }

        jetEditor.selectAndReveal(offset, end - offset);
      }
    }

    private Point getRange(JETItem jetItem)
    {
      if (jetItem instanceof JETScriptletItem || jetItem instanceof JETExpressionItem || jetItem instanceof JETCommentItem)
      {
        Point tokenRange = jetEditor.getTokenRangeAt(jetItem.getStartOffset());
        if (tokenRange != null)
        {
          return tokenRange;
        }
      }
      int start = jetItem.getStartOffset();
      int stop = jetItem.getStopOffset();
      return new Point(start, stop - start);
    }
  }

  static abstract class JETAbstractBaseAction extends Action
  {
    protected final JETEditor jetEditor;

    public JETAbstractBaseAction(JETEditor jetEditor, String text, ImageDescriptor imageDescriptor)
    {
      super(text, imageDescriptor);

      this.jetEditor = jetEditor;
    }

    @Override
    public void run()
    {
      SourceViewer jetSourceViewer = jetEditor.getJETSourceViewer();
      final IDocument jetDocument = jetSourceViewer.getDocument();

      final SourceViewer javaSourceViewer = jetEditor.getJavaSourceViewer();
      IDocument javaDocument = javaSourceViewer.getDocument();

      JETCompilationUnit compilationUnit = jetEditor.getCompilationUnit();
      List<JETJavaItem> javaItems = compilationUnit.getJavaItems(jetEditor.getFileID());
      List<TrackedPosition> jetPositions = new ArrayList<TrackedPosition>(javaItems.size());

      try
      {
        for (JETJavaItem jetJavaItem : javaItems)
        {
          if (jetJavaItem instanceof JETScriptletItem || jetJavaItem instanceof JETExpressionItem)
          {
            TrackedPosition jetPosition = new TrackedPosition(TrackedPosition.Type.JET);
            jetPosition.addToDocument(jetDocument);
            jetPosition.update(jetJavaItem);

            TrackedPosition javaPosition = new TrackedPosition(TrackedPosition.Type.JAVA);
            javaPosition.addToDocument(javaDocument);
            javaPosition.update(jetJavaItem);

            jetPosition.setOpposite(javaPosition);

            jetPositions.add(jetPosition);
          }
        }

        JavaDocumentTransaction javaDocumentTransaction = new JavaDocumentTransaction(jetEditor, jetPositions, isTransformBraces(), isIgnoreOutOfScopeChanges())
          {
            @Override
            protected void start()
            {
              DocumentRewriteSessionType documentRewriteSessionType = getDocumentRewriteSessionType();
              if (documentRewriteSessionType != null)
              {
                startDocumentRewriteSession(javaSourceViewer, documentRewriteSessionType, isRememberSelection());
              }

              JETAbstractBaseAction.this.start();
            }

            @Override
            protected void postCommit(List<TrackedPosition> jetPositions)
            {
              postProcess(jetDocument, jetPositions);
            }

            @Override
            protected void stop()
            {
              for (TrackedPosition jetPosition : jetPositions)
              {
                TrackedPosition javaPosition = jetPosition.getOpposite();
                jetPosition.dispose();
                javaPosition.dispose();
              }

              JETAbstractBaseAction.this.stop();

              DocumentRewriteSessionType documentRewriteSessionType = getDocumentRewriteSessionType();
              if (documentRewriteSessionType != null)
              {
                stopDocumentRewriteSession(javaSourceViewer, isRememberSelection());
              }
            }
          };

        javaDocumentTransaction.modify(javaDocument, new SneakyRunnable()
          {
            @Override
            public void execute() throws Exception
            {
              doRun();
            }
          });

        jetEditor.openDialog(getText().replace("&", "") + " Failure", javaDocumentTransaction.getStatus());
      }
      catch (Exception exception)
      {
        CodeGenUIPlugin.getPlugin().log(exception);
      }
    }

    protected DocumentRewriteSessionType getDocumentRewriteSessionType()
    {
      return null;
    }

    protected boolean isRememberSelection()
    {
      return getDocumentRewriteSessionType() != null;
    }

    protected boolean isTransformBraces()
    {
      return false;
    }

    boolean isIgnoreOutOfScopeChanges()
    {
      return false;
    }

    protected void start()
    {
    }

    protected abstract void doRun() throws Exception;

    protected void postProcess(IDocument jetDocument, List<TrackedPosition> jetPositions)
    {
    }

    protected void stop()
    {
    }
  }

  static class JETExtractLocalVariableAction extends JETAbstractBaseAction
  {
    private final IAction javaAction;

    public JETExtractLocalVariableAction(JETEditor jetEditor, IAction javaAction)
    {
      super(jetEditor, javaAction.getText(), javaAction.getImageDescriptor());

      this.javaAction = javaAction;

      setActionDefinitionId(javaAction.getActionDefinitionId());
    }

    @Override
    protected void doRun()
    {
      javaAction.run();
    }
  }

  static class JETRenameLocalVariableAction extends JETAbstractBaseAction
  {
    public JETRenameLocalVariableAction(JETEditor jetEditor)
    {
      super(jetEditor, "Re&name...", null);
      setActionDefinitionId(RENAME_COMMAND_ID);
    }

    @Override
    protected void doRun() throws Exception
    {
      IJavaElement[] selectedElements = jetEditor.getJavaEditor().getSelectedElements();
      if (selectedElements != null)
      {
        for (IJavaElement element : selectedElements)
        {
          if (element instanceof ILocalVariable)
          {
            ILocalVariable localVariable = (ILocalVariable)element;
            RenameSupport renameLocalVariableSupport = RenameSupport.create(localVariable, localVariable.getElementName(), RenameSupport.UPDATE_REFERENCES);
            renameLocalVariableSupport.openDialog(jetEditor.getSite().getShell());
            return;
          }
        }
      }

      throw new CoreException(new Status(IStatus.ERROR, CodeGenUIPlugin.getPlugin().getBundle().getSymbolicName(), "A local variable must be selected"));
    }
  }

  static class JETFormatAction extends JETAbstractBaseAction
  {
    public JETFormatAction(JETEditor jetEditor)
    {
      super(jetEditor, "&Format", null);
      setActionDefinitionId(FORMAT_COMMAND_ID);
    }

    @Override
    protected DocumentRewriteSessionType getDocumentRewriteSessionType()
    {
      return DocumentRewriteSessionType.UNRESTRICTED;
    }

    @Override
    boolean isIgnoreOutOfScopeChanges()
    {
      return true;
    }

    @Override
    protected void start()
    {
    }

    @Override
    protected void stop()
    {
      jetEditor.waitForCompilerResult(1000);
    }

    @Override
    protected void doRun() throws Exception
    {
      IJavaProject javaProject = JavaCore.create(jetEditor.getJETNature().getProject());
      Map<String, String> options = javaProject.getOptions(true);
      for (Map.Entry<String, String> entry : options.entrySet())
      {
        String key = entry.getKey();
        if (key.contains(".formatter.brace_position_for"))
        {
          entry.setValue(DefaultCodeFormatterConstants.END_OF_LINE);
        }
      }

      options.put(JavaCore.PLUGIN_ID + ".formatter.align_with_spaces", DefaultCodeFormatterConstants.TRUE);
      options.put(DefaultCodeFormatterConstants.FORMATTER_TAB_CHAR, JavaCore.SPACE);
      options.put(DefaultCodeFormatterConstants.FORMATTER_TAB_SIZE, "2");
      options.put(DefaultCodeFormatterConstants.FORMATTER_INDENTATION_SIZE, "2");
      options.put(DefaultCodeFormatterConstants.FORMATTER_INSERT_NEW_LINE_BEFORE_ELSE_IN_IF_STATEMENT, DefaultCodeFormatterConstants.FALSE);
      options.put(DefaultCodeFormatterConstants.FORMATTER_KEEP_ELSE_STATEMENT_ON_SAME_LINE, DefaultCodeFormatterConstants.TRUE);
      options.put(DefaultCodeFormatterConstants.FORMATTER_COMPACT_ELSE_IF, DefaultCodeFormatterConstants.TRUE);

      CodeFormatter codeFormatter = ToolFactory.createCodeFormatter(options);
      JavaEditor javaEditor = jetEditor.getJavaEditor();
      IDocument document = javaEditor.getJavaSourceViewer().getDocument();
      String source = document.get();
      TextEdit edit = ((CodeFormatter)codeFormatter).format(
        CodeFormatter.K_COMPILATION_UNIT | CodeFormatter.F_INCLUDE_COMMENTS,
        source,
        0,
        source.length(),
        0,
        TextUtilities.getDefaultLineDelimiter(document));
      if (edit != null)
      {
        edit.apply(document);
      }
    }

    @Override
    protected void postProcess(IDocument jetDocument, List<TrackedPosition> jetPositions)
    {
      IDocument javaDocument = jetEditor.getJavaSourceViewer().getDocument();

      MultiTextEdit multiTextEdit = new MultiTextEdit();
      List<JETRootItem> rootItems = JETParser.parseRootItems(jetDocument.get());
      for (JETRootItem jetRootItem : rootItems)
      {
        int start = jetRootItem.getStartOffset();
        int stop = jetRootItem.getStopOffset();
        if (jetRootItem instanceof JETScriptletItem)
        {
          JETScriptletItem jetScriptletItem = (JETScriptletItem)jetRootItem;
          String body = jetScriptletItem.getText();
          reformatScriptletBody(jetDocument, jetPositions, javaDocument, multiTextEdit, start, stop, body);
        }
        else if (jetRootItem instanceof JETDirectiveItem)
        {
          JETDirectiveItem jetDirectiveItem = (JETDirectiveItem)jetRootItem;
          String directive = jetDirectiveItem.getText();
          String directiveName = jetDirectiveItem.getNameItem().getText();
          if (directiveName.length() != 0 && Character.isUpperCase(directiveName.codePointAt(0)))
          {
            multiTextEdit.addChild(new ReplaceEdit(start + 2, 0, " "));
            reformatScriptletBody(jetDocument, jetPositions, javaDocument, multiTextEdit, start + 2, stop - 2, directive.substring(2, directive.length() - 2));
          }
          else
          {
            reformatDirective(jetDocument, multiTextEdit, start, stop, directiveName, directive, jetDirectiveItem);
          }
        }
      }

      stripTrailingWhitespace(jetDocument, multiTextEdit);

      if (multiTextEdit.hasChildren())
      {
        try
        {
          multiTextEdit.apply(jetDocument);

          // As a second pass, to avoid overlapping edits, we correct the indentation of lines within the scriptlet bodies.
          multiTextEdit = new MultiTextEdit();
          rootItems = JETParser.parseRootItems(jetDocument.get());
          for (JETRootItem jetRootItem : rootItems)
          {
            if (jetRootItem instanceof JETScriptletItem)
            {
              int start = jetRootItem.getStartOffset();
              int stop = jetRootItem.getStopOffset();
              try
              {
                // Subsequent line starts within the scriptlet will simply have 2 spaces too much indentation.
                // Actually 4 because that's the generate method body's indentation,
                // but in order to line up with the start of the scriptlet, which has <% in it, it will have 2 too many.
                int lineOffset;
                for (int i = jetDocument.getLineOfOffset(start) + 1; (lineOffset = jetDocument.getLineOffset(i)) < stop; ++i)
                {
                  if (jetDocument.getChar(lineOffset) == ' ' && jetDocument.getChar(lineOffset + 1) == ' ')
                  {
                    multiTextEdit.addChild(new ReplaceEdit(lineOffset, 2, ""));
                  }
                }
              }
              catch (BadLocationException exception)
              {
              }
            }
          }

          if (multiTextEdit.hasChildren())
          {
            multiTextEdit.apply(jetDocument);
          }
        }
        catch (Exception exception)
        {
        }
      }
    }

    private static final Map<String, String[]> JET_DIRECTIVE_ORDERED_ATTRIBUTES = new HashMap<String, String[]>();

    static
    {
      JET_DIRECTIVE_ORDERED_ATTRIBUTES.put("jet", new String []{ "package", "class", "imports", "builder", "nlString", "minimize", "skeleton", "version" });
      JET_DIRECTIVE_ORDERED_ATTRIBUTES.put("include", new String []{ "file", "fail" });
    }

    private void reformatDirective(
      IDocument jetDocument,
      MultiTextEdit multiTextEdit,
      int start,
      int stop,
      String directiveName,
      String directive,
      JETDirectiveItem jetDirectiveItem)
    {
      StringBuilder result = new StringBuilder();
      result.append(directiveName);
      boolean isMultiLine = directive.contains("\n");
      String lineDelimiter = TextUtilities.getDefaultLineDelimiter(jetDocument);
      JETAttributeListItem attributes = jetDirectiveItem.getAttributes();
      List<JETAttributeItem> items = new ArrayList<JETAttributeItem>(attributes.getAttributes());
      String[] orderedAttributes = JET_DIRECTIVE_ORDERED_ATTRIBUTES.get(directiveName);
      if (orderedAttributes != null)
      {
        for (int i = orderedAttributes.length; --i >= 0;)
        {
          JETAttributeItem attribute = attributes.getAttribute(orderedAttributes[i]);
          if (items.remove(attribute))
          {
            items.add(0, attribute);
          }
        }
      }

      for (JETAttributeItem jetAttributeItem : items)
      {
        if (isMultiLine)
        {
          result.append(lineDelimiter);
          result.append("  ");
        }
        else if (result.length() != 0)
        {
          result.append(' ');
        }

        String nameText = jetAttributeItem.getNameToken().getText();
        result.append(nameText);
        result.append('=');

        JETTokenItem valueToken = jetAttributeItem.getValueToken();
        String valueText = valueToken.getText();
        if ("imports".equals(nameText))
        {
          char quote = valueText.length() == 0 ? '"' : valueText.charAt(0);

          boolean isMultiLineImport = valueText.contains("\n");

          if (isMultiLineImport)
          {
            result.append(lineDelimiter).append("    ");
          }

          result.append(quote);
          List<String> imports = sortImports(valueToken);
          int count = 0;
          for (String importedName : imports)
          {
            if (isMultiLineImport)
            {
              result.append(lineDelimiter).append("      ");
            }
            else if (count++ != 0)
            {
              result.append(' ');
            }

            result.append(importedName);
          }

          if (isMultiLineImport)
          {
            result.append(lineDelimiter).append("    ");
          }

          result.append(quote);
        }
        else
        {
          result.append(valueText);
        }
      }

      multiTextEdit.addChild(new ReplaceEdit(start + 3, stop - start - 5, result.toString()));
    }

    private static final Comparator<SegmentSequence> COMPARATOR = new Comparator<SegmentSequence>()
      {
        Comparator<String> comparator = CommonPlugin.INSTANCE.getComparator();

        public int compare(SegmentSequence o1, SegmentSequence o2)
        {
          int count1 = o1.segmentCount();
          int count2 = o2.segmentCount();

          for (int i = 0; i < count1 && i < count2; ++i)
          {
            int comparision = comparator.compare(o1.segment(i), o2.segment(i));
            if (comparision != 0)
            {
              return comparision;
            }
          }

          return count1 == count2 ? 0 : count1 < count2 ? -1 : 1;
        }
      };

    private List<String> sortImports(JETTokenItem imports)
    {
      Set<SegmentSequence> importedNames = new TreeSet<SegmentSequence>(COMPARATOR);
      for (JETValueElementItem jetValueElementItem : imports.getValueItem().getElements())
      {
        importedNames.add(SegmentSequence.create(".", jetValueElementItem.getText()));
      }

      List<String> result = new ArrayList<String>();
      for (SegmentSequence name : importedNames)
      {
        result.add(name.toString());
      }

      return result;
    }

    protected void stripTrailingWhitespace(IDocument jetDocument, MultiTextEdit multiTextEdit)
    {
      try
      {
        int numberOfLines = jetDocument.getNumberOfLines();
        for (int i = 0; i < numberOfLines; ++i)
        {
          int lineOffset = jetDocument.getLineOffset(i);
          int lineLength = jetDocument.getLineLength(i);
          String lineDelimiter = jetDocument.getLineDelimiter(i);
          int lineDelimiterLength = lineDelimiter == null ? 0 : lineDelimiter.length();
          int count = 0;
          for (int j = lineOffset + lineLength - lineDelimiterLength - 1; j >= 0; --j)
          {
            char character = jetDocument.getChar(j);
            if (character == ' ' || character == '\t')
            {
              ++count;
            }
            else
            {
              break;
            }
          }

          if (count > 0)
          {
            try
            {
              multiTextEdit.addChild(new ReplaceEdit(lineOffset + lineLength - lineDelimiterLength - count, count, ""));
            }
            catch (MalformedTreeException exception)
            {
              // These can and do happen because the directives are formatted to already eliminate trailing whitespace.
            }
          }
        }
      }
      catch (BadLocationException e)
      {
        // Can't happen.
      }
    }

    protected TrackedPosition getTrackedPosition(List<TrackedPosition> positions, int offset)
    {
      for (Position position : positions)
      {
        if (position.overlapsWith(offset, 0))
        {
          return (TrackedPosition)position;
        }
      }
      return null;
    }

    protected void reformatScriptletBody(
      IDocument jetDocument,
      List<TrackedPosition> positions,
      IDocument javaDocument,
      MultiTextEdit multiTextEdit,
      int start,
      int stop,
      String body)
    {
      // Trim any whitespace including the line feed at the end of the body.
      int trim = -1;
      int length = body.length();
      for (int i = length; --i >= 0;)
      {
        if (Character.isWhitespace(body.charAt(i)))
        {
          trim = i;
        }
        else
        {
          break;
        }
      }

      if (trim != -1)
      {
        multiTextEdit.addChild(new ReplaceEdit(start + trim, length - trim, ""));
      }

      // Compute the indentation for the scriptlet based on the indentation of the corresponding Java.
      TrackedPosition jetPosition = getTrackedPosition(positions, start);
      if (jetPosition != null)
      {
        TrackedPosition javaPosition = jetPosition.getOpposite();
        int jetIndentation = computeIndentation(jetDocument, jetPosition.offset - 2);
        if (jetIndentation != -1)
        {
          int javaIndentation = computeIndentation(javaDocument, javaPosition.offset);
          if (javaIndentation >= 4)
          {
            // The body of the generate method is expected to be at an indentation level of 4 spaces.
            int baseJavaIndentation = javaIndentation - 4;
            int delta = baseJavaIndentation - jetIndentation;
            if (delta > 0)
            {
              StringBuilder spaces = new StringBuilder();
              for (int i = 0; i < delta; ++i)
              {
                spaces.append(' ');
              }

              multiTextEdit.addChild(new ReplaceEdit(start - 2, 0, spaces.toString()));
            }
            else if (delta < 0)
            {
              multiTextEdit.addChild(new ReplaceEdit(start + delta - 2, -delta, ""));
            }
          }
        }
      }
    }

    protected int computeIndentation(IDocument document, int offset)
    {
      int indent = 0;
      for (int i = offset; --i >= 0;)
      {
        try
        {
          char character = document.getChar(i);
          if (character == ' ')
          {
            ++indent;
          }
          else if (character == '\n')
          {
            break;
          }
          else
          {
            return -1;
          }
        }
        catch (BadLocationException exception)
        {
        }
      }

      return indent;
    }
  }

  static class WorkingCopyCompilationMonitor extends JETCompileTemplateOperation.JETCompilationMonitor
  {
    @Override
    public InputStream openInputStream(String locationURI) throws JETException
    {
      URI uri = URI.createURI(locationURI);
      if (uri.isPlatformResource())
      {
        IPath fullPath = new Path(uri.toPlatformString(true));
        ITextFileBuffer buffer = ITextFileBufferManager.DEFAULT.getTextFileBuffer(fullPath, LocationKind.IFILE);
        if (buffer != null)
        {
          IDocument document = buffer.getDocument();
          String contents = document.get();
          return new ByteArrayInputStream(contents.getBytes(StandardCharsets.UTF_8));
        }
      }

      return super.openInputStream(locationURI);
    }
  }

  static class LocalWorkingCopy extends WorkingCopyOwner
  {
    private String contents;

    public LocalWorkingCopy(String contents)
    {
      this.contents = contents;
    }

    @Override
    public IBuffer createBuffer(final ICompilationUnit workingCopy)
    {
      return new IBuffer()
        {
          public IOpenable getOwner()
          {
            return workingCopy;
          }

          public String getText(int offset, int length) throws IndexOutOfBoundsException
          {
            return contents.substring(offset, offset + length);
          }

          public int getLength()
          {
            return contents.length();
          }

          public String getContents()
          {
            return contents;
          }

          public char[] getCharacters()
          {
            return contents.toCharArray();
          }

          public char getChar(int position)
          {
            return contents.charAt(position);
          }

          public boolean isReadOnly()
          {
            return true;
          }

          public boolean isClosed()
          {
            return false;
          }

          public boolean hasUnsavedChanges()
          {
            return false;
          }

          public IResource getUnderlyingResource()
          {
            return null;
          }

          public void close()
          {
          }

          public void save(IProgressMonitor progress, boolean force) throws JavaModelException
          {
            throw new UnsupportedOperationException();
          }

          public void setContents(String contents)
          {
            throw new UnsupportedOperationException();
          }

          public void setContents(char[] contents)
          {
            throw new UnsupportedOperationException();
          }

          public void replace(int position, int length, String text)
          {
            throw new UnsupportedOperationException();
          }

          public void replace(int position, int length, char[] text)
          {
            throw new UnsupportedOperationException();
          }

          public void append(String text)
          {
            throw new UnsupportedOperationException();
          }

          public void append(char[] text)
          {
            throw new UnsupportedOperationException();
          }

          public void addBufferChangedListener(IBufferChangedListener listener)
          {
          }

          public void removeBufferChangedListener(IBufferChangedListener listener)
          {
          }
        };
    }
  }

  static final class StorageEditorInput extends PlatformObject implements IStorageEditorInput, IPathEditorInput, IURIEditorInput
  {
    private final URLStorage storage;

    public StorageEditorInput(URI uri)
    {
      this(uri, null, null);
    }

    public StorageEditorInput(URI uri, String contents, String encoding)
    {
      storage = new URLStorage(uri, contents, encoding == null ? "UTF-8" : encoding);
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Object getAdapter(Class adapter)
    {
      return storage.getAdapter(adapter);
    }

    public boolean exists()
    {
      return storage.exists();
    }

    public ImageDescriptor getImageDescriptor()
    {
      IEditorRegistry registry = PlatformUI.getWorkbench().getEditorRegistry();
      return registry.getImageDescriptor(storage.uri.fileExtension());
    }

    public String getName()
    {
      return storage.uri.lastSegment();
    }

    public IPersistableElement getPersistable()
    {
      return null;
    }

    public String getToolTipText()
    {
      return storage.uri.toString();
    }

    public IEncodedStorage getStorage() throws CoreException
    {
      return storage;
    }

    public java.net.URI getURI()
    {
      try
      {
        URI resolvedURI = JETNature.resolve(storage.uri);
        return new java.net.URI(resolvedURI.toString());
      }
      catch (URISyntaxException exception)
      {
        throw new RuntimeException(exception);
      }
    }

    public IPath getPath()
    {
      IPath path = storage.getFullPath();
      return path == null ? new Path("") : path;
    }

    public IFile getFile()
    {
      return (IFile)storage.getAdapter(IFile.class);
    }

    @Override
    public String toString()
    {
      return storage.toString();
    }

    @Override
    public int hashCode()
    {
      return storage.hashCode();
    }

    @Override
    public boolean equals(Object that)
    {
      if (this == that)
      {
        return true;
      }
      else if (that == null || that.getClass() != StorageEditorInput.class)
      {
        return false;
      }
      else
      {
        StorageEditorInput other = (StorageEditorInput)that;
        return storage == null ? other.storage == null : storage.equals(other.storage);
      }
    }

    private static final class URLStorage extends PlatformObject implements IEncodedStorage
    {
      private final URI uri;

      private final String contents;

      private final String encoding;

      public URLStorage(URI uri, String contents, String encoding)
      {
        this.uri = uri;
        this.contents = contents;
        this.encoding = encoding;
      }

      @Override
      @SuppressWarnings({ "unchecked", "rawtypes" })
      public Object getAdapter(Class adapter)
      {
        if (adapter == IFile.class)
        {
          IFile file = toFile(uri);
          if (file != null)
          {
            return file;
          }
        }

        return super.getAdapter(adapter);
      }

      public boolean exists()
      {
        try
        {
          if (contents == null)
          {
            getContents().close();
          }
          return true;
        }
        catch (Exception exception)
        {
          return false;
        }
      }

      public InputStream getContents() throws CoreException
      {
        try
        {
          if (contents == null)
          {
            return CommonUtil.newURL(uri.toString()).openStream();
          }
          else
          {
            return new ByteArrayInputStream(contents.getBytes(encoding == null ? "UTF-8" : encoding));
          }
        }
        catch (Exception exception)
        {
          throw new CoreException(new Status(IStatus.ERROR, CodeGenPlugin.ID, exception.getLocalizedMessage(), exception));
        }
      }

      public IPath getFullPath()
      {
        if (uri.isPlatformResource())
        {
          return new Path(uri.toPlatformString(true));
        }

        if (uri.isFile())
        {
          return new Path(uri.toFileString());
        }

        return null;
      }

      public String getName()
      {
        return uri.lastSegment();
      }

      public boolean isReadOnly()
      {
        return true;
      }

      public String getCharset() throws CoreException
      {
        return encoding;
      }

      @Override
      public int hashCode()
      {
        return uri == null ? 0 : uri.hashCode();
      }

      @Override
      public boolean equals(Object that)
      {
        if (this == that)
        {
          return true;
        }
        else if (that == null || that.getClass() != URLStorage.class)
        {
          return false;
        }
        else
        {
          URLStorage other = (URLStorage)that;
          return uri == null ? other.uri == null : uri.equals(other.uri);
        }
      }

      @Override
      public String toString()
      {
        return String.valueOf(uri);
      }
    }
  }

  static class JavaEditorInput extends FileEditorInput
  {
    private boolean isConnected;

    private boolean isWorkingCopy;

    private ICompilationUnit workingCopy;

    public JavaEditorInput(IFile file, String content)
    {
      super(file);
      try
      {
        IPath path = file.getFullPath();
        FileBuffers.getTextFileBufferManager().connect(path, LocationKind.IFILE, new NullProgressMonitor());
        isConnected = true;
        ITextFileBuffer textFileBuffer = FileBuffers.getTextFileBufferManager().getTextFileBuffer(path, LocationKind.IFILE);
        if (textFileBuffer instanceof IStateValidationSupport)
        {
          ((IStateValidationSupport)textFileBuffer).validationStateChanged(true, Status.OK_STATUS);
        }
        IDocument document = textFileBuffer.getDocument();
        document.set(content);
      }
      catch (CoreException exception)
      {
        CodeGenUIPlugin.getPlugin().log(exception);
      }
    }

    public synchronized ITypeRoot getWorkingCopy(ITypeRoot inputJavaElement)
    {
      if (!isWorkingCopy && inputJavaElement instanceof ICompilationUnit)
      {
        try
        {
          ICompilationUnit workingCopy = (ICompilationUnit)inputJavaElement;
          workingCopy.becomeWorkingCopy(new NullProgressMonitor());
          this.workingCopy = workingCopy;
          isWorkingCopy = true;
        }
        catch (JavaModelException exception)
        {
          isWorkingCopy = true;
          CodeGenUIPlugin.getPlugin().log(exception);
        }
      }

      return inputJavaElement;
    }

    public void releaseWorkingCopy()
    {
      if (workingCopy != null)
      {
        try
        {
          workingCopy.discardWorkingCopy();
        }
        catch (JavaModelException exception)
        {
          CodeGenUIPlugin.getPlugin().log(exception);
        }
      }
    }

    public void release()
    {
      if (isConnected)
      {
        IPath path = getFile().getFullPath();
        try
        {
          FileBuffers.getTextFileBufferManager().disconnect(path, LocationKind.IFILE, new NullProgressMonitor());
        }
        catch (CoreException exception)
        {
          CodeGenUIPlugin.getPlugin().log(exception);
        }
      }
    }
  }

  @SuppressWarnings("restriction")
  static final class JavaEditor extends org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor
  {
    static final Map<String, String> SELECTION_ACTIONS = new LinkedHashMap<String, String>();

    static
    {
      SELECTION_ACTIONS.put(org.eclipse.jdt.internal.ui.javaeditor.selectionactions.StructureSelectionAction.ENCLOSING, IJavaEditorActionDefinitionIds.SELECT_ENCLOSING);
      SELECTION_ACTIONS.put(org.eclipse.jdt.internal.ui.javaeditor.selectionactions.StructureSelectionAction.NEXT, IJavaEditorActionDefinitionIds.SELECT_NEXT);
      SELECTION_ACTIONS.put(org.eclipse.jdt.internal.ui.javaeditor.selectionactions.StructureSelectionAction.PREVIOUS, IJavaEditorActionDefinitionIds.SELECT_PREVIOUS);
      SELECTION_ACTIONS.put(org.eclipse.jdt.internal.ui.javaeditor.selectionactions.StructureSelectionAction.HISTORY, IJavaEditorActionDefinitionIds.SELECT_LAST);
    }

    private final JETEditor jetEditor;

    private boolean navigating;

    private IAction openDeclarationAction;

    private DefaultCharacterPairMatcher bracketMatcher;

    public JavaEditor(JETEditor jetEditor)
    {
      this.jetEditor = jetEditor;
      setDocumentProvider(new StatusFilteringForwardingDocumentProvider(getDocumentProvider()));
    }

    SourceViewer getJavaSourceViewer()
    {
      return (SourceViewer)getSourceViewer();
    }

    @Override
    public IAction getAction(String actionID)
    {
      if ("OpenEditor".equals(actionID))
      {
        return getOpenDeclarationAction();
      }

      final IAction action = super.getAction(actionID);
      String actionDefinitionID = SELECTION_ACTIONS.get(actionID);
      if (actionDefinitionID != null)
      {
        return new Action(action.getText(), action.getStyle())
          {
            @Override
            public void run()
            {
              try
              {
                navigating = true;
                action.run();
                jetEditor.selectionSynchronizer.sync(getJavaSourceViewer());
              }
              finally
              {
                navigating = false;
              }
            }
          };
      }
      else if (EXTRACT_LOCAL_VARIABLE_ACTION_ID.equals(actionID))
      {
        return new JETExtractLocalVariableAction(jetEditor, action);
      }
      else
      {
        return action;
      }
    }

    IJavaElement[] getSelectedElements()
    {
      ITypeRoot inputJavaElement = getInputJavaElement();
      Point selectedRange = getJavaSourceViewer().getSelectedRange();
      try
      {
        return inputJavaElement.codeSelect(selectedRange.x, selectedRange.y);
      }
      catch (JavaModelException e)
      {
        return null;
      }
    }

    DefaultCharacterPairMatcher getJavaBracketMatcher()
    {
      if (bracketMatcher == null)
      {
        try
        {
          Field field = org.eclipse.jdt.internal.ui.javaeditor.JavaEditor.class.getDeclaredField("fBracketMatcher");
          field.setAccessible(true);
          bracketMatcher = (DefaultCharacterPairMatcher)field.get(this);
        }
        catch (Exception exception)
        {
        }
      }

      return bracketMatcher;
    }

    IHyperlinkDetector[] getHyperlinkDetectors()
    {
      IHyperlinkDetector[] hyperlinkDetectors = getSourceViewerConfiguration().getHyperlinkDetectors(getSourceViewer());
      return hyperlinkDetectors;
    }

    boolean isNavigating()
    {
      return navigating;
    }

    IAction getOpenDeclarationAction()
    {
      if (openDeclarationAction == null)
      {
        openDeclarationAction = new OpenAction(this)
          {
            @Override
            public void run(Object[] elements)
            {
              IFileEditorInput editorInput = (IFileEditorInput)getEditorInput();
              IPath editorInputPath = editorInput.getFile().getFullPath();
              for (Object element : elements)
              {
                if (element instanceof IJavaElement)
                {
                  IPath path = ((IJavaElement)element).getPath();
                  if (editorInputPath.equals(path))
                  {
                    try
                    {
                      navigating = true;
                      JavaUI.revealInEditor(JavaEditor.this, (IJavaElement)element);

                      ISourceViewer javaSourceViewer = JavaEditor.this.getJavaSourceViewer();
                      javaSourceViewer.getTextWidget().notifyListeners(SWT.Selection, new Event());
                    }
                    finally
                    {
                      navigating = false;
                    }
                    return;
                  }
                }
              }

              super.run(elements);
            }
          };
      }
      return openDeclarationAction;
    }

    IAction getGotoMatchingBracketAction()
    {
      final IAction gotoMatchingBracketAction = getAction(org.eclipse.jdt.internal.ui.javaeditor.GotoMatchingBracketAction.GOTO_MATCHING_BRACKET);
      gotoMatchingBracketAction.setActionDefinitionId(IJavaEditorActionDefinitionIds.GOTO_MATCHING_BRACKET);
      return new Action(gotoMatchingBracketAction.getText(), gotoMatchingBracketAction.getStyle())
        {
          @Override
          public void run()
          {
            JETBracketMatcher jetBracketMatcher = jetEditor.getJetBracketMatcher();
            ISourceViewer jetSourceViewer = jetEditor.getSourceViewer();
            Point selectedRange = jetSourceViewer.getSelectedRange();
            IRegion jetMatch = jetBracketMatcher.match(jetSourceViewer.getDocument(), selectedRange.x);
            if (jetMatch == null)
            {
              try
              {
                navigating = true;
                gotoMatchingBracketAction.run();
              }
              finally
              {
                navigating = false;
              }
            }
            else
            {
              int offset = jetMatch.getOffset();
              int length = jetMatch.getLength();
              int targetOffset = (ICharacterPairMatcher.RIGHT == jetBracketMatcher.getAnchor()) ? offset + 1 : offset + length;
              jetEditor.selectAndReveal(targetOffset, 0);
            }
          }
        };
    }

    IContentAssistant getContentAssist()
    {
      try
      {
        Field field = SourceViewer.class.getDeclaredField("fContentAssistant");
        field.setAccessible(true);
        return (IContentAssistant)field.get(getSourceViewer());
      }
      catch (Exception exception)
      {
        throw new RuntimeException(exception);
      }
    }

    IQuickAssistAssistant getQuickAssistAssistant()
    {
      try
      {
        Field field = SourceViewer.class.getDeclaredField("fQuickAssistAssistant");
        field.setAccessible(true);
        return (IQuickAssistAssistant)field.get(getSourceViewer());
      }
      catch (Exception exception)
      {
        throw new RuntimeException(exception);
      }
    }

    IHyperlink[] detectHyperlinks(ITextViewer textViewer, IRegion region, boolean canShowMultipleHyperlinks)
    {
      JETCompilationUnit compilerResult = jetEditor.getCompilationUnit();
      if (compilerResult != null)
      {
        IRegion javaWordRegion = org.eclipse.jdt.internal.ui.text.JavaWordFinder.findWord(textViewer.getDocument(), region.getOffset());
        final int correspondingTemplateFileID = compilerResult.getCorrespondingTemplateFileID(region.getOffset());
        if (correspondingTemplateFileID != -1 && correspondingTemplateFileID != jetEditor.getFileID())
        {
          JETHyperLink jetHyperLink = new JETHyperLink(
            jetEditor,
            javaWordRegion == null || javaWordRegion.getLength() == 0 ? new Region(region.getOffset(), 1) : javaWordRegion,
            correspondingTemplateFileID,
            toURI(jetEditor.getDocumentManager().getEditorInput(correspondingTemplateFileID)).toString())
            {
              @Override
              public String getTypeLabel()
              {
                return "Open JET";
              }
            };
          return new IHyperlink []{ jetHyperLink };
        }
      }
      return null;
    }

    @Override
    protected void markInNavigationHistory()
    {
      // Don't maintain history for the Java file itself.
    }

    @Override
    protected ITypeRoot getInputJavaElement()
    {
      ITypeRoot inputJavaElement = super.getInputJavaElement();
      IEditorInput editorInput = getEditorInput();
      if (editorInput instanceof JavaEditorInput)
      {
        return ((JavaEditorInput)editorInput).getWorkingCopy(inputJavaElement);
      }
      else
      {
        return inputJavaElement;
      }
    }

    @Override
    public void dispose()
    {
      super.dispose();

      IEditorInput editorInput = getEditorInput();
      if (editorInput instanceof JavaEditorInput)
      {
        ((JavaEditorInput)editorInput).releaseWorkingCopy();
      }
    }

    void setInput(IFile javaFile, String compilationUnit)
    {
      IFile effectiveJavaFile = javaFile;
      boolean javaFileExists = javaFile.exists();
      if (!javaFileExists)
      {
        // Ensure that the file is contained by an actual existing project or folder.
        IContainer parent = javaFile.getParent();
        while (!parent.exists())
        {
          parent = parent.getParent();
        }

        if (parent.getType() == IResource.ROOT)
        {
          parent = jetEditor.getEditorInputFile().getParent();
        }

        effectiveJavaFile = parent.getFile(new Path(javaFile.getName()));
      }

      IEditorInput newInput = javaFileExists ? new FileEditorInput(effectiveJavaFile) : new JavaEditorInput(effectiveJavaFile, compilationUnit);
      IEditorInput oldInput = getEditorInput();
      if (!newInput.equals(oldInput))
      {
        setInput(newInput);
        getJavaSourceViewer().setEditable(false);
        if (oldInput instanceof JavaEditorInput)
        {
          ((JavaEditorInput)oldInput).releaseWorkingCopy();
        }
      }

      jetEditor.getJavaPosition().addToDocument(getJavaSourceViewer().getDocument());

      if (newInput instanceof JavaEditorInput)
      {
        ((JavaEditorInput)newInput).release();
      }
    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException
    {
      super.init(new DelegatingEditorSite(site), input);

      IPerspectiveDescriptor perspective = getSite().getPage().getPerspective();
      String id = EDITOR_SHOW_BREADCRUMB + "." + perspective.getId(); //$NON-NLS-1$
      IPreferenceStore preferenceStore = getPreferenceStore();
      PreferenceStore localPreferenceStore = new PreferenceStore();
      localPreferenceStore.setValue(PreferenceConstants.EDITOR_FOLDING_ENABLED, false);
      setPreferenceStore(new ChainedPreferenceStore(new IPreferenceStore []{ localPreferenceStore, preferenceStore }));
      localPreferenceStore.setValue(id, true);
    }

    @Override
    public void createPartControl(Composite parent)
    {
      super.createPartControl(parent);

      new VisibleCaretHandler(getSourceViewer().getTextWidget(), jetEditor.getSourceViewer().getTextWidget());

      // The Java editor does semantic highlighting on a background thread that updates the Java viewer at some arbitrary point in time later.
      ((ITextViewerExtension4)getSourceViewer()).addTextPresentationListener(new ITextPresentationListener()
        {
          private Color getBackground(JETJavaRange jetRange)
          {
            JETItem jetItem = jetRange.getJETItem();
            if (jetItem instanceof JETScriptletItem)
            {
              return ColorManager.INSTANCE.getBackground(JETScriptletRule.TOKEN.getType());
            }
            else if (jetItem instanceof JETExpressionItem)
            {
              return ColorManager.INSTANCE.getBackground(JETExpressionRule.TOKEN.getType());
            }
            else if (jetItem instanceof JETLiteralItem)
            {
              return ColorManager.INSTANCE.getBackground(JETContentRule.TOKEN.getType());
            }
            else if (jetItem instanceof JETSubItem && jetItem.getParent() instanceof JETCommentItem)
            {
              return ColorManager.INSTANCE.getBackground(JETCommentRule.TOKEN.getType());
            }
            else
            {
              return ColorManager.INSTANCE.getBackground(JETDirectiveRule.TOKEN.getType());
            }
          }

          public void applyTextPresentation(TextPresentation textPresentation)
          {
            // Start two parallel iterators on the JET ranges and the style ranges.
            List<JETJavaRange> ranges = jetEditor.getRanges();
            if (!ranges.isEmpty())
            {
              Iterator<StyleRange> i = textPresentation.getAllStyleRangeIterator();
              if (i.hasNext())
              {
                // Accumulate altered style ranges.
                List<StyleRange> newStyleRanges = new ArrayList<StyleRange>();

                // Initialize the current starting point for each scanned JET range and style range.
                Iterator<JETJavaRange> r = ranges.iterator();
                JETJavaRange range = r.next();
                StyleRange styleRange = i.next();

                LOOP: while (true)
                {
                  // Skip any empty ranges, e.g., the ones from an empty scriptlet.
                  int rangeJavaLength = range.getJavaLength();
                  while (rangeJavaLength == 0)
                  {
                    if (!r.hasNext())
                    {
                      newStyleRanges.add(styleRange);
                      break LOOP;
                    }

                    range = r.next();
                    rangeJavaLength = range.getJavaLength();
                  }

                  int javaOffset = styleRange.start;
                  int rangeJavaOffset = range.getJavaOffset();

                  // Consume ranges until we hit one that overlaps with the current style range.
                  while (rangeJavaOffset + rangeJavaLength <= javaOffset)
                  {
                    if (!r.hasNext())
                    {
                      newStyleRanges.add(styleRange);
                      break LOOP;
                    }

                    range = r.next();
                    rangeJavaOffset = range.getJavaOffset();
                    rangeJavaLength = range.getJavaLength();
                  }

                  // Consume style ranges until we hit one that overlaps with the current range.
                  while (javaOffset + styleRange.length <= rangeJavaOffset)
                  {
                    newStyleRanges.add(styleRange);
                    if (i.hasNext())
                    {
                      styleRange = i.next();
                      javaOffset = styleRange.start;
                    }
                    else
                    {
                      break LOOP;
                    }
                  }

                  // Trim off any unstyled prefix from the style range.
                  int delta = rangeJavaOffset - javaOffset;
                  if (delta > 0)
                  {
                    // The prefix has normal style so we clone the range and reduce the length.
                    StyleRange newStyleRange = (StyleRange)styleRange.clone();
                    newStyleRange.length = delta;
                    newStyleRanges.add(newStyleRange);

                    // If we've consumed the whole style range...
                    if (styleRange.length == delta)
                    {
                      if (i.hasNext())
                      {
                        // If there are style ranges left, continue the loop.
                        styleRange = i.next();
                        continue;
                      }
                      else
                      {
                        // Otherwise we're done.
                        break;
                      }
                    }

                    // Advance the style range.
                    javaOffset = styleRange.start += delta;
                    styleRange.length -= delta;
                  }

                  // Consume the styled part of the style range.
                  int lengthDelta = styleRange.length - rangeJavaLength;
                  if (lengthDelta > 0)
                  {
                    // The new style range completely consumes the range.
                    StyleRange newStyleRange = (StyleRange)styleRange.clone();
                    newStyleRange.length = rangeJavaLength;
                    newStyleRange.background = getBackground(range);
                    newStyleRanges.add(newStyleRange);

                    // Advance the style range by the consumed length.
                    styleRange.start += rangeJavaLength;
                    styleRange.length = lengthDelta;

                    // Advance the range if there are any left.
                    if (!r.hasNext())
                    {
                      // If not, consume the remaining portion of the style range and terminate.
                      newStyleRanges.add(styleRange);
                      break LOOP;
                    }
                    else
                    {
                      range = r.next();
                    }
                  }
                  else
                  {
                    // The style range is completely consumed by the range.
                    styleRange.background = getBackground(range);
                    newStyleRanges.add(styleRange);

                    // If we're out of style ranges, terminate.
                    if (!i.hasNext())
                    {
                      break;
                    }
                    else
                    {
                      // Otherwise, advance the range to the remaining portion and advanced to the next style range.
                      range = range.subrange(styleRange.length);
                      styleRange = i.next();
                    }
                  }
                }

                // Consume any remaining unaltered style ranges.
                while (i.hasNext())
                {
                  newStyleRanges.add(i.next());
                }

                textPresentation.clear();
                for (StyleRange newStyleRange : newStyleRanges)
                {
                  textPresentation.addStyleRange(newStyleRange);
                }
              }
            }

            if (jetEditor.isJavaEditorInSync())
            {
              jetEditor.invalidateVisibleTextPresentation(true);
            }
          }
        });

      try
      {
        // Disable the Java editor from also setting the last edit location.
        Field textListenerField = AbstractTextEditor.class.getDeclaredField("fTextListener");
        textListenerField.setAccessible(true);
        Object textListener = textListenerField.get(this);
        Field runnableField = textListener.getClass().getDeclaredField("fRunnable");
        runnableField.setAccessible(true);
        runnableField.set(textListener, new Runnable()
          {
            public void run()
            {
            }
          });
      }
      catch (Exception exception)
      {
        // If that doesn't work, it's not tragic, just annoying.
      }

      ((IPostSelectionProvider)getSelectionProvider()).addSelectionChangedListener(new JavadocUpdater(jetEditor));
    }

    private static final class JavadocUpdater implements ISelectionChangedListener
    {
      private final JETEditor jetEditor;

      public JavadocUpdater(JETEditor jetEditor)
      {
        this.jetEditor = jetEditor;
      }

      public void selectionChanged(SelectionChangedEvent event)
      {
        JavaEditor javaEditor = jetEditor.getJavaEditor();
        ISelectionProvider selectionProvider = javaEditor.getSelectionProvider();
        ISelectionValidator selectionValidator = (ISelectionValidator)selectionProvider;
        if (selectionValidator.isValid(event.getSelection()))
        {
          IWorkbenchPage page = jetEditor.getSite().getPage();
          IViewPart javadocView = page.findView(JavaUI.ID_JAVADOC_VIEW);
          if (javadocView != null)
          {
            IViewPart[] viewStack = page.getViewStack(javadocView);
            if (viewStack != null && viewStack.length > 0 && viewStack[0] == javadocView)
            {
              ((ISelectionListener)javadocView).selectionChanged(javaEditor, selectionProvider.getSelection());
            }
          }
        }
      }
    }

    private static final class StatusFilteringForwardingDocumentProvider extends ForwardingDocumentProvider
    {
      public StatusFilteringForwardingDocumentProvider(IDocumentProvider parentProvider)
      {
        super(IJavaPartitions.JAVA_PARTITIONING, new IDocumentSetupParticipant()
          {
            public void setup(IDocument document)
            {
            }
          }, parentProvider);
      }

      @Override
      public IStatus getStatus(Object element)
      {
        return Status.OK_STATUS;
      }
    }

    private static class DelegatingEditorSite implements IEditorSite
    {
      private final IEditorSite editorSite;

      private SubActionBars subActionBars;

      public DelegatingEditorSite(IEditorSite editorSite)
      {
        this.editorSite = editorSite;
        subActionBars = new SubActionBars(getEditorSite().getActionBars());
      }

      public IEditorSite getEditorSite()
      {
        return editorSite;
      }

      public IEditorActionBarContributor getActionBarContributor()
      {
        return getEditorSite().getActionBarContributor();
      }

      public String getId()
      {
        return JavaUI.ID_CU_EDITOR;
      }

      @SuppressWarnings("rawtypes")
      public boolean hasService(Class api)
      {
        return false;
      }

      @SuppressWarnings({ "unchecked", "rawtypes" })
      public Object getService(Class api)
      {
        return null;
      }

      public String getPluginId()
      {
        return getEditorSite().getPluginId();
      }

      @SuppressWarnings({ "unchecked", "rawtypes" })
      public Object getAdapter(Class adapter)
      {
        return getEditorSite().getAdapter(adapter);
      }

      public IWorkbenchPage getPage()
      {
        return getEditorSite().getPage();
      }

      public ISelectionProvider getSelectionProvider()
      {
        return getEditorSite().getSelectionProvider();
      }

      public Shell getShell()
      {
        return getEditorSite().getShell();
      }

      public IActionBars getActionBars()
      {
        return subActionBars;
      }

      public IWorkbenchPart getPart()
      {
        return getEditorSite().getPart();
      }

      public IWorkbenchWindow getWorkbenchWindow()
      {
        return getEditorSite().getWorkbenchWindow();
      }

      public void setSelectionProvider(ISelectionProvider provider)
      {
        getEditorSite().setSelectionProvider(provider);
      }

      public String getRegisteredName()
      {
        return getEditorSite().getRegisteredName();
      }

      public void registerContextMenu(String menuId, MenuManager menuManager, ISelectionProvider selectionProvider)
      {
      }

      public void registerContextMenu(MenuManager menuManager, ISelectionProvider selectionProvider, boolean includeEditorInput)
      {
      }

      public void registerContextMenu(MenuManager menuManager, ISelectionProvider selectionProvider)
      {
      }

      public void registerContextMenu(String menuId, MenuManager menuManager, ISelectionProvider selectionProvider, boolean includeEditorInput)
      {
      }

      @SuppressWarnings("deprecation")
      public org.eclipse.ui.IKeyBindingService getKeyBindingService()
      {
        return new DelegatingKeyBindingService();
      }

      @SuppressWarnings("deprecation")
      private static class DelegatingKeyBindingService implements org.eclipse.ui.IKeyBindingService
      {
        public String[] getScopes()
        {
          return new String [0];
        }

        public void registerAction(IAction action)
        {
        }

        public void setScopes(String[] scopes)
        {
        }

        public void unregisterAction(IAction action)
        {
        }
      }
    }
  }

  static abstract class SneakyRunnable implements Runnable
  {
    @SuppressWarnings("unchecked")
    private static <E extends Throwable> void sneakyThrow(Throwable throwable) throws E
    {
      throw (E)throwable;
    }

    public final void run()
    {
      try
      {
        execute();
      }
      catch (Throwable throwable)
      {
        SneakyRunnable.<RuntimeException> sneakyThrow(throwable);
      }
    }

    protected abstract void execute() throws Throwable;
  }

  static IEditorPart open(IEditorSite site, String locationURI)
  {
    IFile file = toFile(URI.createURI(locationURI));
    if (file != null && file.exists())
    {
      try
      {
        String editorID = site.getWorkbenchWindow().getWorkbench().getEditorRegistry().getDefaultEditor(file.getName()).getId();
        return site.getPage().openEditor(new FileEditorInput(file), editorID);
      }
      catch (PartInitException exception)
      {
        CodeGenUIPlugin.write(exception);
      }
    }

    return null;
  }

  static IFile toFile(URI uri)
  {
    return uri.isPlatformResource() ? ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true))) : null;
  }

  static URI toPlatformResourceURI(IEditorInput input)
  {
    return toURI(toFile(input));
  }

  static IFile toFile(IEditorInput input)
  {
    if (input instanceof StorageEditorInput)
    {
      return ((StorageEditorInput)input).getFile();
    }
    else
    {
      return input instanceof IAdaptable ? (IFile)((IAdaptable)input).getAdapter(IFile.class) : null;
    }
  }

  static URI toURI(IResource resource)
  {
    return resource == null ? null : URI.createPlatformResourceURI(resource.getFullPath().toString(), true);
  }

  static IRegion toRegion(JETItem jetItem)
  {
    int start = jetItem.getStartOffset();
    int stop = jetItem.getStopOffset();
    return new Region(start, stop - start);
  }

  static IEditorInput toEditorInput(URI uri)
  {
    if (uri != null && uri.isPlatformResource())
    {
      IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true)));
      if (file.isAccessible())
      {
        return new FileEditorInput(file);
      }
    }

    return new StorageEditorInput(JETNature.resolve(uri));
  }

  static IFile toFile(IPath path)
  {
    return ResourcesPlugin.getWorkspace().getRoot().getFile(path);
  }

  static URI toURI(IEditorInput input)
  {
    if (input instanceof IFileEditorInput)
    {
      return toURI(((IFileEditorInput)input).getFile());
    }
    else if (input instanceof StorageEditorInput)
    {
      return URI.createURI(((StorageEditorInput)input).getURI().toString());
    }
    else if (input instanceof IURIEditorInput)
    {
      return URI.createURI(((IURIEditorInput)input).getURI().toString());
    }
    else
    {
      return null;
    }
  }

  static boolean installOnDocument(IDocument document, Position position, String category, IPositionUpdater positionUpdater)
  {
    if (document != null && position != null)
    {
      if (!document.containsPositionCategory(category))
      {
        document.addPositionCategory(category);
        document.addPositionUpdater(positionUpdater);
      }

      try
      {
        document.addPosition(category, position);
        return true;
      }
      catch (Exception excetpion)
      {
      }
    }

    return false;
  }

  static boolean uninstallFromDocument(IDocument document, Position position, String category, IPositionUpdater positionUpdater)
  {
    if (document != null && position != null)
    {
      try
      {
        document.removePosition(category, position);

        Position[] positions = document.getPositions(category);
        if (positions == null || positions.length == 0)
        {
          document.removePositionCategory(category);
          document.removePositionUpdater(positionUpdater);
        }

        return true;
      }
      catch (BadPositionCategoryException exception)
      {
      }
    }

    return false;
  }
}
