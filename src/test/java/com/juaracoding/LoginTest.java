package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.LoginPage;
import com.juaracoding.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

    private WebDriver driver;

    private LoginPage loginPage;

    @BeforeClass
    public void setUp(){
        DriverSingleton.getInstance("firefox");
        driver = DriverSingleton.getDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage();
    }

    @AfterClass
    public void finish(){
        Utils.delay(5);
        DriverSingleton.closeObjectInstance();
    }

    // positive test
    @Test(priority = 1)
    public void testDisplayImgCompanyBranding(){
        Utils.delay(3);
        Assert.assertTrue(loginPage.getImgCompanyBranding());
    }

    @Test(priority = 3)
    public void testValidLogin(){
        loginPage.loginUser("Admin", "admin123");
        Assert.assertEquals(loginPage.getTxtDashboard(), "Dashboard");
    }

    // negative test
    @Test(priority = 2)
    public void testInvalidLogin(){
        loginPage.loginUser("Invalid", "invalid");
        Assert.assertEquals(loginPage.getTxtInvalid(), "Invalid credentials");
    }

}
