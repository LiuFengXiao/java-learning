package ORMTest;

import Mapper.StudentMapper;
import POJO.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MybatisTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        StudentMapper mapper = (StudentMapper) context.getBean("studentMapper");
//        Student student = (Student) context.getBean("student");
//        student.setName("ailiu");
//        student.setId(10);
//        student.setSex(1);
//        Student findStudent =  mapper.getStudent(4);
//        System.out.println(findStudent.getName());
//
//    //    mapper.insertStudent(student);
//        student.setId(1);
//
//        mapper.updateStudent(student);
//        System.out.println("执行完毕！！");

    }
}
