package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Xlsx {
    private final ThreadLocal<List<Map<String, String>>> sheetResult = new ThreadLocal<>();

    public Xlsx(String excelFilePath, String sheetName) throws IOException {
        List<Map<String, String>> sheetResult = new ArrayList<>();
        FileInputStream fis = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        List<String> columns = new ArrayList<>();
        for (Cell cell : sheet.getRow(0)) {
            columns.add(cell.getStringCellValue());
        }
        boolean flag = false;
        for (Row row : sheet) {
            if (!flag) {
                flag = true;
                continue;
            }
            Map<String, String> map = new HashMap<>();
            int i = 0;
            for (Cell cell : row) {
                if (map.get(columns.get(i)) == null) {
                    map.put(columns.get(i), cell.getStringCellValue());
                }
                i++;
            }
            sheetResult.add(map);
        }

        this.sheetResult.set(sheetResult);
    }

    public List<Map<String, String>> getSheetAsTable() {
        return this.sheetResult.get();
    }
}
