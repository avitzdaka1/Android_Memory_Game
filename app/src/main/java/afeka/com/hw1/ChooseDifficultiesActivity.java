package afeka.com.hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseDifficultiesActivity extends AppCompatActivity {
    private String name;
    private int age;

    private Button btn2;
    private Button playBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficulties);

        Intent intent = getIntent();
       // name = intent.getStringExtra();


        btn2 = (Button) findViewById(R.id.btn_2x2);
        playBtn = (Button) findViewById(R.id.game_btn);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               launch2();
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                play();
            }
        });

    }

    public void launch2(){
        Intent intent = new Intent(this, Game2x2.class);
        startActivity(intent);
    }

    public void play(){
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
}
