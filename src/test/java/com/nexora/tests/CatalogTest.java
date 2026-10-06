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
}
