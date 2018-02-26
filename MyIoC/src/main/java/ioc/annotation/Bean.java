package ioc.annotation;

import java.lang.annotation.*;

/**
 * Created by dela on 2/25/18.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Bean { // 类似spring配置文件中的bean
    String id();
}
