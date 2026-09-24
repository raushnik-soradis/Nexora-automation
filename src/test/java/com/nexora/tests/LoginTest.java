package com.nexora.tests;
import com.nexora.pages.AddCustomerPage;
import com.nexora.pages.CustomerPage;
import com.nexora.pages.DashboardPage;
import com.nexora.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nexora.pages.utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {

        // Initialize LoginPage using the page instance provided by BaseTest
        LoginPage loginPage = new LoginPage(page);

        // Perform login using credentials from config.properties
        DashboardPage dashboardPage = loginPage.loginToApplication(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );
        Assert.assertNotNull(dashboardPage);
        dashboardPage.verifyDashboardPage();
        CustomerPage customerPage = dashboardPage.goToCustomerPage();

        customerPage.verifyCustomerPage();

        AddCustomerPage addCustomerPage = customerPage.clickAddCustomer();

        addCustomerPage.verifyAddCustomerPage();
        addCustomerPage.enterCompanyName("Playwright Test Company");
        addCustomerPage.enterIndustry("Logistics");
        addCustomerPage.enterGST("27AABCT1234M1Z5");
        addCustomerPage.enterCustomerName("John Doe");
        addCustomerPage.enterPhone("7321802490");
        addCustomerPage.enterEmail("raushnitrigun@gmail.com");
        addCustomerPage.enterOtp("123456");
        addCustomerPage.enterPassword("SecurePassword123");
        addCustomerPage.enterPassword("SecurePassword123");
        addCustomerPage.enterConfirmPassword("SecurePassword123");
        addCustomerPage.clickVerifyAndContinue();
    }

}




