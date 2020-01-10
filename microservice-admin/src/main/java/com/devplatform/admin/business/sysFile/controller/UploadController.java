

package com.devplatform.admin.business.sysFile.controller;

import com.devplatform.admin.business.sysFile.bean.SysFile;
import com.devplatform.admin.business.sysFile.service.SysFileService;
import com.devplatform.admin.common.utils.AbstractController;
import com.devplatform.admin.common.utils.DateUtil;
import com.devplatform.admin.config.MyConfig;
import com.devplatform.common.util.R;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


/**
 * @description: 上传附件
 * @author: heibin
 * @create: 2019-02-28  17:09
 */


@RestController
@RequestMapping(value = "/upload")
public class UploadController extends AbstractController {


    @Autowired
    private SysFileService sysFileService;

    private String supplierDetailsPath;

    private String priceComparisonSupplierPath;



    /**
     * @description: 上传附件  用于供应商管理和询比价报价附件
     * @author: heibin
     * @create: 2019-02-28  17:09
     */

    @RequestMapping(value="/uploadfile",method= RequestMethod.POST)
    public R uploadify(MultipartRequest mRequest, HttpServletResponse response, HttpServletRequest request, MultipartFile file,String type) throws IOException, FileUploadException {
        List<SysFile> sysFiles = new ArrayList<SysFile>();
        try {
           // Map<String, MultipartFile> fileMap = mRequest.getFileMap();
           // for (String key : fileMap.keySet()) {
              //  MultipartFile mFile = fileMap.get(key);
                //保存附件
                String savePath = upload(request, file,type);
                //存到附件表
                if(StringUtils.isNotBlank(savePath)){
                    SysFile sysFile = new SysFile();
                    sysFile.setCreatedTime(new Date());
                    sysFile.setFileUrl(savePath);
                    sysFile.setFileName(file.getOriginalFilename());
                    sysFile.setFileType(type);
                    sysFile.setCreatedUserId(getUserId());
                    //供应商管理的图片
                    if("1".equals(type)){
                        sysFile.setFilePath("supplierDetails");
                    }else if("2".equals(type)){
                        //询比价管理供应商上传的文件
                        sysFile.setFilePath("priceComparisonSupplier");
                    }
                    //保存
                    sysFileService.save(sysFile);
                    sysFiles.add(sysFile);
                }
           // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok().put("sysFiles", sysFiles);
    }




/**
     * 上传文件
     * @param request
     * @param file 文件，定义的MultipartFile
     * @return path 返回一个路径
     *
     * 别忘了form 加个 enctype="multipart/form-data"
     */


    public String upload(HttpServletRequest request, MultipartFile file,String type) {
        String Path = "";
        StringBuffer dataPath = new StringBuffer();
        //dataPath.append("upload");

        // 得到文件对象
        if (file!=null&&!file.isEmpty()) {
            // 得到该文件的后缀，判断是否允许上传
            String fileName=file.getOriginalFilename();
            String ext = fileName.substring(fileName.lastIndexOf('.') + 1,
                    fileName.length());
            // 获取配置文件中的指定路径
            String realBaseDir = null;
            //供应商管理的图片
            if("1".equals(type)){
                realBaseDir = supplierDetailsPath;
            }else if("2".equals(type)){
                //询比价管理供应商上传的文件
                realBaseDir=priceComparisonSupplierPath;
            }
            // 建立文件夹
            System.out.println(realBaseDir);
            File baseFile = new File(realBaseDir);

            if (!baseFile.exists()) {
                baseFile.mkdirs();
            }
            // 新文件名
            Random ran = new Random();
            long num = ran.nextLong();
            String fileRealName = DateUtil.format(new Date(),
                    "yyyyMMddHHmmss") + num + "dr" + "." + ext;
            // 上传文件
            try {
                InputStream stream = file.getInputStream();// 把文件读入

                OutputStream bos = new FileOutputStream(realBaseDir + fileRealName);
                // 建立一个上传文件的输出流,程序运行到这已经建立了一个空文件，打开的时候提示这个文件已经被另一个程序占用，只能打开只读副本。
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                    bos.write(buffer, 0, bytesRead);// 将文件写入服务器
                }
                bos.close();
                //bos关闭后，文件已经内容并且打开不用提示文件已经被占用，直接可以打开。
                stream.close();
                dataPath.append(fileRealName);
                Path = dataPath.toString();
            } catch (Exception e) {
                Path = "";
                //如果有异常，就删除这个文件
                File filev = new File(realBaseDir  + fileRealName);
                filev.delete();
                e.printStackTrace();
            }
        }

        return Path;
    }



}


