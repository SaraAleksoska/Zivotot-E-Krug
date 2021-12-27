package com.example.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

public class Adapter3 extends FirebaseRecyclerAdapter<Activity,Adapter3.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    private OnItemClickListener listener;

    public Adapter3(@NonNull FirebaseRecyclerOptions<Activity> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Adapter3.myViewHolder holder, int position, @NonNull Activity model) {
        holder.type.setText(model.getTypeA());
        holder.desc.setText(model.getDescA());
        holder.loc.setText(model.getLoc());
        holder.time.setText(model.getTime());
        holder.rep.setText(model.getRep());
        holder.urg.setText(model.getUrg());
        holder.own.setText(model.getOwnName());
    }

    @NonNull
    @Override
    public Adapter3.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity3,parent,false);
        return new Adapter3.myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView type,desc,loc,time,rep,urg,own;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            type = (TextView) itemView.findViewById(R.id.type);
            desc = (TextView) itemView.findViewById(R.id.desc);
            loc = (TextView) itemView.findViewById(R.id.loc);
            time = (TextView) itemView.findViewById(R.id.time);
            rep = (TextView) itemView.findViewById(R.id.rep);
            urg = (TextView) itemView.findViewById(R.id.urg);
            own = (TextView) itemView.findViewById(R.id.own);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });

        }



    }

    public interface OnItemClickListener{
        void onItemClick(DataSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }
}
