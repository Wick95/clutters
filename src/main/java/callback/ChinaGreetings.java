package callback;

/**
 * <p>中国问候实现类</p>
 *
 * @author liubojin
 * @since 1.0 on 2018/11/29
 */
public class ChinaGreetings implements MyInterface {

    @Override
    public void getGreetings() {
        System.out.println("中国人的问候语：您好");
    }
}
