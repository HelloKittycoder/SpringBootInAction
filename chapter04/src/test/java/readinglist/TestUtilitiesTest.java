package readinglist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import readinglist.TestUtilitiesTest.TestConfig;

import static org.junit.Assert.assertEquals;

/**
 * Created by shucheng on 2020/8/31 23:23
 *
 * ConfigFileApplicationContextInitializer是用来加载application.properties属性文件（yml也行）的
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class,
        initializers = ConfigFileApplicationContextInitializer.class)
public class TestUtilitiesTest {

    @Autowired
    private Environment env;

    @Test
    public void loadProperties() {
        assertEquals("bar", env.getProperty("foo"));
        assertEquals("123", env.getProperty("bar"));
    }

    @Configuration
    public static class TestConfig {}
}
