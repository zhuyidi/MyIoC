package ioc.model;

/**
 * Created by dela on 3/1/18.
 */
public class PropertyPO {
    private String name;
    private String value;
    private String typeClassName;

    public PropertyPO() { }

    public PropertyPO(String name, String value, String typeClassName) {
        this.name = name;
        this.value = value;
        this.typeClassName = typeClassName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTypeClassName() {
        return typeClassName;
    }

    public void setTypeClassName(String typeClassName) {
        this.typeClassName = typeClassName;
    }
}
