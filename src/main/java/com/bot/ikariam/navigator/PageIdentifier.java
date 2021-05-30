package com.bot.ikariam.navigator;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import static com.bot.ikariam.navigator.Constants.CONNECTIONG_PAGE;
import static com.bot.ikariam.navigator.Constants.MODAL_DISCONNECTED;
import static com.bot.ikariam.navigator.Constants.MODAL_FORTRESS;

@Log
@Service
@AllArgsConstructor
public class PageIdentifier {

    private final Navigator navigator;

    public boolean isDisconnectedModalOpen() {
        log.info("Checking if disconnected modal is open");
        boolean isOpen = navigator.elementExist(MODAL_DISCONNECTED) || isOnConnectionPage();
        if(isOpen) log.info("User is disconnected");
        return isOpen;
    }

    public boolean isFortressModalOpen() {
        log.info("Cheking if Modal Fortress is open");
        boolean isOpen = navigator.elementExist(MODAL_FORTRESS);
        if (isOpen) log.info("Modal Fortress is open");
        return isOpen;
    }

    public boolean isOnConnectionPage() {
        log.info("Checking if is in connection page");
        boolean isConnectionPage = navigator.elementExist(CONNECTIONG_PAGE);
        if(isConnectionPage) log.info("User is on connection page");
        return isConnectionPage;
    }
}
