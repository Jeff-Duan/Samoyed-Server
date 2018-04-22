package com.cherish.demo.config;


public class PageHelperConfig {

    //通过设置pageSize=0或者RowBounds.limit = 0就会查询出全部的结果。

   /* public PageHelper pageHelper() {

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "pageNum=page;pageSize=rows;orderBy=orderBy");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
*/
}
