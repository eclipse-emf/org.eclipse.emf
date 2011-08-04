package org.eclipse.emf.ecore.xcore.util;

import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.util.XcoreEcoreBuilder;
import org.eclipse.xtext.common.types.JvmOperation;

@SuppressWarnings("all")
public class MappingFacade {
  
  public EOperation getEOperation(final XOperation op) {
    EObject _get = XcoreEcoreBuilder.get(op);
    return ((EOperation) _get);
  }
  
  public GenOperation getGenOperation(final XOperation op) {
    EObject _get = XcoreEcoreBuilder.get(op);
    return ((GenOperation) _get);
  }
  
  public GenOperation getXOperation(final GenOperation op) {
    EObject _get = XcoreEcoreBuilder.get(op);
    return ((GenOperation) _get);
  }
  
  public JvmOperation getJvmOperation(final XOperation op) {
    return null;
  }
}