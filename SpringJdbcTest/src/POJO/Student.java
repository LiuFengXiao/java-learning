package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Student implements Serializable {
    private  int id;
    private  String name;

    public Partment getPartment() {
        return partment;
    }

    public void setPartment(Partment partment) {
        this.partment = partment;
    }

    private  Partment partment;
    private  SexEnum sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public Student(int id, String name, SexEnum sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
    public Student(){

    }
}
