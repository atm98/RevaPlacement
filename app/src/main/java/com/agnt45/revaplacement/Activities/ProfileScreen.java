package com.agnt45.revaplacement.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agnt45.revaplacement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;
import java.util.Map;

public class ProfileScreen extends AppCompatActivity {

    private static final String TAG = "lskfn";
    TextView name,dept,srn,mobile,email;
    ImageView imageView;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        imageView = findViewById(R.id.user_pic);
        name = findViewById(R.id.name);
        dept = findViewById(R.id.dept);
        srn = findViewById(R.id.srn);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            if(user.getPhotoUrl()!=null){
                Picasso.get().load(user.getPhotoUrl()).into(imageView);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CropImage.activity()
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .setAspectRatio(1,1)
                                .start(ProfileScreen.this);
                    }
                });
                FirebaseFirestore.getInstance().collection("Users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            name.setText((CharSequence) task.getResult().getData().get("fullName"));
                            dept.setText((CharSequence) task.getResult().getData().get("Department"));
                            srn.setText((CharSequence) task.getResult().getData().get("SRN"));
                            mobile.setText((CharSequence) task.getResult().getData().get("Mobile"));
                            email.setText((CharSequence) task.getResult().getData().get("email"));
                        }
                    }
                });
            }
            else{
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CropImage.activity()
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .setAspectRatio(1,1)
                                .start(ProfileScreen.this);
                    }
                });

            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                final StorageReference filePath = FirebaseStorage.getInstance().getReference().child("Users").child("Profile_Pic").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("dp.jpg");
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Please Wait");
                progressDialog.setMessage("Profile Picture is Being Uploaded");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                filePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setPhotoUri(Uri.parse(uri.toString()))
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    progressDialog.dismiss();
                                                    Picasso.get().load(user.getPhotoUrl()).into(imageView);
                                                    Log.d(TAG, "User profile updated.");
                                                }
                                            }
                                        });
                            }
                        });



                    }
                });
            }
        }
    }
}
