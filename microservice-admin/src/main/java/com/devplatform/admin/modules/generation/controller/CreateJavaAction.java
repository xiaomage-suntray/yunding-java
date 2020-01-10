package com.devplatform.admin.modules.generation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.devplatform.admin.modules.generation.bean.CreateJavaModel;
import com.devplatform.admin.modules.generation.util.CommonPageParser;
import com.devplatform.admin.modules.generation.util.CustomException;
import com.devplatform.admin.modules.generation.util.DBConnectionUtil;
import com.devplatform.admin.modules.generation.util.FieldModel;
import com.devplatform.admin.modules.generation.util.FtlUtil;
import com.devplatform.admin.modules.generation.util.JDBCQueryUtil;
import com.devplatform.admin.modules.generation.util.ValidateUtil;
import com.devplatform.common.controller.BaseController;
import com.devplatform.common.util.R;
import com.devplatform.common.util.StringUtil;

@Api(tags={"代码生成接口"})
@PropertySource("classpath:/createJava.properties")
@RestController
@RequestMapping("/createjava")
public class CreateJavaAction extends BaseController{
    
    @Value("${gpt.java.devPath}")
    private String java_dev_path; //= "G:\\environment\\Work\\SpringCloudPlatform\\merge\\microservice-admin\\src\\main\\java\\";
    @Value("${gpt.xml.devPath}")
    private String xml_dev_path;// = "G:\\environment\\Work\\SpringCloudPlatform\\merge\\microservice-admin\\src\\main\\resources\\";
    @Value("${gpt.template.devPath}")
    private String template_dev_path;
    @Value("${gpt.url}")
    private String defaultUrl;
    @Value("${gpt.username}")
    private String defaultUserName;
    @Value("${gpt.password}")
    private String defaultPassword;
    
    
    /**
     * 
     * @return
     * @throws Exception
     */
    @ApiOperation(value="获取数据库数据", notes="获取数据库数据")
    @GetMapping("/list")
    public R list() throws Exception {
        Map<String, Object> context = new HashMap<String, Object>();//new HashMap<String, Object>();
//        res = ResourceBundle.getBundle("createJava");
//        String defaultUrl = res.getString("gpt.url");
        String defaultIp = getIpFromUrl(defaultUrl);
        String defaultPort = getPortFromUrl(defaultUrl);
        String defaultDb = getDBFromUrl(defaultUrl);
//        String defaultUserName = res.getString("gpt.username");
//        String defaultPassword = res.getString("gpt.password");
//        String devPath = res.getString("gpt.devPath");
//        devPath = new String(res.getString("gpt.devPath").getBytes("ISO-8859-1"), "UTF-8");
        // 设置页面数据
        context.put("defaultIp", defaultIp);
        context.put("defaultPort", defaultPort);
        context.put("defaultDb", defaultDb);
        context.put("defaultUserName", defaultUserName);
        context.put("defaultPassword", defaultPassword);
        context.put("javaDevPath", java_dev_path);
        context.put("xmlDevPath", xml_dev_path);
        return R.ok(context);
    }
    
    /**
     * 
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value="获取Oracle数据库数据", notes="获取Oracle数据库数据")
    @GetMapping("/listOracle")
    public R listOracle() throws Exception {
        Map<String, Object> context = new HashMap<String, Object>();//new HashMap<String, Object>();
//        res = ResourceBundle.getBundle("createJavaOracle");
//        String defaultUrl = res.getString("gpt.url");
//        String defaultIp = getIpFromUrl(defaultUrl);
//        String defaultPort = getPortFromUrl(defaultUrl);
//        String defaultDb = getDBFromUrl(defaultUrl);
//        String defaultUserName = res.getString("gpt.username");
//        String defaultPassword = res.getString("gpt.password");
//        String devPath = res.getString("gpt.devPath");
//        devPath = new String(res.getString("gpt.devPath").getBytes("ISO-8859-1"), "UTF-8");
//        // 设置页面数据
//        context.put("defaultIp", defaultIp);
//        context.put("defaultPort", defaultPort);
//        context.put("defaultDb", defaultDb);
//        context.put("defaultUserName", defaultUserName);
//        context.put("defaultPassword", defaultPassword);
//        context.put("devPath", devPath);
        return R.ok(context);
    }
    
    /**
     * 
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value="验证并获取表信息", notes="验证并获取表信息")
//    @ApiImplicitParam(name = "model", value = "代码生成实体", required = true,paramType="query", dataType = "CreateJavaModel")
    @PostMapping("/viewTable")
    public Map<String, Object> viewTable(@RequestBody @ApiParam(value = "代码生成实体", required = true) CreateJavaModel model) throws Exception {
        Map<String, Object> context = new HashMap<String, Object>();//new HashMap<String, Object>();
        String tableSql = "select distinct column_name, data_type, column_comment, ordinal_position, column_key, is_nullable, character_maximum_length max_length from information_schema.columns c where table_name = ? and table_schema = ?  order by ordinal_position asc";
        Connection conn = null;
        if (model.getDbType() == 1) {
            String url = "jdbc:mysql://_ip_:_port_/_dbName_?useUnicode=true&amp;charaterEncoding=utf-8&amp;autoReconnect=true";
            url = url.replaceAll("_ip_", model.getIp()).replaceAll("_port_", model.getPort()).replaceAll("_dbName_", model.getDbName());
            try {
                conn = DBConnectionUtil.getMysqlConnection(url, model.getUsername(), model.getDbpwd());
                context.put("success", true);
                context.put("msg", "验证成功");
            }
            catch (Exception e) {
                context.put("success", false);
                context.put("msg", e.toString());
            }
        }
        else if (model.getDbType() == 2) {
            // conn = DBConnectionUtil.getOracleConnection(url,
            // model.getUsername(), model.getDbpwd());
            return null;// 暂时由于没有写获取连接等方法，为避免后续报错，此处返回null，不执行后续语句
        }
        else if (model.getDbType() == 3) {
            
            return null;// 暂时由于没有写获取连接等方法，为避免后续报错，此处返回null，不执行后续语句
        }
        else if (model.getDbType() == 4) {
            
            return null;// 暂时由于没有写获取连接等方法，为避免后续报错，此处返回null，不执行后续语句
        }
        else {
            return null;// 暂时由于没有写获取连接等方法，为避免后续报错，此处返回null，不执行后续语句
        }
        
        if (model.getCurrentIndex() != null && model.getCurrentIndex().equals("1")) {
        	
        	if((boolean) context.get("success")){
        		return R.ok(context.get("msg").toString());
        	}else{
        		return R.error(context.get("msg").toString());
        	}
        }
        List<Map<String, Object>> results = JDBCQueryUtil.commonQueryList(conn, tableSql, model.getTableName(), model.getDbName());
        if (results != null && results.size() > 0) {
            for (Map<String, Object> result : results) {
                result.put("MAX_LENGTH", result.get("CHARACTER_MAXIMUM_LENGTH"));
            }
            context.put("data", results);
            context.put("success", true);
            context.put("msg", "验证成功");
        }
        else {
            context.put("success", false);
            context.put("msg", "没有此表");
        }
        
        if((boolean) context.get("success")){
    		return R.ok(context);
    	}else{
    		return R.error(context.get("msg").toString());
    	}
    }
    
    /**
     * 
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value="验证并获取Oracle表信息", notes="验证并获取Oracle表信息")
//    @ApiImplicitParam(name = "model", value = "代码生成实体", required = true,paramType="query", dataType = "CreateJavaModel")
    @PostMapping("/viewTableOracle")
    public R viewTableOracle(@RequestBody @ApiParam(value = "代码生成实体", required = true)CreateJavaModel model) throws Exception {
        Map<String, Object> context = new HashMap<String, Object>();//new HashMap<String, Object>();
        String tableSql = "SELECT c.COMMENTS as column_comment";
        tableSql += ",T .*,T.NULLABLE as is_nullable FROM user_tab_columns T, user_col_comments c WHERE ";
        tableSql += " T .table_name = c.table_name";
        tableSql += " AND T .column_name = c.column_name";
        tableSql += " AND T .table_name = ?  order by column_id asc ";
        
        Connection conn = null;
        String url = model.getIp();
        try {
            conn = DBConnectionUtil.getOracleConnection(url, model.getUsername(), model.getDbpwd());
            context.put("success", true);
            context.put("msg", "验证成功");
        }
        catch (Exception e) {
            context.put("success", false);
            context.put("msg", e.toString());
        }
        if (model.getCurrentIndex() != null && model.getCurrentIndex().equals("1")) {
        	if((boolean) context.get("success")){
        		return R.ok(context.get("msg").toString());
        	}else{
        		return R.error(context.get("msg").toString());
        	}
        }
        //		String tableAll = "select  * from user_tab_comments where  ROWNUM <10000";
        String tableAll = "select  * from user_tab_comments where  table_name in ('TD_B_COMPENSATORY_DETAIL_INFO')";
        List<Map<String, Object>> ta = JDBCQueryUtil.commonQueryList(conn, tableAll);
        for (int i = 0; i < ta.size(); i++) {
            Map<String, Object> x = ta.get(i);
            
            if (x.containsKey("TABLE_NAME")) {
                if (!x.get("TABLE_NAME").toString().split("_")[0].equals("Wf")) {
                    System.out.println(x.get("TABLE_NAME").toString());
                    model.setTableName(x.get("TABLE_NAME").toString());
                    if (x.get("COMMENTS") != null) {
                        model.setTableRemark(x.get("COMMENTS").toString());
                    }
                    List<Map<String, Object>> results = JDBCQueryUtil.commonQueryList(conn, tableSql, model.getTableName());
                    model.setPfix(ResourceBundle.getBundle("createJava").getString("gpt.pfix"));
                    model.setFields(getFieldsFromJSONList(results));
                    model.setTemplateFolder(getRequest().getSession().getServletContext().getRealPath("/") + CommonPageParser.FGF + "template");
                    createOracle(model);
                }
            }
        }
        context.put("success", true);
        context.put("msg", "验证成功");
        if((boolean) context.get("success")){
    		return R.ok(context.get("msg").toString());
    	}else{
    		return R.error(context.get("msg").toString());
    	}
    }
    
    private String getIpFromUrl(String source) {
        if (ValidateUtil.isNotEmpty(source)) {
            String[] temp = source.split(":");
            if (temp != null && temp.length == 4) {
                return temp[2].replaceAll("//", "");
            }
        }
        
        return source;
    }
    
    private static String getPortFromUrl(String source) {
        if (ValidateUtil.isNotEmpty(source)) {
            String[] temp = source.split(":");
            if (temp != null && temp.length == 4) {
                return temp[3].split("/")[0];
            }
        }
        return source;
    }
    
    private static String getDBFromUrl(String source) {
        if (ValidateUtil.isNotEmpty(source)) {
            String[] temp = source.split(":");
            if (temp != null && temp.length == 4) {
                return temp[3].split("/")[1];
            }
        }
        return source;
    }
    
    private String mysqlDBUrl(CreateJavaModel model) {
        String url = "jdbc:mysql://_ip_:_port_/_dbName_?useUnicode=true&amp;charaterEncoding=utf-8&amp;autoReconnect=true";
        url = url.replaceAll("_ip_", model.getIp()).replaceAll("_port_", model.getPort()).replaceAll("_dbName_", model.getDbName());
        return url;
    }
    
    private List<FieldModel> getFieldsFromJSON(String json_string) {
        if (ValidateUtil.isEmpty(json_string)) {
            return null;
        }
        JSONArray jsonArray = null;
        try {
            jsonArray = JSONArray.parseArray(json_string);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (jsonArray == null) {
            return null;
        }
        
        List<FieldModel> fields = new ArrayList<FieldModel>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject x = (JSONObject) jsonArray.get(i);
            FieldModel field = new FieldModel();
            if (x.containsKey("IS_NULLABLE")) {
                field.setCanBeNull(x.getString("IS_NULLABLE"));
            }
            if (x.containsKey("ENUMAT")) {
                field.setEnumString(x.getString("ENUMAT"));
            }
            if (x.containsKey("COLUMN_NAME")) {
                field.setFieldName(x.getString("COLUMN_NAME"));
            }
            if (x.containsKey("COLUMN_COMMENT")) {
                field.setFieldRemark(x.getString("COLUMN_COMMENT"));
            }
            if (x.containsKey("DATA_TYPE")) {
                field.setFieldType(x.getString("DATA_TYPE"));
            }
            if (x.containsKey("IS_EMAIL")) {
                field.setIsEmail(x.getString("IS_EMAIL"));
            }
            if (x.containsKey("IS_IDNO")) {
                field.setIsIdNo(x.getString("IS_IDNO"));
            }
            if (x.containsKey("IS_MOBILE")) {
                field.setIsMobile(x.getString("IS_MOBILE"));
            }
            if (x.containsKey("MAX_LENGTH")) {
                String t = x.getString("MAX_LENGTH");
                if (ValidateUtil.isNotEmpty(t)) {
                    field.setMaxLength(Integer.parseInt(t));
                }
            }
            if (x.containsKey("MIN_LENGTH")) {
                String t = x.getString("MIN_LENGTH");
                if (ValidateUtil.isNotEmpty(t)) {
                    field.setMaxLength(Integer.parseInt(t));
                }
            }
            if (x.containsKey("REGEX")) {
                field.setRegexString(x.getString("REGEX"));
            }
            fields.add(field);
        }
        System.out.println(fields);
        
        return fields;
        
    }
    
    private List<FieldModel> getFieldsFromJSONList(List<Map<String, Object>> json_string) {
        if (ValidateUtil.isEmpty(json_string)) {
            return null;
        }
        List<Map<String, Object>> jsonArray = json_string;
        List<FieldModel> fields = new ArrayList<FieldModel>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Map<String, Object> x = jsonArray.get(i);
            FieldModel field = new FieldModel();
            if (x.containsKey("IS_NULLABLE")) {
                field.setCanBeNull(x.get("IS_NULLABLE").toString());
            }
            if (x.containsKey("ENUMAT")) {
                field.setEnumString(x.get("ENUMAT").toString());
            }
            if (x.containsKey("COLUMN_NAME")) {
                field.setFieldName(x.get("COLUMN_NAME").toString());
            }
            if (x.containsKey("COLUMN_COMMENT")) {
                if (x.get("COLUMN_COMMENT") != null) {
                    field.setFieldRemark(x.get("COLUMN_COMMENT").toString());
                }
            }
            if (x.containsKey("DATA_TYPE")) {
                field.setFieldType(x.get("DATA_TYPE").toString());
            }
            if (x.containsKey("IS_EMAIL")) {
                field.setIsEmail(x.get("IS_EMAIL").toString());
            }
            if (x.containsKey("IS_IDNO")) {
                field.setIsIdNo(x.get("IS_IDNO").toString());
            }
            if (x.containsKey("IS_MOBILE")) {
                field.setIsMobile(x.get("IS_MOBILE").toString());
            }
            if (x.containsKey("REGEX")) {
                field.setRegexString(x.get("REGEX").toString());
            }
            fields.add(field);
        }
        System.out.println(fields);
        
        return fields;
        
    }
    
    /**
     * 生成文件
     * @param model
     * @param request
     * @return
     */
    @ApiOperation(value="生成文件", notes="生成文件")
    @PostMapping("/createFile")
    public R createFile(@RequestBody @ApiParam(value = "代码生成实体", required = true)CreateJavaModel model) {
        String returnString = "生成成功，请查看";
        mysqlDBUrl(model);//拼接数据库连接字符串
        model.setPfix(ResourceBundle.getBundle("createJava").getString("gpt.pfix"));
        model.setFields(getFieldsFromJSON(model.getJson_string()));
        model.setTemplateFolder(template_dev_path + "template");
        java_dev_path = model.getJavaDevPath();
        xml_dev_path = model.getXmlDevPath();
        create(model);
        return R.ok(returnString);
    }
    
    
    
    public void create(CreateJavaModel model) {
        if (Boolean.parseBoolean(model.getIsBean())) {
            createBean(model);
        }
        if (Boolean.parseBoolean(model.getIsController())) {
            createController(model);
        }
//        if ("1".equals(model.getIsJs())) {
//            createJs(model);
//        }
//        if ("1".equals(model.getIsListJsp())) {
//            createListJsp(model);
//        }
        if (Boolean.parseBoolean(model.getIsMapperJava())) {
            createMapperJava(model);
        }
        if (Boolean.parseBoolean(model.getIsMapperXMl())) {
            createMapperXML(model);
        }
        if (Boolean.parseBoolean(model.getIsService())) {
            createService(model);
            createServiceImpl(model);
        }
        if (Boolean.parseBoolean(model.getIsModel())) {
            createModel(model);
        }
    }
    
    private void createService(CreateJavaModel model) {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("bean", model);
        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempService.java", root);
        content = content.replace("_#_", "$");
        //		writeFile(createFile(model.getDevPath()+"src"+CommonPageParser.FGF+"main"+CommonPageParser.FGF+"java"+CommonPageParser.FGF+"com"+CommonPageParser.FGF+"devplatform"+CommonPageParser.FGF+"modules"+CommonPageParser.FGF+"service"+CommonPageParser.FGF,model.getClassName()+"Service","java"), content);
        writeFile(createFile(java_dev_path 
//        		+ "com" + CommonPageParser.FGF + "devplatform" 
//        		+ CommonPageParser.FGF + "admin" + CommonPageParser.FGF + "modules"
        		+ CommonPageParser.FGF + model.getModulesName()+ CommonPageParser.FGF + "service" 
        		+ CommonPageParser.FGF,model.getClassName() + "Service", "java"), content);
    }
    
    private void createServiceImpl(CreateJavaModel model) {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("bean", model);
        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempServiceImpl.java", root);
        content = content.replace("_#_", "$");
        //		writeFile(createFile(model.getDevPath()+"src"+CommonPageParser.FGF+"main"+CommonPageParser.FGF+"java"+CommonPageParser.FGF+"com"+CommonPageParser.FGF+"devplatform"+CommonPageParser.FGF+"modules"+CommonPageParser.FGF+"service"+CommonPageParser.FGF,model.getClassName()+"Service","java"), content);
        writeFile(createFile(java_dev_path 
//        		+ "com" + CommonPageParser.FGF + "devplatform" 
//        		+ CommonPageParser.FGF + "admin" + CommonPageParser.FGF + "modules"
        		+ CommonPageParser.FGF + model.getModulesName()+ CommonPageParser.FGF + "service" 
        		+ CommonPageParser.FGF + "impl"+ CommonPageParser.FGF,model.getClassName() + "ServiceImpl", "java"), content);
    }
    
    private void createMapperXML(CreateJavaModel model) {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("bean", model);
        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempMapper.xml", root);
        content = content.replace("_$_", "$");
        content = content.replace("_#_", "#");
        System.out.println(content);
        writeFile(createFile(xml_dev_path + "mapper" + CommonPageParser.FGF + model.getModulesName() 
        		+ CommonPageParser.FGF,model.getClassName() + "Mapper", "xml"), content);
//        writeFile(createFile(model.getDevPath() + "com" + CommonPageParser.FGF + "cnpay" + CommonPageParser.FGF + "java" + CommonPageParser.FGF
//                + "com" + CommonPageParser.FGF + "devplatform" + CommonPageParser.FGF + "modules" + CommonPageParser.FGF + "mybatis"
//                + CommonPageParser.FGF, model.getClassName() + "Mapper", "xml"), content);
        // TODO Auto-generated method stub 需要补充修改mybatis.XML的方法
    }
    
    private void createMapperJava(CreateJavaModel model) {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("bean", model);
        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempMapper.java", root);
        content = content.replace("_#_", "$");
        writeFile(createFile(java_dev_path 
//        		+ "com"+ CommonPageParser.FGF + "devplatform" 
//        		+ CommonPageParser.FGF + "admin" + CommonPageParser.FGF + "modules" 
        		+ CommonPageParser.FGF + model.getModulesName() + CommonPageParser.FGF + "dao" 
        		+ CommonPageParser.FGF,model.getClassName() + "Dao", "java"), content);
    }
    
//    private void createListJsp(CreateJavaModel model) {
//        Map<String, Object> root = new HashMap<String, Object>();
//        root.put("bean", model);
//        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempList.jsp", root);
//        content = content.replace("_#_", "$");
//        System.out.println("--------------------->jsp" + content);
//        writeFile(createFile(
//                model.getDevPath() + "src" + CommonPageParser.FGF + "main" + CommonPageParser.FGF + "webapp" + CommonPageParser.FGF + "WEB-INF"
//                        + CommonPageParser.FGF + "view" + CommonPageParser.FGF + model.getLowerName() + CommonPageParser.FGF,
//                model.getLowerName() + "List", "jsp"), content);
//    }
//    
//    private void createJs(CreateJavaModel model) {
//        Map<String, Object> root = new HashMap<String, Object>();
//        root.put("bean", model);
//        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempJs.js", root);
//        content = content.replace("_#_", "$");
//        System.out.println("--------------------->js" + content);
//        writeFile(createFile(model.getDevPath() + "src" + CommonPageParser.FGF + "main" + CommonPageParser.FGF + "webapp" + CommonPageParser.FGF
//                + "js" + CommonPageParser.FGF + model.getLowerName() + CommonPageParser.FGF, model.getLowerName(), "js"), content);
//    }
    
    private void createController(CreateJavaModel model) {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("bean", model);
        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempController.java", root);
        content = content.replace("_#_", "$");
        writeFile(createFile(java_dev_path 
//        		+ "com" + CommonPageParser.FGF + "devplatform" 
//        		+ CommonPageParser.FGF + "admin" + CommonPageParser.FGF + "modules" 
        		+ CommonPageParser.FGF + model.getModulesName() + CommonPageParser.FGF + "controller" 
        		+ CommonPageParser.FGF,model.getClassName() + "Controller", "java"), content);
    }
    
    private void createBean(CreateJavaModel model) {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("bean", model);
        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempBean.java", root);
        content = content.replace("_#_", "$");
        writeFile(createFile(java_dev_path 
//        		+ "com" + CommonPageParser.FGF + "devplatform"
//        		+ CommonPageParser.FGF + "admin" + CommonPageParser.FGF + "modules" 
        		+ CommonPageParser.FGF + model.getModulesName() + CommonPageParser.FGF + "bean" 
        		+ CommonPageParser.FGF, model.getClassName(), "java"), content);
    }
    
    private void createModel(CreateJavaModel model) {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("bean", model);
        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempModel.java", root);
        content = content.replace("_#_", "$");
        writeFile(createFile(java_dev_path 
//        		+ "com" + CommonPageParser.FGF + "devplatform"
//        		+ CommonPageParser.FGF + "admin" + CommonPageParser.FGF + "modules" 
        		+ CommonPageParser.FGF + model.getModulesName() + CommonPageParser.FGF + "model" 
        		+ CommonPageParser.FGF,model.getClassName() + "Model", "java"), content);
    }
    
    private void writeFile(File beanFile, String content) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile), "UTF-8"));
            bw.write(content);
            bw.flush();
            bw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("文件出错", "0102");
        }
    }
    
    private File createFile(String targetPath, String fileName, String fileExt) {
        File folder = new File(targetPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File beanFile = new File(targetPath, fileName + "." + fileExt);
        try {
            beanFile.createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println(beanFile.getPath());
            throw new CustomException("文件出错", "0102");
        }
        return beanFile;
    }
    
    private void createOracle(CreateJavaModel model) {
        createBeanOracle(model);
        craateActionOracle(model);
        //		createOracleModel(model);
        createOracleService(model);
        createOracleImplService(model);
        createOracleDaoJava(model);
        createMabitisMapperXML(model);
    }
    
    private void createBeanOracle(CreateJavaModel model) {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("bean", model);
        //        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempOracleBean.java", root);
        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempOracleHibernateBean.java", root);
        content = content.replace("_#_", "$");
        writeFile(createFile(model.getJavaDevPath() + "src" + CommonPageParser.FGF + "main" + CommonPageParser.FGF + "java" + CommonPageParser.FGF + "com"
                + CommonPageParser.FGF + "devplatform" + CommonPageParser.FGF + "modules" + CommonPageParser.FGF + "bean" + CommonPageParser.FGF
                + model.getPacageName() + CommonPageParser.FGF, model.getClassOracleName(), "java"), content);
    }
    
    private void craateActionOracle(CreateJavaModel model) {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("bean", model);
        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempOracleController.java", root);
        content = content.replace("_#_", "$");
        writeFile(createFile(
                model.getJavaDevPath() + "src" + CommonPageParser.FGF + "main" + CommonPageParser.FGF + "java" + CommonPageParser.FGF + "com"
                        + CommonPageParser.FGF + "sjw" + CommonPageParser.FGF + "manager" + CommonPageParser.FGF + model.getPacageName()
                        + CommonPageParser.FGF + CommonPageParser.FGF + "controller" + CommonPageParser.FGF,
                model.getClassOracleName() + "Controller", "java"), content);
    }
    
    private void createOracleModel(CreateJavaModel model) {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("bean", model);
        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempOracleModel.java", root);
        content = content.replace("_#_", "$");
        writeFile(createFile(model.getJavaDevPath() + "src" + CommonPageParser.FGF + "main" + CommonPageParser.FGF + "java" + CommonPageParser.FGF + "com"
                + CommonPageParser.FGF + "sjw" + CommonPageParser.FGF + "manager" + CommonPageParser.FGF + model.getPacageName()
                + CommonPageParser.FGF + "vo" + CommonPageParser.FGF, model.getClassOracleName() + "Vo", "java"), content);
    }
    
    private void createOracleService(CreateJavaModel model) {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("bean", model);
        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempOracleService.java", root);
        content = content.replace("_#_", "$");
        writeFile(createFile(model.getJavaDevPath() + "src" + CommonPageParser.FGF + "main" + CommonPageParser.FGF + "java" + CommonPageParser.FGF + "com"
                + CommonPageParser.FGF + "sjw" + CommonPageParser.FGF + "manager" + CommonPageParser.FGF + model.getPacageName()
                + CommonPageParser.FGF + "service" + CommonPageParser.FGF, model.getClassOracleName() + "Service", "java"), content);
    }
    
    private void createOracleImplService(CreateJavaModel model) {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("bean", model);
        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempOracleImplService.java", root);
        content = content.replace("_#_", "$");
        writeFile(createFile(
                model.getJavaDevPath() + "src" + CommonPageParser.FGF + "main" + CommonPageParser.FGF + "java" + CommonPageParser.FGF + "com"
                        + CommonPageParser.FGF + "sjw" + CommonPageParser.FGF + "manager" + CommonPageParser.FGF + model.getPacageName()
                        + CommonPageParser.FGF + "service" + CommonPageParser.FGF + "impl" + CommonPageParser.FGF,
                model.getClassOracleName() + "ServiceImpl", "java"), content);
    }
    
    private void createOracleDaoJava(CreateJavaModel model) {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("bean", model);
        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempOracleMapper.java", root);
        content = content.replace("_#_", "$");
        writeFile(
                createFile(model.getJavaDevPath() + "src" + CommonPageParser.FGF + "main" + CommonPageParser.FGF + "java" + CommonPageParser.FGF + "com"
                        + CommonPageParser.FGF + "devplatform" + CommonPageParser.FGF + "modules" + CommonPageParser.FGF + "dao"
                        + CommonPageParser.FGF + model.getPacageName() + CommonPageParser.FGF, model.getClassOracleName() + "Dao", "java"),
                content);
    }
    
    private void createMabitisMapperXML(CreateJavaModel model) {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("bean", model);
        String content = FtlUtil.analysisTemplate(model.getTemplateFolder(), "TempOracleMapper.xml", root);
        content = content.replace("_$_", "$");
        content = content.replace("_#_", "#");
        System.out.println(content);
        writeFile(createFile(
                model.getJavaDevPath() + "src" + CommonPageParser.FGF + "main" + CommonPageParser.FGF + "resources" + CommonPageParser.FGF
                        + "mybatis-mapping" + CommonPageParser.FGF + model.getPacageName() + CommonPageParser.FGF,
                model.getClassOracleName() + "-mapper", "xml"), content);
        // TODO Auto-generated method stub 需要补充修改mybatis.XML的方法
    }
}
