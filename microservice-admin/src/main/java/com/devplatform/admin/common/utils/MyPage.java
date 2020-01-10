
package com.devplatform.admin.common.utils;

import java.util.List;

/**
 * 
 * @Description: 分页处理
 * @Copyright: Copyright (c) 2018
 * @Company: 北京腾信软创科技股份有限公司
 * @author Rice
 * @version 1.1  2018年4月10日
 */
public class MyPage {
    
    private Integer pageNum;//当前页
    
    private Integer pageSize;//叶容量
    
    private Integer total;//总条数
    
    private Integer pages;//总页数
    
    private List<?> rows;//数据集合
    
    public Integer getPageNum() {
        return pageNum;
    }
    
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    
    public Integer getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    public Integer getTotal() {
        return total;
    }
    
    public void setTotal(Integer total) {
        this.total = total;
    }
    
    public Integer getPages() {
        return pages;
    }
    
    public void setPages(Integer pages) {
        this.pages = pages;
    }
    
    public List<?> getRows() {
        return rows;
    }
    
    public void setRows(List<?> rows) {
        this.rows = rows;
    }
    
}
