package org.eoa.projectbudget.dto;

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

    public int getCurrentPage() {
        return currentPage;
    }

    public Page setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Page setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
