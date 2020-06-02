package com.marshal.epoch.test.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.marshal.epoch.test.service.DynamicDatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marshal
 * @date 2019/12/23
 * @desc
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DynamicDatasourceServiceImpl implements DynamicDatasourceService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL = "select * from sys_user";

    @DS("master")
    @Override
    public Object testMaster() {
        return jdbcTemplate.queryForList(SQL);
    }

    @DS("slave_1")
    @Override
    public Object testSlave() {
        return jdbcTemplate.queryForList(SQL);
    }
}
