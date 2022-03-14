package utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sane=jeev Reddy
 * First Row of every excel sheet = column name
 * First Column of every sheet = Scenario Name(or the key to fetch the data) There should not be any blank row in between data
 */

public class ExcelReader {

    private  Workbook workbook;
    private  FileInputStream fis;

    /**
     * Initializing Workbook
     * @param filePath
     * @param fileType(xls,xlsx)
     */
    public ExcelReader(String filePath, String fileType) {
        try {
            fis = new FileInputStream(new File(filePath));
            if (fileType.equalsIgnoreCase("xls")) {
                workbook = new HSSFWorkbook(fis);
            } else {
                workbook = new XSSFWorkbook(fis);
            }
        } catch (FileNotFoundException e) {
            Assert.fail("Data file not found at given path: PATH: "+filePath);
        } catch (IOException e) {
            Assert.fail("Error While instantiating Data file: Error Message: "+e.getLocalizedMessage());
        }
    }

    /**
     * To Get the total number of data rows
     * @param sheet
     * @return
     */
    public  int getNonBlankRowCount(Sheet sheet) {
        int totalRowNumber = 0;
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell == null) {
                break;
            } else if (cell.getCellType() == CellType.BLANK) {
                break;
            } else {
                totalRowNumber++;
            }
        }
        return totalRowNumber;
    }

    /**
     * To Get the total number of columns in data row
     * @param sheet
     * @param scenarioName
     * @return
     */
    public  int getNonBlankColCount(Sheet sheet, String scenarioName) {
        int totalColCount = 0, scenarioRowNumber;
        scenarioRowNumber = getScenarioDataRowNumber(sheet,scenarioName);
        Row row = sheet.getRow(scenarioRowNumber);
        for (Cell cell : row) {
            if (cell == null) {
                break;
            } else if (cell.getCellType() == CellType.BLANK) {
                break;
            } else {
                totalColCount++;
            }
        }
        return totalColCount;
    }

    /**
     * To Get the specific scenario data by using Scenario Name or Key
     * @param sheet
     * @param scenarioName
     * @return
     */
    public  Map<String, String> getRowDataAsMap(Sheet sheet, String scenarioName) {
        int colHedaderIndex=0;
        Map<String, String> data = new HashMap<String, String>();
        String[] colHeaders = getRowData(sheet,0);
        Row row = sheet.getRow(getScenarioDataRowNumber(sheet,scenarioName));
        for (Cell cell : row) {
            if (cell == null) {
                break;
            } else if (cell.getCellType() == CellType.BLANK) {
                break;
            } else {
                data.put(colHeaders[colHedaderIndex], getCellValue(sheet,cell.getRowIndex(),cell.getColumnIndex()));
                colHedaderIndex++;
            }
        }
        return data;
    }

    /**
     * To Get row data as String array by row number
     * @param sheet
     * @param rowNum
     * @return
     */
    public  String[] getRowData(Sheet sheet,int rowNum){
        ArrayList<String> rowData = new ArrayList<String>();
        Row row = sheet.getRow(rowNum);
        for(Cell cell:row){
            if (cell == null) {
                break;
            } else if (cell.getCellType() == CellType.BLANK) {
                break;
            } else {
                rowData.add(getCellValue(sheet,rowNum,cell.getColumnIndex()));
            }
        }
        return rowData.toArray(new String[rowData.size()]);
    }

    /**
     * initializing the worksheet
     * @param sheetName
     * @return
     */
    public  Sheet getSheet(String sheetName) {
        Sheet sheet = null;
        int sheetIndex = workbook.getSheetIndex(sheetName);
        try {
            if (sheetIndex == -1) {
                throw new Error("Sheet " + sheetName
                        + " doesn't exist in excel file");
            } else {
                sheet = workbook.getSheetAt(sheetIndex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheet;
    }

    /**
     * To get Cell Value by using row number and col number
     * @param sheet
     * @param rowNum
     * @param colNum
     * @return
     */
    public  String getCellValue(Sheet sheet, int rowNum, int colNum) {
        Cell cell = sheet.getRow(rowNum).getCell(colNum);
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else {
            return String.valueOf(cell.getNumericCellValue());
        }
    }

    /**
     * to get the data row number of any scenario by using scenario name
     * @param sheet
     * @param scenarioName
     * @return
     */
    public int getScenarioDataRowNumber(Sheet sheet, String scenarioName) {
        int scenarioDataRowNumber = 0;
        boolean isScenarioDataExists = false;
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell == null) {
                throw new Error("Scenario data doesn't exist for Scenario: '"
                        + scenarioName + "'");
            } else if (cell.getCellType() == CellType.BLANK) {
                throw new Error("Scenario data doesn't exist for Scenario: '"
                        + scenarioName + "'");
            } else {
                if (cell.getStringCellValue().trim()
                        .equalsIgnoreCase(scenarioName)) {
                    scenarioDataRowNumber = cell.getRowIndex();
                    isScenarioDataExists = true;
                    break;
                }
            }
        }
        if (!isScenarioDataExists) {
            throw new Error("Scenario data doesn't exist for Scenario: '"
                    + scenarioName + "'");
        }
        return scenarioDataRowNumber;
    }
}