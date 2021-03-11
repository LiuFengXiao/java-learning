package ORMTest;


import POJO.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JdbcTemplateTest extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        w
        System.out.println(getServletConfig().getInitParameter("gege"));
        System.out.println(getServletConfig().getInitParameter("jdbc"));
        System.out.println("jjjjj");
    }

    public  static void getCon(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Driver driver = DriverManager.getDriver("jdbc:mysql://localhost:3306/forlearn?user=root&passoword=123456&useSSL=false");
            String username = "root";
            String passsword = "123456";
            String url ="jdbc:mysql://localhost:3306/forlearn?useSSL=false";
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource(driver,url,username,passsword);
            JdbcTemplate temp =new JdbcTemplate(dataSource);
            String sql = "select * from Student where id=1";
            Student student = temp.queryForObject(sql,new RowMapper<Student>(){

                @Override
                public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                    Student student1= new Student();
                    student1.setId((int) resultSet.getLong("id"));
                    student1.setName(resultSet.getNString("sex"));
                    return student1;
                }
            });
            System.out.println(student.getId());



      }catch (Exception e){
            e.printStackTrace();
        }
       
       
    }
    
}
