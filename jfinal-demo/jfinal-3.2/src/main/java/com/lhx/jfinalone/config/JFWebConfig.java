package com.lhx.jfinalone.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.lhx.jfinalone.entity._MappingKit;

public class JFWebConfig extends JFinalConfig {
    private static Logger log=LoggerFactory.getLogger(JFWebConfig.class);
    public void configConstant(Constants me) {
        // 第一次使用use加载的配置将成为主配置，可以通过PropKit.get(...)直接取值
        PropKit.use("config.properties");
        me.setDevMode(PropKit.getBoolean("devMode"));
    }
    public void configRoute(Routes me) {
        me.setBaseViewPath("/WEB-INF/jsp/");
        me.add(new IndexRouteConfig());
        me.add(new UserRouteConfig());
    }
    public void configEngine(Engine me) {}
    public void configPlugin(Plugins me) {
        //添加数据源插件
        DruidPlugin dp=createDruidPlugin();
        me.add(dp);

        //添加表实体映射插件
        ActiveRecordPlugin arp=new ActiveRecordPlugin(dp);
        me.add(arp);
        arp.setDialect(new MysqlDialect());
        _MappingKit.mapping(arp);

        //添加定时器插件...

    }
    public void configInterceptor(Interceptors me) {}
    public void configHandler(Handlers me) {}
    public static DruidPlugin createDruidPlugin() {
        return new DruidPlugin(PropKit.get("jdbc.url"), PropKit.get("jdbc.username"), PropKit.get("jdbc.password").trim());
    }
}
