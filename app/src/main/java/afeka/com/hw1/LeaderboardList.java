package afeka.com.hw1;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeaderboardList extends Fragment {
    private ListView leaders;
    private HashMap<Integer, ArrayList<Score>> scoreList;
    private View view;
    private int idx[];
    private Button mode2;
    private Button mode4;
    private Button mode5;
    private int gameMode = 2;
     private TextView leaderText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        scoreList = new HashMap<>();
        ArrayList<Score> list2 = new ArrayList<>();
        ArrayList<Score> list4 = new ArrayList<>();
        ArrayList<Score> list5 = new ArrayList<>();
        scoreList.put(2,list2);
        scoreList.put(4,list4);
        scoreList.put(5,list5);


        idx = new int[5];
        view = inflater.inflate(R.layout.fragment_leaderboard_list,container,false);
        leaders = view.findViewById(R.id.score_list);
        leaderText = (TextView) view.findViewById(R.id.leader_txt);
                mode2 = (Button) view.findViewById(R.id.btn_mode_2);
        mode4 = (Button) view.findViewById(R.id.btn_mode_4);
        mode5 = (Button) view.findViewById(R.id.btn_mode_5);

        mode2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaderText.setText(R.string.lead_easy);
                 ScoreAdapter adapter = new ScoreAdapter(getActivity(),scoreList.get(2));
                leaders.setAdapter(adapter);
            }
        });
        mode4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaderText.setText(R.string.lead_medium);
                ScoreAdapter adapter = new ScoreAdapter(getActivity(),scoreList.get(4));
                leaders.setAdapter(adapter);
            }


        });
        mode5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaderText.setText(R.string.lead_hard);
                 ScoreAdapter adapter = new ScoreAdapter(getActivity(), scoreList.get(5));
                    leaders.setAdapter(adapter);
            }
        });

        ParseQuery<ParseObject> query = ParseQuery.getQuery("PlayerScore");
        query.orderByDescending("Score");
        try {
            List<ParseObject> data = query.find();
            for(ParseObject ob : data){
                Score temp = new Score();
                temp.setUserName(++idx[ob.getInt("GameMode")] + ". " + ob.getString("Name"));
                temp.setGameMode(ob.getInt("GameMode"));
                temp.setScore(ob.getDouble("Score"));
                temp.setCoordinates(ob.getParseGeoPoint("Coordinates"));
                scoreList.get(temp.getGameMode()).add(temp);

            }
        } catch (ParseException e){
            e.printStackTrace();
        }


        ScoreAdapter adapter = new ScoreAdapter(this.getActivity(),scoreList.get(gameMode));




        leaders.setAdapter(adapter);


        return view;
    }



}

