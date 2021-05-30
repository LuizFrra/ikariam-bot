package com.bot.ikariam.handler.captcha.solver;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SolverFactory {
    private final List<SolverStrategy> strategies;

    public SolverStrategy get() {
        return strategies.get(0);
    }
}
