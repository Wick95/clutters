import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liubojin
 * @since 1.1 on 2018/9/18
 */
public class ImportExcel2Sql {
    /**
     * 数据库名称
     */
    private static final String DATABASE_NAME = "insurance_settlement";
    /**
     * 表名称
     */
    private static final String TABLE_NAME = "bnet_settlements";
    /**
     * 读取文件的编码格式 这里选用中文操作系统默认的字符集GB2312
     */
    private static final String CHAR_SET_NAME = "GB2312";
    /**
     * 文件名
     */
    private static final String FILE_NAME = "create_" + TABLE_NAME;
    /**
     * 资源路径
     */
    private static final String SOURCE_FILE_PATH = "/Users/liubojin/Desktop/" + FILE_NAME + ".csv";
    /**
     * 目标路径
     */
    private static final String TARGET_FILE_PATH = "/Users/liubojin/Desktop/" + FILE_NAME + ".sql";
    /**
     * 主键名称
     */
    private static String PRIMARY_KEY = null;
    /**
     * 默认varchar长度
     */
    private static String DEFAULT_VARCHAR_LENGTH = "100";
    /**
     * 默认int长度
     */
    private static String DEFAULT_INT_LENGTH = "11";

    public static void main(String[] args) {
        produceSqlFromExcel(new File("C:\\Users\\liubojin\\Desktop\\资料\\物流结算5.0\\B商家营业厅\\结算单字段.xls"));
    }

    /**
     * 读取csv格式的文件
     *
     * @return list集合
     */
    private static List<String> readCsv() {
        List<String> list = null;

        try {
            list = Files.readAllLines(Paths.get(SOURCE_FILE_PATH), Charset.forName(CHAR_SET_NAME));
            System.out.println("Info : Field \" " + SOURCE_FILE_PATH + " \" has been read successfully !");

        } catch (MalformedInputException e) {
            System.out.println("Error : Field \" " + SOURCE_FILE_PATH + " \" is not encoding by " + CHAR_SET_NAME + " ! ");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Field reading error !");
        }

        return list;
    }

    /**
     * new File("C:/Users/liubojin/Desktop/B网营业厅字段需求.xls")
     *
     * @param sourceFile 文件路径
     */
    private static void produceSqlFromExcel(File sourceFile) {
        try {
            //1.读取文件所有行
            List list = ReadExcel.readExcel(sourceFile);

            if (list == null) {
                //如果文件是空的，则返回。
                System.out.println("Error : Field \" " + sourceFile + " \" is null ");
                return;
            }

            //2.1 文件去除标题行
            list.remove(0);

            StringBuilder stringBuilder = new StringBuilder();
            //create table 语句开始
            stringBuilder.append("CREATE TABLE `").append(DATABASE_NAME).append("`.`").append(TABLE_NAME).append("`  (").append(System.getProperty("line.separator"));

            for (Object sub : list) {
                List row = (List) sub;
                for (int index = 0; index < row.size(); index++) {
                    switch (index) {
                        case 0:
                            String columnName = row.get(index).toString();
                            stringBuilder.append("`").append(columnName.toLowerCase()).append("`").append(" ");
                            break;
                        case 1:
                            String columnType = row.get(index).toString();
                            if (columnType == null) {
                                System.out.println("数据类型为空");
                                return;
                            }
                            columnType = columnType.toLowerCase();
                            if (columnType.contains("varchar")) {
                                columnType = columnType.replace("varchar2", "varchar");
                                if (!columnType.contains("(") || !columnType.contains(")")) {
                                    columnType = "varchar(" + DEFAULT_VARCHAR_LENGTH + ")";
                                }
                            } else if ("int".equals(columnType) || "number".equals(columnType)) {
                                columnType = "int(" + DEFAULT_INT_LENGTH + ")";
                            } else if ("date".equals(columnType)) {
                                columnType = "datetime";
                            } else if (columnType.contains("decimal") && !columnType.contains("(")) {
                                columnType = "decimal(20, 2)";
                            }
                            stringBuilder.append(columnType).append(" ");
                            break;
                        case 2:
                            String isNull = row.get(index).toString();
                            if (isNull == null) {
                                System.out.println("是否必填为空");
                                return;
                            }
                            isNull = isNull.toLowerCase();
                            if ("y".equals(isNull)) {
                                stringBuilder.append("NULL").append(" ");
                            } else if ("n".equals(isNull)) {
                                stringBuilder.append("NOT NULL").append(" ");
                            } else if ("primary".equals(isNull)) {
                                stringBuilder.append("NOT NULL").append(" ");
                                PRIMARY_KEY = row.get(0).toString();
                            }
                            break;
                        case 3:
                            String comment = row.get(index).toString();
                            stringBuilder.append("COMMENT").append("'").append(comment);
                            if (index == row.size() - 1) {
                                stringBuilder.append("'");
                                if (list.get(list.size() - 1) != sub) {
                                    stringBuilder.append(",").append(System.getProperty("line.separator"));
                                }
                            }
                            break;
                        case 4:
                            String comment2 = row.get(index).toString();
                            stringBuilder.append("(").append(comment2).append(")'");
                            if (list.get(list.size() - 1) != sub) {
                                stringBuilder.append(",").append(System.getProperty("line.separator"));
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            if (PRIMARY_KEY != null) {
                stringBuilder.append(",").append(System.getProperty("line.separator")).append(" PRIMARY KEY (`").append(PRIMARY_KEY).append("`)");
            }
            stringBuilder.append(System.getProperty("line.separator")).append(");");
            System.out.println(stringBuilder);

            List<String> contentList = new ArrayList<>();
            contentList.add(stringBuilder.toString());
            Files.write(Paths.get(TARGET_FILE_PATH), contentList, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}