package com.devplatform.admin.modules.generation.constant;

/**
 * 数据权限来源的枚举类型
 */
public enum DATAPERMISSSION_ENUM {

    /**
     * 2是组织机构
     */
    ORG(2),
    /**
     * 1是岗位
     */
    POST(1),
    ;
    private Integer type;

    private DATAPERMISSSION_ENUM(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
