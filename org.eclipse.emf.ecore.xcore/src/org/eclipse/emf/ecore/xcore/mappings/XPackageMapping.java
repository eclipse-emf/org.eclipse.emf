package org.eclipse.emf.ecore.xcore.mappings;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EPackage;

public class XPackageMapping extends AbstractMapping
{
	private EPackage ePackage;
	private GenPackage genPackage;
	
	public EPackage getEPackage()
  {
  	return ePackage;
  }
	public void setEPackage(EPackage ePackage)
  {
  	this.ePackage = ePackage;
  }
	public GenPackage getGenPackage()
  {
  	return genPackage;
  }
	public void setGenPackage(GenPackage genPackage)
  {
  	this.genPackage = genPackage;
  }
	
}
