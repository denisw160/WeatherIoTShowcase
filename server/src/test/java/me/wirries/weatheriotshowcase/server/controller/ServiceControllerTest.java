package me.wirries.weatheriotshowcase.server.controller;

import me.wirries.weatheriotshowcase.server.AbstractApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link ServiceController}.
 */
public class ServiceControllerTest extends AbstractApplicationTest {

    @Autowired
    private ServiceController controller;

    @Test
    public void sample() throws Exception {
        final String sample = controller.sample();
        assertEquals("sample", sample);
    }

}