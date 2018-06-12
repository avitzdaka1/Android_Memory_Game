package afeka.com.hw1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

public class LeaderboardMap extends Fragment implements OnMapReadyCallback {
    private View view;
    private GoogleMap googleMap;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_leaderboard_map,container,false);
        initMap();
        return view;

    }

    public void initMap(){
     //   MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
    //    mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }
}
