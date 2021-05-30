package com.bot.ikariam.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties
public class Properties {
    @Value("${ikariam.game.url}")
    public String gameUrl;

    @Value("${ikariam.email}")
    private String email;

    @Value("${ikariam.password}")
    private String password;

    @Value("${ikariam.telegram.user.id}")
    private int telegramUserId;

    @Value("${ikariam.telegram.token}")
    private String telegramToken;
}
