package avg.com.overwatch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Player extends Fragment implements View.OnClickListener {
    private static final String TAG = "mouseFragment";
    Button btnVlUp, btnVlDown, btnSkNext, btnSkBack, btnPlay, btnNext, btnBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        btnVlUp = view.findViewById(R.id.btnPlayerVlUp);// not set
        btnVlDown = view.findViewById(R.id.btnPlayerVlDown);// not set
        btnSkNext = view.findViewById(R.id.btnPlayerSkNext);
        btnSkBack = view.findViewById(R.id.btnPlayerSkBack);
        btnPlay = view.findViewById(R.id.btnPlayerPlay);
        btnNext = view.findViewById(R.id.btnPlayerNext);
        btnBack = view.findViewById(R.id.btnPlayerBack);


        btnPlay.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);




        btnVlUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                detSend p = new detSend();

                String message = "";

                int action = motionEvent.getAction();
                int touchCount = motionEvent.getPointerCount();

                if (touchCount == 1) {
                    switch (action) {
                        case 0:    // touch down

                               message = "playervluppress";
                               p.execute(message);

                            break;

                        case 1:    // touch up
                            message = "playervlupres";
                            p.execute(message);
                            break;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
        btnVlDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                detSend p = new detSend();

                String message = "";

                int action = motionEvent.getAction();
                int touchCount = motionEvent.getPointerCount();

                if (touchCount == 1) {
                    switch (action) {
                        case 0:    // touch down
                            message = "playervldownpress";
                            p.execute(message);
                            break;

                        case 1:    // touch up
                            message = "playervldownres";
                            p.execute(message);
                            break;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        btnSkNext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                detSend p = new detSend();

                String message = "";

                int action = motionEvent.getAction();
                int touchCount = motionEvent.getPointerCount();

                if (touchCount == 1) {
                    switch (action) {
                        case 0:    // touch down
                            message = "sknextpress";
                            p.execute(message);
                            break;

                        case 1:    // touch up
                            message = "sknextres";
                            p.execute(message);
                            break;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        btnSkBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                detSend p = new detSend();

                String message = "";

                int action = motionEvent.getAction();
                int touchCount = motionEvent.getPointerCount();

                if (touchCount == 1) {
                    switch (action) {
                        case 0:    // touch down
                            message = "skbackpress";
                            p.execute(message);
                            break;

                        case 1:    // touch up
                            message = "skbackres";
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


    @Override
    public void onClick(View view) {
        String action = "";
        switch (view.getId()) {
            case R.id.btnPlayerPlay:
                action = "playerplay";
                break;
            case R.id.btnPlayerNext:
                action = "playernext";
                break;
            case R.id.btnPlayerBack:
                action = "playerback";
                break;
            default:
                action = "";
        }
        runaction(action);
    }

    public void runaction(String act) {
        detSend p = new detSend();
        p.execute(act);
    }
}
