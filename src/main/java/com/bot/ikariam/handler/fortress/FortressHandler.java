package com.bot.ikariam.handler.fortress;

import com.bot.ikariam.handler.captcha.CaptchaHandler;
import com.bot.ikariam.navigator.Navigator;
import com.bot.ikariam.navigator.PageIdentifier;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.springframework.stereotype.Service;

import static com.bot.ikariam.handler.fortress.Constants.BUILD_FORTRESS;
import static com.bot.ikariam.handler.fortress.Constants.CAPTURE_BUTTON;
import static com.bot.ikariam.handler.fortress.Constants.MISSION_IN_PROGRESS_BAR;
import static com.bot.ikariam.handler.fortress.Constants.MISSION_IN_PROGRESS_TEXT_BOX;

@Log
@Service
@AllArgsConstructor
public class FortressHandler {

    private final Navigator navigator;

    private final CaptchaHandler captchaHandler;

    private final PageIdentifier pageIdentifier;

    public boolean captureContrabandist() {
        openFortressModal();

        boolean captchaDetected = captchaHandler.isNeedToSolveCaptcha();
        boolean isMissionInProgress = isMissionInProgress();
        boolean captchaWasSolved = true;

        if (captchaDetected) {
            captchaWasSolved = captchaHandler.solveCaptcha();
            if(!captchaWasSolved) log.info("Error while validating captcha");
        }

        if (!isMissionInProgress && captchaWasSolved) {
            log.info("Starting capture of contrabandist");
            navigator.waitAndClick(CAPTURE_BUTTON);
            return true;
        }

        return false;
    }

    private boolean isMissionInProgress() {
        log.info("Checking if have mission in progress");
        boolean isMissionInProgress = navigator.elementExist(MISSION_IN_PROGRESS_BAR) || navigator.elementExist(MISSION_IN_PROGRESS_TEXT_BOX);
        if (isMissionInProgress) log.info("Already have a mission in progress");
        return isMissionInProgress;
    }

    public void openFortressModal() {
        if (!pageIdentifier.isFortressModalOpen()) {
            log.info("Openning Fortress modal");
            navigator.waitAndClick(BUILD_FORTRESS);
        }
    }
}
