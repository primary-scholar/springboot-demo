package com.mimu.simple.spring.annotation.imports.beans;

import com.mimu.simple.spring.annotation.imports.api.Hello;

public class HelloB implements Hello {
    @Override
    public void sayHi() {
        System.out.println("Hi B");
    }
}
