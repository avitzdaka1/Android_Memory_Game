package afeka.com.hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseDifficultiesActivity extends AppCompatActivity {
    private String name;
    private int age;

    private RadioGroup mode;
    private Button playBtn;
    private int checkedBUttonId;
    private int gameMode;

    private RadioButton butt;



    private TextView nameField;
    private TextView ageField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficulties);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        age = intent.getExtras().getInt("age");
        nameField = (TextView) findViewById(R.id.name_fld);
        ageField = (TextView) findViewById(R.id.age_fld);

        nameField.setText("Your name: " + name);
        ageField.setText("Your age: " + String.valueOf(age));


        playBtn = (Button) findViewById(R.id.game_btn);
        mode = (RadioGroup)findViewById(R.id.radioGroup);

        mode.check(R.id.mode_easy);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkedBUttonId = mode.getCheckedRadioButtonId();

                switch(checkedBUttonId) {
                    case R.id.mode_easy:
                        gameMode = 2;
                        break;
                    case R.id.mode_medium:
                        gameMode = 4;
                        break;
                    case R.id.mode_hard:
                        gameMode = 5;
                        break;
                }


                play();

            }
        });


    }



    public void play(){
        Intent intent = new Intent(this, Game.class);
        intent.putExtra("mode",gameMode);
        intent.putExtra("name",name);
        intent.putExtra("age",age);
        startActivity(intent);
    }














    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.mode_easy:
                if (checked)
                    gameMode = 2;
                    break;
            case R.id.mode_medium:
                if (checked)
                    gameMode = 4;
                    break;
            case R.id.mode_hard:
                if (checked)
                    gameMode = 5;
                    break;
        }
    }

}
