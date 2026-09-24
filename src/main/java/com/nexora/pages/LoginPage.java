package com.nexora.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {
    private final Page page;
        String base_url;
    private static final String input_placeHolder = "e.g. admin123";
    private static final String input_placeHolder_label = "Enter your password";


    public LoginPage(Page page) {
        this.page = page;
    }
   public DashboardPage loginToApplication(String username, String password)
   {
     // page.navigate(base_url);
      System.out.println(page.title());
       System.out.println("Before login URL: " + page.url());
       // Fills the username in <input name="user"> field
       page.locator("input[name='user']").fill(username);

       // Select Gmail domain
       page.locator("select").selectOption("@gmail.com");
       // Fills the password field by placeholder
       page.getByPlaceholder(input_placeHolder_label).fill(password);

       // Clicks the 'Log in' button
       page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log in")).click();
       page.waitForTimeout(2000);

       page.waitForLoadState();

       System.out.println("After login URL: " + page.url());
       System.out.println("After login title: " + page.title());
       System.out.println("After login text:");
       System.out.println("Email suggestions:");

       System.out.println(page.locator("body").innerText());
       //DashboardPage dashboardPage = new DashboardPage(page);
       return new DashboardPage(page);
   }



   }
















