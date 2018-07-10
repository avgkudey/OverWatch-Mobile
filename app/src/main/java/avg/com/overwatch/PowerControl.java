package avg.com.overwatch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class PowerControl extends Fragment implements View.OnClickListener {
    private static final String TAG = "powerFragment";
    Button btnsignout, btnshd, btnrestart, btnsleep, btnlock;
    String action = "";
    DialogInterface.OnClickListener dialogClickListener;
    AlertDialog.Builder builder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_power_control, container, false);
        btnsignout = view.findViewById(R.id.btnsignout);
        btnshd = view.findViewById(R.id.btnshd);
        btnrestart = view.findViewById(R.id.btnrst);
        btnsleep = view.findViewById(R.id.btnsleep);
        btnlock = view.findViewById(R.id.btnLock);

        btnsignout.setOnClickListener(this);
        btnrestart.setOnClickListener(this);
        btnsleep.setOnClickListener(this);
        btnshd.setOnClickListener(this);
        btnlock.setOnClickListener(this);

        dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        runaction(action.toUpperCase());
                        dialog.dismiss();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };
        builder = new AlertDialog.Builder(getActivity());

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnsignout:
                action = "Signout";
                break;
            case R.id.btnshd:
                action = "Shutdown";
                break;
            case R.id.btnrst:
                action = "Restart";
                break;
            case R.id.btnsleep:
                action = "Sleep";
                break;
            case R.id.btnLock:
                action = "Lock";
                break;
        }
        showConfirmDialog();
    }

    private void showConfirmDialog() {
        builder.setTitle(action)
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener)
                .show();
    }

    public void runaction(String act) {
        detSend p = new detSend();
        p.execute(act);
    }
}
