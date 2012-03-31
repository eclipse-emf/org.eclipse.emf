/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.contentassist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.xcore.XTypedElement;
import org.eclipse.xtext.common.types.xtext.ui.JdtVariableCompletions;

public class XcoreVariableCompletions extends JdtVariableCompletions
{
  @Override
  protected String getTextUnderReference(EObject context, EReference refToTypeRef)
  {
    String result = super.getTextUnderReference(context, refToTypeRef);
    if (context instanceof XTypedElement)
    {
      XTypedElement xTypedElement = (XTypedElement)context;
      int[] multiplicity = xTypedElement.getMultiplicity();
      if (multiplicity != null)
      {
        if (multiplicity.length == 0 || 
              multiplicity.length == 1 && multiplicity[0] != 1 || 
              multiplicity.length == 2 && multiplicity[1] != 1)
        {
          // Add square brackets so that the variable proposal treats it as something that needs to be pluralized.
          //
          return result + "[]";
        }
      }
    }
    return result;
  }
}
