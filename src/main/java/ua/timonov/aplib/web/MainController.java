package ua.timonov.aplib.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Alex on 23.12.2016.
 */
public class MainController {

    public static final String SLASH_INDEX = "/index";
    public static final String INDEX = "index";

    @RequestMapping(value = SLASH_INDEX, method = RequestMethod.GET)
    public String getMainPage() {
        return INDEX;
    }


}
