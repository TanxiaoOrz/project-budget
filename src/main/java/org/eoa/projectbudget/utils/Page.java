package org.eoa.projectbudget.utils;

/**
 * @Author: 张骏山
 * @Date: 2023/11/17 11:10
 * @PackageName: org.eoa.projectbudget.dto
 * @ClassName: Page
 * @Description: 分页类
 * @Version: 1.0
 **/

public class Page {
    int currentPage;
    int pageSize;

    public Page(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public int getStart() {
        return (currentPage-1)*pageSize;
    }

    public int getEnd(int max) {
        return Math.min(((currentPage)*pageSize), max);
    }
}
