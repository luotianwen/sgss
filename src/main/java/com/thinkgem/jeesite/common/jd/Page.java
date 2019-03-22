package com.thinkgem.jeesite.common.jd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页面实体类
 * 保存页面信息
 */
public class Page {

    private String goodId;
    private String goodName;
    private String dataUrl;
    private List<String>  picUrl;
    private String price;
    private Map<String, String> param = new HashMap<String, String>();
    private String content;
    private List<String> disUrl;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getDisUrl() {
        return disUrl;
    }

    public void setDisUrl(List disUrl) {
        this.disUrl = disUrl;
    }

    public String getGoodId() {
        return goodId;
    }
    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }
    public String getGoodName() {
        return goodName;
    }
    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }
    public String getDataUrl() {
        return dataUrl;
    }
    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }
    public Map<String, String> getParam() {
        return param;
    }
    public void setParam(String key,String value) {
        this.param.put(key, value);
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public List<String>  getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(List picUrl) {
        this.picUrl = picUrl;
    }
}