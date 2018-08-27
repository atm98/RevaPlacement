package com.agnt45.revaplacement.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.agnt45.revaplacement.R;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultScreen extends AppCompatActivity {

    Map<String,Object> ans,Answ ;
    ArrayList<String> Answer;
    TextView score,correctText,incorrectText;
    Button retake,submit;
    FirebaseUser user;
    int Size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);
        score = findViewById(R.id.score);
        correctText = findViewById(R.id.correct);
        incorrectText = findViewById(R.id.incorrect);
        retake = findViewById(R.id.reteke_test);
        submit = findViewById(R.id.submit_upload);
        Intent intent = this.getIntent();
        user = FirebaseAuth.getInstance().getCurrentUser();
        Size = intent.getIntExtra("Size:",0);
        ans = (Map<String, Object>) intent.getSerializableExtra("Ans:");
        Answ = new HashMap<>();
        Log.d("Answers:", String.valueOf(ans));
        FirebaseFirestore.getInstance().collection("DailyTest").document("JMvCAvd3yBYbejDHDrfO").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    Answer= (ArrayList<String>) task.getResult().getData().get("Answers");
                    for(int i =0;i<Size;i++){
                        Answ.put(String.valueOf(i),Answer.get(i).toString());
                    }
                    checkResult(ans,Answ,Size,user);
                }
            }
        });

    }

    private void checkResult(Map<String, Object> ans, Map<String, Object> answ, final int size, final FirebaseUser user) {
        int Correct=0;
        int Incorrect=0;

        Log.d("CheckResult", String.valueOf(ans));
        Log.d("CheckResult", String.valueOf(answ));
        Log.d("CheckResult",String.valueOf(size));

        if(ans.values().equals(answ.values()))
        {
            Correct=size;

        }
        correctText.setText(Correct + " Correct");
        incorrectText.setText(Incorrect+ " Incorrect");
        score.setText(Correct+"/"+size);
        retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultScreen.this,TestScreen.class));
                finish();
            }
        });
        final int finalCorrect = Correct;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> testData = new HashMap<>();
                testData.put("correctQuestions", finalCorrect);
                testData.put("TotalQuestions",size);
                FirebaseFirestore.getInstance().collection("Users")
                        .document(user.getUid()).collection("Tests").document("JMvCAvd3yBYbejDHDrfO")
                        .set(testData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ResultScreen.this,"test Data Uploaded",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ResultScreen.this,HomeScreen.class));
                            finish();
                        }
                    }
                });
            }
        });
    }
}
