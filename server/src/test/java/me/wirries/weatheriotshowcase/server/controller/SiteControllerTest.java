package me.wirries.weatheriotshowcase.server.controller;

import me.wirries.weatheriotshowcase.server.AbstractApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link SiteController}.
 */
public class SiteControllerTest extends AbstractApplicationTest {

    @Autowired
    private SiteController controller;

    @Test
    public void dashboard() throws Exception {
        final String s = controller.dashboard();
        assertEquals("site/dashboard", s);
    }

    @Test
    public void locations() throws Exception {
        final String s = controller.locations();
        assertEquals("site/locations", s);
    }

    @Test
    public void map() throws Exception {
        final String s = controller.map();
        assertEquals("site/map", s);
    }

    @Test
    public void monitor() throws Exception {
        final String s = controller.monitor();
        assertEquals("site/monitor", s);
    }

    @Test
    public void api() throws Exception {
        final String s = controller.api();
        assertEquals("redirect:swagger-ui.html", s);
    }

}