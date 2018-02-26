package ioc.annotation;

import java.lang.annotation.*;

/**
 * Created by dela on 2/25/18.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Inject {

    public String name() default "";
}
