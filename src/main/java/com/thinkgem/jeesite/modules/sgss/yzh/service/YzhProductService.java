/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.yzh.service;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.modules.sgss.goods.entity.Goods;
import com.thinkgem.jeesite.modules.sgss.goods.service.SyncGoods;
import org.json.JSONML;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.yzh.entity.YzhProduct;
import com.thinkgem.jeesite.modules.sgss.yzh.dao.YzhProductDao;

/**
 * 云中鹤商品管理Service
 * @author martins
 * @version 2020-06-02
 */
@Service
@Transactional(readOnly = true)
public class YzhProductService extends CrudService<YzhProductDao, YzhProduct> {

	public YzhProduct get(String id) {
		return super.get(id);
	}
	
	public List<YzhProduct> findList(YzhProduct yzhProduct) {
		return super.findList(yzhProduct);
	}
	
	public Page<YzhProduct> findPage(Page<YzhProduct> page, YzhProduct yzhProduct) {
		return super.findPage(page, yzhProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(YzhProduct yzhProduct) {
		super.save(yzhProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(YzhProduct yzhProduct) {
		super.delete(yzhProduct);
	}

	public static String md5(String plainText){
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes("UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException("没有这个md5算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}

		return md5code;
	}
	private static final String getProductIdsByPage="api/product/v2/getProductIdsByPage.php";
	private static final String detial= "api/product/detial.php";
	private static final String stock= "/api/product/stock.php";

    @Transactional(readOnly = false)
        void deteil(int i){
        String WID=Global.getConfig("yzh.WID");
        String accessToken=Global.getConfig("yzh.accessToken");
        String yzhURL=Global.getConfig("yzh.Url");
        long timestamp=System.currentTimeMillis();
        Map map = new HashMap();
        map.put("wid", WID);
        //MD5(wid + accessToken + 时间戳)，值再转大写
        map.put("token", md5(WID+accessToken+timestamp).toUpperCase());
        map.put("timestamp", timestamp+"");

        map.put("pid", i+"");

        String str = Cont.post(yzhURL+detial, map).toLowerCase();
        ObjectMapper objectMapper = new ObjectMapper();
        YzhProductData j = null;
        try {
            j = objectMapper.readValue(str, YzhProductData.class);
            if(j.getResponse_status().equals("true")){
                if(j.getResult_data().getProduct_data().getStatus().equals("selling")) {

                    YzhProductData.ResultDataBean.ProductDataBean t=j.getResult_data().getProduct_data();
                    YzhProduct yzhProduct=new YzhProduct();
                    yzhProduct.setProductid(t.getProductid());
                    List<YzhProduct> ls=this.findList(yzhProduct);
                    if(null!=ls&&ls.size()>0){
                        yzhProduct.setId(ls.get(0).getId());
                    }
                    yzhProduct.setName(t.getName());		// 商品名称
                    yzhProduct.setThumbnailimage(t.getThumbnailimage());		// 商品缩略图
                    yzhProduct.setBrand(t.getBrand());		// 品牌
                    yzhProduct.setProductcode(t.getProductcode());		// 商品型号
                    yzhProduct.setStatus(t.getStatus());		// 状态
                    yzhProduct.setMarketprice(t.getMarketprice());		// 市场价
                    yzhProduct.setRetailprice(t.getRetailprice());		// 协议价格
                    yzhProduct.setProductplace(t.getProductplace());		// 商品产地
                    yzhProduct.setFeatures(t.getFeatures());		// 商品描述信息
                    yzhProduct.setCreatetime(t.getCreatetime());		// 商品创建时间
                    yzhProduct.setIs7toreturn(t.isIs7toreturn());		// 无理由退货
                    yzhProduct.setProductDescription(j.getResult_data().getProduct_description());		// 商品详情信息
                    yzhProduct.setUpdateDate(new Date());
                    this.save(yzhProduct);


                }
            }
        }catch (Exception e){

        }




    }
    @Transactional(readOnly = false)
    public void saveSync(Goods goods) throws Exception {
        YzhProduct yzhProduct=new YzhProduct();
        yzhProduct.setId(goods.getId());
        yzhProduct.setSync("1");
        SyncGoods sy=new SyncGoods(goods,"1");
        sy.login().saveGoods().saveSku().CommitGoods();
        dao.saveSync(yzhProduct);
    }


    @Transactional(readOnly = false)
	  void getpage(int ii){
		String WID=Global.getConfig("yzh.WID");
		String accessToken=Global.getConfig("yzh.accessToken");
		String yzhURL=Global.getConfig("yzh.Url");
		long timestamp=System.currentTimeMillis();
		Map map = new HashMap();
		map.put("wid", WID);
		//MD5(wid + accessToken + 时间戳)，值再转大写
		map.put("token", md5(WID+accessToken+timestamp).toUpperCase());
		map.put("timestamp", timestamp+"");
		map.put("page", ii+"");
        System.out.println(yzhURL+getProductIdsByPage);
        System.out.println(map);
		String str = Cont.post(yzhURL+getProductIdsByPage, map).toLowerCase();
        System.out.println(str);
        ObjectMapper objectMapper = new ObjectMapper();
        YzhData j = null;
        try {
            j = objectMapper.readValue(str, YzhData.class);
            if(j.getResponse_status().equals("true")){
                for (Integer i:j.getResult_data()
                ) {
                    deteil(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	@Transactional(readOnly = false)
    public void tb(YzhProduct yzhProduct) {
        getpage(1);

    }
}