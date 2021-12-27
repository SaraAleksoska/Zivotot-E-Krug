package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

public class VolunteerView extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_view);

        recyclerView = (RecyclerView) findViewById(R.id.activities);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Activity> options =
                new FirebaseRecyclerOptions.Builder<Activity>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Activities").orderByChild("vol").equalTo(FirebaseAuth.getInstance().getUid()),Activity.class)
                        .build();

        adapter = new Adapter(options);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataSnapshot documentSnapshot, int position) {
                Activity activity = documentSnapshot.getValue(Activity.class);
                activity.setState("finished");
                activity.setVol("/");
                FirebaseDatabase.getInstance().getReference().child("Activities").child(documentSnapshot.getKey()).setValue(activity);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}