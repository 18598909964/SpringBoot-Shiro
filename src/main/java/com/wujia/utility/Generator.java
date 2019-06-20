package com.wujia.utility;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class Generator {

    public static void main(String[] args) {
        // 包名
        String packageName = "com.wujia";
        boolean serviceNameStartWithI = false;//auth -> UserService, 设置成true: auth -> IUserService
        //把需要自动生成的表 放在这里!!
        generateByTables(serviceNameStartWithI, packageName, "吾嘉", "spring_boot_shiro", "users");
//        generateByTables(serviceNameStartWithI, packageName, "吾嘉", "room_lease", "admin_role");
//        generateByTables(serviceNameStartWithI, packageName, "吾嘉", "room_lease", "floor");
//        generateByTables(serviceNameStartWithI, packageName, "吾嘉", "room_lease", "grade");
//        generateByTables(serviceNameStartWithI, packageName, "吾嘉", "room_lease", "landlord");
//        generateByTables(serviceNameStartWithI, packageName, "吾嘉", "room_lease", "property");
//        generateByTables(serviceNameStartWithI, packageName, "吾嘉", "room_lease", "register");
//        generateByTables(serviceNameStartWithI, packageName, "吾嘉", "room_lease", "repairs");
//        generateByTables(serviceNameStartWithI, packageName, "吾嘉", "room_lease", "reserve");
//        generateByTables(serviceNameStartWithI, packageName, "吾嘉", "room_lease", "role");
//        generateByTables(serviceNameStartWithI, packageName, "吾嘉", "room_lease", "role_grade");
//        generateByTables(serviceNameStartWithI, packageName, "吾嘉", "room_lease", "room");
//        generateByTables(serviceNameStartWithI, packageName, "吾嘉", "room_lease", "room_type");
//        generateByTables(serviceNameStartWithI, packageName, "吾嘉", "room_lease", "suite");
//        generateByTables(serviceNameStartWithI, packageName, "吾嘉", "room_lease", "tenant");
//        generateByTables(serviceNameStartWithI, packageName, "吾嘉", "room_lease", "trade");
        System.out.println("completed...");
    }

    /**
     * @param serviceNameStartWithI
     * @param packageName           包名
     * @param author                作者
     * @param database              数据库名
     * @param tableNames            表名
     */
    private static void generateByTables(boolean serviceNameStartWithI, String packageName, String author, String database, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://localhost:3306/" + database + "?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=no";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("980307")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
//              .setSuperMapperClass("cn.saytime.mapper.BaseMapper")
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setAuthor(author)
                .setOutputDir("E:\\Works\\generator")
                .setFileOverride(true)
                .setEnableCache(false);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                                .setMapper("dao")
                                .setService("service")
                                .setServiceImpl("service.impl")
                                .setXml("dao")
                ).execute();
    }
}
