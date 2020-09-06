package readinglist;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by shucheng on 2020/8/31 22:40
 * 使用selenium测试表单提交
 * 浏览器：firefox78（驱动地址：https://github.com/mozilla/geckodriver/releases/tag/v0.27.0）
 *
 * 说明：
 * 环境变量里需要有两个路径：1.firefox浏览器的路径（如果不是在默认路径下安装的话）
 * 2.firefox驱动的路径
 *
 * 这里不是以Test结尾，maven打包的时候不会执行这个单元测试，
 * 想要执行的话，改下名，比如：ServerWebSeleniumTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerWebTests {

    private static FirefoxDriver browser;

    @Value("${local.server.port}")
    private int port;

    @BeforeClass
    public static void openBrowser() {
        System.setProperty("webdriver.firefox.bin","D:\\worksoft\\Mozilla Firefox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver","E:\\selenium\\geckodriver-v0.27.0-win64\\geckodriver.exe");
        browser = new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void addBookToEmptyList() {
        String baseUrl = "http://localhost:" + port;
        // System.out.println(baseUrl);
        browser.get(baseUrl);

        String currentUrl = browser.getCurrentUrl();
        assertEquals(baseUrl +"/readingList", currentUrl);

        assertEquals("You have no books in your book list",
                browser.findElementByTagName("div").getText());

        // 填写并提交表单
        browser.findElementByName("title").sendKeys("BOOK TITLE");
        browser.findElementByName("author").sendKeys("BOOK AUTHOR");
        browser.findElementByName("isbn").sendKeys("1234567890");
        browser.findElementByName("description").sendKeys("DESCRIPTION");
        browser.findElementByTagName("form").submit();

        // 获取提交表单后的页面结果
        WebElement dl =
                browser.findElementByCssSelector("dt.bookHeadline");
        assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)",
                dl.getText());
        WebElement dt =
                browser.findElementByCssSelector("dd.bookDescription");
        assertEquals("DESCRIPTION", dt.getText());
    }

    @AfterClass
    public static void closeBrowser() {
        browser.quit();
    }
}
