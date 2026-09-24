package com.nexora.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DashboardPage {

    private final Page page;

    public DashboardPage(Page page) {
        this.page = page;
    }

    public void verifyDashboardPage() {
        System.out.println("Dashboard Title: " + page.title());
        assertThat(page).hasTitle("Nexora");
    }

    public CustomerPage goToCustomerPage() {
        String beforeUrl = page.url();

        Locator customerLink = page.locator("a, button")
               .filter(new Locator.FilterOptions().setHasText("Customer"))
               .first();

        System.out.println("Customer locator count: " + customerLink.count());
        System.out.println("Customer href: " + customerLink.getAttribute("href"));
        System.out.println("Customer aria-label: " + customerLink.getAttribute("aria-label"));
        System.out.println("Customer is visible: " + customerLink.isVisible());
        System.out.println("Customer is enabled: " + customerLink.isEnabled());

        if (customerLink.count() == 0) {
            throw new IllegalStateException("Customer menu item not found on dashboard");
        }

        assertThat(customerLink).isVisible();
        assertThat(customerLink).isEnabled();

        customerLink.click();
        page.waitForLoadState();
        page.waitForTimeout(1000);

        System.out.println("Before click URL: " + beforeUrl);
        System.out.println("After click URL: " + page.url());
        System.out.println("URL changed after click: " + !page.url().equals(beforeUrl));

        return new CustomerPage(page);
    }
}
