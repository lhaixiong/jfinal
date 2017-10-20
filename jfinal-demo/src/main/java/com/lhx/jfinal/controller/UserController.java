package com.lhx.jfinal.controller;

import com.jfinal.core.Controller;
import com.lhx.jfinal.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserController extends Controller {
    private static Logger log= LoggerFactory.getLogger(UserController.class);
    public void index(){
        List<User> userList = User.dao.find("select * from user");
        setAttr("userList",userList);
        setAttr("aaa",4444);
        renderJsp("list.jsp");
    }
    public void say(){
        System.out.println(getClass().getName()+">>>>>>>>>>>>>>");
        renderText("hello,UserController ddd world bbbbbbbb");
    }
}
