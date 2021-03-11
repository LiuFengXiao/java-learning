package Controller;

import POJO.SexEnum;
import POJO.Student;
import Service.StudentService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.File;
import java.io.IOException;


@Controller("TransactionAnnantionTest")
public class TransactionAnnantionTest  {

    @Autowired
    StudentService  studentService = null;
    @RequestMapping("/SelectStudent")
    public String SelectStudent(){
        System.out.println("这是什么东西");


       // Student st = studentService.getStudent2(4);

        return "test";
    }
    @RequestMapping("/test")
    public String  test(){
        System.out.println("wok");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("page");
        return  "SelectStudent";
    }
    @RequestMapping("/getStudentinfo")
    public ModelAndView getinfo(){


        System.out.println("info");
         studentService.insertStudent2(new Student(3,"jj", SexEnum.MALE));
        ModelAndView mv = new ModelAndView();

        mv.setView(new MappingJackson2JsonView());
        return mv;
    }
    @RequestMapping("/upload")
    public ModelAndView upload(MultipartFile file, @RequestParam("text") String text){
        String filename = file.getOriginalFilename();
        ModelAndView mv = new ModelAndView();
        System.out.println(text);
        System.out.println(filename);
        File obj = new File(filename);
        try {
            file.transferTo(obj);
            mv.addObject("msg","上传成功!");
        } catch (IOException e) {
            e.printStackTrace();
            mv.addObject("msg","上传失败！");
        }
        mv.setView(new MappingJackson2JsonView());
    return  mv;
    }
    @RequestMapping("/msg")
    public String getmsg(){
        return "msg.jsp";
    }
    @ExceptionHandler(Exception.class)
    public String handlerException(){
        return "exception.jsp";
    }


}
