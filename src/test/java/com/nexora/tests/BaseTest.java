package com.nexora.tests;
import com.microsoft.playwright.Page;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.nexora.pages.utils.ConfigReader;
import com.nexora.pages.utils.DriverFactory;

public class BaseTest {
    protected Page page;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        // 2. Initialize browser via DriverFactory
        DriverFactory.initBrowser();

        // 3. Assign page instance and navigate
        page = DriverFactory.getPage();
        page.navigate(ConfigReader.getProperty("base_url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.closeBrowser();
    }
}
