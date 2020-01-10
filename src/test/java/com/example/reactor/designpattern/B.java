package com.example.reactor.designpattern;

public class B implements I{
    @Override
    public void f() {
        System.out.println("B: doing f()");
    }

    @Override
    public void g() {
        System.out.println("B: doing g()");
    }
}
