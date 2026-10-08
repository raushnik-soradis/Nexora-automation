package com.nexora.tests;

import com.nexora.pages.CatalogPage;
import org.testng.annotations.Test;

public class CatalogTest extends BaseTest {
    @Test
    public void verifyCatalogPage() {
        // Login
        login();
        dashboardPage.goToCatalogPage();

        // Verify Catalog Page
        CatalogPage catalogPage = dashboardPage.goToCatalogPage();
        catalogPage.verifyCatalogPage();
        catalogPage.clickView();
    }
    @Test
    public void clickEditCatalog() {
        // Login
        login();

        // Navigate to Catalogs
        dashboardPage.goToCatalogPage();

        // Click Edit on the first catalog
        CatalogPage catalogPage = dashboardPage.goToCatalogPage();
        catalogPage.clickEdit("Fair Exports India Private Limited");
        catalogPage.clickNext();
        catalogPage.editVariantWeight("Topside-41A", "1600");
        catalogPage.clickSaveCatalog();
    }

    @Test
    public void editCatalogVariantWeight() {

        login();

        CatalogPage catalogPage = dashboardPage.goToCatalogPage();

        // Open catalog
        catalogPage.clickEdit("Fair Exports India Private Limited");

        // Go to Product Details / Variants
        catalogPage.clickNext();

        // Change weight
        catalogPage.editVariantWeight("Topside-41A", "1500");

        // Save
        catalogPage.clickSaveCatalog();

        // After save, application goes to /orders
        CatalogPage catalogPageAfterSave = dashboardPage.goToCatalogPage();

        // Reopen the same catalog
        catalogPageAfterSave.clickEdit("Fair Exports India Private Limited");

        // Go to variants
        catalogPageAfterSave.clickNext();

        // Verify saved weight
        catalogPageAfterSave.verifyVariantWeight("Topside-41A", "1500");
    }
}
