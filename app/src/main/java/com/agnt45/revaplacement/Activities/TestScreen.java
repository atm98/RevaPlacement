package com.agnt45.revaplacement.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.agnt45.revaplacement.Adapters.QuestionAdapter;
import com.agnt45.revaplacement.Classes.PlacementEvent;
import com.agnt45.revaplacement.Classes.Question;
import com.agnt45.revaplacement.R;
import com.firebase.client.Firebase;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TestScreen extends AppCompatActivity {


    private RecyclerView recyclerView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("DailyTest");
    private QuestionAdapter questionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_screen);
        Firebase.setAndroidContext(this);
        Query query = collectionReference.document("JMvCAvd3yBYbejDHDrfO").collection("Questions");
        FirestoreRecyclerOptions<Question> options = new FirestoreRecyclerOptions.Builder<Question>()
                .setQuery(query,Question.class).build();
        questionAdapter = new QuestionAdapter(options);
        recyclerView = findViewById(R.id.question_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(questionAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        questionAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        questionAdapter.stopListening();
    }
}
