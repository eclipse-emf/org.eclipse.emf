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
        final GenClass _genClass = (GenClass)t;
        _matched=true;
        _switchResult = _genClass;
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
        final GenTypeParameter _genTypeParameter = (GenTypeParameter)t;
        _matched=true;
        _switchResult = _genTypeParameter;
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
