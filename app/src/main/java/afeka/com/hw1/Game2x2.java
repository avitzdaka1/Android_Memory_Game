package afeka.com.hw1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Game2x2 extends AppCompatActivity {

    ImageView im_11, im_12, im_21, im_22;

    Integer[] cardArray = {101,102,201,202};

    int img101,img102,img201,img202;

    int firstCard,secondCard;

    int clickedFirst, clickedSecond;

    int cardNumber = 1;

    int turn = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2x2);

        im_11 = (ImageView) findViewById(R.id.img_11);
        im_12 = (ImageView) findViewById(R.id.img_12);
        im_21 = (ImageView) findViewById(R.id.img_21);
        im_22 = (ImageView) findViewById(R.id.img_22);

        im_11.setTag("0");
        im_12.setTag("1");
        im_21.setTag("2");
        im_22.setTag("3");

        loadCards();

        Collections.shuffle(Arrays.asList(cardArray));

        im_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(im_11,theCard);
            }
        });
        im_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(im_12,theCard);
            }
        });
        im_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(im_21,theCard);
            }
        });
        im_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(im_22,theCard);
            }
        });

    }
    private  void doStuff(ImageView im, int card){
            if(cardArray[card] == 101 ){
                im.setImageResource(img101);
            } else if(cardArray[card] == 102){
                im.setImageResource(img102);
            } else if(cardArray[card] == 201){
                im.setImageResource(img201);
            } else{
                im.setImageResource(img202);
            }

            if(cardNumber == 1) {
                firstCard = cardArray[card];
                if (firstCard > 200) {
                    firstCard = firstCard - 100;
                }
                cardNumber = 2;
                clickedFirst = card;

                im.setEnabled(false);
            } else if(cardNumber == 2){
                secondCard = cardArray[card];
                if(secondCard > 200){
                    secondCard = secondCard - 100;
                }
                cardNumber = 1;
                clickedSecond = card;

                im_11.setEnabled(false);
                im_12.setEnabled(false);
                im_21.setEnabled(false);
                im_22.setEnabled(false);

                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculate();
                    }
                },1000);
            }
    }

    private void calculate(){
        if(firstCard == secondCard){
            if(clickedFirst == 0){
                im_11.setActivated(true);
            } else if(clickedFirst == 1){
                im_12.setActivated(true);
            } else if(clickedFirst == 2){
                im_21.setActivated(true);
            } else if(clickedFirst == 3){
                im_22.setActivated(true);
            }

            if(clickedSecond == 0){
                im_11.setActivated(true);
            } else if(clickedSecond == 1){
                im_12.setActivated(true);
            } else if(clickedSecond == 2){
                im_21.setActivated(true);
            } else if(clickedSecond == 3){
                im_22.setActivated(true);
            }
        } else {
            if(!im_11.isActivated())
                im_11.setImageResource(R.drawable.img_back);
            if(!im_12.isActivated())
                im_12.setImageResource(R.drawable.img_back);
            if(!im_21.isActivated())
                im_21.setImageResource(R.drawable.img_back);
            if(!im_22.isActivated())
                im_22.setImageResource(R.drawable.img_back);
        }

        if(!im_11.isActivated())
            im_11.setEnabled(true);
        if(!im_12.isActivated())
            im_12.setEnabled(true);
        if(!im_21.isActivated())
            im_21.setEnabled(true);
        if(!im_22.isActivated())
            im_22.setEnabled(true);

        checkEnd();

    }

    private void checkEnd(){
        if(im_11.isActivated() && im_12.isActivated() && im_21.isActivated() && im_22.isActivated()){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Game2x2.this);
            alertDialogBuilder.setMessage("YOU WIN!!!!").setCancelable(false).setPositiveButton("NEW", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i){
                Intent intent = new Intent(getApplicationContext(),Game2x2.class);
                startActivity(intent);
                finish();
                }
            }).setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    private void loadCards(){
        img101 = R.drawable.img_1;
        img102 = R.drawable.img_2;
        img201 = R.drawable.img_1;
        img202 = R.drawable.img_2;
    }
}
