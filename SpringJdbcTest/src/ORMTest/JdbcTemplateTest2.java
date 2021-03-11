package ORMTest;


import POJO.Student;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplateTest2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        JdbcTemplate  jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
        getCon(jdbcTemplate,context);
        System.out.println("已经进入");


    }
    private void getCon(JdbcTemplate  jdbcTemplate,ApplicationContext context){
        String sql = "select * from student where id =1;";
         Student student = jdbcTemplate.queryForObject(sql,new RowMapper<POJO.Student>(){

            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student1 = (Student) context.getBean("student");
                student1.setId((int) resultSet.getLong("id"));
                student1.setName(resultSet.getNString("sex"));
                return student1;
            }
        });
        System.out.println(student.getId());

    }
}
