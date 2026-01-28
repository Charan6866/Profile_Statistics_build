package listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
//import java.net.URL;

//Extent report 5.x...//version

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import testCases.BaseTest;



public class ExtentManager extends BaseTest implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;
    private static final Logger log = LogManager.getLogger(ExtentManager.class);
    public void onStart(ITestContext testContext) {

       /*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
       Date dt=new Date();
       String currentdatetimestamp=df.format(dt);
       */

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
        repName = "ProfileStatistics-Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify location of the report

        sparkReporter.config().setDocumentTitle("Profile Statistics Automation Report"); // Title of report
        sparkReporter.config().setReportName("Profile Statistics Functional Testing"); // name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Profile Statistics");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Profile Analysis");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environemnt", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
        log.info("****************Test started*************");
    }

    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); // to display groups in report
        test.log(Status.PASS,result.getName()+" got successfully executed");
        log.info("*** Test Passed *** | Test Class : '{}' | Test Method : '{}'", result.getTestClass(), result.getName());
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL,result.getName()+" got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());
        //log.error("Test Failed: " + result.getName() + " - " + result.getThrowable());
        BaseTest base = (BaseTest) result.getInstance();

        try {
            String imgPath = base.Takescreenshot(result.getName());
            //For Allure
            byte[] bytes = base.takeScreenshotForAllure();
            io.qameta.allure.Allure.getLifecycle()
                    .addAttachment("Failure Screenshot", "image/png", "png", bytes);
            test.addScreenCaptureFromPath(imgPath);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        log.info("*** Test Failed *** | Test Class : '{}' | Test Method : '{}'", result.getTestClass(), result.getName());

    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName()+" got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
        log.info("*** Test Skipped *** | Test Class : '{}' | Test Method : '{}'", result.getTestClass(), result.getName());

    }

    public void onFinish(ITestContext testContext) {

        extent.flush();

        String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("******************Test finished*****************");

    }



}

