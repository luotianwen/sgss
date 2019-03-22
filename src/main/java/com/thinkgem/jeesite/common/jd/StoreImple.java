package com.thinkgem.jeesite.common.jd;

import java.io.File;

public class StoreImple implements Storeable{
    public void store(Page page) {
        String dataUrl = page.getDataUrl();
        String goodid = page.getGoodId();
        String goodname = page.getGoodName();
        String path="D:";
        if(null!=page.getPath()&&!"".equals(page.getPath()))
            path=page.getPath();
        path=path+ File.separator+goodid;
        File file=new File(path);
        file.deleteOnExit();
        file.mkdirs();
        int i=1;
        for (String pic:page.getPicUrl()
             ) {

           PageUtil.getFile(pic,path+"/a"+i+pic.substring(pic.lastIndexOf(".")));

           i++;
        }
        int j=1;
        for (String dispic:page.getDisUrl()
                ) {

            PageUtil.getFile(dispic,path+"/"+j+dispic.substring(dispic.lastIndexOf(".")));
            j++;
        }
        System.out.println(goodid+"下载完成");
       /* System.out.println(goodid);
        System.out.println(dataUrl);
        System.out.println(page.getPicUrl());
        System.out.println(goodname);
        System.out.println(page.getDisUrl());*/

       // System.out.println(goodid);
        //MyDBUtils.update(MyDBUtils.INSERT_LOG, goodid,dataUrl,picUrl,goodname,price,param,currtime);
    }
}
