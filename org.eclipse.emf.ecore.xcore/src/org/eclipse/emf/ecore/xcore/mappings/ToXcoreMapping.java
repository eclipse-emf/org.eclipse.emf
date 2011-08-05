package org.eclipse.emf.ecore.xcore.mappings;

import org.eclipse.emf.ecore.xcore.XNamedElement;

public class ToXcoreMapping extends AbstractMapping
{
	private XNamedElement xcoreElement;

	public XNamedElement getXcoreElement()
  {
	  return xcoreElement;
  }
	
	public void setXcoreElement(XNamedElement xcoreElement)
  {
	  this.xcoreElement = xcoreElement;
  }
}
