package org.eclipse.emf.ecore.xcore.mappings;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtext.common.types.JvmGenericType;

public class XClassMapping extends AbstractMapping
{
	 private EClass eclass;
	 private GenClass genClass;
	 private JvmGenericType interfaceType;
	 private JvmGenericType classType;
   
	public EClass getEclass()
  {
  	return eclass;
  }

	public void setEclass(EClass eclass)
  {
  	this.eclass = eclass;
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
