package avg.com.overwatch;

import android.os.AsyncTask;

import java.io.PrintWriter;
import java.net.Socket;

public class detSend extends AsyncTask<String, Void, Void>

{
    Socket s;
    PrintWriter writer;


    @Override
    protected Void doInBackground(String... voids) {
        try {
            String message = voids[0];
            s = new Socket(ConnectionDetails.serverIp, ConnectionDetails.ServerdetCollectorPort);
            writer = new PrintWriter(s.getOutputStream());
            writer.write(message);
            writer.flush();
            writer.close();
        } catch (Exception e) {
        }
        return null;
    }
}