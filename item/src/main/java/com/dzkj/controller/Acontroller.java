package com.dzkj.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dzkj.pojo.users;
import com.dzkj.service.userDaoImp;

@Controller
@RequestMapping("home")
public class Acontroller {
//登录
	@Autowired
	userDaoImp userdao;
	@RequestMapping("loginservlet")
	public String login(String name, String pwd,HttpServletRequest request) {
		String email = null,tellphone = null,uname = null;
		// 手机号判断
		String regtell = "^1[3|4|5|7|8][0-9]\\d{8}$";
		// 邮箱判断
		String regemail = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		if (name.matches(regtell)) {
			tellphone = name;
		} else if (name.matches(regemail)) {
			email = name;
		} else { 
			uname = name;
		}
		users u=new users();
		u.setUname(uname);
		u.setEmail(email);
		u.setTellphone(tellphone);
		u.setPwd(pwd);
		users user = userdao.login(u);
		HttpSession session=request.getSession();
		if (user != null) {
			session.setAttribute("user", user.getUzname());
			return "home/home";
		}
		return "redirect:login.html";
	}

	String codestr = "",fristphone="";
//手机号码验证码注册
	@RequestMapping("tell")
	@ResponseBody
	public String phone(String phone) {
		fristphone=phone;
		String codeString = SendSmsUtil.sendCheckMSM(fristphone);
		codestr = codeString;
		return codestr;
	}

	@RequestMapping("tellphone")
	@ResponseBody
	public String tellphone(String code,String phone) {
		if (codestr.equals(code)&&fristphone.equals(phone)) {
			return "success";
		}
		return "error";
	}
}
