package avg.com.overwatch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class RemoteDesktop extends AppCompatActivity {
    ImageView imageView;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_desktop);
        imageView = findViewById(R.id.imageView);
        Thread mythread = new Thread(new ServerImageThread());
        mythread.start();

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
               
                return false;
            }
        });
    }

    public class ServerImageThread implements Runnable {
        ServerSocket ss;
        Socket s;
        DataInputStream dis;
        int len;
        byte[] data;

        @Override
        public void run() {
            try {
                ss = new ServerSocket(ConnectionDetails.RDfeedAndroidPort);
                
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "listning", Toast.LENGTH_SHORT).show();
                    }
                });

                while (true) {
                    s = ss.accept();
                    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                    byte[] buffer = (byte[]) ois.readObject();
                    String root = Environment.getExternalStorageDirectory().toString();
                    File mydir = new File(root + "/savedimages");
                    mydir.mkdirs();
                    FileOutputStream fos = new FileOutputStream(mydir + "2.jpg");
                    fos.write(buffer);
                    final Bitmap bitmap = BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
                    SaveImage(bitmap);
                    final Matrix matrix = new Matrix();
                    matrix.postRotate(-90);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap rotated = Bitmap.createBitmap(bitmap ,0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                            imageView.setImageBitmap(rotated);
                        }
                    });
                }
            } catch (final Exception e) {
                e.printStackTrace();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
//
        }

        private void SaveImage(Bitmap finalbitmap) {
            String root = Environment.getExternalStorageDirectory().toString();
            File mydir = new File(root + "/savedimages");
            mydir.mkdirs();
            Random generator = new Random();
            int n = 10000;
            String fname = "image-" + n + ".jpeg";
            File file = new File(mydir, fname);
            if (file.exists()) file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                finalbitmap.compress(Bitmap.CompressFormat.PNG, 10, out);
                out.flush();
                out.close();
                Toast.makeText(getApplicationContext(), "Image saved", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
