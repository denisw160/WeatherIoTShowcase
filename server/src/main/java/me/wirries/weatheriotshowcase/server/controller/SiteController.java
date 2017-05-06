package me.wirries.weatheriotshowcase.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This Controller handles the index site.
 *
 * @author Denis.Wirries
 * @version 1.0
 * @since 06.05.2017
 */
@Controller
public class SiteController {

    @RequestMapping(value = "/")
    public String dashboard() {
        return "site/dashboard";
    }

    @RequestMapping(value = "/map")
    public String map() {
        return "site/map";
    }

    @RequestMapping(value = "/monitor")
    public String monitor() {
        return "site/monitor";
    }

    @RequestMapping(value = "/api")
    public String api() {
        return "redirect:swagger-ui.html";
    }

}
