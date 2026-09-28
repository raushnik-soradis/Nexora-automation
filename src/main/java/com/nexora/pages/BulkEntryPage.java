package com.nexora.pages;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BulkEntryPage {
    private final Page page;

    public BulkEntryPage(Page page) {
        this.page = page;
    }

    public void verifyBulkEntryPage() {
        Locator bulkEntryHeading = page.getByText(
                "Bulk Entry",
                new Page.GetByTextOptions().setExact(true));
        assertThat(bulkEntryHeading).isVisible();
        System.out.println("Bulk Entry page is visible");
    }
    public void verifyDownloadTemplateSection() {

        Locator downloadTemplateHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Download Template")
                        .setExact(true));
        assertThat(downloadTemplateHeading).isVisible();
        System.out.println("Download Template heading is visible");
    }
    public void downloadTemplate() {

        Locator downloadTemplate =
                page.locator("a[download='BulkCustomerTemplate']");

        assertThat(downloadTemplate).isVisible();

        System.out.println("Download Template link is visible");
        System.out.println("Starting download...");
        Download download = page.waitForDownload(downloadTemplate::click);
        System.out.println("Download event received");
        String fileName = download.suggestedFilename();
        System.out.println("Suggested filename: " + fileName);

        Path downloadFolder = Paths.get(System.getProperty("user.dir"), "downloads");

        try {
            Files.createDirectories(downloadFolder);
            Path downloadPath = downloadFolder.resolve(fileName);
            System.out.println("Saving file to: " + downloadPath.toAbsolutePath());
            download.saveAs(downloadPath);
            System.out.println("File exists: " + Files.exists(downloadPath));
            System.out.println("File size: " + Files.size(downloadPath) + " bytes");
            if (!Files.exists(downloadPath)) {
                throw new AssertionError("Downloaded file was not found: " + downloadPath.toAbsolutePath());
            }
            System.out.println("Download completed successfully: " + downloadPath.toAbsolutePath());

        } catch (Exception e) {
            throw new RuntimeException("Failed to save downloaded file", e);
        }
    }
    public void uploadCustomerFile() {

        Path filePath = Paths.get(
                System.getProperty("user.dir"), "downloads", "BulkCustomerTemplate.xlsx");

        if (!Files.exists(filePath)) {
            throw new AssertionError("Upload file not found: " + filePath.toAbsolutePath());
        }

        Locator fileInput = page.locator("input[type='file'][name='file']");

        fileInput.setInputFiles(filePath);

        System.out.println("File uploaded successfully: " + filePath.toAbsolutePath());
    }
    public void verifyUploadedFile() {

        Locator uploadedFile = page.getByText(
                "BulkCustomerTemplate.xlsx",
                new Page.GetByTextOptions().setExact(true)
        );

        assertThat(uploadedFile).isVisible();

        System.out.println("Uploaded file is visible");
    }

}
