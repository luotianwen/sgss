package com.thinkgem.jeesite.common.jd;

public class Test {
    public static void main(String[] args) {
        String dispic="http://img13.360buyimg.com/popWareDetail/jfs/t4060/64/570075432/57208/79d73677/5854937cNf8b82c23.jpg";
        dispic=dispic.replace("https","http");
        System.out.println(dispic);
        PageUtil.getFile(dispic,"D:/5854937cNf8b82c23.jpg");
    }
}
