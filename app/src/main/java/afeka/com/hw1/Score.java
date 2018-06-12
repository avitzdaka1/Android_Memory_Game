package afeka.com.hw1;

import com.parse.Parse;
import com.parse.ParseObject;

public class Score {
    private String name;
    private int gameMode;
    private double score;


    public Score(){


    }

    public void setUserName(String name){
        this.name = name;
    }

    public void setGameMode(int gameMode){
        this.gameMode = gameMode;
    }
    public void setScore(double score){
        this.score = score;
    }
    public String getName(){
        return name;
    }
    public double getScore(){
        return score;
    }
    public int getGameMode(){
        return gameMode;
    }

}
