package com.marshal.epoch.test.service;

/**
 * @author Marshal
 * @date 2019/12/23
 * @desc
 */
public interface DynamicDatasourceService {

    /**
     * 测试master
     *
     * @return
     */
    Object testMaster();

    /**
     * 测试slave
     *
     * @return
     */
    Object testSlave();

}
