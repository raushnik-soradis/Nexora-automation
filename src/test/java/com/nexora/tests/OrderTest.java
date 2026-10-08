package com.nexora.tests;

import com.nexora.pages.OrderPage;
import org.testng.annotations.Test;

public class OrderTest extends BaseTest {

    @Test
    public void verifyOrderManagementPage() {
        login();
        OrderPage orderPage = dashboardPage.goToOrderPage();
        orderPage.VerifyOrderManagementPage();
        orderPage.clickManageOrders();
        orderPage.exportOrders();
        orderPage.searchOrder("521385580825");
//        orderPage.verifyOrder("521385580825");
        orderPage.viewOrder("451134046717");
        orderPage.closeViewDetails();

        orderPage.editOrder("451134046717");



    }
    @Test
    public void createNewOrder() {
        login();
        OrderPage orderPage = dashboardPage.goToOrderPage();
        orderPage.VerifyOrderManagementPage();
        orderPage.clickManageOrders();
        orderPage.clickCreateOrder();
        orderPage.enterCustomer("Fair Exports India Private Limited");
        orderPage.selectCustomer("Fair Exports India Private Limited");
        orderPage.clickNext();
        //orderPage.clickCustomerDetailsNext();
        orderPage.clickPickupDateTime();
       orderPage.selectPickupDateTime();
       orderPage.verifyPickupDateTime("17/10/2026 12:00 AM");
        orderPage.enterVehicleNumber("MH-12-AB-1234");
        orderPage.enterDriverName("Rahul Kumar");
        orderPage.clickAddProduct();
        orderPage.searchProduct("Al Taman");
        orderPage.selectProduct("Al Taman");
        orderPage.selectVariant();
        orderPage.enterHSNNumber("123456");
        orderPage.selectBatchDate();
        orderPage.selectBatchDateValue();
        orderPage.verifyBatchDate("15/10/2026");
        orderPage.selectExpiryDate();
        orderPage.verifyExpiryDate("31/10/2026");
        orderPage.selectBoxes(2);
        orderPage.verifyBoxes(2);
        orderPage.selectItemsPerBox(10);
        orderPage.verifyItemsPerBox(10);
        orderPage.enterUnit("KG");
        orderPage.enterBasePrice("250");
        orderPage.verifyBasePrice("250");
        orderPage.verifyTotalPrice(2, 10, 250);

//        String response = orderPage.createOrder();
        //orderPage.createOrder();
        String response = orderPage.createOrder();

        System.out.println("CREATE ORDER RESPONSE:");
        System.out.println(response);
        orderPage.verifyOrderCreatedSuccessfully();
        orderPage.viewOrder("451134046717");
        orderPage.editOrder("451134046717");
    }


    @Test
    public void generateReport() {

        login();

        OrderPage orderPage = dashboardPage.goToOrderPage();

        orderPage.VerifyOrderManagementPage();

        orderPage.clickManageOrders();

        orderPage.clickGenerateReport();
        orderPage.verifyGenerateReportPage();
        orderPage.selectInventorySummaryReport();
        orderPage.clickNextSetFilters();
        orderPage.enterStartDate("01/10/2026");
        orderPage.enterEndDate("08/10/2026");
        orderPage.clickGenerateReportFromFilters();
        orderPage.verifyReportGeneratedPage();
        orderPage.downloadExcelReport();
    }
}