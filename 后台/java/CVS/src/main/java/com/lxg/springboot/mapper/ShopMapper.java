package com.lxg.springboot.mapper;

import java.util.List;

import com.lxg.springboot.model.Order;
import com.lxg.springboot.model.Shop;

public interface ShopMapper {

	List<Shop> query();
	
	List<Order> totle(Order order);
			
}