package afeka.com.hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Entrance extends AppCompatActivity {

    private Button submitButton;
    private String name;
    private int age;
    private EditText nameField;
    private EditText ageField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
        nameField = (EditText) findViewById(R.id.name_fld);
        ageField = (EditText) findViewById(R.id.age_fld);

        submitButton = (Button) findViewById(R.id.submit_btn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmpty(nameField) || isEmpty(ageField)){
                    String str = "You can't have empty fields!!!";
                    Toast.makeText(Entrance.this,str,Toast.LENGTH_SHORT).show();
                }
                else {
                    name = ((EditText) findViewById(R.id.name_fld)).getText().toString();

                    age = Integer.parseInt(((EditText) findViewById(R.id.age_fld)).getText().toString());
                    launchActivity();
                }
            }
        });
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    private void launchActivity() {

        Intent intent = new Intent(this, ChooseDifficultiesActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("age", age);
        startActivity(intent);
    }
}
