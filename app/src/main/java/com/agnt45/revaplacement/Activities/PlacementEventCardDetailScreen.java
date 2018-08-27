package com.agnt45.revaplacement.Activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agnt45.revaplacement.Adapters.CommentAdapter;
import com.agnt45.revaplacement.Classes.Comment;
import com.agnt45.revaplacement.Classes.PlacementEvent;
import com.agnt45.revaplacement.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class PlacementEventCardDetailScreen extends AppCompatActivity {

    private String DocumentId;
    private TextView duration,desc,links;
    private ImageView pic_PlacementEvent,like,comment,share,user_pic,send_message;
    private RecyclerView commentsRecyclerView;
    private Map<String,Object> placementEvent;
    private boolean comment_Flag=false;
    private FirebaseUser user;
    private LinearLayout commentLayout;
    private EditText comment_message;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference;
    private CommentAdapter commentAdapter;

    @Override
    protected void onStart() {
        super.onStart();
        commentAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        commentAdapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_event_card_detail_screen);
        user = FirebaseAuth.getInstance().getCurrentUser();
        Intent DetailsIntent = this.getIntent();
        DocumentId = DetailsIntent.getStringExtra("DocumentId");
        collectionReference = db.collection("PlacementEvent").document(DocumentId).collection("Comments");
        commentsRecyclerView = findViewById(R.id.coment_recyclerView);
        comment_Flag = DetailsIntent.getBooleanExtra("Comment",false);
        commentLayout = findViewById(R.id.comment_Input_layout);
        comment_message = commentLayout.findViewById(R.id.message_comment);
        send_message = commentLayout.findViewById(R.id.add_comment_user);
        user_pic = commentLayout.findViewById(R.id.pic_comment_user);
        Query query = collectionReference;
        FirestoreRecyclerOptions<Comment> options = new FirestoreRecyclerOptions.Builder<Comment>().setQuery(query,Comment.class).build();
        commentAdapter = new CommentAdapter(options,DocumentId);
        commentsRecyclerView.setHasFixedSize(true);
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(PlacementEventCardDetailScreen.this));

        if(comment_Flag){

            commentsRecyclerView.setVisibility(View.VISIBLE);
            commentLayout.setVisibility(View.VISIBLE);
            commentsRecyclerView.setAdapter(commentAdapter);
            
        }else {
            commentsRecyclerView.setVisibility(View.GONE);
            commentLayout.setVisibility(View.GONE);
        }
        user_pic=findViewById(R.id.pic_comment_user);
        duration = findViewById(R.id.duration_since_posting_detail);
        desc = findViewById(R.id.desc_placement_event_detail);
        links = findViewById(R.id.links_placement_event_detail);
        pic_PlacementEvent = findViewById(R.id.pic_placement_event_detail);
        like = findViewById(R.id.like_button_detail);
        comment = findViewById(R.id.comment_button_detail);
        share = findViewById(R.id.share_button_detail);
        Picasso.get().load(user.getPhotoUrl()).into(user_pic);
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentLayout.setVisibility(View.VISIBLE);
                commentsRecyclerView.setVisibility(View.VISIBLE);
                commentsRecyclerView.setAdapter(commentAdapter);
            }
        });
        send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!comment_message.getText().toString().equals("")){
                    Map<String,Object> commentData = new HashMap<>();
                    commentData.put("commentMessage",comment_message.getText().toString());
                    commentData.put("commentUsername",FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                    commentData.put("commentUserpic",user.getPhotoUrl().toString());
                    commentData.put("commentUseruid",FirebaseAuth.getInstance().getCurrentUser().getUid());


                    FirebaseFirestore.getInstance().collection("PlacementEvent").document(DocumentId).collection("Comments").add(commentData).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){
                                comment_message.setText("");
                                Toast.makeText(PlacementEventCardDetailScreen.this,"Comment Added",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        FirebaseFirestore.getInstance().collection("PlacementEvent").document(DocumentId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    placementEvent = documentSnapshot.getData();
                    if(placementEvent!=null){
                        duration.setText(placementEvent.get("timeofPost").toString());
                        desc.setText((CharSequence) placementEvent.get("Desc"));
                        Picasso.get().load(String.valueOf(placementEvent.get("picUrl"))).into(pic_PlacementEvent);
                        
                    }
                }
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore.getInstance().collection("PlacementEvent")
                        .document(DocumentId)
                        .collection("Likes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            Log.d("task",task.getResult().getDocuments().toString());
                            boolean flag = false;
                            for(final DocumentSnapshot documentSnapshot :task.getResult()){
                                if(documentSnapshot.getId().equals(user.getUid())){
                                    like.setImageResource(R.drawable.ic_action_like);
                                    like.getDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);

                                    flag=true;

                                }
                            }
                            if(flag){
                                FirebaseFirestore.getInstance().collection("PlacementEvent")
                                        .document(DocumentId)
                                        .collection("Likes").document(user.getUid()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            like.setImageResource(R.drawable.ic_action_like);
                                            like.getDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                                        }
                                    }
                                });
                            }else{
                                Map<String,String> likeMap = new HashMap<>();
                                likeMap.put("like_Status","LIKED");
                                FirebaseFirestore.getInstance().collection("PlacementEvent")
                                        .document(DocumentId)
                                        .collection("Likes").document(user.getUid()).set(likeMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            like.setImageResource(R.drawable.ic_action_like);
                                            like.getDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
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
}
