package com.example.reactor.designpattern.interpreter.notendexpression;

import com.example.reactor.designpattern.interpreter.Expression;
import com.example.reactor.designpattern.interpreter.endexpression.LeftDown;
import com.example.reactor.designpattern.interpreter.endexpression.LeftUp;
import com.example.reactor.designpattern.interpreter.endexpression.RightDown;
import com.example.reactor.designpattern.interpreter.endexpression.RightUp;


public class RightClick implements Expression {

    private Expression rightDown;
    private Expression rightUp;

    public RightClick() {
        this.rightDown = new RightDown();
        this.rightUp = new RightUp();
    }

    @Override
    public void interpret() {
        rightDown.interpret();
        rightUp.interpret();
    }
}
