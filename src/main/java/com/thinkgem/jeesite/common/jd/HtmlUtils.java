package com.thinkgem.jeesite.common.jd;

import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import sun.org.mozilla.javascript.internal.NativeObject;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.List;

public class HtmlUtils {
    /**
     * 根据xpath获取对应标签的内容
     * @param tagNode
     * @param xpath
     * @return
     */
    public static String getText(TagNode tagNode, String xpath){
        String content = null;
        Object[] evaluateXPath;
        try {
            evaluateXPath = tagNode.evaluateXPath(xpath);
            if(evaluateXPath!=null && evaluateXPath.length>0){
                TagNode node = (TagNode)evaluateXPath[0];
                content = node.getText().toString();
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        return content;
    }


    /**
     * 获取对应标签中指定属性的值
     * @param tagNode
     * @param xpath
     * @param attr
     * @return
     */
    public static List getListAttributeByName(TagNode tagNode,String xpath,String attr,String http){
        String content = null;
        Object[] evaluateXPath;
        List<String>pics=new ArrayList<String>();
        try {
            evaluateXPath = tagNode.evaluateXPath(xpath);

            for (Object tnode : evaluateXPath) {
                TagNode node = (TagNode)tnode;

                pics.add(http+ node.getAttributeByName(attr));
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        return pics;
    }
    /**
     * 获取对应标签中指定属性的值
     * @param tagNode
     * @param xpath
     * @param attr
     * @return
     */
    public static String getAttributeByName(TagNode tagNode,String xpath,String attr){
        String content = null;
        Object[] evaluateXPath;
        String []pics;
        try {
            evaluateXPath = tagNode.evaluateXPath(xpath);
               if(evaluateXPath!=null && evaluateXPath.length>0){
                TagNode node = (TagNode)evaluateXPath[0];
                content = node.getAttributeByName(attr);
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String getAttributeByScript(TagNode tagNode, String xpath, String attr, String http) {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine=sem.getEngineByName("js");
        String content = null;
        String desc = null;
        Object[] evaluateXPath;

        try {
            evaluateXPath = tagNode.evaluateXPath(xpath);
            TagNode node = (TagNode)evaluateXPath[0];
            content=node.getText().toString();
          /*  for (Object tnode : evaluateXPath) {
                TagNode node = (TagNode)tnode;
                pics.add(http+ node.getAttributeByName(attr));
            }*/
            engine.eval(content);
            NativeObject pageConfig= (NativeObject) engine.get("pageConfig");//获取js中对象
           /* Gson gson=new Gson();
            System.out.println(gson.toJson(pageConfig));
            org.json.JSONObject container = new org.json.JSONObject(gson.toJson(pageConfig));*/
           // System.out.println(pageConfig.);
            NativeObject product = (NativeObject) pageConfig.get ("product", null);
              desc = (String) product.get ("desc", null);
            //Object obj=engine.get("pageConfig.product.desc");//获取js中对象
             //System.out.println(desc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return desc;
    }
}
