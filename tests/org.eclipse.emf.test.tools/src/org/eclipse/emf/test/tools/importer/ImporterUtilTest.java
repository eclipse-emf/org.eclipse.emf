/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * ImporterUtilTest.java,v 1.1 2005/05/16 14:16:30 marcelop Exp
 */
package org.eclipse.emf.test.tools.importer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.importer.util.ImporterUtil;

public class ImporterUtilTest extends TestCase
{
  /**
   * @param name
   */
  public ImporterUtilTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("ImporterUtilTest");
    ts.addTest(new ImporterUtilTest("testDecodeAction"));
    ts.addTest(new ImporterUtilTest("testValidPluginID"));
    ts.addTest(new ImporterUtilTest("testMergeDiagnostic"));
    ts.addTest(new ImporterUtilTest("testCreateErrorDiagnostic"));
    return ts;
  }
  
  public void testDecodeAction()
  {
    int actionCode = 0;
    ImporterUtil.DecodedAction decodedAction = null;
    
    actionCode =
      ImporterUtil.ACTION_DEFAULT
      | ImporterUtil.ACTION_MESSAGE_NONE 
      | ImporterUtil.ACTION_MESSAGE_SET 
      | ImporterUtil.ACTION_MESSAGE_SET_TYPED 
      | ImporterUtil.ACTION_MESSAGE_SET_ERROR
      | ImporterUtil.ACTION_DEFAULT
      | ImporterUtil.ACTION_DIALOG_NONE
      | ImporterUtil.ACTION_DIALOG_SHOW_IF_HAS_CHILD
      | ImporterUtil.ACTION_DIALOG_SHOW
      | ImporterUtil.ACTION_DIALOG_SHOW_ERROR
      ; 
    decodedAction = ImporterUtil.decodeAction(actionCode);
    assertEquals(ImporterUtil.ACTION_MESSAGE_SET_ERROR, decodedAction.message);
    assertEquals(ImporterUtil.ACTION_DIALOG_SHOW_ERROR, decodedAction.dialog);

    actionCode =
      ImporterUtil.ACTION_DEFAULT
      | ImporterUtil.ACTION_MESSAGE_NONE 
      | ImporterUtil.ACTION_MESSAGE_SET 
      | ImporterUtil.ACTION_MESSAGE_SET_TYPED 
      | ImporterUtil.ACTION_DEFAULT
      | ImporterUtil.ACTION_DIALOG_NONE
      | ImporterUtil.ACTION_DIALOG_SHOW_IF_HAS_CHILD
      | ImporterUtil.ACTION_DIALOG_SHOW
      ; 
    decodedAction = ImporterUtil.decodeAction(actionCode);
    assertEquals(ImporterUtil.ACTION_MESSAGE_SET_TYPED, decodedAction.message);
    assertEquals(ImporterUtil.ACTION_DIALOG_SHOW, decodedAction.dialog);

    actionCode =
      ImporterUtil.ACTION_DEFAULT
      | ImporterUtil.ACTION_MESSAGE_NONE 
      | ImporterUtil.ACTION_MESSAGE_SET
      | ImporterUtil.ACTION_DEFAULT
      | ImporterUtil.ACTION_DIALOG_NONE
      | ImporterUtil.ACTION_DIALOG_SHOW_IF_HAS_CHILD
      ; 
    decodedAction = ImporterUtil.decodeAction(actionCode);
    assertEquals(ImporterUtil.ACTION_MESSAGE_SET, decodedAction.message);
    assertEquals(ImporterUtil.ACTION_DIALOG_SHOW_IF_HAS_CHILD, decodedAction.dialog);

    actionCode =
      ImporterUtil.ACTION_DEFAULT
      | ImporterUtil.ACTION_MESSAGE_NONE 
      | ImporterUtil.ACTION_DEFAULT
      | ImporterUtil.ACTION_DIALOG_NONE
      ; 
    decodedAction = ImporterUtil.decodeAction(actionCode);
    assertEquals(ImporterUtil.ACTION_MESSAGE_NONE, decodedAction.message);
    assertEquals(ImporterUtil.ACTION_DIALOG_NONE, decodedAction.dialog);

    actionCode =
      ImporterUtil.ACTION_DEFAULT
      | ImporterUtil.ACTION_DEFAULT
      ; 
    decodedAction = ImporterUtil.decodeAction(actionCode);
    assertEquals(ImporterUtil.ACTION_DEFAULT, decodedAction.message);
    assertEquals(ImporterUtil.ACTION_DEFAULT, decodedAction.dialog);

    actionCode =
      ImporterUtil.ACTION_DEFAULT
      ; 
    decodedAction = ImporterUtil.decodeAction(actionCode);
    assertEquals(ImporterUtil.ACTION_DEFAULT, decodedAction.message);
    assertEquals(ImporterUtil.ACTION_DEFAULT, decodedAction.dialog);

    actionCode =
      ImporterUtil.ACTION_MESSAGE_SET
      | ImporterUtil.ACTION_DIALOG_SHOW_ERROR
      ; 
    decodedAction = ImporterUtil.decodeAction(actionCode);
    assertEquals(ImporterUtil.ACTION_MESSAGE_SET, decodedAction.message);
    assertEquals(ImporterUtil.ACTION_DIALOG_SHOW_ERROR, decodedAction.dialog);

    actionCode =
      ImporterUtil.ACTION_DIALOG_NONE
      ; 
    decodedAction = ImporterUtil.decodeAction(actionCode);
    assertEquals(ImporterUtil.ACTION_DEFAULT, decodedAction.message);
    assertEquals(ImporterUtil.ACTION_DIALOG_NONE, decodedAction.dialog);
  }
  
  public void testValidPluginID()
  {
    assertEquals("ab.c.12", ImporterUtil.validPluginID("ab.c.12"));
    assertEquals("ab.c.12", ImporterUtil.validPluginID("a b. c. 1 2"));
    assertEquals("a_b.c._12", ImporterUtil.validPluginID("a-b .c.-1 2"));
    assertEquals("a_b�.c._1�2", ImporterUtil.validPluginID("a-b�.c.-1�2"));
  }
  
  public void testMergeDiagnostic()
  {
    Exception baseException = new Exception();
    Exception toBeMergedException = new Exception();
    Diagnostic baseDiagnostic = new BasicDiagnostic(Diagnostic.INFO, "base", 1, "baseMessage", new Object[]{baseException});
    Diagnostic diagnosticToBeMerged = new BasicDiagnostic(Diagnostic.OK, "merged", 1, "toBeMergedMessage", new Object[]{toBeMergedException});
    Diagnostic mergedDiagnostic = ImporterUtil.mergeDiagnostic(baseDiagnostic, diagnosticToBeMerged);
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
    Diagnostic diagnostic = ImporterUtil.createErrorDiagnostic(throwable, false);
    assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
    assertEquals(ImporterUtil.ACTION_DEFAULT, diagnostic.getCode());
    assertEquals(throwable, diagnostic.getException());
    String message = ImporterPlugin.INSTANCE.getString("_UI_GenericException_message", new Object[]{"NullPointerException"}); 
    assertEquals(message, diagnostic.getMessage());

    Exception rootException = new Exception("root");
    diagnostic = ImporterUtil.createErrorDiagnostic(rootException, true);
    assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
    assertEquals(ImporterUtil.ACTION_DIALOG_SHOW_ERROR, diagnostic.getCode());
    assertEquals(rootException, diagnostic.getException());
    assertEquals("root", diagnostic.getMessage());

    throwable = new Exception(new Exception(new Exception(rootException)));
    diagnostic = ImporterUtil.createErrorDiagnostic(throwable, true);
    assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
    assertEquals(ImporterUtil.ACTION_DIALOG_SHOW_ERROR, diagnostic.getCode());
    assertEquals(rootException, diagnostic.getException());
    assertEquals("root", diagnostic.getMessage());

    throwable = new WrappedException(new WrappedException(rootException));
    diagnostic = ImporterUtil.createErrorDiagnostic(throwable, true);
    assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
    assertEquals(ImporterUtil.ACTION_DIALOG_SHOW_ERROR, diagnostic.getCode());
    assertEquals(rootException, diagnostic.getException());
    assertEquals("root", diagnostic.getMessage());

    throwable = new WrappedException(new Exception(new WrappedException(new Exception(rootException))));
    diagnostic = ImporterUtil.createErrorDiagnostic(throwable, false);
    assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
    assertEquals(ImporterUtil.ACTION_DEFAULT, diagnostic.getCode());
    assertEquals(rootException, diagnostic.getException());
    assertEquals("root", diagnostic.getMessage());
  }
}