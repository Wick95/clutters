import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>测试静态方法</p>
 *
 * @author liubojin
 * @since 1.0 on 2018/9/26
 */
public class test3 {
    public static void stati() {
        System.out.println("123");
        test3 t = new test3();
        t.privat();
    }

    private List<String> privat() {
        List<String> stringList = new ArrayList<>();
        ArrayList<String> obj = new ArrayList<String>() {{
            add("123123");
            add("456456");
        }};
        stringList.addAll(obj);
        return stringList;
    }

    public static void main(String[] args) {
//        String errorMessage = null;
//        errorMessage += "123123";
//        System.out.println(errorMessage);
//
//
//        String errorMessage1 = "";
//        System.out.println(errorMessage1.isEmpty());
//        System.out.println(errorMessage1 += "123123");
//        System.out.println(errorMessage1);


        Set<Integer> OU_SET = new HashSet<>();
        System.out.println(OU_SET.isEmpty());


    }
}
