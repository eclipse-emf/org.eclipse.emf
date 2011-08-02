package org.eclipse.emf.ecore.xcore.jvmmodel;

import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.util.XcoreJvmInferrer;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociator;
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelInferrer;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class XcoreJvmModelInferrer implements IJvmModelInferrer {
  
  @Inject
  private IJvmModelAssociator jvmModelAssociator;
  
  protected List<? extends JvmDeclaredType> _inferJvmModel(final EObject sourceObject) {
    ArrayList<JvmDeclaredType> _newArrayList = CollectionLiterals.<JvmDeclaredType>newArrayList();
    return _newArrayList;
  }
  
  protected List<? extends JvmDeclaredType> _inferJvmModel(final XPackage xPackage) {
    XcoreJvmInferrer _xcoreJvmInferrer = new XcoreJvmInferrer();
    List<? extends JvmDeclaredType> _declaredTypes = _xcoreJvmInferrer.getDeclaredTypes(xPackage);
    return _declaredTypes;
  }
  
  public List<? extends JvmDeclaredType> inferJvmModel(final EObject xPackage) {
    if ((xPackage instanceof XPackage)) {
      return _inferJvmModel((XPackage)xPackage);
    } else if ((xPackage instanceof EObject)) {
      return _inferJvmModel((EObject)xPackage);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        java.util.Arrays.<Object>asList(xPackage).toString());
    }
  }
}