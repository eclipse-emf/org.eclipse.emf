/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xcore.XGenericType;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class XcoreExtensions {
  public GenClass getGenClass(final XGenericType type) {
    GenClass _switchResult = null;
    GenBase _type = type.getType();
    final GenBase t = _type;
    boolean _matched = false;
    if (!_matched) {
      if (t instanceof GenClass) {
        _matched=true;
        _switchResult = ((GenClass)t);
      }
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
    if (!_matched) {
      if (t instanceof GenTypeParameter) {
        _matched=true;
        _switchResult = ((GenTypeParameter)t);
      }
    }
    if (!_matched) {
      _switchResult = null;
    }
    return _switchResult;
  }
  
  public static Iterable<EObject> allContentsIterable(final EObject eObject) {
    TreeIterator<EObject> _eAllContents = eObject.eAllContents();
    return IteratorExtensions.<EObject>toIterable(_eAllContents);
  }
  
  public static Iterable<EObject> allContentsIterable(final Resource resource) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    return IteratorExtensions.<EObject>toIterable(_allContents);
  }
}
