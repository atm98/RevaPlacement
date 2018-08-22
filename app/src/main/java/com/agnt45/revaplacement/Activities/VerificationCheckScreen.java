package com.agnt45.revaplacement.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.agnt45.revaplacement.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class VerificationCheckScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private ImageView statusIcon;
    private TextView statusMessage;
    private Button AfterVerficatinoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_check_screen);
        statusIcon = findViewById(R.id.verified_status_logo);
        statusMessage = findViewById(R.id.verified_status_message);
        AfterVerficatinoButton = findViewById(R.id.afterVerification_button);
        AfterVerficatinoButton.setVisibility(View.GONE);
        AfterVerficatinoButton.setEnabled(false);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();

        Task task = mAuth.getCurrentUser().reload();
        task.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                user=mAuth.getCurrentUser();
                Log.d("Debug", String.valueOf(user.isEmailVerified()));
                if(user.isEmailVerified()){
                    showVerified();
                }else{
                    showUnVerified();
                }
            }
        });

    }

    private void showVerified() {
        Log.d("Debug:","Verified");
        Picasso.get().load(R.drawable.verified_icon).into(statusIcon);
        AfterVerficatinoButton.setVisibility(View.VISIBLE);
        AfterVerficatinoButton.setEnabled(true);
        statusMessage.setText("Your Account is Verified, Click on the Button below to Go further");
        AfterVerficatinoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VerificationCheckScreen.this,HomeScreen.class));
                finish();
            }
        });
    }

    private void showUnVerified() {
        Log.d("Debug:","Un-Verified");
        Picasso.get().load(R.drawable.unverified_icon).into(statusIcon);
        statusMessage.setText("Your Account is Not Verified, a Verification mail has been sent to : " + user.getEmail());
    }
}
