package org.eclipse.emf.ecore.xcore.mappings;

import java.util.ListIterator;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;

public class XcoreMapper
{
	public XDataType getXDataType(EObject element) {
		return (XDataType) getXcoreElement(element);
	}
	public XClass getXClass(EObject element) {
		return (XClass) getXcoreElement(element);
	}
	public XOperation getXOperation(EObject element) {
		return (XOperation) getXcoreElement(element);
	}
	public XStructuralFeature getXFeature(EObject element) {
		return (XStructuralFeature) getXcoreElement(element);
	}
	public XPackageMapping getMapping(XPackage aPackage) {
		return lazyCreateMapping(aPackage, XPackageMapping.class);
	}
	
	public void unsetMapping(XPackage xPackage)
	{
		remove(xPackage.eAdapters(), XPackageMapping.class);
		for (XClassifier xClassifier : xPackage.getClassifiers())
		{
			if (xClassifier instanceof XClass)
			{
				XClass xClass = (XClass)xClassifier;
		    remove(xClass.eAdapters(), XClassMapping.class);
		    for (XMember xMember : xClass.getMembers())
		    {
		    	if (xMember instanceof XStructuralFeature)
		    	{
		    		XStructuralFeature xStructuralFeature = (XStructuralFeature)xMember;
		        remove(xStructuralFeature.eAdapters(), XFeatureMapping.class);
		    	}
		    	else if (xMember instanceof XOperation)
		    	{
		    		XOperation xOperation = (XOperation)xMember;
		        remove(xOperation.eAdapters(), XOperationMapping.class);
		    	}
		    }
			}
			else if (xClassifier instanceof XDataType)
			{
				XDataType xDataType = (XDataType)xClassifier;
		    remove(xDataType.eAdapters(), XDataTypeMapping.class);
			}
		}
	}
	
	private void remove(EList<Adapter> adapters, Class<?> type)
	{
		for (ListIterator<Adapter> i = adapters.listIterator(); i.hasNext(); )
		{
			Adapter adapter = i.next();
			if (adapter.isAdapterForType(type))
			{
				i.remove();
				break;
			}
		}
	}

	public XClassMapping getMapping(XClass clazz) {
		return lazyCreateMapping(clazz, XClassMapping.class);
	}
	public XFeatureMapping getMapping(XStructuralFeature feature) {
		return lazyCreateMapping(feature, XFeatureMapping.class);
	}
	public XOperationMapping getMapping(XOperation feature) {
		return lazyCreateMapping(feature, XOperationMapping.class);
	}
	public XDataTypeMapping getMapping(XDataType derivedElement) {
		return lazyCreateMapping(derivedElement, XDataTypeMapping.class);
	}
	
	public ToXcoreMapping getToXcoreMapping(EObject derivedElement) {
		return lazyCreateMapping(derivedElement, ToXcoreMapping.class);
	}
	
	public ENamedElement getEcore(XNamedElement namedElement) {
		if (namedElement instanceof XPackage) {
			return getMapping((XPackage)namedElement).getEPackage();
		} else if (namedElement instanceof XClass) {
			return getMapping((XClass)namedElement).getEClass();
		} else if (namedElement instanceof XDataType) {
			return getMapping((XDataType)namedElement).getEDataType();
		} else if (namedElement instanceof XStructuralFeature) {
			return getMapping((XStructuralFeature)namedElement).getEStructuralFeature();
		} else if (namedElement instanceof XOperation) {
			return getMapping((XOperation)namedElement).getEOperation();
		} 
		return null;
	}
	
	public GenBase getGen(XNamedElement namedElement) {
		if (namedElement instanceof XPackage) {
			return getMapping((XPackage)namedElement).getGenPackage();
		} else if (namedElement instanceof XClass) {
			return getMapping((XClass)namedElement).getGenClass();
		} else if (namedElement instanceof XDataType) {
			return getMapping((XDataType)namedElement).getGenDataType();
		} else if (namedElement instanceof XStructuralFeature) {
			return getMapping((XStructuralFeature)namedElement).getGenFeature();
		} else if (namedElement instanceof XOperation) {
			return getMapping((XOperation)namedElement).getGenOperation();
		} 
		return null;
	}
	
	@SuppressWarnings("unchecked")
  protected <T extends Adapter> T lazyCreateMapping(EObject object, Class<T> mapperType) 
  {
		if (object == null)
			throw new NullPointerException("object");
		if (mapperType == null)
			throw new NullPointerException("mapperType");
	  T adapter = (T) EcoreUtil.getAdapter(object.eAdapters(), mapperType);
	  if (adapter == null) {
	  	try
      {
	      adapter = mapperType.newInstance();
	      object.eAdapters().add(adapter);
      } catch (Exception e)
      {
	      throw new RuntimeException(e);
      }
	  	
	  }
		return adapter;
  }
	
	
	protected EObject getXcoreElement(EObject object) {
		ToXcoreMapping adapter = (ToXcoreMapping) EcoreUtil.getAdapter(object.eAdapters(), ToXcoreMapping.class);
		if (adapter != null)
			return adapter.getXcoreElement();
		return null;
	}
	
}
