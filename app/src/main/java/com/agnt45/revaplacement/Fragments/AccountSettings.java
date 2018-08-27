package com.agnt45.revaplacement.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agnt45.revaplacement.Activities.HomeScreen;
import com.agnt45.revaplacement.Activities.LoginScreen;
import com.agnt45.revaplacement.Activities.ProfileScreen;
import com.agnt45.revaplacement.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountSettings extends Fragment {


    TextView username,logout;
    FirebaseUser user;
    ImageView userImage;
    LinearLayout layout;
    public AccountSettings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_account_settings, container, false);
        layout = rootView.findViewById(R.id.profile);
        username = layout.findViewById(R.id.User_DName);
        userImage = layout.findViewById(R.id.usr_pic);
        logout = rootView.findViewById(R.id.logout);
        logout.isClickable();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), ProfileScreen.class));
                }
            });
            Picasso.get().load(user.getPhotoUrl()).into(userImage);
            username.setText(user.getDisplayName());
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getActivity(), LoginScreen.class));
                    ((HomeScreen)rootView.getContext()).finish();
                }
            });
        }
        return rootView;
    }

}
