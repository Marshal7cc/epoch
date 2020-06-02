package com.marshal.epoch.generator.enums;

/**
 * 生成文件类型枚举
 * @author Marshal
 */
public enum FileType {
    /**
     * controller
     */
    Controller("Controller.java"),
    /**
     * mapper
     */
    MapperXml("Mapper.xml"),
    /**
     * service
     */
    Mapper("Mapper.java"),
    /**
     * controller
     */
    Service("Service.java"),
    /**
     * serviceImpl
     */
    Impl("ServiceImpl.java"),
    /**
     * vue
     */
    Vue(".vue"),
    /**
     * api
     */
    Api("Api.java"),
    /**
     * dto
     */
    Dto(".java");

    private String fileSuffix;

    FileType(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }
}
