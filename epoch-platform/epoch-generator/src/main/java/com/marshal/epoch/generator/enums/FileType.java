package com.marshal.epoch.generator.enums;

/**
 * 生成文件类型枚举
 */
public enum FileType {
    Controller("Controller.java"),
    MapperXml("Mapper.xml"),
    Mapper("Mapper.java"),
    Service("Service.java"),
    Impl("ServiceImpl.java"),
    Vue(".vue"),
    Api("Api.java"),
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
