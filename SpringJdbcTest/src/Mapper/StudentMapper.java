package Mapper;

import POJO.Student;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface StudentMapper {
    public Student getStudent(int id);
    public void insertStudent(Student student);
    public void updateStudent(Student student);
}
