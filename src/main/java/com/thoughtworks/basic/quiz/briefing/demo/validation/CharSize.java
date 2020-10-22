package com.thoughtworks.basic.quiz.briefing.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

// TODO GTB-3: + 自定义了校验注解，非常不错
@Documented
@Constraint(validatedBy = CharSizeValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CharSize {

    String message() default "{javax.validation.constraints.Size.message}";

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    /**
     * @return size the element must be higher or equal to
     */
    int min() default 0;

    /**
     * @return size the element must be lower or equal to
     */
    int max() default Integer.MAX_VALUE;
}
