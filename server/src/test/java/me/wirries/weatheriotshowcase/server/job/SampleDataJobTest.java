package me.wirries.weatheriotshowcase.server.job;

import me.wirries.weatheriotshowcase.server.AbstractApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Tests for {@link SampleDataJob}.
 */
public class SampleDataJobTest extends AbstractApplicationTest {

    @Autowired
    private SampleDataJob job;

    @Test
    public void run() throws Exception {
        job.run();
    }

    @Test
    public void createData() throws Exception {
        job.createData();
    }

}