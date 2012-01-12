package org.eclipse.emf.ecore.xcore;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.ecore.xcore.XGenericType;

@SuppressWarnings("all")
public class XcoreExtensions {
  public GenClass getGenClass(final XGenericType type) {
    GenClass _switchResult = null;
    GenBase _type = type.getType();
    final GenBase t = _type;
    boolean matched = false;
    if (!matched) {
      if (t instanceof GenClass) {
        final GenClass _genClass = (GenClass)t;
        matched=true;
        _switchResult = _genClass;
      }
    }
    if (!matched) {
      _switchResult = null;
    }
    return _switchResult;
  }
  
  public GenTypeParameter getGenTypeParameter(final XGenericType type) {
    GenTypeParameter _switchResult = null;
    GenBase _type = type.getType();
    final GenBase t = _type;
    boolean matched = false;
    if (!matched) {
      if (t instanceof GenTypeParameter) {
        final GenTypeParameter _genTypeParameter = (GenTypeParameter)t;
        matched=true;
        _switchResult = _genTypeParameter;
      }
    }
    if (!matched) {
      _switchResult = null;
    }
    return _switchResult;
  }
}
