package ioc.util;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * Created by dela on 3/1/18.
 */

// 负责Bean的实例化
// 使用cglib动态代理生成object
// 创建一个代理, 将clazz作为要产生的代理的父类(实质上没有要代理的类),
// 只不过cglib在动态代理的时候是由cglib包来创建要产生的代理的父类的(也就是createObject()方法中的clazz)
public class BeanUtil {
    public static <T> T createObject(Class<?> clazz, Constructor constructor, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(NoOp.INSTANCE);

        if (constructor == null) {
            return (T) enhancer.create();
        } else {
            return (T) enhancer.create(constructor.getParameterTypes(), args);
        }
    }
}
