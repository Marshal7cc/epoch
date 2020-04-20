package com.marshal.epoch.common.util;

//import com.deepoove.poi.XWPFTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * @auth Marshal
 * @date 2019/8/16
 * @desc poi工具类
 */
//todo
public class PoiUtil {

    private PoiUtil() {

    }

    public static void renderTemplate(String filePath, Map renderData, HttpServletResponse response) throws Exception {
        renderTemplate(new FileInputStream(filePath), renderData, response);
    }

    public static void renderTemplate(File file, Map renderData, HttpServletResponse response) throws Exception {
        renderTemplate(new FileInputStream(file), renderData, response);
    }

    /**
     * 模板渲染
     *
     * @param inputStream
     * @param renderData
     */
    public static void renderTemplate(InputStream inputStream, Map renderData, HttpServletResponse response) {

//        XWPFTemplate template = XWPFTemplate.compile(inputStream)
//                .render(renderData);
//
//        try {
//
//            ServletOutputStream outputStream = response.getOutputStream();
//
//            template.write(outputStream);
//
//            template.close();
//            outputStream.flush();
//            outputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}
