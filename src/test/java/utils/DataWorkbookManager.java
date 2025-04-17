package utils;

import org.apache.poi.ss.usermodel.Sheet;

public class DataWorkbookManager {

    private static ThreadLocal<ExcelReader> excelReader = new ThreadLocal<>();
    private static ThreadLocal<Sheet> dataWorkSheet = new ThreadLocal<>();

    public ExcelReader getReader() {
        return excelReader.get();
    }
    public Sheet getWorkSheet() {
        return dataWorkSheet.get();
    }
    public void setReader(ExcelReader reader) {
        excelReader.set(reader);
    }
    public void setWorkSheet(Sheet worksheet) {
        dataWorkSheet.set(worksheet);
    }

}
