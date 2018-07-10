package avg.com.overwatch;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.*;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.widget.Toast;

public class ConnectionScreen extends AppCompatActivity {

    Button btnConnect, btnscn;
    detCollector detCollector = new detCollector();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_screen);
        btnConnect = findViewById(R.id.btnConnect);
        btnscn = findViewById(R.id.btnscn);

        final Activity activity = this;
        detCollector.start();
        btnConnect.setVisibility(View.INVISIBLE);
        btnscn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detSend detsend = new detSend();
                detsend.execute("authorize");
                startActivity(new Intent(ConnectionScreen.this, Toolbox.class));
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {

            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                if (result.getContents() == null) {
                    Toast.makeText(getApplicationContext(), "canceled", Toast.LENGTH_SHORT).show();
                } else {
                    String[] connectionvals = result.getContents().split(",");
                    ConnectionDetails.serverIp = connectionvals[0];
                    ConnectionDetails.ServerdetCollectorPort = Integer.parseInt(connectionvals[1]);
                    ConnectionDetails.ServermouseReceivePort = Integer.parseInt(connectionvals[2]);
                    ConnectionDetails.ServerjoystickPort = Integer.parseInt(connectionvals[3]);
                    ConnectionDetails.ServerRdMousereceivePort = Integer.parseInt(connectionvals[4]);
                    ConnectionDetails.rdheight = Integer.parseInt(connectionvals[5]);
                    ConnectionDetails.rdwidth = Integer.parseInt(connectionvals[6]);

                    Toast.makeText(getApplicationContext(), ConnectionDetails.serverIp
                            + "," + ConnectionDetails.ServerdetCollectorPort
                            + "," + ConnectionDetails.ServermouseReceivePort +
                            "," + ConnectionDetails.ServerjoystickPort +
                            "," + ConnectionDetails.ServerRdMousereceivePort, Toast.LENGTH_SHORT).show();

                    btnConnect.setVisibility(View.VISIBLE);
                }

            } else {

                super.onActivityResult(requestCode, resultCode, data);
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "incorrect QR Code", Toast.LENGTH_SHORT).show();
        }
    }
}
