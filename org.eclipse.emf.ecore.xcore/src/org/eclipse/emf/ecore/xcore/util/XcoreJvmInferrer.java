package org.eclipse.emf.ecore.xcore.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.common.types.TypesFactory;

public class XcoreJvmInferrer
{
  public List<? extends JvmDeclaredType> getDeclaredTypes(XPackage xPackage)
  {
    EPackage ePackage = (EPackage)XcoreEcoreBuilder.get(xPackage);
    return getDeclaredTypes(ePackage);
  }

  public List<? extends JvmDeclaredType> getDeclaredTypes(EPackage ePackage)
  {
    ArrayList<JvmDeclaredType> result = new ArrayList<JvmDeclaredType>();
    for (EClassifier eClassifier : ePackage.getEClassifiers())
    {
      result.addAll(getDeclaredTypes(eClassifier));
      
    }
    return result;
  }
  
  public List<? extends JvmDeclaredType> getDeclaredTypes(EClassifier eClassifier)
  {
    ArrayList<JvmDeclaredType> result = new ArrayList<JvmDeclaredType>();
    if (eClassifier instanceof EClass)
    {
      getDeclaredTypes((EClass)eClassifier);
    }
    else if (eClassifier instanceof EEnum)
    {
      
    }
    else if (eClassifier instanceof EDataType)
    {
      
    }
    return result;
  }

  public List<? extends JvmDeclaredType> getDeclaredTypes(EClass eClass)
  {
    ArrayList<JvmDeclaredType> result = new ArrayList<JvmDeclaredType>();
    JvmGenericType jvmGenericType = TypesFactory.eINSTANCE.createJvmGenericType();
    jvmGenericType.setSimpleName(eClass.getName());
    jvmGenericType.setPackageName(eClass.getEPackage().getName());
    jvmGenericType.setVisibility(JvmVisibility.PUBLIC);
    jvmGenericType.setStatic(true);
    return result;
  }
}
