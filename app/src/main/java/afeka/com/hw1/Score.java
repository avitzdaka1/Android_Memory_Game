package afeka.com.hw1;

import com.parse.Parse;
import com.parse.ParseObject;

public class Score {
    private String userName;
    private int gameMode;
    private double score;


    public Score(){


    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setGameMode(int gameMode){
        this.gameMode = gameMode;
    }
    public void setScore(double score){
        this.score = score;
    }

}