package org.eclipse.emf.ecore.xcore.typing;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.typing.XbaseTypeProvider;

import com.google.inject.Inject;

public class XcoreTypeProvider extends XbaseTypeProvider
{
	
	@Inject
	private XcoreMapper mapper;
	
	protected JvmTypeReference _expectedType(XOperation expr, EReference reference, int index, boolean rawType)
	{
	  if (reference == XcorePackage.Literals.XOPERATION__BODY) {
	  	JvmOperation operation = mapper.getMapping(expr).getJvmOperation();
	  	return operation.getReturnType();
	  }
	  return null;
	}
	
}
