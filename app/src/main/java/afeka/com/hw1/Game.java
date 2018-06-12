package afeka.com.hw1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game extends AppCompatActivity {

    CountDownTimer timer;
    TextView tmr;
    int timerDuration;
    private Score score;
    private String name;
    private int age;
    private int points = 0;
    private int gameMode;
    private int index = 0;
    boolean finish = false;
    boolean isWin = false;

    private TextView nameField;
    private TextView ageField;

    private ArrayList<ImageView> list;
    private ArrayList<Integer> imageData;

    int firstCard, secondCard;

    int idFirst, idSecond;

    int cardNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        nameField = (TextView) findViewById(R.id.name_fld);
        ageField = (TextView) findViewById(R.id.age_fld);
        tmr = (TextView) findViewById(R.id.tmr_fld);

        Intent intent = getIntent();
        gameMode = intent.getExtras().getInt("mode");
        name = intent.getStringExtra("name");
        age = intent.getExtras().getInt("age");

        score = new Score();
        score.setGameMode(gameMode);
        score.setUserName(name);

        switch (gameMode){
            case 2:
                timerDuration = 5;
                break;
            case 4:
                timerDuration = 45;
                break;
            case 5:
                timerDuration = 60;
                break;
        }

        timer = new MyTimer(timerDuration*1000, 1000, finish) {

            public void onTick(long millisUntilFinished) {
                tmr.setText("seconds remaining: " + millisUntilFinished / 1000);
                updateTime(millisUntilFinished);
                score.setScore(millisUntilFinished);
                if (getRemainTime() < 1) {
                    isWin = false;
                    endGame();
                }
            }

            public void onFinish() {
                tmr.setText("done!");
            }
        }.start();


        nameField.setText("Your name: " + name);
        ageField.setText("Your age: " + String.valueOf(age));

        LinearLayout gamePanel = (LinearLayout) findViewById(R.id.rows);
        gamePanel.setWeightSum(gameMode);

        list = new ArrayList<>(gameMode * gameMode);
        imageData = new ArrayList<>(gameMode * gameMode);


        for (int i = 0; i < gameMode; i++) {
            LinearLayout temp = new LinearLayout(this);
            temp.setWeightSum(gameMode);

            temp.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            temp.setLayoutParams(lp);

            for (int j = 0; j < gameMode; j++) {

                int id = Integer.parseInt(String.valueOf(i) + String.valueOf(j));
                String imgName = "img_" + id;

                ImageView img = new ImageView(this);
                img.setImageResource(R.drawable.img_back);

                img.setTag(index++);
                img.setId(id);

                img.setLayoutParams(lp);
                temp.addView(img);
                list.add(img);
            }

            gamePanel.addView(temp);


        }


        loadCards();

        Collections.shuffle(imageData);

        for (ImageView img : list) {
            img.setOnClickListener(imgListener);
        }
    }

    private View.OnClickListener imgListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < list.size(); i++) {
                if (v.getId() == list.get(i).getId()) {
                    int theCard = (int) v.getTag();
                    doStuff(list.get(i), theCard);
                }
            }
        }
    };

    private void loadCards() {

        for (int i = 0; i < gameMode * gameMode / 2; i++) {
            imageData.add(getResources().getIdentifier("img_" + (i + 1), "drawable", getPackageName()));

        }
        for (int i = 0; i < gameMode * gameMode / 2; i++) {
            imageData.add(getResources().getIdentifier("img_" + (i + 1), "drawable", getPackageName()));

        }

        if (gameMode % 2 == 1)
            imageData.add((getResources().getIdentifier("img_13", "drawable", getPackageName())));


    }

    private void doStuff(ImageView im, int card) {

        im.setImageResource(imageData.get(card));


        if (cardNumber == 1) {
            firstCard = imageData.get(card);
            cardNumber = 2;
            idFirst = card;
            im.setEnabled(false);
        } else if (cardNumber == 2) {
            secondCard = imageData.get(card);
            cardNumber = 1;
            idSecond = card;

            for (ImageView iv : list)
                iv.setEnabled(false);

            Handler handler = new Handler();


            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    calculate();
                }
            }, 1000);


        }


    }

    private void calculate() {
        if (firstCard == secondCard) {
            list.get(idFirst).setActivated(true);
            list.get(idSecond).setActivated(true);
            points++;
        }

        for (ImageView iv : list) {
            if (!iv.isActivated()) {
                iv.setEnabled(true);
                iv.setImageResource(R.drawable.img_back);
            }
        }


        checkEnd();

    }

    private void checkEnd() {
        if (points == gameMode * gameMode / 2) {
            finish = true;
            isWin = true;
        }

        if(finish){
            endGame();
        }
    }

    private void endGame() {
        if(isWin) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("PlayerScore");
            query.whereEqualTo("GameMode", gameMode);
            query.orderByDescending("Score");
            query.setLimit(10);

            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> scoreList, ParseException e) {

                    if (scoreList.size() < 10) {
                        ParseObject temp = new ParseObject("PlayerScore");
                        temp.put("Name", score.getName());
                        temp.put("GameMode", score.getGameMode());
                        temp.put("Score", score.getScore());
                        temp.saveInBackground();

                    } else {
                        if (scoreList.get(9).getDouble("Score") < score.getScore()) {
                            scoreList.get(9).put("Name", score.getName());
                            scoreList.get(9).put("Score", score.getScore());
                            scoreList.get(9).saveInBackground();
                        }

                    }
                }
            });


            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Game.this);
            alertDialogBuilder.setMessage("You Win! Time taken to complete: " + ((MyTimer) timer).getTimeTaken() + " seconds!").setCancelable(false).setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getApplicationContext(), Game.class);
                    intent.putExtra("name", name);
                    intent.putExtra("age", age);
                    intent.putExtra("mode", gameMode);
                    startActivity(intent);
                    finish();
                }
            }).setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            timer.cancel();

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

    } else {
            moveCard();
            timer.cancel();

        }
    }

    public void moveCard(){
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 600);
        animation.setDuration(500);
        animation.setFillAfter(false);
        animation.setAnimationListener(new MyAnimationListener(list));

        for(ImageView card : list){
            card.startAnimation(animation);
        }
    }
    private class MyAnimationListener implements Animation.AnimationListener{
        private ArrayList<ImageView> list;

        public MyAnimationListener(ArrayList<ImageView> list){
            this.list = list;
        }
        @Override
        public void onAnimationEnd(Animation animation) {
            for(ImageView card : list){
                card.clearAnimation();
                card.setTranslationY(600);
            }

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Game.this);
            alertDialogBuilder.setMessage("You Loose! Try again...").setCancelable(false).setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getApplicationContext(), Game.class);
                    intent.putExtra("name", name);
                    intent.putExtra("age", age);
                    intent.putExtra("mode", gameMode);
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

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

    }


}


class MyTimer extends CountDownTimer{
    private long timeLeft;
    private long timerDuration;
    boolean finish;
    public MyTimer(long millisInFuture, long countDownInterval,boolean finish) {
        super(millisInFuture, countDownInterval);
        timeLeft = millisInFuture;
        timerDuration = millisInFuture;
        this.finish = finish;
    }

    @Override
    public void onTick(long l) {

    }

    @Override
    public void onFinish() {

    }
    public void updateTime(long millis){
        timeLeft = millis;

    }
    public int getTimeTaken(){
        return (int)(timerDuration - timeLeft)/1000;

    }
    public int getRemainTime(){
        return (int)timeLeft/1000;
    }
}