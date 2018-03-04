package ioc.model;

/**
 * Created by dela on 3/1/18.
 */
public class ConstructorPO {
    private int index;
    private String ref;
    private String name;

    public ConstructorPO() { }

    public ConstructorPO(int index, String ref, String name) {
        this.index = index;
        this.ref = ref;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
