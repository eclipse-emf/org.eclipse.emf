package org.eclipse.emf.ecore.xcore.tests.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

public class LabelledParameterized extends Suite {
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public static @interface LabelledParameters {
	}

	private class TestClassRunnerForParameters extends BlockJUnit4ClassRunner {
		private final int parameterSetNumber;

		private final List<Object[]> parameters;

		TestClassRunnerForParameters(Class<?> type,
				List<Object[]> parameterList, int i) throws InitializationError {
			super(type);
			parameters = parameterList;
			parameterSetNumber = i;
		}

		@Override
		public Object createTest() throws Exception {
			return getTestClass().getOnlyConstructor().newInstance(
					computeParams());
		}

		protected Object[] computeParams() throws Exception {
			try {
				return parameters.get(parameterSetNumber);
			} catch (ClassCastException e) {
				throw new Exception(String.format(
						"%s.%s() must return a Collection of arrays.",
						getTestClass().getName(),
						getParametersMethod(getTestClass()).getName()));
			}
		}

		@Override
		protected String getName() {
			return String.format("%s", parameters.get(parameterSetNumber)[0]);
		}

		@Override
		protected String testName(final FrameworkMethod method) {
			return String.format("%s -> %s",
					parameters.get(parameterSetNumber)[0], method.getName());
		}

		@Override
		protected void validateZeroArgConstructor(List<Throwable> errors) {
			// super.validateZeroArgConstructor(errors);
		}

		@Override
		protected Statement classBlock(RunNotifier notifier) {
			return childrenInvoker(notifier);
		}
	}

	protected final ArrayList<Runner> runners = new ArrayList<Runner>();

	/**
	 * Only called reflectively. Do not use programmatically.
	 */
	public LabelledParameterized(Class<?> klass) throws Throwable {
		super(klass, Collections.<Runner> emptyList());
		List<Object[]> parametersList = getParametersList(getTestClass());
		for (int i = 0; i < parametersList.size(); i++)
			runners.add(new TestClassRunnerForParameters(getTestClass()
					.getJavaClass(), parametersList, i));
	}

	@Override
	protected List<Runner> getChildren() {
		return runners;
	}

	@SuppressWarnings("unchecked")
	protected List<Object[]> getParametersList(TestClass klass)
			throws Throwable {
		return (List<Object[]>) getParametersMethod(klass).invokeExplosively(
				null);
	}

	protected FrameworkMethod getParametersMethod(TestClass testClass)
			throws Exception {
		List<FrameworkMethod> methods = testClass
				.getAnnotatedMethods(LabelledParameters.class);
		for (FrameworkMethod each : methods) {
			int modifiers = each.getMethod().getModifiers();
			if (Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers))
				return each;
		}

		throw new Exception("No public static parameters method on class "
				+ testClass.getName());
	}

}