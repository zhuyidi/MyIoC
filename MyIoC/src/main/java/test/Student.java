package test;

/**
 * Created by dela on 2/25/18.
 */
public class Student {
    private long id;

    public Student() { }

    public Student(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                '}';
    }
}
