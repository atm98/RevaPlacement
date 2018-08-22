package com.agnt45.revaplacement.Activities;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.agnt45.revaplacement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordScreen extends AppCompatActivity {
    private TextInputLayout email;
    private Button Submit;
    private TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_screen);
        email = findViewById(R.id.password_forgot_email_input);
        message = findViewById(R.id.success_message);
        Submit = findViewById(R.id.submit_button);

            Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(email.getEditText().getText().toString().equals("")){
                        email.setError("Please Enter Email");
                    }else{
                    final ProgressDialog progressDialog = new ProgressDialog(ForgotPasswordScreen.this);
                    progressDialog.setMessage("Sending Password Reset Email");
                    progressDialog.show();
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email.getEditText().getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        message.setVisibility(View.VISIBLE);
                                        message.setText("Password Reset Email Sent to: "+email.getEditText().getText().toString());
                                    }else if(!task.isSuccessful()){
                                        progressDialog.hide();
                                        message.setVisibility(View.VISIBLE);
                                        message.setText("Please Check Email for Corrections");

                                    }
                                }
                            });
                    }
                }
            });

        }
    }


