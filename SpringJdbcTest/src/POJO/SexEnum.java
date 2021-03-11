package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public enum SexEnum implements Serializable {
    MALE(1,"男"),
    FEMALE(0,"女");

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
     SexEnum(){

    }
     SexEnum(int id,String name){
        this.id = id;
        this.name= name;
    }
    public static SexEnum getSexEnumById(int id){
        for(SexEnum sex:SexEnum.values()){
            if(id ==sex.getId()){
                return  sex;
            }
        }
        return  null;
    }

    private int id;
    private String name;


}
