package org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * use www.xpect-tests.org insted. In Xpect, thre is no direct counterpart for
 * this annotation since each kind of injected parameter has its own annotation.
 * 
 * This class will be removed in the next release after 2.4.2
 * 
 * @author Moritz Eysholdt - Initial contribution and API
 */
@Deprecated
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectParameter {
	String value() default "";
}
