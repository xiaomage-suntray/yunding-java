
package com.devplatform.admin.modules.generation.bean;

import java.util.List;

import com.devplatform.admin.modules.generation.util.FieldModel;
import com.devplatform.admin.modules.generation.util.ValidateUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "代码生成对象", description = "代码生成对象")
public class CreateJavaModel {
    
    @ApiModelProperty(value = "数据库类型", name = "dbType")
    private int dbType;// 数据库类型
    
    @ApiModelProperty(value = "数据库ip", name = "ip")
    private String ip;// 数据库ip
    
    @ApiModelProperty(value = "数据库端口", name = "port")
    private String port;// 数据库端口
    
    @ApiModelProperty(value = "数据库名称", name = "dbName")
    private String dbName;// 数据库名称
    
    @ApiModelProperty(value = "用户名", name = "username")
    private String username;// 用户名
    
    @ApiModelProperty(value = "密码", name = "dbpwd")
    private String dbpwd;// 密码
    
    @ApiModelProperty(value = "要生成的表名", name = "tableName")
    private String tableName;// 要生成的表名
    
    @ApiModelProperty(value = "表名前缀", name = "pfix")
    private String pfix;// 表名前缀
    
    @ApiModelProperty(value = "表备注信息", name = "tableRemark")
    private String tableRemark;// 表备注信息
    
    @ApiModelProperty(value = "生成java文件的存放路径", name = "devPath")
    private String javaDevPath;// 生成文件的存放路径
    
    @ApiModelProperty(value = "生成xml文件的存放路径", name = "devPath")
    private String xmlDevPath;// 生成文件的存放路径
    
    @ApiModelProperty(value = "模板地址", name = "templateFolder")
    private String templateFolder;//模板地址
    
    private String currentIndex;
    
    private String isShwoMenu;// 是否生成菜单
    
    private String isAddJsp;
    
    private String isBean;
    
    private String isController;
    
    private String isEditjsp;
    
    private String isJs;
    
    private String isListJsp;
    
    private String isMapperJava;
    
    private String isMapperXMl;
    
    private String isService;
    
    private String isModel;
    
    private String className;
    
    private String classOracleName;
    
    private String lowerName;
    
    private String lowerOracleName;
    
    private String pacageName;
    
    @ApiModelProperty(value = "模块名称", name = "modulesName")
    private String modulesName;//模块名称
    
    @ApiModelProperty(value = "表字段的集合", name = "fields")
    private List<FieldModel> fields;// 列的集合
    
    @ApiModelProperty(value = "表字段的集合", name = "json_string")
    private String json_string;//列集合
    
    public String getLowerName() {
        String tempName = String.valueOf(this.tableName).toLowerCase();
        if (tempName != null) {
            if (ValidateUtil.isNotEmpty(pfix)) {
                tempName = tempName.replaceAll(pfix.toLowerCase(), "");
            }
            StringBuffer sb = new StringBuffer(tempName.length());
            String tableNew = tempName.toLowerCase();
            String[] tables = tableNew.split("_");
            String temp = null;
            for (int i = 0; i < tables.length; i++) {
                temp = tables[i].trim();
                sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
            }
            lowerName = sb.toString();
            lowerName = lowerName.substring(0, 1).toLowerCase() + lowerName.substring(1);
        }
        return lowerName;
    }
    
    public void setLowerName(String lowerName) {
        this.lowerName = lowerName;
    }
    
    public String getClassName() {
        String tempName = String.valueOf(this.tableName).toLowerCase();
        if (tempName != null) {
            if (ValidateUtil.isNotEmpty(pfix)) {
                tempName = tempName.replaceAll(pfix.toLowerCase(), "");
            }
            StringBuffer sb = new StringBuffer(tempName.length());
            String tableNew = tempName.toLowerCase();
            String[] tables = tableNew.split("_");
            String temp = null;
            for (int i = 0; i < tables.length; i++) {
                temp = tables[i].trim();
                sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
            }
            className = sb.toString();
        }
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public String getJavaDevPath() {
        return javaDevPath;
    }
    
    public void setJavaDevPath(String javaDevPath) {
        this.javaDevPath = javaDevPath;
    }
    
    public String getXmlDevPath() {
        return xmlDevPath;
    }
    
    public void setXmlDevPath(String xmlDevPath) {
        this.xmlDevPath = xmlDevPath;
    }
    
    public int getDbType() {
        return dbType;
    }
    
    public void setDbType(int dbType) {
        this.dbType = dbType;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getPort() {
        return port;
    }
    
    public void setPort(String port) {
        this.port = port;
    }
    
    public String getDbName() {
        return dbName;
    }
    
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getDbpwd() {
        return dbpwd;
    }
    
    public void setDbpwd(String dbpwd) {
        this.dbpwd = dbpwd;
    }
    
    public String getTableName() {
        return tableName;
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    public String getPfix() {
        return pfix;
    }
    
    public void setPfix(String pfix) {
        this.pfix = pfix;
    }
    
    public String getTableRemark() {
        return tableRemark;
    }
    
    public void setTableRemark(String tableRemark) {
        this.tableRemark = tableRemark;
    }
    
    public String getIsShwoMenu() {
        return isShwoMenu;
    }
    
    public void setIsShwoMenu(String isShwoMenu) {
        this.isShwoMenu = isShwoMenu;
    }
    
    public String getCurrentIndex() {
        return currentIndex;
    }
    
    public void setCurrentIndex(String currentIndex) {
        this.currentIndex = currentIndex;
    }
    
    public List<FieldModel> getFields() {
        return fields;
    }
    
    public void setFields(List<FieldModel> fields) {
        this.fields = fields;
    }
    
    public String getIsAddJsp() {
        return isAddJsp;
    }
    
    public void setIsAddJsp(String isAddJsp) {
        this.isAddJsp = isAddJsp;
    }
    
    public String getIsBean() {
        return isBean;
    }
    
    public void setIsBean(String isBean) {
        this.isBean = isBean;
    }
    
    public String getIsController() {
        return isController;
    }
    
    public void setIsController(String isController) {
        this.isController = isController;
    }
    
    public String getIsEditjsp() {
        return isEditjsp;
    }
    
    public void setIsEditjsp(String isEditjsp) {
        this.isEditjsp = isEditjsp;
    }
    
    public String getIsJs() {
        return isJs;
    }
    
    public void setIsJs(String isJs) {
        this.isJs = isJs;
    }
    
    public String getIsListJsp() {
        return isListJsp;
    }
    
    public void setIsListJsp(String isListJsp) {
        this.isListJsp = isListJsp;
    }
    
    public String getIsMapperJava() {
        return isMapperJava;
    }
    
    public void setIsMapperJava(String isMapperJava) {
        this.isMapperJava = isMapperJava;
    }
    
    public String getIsMapperXMl() {
        return isMapperXMl;
    }
    
    public void setIsMapperXMl(String isMapperXMl) {
        this.isMapperXMl = isMapperXMl;
    }
    
    public String getIsService() {
        return isService;
    }
    
    public String getIsModel() {
        return isModel;
    }
    
    public void setIsModel(String isModel) {
        this.isModel = isModel;
    }
    
    public void setIsService(String isService) {
        this.isService = isService;
    }
    
    public String getTemplateFolder() {
        return templateFolder;
    }
    
    public void setTemplateFolder(String templateFolder) {
        this.templateFolder = templateFolder;
    }
    
    public String getClassOracleName() {
        String tempName = String.valueOf(this.tableName).toLowerCase();
        //			tempName = tempName.substring(tempName.indexOf("_")+1,tempName.length());
        //			if(tempName.split("_")[0].equals("a")){
        //			   tempName = tempName.replaceFirst("a", "account");
        //			}
        //			if(tempName.split("_")[0].equals("b")){
        //				tempName = tempName.replaceFirst("b", "busi");
        //			}
        //			if(tempName.split("_")[0].equals("ct")){
        //				tempName = tempName.replaceFirst("ct", "contract");
        //			}
        //			if(tempName.split("_")[0].equals("s")){
        //				tempName = tempName.replaceFirst("s", "system");
        //			}
        //			if(tempName.split("_")[0].equals("ms")){
        //				tempName = tempName.replaceFirst("ms", "message");
        //			}
        //			if(tempName.split("_")[0].equals("m")){
        //				tempName = tempName.replaceFirst("m", "marketing");
        //			}
        //			if(tempName.split("_")[0].equals("i")){
        //				tempName = tempName.replaceFirst("i", "uipInterface");
        //			}
        //			if(tempName.split("_")[0].equals("wf")){
        //				tempName = tempName.replaceFirst("wf", "wf");
        //			}
        if (tempName != null) {
            StringBuffer sb = new StringBuffer(tempName.length());
            String tableNew = tempName.toLowerCase();
            String[] tables = tableNew.split("_");
            String temp = null;
            for (int i = 0; i < tables.length; i++) {
                temp = tables[i].trim();
                sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
            }
            classOracleName = sb.toString();
        }
        return classOracleName;
    }
    
    public void setClassOracleName(String classOracleName) {
        this.classOracleName = classOracleName;
    }
    
    public String getPacageName() {
        String pacageName = "";
        String osName = System.getProperty("os.name");
        System.out.println(osName);
        if (osName.startsWith("Mac OS")) {
            // 苹果
            //设置包名
            pacageName = this.javaDevPath.substring(this.javaDevPath.indexOf("//src//main//java//") + 19);
            pacageName = pacageName.replace("//", ".");
        }
        else if (osName.startsWith("Windows")) {
            // windows
            //设置包名
            pacageName = this.javaDevPath.substring(this.javaDevPath.indexOf("\\src\\main\\java\\") + 15);
            pacageName = pacageName.replace("\\", ".");
        }
        else {
            // unix or linux
        }
        //设置包名
        return pacageName;
    }
    
    public void setPacageName(String pacageName) {
        this.pacageName = pacageName;
    }
    
    public String getLowerOracleName() {
        
        String tempName = String.valueOf(this.tableName).toLowerCase();
        tempName = tempName.substring(tempName.indexOf("_") + 1, tempName.length());
        //		if(tempName.split("_")[0].equals("a")){
        //		   tempName = tempName.replaceFirst("a", "account");
        //		}
        //		if(tempName.split("_")[0].equals("b")){
        //			tempName = tempName.replaceFirst("b", "busi");
        //		}
        //		if(tempName.split("_")[0].equals("ct")){
        //			tempName = tempName.replaceFirst("ct", "contract");
        //		}
        //		if(tempName.split("_")[0].equals("s")){
        //			tempName = tempName.replaceFirst("s", "system");
        //		}
        //		if(tempName.split("_")[0].equals("ms")){
        //			tempName = tempName.replaceFirst("ms", "message");
        //		}
        //		if(tempName.split("_")[0].equals("m")){
        //			tempName = tempName.replaceFirst("m", "marketing");
        //		}
        //		if(tempName.split("_")[0].equals("i")){
        //			tempName = tempName.replaceFirst("i", "uipInterface");
        //		}
        //		if(tempName.split("_")[0].equals("wf")){
        //			tempName = tempName.replaceFirst("wf", "wf");
        //		}
        if (tempName != null) {
            StringBuffer sb = new StringBuffer(tempName.length());
            String tableNew = tempName.toLowerCase();
            String[] tables = tableNew.split("_");
            String temp = null;
            for (int i = 0; i < tables.length; i++) {
                temp = tables[i].trim();
                sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
            }
            lowerOracleName = sb.toString();
            lowerOracleName = lowerOracleName.substring(0, 1).toLowerCase() + lowerOracleName.substring(1);
        }
        return lowerOracleName;
    }
    
    public void setLowerOracleName(String lowerOracleName) {
        this.lowerOracleName = lowerOracleName;
    }
    
    public String getModulesName() {
        return modulesName;
    }
    
    public void setModulesName(String modulesName) {
        this.modulesName = modulesName;
    }
    
    public String getJson_string() {
        return json_string;
    }
    
    public void setJson_string(String json_string) {
        this.json_string = json_string;
    }
    
}
