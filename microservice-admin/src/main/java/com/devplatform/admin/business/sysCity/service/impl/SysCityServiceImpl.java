package com.devplatform.admin.business.sysCity.service.impl;


import com.devplatform.admin.business.sysCity.bean.SysCity;
import com.devplatform.admin.business.sysCity.dao.SysCityDao;
import com.devplatform.admin.business.sysCity.service.SysCityService;
import com.devplatform.admin.modules.sys.bean.SysDictionary;
import com.devplatform.admin.modules.sys.bean.SysOrg;
import com.devplatform.common.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devplatform.common.service.impl.MyBaseServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


/**
 * 城市表的service接口实现类
 * <br>
 * <b>功能：</b>SysCityServiceImpl<br>
 * @author 代码生成器产生
 */
@Service("sysCityService")
public class SysCityServiceImpl extends MyBaseServiceImpl<SysCityDao, SysCity> implements SysCityService {



    @Autowired
    private SysCityService sysCityService;


    /**
     * 全国城市导入数据库
     * @param file
     */
    @Transactional
    @Override
    public void importCityInfoExcel(MultipartFile file) throws Exception{
        //创建一个list集合，用来封装车辆对象
        List<SysCity> list = new ArrayList<SysCity>();

        //创建错误信息集合
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        if (!extension.equals("xls") && !extension.equals("xlsx")) {
        }

        Workbook workbook = null;
        if(extension.equals("xlsx")){
            workbook = new XSSFWorkbook(file.getInputStream());
        }else if(extension.equals("xls")){
            workbook = new HSSFWorkbook(file.getInputStream());
        }
        Sheet sheet = workbook.getSheetAt(0);
        //获得到第一行的行数
        int begin = sheet.getFirstRowNum();
        //获得到最后一行的行数
        int end = sheet.getLastRowNum();
        int lie = sheet.getRow(0).getPhysicalNumberOfCells();

        //调出空行
        for (int i = 1; i <= end; i++) {
            Row row = sheet.getRow(i);
            String cityname = getStringCellValue(row.getCell(0)).trim();
            String adcode = getStringCellValue(row.getCell(1)).trim();
            String citycode = getStringCellValue(row.getCell(2)).trim();
            SysCity sysCity = new SysCity();
            sysCity.setCityname(cityname);
            sysCity.setAdcode(adcode);
            sysCity.setCitycode(citycode);
            list.add(sysCity);
        }
        sysCityService.saveBatch(list);
    }

    /**
     * 批量导入表格-获取单元格值
     * @author heibin
     * 2019年2月25日上午10:14:43
     * @param cell
     * @return
     */
    private String getStringCellValue(Cell cell) {
        if(cell == null)
            return "";
        String strCell = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                strCell = cell.getNumericCellValue()+"";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        return strCell;
    }
}
