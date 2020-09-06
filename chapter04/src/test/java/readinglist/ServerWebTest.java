package readinglist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by shucheng on 2020/8/31 22:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerWebTest {

    @Value("${local.server.port}")
    private int port;

    @Test
    public void testSomething() {
        RestTemplate test = new RestTemplate();
        String s = test.getForObject("http://localhost:{port}", String.class, port);
        System.out.println(s);
    }
}
