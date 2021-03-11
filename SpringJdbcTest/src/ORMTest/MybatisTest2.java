package ORMTest;

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

public class MybatisTest2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        Student2Mapper mapper = (Student2Mapper) context.getBean("student2Mapper");
        Student2 student2 = (Student2) context.getBean("student2");
        student2.setName("ailiu");
        student2.setId(10);
        student2.setSex(SexEnum.FEMALE);
      //  mapper.insertStudent2(student2);

        Student2 findStudent =  mapper.getStudent2(4);
        System.out.println(findStudent.getSex().getName());

        student2.setId(1);
        mapper.updateStudent2(student2);
        System.out.println("执行完毕！！");

    }
}
