package com.example.semestrovka.helpers;

import freemarker.template.Configuration;

import javax.servlet.ServletContext;

public class FreemarkerConfigSingleton {
    private static Configuration cfg;
    private static ServletContext sc;

    public static Configuration getCfg() {
        if (cfg == null){
            cfg = new Configuration(Configuration.VERSION_2_3_32);
            cfg.setServletContextForTemplateLoading(sc,"/WEB-INF/templates");
        }
        return cfg;
    }

    public static void setServletContext(ServletContext servletContext) {
        sc = servletContext;
    }
}
