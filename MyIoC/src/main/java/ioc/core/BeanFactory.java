package ioc.core;

/**
 * Created by dela on 2/25/18.
 */
public interface BeanFactory {
    Object getBean(String name) throws Exception;
}
