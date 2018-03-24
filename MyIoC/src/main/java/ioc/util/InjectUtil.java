package ioc.util;

import java.lang.reflect.Field;

/**
 * Created by dela on 3/1/18.
 */

// 完成依赖注入
public class InjectUtil {
    public static void injectField(Field field,Object obj,Object value) throws IllegalAccessException {
        if(field != null) {
            field.setAccessible(true);
            field.set(obj, value);
        }
    }
}
