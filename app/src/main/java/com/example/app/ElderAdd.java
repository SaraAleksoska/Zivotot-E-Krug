package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ElderAdd extends AppCompatActivity implements View.OnClickListener , LocationListener {

    private EditText editTypeActivity, editDescActivity, editTime, editRepetitive, editUrgency, editLocation;
    private Button addButton;
    private FirebaseAuth mAuth;
    private TextView banner;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    private String userID;
    LocationManager locationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elder_add);

        mAuth = FirebaseAuth.getInstance();


        mDatabase = FirebaseDatabase.getInstance().getReference();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        addButton = (Button) findViewById(R.id.addActivity);
        addButton.setOnClickListener(this);


        editTypeActivity = (EditText) findViewById(R.id.typeActivity);
        editDescActivity = (EditText) findViewById(R.id.descActivity);
        editTime = (EditText) findViewById(R.id.time);
        editRepetitive = (EditText) findViewById(R.id.repetitive);
        editUrgency = (EditText) findViewById(R.id.urgency);
        editLocation = (EditText) findViewById(R.id.location);


        if(ContextCompat.checkSelfPermission(ElderAdd.this,Manifest.permission.ACCESS_FINE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(ElderAdd.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    100);
        }

        editLocation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    getLocation();
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.addActivity:
                addActivity();
                break;
        }

    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,ElderAdd.this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(41.9981, 21.4254,  1);
            Address address = addresses.get(0);
            editLocation.setHint(address.getLocality() + ", " + address.getCountryName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(ElderAdd.this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }


    private void addActivity() {

        String type = editTypeActivity.getText().toString().trim();
        String desc = editDescActivity.getText().toString().trim();
        String time = editTime.getText().toString().trim();
        String rep = editRepetitive.getText().toString().trim();
        String urg = editUrgency.getText().toString().trim();
        String loc = editLocation.getText().toString().trim();

        if (type.isEmpty()) {
            editTypeActivity.setError("Type of the Activity is required !");
            editTypeActivity.requestFocus();
            return;
        }

        if (desc.isEmpty()) {
            editDescActivity.setError("Description of the Activity is required !");
            editDescActivity.requestFocus();
            return;
        }

        if (time.isEmpty()) {
            editTime.setError("Time for the Activity is required !");
            editTime.requestFocus();
            return;
        }

        if (rep.isEmpty()) {
            editRepetitive.setError("Repetition information for the Activity is required !");
            editRepetitive.requestFocus();
            return;
        }

        if(!rep.equals("repetitive")&&!rep.equals("one-time"))
        {
            editUrgency.setError("Valid Urgency information for the Activity is required !");
            editUrgency.requestFocus();
            return;
        }

        if (urg.isEmpty()) {
            editUrgency.setError("Urgency information for the Activity is required !");
            editUrgency.requestFocus();
            return;
        }

        if(!urg.equals("yes")&&!urg.equals("no"))
        {
            editUrgency.setError("Valid Urgency information for the Activity is required !");
            editUrgency.requestFocus();
            return;
        }

        if (loc.isEmpty()) {
            editLocation.setError("Location for the Activity is required !");
            editLocation.requestFocus();
            return;
        }

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        FirebaseDatabase.getInstance().getReference("Users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user  = snapshot.getValue(User.class);

                if(user != null)
                {
                    Activity activity = new Activity(type,desc,time,rep,urg,loc, userID);
                    activity.setOwnName(user.getFullName());
                    mDatabase.child("Activities").push().setValue(activity).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ElderAdd.this, "Activity has been added successfully!", Toast.LENGTH_LONG).show();

                            }
                            else
                            {
                                Toast.makeText(ElderAdd.this, "Something went wrong. Try again !", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}