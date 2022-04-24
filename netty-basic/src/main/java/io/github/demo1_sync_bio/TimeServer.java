package io.github.demo1_sync_bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        ServerSocket ss = null;
        try {
            ss = new ServerSocket(port);
            System.out.println("The time server port is " + port);
            Socket s = null;
            while (true) {
                s = ss.accept();
                new Thread(new TimeServerHandler(s)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ss != null) {
                System.out.println("Time server close.");
                ss.close();
                ss = null;
            }
        }
    }
}
