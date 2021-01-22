package aqa.framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for page declaration
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PageInfo {

    /**
     * Page game getter
     *
     * @return Page name
     */
    String pageName() default "";

    /**
     * Page xpath anchor getter
     *
     * @return Xpath anchor
     */
    String xpath() default "";

    /**
     * Page css selector getter
     *
     * @return css selector
     */
    String css() default "";

    /**
     * Page id anchor getter
     *
     * @return id anchor
     */
    String id() default "";

}


