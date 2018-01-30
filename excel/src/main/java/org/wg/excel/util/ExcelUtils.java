package org.wg.excel.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel操作类
 *
 * @author run
 */
public class ExcelUtils {
    /**
     * 2003- 版本的excel
     */
    private static final String EXCEL_XLS = "xls";

    /**
     * 2007+ 版本的excel
     */
    private static final String EXCEL_XLSX = "xlsx";

    public static void main(String[] args) throws Exception {
//        List<List<String>> dataList = read("F:\\Temp\\test.xls");
        List<List<String>> dataList = read(new File("F:\\Temp\\事务实验.xlsx"));
        System.out.println(dataList.size());
        for (List<String> dataRole : dataList) {
            for (String data : dataRole) {
                System.out.println(data);
            }
        }
    }

    /**
     * 读取 Excel 第一页所有数据
     *
     * @return
     * @throws Exception
     */
    public static List<List<String>> read(File excelFile) throws Exception {
        Workbook workbook = getWorkbok(excelFile);
        return read(workbook, 0, 0, getRowCount(workbook, 0));
    }

    /**
     * 读取指定sheet 页所有数据
     *
     * @param sheetIx 指定 sheet 页，从 0 开始
     * @return
     * @throws Exception
     */
    public static List<List<String>> read(File excelFile, int sheetIx) throws Exception {
        Workbook workbook = getWorkbok(excelFile);
        return read(workbook, sheetIx, 0, getRowCount(workbook, sheetIx));
    }

    /**
     * 把Excel文件转成工作薄对象
     *
     * @param excelFile excel文件
     * @return
     */
    public static Workbook getWorkbok(File excelFile) throws Exception {
        Workbook workbook;
        InputStream is = new FileInputStream(excelFile);
        if (excelFile.getName().endsWith(EXCEL_XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (excelFile.getName().endsWith(EXCEL_XLSX)) {
            workbook = new XSSFWorkbook(is);
        } else {
            throw new Exception("文件不是excel文件！");
        }
        return workbook;
    }

    /**
     * 读取指定sheet 页指定行数据
     *
     * @param sheetIx 指定 sheet 页，从 0 开始
     * @param start   指定开始行，从 0 开始
     * @param end     指定结束行，从 0 开始
     * @return
     * @throws Exception
     */
    public static List<List<String>> read(Workbook workbook, int sheetIx, int start, int end) throws Exception {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        List<List<String>> list = new ArrayList<>();
        int rowCount = getRowCount(workbook, sheetIx);
        if (end > rowCount) {
            end = rowCount;
        }
        // 第一行总列数
        int cols = sheet.getRow(0).getLastCellNum();
        for (int i = start; i <= end; i++) {
            List<String> rowList = new ArrayList<>();
            Row row = sheet.getRow(i);
            for (int j = 0; j < cols; j++) {
                if (row == null) {
                    rowList.add(null);
                    continue;
                }
                rowList.add(getCellValueToString(row.getCell(j)));
            }
            list.add(rowList);
        }
        return list;
    }

    /**
     * 返回sheet 中的行数
     *
     * @param sheetIx 指定 Sheet 页，从 0 开始
     * @return
     */
    public static int getRowCount(Workbook workbook, int sheetIx) {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        if (sheet.getPhysicalNumberOfRows() == 0) {
            return 0;
        }
        return sheet.getLastRowNum();
    }

    /**
     * 转换单元格的类型为String 默认的 <br>
     *
     * @param cell
     * @return
     */
    private static String getCellValueToString(Cell cell) {
        String cellStr = null;
        if (cell == null) {
            return null;
        }
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                cellStr = String.valueOf(cell.getBooleanCellValue());
                break;
            case NUMERIC:
                //如果为时间格式的内容
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    cellStr = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                } else {
                    cellStr = new DecimalFormat("0").format(cell.getNumericCellValue());
                }
                break;
            // 字符串
            case STRING:
                cellStr = cell.getStringCellValue();
                break;
            // 公式
            case FORMULA:
                cellStr = cell.getCellFormula();
                break;
            // 空值
            case BLANK:
                break;
            // 故障
            case ERROR:
                break;
            default:
                break;
        }
        return cellStr;
    }
}
