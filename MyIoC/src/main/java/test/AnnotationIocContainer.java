package test;

import ioc.annotation.Bean;
import ioc.annotation.Inject;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dela on 2/25/18.
 */
public class AnnotationIocContainer {

    protected Map<String, Object> beanInstanceMap = new HashMap<String, Object>();

    public void bind(String packageName) {

        initializeBean(packageName);

        inject();
    }

    /**
     * 实例化Bean
     */
    private void initializeBean(String packageName) {

        try {
            List<Class<?>> list = new ClassDetector(packageName).detect(Bean.class);;

            for (Class<?> clazz : list) {

                Bean beanAnnotation = clazz.getAnnotation(Bean.class);

                System.out.println("class="+clazz.getName()+",bean_id="+beanAnnotation.id());

                Object bean = clazz.newInstance();  //如需通过构造函数注入,需要在此处理

                beanInstanceMap.put(beanAnnotation.id(), bean);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private void inject(){

        for(String beanName : beanInstanceMap.keySet()){
            Object bean = beanInstanceMap.get(beanName);
            if(bean!=null){
                processSetterAnnotation(bean);
                processFieldAnnotation(bean);
            }
        }

    }

    /**
     * 处理field上的注解
     * @param bean
     */
    protected void processFieldAnnotation(Object bean){

        try {
            Field[] fields = bean.getClass().getDeclaredFields();
            for(Field field : fields){
                if(field!=null && field.isAnnotationPresent(Inject.class)){
                    Inject resource = field.getAnnotation(Inject.class);
                    String name = resource.name();
                    Object injectBean = null;
                    if(name!=null&&!"".equals(name)){
                        injectBean = beanInstanceMap.get(name);
                    }else{
                        for(String key : beanInstanceMap.keySet()){
                            //判断当前属性所属的类型是否在配置文件中存在
                            if(field.getType().isAssignableFrom(beanInstanceMap.get(key).getClass())){
                                //获取类型匹配的实例对象
                                injectBean = beanInstanceMap.get(key);
                                break;
                            }
                        }
                    }

                    if(injectBean!=null){
                        //允许访问private字段
                        field.setAccessible(true);
                        //把引用对象注入属性
                        field.set(bean, injectBean);
                    }else{
                        System.out.println("field inject failed,name="+name);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理set方法上的注解
     * @param bean
     */
    protected void processSetterAnnotation(Object bean){

        try {
            //获取bean的属性描述器
            PropertyDescriptor[] ps =
                    Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
            for(PropertyDescriptor pd : ps){

                Method setter = pd.getWriteMethod();

                if(setter!=null && setter.isAnnotationPresent(Inject.class)){
                    //获取当前注解，并判断name属性是否为空
                    Inject resource = setter.getAnnotation(Inject.class);
                    String name = resource.name();
                    Object injectBean = null;
                    if(name!=null&&!"".equals(name)){
                        injectBean = beanInstanceMap.get(name);
                    }else{ //如果当前注解没有指定name属性,则根据类型进行匹配
                        for(String key : beanInstanceMap.keySet()){
                            if(pd.getPropertyType().isAssignableFrom(beanInstanceMap.get(key).getClass())){
                                injectBean = beanInstanceMap.get(key);
                                break;
                            }
                        }
                    }

                    if(injectBean!=null){
                        //允许访问private方法
                        setter.setAccessible(true);
                        //把引用对象注入属性
                        setter.invoke(bean, injectBean);
                    }else{
                        System.out.println("setter inject failed,name="+name);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}