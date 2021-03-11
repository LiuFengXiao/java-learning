package Mapper;

import POJO.Student;
import org.springframework.stereotype.Repository;

@Repository  //标记mapper接口
public interface Student3Mapper {
    public Student getStudent2(int id);
    public void insertStudent2(Student student);
    public void updateStudent2(Student student);
}
