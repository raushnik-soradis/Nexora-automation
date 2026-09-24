package com.nexora.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AddCustomerPage {

    Page page;

    public AddCustomerPage(Page page) {
        this.page = page;
    }

    public void verifyAddCustomerPage() {
        assertThat(
                page.getByText("Add Customer", new Page.GetByTextOptions().setExact(true))).isVisible();
    }

    public void enterCompanyName(String companyName) {
        page.locator("input[name='companyName']").fill(companyName);
    }

    public void enterIndustry(String industry) {
        page.getByPlaceholder(
                "e.g., Logistics, Manufacturing"
        ).fill(industry);
    }

    public void enterGST(String gst) {
        page.locator("input[name='gst']").fill(gst);
    }

    // Contact Information heading
    public void enterCustomerName(String name) {
        page.locator("input[name='name']").fill(name);
    }

    public void enterPhone(String phoneNumber) {
        page.locator("input[name='phone']").fill(phoneNumber);
    }

    public void enterEmail(String email) {
        page.locator("input[name='email']").fill(email);
    }

    public void clickOutsideEmail() {
        page.locator("body").click(
                new Locator.ClickOptions().setPosition(10, 10)
        );
    }

    // OTP
    public void enterOtp(String otp) {
        System.out.println("Entering OTP: " + otp);
        assert otp.length() == 6 : "OTP must contain 6 digits";
        for (int i = 0; i < otp.length(); i++) {
            Locator otpField = page.getByLabel("OTP Input " + (i + 1));
            assertThat(otpField).isVisible();
            otpField.click();
            otpField.pressSequentially(String.valueOf(otp.charAt(i)));
        }
        System.out.println("OTP entry completed");

    }

    public void clickVerifyAndContinue() {
        Locator verifyContinue = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Verify & Continue"));
        assertThat(verifyContinue).isVisible();
        verifyContinue.click();
        System.out.println("Verify & Continue clicked");
    }

    public void enterPassword(String password) {
        // Target the first password input on the OTP/password modal
        System.out.println("Entering password...");
        Locator passwordField = page.locator("input[name='password']");
        assertThat(passwordField).isVisible();
        passwordField.fill(password);
        System.out.println("Password entered successfully");
    }

    public void enterConfirmPassword(String password) {
        // Target the second password input (confirm) if present
        System.out.println("Entering confirm password...");
        Locator confirmPasswordField = page.locator("input[name='confirmPassword']");
        assertThat(confirmPasswordField).isVisible();
        confirmPasswordField.fill(password);
        System.out.println("Confirm password entered successfully");
    }

    public void clickNext() {
        Locator nextButton = page.getByLabel("Add New CustomerCreate a new").locator("button[type='submit']");
        assertThat(nextButton).isVisible();
        nextButton.click();
        System.out.println("First Next clicked successfully");
    }
    public void verifyNextPage() {

        System.out.println("Verifying Consignor Addresses page...");

        Locator heading =page.getByText("Consignor Addresses (Pickup Locations)",
                new Page.GetByTextOptions().setExact(true));
        assertThat(heading).isVisible();

        System.out.println("Consignor Addresses page is visible");
    }

    public void clickSecondNext() {

        System.out.println("Clicking second Next...");

        Locator nextButtons = page.getByRole(
                AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next"));
                Locator nextButton = nextButtons.last();
                assertThat(nextButton).isVisible();
                nextButton.click();
                System.out.println("Second Next clicked successfully");
    }
    public void verifyFinalPage() {

        System.out.println("Verifying final page...");

        Locator heading = page.getByText("Consignee Addresses (Delivery Locations)",
                new Page.GetByTextOptions().setExact(true));
        assertThat(heading).isVisible();

        System.out.println("Consignee Addresses (Delivery Locations) is visible");
    }
    public void clickLastButton() {

        System.out.println("Clicking last Next...");
        Locator nextButtons = page.getByRole(
                AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Create Customer"));
        Locator nextButton = nextButtons.last();
        assertThat(nextButton).isVisible();
        nextButton.click();
        System.out.println("Create Customer successfully");
    }

}