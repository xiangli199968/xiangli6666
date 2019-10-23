package com.dzkj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import com.dzkj.pojo.users;

import tk.mybatis.mapper.common.Mapper;

public interface usermapper extends  Mapper<users>{
	users login(users user);
}
