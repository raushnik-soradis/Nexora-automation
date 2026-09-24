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
        page.locator("input[name='name']").fill(name); }

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
        // wait for modal/button that indicates OTP flow
        try {
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Verify & Continue")).waitFor();
        } catch (Exception ignored) {
            // continue — modal might not use that button
        }
    }
    public void clickVerifyAndContinue() {
        Locator verifyContinue = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Verify & Continue"));
        assertThat(verifyContinue).isVisible();
        verifyContinue.click();
    }
    public void enterPassword(String password) {
        // Target the first password input on the OTP/password modal
        page.locator("input[name='password']").fill(password);
    }

    public void enterConfirmPassword(String password) {
        // Target the second password input (confirm) if present
        page.locator("input[name='confirmPassword']").fill(password);
    }

    public void clickNext() {
        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Next ")
        ).click();
    }



    }

