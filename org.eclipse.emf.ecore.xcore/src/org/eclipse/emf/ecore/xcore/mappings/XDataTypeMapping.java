package org.eclipse.emf.ecore.xcore.mappings;

import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.xtext.common.types.JvmGenericType;

public class XDataTypeMapping extends AbstractMapping
{
	 private EDataType eDataType;
	 private GenDataType genDataType;
	 private JvmGenericType interfaceType;
	 private JvmGenericType dataType;
   
	public EDataType getEDataType()
  {
  	return eDataType;
  }

	public void setEDataType(EDataType eDataType)
  {
  	this.eDataType = eDataType;
  }

	public GenDataType getGenDataType()
  {
  	return genDataType;
  }

	public void setGenDataType(GenDataType genDataType)
  {
  	this.genDataType = genDataType;
  }

	public JvmGenericType getInterfaceType()
  {
  	return interfaceType;
  }

	public void setInterfaceType(JvmGenericType interfaceType)
  {
  	this.interfaceType = interfaceType;
  }

	public JvmGenericType getDataType()
  {
  	return dataType;
  }

	public void setDataType(JvmGenericType dataType)
  {
  	this.dataType = dataType;
  }
   
   
}
