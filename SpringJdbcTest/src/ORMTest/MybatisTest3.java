package ORMTest;

import Mapper.PartmentMapper;
import Mapper.Student2Mapper;
import Mapper.StudentMapper;
import POJO.SexEnum;
import POJO.Student;
import POJO.Student2;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MybatisTest3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        StudentMapper mapper = (StudentMapper) context.getBean("studentMapper");
        Student student = (Student) context.getBean("student");
        student.setName("ailiu");
        student.setId(10);
        student.setSex(SexEnum.FEMALE);


        Student findStudent =  mapper.getStudent(4);
        StudentMapper mapper2 = (StudentMapper) context.getBean("studentMapper");

        Student findStudent2 =  mapper2.getStudent(4);
//        System.out.println(findStudent.getName());
//        System.out.println(findStudent.getPartment().getName());
        System.out.println(findStudent.hashCode());
        System.out.println(findStudent2.hashCode());




//        student.setId(1);
//        mapper.updateStudent2(student2);
        System.out.println("执行完毕！！");

    }
}
