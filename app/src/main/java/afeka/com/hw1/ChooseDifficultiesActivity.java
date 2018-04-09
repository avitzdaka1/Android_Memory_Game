package afeka.com.hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChooseDifficultiesActivity extends AppCompatActivity {
    private String name;
    private int age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficulties);

        Intent intent = getIntent();
        name = intent.getStringExtra()

    }
}
