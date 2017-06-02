package com.lxg.springboot.controller;

import com.lxg.springboot.mapper.RefereeMapper;
import com.lxg.springboot.model.Referee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zhenghong
 * on 2017/4/25.
 */
@RestController
@RequestMapping("health/referee/")
public class RefereeController extends BaseController {
	
	@Resource
    private RefereeMapper refereeMapper;
    
    @RequestMapping("query")
    public Referee query(String openid, String refereeid) {
    
    	Referee referee = new Referee();
    	referee.setOpenid(openid);
    	
    	String referee1;
    	String referee2;
    
    	referee1 = refereeMapper.queryreferee(openid);
    	
    	// 未被推荐过
    	if (!refereeid.equals(referee1)){
    		
    		referee2 = refereeMapper.queryreferee(refereeid);
    		
    		// 相互推荐
    		if (openid.equals(referee2)) 
    			return referee;
    		
    		refereeMapper.save(openid, refereeid);
    		
    	} else {
    		return referee;
    	}
    	
    	referee.setReferee1(refereeid);
    	referee.setReferee2(referee2);
    	
    	return referee;  	
    }    
}
