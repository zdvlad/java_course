package ru.course.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.MatchResult;

public class ApplicationManager {

    private final Properties properties;
    private WebDriver wd;
    private String browser;
    private RegistrationHelper registrationHelper;
    private FTPHelper ftpHelper;
    private MailHelper mail;
    private ChangePassswordHelper password;
    private DbHelper dbHelper;
    private SoapHelper soap;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        dbHelper = new DbHelper();
    }

    public void stop() {
        if (wd != null)
            wd.quit();
    }

    public Properties getProperties() {
        return properties;
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null)
            registrationHelper = new RegistrationHelper(this);

        return registrationHelper;
    }

    public FTPHelper ftp() {
        if (ftpHelper == null)
            ftpHelper = new FTPHelper(this);

        return ftpHelper;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.CHROME))
                wd = new ChromeDriver();
            else if (browser.equals(BrowserType.FIREFOX))
                wd = new FirefoxDriver();
            wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseURL"));
        }
        return wd;
    }

    public MailHelper mail() {
        if (mail == null)
            mail = new MailHelper(this);

        return mail;
    }

    public ChangePassswordHelper password() {
        if (password == null)
            password = new ChangePassswordHelper(this);

        return password;
    }

    public DbHelper db() {
        return dbHelper;
    }

    public SoapHelper soap() {
        if (soap == null)
            soap = new SoapHelper(this);

        return soap;
    }
}
