package com.nexora.pages;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class OrderPage {
    Page page;
    Locator orderManagementHeading;
    Locator manageOrdersButton;
    Locator searchOrderInput;
    Locator customerNameInput;
    Locator nextButton;
    Locator pickupDateTimeInput;
    Locator vehicleNumberInput;
    Locator driverNameInput;
    Locator addProductButton;
    Locator productSearchInput;
    Locator variantDropdown;
    Locator variantOption;
    Locator hsnNumberInput;
    Locator batchDateInput;
    Locator expiryDateInput;
    Locator unitInput;
    Locator basePriceInput;
    Locator totalPrice;
    Locator createOrderButton;
    Locator exportButton;
    Locator startDateInput;
    Locator endDateInput;
    Locator generateReportButton;


    public OrderPage(Page page) {
        this.page = page;
        orderManagementHeading = page.getByText(
                "Order Analytics",
                new Page.GetByTextOptions().setExact(true)
        );

        manageOrdersButton = page.getByText(
                "Manage Orders",
                new Page.GetByTextOptions().setExact(true)
        );

        searchOrderInput = page.getByPlaceholder(
                "Search by Order Number..."
        );
        customerNameInput = page.locator("input[name='customerName']");

        nextButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Next")
        );
        pickupDateTimeInput = page.getByPlaceholder("Select date & time");
        vehicleNumberInput = page.locator("input[name='vehicleNumber']");
        driverNameInput = page.locator("input[name='driverName']");
        addProductButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Add Product").setExact(true));
        productSearchInput = page.getByPlaceholder("Search Products...");
        variantOption = page.getByText(
                "Topside-41A · 1500",
                new Page.GetByTextOptions().setExact(true)
        );

        variantDropdown = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Select variant").setExact(true)
        );
        hsnNumberInput = page.getByPlaceholder("HSN Number");
        batchDateInput = page.getByPlaceholder("Select batch date");
        expiryDateInput = page.getByPlaceholder("Select expiry date");
        unitInput = page.getByPlaceholder("Unit");
        basePriceInput = page.getByPlaceholder("250");
        totalPrice = page.locator(
                "div.px-1\\.5.py-1\\.5.border-r.border-zinc-100.text-center"
        ).last();
        createOrderButton = page.locator("button[type='submit']")
                .filter(new Locator.FilterOptions().setHasText("Create Order"));

        exportButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions()
                        .setName("Export")
                        .setExact(true)
        );
        startDateInput = page.locator("input[date-range='start']").first();
        endDateInput = page.locator("input[date-range='end']").first();



    }


    public void VerifyOrderManagementPage() {
        // Verify the Order page is displayed
        Locator heading = page.getByText("Order Analytics", new Page.GetByTextOptions().setExact(true));
        assertThat(heading).isVisible();
        System.out.println("Order Management page is displayed successfully.");

    }

    public void clickManageOrders() {

        manageOrdersButton.click();

        System.out.println("Clicked on Manage Orders.");
    }

    public void searchOrder(String orderNumber) {

        searchOrderInput.fill(orderNumber);

        System.out.println("Entered Order Number: " + orderNumber);
    }

    public void verifyOrder(String orderNumber) {

        Locator order = page.getByText(
                orderNumber,
                new Page.GetByTextOptions().setExact(true)
        );

        assertThat(order).isVisible();

        System.out.println("Order verified successfully: " + orderNumber);
    }

    public void clickCreateOrder() {
        Locator createOrderButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Create Order")
        );

        assertThat(createOrderButton).isVisible();
        createOrderButton.click();

        System.out.println("Clicked on Create Order.");
    }

    public void enterCustomer(String customerName) {
        customerNameInput.fill(customerName);
        System.out.println("Entered Customer: " + customerName);
    }

    public void selectCustomer(String customerName) {
        Locator customerOption = page.locator("div.font-medium")
                .filter(new Locator.FilterOptions().setHasText(customerName));
        assertThat(customerOption).isVisible();
        customerOption.click();

        System.out.println("Selected Customer: " + customerName);
    }

    public void clickNext() {

        Locator nextButton = page.locator(
                "button[type='submit']"
        );

        assertThat(nextButton).isEnabled();
        nextButton.click();

        System.out.println("Clicked Next button.");
    }

    public void clickCustomerDetailsNext() {

        Locator nextButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Next").setExact(true)).last();
        assertThat(nextButton).isVisible();
        assertThat(nextButton).isEnabled();
        nextButton.click();
        System.out.println("Clicked Next button.");
    }

    public void clickPickupDateTime() {

        Locator pickupDateTime = page.locator("input[placeholder='Select date & time']");

        assertThat(pickupDateTime).isVisible();
        assertThat(pickupDateTime).isEnabled();
        pickupDateTime.click();
        System.out.println("Pickup Date & Time field clicked successfully.");
    }

    public void selectPickupDateTime() {

        // Select date: 17 October 2026
        Locator date = page.locator(
                "td.ant-picker-cell[title='2026-10-17']");
        assertThat(date).isVisible();
        date.click();
        System.out.println("Selected Pickup Date: 17/10/2026");

        // Select Hour: 12
        Locator hour = page.locator(
                ".ant-picker-time-panel-column").nth(0).locator(
                "li.ant-picker-time-panel-cell[data-value='0']");

        assertThat(hour).isVisible();
        hour.click();
        System.out.println("Selected Pickup Hour: 12");


        // Select Minute: 00
        Locator minute = page.locator(
                ".ant-picker-time-panel-column"
        ).nth(1).locator(
                "li.ant-picker-time-panel-cell[data-value='0']"
        );

        assertThat(minute).isVisible();
        minute.click();
        System.out.println("Selected Pickup Minute: 00");


        // Select AM
        Locator am = page.locator(
                ".ant-picker-time-panel-column").nth(2).locator(
                "li.ant-picker-time-panel-cell[data-value='am']");

        assertThat(am).isVisible();
        am.click();
        System.out.println("Selected AM");

        // Click OK
        Locator okButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("OK"));

        assertThat(okButton).isVisible();
        okButton.click();

        System.out.println(
                "Pickup Date & Time selected: 17/10/2026 12:00 AM");
    }

    public void verifyPickupDateTime(String expectedDateTime) {

        assertThat(pickupDateTimeInput).hasValue(expectedDateTime);

        System.out.println("Pickup Date & Time selected successfully: " + expectedDateTime);
    }

    public void enterVehicleNumber(String vehicleNumber) {
        assertThat(vehicleNumberInput).isVisible();
        assertThat(vehicleNumberInput).isEnabled();

        vehicleNumberInput.fill(vehicleNumber);

        System.out.println("Entered Vehicle Number: " + vehicleNumber);
    }

    public void enterDriverName(String driverName) {

        assertThat(driverNameInput).isVisible();
        assertThat(driverNameInput).isEnabled();

        driverNameInput.fill(driverName);

        System.out.println("Entered Driver Name: " + driverName);
    }

    public void clickAddProduct() {

        assertThat(addProductButton).isVisible();
        assertThat(addProductButton).isEnabled();

        addProductButton.click();

        System.out.println("Clicked on Add Product.");
    }

    public void searchProduct(String productName) {

        assertThat(productSearchInput).isVisible();
        assertThat(productSearchInput).isEnabled();

        productSearchInput.fill(productName);

        System.out.println("Searched Product: " + productName);
    }

    public void selectProduct(String productName) {

        Locator product = page.getByText(
                productName,
                new Page.GetByTextOptions().setExact(true)
        );

        assertThat(product).isVisible();
        product.click();

        System.out.println("Selected Product: " + productName);
    }


    public void selectVariant() {

        assertThat(variantDropdown).isVisible();
        assertThat(variantDropdown).isEnabled();
        variantDropdown.click();
        System.out.println("Clicked on Select Variant.");
        // Select variant
        assertThat(variantOption).isVisible();
        variantOption.click();

        System.out.println("Selected Variant: Topside-41A · 1500");
    }

    public void enterHSNNumber(String hsnNumber) {

        assertThat(hsnNumberInput).isVisible();
        assertThat(hsnNumberInput).isEnabled();

        hsnNumberInput.fill(hsnNumber);

        System.out.println("Entered HSN Number: " + hsnNumber);
    }

    public void selectBatchDate() {

        assertThat(batchDateInput).isVisible();
        assertThat(batchDateInput).isEnabled();
        batchDateInput.click();
        System.out.println("Clicked on Batch Date.");
    }
    public void selectBatchDateValue() {
        Locator batchDate = page.locator(
                "td.ant-picker-cell[title='2026-10-15']:visible"
        );

        assertThat(batchDate).isVisible();
        batchDate.click();

        System.out.println("Selected Batch Date: 15/10/2026");
    }
    public void verifyBatchDate(String expectedDate) {
        assertThat(batchDateInput).hasValue(expectedDate);
        System.out.println(
                "Batch Date selected successfully: " + expectedDate
        );
    }

    public void selectExpiryDate() {

        assertThat(expiryDateInput).isVisible();
        assertThat(expiryDateInput).isEnabled();

        expiryDateInput.click();

        System.out.println("Clicked on Expiry Date.");

        Locator expiryDate = page.locator(
                "td.ant-picker-cell[title='2026-10-31']:visible"
        ).last();

        assertThat(expiryDate).isVisible();

        expiryDate.click();

        System.out.println("Selected Expiry Date: 31/10/2026");
    }

    public void verifyExpiryDate(String expectedDate) {

        assertThat(expiryDateInput).hasValue(expectedDate);

        System.out.println("Expiry Date selected successfully: " + expectedDate);
    }

    public void selectBoxes(int boxes) {
        Locator boxesInput = page.locator("input[type='number']").nth(0);

        assertThat(boxesInput).isVisible();
        assertThat(boxesInput).isEnabled();

        boxesInput.fill(String.valueOf(boxes));

        System.out.println("Boxes selected: " + boxes);
    }

    public void verifyBoxes(int expectedBoxes) {
        Locator boxesInput = page.locator("input[type='number']").nth(0);

        assertThat(boxesInput).hasValue(String.valueOf(expectedBoxes));

        System.out.println("Boxes verified successfully: " + expectedBoxes);
    }

    public void selectItemsPerBox(int items) {
        Locator itemsPerBoxInput = page.locator("input[type='number']").nth(1);

        assertThat(itemsPerBoxInput).isVisible();
        assertThat(itemsPerBoxInput).isEnabled();

        itemsPerBoxInput.fill(String.valueOf(items));

        System.out.println("Items / Box selected: " + items);
    }

    public void verifyItemsPerBox(int expectedItems) {
        Locator itemsPerBoxInput = page.locator("input[type='number']").nth(1);

        assertThat(itemsPerBoxInput).hasValue(String.valueOf(expectedItems));

        System.out.println("Items / Box verified successfully: " + expectedItems);
    }

    public void enterUnit(String unit) {
        assertThat(unitInput).isVisible();
        assertThat(unitInput).isEnabled();

        unitInput.fill(unit);

        System.out.println("Entered Unit: " + unit);
    }

    public void enterBasePrice(String price) {
        assertThat(basePriceInput).isVisible();
        assertThat(basePriceInput).isEnabled();

        basePriceInput.fill(price);

        System.out.println("Entered Base ₹/ps: " + price);
    }

    public void verifyBasePrice(String expectedPrice) {
        assertThat(basePriceInput).hasValue(expectedPrice);

        System.out.println("Base ₹/ps verified successfully: " + expectedPrice);
    }

    public void verifyTotalPrice(int boxes, int itemsPerBox, double basePrice) {

        double expectedTotal = boxes * itemsPerBox * basePrice;

        assertThat(totalPrice).isVisible();

        String actualTotal = totalPrice.textContent().trim();

        String expectedAmount = String.format("%.2f", expectedTotal);

        if (!actualTotal.contains(expectedAmount)) {
            throw new AssertionError(
                    "Total ₹ mismatch: expected " + expectedAmount + ", but found " + actualTotal
            );
        }
        System.out.println(
                "Total ₹ calculated successfully: " + actualTotal
        );
    }

    public String createOrder() {

        assertThat(createOrderButton).isVisible();
        assertThat(createOrderButton).isEnabled();

        Response response = page.waitForResponse(
                res ->
                        res.request().method().equals("POST")
                                && res.url().equals(
                                "https://warehouseapi.nexorabysoradis.com/api/order"
                        ),
                () -> {
                    createOrderButton.click();
                    System.out.println("Clicked on Create Order.");
                }
        );

        System.out.println("========== CREATE ORDER API ==========");
        System.out.println("Status: " + response.status());
        System.out.println("URL: " + response.url());
        System.out.println("Response Body:");
        System.out.println(response.text());
        System.out.println("======================================");

        page.waitForURL("**/orders/manage-orders");

        System.out.println("Returned to Order Management page.");

        return response.text();
    }

    public void verifyOrderCreatedSuccessfully() {
        page.waitForURL("**/orders/manage-orders");

        assertThat(page.getByText(
                "Order Management",
                new Page.GetByTextOptions().setExact(true)
        )).isVisible();

        System.out.println(
                "Order created successfully and redirected to Order Management."
        );
    }
    public void viewOrder(String orderNumber) {

        Locator orderRow = page.locator("tr")
                .filter(new Locator.FilterOptions()
                        .setHasText(orderNumber));

        assertThat(orderRow).isVisible();

        Locator viewButton = orderRow.locator("button")
                .filter(new Locator.FilterOptions()
                        .setHas(page.locator(
                                "svg path[d=\"M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z\"]"
                        )));

        assertThat(viewButton).isVisible();
        assertThat(viewButton).isEnabled();

        viewButton.click();

        System.out.println("Clicked View for Order: " + orderNumber);
    }

    public void closeViewDetails() {

        Locator closeButton = page.locator(
                "button:has(svg line[x1='18'][y1='6'][x2='6'][y2='18'])"
        );

        assertThat(closeButton).isVisible();
        assertThat(closeButton).isEnabled();

        closeButton.click();

        System.out.println("Closed View Details successfully.");
    }
    public void editOrder(String orderNumber) {

        Locator orderRow = page.locator("tr")
                .filter(new Locator.FilterOptions()
                        .setHasText(orderNumber));

        assertThat(orderRow).isVisible();

        Locator editButton = orderRow.locator("button")
                .filter(new Locator.FilterOptions()
                        .setHas(page.locator(
                                "svg path[d^=\"M11 4H4\"]"
                        )));

        assertThat(editButton).isVisible();
        assertThat(editButton).isEnabled();

        editButton.click();

        System.out.println("Clicked Edit for Order: " + orderNumber);
    }

 public void exportOrders() {

        assertThat(exportButton).isVisible();
        assertThat(exportButton).isEnabled();

     System.out.println("Export button is displayed successfully.");

     Download download = page.waitForDownload(() -> {
         exportButton.click();
     });

     System.out.println("Clicked Export button.");

     System.out.println("Downloaded file name: " + download.suggestedFilename());

     download.saveAs(
             Paths.get("downloads", download.suggestedFilename())
     );

     System.out.println(
             "File downloaded successfully: " + download.suggestedFilename()
     );
    }

    public void clickGenerateReport() {

        Locator generateReportButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions()
                        .setName("Generate Report")
                        .setExact(true)
        );

        assertThat(generateReportButton).isVisible();
        assertThat(generateReportButton).isEnabled();

        generateReportButton.click();

        System.out.println("Clicked Generate Report.");
    }
    public void verifyGenerateReportPage() {

        Locator heading = page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions()
                        .setName("Generate Report")
                        .setExact(true)
        );

        assertThat(heading).isVisible();

        assertThat(page.getByText(
                "Choose the type of report you want to export",
                new Page.GetByTextOptions().setExact(true)
        )).isVisible();

        System.out.println("Generate Report page is displayed successfully.");
    }
    public void selectInventorySummaryReport() {

        Locator reportType = page.getByText(
                "Inventory Summary",
                new Page.GetByTextOptions().setExact(true)
        );

        assertThat(reportType).isVisible();
        reportType.click();

        System.out.println("Selected Report Type: Inventory Summary");
    }
    public void clickNextSetFilters() {

        Locator nextButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions()
                        .setName("Next — Set Filters")
                        .setExact(true)
        );

        assertThat(nextButton).isVisible();
        assertThat(nextButton).isEnabled();

        nextButton.click();

        System.out.println("Clicked Next — Set Filters.");
    }
    public void enterStartDate(String date) {
        assertThat(startDateInput).isVisible();
        assertThat(startDateInput).isEnabled();

        startDateInput.fill(date);

        System.out.println("Entered Start Date: " + date);
    }

    public void enterEndDate(String date) {
        assertThat(endDateInput).isVisible();
        assertThat(endDateInput).isEnabled();

        endDateInput.fill(date);

        System.out.println("Entered End Date: " + date);
    }
    public void clickGenerateReportFromFilters() {

        Locator generateReportButton = page
                .getByLabel("Generate ReportConfigure")
                .getByRole(
                        AriaRole.BUTTON,
                        new Locator.GetByRoleOptions()
                                .setName("Generate Report")
                                .setExact(true)
                );

        assertThat(generateReportButton).isVisible();
        assertThat(generateReportButton).isEnabled();
             generateReportButton.click();

        System.out.println("Clicked Generate Report from Filters.");

    }

    public void verifyReportGeneratedPage() {

        Locator heading = page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions()
                        .setName("Generate Report")
                        .setExact(true)
        );

        assertThat(heading).isVisible();

        assertThat(page.getByText(
                "Your report is ready to download",
                new Page.GetByTextOptions().setExact(true)
        )).isVisible();

        assertThat(page.getByText(
                "Report Generated",
                new Page.GetByTextOptions().setExact(true)
        )).isVisible();

        System.out.println("Report Generated page is displayed successfully.");
    }

    public void downloadExcelReport() {

        Locator downloadExcelButton = page.getByRole(
                    AriaRole.BUTTON,
                    new Page.GetByRoleOptions()
                            .setName("Download Excel")
                            .setExact(true)
            );

            assertThat(downloadExcelButton).isVisible();
            assertThat(downloadExcelButton).isEnabled();

            Download download = page.waitForDownload(() -> {
                downloadExcelButton.click();
            });

            System.out.println("Clicked Download Excel.");
            System.out.println("Downloaded file name: " + download.suggestedFilename());

            download.saveAs(Paths.get(
                    "downloads",
                    download.suggestedFilename()
            ));

            System.out.println("Excel report downloaded successfully.");

        }

}






