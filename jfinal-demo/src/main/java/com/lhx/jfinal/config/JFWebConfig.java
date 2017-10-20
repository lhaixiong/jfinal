package com.lhx.jfinal.config;

import com.jfinal.aop.Interceptor;
import com.jfinal.config.*;
import com.jfinal.core.ActionInvocation;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.lhx.jfinal.cons.SystemCons;
import com.lhx.jfinal.controller.IndexController;
import com.lhx.jfinal.controller.UserController;
import com.lhx.jfinal.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class JFWebConfig extends JFinalConfig {
    private static Logger log=LoggerFactory.getLogger(JFWebConfig.class);
    @Override
    public void configConstant(Constants me) {
        // 第一次使用use加载的配置将成为主配置，可以通过PropKit.get(...)直接取值
        PropKit.use("config.properties");
        me.setDevMode(PropKit.getBoolean(SystemCons.DEV_MODE));
        me.setViewType(ViewType.JSP);
        me.setBaseViewPath(SystemCons.BASE_VIEW_PATH);
        me.setJspViewExtension(SystemCons.JSP_EXTENSION);
        me.setEncoding(SystemCons.ENCODING);
        me.setUrlParaSeparator("-");
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", IndexController.class);
        me.add("/user",UserController.class,"/user");
    }

    @Override
    public void configPlugin(Plugins me) {
        DruidPlugin dp=new DruidPlugin(PropKit.get("jdbc.url"),PropKit.get("jdbc.username"),
                PropKit.get("jdbc.password"),PropKit.get("jdbc.driverClass"));
        me.add(dp);
        ActiveRecordPlugin arp=new ActiveRecordPlugin(dp);
        me.add(arp);
        arp.setDialect(new MysqlDialect());
        arp.addMapping("user", User.class);
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new Interceptor() {
            public void intercept(ActionInvocation ai) {
                log.info("Before method invoking");
                ai.invoke();//!important
                log.info("after method invoking");
            }
        });
    }

    @Override
    public void configHandler(Handlers me) {

    }

    @Override
    public void afterJFinalStart() {
        log.info("afterJFinalStart>>>>>");
        super.afterJFinalStart();
    }

    @Override
    public void beforeJFinalStop() {
        log.info("beforeJFinalStop>>>");
        super.beforeJFinalStop();
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        log.info("开始反注册driver...");
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                log.info("反注册driver:" + driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        log.info("完成反注册driver...");
    }
}
