package com.thinkgem.jeesite.common.jd;

public class Spider {

    private Downloadable downloadable;
    private Processable processable;
    private Storeable storeable;


    public Page download(String url){
        return downloadable.download(url);
    }


    public void process(Page page){
        processable.process(page);
    }

    public void store(Page page){
        storeable.store(page);
    }

    public Downloadable getDownloadable() {
        return downloadable;
    }

    public void setDownloadable(Downloadable downloadable) {
        this.downloadable = downloadable;
    }

    public Processable getProcessable() {
        return processable;
    }

    public void setProcessable(Processable processable) {
        this.processable = processable;
    }

    public Storeable getStoreable() {
        return storeable;
    }

    public void setStoreable(Storeable storeable) {
        this.storeable = storeable;
    }
}
