package afeka.com.hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Entrance extends AppCompatActivity {

    private Button submitButton;
    private String name;
    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        submitButton = (Button) findViewById(R.id.submit_btn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = ((EditText)findViewById(R.id.name_fld)).getText().toString();
                age = Integer.parseInt(((EditText) findViewById(R.id.age_fld)).getText().toString());
                launchActivity();
            }
        });
    }

    private void launchActivity() {

        Intent intent = new Intent(this, ChooseDifficultiesActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("age", age);
        startActivity(intent);
    }
}
