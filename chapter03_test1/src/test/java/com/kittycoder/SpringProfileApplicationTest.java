package com.kittycoder;

import com.kittycoder.properties.TestProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by shucheng on 2020/8/30 23:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringProfileApplicationTest {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TestProperties testProperties;

    @Test
    public void testProfile() {
        logger.info("settings.test.id==>{}", testProperties.getId());
        logger.info("settings.test.name==>{}", testProperties.getName());
        logger.info("settings.test.serverName==>{}", testProperties.getServerName());
    }
}
