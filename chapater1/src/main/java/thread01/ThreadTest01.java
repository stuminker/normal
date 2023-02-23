package thread01;

/**
 * @author stuminker
 * @version 1.0
 * @project normal
 * @description
 * @date 2023/2/19 14:11:54
 */
public class ThreadTest01 {

    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.setName("TestThread---->");
        System.out.println(Thread.currentThread().getName());
        thread.run();
        System.out.println(Thread.currentThread().getName());
    }
}
