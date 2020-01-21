package com.example.reactor.designpattern.interpreter.endexpression;

import com.example.reactor.designpattern.interpreter.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
public class Move implements Expression {

    private int x;
    private int y;

    @Override
    public void interpret() {
        log.info("moving to x={}, y={}", x, y);
    }
}
