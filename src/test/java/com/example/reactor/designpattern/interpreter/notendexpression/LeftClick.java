package com.example.reactor.designpattern.interpreter.notendexpression;

import com.example.reactor.designpattern.interpreter.Expression;
import com.example.reactor.designpattern.interpreter.endexpression.LeftDown;
import com.example.reactor.designpattern.interpreter.endexpression.LeftUp;


public class LeftClick implements Expression {

    private Expression leftDown;
    private Expression leftUp;

    public LeftClick() {
        this.leftDown = new LeftDown();
        this.leftUp = new LeftUp();
    }

    @Override
    public void interpret() {
        leftDown.interpret();
        leftUp.interpret();
    }
}
