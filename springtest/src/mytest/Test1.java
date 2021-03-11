package mytest;
import POJO.Person;
import com.sun.org.apache.bcel.internal.util.ClassPath;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Test1 {


    public static void main(String[] args){
        ClassPathResource resource = new ClassPathResource("beans.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);
//        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource,factory);
        Person  person=  factory.getBean("person",Person.class);
       System.out.println(person.getName());

    }
}
