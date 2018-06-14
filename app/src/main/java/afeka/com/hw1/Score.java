package afeka.com.hw1;

import com.google.android.gms.maps.model.LatLng;
import com.parse.Parse;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

public class Score {
    private String name;
    private int gameMode;
    private double score;
    private ParseGeoPoint coordinates;


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
    public void setCoordinates(ParseGeoPoint myLocation){
        if(myLocation == null)
            coordinates = new ParseGeoPoint(0,0);
        else
            coordinates = new ParseGeoPoint(myLocation.getLatitude(),myLocation.getLongitude());
    }
    public ParseGeoPoint getCoordinates(){
        return coordinates;
    }

}
