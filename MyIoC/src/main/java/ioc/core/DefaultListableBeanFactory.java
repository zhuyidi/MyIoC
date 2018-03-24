package ioc.core;

import com.sun.xml.internal.ws.util.StringUtils;
import ioc.model.BeanDefinition;
import ioc.model.ConstructorPO;
import ioc.util.BeanUtil;
import ioc.util.ClassUtil;
import ioc.util.InjectUtil;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dela on 3/1/18.
 */
public class DefaultListableBeanFactory implements BeanFactory {
    private static Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();
    private static Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private static final Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<String>());


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


    // 注册Bean
    protected void registerBean(String name, BeanDefinition bd){
        beanDefinitionMap.put(name,bd);
        beanNameSet.add(name);
    }

    // 如果当前Bean还没有被实例化过, 那就调用好createBean对它进行实例化
    // 将BeanDefinition结构转化成一个Object的对象(使用反射机制)
    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        String beanName = beanDefinition.getClassName();
        Class clz = ClassUtil.loadClass(beanName);
        if(clz == null) {
            throw new Exception("can not find bean by beanName");
        }
        List<ConstructorPO> constructorArgs = beanDefinition.getConstructorPOList();
        if(constructorArgs != null && !constructorArgs.isEmpty()){
            List<Object> objects = new ArrayList<Object>();
            for (ConstructorPO constructorArg : constructorArgs) {
                objects.add(getBean(constructorArg.getRef()));
            }
            return BeanUtil.createObject(clz,clz.getConstructor(),objects.toArray());
        }else {
            return BeanUtil.createObject(clz,null,null);
        }
    }

    // 填充Bean
    // 对该Bean的Field一一进行填充, 如果有依赖其它Bean的也一并完成依赖注入
    private void populatebean(Object bean) throws Exception {
        Field[] fields = bean.getClass().getSuperclass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                String beanName = field.getName();
                beanName = StringUtils.decapitalize(beanName);
                if (beanNameSet.contains(field.getName())) {
                    Object fieldBean = getBean(beanName);
                    if (fieldBean != null) {
                        InjectUtil.injectField(field,bean,fieldBean);
                    }
                }
            }
        }
    }
}
