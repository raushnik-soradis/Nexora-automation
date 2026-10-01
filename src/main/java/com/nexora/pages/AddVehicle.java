package com.nexora.pages;


import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.Locator;
public class AddVehicle {

        Page page;

        public AddVehicle(Page page) {
            this.page = page;
        }

        public void verifyAddNewVehiclePage() {
            assertThat(
                    page.getByText(
                            "Add New Vehicle",
                            new Page.GetByTextOptions().setExact(true)
                    )
            ).isVisible();
        }
    public void enterRegistrationNumber(String registrationNumber) {

        Locator registrationNumberField =
                page.locator("input[name='registrationNumber']");

        assertThat(registrationNumberField).isVisible();

        registrationNumberField.fill(registrationNumber);
    }
    }
