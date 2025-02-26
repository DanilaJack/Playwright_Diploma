package com.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Excel {

    public static int getRowCount(File file) {

        int rowCount = 0;

        try(FileInputStream fis = new FileInputStream(file)){

            Workbook workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(0);

            rowCount = sheet.getPhysicalNumberOfRows();
        }
        catch (IOException | InvalidFormatException e){
            e.printStackTrace();
        }

        return rowCount-1;
    }

}
