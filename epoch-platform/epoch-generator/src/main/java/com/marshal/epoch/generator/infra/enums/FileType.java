package com.marshal.epoch.generator.infra.enums;

/**
 * 生成文件类型枚举
 *
 * @author Marshal
 */
public enum FileType {
    /**
     * controller
     */
    Controller("Controller.java"),
    /**
     * mapperXML
     */
    MapperXml("Mapper.xml"),
    /**
     * mapper
     */
    Mapper("Mapper.java"),
    /**
     * repository
     */
    Repository("Repository.java"),
    /**
     * repositoryImpl
     */
    Impl("RepositoryImpl.java"),
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
    Entity(".java");

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
