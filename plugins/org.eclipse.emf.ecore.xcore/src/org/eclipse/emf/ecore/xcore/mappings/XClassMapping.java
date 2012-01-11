package org.eclipse.emf.ecore.xcore.mappings;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtext.common.types.JvmGenericType;

public class XClassMapping extends AbstractMapping
{
	 private EClass eClass;
	 private GenClass genClass;
	 private JvmGenericType interfaceType;
	 private JvmGenericType classType;
   
	public EClass getEClass()
  {
  	return eClass;
  }

	public void setEClass(EClass eclass)
  {
  	this.eClass = eclass;
  }

	public GenClass getGenClass()
  {
  	return genClass;
  }

	public void setGenClass(GenClass genClass)
  {
  	this.genClass = genClass;
  }

	public JvmGenericType getInterfaceType()
  {
  	return interfaceType;
  }

	public void setInterfaceType(JvmGenericType interfaceType)
  {
  	this.interfaceType = interfaceType;
  }

	public JvmGenericType getClassType()
  {
  	return classType;
  }

	public void setClassType(JvmGenericType classType)
  {
  	this.classType = classType;
  }
   
   
}
