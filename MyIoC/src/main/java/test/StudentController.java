package test;

import ioc.annotation.Bean;
import ioc.annotation.Inject;

/**
 * Created by dela on 2/25/18.
 */
@Bean(id="studentController")
public class StudentController {

    @Inject
//  @Inject(name="studentService")
    private StudentService studentService;

    public String find(long id){
        Student stu = studentService.find(id);
        System.out.println("stu:"+stu);
        return "find";
    }

}
