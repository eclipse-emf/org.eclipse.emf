package org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.Strings;

import com.google.common.collect.Multimap;
import com.google.inject.ImplementedBy;

/**
 * use org.xpect.parameter.IParameterProvider from www.xpect-tests.org instead.
 * 
 * This class will be removed in the next release after 2.4.2
 * 
 * @author Moritz Eysholdt - Initial contribution and API
 */
@Deprecated
@ImplementedBy(XpectParameterProvider.class)
public interface IParameterProvider {

	public interface IExpectation {
		public class Util {
			public static String replace(XtextResource res, IExpectation exp,
					String value) {
				String indented;
				if (!Strings.isEmpty(exp.getIndentation()))
					indented = exp.getIndentation()
							+ value.replace("\n", "\n" + exp.getIndentation());
				else
					indented = value;
				String document = res.getParseResult().getRootNode().getText();
				String before = document.substring(0, exp.getOffset());
				String after = document.substring(
						exp.getOffset() + exp.getLength(), document.length());
				return before + indented + after;
			}
		}

		String getExpectation();

		String getIndentation();

		int getLength();

		int getOffset();
	}

	public interface IParameterAcceptor {
		void acceptImportURI(URI uri);

		void acceptTest(String title, String method,
				Multimap<String, Object> params, IExpectation expectation,
				boolean ignore);

		// void acceptTestClass(Class<?> clazz);
	}

	void collectParameters(Class<?> testClass, XtextResource resource,
			IParameterAcceptor acceptor);

}
