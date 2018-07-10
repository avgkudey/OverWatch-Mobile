package avg.com.overwatch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class Mouse extends Fragment {
    private static final String TAG = "mouseFragment";
    Button btnleft, btnright, btnMiddle;
    ImageView mousepad, scrollbar;

    float initX = 0;
    float initY = 0;
    float disX = 0;
    float disY = 0;
    private int mouse_sensitivity = 1;
    int xCoordinator;
    int yCoordinator;
    boolean mouseMoved, moultiTouch;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mouse, container, false);
        btnleft = view.findViewById(R.id.btnLeft);
        btnright = view.findViewById(R.id.btnright);
        mousepad = view.findViewById(R.id.mousepad);

        mousepad.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MousePortal p = new MousePortal();
                String message = "";
                MousePortal b1 = new MousePortal();
                int action = event.getAction();
                int touchCount = event.getPointerCount();
////////////////////////////////////////////
                switch (action) {

                    case 0:    // touch down
                        initX = event.getX();
                        initY = event.getY();
                        break;

                    case 1:    // touch up
                        long deltaTime = event.getEventTime() - event.getDownTime();
                        if (deltaTime < 250) {
                            message = "touch up";
                            b1.execute(message);
                        }
                        break;

                    case 2: // moved
                        int deltaX = (int) ((initX - event.getX()) * -1);
                        int deltaY = ((int) (initY - event.getY())) * -1;
                        message = deltaX * mouse_sensitivity + "," + deltaY * mouse_sensitivity;
                        b1.execute(message);
                        initX = event.getX();
                        initY = event.getY();
                        break;
                    default:
                        break;
                }
                //}


                ///////////////////////////////////


                // if a single touch
               /* if (touchCount == 1) {
                    switch (action) {

                        case 0:    // touch down
                            initX = event.getX();
                            initY = event.getY();
                            break;

                        case 1:    // touch up
                            long deltaTime = event.getEventTime() - event.getDownTime();
                            if (deltaTime < 250) {
                                message = "touch up";
                                b1.execute(message);
                            }
                            break;

                        case 2: // moved
                            int deltaX = (int) ((initX - event.getX()) * -1);
                            int deltaY = ((int)(initY - event.getY())) * -1;
                            message = deltaX * mouse_sensitivity + "," + deltaY * mouse_sensitivity;
                            b1.execute(message);
                            initX = event.getX();
                            initY = event.getY();
                            break;
                        default:
                            break;
                    }
                }

                // if two touches send scroll message
                // based off MAC osx multi touch scrolls up and down
                else if (touchCount == 2) {
                    if (action == 0) {
                        message = "kjkj";
                        b1.execute(message);
                    }
                    if (action == 1) {
                        message = "right";
                        b1.execute(message);
                    }
                    if (action == 2) {
                        message = "rigxcxcxht";
                        b1.execute(message);
                        float deltaY = event.getY() - initY;
                        float tolerance = 10;

                        if (deltaY > tolerance) {
//                            sendToAppDel(Constants.SCROLLUP);
                            initY = event.getY();
                        } else if (deltaY < -1 * tolerance) {
//                            sendToAppDel(Constants.SCROLLDOWN);
                            initY = event.getY();
                        }
                    } else initY = event.getY();
                }*/
                return true;
            }
        });


        btnleft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MousePortal p = new MousePortal();
                btnleft.performClick();
                String message = "";

                int action = motionEvent.getAction();
                int touchCount = motionEvent.getPointerCount();

                if (touchCount == 1) {
                    switch (action) {
                        case 0:    // touch down
                            message = "left-press";
                            p.execute(message);
                            break;

                        case 1:    // touch up
                            message = "left-release";
                            p.execute(message);
                            break;
                        default:
                            break;
                    }
                }
                return false;
            }
        });


        btnright.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MousePortal p = new MousePortal();
                btnleft.performClick();
                String message = "";

                int action = motionEvent.getAction();
                int touchCount = motionEvent.getPointerCount();

                if (touchCount == 1) {
                    switch (action) {
                        case 0:    // touch down
                            message = "right-press";
                            p.execute(message);
                            break;

                        case 1:    // touch up
                            message = "right-release";
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

