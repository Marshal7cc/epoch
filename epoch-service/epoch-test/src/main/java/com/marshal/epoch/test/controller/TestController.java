package com.marshal.epoch.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.marshal.epoch.core.rest.Response;
import com.marshal.epoch.test.service.DynamicDatasourceService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marshal
 * @date 2019/12/16
 * @desc
 */
@RestController
public class TestController {

    @Autowired
    private DynamicDatasourceService dynamicDatasourceService;

    @GetMapping("/test")
    public void test(HttpServletResponse response) {
        List<JSONObject> reorderBjList = new ArrayList<>();

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("受理统计列表");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("申请编号");
        cell = row.createCell(1);
        cell.setCellValue("收件编号");
        cell = row.createCell(2);
        cell.setCellValue("不动产单元号");
        cell = row.createCell(3);
        cell.setCellValue("经办人");
        cell = row.createCell(4);
        cell.setCellValue("权利人");
        cell = row.createCell(5);
        cell.setCellValue("义务人");
        cell = row.createCell(6);
        cell.setCellValue("申请时间");
        cell = row.createCell(7);
        cell.setCellValue("业务类型");
        cell = row.createCell(8);
        cell.setCellValue("办结时间");
        cell = row.createCell(9);
        cell.setCellValue("座落");
        cell = row.createCell(10);
        cell.setCellValue("代理人");
        cell = row.createCell(11);
        cell.setCellValue("办理状态");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < reorderBjList.size(); i++) {

        }

        try {
            Response.responseExcel(wb, "slbj.xlsx", response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/ds1")
    public Object ds1() {
        return dynamicDatasourceService.testMaster();
    }

    @GetMapping("/ds2")
    public Object ds2() {
        return "dynamicDatasourceService.testSlave()";
    }

}
