package api.utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ITestlistners implements ITestListener {

	ExtentReports extent = ExtentReportManager.extentReportGenerate();
	ExtentTest test;

	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		test.assignCategory(result.getMethod().getMethodName());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Cases Passed");
	}

	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		test.assignCategory(result.getMethod().getMethodName());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Cases Failed");
		test.log(Status.PASS, result.getThrowable().getMessage());
	

	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		test.assignCategory(result.getMethod().getMethodName());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Cases Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		test.assignCategory(result.getMethod().getMethodName());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Cases Skip");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

	public void onFinish(ITestResult result) {
		extent.flush();
	}

}
