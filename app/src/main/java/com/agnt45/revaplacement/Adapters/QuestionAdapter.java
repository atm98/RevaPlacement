package com.agnt45.revaplacement.Adapters;

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

import com.agnt45.revaplacement.Classes.Question;
import com.agnt45.revaplacement.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.ObservableSnapshotArray;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuestionAdapter extends
        FirestoreRecyclerAdapter<Question,QuestionAdapter.QuestionViewHolder> {

    private Map<String,Object> answers;
    public QuestionAdapter(@NonNull FirestoreRecyclerOptions<Question> options) {

        super(options);
        answers = new HashMap<>();
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
    protected void onBindViewHolder(@NonNull final QuestionViewHolder holder, int position, @NonNull Question model) {
        holder.Question.setText(model.getQuestionText());
        holder.Next.setVisibility(View.GONE);
        holder.Next.setEnabled(false);
        int a =holder.createRadioButton(model.getOptionList());
        if(position!=getItemCount()){
            switch (a){
                case 100:{
                    answers.put(String.valueOf(position),model.getOptionList()[0]);
                    holder.Next.setVisibility(View.VISIBLE);
                    holder.Next.setEnabled(true);
                    holder.Next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.option_layout.setEnabled(false);
                        }
                    });
                }case 101:{
                    answers.put(String.valueOf(position),model.getOptionList()[1]);
                    holder.Next.setVisibility(View.VISIBLE);
                    holder.Next.setEnabled(true);
                    holder.Next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.option_layout.setEnabled(false);
                        }
                    });
                }case 102:{
                    answers.put(String.valueOf(position),model.getOptionList()[2]);
                    holder.Next.setVisibility(View.VISIBLE);
                    holder.Next.setEnabled(true);
                    holder.Next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.option_layout.setEnabled(false);
                        }
                    });
                }case 103:{
                    answers.put(String.valueOf(position),model.getOptionList()[3]);
                    holder.Next.setVisibility(View.VISIBLE);
                    holder.Next.setEnabled(true);
                    holder.Next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.option_layout.setEnabled(false);
                        }
                    });
                }
            }
        }else {
            switch (a){
                case 100:{
                    answers.put(String.valueOf(position),model.getOptionList()[0]);
                    holder.Next.setVisibility(View.VISIBLE);
                    holder.Next.setEnabled(true);
                    holder.Next.setText("Submit Test");
                    holder.Next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("Debug", String.valueOf(answers));
                        }
                    });
                }case 101:{
                    answers.put(String.valueOf(position),model.getOptionList()[1]);
                    holder.Next.setVisibility(View.VISIBLE);
                    holder.Next.setEnabled(true);
                    holder.Next.setText("Submit Test");
                    holder.Next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("Debug", String.valueOf(answers));

                        }
                    });
                }case 102:{
                    answers.put(String.valueOf(position),model.getOptionList()[2]);
                    holder.Next.setVisibility(View.VISIBLE);
                    holder.Next.setEnabled(true);
                    holder.Next.setText("Submit Test");
                    holder.Next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("Debug", String.valueOf(answers));

                        }
                    });
                }case 103:{
                    answers.put(String.valueOf(position),model.getOptionList()[3]);
                    holder.Next.setVisibility(View.VISIBLE);
                    holder.Next.setEnabled(true);
                    holder.Next.setText("Submit Test");
                    holder.Next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("Debug", String.valueOf(answers));

                        }
                    });
                }
            }
        }

    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_question,parent,false);
        return new QuestionViewHolder(view);
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder{
        LinearLayout option_layout;
        TextView Question;
        Button Next;
        public QuestionViewHolder(View itemView) {
            super(itemView);
            option_layout = itemView.findViewById(R.id.options);
            Question = itemView.findViewById(R.id.Question);
            Next = itemView.findViewById(R.id.next_button);
        }
        public int createRadioButton(String[] options) {
            final RadioButton[] rb = new RadioButton[5];
            final int[] id = new int[1];
            RadioGroup rg = new RadioGroup(itemView.getContext()); //create the RadioGroup
            rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
            for(int i=0; i<5; i++){
                rb[i]  = new RadioButton(itemView.getContext());
                rb[i].setText(options[i]);
                rb[i].setId(i + 100);
                rg.addView(rb[i]);
            }
            option_layout.addView(rg);//you add the whole RadioGroup to the layout
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    id[0] =checkedId;
                }
            });
            return id[0];
        }
    }
}
