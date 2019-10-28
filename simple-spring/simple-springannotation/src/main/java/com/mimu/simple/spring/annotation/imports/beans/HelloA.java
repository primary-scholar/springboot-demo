package com.mimu.simple.spring.annotation.imports.beans;

import com.mimu.simple.spring.annotation.imports.api.Hello;

public class HelloA implements Hello {
    @Override
    public void sayHi() {
        System.out.println("Hi A");
    }
}
