package org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.IParameterProvider.IExpectation;
import org.eclipse.xtext.resource.XtextResource;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class will be removed in the next release after 2.4.2
 * 
 * @author Moritz Eysholdt - Initial contribution and API
 */
@Deprecated
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface TestExpectationValidator {

	public interface ITestExpectationValidator<RESULT> {

		void validate(XtextResource resource, IExpectation expectation,
				RESULT actual);
	}

	public class NullTestResultValidator implements
			ITestExpectationValidator<Void> {
		public NullTestResultValidator(Test config) {
		}

		public NullTestResultValidator(Xpect config) {
		}

		public void validate(XtextResource resource, IExpectation expectation,
				@TestResult Void actual) {
			if (expectation != null && expectation.getExpectation() != null
					&& expectation.getExpectation().length() > 0)
				Assert.fail("This test should not have an expectation. Expectation: '"
						+ expectation + "'.");
		}
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.PARAMETER)
	public @interface TestResult {
	}

	Class<? extends ITestExpectationValidator<?>> validator();
}