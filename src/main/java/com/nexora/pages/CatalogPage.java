package com.nexora.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CatalogPage {
    Page page;
    public CatalogPage(Page page) {
        this.page = page;
    }
    public void verifyCatalogPage() {

        assertThat(
                page.getByText("Catalogs", new Page.GetByTextOptions().setExact(true))).isVisible();
    }
    public void clickView() {

        Locator viewButton = page.locator("button").filter(
                new Locator.FilterOptions().setHas(page.locator("svg path[d*='M1 12s4-8 11-8']"))).first();

        assertThat(viewButton).isVisible();
        assertThat(viewButton).isEnabled();

        viewButton.click();
    }
    public void clickEdit(String customerName) {

        System.out.println("Clicking Edit button for the first catalog...");
        Locator catalogRow = page.locator("tr.ant-table-row").filter(
                new Locator.FilterOptions().setHasText(customerName)
        );
        assertThat(catalogRow).isVisible();
        Locator editButton = page.locator("button").filter(
                new Locator.FilterOptions().setHas(
                        page.locator("svg path[d*='M11 4H4']")
                )
        ).first();

        assertThat(editButton).isVisible();
        assertThat(editButton).isEnabled();

        editButton.click();
    }
    public void clickNext() {

        Locator nextButton = page.getByLabel("Edit CatalogUpdate catalog")
                .getByRole(
                        AriaRole.BUTTON,
                        new Locator.GetByRoleOptions().setName("Next")
                );

        assertThat(nextButton).isVisible();
        assertThat(nextButton).isEnabled();

        nextButton.click();
    }
    public void editVariantWeight(String variantName, String newWeight) {

        Locator variantRow = page.locator(
                "div.grid.grid-cols-\\[24px_1fr_1fr_1fr_1fr_60px_150px_110px_32px\\]"
        ).filter(
                new Locator.FilterOptions().setHas(
                        page.locator("input[value='" + variantName + "']")
                )
        ).first();

        assertThat(variantRow).isVisible();
        Locator weightInput = variantRow.locator(
                "input[placeholder='e.g. 500g']"
        );

        assertThat(weightInput).isVisible();
        assertThat(weightInput).isEnabled();


        String oldWeight = weightInput.inputValue();

        weightInput.fill(newWeight);

        assertThat(weightInput).hasValue(newWeight);

        System.out.println(
                "Weight updated successfully for " + variantName +
                        ": " + oldWeight + " → " + newWeight
        );
    }
    public void clickSaveCatalog() {

        Locator saveCatalogButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Save Catalog")
        );

        assertThat(saveCatalogButton).isVisible();
        assertThat(saveCatalogButton).isEnabled();

        saveCatalogButton.click();
    }
}
