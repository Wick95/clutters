import java.util.ArrayList;
import java.util.List;

/**
 * <p>list中的 Object get（Integer index) 方法测试 </p>
 *
 * @author liubojin
 * @since 1.0 on 2018/9/20
 */
public class List$getTest {

    /**
     *
     *
     * @param args args
     */
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("123123");
        list.add("456456");
        Object abc = list.get(0);
        String bcd = (String) abc;
        System.out.println(abc.equals(bcd));

    }
}
