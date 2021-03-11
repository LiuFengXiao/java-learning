package Mapper;

import POJO.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherMapper {
    public Teacher getTeacher(int id);
    public void insertTeacher(Teacher teacher);
    public void updateTeacher(Teacher teacher);
}
