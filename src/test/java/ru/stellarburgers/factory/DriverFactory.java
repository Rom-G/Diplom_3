package ru.stellarburgers.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Getter
public class DriverFactory extends ExternalResource {

    private WebDriver driver;

    public void initDriver() {
        if ("firefox".equals(System.getProperty("browser")))
            startFirefox();
        else if ("yandex".equals(System.getProperty("browser"))) {
            startYandex();
        } else
            startChrome();
    }

    public void startFirefox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    public void startChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public void startYandex() {
        String driverPath = "C:/WebDriver/bin/yandexdriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
    }

    @Override
    protected void before() {
        initDriver();
    }

    @Override
    protected void after() {
        driver.quit();
    }
}
