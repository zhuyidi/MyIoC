package ioc.model;

import java.util.List;

/**
 * Created by dela on 2/25/18.
 */
public class BeanDefinition {
    private String beanName;
    private String className;
    private String interfaceName;
    private List<ConstructorPO> constructorPOList;
    private List<PropertyPO> propertyPOList;

    public BeanDefinition() {

    }

    public BeanDefinition(String beanName, String className, String interfaceName,
            List<ConstructorPO> constructorPOList, List<PropertyPO> propertyPOList) {
        this.beanName = beanName;
        this.className = className;
        this.interfaceName = interfaceName;
        this.constructorPOList = constructorPOList;
        this.propertyPOList = propertyPOList;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<ConstructorPO> getConstructorPOList() {
        return constructorPOList;
    }

    public void setConstructorPOList(List<ConstructorPO> constructorPOList) {
        this.constructorPOList = constructorPOList;
    }

    public List<PropertyPO> getPropertyPOList() {
        return propertyPOList;
    }

    public void setPropertyPOList(List<PropertyPO> propertyPOList) {
        this.propertyPOList = propertyPOList;
    }
}
