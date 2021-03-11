package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Student2 implements Serializable {
    private  int id;
    private  String name;

    public String getParmenter() {
        return parmenter;
    }

    public void setParmenter(String parmenter) {
        this.parmenter = parmenter;
    }

    private  String parmenter;
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

    public Student2(int id, String name, SexEnum sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
    public Student2(){

    }
}
