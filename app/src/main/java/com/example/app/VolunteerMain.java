package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VolunteerMain extends AppCompatActivity implements View.OnClickListener {

    private Button getNewActivity, viewActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_main);

        getNewActivity = (Button) findViewById(R.id.get);
        getNewActivity.setOnClickListener(this);

        viewActivities = (Button) findViewById(R.id.view);
        viewActivities.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get:
                startActivity(new Intent(this, VolunteerGet.class));
                break;
            case R.id.view:
                startActivity(new Intent(this,VolunteerView.class));
                break;
        }

    }
}