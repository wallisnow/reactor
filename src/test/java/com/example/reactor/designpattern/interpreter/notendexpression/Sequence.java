package com.example.reactor.designpattern.interpreter.notendexpression;

import com.example.reactor.designpattern.interpreter.Expression;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Sequence implements Expression {

    // 指令集序列
    private List<Expression> expressions;

    @Override
    public void interpret() {
        // 循环挨个解析每条指令
        expressions.forEach(Expression::interpret);
    }
}
