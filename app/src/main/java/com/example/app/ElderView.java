package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

public class ElderView extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterVolonter adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elder_view);

        recyclerView = (RecyclerView) findViewById(R.id.activities);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Activity>options =
                new FirebaseRecyclerOptions.Builder<Activity>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Activities").orderByChild("own").equalTo(FirebaseAuth.getInstance().getUid()),Activity.class)
                .build();


        adapter2 = new AdapterVolonter(options);
        recyclerView.setAdapter(adapter2);

        adapter2.setOnItemClickListener(new AdapterVolonter.OnItemClickListener() {
            @Override
            public void onItemClick(DataSnapshot documentSnapshot, int position) {
                FirebaseDatabase.getInstance().getReference().child("Activities").child(documentSnapshot.getKey()).removeValue();
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        adapter2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter2.stopListening();
    }
    public void rateing1 (View view){
        Intent intent=new Intent(getApplicationContext(),RateVolonter.class);
        startActivity(intent);
    }
}