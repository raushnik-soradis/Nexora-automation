package com.nexora.tests;

import com.nexora.pages.BulkEntryPage;
import com.nexora.pages.CustomerPage;
import com.nexora.pages.DashboardPage;
import com.nexora.pages.LoginPage;
import com.nexora.pages.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

     public class BulkEntryTest extends BaseTest {

        @Test
        public void testBulkEntry() {

            LoginPage loginPage = new LoginPage(page);

            DashboardPage dashboardPage = loginPage.loginToApplication(
                    ConfigReader.getProperty("username"),
                    ConfigReader.getProperty("password")
            );
            Assert.assertNotNull(dashboardPage);
            dashboardPage.verifyDashboardPage();
            CustomerPage customerPage = dashboardPage.goToCustomerPage();
            customerPage.verifyCustomerPage();
            BulkEntryPage bulkEntryPage = customerPage.clickBulkEntry();
            bulkEntryPage.verifyBulkEntryPage();
            bulkEntryPage.verifyDownloadTemplateSection();
            bulkEntryPage.downloadTemplate();
            bulkEntryPage.uploadCustomerFile();
            bulkEntryPage.verifyUploadedFile();

        }
    }

