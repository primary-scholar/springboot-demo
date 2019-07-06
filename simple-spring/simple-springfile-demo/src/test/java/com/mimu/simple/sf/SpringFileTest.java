package com.mimu.simple.sf;

import com.mimu.simple.sf.controller.inner.InnerController;
import com.mimu.simple.sf.model.Human;
import com.mimu.simple.sf.model.People;
import com.mimu.simple.sf.model.Student;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringFileTest {

    @Test
    public void info(){
        ApplicationContext context = new ClassPathXmlApplicationContext("aop.xml");
        InnerController controller = context.getBean("innerController", InnerController.class);
        controller.message();
    }

    @Test
    public void info1(){
        People people = new People();
        people.setName("people");
        Student student = new Student();
        student.setId(123);
        People.InnerClass innerClass = new People.InnerClass();
        people.setStudent(student);
        //innerClass.setId(1);
        people.setInnerClass(innerClass);
        Human human = new Human();
        Human.InnerClass innerClass1 = new Human.InnerClass();
        BeanUtils.copyProperties(people,human);
        BeanUtils.copyProperties(innerClass,innerClass1);
        human.setInnerClass(innerClass1);
        System.out.println(human.getStudent());
    }
}
