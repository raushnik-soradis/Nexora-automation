package com.nexora.tests;

import com.nexora.pages.StaffPage;
import org.testng.annotations.Test;


public class StaffTest extends BaseTest {
    @Test
    public void verifyStaffPage() {
        login();
        dashboardPage.verifyDashboardPage();
        StaffPage staffPage = new StaffPage(page);
        page.getByText("Staff",
                new com.microsoft.playwright.Page.GetByTextOptions()
                        .setExact(true)).click();
        staffPage.verifyStaffPage();
        //staffPage.searchStaff("Yogesh");
        //staffPage.searchStaff("yogesh.k@westernsuperfresh.com");
        staffPage.verifyStaffStatusDropdown();
    }


        @Test
        public void editStaffMember() {
            login();
            dashboardPage.verifyDashboardPage();
            StaffPage staffPage = new StaffPage(page);
            page.getByText("Staff",
                    new com.microsoft.playwright.Page.GetByTextOptions()
                            .setExact(true)).click();
            staffPage.verifyStaffPage();
            staffPage.clickEditButton();
            staffPage.clickNext();
            staffPage.selectWarehouseManagerRole();
            staffPage.clickNextOnRolePermissionPage();
            staffPage.clickUpdateStaff();
           staffPage.verifyUpdatedStaffRole();


        }
}
