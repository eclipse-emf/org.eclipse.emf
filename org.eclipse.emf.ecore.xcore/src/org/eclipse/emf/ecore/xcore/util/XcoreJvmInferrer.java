package org.eclipse.emf.ecore.xcore.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.common.types.util.TypeReferences;

import com.google.inject.Inject;

public class XcoreJvmInferrer
{
	@Inject
	private TypeReferences typeReferences;
	
  public List<? extends JvmDeclaredType> getDeclaredTypes(XPackage xPackage)
  {
    GenPackage genPackage = (GenPackage)XcoreEcoreBuilder.getGen(XcoreEcoreBuilder.get(xPackage));
    return getDeclaredTypes(genPackage);
  }

  public List<? extends JvmDeclaredType> getDeclaredTypes(GenPackage genPackage)
  {
    ArrayList<JvmDeclaredType> result = new ArrayList<JvmDeclaredType>();
    for (GenClassifier genClassifier : genPackage.getGenClassifiers())
    {
      result.addAll(getDeclaredTypes(genClassifier));
    }
    return result;
  }
  
  public List<? extends JvmDeclaredType> getDeclaredTypes(GenClassifier genClassifier)
  {
    ArrayList<JvmDeclaredType> result = new ArrayList<JvmDeclaredType>();
    if (genClassifier instanceof GenClass)
    {
      result.addAll(getDeclaredTypes((GenClass)genClassifier));
    }
    else if (genClassifier instanceof GenEnum)
    {
      
    }
    else if (genClassifier instanceof EDataType)
    {
      
    }
    return result;
  }

  public List<? extends JvmDeclaredType> getDeclaredTypes(GenClass genClass)
  {
  	// TODO set java.lang.Object super type or inferred super type
    ArrayList<JvmDeclaredType> result = new ArrayList<JvmDeclaredType>();
    JvmGenericType jvmGenericType = TypesFactory.eINSTANCE.createJvmGenericType();
    jvmGenericType.setSimpleName(genClass.getName());
    jvmGenericType.setPackageName(genClass.getGenPackage().getInterfacePackageName());
    jvmGenericType.setVisibility(JvmVisibility.PUBLIC);
    
    JvmTypeReference eObjectImpl = typeReferences.getTypeForName(EObjectImpl.class, genClass);
    jvmGenericType.getSuperTypes().add(eObjectImpl);
    
    result.add(jvmGenericType);
    return result;
  }
}
