package com.nexora.tests;

import com.microsoft.playwright.Page;
import com.nexora.pages.RolePermissionPage;
import org.testng.annotations.Test;

public class RolePermissionTest extends BaseTest {
    @Test
    public void verifyRolesPermissionPage() {

        // Log in to Nexora
        login();

        // Create page object
        RolePermissionPage rolePermissionPage =
                new RolePermissionPage(page);

        // Navigate directly to Roles & Permissions
        rolePermissionPage.navigateToRolesPage();
        rolePermissionPage.clickCreateRole();
        rolePermissionPage.verifyCreateRoleButton();
        rolePermissionPage.verifyRolePermissionToggles();
        rolePermissionPage.enterRoleName( "Automation Role 101");
        rolePermissionPage.enterRoleDescription("Role created by Playwright automation");
        rolePermissionPage.enableIsPickerToggle();

        rolePermissionPage.enableIsDriverToggle();
// Open Customer tab before interacting with its toggles
        rolePermissionPage.clickCustomerPermissionsTab();

        rolePermissionPage.setCustomerPermission("Read", true);
        rolePermissionPage.setCustomerPermission("Write", true);
        rolePermissionPage.setCustomerPermission("Get All", true);
        rolePermissionPage.setCustomerPermission("Get One", true);
        rolePermissionPage.setCustomerPermission("Delete", false);
        rolePermissionPage.setCustomerPermission("Add", true);

        rolePermissionPage.submitCreateRole();
    }
}