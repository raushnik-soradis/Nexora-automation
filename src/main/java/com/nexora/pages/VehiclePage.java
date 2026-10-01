package com.nexora.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class VehiclePage  {

    Page page;
    public VehiclePage(Page page) {

        this.page = page;

    }

    public void verifyVehiclePage() {

        assertThat(
                page.getByText("Vehicles", new Page.GetByTextOptions().setExact(true))
        ).isVisible();
    }
    public void clickBulkEntry() {

        Locator bulkEntryButton = page.locator("button").filter(
                new Locator.FilterOptions().setHasText("Bulk Entry")
        );

        assertThat(bulkEntryButton).isVisible();

        bulkEntryButton.click();
    }

    public AddVehicle clickAddVehicle() {

        Locator addVehicleButton = page.locator("button").filter(
                new Locator.FilterOptions().setHasText("Add Vehicle")
        );

        assertThat(addVehicleButton).isVisible();

        addVehicleButton.click();

        return new AddVehicle(page);
    }


}














