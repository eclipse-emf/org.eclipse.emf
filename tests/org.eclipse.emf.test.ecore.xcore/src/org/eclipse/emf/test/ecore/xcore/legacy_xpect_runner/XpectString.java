package org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.IParameterProvider.IExpectation;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.TestExpectationValidator.ITestExpectationValidator;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.TestExpectationValidator.TestResult;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.XpectString.StringResultValidator;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.internal.FormattingMigrator;
import org.junit.Assert;
import org.junit.ComparisonFailure;

/**
 * use org.xpect.runner.Xpect from www.xpect-tests.org instead. In Xpect, test
 * methods don't have return values anymore. Instead, the test expectation is
 * passed in as method parameter. To handle a method with a String expectation
 * you can use a method declaration such as
 * 
 * <code>@Xpect public void linkedName(@StringExpectation IStringExpectation expectation) { }</code>
 * 
 * This class will be removed in the next release after 2.4.2
 * 
 * @author Moritz Eysholdt - Initial contribution and API
 */
@Deprecated
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@SuppressWarnings("restriction")
@TestExpectationValidator(validator = StringResultValidator.class)
public @interface XpectString {

	public class StringResultValidator implements
			ITestExpectationValidator<String> {

		protected XpectString config;

		public StringResultValidator(XpectString config) {
			this.config = config;
		}

		public void validate(XtextResource resource, IExpectation expectation,
				@TestResult String actual) {
			Assert.assertNotNull(resource);
			Assert.assertNotNull(expectation);
			Assert.assertNotNull(expectation.getExpectation());
			Assert.assertNotNull(actual);
			String exp;
			if (!config.whitespaceSensitive()) {
				FormattingMigrator migrator = new FormattingMigrator();
				exp = migrator.migrate(actual, expectation.getExpectation());
			} else
				exp = expectation.getExpectation();

			if ((config.caseSensitive() && !exp.equals(actual))
					|| (!config.caseSensitive() && !exp
							.equalsIgnoreCase(actual))) {
				String expDoc = IExpectation.Util.replace(resource,
						expectation, exp);
				String actDoc = IExpectation.Util.replace(resource,
						expectation, actual);
				throw new ComparisonFailure("", expDoc, actDoc);
			}
		}
	}

	boolean caseSensitive() default true;

	boolean whitespaceSensitive() default false;
}
