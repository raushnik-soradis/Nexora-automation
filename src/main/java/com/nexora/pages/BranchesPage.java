package com.nexora.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BranchesPage {
    Page page;

    public BranchesPage(Page page) {
        this.page = page;
    }

    public void navigateToBranchesPage() {
        page.navigate("https://admin.nexorabysoradis.com/branches");
        System.out.println("Navigated to Branches page.");

        assertThat(page.getByRole(
                com.microsoft.playwright.options.AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("Branch Management")
        )).isVisible();

        System.out.println("Branch Management page is displayed successfully.");
    }

    public void verifyAddBranchButton() {
        assertThat(
                page.getByRole(
                        com.microsoft.playwright.options.AriaRole.BUTTON,
                        new Page.GetByRoleOptions().setName("Add Branch")
                )
        ).isVisible();

        System.out.println("Add Branch button is visible.");
    }

    public void verifyExistingBranch(String branchName) {
        assertThat(page.getByText(
                branchName,
                new Page.GetByTextOptions().setExact(true)
        )).isVisible();

        System.out.println("Verified branch: " + branchName);
    }

    public void clickViewBranch(String branchName) {

        // Find the table row for the specified branch.
        Locator branchRow = page.locator("tr")
                .filter(new Locator.FilterOptions()
                        .setHasText(branchName));

        assertThat(branchRow).isVisible();

        // Click View only within the selected branch row.
        Locator viewButton = branchRow.locator("button[title='View']");

        assertThat(viewButton).isVisible();
        viewButton.click();

        System.out.println("Clicked View for branch: " + branchName);
    }

    public void clickEditBranch(String branchName) {

        Locator branchRow = page.locator("tr")
                .filter(new Locator.FilterOptions()
                        .setHasText(branchName));

        assertThat(branchRow).isVisible();

        Locator editButton = branchRow.locator("button[title='Edit']");

        assertThat(editButton).isVisible();
        assertThat(editButton).isEnabled();

        editButton.click();

        System.out.println("Clicked Edit for branch: " + branchName);
    }


    public void updateBranchPincode(String newPincode) {

        Locator pincodeInput = page.locator("input[name='pincode']");

        assertThat(pincodeInput).isVisible();
        pincodeInput.fill(newPincode);

        System.out.println("Entered updated pincode: " + newPincode);

        Locator updateButton = page.getByRole(
                com.microsoft.playwright.options.AriaRole.BUTTON,
                new Page.GetByRoleOptions()
                        .setName("Update Branch")
        );

        assertThat(updateButton).isVisible();
        assertThat(updateButton).isEnabled();

        updateButton.click();

        System.out.println("Clicked Update Branch button.");
    }


    public void clickAddBranch() {
        Locator addBranchButton = page.getByRole(
                com.microsoft.playwright.options.AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Add Branch")
        );

        assertThat(addBranchButton).isVisible();
        assertThat(addBranchButton).isEnabled();

        addBranchButton.click();

        System.out.println("Clicked Add Branch button.");
    }

    public void enterBranchName(String branchName) {
        Locator branchNameInput = page.locator("input[name='name']");

        assertThat(branchNameInput).isVisible();
        branchNameInput.fill(branchName);

        System.out.println("Entered Branch Name: " + branchName);
    }




    public void selectOfficeLocationType() {

        // Locate the Add Branch modal
        Locator branchModal = page.locator(".ant-modal-wrap")
                .filter(new Locator.FilterOptions()
                        .setHas(page.locator("input[name='name']")));

        assertThat(branchModal).isVisible();

        // Locate the Location Type dropdown inside the modal
        Locator locationTypeInput = branchModal.locator(
                "input.ant-select-input[role='combobox']"
        ).first();

        assertThat(locationTypeInput).isVisible();
        locationTypeInput.click();

        // Select Office from the dropdown options
        Locator officeOption = page.locator(
                ".ant-select-item-option[title='Office']"
        );

        assertThat(officeOption).isVisible();
        officeOption.click();

        System.out.println("Selected Location Type: Office");
    }

    public void enterBranchAddress(String address) {
        Locator addressInput = page.locator("textarea[name='address']");

        assertThat(addressInput).isVisible();
        addressInput.fill(address);

        System.out.println("Entered Branch Address: " + address);
    }

    public void enterBranchPincode(String pincode) {
        Locator pincodeInput = page.locator("input[name='pincode']");

        assertThat(pincodeInput).isVisible();
        pincodeInput.fill(pincode);

        System.out.println("Entered Branch Pincode: " + pincode);
    }

    public void clickCreateBranch() {
        Locator createBranchButton = page.getByRole(
                com.microsoft.playwright.options.AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Create Branch")
        );

        assertThat(createBranchButton).isVisible();
        assertThat(createBranchButton).isEnabled();
        createBranchButton.click();

        System.out.println("Clicked Create Branch button.");
    }




}





