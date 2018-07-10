package avg.com.overwatch;

import android.os.AsyncTask;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by KASUN on 2/5/2018.
 */

public class RDPortal extends AsyncTask<String, Void, Void>

{
    Socket s;
    PrintWriter writer;
    @Override
    protected Void doInBackground(String... voids) {
        try {
            String message = voids[0];
            s = new Socket(ConnectionDetails.serverIp, 6005);
            writer = new PrintWriter(s.getOutputStream());
            writer.write(message);
            writer.flush();
            writer.close();
        } catch (Exception e) {
        }
        return null;
    }
}
