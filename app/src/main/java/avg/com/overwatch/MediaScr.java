package avg.com.overwatch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MediaScr extends Fragment {

    private static final String TAG = "mediaFragment";
    Button btnback, btnplay, btnnext, btnMute;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_scr, container, false);

        btnback = view.findViewById(R.id.btnbackmedia);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runaction("mediaback");
            }
        });


        btnnext = view.findViewById(R.id.btnnextmedia);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runaction("medianext");
            }
        });

        btnplay = view.findViewById(R.id.btnplaymedia);
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runaction("mediaplay");
            }
        });

        btnMute = view.findViewById(R.id.btnMute);
        btnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runaction("mediamute");
            }
        });

        return view;
    }


    public void runaction(String act) {
        detSend p = new detSend();
        p.execute(act);
    }
}
