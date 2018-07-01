/**
 * Copyright (c) 2018 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.xcore.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.XAbstractFeatureCall;
import org.eclipse.xtext.xbase.XbasePackage;
import org.eclipse.xtext.xbase.util.FeatureCallAsTypeLiteralHelper;

import com.google.inject.Singleton;

@Singleton
public class XcoreFeatureCallAsTypeLiteralHelper extends FeatureCallAsTypeLiteralHelper
{
  @Override
  public XAbstractFeatureCall getRootTypeLiteral(XAbstractFeatureCall featureCall)
  {
    EObject feature = (EObject)featureCall.eGet(XbasePackage.Literals.XABSTRACT_FEATURE_CALL__FEATURE, false);
    if (feature == null || feature.eIsProxy())
    {
      return null;
    }
    else
    {
      return super.getRootTypeLiteral(featureCall);
    }
  }
}
