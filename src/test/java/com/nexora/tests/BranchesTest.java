package com.nexora.tests;

import com.nexora.pages.BranchesPage;
import org.testng.annotations.Test;

public class BranchesTest extends BaseTest {
    @Test
    public void verifyBranchesPage() {
        login();

        BranchesPage branchPage = new BranchesPage(page);

        branchPage.navigateToBranchesPage();
        branchPage.verifyAddBranchButton();
        branchPage.verifyExistingBranch("Toloja Warehouse");
        branchPage.verifyExistingBranch("Western Superfresh");
        branchPage.verifyExistingBranch("Nerul Warehouse");
        //  branchPage.clickViewBranch("Western Superfresh");
        branchPage.clickEditBranch("Western Superfresh");

        branchPage.updateBranchPincode("410206");
    }
        @Test
        public void AddNewBranch() {
            login();
            BranchesPage branchPage = new BranchesPage(page);

            branchPage.navigateToBranchesPage();
            branchPage.clickAddBranch();
            branchPage.enterBranchName("Delhi New Branch");
            branchPage.selectOfficeLocationType();
            branchPage.enterBranchAddress("Delhi, India");
            branchPage.enterBranchPincode("410001");

            branchPage.clickCreateBranch();


        }
}
