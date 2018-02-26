package test;

import ioc.annotation.Bean;
import ioc.annotation.Inject;

/**
 * Created by dela on 2/25/18.
 */
@Bean(id="studentService")
public class StudentServiceImpl implements StudentService {

    @Inject
    private StudentDao studentDao;

    public Student find(long id) {

        return studentDao.find(id);
    }

}