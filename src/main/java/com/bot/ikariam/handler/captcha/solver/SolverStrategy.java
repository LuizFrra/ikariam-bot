package com.bot.ikariam.handler.captcha.solver;

import java.io.File;

public interface SolverStrategy {
    String execute(File file);
}
