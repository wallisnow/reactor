package com.example.reactor.designpattern.interpreter.notendexpression;

import com.example.reactor.designpattern.interpreter.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Repetition implements Expression {

    private int loopCount;// 循环次数
    private Expression expression;// 循环体表达式

    @Override
    public void interpret() {
        while (loopCount > 0) {
            expression.interpret();
            loopCount--;
        }
    }
}
