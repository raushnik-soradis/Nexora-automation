package com.nexora.pages;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;

import com.microsoft.playwright.Page;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ManifestPage {
    private final Page page;

    public ManifestPage(Page page) {
        this.page = page;
    }
    public void VerifyManifestPage() {
        assertThat(
                page.getByText("Manifest", new Page.GetByTextOptions().setExact(true))
        ).isVisible();

    }
    public void clickView() {

        Locator viewButton = page.locator("button[title='View']").first();

        assertThat(viewButton).isVisible();
        assertThat(viewButton).isEnabled();

        viewButton.click();
    }
    public void downloadManifest() {

        Locator downloadButton = page.locator("button[title='Download']").first();

        assertThat(downloadButton).isVisible();
        assertThat(downloadButton).isEnabled();

        Download download = page.waitForDownload(() -> {
            downloadButton.click();
        });

        Path downloadPath = Paths.get(
                "downloads",
                download.suggestedFilename()
        );

        download.saveAs(downloadPath);

        System.out.println(
                "Manifest downloaded successfully: " + downloadPath
        );
    }

    // Edit first Manifest
    public void clickEdit() {

        Locator editButton = page.locator("button[title='Edit']").first();

        assertThat(editButton).isVisible();
        assertThat(editButton).isEnabled();

        editButton.click();
    }
}
