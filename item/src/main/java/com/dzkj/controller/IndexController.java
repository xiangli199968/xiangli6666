package com.dzkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dzkj.service.userDaoImp;

@Controller
@RequestMapping("home")
public class IndexController {
	@RequestMapping("index")
	public String index() {
		return "person/index";
	}
}
