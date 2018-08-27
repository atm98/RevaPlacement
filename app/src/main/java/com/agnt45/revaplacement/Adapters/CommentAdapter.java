package com.agnt45.revaplacement.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agnt45.revaplacement.Classes.Comment;
import com.agnt45.revaplacement.R;
import com.firebase.client.Firebase;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.ObservableSnapshotArray;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;


public class CommentAdapter extends FirestoreRecyclerAdapter<Comment
        ,CommentAdapter.CommentViewHolder> {

    String DocumentId;
    public CommentAdapter(@NonNull FirestoreRecyclerOptions<Comment> options, String documentId) {
        super(options);
        this.DocumentId = documentId;
    }

    @NonNull
    @Override
    public ObservableSnapshotArray<Comment> getSnapshots() {
        return super.getSnapshots();
    }

    @Override
    protected void onBindViewHolder(@NonNull final CommentViewHolder holder, final int position, @NonNull final Comment model) {
        holder.commentUserName.setText(model.getCommentUsername());
        holder.commentMessage.setText(model.getCommentMessage());
        Picasso.get().load(model.getCommentUserpic()).into(holder.picUser);
        if(!(model.getCommentUserpic() ==null)){
            Picasso.get().load(model.getCommentUserpic()).into(holder.picUser);
        }
        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(model.getCommentUseruid())){
            holder.delComment.setVisibility(View.VISIBLE);
            holder.delComment.setEnabled(true);
            holder.delComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseFirestore.getInstance().collection("PlacementEvent").
                            document(DocumentId).collection("Comments").document(getSnapshots().
                            getSnapshot(position).getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(holder.itemView.getContext(),position+","+"Comment Deleted",Toast.LENGTH_SHORT).show();
                                notifyItemRemoved(position);
                            }
                        }
                    });
                }
            });
        }else{
            holder.delComment.setVisibility(View.GONE);
            holder.delComment.setEnabled(false);
        }
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();

    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_commet
        ,parent,false);
        return new CommentViewHolder(view);
    }

    class CommentViewHolder extends RecyclerView.ViewHolder{
        private ImageView picUser,delComment;
        private TextView commentUserName,commentMessage;
        public CommentViewHolder(View itemView) {
            super(itemView);
            picUser = itemView.findViewById(R.id.pic_comment_user);
            delComment = itemView.findViewById(R.id.del_comment_by_user);
            commentUserName = itemView.findViewById(R.id.name_comment_user);
            commentMessage = itemView.findViewById(R.id.message_commet_user);
        }
    }
}
