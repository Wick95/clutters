package flowmonitor.io;

import sun.security.action.GetPropertyAction;

import java.security.AccessController;

/**
 * <p>Rules file class </p>
 *
 * @author liubojin
 * @since 1.0 on 2018/12/6
 */
public class RulesFile {

    private static RulesFile instance = null;

    private static final char SLASH = AccessController.doPrivileged(new GetPropertyAction("file.separator")).charAt(0);

    private FileIo fileIo;

    private RulesFile() {
        String rulesFileName = "rules.txt";
        String directoryName = "flow-monitor";
        String rulesFilePath = directoryName + SLASH + rulesFileName;
        System.out.println("create path successfully. Rules file 's path is : " + rulesFilePath);
    }

    public static synchronized RulesFile getInstance(){
        if(instance==null){
            instance=new RulesFile();
        }
        return instance;
    }

    public
}
