/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.xcore;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class XcoreExtensions {
  public GenClass getGenClass(final XGenericType type) {
    GenClass _switchResult = null;
    GenBase _type = type.getType();
    final GenBase t = _type;
    boolean _matched = false;
    if (t instanceof GenClass) {
      _matched=true;
      _switchResult = ((GenClass)t);
    }
    if (!_matched) {
      _switchResult = null;
    }
    return _switchResult;
  }
  
  public GenTypeParameter getGenTypeParameter(final XGenericType type) {
    GenTypeParameter _switchResult = null;
    GenBase _type = type.getType();
    final GenBase t = _type;
    boolean _matched = false;
    if (t instanceof GenTypeParameter) {
      _matched=true;
      _switchResult = ((GenTypeParameter)t);
    }
    if (!_matched) {
      _switchResult = null;
    }
    return _switchResult;
  }
  
  public static Iterable<EObject> allContentsIterable(final EObject eObject) {
    return IteratorExtensions.<EObject>toIterable(eObject.eAllContents());
  }
  
  public static Iterable<EObject> allContentsIterable(final Resource resource) {
    return IteratorExtensions.<EObject>toIterable(resource.getAllContents());
  }
}
