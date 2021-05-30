package com.bot.ikariam.handler.captcha.solver.telegram;

import com.bot.ikariam.configuration.Properties;
import com.bot.ikariam.handler.captcha.solver.SolverStrategy;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class TelegramStrategy implements SolverStrategy {

    private final TelegramBot bot;

    private final Properties properties;

    private static final AtomicBoolean isWaitingCaptcha = new AtomicBoolean(false);

    private static final AtomicReference<String> captchaResult = new AtomicReference<>("");

    public TelegramStrategy(TelegramBot bot, Properties properties) {
        this.bot = bot;
        this.properties = properties;
        setUpListeners();
    }

    @Override
    public String execute(File file) {
        SendResponse sendResponse = bot.execute(new SendPhoto(properties.getTelegramUserId(), file));
        log.info(sendResponse.toString());
        waitRespponse();
        return captchaResult.get();
    }

    private void waitRespponse() {
        isWaitingCaptcha.set(true);
        while (isWaitingCaptcha.get()) {
            try {
                log.info("Waiting for captcha");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                isWaitingCaptcha.set(false);
            }
        }
    }

    private void setUpListeners() {
        bot.setUpdatesListener((updates) -> {
            updates.forEach(update -> {
                log.info(update.toString());
                String text = update.message().text();
                if(CaptchaCommand.isCaptchaCommand(text)) {
                    log.info("Receiving captcha from telegram");
                    String argument = CaptchaCommand.extractArgs(text);
                    captchaResult.set(argument);
                    log.info("Captcha received: {}", argument);
                    isWaitingCaptcha.set(false);
                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
