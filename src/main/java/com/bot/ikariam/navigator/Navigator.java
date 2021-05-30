package com.bot.ikariam.navigator;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@Component
public class Navigator {
    private final WebDriver driver;

    private final WebDriverWait wait;

    public Navigator(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void get(String url) {
        driver.get(url);
    }

    /* Screenshot */
    public File takeScreenshotForElement(By by) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            BufferedImage fullImg = ImageIO.read(screenshot);
            Optional<WebElement> elementop = getElement(by);

            if (elementop.isEmpty()) return null;

            WebElement element = elementop.get();
            Point location = element.getLocation();

            int widht = element.getSize().getWidth();
            int height = element.getSize().getHeight();

            BufferedImage eleScreenshot = fullImg.getSubimage(location.getX(), location.getY(), widht, height);
            ImageIO.write(eleScreenshot, "png", screenshot);

            return screenshot;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /* CLICK AND FIND  */
    public void click(By by) {
        driver.findElement(by).click();
    }

    public void waitAndClick(By by) {
        waitForElementPresence(by);
        click(by);
    }

    public Optional<WebElement> getElement(By by) {
        List<WebElement> elements = driver.findElements(by);
        return elements.size() > 0 ? Optional.ofNullable(elements.get(0)) : Optional.empty();
    }

    public boolean elementExist(By by) {
        return driver.findElements(by).size() > 0;
    }

    public void waitForElementPresence(By by) {
        wait.until(presenceOfElementLocated(by));
    }

    public void sendKeys(By by, String input) {
        driver.findElement(by).sendKeys(input);
    }

    /* TABS */

    private String getLastTab() {
        return driver.getWindowHandles().stream().reduce((first, second) -> second).orElse("");
    }

    public void moveToTab(String name) {
        driver.switchTo().window(name);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveToLastTab() {
        moveToTab(getLastTab());
    }
}
