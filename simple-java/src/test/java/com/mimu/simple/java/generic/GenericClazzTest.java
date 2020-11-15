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
        List<ParentClass> parentClassList = new ArrayList<>();
        List<GrandChildClass> grandChildClassList = new ArrayList<>();
        /**
         * 普通容器中，也只可存放 本身及其子类
         */
        List<ChildClass> childClassList = new ArrayList<>();
        //childClassList.add(new Object());
        //childClassList.add(new ParentClass());
        childClassList.add(new ChildClass());
        childClassList.add(new GrandChildClass());

        /**
         * extends 在容器中使用时，只可读不可写
         * 读： 默认返回 其本身(extends 后的 对象) ,可进行强制类型转换(不安全)
         * 写：不可写
         *
         * 在 本例中 classExtends 可表示
         * 1.List<ChildClass></>
         * 2.List<GrandChildClass></>
         * 读：从容器中读取数据时，
         * 唯一可保证的是 获取的数据类型是 ChildClass(此时类型确定) 或其子类(此时类型不确定)
         * 写：(不可写)
         * 不可存放:ChildClass 因为可能是 List<GrandChildClass></>
         * 不可存放:GrandChildClass 因为可能是 List<GrandChildClass 的兄弟类型></>
         */
        List<? extends ChildClass> classExtends = new ArrayList<>();
        //classExtends.add(new ChildClass());
        //classExtends.add(new GrandChildClass());
        ChildClass childClass = classExtends.get(0);

        /**
         * super 在容器中使用时，可读可写
         * 读：默认返回 object ,可进行强制类型转换(不安全)
         * 写：只能存放 其本身(super 后的对象)或 其子类
         *
         * 在本例中 classSuper 可表示
         * 1.List<Object></>
         * 2.List<ParentClass></>
         * 3.List<ChildClass></>
         * 读：从容器中读取数据时，
         * 唯一可保证的是 获取的数据类型是 Object(此时类型确定) 或其子类(此时类型不确定)
         * 写：
         * 只可存放 其本身 及其子类
         */
        List<? super ChildClass> classSuper = new ArrayList<>();
        //classSuper.add(new Object());
        //classSuper.add(new ParentClass());
        classSuper.add(new ChildClass());
        classSuper.add(new GrandChildClass());

        Object object = classSuper.get(0);

        //testExtends(parentClassList);
        testExtends(childClassList);
        testExtends(grandChildClassList);
        testExtends(classExtends);

        testSuper(parentClassList);
        testSuper(childClassList);
        //testSuper(grandChildClassList);
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