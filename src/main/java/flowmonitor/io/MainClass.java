package flowmonitor.io;

import java.io.File;
import java.io.IOException;

/**
 * <p></p>
 *
 * @author liubojin
 * @since 1.0 on 2018/12/6
 */
public class MainClass {
    public static void main(String[] args) {


//        if (RulesFile.getFile().exists()) {
//
//            System.out.println("Rules file has been found.");
//            return;
//        }
//        if (RulesFile.getDirectory().exists()) {
//
//            try {
//                Boolean isCreate = RulesFile.getFile().createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

//        RulesFile rulesFile = new RulesFile();
//        File file = RulesFile.getFile();

        File file = new File("123123/12334/fanxiegang/");

        FileIo fileIo = new FileIo();
        fileIo.createFile(file);


    }
}
