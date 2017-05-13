package me.wirries.weatheriotshowcase.server;

import me.wirries.weatheriotshowcase.server.config.TestConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Abstract Test Case for all Spring Tests.
 */
@SpringBootTest(classes = {ServerApplication.class, TestConfig.class})
@RunWith(SpringRunner.class)
public abstract class AbstractApplicationTest {

}
