package com.lxg.springboot.controller;

import com.lxg.springboot.mapper.DiagnosisMapper;
import com.lxg.springboot.model.Diagnosis;
import com.lxg.springboot.model.Result;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

/**
 * Created by zhenghong
 * on 2017/4/25.
 */
@RestController
@RequestMapping("health/diagnosis/")
public class DiagnosisController extends BaseController {
	
	@Resource
    private DiagnosisMapper diagnosisMapper;

    @RequestMapping("save")
    public Result save(Diagnosis diagnosis) {
    
    	diagnosisMapper.save(diagnosis);
    	return re;
    }    
        
    @RequestMapping("query")
    public Diagnosis query(Diagnosis diagnosis) {
    	
    	System.out.println(diagnosis.toString());
    	Diagnosis d = diagnosisMapper.query(diagnosis);
    	return d;  	
    }  
    
    @RequestMapping("querybypage")
    public List<Diagnosis> querybypage(Diagnosis diagnosis) {
    	
    	List<Diagnosis> d = diagnosisMapper.querybypage(diagnosis);
    	return d;  	
    } 
    
}
