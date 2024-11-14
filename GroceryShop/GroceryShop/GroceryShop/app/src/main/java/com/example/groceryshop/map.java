package com.example.groceryshop;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Location;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.location.Address;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Context;

import android.location.Geocoder;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class map extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener{

    public void onMarkerDragStart(@NonNull Marker marker) {
        // This method is called when the marker drag starts.
    }

    @Override
    public void onMarkerDrag(@NonNull Marker marker) {
        // This method is called while the marker is being dragged.
    }



    private GoogleMap mMap;
    private Button zoomInButton;
    private Button zoomOutButton;

    private Button confirmaddbtn;
    public  TextView maptext;
    String addresss;
    public static String convertLatLongToString(double lati, double longi) {
        String latitude = Location.convert(lati, Location.FORMAT_SECONDS);
        String longitude = Location.convert(longi, Location.FORMAT_SECONDS);
        return latitude + "," + longitude;
    }
    public static class GeocodingUtils {

        public static String getAddressFromLatLong(Context context, double lati, double longi) {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(lati, longi, 1);
                if (addresses != null && addresses.size() > 0) {
                    Address address = addresses.get(0);

                    String addressText = address.getAddressLine(0); // Full address including street details
                    return addressText;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Address not found";
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        // Initialize UI components
        zoomInButton = findViewById(R.id.zoom_in_button);
        zoomOutButton = findViewById(R.id.zoom_out_button);
        confirmaddbtn= findViewById(R.id.confirm_add);
        String username = getIntent().getStringExtra("username");

        maptext=findViewById(R.id.maptext);
        String apiKey = getString(R.string.google_maps_api_key);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        // Set click listeners for buttons

        zoomInButton.setOnClickListener(view -> {
            if (mMap != null) {
                // Get the current camera position
                CameraPosition currentCameraPosition = mMap.getCameraPosition();

                // Increase zoom level by a certain amount (e.g., 1)
                float newZoomLevel = currentCameraPosition.zoom + 1;

                // Set the new camera position with increased zoom level
                CameraPosition newCameraPosition = new CameraPosition.Builder()
                        .target(currentCameraPosition.target)
                        .zoom(newZoomLevel)
                        .build();

                // Animate the camera to the new position
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(newCameraPosition));
            }
        });


        zoomOutButton.setOnClickListener(view -> {
            if (mMap != null) {
                // Get the current camera position
                CameraPosition currentCameraPosition = mMap.getCameraPosition();

                // Decrease zoom level by a certain amount (e.g., 1)
                float newZoomLevel = currentCameraPosition.zoom - 1;

                // Set the new camera position with decreased zoom level
                CameraPosition newCameraPosition = new CameraPosition.Builder()
                        .target(currentCameraPosition.target)
                        .zoom(newZoomLevel)
                        .build();

                // Animate the camera to the new position
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(newCameraPosition));
            }
        });
        //set on click listener for marker not used


        //Set on click listener for Confirm Address button
        confirmaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference userInfoRef = FirebaseDatabase.getInstance().getReference("Users").child(username);
                userInfoRef.child("Home Address").setValue(addresss)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(map.this, "Address saved successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(map.this, "Failed to save Address: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                Intent intent1 = new Intent(map.this,CashOnDelivery.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker at the current location in Bangladesh

        //mMap.addMarker(new MarkerOptions().position(perthLocation).title("My Location"));
        final LatLng perthLocation = new LatLng(23.758256, 90.404128);
        Marker perth = mMap.addMarker(
                new MarkerOptions()
                        .position(perthLocation)
                        .draggable(true));

       // String err= convertLatLongToString(perthLocation.latitude, perthLocation.longitude);

       // maptext.setText(addresss);

        mMap.setOnMarkerDragListener(this);
        addresss= GeocodingUtils.getAddressFromLatLong(this,perthLocation.latitude,perthLocation.longitude);
        maptext.setText(addresss);
        // Move the camera to the current location with a suitable zoom level
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(perthLocation, 12)); // Adjust the zoom level as needed
    }

    public void onMarkerDragEnd(@NonNull Marker marker) {
        LatLng newPosition = marker.getPosition();
        addresss = GeocodingUtils.getAddressFromLatLong(this, newPosition.latitude, newPosition.longitude);
        String err= convertLatLongToString(newPosition.latitude, newPosition.longitude);
        maptext.setText(addresss+"\n" +err);


    }

}