package com.nexora.tests;


import com.nexora.pages.ManifestPage;
import org.testng.annotations.Test;



public class ManifestTest extends BaseTest
{
  @Test
    public void VerifyManifestPage() {

        System.out.println("========== Manifest Test Started ==========");

        login();
      ManifestPage manifestPage = dashboardPage.goToManifestPage();
      manifestPage.VerifyManifestPage();
      manifestPage.clickView();

    }
        // Download first Manifest
    @Test
    public void downloadManifest() {
        System.out.println("========== Manifest Download Test Started ==========");
        login();
        ManifestPage manifestPage = dashboardPage.goToManifestPage();
        manifestPage.downloadManifest();
    }
//    @Test
//    public void clickEditManifest() {
//        System.out.println("========== Manifest Edit Test Started ==========");
//        login();
//        ManifestPage manifestPage = dashboardPage.goToManifestPage();
//        manifestPage.clickEdit();
//    }

}

