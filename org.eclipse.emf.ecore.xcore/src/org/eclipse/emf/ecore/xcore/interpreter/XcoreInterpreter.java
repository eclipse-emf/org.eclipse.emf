package org.eclipse.emf.ecore.xcore.interpreter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.mappings.ToXcoreMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.xbase.interpreter.impl.EvaluationException;
import org.eclipse.xtext.xbase.interpreter.impl.XbaseInterpreter;

import com.google.inject.Inject;

public class XcoreInterpreter extends XbaseInterpreter
{
	
	@Inject
	private XcoreMapper mapper;
	
	@Override
	protected Object invokeOperation(JvmOperation operation, Object receiver, List<Object> argumentValues)
	{
		if (receiver instanceof EObject) {
			ToXcoreMapping mapping = mapper.getToXcoreMapping(operation);
			if (mapping != null) {
				XNamedElement element = mapping.getXcoreElement();
				if (element instanceof XOperation) {
					EOperation op = mapper.getMapping((XOperation)element).getEOperation();
					try
					{
						return ((EObject)receiver).eInvoke(op, new BasicEList<Object>(argumentValues));
					} catch (InvocationTargetException e)
					{
						throw new EvaluationException(e);
					}
				}
			}
		} 
	  return super.invokeOperation(operation, receiver, argumentValues);
	}
}
