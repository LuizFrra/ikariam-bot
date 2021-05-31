package com.bot.ikariam.handler.captcha;

import com.bot.ikariam.handler.captcha.solver.SolverFactory;
import com.bot.ikariam.navigator.Navigator;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.bot.ikariam.handler.captcha.Constants.CONFIRM_BUTTON_CAPTCHA;
import static com.bot.ikariam.handler.captcha.Constants.FORM_CAPTCHA;
import static com.bot.ikariam.handler.captcha.Constants.INPUT_CAPTCHA_FORM;

@Log
@Service
@AllArgsConstructor
public class CaptchaHandler {

    private final Navigator navigator;

    private final SolverFactory solverFactory;

    public boolean isNeedToSolveCaptcha() {
        return navigator.elementExist(FORM_CAPTCHA);
    }

    public boolean solveCaptcha() {
        if (isNeedToSolveCaptcha()) {
            log.info("Starting solve captcha");
            File captcha = navigator.takeScreenshotForElement(FORM_CAPTCHA);
            populateCaptchaForm(getCaptchaResponseResult(captcha));
        }
        return !isNeedToSolveCaptcha();
    }

    private String getCaptchaResponseResult(File captcha) {
        return solverFactory.get().execute(captcha);
    }

    public void populateCaptchaForm(String response) {
        navigator.sendKeys(INPUT_CAPTCHA_FORM, response);
        navigator.click(CONFIRM_BUTTON_CAPTCHA);
    }

}
