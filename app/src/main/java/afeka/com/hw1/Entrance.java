package afeka.com.hw1;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Entrance extends AppCompatActivity {

    private Button submitButton;
    private Button leaderboardButton;
    private String name;
    private int age;
    private EditText nameField;
    private EditText ageField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
        if(googleServicesAvailable()){
            Toast.makeText(this,"Perfect!",Toast.LENGTH_LONG).show();
        }
        nameField = (EditText) findViewById(R.id.name_fld);
        ageField = (EditText) findViewById(R.id.age_fld);




        leaderboardButton = (Button) findViewById(R.id.leader_btn);
        submitButton = (Button) findViewById(R.id.submit_btn);





        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showLeaderboard();


            }
        });

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

    public boolean googleServicesAvailable(){
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if(isAvailable == ConnectionResult.SUCCESS){
            return true;
        }
        else if(api.isUserResolvableError(isAvailable)){
            Dialog dialog = api.getErrorDialog(this,isAvailable,0);
            dialog.show();
        }
        else {
            Toast.makeText(this,"Cant connect to play services!", Toast.LENGTH_LONG).show();
        }
        return false;
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

    private void showLeaderboard() {
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }


}

