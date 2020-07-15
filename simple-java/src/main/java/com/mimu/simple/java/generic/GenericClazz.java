package com.mimu.simple.java.generic;


/**
 * author: mimu
 * date: 2019/10/18
 */

/**
 * Tips
 * <p>
 * 类上使用 <T> 代表该类是一个 泛型类
 *
 * @param <T>
 */
public class GenericClazz<T> {

    /**
     * 该方法是一个 泛型方法 该方法中的 <T> 的类型 和泛型类中的 <T> 是毫无关系的 类型(可相同，可不同)
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> T getB(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    /**
     * 该方法 不是 泛型方法 此处的 T 和泛型类中的 T 必须相同
     *
     * @param tClass
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public T getA(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        return tClass.newInstance();
    }
}
