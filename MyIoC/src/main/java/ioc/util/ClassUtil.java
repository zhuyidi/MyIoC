package ioc.util;

/**
 * Created by dela on 2/26/18.
 */

// 负责类的加载
public class ClassUtil {
    public static ClassLoader getClassLoader() {
        return ClassLoader.getSystemClassLoader();
    }

    public static Class loadClass(String className) {
        try {
            return getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
