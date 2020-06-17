package com.marshal.epoch.workflow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @auth: Marshal
 * @Date: 2018/10/26
 * @desc: 通用controller
 */
@Controller
public class CommonController {

    private Logger logger = LoggerFactory.getLogger(CommonController.class);

    /**
     * 直接访问html页面
     *
     * @param name
     * @return
     */
    @GetMapping({"/{name}.html"})
    public ModelAndView renderView(@PathVariable String name) {
        return new ModelAndView(new StringBuffer().append(name).toString());
    }

    @GetMapping({"/{folder1}/{name}.html"})
    public ModelAndView renderFolder1View(@PathVariable String folder1, @PathVariable String name) {
        return new ModelAndView(new StringBuilder().append(folder1).append("/").append(name).toString());
    }

    @GetMapping({"/{folder1}/{folder2}/{name}.html"})
    public ModelAndView renderFolder2View(@PathVariable String folder1, @PathVariable String folder2,
                                          @PathVariable String name) {
        return new ModelAndView(new StringBuilder().append(folder1).append("/").append(folder2)
                .append("/").append(name).toString());
    }

    @GetMapping({"/{folder1}/{folder2}/{folder3}/{name}.html"})
    public ModelAndView renderFolder3View(@PathVariable String folder1, @PathVariable String folder2,
                                          @PathVariable String folder3, @PathVariable String name) {
        return new ModelAndView(new StringBuilder().append(folder1).append("/").append(folder2)
                .append("/").append(folder3).append("/").append(name).toString());
    }
}
