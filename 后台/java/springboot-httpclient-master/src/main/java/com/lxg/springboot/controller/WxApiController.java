package com.lxg.springboot.controller;

import com.lxg.springboot.http.HttpAPIService;
import com.lxg.springboot.http.HttpResult;
import com.lxg.springboot.http.Token;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;

import javax.annotation.Resource;

/**
 * Created by zhenghong
 * on 2017/4/25.
 */
@RestController
public class WxApiController {

	@Resource
    private HttpAPIService httpAPIService;

    @RequestMapping("wx/getopenid")
    public String getopenid(String code) throws Exception {

    	 String url = "https://api.weixin.qq.com/sns/jscode2session?"
    	 		+ "appid=wx6fabc07e4965d33e&"
    	 		+ "secret=9356f5ed37a87e5cd930f134e86adf74&"
    	 		+ "js_code="+code+"&"
    	 		+ "grant_type=authorization_code";


         String res = httpAPIService.doGet(url);
         
         return res;
    	
    }
    
    @RequestMapping("wx/send")
    public HttpResult send(String openid, String templateid, String formid, String data, String page) throws Exception {

    	 // 获取token
   	 	 String urltoken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&"
   	 	 		+ "appid=wx6fabc07e4965d33e&"
   	 	 		+ "secret=9356f5ed37a87e5cd930f134e86adf74";


   	 	 Token token = JSON.parseObject(httpAPIService.doGet(urltoken), Token.class);
    	
    	 String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+
    			 token.getAccess_token();

         // 参数
         HashMap<String, Object> map = new HashMap<String, Object>();
         map.put("touser", openid);
         map.put("template_id", templateid);  	
         map.put("form_id", formid);
         map.put("data", JSON.parse(data));
         map.put("page", page);
         map.put("emphasis_keyword", "keyword1.DATA");
         
         HashMap<String, Object> maptemp = new HashMap<String, Object>();
         maptemp.put("Jsondata", JSON.toJSONString(map));
         
         // 请求头
         HashMap<String, Object> header = new HashMap<String, Object>();
         
         HttpResult res = httpAPIService.doPostWx(url, maptemp, header);
         
         return res;
    	
    }
    
}
