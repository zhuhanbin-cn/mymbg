package com.code.generator;

import com.code.generator.model.BaseData;
import com.code.generator.utils.DatabaseUtil;
import com.google.common.base.CaseFormat;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by L on 2018/5/29.
 */
public class CodeGenerator {
    /**
     * JDBC配置，请修改为你项目的实际配置
     */
    public static final String JDBC_URL = "jdbc:mysql://61.174.254.205:3306/ebb?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false";
    public static final String JDBC_USERNAME = "root";
    public static final String JDBC_PASSWORD = "w8Kz4#mj@";
    public static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    /**
     * 模块名称
     */
    static final String BASE_MODULE_NAME = "settingconfig";
    // static final String MODULE_NAME = "ipplatform";

    /**
     * feign客户端请求路径
     */
    static final String API_URL = "/api/" + BASE_MODULE_NAME;

    /**
     * 基础项目名称
     */
    static final String BASE_NAME = "ebb";
    /**
     * root这个是根据项目需求可以无值
     */
    static final String ROOT = "root";


    /**
     * 模块路径
     */
    static final String MODULE_DIR = "/" + BASE_NAME + "-" + CodeGenerator.BASE_MODULE_NAME;

    /**
     * 模块基础存放路径
     */
    public static final String BASE_DIR = "com/zjbdos/" + BASE_NAME + "/" + BASE_MODULE_NAME;

    /**
     * 模块基础包路径
     */
    public static final String BASE_PACKAGE = "com.zjbdos." + BASE_NAME + "." + BASE_MODULE_NAME;


    /**
     * 项目在硬盘上的基础路径
     */
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    /**
     * 模板位置
     */
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/code-generator/src/main/resources/generatorTemplate";

    /**
     * Client路径和server路径
     */
    public static final String Client_DIR = "/" + BASE_NAME + "-" + BASE_MODULE_NAME + "-client";
    public static final String Server_DIR = "/" + BASE_NAME + "-" + BASE_MODULE_NAME + "-server";

    /**
     * applicationName
     */
    public static final String APPLICATION_NAME = BASE_NAME + "-" + BASE_MODULE_NAME + "-server";

    /**
     * java Client文件路径
     */
    private static final String JAVA_CLIENT_PATH = MODULE_DIR + Client_DIR + "/src/main/java/";
    /**
     * java Server文件路径
     */
    private static final String JAVA_SERVER_PATH = MODULE_DIR + Server_DIR + "/src/main/java/";

    /**
     * 资源文件路径
     */
    private static final String RESOURCES_PATH = MODULE_DIR + Server_DIR + "/src/main/resources";
    /**
     * 生成的Main存放路径 STORAGE
     */
    private static final String STORAGE_PATH_MAIN = JAVA_SERVER_PATH + BASE_DIR + "/server/";
    /**
     * 生成的Service存放路径 STORAGE
     */
    private static final String STORAGE_PATH_SERVICE = JAVA_SERVER_PATH + BASE_DIR + "/server/service/";
    /**
     * 生成的Service实现存放路径
     */
    private static final String STORAGE_PATH_SERVICE_IMPL = STORAGE_PATH_SERVICE + "impl/";

    /**
     * 生成config存放路径
     */
    private static final String STORAGE_PATH_SERVICE_CONFIG = STORAGE_PATH_MAIN + "config/";


    /**
     * 生成的Controller存放路径
     */
    private static final String STORAGE_PATH_CONTROLLER = JAVA_SERVER_PATH + BASE_DIR + "/server/controller/";
    /**
     * 生成的Converter存放路径
     */
    private static final String STORAGE_PATH_CONVERTER = JAVA_SERVER_PATH + BASE_DIR + "/server/converter/";

    /**
     * 生成的Entity存放路径
     */
    private static final String STORAGE_PATH_ENTITYVO = JAVA_SERVER_PATH + BASE_DIR + "/server/entity/";
    /**
     * 生成的Mapper存放路径
     */
    private static final String STORAGE_PATH_MAPPER= JAVA_SERVER_PATH + BASE_DIR + "/server/dao/";
    /**
     * 生成的RequestVO存放路径
     */
    private static final String STORAGE_PATH_REQUESTVO = JAVA_CLIENT_PATH + BASE_DIR + "/client/vo/";
    /**
     * 生成的ResponseVO存放路径
     */
    private static final String STORAGE_PATH_RESPONSEVO = JAVA_CLIENT_PATH + BASE_DIR + "/client/vo/";
    /**
     * 生成的DTO存放路径
     */
    private static final String STORAGE_PATH_DTO = JAVA_CLIENT_PATH + BASE_DIR + "/client/dto/";


    /**
     * 生成的FeignClient存放路径
     */
    private static final String STORAGE_PATH_FEIGN = JAVA_CLIENT_PATH + BASE_DIR + "/client/feign/";
    /**
     * 生成的FeignFactory存放路径
     */
    private static final String STORAGE_PATH_FEIGN_FACTORY = JAVA_CLIENT_PATH + BASE_DIR + "/client/feign/factory/";
    /**
     * 生成的FeignFallback存放路径
     */
    private static final String STORAGE_PATH_FEIGN_FALLBACK = JAVA_CLIENT_PATH + BASE_DIR + "/client/feign/fallback/";


    /**
     * Server所在包
     */
    public static final String SERVER_PACKAGE = BASE_PACKAGE + ".server";
    /**
     * Client所在包
     */
    public static final String CLIENT_PACKAGE = BASE_PACKAGE + ".client";


    /**
     * Mapper所在包 com.zjbdos.ebb.shop.server.dao
     */
    public static final String MAPPER_PACKAGE = SERVER_PACKAGE + ".dao";

    /**
     * Entity所在包 com.zjbdos.ebb.shop.server.entity
     */
    public static final String ENTITY_PACKAGE = SERVER_PACKAGE + ".entity";

    /**
     * Mapper插件基础接口的完全限定名
     */
    public static final String MAPPER_INTERFACE_REFERENCE = "tk.mybatis.mapper.common.Mapper";


    private static final String AUTHOR = "zhuhanbin";
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

    public static void main(String[] args) throws ClassNotFoundException {




        //shop库中
        //genCode("t_run_shop_ip","ShopIp");
        //genCode("t_run_shop_platform","ShopPlatform");
        //genCode("t_run_shop_user","ShopUser");
        genCode("t_sys_shop_platform", "ShopPlatform");
        genCode("t_sys_cloud_platform", "CloudPlatform");

        genCode("t_sys_cloud_api", "CloudAPI");
        genCode("t_sys_cloud_image", "CloudImage");
        genCode("t_sys_cloud_region", "CloudRegion");
        genCode("t_sys_cloud_flavor", "CloudFlavor");
        //genMain("EbbShopApplication");

        //ip_platform
        //genCode("t_run_ip","Ip");
        //genCode("t_run_ip_platform","IpPlatform");
        //genMain("EbbIpPlatformApplication");


        //genCode("输入表名","输入自定义Model名称");

    }

    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     *
     * @param tableNames 数据表名称...
     */
    public static void genCode(String... tableNames) throws ClassNotFoundException {
        for (String tableName : tableNames) {
            genCode("T_Run_Ip", "Ip");
        }
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     *
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     */
    public static void genCode(String tableName, String modelName) throws ClassNotFoundException {
        File file1 = new File(PROJECT_PATH + JAVA_CLIENT_PATH);
        File file3 = new File(PROJECT_PATH + JAVA_SERVER_PATH);

        File file2 = new File(PROJECT_PATH + RESOURCES_PATH);
        if (!file1.getAbsoluteFile().exists()) {
            file1.getAbsoluteFile().mkdirs();
        }
        if (!file2.getAbsoluteFile().exists()) {
            file2.getAbsoluteFile().mkdirs();
        }
        if (!file3.getAbsoluteFile().exists()) {
            file3.getAbsoluteFile().mkdirs();
        }
        //(tableName, modelName);
        genModelAndMapper(tableName, modelName);

        List<BaseData> baseDataList = DatabaseUtil.getBaseDataList(tableName);
        String comment = DatabaseUtil.getCommentByTableName(tableName);


        Map<String, Object> data = new HashMap<String, Object>();
        data.put("baseDataList", baseDataList);

        data.put("serverPackage", CodeGenerator.SERVER_PACKAGE);
        data.put("clientPackage", CodeGenerator.CLIENT_PACKAGE);
        data.put("baseModelName", BASE_MODULE_NAME);

        data.put("tableName", tableName);
        data.put("modelNameUpperCamel", modelName);
        data.put("modelNameLowerCamel", tableNameConvertLowerCamel(modelName));
        data.put("applicationName", APPLICATION_NAME);
        data.put("apiurl", API_URL);
        data.put("comment", comment);


        //requestVO
        genJava(data, modelName, PROJECT_PATH + STORAGE_PATH_REQUESTVO, "Req", "VO", "VO.ftl");

        //responseVO
        genJava(data, modelName, PROJECT_PATH + STORAGE_PATH_RESPONSEVO, "Res", "VO", "VO.ftl");
        //DTO
        genJava(data, modelName, PROJECT_PATH + STORAGE_PATH_DTO, "", "DTO", "VO.ftl");

        //feign
        genJava(data, modelName, PROJECT_PATH + STORAGE_PATH_FEIGN, "", "ServiceFeign", "feignService.ftl");
        genJava(data, modelName, PROJECT_PATH + STORAGE_PATH_FEIGN_FACTORY, "", "ServiceFeignFactory", "feignFactoty.ftl");
        genJava(data, modelName, PROJECT_PATH + STORAGE_PATH_FEIGN_FALLBACK, "", "ServiceFeignImpl", "feignFallback.ftl");

        //server/service
        genJava(data, modelName, PROJECT_PATH + STORAGE_PATH_SERVICE, "", "Service", "serverService.ftl");
        genJava(data, modelName, PROJECT_PATH + STORAGE_PATH_SERVICE_IMPL, "", "ServiceImpl", "serverServiceImpl.ftl");
        //control
        genJava(data, modelName, PROJECT_PATH + STORAGE_PATH_CONTROLLER, "", "Controller", "controller.ftl");
        //converter
        genJava(data, modelName, PROJECT_PATH + STORAGE_PATH_CONVERTER, "", "Converter", "converter.ftl");

        genJava(data, "EbbShopApplication", PROJECT_PATH + STORAGE_PATH_MAIN, "", "", "main.ftl");
        genJava(data, "SwaggerConfig", PROJECT_PATH + STORAGE_PATH_SERVICE_CONFIG, "", "", "SwaggerConfig.ftl");


        //因为实体有继承baseEntity，所以要对entity重写
        //因为要对数据去掉重复，所有最后再重写entity
        //取出baseEntity中的属性，为后面的entity实体类去重复做准备
        Class<?> class1 = Class.forName("com.zjbdos.springcloud.common.server.entity.BaseEntity");
        Field[] fields = class1.getDeclaredFields();
        //Method[] methods = class1.getDeclaredMethods();     //得到类本身的所有方法

        List<BaseData> entityDataList=new ArrayList<BaseData>();
        for (BaseData b : baseDataList) {
            boolean isfound=false;
            for (Field f : fields) {
                if (b.getColumnName().equals(f.getName())){
                    isfound=true;
                    continue;
                }
            }
            if (!isfound){
                entityDataList.add(b);
            }
        }

        data.put("entityDataList",  entityDataList);
        genJava(data, modelName, PROJECT_PATH + STORAGE_PATH_ENTITYVO, "", "", "entity.ftl");
        //重写mapper
        genJava(data, modelName, PROJECT_PATH + STORAGE_PATH_MAPPER, "", "Mapper", "mapper.ftl");
    }


    public static void genModelAndMapper(String tableName, String modelName) {
        Context context = new Context(ModelType.FLAT);
        context.setId("Potato");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");


        //配置jdbc
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(JDBC_URL);
        jdbcConnectionConfiguration.setUserId(JDBC_USERNAME);
        jdbcConnectionConfiguration.setPassword(JDBC_PASSWORD);
        jdbcConnectionConfiguration.setDriverClass(JDBC_DIVER_CLASS_NAME);
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
        //配置插件
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        pluginConfiguration.addProperty("mappers", CodeGenerator.MAPPER_INTERFACE_REFERENCE);
        context.addPluginConfiguration(pluginConfiguration);

        //实体entity,TargetProject不需要全路径
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_SERVER_PATH);
        javaModelGeneratorConfiguration.setTargetPackage(CodeGenerator.ENTITY_PACKAGE);
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        //Mapper.xml
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(PROJECT_PATH + RESOURCES_PATH);
        sqlMapGeneratorConfiguration.setTargetPackage(MAPPER_PACKAGE);
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);


        //Mapper.java 接口
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_SERVER_PATH);
        javaClientGeneratorConfiguration.setTargetPackage(CodeGenerator.MAPPER_PACKAGE);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");

        //context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);


        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        tableConfiguration.setDomainObjectName(modelName);
        tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
        context.addTableConfiguration(tableConfiguration);

        List<String> warnings;
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();

            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            warnings = new ArrayList<String>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }

        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
        }
        if (StringUtils.isEmpty(modelName)) {
            modelName = tableNameConvertUpperCamel(tableName);
        }
        System.out.println(modelName + ".java 生成成功");
        System.out.println(modelName + "Mapper.java 生成成功");
        System.out.println(modelName + "Mapper.xml 生成成功");
    }


//

    public static void genJava(Map data, String modelNameUpperCamel, String savePath, String prefixName, String suffixName, String ftlName) {
        if (data == null) {
            data = new HashMap<String, Object>();
        }
        data.put("baseRequestMapping", modelNameConvertMappingPath(modelNameUpperCamel));
        data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));

        //生成文件名
        StringBuilder className = new StringBuilder(modelNameUpperCamel);

        try {
            if (!StringUtils.isEmpty(prefixName) && StringUtils.isNotBlank(prefixName)) {
                className.insert(0, prefixName);
            }
            if (!StringUtils.isEmpty(suffixName) && StringUtils.isNotBlank(suffixName)) {
                className.append(suffixName);
            }
            //创建文件
            File file = new File(savePath + className + ".java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            data.put("date", DATE);
            data.put("author", AUTHOR);
            data.put("className", className);
            freemarker.template.Configuration cfg = getConfiguration();
            cfg.getTemplate(ftlName).process(data, new FileWriter(file));
            System.out.println(className + ".java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException(prefixName + "," + suffixName, e);
        }

    }

    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }

    private static String tableNameConvertMappingPath(String tableName) {
        //兼容使用大写的表名
        tableName = tableName.toLowerCase();
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    private static String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

    //首字母大写
    private static String firstToCapital(String str) {
        char[] chars = str.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char) (chars[0] - 32);
        }
        return new String(chars);
    }

}