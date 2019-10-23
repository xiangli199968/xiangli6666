package com.dzkj.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dzkj.mapper.usermapper;
import com.dzkj.pojo.users;

import tk.mybatis.mapper.common.Mapper;

@Service
public class userDao implements userDaoImp{
	@Resource
	usermapper usermapper;
	//登录的实现
	@Override
	public users login(users user) {
		return usermapper.selectOne(user);
	}

}
