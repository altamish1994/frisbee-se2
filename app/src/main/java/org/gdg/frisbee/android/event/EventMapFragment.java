package org.gdg.frisbee.android.event;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Marker;

import org.gdg.frisbee.android.R;

/**
 * Created by Adeel on 10/15/2015.
 */
public class EventMapFragment extends Fragment {


    // Google Map
    private GoogleMap googleMap;
    private MapFragment mapFrag;
    Marker marker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.fragment_event_map, parent, false);
        try {
            // Loading map
            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;

    }

    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {

            mapFrag = (MapFragment)  getActivity().getFragmentManager().findFragmentById(R.id.myEventMap);
            googleMap = mapFrag.getMap();

            googleMap.setMyLocationEnabled(true); //enabling current location

            googleMap.getUiSettings().setZoomControlsEnabled(true);

            googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

                @Override
                public void onCameraChange(CameraPosition arg0) {
                    // TODO Auto-generated method stub
                    if (marker != null) {
                        marker.setPosition(arg0.target);
                    }
                }
            });


            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getActivity(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initilizeMap();
    }

}
