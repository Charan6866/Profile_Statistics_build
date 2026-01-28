package DataProvider;

import org.testng.annotations.DataProvider;
import testCases.BaseTest;

import utils.ExcelUtils;

public class Dataprovider extends BaseTest {
    String Excelpath;
    String sheetName;

    @DataProvider(name = "sampleData")
    public Object[][] profileData1() throws Exception {
        Excelpath = properties.getProperty("Excelpath");
        sheetName = properties.getProperty("sheetName1");
        int row = ExcelUtils.getRowCount(Excelpath, sheetName);
        int cell = ExcelUtils.getCellCount(Excelpath, sheetName, 0);
        Object[][] data = new Object[row - 1][9];
        for (int i = 1; i < row; i++) {
            //for(int j=0;j<cell;j++){
            String fullName = ExcelUtils.getCellData(Excelpath, sheetName, i, 0);
            String email = ExcelUtils.getCellData(Excelpath, sheetName, i, 1);
            String yearOfExp = ExcelUtils.getCellData(Excelpath, sheetName, i, 2);
            String jobRole = ExcelUtils.getCellData(Excelpath, sheetName, i, 3);
            String websites = ExcelUtils.getCellData(Excelpath, sheetName, i, 4);
            String apps = ExcelUtils.getCellData(Excelpath, sheetName, i, 5);
            String skills = ExcelUtils.getCellData(Excelpath, sheetName, i, 6);
            String expected = ExcelUtils.getCellData(Excelpath, sheetName, i, 7);

            data[i - 1][0] = i;
            data[i - 1][1] = fullName;
            data[i - 1][2] = email;
            data[i - 1][3] = yearOfExp;
            data[i - 1][4] = jobRole;
            data[i - 1][5] = websites;
            data[i - 1][6] = apps;
            data[i - 1][7] = skills;
            data[i - 1][8] = expected;
            //  }

        }
        return data;
    }

    @DataProvider(name = "dataforportfolio")
    public Object[][] profileData2() throws Exception {
        Excelpath = properties.getProperty("Excelpath");
        sheetName = properties.getProperty("sheetName2");
        int row = ExcelUtils.getRowCount(Excelpath, sheetName);
        int cell = ExcelUtils.getCellCount(Excelpath, sheetName, 0);
        Object[][] data = new Object[row - 1][cell];
        for (int i = 1; i < row; i++) {
            //for(int j=0;j<cell;j++){
            String fullName = ExcelUtils.getCellData(Excelpath, sheetName, i, 0);
            String email = ExcelUtils.getCellData(Excelpath, sheetName, i, 1);
            String yearOfExp = ExcelUtils.getCellData(Excelpath, sheetName, i, 2);
            String jobRole = ExcelUtils.getCellData(Excelpath, sheetName, i, 3);
            String websites = ExcelUtils.getCellData(Excelpath, sheetName, i, 4);
            String apps = ExcelUtils.getCellData(Excelpath, sheetName, i, 5);
            String skills = ExcelUtils.getCellData(Excelpath, sheetName, i, 6);

            data[i - 1][0] = fullName;
            data[i - 1][1] = email;
            data[i - 1][2] = yearOfExp;
            data[i - 1][3] = jobRole;
            data[i - 1][4] = websites;
            data[i - 1][5] = apps;
            data[i - 1][6] = skills;
            //  }

        }
        return data;
    }

}
