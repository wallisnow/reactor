package com.example.reactor.designpattern;

public class Main {
    public static void main(String[] args) {
        C c = new C();
        c.f();      // output: A: doing f()
        c.g();      // output: A: doing g()
        c.toB();    // 更换委托对象
        c.f();      // output: B: doing f()
        c.g();      // output: B: doing g()
    }
}
