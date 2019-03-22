package com.thinkgem.jeesite.common.jd;
public class DownloadImpl implements Downloadable {

    public Page download(String url) {
        Page page = new Page();
        String content=PageUtil.getContent(url);

        page.setContent(content);
        page.setDataUrl(url);
        return page;
    }
}
