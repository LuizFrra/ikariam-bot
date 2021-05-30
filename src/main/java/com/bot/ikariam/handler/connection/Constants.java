package com.bot.ikariam.handler.connection;

import org.openqa.selenium.By;

public class Constants {
    public static final By EMAIL_INPUT_LOGIN_FORM = By.xpath("//input[@type='email']");

    public static final By PASSWORD_INPUT_LOGIN_FORM = By.xpath("//input[@type='password']");

    public static final By LOGIN_BUTTON_FORM = By.xpath("//button[@type='submit']");

    public static final By PLAY_BUTTON_CONNECTED_PAGE = By.xpath("//span[text()='Jogar']");

    public static final By LOGIN_REGISTER_FORM = By.id("loginRegisterTabs");

    public static final By LOGIN_TAB_FORM = By.xpath("//span[text()='Login']");

    public static final By LOGIN_TAB_FORM_IDENTIFIER = By.id("loginTab");

}
