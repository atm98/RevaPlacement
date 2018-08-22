package com.agnt45.revaplacement.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.agnt45.revaplacement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterScreen extends AppCompatActivity {

    private TextInputLayout fullName,SRN,Mobile,Email,Password,confirmPassword;
    private Spinner Department;
    private Button Register;
    private TextView Login_TextButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        mAuth = FirebaseAuth.getInstance();
        fullName = findViewById(R.id.user_fullName);
        SRN = findViewById(R.id.user_SRN);
        Mobile = findViewById(R.id.user_Mobile);
        Email = findViewById(R.id.user_Email);
        Password = findViewById(R.id.user_Password);
        confirmPassword = findViewById(R.id.user_Password_confirm);
        Department = findViewById(R.id.user_Department);
        Register = findViewById(R.id.register_Button);
        Login_TextButton = findViewById(R.id.login_button);
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("SELECT DEPARTMENT");
        spinnerArray.add("ECE");
        spinnerArray.add("CSE");
        spinnerArray.add("EEE");
        spinnerArray.add("ME");
        spinnerArray.add("CE");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Department.setAdapter(adapter);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Password.getEditText().getText().toString().equals(confirmPassword.getEditText().getText().toString())){
                    if(!(fullName.getEditText().getText().toString().equals("")||SRN.getEditText().getText().toString().equals("")||Department.getSelectedItem().toString().equals("SELECT DEPARTMENT")||Mobile.getEditText()
                            .getText().toString().equals("")||Email.getEditText().getText().toString().equals("")||Password.getEditText().getText().toString().equals(""))){
                        register(fullName.getEditText().getText().toString(),SRN.getEditText().getText()
                                .toString(),Department.getSelectedItem().toString(),Mobile.getEditText()
                                .getText().toString(),Email.getEditText().getText().toString(),Password.getEditText().getText().toString());


                    }else{
                        Toast.makeText(RegisterScreen.this,"Please fill All the feilds to Register",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Password.setError("Password Does not Match");
                    confirmPassword.setError("Password Does not Match");
                }

            }
        });
    }

    private void register(final String name, final String srn, final String dept, final String mobile, final String email, String password) {
        //final ProgressDialog progressDialog = new ProgressDialog(this);
        //progressDialog.setTitle("Registering User");
        //progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //progressDialog.setMessage("User Registered");
                            final Map<String,Object> userData = new HashMap<>();
                            userData.put("fullName",name);
                            userData.put("SRN",srn);
                            userData.put("Department",dept);
                            userData.put("Mobile",mobile);
                            userData.put("email",email);

                            final FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileData = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name).build();

                            user.updateProfile(profileData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        FirebaseFirestore.getInstance().collection("Users").document(user.getUid()).set(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                   // progressDialog.setMessage("User Information Added");
                                                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful()) {
                                                             //  progressDialog.setMessage("Verification mail sent to :" + email);
                                                              //  progressDialog.dismiss();
                                                                startActivity(new Intent(RegisterScreen.this,VerificationCheckScreen.class));
                                                                finish();

                                                            }else{
                                                              //  progressDialog.hide();
                                                                Toast.makeText(RegisterScreen.this,"Error sending Verification Email",Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    });
                                                }else{
                                                    //progressDialog.hide();
                                                    Toast.makeText(RegisterScreen.this,"Error Adding User Information",Toast.LENGTH_LONG).show();
                                                    Log.d("User info",task.getException().getCause().toString());

                                                }
                                            }
                                        });
                                    }
                                }
                            });


                        } else {
                            // If sign in fails, display a message to the user.
                            //progressDialog.hide();
                            Toast.makeText(RegisterScreen.this,"Error Registering User",Toast.LENGTH_LONG).show();


                        }

                        // ...
                    }
                });
    }
}
