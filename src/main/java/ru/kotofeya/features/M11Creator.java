package ru.kotofeya.features;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.kotofeya.storage.model.expands.ExpandString;
import ru.kotofeya.storage.model.incomes.IncomeString;

import java.io.*;
import java.util.List;

public class M11Creator {
    private Workbook workBook;
    private Sheet sheet0, sheet1;
    private Cell blankNumber, organization, blankCreateDate, operationCode, senderStructOrg,
    senderActType, recStructOrg, recActType, corrAccSub, corrAccCode, accUnitOutput,
    throughWhom, requested, allowed,
            item0_1, item0_2, item0_3, item0_4, item0_5,
            item0_6, item0_7, item0_8, item0_9, item0_10, item0_11,
            item1_1, item1_2, item1_3, item1_4, item1_5,
            item1_6, item1_7, item1_8, item1_9, item1_10, item1_11,
    releasedPost, releasedName, receivedPost, receivedName;
    List<IncomeString> incomeStrings;
    List<ExpandString> expandStrings;

    XSSFCellStyle commonStyle, _0357_Style, _14610_Style;

    public M11Creator(InputStream inputStream){
        try {
            this.workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNewIncomeBlank(List<IncomeString> incomeStrings){
        this.incomeStrings = incomeStrings;
        createNewForm("income");
    }

    public void createNewExpandBlank(List<ExpandString> expandStrings) {
        this.expandStrings = expandStrings;
        createNewForm("expand");
    }

    private void createNewForm(String tag){
         initCells();
         createCellsStyle();
         switch (tag){
             case "income":
                 writeIncomeItemsToWorkbook();
                 break;
             case "expand":
                 writeExpandItemsToWorkbook();
                 break;
         }
    }

    public ByteArrayOutputStream getOutputStream(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workBook.write(outputStream);
            workBook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream;
    }



    private void writeIncomeItemsToWorkbook(){
        if(incomeStrings != null) {
            addItemsCell(incomeStrings.size());
            for (int j = 0; j < incomeStrings.size(); j++) {
                Row row = sheet1.getRow(6 + j);
                IncomeString incomeString = incomeStrings.get(j);
                row.getCell(2).setCellValue(incomeString.getItem().getName());
                row.getCell(5).setCellValue("шт.");
                row.getCell(6).setCellValue(incomeString.getCount());
                row.getCell(7).setCellValue(incomeString.getCount());
                row.getCell(8).setCellValue(incomeString.getPurchasePriceAct()/100d);
                row.getCell(9).setCellValue((incomeString.getCount() * incomeString.getPurchasePriceAct()/100d));
            }
        }
    }
    private void writeExpandItemsToWorkbook(){
        if(expandStrings != null) {
            addItemsCell(expandStrings.size());
            for (int j = 0; j < expandStrings.size(); j++) {
                Row row = sheet1.getRow(6 + j);
                ExpandString expandString = expandStrings.get(j);
                row.getCell(2).setCellValue(expandString.getItem().getName());
                row.getCell(5).setCellValue("шт.");
                row.getCell(6).setCellValue(expandString.getCount());
                row.getCell(7).setCellValue(expandString.getCount());
                row.getCell(8).setCellValue(expandString.getSalePrice()/100d);
                row.getCell(9).setCellValue((expandString.getCount() * expandString.getSalePrice()/100d));
            }
        }
    }
    private void initCells(){
        sheet0 = workBook.getSheetAt(0);
        sheet1 = workBook.getSheetAt(1);
        blankNumber = sheet0.getRow(4).getCell(6);
        organization = sheet0.getRow(6).getCell(2);
        blankCreateDate = sheet0.getRow(10).getCell(1);
        operationCode = sheet0.getRow(10).getCell(2);
        senderStructOrg = sheet0.getRow(10).getCell(3);
        senderActType = sheet0.getRow(10).getCell(5);
        recStructOrg = sheet0.getRow(10).getCell(6);
        recActType = sheet0.getRow(10).getCell(7);
        corrAccSub = sheet0.getRow(10).getCell(8);
        corrAccCode = sheet0.getRow(10).getCell(9);
        accUnitOutput = sheet0.getRow(10).getCell(10);
        throughWhom = sheet0.getRow(11).getCell(2);
        requested = sheet0.getRow(12).getCell(2);
        allowed = sheet0.getRow(12).getCell(6);
        item0_1 = sheet0.getRow(17).getCell(0);
        item0_2 = sheet0.getRow(17).getCell(1);
        item0_3 = sheet0.getRow(17).getCell(2);
        item0_4 = sheet0.getRow(17).getCell(3);
        item0_5 = sheet0.getRow(17).getCell(4);
        item0_6 = sheet0.getRow(17).getCell(5);
        item0_7 = sheet0.getRow(17).getCell(6);
        item0_8 = sheet0.getRow(17).getCell(7);
        item0_9 = sheet0.getRow(17).getCell(8);
        item0_10 = sheet0.getRow(17).getCell(9);
        item0_11 = sheet0.getRow(17).getCell(10);
        item1_1 = sheet1.getRow(5).getCell(0);
        item1_2 = sheet1.getRow(5).getCell(1);
        item1_3 = sheet1.getRow(5).getCell(2);
        item1_4 = sheet1.getRow(5).getCell(3);
        item1_5 = sheet1.getRow(5).getCell(4);
        item1_6 = sheet1.getRow(5).getCell(5);
        item1_7 = sheet1.getRow(5).getCell(6);
        item1_8 = sheet1.getRow(5).getCell(7);
        item1_9 = sheet1.getRow(5).getCell(8);
        item1_10 = sheet1.getRow(5).getCell(9);
        item1_11 = sheet1.getRow(5).getCell(10);
        releasedPost = sheet1.getRow(14).getCell(2);
        releasedName = sheet1.getRow(14).getCell(8);
        receivedPost = sheet1.getRow(16).getCell(2);
        receivedName = sheet1.getRow(16).getCell(8);
    }
    private void createCellsStyle(){
        commonStyle = (XSSFCellStyle) workBook.createCellStyle();
        commonStyle.setBorderTop(BorderStyle.THIN);
        commonStyle.setBorderBottom(BorderStyle.THIN);
        commonStyle.setBorderLeft(BorderStyle.THIN);
        commonStyle.setBorderRight(BorderStyle.THIN);
        commonStyle.setAlignment(HorizontalAlignment.CENTER);

        _0357_Style = (XSSFCellStyle) workBook.createCellStyle();
        _0357_Style.setBorderTop(BorderStyle.THIN);
        _0357_Style.setBorderBottom(BorderStyle.THIN);
        _0357_Style.setBorderLeft(BorderStyle.MEDIUM);
        _0357_Style.setBorderRight(BorderStyle.THIN);
        _0357_Style.setAlignment(HorizontalAlignment.CENTER);

        _14610_Style = (XSSFCellStyle) workBook.createCellStyle();
        _14610_Style.setBorderTop(BorderStyle.THIN);
        _14610_Style.setBorderBottom(BorderStyle.THIN);
        _14610_Style.setBorderLeft(BorderStyle.THIN);
        _14610_Style.setBorderRight(BorderStyle.MEDIUM);
        _14610_Style.setAlignment(HorizontalAlignment.CENTER);
    }
    private void addItemsCell(int k){
        for (int j = 0; j < k; j++) {
            Row row = sheet1.createRow(6 + j);
            for(int i = 0; i < 11; i++){
                row.createCell(i);
                switch (i){
                    case 0:
                    case 3:
                    case 5:
                    case 7:
                        row.getCell(i).setCellStyle(_0357_Style);
                        break;
                    case 1:
                    case 4:
                    case 6:
                    case 10:
                        row.getCell(i).setCellStyle(_14610_Style);
                        break;
                    default:
                        row.getCell(i).setCellStyle(commonStyle);
                }
            }
        }
    }
}