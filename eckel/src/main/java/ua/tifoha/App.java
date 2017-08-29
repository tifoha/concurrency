package ua.tifoha;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        final Timer timer = new Timer(false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task complete.");
                timer.cancel();
            }
        }, 1_000);

        System.out.println("End.");
//        timer.purge();
    }
}
