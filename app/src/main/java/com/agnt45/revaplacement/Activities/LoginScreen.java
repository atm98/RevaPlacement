package com.agnt45.revaplacement.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.agnt45.revaplacement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {
    TextInputLayout email,password;
    TextView forgotPasswd;
    private FirebaseAuth mAuth;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email_input);
        password = findViewById(R.id.password_input);
        forgotPasswd = findViewById(R.id.forgot_passwd);
        submit = findViewById(R.id.login_button);
        forgotPasswd.isClickable();
        forgotPasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreen.this,ForgotPasswordScreen.class));
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(email,password);
            }
        });

    }

    private void login(TextInputLayout email, TextInputLayout password) {
        if(email.getEditText().getText().toString().equals("")){
            email.setError("Please Enter Email");
        }else if(password.getEditText().getText().toString().equals("")){
            password.setError("Please Enter Password");
        }else if(email.getEditText().getText().toString().equals("")&&password.getEditText().getText().toString().equals("")){
            email.setError("Please Enter Email");
            password.setError("Please Enter Password");
        }else{
            mAuth.signInWithEmailAndPassword(email.getEditText().getText().toString(), password.getEditText().getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(LoginScreen.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser!=null){
            if(currentUser.isEmailVerified()) {
                startActivity(new Intent(LoginScreen.this, HomeScreen.class));
                finish();
            }else{
                startActivity(new Intent(LoginScreen.this, VerificationCheckScreen.class));
                finish();
            }
        }
    }
}
