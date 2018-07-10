package avg.com.overwatch;

import android.os.AsyncTask;
import android.util.Log;

import java.io.PrintWriter;
import java.net.Socket;

public class MousePortal extends AsyncTask<String, Void, Void>

{
    Socket s;
    PrintWriter writer;


    @Override
    protected Void doInBackground(String... voids) {
        try {
            String message = voids[0];
            s = new Socket(ConnectionDetails.serverIp, ConnectionDetails.ServermouseReceivePort);
            writer = new PrintWriter(s.getOutputStream());
            writer.write(message);
            writer.flush();
            writer.close();
            Log.d("",message.toString());
        } catch (Exception e) {
            Log.d("",e.toString());
        }
        return null;
    }
}

