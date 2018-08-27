package com.agnt45.revaplacement.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.agnt45.revaplacement.Adapters.CompanyAdapter;
import com.agnt45.revaplacement.Adapters.PlacementEventAdapter;
import com.agnt45.revaplacement.Classes.CompanyInfo;
import com.agnt45.revaplacement.Classes.PlacementEvent;
import com.agnt45.revaplacement.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Map;

public class IconScreen extends AppCompatActivity {

    FirebaseFirestore db  = FirebaseFirestore.getInstance();
    DocumentReference collectionReference = db.collection("TestTypes").document("qXUItZf7ehrv48LxpjkG");
    RecyclerView recyclerView ;
    TestsAdapter adapter;
    CompanyAdapter Adapter;
    int choice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_screen);
        Intent intent = this.getIntent();
        recyclerView = findViewById(R.id.testNameRecycler);

        choice = intent.getIntExtra("iconFilter:",10);
        Toast.makeText(IconScreen.this,String.valueOf(choice),Toast.LENGTH_SHORT).show();
        if(choice==0){
           showCompInfo();
        }else if(choice==1){
            showquant();
        }else if(choice==2){
            showlogical();
        }else if(choice==3){
            showsoftskills();
        }else if(choice==4){
           showgd();
        }else if(choice==5){
            showverbal();
        }else if(choice==6){
            showtechnical();
        }else if(choice==7){
            showhr();
        }else if(choice==8){
            showresume();
        }

    }

    private void show() {
        //
    }

    private void showresume() {
    }

    private void showhr() {
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    ArrayList<String> testArray = (ArrayList<String>)
                            task.getResult().getData().get("hrArray");
                    if (testArray != null) {
                        Log.d("Not_Null:",String.valueOf(testArray));
                        adapter = new TestsAdapter(IconScreen.this,testArray);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(IconScreen.this));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
        });

    }

    private void showtechnical() {
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    ArrayList<String> testArray = (ArrayList<String>)
                            task.getResult().getData().get("technicalArray");
                    if (testArray != null) {
                        Log.d("Not_Null:",String.valueOf(testArray));
                        adapter = new TestsAdapter(IconScreen.this,testArray);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(IconScreen.this));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
        });
    }

    private void showgd() {
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    ArrayList<String> testArray = (ArrayList<String>)
                            task.getResult().getData().get("gdArray");
                    if (testArray != null) {
                        Log.d("Not_Null:",String.valueOf(testArray));
                        adapter = new TestsAdapter(IconScreen.this,testArray);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(IconScreen.this));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
        });

    }

    private void showsoftskills() {

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    ArrayList<String> testArray = (ArrayList<String>)
                            task.getResult().getData().get("sskillsArray");
                    if (testArray != null) {
                        Log.d("Not_Null:",String.valueOf(testArray));
                        adapter = new TestsAdapter(IconScreen.this,testArray);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(IconScreen.this));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
        });
    }

    private void showverbal() {
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    ArrayList<String> testArray = (ArrayList<String>) task.getResult().getData().get("verbalArray");
                    if (testArray != null) {
                        Log.d("Not_Null:",String.valueOf(testArray));
                        adapter = new TestsAdapter(IconScreen.this,testArray);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(IconScreen.this));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
        });
    }

    private void showlogical() {
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                     Map<String,Object> data= task.getResult().getData();
                     Log.d("Test", String.valueOf(data));
                    ArrayList<String> testArray= (ArrayList<String>) data.get("logicalArray");
                    if (testArray != null) {
                        Log.d("Not_Null:",String.valueOf(testArray));
                        adapter = new TestsAdapter(IconScreen.this,testArray);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(IconScreen.this));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
        });
    }

    private void showquant() {
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    ArrayList<String> testArray ;
                    Map<String,Object> map = task.getResult().getData();

                    if(map.containsKey("quantArray")){
                        testArray = (ArrayList<String>) map.get("quantArray");
                        Log.d("AA",String.valueOf(testArray));
                        adapter = new TestsAdapter(IconScreen.this,testArray);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(IconScreen.this));
                        recyclerView.setAdapter(adapter);
                    }
//                    if (testArray != null) {
//                        Log.d("Not_Null:",String.valueOf(testArray));

//                    }
                }
            }
        });
    }

    private void showCompInfo() {
        Query query = FirebaseFirestore.getInstance().collection("CompanyProfile");
        FirestoreRecyclerOptions<CompanyInfo> options = new FirestoreRecyclerOptions.Builder<CompanyInfo>()
                .setQuery(query,CompanyInfo.class).build();
         Adapter = new CompanyAdapter(options);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(IconScreen.this));
        recyclerView.setAdapter(Adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(Adapter!=null){
            Adapter.startListening();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(Adapter!=null) {
            Adapter.stopListening();
        }
    }

    public class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.TestViewHolder>{

        Context context;
        ArrayList<String> testList;
        public TestsAdapter(Context context, ArrayList<String> testList) {
            this.context =context;
            this.testList = testList;
        }

        @NonNull
        @Override
        public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_test,parent,false);
            return new TestViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final TestViewHolder holder, final int position) {
            holder.testName.setText(testList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(),TestScreen.class);
                    intent.putExtra("TestType",testList.get(position));
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return testList.size();
        }

        public class TestViewHolder extends RecyclerView.ViewHolder{

            TextView testName;
            public TestViewHolder(View itemView) {
                super(itemView);
                testName = itemView.findViewById(R.id.testName);
            }
        }
    }
}
