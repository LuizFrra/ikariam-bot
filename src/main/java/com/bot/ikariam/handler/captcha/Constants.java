package com.bot.ikariam.handler.captcha;

import org.openqa.selenium.By;

public class Constants {
    public static final By FORM_CAPTCHA = By.className("captchaImage");

    public static final By INPUT_CAPTCHA_FORM = By.xpath("//input[@name='captcha']");

    public static final By CONFIRM_BUTTON_CAPTCHA = By.xpath("//input[@value='Capturar']");
}
