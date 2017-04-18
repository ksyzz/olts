package org.ksyzz.controller;

import org.ksyzz.entity.Option;
import org.ksyzz.info.OptionInfo;
import org.ksyzz.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by fengqian on 2017/4/18.
 */
@Controller
@RequestMapping("/option")
public class OptionController {
    @Autowired
    OptionService optionService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public OptionInfo addOption(
            @RequestParam("content") String content
    ){
        Option option = optionService.createOption(content);
        return new OptionInfo(option);
    }
}
