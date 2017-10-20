package com.lhx.jfinalone.controller;

import com.jfinal.core.Controller;
import com.lhx.jfinalone.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserController extends Controller {
    private static Logger log= LoggerFactory.getLogger(UserController.class);
    public void list(){
        List<SysUser> userList = SysUser.dao.find("select * from sys_user");
        setAttr("userList",userList);
        setAttr("aaa",4444);
        renderJsp("user_list.jsp");
    }

}
