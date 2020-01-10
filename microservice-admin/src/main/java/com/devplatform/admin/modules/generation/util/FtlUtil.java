
package com.devplatform.admin.modules.generation.util;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class FtlUtil {
    
    public static String analysisTemplate(String templatePath, String templateName, Map<?, ?> root) {
        try {
            Configuration config = new Configuration();
            // 设置要解析的模板所在的目录，并加载模板文件
            File tp = new File(templatePath);
            config.setDirectoryForTemplateLoading(tp);
            // 设置包装器，并将对象包装为数据模型
            config.setObjectWrapper(new DefaultObjectWrapper());
            // 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
            // 否则会出现乱码
            Template template = config.getTemplate(templateName, "UTF-8");
            
            Writer out = new StringWriter();
            template.process(root, out);
            return out.toString();
        }
        catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }
}
