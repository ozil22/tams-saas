package com.pactera.tams.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;

public class ExportExcelUtils {

    public static void exportExcel(List list,HttpServletResponse response){
    	 Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
//    	 File f = new File("C:/Users/Administrator/Desktop/abc.xlsx");
//         FileOutputStream out;
// 		try {
// 			out = new FileOutputStream(f);
// 			workbook.write(out);
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}
    	 downLoadExcel(response,workbook);
    }

    private static void downLoadExcel(HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode("", "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
//            throw new NormalException(e.getMessage());
        }
    }

    public static  List importExcel(String filePath,Integer titleRows,Integer headerRows, Class pojoClass){
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<List> list = new ArrayList();
        try {
        	InputStream input = new FileInputStream(filePath);
        	XSSFWorkbook workbook = new XSSFWorkbook(input);
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
    			params.setStartSheetIndex(i);
    			List importExcel = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
    			list.add(importExcel);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
    }
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (file == null){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        }catch (NoSuchElementException e){
//            throw new NormalException("excel文件不能为空");
        } catch (Exception e) {
//            throw new NormalException(e.getMessage());
        }
        return list;
    }

}
