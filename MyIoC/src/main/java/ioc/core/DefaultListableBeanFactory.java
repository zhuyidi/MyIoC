package ioc.core;

import ioc.model.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dela on 3/1/18.
 */
public class DefaultListableBeanFactory implements BeanFactory {
    private static Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();
    private static Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();


    public Object getBean(String name) throws Exception {
        // 查看对象有没有被实例化过, 如果有则直接返回
        // 其实应该是都已经实例化过了, 早在BeanDefinition的载入和解析之后就应该实例化了(lazyinit模式)
        Object bean = beanMap.get(name);

        if (bean != null) {
            return bean;
        }

        // 如果这个Bean没有被实例化过, 那就调用createBean对它进行实例化
        bean = createBean(beanDefinitionMap.get(name));

        return null;
    }

    public Object createBean(BeanDefinition beanDefinition) {
        return null;
    }
}
