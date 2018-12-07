import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * <p>read excel 文件</p>
 *
 * @author liubojin
 * @since 1.0 on 2018/9/18
 */
public class ReadExcel {
    public static void main(String[] args) {
        ReadExcel obj = new ReadExcel();

        File file = new File("C:/Users/liubojin/Desktop/B网营业厅字段需求.xls");
        List excelList = readExcel(file);



        for (Object row : excelList) {

            List list = (List) row;
            for (Object cell : list) {
                System.out.print(cell+"end");
            }
            System.out.println();
        }

    }

    /**
     * 去读Excel的方法readExcel，该方法的入口参数为一个File对象
     *
     * @param file 目标文件
     * @return list集合
     */
    public static List readExcel(File file) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheetSize = wb.getNumberOfSheets();
            for (int index = 0; index < sheetSize; index++) {
                List<List> outerList = new ArrayList<>();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList = new ArrayList();
                    // sheet.getColumns()返回该页的总列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        if (cellinfo.isEmpty()) {
                            continue;
                        }
                        innerList.add(cellinfo);
                        System.out.print(cellinfo);
                    }
                    outerList.add(i, innerList);
                    System.out.println();
                }
                return outerList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}