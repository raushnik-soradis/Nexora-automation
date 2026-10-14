package com.nexora.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class StaffPage {
    Page page;


    public StaffPage(Page page) {
        this.page = page;
    }

    public void verifyStaffPage() {
        Locator staffHeading = page.locator("h1")
                .filter(new Locator.FilterOptions().setHasText("Staff Management"));
        assertThat(staffHeading).isVisible();
        System.out.println("Staff Management page is displayed successfully.");
    }

    public void searchStaff(String staffName) {
        Locator searchBox = page.getByPlaceholder(
                "Search by Staff name, email, phone..."
        );

        assertThat(searchBox).isVisible();
        searchBox.fill(staffName);

        System.out.println("Entered staff name in search: " + staffName);

    }

    public void verifyStaffStatusDropdown() {
        // Click the All Status dropdown
        page.getByText("All Status",
                new Page.GetByTextOptions().setExact(true)).click();

        // Select Active status
        page.getByText("Active",
                new Page.GetByTextOptions().setExact(true)).click();

        System.out.println("Selected Active status from dropdown.");

        // Verify In-Active option is visible
        assertThat(page.getByText("In-Active",
                new Page.GetByTextOptions().setExact(true))).isVisible();

        System.out.println(
                "Staff Status dropdown displayed Active and In-Active successfully."
        );
    }

    public void clickEditButton() {

        Locator raushniRow = page.locator("tr")
                .filter(new Locator.FilterOptions()
                        .setHasText("raushni@westernsuperfresh.com"));

        assertThat(raushniRow).isVisible();

        Locator editButton = raushniRow.locator("button[title='Edit']");

        assertThat(editButton).isVisible();
        editButton.click();

        System.out.println(
                "Clicked Edit button for Raushni Kumari."
        );
    }

    public void clickNext() {
        Locator nextButton = page.locator("button[type='submit']")
                .filter(new Locator.FilterOptions()
                        .setHasText("Next"))
                .last();

        assertThat(nextButton).isVisible();
        nextButton.click();

        System.out.println("Clicked Next button successfully.");
    }

    public void selectWarehouseManagerRole() {

        Locator warehouseManagerHeading = page.getByRole(
                com.microsoft.playwright.options.AriaRole.HEADING,
                new Page.GetByRoleOptions()
                        .setName("WAREHOUSE MANAGER")
                        .setExact(true)
        );

        assertThat(warehouseManagerHeading).isVisible();
        warehouseManagerHeading.click();

        // Go up three levels to the outer role card
        Locator warehouseManagerCard =
                warehouseManagerHeading.locator("xpath=../../..");

        assertThat(warehouseManagerCard)
                .hasClass(java.util.regex.Pattern.compile(
                        ".*border-blue-500.*"
                ));

        System.out.println(
                "Verified selected role: WAREHOUSE MANAGER"
        );
    }

    public void clickNextOnRolePermissionPage() {
        Locator nextButton = page.locator(".ant-modal-wrap")
                .getByRole(
                        com.microsoft.playwright.options.AriaRole.BUTTON,
                        new Locator.GetByRoleOptions()
                                .setName("Next")
                                .setExact(true)
                );

        assertThat(nextButton).isVisible();
        nextButton.click();

        System.out.println("Clicked Next on Role & Permission page.");
    }

    public void clickUpdateStaff() {
        Locator updateButton = page.getByRole(
                com.microsoft.playwright.options.AriaRole.BUTTON,
                new Page.GetByRoleOptions()
                        .setName("Update Staff")
                        .setExact(true)
        );

        assertThat(updateButton).isVisible();
        assertThat(updateButton).isEnabled();

        com.microsoft.playwright.Response response =
                page.waitForResponse(
                        r -> r.url().contains("/api/staff/")
                                && r.request().method().equals("PUT"),
                        () -> updateButton.click()
                );

        System.out.println("Update API status: " + response.status());
        System.out.println("Update API response: " + response.text());

        System.out.println("Clicked Update Staff button successfully.");
    }


    public void verifyUpdatedStaffRole() {
        Locator raushniRow = page.locator("tr")
                .filter(new Locator.FilterOptions()
                        .setHasText("raushni@westernsuperfresh.com"));

        assertThat(raushniRow).isVisible();

        Locator updatedRole = raushniRow.getByText(
                "WAREHOUSE MANAGER",
                new Locator.GetByTextOptions().setExact(true)
        );

        assertThat(updatedRole).isVisible();

        System.out.println(
                "Verified: Raushni Kumari's role is WAREHOUSE MANAGER."
        );

    }

}