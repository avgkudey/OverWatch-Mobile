package avg.com.overwatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Toolbox extends AppCompatActivity {
Button btnRC,btnJY,btnRD;
TextView txtip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbox);

        btnRC=findViewById(R.id.btnRC);
        btnJY=findViewById(R.id.btnJY);
        btnRD=findViewById(R.id.btnRD);
        txtip=findViewById(R.id.txtip);
        txtip.setText(ConnectionDetails.serverIp+","+ConnectionDetails.ServermouseReceivePort);

        btnRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Toolbox.this,RemoteControl.class));
            }
        });
        btnRD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Toolbox.this,RemoteDesktop.class));
            }
        });

        btnJY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Toolbox.this,joystick.class));
            }
        });
    }


}
