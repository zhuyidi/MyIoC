import java.util.HashMap;

/**
 * Created by dela on 3/7/18.
 */
public class test extends Thread {
    public test(String name) {
        super(name);
    }

    @Override
    public void run() {
//        for (int i = 0; i < 20; i++) {
//            System.out.println(getName() + " " + i);
//        }
        int a = 3/0;

    }

    public static void main(String[] args) throws InterruptedException {
//        new test("xinxiancheng").start();
//        for (int i = 0; i < 100; i++) {
//            if (i == 10) {
//                test jt = new test("join");
//                jt.start();
////                jt.join();
//            }
//
//            System.out.println(Thread.currentThread().getName() + " " + i);
//        }



        System.out.println(String.valueOf(3.14));
    }
}
