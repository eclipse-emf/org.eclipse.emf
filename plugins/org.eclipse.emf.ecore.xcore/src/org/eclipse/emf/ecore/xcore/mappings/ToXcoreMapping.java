package org.eclipse.emf.ecore.xcore.mappings;

import org.eclipse.emf.common.notify.Notifier;
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
	
	@Override
	public void setTarget(Notifier newTarget)
	{
	  // TODO Auto-generated method stub
	  super.setTarget(newTarget);
	}
	@Override
	public void unsetTarget(Notifier oldTarget)
	{
	  // TODO Auto-generated method stub
	  super.unsetTarget(oldTarget);
	}
}
