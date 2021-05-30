package com.bot.ikariam.handler.connection;

import com.bot.ikariam.configuration.Properties;
import com.bot.ikariam.navigator.Navigator;
import com.bot.ikariam.navigator.PageIdentifier;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import static com.bot.ikariam.handler.connection.Constants.EMAIL_INPUT_LOGIN_FORM;
import static com.bot.ikariam.handler.connection.Constants.LOGIN_BUTTON_FORM;
import static com.bot.ikariam.handler.connection.Constants.LOGIN_REGISTER_FORM;
import static com.bot.ikariam.handler.connection.Constants.LOGIN_TAB_FORM;
import static com.bot.ikariam.handler.connection.Constants.LOGIN_TAB_FORM_IDENTIFIER;
import static com.bot.ikariam.handler.connection.Constants.PASSWORD_INPUT_LOGIN_FORM;
import static com.bot.ikariam.handler.connection.Constants.PLAY_BUTTON_CONNECTED_PAGE;

@Log
@Service
public class ConnectionHandler {

    private final Navigator navigator;

    private final PageIdentifier pageIdentifier;

    private final Properties properties;

    private boolean isFirstLogin = true;

    public ConnectionHandler(Navigator navigator, PageIdentifier pageIdentifier, Properties properties) {
        this.navigator = navigator;
        this.pageIdentifier = pageIdentifier;
        this.properties = properties;
    }

    public void execute() {
        if(pageIdentifier.isDisconnectedModalOpen() || isFirstLogin) {
            log.info("Logging on game");
            goToConnectPage();
            if (!pageIdentifier.isOnConnectionPage()) {
                changeToLoginForm();
                inputEmail();
                inputPassword();
                clickOnLoginButton();
            }
            clickOnPlayButton();
            clickOnPlayButton();
            moveToGameTab();
            isFirstLogin = false;
        }
    }

    private void goToConnectPage() {
        log.info("Going to connect/login page");
        navigator.get("https://lobby.ikariam.gameforge.com/pt_BR/");
    }

    private void changeToLoginForm() {
        navigator.waitForElementPresence(LOGIN_REGISTER_FORM);
        navigator.click(LOGIN_TAB_FORM);
        navigator.waitForElementPresence(LOGIN_TAB_FORM_IDENTIFIER);
    }

    private void inputEmail() {
        log.info("Passing e-mail to login form");
        navigator.sendKeys(EMAIL_INPUT_LOGIN_FORM, properties.getEmail());
    }

    private void inputPassword() {
        log.info("Passing password to login form");
        navigator.sendKeys(PASSWORD_INPUT_LOGIN_FORM, properties.getPassword());
    }

    private void clickOnLoginButton() {
        log.info("Clicking on login button");
        navigator.click(LOGIN_BUTTON_FORM);
    }

    private void clickOnPlayButton() {
        log.info("Clicking on play button");
        navigator.waitForElementPresence(PLAY_BUTTON_CONNECTED_PAGE);
        navigator.click(PLAY_BUTTON_CONNECTED_PAGE);
    }

    private void moveToGameTab() {
        log.info("Moving to game tab");
        navigator.moveToLastTab();
    }
}
