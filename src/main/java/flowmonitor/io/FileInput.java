package flowmonitor.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p>file input</p>
 *
 * @author liubojin
 * @since 1.0 on 2018/12/3
 */
public class FileInput {

    public void  run () {

        try {
            FileInputStream fileInputStream = new FileInputStream(new File("/test"));
            Throwable var2 = null;

            try {
                System.out.println(fileInputStream.read());
            } catch (Throwable var14) {
                var2 = var14;
                throw var14;
            } finally {
                if (fileInputStream != null) {
                    if (var2 != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable var13) {
                            var2.addSuppressed(var13);
                        }
                    } else {
                        fileInputStream.close();
                    }
                }

            }

        } catch (IOException var17) {
            System.out.println(123123);
            throw new RuntimeException(var17.getMessage(), var17);
        } catch (Exception var18) {
            System.out.println(456456);
            throw new RuntimeException(var18.getMessage(), var18);
        }
    }
}
