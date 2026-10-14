package com.nexora.pages;

import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.Locator;

public class RolePermissionPage {

    private final Page page;

    public RolePermissionPage(Page page) {
        this.page = page;
    }

    public void navigateToRolesPage() {
        page.navigate("https://admin.nexorabysoradis.com/roles");

        assertThat(page.getByText(
                "All Roles",
                new Page.GetByTextOptions().setExact(true)
        )).isVisible();

        System.out.println(
                "Roles & Permissions page is displayed successfully."
        );
    }
    public void clickCreateRole() {
        page.getByRole(
                com.microsoft.playwright.options.AriaRole.BUTTON,
                new Page.GetByRoleOptions()
                        .setName("Create Role")
                        .setExact(true)
        ).click();

        System.out.println("Clicked Create Role button.");

    }
    public void verifyCreateRoleButton() {
        Locator createRoleForm = page.getByLabel("Create New RoleDefine role");

        Locator createRoleButton = createRoleForm.getByRole(
                com.microsoft.playwright.options.AriaRole.BUTTON,
                new Locator.GetByRoleOptions()
                        .setName("Create Role")
                        .setExact(true)
        );

        assertThat(createRoleButton).isVisible();
        assertThat(createRoleButton).isEnabled();

        System.out.println(
                "Create Role button inside the form is visible and enabled."
        );
    }
    public void verifyRolePermissionToggles() {
        assertThat(page.getByText(
                "Is Picker",
                new Page.GetByTextOptions().setExact(true)
        )).isVisible();

        assertThat(page.getByText(
                "Is Driver",
                new Page.GetByTextOptions().setExact(true)
        )).isVisible();

        assertThat(page.getByText(
                "Settings Access",
                new Page.GetByTextOptions().setExact(true)
        )).isVisible();

        assertThat(page.getByText(
                "Security Access",
                new Page.GetByTextOptions().setExact(true)
        )).isVisible();

        System.out.println("All four role and permission toggle labels are visible.");

    }
    public void enterRoleName(String roleName) {
        Locator roleNameInput = page.locator("input[name='name']");

        assertThat(roleNameInput).isVisible();
        roleNameInput.fill(roleName);

        System.out.println("Entered Role Name: " + roleName);

    }

    public void enterRoleDescription(String description) {
        Locator descriptionInput = page.locator("textarea[name='description']");

        assertThat(descriptionInput).isVisible();
        descriptionInput.fill(description);

        System.out.println("Entered Role Description: " + description);

    }

    public void verifyRoleInformation(String roleName, String description) {
        assertThat(page.locator("input[name='name']")).hasValue(roleName);
        assertThat(page.locator("textarea[name='description']")).hasValue(description);

        System.out.println("Verified role name and description successfully.");

    }

    public void enableIsPickerToggle() {
        Locator pickerToggle = page.getByRole(
                com.microsoft.playwright.options.AriaRole.SWITCH
        ).first();

        assertThat(pickerToggle).isVisible();

        String currentState = pickerToggle.getAttribute("aria-checked");

        if ("false".equals(currentState)) {
            pickerToggle.click();
        }

        assertThat(pickerToggle).hasAttribute("aria-checked", "true");

        System.out.println("Verified: Is Picker toggle is ON.");
    }


    public void enableIsDriverToggle() {
        Locator driverLabel = page.getByText(
                "Is Driver",
                new Page.GetByTextOptions().setExact(true)
        );

        assertThat(driverLabel).isVisible();

        Locator driverToggle = driverLabel.locator("xpath=following-sibling::button[@role='switch']");

        assertThat(driverToggle).isVisible();

        String currentState = driverToggle.getAttribute("aria-checked");

        if ("false".equals(currentState)) {
            driverToggle.click();
        }

        assertThat(driverToggle).hasAttribute("aria-checked", "true");

        System.out.println("Verified: Is Driver toggle is ON.");
    }

    public void clickCustomerPermissionsTab() {

        Locator customerTab = page.locator("span.ant-collapse-title")
                .filter(new Locator.FilterOptions()
                        .setHas(
                                page.getByText(
                                        "Customer Permissions",
                                        new Page.GetByTextOptions()
                                                .setExact(true)
                                )
                        ));

        assertThat(customerTab).isVisible();
        customerTab.click();

        System.out.println("Clicked Customer Permissions tab.");
    }

    public void setCustomerPermission(String permissionName, boolean enable) {
        Locator permissionLabel = page.getByText(
                permissionName,
                new Page.GetByTextOptions().setExact(true)
        );

        assertThat(permissionLabel.first()).isVisible();

        Locator permissionRow = permissionLabel.first().locator("xpath=..");

        Locator permissionToggle = permissionRow.getByRole(
                com.microsoft.playwright.options.AriaRole.SWITCH
        );

        assertThat(permissionToggle).isVisible();

        String expectedState = String.valueOf(enable);
        String currentState = permissionToggle.getAttribute("aria-checked");

        if (!expectedState.equals(currentState)) {
            permissionToggle.click();
        }

        assertThat(permissionToggle).hasAttribute("aria-checked", expectedState);

        System.out.println(
                "Verified Customer permission: " + permissionName
                        + " = " + (enable ? "ON" : "OFF")
        );
    }



    public void submitCreateRole() {

            // Capture the response from the role creation API.
            page.onResponse(response -> {
                if (response.url().contains("/api/role")
                        && response.request().method().equals("POST")) {

                    System.out.println("Role API URL: " + response.url());
                    System.out.println("Role API Status: " + response.status());

                    try {
                        System.out.println(
                                "Role API Response Body: "
                                        + response.text()
                        );
                    } catch (Exception e) {
                        System.out.println(
                                "Could not read API response body: "
                                        + e.getMessage()
                        );
                    }
                }
            });

            Locator createRoleButton = page.locator("button.bg-gradient-to-r")
                    .filter(new Locator.FilterOptions()
                            .setHasText("Create Role"));

            assertThat(createRoleButton).isVisible();
            assertThat(createRoleButton).isEnabled();

            createRoleButton.click();

            System.out.println("Clicked final Create Role button.");

            page.waitForTimeout(1500);
        }

    }
