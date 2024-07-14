package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public static ExtentSparkReporter sparkReport;
	public static ExtentReports extentReport;
//	public ExtentTest test;
	static String reportName;

	public static ExtentReports extentReportGenerate() {
		
		String timeStamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		reportName = "Test-Report" + timeStamp + ".html";
		sparkReport = new ExtentSparkReporter(".\\reports\\report.html" ); 
		sparkReport.config().setDocumentTitle("APIPetAutomation");
		sparkReport.config().setReportName("Pet Project Report");
		sparkReport.config().setTheme(Theme.STANDARD);

		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReport);
		extentReport.setSystemInfo("Application", "Pet Stor API Automation");
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Environment", "QA");
		extentReport.setSystemInfo("Tester Name", "Saidachary");
		return extentReport;
	}
	
}
