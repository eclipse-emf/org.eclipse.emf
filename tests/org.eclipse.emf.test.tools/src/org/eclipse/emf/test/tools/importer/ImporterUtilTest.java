/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * ImporterUtilTest.java,v 1.1 2005/05/16 14:16:30 marcelop Exp
 */
package org.eclipse.emf.test.tools.importer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.importer.util.ImporterUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
    ts.addTest(new ImporterUtilTest("testMergeStatus"));
    ts.addTest(new ImporterUtilTest("testCreateErrorStatus"));
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
    assertEquals("a_bá.c._1ã2", ImporterUtil.validPluginID("a-bá.c.-1ã2"));
  }
  
  public void testMergeStatus()
  {
    Exception baseException = new Exception();
    Exception toBeMergedException = new Exception();
    IStatus baseStatus = new Status(IStatus.INFO, "base", 1, "baseMessage", baseException);
    IStatus statusToBeMerged = new Status(IStatus.OK, "merged", 1, "toBeMergedMessage", toBeMergedException);
    MultiStatus mergedStatus = ImporterUtil.mergeStatus(baseStatus, statusToBeMerged);
    assertTrue(mergedStatus.isMultiStatus());
    assertEquals(baseStatus.getSeverity(), mergedStatus.getSeverity());
    assertEquals(baseStatus.getPlugin(), mergedStatus.getPlugin());
    assertEquals(baseStatus.getCode(), mergedStatus.getCode());
    assertEquals(baseStatus.getMessage(), mergedStatus.getMessage());
    assertEquals(baseStatus.getException(), mergedStatus.getException());
    assertEquals(1, mergedStatus.getChildren().length);
    assertEquals(statusToBeMerged, mergedStatus.getChildren()[0]);
  }
  
  public void testCreateErrorStatus()
  {
    Throwable throwable = new NullPointerException();
    IStatus status = ImporterUtil.createErrorStatus(throwable, false);
    assertEquals(IStatus.ERROR, status.getSeverity());
    assertEquals(ImporterUtil.ACTION_DEFAULT, status.getCode());
    assertEquals(throwable, status.getException());
    String message = ImporterPlugin.INSTANCE.getString("_UI_GenericException_message", new Object[]{"NullPointerException"}); 
    assertEquals(message, status.getMessage());

    Exception rootException = new Exception("root");
    status = ImporterUtil.createErrorStatus(rootException, true);
    assertEquals(IStatus.ERROR, status.getSeverity());
    assertEquals(ImporterUtil.ACTION_DIALOG_SHOW_ERROR, status.getCode());
    assertEquals(rootException, status.getException());
    assertEquals("root", status.getMessage());

    throwable = new Exception(new Exception(new Exception(rootException)));
    status = ImporterUtil.createErrorStatus(throwable, true);
    assertEquals(IStatus.ERROR, status.getSeverity());
    assertEquals(ImporterUtil.ACTION_DIALOG_SHOW_ERROR, status.getCode());
    assertEquals(rootException, status.getException());
    assertEquals("root", status.getMessage());

    throwable = new WrappedException(new WrappedException(rootException));
    status = ImporterUtil.createErrorStatus(throwable, true);
    assertEquals(IStatus.ERROR, status.getSeverity());
    assertEquals(ImporterUtil.ACTION_DIALOG_SHOW_ERROR, status.getCode());
    assertEquals(rootException, status.getException());
    assertEquals("root", status.getMessage());

    throwable = new WrappedException(new Exception(new WrappedException(new Exception(rootException))));
    status = ImporterUtil.createErrorStatus(throwable, false);
    assertEquals(IStatus.ERROR, status.getSeverity());
    assertEquals(ImporterUtil.ACTION_DEFAULT, status.getCode());
    assertEquals(rootException, status.getException());
    assertEquals("root", status.getMessage());
  }
}