package com.example.reactor.designpattern.interpreter.endexpression;

import com.example.reactor.designpattern.interpreter.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Data
@Slf4j
@AllArgsConstructor
public class Delay implements Expression {

    private int seconds;

    @Override
    public void interpret() {
        log.info(" waiting {} second", seconds);
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
