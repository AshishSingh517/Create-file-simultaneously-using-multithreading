package Threads;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Thread.currentThread;
public class print_2files implements Runnable {

    @Override
    public void run() {

        String filename = "Text" + currentThread().getId() + ".txt";
        FileWriter writer;
        try {
            writer = new FileWriter(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int totalthread = 2;
        for (int i = 1; i <= 1000000000 / totalthread; i++) {
            String s = i + " " + currentThread().getId();
            try {
                writer.write(s);
                writer.write("\n");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void printnumber() {
        long starttime = System.currentTimeMillis();
        Runnable runnable = new print_2files();
        java.lang.Thread d1 = new java.lang.Thread(runnable);
        System.out.println("1st file print successfully");
        d1.start();
        java.lang.Thread d2 = new java.lang.Thread(runnable);
        System.out.println("2nd file print succesfully");
        d2.start();
        try {
            d1.join();
            d2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        }
        long endtime=System.currentTimeMillis();
        long totaltime = endtime - starttime;
        System.out.println("Execution Time: " + totaltime + " milliseconds");
    }

}
