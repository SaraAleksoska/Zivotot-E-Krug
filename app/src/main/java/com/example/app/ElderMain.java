package com.example.app;

import static com.example.app.R.layout.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ElderMain extends AppCompatActivity implements View.OnClickListener{

    private Button addNewActivity, viewActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elder_main);

        addNewActivity = (Button) findViewById(R.id.add);
        addNewActivity.setOnClickListener(this);

        viewActivities = (Button) findViewById(R.id.view);
        viewActivities.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.add:
                startActivity(new Intent(this, ElderAdd.class));
                break;
            case R.id.view:
                startActivity(new Intent(this, ElderView.class));
                break;
        }
    }
}