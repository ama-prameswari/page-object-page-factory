package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.LoginPage;
import com.juaracoding.pages.RecruitmentPage;
import com.juaracoding.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RecruitmentTest {

    private WebDriver driver;

    private LoginPage loginPage;
    private RecruitmentPage recruitmentPage;

    @BeforeClass
    public void setUp(){
        // Initialize Driver and Pages
        DriverSingleton.getInstance("firefox");
        driver = DriverSingleton.getDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Initialize Page Objects
        loginPage = new LoginPage();
        recruitmentPage = new RecruitmentPage();
    }

    @AfterClass
    public void finish(){
        Utils.delay(5);
        DriverSingleton.closeObjectInstance();
    }

    @Test
    public void testAddCandidate(){
        // step pre-condition:
        loginPage.loginUser("Admin", "admin123"); //Step 1: Login as Admin

        // step action:
        recruitmentPage.setMenuRecruitment(); // Step 2: Navigate to Recruitment Menu
        recruitmentPage.addCandidate(); // Step 3: Add a Candidate

        // step validation: assert add candidate
        // Step 4: Validate the Application Stage and Candidate Name
        Assert.assertEquals(recruitmentPage.getTxtApplicationStage(), "Application Stage");
        Assert.assertEquals(recruitmentPage.getTxtFirstValidation(), "Juara Coding");
    }

}
