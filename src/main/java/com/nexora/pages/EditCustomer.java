package com.nexora.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EditCustomer {
    private final Page page;

    public EditCustomer(Page page) {
        this.page = page;
    }

    public void verifyCompanyName(String companyName) {
        Locator companyNameLocator = page.getByText(companyName, new Page.GetByTextOptions().setExact(true));
        assertThat(companyNameLocator).isVisible();
        System.out.println("Company name is visible: " + companyName);
    }

    public void clickEditCustomer(String companyName) {
        Locator customerRow = page.locator("tr").filter(
                new Locator.FilterOptions().setHasText(companyName)
        );

        assertThat(customerRow).isVisible();

        Locator editButton = customerRow.locator("button[title='View']").nth(1);

        assertThat(editButton).isVisible();
        editButton.click();

        System.out.println("Edit customer button clicked for: " + companyName);
    }
    public void verifyEditCustomerPage() {

        System.out.println("Verifying Edit Customer page...");

        Locator heading = page.getByText("Edit Customer", new Page.GetByTextOptions().setExact(true));
        assertThat(heading).isVisible();

        System.out.println("Edit Customer is visible");
    }

    public void updateGST(String gst) {

        Locator gstField = page.locator("input[name='gst']");

        assertThat(gstField).isVisible();

        gstField.fill(gst);

        System.out.println("GST updated to: " + gst);
    }
    public void clickNext() {

        System.out.println("Clicking first Next...");
        Locator nextButton = page.locator("button[type='submit']") .filter(new Locator.FilterOptions().setHasText("Next"));
              assertThat(nextButton).isVisible();
              nextButton.click();
        System.out.println("First Next clicked successfully");
    }
    public void verifyNextbtnPage() {

        System.out.println("Verifying next page...");

        Locator heading = page.locator(
                "h2:text-is('Consignor Addresses (Pickup Locations)')"
        );
        System.out.println("Heading count: " + heading.count());
        assertThat(heading).isVisible();
        System.out.println("Next page is visible");
    }
    public void clickSecondNextbtn() {

        System.out.println("Clicking second Next...");

        Locator nextButtons = page.getByRole(
                AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next"));
        Locator nextButton = nextButtons.last();
        assertThat(nextButton).isVisible();
        nextButton.click();
        System.out.println("Consigee Addresses (Delivery Locations) page is visible");
    }
    public void clickUpdateCustomer() {

        Locator updateButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Update Customer")
        );

        assertThat(updateButton).isVisible();
        updateButton.click();

        System.out.println("Update Customer clicked successfully");
    }
}
