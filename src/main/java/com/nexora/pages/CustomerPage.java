package com.nexora.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CustomerPage {
    private final Page page;

    public CustomerPage(Page page) {
        this.page = page;
    }

    public void verifyCustomerPage() {
        String pageText = page.locator("body").innerText();
        Locator customerHeader = page.locator("h1")
                .filter(new Locator.FilterOptions().setHasText("Customer Management"))
                .first();

        //System.out.println("Customer Page Title: " + page.title());
       // System.out.println("Customer Page URL: " + page.url());
        // System.out.println("Customer Management header visible: " + customerHeader.isVisible());
        // System.out.println("Page contains 'customer': " + pageText.toLowerCase().contains("customer"));
        //System.out.println("URL contains /customer: " + page.url().contains("/customer"));

        assertThat(page).hasURL("https://admin.nexorabysoradis.com/customer");

        assertThat(customerHeader).isVisible();

        //System.out.println("Customer Page Text:");
       // System.out.println(pageText);
    }
    public BulkEntryPage clickBulkEntry() {

        Locator bulkEntry = page.getByText("Bulk Entry", new Page.GetByTextOptions().setExact(true));
        assertThat(bulkEntry).isVisible();
        bulkEntry.click();
        System.out.println("Bulk Entry clicked successfully");
        return new BulkEntryPage(page);
    }
    public AddCustomerPage clickAddCustomer() {

        page.getByText("Add Customer", new Page.GetByTextOptions().setExact(true)).click();

        return new AddCustomerPage(page);
    }
    public Locator getSearchField() {

        Locator searchField = page.getByPlaceholder(
                "Search by Customer Name, Email..."
        );

        assertThat(searchField).isVisible();

        return searchField;
    }
    public void searchCustomer(String customerName) {

        Locator searchField = getSearchField();

        searchField.fill(customerName);
    }
}
