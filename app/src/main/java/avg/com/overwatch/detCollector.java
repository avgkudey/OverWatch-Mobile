package avg.com.overwatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class detCollector extends Thread {
    ServerSocket serverSocket;
    String message = "";
    int count = 0;
    InputStreamReader isr;
    BufferedReader br;


    public void run() {
        try {
            serverSocket = new ServerSocket(ConnectionDetails.AndroiddetCollectorPort);

            while (true) {
                Socket socket = serverSocket.accept();
                count++;
                isr = new InputStreamReader(socket.getInputStream());
                br = new BufferedReader(isr);
                message = br.readLine();
                System.err.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
