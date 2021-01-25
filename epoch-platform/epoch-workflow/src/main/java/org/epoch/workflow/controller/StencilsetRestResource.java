package org.epoch.workflow.controller;

import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ActivitiException;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class StencilsetRestResource {

    @RequestMapping(value = {"/editor/stencilset",
            "/service/editor/stencilset"}, method = RequestMethod.GET,
            produces = "application/javascript;charset=UTF-8")
    @ResponseBody
    public String getStencilset(HttpServletRequest request) {
        String env = RequestContextUtils.getLocale(request).toString();
        InputStream stencilsetStream = null;
        if ("en_GB".equalsIgnoreCase(env) || "en_US".equalsIgnoreCase(env) || "en".equalsIgnoreCase(env)) {
            stencilsetStream = getClass().getClassLoader().getResourceAsStream("stencilset.json");
        } else {
            stencilsetStream = getClass().getClassLoader().getResourceAsStream("stencilset_" + env + ".json");
            if (stencilsetStream == null) {
                stencilsetStream = getClass().getClassLoader().getResourceAsStream("stencilset.json");
            }
        }
        try (InputStream ignore = stencilsetStream) {
            return IOUtils.toString(ignore, "utf-8");
        } catch (Exception e) {
            throw new ActivitiException("Error while loading stencil set", e);
        }
    }

    @RequestMapping(value = {"/editor/stencilset_new",
            "/service/editor/stencilset_new"}, method = RequestMethod.GET, produces = "application/javascript;charset=UTF-8")
    @ResponseBody
    public String getStencilsetNew(HttpServletRequest request) {
        String env = RequestContextUtils.getLocale(request).toString();
        InputStream stencilsetStream = null;
        if (env.equalsIgnoreCase("en_GB") || env.equalsIgnoreCase("en_US")
                || env.equalsIgnoreCase("en")) {
            stencilsetStream = getClass().getClassLoader().getResourceAsStream("stencilset.json");
        } else {
            stencilsetStream = getClass().getClassLoader().getResourceAsStream("stencilset_new_" + env + ".json");
            if (stencilsetStream == null) {
                stencilsetStream = getClass().getClassLoader().getResourceAsStream("stencilset.json");
            }
        }
        try (InputStream ignore = stencilsetStream) {
            return IOUtils.toString(ignore, "utf-8");
        } catch (Exception e) {
            throw new ActivitiException("Error while loading stencil set", e);
        }
    }
}
