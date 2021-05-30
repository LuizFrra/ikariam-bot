package com.bot.ikariam.configuration;

import com.pengrad.telegrambot.TelegramBot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public WebDriver setupWebDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("general.useragent.override", "Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36");
        options.addArguments("--enable-javascript");
        options.addPreference("javascript.enabled", true);
        return new FirefoxDriver(options);
    }

    @Bean
    public WebDriverWait setupWebDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, 30);
    }

    @Bean
    public TelegramBot telegramBot(Properties properties) {
        return new TelegramBot(properties.getTelegramToken());
    }
}
