package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class RateVolonter extends AppCompatActivity {

    Button button;
    RatingBar ratingBar;

    int myRating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_volonter);

        button = findViewById(R.id.rate);
        ratingBar = findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                int rating = (int) v;
                String message = null;

                myRating = (int) ratingBar.getRating();

                switch (rating){
                    case 1:
                        message = "1 star";
                        break;
                    case 2:
                        message = "2 stars";
                        break;
                    case 3:
                        message = "3 stars";
                        break;
                    case 4:
                        message = "4 stars";
                        break;
                    case 5:
                        message = "5 stars";
                        break;
                }
                Toast.makeText(RateVolonter.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RateVolonter.this, String.valueOf(myRating), Toast.LENGTH_SHORT).show();
            }
        });
    }
}