package com.example.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.*;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;

public class Adapter2 extends FirebaseRecyclerAdapter<Activity,Adapter2.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    private Adapter2.OnItemClickListener listener;
    public Adapter2(@NonNull FirebaseRecyclerOptions<Activity> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Activity model) {
        holder.type.setText(model.getTypeA());
        holder.desc.setText(model.getDescA());
        holder.loc.setText(model.getLoc());
        holder.time.setText(model.getTime());
        holder.act.setText(model.getState());
        holder.rep.setText(model.getRep());
        holder.urg.setText(model.getUrg());
        holder.vol.setText(model.getVolName());
        holder.tel.setText(model.getVolTel());
        holder.mail.setText(model.getVolmail());

        if(holder.mail.getText().equals("/"))
        {
            holder.mail.setVisibility(View.GONE);
            holder.email.setVisibility(View.GONE);
        }

        if(holder.tel.getText().equals("/"))
        {
            holder.tel.setVisibility(View.GONE);
            holder.phonenumber.setVisibility(View.GONE);
        }

        if(holder.vol.getText().equals("/")){
            holder.vol.setVisibility(View.GONE);
            holder.volunteer.setVisibility(View.GONE);
        }

        if(!holder.mail.getText().equals("/"))
        {
            holder.mail.setVisibility(View.VISIBLE);
            holder.email.setVisibility(View.VISIBLE);
        }

        if(!holder.tel.getText().equals("/"))
        {
            holder.tel.setVisibility(View.VISIBLE);
            holder.phonenumber.setVisibility(View.VISIBLE);
        }

        if(!holder.vol.getText().equals("/")){
            holder.vol.setVisibility(View.VISIBLE);
            holder.volunteer.setVisibility(View.VISIBLE);
        }


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity2,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView type,desc,loc,time,rep,urg,act,vol,tel, mail, volunteer, phonenumber, email;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            type = (TextView) itemView.findViewById(R.id.type);
            desc = (TextView) itemView.findViewById(R.id.desc);
            loc = (TextView) itemView.findViewById(R.id.loc);
            time = (TextView) itemView.findViewById(R.id.time);
            rep = (TextView) itemView.findViewById(R.id.rep);
            urg = (TextView) itemView.findViewById(R.id.urg);
            act = (TextView) itemView.findViewById(R.id.act);
            vol = (TextView) itemView.findViewById(R.id.vol);
            tel = (TextView) itemView.findViewById(R.id.phone);
            mail = (TextView) itemView.findViewById(R.id.mail);
            volunteer = (TextView) itemView.findViewById(R.id.volunteer);
            phonenumber = (TextView) itemView.findViewById(R.id.phonenumber);
            email = (TextView) itemView.findViewById(R.id.email);

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

    public void setOnItemClickListener(Adapter2.OnItemClickListener listener)
    {
        this.listener = listener;
    }

}
