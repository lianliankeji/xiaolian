package com.lxg.springboot.controller;

import com.lxg.springboot.mapper.ScoreMapper;
import com.lxg.springboot.model.Result;
import com.lxg.springboot.model.Score;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zhenghong
 * on 2017/4/25.
 */
@RestController
@RequestMapping("health/score/")
public class ScoreController extends BaseController {
	
	@Resource
    private ScoreMapper scoreMapper;
    
    @RequestMapping("update")
    public Result update(Score score) {
  
    	int count = scoreMapper.count(score);
    	if (count == 0){
    		scoreMapper.save(score);
    	}else{
    		scoreMapper.update(score);
    	}
    	return re;
    }
    
    @RequestMapping("querybytype")
    public int querybytype(Score score) {
    	
    	int s = scoreMapper.querybytype(score);
    	return s;  	
    }   
    
    @RequestMapping("query")
    public int query(String openid) {
    	
    	int score = scoreMapper.query(openid);
    	return score;  	
    }  
}
