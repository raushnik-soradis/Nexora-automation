package com.nexora.tests;

import com.nexora.pages.AddVehicle;
import com.nexora.pages.DashboardPage;
import com.nexora.pages.LoginPage;
import com.nexora.pages.VehiclePage;
import com.nexora.pages.utils.ConfigReader;
import org.testng.annotations.Test;


public class AddVehicleTest extends BaseTest {
    @Test
    public void addVehicle() {

        // Login
        System.out.println("========== Add Vehicle Test Started ==========");

        login();
        // Navigate to Vehicle page
        VehiclePage vehiclePage = dashboardPage.goToVehiclePage();
        AddVehicle addVehiclePage = vehiclePage.clickAddVehicle();

        addVehiclePage.verifyAddNewVehiclePage();
        addVehiclePage.enterRegistrationNumber("MH-02-AB-1234");


    }

}
