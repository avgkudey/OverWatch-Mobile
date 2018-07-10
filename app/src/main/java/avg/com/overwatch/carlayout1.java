package avg.com.overwatch;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;


public class carlayout1 extends Fragment implements SensorEventListener {

    private static final String TAG = "carlayout1";
    Button btnJyW, btnJyS, btnJyA, btnJyD;
    ToggleButton autoAcc, autoTurn;

    Sensor mysensor;
    SensorManager sm;
    boolean autoAccelerate = false;
    boolean autoTurning = false;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        try {
            detSend detSend = new detSend();
            if (autoTurning) {

                if (sensorEvent.values[1] > 2) {

                    detSend.execute("apress");
//                    detSend.execute("ares");
//                    detSend.execute("dres");
                }
                if (sensorEvent.values[1] < -2) {
                    detSend.execute("dpress");
//                    detSend.execute("dres");
//                    detSend.execute("ares");
                }

            }
            if (autoAccelerate) {
                if (sensorEvent.values[0] > 2) {
                    detSend.execute("sres");
                    detSend.execute("wpress");
                    detSend.execute("wres");
                }
                if (sensorEvent.values[0] < -2) {
                    detSend.execute("wres");
                    detSend.execute("spress");
                    detSend.execute("sres");
                }
            }
        } catch (Exception e) {

        }

//        yAxis.setText("Y :  " + sensorEvent.values[1]);
//        zAxis.setText("Z :  " + sensorEvent.values[2]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carlayout1, container, false);

        btnJyW = view.findViewById(R.id.btnJyW);
        btnJyS = view.findViewById(R.id.btnJyS);
        btnJyA = view.findViewById(R.id.btnJyA);
        btnJyD = view.findViewById(R.id.btnJyD);
        autoAcc = view.findViewById(R.id.autoAcc);
        autoTurn = view.findViewById(R.id.autoTurn);


        sm = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mysensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, mysensor, SensorManager.SENSOR_DELAY_NORMAL);


        autoAcc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                autoAccelerate = isChecked;
                if (isChecked) {
                    btnJyW.setEnabled(false);
                    btnJyS.setEnabled(false);
                } else {
                    btnJyW.setEnabled(true);
                    btnJyS.setEnabled(true);
                }
            }

        });

        autoTurn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                autoTurning = isChecked;
                if (isChecked) {
                    btnJyA.setEnabled(false);
                    btnJyD.setEnabled(false);
                } else {
                    btnJyA.setEnabled(true);
                    btnJyD.setEnabled(true);
                }
            }
        });


        btnJyW.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                detSend p = new detSend();

                String message = "";

                int action = motionEvent.getAction();
                int touchCount = motionEvent.getPointerCount();

                if (touchCount == 1) {
                    switch (action) {
                        case 0:    // touch down
                            try {
                                while (action == 0) {
                                    message = "wpress";
                                    p.execute(message);
                                }
                            } catch (Exception e) {
                            }
                            break;

                        case 1:    // touch up
                            message = "wres";
                            p.execute(message);
                            break;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
        btnJyS.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                detSend p = new detSend();

                String message = "";

                int action = motionEvent.getAction();
                int touchCount = motionEvent.getPointerCount();

                if (touchCount == 1) {
                    switch (action) {
                        case 0:    // touch down
                            message = "spress";
                            p.execute(message);
                            break;

                        case 1:    // touch up
                            message = "sres";
                            p.execute(message);
                            break;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
        btnJyA.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                detSend p = new detSend();

                String message = "";

                int action = motionEvent.getAction();
                int touchCount = motionEvent.getPointerCount();

                if (touchCount == 1) {
                    switch (action) {
                        case 0:    // touch down
                            message = "apress";
                            p.execute(message);
                            break;

                        case 1:    // touch up
                            message = "ares";
                            p.execute(message);
                            break;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
        btnJyD.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                detSend p = new detSend();

                String message = "";

                int action = motionEvent.getAction();
                int touchCount = motionEvent.getPointerCount();

                if (touchCount == 1) {
                    switch (action) {
                        case 0:    // touch down
                            message = "dpress";
                            p.execute(message);
                            break;

                        case 1:    // touch up
                            message = "dres";
                            p.execute(message);
                            break;
                        default:
                            break;
                    }
                }
                return false;
            }
        });


        return view;
    }


}
