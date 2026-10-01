package com.nexora.tests;

import com.nexora.pages.DashboardPage;
import com.nexora.pages.LoginPage;
import com.nexora.pages.VehiclePage;
import com.nexora.pages.utils.ConfigReader;
import org.testng.annotations.Test;

public class VehicleTest extends BaseTest {
    // Add your test methods here
    @Test
    public void verifyVehiclePage() {

        // Login
        LoginPage loginPage = new LoginPage(page);

        loginPage.loginToApplication(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );
        handleCheckInSkip();

        // Dashboard
        DashboardPage dashboardPage = new DashboardPage(page);
        VehiclePage vehiclePage = dashboardPage.goToVehiclePage();
        vehiclePage.verifyVehiclePage();


        // Navigate to Vehicles

    }
}