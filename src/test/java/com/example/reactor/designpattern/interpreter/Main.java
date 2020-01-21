package com.example.reactor.designpattern.interpreter;

import com.example.reactor.designpattern.interpreter.endexpression.Delay;
import com.example.reactor.designpattern.interpreter.endexpression.Move;
import com.example.reactor.designpattern.interpreter.endexpression.RightDown;
import com.example.reactor.designpattern.interpreter.notendexpression.LeftClick;
import com.example.reactor.designpattern.interpreter.notendexpression.Repetition;
import com.example.reactor.designpattern.interpreter.notendexpression.Sequence;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
         * BEGIN             // 脚本开始
         * MOVE 500,600;     // 鼠标移动到坐标(500, 600)
         *  BEGIN LOOP 5     // 开始循环5次
         *      LEFT_CLICK;  // 循环体内单击左键
         *      DELAY 1;     // 每次延时1秒
         *  END;             // 循环体结束
         * RIGHT_DOWN;       // 按下右键
         * DELAY 5;       //   延时5s
         * END;              // 脚本结束
         */

        List<Expression> expressionSet = new ArrayList<>();

        expressionSet.add(new Move(200, 100));
        expressionSet.add(
                new Repetition(5,
                        new Sequence(
                                List.of(new LeftClick(),
                                        new Delay(1))
                        )
                ));
        expressionSet.add(new RightDown());
        expressionSet.add(new Delay(5));

        new Sequence(expressionSet).interpret();
    }
}
