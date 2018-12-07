package flowmonitor.io;

import sun.security.action.GetPropertyAction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AccessController;
import java.util.Arrays;

/**
 * <p>file io</p>
 *
 * @author liubojin
 * @since 1.0 on 2018/11/30
 */
public class FileIo {
    public static void main(String[] args) {
        try {
            File file = new File("\\flow monitor\\roles.txt");
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getCanonicalPath());

            File file2 = new File(".");
            System.out.println(file2.getAbsolutePath());
            System.out.println(file2.getCanonicalPath());

            File file3 = new File("/");
            System.out.println(file3.getAbsolutePath());
            System.out.println(file3.getCanonicalPath());

            File file4 = new File("fm/test.txt");
            System.out.println(file4.getAbsolutePath());
            System.out.println(file4.getCanonicalPath());

            final char slash = AccessController.doPrivileged(
                    new GetPropertyAction("file.separator")).charAt(0);
            final char semicolon = AccessController.doPrivileged(
                    new GetPropertyAction("path.separator")).charAt(0);
            final char altSlash = (slash == '\\') ? '/' : '\\';

            System.out.println("slash is : " + slash);
            System.out.println("semicolon is : " + semicolon);
            System.out.println("ALT_SLASH is : " + altSlash);

            file4.createNewFile();

        } catch (Exception e) {
            System.out.println("This is exception");
        }



/*        try (FileInputStream fileInputStream = new FileInputStream(new File("\\test.txt"))) {
            System.out.println(fileInputStream.read());
        } catch (IOException ioException) {
            System.out.println(123123);
            throw new RuntimeException(ioException.getMessage(), ioException);

        } catch (Exception exception) {
            System.out.println(456456);
            throw new RuntimeException(exception.getMessage(), exception);

        }*/


    }

    public boolean createFile(File file) {

        if (file.exists()) {
            System.out.println("File already exists. ");
            System.out.println("File path:" + file.getAbsolutePath() + ",file size:" + file.length());
            return true;
        }

        createDirectory(file.getParentFile());

        try {
            return file.createNewFile();
        } catch (IOException e) {
            System.out.println("Create file error,because" + Arrays.toString(e.getStackTrace()));
            return false;
        }
    }

    public boolean createDirectory(File file) {
        return file.mkdirs();
    }

    public void writeContent(String pathStr) {
        Path path = Paths.get(pathStr);

        //Use try-with-resource to get auto-closeable writer instance
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("Hello World !!");
        }
    }
}
