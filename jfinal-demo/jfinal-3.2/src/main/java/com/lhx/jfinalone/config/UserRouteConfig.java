package com.lhx.jfinalone.config;


import com.jfinal.config.Routes;
import com.lhx.jfinalone.controller.UserController;

public class UserRouteConfig extends Routes {
    @Override
    public void config() {
        add("/user", UserController.class);
    }
}
