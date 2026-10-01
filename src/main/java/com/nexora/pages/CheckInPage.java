package com.nexora.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.nexora.pages.utils.DriverFactory.page;

public class CheckInPage {
    Page page;

    public CheckInPage(Page page) {
        this.page = page;
    }
        public boolean isCheckInDisplayed() {

            Locator checkInPopup = page.locator(".ant-modal-wrap");

            return checkInPopup.isVisible();
        }

    public void verifySkipButton() {

        Locator skipButton = page.locator("button[type='button']")
                .filter(new Locator.FilterOptions().setHasText("Skip"));

        assertThat(skipButton).isVisible();
        assertThat(skipButton).isEnabled();
    }

    public void clickSkip() {


        Locator skipButton = page.locator("button[type='button']")
                .filter(new Locator.FilterOptions().setHasText("Skip"));

        assertThat(skipButton).isVisible();
        assertThat(skipButton).isEnabled();

        skipButton.click();

    }
    public void verifyCheckInButton() {

        Locator checkInButton = page.locator("button[type='submit']")
                .filter(new Locator.FilterOptions().setHasText("Check In"));

        assertThat(checkInButton).isVisible();
        assertThat(checkInButton).isEnabled();


    }
    public void clickCheckIn() {

        Locator checkInButton = page.locator("button[type='submit']")
                .filter(new Locator.FilterOptions().setHasText("Check In"));

        assertThat(checkInButton).isVisible();
        assertThat(checkInButton).isEnabled();
        System.out.println("Check In button is visible and enabled.");
        System.out.println("Clicking Check In...");

        checkInButton.click();
        System.out.println("Check In button clicked.");
        Locator popup = page.getByText(
                "Don't Skip the Check-In",
                new Page.GetByTextOptions().setExact(true)
        );

        if (!popup.isVisible()) {
            System.out.println("SUCCESS: Check In submitted successfully.");
        } else {
            System.out.println("Check In popup is still visible.");
        }

    }
}
