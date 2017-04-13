package org.ksyzz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fengqian on 2017/4/13.
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "login";
    }
}
