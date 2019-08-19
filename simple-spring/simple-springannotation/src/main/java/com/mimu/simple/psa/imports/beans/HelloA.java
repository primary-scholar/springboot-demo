package com.mimu.simple.psa.imports.beans;

import com.mimu.simple.psa.imports.api.Hello;

public class HelloA implements Hello {
    @Override
    public void sayHi() {
        System.out.println("Hi A");
    }
}
