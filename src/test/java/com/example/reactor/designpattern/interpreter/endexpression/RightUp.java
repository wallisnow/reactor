package com.example.reactor.designpattern.interpreter.endexpression;

import com.example.reactor.designpattern.interpreter.Expression;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RightUp implements Expression {

    @Override
    public void interpret() {
        log.info("loosen Right");
    }
}
