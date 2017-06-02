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
    	
    	String referee1;
    	String referee2;
    
    	referee1 = refereeMapper.queryreferee(openid);
    	// 未被推荐过
    	if (!refereeid.equals(referee1))
    		refereeMapper.save(referee);
    	
    	referee2 = refereeMapper.queryreferee(referee1);
    	
    	referee.setOpenid(openid);
    	referee.setReferee1(referee1);
    	referee.setReferee2(referee2);
    	
    	return referee;  	
    }    
}
