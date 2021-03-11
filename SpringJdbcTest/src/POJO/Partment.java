package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Partment implements Serializable {
    public Partment(){

    }
    public Partment(int id, Teacher teacher, String name){
        this.id=id;
        this.name=name;
        this.teacher=teacher;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacherId(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private Teacher teacher;
    private String name;
}
