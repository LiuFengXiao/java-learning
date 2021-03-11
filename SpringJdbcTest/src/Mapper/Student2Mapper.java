package Mapper;

import POJO.Student;
import POJO.Student2;
import org.springframework.stereotype.Repository;

@Repository
public interface Student2Mapper {
    public Student2 getStudent2(int id);
    public void insertStudent2(Student2 student);
    public void updateStudent2(Student2 student);
}
