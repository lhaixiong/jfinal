package com.lhx.jfinalone.controller;

import com.jfinal.core.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexController extends Controller {
    private static Logger log= LoggerFactory.getLogger(IndexController.class);
    public void index(){
       renderJsp("page.jsp");
//        renderText("aaabbbb");
    }
}
