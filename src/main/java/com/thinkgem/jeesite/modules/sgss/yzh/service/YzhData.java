package com.thinkgem.jeesite.modules.sgss.yzh.service;

import java.util.List;

public class YzhData {

    /**
     * RESPONSE_STATUS : true
     * TOTAL_PAGE : 100
     * PAGE : 1
     * RESULT_DATA : [10,23,25]
     */

    private String response_status;
    private int total_page;
    private int page;
    private List<Integer> result_data;

    public String getResponse_status() {
        return response_status;
    }

    public void setResponse_status(String response_status) {
        this.response_status = response_status;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Integer> getResult_data() {
        return result_data;
    }

    public void setResult_data(List<Integer> result_data) {
        this.result_data = result_data;
    }
}
