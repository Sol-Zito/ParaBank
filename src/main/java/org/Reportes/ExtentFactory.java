package org.Reportes;

import com.aventstack.extentreports.ExtentReports;

public class ExtentFactory {
    public static ExtentReports getInstance() {
        ExtentReports instance = new ExtentReports();
        instance.setSystemInfo("Selenium version", "4.1.4");
        instance.setSystemInfo("OS", "Windows");
        instance.setSystemInfo("Browser", "Chrome");
        return instance;

    }
}