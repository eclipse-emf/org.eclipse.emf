package org.eclipse.emf.ecore.xcore.interpreter;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.interpreter.IEvaluationResult;
import org.eclipse.xtext.xbase.interpreter.impl.DefaultEvaluationContext;

import com.google.inject.Inject;

public class XcoreInvocationDelegate implements EOperation.Internal.InvocationDelegate
{
	@Inject
	private XcoreInterpreter interpreter;
	
	private XBlockExpression body;
	private EOperation eOperation;

	public Object dynamicInvoke(InternalEObject target, EList<?> arguments) throws InvocationTargetException
  {
		XExpression body = getBody();
		if (body == null)
			throw new IllegalStateException("coudn't find exeutable Xbase body");
		DefaultEvaluationContext context = new DefaultEvaluationContext();
		context.newValue(QualifiedName.create("this"), target);
		for (int i = 0; i< arguments.size(); i++) {
			Object arg = arguments.get(i);
			EParameter parameter = getEOperation().getEParameters().get(i);
			context.newValue(QualifiedName.create(parameter.getName()), arg);
		}
		IEvaluationResult result = interpreter.evaluate(body, context, CancelIndicator.NullImpl);
		if (result.getException() != null)
			throw new InvocationTargetException(result.getException());
	  return result.getResult();
  }
	
	public EOperation getEOperation()
  {
	  return eOperation;
  }
	
	protected XExpression getBody()
  {
		return body;
  }

	public void initialize(XBlockExpression body, EOperation operation)
  {
		this.body = body;
		this.eOperation = operation;
  }
	
}
