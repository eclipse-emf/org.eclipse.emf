package org.eclipse.emf.ecore.xcore.mappings;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;

public class XcoreMapper
{
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
