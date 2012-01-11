package org.eclipse.emf.ecore.xcore.mappings;

import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeReference;

public class XDataTypeMapping extends AbstractMapping
{
  private EDataType eDataType;
  private GenDataType genDataType;
  private JvmTypeReference dataType;
  private JvmOperation creator;
  private JvmOperation converter;

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

  public JvmTypeReference getDataType()
  {
    return dataType;
  }

  public void setDataType(JvmTypeReference dataType)
  {
    this.dataType = dataType;
  }

  public JvmOperation getCreator()
  {
    return creator;
  }

  public void setCreator(JvmOperation creator)
  {
    this.creator = creator;
  }

  public JvmOperation getConverter()
  {
    return converter;
  }

  public void setConverter(JvmOperation converter)
  {
    this.converter = converter;
  }
}
