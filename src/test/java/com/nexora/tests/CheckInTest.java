package com.nexora.tests;

import com.nexora.pages.CheckInPage;
import com.nexora.pages.LoginPage;
import com.nexora.pages.utils.ConfigReader;
import org.testng.annotations.Test;

public class CheckInTest extends BaseTest {

        @Test
        public void verifyCheckInPopup() {

            LoginPage loginPage = new LoginPage(page);

            loginPage.loginToApplication(
                    ConfigReader.getProperty("username"),
                    ConfigReader.getProperty("password")
            );

            CheckInPage checkInPage = new CheckInPage(page);

            if (checkInPage.isCheckInDisplayed()) {

                checkInPage.verifySkipButton();
                checkInPage.clickSkip();
            }
        }
//        @Test
//    public void verifyCheckIn() {
//
//        LoginPage loginPage = new LoginPage(page);
//
//        loginPage.loginToApplication(
//                ConfigReader.getProperty("username"),
//                ConfigReader.getProperty("password")
//        );
//
//        CheckInPage checkInPage = new CheckInPage(page);
//
//        if (checkInPage.isCheckInDisplayed()) {
//
//            checkInPage.verifyCheckInButton();
//            checkInPage.clickCheckIn();
//        }
//    }
@Test
public void verifyCheckIn() {

    LoginPage loginPage = new LoginPage(page);

    loginPage.loginToApplication(
            ConfigReader.getProperty("username"),
            ConfigReader.getProperty("password")
    );

    CheckInPage checkInPage = new CheckInPage(page);

    boolean checkInDisplayed = checkInPage.isCheckInDisplayed();

    System.out.println("Check-In Popup Displayed: " + checkInDisplayed);

    if (checkInDisplayed) {

        checkInPage.verifyCheckInButton();
        checkInPage.clickCheckIn();
    }
}
}
