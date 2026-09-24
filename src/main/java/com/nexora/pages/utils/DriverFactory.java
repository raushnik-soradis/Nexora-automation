package com.nexora.pages.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class DriverFactory {
    private static Playwright playwright;
    private static Browser browser;
    public static Page page;

    public static void initBrowser() {

        playwright = Playwright.create();

        String browserName = ConfigReader.getProperty("browser");

        if (browserName.equalsIgnoreCase("chromium")) {

            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
            );

        } else {
            throw new RuntimeException(
                    "Unsupported browser: " + browserName
            );
        }

        page = browser.newPage();

        page.setDefaultTimeout(8000);
    }

    public static Page getPage() {
        return page;
    }

    public static void closeBrowser() {

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}
