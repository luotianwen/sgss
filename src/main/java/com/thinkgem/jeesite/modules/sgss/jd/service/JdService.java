/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sgss.jd.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.jd.*;
import com.thinkgem.jeesite.modules.sgss.goods.entity.Goods;
import com.thinkgem.jeesite.modules.sgss.goods.entity.GoodsDetail;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sgss.jd.entity.Jd;
import com.thinkgem.jeesite.modules.sgss.jd.dao.JdDao;

/**
 * 京东抓取Service
 * @author martins
 * @version 2019-03-22
 */
@Service
@Transactional(readOnly = true)
public class JdService extends CrudService<JdDao, Jd> {

	public Jd get(String id) {
		return super.get(id);
	}
	
	public List<Jd> findList(Jd jd) {
		return super.findList(jd);
	}
	
	public Page<Jd> findPage(Page<Jd> page, Jd jd) {
		return super.findPage(page, jd);
	}
	
	@Transactional(readOnly = false)
	public void save(Jd jd) {
		super.save(jd);
	}
	
	@Transactional(readOnly = false)
	public void delete(Jd jd) {
		super.delete(jd);
	}

    public Goods pacth(Jd jd) {
		Spider spider = new Spider();
		//给接口注入实现类
		spider.setDownloadable(new DownloadImpl());
		spider.setProcessable(new ProcessImpl());

		String url=jd.getUrl();
		com.thinkgem.jeesite.common.jd.Page page = spider.download("http://item.jd.com/"+url+".html");
		spider.process(page);
		Goods g=new Goods();
		g.setArtno( page.getGoodId());
		g.setName( page.getGoodName());
		String savePath = this.getFolder();
		int i=1;
		String imgs="";
		String img="";
		for (String pic:page.getPicUrl()
		) {
			img=savePath+"/"+page.getGoodId()+"_"+i+pic.substring(pic.lastIndexOf("."));
			if(i==1){
				g.setLogo(img);
			}
			PageUtil.getFile(pic,Global.getUserfilesBaseDir()+"/"+img);
			imgs+="|"+img;
			i++;
		}
		g.setImgs(imgs);
		int j=1;
		String disImg="";
	/*	<p><img src="/userfiles/1/images/goods/2019/03/21/35811553158632695.jpg"  /></p>
	*/
	StringBuffer sb=new StringBuffer();
	for (String dispic:page.getDisUrl()
		) {
			disImg=savePath+"/"+page.getGoodId()+"_d"+j+dispic.substring(dispic.lastIndexOf("."));
			PageUtil.getFile(dispic,Global.getUserfilesBaseDir()+"/"+disImg);
			j++;
		    sb.append("<p>").append("<img src=\"").append(disImg).append("\"  /></p>");
		}
		GoodsDetail gd=new GoodsDetail();
		gd.setDetails(sb.toString());
       g.setDetail(gd);

		return g;
    }
	/**
	 * 根据字符串创建本地目录 并按照日期建立子目录返回
	 * @return
	 */
	private String getFolder() {
		SystemAuthorizingRealm.Principal principal = (SystemAuthorizingRealm.Principal) UserUtils.getPrincipal();
		String path=Global.USERFILES_BASE_URL+ principal + "/images/goods";
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		path += "/" + formater.format(new Date());
		File dir = new File(this.getPhysicalPath(path));
		if (!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
				return "";
			}
		}
		return path;
	}
	/**
	 * 根据传入的虚拟路径获取物理路径
	 *
	 * @param path
	 * @return
	 */
	private String getPhysicalPath(String path) {
		String _realPath =  Global.getUserfilesBaseDir();
		if(null!=_realPath&!_realPath.equals("")) {
			return new File(_realPath).getPath() + "/" + path;
		}
		return null;
	}
}