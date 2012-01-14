/**
 * Copyright (c) 2005-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.tools.converter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.converter.util.ConverterUtil;

public class ConverterUtilTest extends TestCase
{
  /**
   * @param name
   */
  public ConverterUtilTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("ConverterUtilTest");
    ts.addTest(new ConverterUtilTest("testDecodeAction"));
    ts.addTest(new ConverterUtilTest("testMergeDiagnostic"));
    ts.addTest(new ConverterUtilTest("testCreateErrorDiagnostic"));
    return ts;
  }
  
  public void testDecodeAction()
  {
    int actionCode = 0;
    ConverterUtil.DecodedAction decodedAction = null;
    
    actionCode =
      ConverterUtil.ACTION_DEFAULT
      | ConverterUtil.ACTION_MESSAGE_NONE 
      | ConverterUtil.ACTION_MESSAGE_SET 
      | ConverterUtil.ACTION_MESSAGE_SET_TYPED 
      | ConverterUtil.ACTION_MESSAGE_SET_ERROR
      | ConverterUtil.ACTION_DEFAULT
      | ConverterUtil.ACTION_DIALOG_NONE
      | ConverterUtil.ACTION_DIALOG_SHOW_IF_HAS_CHILD
      | ConverterUtil.ACTION_DIALOG_SHOW
      | ConverterUtil.ACTION_DIALOG_SHOW_ERROR
      ; 
    decodedAction = ConverterUtil.decodeAction(actionCode);
    assertEquals(ConverterUtil.ACTION_MESSAGE_SET_ERROR, decodedAction.message);
    assertEquals(ConverterUtil.ACTION_DIALOG_SHOW_ERROR, decodedAction.dialog);

    actionCode =
      ConverterUtil.ACTION_DEFAULT
      | ConverterUtil.ACTION_MESSAGE_NONE 
      | ConverterUtil.ACTION_MESSAGE_SET 
      | ConverterUtil.ACTION_MESSAGE_SET_TYPED 
      | ConverterUtil.ACTION_DEFAULT
      | ConverterUtil.ACTION_DIALOG_NONE
      | ConverterUtil.ACTION_DIALOG_SHOW_IF_HAS_CHILD
      | ConverterUtil.ACTION_DIALOG_SHOW
      ; 
    decodedAction = ConverterUtil.decodeAction(actionCode);
    assertEquals(ConverterUtil.ACTION_MESSAGE_SET_TYPED, decodedAction.message);
    assertEquals(ConverterUtil.ACTION_DIALOG_SHOW, decodedAction.dialog);

    actionCode =
      ConverterUtil.ACTION_DEFAULT
      | ConverterUtil.ACTION_MESSAGE_NONE 
      | ConverterUtil.ACTION_MESSAGE_SET
      | ConverterUtil.ACTION_DEFAULT
      | ConverterUtil.ACTION_DIALOG_NONE
      | ConverterUtil.ACTION_DIALOG_SHOW_IF_HAS_CHILD
      ; 
    decodedAction = ConverterUtil.decodeAction(actionCode);
    assertEquals(ConverterUtil.ACTION_MESSAGE_SET, decodedAction.message);
    assertEquals(ConverterUtil.ACTION_DIALOG_SHOW_IF_HAS_CHILD, decodedAction.dialog);

    actionCode =
      ConverterUtil.ACTION_DEFAULT
      | ConverterUtil.ACTION_MESSAGE_NONE 
      | ConverterUtil.ACTION_DEFAULT
      | ConverterUtil.ACTION_DIALOG_NONE
      ; 
    decodedAction = ConverterUtil.decodeAction(actionCode);
    assertEquals(ConverterUtil.ACTION_MESSAGE_NONE, decodedAction.message);
    assertEquals(ConverterUtil.ACTION_DIALOG_NONE, decodedAction.dialog);

    actionCode =
      ConverterUtil.ACTION_DEFAULT
      | ConverterUtil.ACTION_DEFAULT
      ; 
    decodedAction = ConverterUtil.decodeAction(actionCode);
    assertEquals(ConverterUtil.ACTION_DEFAULT, decodedAction.message);
    assertEquals(ConverterUtil.ACTION_DEFAULT, decodedAction.dialog);

    actionCode =
      ConverterUtil.ACTION_DEFAULT
      ; 
    decodedAction = ConverterUtil.decodeAction(actionCode);
    assertEquals(ConverterUtil.ACTION_DEFAULT, decodedAction.message);
    assertEquals(ConverterUtil.ACTION_DEFAULT, decodedAction.dialog);

    actionCode =
      ConverterUtil.ACTION_MESSAGE_SET
      | ConverterUtil.ACTION_DIALOG_SHOW_ERROR
      ; 
    decodedAction = ConverterUtil.decodeAction(actionCode);
    assertEquals(ConverterUtil.ACTION_MESSAGE_SET, decodedAction.message);
    assertEquals(ConverterUtil.ACTION_DIALOG_SHOW_ERROR, decodedAction.dialog);

    actionCode =
      ConverterUtil.ACTION_DIALOG_NONE
      ; 
    decodedAction = ConverterUtil.decodeAction(actionCode);
    assertEquals(ConverterUtil.ACTION_DEFAULT, decodedAction.message);
    assertEquals(ConverterUtil.ACTION_DIALOG_NONE, decodedAction.dialog);
  }
  
  public void testMergeDiagnostic()
  {
    Exception baseException = new Exception();
    Exception toBeMergedException = new Exception();
    Diagnostic baseDiagnostic = new BasicDiagnostic(Diagnostic.INFO, "base", 1, "baseMessage", new Object[]{baseException});
    Diagnostic diagnosticToBeMerged = new BasicDiagnostic(Diagnostic.OK, "merged", 1, "toBeMergedMessage", new Object[]{toBeMergedException});
    Diagnostic mergedDiagnostic = ConverterUtil.mergeDiagnostic(baseDiagnostic, diagnosticToBeMerged);
    assertFalse(mergedDiagnostic.getChildren().isEmpty());
    assertEquals(baseDiagnostic.getSeverity(), mergedDiagnostic.getSeverity());
    assertEquals(baseDiagnostic.getSource(), mergedDiagnostic.getSource());
    assertEquals(baseDiagnostic.getCode(), mergedDiagnostic.getCode());
    assertEquals(baseDiagnostic.getMessage(), mergedDiagnostic.getMessage());
    assertEquals(baseDiagnostic.getException(), mergedDiagnostic.getException());
    assertEquals(1, mergedDiagnostic.getChildren().size());
    assertEquals(diagnosticToBeMerged, mergedDiagnostic.getChildren().get(0));
  }
  
  public void testCreateErrorDiagnostic()
  {
    Throwable throwable = new NullPointerException();
    Diagnostic diagnostic = ConverterUtil.createErrorDiagnostic(throwable, false);
    assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
    assertEquals(ConverterUtil.ACTION_DEFAULT, diagnostic.getCode());
    assertEquals(throwable, diagnostic.getException());
    String message = ImporterPlugin.INSTANCE.getString("_UI_GenericException_message", new Object[]{"NullPointerException"}); 
    assertEquals(message, diagnostic.getMessage());
    assertEquals(0, diagnostic.getChildren().size());

    Exception rootException = new Exception("root");
    diagnostic = ConverterUtil.createErrorDiagnostic(rootException, true);
    assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
    assertEquals(ConverterUtil.ACTION_DIALOG_SHOW_ERROR, diagnostic.getCode());
    assertEquals(rootException, diagnostic.getException());
    assertEquals("An error occurred while performing this operation.", diagnostic.getMessage());
    assertEquals(1, diagnostic.getChildren().size());
    assertEquals("Exception: root", diagnostic.getChildren().get(0).getMessage());
    assertEquals(rootException, diagnostic.getChildren().get(0).getException());
    assertEquals(0, diagnostic.getChildren().get(0).getChildren().size());

    throwable = new Exception(new Exception(new Exception(rootException)));
    diagnostic = ConverterUtil.createErrorDiagnostic(throwable, true);
    assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
    assertEquals(ConverterUtil.ACTION_DIALOG_SHOW_ERROR, diagnostic.getCode());
    assertEquals(throwable, diagnostic.getException());
    assertEquals("An error occurred while performing this operation.", diagnostic.getMessage());
    assertEquals(1, diagnostic.getChildren().size());
    //
    Diagnostic childDiagnostic = diagnostic.getChildren().get(0);
    assertEquals("Exception: java.lang.Exception: java.lang.Exception: java.lang.Exception: root", childDiagnostic.getMessage());
    assertEquals(throwable, childDiagnostic.getException());
    assertEquals(1, childDiagnostic.getChildren().size());
    //
    childDiagnostic = childDiagnostic.getChildren().get(0);
    assertEquals("Exception: java.lang.Exception: java.lang.Exception: root", childDiagnostic.getMessage());
    assertEquals(throwable.getCause(), childDiagnostic.getException());
    assertEquals(1, childDiagnostic.getChildren().size());
    //
    childDiagnostic = childDiagnostic.getChildren().get(0);
    assertEquals("Exception: java.lang.Exception: root", childDiagnostic.getMessage());
    assertEquals(throwable.getCause().getCause(), childDiagnostic.getException());
    assertEquals(1, childDiagnostic.getChildren().size());
    //
    childDiagnostic = childDiagnostic.getChildren().get(0);
    assertEquals("Exception: root", childDiagnostic.getMessage());
    assertEquals(rootException, childDiagnostic.getException());
    assertEquals(0, childDiagnostic.getChildren().size());

    throwable = new WrappedException(new WrappedException(rootException));
    diagnostic = ConverterUtil.createErrorDiagnostic(throwable, true);
    assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
    assertEquals(ConverterUtil.ACTION_DIALOG_SHOW_ERROR, diagnostic.getCode());
    assertEquals(throwable, diagnostic.getException());
    assertEquals("An error occurred while performing this operation.", diagnostic.getMessage());
    assertEquals(1, diagnostic.getChildren().size());
    //
    childDiagnostic = diagnostic.getChildren().get(0);
    assertEquals("Exception: root", childDiagnostic.getMessage());
    assertEquals(rootException, childDiagnostic.getException());
    assertEquals(0, childDiagnostic.getChildren().size());    

    throwable = new WrappedException(new Exception(new WrappedException(new Exception(rootException))));
    diagnostic = ConverterUtil.createErrorDiagnostic(throwable, false);
    assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
    assertEquals(ConverterUtil.ACTION_DEFAULT, diagnostic.getCode());
    assertEquals(throwable, diagnostic.getException());
    assertEquals("The operation was interrupted due to an exception ('Exception: org.eclipse.emf.common.util.WrappedException: java.lang.Exception: java.lang.Exception: root').\nCheck the log for further details.", diagnostic.getMessage());
    assertEquals(1, diagnostic.getChildren().size());
    //
    childDiagnostic = diagnostic.getChildren().get(0);
    assertEquals("Exception: java.lang.Exception: root", childDiagnostic.getMessage());
    assertEquals(1, childDiagnostic.getChildren().size());    
    //
    childDiagnostic = childDiagnostic.getChildren().get(0);
    assertEquals("Exception: root", childDiagnostic.getMessage());
    assertEquals(rootException, childDiagnostic.getException());
    assertEquals(0, childDiagnostic.getChildren().size());
  }
}
