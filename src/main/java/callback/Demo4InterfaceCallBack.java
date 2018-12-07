package callback;

/**
 * <p>interface call back </p>
 *
 * @author liubojin
 * @since 1.0 on 2018/11/29
 */

/* Interface and class is a data type in java .
 * In usual , they are called 'the interface variables' and 'the class variables'
 * */

public class Demo4InterfaceCallBack {

    public void regards(MyInterface myInterface) {
        myInterface.getGreetings();
    }

    public static void main(String[] args) {
        Demo4InterfaceCallBack demo = new Demo4InterfaceCallBack();


        demo.regards(new ChinaGreetings());
    }
}

