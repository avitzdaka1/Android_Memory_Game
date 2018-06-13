package afeka.com.hw1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends FragmentActivity {
    private Button toggleBtn;
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        toggleBtn = (Button) findViewById(R.id.toggle_btn);
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
            Fragment list = new LeaderboardList();
            FragmentManager fm  = getSupportFragmentManager();
            fm.beginTransaction().add(R.id.fragment_container, list,"Current").commit();
            mode = 1;


        }

        toggleBtn.setText("Switch to Map View");

        toggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                if(mode == 1){
                    FrameLayout fl = (FrameLayout) findViewById(R.id.fragment_container);
                    fl.removeAllViews();
                     fragment = new LeaderboardMap();
                    toggleBtn.setText("Switch to List View");
                    mode = 2;
                } else {
                    FrameLayout fl = (FrameLayout) findViewById(R.id.fragment_container);
                    fl.removeAllViews();
                     fragment = new LeaderboardList();
                    toggleBtn.setText("Switch to Map View");
                    mode = 1;
                }

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, fragment).commit();

            }
        });

    }

}
