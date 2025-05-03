package utils;

import java.io.IOException;

public class TestXlsx {
    public static void main(String[] args) throws IOException {
        Xlsx xlsx = new Xlsx("/Users/sanjeeva/IdeaProjects/Cucumber-TestNG/src/test/resources/DataWorkbook/Chrome/TestData.xlsx","TestData");
        System.out.println(xlsx.getSheetAsTable());
    }
}