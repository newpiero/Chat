package Java3_task.task_4_wait_notifyAll;

public class MainClass {

    private static volatile String currentOperation = "A";
    private static final Object monitor = new Object();


    public static void main(String[] args) {


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        synchronized (monitor) {

                            while (!currentOperation.equals("A")) {
                                monitor.wait();
                            }
                            System.out.println("A");
                            currentOperation = "B";
                            monitor.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        synchronized (monitor) {

                            while (!currentOperation.equals("B")) {
                                monitor.wait();
                            }
                            System.out.println("B");
                            currentOperation = "C";
                            monitor.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 5; i++) {
                    try {
                        synchronized (monitor) {

                            while (!currentOperation.equals("C")) {
                                monitor.wait();
                            }
                            System.out.println("C");
                            currentOperation = "A";
                            monitor.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        t1.start();
        t2.start();
        t3.start();
    }
}
