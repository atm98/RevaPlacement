package com.agnt45.revaplacement.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.agnt45.revaplacement.Activities.ResultScreen;
import com.agnt45.revaplacement.Classes.Question;
import com.agnt45.revaplacement.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.ObservableSnapshotArray;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuestionAdapter extends
        FirestoreRecyclerAdapter<Question,QuestionAdapter.QuestionViewHolder> {

    private Map<String,Object> answers;

    private Context context;

    public QuestionAdapter(@NonNull FirestoreRecyclerOptions<Question> options, Context context) {

        super(options);
        this.answers = new HashMap<>();
        this.context = context;


    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @NonNull
    @Override
    public ObservableSnapshotArray<Question> getSnapshots() {
        return super.getSnapshots();
    }

    @Override
    protected void onBindViewHolder(@NonNull final QuestionViewHolder holder, final int position, @NonNull final Question model) {
        holder.Question.setText(model.getQuestionText());
        holder.Next.setVisibility(View.GONE);
        holder.Next.setEnabled(false);
        holder.option_layout.setEnabled(true);
        holder.r1.setText(model.getOptionArray().get(0).toString());
        holder.r2.setText(model.getOptionArray().get(1).toString());
        holder.r3.setText(model.getOptionArray().get(2).toString());
        holder.r4.setText(model.getOptionArray().get(3).toString());
        holder.option_layout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(position!=getItemCount()-1){
                    Log.d("Position "+String.valueOf(position),
                            "CheckedId "+String.valueOf(checkedId));
                    Log.d("ItemCount", String.valueOf(getItemCount()));
                    if(checkedId==R.id.option1){

                        answers.put(String.valueOf(position),model.getOptionArray().get(0));
                        holder.Next.setVisibility(View.VISIBLE);
                        holder.Next.setEnabled(true);
                        holder.Next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                holder.r1.setChecked(true);
                                holder.r2.setChecked(false);
                                holder.r3.setChecked(false);
                                holder.r4.setChecked(false);

                            }
                        });
                    }else  if(checkedId==R.id.option2){
                        answers.put(String.valueOf(position),model.getOptionArray().get(1));
                        holder.Next.setVisibility(View.VISIBLE);

                        holder.Next.setEnabled(true);
                        holder.Next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                holder.r1.setChecked(false);
                                holder.r2.setChecked(true);
                                holder.r3.setChecked(false);
                                holder.r4.setChecked(false);


                            }
                        });
                    }else  if(checkedId==R.id.option3){
                        answers.put(String.valueOf(position),model.getOptionArray().get(2));
                        holder.Next.setVisibility(View.VISIBLE);
                        holder.Next.setEnabled(true);
                        holder.Next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                holder.r1.setChecked(false);
                                holder.r2.setChecked(false);
                                holder.r3.setChecked(true);
                                holder.r4.setChecked(false);

                            }
                        });
                    }else  if(checkedId==R.id.option4){
                        answers.put(String.valueOf(position),model.getOptionArray().get(3));
                        holder.Next.setVisibility(View.VISIBLE);
                        holder.Next.setEnabled(true);
                        holder.Next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                holder.r1.setChecked(false);
                                holder.r2.setChecked(false);
                                holder.r3.setChecked(false);
                                holder.r4.setChecked(true);

                            }
                        });
                    }
                }else{
                    if(checkedId==R.id.option1){
                        answers.put(String.valueOf(position),model.getOptionArray().get(0));
                        holder.Next.setVisibility(View.VISIBLE);
                        holder.Next.setEnabled(true);
                        holder.Next.setText("SUBMIT TEST");
                        holder.Next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //holder.option_layout.setEnabled(false);
                                holder.r1.setChecked(true);
                                holder.r2.setChecked(false);
                                holder.r3.setChecked(false);
                                holder.r4.setChecked(false);
                                checkHashMap(holder);


                            }
                        });
                    }else  if(checkedId==R.id.option2){
                        answers.put(String.valueOf(position),model.getOptionArray().get(1));
                        holder.Next.setVisibility(View.VISIBLE);
                        holder.Next.setText("SUBMIT TEST");
                        holder.Next.setEnabled(true);
                        holder.Next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //holder.option_layout.setEnabled(false);
                                holder.r1.setChecked(false);
                                holder.r2.setChecked(true);
                                holder.r3.setChecked(false);
                                holder.r4.setChecked(false);
                                checkHashMap(holder);

                            }
                        });
                    }else  if(checkedId==R.id.option3){
                        answers.put(String.valueOf(position),model.getOptionArray().get(2));
                        holder.Next.setVisibility(View.VISIBLE);
                        holder.Next.setText("SUBMIT TEST");
                        holder.Next.setEnabled(true);
                        holder.Next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //holder.option_layout.setEnabled(false);
                                holder.r1.setChecked(false);
                                holder.r2.setChecked(false);
                                holder.r3.setChecked(true);
                                holder.r4.setChecked(false);
                                checkHashMap(holder);

                            }
                        });
                    }else  if(checkedId==R.id.option4){
                        answers.put(String.valueOf(position),model.getOptionArray().get(3));
                        holder.Next.setVisibility(View.VISIBLE);
                        holder.Next.setText("SUBMIT TEST");
                        holder.Next.setEnabled(true);
                        holder.Next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //holder.option_layout.setEnabled(false);
                                holder.r1.setChecked(false);
                                holder.r2.setChecked(false);
                                holder.r3.setChecked(false);
                                holder.r4.setChecked(true);
                                checkHashMap(holder);

                            }
                        });
                    }
                }
            }
        });
    }

    private void checkHashMap(QuestionViewHolder viewHolder) {
        Intent intent = new Intent(viewHolder.itemView.getContext(), ResultScreen.class);
        Bundle bundle = new Bundle();
        intent.putExtra("Size:",getItemCount());
        intent.putExtra("Ans:", (Serializable) answers);
        viewHolder.itemView.getContext().startActivity(intent);
    }


    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_question,parent,false);
        return new QuestionViewHolder(view,context);
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder{
        RadioGroup option_layout;
        RadioButton r1,r2,r3,r4;
        TextView Question;
        Button Next;
        Context con;
        public QuestionViewHolder(View itemView, Context context) {
            super(itemView);
            option_layout = itemView.findViewById(R.id.options);
            r1 = option_layout.findViewById(R.id.option1);
            r2 = option_layout.findViewById(R.id.option2);
            r3 = option_layout.findViewById(R.id.option3);
            r4 = option_layout.findViewById(R.id.option4);
            option_layout.removeAllViews();
            option_layout.addView(r1);
            option_layout.addView(r2);
            option_layout.addView(r3);
            option_layout.addView(r4);
            Question = itemView.findViewById(R.id.Question);
            Next = itemView.findViewById(R.id.next_button);
            this.con= context;
        }

    }
}
