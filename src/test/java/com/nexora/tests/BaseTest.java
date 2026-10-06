package com.nexora.tests;
import com.microsoft.playwright.Page;

import com.nexora.pages.CheckInPage;
import com.nexora.pages.DashboardPage;
import com.nexora.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.nexora.pages.utils.ConfigReader;
import com.nexora.pages.utils.DriverFactory;


public class BaseTest {

    protected Page page;
    protected DashboardPage dashboardPage;
    protected CheckInPage checkInPage;


    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        // Initialize browser
        DriverFactory.initBrowser();

        // Get page instance
        page = DriverFactory.getPage();

        // Navigate to application
        page.navigate(ConfigReader.getProperty("base_url"));
        checkInPage = new CheckInPage(page);
        dashboardPage = new DashboardPage(page);
    }

    // Common Login Method
    protected void login() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.loginToApplication(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );
    }
    protected void handleCheckInSkip() {

        CheckInPage checkInPage = new CheckInPage(page);

        if (checkInPage.isCheckInDisplayed()) {
            checkInPage.clickSkip();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.closeBrowser();
    }
}