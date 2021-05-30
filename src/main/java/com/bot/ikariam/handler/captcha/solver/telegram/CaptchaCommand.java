package com.bot.ikariam.handler.captcha.solver.telegram;

public class CaptchaCommand {
    public static boolean isCaptchaCommand(String text) {
        return text.startsWith("/captcha");
    }

    public static String extractArgs(String text) {
        return text.replace("/captcha ", "").replace("/captcha", "");
    }
}
