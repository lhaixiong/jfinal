package com.lhx.jfinalone.config;


import com.jfinal.config.Routes;
import com.lhx.jfinalone.controller.IndexController;

public class IndexRouteConfig extends Routes {
    @Override
    public void config() {
        add("/", IndexController.class);
    }
}
