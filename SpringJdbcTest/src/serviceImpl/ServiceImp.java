package serviceImpl;

import Mapper.Student3Mapper;
import POJO.SexEnum;
import POJO.Student;
import Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ServiceImp  implements StudentService {

    @Autowired
    Student3Mapper student3Mapper=null;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Student getStudent2(int id) {
        System.out.println("正在执行!!!");
       // insertStudent2(new Student(12,"nae", SexEnum.FEMALE));
        //insertStudent2(new Student(3,"nae", SexEnum.FEMALE));
        Student student =student3Mapper.getStudent2(id);
        System.out.println(student.getName());
        return student;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)

    public void insertStudent2(Student student) {
        student3Mapper.insertStudent2(student);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)

    public void updateStudent2(Student student) {
        student3Mapper.updateStudent2(student);
    }
}
