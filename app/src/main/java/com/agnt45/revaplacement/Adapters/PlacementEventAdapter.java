package com.agnt45.revaplacement.Adapters;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agnt45.revaplacement.Activities.PlacementEventCardDetailScreen;
import com.agnt45.revaplacement.Classes.PlacementEvent;
import com.agnt45.revaplacement.R;
import com.firebase.client.Firebase;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.ObservableSnapshotArray;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class PlacementEventAdapter extends FirestoreRecyclerAdapter<PlacementEvent,PlacementEventAdapter.PlacementEventViewHolder> {


    public PlacementEventAdapter(@NonNull FirestoreRecyclerOptions<PlacementEvent> options) {
        super(options);
    }

    @NonNull
    @Override
    public ObservableSnapshotArray<PlacementEvent> getSnapshots() {
        return super.getSnapshots();
    }

    @Override
    protected void onBindViewHolder(@NonNull final PlacementEventViewHolder holder, final int position, @NonNull PlacementEvent model) {
        holder.desc.setText(model.getDesc());
        Picasso.get().load(model.getPicUrl()).into(holder.pic);
        holder.duration.setText(model.getTimeofPost().toString());
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(v.getContext(), PlacementEventCardDetailScreen.class);
                details.putExtra("DocumentId",getSnapshots().getSnapshot(position).getId());
                details.putExtra("Comment",true);
                v.getContext().startActivity(details);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(v.getContext(), PlacementEventCardDetailScreen.class);
                details.putExtra("DocumentId",getSnapshots().getSnapshot(position).getId());
                v.getContext().startActivity(details);
            }
        });
        holder.like.isClickable();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore.getInstance().collection("PlacementEvent")
                        .document(getSnapshots().getSnapshot(position).getId())
                        .collection("Likes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            Log.d("task",task.getResult().getDocuments().toString());
                            boolean flag = false;
                            for(final DocumentSnapshot documentSnapshot :task.getResult()){
                                if(documentSnapshot.getId().equals(user.getUid())){
                                    holder.like.setImageResource(R.drawable.ic_action_like);
                                    holder.like.getDrawable().setColorFilter(holder.itemView.getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);

                                    flag=true;

                                }
                            }
                            if(flag){
                                FirebaseFirestore.getInstance().collection("PlacementEvent")
                                        .document(getSnapshots().getSnapshot(position).getId())
                                        .collection("Likes").document(user.getUid()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            holder.like.setImageResource(R.drawable.ic_action_like);
                                            holder.like.getDrawable().setColorFilter(holder.itemView.getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                                        }
                                    }
                                });
                            }else{
                                Map<String,String> likeMap = new HashMap<>();
                                likeMap.put("like_Status","LIKED");
                                FirebaseFirestore.getInstance().collection("PlacementEvent")
                                        .document(getSnapshots().getSnapshot(position).getId())
                                        .collection("Likes").document(user.getUid()).set(likeMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            holder.like.setImageResource(R.drawable.ic_action_like);
                                            holder.like.getDrawable().setColorFilter(holder.itemView.getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                                        }
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });


    }

    @NonNull
    @Override
    public PlacementEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_car_placement_event,parent,false);
        return new PlacementEventViewHolder(view);
    }

    class PlacementEventViewHolder extends RecyclerView.ViewHolder{
        TextView desc;
        ImageView pic;
        TextView duration;
        ImageView like,comment;
        public PlacementEventViewHolder(View itemView) {
            super(itemView);
            desc  = itemView.findViewById(R.id.desc_placement_event);
            pic = itemView.findViewById(R.id.pic_placement_event);
            duration = itemView.findViewById(R.id.duration_since_posting);
            like = itemView.findViewById(R.id.like_button);
            comment = itemView.findViewById(R.id.comment_button);

        }
    }
}




