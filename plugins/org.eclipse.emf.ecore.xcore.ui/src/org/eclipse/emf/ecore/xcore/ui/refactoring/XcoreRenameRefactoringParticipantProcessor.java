/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.refactoring;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.xtext.common.types.ui.refactoring.participant.JdtRenameParticipant;
import org.eclipse.xtext.ui.refactoring.impl.RenameElementProcessor;
import org.eclipse.xtext.xbase.ui.jvmmodel.refactoring.JvmModelJdtRenameParticipantContext;
import org.eclipse.xtext.xbase.ui.jvmmodel.refactoring.jdt.JdtRenameRefactoringParticipantProcessor;

public class XcoreRenameRefactoringParticipantProcessor extends JdtRenameRefactoringParticipantProcessor
{
  @Override
  protected RefactoringStatus preCheckInitialConditions(IProgressMonitor pm) throws CoreException {
      JdtRenameParticipant jdtRenameParticipant = ((JvmModelJdtRenameParticipantContext) getRenameElementContext()).getJdtRenameParticipant();
      RefactoringProcessor triggeringProcessor = jdtRenameParticipant.getProcessor().getRefactoring().getProcessor();
      if (triggeringProcessor instanceof RenameElementProcessor) 
      {
        EClass targetElementEClass = ((RenameElementProcessor)triggeringProcessor).getRenameElementContext().getTargetElementEClass();
        if (targetElementEClass.getEPackage() == XcorePackage.eINSTANCE)
        {
          // Ensure that for refactorings of Xcore we don't fail because there are multiple reverse mappings for the JVM derived things being renamed as a result.
          //
          return new RefactoringStatus();
        }
      }
      return super.preCheckInitialConditions(pm);
  }
}
