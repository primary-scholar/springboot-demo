package com.mimu.simple.java.generic;

import com.mimu.simple.java.generic.group.ChildClass;
import com.mimu.simple.java.generic.group.GrandChildClass;
import com.mimu.simple.java.generic.group.ParentClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * author: mimu
 * date: 2019/10/18
 */
public class GenericClazzTest {

    @Test
    public void get() {
        GenericClazz<Person> clazz = new GenericClazz<>();
        try {
            System.out.println(clazz.getB(Fruit.class));
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(clazz.getA(Person.class));
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        /**
         * 该方法 编译报错
         */
        /*try {
            System.out.println(clazz.getA(Fruit.class));
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }*/
    }

    @Test
    public void info() {
        List<ParentClass> parentClasses = new ArrayList<>();
        parentClasses.add(new ParentClass());
        parentClasses.add(new ChildClass());
        ParentClass parentClass = parentClasses.get(0);

        List<GrandChildClass> grandChildClasses = new ArrayList<>();
        grandChildClasses.add(new GrandChildClass());
        //grandChildClasses.add(new ParentClass());

        List<? extends ChildClass> classExtends = new ArrayList<>();
        //classExtends.add(new ParentClass());
        ParentClass parentClass1 = classExtends.get(0);

        List<? super ChildClass> classSuper = new ArrayList<>();
        //classSuper.add(new Object());
        //classSuper.add(new ParentClass());
        classSuper.add(new ChildClass());
        classSuper.add(new GrandChildClass());

        Object object = classSuper.get(0);

        //testExtends(parentClasses);
        testExtends(grandChildClasses);
        testExtends(classExtends);
        //testExtends(classSuper);

        testSuper(parentClasses);
        //testSuper(grandChildClasses);
        //testSuper(classExtends);
        testSuper(classSuper);
    }

    /**
     * 只读，不可写
     *
     * @param certainClass
     */
    public void testExtends(List<? extends ChildClass> certainClass) {
        for (ChildClass aClass : certainClass) {
            GrandChildClass childClass = (GrandChildClass) aClass;
        }
        //certainClass.add(new ChildClass());

    }

    /**
     * 可读可写
     *
     * @param notCertainClass
     */
    public void testSuper(List<? super ChildClass> notCertainClass) {
        for (Object certainClass : notCertainClass) {

        }
        //notCertainClass.add(new Object());
        //notCertainClass.add(new ParentClass());
        notCertainClass.add(new ChildClass());
        notCertainClass.add(new GrandChildClass());
    }

}