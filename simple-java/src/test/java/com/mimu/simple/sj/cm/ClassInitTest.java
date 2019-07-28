package com.mimu.simple.sj.cm;

/**
 * author: mimu
 * date: 2019/7/28
 */
public class ClassInitTest {
    /**
     * 类初始化顺序
     * 结论1：父类的静态代码块
     *      ->子类的静态代码块
     *      ->初始化父类的属性值/父类的普通代码块(自上而下的顺序排列)
     *      ->父类的构造方法->初始化子类的属性值/子类的普通代码块(自上而下的顺序排列)
     *      ->子类的构造方法
     *
     * 结论2：类的静态属性只初始化一次
     * 结论3：非静态资源会随对象的创建而执行初始化。每创建一个对象，执行一次初始化。
     * @param args
     */
    public static void main(String[] args) {
        Singleton.getInstance();
        System.out.println("Singleton value1:" + Singleton.value1);
        System.out.println("Singleton value2:" + Singleton.value2);

        Singleton2.getInstance();
        System.out.println("Singleton2 value1:" + Singleton2.value1);
        System.out.println("Singleton2 value2:" + Singleton2.value3);
    }

    static class Singleton {
        static {
            System.out.println(Singleton.value1 + "\t" + Singleton.value2 + "\t" + Singleton.singleton);
        }

        /**
         * 静态代码块 按照顺序初始化，在执行new Singleton() 时，value1，value2 未进行初始化其值为0，0
         * 执行完 构造函数后其值为1，1，但被后续的静态变量初始化覆盖为5，3
         */
        private static Singleton singleton = new Singleton();
        public static int value1 = 5;
        public static int value2 = 3;

        private Singleton() {
            value1++;
            value2++;
        }

        public static Singleton getInstance() {
            return singleton;
        }
    }

    static class Singleton2 {
        static {
            System.out.println(Singleton2.value1 + "\t" + Singleton2.value3 + "\t" + Singleton2.singleton2);
        }
        /**
         * 静态代码块 按照顺序初始化，在执行new Singleton() 时，value1，value2 已进行初始化其值为5，3
         * 执行完 构造函数后其值为6，4
         */
        public static int value1 = 5;
        public static int value3 = 3;
        private static Singleton2 singleton2 = new Singleton2();

        private Singleton2() {
            value1++;
            value3++;
        }

        public static Singleton2 getInstance() {
            return singleton2;
        }
    }

}




