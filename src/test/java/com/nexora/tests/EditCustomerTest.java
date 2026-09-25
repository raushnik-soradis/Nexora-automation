package com.nexora.tests;

import com.nexora.pages.CustomerPage;
import com.nexora.pages.DashboardPage;
import com.nexora.pages.EditCustomer;
import com.nexora.pages.LoginPage;
import com.nexora.pages.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditCustomerTest  extends BaseTest{
    @Test
    public void testEditCustomerGST() {

        LoginPage loginPage = new LoginPage(page);

        DashboardPage dashboardPage = loginPage.loginToApplication(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        Assert.assertNotNull(dashboardPage);

        dashboardPage.verifyDashboardPage();

        CustomerPage customerPage = dashboardPage.goToCustomerPage();

        customerPage.verifyCustomerPage();

        EditCustomer editCustomerPage = new EditCustomer(page);
        editCustomerPage.verifyCompanyName("Playwright Test Company");
        editCustomerPage.clickEditCustomer("Playwright Test Company");
        editCustomerPage.verifyEditCustomerPage();
        editCustomerPage.updateGST("36AAACH7409R1Z2");
        editCustomerPage.clickNext();
        editCustomerPage.verifyNextbtnPage();
        editCustomerPage.clickSecondNextbtn();
        editCustomerPage.clickUpdateCustomer();
    }

}
